/**
 * 
 */
package com.qts.icam.model.library;

import java.util.List;

import com.qts.icam.model.common.Resource;

/**
 * @author saikat.das
 * 
 */
public class BookRating {

	private String bookRatingObjectId;
	private String bookRatingCode;
	private String bookRatingDesc;
	private String bookRatingComments;
	private String userId;
	private Resource bookRatingBy;
	private List<Book> bookList;
	private String updatedBy;

	public String getBookRatingObjectId() {
		return bookRatingObjectId;
	}

	public void setBookRatingObjectId(String bookRatingObjectId) {
		this.bookRatingObjectId = bookRatingObjectId;
	}

	public String getBookRatingCode() {
		return bookRatingCode;
	}

	public void setBookRatingCode(String bookRatingCode) {
		this.bookRatingCode = bookRatingCode;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBookRatingDesc() {
		return bookRatingDesc;
	}

	public void setBookRatingDesc(String bookRatingDesc) {
		this.bookRatingDesc = bookRatingDesc;
	}

	public String getBookRatingComments() {
		return bookRatingComments;
	}

	public void setBookRatingComments(String bookRatingComments) {
		this.bookRatingComments = bookRatingComments;
	}

	public Resource getBookRatingBy() {
		return bookRatingBy;
	}

	public void setBookRatingBy(Resource bookRatingBy) {
		this.bookRatingBy = bookRatingBy;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
