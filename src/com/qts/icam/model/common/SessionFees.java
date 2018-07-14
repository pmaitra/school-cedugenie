package com.qts.icam.model.common;

import java.util.List;

public class SessionFees {
	

	private String sessionFeesObjectId;
	private String sessionFeesCode;
	private String sessionFeesDesc;
	private String sessionFeesName;
	private String updatedBy;
	
	private String academicSsession;
	private String className;
	private String streamName;
	private String section;
	private String registrationId;
	private double netTotAmount;
	
	private String feesName;
	private double payingAmount;
	private String comment;
	private String paymentMode;
	
	/**
	 * @author anup.roy for receive session fees*/
	
	private String standardName;
	private String sectionName;
	private String rollNumber;
	private String dateOfPayment;
	
	
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getSessionFeesObjectId() {
		return sessionFeesObjectId;
	}
	public void setSessionFeesObjectId(String sessionFeesObjectId) {
		this.sessionFeesObjectId = sessionFeesObjectId;
	}
	public String getSessionFeesCode() {
		return sessionFeesCode;
	}
	public void setSessionFeesCode(String sessionFeesCode) {
		this.sessionFeesCode = sessionFeesCode;
	}
	public String getSessionFeesDesc() {
		return sessionFeesDesc;
	}
	public void setSessionFeesDesc(String sessionFeesDesc) {
		this.sessionFeesDesc = sessionFeesDesc;
	}
	public String getSessionFeesName() {
		return sessionFeesName;
	}
	public void setSessionFeesName(String sessionFeesName) {
		this.sessionFeesName = sessionFeesName;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getAcademicSsession() {
		return academicSsession;
	}
	public void setAcademicSsession(String academicSsession) {
		this.academicSsession = academicSsession;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public double getNetTotAmount() {
		return netTotAmount;
	}
	public void setNetTotAmount(double netTotAmount) {
		this.netTotAmount = netTotAmount;
	}
	public String getFeesName() {
		return feesName;
	}
	public void setFeesName(String feesName) {
		this.feesName = feesName;
	}
	public double getPayingAmount() {
		return payingAmount;
	}
	public void setPayingAmount(double payingAmount) {
		this.payingAmount = payingAmount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	/**
	 * @return the dateOfPayment
	 */
	public String getDateOfPayment() {
		return dateOfPayment;
	}
	/**
	 * @param dateOfPayment the dateOfPayment to set
	 */
	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
}
