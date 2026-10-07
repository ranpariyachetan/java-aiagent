package com.dotawsnet.aiagent;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dotawsnet.aiagent.Services.WebsiteBuilderService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController 
@RequestMapping("/website")
public class WebsiteBuilderController {
    
    private WebsiteBuilderService websiteService;

    public  WebsiteBuilderController(WebsiteBuilderService websiteService) {
        this.websiteService = websiteService;
    }

    @PostMapping("/generate")
    public String generateWebsite(@RequestBody String message) {
        System.out.println("Message recied in controller: " + message);
        return websiteService.generate(message);
    }
}
