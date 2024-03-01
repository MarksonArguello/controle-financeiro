package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.ControleFinanceiroApplication;
import com.markson.controlefinanceiro.domain.despesa.Despesa;
import com.markson.controlefinanceiro.domain.despesa.DespesaRepository;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosCadastramentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.dto.DadosDetalhamentoDespesa;
import com.markson.controlefinanceiro.domain.despesa.enums.Categoria;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ContextConfiguration(classes = ControleFinanceiroApplication.class)
@ActiveProfiles("test")
public class DespesaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DespesaRepository despesaReposiroty;

    @Autowired
    private JacksonTester<DadosCadastramentoDespesa> dadosCadastradoDespesaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoDespesa> dadosDetalhamentoDespesaJson;

    @Test
    public void teste() {
        assertThat(1).isEqualTo(1);
    }

    @Nested
    class Cadastrar {
        private MockHttpServletResponse cadastrar(DadosCadastramentoDespesa dados) throws Exception {
            return mvc.perform(post("/despesas")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(dadosCadastradoDespesaJson
                                    .write(dados)
                                    .getJson()))
                    .andReturn()
                    .getResponse();
        }

        @Test
        @WithMockUser
        void cadastraNoBancoQuandoInformacoesValidas() throws Exception {
            // Given
            var despesa = new Despesa(null,"Comida", new BigDecimal("1500"),LocalDate.now(), Categoria.ALIMENTACAO);
            var dtoDespesa = new DadosCadastramentoDespesa(despesa.getDescricao(), despesa.getValor(), despesa.getData(), despesa.getCategoria());

            // When
            var response = cadastrar(dtoDespesa);

            // Then
            Despesa despesaCadastrada = despesaReposiroty.findDespesaByDescricaoIsLikeIgnoreCaseAndValor(despesa.getDescricao(), despesa.getValor());
            var jsonEsperado = dadosDetalhamentoDespesaJson
                    .write(new DadosDetalhamentoDespesa(despesaCadastrada))
                    .getJson();

            assertThat(response.getStatus())
                    .isEqualTo(HttpStatus.CREATED.value());
            assertThat(response.getContentAsString())
                    .isEqualTo(jsonEsperado);
        }


        @Test
        @WithMockUser
        void returns400QuandoDespesaJaCadastrada() throws Exception {
            // Given
            var despesa = new Despesa(null, "Remedios", new BigDecimal("500.00"),LocalDate.now(), Categoria.SAUDE);
            var dtoDespesa = new DadosCadastramentoDespesa(despesa.getDescricao(), despesa.getValor(), despesa.getData(), despesa.getCategoria());

            // When
            cadastrar(dtoDespesa);

            var response = cadastrar(dtoDespesa);

            // Then
            assertThat(response.getStatus())
                    .isEqualTo(HttpStatus.BAD_REQUEST.value());
            assertThat(response.getContentAsString())
                    .isEqualTo("Despesa já cadastrada");
        }
    }
}
