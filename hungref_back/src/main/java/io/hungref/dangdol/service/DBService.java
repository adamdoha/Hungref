package io.hungref.dangdol.service;

import io.hungref.dangdol.domain.cook.*;
import io.hungref.dangdol.domain.ingredient.Ingredient;
import io.hungref.dangdol.domain.ingredient.IngredientRepository;
import io.hungref.dangdol.domain.post.Post;
import io.hungref.dangdol.domain.post.PostRepository;
import io.hungref.dangdol.domain.tag.Tag;
import io.hungref.dangdol.domain.tag.TagRepository;
import io.hungref.dangdol.domain.user.User;
import io.hungref.dangdol.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DBService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CookRepository cookRepository;
    private final CookIngredientRepository cookIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final TagRepository tagRepository;

    public Post uploadPost(Post post) {
        return postRepository.save(post);
    }

    public Cook uploadCook(Cook cook) {
        return cookRepository.save(cook);
    }

    public void uploadCookIngredient(CookIngredient cookIngredientEntity) {
        cookIngredientRepository.save(cookIngredientEntity);
    }

    public void signUp(User user) {
        userRepository.save(user);
    }

    
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public Ingredient findIngredientByCookIngredientName(String cookIngredientName) {
        return ingredientRepository.findByCookIngredientName(cookIngredientName);
    }

    @Transactional
    public void DatabaseInit() {
        ingredientRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Cook> findAllCooks() {
        return cookRepository.findAll();
    }

    public void uploadTag(Tag tag) {
        tagRepository.save(tag);
    }
}
