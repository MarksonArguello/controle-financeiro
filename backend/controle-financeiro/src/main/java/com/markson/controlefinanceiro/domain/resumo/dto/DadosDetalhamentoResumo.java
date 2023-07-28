package com.markson.controlefinanceiro.domain.resumo.dto;

import com.markson.controlefinanceiro.domain.resumo.GastoPorCategoria;

import java.math.BigDecimal;
import java.util.List;

public record DadosDetalhamentoResumo(
    BigDecimal totalReceitas,
    BigDecimal totalDespesas,
    BigDecimal saldo,
    List<GastoPorCategoria> gastoPorCategoria
) {
}
