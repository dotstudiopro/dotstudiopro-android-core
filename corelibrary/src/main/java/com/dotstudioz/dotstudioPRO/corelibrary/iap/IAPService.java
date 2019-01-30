package com.dotstudioz.dotstudioPRO.corelibrary.iap;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.ActionMenuView;

import com.dotstudioz.dotstudioPRO.corelibrary.iap.util.IabBroadcastReceiver;
import com.dotstudioz.dotstudioPRO.corelibrary.iap.util.IabHelper;
import com.dotstudioz.dotstudioPRO.corelibrary.iap.util.IabResult;
import com.dotstudioz.dotstudioPRO.corelibrary.iap.util.Inventory;
import com.dotstudioz.dotstudioPRO.corelibrary.iap.util.Purchase;

public class IAPService implements IabBroadcastReceiver.IabBroadcastListener {

    private static IAPService ourInstance = new IAPService();

    public Context ctx;
    public static IAPService getInstance() {
        return ourInstance;
    }

    private IAPService() {

    }

    // Does the user have an active subscription?
    public boolean mSubscribedToRevry = false;

    // Will the subscription auto-renew?
    public boolean mAutoRenewEnabled = false;

    // Tracks the currently owned SKU, and the options in the Manage dialog
    public String mCurrentlyOwnedSku = "";
    public String mFirstChoiceSku = "";
    public String mSecondChoiceSku = "";

    // Used to select between on a monthly or yearly basis
    public String mSelectedSubscriptionPeriod = "";

    public static String SKU_MANAGED_PRODUCT = "test_managed_product_1";
    // SKU for our subscription
    public static String SKU_MONTHLY;
    public static String SKU_YEARLY;
    public static String SKU_ANDROID_TEST_PURCHASE_GOOD = "android.test.purchased";
    // (arbitrary) request code for the purchase flow
    public static final int RC_REQUEST = 10001;

    private static final String TAG = "InAppBilling";
    // The helper object
    public IabHelper mHelper;
    // Provides purchase notification while this app is running
    public IabBroadcastReceiver mBroadcastReceiver;

    /* base64EncodedPublicKey should be YOUR APPLICATION'S PUBLIC KEY
     * (that you got from the Google Play developer console). This is not your
     * developer public key, it's the *app-specific* public key.
     *
     * Instead of just storing the entire literal string here embedded in the
     * program,  construct the key at runtime from pieces or
     * use bit manipulation (for example, XOR with some other string) to hide
     * the actual key.  The key itself is not secret information, but we don't
     * want to make it easy for an attacker to replace the public key with one
     * of their own and then fake messages from the server.
     */
    public static String base64EncodedPublicKey;

    public void initialize(Context context) {
        ctx = context;
        // Create the helper, passing it our context and the public key to verify signatures with
        Log.d(TAG, "Creating IAB helper.");
        mHelper = new IabHelper(ctx, base64EncodedPublicKey);

        // enable debug logging (for a production application, you should set this to false).
        mHelper.enableDebugLogging(true);

        // Start setup. This is asynchronous and the specified listener
        // will be called once setup completes.
        Log.d(TAG, "Starting setup.");
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                Log.d(TAG, "Setup finished.");

                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    complain("Problem setting up in-app billing: " + result);
                    return;
                }

                // Have we been disposed of in the meantime? If so, quit.
                if (mHelper == null) return;

                // Important: Dynamically register for broadcast messages about updated purchases.
                // We register the receiver here instead of as a <receiver> in the Manifest
                // because we always call getPurchases() at startup, so therefore we can ignore
                // any broadcasts sent while the app isn't running.
                // Note: registering this listener in an Activity is a bad idea, but is done here
                // because this is a SAMPLE. Regardless, the receiver must be registered after
                // IabHelper is setup, but before first call to getPurchases().
                mBroadcastReceiver = new IabBroadcastReceiver(IAPService.this);
                IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                ctx.registerReceiver(mBroadcastReceiver, broadcastFilter);

