# EduSphere

**EduSphere** is a microservice-based system built with Spring Boot.  
This project is under active development.

## Technologies

- Java 21
- Spring Boot 3
- Spring Data JPA
- H2 Database (for dev/testing)
- Maven + Jib (Docker image builder)
- Docker Compose
- Swagger / OpenAPI for documentation

## Services

### Profiles Service

Provides CRUD APIs for managing user profiles.

Available endpoints:

- `POST /api/profiles/create`
- `PUT /api/profiles/update`
- `GET /api/profiles/fetch`
- `DELETE /api/profiles/delete`

Swagger UI:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Courses Service

Provides APIs for managing courses, modules, and lessons.

Available endpoints:

Courses:

- `POST /api/courses/create`
- `PUT /api/courses/update`
- `GET /api/courses/fetch`
- `DELETE /api/courses/delete`

Modules:

- `POST /api/modules/create`
- `PUT /api/modules/update`
- `GET /api/modules/fetch`
- `DELETE /api/modules/delete`

Lessons:

- `POST /api/lessons/create`
- `PUT /api/lessons/update`
- `GET /api/lessons/fetch`
- `DELETE /api/lessons/delete`

Swagger UI:  
[http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

## Running locally

To build Docker images and run all services:

```bash
./build-and-run.sh
```

This script will:

Build Docker images using Jib for each service.

Start all services with Docker Compose.

Ensure Docker is running before executing the script.

## Docker

To build the Docker image:

```bash
mvn clean compile jib:dockerBuild
```

## Status
This project is under active development. Structure and modules may change during development.

## License
MIT License