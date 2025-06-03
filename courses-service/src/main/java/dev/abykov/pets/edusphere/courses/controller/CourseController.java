package dev.abykov.pets.edusphere.courses.controller;

import dev.abykov.pets.edusphere.courses.constants.CoursesConstants;
import dev.abykov.pets.edusphere.courses.dto.CourseDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<CourseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
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
    public ResponseEntity<ResponseDto> update(@PathVariable Long id, @Valid @RequestBody CourseDto dto) {
        courseService.update(id, dto);
        return ResponseEntity.ok(new ResponseDto(CoursesConstants.STATUS_200, CoursesConstants.MESSAGE_200));
    }

    @Operation(summary = "Delete course")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.ok(new ResponseDto(CoursesConstants.STATUS_200, CoursesConstants.MESSAGE_200));
    }
}
