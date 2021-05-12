package com.insurance.quote.client;

import java.util.Scanner;
import org.apache.log4j.Logger;
import com.insurance.quote.entities.UserCreation;
import com.insurance.quote.service.AccountServiceImpl;
import com.insurance.quote.service.PolicyDetailsService;
import com.insurance.quote.service.PolicyDetailsServiceImpl;
import com.insurance.quote.service.PolicyReportServiceImpl;
import com.insurance.quote.service.PolicyServiceImpl;
import com.insurance.quote.service.UserService;
import com.insurance.quote.service.UserServiceImpl;

public class InsuranceQuote {

	public static void main(String[] args) {
		final Logger log=Logger.getLogger(InsuranceQuote.class.getName());
		String roleCode = null;
		UserService userService = new UserServiceImpl();
		UserCreation user = null;

		Scanner s1 = new Scanner(System.in);	//scanner statement

		String logflag = "N";
		String exitflag = "N";
		try {  while (!exitflag.equals("Y")) {
		int attempt=0;
		
				while(roleCode==null) {
					attempt++;
					System.out.println("Enter Login Credentials: ");
					user = userService.getInputCheck();
					roleCode = userService.validateUser(user);
					log.info(roleCode);	//test
					if(attempt==2 && roleCode==null){
						log.debug(logflag+" "+exitflag);
						break;
					}
				}
				
			log.debug(logflag);
			
			if((!logflag.equals("Y")) && (roleCode != null)) {
					log.info(roleCode);	//test
					userService.doUserOperations(roleCode, user);
			}
				System.out.println("Do you want to exit ? (Y/N)");
				exitflag = s1.next().toUpperCase();
				log.debug(logflag+" "+exitflag);
			
				if (exitflag.equals("N")) {
					logflag = "N";
				log.info(logflag+" "+exitflag);
				}
			
		}}catch(Exception e) {
					System.out.println(e);
		}

		 s1.close();
//		userService.closeResources();
//		accountService.closeResources();
//		policyDetailsService.closeResources();

	}	}
