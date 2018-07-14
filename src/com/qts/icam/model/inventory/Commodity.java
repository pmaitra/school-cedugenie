package com.qts.icam.model.inventory;

public class Commodity {

	private int commodityId;
	private String objectId;
	private String updatedBy;
	
	private String commodityCode;
	private String commodityName;
	private String commodityDesc;
	
	private String commodityType;
	private String commoditySubType;
	
	private double inStock;
	private double threshold;
	
	private double purchaseRate;
	private double sellingRate;
	private double oldSellingRate;
	
	private String vendor;
	private String vendorCode;
	
	private String date;
	
	private String modelNo;
	private double warranty;
	
	private String unit;
	private String quantity;
	
	public int getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
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
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityDesc() {
		return commodityDesc;
	}
	public void setCommodityDesc(String commodityDesc) {
		this.commodityDesc = commodityDesc;
	}
	public String getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}
	public String getCommoditySubType() {
		return commoditySubType;
	}
	public void setCommoditySubType(String commoditySubType) {
		this.commoditySubType = commoditySubType;
	}
	public double getInStock() {
		return inStock;
	}
	public void setInStock(double inStock) {
		this.inStock = inStock;
	}
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	public double getPurchaseRate() {
		return purchaseRate;
	}
	public void setPurchaseRate(double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}
	public double getSellingRate() {
		return sellingRate;
	}
	public void setSellingRate(double sellingRate) {
		this.sellingRate = sellingRate;
	}
	public double getOldSellingRate() {
		return oldSellingRate;
	}
	public void setOldSellingRate(double oldSellingRate) {
		this.oldSellingRate = oldSellingRate;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
