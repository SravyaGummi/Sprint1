package com.insurance.quote.dao.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.insurance.quote.dao.JPAUtil;
import com.insurance.quote.dao.PolicyDao;
import com.insurance.quote.dao.PolicyDaoImpl;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.Policy;

public class PolicyDaoImplTest {
	private PolicyDao dao;
	private EntityManager em;

	@Before
	public void initialise() {
		dao = new PolicyDaoImpl();
		em = JPAUtil.getEntityManager();
	}

	@After
	public void release() {
		em.close();
	}

	@Test
	public void testGetPolNum() {
		Policy py = new Policy(1000.0f,1126);
		em.getTransaction().begin();
		dao.getPol(py.getAccountNumber(), py.getPolicyPremium());

		em.getTransaction().commit();

		Policy obj = em.find(Policy.class, py.getAccountNumber());

		assertEquals(1126, py.getAccountNumber());
		assertEquals(1000.0f, py.getPolicyPremium(), 0.00);

	}

	@Test
	public void testPolicyEntry() {
		Policy py = new Policy(1000.0f,1126);
		em.getTransaction().begin();

		dao.policyEntry(py);
		em.getTransaction().commit();

		Policy obj = em.find(Policy.class, py.getPolicyNumber());

		assertTrue(em.contains(obj));

	}

	@Test
	public void testUpdatePremium() {
		Policy py = new Policy(1000.0f,1126);
		em.getTransaction().begin();

		dao.updatePremium(py.getAccountNumber(), py.getPolicyPremium());
		em.getTransaction().commit();
		assertEquals(1126, py.getAccountNumber());
		assertEquals(1000.0f, py.getPolicyPremium(), 0.00);

	}

}
