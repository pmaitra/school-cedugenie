/**
 * 
 */
package com.qts.icam.model.library;

/**
 * @author saikat.das
 * 
 */
public class BookCategory {

	private String bookCategoryObjectId;
	private String bookCategoryCode;
	private String bookCategoryName;
	private String bookCategoryDesc;
	private String bookCategoryParentCode;
	private String updatedBy;

	public String getBookCategoryObjectId() {
		return bookCategoryObjectId;
	}

	public void setBookCategoryObjectId(String bookCategoryObjectId) {
		this.bookCategoryObjectId = bookCategoryObjectId;
	}

	public String getBookCategoryCode() {
		return bookCategoryCode;
	}

	public void setBookCategoryCode(String bookCategoryCode) {
		this.bookCategoryCode = bookCategoryCode;
	}

	public String getBookCategoryName() {
		return bookCategoryName;
	}

	public void setBookCategoryName(String bookCategoryName) {
		this.bookCategoryName = bookCategoryName;
	}

	public String getBookCategoryDesc() {
		return bookCategoryDesc;
	}

	public void setBookCategoryDesc(String bookCategoryDesc) {
		this.bookCategoryDesc = bookCategoryDesc;
	}

	public String getBookCategoryParentCode() {
		return bookCategoryParentCode;
	}

	public void setBookCategoryParentCode(String bookCategoryParentCode) {
		this.bookCategoryParentCode = bookCategoryParentCode;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
