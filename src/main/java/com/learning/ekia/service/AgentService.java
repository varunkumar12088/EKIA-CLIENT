package com.learning.ekia.service;

import com.learning.ekia.constants.SharedConstant;
import com.learning.ekia.executor.LlmRetryExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AgentService {

    private final ChatClient agentChatClient;

    private final SyncMcpToolCallbackProvider mcpToolCallbackProvider;

    private final PromptTemplateService promptTemplateService;

    private final LlmRetryExecutor llmRetryExecutor;

    private final LlmResilienceService llmResilienceService;


    public AgentService(
            ChatClient agentChatClient,
            SyncMcpToolCallbackProvider mcpToolCallbackProvider,
            PromptTemplateService promptTemplateService,
            LlmRetryExecutor llmRetryExecutor,
            LlmResilienceService llmResilienceService) {

        this.agentChatClient = agentChatClient;
        this.mcpToolCallbackProvider = mcpToolCallbackProvider;
        this.promptTemplateService = promptTemplateService;
        this.llmRetryExecutor = llmRetryExecutor;
        this.llmResilienceService = llmResilienceService;
    }


    public String ask(String question) {

        String systemPrompt = promptTemplateService.createSystemPrompt(SharedConstant.DOMAIN);

        String userPrompt = promptTemplateService.createUserPrompt(SharedConstant.DOMAIN, question);

        try {

            return llmResilienceService.execute(
                    () -> llmRetryExecutor.execute(
                            () -> agentChatClient
                                    .prompt()
                                    .system(systemPrompt)
                                    .user(userPrompt)
                                    .tools(mcpToolCallbackProvider)
                                    .call()
                                    .content()
                    )
            );

        } catch (Exception exception) {

            return fallback(question, exception);
        }
    }


    private String fallback(String question, Exception exception) {

        log.error("LLM request failed. Using fallback. Question: {}", question, exception);

        return """
                The AI service is temporarily unavailable.
                Please try again shortly.
                """;
    }
}