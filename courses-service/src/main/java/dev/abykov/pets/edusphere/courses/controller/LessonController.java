package dev.abykov.pets.edusphere.courses.controller;

import dev.abykov.pets.edusphere.courses.constants.CoursesConstants;
import dev.abykov.pets.edusphere.courses.dto.LessonDto;
import dev.abykov.pets.edusphere.courses.dto.ResponseDto;
import dev.abykov.pets.edusphere.courses.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@Tag(
        name = "Lessons CRUD REST APIs",
        description = "APIs to CREATE, UPDATE, FETCH, and DELETE lessons"
)
public class LessonController {

    private final LessonService lessonService;

    @Operation(summary = "Get all lessons")
    @GetMapping
    public ResponseEntity<List<LessonDto>> getAll() {
        return ResponseEntity.ok(lessonService.getAll());
    }

    @Operation(summary = "Get lesson by ID")
    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(lessonService.getById(id));
    }

    @Operation(summary = "Create lesson")
    @PostMapping
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody LessonDto dto) {
        lessonService.create(dto);
        return ResponseEntity.ok(new ResponseDto(CoursesConstants.STATUS_201, "Lesson created successfully"));
    }

    @Operation(summary = "Update lesson")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable UUID id, @Valid @RequestBody LessonDto dto) {
        lessonService.update(id, dto);
        return ResponseEntity.ok(new ResponseDto(CoursesConstants.STATUS_200, "Lesson updated successfully"));
    }

    @Operation(summary = "Delete lesson")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable UUID id) {
        lessonService.delete(id);
        return ResponseEntity.ok(new ResponseDto(CoursesConstants.STATUS_200, "Lesson deleted successfully"));
    }

    @Operation(summary = "Get all lessons by module ID")
    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<LessonDto>> getByModuleId(@PathVariable UUID moduleId) {
        return ResponseEntity.ok(lessonService.getByModuleId(moduleId));
    }
}
