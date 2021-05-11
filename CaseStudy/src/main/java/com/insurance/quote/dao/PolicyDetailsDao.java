package com.insurance.quote.dao;

import java.util.List;

import com.insurance.quote.entities.PolicyDetails;

public interface PolicyDetailsDao {

	public void beginTransaction();

	public void commitTransaction();

	public String insertDetails(PolicyDetails details);

	public List<PolicyDetails> getPolReport(int polNumReport);

	public void closeResources();

}
