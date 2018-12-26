/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dotstudioz.dotstudioPRO.corelibrary.dotstudiovideoplayer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.analytics.AnalyticsConstants;
import com.google.android.exoplayer.util.PlayerControl;

import java.lang.ref.WeakReference;
import java.util.Formatter;
import java.util.Locale;

/**
 * A view containing controls for a MediaPlayer. Typically contains the
 * buttons like "Play/Pause", "Rewind", "Fast Forward" and a progress
 * slider. It takes care of synchronizing the controls with the state
 * of the MediaPlayer.
 * <p>
 * The way to use this class is to instantiate it programatically.
 * The MediaController will create a default set of controls
 * and put them in a window floating above your application. Specifically,
 * the controls will float above the view specified with setAnchorView().
 * The window will disappear if left idle for three seconds and reappear
 * when the user touches the anchor view.
 * <p>
 * Functions like show() and hide() have no effect when MediaController
 * is created in an xml layout.
 * 
 * MediaController will hide and
 * show the buttons according to these rules:
 * <ul>
 * <li> The "previous" and "next" buttons are hidden until setPrevNextListeners()
 *   has been called
 * <li> The "previous" and "next" buttons are visible but disabled if
 *   setPrevNextListeners() was called with null listeners
 * <li> The "rewind" and "fastforward" buttons are shown unless requested
 *   otherwise by using the MediaController(Context, boolean) constructor
 *   with the boolean set to false
 * </ul>
 */
public class VideoControllerView extends FrameLayout {
    private static final String TAG = "VideoControllerView";
    
    private MediaPlayerControl  mPlayer;
    private String              seekBarColor = "";
    private PlayerControl       mPlayerControl;
    private Context             mContext;
    private ViewGroup           mAnchor;
    private View                mRoot;
    private SeekBar             mProgress;
    private TextView            mEndTime, mCurrentTime;
    public static boolean       mShowing;
    private boolean             mDragging;
    private static final int    sDefaultTimeout = 3000;
    private static final int    FADE_OUT = 1;
    private static final int    SHOW_PROGRESS = 2;
    private boolean             mUseFastForward;
    private boolean             mFromXml;
    private boolean             mListenersSet;
    private OnClickListener mNextListener, mPrevListener;
    StringBuilder               mFormatBuilder;
    Formatter                   mFormatter;
    private ImageButton         mPauseButton;
    private ImageButton         mFfwdButton;
    private ImageButton         mRewButton;
    private ImageButton         mNextButton;
    private ImageButton         mPrevButton;
    private ImageButton         mFullscreenButton;
    private ImageButton         mFullscreenButton_live;
    private View                extraViewInCenter;
    private LinearLayout        buttonsLinearLayout;
    private LinearLayout        bufferingLinearLayout;
    private boolean             bufferingFlag;
    private LinearLayout        seekbar_container;
    private Handler             mHandler = new MessageHandler(this);

    Typeface tfRegular = Typeface.createFromAsset(this.getResources().getAssets(), "montserrat-regular.ttf");
    Typeface tfBold = Typeface.createFromAsset(this.getResources().getAssets(), "montserrat-bold.ttf");

    public VideoControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRoot = null;
        mContext = context;
        mUseFastForward = true;
        mFromXml = true;
        
