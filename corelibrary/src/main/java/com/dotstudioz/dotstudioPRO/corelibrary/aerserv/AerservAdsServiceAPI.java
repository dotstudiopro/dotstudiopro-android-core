package com.dotstudioz.dotstudioPRO.corelibrary.aerserv;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aerserv.sdk.AerServConfig;
import com.aerserv.sdk.AerServEvent;
import com.aerserv.sdk.AerServEventListener;
import com.aerserv.sdk.AerServInterstitial;
import com.aerserv.sdk.AerServSdk;
import com.aerserv.sdk.AerServVirtualCurrency;

import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mohsin on 25-10-2017.
 */

public class AerservAdsServiceAPI {

    Context callingContext;
    VideoInfoDTO currentVideoInfoDTO;

    String MANUFACTURER = "";
    String MODEL = "";
    String widthPixels = "";
    String heightPixels = "";
    String companyBundleID = "";

    private static AerservAdsServiceAPI mInstance = new AerservAdsServiceAPI();
    private AerservAdsServiceAPI(){ }
    public static synchronized AerservAdsServiceAPI getInstance() {
        return mInstance;
    }


    //public String AERSERV_APP_ID = "1006080";//LIVE APP ID
    //public String AERSERV_APP_ID = "1000741";//TEST APP ID
    public String AERSERV_APP_ID = "1006489";//ABS LIVE APP ID

    //public String AERSERV_PLC_ID = "1028723";//LIVE APP ID MY_SPOTLIGHT_TV
    //public String AERSERV_PLC_ID = "1028726";//LIVE APP ID NOSEY
    //public String AERSERV_PLC_ID = "1028763";//LIVE APP ID CELEBRITY_PAGE
    //public String AERSERV_PLC_ID = "1029907";//LIVE APP ID ABS
    public String AERSERV_PLC_ID = "1000741";//TEST APP ID

    public void initializeAerServSDK(Context context, String aerservAppID, String aerservPLCId, VideoInfoDTO vidInfoDTO, String MANUFACTURER,
                                     String MODEL, String widthPixels, String heightPixels, String companyBundleID) {
        callingContext = context;
        currentVideoInfoDTO = vidInfoDTO;
        this.MANUFACTURER = MANUFACTURER;
        this.MODEL = MODEL;
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        this.companyBundleID = companyBundleID;
        this.AERSERV_APP_ID = aerservAppID;
        this.AERSERV_PLC_ID = aerservPLCId;
        AerServSdk.init((Activity) context, AERSERV_APP_ID);

        iAerservAdsServiceAPI = (IAerservAdsServiceAPI) context;
    }

    private AerServInterstitial interstitial;
    private List<String> keywords = Arrays.asList("keyword1", "keyword2");
    public void loadInterstitial(VideoInfoDTO videoInfoDTO) {

        currentVideoInfoDTO = videoInfoDTO;

        iAerservAdsServiceAPI.saveAdRequestAnalyticsEvent();


        Map<String, String> pubKeys = new HashMap<String, String>();

        Date tempDate = new Date();
        double newCB = ((int) tempDate.getTime()) * (Math.floor((Math.random() * 10000) + 1));
        int newCBInt = (int) newCB;

        /*pubKeys.put("cb", ""+newCBInt);
        pubKeys.put("rand", ""+newCBInt);
        pubKeys.put("media_id", currentVideoInfoDTO.getVideoID());
        pubKeys.put("title", currentVideoInfoDTO.getVideoTitle());
        pubKeys.put("media_title_dashes", currentVideoInfoDTO.getVideoTitle().replaceAll(" ", "-"));
        pubKeys.put("media_desc", currentVideoInfoDTO.getDescription());
        pubKeys.put("media_desc_dashes", currentVideoInfoDTO.getDescription().replaceAll(" ", "-"));
        pubKeys.put("app_bundle", companyBundleID);
        pubKeys.put("device_ifa", ApplicationConstants.ADVERTISING_ID_CLIENT);
        pubKeys.put("app_name", "nosey");
        pubKeys.put("device_make", MANUFACTURER);
        pubKeys.put("device_model", MODEL);
        pubKeys.put("player_width", widthPixels);
        pubKeys.put("player_height", heightPixels);
        pubKeys.put("channel_id", currentVideoInfoDTO.getChannelID());
        pubKeys.put("series_title_dashes", currentVideoInfoDTO.getSeriesTitle().replaceAll(" ", "-"));
        pubKeys.put("vpaid", "js");*/
        //pubKeys.put("VPI", "mp4");



        pubKeys.put("media_title", currentVideoInfoDTO.getVideoTitle().replaceAll(" ", "-"));
        pubKeys.put("media_url", currentVideoInfoDTO.getVideoToPlayURL().replaceAll(" ", "-"));
        pubKeys.put("vid_duration", ""+currentVideoInfoDTO.getVideoDuration());
        pubKeys.put("company_id", currentVideoInfoDTO.getCompanyId());
        pubKeys.put("series_title", currentVideoInfoDTO.getSeriesTitle().replaceAll(" ", "-"));
        pubKeys.put("video_id", currentVideoInfoDTO.getVideoID());

        //final AerServConfig config = new AerServConfig(this, "1028726")
        final AerServConfig config = new AerServConfig(callingContext, AERSERV_PLC_ID)
                .setEventListener(listener)
                .setPubKeys(pubKeys)
                .setKeywords(keywords);
        interstitial = new AerServInterstitial(config);
        interstitial.show();
    }

