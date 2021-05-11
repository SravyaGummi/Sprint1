package com.insurance.quote.service;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.insurance.quote.dao.AccountDaoImpl;
import com.insurance.quote.dao.BusinessSegmentDaoImpl;
import com.insurance.quote.dao.UserDAOImpl;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.BusinessSegment;
import com.insurance.quote.entities.UserCreation;
import com.insurance.quote.exception.MismatchUserNameException;
import com.insurance.quote.exception.ZipCodeException;
import com.insurance.quote.exception.NameException;

public class AccountServiceImpl implements AccountService {
	String name, city, street, state, ano, strZip;
	int zip, accno;
	String businessSegment, uName, choice, message = " ", checkUser = null;
	Scanner s1;
	UserDAOImpl dao = new UserDAOImpl();
	AccountDaoImpl accDao = new AccountDaoImpl();
	BusinessSegment bs = new BusinessSegment();
	AccountCreation ac = new AccountCreation(name, street, state, city, zip, businessSegment, uName);
	BusinessSegmentDaoImpl busiDao = new BusinessSegmentDaoImpl();

	@Override
	public String getInput() {// Validates the input user name in database
		s1 = new Scanner(System.in);
		System.out.println("Enter user Name ");
		uName = s1.nextLine();
		try {
			UserCreation userName = new UserCreation(uName, checkUser);
			UserCreation user1 = dao.findName(userName);
			if (user1 == null) { // Throws MismatchUserNameException if user name is not present
				uName = null;
				throw new MismatchUserNameException("Wrong Username Entered");
			}
		} catch (MismatchUserNameException mismatch) {
		}
		return uName;
	}

	@Override
	public AccountCreation getInput(UserCreation user) {
		// takes input from user for account creation
		s1 = new Scanner(System.in);
		System.out.println("Enter choice of Business Segment");
		choice = s1.nextLine();
		System.out.println("Enter user Name ");
		uName = s1.nextLine();
		System.out.println("Enter Name ");
		name = s1.nextLine();
		System.out.println("Enter street ");
		street = s1.nextLine();
		System.out.println("Enter city ");
		city = s1.nextLine();
		System.out.println("Enter state ");
		state = s1.nextLine();
		System.out.println("Enter zip ");
		strZip = s1.nextLine();

		String regex1 = "^[0-9]{5}$"; // Validates the input ZIP pattern
//	^                                   # start of line
//[0-9]									# matches digits from 0 to 9
//{5}									# repeats the preceding character for 5 times
//	$                                   # end of line

		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(strZip);

		String regex2 = "^[A-Za-z]+$"; // Validates the input Name pattern to consist only of alphabets
//	^                                   # start of line
//[A-Za-z]								# matches characters from A to Z and a to z in a string
// +									# repeats the preceding character for atleast one or more times
//	$                                   # end of line

		Pattern pattern2 = Pattern.compile(regex2);
		Matcher matcher2 = pattern2.matcher(name);

		int opt = Integer.parseInt(choice); // converts string to int
		businessSegment = busiDao.getBusiName(opt); // Retrieves the name of business segment based choice entered

		try {
			if (!matcher1.matches()) { // Throws ZipCodeException if zip code pattern does not match
				throw new ZipCodeException("ZipCode should be only numbers(0 to9) with a length of 5 ");
			}
		} catch (ZipCodeException zip) {
		}
		try {
			if (!matcher2.matches()) { // Throws NameException if name pattern does not match
				throw new NameException("Name should consist of alphabets only ");
			}
		} catch (NameException e) {
		}

		try { // Checks if user name entered has an existing account
			UserCreation userName = new UserCreation(uName, checkUser);
			UserCreation user1 = dao.findName(userName);
			AccountCreation acc = accDao.getAcc(uName);
			if (acc != null) {
				System.out.println("User has an existing account");
			}
			if (user1 == null) { // throws MismatchUserNameException if user name not found
				throw new MismatchUserNameException("Wrong Username Entered");
			} else if (matcher1.matches() && matcher2.matches() && acc == null) { // Creates account if Zip code, user
																					// name pattern matches and user has
																					// no existing account
				zip = Integer.parseInt(strZip);
				ac = new AccountCreation(name, street, state, city, zip, businessSegment, uName);
			}
		} catch (MismatchUserNameException mismatch) {
		}

		return ac;

	}

	public String accountCreation(AccountCreation ac) { // To persist the account created in the database
		accDao.beginTransaction();
		message = accDao.accountCreation(ac);
		accDao.commitTransaction();
		return message;
	}

	@Override
	public List<String> getBusinessSegment() { // Gets List of Business segments from the database
		return busiDao.getBusinessSegement();
	}

	@Override
	public void closeResources() {

		dao.closeResources();
	}
}
