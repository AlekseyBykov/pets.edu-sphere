package dev.abykov.pets.edusphere.courses.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.TestcontainersConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {

    @LocalServerPort
    protected int port;

    protected String baseUrl;

    @Autowired
    protected TestRestTemplate restTemplate;

    @BeforeEach
    void setup() {
        baseUrl = "http://localhost:" + port + "/api/courses";
    }

    @SuppressWarnings("rawtypes")
    protected static final PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("test_courses")
            .withUsername("test")
            .withPassword("test");

    static {
        postgres.start();
    }

    @BeforeAll
    static void checkTestcontainersConfig() {
        System.out.println("JAVA_TOOL_OPTIONS = " + System.getenv("JAVA_TOOL_OPTIONS"));

        TestcontainersConfiguration config = TestcontainersConfiguration.getInstance();

        System.out.println("reuse.enable = " + config.getEnvVarOrProperty("testcontainers.reuse.enable", "false"));
        System.out.println("ryuk.disabled = " + config.getEnvVarOrProperty("testcontainers.ryuk.disabled", "false"));
    }

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
}
