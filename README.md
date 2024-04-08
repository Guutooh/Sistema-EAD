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

## Tópicos Principais

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

## Status do Projeto
🚀 Em andamento...

Este repositório é uma jornada pelo desenvolvimento e implementação de microsserviços do projeto EAD.

---

*Nota: Este README está em constante atualização à medida que novos tópicos são explorados/implementado.*
