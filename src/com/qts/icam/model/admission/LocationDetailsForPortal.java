package com.qts.icam.model.admission;

import java.util.List;

import com.qts.icam.model.common.Location;
import com.qts.icam.model.common.NameId;
import com.qts.icam.model.common.Resource;

public class LocationDetailsForPortal {
	private String venueTeacherMappingId;
	private int programId;
	private String city;
	private String area;
	private String stateName;
	private String name;
	private String zoneName;
	private String pin;
	private String interviewDate;
	private Location location;
	private String venueName;
	private List<NameId> nameIdList; 
	private String venueCode;
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	public String getVenueTeacherMappingId() {
		return venueTeacherMappingId;
	}
	public void setVenueTeacherMappingId(String venueTeacherMappingId) {
		this.venueTeacherMappingId = venueTeacherMappingId;
	}
	public String getVenueCode() {
		return venueCode;
	}
	public void setVenueCode(String venueCode) {
		this.venueCode = venueCode;
	}
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	public List<NameId> getNameIdList() {
		return nameIdList;
	}
	public void setNameIdList(List<NameId> nameIdList) {
		this.nameIdList = nameIdList;
	}
	
}
