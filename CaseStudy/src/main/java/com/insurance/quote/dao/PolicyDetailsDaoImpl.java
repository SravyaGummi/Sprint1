package com.insurance.quote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.insurance.quote.entities.PolicyDetails;

public class PolicyDetailsDaoImpl implements PolicyDetailsDao {
	private EntityManager entityManager;

	public PolicyDetailsDaoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}

	@Override
	public String insertDetails(PolicyDetails details) { // Persists data to policy details and returns message if data
															// is persisted
		String message = null;
		entityManager.persist(details);
		if (entityManager.contains(details)) {
			message = "added Sucessfully ";
		}
		return message;
	}

	@Override

	public List<PolicyDetails> getPolReport(int polNumReport) { // Retrieves list of details from policy details based
																// on policy number for report generation
		String qStr = "select NEW PolicyDetails( pd.questionDesc, pd.answer,pd.answerWeightAge) from PolicyDetails pd where pd.policyNumber=:num";
		TypedQuery<PolicyDetails> query = entityManager.createQuery(qStr, PolicyDetails.class);
		query.setParameter("num", polNumReport);
		List<PolicyDetails> results = query.getResultList();
		return results;

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
