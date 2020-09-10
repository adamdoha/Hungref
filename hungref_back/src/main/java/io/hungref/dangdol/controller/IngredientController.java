package io.hungref.dangdol.controller;

import io.hungref.dangdol.dto.ingredient.SaveIngredientRequestDto;
import io.hungref.dangdol.service.IngredientService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class IngredientController {
    private final IngredientService ingredientService;

    @ApiOperation("Database에 재료를 저장합니다.")
    @PostMapping("/ingredient/save")
    public ResponseEntity saveIngredient(@RequestBody SaveIngredientRequestDto saveIngredientRequestdto){
        String ingredientName=saveIngredientRequestdto.getIngredientName();
        ingredientService.save(ingredientName);

        String message="재료가 저장되었습니다.";
        return ResponseEntity.ok(message);
    }
}
