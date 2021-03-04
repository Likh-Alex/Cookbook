package com.dev.cookbook.service;

import com.dev.cookbook.entity.Recipe;
import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Recipe save(Recipe recipe);

    Optional<Recipe> getById(Long id);

    List<Recipe> getAll();
}
