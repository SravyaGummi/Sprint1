package com.insurance.quote.dao;

import java.util.List;

import com.insurance.quote.entities.BusinessSegment;

public interface BusinessSegmentDao {
	public List<String> getBusinessSegement();
	public String getBusiName(int choice);
	public BusinessSegment viewBusiQuesId(String businessName);
}
