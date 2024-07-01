package com.crs.datajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class CartItem {

    @EmbeddedId
    private CartItemPK id = new CartItemPK();
    private String size;
    private int quantity;
    private Double price;

    public  CartItem(){}

    public CartItem(Cart cart, Product product , String size, int quantity, Double price) {
        id.setCart(cart);
        id.setProduct(product);
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public Cart getCart(){
        return id.getCart();
    }

    public void setCart(Cart cart){
        id.setCart(cart);
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
