package dev.abykov.pets.edusphere.courses.controller;

import dev.abykov.pets.edusphere.courses.constants.CoursesConstants;
import dev.abykov.pets.edusphere.courses.dto.ModuleDto;
import dev.abykov.pets.edusphere.courses.dto.ResponseDto;
import dev.abykov.pets.edusphere.courses.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
@Tag(name = "Modules CRUD REST APIs", description = "APIs to CREATE, UPDATE, FETCH, and DELETE modules")
public class ModuleController {

    private final ModuleService moduleService;

    @Operation(summary = "Get all modules")
    @GetMapping
    public ResponseEntity<List<ModuleDto>> getAll() {
        return ResponseEntity.ok(moduleService.getAll());
    }

    @Operation(summary = "Get module by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ModuleDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(moduleService.getById(id));
    }

    @Operation(summary = "Create module")
    @PostMapping
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody ModuleDto dto) {
        moduleService.create(dto);
        return ResponseEntity.ok(new ResponseDto(CoursesConstants.STATUS_201, "Module created successfully"));
    }

    @Operation(summary = "Update module")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable UUID id, @Valid @RequestBody ModuleDto dto) {
        moduleService.update(id, dto);
        return ResponseEntity.ok(new ResponseDto(CoursesConstants.STATUS_200, "Module updated successfully"));
    }

    @Operation(summary = "Delete module")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable UUID id) {
        moduleService.delete(id);
        return ResponseEntity.ok(new ResponseDto(CoursesConstants.STATUS_200, "Module deleted successfully"));
    }

    @Operation(summary = "Get all modules by course ID")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ModuleDto>> getByCourseId(@PathVariable UUID courseId) {
        return ResponseEntity.ok(moduleService.getByCourseId(courseId));
    }
}
