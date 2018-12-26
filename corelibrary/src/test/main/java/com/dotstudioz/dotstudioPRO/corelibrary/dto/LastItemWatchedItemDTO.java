package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by user on 15-06-2016.
 */
public class LastItemWatchedItemDTO {
    private String videoID;
    private String thumb;
    private String title;
    private String seriesTitle;
    private int videoDuration;
    private int videoPausedPoint;

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
