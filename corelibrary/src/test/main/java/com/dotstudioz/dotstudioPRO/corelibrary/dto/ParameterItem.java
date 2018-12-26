package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by mohsin on 03-03-2017.
 */

public class ParameterItem {

    private String parameterName;
    private String parameterValue;

    public ParameterItem(String parameterName, String parameterValue) {
        setParameterName(parameterName);
        setParameterValue(parameterValue);
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
}
