package com.example.demo.patterns.adapter;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public class CreditScoreServiceAdapter implements CreditScoreProvider {
    private ExternalCreditRatingService creditRatingService;

    public CreditScoreServiceAdapter(ExternalCreditRatingService creditRatingService) {
        this.creditRatingService = creditRatingService;
    }

    



	@Override
	public int getCreditScore(User user) {
		// TODO Auto-generated method stub
		  int ssn = user.getSocialSecurityNumber();
	        return creditRatingService.fetchCreditRating(ssn);
		
	}
}
