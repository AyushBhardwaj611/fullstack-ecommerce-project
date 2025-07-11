package dev.ayush.productservice.repositories;

import dev.ayush.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository("categoryrepository")
public interface CategoryRepository
extends JpaRepository<Category, UUID> {

    Optional<Category> findById(UUID uuid);
}
