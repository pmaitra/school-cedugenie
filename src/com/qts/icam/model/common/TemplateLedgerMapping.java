package com.qts.icam.model.common;

public class TemplateLedgerMapping {
	
	private int templateLedgerMappingId;
	private String objectId;
	private String updatedBy;

	private String salaryTemplateCode;
	private String salaryTemplateName;
	private String salaryTemplateDesc;
	private String ledgerMappingStatus;	
	
	private String salaryTemplateDetailsCode;
	private String salarybreakupName;
	private String salarybreakupType;
	private String ledger;
	
	public int getTemplateLedgerMappingId() {
		return templateLedgerMappingId;
	}
	public void setTemplateLedgerMappingId(int templateLedgerMappingId) {
		this.templateLedgerMappingId = templateLedgerMappingId;
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
	public String getSalaryTemplateCode() {
		return salaryTemplateCode;
	}
	public void setSalaryTemplateCode(String salaryTemplateCode) {
		this.salaryTemplateCode = salaryTemplateCode;
	}
	public String getSalaryTemplateName() {
		return salaryTemplateName;
	}
	public void setSalaryTemplateName(String salaryTemplateName) {
		this.salaryTemplateName = salaryTemplateName;
	}
	public String getSalaryTemplateDesc() {
		return salaryTemplateDesc;
	}
	public void setSalaryTemplateDesc(String salaryTemplateDesc) {
		this.salaryTemplateDesc = salaryTemplateDesc;
	}
	public String getLedgerMappingStatus() {
		return ledgerMappingStatus;
	}
	public void setLedgerMappingStatus(String ledgerMappingStatus) {
		this.ledgerMappingStatus = ledgerMappingStatus;
	}
	public String getSalaryTemplateDetailsCode() {
		return salaryTemplateDetailsCode;
	}
	public void setSalaryTemplateDetailsCode(String salaryTemplateDetailsCode) {
		this.salaryTemplateDetailsCode = salaryTemplateDetailsCode;
	}
	public String getSalarybreakupName() {
		return salarybreakupName;
	}
	public void setSalarybreakupName(String salarybreakupName) {
		this.salarybreakupName = salarybreakupName;
	}	
	public String getSalarybreakupType() {
		return salarybreakupType;
	}
	public void setSalarybreakupType(String salarybreakupType) {
		this.salarybreakupType = salarybreakupType;
	}
	public String getLedger() {
		return ledger;
	}
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}
}
