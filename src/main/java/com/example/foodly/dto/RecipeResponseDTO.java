package com.example.foodly.dto;

import com.example.foodly.model.CategoryType;
import com.example.foodly.model.DifficultyType;

import java.util.List;

public record RecipeResponseDTO(
        Long id,
        String title,
        String image,
        String time,
        int servings,
        DifficultyType difficulty,
        List<String> ingredients,
        List<String> instructions,
        CategoryType category

) {
}
