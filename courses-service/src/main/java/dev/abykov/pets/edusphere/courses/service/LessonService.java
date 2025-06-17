package dev.abykov.pets.edusphere.courses.service;

import dev.abykov.pets.edusphere.courses.dto.LessonDto;

import java.util.List;
import java.util.UUID;

public interface LessonService {

    List<LessonDto> getAll();

    LessonDto getById(UUID id);

    LessonDto create(LessonDto dto);

    LessonDto update(UUID id, LessonDto dto);

    void delete(UUID id);

    List<LessonDto> getByModuleId(UUID moduleId);
}
