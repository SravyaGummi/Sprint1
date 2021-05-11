package com.insurance.quote.service;

import java.util.List;

import com.insurance.quote.entities.Policy;

public interface PolicyListService {

	public List<Policy> policyList();

	public String getInput();

	public List<Policy> getPolCheck(int accNum);

}
