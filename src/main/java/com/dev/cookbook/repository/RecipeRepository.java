package com.dev.cookbook.repository;

import com.dev.cookbook.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe getById(Long id);

    List<Recipe> findByOrderByPreamble();
}
