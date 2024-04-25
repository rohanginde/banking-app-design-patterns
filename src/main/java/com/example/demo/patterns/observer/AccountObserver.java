package com.example.demo.patterns.observer;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Account;
public interface AccountObserver {
    void update(Account account);
}