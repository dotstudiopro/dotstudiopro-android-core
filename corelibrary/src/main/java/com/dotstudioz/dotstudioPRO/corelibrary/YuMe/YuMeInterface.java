/**
 *  YuMeInterface.java 
 *  
 *  Purpose: Implements the functions defined by YuMe SDK interface 'YuMeAppInterface'.
 *  Also contains some internal functions acts as an internal interface
 *  for other application views to interact with YuMe SDK.
 *  Implemented as a singleton class to avoid multiple instantiations. 
 *  
 *  � 2014 YuMe, Inc. All rights reserved. 
 */

package com.dotstudioz.dotstudioPRO.corelibrary.YuMe;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.yume.android.sdk.YuMeAdBlockType;
import com.yume.android.sdk.YuMeAdEvent;
import com.yume.android.sdk.YuMeAdParams;
import com.yume.android.sdk.YuMeAdStatus;
import com.yume.android.sdk.YuMeAdViewInfo;
import com.yume.android.sdk.YuMeAppInterface;
import com.yume.android.sdk.YuMeException;
import com.yume.android.sdk.YuMeSDKInterface;
import com.yume.android.sdk.YuMeSDKInterfaceImpl;
import com.yume.android.sdk.YuMeSdkUsageMode;
import com.yume.android.sdk.YuMeStorageMode;

/**
 * Implements the functions defined by YuMe SDK interface 'YuMeAppInterface'.
 * Also contains some internal functions acts as an internal interface
 * for other application views to interact with YuMe SDK.
 * Implemented as a singleton class to avoid multiple instantiations. 
 */
public class YuMeInterface implements YuMeAppInterface {
	
	/** Logging TAG used for console logging */
	private String TAG = "YuMeAndroidZISSampleApp";
	
	/** handle to YuMe SDK */
	private static YuMeSDKInterface yumeSDK = null;
	
	/** self instance */
	private static YuMeInterface yumeInterface;

    public boolean isSingleVideoView = false;
    public boolean isSeriesVideoView = false;
	public boolean isMainActivityView = false;

	public YumeInterfaceForMainActivity yumeInterfaceForMainActivity;

	public interface YumeInterfaceForMainActivity {
		boolean isPostRollPlayingFromYUME();
		void startPlayingSeriesVideoFromYUME();
		void hideADLayoutFromYUME();
	}

	/** Display Metrics object */
	private DisplayMetrics displayMetrics = new DisplayMetrics();
	
	/** The config id (or) domain id supplied by YuMe. */
	//private String CONFIG_ID = "1736DAhIYgKh";
	//private String CONFIG_ID = "211EsvNSRHO";
	private String CONFIG_ID = "2295hqhkbRNW";

	//Constructor 
	private YuMeInterface() {
		/* instantiate the YuMe SDK, BSP, Player */
		if(Integer.parseInt(Build.VERSION.SDK) >= 16) {
			Log.i(TAG, "Android API Level >= 16.");
			if(yumeSDK == null) {
				yumeSDK = new YuMeSDKInterfaceImpl();
				Log.i(TAG, "YuMe SDK instantiated...");
			}
		} else {
			Log.i(TAG, "Android API Level < 16. Hence, not instantiating YuMe SDK, BSP and Player.");
		}		
	}
	
	/**
	 * Creates the YuMeInterface instance, if null.
	 * Else returns the existing YuMeInterface handle.
	 * @return The handle to YuMeInterface module.
	 */	
	public static synchronized YuMeInterface getYuMeInterface() {
		if(yumeInterface == null)
			yumeInterface = new YuMeInterface();
		return yumeInterface;
	}
	
	/**
	 * Throws an exception when trying to clone a copy of YuMeInterface.
	 */	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	/**
	 * Performs the clean-up of the YuMeInterface.
	 */		
	public void cleanUp() {
		/* perform the necessary clean-up */
		yumeInterface = null;
	}

