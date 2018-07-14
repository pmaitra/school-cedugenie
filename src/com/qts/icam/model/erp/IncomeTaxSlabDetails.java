package com.qts.icam.model.erp;

import java.util.List;

import com.qts.icam.model.common.IncomeAge;



public class IncomeTaxSlabDetails {
	private int incomeTaxSerialId;
	private String incomeTaxSlabDetailsId;
	private String updatedBy;
	private String incomeTaxSlabDetailsCode;
	private String incomeTaxSlabDetailsName;
	private String incomeTaxbasedOn;
	private String financialYear;
	private String citizenType;	
	private String gender;
	private String genderName;
	private IncomeAge incomeAge;
	private List<IncomeTaxParameters> incomeTaxParameters;
	private List<IncomeTaxSlab> incomeTaxSlabList;
	private String status;
	public int getIncomeTaxSerialId() {
		return incomeTaxSerialId;
	}
	public void setIncomeTaxSerialId(int incomeTaxSerialId) {
		this.incomeTaxSerialId = incomeTaxSerialId;
	}
	public String getIncomeTaxSlabDetailsId() {
		return incomeTaxSlabDetailsId;
	}
	public void setIncomeTaxSlabDetailsId(String incomeTaxSlabDetailsId) {
		this.incomeTaxSlabDetailsId = incomeTaxSlabDetailsId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getIncomeTaxSlabDetailsCode() {
		return incomeTaxSlabDetailsCode;
	}
	public void setIncomeTaxSlabDetailsCode(String incomeTaxSlabDetailsCode) {
		this.incomeTaxSlabDetailsCode = incomeTaxSlabDetailsCode;
	}
	public String getIncomeTaxSlabDetailsName() {
		return incomeTaxSlabDetailsName;
	}
	public void setIncomeTaxSlabDetailsName(String incomeTaxSlabDetailsName) {
		this.incomeTaxSlabDetailsName = incomeTaxSlabDetailsName;
	}
	public String getIncomeTaxbasedOn() {
		return incomeTaxbasedOn;
	}
	public void setIncomeTaxbasedOn(String incomeTaxbasedOn) {
		this.incomeTaxbasedOn = incomeTaxbasedOn;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getCitizenType() {
		return citizenType;
	}
	public void setCitizenType(String citizenType) {
		this.citizenType = citizenType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGenderName() {
		return genderName;
	}
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	public IncomeAge getIncomeAge() {
		return incomeAge;
	}
	public void setIncomeAge(IncomeAge incomeAge) {
		this.incomeAge = incomeAge;
	}
	public List<IncomeTaxParameters> getIncomeTaxParameters() {
		return incomeTaxParameters;
	}
	public void setIncomeTaxParameters(List<IncomeTaxParameters> incomeTaxParameters) {
		this.incomeTaxParameters = incomeTaxParameters;
	}
	public List<IncomeTaxSlab> getIncomeTaxSlabList() {
		return incomeTaxSlabList;
	}
	public void setIncomeTaxSlabList(List<IncomeTaxSlab> incomeTaxSlabList) {
		this.incomeTaxSlabList = incomeTaxSlabList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
