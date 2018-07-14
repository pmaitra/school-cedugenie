package com.qts.icam.model.common;

public class Scholarship {
	private int scholarshipId;
	private String objectId;
	private String updatedBy;
	private String scholarshipCode;
	private String scholarshipName;
	private String desc;
	private String status;
	private double concession;
	private String concessionUnit;
	
	public int getScholarshipId() {
		return scholarshipId;
	}
	public void setScholarshipId(int scholarshipId) {
		this.scholarshipId = scholarshipId;
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
	public String getScholarshipCode() {
		return scholarshipCode;
	}
	public void setScholarshipCode(String scholarshipCode) {
		this.scholarshipCode = scholarshipCode;
	}
	public String getScholarshipName() {
		return scholarshipName;
	}
	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getConcession() {
		return concession;
	}
	public void setConcession(double concession) {
		this.concession = concession;
	}
	public String getConcessionUnit() {
		return concessionUnit;
	}
	public void setConcessionUnit(String concessionUnit) {
		this.concessionUnit = concessionUnit;
	}
}
