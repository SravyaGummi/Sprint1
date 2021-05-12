package com.insurance.quote.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.insurance.quote.dao.AccountDaoImpl;
import com.insurance.quote.dao.BusinessSegmentDaoImpl;
import com.insurance.quote.dao.PolicyQuestionsDaoImpl;
import com.insurance.quote.dao.UserDAOImpl;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.BusinessSegment;
import com.insurance.quote.entities.PolicyQuestions;
import com.insurance.quote.entities.UserCreation;
import com.insurance.quote.exception.MismatchUserNameException;

public class PolicyQuestionsServiceImpl implements PolicyQuestionsService{
	
	int choice=0, premium=0,total=0, i=0;
	String id=null, checkUser=null,uName=null;
	AccountCreation ac = new AccountCreation();
	PolicyQuestionsDaoImpl polQuesDao = new PolicyQuestionsDaoImpl();
	BusinessSegmentDaoImpl busiDao = new BusinessSegmentDaoImpl();
	UserDAOImpl userDao = new UserDAOImpl();
	AccountDaoImpl accDao = new AccountDaoImpl();
	
	
	@Override
	public AccountCreation accCheck(String uName) {		//Checks if the user has an account associated
		AccountCreation acc = accDao.getAcc(uName);
			if(acc==null) {
			System.out.println("Account does not exist for the user");
		}
		return acc;
	}
	
	
	@Override
	public String getInput(String roleCode) {		//Accepts the input and validates the user name
		
		Scanner s1 =new Scanner(System.in);
		System.out.println("Enter user Name for policy creation ");
		uName=s1.nextLine();
		try{
			UserCreation userName = new UserCreation(uName,checkUser);
			UserCreation user1 = userDao.findName(userName);
			
			if (user1==null) {
				uName=null;						//Throws  MismatchUserNameException if user name is not in database
			throw new MismatchUserNameException("Wrong Username Entered");}
			if(roleCode.equals("Agent") && user1.getRoleCode().equals("Admin")) {
				System.out.println("No access for policy creation ");
				uName=null;
			}}
			catch(MismatchUserNameException mismatch) {}
		return uName;		
	}
	
	
	@Override
	public BusinessSegment viewBusiQuesId(AccountCreation ac) {
	String businessName = ac.getBusinessSegment();
	return busiDao.viewBusiQuesId(businessName);
	
}
	@Override
	public  List<Integer> dispPolQues(String busiQuesId) {
		List<PolicyQuestions> polList = polQuesDao.dispPolQues(busiQuesId);
		List<PolicyQuestions> polListObj = polList.stream().collect(Collectors.toList());	//retrieve questions and answers which are retrived from database

		List<Integer> ans = new ArrayList<Integer>();		//storing the option of the answer selected by the user in the list
		
		for(PolicyQuestions val:polListObj) {				
			i++;
			System.out.println(val);						//displaying questions and answers
			Scanner s1 =new Scanner(System.in);
			choice=s1.nextInt();
			
			switch(choice){									//Pol_Ques_Seq
				case 1:	
					ans.add(1);
					break;
				case 2:
					ans.add(2);
					break;
				case 3:
					ans.add(3);
					break;			
			}
	}
		return ans;
	}
}

	
