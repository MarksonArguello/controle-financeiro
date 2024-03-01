package com.markson.controlefinanceiro.domain.receita;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;


public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    @Query("""
    SELECT COUNT(*) > 0 FROM Receita r
    WHERE r.descricao=:descricao
    AND EXTRACT(MONTH FROM r.data)=:mes
    """)
    Boolean existsByDescricaoAndMes(String descricao, int mes);

    @Query("""
    SELECT COUNT(*) > 0 FROM Receita r
    WHERE r.id!=:id
    AND r.descricao=:descricao
    AND EXTRACT(MONTH FROM r.data)=:mes
    """)
    Boolean existsByDescricaoAndMesAndIdDiferente(String descricao, int mes, Long id);

    Page<Receita> findByDescricaoContainingIgnoreCase(Pageable paginacao, String descricao);

    @Query("""
    SELECT r FROM Receita r
    WHERE EXTRACT(YEAR FROM r.data)=:ano
    AND EXTRACT(MONTH FROM r.data)=:mes
    """)
    Page<Receita> findByAnoAndMes(int ano, int mes, Pageable paginacao);

    @Query("""
    SELECT SUM(r.valor) FROM Receita r
    WHERE EXTRACT(YEAR FROM r.data)=:ano
    AND EXTRACT(MONTH FROM r.data)=:mes
    """)
    BigDecimal valorTotalReceitasNoMes(int ano, int mes);
}
