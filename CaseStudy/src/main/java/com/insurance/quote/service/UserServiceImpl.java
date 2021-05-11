package com.insurance.quote.service;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.insurance.quote.dao.UserDAOImpl;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.BusinessSegment;
import com.insurance.quote.entities.Policy;
import com.insurance.quote.entities.PolicyDetails;
import com.insurance.quote.entities.UserCreation;
import com.insurance.quote.exception.NoUserFoundException;
import com.insurance.quote.exception.UserNameException;
import com.insurance.quote.exception.WrongPasswordException;
import com.insurance.quote.exception.PasswordCriteriaException;

public class UserServiceImpl implements UserService {
	String userName, password, rolecode, choice, message;
	int opt = 0;
	Scanner s1;
	UserDAOImpl userDao = new UserDAOImpl();
	UserCreation userCreation1 = new UserCreation(userName, password);
	UserCreation userCreation2 = new UserCreation(userName, password, rolecode);

	@Override
	public String validateUser(UserCreation user) { // Validates the user name entered
		String strPass, strUser, strRole = null;
		String pass = user.getPassword();
		String userName = user.getUserName();

		UserCreation user1 = userDao.findName(user);

		try {
			if (user1 == null) { // if user name is not found throws NoUserFoundException
				throw new NoUserFoundException("Login Failed - Wrong username entered");
			} else {
				strPass = user1.getPassword();
				strUser = user1.getUserName();
				// Checks for matching user name and password
				if (strPass.equals(pass) && strUser.equals(userName)) {
					System.out.println("Login Sucessful");
					strRole = user1.getRoleCode();
				} else { // If password is different it throws WrongPasswordException
					strRole = null;
					throw new WrongPasswordException("Login failed - Wrong password entered");
				}
			}
		} catch (NoUserFoundException e) {
		} catch (WrongPasswordException e) {
		}
		return strRole; // returns the role code
	}

	@Override
	public String profileCreation(UserCreation user) {

		UserCreation user1 = userDao.findName(user); // Checks if username is present in database

		try {
			if (user1 == null) {
				userDao.beginTransaction();
				message = userDao.profileCreation(user);
				userDao.commitTransaction();
			} else {
				throw new UserNameException("UserName already exists "); // Throws UserNameException if user name is in
																			// database
			}
		} catch (UserNameException e) {
		}
		return message;
	}

	@Override
	public int getInputChoiceInsured() { // Displaying different functions available for insured
		s1 = new Scanner(System.in);

		System.out.println("Choose any of the following : ");
		System.out.println("(1) Account Creation" + "\n" + "(2)  Policy List ");

		opt = s1.nextInt();

		return opt; // Returns the option selected
	}

	@Override // Displaying different functions available for agent
	public int getInputChoiceAgent() {
		s1 = new Scanner(System.in);
		System.out.println("Choose any of the following : ");
		System.out.println("(1) Account Creation" + "\n" + "(2) Policy Creation" + "\n" + "(3) Policy List ");

		opt = s1.nextInt();

		return opt; // Returns the option selected
	}

	@Override
	public int getInputChoiceAdmin() { // Displaying different functions available for admin
		s1 = new Scanner(System.in);

		System.out.println("Choose any of the following : ");
		System.out.println("(1) User Creation" + "\n" + "(2) Account Creation" + "\n" + "(3) Policy Creation" + "\n"
				+ "(4) Policy List " + "\n" + "(5) Policy List and Policy Detailed  Report View");

		opt = s1.nextInt();

		return opt; // Returns the option selected
	}

	// Takes the input details for user creation stores the data in constructor
	public UserCreation getInputCheck() {
		s1 = new Scanner(System.in);
		System.out.println("Enter UserName ");
		userName = s1.nextLine();
		System.out.println("Enter Password ");
		password = s1.nextLine();
		userCreation1 = new UserCreation(userName, password);
		return userCreation1; // returns the object
	}

	@Override
	public UserCreation getInputCreate() {
		s1 = new Scanner(System.in);
		System.out.println("Enter UserName ");
		userName = s1.nextLine();
		System.out.println("Enter Password ");
		System.out.println(
				"Note:- Password must be 8 to 20 characters long with atleast one lowercase,uppercase character,a digit & a special character");
		password = s1.nextLine();
//		^                                   # start of line
//		  (?=.*[0-9])                       # positive lookahead, digit [0-9]
//		  (?=.*[a-z])                       # positive lookahead, one lower case character [a-z]
//		  (?=.*[A-Z])                       # positive lookahead, one upper case character [A-Z]
//		  (?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) # positive lookahead, one of the special character in this [..]
//		  .                                 # matches anything
//		  {8,20}                            # length at least 8 characters and maximum of 20 characters
//		$                                   # end of line
		try {
			String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
			Pattern pattern = Pattern.compile(passwordRegex);
			Matcher matcher = pattern.matcher(password);

			System.out.println("Enter Role ");
			rolecode = s1.nextLine();
			if (matcher.matches()) {
				userCreation2 = new UserCreation(userName, password, rolecode);
			}

			// throws PasswordCriteriaException if pattern
			else {
				throw new PasswordCriteriaException("Password criteria not acheived");
			}
		} catch (PasswordCriteriaException e) {
		}
		return userCreation2;
	}
	
