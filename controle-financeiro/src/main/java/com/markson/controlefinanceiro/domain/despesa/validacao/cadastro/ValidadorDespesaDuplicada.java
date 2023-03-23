package com.markson.controlefinanceiro.domain.despesa.validacao.cadastro;

import com.markson.controlefinanceiro.domain.despesa.dto.DadosCadastramentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.DespesaRepository;
import com.markson.controlefinanceiro.domain.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorCadastroDespesaDuplicada")
public class ValidadorDespesaDuplicada implements ValidadorCadastroDespesa {
    @Autowired
    private DespesaRepository repository;

    @Override
    public void validar(DadosCadastramentoDespesa dados) {
        Boolean jaEstaNoBanco = repository.existsByDescricaoAndMes(
                dados.descricao(),
                dados.data().getMonthValue());

        if (jaEstaNoBanco) {
            throw new ValidacaoException("Despesa já cadastrada");
        }
    }
}
