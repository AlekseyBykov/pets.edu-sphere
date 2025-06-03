package dev.abykov.pets.edusphere.profiles.repository;

import dev.abykov.pets.edusphere.profiles.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByPhone(String phone);
}
