package com.insurance.quote.service;

import java.util.List;

import com.insurance.quote.entities.Policy;

public interface PolicyDetailsService {

	public float getDetails(List<Integer> ans, String busiQuesID, int polNum);

	public Policy getPol(int accNum, float premium);

	public float getPolPremium(int accNum);

	public List<Policy> getPolList(int accNum);

	public int getPolAccNum(int polNum);

	public void closeResources();


}
