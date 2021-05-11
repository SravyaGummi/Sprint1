package com.insurance.quote.service;

import java.util.List;
import java.util.Scanner;

import com.insurance.quote.dao.AccountDao;
import com.insurance.quote.dao.AccountDaoImpl;
import com.insurance.quote.dao.PolicyDao;
import com.insurance.quote.dao.PolicyDaoImpl;
import com.insurance.quote.dao.PolicyDetailsDao;
import com.insurance.quote.dao.PolicyDetailsDaoImpl;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.PolicyDetails;

public class PolicyReportServiceImpl implements PolicyReportService{

	PolicyDao polDao = new PolicyDaoImpl();
	PolicyDetailsDao polDetailsDao = new PolicyDetailsDaoImpl();
	AccountDao accDao = new AccountDaoImpl();
	@Override
	public int getInput() {		//Takes input from console and returns the value
	
		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter the Policy Number to view their report");
		int accNumReport=s1.nextInt();
		return accNumReport;		
	}	
	
	@Override
	public AccountCreation getAccReport(int accNumReport){		//Gets data of accounts as object
		return accDao.getAccReport(accNumReport);
		
	}
	
	@Override
	public List<PolicyDetails> getPolReport(int polNumReport) {		//Gets list of policy details based on policy number
	return polDetailsDao.getPolReport(polNumReport);
}
}