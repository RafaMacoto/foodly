package com.example.foodly.model.recipe;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String title;

    @NotBlank(message = "Campo obrigatório")
    private String image;

    @NotBlank(message = "Campo obrigatório")
    private String time;

    @NotNull(message = "Campo obrigatório")
    private int servings;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo obrigatório")
    private DifficultyType difficulty;

    @ElementCollection
    @Size(min = 1, message = "A lista de ingredientes não pode estar vazia")
    private List<String> ingredients;

    @ElementCollection
    @Size(min = 1, message = "A lista de intruções não pode estar vazia")
    private List<String> instructions;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Campo obrigatório")
    private CategoryType category;


}
