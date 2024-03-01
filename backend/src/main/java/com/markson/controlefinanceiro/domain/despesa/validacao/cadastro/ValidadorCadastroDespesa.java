package com.markson.controlefinanceiro.domain.despesa.validacao.cadastro;

import com.markson.controlefinanceiro.domain.despesa.dto.DadosCadastramentoDespesa;

public interface ValidadorCadastroDespesa {
    void validar(DadosCadastramentoDespesa dados);
}
