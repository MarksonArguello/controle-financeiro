package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.despesa.dto.DadosAtualizacaoDespesa;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosDetalhamentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosCadastramentoDespesa;
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

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoDespesa> detalhar(@PathVariable Long id) {
        var despesa = despesaService.detalhar(id);
        return ResponseEntity.ok(despesa);
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoDespesa>> listar(
            @PageableDefault(size = 20, sort = "data", direction = Sort.Direction.DESC)
            Pageable paginacao,
            @RequestParam(value = "descricao", required = false, defaultValue = "")
            String descricao
    ) {
        var paginas = despesaService.listar(paginacao, descricao);

        return ResponseEntity.ok(paginas);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoDespesa> atualizar(
            @PathVariable
            Long id,
            @Valid
            @RequestBody
            DadosAtualizacaoDespesa dadosAtualizacao
    ) {
        DadosDetalhamentoDespesa dadosDetalhamento = despesaService.atualizar(id, dadosAtualizacao);

        return ResponseEntity.ok(dadosDetalhamento);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        despesaService.deletar(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<Page<DadosDetalhamentoDespesa>> listarReceitaDoMes(@PathVariable int ano, @PathVariable int mes, @PageableDefault(size = 20, sort = "data", direction = Sort.Direction.DESC) Pageable paginacao) {
        Page<DadosDetalhamentoDespesa> despesas = despesaService.listarReceitaDoMes(ano, mes, paginacao);

        return ResponseEntity.ok(despesas);
    }
}