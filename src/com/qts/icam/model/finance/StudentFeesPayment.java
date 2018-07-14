package com.qts.icam.model.finance;

import java.util.List;

public class StudentFeesPayment {
	private int serialId;
	private String objectId;
	private String desc;
	private String updatedBy;
	private String status;
	
	private String rollNumber;
	private String section;
	private String standard;
	private String feesStatus;
	List<StudentFeesPaymentDetails> studentFeesPaymentDetailsList;
	
	public int getSerialId() {
		return serialId;
	}
	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getFeesStatus() {
		return feesStatus;
	}
	public void setFeesStatus(String feesStatus) {
		this.feesStatus = feesStatus;
	}
	public List<StudentFeesPaymentDetails> getStudentFeesPaymentDetailsList() {
		return studentFeesPaymentDetailsList;
	}
	public void setStudentFeesPaymentDetailsList(
			List<StudentFeesPaymentDetails> studentFeesPaymentDetailsList) {
		this.studentFeesPaymentDetailsList = studentFeesPaymentDetailsList;
	}
}
