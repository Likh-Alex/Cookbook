package com.dev.cookbook.service.impl;

import com.dev.cookbook.entity.Ingredient;
import com.dev.cookbook.repository.IngredientRepository;
import com.dev.cookbook.service.IngredientService;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Override
    public void saveAll(Set<Ingredient> ingredients) {
        ingredientRepository.saveAll(ingredients);
    }
}
