package com.dotstudioz.dotstudioPRO.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 15-04-2015.
 */
public class SpotLightCategoriesDTO implements Serializable {
    private String categoryName;
    private String categoryValue;
    private String categoryId;
    private String dsproChannelId;
    private String companyId;
    private boolean enabled;
    private boolean homepage;
    private boolean platform;
    private String poster;
    private String wallpaper;
    private String spotlightPoster;
    private int imageHeight;
    private int imageWidth;
    private boolean menu;
    private String categorySlug;
    private int categoryWeight;
    private String path;
    private String channels;
    private String playlistID;
    private boolean isParentChannel;
    private boolean hasChildCategory;
    private int parentId;
    private ArrayList<SpotLightCategoriesDTO> childrenSpotLightCategoriesDTOList = new ArrayList<>();

    private ArrayList<CustomFieldDTO> customFieldDTOArrayList = new ArrayList<>();

    private List<SpotLightChannelDTO> spotLightChannelDTOList = new ArrayList<SpotLightChannelDTO>();

    private List<VideoInfoDTO> videoInfoDTOList = new ArrayList<VideoInfoDTO>();

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    public List<SpotLightChannelDTO> getSpotLightChannelDTOList() {
        return spotLightChannelDTOList;
    }

    public void setSpotLightChannelDTOList(List<SpotLightChannelDTO> spotLightChannelDTOList) {
        this.spotLightChannelDTOList = spotLightChannelDTOList;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isHomepage() {
        return homepage;
    }

    public void setHomepage(boolean homepage) {
        this.homepage = homepage;
    }

    public boolean isPlatform() {
        return platform;
    }

    public void setPlatform(boolean platform) {
        this.platform = platform;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(String wallpaper) {
        this.wallpaper = wallpaper;
    }

    public String getSpotlightPoster() {
        return spotlightPoster;
    }

    public void setSpotlightPoster(String spotlightPoster) {
        this.spotlightPoster = spotlightPoster;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public boolean isMenu() {
        return menu;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public int getCategoryWeight() {
        return categoryWeight;
    }

    public void setCategoryWeight(int categoryWeight) {
        this.categoryWeight = categoryWeight;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDsproChannelId() {
        return dsproChannelId;
    }

    public void setDsproChannelId(String dsproChannelId) {
        this.dsproChannelId = dsproChannelId;
    }

    public List<VideoInfoDTO> getVideoInfoDTOList() {
        return videoInfoDTOList;
    }

    public void setVideoInfoDTOList(List<VideoInfoDTO> videoInfoDTOList) {
        this.videoInfoDTOList = videoInfoDTOList;
    }

    public String getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(String playlistID) {
        this.playlistID = playlistID;
    }

    public boolean isParentChannel() {
        return isParentChannel;
    }

    public void setParentChannel(boolean parentChannel) {
        isParentChannel = parentChannel;
    }

    public boolean isHasChildCategory() {
        return hasChildCategory;
    }

    public void setHasChildCategory(boolean hasChildCategory) {
        this.hasChildCategory = hasChildCategory;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public ArrayList<SpotLightCategoriesDTO> getChildrenSpotLightCategoriesDTOList() {
        return childrenSpotLightCategoriesDTOList;
    }

    public void setChildrenSpotLightCategoriesDTOList(ArrayList<SpotLightCategoriesDTO> childrenSpotLightCategoriesDTOList) {
        this.childrenSpotLightCategoriesDTOList = childrenSpotLightCategoriesDTOList;
    }

    public ArrayList<CustomFieldDTO> getCustomFieldDTOArrayList() {
        return customFieldDTOArrayList;
    }

    public void setCustomFieldDTOArrayList(ArrayList<CustomFieldDTO> customFieldDTOArrayList) {
        this.customFieldDTOArrayList = customFieldDTOArrayList;
    }
}
