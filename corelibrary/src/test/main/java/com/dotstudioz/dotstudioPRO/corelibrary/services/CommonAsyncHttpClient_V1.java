package com.dotstudioz.dotstudioPRO.services.services;

import com.dotstudioz.dotstudioPRO.services.accesstoken.AccessTokenHandler;
import com.dotstudioz.dotstudioPRO.models.dto.ParameterItem;
import com.dotstudioz.dotstudioPRO.models.dto.ParameterItemArray;
import com.dotstudioz.dotstudioPRO.models.dto.ParameterItemJSONArray;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.params.CoreProtocolPNames;

/**
 * Created by mohsin on 03-03-2017.
 */

public class CommonAsyncHttpClient_V1 {

    public static ICommonAsyncHttpClient_V1 iCommonAsyncHttpClient_V1;

    private static CommonAsyncHttpClient_V1 ourInstance = new CommonAsyncHttpClient_V1();

    public static CommonAsyncHttpClient_V1 getInstance(Object obj) {
        if (obj instanceof CommonAsyncHttpClient_V1.ICommonAsyncHttpClient_V1)
            iCommonAsyncHttpClient_V1 = (CommonAsyncHttpClient_V1.ICommonAsyncHttpClient_V1) obj;
        else
            throw new RuntimeException(obj.toString()+ " must implement ICommonAsyncHttpClient_V1");

        return ourInstance;
    }

    public CommonAsyncHttpClient_V1() {

    }

    public interface ICommonAsyncHttpClient_V1 {
        void onResultHandler(JSONObject response);
        void onErrorHandler(String ERROR);
        void accessTokenExpired();
        void clientTokenExpired();
    }

