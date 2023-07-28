package com.markson.controlefinanceiro.infra.security.token;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class AlgoritmoJWTConfig {
    @Value("${api.security.token.secret}")
    private String secret;

    @Bean
    public Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }
}
