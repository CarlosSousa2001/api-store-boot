package com.crs.datajpa.controller;

import com.crs.datajpa.model.dto.AddressDTO;
import com.crs.datajpa.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        var addressRes = addressService.createAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressRes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id){
        var addressRes = addressService.getAddressByIdService(id);

        return ResponseEntity.ok().body(addressRes);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getListAddress(){
        var listAddress = addressService.getAddressList();

        return ResponseEntity.ok().body(listAddress);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        var updateAddress = addressService.updateAddressService(id, addressDTO);

        return ResponseEntity.ok().body(updateAddress);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço deletado");
    }







}
