package com.dotawsnet.aiagent.Services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.messages.AssistantMessage;

@Service 
public class ChatService {
    
    private ChatClient chatClient;

    private List<Message> conversationHistory = new ArrayList<>();

    private static final String SYSTEM_PROMPT = """
            You are a customer support executive of a food delivery application called Tomato.

            Your job is to identify the customer's main problem and urgencey. Answer them related to their query in one line.

            Use professional language. If user has an issue, use words like I understand your frustration, I am really sorry for your trouble etc.

            Do not answer any other question which is not related to ordering food query, refund query,
            order tracking status query or company policy query.
            """;;

    public ChatService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public String chat(String message) {
        conversationHistory.add(new UserMessage(message));
        String response = chatClient
        .prompt()
        .system(SYSTEM_PROMPT)
        .messages(conversationHistory)
        .call()
        .content();
        conversationHistory.add(new AssistantMessage(response));
        return response;
    }
}
