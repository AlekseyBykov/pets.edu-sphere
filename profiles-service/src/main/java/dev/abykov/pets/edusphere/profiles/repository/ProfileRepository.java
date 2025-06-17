package dev.abykov.pets.edusphere.profiles.repository;

import dev.abykov.pets.edusphere.profiles.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByPhone(String phone);

    Optional<Profile> findByEmail(String email);
}
