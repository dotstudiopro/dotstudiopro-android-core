package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by user on 15-06-2016.
 */
public class AdDTO {
    public static String PRE_OFFSET = "pre";
    public static String POST_OFFSET = "post";
    public static String MID_OFFSET = "mid";
    public static String AD_TYPE_LINEAR = "linear";
    private String offset;
    private String type;
    private String tag;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
