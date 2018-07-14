package com.qts.icam.model.admission;

import java.util.List;

import com.qts.icam.model.admission.FeesCategory;
import com.qts.icam.model.common.Resource;

public class AdmissionProcess {
	
	/************** Common ****************/
	private String candidateObjectId;
	private String candidateFirstName;
	private String candidateMiddleName;
	private String candidateLastName;
	private String userId;
	private String candidateEmail;
	private String candidateContactNo;
	private String candidateGender;
	private String candidateBirthDate;
	private String updatedBy;
	private String formId;
	private String formName;
	private String courseClass;
	private String courseName;
	private String courseType;	
	private String formStatus;
	private String status;
	private String gender;
	private String dateOfBirth;
	private String category;
	private boolean dateStatus;
	
	
	
	/********** Form Submission ***********/
	private String formSubmissionDate;
	
	private String storageRootPath;
	
	private String folderObjectId;
	private String folderCode;
	private String folderName;
	private String folderDescription;
	
	private String attachmentObjectId;
	private String attachmentCode;
	private String attachmentName;
	private String attachmentDescription;	
	private int attachmentSize;
	private String attachmentFormat;
	
	/**************************************/
	
	/********** Interview Result ***********/
	
	private String interviewResultObjId;
	private List<Marks> marksList;
	private List<Form> formList;
	private String comment;
	private int totalMarks;
	private String lastFeesSubmissionDate;
	private String paymentDate;
	
	/**************************************/
	
	/********* Interview Schedule *********/
	
	private String interviewDateAndTime;
	private String interviewDate;
	private String interviewTime;
	private List<Resource> resourceList;
	private String examinerName;
	private String reviewerName;
	private String roomNo;
	private String venue;
	
	private List<FeesCategory> feesCategoryList;
	
	/**************************************/
	private int intPaymentDate;
	private int intLastFeesSubmissionDate;
	private int intFormSubmissionDate;
	
	/*******Additional Status to search *******/
	
	private String searchStatus;
	private String admissionYear;	
			
	private String taskStatus;
	
