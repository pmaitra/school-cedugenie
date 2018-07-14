package com.qts.icam.model.common;

import java.util.List;

/**
 * @author anup.roy
 * */
public class CommodityReceiveVoucher {

	private String commodityReceiveVoucherObjectId;
	private String commodityReceiveVoucherCode;
	private String commodityIssueVoucherCode;
	private String indentSheetId;
	private List<CommodityReceiveVoucherDetails> commodityReceiveVoucherDetailsList;
	private String updatedBy;
	
	public String getCommodityReceiveVoucherObjectId() {
		return commodityReceiveVoucherObjectId;
	}
	public String getCommodityReceiveVoucherCode() {
		return commodityReceiveVoucherCode;
	}
	public String getCommodityIssueVoucherCode() {
		return commodityIssueVoucherCode;
	}
	public String getIndentSheetId() {
		return indentSheetId;
	}
	public void setCommodityReceiveVoucherObjectId(String commodityReceiveVoucherObjectId) {
		this.commodityReceiveVoucherObjectId = commodityReceiveVoucherObjectId;
	}
	public void setCommodityReceiveVoucherCode(String commodityReceiveVoucherCode) {
		this.commodityReceiveVoucherCode = commodityReceiveVoucherCode;
	}
	public void setCommodityIssueVoucherCode(String commodityIssueVoucherCode) {
		this.commodityIssueVoucherCode = commodityIssueVoucherCode;
	}
	public void setIndentSheetId(String indentSheetId) {
		this.indentSheetId = indentSheetId;
	}
	public List<CommodityReceiveVoucherDetails> getCommodityReceiveVoucherDetailsList() {
		return commodityReceiveVoucherDetailsList;
	}
	public void setCommodityReceiveVoucherDetailsList(
			List<CommodityReceiveVoucherDetails> commodityReceiveVoucherDetailsList) {
		this.commodityReceiveVoucherDetailsList = commodityReceiveVoucherDetailsList;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
