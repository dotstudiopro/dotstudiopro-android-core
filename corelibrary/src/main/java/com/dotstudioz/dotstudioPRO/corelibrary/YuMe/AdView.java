/**
 *  AdView.java 
 *  
 *  Purpose: Display of YuMe Ads.
 *
 *  AdView takes care of
 *  	- creating the layouts required for video ad display,
 *  	- setting the created layouts in the YuMe SDK, and
 *  	- requesting the YuMe SDK to play an ad, if available.
 *
 *  It creates a Relative Layout (rLayout), a Frame Layout (fLayout) and a VideoView (vView). 
 *  The rLayout is used for positioning the video display area in case of non full screen mode.
 *  The fLayout and vView are required for ad display and should be of the same size.
 *  Finally, the vView is added as a child of fLayout and fLayout is added as a child of rLayout.
 *
 *  Once the layout creation is complete, the SetParentView() method of YuMe SDK is invoked,
 *  followed by the invocation of ShowAd() method.
 *
 *  The SDK would then play the prefetched ad, if available, else, return an exception.
 *
 *  NOTE: BOTH fLayout and vView needs to be resized
 *  (RESIZING REQUIRED FOR BOTH ANDROID TABLETS & ANDROID PHONES) whenever orientation of the device changes.
 *  
 *  This view gets closed when the ad play completes, ad play times out (or) 
 *  when device's HOME / BACK keys are pressed.
 *  When the device's HOME / BACK keys are pressed, YuMe SDK's BackKeyPressed() method
 *  is invoked for performing SDK clean-up. 
 *
 *  � 2014 YuMe, Inc. All rights reserved. 
 */

