package dev.abykov.pets.edusphere.courses.repository;

import dev.abykov.pets.edusphere.courses.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByModuleId(Long moduleId);
}
