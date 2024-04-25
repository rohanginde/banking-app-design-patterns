package com.example.demo.patterns.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Account;

public class WithdrawCommand implements TransactionCommand {
  
	private Account account;
    private double amount;

 

    public WithdrawCommand(Account from, double amount) {
		// TODO Auto-generated constructor stub
    	this.account = from;
        this.amount = amount;
	}

	@Override
    public void execute() {
		System.out.println("here in execute of withdraw");
        account.withdraw(amount);
    }
}