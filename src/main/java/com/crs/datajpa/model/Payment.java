package com.crs.datajpa.model;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "fk_payment_order"))
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;


    public Payment(){}

    public Payment(Long id, Order order, PaymentStatus status) {
        this.id = id;
        this.order = order;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
