package com.dotstudioz.dotstudioPRO.corelibrary.components;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

public class VerticalPageTransformer implements ViewPager.PageTransformer {

    //private static final float MIN_SCALE = 0.75f;
    private static final float MIN_SCALE = 1.0f;

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) {
                view.setAlpha(0);
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left/top page
                view.setAlpha(1);
                ViewCompat.setElevation(view, 1);
                // Counteract the default slide transition
                view.setTranslationX(view.getWidth() * -position);
                view.setTranslationY(0);

                //set Y position to swipe in from top
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else if (position <= 1) { // [0,1]
                view.setAlpha(1);
                ViewCompat.setElevation(view, 2);

                // Counteract the default slide transition
                view.setTranslationX(view.getWidth() * -position);
                view.setTranslationY(position * view.getHeight());

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(1);
                view.setScaleY(1);

            }/* else if (position <= 1) {
                view.setAlpha(1);

                view.setTranslationX(view.getWidth() * -position);

                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);
            }*/ else {
                view.setAlpha(0);
            }
        }
    }