package com.markson.controlefinanceiro.infra.documentation;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Documentação Controle Financeiro REST API",
                description = "Uma API REST desenvolvida para um CRUD de controle financeiro. Nela é possível registrar receitas e despesas mensais além da possibilidade de criar um resumo contendo os gastos por categoria e total de receitas de um determinado mês.\n",
                version = "v1",
                contact = @Contact(
                        name = "Markson Arguello",
                        email = "marksonarguello@gmail.com",
                        url = "https://www.linkedin.com/in/marksonarguello/"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        )
)
public class OpenAPIConfiguration {
}