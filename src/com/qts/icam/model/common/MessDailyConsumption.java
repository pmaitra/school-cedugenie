package com.qts.icam.model.common;

import java.util.List;

/**
 * @author anup.roy*/

public class MessDailyConsumption {
	
	private String inventorySession;
	private String dateOfIssue;
	private String issueToKitchenCiv;
	private String userId;
	private String messDailyConsumptionObjectId;
	private String updatedBy;
	private List<MessDailyConsumptionDetails> consumptionDetailsList;
	
	public String getInventorySession() {
		return inventorySession;
	}
	public void setInventorySession(String inventorySession) {
		this.inventorySession = inventorySession;
	}
	public String getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	/**
	 * @return the issueToKitchenCiv
	 */
	public String getIssueToKitchenCiv() {
		return issueToKitchenCiv;
	}
	/**
	 * @param issueToKitchenCiv the issueToKitchenCiv to set
	 */
	public void setIssueToKitchenCiv(String issueToKitchenCiv) {
		this.issueToKitchenCiv = issueToKitchenCiv;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessDailyConsumptionObjectId() {
		return messDailyConsumptionObjectId;
	}
	public void setMessDailyConsumptionObjectId(String messDailyConsumptionObjectId) {
		this.messDailyConsumptionObjectId = messDailyConsumptionObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public List<MessDailyConsumptionDetails> getConsumptionDetailsList() {
		return consumptionDetailsList;
	}
	public void setConsumptionDetailsList(List<MessDailyConsumptionDetails> consumptionDetailsList) {
		this.consumptionDetailsList = consumptionDetailsList;
	}
}