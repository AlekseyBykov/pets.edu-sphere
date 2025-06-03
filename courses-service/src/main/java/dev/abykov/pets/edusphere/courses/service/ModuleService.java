package dev.abykov.pets.edusphere.courses.service;

import dev.abykov.pets.edusphere.courses.dto.ModuleDto;

import java.util.List;

public interface ModuleService {

    List<ModuleDto> getAll();

    ModuleDto getById(Long id);

    ModuleDto create(ModuleDto dto);

    ModuleDto update(Long id, ModuleDto dto);

    void delete(Long id);

    List<ModuleDto> getByCourseId(Long courseId);
}
