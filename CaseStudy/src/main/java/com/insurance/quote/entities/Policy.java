package com.insurance.quote.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Policy")
public class Policy implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seqpolnum")
	@SequenceGenerator(name = "seqpolnum", sequenceName = "seqpolnum", allocationSize = 1,initialValue = 23401)
		
	private int policyNumber;
	private float policyPremium;
	private int accountNumber;

	public Policy() {}
	
	public Policy( float policyPremium, int accountNumber) {
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
	}
	
	
	public void setPolicyPremium(float policyPremium) {
		this.policyPremium = policyPremium;
	}

	public int getPolicyNumber() {
		return policyNumber;
	}
	public float getPolicyPremium() {
		return policyPremium;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	
	@Override
	public String toString() {
		return "Policy Number = "+policyNumber+" Policy Premium = " + policyPremium +
				" Account Number = "+ accountNumber +"\n";
	}

}
