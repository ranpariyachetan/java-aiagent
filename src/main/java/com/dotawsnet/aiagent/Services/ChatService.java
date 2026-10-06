package com.dotawsnet.aiagent.Services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service 
public class ChatService {
    
    private ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    public String chat(String message) {

        String prompt = "Please respond to the following message: " + message;

        return chatClient.prompt().user(prompt).call().content();
    }
}
