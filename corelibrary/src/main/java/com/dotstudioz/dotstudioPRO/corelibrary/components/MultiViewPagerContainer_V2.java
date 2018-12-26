package com.dotstudioz.dotstudioPRO.corelibrary.components;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dotstudioz.dotstudioPRO.corelibrary.adapters.VideoOrChannelItemPagerAdapter;
import com.dotstudioz.dotstudioPRO.corelibrary.adapters.VideoOrChannelItemPagerAdapter_V1;
import com.dotstudioz.dotstudioPRO.corelibrary.adapters.VideoOrChannelItemPagerWithBorderAdapter;
import com.dotstudioz.dotstudioPRO.corelibrary.adapters.VideoOrChannelItemPagerWithBorderAdapter_V1;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;

/**
 * PagerContainer: A layout that displays a ViewPager with its children that are outside
 * the typical pager bounds.
 */
public class MultiViewPagerContainer_V2 extends FrameLayout implements ViewPager.OnPageChangeListener {

    public ViewPager mPager;
    boolean mNeedsRedraw = false;
    public static boolean isTwoAndHalfPage = false;

    public MultiViewPagerContainer_V2(Context context) {
        super(context);
        init();
    }

    public MultiViewPagerContainer_V2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultiViewPagerContainer_V2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //Disable clipping of children so non-selected pages are visible
        setClipChildren(false);

