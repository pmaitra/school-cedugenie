package com.qts.icam.model.administrator;

import javax.xml.bind.annotation.XmlElement;

public class SubjectMarksForXml {
	String subject;
	String subjectGroup;
	int fullMarks;
	int passMarks;
	int obtainedMarks;
	
	
	public String getSubject() {
		return subject;
	}
	@XmlElement(name="Subject")
	public void setSubject(String subjects) {
		this.subject = subjects;
	}
	
	public String getSubjectGroup() {
		return subjectGroup;
	}
	@XmlElement(name="SubjectGroup")
	public void setSubjectGroup(String subjectGroup) {
		this.subjectGroup = subjectGroup;
	}
	
	public int getFullMarks() {
		return fullMarks;
	}
	@XmlElement(name="FullMarks")
	public void setFullMarks(int fullMarks) {
		this.fullMarks = fullMarks;
	}
	
	public int getPassMarks() {
		return passMarks;
	}
	@XmlElement(name="PassMarks")
	public void setPassMarks(int passMarks) {
		this.passMarks = passMarks;
	}
	
	public int getObtainedMarks() {
		return obtainedMarks;
	}
	@XmlElement(name="ObtainedMarks")
	public void setObtainedMarks(int obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}
}
