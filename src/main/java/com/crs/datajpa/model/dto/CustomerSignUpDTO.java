package com.crs.datajpa.model.dto;

import com.crs.datajpa.model.Customer;
import jakarta.persistence.Column;

public class CustomerSignUpDTO {

    private Long id;
    private String username;

    private String email;

    private String password;

    public CustomerSignUpDTO() {
    }

    public CustomerSignUpDTO(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public CustomerSignUpDTO(Customer customer) {
        this.id = customer.getId();
        this.username = customer.getUsername();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
