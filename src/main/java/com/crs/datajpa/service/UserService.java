package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.ProductNotFoundException;
import com.crs.datajpa.model.User;
import com.crs.datajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(User userRequest) {
        return userRepository.save(userRequest);
    }
    public User getById(Long id)  {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        return userOptional.get();
    }
}
