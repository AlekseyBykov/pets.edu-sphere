package dev.abykov.pets.edusphere.courses.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LessonDto {
    private UUID id;
    private String title;
    private UUID moduleId;
}
