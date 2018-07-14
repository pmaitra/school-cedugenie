package com.qts.icam.model.erp;

public class MiscellaneousTax {
	
	private int miscellaneousTaxSlabId;
	private String miscellaneousTaxSlabObjectId;
	private String updatedBy;
	private String miscellaneousTaxSlabCode;
	private String miscellaneousTaxSlabName;
	private String miscellaneousTaxSlabDesc;
	private double startSlabAmount;
	private double endSlabAmount;
	private double taxRate;
	private String miscellaneousTaxType;
	private String taxBasedOn;
	private String taxFigureType;
	
	
	public int getMiscellaneousTaxSlabId() {
		return miscellaneousTaxSlabId;
	}
	public void setMiscellaneousTaxSlabId(int miscellaneousTaxSlabId) {
		this.miscellaneousTaxSlabId = miscellaneousTaxSlabId;
	}
	public String getMiscellaneousTaxSlabObjectId() {
		return miscellaneousTaxSlabObjectId;
	}
	public void setMiscellaneousTaxSlabObjectId(String miscellaneousTaxSlabObjectId) {
		this.miscellaneousTaxSlabObjectId = miscellaneousTaxSlabObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getMiscellaneousTaxSlabCode() {
		return miscellaneousTaxSlabCode;
	}
	public void setMiscellaneousTaxSlabCode(String miscellaneousTaxSlabCode) {
		this.miscellaneousTaxSlabCode = miscellaneousTaxSlabCode;
	}
	public String getMiscellaneousTaxSlabName() {
		return miscellaneousTaxSlabName;
	}
	public void setMiscellaneousTaxSlabName(String miscellaneousTaxSlabName) {
		this.miscellaneousTaxSlabName = miscellaneousTaxSlabName;
	}
	public String getMiscellaneousTaxSlabDesc() {
		return miscellaneousTaxSlabDesc;
	}
	public void setMiscellaneousTaxSlabDesc(String miscellaneousTaxSlabDesc) {
		this.miscellaneousTaxSlabDesc = miscellaneousTaxSlabDesc;
	}
	public double getStartSlabAmount() {
		return startSlabAmount;
	}
	public void setStartSlabAmount(double startSlabAmount) {
		this.startSlabAmount = startSlabAmount;
	}
	public double getEndSlabAmount() {
		return endSlabAmount;
	}
	public void setEndSlabAmount(double endSlabAmount) {
		this.endSlabAmount = endSlabAmount;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public String getMiscellaneousTaxType() {
		return miscellaneousTaxType;
	}
	public void setMiscellaneousTaxType(String miscellaneousTaxType) {
		this.miscellaneousTaxType = miscellaneousTaxType;
	}
	public String getTaxBasedOn() {
		return taxBasedOn;
	}
	public void setTaxBasedOn(String taxBasedOn) {
		this.taxBasedOn = taxBasedOn;
	}
	public String getTaxFigureType() {
		return taxFigureType;
	}
	public void setTaxFigureType(String taxFigureType) {
		this.taxFigureType = taxFigureType;
	}
	

}
