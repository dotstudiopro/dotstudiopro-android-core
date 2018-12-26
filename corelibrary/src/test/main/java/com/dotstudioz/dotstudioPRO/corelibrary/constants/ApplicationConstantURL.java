package com.dotstudioz.dotstudioPRO.services.constants;

/**
 * Created by Admin on 29-06-2015.
 */
public  class ApplicationConstantURL {

    private static ApplicationConstantURL ourInstance = new ApplicationConstantURL();

    public static ApplicationConstantURL getInstance() {
        return ourInstance;
    }

    private ApplicationConstantURL() {}

    public String COUNTRY_CODE = "IN";

    //PRODUCTION_URL
    public  String API_DOMAIN = "http://api.myspotlight.tv"; //PRODUCTION SERVER
    public  String API_DOMAIN_S = "https://api.myspotlight.tv"; //PRODUCTION SERVER
    //public  String API_DOMAIN = "http://c69ffc89.ngrok.io/"; // This was Erics machine
    //public  String API_DOMAIN = "http://dev.api.myspotlight.tv"; // DEVELOPMENT SERVER

    public  String TOKEN_URL = API_DOMAIN + "/token";
    public  String DEVICE_CODE_URL = API_DOMAIN + "/device/codes/new";
    public  String DEVICE_CODE_ACTIVATION_URL = API_DOMAIN + "/device/codes/customer";
    public  String DEVICE_CODE_VERIFICATION_URL = API_DOMAIN + "/device/codes?code=";

   // http://api.myspotlight.tv/device/codes/new
    //public  String USER_LOGIN_URL = "https://api.dotstudiodev.com/v2/universalapi/customer_login";
    public  String USER_LOGIN_URL = API_DOMAIN + "/users/login";
    public  String USER_DETAILS_URL = API_DOMAIN + "/users/details";
    public  String USER_REGISTER_URL = API_DOMAIN + "/users/register";
    public  String USER_TOKEN_REFRESH = API_DOMAIN + "/users/token/refresh";
    public  String LON_LAT_COUNTRY = API_DOMAIN + "/country/analytics";
    public  String CHANGE_PASSWORD_URL = API_DOMAIN + "/users/password";
    //public  String CHANGE_PASSWORD_URL = "https://api.dotstudiopro.com/v2/universalapi/customer_details/password";
    //public  String MY_PURCHASES_URL = "https://api.dotstudiopro.com/v2/universalapi/collect_video_information";
    public  String MY_PURCHASES_URL = API_DOMAIN + "/users/orders/history";
    public  String VIDEO_PURCHASE_STATUS = "https://api.dotstudiopro.com/v2/universalapi/paywall_status";
    public  String CUSTOMER_REGISTER = "https://api.dotstudiopro.com/v2/universalapi/customer_register";

    public String APP_VERSION_ANDROID_API = API_DOMAIN + "/latestAppVersion/android";
    public String APP_VERSION_FIRE_TV_API = API_DOMAIN + "/latestAppVersion/fire_tv";

    public String BLOG_LIST = "http://api.americanbeautystar.com:80/wp-json/dsp/v1/featured/blog/carousel";

    //public  String AVATAR = "http://spotlight.dotstudiopro.com/user/avatar/";
    public  String AVATAR = API_DOMAIN + "/users/avatar/";
    //public   String CATEGORIES_LIST = "http://spotlight.dotstudiopro.com/categories/list";
    public   String HOMEPAGE_CATEGORIES_LIST = "http://api.spotnetwork.tv/wp-json/dsp/v1/homepage";

    public   String SUBSCRIPTION_LIST = API_DOMAIN_S + "/subscriptions/summary";
    public   String ACTIVE_SUBSCRIPTIONS_LIST = API_DOMAIN_S + "/subscriptions/users/active_subscriptions";
    public   String CHECK_CHANNEL_SUBSCRIPTIONS_STATUS = API_DOMAIN_S + "/subscriptions/check/";
    public   String CREATE_BRAINTREE_CUSTOMER_FROM_NONCE = API_DOMAIN_S + "/subscriptions/users/create_from_nonce";
    public   String CREATE_CHARGIFY_CUSTOMER_USING_SUBSCRIPTION_ID = API_DOMAIN_S + "/subscriptions/users/import/subscribe_to/";

    public   String CATEGORIES_LIST = API_DOMAIN + "/categories/"+COUNTRY_CODE+"/";
    //public  String CHANNELS = "http://spotlight.dotstudiopro.com/json/channels";
    public  String CHANNELS = API_DOMAIN + "/channels/"+COUNTRY_CODE+"/";
    public  String CHANNEL = API_DOMAIN + "/channel/"+COUNTRY_CODE+"/";
    public  String CHANNELS_DETAILS = API_DOMAIN + "/channels/"+COUNTRY_CODE+"/";

