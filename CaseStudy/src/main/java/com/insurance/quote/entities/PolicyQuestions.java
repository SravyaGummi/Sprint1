package com.insurance.quote.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Policy_Questions")
public class PolicyQuestions implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="Pol_Ques_Id")
	private String polQuesId;
	@Column(name="Pol_Ques_Seq")
	private int polQuesSeq;
	@Column(name="Bus_Seg_Id")
	private String busSegId;
	@Column(name="Pol_Ques_Desc")
	private String polQuesDesc;
	@Column(name="Pol_Ques_Ans1")
	private String polQuesAns1;
	@Column(name="Pol_Ques_Ans2")
	private String polQuesAns2;
	@Column(name="Pol_Ques_Ans3")
	private String polQuesAns3;
	@Column(name="Pol_Ques_Ans1_weightage")
	private int polQuesAns1weightage;
	@Column(name="Pol_Ques_Ans2_weightage")
	private int polQuesAns2weightage;
	@Column(name="Pol_Ques_Ans3_weightage")
	private int polQuesAns3weightage;
	
	public PolicyQuestions() {}

	public String getPolQuesId() {
		return polQuesId;
	}

	public int getPolQuesSeq() {
		return polQuesSeq;
	}

	public String getBusSegId() {
		return busSegId;
	}

	public String getPolQuesDesc() {
		return polQuesDesc;
	}

	public String getPolQuesAns1() {
		return polQuesAns1;
	}

	public String getPolQuesAns2() {
		return polQuesAns2;
	}

	public String getPolQuesAns3() {
		return polQuesAns3;
	}

	public int getPolQuesAns1weightage() {
		return polQuesAns1weightage;
	}

	public int getPolQuesAns2weightage() {
		return polQuesAns2weightage;
	}

	public int getPolQuesAns3weightage() {
		return polQuesAns3weightage;
	}
	
	public PolicyQuestions(String polQuesDesc,String polQuesAns1,String polQuesAns2,String polQuesAns3
		,int polQuesAns1weightage,int polQuesAns2weightage,int polQuesAns3weightage) {
		this.polQuesDesc = polQuesDesc;
		this.polQuesAns1 = polQuesAns1;
		this.polQuesAns2 = polQuesAns2;
		this.polQuesAns3 = polQuesAns3;		
		this.polQuesAns1weightage=polQuesAns1weightage;
		this.polQuesAns2weightage=polQuesAns2weightage;
		this.polQuesAns3weightage=polQuesAns3weightage;
	}

	public PolicyQuestions(String polQuesId, int polQuesSeq, String busSegId, String polQuesDesc,
			String polQuesAns1, String polQuesAns2, String polQuesAns3, int polQuesAns1weightage,
			int polQuesAns2weightage, int polQuesAns3weightage) {
		super();
		this.polQuesId = polQuesId;
		this.polQuesSeq = polQuesSeq;
		this.busSegId = busSegId;
		this.polQuesDesc = polQuesDesc;
		this.polQuesAns1 = polQuesAns1;
		this.polQuesAns2 = polQuesAns2;
		this.polQuesAns3 = polQuesAns3;
		this.polQuesAns1weightage = polQuesAns1weightage;
		this.polQuesAns2weightage = polQuesAns2weightage;
		this.polQuesAns3weightage = polQuesAns3weightage;
	}

	@Override
	public String toString() {
		return  polQuesDesc + ": (1) " + polQuesAns1
				+ " (2) " + polQuesAns2 + " (3) " + polQuesAns3 ;
	}

}
	
	
	
		