package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by Admin on 13-08-2015.
 */
public class SearchResultDTO {
    private String id;
    private String poster;
    private String spotlightPoster;
    private String slug;
    private String title;
    private String seriesTitle;
    private String casting;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSpotlightPoster() {
        return spotlightPoster;
    }

    public void setSpotlightPoster(String spotlightPoster) {
        this.spotlightPoster = spotlightPoster;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

    public String getCasting() {
        return casting;
    }

    public void setCasting(String casting) {
        this.casting = casting;
    }
}
