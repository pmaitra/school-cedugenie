package com.qts.icam.model.library;


public class BookAllocationDetails {
	private String bookAllocationDetailsObjectId;
	private String bookAllocationDetailsCode;
	private String bookCode;
	private String bookName;
	private String bookId;
	private String bookIssueDate;
	private String bookReturnDate;
	private String actualReturnDate;
	private String updatedBy;
	private int noOfDaysExtend;
	private int penalty;
	private String status;
	private String bookIssuedTo;
	private String genreName;
	
	public String getBookAllocationDetailsObjectId() {
		return bookAllocationDetailsObjectId;
	}
	public void setBookAllocationDetailsObjectId(
			String bookAllocationDetailsObjectId) {
		this.bookAllocationDetailsObjectId = bookAllocationDetailsObjectId;
	}
	public String getBookAllocationDetailsCode() {
		return bookAllocationDetailsCode;
	}
	public void setBookAllocationDetailsCode(String bookAllocationDetailsCode) {
		this.bookAllocationDetailsCode = bookAllocationDetailsCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(String actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}	
	public int getNoOfDaysExtend() {
		return noOfDaysExtend;
	}
	public void setNoOfDaysExtend(int noOfDaysExtend) {
		this.noOfDaysExtend = noOfDaysExtend;
	}
	public int getPenalty() {
		return penalty;
	}
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}
	public String getBookIssuedTo() {
		return bookIssuedTo;
	}
	public void setBookIssuedTo(String bookIssuedTo) {
		this.bookIssuedTo = bookIssuedTo;
	}
	public String getBookReturnDate() {
		return bookReturnDate;
	}
	public void setBookReturnDate(String bookReturnDate) {
		this.bookReturnDate = bookReturnDate;
	}
	public String getBookIssueDate() {
		return bookIssueDate;
	}
	public void setBookIssueDate(String bookIssueDate) {
		this.bookIssueDate = bookIssueDate;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	} 
	

}
