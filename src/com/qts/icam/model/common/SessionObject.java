package com.qts.icam.model.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionObject {

	private String objectId;	
	private String userId;	
	private String userName;
	private Resource resource;
	private String resourceTpye; 
	private String userImage;
	private Map<String, String> availableRolesAndAccess;
	private String currentRoleOrAccess;
	private Set<String> availableRoles;
	private String lastVisitedOn;
	
	/******Added By Naimisha 22042017*****/
	private List<Course>courseList;
	private String courseCode;
	private String surveyId;
	
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}	
	public String getResourceTpye() {
		return resourceTpye;
	}
	public void setResourceTpye(String resourceTpye) {
		this.resourceTpye = resourceTpye;
	}
	public Map<String, String> getAvailableRolesAndAccess() {
		return availableRolesAndAccess;
	}
	public void setAvailableRolesAndAccess(
			Map<String, String> availableRolesAndAccess) {
		this.availableRolesAndAccess = availableRolesAndAccess;
	}
	public String getCurrentRoleOrAccess() {
		return currentRoleOrAccess;
	}
	public void setCurrentRoleOrAccess(String currentRoleOrAccess) {
		this.currentRoleOrAccess = currentRoleOrAccess;
	}
	public Set<String> getAvailableRoles() {
		return availableRoles;
	}
	public void setAvailableRoles(Set<String> availableRoles) {
		this.availableRoles = availableRoles;
	}
	public String getLastVisitedOn() {
		return lastVisitedOn;
	}
	public void setLastVisitedOn(String lastVisitedOn) {
		this.lastVisitedOn = lastVisitedOn;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}	
	
	
}
