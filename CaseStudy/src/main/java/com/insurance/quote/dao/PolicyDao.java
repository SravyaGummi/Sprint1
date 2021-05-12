package com.insurance.quote.dao;

import java.util.List;

import com.insurance.quote.entities.Policy;

public interface PolicyDao {
	public void policyEntry(Policy policy);
	public void beginTransaction();
	public void commitTransaction();
	public Policy getPol(int accNum, float premium);
	void updatePremium(int polNum, float premium);
	public List<Policy> getPolicy();
}
