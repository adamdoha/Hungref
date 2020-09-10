package io.hungref.dangdol.dto.cook;

import io.hungref.dangdol.domain.cook.Cook;
import io.hungref.dangdol.domain.cook.CookIngredient;
import io.hungref.dangdol.domain.ingredient.Ingredient;
import io.hungref.dangdol.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CookRequestDto {
    String cookName;
    List<String> ingredients;
    String recipe;
    String category;

    @Builder
    public CookRequestDto(String cookName, List<String> ingredients, String recipe, String category){
        this.cookName = cookName;
        this.ingredients=ingredients;
        this.recipe=recipe;
        this.category=category;
    }

    public Cook toEntity(Post post){
        return Cook.builder()
                .cookName(cookName)
                .post(post)
                .recipe(recipe)
                .category(category)
                .build();
    }
}