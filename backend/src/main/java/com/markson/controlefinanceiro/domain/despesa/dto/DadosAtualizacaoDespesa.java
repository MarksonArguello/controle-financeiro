package com.markson.controlefinanceiro.domain.despesa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
@Schema(
        name = "Dados de Atualização de Despesa",
        description = "Dados de Atualização de Despesa"
)
public record DadosAtualizacaoDespesa(
        @Schema(
                example = "Conta de energia"
        )
        @NotBlank
        String descricao,
        @Schema(
                example = "500.00"
        )
        @NotNull
        BigDecimal valor,
        @NotNull
        LocalDate data
) {
}
