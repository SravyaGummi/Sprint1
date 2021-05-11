package com.insurance.quote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.insurance.quote.entities.BusinessSegment;
import com.insurance.quote.entities.PolicyQuestions;

public class PolicyQuestionsDaoImpl implements PolicyQuestionsDao{
	private EntityManager entityManager;

	public PolicyQuestionsDaoImpl () {
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public List<PolicyQuestions> dispPolQues(String busiQuesId){	//Retrieves list of data based on business segment Id
	
		String qStr = "select pq from PolicyQuestions pq where pq.busSegId=:id";
		Query query = entityManager.createQuery(qStr);
		query.setParameter("id", busiQuesId);
		List<PolicyQuestions> results =query.getResultList();
		return results;
	}
	
}