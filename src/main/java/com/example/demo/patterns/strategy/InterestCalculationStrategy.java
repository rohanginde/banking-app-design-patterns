package com.example.demo.patterns.strategy;

public interface InterestCalculationStrategy {
    double calculateInterest(double balance);
    double getAnnualInterestRate();
}

