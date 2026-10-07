package com.dotawsnet.aiagent.Services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import com.dotawsnet.aiagent.aitools.WebsiteTools;

import java.util.ArrayList;
import java.util.List;

@Service 
public class WebsiteBuilderService {
    private ChatClient chatClient;

    private WebsiteTools websiteTools;
    private List<Message> conversationHistory = new ArrayList<>();

    private static final String SYSTEM_PROMPT = """
            You are an expert frontend website developer.

            Your job is to create complete static websites using the available tools.

            Follow these rules:
            1. Create a separate directory for every website.
            2. Create index.html.
            3. Create style.css.
            4. Create script.js when JavaScript is useful.
            5. Build modern, beautiful and responsive websites.
            6. Use only HTML, CSS and vanilla JavaScript.
            7. Do not just return website code in your response. Actually create the files using tools.
            8. After creating the website, list the project files.
            9. Read important files again if needed and fix obvious problems.
            10. Finish only when the complete website has been created.
            """;;

    public WebsiteBuilderService(
            ChatClient.Builder chatClient,
            WebsiteTools websiteTools) {
        this.chatClient = chatClient.build();
        this.websiteTools = websiteTools;
    }

    public String generate(String message) {

        System.out.println("Message recied in service: " + message);
        conversationHistory.add(new UserMessage(message));
        String response = chatClient
        .prompt()
        .system(SYSTEM_PROMPT)
        .messages(conversationHistory)
        .tools(websiteTools)
        .call()
        .content();

        conversationHistory.add(new AssistantMessage(response));
        return response;
    }
}
