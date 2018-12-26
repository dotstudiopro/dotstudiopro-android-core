package com.dotstudioz.dotstudioPRO.models.dto;

public class ChannelMyListDTO {

    private String id;
    private String title;
    private String companyId;
    private String poster;
    private String spotlightPoster;
    private String slug;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
}
