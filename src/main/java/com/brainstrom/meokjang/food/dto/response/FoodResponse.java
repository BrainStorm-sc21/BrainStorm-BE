package com.brainstrom.meokjang.food.dto.response;

import com.brainstrom.meokjang.food.domain.Food;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class FoodResponse {
    private final Long foodId;
    private final String foodName;

    private final Integer stock;

    private final LocalDate expireDate;

    private final String storageWay;

    private final LocalDateTime createdAt;

    public FoodResponse(Food food) {
        this.foodId = food.getFoodId();
        this.foodName = food.getFoodName();
        this.stock = food.getStock();
        this.expireDate = food.getExpireDate();
        this.storageWay = food.getStorageWay();
        this.createdAt = food.getCreatedAt();
    }
}