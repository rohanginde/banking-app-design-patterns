package com.example.demo.dto;

public class UserDTO {
    @Override
	public String toString() {
		return "UserDTO [username=" + username + ", password=" + password + ", creditScore=" + creditScore
				+ ", SocialSecurityNumber=" + ssn + "]";
	}

	private String username;
    private String password;
    private Long creditScore;
    private String ssn;

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Long getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Long creditScore) {
		this.creditScore = creditScore;
	}

	// Constructors, getters, and setters
    public UserDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}