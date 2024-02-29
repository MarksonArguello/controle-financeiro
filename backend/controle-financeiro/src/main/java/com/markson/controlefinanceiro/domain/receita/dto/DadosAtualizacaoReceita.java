package com.markson.controlefinanceiro.domain.receita.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(
        name = "Dados de Atualização de Receita",
        description = "Dados de Atualização de Receita"
)
public record DadosAtualizacaoReceita(
        @Schema(
                example = "Rendimentos"
        )
        @NotBlank
        String descricao,
        @Schema(
                example = "260.00"
        )
        @NotNull
        BigDecimal valor,
        @NotNull
        LocalDateTime data
) {
}
