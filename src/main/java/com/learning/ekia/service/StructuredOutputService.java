package com.learning.ekia.service;

import com.learning.ekia.executor.LlmRetryExecutor;
import com.learning.ekia.model.BillingIntent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class StructuredOutputService {

    private static final String DOMAIN = "Telecom BSS Billing";

    private final ChatClient chatClient;

    private final PromptTemplateService promptTemplateService;

    private final LlmRetryExecutor llmRetryExecutor;


    public StructuredOutputService(ChatClient chatClient, PromptTemplateService promptTemplateService, LlmRetryExecutor llmRetryExecutor) {
        this.chatClient = chatClient;
        this.promptTemplateService = promptTemplateService;
        this.llmRetryExecutor = llmRetryExecutor;
    }


    public BillingIntent analyze(String question) {

        String systemPrompt = promptTemplateService.createBillingIntentClassificationPrompt(DOMAIN);

        String userPrompt = promptTemplateService.createUserPrompt(DOMAIN, question);

        return llmRetryExecutor.execute(() -> chatClient
                .prompt()
                .system(systemPrompt)
                .user(userPrompt)
                .call()
                .entity(BillingIntent.class)
        );
    }
}