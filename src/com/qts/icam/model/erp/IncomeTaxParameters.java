package com.qts.icam.model.erp;

public class IncomeTaxParameters {
	
	private String incomeTaxParameterObjectId;
	private String updatedBy;
	private String incomeTaxParamCode;
	private String incomeTaxParamName;
	private String incomeTaxParamDesc;
	private double amount;
	private double figure;
	private String figureType;
	private int taxId;
	private String incomeAge;
	
	public String getIncomeTaxParameterObjectId() {
		return incomeTaxParameterObjectId;
	}
	public void setIncomeTaxParameterObjectId(String incomeTaxParameterObjectId) {
		this.incomeTaxParameterObjectId = incomeTaxParameterObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getIncomeTaxParamCode() {
		return incomeTaxParamCode;
	}
	public void setIncomeTaxParamCode(String incomeTaxParamCode) {
		this.incomeTaxParamCode = incomeTaxParamCode;
	}
	public String getIncomeTaxParamName() {
		return incomeTaxParamName;
	}
	public void setIncomeTaxParamName(String incomeTaxParamName) {
		this.incomeTaxParamName = incomeTaxParamName;
	}
	public String getIncomeTaxParamDesc() {
		return incomeTaxParamDesc;
	}
	public void setIncomeTaxParamDesc(String incomeTaxParamDesc) {
		this.incomeTaxParamDesc = incomeTaxParamDesc;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public double getFigure() {
		return figure;
	}
	public void setFigure(double figure) {
		this.figure = figure;
	}
	public String getFigureType() {
		return figureType;
	}
	public void setFigureType(String figureType) {
		this.figureType = figureType;
	}
	public String getIncomeAge() {
		return incomeAge;
	}
	public void setIncomeAge(String incomeAge) {
		this.incomeAge = incomeAge;
	}	
	
	
}
