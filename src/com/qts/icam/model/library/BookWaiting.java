package com.qts.icam.model.library;

public class BookWaiting {
	private String bookWaitingDetailsObjectId;
	private String bookWaitingDetailsCode;
	private String bookCode;	
	private String updatedBy;
	private String status;
	private String userId;
	
	public String getBookWaitingDetailsObjectId() {
		return bookWaitingDetailsObjectId;
	}
	public void setBookWaitingDetailsObjectId(String bookWaitingDetailsObjectId) {
		this.bookWaitingDetailsObjectId = bookWaitingDetailsObjectId;
	}
	public String getBookWaitingDetailsCode() {
		return bookWaitingDetailsCode;
	}
	public void setBookWaitingDetailsCode(String bookWaitingDetailsCode) {
		this.bookWaitingDetailsCode = bookWaitingDetailsCode;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}	
}
