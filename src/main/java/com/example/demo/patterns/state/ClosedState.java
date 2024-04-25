package com.example.demo.patterns.state;

import com.example.demo.entity.Account;

public class ClosedState implements AccountState {

    @Override
    public void deposit(Account account, double amount) {
        // No deposits should be allowed for a closed account.
        System.out.println("No deposits allowed. This account is closed.");
    }

    @Override
    public void withdraw(Account account, double amount) {
        // No withdrawals should be allowed for a closed account.
        System.out.println("No withdrawals allowed. This account is closed.");
    }

    public void close(Account account) {
        // The account is already closed.
        System.out.println("This account is already closed.");
    }

	@Override
    public AccountStateType getStateType() {
        return AccountStateType.CLOSED;
    }
}