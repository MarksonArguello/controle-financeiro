package com.markson.controlefinanceiro.domain.resumo;

import com.markson.controlefinanceiro.domain.despesa.enums.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(
        name = "Gasto por categoria",
        description = "Gasto por categoria em determinado mês"
)
public record GastoPorCategoria(
        @Schema(
                example = "MORADIA"
        )
        Categoria categoria,
        @Schema(
                example = "100.00"
        )
        BigDecimal valor
) {
}
