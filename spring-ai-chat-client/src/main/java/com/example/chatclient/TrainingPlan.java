package com.example.chatclient;

import lombok.Data;

import java.util.List;

@Data
public class TrainingPlan {

    private String sportType;
    private String trainingDaysPerWeek;
    private List<TrainingDay> trainingDays;
}