package com.markson.controlefinanceiro.domain.receita.dto;

import com.markson.controlefinanceiro.domain.receita.Receita;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
@Schema(
        name = "Dados de Detalhamento de Receita",
        description = "Dados de Detalhamento de Receita"
)
public record DadosDetalhamentoReceita(
        @Schema(
                example = "1"
        )
        Long id,
        @Schema(
                example = "Salário"
        )
        String descricao,
        @Schema(
                example = "600.00"
        )
        BigDecimal valor,
        LocalDate data
) {
    public DadosDetalhamentoReceita(Receita receita) {
        this(
                receita.getId(),
                receita.getDescricao(),
                receita.getValor(),
                receita.getData()
        );
    }
}
