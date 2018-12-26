package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by Admin on 22-11-2014.
 */
public class HomePageCategoryItem {

    private String imageURL;
    private String videoId;
    private String movieName;
    private String movieMetaInformation;
    private String movieDescription;



    public String getImageURL() {

        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieMetaInformation() {
        return movieMetaInformation;
    }

    public void setMovieMetaInformation(String movieMetaInformation) {
        this.movieMetaInformation = movieMetaInformation;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }
}
