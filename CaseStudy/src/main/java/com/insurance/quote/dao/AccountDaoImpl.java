package com.insurance.quote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;

import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.PolicyQuestions;
import com.insurance.quote.entities.UserCreation;

public class AccountDaoImpl implements AccountDao {

	private EntityManager entityManager;

	public AccountDaoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}

	@Override
	public AccountCreation getAcc(String userName) { // Query to retrieve the data from accounts table in database
		AccountCreation acc = null;
		try {
			String qStr = "select ac from AccountCreation ac where ac.userName=:name";
			TypedQuery<AccountCreation> query = entityManager.createQuery(qStr, AccountCreation.class);
			query.setParameter("name", userName);
			acc = query.getSingleResult();
			return acc;
		} catch (Exception e) {
		}
		return acc;
	}

	@Override
	public AccountCreation getAccReport(int accNumReport) { // query to retrieve data from accounts table for displaying
															// in report generation

		String qStr = "select NEW AccountCreation( ac.insuredName,ac.insuredStreet,ac.insuredState, ac.insuredCity, ac.insuredZip,ac.businessSegment) from AccountCreation ac where ac.accountNumber=:num";
		TypedQuery<AccountCreation> query = entityManager.createQuery(qStr, AccountCreation.class);
		query.setParameter("num", accNumReport);
		AccountCreation acReport = query.getSingleResult();
		return acReport;
	}

	@Override
	public String accountCreation(AccountCreation ac) { // Persists the data entered for account creation in accounts
														// table of database
		String message = null;
		entityManager.persist(ac);
		if (entityManager.contains(ac)) {
			message = "Account Created Sucessfully ";
		}
		return message;
	}

	@Override
	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}

	@Override
	public void commitTransaction() {
		entityManager.getTransaction().commit();
	}

	@Override
	public void closeResources() {
		entityManager.close();
		}
}
