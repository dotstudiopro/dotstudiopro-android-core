package com.dotstudioz.dotstudioPRO.corelibrary.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Admin on 30-06-2015.
 */
public class CustomAnimationClass extends Animation {
    private View view;
    private int actualHeight;
    private int initialHeight;
    public CustomAnimationClass(View view) {
        this.view = view;
        this.setDuration(500);
    }
    public void setActualHeight(int actualHeight) {
        this.actualHeight = actualHeight;
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight;
        newHeight = (int)(initialHeight * interpolatedTime);
        view.getLayoutParams().height = newHeight;
        view.requestLayout();
    }
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        initialHeight = actualHeight;
    }
    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
