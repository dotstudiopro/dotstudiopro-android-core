// Copyright 2014 Google Inc. All Rights Reserved.

package com.dotstudioz.dotstudioPRO.dsplayer.ima;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dotstudioz.dotstudioPRO.dsplayer.ima.samplevideoplayer.VideoPlayer;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * Video player that can play content video and ads.
 */
public class VideoPlayerWithAdPlayback extends RelativeLayout {

    /** Interface for alerting caller of video completion. */
    public interface OnContentCompleteListener {
        public void onContentComplete();
    }
    public interface OnPlayNextVideoIfAvailableListener {
        public void playNextVideoIfAvailable();
        public void onBufferingEnd();
        public void onBufferingStart();
    }
    public interface OnMediaPlayerPreparedListener {
        public void onPreparedCalled();
        public void showBusyCursor();
        public void hideBusyCursor();
        public void showPoster();
        public void hidePoster();

        public void pauseContentForAdPlayback();
        public void resumeContentAfterAdPlayback();
    }

    // The wrapped video player.
    //public VideoPlayer mVideoPlayer;

    public VideoPlayer mVideoPlayer;

    // The SDK will render ad playback UI elements into this ViewGroup.
    public ViewGroup mAdUiContainer;

    // Used to track if the current video is an ad (as opposed to a content video).
    public boolean mIsAdDisplayed;

    // Used to track the current content video URL to resume content playback.
    private String mContentVideoUrl;

    // The saved position in the ad to resume if app is backgrounded during ad playback.
    public int mSavedAdPosition;

    // The saved position in the content to resume to after ad playback or if app is backgrounded
    // during content playback.
    public int mSavedContentPosition;

    private int seekbarColor;

    // Called when the content is completed.
    private OnContentCompleteListener mOnContentCompleteListener;

    // Called when the content is completed.
    private OnPlayNextVideoIfAvailableListener mOnPlayNextVideoIfAvailableListener;

    private OnMediaPlayerPreparedListener mOnMediaPlayerPreparedListener;

    // VideoAdPlayer interface implementation for the SDK to send ad play/pause type events.
    private VideoAdPlayer mVideoAdPlayer;

    // ContentProgressProvider interface implementation for the SDK to check content progress.
    private ContentProgressProvider mContentProgressProvider;

    private final List<VideoAdPlayer.VideoAdPlayerCallback> mAdCallbacks =
            new ArrayList<VideoAdPlayer.VideoAdPlayerCallback>(1);

    public VideoPlayerWithAdPlayback(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mOnPlayNextVideoIfAvailableListener = (OnPlayNextVideoIfAvailableListener) context;
        mOnMediaPlayerPreparedListener = (OnMediaPlayerPreparedListener) context;
    }

    public VideoPlayerWithAdPlayback(Context context, AttributeSet attrs) {
        super(context, attrs);
        mOnPlayNextVideoIfAvailableListener = (OnPlayNextVideoIfAvailableListener) context;
        mOnMediaPlayerPreparedListener = (OnMediaPlayerPreparedListener) context;
    }

