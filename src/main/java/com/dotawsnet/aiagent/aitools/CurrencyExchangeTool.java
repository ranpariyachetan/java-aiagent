package com.dotawsnet.aiagent.aitools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component 
public class CurrencyExchangeTool {
    RestClient restClient;

    public CurrencyExchangeTool(RestClient.Builder builder){
        this.restClient = builder.baseUrl("https://api.frankfurter.dev").build();
    }

    @Tool (description = "Gets the latest exchange rate between to currencies.")
    public String getExchangeRate(
            @ToolParam (description = "Source currency code, for example USD.")
            String from,
            @ToolParam (description = "Target currency code, for example GBP.")
            String to
    ) {

        System.out.println("Currency Exchange tool called to convert " + from + " to " + to);

        return restClient.get()
                .uri("/v2/rate/{from}/{to}", from, to)
                .retrieve()
                .body(String.class);
    }
}
