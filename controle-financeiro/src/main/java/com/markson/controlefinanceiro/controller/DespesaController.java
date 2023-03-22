package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.despesa.DadosCadastramentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.DadosDetalhamentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.DespesaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoDespesa>> listar(
            @PageableDefault(size = 20, sort = "data", direction = Sort.Direction.DESC)
            Pageable paginacao)
    {
        var paginas = despesaService.listar(paginacao);

        return ResponseEntity.ok(paginas);
    }
}
