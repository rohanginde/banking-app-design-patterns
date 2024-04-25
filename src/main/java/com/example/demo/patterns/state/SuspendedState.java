package com.example.demo.patterns.state;

import com.example.demo.entity.Account;

public class SuspendedState implements AccountState {

    @Override
    public void deposit(Account account, double amount) {
        // Deposits might be allowed even in a suspended state, but with logging or alerts.
        System.out.println("Depositing in a suspended account. Transaction will be reviewed.");
        account.setBalance(account.getBalance() + amount);
    }

    @Override
    public void withdraw(Account account, double amount) {
        // Withdrawals are typically disallowed for suspended accounts.
        System.out.println("Withdrawal attempt in suspended state denied.");
    }

    public void close(Account account) {
        // Transition to closed state if the account is suspended and needs to be closed.
        System.out.println("Closing the account from suspended state.");
        account.changeState(new ClosedState());
    }

	@Override
    public AccountStateType getStateType() {
        return AccountStateType.SUSPENDED;
    }
}
