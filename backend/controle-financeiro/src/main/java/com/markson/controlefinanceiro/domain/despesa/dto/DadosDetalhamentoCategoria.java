package com.markson.controlefinanceiro.domain.despesa.dto;

import com.markson.controlefinanceiro.domain.despesa.enums.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Dados de Detalhamento de Categoria",
        description = "Dados de Detalhamento de Categoria"
)
public record DadosDetalhamentoCategoria (
        @Schema(
                example = "MORADIA"
        )
        Categoria valor,
        @Schema(
                example = "Moradia"
        )
        String texto
) {
    public DadosDetalhamentoCategoria(Categoria categoria) {
        this(categoria, categoria.toString());
    }
}
