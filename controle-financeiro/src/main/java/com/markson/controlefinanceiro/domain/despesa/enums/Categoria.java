package com.markson.controlefinanceiro.domain.despesa.enums;

public enum Categoria {
    ALIMENTACAO("Alimentação"),
    SAUDE("Saúde"),
    MORADIA("Moradia"),
    TRANSPORTE("Transporte"),
    EDUCACAO("Educação"),
    LAZER("Lazer"),
    IMPREVISTOS("Imprevistos"),
    OUTRAS("Outras");

    private final String categoria;

    Categoria(String categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return categoria;
    }
}
