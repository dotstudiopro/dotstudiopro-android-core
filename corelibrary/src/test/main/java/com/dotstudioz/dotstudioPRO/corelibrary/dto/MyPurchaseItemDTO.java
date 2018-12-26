package com.dotstudioz.dotstudioPRO.models.dto;

import java.util.Comparator;

/**
 * Created by Admin on 17-06-2015.
 */
public class MyPurchaseItemDTO {

    private String videoID;
    private String title;
    private String imageURL;
    private boolean status;
    private double videoPrice;
    private String videoPurchaseDate;
    private String category;
    private String channel;

    public double getVideoPrice() {
        return videoPrice;
    }

    public void setVideoPrice(double videoPrice) {
        this.videoPrice = videoPrice;
    }

    public String getVideoPurchaseDate() {
        return videoPurchaseDate;
    }

    public void setVideoPurchaseDate(String videoPurchaseDate) {
        this.videoPurchaseDate = videoPurchaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }



    public static Comparator<MyPurchaseItemDTO> COMPARE_BY_STATUS = new Comparator<MyPurchaseItemDTO>() {
        @Override
        public int compare(MyPurchaseItemDTO lhs, MyPurchaseItemDTO rhs) {
            return (lhs.status == rhs.status ? 0 : (rhs.status ? 1 : -1));
        }
    };
}
