package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.academics.Subject;

public class SubjectCategory {

	private String subjectCategoryObjectId;
	private String subjectCategoryCode;
	private String subjectCategoryDesc;
	private String updatedBy;
	private String subjectCategoryName;
	private List<Subject> subjectList;
	private String status;
	
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	public String getSubjectCategoryObjectId() {
		return subjectCategoryObjectId;
	}
	public void setSubjectCategoryObjectId(String subjectCategoryObjectId) {
		this.subjectCategoryObjectId = subjectCategoryObjectId;
	}
	public String getSubjectCategoryCode() {
		return subjectCategoryCode;
	}
	public void setSubjectCategoryCode(String subjectCategoryCode) {
		this.subjectCategoryCode = subjectCategoryCode;
	}
	public String getSubjectCategoryDesc() {
		return subjectCategoryDesc;
	}
	public void setSubjectCategoryDesc(String subjectCategoryDesc) {
		this.subjectCategoryDesc = subjectCategoryDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSubjectCategoryName() {
		return subjectCategoryName;
	}
	public void setSubjectCategoryName(String subjectCategoryName) {
		this.subjectCategoryName = subjectCategoryName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
