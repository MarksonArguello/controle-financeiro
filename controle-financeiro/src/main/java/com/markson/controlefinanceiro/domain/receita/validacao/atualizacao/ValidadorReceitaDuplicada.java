package com.markson.controlefinanceiro.domain.receita.validacao.atualizacao;

import com.markson.controlefinanceiro.domain.exception.ValidacaoException;
import com.markson.controlefinanceiro.domain.receita.DadosAtualizacaoReceita;
import com.markson.controlefinanceiro.domain.receita.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "ValidadorAtualizacaoReceitaDuplicada")
public class ValidadorReceitaDuplicada implements ValidadorAtualizacaoReceita {
    private final ReceitaRepository repository;

    @Autowired
    public ValidadorReceitaDuplicada(ReceitaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validar(Long id, DadosAtualizacaoReceita dados) {
        Boolean receitaJaEstaNoBanco = repository.existsByDescricaoAndMesAndIdDiferente(
                dados.descricao(),
                dados.data().getMonthValue(),
                id
        );

        if (receitaJaEstaNoBanco) {
            throw new ValidacaoException("Receita já esta cadastrada");
        }
    }
}
