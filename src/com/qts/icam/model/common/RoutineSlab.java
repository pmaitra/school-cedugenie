package com.qts.icam.model.common;

import java.util.List;

/**
 * @author anup.roy
 * */
public class RoutineSlab {

	private String routineSlabObjectId;
	private String updatedBy;
	private String routineSlabCode;
	private String routineSlabName;
	private String routineSlabDesc;
	private List<MessMenuTime> messMenuTimeList;
	/**
	 * @return the routineSlabObjectId
	 */
	public String getRoutineSlabObjectId() {
		return routineSlabObjectId;
	}
	/**
	 * @param routineSlabObjectId the routineSlabObjectId to set
	 */
	public void setRoutineSlabObjectId(String routineSlabObjectId) {
		this.routineSlabObjectId = routineSlabObjectId;
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
	 * @return the routineSlabCode
	 */
	public String getRoutineSlabCode() {
		return routineSlabCode;
	}
	/**
	 * @param routineSlabCode the routineSlabCode to set
	 */
	public void setRoutineSlabCode(String routineSlabCode) {
		this.routineSlabCode = routineSlabCode;
	}
	/**
	 * @return the routineSlabName
	 */
	public String getRoutineSlabName() {
		return routineSlabName;
	}
	/**
	 * @param routineSlabName the routineSlabName to set
	 */
	public void setRoutineSlabName(String routineSlabName) {
		this.routineSlabName = routineSlabName;
	}
	/**
	 * @return the routineSlabDesc
	 */
	public String getRoutineSlabDesc() {
		return routineSlabDesc;
	}
	/**
	 * @param routineSlabDesc the routineSlabDesc to set
	 */
	public void setRoutineSlabDesc(String routineSlabDesc) {
		this.routineSlabDesc = routineSlabDesc;
	}
	/**
	 * @return the messMenuTimeList
	 */
	public List<MessMenuTime> getMessMenuTimeList() {
		return messMenuTimeList;
	}
	/**
	 * @param messMenuTimeList the messMenuTimeList to set
	 */
	public void setMessMenuTimeList(List<MessMenuTime> messMenuTimeList) {
		this.messMenuTimeList = messMenuTimeList;
	}	
}
