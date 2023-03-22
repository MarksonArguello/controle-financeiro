package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.receita.DadosCadastramentoReceita;
import com.markson.controlefinanceiro.domain.receita.DadosDetalhamentoReceita;
import com.markson.controlefinanceiro.domain.receita.ReceitaService;
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
@RequestMapping("/receitas")
public class ReceitaController {
    @Autowired
    private ReceitaService receitaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoReceita> cadastrar(@Valid @RequestBody DadosCadastramentoReceita dadosCadastramento,
                                                              UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoReceita dadosDetalhamento = receitaService.cadastrar(dadosCadastramento);

        var uri = uriBuilder.path("/receitas/{id}")
                .buildAndExpand(dadosDetalhamento.id())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(dadosDetalhamento);
    }
}
