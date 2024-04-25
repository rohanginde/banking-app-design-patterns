package com.example.demo.patterns.adapter;

public class ExternalCreditRatingService {
    public int fetchCreditRating(int ssn) {
        // Simulate fetching credit rating
        return (int) (Math.random() * 300) + 550;  // Random credit score between 550 and 850
    }
}
