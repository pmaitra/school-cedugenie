package com.qts.icam.model.common;

import java.util.List;

/**
 * @author anup.roy
 * */

public class MessDemandVoucher {

	private String demandVoucherId;
	private String demandVoucherObjectId;
	private String updatedBy;
	private List<MessDemandVoucherDetails> messDemandVoucherDetailsList;
	private String demandVoucherStatus;
	private String demandVoucherOpenDate;
	private String demandVoucherCloseDate;
	private CommodityReceiveVoucher commodityReceiveVoucher;

	public String getDemandVoucherId() {
		return demandVoucherId;
	}

	public void setDemandVoucherId(String demandVoucherId) {
		this.demandVoucherId = demandVoucherId;
	}

	public String getDemandVoucherObjectId() {
		return demandVoucherObjectId;
	}

	public void setDemandVoucherObjectId(String demandVoucherObjectId) {
		this.demandVoucherObjectId = demandVoucherObjectId;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<MessDemandVoucherDetails> getMessDemandVoucherDetailsList() {
		return messDemandVoucherDetailsList;
	}

	public void setMessDemandVoucherDetailsList(List<MessDemandVoucherDetails> messDemandVoucherDetailsList) {
		this.messDemandVoucherDetailsList = messDemandVoucherDetailsList;
	}

	public String getDemandVoucherStatus() {
		return demandVoucherStatus;
	}

	public String getDemandVoucherOpenDate() {
		return demandVoucherOpenDate;
	}

	public void setDemandVoucherStatus(String demandVoucherStatus) {
		this.demandVoucherStatus = demandVoucherStatus;
	}

	public void setDemandVoucherOpenDate(String demandVoucherOpenDate) {
		this.demandVoucherOpenDate = demandVoucherOpenDate;
	}

	public String getDemandVoucherCloseDate() {
		return demandVoucherCloseDate;
	}

	public void setDemandVoucherCloseDate(String demandVoucherCloseDate) {
		this.demandVoucherCloseDate = demandVoucherCloseDate;
	}

	public CommodityReceiveVoucher getCommodityReceiveVoucher() {
		return commodityReceiveVoucher;
	}

	public void setCommodityReceiveVoucher(CommodityReceiveVoucher commodityReceiveVoucher) {
		this.commodityReceiveVoucher = commodityReceiveVoucher;
	}
}
