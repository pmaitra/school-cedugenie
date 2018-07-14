package com.qts.icam.model.academics;

import com.qts.icam.model.common.Asset;

public class AssetConsumption {
	
	private long assetConsumptionId;
	private String objectId;
	private String updatedBy;
	private String startDate;
	private String endDate;
	private Asset asset;
	private double consumedQuantity;
	private String status;
	private String unit;
	
	public long getAssetConsumptionId() {
		return assetConsumptionId;
	}
	public void setAssetConsumptionId(long assetConsumptionId) {
		this.assetConsumptionId = assetConsumptionId;
	}
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public double getConsumedQuantity() {
		return consumedQuantity;
	}
	public void setConsumedQuantity(double consumedQuantity) {
		this.consumedQuantity = consumedQuantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	

}
