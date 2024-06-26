package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.exceptions.UsernameUniqueViolationException;
import com.crs.datajpa.model.Customer;
import com.crs.datajpa.model.dto.CustomerSignUpDTO;
import com.crs.datajpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public CustomerSignUpDTO createUser(CustomerSignUpDTO customerSignUp) {
        try {

            Customer customerRes = new Customer();
            customerRes.setUsername(customerSignUp.getUsername());
            customerRes.setEmail(customerSignUp.getEmail());
            var passwordEncrypt = passwordEncoder.encode(customerSignUp.getPassword());
            customerRes.setPassword(passwordEncrypt);

            Customer entity = customerRepository.save(customerRes);

            return new CustomerSignUpDTO(entity);

        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Usuário '%s' já cadastrado", customerSignUp.getEmail()));
        }

    }

    public Customer getById(Long id) {
        Optional<Customer> userOptional = customerRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        authenticated();
        return userOptional.get();
    }

    protected Customer authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String emailContext = authentication.getName();

            System.out.println("--------------" + emailContext + "---------------");

            return customerRepository.findByEmail(emailContext).get();

        } catch (Exception ex) {
            throw new UsernameNotFoundException("Usuario com emmail não encontrado");
        }
    }

}
