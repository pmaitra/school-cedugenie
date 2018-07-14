package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.common.FinancialYear;
import com.qts.icam.model.common.ITSection;
import com.qts.icam.model.common.IncomeAge;

public class ITSectionGroup {
	

	
	private String itSectionGroupObjectId;
	private String itSectionGroupCode;
	private String itSectionGroupName;
	private String itSectionGroupDesc;
	private String updatedBy;
	private String status;
	private double groupAmount;
	private ITSection itSection;
	private List<ITSection> itSectionList;
	private FinancialYear financialYear;
	private IncomeAge incomeAge;
	private boolean totalAmountApplicable;
	
		
	
	
	
	public boolean isTotalAmountApplicable() {
		return totalAmountApplicable;
	}
	public void setTotalAmountApplicable(boolean totalAmountApplicable) {
		this.totalAmountApplicable = totalAmountApplicable;
	}
	public FinancialYear getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}
	public IncomeAge getIncomeAge() {
		return incomeAge;
	}
	public void setIncomeAge(IncomeAge incomeAge) {
		this.incomeAge = incomeAge;
	}
	public String getItSectionGroupObjectId() {
		return itSectionGroupObjectId;
	}
	public void setItSectionGroupObjectId(String itSectionGroupObjectId) {
		this.itSectionGroupObjectId = itSectionGroupObjectId;
	}
	public String getItSectionGroupCode() {
		return itSectionGroupCode;
	}
	public void setItSectionGroupCode(String itSectionGroupCode) {
		this.itSectionGroupCode = itSectionGroupCode;
	}
	public String getItSectionGroupName() {
		return itSectionGroupName;
	}
	public void setItSectionGroupName(String itSectionGroupName) {
		this.itSectionGroupName = itSectionGroupName;
	}
	public String getItSectionGroupDesc() {
		return itSectionGroupDesc;
	}
	public void setItSectionGroupDesc(String itSectionGroupDesc) {
		this.itSectionGroupDesc = itSectionGroupDesc;
	}
	public double getGroupAmount() {
		return groupAmount;
	}
	public void setGroupAmount(double groupAmount) {
		this.groupAmount = groupAmount;
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
	public ITSection getItSection() {
		return itSection;
	}
	public void setItSection(ITSection itSection) {
		this.itSection = itSection;
	}
	public List<ITSection> getItSectionList() {
		return itSectionList;
	}
	public void setItSectionList(List<ITSection> itSectionList) {
		this.itSectionList = itSectionList;
	}	

}
