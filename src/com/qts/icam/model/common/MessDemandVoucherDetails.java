package com.qts.icam.model.common;

/**
 * @author anup.roy*/

public class MessDemandVoucherDetails {

	private String commodity;
	private String commodityUnit;
	private double commodityInQMStock;
	private double demandedQuantity;
	private double commodityRate;
	private String messDemandVoucherDetailsObjectId;
	private String updatedBy;
	private String indentSheetId;
	
	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getCommodityUnit() {
		return commodityUnit;
	}

	public void setCommodityUnit(String commodityUnit) {
		this.commodityUnit = commodityUnit;
	}

	public double getCommodityInQMStock() {
		return commodityInQMStock;
	}

	public void setCommodityInQMStock(double commodityInQMStock) {
		this.commodityInQMStock = commodityInQMStock;
	}

	public double getDemandedQuantity() {
		return demandedQuantity;
	}

	public void setDemandedQuantity(double demandedQuantity) {
		this.demandedQuantity = demandedQuantity;
	}

	public double getCommodityRate() {
		return commodityRate;
	}

	public void setCommodityRate(double commodityRate) {
		this.commodityRate = commodityRate;
	}

	public String getMessDemandVoucherDetailsObjectId() {
		return messDemandVoucherDetailsObjectId;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public String getIndentSheetId() {
		return indentSheetId;
	}

	public void setMessDemandVoucherDetailsObjectId(String messDemandVoucherDetailsObjectId) {
		this.messDemandVoucherDetailsObjectId = messDemandVoucherDetailsObjectId;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setIndentSheetId(String indentSheetId) {
		this.indentSheetId = indentSheetId;
	}
}
