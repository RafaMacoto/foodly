package com.example.foodly.controller;

import com.example.foodly.dto.RecipeDTO;
import com.example.foodly.dto.RecipeResponseDTO;
import com.example.foodly.mapper.RecipeMapper;
import com.example.foodly.model.recipe.Recipe;
import com.example.foodly.repository.RecipeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping
    @Cacheable("recipes")
    public List<RecipeResponseDTO> index() {
        return recipeRepository.findAll().stream().map(RecipeMapper::toDTO).toList();
    }

    @PostMapping
    @CacheEvict(value = "recipes", allEntries = true)
    @Operation(summary = "Cadastrar receita", description = "Insere uma receita...", responses = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400"),
    })
    public ResponseEntity<RecipeResponseDTO> create(@RequestBody @Valid RecipeDTO recipe){
        Recipe recipe1 = RecipeMapper.toEntity(recipe);
        recipeRepository.save(recipe1);
        return ResponseEntity.status(HttpStatus.CREATED).body(RecipeMapper.toDTO(recipe1));
    }

    @GetMapping("{id}")
    public ResponseEntity<RecipeResponseDTO> get(@PathVariable long id){
        return ResponseEntity.ok(RecipeMapper.toDTO(getRecipe(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<RecipeResponseDTO> update(@PathVariable Long id, @RequestBody @Valid RecipeDTO dto) {
        Optional<Recipe> receitaOptional = recipeRepository.findById(id);
        if (receitaOptional.isEmpty()) return ResponseEntity.notFound().build();

        Recipe receita = receitaOptional.get();
        receita.setTitle(dto.title());
        receita.setImage(dto.image());
        receita.setTime(dto.time());
        receita.setServings(dto.servings());
        receita.setDifficulty(dto.difficulty());
        receita.setIngredients(dto.ingredients());
        receita.setInstructions(dto.instructions());
        receita.setCategory(dto.category());

        Recipe atualizada = recipeRepository.save(receita);
        return ResponseEntity.ok(RecipeMapper.toDTO(atualizada));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        recipeRepository.delete(getRecipe(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private Recipe getRecipe(Long id) {
        return recipeRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }
}
