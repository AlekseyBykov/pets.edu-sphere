package dev.abykov.pets.edusphere.courses.controller;

import dev.abykov.pets.edusphere.courses.constants.CoursesConstants;
import dev.abykov.pets.edusphere.courses.dto.CourseDto;
import dev.abykov.pets.edusphere.courses.dto.CourseWithTeacherDTO;
import dev.abykov.pets.edusphere.courses.dto.ResponseDto;
import dev.abykov.pets.edusphere.courses.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Tag(
        name = "Courses CRUD REST APIs",
        description = "APIs to CREATE, UPDATE, FETCH, and DELETE courses"
)
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Get all courses")
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @Operation(summary = "Get course by ID")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Course found"),
                    @ApiResponse(
                            responseCode = "404", description = "Course not found",
                            content = @Content(schema = @Schema(implementation = ResponseDto.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @GetMapping("/{id}/with-teacher")
    public ResponseEntity<CourseWithTeacherDTO> getCourseWithTeacher(@PathVariable UUID id) {
        return ResponseEntity.ok(courseService.getCourseWithTeacher(id));
    }

    @Operation(summary = "Create course")
    @PostMapping
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CourseDto dto) {
        courseService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CoursesConstants.STATUS_201, CoursesConstants.MESSAGE_201));
    }

    @Operation(summary = "Update course")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable UUID id, @Valid @RequestBody CourseDto dto) {
        courseService.update(id, dto);
        return ResponseEntity.ok(new ResponseDto(CoursesConstants.STATUS_200, CoursesConstants.MESSAGE_200));
    }

    @Operation(summary = "Delete course")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable UUID id) {
        courseService.delete(id);
        return ResponseEntity.ok(new ResponseDto(CoursesConstants.STATUS_200, CoursesConstants.MESSAGE_200));
    }
}
