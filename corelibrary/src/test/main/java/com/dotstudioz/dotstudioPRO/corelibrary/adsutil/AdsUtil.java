package com.dotstudioz.dotstudioPRO.corelibrary.adsutil;

import com.dotstudioz.dotstudioPRO.models.dto.AdDTO;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by mohsin on 09-10-2016.
 */
public class AdsUtil {
    private static AdsUtil ourInstance = new AdsUtil();

    public static AdsUtil getInstance() {
        return ourInstance;
    }

    private AdsUtil() {
    }

    public int[] getLinearOffsetInt(VideoInfoDTO videoInfoDTO) {
        int[] linearOffsetInt;
        int numberOfMidRolls = 0;
        for(int i = 0; i < videoInfoDTO.getAdDTOArrayList().size(); i++) {
            if(!videoInfoDTO.getAdDTOArrayList().get(i).getOffset().equals(AdDTO.PRE_OFFSET) &&
                    !videoInfoDTO.getAdDTOArrayList().get(i).getOffset().equals(AdDTO.POST_OFFSET)) {
                numberOfMidRolls++;
            }
        }
        linearOffsetInt = new int[numberOfMidRolls];
        int midsAddedSoFar = 0;
        for(int i = 0; i < videoInfoDTO.getAdDTOArrayList().size(); i++) {
            if(!videoInfoDTO.getAdDTOArrayList().get(i).getOffset().equals(AdDTO.PRE_OFFSET) &&
                    !videoInfoDTO.getAdDTOArrayList().get(i).getOffset().equals(AdDTO.POST_OFFSET)) {
                linearOffsetInt[midsAddedSoFar] = Integer.parseInt(videoInfoDTO.getAdDTOArrayList().get(i).getOffset());
                midsAddedSoFar++;
            }
        }
        Arrays.sort(linearOffsetInt);

        return linearOffsetInt;
    }

    public String getCacheChangedAdTag(String adTag) {
        if(adTag != null) {
            if(adTag != null) {
                if(adTag.indexOf("cb=") > 0) {
                    int indexOfCB = adTag.indexOf("cb=");
                    int indexOfNextChar = (adTag.indexOf("cb=")+1);
                    int indexOfLastChar = 0;
                    Character compareChar = new Character('&');
                    boolean tempFlag = false;
                    while(!tempFlag) {
                        if(indexOfNextChar < adTag.length()) {
                            Character nextChar = adTag.charAt(indexOfNextChar);
                            if (nextChar.compareTo(compareChar) == 0) {
                                indexOfLastChar = indexOfNextChar;
                                tempFlag = true;
                            } else {
                                indexOfNextChar++;
                            }
                        } else {
                            indexOfLastChar = indexOfNextChar;
                            tempFlag = true;
                        }
                    }
                    String stringToReplace = ("&"+adTag.substring(indexOfCB, indexOfLastChar));
                    Date tempDate = new Date();
                    double newCB = ((int) tempDate.getTime()) * (Math.floor((Math.random() * 10000) + 1));
                    int newCBInt = (int) newCB;

                    adTag = adTag.replaceAll(stringToReplace, "&cb="+newCBInt);
                    return adTag;
                    //System.out.println("adTag.substring(indexOfCB, indexOfLastChar)=>&"+adTag.substring(indexOfCB, indexOfLastChar));
                } else {
                    return adTag;
                }
            }
        }

        return "";
    }
}
