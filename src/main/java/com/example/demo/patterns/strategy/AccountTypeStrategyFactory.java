package com.example.demo.patterns.strategy;

public class AccountTypeStrategyFactory {
    public static InterestCalculationStrategy getStrategy(AccountType accountType) {
        switch (accountType) {
            case SAVINGS:
                return new CompoundInterestStrategy(1.5, 4);
            case CHECKING:
                return new SimpleInterestStrategy(0.5);
            default:
                throw new IllegalArgumentException("Unknown Account Type");  
        }
    }
}
