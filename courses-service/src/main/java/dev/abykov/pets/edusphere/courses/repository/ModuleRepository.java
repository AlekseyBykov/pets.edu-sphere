package dev.abykov.pets.edusphere.courses.repository;

import dev.abykov.pets.edusphere.courses.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ModuleRepository extends JpaRepository<Module, UUID> {

    List<Module> findAllByCourseId(UUID courseId);
}
