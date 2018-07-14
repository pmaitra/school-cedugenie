/**
 * 
 */
package com.qts.icam.model.library;

/**
 * @author saikat.das
 * 
 */
public class BookLocation {

	private String bookLocationObjectId;
	private String bookLocationCode;
	private String bookLocationName;
	private String bookLocationDesc;
	private String bookLocationParentCode;
	private String updatedBy;

	public String getBookLocationObjectId() {
		return bookLocationObjectId;
	}

	public void setBookLocationObjectId(String bookLocationObjectId) {
		this.bookLocationObjectId = bookLocationObjectId;
	}

	public String getBookLocationCode() {
		return bookLocationCode;
	}

	public void setBookLocationCode(String bookLocationCode) {
		this.bookLocationCode = bookLocationCode;
	}

	public String getBookLocationName() {
		return bookLocationName;
	}

	public void setBookLocationName(String bookLocationName) {
		this.bookLocationName = bookLocationName;
	}

	public String getBookLocationDesc() {
		return bookLocationDesc;
	}

	public void setBookLocationDesc(String bookLocationDesc) {
		this.bookLocationDesc = bookLocationDesc;
	}

	public String getBookLocationParentCode() {
		return bookLocationParentCode;
	}

	public void setBookLocationParentCode(String bookLocationParentCode) {
		this.bookLocationParentCode = bookLocationParentCode;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
