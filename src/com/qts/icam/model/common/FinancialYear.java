package com.qts.icam.model.common;

public class FinancialYear {
	private String financialYearObjectId;
	private String financialYearCode;
	private String financialYearDesc;
	private String financialYearName;
	private String updatedBy;
	private String yearStatus; 
	private String sessionStartDate;
	private String sessionEndDate;
	
	
	public String getFinancialYearObjectId() {
		return financialYearObjectId;
	}
	public void setFinancialYearObjectId(String financialYearObjectId) {
		this.financialYearObjectId = financialYearObjectId;
	}
	public String getFinancialYearCode() {
		return financialYearCode;
	}
	public void setFinancialYearCode(String financialYearCode) {
		this.financialYearCode = financialYearCode;
	}
	public String getFinancialYearDesc() {
		return financialYearDesc;
	}
	public void setFinancialYearDesc(String financialYearDesc) {
		this.financialYearDesc = financialYearDesc;
	}
	public String getFinancialYearName() {
		return financialYearName;
	}
	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getSessionStartDate() {
		return sessionStartDate;
	}
	public void setSessionStartDate(String sessionStartDate) {
		this.sessionStartDate = sessionStartDate;
	}
	public String getSessionEndDate() {
		return sessionEndDate;
	}
	public void setSessionEndDate(String sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}
	public String getYearStatus() {
		return yearStatus;
	}
	public void setYearStatus(String yearStatus) {
		this.yearStatus = yearStatus;
	}
	
	
	
}
