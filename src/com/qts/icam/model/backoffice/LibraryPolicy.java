package com.qts.icam.model.backoffice;

import java.util.List;

import com.qts.icam.model.backoffice.Rating;
import com.qts.icam.model.common.ResourceType;

public class LibraryPolicy {



	private String libraryPolicyObjectId;
	private String libraryPolicyCode;
	private String libraryPolicyName;
	private String libraryPolicyDesc;
	private String updatedBy;
	
	//Book Policy
	private int maxNoOfBookReq;
	private int maxNoOfBooksPerReq;
	private int sameBookAcrossReq;
	private List<Rating> ratingList; 
	//Fine Policy
	private double finePerDay;
	private double maxFine;
	private double overDueFine;
	private double processingFee;
	
	private int maxNoOfBookAllocated;	
	private ResourceType resourceType;
	private int libraryPolicyId;
	
	
		
	public int getMaxNoOfBookAllocated() {
		return maxNoOfBookAllocated;
	}
	public void setMaxNoOfBookAllocated(int maxNoOfBookAllocated) {
		this.maxNoOfBookAllocated = maxNoOfBookAllocated;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	public int getLibraryPolicyId() {
		return libraryPolicyId;
	}
	public void setLibraryPolicyId(int libraryPolicyId) {
		this.libraryPolicyId = libraryPolicyId;
	}
	public String getLibraryPolicyObjectId() {
		return libraryPolicyObjectId;
	}
	public void setLibraryPolicyObjectId(String libraryPolicyObjectId) {
		this.libraryPolicyObjectId = libraryPolicyObjectId;
	}
	public String getLibraryPolicyCode() {
		return libraryPolicyCode;
	}
	public void setLibraryPolicyCode(String libraryPolicyCode) {
		this.libraryPolicyCode = libraryPolicyCode;
	}
	public String getLibraryPolicyName() {
		return libraryPolicyName;
	}
	public void setLibraryPolicyName(String libraryPolicyName) {
		this.libraryPolicyName = libraryPolicyName;
	}
	public String getLibraryPolicyDesc() {
		return libraryPolicyDesc;
	}
	public void setLibraryPolicyDesc(String libraryPolicyDesc) {
		this.libraryPolicyDesc = libraryPolicyDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getMaxNoOfBookReq() {
		return maxNoOfBookReq;
	}
	public void setMaxNoOfBookReq(int maxNoOfBookReq) {
		this.maxNoOfBookReq = maxNoOfBookReq;
	}
	public int getMaxNoOfBooksPerReq() {
		return maxNoOfBooksPerReq;
	}
	public void setMaxNoOfBooksPerReq(int maxNoOfBooksPerReq) {
		this.maxNoOfBooksPerReq = maxNoOfBooksPerReq;
	}
	
	public int getSameBookAcrossReq() {
		return sameBookAcrossReq;
	}
	public void setSameBookAcrossReq(int sameBookAcrossReq) {
		this.sameBookAcrossReq = sameBookAcrossReq;
	}
	public List<Rating> getRatingList() {
		return ratingList;
	}
	public void setRatingList(List<Rating> ratingList) {
		this.ratingList = ratingList;
	}
	public double getFinePerDay() {
		return finePerDay;
	}
	public void setFinePerDay(double finePerDay) {
		this.finePerDay = finePerDay;
	}
	public double getMaxFine() {
		return maxFine;
	}
	public void setMaxFine(double maxFine) {
		this.maxFine = maxFine;
	}
	public double getOverDueFine() {
		return overDueFine;
	}
	public void setOverDueFine(double overDueFine) {
		this.overDueFine = overDueFine;
	}
	public double getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(double processingFee) {
		this.processingFee = processingFee;
	}
	

}
