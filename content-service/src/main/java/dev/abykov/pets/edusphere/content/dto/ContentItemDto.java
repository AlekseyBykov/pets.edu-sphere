package dev.abykov.pets.edusphere.content.dto;

import java.util.UUID;

public record ContentItemDto(
        UUID id,
        String filename,
        String contentType,
        long size
) {}
