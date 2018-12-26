package com.dotstudioz.dotstudioPRO.corelibrary.analytics;

import android.util.Log;

import com.dotstudioz.dotstudioPRO.analytics.AnalyticsConstants;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstantURL;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.AbstractHttpEntity;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Admin on 16-01-2016.
 */
public class SavePlayerAnalyticsEventService {

    public JSONObject initializePlayerAnalyticsMainObject(VideoInfoDTO videoInfoDTO, String spotlightCompanyID, String useremailid, String userid, float lon, float lat, boolean isTablet, String manufacturer, String model, String os, String appname, String appversion, String ISO_CODE, String COUNTRY_NAME) {
        JSONObject mainJSONObject = new JSONObject();
        try {
            String date = CommonCoreLibraryUtils.dateFormatForAnalytics();
            mainJSONObject.put(AnalyticsConstants.DATE, date);
            mainJSONObject.put(AnalyticsConstants.FIRST_EVENT, date);
            mainJSONObject.put(AnalyticsConstants.LAST_UPDATE, date);
            if(AnalyticsConstants.SESSION_ID_VALUE.toString().length() > 0)
                mainJSONObject.put(AnalyticsConstants.SESSION_ID, AnalyticsConstants.SESSION_ID_VALUE);

            if(videoInfoDTO == null || videoInfoDTO.getChannelID() == null || videoInfoDTO.getVideoID() == null || videoInfoDTO.getCompanyId() == null)
                return null;

            mainJSONObject.put(AnalyticsConstants.PAGE, getPageObject(videoInfoDTO.getChannelID(), videoInfoDTO.getVideoID(), videoInfoDTO.getCompanyId(), spotlightCompanyID, isTablet, manufacturer, model, os, appname, appversion));
            mainJSONObject.put(AnalyticsConstants.USER, getUserObject(useremailid, userid, lon, lat, ISO_CODE, COUNTRY_NAME));
            mainJSONObject.put(AnalyticsConstants.EVENTS, new JSONArray());
        } catch(JSONException e) { }

        return mainJSONObject;
    }

    public JSONObject getPageObject(String channelid, String videoid, String companyid, String originalcompanyid, boolean isTablet, String manufacturer, String model, String os, String appname, String appversion) {
        JSONObject pageObject = new JSONObject();
        try {
            pageObject.put(AnalyticsConstants.CHANNEL_ID, channelid);
            pageObject.put(AnalyticsConstants.VIDEO, getVideoObject(videoid, originalcompanyid));
            pageObject.put(AnalyticsConstants.COMPANY_ID, companyid);
            pageObject.put(AnalyticsConstants.PAGE_TYPE_PARAMETER_NAME, AnalyticsConstants.PAGE_TYPE);
            pageObject.put(AnalyticsConstants.PLAYER_VERSION_NAME, AnalyticsConstants.PLAYER_VERSION_VALUE);
            pageObject.put(AnalyticsConstants.MOBILE_DEVICE_INFO, getMobileDeviceInfoObject(isTablet, manufacturer, model, os, appname, appversion));
        } catch(JSONException e) {
            //e.printStackTrace();
        }
        return pageObject;
    }
    public JSONObject getMobileDeviceInfoObject(boolean isTablet, String manufacturer, String model, String os, String appname, String appversion) {
        JSONObject mobileDeviceInfoObject = new JSONObject();
        try {
            if(isTablet)
                mobileDeviceInfoObject.put(AnalyticsConstants.MOBILE_DEVICE_TYPE, AnalyticsConstants.TABLET);
            else
                mobileDeviceInfoObject.put(AnalyticsConstants.MOBILE_DEVICE_TYPE, AnalyticsConstants.PHONE);
            mobileDeviceInfoObject.put(AnalyticsConstants.MOBILE_DEVICE_MANUFACTURER, manufacturer);
            mobileDeviceInfoObject.put(AnalyticsConstants.MOBILE_DEVICE_OS_VERSION, os);
            mobileDeviceInfoObject.put(AnalyticsConstants.MOBILE_DEVICE_MODEL, model);
            mobileDeviceInfoObject.put(AnalyticsConstants.MOBILE_DEVICE_APP_NAME, appname);
            mobileDeviceInfoObject.put(AnalyticsConstants.MOBILE_DEVICE_APP_VERSION, appversion);
        } catch(JSONException e) {
            //e.printStackTrace();
        }
        return mobileDeviceInfoObject;
    }
    public JSONObject getVideoObject(String videoid, String companyid) {
        JSONObject videoObject = new JSONObject();
        try {
            videoObject.put(AnalyticsConstants.VIDEO_ID, videoid);
            videoObject.put(AnalyticsConstants.COMPANY_ID, companyid);
        } catch(JSONException e) {
            //e.printStackTrace();
        }
        return videoObject;
    }