	private AdmissionForm admissionForm;
		
		
	public String getCandidateFirstName() {
		return candidateFirstName;
	}
	public List<FeesCategory> getFeesCategoryList() {
		return feesCategoryList;
	}
	public void setFeesCategoryList(List<FeesCategory> feesCategoryList) {
		this.feesCategoryList = feesCategoryList;
	}
	public String getCandidateObjectId() {
		return candidateObjectId;
	}
	public void setCandidateObjectId(String candidateObjectId) {
		this.candidateObjectId = candidateObjectId;
	}
	public void setCandidateFirstName(String candidateFirstName) {
		this.candidateFirstName = candidateFirstName;
	}
	public String getCandidateMiddleName() {
		return candidateMiddleName;
	}
	public void setCandidateMiddleName(String candidateMiddleName) {
		this.candidateMiddleName = candidateMiddleName;
	}
	public String getCandidateLastName() {
		return candidateLastName;
	}
	public void setCandidateLastName(String candidateLastName) {
		this.candidateLastName = candidateLastName;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCandidateEmail() {
		return candidateEmail;
	}
	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}
	public String getCandidateContactNo() {
		return candidateContactNo;
	}
	public void setCandidateContactNo(String candidateContactNo) {
		this.candidateContactNo = candidateContactNo;
	}
	public String getCandidateGender() {
		return candidateGender;
	}
	public void setCandidateGender(String candidateGender) {
		this.candidateGender = candidateGender;
	}
	public String getCandidateBirthDate() {
		return candidateBirthDate;
	}
	public void setCandidateBirthDate(String candidateBirthDate) {
		this.candidateBirthDate = candidateBirthDate;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getCourseClass() {
		return courseClass;
	}
	public void setCourseClass(String courseClass) {
		this.courseClass = courseClass;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getFormStatus() {
		return formStatus;
	}
	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}
	public String getFormSubmissionDate() {
		return formSubmissionDate;
	}
	public void setFormSubmissionDate(String formSubmissionDate) {
		this.formSubmissionDate = formSubmissionDate;
	}
	public String getStorageRootPath() {
		return storageRootPath;
	}
	public void setStorageRootPath(String storageRootPath) {
		this.storageRootPath = storageRootPath;
	}
	public String getFolderObjectId() {
		return folderObjectId;
	}
	public void setFolderObjectId(String folderObjectId) {
		this.folderObjectId = folderObjectId;
	}
	public String getFolderCode() {
		return folderCode;
	}
	public void setFolderCode(String folderCode) {
		this.folderCode = folderCode;
	}
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	public String getFolderDescription() {
		return folderDescription;
	}
	public void setFolderDescription(String folderDescription) {
		this.folderDescription = folderDescription;
	}
	public String getAttachmentObjectId() {
		return attachmentObjectId;
	}
	public void setAttachmentObjectId(String attachmentObjectId) {
		this.attachmentObjectId = attachmentObjectId;
	}
	public String getAttachmentCode() {
		return attachmentCode;
	}
	public void setAttachmentCode(String attachmentCode) {
		this.attachmentCode = attachmentCode;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentDescription() {
		return attachmentDescription;
	}
	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}
	public int getAttachmentSize() {
		return attachmentSize;
	}
	public void setAttachmentSize(int attachmentSize) {
		this.attachmentSize = attachmentSize;
	}
	public String getAttachmentFormat() {
		return attachmentFormat;
	}
	public void setAttachmentFormat(String attachmentFormat) {
		this.attachmentFormat = attachmentFormat;
	}
	public String getInterviewResultObjId() {
		return interviewResultObjId;
	}
	public void setInterviewResultObjId(String interviewResultObjId) {
		this.interviewResultObjId = interviewResultObjId;
	}
	public List<Marks> getMarksList() {
		return marksList;
	}
	public void setMarksList(List<Marks> marksList) {
		this.marksList = marksList;
	}
	public List<Form> getFormList() {
		return formList;
	}
	public void setFormList(List<Form> formList) {
		this.formList = formList;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}	
	public String getLastFeesSubmissionDate() {
		return lastFeesSubmissionDate;
	}
	public void setLastFeesSubmissionDate(String lastFeesSubmissionDate) {
		this.lastFeesSubmissionDate = lastFeesSubmissionDate;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getInterviewDateAndTime() {
		return interviewDateAndTime;
	}
	public void setInterviewDateAndTime(String interviewDateAndTime) {
		this.interviewDateAndTime = interviewDateAndTime;
		String[] strDateTime = interviewDateAndTime.split("::");
		setInterviewDate(strDateTime[0]);
		setInterviewTime(strDateTime[1]);
		
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
	public List<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	public String getExaminerName() {
		return examinerName;
	}
	public void setExaminerName(String examinerName) {
		this.examinerName = examinerName;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public int getIntPaymentDate() {
		return intPaymentDate;
	}
	public void setIntPaymentDate(int intPaymentDate) {
		this.intPaymentDate = intPaymentDate;
	}
	public int getIntLastFeesSubmissionDate() {
		return intLastFeesSubmissionDate;
	}
	public void setIntLastFeesSubmissionDate(int intLastFeesSubmissionDate) {
		this.intLastFeesSubmissionDate = intLastFeesSubmissionDate;
	}
	public int getIntFormSubmissionDate() {
		return intFormSubmissionDate;
	}
	public void setIntFormSubmissionDate(int intFormSubmissionDate) {
		this.intFormSubmissionDate = intFormSubmissionDate;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
	public String getAdmissionYear() {
		return admissionYear;
	}
	public void setAdmissionYear(String admissionYear) {
		this.admissionYear = admissionYear;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isDateStatus() {
		return dateStatus;
	}
	public void setDateStatus(boolean dateStatus) {
		this.dateStatus = dateStatus;
	}
	public AdmissionForm getAdmissionForm() {
		return admissionForm;
	}
	public void setAdmissionForm(AdmissionForm admissionForm) {
		this.admissionForm = admissionForm;
	}
	
	
}
