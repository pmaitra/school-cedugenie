package com.qts.icam.model.common;

/**
 * @author anup.roy
 * */

public class CommodityReceiveVoucherDetails {

	private String commodityReceiveVoucherDetailsObjectId;
	private String commodityName;
	private double issuedQuantityToMess;
	private String updatedBy;
	private String receiveVoucherCode;
	private int lpNo;
	
	public String getCommodityReceiveVoucherDetailsObjectId() {
		return commodityReceiveVoucherDetailsObjectId;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public double getIssuedQuantityToMess() {
		return issuedQuantityToMess;
	}
	public void setCommodityReceiveVoucherDetailsObjectId(String commodityReceiveVoucherDetailsObjectId) {
		this.commodityReceiveVoucherDetailsObjectId = commodityReceiveVoucherDetailsObjectId;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public void setIssuedQuantityToMess(double issuedQuantityToMess) {
		this.issuedQuantityToMess = issuedQuantityToMess;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getReceiveVoucherCode() {
		return receiveVoucherCode;
	}
	public void setReceiveVoucherCode(String receiveVoucherCode) {
		this.receiveVoucherCode = receiveVoucherCode;
	}
	/**
	 * @return the lpNo
	 */
	public int getLpNo() {
		return lpNo;
	}
	/**
	 * @param lpNo the lpNo to set
	 */
	public void setLpNo(int lpNo) {
		this.lpNo = lpNo;
	}
	
}
