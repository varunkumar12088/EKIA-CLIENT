package com.learning.ekia.model;

public record BillingIntent(

        BillingIntentType intent,

        String category,

        String customerId
) {
}
