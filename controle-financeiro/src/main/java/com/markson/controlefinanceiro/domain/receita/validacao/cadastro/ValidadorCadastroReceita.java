package com.markson.controlefinanceiro.domain.receita.validacao.cadastro;

import com.markson.controlefinanceiro.domain.receita.dto.DadosCadastramentoReceita;

public interface ValidadorCadastroReceita {
    void validar(DadosCadastramentoReceita dadosCadastramento);
}
