package io.hungref.dangdol.domain.ingredient;

import io.hungref.dangdol.domain.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {

    @Query("select i from Ingredient i where i.ingredientName = :cookIngredientName")
    Ingredient findByCookIngredientName(@Param("cookIngredientName") String cookIngredientName);
}