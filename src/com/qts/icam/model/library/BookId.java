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
public class BookId {

	private String bookIdObjectId;
	private String bookCode;
	private String bookId;
	private BookLocation bookLocation;
	private BookLocation bookParentLocation;
	private String bookLifeCycleCode;
	private String bookOperationalCode;
	private String newBookEntryDate;
	private String bookRetirementDate;
	private List<Resource> bookIssuedToList;
	private String updatedBy;
	private Double price;
	private String comment;

	public String getBookIdObjectId() {
		return bookIdObjectId;
	}

	public void setBookIdObjectId(String bookIdObjectId) {
		this.bookIdObjectId = bookIdObjectId;
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

	public BookLocation getBookLocation() {
		return bookLocation;
	}

	public void setBookLocation(BookLocation bookLocation) {
		this.bookLocation = bookLocation;
	}

	public BookLocation getBookParentLocation() {
		return bookParentLocation;
	}

	public void setBookParentLocation(BookLocation bookParentLocation) {
		this.bookParentLocation = bookParentLocation;
	}

	public String getBookLifeCycleCode() {
		return bookLifeCycleCode;
	}

	public void setBookLifeCycleCode(String bookLifeCycleCode) {
		this.bookLifeCycleCode = bookLifeCycleCode;
	}

	public String getBookOperationalCode() {
		return bookOperationalCode;
	}

	public void setBookOperationalCode(String bookOperationalCode) {
		this.bookOperationalCode = bookOperationalCode;
	}

	public String getNewBookEntryDate() {
		return newBookEntryDate;
	}

	public void setNewBookEntryDate(String newBookEntryDate) {
		this.newBookEntryDate = newBookEntryDate;
	}

	public String getBookRetirementDate() {
		return bookRetirementDate;
	}

	public void setBookRetirementDate(String bookRetirementDate) {
		this.bookRetirementDate = bookRetirementDate;
	}

	public List<Resource> getBookIssuedToList() {
		return bookIssuedToList;
	}

	public void setBookIssuedToList(List<Resource> bookIssuedToList) {
		this.bookIssuedToList = bookIssuedToList;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
