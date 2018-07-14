package com.qts.icam.model.common;

import java.util.List;

public class Marks {
	
	private String marksObjectId;
	private String marksCode;
	private String marksName;
	private String marksDesc;
	private String updatedBy;
	private String subjectName;
	private int marks;
	private int fullMarks;
	private int passMarks;
	private int highestMarks;
	//used for mapping in mapper
	private String studentId;
	private String className;
	private String sectionName;
	private String streamName;
	private String academicYear;
	private String examType;
	private int unitMarks;
	private int unitFullMarks;
	private double avgMarks;
	private String status;
	private String courseName;
	private String termName;
	private String examName;
	private String subjectGroup;
	private List<Marks> listMarks;
	//-------------setter&getter-----------

	public String getMarksObjectId() {
		return marksObjectId;
	}
	public void setMarksObjectId(String marksObjectId) {
		this.marksObjectId = marksObjectId;
	}
	public String getMarksCode() {
		return marksCode;
	}
	public void setMarksCode(String marksCode) {
		this.marksCode = marksCode;
	}
	public String getMarksDesc() {
		return marksDesc;
	}
	public void setMarksDesc(String marksDesc) {
		this.marksDesc = marksDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public int getFullMarks() {
		return fullMarks;
	}
	public void setFullMarks(int fullMarks) {
		this.fullMarks = fullMarks;
	}
	public int getPassMarks() {
		return passMarks;
	}
	public void setPassMarks(int passMarks) {
		this.passMarks = passMarks;
	}
	public int getHighestMarks() {
		return highestMarks;
	}
	public void setHighestMarks(int highestMarks) {
		this.highestMarks = highestMarks;
	}
	public String getMarksName() {
		return marksName;
	}
	public void setMarksName(String marksName) {
		this.marksName = marksName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public int getUnitMarks() {
		return unitMarks;
	}
	public void setUnitMarks(int unitMarks) {
		this.unitMarks = unitMarks;
	}
	public int getUnitFullMarks() {
		return unitFullMarks;
	}
	public void setUnitFullMarks(int unitFullMarks) {
		this.unitFullMarks = unitFullMarks;
	}
	public double getAvgMarks() {
		return avgMarks;
	}
	public void setAvgMarks(double avgMarks) {
		this.avgMarks = avgMarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getSubjectGroup() {
		return subjectGroup;
	}
	public void setSubjectGroup(String subjectGroup) {
		this.subjectGroup = subjectGroup;
	}
	public List<Marks> getListMarks() {
		return listMarks;
	}
	public void setListMarks(List<Marks> listMarks) {
		this.listMarks = listMarks;
	}
	
	
}
