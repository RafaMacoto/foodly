package com.example.foodly.config;


import com.example.foodly.model.recipe.CategoryType;
import com.example.foodly.model.recipe.DifficultyType;
import com.example.foodly.model.recipe.Recipe;
import com.example.foodly.repository.RecipeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private RecipeRepository repository;

    @PostConstruct
    public void init() {
        var recipes = List.of(
                Recipe.builder()
                        .title("Bolo de Chocolate")
                        .image("https://example.com/imagem-bolo.jpg")
                        .time("45 minutos")
                        .servings(8)
                        .difficulty(DifficultyType.FACIL)
                        .ingredients(List.of("2 ovos", "1 xícara de açúcar", "1 xícara de leite", "2 xícaras de farinha de trigo", "1 xícara de chocolate em pó"))
                        .instructions(List.of("Misture os ingredientes", "Asse em forno a 180°C por 40 minutos"))
                        .category(CategoryType.DOCES)
                        .build(),

                Recipe.builder()
                        .title("Strogonoff de Frango")
                        .image("https://example.com/imagem-strogonoff.jpg")
                        .time("30 minutos")
                        .servings(4)
                        .difficulty(DifficultyType.MEDIO)
                        .ingredients(List.of("500g de frango", "1 lata de creme de leite", "1 colher de ketchup", "1 cebola"))
                        .instructions(List.of("Refogue o frango", "Adicione o ketchup e o creme de leite", "Cozinhe por 10 minutos"))
                        .category(CategoryType.CARNES)
                        .build()
        );

        repository.saveAll(recipes);
    }
}
