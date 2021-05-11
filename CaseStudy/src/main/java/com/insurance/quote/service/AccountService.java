package com.insurance.quote.service;

import java.util.List;

import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.UserCreation;

public interface AccountService {
	//public AccountCreation getInput(UserCreation user);
	public List<String> getBusinessSegment();
	public String accountCreation(AccountCreation ac);
	public String getInput();
	public void closeResources();
	public AccountCreation getInput(UserCreation user, String roleCode);
}
