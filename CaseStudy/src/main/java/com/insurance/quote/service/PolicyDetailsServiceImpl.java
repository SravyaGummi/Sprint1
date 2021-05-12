package com.insurance.quote.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.insurance.quote.dao.PolicyDaoImpl;
import com.insurance.quote.dao.PolicyDetailsDaoImpl;
import com.insurance.quote.dao.PolicyQuestionsDaoImpl;
import com.insurance.quote.entities.Policy;
import com.insurance.quote.entities.PolicyDetails;
import com.insurance.quote.entities.PolicyQuestions;

public class PolicyDetailsServiceImpl implements PolicyDetailsService {
	private static Logger log=Logger.getLogger(PolicyDetailsServiceImpl.class.getName());
	int i = 0, premium, weightAge, policyNumber, answerWeightAge;
	String strAnswer, strQId, strQuesDesc, questionId, questionDesc, answer;

	PolicyDaoImpl polDao = new PolicyDaoImpl();
	PolicyDetailsDaoImpl polDetailsDao = new PolicyDetailsDaoImpl();
	PolicyQuestionsDaoImpl polQuesDao = new PolicyQuestionsDaoImpl();
	PolicyDetails polDetails = new PolicyDetails(policyNumber, questionId, questionDesc, answer, answerWeightAge);

	@Override
	public Policy getPol(int accNum, float premium) { // Gets policy object based on account number and premium
		return polDao.getPol(accNum, premium);
	}

	@Override
	public int getPolAccNum(int polNum) { // Gets account number based on policy number
		List<Policy> policy = polDao.getPolicy();
		List<Integer> pol = policy.stream().filter(polAcc -> polAcc.getPolicyNumber() == polNum)
				.map(acc -> acc.getAccountNumber()).collect(Collectors.toList());
		log.info(pol);
		int accNum = pol.get(0);
		return accNum;
	}

	@Override
	public float getDetails(List<Integer> ans, String busiQuesID, int polNum) { // Persist the values obtained from
																			// stream API and returns premium value
		int size = ans.size();
		List<PolicyQuestions> polList = polQuesDao.dispPolQues(busiQuesID);
		
		for (i = 0; i < size; i++) {
			ans.get(i);
			List<String> qId = polList.stream().filter(seq -> seq.getPolQuesSeq() == (i + 1))
					.map(id -> id.getPolQuesId()).collect(Collectors.toList());
			strQId = qId.get(0);
			List<String> quesDesc = polList.stream().filter(seq -> seq.getPolQuesSeq() == (i + 1))
					.map(ques -> ques.getPolQuesDesc()).collect(Collectors.toList());
			strQuesDesc = quesDesc.get(0);
			if (ans.get(i) == 1) {
				List<String> answer = polList.stream().filter(seq -> seq.getPolQuesSeq() == (i + 1))
						.map(ans1 -> ans1.getPolQuesAns1()).collect(Collectors.toList());
				List<Integer> answerWeight = polList.stream().filter(seq -> seq.getPolQuesSeq() == (i + 1))
						.map(ans1 -> ans1.getPolQuesAns1weightage()).collect(Collectors.toList());
				strAnswer = answer.get(0);
				
				weightAge = answerWeight.get(0);
				premium += weightAge;
			} else if (ans.get(i) == 2) {
				List<String> answer = polList.stream().filter(seq -> seq.getPolQuesSeq() == (i + 1))
						.map(ans2 -> ans2.getPolQuesAns2()).collect(Collectors.toList());
				List<Integer> answerWeight = polList.stream().filter(seq -> seq.getPolQuesSeq() == (i + 1))
						.map(ans2 -> ans2.getPolQuesAns2weightage()).collect(Collectors.toList());
				strAnswer = answer.get(0);
				weightAge = answerWeight.get(0);
				premium += weightAge;
			} else if (ans.get(i) == 3) {
				List<String> answer = polList.stream().filter(seq -> seq.getPolQuesSeq() == (i + 1))
						.map(ans3 -> ans3.getPolQuesAns3()).collect(Collectors.toList());
				List<Integer> answerWeight = polList.stream().filter(seq -> seq.getPolQuesSeq() == (i + 1))
						.map(ans3 -> ans3.getPolQuesAns3weightage()).collect(Collectors.toList());
				strAnswer = answer.get(0);
				weightAge = answerWeight.get(0);
				premium += weightAge;
			}
			polDetails = new PolicyDetails(polNum, strQId, strQuesDesc, strAnswer, weightAge);
		
			log.info(polDetails);
			polDetailsDao.beginTransaction();
			polDetailsDao.insertDetails(polDetails);
			polDetailsDao.commitTransaction();

		}

		return premium;
	}

	@Override
	public List<Policy> getPolList(int accNum) { // Returns list of policy based on account number
		List<Policy> policy = polDao.getPolicy();
		List<Policy> pol = policy.stream().filter(polAcc -> polAcc.getAccountNumber() == accNum)
				.collect(Collectors.toList());
		return pol;
	}

	@Override
	public float getPolPremium(int polNum) { // Returns premium based on policy number
		List<Policy> policy = polDao.getPolicy();
		List<Float> polPremium = policy.stream().filter(pol -> pol.getPolicyNumber() == polNum)
				.map(premium -> premium.getPolicyPremium()).collect(Collectors.toList());
		float premium = polPremium.get(0);
		return premium;
	}

	@Override
	public void closeResources() {
		polDetailsDao.closeResources();
	}

}
