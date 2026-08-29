package com.learning.ekia.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.learning.ekia.properties.EKIAProperties;
import com.learning.ekia.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AgentTestRunner implements CommandLineRunner {

    private final EKIAProperties ekiaProperties;

    public AgentTestRunner(EKIAProperties ekiaProperties) {
        this.ekiaProperties = ekiaProperties;
    }

    @Override
    public void run(String... args) {

        log.info("========================================");
        log.info("   TELECOM BSS AGENT TOOL TEST STARTED");
        log.info("========================================");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String prop = objectMapper
                    .registerModule(new JavaTimeModule())
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(ekiaProperties);
            log.info("EKIA PROPERTIES: {}", prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("========================================");
        log.info("   TELECOM BSS AGENT TOOL TEST FINISHED");
        log.info("========================================");
    }

}