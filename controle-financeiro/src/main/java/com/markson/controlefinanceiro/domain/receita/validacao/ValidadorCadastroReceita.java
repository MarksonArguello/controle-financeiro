package com.markson.controlefinanceiro.domain.receita.validacao;

import com.markson.controlefinanceiro.domain.receita.DadosCadastramentoReceita;

public interface ValidadorCadastroReceita {
    void validar(DadosCadastramentoReceita dadosCadastramento);
}
