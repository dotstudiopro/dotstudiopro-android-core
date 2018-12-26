package com.dotstudioz.dotstudioPRO.services.services;

import android.content.Context;
import android.widget.Toast;

import com.dotstudioz.dotstudioPRO.services.accesstoken.AccessTokenHandler;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstantURL;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTO;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightChannelDTO;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by mohsin on 02-03-2017.
 */

public class FetchMissingChildChannelService_V1 {

    public FetchMissingChildChannelService_V1.IFetchMissingChildChannelService_V1 iFetchMissingChildChannelService_V1;
    private ArrayList<SpotLightCategoriesDTO> spotLightCategoriesDTOList;
    private String selectedParentCategorySlug;
    private String selectedParentChannelSlug;
    private boolean isSeasonClicked;
    private int seasonToLoad;

    public FetchMissingChildChannelService_V1(Context ctx) {
        if (ctx instanceof FetchMissingChildChannelService_V1.IFetchMissingChildChannelService_V1)
            iFetchMissingChildChannelService_V1 = (FetchMissingChildChannelService_V1.IFetchMissingChildChannelService_V1) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement IFetchMissingChildChannelService_V1");
    }

    public void fetchMissingChildChannelData(String selectedParentCategorySlug, String selectedParentChannelSlug, String channelSlug, ArrayList<SpotLightCategoriesDTO> spotLightCategoriesDTOArrayList, boolean isSeasonClicked, int seasonToLoad) {
        this.selectedParentCategorySlug = selectedParentCategorySlug;
        this.selectedParentChannelSlug = selectedParentChannelSlug;
        this.spotLightCategoriesDTOList = spotLightCategoriesDTOArrayList;
        this.isSeasonClicked = isSeasonClicked;
        this.seasonToLoad = seasonToLoad;

        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(2, 30000);
        client.setTimeout(30000);
        client.addHeader("x-access-token", ApplicationConstants.xAccessToken);

        iFetchMissingChildChannelService_V1.showProgress("Loading");
        try {
            client.get(ApplicationConstantURL.getInstance().CHANNEL + channelSlug, null, new JsonHttpResponseHandler() {
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
                        fetchMissingChildChannelData(responseBody);
                    } else {
                        if (AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                            AccessTokenHandler.getInstance().setFlagWhileCalingForToken(AccessTokenHandler.getInstance().fetchTokenCalledInChannelPageString);
                            if (AccessTokenHandler.getInstance().foundAnyError)
                                iFetchMissingChildChannelService_V1.accessTokenExpired();
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject responseBody) {
                    iFetchMissingChildChannelService_V1.hidePDialog();
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
                                AccessTokenHandler.getInstance().setFlagWhileCalingForToken(AccessTokenHandler.getInstance().fetchTokenCalledInChannelPageString);
                                if (AccessTokenHandler.getInstance().foundAnyError)
                                    iFetchMissingChildChannelService_V1.accessTokenExpired();
                            }
                        }
                    }
                }
            });
        } catch (Exception e) {
            iFetchMissingChildChannelService_V1.hidePDialog();
        }
    }
    JSONArray channelsArray;
    ArrayList<VideoInfoDTO> missingVideoInfoDTOList;
    String selectedChannelID;
    private ArrayList episodesArrayList;
    private void fetchMissingChildChannelData(JSONObject response) {
        JSONObject obj = response;

        try {
            channelsArray = new JSONArray();
            channelsArray = obj.getJSONArray("channels");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(channelsArray.length() == 0) {
            iFetchMissingChildChannelService_V1.processMissingChildChannelDataServiceError("Data not available at the moment!");
            iFetchMissingChildChannelService_V1.hidePDialog();
            return;
        }

        for (int i = 0; i < channelsArray.length(); i++) {
            try {
                JSONObject channel = channelsArray.getJSONObject(i);
                JSONObject spotLightCategoriesDTOOBJ = new JSONObject();
                SpotLightCategoriesDTO spotLightCategoriesDTO = new SpotLightCategoriesDTO();
                SpotLightChannelDTO spotLightChannelDTO = new SpotLightChannelDTO();
                spotLightChannelDTO.setId(channel.getString("_id"));
                selectedChannelID = spotLightChannelDTO.getId();

                spotLightChannelDTO.setTitle(channel.getString("title"));
                try {
                    try {
                        if(channel.has("poster")) {
                            String imageString = channel.getString("poster");
                            spotLightChannelDTO.setPoster(imageString);
                        }
                    } catch (JSONException e) {
                        spotLightChannelDTO.setPoster("");
                    }

                    String imageString = "";
                    try {
                        imageString = channel.getString("image");
                    } catch (JSONException e) {
                        imageString = channel.getString("poster");
                    }

                    imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);
                    spotLightChannelDTO.setImage(imageString);
                } catch (JSONException e) {
                    spotLightChannelDTO.setImage("");
                }
                try {
                    String imageString = channel.getString("spotlight_poster");
                    imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);
                    spotLightChannelDTO.setSpotlightImage(imageString);
                } catch (JSONException e) {
                    try {
                        String imageString = channel.getString("videos_thumb");
                        imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);
                        spotLightChannelDTO.setSpotlightImage(imageString);
                    } catch (JSONException ee) {
                        spotLightChannelDTO.setSpotlightImage("");
                    }
                    //e.printStackTrace();
                }

                try {
                    spotLightChannelDTO.setLink(channel.getString("link"));
                } catch (JSONException e) {
                    spotLightChannelDTO.setLink(channel.getString("channel_url"));
                }
                spotLightChannelDTO.setSlug(channel.getString("slug"));

                try {
                    spotLightChannelDTO.setChannelLogo(channel.getString("channel_logo"));
                } catch (JSONException e) { /*e.printStackTrace();*/ }
                try {
                    spotLightChannelDTO.setYear(channel.getString("year"));
                } catch (JSONException e) { /*e.printStackTrace();*/ }
                try {
                    spotLightChannelDTO.setLanguage(channel.getString("language"));
                } catch (JSONException e) { /*e.printStackTrace();*/ }
                try {
                    spotLightChannelDTO.setChannelRating(channel.getString("rating"));
                } catch (JSONException e) { /*e.printStackTrace();*/ }
                try {
                    spotLightChannelDTO.setCompany(channel.getString("company").toUpperCase());
                } catch (JSONException e) { /*e.printStackTrace();*/ }
                try {
                    spotLightChannelDTO.setSpotlight_company_id(channel.getString("spotlight_company_id"));
                } catch (JSONException e) { /*e.printStackTrace();*/ }
                try {
                    spotLightChannelDTO.setCountry(channel.getString("country"));
                } catch (JSONException e) { /*e.printStackTrace();*/ }
                try {
                    spotLightChannelDTO.setChannelDescription(channel.getString("description"));
                } catch (JSONException e) { /*e.printStackTrace();*/ }




                boolean isVideo = false;
                boolean isPlaylist = false;

                try {
                    spotLightChannelDTO.setVideo(channel.getString("video_id"));
                    isVideo = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                    iFetchMissingChildChannelService_V1.hidePDialog();
                }
                try {
                    if(channel.has("playlist")) {
                        if(channel.getJSONArray("playlist").length() > 0) {
                            isVideo = false;
                        } else {
                            if (!isVideo) {
                                try {
                                    spotLightChannelDTO.setVideo(channel.getJSONObject("video").getString("_id"));
                                    isVideo = true;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        if (!isVideo) {
                            try {
                                spotLightChannelDTO.setVideo(channel.getJSONObject("video").getString("_id"));
                                isVideo = true;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch(Exception e){}

                if (!isVideo) {
                    try {
                        episodesArrayList = new ArrayList<>();
                        JSONArray playlistArray = channel.getJSONArray("playlist");
                        for (int j = 0; j < playlistArray.length(); j++) {
                            try {
                                if (playlistArray.getJSONObject(j).getString("_id").length() > 0)
                                    spotLightChannelDTO.getPlaylist().add(playlistArray.getJSONObject(j).getString("_id"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                VideoInfoDTO videoInfoDTO = new VideoInfoDTO();
                                videoInfoDTO.setVideoID(playlistArray.getJSONObject(j).getString("_id"));
                                try { videoInfoDTO.setVideoTitle(playlistArray.getJSONObject(j).getString("title")); } catch(Exception e) {}
                                try { videoInfoDTO.setSeriesTitle(playlistArray.getJSONObject(j).getString("seriestitle")); } catch(Exception e) {}
                                try { videoInfoDTO.setDescription(playlistArray.getJSONObject(j).getString("description")); } catch(Exception e) {}
                                try { videoInfoDTO.setThumb(playlistArray.getJSONObject(j).getString("thumb")); } catch(Exception e) {}
                                try { videoInfoDTO.setSlug(playlistArray.getJSONObject(j).getString("slug")); } catch(Exception e) {}

                                try { videoInfoDTO.setVideoYear(playlistArray.getJSONObject(j).getString("year")); } catch(JSONException e) { videoInfoDTO.setVideoYear("-"); }
                                try { videoInfoDTO.setVideoLanguage(playlistArray.getJSONObject(j).getString("language")); } catch(JSONException e) { videoInfoDTO.setVideoLanguage("-"); }
                                try { videoInfoDTO.setCountry(playlistArray.getJSONObject(j).getString("country")); } catch(JSONException e) { videoInfoDTO.setCountry("-"); }

                                try {
                                    String duraString = playlistArray.getJSONObject(j).getString("duration");
                                    float floatVideoDuration = Float.parseFloat(duraString);
                                    int videoDurationInt = (int) floatVideoDuration;
                                    videoInfoDTO.setVideoDuration(videoDurationInt);
                                } catch(Exception e) { videoInfoDTO.setVideoDuration(0); }
                                if(videoInfoDTO.getVideoPausedPoint() == 0) {
                                    try {
                                        int duraInt = playlistArray.getJSONObject(j).getInt("duration");
                                        videoInfoDTO.setVideoDuration(duraInt);
                                    } catch(Exception e) { videoInfoDTO.setVideoDuration(0); }
                                }
                                if(videoInfoDTO.getVideoPausedPoint() == 0) {
                                    try {
                                        float floatVideoDuration = (float)(playlistArray.getJSONObject(j).getDouble("duration"));
                                        int videoDurationInt = (int) floatVideoDuration;
                                        videoInfoDTO.setVideoDuration(videoDurationInt);
                                    } catch(Exception e) { videoInfoDTO.setVideoDuration(0); }
                                }

                                spotLightChannelDTO.getVideoInfoDTOList().add(videoInfoDTO);
                                episodesArrayList.add(videoInfoDTO);
                            } catch(Exception e) {
                                e.printStackTrace();
                            }

                            for(int x = 0; x < spotLightCategoriesDTOList.size(); x++) {
                                if(spotLightCategoriesDTOList.get(x).getCategorySlug().equals(selectedParentCategorySlug)) {
                                    for (int y = 0; y < spotLightCategoriesDTOList.get(x).getSpotLightChannelDTOList().size(); y++) {
                                        if(spotLightCategoriesDTOList.get(x).getSpotLightChannelDTOList().get(y).getSlug().equals(selectedParentChannelSlug)) {
                                            //spotLightCategoriesDTOList.get(x).getSpotLightChannelDTOList().get(y).getSeasonsList().clear();
                                            for(int z = 0; z < spotLightCategoriesDTOList.get(x).getSpotLightChannelDTOList().get(y).getSeasonsList().size(); z++) {
                                                if(spotLightCategoriesDTOList.get(x).getSpotLightChannelDTOList().get(y).getSeasonsList().get(z).getSlug().equals(spotLightChannelDTO.getSlug()))
                                                    spotLightCategoriesDTOList.get(x).getSpotLightChannelDTOList().get(y).getSeasonsList().set(z, spotLightChannelDTO);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                SpotLightChannelDTO spotLightChannelDTO1 = null;
                SpotLightCategoriesDTO spotLightCategoriesDTO1 = null;
                for(int x = 0; x < spotLightCategoriesDTOList.size(); x++) {
                    if(spotLightCategoriesDTOList.get(x).getCategorySlug().equals(selectedParentCategorySlug)) {
                        spotLightCategoriesDTO1 = spotLightCategoriesDTOList.get(x);
                        for (int y = 0; y < spotLightCategoriesDTOList.get(x).getSpotLightChannelDTOList().size(); y++) {
                            if(spotLightCategoriesDTOList.get(x).getSpotLightChannelDTOList().get(y).getSlug().equals(selectedParentChannelSlug)) {
                                spotLightChannelDTO1 = spotLightCategoriesDTOList.get(x).getSpotLightChannelDTOList().get(y);
                            }
                        }
                    }
                }

                if(isSeasonClicked) {
                    isSeasonClicked = false;
                    iFetchMissingChildChannelService_V1.populateEpisodesListWithNewData((ArrayList)spotLightChannelDTO1.getSeasonsList().get(seasonToLoad-1).getVideoInfoDTOList());
                    iFetchMissingChildChannelService_V1.hidePDialog();
                    return;
                } else {
                    iFetchMissingChildChannelService_V1.postProcessingMissingChildChannelDataServiceResponse(selectedChannelID, spotLightChannelDTO1, spotLightCategoriesDTO1);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface IFetchMissingChildChannelService_V1 {
        void showProgress(String message);
        void hidePDialog();
        void postProcessingMissingChildChannelDataServiceResponse(String selectedChannelID, SpotLightChannelDTO spotLightChannelDTO, SpotLightCategoriesDTO spotLightCategoriesDTO);
        void populateEpisodesListWithNewData(ArrayList<VideoInfoDTO> videoInfoDtosList);
        void processMissingChildChannelDataServiceError(String ERROR);
        void accessTokenExpired();
    }
}
