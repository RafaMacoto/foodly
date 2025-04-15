package com.example.foodly.repository;

import com.example.foodly.model.recipe.CategoryType;
import com.example.foodly.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCategory(CategoryType categoryType);
}
