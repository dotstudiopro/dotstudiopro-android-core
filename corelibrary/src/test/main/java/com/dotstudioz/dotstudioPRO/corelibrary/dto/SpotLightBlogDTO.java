package com.dotstudioz.dotstudioPRO.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 15-04-2015.
 */
public class SpotLightBlogDTO implements Serializable {
    private String id;
    private String blogTitle;
    private String postTitle;
    private String postExcerpt;
    private String postContent;
    private String postImage;
    private String postImageWidth;
    private String postImageHeight;
    private String postFeaturedImage;
    private String postCategory;

    private String postBlurb;
    private String postVideoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostExcerpt() {
        return postExcerpt;
    }

    public void setPostExcerpt(String postExcerpt) {
        this.postExcerpt = postExcerpt;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPostImageWidth() {
        return postImageWidth;
    }

    public void setPostImageWidth(String postImageWidth) {
        this.postImageWidth = postImageWidth;
    }

    public String getPostImageHeight() {
        return postImageHeight;
    }

    public void setPostImageHeight(String postImageHeight) {
        this.postImageHeight = postImageHeight;
    }

    public String getPostFeaturedImage() {
        return postFeaturedImage;
    }

    public void setPostFeaturedImage(String postFeaturedImage) {
        this.postFeaturedImage = postFeaturedImage;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getPostBlurb() {
        return postBlurb;
    }

    public void setPostBlurb(String postBlurb) {
        this.postBlurb = postBlurb;
    }

    public String getPostVideoId() {
        return postVideoId;
    }

    public void setPostVideoId(String postVideoId) {
        this.postVideoId = postVideoId;
    }
}
