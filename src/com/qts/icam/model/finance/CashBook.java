package com.qts.icam.model.finance;

import java.util.List;

public class CashBook {
	String ledger;
	Double amount;
	String cashBank;
	String dateOfCreation;
	public List<CashBook>cashBookDebitList;
	public List<CashBook>cashBookCreditList;
	private Boolean debit;
	private Double openingBalance;
	private Double currentBalance;
	
	public String getLedger() {
		return ledger;
	}
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCashBank() {
		return cashBank;
	}
	public void setCashBank(String cashBank) {
		this.cashBank = cashBank;
	}
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	public List<CashBook> getCashBookDebitList() {
		return cashBookDebitList;
	}
	public void setCashBookDebitList(List<CashBook> cashBookDebitList) {
		this.cashBookDebitList = cashBookDebitList;
	}
	public List<CashBook> getCashBookCreditList() {
		return cashBookCreditList;
	}
	public void setCashBookCreditList(List<CashBook> cashBookCreditList) {
		this.cashBookCreditList = cashBookCreditList;
	}
	public Boolean getDebit() {
		return debit;
	}
	public void setDebit(Boolean debit) {
		this.debit = debit;
	}
	public Double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public Double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}
}
