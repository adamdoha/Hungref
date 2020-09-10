package io.hungref.dangdol.dto.post;

import io.hungref.dangdol.domain.post.Post;
import io.hungref.dangdol.domain.user.User;
import io.hungref.dangdol.dto.cook.CookRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    String title;
    List<String> tags;
    CookRequestDto cookRequestDto;

    @Builder
    public PostRequestDto(String title,List<String> tags,CookRequestDto cookRequestDto){
        this.title=title;
        this.tags=tags;
        this.cookRequestDto=cookRequestDto;
    }

    public Post toEntity(User user){
        return Post.builder()
                .postingUser(user)
                .title(title)
                .build();
    }
}