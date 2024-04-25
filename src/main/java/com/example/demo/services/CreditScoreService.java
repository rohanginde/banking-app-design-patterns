package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.patterns.adapter.CreditScoreProvider;
import com.example.demo.repository.UserRepository;

@Service
public class CreditScoreService {
    private final CreditScoreProvider creditScoreProvider;
    @Autowired
	UserRepository userRepository;

    // Constructor injection of the credit score provider (adapter)
    public CreditScoreService(CreditScoreProvider creditScoreProvider) {
        this.creditScoreProvider = creditScoreProvider;
    }

    public int getCreditScoreForUser(Long userId) {
    	
    	User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

    	
    	
        return creditScoreProvider.getCreditScore(user);
    }
}
