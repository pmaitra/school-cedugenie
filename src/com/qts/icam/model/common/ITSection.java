package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.common.ITSectionDetails;

public class ITSection {
	


	private String itSectionObjectId;
	private String itSectionCode;
	private String itSectionName;
	private String itSectionDesc;
	private String updatedBy;
	private String status;
	private double amount;
	private List<ITSectionDetails> itSectionDetailsList;
	
	public String getItSectionObjectId() {
		return itSectionObjectId;
	}
	public void setItSectionObjectId(String itSectionObjectId) {
		this.itSectionObjectId = itSectionObjectId;
	}
	public String getItSectionCode() {
		return itSectionCode;
	}
	public void setItSectionCode(String itSectionCode) {
		this.itSectionCode = itSectionCode;
	}
	public String getItSectionName() {
		return itSectionName;
	}
	public void setItSectionName(String itSectionName) {
		this.itSectionName = itSectionName;
	}
	public String getItSectionDesc() {
		return itSectionDesc;
	}
	public void setItSectionDesc(String itSectionDesc) {
		this.itSectionDesc = itSectionDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public List<ITSectionDetails> getItSectionDetailsList() {
		return itSectionDetailsList;
	}
	public void setItSectionDetailsList(List<ITSectionDetails> itSectionDetailsList) {
		this.itSectionDetailsList = itSectionDetailsList;
	}

}
