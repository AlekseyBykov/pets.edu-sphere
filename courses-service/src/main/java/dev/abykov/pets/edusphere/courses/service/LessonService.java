package dev.abykov.pets.edusphere.courses.service;

import dev.abykov.pets.edusphere.courses.dto.LessonDto;

import java.util.List;

public interface LessonService {

    List<LessonDto> getAll();

    LessonDto getById(Long id);

    LessonDto create(LessonDto dto);

    LessonDto update(Long id, LessonDto dto);

    void delete(Long id);

    List<LessonDto> getByModuleId(Long moduleId);
}
