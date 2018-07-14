package com.qts.icam.model.common;

/**
 * @author anup.roy
 * */

public class CommodityIssueVoucherDetails {

	private String commodityIssueVoucherDetailsObjectId;
	private String updatedBy;
	private String commodityName;
	private double issuedQuantityToMess;
	private String voucherCode;
	private MessDemandVoucherDetails messDemandVoucherDetails;
	
	public String getCommodityIssueVoucherDetailsObjectId() {
		return commodityIssueVoucherDetailsObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public double getIssuedQuantityToMess() {
		return issuedQuantityToMess;
	}
	public void setCommodityIssueVoucherDetailsObjectId(String commodityIssueVoucherDetailsObjectId) {
		this.commodityIssueVoucherDetailsObjectId = commodityIssueVoucherDetailsObjectId;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public void setIssuedQuantityToMess(double issuedQuantityToMess) {
		this.issuedQuantityToMess = issuedQuantityToMess;
	}
	public String getVoucherCode() {
		return voucherCode;
	}
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
	/**
	 * @return the messDemandVoucherDetails
	 */
	public MessDemandVoucherDetails getMessDemandVoucherDetails() {
		return messDemandVoucherDetails;
	}
	/**
	 * @param messDemandVoucherDetails the messDemandVoucherDetails to set
	 */
	public void setMessDemandVoucherDetails(MessDemandVoucherDetails messDemandVoucherDetails) {
		this.messDemandVoucherDetails = messDemandVoucherDetails;
	}
	
	
}
