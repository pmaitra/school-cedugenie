package com.qts.icam.model.finance;

import java.util.List;

public class Transaction {
	private int transactionSerialId;
	private String objectId;
	private String updatedBy;
	private String transactionCode;
	private String voucherTypeName;
	private String voucherTypeCode;
    private String desigation;
	private String jobType;
	private double payableAmount;
	private double grossAmount;
	private String considerationStatus;
	private String paymentMode;
	private String chequeNo;
	private String bankName;
	private String resourceId;
	private String resourceName;
	private String transactionDate;
	private double taxAmount;
	private String narration;
	List<TransactionDetails> trDetList;
	private String workingAreaCode;
	private String incomeExpense;
	private String department;
	private String bankAccountNo;
	private String bankCode;
	private String bankLocation;
	private String bankIFSCCode;
	
	public int getTransactionSerialId() {
		return transactionSerialId;
	}
	public void setTransactionSerialId(int transactionSerialId) {
		this.transactionSerialId = transactionSerialId;
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
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getVoucherTypeName() {
		return voucherTypeName;
	}
	public void setVoucherTypeName(String voucherTypeName) {
		this.voucherTypeName = voucherTypeName;
	}
	public String getVoucherTypeCode() {
		return voucherTypeCode;
	}
	public void setVoucherTypeCode(String voucherTypeCode) {
		this.voucherTypeCode = voucherTypeCode;
	}
	public String getDesigation() {
		return desigation;
	}
	public void setDesigation(String desigation) {
		this.desigation = desigation;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public double getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}
	public double getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public String getConsiderationStatus() {
		return considerationStatus;
	}
	public void setConsiderationStatus(String considerationStatus) {
		this.considerationStatus = considerationStatus;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public List<TransactionDetails> getTrDetList() {
		return trDetList;
	}
	public void setTrDetList(List<TransactionDetails> trDetList) {
		this.trDetList = trDetList;
	}
	public String getWorkingAreaCode() {
		return workingAreaCode;
	}
	public void setWorkingAreaCode(String workingAreaCode) {
		this.workingAreaCode = workingAreaCode;
	}
	public String getIncomeExpense() {
		return incomeExpense;
	}
	public void setIncomeExpense(String incomeExpense) {
		this.incomeExpense = incomeExpense;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankLocation() {
		return bankLocation;
	}
	public void setBankLocation(String bankLocation) {
		this.bankLocation = bankLocation;
	}
	public String getBankIFSCCode() {
		return bankIFSCCode;
	}
	public void setBankIFSCCode(String bankIFSCCode) {
		this.bankIFSCCode = bankIFSCCode;
	}	
}
