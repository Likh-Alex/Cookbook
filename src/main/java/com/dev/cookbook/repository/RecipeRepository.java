package com.dev.cookbook.repository;

import com.dev.cookbook.entity.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe getById(Long id);

    List<Recipe> findByOrderByPreamble();
}
