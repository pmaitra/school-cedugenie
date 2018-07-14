package com.qts.icam.model.administrator;

public class PreviousData {
	private String objectId;
	private String updatedBy;
	
	private boolean academic_year ;
	private boolean class_stream_section_teaching_level ;
	private boolean student;
	private boolean staff;
	private boolean subject;
	private boolean subject_course;
	private boolean student_subject;
	private boolean book;
	private String next;
	
	
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
	public boolean isAcademic_year() {
		return academic_year;
	}
	public void setAcademic_year(boolean academic_year) {
		this.academic_year = academic_year;
	}
	public boolean isClass_stream_section_teaching_level() {
		return class_stream_section_teaching_level;
	}
	public void setClass_stream_section_teaching_level(
			boolean class_stream_section_teaching_level) {
		this.class_stream_section_teaching_level = class_stream_section_teaching_level;
	}
	public boolean isStudent() {
		return student;
	}
	public void setStudent(boolean student) {
		this.student = student;
	}
	public boolean isStaff() {
		return staff;
	}
	public void setStaff(boolean staff) {
		this.staff = staff;
	}
	public boolean isSubject() {
		return subject;
	}
	public void setSubject(boolean subject) {
		this.subject = subject;
	}
	public boolean isSubject_course() {
		return subject_course;
	}
	public void setSubject_course(boolean subject_course) {
		this.subject_course = subject_course;
	}
	public boolean isStudent_subject() {
		return student_subject;
	}
	public void setStudent_subject(boolean student_subject) {
		this.student_subject = student_subject;
	}
	public boolean isBook() {
		return book;
	}
	public void setBook(boolean book) {
		this.book = book;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
}
