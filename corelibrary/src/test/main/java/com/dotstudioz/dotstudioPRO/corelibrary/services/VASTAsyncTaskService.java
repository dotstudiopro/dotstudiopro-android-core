package com.dotstudioz.dotstudioPRO.services.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.dotstudioz.dotstudioPRO.analytics.AnalyticsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.AdDTO;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;
import com.dotstudioz.dotstudioPRO.corelibrary.vastplayer.vast.VASTPlayer;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.net.UnknownHostException;

/**
 * Created by mohsin on 10-10-2016.
 */

public class VASTAsyncTaskService extends AsyncTask<String, String, String>  {

    public IVASTAsyncTaskService iVASTAsyncTaskService;
    private ProgressDialog progressDialog;
    private boolean unknownHostExceptionOccured = false;
    private String errorMessage;
    private VideoInfoDTO currentVideoInfoDTO;
    private String offsetReceived;
    private int duration, position, position_end;
    private String URL_TO_CALL_FOR_AD = "";
    private boolean errorOnHTTP = false;
    private boolean errorOnURL = false;
    private String vastTagURI = "";
    private StringBuilder stringBuilder;
    private VASTPlayer newPlayer;


    public VASTAsyncTaskService(Context ctx, VideoInfoDTO videoInfoDTO, String offsetReceived, int duration, int position, int position_end) {
        this.currentVideoInfoDTO = videoInfoDTO;
        this.offsetReceived = offsetReceived;
        this.duration = duration;
        this.position = position;
        this.position_end = position_end;
        stringBuilder = new StringBuilder();

        if (ctx instanceof VASTAsyncTaskService.IVASTAsyncTaskService) {
            iVASTAsyncTaskService = (VASTAsyncTaskService.IVASTAsyncTaskService) ctx;
        } else
            throw new RuntimeException(ctx.toString()+ " must implement IVASTAsyncTaskService");
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
    }

    private String getOldState() {
        String oldState = "";
        if (offsetReceived.equals(AdDTO.PRE_OFFSET))
            oldState = AnalyticsConstants.OLD_STATE_IDLE;
        else
            oldState = AnalyticsConstants.OLD_STATE_PLAYING;
        return oldState;
    }

    public void onPostExecute(String stringData) {
        super.onPostExecute(stringData);

        // Get file content
        //String vastXMLContent = FileHelper.getFileContent(MainActivity.this, "DSP.xml");
        try {
            if(!errorOnHTTP) {
                if (!TextUtils.isEmpty(stringBuilder.toString())) {
                    // We can create VAST Player and pass the data
                    newPlayer = new VASTPlayer(((Context)iVASTAsyncTaskService),
                            new VASTPlayer.VASTPlayerListener() {

                                @Override
                                public void vastReady() {
                                    iVASTAsyncTaskService.VASTAsyncTaskServiceVastEventHandler(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_IMPRESSION, URL_TO_CALL_FOR_AD, duration, position, position_end, getOldState());
                                    newPlayer.play();
                                }

                                @Override
                                public void vastError(int error) {
                                    String message = "Unable to play VAST Document: Error: " + error;
                                    iVASTAsyncTaskService.VASTAsyncTaskServiceVastError();
                                }

                                @Override
                                public void vastClick() {
                                    iVASTAsyncTaskService.VASTAsyncTaskServiceVastEventHandler(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_CLICK, URL_TO_CALL_FOR_AD, duration, position, position_end, getOldState());
                                }

                                @Override
                                public void vastComplete() {
                                    iVASTAsyncTaskService.VASTAsyncTaskServiceVastEventHandler(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_COMPLETE, URL_TO_CALL_FOR_AD, duration, position, position_end, getOldState());
                                    iVASTAsyncTaskService.VASTAsyncTaskServiceAdComplete();
                                }

                                @Override
                                public void vastDismiss() {
                                    iVASTAsyncTaskService.VASTAsyncTaskServiceVastError();
                                }
                            });

                    newPlayer.loadVideoWithData(stringBuilder.toString());
                    //activity.newPlayer.loadVideoWithUrl(vastTagURI);
                } else {
                }
            } else if(errorOnURL) {
                //Toast.makeText(activity, "Error playing Ad, continuing with video!", Toast.LENGTH_SHORT).show();
                iVASTAsyncTaskService.VASTAsyncTaskServiceVastError();
            } else {
                //Toast.makeText(activity, "Error playing Ad, continuing with video!", Toast.LENGTH_SHORT).show();
                iVASTAsyncTaskService.VASTAsyncTaskServiceVastError();
            }
        } catch(Exception e) {
            //e.printStackTrace();
        }
    }

