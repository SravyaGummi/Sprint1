package com.insurance.quote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.insurance.quote.entities.Policy;
import com.insurance.quote.entities.PolicyQuestions;

public class PolicyDaoImpl implements PolicyDao {
	private EntityManager entityManager;

	public PolicyDaoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public void policyEntry(Policy policy)
	{
		entityManager.persist(policy);
	}
		
	@Override
	public Policy getPol(int accNum,float premium){		//Retrieves data based on account number and premium value
		String qStr = "select p from Policy p where p.accountNumber=:accountNum and p.policyPremium=:polNum";
		TypedQuery <Policy> query = entityManager.createQuery(qStr,Policy.class);
		query.setParameter("accountNum",accNum);
		query.setParameter("polNum",premium);
		Policy pol = query.getSingleResult();
		return pol;
	}
	
		
	@Override
	public List<Policy> getPolicy(){		//Retrieves list of data from policy table
		String qStr = "select p from Policy p ";
		Query query = entityManager.createQuery(qStr);
		List<Policy> pol = query.getResultList();
		return pol;
	}
	
	
	@Override
	public void updatePremium(int polNum,float premium) {	//updates the premium value in policy table when the policy is created
		Policy p=entityManager.find(Policy.class,polNum);
		p.setPolicyPremium(premium);
		entityManager.merge(p);
		
	 }
	
	
	
	
	@Override
		public void beginTransaction() {
			entityManager.getTransaction().begin();
		}
	
	
		@Override
		public void commitTransaction() {
			entityManager.getTransaction().commit();
		}
	
}
