package com.markson.controlefinanceiro.domain.despesa.dto;

import com.markson.controlefinanceiro.domain.despesa.Despesa;
import com.markson.controlefinanceiro.domain.despesa.enums.Categoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosDetalhamentoDespesa(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDateTime data,
        Categoria categoria
) {
    public DadosDetalhamentoDespesa(Despesa despesa) {
        this(
                despesa.getId(),
                despesa.getDescricao(),
                despesa.getValor(),
                despesa.getData(),
                despesa.getCategoria()
        );
    }
}
