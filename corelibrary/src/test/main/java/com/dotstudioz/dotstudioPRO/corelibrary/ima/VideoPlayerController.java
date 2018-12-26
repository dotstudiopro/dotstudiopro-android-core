// Copyright 2014 Google Inc. All Rights Reserved.

package com.dotstudioz.dotstudioPRO.dsplayer.ima;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dotstudioz.dotstudioPRO.analytics.AnalyticsConstants;
import com.dotstudioz.dotstudioPRO.dsplayer.ima.samplevideoplayer.VideoPlayer;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.UiElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ads logic for handling the IMA SDK integration code and events.
 */
public class VideoPlayerController {

    /**
     * Log interface, so we can output the log commands to the UI or similar.
     */
    public interface Logger {
        void log(String logMessage);
    }

    // Container with references to video player and ad UI ViewGroup.
    private AdDisplayContainer mAdDisplayContainer;

    // The AdsLoader instance exposes the requestAds method.
    public AdsLoader mAdsLoader;

    // AdsManager exposes methods to control ad playback and listen to ad events.
    public AdsManager mAdsManager;

    // Factory class for creating SDK objects.
    private ImaSdkFactory mSdkFactory;

    private AdsRenderingSettings mAdsRenderingSettings;

    // Ad-enabled video player.
    public VideoPlayerWithAdPlayback mVideoPlayerWithAdPlayback;

    // Button the user taps to begin video playback and ad request.
    private View mPlayButton;

    // VAST ad tag URL to use when requesting ads during video playback.
    private String mCurrentAdTagUrl;

    // URL of content video.
    private String mContentVideoUrl;

    // ViewGroup to render an associated companion ad into.
    private ViewGroup mCompanionViewGroup;

    // Tracks if the SDK is playing an ad, since the SDK might not necessarily use the video
    // player provided to play the video ad.
    public boolean mIsAdPlaying;

    // View that handles taps to toggle ad pause/resume during video playback.
    private View mPlayPauseToggle;

    public int currentContentVideoDuration = 0;

    // View that we can write log messages to, to display in the UI.
    private Logger mLog;

    private int seekbarColor = 0;

    public boolean isPlaying() {
        return mIsAdPlaying;
    }

    public boolean isAdPausedManually=  false;

    public void discardAdBreakCustom() {
        //mAdsManager.discardAdBreak();
    }

