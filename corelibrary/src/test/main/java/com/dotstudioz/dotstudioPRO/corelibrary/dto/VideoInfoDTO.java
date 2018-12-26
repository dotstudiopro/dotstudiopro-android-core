package com.dotstudioz.dotstudioPRO.models.dto;

import android.graphics.Bitmap;

import com.dotstudioz.dotstudioPRO.corelibrary.dotstudiovideoplayer.CustomDoubleWidthVideoView;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Admin on 18-04-2015.
 */
public class VideoInfoDTO {
    private String videoID;
    private int videoDuration;
    private String videoTitle = "";
    private String seriesTitle = "";
    private String description = "";
    private String casting = "";
    private String writterDirector = "";
    private int ratingValue;
    private String poster;
    private boolean isYoutubeVideo;
    private String youtubeVideoID;
    private String vodDir;
    private boolean isPreRollToBePlayed;
    private boolean isMidRollToBePlayed;
    private boolean isPostRollToBePlayed;
    private int noOfPreRollToBePlayed;
    private int noOfPreRollToBeAlreadyPlayed;
    private int noOfMidRollToBePlayed;
    private int noOfMidRollToBeAlreadyPlayed;
    private ArrayList<MidRollDTO> midRollDTOArrayList = new ArrayList<>();
    private int noOfPostRollToBePlayed;
    private int noOfPostRollToBeAlreadyPlayed;
    private int[] linearOffset;
    private String videoToPlayURL;
    private boolean isChromeCastVideoToPlayURLAvailable = false;
    private String chromeCastVideoToPlayURL;
    private String channelLink;
    private String noOfComments;
    private String thumb;
    private ArrayList<String> thumbs = new ArrayList<>();
    private String accessValue;
    private boolean isTeaserAvailable;
    private String teaserID;
    private String teaserURL;
    private boolean isVideoAccessEnabled;
    private boolean isGeoBlocked;
    private String geoBlockedDisabledCountries;

    private String videoYear;
    private String videoLanguage;
    private String videoRating;
    private String videoCompanyName;

    private String companyId;
    private String source;

    private String androidZoneID;

    private String slug;

    private String rentalPrice;
    private String rentalPeriod;

    private int videoPausedPoint;

    private boolean isSingleVideo;
    private String channelSpotlightImage;
    private String channelID;
    private String dsproChannelID;
    private String channelTitle;
    private String channelLogo;
    private String channelPoster;

    private String teaserTrailer;
    private String teaserTrailerThumb;

    private ArrayList<AdDTO> adDTOArrayList = new ArrayList<>();
    private ArrayList<CustomFieldDTO> customFieldsArrayList = new ArrayList<>();

    private Bitmap videoImageBitmap;

    public boolean alreadyShownResumePlayback = false;

    private String country;

    private boolean videoLogoWatermarkEnabled;
    private String videoLogoWatermark;
    private float videoLogoWatermarkOpacity;
    private String videoLogoWatermarkLink;

    private boolean isClosedCaptionEnabled = false;
    private String closedCaptionPath;

