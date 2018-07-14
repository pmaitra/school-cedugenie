package com.qts.icam.model.backoffice;

import java.util.List;

import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.erp.EmployeeType;
import com.qts.icam.model.erp.JobType;


/**
 * 
 * @author sayani.datta
 *
 */
public class AcademicLeave {
	private String leaveObjectId;
	private String leaveCode;
	private String leaveDesc;
	private String leaveName;
	private int leaveTypeId;
	private String updatedBy;
	private AcademicYear academicYear;
	private JobType jobType;
	private EmployeeType employeeType;	
	private AcademicLeaveCategory academicLeaveType;
	private int leaveDuration;
	private boolean leaveEncashment;
	private int leaveLimit;
	private String leaveValidUpto;
	private List<AcademicLeaveCategory> AcademicLeaveCategory;
	private boolean leaveCarryForward;
	private String status;
	
	public boolean isLeaveCarryForward() {
		return leaveCarryForward;
	}
	public void setLeaveCarryForward(boolean leaveCarryForward) {
		this.leaveCarryForward = leaveCarryForward;
	}
	public String getLeaveObjectId() {
		return leaveObjectId;
	}
	public void setLeaveObjectId(String leaveObjectId) {
		this.leaveObjectId = leaveObjectId;
	}
	public String getLeaveCode() {
		return leaveCode;
	}
	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}
	public String getLeaveDesc() {
		return leaveDesc;
	}
	public void setLeaveDesc(String leaveDesc) {
		this.leaveDesc = leaveDesc;
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public int getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public int getLeaveDuration() {
		return leaveDuration;
	}
	public void setLeaveDuration(int leaveDuration) {
		this.leaveDuration = leaveDuration;
	}
	
	public boolean getLeaveEncashment() {
		return leaveEncashment;
	}
	public void setLeaveEncashment(boolean leaveEncashment) {
		this.leaveEncashment = leaveEncashment;
	}
	public int getLeaveLimit() {
		return leaveLimit;
	}
	public void setLeaveLimit(int leaveLimit) {
		this.leaveLimit = leaveLimit;
	}
	public String getLeaveValidUpto() {
		return leaveValidUpto;
	}
	public void setLeaveValidUpto(String leaveValidUpto) {
		this.leaveValidUpto = leaveValidUpto;
	}
	public List<AcademicLeaveCategory> getAcademicLeaveCategory() {
		return AcademicLeaveCategory;
	}
	public void setAcademicLeaveCategory(
			List<AcademicLeaveCategory> academicLeaveCategory) {
		AcademicLeaveCategory = academicLeaveCategory;
	}
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}
	public AcademicLeaveCategory getAcademicLeaveType() {
		return academicLeaveType;
	}
	public void setAcademicLeaveType(AcademicLeaveCategory academicLeaveType) {
		this.academicLeaveType = academicLeaveType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}
