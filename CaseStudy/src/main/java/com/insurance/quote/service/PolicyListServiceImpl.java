package com.insurance.quote.service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.insurance.quote.dao.AccountDaoImpl;
import com.insurance.quote.dao.PolicyDaoImpl;
import com.insurance.quote.dao.PolicyListDao;
import com.insurance.quote.dao.PolicyListDaoImpl;
import com.insurance.quote.dao.UserDAOImpl;
import com.insurance.quote.entities.Policy;
import com.insurance.quote.entities.UserCreation;
import com.insurance.quote.exception.MismatchUserNameException;

public class PolicyListServiceImpl implements PolicyListService{
	
	String uName=null,checkUser=null;
	PolicyListDao polListDao = new PolicyListDaoImpl();
	UserDAOImpl userDao = new UserDAOImpl();
	PolicyDaoImpl polDao = new PolicyDaoImpl();
	AccountDaoImpl accDao = new AccountDaoImpl();
	@Override
	public String getInput() {			//Accepts the input and validates the user name
		Scanner s1 =new Scanner(System.in);
		System.out.println("Enter user Name ");
		uName=s1.nextLine();
		try{
			UserCreation userName = new UserCreation(uName,checkUser);
			UserCreation user1 = userDao.findName(userName);
			if (user1==null) {
				uName=null;								//Throws  MismatchUserNameException if user name is not in database
			throw new MismatchUserNameException("Wrong Username Entered");
			}
			}
			catch(MismatchUserNameException mismatch) {}
		return uName;		
	}
	
	@Override
	public List<Policy> policyList(){		//Returns list of policy data
		return polListDao.policyList();
}
	@Override
	public List<Policy> getPolCheck(int accNum) {		//Returns list of policy based on account number
		List<Policy> policy = polDao.getPolicy();
		List<Policy> polCheck = policy.stream().filter(pol->pol.getAccountNumber()==accNum).collect(Collectors.toList());
		if(polCheck.isEmpty()) {
			System.out.println("No policy associated with the user");
		}
		return polCheck;
	}	
	
}
