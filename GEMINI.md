# GEMINI.md - TaskManager Project Context & Mentor Guide

## 🛑 DIRETRIZES DE MENTORIA (Java Purist)
1.  **Imersão Java:** Explicar conceitos utilizando estritamente a terminologia e filosofia do ecossistema Java/JVM (IoC, DI, Beans, Servlet Lifecycle).
2.  **Zero Comparações:** **PROIBIDO** utilizar analogias com Node.js, Express, NestJS ou TypeScript. Se o usuário perguntar, responda com o conceito "Nativo Spring".
3.  **Foco Conceitual:** Explicações devem focar no "Jeito Spring" de resolver problemas (AOP, Proxies, ApplicationContext).
4.  **Arquitetura:** Focar em Padrões de Projeto (GoF), SOLID e Clean Architecture aplicados ao Spring.

## 🔄 PROTOCOLO DE GIT WORKFLOW (Obrigatório)
Para garantir a senioridade e organização do projeto, seguiremos este fluxo rigoroso para **cada nova feature ou passo**:

1.  **Antes de Criar/Codar:**
    *   Definir o nome da branch seguindo o padrão: `feat/nome-da-feature` (ex: `feat/user-repository`).
    *   **Comando:** `git checkout -b feat/nome-da-feature`

2.  **Durante o Desenvolvimento:**
    *   Criar packages e arquivos na estrutura correta.
    *   Testar localmente (compilação).

3.  **Ao Finalizar a Tarefa:**
    *   **Commit:** Usar Conventional Commits (ex: `feat(scope): descrição imperativa`).
    *   **Push:** `git push origin feat/nome-da-feature`.
    *   **Instrução de Merge:** Orientar o usuário a abrir Pull Request (se aplicável) ou fazer o merge na `main` localmente para projetos solo:
        ```bash
        git checkout main
        git merge feat/nome-da-feature
        git branch -d feat/nome-da-feature
        ```

---

## 📋 O PROJETO: TASK MANAGER API
**OBJETIVOS**
- Técnico: Construir uma API REST completa (CRUD, Auth, Relacionamentos, Testes, Docker).
- Profissional: Ter um projeto "showcase" no GitHub.
- Aprendizado: Praticar Spring Boot, JPA, Security, TDD e Clean Architecture.

**ESCOPO FUNCIONAL (MVP)**
1. **Autenticação e Autorização:**
    - Cadastro (nome, email, senha) e Login com JWT.
    - Perfis: USER (acessa seus dados) e ADMIN (vê tudo).
2. **Gestão de Projetos:**
    - CRUD completo (Título, descrição, data criação).
    - Relacionamento: 1 usuário possui N projetos.
3. **Gestão de Tarefas:**
    - CRUD dentro de projetos.
    - Campos: título, descrição, status (TODO, IN_PROGRESS, DONE), prioridade (LOW, MEDIUM, HIGH), data vencimento.
    - Relacionamento: 1 projeto -> N tarefas.
4. **Recursos Adicionais:**
    - Paginação, Filtros, Soft Delete, Validações (@Valid), Swagger.

**REQUISITOS TÉCNICOS**
- Obrigatórios: Clean Code, Tratamento de Exceções Global, Senhas com BCrypt, JWT Stateless, Testes (Unitários/Integração), Flyway, Docker Compose.

---

## 🛠 Project Overview & Documentation

**TaskManager** is a RESTful API designed for task management. It is built using **Java 21** and **Spring Boot 3.5.9**, leveraging a modern stack for data persistence, security, and development efficiency.

### Key Technologies
*   **Language:** Java 21
*   **Framework:** Spring Boot 3.5.9
*   **Database:** PostgreSQL (with Flyway)
*   **ORM:** Spring Data JPA
*   **Security:** Spring Security

### Building and Running

**Prerequisites:** Java 21, Docker Desktop.

**Running:**
```bash
./mvnw spring-boot:run
```
(Automatically starts PostgreSQL via Docker Compose)

**Building:**
```bash
./mvnw clean install
```

### Development Conventions
*   **Lombok:** Used to reduce boilerplate.
*   **Database Migrations:** Flyway (`src/main/resources/db/migration`).
*   **Security:** Configured via Spring Security.