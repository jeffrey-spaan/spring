package com.example.chatclient;

import lombok.Data;

import java.util.List;

@Data
public class NutritionPlan {
    private List<MealType> mealTypes;
}