package com.crs.datajpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemPK implements Serializable {
    private static final long serialVersionUID = 6576422530023818928L;
    @Column(name = "ORDER_PK")
    private Long orderPK;

    @Column(name = "PRODUCT_PK")
    private Long productPK;

    public OrderItemPK() {
    }

    public OrderItemPK(Long orderPK, Long productPK) {
        this.orderPK = orderPK;
        this.productPK = productPK;
    }

    public Long getOrderPK() {
        return orderPK;
    }

    public void setOrderPK(Long orderPK) {
        this.orderPK = orderPK;
    }

    public Long getProductPK() {
        return productPK;
    }

    public void setProductPK(Long productPK) {
        this.productPK = productPK;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(orderPK, that.orderPK) && Objects.equals(productPK, that.productPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderPK, productPK);
    }
}
