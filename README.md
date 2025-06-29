# EduSphere

**EduSphere** is a full-featured microservice-based educational platform built with Spring Boot. It demonstrates real-world architecture with:

- Inter-service communication (REST)
- Resilience patterns (like circuit breakers and retries)
- API gateway routing
- Docker-based containerization
- Comprehensive unit, integration, and repository testing
- Testcontainers-based integration testing with real PostgreSQL

## Status
This project **is under active development**. Structure and modules may change during development.

## Technologies

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Web
- Spring Cloud Gateway (planned)
- PostgreSQL (per-service database)
- Flyway (database migrations)
- H2 Database (for local unit testing only)
- Testcontainers (for PostgreSQL-based integration tests)
- AWS SDK v2 (for S3-compatible storage)
- MinIO (local S3-compatible object storage)
- Swagger / OpenAPI 3
- Maven
- Docker & Docker Compose

## Services

### Gateway Server

Acts as an API Gateway and edge server, routing external requests to internal microservices and handling cross-cutting 
concerns like authentication, logging, and rate limiting.

Built with Spring Cloud Gateway.

Routes configured:

- `/edu/courses/** → forwards to courses-service`
- `/edu/profiles/** → forwards to profiles-service`

This enables clean separation of client APIs from internal service URLs and simplifies client integration.

### Config Server

The Config Server provides centralized configuration management for all microservices in the edu-sphere system.
It uses Spring Cloud Config to serve configuration properties from a Git repository.

Purpose:

- Centralizes and manages configuration for all environments (dev, test, prod).
- Enables configuration updates without rebuilding Docker images.
- Supports versioned configuration via Git.

Default setup:

- Runs on port `8888`
- Registered with Eureka Discovery for service discovery

### Discovery Server

Eureka-based service registry that allows microservices to discover each other dynamically without hardcoding 
hostnames or ports.

Exposes dashboard at:

- [http://localhost:8761](http://localhost:8761)

All other services (like `courses-service`, `profiles-service`) register themselves with Eureka at runtime.

### Profiles Service

Manages user profile data.

**Endpoints:**

- `POST /api/profiles/create`
- `PUT /api/profiles/update`
- `GET /api/profiles/fetch`
- `DELETE /api/profiles/delete`

Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Courses Service

Handles courses, modules, and lessons.

**Endpoints:**

#### Courses
- `POST /api/courses/create`
- `PUT /api/courses/update`
- `GET /api/courses/fetch`
- `DELETE /api/courses/delete`

#### Modules
- `POST /api/modules/create`
- `PUT /api/modules/update`
- `GET /api/modules/fetch`
- `DELETE /api/modules/delete`

#### Lessons
- `POST /api/lessons/create`
- `PUT /api/lessons/update`
- `GET /api/lessons/fetch`
- `DELETE /api/lessons/delete`

Swagger UI: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

### Content Service

Handles upload and download of educational content (images, PDFs, videos, etc). Files are stored in an S3-compatible object store (MinIO).
Metadata is stored in PostgreSQL via the ContentItem entity.

**Endpoints:**

- `POST /contents/upload` — Upload a file
- `GET /contents/download/{key}` — Download a file by key

Swagger UI: [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)

## Scripts
### `build-and-run.sh`

Builds Docker images for all microservices and starts the system using Docker Compose.

```bash
./build-and-run.sh
```
Make sure Docker is running before executing this script.

### `stop-and-clean.sh`

Stops all running containers related to the project and removes their volumes. This is useful to reset the environment 
during development or troubleshooting.

```bash
./stop-and-clean.sh
```

### `stop-and-purge.sh`

Stops all running services, removes containers, images, and volumes used by the system.

```bash
./stop-and-purge.sh
```
Use this to fully clean and reset the Docker environment, including local images and data volumes.

## Test Coverage

- JUnit-based controller tests
- Repository tests using H2 and `@DataJpaTest`
- Repository tests with Flyway-based database setup (`application-test-jpa.yml`)
- Integration tests using Testcontainers (PostgreSQL), Flyway migrations, and @SpringBootTest
- MockMvc tests for REST APIs
- Entity auditing tests

Testcontainers integration allows realistic PostgreSQL-backed testing scenarios with full isolation and no external dependencies.
 
## License
MIT License
