package io.hungref.dangdol.domain.cook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.hungref.dangdol.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
public class CookIngredient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cookIngredientId;

    @NotNull
    private String ingredientName;

    @ManyToOne(optional = false)
    @JsonBackReference
    private Cook cook;

    @Builder
    public CookIngredient(Cook cook, String ingredientName){
        this.ingredientName=ingredientName;
        this.cook=cook;
        this.cook.addIngredient(this);
    }
}