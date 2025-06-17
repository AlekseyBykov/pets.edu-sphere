package dev.abykov.pets.edusphere.content.mapper;

import dev.abykov.pets.edusphere.content.dto.ContentItemDto;
import dev.abykov.pets.edusphere.content.model.ContentItem;
import org.springframework.stereotype.Component;

@Component
public class ContentMapper {

    public ContentItemDto toDto(ContentItem item) {
        return new ContentItemDto(
                item.getId(),
                item.getFilename(),
                item.getContentType(),
                item.getSize()
        );
    }
}
