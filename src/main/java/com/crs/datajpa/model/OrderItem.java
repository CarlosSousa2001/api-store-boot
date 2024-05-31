package com.crs.datajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id;

    @MapsId("orderId") // o jpa agora vai inserir automaticamente o id pra mim
    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false, foreignKey = @ForeignKey(name = "fk_order_item_order"))
    @JsonIgnore
    private Order order;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID",  nullable = false, foreignKey = @ForeignKey(name = "fk_order_item_product"))
    private Product product;

    private String size;

    private int quantity;
    @Column(name = "price", precision = 10, scale = 2) // value decimal(19,2)
    private Integer price;


    public OrderItem(){}

    public OrderItem(OrderItemPK id, Order order, Product product, String size, int quantity, Integer price) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemPK getId() {
        return id;
    }

    public void setId(OrderItemPK id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
