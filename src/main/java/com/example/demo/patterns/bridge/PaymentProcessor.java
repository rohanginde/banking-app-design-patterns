package com.example.demo.patterns.bridge;

public interface PaymentProcessor {
    void processPayment(double amount, String method);
}