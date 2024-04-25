package com.example.demo.patterns.state;

public class StateFactory {
    public static AccountState getState(AccountStateType type) {
        switch (type) {
            case ACTIVE:
                return new ActiveState();
            case SUSPENDED:
                return new SuspendedState();
            case CLOSED:
                return new ClosedState();
            default:
                throw new IllegalArgumentException("Unknown StateType: " + type);
        }
    }
}
