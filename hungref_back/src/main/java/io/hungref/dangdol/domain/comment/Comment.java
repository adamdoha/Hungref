package io.hungref.dangdol.domain.comment;

import io.hungref.dangdol.domain.BaseTimeEntity;
import io.hungref.dangdol.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long CommentId;

    @ManyToOne(optional = false)
    User commentUser;

    @Column(columnDefinition = "TEXT")
    String content;

    @Builder
    public Comment(User commentUser,String content){
        this.commentUser=commentUser;
        this.content=content;
    }
}