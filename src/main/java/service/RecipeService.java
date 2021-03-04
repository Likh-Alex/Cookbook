package service;

import entity.Recipe;
import java.util.List;

public interface RecipeService {
    Recipe save(Recipe recipe);

    void delete(Long id);

    List<Recipe> getAllRecipes();
}
