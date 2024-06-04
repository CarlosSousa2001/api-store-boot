package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.exceptions.UsernameUniqueViolationException;
import com.crs.datajpa.model.Customer;
import com.crs.datajpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer createUser(Customer userRequest) {

        try {

            return customerRepository.save(userRequest);
        } catch (DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException(String.format("Usuário '%s' já cadastrado", userRequest.getEmail()));
        }

    }
    public Customer getById(Long id)  {
        Optional<Customer> userOptional = customerRepository.findById(id);

        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return userOptional.get();
    }

}
