package com.qts.icam.model.backoffice;

import java.util.List;

import com.qts.icam.model.common.SocialCategory;

public class Fees {
	private int serialId;
	private String objectId;
	private String updatedBy;
	private String feesCode;
	private String feesName;
	private String desc;
	private String status;
	private List<SocialCategory> socialCategoryList;
	private String ledger;
	
	/**added by Saif Date- 05/09/2017*/
	private double amount;
	private double amountPaid;
	private double amountPayable;
	
	/**Saif addition ends*/
	
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
	public String getFeesCode() {
		return feesCode;
	}
	public void setFeesCode(String feesCode) {
		this.feesCode = feesCode;
	}
	public String getFeesName() {
		return feesName;
	}
	public void setFeesName(String feesName) {
		this.feesName = feesName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<SocialCategory> getSocialCategoryList() {
		return socialCategoryList;
	}
	public void setSocialCategoryList(List<SocialCategory> socialCategoryList) {
		this.socialCategoryList = socialCategoryList;
	}
	public String getLedger() {
		return ledger;
	}
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public double getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}
	
}