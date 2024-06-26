package com.crs.datajpa.model.dto;

import com.crs.datajpa.model.OrderItem;

public class OrdemItemDto {

    private Long productId;
    private String name;
    private Double price;
    private int quantity;
    private String photoUrl;

    public OrdemItemDto() {
    }

    public OrdemItemDto(Long productId, String name, Double price, int quantity, String photoUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.photoUrl = photoUrl;
    }

    public OrdemItemDto(OrderItem entity) {
        this.productId = entity.getProduct().getId();
        this.name = entity.getProduct().getTitle();
        this.price = entity.getProduct().getPrice();
        this.quantity = entity.getQuantity();
        this.photoUrl = entity.getProduct().getPhotoUrl();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
