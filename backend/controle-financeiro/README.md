> **Tecnologias**: Spring Boot, Postgres, Spring Security, JWT, JPA, Rest API, Docker
> 
# Controle financeiro

Uma API REST desenvolvida para um CRUD de controle financeiro. Nela é possível registrar receitas e despesas mensais além da possibilidade de criar um resumo contendo os gastos por categoria e total de receitas de um determinado mês.

## Manual de instalação

**1. Clone o repositório**

```bash
git clone https://github.com/MarksonArguello/controle-financeiro.git
```

**2. Adicionar as variáveis de ambiente**

```bash
    export DATASOURCE_USERNAME=username
    export DATASOURCE_PASSWORD=password
    export JWT_SECRET=secret
```

**3. Rodar o projeto**
+ abrir `controle-financeiro/backend/controle-financeiro/`
+ compilar o projeto `mvn clean package`
+ rodar o docker compose `docker-compose up -d`
  

O projeto estará rodando em <http://localhost:8080>

## Explore a API

É possível acessar o swagger com a documentação da API em <http://localhost:8080/swagger-ui/>

O projeto possui as seguintes API's CRUD.

### Autenticação

| Método | Url              | Descrição | Exemplo de body de requisição | 
|--------|------------------|-----------|-------------------------------|
| POST   | /login           | Login     | [JSON](#login)                |

### Usuários

| Método | Url       | Descrição           | Exemplo de body de requisição | 
|--------|-----------|---------------------|-------------------------------|
| POST   | /usuarios | Cadastra um usuário | [JSON](#postusuarios)         |

### Receitas

| Método | Url                   | Descrição                                         | Exemplo de body de requisição | 
|--------|-----------------------|---------------------------------------------------|-------------------------------|
| GET    | /receitas             | Recupera todas as receitas cadastradas no sistema |                               |
| POST   | /receitas             | Cadastra uma receita                              | [JSON](#postreceitas)         |
| GET    | /receitas/{id}        | Recupera uma receita com base no id               |                               |
| PUT    | /receitas/{id}        | Altera uma receita com base no id                 | [JSON](#putreceitasid)        |
| DELETE | /receitas/{id}        | Deleta uma receita com base no id                 |                               |
| GET    | /receitas/{ano}/{mes} | Recupera as receitas do mês do ano informado      |                               |

### Despesas

| Método | Url                   | Descrição                                                 | Exemplo de body de requisição | 
|--------|-----------------------|-----------------------------------------------------------|-------------------------------|
| GET    | /despesas             | Recupera todas as despesas cadastradas no sistema         |                               |
| POST   | /despesas             | Cadastra uma despesa                                      | [JSON](#postdespesas)         |
| GET    | /despesas/{id}        | Recupera uma despesa com base no id                       |                               |
| PUT    | /despesas/{id}        | Altera uma despesa com base no id                         | [JSON](#putdespesasid)        |
| DELETE | /despesas/{id}        | Deleta uma despesa com base no id                         |                               |
| GET    | /despesas/{ano}/{mes} | Recupera as despesas do mês do ano informado              |                               |
| GET    | /despesas/categorias  | Recupera as categorias de despesas cadastradas no sistema |                               |

### Resumo

| Método | Url                | Descrição                                                                                                           | 
|--------|--------------------|---------------------------------------------------------------------------------------------------------------------|
| GET    | resumo/{ano}/{mes} | Gera um resumo contendo total de receitas, despesas, saldo e todas as despesas por categorias de um determinado mês | 

## Exemplo de body de requisição JSON válido

##### <a id="login">Log in -> /login</a>
```json
{
  "login": "Louise", 
  "senha": "12345678"
}
```

##### <a id="postusuarios">Cadastrar usuário -> /usuarios</a>
```json
{
  "login": "Louise", 
  "senha": "12345678"
}
```

##### <a id="postreceitas">Cadastrar receita-> /receitas</a>
```json
{
  "descricao": "Salário",
  "valor": 600.00,
  "data": "2023-08-10T10:00:00"
}
```

##### <a id="putreceitasid">Atualizar receita-> /receitas/{id}</a>
```json
{
  "descricao": "Rendimentos",
  "valor": 260.00,
  "data": "2023-09-01T10:00:00"
}
```

##### <a id="postdespesas">Cadastrar despesa-> /despesas</a>
```json
{
  "descricao": "Conta de energia",
  "valor": 600.00,
  "data": "2023-08-10",
  "categoria": "MORADIA"
}
```

##### <a id="putdespesasid">Atualizar despesa-> /despesas/{id}</a>
```json
{
  "descricao": "Conta de energia",
  "valor": 500.00,
  "data": "2023-08-10"
}
```