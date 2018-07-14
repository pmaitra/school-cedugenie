package com.qts.icam.model.backoffice;

import com.qts.icam.model.common.ResourceType;


public class LeavePolicy {
	private String leavePolicyObjectId;
	private int leavePolicyId;
	private int updated_on;
	private String updatedBy;
	private String resourceTypeSelect;
	
	//leave policy
	private int noOfYearsCarryForward;
	private int dailyEncashment;
	private ResourceType resourceType;
	
	public String getLeavePolicyObjectId() {
		return leavePolicyObjectId;
	}
	public void setLeavePolicyObjectId(String leavePolicyObjectId) {
		this.leavePolicyObjectId = leavePolicyObjectId;
	}
	public int getLeavePolicyId() {
		return leavePolicyId;
	}
	public void setLeavePolicyId(int leavePolicyId) {
		this.leavePolicyId = leavePolicyId;
	}
	public int getUpdated_on() {
		return updated_on;
	}
	public void setUpdated_on(int updated_on) {
		this.updated_on = updated_on;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getResourceTypeSelect() {
		return resourceTypeSelect;
	}
	public void setResourceTypeSelect(String resourceTypeSelect) {
		this.resourceTypeSelect = resourceTypeSelect;
	}
	public int getNoOfYearsCarryForward() {
		return noOfYearsCarryForward;
	}
	public void setNoOfYearsCarryForward(int noOfYearsCarryForward) {
		this.noOfYearsCarryForward = noOfYearsCarryForward;
	}
	public int getDailyEncashment() {
		return dailyEncashment;
	}
	public void setDailyEncashment(int dailyEncashment) {
		this.dailyEncashment = dailyEncashment;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	
	
	
	

}
