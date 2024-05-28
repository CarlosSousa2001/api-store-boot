package com.crs.datajpa.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Invoice { // nota fiscal


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "invoice", optional = false)
    private Order order;
    private String text;

    private Date dateOfIssue; // data emissão


    public Invoice() {
    }

    public Invoice(Long id, Order order, String text, Date dateOfIssue) {
        this.id = id;
        this.order = order;
        this.text = text;
        this.dateOfIssue = dateOfIssue;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}
