package com.insurance.quote.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="accounts")
public class AccountCreation implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seqaccnum")
	@SequenceGenerator(name = "seqaccnum", sequenceName = "seqaccnum", allocationSize = 1,initialValue = 1000)
	private int accountNumber;
	private String insuredName;
	private String insuredCity;
	private String insuredStreet;
	private String insuredState;
	private int insuredZip;
	private String businessSegment;
	private String userName;
	public AccountCreation() {}
	//create another constructor for acc no
	public AccountCreation(String insuredName,String insuredStreet, String insuredState,String insuredCity, 
			int insuredZip, String businessSegment,String userName) {
		
		this.insuredName = insuredName;
		this.insuredCity = insuredCity;
		this.insuredStreet = insuredStreet;
		this.insuredState = insuredState;
		this.insuredZip = insuredZip;
		this.businessSegment = businessSegment;
		this.userName=userName;
	}
	
	
	public AccountCreation(String insuredName,String insuredStreet, String insuredState,String insuredCity, 
			int insuredZip, String businessSegment) {
		
		this.insuredName = insuredName;
		this.insuredCity = insuredCity;
		this.insuredStreet = insuredStreet;
		this.insuredState = insuredState;
		this.insuredZip = insuredZip;
		this.businessSegment = businessSegment;
		}
	

	public String getInsuredName() {
		return insuredName;
	}
	public String getInsuredCity() {
		return insuredCity;
	}
	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	public void setInsuredCity(String insuredCity) {
		this.insuredCity = insuredCity;
	}
	public void setInsuredStreet(String insuredStreet) {
		this.insuredStreet = insuredStreet;
	}
	public void setInsuredState(String insuredState) {
		this.insuredState = insuredState;
	}
	public void setInsuredZip(int insuredZip) {
		this.insuredZip = insuredZip;
	}
	public void setBusinessSegment(String businessSegment) {
		this.businessSegment = businessSegment;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getInsuredStreet() {
		return insuredStreet;
	}
	public String getInsuredState() {
		return insuredState;
	}
	public int getInsuredZip() {
		return insuredZip;
	}
	public String getBusinessSegment() {
		return businessSegment;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	@Override
	public String toString() {
	return
	 "Insured Name	=	" + insuredName +"\n"
	+"Insured City	=	" + insuredCity +"\n"
	+"Insured Street	=	"+ insuredStreet +"\n"
	+"Insured State	=	" + insuredState +"\n"
	+"Insured Zip	=	" + insuredZip +"\n"
	+"Business Segment=	" + businessSegment;
	}
	
	
	

}
