package com.crs.datajpa.request;

public class AddItemRequest {

    private Long productId;
    private Long cartId;
    private String size;
    private int quantity;

    public AddItemRequest(){}

    public AddItemRequest(Long productId, Long cartId, String size, int quantity) {
        this.productId = productId;
        this.cartId = cartId;
        this.size = size;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
