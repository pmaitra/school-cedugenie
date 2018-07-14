package com.qts.icam.model.finance;

public class Passbook {
	private int passbookSerialId;
	
	private String objectId;
	private String updatedBy;
	
	private String bank;	
	private String date;
	private String particulars;
	private boolean withdraw;
	private String instrumentNumber;
	private String instrumentDate;
	private boolean debit;
	private double balance;
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public int getPassbookSerialId() {
		return passbookSerialId;
	}
	public void setPassbookSerialId(int passbookSerialId) {
		this.passbookSerialId = passbookSerialId;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getParticulars() {
		return particulars;
	}
	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}
	public boolean isWithdraw() {
		return withdraw;
	}
	public void setWithdraw(boolean withdraw) {
		this.withdraw = withdraw;
	}
	public String getInstrumentNumber() {
		return instrumentNumber;
	}
	public void setInstrumentNumber(String instrumentNumber) {
		this.instrumentNumber = instrumentNumber;
	}
	public String getInstrumentDate() {
		return instrumentDate;
	}
	public void setInstrumentDate(String instrumentDate) {
		this.instrumentDate = instrumentDate;
	}
	public boolean isDebit() {
		return debit;
	}
	public void setDebit(boolean debit) {
		this.debit = debit;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
