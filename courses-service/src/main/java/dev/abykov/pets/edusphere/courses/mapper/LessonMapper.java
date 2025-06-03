package dev.abykov.pets.edusphere.courses.mapper;

import dev.abykov.pets.edusphere.courses.dto.LessonDto;
import dev.abykov.pets.edusphere.courses.entity.Lesson;
import dev.abykov.pets.edusphere.courses.entity.Module;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public LessonDto toDto(Lesson entity) {
        if (entity == null) {
            return null;
        }

        LessonDto dto = new LessonDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setModuleId(entity.getModule().getId());
        return dto;
    }

    public Lesson toEntity(LessonDto dto, Module module) {
        Lesson lesson = new Lesson();
        lesson.setId(dto.getId());
        lesson.setTitle(dto.getTitle());
        lesson.setModule(module);
        return lesson;
    }
}
