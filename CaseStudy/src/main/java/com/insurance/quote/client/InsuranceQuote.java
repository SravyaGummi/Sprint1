package com.insurance.quote.client;

import java.util.List;
import java.util.Scanner;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.BusinessSegment;
import com.insurance.quote.entities.UserCreation;
import com.insurance.quote.service.AccountService;
import com.insurance.quote.service.AccountServiceImpl;
import com.insurance.quote.service.PolicyDetailsService;
import com.insurance.quote.service.PolicyDetailsServiceImpl;
import com.insurance.quote.service.PolicyListService;
import com.insurance.quote.service.PolicyListServiceImpl;
import com.insurance.quote.service.PolicyQuestionsService;
import com.insurance.quote.service.PolicyQuestionsServiceImpl;
import com.insurance.quote.service.PolicyReportService;
import com.insurance.quote.service.PolicyReportServiceImpl;
import com.insurance.quote.service.PolicyService;
import com.insurance.quote.service.PolicyServiceImpl;
import com.insurance.quote.service.UserService;
import com.insurance.quote.service.UserServiceImpl;
import com.insurance.quote.entities.Policy;
import com.insurance.quote.entities.PolicyDetails;

public class InsuranceQuote {

	public static void main(String[] args) {
		String roleCode = null;
		UserService userService = new UserServiceImpl();
		
		new PolicyServiceImpl();
		PolicyDetailsService policyDetailsService = new PolicyDetailsServiceImpl();
		new PolicyReportServiceImpl();
		AccountServiceImpl accountService = new AccountServiceImpl();
		UserCreation user = null;

		// =========================User Creation ,User Login================

		Scanner s1 = new Scanner(System.in);

		String logflag = "N";
		String exitflag = "N";
		try {  while (!exitflag.equals("Y")) {
		int attempt=0;
		//System.out.println(logflag+" "+exitflag+" "+attempt);
				while(roleCode==null) {
					attempt++;
					System.out.println("Enter Login Credentials: ");
					user = userService.getInputCheck();
					roleCode = userService.validateUser(user);
					System.out.println(roleCode);	//test
					if(attempt==2 && roleCode==null){
						System.out.println(logflag+" "+exitflag+" inside rolecode==null");
						break;
					}
				}
				
			System.out.println(logflag);
			
			if((!logflag.equals("Y")) && (roleCode != null)) {
					System.out.println(roleCode);	//test
					userService.doUserOperations(roleCode, user);
			}
				System.out.println("Do you want to exit ? (Y/N)");
				exitflag = s1.next().toUpperCase();
				System.out.println(logflag+" "+exitflag);
			
				if (exitflag.equals("N")) {
					logflag = "N";
					System.out.println("Trying to not exit");
					System.out.println(logflag+" "+exitflag);
				}
			
		}}catch(Exception e) {
					System.out.println(e);
		}

		 s1.close();
//		userService.closeResources();
//		accountService.closeResources();
//		policyDetailsService.closeResources();

	}	}
