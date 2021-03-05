package com.dev.cookbook.contoller;

import com.dev.cookbook.dto.request.RecipeRequestDto;
import com.dev.cookbook.dto.response.RecipeResponseDto;
import com.dev.cookbook.entity.Ingredient;
import com.dev.cookbook.entity.Recipe;
import com.dev.cookbook.repository.RecipeRepository;
import com.dev.cookbook.service.RecipeService;
import com.dev.cookbook.service.mapper.RecipeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecipeController.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
class RecipeControllerTest {
    public static final long RECIPE_ID = 1L;
    public static final String SAVE_RECIPE_URL = "http://localhost:8080/recipes/save/";
    public static final String GET_ALL_RECIPES_URL = "http://localhost:8080/recipes/";
    public static final String DELETE_RECIPE_URL = "http://localhost:8080/recipes/delete/{id}";
    @Autowired
    private MockMvc mvc;
    @MockBean
    private RecipeService recipeService;
    @MockBean
    private RecipeRepository recipeRepository;
    @MockBean
    private RecipeMapper recipeMapper;
    @Autowired
    private ObjectMapper objectMapper;

    public static final String PREAMBLE = "Soup";
    public static final String BASIC_DESCRIPTION = "Basic soup";
    public static final long ID = 1L;
    public static final String SOUP_INGREDIENT = "Soup ingredient";
    private Ingredient basicIngredient;
    private Recipe recipe;
    private RecipeRequestDto requestDto;
    private List<Recipe> recipeList;
    private List<Recipe> emptyRecipeList;

    @BeforeEach
    void setUp() {
        recipeMapper = new RecipeMapper();
        basicIngredient = new Ingredient();
        basicIngredient.setId(ID);
        basicIngredient.setName(SOUP_INGREDIENT);

        requestDto = new RecipeRequestDto();
        requestDto.setPreamble(PREAMBLE);
        requestDto.setDescription(BASIC_DESCRIPTION);
        requestDto.setIngredients(Set.of(basicIngredient));

        recipe = new Recipe();
        recipe.setId(ID);
        recipe.setPreviousVersions(new ArrayList<>());
        recipe.setPreamble(PREAMBLE);
        recipe.setDescription(BASIC_DESCRIPTION);
        recipe.setIngredients(Set.of(basicIngredient));
        recipe.setFirstVersion(true);

        recipeList = new ArrayList<>();
        emptyRecipeList = new ArrayList<>();
        recipeList.add(recipe);
    }

    @Test
    void saveRecipe_Ok() throws Exception {
        Recipe newRecipe = recipeMapper.toEntity(requestDto);
        RecipeResponseDto responseDto = recipeMapper.toDto(newRecipe);
        String jsonResponse = objectMapper.writeValueAsString(responseDto);
        mvc.perform(post(SAVE_RECIPE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonResponse))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllRecipes_Ok() throws Exception {
        given(recipeService.getAll()).willReturn(recipeList);
        mvc.perform(get(GET_ALL_RECIPES_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(recipeList.size())));
    }

    @Test
    void getEmptyList_Ok() throws Exception {
        given(recipeService.getAll()).willReturn(emptyRecipeList);
        mvc.perform(get(GET_ALL_RECIPES_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(emptyRecipeList.size())));
    }

    @Test
    void deleteRecipe_Ok() throws Exception {
        Recipe recipeToDelete = recipe;
        recipeToDelete.setId(RECIPE_ID);
        given(recipeService.getById(RECIPE_ID)).willReturn(Optional.of(recipeToDelete));
        doNothing().when(recipeService).delete(recipeToDelete.getId());
        mvc.perform(MockMvcRequestBuilders.delete(DELETE_RECIPE_URL, recipeToDelete.getId()))
                .andExpect(status().isOk());
    }

    @AfterEach
    void tearDown() {
        recipeMapper = null;
        basicIngredient = null;
        requestDto = null;
        recipe = null;
        recipeList = null;
        emptyRecipeList = null;
    }
}
