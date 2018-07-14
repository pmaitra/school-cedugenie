package com.qts.icam.model.common;

import java.util.List;

public class Condemnation {
	private int condemnationId;
	private String objectId;
	private String updatedBy;
	private String condemnationCode;
	private String condemnationName;
	private String condemnationDesc;
	private String year;
	private Department department;
	private AnnualStock annualStock;
	private String lpValue;
	private String itemName;
	private String unit;	
	private int qtyProducedForCB;
	private int qtySentencedToUNS;
	private String dateOfPurchase;
	private double rate;
	private double costAmount;
	private String remarks;
	private List<CondemnationDetails> condemnationDetailsList;
//	private String ledgerBalance;
//	private String groundBalance;
//	private String surplus;
//	private String deficient;
//	private String serviceable;
//	private String repairable;	
//	private double qtySentencedToSer;
//	private double qtySentencedToRep;
	

	public int getCondemnationId() {
		return condemnationId;
	}
	public void setCondemnationId(int condemnationId) {
		this.condemnationId = condemnationId;
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
	public String getCondemnationCode() {
		return condemnationCode;
	}
	public void setCondemnationCode(String condemnationCode) {
		this.condemnationCode = condemnationCode;
	}
	public String getCondemnationName() {
		return condemnationName;
	}
	public void setCondemnationName(String condemnationName) {
		this.condemnationName = condemnationName;
	}
	public String getCondemnationDesc() {
		return condemnationDesc;
	}
	public void setCondemnationDesc(String condemnationDesc) {
		this.condemnationDesc = condemnationDesc;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public AnnualStock getAnnualStock() {
		return annualStock;
	}
	public void setAnnualStock(AnnualStock annualStock) {
		this.annualStock = annualStock;
	}
	public String getLpValue() {
		return lpValue;
	}
	public void setLpValue(String lpValue) {
		this.lpValue = lpValue;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getQtyProducedForCB() {
		return qtyProducedForCB;
	}
	public void setQtyProducedForCB(int qtyProducedForCB) {
		this.qtyProducedForCB = qtyProducedForCB;
	}
	public int getQtySentencedToUNS() {
		return qtySentencedToUNS;
	}
	public void setQtySentencedToUNS(int qtySentencedToUNS) {
		this.qtySentencedToUNS = qtySentencedToUNS;
	}
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getCostAmount() {
		return costAmount;
	}
	public void setCostAmount(double costAmount) {
		this.costAmount = costAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	
	public List<CondemnationDetails> getCondemnationDetailsList() {
		return condemnationDetailsList;
	}
	public void setCondemnationDetailsList(List<CondemnationDetails> condemnationDetailsList) {
		this.condemnationDetailsList = condemnationDetailsList;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
}