	////////////IMPLEMENTATION OF YuMeAppInterface FUNCTIONS - START /////////// 
	/**
	 * Listens for SDK ad events.
	 * @param adBlockType The ad block type notified by the YuMe SDK.
	 * @param adEvent The ad event notified by the YuMe SDK. 
	 * @param adStatus Ad status info that provides more information about a particular adEvent
	 */	
	@Override
	public void YuMeApp_EventListener(YuMeAdBlockType adBlockType, YuMeAdEvent adEvent, YuMeAdStatus adStatus) {
		Log.i(TAG, "YuMeApp_EventListener: YuMeAdEvent: " + adEvent + ", YuMeAdStatus: " + adStatus + "(" + adBlockType + ")");

		switch (adEvent) {
			case INIT_SUCCESS:
				showInfo("INIT_SUCCESS.");
				break;
			case INIT_FAILED:
				showError("INIT_FAILED.");
				break;
			case AD_READY_TO_PLAY:
				showInfo("AD_READY_TO_PLAY.");
				break;
			case AD_NOT_READY:
				if(adStatus == YuMeAdStatus.CACHED_AD_EXPIRED) {
					showInfo("CACHED_AD_EXPIRED.");
				} else {
					showInfo("AD_NOT_READY.");
				}
				break;
			case AD_PLAYING:
				showInfo("AD_PLAYING.");
				break;
			case AD_COMPLETED:
				showInfo("AD_COMPLETED.");
				//adView.onBackPressed();	//this takes you back to the HomeView
				if(isMainActivityView) {
					/*mainActivityViewHomeView.mainActionBar.setVisibility(View.VISIBLE);
					//mainActivityViewHomeView.startPlayingSeriesVideo();
					if(!mainActivityViewHomeView.playingPostRollAd)
						mainActivityViewHomeView.startPlayingSeriesVideoHandler.post(mainActivityViewHomeView.startPlayingSeriesVideoRunnable);
					else {
						mainActivityViewHomeView.playingPostRollAd = false;
						mainActivityViewHomeView.hideADLayoutHandler.post(mainActivityViewHomeView.hideADLayoutRunnable);
						mainActivityViewHomeView.myHandler.post(mainActivityViewHomeView.myRunnable);
					}*/
				}

				showInfo("AD_COMPLETED.");
				//adView.onBackPressed();	//this takes you back to the HomeView
				if(isMainActivityView) {
					if(!yumeInterfaceForMainActivity.isPostRollPlayingFromYUME())
						yumeInterfaceForMainActivity.startPlayingSeriesVideoFromYUME();
					else {
						yumeInterfaceForMainActivity.hideADLayoutFromYUME();
					}
				}

				break;
			case AD_STOPPED:
				showInfo("AD_STOPPED.");
				break;
			default:
				break;
		}
	}
	
	/**
	 * Gets the application context.
	 * Used by the SDK to get the application context from the application.
	 * @return The application context. 
	 */
	@Override
	public Context YuMeApp_GetApplicationContext() {
		return getApplicationContext();
	}
	
	/**
	 * Gets the context of the ad activity.
	 * Used by the SDK to get the ad activity context from the application.
	 * @return The ad activity context.
	 */
	@Override
	public Context YuMeApp_GetActivityContext() {
		return getActivityContext();
	}
	
