package com.dev.cookbook.contoller;

import com.dev.cookbook.dto.request.RecipeRequestDto;
import com.dev.cookbook.dto.response.RecipeResponseDto;
import com.dev.cookbook.entity.Recipe;
import com.dev.cookbook.service.RecipeService;
import com.dev.cookbook.service.mapper.RecipeMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
@AllArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    @PostMapping("/save")
    public ResponseEntity<RecipeResponseDto> save(@RequestBody RecipeRequestDto dto) {
        Recipe recipe = recipeMapper.toEntity(dto);
        Recipe savedRecipe = recipeService.save(recipe);
        RecipeResponseDto responseDto = recipeMapper.toDto(savedRecipe);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping()
    public List<RecipeResponseDto> getAll() {
        return recipeService.getAll()
                .stream()
                .map(recipeMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/fork/{id}")
    public ResponseEntity<RecipeResponseDto> fork(@RequestBody RecipeRequestDto dto,
                                                  @PathVariable Long id) {
        Recipe forkedRecipe = recipeMapper.toEntity(dto);
        Optional<Recipe> parentRecipe = recipeService.getById(id);
        if (parentRecipe.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        forkedRecipe.setParent(parentRecipe.get());
        forkedRecipe.getPreviousVersions().add(parentRecipe.get());
        Recipe updatedRecipe = recipeService.save(forkedRecipe);
        RecipeResponseDto recipeResponseDto = recipeMapper.toDto(updatedRecipe);
        return new ResponseEntity<>(recipeResponseDto, HttpStatus.OK);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<RecipeResponseDto> modify(@RequestBody RecipeRequestDto dto,
                                                    @PathVariable Long id) {
        Optional<Recipe> recipeById = recipeService.getById(id);
        if (recipeById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Recipe updatedRecipe = recipeMapper.toEntity(dto);
        updatedRecipe.setFirstVersion(false);
        updatedRecipe.setId(id);
        Recipe recipeFromDb = recipeService.save(updatedRecipe);
        RecipeResponseDto responseDto = recipeMapper.toDto(recipeFromDb);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/versions/{id}")
    public ResponseEntity<List<RecipeResponseDto>> getPreviousVersions(@PathVariable Long id) {
        Optional<Recipe> recipeById = recipeService.getById(id);
        if (recipeById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<RecipeResponseDto> responseDto = recipeById.get()
                .getPreviousVersions()
                .stream()
                .map(recipeMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
