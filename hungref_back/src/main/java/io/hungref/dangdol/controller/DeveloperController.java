package io.hungref.dangdol.controller;

import io.hungref.dangdol.domain.cook.Cook;
import io.hungref.dangdol.domain.ingredient.Ingredient;
import io.hungref.dangdol.domain.post.Post;
import io.hungref.dangdol.domain.user.User;
import io.hungref.dangdol.service.DBService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/develop/")
public class DeveloperController {
    private final DBService dbService;

    @ApiOperation("Database 초기화")
    @GetMapping("/init")
    public ResponseEntity init(){
        dbService.DatabaseInit();
        String message="삭제했음";
        return ResponseEntity.ok(message);
    }

    @ApiOperation("Post 전체 정보")
    @GetMapping("/posts")
    public ResponseEntity posts(){
        List<Post> list=dbService.findAllPosts();
        return ResponseEntity.ok(list);
    }

    @ApiOperation("요리 전체 정보")
    @GetMapping("/cooks")
    public ResponseEntity cooks(){
        List<Cook> list=dbService.findAllCooks();
        return ResponseEntity.ok(list);
    }

    @ApiOperation("User 전체 정보")
    @GetMapping("/users")
    public ResponseEntity users(){
        List<User> list=dbService.findAllUsers();
        return ResponseEntity.ok(list);
    }

    @ApiOperation("재료 전체 정보")
    @GetMapping("/ingredients")
    public ResponseEntity ingredients(){
        List<Ingredient> list=dbService.findAllIngredients();
        return ResponseEntity.ok(list);
    }

}
