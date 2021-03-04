package com.dev.cookbook.dto.response;

import com.dev.cookbook.entity.Ingredient;
import com.dev.cookbook.entity.Recipe;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class RecipeResponseDto {
    private Long id;
    private String preamble;
    private String description;
    private Set<Ingredient> ingredients;
    private boolean isFirstVersion;
    private Recipe parent;
    private List<Recipe> previousVersions;
}
