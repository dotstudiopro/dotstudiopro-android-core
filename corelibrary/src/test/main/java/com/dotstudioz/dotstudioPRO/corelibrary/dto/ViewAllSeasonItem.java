package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by Admin on 03-12-2014.
 */
public class ViewAllSeasonItem {

    private String seasonName;
    private int rating;
    private String seasonImage;

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getSeasonImage() {
        return seasonImage;
    }

    public void setSeasonImage(String seasonImage) {
        this.seasonImage = seasonImage;
    }
}
