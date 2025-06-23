package dev.abykov.pets.edusphere.courses.controller;

import dev.abykov.pets.edusphere.courses.dto.CourseDto;
import dev.abykov.pets.edusphere.courses.dto.ResponseDto;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseControllerIntegrationTest extends BaseIntegrationTest {

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
        ResponseEntity<CourseDto> getResponse = restTemplate.getForEntity(
                baseUrl + "/" + course.getId(),
                CourseDto.class
        );

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertNotNull(getResponse.getBody());
        assertEquals("Test course", getResponse.getBody().getTitle());
    }

    @Test
    @Order(3)
    void testGetNonExistentCourse() {
        UUID fakeId = UUID.randomUUID();
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/" + fakeId, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    private CourseDto buildTestCourse() {
        CourseDto course = new CourseDto();
        course.setTitle("Test Course");
        course.setDescription("Integration Test Description");
        return course;
    }
}
