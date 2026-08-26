package com.learning.ekia.service;

import com.learning.ekia.registry.McpToolRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class McpToolExecutionService {

    private final McpToolRegistry toolRegistry;

    public String execute(String toolName, String input) {

        log.info("Executing MCP tool: {}", toolName);

        ToolCallback toolCallback = toolRegistry.getTool(toolName);

        if (toolCallback == null) {
            throw new IllegalArgumentException("MCP tool not found: " + toolName);
        }

        log.info("Tool input: {}", input);

        String result = toolCallback.call(input);

        log.info("Tool execution completed: {}", toolName);

        return result;
    }
}
