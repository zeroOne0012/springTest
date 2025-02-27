package com.example.FirstProject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChefTest {
    @Autowired
    IngredientFactory ingredientFactory;
    @Autowired
    Chef chef;

    @Test
    void 돈가스_요리하기(){
        // 준비
//        IngredientFactory ingredientFactory = new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu = "돈가스";

        // 수행
        String food = chef.cook(menu);


        // 예상
        String expected = "등심 돈가스";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기(){
        // 준비
//        IngredientFactory ingredientFactory = new IngredientFactory();
//        Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";

        // 수행
        String food = chef.cook(menu);


        // 예상
        String expected = "한우 꽃등심 스테이크";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }
}