                // IAB is fully set up. Now, let's get an inventory of stuff we own.
                Log.d(TAG, "Setup successful. Querying inventory.");
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    complain("Error querying inventory. Another async operation in progress.");
                }
            }
        });
    }

    // Listener that's called when we finish querying the items and subscriptions we own
    public IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            Log.d(TAG, "Query inventory finished.");

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
            /*    try {
                    if (inventory.hasPurchase(SKU_ANDROID_TEST_PURCHASE_GOOD)) {
                        mHelper.consumeAsync(inventory.getPurchase(SKU_ANDROID_TEST_PURCHASE_GOOD), null);
                    }
                }catch (IabAsyncInProgressException e) {
                    complain("Error launching purchase flow. Another async operation in progress.");
                    // setWaitScreen(false);
                }*/
                complain("Failed to query inventory: " + result);
                return;
            }

            Log.d(TAG, "Query inventory was successful.");

            /*
             * Check for items we own. Notice that for each purchase, we check
             * the developer payload to see if it's correct! See
             * verifyDeveloperPayload().
             */

//            // Do we have the premium upgrade?
//            Purchase premiumPurchase = inventory.getPurchase(SKU_PREMIUM);
//            mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
//            Log.d(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));

            // First find out which subscription is auto renewing
            Purchase revryMonthly = inventory.getPurchase(SKU_MONTHLY);
            Purchase revryYearly = inventory.getPurchase(SKU_YEARLY);
            if (revryMonthly != null && revryMonthly.isAutoRenewing()) {
                mCurrentlyOwnedSku = SKU_MONTHLY;
                mAutoRenewEnabled = true;
            } else if (revryYearly != null && revryYearly.isAutoRenewing()) {
                mCurrentlyOwnedSku = SKU_YEARLY;
                mAutoRenewEnabled = true;
            } else {
                mCurrentlyOwnedSku = "";
                mAutoRenewEnabled = false;
            }

            // The user is subscribed if either subscription exists, even if neither is auto
            // renewing
            mSubscribedToRevry = (revryMonthly != null && verifyDeveloperPayload(revryMonthly))
                    || (revryYearly != null && verifyDeveloperPayload(revryYearly));
            Log.d(TAG, "User " + (mSubscribedToRevry ? "HAS" : "DOES NOT HAVE")
                    + "subscription.");

            // Check for gas delivery -- if we own gas, we should fill up the tank immediately
            Purchase gasPurchase = inventory.getPurchase(SKU_MANAGED_PRODUCT);
            if (gasPurchase != null && verifyDeveloperPayload(gasPurchase)) {
                Log.d(TAG, "We have gas. Consuming it.");
                try {
                    mHelper.consumeAsync(inventory.getPurchase(SKU_MANAGED_PRODUCT), mConsumeFinishedListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    complain("Error consuming gas. Another async operation in progress.");
                }
                return;
            }
        }



    };

    // Called when consumption is complete
    public IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            Log.d(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            // We know this is the "gas" sku because it's the only one we consume,
            // so we don't check which sku was consumed. If you have more than one
            // sku, you probably should check...
            if (result.isSuccess()) {
                // successfully consumed, so we apply the effects of the item in our
                // game world's logic, which in our case means filling the gas tank a bit
                Log.d(TAG, "Consumption successful. Provisioning.");

            }
            else {
                complain("Error while consuming: " + result);
            }

            Log.d(TAG, "End consumption flow.");
        }
    };

    /** Verifies the developer payload of a purchase. */
    public boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();

        /*
         * TODO: verify that the developer payload of the purchase is correct. It will be
         * the same one that you sent when initiating the purchase.
         *
         * WARNING: Locally generating a random string when starting a purchase and
         * verifying it here might seem like a good approach, but this will fail in the
         * case where the user purchases an item on one device and then uses your app on
         * a different device, because on the other device you will not have access to the
         * random string you originally generated.
         *
         * So a good developer payload has these characteristics:
         *
         * 1. If two different users purchase an item, the payload is different between them,
         *    so that one user's purchase can't be replayed to another user.
         *
         * 2. The payload must be such that you can verify it even when the app wasn't the
         *    one who initiated the purchase flow (so that items purchased by the user on
         *    one device work on other devices owned by the user).
         *
         * Using your own server to store and verify developer payloads across app
         * installations is recommended.
         */

        return true;
    }

    public void complain(String message) {
        Log.e(TAG, "**** Error: " + message);
        alert("Error: " + message);
    }

    public void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(ctx);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        Log.d(TAG, "Showing alert dialog: " + message);
        bld.create().show();
    }

    @Override
    public void receivedBroadcast() {

        // Received a broadcast notification that the inventory of items has changed
        Log.d(TAG, "Received broadcast notification. Querying inventory.");
        try {
            mHelper.queryInventoryAsync(mGotInventoryListener);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error querying inventory. Another async operation in progress.");
        }
    }

}
