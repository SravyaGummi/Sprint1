package com.insurance.quote.dao;

import java.util.List;

import com.insurance.quote.entities.PolicyQuestions;

public interface PolicyQuestionsDao {
	public List<PolicyQuestions> dispPolQues(String busiQuesId);
}
