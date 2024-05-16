package com.crs.datajpa.request;

public class AddItemRequest {

    private Long productId;
    private Long userID;
    private String size;
    private int quantity;
    private Integer price;

    public AddItemRequest(){}

    public AddItemRequest(Long productId, Long userID, String size, int quantity, Integer price) {
        this.productId = productId;
        this.userID = userID;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
