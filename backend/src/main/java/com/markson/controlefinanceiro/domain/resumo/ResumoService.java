package com.markson.controlefinanceiro.domain.resumo;


import com.markson.controlefinanceiro.domain.resumo.dto.DadosDetalhamentoResumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ResumoService {

    private final MontadorDeResumo montadorDeResumo;

    @Autowired
    public ResumoService(MontadorDeResumo montadorDeResumo) {
        this.montadorDeResumo = montadorDeResumo;
    }

    public DadosDetalhamentoResumo resumoDoMes(int ano, int mes) {
        return montadorDeResumo.montar(ano, mes);
    }
}
