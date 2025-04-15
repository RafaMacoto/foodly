package com.example.foodly.dto;

import com.example.foodly.model.recipe.CategoryType;
import com.example.foodly.model.recipe.DifficultyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RecipeDTO(

        @NotBlank
        String title,
        @NotBlank
        String image,
        @NotBlank
        String time,
        @NotNull
        int servings,
        @NotNull
        DifficultyType difficulty,
        @Size(min = 1)
        List<String> ingredients,
        @Size(min = 1)
        List<String> instructions,
        @NotNull
        CategoryType category
) {
}
