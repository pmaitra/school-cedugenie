package com.qts.icam.model.common;

import java.util.List;

public class Data {
	
	private String updatedBy;
	private String objectId;
	private int admissionFormId;
	private String driveName;
	private int programId;
	private PortalCandidateDetails portalCandidateDetails;
	private List<QualificationDetails> qualificationDetails;
	private List<ProfessionalDetails> professionalDetails;
	private PaymentDetails paymentDetails;
	private int marks;
	private String selectionStatus;
	private String programName;
	private String programType;
	private int totalSeat;
	private String formIssuanceDate;
	private String formSubmissionLastDate;
	private String candidateScrutinyDate;
	private String interviewDate;
	private String interviewTime;
	private String marksSubmissionDate;
	private String feesPaymentStartDate;
	private String feesPaymentEndDate;
	private List<TermWiseFees> termWiseFeesList;
	private String userId;
	private String email;
	private String name;
	private String registrationId;
	private List<PaymentDetails> paymentForAdmittedCandidates;
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public int getAdmissionFormId() {
		return admissionFormId;
	}
	public void setAdmissionFormId(int admissionFormId) {
		this.admissionFormId = admissionFormId;
	}
	
	public PortalCandidateDetails getPortalCandidateDetails() {
		return portalCandidateDetails;
	}
	public void setPortalCandidateDetails(PortalCandidateDetails portalCandidateDetails) {
		this.portalCandidateDetails = portalCandidateDetails;
	}
	public List<QualificationDetails> getQualificationDetails() {
		return qualificationDetails;
	}
	public void setQualificationDetails(List<QualificationDetails> qualificationDetails) {
		this.qualificationDetails = qualificationDetails;
	}
	public List<ProfessionalDetails> getProfessionalDetails() {
		return professionalDetails;
	}
	public void setProfessionalDetails(List<ProfessionalDetails> professionalDetails) {
		this.professionalDetails = professionalDetails;
	}
	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getSelectionStatus() {
		return selectionStatus;
	}
	public void setSelectionStatus(String selectionStatus) {
		this.selectionStatus = selectionStatus;
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getProgramType() {
		return programType;
	}
	public void setProgramType(String programType) {
		this.programType = programType;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}
	public String getFormIssuanceDate() {
		return formIssuanceDate;
	}
	public void setFormIssuanceDate(String formIssuanceDate) {
		this.formIssuanceDate = formIssuanceDate;
	}
	public String getFormSubmissionLastDate() {
		return formSubmissionLastDate;
	}
	public void setFormSubmissionLastDate(String formSubmissionLastDate) {
		this.formSubmissionLastDate = formSubmissionLastDate;
	}
	public String getCandidateScrutinyDate() {
		return candidateScrutinyDate;
	}
	public void setCandidateScrutinyDate(String candidateScrutinyDate) {
		this.candidateScrutinyDate = candidateScrutinyDate;
	}
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}
	public String getMarksSubmissionDate() {
		return marksSubmissionDate;
	}
	public void setMarksSubmissionDate(String marksSubmissionDate) {
		this.marksSubmissionDate = marksSubmissionDate;
	}
	public String getFeesPaymentStartDate() {
		return feesPaymentStartDate;
	}
	public void setFeesPaymentStartDate(String feesPaymentStartDate) {
		this.feesPaymentStartDate = feesPaymentStartDate;
	}
	public String getFeesPaymentEndDate() {
		return feesPaymentEndDate;
	}
	public void setFeesPaymentEndDate(String feesPaymentEndDate) {
		this.feesPaymentEndDate = feesPaymentEndDate;
	}
	public List<TermWiseFees> getTermWiseFeesList() {
		return termWiseFeesList;
	}
	public void setTermWiseFeesList(List<TermWiseFees> termWiseFeesList) {
		this.termWiseFeesList = termWiseFeesList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDriveName() {
		return driveName;
	}
	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public List<PaymentDetails> getPaymentForAdmittedCandidates() {
		return paymentForAdmittedCandidates;
	}
	public void setPaymentForAdmittedCandidates(List<PaymentDetails> paymentForAdmittedCandidates) {
		this.paymentForAdmittedCandidates = paymentForAdmittedCandidates;
	}
	
	
}
