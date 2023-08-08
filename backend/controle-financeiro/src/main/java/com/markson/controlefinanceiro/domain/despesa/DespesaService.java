package com.markson.controlefinanceiro.domain.despesa;

import com.markson.controlefinanceiro.domain.despesa.dto.DadosAtualizacaoDespesa;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosCadastramentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosDetalhamentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.enums.Categoria;
import com.markson.controlefinanceiro.domain.despesa.validacao.atualizacao.ValidadorAtulizacaoDespesa;
import com.markson.controlefinanceiro.domain.despesa.validacao.cadastro.ValidadorCadastroDespesa;
import com.markson.controlefinanceiro.domain.exception.ValidacaoException;
import com.markson.controlefinanceiro.domain.resumo.GastoPorCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class DespesaService {
    private final DespesaRepository despesaRepository;
    private final List<ValidadorCadastroDespesa> validadoresCadastro;

    private final List<ValidadorAtulizacaoDespesa> validadoresAtualizacao;

    @Autowired
    public DespesaService(DespesaRepository despesaRepository, List<ValidadorCadastroDespesa> validadoresCadastro, List<ValidadorAtulizacaoDespesa> validadoresAtualizacao) {
        this.despesaRepository = despesaRepository;
        this.validadoresCadastro = validadoresCadastro;
        this.validadoresAtualizacao = validadoresAtualizacao;
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

    public Page<DadosDetalhamentoDespesa> listar(Pageable paginacao, String descricao) {
        Page<Despesa> despesas = despesaRepository.findByDescricaoContainingIgnoreCase(paginacao,descricao);

        return despesas
                .map(DadosDetalhamentoDespesa::new);
    }

    public List<Categoria> listarCategorias() {
        return Arrays.asList(Categoria.class.getEnumConstants());
    }

    public DadosDetalhamentoDespesa atualizar(Long id, DadosAtualizacaoDespesa dadosAtualizacao) {
        if (!despesaRepository.existsById(id)) {
            throw new ValidacaoException("Despesa com o id informado não existe");
        }

        validadoresAtualizacao.forEach(validador -> validador.validar(id, dadosAtualizacao));

        Despesa despesa = despesaRepository.getReferenceById(id);
        despesa.atualizar(dadosAtualizacao);
        return new DadosDetalhamentoDespesa(despesa);
    }

    public void deletar(Long id) {
        if (!despesaRepository.existsById(id)) {
            throw new ValidacaoException("Despesa com o id informado não existe");
        }

        despesaRepository.deleteById(id);
    }

    public Page<DadosDetalhamentoDespesa> listarReceitaDoMes(int ano, int mes, Pageable paginacao) {
        Page<Despesa> despesas = despesaRepository.findByAnoAndMes(ano, mes, paginacao);
        return despesas
                .map(DadosDetalhamentoDespesa::new);
    }

    public BigDecimal valorTotalDespesasNoMes(int ano, int mes) {
        BigDecimal total = despesaRepository.valorTotalDespesasNoMes(ano, mes);

        if (total == null)
            return BigDecimal.ZERO;

        return total;
    }

    public List<GastoPorCategoria> gastoPorCategoriaNoMes(int ano, int mes) {
        return despesaRepository.gastoPorCategoriaNoMes(ano, mes);
    }
}
