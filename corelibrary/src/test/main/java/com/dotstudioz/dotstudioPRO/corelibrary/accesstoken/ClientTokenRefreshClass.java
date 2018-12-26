package com.dotstudioz.dotstudioPRO.services.accesstoken;

import android.content.Context;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.result.Delegation;
import com.dotstudioz.dotstudioPRO.corelibrary.SharedPreferences.SharedPreferencesUtil;
import com.dotstudioz.dotstudioPRO.services.services.LatitudeAndLongitudeService;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mohsin on 07-10-2016.
 */

public class ClientTokenRefreshClass {

    //PRODUCTION_URL
    public static String API_DOMAIN = "http://api.myspotlight.tv";
    //public static String API_DOMAIN = "http://dev.api.myspotlight.tv";
    public static String CLIENT_REFRESH_TOKEN = API_DOMAIN + "/users/token/refresh";

    public String SAMPLE_RESPONSE = "{\n" +
            "  \"success\": true,\n" +
            "  \"client_token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiI1NjkwMTM0ZTk3ZjgxNTQ3MzFhZWVkMmQiLCJleHBpcmVzIjoxNDc1ODYyNDA3NjgxLCJjb250ZXh0Ijp7ImF2YXRhciI6Imh0dHA6Ly9jZG4uZG90c3R1ZGlvcHJvLmNvbS9hdmF0YXJzLzU3MWRlN2YwNmUyYTJhMGY3OGJlOWYyNzU0MmM0ZjU0OTdmODE1N2M0NzdiMjNjNi5wbmciLCJpZCI6IjU0MmM0ZjU0OTdmODE1N2M0NzdiMjNjNiIsImZpcnN0X25hbWUiOiJNb2hzaW4iLCJsYXN0X25hbWUiOiJTaGFpa2gifX0.fCFcZL_DR-chWvVWLkc5u2eehNgK8Ip4jL89hjxyDMM\"\n" +
            "}";

    public String ACTUAL_RESPONSE = "";

    public IClientTokenRefresh iClientTokenRefresh;

    public ClientTokenRefreshClass(Context ctx) {
        if (ctx instanceof ClientTokenRefreshClass.IClientTokenRefresh)
            iClientTokenRefresh = (ClientTokenRefreshClass.IClientTokenRefresh) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement IClientTokenRefresh");
    }

    public void refreshExistingClientToken(String xAccessToken, String xClientToken) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(2, 30000);
        client.setTimeout(30000);
        client.addHeader("x-access-token", xAccessToken);
        client.addHeader("x-client-token", xClientToken);

        /*System.out.println("CLIENT_REFRESH_TOKEN_URL:-"+CLIENT_REFRESH_TOKEN);
        System.out.println("ApplicationConstants.xAccessToken:-"+xAccessToken);
        System.out.println("ApplicationConstants.xClientToken:-"+xClientToken);*/

        try {
            client.post(CLIENT_REFRESH_TOKEN, null, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    System.out.println("CLIENT_REFRESH_TOKEN ONSUCCESS:-"+responseBody.toString());
                    try {
                        String s = new String(responseBody);
                        JSONObject jsonObject = new JSONObject(s);
                        if(jsonObject.has("client_token")) {
                            ACTUAL_RESPONSE = jsonObject.getString("client_token");
                        }
                        iClientTokenRefresh.clientTokenResponse(ACTUAL_RESPONSE);
                    } catch(Exception e) {
                        //e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    try {
                        System.out.println("CLIENT_REFRESH_TOKEN ONFAILURE:-"+responseBody.toString());
                        iClientTokenRefresh.clientTokenError(responseBody.toString());
                    } catch(Exception e) {
                        //e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            iClientTokenRefresh.clientTokenError(e.getMessage());
            //e.printStackTrace();
        }
    }

    public void refreshExistingClientTokenUsingAuth0(String xAccessToken, String xClientToken, String refreshToken) {
        /*System.out.println("CLIENT_REFRESH_TOKEN_URL:-"+CLIENT_REFRESH_TOKEN);
        System.out.println("ApplicationConstants.xAccessToken:-"+xAccessToken);
        System.out.println("ApplicationConstants.xClientToken:-"+xClientToken);*/

        try {
            AuthenticationAPIClient client = new AuthenticationAPIClient(
                    new Auth0("aHO7DngzWo01oiM9FdxMKPKaj5NM8NFQ", "dotstudiopro.auth0.com"));
            client.delegationWithRefreshToken(refreshToken)
                    .start(new BaseCallback<Delegation, AuthenticationException>() {

                        @Override
                        public void onSuccess(Delegation payload) {
                            String idToken = payload.getIdToken(); // New ID Token
                            long expiresIn = payload.getExpiresIn(); // New ID Token Expire Date

                            iClientTokenRefresh.clientTokenResponse(idToken);
                        }

                        @Override
                        public void onFailure(AuthenticationException error) {
                            iClientTokenRefresh.clientTokenError(error.getDescription());
                            error.printStackTrace();
                            System.out.println("refreshTheClientToken:==>onFailure==>" + error.getDescription());
                        }
                    });
        } catch (Exception e) {
            iClientTokenRefresh.clientTokenError(e.getMessage());
            //e.printStackTrace();
        }
    }

    public interface IClientTokenRefresh {
        void clientTokenResponse(String ACTUAL_RESPONSE);
        void clientTokenError(String ERROR);
    }
}
