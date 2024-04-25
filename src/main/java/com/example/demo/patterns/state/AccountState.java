package com.example.demo.patterns.state;

import com.example.demo.entity.Account;

public interface AccountState {
	 AccountStateType getStateType();
    void deposit(Account account, double amount);
    void withdraw(Account account, double amount);
}