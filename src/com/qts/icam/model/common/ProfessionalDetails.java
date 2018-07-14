package com.qts.icam.model.common;

public class ProfessionalDetails {

	private int professionalId;
	private String organization;
	private String designation;
	private String jobRole;
	private int jobStartDate;
	private int jobEndDate;
	
	public int getProfessionalId() {
		return professionalId;
	}
	public void setProfessionalId(int professionalId) {
		this.professionalId = professionalId;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public int getJobStartDate() {
		return jobStartDate;
	}
	public void setJobStartDate(int jobStartDate) {
		this.jobStartDate = jobStartDate;
	}
	public int getJobEndDate() {
		return jobEndDate;
	}
	public void setJobEndDate(int jobEndDate) {
		this.jobEndDate = jobEndDate;
	}
	
}
