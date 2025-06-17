package dev.abykov.pets.edusphere.content.repository;

import dev.abykov.pets.edusphere.content.model.ContentItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentItemRepository extends JpaRepository<ContentItem, UUID> {
}
