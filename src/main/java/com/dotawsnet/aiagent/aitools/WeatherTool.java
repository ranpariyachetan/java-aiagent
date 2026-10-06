package com.dotawsnet.aiagent.aitools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WeatherTool {
    RestClient restClient;
    
    String apiKey="YOUR_API_KEY"; // Replace with your actual

    public WeatherTool(
            RestClient.Builder builder, 
            @Value("${weather.api.key}") String apiKey) {
        this.restClient = builder.baseUrl("https://api.weatherapi.com/v1").build();
        this.apiKey = apiKey;
    }

    @Tool (description = "Fetches the current weather for a specified city.")
    public String currentWeather(
            @ToolParam(description = "The name of the city for which to fetch the current weather.")
            String city) {

        System.out.println("Weather tool called");

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/current.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", city)
                        .build())
                .retrieve()
                .body(String.class);
    }
}
