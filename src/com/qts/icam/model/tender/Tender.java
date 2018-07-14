package com.qts.icam.model.tender;

import java.util.List;

import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.inventory.Commodity;

public class Tender {

	private String tenderReferenceNumber;
	private String tenderType;
	private String tenderCategory;
	private String tenderSubCategory;
	private String tenderOpenDate;
	private String tenderCloseDate;
	private String tenderSPOC;
	private String tenderDept;
	private String updatedBy;
	private String objectId;
	private List<Commodity> commodityList;
	private UploadFile uploadFile;
	private List<Attachment>attachmentList;
	private String department;
	
	
	public String getTenderReferenceNumber() {
		return tenderReferenceNumber;
	}
	public void setTenderReferenceNumber(String tenderReferenceNumber) {
		this.tenderReferenceNumber = tenderReferenceNumber;
	}
	public String getTenderType() {
		return tenderType;
	}
	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}
	public String getTenderCategory() {
		return tenderCategory;
	}
	public void setTenderCategory(String tenderCategory) {
		this.tenderCategory = tenderCategory;
	}
	public String getTenderSubCategory() {
		return tenderSubCategory;
	}
	public void setTenderSubCategory(String tenderSubCategory) {
		this.tenderSubCategory = tenderSubCategory;
	}
	public String getTenderOpenDate() {
		return tenderOpenDate;
	}
	public void setTenderOpenDate(String tenderOpenDate) {
		this.tenderOpenDate = tenderOpenDate;
	}
	public String getTenderCloseDate() {
		return tenderCloseDate;
	}
	public void setTenderCloseDate(String tenderCloseDate) {
		this.tenderCloseDate = tenderCloseDate;
	}
	public String getTenderSPOC() {
		return tenderSPOC;
	}
	public void setTenderSPOC(String tenderSPOC) {
		this.tenderSPOC = tenderSPOC;
	}
	public String getTenderDept() {
		return tenderDept;
	}
	public void setTenderDept(String tenderDept) {
		this.tenderDept = tenderDept;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public List<Commodity> getCommodityList() {
		return commodityList;
	}
	public void setCommodityList(List<Commodity> commodityList) {
		this.commodityList = commodityList;
	}
	public UploadFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
}
