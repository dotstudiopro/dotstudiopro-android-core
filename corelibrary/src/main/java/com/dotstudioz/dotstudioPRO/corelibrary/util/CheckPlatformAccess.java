package com.dotstudioz.dotstudioPRO.corelibrary.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CheckPlatformAccess {
    public static Set<String> availablePlatForms = null;
    public static boolean hasPlatformAccess = false;


    public CheckPlatformAccess() {
        availablePlatForms =  new HashSet<String>();
        hasPlatformAccess = false;
    }

    /**
     *
     * @param channelObject ==> Channel object to get the list of platforms under the channel category
     * @param platform ==> device platform to check whether the channel has access for this device.
     */
    public void checkPlatform(JSONObject channelObject, String platform)
    {
          try {
            if (channelObject.has("categories")) {
                for (int i = 0; i < channelObject.getJSONArray("categories").length(); i++) {
                    JSONObject categoriesObject = (JSONObject) channelObject.getJSONArray("categories").get(i);
                    if (categoriesObject.has("platforms")) {
                        for (int k = 0; k < categoriesObject.getJSONArray("platforms").length(); k++) {
                            JSONObject platformsObject = (JSONObject) categoriesObject.getJSONArray("platforms").get(k);
                           // if (platformsObject.has(platform)) {
                              checkAvailablePlatform(platformsObject,platform);
                           // }

                        }
                    }
                }
            }

        }catch (Exception e){

        }
    }

    /**
     *
     * @param categoriesArray ==> Channel object to get the list of platforms under the channel category
     * @param platform ==> device platform to check whether the channel has access for this device.
     */
    public void checkPlatformWithCategoriesArray(JSONArray categoriesArray, String platform)
    {
        try {

                for (int i = 0; i < categoriesArray.length(); i++) {
                    JSONObject categoriesObject = (JSONObject) categoriesArray.get(i);
                    if (categoriesObject.has("platforms")) {
                        for (int k = 0; k < categoriesObject.getJSONArray("platforms").length(); k++) {
                            JSONObject platformsObject = (JSONObject) categoriesObject.getJSONArray("platforms").get(k);
                            //if (platformsObject.has(platform)) {
                                checkAvailablePlatform(platformsObject,platform);
                           // }

                        }
                    }
                }


        }catch (Exception e){

        }
    }
    /**
     *
     * @param platformsObject ==> Platform object for single channel
     * @param platform  ==> current device platform
     * @throws Exception ==> exception
     */

    public void checkAvailablePlatform(JSONObject platformsObject, String platform) throws Exception {

        Iterator<String> itr = platformsObject.keys();
        while(itr.hasNext()){
            String key= itr.next();
            Object value = platformsObject.get(key);
             if(value instanceof String) {
               if(value.toString().equalsIgnoreCase("true") && !key.equalsIgnoreCase(platform)) {
                    // result.append(URLEncoder.encode(key, "UTF-8"));
                   //"roku_mrss":"false","ios":"false","xumo_mrss":"false","andriod":"false","roku":"false","apple_tv":"false","amazon_fire":"true","vewd_mrss":"false","android_TV":"true"
                   boolean flag = false;
                   if(key.equalsIgnoreCase("roku_mrss")) {
                       availablePlatForms.add("Roku MRSS");
                       flag = true;
                   } else if(key.equalsIgnoreCase("ios")) {
                       if(availablePlatForms.size() > 0)
                           availablePlatForms.add(" iOS");
                       else
                           availablePlatForms.add("iOS");
                       flag = true;
                   } else if(key.equalsIgnoreCase("xumo_mrss")) {
                       if(availablePlatForms.size() > 0)
                           availablePlatForms.add(" Xumo MRSS");
                       else
                           availablePlatForms.add("Xumo MRSS");
                       flag = true;
                   } else if(key.equalsIgnoreCase("andriod")) {
                       if(availablePlatForms.size() > 0)
                           availablePlatForms.add(" Android");
                       else
                           availablePlatForms.add("Android");
                       flag = true;
                   } else if(key.equalsIgnoreCase("roku")) {
                       if(availablePlatForms.size() > 0)
                           availablePlatForms.add(" Roku TV");
                       else
                           availablePlatForms.add("Roku TV");
                       flag = true;
                   } else if(key.equalsIgnoreCase("apple_tv")) {
                       if(availablePlatForms.size() > 0)
                           availablePlatForms.add(" Apple TV");
                       else
                           availablePlatForms.add("Apple TV");
                       flag = true;
                   } else if(key.equalsIgnoreCase("amazon_fire")) {
                       if(availablePlatForms.size() > 0)
                           availablePlatForms.add(" Amazon Fire TV");
                       else
                           availablePlatForms.add("Amazon Fire TV");
                       flag = true;
                   } else if(key.equalsIgnoreCase("vewd_mrss")) {
                       if(availablePlatForms.size() > 0)
                           availablePlatForms.add(" Vewd MRSS");
                       else
                           availablePlatForms.add("Vewd MRSS");
                       flag = true;
                   } else if(key.equalsIgnoreCase("android_TV")) {
                       if(availablePlatForms.size() > 0)
                           availablePlatForms.add(" Android TV");
                       else
                           availablePlatForms.add("Android TV");
                       flag = true;
                   } else if(key.equalsIgnoreCase("website")) {
                       if(availablePlatForms.size() > 0)
                           availablePlatForms.add(" Website");
                       else
                           availablePlatForms.add("Website");
                       flag = true;
                   }

                   if(!flag) {
                       if(availablePlatForms.size() > 0)
                           availablePlatForms.add(" "+key);
                       else
                           availablePlatForms.add(key);
                   }
                }
                if (platformsObject.getString(platform).equalsIgnoreCase("true")) {
                    hasPlatformAccess = true;
                }
            } else  if(value instanceof Boolean) {
                if(((Boolean) value).booleanValue()) {
                    // result.append(URLEncoder.encode(key, "UTF-8"));
                    availablePlatForms.add(key);
                }
                if (platformsObject.getBoolean(platform)) {
                    hasPlatformAccess = true;
                }
            }

        }

    }
}
