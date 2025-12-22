# TaskManager API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.9-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## 📌 Sobre o Projeto

O **TaskManager** é uma solução robusta para o gerenciamento organizado de projetos e tarefas. Projetado para garantir produtividade com segurança, o sistema permite que usuários criem espaços de trabalho isolados, onde cada recurso é rigorosamente protegido por camadas de autenticação e autorização. É uma plataforma ideal para quem busca controle total sobre suas atividades, garantindo que os dados sejam acessíveis apenas por seus respectivos proprietários.

---

## 📖 Project Overview

**TaskManager** is an enterprise-grade RESTful API designed to demonstrate advanced backend engineering concepts using the **Spring Boot** ecosystem. 

The project focuses strictly on **Clean Architecture**, **SOLID principles**, and **Security Best Practices** (OWASP). It implements a multi-tenant-like data isolation strategy where resources are strictly protected based on user ownership.

### Key Architectural Highlights
- **Strict Layered Architecture:** Clear separation between Controllers, Services, Repositories, and Entities.
- **Domain-Driven Design (DDD) Hints:** usage of Rich Models (protecting invariants) over Anemic Models.
- **Stateless Authentication:** Custom Security Filter Chain implementing JWT (JSON Web Tokens).
- **Automated Database Migrations:** Version control for database schema using Flyway.
- **Unified Error Handling:** Centralized `@RestControllerAdvice` transforming Java Exceptions into semantic HTTP responses (404, 400, 409).

---

## 🛠 Tech Stack

- **Language:** Java 21 (LTS)
- **Framework:** Spring Boot 3.5.9
- **Database:** PostgreSQL (Containerized)
- **ORM:** Spring Data JPA (Hibernate)
- **Security:** Spring Security 6 + java-jwt (Auth0)
- **Migrations:** Flyway
- **Validation:** Jakarta Validation (Bean Validation)
- **Utilities:** Lombok, Docker Compose

---

## 🚀 Features Implemented

### 1. Authentication & Security
- **Sign Up:** User registration with `BCrypt` password hashing.
- **Login:** Stateless authentication returning `Bearer` JWT tokens (HMAC256).
- **Access Control:** All endpoints (except auth) are protected by a custom `OncePerRequestFilter`.

### 2. Project Management
- **CRUD Operations:** Create, Read, Update (Patch), and Delete projects.
- **Data Isolation:** A user can **only** access or modify projects they created. Accessing another user's project results in a `404 Not Found` (Security by Obscurity/Isolation), not just a 403.
- **Partial Updates:** Semantic `PATCH` implementation for updating specific fields without overwriting the entire resource.

---

## 🏗 Database Schema

The application uses a relational model managed by Flyway migrations:

1.  **Users:** Stores credentials and profile data.
2.  **Projects:** Linked to Users via Foreign Key (`ON DELETE CASCADE`).

```sql
-- V2__create-table-projects.sql (Snippet)
CREATE TABLE projects (
    ...
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_projects_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

---

## ⚡ How to Run

### Prerequisites
- Java 21 SDK
- Docker & Docker Compose

### Fast Start
The project utilizes `spring-boot-docker-compose`. When you run the application, it automatically spins up the PostgreSQL container defined in `compose.yaml`.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/jhonataswillian/taskmanager.git
    cd taskmanager
    ```

2.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

3.  **Access the API:**
    The API will be available at `http://localhost:8080`.

---

## 🔌 API Endpoints

### Authentication
| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `POST` | `/users` | Register a new user | ❌ |
| `POST` | `/auth/login` | Authenticate and retrieve JWT | ❌ |

### Projects
| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `GET` | `/projects` | List all projects owned by the user | ✅ |
| `GET` | `/projects/{id}` | Get specific project details | ✅ |
| `POST` | `/projects` | Create a new project | ✅ |
| `PATCH` | `/projects/{id}` | Update project (Title/Description) | ✅ |
| `DELETE`| `/projects/{id}` | Delete a project | ✅ |

---

## 👨‍💻 Developed by Jhonatas Willian

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/jhonataswillian)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jhonataswillian/)