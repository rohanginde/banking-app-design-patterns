package com.example.demo.patterns.command;

import com.example.demo.entity.Account;

public class DepositCommand implements TransactionCommand {
    private Account account;
    private double amount;

    public DepositCommand(Account to, double amount) {
		// TODO Auto-generated constructor stub
        this.account = to;
        this.amount = amount;
	}

	@Override
    public void execute() {
        account.deposit(amount);
    }
}