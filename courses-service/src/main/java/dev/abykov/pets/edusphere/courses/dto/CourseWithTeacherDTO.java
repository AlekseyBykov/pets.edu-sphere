package dev.abykov.pets.edusphere.courses.dto;

import dev.abykov.pets.edusphere.courses.client.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CourseWithTeacherDTO {

    private UUID id;
    private String title;
    private ProfileDto teacher;
}
