package com.qts.icam.model.academics;

import java.util.List;

import com.qts.icam.model.backoffice.Exam;

public class CourseSubjectMapping {
	private int mappingId;
	private String objectId;
	private String courseCode;
	private String updatedBy;
	private String subject;
	private List<String> newSubjectList;
	private List<String> oldSubjectList;
	
	private List<Exam> examList;
	
	/*****Added By Sourav 01032017******/
	private String term;
	private List<String> subjectList;
	private int courseCredit;
	
	public int getMappingId() {
		return mappingId;
	}
	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<String> getNewSubjectList() {
		return newSubjectList;
	}
	public void setNewSubjectList(List<String> newSubjectList) {
		this.newSubjectList = newSubjectList;
	}
	public List<String> getOldSubjectList() {
		return oldSubjectList;
	}
	public void setOldSubjectList(List<String> oldSubjectList) {
		this.oldSubjectList = oldSubjectList;
	}
	public List<Exam> getExamList() {
		return examList;
	}
	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public List<String> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<String> subjectList) {
		this.subjectList = subjectList;
	}
	public int getCourseCredit() {
		return courseCredit;
	}
	public void setCourseCredit(int courseCredit) {
		this.courseCredit = courseCredit;
	}
	
}
