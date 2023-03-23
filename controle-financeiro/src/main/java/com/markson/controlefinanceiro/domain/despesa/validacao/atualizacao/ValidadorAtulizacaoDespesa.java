package com.markson.controlefinanceiro.domain.despesa.validacao.atualizacao;

import com.markson.controlefinanceiro.domain.despesa.dto.DadosAtualizacaoDespesa;

public interface ValidadorAtulizacaoDespesa {
    void validar(Long id, DadosAtualizacaoDespesa dados);
}
