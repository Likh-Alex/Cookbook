package com.dev.cookbook.dto.response;

import com.dev.cookbook.entity.Ingredient;
import com.dev.cookbook.entity.Recipe;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class RecipeResponseDto {
    private Long id;
    private Recipe parent;
    private String preamble;
    private String description;
    private LocalDate dateCreated;
    private boolean isFirstVersion;
    private Set<Ingredient> ingredients;
    private List<Recipe> previousVersions;
}
