package com.mustbe.mustbe.security;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.mustbe.mustbe.security.SecurityConstants.EXPIRATION_TIME;

@Component
public class JWTProvider {

    private String secret;
    private static JWTProvider provider;

    public static JWTProvider getInstance(){
        if(provider == null) {
            provider = new JWTProvider();
        }
        return provider;
    }

    private JWTProvider(){
        this.secret = UUID.randomUUID().toString();
    }

    public String generateToken(String username){
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(this.secret.getBytes()));
    }

    public String getSecret() {
        return this.secret;
    }


}