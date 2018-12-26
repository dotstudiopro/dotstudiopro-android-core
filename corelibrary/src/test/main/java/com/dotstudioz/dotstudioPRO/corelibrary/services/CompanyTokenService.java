package com.dotstudioz.dotstudioPRO.services.services;

import android.content.Context;
import com.dotstudioz.dotstudioPRO.models.dto.TokenResponseDTO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Admin on 23-02-2016.
 */
public class CompanyTokenService {

    public CompanyTokenService.ICompanyTokenService iCompanyTokenService;

    public CompanyTokenService(Context ctx) {
        if (ctx instanceof CompanyTokenService.ICompanyTokenService)
            iCompanyTokenService = (CompanyTokenService.ICompanyTokenService) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement ICompanyTokenService");
    }

    public void requestForToken(String companyKey, String TOKEN_URL) {

        final TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();

        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(2, 30000);
        client.setTimeout(30000);
        //client.addHeader("x-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiI1NjkwMTM0ZTk3ZjgxNTQ3MzFhZWVkMmQiLCJleHBpcmVzIjoxNDU2NzE2ODY3NzE1LCJjb250ZXh0Ijp7Im5hbWUiOiJzdWJkb21haW4iLCJzdWJkb21haW4iOiJzdWJkb21haW4ifX0.hFOTWpwiwEx7qq1dKujVi1JuI9VjcbCyTo0GMjQtqhE");

        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("key", companyKey);
        RequestParams rp = new RequestParams(jsonParams);

        try {
            client.post(TOKEN_URL, rp, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    iCompanyTokenService.companyTokenServiceResponse(responseBody);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    iCompanyTokenService.companyTokenServiceError(responseBody);
                }
            });
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public interface ICompanyTokenService {
        void companyTokenServiceResponse(byte[] responseBody);
        void companyTokenServiceError(byte[] responseBody);
    }
}
