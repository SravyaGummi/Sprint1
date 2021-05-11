package com.insurance.quote.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Business_Segment")
public class BusinessSegment implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="Bus_Seg_Id")
	private String busSegId;
	@Column(name="Bus_Seg_Name")
	private String busSegName;
	@Column(name="Bus_Seg_Seq")
	private int busSegSeq;
	
	public BusinessSegment(String busSegId, String busSegName, int busSegSeq) {
		this.busSegId = busSegId;
		this.busSegName = busSegName;
		this.busSegSeq = busSegSeq;
	}
	
	public BusinessSegment() {
		// TODO Auto-generated constructor stub
	}

	public BusinessSegment(String result) {
		// TODO Auto-generated constructor stub
		this.busSegId=result;
	}

	public String getBusSegId() {
		return busSegId;
	}
	public String getBusSegName() {
		return busSegName;
	}
	public int getBusSegSeq() {
		return busSegSeq;
	}

	@Override
	public String toString() {
		return "BusinessSegment [Bus_Seg_Id=" + busSegId + ", Bus_Seg_Name=" + busSegName + ", Bus_Seg_Seq="
				+ busSegSeq + "]";
	}
	

}
