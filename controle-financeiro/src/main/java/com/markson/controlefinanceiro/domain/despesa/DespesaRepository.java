package com.markson.controlefinanceiro.domain.despesa;

import com.markson.controlefinanceiro.domain.resumo.GastoPorCategoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    Despesa findDespesaByDescricaoIsLikeIgnoreCaseAndValor(String descricao, BigDecimal valor);

    @Query("""
    SELECT COUNT(*) > 0 FROM Despesa d
    WHERE d.descricao ILIKE :descricao
        AND EXTRACT(MONTH FROM d.data)=:mes
    """)
    Boolean existsByDescricaoAndMes(String descricao, int mes);

    @Query("""
    SELECT COUNT(*) > 0 FROM Despesa d
    WHERE d.id!=:id
        AND EXTRACT(MONTH FROM d.data)=:mes
        AND d.descricao ILIKE :descricao
    """)
    Boolean existsByDescricaoAndMesAndIdDiferente(String descricao, int mes, Long id);

    Page<Despesa> findByDescricaoContainingIgnoreCase(Pageable paginacao, String descricao);

    @Query("""
    SELECT d FROM Despesa d
    WHERE EXTRACT(YEAR FROM d.data)=:ano
    AND EXTRACT(MONTH FROM d.data)=:mes
    """)
    Page<Despesa> findByAnoAndMes(int ano, int mes, Pageable paginacao);

    @Query("""
    SELECT SUM(d.valor) FROM Despesa d
    WHERE EXTRACT(YEAR FROM d.data)=:ano
    AND EXTRACT(MONTH FROM d.data)=:mes
    """)
    BigDecimal valorTotalDespesasNoMes(int ano, int mes);

    @Query("""
    SELECT new com.markson.controlefinanceiro.domain.resumo.GastoPorCategoria(d.categoria, SUM(d.valor)) FROM Despesa d
    WHERE EXTRACT(YEAR FROM d.data)=:ano
    AND EXTRACT(MONTH FROM d.data)=:mes
    GROUP BY d.categoria
    """)
    List<GastoPorCategoria> gastoPorCategoriaNoMes(int ano, int mes);
}
