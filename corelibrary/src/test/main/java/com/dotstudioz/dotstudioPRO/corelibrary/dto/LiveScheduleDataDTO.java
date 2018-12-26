package com.dotstudioz.dotstudioPRO.models.dto;

import java.util.Date;

/**
 * Created by Admin on 18-04-2015.
 */
public class LiveScheduleDataDTO {
    private String title;
    private String description;
    private int duration;
    private Date scheduledStartTime;
    private Date scheduledStartTimeActualData;
    private String thumb;
    private boolean isDataAlreadySet = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getScheduledStartTime() {
        return scheduledStartTime;
    }

    public void setScheduledStartTime(Date scheduledStartTime) {
        this.scheduledStartTime = scheduledStartTime;
    }

    public Date getScheduledStartTimeActualData() {
        return scheduledStartTimeActualData;
    }

    public void setScheduledStartTimeActualData(Date scheduledStartTimeActualData) {
        this.scheduledStartTimeActualData = scheduledStartTimeActualData;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public boolean isDataAlreadySet() {
        return isDataAlreadySet;
    }

    public void setDataAlreadySet(boolean dataAlreadySet) {
        isDataAlreadySet = dataAlreadySet;
    }
}
