package com.crs.datajpa.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.model.dto.CustomerResponseAuthTokenDTO;
import com.crs.datajpa.model.dto.CustomerSignInDTO;
import com.crs.datajpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCustomerService {

    @Value("${JWT.SECRET.KEY}")
    private String secretKey;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerResponseAuthTokenDTO execute(CustomerSignInDTO customerSignInDTO) {
        var customer = customerRepository.findByEmail(customerSignInDTO.getEmail()).orElseThrow(
                () -> new EntityNotFoundException()
        );

        // verificar senha

        boolean authenticatedMatched = passwordEncoder.matches(customerSignInDTO.getPassword(), customerSignInDTO.getPassword());

        if (!authenticatedMatched) {
            System.out.println(customer.getEmail() + "Errorrr");
        }

        // se for igual retorno o token

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(8));

        var token =  JWT.create()
                .withIssuer("datajpa")
                .withExpiresAt(expiresIn)
                .withSubject(customer.getId().toString())
                .withClaim("roles", Arrays.asList("CLIENT"))
                .sign(algorithm);

        CustomerResponseAuthTokenDTO customerToken = new CustomerResponseAuthTokenDTO();

        customerToken.setAccessToken(token);
        customerToken.setExpiresIn(expiresIn.toEpochMilli());

        return customerToken;
    }

}
