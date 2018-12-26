package com.dotstudioz.dotstudioPRO.corelibrary.userauthenticationfragment;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.result.Credentials;
import com.auth0.android.result.UserProfile;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.dotstudioz.dotstudioPRO.corelibrary.SharedPreferences.SharedPreferencesUtil;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
/*import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.DefaultAudience;
import com.facebook.login.DeviceLoginManager;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginResult;*/

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohsin on 26-10-2017.
 */

public class UsernamePasswordAuth0API {

    public class ParameterItem {

        private String parameterName;
        private String parameterValue;

        public ParameterItem(String parameterName, String parameterValue) {
            setParameterName(parameterName);
            setParameterValue(parameterValue);
        }

        public String getParameterName() {
            return parameterName;
        }

        public void setParameterName(String parameterName) {
            this.parameterName = parameterName;
        }

        public String getParameterValue() {
            return parameterValue;
        }

        public void setParameterValue(String parameterValue) {
            this.parameterValue = parameterValue;
        }
    }

    public IUsernamePasswordAuth0API iUsernamePasswordAuth0API;
    public interface IUsernamePasswordAuth0API {
        void authenticateUserResponse(boolean response);
        void authenticateUserError(String error);
    }

    private static Context mContext;
    private static UsernamePasswordAuth0API mInstance = new UsernamePasswordAuth0API();
    public static synchronized UsernamePasswordAuth0API getInstance(Context context) {
        mContext = context;
        return mInstance;
    }

    public Auth0 account;
    private String clientID;
    private String auth0Domain;
    private String username;
    private String password;

    public void authenticateUser(String auth0ClientIDParam, String auth0DomainParam, String usernameParam, String passwordParam, String companyKeyParam) {
        iUsernamePasswordAuth0API = (IUsernamePasswordAuth0API) mContext;

        //account = new Auth0("yox0owDeBdTrKHU0GkrfC3OO9USmgZQ5", "dotstudiopro.auth0.com");//famileague
        account = new Auth0(auth0ClientIDParam, auth0DomainParam);
        //account = new Auth0("5Dm7WsZuYcq3SwnYt33gdxkqdPqkazQT", "dotstudiopro.auth0.com");//nosey
        AuthenticationAPIClient authentication = new AuthenticationAPIClient(account);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        //paramMap.put("c", "57e9fb9644afa8c50570d38dab7b5fe1c094c9b5");
        paramMap.put("c", companyKeyParam);

        authentication
            //.login("mohsin@dotstudioz.com", "mohsin", "Username-Password-Authentication")
                //.login("ketan6@gmail.com", "ketan6", "Username-Password-Authentication")
                .login(usernameParam, passwordParam, "Username-Password-Authentication")
                .addAuthenticationParameters(paramMap)
            .start(new BaseCallback<Credentials, AuthenticationException>() {
                @Override
                public void onSuccess(Credentials payload) {
                    //Logged in!
                    System.out.println("SUCCESS SUCCESS SUCCESS");
                    System.out.println(payload.toString());
                    getTheUserProfileFromAuth0(payload);
                }

                @Override
                public void onFailure(AuthenticationException error) {
                    //Error!
                    System.out.println("FAILURE FAILURE FAILURE ");
                    iUsernamePasswordAuth0API.authenticateUserError(error.getMessage());
                    error.printStackTrace();
                }
            });
    }

    /*CallbackManager callbackManager;
    public void authenticateFacebookUser(final String auth0ClientIDParam, final String auth0DomainParam, final String companyKeyParam, final String connectionNameParam) {
        account = new Auth0(auth0ClientIDParam, auth0DomainParam);//famileague
        DeviceLoginManager.getInstance().logInWithReadPermissions(((Activity)mContext), Arrays.asList("public_profile, email"));

        callbackManager = CallbackManager.Factory.create();

        DeviceLoginManager.getInstance().logOut();
        DeviceLoginManager.getInstance().setDefaultAudience(DefaultAudience.EVERYONE);
        //DeviceLoginManager.getInstance().setDeviceRedirectUri(Uri.parse("https://dotstudiopro.auth0.com/login/callback"));
        DeviceLoginManager.getInstance().setLoginBehavior(LoginBehavior.DEVICE_AUTH);
        DeviceLoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("ON SUCCESS==>ON SUCCESS==>ON SUCCESS==>"+loginResult.getAccessToken().getToken());
                loginWithOAuthAccessToken(loginResult.getAccessToken().getToken(),
                        auth0ClientIDParam,
                        auth0DomainParam,
                        companyKeyParam,
                        connectionNameParam);
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel called!");
            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();
                System.out.println("onError==>"+error.getMessage());
            }
        });
    }*/

