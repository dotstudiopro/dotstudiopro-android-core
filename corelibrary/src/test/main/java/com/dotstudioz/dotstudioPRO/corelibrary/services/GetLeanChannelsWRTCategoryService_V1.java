package com.dotstudioz.dotstudioPRO.services.services;

import android.content.Context;
import android.widget.Toast;

import com.dotstudioz.dotstudioPRO.services.accesstoken.AccessTokenHandler;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstantURL;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mohsin on 02-03-2017.
 */

public class GetLeanChannelsWRTCategoryService_V1 {

    public IGetLeanChannelsWRTCategoryService_V1 iGetLeanChannelsWRTCategoryService_V1;

    public GetLeanChannelsWRTCategoryService_V1(Context ctx) {
        if (ctx instanceof GetLeanChannelsWRTCategoryService_V1.IGetLeanChannelsWRTCategoryService_V1)
            iGetLeanChannelsWRTCategoryService_V1 = (GetLeanChannelsWRTCategoryService_V1.IGetLeanChannelsWRTCategoryService_V1) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement IGetLeanChannelsWRTCategoryService_V1");
    }

    public void getLeanChannelDataWRTCategory(String categorySlug) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(2, 30000);
        client.setTimeout(30000);
        client.addHeader("x-access-token", ApplicationConstants.xAccessToken);

        Map<String, String> jsonParams = new HashMap<String, String>();

        jsonParams.put("detail", "lean");

        RequestParams rp = new RequestParams(jsonParams);

        iGetLeanChannelsWRTCategoryService_V1.showProgress("Loading");
        try {
            client.get(ApplicationConstantURL.getInstance().CHANNELS + categorySlug, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {

                    boolean isSuccess = true;
                    try {
                        isSuccess = responseBody.getBoolean("success");
                    } catch (JSONException e) {
                        //throws error, because on success there is no boolean returned, so
                        // we are assuming that it is a success
                        isSuccess = true;
                    }

                    if (isSuccess) {
                        iGetLeanChannelsWRTCategoryService_V1.processLeanChannelWRTCategoryServiceResponse(responseBody);
                    } else {
                        iGetLeanChannelsWRTCategoryService_V1.numberOfCategoriesAlreadyFetched();
                        if (AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                            AccessTokenHandler.getInstance().setFlagWhileCalingForToken(AccessTokenHandler.getInstance().fetchTokenCalledInCategoriesPageString);
                            if (AccessTokenHandler.getInstance().foundAnyError)
                                iGetLeanChannelsWRTCategoryService_V1.accessTokenExpired();
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                    iGetLeanChannelsWRTCategoryService_V1.numberOfCategoriesAlreadyFetched();
                    iGetLeanChannelsWRTCategoryService_V1.requestForFetchingAChannelCompleted();
                    iGetLeanChannelsWRTCategoryService_V1.processLeanChannelWRTCategoryServiceError(error);
                    iGetLeanChannelsWRTCategoryService_V1.hidePDialog();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject responseBody) {
                    iGetLeanChannelsWRTCategoryService_V1.numberOfCategoriesAlreadyFetched();
                    iGetLeanChannelsWRTCategoryService_V1.hidePDialog();
                    if (responseBody != null) {
                        boolean isSuccess = true;
                        try {
                            isSuccess = responseBody.getBoolean("success");
                        } catch (JSONException e) {
                            //throws error, because on success there is no boolean returned, so
                            // we are assuming that it is a success
                            isSuccess = true;
                        }

                        if (!isSuccess) {
                            if (AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                                AccessTokenHandler.getInstance().setFlagWhileCalingForToken(AccessTokenHandler.getInstance().fetchTokenCalledInCategoriesPageString);
                                if (AccessTokenHandler.getInstance().foundAnyError)
                                    iGetLeanChannelsWRTCategoryService_V1.accessTokenExpired();
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            iGetLeanChannelsWRTCategoryService_V1.hidePDialog();
        }
    }


    public interface IGetLeanChannelsWRTCategoryService_V1 {
        void showProgress(String message);
        void hidePDialog();
        void processLeanChannelWRTCategoryServiceResponse(JSONObject response);
        void processLeanChannelWRTCategoryServiceError(String ERROR);
        void accessTokenExpired();

        void numberOfCategoriesAlreadyFetched();
        void requestForFetchingAChannelCompleted();
    }
}
