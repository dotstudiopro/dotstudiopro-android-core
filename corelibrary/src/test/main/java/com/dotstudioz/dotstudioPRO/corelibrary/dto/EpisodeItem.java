package com.dotstudioz.dotstudioPRO.models.dto;

import android.graphics.Bitmap;

/**
 * Created by Admin on 03-12-2014.
 */
public class EpisodeItem {

    private String seasonName;
    private String videoThumbnailImageView;
    private Bitmap videoThumbnailBitmap;
    private boolean isSelected;
    private String yearLangCountryDurationString;
    private String videoID;
    private String videoDescription;
    private String seriesTitle;
    private int videoDuration;
    private int videoPausedPoint;

    public EpisodeItem() {
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public EpisodeItem(String seasonName) {
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

    public String getYearLangCountryDurationString() {
        return yearLangCountryDurationString;
    }

    public void setYearLangCountryDurationString(String yearLangCountryDurationString) {
        this.yearLangCountryDurationString = yearLangCountryDurationString;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
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
