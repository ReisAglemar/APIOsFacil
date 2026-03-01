# 🚀 APIosFacil – Módulo de Usuário

API REST desenvolvida em Java com arquitetura em camadas, organizada por responsabilidade e preparada para múltiplos ambientes de banco de dados.

O projeto segue uma estrutura modular clara, separando configuração, domínio, controle, serviço e infraestrutura.

---

## 🎯 Objetivo

- Implementar uma API REST para gerenciamento de usuários
- Aplicar arquitetura em camadas
- Trabalhar com DTOs para controle de entrada e saída
- Implementar tratamento global de erros
- Configurar múltiplos bancos de dados (H2 e PostgreSQL)
- Documentar a API com OpenAPI

---

## 🏗 Arquitetura

A aplicação segue o padrão:

Controller → Service → Repository → Model

Separação por responsabilidade:

- **Controller**: Camada de entrada HTTP
- **Service**: Regras de negócio
- **Repository**: Persistência de dados
- **Domain**: Entidades e DTOs
- **Config**: Configurações da aplicação
- **Infra**: Tratamento técnico (ex: exceções)

---

## 📁 Estrutura do Projeto

    ├── .idea
    │   ├── .gitignore
    │   ├── compiler.xml
    │   ├── encodings.xml
    │   ├── jarRepositories.xml
    │   ├── misc.xml
    │   └── vcs.xml
    │
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── com
    │   │   │       └── APIosFacil
    │   │   │           └── usuario
    │   │   │               ├── config
    │   │   │               │   ├── openApi
    │   │   │               │   ├── H2Config.java
    │   │   │               │   └── PostgreSqlConfig.java
    │   │   │               │
    │   │   │               ├── controller
    │   │   │               │   └── UsuarioController.java
    │   │   │               │
    │   │   │               ├── domain
    │   │   │               │   ├── dto
    │   │   │               │   │   ├── AtualizaUsuarioDto.java
    │   │   │               │   │   ├── CadastraUsuarioDto.java
    │   │   │               │   │   ├── DetalhaUsuarioDto.java
    │   │   │               │   │   └── ListaUsuarioDto.java
    │   │   │               │   │
    │   │   │               │   ├── model
    │   │   │               │   │   └── UsuarioEntity.java
    │   │   │               │   │
    │   │   │               │   └── repository
    │   │   │               │
    │   │   │               ├── infra
    │   │   │               │   └── TratamentoDeErros.java
    │   │   │               │
    │   │   │               ├── service
    │   │   │               │
    │   │   │               └── OsFacilApplication.java
    │   │   │
    │   │   └── resources
    │   │       ├── ValidationMessages.properties
    │   │       └── application.properties
    │   │
    │   └── test
    │       └── java
    │           └── com
    │               └── APIosFacil
    │                   └── usuario
    │
    ├── target
    │   ├── classes
    │   │   ├── com
    │   │   │   └── APIosFacil
    │   │   │       └── usuario
    │   │   ├── ValidationMessages.properties
    │   │   └── application.properties
    │   │
    │   └── test-classes
    │       └── com
    │           └── APIosFacil
    │               └── usuario
    │                   ├── config
    │                   ├── controller
    │                   ├── domain
    │                   ├── infra
    │                   ├── service
    │                   └── OsFacilApplicationTests.class
    │
    ├── .gitignore
    ├── HELP.md
    ├── README.md
    ├── mvnw
    ├── mvnw.cmd
    └── pom.xml
---

## 🧠 Conceitos Aplicados

- Arquitetura em camadas
- DTO Pattern
- Tratamento global de exceções
- Configuração de múltiplos ambientes de banco
- Validação de dados
- Documentação com OpenAPI
- Separação entre domínio e infraestrutura
- Boas práticas de organização de pacotes

---

## 🗄 Banco de Dados

A aplicação possui configuração para:

- H2 (ambiente de desenvolvimento/testes)
- PostgreSQL (ambiente de produção)

A configuração pode ser alterada via `application.properties`.

---

## 📦 Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- H2 Database
- PostgreSQL
- OpenAPI / Swagger

---

