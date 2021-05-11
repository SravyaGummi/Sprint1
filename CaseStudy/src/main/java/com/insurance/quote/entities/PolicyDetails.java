package com.insurance.quote.entities;
//Policy Number	Numeric 10
//Question ID	Alpha Numeric 15
//Answer	Alpha Numeric 30

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="PolicyDetails")
public class PolicyDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id

	private String questionId;
	private int policyNumber;
	private String questionDesc;
	private String answer;
	private int answerWeightAge;

	
	public PolicyDetails() {}
	
	public PolicyDetails(int policyNumber, String questionId,String questionDesc, String answer,int answerWeightAge) {
		this.policyNumber = policyNumber;
		this.questionId = questionId;
		this.questionDesc=questionDesc;
		this.answer = answer;
		this.answerWeightAge=answerWeightAge;
	}
	
	public PolicyDetails(String questionDesc, String answer,int answerWeightAge) {
		
		this.questionDesc=questionDesc;
		this.answer = answer;
		this.answerWeightAge=answerWeightAge;
	}
	
	public int getPolicyNumber() {
		return policyNumber;
	}
	public String getQuestionId() {
		return questionId;
	}
	public String getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return "Question = "+ questionDesc +
				"Answer Selected= " + answer +
				"WeightAge=" + answerWeightAge+ "\n";
	}
	
		
}
