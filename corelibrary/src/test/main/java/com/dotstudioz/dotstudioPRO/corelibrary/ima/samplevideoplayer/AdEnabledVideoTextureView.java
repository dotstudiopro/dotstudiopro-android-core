package com.dotstudioz.dotstudioPRO.dsplayer.ima.samplevideoplayer;

import android.content.Context;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.dotstudioz.dotstudioPRO.dsplayer.player.VideoSource;
import com.dotstudioz.dotstudioPRO.dsplayer.player.VideoTexturePresenter;
import com.dotstudioz.dotstudioPRO.dsplayer.player.VideoTextureView;
import com.dotstudioz.dotstudioPRO.dsplayer.player.assets.AssetsVideoSource;
import com.dotstudioz.dotstudioPRO.dsplayer.player.hls.HlsVideoSource;
import com.google.android.exoplayer.ExoPlayer;

import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohsin on 10-02-2017.
 */

public class AdEnabledVideoTextureView extends VideoTextureView implements VideoPlayer {

    private float videoAspectRatio;

    public AdEnabledVideoTextureView(Context context) {
        super(context);
        init();
    }

    public AdEnabledVideoTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public int seekbarColor;

    private enum PlaybackState {
        STOPPED, PAUSED, PLAYING, BUFFERING_START, BUFFERING_END, ON_PREPARED_CALLED
    }

    private PlaybackState mPlaybackState;
    private final List<PlayerCallback> mVideoPlayerCallbacks = new ArrayList<PlayerCallback>(1);
    public boolean isPreparedAlreadyCalled = false;

