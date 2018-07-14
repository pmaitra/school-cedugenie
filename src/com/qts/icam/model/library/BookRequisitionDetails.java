package com.qts.icam.model.library;

import java.util.List;
import java.util.UUID;

/**
 * @author saikat.das
 * 
 */
public class BookRequisitionDetails {
	private UUID recordId;
	private String bookRequisitionDetailsObjectId;
	private String bookRequisitionDetailsCode;
	private String bookRequisitionCode;
	private String bookName;
	private String bookAuthor;
	private String bookEdition;
	private String bookPublisher;
	private int numberOfBooksRequisitioned;
	private int numberOfBooksReceived;
	private int numberOfBooksDeficit;
	private double rate;
	private double totalPrice;
	private String updatedBy;
	private boolean addedToStock;
	private List<BookRequisition> bookRequisitionList;
	private String genre;
	private String departmentName;
	
	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookEdition() {
		return bookEdition;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}	
	
	public UUID getRecordId() {
		return recordId;
	}

	public void setRecordId(UUID recordId) {
		this.recordId = recordId;
	}

	private List<BookRequisitionReceivedDates> bookRequisitionReceivedDatesList;

	public String getBookRequisitionDetailsObjectId() {
		return bookRequisitionDetailsObjectId;
	}

	public void setBookRequisitionDetailsObjectId(
			String bookRequisitionDetailsObjectId) {
		this.bookRequisitionDetailsObjectId = bookRequisitionDetailsObjectId;
	}

	public String getBookRequisitionCode() {
		return bookRequisitionCode;
	}

	public void setBookRequisitionCode(String bookRequisitionCode) {
		this.bookRequisitionCode = bookRequisitionCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getNumberOfBooksRequisitioned() {
		return numberOfBooksRequisitioned;
	}

	public void setNumberOfBooksRequisitioned(int numberOfBooksRequisitioned) {
		this.numberOfBooksRequisitioned = numberOfBooksRequisitioned;
	}

	public int getNumberOfBooksReceived() {
		return numberOfBooksReceived;
	}

	public void setNumberOfBooksReceived(int numberOfBooksReceived) {
		this.numberOfBooksReceived = numberOfBooksReceived;
	}

	public int getNumberOfBooksDeficit() {
		return numberOfBooksDeficit;
	}

	public void setNumberOfBooksDeficit(int numberOfBooksDeficit) {
		this.numberOfBooksDeficit = numberOfBooksDeficit;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public List<BookRequisitionReceivedDates> getBookRequisitionReceivedDatesList() {
		return bookRequisitionReceivedDatesList;
	}

	public void setBookRequisitionReceivedDatesList(
			List<BookRequisitionReceivedDates> bookRequisitionReceivedDatesList) {
		this.bookRequisitionReceivedDatesList = bookRequisitionReceivedDatesList;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getBookRequisitionDetailsCode() {
		return bookRequisitionDetailsCode;
	}

	public void setBookRequisitionDetailsCode(String bookRequisitionDetailsCode) {
		this.bookRequisitionDetailsCode = bookRequisitionDetailsCode;
	}

	public boolean isAddedToStock() {
		return addedToStock;
	}

	public void setAddedToStock(boolean addedToStock) {
		this.addedToStock = addedToStock;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<BookRequisition> getBookRequisitionList() {
		return bookRequisitionList;
	}

	public void setBookRequisitionList(List<BookRequisition> bookRequisitionList) {
		this.bookRequisitionList = bookRequisitionList;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
