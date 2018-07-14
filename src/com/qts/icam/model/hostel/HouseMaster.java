package com.qts.icam.model.hostel;

import com.qts.icam.model.common.AcademicYear;

public class HouseMaster {

	private String objectId;
	private String updatedBy;
	private AcademicYear academicYear;
	private House house;
	private String houseMasterName;
	private String asstHouseMasterName;
	private String houseCode;
	private String houseName;
	
	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}
	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the academicYear
	 */
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	/**
	 * @return the house
	 */
	public House getHouse() {
		return house;
	}
	/**
	 * @param house the house to set
	 */
	public void setHouse(House house) {
		this.house = house;
	}
	/**
	 * @return the houseMasterName
	 */
	public String getHouseMasterName() {
		return houseMasterName;
	}
	/**
	 * @param houseMasterName the houseMasterName to set
	 */
	public void setHouseMasterName(String houseMasterName) {
		this.houseMasterName = houseMasterName;
	}
	/**
	 * @return the asstHouseMasterName
	 */
	public String getAsstHouseMasterName() {
		return asstHouseMasterName;
	}
	/**
	 * @param asstHouseMasterName the asstHouseMasterName to set
	 */
	public void setAsstHouseMasterName(String asstHouseMasterName) {
		this.asstHouseMasterName = asstHouseMasterName;
	}
	/**
	 * @return the houseCode
	 */
	public String getHouseCode() {
		return houseCode;
	}
	/**
	 * @param houseCode the houseCode to set
	 */
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	/**
	 * @return the houseName
	 */
	public String getHouseName() {
		return houseName;
	}
	/**
	 * @param houseName the houseName to set
	 */
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	
}
