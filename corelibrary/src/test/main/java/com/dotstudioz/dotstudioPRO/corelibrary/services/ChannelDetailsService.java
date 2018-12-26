package com.dotstudioz.dotstudioPRO.services.services;

import android.content.Context;
import android.widget.Toast;

import com.dotstudioz.dotstudioPRO.services.accesstoken.AccessTokenHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mohsin on 09-10-2016.
 */

public class ChannelDetailsService {

    public IChannelDetailsService iChannelDetailsService;

    public ChannelDetailsService(Context ctx) {
        if (ctx instanceof ChannelDetailsService.IChannelDetailsService)
            iChannelDetailsService = (ChannelDetailsService.IChannelDetailsService) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement IChannelDetailsService");
    }

    public void getChannelDetails(String xAccessToken, String URL, String categorySlug, String channelSlug) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(2, 30000);
        client.setTimeout(30000);
        client.addHeader("x-access-token", xAccessToken);

        try {
            client.get(URL + categorySlug + "/" + channelSlug, null, new JsonHttpResponseHandler() {
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
                    try {
                        if (isSuccess)
                            iChannelDetailsService.channelDetailsServiceResponse(((JSONObject) responseBody.getJSONArray("channels").get(0)));
                        else {
                            if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                                AccessTokenHandler.getInstance().setFlagWhileCalingForToken(AccessTokenHandler.getInstance().fetchTokenCalledInChannelsPageString);
                                if(AccessTokenHandler.getInstance().foundAnyError)
                                    iChannelDetailsService.accessTokenExpired();
                                else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                                    iChannelDetailsService.clientTokenExpired();
                            }
                        }
                    } catch (JSONException e) {
                        //e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject responseBody) {
                    if (responseBody != null) {
                        boolean isSuccess = true;
                        try {
                            isSuccess = responseBody.getBoolean("success");
                        } catch (JSONException e) {
                            //throws error, because on success there is no boolean returned, so
                            // we are assuming that it is a success
                            isSuccess = true;
                        }
                        try {
                            if (!isSuccess) {
                                if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                                    AccessTokenHandler.getInstance().setFlagWhileCalingForToken(AccessTokenHandler.getInstance().fetchTokenCalledInChannelsPageString);
                                    if(AccessTokenHandler.getInstance().foundAnyError)
                                        iChannelDetailsService.accessTokenExpired();
                                    else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                                        iChannelDetailsService.clientTokenExpired();
                                }
                            }
                        } catch (Exception e) {
                            //e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            iChannelDetailsService.channelDetailsServiceError(e.getMessage());
        }
    }

    public interface IChannelDetailsService {
        void channelDetailsServiceResponse(JSONObject jsonObject);
        void channelDetailsServiceError(String error);
        void accessTokenExpired();
        void clientTokenExpired();
    }
}
