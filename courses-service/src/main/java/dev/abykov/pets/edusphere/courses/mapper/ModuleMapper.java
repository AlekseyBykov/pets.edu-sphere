package dev.abykov.pets.edusphere.courses.mapper;

import dev.abykov.pets.edusphere.courses.dto.ModuleDto;
import dev.abykov.pets.edusphere.courses.entity.Course;
import dev.abykov.pets.edusphere.courses.entity.Module;
import org.springframework.stereotype.Component;

@Component
public class ModuleMapper {

    public ModuleDto toDto(Module entity) {
        if (entity == null) {
            return null;
        }

        ModuleDto dto = new ModuleDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setCourseId(entity.getCourse().getId());
        return dto;
    }

    public Module toEntity(ModuleDto dto, Course course) {
        Module module = new Module();
        module.setId(dto.getId());
        module.setTitle(dto.getTitle());
        module.setCourse(course);
        return module;
    }
}
