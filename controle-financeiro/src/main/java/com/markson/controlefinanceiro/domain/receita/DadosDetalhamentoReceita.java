package com.markson.controlefinanceiro.domain.receita;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosDetalhamentoReceita(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDateTime data
) {
    public DadosDetalhamentoReceita(Receita receita) {
        this(
                receita.getId(),
                receita.getDescricao(),
                receita.getValor(),
                receita.getData()
        );
    }
}