        //Child clipping doesn't work with hardware acceleration in Android 3.x/4.x
        //You need to set this value here if using hardware acceleration in an
        // application targeted at these releases.
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        try {
            mPager = (ViewPager) getChildAt(0);
            mPager.setOnPageChangeListener(this);
        } catch (Exception e) {
            throw new IllegalStateException("The root child of PagerContainer must be a ViewPager");
        }
    }

    public ViewPager getViewPager() {
        return mPager;
    }

    private Point mCenter = new Point();
    private Point mInitialTouch = new Point();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenter.x = w / 2;
        mCenter.y = h / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //We capture any touches not already handled by the ViewPager
        // to implement scrolling from a touch outside the pager bounds.
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitialTouch.x = (int) ev.getX();
                mInitialTouch.y = (int) ev.getY();
            default:
                ev.offsetLocation(mCenter.x - mInitialTouch.x, mCenter.y - mInitialTouch.y);
                break;
        }

        boolean isNextNextItemClicked = false;
        boolean isNextNextNextItemClicked = false;
        boolean isNextNextNextNextItemClicked = false;
        int screenWidth = mPager.getContext().getResources().getDisplayMetrics().widthPixels;
        if (mInitialTouch.x > ((80 * screenWidth) / 100)) {
            isNextNextItemClicked = true;
        }


        if (mPager.getAdapter() instanceof VideoOrChannelItemPagerAdapter) {
            ((VideoOrChannelItemPagerAdapter) mPager.getAdapter()).isNextNextItemClicked = false;
            ((VideoOrChannelItemPagerAdapter) mPager.getAdapter()).isNextItemClicked = false;
            if (isNextNextItemClicked) {
                ((VideoOrChannelItemPagerAdapter) mPager.getAdapter()).isNextNextItemClicked = true;
            } else {
                ((VideoOrChannelItemPagerAdapter) mPager.getAdapter()).isNextItemClicked = true;
            }
        } else if (mPager.getAdapter() instanceof VideoOrChannelItemPagerAdapter_V1) {
            ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextNextItemClicked = false;
            ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextItemClicked = false;
            ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextNextNextItemClicked = false;
            ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextNextNextNextItemClicked = false;

            isNextNextItemClicked = false;
            isNextNextNextItemClicked = false;

            //this condition works only in case of video thumbnails being in the rail
            if (mInitialTouch.x > ((80 * screenWidth) / 100) && mInitialTouch.x < ((101 * screenWidth) / 100)) {
                isNextNextNextItemClicked = true;
            } else if (mInitialTouch.x > ((40 * screenWidth) / 100) && mInitialTouch.x < ((80 * screenWidth) / 100)) {
                isNextNextItemClicked = true;
            }

            ApplicationConstants.getInstance().hasClickedChannelPosterItem = false;

            //this flag will be turned on whenever there is an click event on the rail item
            ApplicationConstants.getInstance().channelClickIntercepted = true;
            //this condition works only in case of channel posters being in the rail
            if(((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).channelPosterWidth > 0) {

                //we are doing this only for the 3rd poster, so > 60 and < 90
                if (mInitialTouch.x > ((60 * screenWidth) / 100) && mInitialTouch.x < ((90 * screenWidth) / 100)) {
                    ApplicationConstants.getInstance().hasClickedChannelPosterItem = true;
                }

                if (mInitialTouch.x > ((0 * screenWidth) / 100) && mInitialTouch.x < ((30 * screenWidth) / 100)) {
                    Log.d("MultiViewPagerContainer_V2", "if (mInitialTouch.x > ((0 * screenWidth) / 100) && mInitialTouch.x < ((30 * screenWidth) / 100)) {");
                }

                if (mInitialTouch.x > ((30 * screenWidth) / 100) && mInitialTouch.x < ((60 * screenWidth) / 100)) {
                    Log.d("MultiViewPagerContainer_V2", "if (mInitialTouch.x > ((30 * screenWidth) / 100) && mInitialTouch.x < ((60 * screenWidth) / 100)) {");
                }
            }

            //we have to set the flags only in case of video thumbnails
            if(((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).videoInfoDTOArrayList.size() > 0) {
                //this condition works only in case of video thumbnails being in the rail
                if (mInitialTouch.x > ((80 * screenWidth) / 100) && mInitialTouch.x < ((101 * screenWidth) / 100)) {
                    isNextNextNextItemClicked = true;
                } else if (mInitialTouch.x > ((40 * screenWidth) / 100) && mInitialTouch.x < ((80 * screenWidth) / 100)) {
                    isNextNextItemClicked = true;
                }

                if (isNextNextItemClicked) {
                    ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextItemClicked = true;
                } else if(isNextNextNextItemClicked) {
                    ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextNextItemClicked = true;
                }



            } else {
                ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextNextNextNextItemClicked = false;
                ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextNextNextItemClicked = false;
                ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextNextItemClicked = false;
                ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextItemClicked = false;
                if (isNextNextItemClicked) {
                    ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextNextItemClicked = true;
                } else {
                    ((VideoOrChannelItemPagerAdapter_V1) mPager.getAdapter()).isNextItemClicked = true;
                }
            }
        } else if(mPager.getAdapter() instanceof VideoOrChannelItemPagerWithBorderAdapter) {
            ((VideoOrChannelItemPagerWithBorderAdapter) mPager.getAdapter()).isNextNextItemClicked = false;
            ((VideoOrChannelItemPagerWithBorderAdapter) mPager.getAdapter()).isNextItemClicked = false;
            if (isNextNextItemClicked) {
                ((VideoOrChannelItemPagerWithBorderAdapter) mPager.getAdapter()).isNextNextItemClicked = true;
            } else {
                ((VideoOrChannelItemPagerWithBorderAdapter) mPager.getAdapter()).isNextItemClicked = true;
            }
        } else if(mPager.getAdapter() instanceof VideoOrChannelItemPagerWithBorderAdapter_V1) {
            ((VideoOrChannelItemPagerWithBorderAdapter_V1) mPager.getAdapter()).isNextNextItemClicked = false;
            ((VideoOrChannelItemPagerWithBorderAdapter_V1) mPager.getAdapter()).isNextItemClicked = false;
            if (isNextNextItemClicked) {
                ((VideoOrChannelItemPagerWithBorderAdapter_V1) mPager.getAdapter()).isNextNextItemClicked = true;
            } else {
                ((VideoOrChannelItemPagerWithBorderAdapter_V1) mPager.getAdapter()).isNextItemClicked = true;
            }
        }

        return mPager.dispatchTouchEvent(ev);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Force the container to redraw on scrolling.
        //Without this the outer pages render initially and then stay static
        if (mNeedsRedraw) invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        //System.out.println("onPageSelected(int position)==>"+position);
    }

    @Override
    public void onPageScrollStateChanged(int scrollState) {
        //mNeedsRedraw = (scrollState != ViewPager.SCROLL_STATE_IDLE);

        // A small hack to remove the HW layer that the viewpager add to each page when scrolling.
        if (scrollState != ViewPager.SCROLL_STATE_IDLE) {
            final int childCount = mPager.getChildCount();
            for (int i = 0; i < childCount; i++)
                mPager.getChildAt(i).setLayerType(View.LAYER_TYPE_NONE, null);
        }
    }
}