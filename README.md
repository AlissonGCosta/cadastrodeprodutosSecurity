# API de Produtos com Spring Security

## Descricao

API REST para cadastro, consulta, atualizacao e exclusao logica de produtos, com autenticacao via JWT, autorizacao por perfil e organizacao em camadas.

O sistema permite que qualquer pessoa visualize produtos ativos, mas somente usuarios com perfil `ADMIN` podem criar, alterar ou remover produtos.

## Tecnologias

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Security
- Bean Validation
- Maven
- Banco de dados H2, MySQL ou PostgreSQL
- JWT
- BCrypt
- JUnit
- Mockito
- MockMvc
- Swagger/OpenAPI, opcional

## Funcionalidades Que o Projeto Deve Ter

- Cadastro publico de usuarios.
- Login com email e senha.
- Geracao de token JWT no login.
- Senha criptografada com BCrypt.
- Roles `USER` e `ADMIN`.
- Listagem publica de produtos ativos.
- Busca publica de produto ativo por id.
- Cadastro de produto permitido apenas para `ADMIN`.
- Atualizacao de produto permitida apenas para `ADMIN`.
- Exclusao logica de produto permitida apenas para `ADMIN`.
- Validacoes com Bean Validation.
- Tratamento global de erros com `@RestControllerAdvice`.
- Seed ou script para criacao de usuario `ADMIN`.
- Testes automatizados dos principais fluxos.

## Estrutura Sugerida

```text
src/main/java
└── br/com/costa/cadastrodeprodutosSecurity
    ├── config
    ├── controller
    ├── dto
    ├── entity
    ├── exception
    ├── repository
    ├── security
    └── service
```

Responsabilidades:

- `controller`: recebe requisicoes HTTP e retorna respostas.
- `service`: concentra regras de negocio.
- `repository`: acesso ao banco de dados.
- `dto`: objetos de entrada e saida da API.
- `entity`: entidades JPA.
- `security`: configuracao do Spring Security, JWT e filtros.
- `exception`: tratamento global de erros.
- `config`: configuracoes gerais e seed inicial.

## Entidades

### Produto

Campos:

- `id`
- `nome`
- `descricao`
- `preco`
- `quantidadeEstoque`
- `categoria`
- `ativo`
- `dataCriacao`
- `dataAtualizacao`

Regras:

- `nome` e obrigatorio.
- `descricao` e obrigatoria e deve ter no minimo 10 caracteres.
- `preco` e obrigatorio e deve ser maior que zero.
- `quantidadeEstoque` e obrigatoria e nao pode ser negativa.
- `categoria` e obrigatoria.
- Produto deve ser criado com `ativo = true`.
- Exclusao deve ser logica, alterando `ativo` para `false`.

### Usuario

Campos:

- `id`
- `nome`
- `email`
- `senha`
- `role`

Regras:

- `nome` e obrigatorio.
- `email` e obrigatorio, valido e unico.
- `senha` e obrigatoria e deve ter no minimo 6 caracteres.
- Senha deve ser salva com BCrypt.
- Cadastro publico sempre cria usuario com role `USER`.
- Usuario `ADMIN` deve ser criado via seed inicial ou diretamente no banco.

## Como Rodar o Projeto

### Pre-requisitos

- Java 21 instalado.
- Maven instalado ou uso do wrapper `mvnw`.
- Banco configurado no `application.properties` ou `application.yml`.

### Executar

No Windows:

```bash
./mvnw.cmd spring-boot:run
```

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

A API deve ficar disponivel em:

```text
http://localhost:8080
```

## Banco de Dados

Se usar H2, habilite o console no arquivo de configuracao:

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Acesso esperado:

```text
http://localhost:8080/h2-console
```

Se usar MySQL ou PostgreSQL, configure URL, usuario e senha no arquivo de configuracao do Spring.

## Usuario ADMIN Padrao

O projeto deve possuir um usuario administrador criado por seed inicial ou script SQL.

Exemplo:

```text
Nome: Administrador
Email: admin@email.com
Senha: 123456
Role: ADMIN
```

A senha deve ser armazenada criptografada no banco.

## Endpoints

### Autenticacao

