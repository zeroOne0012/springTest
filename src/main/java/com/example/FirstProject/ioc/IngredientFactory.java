package com.example.FirstProject.ioc;

import org.springframework.stereotype.Component;

@Component // 해당 클래스를 객체를 만들고, 이를 IoC 컨테이너에 등록!
public class IngredientFactory {

    public Ingredient get(String menu) {
        switch(menu){
            case"돈가스":
                return new Pork("등심");
            case"스테이크":
                return new Beef("한우 꽃등심");
            default:
                return null;
        }
    }
}
