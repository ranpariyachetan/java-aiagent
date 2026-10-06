package com.dotawsnet.aiagent.Services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import com.dotawsnet.aiagent.aitools.*;
@Service 
public class ChatService {
    
    private ChatClient chatClient;

    private CalculatorTool calculatorTool;
    private WeatherTool weatherTool;
    private CurrencyExchangeTool currencyExchangeTool;
    private List<Message> conversationHistory = new ArrayList<>();

    private static final String SYSTEM_PROMPT = """
            You are a helpful AI assistant with access to external tools.

            Follow these rules:
            1. For arithmetic calculations, ALWAYS use the calculator tool.
            2. Always use calculator tool for even trivial calculation
            3. For current weather, ALWAYS use the currentWeather tool.
            4. For currency conversion or exchange rates, ALWAYS use the convertCurrency tool.
            5. You may call multiple tools when solving a multi-step request.
            6. After receiving tool results, explain the answer naturally.
            7. Never invent current weather or exchange-rate information.
            """;;

    public ChatService(
            ChatClient.Builder chatClient, 
            CalculatorTool calculatorTool, 
            WeatherTool weatherTool,
            CurrencyExchangeTool currencyExchangeTool) {
        this.chatClient = chatClient.build();
        this.calculatorTool = calculatorTool;
        this.weatherTool = weatherTool;
        this.currencyExchangeTool = currencyExchangeTool;
    }

    public String chat(String message) {
        conversationHistory.add(new UserMessage(message));
        String response = chatClient
        .prompt()
        .system(SYSTEM_PROMPT)
        .messages(conversationHistory)
        .tools(calculatorTool, weatherTool, currencyExchangeTool)
        .call()
        .content();
        conversationHistory.add(new AssistantMessage(response));
        return response;
    }
}
