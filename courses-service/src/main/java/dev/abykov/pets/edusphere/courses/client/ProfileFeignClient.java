package dev.abykov.pets.edusphere.courses.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "profiles-service")
public interface ProfileFeignClient {

    @GetMapping("/api/profiles/{id}")
    ProfileDto fetchProfileById(@PathVariable UUID id);
}
