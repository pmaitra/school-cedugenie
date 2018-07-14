package com.qts.icam.model.backoffice;

import java.util.List;

import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Student;

public class TeacherSubjectMapping {
	private int serialId;
	private String objectId;
	private String updatedBy;
	private String status;
	private Resource resource;
	private String subject;
	private String subjectGroup;
	private List<Subject> subjects;
	private List<String> newSubjectList;
	private List<String> oldSubjectList;
	
	public int getSerialId() {
		return serialId;
	}
	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
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
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubjectGroup() {
		return subjectGroup;
	}
	public void setSubjectGroup(String subjectGroup) {
		this.subjectGroup = subjectGroup;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
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
}
