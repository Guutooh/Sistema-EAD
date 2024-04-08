# Projeto EAD - Microservices

## Overview
Repositório do projeto EAD (Ensino à Distância), 
uma arquitetura de microsserviços composta pelos serviços AuthUser, Course e Notification. 
Este projeto abrange desde autenticação até a comunicação assíncrona e implementações específicas para um ambiente de ensino online.

## Estrutura do Projeto
1. **AuthUser Microservice:**
    - Autenticação de Usuários
    - Registro e Verificações
    - Segurança com JWT

2. **Curso Microservice:**
    - Gerenciamento de Cursos
    - Relacionamentos e Mapeamentos
    - Comunicação Síncrona e Assíncrona

3. **Notification Microservice:**
    - Notificações Assíncronas
    - Comunicação Event-Driven
    - Configuração com RabbitMQ
  
### AuthUser Microservice:
- Configuração e Base de Dados
- Registro de Usuários
- Validação e Paginação
- Comunicação com Curso Microservice

### Curso Microservice:
- CRUD de Cursos
- Relacionamentos e Entidades
- Comunicação com AuthUser e Notification
- Configuração e Deploy

### Notification Microservice:
- Notificações Assíncronas
- Event-Driven Pattern
- Configuração com RabbitMQ
- Integração com Curso Microservice

## Principais Tecnologias do projeto.

1. **Descodificando Microservices com Spring (Evento):**
   - Introdução aos conceitos de microservices e vantagens do uso do Spring Framework.

2. **Planejamento Arquitetura de Microservices:**
   - Definição dos componentes e comunicações na arquitetura de microservices.

3. **Ecossistema Spring Aplicados a Microservices:**
   - Visão geral das tecnologias e projetos Spring utilizados na arquitetura de microservices.

4. **API RESTFul para Microservices:**
   - Implementação de APIs RESTful com Spring para exposição de funcionalidades dos microservices.

5. **Spring Data JPA Avançado em Microservices:**
   - Utilização avançada do Spring Data JPA para acesso a bancos de dados relacionais.

6. **Spring Logging:**
   - Configuração e utilização de bibliotecas de logging no ecossistema Spring.

7. **API Composition Pattern e Synchronous Communication:**
   - Implementação de padrões de comunicação síncrona entre microservices.

8. **Cross Cutting Service Registry Discovery Pattern:**
   - Utilização do padrão de registro e descoberta de serviços com Spring Cloud Netflix Eureka.

9. **Cross Cutting API Gateway Pattern:**
   - Implementação de um gateway de API com Spring Cloud Gateway para roteamento e balanceamento de carga.

10. **Asynchronous Communication via Command Messages:**
    - Introdução à comunicação assíncrona utilizando mensagens de comando.

11. **Reliability - Circuit Breaker Pattern:**
    - Implementação do padrão de circuit breaker para lidar com falhas em chamadas de serviços remotos.

12. **Cross Cutting - Global Config Management Pattern:**
    - Utilização do Spring Cloud Config para gerenciamento centralizado de configurações.

13. **Authentication e Authorization com Basic Authentication:**
    - Implementação de autenticação básica utilizando Spring Security.

14. **Authentication e Authorization com Json Web Token (JWT):**
    - Implementação de autenticação e autorização com JWT (Json Web Token).

15. **Config Ambientes Dev e Prod e Deploy Microservices no Heroku Cloud:**
    - Configuração e implantação dos microservices em ambientes de desenvolvimento e produção na plataforma Heroku.

16. **Arquitetura Hexagonal:**
    - Abordagem prática para implementação da arquitetura hexagonal em microservices.

17. **Observability - Log Aggregation com Elastic Stack (ELK):**
    - Configuração do Elastic Stack para agregação e análise de logs distribuídos.



## Status do Projeto
🚀 Em andamento...

Este repositório é uma jornada pelo desenvolvimento e implementação de microsserviços do projeto EAD.

---

*Nota: Este README está em constante atualização à medida que novos tópicos são explorados/implementado.*
