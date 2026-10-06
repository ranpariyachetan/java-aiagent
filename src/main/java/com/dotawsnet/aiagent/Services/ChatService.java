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

    public ChatService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public String chat(String message) {
        conversationHistory.add(new UserMessage(message));
        String response = chatClient.prompt().messages(conversationHistory).call().content();
        conversationHistory.add(new AssistantMessage(response));
        return response;
    }
}
