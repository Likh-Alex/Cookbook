package service;

import entity.Ingredient;

public interface IngredientService {
    Ingredient save(Ingredient ingredient);

    Ingredient getByName(String name);

    void delete(Long id);
}
