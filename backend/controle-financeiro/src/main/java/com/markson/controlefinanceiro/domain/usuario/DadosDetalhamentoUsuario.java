package com.markson.controlefinanceiro.domain.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Dados de Detalhamento do Usuário",
        description = "Dados de Detalhamento do Usuário"
)
public record DadosDetalhamentoUsuario(
        @Schema(
                example = "1"
        )
        Long id,
        @Schema(
                example = "Louise"
        )
        String login
) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }
}
