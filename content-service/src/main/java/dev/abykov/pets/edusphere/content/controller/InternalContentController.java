package dev.abykov.pets.edusphere.content.controller;

import dev.abykov.pets.edusphere.content.dto.ContentItemDto;
import dev.abykov.pets.edusphere.content.mapper.ContentMapper;
import dev.abykov.pets.edusphere.content.repository.ContentItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/internal/contents")
public class InternalContentController {

    private final ContentItemRepository repository;
    private final ContentMapper mapper;

    public InternalContentController(ContentItemRepository repository, ContentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ContentItemDto getById(@PathVariable UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }
}
