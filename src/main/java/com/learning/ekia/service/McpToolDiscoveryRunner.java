package com.learning.ekia.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class McpToolDiscoveryRunner implements CommandLineRunner {

    private final McpToolDiscoveryService discoveryService;

    public McpToolDiscoveryRunner(McpToolDiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @Override
    public void run(String... args) {
        log.info("======================================");
        log.info("      MCP TOOL DISCOVERY STARTED      ");
        log.info("======================================");

        discoveryService.discoverTools();

        log.info("======================================");
        log.info("      MCP TOOL DISCOVERY FINISHED     ");
        log.info("======================================");
    }
}
