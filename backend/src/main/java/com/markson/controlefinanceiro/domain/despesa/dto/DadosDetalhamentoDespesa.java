package com.markson.controlefinanceiro.domain.despesa.dto;

import com.markson.controlefinanceiro.domain.despesa.Despesa;
import com.markson.controlefinanceiro.domain.despesa.enums.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(
        name = "Dados de Detalhamento de Despesa",
        description = "Dados de Detalhamento de Despesa"
)
public record DadosDetalhamentoDespesa(
        @Schema(
                example = "1"
        )
        Long id,
        @Schema(
                example = "Conta de energia"
        )
        String descricao,
        @Schema(
                example = "600.00"
        )
        BigDecimal valor,
        LocalDate data,
        @Schema(
                example = "MORADIA"
        )
        Categoria categoria
) {
    public DadosDetalhamentoDespesa(Despesa despesa) {
        this(
                despesa.getId(),
                despesa.getDescricao(),
                despesa.getValor(),
                despesa.getData(),
                despesa.getCategoria()
        );
    }
}