    public  String SPOTLIGHT_DOMAIN = "http://spotlight.dotstudiopro.com";
    public  String SEARCH_API_URL = API_DOMAIN + "/search/";
    public  String SEARCH_VIDEO_API_URL = API_DOMAIN + "/search/videos/";
    public  String SEARCH_SUGGESTER_API_URL = API_DOMAIN + "/search/s/";
    public  String SEARCH_BY_COMPANY_API_URL = "http://spotlight.dotstudiopro.com/company/";
    public  String SEARCH_BY_COMPANY_DATA_FORMAT = "/json";

    public  String IMAGES = "https://images.dotstudiopro.com/";
    //public  String VIDEOS_API = "https://api.dotstudiopro.com/v2/cc89512b/json/videos/";
    //public  String VIDEOS_API = "https://api.dotstudiopro.com/v2/cc89512b/json/videos/";
    public  String VIDEOS_API = API_DOMAIN + "/channels/"+COUNTRY_CODE+"/";

    public String VIDEO_PLAY2_API = API_DOMAIN + "/video/play2/";

    public  String PLAYLIST_VIDEOS = "https://api.dotstudiopro.com/v2/cc89512b/json/playlists/";
    public  String TEASER_DOMAIN = "http://cdn.dotstudiopro.com/";

    public  String GET_FIRST_LAST_NAME_API = "https://api.dotstudiopro.com/v2/universalapi/customer_details";
    public  String SAVE_FIRST_LAST_NAME_API = "https://api.dotstudiopro.com/v2/universalapi/customer_details/edit";

    public  String FORGOT_PASSWORD_API = "https://api.dotstudiopro.com/v2/universalapi/forgotpassword";

    /*public  String CREATE_ANALYTICS_USER_API = "https://collector.dotstudiodev.com/users";
    public  String SAVE_PLAYER_DATA_API = "https://collector.dotstudiodev.com/plays";
    public  String SAVE_APP_DATA_API = "https://collector.dotstudiodev.com/players";*/
    public  String CREATE_ANALYTICS_USER_API = "https://collector.dotstudiopro.com/users";
    public  String SAVE_PLAYER_DATA_API = "https://collector.dotstudiopro.com/plays";
    public  String SAVE_APP_DATA_API = "https://collector.dotstudiopro.com/players";

    //public  String CLIENT_TOKEN_API = "http://myspotlight.tv/dotstudio_token";
    public  String CLIENT_TOKEN_API = API_DOMAIN + "/users/payment/token";
    //public  String RENT_API = "http://myspotlight.tv/rent";
    public  String RENT_API = API_DOMAIN + "/users/payment/purchase";
    public  String SHARING_DOMAIN_NAME = "http://myspotlight.tv/watch";

    public  String ADSERVER_API = "http://adserver.dotstudiopro.com/adserver/www/delivery/fc.php?script=apVideo:vast2&zoneid=";

    public  String VIDEO_PLAYBACK_DETAILS_API = API_DOMAIN + "/users/videos/point/";
    public  String MULTIPLE_VIDEO_PLAYBACK_DETAILS_API = API_DOMAIN + "/users/videos/points/";
    public  String LAST_WATCHED_VIDEOS_API = API_DOMAIN + "/users/resumption/videos";

    public  String ADD_TO_MY_LIST_API = API_DOMAIN + "/watchlist/channels/add";
    public  String DELETE_FROM_MY_LIST_API = API_DOMAIN + "/watchlist/channels/delete";
    public  String  GET_MY_LIST_API = API_DOMAIN + "/watchlist/channels";

    public  String RECOMMENDATION_API = API_DOMAIN + "/search/recommendation";
    public  String RECOMMENDATION_CHANNEL_API = API_DOMAIN + "/search/recommendation/channel";

    public  String ANALYTICS_TESTING_URL = API_DOMAIN + "/testing/analytics";


    public void setCountryCode(String countryCode) {
        this.COUNTRY_CODE = countryCode;

        this.CATEGORIES_LIST = API_DOMAIN + "/categories/"+countryCode+"/";
        this.CHANNELS = API_DOMAIN + "/channels/"+countryCode+"/";
        this.CHANNELS_DETAILS = API_DOMAIN + "/channels/"+countryCode+"/";
        this.VIDEOS_API = API_DOMAIN + "/channels/"+countryCode+"/";

    }


}
