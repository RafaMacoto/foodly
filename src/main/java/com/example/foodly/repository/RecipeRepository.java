package com.example.foodly.repository;

import com.example.foodly.model.recipe.CategoryType;
import com.example.foodly.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {

    List<Recipe> findByCategory(CategoryType categoryType);
}
