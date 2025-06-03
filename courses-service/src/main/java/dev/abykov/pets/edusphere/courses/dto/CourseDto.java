package dev.abykov.pets.edusphere.courses.dto;

import lombok.Data;

@Data
public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private Long teacherId;
}
