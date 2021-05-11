package com.insurance.quote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.insurance.quote.entities.Policy;

public class PolicyListDaoImpl implements PolicyListDao{
	private EntityManager entityManager;

	public PolicyListDaoImpl(){
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public List<Policy> policyList(){		//returns a list of data from policy table
		String qStr = "select pol from Policy pol";
		Query list = entityManager.createQuery(qStr);
		return list.getResultList();
	}

}
