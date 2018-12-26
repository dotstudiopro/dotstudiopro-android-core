package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by mohsin on 26-01-2017.
 */

public class IndicatorDTO {

    private boolean isActive;
    private boolean isVertical;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }
}
