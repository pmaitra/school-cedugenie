package com.qts.icam.model.common;

import com.qts.icam.model.inventory.Commodity;

public class AnnualStock {
	
	private int annualStockId;
	private String objectId;
	private String updatedBy;
	private Asset asset;
	private Commodity commodity;
	private String ledgerPage;
	private int groundBalance;
	private int surplus;
	private int deficient;
	private int serviceable;
	private int repairable;
	private boolean condemnation;
	private String remarks;
	private String quantity;
	private String status;
	private int ledgerNumber;
	private int pageNumber;
	
	public int getAnnualStockId() {
		return annualStockId;
	}
	public void setAnnualStockId(int annualStockId) {
		this.annualStockId = annualStockId;
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
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public String getLedgerPage() {
		return ledgerPage;
	}
	public void setLedgerPage(String ledgerPage) {
		this.ledgerPage = ledgerPage;
	}
	public int getGroundBalance() {
		return groundBalance;
	}
	public void setGroundBalance(int groundBalance) {
		this.groundBalance = groundBalance;
	}
	public int getSurplus() {
		return surplus;
	}
	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}
	public int getDeficient() {
		return deficient;
	}
	public void setDeficient(int deficient) {
		this.deficient = deficient;
	}
	public int getServiceable() {
		return serviceable;
	}
	public void setServiceable(int serviceable) {
		this.serviceable = serviceable;
	}
	public int getRepairable() {
		return repairable;
	}
	public void setRepairable(int repairable) {
		this.repairable = repairable;
	}
	public boolean isCondemnation() {
		return condemnation;
	}
	public void setCondemnation(boolean condemnation) {
		this.condemnation = condemnation;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public int getLedgerNumber() {
		return ledgerNumber;
	}
	public void setLedgerNumber(int ledgerNumber) {
		this.ledgerNumber = ledgerNumber;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	

}
