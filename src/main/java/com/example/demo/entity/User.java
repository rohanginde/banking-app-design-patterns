package com.example.demo.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    
    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
    
    private int SocialSecurityNumber;

    public User() {}

    // Private constructor to ensure creation only via Builder
    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.accounts = builder.accounts;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public int getSocialSecurityNumber() {
		return SocialSecurityNumber;
	}

	public void setSocialSecurityNumber(int socialSecurityNumber) {
		SocialSecurityNumber = socialSecurityNumber;
	}

	// Static Builder class
    public static class Builder {
        private Long id;
        private String username;
        private String password;
        private List<Account> accounts;

        public Builder() {}

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withAccounts(List<Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}