package com.markson.controlefinanceiro.domain.receita.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
@Schema(
        name = "Dados de Cadastramento de Receita",
        description = "Dados de Cadastramento de Receita"
)
public record DadosCadastramentoReceita(
        @Schema(
                example = "Salário"
        )
        @NotBlank(message = "descrição não pode ser em branco")
        String descricao,
        @Schema(
                example = "600.00"
        )
        @NotNull(message = "valor não pode ser vazio")
        BigDecimal valor,
        @NotNull(message = "data não pode ser vazia")
        LocalDate data
) {
}
