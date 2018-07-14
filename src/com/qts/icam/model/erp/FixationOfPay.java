package com.qts.icam.model.erp;

import java.util.List;

public class FixationOfPay {
	private String fixationOfPayObjectId;
	private String updatedBy;
	private int fixationOfPayId;
	private String fixationOfPayCode;		
	private String fixationOfPayName;
	private int fixationOfPayStartRange;
	private int fixationOfPayEndRange;
	private String fixationOfPayDesc;		
	private String status;
	private SalaryTemplate salaryTemplate;
	private FixationOfPayDetails fixationOfPayDetails;
	List<FixationOfPayDetails> fixationOfPayDetailsList;
	
	public String getFixationOfPayObjectId() {
		return fixationOfPayObjectId;
	}
	public void setFixationOfPayObjectId(String fixationOfPayObjectId) {
		this.fixationOfPayObjectId = fixationOfPayObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getFixationOfPayId() {
		return fixationOfPayId;
	}
	public void setFixationOfPayId(int fixationOfPayId) {
		this.fixationOfPayId = fixationOfPayId;
	}
	public String getFixationOfPayCode() {
		return fixationOfPayCode;
	}
	public void setFixationOfPayCode(String fixationOfPayCode) {
		this.fixationOfPayCode = fixationOfPayCode;
	}
	public String getFixationOfPayName() {
		return fixationOfPayName;
	}
	public void setFixationOfPayName(String fixationOfPayName) {
		this.fixationOfPayName = fixationOfPayName;
	}	
	public int getFixationOfPayStartRange() {
		return fixationOfPayStartRange;
	}
	public void setFixationOfPayStartRange(int fixationOfPayStartRange) {
		this.fixationOfPayStartRange = fixationOfPayStartRange;
	}
	public int getFixationOfPayEndRange() {
		return fixationOfPayEndRange;
	}
	public void setFixationOfPayEndRange(int fixationOfPayEndRange) {
		this.fixationOfPayEndRange = fixationOfPayEndRange;
	}
	public String getFixationOfPayDesc() {
		return fixationOfPayDesc;
	}
	public void setFixationOfPayDesc(String fixationOfPayDesc) {
		this.fixationOfPayDesc = fixationOfPayDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<FixationOfPayDetails> getFixationOfPayDetailsList() {
		return fixationOfPayDetailsList;
	}
	public void setFixationOfPayDetailsList(List<FixationOfPayDetails> fixationOfPayDetailsList) {
		this.fixationOfPayDetailsList = fixationOfPayDetailsList;
	}
	public SalaryTemplate getSalaryTemplate() {
		return salaryTemplate;
	}
	public void setSalaryTemplate(SalaryTemplate salaryTemplate) {
		this.salaryTemplate = salaryTemplate;
	}
	public FixationOfPayDetails getFixationOfPayDetails() {
		return fixationOfPayDetails;
	}
	public void setFixationOfPayDetails(FixationOfPayDetails fixationOfPayDetails) {
		this.fixationOfPayDetails = fixationOfPayDetails;
	}	
}
