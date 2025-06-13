# EduSphere

**EduSphere** is a full-featured microservice-based educational platform built with Spring Boot. It demonstrates real-world architecture with:

- Inter-service communication (REST)
- Resilience patterns (like circuit breakers and retries)
- API gateway routing
- Docker-based containerization
- Comprehensive unit, integration, and repository testing

## Status
This project **is under active development**. Structure and modules may change during development.

## Technologies

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Cloud Gateway (planned)
- H2 Database (for local testing)
- PostgreSQL (planned for production)
- Maven
- Docker & Docker Compose
- Swagger / OpenAPI 3
 
## Services

### Profiles Service

Manages user profile data.

**Endpoints:**

- `POST /api/profiles/create`
- `PUT /api/profiles/update`
- `GET /api/profiles/fetch`
- `DELETE /api/profiles/delete`

🔗 Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

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

Swagger UI:  
[http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

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
./stop-and-clean.sh
```
Use this to fully clean and reset the Docker environment, including local images and data volumes.

## Test Coverage

- JUnit-based controller tests
- Repository tests using H2 and `@DataJpaTest`
- MockMvc tests for REST APIs
- Entity auditing tests

Further test scenarios planned (e.g., end-to-end, resilience)

## License
MIT License
