package com.qts.icam.model.common;

public class PreviousYearFinanceData {


	private String previousDataObjectId;
	private String previousDataCode;
	private String previousDataDesc;
	private String previousDataName;
	private int previousDataId;
	private String updatedBy;

	private Double amount;
	private String narration;
	private String date;
	private String incomeExpense;
	private String mode;
	
	
	
	
	public String getPreviousDataObjectId() {
		return previousDataObjectId;
	}
	public void setPreviousDataObjectId(String previousDataObjectId) {
		this.previousDataObjectId = previousDataObjectId;
	}
	public String getPreviousDataCode() {
		return previousDataCode;
	}
	public void setPreviousDataCode(String previousDataCode) {
		this.previousDataCode = previousDataCode;
	}
	public String getPreviousDataDesc() {
		return previousDataDesc;
	}
	public void setPreviousDataDesc(String previousDataDesc) {
		this.previousDataDesc = previousDataDesc;
	}
	public String getPreviousDataName() {
		return previousDataName;
	}
	public void setPreviousDataName(String previousDataName) {
		this.previousDataName = previousDataName;
	}
	public int getPreviousDataId() {
		return previousDataId;
	}
	public void setPreviousDataId(int previousDataId) {
		this.previousDataId = previousDataId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIncomeExpense() {
		return incomeExpense;
	}
	public void setIncomeExpense(String incomeExpense) {
		this.incomeExpense = incomeExpense;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
}
