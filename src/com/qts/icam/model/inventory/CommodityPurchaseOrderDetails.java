package com.qts.icam.model.inventory;

public class CommodityPurchaseOrderDetails {
	
	private String objId;
	private String updatedBy;	
	private String commodity;	
	private String service;	
	private double qtyOrdered;
	private double rate;
	private double discount;
	private double amount;	
	private double qtyDeficit;
	private double qtyDefect;
	private double qtyReceived;
	private double damage;	
	private double increaseInStock;	
	private String receiveStatus;	
	private String commodityPurchaseOrderCode;	
	private String vendorCode;	
	private String expenceDesc;
	private String paymentType;
	private Double expenceAmount;
	
	//Added  by naimisha 09052018
	
	private String tickeCode;
	private String taskCode;
	
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public double getQtyOrdered() {
		return qtyOrdered;
	}

	public void setQtyOrdered(double qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getQtyDeficit() {
		return qtyDeficit;
	}

	public void setQtyDeficit(double qtyDeficit) {
		this.qtyDeficit = qtyDeficit;
	}

	public double getQtyDefect() {
		return qtyDefect;
	}

	public void setQtyDefect(double qtyDefect) {
		this.qtyDefect = qtyDefect;
	}

	public double getQtyReceived() {
		return qtyReceived;
	}

	public void setQtyReceived(double qtyReceived) {
		this.qtyReceived = qtyReceived;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getIncreaseInStock() {
		return increaseInStock;
	}

	public void setIncreaseInStock(double increaseInStock) {
		this.increaseInStock = increaseInStock;
	}

	public String getReceiveStatus() {
		return receiveStatus;
	}

	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	public String getCommodityPurchaseOrderCode() {
		return commodityPurchaseOrderCode;
	}

	public void setCommodityPurchaseOrderCode(String commodityPurchaseOrderCode) {
		this.commodityPurchaseOrderCode = commodityPurchaseOrderCode;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getExpenceDesc() {
		return expenceDesc;
	}

	public void setExpenceDesc(String expenceDesc) {
		this.expenceDesc = expenceDesc;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Double getExpenceAmount() {
		return expenceAmount;
	}

	public void setExpenceAmount(Double expenceAmount) {
		this.expenceAmount = expenceAmount;
	}

	public String getTickeCode() {
		return tickeCode;
	}

	public void setTickeCode(String tickeCode) {
		this.tickeCode = tickeCode;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	
}
