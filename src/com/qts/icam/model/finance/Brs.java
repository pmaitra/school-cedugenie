package com.qts.icam.model.finance;

public class Brs {	
	String chequeNumber;
	Double amount;
	Boolean debit;
	String date;
	String voucherType;
	String voucherNumber;
	String narration;
	
	String entryDate;
	String instrumentDate;
	Boolean withdraw;
	
	String transactionPassbook;
	
	
	public String getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
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
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getInstrumentDate() {
		return instrumentDate;
	}
	public void setInstrumentDate(String instrumentDate) {
		this.instrumentDate = instrumentDate;
	}
	public Boolean getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(Boolean withdraw) {
		this.withdraw = withdraw;
	}
	public String getTransactionPassbook() {
		return transactionPassbook;
	}
	public void setTransactionPassbook(String transactionPassbook) {
		this.transactionPassbook = transactionPassbook;
	}
}
