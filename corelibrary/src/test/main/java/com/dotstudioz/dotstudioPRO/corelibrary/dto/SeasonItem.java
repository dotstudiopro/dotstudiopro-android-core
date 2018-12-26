package com.dotstudioz.dotstudioPRO.models.dto;

import android.graphics.Bitmap;

/**
 * Created by Admin on 03-12-2014.
 */
public class SeasonItem {

    private String seasonName;
    private String videoThumbnailImageView;
    private Bitmap videoThumbnailBitmap;
    private boolean isSelected;

    public SeasonItem() {
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public SeasonItem(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getVideoThumbnailImageView() {
        return videoThumbnailImageView;
    }

    public void setVideoThumbnailImageView(String videoThumbnailImageView) {
        this.videoThumbnailImageView = videoThumbnailImageView;
    }

    public Bitmap getVideoThumbnailBitmap() {
        return videoThumbnailBitmap;
    }

    public void setVideoThumbnailBitmap(Bitmap videoThumbnailBitmap) {
        this.videoThumbnailBitmap = videoThumbnailBitmap;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
