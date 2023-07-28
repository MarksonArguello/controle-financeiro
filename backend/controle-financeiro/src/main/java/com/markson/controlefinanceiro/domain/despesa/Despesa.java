package com.markson.controlefinanceiro.domain.despesa;

import com.markson.controlefinanceiro.domain.despesa.dto.DadosAtualizacaoDespesa;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosCadastramentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.enums.Categoria;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Despesa")
@Table(name = "despesas")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private Categoria categoria = Categoria.OUTRAS;

    public Despesa(Long id, String descricao, BigDecimal valor, LocalDateTime data, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;

        if (categoria  != null)
            this.categoria = categoria;
    }

    public Despesa(DadosCadastramentoDespesa dadosCadastramento) {
        this(null, dadosCadastramento.descricao(), dadosCadastramento.valor(), dadosCadastramento.data(), dadosCadastramento.categoria());
    }

    public void atualizar(DadosAtualizacaoDespesa dadosAtualizacao) {
        this.data = dadosAtualizacao.data();
        this.descricao = dadosAtualizacao.descricao();
        this.valor = dadosAtualizacao.valor();
    }
}
