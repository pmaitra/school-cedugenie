package com.qts.icam.model.common;

import java.util.List;

public class ProgrammeDetailsForPortal {

	private List<Data> data;
	private int programId;
	private String programName;
	private String programType;
	private int totalSeat;
	private int formIssuanceDate;
	private int formSubmissionLastDate;
	private int candidateScrutinyDate;
	private int interviewDate;
	private int interviewTime;
	private int marksSubmissionDate;
	private int feesPaymentStartDate;
	private int feesPaymentEndDate;
	private List<TermWiseFees> termWiseFeesList;
	private int courseFormFees;
	private String interviewStartTime;
	private String courseDetailsCode;
	private String admissionDrive ;
	private String courseFees;
	private String programCode;
	
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
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
	
	public int getFormIssuanceDate() {
		return formIssuanceDate;
	}
	public void setFormIssuanceDate(int formIssuanceDate) {
		this.formIssuanceDate = formIssuanceDate;
	}
	public int getFormSubmissionLastDate() {
		return formSubmissionLastDate;
	}
	public void setFormSubmissionLastDate(int formSubmissionLastDate) {
		this.formSubmissionLastDate = formSubmissionLastDate;
	}
	public int getCandidateScrutinyDate() {
		return candidateScrutinyDate;
	}
	public void setCandidateScrutinyDate(int candidateScrutinyDate) {
		this.candidateScrutinyDate = candidateScrutinyDate;
	}
	public int getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(int interviewDate) {
		this.interviewDate = interviewDate;
	}
	public int getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(int interviewTime) {
		this.interviewTime = interviewTime;
	}
	public int getMarksSubmissionDate() {
		return marksSubmissionDate;
	}
	public void setMarksSubmissionDate(int marksSubmissionDate) {
		this.marksSubmissionDate = marksSubmissionDate;
	}
	public int getFeesPaymentStartDate() {
		return feesPaymentStartDate;
	}
	public void setFeesPaymentStartDate(int feesPaymentStartDate) {
		this.feesPaymentStartDate = feesPaymentStartDate;
	}
	public int getFeesPaymentEndDate() {
		return feesPaymentEndDate;
	}
	public void setFeesPaymentEndDate(int feesPaymentEndDate) {
		this.feesPaymentEndDate = feesPaymentEndDate;
	}
	public List<TermWiseFees> getTermWiseFeesList() {
		return termWiseFeesList;
	}
	public void setTermWiseFeesList(List<TermWiseFees> termWiseFeesList) {
		this.termWiseFeesList = termWiseFeesList;
	}
	public int getCourseFormFees() {
		return courseFormFees;
	}
	public void setCourseFormFees(int courseFormFees) {
		this.courseFormFees = courseFormFees;
	}
	
	public String getInterviewStartTime() {
		return interviewStartTime;
	}
	public void setInterviewStartTime(String interviewStartTime) {
		this.interviewStartTime = interviewStartTime;
	}
	public String getCourseDetailsCode() {
		return courseDetailsCode;
	}
	public void setCourseDetailsCode(String courseDetailsCode) {
		this.courseDetailsCode = courseDetailsCode;
	}
	public String getAdmissionDrive() {
		return admissionDrive;
	}
	public void setAdmissionDrive(String admissionDrive) {
		this.admissionDrive = admissionDrive;
	}
	public String getCourseFees() {
		return courseFees;
	}
	public void setCourseFees(String courseFees) {
		this.courseFees = courseFees;
	}
	public String getProgramCode() {
		return programCode;
	}
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	
}
