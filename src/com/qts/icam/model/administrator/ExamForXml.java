package com.qts.icam.model.administrator;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ExamForXml {
	String term;
	String examType;
	String examName;		
	String examTypeCode;
	String examCode;
	String termCode;
	String registrationId;
	String year;
	List<SubjectMarksForXml> subjectMarksList;
	
	public String getTerm() {
		return term;
	}
	@XmlElement(name="Term")
	public void setTerm(String term) {
		this.term = term;
	}
	
	public String getExamType() {
		return examType;
	}
	@XmlElement(name="ExamType")
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
	public String getExamName() {
		return examName;
	}
	@XmlElement(name="ExamName")
	public void setExamName(String examName) {
		this.examName = examName;
	}
	
	public List<SubjectMarksForXml> getSubjectMarksList() {
		return subjectMarksList;
	}
	@XmlElement(name="SubjectMarks")
	public void setSubjectMarksList(List<SubjectMarksForXml> subjectMarksList) {
		this.subjectMarksList = subjectMarksList;
	}
	public String getExamTypeCode() {
		return examTypeCode;
	}
	public void setExamTypeCode(String examTypeCode) {
		this.examTypeCode = examTypeCode;
	}
	public String getExamCode() {
		return examCode;
	}
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	public String getTermCode() {
		return termCode;
	}
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