    public VideoTexturePresenter videoTexturePresenter;
    public MediaControllerExtended mc;
    private void init() {
        mPlaybackState = PlaybackState.STOPPED;

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!isPlayingAdRightNow) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        try {
                            if(isPreparedAlreadyCalled)
                                mc.show();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.performClick();
                    }
                    return true;
                } else {
                    if(mc != null) {
                        mc.isPausedManually = false;
                        mc.hide();
                    }
                }
                return false;
            }
        });

        mc = new MediaControllerExtended(getContext());
        //mc.setAnchorView(findViewById(R.id.videoSurfaceContainerTest));
        mc.setAnchorView(this);
        /*mc.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return false;
            }
        });


        final int topContainer1 = getResources().getIdentifier("mediacontroller_progress", "id", "android");
        final SeekBar seekbar = (SeekBar) mc.findViewById(topContainer1);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println(fromUser+"<==onProgressChangedonProgressChanged==>"+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/

        initializeVideoTexturePresenture();
    }

    public void initializeVideoTexturePresenture() {
        videoTexturePresenter = new VideoTexturePresenter(this);
        videoTexturePresenter.onCreate();

        videoTexturePresenter.addOnStateChangedListener(new VideoTexturePresenter.OnStateChangedListener() {
            @Override
            public void onStateChanged(boolean playWhenReady, int playbackState) {
                if(playbackState == ExoPlayer.STATE_BUFFERING) {
                    mPlaybackState = PlaybackState.BUFFERING_START;
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onBufferingStart();
                    }
                }
                if(playbackState == ExoPlayer.STATE_READY) {
                    //if(mc != null && !mc.isPausedManually) {
                    if(playWhenReady)
                        play();
                    else
                        pause();
                    //}
                    mPlaybackState = PlaybackState.BUFFERING_END;
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onBufferingEnd();
                    }



                    try {
                        if(!isPreparedAlreadyCalled) {
                            isPreparedAlreadyCalled = true;
                            mPlaybackState = PlaybackState.ON_PREPARED_CALLED;
                            for (PlayerCallback callback : mVideoPlayerCallbacks) {
                                callback.onPreparedCalled();
                            }
                        }
                        if(isPlayingAdRightNow) {
                            mc.hideBusyCursor();
                        }/* else {
                            play();
                        }*/
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                if(playbackState == ExoPlayer.STATE_ENDED) {
                    try {
                        // Reset the MediaPlayer.
                        // This prevents a race condition which occasionally results in the media
                        // player crashing when switching between videos.
                        //disablePlaybackControls();
                        enablePlaybackControls();
                        mPlaybackState = PlaybackState.STOPPED;

                        for (PlayerCallback callback : mVideoPlayerCallbacks) {
                            callback.onCompleted();
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        videoTexturePresenter.addOnErrorListener(new VideoTexturePresenter.OnErrorListener() {
            @Override
            public void onError(Exception e) {
                try {
                    mc.onErrorCalled();
                } catch(Exception e1) {
                    e1.printStackTrace();
                }
                try {
                    mPlaybackState = PlaybackState.STOPPED;
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onError();
                    }
                } catch(Exception ee) {
                    ee.printStackTrace();
                }

                e.printStackTrace();


                try {
                    initializeVideoTexturePresenture();
                    if(lastSetVideoUrl != null && lastSetVideoUrl.length() > 0)
                        setVideoPath(lastSetVideoUrl, lastSetIsPlayingAdRightNow);
                } catch(Exception ee) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void setmFullscreenButton(boolean flag) {
        try {
            mc.setmFullscreenButton(flag);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isCCAvailable = false;
    @Override
    public void setCCShowingSetting(boolean flag) {
        isCCAvailable = flag;
        if(mc != null) {
            try {
                mc.setCCShowing(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isMediaControllerShowing() {
        if(mc != null) {
            return mc.isShowing();
        }
        return false;
    }

    @Override
    public int getDuration() {
        return mPlaybackState == PlaybackState.STOPPED ? 0 : (videoTexturePresenter==null?0:(int)videoTexturePresenter.getDuration());
    }

    // Methods implementing the VideoPlayer interface.
    @Override
    public void play() {
        //start();
        videoTexturePresenter.play();

        try {
            if(mc.isPausedManually)
                mc.onPlayCalled();
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            if(mc != null) {
                mc.isPausedManually = false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSeekbarColor(int seekbarColor) {
        this.seekbarColor = seekbarColor;
    }

    @Override
    public void pause() {
        //super.pause();

        try {
            mc.onPauseCalled();
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            if(mc != null) {
                if(!doNotSetPausedManuallyFlag)
                    mc.isPausedManually = true;
                else
                    doNotSetPausedManuallyFlag = false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            mPlaybackState = PlaybackState.PAUSED;
            for (PlayerCallback callback : mVideoPlayerCallbacks) {
                callback.onPause();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        videoTexturePresenter.pause();
    }

    public boolean doNotSetPausedManuallyFlag = false;
    public void setDoNotSetPausedManuallyFlag(boolean flag) {
        doNotSetPausedManuallyFlag = flag;
    }

    @Override
    public int getCurrentPosition() {
        if(videoTexturePresenter != null && mc != null && !mc.isAdPlaying)
            return (int)videoTexturePresenter.getCurrentPosition();
        try {
            if(videoTexturePresenter != null && mc != null && mc.isAdPlaying)
                return (int)videoTexturePresenter.getCurrentPosition();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void seekTo(int videoPosition) {
        try {
            mc.onSeekCalled((int)videoTexturePresenter.getCurrentPosition(), videoPosition);
        } catch(Exception e) {
            e.printStackTrace();
        }
        videoTexturePresenter.seekTo(videoPosition);
    }

    @Override
    public void pauseContentCalledForAdPlayback() {
        try {
            if(mc != null) {
                mc.isAdPlaying = true;
                mc.hide();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resumeContentCalledForAdPlayback() {
        try {
            if(mc != null) {
                mc.isAdPlaying = false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopPlayback() {
        try {
            mc.onCompletedCalled();
        } catch(Exception e) {
            e.printStackTrace();
        }
        videoTexturePresenter.stop();
        try {
            if (mPlaybackState == PlaybackState.STOPPED) {
                return;
            }
            //super.stopPlayback();
            mPlaybackState = PlaybackState.STOPPED;
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            if(mc != null) {
                mc.isPausedManually = false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disablePlaybackControls() {
        /*try {
            setMediaController(null);
        } catch(Exception e) {
            e.printStackTrace();
        }*/
    }

    public void setIsPlayingAdRightNow(boolean flag) {
        isPlayingAdRightNow = flag;
    }

    @Override
    public void enablePlaybackControls() {
        isPlayingAdRightNow = false;
        if(mc != null) {
            mc.isAdPlaying = false;
        }
        /*try {
            setMediaController(mc);
        } catch(Exception e) {
            e.printStackTrace();
        }*/
    }

    public URL convertToURLEscapingIllegalCharacters(String string){
        try {
            String decodedURL = URLDecoder.decode(string, "UTF-8");
            URL url = new URL(decodedURL);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            return uri.toURL();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void hideMediaController() {
        if(mc != null) {
            mc.hide();
        }
    }
    @Override
    public void hideMediaControllerWithoutValidation() {
        if(mc != null) {
            mc.hideWithoutValidation();
        }
    }
    @Override
    public void hideCC() {
        if(mc != null) {
            mc.hideCC();
        }
    }

    @Override
    public void showMC() {
        if(mc != null) {
            mc.isAdPlaying = false;
            mc.isPausedManually = false;
            mc.show();
            mc.isPausedManually = true;
        }
    }

    public boolean lastSetIsPlayingAdRightNow = false;
    public String lastSetVideoUrl = "";
    public boolean isPlayingAdRightNow = false;
    @Override
    public void setVideoPath(String videoUrl, boolean mIsAdDisplayed) {

        Log.d("AdEnabledVideoTextureView", "setVideoPath==>videoUrl==>"+videoUrl);

        videoTexturePresenter.release();

        isPlayingAdRightNow = mIsAdDisplayed;
        isPreparedAlreadyCalled = false;
        if(mc != null) {
            mc.showBusyCursor();
            mc.isAdPlaying = true;
            mc.isPausedManually = false;
            mc.hide();
        }
        if(!mIsAdDisplayed) {

            isPlayingAdRightNow = false;

            VideoSource source = HlsVideoSource
                    .newBuilder(Uri.parse(convertToURLEscapingIllegalCharacters(videoUrl).toString()), "UserAgent")
                    .bufferSegmentSize(64 * 1024)
                    .bufferSegmentCount(256)
                    .eventHandler(new Handler())
                    .build();

            videoTexturePresenter.setSource(source);
            mc.setCCShowing(isCCAvailable);
            mc.setAnchorView(this);
            try {
                CommonUtils.getInstance().styleMediaController(mc, seekbarColor);
            } catch(Exception e) {
                e.printStackTrace();
            }

            videoTexturePresenter.setMediaControllerForThePlayer(mc);
            videoTexturePresenter.prepare();
            if(mc != null) {
                mc.isAdPlaying = false;
            }
            //videoTexturePresenter.play();
        } else {
            //videoUrl = "https://pubads.g.doubleclick.net/gampad/ads?env=vp&gdfp_req=1&impl=s&output=vast&cmsid=&iu=112111745/nosey&sz=640x480&unviewed_position_start=1&url=undefined&description_url=undefined&cust_params=pos%3Dvideo%26channel-id%3D5898fd5a5beb695476d656c5%26s1%3DBill-Cunningham-Show%26episode-id%3D5894a6bc9fcf70583091f8d9%26episode-Name%3DDNA-Doubt%26series-title%3DBill-Cunningham-Show%26test%3Don&correlator=13601008654685128";
            //videoUrl = "http://www.shaikhmohsin.com/small.webm";
            //videoUrl = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinearvpaid&correlator=";
            //videoUrl = "https://pubads.g.doubleclick.net/gampad/ads?sz=480x70&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dnonlinearvpaid&correlator=";
            System.out.println("videoUrl.toString()==?"+videoUrl.toString());

            if(videoUrl.substring((videoUrl.length()-4), videoUrl.length()).equalsIgnoreCase("m3u8")) {
                VideoSource source = HlsVideoSource
                        .newBuilder(Uri.parse(convertToURLEscapingIllegalCharacters(videoUrl).toString()), "UserAgent")
                        .bufferSegmentSize(64 * 1024)
                        .bufferSegmentCount(256)
                        .eventHandler(new Handler())
                        .build();

                videoTexturePresenter.setSource(source);
                videoTexturePresenter.prepare();
                //videoTexturePresenter.play();
                if(mc != null) {
                    mc.isAdPlaying = true;
                }
                isPlayingAdRightNow = true;
            } else {
                VideoSource source = AssetsVideoSource
                        //.newBuilder(Uri.parse(convertToURLEscapingIllegalCharacters(videoUrl).toString()), "UserAgent")
                        .newBuilder(Uri.parse(videoUrl.toString()), "UserAgent")
                        .bufferSegmentSize(64 * 1024)
                        .bufferSegmentCount(256)
                        .build();

                videoTexturePresenter.setSource(source);
                videoTexturePresenter.prepare();
                //videoTexturePresenter.play();
                if(mc != null) {
                    mc.isAdPlaying = true;
                }
                isPlayingAdRightNow = true;
            }

            /*VideoSource source = HlsVideoSource
                    .newBuilder(Uri.parse(convertToURLEscapingIllegalCharacters(videoUrl).toString()), "UserAgent")
                    .bufferSegmentSize(64 * 1024)
                    .bufferSegmentCount(256)
                    .eventHandler(new Handler())
                    .build();*/





        }
    }

    public void setMediaControllerIsAdPlaying(boolean flag) {
        if(mc != null) {
            mc.isAdPlaying = flag;
            isPlayingAdRightNow = flag;
            mc.isPausedManually = !flag;
        }
    }

    @Override
    public void addPlayerCallback(PlayerCallback callback) {
        mVideoPlayerCallbacks.add(callback);
    }

    @Override
    public void removePlayerCallback(PlayerCallback callback) {
        try {
            mVideoPlayerCallbacks.remove(callback);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * Set the aspect ratio that this  should satisfy.
     *
     * @param widthHeightRatio The width to height ratio.
     */
    public void setVideoWidthHeightRatio(float widthHeightRatio) {
        if (this.videoAspectRatio != widthHeightRatio) {
            this.videoAspectRatio = widthHeightRatio;
            requestLayout();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        /*if (videoAspectRatio != 0) {
            float viewAspectRatio = (float) width / height;
            float aspectDeformation = videoAspectRatio / viewAspectRatio - 1;
            if (aspectDeformation > MAX_ASPECT_RATIO_DEFORMATION_PERCENT) {
                height = (int) (width / videoAspectRatio);
            } else if (aspectDeformation < -MAX_ASPECT_RATIO_DEFORMATION_PERCENT) {
                width = (int) (height * videoAspectRatio);
            }
        }*/
        setMeasuredDimension(width, height);
    }

    public boolean updateTextureViewSize(int screenWidth, int screenHeight, int mVideoWidth, int mVideoHeight) {
        try {
            float scaleX = 1.0f;
            float scaleY = 1.0f;
            // Calculate pivot points, in our case crop from center
            int pivotPointX = screenWidth / 2;
            int pivotPointY = screenHeight / 2;
            float ratio = (float) mVideoHeight / mVideoWidth;
            float finalVideoWidth = (float) screenWidth;
            float finalVideoHeight = (float) screenWidth * ratio;

            scaleX = 1.0f;
            scaleY = ratio;

            if (finalVideoHeight < screenHeight) {
                finalVideoHeight = (float) screenHeight;
                finalVideoWidth = (float) finalVideoHeight / ratio;
                scaleY = 1.0f;
                scaleX = ratio;
            }

            if (finalVideoWidth > screenWidth) {
                scaleX = (float) finalVideoWidth / screenWidth;
                scaleY = 1.0f;
            } else if (finalVideoHeight > screenHeight) {
                scaleY = (float) finalVideoHeight / screenHeight;
                scaleX = 1.0f;
            }

            Matrix matrix = new Matrix();
            matrix.setScale(scaleX, scaleY, pivotPointX, pivotPointY);
            this.setTransform(matrix);
            this.setLayoutParams(new FrameLayout.LayoutParams(screenWidth, screenHeight));

            return true;
        } catch(Exception e) {
            //e.printStackTrace();
            return false;
        }
    }
}
