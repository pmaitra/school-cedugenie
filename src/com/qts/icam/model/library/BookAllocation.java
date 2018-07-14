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
public class BookAllocation {

	private String bookAllocationObjectId;
	private String bookAllocationCode;
	private Book book;
	private boolean bookIssued;
	private Resource bookIssuedBy;
	private Resource bookIssuedTo;
	private String bookIssueDate;
	private String bookReturnDate;
	private String bookReturnExtendedDate;
	private boolean bookDefaulted;
	private BookPriority bookPriority;
	private BookRating bookRating;
	private BookPenalty bookPenalty;
	private String bookIssueComment;
	private String updatedBy;
	private List<BookAllocationDetails> bookAllocationDetails;
	// below added fields are used for mybatis mapping in librarymapper.xml file
		private String bookRequestedId;

	public String getBookAllocationObjectId() {
		return bookAllocationObjectId;
	}

	public void setBookAllocationObjectId(String bookAllocationyObjectId) {
		this.bookAllocationObjectId = bookAllocationyObjectId;
	}
	
	public String getBookAllocationCode() {
		return bookAllocationCode;
	}

	public void setBookAllocationCode(String bookAllocationCode) {
		this.bookAllocationCode = bookAllocationCode;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public boolean isBookIssued() {
		return bookIssued;
	}

	public void setBookIssued(boolean bookIssued) {
		this.bookIssued = bookIssued;
	}

	public Resource getBookIssuedBy() {
		return bookIssuedBy;
	}

	public void setBookIssuedBy(Resource bookIssuedBy) {
		this.bookIssuedBy = bookIssuedBy;
	}

	public Resource getBookIssuedTo() {
		return bookIssuedTo;
	}
	public List<BookAllocationDetails> getBookAllocationDetails() {
		return bookAllocationDetails;
	}

	public void setBookAllocationDetails(
			List<BookAllocationDetails> bookAllocationDetails) {
		this.bookAllocationDetails = bookAllocationDetails;
	}

	public void setBookIssuedTo(Resource bookIssuedTo) {
		this.bookIssuedTo = bookIssuedTo;
	}

	public String getBookIssueDate() {
		return bookIssueDate;
	}

	public void setBookIssueDate(String bookIssueDate) {
		this.bookIssueDate = bookIssueDate;
	}

	public String getBookReturnDate() {
		return bookReturnDate;
	}

	public void setBookReturnDate(String bookReturnDate) {
		this.bookReturnDate = bookReturnDate;
	}

	public String getBookReturnExtendedDate() {
		return bookReturnExtendedDate;
	}

	public void setBookReturnExtendedDate(String bookReturnExtendedDate) {
		this.bookReturnExtendedDate = bookReturnExtendedDate;
	}

	public boolean isBookDefaulted() {
		return bookDefaulted;
	}

	public void setBookDefaulted(boolean bookDefaulted) {
		this.bookDefaulted = bookDefaulted;
	}

	public BookPriority getBookPriority() {
		return bookPriority;
	}

	public void setBookPriority(BookPriority bookPriority) {
		this.bookPriority = bookPriority;
	}

	public BookRating getBookRating() {
		return bookRating;
	}

	public void setBookRating(BookRating bookRating) {
		this.bookRating = bookRating;
	}

	public BookPenalty getBookPenalty() {
		return bookPenalty;
	}

	public void setBookPenalty(BookPenalty bookPenalty) {
		this.bookPenalty = bookPenalty;
	}

	public String getBookIssueComment() {
		return bookIssueComment;
	}

	public void setBookIssueComment(String bookIssueComment) {
		this.bookIssueComment = bookIssueComment;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getBookRequestedId() {
		return bookRequestedId;
	}

	public void setBookRequestedId(String bookRequestedId) {
		this.bookRequestedId = bookRequestedId;
	}

}
