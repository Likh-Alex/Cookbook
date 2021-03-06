package com.dev.cookbook.service.mapper;

import com.dev.cookbook.dto.request.RecipeRequestDto;
import com.dev.cookbook.dto.response.RecipeResponseDto;
import com.dev.cookbook.entity.Recipe;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {
    public RecipeResponseDto toDto(Recipe recipe) {
        RecipeResponseDto dto = new RecipeResponseDto();
        dto.setId(recipe.getId());
        dto.setParent(recipe.getParent());
        dto.setPreamble(recipe.getPreamble());
        dto.setDescription(recipe.getDescription());
        dto.setDateCreated(recipe.getDateCreated());
        dto.setDateCreated(recipe.getDateCreated());
        boolean isFirstVersion = recipe.getPreviousVersions().size() == 0;
        dto.setFirstVersion(isFirstVersion);
        dto.setIngredients(recipe.getIngredients());
        dto.setPreviousVersions(recipe.getPreviousVersions());
        return dto;
    }

    public Recipe toEntity(RecipeRequestDto dto) {
        Recipe recipe = new Recipe();
        recipe.setPreamble(dto.getPreamble());
        recipe.setDateCreated(LocalDate.now());
        recipe.setDescription(dto.getDescription());
        recipe.setPreviousVersions(new ArrayList<>());
        recipe.setFirstVersion(true);
        recipe.setIngredients(dto.getIngredients());
        return recipe;
    }
}
