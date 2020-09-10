package io.hungref.dangdol.dto.ingredient;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SaveIngredientRequestDto {
    String ingredientName;

    @Builder
    public SaveIngredientRequestDto(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
