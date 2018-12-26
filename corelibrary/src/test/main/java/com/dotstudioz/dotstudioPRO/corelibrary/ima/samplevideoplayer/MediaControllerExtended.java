package com.dotstudioz.dotstudioPRO.dsplayer.ima.samplevideoplayer;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;


/**
 * Created by Admin on 12-03-2015.
 */
public class MediaControllerExtended extends MediaController {
    Context mContext;

    public MediaControllerExtended(Context context) {
        super(context);
        mContext = context;
        iMediaControllerExtended = (IMediaControllerExtended) context;
    }

    public MediaControllerExtended(Context context, boolean useFastForward) {
        super (context, useFastForward);
        mContext = context;
        iMediaControllerExtended = (IMediaControllerExtended) context;
    }

    public MediaControllerExtended(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        iMediaControllerExtended = (IMediaControllerExtended) context;
    }

    private boolean _isShowing = false;
    public boolean isPausedManually = false;
    public boolean isAdPlaying = false;

    @Override
    public boolean isShowing() { return _isShowing; }

    @Override
    public void show() {
        if(!isAdPlaying && !_isShowing) {
            iMediaControllerExtended.hackForMediaControllerShow();
            super.show();
            _isShowing = true;

            ViewGroup parent = (ViewGroup) this.getParent();
            parent.setVisibility(View.VISIBLE);


            iMediaControllerExtended.showingMediaController();
            try {
                iMediaControllerExtended.mediaControllerHeightCalculated(this.getMeasuredHeight());
            } catch(Exception e) {
                e.printStackTrace();
                iMediaControllerExtended.mediaControllerHeightCalculated(150);
            }
        } else {
            hide();
        }
    }

    @Override
    public void hide() {
        if(!isPausedManually) {
            iMediaControllerExtended.hackForMediaControllerHide();
            super.hide();
            _isShowing = false;

            ViewGroup parent = (ViewGroup) this.getParent();
            parent.setVisibility(View.GONE);


            iMediaControllerExtended.hidingMediaController();
        }
    }

    public void showBusyCursor() {
        if(iMediaControllerExtended != null) {
            iMediaControllerExtended.showBusyCursor();
        }
    }

    public void hideBusyCursor() {
        if(iMediaControllerExtended != null) {
            iMediaControllerExtended.hideBusyCursor();
        }
    }

    public void hideWithoutValidation() {
        super.hide();
        _isShowing = false;
    }

    /**
     * This method is called for hiding the closed caption text
     * So we are hiding the active CC icon and showing the non-active CC icon
     */
    public void hideCC() {
        //closedCaptionTextViewVisible.performClick();
        closedCaptionTextView.setVisibility(View.VISIBLE);
        closedCaptionTextViewVisible.setVisibility(View.GONE);
    }


    public interface IMediaControllerExtended {
        boolean isFullScreen();
        void doToggleFullScreen();

        void showingMediaController();
        void hidingMediaController();

        void hackForMediaControllerShow();
        void hackForMediaControllerHide();

        boolean isCCShowing();
        void doToggleCCShowing(boolean flag);





        void onPlayCalled();
        void onPauseCalled();
        void onCompletedCalled();
        void onErrorCalled();
        void onSeekCalled(int start, int end);
        void onCCCalled();

        void mediaControllerHeightCalculated(int heightOfMediaController);
        void showBusyCursor();
        void hideBusyCursor();
    }
    IMediaControllerExtended iMediaControllerExtended;

    public void onPlayCalled() {
        iMediaControllerExtended.onPlayCalled();
    }
    public void onPauseCalled() {
        iMediaControllerExtended.onPauseCalled();
    }
    public void onErrorCalled() {
        iMediaControllerExtended.onErrorCalled();
    }
    public void onCompletedCalled() {
        iMediaControllerExtended.onCompletedCalled();
    }
    public void onSeekCalled(int start, int end) {
        iMediaControllerExtended.onSeekCalled(start, end);
    }
    public void onCCCalled() {
        iMediaControllerExtended.onCCCalled();
    }


    private static final int sDefaultTimeout = 3000;
    public ImageView mFullscreenButton;
    public ImageView closedCaptionTextView;
    public ImageView closedCaptionTextViewVisible;

    private OnClickListener mFullscreenListener = new OnClickListener() {
        public void onClick(View v) {
            if(mContext instanceof IMediaControllerExtended) {
                hideWithoutValidation();

                if (iMediaControllerExtended.isFullScreen()) {
                    mFullscreenButton.setImageResource(R.drawable.full_screen_360_small_small_small);
                }
                else {
                    mFullscreenButton.setImageResource(R.drawable.exit_full_screen_360_small_small_small);
                }

                iMediaControllerExtended.doToggleFullScreen();
            }
        }
    };

