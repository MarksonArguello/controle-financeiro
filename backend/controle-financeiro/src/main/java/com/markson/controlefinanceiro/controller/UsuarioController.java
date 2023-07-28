package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.autenticacao.AutenticacaoService;
import com.markson.controlefinanceiro.domain.autenticacao.DadosAutenticacao;
import com.markson.controlefinanceiro.domain.usuario.DadosDetalhamentoUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@Valid @RequestBody DadosAutenticacao dadosAutenticacao, UriComponentsBuilder uriBuilder) {
        var usuario = autenticacaoService.cadastrar(dadosAutenticacao);
        var uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(new DadosDetalhamentoUsuario(usuario));
    }
}
