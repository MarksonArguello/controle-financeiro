package com.markson.controlefinanceiro.domain.resumo;

import com.markson.controlefinanceiro.domain.despesa.DespesaService;
import com.markson.controlefinanceiro.domain.receita.ReceitaService;
import com.markson.controlefinanceiro.domain.resumo.dto.DadosDetalhamentoResumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MontadorDeResumo {
    private final ReceitaService receitaService;
    private final DespesaService despesaService;

    @Autowired
    public MontadorDeResumo(ReceitaService receitaService, DespesaService despesaService) {
        this.receitaService = receitaService;
        this.despesaService = despesaService;
    }

    public DadosDetalhamentoResumo montar(int ano, int mes) {
        BigDecimal totalReceitas = receitaService.valorTotalReceitasNoMes(ano, mes);
        BigDecimal totalDespesas = despesaService.valorTotalDespesasNoMes(ano, mes);
        BigDecimal saldoFinal = this.saldoFinal(totalReceitas, totalDespesas);
        List<GastoPorCategoria> gastoPorCategoria = despesaService.gastoPorCategoriaNoMes(ano, mes);

        return new DadosDetalhamentoResumo(totalReceitas, totalDespesas, saldoFinal, gastoPorCategoria);
    }

    private BigDecimal saldoFinal(BigDecimal totalReceitas, BigDecimal totalDespesas) {
        BigDecimal saldoFinal = BigDecimal.ZERO;

        if (totalReceitas != null) saldoFinal = saldoFinal.add(totalReceitas);
        if (totalDespesas != null) saldoFinal = saldoFinal.subtract(totalDespesas);

        return saldoFinal;
    }


}
