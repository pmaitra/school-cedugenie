package com.qts.icam.model.academics;

import java.util.List;

public class DescriptiveIndicatorHead {
	private int serialId;
	private String objectId;
	private String updatedBy;
	private String headCode;
	private String headName;
	private String headDesc;
	private String status;
	private List<DescriptiveIndicator> indicatorList;
	
	
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
	public String getHeadCode() {
		return headCode;
	}
	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getHeadDesc() {
		return headDesc;
	}
	public void setHeadDesc(String headDesc) {
		this.headDesc = headDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<DescriptiveIndicator> getIndicatorList() {
		return indicatorList;
	}
	public void setIndicatorList(List<DescriptiveIndicator> indicatorList) {
		this.indicatorList = indicatorList;
	}
}