	/**
	 * Gets the parent view info.
	 * Used by the SDK to get the parent view info from the application.
	 * @return The parent view info object.
	 */
	@Override
	public YuMeAdViewInfo YuMeApp_GetAdViewInfo() {
		Context appContext = getApplicationContext();
		if(appContext != null) {
			Display display = ((WindowManager)appContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			display.getMetrics(displayMetrics);
			
			YuMeAdViewInfo adViewInfo = new YuMeAdViewInfo();
			adViewInfo.width = displayMetrics.widthPixels;
			adViewInfo.height = displayMetrics.heightPixels;
			adViewInfo.left = 0;
			adViewInfo.top = 0;
			adViewInfo.statusBarAndTitleBarHeight = AdView.STATUS_BAR_AND_TITLE_BAR_HEIGHT;
			return adViewInfo;
		}
		return null;
	}
	
	/**
	 * Gets the Updated Qs Params.
	 * Used by the SDK to get the Updated Qs Params from the application. 
	 * If returned null SDK uses existing Qs Params value.
	 * @return The Updated Qs Params.
	 */	
	@Override
	public String YuMeApp_GetUpdatedQSParams() {
		return null;
	}	
	//////////// IMPLEMENTATION OF YuMeAppInterface FUNCTIONS - END ///////////
	
	/**
	 * Gets the ad activity context.
	 * @return The activity context, else null.
	 */
	public Context getActivityContext() {
		/* get the ad activity context */
		if(yumeInterfaceForMainActivity != null) {
			//return adView.getActivityContext();
			return ((Activity) yumeInterfaceForMainActivity);
		}
		return null;
	}
	
	/**
	 * Gets the Application context.
	 * @return The Application context, else null.
	 */	 
	public Context getApplicationContext() {
		/*if(homeView != null) {
			return homeView.getAppContext();
		} else if(adView != null) {
			return adView.getAppContext();
		}*/

        if(isMainActivityView) {
			if(yumeInterfaceForMainActivity != null) {
				return ((Activity)yumeInterfaceForMainActivity);
			}
		}
		return null;
	}	

	/**
	 * Gets the YuMe SDK Version.
	 * @return sdkVersion The sdk version info.
	 */	
	public String getVersion() {
		String sdkVersion = null;
		try {
			sdkVersion = yumeSDK.YuMeSDK_GetVersion();
		} catch (YuMeException e) {
			////e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (Exception e) {
			////e.printStackTrace();
		}
		return sdkVersion;
	};
	
	/**
	 * Initializes YuMe SDK with ad params.
	 * @return true, if Init is success, else false.
	 */	
	public boolean initYuMeSDK() {
		boolean retVal = false;
		try {
			/* set the sdk params. This Ad Params will be used by the SDK for initialization,
			if the configuration data cannot be fetched for some reason. */
			YuMeAdParams adParams1 = new YuMeAdParams();
			
			/* Ad server URL */
			//adParams1.adServerUrl = "http://shadow01.yumenetworks.com/";
			adParams1.adServerUrl = "http://plg1.yumenetworks.com";
		
			/* Publisher domain */
			adParams1.domainId = CONFIG_ID;

			/* Disk quota in MB for storing the prefetched assets. */
			adParams1.storageSize = 10.0f; //10 MB
			
			/* The playlist response timeout value (in seconds).
			Valid value is between 4 and 60 including.
			Default value is 5 (if timeOut < 4 default will be used).
			If timeOut is > 60 exception will be returned */
			adParams1.adTimeout = 8;
		
			/* Timeout value for interruption during ad streaming (in seconds).
			Valid value is between 3 and 60 including.
			Default value is 6 (if value < 3, default will be used).
			If value is > 60 exception will be returned.*/
			adParams1.videoTimeout = 8;
			
			/* ad block type */
			adParams1.eAdBlockType = YuMeAdBlockType.PREROLL;
			
			/* SDK Usage Mode */
			adParams1.eSdkUsageMode = YuMeSdkUsageMode.PREFETCH_MODE;
			
			/* asset storage mode */
			adParams1.eStorageMode = YuMeStorageMode.INTERNAL_STORAGE;
			retVal = yumeSDK.YuMeSDK_Init(adParams1, this);
			//retVal = yumeSDK.YuMeSDK_Init(CONFIG_ID, adParams, this);
		} catch (YuMeException e) {
			//e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return retVal;
	} ;
	
	/**
	 * De-initializes the YuMe SDK.
	 */	
	public void deInitYuMeSDK() {
		try {
			yumeSDK.YuMeSDK_DeInit();
		} catch (YuMeException e) {
			////e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (Exception e) {
			////e.printStackTrace();
		}
		yumeSDK = null;
	}
	
	/**
	 * Checks if an ad is available for play back.
	 * @return true, if Ad is available, else false.
	 */
	public boolean sdkIsAdAvailable() {
		boolean bRetVal = false;
		try {
			bRetVal = yumeSDK.YuMeSDK_IsAdAvailable();
			if(!bRetVal) {
				showError("YuMeSDK_IsAdAvailable(): No Prefetched Ad Present.");
			}
		} catch (YuMeException e) {
			showError(e.getMessage());
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return bRetVal;		
	}
	
	/**
	 * Requests the YuMe SDK to prefetch an ad display.
	 * @return true, if ShowAd is success, else false.
	 */
	public boolean displayAd(FrameLayout fLayout) {
		boolean retVal = false;
		try {
			yumeSDK.YuMeSDK_ShowAd(fLayout);
			retVal = true;
		} catch (YuMeException e) {
			////e.printStackTrace();
			showError(e.getMessage());
		} catch (Exception e){
			////e.printStackTrace();
		}
		return retVal;
	}
	
	/**
	 * Notifies the Back key pressed event to YuMe SDK.
	 */		
	public void backKeyPressed() {
		try {
			yumeSDK.YuMeSDK_BackKeyPressed();
		} catch (YuMeException e) {
			////e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (Exception e) {
			////e.printStackTrace();
		}
	} ;
	
	/**
	 * Stops an Ad.
	 */	
	public void stopAd() {
		try {
			yumeSDK.YuMeSDK_StopAd();
		} catch (YuMeException e) {
			////e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (Exception e) {
			////e.printStackTrace();
		}
	};	
	
	/**
	 * Displays the info message.
	 * @param infoMsg The info message. 
	 */	
	public void showInfo(String infoMsg) {
		Context appContext = getApplicationContext();
		if(appContext != null) {
			Utils.displayShortToastMsg(appContext, infoMsg);
			System.out.println(infoMsg);
		}
		Log.i(TAG, infoMsg);
	}
	
	/**
	 * Displays the error message.
	 * @param errMsg The error message.
	 */	
	public void showError(String errMsg) {
		Context appContext = getApplicationContext();
		if(appContext != null) {
			Utils.displayShortToastMsg(appContext, errMsg);
		}
		Log.e(TAG, errMsg);
	}
}
