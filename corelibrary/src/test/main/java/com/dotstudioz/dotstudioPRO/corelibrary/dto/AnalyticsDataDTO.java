package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by Admin on 15-09-2015.
 */
public class AnalyticsDataDTO {

    private String customer;
    private String email;
    private GeoLocationDTO geolocation;
    private String appCodeName;
    private String appName;
    private String appVersion;
    private String platform;//type of device - mobile, tablet
    private String product;//spotlight
    private String productSub;
    private String os;//android, ios
    private String device;//samsung, iPhone, LG
    private String manufacturer;
    private String model;


    private String event;
    private String analytics_user;
    private String pageType;// spotlight-ios, spotlight-android, roku, timeline, channel
    private String slug;
    private String channelId;
    private String videoId;
    private String companyId;
    private String currentQuality;
    private String duration;
    private String fullscreen;
    private String position;
    private String volume;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GeoLocationDTO getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeoLocationDTO geolocation) {
        this.geolocation = geolocation;
    }

    public String getAppCodeName() {
        return appCodeName;
    }

    public void setAppCodeName(String appCodeName) {
        this.appCodeName = appCodeName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductSub() {
        return productSub;
    }

    public void setProductSub(String productSub) {
        this.productSub = productSub;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAnalytics_user() {
        return analytics_user;
    }

    public void setAnalytics_user(String analytics_user) {
        this.analytics_user = analytics_user;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCurrentQuality() {
        return currentQuality;
    }

    public void setCurrentQuality(String currentQuality) {
        this.currentQuality = currentQuality;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(String fullscreen) {
        this.fullscreen = fullscreen;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
