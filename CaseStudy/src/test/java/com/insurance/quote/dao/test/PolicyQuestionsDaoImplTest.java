package com.insurance.quote.dao.test;


import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.insurance.quote.dao.JPAUtil;
import com.insurance.quote.dao.PolicyQuestionsDao;
import com.insurance.quote.dao.PolicyQuestionsDaoImpl;
import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.PolicyQuestions;
import com.insurance.quote.entities.UserCreation;

import antlr.PythonCharFormatter;

public class PolicyQuestionsDaoImplTest {
	
		private  PolicyQuestionsDao dao;
		private EntityManager em;

		@Before
		public void initialise() {
			dao = new PolicyQuestionsDaoImpl();
			em = JPAUtil.getEntityManager();
		}

		@After
		public void release() {
			em.close();
		}

			@Test
		public void testDispPolQues() {
			
		PolicyQuestions pq=new PolicyQuestions("Q10109",9,"SEG101","Unkwon Hit or Theft Damage","10000 to 30000","30001 to 50000","50001 to 1000000",200,400,600);
			dao.dispPolQues(pq.getPolQuesId());
			
			PolicyQuestions user = em.find(PolicyQuestions.class,pq.getPolQuesId());
			System.out.println(user);
			assertEquals("Unkwon Hit or Theft Damage", user.getPolQuesDesc());
			assertEquals("10000 to 30000", user.getPolQuesAns1());
			assertEquals("30001 to 50000", user.getPolQuesAns2());
			assertEquals("50001 to 1000000", user.getPolQuesAns3());
		
		}
}