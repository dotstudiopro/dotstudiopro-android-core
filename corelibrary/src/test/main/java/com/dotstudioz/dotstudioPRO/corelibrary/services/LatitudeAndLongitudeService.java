package com.dotstudioz.dotstudioPRO.services.services;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mohsin on 08-10-2016.
 */

public class LatitudeAndLongitudeService {

    public LatitudeAndLongitudeInterface latitudeAndLongitudeInterface;

    public LatitudeAndLongitudeService(Context ctx) {
        if (ctx instanceof LatitudeAndLongitudeInterface)
            latitudeAndLongitudeInterface = (LatitudeAndLongitudeInterface) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement LatitudeAndLongitudeInterface");
    }

    public void getLatitudeAndLongitude(String xAccessToken, String URL) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(2, 30000);
        client.setTimeout(30000);
        client.addHeader("x-access-token", xAccessToken);

        try {
            if (xAccessToken != null && xAccessToken.length() > 0) {
                client.post(URL, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String s = new String(responseBody);
                            latitudeAndLongitudeInterface.latitudeAndLongitudeResponse(s);
                        } catch (Exception e) {
                            latitudeAndLongitudeInterface.latitudeAndLongitudeError(e.getMessage());
                            //e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        if (responseBody != null) {
                            String s = new String(responseBody);
                            latitudeAndLongitudeInterface.latitudeAndLongitudeError(s);
                        }
                    }
                });
            }
        } catch (Exception e) {
            latitudeAndLongitudeInterface.latitudeAndLongitudeError(e.getMessage());
            //e.printStackTrace();
        }
    }



    public interface LatitudeAndLongitudeInterface {
        void latitudeAndLongitudeResponse(String ACTUAL_RESPONSE);
        void latitudeAndLongitudeError(String ERROR);
    }
}
