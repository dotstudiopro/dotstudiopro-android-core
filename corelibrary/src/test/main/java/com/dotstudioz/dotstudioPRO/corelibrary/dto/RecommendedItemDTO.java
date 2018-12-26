package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by user on 15-06-2016.
 */
public class RecommendedItemDTO {
    private String id;
    private String type;
    private String thumb;
    private String poster;
    private String spotLightPoster;
    private String title;
    private String seriesTitle;
    private int videoDuration;
    private int videoPausedPoint;
    private String slug;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSpotLightPoster() {
        return spotLightPoster;
    }

    public void setSpotLightPoster(String spotLightPoster) {
        this.spotLightPoster = spotLightPoster;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
