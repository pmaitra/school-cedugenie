package com.qts.icam.model.academics;

import java.util.List;

public class SubjectGroup {
	private int subjectGroupId;
	private String objectId;
	private String subjectGroupCode;
	private String desc;
	private String updatedBy;
	private String subjectGroupName;
	private String status;
	private Integer subjectGroupOrderId;
	private List<Subject> subjectList; /*Added by naimisha for ERP*/
	
	/**Added For NISM**/
	
	private Integer totalHRSForCourse;
	/**anup.roy 04.08.2017**/
	
	private String scholasticTypeCode;
	private String scholasticTypeName;
	
	
	/*Added by naimisha 18052018*/
	private String taskNo;
	private String ticketNo;
	
	public int getSubjectGroupId() {
		return subjectGroupId;
	}
	public void setSubjectGroupId(int subjectGroupId) {
		this.subjectGroupId = subjectGroupId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getSubjectGroupCode() {
		return subjectGroupCode;
	}
	public void setSubjectGroupCode(String subjectGroupCode) {
		this.subjectGroupCode = subjectGroupCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSubjectGroupName() {
		return subjectGroupName;
	}
	public void setSubjectGroupName(String subjectGroupName) {
		this.subjectGroupName = subjectGroupName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getSubjectGroupOrderId() {
		return subjectGroupOrderId;
	}
	public void setSubjectGroupOrderId(Integer subjectGroupOrderId) {
		this.subjectGroupOrderId = subjectGroupOrderId;
	}
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	public Integer getTotalHRSForCourse() {
		return totalHRSForCourse;
	}
	public void setTotalHRSForCourse(Integer totalHRSForCourse) {
		this.totalHRSForCourse = totalHRSForCourse;
	}
	public String getScholasticTypeCode() {
		return scholasticTypeCode;
	}
	public void setScholasticTypeCode(String scholasticTypeCode) {
		this.scholasticTypeCode = scholasticTypeCode;
	}
	public String getScholasticTypeName() {
		return scholasticTypeName;
	}
	public void setScholasticTypeName(String scholasticTypeName) {
		this.scholasticTypeName = scholasticTypeName;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
}
