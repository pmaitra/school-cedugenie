package com.qts.icam.model.common;

public class Daybook {
	String ledgerCode;
	Double amount;
	Boolean debit;
	String date;	
	String voucherType;
	String voucherNumber;	
	String narration;
	/**Addeb by ranita.sur on 08/08/2017 for edit daybook details**/
	private String updatedBy;	
	private String transactionId;
	private int transactionSerialId;
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Boolean getDebit() {
		return debit;
	}
	public void setDebit(Boolean debit) {
		this.debit = debit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getLedgerCode() {
		return ledgerCode;
	}
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public int getTransactionSerialId() {
		return transactionSerialId;
	}
	public void setTransactionSerialId(int transactionSerialId) {
		this.transactionSerialId = transactionSerialId;
	}
	
}
