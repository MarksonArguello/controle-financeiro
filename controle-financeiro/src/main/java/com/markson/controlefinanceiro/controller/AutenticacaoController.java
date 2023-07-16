package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.autenticacao.DadosAutenticacao;
import com.markson.controlefinanceiro.domain.usuario.Usuario;
import com.markson.controlefinanceiro.infra.security.token.DadosTokenJWT;
import com.markson.controlefinanceiro.infra.security.token.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity logar(@Valid @RequestBody DadosAutenticacao usuario) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(usuario.login(), usuario.senha());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String jwtToken = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(
                new DadosTokenJWT(jwtToken)
        );
    }
}
