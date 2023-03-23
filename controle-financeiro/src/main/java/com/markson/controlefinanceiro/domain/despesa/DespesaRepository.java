package com.markson.controlefinanceiro.domain.despesa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
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
}
