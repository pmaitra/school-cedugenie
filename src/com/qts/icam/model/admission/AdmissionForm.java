package com.qts.icam.model.admission;

import java.util.List;

import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.MeritListType;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.common.Course;



/**
 * 
 * @author parmanand.singh
 * @version 1.0
 *
 */
public class AdmissionForm {
	private String admissionFormObjectId;
	private String admissionFormCode;
	private String admissionFormDesc;
	private String admissionFormName;
	private int admissionFormId;
	private String updatedBy;
	private String admissionDriveName;
	private String admissionFormYear;
	private String admissionDriveStartDate;
	private String admissionDriveActualEndDate;
	private String admissionDriveExpectedEndDate;
	private String admissionFormSubmissionLastDate;
	private int intAdmissionDriveStartDate;
	private int intAdmissionDriveActualEndDate;
	private int intAdmissionDriveExpectedEndDate;
	private int intAdmissionFormSubmissionLastDate;
	private int noOfFormSold;
	private double totalPrice;
	private double courseFees;
	private int noOfOpenings;
	private int totalSeat;
	private int noOfAdmittedStudents;	
	private double formFees;
	private String courseName;
	private String oldCourseName;
	private String courseCode;
	private String courseClass;
	private String courseType;
	private String streamName;
	private String oldStreamName;
	private int courseDuration;
	private String admissionDriveCode;
	private String admissionFormObjectID;
	private String admissionFormDescription;
	private String strMessage;
	private String imagePath;
	private String imageFile1name;
	private String imageFile2name;
	private String pdfFolder;
	private String rootPath;
	private String status;
	private String searchStatus;
	private CustomizedAdmissionProcess customizedAdmissionProcess;
	private List<CustomizedAdmissionProcess> customizedAdmissionProcessList;
	
	private Attachment attachment;
	private String oldCourseType;
	private List<Course> courseList;
	private String courseStartDate;
	private String courseStartTime;
	private String courseEndTime;
	private String paymentMode;
	
	private String examinationDate;
	private String examinationTime;
	private Standard standard;
	private MeritListType meritListType;
	private Venue venue;
	private List<Candidate> candidateList;
	private String admitCardFilePath;
	private String pdfFilePath;
	
	/***added by naimisha*/
	
	private String formIssuanceDate;
	private String interviewDate;
	private String marksSubmissionDate;
	private String lastFormSubmissionDate;
	private String interviewStartTime;
	private String scrutinyDate;
	private String interviewEndTime;
	
	private String courseAcronym;
	private int courseFormFees;
	
