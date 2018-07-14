package com.qts.icam.model.common;

import com.qts.icam.model.inventory.Commodity;

/**
 * @author anup.roy
 * */
public class MessDailyRationPurchaseOrderDetails {

	private String messDailyRationPoDetailsObjId;
	private String updatedBy;
	private String messDailyRationPoDetailsCode;
	private double quantity;
	private double rate;
	private Commodity commodity;
	private String unit;
	/** added by @author Sourav.Bhadra on 06-03-2018 **/
	private double totalPrice;
	
	public String getMessDailyRationPoDetailsObjId() {
		return messDailyRationPoDetailsObjId;
	}
	public void setMessDailyRationPoDetailsObjId(String messDailyRationPoDetailsObjId) {
		this.messDailyRationPoDetailsObjId = messDailyRationPoDetailsObjId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getMessDailyRationPoDetailsCode() {
		return messDailyRationPoDetailsCode;
	}
	public void setMessDailyRationPoDetailsCode(String messDailyRationPoDetailsCode) {
		this.messDailyRationPoDetailsCode = messDailyRationPoDetailsCode;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
