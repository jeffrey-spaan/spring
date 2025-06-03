package com.example.chatclient;

import lombok.Data;

import java.util.List;

@Data
public class MealType {

    private String name; // E.g., "Breakfast", "Lunch", "Dinner", "Snack"
    private List<Meal> meals;
}