package com.learning.ekia.config;

import com.learning.ekia.properties.EKIAProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class LlmRetryConfig {

    @Bean
    public RetryTemplate llmRetryTemplate(EKIAProperties properties) {

        RetryTemplate retryTemplate = new RetryTemplate();

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();

        retryPolicy.setMaxAttempts(properties.retry().maxAttempts());

        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();

        backOffPolicy.setInitialInterval(properties.retry().initialInterval());

        backOffPolicy.setMultiplier(properties.retry().multiplier());

        backOffPolicy.setMaxInterval(properties.retry().maxInterval());

        retryTemplate.setRetryPolicy(retryPolicy);

        retryTemplate.setBackOffPolicy(backOffPolicy);

        return retryTemplate;
    }
}