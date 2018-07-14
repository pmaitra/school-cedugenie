package com.qts.icam.model.event;

import java.util.List;

public class SchoolEvent {
	private String schoolEventObjectId;
	private String eventName;
	private String eventDescription;
	private String eventStartDate; 
	private String eventEndDate;
	private String updatedBy;
	private String serialId;
	private List<EventAchievement> eventAchievementList;
	private EventAchievement eventAchievement;
	private String eventIncharge;
	
	
	public String getEventIncharge() {
		return eventIncharge;
	}
	public void setEventIncharge(String eventIncharge) {
		this.eventIncharge = eventIncharge;
	}
	public EventAchievement getEventAchievement() {
		return eventAchievement;
	}
	public void setEventAchievement(EventAchievement eventAchievement) {
		this.eventAchievement = eventAchievement;
	}
	public List<EventAchievement> getEventAchievementList() {
		return eventAchievementList;
	}
	public void setEventAchievementList(List<EventAchievement> eventAchievementList) {
		this.eventAchievementList = eventAchievementList;
	}
	
	public String getSerialId() {
		return serialId;
	}
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}
	public String getSchoolEventObjectId() {
		return schoolEventObjectId;
	}
	public void setSchoolEventObjectId(String schoolEventObjectId) {
		this.schoolEventObjectId = schoolEventObjectId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public String getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}
