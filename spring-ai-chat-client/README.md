# Spring AI Chat Client

This is a simple <strong>chat client for Spring AI</strong>. It allows you to interact with the Spring AI API.
The main features include:
- Retrieving nutrition information for a given person's details.
- Retrieving a list of workouts

<b>Author:</b> <a href="https://github.com/jeffrey-spaan" target="_blank">Jeffrey Spaan</a><br>
<b>Created:</b> 2025-06-03<br>
<b>Last updated:</b> 2025-06-03

[![](https://img.shields.io/badge/Spring%20Boot-8A2BE2)]() [![](https://img.shields.io/badge/release-May%2022,%202025-blue)]() [![](https://img.shields.io/badge/version-3.5.0-blue)]()

## 1. Why should you use a Chat Client?

- **User-friendly**: A <strong>chat client</strong> provides a simple and intuitive interface for interacting with the Spring AI API.
- **Quick access**: You can quickly retrieve information without needing to write code or use complex tools.
- **Interactive**: The chat interface allows for a more conversational interaction, making it easier to ask follow-up questions and get relevant information. This is not covered in this example.
- **Versatile**: You can use the <strong>chat client</strong> for various tasks, such as retrieving nutrition information, workout plans, and more.

## 2. How the Chat Client implementation works in Spring AI

The <strong>chat client</strong> is built using the Spring AI framework, which provides a simple way to create AI-powered applications.<br />

Let's create an application using the dependencies as previewed:

![01-start-spring-io](https://github.com/jeffrey-spaan/spring/blob/main/spring-ai-chat-client/images/01-start-spring-io.jpg)

[![](https://img.shields.io/badge/Lombok-8A2BE2)]()
Because it is just that easy to use.
Want to know more about <b>Project Lombok</b>? [Click this link](https://projectlombok.org/features/)

[![](https://img.shields.io/badge/OpenAI-8A2BE2)]()
The <b>OpenAI</b> dependency is used to integrate with the OpenAI API, which provides the underlying AI capabilities for the chat client. It allows us to send and receive messages from the AI model.

[![](https://img.shields.io/badge/Spring%20Web-8A2BE2)]()
This Spring Framework dependency will provide us with all the necessary functionality to create and manage our REST endpoints.

### 2.1 Creating an OpenAI API Key
To use the OpenAI API, you need to create an API key. Follow these steps:
1. Go to the [OpenAI website](https://auth.openai.com/create-account).
2. Sign up for an account or log in if you already have one.
3. Navigate to settings > API Keys.
4. Create a new API key and copy it.
5. Store the API key securely within your application, using a .env file or environment variable.

### 2.2 Configuring the OpenAI API Key in Spring Boot

To configure the OpenAI API key in your Spring Boot application, you can use the `application.yml` file. Add the following lines to the file:

```properties
spring:
  ai:
    openai:
      api-key: your_api_key
      chat:
        options:
          model: gpt-4
```
Replace `your_api_key` with your actual OpenAI API key and `gpt-4` with the desired chat model (e.g., `gpt-4`).

### 2.3 Create the Chat Client Config

We need to create a Chat Client configuration class to enable the chat client functionality:

```java
@Configuration
class Config {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.defaultSystem("You provide a nutrition plan and a training plan in JSON format for a person with the following details: name {name}, gender {gender}, age {age}, height {height}, currentWeight {currentWeight}, targetWeight {targetWeight}, currentFatPercentage {currentFatPercentage}, targetFatPercentage {targetFatPercentage}, dietPreference {dietPreference}, goal {goal}, sportType {sportType}, trainingDaysPerWeek {trainingDaysPerWeek}, additionalDetails {additionalDetails}")
                .build();
    }
}
```

The configuration class defines a bean for the `ChatClient`, which is used to send and receive messages from the OpenAI API.<br />
The `defaultSystem` method sets the system prompt that will be used for all chat requests.

Basically, the `ChatClient` is configured to use a system prompt that provides the necessary context for generating a nutrition and training plan based on the provided person details.

### 2.4 Creating the PersonDetails and Plan classes

To represent the person details and the plan, you need to create two classes: `PersonDetails` and `Plan`.

`PersonDetails.java`
```java
@Data
public class PersonDetails {

    private String name;
    private String gender;
    private String age;
    private String height;
    private String currentWeight;
    private String targetWeight;
    private String currentFatPercentage;
    private String targetFatPercentage;
    private String dietPreference;
    private String goal;
    private String sportType;
    private String trainingDaysPerWeek;
    private String additionalDetails;
}
```
<br />

`Plan.java`
```java
@Data
public class Plan {
    private NutritionPlan nutritionPlan;
    private TrainingPlan trainingPlan;
}
```

Both the `NutritionPlan` and `TrainingPlan` classes have objects that represent the nutrition and training plans, respectively. You can define these classes based on your requirements.

For this example, the classes are defined so that the OpenAI response can return a JSON object that matches the following structure:

```json
{
  "nutritionPlan": {
    "mealTypes": [
      {
        "name": "Breakfast",
        "meals": [
          {
            "name": "Eggs and Toast",
            "calories": 350
          },
          {
            "name": "Protein Shake",
            "calories": 200
          }
        ]
      },
      {
        "name": "Lunch",
        "meals": [
          {
            "name": "Chicken Salad",
            "calories": 600
          },
          {
            "name": "Tuna Sandwich",
            "calories": 400
          }
        ]
      },
      {
        "name": "Dinner",
        "meals": [
          {
            "name": "Steak and Vegetables",
            "calories": 700
          },
          {
            "name": "Salmon and Rice",
            "calories": 600
          }
        ]
      }
    ]
  },
  "trainingPlan": {
    "sportType": "gym",
    "trainingDaysPerWeek": "3",
    "trainingDays": [
      {
        "day": 1,
        "type": "Upper Body",
        "exercises": [
          {
            "name": "Bench Press",
            "sets": 3,
            "reps": "10-12"
          },
          {
            "name": "Pull Ups",
            "sets": 3,
            "reps": "10-12"
          }
        ]
      },
      {
        "day": 2,
        "type": "Lower Body",
        "exercises": [
          {
            "name": "Squats",
            "sets": 3,
            "reps": "10-12"
          },
          {
            "name": "Deadlifts",
            "sets": 3,
            "reps": "10-12"
          }
        ]
      },
      {
        "day": 3,
        "type": "Full Body",
        "exercises": [
          {
            "name": "Burpees",
            "sets": 3,
            "reps": "10-12"
          },
          {
            "name": "Push Ups",
            "sets": 3,
            "reps": "10-12"
          }
        ]
      }
    ]
  }
}
```

### 2.5 Create the REST Endpoint

Now that the <strong>chat client</strong> is configured in our Spring AI application, it is time to use the newly implemented funcationality.

For this, we will create a REST endpoint using the standard Spring Web implementation.

```java
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
```

## 3. Spring AI Chat Client in Action

- To test the <b>REST endpoint</b>, a tool like <b>Postman</b> can be used to send the <b>HTTP POST request</b>.
- A Postman collection is added within the repository `src/main/resources/postman/collection-to-import.json`

[![](https://img.shields.io/badge/POST-yellow)]()<br/>
<small>Endpoint:</small> `http://localhost:8081/api/v1/chatclient`<br/>
<small>Body:</small><br/>
```json
{
  "name": "John Doe",
  "gender": "male",
  "age": "38",
  "height": "180cm",
  "currentWeight": "90kg",
  "targetWeight": "80kg",
  "currentFatPercentage": "30 percent",
  "targetFatPercentage": "10 percent",
  "dietPreference": "none",
  "goal": "build muscle",
  "sportType": "gym",
  "trainingDaysPerWeek": "3",
  "additionalDetails": "none"
}
```
<small>Returns:</small> A JSON object containing the nutrition and training plans based on the provided person details.

![02-postman-post-createplan](https://github.com/jeffrey-spaan/spring/blob/main/spring-ai-chat-client/images/02-postman-post-createplan.jpg)
<br>

## Let's Stay Connected

If you have any questions in regard to this repository and/or documentation, please do reach out.

Remember to:
- <b>Star</b> the [repository](https://github.com/jeffrey-spaan/spring)
- [Follow me](https://github.com/jeffrey-spaan) for more interesting repositories!