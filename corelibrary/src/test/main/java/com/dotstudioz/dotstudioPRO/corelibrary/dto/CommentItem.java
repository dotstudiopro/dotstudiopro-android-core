package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by Admin on 05-12-2014.
 */
public class CommentItem {
    private String username;
    private String comment;
    private String commentDate;
    private String userImageFeed;
    private String userImage;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getUserImageFeed() {
        return userImageFeed;
    }

    public void setUserImageFeed(String userImageFeed) {
        this.userImageFeed = userImageFeed;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
