package com.qts.icam.model.administrator;

import javax.xml.bind.annotation.XmlElement;

public class SubjectsForXml {
	String subject;
	String subjectGroup;
	
	public String getSubject() {
		return subject;
	}
	@XmlElement(name="Subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getSubjectGroup() {
		return subjectGroup;
	}
	@XmlElement(name="SubjectGroup")
	public void setSubjectGroup(String subjectGroup) {
		this.subjectGroup = subjectGroup;
	}
}
