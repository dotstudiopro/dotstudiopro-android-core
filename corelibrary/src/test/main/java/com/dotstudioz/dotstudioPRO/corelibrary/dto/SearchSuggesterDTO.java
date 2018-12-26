package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by user on 11-04-2016.
 */
public class SearchSuggesterDTO {
    private String parent;
    private String suggestion;
    private int score;
    private String channelURL;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getChannelURL() {
        return channelURL;
    }

    public void setChannelURL(String channelURL) {
        this.channelURL = channelURL;
    }
}
