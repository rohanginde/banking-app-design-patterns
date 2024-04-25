package com.example.demo.patterns.adapter;


import com.example.demo.entity.User;

public interface CreditScoreProvider {
    int getCreditScore(User user);
}