    /**
     * All GET requests will be made using this method
     * @param headersArrayList        - list of parameters to be included in header
     * @param requestParamsArrayList  - list of parameters to be included in body
     * @param API_URL                 - the URL of the API
     * @param API_CALLED_FOR          - if the token is expired, then this variable can be used to
     *                                  set the failed call API in some variable
     */
    public void getAsyncHttpClient(ArrayList<ParameterItem> headersArrayList, ArrayList<ParameterItem> requestParamsArrayList, String API_URL, final String API_CALLED_FOR) {

        //Asynchronous Http client for making the GET request
        //Using com.loopj.android.http library, which internally uses the DefaultHttpClient
        AsyncHttpClient client = new AsyncHttpClient();

        try {
            String userAgent = System.getProperty("http.agent");
            client.setUserAgent(userAgent);
            System.out.println("userAgentuserAgentuserAgent==>"+userAgent);
            client.addHeader(CoreProtocolPNames.USER_AGENT, userAgent);
            client.addHeader("user-agent", userAgent);
        } catch(Exception e) {
            e.printStackTrace();
        }
        //Number of tries to make before throwing timeout error
        client.setMaxRetriesAndTimeout(2, 30000);

        //Time to wait before timing out
        client.setTimeout(30000);
        client.setResponseTimeout(30000);

        //Check for the length of the headersArrayList, if it has any data in it,
        //the start populating the header parameters
        if(headersArrayList != null && headersArrayList.size() > 0) {
            for (int i = 0; i < headersArrayList.size(); i++) {
                client.addHeader(headersArrayList.get(i).getParameterName(), headersArrayList.get(i).getParameterValue());
            }
        }

        //Check for the length of the requestParamsArrayList, if it has any data in it,
        //the start populating the body parameters
        Map<String, String> requestParamsMap = null;
        if(requestParamsArrayList != null && requestParamsArrayList.size() > 0) {
            requestParamsMap = new HashMap<String, String>();
            for (int i = 0; i < requestParamsArrayList.size(); i++) {
                requestParamsMap.put(requestParamsArrayList.get(i).getParameterName(), requestParamsArrayList.get(i).getParameterValue());
            }
        }

        //typecasting the body parameters to RequestParams & assigning the params, if found
        RequestParams rp = null;
        if(requestParamsMap != null && requestParamsMap.size() > 0) {
            rp = new RequestParams(requestParamsMap);
        }

        //the actual GET request is made here
        client.get(API_URL, (rp!=null?rp:null), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                boolean isSuccess = true;
                try {
                    //if the data was fetched successfully then the"success" is true or else it will
                    //be false with a reason, this is being done so as to handle the token failure
                    //sample of failure result looks like
                    /**
                     * {
                     * "success": false,
                     * "reason": "Auth failed"
                     * }
                     **/
                    isSuccess = responseBody.getBoolean("success");
                } catch (JSONException e) {
                    //there is a case, for example categories_api, there is no parameter named "success"
                    //so in that case, it will come inside this exception

                    //throws error, because on success there is no boolean returned, so
                    //we are assuming that it is a success
                    isSuccess = true;
                }

                if (isSuccess) {
                    iCommonAsyncHttpClient_V1.onResultHandler(responseBody);
                } else {
                    if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                        AccessTokenHandler.getInstance().setFlagWhileCalingForToken(API_CALLED_FOR);
                        if(AccessTokenHandler.getInstance().foundAnyError)
                            iCommonAsyncHttpClient_V1.accessTokenExpired();
                        else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                            iCommonAsyncHttpClient_V1.clientTokenExpired();
                    } else {
                        iCommonAsyncHttpClient_V1.onErrorHandler("ERROR");
                    }
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                JSONObject newJSONObject = new JSONObject();
                try {
                    newJSONObject.put("result", responseBody);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                iCommonAsyncHttpClient_V1.onResultHandler(newJSONObject);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                iCommonAsyncHttpClient_V1.onErrorHandler(error);
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

                    if (!isSuccess) {
                        boolean alreadyHandledFlag = false;
                        try {
                            if (responseBody != null && responseBody.has("error")) {
                                if (responseBody.getString("error") != null &&
                                        responseBody.getString("error").toLowerCase().equals("no channels found for this customer.")) {
                                    iCommonAsyncHttpClient_V1.onErrorHandler(responseBody.getString("error"));
                                    alreadyHandledFlag = true;
                                }
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }

                        if(alreadyHandledFlag)
                            return;
                        if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                            AccessTokenHandler.getInstance().setFlagWhileCalingForToken(API_CALLED_FOR);
                            if(AccessTokenHandler.getInstance().foundAnyError)
                                iCommonAsyncHttpClient_V1.accessTokenExpired();
                            else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                                iCommonAsyncHttpClient_V1.clientTokenExpired();
                        }
                    } else {
                        iCommonAsyncHttpClient_V1.onErrorHandler(error.getMessage());
                    }
                } else {
                    iCommonAsyncHttpClient_V1.onErrorHandler(error.getMessage());
                }
            }
        });
    }



    /**
     * All POST requests will be made using this method
     * @param headersArrayList        - list of parameters to be included in header
     * @param requestParamsArrayList  - list of parameters to be included in body
     * @param API_URL                 - the URL of the API
     * @param API_CALLED_FOR          - if the token is expired, then this variable can be used to
     *                                  set the failed call API in some variable
     */
    public void postAsyncHttpClient(ArrayList<ParameterItem> headersArrayList, ArrayList<ParameterItem> requestParamsArrayList, String API_URL, final String API_CALLED_FOR) {

        //Asynchronous Http client for making the POST request
        //Using com.loopj.android.http library, which internally uses the DefaultHttpClient
        AsyncHttpClient client = new AsyncHttpClient();

        try {
            String userAgent = System.getProperty("http.agent");
            client.setUserAgent(userAgent);
            System.out.println("userAgentuserAgentuserAgent==>"+userAgent);
            client.addHeader(CoreProtocolPNames.USER_AGENT, userAgent);
            client.addHeader("user-agent", userAgent);
        } catch(Exception e) {
            e.printStackTrace();
        }

        //Number of tries to make before throwing timeout error
        client.setMaxRetriesAndTimeout(2, 30000);

        //Time to wait before timing out
        client.setTimeout(30000);

        //Check for the length of the headersArrayList, if it has any data in it,
        //the start populating the header parameters
        if(headersArrayList != null && headersArrayList.size() > 0) {
            for (int i = 0; i < headersArrayList.size(); i++) {
                client.addHeader(headersArrayList.get(i).getParameterName(), headersArrayList.get(i).getParameterValue());
            }
        }

        //Check for the length of the requestParamsArrayList, if it has any data in it,
        //the start populating the body parameters
        Map<String, String> requestParamsMap = null;
        if(requestParamsArrayList != null && requestParamsArrayList.size() > 0) {
            requestParamsMap = new HashMap<String, String>();
            for (int i = 0; i < requestParamsArrayList.size(); i++) {
                requestParamsMap.put(requestParamsArrayList.get(i).getParameterName(), requestParamsArrayList.get(i).getParameterValue());
            }
        }

        //typecasting the body parameters to RequestParams & assigning the params, if found
        RequestParams rp = null;
        if(requestParamsMap != null && requestParamsMap.size() > 0) {
            rp = new RequestParams(requestParamsMap);
        }

        //the actual GET request is made here
        client.post(API_URL, (rp!=null?rp:null), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                boolean isSuccess = true;
                try {
                    //if the data was fetched successfully then the"success" is true or else it will
                    //be false with a reason, this is being done so as to handle the token failure
                    //sample of failure result looks like
                    /**
                     * {
                     * "success": false,
                     * "reason": "Auth failed"
                     * }
                     **/
                    isSuccess = responseBody.getBoolean("success");
                } catch (JSONException e) {
                    //there is a case, for example categories_api, there is no parameter named "success"
                    //so in that case, it will come inside this exception

                    //throws error, because on success there is no boolean returned, so
                    //we are assuming that it is a success
                    isSuccess = true;
                }

                if (isSuccess) {
                    iCommonAsyncHttpClient_V1.onResultHandler(responseBody);
                } else {
                    if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                        AccessTokenHandler.getInstance().setFlagWhileCalingForToken(API_CALLED_FOR);
                        if(AccessTokenHandler.getInstance().foundAnyError)
                            iCommonAsyncHttpClient_V1.accessTokenExpired();
                        else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                            iCommonAsyncHttpClient_V1.clientTokenExpired();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                iCommonAsyncHttpClient_V1.onErrorHandler(error);
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

                    if (!isSuccess) {
                        if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                            AccessTokenHandler.getInstance().setFlagWhileCalingForToken(API_CALLED_FOR);
                            if(AccessTokenHandler.getInstance().foundAnyError)
                                iCommonAsyncHttpClient_V1.accessTokenExpired();
                            else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                                iCommonAsyncHttpClient_V1.clientTokenExpired();
                            else {
                                try {
                                    iCommonAsyncHttpClient_V1.onErrorHandler((responseBody.getString("message") != null) ? responseBody.getString("message"):"ERROR");
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try { iCommonAsyncHttpClient_V1.onErrorHandler("ERROR"); } catch(Exception e) { e.printStackTrace(); }
                        }
                    } else {
                        iCommonAsyncHttpClient_V1.onErrorHandler(error.getMessage());
                    }
                } else {
                    iCommonAsyncHttpClient_V1.onErrorHandler(error.getMessage());
                }
            }
        });
    }

    public void postAsyncHttpClientArray(ArrayList<ParameterItem> headersArrayList, ArrayList<ParameterItemJSONArray> requestParamsArrayList, String API_URL, final String API_CALLED_FOR) {

        //Asynchronous Http client for making the POST request
        //Using com.loopj.android.http library, which internally uses the DefaultHttpClient
        AsyncHttpClient client = new AsyncHttpClient();

        try {
            String userAgent = System.getProperty("http.agent");
            client.setUserAgent(userAgent);
            System.out.println("userAgentuserAgentuserAgent==>"+userAgent);
            client.addHeader(CoreProtocolPNames.USER_AGENT, userAgent);
            client.addHeader("user-agent", userAgent);
        } catch(Exception e) {
            e.printStackTrace();
        }

        //Number of tries to make before throwing timeout error
        client.setMaxRetriesAndTimeout(2, 30000);

        //Time to wait before timing out
        client.setTimeout(30000);

        //Check for the length of the headersArrayList, if it has any data in it,
        //the start populating the header parameters
        if(headersArrayList != null && headersArrayList.size() > 0) {
            for (int i = 0; i < headersArrayList.size(); i++) {
                client.addHeader(headersArrayList.get(i).getParameterName(), headersArrayList.get(i).getParameterValue());
            }
        }

        //Check for the length of the requestParamsArrayList, if it has any data in it,
        //the start populating the body parameters
        Map<String, String> requestParamsMap = null;
        if(requestParamsArrayList != null && requestParamsArrayList.size() > 0) {
            requestParamsMap = new HashMap<String, String>();
            for (int i = 0; i < requestParamsArrayList.size(); i++) {
                requestParamsMap.put(requestParamsArrayList.get(i).getParameterName(), requestParamsArrayList.get(i).getParameterValue().toString());
            }
        }

        //typecasting the body parameters to RequestParams & assigning the params, if found
        RequestParams rp = null;
        if(requestParamsMap != null && requestParamsMap.size() > 0) {
            rp = new RequestParams(requestParamsMap);
        }

        //the actual GET request is made here
        client.post(API_URL, (rp!=null?rp:null), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                boolean isSuccess = true;
                try {
                    //if the data was fetched successfully then the"success" is true or else it will
                    //be false with a reason, this is being done so as to handle the token failure
                    //sample of failure result looks like
                    /**
                     * {
                     * "success": false,
                     * "reason": "Auth failed"
                     * }
                     **/
                    isSuccess = responseBody.getBoolean("success");
                } catch (JSONException e) {
                    //there is a case, for example categories_api, there is no parameter named "success"
                    //so in that case, it will come inside this exception

                    //throws error, because on success there is no boolean returned, so
                    //we are assuming that it is a success
                    isSuccess = true;
                }

                if (isSuccess) {
                    iCommonAsyncHttpClient_V1.onResultHandler(responseBody);
                } else {
                    if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                        AccessTokenHandler.getInstance().setFlagWhileCalingForToken(API_CALLED_FOR);
                        if(AccessTokenHandler.getInstance().foundAnyError)
                            iCommonAsyncHttpClient_V1.accessTokenExpired();
                        else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                            iCommonAsyncHttpClient_V1.clientTokenExpired();
                        else {
                            try {
                                iCommonAsyncHttpClient_V1.onErrorHandler((responseBody.getString("message") != null) ? responseBody.getString("message"):"ERROR");
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                iCommonAsyncHttpClient_V1.onErrorHandler(error);
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

                    if (!isSuccess) {
                        if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                            AccessTokenHandler.getInstance().setFlagWhileCalingForToken(API_CALLED_FOR);
                            if(AccessTokenHandler.getInstance().foundAnyError)
                                iCommonAsyncHttpClient_V1.accessTokenExpired();
                            else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                                iCommonAsyncHttpClient_V1.clientTokenExpired();
                        }
                    } else {
                        iCommonAsyncHttpClient_V1.onErrorHandler(error.getMessage());
                    }
                } else {
                    iCommonAsyncHttpClient_V1.onErrorHandler(error.getMessage());
                }
            }
        });
    }



    /**
     * All DELETE requests will be made using this method
     * @param headersArrayList        - list of parameters to be included in header
     * @param requestParamsArrayList  - list of parameters to be included in body
     * @param API_URL                 - the URL of the API
     * @param API_CALLED_FOR          - if the token is expired, then this variable can be used to
     *                                  set the failed call API in some variable
     */
    public void deleteAsyncHttpClient(ArrayList<ParameterItem> headersArrayList, ArrayList<ParameterItem> requestParamsArrayList, String API_URL, final String API_CALLED_FOR) {

        //Asynchronous Http client for making the POST request
        //Using com.loopj.android.http library, which internally uses the DefaultHttpClient
        AsyncHttpClient client = new AsyncHttpClient();

        try {
            String userAgent = System.getProperty("http.agent");
            client.setUserAgent(userAgent);
            System.out.println("userAgentuserAgentuserAgent==>"+userAgent);
            client.addHeader(CoreProtocolPNames.USER_AGENT, userAgent);
            client.addHeader("user-agent", userAgent);
        } catch(Exception e) {
            e.printStackTrace();
        }

        //Number of tries to make before throwing timeout error
        client.setMaxRetriesAndTimeout(2, 30000);

        //Time to wait before timing out
        client.setTimeout(30000);

        //Check for the length of the headersArrayList, if it has any data in it,
        //the start populating the header parameters
        if(headersArrayList != null && headersArrayList.size() > 0) {
            for (int i = 0; i < headersArrayList.size(); i++) {
                client.addHeader(headersArrayList.get(i).getParameterName(), headersArrayList.get(i).getParameterValue());
            }
        }

        //Check for the length of the requestParamsArrayList, if it has any data in it,
        //the start populating the body parameters
        Map<String, String> requestParamsMap = null;
        if(requestParamsArrayList != null && requestParamsArrayList.size() > 0) {
            requestParamsMap = new HashMap<String, String>();
            for (int i = 0; i < requestParamsArrayList.size(); i++) {
                requestParamsMap.put(requestParamsArrayList.get(i).getParameterName(), requestParamsArrayList.get(i).getParameterValue());
            }
        }

        //typecasting the body parameters to RequestParams & assigning the params, if found
        RequestParams rp = null;
        if(requestParamsMap != null && requestParamsMap.size() > 0) {
            rp = new RequestParams(requestParamsMap);
        }

        //the actual GET request is made here
        client.delete(API_URL, (rp!=null?rp:null), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                boolean isSuccess = true;
                try {
                    //if the data was fetched successfully then the"success" is true or else it will
                    //be false with a reason, this is being done so as to handle the token failure
                    //sample of failure result looks like
                    /**
                     * {
                     * "success": false,
                     * "reason": "Auth failed"
                     * }
                     **/
                    isSuccess = responseBody.getBoolean("success");
                } catch (JSONException e) {
                    //there is a case, for example categories_api, there is no parameter named "success"
                    //so in that case, it will come inside this exception

                    //throws error, because on success there is no boolean returned, so
                    //we are assuming that it is a success
                    isSuccess = true;
                }

                if (isSuccess) {
                    iCommonAsyncHttpClient_V1.onResultHandler(responseBody);
                } else {
                    if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                        AccessTokenHandler.getInstance().setFlagWhileCalingForToken(API_CALLED_FOR);
                        if(AccessTokenHandler.getInstance().foundAnyError)
                            iCommonAsyncHttpClient_V1.accessTokenExpired();
                        else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                            iCommonAsyncHttpClient_V1.clientTokenExpired();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String error, Throwable throwable) {
                iCommonAsyncHttpClient_V1.onErrorHandler(error);
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

                    if (!isSuccess) {
                        if(AccessTokenHandler.getInstance().handleTokenExpiryConditions(responseBody)) {
                            AccessTokenHandler.getInstance().setFlagWhileCalingForToken(API_CALLED_FOR);
                            if(AccessTokenHandler.getInstance().foundAnyError)
                                iCommonAsyncHttpClient_V1.accessTokenExpired();
                            else if(AccessTokenHandler.getInstance().foundAnyErrorForClientToken)
                                iCommonAsyncHttpClient_V1.clientTokenExpired();
                            else {
                                try {
                                    iCommonAsyncHttpClient_V1.onErrorHandler((responseBody.getString("message") != null) ? responseBody.getString("message"):"ERROR");
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try { iCommonAsyncHttpClient_V1.onErrorHandler("ERROR"); } catch(Exception e) { e.printStackTrace(); }
                        }
                    } else {
                        iCommonAsyncHttpClient_V1.onErrorHandler(error.getMessage());
                    }
                } else {
                    iCommonAsyncHttpClient_V1.onErrorHandler(error.getMessage());
                }
            }
        });
    }
}
