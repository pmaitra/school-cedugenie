package com.qts.icam.model.finance;


public class Ledger {
	
	private int ledgerSerialId;
	private String objectId;
	private String updatedBy;

	private String ledgerCode;
	private String ledgerName;
	
	private String parentLedgerCode;	
	private String parentLedgerName;
	
	private String parentGroupCode;
	private String parentGroupName;
	
	private double openingBal;
	private double currentBal;
	private double closingBal;
	private String openingDrCr;
	private String currentDrCr;	
	private int count;
	
	
	public int getLedgerSerialId() {
		return ledgerSerialId;
	}
	public void setLedgerSerialId(int ledgerSerialId) {
		this.ledgerSerialId = ledgerSerialId;
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
	public String getLedgerCode() {
		return ledgerCode;
	}
	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}
	public String getLedgerName() {
		return ledgerName;
	}
	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}
	public String getParentLedgerCode() {
		return parentLedgerCode;
	}
	public void setParentLedgerCode(String parentLedgerCode) {
		this.parentLedgerCode = parentLedgerCode;
	}
	public String getParentLedgerName() {
		return parentLedgerName;
	}
	public void setParentLedgerName(String parentLedgerName) {
		this.parentLedgerName = parentLedgerName;
	}
	public String getParentGroupCode() {
		return parentGroupCode;
	}
	public void setParentGroupCode(String parentGroupCode) {
		this.parentGroupCode = parentGroupCode;
	}
	public String getParentGroupName() {
		return parentGroupName;
	}
	public void setParentGroupName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}
	
	
	public double getCurrentBal() {
		return currentBal;
	}
	public double getOpeningBal() {
		return openingBal;
	}
	public void setOpeningBal(double openingBal) {
		this.openingBal = openingBal;
	}
	public void setCurrentBal(double currentBal) {
		this.currentBal = currentBal;
	}
	public double getClosingBal() {
		return closingBal;
	}
	public void setClosingBal(double closingBal) {
		this.closingBal = closingBal;
	}
	public String getOpeningDrCr() {
		return openingDrCr;
	}
	public void setOpeningDrCr(String openingDrCr) {
		this.openingDrCr = openingDrCr;
	}
	public String getCurrentDrCr() {
		return currentDrCr;
	}
	public void setCurrentDrCr(String currentDrCr) {
		this.currentDrCr = currentDrCr;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
