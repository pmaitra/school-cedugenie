package com.qts.icam.model.library;

import java.util.List;

public class BookRequestDetails {

	private String bookRequestDetailsObjectId;
	private String bookRequestDetailsCode;
	private String bookCode;
	private String bookName;
	private String updatedBy;
	private String status;
	private List<BookId> bookIdList;
	private List<BookWaiting> bookWaitingList;
	private String bookId;
	private Book book;
	private String expectedBookReturnDate;
	//----------------setter&getter----------------
	public String getBookRequestDetailsObjectId() {
		return bookRequestDetailsObjectId;
	}
	public void setBookRequestDetailsObjectId(String bookRequestDetailsObjectId) {
		this.bookRequestDetailsObjectId = bookRequestDetailsObjectId;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBookRequestDetailsCode() {
		return bookRequestDetailsCode;
	}
	public void setBookRequestDetailsCode(String bookRequestDetailsCode) {
		this.bookRequestDetailsCode = bookRequestDetailsCode;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public List<BookId> getBookIdList() {
		return bookIdList;
	}
	public void setBookIdList(List<BookId> bookIdList) {
		this.bookIdList = bookIdList;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public List<BookWaiting> getBookWaitingList() {
		return bookWaitingList;
	}
	public void setBookWaitingList(List<BookWaiting> bookWaitingList) {
		this.bookWaitingList = bookWaitingList;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getExpectedBookReturnDate() {
		return expectedBookReturnDate;
	}
	public void setExpectedBookReturnDate(String expectedBookReturnDate) {
		this.expectedBookReturnDate = expectedBookReturnDate;
	}
	
	
}
