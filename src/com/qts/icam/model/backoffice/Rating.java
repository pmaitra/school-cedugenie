package com.qts.icam.model.backoffice;

import com.qts.icam.model.common.ResourceType;

public class Rating {
	

	private String ratingObjectId;
	private String ratingCode;
	private String ratingName;
	private String ratingDesc;
	private String updatedBy;

	private int maxLendingPeriod;
	private int rating;
	private int minLendingsTo;
	private int minLendingsFrom;
	private int months;
	
	private String academicYear;
	private int libraryPolicyId;
	private ResourceType resourceType;
	
	
	
	
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public int getLibraryPolicyId() {
		return libraryPolicyId;
	}
	public void setLibraryPolicyId(int libraryPolicyId) {
		this.libraryPolicyId = libraryPolicyId;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	public String getRatingObjectId() {
		return ratingObjectId;
	}
	public void setRatingObjectId(String ratingObjectId) {
		this.ratingObjectId = ratingObjectId;
	}
	public String getRatingCode() {
		return ratingCode;
	}
	public void setRatingCode(String ratingCode) {
		this.ratingCode = ratingCode;
	}
	public String getRatingName() {
		return ratingName;
	}
	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}
	public String getRatingDesc() {
		return ratingDesc;
	}
	public void setRatingDesc(String ratingDesc) {
		this.ratingDesc = ratingDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getMaxLendingPeriod() {
		return maxLendingPeriod;
	}
	public void setMaxLendingPeriod(int maxLendingPeriod) {
		this.maxLendingPeriod = maxLendingPeriod;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}	
	public int getMinLendingsTo() {
		return minLendingsTo;
	}
	public void setMinLendingsTo(int minLendingsTo) {
		this.minLendingsTo = minLendingsTo;
	}
	public int getMinLendingsFrom() {
		return minLendingsFrom;
	}
	public void setMinLendingsFrom(int minLendingsFrom) {
		this.minLendingsFrom = minLendingsFrom;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	


}
