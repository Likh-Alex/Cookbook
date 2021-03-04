package com.dev.cookbook.service;

import com.dev.cookbook.entity.Ingredient;
import java.util.Set;

public interface IngredientService {
    void saveAll(Set<Ingredient> ingredients);
}
