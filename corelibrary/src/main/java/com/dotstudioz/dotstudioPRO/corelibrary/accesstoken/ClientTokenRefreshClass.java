package com.dotstudioz.dotstudioPRO.corelibrary.accesstoken;

import android.content.Context;
import android.widget.Toast;


import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.result.Delegation;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstantURL;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.services.services.retrofit.RestClientInterface;
import com.dotstudioz.dotstudioPRO.services.services.retrofit.RestClientManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohsin on 07-10-2016.
 */

public class ClientTokenRefreshClass {

    //PRODUCTION_URL
    public static String API_DOMAIN = "http://api.myspotlight.tv";
    //public static String API_DOMAIN = "http://dev.api.myspotlight.tv";
   // public static String CLIENT_REFRESH_TOKEN = API_DOMAIN + "/users/token/refresh";

    public String SAMPLE_RESPONSE = "{\n" +
            "  \"success\": true,\n" +
            "  \"client_token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiI1NjkwMTM0ZTk3ZjgxNTQ3MzFhZWVkMmQiLCJleHBpcmVzIjoxNDc1ODYyNDA3NjgxLCJjb250ZXh0Ijp7ImF2YXRhciI6Imh0dHA6Ly9jZG4uZG90c3R1ZGlvcHJvLmNvbS9hdmF0YXJzLzU3MWRlN2YwNmUyYTJhMGY3OGJlOWYyNzU0MmM0ZjU0OTdmODE1N2M0NzdiMjNjNi5wbmciLCJpZCI6IjU0MmM0ZjU0OTdmODE1N2M0NzdiMjNjNiIsImZpcnN0X25hbWUiOiJNb2hzaW4iLCJsYXN0X25hbWUiOiJTaGFpa2gifX0.fCFcZL_DR-chWvVWLkc5u2eehNgK8Ip4jL89hjxyDMM\"\n" +
            "}";

    public String ACTUAL_RESPONSE = "";

    public IClientTokenRefresh iClientTokenRefresh;

    public ClientTokenRefreshClass(Context ctx) {
        if (ctx instanceof IClientTokenRefresh)
            iClientTokenRefresh = (IClientTokenRefresh) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement IClientTokenRefresh");
    }


    private void handleError(Response<Object> response) {

        try {
            JSONObject responseBody = new JSONObject("" + response.errorBody());

            if (responseBody != null) {

                iClientTokenRefresh.clientTokenError(responseBody.toString());
            } else {
                if (responseBody.has("message")) {
                    iClientTokenRefresh.clientTokenError(responseBody.getString("message"));
                }
            }
        }catch(Exception e)
        {
            iClientTokenRefresh.clientTokenError(e.getMessage());
        }


    }
    public void refreshExistingClientToken(String xAccessToken, String xClientToken) {

        try {
            RestClientInterface restClientInterface = RestClientManager.getClient(ApplicationConstantURL.getInstance().API_DOMAIN_S, xAccessToken, xClientToken, null).create(RestClientInterface.class);
            Call<Object> call1 = restClientInterface.requestPost(ApplicationConstantURL.getInstance().CLIENT_REFRESH_TOKEN, new JsonObject());
            call1.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    try {
                        if (response != null && !response.isSuccessful() && response.errorBody() != null) {
                            handleError(response);
                            return;
                        }
                        if (response != null && response.isSuccessful() && response.body() != null) {
                            JSONObject responseBody = new JSONObject("" + (new Gson().toJson(response.body())));
                            System.out.println("CLIENT_REFRESH_TOKEN ONSUCCESS:-"+responseBody.toString());
                            try {
                                String s = new String(responseBody.toString());
                                JSONObject jsonObject = new JSONObject(s);
                                if(jsonObject.has("client_token")) {
                                    ACTUAL_RESPONSE = jsonObject.getString("client_token");
                                }
                                iClientTokenRefresh.clientTokenResponse(ACTUAL_RESPONSE);
                            } catch(Exception e) {
                                //e.printStackTrace();
                            }

                        } else {
                            iClientTokenRefresh.clientTokenError("CLIENT_REFRESH_TOKEN FAILED");
                        }
                    } catch (Exception e) {
                        iClientTokenRefresh.clientTokenError(e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    call.cancel();
                    iClientTokenRefresh.clientTokenError(t.getMessage());
                }
            });

        } catch (Exception e) {
            iClientTokenRefresh.clientTokenError(e.getMessage());
        }



    }

    public void refreshExistingClientTokenUsingAuth0(String xAccessToken, String xClientToken, String refreshToken) {
        /*System.out.println("CLIENT_REFRESH_TOKEN_URL:-"+CLIENT_REFRESH_TOKEN);
        System.out.println("ApplicationConstants.xAccessToken:-"+xAccessToken);
        System.out.println("ApplicationConstants.xClientToken:-"+xClientToken);*/

        try {
            AuthenticationAPIClient client = new AuthenticationAPIClient(
                    new Auth0(ApplicationConstants.COMPANY_KEY, "dotstudiopro.auth0.com"));
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
        }
    }

    public interface IClientTokenRefresh {
        void clientTokenResponse(String ACTUAL_RESPONSE);
        void clientTokenError(String ERROR);
    }
}
