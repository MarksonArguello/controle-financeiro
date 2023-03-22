package com.markson.controlefinanceiro.domain.despesa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosCadastramentoDespesa(
        @NotBlank
        String descricao,
        @NotNull
        BigDecimal valor,
        @NotNull
        LocalDateTime data
) {
}
