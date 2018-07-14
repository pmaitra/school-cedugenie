/**
 * 
 */
package com.qts.icam.model.library;

/**
 * @author saikat.das
 * 
 */
public class BookOperationalStatus {

	private String bookOperationalStatusObjectId;
	private String bookOperationalStatusCode;
	private String bookOperationalStatusDesc;
	private String updatedBy;

	public String getBookOperationalStatusObjectId() {
		return bookOperationalStatusObjectId;
	}

	public void setBookOperationalStatusObjectId(
			String bookOperationalStatusObjectId) {
		this.bookOperationalStatusObjectId = bookOperationalStatusObjectId;
	}

	public String getBookOperationalStatusCode() {
		return bookOperationalStatusCode;
	}

	public void setBookOperationalStatusCode(String bookOperationalStatusCode) {
		this.bookOperationalStatusCode = bookOperationalStatusCode;
	}

	public String getBookOperationalStatusDesc() {
		return bookOperationalStatusDesc;
	}

	public void setBookOperationalStatusDesc(String bookOperationalStatusDesc) {
		this.bookOperationalStatusDesc = bookOperationalStatusDesc;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
