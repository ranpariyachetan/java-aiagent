package com.dotawsnet.aiagent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dotawsnet.aiagent.Services.ChatService;

@RestController 
@RequestMapping("/api")
public class ChatController {
    
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping ("/chat")
    public String chat(@RequestBody String message) {
        return chatService.chat(message);
    }

    @GetMapping ("/health")
    public String healthCheck() {
        return "ChatController is healthy!";
    }
}
