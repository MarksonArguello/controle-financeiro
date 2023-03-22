package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.receita.DadosAtualizacaoReceita;
import com.markson.controlefinanceiro.domain.receita.DadosCadastramentoReceita;
import com.markson.controlefinanceiro.domain.receita.DadosDetalhamentoReceita;
import com.markson.controlefinanceiro.domain.receita.ReceitaService;
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

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoReceita>> listar(
            @PageableDefault(
                    size = 20,
                    sort = "data",
                    direction = Sort.Direction.DESC)
            Pageable paginacao) {
        Page<DadosDetalhamentoReceita> dadosDetalhamento = receitaService.listar(paginacao);
        return ResponseEntity.ok(dadosDetalhamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoReceita> detalhar(
            @PathVariable
            Long id
    ) {
        DadosDetalhamentoReceita dadosDetalhamento = receitaService.detalhar(id);

        return ResponseEntity.ok(dadosDetalhamento);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoReceita> atualizar(
            @PathVariable
            Long id,
            @Valid
            @RequestBody
            DadosAtualizacaoReceita dadosAtualizacao
    ) {
        DadosDetalhamentoReceita dadosDetalhamento = receitaService.atualizar(id, dadosAtualizacao);
        return ResponseEntity.ok(dadosDetalhamento);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
