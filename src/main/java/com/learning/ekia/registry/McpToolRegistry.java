package com.learning.ekia.registry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class McpToolRegistry {

    private final Map<String, ToolCallback> tools = new ConcurrentHashMap<>();

    public void register(ToolCallback toolCallback) {

        String toolName = toolCallback.getToolDefinition().name();

        log.info("Registering MCP tool: {}", toolName);

        tools.put(toolName, toolCallback);
    }

    public ToolCallback getTool(String toolName) {
        return tools.get(toolName);
    }

    public boolean contains(String toolName) {
        return tools.containsKey(toolName);
    }

    public int size() {
        return tools.size();
    }

    public Map<String, ToolCallback> getAllTools() {
        return Map.copyOf(tools);
    }

    public void printTools() {
        log.info("========== REGISTERED MCP TOOLS ==========");

        tools.values()
                .stream()
                .map(callback -> callback.getToolDefinition().name())
                .sorted()
                .forEach(tool -> log.info("Tool: {}", tool));

        log.info("Total tools: {}", tools.size());

        log.info("==========================================");
    }

}
