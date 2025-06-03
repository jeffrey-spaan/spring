package com.example.chatclient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("You provide a nutrition plan and a training plan in JSON format for a person with the following details: name {name}, gender {gender}, age {age}, height {height}, currentWeight {currentWeight}, targetWeight {targetWeight}, currentFatPercentage {currentFatPercentage}, targetFatPercentage {targetFatPercentage}, dietPreference {dietPreference}, goal {goal}, sportType {sportType}, trainingDaysPerWeek {trainingDaysPerWeek}, additionalDetails {additionalDetails}")
                .build();
    }
}