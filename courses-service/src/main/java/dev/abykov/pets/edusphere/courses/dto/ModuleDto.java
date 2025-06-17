package dev.abykov.pets.edusphere.courses.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ModuleDto {
    private UUID id;
    private String title;
    private UUID courseId;
}
