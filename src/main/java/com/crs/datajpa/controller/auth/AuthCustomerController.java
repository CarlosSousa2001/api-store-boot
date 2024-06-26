package com.crs.datajpa.controller.auth;

import com.crs.datajpa.model.dto.CustomerResponseAuthTokenDTO;
import com.crs.datajpa.model.dto.CustomerSignInDTO;
import com.crs.datajpa.service.auth.AuthCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/signin")
public class AuthCustomerController {

    @Autowired
    private AuthCustomerService authCustomerService;

    @PostMapping
    public ResponseEntity<CustomerResponseAuthTokenDTO> customerSignIn(@RequestBody CustomerSignInDTO customerSignInDTO){
        var res =  authCustomerService.execute(customerSignInDTO);
        return ResponseEntity.ok().body(res);
    }
}
