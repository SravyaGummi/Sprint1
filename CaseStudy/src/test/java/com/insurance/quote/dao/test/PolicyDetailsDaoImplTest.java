package com.insurance.quote.dao.test;


import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.insurance.quote.dao.JPAUtil;
import com.insurance.quote.dao.PolicyDetailsDao;
import com.insurance.quote.dao.PolicyDetailsDaoImpl;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.PolicyDetails;

public class PolicyDetailsDaoImplTest {

	private PolicyDetailsDao dao;
	private EntityManager em;

	@Before
	public void initialise() {
		dao = new PolicyDetailsDaoImpl();
		em = JPAUtil.getEntityManager();
	}

	@After
	public void release() {
		em.close();
	}
	@Test
	public void testInsertDetails() {
		PolicyDetails pd=new PolicyDetails(111,"qw","qw","qw",200);
				
em.getTransaction().begin();
		
		dao.insertDetails(pd);
		em.getTransaction().commit();
		assertTrue(em.contains(pd));
	
	}

}