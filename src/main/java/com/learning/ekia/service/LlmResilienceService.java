package com.learning.ekia.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Slf4j
@Service
public class LlmResilienceService {

    private final CircuitBreaker llmCircuitBreaker;

    public LlmResilienceService(CircuitBreaker llmCircuitBreaker) {
        this.llmCircuitBreaker = llmCircuitBreaker;
    }

    public <T> T execute(Supplier<T> operation) {

        Supplier<T> decoratedSupplier = CircuitBreaker.decorateSupplier(llmCircuitBreaker, operation);

        return decoratedSupplier.get();
    }
}