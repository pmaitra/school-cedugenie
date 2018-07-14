package com.qts.icam.model.finance;

public class Budget {
	
	private int budgetSerialId;
	
	private String objectId;
	private String updatedBy;

	private String budgetCode;
	private String budgetName;
	private String budgetDesc;
	
	private String academicYear;
	private String department;
	
	private Double expectedIncome;
	private Double actualIncome;
	private Double expectedExpense;
	private Double actualExpense;
	
	public int getBudgetSerialId() {
		return budgetSerialId;
	}
	public void setBudgetSerialId(int budgetSerialId) {
		this.budgetSerialId = budgetSerialId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getBudgetCode() {
		return budgetCode;
	}
	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}
	public String getBudgetName() {
		return budgetName;
	}
	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}
	public String getBudgetDesc() {
		return budgetDesc;
	}
	public void setBudgetDesc(String budgetDesc) {
		this.budgetDesc = budgetDesc;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Double getExpectedIncome() {
		return expectedIncome;
	}
	public void setExpectedIncome(Double expectedIncome) {
		this.expectedIncome = expectedIncome;
	}
	public Double getActualIncome() {
		return actualIncome;
	}
	public void setActualIncome(Double actualIncome) {
		this.actualIncome = actualIncome;
	}
	public Double getExpectedExpense() {
		return expectedExpense;
	}
	public void setExpectedExpense(Double expectedExpense) {
		this.expectedExpense = expectedExpense;
	}
	public Double getActualExpense() {
		return actualExpense;
	}
	public void setActualExpense(Double actualExpense) {
		this.actualExpense = actualExpense;
	}
}
