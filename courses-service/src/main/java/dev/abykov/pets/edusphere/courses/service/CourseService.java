package dev.abykov.pets.edusphere.courses.service;

import dev.abykov.pets.edusphere.courses.dto.CourseDto;
import dev.abykov.pets.edusphere.courses.dto.CourseWithTeacherDTO;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    List<CourseDto> getAll();

    CourseDto getById(UUID id);

    CourseWithTeacherDTO getCourseWithTeacher(UUID courseId);

    CourseDto create(CourseDto dto);

    CourseDto update(UUID id, CourseDto dto);

    void delete(UUID id);
}
