package io.hungref.dangdol.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.hungref.dangdol.domain.BaseTimeEntity;
import io.hungref.dangdol.domain.comment.Comment;
import io.hungref.dangdol.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable =false)
    private String email;
    @Column(nullable =false)
    private String password;
    @Column(nullable =false)
    private String nickname;
    @Column(nullable =false)
    private String phone;

    @OneToMany(mappedBy = "postingUser")
    @JsonBackReference
    List<Post> posts=new ArrayList<>();

    @OneToMany(mappedBy ="commentUser")
    @JsonBackReference
    List<Comment> comments=new ArrayList<>();


    @Builder
    public User(String email,String password,String nickname,String phone){
        this.email=email;
        this.password=password;
        this.nickname=nickname;
        this.phone=phone;
    }
}