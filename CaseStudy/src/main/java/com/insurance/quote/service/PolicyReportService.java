package com.insurance.quote.service;

import java.util.List;

import com.insurance.quote.entities.AccountCreation;
import com.insurance.quote.entities.PolicyDetails;

public interface PolicyReportService {

	public int getInput();

	public AccountCreation getAccReport(int accNumReport);

	public List<PolicyDetails> getPolReport(int polNumReport);

}
