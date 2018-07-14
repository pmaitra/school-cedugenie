package com.qts.icam.model.erp;

public class NomineeDetails {
	private String nomineeDetailsObjectId;
	private String nomineeDetailsnCode;
	private String nomineeDetailsDesc;
	private String updatedBy;
	
	private String nomineeName;
	private String relationship;
	private double percentage;	
	private String userId;
	public String getNomineeDetailsObjectId() {
		return nomineeDetailsObjectId;
	}
	public void setNomineeDetailsObjectId(String nomineeDetailsObjectId) {
		this.nomineeDetailsObjectId = nomineeDetailsObjectId;
	}
	public String getNomineeDetailsnCode() {
		return nomineeDetailsnCode;
	}
	public void setNomineeDetailsnCode(String nomineeDetailsnCode) {
		this.nomineeDetailsnCode = nomineeDetailsnCode;
	}
	public String getNomineeDetailsDesc() {
		return nomineeDetailsDesc;
	}
	public void setNomineeDetailsDesc(String nomineeDetailsDesc) {
		this.nomineeDetailsDesc = nomineeDetailsDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