        Log.i(TAG, TAG);
    }

    public VideoControllerView(Context context, boolean useFastForward) {
        super(context);
        mContext = context;
        mUseFastForward = useFastForward;
        
        Log.i(TAG, TAG);
    }

    public VideoControllerView(Context context) {
        this(context, true);

        Log.i(TAG, TAG);
    }

    @Override
    public void onFinishInflate() {
        if (mRoot != null)
            initControllerView(mRoot);
    }

    public void setSeekBarColor(String seekBarColor) {
        this.seekBarColor = seekBarColor;

        if(mProgress != null) {
            if (seekBarColor.length() > 0) {
                mProgress.getProgressDrawable().setColorFilter(Color.parseColor(seekBarColor), PorterDuff.Mode.SRC_IN);
                mProgress.getThumb().setColorFilter(Color.parseColor(seekBarColor), PorterDuff.Mode.SRC_IN);
            }
        }
    }
    
    public void setMediaPlayer(MediaPlayerControl player) {
        mPlayer = player;
        updatePausePlay();
        updateFullScreen();
    }
    public void setMediaPlayer(PlayerControl player) {
        mPlayerControl = player;
        updatePausePlay();
        updateFullScreen();
    }

    /**
     * Set the view that acts as the anchor for the control view.
     * This can for example be a VideoView, or your Activity's main view.
     * @param view The view to which to anchor the controller when it is visible.
     */
    public void setAnchorView(ViewGroup view) {
        mAnchor = view;

        LayoutParams frameParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        removeAllViews();
        View v = makeControllerView();
        addView(v, frameParams);
    }

    /**
     * Create the view that holds the widgets that control playback.
     * Derived classes can override this to create their own.
     * @return The controller view.
     * @hide This doesn't work as advertised
     */
    protected View makeControllerView() {
        LayoutInflater inflate = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRoot = inflate.inflate(R.layout.media_controller, null);

        initControllerView(mRoot);

        return mRoot;
    }

    public void setBufferingFlag(boolean flagValue) {
        bufferingFlag = flagValue;
    }

    public boolean getBufferingFlag() {
        return bufferingFlag;
    }

    public void hideButtonsForLive() {
        if(mFfwdButton!=null) mFfwdButton.setVisibility(View.GONE);
        if(mRewButton!=null) mRewButton.setVisibility(View.GONE);
        if(mNextButton!=null) mNextButton.setVisibility(View.GONE);
        if(mPrevButton!=null) mPrevButton.setVisibility(View.GONE);
        if(mProgress!=null) mProgress.setVisibility(View.GONE);
        if(mCurrentTime!=null) mCurrentTime.setVisibility(View.GONE);
        if(mEndTime!=null) mEndTime.setVisibility(View.GONE);
        if(seekbar_container!=null) seekbar_container.setVisibility(View.GONE);
        if(extraViewInCenter!=null) extraViewInCenter.setVisibility(View.VISIBLE);
        if(mFullscreenButton!=null) mFullscreenButton.setVisibility(View.GONE);
        if(mFullscreenButton_live!=null) mFullscreenButton_live.setVisibility(View.VISIBLE);
    }

    public void showButtonsForM3U8() {
        if(mFfwdButton!=null) {
            mFfwdButton.setVisibility(View.VISIBLE);
            if (mFfwdButton != null) {
                mFfwdButton.setOnClickListener(mFfwdListener);
                if (!mFromXml) {
                    mFfwdButton.setVisibility(mUseFastForward ? View.VISIBLE : View.GONE);
                }
            }
        }
        if(mRewButton!=null) {
            mRewButton.setVisibility(View.VISIBLE);
            if (mRewButton != null) {
                mRewButton.setOnClickListener(mRewListener);
                if (!mFromXml) {
                    mRewButton.setVisibility(mUseFastForward ? View.VISIBLE : View.GONE);
                }
            }
        }
        if(mNextButton!=null) mNextButton.setVisibility(View.GONE);
        if(mPrevButton!=null) mPrevButton.setVisibility(View.GONE);
        if(mProgress!=null) mProgress.setVisibility(View.VISIBLE);
        if(mCurrentTime!=null) mCurrentTime.setVisibility(View.VISIBLE);
        if(mEndTime!=null) mEndTime.setVisibility(View.VISIBLE);
        if(seekbar_container!=null) seekbar_container.setVisibility(View.VISIBLE);
        if(extraViewInCenter!=null) extraViewInCenter.setVisibility(View.GONE);
        if(mFullscreenButton!=null) mFullscreenButton.setVisibility(View.VISIBLE);
        if(mFullscreenButton_live!=null) mFullscreenButton_live.setVisibility(View.GONE);
    }

    private void initControllerView(View v) {
        buttonsLinearLayout = (LinearLayout) v.findViewById(R.id.buttonsLinearLayout);
        bufferingLinearLayout = (LinearLayout) v.findViewById(R.id.bufferingLinearLayout);

        mPauseButton = (ImageButton) v.findViewById(R.id.pause);
        if (mPauseButton != null) {
            mPauseButton.requestFocus();
            mPauseButton.setOnClickListener(mPauseListener);
        }
        
        mFullscreenButton = (ImageButton) v.findViewById(R.id.fullscreen);
        if (mFullscreenButton != null) {
            mFullscreenButton.requestFocus();
            mFullscreenButton.setOnClickListener(mFullscreenListener);
        }
        mFullscreenButton_live = (ImageButton) v.findViewById(R.id.fullscreen_live);
        if (mFullscreenButton_live != null) {
            mFullscreenButton_live.requestFocus();
            mFullscreenButton_live.setOnClickListener(mFullscreenListener);
        }

        seekbar_container = (LinearLayout) v.findViewById(R.id.seekbar_container);
        extraViewInCenter = (View) v.findViewById(R.id.extraViewInCenter);

        mFfwdButton = (ImageButton) v.findViewById(R.id.ffwd);
        if (mFfwdButton != null) {
            mFfwdButton.setOnClickListener(mFfwdListener);
            if (!mFromXml) {
                mFfwdButton.setVisibility(mUseFastForward ? View.VISIBLE : View.GONE);
            }
        }

        mRewButton = (ImageButton) v.findViewById(R.id.rew);
        if (mRewButton != null) {
            mRewButton.setOnClickListener(mRewListener);
            if (!mFromXml) {
                mRewButton.setVisibility(mUseFastForward ? View.VISIBLE : View.GONE);
            }
        }

        // By default these are hidden. They will be enabled when setPrevNextListeners() is called 
        mNextButton = (ImageButton) v.findViewById(R.id.next);
        if (mNextButton != null && !mFromXml && !mListenersSet) {
            mNextButton.setVisibility(View.GONE);
        }
        mPrevButton = (ImageButton) v.findViewById(R.id.prev);
        if (mPrevButton != null && !mFromXml && !mListenersSet) {
            mPrevButton.setVisibility(View.GONE);
        }

        mProgress = (SeekBar) v.findViewById(R.id.mediacontroller_progress);
        if (mProgress != null) {
            if (mProgress instanceof SeekBar) {
                SeekBar seeker = (SeekBar) mProgress;
                seeker.setOnSeekBarChangeListener(mSeekListener);
                seeker.setOnDragListener(mDragListener);
            }
            mProgress.setMax(1000);
        }



        mEndTime = (TextView) v.findViewById(R.id.time);
        mCurrentTime = (TextView) v.findViewById(R.id.time_current);
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

        mEndTime.setTypeface(tfBold);
        mCurrentTime.setTypeface(tfBold);

        installPrevNextListeners();
    }

    /**
     * Show the controller on screen. It will go away
     * automatically after 3 seconds of inactivity.
     */
    public void show() {
        show(sDefaultTimeout);
    }

    /**
     * Disable pause or seek buttons if the stream cannot be paused or seeked.
     * This requires the control interface to be a MediaPlayerControlExt
     */
    private void disableUnsupportedButtons() {
        if (mPlayer == null) {
            return;
        }
        
        try {
            if (mPauseButton != null && !mPlayer.canPause()) {
                mPauseButton.setEnabled(false);
            }
            if (mRewButton != null && !mPlayer.canSeekBackward()) {
                mRewButton.setEnabled(false);
            }
            if (mFfwdButton != null && !mPlayer.canSeekForward()) {
                mFfwdButton.setEnabled(false);
            }
        } catch (IncompatibleClassChangeError ex) {
            // We were given an old version of the interface, that doesn't have
            // the canPause/canSeekXYZ methods. This is OK, it just means we
            // assume the media can be paused and seeked, and so we don't disable
            // the buttons.
        }
    }
    
    /**
     * Show the controller on screen. It will go away
     * automatically after 'timeout' milliseconds of inactivity.
     * @param timeout The timeout in milliseconds. Use 0 to show
     * the controller until hide() is called.
     */
    public void show(int timeout) {
        if(mPlayer != null) {
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.ON_DISPLAY_CLICK);
            if (!mShowing && mAnchor != null) {
                setProgress();
                if (mPauseButton != null) {
                    mPauseButton.requestFocus();
                }
                disableUnsupportedButtons();

                LayoutParams tlp = new LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        Gravity.BOTTOM
                );

                mAnchor.addView(this, tlp);
                mShowing = true;
            }
            updatePausePlay();
            updateFullScreen();

            // cause the progress bar to be updated even if mShowing
            // was already true.  This happens, for example, if we're
            // paused with the progress bar showing the user hits play.
            mHandler.sendEmptyMessage(SHOW_PROGRESS);

            Message msg = mHandler.obtainMessage(FADE_OUT);
            if (timeout != 0) {
                mHandler.removeMessages(FADE_OUT);
                mHandler.sendMessageDelayed(msg, timeout);
            }

            if (mPlayer.isLiveVideoSource())
                hideButtonsForLive();
            else
                showButtonsForM3U8();
        }
    }
    
    public boolean isShowing() {
        return mShowing;
    }

    /**
     * Remove the controller from the screen.
     */
    public void hide() {
        if (mAnchor == null) {
            return;
        }

        try {
            mAnchor.removeView(this);
            mHandler.removeMessages(SHOW_PROGRESS);
        } catch (IllegalArgumentException ex) {
            Log.w("MediaController", "already removed");
        }
        mShowing = false;
    }

    private String stringForTime(int timeMs) {
        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours   = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    private int setProgress() {
        if (mPlayer == null || mDragging) {
            return 0;
        }
        
        int position = mPlayer.getCurrentPosition();
        int duration = mPlayer.getDuration();
        if (mProgress != null) {
            if (duration > 0) {
                // use long to avoid overflow
                long pos = 1000L * position / duration;
                mProgress.setProgress( (int) pos);
            }
            int percent = mPlayer.getBufferPercentage();
            mProgress.setSecondaryProgress(percent * 10);
        }

        if (mEndTime != null)
            mEndTime.setText(stringForTime(duration));
        if (mCurrentTime != null)
            mCurrentTime.setText(stringForTime(position));

        return position;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        show(sDefaultTimeout);
        return true;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        show(sDefaultTimeout);
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mPlayer == null) {
            return true;
        }
        
        int keyCode = event.getKeyCode();
        final boolean uniqueDown = event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN;
        if (keyCode ==  KeyEvent.KEYCODE_HEADSETHOOK
                || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
                || keyCode == KeyEvent.KEYCODE_SPACE) {
            if (uniqueDown) {
                doPauseResume();
                show(sDefaultTimeout);
                if (mPauseButton != null) {
                    mPauseButton.requestFocus();
                }
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_PLAY) {
            if (uniqueDown && !mPlayer.isPlaying()) {
                mPlayer.start();
                updatePausePlay();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP
                || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
            if (uniqueDown && mPlayer.isPlaying()) {
                mPlayer.pause();
                updatePausePlay();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
                || keyCode == KeyEvent.KEYCODE_VOLUME_UP
                || keyCode == KeyEvent.KEYCODE_VOLUME_MUTE) {
            // don't show the controls for volume adjustment
            return super.dispatchKeyEvent(event);
        } else if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU) {
            if (uniqueDown) {
                hide();
            }
            return true;
        }

        show(sDefaultTimeout);
        return super.dispatchKeyEvent(event);
    }

    private OnClickListener mPauseListener = new OnClickListener() {
        public void onClick(View v) {
            doPauseResume();
            show(sDefaultTimeout);
        }
    };

    private OnClickListener mFullscreenListener = new OnClickListener() {
        public void onClick(View v) {
            doToggleFullscreen();
            show(sDefaultTimeout);
        }
    };

    public void updatePausePlay() {
        if (mRoot == null || mPauseButton == null || mPlayer == null) {
            return;
        }

        if (mPlayer.isPlaying()) {
            mPauseButton.setImageResource(R.drawable.ic_media_pause);
        } else {
            mPauseButton.setImageResource(R.drawable.ic_media_play);
        }
    }

    public void updateFullScreen() {
        if (mRoot == null || mFullscreenButton == null || mPlayer == null) {
            return;
        }
        
        if (mPlayer.isFullScreen()) {
            //mFullscreenButton.setImageResource(R.drawable.ic_media_fullscreen_shrink);
            mFullscreenButton.setImageResource(R.drawable.exit_full_screen_360_small_small_small);
            mFullscreenButton_live.setImageResource(R.drawable.exit_full_screen_360_small_small_small);
        }
        else {
            mFullscreenButton.setImageResource(R.drawable.full_screen_360_small_small_small);
            mFullscreenButton_live.setImageResource(R.drawable.full_screen_360_small_small_small);
        }
    }

    private void doPauseResume() {
        if (mPlayer == null) {
            return;
        }
        
        if (mPlayer.isPlaying()) {
            mPlayer.analyticsPlayerCollectionVideoCollector(AnalyticsConstants.ON_PAUSE, "" + (mPlayer.getCurrentPosition()/1000), "" + (mPlayer.getCurrentPosition()/1000));
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.ON_PAUSE);
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.CATEGORY_PLAYBACK, AnalyticsConstants.EVENT_PAUSE, (mPlayer.getDuration()/1000), (mPlayer.getCurrentPosition()/1000), (mPlayer.getCurrentPosition()/1000), AnalyticsConstants.OLD_STATE_PLAYING );
            mPlayer.setIsPausedManually(true);
            mPlayer.pause();
        } else {
            mPlayer.start();
            mPlayer.setIsPausedManually(false);
            mPlayer.analyticsPlayerCollectionVideoCollector(AnalyticsConstants.ON_RESUME, "" + (mPlayer.getCurrentPosition()/1000), "" + (mPlayer.getCurrentPosition()/1000));
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.ON_RESUME);
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.CATEGORY_PLAYBACK, AnalyticsConstants.EVENT_PLAY, (mPlayer.getDuration()/1000), (mPlayer.getCurrentPosition()/1000), (mPlayer.getCurrentPosition()/1000), AnalyticsConstants.OLD_STATE_PAUSED );
        }
        updatePausePlay();
    }

    private void doToggleFullscreen() {
        if (mPlayer == null) {
            return;
        }
        
        mPlayer.toggleFullScreen();

        if(mPlayer.isFullScreen())
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.CATEGORY_PLAYER_SETUP, AnalyticsConstants.EVENT_RESIZE, (mPlayer.getDuration()/1000), (mPlayer.getCurrentPosition()/1000), (mPlayer.getCurrentPosition()/1000), "fullscreen" );
        else
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.CATEGORY_PLAYER_SETUP, AnalyticsConstants.EVENT_RESIZE, (mPlayer.getDuration()/1000), (mPlayer.getCurrentPosition()/1000), (mPlayer.getCurrentPosition()/1000), "restore" );
        mPlayer.analyticsPlayerCollectionVideoCollector(AnalyticsConstants.ON_FULL_SCREEN, "" + (mPlayer.getCurrentPosition()/1000), "" + (mPlayer.getCurrentPosition()/1000));
        mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.ON_FULL_SCREEN);
    }

    // There are two scenarios that can trigger the seekbar listener to trigger:
    //
    // The first is the user using the touchpad to adjust the posititon of the
    // seekbar's thumb. In this case onStartTrackingTouch is called followed by
    // a number of onProgressChanged notifications, concluded by onStopTrackingTouch.
    // We're setting the field "mDragging" to true for the duration of the dragging
    // session to avoid jumps in the position in case of ongoing playback.
    //
    // The second scenario involves the user operating the scroll ball, in this
    // case there WON'T BE onStartTrackingTouch/onStopTrackingTouch notifications,
    // we will simply apply the updated position without suspending regular updates.
    private OnSeekBarChangeListener mSeekListener = new OnSeekBarChangeListener() {
        public void onStartTrackingTouch(SeekBar bar) {
            show(3600000);

            mDragging = true;

            // By removing these pending progress messages we make sure
            // that a) we won't update the progress while the user adjusts
            // the seekbar and b) once the user is done dragging the thumb
            // we will post one of these messages to the queue again and
            // this ensures that there will be exactly one message queued up.
            mHandler.removeMessages(SHOW_PROGRESS);
        }

        public void onProgressChanged(SeekBar bar, int progress, boolean fromuser) {
            //System.out.println("PROGRESS_SET:-"+progress);
            if (mPlayer == null) {
                return;
            }

            if (!fromuser) {
                // We're not interested in programmatically generated changes to
                // the progress bar's position.
                return;
            }

            //Setting the buffering variable to true, this is done, when the seekbar is touched by the user
            setBufferingFlag(true);
            //System.out.println("PROGRESS_SET1:-"+progress);



            long duration = mPlayer.getDuration();
            long newposition = (duration * progress) / 1000L;

            startPosition = (mPlayer.getCurrentPosition()/1000);
            endPosition = ((int)newposition/1000);

            mPlayer.seekTo( (int) newposition);
            if (mCurrentTime != null)
                mCurrentTime.setText(stringForTime( (int) newposition));


            mPlayer.analyticsPlayerCollectionVideoCollector(AnalyticsConstants.ON_SEEK, "" + (newposition / 1000L), "" + progress);
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.ON_SEEK);
        }

        public void onStopTrackingTouch(SeekBar bar) {
            mDragging = false;
            setProgress();
            updatePausePlay();
            show(sDefaultTimeout);

            // Ensure that progress is properly updated in the future,
            // the call to show() does not guarantee this because it is a
            // no-op if we are already showing.
            mHandler.sendEmptyMessage(SHOW_PROGRESS);

            mPlayer.setSeekedManually(true);
            mPlayer.setSeekedFromAndTo(startPosition, endPosition);
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.CATEGORY_PLAYBACK, AnalyticsConstants.EVENT_SEEK, (mPlayer.getDuration()/1000), startPosition, endPosition, AnalyticsConstants.OLD_STATE_PLAYING );
        }
    };
    private int startPosition;
    private int endPosition;

    private OnDragListener mDragListener = new OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            return false;
        }
    };

    @Override
    public void setEnabled(boolean enabled) {
        if (mPauseButton != null) {
            mPauseButton.setEnabled(enabled);
        }
        if (mFfwdButton != null) {
            mFfwdButton.setEnabled(enabled);
        }
        if (mRewButton != null) {
            mRewButton.setEnabled(enabled);
        }
        if (mNextButton != null) {
            mNextButton.setEnabled(enabled && mNextListener != null);
        }
        if (mPrevButton != null) {
            mPrevButton.setEnabled(enabled && mPrevListener != null);
        }
        if (mProgress != null) {
            mProgress.setEnabled(enabled);
        }
        disableUnsupportedButtons();
        super.setEnabled(enabled);
    }

    private OnClickListener mRewListener = new OnClickListener() {
        public void onClick(View v) {
            if (mPlayer == null) {
                return;
            }

            int beforeSeek = mPlayer.getCurrentPosition();
            int afterSeek = beforeSeek - 15000;

            int pos = mPlayer.getCurrentPosition();
            pos -= 5000; // milliseconds
            mPlayer.seekTo(pos);
            setProgress();

            show(sDefaultTimeout);

            mPlayer.setSeekedManually(true);
            mPlayer.setSeekedFromAndTo(beforeSeek, endPosition);
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.CATEGORY_PLAYBACK, AnalyticsConstants.EVENT_SEEK, (mPlayer.getDuration()/1000), (beforeSeek/1000), (afterSeek/1000), AnalyticsConstants.OLD_STATE_PLAYING );
        }
    };

    private OnClickListener mFfwdListener = new OnClickListener() {
        public void onClick(View v) {
            if (mPlayer == null) {
                return;
            }

            int beforeSeek = mPlayer.getCurrentPosition();
            int afterSeek = beforeSeek + 15000;

            int pos = mPlayer.getCurrentPosition();
            pos += 15000; // milliseconds
            mPlayer.seekTo(pos);
            setProgress();

            show(sDefaultTimeout);

            mPlayer.setSeekedManually(true);
            mPlayer.setSeekedFromAndTo(beforeSeek, afterSeek);
            mPlayer.analyticsPlayerCollectionVideoCollection(AnalyticsConstants.CATEGORY_PLAYBACK, AnalyticsConstants.EVENT_SEEK, (mPlayer.getDuration()/1000), (beforeSeek/1000), (afterSeek/1000), AnalyticsConstants.OLD_STATE_PLAYING );
        }
    };

    private void installPrevNextListeners() {
        if (mNextButton != null) {
            mNextButton.setOnClickListener(mNextListener);
            mNextButton.setEnabled(mNextListener != null);
        }

        if (mPrevButton != null) {
            mPrevButton.setOnClickListener(mPrevListener);
            mPrevButton.setEnabled(mPrevListener != null);
        }
    }

    public void setPrevNextListeners(OnClickListener next, OnClickListener prev) {
        mNextListener = next;
        mPrevListener = prev;
        mListenersSet = true;

        if (mRoot != null) {
            installPrevNextListeners();
            
            if (mNextButton != null && !mFromXml) {
                mNextButton.setVisibility(View.VISIBLE);
            }
            if (mPrevButton != null && !mFromXml) {
                mPrevButton.setVisibility(View.VISIBLE);
            }
        }
    }
    
    public interface MediaPlayerControl {
        void    start();
        void    pause();
        int     getDuration();
        int     getCurrentPosition();
        void    seekTo(int pos);
        boolean isPlaying();
        int     getBufferPercentage();
        boolean canPause();
        boolean canSeekBackward();
        boolean canSeekForward();
        boolean isFullScreen();
        void    toggleFullScreen();

        void setIsPausedManually(boolean value);
        boolean isLiveVideoSource();
        void analyticsPlayerCollectionVideoCollection(String eventName);
        void analyticsPlayerCollectionVideoCollector(String eventName, String start, String end);
        void analyticsPlayerCollectionVideoCollection(String category, String type, int duration, int position, int positionEnd, String oldState );
        void setSeekedManually(boolean flag);
        void setSeekedFromAndTo(int seekedFrom, int seekedTo);
    }
    
    private static class MessageHandler extends Handler {
        private final WeakReference<VideoControllerView> mView; 

        MessageHandler(VideoControllerView view) {
            mView = new WeakReference<VideoControllerView>(view);
        }
        @Override
        public void handleMessage(Message msg) {
            VideoControllerView view = mView.get();
            if (view == null || view.mPlayer == null) {
                return;
            }
            
            int pos;
            switch (msg.what) {
                case FADE_OUT:
                    //Adding another condition to check if the player is paused and
                    // if so then do not hide the controls,
                    if(!view.mPlayer.isPlaying() || view.getBufferingFlag()) {
                        view.findViewById(R.id.buttonsLinearLayout).setVisibility(VISIBLE);
                        view.findViewById(R.id.seekbar_container).setVisibility(VISIBLE);
                        //view.findViewById(R.id.bufferingLinearLayout).setVisibility(VISIBLE);
                    } else {
                        view.hide();
                    }
                    break;
                case SHOW_PROGRESS:
                    pos = view.setProgress();
                    if (!view.mDragging && view.mShowing && view.mPlayer.isPlaying()) {
                        msg = obtainMessage(SHOW_PROGRESS);
                        sendMessageDelayed(msg, 1000 - (pos % 1000));
                    }
                    break;
            }
        }
    }
}