    public VideoPlayerWithAdPlayback(Context context) {
        super(context);
        mOnPlayNextVideoIfAvailableListener = (OnPlayNextVideoIfAvailableListener) context;
        mOnMediaPlayerPreparedListener = (OnMediaPlayerPreparedListener) context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    public void init() {
        try {
            mIsAdDisplayed = false;
            mSavedAdPosition = 0;
            mSavedContentPosition = 0;

            //mVideoPlayer = (VideoPlayer) this.getRootView().findViewById(R.id.videoPlayer);
            //mAdUiContainer = (ViewGroup) this.getRootView().findViewById(R.id.adUiContainer);

            // Define VideoAdPlayer connector.
            mVideoAdPlayer = new VideoAdPlayer() {
                @Override
                public void playAd() {
                    if(mVideoPlayer != null) {
                        mIsAdDisplayed = true;
                        mVideoPlayer.play();
                        mVideoPlayer.pauseContentCalledForAdPlayback();
                    }
                }

                @Override
                public void loadAd(String url) {
                    mIsAdDisplayed = true;
                    if(mVideoPlayer != null) {
                        mVideoPlayer.setVideoPath(url, mIsAdDisplayed);
                    }
                }

                @Override
                public void stopAd() {
                    if(mVideoPlayer != null) {
                        mVideoPlayer.stopPlayback();
                    }
                }

                @Override
                public void pauseAd() {
                    if(mVideoPlayer != null) {
                        mVideoPlayer.pause();
                    }
                }

                @Override
                public void resumeAd() {
                    playAd();
                }

                @Override
                public void addCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
                    mAdCallbacks.add(videoAdPlayerCallback);
                }

                @Override
                public void removeCallback(VideoAdPlayerCallback videoAdPlayerCallback) {
                    mAdCallbacks.remove(videoAdPlayerCallback);
                }

                @Override
                public VideoProgressUpdate getAdProgress() {
                    try {
                        if (!mIsAdDisplayed || mVideoPlayer.getDuration() <= 0) {
                            return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
                        }

                        //System.out.println(mVideoPlayer.getCurrentPosition()+"<==getAdProgress==>"+mVideoPlayer.getDuration());

                        return new VideoProgressUpdate(mVideoPlayer.getCurrentPosition(),
                                mVideoPlayer.getDuration());
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    return new VideoProgressUpdate(0, 0);
                }
            };

            mContentProgressProvider = new ContentProgressProvider() {
                @Override
                public VideoProgressUpdate getContentProgress() {
                    if (mIsAdDisplayed || mVideoPlayer.getDuration() <= 0) {
                        return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
                    }

                    if(mVideoPlayer != null) {
                        return new VideoProgressUpdate(mVideoPlayer.getCurrentPosition(),
                                mVideoPlayer.getDuration());
                    }

                    return new VideoProgressUpdate(0, 0);
                }
            };

            // Set player callbacks for delegating major video events.
            mVideoPlayer.addPlayerCallback(new VideoPlayer.PlayerCallback() {
                @Override
                public void onPlay() {
                    if (mIsAdDisplayed) {
                        for (VideoAdPlayer.VideoAdPlayerCallback callback : mAdCallbacks) {
                            callback.onPlay();
                        }
                    }
                }

                @Override
                public void onPause() {
                    if (mIsAdDisplayed) {
                        for (VideoAdPlayer.VideoAdPlayerCallback callback : mAdCallbacks) {
                            callback.onPause();
                        }
                    }
                }

                @Override
                public void onResume() {
                    if (mIsAdDisplayed) {
                        for (VideoAdPlayer.VideoAdPlayerCallback callback : mAdCallbacks) {
                            callback.onResume();
                        }
                    }
                }

                @Override
                public void onError() {
                    if (mIsAdDisplayed) {
                        for (VideoAdPlayer.VideoAdPlayerCallback callback : mAdCallbacks) {
                            callback.onError();
                        }
                    }
                }

                @Override
                public void onCompleted() {
                    if (mIsAdDisplayed) {
                        for (VideoAdPlayer.VideoAdPlayerCallback callback : mAdCallbacks) {
                            callback.onEnded();
                        }
                    } else {
                        // Alert an external listener that our content video is complete.
                        if (mOnContentCompleteListener != null) {
                            mOnContentCompleteListener.onContentComplete();
                            mOnPlayNextVideoIfAvailableListener.playNextVideoIfAvailable();
                        }
                    }
                }

                @Override
                public void onBufferingStart() {
                    if (!mIsAdDisplayed) {
                        mOnPlayNextVideoIfAvailableListener.onBufferingStart();
                    }
                }

                @Override
                public void onBufferingEnd() {
                    if (!mIsAdDisplayed) {
                        mOnPlayNextVideoIfAvailableListener.onBufferingEnd();
                    }
                }

                @Override
                public void onPreparedCalled() {
                    if (!mIsAdDisplayed) {
                        mOnMediaPlayerPreparedListener.onPreparedCalled();
                    }
                }
            });

            /*mVideoPlayer.addPlayerCallback(new VideoPlayer.PlayerCallback() {
                @Override
                public void onPlay() {
                    if (mIsAdDisplayed) {
                        for (VideoAdPlayer.VideoAdPlayerCallback callback : mAdCallbacks) {
                            callback.onPlay();
                        }
                    }
                }

                @Override
                public void onPause() {
                    if (mIsAdDisplayed) {
                        for (VideoAdPlayer.VideoAdPlayerCallback callback : mAdCallbacks) {
                            callback.onPause();
                        }
                    }
                }

                @Override
                public void onResume() {
                    if (mIsAdDisplayed) {
                        for (VideoAdPlayer.VideoAdPlayerCallback callback : mAdCallbacks) {
                            callback.onResume();
                        }
                    }
                }

                @Override
                public void onError() {
                    if (mIsAdDisplayed) {
                        for (VideoAdPlayer.VideoAdPlayerCallback callback : mAdCallbacks) {
                            callback.onError();
                        }
                    }
                }

                @Override
                public void onCompleted() {
                    if (mIsAdDisplayed) {
                        for (VideoAdPlayer.VideoAdPlayerCallback callback : mAdCallbacks) {
                            callback.onEnded();
                        }
                    } else {
                        // Alert an external listener that our content video is complete.
                        if (mOnContentCompleteListener != null) {
                            mOnContentCompleteListener.onContentComplete();
                            mOnPlayNextVideoIfAvailableListener.playNextVideoIfAvailable();
                        }
                    }
                }

                @Override
                public void onBufferingStart() {
                    if (!mIsAdDisplayed) {
                        mOnPlayNextVideoIfAvailableListener.onBufferingStart();
                    }
                }

                @Override
                public void onBufferingEnd() {
                    if (!mIsAdDisplayed) {
                        mOnPlayNextVideoIfAvailableListener.onBufferingEnd();
                    }
                }

                @Override
                public void onPreparedCalled() {
                    if (!mIsAdDisplayed) {
                        mOnMediaPlayerPreparedListener.onPreparedCalled();
                    }
                }
            });*/
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void showPoster() {
        mOnMediaPlayerPreparedListener.showPoster();
    }
    public void hidePoster() {
        mOnMediaPlayerPreparedListener.hidePoster();
    }
    public void showBusyCursor() {
        mOnMediaPlayerPreparedListener.showBusyCursor();
    }
    public void hideBusyCursor() {
        mOnMediaPlayerPreparedListener.hideBusyCursor();
    }

    /**
     * Set a listener to be triggered when the content (non-ad) video completes.
     */
    public void setOnContentCompleteListener(OnContentCompleteListener listener) {
        mOnContentCompleteListener = listener;
    }

    /**
     * Set the path of the video to be played as content.
     */
    public void setContentVideoPath(String contentVideoUrl) {
        mContentVideoUrl = contentVideoUrl;
    }

    /**
     * Save the playback progress state of the currently playing video. This is called when content
     * is paused to prepare for ad playback or when app is backgrounded.
     */
    public void savePosition() {
        try {
            if (mIsAdDisplayed) {
                mSavedAdPosition = mVideoPlayer.getCurrentPosition();
            } else {
                mSavedContentPosition = mVideoPlayer.getCurrentPosition();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Restore the currently loaded video to its previously saved playback progress state. This is
     * called when content is resumed after ad playback or when focus has returned to the app.
     */
    public void restorePosition() {
        try {
            if (mIsAdDisplayed) {
                mVideoPlayer.seekTo(mSavedAdPosition);
            } else {
                mVideoPlayer.seekTo(mSavedContentPosition);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the content video.
     */
    public void pause() {
        if (mIsAdDisplayed) {
            mVideoPlayer.pause();
        } else {
            mVideoPlayer.pause();
        }
    }

    /**
     * Plays the content video.
     */
    public void play() {
        if (mIsAdDisplayed) {
            mVideoPlayer.play();
        } else {
            mVideoPlayer.play();
        }
    }

    /**
     * Seeks the content video.
     */
    public void seek(int time) {
        if (mIsAdDisplayed) {
            // When ad is playing, set the content video position to seek to when ad finishes.
            mSavedContentPosition = time;
        } else {
            mVideoPlayer.seekTo(time);
        }
    }

    /**
     * Returns current content video play time.
     */
    public int getCurrentContentTime() {
        try {
            if (mIsAdDisplayed) {
                return mSavedContentPosition;
            } else {
                return mVideoPlayer.getCurrentPosition();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Pause the currently playing content video in preparation for an ad to play, and disables
     * the media controller.
     */
    public void pauseContentForAdPlayback() {
        try {
            //mVideoPlayer.disablePlaybackControls();
            mOnMediaPlayerPreparedListener.pauseContentForAdPlayback();
            savePosition();
            mVideoPlayer.stopPlayback();
            mVideoPlayer.pauseContentCalledForAdPlayback();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Resume the content video from its previous playback progress position after
     * an ad finishes playing. Re-enables the media controller.
     */
    public void resumeContentAfterAdPlayback() {
        try {
            if (mContentVideoUrl == null || mContentVideoUrl.isEmpty()) {
                Log.w("ImaExample", "No content URL specified.");
                return;
            }
            try {
                mOnMediaPlayerPreparedListener.resumeContentAfterAdPlayback();
                if (!mIsAdDisplayed) {
                    mVideoPlayer.resumeContentCalledForAdPlayback();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            mIsAdDisplayed = false;
            //String videoUrl = URLParamEncoder.convertToURLEscapingIllegalCharacters(mContentVideoUrl).toString();
            //String videoURL = URLParamEncoder.encode("https://e4z6v2z8.ssl.hwcdn.net/files/company/57fe8fe399f815e309dbc2f4/assets/videos/587d4bdb9fcf702d56a9abdc/vod/587d4bdb9fcf702d56a9abdc.m3u8?hwauth=exp=1484700054345~acl=*~hmac=e8e35e2093733091b84ad4695807d2003d166543c69884a9862103c39a8bcbd8");
            //mVideoPlayer.setVideoPath(mContentVideoUrl);
            mVideoPlayer.setVideoPath(mContentVideoUrl, false);
            //System.out.println("mContentVideoUrl:-" + mContentVideoUrl);
            mVideoPlayer.enablePlaybackControls();
            mVideoPlayer.seekTo(mSavedContentPosition);
            mVideoPlayer.play();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the UI element for rendering video ad elements.
     */
    public ViewGroup getAdUiContainer() {
        return mAdUiContainer;
    }

    /**
     * Returns an implementation of the SDK's VideoAdPlayer interface.
     */
    public VideoAdPlayer getVideoAdPlayer() {
        return mVideoAdPlayer;
    }

    /**
     * Returns if an ad is displayed.
     */
    public boolean getIsAdDisplayed() {
        return mIsAdDisplayed;
    }

    public ContentProgressProvider getContentProgressProvider() {
        return mContentProgressProvider;
    }

    public int getSeekbarColor() {
        return seekbarColor;
    }

    public void setSeekbarColor(int seekbarColor) {
        try {
            this.seekbarColor = seekbarColor;

            if(mVideoPlayer != null)
                mVideoPlayer.setSeekbarColor(seekbarColor);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
