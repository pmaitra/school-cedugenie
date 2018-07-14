package com.qts.icam.model.common;

public class ProfitAndLoss {

	private String ledger;
	private Double amount;
	private String incomeExpense;
	
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
	public String getIncomeExpense() {
		return incomeExpense;
	}
	public void setIncomeExpense(String incomeExpense) {
		this.incomeExpense = incomeExpense;
	}
}
