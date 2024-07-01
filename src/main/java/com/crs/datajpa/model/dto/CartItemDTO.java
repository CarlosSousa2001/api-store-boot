package com.crs.datajpa.model.dto;

import com.crs.datajpa.model.CartItem;

public class CartItemDTO {

    private Long productId;
    private String name;
    private Double price;
    private int quantity;

    public CartItemDTO() {
    }

    public CartItemDTO(Long productId, String name, Double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public CartItemDTO(CartItem entity) {
        this.productId = entity.getProduct().getId();
        this.name = entity.getProduct().getTitle();
        this.price = entity.getProduct().getPrice();
        this.quantity = entity.getQuantity();
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
