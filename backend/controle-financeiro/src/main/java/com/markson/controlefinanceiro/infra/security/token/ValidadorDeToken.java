package com.markson.controlefinanceiro.infra.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValidadorDeToken {
    @Value("${api.security.token.issuer}")
    private String issuer;
    @Autowired
    private Algorithm algoritmo;

    public DecodedJWT validar(String token) {
        try {
            return JWT.require(algoritmo)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
}