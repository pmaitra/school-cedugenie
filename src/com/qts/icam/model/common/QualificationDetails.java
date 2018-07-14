package com.qts.icam.model.common;

import java.util.List;

public class QualificationDetails {

	private int academicId;
	private String degree;
	private String specialization;
	private String instituteName;
	private String affiliation;
	private String statusOfCompletion;
	private long previousCourseStartDate;
	private long previousCourseEndDate;
	private double percentage;
	private double cgpa;
	
	public int getAcademicId() {
		return academicId;
	}
	public void setAcademicId(int academicId) {
		this.academicId = academicId;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	public String getStatusOfCompletion() {
		return statusOfCompletion;
	}
	public void setStatusOfCompletion(String statusOfCompletion) {
		this.statusOfCompletion = statusOfCompletion;
	}	
	public long getPreviousCourseStartDate() {
		return previousCourseStartDate;
	}
	public void setPreviousCourseStartDate(long previousCourseStartDate) {
		this.previousCourseStartDate = previousCourseStartDate;
	}
	public long getPreviousCourseEndDate() {
		return previousCourseEndDate;
	}
	public void setPreviousCourseEndDate(long previousCourseEndDate) {
		this.previousCourseEndDate = previousCourseEndDate;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
}
