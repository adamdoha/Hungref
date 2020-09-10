package io.hungref.dangdol.service;

import io.hungref.dangdol.Exceptions.ingredient.DuplicateIngredientException;
import io.hungref.dangdol.domain.ingredient.Ingredient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientService {
    private final DBService db;

    public void save(String ingredientName) {
        Ingredient ingredientEntity=db.findIngredientByCookIngredientName(ingredientName);
        if(ingredientEntity!=null) throw new DuplicateIngredientException();

        Ingredient ingredient=Ingredient.builder()
                .ingredientName(ingredientName)
                .build();
        db.saveIngredient(ingredient);
    }
}