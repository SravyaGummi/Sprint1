package com.insurance.quote.service;

import com.insurance.quote.dao.AccountDaoImpl;
import com.insurance.quote.dao.PolicyDaoImpl;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.Policy;
import com.insurance.quote.entities.UserCreation;

public class PolicyServiceImpl implements PolicyService{

	int totalPremium=0,accNum=0;
	PolicyQuestionsServiceImpl polService = new PolicyQuestionsServiceImpl();
	PolicyDaoImpl polDao = new PolicyDaoImpl();
	AccountDaoImpl accDao = new AccountDaoImpl();
	Policy policy = new Policy(totalPremium,accNum);
	
	@Override
	public AccountCreation getAcc(String userName) {	//returns account table data based on user name
		return accDao.getAcc(userName);
	}
	
	@Override
	public void policyEntry(int accNum,float totalPremium) {	//To persist the data in policy table
		policy = new Policy(totalPremium,accNum);
		polDao.beginTransaction();
		polDao.policyEntry(policy);
		polDao.commitTransaction();
	}

	@Override
	public void updatePremium(int polNum,float premium) {	//To update the premium in policy table
		
		polDao.beginTransaction();
		polDao.updatePremium(polNum,premium);
		polDao.commitTransaction();
	}
	
	
}
