package com.markson.controlefinanceiro.domain.resumo.dto;

import com.markson.controlefinanceiro.domain.resumo.GastoPorCategoria;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(
        name = "Resumo do mês",
        description = "Informações resumidas de determinado mês como: total de receitas, total de despesas, saldo final, gasto por categoria"
)
public record DadosDetalhamentoResumo(
        @Schema(
                example = "600.00"
        )
        BigDecimal totalReceitas,
        @Schema(
                example = "500.00"
        )
        BigDecimal totalDespesas,
        @Schema(
                example = "100.00"
        )
        BigDecimal saldo,
        List<GastoPorCategoria> gastoPorCategoria
) {
}
