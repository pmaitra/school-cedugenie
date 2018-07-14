package com.qts.icam.model.finance;

import java.util.List;

public class TransactionalWorkingArea {

	 private int transactionalWorkingAreaIdSerial ;
	  private String objectId;
	  private String updatedBy;	 
	  private String transactionalWorkingAreaCode ;
	  private String transactionalWorkingAreaName ;
	  private String transactionalWorkingAreaDesc ;
	  private String department ;
	  private String incomeExpense ;	  
	  private String resourceType ;
	  private String resource ;
	  private Integer resourceId ;
	  private String transactionDate ;
	  private String transactionYear ;
	  private String transactionMonth ;
	  private double grossAmount ;
	  private double netAmount ;
	  private String transactionMode ;
	  private String chequeNo ;
	  private String bankName ;
	  private String bankCode;
	  private String bankLocation ;
	  private String reasonOfTransaction ;
	  private String descOfTransaction ;
	  private String paidAgainst; 
	  private String transactionStatus; 
	  private double cashAmount ;
	  private double bankAmount ;
	  private String academicYear ;
	  List<TransactionWorkingAreaDetails> trWorkingDetList;
	  private String codeId ;
	  private String bankIfscCode;
	  private String bankAccountNo;
	  
	  /* added by sourav.bhadra on 21-08-2017 */
	  private boolean debit;
	  
	  
	  
	  //Added by naimisha ghosh 24042018
	  
	 private String ticketCode;
	 private String taskCode;
	 
	 
	  
	public int getTransactionalWorkingAreaIdSerial() {
		return transactionalWorkingAreaIdSerial;
	}
	public void setTransactionalWorkingAreaIdSerial(
			int transactionalWorkingAreaIdSerial) {
		this.transactionalWorkingAreaIdSerial = transactionalWorkingAreaIdSerial;
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
	public String getTransactionalWorkingAreaCode() {
		return transactionalWorkingAreaCode;
	}
	public void setTransactionalWorkingAreaCode(String transactionalWorkingAreaCode) {
		this.transactionalWorkingAreaCode = transactionalWorkingAreaCode;
	}
	public String getTransactionalWorkingAreaName() {
		return transactionalWorkingAreaName;
	}
	public void setTransactionalWorkingAreaName(String transactionalWorkingAreaName) {
		this.transactionalWorkingAreaName = transactionalWorkingAreaName;
	}
	public String getTransactionalWorkingAreaDesc() {
		return transactionalWorkingAreaDesc;
	}
	public void setTransactionalWorkingAreaDesc(String transactionalWorkingAreaDesc) {
		this.transactionalWorkingAreaDesc = transactionalWorkingAreaDesc;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getIncomeExpense() {
		return incomeExpense;
	}
	public void setIncomeExpense(String incomeExpense) {
		this.incomeExpense = incomeExpense;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionYear() {
		return transactionYear;
	}
	public void setTransactionYear(String transactionYear) {
		this.transactionYear = transactionYear;
	}
	public String getTransactionMonth() {
		return transactionMonth;
	}
	public void setTransactionMonth(String transactionMonth) {
		this.transactionMonth = transactionMonth;
	}
	public double getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
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
	public String getReasonOfTransaction() {
		return reasonOfTransaction;
	}
	public void setReasonOfTransaction(String reasonOfTransaction) {
		this.reasonOfTransaction = reasonOfTransaction;
	}
	public String getDescOfTransaction() {
		return descOfTransaction;
	}
	public void setDescOfTransaction(String descOfTransaction) {
		this.descOfTransaction = descOfTransaction;
	}	
	public String getPaidAgainst() {
		return paidAgainst;
	}
	public void setPaidAgainst(String paidAgainst) {
		this.paidAgainst = paidAgainst;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}
	public double getBankAmount() {
		return bankAmount;
	}
	public void setBankAmount(double bankAmount) {
		this.bankAmount = bankAmount;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public List<TransactionWorkingAreaDetails> getTrWorkingDetList() {
		return trWorkingDetList;
	}
	public void setTrWorkingDetList(
			List<TransactionWorkingAreaDetails> trWorkingDetList) {
		this.trWorkingDetList = trWorkingDetList;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getBankIfscCode() {
		return bankIfscCode;
	}
	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public boolean isDebit() {
		return debit;
	}
	public void setDebit(boolean debit) {
		this.debit = debit;
	}
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	
}
