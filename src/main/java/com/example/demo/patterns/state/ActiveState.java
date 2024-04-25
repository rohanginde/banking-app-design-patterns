package com.example.demo.patterns.state;

import com.example.demo.entity.Account;

public class ActiveState implements AccountState {

	@Override
	public void deposit(Account account, double amount) {
		// TODO Auto-generated method stub
		
		account.setBalance( account.getBalance()+ amount);
	}

	@Override
	public void withdraw(Account account, double amount) {
		// TODO Auto-generated method stub
		System.out.println("Here in final withdraw");
		account.setBalance( account.getBalance()-amount);
	}
    

	@Override
    public AccountStateType getStateType() {
        return AccountStateType.ACTIVE;
    }
}