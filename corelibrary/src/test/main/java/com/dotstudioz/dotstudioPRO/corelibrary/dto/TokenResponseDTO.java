package com.dotstudioz.dotstudioPRO.models.dto;

/**
 * Created by Admin on 23-02-2016.
 */
public class TokenResponseDTO {

    private boolean isResponseValid;
    private String response;

    public boolean isResponseValid() {
        return isResponseValid;
    }

    public void setIsResponseValid(boolean isResponseValid) {
        this.isResponseValid = isResponseValid;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
