package com.insurance.quote.dao.test;


import static org.junit.Assert.*;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.insurance.quote.dao.JPAUtil;
import com.insurance.quote.dao.UserDAOImpl;
import com.insurance.quote.dao.UserDao;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.UserCreation;
import com.insurance.quote.exception.UserNameException;

public class UserDAOImplTest {

	private UserDao dao;
	private EntityManager em;

	@Before
	public void initialise() {
		dao = new UserDAOImpl();
		em = JPAUtil.getEntityManager();
	}

	@After
	public void release() {
		em.close();
	}


@Test
	public void testProfileCreation() {
		UserCreation userinput = new UserCreation("Jungkook", "BTS@jk1234", "Insured");
		UserDAOImpl dao = new UserDAOImpl();
		dao.profileCreation(userinput);
		EntityManager em = JPAUtil.getEntityManager();
		UserCreation user = em.find(UserCreation.class, userinput.getUserName());

		assertEquals("Jungkook", user.getUserName());
		assertEquals("BTS@jk1234", user.getPassword());
		assertEquals("Insured", user.getRoleCode());

	}
@Test
public void testfindName() {
	UserCreation uc1 = new UserCreation("Jungkook","BTS@jk1234", "Insured");
dao.findName(uc1);

	UserCreation obj=em.find(UserCreation.class, uc1.getUserName());
	assertEquals("Jungkook",obj.getUserName());

}

	
	@Test
	public void testCommitTransaction() {

	}

}
