package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.exceptions.UsernameUniqueViolationException;
import com.crs.datajpa.model.User;
import com.crs.datajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User userRequest) {

        try {
            return userRepository.save(userRequest);
        } catch (DataIntegrityViolationException ex){
            throw new UsernameUniqueViolationException(String.format("Usuário '%s' já cadastrado", userRequest.getEmail()));
        }

    }
    public User getById(Long id)  {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()) {
            throw new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id));
        }

        return userOptional.get();
    }
}
