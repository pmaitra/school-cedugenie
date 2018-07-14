package com.qts.icam.model.backoffice;

public class AttendancePolicy {
	

	private String attendancePolicyObjectId;
	private String attendancePolicyCode;
	private String attendancePolicyDesc;
	private String updatedBy;
	private String compensation;
	private int limitationOfDay;
	private String overTime;
	private int attendancePolicyId;
	
	
	
	public int getAttendancePolicyId() {
		return attendancePolicyId;
	}
	public void setAttendancePolicyId(int attendancePolicyId) {
		this.attendancePolicyId = attendancePolicyId;
	}
	public String getAttendancePolicyObjectId() {
		return attendancePolicyObjectId;
	}
	public void setAttendancePolicyObjectId(String attendancePolicyObjectId) {
		this.attendancePolicyObjectId = attendancePolicyObjectId;
	}
	public String getAttendancePolicyCode() {
		return attendancePolicyCode;
	}
	public void setAttendancePolicyCode(String attendancePolicyCode) {
		this.attendancePolicyCode = attendancePolicyCode;
	}
	public String getAttendancePolicyDesc() {
		return attendancePolicyDesc;
	}
	public void setAttendancePolicyDesc(String attendancePolicyDesc) {
		this.attendancePolicyDesc = attendancePolicyDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCompensation() {
		return compensation;
	}
	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}
	public int getLimitationOfDay() {
		return limitationOfDay;
	}
	public void setLimitationOfDay(int limitationOfDay) {
		this.limitationOfDay = limitationOfDay;
	}
	public String getOverTime() {
		return overTime;
	}
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}


}
