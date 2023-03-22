package com.markson.controlefinanceiro.domain.despesa;

import com.markson.controlefinanceiro.domain.despesa.dto.DadosCadastramentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosDetalhamentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.validacao.cadastro.ValidadorCadastroDespesa;
import com.markson.controlefinanceiro.domain.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public DadosDetalhamentoDespesa detalhar(Long id) {
        if (!despesaRepository.existsById(id)) {
            throw new ValidacaoException("Despesa com o id informado não existe");
        }
        var despesa = despesaRepository.getReferenceById(id);

        return new DadosDetalhamentoDespesa(despesa);
    }
}
