package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by Admin on 19-11-2014.
 */
public class SearchResultItem {

    private String title;
    private String seasonInfo;
    private String imageURL;

    public SearchResultItem() {
    }

    public SearchResultItem(String title, String seasonInfo, String imageURL) {
        this.title = title;
        this.seasonInfo = seasonInfo;
        this.imageURL = imageURL;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeasonInfo() {
        return seasonInfo;
    }

    public void setSeasonInfo(String seasonInfo) {
        this.seasonInfo = seasonInfo;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
