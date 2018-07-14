package com.qts.icam.model.finance;

public class FeesLedgerMapping {
	private int serialId;
	private String objectId;
	private String updatedBy;
	private String status;
	
	private String feesCode;
	private String ledger;
	
	
	
	public int getSerialId() {
		return serialId;
	}
	public void setSerialId(int serialId) {
		this.serialId = serialId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFeesCode() {
		return feesCode;
	}
	public void setFeesCode(String feesCode) {
		this.feesCode = feesCode;
	}
	public String getLedger() {
		return ledger;
	}
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}
}
