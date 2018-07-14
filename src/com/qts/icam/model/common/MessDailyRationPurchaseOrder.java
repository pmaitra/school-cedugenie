package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.inventory.Commodity;

/**
 * @author anup.roy
 * */
public class MessDailyRationPurchaseOrder {

	private String messDailyRationPurchaseOrderObjId;
	private String updatedBy;
	private String messDailyRationPurchaseOrderCode;
	private Vendor vendor;
	private List<MessDailyRationPurchaseOrderDetails> messDailyRationPoDetailsList;
	private String inventorySession;
	
	/** added by @author Sourav.Bhadra on 24-10-2017 **/
	private String requisitionId;
	
	/** added by @author Sourav.Bhadra on 06-03-2018 **/
	private double totalAmount;
	
	public String getMessDailyRationPurchaseOrderObjId() {
		return messDailyRationPurchaseOrderObjId;
	}
	public void setMessDailyRationPurchaseOrderObjId(String messDailyRationPurchaseOrderObjId) {
		this.messDailyRationPurchaseOrderObjId = messDailyRationPurchaseOrderObjId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getMessDailyRationPurchaseOrderCode() {
		return messDailyRationPurchaseOrderCode;
	}
	public void setMessDailyRationPurchaseOrderCode(String messDailyRationPurchaseOrderCode) {
		this.messDailyRationPurchaseOrderCode = messDailyRationPurchaseOrderCode;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public List<MessDailyRationPurchaseOrderDetails> getMessDailyRationPoDetailsList() {
		return messDailyRationPoDetailsList;
	}
	public void setMessDailyRationPoDetailsList(List<MessDailyRationPurchaseOrderDetails> messDailyRationPoDetailsList) {
		this.messDailyRationPoDetailsList = messDailyRationPoDetailsList;
	}
	public String getInventorySession() {
		return inventorySession;
	}
	public void setInventorySession(String inventorySession) {
		this.inventorySession = inventorySession;
	}
	public String getRequisitionId() {
		return requisitionId;
	}
	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
