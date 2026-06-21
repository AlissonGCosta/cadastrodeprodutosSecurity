# Projeto Avaliativo: API de Produtos com Spring Security

## Objetivo

Desenvolver uma API REST para gerenciamento de produtos, aplicando autenticacao, autorizacao por perfil e boas praticas de organizacao em camadas.

O projeto deve permitir que qualquer pessoa visualize produtos, mas somente usuarios autorizados possam criar, alterar ou remover produtos.

## Tema

Sistema de catalogo e gerenciamento de produtos.

## Tecnologias Sugeridas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Bean Validation
- Banco H2 ou PostgreSQL
- Maven ou Gradle
- Lombok
- JWT
- Swagger/OpenAPI
- JUnit
- Mockito
- MockMvc

Banco recomendado:

- H2 no comeco
- PostgreSQL como desafio extra

## Entidades Principais

### Produto

Campos obrigatorios:

- id
- nome
- descricao
- preco
- quantidadeEstoque
- categoria
- ativo
- dataCriacao
- dataAtualizacao

Regras:

- nome nao pode ser vazio.
- descricao deve ter no minimo 10 caracteres.
- preco deve ser maior que zero.
- quantidadeEstoque nao pode ser negativa.
- categoria nao pode ser vazia.
- produto deve ser criado como ativo.

### Usuario

Campos obrigatorios:

- id
- nome
- email
- senha
- role

Roles:

- USER
- ADMIN

Regras:

- email deve ser unico.
- senha deve ser criptografada com BCrypt.
- todo usuario cadastrado pela rota publica recebe role USER.
- ADMIN pode ser criado via seed inicial ou diretamente no banco.

## Funcionalidades Obrigatorias

### 1. Cadastro de Usuario

Endpoint:

```http
POST /auth/register
```

Body:

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

Regras:

- O endpoint deve ser publico.
- O email nao pode se repetir.
- A senha deve ser salva criptografada com BCrypt.
- O usuario cadastrado deve receber role USER.

### 2. Login

Endpoint:

```http
POST /auth/login
```

Body:

```json
{
  "email": "joao@email.com",
  "senha": "123456"
}
```

Resposta esperada:

```json
{
  "token": "jwt_gerado"
}
```

Regras:

- O endpoint deve ser publico.
- O login deve validar email e senha.
- Em caso de sucesso, deve retornar um token JWT.
- Em caso de erro, deve retornar 401.

### 3. Listar Produtos

Endpoint:

```http
GET /produtos
```

Regras:

- Qualquer pessoa pode acessar.
- Deve listar apenas produtos ativos.

### 4. Buscar Produto Por Id

Endpoint:

```http
GET /produtos/{id}
```

Regras:

- Qualquer pessoa pode acessar.
- Se o produto nao existir, retornar 404.
- Se o produto estiver inativo, retornar 404.

### 5. Cadastrar Produto

Endpoint:

```http
POST /produtos
```

Body:

```json
{
  "nome": "Notebook Dell",
  "descricao": "Notebook Dell com 16GB RAM",
  "preco": 3500.00,
  "quantidadeEstoque": 10,
  "categoria": "Informatica"
}
```

Regras:

- Somente ADMIN pode cadastrar produto.
- Produto deve ser criado como ativo.
- Data de criacao deve ser preenchida automaticamente.

### 6. Atualizar Produto

Endpoint:

```http
PUT /produtos/{id}
```

Regras:

- Somente ADMIN pode atualizar produto.
- Se o produto nao existir, retornar 404.
- Data de atualizacao deve ser preenchida automaticamente.

### 7. Deletar Produto

Endpoint:

```http
DELETE /produtos/{id}
```

Regras:

- Somente ADMIN pode deletar produto.
- A exclusao deve ser logica.
- Ao deletar, alterar o campo `ativo` para `false`.
- Produto deletado nao deve aparecer em `GET /produtos`.

## Regras de Seguranca

