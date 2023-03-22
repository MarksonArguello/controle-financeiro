package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.despesa.dto.DadosDetalhamentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosCadastramentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.DespesaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/despesas")
public class DespesaController {
    private final DespesaService despesaService;

    @Autowired
    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoDespesa> cadastrar(
            @Valid
            @RequestBody
            DadosCadastramentoDespesa dadosCadastramento,
            UriComponentsBuilder uriBuilder
    ) {
        DadosDetalhamentoDespesa dadosDetalhamento = despesaService.cadastrar(dadosCadastramento);

        var uri = uriBuilder.path("/despesas/{id}")
                .buildAndExpand(dadosDetalhamento.id())
                .toUri();
        return ResponseEntity.created(uri)
                .body(dadosDetalhamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoDespesa> detalhar(@PathVariable Long id) {
        var despesa = despesaService.detalhar(id);
        return ResponseEntity.ok(despesa);
    }
}
