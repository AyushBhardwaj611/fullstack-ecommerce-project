package dev.ayush.productservice.services;

import dev.ayush.productservice.Dtos.GetProductTitlesRequestDto;
import dev.ayush.productservice.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

    Category getCategory(String uuid);

    List<String> getProductTitles(List<String> uuid);
}
