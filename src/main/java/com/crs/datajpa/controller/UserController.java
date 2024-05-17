package com.crs.datajpa.controller;

import com.crs.datajpa.model.User;
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
    public ResponseEntity<User> createUser(@RequestBody User userRequest) {
        var userRes = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        var getUserRes = userService.getById(id);
        return  ResponseEntity.ok().body(getUserRes);
    }
}
