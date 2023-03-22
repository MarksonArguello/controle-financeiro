package com.markson.controlefinanceiro.domain.receita;

import com.markson.controlefinanceiro.domain.receita.dto.DadosAtualizacaoReceita;
import com.markson.controlefinanceiro.domain.receita.dto.DadosCadastramentoReceita;
import com.markson.controlefinanceiro.domain.receita.dto.DadosDetalhamentoReceita;
import com.markson.controlefinanceiro.domain.receita.validacao.atualizacao.ValidadorAtualizacaoReceita;
import com.markson.controlefinanceiro.domain.receita.validacao.cadastro.ValidadorCadastroReceita;
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
    private final List<ValidadorAtualizacaoReceita> validadoresAtualizacao;

    @Autowired
    public ReceitaService(ReceitaRepository receitaRepository, List<ValidadorCadastroReceita> validadoresCadastro, List<ValidadorAtualizacaoReceita> validadoresAtualizacao) {
        this.receitaRepository = receitaRepository;
        this.validadoresCadastro = validadoresCadastro;
        this.validadoresAtualizacao = validadoresAtualizacao;
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

    public DadosDetalhamentoReceita atualizar(Long id, DadosAtualizacaoReceita dadosAtualizacao) {
        if (!receitaRepository.existsById(id)) {
            throw new EntityNotFoundException("Receita com o id informado não existe");
        }

        validadoresAtualizacao.forEach(validador -> validador.validar(id, dadosAtualizacao));

        Receita receita = receitaRepository.getReferenceById(id);

        return receita.atualizar(dadosAtualizacao);
    }

    public void deletar(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new EntityNotFoundException("Receita com o id informado não existe");
        }

        receitaRepository.deleteById(id);
    }
}
