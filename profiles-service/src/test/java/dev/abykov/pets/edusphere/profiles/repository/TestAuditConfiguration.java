package dev.abykov.pets.edusphere.profiles.repository;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@TestConfiguration
public class TestAuditConfiguration {

    @Bean
    public AuditorAware<String> auditAwareImpl() {
        return () -> Optional.of("test-user");
    }
}
