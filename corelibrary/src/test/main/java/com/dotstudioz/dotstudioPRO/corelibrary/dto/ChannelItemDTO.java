package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by Admin on 30-06-2015.
 */
public class ChannelItemDTO {

    private String videoImageURL;
    private String videoName;
    private int imageHeight;
    private int imageWidth;

    public String getVideoImageURL() {
        return videoImageURL;
    }

    public void setVideoImageURL(String videoImageURL) {
        this.videoImageURL = videoImageURL;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
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
}
