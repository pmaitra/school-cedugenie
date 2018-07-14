package com.qts.icam.model.finance;

public class TransactionDetails {
	private int transactionDetSerialId;
	private String objectId;
	private String updatedBy;
	
	private double amount;
	private String ledger;
	private boolean isDebit;
	private Boolean isEarning;
	private int resourceId;
	private String resourceName;
	private String transactionDate;
	private String desigation;
	
	private String jobType;
	private double payableAmount;
	private double grossAmount;
	private String considerationStatus;
	private String paymentMode;
    private double onbasic;
	
	private String chequeNo;
	private String bankName;
	public int getTransactionDetSerialId() {
		return transactionDetSerialId;
	}
	public void setTransactionDetSerialId(int transactionDetSerialId) {
		this.transactionDetSerialId = transactionDetSerialId;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getLedger() {
		return ledger;
	}
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}
	public boolean isDebit() {
		return isDebit;
	}
	public void setDebit(boolean isDebit) {
		this.isDebit = isDebit;
	}
	public Boolean getIsEarning() {
		return isEarning;
	}
	public void setIsEarning(Boolean isEarning) {
		this.isEarning = isEarning;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
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
	public double getOnbasic() {
		return onbasic;
	}
	public void setOnbasic(double onbasic) {
		this.onbasic = onbasic;
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
}
