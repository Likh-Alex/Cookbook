package com.dev.cookbook.service.mapper;

import com.dev.cookbook.dto.request.RecipeRequestDto;
import com.dev.cookbook.dto.response.RecipeResponseDto;
import com.dev.cookbook.entity.Ingredient;
import com.dev.cookbook.entity.Recipe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Set;

class RecipeMapperTest {
    public static final String PREAMBLE = "Soup";
    public static final String BASIC_DESCRIPTION = "Basic soup";
    public static final long ID = 1L;
    public static final String SOUP_INGREDIENT = "Soup ingredient";
    private RecipeMapper recipeMapper;
    private Recipe recipe;
    private RecipeRequestDto requestDto;
    private RecipeResponseDto responseDto;
    private Ingredient basicIngredient;

    @BeforeEach
    void setUp() {
        recipeMapper = new RecipeMapper();

        basicIngredient = new Ingredient();
        basicIngredient.setId(ID);
        basicIngredient.setName(SOUP_INGREDIENT);

        recipe = new Recipe();
        recipe.setId(ID);
        recipe.setPreviousVersions(new ArrayList<>());
        recipe.setPreamble(PREAMBLE);
        recipe.setDescription(BASIC_DESCRIPTION);
        recipe.setIngredients(Set.of(basicIngredient));
        recipe.setFirstVersion(true);


        requestDto = new RecipeRequestDto();
        requestDto.setPreamble(PREAMBLE);
        requestDto.setDescription(BASIC_DESCRIPTION);
        requestDto.setIngredients(Set.of(basicIngredient));

        responseDto = new RecipeResponseDto();
        responseDto.setId(ID);
        responseDto.setPreamble(PREAMBLE);
        responseDto.setDescription(BASIC_DESCRIPTION);
        responseDto.setIngredients(Set.of(basicIngredient));
        responseDto.setFirstVersion(true);
        responseDto.setPreviousVersions(new ArrayList<>());
    }

    @Test
    void checkReqDtoToResDto_Ok() {
        RecipeResponseDto actualDto = recipeMapper.toDto(recipe);
        RecipeResponseDto expectedDto = responseDto;
        Assertions.assertEquals(actualDto, expectedDto);
    }

    @Test
    void checkReqDtoToEntity_Ok() {
        Recipe actualEntity = recipeMapper.toEntity(requestDto);
        actualEntity.setId(ID);
        Recipe expectedEntity = recipe;
        Assertions.assertEquals(actualEntity, expectedEntity);

    }

    @AfterEach
    void tearDown() {
        recipe = null;
        requestDto = null;
        responseDto = null;
        recipeMapper = null;
        basicIngredient = null;
    }
}