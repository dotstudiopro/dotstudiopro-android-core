package com.dotstudioz.dotstudioPRO.services.constants;

/**
 * Created by Admin on 17-09-2015.
 */
public class AnalyticsConstants {

    //public static final String ANALYTICS_URL = "https://dev.collector.myspotlight.tv/collect";
    public static final String ANALYTICS_URL = "https://collector.myspotlight.tv/collect";

    public static final String CREATE_ANALYTICS_USER_API = "https://collector.dotstudiopro.com/users";
    public static final String SAVE_PLAYER_DATA_API = "https://collector.dotstudiopro.com/plays";
    public static final String SAVE_APP_DATA_API = "https://collector.dotstudiopro.com/players";

    public static final String APP_NAME             = "SPOTLIGHT";
    public static final String APP_CODE_NAME        = "SPOTLIGHT_ANDROID";
    public static final String APP_VERSION          = "1.0";
    public static final String ANALYTICS_USER       = "540b621fbf9273f61111a46d";
   // public static final String PAGE_TYPE            = "android";
    public static final String PAGE_TYPE            = "firetv";

    public static final String COUNTRY_CODE         = "CA";
    public static final String COUNTRY_NAME         = "Canada";

    public static String SESSION_ID_VALUE           = "";

    public static final String START_TIME           = "0";
    public static final String END_TIME             = "0";

    //Events for /plays
    public static final String ON_VIEW              = "onView";
    public static final String ON_PAGE_LOAD         = "pageLoad";
    public static final String ON_BEFORE_PLAY       = "onBeforePlay";
    public static final String ON_PLAY              = "onPlay";
    public static final String ON_DISPLAY_CLICK     = "onDisplayClick";
    public static final String ON_PAUSE             = "onPause";
    public static final String ON_RESUME            = "onResume";
    public static final String ON_STOP              = "onStop";
    public static final String ON_SEEK              = "onSeek";
    public static final String LEAVE_PAGE           = "leavePage";
    public static final String ON_COMPLETE          = "onComplete";
    public static final String ON_FULL_SCREEN       = "onFullScreen";
    public static final String ON_AD_START          = "onAdStart";
    public static final String ON_AD_ERROR          = "onAdError";
    public static final String ON_AD_CLICKED        = "onAdClicked";
    public static final String ON_AD_COMPLETE       = "onAdComplete";

    //Events for /players
    public static final String ON_APP_LOADED        = "onAppLoaded";
    public static final String ON_USER_LOGIN        = "onUserLogin";
    public static final String ON_SEARCH            = "onSearch";
    public static final String ON_CATEGORY_CLICKED  = "onCategoryClicked";
    public static final String ON_CHANNEL_CLICKED   = "onChannelClicked";









    //Analytics event categories
    public static final String CATEGORY_PLAYBACK            = "playback";
    public static final String CATEGORY_PLAYER_SETUP        = "player_setup";
    public static final String CATEGORY_QUALITY             = "quality";
    public static final String CATEGORY_ADVERTISING         = "advertising";
    public static final String CATEGORY_CLOSED_CAPTIONING   = "closed_captioning";

    //Analytics event types for playback
    public static final String EVENT_FIRST_FRAME            = "first_frame";
    public static final String EVENT_PLAY                   = "play";
    public static final String EVENT_PAUSE                  = "pause";
    public static final String EVENT_COMPLETE               = "complete";
    public static final String EVENT_MEDIA_ERROR            = "media_error";
    public static final String EVENT_SEEK                   = "seek";
    public static final String EVENT_RESUME_AFTER_SEEK      = "resume_after_seek";
    public static final String EVENT_END_PLAYBACK           = "end_playback";
    public static final String EVENT_FIRST_QUARTILE         = "first_quartile";
    public static final String EVENT_SECOND_QUARTILE        = "second_quartile";
    public static final String EVENT_THIRD_QUARTILE         = "third_quartile";
    //Analytics event types for player_setup
    public static final String EVENT_READY                  = "ready";
    public static final String EVENT_SETUP_ERROR            = "setup_error";
    public static final String EVENT_IDLE                   = "idle";
    public static final String EVENT_RESIZE                 = "resize";
    //Analytics event types for quality
    public static final String EVENT_LEVELS                 = "levels";
    public static final String EVENT_QUALITY_CHANGE         = "quality_change";
    //Analytics event types for closed captioning
    public static final String EVENT_CLOSED_CAPTIONING      = "track";
    //Analytics event types for advertising
    public static final String EVENT_AD_CLICK               = "ad_click";
    public static final String EVENT_AD_SKIP                = "ad_skip";
    public static final String EVENT_AD_COMPLETE            = "ad_complete";
    public static final String EVENT_AD_IMPRESSION          = "ad_impression";
    public static final String EVENT_AD_REQUEST             = "ad_request";
    public static final String EVENT_AD_LOADED              = "ad_loaded";
    public static final String EVENT_AD_ERROR               = "ad_error";


    //Analytics api parameters name
    //MAIN OBJECT
    public static final String DATE                     = "date";
    public static final String FIRST_EVENT              = "first_event";
    public static final String LAST_UPDATE              = "last_update";
    public static final String LAST_SENT                = "last_sent";
    public static final String SESSION_ID               = "session_id";

    //PAGE
    public static final String PAGE                     = "page";
    public static final String CHANNEL_ID               = "channel_id";
    public static final String PLAYLIST_ID              = "playlist_id";
    public static final String PAGE_COMPANY_ID          = "company_id";
    public static final String PAGE_TYPE_PARAMETER_NAME = "page_type";
    public static final String PLAYER_VERSION_NAME      = "player_version";
    public static final String PLAYER_VERSION_VALUE     = "android_v1";
    public static final String PAGE_URL                 = "page_url";

