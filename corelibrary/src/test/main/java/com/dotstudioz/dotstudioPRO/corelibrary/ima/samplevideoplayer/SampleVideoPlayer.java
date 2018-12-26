// Copyright 2014 Google Inc. All Rights Reserved.

package com.dotstudioz.dotstudioPRO.dsplayer.ima.samplevideoplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

/**
 * A VideoView that intercepts various methods and reports them back via a PlayerCallback.
 */
public class SampleVideoPlayer extends VideoView implements VideoPlayer {

    public int seekbarBackgroundColor;
    public int seekbarColor;
    private enum PlaybackState {
        STOPPED, PAUSED, PLAYING, BUFFERING_START, BUFFERING_END, ON_PREPARED_CALLED
    }

    private PlaybackState mPlaybackState;
    private final List<PlayerCallback> mVideoPlayerCallbacks = new ArrayList<PlayerCallback>(1);

    public VideoView videoView;
    public MediaControllerExtended mc;
    MediaPlayer mediaPlayerInstance;

    public SampleVideoPlayer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SampleVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SampleVideoPlayer(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPlaybackState = PlaybackState.STOPPED;

        videoView = this;

        super.setOnErrorListener(new OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                System.out.println("onError(MediaPlayer mp, int what, int extra)==>"+what);
                return false;
            }

        });

        super.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                    mPlaybackState = PlaybackState.BUFFERING_START;
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onBufferingStart();
                    }
                }
                if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                    mPlaybackState = PlaybackState.BUFFERING_END;
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onBufferingEnd();
                    }
                }
                return false;
            }
        });

        super.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                System.out.println("INSIDE SampleVideoPlayer onPrepared Called");
                mediaPlayerInstance = mp;
                try {
                    mPlaybackState = PlaybackState.ON_PREPARED_CALLED;
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onPreparedCalled();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Set OnCompletionListener to notify our callbacks when the video is completed.
        super.setOnCompletionListener(new OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                try {
                    // Reset the MediaPlayer.
                    // This prevents a race condition which occasionally results in the media
                    // player crashing when switching between videos.
                    //disablePlaybackControls();
                    mediaPlayer.reset();
                    mediaPlayer.setDisplay(getHolder());
                    //enablePlaybackControls();
                    mPlaybackState = PlaybackState.STOPPED;

                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onCompleted();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Set OnErrorListener to notify our callbacks if the video errors.
        super.setOnErrorListener(new OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                try {
                    mPlaybackState = PlaybackState.STOPPED;
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onError();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

                // Returning true signals to MediaPlayer that we handled the error. This will
                // prevent the completion handler from being called.
                return true;
            }
        });
    }

    public void initializeVideoTexturePresenture() {

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
        }
    }

    @Override
    public void setmFullscreenButton(boolean flag) {
        try {
            mc.setmFullscreenButton(flag);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setCCShowingSetting(boolean flag) {
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
        return mPlaybackState == PlaybackState.STOPPED ? 0 : super.getDuration();
    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {
        // The OnCompletionListener can only be implemented by SampleVideoPlayer.
        throw new UnsupportedOperationException();
    }

    @Override
    public void setOnErrorListener(OnErrorListener listener) {
        // The OnErrorListener can only be implemented by SampleVideoPlayer.
        throw new UnsupportedOperationException();
    }

    // Methods implementing the VideoPlayer interface.
    @Override
    public void play() {
        start();
    }

    @Override
    public void start() {
        super.start();
        try {
            if(mc != null) {
                mc.isPausedManually = false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        try {
            switch (mPlaybackState) {
                case STOPPED:
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onPlay();
                    }
                    break;
                case PAUSED:
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onResume();
                    }
                    break;
                case BUFFERING_START:
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onBufferingStart();
                    }
                    break;
                case BUFFERING_END:
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onBufferingEnd();
                    }
                    break;
                case ON_PREPARED_CALLED:
                    for (PlayerCallback callback : mVideoPlayerCallbacks) {
                        callback.onPreparedCalled();
                    }
                    break;
                default:
                    // Already playing; do nothing.
                    break;
            }
            mPlaybackState = PlaybackState.PLAYING;
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
        super.pause();
        try {
            mPlaybackState = PlaybackState.PAUSED;
            for (PlayerCallback callback : mVideoPlayerCallbacks) {
                callback.onPause();
            }
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
    }

    public boolean doNotSetPausedManuallyFlag = false;
    public void setDoNotSetPausedManuallyFlag(boolean flag) {
        doNotSetPausedManuallyFlag = flag;
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
            if (mPlaybackState == PlaybackState.STOPPED) {
                return;
            }
            super.stopPlayback();
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
        try {
            setMediaController(null);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enablePlaybackControls() {
        try {
            setMediaController(mc);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setVideoPath(String videoUrl, boolean mIsAdDisplayed) {
        //System.out.println("This is inside the SampleVideoPlayer, this is where the ads will play!");
        //System.out.println("setVideoPath videoUrl==>"+videoUrl);
        init();
        videoView.requestFocus();
        videoView.setVideoPath(videoUrl);
        videoView.start();
        /*if(mediaPlayerInstance == null) {
            mediaPlayerInstance = new MediaPlayer();
        }

        mediaPlayerInstance = new MediaPlayer();
        mediaPlayerInstance.setDisplay(getHolder());
        if(mediaPlayerInstance != null) {
            try {
                mediaPlayerInstance.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        try {
                            mPlaybackState = PlaybackState.ON_PREPARED_CALLED;
                            for (PlayerCallback callback : mVideoPlayerCallbacks) {
                                callback.onPreparedCalled();
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                mediaPlayerInstance.setOnCompletionListener(new OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        try {
                            // Reset the MediaPlayer.
                            // This prevents a race condition which occasionally results in the media
                            // player crashing when switching between videos.
                            //disablePlaybackControls();
                            //mediaPlayer.reset();
                            //mediaPlayer.setDisplay(getHolder());
                            //enablePlaybackControls();
                            mPlaybackState = PlaybackState.STOPPED;

                            for (PlayerCallback callback : mVideoPlayerCallbacks) {
                                callback.onCompleted();
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                mediaPlayerInstance.setOnErrorListener(new OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        try {
                            mPlaybackState = PlaybackState.STOPPED;
                            for (PlayerCallback callback : mVideoPlayerCallbacks) {
                                callback.onError();
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                });

                mediaPlayerInstance.setDataSource(videoUrl);
                mediaPlayerInstance.prepareAsync();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }*/
    }

    public void setMediaControllerIsAdPlaying(boolean flag) {
        if(mc != null) {
            mc.isAdPlaying = flag;
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if(mc != null && mc.isShowing())
                    mc.hide();
                break;

            default:
                return super.onKeyDown(keyCode, event);
        }
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        /*if ((KeyEvent.KEYCODE_DPAD_UP == event.getKeyCode() || KeyEvent.KEYCODE_DPAD_DOWN == event.getKeyCode())) {
            //handle key events here
        }*/
        if(KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
            if (mc != null && mc.isShowing())
                mc.hide();
        }
        return super.dispatchKeyEvent(event);
    }
}
