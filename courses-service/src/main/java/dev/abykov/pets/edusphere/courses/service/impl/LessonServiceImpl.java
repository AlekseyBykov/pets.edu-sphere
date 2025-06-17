package dev.abykov.pets.edusphere.courses.service.impl;

import dev.abykov.pets.edusphere.courses.dto.LessonDto;
import dev.abykov.pets.edusphere.courses.entity.Lesson;
import dev.abykov.pets.edusphere.courses.entity.Module;
import dev.abykov.pets.edusphere.courses.mapper.LessonMapper;
import dev.abykov.pets.edusphere.courses.repository.LessonRepository;
import dev.abykov.pets.edusphere.courses.repository.ModuleRepository;
import dev.abykov.pets.edusphere.courses.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;
    private final LessonMapper lessonMapper;

    @Override
    public List<LessonDto> getAll() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::toDto)
                .toList();
    }

    @Override
    public LessonDto getById(UUID id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        return lessonMapper.toDto(lesson);
    }

    @Override
    public LessonDto create(LessonDto dto) {
        Module module = moduleRepository.findById(dto.getModuleId())
                .orElseThrow(() -> new RuntimeException("Module not found"));

        Lesson lesson = lessonMapper.toEntity(dto, module);
        return lessonMapper.toDto(lessonRepository.save(lesson));
    }

    @Override
    public LessonDto update(UUID id, LessonDto dto) {
        Lesson existing = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        existing.setTitle(dto.getTitle());
        return lessonMapper.toDto(lessonRepository.save(existing));
    }

    @Override
    public void delete(UUID id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public List<LessonDto> getByModuleId(UUID moduleId) {
        return lessonRepository.findAllByModuleId(moduleId).stream()
                .map(lessonMapper::toDto)
                .toList();
    }
}