    public void setmFullscreenButton(boolean flag) {
        if (flag) {
            mFullscreenButton.setImageResource(R.drawable.full_screen_360_small_small_small);
        } else {
            mFullscreenButton.setImageResource(R.drawable.exit_full_screen_360_small_small_small);
        }
    }

    private OnClickListener closedCaptionTextViewListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mContext instanceof IMediaControllerExtended) {
                onCCCalled();
                iMediaControllerExtended.doToggleCCShowing(true);

                closedCaptionTextView.setVisibility(View.GONE);
                closedCaptionTextViewVisible.setVisibility(View.VISIBLE);
            }
        }
    };
    private OnClickListener closedCaptionTextViewListenerVisible = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mContext instanceof IMediaControllerExtended) {
                iMediaControllerExtended.onCCCalled();
                iMediaControllerExtended.doToggleCCShowing(false);

                closedCaptionTextView.setVisibility(View.VISIBLE);
                closedCaptionTextViewVisible.setVisibility(View.GONE);
            }
        }
    };

    public void setCCShowing(boolean flag) {
        if (!flag) {
            closedCaptionTextView.setVisibility(View.GONE);
            closedCaptionTextViewVisible.setVisibility(View.GONE);
        } else {
            closedCaptionTextView.setVisibility(View.GONE);
            closedCaptionTextViewVisible.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setAnchorView(View view) {
        super.setAnchorView(view);

        try {
            LinearLayout viewGroupLevel1 = (LinearLayout)  this.getChildAt(0);
            LinearLayout viewGroupLevel2 = (LinearLayout) viewGroupLevel1.getChildAt(0);
            View v = viewGroupLevel2.getChildAt(2);
            v.getPaddingTop();
            v.getLayoutParams();
        } catch(Exception e) {
            e.printStackTrace();
        }

        int marginTopCalculateValue = 0;
        try {
            //marginTopCalculateValue = view.getHeight()/4;
            if(ApplicationConstants.TEXT_INPUT_HEIGHT > 0)
                marginTopCalculateValue = ApplicationConstants.TEXT_INPUT_HEIGHT/2;
            else if(ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR > 0)
                marginTopCalculateValue = ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR/3;
            else
                marginTopCalculateValue = 50;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setBackgroundColor(Color.parseColor("#0Dff0000"));

        mFullscreenButton = new ImageView(getContext());
        mFullscreenButton.setImageResource(R.drawable.full_screen_360_small_small_small);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT;
        params.setMargins(0, marginTopCalculateValue, 10, 0);
        addView(mFullscreenButton, params);
        mFullscreenButton.setOnClickListener(mFullscreenListener);


        closedCaptionTextView = new ImageView(getContext());
        int heightOrWidth = 0;
        if(ApplicationConstants.TEXT_INPUT_HEIGHT > 0)
            heightOrWidth = ApplicationConstants.TEXT_INPUT_HEIGHT/2;
        else if(ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR > 0)
            heightOrWidth = ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR/3;
        LayoutParams ccparams = new LayoutParams((heightOrWidth+(heightOrWidth/2)), heightOrWidth);
        ccparams.gravity = Gravity.LEFT;
        ccparams.setMargins(10, marginTopCalculateValue, 0, 0);
        addView(closedCaptionTextView, ccparams);
        closedCaptionTextView.setOnClickListener(closedCaptionTextViewListener);
        closedCaptionTextView.setVisibility(View.GONE);
        closedCaptionTextView.setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_cc).color(Color.parseColor("#eeeeee")));

        closedCaptionTextViewVisible = new ImageView(getContext());
        LayoutParams ccparamsVisible = new LayoutParams((heightOrWidth+(heightOrWidth/2)), heightOrWidth);
        ccparamsVisible.gravity = Gravity.LEFT;
        ccparamsVisible.setMargins(10, marginTopCalculateValue, 0, 0);
        addView(closedCaptionTextViewVisible, ccparamsVisible);
        closedCaptionTextViewVisible.setOnClickListener(closedCaptionTextViewListenerVisible);
        closedCaptionTextViewVisible.setVisibility(View.GONE);
        closedCaptionTextViewVisible.setImageDrawable(new IconDrawable(getContext(), FontAwesomeIcons.fa_cc).color(Color.parseColor("#6dec68")));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if(this.isShowing())
                    this.hide();
                else
                    super.onKeyDown(keyCode, event);
                break;

            default:
                return super.onKeyDown(keyCode, event);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if(event.getAction() == KeyEvent.KEYCODE_BACK) {
            if(this.isShowing()) {
                this.hide();
                return true;
            } else {
                super.onInterceptTouchEvent(event);
            }
        } else {
            super.onInterceptTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if(isPausedManually)
                    this.hideWithoutValidation();
                else
                    super.dispatchKeyEvent(event);
                break;

            default:
                return super.dispatchKeyEvent(event);
        }
        return false;
    }
}
