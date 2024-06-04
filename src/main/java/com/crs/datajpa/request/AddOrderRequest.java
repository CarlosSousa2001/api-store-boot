package com.crs.datajpa.request;

public class AddOrderRequest {

    private Long cartId;
    private Long userId;

    public AddOrderRequest(){}


    public AddOrderRequest(Long cartId, Long userId) {
        this.cartId = cartId;
        this.userId = userId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
