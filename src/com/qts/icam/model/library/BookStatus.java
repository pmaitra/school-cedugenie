/**
 * 
 */
package com.qts.icam.model.library;

import java.util.List;

/**
 * @author saikat.das
 * 
 */
public class BookStatus {

	private List<BookLifeCycleStatus> bookLifeCycleStatusList;
	private List<BookOperationalStatus> bookOperationalStatusList;
	private String receivedDateDetailsId;
	private BookRequisitionDetails bookRequisitionDetails;

	public List<BookLifeCycleStatus> getBookLifeCycleStatusList() {
		return bookLifeCycleStatusList;
	}

	public void setBookLifeCycleStatusList(
			List<BookLifeCycleStatus> bookLifeCycleStatusList) {
		this.bookLifeCycleStatusList = bookLifeCycleStatusList;
	}

	public List<BookOperationalStatus> getBookOperationalStatusList() {
		return bookOperationalStatusList;
	}

	public void setBookOperationalStatusList(
			List<BookOperationalStatus> bookOperationalStatusList) {
		this.bookOperationalStatusList = bookOperationalStatusList;
	}

	public BookRequisitionDetails getBookRequisitionDetails() {
		return bookRequisitionDetails;
	}

	public void setBookRequisitionDetails(BookRequisitionDetails bookRequisitionDetails) {
		this.bookRequisitionDetails = bookRequisitionDetails;
	}

	public String getReceivedDateDetailsId() {
		return receivedDateDetailsId;
	}

	public void setReceivedDateDetailsId(String receivedDateDetailsId) {
		this.receivedDateDetailsId = receivedDateDetailsId;
	}
	
}
