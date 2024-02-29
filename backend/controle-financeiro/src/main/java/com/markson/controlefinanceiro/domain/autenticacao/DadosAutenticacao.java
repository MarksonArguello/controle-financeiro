package com.markson.controlefinanceiro.domain.autenticacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "Dados de autenticação",
        description = "Dados de autenticação"
)
public record DadosAutenticacao(
        @Schema(
                example = "Louise"
        )
        @NotBlank
        String login,
        @Schema(
                example = "12345678"
        )
        @NotBlank
        String senha
) {
}
