package com.qts.icam.model.common;

/**
 * @author anup.roy
 * */
public class MessDailyConsumptionDetails {

	private String updatedBy;
	private String messDailyConsumptionDetailsObjectId;
	private String lpNo;
	private String commodityType;
	private String commodityName;
	private String commodityAU;
	private double oldStockInForIssue;
	private double rate;
	private double issuingQuantity;
	private double amount;
	private String messDailyConsumptionCode;
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getMessDailyConsumptionDetailsObjectId() {
		return messDailyConsumptionDetailsObjectId;
	}
	public void setMessDailyConsumptionDetailsObjectId(String messDailyConsumptionDetailsObjectId) {
		this.messDailyConsumptionDetailsObjectId = messDailyConsumptionDetailsObjectId;
	}
	public String getLpNo() {
		return lpNo;
	}
	public void setLpNo(String lpNo) {
		this.lpNo = lpNo;
	}
	public String getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityAU() {
		return commodityAU;
	}
	public void setCommodityAU(String commodityAU) {
		this.commodityAU = commodityAU;
	}
	public double getOldStockInForIssue() {
		return oldStockInForIssue;
	}
	public void setOldStockInForIssue(double oldStockInForIssue) {
		this.oldStockInForIssue = oldStockInForIssue;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getIssuingQuantity() {
		return issuingQuantity;
	}
	public void setIssuingQuantity(double issuingQuantity) {
		this.issuingQuantity = issuingQuantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the messDailyConsumptionCode
	 */
	public String getMessDailyConsumptionCode() {
		return messDailyConsumptionCode;
	}
	/**
	 * @param messDailyConsumptionCode the messDailyConsumptionCode to set
	 */
	public void setMessDailyConsumptionCode(String messDailyConsumptionCode) {
		this.messDailyConsumptionCode = messDailyConsumptionCode;
	}
}
