# EduSphere

**EduSphere** is a microservice-based system built with Spring Boot.  
This project is under active development.

## Technologies

- Java 21
- Spring Boot 3
- Spring Data JPA
- H2 Database (for dev/testing)
- Maven + Jib (Docker image builder)
- Swagger / OpenAPI for documentation

## Profiles Service

Currently, the repository includes the `profiles-service` module providing CRUD APIs for managing user profiles.

Available endpoints:
- `POST /api/profiles/create`
- `PUT /api/profiles/update`
- `GET /api/profiles/fetch`
- `DELETE /api/profiles/delete`

Access Swagger UI at:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Docker

To build the Docker image:

```bash
mvn clean compile jib:dockerBuild
```

## Status
This project is work in progress. Structure and modules may change during development.

## License
MIT License