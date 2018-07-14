package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.academics.CourseType;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;

public class SubjectList {
	
	private List<Subject> subjects;	
	private List<SubjectGroup> listSubjectGroup;
	private List<SubjectCategory> listSubjectCategory;
	private List<Class> listClass;
	private List<CourseType> listCourseType;

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<SubjectGroup> getListSubjectGroup() {
		return listSubjectGroup;
	}

	public void setListSubjectGroup(List<SubjectGroup> listSubjectGroup) {
		this.listSubjectGroup = listSubjectGroup;
	}

	public List<SubjectCategory> getListSubjectCategory() {
		return listSubjectCategory;
	}

	public void setListSubjectCategory(List<SubjectCategory> listSubjectCategory) {
		this.listSubjectCategory = listSubjectCategory;
	}

	public List<Class> getListClass() {
		return listClass;
	}

	public void setListClass(List<Class> listClass) {
		this.listClass = listClass;
	}

	public List<CourseType> getListCourseType() {
		return listCourseType;
	}

	public void setListCourseType(List<CourseType> listCourseType) {
		this.listCourseType = listCourseType;
	}
	

	
}