    private AerServEventListener listener = new AerServEventListener(){
        @Override
        public void onAerServEvent(final AerServEvent event, final List<Object> args){
            ((Activity)callingContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String msg = null;
                    AerServVirtualCurrency vc = null;
                    switch (event) {
                        case AD_CLICKED:
                            iAerservAdsServiceAPI.saveAdClickedAnalyticsEvent();
                            break;
                        case AD_FAILED:
                            if (args.size() > 1) {
                                Integer adFailedCode =
                                        (Integer) args.get(AerServEventListener.AD_FAILED_CODE);
                                String adFailedReason =
                                        (String) args.get(AerServEventListener.AD_FAILED_REASON);
                                msg = "Ad failed with code=" + adFailedCode + ", reason=" + adFailedReason;
                            } else {
                                msg = "Ad Failed with message: " + args.get(0).toString();
                            }

                            iAerservAdsServiceAPI.saveAdErrorAnalyticsEvent(msg);
                            iAerservAdsServiceAPI.startPlayingVideo();
                            break;
                        case VC_READY:
                            vc = (AerServVirtualCurrency) args.get(0);
                            msg = "Virtual Currency PLC has loaded: name=" + vc.getName()
                                    + ", amount=" + vc.getAmount().toString()
                                    + ", buyerName=" + vc.getBuyerName()
                                    + ", buyerPrice=" + vc.getBuyerPrice();
                            break;
                        case AD_IMPRESSION:
                            iAerservAdsServiceAPI.saveAdImpressionAnalyticsEvent();
                            break;
                        case VIDEO_START:
                            iAerservAdsServiceAPI.saveAdStartedAnalyticsEvent();
                            break;
                        case AD_LOADED:
                            iAerservAdsServiceAPI.saveAdLoadedAnalyticsEvent();
                            break;
                        case AD_COMPLETED:
                            iAerservAdsServiceAPI.saveAdCompletedAnalyticsEvent();
                            iAerservAdsServiceAPI.startPlayingVideo();
                            break;
                        case AD_DISMISSED:
                            iAerservAdsServiceAPI.saveAdSkippedAnalyticsEvent();
                            iAerservAdsServiceAPI.startPlayingVideo();
                            break;
                        case VC_REWARDED:
                            vc = (AerServVirtualCurrency) args.get(0);
                            msg = "Virtual Currency PLC has rewarded: name=" + vc.getName()
                                    + ", amount=" + vc.getAmount().toString()
                                    + ", buyerName=" + vc.getBuyerName()
                                    + ", buyerPrice=" + vc.getBuyerPrice();
                            break;
                        default:
                            msg = event.toString() + " event fired with args: " + args.toString();
                    }
                    if(msg != null) {
                        //Toast.makeText(callingContext, msg, Toast.LENGTH_SHORT).show();
                        Log.d("onAerServEvent", msg);
                    }
                }
            });
        }
    };


    public IAerservAdsServiceAPI iAerservAdsServiceAPI;
    public interface IAerservAdsServiceAPI {
        void saveAdRequestAnalyticsEvent();
        void saveAdImpressionAnalyticsEvent();
        void saveAdLoadedAnalyticsEvent();
        void saveAdStartedAnalyticsEvent();
        void saveAdClickedAnalyticsEvent();
        void saveAdSkippedAnalyticsEvent();
        void saveAdErrorAnalyticsEvent(String msg);
        void saveAdCompletedAnalyticsEvent();
        void startPlayingVideo();
    }


}
