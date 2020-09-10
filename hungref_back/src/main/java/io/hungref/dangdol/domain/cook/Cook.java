package io.hungref.dangdol.domain.cook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.hungref.dangdol.domain.BaseTimeEntity;
import io.hungref.dangdol.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Cook extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cookId;

    @Column
    private String cookName;

    @OneToOne
    @JsonBackReference
    @Fetch(value = FetchMode.SELECT)
    private Post post;

    @OneToMany(mappedBy = "cook", cascade = CascadeType.ALL)
    private List<CookIngredient> ingredients=new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String recipe;

    @Column
    private String category;

    @Builder
    public Cook(Post post, String cookName, String recipe, String category){
        this.cookName = cookName;
        this.recipe=recipe;
        this.category=category;
        this.post=post;
        this.post.setCook(this);
    }

    public void addIngredient(CookIngredient cookIngredient) {
        this.ingredients.add(cookIngredient);
    }
}