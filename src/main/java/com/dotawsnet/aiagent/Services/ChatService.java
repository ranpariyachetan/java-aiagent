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
    private List<Message> conversationHistory = new ArrayList<>();

    private static final String SYSTEM_PROMPT = """
            You are a helpful AI assistant with access to external tools.

            Follow these rules:
            1. For arithmetic operations, ALWAYS use the CalculatorTool.
            2. After receiving tool results, explain the answer naturally.
            """;;

    public ChatService(ChatClient.Builder chatClient, CalculatorTool calculatorTool) {
        this.chatClient = chatClient.build();
        this.calculatorTool = calculatorTool;
    }

    public String chat(String message) {
        conversationHistory.add(new UserMessage(message));
        String response = chatClient
        .prompt()
        .system(SYSTEM_PROMPT)
        .messages(conversationHistory)
        .tools(calculatorTool)
        .call()
        .content();
        conversationHistory.add(new AssistantMessage(response));
        return response;
    }
}
