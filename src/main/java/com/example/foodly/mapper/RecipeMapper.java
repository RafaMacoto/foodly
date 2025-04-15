package com.example.foodly.mapper;

import com.example.foodly.dto.RecipeDTO;
import com.example.foodly.dto.RecipeResponseDTO;
import com.example.foodly.model.recipe.Recipe;

public class RecipeMapper {
    public static RecipeResponseDTO toDTO(Recipe recipe) {
        return new RecipeResponseDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getImage(),
                recipe.getTime(),
                recipe.getServings(),
                recipe.getDifficulty(),
                recipe.getIngredients(),
                recipe.getInstructions(),
                recipe.getCategory()
        );
    }

    public static Recipe toEntity(RecipeDTO dto) {
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.title());
        recipe.setImage(dto.image());
        recipe.setTime(dto.time());
        recipe.setServings(dto.servings());
        recipe.setDifficulty(dto.difficulty());
        recipe.setIngredients(dto.ingredients());
        recipe.setInstructions(dto.instructions());
        recipe.setCategory(dto.category());
        return recipe;
    }
}
