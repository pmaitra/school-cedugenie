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
public class BookRequest {

	private String bookRequestObjectId;
	private String bookRequestCode;
	private List<BookAllocation> bookAllocationList;
	private List<BookRequestDetails> bookRequestDetailsList;
	private String bookRequestOpenDate;
	private String bookRequestCloseDate;
	private Resource bookRequestFor;
	private Resource bookRequestBy;
	private String bookRequestStatus;
	private List<BookRequestActivityLog> bookRequestActivityLogList;
	private String updatedBy;
	private String userId;
	private int numberOfBookRequested;
	private String status;
	private String accessionNumber;
	

	public String getBookRequestObjectId() {
		return bookRequestObjectId;
	}

	public void setBookRequestObjectId(String bookRequestObjectId) {
		this.bookRequestObjectId = bookRequestObjectId;
	}

	public String getBookRequestCode() {
		return bookRequestCode;
	}

	public void setBookRequestCode(String bookRequestCode) {
		this.bookRequestCode = bookRequestCode;
	}

	public List<BookAllocation> getBookAllocationList() {
		return bookAllocationList;
	}

	public void setBookAllocationList(List<BookAllocation> bookAllocationList) {
		this.bookAllocationList = bookAllocationList;
	}

	public String getBookRequestOpenDate() {
		return bookRequestOpenDate;
	}

	public void setBookRequestOpenDate(String bookRequestOpenDate) {
		this.bookRequestOpenDate = bookRequestOpenDate;
	}

	public String getBookRequestCloseDate() {
		return bookRequestCloseDate;
	}

	public void setBookRequestCloseDate(String bookRequestCloseDate) {
		this.bookRequestCloseDate = bookRequestCloseDate;
	}

	public Resource getBookRequestFor() {
		return bookRequestFor;
	}

	public void setBookRequestFor(Resource bookRequestFor) {
		this.bookRequestFor = bookRequestFor;
	}

	public Resource getBookRequestBy() {
		return bookRequestBy;
	}

	public void setBookRequestBy(Resource bookRequestBy) {
		this.bookRequestBy = bookRequestBy;
	}

	public String getBookRequestStatus() {
		return bookRequestStatus;
	}

	public void setBookRequestStatus(String bookRequestStatus) {
		this.bookRequestStatus = bookRequestStatus;
	}

	public List<BookRequestActivityLog> getBookRequestActivityLogList() {
		return bookRequestActivityLogList;
	}

	public void setBookRequestActivityLogList(
			List<BookRequestActivityLog> bookRequestActivityLogList) {
		this.bookRequestActivityLogList = bookRequestActivityLogList;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<BookRequestDetails> getBookRequestDetailsList() {
		return bookRequestDetailsList;
	}

	public void setBookRequestDetailsList(
			List<BookRequestDetails> bookRequestDetailsList) {
		this.bookRequestDetailsList = bookRequestDetailsList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getNumberOfBookRequested() {
		return numberOfBookRequested;
	}

	public void setNumberOfBookRequested(int numberOfBookRequested) {
		this.numberOfBookRequested = numberOfBookRequested;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the accessionNumber
	 */
	public String getAccessionNumber() {
		return accessionNumber;
	}

	/**
	 * @param accessionNumber the accessionNumber to set
	 */
	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}
}
