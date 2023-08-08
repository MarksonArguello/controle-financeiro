package com.markson.controlefinanceiro.domain.despesa.dto;

import com.markson.controlefinanceiro.domain.despesa.enums.Categoria;

public record DadosDetalhamentoCategoria (
        Categoria valor,
        String texto
) {
    public DadosDetalhamentoCategoria(Categoria categoria) {
        this(categoria, categoria.toString());
    }
}
