package com.qts.icam.model.event;

import java.util.List;

import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.UploadFile;

public class EventAchievement {
	
	private String serialId;
	private String objectId;
	private Integer schoolEvent;
	private String eventPosition;
	private String rollNumber;
	private String imageFilePath;
	private String updatedBy;
	private UploadFile uploadFile;
	private List<Attachment>attachmentList;
	
	public String getSerialId() {
		return serialId;
	}
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}
	public Integer getSchoolEvent() {
		return schoolEvent;
	}
	public void setSchoolEvent(Integer schoolEvent) {
		this.schoolEvent = schoolEvent;
	}
	public String getEventPosition() {
		return eventPosition;
	}
	public void setEventPosition(String eventPosition) {
		this.eventPosition = eventPosition;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
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
	
}
