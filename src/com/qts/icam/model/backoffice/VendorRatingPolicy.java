package com.qts.icam.model.backoffice;

import com.qts.icam.model.common.VendorType;

public class VendorRatingPolicy {
	
	private String vendorRatingPolicyObjectId;
	private String vendorRatingPolicyCode;
	private String vendorRatingPolicyName;
	private String vendorRatingPolicyDesc;
	private String updatedBy;
	private int maxSupplyDay;
	private int maxNoDeffects;
	private VendorType vendorType;
	private int vendorRatingPolicyId;
	
	public String getVendorRatingPolicyObjectId() {
		return vendorRatingPolicyObjectId;
	}
	public void setVendorRatingPolicyObjectId(String vendorRatingPolicyObjectId) {
		this.vendorRatingPolicyObjectId = vendorRatingPolicyObjectId;
	}
	public String getVendorRatingPolicyCode() {
		return vendorRatingPolicyCode;
	}
	public void setVendorRatingPolicyCode(String vendorRatingPolicyCode) {
		this.vendorRatingPolicyCode = vendorRatingPolicyCode;
	}
	public String getVendorRatingPolicyName() {
		return vendorRatingPolicyName;
	}
	public void setVendorRatingPolicyName(String vendorRatingPolicyName) {
		this.vendorRatingPolicyName = vendorRatingPolicyName;
	}
	public String getVendorRatingPolicyDesc() {
		return vendorRatingPolicyDesc;
	}
	public void setVendorRatingPolicyDesc(String vendorRatingPolicyDesc) {
		this.vendorRatingPolicyDesc = vendorRatingPolicyDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getMaxSupplyDay() {
		return maxSupplyDay;
	}
	public void setMaxSupplyDay(int maxSupplyDay) {
		this.maxSupplyDay = maxSupplyDay;
	}
	public int getMaxNoDeffects() {
		return maxNoDeffects;
	}
	public void setMaxNoDeffects(int maxNoDeffects) {
		this.maxNoDeffects = maxNoDeffects;
	}
	public VendorType getVendorType() {
		return vendorType;
	}
	public void setVendorType(VendorType vendorType) {
		this.vendorType = vendorType;
	}
	public int getVendorRatingPolicyId() {
		return vendorRatingPolicyId;
	}
	public void setVendorRatingPolicyId(int vendorRatingPolicyId) {
		this.vendorRatingPolicyId = vendorRatingPolicyId;
	}

}
