package dev.abykov.pets.edusphere.profiles.integration;

import dev.abykov.pets.edusphere.profiles.constants.ProfilesConstants;
import dev.abykov.pets.edusphere.profiles.dto.ProfileDto;
import dev.abykov.pets.edusphere.profiles.dto.ResponseDto;
import dev.abykov.pets.edusphere.profiles.repository.ProfileRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProfileIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ProfileRepository profileRepository;

    @Test
    @Order(1)
    void testCreateProfile() {
        var request = new ProfileDto("Test User", "john@example.com", "1234567890");

        ResponseEntity<ResponseDto> response = restTemplate.postForEntity(
                "/api/profiles/create",
                request,
                ResponseDto.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ProfilesConstants.STATUS_201, response.getBody().getStatusCode());
    }

    @Test
    @Order(2)
    void testFindByPhone() {
        var profile = profileRepository.findByPhone("1234567890");
        assertTrue(profile.isPresent());
        assertEquals("Test User", profile.get().getUsername());
    }
}
