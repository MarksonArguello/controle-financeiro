package com.markson.controlefinanceiro.infra.security.token;

import com.markson.controlefinanceiro.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final GeradorDeToken geradorDeToken;
    private final ValidadorDeToken validadorDeToken;

    @Autowired
    public TokenService(GeradorDeToken geradorDeToken, ValidadorDeToken validadorDeToken) {
        this.geradorDeToken = geradorDeToken;
        this.validadorDeToken = validadorDeToken;
    }

    public String gerarToken(Usuario usuario) {
        return geradorDeToken
                .gerarToken(usuario);
    }

    public String getSubject(String tokenJWT) {
        return validadorDeToken
                .validar(tokenJWT)
                .getSubject();
    }
}