    //MOBILE DEVICE INFO
    public static final String PHONE                        = "phone";
    public static final String TABLET                       = "tablet";
    public static final String MOBILE_DEVICE_INFO           = "mobile_device_info";
    public static final String MOBILE_DEVICE_TYPE           = "type";
    public static final String MOBILE_DEVICE_MANUFACTURER   = "manufacturer";
    public static final String MOBILE_DEVICE_OS_VERSION     = "os_version";
    public static final String MOBILE_DEVICE_MODEL          = "model";
    public static final String MOBILE_DEVICE_APP_NAME       = "app_name";
    public static final String MOBILE_DEVICE_APP_VERSION    = "app_version";

    //VIDEO
    public static final String VIDEO                    = "video";
    public static final String VIDEO_ID                 = "id";
    public static final String COMPANY_ID               = "company_id";

    //USER
    public static final String USER                     = "user";
    public static final String USER_ID                  = "id";
    public static final String EMAIL                    = "email";

    //LOCATION
    public static final String LOCATION                 = "location";
    public static final String ISOCODE                  = "isocode";
    public static final String COUNTRY                  = "country";
    public static final String STATE                    = "state";
    public static final String CITY                     = "city";
    public static final String POSTAL                   = "postal";
    public static final String GEOMETRY                 = "geometry";
    public static final String GEOMETRY_TYPE            = "type";
    public static final String GEOMETRY_COORDINATES     = "coordinates";

    //EVENTS
    public static final String EVENTS                   = "events";

    //EVENT
    public static final String EVENT_DATE               = "date";
    public static final String EVENT_CATEGORY           = "category";
    public static final String EVENT_TYPE               = "type";
    public static final String EVENT_MESSAGE            = "message";
    public static final String EVENT_DATA               = "data";

    //TIME inside EVENT
    public static final String EVENT_TIME               = "time";
    public static final String EVENT_TIME_DURATION      = "duration";
    public static final String EVENT_TIME_POSITION      = "position";
    public static final String EVENT_TIME_POSITION_END  = "position_end";

    //EVENT inside EVENT Object
    public static final String EVENT_INSIDE_EVENT       = "event";

    //EVENT inside EVENT inside EVENTS Object
    public static final String EVENT_INSIDE_EVENT_INSIDE_EVENTS = "e";

    //OLD_STATES
    public static final String OLD_STATE_PLAYING        = "playing";
    public static final String OLD_STATE_PAUSED         = "paused";
    public static final String OLD_STATE_IDLE           = "idle";
    public static final String OLD_STATE_COMPLETED      = "completed";











    public static final String GA_CATEGORY_PLAY                     = "Play";
    public static final String GA_FIRST_FRAME_CATEGORY_PLAY         = "FirstFrame";
    public static final String GA_PLAY_BUTTON_CLICKED_CATEGORY_PLAY = "PlayButtonClick";
    public static final String GA_PLAY_EVENT_CATEGORY_PLAY          = "PlayEvent";


    public static final String GA_CATEGORY_PLAYER           = "Player";

    public static final String GA_SETUP_1_PLAYER_REQUEST    = "setup_1_player_ready";
    public static final String GA_SETUP_2_AD_REQUEST        = "setup_2_ad_request";
    public static final String GA_SETUP_3_AD_LOADED         = "setup_3_ad_loaded";
    public static final String GA_SETUP_4_AD_IMPRESSION     = "setup_4_ad_impression";
    public static final String GA_SETUP_5_AD_STARTED        = "setup_5_ad_started";
    public static final String GA_SETUP_6_AD_COMPLETE       = "setup_6_ad_complete";

    public static final String GA_SETUP_LOADEDMETADATA      = "setup_loadedmetadata";
    public static final String GA_BEFORE_AD_REQUEST         = "before_ad_request";
    public static final String GA_BEFORE_ADPLAY             = "before_adPlay";
    public static final String GA_BEFORE_SET_CONTENT_WITH_AD_TAG = "before_set_content_with_ad_tag";
    public static final String GA_SET_CONTENT_WITH_AD_TAG   = "set_content_with_ad_tag";
    public static final String GA_CREATE_IMA_OBJECT         = "create_ima_object";
    public static final String GA_BEFORE_SETUP_ADS          = "before_setup_ads";
    public static final String GA_SETUP_ADS_COMPLETE        = "setup_ads_complete";
    public static final String GA_AD_ERROR                  = "ad_error";

    public static final String GA_AD_CLICKED                = "ad_clicked";
    public static final String GA_AD_MOBILE_BLOCK_ANDROID   = "ad_mobile_block_android";
    public static final String GA_VIEW_FIRST_FRAME          = "view_first_frame";
    public static final String GA_VIEW_QUARTILE_1           = "view_quartile_1";
    public static final String GA_VIEW_QUARTILE_2           = "view_quartile_2";
    public static final String GA_VIEW_QUARTILE_3           = "view_quartile_3";
    public static final String GA_VIEW_CONTENT_ENDED        = "view_content_ended";



    public static final String AF_FIRST_FRAME               = "first_frame";
    public static final String AF_VIDEO_ID                  = "video_id";
    public static final String AF_TITLE                     = "title";
    public static final String AF_LOGIN                     = "login";
    public static final String AF_USER_ID                   = "user_id";

}
