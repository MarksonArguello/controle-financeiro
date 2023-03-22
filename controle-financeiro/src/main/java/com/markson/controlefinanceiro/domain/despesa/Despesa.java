package com.markson.controlefinanceiro.domain.despesa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Despesa")
@Table(name = "despesas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;

    public Despesa(DadosCadastramentoDespesa dadosCadastramento) {
        this(null, dadosCadastramento.descricao(), dadosCadastramento.valor(), dadosCadastramento.data());
    }
}
