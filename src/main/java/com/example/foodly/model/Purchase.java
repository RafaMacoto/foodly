package com.example.foodly.model;


import com.example.foodly.model.recipe.Recipe;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    @Positive
    private BigDecimal amount;

    @PastOrPresent
    @NotNull
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    @ManyToOne
    private Recipe recipe;


}
