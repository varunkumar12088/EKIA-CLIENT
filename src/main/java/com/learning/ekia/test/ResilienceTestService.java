package com.learning.ekia.test;

import com.learning.ekia.service.LlmResilienceService;
import org.springframework.stereotype.Service;

@Service
public class ResilienceTestService {

    private final LlmResilienceService llmResilienceService;

    public ResilienceTestService(LlmResilienceService llmResilienceService) {

        this.llmResilienceService = llmResilienceService;
    }

    public String testFailure() {

        return llmResilienceService.execute(
                () -> {
                    throw new RuntimeException("Simulated LLM failure");
                }
        );
    }
}