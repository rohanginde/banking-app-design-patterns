package com.example.demo.patterns.observer;

import com.example.demo.entity.Account;

public class EmailNotificationObserver implements AccountObserver {

    @Override
    public void update(Account account) {
    	
    	
        sendEmailNotification(account);
    }

    private void sendEmailNotification(Account account) {
        // Logic to send email notifications
        System.out.println("Email sent to account owner about the change in account status or balance. Account ID: " + account.getId());
    }
}