package com.qts.icam.model.finance;

import java.util.List;

public class Group {
	
	private int groupSerialId;
	
	private String objectId;
	private String updatedBy;

	private String groupCode;
	private String groupName;
	private String parentGroupCode;
	private String parentGroupName;
	private String status;
	
	/*/Added By Naimisha 27052017/*/
	private String debitStatus;
	private String creditStatus;
	private Double totalAmount;
	private String groupTypeCode;
	private String groupTypeName;
	private List<Group> parentGroupList;
	private List<Group> childGroupList;
	private List<Group> subChildGroupList;
	private List<Group> totalList;
	private String subGroupName;
	private String subGroupCode;
	private String childName;
	private String childCode;
	private String details;
	private String ledgerName;
	private Double currentBalance;
	private Double value;
	private Double creditAmmount;
	private Double debitAmmount;
	private String subChildName;
	private Double prevBalance;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public List<Group> getTotalList() {
		return totalList;
	}

	public void setTotalList(List<Group> totalList) {
		this.totalList = totalList;
	}

	
	public Double getCreditAmmount() {
		return creditAmmount;
	}

	public void setCreditAmmount(Double creditAmmount) {
		this.creditAmmount = creditAmmount;
	}

	public Double getDebitAmmount() {
		return debitAmmount;
	}

	public void setDebitAmmount(Double debitAmmount) {
		this.debitAmmount = debitAmmount;
	}

	public Double getPrevBalance() {
		return prevBalance;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void setPrevBalance(Double prevBalance) {
		this.prevBalance = prevBalance;
	}

	
	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getLedgerName() {
		return ledgerName;
	}

	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

	
	
	public String getSubChildName() {
		return subChildName;
	}

	public void setSubChildName(String subChildName) {
		this.subChildName = subChildName;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getSubGroupName() {
		return subGroupName;
	}

	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}

	public String getSubGroupCode() {
		return subGroupCode;
	}

	public void setSubGroupCode(String subGroupCode) {
		this.subGroupCode = subGroupCode;
	}

	public int getGroupSerialId() {
		return groupSerialId;
	}

	public void setGroupSerialId(int groupSerialId) {
		this.groupSerialId = groupSerialId;
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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getParentGroupCode() {
		return parentGroupCode;
	}

	public void setParentGroupCode(String parentGroupCode) {
		this.parentGroupCode = parentGroupCode;
	}

	public String getParentGroupName() {
		return parentGroupName;
	}

	public void setParentGroupName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}

	public String getGroupTypeCode() {
		return groupTypeCode;
	}

	public void setGroupTypeCode(String groupTypeCode) {
		this.groupTypeCode = groupTypeCode;
	}

	public String getGroupTypeName() {
		return groupTypeName;
	}

	public void setGroupTypeName(String groupTypeName) {
		this.groupTypeName = groupTypeName;
	}

	public List<Group> getParentGroupList() {
		return parentGroupList;
	}

	public void setParentGroupList(List<Group> parentGroupList) {
		this.parentGroupList = parentGroupList;
	}

	public List<Group> getChildGroupList() {
		return childGroupList;
	}

	public void setChildGroupList(List<Group> childGroupList) {
		this.childGroupList = childGroupList;
	}

	public List<Group> getSubChildGroupList() {
		return subChildGroupList;
	}

	public void setSubChildGroupList(List<Group> subChildGroupList) {
		this.subChildGroupList = subChildGroupList;
	}

	public String getDebitStatus() {
		return debitStatus;
	}

	public void setDebitStatus(String debitStatus) {
		this.debitStatus = debitStatus;
	}

	public String getCreditStatus() {
		return creditStatus;
	}

	public void setCreditStatus(String creditStatus) {
		this.creditStatus = creditStatus;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
