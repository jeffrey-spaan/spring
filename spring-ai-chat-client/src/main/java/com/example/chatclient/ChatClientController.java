package com.example.chatclient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatclient")
public class ChatClientController {
    private final ChatClient chatClient;

    public ChatClientController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping
    Plan createPlan(@RequestBody PersonDetails personDetails) {
        return this.chatClient.prompt()
                        .system(sp -> sp
                                .param("name", personDetails.getName())
                                .param("gender", personDetails.getGender())
                                .param("age", personDetails.getAge())
                                .param("height", personDetails.getHeight())
                                .param("currentWeight", personDetails.getCurrentWeight())
                                .param("targetWeight", personDetails.getTargetWeight())
                                .param("currentFatPercentage", personDetails.getCurrentFatPercentage())
                                .param("targetFatPercentage", personDetails.getTargetFatPercentage())
                                .param("dietPreference", personDetails.getDietPreference())
                                .param("goal", personDetails.getGoal())
                                .param("sportType", personDetails.getSportType())
                                .param("trainingDaysPerWeek", personDetails.getTrainingDaysPerWeek())
                                .param("additionalDetails", personDetails.getAdditionalDetails())
                        )
                        .user("provide the nutrition plan and training plan in JSON format")
                        .call()
                        .entity(Plan.class);
    }
}