Configure o Spring Security da seguinte forma:

```text
POST /auth/register -> publico
POST /auth/login -> publico
GET /produtos -> publico
GET /produtos/{id} -> publico
POST /produtos -> ADMIN
PUT /produtos/{id} -> ADMIN
DELETE /produtos/{id} -> ADMIN
```

Requisitos:

- Usar JWT para autenticacao.
- Usar BCrypt para senha.
- Usar roles USER e ADMIN.
- Retornar 401 quando o usuario nao estiver autenticado.
- Retornar 403 quando o usuario estiver autenticado, mas nao tiver permissao.

## Validacoes Obrigatorias

### Produto

- nome: obrigatorio
- descricao: obrigatoria, minimo 10 caracteres
- preco: obrigatorio, maior que zero
- quantidadeEstoque: obrigatorio, zero ou maior
- categoria: obrigatoria

### Usuario

- nome: obrigatorio
- email: obrigatorio e valido
- senha: obrigatoria, minimo 6 caracteres
- email nao pode repetir

Use Bean Validation com anotacoes como:

```java
@NotBlank
@NotNull
@Email
@Size
@Positive
@PositiveOrZero
```

## Tratamento de Erros

Crie um tratamento global de erros com `@RestControllerAdvice`.

A API deve retornar respostas claras para:

- 400: erro de validacao
- 401: nao autenticado
- 403: sem permissao
- 404: recurso nao encontrado
- 409: email ja cadastrado

Exemplo:

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

## Organizacao do Projeto

Sugestao de pacotes:

```text
com.seuprojeto.produtos
├── config
├── controller
├── dto
├── entity
├── repository
├── security
├── service
└── exception
```

Responsabilidades:

- controller: recebe requisicoes e retorna respostas
- service: regras de negocio
- repository: acesso ao banco
- dto: objetos de entrada e saida
- entity: entidades JPA
- security: JWT, filtros e configuracao do Spring Security
- exception: tratamento de erros
- config: configuracoes gerais

## Requisitos de Entrega

Voce deve entregar:

- Codigo-fonte completo
- README com instrucoes de execucao
- Script ou seed para criar usuario ADMIN
- Collection do Postman ou exemplos de requisicoes
- Testes automatizados

O README deve conter:

- nome do projeto
- descricao
- tecnologias utilizadas
- como rodar o projeto
- como acessar o banco H2, se usar H2
- endpoints disponiveis
- usuario ADMIN padrao
- exemplos de login
- exemplos de uso do token JWT

## Testes Obrigatorios

Crie testes para:

- Cadastrar usuario com sucesso
- Nao permitir email duplicado
- Login com credenciais validas
- Nao permitir cadastrar produto sem token
- Nao permitir usuario USER cadastrar produto
- Permitir ADMIN cadastrar produto
- Listar produtos sem autenticacao
- Buscar produto inexistente retorna 404
- Deletar produto altera ativo para false

## Criterios de Avaliacao

- CRUD de produtos funcionando: 2 pontos
- Autenticacao com JWT: 2 pontos
- Autorizacao por roles: 2 pontos
- Validacoes e tratamento de erros: 1,5 ponto
- Organizacao em camadas e uso de DTOs: 1 ponto
- Testes automatizados: 1 ponto
- README e documentacao: 0,5 ponto

Total: 10 pontos

## Desafio Extra

Para melhorar o projeto, implemente:

- Paginacao em `GET /produtos`
- Filtro por categoria
- Busca por nome
- Ordenacao por preco
- Swagger/OpenAPI
- Deploy no Render, Railway ou Docker
- Refresh token
- Controle de estoque ao vender produto

## Entrega Minima Aceitavel

Se quiser uma versao menor para comecar, implemente primeiro:

- Cadastro e login de usuario
- JWT
- CRUD de produtos
- ADMIN cria, atualiza e deleta
- Publico lista e busca produtos
- Senha com BCrypt
- Validacoes basicas
