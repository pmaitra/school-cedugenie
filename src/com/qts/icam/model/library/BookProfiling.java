/**
 * 
 */
package com.qts.icam.model.library;

/**
 * @author saikat.das
 * 
 */
public class BookProfiling {

	private String bookProfilingObjectId;
	private BookCategory bookCategory;
	private BookClassification bookClassification;
	private BookAllocation bookAllocation;
	private String updatedBy;

	public String getBookProfilingObjectId() {
		return bookProfilingObjectId;
	}

	public void setBookProfilingObjectId(String bookProfilingObjectId) {
		this.bookProfilingObjectId = bookProfilingObjectId;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public BookClassification getBookClassification() {
		return bookClassification;
	}

	public void setBookClassification(BookClassification bookClassification) {
		this.bookClassification = bookClassification;
	}

	public BookAllocation getBookAllocation() {
		return bookAllocation;
	}

	public void setBookAllocation(BookAllocation bookAllocation) {
		this.bookAllocation = bookAllocation;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
