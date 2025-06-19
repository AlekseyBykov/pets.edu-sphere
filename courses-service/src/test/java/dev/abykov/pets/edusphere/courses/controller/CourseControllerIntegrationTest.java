package dev.abykov.pets.edusphere.courses.controller;

import dev.abykov.pets.edusphere.courses.dto.CourseDto;
import dev.abykov.pets.edusphere.courses.dto.ResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("test_courses")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api/courses";
    }

    @BeforeAll
    static void checkTestcontainersConfig() {
        System.out.println("JAVA_TOOL_OPTIONS = " + System.getenv("JAVA_TOOL_OPTIONS"));

        TestcontainersConfiguration config = TestcontainersConfiguration.getInstance();

        System.out.println("reuse.enable = " + config.getEnvVarOrProperty("testcontainers.reuse.enable", "false"));
        System.out.println("ryuk.disabled = " + config.getEnvVarOrProperty("testcontainers.ryuk.disabled", "false"));
    }

    private CourseDto buildTestCourse() {
        CourseDto course = new CourseDto();
        course.setTitle("Test Course");
        course.setDescription("Integration Test Description");
        return course;
    }

    @Test
    @Order(1)
    void testCreateCourse() {
        CourseDto course = buildTestCourse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CourseDto> request = new HttpEntity<>(course, headers);
        ResponseEntity<ResponseDto> response = restTemplate.postForEntity(baseUrl, request, ResponseDto.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("CREATED", response.getBody().getStatus());
    }

    @Test
    @Order(2)
    void testGetCourseById() {
        ResponseEntity<List<CourseDto>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<CourseDto> courses = response.getBody();
        assertNotNull(courses);
        assertFalse(courses.isEmpty());

        CourseDto course = courses.get(0);
        ResponseEntity<CourseDto> getResponse = restTemplate.getForEntity(baseUrl + "/" + course.getId(), CourseDto.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertNotNull(getResponse.getBody());
        assertEquals("Test Course", getResponse.getBody().getTitle());
    }

    @Test
    @Order(3)
    void testGetNonExistentCourse() {
        UUID fakeId = UUID.randomUUID();
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/" + fakeId, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
