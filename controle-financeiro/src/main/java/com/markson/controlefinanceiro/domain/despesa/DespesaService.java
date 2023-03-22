package com.markson.controlefinanceiro.domain.despesa;

import com.markson.controlefinanceiro.domain.despesa.validacao.cadastro.ValidadorCadastroDespesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {
    private final DespesaRepository despesaRepository;
    private final List<ValidadorCadastroDespesa> validadoresCadastro;

    @Autowired
    public DespesaService(DespesaRepository despesaRepository, List<ValidadorCadastroDespesa> validadoresCadastro) {
        this.despesaRepository = despesaRepository;
        this.validadoresCadastro = validadoresCadastro;
    }

    public DadosDetalhamentoDespesa cadastrar(DadosCadastramentoDespesa dadosCadastramento) {
        validadoresCadastro.forEach(
                validador -> validador.validar(dadosCadastramento)
        );

        var despesa = new Despesa(dadosCadastramento);
        despesaRepository.save(despesa);
        return new DadosDetalhamentoDespesa(despesa);
    }

    public Page<DadosDetalhamentoDespesa> listar(Pageable paginacao) {
        return despesaRepository
                .findAll(paginacao)
                .map(DadosDetalhamentoDespesa::new);
    }
}
