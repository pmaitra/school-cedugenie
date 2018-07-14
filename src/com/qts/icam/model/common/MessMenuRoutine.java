package com.qts.icam.model.common;

import java.util.List;

/**
 * @author anup.roy
 * */
public class MessMenuRoutine {

	private String objectId;
	private String updatedBy;
	private String messMenuRoutineCode;
	private String messMenuRoutineName;
	private String messMenuRoutineDesc;	
	private List<RoutineSlab> routineSlabList;	
	private String startDate;
	private String endDate;
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
	 * @return the messMenuRoutineCode
	 */
	public String getMessMenuRoutineCode() {
		return messMenuRoutineCode;
	}
	/**
	 * @param messMenuRoutineCode the messMenuRoutineCode to set
	 */
	public void setMessMenuRoutineCode(String messMenuRoutineCode) {
		this.messMenuRoutineCode = messMenuRoutineCode;
	}
	/**
	 * @return the messMenuRoutineName
	 */
	public String getMessMenuRoutineName() {
		return messMenuRoutineName;
	}
	/**
	 * @param messMenuRoutineName the messMenuRoutineName to set
	 */
	public void setMessMenuRoutineName(String messMenuRoutineName) {
		this.messMenuRoutineName = messMenuRoutineName;
	}
	/**
	 * @return the messMenuRoutineDesc
	 */
	public String getMessMenuRoutineDesc() {
		return messMenuRoutineDesc;
	}
	/**
	 * @param messMenuRoutineDesc the messMenuRoutineDesc to set
	 */
	public void setMessMenuRoutineDesc(String messMenuRoutineDesc) {
		this.messMenuRoutineDesc = messMenuRoutineDesc;
	}
	/**
	 * @return the routineSlabList
	 */
	public List<RoutineSlab> getRoutineSlabList() {
		return routineSlabList;
	}
	/**
	 * @param routineSlabList the routineSlabList to set
	 */
	public void setRoutineSlabList(List<RoutineSlab> routineSlabList) {
		this.routineSlabList = routineSlabList;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
