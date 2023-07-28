package com.markson.controlefinanceiro.domain.resumo;

import com.markson.controlefinanceiro.domain.despesa.enums.Categoria;

import java.math.BigDecimal;


public record GastoPorCategoria(
    Categoria categoria,
    BigDecimal valor
) {
}
