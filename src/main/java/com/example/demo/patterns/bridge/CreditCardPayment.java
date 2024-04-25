package com.example.demo.patterns.bridge;

public class CreditCardPayment implements Payment {
    private PaymentProcessor processor;
    private double amount;

    public CreditCardPayment(PaymentProcessor processor, double amount) {
        this.processor = processor;
        this.amount = amount;
    }

    @Override
    public void pay(double amount) {
        processor.processPayment(amount, "Credit Card");
    }
}