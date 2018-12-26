package com.dotstudioz.dotstudioPRO.corelibrary.dotstudiovideoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class CustomDoubleWidthVideoView extends VideoView {

    private PlayPauseListener mListener;

    public CustomDoubleWidthVideoView(Context context) {
        super(context);
    }

    public CustomDoubleWidthVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDoubleWidthVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setPlayPauseListener(PlayPauseListener listener) {
        mListener = listener;
    }

    @Override
    public void pause() {
        super.pause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    public void start() {
        super.start();
        if (mListener != null) {
            mListener.onPlay();
        }
    }

    public static interface PlayPauseListener {
        void onPlay();
        void onPause();
    }




    private int mForceHeight = 0;
    private int mForceWidth = 0;

    public void setDimensions(int w, int h) {
        this.mForceHeight = h;
        this.mForceWidth = w;

        // not sure whether it is useful or not but safe to do so
        //getHolder().setFixedSize(mForceWidth, mForceHeight);

        //System.out.println(mForceHeight+"-:DIMENSIONS_INSIDE:-"+mForceWidth);

        requestLayout();
        invalidate();     // very important, so that onMeasure will be triggered

    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(mForceWidth, widthMeasureSpec);
        int height = getDefaultSize(mForceHeight, heightMeasureSpec);

        //System.out.println(width+"-:DIMENSIONS_INSIDE_ON_MEASURE1:-"+height);
        //System.out.println(mForceWidth+"-:DIMENSIONS_INSIDE_ON_MEASURE1:-"+mForceHeight);

        //setMeasuredDimension(((width*3)/2), height);
        setMeasuredDimension(width, height);
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(mForceWidth, widthMeasureSpec);
        int height = getDefaultSize(mForceHeight, heightMeasureSpec);

        System.out.println(width+"-:DIMENSIONS_INSIDE_ON_MEASURE1:-"+height);
        System.out.println(mForceWidth+"-:DIMENSIONS_INSIDE_ON_MEASURE1:-"+mForceHeight);

        if (mForceWidth > 0 && mForceHeight > 0) {
            if ( mForceWidth * height  > width * mForceHeight ) {
                height = width * mForceHeight / mForceWidth;
            } else if ( mForceWidth * height  < width * mForceHeight ) {
                width = height * mForceWidth / mForceHeight;
            } else {
            }
        }

        System.out.println(width+"-:DIMENSIONS_INSIDE_ON_MEASURE2:-"+height);
        System.out.println(mForceWidth+"-:DIMENSIONS_INSIDE_ON_MEASURE2:-"+mForceHeight);
        setMeasuredDimension(mForceWidth, mForceHeight);
    }*/

}