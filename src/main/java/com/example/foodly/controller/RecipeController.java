package com.example.foodly.controller;

import com.example.foodly.dto.RecipeDTO;
import com.example.foodly.dto.RecipeFilterDTO;
import com.example.foodly.dto.RecipeResponseDTO;
import com.example.foodly.mapper.RecipeMapper;
import com.example.foodly.model.recipe.Recipe;
import com.example.foodly.repository.RecipeRepository;
import com.example.foodly.specification.RecipeSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    @Operation(summary = "Listar receitas", description = "Lista receitas...", responses = {@ApiResponse(responseCode = "200")})
    public Page<Recipe> index(
            RecipeFilterDTO filter,
            @PageableDefault(size = 10 , sort = "servings", direction = Sort.Direction.DESC) Pageable pageable) {

//        var probe = Recipe.builder().title(filter.title()).servings(filter.servings()).build();
//        var example = Example.of(probe);
//        Page<Recipe> recipes = recipeRepository.findAll(example, pageable);
//        Page<RecipeResponseDTO> recipeDto = recipes.map(r -> RecipeMapper.toDTO(r));

        var specification = RecipeSpecification.withFilter(filter);

        return recipeRepository.findAll(specification, pageable);
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
