package com.example.demo.patterns.strategy;

public class SimpleInterestStrategy implements InterestCalculationStrategy {
    private double annualInterestRate; // Annual interest rate in percentage

    public SimpleInterestStrategy(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    public double calculateInterest(double accountBalance) {
        // Simple interest calculation for one year
        return accountBalance * (annualInterestRate / 100);
    }

    @Override
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }
}
