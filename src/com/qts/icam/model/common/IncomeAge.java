package com.qts.icam.model.common;

import com.qts.icam.model.common.FinancialYear;

public class IncomeAge {
	

	private String incomeAgeId;
	private String incomeAgeObjectId;
	private String updatedBy;
	private String incomeAgeName;
	private String incomeAgeCode;
	private String startAge;
	private String endAgelessThan;	
	private FinancialYear financialYear;
	private String status;
	
	public String getIncomeAgeId() {
		return incomeAgeId;
	}
	public void setIncomeAgeId(String incomeAgeId) {
		this.incomeAgeId = incomeAgeId;
	}	
	public String getIncomeAgeObjectId() {
		return incomeAgeObjectId;
	}
	public void setIncomeAgeObjectId(String incomeAgeObjectId) {
		this.incomeAgeObjectId = incomeAgeObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getIncomeAgeName() {
		return incomeAgeName;
	}
	public void setIncomeAgeName(String incomeAgeName) {
		this.incomeAgeName = incomeAgeName;
	}
	public String getIncomeAgeCode() {
		return incomeAgeCode;
	}
	public void setIncomeAgeCode(String incomeAgeCode) {
		this.incomeAgeCode = incomeAgeCode;
	}
	public String getStartAge() {
		return startAge;
	}
	public void setStartAge(String startAge) {
		this.startAge = startAge;
	}
	public String getEndAgelessThan() {
		return endAgelessThan;
	}
	public void setEndAgelessThan(String endAgelessThan) {
		this.endAgelessThan = endAgelessThan;
	}
	public FinancialYear getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
