package com.markson.controlefinanceiro.controller;

import com.markson.controlefinanceiro.ControleFinanceiroApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ContextConfiguration(classes = ControleFinanceiroApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ResumoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    void returns200QuandoDadosValidos() throws Exception {
        // When
        var response = mvc.perform(get("/resumo/1999/03"))
                .andReturn()
                .getResponse();

        // Then
        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.OK.value());

    }
}
