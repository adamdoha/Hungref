package io.hungref.dangdol.service;

import io.hungref.dangdol.domain.cook.*;
import io.hungref.dangdol.domain.ingredient.Ingredient;
import io.hungref.dangdol.domain.post.Post;
import io.hungref.dangdol.domain.tag.Tag;
import io.hungref.dangdol.domain.user.User;
import io.hungref.dangdol.dto.cook.CookRequestDto;
import io.hungref.dangdol.dto.post.PostRequestDto;
import io.hungref.dangdol.dto.user.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final DBService db;

    @Transactional
    public boolean upload(UserInfo userInfo, PostRequestDto postRequestDto){
        //유저 정보 가져오기
        User user=db.findUserByEmail(userInfo.getEmail());

        //요리 정보 가져오기
        CookRequestDto cookRequestDto=postRequestDto.getCookRequestDto();

        //요리에 사용된 재료정보 가져오기
        List<String> cookIngredients=cookRequestDto.getIngredients();

        //태그 정보 가져오기
        List<String> tags=postRequestDto.getTags();

        //Post DB화, Cook DB화
        Post post=db.uploadPost(postRequestDto.toEntity(user));
        Cook cook=db.uploadCook(cookRequestDto.toEntity(post));

        //CookIngredient DB화
        for(String ingredientName:cookIngredients){
            CookIngredient cookIngredient=CookIngredient.builder()
                    .ingredientName(ingredientName)
                    .cook(cook)
                    .build();
            db.uploadCookIngredient(cookIngredient);
        }

        //Tag DB화
        for(String tagName:tags){
            Tag tag=Tag.builder()
                    .tag(tagName)
                    .post(post)
                    .build();
            db.uploadTag(tag);
        }
        return true;
    }
}