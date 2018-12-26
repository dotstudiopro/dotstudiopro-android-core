/**
 * Utils.java
 * 
 * Purpose: Utility functions for the application.
 *
 * � 2014 YuMe, Inc. All rights reserved.
 */

package com.dotstudioz.dotstudioPRO.corelibrary.YuMe;

import android.content.Context;
import android.widget.Toast;

/**
 * Utility functions for the application.
 */
public class Utils {
	
	/** toast for displaying messages for shorter duration */
	private static Toast shortToast = null;
	
	/** toast for displaying messages for longer duration */
	private static Toast longToast = null;	
	
	/**
	 * Displays the short toast message.
	 * @param context The app context.
	 * @param toastMsgShort The short toast message to be displayed.
	 */
	public static void displayShortToastMsg(Context context, String toastMsgShort) {
		if(context == null)
			return;
		if(shortToast == null)
			shortToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
		shortToast.setText(toastMsgShort);
		shortToast.show();
	};	
	
	/**
	 * Displays the long toast message.
	 * @param context The app context.
	 * @param toastMsgLong The long toast message to be displayed.
	 */
	public static void displayLongToastMsg(Context context, String toastMsgLong) {
		if(context == null)
			return;
		if(longToast == null)
			longToast = Toast.makeText(context, "", Toast.LENGTH_LONG);		
		longToast.setText(toastMsgLong);
		longToast.show();
	};
	
	/**
	 * Removes the toast messages on app exit.
	 */		
	public static void removeToastMsg() {
		if(shortToast != null)
			shortToast.cancel();
		if(longToast != null)
			longToast.cancel();
	};
}
