package com.insurance.quote.service;

import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.UserCreation;

public interface PolicyService {

	public void policyEntry(int accNum, float premium);

	public void updatePremium(int polNum, float premium);

	public AccountCreation getAcc(String userName);

}
