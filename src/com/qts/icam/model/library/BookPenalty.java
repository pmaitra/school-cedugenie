/**
 * 
 */
package com.qts.icam.model.library;

/**
 * @author saikat.das
 * 
 */
public class BookPenalty {

	private String bookPenaltyObjectId;
	private int daysOfBookDefault;
	private int bookPenaltyToPay;
	private String updatedBy;

	public String getBookPenaltyObjectId() {
		return bookPenaltyObjectId;
	}

	public void setBookPenaltyObejctId(String bookPenaltyObjectId) {
		this.bookPenaltyObjectId = bookPenaltyObjectId;
	}

	public int getDaysOfBookDefault() {
		return daysOfBookDefault;
	}

	public void setDaysOfBookDefault(int daysOfBookDefault) {
		this.daysOfBookDefault = daysOfBookDefault;
	}

	public int getBookPenaltyToPay() {
		return bookPenaltyToPay;
	}

	public void setBookPenaltyToPay(int bookPenaltyToPay) {
		this.bookPenaltyToPay = bookPenaltyToPay;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
