package com.dotstudioz.dotstudioPRO.services.services;

import android.content.Context;

import com.dotstudioz.dotstudioPRO.services.accesstoken.AccessTokenHandler;
import com.dotstudioz.dotstudioPRO.services.accesstoken.ClientTokenRefreshClass;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mohsin on 08-10-2016.
 */

public class SearchService {

    public ISearchService iSearchService;

    public SearchService(Context ctx) {
        if (ctx instanceof SearchService.ISearchService)
            iSearchService = (SearchService.ISearchService) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement ISearchService");
    }


    public void search(String xAccessToken, String xClientToken, String searchQueryString, String SEARCH_API_URL) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(2, 30000);
        client.setTimeout(30000);
        client.addHeader("x-access-token", xAccessToken);
        client.addHeader("x-client-token", xClientToken);

        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("token", xAccessToken);
        jsonParams.put("x-client-token", xClientToken);
        jsonParams.put("q", searchQueryString);

        RequestParams rp = new RequestParams(jsonParams);

        try {
            client.get(SEARCH_API_URL, rp, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String s = new String(responseBody);
                        iSearchService.searchServiceResponse(s);
                    } catch (Exception e) {
                        iSearchService.searchError(e.getMessage());
                        //e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    if (responseBody != null) {
                        String s = new String(responseBody);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                        } catch (JSONException e) {
                        }

                        boolean isSuccess = true;
                        try {
                            isSuccess = jsonObject.getBoolean("success");
                        } catch (JSONException e) {
                            //throws error, because on success there is no boolean returned, so
                            // we are assuming that it is a success
                            isSuccess = false;
                        }

                        if (!isSuccess) {
                            if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(jsonObject)) {
                                AccessTokenHandler.getInstance().setFlagWhileCalingForToken(AccessTokenHandler.getInstance().fetchTokenCalledInSearchPageString);
                                if(AccessTokenHandler.getInstance().foundAnyError)
                                    iSearchService.accessTokenExpired();
                                else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                                    iSearchService.clientTokenExpired();
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }


    public interface ISearchService {
        void searchServiceResponse(String ACTUAL_RESPONSE);
        void searchError(String ERROR);
        void accessTokenExpired();
        void clientTokenExpired();
    }
}
