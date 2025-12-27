# TaskManager API

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.9-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Testcontainers](https://img.shields.io/badge/Testcontainers-Enabled-9B4F96?style=for-the-badge&logo=testcontainers&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

## 📖 About the Project

**TaskManager** is an enterprise-grade RESTful API engineered to demonstrate robust backend development practices using the modern **Spring Boot** ecosystem.

The core philosophy of this project is **Security and Data Isolation**. It implements a multi-tenant-like architecture where resources (Projects and Tasks) are strictly isolated by user ownership. The application was built following **Clean Architecture** principles, enforcing strict separation of concerns and maintainability.

### 🌟 Key Features
- **Stateless Authentication:** Secure login using **JWT (JSON Web Tokens)** with a custom Security Filter Chain.
- **Resource Isolation:** Users can strictly access only their own data. Accessing another user's resource results in a `404 Not Found` (Security by Obscurity) rather than just `403 Forbidden`.
- **Database Migrations:** Automated schema versioning with **Flyway**.
- **Unified Error Handling:** Centralized `@RestControllerAdvice` that transforms Java Exceptions into semantic HTTP responses (400, 404, 409, 500).
- **Interactive Documentation:** Fully documented API with **Swagger UI (OpenAPI)**.

---

## 🛠 Tech Stack & Architecture

The project adheres to the standard **Layered Architecture**:
`Controller` -> `Service` -> `Repository` -> `Database`.

| Component | Technology | Description |
| :--- | :--- | :--- |
| **Language** | Java 21 (LTS) | Leveraging Records, Pattern Matching, and virtual threads readiness. |
| **Framework** | Spring Boot 3.5.9 | Core framework for Dependency Injection and Web MVC. |
| **Database** | PostgreSQL 16 | Relational database run via **Docker Compose**. |
| **ORM** | Spring Data JPA | Hibernate implementation for efficient data access. |
| **Security** | Spring Security 6 | Configuration of Filter Chains, Password Encoding (BCrypt), and CORS. |
| **Testing** | JUnit 5 + Mockito | For fast, isolated Unit Tests. |
| **Integration** | Testcontainers | **Real PostgreSQL** containers for reliable End-to-End testing. |

---

## 🏗 Database Schema

The relational model is managed by **Flyway**. The strict integrity is enforced by Foreign Keys with `ON DELETE CASCADE`.

```sql
-- Relationship: User (1) -> (N) Projects -> (N) Tasks
CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    ...
    project_id BIGINT NOT NULL,
    CONSTRAINT fk_tasks_project FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
);
```

---

## 🧪 Testing Strategy

This project takes quality assurance seriously, implementing the **Testing Pyramid**:

### 1. Unit Tests (`/src/test/java/**/service`)
*   **Focus:** Business logic isolation.
*   **Tools:** JUnit 5, Mockito.
*   **Coverage:** Services and Controllers are tested ensuring that business rules (like "User cannot create task in another user's project") are enforced.

### 2. Integration Tests (`FullFlowIntegrationTest.java`)
*   **Focus:** End-to-End user flows.
*   **Tools:** **Testcontainers** (Docker).
*   **Scenario:** The test spins up a real PostgreSQL container, registers a user, logs in to retrieve a JWT, creates a project, and verifies persistence.
*   **Why:** Ensures the application works in a production-like environment.

---

## ⚡ Getting Started

### Prerequisites
*   Java 21 SDK
*   Docker & Docker Compose

### Fast Run (Localhost)
The application utilizes `spring-boot-docker-compose`. When you start the app, it automatically provisions the database.

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/jhonataswillian/taskmanager.git
    cd taskmanager
    ```

2.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

3.  **Access Swagger UI:**
    Open `http://localhost:8080/swagger-ui.html` to explore and test endpoints.

### Running Tests
To execute the full suite (including Testcontainers):
```bash
./mvnw test
```

---

## 🔌 Main Endpoints

### Auth
*   `POST /users` - Register new user.
*   `POST /auth/login` - Authenticate and receive Bearer Token.

### Projects
*   `GET /projects` - List my projects.
*   `POST /projects` - Create new project.
*   `PATCH /projects/{id}` - Update project details.

### Tasks
*   `POST /projects/{projectId}/tasks` - Create task within a project.
*   `GET /projects/{projectId}/tasks` - List tasks.
*   `PATCH /projects/{projectId}/tasks/{taskId}` - Move task status (TODO -> DONE).

---

## 👨‍💻 Developed by Jhonatas Willian

[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/jhonataswillian)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jhonataswillian/)
