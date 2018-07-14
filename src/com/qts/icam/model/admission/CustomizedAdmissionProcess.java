package com.qts.icam.model.admission;

public class CustomizedAdmissionProcess {
	
	private String customizedAdmissionProcessCode;
	private String customizedAdmissionProcessName;
	private String customizedAdmissionProcessDesc;
	private boolean formSubmission;  
	private boolean scheduleInterview;  
	private boolean interviewResult; 
	private boolean meritList; 
	private boolean payment; 
	private String status;
	
	public String getCustomizedAdmissionProcessCode() {
		return customizedAdmissionProcessCode;
	}
	public void setCustomizedAdmissionProcessCode(
			String customizedAdmissionProcessCode) {
		this.customizedAdmissionProcessCode = customizedAdmissionProcessCode;
	}
	public String getCustomizedAdmissionProcessName() {
		return customizedAdmissionProcessName;
	}
	public void setCustomizedAdmissionProcessName(
			String customizedAdmissionProcessName) {
		this.customizedAdmissionProcessName = customizedAdmissionProcessName;
	}
	public String getCustomizedAdmissionProcessDesc() {
		return customizedAdmissionProcessDesc;
	}
	public void setCustomizedAdmissionProcessDesc(
			String customizedAdmissionProcessDesc) {
		this.customizedAdmissionProcessDesc = customizedAdmissionProcessDesc;
	}
	public boolean isFormSubmission() {
		return formSubmission;
	}
	public void setFormSubmission(boolean formSubmission) {
		this.formSubmission = formSubmission;
	}
	public boolean isScheduleInterview() {
		return scheduleInterview;
	}
	public void setScheduleInterview(boolean scheduleInterview) {
		this.scheduleInterview = scheduleInterview;
	}
	public boolean isInterviewResult() {
		return interviewResult;
	}
	public void setInterviewResult(boolean interviewResult) {
		this.interviewResult = interviewResult;
	}
	public boolean isMeritList() {
		return meritList;
	}
	public void setMeritList(boolean meritList) {
		this.meritList = meritList;
	}
	public boolean isPayment() {
		return payment;
	}
	public void setPayment(boolean payment) {
		this.payment = payment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
