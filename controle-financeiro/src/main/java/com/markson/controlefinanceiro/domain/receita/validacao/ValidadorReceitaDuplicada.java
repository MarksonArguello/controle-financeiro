package com.markson.controlefinanceiro.domain.receita.validacao;

import com.markson.controlefinanceiro.domain.exception.ValidacaoException;
import com.markson.controlefinanceiro.domain.receita.DadosCadastramentoReceita;
import com.markson.controlefinanceiro.domain.receita.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorReceitaDuplicada implements ValidadorCadastroReceita{
    @Autowired
    private ReceitaRepository repository;
    @Override
    public void validar(DadosCadastramentoReceita dadosCadastramento) {
        Boolean receitaJaEstaNoBanco = repository.existsByDescricaoAndMes(
                dadosCadastramento.descricao(),
                dadosCadastramento.data().getMonthValue()
        );

        if (receitaJaEstaNoBanco) {
            throw new ValidacaoException("Receita já esta no banco");
        }
    }
}
