package com.learning.ekia.test;

import com.learning.ekia.service.McpToolDiscoveryRunner;
import com.learning.ekia.service.McpToolExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class McpToolTestRunner implements CommandLineRunner {

    private final McpToolExecutionService executionService;

    public McpToolTestRunner(McpToolExecutionService executionService) {
        this.executionService = executionService;
    }

    @Override
    public void run(String... args) {

        log.info("========== MCP TOOL TEST ==========");

        String input = """
                {
                    "request":
                                {
                                    "invoiceId": "INV-1001"
                                }
                }
                """;

        String result = executionService.execute("get_invoice", input);

        log.info("Tool Result: {}", result);

        log.info("===================================");
    }
}
