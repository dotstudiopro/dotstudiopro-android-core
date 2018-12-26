package com.dotstudioz.dotstudioPRO.dsplayer.ima;

/**
 * Information about a video playlist item that the user will select in a playlist.
 */
public final class VideoItem {

    private int mThumbnailResourceId;
    private String mTitle;
    private String mVideoUrl;
    private String mAdTagUrl;
    private boolean mIsVmap;

    public VideoItem(String videoUrl, String title, String adTagUrl, int thumbnailResourceId,
                     boolean isVmap) {
        super();
        mThumbnailResourceId = thumbnailResourceId;
        mTitle = title;
        mAdTagUrl = adTagUrl;
        mVideoUrl = videoUrl;
        mIsVmap = isVmap;
    }

    /**
     * Returns the video thumbnail image resource.
     */
    public int getImageResource() {
        return mThumbnailResourceId;
    }

    /**
     * Returns the title of the video item.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the URL of the content video.
     */
    public String getVideoUrl() {
        return mVideoUrl;
    }

    /**
     * Returns the ad tag for the video.
     */
    public String getAdTagUrl() {
        return mAdTagUrl;
    }

    /**
     * Returns if the ad is VMAP.
     */
    public boolean getIsVmap() {
        return mIsVmap;
    }

    public void setmThumbnailResourceId(int mThumbnailResourceId) {
        this.mThumbnailResourceId = mThumbnailResourceId;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmVideoUrl(String mVideoUrl) {
        this.mVideoUrl = mVideoUrl;
    }

    public void setmAdTagUrl(String mAdTagUrl) {
        this.mAdTagUrl = mAdTagUrl;
    }

    public void setmIsVmap(boolean mIsVmap) {
        this.mIsVmap = mIsVmap;
    }
}
