package io.hungref.dangdol.domain.tag;

import io.hungref.dangdol.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long tagId;

    @Column
    String tag;

    @ManyToOne
    Post post;

    @Builder
    public Tag(String tag, Post post) {
        this.tag = tag;
        this.post = post;
        this.post.addTag(this);
    }
}
