package com.learning.ekia.service;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Service
public class PromptTemplateService {

    private PromptTemplate systemPromptTemplate;
    private PromptTemplate userPromptTemplate;
    private PromptTemplate systemBillingIntentClassificationPT;


    @PostMapping
    public void init(){
        this.loadPromptTemplate();
    }

    public String createSystemPrompt(String domain) {
        return this.systemPromptTemplate.createMessage(Map.of("domain", domain)).getText();
    }

    public String createUserPrompt(String domain, String question) {
        return this.userPromptTemplate.createMessage(Map.of("domain", domain, "question", question)).getText();
    }


    public String createBillingIntentClassificationPrompt(String domain) {
        return this.systemBillingIntentClassificationPT.createMessage(Map.of("domain", domain)).getText();
    }


    private void loadPromptTemplate() {
        this.systemPromptTemplate = new PromptTemplate(new ClassPathResource("prompts/system/ekia-system.st"));
        this.userPromptTemplate = new PromptTemplate(new ClassPathResource("prompts/user/telecom-question.st"));
        this.systemBillingIntentClassificationPT = new PromptTemplate(new ClassPathResource("prompts/system/billing-intent-classification.st"));
    }
}