    private String preRollAdFixIssueVMAP;
    private String midRollAdFixIssueVMAP;
    private String postRollAdFixIssueVMAP;






    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public int getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(int videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCasting() {
        return casting;
    }

    public void setCasting(String casting) {
        this.casting = casting;
    }

    public String getWritterDirector() {
        return writterDirector;
    }

    public void setWritterDirector(String writterDirector) {
        this.writterDirector = writterDirector;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public boolean isYoutubeVideo() {
        return isYoutubeVideo;
    }

    public void setIsYoutubeVideo(boolean isYoutubeVideo) {
        this.isYoutubeVideo = isYoutubeVideo;
    }

    public String getYoutubeVideoID() {
        return youtubeVideoID;
    }

    public void setYoutubeVideoID(String youtubeVideoID) {
        this.youtubeVideoID = youtubeVideoID;
    }

    public String getVodDir() {
        return vodDir;
    }

    public void setVodDir(String vodDir) {
        this.vodDir = vodDir;
    }

    public boolean isPreRollToBePlayed() {
        return isPreRollToBePlayed;
    }

    public void setIsPreRollToBePlayed(boolean isPreRollToBePlayed) {
        this.isPreRollToBePlayed = isPreRollToBePlayed;
    }

    public boolean isMidRollToBePlayed() {
        return isMidRollToBePlayed;
    }

    public void setIsMidRollToBePlayed(boolean isMidRollToBePlayed) {
        this.isMidRollToBePlayed = isMidRollToBePlayed;
    }

    public boolean isPostRollToBePlayed() {
        return isPostRollToBePlayed;
    }

    public void setIsPostRollToBePlayed(boolean isPostRollToBePlayed) {
        this.isPostRollToBePlayed = isPostRollToBePlayed;
    }

    public int getNoOfPreRollToBePlayed() {
        return noOfPreRollToBePlayed;
    }

    public void setNoOfPreRollToBePlayed(int noOfPreRollToBePlayed) {
        this.noOfPreRollToBePlayed = noOfPreRollToBePlayed;
    }

    public int getNoOfPreRollToBeAlreadyPlayed() {
        return noOfPreRollToBeAlreadyPlayed;
    }

    public void setNoOfPreRollToBeAlreadyPlayed(int noOfPreRollToBeAlreadyPlayed) {
        this.noOfPreRollToBeAlreadyPlayed = noOfPreRollToBeAlreadyPlayed;
    }

    public int getNoOfPostRollToBePlayed() {
        return noOfPostRollToBePlayed;
    }

    public void setNoOfPostRollToBePlayed(int noOfPostRollToBePlayed) {
        this.noOfPostRollToBePlayed = noOfPostRollToBePlayed;
    }

    public int getNoOfPostRollToBeAlreadyPlayed() {
        return noOfPostRollToBeAlreadyPlayed;
    }

    public void setNoOfPostRollToBeAlreadyPlayed(int noOfPostRollToBeAlreadyPlayed) {
        this.noOfPostRollToBeAlreadyPlayed = noOfPostRollToBeAlreadyPlayed;
    }

    public String getVideoToPlayURL() {
        return videoToPlayURL;
    }

    public void setVideoToPlayURL(String videoToPlayURL) {
        this.videoToPlayURL = videoToPlayURL;
    }

    public int[] getLinearOffset() {
        return linearOffset;
    }

    public void setLinearOffset(int[] linearOffset) {
        this.linearOffset = linearOffset;
    }

    public String getChannelLink() {
        return channelLink;
    }

    public void setChannelLink(String channelLink) {
        this.channelLink = channelLink;
    }

    public String getNoOfComments() {
        return noOfComments;
    }

    public void setNoOfComments(String noOfComments) {
        this.noOfComments = noOfComments;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public ArrayList<String> getThumbs() {
        return thumbs;
    }

    public void setThumbs(ArrayList<String> thumbs) {
        this.thumbs = thumbs;
    }

    public String getAccessValue() {
        return accessValue;
    }

    public void setAccessValue(String accessValue) {
        this.accessValue = accessValue;
    }

    public void setIsTeaserAvailable(boolean isTeaserAvailable) {
        this.isTeaserAvailable = isTeaserAvailable;
    }

    public boolean isTeaserAvailable() {
        return isTeaserAvailable;
    }

    public String getTeaserID() {
        return teaserID;
    }

    public void setTeaserID(String teaserID) {
        this.teaserID = teaserID;
    }

    public String getTeaserURL() {
        return teaserURL;
    }

    public void setTeaserURL(String teaserURL) {
        this.teaserURL = teaserURL;
    }

    public boolean isVideoAccessEnabled() {
        return isVideoAccessEnabled;
    }

    public void setIsVideoAccessEnabled(boolean isVideoAccessEnabled) {
        this.isVideoAccessEnabled = isVideoAccessEnabled;
    }

    public boolean isGeoBlocked() {
        return isGeoBlocked;
    }

    public void setIsGeoBlocked(boolean isGeoBlocked) {
        this.isGeoBlocked = isGeoBlocked;
    }

    public String getGeoBlockedDisabledCountries() {
        return geoBlockedDisabledCountries;
    }

    public void setGeoBlockedDisabledCountries(String geoBlockedDisabledCountries) {
        this.geoBlockedDisabledCountries = geoBlockedDisabledCountries;
    }

    public String getVideoYear() {
        return videoYear;
    }

    public void setVideoYear(String videoYear) {
        this.videoYear = videoYear;
    }

    public String getVideoLanguage() {
        return videoLanguage;
    }

    public void setVideoLanguage(String videoLanguage) {
        this.videoLanguage = videoLanguage;
    }

    public String getVideoRating() {
        return videoRating;
    }

    public void setVideoRating(String videoRating) {
        this.videoRating = videoRating;
    }

    public String getVideoCompanyName() {
        return videoCompanyName;
    }

    public void setVideoCompanyName(String videoCompanyName) {
        this.videoCompanyName = videoCompanyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAndroidZoneID() {
        return androidZoneID;
    }

    public void setAndroidZoneID(String androidZoneID) {
        this.androidZoneID = androidZoneID;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setYoutubeVideo(boolean youtubeVideo) {
        isYoutubeVideo = youtubeVideo;
    }

    public void setPreRollToBePlayed(boolean preRollToBePlayed) {
        isPreRollToBePlayed = preRollToBePlayed;
    }

    public void setMidRollToBePlayed(boolean midRollToBePlayed) {
        isMidRollToBePlayed = midRollToBePlayed;
    }

    public void setPostRollToBePlayed(boolean postRollToBePlayed) {
        isPostRollToBePlayed = postRollToBePlayed;
    }

    public void setTeaserAvailable(boolean teaserAvailable) {
        isTeaserAvailable = teaserAvailable;
    }

    public void setVideoAccessEnabled(boolean videoAccessEnabled) {
        isVideoAccessEnabled = videoAccessEnabled;
    }

    public String getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public int getVideoPausedPoint() {
        return videoPausedPoint;
    }

    public void setVideoPausedPoint(int videoPausedPoint) {
        this.videoPausedPoint = videoPausedPoint;
    }

    public boolean isSingleVideo() {
        return isSingleVideo;
    }

    public void setSingleVideo(boolean singleVideo) {
        isSingleVideo = singleVideo;
    }

    public String getChannelSpotlightImage() {
        return channelSpotlightImage;
    }

    public void setChannelSpotlightImage(String channelSpotlightImage) {
        this.channelSpotlightImage = channelSpotlightImage;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getDsproChannelID() {
        return dsproChannelID;
    }

    public void setDsproChannelID(String dsproChannelID) {
        this.dsproChannelID = dsproChannelID;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getChannelLogo() {
        return channelLogo;
    }

    public void setChannelLogo(String channelLogo) {
        this.channelLogo = channelLogo;
    }

    public String getChannelPoster() {
        return channelPoster;
    }

    public void setChannelPoster(String channelPoster) {
        this.channelPoster = channelPoster;
    }

    public ArrayList<AdDTO> getAdDTOArrayList() {
        return adDTOArrayList;
    }

    public void setAdDTOArrayList(ArrayList<AdDTO> adDTOArrayList) {
        this.adDTOArrayList = adDTOArrayList;
    }

    public ArrayList<CustomFieldDTO> getCustomFieldsArrayList() {
        return customFieldsArrayList;
    }

    public void setCustomFieldsArrayList(ArrayList<CustomFieldDTO> customFieldsArrayList) {
        this.customFieldsArrayList = customFieldsArrayList;
    }

    public Bitmap getVideoImageBitmap() {
        return videoImageBitmap;
    }

    public void setVideoImageBitmap(Bitmap videoImageBitmap) {
        this.videoImageBitmap = videoImageBitmap;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isVideoLogoWatermarkEnabled() {
        return videoLogoWatermarkEnabled;
    }

    public void setVideoLogoWatermarkEnabled(boolean videoLogoWatermarkEnabled) {
        this.videoLogoWatermarkEnabled = videoLogoWatermarkEnabled;
    }

    public String getVideoLogoWatermark() {
        return videoLogoWatermark;
    }

    public void setVideoLogoWatermark(String videoLogoWatermark) {
        this.videoLogoWatermark = videoLogoWatermark;
    }

    public float getVideoLogoWatermarkOpacity() {
        return videoLogoWatermarkOpacity;
    }

    public void setVideoLogoWatermarkOpacity(float videoLogoWatermarkOpacity) {
        this.videoLogoWatermarkOpacity = videoLogoWatermarkOpacity;
    }

    public String getVideoLogoWatermarkLink() {
        return videoLogoWatermarkLink;
    }

    public void setVideoLogoWatermarkLink(String videoLogoWatermarkLink) {
        this.videoLogoWatermarkLink = videoLogoWatermarkLink;
    }

    public boolean isClosedCaptionEnabled() {
        return isClosedCaptionEnabled;
    }

    public void setClosedCaptionEnabled(boolean closedCaptionEnabled) {
        isClosedCaptionEnabled = closedCaptionEnabled;
    }

    public String getClosedCaptionPath() {
        return closedCaptionPath;
    }

    public void setClosedCaptionPath(String closedCaptionPath) {
        this.closedCaptionPath = closedCaptionPath;
    }

    public boolean isChromeCastVideoToPlayURLAvailable() {
        return isChromeCastVideoToPlayURLAvailable;
    }

    public void setChromeCastVideoToPlayURLAvailable(boolean chromeCastVideoToPlayURLAvailable) {
        isChromeCastVideoToPlayURLAvailable = chromeCastVideoToPlayURLAvailable;
    }

    public String getChromeCastVideoToPlayURL() {
        return chromeCastVideoToPlayURL;
    }

    public void setChromeCastVideoToPlayURL(String chromeCastVideoToPlayURL) {
        this.chromeCastVideoToPlayURL = chromeCastVideoToPlayURL;
    }

    public String getPreRollAdFixIssueVMAP() {
        return preRollAdFixIssueVMAP;
    }

    public void setPreRollAdFixIssueVMAP(String preRollAdFixIssueVMAP) {
        this.preRollAdFixIssueVMAP = preRollAdFixIssueVMAP;
    }

    public String getMidRollAdFixIssueVMAP() {
        return midRollAdFixIssueVMAP;
    }

    public void setMidRollAdFixIssueVMAP(String midRollAdFixIssueVMAP) {
        this.midRollAdFixIssueVMAP = midRollAdFixIssueVMAP;
    }

    public String getPostRollAdFixIssueVMAP() {
        return postRollAdFixIssueVMAP;
    }

    public void setPostRollAdFixIssueVMAP(String postRollAdFixIssueVMAP) {
        this.postRollAdFixIssueVMAP = postRollAdFixIssueVMAP;
    }

    public ArrayList<MidRollDTO> getMidRollDTOArrayList() {
        return midRollDTOArrayList;
    }

    public void setMidRollDTOArrayList(ArrayList<MidRollDTO> midRollDTOArrayList) {
        this.midRollDTOArrayList = midRollDTOArrayList;
    }

    public int getNoOfMidRollToBePlayed() {
        return noOfMidRollToBePlayed;
    }

    public void setNoOfMidRollToBePlayed(int noOfMidRollToBePlayed) {
        this.noOfMidRollToBePlayed = noOfMidRollToBePlayed;
    }

    public int getNoOfMidRollToBeAlreadyPlayed() {
        return noOfMidRollToBeAlreadyPlayed;
    }

    public void setNoOfMidRollToBeAlreadyPlayed(int noOfMidRollToBeAlreadyPlayed) {
        this.noOfMidRollToBeAlreadyPlayed = noOfMidRollToBeAlreadyPlayed;
    }

    public String getTeaserTrailer() {
        return teaserTrailer;
    }

    public void setTeaserTrailer(String teaserTrailer) {
        this.teaserTrailer = teaserTrailer;
    }

    public String getTeaserTrailerThumb() {
        return teaserTrailerThumb;
    }

    public void setTeaserTrailerThumb(String teaserTrailerThumb) {
        this.teaserTrailerThumb = teaserTrailerThumb;
    }
}
