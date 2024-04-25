package com.example.demo.services;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.patterns.command.TransactionCommand;
import com.example.demo.patterns.decorator.TransactionCommandWithLogging;
import com.example.demo.patterns.facade.AccountFacade;
import com.example.demo.patterns.factory.TransactionCommandFactory;
import com.example.demo.patterns.observer.AccountObserver;
import com.example.demo.patterns.observer.EmailNotificationObserver;
import com.example.demo.patterns.state.AccountStateType;
import com.example.demo.patterns.state.ActiveState;
import com.example.demo.patterns.state.ClosedState;
import com.example.demo.patterns.state.StateFactory;
import com.example.demo.patterns.strategy.AccountType;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AccountFacade accountFacade;


    @Transactional
    public Account createAccountForUser(Long userId, AccountType accountType) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Account account = new  Account.Builder().withUser(user).withBalance(200).withState(new ActiveState()).withAccountType(accountType).build();// Set initial balance
        
      account.addObserver(new EmailNotificationObserver());
        
        account.setAccountType(accountType);
        return accountRepository.save(account);
    }
    // Handle deposits and withdrawals generically
    @Transactional
    public void performTransaction(String type, Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));

        
        account.addObserver(new EmailNotificationObserver());
        // Use factory to create the appropriate command
        TransactionCommand command = TransactionCommandFactory.getCommand(type, account, amount);

        // Decorate the command with logging functionality
        command = new TransactionCommandWithLogging(command);

        // Execute the decorated command
        command.execute();

   
        
        // Persist changes to the account
        accountRepository.save(account);
    }

    
    
    
    public void closeAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));
        account.setState(new ClosedState());
        accountRepository.save(account);
    }

    
    public void activateAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));
        account.setState(new ActiveState());
        accountRepository.save(account);
    }
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException("Cannot transfer to the same account.");
        }

        Account fromAccount = accountRepository.findById(fromAccountId)
            .orElseThrow(() -> new IllegalArgumentException("Source account not found"));
        Account toAccount = accountRepository.findById(toAccountId)
            .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds in source account");
        }

        if (fromAccount.getStateType() != AccountStateType.ACTIVE) {
            throw new IllegalArgumentException("Transfer Unsuccessful. Account is Closed");
        }
        if (toAccount.getStateType() != AccountStateType.ACTIVE) {
            throw new IllegalArgumentException("Transfer Unsuccessful. Account is Closed");
        }
        // Using the AccountFacade to handle the transaction
        accountFacade.transfer(fromAccount, toAccount, amount);

        // Save the updated accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
    
    
    
    public Map<String,Object> getInterest(Long accountId){
    	
    	Account account = accountRepository.findById(accountId)
    	        .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    	    double interest = account.calculateInterest();
    	    double annualRate = account.getAnnualInterestRate();
    	    Map<String, Object> response = new HashMap<>();
    	    response.put("interest", interest);
    	    response.put("annualRate", annualRate);
    	
    	return response;
    }

}