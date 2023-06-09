package com.brainstrom.meokjang.food.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FoodRequest {
    private Long userId;
    private FoodDto food;

    public FoodRequest(Long userId, FoodDto food) {
        this.userId = userId;
        this.food = food;
    }
}
