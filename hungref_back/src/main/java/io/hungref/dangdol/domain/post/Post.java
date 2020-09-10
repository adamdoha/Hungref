package io.hungref.dangdol.domain.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.hungref.dangdol.domain.BaseTimeEntity;
import io.hungref.dangdol.domain.cook.Cook;
import io.hungref.dangdol.domain.tag.Tag;
import io.hungref.dangdol.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(optional = false)
    @JsonBackReference
    User postingUser;

    @Column(length=500 , nullable = false)
    private String title;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Tag> tags=new ArrayList<>();

    @Column(nullable = false)
    private int views;

    @Column(nullable = false)
    private int hearts;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonBackReference
    private Cook cook;

    @Builder
    public Post(User postingUser,String title){
        this.postingUser =postingUser;
        this.title=title;
        this.views=0;
        this.hearts=0;
    }

    public void setCook(Cook cook){
        this.cook=cook;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }
}