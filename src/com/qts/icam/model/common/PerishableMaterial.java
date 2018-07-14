package com.qts.icam.model.common;

import java.util.List;

public class PerishableMaterial {
	private String objectId;
	private String updatedBy;
	private String commodityName;
	private String commodityUnit;
	private String commodityQuantity;
	private String orderId;
	private String academicsession;
	private String financialSession;
	private List<PerishableMaterial> perishableMaterialsList;
	private String orderNumber;
	private String openDate;
	private String closeDate;
	
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
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityUnit() {
		return commodityUnit;
	}
	public void setCommodityUnit(String commodityUnit) {
		this.commodityUnit = commodityUnit;
	}
	public String getCommodityQuantity() {
		return commodityQuantity;
	}
	public void setCommodityQuantity(String commodityQuantity) {
		this.commodityQuantity = commodityQuantity;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAcademicsession() {
		return academicsession;
	}
	public void setAcademicsession(String academicsession) {
		this.academicsession = academicsession;
	}
	public String getFinancialSession() {
		return financialSession;
	}
	public void setFinancialSession(String financialSession) {
		this.financialSession = financialSession;
	}
	public List<PerishableMaterial> getPerishableMaterialsList() {
		return perishableMaterialsList;
	}
	public void setPerishableMaterialsList(List<PerishableMaterial> perishableMaterialsList) {
		this.perishableMaterialsList = perishableMaterialsList;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	
	
}
