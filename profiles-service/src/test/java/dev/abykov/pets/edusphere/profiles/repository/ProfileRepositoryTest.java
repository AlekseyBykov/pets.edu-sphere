package dev.abykov.pets.edusphere.profiles.repository;

import dev.abykov.pets.edusphere.profiles.entity.Profile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test-jpa")
@Import(TestAuditConfiguration.class)
@DataJpaTest
class ProfileRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProfileRepository profileRepository;

    @Test
    @DisplayName("when profile saved with phone then repository should find it by phone")
    public void whenProfileSaved_thenFindsByPhone() {
        Profile profile = new Profile();
        profile.setUsername("Test User");
        profile.setPhone("1234567890");
        profile.setEmail("test@example.com");

        entityManager.persistAndFlush(profile);

        Optional<Profile> maybeProfile = profileRepository.findByPhone("1234567890");

        maybeProfile.ifPresentOrElse(
                found -> {
                    assertEquals("Test User", found.getUsername(), "Name mismatch");
                    assertEquals("1234567890", found.getPhone(), "Phone mismatch");
                    assertEquals("test@example.com", found.getEmail(), "Email mismatch");
                },
                () -> fail("Expected profile to be found, but was not")
        );
    }
}
