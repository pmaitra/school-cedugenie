package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.backoffice.Term;

public class StudentFeesTemplate {
	
	private int studentFeesTemplateId;
	private String studentFeesTemplateObjectId;
	private String studentFeesTemplateCode;
	private String studentFeesTemplateName;
	private String studentFeesTemplateDesc;
	private String updatedBy;
	private String status;
	private Course course;
	private List<FeesCategory> feesCategoryList;
	private FeesCategory feesCategory;
	private SocialCategory socialCategory;
	private List<StudentFeesTemplateDetails> studentFeesTemplateDetailsList;
	private List<StudentFeesTemplateDetails> studentFeesTemplateDetailsListOld;
	private Term term;
	
	
	public int getStudentFeesTemplateId() {
		return studentFeesTemplateId;
	}
	public void setStudentFeesTemplateId(int studentFeesTemplateId) {
		this.studentFeesTemplateId = studentFeesTemplateId;
	}
	public String getStudentFeesTemplateObjectId() {
		return studentFeesTemplateObjectId;
	}
	public void setStudentFeesTemplateObjectId(String studentFeesTemplateObjectId) {
		this.studentFeesTemplateObjectId = studentFeesTemplateObjectId;
	}
	public String getStudentFeesTemplateCode() {
		return studentFeesTemplateCode;
	}
	public void setStudentFeesTemplateCode(String studentFeesTemplateCode) {
		this.studentFeesTemplateCode = studentFeesTemplateCode;
	}
	public String getStudentFeesTemplateName() {
		return studentFeesTemplateName;
	}
	public void setStudentFeesTemplateName(String studentFeesTemplateName) {
		this.studentFeesTemplateName = studentFeesTemplateName;
	}
	public String getStudentFeesTemplateDesc() {
		return studentFeesTemplateDesc;
	}
	public void setStudentFeesTemplateDesc(String studentFeesTemplateDesc) {
		this.studentFeesTemplateDesc = studentFeesTemplateDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<FeesCategory> getFeesCategoryList() {
		return feesCategoryList;
	}
	public void setFeesCategoryList(List<FeesCategory> feesCategoryList) {
		this.feesCategoryList = feesCategoryList;
	}
	public FeesCategory getFeesCategory() {
		return feesCategory;
	}
	public void setFeesCategory(FeesCategory feesCategory) {
		this.feesCategory = feesCategory;
	}
	public SocialCategory getSocialCategory() {
		return socialCategory;
	}
	public void setSocialCategory(SocialCategory socialCategory) {
		this.socialCategory = socialCategory;
	}
	public List<StudentFeesTemplateDetails> getStudentFeesTemplateDetailsList() {
		return studentFeesTemplateDetailsList;
	}
	public void setStudentFeesTemplateDetailsList(List<StudentFeesTemplateDetails> studentFeesTemplateDetailsList) {
		this.studentFeesTemplateDetailsList = studentFeesTemplateDetailsList;
	}
	public List<StudentFeesTemplateDetails> getStudentFeesTemplateDetailsListOld() {
		return studentFeesTemplateDetailsListOld;
	}
	public void setStudentFeesTemplateDetailsListOld(List<StudentFeesTemplateDetails> studentFeesTemplateDetailsListOld) {
		this.studentFeesTemplateDetailsListOld = studentFeesTemplateDetailsListOld;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
}
