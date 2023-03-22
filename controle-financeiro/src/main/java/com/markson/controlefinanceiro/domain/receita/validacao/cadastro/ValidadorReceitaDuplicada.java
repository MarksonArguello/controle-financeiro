package com.markson.controlefinanceiro.domain.receita.validacao.cadastro;

import com.markson.controlefinanceiro.domain.exception.ValidacaoException;
import com.markson.controlefinanceiro.domain.receita.dto.DadosCadastramentoReceita;
import com.markson.controlefinanceiro.domain.receita.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "ValidadorCadastroReceitaDuplicada")
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
            throw new ValidacaoException("Receita já esta cadastrada");
        }
    }
}
