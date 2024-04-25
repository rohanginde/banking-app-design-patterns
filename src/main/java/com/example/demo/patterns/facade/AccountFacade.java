package com.example.demo.patterns.facade;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Account;
import com.example.demo.patterns.command.DepositCommand;
import com.example.demo.patterns.command.WithdrawCommand;

@Component
public class AccountFacade {
    public void transfer(Account from, Account to, double amount) {
    	
    	System.out.println("here in facade");
        new WithdrawCommand(from, amount).execute();
        new DepositCommand(to, amount).execute();
    }
}
