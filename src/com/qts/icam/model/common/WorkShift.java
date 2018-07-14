package com.qts.icam.model.common;

public class WorkShift {	
	private String workShiftObjectId;
	private String updatedBy;
	private String workShiftCode;
	private String workShiftName;
	private String workShiftDesc;	
	//change
	private String workShiftStartTime;
	private String workShiftEndTime;
	//
	private String status;
	private String userId;
	
	
	
	public String getWorkShiftStartTime() {
		return workShiftStartTime;
	}
	public void setWorkShiftStartTime(String workShiftStartTime) {
		this.workShiftStartTime = workShiftStartTime;
	}
	public String getWorkShiftEndTime() {
		return workShiftEndTime;
	}
	public void setWorkShiftEndTime(String workShiftEndTime) {
		this.workShiftEndTime = workShiftEndTime;
	}
	public String getWorkShiftObjectId() {
		return workShiftObjectId;
	}
	public void setWorkShiftObjectId(String workShiftObjectId) {
		this.workShiftObjectId = workShiftObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getWorkShiftCode() {
		return workShiftCode;
	}
	public void setWorkShiftCode(String workShiftCode) {
		this.workShiftCode = workShiftCode;
	}
	public String getWorkShiftName() {
		return workShiftName;
	}
	public void setWorkShiftName(String workShiftName) {
		this.workShiftName = workShiftName;
	}
	public String getWorkShiftDesc() {
		return workShiftDesc;
	}
	public void setWorkShiftDesc(String workShiftDesc) {
		this.workShiftDesc = workShiftDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}	
	
}
