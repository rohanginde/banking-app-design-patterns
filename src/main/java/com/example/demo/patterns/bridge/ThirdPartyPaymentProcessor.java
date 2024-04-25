package com.example.demo.patterns.bridge;

public class ThirdPartyPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount, String method) {
        System.out.println("Processing " + amount + " via " + method + " through a third-party.");
    }
}