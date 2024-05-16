package com.crs.datajpa.controller;

import com.crs.datajpa.model.Address;
import com.crs.datajpa.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address addressRequest) {
        var AddressRes = addressService.createAddress(addressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(AddressRes);
    }
}
