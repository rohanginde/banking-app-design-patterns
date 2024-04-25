package com.example.demo.patterns.factory;

import com.example.demo.entity.Account;
import com.example.demo.patterns.command.DepositCommand;
import com.example.demo.patterns.command.TransactionCommand;
import com.example.demo.patterns.command.WithdrawCommand;

public class TransactionCommandFactory {
    public static TransactionCommand getCommand(String type, Account account, double amount) {
        switch (type) {
            case "deposit":
                return new DepositCommand(account, amount);
            case "withdraw":
                return new WithdrawCommand(account, amount);
            default:
                throw new IllegalArgumentException("Invalid transaction type");
        }
    }
}
