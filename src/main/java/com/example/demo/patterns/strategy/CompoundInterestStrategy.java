package com.example.demo.patterns.strategy;

public class CompoundInterestStrategy implements InterestCalculationStrategy {
    private double annualInterestRate; // Annual interest rate in percentage
    private int compoundingFrequency; // Times per year interest is compounded

    public CompoundInterestStrategy(double annualInterestRate, int compoundingFrequency) {
        this.annualInterestRate = annualInterestRate;
        this.compoundingFrequency = compoundingFrequency;
    }

    @Override
    public double calculateInterest(double accountBalance) {
        // Compound interest calculation for one year
        return accountBalance * (Math.pow(1 + (annualInterestRate / 100) / compoundingFrequency, compoundingFrequency) - 1);
    }

    @Override
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }
}
