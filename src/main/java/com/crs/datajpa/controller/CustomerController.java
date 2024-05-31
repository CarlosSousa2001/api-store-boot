package com.crs.datajpa.controller;

import com.crs.datajpa.model.Customer;
import com.crs.datajpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createUser(@RequestBody Customer userRequest) {
        var userRes = customerService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getUserById(@PathVariable Long id) {
        var getUserRes = customerService.getById(id);
        return  ResponseEntity.ok().body(getUserRes);
    }
}
