# Lexora — Enterprise Legal Contract Intelligence Platform

> **Enterprise Legal Contract Intelligence and Evidence Retrieval Platform**
>
> Lexora is a production-grade backend system designed to ingest, analyze, and retrieve legal contracts and evidence using AI-powered search, event streaming, and secure REST APIs.

---

## Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Core language |
| Spring Boot | 3.3.x | Application framework |
| Maven | 3.9+ | Build and dependency management |
| PostgreSQL | 15+ | Primary relational database |
| Spring Data JPA / Hibernate | 6.x | ORM and persistence layer |
| Spring Boot Actuator | 3.3.x | Health, metrics, monitoring |
| Jakarta Validation | 3.x | Bean validation |
| Logback | 1.4.x | Structured logging |

---

## Project Structure

```
D:\Lexora
├── pom.xml
├── README.md
├── .gitignore
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── lexora
    │   │           ├── LexoraApplication.java       # Main entry point
    │   │           ├── config/                      # Spring configuration classes
    │   │           │   └── DatabaseConfig.java
    │   │           ├── controller/                  # REST controllers
    │   │           │   └── HealthController.java
    │   │           ├── service/                     # Business logic layer
    │   │           ├── repository/                  # Spring Data JPA repositories
    │   │           ├── entity/                      # JPA entities
    │   │           ├── dto/                         # Data Transfer Objects
    │   │           │   ├── ApiResponse.java
    │   │           │   └── SampleRequestDto.java
    │   │           ├── exception/                   # Exception handling
    │   │           │   ├── GlobalExceptionHandler.java
    │   │           │   ├── LexoraException.java
    │   │           │   └── ResourceNotFoundException.java
    │   │           ├── security/                    # Security (Phase 2)
    │   │           └── util/                        # Utility classes
    │   └── resources
    │       ├── application.yml                      # Main config (dev profile active)
    │       ├── application-dev.yml                  # Dev profile (PostgreSQL via env vars)
    │       └── logback-spring.xml                   # Profile-aware logging config
    └── test
        ├── java
        │   └── com
        │       └── lexora
        │           ├── controller/
        │           │   └── HealthControllerTest.java
        │           ├── dto/
        │           │   └── SampleRequestDtoValidationTest.java
        │           └── exception/
        │               └── GlobalExceptionHandlerTest.java
        └── resources
            └── application.yml                      # H2 in-memory DB for tests
```

---

## Prerequisites

- **Java 17+** — [Download Temurin](https://adoptium.net/)
- **Maven 3.9+** — [Download Maven](https://maven.apache.org/download.cgi)
- **PostgreSQL 15+** — [Download PostgreSQL](https://www.postgresql.org/download/)
- **Git** — for version control

---

## PostgreSQL Setup

Run these commands in your PostgreSQL shell (`psql`):

```sql
CREATE DATABASE lexora_db;
CREATE USER lexora_user WITH PASSWORD 'yourpassword';
GRANT ALL PRIVILEGES ON DATABASE lexora_db TO lexora_user;
```

---

## Environment Variables

Configure these before running the application:

| Variable | Default | Required | Description |
|---|---|---|---|
| `DB_HOST` | `localhost` | No | PostgreSQL host |
| `DB_PORT` | `5432` | No | PostgreSQL port |
| `DB_NAME` | `lexora_db` | No | Database name |
| `DB_USERNAME` | `postgres` | No | Database username |
| `DB_PASSWORD` | _(none)_ | **Yes** | Database password — never hardcode! |

> **Security Note:** Never commit passwords to version control. Use environment variables or a secrets manager.

---

## How to Run

### Windows (PowerShell)

```powershell
$env:DB_PASSWORD = "yourpassword"
mvn spring-boot:run
```

### Linux / macOS

```bash
export DB_PASSWORD=yourpassword
mvn spring-boot:run
```

The API will be available at: `http://localhost:8080/api`

---

## Running Tests

```bash
mvn clean test
```

Tests use H2 in-memory database — no PostgreSQL required for testing.

---

## API Endpoints

### Custom Health Endpoint

```
GET /api/v1/health
```

**Response:**
```json
{
  "success": true,
  "message": "Lexora is running",
  "data": {
    "status": "UP",
    "service": "Lexora API",
    "version": "1.0.0-SNAPSHOT"
  },
  "timestamp": "2026-10-02T06:00:00Z"
}
```

### Spring Boot Actuator Health

```
GET /api/actuator/health
GET /api/actuator/info
GET /api/actuator/metrics
```

---

## API Response Format

All endpoints return a consistent `ApiResponse<T>` wrapper:

```json
{
  "success": true | false,
  "message": "Optional message",
  "data": { ... },
  "timestamp": "ISO-8601 instant"
}
```

---

## Current Phase

### ✅ Phase 1 — Backend Foundation (Complete)

- [x] Spring Boot 3.3.x project structure
- [x] Maven dependency configuration (JPA, Web, Actuator, Validation, PostgreSQL)
- [x] Application configuration (dev profile, Hikari pool)
- [x] PostgreSQL database configuration via environment variables
- [x] Global API response model (`ApiResponse<T>`)
- [x] Global exception handling (`GlobalExceptionHandler`)
- [x] Jakarta Bean Validation foundation
- [x] Health endpoint (`/api/v1/health`)
- [x] Structured logging (Logback, profile-aware)
- [x] Full test suite (7 unit tests, 0 failures)

---

## Roadmap

| Phase | Feature | Status |
|---|---|---|
| Phase 1 | Backend Foundation | ✅ Complete |
| Phase 2 | Authentication & JWT | 🔜 Planned |
| Phase 3 | Document Upload & Storage (S3) | 🔜 Planned |
| Phase 4 | Kafka Event Streaming | 🔜 Planned |
| Phase 5 | AI/Vector Search (pgvector) | 🔜 Planned |
| Phase 6 | React Frontend | 🔜 Planned |

---

## Contributing

1. Create a feature branch: `git checkout -b feat/your-feature`
2. Follow the existing package structure
3. Write unit tests for all new classes
4. Run `mvn clean test` before committing
5. Submit a pull request with a clear description

---

## License

Copyright © 2026 Yogesh Bhatt. All rights reserved.
