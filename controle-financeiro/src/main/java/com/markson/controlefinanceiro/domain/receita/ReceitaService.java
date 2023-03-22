package com.markson.controlefinanceiro.domain.receita;

import com.markson.controlefinanceiro.domain.receita.validacao.ValidadorCadastroReceita;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {
    private final ReceitaRepository receitaRepository;
    private final List<ValidadorCadastroReceita> validadoresCadastro;

    @Autowired
    public ReceitaService(ReceitaRepository receitaRepository, List<ValidadorCadastroReceita> validadoresCadastro) {
        this.receitaRepository = receitaRepository;
        this.validadoresCadastro = validadoresCadastro;
    }

    public DadosDetalhamentoReceita cadastrar(DadosCadastramentoReceita dadosCadastramento) {
        validadoresCadastro.forEach(
                validador -> validador.validar(dadosCadastramento)
        );

       var receita = new Receita(dadosCadastramento);

        receitaRepository.save(receita);

        return new DadosDetalhamentoReceita(receita);
    }

    public Page<DadosDetalhamentoReceita> listar(Pageable paginacao) {
        Page<Receita> receitas =  receitaRepository.findAll(paginacao);

        return receitas.map(DadosDetalhamentoReceita::new);
    }

    public DadosDetalhamentoReceita detalhar(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new EntityNotFoundException("Receita com o id informado não existe");
        }

        Receita receita = receitaRepository.getReferenceById(id);

        return new DadosDetalhamentoReceita(receita);
    }
}
