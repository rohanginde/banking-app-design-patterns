package com.example.demo.patterns.bridge;

public class BankTransferPayment implements Payment {
    private PaymentProcessor processor;
	private double amount;

    public BankTransferPayment(PaymentProcessor processor, double amount) {
        this.processor = processor;
        this.amount = amount;
    }
    @Override
    public void pay(double amount) {
        processor.processPayment(amount, "Bank Transfer");
    }
}