package com.dotstudioz.dotstudioPRO.corelibrary.YuMe;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class AdView extends Activity {
	
	/** Logging TAG used for console logging */
	String TAG = "YuMeAndroidSampleApp";
	
	/** YuMeInterface handle  */
	YuMeInterface yumeInterface;
	
	/** relative Layout that contains the frame layout and video view */
	private RelativeLayout rLayout = null;

	/** frame layout that holds the video view */
	FrameLayout fLayout = null;
	
	/** Delay timer */
	private Timer delayTimer;
	
	/** combined height of status bar and title bar */
	static int STATUS_BAR_AND_TITLE_BAR_HEIGHT = 0;
	
	/** flag to indicate if back key is pressed */
 	private boolean bBackkeyPressed = false;
 	
	/** Display Metrics object */
	DisplayMetrics displayMetrics = new DisplayMetrics(); 	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	/* set the full screen mode */
    	setTheme(android.R.style.Theme_Black_NoTitleBar_Fullscreen);
 
    	super.onCreate(savedInstanceState);
    	
    	/* create the YuMeInterface object */
    	yumeInterface = YuMeInterface.getYuMeInterface();
    	
        /* Create the screen Layout that holds the Frame Layout and VideoView */
        rLayout = new RelativeLayout(this);
        if(rLayout != null) {
	        ViewGroup.LayoutParams rLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
	        if(rLayoutParams != null)
	        	rLayout.setLayoutParams(rLayoutParams);
        }
        
        /* Create the fLayout */
        fLayout = new FrameLayout(this);
        if(fLayout != null) {
	        ViewGroup.LayoutParams fLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
	        if(fLayoutParams != null)
	        	fLayout.setLayoutParams(fLayoutParams);
        }
        
        /* request for title bar icon */
    	requestWindowFeature(Window.FEATURE_LEFT_ICON);
    	setContentView(rLayout);
    	
    	/* create the display view */
    	createDisplayView();
    
    	/* set the adview handle in YuMeInterface */
   		//yumeInterface.setAdView(this);
   		
   		/* this delay timer is started in order to make sure that status bar and title bar
   		height is calculated correctly */
   		startDelayTimer();
    };
    
    @Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
		/* handle the orientation change event */
		handleOrientationChange(newConfig.orientation);
	}
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
    	
    	bBackkeyPressed = true;
    	
    	/* This will get called when Back Key gets pressed */
    	/* call the Back key Pressed API in SDK so that it can do the
    	necessary cleanup, if playing is in progress */
		yumeInterface.backKeyPressed();
		
    	finish();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        
		if(!bBackkeyPressed) {
			/* stop the ad */
			yumeInterface.stopAd();
			
			/* close the activity */
			finish();
		}
    }    
	
	/**
	 * Gets the Application context.
	 * @return The Application context.
	 */	
    public Context getAppContext() {
    	return this.getApplicationContext();
    }	
	
    /**
	 * Gets the Activity context.
	 * @return The current Activity context.
	 */	
	public Context getActivityContext() {
    	return this;
	}
    
	/**
	 * Creates the Display View.
	 */		
	private void createDisplayView() {
		/* remove existing views from layout, if any */
		removeViewsFromLayout();
		
		/* add the views to layout */
		addViewsToLayout();		
	}
	
	/**
	 * Adds views to layout.
	 */
    private void addViewsToLayout() {
    	/* add the fLayout to rLayout */
   		if(rLayout != null)
   			rLayout.addView(fLayout);
    }    	
	
	/**
	 * Removes the views from layout.
	 */
    private void removeViewsFromLayout() {
    	/* remove the fLayout from rLayout if added already */
    	if(rLayout != null)
    		rLayout.removeView(fLayout);
    }	

    /**
	 * Handles the orientation change event.
	 * @param newOrientation The current orientation. 
	 */	 	
	private void handleOrientationChange(int newOrientation) {
		switch(newOrientation) {
			case Configuration.ORIENTATION_UNDEFINED:
				Log.d(TAG, "New Orientation: UNDEFINED");
				break;
			case Configuration.ORIENTATION_PORTRAIT:
				Log.d(TAG, "New Orientation: PORTRAIT");
				resizeAdLayout();
				break;
			case Configuration.ORIENTATION_LANDSCAPE:
				Log.d(TAG, "New Orientation: LANDSCAPE");
				resizeAdLayout();
				break;
			case Configuration.ORIENTATION_SQUARE:
				Log.d(TAG, "New Orientation: SQUARE");
				break;
		}
	}
    
	/**
	 * Gets the combined height of status bar and title bar.
	 * @return The combined height of status bar and title bar.
	 */		
	public int getStatusBarAndTitleBarHeight() {
		return STATUS_BAR_AND_TITLE_BAR_HEIGHT;
	}
    
	/**
	 * Calculates the combined height of status bar and title bar.
	 */		
	public void calculateStatusBarAndTitleBarHeight() {
        Rect rect = new Rect();
        Window window = this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        
        int statusBarHeight = rect.top;
        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        //Log.d(TAG, "contentViewTop: " + contentViewTop);
        int titleBarHeight = 0;
        if(contentViewTop > 0)
        	titleBarHeight = contentViewTop - statusBarHeight;
        
        /* work-around to fix the issue wherein statusBarHeight is always >0 even, if not present */ 
        if(contentViewTop == 0)
        	statusBarHeight = 0;        

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        /* check if status bar resides at the bottom of the device screen like in Motorola Xoom */
        if(rect.bottom < display.getHeight()) {
        	statusBarHeight = display.getHeight() - rect.bottom;
        	STATUS_BAR_AND_TITLE_BAR_HEIGHT = titleBarHeight;
        } else {
        	STATUS_BAR_AND_TITLE_BAR_HEIGHT = statusBarHeight + titleBarHeight;
        }
    	Log.i(TAG, "Status Bar Height: " + statusBarHeight + ", Title Bar Height: " + titleBarHeight);        
        Log.i(TAG, "Status Bar & Title Bar Height: " + STATUS_BAR_AND_TITLE_BAR_HEIGHT);
	}
	
	/**
	 * Creates and starts the Delay timer.
	 */		
	private void startDelayTimer() {
		if(delayTimer == null) {
			/* create and start the Delay timer */
			int timeVal = 50; /* ms */
			delayTimer = new Timer();
			delayTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					onDelayTimerExpired();
				}
			}, timeVal);
		}
	}
	
	/**
	 * Stops the Delay timer.
	 */		
	void stopDelayTimer() {
		if(delayTimer != null) {
			delayTimer.cancel();
			delayTimer = null;
		}
	}
	
	/**
	 * Listener for timer expiry event from Delay timer.
	 */		
	void onDelayTimerExpired() {
		/* stop the Delay timer */
		stopDelayTimer();
		
		/* perform the ad display on UI thread */
		runOnUiThread(displayAdOnUIThread);
	}
	
	/**
	 * Displays the ad on UI thread.
	 */
	private Runnable displayAdOnUIThread = new Runnable() {
		public void run() {
			boolean retVal = false;
			try {
				//Check if an Ad is available 
				retVal = yumeInterface.sdkIsAdAvailable();
				if(!retVal) {
					finish();
					return;
				}
			} catch (Exception e) {
				Log.e(TAG, "Exeption: Ad NOT Available.");
				//e.printStackTrace();
			}
			try {
				/* get the status bar and title bar height */
				calculateStatusBarAndTitleBarHeight();
				
				/* resize the ad layout */
				resizeAdLayout();
		   		
		   		/* display the prefetched ad */
				retVal = yumeInterface.displayAd(fLayout);
				if(!retVal)
					finish();
			} catch (Exception e) {
				Log.e(TAG, "Exception Displaying Ad.");
				//e.printStackTrace();
			}				
		}
	};
	
	/**
	 * Resizes the ad layout.
	 */	
	void resizeAdLayout() {
        /* get the available width and height for ad display */
        Display display = getWindowManager().getDefaultDisplay();
        display.getMetrics(displayMetrics);        
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        
        /* set / modify the rLayout padding to make the frame layout positioned properly, in case of non-full screen parentview for ad display */
        if(rLayout != null)
        	rLayout.setPadding(0, 0, 0, 0);

        /* set / modify the Frame Layout params */
        if(fLayout != null) {
	        ViewGroup.LayoutParams fLayoutParams1 = fLayout.getLayoutParams();
	        if(fLayoutParams1 == null) {
	            fLayoutParams1 = new FrameLayout.LayoutParams(displayWidth, displayHeight - STATUS_BAR_AND_TITLE_BAR_HEIGHT);
	        } else {
	            fLayoutParams1.width = displayWidth;
	            fLayoutParams1.height = displayHeight - STATUS_BAR_AND_TITLE_BAR_HEIGHT;
	        }
	        fLayout.setLayoutParams(fLayoutParams1);
	        Log.d(TAG, "Resizing FLayout: Width: " + fLayout.getLayoutParams().width + ", Height: " + fLayout.getLayoutParams().height);
        }
    }	
}
