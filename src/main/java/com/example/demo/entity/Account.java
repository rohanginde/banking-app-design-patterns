package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.patterns.observer.AccountObserver;
import com.example.demo.patterns.state.AccountState;
import com.example.demo.patterns.state.AccountStateType;
import com.example.demo.patterns.state.ActiveState;
import com.example.demo.patterns.state.StateFactory;
import com.example.demo.patterns.strategy.AccountType;
import com.example.demo.patterns.strategy.AccountTypeStrategyFactory;
import com.example.demo.patterns.strategy.InterestCalculationStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double balance;
    
    @Enumerated(EnumType.STRING) // Use EnumType.ORDINAL if you prefer storing the index
    private AccountStateType stateType;

    
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    
    @JsonIgnore
    @Transient
    private AccountState state;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    
    
    
    @Transient
    private InterestCalculationStrategy interestStrategy;
    
    
    public double calculateInterest() {
        return interestStrategy.calculateInterest(balance);
    }
    
    public double getAnnualInterestRate() {
        return interestStrategy.getAnnualInterestRate();
    }


    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


    public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountState getState() {
		return state;
	}

    public void setState(AccountState state) {
        this.state = state;
        this.stateType = state.getStateType();
    }


	public List<AccountObserver> getObservers() {
		return observers;
	}

	public void setObservers(List<AccountObserver> observers) {
		this.observers = observers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", stateType=" + stateType + ", accountType="
				+ accountType + ", state=" + state + ", user=" + user + ", interestStrategy=" + interestStrategy
				+ ", observers=" + observers + "]";
	}
	@JsonIgnore
	@Transient
    private List<AccountObserver> observers = new ArrayList<>();

    public void deposit(double amount) {
        state.deposit(this, amount);
        notifyObservers();
    }

   

	public void withdraw(double amount) {
    	System.out.println("here in account withdraw");
        state.withdraw(this, amount);
        notifyObservers();
    }

    public void addObserver(AccountObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(AccountObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
    	
    	
    	System.out.println(observers.size());
        for (AccountObserver observer : observers) {
            observer.update(this);
        }
    }

    public void changeState(AccountState newState) {
        this.state = newState;
    }
    
    public Account() {
    	
    }
    
    
    @PostLoad
    private void init() {
        this.state = StateFactory.getState(this.stateType);
        this.interestStrategy = AccountTypeStrategyFactory.getStrategy(this.accountType);
    }
    

    
	public AccountStateType getStateType() {
		return stateType;
	}

	public AccountType getAccountType() {
		return accountType;
	}


	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
		this.interestStrategy = AccountTypeStrategyFactory.getStrategy(this.accountType);
	}


	public void setStateType(AccountStateType stateType) {
		this.stateType = stateType;
	}

	private Account(Builder builder) {
        this.id = builder.id;
        this.balance = builder.balance;
        this.state = builder.state != null ? builder.state : new ActiveState(); // Default to ActiveState if not set
        this.stateType = builder.stateType != null ? builder.stateType : AccountStateType.ACTIVE; // Default to ACTIVE if not set
        this.user = builder.user;
        this.observers = builder.observers != null ? builder.observers : new ArrayList<>();
        this.accountType = builder.stateType != null ? builder.accountType: AccountType.CHECKING;
    }

	public static class Builder {
        private Long id;
        private double balance;
        private AccountState state;
        private AccountStateType stateType;
        private User user;
        private List<AccountObserver> observers;
        private AccountType accountType;
        private InterestCalculationStrategy interestStrategy;

        public Builder() {
            this.observers = new ArrayList<>();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder withState(AccountState state) {
            this.state = state;
            if (state != null) {
                this.stateType = state.getStateType();
            }
            return this;
        }

        public Builder withStateType(AccountStateType stateType) {
            this.stateType = stateType;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withObservers(List<AccountObserver> observers) {
            this.observers = observers;
            return this;
        }

        public Builder withAccountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder withInterestStrategy(InterestCalculationStrategy interestStrategy) {
            this.interestStrategy = interestStrategy;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.id = this.id;
            account.balance = this.balance;
            account.state = this.state != null ? this.state : new ActiveState(); // Ensure state is initialized
            account.stateType = this.stateType != null ? this.stateType : AccountStateType.ACTIVE; // Default to ACTIVE if not set
            account.user = this.user;
            account.observers = this.observers.size() > 0 ? this.observers : new ArrayList<>(); // Ensure observers are initialized
            account.accountType = this.accountType;
           ; // Ensure interestStrategy is initialized
            return account;
        }
    }

}
