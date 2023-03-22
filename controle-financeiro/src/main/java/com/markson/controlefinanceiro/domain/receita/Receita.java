package com.markson.controlefinanceiro.domain.receita;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private LocalDateTime data;

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
