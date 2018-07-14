package com.qts.icam.model.inventory;

public class IndividualCommodity {

	private int individualCommodityId;
	private String objectId;
	private String updatedBy;
	private String updatedOn;
	
	private String individualCommodityCode;
		
	private String purchaseDate;
	private String expieryDate;
	
	private String status;
	private String vendorCode;
	private String commodityCode;
	
	private String allotedTo;
	private String allotedBy;
	private String allotedOn;
	private String returnedOn;
	private String returnedTo;
	private String comment;

	private String modelNo;
	private double warranty;
	private double depreciation;


	
	public int getIndividualCommodityId() {
		return individualCommodityId;
	}
	public void setIndividualCommodityId(int individualCommodityId) {
		this.individualCommodityId = individualCommodityId;
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
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getIndividualCommodityCode() {
		return individualCommodityCode;
	}
	public void setIndividualCommodityCode(String individualCommodityCode) {
		this.individualCommodityCode = individualCommodityCode;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getExpieryDate() {
		return expieryDate;
	}
	public void setExpieryDate(String expieryDate) {
		this.expieryDate = expieryDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}
	public String getAllotedTo() {
		return allotedTo;
	}
	public void setAllotedTo(String allotedTo) {
		this.allotedTo = allotedTo;
	}
	public String getAllotedBy() {
		return allotedBy;
	}
	public void setAllotedBy(String allotedBy) {
		this.allotedBy = allotedBy;
	}
	public String getAllotedOn() {
		return allotedOn;
	}
	public void setAllotedOn(String allotedOn) {
		this.allotedOn = allotedOn;
	}
	public String getReturnedOn() {
		return returnedOn;
	}
	public void setReturnedOn(String returnedOn) {
		this.returnedOn = returnedOn;
	}
	public String getReturnedTo() {
		return returnedTo;
	}
	public void setReturnedTo(String returnedTo) {
		this.returnedTo = returnedTo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public double getWarranty() {
		return warranty;
	}
	public void setWarranty(double warranty) {
		this.warranty = warranty;
	}
	public double getDepreciation() {
		return depreciation;
	}
	public void setDepreciation(double depreciation) {
		this.depreciation = depreciation;
	}
}
