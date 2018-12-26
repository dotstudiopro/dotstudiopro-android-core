package com.dotstudioz.dotstudioPRO.services.services;

import android.content.Context;

import com.dotstudioz.dotstudioPRO.services.accesstoken.AccessTokenHandler;
import com.dotstudioz.dotstudioPRO.models.dto.RecommendedItemDTO;
import com.dotstudioz.dotstudioPRO.models.dto.RecommendedItemPairDTO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Admin on 17-01-2016.
 */
public class RecommendationService {
    public RecommendationService.IRecommendationService iRecommendationService;

    public RecommendationService(Context ctx) {
        if (ctx instanceof RecommendationService.IRecommendationService)
            iRecommendationService = (RecommendationService.IRecommendationService) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement IRecommendationService");
    }

    public void getRecommendation(String xAccessToken, String RECOMMENDATION_API, String id, int size, int from) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(2, 30000);
        client.setTimeout(30000);
        client.addHeader("x-access-token", xAccessToken);

        Map<String, String> jsonParams = new HashMap<>();
        //jsonParams.put("q", "5acef45299f8154417181dc4");
        jsonParams.put("q", id);
        jsonParams.put("size", ""+size);
        jsonParams.put("from", ""+from);

        RequestParams rp = new RequestParams(jsonParams);

        //System.out.println(id+"<==id=======RECOMMENDATION_API==>"+RECOMMENDATION_API);

        client.get(RECOMMENDATION_API, rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                processResponse(responseBody);
                //System.out.println("responseBody from RECOMMENDATION API==>"+responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject responseBody) {
                iRecommendationService.recommendationServiceError(error.getMessage());
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
                            iRecommendationService.accessTokenExpired();
                        else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                            iRecommendationService.clientTokenExpired();
                    }
                }
            }
        });
    }

    private ArrayList<RecommendedItemDTO> recommendedItemDTOList;
    private ArrayList<RecommendedItemPairDTO> recommendedItemPairDTOList;
    private void processResponse(JSONObject jsonObject) {
        recommendedItemDTOList = new ArrayList<>();
        recommendedItemPairDTOList = new ArrayList<>();
        try {
            if(jsonObject != null && jsonObject.has("data")) {
                if(jsonObject.getJSONObject("data").has("hits")) {
                    if(jsonObject.getJSONObject("data").getJSONArray("hits").length() > 0) {

                        for(int i = 0; i < jsonObject.getJSONObject("data").getJSONArray("hits").length(); i++) {
                            try {
                                JSONObject recommendedItemJSONObject = (JSONObject) jsonObject.getJSONObject("data").getJSONArray("hits").get(i);
                                RecommendedItemDTO recommendedItemDTO = new RecommendedItemDTO();
                                if(recommendedItemJSONObject.has("_id"))
                                    recommendedItemDTO.setId(recommendedItemJSONObject.getString("_id"));
                                if(recommendedItemJSONObject.has("_type"))
                                    recommendedItemDTO.setType(recommendedItemJSONObject.getString("_type"));
                                if(recommendedItemJSONObject.has("_source")) {
                                    if(recommendedItemJSONObject.getJSONObject("_source").has("thumb")) {
                                        recommendedItemDTO.setThumb(recommendedItemJSONObject.getJSONObject("_source").getString("thumb"));
                                    }
                                    if(recommendedItemJSONObject.getJSONObject("_source").has("poster")) {
                                        recommendedItemDTO.setPoster(recommendedItemJSONObject.getJSONObject("_source").getString("poster"));
                                    }
                                    if(recommendedItemJSONObject.getJSONObject("_source").has("title")) {
                                        recommendedItemDTO.setTitle(recommendedItemJSONObject.getJSONObject("_source").getString("title"));
                                    }
                                }
                                if(recommendedItemDTO.getId() != null && recommendedItemDTO.getId().length() > 0)
                                    recommendedItemDTOList.add(recommendedItemDTO);
                            } catch(Exception e) {}
                        }
                    }
                }
            }
        } catch(Exception e){}

        if(recommendedItemDTOList.size() > 0) {
            recommendedItemPairDTOList = new ArrayList<>();
            for(int i = 0; i < recommendedItemDTOList.size(); i++) {
                RecommendedItemPairDTO recommendedItemPairDTO = new RecommendedItemPairDTO();
                recommendedItemPairDTO.setRecommendedItemDTO1(recommendedItemDTOList.get(i));

                i++;

                if(i < recommendedItemDTOList.size()) {
                    recommendedItemPairDTO.setRecommendedItemDTO2(recommendedItemDTOList.get(i));
                }

                recommendedItemPairDTOList.add(recommendedItemPairDTO);
            }
        }

        iRecommendationService.recommendationServiceResponse(recommendedItemPairDTOList);
    }

    public interface IRecommendationService {
        void recommendationServiceResponse(ArrayList recommendedItemPairDTOList);
        void recommendationServiceError(String error);
        void accessTokenExpired();
        void clientTokenExpired();
    }
}
