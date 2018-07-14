package com.qts.icam.model.backoffice;

import java.util.List;

public class AttendanceDetails {
	private String attendanceDetailsObjectId;
	private String attendanceDetailsCode;
	private String attendanceDetailsDesc;
	private String updatedBy;
	private String month;
	private String year;
	private int resource;
	private List<String> absentDateList;
	private List<String> presentDateList;
	private String teacherId;
	
	public String getAttendanceDetailsObjectId() {
		return attendanceDetailsObjectId;
	}
	public void setAttendanceDetailsObjectId(String attendanceDetailsObjectId) {
		this.attendanceDetailsObjectId = attendanceDetailsObjectId;
	}
	public String getAttendanceDetailsCode() {
		return attendanceDetailsCode;
	}
	public void setAttendanceDetailsCode(String attendanceDetailsCode) {
		this.attendanceDetailsCode = attendanceDetailsCode;
	}
	public String getAttendanceDetailsDesc() {
		return attendanceDetailsDesc;
	}
	public void setAttendanceDetailsDesc(String attendanceDetailsDesc) {
		this.attendanceDetailsDesc = attendanceDetailsDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<String> getAbsentDateList() {
		return absentDateList;
	}
	public void setAbsentDateList(List<String> absentDateList) {
		this.absentDateList = absentDateList;
	}
	public int getResource() {
		return resource;
	}
	public void setResource(int resource) {
		this.resource = resource;
	}
	public List<String> getPresentDateList() {
		return presentDateList;
	}
	public void setPresentDateList(List<String> presentDateList) {
		this.presentDateList = presentDateList;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	
}