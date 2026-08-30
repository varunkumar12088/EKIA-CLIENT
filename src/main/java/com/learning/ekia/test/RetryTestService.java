package com.learning.ekia.test;

import com.learning.ekia.executor.LlmRetryExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class RetryTestService {

    private final LlmRetryExecutor llmRetryExecutor;

    private final AtomicInteger counter = new AtomicInteger(0);


    public RetryTestService(LlmRetryExecutor llmRetryExecutor) {

        this.llmRetryExecutor = llmRetryExecutor;
    }


    public String testRetry() {

        return llmRetryExecutor.execute(() -> {

            int attempt = counter.incrementAndGet();

            log.info("Test operation attempt: {}", attempt);

            if (attempt < 3) {

                throw new RuntimeException("Temporary simulated LLM failure");
            }

            return "LLM operation successful";
        });
    }
}