package com.insurance.quote.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.insurance.quote.dao.BusinessSegmentDao;
import com.insurance.quote.dao.BusinessSegmentDaoImpl;
import com.insurance.quote.dao.JPAUtil;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.BusinessSegment;
import com.insurance.quote.entities.PolicyQuestions;

public class BusinessSegmentDaoImplTest {
	private BusinessSegmentDao dao;
	private EntityManager em;

	@Before
	public void initialise() {
		dao = new BusinessSegmentDaoImpl();
		em = JPAUtil.getEntityManager();
	}

	@After
	public void release() {
		em.close();
	}

	@Test
	public void testGetBusinessSegement() {
		BusinessSegment bs = new BusinessSegment("abc", "abc", 123);

		em.getTransaction().begin();
		dao.getBusinessSegement();
		em.getTransaction().commit();

		BusinessSegment obj = em.find(BusinessSegment.class, bs.getBusSegId());
		assertEquals("abc", bs.getBusSegId());
		assertEquals("abc", bs.getBusSegName());
		assertEquals(123, bs.getBusSegSeq());

	}

	@Test
	public void testgetBusiName() {
		BusinessSegment bs = new BusinessSegment("SEG101", "Business Auto", 1);
		dao.getBusiName(1);
	
		assertEquals("Business Auto",bs.getBusSegName());

	}

	@Test
	public void testViewBusiQuesId() {

		BusinessSegment bs = dao.viewBusiQuesId("Business Auto");

		assertEquals("SEG101", bs.getBusSegId());
		

	}
}