    // Inner class implementation of AdsLoader.AdsLoaderListener.
    private class AdsLoadedListener implements AdsLoader.AdsLoadedListener {
        /**
         * An event raised when ads are successfully loaded from the ad server via AdsLoader.
         */
        @Override
        public void onAdsManagerLoaded(AdsManagerLoadedEvent adsManagerLoadedEvent) {
            // Ads were successfully loaded, so get the AdsManager instance. AdsManager has
            // events for ad playback and errors.
            mAdsManager = adsManagerLoadedEvent.getAdsManager();

            // Attach event and error event listeners.
            mAdsManager.addAdErrorListener(new AdErrorEvent.AdErrorListener() {
                /**
                 * An event raised when there is an error loading or playing ads.
                 */
                @Override
                public void onAdError(AdErrorEvent adErrorEvent) {
                    log("Ad Error: " + adErrorEvent.getError().getMessage());
                    //Toast.makeText(ctx, "Ad Error: " + adErrorEvent.getError().getMessage(), Toast.LENGTH_SHORT).show();
                    resumeContent();
                }
            });
            mAdsManager.addAdEventListener(new AdEvent.AdEventListener() {
                /**
                 * Responds to AdEvents.
                 */
                @Override
                public void onAdEvent(AdEvent adEvent) {
                    log("Event: " + adEvent.getType());

                    /*if(adEvent.getType() == AdEvent.AdEventType.TAPPED) {
                        try {
                            if (!isAdPausedManually) {
                                mAdsManager.pause();
                                mVideoPlayerWithAdPlayback.mVideoPlayer.pause();
                                isAdPausedManually = true;
                                iVideoPlayerController.adPausedResumed(true);
                            } else {
                                mAdsManager.resume();
                                mVideoPlayerWithAdPlayback.mVideoPlayer.play();
                                isAdPausedManually = false;
                                iVideoPlayerController.adPausedResumed(false);
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }*/

                    // These are the suggested event types to handle. For full list of all ad
                    // event types, see the documentation for AdEvent.AdEventType.
                    switch (adEvent.getType()) {
                        case LOADED:
                            System.out.println("INSIDE AD_LOADED");
                            // AdEventType.LOADED will be fired when ads are ready to be
                            // played. AdsManager.start() begins ad playback. This method is
                            // ignored for VMAP or ad rules playlists, as the SDK will
                            // automatically start executing the playlist.
                            if(mAdsManager != null)
                                mAdsManager.start();

                            try {
                                if(mVideoPlayerWithAdPlayback.mSavedContentPosition == 0 || mVideoPlayerWithAdPlayback.mSavedContentPosition == 1)
                                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_LOADED, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_IDLE);
                                else if(mVideoPlayerWithAdPlayback.mSavedContentPosition == currentContentVideoDuration)
                                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_LOADED, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_COMPLETED);
                                else
                                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_LOADED, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_PLAYING);
                            } catch (Exception e) {
                            }
                            try { iVideoPlayerController.sendGAEvent(AnalyticsConstants.GA_CATEGORY_PLAYER, AnalyticsConstants.GA_SETUP_3_AD_LOADED, iVideoPlayerController.getCurrentVideoID()); } catch(Exception e){}

                            break;
                        case STARTED:
                            try {
                                System.out.println("INSIDE AD_AD_BREAK_STARTED");
                                if(mVideoPlayerWithAdPlayback.mSavedContentPosition == 0 || mVideoPlayerWithAdPlayback.mSavedContentPosition == 1)
                                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_IMPRESSION, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_IDLE);
                                else if(mVideoPlayerWithAdPlayback.mSavedContentPosition == currentContentVideoDuration)
                                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_IMPRESSION, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_COMPLETED);
                                else
                                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_IMPRESSION, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_PLAYING);
                            } catch (Exception e) {
                            }
                            try { iVideoPlayerController.sendGAEvent(AnalyticsConstants.GA_CATEGORY_PLAYER, AnalyticsConstants.GA_SETUP_5_AD_STARTED, iVideoPlayerController.getCurrentVideoID()); } catch(Exception e){}
                            try { iVideoPlayerController.sendGAEvent(AnalyticsConstants.GA_CATEGORY_PLAYER, AnalyticsConstants.GA_SETUP_4_AD_IMPRESSION, iVideoPlayerController.getCurrentVideoID()); } catch(Exception e){}
                            break;
                        case CLICKED:
                            iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_CLICK, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_PLAYING);
                            try { iVideoPlayerController.sendGAEvent(AnalyticsConstants.GA_CATEGORY_PLAYER, AnalyticsConstants.GA_AD_CLICKED, iVideoPlayerController.getCurrentVideoID()); } catch(Exception e){}
                            break;
                        case SKIPPED:
                            iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_SKIP, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_PLAYING);
                            break;
                        case CONTENT_PAUSE_REQUESTED:
                            // AdEventType.CONTENT_PAUSE_REQUESTED is fired immediately before
                            // a video ad is played.
                            isAdPausedManually = false;
                            pauseContent();
                            hidePosterAndBusyCursor();
                            break;
                        case COMPLETED:
                            try {
                                System.out.println("INSIDE AD_COMPLETE");
                                if(mVideoPlayerWithAdPlayback.mSavedContentPosition == 0 || mVideoPlayerWithAdPlayback.mSavedContentPosition == 1)
                                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_COMPLETE, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_IDLE);
                                else if(mVideoPlayerWithAdPlayback.mSavedContentPosition == currentContentVideoDuration)
                                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_COMPLETE, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_COMPLETED);
                                else
                                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_COMPLETE, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_PLAYING);
                            } catch (Exception e) {
                            }
                            try { iVideoPlayerController.sendGAEvent(AnalyticsConstants.GA_CATEGORY_PLAYER, AnalyticsConstants.GA_SETUP_6_AD_COMPLETE, iVideoPlayerController.getCurrentVideoID()); } catch(Exception e){}
                            break;
                        case CONTENT_RESUME_REQUESTED:
                            // AdEventType.CONTENT_RESUME_REQUESTED is fired when the ad is
                            // completed and you should start playing your content.
                            resumeContent();
                            break;
                        case PAUSED:
                            break;
                        case RESUMED:
                            break;
                        case FIRST_QUARTILE:
                            //try { iVideoPlayerController.sendGAEvent(AnalyticsConstants.GA_CATEGORY_PLAYER, AnalyticsConstants.GA_VIEW_QUARTILE_1, iVideoPlayerController.getCurrentVideoID()); } catch(Exception e){}
                            break;
                        case MIDPOINT:
                            //try { iVideoPlayerController.sendGAEvent(AnalyticsConstants.GA_CATEGORY_PLAYER, AnalyticsConstants.GA_VIEW_QUARTILE_2, iVideoPlayerController.getCurrentVideoID()); } catch(Exception e){}
                            break;
                        case THIRD_QUARTILE:
                            //try { iVideoPlayerController.sendGAEvent(AnalyticsConstants.GA_CATEGORY_PLAYER, AnalyticsConstants.GA_VIEW_QUARTILE_3, iVideoPlayerController.getCurrentVideoID()); } catch(Exception e){}
                            break;
                        case ALL_ADS_COMPLETED:
                            System.out.println("INSIDE AD_ALL_ADS_COMPLETED");
                            if (mAdsManager != null) {
                                mAdsManager.destroy();
                                mAdsManager = null;
                            }
                            break;
                        default:
                            break;
                    }
                }
            });
            mAdsManager.init(mAdsRenderingSettings);
            //mAdsManager.init();
        }
    }

    public void resetParametersBeforeStartingAnotherVideo() {
        try {
            mIsAdPlaying = false;
            mVideoPlayerWithAdPlayback.mSavedAdPosition = 0;
            mVideoPlayerWithAdPlayback.mSavedContentPosition = 0;
            mVideoPlayerWithAdPlayback.mVideoPlayer.stopPlayback();
            //mVideoPlayerWithAdPlayback.mVideoPlayer.initializeVideoTexturePresenture();
            mAdsLoader.contentComplete();

            //nullifying the adsLoader
            try {
                mAdsLoader.removeAdsLoadedListener(adsLoadedListener);
                mAdsLoader.removeAdErrorListener(adErrorListener);
            } catch(Exception e) {
                e.printStackTrace();
            }
            mAdsLoader = null;

            //nullifying the adsManager
            if (mAdsManager != null) {
                mAdsManager.destroy();
                mAdsManager = null;
            }
            try {
                mAdsManager.init(mAdsRenderingSettings);
            } catch(Exception e) {
                //e.printStackTrace();
            }
            //mAdsManager.init();


            initializeAdsManager(ctx, language);
        } catch(Exception e) {
            e.printStackTrace();
        }

        /*try {
            initializeAdsManager(ctx, "en");

            mVideoPlayerWithAdPlayback.setOnContentCompleteListener(
                    new VideoPlayerWithAdPlayback.OnContentCompleteListener() {
                        *//**
         * Event raised by VideoPlayerWithAdPlayback when content video is complete.
         *//*
                        @Override
                        public void onContentComplete() {
                            mAdsLoader.contentComplete();
                        }
                    });
        } catch(Exception e) {
            e.printStackTrace();
        }*/
    }

    public Context ctx;
    public String language;
    public VideoPlayerController(Context context,
                                 VideoPlayerWithAdPlayback videoPlayerWithAdPlayback,
                                 VideoPlayer videoAdsPlayer, VideoPlayer videoPlayer, ViewGroup mAdUiContainer, View playButton,
                                 View playPauseToggle, String language, ViewGroup companionViewGroup, Logger log) {
        ctx = context;
        this.language = language;

        iVideoPlayerController = (IVideoPlayerController) context;
        mVideoPlayerWithAdPlayback = videoPlayerWithAdPlayback;
        //mVideoPlayerWithAdPlayback.mVideoPlayer = videoAdsPlayer;
        mVideoPlayerWithAdPlayback.mVideoPlayer = videoPlayer;
        mVideoPlayerWithAdPlayback.mAdUiContainer = mAdUiContainer;
        mVideoPlayerWithAdPlayback.init();
        mPlayButton = playButton;
        mPlayPauseToggle = playPauseToggle;
        mIsAdPlaying = false;
        mCompanionViewGroup = companionViewGroup;
        mLog = log;

        initializeAdsManager(context, language);

        mVideoPlayerWithAdPlayback.setOnContentCompleteListener(
                new VideoPlayerWithAdPlayback.OnContentCompleteListener() {
                    /**
                     * Event raised by VideoPlayerWithAdPlayback when content video is complete.
                     */
                    @Override
                    public void onContentComplete() {
                        mAdsLoader.contentComplete();
                    }
                });

        // When Play is clicked, request ads and hide the button.
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestAndPlayAds();
            }
        });
    }

    public void initializeAdsManager(Context context,String language) {
        // Create an AdsLoader and optionally set the language.
        ImaSdkSettings imaSdkSettings = new ImaSdkSettings();
        imaSdkSettings.setLanguage(language);
        mSdkFactory = null;
        mSdkFactory = ImaSdkFactory.getInstance();
        mAdsRenderingSettings = mSdkFactory.createAdsRenderingSettings();
        Set<UiElement> set = new HashSet<UiElement>();
        set.add(UiElement.AD_ATTRIBUTION);
        set.add(UiElement.COUNTDOWN);
        mAdsRenderingSettings.setUiElements(set);
        //mAdsRenderingSettings.setMimeTypes();
        List<String> mimes = Arrays.asList("video/mp4");
        mAdsRenderingSettings.setMimeTypes(mimes);
        mAdsLoader = null;
        mAdsLoader = mSdkFactory.createAdsLoader(context, imaSdkSettings);



        adsLoadedListener = new VideoPlayerController.AdsLoadedListener();
        mAdsLoader.addAdsLoadedListener(adsLoadedListener);
        mAdsLoader.addAdErrorListener(adErrorListener);
        //mAdsLoader.addAdsLoadedListener(new VideoPlayerController.AdsLoadedListener());
    }

    public VideoPlayerController.AdsLoadedListener adsLoadedListener;
    public AdErrorEvent.AdErrorListener adErrorListener = new AdErrorEvent.AdErrorListener() {
        @Override
        public void onAdError(AdErrorEvent adErrorEvent) {
            log("Ad Error: " + adErrorEvent.getError().getMessage());
            //Toast.makeText(ctx, "Ad Error: " + adErrorEvent.getError().getMessage(), Toast.LENGTH_SHORT).show();
            iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_ERROR, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_PLAYING);
            try { iVideoPlayerController.sendGAEvent(AnalyticsConstants.GA_CATEGORY_PLAYER, AnalyticsConstants.GA_AD_ERROR, iVideoPlayerController.getCurrentVideoID()); } catch(Exception e){}

            try {
                resumeContent();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void log(String message) {
        if (mLog != null) {
            mLog.log(message + "\n");
        }
    }

    public void hidePosterAndBusyCursor() {
        try {
            mVideoPlayerWithAdPlayback.hideBusyCursor();
            mVideoPlayerWithAdPlayback.hidePoster();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void pauseContent() {
        try {
            mVideoPlayerWithAdPlayback.pauseContentForAdPlayback();
            mIsAdPlaying = true;
            setPlayPauseOnAdTouch();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void resumeContent() {
        try {
            mIsAdPlaying = false;
            mVideoPlayerWithAdPlayback.resumeContentAfterAdPlayback();
            removePlayPauseOnAdTouch();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the ad tag URL the player should use to request ads when playing a content video.
     */
    public void setAdTagUrl(String adTagUrl) {
        mCurrentAdTagUrl = adTagUrl;
    }

    public String getAdTagUrl() {
        return mCurrentAdTagUrl;
    }

    /**
     * Request and subsequently play video ads from the ad server.
     */
    public void requestAndPlayAds() {
        try {
            if (mCurrentAdTagUrl == null || mCurrentAdTagUrl == "") {
                log("No VAST ad tag URL specified");
                resumeContent();
                return;
            }

            // Since we're switching to a new video, tell the SDK the previous video is finished.
            if (mAdsManager != null) {
                mAdsManager.destroy();
            }
            if(mAdsLoader == null) {
                initializeAdsManager(ctx, language);
            } else {
                mAdsLoader.contentComplete();
            }

            mPlayButton.setVisibility(View.GONE);
            mAdDisplayContainer = mSdkFactory.createAdDisplayContainer();
            mAdDisplayContainer.setPlayer(mVideoPlayerWithAdPlayback.getVideoAdPlayer());
            mAdDisplayContainer.setAdContainer(mVideoPlayerWithAdPlayback.getAdUiContainer());

            // Set up spots for companions.
            CompanionAdSlot companionAdSlot = mSdkFactory.createCompanionAdSlot();
            companionAdSlot.setContainer(mCompanionViewGroup);
            companionAdSlot.setSize(728, 90);
            ArrayList<CompanionAdSlot> companionAdSlots = new ArrayList<CompanionAdSlot>();
            companionAdSlots.add(companionAdSlot);
            mAdDisplayContainer.setCompanionSlots(companionAdSlots);

            // Create the ads request.
            AdsRequest request = mSdkFactory.createAdsRequest();
            request.setAdTagUrl(mCurrentAdTagUrl);
            request.setAdDisplayContainer(mAdDisplayContainer);
            request.setContentProgressProvider(mVideoPlayerWithAdPlayback.getContentProgressProvider());

            // Request the ad. After the ad is loaded, onAdsManagerLoaded() will be called.
            mAdsLoader.requestAds(request);

            try {
                if(mVideoPlayerWithAdPlayback.mSavedContentPosition == 0 || mVideoPlayerWithAdPlayback.mSavedContentPosition == 1)
                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_REQUEST, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_IDLE);
                else if(mVideoPlayerWithAdPlayback.mSavedContentPosition == currentContentVideoDuration)
                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_REQUEST, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_COMPLETED);
                else
                    iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_REQUEST, mCurrentAdTagUrl, currentContentVideoDuration, mVideoPlayerWithAdPlayback.mSavedContentPosition, mVideoPlayerWithAdPlayback.mSavedContentPosition, AnalyticsConstants.OLD_STATE_PLAYING);
            } catch (Exception e) {
                iVideoPlayerController.createAndAddEventInMainObject(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_REQUEST, mCurrentAdTagUrl, currentContentVideoDuration, 0, 0, AnalyticsConstants.OLD_STATE_IDLE);
            }

            try { iVideoPlayerController.sendGAEvent(AnalyticsConstants.GA_CATEGORY_PLAYER, AnalyticsConstants.GA_SETUP_2_AD_REQUEST, iVideoPlayerController.getCurrentVideoID()); } catch(Exception e){}
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Touch to toggle play/pause during ad play instead of seeking.
     */
    private void setPlayPauseOnAdTouch() {
        // Use AdsManager pause/resume methods instead of the video player pause/resume methods
        // in case the SDK is using a different, SDK-created video player for ad playback.
        mPlayPauseToggle.setOnTouchListener(
                new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent event) {
                        // If an ad is playing, touching it will toggle playback.
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            try {
                                /*if (mIsAdPlaying) {
                                    mAdsManager.pause();
                                    mVideoPlayerWithAdPlayback.mVideoPlayer.pause();
                                    mIsAdPlaying = false;
                                    iVideoPlayerController.adPausedResumed(true);
                                } else {
                                    mAdsManager.resume();
                                    mVideoPlayerWithAdPlayback.mVideoPlayer.play();
                                    mIsAdPlaying = true;
                                    iVideoPlayerController.adPausedResumed(false);
                                }*/
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                            return true;
                        } else {
                            return false;
                        }
                        //return false;
                    }
                }
        );

    }

    /**
     * Remove the play/pause on touch behavior.
     */
    private void removePlayPauseOnAdTouch() {
        mPlayPauseToggle.setOnTouchListener(null);
    }

    /**
     * Set metadata about the content video. In more complex implementations, this might
     * more than just a URL and could trigger additional decisions regarding ad tag selection.
     */
    public void setContentVideo(String videoPath) {
        try {
            mVideoPlayerWithAdPlayback.setContentVideoPath(videoPath);
            mContentVideoUrl = videoPath;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseContentCalledForAdPlayback() {
        try {
            mVideoPlayerWithAdPlayback.mVideoPlayer.pauseContentCalledForAdPlayback();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void resumeContentCalledForAdPlayback() {
        try {
            mVideoPlayerWithAdPlayback.mVideoPlayer.resumeContentCalledForAdPlayback();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void stopPlayBack() {
        try {
            mVideoPlayerWithAdPlayback.mVideoPlayer.stopPlayback();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getContentVideoUrl() {
        return mContentVideoUrl;
    }

    /**
     * Save position of the video, whether content or ad. Can be called when the app is
     * paused, for example.
     */
    public void pause() {
        try {
            mVideoPlayerWithAdPlayback.savePosition();
            if (mAdsManager != null && mVideoPlayerWithAdPlayback.getIsAdDisplayed()) {
                mAdsManager.pause();
            } else {
                mVideoPlayerWithAdPlayback.pause();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Restore the previously saved progress location of the video. Can be called when
     * the app is resumed.
     */
    public void resume() {
        try {
            mVideoPlayerWithAdPlayback.restorePosition();
            if (mAdsManager != null && mVideoPlayerWithAdPlayback.getIsAdDisplayed()) {
                mAdsManager.resume();
            } else {
                mVideoPlayerWithAdPlayback.play();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Seeks to time in content video in seconds.
     */
    public void seek(double time) {
        try {
            mVideoPlayerWithAdPlayback.seek((int) (time * 1000.0));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current time of the content video in seconds.
     */
    public double getCurrentContentTime() {
        return ((double) mVideoPlayerWithAdPlayback.getCurrentContentTime()) / 1000.0;
    }

    public int getSeekbarColor() {
        return seekbarColor;
    }

    public void setSeekbarColor(int seekbarColor) {
        try {
            this.seekbarColor = seekbarColor;
            if (mVideoPlayerWithAdPlayback != null)
                mVideoPlayerWithAdPlayback.setSeekbarColor(seekbarColor);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void fullScreenIconSetting(boolean flag) {
        try {
            mVideoPlayerWithAdPlayback.mVideoPlayer.setmFullscreenButton(flag);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setCCShowingSetting(boolean flag) {
        try {
            mVideoPlayerWithAdPlayback.mVideoPlayer.setCCShowingSetting(flag);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMediaControllerVisible() {
        return mVideoPlayerWithAdPlayback.mVideoPlayer.isMediaControllerShowing();
    }


    public interface IVideoPlayerController {
        void analyticsPlayerCollectionVideoCollection(String eventName);
        void analyticsPlayerCollectionVideoCollector(String eventName, String start, String end);
        void analyticsPlayerCollectionVideoCollection(String category, String type, int duration, int position, int positionEnd, String oldState);

        void createAndAddEventInMainObject(String category, String type, String message, int duration, int position, int positionEnd, String oldState);
        void sendGAEvent(String eventCategory, String eventType, String videoID);
        String getCurrentVideoID();

        void adPausedResumed(boolean flag);
    }
    public IVideoPlayerController iVideoPlayerController;
}
