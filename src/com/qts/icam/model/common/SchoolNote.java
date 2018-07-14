package com.qts.icam.model.common;

import java.util.List;

public class SchoolNote {

	private String updatedBy;
	private String note;
	private String description;
	private String json;
	private String objectId;
	private String sender;
	private String academicSession;
	private List<Standard> standardList;
	private List<String> rollList;
	private String general;
	private String dateString;
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getAcademicSession() {
		return academicSession;
	}
	public void setAcademicSession(String academicSession) {
		this.academicSession = academicSession;
	}
	public List<Standard> getStandardList() {
		return standardList;
	}
	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}
	public List<String> getRollList() {
		return rollList;
	}
	public void setRollList(List<String> rollList) {
		this.rollList = rollList;
	}
	public String getGeneral() {
		return general;
	}
	public void setGeneral(String general) {
		this.general = general;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
}