    public void loginWithOAuthAccessToken(String tokenKey, String auth0ClientIDParam, String auth0DomainParam, String companyKeyParam, String connectionNameParam) {
        iUsernamePasswordAuth0API = (IUsernamePasswordAuth0API) mContext;

        account = new Auth0(auth0ClientIDParam, auth0DomainParam);
        AuthenticationAPIClient authentication = new AuthenticationAPIClient(account);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("c", companyKeyParam);

        authentication
                .loginWithOAuthAccessToken( tokenKey, connectionNameParam)
                .setScope("openid")
                .addAuthenticationParameters(paramMap)
                .start(new BaseCallback<Credentials, AuthenticationException>() {
                    @Override
                    public void onSuccess(Credentials payload) {
                        //Logged in!
                        System.out.println("SUCCESS SUCCESS SUCCESS");
                        System.out.println(payload.toString());
                        getTheUserProfileFromAuth0(payload);
                    }

                    @Override
                    public void onFailure(AuthenticationException error) {
                        //Error!
                        System.out.println("FAILURE FAILURE FAILURE ");
                        error.printStackTrace();
                    }
                });
    }







    UserProfile mUserProfile;
    public void getTheUserProfileFromAuth0(Credentials credentials) {
        // The process to reclaim an UserProfile is preceded by an Authentication call.
        AuthenticationAPIClient aClient = new AuthenticationAPIClient(account);

        //aClient.tokenInfo(credentials.getIdToken())
        aClient.tokenInfo(credentials.getIdToken())
                .start(new BaseCallback<UserProfile, AuthenticationException>() {
                    @Override
                    public void onSuccess(final UserProfile payload) {
                        ((Activity)mContext).runOnUiThread(new Runnable() {
                            public void run() {
                                mUserProfile = payload;
                                /*try {
                                    System.out.println("PROFILE REQUEST:-" + mUserProfile.toString());
                                    System.out.println("mUserProfile.getExtraInfo().get('spotlight')" + mUserProfile.getExtraInfo().get("spotlight"));
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }*/
                                if (mUserProfile != null && mUserProfile.getExtraInfo() != null && mUserProfile.getExtraInfo().size() > 0) {

                                    if (mUserProfile.getEmail() != null && mUserProfile.getEmail().length() > 0) {
                                        ApplicationConstants.userEmailId = mUserProfile.getEmail();

                                        SharedPreferencesUtil.getInstance(mContext).addToSharedPreference(
                                                ApplicationConstants.USER_EMAIL_DETAILS_RESPONSE_SHARED_PREFERENCE,
                                                ApplicationConstants.userEmailId,
                                                ApplicationConstants.USER_EMAIL_DETAILS_RESPONSE_SHARED_PREFERENCE_KEY);
                                    }

                                    if (mUserProfile.getExtraInfo().containsKey("spotlight")) {
                                        scrapeOutInformationFromClientToken(mUserProfile.getExtraInfo().get("spotlight").toString());
                                    }
                                } else {
                                    Toast.makeText(mContext, "User details not available!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }

                    @Override
                    public void onFailure(AuthenticationException error) {
                        ((Activity)mContext).runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(((Activity)mContext), "Profile Request Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }



    String userIdString = "";
    String userFirstName = "";
    String userLastName = "";
    String userAvatarPath = "";
    public void scrapeOutInformationFromClientToken(String clientToken) {
        ApplicationConstants.CLIENT_TOKEN = clientToken;

        Base64 decoder = new Base64(true);
        byte[] secret = decoder.decodeBase64(ApplicationConstants.CLIENT_TOKEN.split("\\.")[1]);
        String s = new String(secret);
        //System.out.println("String of secret:String of secret:" + s);
        try {
            JSONObject spotlightJSONObject = new JSONObject(s);

            if (spotlightJSONObject.has("context")) {
                if (spotlightJSONObject.getJSONObject("context").has("id")) {
                    userIdString = spotlightJSONObject.getJSONObject("context").getString("id");
                    ApplicationConstants.userIdString = userIdString;
                }

                if (spotlightJSONObject.getJSONObject("context").has("first_name")) {
                    userFirstName = spotlightJSONObject.getJSONObject("context").getString("first_name");
                    ApplicationConstants.userFirstName = userFirstName;
                }

                if (spotlightJSONObject.getJSONObject("context").has("last_name")) {
                    userLastName = spotlightJSONObject.getJSONObject("context").getString("last_name");
                    ApplicationConstants.userLastName = userLastName;
                }

                if (spotlightJSONObject.getJSONObject("context").has("avatar")) {
                    userAvatarPath = spotlightJSONObject.getJSONObject("context").getString("avatar");
                    ApplicationConstants.userAvatarPath = userAvatarPath;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SharedPreferencesUtil.getInstance(mContext).addToSharedPreference(
                ApplicationConstants.USER_DETAILS_RESPONSE_SHARED_PREFERENCE,
                ApplicationConstants.CLIENT_TOKEN,
                ApplicationConstants.USER_DETAILS_RESPONSE_SHARED_PREFERENCE_KEY);

        iUsernamePasswordAuth0API.authenticateUserResponse(true);
    }
}
