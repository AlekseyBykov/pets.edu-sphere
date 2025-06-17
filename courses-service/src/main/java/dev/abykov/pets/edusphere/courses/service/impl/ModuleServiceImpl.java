package dev.abykov.pets.edusphere.courses.service.impl;

import dev.abykov.pets.edusphere.courses.constants.ErrorMessages;
import dev.abykov.pets.edusphere.courses.dto.ModuleDto;
import dev.abykov.pets.edusphere.courses.entity.Course;
import dev.abykov.pets.edusphere.courses.entity.Module;
import dev.abykov.pets.edusphere.courses.exception.CourseNotFoundException;
import dev.abykov.pets.edusphere.courses.exception.ModuleNotFoundException;
import dev.abykov.pets.edusphere.courses.mapper.ModuleMapper;
import dev.abykov.pets.edusphere.courses.repository.CourseRepository;
import dev.abykov.pets.edusphere.courses.repository.ModuleRepository;
import dev.abykov.pets.edusphere.courses.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final ModuleMapper moduleMapper;

    @Override
    public List<ModuleDto> getAll() {
        return moduleRepository.findAll().stream()
                .map(moduleMapper::toDto)
                .toList();
    }

    @Override
    public ModuleDto getById(UUID id) {
        Module module = moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(
                        String.format(ErrorMessages.MODULE_NOT_FOUND, id)
                ));
        return moduleMapper.toDto(module);
    }

    @Override
    public ModuleDto create(ModuleDto dto) {
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format(ErrorMessages.COURSE_NOT_FOUND, dto.getCourseId())
                ));

        Module module = moduleMapper.toEntity(dto, course);
        return moduleMapper.toDto(moduleRepository.save(module));
    }

    @Override
    public ModuleDto update(UUID id, ModuleDto dto) {
        Module existing = moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(
                        String.format(ErrorMessages.MODULE_NOT_FOUND, id)
                ));
        existing.setTitle(dto.getTitle());
        return moduleMapper.toDto(moduleRepository.save(existing));
    }

    @Override
    public void delete(UUID id) {
        moduleRepository.deleteById(id);
    }

    @Override
    public List<ModuleDto> getByCourseId(UUID courseId) {
        return moduleRepository.findAllByCourseId(courseId).stream()
                .map(moduleMapper::toDto)
                .toList();
    }
}