    public JSONObject getUserObject(String useremailid, String userid, float lon, float lat, String ISO_CODE, String COUNTRY_NAME) {
        JSONObject userObject = new JSONObject();
        try {
            /*if(useremailid.length() > 0)
                userObject.put(AnalyticsConstants.EMAIL, useremailid);*/
            if(userid.length() > 0)
                userObject.put(AnalyticsConstants.USER_ID, userid);
            userObject.put(AnalyticsConstants.LOCATION, getLocationObject(lon, lat, ISO_CODE, COUNTRY_NAME));
        } catch(JSONException e) {
            //e.printStackTrace();
        }
        return userObject;
    }
    public JSONObject getLocationObject(float lon, float lat, String ISO_CODE, String COUNTRY_NAME) {
        JSONObject locationJSONObject = new JSONObject();
        try {
            locationJSONObject.put(AnalyticsConstants.ISOCODE, ISO_CODE);
            locationJSONObject.put(AnalyticsConstants.COUNTRY, COUNTRY_NAME);
            locationJSONObject.put(AnalyticsConstants.GEOMETRY, getGeometryObject(lon, lat));
        } catch(JSONException e) {
            //e.printStackTrace();
        }
        return locationJSONObject;
    }
    public JSONObject getGeometryObject(float lon, float lat) {
        JSONObject geometryJSONObject = new JSONObject();
        try {
            JSONArray coordinatesJSONArray = new JSONArray();
            coordinatesJSONArray.put(lon);
            coordinatesJSONArray.put(lat);
            geometryJSONObject.put(AnalyticsConstants.GEOMETRY_COORDINATES, coordinatesJSONArray);
        } catch(JSONException e) {
            //e.printStackTrace();
        }
        return geometryJSONObject;
    }

    public JSONObject getEventObject(String category, String type, String message, int duration, int position, int position_end, String oldState) {
        JSONObject eventObject = new JSONObject();
        try {
            eventObject.put(AnalyticsConstants.EVENT_DATE, CommonCoreLibraryUtils.dateFormatForAnalytics());
            eventObject.put(AnalyticsConstants.EVENT_CATEGORY, category);
            eventObject.put(AnalyticsConstants.EVENT_TYPE, type);
            //if(category.equals(AnalyticsConstants.CATEGORY_PLAYBACK)) {
                //if(duration > 0) {
                    eventObject.put(AnalyticsConstants.EVENT_TIME, getTimeObject(duration, position, position_end, type));
                //}
            //}
            if(message.length() > 0)
                eventObject.put(AnalyticsConstants.EVENT_MESSAGE, message);

            if(oldState.length() > 0)
                eventObject.put(AnalyticsConstants.EVENT_INSIDE_EVENT, getEventObject(oldState));
        } catch(JSONException e) {
            //e.printStackTrace();
        }
        return eventObject;
    }
    public JSONObject getTimeObject(int duration, int position, int position_end, String type) {
        JSONObject timeJSONObject = new JSONObject();
        try {
            timeJSONObject.put(AnalyticsConstants.EVENT_TIME_DURATION, duration);
            timeJSONObject.put(AnalyticsConstants.EVENT_TIME_POSITION, position);
            if(type.equals(AnalyticsConstants.EVENT_SEEK))
                timeJSONObject.put(AnalyticsConstants.EVENT_TIME_POSITION_END, position_end);
        } catch(JSONException e) {
            //e.printStackTrace();
        }
        return timeJSONObject;
    }
    public JSONObject getEventObject(String oldstate) {
        JSONObject eventJSONObject = new JSONObject();
        try {
            JSONObject eventOldStateJSONObject = new JSONObject();
            eventOldStateJSONObject.put(AnalyticsConstants.EVENT_INSIDE_EVENT_INSIDE_EVENTS, oldstate);

            eventJSONObject.put(AnalyticsConstants.EVENT_INSIDE_EVENT, eventOldStateJSONObject);
        } catch(JSONException e) {
            //e.printStackTrace();
        }
        return eventJSONObject;
    }





    public JSONObject setObjectToSave(JSONObject mainJSONObject) {
        JSONObject mainObjectToSend = null;
        try {
            mainObjectToSend = new JSONObject(mainJSONObject.toString());
            try {
                if (AnalyticsConstants.SESSION_ID_VALUE.length() > 0) {
                    mainObjectToSend.put(AnalyticsConstants.SESSION_ID, AnalyticsConstants.SESSION_ID_VALUE);
                } else {
                    if(mainObjectToSend.has(AnalyticsConstants.LAST_SENT))
                        mainObjectToSend.remove(AnalyticsConstants.LAST_SENT);
                }
            } catch(JSONException e) {
                //e.printStackTrace();
            }
        } catch(JSONException e) {
            //e.printStackTrace();
            return null;
        }

        return mainObjectToSend;
    }

