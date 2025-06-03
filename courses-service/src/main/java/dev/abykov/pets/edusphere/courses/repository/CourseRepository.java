package dev.abykov.pets.edusphere.courses.repository;

import dev.abykov.pets.edusphere.courses.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
