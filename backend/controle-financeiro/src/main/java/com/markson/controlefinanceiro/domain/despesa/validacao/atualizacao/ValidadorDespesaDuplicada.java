package com.markson.controlefinanceiro.domain.despesa.validacao.atualizacao;

import com.markson.controlefinanceiro.domain.despesa.DespesaRepository;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosAtualizacaoDespesa;
import com.markson.controlefinanceiro.domain.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorAtualizacaoDespesaDuplicada")
public class ValidadorDespesaDuplicada implements ValidadorAtulizacaoDespesa {
    private final DespesaRepository repository;

    @Autowired
    public ValidadorDespesaDuplicada(DespesaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validar(Long id, DadosAtualizacaoDespesa dados) {
        Boolean jaEstaNoBanco = repository.existsByDescricaoAndMesAndIdDiferente(
                dados.descricao(),
                dados.data().getMonthValue(),
                id
        );

        if (jaEstaNoBanco) {
            throw new ValidacaoException("Despesa já cadastrada");
        }
    }
}
