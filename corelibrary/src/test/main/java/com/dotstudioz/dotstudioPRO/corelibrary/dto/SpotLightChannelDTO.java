
package com.dotstudioz.dotstudioPRO.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Admin on 15-04-2015.
 */
public class SpotLightChannelDTO implements Serializable {
    private String id;
    private String dspro_id;
    private boolean isProduct;
    private String image;
    private String poster;
    private String spotlightImage;
    private String link;
    private String title;
    private String slug;
    private String video;
    private String channelCategory;
    private String channelLogo;
    private String year;
    private String language;
    private String channelRating;
    private String company;
    private String spotlight_company_id;
    private String country;
    private String channelDescription;
    private List<String> categories = new ArrayList<String>();
    private List<String> playlist = new ArrayList<String>();
    private List<String> videoCompanyIdList = new ArrayList<String>();

    private boolean isSeasonsPresent = false;
    private int numberOfSeasons;
    private List<SpotLightChannelDTO> seasonsList = new ArrayList<SpotLightChannelDTO>();

    private List<VideoInfoDTO> videoInfoDTOList = new ArrayList<VideoInfoDTO>();

    private int categoryWeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDspro_id() {
        return dspro_id;
    }

    public void setDspro_id(String dspro_id) {
        this.dspro_id = dspro_id;
    }

    public boolean isProduct() {
        return isProduct;
    }

    public void setProduct(boolean product) {
        isProduct = product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSpotlightImage() {
        return spotlightImage;
    }

    public void setSpotlightImage(String spotlightImage) {
        this.spotlightImage = spotlightImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getChannelCategory() {
        return channelCategory;
    }

    public void setChannelCategory(String channelCategory) {
        this.channelCategory = channelCategory;
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    public void setChannelLogo(String channelLogo) {
        this.channelLogo = channelLogo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getChannelRating() {
        return channelRating;
    }

    public void setChannelRating(String channelRating) {
        this.channelRating = channelRating;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpotlight_company_id() {
        return spotlight_company_id;
    }

    public void setSpotlight_company_id(String spotlight_company_id) {
        this.spotlight_company_id = spotlight_company_id;
    }

    public void setSeasonsPresent(boolean seasonsPresent) {
        isSeasonsPresent = seasonsPresent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<String> playlist) {
        this.playlist = playlist;
    }

    public boolean isSeasonsPresent() {
        return isSeasonsPresent;
    }

    public void setIsSeasonsPresent(boolean isSeasonsPresent) {
        this.isSeasonsPresent = isSeasonsPresent;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public List<SpotLightChannelDTO> getSeasonsList() {
        return seasonsList;
    }

    public void setSeasonsList(List<SpotLightChannelDTO> seasonsList) {
        this.seasonsList = seasonsList;
    }

    public List<String> getVideoCompanyIdList() {
        return videoCompanyIdList;
    }

    public void setVideoCompanyIdList(List<String> videoCompanyIdList) {
        this.videoCompanyIdList = videoCompanyIdList;
    }

    public int getCategoryWeight() {
        return categoryWeight;
    }

    public void setCategoryWeight(int categoryWeight) {
        this.categoryWeight = categoryWeight;
    }

    public List<VideoInfoDTO> getVideoInfoDTOList() {
        return videoInfoDTOList;
    }

    public void setVideoInfoDTOList(List<VideoInfoDTO> videoInfoDTOList) {
        this.videoInfoDTOList = videoInfoDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpotLightChannelDTO that = (SpotLightChannelDTO) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
