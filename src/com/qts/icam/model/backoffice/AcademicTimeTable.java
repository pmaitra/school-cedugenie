package com.qts.icam.model.backoffice;

import java.util.List;

import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.Standard;

public class AcademicTimeTable {
	private String objectId;
	private String updatedBy;
	private String timeTableUserId;
	private String timeTableCode;
	private String timeTablestartTime;
	private String timeTablesubjectName;
	private String status;
	private String totalSlot;	
	private Standard timeTableClass;
	private Section timeTableSection;	
	private String timeTableDay;
	private int timeintTableCode;
	private AcademicTimeTableDetails academicTimeTableDetails;
	private List<AcademicTimeTableDetails> listAcademicTimeTableDetails ;
	private AcademicYear academicYear;
	
	
	public String getTimeTableCode() {
		return timeTableCode;
	}
	public void setTimeTableCode(String timeTableCode) {
		this.timeTableCode = timeTableCode;
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
	public String getTimeTableUserId() {
		return timeTableUserId;
	}
	public void setTimeTableUserId(String timeTableUserId) {
		this.timeTableUserId = timeTableUserId;
	}
	public String getTimeTablestartTime() {
		return timeTablestartTime;
	}
	public void setTimeTablestartTime(String timeTablestartTime) {
		this.timeTablestartTime = timeTablestartTime;
	}
	public String getTimeTablesubjectName() {
		return timeTablesubjectName;
	}
	public void setTimeTablesubjectName(String timeTablesubjectName) {
		this.timeTablesubjectName = timeTablesubjectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Standard getTimeTableClass() {
		return timeTableClass;
	}
	public void setTimeTableClass(Standard timeTableClass) {
		this.timeTableClass = timeTableClass;
	}
	public Section getTimeTableSection() {
		return timeTableSection;
	}
	public void setTimeTableSection(Section timeTableSection) {
		this.timeTableSection = timeTableSection;
	}
	public String getTimeTableDay() {
		return timeTableDay;
	}
	public void setTimeTableDay(String timeTableDay) {
		this.timeTableDay = timeTableDay;
	}
	public int getTimeintTableCode() {
		return timeintTableCode;
	}
	public void setTimeintTableCode(int timeintTableCode) {
		this.timeintTableCode = timeintTableCode;
	}
	public AcademicTimeTableDetails getAcademicTimeTableDetails() {
		return academicTimeTableDetails;
	}
	public void setAcademicTimeTableDetails(
			AcademicTimeTableDetails academicTimeTableDetails) {
		this.academicTimeTableDetails = academicTimeTableDetails;
	}
	public List<AcademicTimeTableDetails> getListAcademicTimeTableDetails() {
		return listAcademicTimeTableDetails;
	}
	public void setListAcademicTimeTableDetails(
			List<AcademicTimeTableDetails> listAcademicTimeTableDetails) {
		this.listAcademicTimeTableDetails = listAcademicTimeTableDetails;
	}
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public String getTotalSlot() {
		return totalSlot;
	}
	public void setTotalSlot(String totalSlot) {
		this.totalSlot = totalSlot;
	}
}
