/**
 * 
 */
package com.qts.icam.model.library;

/**
 * @author saikat.das
 * 
 */
public class BookRequestActivityLog {

	private String bookRequestActivityLogObjectId;
	private String bookRequestActivityLogCode;
	private String bookRequestActivityLogDesc;
	private String updatedBy;

	public String getBookRequestActivityLogObjectId() {
		return bookRequestActivityLogObjectId;
	}

	public void setBookRequestActivityLogObjectId(
			String bookRequestActivityLogObjectId) {
		this.bookRequestActivityLogObjectId = bookRequestActivityLogObjectId;
	}

	public String getBookRequestActivityLogCode() {
		return bookRequestActivityLogCode;
	}

	public void setBookRequestActivityLogCode(String bookRequestActivityLogCode) {
		this.bookRequestActivityLogCode = bookRequestActivityLogCode;
	}

	public String getBookRequestActivityLogDesc() {
		return bookRequestActivityLogDesc;
	}

	public void setBookRequestActivityLogDesc(String bookRequestActivityLogDesc) {
		this.bookRequestActivityLogDesc = bookRequestActivityLogDesc;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
