package com.insurance.quote.dao;

import com.insurance.quote.entities.AccountCreation;

public interface AccountDao {
	
	public String accountCreation(AccountCreation ac);
	public void beginTransaction();
	public void commitTransaction();
	AccountCreation getAccReport(int accNumReport);
	AccountCreation getAcc(String userName);
	void closeResources();
}
