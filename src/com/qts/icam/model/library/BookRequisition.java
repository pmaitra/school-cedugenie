/**
 * 
 */
package com.qts.icam.model.library;

import java.util.List;

import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Vendor;

/**
 * @author saikat.das
 * 
 */
public class BookRequisition {

	private String bookRequisitionObjectId;
	private String bookRequisitionCode;
	private List<BookRequisitionDetails> bookRequisitionDetailsList;
	private int totalNumberOfBooksRequisitioned;
	private int totalNumberOfBooksReceived;
	private int totalNumberOfBooksDeficit;
	private String bookRequisitionOpenDate;
	private String bookRequisitionCloseDate;
	private Resource bookRequisitionFor;
	private Resource bookRequisitionBy;
	private String bookRequisitionStatus;
	private String vendorName;
	private List<Vendor> vendorList;
	private Vendor vendor;
	private String updatedBy;
	private double totalPrice;
	private String status;
	private String paymentMode;
	//----------------UPDATE---
	private int intBookRequisitionOpenDate;
	private int intBookRequisitionCloseDate;
	//-------------------------
	private boolean purchaseOrderCreated;
	
	
	

	
	
	
	
	
	public boolean isPurchaseOrderCreated() {
		return purchaseOrderCreated;
	}

	public void setPurchaseOrderCreated(boolean purchaseOrderCreated) {
		this.purchaseOrderCreated = purchaseOrderCreated;
	}

	public String getBookRequisitionObjectId() {
		return bookRequisitionObjectId;
	}

	public void setBookRequisitionObjectId(String bookRequisitionObjectId) {
		this.bookRequisitionObjectId = bookRequisitionObjectId;
	}

	public String getBookRequisitionCode() {
		return bookRequisitionCode;
	}

	public void setBookRequisitionCode(String bookRequisitionCode) {
		this.bookRequisitionCode = bookRequisitionCode;
	}

	public List<BookRequisitionDetails> getBookRequisitionDetailsList() {
		return bookRequisitionDetailsList;
	}

	public void setBookRequisitionDetailsList(
			List<BookRequisitionDetails> bookRequisitionDetailsList) {
		this.bookRequisitionDetailsList = bookRequisitionDetailsList;
	}

	public int getTotalNumberOfBooksRequisitioned() {
		return totalNumberOfBooksRequisitioned;
	}

	public void setTotalNumberOfBooksRequisitioned(
			int totalNumberOfBooksRequisitioned) {
		this.totalNumberOfBooksRequisitioned = totalNumberOfBooksRequisitioned;
	}

	public int getTotalNumberOfBooksReceived() {
		return totalNumberOfBooksReceived;
	}

	public void setTotalNumberOfBooksReceived(int totalNumberOfBooksReceived) {
		this.totalNumberOfBooksReceived = totalNumberOfBooksReceived;
	}

	public int getTotalNumberOfBooksDeficit() {
		return totalNumberOfBooksDeficit;
	}

	public void setTotalNumberOfBooksDeficit(int totalNumberOfBooksDeficit) {
		this.totalNumberOfBooksDeficit = totalNumberOfBooksDeficit;
	}

	public String getBookRequisitionOpenDate() {
		return bookRequisitionOpenDate;
	}

	public void setBookRequisitionOpenDate(String bookRequisitionOpenDate) {
		this.bookRequisitionOpenDate = bookRequisitionOpenDate;
	}

	public String getBookRequisitionCloseDate() {
		return bookRequisitionCloseDate;
	}

	public void setBookRequisitionCloseDate(String bookRequisitionCloseDate) {
		this.bookRequisitionCloseDate = bookRequisitionCloseDate;
	}

	public Resource getBookRequisitionFor() {
		return bookRequisitionFor;
	}

	public void setBookRequisitionFor(Resource bookRequisitionFor) {
		this.bookRequisitionFor = bookRequisitionFor;
	}

	public Resource getBookRequisitionBy() {
		return bookRequisitionBy;
	}

	public void setBookRequisitionBy(Resource bookRequisitionBy) {
		this.bookRequisitionBy = bookRequisitionBy;
	}

	public String getBookRequisitionStatus() {
		return bookRequisitionStatus;
	}

	public void setBookRequisitionStatus(String bookRequisitionStatus) {
		this.bookRequisitionStatus = bookRequisitionStatus;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getIntBookRequisitionOpenDate() {
		return intBookRequisitionOpenDate;
	}

	public void setIntBookRequisitionOpenDate(int intBookRequisitionOpenDate) {
		this.intBookRequisitionOpenDate = intBookRequisitionOpenDate;
	}

	public int getIntBookRequisitionCloseDate() {
		return intBookRequisitionCloseDate;
	}

	public void setIntBookRequisitionCloseDate(int intBookRequisitionCloseDate) {
		this.intBookRequisitionCloseDate = intBookRequisitionCloseDate;
	}

	public List<Vendor> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	
	

}
