package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.resumo.ResumoService;
import com.markson.controlefinanceiro.domain.resumo.dto.DadosDetalhamentoResumo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
@Tag(
        name = "Geração de Resumo Mensal",
        description = "Gera um resumo contendo total de receitas, despesas, saldo e todas as despesas por categorias de um determinado mês"
)
public class ResumoController {
    private final ResumoService resumoService;

    @Autowired
    public ResumoController(ResumoService resumoService) {
        this.resumoService = resumoService;
    }

    @Operation(
            summary = "Gera o resumo de determinado mês",
            description = "Gera um resumo contendo total de receitas, despesas, saldo e todas as despesas por categorias de um determinado mês"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Retorna o resumo do mês"
    )
    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<DadosDetalhamentoResumo> resumoDoMes(@PathVariable int ano, @PathVariable int mes) {
        DadosDetalhamentoResumo resumo = resumoService.resumoDoMes(ano, mes);

        return ResponseEntity.ok(resumo);
    }
}
