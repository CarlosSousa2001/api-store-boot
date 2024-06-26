package com.crs.datajpa.model.dto;


import java.util.Date;

public class CustomerResponseAuthTokenDTO {

    private String accessToken;

    private Long expiresIn;

    public CustomerResponseAuthTokenDTO() {
    }

    public CustomerResponseAuthTokenDTO(String accessToken, Long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
