package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @CrossOrigin
    @PostMapping("/pay")
    public ResponseEntity<String> makePayment(@RequestParam String type, @RequestParam double amount) {
      
      String status=  paymentService.processPayment(type, amount);
      if(status.equals("Success")) {
    	  return ResponseEntity.ok("Payment of $" + amount + " processed via " + type);
      }else {

          return ResponseEntity.ok("Payment of $" + amount + " processing failed via " + type);
      }
    }
}
