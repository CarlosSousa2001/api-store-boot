package com.crs.datajpa.model.dto;

import com.crs.datajpa.model.Customer;
import jakarta.persistence.Column;

public class CustomerSignInDTO {

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    public CustomerSignInDTO() {
    }

    public CustomerSignInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public CustomerSignInDTO(Customer customer) {
        this.email = customer.getEmail();
        this.password = customer.getPassword();
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
