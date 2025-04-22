package com.example.foodly.specification;

import com.example.foodly.dto.RecipeFilterDTO;
import com.example.foodly.model.recipe.Recipe;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RecipeSpecification {

    public static Specification<Recipe> withFilter(RecipeFilterDTO filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.title() != null) {
                predicates.add(
                        cb.like(root.get("title"), filter.title())
                );
            }
            if (filter.servings() != 0) {
                predicates.add(cb.equal(root.get("description"), filter.servings()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);

            return cb.and(arrayPredicates);
        };
    }
}
