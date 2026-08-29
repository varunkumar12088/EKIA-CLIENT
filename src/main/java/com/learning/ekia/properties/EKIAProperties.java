package com.learning.ekia.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Validated
@ConfigurationProperties(prefix = "app")
public record EKIAProperties(

        @NotBlank
        String environment,

        @NotNull
        @Valid
        Ai ai,

        @NotNull
        @Valid
        Retry retry,

        @NotNull
        @Valid
        Timeout timeout

) {

    public record Ai(

            @NotNull
            @Valid
            OpenRouter openrouter,

            @NotNull
            @Valid
            Gemini gemini,

            @NotNull
            @Valid
            Embedding embedding

    ) {
    }

    public record OpenRouter(

            @NotBlank
            String model,

            @Min(0)
            @Max(2)
            Double temperature,

            @Min(1)
            Integer maxTokens

    ) {
    }

    public record Gemini(

            @NotBlank
            String model,

            @Min(0)
            @Max(2)
            Double temperature,

            @Min(1)
            Integer maxTokens

    ) {
    }

    public record Embedding(

            String provider,

            String model,

            @Min(1)
            Integer batchSize

    ) {
    }

    public record Retry(

            @Min(1)
            Integer maxAttempts,

            @NotNull
            Duration initialBackoff,

            @NotNull
            Duration maxBackoff

    ) {
    }

    public record Timeout(

            @NotNull
            Duration llm,

            @NotNull
            Duration mcp

    ) {
    }
}
