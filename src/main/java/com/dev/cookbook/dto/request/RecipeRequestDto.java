package com.dev.cookbook.dto.request;

import com.dev.cookbook.entity.Ingredient;
import java.util.Set;
import lombok.Data;

@Data
public class RecipeRequestDto {
    private String preamble;
    private String description;
    private Set<Ingredient> ingredients;
}
