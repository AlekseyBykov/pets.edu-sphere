package dev.abykov.pets.edusphere.courses.service;

import dev.abykov.pets.edusphere.courses.dto.ModuleDto;

import java.util.List;
import java.util.UUID;

public interface ModuleService {

    List<ModuleDto> getAll();

    ModuleDto getById(UUID id);

    ModuleDto create(ModuleDto dto);

    ModuleDto update(UUID id, ModuleDto dto);

    void delete(UUID id);

    List<ModuleDto> getByCourseId(UUID courseId);
}
