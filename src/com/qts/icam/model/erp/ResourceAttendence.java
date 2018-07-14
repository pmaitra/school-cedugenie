package com.qts.icam.model.erp;

import com.qts.icam.model.common.ResourceType;

public class ResourceAttendence {
	private String attendanceObjectId;
	private String attendanceCode;
	private String attendanceDesc;
	private String updatedBy;	
	private String absentDay;
	private String month;
	private String year;
	private String resourceId;	
	private int attendanceDetailsId;
	private int extraWorkingDays;
	private String compensationType;
	private String compentationLimitationInYear;
	private int totalDays;
	private int presentDays;	
	private String startDate;
	private String endDate;			
	private ResourceType resourceType;
	public String getAttendanceObjectId() {
		return attendanceObjectId;
	}
	public void setAttendanceObjectId(String attendanceObjectId) {
		this.attendanceObjectId = attendanceObjectId;
	}
	public String getAttendanceCode() {
		return attendanceCode;
	}
	public void setAttendanceCode(String attendanceCode) {
		this.attendanceCode = attendanceCode;
	}
	public String getAttendanceDesc() {
		return attendanceDesc;
	}
	public void setAttendanceDesc(String attendanceDesc) {
		this.attendanceDesc = attendanceDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getAbsentDay() {
		return absentDay;
	}
	public void setAbsentDay(String absentDay) {
		this.absentDay = absentDay;
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
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public int getAttendanceDetailsId() {
		return attendanceDetailsId;
	}
	public void setAttendanceDetailsId(int attendanceDetailsId) {
		this.attendanceDetailsId = attendanceDetailsId;
	}
	public int getExtraWorkingDays() {
		return extraWorkingDays;
	}
	public void setExtraWorkingDays(int extraWorkingDays) {
		this.extraWorkingDays = extraWorkingDays;
	}
	public String getCompensationType() {
		return compensationType;
	}
	public void setCompensationType(String compensationType) {
		this.compensationType = compensationType;
	}
	public String getCompentationLimitationInYear() {
		return compentationLimitationInYear;
	}
	public void setCompentationLimitationInYear(String compentationLimitationInYear) {
		this.compentationLimitationInYear = compentationLimitationInYear;
	}
	public int getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	public int getPresentDays() {
		return presentDays;
	}
	public void setPresentDays(int presentDays) {
		this.presentDays = presentDays;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	
	
}
