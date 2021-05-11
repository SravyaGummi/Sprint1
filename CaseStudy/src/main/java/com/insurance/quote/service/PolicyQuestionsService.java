package com.insurance.quote.service;

import java.util.List;

import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.BusinessSegment;
import com.insurance.quote.entities.PolicyQuestions;

public interface PolicyQuestionsService {
	public BusinessSegment viewBusiQuesId(AccountCreation ac);
	
	public List<Integer> dispPolQues(String busiQuesId);

	public String getInput();

	public AccountCreation accCheck(String uName);
	
}
