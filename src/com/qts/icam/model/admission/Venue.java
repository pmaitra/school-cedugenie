package com.qts.icam.model.admission;

import com.qts.icam.model.common.Address;

public class Venue {
	private int venueId;
	private String objectId;
	private String venueCode;
	private String venueDesc;
	private String venueName;
	private String updatedBy;
	private String status;
	private String contactNo;
	private String mobileNo;
	private String email;
	private Address address;
	private int capacity;
	private long startSeatRollNo;
	private long endSeatRollNo;
	private int availableSeat;
	private long prevStartSeatRollNo;
	private long prevEndSeatRollNo;
	private String examTime;
	private String examDate;
	
	
	public int getVenueId() {
		return venueId;
	}
	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getVenueCode() {
		return venueCode;
	}
	public void setVenueCode(String venueCode) {
		this.venueCode = venueCode;
	}
	public String getVenueDesc() {
		return venueDesc;
	}
	public void setVenueDesc(String venueDesc) {
		this.venueDesc = venueDesc;
	}
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
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
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public long getStartSeatRollNo() {
		return startSeatRollNo;
	}
	public void setStartSeatRollNo(long startSeatRollNo) {
		this.startSeatRollNo = startSeatRollNo;
	}
	public long getEndSeatRollNo() {
		return endSeatRollNo;
	}
	public void setEndSeatRollNo(long endSeatRollNo) {
		this.endSeatRollNo = endSeatRollNo;
	}
	public int getAvailableSeat() {
		return availableSeat;
	}
	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}
	public long getPrevStartSeatRollNo() {
		return prevStartSeatRollNo;
	}
	public void setPrevStartSeatRollNo(long prevStartSeatRollNo) {
		this.prevStartSeatRollNo = prevStartSeatRollNo;
	}
	public long getPrevEndSeatRollNo() {
		return prevEndSeatRollNo;
	}
	public void setPrevEndSeatRollNo(long prevEndSeatRollNo) {
		this.prevEndSeatRollNo = prevEndSeatRollNo;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	
	
	
}
