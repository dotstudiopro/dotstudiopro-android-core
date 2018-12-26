package com.dotstudioz.dotstudioPRO.models.dto;

import java.util.ArrayList;

/**
 * Created by user on 15-06-2016.
 */
public class LastWatchedVideosPairDTO {
    private ArrayList<VideoInfoDTO> continueWatchingDTOList = new ArrayList<>();
    private ArrayList<VideoInfoDTO> watchAgainDTOList = new ArrayList<>();

    public ArrayList<VideoInfoDTO> getContinueWatchingDTOList() {
        return continueWatchingDTOList;
    }

    public void setContinueWatchingDTOList(ArrayList<VideoInfoDTO> continueWatchingDTOList) {
        this.continueWatchingDTOList = continueWatchingDTOList;
    }

    public ArrayList<VideoInfoDTO> getWatchAgainDTOList() {
        return watchAgainDTOList;
    }

    public void setWatchAgainDTOList(ArrayList<VideoInfoDTO> watchAgainDTOList) {
        this.watchAgainDTOList = watchAgainDTOList;
    }
}
