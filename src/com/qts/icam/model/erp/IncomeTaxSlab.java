package com.qts.icam.model.erp;

import java.util.List;

public class IncomeTaxSlab {
	private int taxSlabId;
	private String incomeTaxSlabObjectId;
	private String updatedBy;
	private String incomeTaxSlabCode;
	private String incomeTaxSlabName;
	private String incomeTaxSlabDesc;	
	private List<IncomeTaxParameters> incomeTaxParameterList;
	
	public int getTaxSlabId() {
		return taxSlabId;
	}
	public void setTaxSlabId(int taxSlabId) {
		this.taxSlabId = taxSlabId;
	}
	public String getIncomeTaxSlabObjectId() {
		return incomeTaxSlabObjectId;
	}
	public void setIncomeTaxSlabObjectId(String incomeTaxSlabObjectId) {
		this.incomeTaxSlabObjectId = incomeTaxSlabObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getIncomeTaxSlabCode() {
		return incomeTaxSlabCode;
	}
	public void setIncomeTaxSlabCode(String incomeTaxSlabCode) {
		this.incomeTaxSlabCode = incomeTaxSlabCode;
	}
	public String getIncomeTaxSlabName() {
		return incomeTaxSlabName;
	}
	public void setIncomeTaxSlabName(String incomeTaxSlabName) {
		this.incomeTaxSlabName = incomeTaxSlabName;
	}
	public String getIncomeTaxSlabDesc() {
		return incomeTaxSlabDesc;
	}
	public void setIncomeTaxSlabDesc(String incomeTaxSlabDesc) {
		this.incomeTaxSlabDesc = incomeTaxSlabDesc;
	}
	public List<IncomeTaxParameters> getIncomeTaxParameterList() {
		return incomeTaxParameterList;
	}
	public void setIncomeTaxParameterList(
			List<IncomeTaxParameters> incomeTaxParameterList) {
		this.incomeTaxParameterList = incomeTaxParameterList;
	}	
}
