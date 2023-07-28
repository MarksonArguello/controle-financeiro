package com.markson.controlefinanceiro.domain.receita.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosCadastramentoReceita(
        @NotBlank
        String descricao,
        @NotNull
        BigDecimal valor,
        @NotNull
        LocalDateTime data
) {
}
