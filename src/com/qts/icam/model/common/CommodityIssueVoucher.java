package com.qts.icam.model.common;

import java.util.List;

/**
 * @author anup.roy*/

public class CommodityIssueVoucher{
	
	private String commodityIssueVoucherObjectId;
	private String updatedBy;
	private String commodityIssueVoucherCode;
	private MessDemandVoucher messDemanVoucher;
	private List<CommodityIssueVoucherDetails> commodityIssueVoucherDetailsList;
	
	public String getCommodityIssueVoucherObjectId() {
		return commodityIssueVoucherObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public String getCommodityIssueVoucherCode() {
		return commodityIssueVoucherCode;
	}
	public MessDemandVoucher getMessDemanVoucher() {
		return messDemanVoucher;
	}
	
	public List<CommodityIssueVoucherDetails> getCommodityIssueVoucherDetailsList() {
		return commodityIssueVoucherDetailsList;
	}
	public void setCommodityIssueVoucherDetailsList(List<CommodityIssueVoucherDetails> commodityIssueVoucherDetailsList) {
		this.commodityIssueVoucherDetailsList = commodityIssueVoucherDetailsList;
	}
	public void setCommodityIssueVoucherObjectId(String commodityIssueVoucherObjectId) {
		this.commodityIssueVoucherObjectId = commodityIssueVoucherObjectId;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public void setCommodityIssueVoucherCode(String commodityIssueVoucherCode) {
		this.commodityIssueVoucherCode = commodityIssueVoucherCode;
	}
	public void setMessDemanVoucher(MessDemandVoucher messDemanVoucher) {
		this.messDemanVoucher = messDemanVoucher;
	}
	
	
}
