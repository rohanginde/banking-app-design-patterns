package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.patterns.bridge.BankTransferPayment;
import com.example.demo.patterns.bridge.CreditCardPayment;
import com.example.demo.patterns.bridge.DirectPaymentProcessor;
import com.example.demo.patterns.bridge.Payment;
import com.example.demo.patterns.bridge.PaymentProcessor;
import com.example.demo.patterns.bridge.ThirdPartyPaymentProcessor;

@Service
public class PaymentService {
    public String processPayment(String type, double amount) {
    	  PaymentProcessor processor;
          Payment payment;

          if ("direct".equals(type)) {
              processor = new DirectPaymentProcessor();
          } else {
              processor = new ThirdPartyPaymentProcessor();
          }

          if (amount > 1000) {  // Assume large transactions use bank transfers
              payment = new BankTransferPayment(processor, amount);
          } else {
              payment = new CreditCardPayment(processor, amount);
          }
          payment.pay(amount);
          return "Success";
       
    }
}