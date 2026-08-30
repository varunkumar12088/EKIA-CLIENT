package com.learning.ekia.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Slf4j
@Component
public class LlmRetryExecutor {

    private final RetryTemplate llmRetryTemplate;

    public LlmRetryExecutor(RetryTemplate llmRetryTemplate) {
        this.llmRetryTemplate = llmRetryTemplate;
    }


    public <T> T execute(Supplier<T> llmOperation) {
        return llmRetryTemplate.execute(context -> executeLlmCall(context, llmOperation), context -> handleFailure(context));
    }


    private <T> T executeLlmCall(RetryContext context, Supplier<T> llmOperation) {

        int attemptNumber = context.getRetryCount() + 1;

        log.info("Executing LLM call. Attempt: {}", attemptNumber);

        return llmOperation.get();
    }


    private <T> T handleFailure(RetryContext context) {

        log.error("LLM call failed after {} attempts", context.getRetryCount());

        throw new IllegalStateException("LLM request failed after retry attempts", context.getLastThrowable());
    }
}