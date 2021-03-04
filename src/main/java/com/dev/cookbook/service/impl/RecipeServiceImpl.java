package com.dev.cookbook.service.impl;

import com.dev.cookbook.entity.Recipe;
import com.dev.cookbook.repository.IngredientRepository;
import com.dev.cookbook.repository.RecipeRepository;
import com.dev.cookbook.service.RecipeService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public Recipe save(Recipe recipe) {
        ingredientRepository.saveAll(recipe.getIngredients());
        return recipeRepository.save(recipe);
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.ofNullable(recipeRepository.getById(id));
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findByOrderByPreamble();
    }
}
