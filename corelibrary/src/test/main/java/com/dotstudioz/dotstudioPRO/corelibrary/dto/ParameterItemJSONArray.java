package com.dotstudioz.dotstudioPRO.models.dto;

import org.json.JSONArray;

/**
 * Created by mohsin on 03-03-2017.
 */

public class ParameterItemJSONArray {

    private String parameterName;
    private JSONArray parameterValue;

    public ParameterItemJSONArray(String parameterName, JSONArray parameterValue) {
        setParameterName(parameterName);
        setParameterValue(parameterValue);
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public JSONArray getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(JSONArray parameterValue) {
        this.parameterValue = parameterValue;
    }
}
