package com.crs.datajpa.request;

public class AddOrderRequest {

    private Long userId;

    public AddOrderRequest(){}

    public AddOrderRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
