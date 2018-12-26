package com.dotstudioz.dotstudioPRO.dsplayer.player;

import android.content.Context;
import android.graphics.Matrix;
import android.opengl.GLES20;
import android.util.AttributeSet;
import android.view.TextureView;
import android.widget.FrameLayout;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class VideoTextureView extends TextureView {

    private static final float MAX_ASPECT_RATIO_DEFORMATION_PERCENT = 0.01f;

    private float videoAspectRatio;

    public VideoTextureView(Context context) {
        super(context);
    }

    public VideoTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Set the aspect ratio that this {@link VideoTextureView} should satisfy.
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
        System.out.println("screenWidth==>"+screenWidth);
        System.out.println("screenHeight==>"+screenHeight);
        System.out.println("mVideoWidth==>"+mVideoWidth);
        System.out.println("mVideoHeight==>"+mVideoHeight);
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

            System.out.println("scaleX==>"+scaleX);
            System.out.println("scaleY==>"+scaleY);
            System.out.println("screenWidth==>"+screenWidth);
            System.out.println("screenHeight==>"+screenHeight);

            return true;
        } catch(Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

}