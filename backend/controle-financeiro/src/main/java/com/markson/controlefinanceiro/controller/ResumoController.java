package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.domain.resumo.ResumoService;
import com.markson.controlefinanceiro.domain.resumo.dto.DadosDetalhamentoResumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo")
public class ResumoController {
    private final ResumoService resumoService;

    @Autowired
    public ResumoController(ResumoService resumoService) {
        this.resumoService = resumoService;
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<DadosDetalhamentoResumo> resumoDoMes(@PathVariable int ano, @PathVariable int mes) {
        DadosDetalhamentoResumo resumo = resumoService.resumoDoMes(ano, mes);

        return ResponseEntity.ok(resumo);
    }
}
