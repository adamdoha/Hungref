package io.hungref.dangdol.domain.ingredient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.hungref.dangdol.domain.BaseTimeEntity;
import io.hungref.dangdol.domain.cook.CookIngredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Ingredient extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ingredientId;

    @Column
    String ingredientName;

    @Builder
    public Ingredient(String ingredientName){
        this.ingredientName = ingredientName;
    }
}