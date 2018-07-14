/**
 * 
 */
package com.qts.icam.model.library;

/**
 * @author saikat.das
 * 
 */
public class BookLifeCycleStatus {

	private String bookLifeCycleStatusObjectId;
	private String bookLifeCycleStatusCode;
	private String bookLifeCycleStatusDesc;
	private String updatedBy;

	public String getBookLifeCycleStatusObjectId() {
		return bookLifeCycleStatusObjectId;
	}

	public void setBookLifeCycleStatusObjectId(
			String bookLifeCycleStatusObjectId) {
		this.bookLifeCycleStatusObjectId = bookLifeCycleStatusObjectId;
	}

	public String getBookLifeCycleStatusCode() {
		return bookLifeCycleStatusCode;
	}

	public void setBookLifeCycleStatusCode(String bookLifeCycleStatusCode) {
		this.bookLifeCycleStatusCode = bookLifeCycleStatusCode;
	}

	public String getBookLifeCycleStatusDesc() {
		return bookLifeCycleStatusDesc;
	}

	public void setBookLifeCycleStatusDesc(String bookLifeCycleStatusDesc) {
		this.bookLifeCycleStatusDesc = bookLifeCycleStatusDesc;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
