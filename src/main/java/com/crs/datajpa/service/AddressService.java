package com.crs.datajpa.service;

import com.crs.datajpa.model.Address;
import com.crs.datajpa.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address addressRequest) {
        return addressRepository.save(addressRequest);
    }
}
