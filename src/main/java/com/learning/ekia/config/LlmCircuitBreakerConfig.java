package com.learning.ekia.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LlmCircuitBreakerConfig {

    @Bean
    public CircuitBreaker llmCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("llm");

        circuitBreaker.getEventPublisher()
                .onStateTransition(event ->
                        log.warn("LLM Circuit Breaker transition: {}", event.getStateTransition())
                );

        return circuitBreaker;
    }
}