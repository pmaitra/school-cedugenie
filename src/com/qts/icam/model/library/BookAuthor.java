/**
 * 
 */
package com.qts.icam.model.library;

/**
 * @author saikat.das
 * 
 */
public class BookAuthor {
	private String updatedBy;
	private String bookAuthorObjectId;
	private String bookObjectId;
	private String authorObjectId;

	
	public String getBookAuthorObjectId() {
		return bookAuthorObjectId;
	}

	public void setBookAuthorObjectId(String bookAuthorObjectId) {
		this.bookAuthorObjectId = bookAuthorObjectId;
	}

	public String getBookObjectId() {
		return bookObjectId;
	}

	public void setBookObjectId(String bookObjectId) {
		this.bookObjectId = bookObjectId;
	}

	public String getAuthorObjectId() {
		return authorObjectId;
	}

	public void setAuthorObjectId(String authorObjectId) {
		this.authorObjectId = authorObjectId;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
