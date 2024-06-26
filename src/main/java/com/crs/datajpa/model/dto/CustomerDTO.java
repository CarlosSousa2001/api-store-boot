package com.crs.datajpa.model.dto;

import com.crs.datajpa.model.Customer;

public class CustomerDTO {

    private Long id;
    private String username;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.username = customer.getUsername();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
