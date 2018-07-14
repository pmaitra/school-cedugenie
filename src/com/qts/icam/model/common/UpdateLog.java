package com.qts.icam.model.common;

public class UpdateLog {
	private int serialId;
	private String description;
	private String updatedByUserId;
	private String updatedByName;
	private String updatedOn;
	private String updatedFor;
	private String insertUpdate;
	private String functionality;
	private String module;
	
	
	
	public int getSerialId() {
		return serialId;
	}
	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUpdatedByUserId() {
		return updatedByUserId;
	}
	public void setUpdatedByUserId(String updatedByUserId) {
		this.updatedByUserId = updatedByUserId;
	}
	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedFor() {
		return updatedFor;
	}
	public void setUpdatedFor(String updatedFor) {
		this.updatedFor = updatedFor;
	}
	public String getInsertUpdate() {
		return insertUpdate;
	}
	public void setInsertUpdate(String insertUpdate) {
		this.insertUpdate = insertUpdate;
	}
	public String getFunctionality() {
		return functionality;
	}
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
}
