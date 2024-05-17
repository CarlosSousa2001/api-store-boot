package com.crs.datajpa.controller;

import com.crs.datajpa.model.Customer;
import com.crs.datajpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Customer> createUser(@RequestBody Customer userRequest) {
        var userRes = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getUserById(@PathVariable Long id) {
        var getUserRes = userService.getById(id);
        return  ResponseEntity.ok().body(getUserRes);
    }
}
