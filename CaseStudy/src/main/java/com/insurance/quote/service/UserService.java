package com.insurance.quote.service;

import com.insurance.quote.entities.UserCreation;

public interface  UserService {
	public String validateUser(UserCreation user);

	public String profileCreation(UserCreation user);
	
	public UserCreation getInputCreate();

	public UserCreation getInputCheck();

	public int getInputChoiceInsured();

	public int getInputChoiceAgent();

	public int getInputChoiceAdmin();

	public void closeResources();

	void doUserOperations(String roleCode, UserCreation user);
	}
