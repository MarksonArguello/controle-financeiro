package com.markson.controlefinanceiro.domain.receita;

import com.markson.controlefinanceiro.domain.receita.dto.DadosAtualizacaoReceita;
import com.markson.controlefinanceiro.domain.receita.dto.DadosCadastramentoReceita;
import com.markson.controlefinanceiro.domain.receita.dto.DadosDetalhamentoReceita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "Receita")
@Table(name = "receitas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public Receita(DadosCadastramentoReceita dadosCadastramento) {
        this(
                null,
                dadosCadastramento.descricao(),
                dadosCadastramento.valor(),
                dadosCadastramento.data()
        );
    }

    public DadosDetalhamentoReceita atualizar(DadosAtualizacaoReceita dadosAtualizacao) {
        this.descricao = dadosAtualizacao.descricao();
        this.valor = dadosAtualizacao.valor();
        this.data = dadosAtualizacao.data();

        return new DadosDetalhamentoReceita(this);
    }
}
