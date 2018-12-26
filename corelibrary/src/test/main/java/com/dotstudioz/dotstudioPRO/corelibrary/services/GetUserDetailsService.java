package com.dotstudioz.dotstudioPRO.services.services;

import android.content.Context;

import com.dotstudioz.dotstudioPRO.services.accesstoken.AccessTokenHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Admin on 17-01-2016.
 */
public class GetUserDetailsService {

    public IGetUserDetailsService iGetUserDetailsService;

    public GetUserDetailsService(Context ctx) {
        if (ctx instanceof GetUserDetailsService.IGetUserDetailsService)
            iGetUserDetailsService = (GetUserDetailsService.IGetUserDetailsService) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement IGetUserDetailsService");
    }

    public void getUserDetails(String xAccessToken, String xClientToken, String CLIENT_TOKEN_API) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(2, 30000);
        client.setTimeout(30000);
        client.addHeader("x-access-token", xAccessToken);
        client.addHeader("x-client-token", xClientToken);

        client.get(CLIENT_TOKEN_API, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                iGetUserDetailsService.getUserDetailsServiceResponse(responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject responseBody) {
                iGetUserDetailsService.getUserDetailsServiceError(error.getMessage());
                boolean isSuccess = true;
                try {
                    isSuccess = responseBody.getBoolean("success");
                } catch (JSONException e) {
                    //throws error, because on success there is no boolean returned, so
                    // we are assuming that it is a success
                    isSuccess = false;
                }

                if (!isSuccess) {
                    if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                        AccessTokenHandler.getInstance().setFlagWhileCalingForToken(AccessTokenHandler.getInstance().fetchTokenCalledInRentNowPageString);
                        if(AccessTokenHandler.getInstance().foundAnyError)
                            iGetUserDetailsService.accessTokenExpired();
                        else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                            iGetUserDetailsService.clientTokenExpired();
                    }
                }
            }
        });
    }

    public interface IGetUserDetailsService {
        void getUserDetailsServiceResponse(JSONObject jsonObject);
        void getUserDetailsServiceError(String error);
        void accessTokenExpired();
        void clientTokenExpired();
    }
}