	@Override
	public void doUserOperations(String roleCode, UserCreation user) {
		int option, num = 1, polNum, accNumReport, polNumReport;
		float premium = 0.0f, polReportPremium;
		String userName;
		UserService userService = new UserServiceImpl();
		AccountCreation account = new AccountCreation();
		AccountService accountService = new AccountServiceImpl();
		PolicyQuestionsService policyQuesService = new PolicyQuestionsServiceImpl();
		PolicyService policyService = new PolicyServiceImpl();
		PolicyReportService polReportService = new PolicyReportServiceImpl();
		PolicyListService polListService = new PolicyListServiceImpl(); // retrieve list of policies from

		PolicyDetailsService policyDetailsService = new PolicyDetailsServiceImpl();
		switch (roleCode) {
		case "Insured":
		case "insured":
			option = getInputChoiceInsured();
			switch (option) {
			case 1:// account creation

				userName = user.getUserName();
				if (userName != null) {
					for (String bSeg : accountService.getBusinessSegment()) { // to display the existing options of different business segments
						System.out.println(num + " " + bSeg);
						num++;
					}

					account = accountService.getInput(user,roleCode); // takes necessary inputs for user creation which are stored in entity class constructor and returns its object
					System.out.println(account);
					if (account.getInsuredName() != null) {
						System.out.println(account);
						message = accountService.accountCreation(account); // persist the data in database
						System.out.println(message);
					}
				}
				break;
			case 2:// view policy database
				 userName = polListService.getInput();
				if (userName != null) {
					AccountCreation accc = policyService.getAcc(userName);
					if (accc != null) {
						AccountCreation acc = policyService.getAcc(userName);
						int accNum = acc.getAccountNumber();
						if (polListService.getPolCheck(accNum) != null) {
							List<Policy> polDataList = policyDetailsService.getPolList(accNum);
							for (Policy value : polDataList) { // display list of policies
								System.out.println(value);
							}
						}
					} else {
						System.out.println("No account present for the user");
					}
				}
				break;
			}
			break;

		case "Agent":
		case "agent":
			option = userService.getInputChoiceAgent();
			switch (option) {
			case 1:// account creation

				 userName = accountService.getInput(); // Takes input for account creation
				if (userName != null) {
					for (String bSeg : accountService.getBusinessSegment()) { // to display the existing options of different business segments
						System.out.println(num + " " + bSeg);
						num++;
					}

					account = accountService.getInput(user,roleCode); // takes necessary inputs for user creation which are stored in entity class constructor and returns its object
					System.out.println(account);
					if (account.getInsuredName() != null) {
						System.out.println(account);
						message = accountService.accountCreation(account); // persist the data in database
						System.out.println(message);
					}
				}
				break;
			case 2:
				// policy creation

			userName = policyQuesService.getInput(); // Takes input for policy creation

				if (userName != null) {
					AccountCreation accCheck = policyQuesService.accCheck(userName);
					if (accCheck != null) {
						account = policyService.getAcc(userName);
						BusinessSegment objID = policyQuesService.viewBusiQuesId(account); // gets business segment id
						String busiQuesID = objID.getBusSegId();
						List<Integer> ans = policyQuesService.dispPolQues(busiQuesID); // retrieve and display questions
						// and answers which are
						// retrived from database
						System.out.println(ans); // remove

						int accNum = account.getAccountNumber();
						System.out.println(account); // remove
						policyService.policyEntry(accNum, premium); // Persisting account number and premium as zero in policy table
						Policy polData = policyDetailsService.getPol(accNum, premium); // Retrieving policy table data from database for particular account number
						polNum = polData.getPolicyNumber(); // Getting policy number using getter methods
						System.out.println(polNum); // remove
						premium = policyDetailsService.getDetails(ans, busiQuesID, polNum); // persist the  options selected by user in policy tables database and calculate premium
						policyService.updatePremium(polNum, premium); // updating the premium value in policy table
					}
				}
				break;
			case 3:// view policy

				PolicyListService polList1 = new PolicyListServiceImpl(); // retrieve list of policies from database
				 userName = polList1.getInput();
				if (userName != null) {
					AccountCreation accc = policyService.getAcc(userName);
					if (accc != null) {
						AccountCreation acc = policyService.getAcc(userName);
						int accNum = acc.getAccountNumber();
						if (polList1.getPolCheck(accNum) != null) {
							List<Policy> polDataList = policyDetailsService.getPolList(accNum);
							for (Policy value : polDataList) { // display list of policies
								System.out.println(value);
							}
						}
					} else {
						System.out.println("No account present for the user");
					}
				}
				break;
			}
			break;

		case "Admin":
		case "admin":
			option = userService.getInputChoiceAdmin();
			switch (option) {
			case 1:// User creation
				user = getInputCreate(); // Takes input and validates the data
				message = profileCreation(user); // Creates a profile for user
				System.out.println(message);
				break;

			case 2:// account creation
				userName = accountService.getInput();
				if (userName != null) {
					for (String bSeg : accountService.getBusinessSegment()) { // to display the existing options of different business segments
						System.out.println(num + " " + bSeg);
						num++;
					}

					account = accountService.getInput(user,roleCode); // takes necessary inputs for user creation which are stored in entity class constructor and returns its object
					System.out.println(account);
					if (account.getInsuredName() != null) {
						System.out.println(account);
						message = accountService.accountCreation(account); // persist the data in database
						System.out.println(message);
					}
				}
				break;
			case 3:
				// policy creation

				 userName = policyQuesService.getInput();

				if (userName != null) {
					AccountCreation accCheck = policyQuesService.accCheck(userName);
					if (accCheck != null) {
						account = policyService.getAcc(userName);
						BusinessSegment objID = policyQuesService.viewBusiQuesId(account); // gets business segment id
						String busiQuesID = objID.getBusSegId();
						List<Integer> ans = policyQuesService.dispPolQues(busiQuesID); // retrieve and display questions
						// and answers which are
						// retrived from database
						// System.out.println(ans); //remove

						int accNum = account.getAccountNumber();
						// System.out.println(acc); //remove
						policyService.policyEntry(accNum, premium); // Persisting account number and premium as zero in policy table
						Policy polData = policyDetailsService.getPol(accNum, premium); // Retrieving policy table data from database for particular account number
						polNum = polData.getPolicyNumber(); // Getting policy number using getter methods
						// System.out.println(polNum); //remove
						premium = policyDetailsService.getDetails(ans, busiQuesID, polNum); // persist the options  selected by user in policy tables database and calculate premium
						policyService.updatePremium(polNum, premium); // updating the premium value in  policy table
					}
				}
				break;
			case 4:// view policy

				// retrieve list of policies from database
				 userName = polListService.getInput();
				if (userName != null) {
					AccountCreation accc = policyService.getAcc(userName);
					if (accc != null) {
						AccountCreation acc = policyService.getAcc(userName);
						int accNum = acc.getAccountNumber();
						if (polListService.getPolCheck(accNum) != null) {
							List<Policy> polDataList = policyDetailsService.getPolList(accNum);
							for (Policy value : polDataList) { // display list of policies
								System.out.println(value);
							}
						}
					} else {
						System.out.println("No account present for the user");
					}
				}
				break;
			case 5:

				// retrieve list of policies from database
				 userName = polListService.getInput();
				if (userName != null) {
					account = policyService.getAcc(userName);
					if (account != null) {
						account = policyService.getAcc(userName);
						int accNum = account.getAccountNumber();
						if (polListService.getPolCheck(accNum) != null) {
							List<Policy> polDataList = policyDetailsService.getPolList(accNum);
							for (Policy value : polDataList) { // display list of policies
								System.out.println(value);
							}
						}
						// Report Generation
						polNumReport = polReportService.getInput(); // get account number of selected user
																	// to view the report
						accNumReport = policyDetailsService.getPolAccNum(polNumReport); // retrieve policy table details
						account = polReportService.getAccReport(accNumReport); // retrieve account details of  user for report
						System.out.println(account);
						List<PolicyDetails> polReport = polReportService.getPolReport(polNumReport); // list of question answer and weightage of user for report
						for (PolicyDetails report : polReport) {
							System.out.println(report);
						}
						polReportPremium = policyDetailsService.getPolPremium(polNumReport); // Retrieve premium value for report
						System.out.println(polReportPremium);

					}
				} else {
					System.out.println("No account present for the user");
				}

				break;

			default:
				System.out.println("Wrong choice entered");
			}
			break;

		default:
			System.out.println("Wrong choice entered");
		}
	}

	@Override
	public void closeResources() {
		s1.close();
		userDao.closeResources();
	}

}