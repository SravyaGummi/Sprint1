package com.insurance.quote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.insurance.quote.entities.BusinessSegment;

public class BusinessSegmentDaoImpl implements BusinessSegmentDao {
	private EntityManager entityManager;

	public BusinessSegmentDaoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}

	@Override
	public List<String> getBusinessSegement() { // Retrieves the list of business segment name from database
		String qStr = "select bs.busSegName from BusinessSegment bs ";
		Query query = entityManager.createQuery(qStr);
		return query.getResultList();

	}

	@Override
	public String getBusiName(int choice) { // Retrieves business segment name from database using sequence value of
											// selected business name
		String qStr = "select bs.busSegName from BusinessSegment bs where busSegSeq=:val";
		TypedQuery<String> query = entityManager.createQuery(qStr, String.class);
		query.setParameter("val", choice);
		String busiName = query.getSingleResult();
		return busiName;
	}

	@Override
	public BusinessSegment viewBusiQuesId(String businessName) { // Retrieves business segment id from database for
																	// selected business name

		String qStr = "select bs.busSegId from BusinessSegment bs where busSegName=:name";
		Query query = entityManager.createQuery(qStr);
		query.setParameter("name", businessName);

		String result = (String) query.getSingleResult();
		BusinessSegment idObj = new BusinessSegment(result);

		return idObj;
	}
}
