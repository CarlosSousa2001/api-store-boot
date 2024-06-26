package com.crs.datajpa.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProvider {

    @Value("${JWT.SECRET.KEY}")
    private String secretKey;


    public DecodedJWT validateToken(String token){
        // Essas duas formas funcionam
        //String tokenReplace = token.replace("Bearer ", "");
        String tokenReplace = token.substring(7);

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

         return  JWT.require(algorithm)
                    .build()
                    .verify(tokenReplace);

        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("TOKEN INVÁlIDO");
        }
    }
}
