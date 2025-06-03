package dev.abykov.pets.edusphere.courses.service;

import dev.abykov.pets.edusphere.courses.dto.CourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> getAll();

    CourseDto getById(Long id);

    CourseDto create(CourseDto dto);

    CourseDto update(Long id, CourseDto dto);

    void delete(Long id);
}