    public void callToServer(JSONObject mainObjectToSend) {
        Log.d(this.getClass().getName(), "Inside callToServer");

        //System.out.println("CallToServer OUTPUT0:-"+mainObjectToSend.toString());
        try {
            for(int i = 0; i < mainObjectToSend.getJSONArray(AnalyticsConstants.EVENTS).length(); i++) {
                //System.out.println("EVENT==>"+mainObjectToSend.getJSONArray(AnalyticsConstants.EVENTS).get(i));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(AnalyticsConstants.ANALYTICS_URL);
            AbstractHttpEntity entity = new ByteArrayEntity(mainObjectToSend.toString().getBytes("UTF8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(entity);
            HttpResponse response = client.execute(post);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            //System.out.println("CallToServer OUTPUT:-"+json);
            try {
                JSONObject resultJSONObject = new JSONObject(json);
                if(AnalyticsConstants.SESSION_ID_VALUE.length() == 0) {
                    String sessionID = resultJSONObject.getString("session_id");
                    if (sessionID.length() > 0) {
                        AnalyticsConstants.SESSION_ID_VALUE = sessionID;
                    }
                }
            } catch(JSONException e) {
                //e.printStackTrace();
            }
        } catch(UnsupportedEncodingException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }




        //Testing analytics events, should comment this before pushing it to production
        /*try {
            if(mainObjectToSend != null) {
                if(mainObjectToSend.has("events") && mainObjectToSend.getJSONArray("events") != null) {
                    //System.out.println("mainObjectToSend.getJSONArray==>"+mainObjectToSend.getJSONArray("events"));
                    for(int i = 0; i < mainObjectToSend.getJSONArray("events").length(); i++) {
                        saveEventsUsingTestingAPI((JSONObject)mainObjectToSend.getJSONArray("events").get(i));
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }*/
    }

    public void saveEventsUsingTestingAPI(JSONObject eventObject) {
        System.out.println("saveEventsUsingTestingAPI==>"+eventObject.toString());
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("token", ApplicationConstants.xAccessToken);
                //jsonObject.put("os", "amazon_fire");
                jsonObject.put("os", "android");
                jsonObject.put("version", "06_03_2018_15_30");
                jsonObject.put("name", "abs");
                jsonObject.put("event", eventObject.getString("type"));
            } catch(Exception e) {
                e.printStackTrace();
            }

            HttpPost post = new HttpPost(ApplicationConstantURL.getInstance().ANALYTICS_TESTING_URL);
            AbstractHttpEntity entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF8"));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(entity);
            HttpResponse response = client.execute(post);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            try {
                System.out.println("CallToServer OUTPUT:-" + json);
            } catch(Exception e) {
                e.printStackTrace();
            }
            /*try {
                JSONObject resultJSONObject = new JSONObject(json);
                if(AnalyticsConstants.SESSION_ID_VALUE.length() == 0) {
                    String sessionID = resultJSONObject.getString("session_id");
                    if (sessionID.length() > 0) {
                        AnalyticsConstants.SESSION_ID_VALUE = sessionID;
                    }
                }
            } catch(JSONException e) {
                //e.printStackTrace();
            }*/
        } catch(UnsupportedEncodingException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public void callToServer(String eventName, String videoId, String start, String end, String appCodeName) {
        try {
            Map<String, String> jsonParams = new HashMap<String, String>();
            jsonParams.put("event", eventName);
            jsonParams.put("page", appCodeName);
            //jsonParams.put("page", AnalyticsConstants.PAGE_TYPE);
            jsonParams.put("videoId", videoId);
            jsonParams.put("start", start);
            jsonParams.put("end", end);

            AsyncHttpClient client = new AsyncHttpClient();
            client.setMaxRetriesAndTimeout(2, 30000);
            client.setTimeout(30000);
            RequestParams rp = new RequestParams(jsonParams);
            client.post(AnalyticsConstants.SAVE_PLAYER_DATA_API, rp, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    //System.out.println("SAVE_PLAYER_DATA_API data posted successfully");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    //System.out.println("SAVE_PLAYER_DATA_API data posting failed");
                }
            });

        } catch(Exception e) { }
    }

    public JSONObject cloneMainObject(JSONObject exitingJSONObject) {
        JSONObject mainJSONObject = new JSONObject();
        try {
            String date = CommonCoreLibraryUtils.dateFormatForAnalytics();
            if(exitingJSONObject == null)
                return mainJSONObject;
            mainJSONObject.put(AnalyticsConstants.DATE, exitingJSONObject.get(AnalyticsConstants.DATE));
            mainJSONObject.put(AnalyticsConstants.FIRST_EVENT, exitingJSONObject.get(AnalyticsConstants.FIRST_EVENT));
            mainJSONObject.put(AnalyticsConstants.LAST_UPDATE, exitingJSONObject.get(AnalyticsConstants.LAST_UPDATE));
            if(AnalyticsConstants.SESSION_ID_VALUE.toString().length() > 0)
                mainJSONObject.put(AnalyticsConstants.SESSION_ID, AnalyticsConstants.SESSION_ID_VALUE);

            mainJSONObject.put(AnalyticsConstants.PAGE, exitingJSONObject.get(AnalyticsConstants.PAGE));
            mainJSONObject.put(AnalyticsConstants.USER, exitingJSONObject.get(AnalyticsConstants.USER));
            mainJSONObject.put(AnalyticsConstants.EVENTS, exitingJSONObject.get(AnalyticsConstants.EVENTS));
        } catch(JSONException e) { }

        return mainJSONObject;
    }
}
