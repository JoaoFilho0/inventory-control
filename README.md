# Inventory Control

Aplicação Java com Spring Boot para controle de clientes, produtos e compras.

## Tecnologias

- Java 25
- Spring Boot
- Spring Data JPA
- Flyway
- MySQL
- Docker Compose

## Requisitos

- Java 25 instalado
- Docker e Docker Compose
- Maven Wrapper incluído no projeto (`./mvnw`)

## Como executar localmente

### 1. Suba o banco de dados

O projeto já possui um `docker-compose.yml` configurado com MySQL.

Execute:

```bash
docker compose up -d
```

Isso irá subir:

- MySQL 8
- banco `inventory_control`
- usuário `root`
- senha `1234`
- porta `3307` no host

### 2. Inicie a aplicação

Com o banco em execução, rode:

```bash
./mvnw spring-boot:run
```

A aplicação ficará disponível em:

```text
http://localhost:8080
```

## Configuração atual

O projeto já está configurado em `src/main/resources/application.properties` para usar:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/inventory_control?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=1234
```

As migrations do banco ficam em:

```text
src/main/resources/db/migration
```

O Flyway executa automaticamente na inicialização da aplicação.

## Como parar o banco

```bash
docker compose down
```

Se quiser remover também o volume com os dados:

```bash
docker compose down -v
```

## Executar testes

```bash
./mvnw test
```

## Endpoints principais

### Cliente

- `POST /cliente`
- `GET /cliente`
- `GET /cliente/{id}`
- `PUT /cliente/{id}`
- `DELETE /cliente/{id}`

Exemplo de criação:

```json
{
  "nome": "Joao Silva"
}
```

### Produto

- `POST /produto`
- `GET /produto`
- `GET /produto/{id}`
- `PUT /produto/{id}`
- `DELETE /produto/{id}`

Exemplo de criação:

```json
{
  "nome": "Notebook Dell",
  "quantidade": 10,
  "preco": 3500.0,
  "descricao": "Notebook para uso corporativo"
}
```

### Compra

- `POST /compra`
- `GET /compra`
- `GET /compra/{id}`
- `PUT /compra/{id}`
- `DELETE /compra/{id}`

Exemplo de criação:

```json
{
  "clienteId": 1,
  "produtoId": 1
}
```

## Regras atuais da compra

- uma compra consome `1` unidade do produto
- a compra só é salva se houver estoque disponível
- ao excluir uma compra, `1` unidade volta para o estoque

## Estrutura do projeto

- `src/main/java/com/inventorycontrol/controller`: endpoints REST
- `src/main/java/com/inventorycontrol/service`: regras de negócio
- `src/main/java/com/inventorycontrol/repository`: acesso a dados
- `src/main/java/com/inventorycontrol/model`: entidades JPA
- `src/main/java/com/inventorycontrol/dto`: DTOs de entrada
- `src/main/resources/db/migration`: migrations do Flyway
- `docker-compose.yml`: ambiente local do MySQL
