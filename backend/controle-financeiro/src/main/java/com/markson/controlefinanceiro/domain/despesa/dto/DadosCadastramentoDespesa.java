package com.markson.controlefinanceiro.domain.despesa.dto;

import com.markson.controlefinanceiro.domain.despesa.enums.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
@Schema(
        name = "Dados de Cadastramento de Despesa",
        description = "Dados de Cadastramento de Despesa"
)

public record DadosCadastramentoDespesa(
        @Schema(
                example = "Conta de energia"
        )
        @NotBlank
        String descricao,
        @Schema(
                example = "600.00"
        )
        @NotNull
        BigDecimal valor,
        @NotNull
        LocalDate data,
        @Schema(
                example = "MORADIA"
        )
        Categoria categoria
) {
}
