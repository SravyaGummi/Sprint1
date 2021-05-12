package com.insurance.quote.dao.test;


import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.insurance.quote.dao.AccountDao;
import com.insurance.quote.dao.AccountDaoImpl;
import com.insurance.quote.dao.JPAUtil;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.UserCreation;

public class AccountDaoImplTest {
	private  AccountDao dao;
	private EntityManager em;

	@Before
	public void initialise() {
		dao = new AccountDaoImpl();
		em = JPAUtil.getEntityManager();
	}

	@After
	public void release() {
		em.close();
	}

	
	@Test
	public void testAccountCreation() {
		AccountCreation ac=new AccountCreation("bhagu","Hyd","Hyderabad","Telangana",50008,"Resturant","Jungkook");
		em.getTransaction().begin();
		
		dao.accountCreation(ac);
		em.getTransaction().commit();
		assertTrue(em.contains(ac));
		AccountCreation obj=em.find(AccountCreation.class, ac.getAccountNumber());
		System.out.println(obj);
	
	}
	
	@Test
	public void testgetAccNum() {
		AccountCreation ac=new AccountCreation("bhagu","Hyd","Hyderabad","Telangana",50008,"Resturant","Jungkook");

		em.getTransaction().begin();
		
		dao.getAcc("Jungkook");
		em.getTransaction().commit();
		assertEquals(1133,ac.getAccountNumber());
		
	}
	
	@Test
	public void testBeginTransaction() {
	
	}

	@Test
	public void testCommitTransaction() {
		
	}

}
