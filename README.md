# Inventory Control

Aplicação Java com Spring Boot para controle de inventário.

## Requisitos

- Java 25
- Maven Wrapper incluído no projeto (`./mvnw`)
- MySQL

## Banco de dados

O projeto foi configurado para usar **MySQL**, com suporte a:

- Spring Data JPA
- Flyway
- Driver `mysql-connector-j`

As migrations ficam em `src/main/resources/db/migration`.

## Configuração da aplicação

Atualmente o arquivo `src/main/resources/application.properties` contém apenas o nome da aplicação. Antes de executar, adicione as configurações de conexão com o MySQL, por exemplo:

```properties
spring.application.name=inventory-control

spring.datasource.url=jdbc:mysql://localhost:3306/inventory_control
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

## Como executar

1. Crie um banco no MySQL, por exemplo:

```sql
CREATE DATABASE inventory_control;
```

2. Ajuste o `application.properties` com usuário, senha, host e porta do seu ambiente.

3. Execute a aplicação com o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

## Executar testes

```bash
./mvnw test
```

## Estrutura relevante

- `pom.xml`: dependências e versão do Java
- `src/main/java/com/inventorycontrol/InventoryControlApplication.java`: ponto de entrada da aplicação
- `src/main/resources/application.properties`: configuração da aplicação
- `src/main/resources/db/migration`: scripts de migração do banco
