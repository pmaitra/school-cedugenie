package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.academics.Subject;

public class SubjectType {
	
	private String subjectTypeObjectId;
	private String subjectTypeCode;
	private String subjectTypeDesc;
	private String updatedBy;
	private String subjectTypeName;
	private List<Subject> subjectList;
	

	public String getSubjectTypeObjectId() {
		return subjectTypeObjectId;
	}
	public void setSubjectTypeObjectId(String subjectTypeObjectId) {
		this.subjectTypeObjectId = subjectTypeObjectId;
	}
	public String getSubjectTypeCode() {
		return subjectTypeCode;
	}
	public void setSubjectTypeCode(String subjectTypeCode) {
		this.subjectTypeCode = subjectTypeCode;
	}
	public String getSubjectTypeDesc() {
		return subjectTypeDesc;
	}
	public void setSubjectTypeDesc(String subjectTypeDesc) {
		this.subjectTypeDesc = subjectTypeDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSubjectTypeName() {
		return subjectTypeName;
	}
	public void setSubjectTypeName(String subjectTypeName) {
		this.subjectTypeName = subjectTypeName;
	}
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

}
