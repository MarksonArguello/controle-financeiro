package com.markson.controlefinanceiro.domain.despesa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    @Query("""
    SELECT COUNT(*) > 0 FROM Despesa d
    WHERE d.descricao ILIKE :descricao
        AND EXTRACT(MONTH FROM d.data)=:mes
    """)
    Boolean existsByDescricaoAndMes(String descricao, int mes);
}