| Metodo | Endpoint | Acesso | Descricao |
| --- | --- | --- | --- |
| POST | `/auth/register` | Publico | Cadastrar usuario |
| POST | `/auth/login` | Publico | Fazer login e obter JWT |

### Produtos

| Metodo | Endpoint | Acesso | Descricao |
| --- | --- | --- | --- |
| GET | `/produtos` | Publico | Listar produtos ativos |
| GET | `/produtos/{id}` | Publico | Buscar produto ativo por id |
| POST | `/produtos` | ADMIN | Cadastrar produto |
| PUT | `/produtos/{id}` | ADMIN | Atualizar produto |
| DELETE | `/produtos/{id}` | ADMIN | Excluir produto logicamente |

## Exemplos de Requisicoes

### Cadastrar Usuario

```http
POST /auth/register
Content-Type: application/json
```

```json
{
  "nome": "Joao Silva",
  "email": "joao@email.com",
  "senha": "123456"
}
```

Resposta esperada:

```json
{
  "id": 1,
  "nome": "Joao Silva",
  "email": "joao@email.com",
  "role": "USER"
}
```

### Login

```http
POST /auth/login
Content-Type: application/json
```

```json
{
  "email": "admin@email.com",
  "senha": "123456"
}
```

Resposta esperada:

```json
{
  "token": "jwt_gerado"
}
```

### Usar o Token JWT

Para acessar endpoints protegidos, envie o token no header `Authorization`:

```http
Authorization: Bearer jwt_gerado
```

### Cadastrar Produto

```http
POST /produtos
Content-Type: application/json
Authorization: Bearer jwt_gerado
```

```json
{
  "nome": "Notebook Dell",
  "descricao": "Notebook Dell com 16GB RAM",
  "preco": 3500.00,
  "quantidadeEstoque": 10,
  "categoria": "Informatica"
}
```

## Regras de Seguranca

| Rota | Acesso |
| --- | --- |
| `POST /auth/register` | Publico |
| `POST /auth/login` | Publico |
| `GET /produtos` | Publico |
| `GET /produtos/{id}` | Publico |
| `POST /produtos` | ADMIN |
| `PUT /produtos/{id}` | ADMIN |
| `DELETE /produtos/{id}` | ADMIN |

Retornos esperados:

- `401 Unauthorized`: usuario nao autenticado.
- `403 Forbidden`: usuario autenticado, mas sem permissao.

## Tratamento de Erros

A API deve retornar respostas claras para:

- `400`: erro de validacao.
- `401`: nao autenticado.
- `403`: sem permissao.
- `404`: recurso nao encontrado.
- `409`: email ja cadastrado.

Exemplo de erro de validacao:

```json
{
  "status": 400,
  "erro": "Erro de validacao",
  "campos": {
    "nome": "O nome e obrigatorio",
    "preco": "O preco deve ser maior que zero"
  }
}
```

## Testes Obrigatorios

O projeto deve conter testes para:

- Cadastrar usuario com sucesso.
- Nao permitir email duplicado.
- Login com credenciais validas.
- Nao permitir cadastrar produto sem token.
- Nao permitir usuario `USER` cadastrar produto.
- Permitir `ADMIN` cadastrar produto.
- Listar produtos sem autenticacao.
- Buscar produto inexistente retorna `404`.
- Deletar produto altera `ativo` para `false`.

Para executar os testes:

```bash
./mvnw test
```

No Windows:

```bash
./mvnw.cmd test
```

## Desafios Extras

- Paginacao em `GET /produtos`.
- Filtro por categoria.
- Busca por nome.
- Ordenacao por preco.
- Swagger/OpenAPI.
- Deploy no Render, Railway ou Docker.
- Refresh token.
- Controle de estoque ao vender produto.

## Criterios de Avaliacao

- CRUD de produtos funcionando: 2 pontos.
- Autenticacao com JWT: 2 pontos.
- Autorizacao por roles: 2 pontos.
- Validacoes e tratamento de erros: 1,5 ponto.
- Organizacao em camadas e uso de DTOs: 1 ponto.
- Testes automatizados: 1 ponto.
- README e documentacao: 0,5 ponto.

Total: 10 pontos.
