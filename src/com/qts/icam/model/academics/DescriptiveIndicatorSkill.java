package com.qts.icam.model.academics;

import java.util.List;

public class DescriptiveIndicatorSkill {
	private int serialId;
	private String objectId;
	private String updatedBy;
	private String skillCode;
	private String skillName;
	private String skillDesc;
	private String status;
	private int maxHeads;
	private List<DescriptiveIndicatorHead> headList;
	
	
	public int getSerialId() {
		return serialId;
	}
	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSkillCode() {
		return skillCode;
	}
	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getSkillDesc() {
		return skillDesc;
	}
	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMaxHeads() {
		return maxHeads;
	}
	public void setMaxHeads(int maxHeads) {
		this.maxHeads = maxHeads;
	}
	public List<DescriptiveIndicatorHead> getHeadList() {
		return headList;
	}
	public void setHeadList(List<DescriptiveIndicatorHead> headList) {
		this.headList = headList;
	}	
}