    public String doInBackground(String... params) {
        iVASTAsyncTaskService.VASTAsyncTaskServiceVastEventHandler(AnalyticsConstants.CATEGORY_ADVERTISING, AnalyticsConstants.EVENT_AD_REQUEST, URL_TO_CALL_FOR_AD, duration, position, position_end, getOldState());

        boolean isPreToBeSkippedFlag = false;
        if(offsetReceived.equals(AdDTO.PRE_OFFSET) && currentVideoInfoDTO.getNoOfPreRollToBePlayed() > 1) {
            int noOfPreToBePlayedNow = (currentVideoInfoDTO.getNoOfPreRollToBeAlreadyPlayed() + 1);
            isPreToBeSkippedFlag = true;
        }

        boolean isPostToBeSkippedFlag = false;
        if(offsetReceived.equals(AdDTO.POST_OFFSET) && currentVideoInfoDTO.getNoOfPostRollToBePlayed() > 1) {
            int noOfPostToBePlayedNow = (currentVideoInfoDTO.getNoOfPostRollToBeAlreadyPlayed() + 1);
            isPostToBeSkippedFlag = true;
        }

        int counter = 0;
        for(int i = 0; i < currentVideoInfoDTO.getAdDTOArrayList().size(); i++) {
            if(currentVideoInfoDTO.getAdDTOArrayList().get(i).getOffset().equals(offsetReceived)) {
                if(isPreToBeSkippedFlag) {
                    if(counter == currentVideoInfoDTO.getNoOfPreRollToBeAlreadyPlayed()) {
                        URL_TO_CALL_FOR_AD = currentVideoInfoDTO.getAdDTOArrayList().get(i).getTag();
                        break;
                    } else {
                        counter++;
                    }
                } else if(isPostToBeSkippedFlag) {
                    if(counter == currentVideoInfoDTO.getNoOfPostRollToBeAlreadyPlayed()) {
                        URL_TO_CALL_FOR_AD = currentVideoInfoDTO.getAdDTOArrayList().get(i).getTag();
                        break;
                    } else {
                        counter++;
                    }
                } else {
                    URL_TO_CALL_FOR_AD = currentVideoInfoDTO.getAdDTOArrayList().get(i).getTag();
                    break;
                }
            }
        }

        if(URL_TO_CALL_FOR_AD == null || URL_TO_CALL_FOR_AD.length() == 0) {
            errorOnHTTP = true;
            errorOnURL = true;
            return "";
        }

        URL_TO_CALL_FOR_AD = "http://shadow01.yumenetworks.com/dynamic_preroll_playlist.vast2xml?domain=1552hCkaKYjg";

        //HttpPost httppost = new HttpPost(ApplicationConstantURL.getInstance().ADSERVER_API+activity.currentVideoInfoDTO.getAndroidZoneID());
        String encodedurl = "";
        String finalString = "";
        try {
            //ORIGINAL_URL_TO_CALL_FOR_AD = "https://adserver.dotstudiopro.com/adserver/www/delivery/fc.php?script=apVideo:vast2&zoneid=1157&cb=1467231997&vid_duration=4731.131131&content_type=video&id=56abbad397f81550287b23c6&keywords=&title=CeeLo%20Green%20%20|%20JOURNEY%20TO%20GNARLS%20BARKLEY&url=http://cdn.dotstudiopro.com/files/company/565e65aa97f815c8590b0592/assets/videos/56abbad397f81550287b23c6/vod/56abbad397f81550287b23c6.m3u8&pageUrl=www.dotstudiopro-565e65aa97f815c8590b0592.com&eov=eov";
            //ORIGINAL_URL_TO_CALL_FOR_AD = "https://adserver.dotstudiopro.com/adserver/www/delivery/fc.php?script=apVideo:vast2&zoneid=1250&cb=1467211957&vid_duration=346&content_type=video&id=5772b4cf97f815690416304a&keywords=tv,starz,power,curtis \"50 cent\" jackson,omari hardwick,lela loren,joseph sikora,courtney kemp agboh,lucy walters,nightclub,drug dealers,new york city,andy bean,adam huss&title=50 Cent, Omari Hardwick & More at the \"Power\" Season 3 Premiere&url=http://cdn.dotstudiopro.com/files/company/55e0f8e597f8157975153ae6/assets/videos/5772b4cf97f815690416304a/vod/5772b4cf97f815690416304a.m3u8&pageUrl=www.dotstudiopro-55e0f8e597f8157975153ae6.com&eov=eov";

            try {
                String hostAndScheme = URL_TO_CALL_FOR_AD.substring(0, URL_TO_CALL_FOR_AD.indexOf("?"));
                String queryParameters = URL_TO_CALL_FOR_AD.substring((URL_TO_CALL_FOR_AD.indexOf("?") + 1), URL_TO_CALL_FOR_AD.length());
                try {
                    encodedurl = URLEncoder.encode(queryParameters, "UTF-8");
                    encodedurl = encodedurl.replaceAll("%3D", "=");
                    encodedurl = encodedurl.replaceAll("%26", "&");
                } catch (Exception e) {
                    //e.printStackTrace();
                }
                finalString = hostAndScheme + "?" + encodedurl;
            } catch(Exception e) { }
        } catch(Exception e) {
            //e.printStackTrace();
        }

        if(URL_TO_CALL_FOR_AD == null || URL_TO_CALL_FOR_AD.length() == 0) {
            errorOnHTTP = true;
            errorOnURL = true;
            return "";
        }

        try {
            //Below hardcoded URL is for testing purpose only, as Ads from prod are not enabled in INDIA
            //URL_TO_CALL_FOR_AD = "http://shadow01.yumenetworks.com/dynamic_preroll_playlist.vast2xml?domain=1552hCkaKYjg";
            HttpClient httpclient = new DefaultHttpClient();
            //HttpPost httppost = new HttpPost(finalString);
            HttpPost httppost = new HttpPost(URL_TO_CALL_FOR_AD);
            try {
                HttpResponse response = httpclient.execute(httppost);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String s = null;
                while ((s = reader.readLine()) != null) {
                    stringBuilder.append(s);
                }

                /*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder;
                InputSource is;
                try {
                    builder = factory.newDocumentBuilder();
                    is = new InputSource(new StringReader(activity.stringBuilder.toString()));
                    Document doc = builder.parse(is);
                    vastTagURI = ((NodeList)doc.getElementsByTagName("VASTAdTagURI")).item(0).getChildNodes().item(0).getNodeValue().toString();
                    System.out.println("vstTagURI:-"+vastTagURI);
                } catch (ParserConfigurationException e) {//e.printStackTrace();
                } catch (SAXException e) {//e.printStackTrace();
                } catch (IOException e) {//e.printStackTrace();
                }

                if(vastTagURI.length() > 0) {
                    //vastTagURI = "http://shadow01.yumenetworks.com/dynamic_preroll_playlist.vast2xml?domain=1552hCkaKYjg";
                    URL url = new URL(vastTagURI);
                    HttpURLConnection connection =
                            (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Accept", "application/xml");

                    InputStream xml = connection.getInputStream();

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(xml);
                    DOMSource domSource = new DOMSource(doc);
                    StringWriter writer = new StringWriter();
                    StreamResult result = new StreamResult(writer);
                    TransformerFactory tf = TransformerFactory.newInstance();
                    Transformer transformer = tf.newTransformer();
                    transformer.transform(domSource, result);
                    System.out.println("XML IN String format is: \n" + writer.toString());
                    System.out.println("ACTUAL_OUTPUT_FROM_VAST_AD_TAG_URI:"+writer.toString());
                    activity.stringBuilder = new StringBuilder();
                    activity.stringBuilder.append(writer.toString());
                }*/

            } catch (UnknownHostException e) {
                //e.printStackTrace();
                unknownHostExceptionOccured = true;
            } catch (ClientProtocolException e) {
                //e.printStackTrace();
            } catch (IOException e) {
                //e.printStackTrace();
            } catch (Exception e) {
                //e.printStackTrace();
            }

        } catch(Exception e) {
            errorOnHTTP = true;
            errorOnURL = false;
            //e.printStackTrace();
        }
        return "";
    }




    public interface IVASTAsyncTaskService {
        void VASTAsyncTaskServiceVastEventHandler(String EVENT_CATEGORY, String EVENT_TYPE, String URL_TO_CALL_FOR_AD, int duration, int position, int position_end, String oldState);
        void VASTAsyncTaskServiceVastError();
        void VASTAsyncTaskServiceAdComplete();
    }
}
