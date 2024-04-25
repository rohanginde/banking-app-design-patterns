package com.example.demo.controllers;

import com.example.demo.entity.Account;
import com.example.demo.patterns.strategy.AccountType;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/accounts")
public class AccountController {
	  @Autowired
	    private AccountService accountService;

	   @CrossOrigin(origins = "http://localhost:3000")
	   @PostMapping("/create")
	    public ResponseEntity<Account> createAccount(@RequestParam Long userId, @RequestParam String type) {
		   System.out.println("here we are");
	        try {
	        	
	        	
	        	System.out.print("Received account type: {}"+type);
	            AccountType accountType = AccountType.valueOf(type.toUpperCase()); // Convert manually
	          

	           Account account = accountService.createAccountForUser(userId,accountType);
	            
	           // System.out.print(account.toString());
	            return ResponseEntity.ok(account);
	            
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body(null);
	        }
	    }
	   

	    @PostMapping("/{accountId}/transaction")
	    public ResponseEntity<String> performTransaction(@PathVariable Long accountId, @RequestParam String type, @RequestParam double amount) {
	        try {
	            accountService.performTransaction(type, accountId, amount);
	            return ResponseEntity.ok("Transaction completed successfully");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Failed to perform transaction: " + e.getMessage());
	        }
	    }

	    @PostMapping("/{accountId}/close")
	    public ResponseEntity<String> closeAccount(@PathVariable Long accountId) {
	        try {
	        	 accountService.closeAccount(accountId);
	            return ResponseEntity.ok("Account closed successfully");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Failed to close account: " + e.getMessage());
	        }
	    }

	    @PostMapping("/{accountId}/activate")
	    public ResponseEntity<String> activateAccount(@PathVariable Long accountId) {
	        try {
	   
	            accountService.activateAccount(accountId);
	            return ResponseEntity.ok("Account Activated successfully");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Failed to activate account: " + e.getMessage());
	        }
	    }
	    
	    @GetMapping("/{accountId}")
	    public ResponseEntity<Account> getAccountById(@PathVariable String accountId) {
	        try {
	        	
	        	
	            Account account = accountService.getAccountById(Long.valueOf(accountId));
	            return ResponseEntity.ok(account);
	        } catch (Exception e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	    
	    @CrossOrigin(origins = "http://localhost:3000")
	 // In AccountController.java
	    @PostMapping("/transfer")
	    public ResponseEntity<String> transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam double amount) {
	        try {
	            accountService.transfer(fromAccountId, toAccountId, amount);
	            return ResponseEntity.ok("Transfer successful");
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Transfer failed: " + e.getMessage());
	        }
	        
	        
	        
	        
	    }

	    
	    
	    @GetMapping("/interest/{accountId}")
	    public ResponseEntity<?> getInterest(@PathVariable Long accountId) {
	        return ResponseEntity.ok(accountService.getInterest(accountId));
	    }

}
