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

    @Lob // para "arquivos" grandes
    private byte[] text;

    private Date dateOfIssue; // data emissão


    public Invoice() {
    }

    public Invoice(Long id, Order order, byte[] text, Date dateOfIssue) {
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

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }
}
