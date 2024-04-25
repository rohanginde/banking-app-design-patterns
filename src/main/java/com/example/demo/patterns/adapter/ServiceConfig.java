package com.example.demo.patterns.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public CreditScoreProvider creditScoreProvider() {
        ExternalCreditRatingService externalService = new ExternalCreditRatingService();
        return new CreditScoreServiceAdapter(externalService);
    }
}
