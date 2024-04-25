package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.services.CreditScoreService;

@RestController
@RequestMapping("/api/credit-scores")
public class CreditScoreController {
    private final CreditScoreService creditScoreService;

    public CreditScoreController(CreditScoreService creditScoreService) {
        this.creditScoreService = creditScoreService;
    }

    @CrossOrigin
    @GetMapping("/{userId}")
    public ResponseEntity<Integer> getUserCreditScore(@PathVariable Long userId) {
        // This is a simplification. Normally you'd fetch the user object from your database.
     
        int score = creditScoreService.getCreditScoreForUser(userId);
        return ResponseEntity.ok(score);
    }
}