	//Added By Naimisha 17062017
	private List<Section> sectionList;
	
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getAdmissionFormObjectId() {
		return admissionFormObjectId;
	}
	public void setAdmissionFormObjectId(String admissionFormObjectId) {
		this.admissionFormObjectId = admissionFormObjectId;
	}
	public String getAdmissionFormCode() {
		return admissionFormCode;
	}
	public void setAdmissionFormCode(String admissionFormCode) {
		this.admissionFormCode = admissionFormCode;
	}
	public String getAdmissionFormDesc() {
		return admissionFormDesc;
	}
	public void setAdmissionFormDesc(String admissionFormDesc) {
		this.admissionFormDesc = admissionFormDesc;
	}
	public String getAdmissionFormName() {
		return admissionFormName;
	}
	public void setAdmissionFormName(String admissionFormName) {
		this.admissionFormName = admissionFormName;
	}
	public int getAdmissionFormId() {
		return admissionFormId;
	}
	public void setAdmissionFormId(int admissionFormId) {
		this.admissionFormId = admissionFormId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getAdmissionDriveName() {
		return admissionDriveName;
	}
	public void setAdmissionDriveName(String admissionDriveName) {
		this.admissionDriveName = admissionDriveName;
	}
	public String getAdmissionFormYear() {
		return admissionFormYear;
	}
	public void setAdmissionFormYear(String admissionFormYear) {
		this.admissionFormYear = admissionFormYear;
	}
	public String getAdmissionDriveStartDate() {
		return admissionDriveStartDate;
	}
	public void setAdmissionDriveStartDate(String admissionDriveStartDate) {
		this.admissionDriveStartDate = admissionDriveStartDate;
	}
	public String getAdmissionDriveActualEndDate() {
		return admissionDriveActualEndDate;
	}
	public void setAdmissionDriveActualEndDate(String admissionDriveActualEndDate) {
		this.admissionDriveActualEndDate = admissionDriveActualEndDate;
	}
	public String getAdmissionDriveExpectedEndDate() {
		return admissionDriveExpectedEndDate;
	}
	public void setAdmissionDriveExpectedEndDate(
			String admissionDriveExpectedEndDate) {
		this.admissionDriveExpectedEndDate = admissionDriveExpectedEndDate;
	}	
	
	public int getIntAdmissionDriveStartDate() {
		return intAdmissionDriveStartDate;
	}
	public void setIntAdmissionDriveStartDate(int intAdmissionDriveStartDate) {
		this.intAdmissionDriveStartDate = intAdmissionDriveStartDate;
	}
	public int getIntAdmissionDriveActualEndDate() {
		return intAdmissionDriveActualEndDate;
	}
	public void setIntAdmissionDriveActualEndDate(
			int intAdmissionDriveActualEndDate) {
		this.intAdmissionDriveActualEndDate = intAdmissionDriveActualEndDate;
	}
	public int getIntAdmissionDriveExpectedEndDate() {
		return intAdmissionDriveExpectedEndDate;
	}
	public void setIntAdmissionDriveExpectedEndDate(
			int intAdmissionDriveExpectedEndDate) {
		this.intAdmissionDriveExpectedEndDate = intAdmissionDriveExpectedEndDate;
	}
	public int getIntAdmissionFormSubmissionLastDate() {
		return intAdmissionFormSubmissionLastDate;
	}
	public void setIntAdmissionFormSubmissionLastDate(
			int intAdmissionFormSubmissionLastDate) {
		this.intAdmissionFormSubmissionLastDate = intAdmissionFormSubmissionLastDate;
	}
	public double getCourseFees() {
		return courseFees;
	}
	public void setCourseFees(double courseFees) {
		this.courseFees = courseFees;
	}
	public int getNoOfOpenings() {
		return noOfOpenings;
	}
	public void setNoOfOpenings(int noOfOpenings) {
		this.noOfOpenings = noOfOpenings;
	}
	public int getNoOfAdmittedStudents() {
		return noOfAdmittedStudents;
	}
	public void setNoOfAdmittedStudents(int noOfAdmittedStudents) {
		this.noOfAdmittedStudents = noOfAdmittedStudents;
	}
	public String getAdmissionFormSubmissionLastDate() {
		return admissionFormSubmissionLastDate;
	}
	public void setAdmissionFormSubmissionLastDate(
			String admissionFormSubmissionLastDate) {
		this.admissionFormSubmissionLastDate = admissionFormSubmissionLastDate;
	}
	public double getFormFees() {
		return formFees;
	}
	public void setFormFees(double formFees) {
		this.formFees = formFees;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseClass() {
		return courseClass;
	}
	public void setCourseClass(String courseClass) {
		this.courseClass = courseClass;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	
	public int getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}
	public String getAdmissionDriveCode() {
		return admissionDriveCode;
	}
	public void setAdmissionDriveCode(String admissionDriveCode) {
		this.admissionDriveCode = admissionDriveCode;
	}
	public String getAdmissionFormObjectID() {
		return admissionFormObjectID;
	}
	public void setAdmissionFormObjectID(String admissionFormObjectID) {
		this.admissionFormObjectID = admissionFormObjectID;
	}
	public String getAdmissionFormDescription() {
		return admissionFormDescription;
	}
	public void setAdmissionFormDescription(String admissionFormDescription) {
		this.admissionFormDescription = admissionFormDescription;
	}
	public String getStrMessage() {
		return strMessage;
	}
	public void setStrMessage(String strMessage) {
		this.strMessage = strMessage;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getImageFile1name() {
		return imageFile1name;
	}
	public void setImageFile1name(String imageFile1name) {
		this.imageFile1name = imageFile1name;
	}
	public String getImageFile2name() {
		return imageFile2name;
	}
	public void setImageFile2name(String imageFile2name) {
		this.imageFile2name = imageFile2name;
	}
	public String getPdfFolder() {
		return pdfFolder;
	}
	public void setPdfFolder(String pdfFolder) {
		this.pdfFolder = pdfFolder;
	}
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSearchStatus() {
		return searchStatus;
	}
	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}

	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	public int getNoOfFormSold() {
		return noOfFormSold;
	}
	public void setNoOfFormSold(int noOfFormSold) {
		this.noOfFormSold = noOfFormSold;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	
	public String getOldStreamName() {
		return oldStreamName;
	}
	public void setOldStreamName(String oldStreamName) {
		this.oldStreamName = oldStreamName;
	}
	public String getOldCourseName() {
		return oldCourseName;
	}
	public void setOldCourseName(String oldCourseName) {
		this.oldCourseName = oldCourseName;
	}
	public String getOldCourseType() {
		return oldCourseType;
	}
	public void setOldCourseType(String oldCourseType) {
		this.oldCourseType = oldCourseType;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}
	public CustomizedAdmissionProcess getCustomizedAdmissionProcess() {
		return customizedAdmissionProcess;
	}
	public void setCustomizedAdmissionProcess(CustomizedAdmissionProcess customizedAdmissionProcess) {
		this.customizedAdmissionProcess = customizedAdmissionProcess;
	}
	public List<CustomizedAdmissionProcess> getCustomizedAdmissionProcessList() {
		return customizedAdmissionProcessList;
	}
	public void setCustomizedAdmissionProcessList(
			List<CustomizedAdmissionProcess> customizedAdmissionProcessList) {
		this.customizedAdmissionProcessList = customizedAdmissionProcessList;
	}
	public String getCourseStartDate() {
		return courseStartDate;
	}
	public void setCourseStartDate(String courseStartDate) {
		this.courseStartDate = courseStartDate;
	}
	public String getCourseEndTime() {
		return courseEndTime;
	}
	public void setCourseEndTime(String courseEndTime) {
		this.courseEndTime = courseEndTime;
	}
	public String getCourseStartTime() {
		return courseStartTime;
	}
	public void setCourseStartTime(String courseStartTime) {
		this.courseStartTime = courseStartTime;
	}
	
	public String getExaminationDate() {
		return examinationDate;
	}
	public void setExaminationDate(String examinationDate) {
		this.examinationDate = examinationDate;
	}
	public String getExaminationTime() {
		return examinationTime;
	}
	public void setExaminationTime(String examinationTime) {
		this.examinationTime = examinationTime;
	}
	
	public Standard getStandard() {
		return standard;
	}
	public void setStandard(Standard standard) {
		this.standard = standard;
	}
	
	public MeritListType getMeritListType() {
		return meritListType;
	}
	public void setMeritListType(MeritListType meritListType) {
		this.meritListType = meritListType;
	}
	
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	public List<Candidate> getCandidateList() {
		return candidateList;
	}
	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}
	
	public String getAdmitCardFilePath() {
		return admitCardFilePath;
	}
	public void setAdmitCardFilePath(String admitCardFilePath) {
		this.admitCardFilePath = admitCardFilePath;
	}
	
	public String getPdfFilePath() {
		return pdfFilePath;
	}
	public void setPdfFilePath(String pdfFilePath) {
		this.pdfFilePath = pdfFilePath;
	}
	public String getFormIssuanceDate() {
		return formIssuanceDate;
	}
	public void setFormIssuanceDate(String formIssuanceDate) {
		this.formIssuanceDate = formIssuanceDate;
	}
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getMarksSubmissionDate() {
		return marksSubmissionDate;
	}
	public void setMarksSubmissionDate(String marksSubmissionDate) {
		this.marksSubmissionDate = marksSubmissionDate;
	}
	public String getLastFormSubmissionDate() {
		return lastFormSubmissionDate;
	}
	public void setLastFormSubmissionDate(String lastFormSubmissionDate) {
		this.lastFormSubmissionDate = lastFormSubmissionDate;
	}
	public String getInterviewStartTime() {
		return interviewStartTime;
	}
	public void setInterviewStartTime(String interviewStartTime) {
		this.interviewStartTime = interviewStartTime;
	}
	public String getScrutinyDate() {
		return scrutinyDate;
	}
	public void setScrutinyDate(String scrutinyDate) {
		this.scrutinyDate = scrutinyDate;
	}
	public String getInterviewEndTime() {
		return interviewEndTime;
	}
	public void setInterviewEndTime(String interviewEndTime) {
		this.interviewEndTime = interviewEndTime;
	}
	public String getCourseAcronym() {
		return courseAcronym;
	}
	public void setCourseAcronym(String courseAcronym) {
		this.courseAcronym = courseAcronym;
	}
	public int getCourseFormFees() {
		return courseFormFees;
	}
	public void setCourseFormFees(int courseFormFees) {
		this.courseFormFees = courseFormFees;
	}
	public List<Section> getSectionList() {
		return sectionList;
	}
	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}
}
