package com.markson.controlefinanceiro.domain.receita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


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
}
