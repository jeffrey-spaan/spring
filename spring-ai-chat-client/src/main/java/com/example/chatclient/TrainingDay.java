package com.example.chatclient;

import lombok.Data;

import java.util.List;

@Data
public class TrainingDay {
    private int day;
    private String type;
    private List<Exercise> exercises;
}