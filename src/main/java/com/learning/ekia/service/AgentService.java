package com.learning.ekia.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AgentService {

    private static final String SYSTEM_PROMPT = """
            You are EKIA, an Enterprise Knowledge Intelligence Agent.

            You help users with Telecom BSS and Billing information.

            When the user asks for information that is available
            through a provided tool, use the appropriate tool.

            Do not invent invoice, payment, usage, or billing account data.

            Use the tool result to generate the final answer.

            If the required information is not available,
            clearly say so.
            """;

    private final ChatClient agentChatClient;
    private final SyncMcpToolCallbackProvider mcpToolCallbackProvider;

    public AgentService(
            ChatClient agentChatClient,
            SyncMcpToolCallbackProvider mcpToolCallbackProvider) {

        this.agentChatClient = agentChatClient;
        this.mcpToolCallbackProvider = mcpToolCallbackProvider;
    }

    public String ask(String question) {

        log.info("Agent received question: {}", question);

        String response = agentChatClient
                .prompt()
                .system(SYSTEM_PROMPT)
                .user(question)
                .tools(mcpToolCallbackProvider)
                .call()
                .content();

        log.info("Agent final response: {}", response);

        return response;
    }
}
