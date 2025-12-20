Atue como meu Senior Backend Engineer e Mentor Técnico. Estou em transição de carreira de Node.js para Java/Spring Boot e este é meu projeto de portfólio para demonstrar senioridade e boas práticas.

Eu preciso que você me guie passo a passo na construção deste projeto do ZERO. Não quero que você apenas cuspa código; quero que você explique o "porquê" de cada decisão arquitetural, fazendo paralelos com o ecossistema Node.js quando possível.

## 📋 O PROJETO: TASK MANAGER API (A Verdade Única)
Aqui estão as especificações exatas que devo seguir. Use isso como sua "Bíblia" de regras de negócio:

**CONTEXTO**
Estou desenvolvendo uma API REST de Gerenciamento de Tarefas em Java + Spring Boot para compor meu portfólio como Desenvolvedor Backend Júnior.
Atualmente estou em transição de Node.js para o ecossistema Java (estudando na Alura) e preciso de projetos práticos.

**OBJETIVOS**
- Técnico: Construir uma API REST completa (CRUD, Auth, Relacionamentos, Testes, Docker).
- Profissional: Ter um projeto "showcase" no GitHub.
- Aprendizado: Praticar Spring Boot, JPA, Security, TDD e Clean Architecture.

**ESCOPO FUNCIONAL (MVP)**
1. Autenticação e Autorização:
    - Cadastro (nome, email, senha) e Login com JWT.
    - Perfis: USER (acessa seus dados) e ADMIN (vê tudo).
2. Gestão de Projetos:
    - CRUD completo (Título, descrição, data criação).
    - Relacionamento: 1 usuário possui N projetos.
3. Gestão de Tarefas:
    - CRUD dentro de projetos.
    - Campos: título, descrição, status (TODO, IN_PROGRESS, DONE), prioridade (LOW, MEDIUM, HIGH), data vencimento.
    - Relacionamento: 1 projeto -> N tarefas.
4. Recursos Adicionais:
    - Paginação, Filtros, Soft Delete, Validações (@Valid), Swagger.

**REQUISITOS TÉCNICOS**
- Obrigatórios: Clean Code, Tratamento de Exceções Global, Senhas com BCrypt, JWT Stateless, Testes (Unitários/Integração), Flyway, Docker Compose.

---

## 🛠 ESTADO ATUAL (O que já configurei)
Gerei o projeto via **Spring Initializr** e descompactei.
- **Stack:** Maven, Java 21 (LTS), Spring Boot 3.5.9.
- **Config:** `application.yml` (com variáveis de ambiente) e `compose.yaml` (PostgreSQL).

**Dependências já instaladas (pom.xml):**
Spring Web, Spring Data JPA, PostgreSQL Driver, Validation, Spring Security, Flyway Migration, Lombok, DevTools, Docker Compose Support.
*(Sei que faltam as libs do Swagger e JWT, vamos adicionar depois)*

---

## 📝 SUA PRIMEIRA MISSÃO
Antes de codar:

1. **Crie um arquivo** chamado `GEMINI_CONTEXT.md` na raiz do projeto.
    - Copie todo o conteúdo da seção "O PROJETO" acima para dentro dele. Adicione também um checklist macro das etapas que seguiremos.
2. **Atualize o `.gitignore`**:
    - Me instrua a adicionar o `GEMINI_CONTEXT.md` no `.gitignore` para que esse arquivo de contexto interno não suba para o GitHub.

Estou pronto. Me diga o que fazer.# TaskManager Project Context

## Project Overview

**TaskManager** is a RESTful API designed for task management. It is built using **Java 21** and **Spring Boot 3.5.9**, leveraging a modern stack for data persistence, security, and development efficiency.

### Key Technologies

*   **Language:** Java 21
*   **Framework:** Spring Boot 3.5.9
*   **Build Tool:** Maven
*   **Database:** PostgreSQL (with Flyway for migrations)
*   **ORM:** Spring Data JPA
*   **Security:** Spring Security
*   **Utilities:** Lombok, Spring Validation
*   **Infrastructure:** Docker Compose (integrated with Spring Boot)

## Building and Running

### Prerequisites

*   **Java Development Kit (JDK) 21**
*   **Docker Desktop** (or Docker Engine + Compose plugin) - Required for the database.

### Running the Application

This project uses `spring-boot-docker-compose`, which automatically manages the lifecycle of the Docker services defined in `compose.yaml` when the application starts.

1.  **Start the Application:**
    ```bash
    ./mvnw spring-boot:run
    ```
    *This command will automatically spin up the PostgreSQL container if Docker is running.*

2.  **Access the API:**
    The application will start on the default port (usually 8080).

### Building the Project

To compile the code and run tests:

```bash
./mvnw clean install
```

## Development Conventions

*   **Lombok:** The project uses Lombok to reduce boilerplate code (getters, setters, constructors). Ensure your IDE has the Lombok plugin installed and annotation processing enabled.
*   **Database Migrations:** Flyway is used for database version control. Migration scripts should be placed in `src/main/resources/db/migration` following the naming convention `V<VERSION>__<DESCRIPTION>.sql`.
*   **Security:** Spring Security is included. Be aware that by default, it secures all endpoints. Configure `SecurityConfig` to customize access rules.
*   **Docker Compose:** The `compose.yaml` file defines the local development environment (PostgreSQL).
    *   **Database:** `mydatabase`
    *   **User:** `myuser`
    *   **Password:** `secret`

## Project Structure

*   `src/main/java`: Application source code.
*   `src/main/resources`: Configuration (`application.yaml`) and resources.
*   `src/test`: Unit and integration tests.
*   `compose.yaml`: Docker Compose definition for local dependencies.
