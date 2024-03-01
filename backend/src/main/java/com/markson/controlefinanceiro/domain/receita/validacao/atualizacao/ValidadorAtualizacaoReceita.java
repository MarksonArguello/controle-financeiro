package com.markson.controlefinanceiro.domain.receita.validacao.atualizacao;

import com.markson.controlefinanceiro.domain.receita.dto.DadosAtualizacaoReceita;

public interface ValidadorAtualizacaoReceita {
    void validar(Long id, DadosAtualizacaoReceita dados);
}
