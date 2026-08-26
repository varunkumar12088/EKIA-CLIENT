package com.learning.ekia.service;

import com.learning.ekia.registry.McpToolRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class McpToolDiscoveryService {

    private final SyncMcpToolCallbackProvider toolCallbackProvider;
    private final McpToolRegistry toolRegistry;


    public void discoverTools() {

        log.info("Starting MCP tool discovery...");

        ToolCallback[] toolCallbacks = toolCallbackProvider.getToolCallbacks();

        log.info("MCP server returned {} tools", toolCallbacks.length);

        for (ToolCallback toolCallback : toolCallbacks) {

            String toolName = toolCallback.getToolDefinition().name();

            String description = toolCallback.getToolDefinition().description();

            log.info("Discovered MCP tool: {}", toolName);

            log.info("Description: {}", description);

            toolRegistry.register(toolCallback);
        }

        toolRegistry.printTools();
    }
}
