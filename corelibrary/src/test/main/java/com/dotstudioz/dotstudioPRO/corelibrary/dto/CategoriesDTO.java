package com.dotstudioz.dotstudioPRO.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 17-04-2015.
 */
public class CategoriesDTO implements Serializable {
    private String categoryName;
    private String categorySlug;
    private String categoryValue;
    private String categoryPoster;
    private String spotlightPoster;
    private String wallpaper;
    private String channelID;
    private String dsproChannelID;
    private String channelName;
    private String channelCasting;
    private String channelLink;
    private String channelLogo;
    private String videoID;
    private List<String> videosList = new ArrayList<String>();
    private String channelImage;
    private String channelSpotlightImage;
    private String channelSlug;
    private boolean hasSeason = false;
    private String spotlightCompanyId;
    private boolean isVideoList = false;
    private ArrayList<VideoInfoDTO> videoInfoDTOArrayList = new ArrayList<>();

    private int videoDuration = 0;
    private int videoPausedPoint = 0;



    public String getCategoryName() {

        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    public String getCategoryPoster() {
        return categoryPoster;
    }

    public void setCategoryPoster(String categoryPoster) {
        this.categoryPoster = categoryPoster;
    }

    public String getSpotlightPoster() {
        return spotlightPoster;
    }

    public void setSpotlightPoster(String spotlightPoster) {
        this.spotlightPoster = spotlightPoster;
    }

    public String getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(String wallpaper) {
        this.wallpaper = wallpaper;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getDsproChannelID() {
        return dsproChannelID;
    }

    public void setDsproChannelID(String dsproChannelID) {
        this.dsproChannelID = dsproChannelID;
    }

    public String getChannelImage() {
        return channelImage;
    }

    public void setChannelImage(String channelImage) {
        this.channelImage = channelImage;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelCasting() {
        return channelCasting;
    }

    public void setChannelCasting(String channelCasting) {
        this.channelCasting = channelCasting;
    }

    public String getChannelSpotlightImage() {
        return channelSpotlightImage;
    }

    public void setChannelSpotlightImage(String channelSpotlightImage) {
        this.channelSpotlightImage = channelSpotlightImage;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public List<String> getVideosList() {
        return videosList;
    }

    public void setVideosList(List<String> videosList) {
        this.videosList = videosList;
    }

    public String getChannelLink() {
        return channelLink;
    }

    public void setChannelLink(String channelLink) {
        this.channelLink = channelLink;
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    public void setChannelLogo(String channelLogo) {
        this.channelLogo = channelLogo;
    }

    public String getChannelSlug() {
        return channelSlug;
    }

    public void setChannelSlug(String channelSlug) {
        this.channelSlug = channelSlug;
    }

    public boolean isHasSeason() {
        return hasSeason;
    }

    public void setHasSeason(boolean hasSeason) {
        this.hasSeason = hasSeason;
    }

    public String getSpotlightCompanyId() {
        return spotlightCompanyId;
    }

    public void setSpotlightCompanyId(String spotlightCompanyId) {
        this.spotlightCompanyId = spotlightCompanyId;
    }

    public boolean isVideoList() {
        return isVideoList;
    }

    public void setVideoList(boolean videoList) {
        isVideoList = videoList;
    }

    public ArrayList<VideoInfoDTO> getVideoInfoDTOArrayList() {
        return videoInfoDTOArrayList;
    }

    public void setVideoInfoDTOArrayList(ArrayList<VideoInfoDTO> videoInfoDTOArrayList) {
        this.videoInfoDTOArrayList = videoInfoDTOArrayList;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(int videoDuration) {
        this.videoDuration = videoDuration;
    }

    public int getVideoPausedPoint() {
        return videoPausedPoint;
    }

    public void setVideoPausedPoint(int videoPausedPoint) {
        this.videoPausedPoint = videoPausedPoint;
    }
}
