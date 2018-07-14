/**
 * 
 */
package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.library.Book;
import com.qts.icam.model.library.BookRequisition;



/**
 * @author saikat.das
 * 
 */
public class Vendor {
	private String vendorObjectId;
	private String vendorCode;
	private String vendorName;
	private String vendorDesc;
	private String vendorContactPerson;
	private String vendorEmail;
	private String vendorContactNumber;
	private String vendorType;
	private String vendorAddress;
	private List<BookRequisition> bookRequisitionList;
	private String updatedBy;	
	private int vendorId;	
	private List<Item> vendorItems;
	private double vendorSellingRate;	
	private String vendorContactNo1;
	private String vendorContactNo2;
	private String address;
	private double quantityOrdered;
	private Item item;
	private Book book;
	private String bankName;
	private Long accountNo;
	private String bankCode;
	private String bankLocation;
	private String emailId;
	private String branchCode;
	private String branchName;
	private String bankIfscCode;
	private Long bankBranchCode;
	private double openingBal;
	private String vedorRecId;
	
	
	//Added by naimisha 03052018
	private String  financialYear;
	
	//Added by naimisha 07052018
	
	private String commodityName;
	
	

	public String getVendorObjectId() {
		return vendorObjectId;
	}

	public void setVendorObjectId(String vendorObjectId) {
		this.vendorObjectId = vendorObjectId;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorDesc() {
		return vendorDesc;
	}

	public void setVendorDesc(String vendorDesc) {
		this.vendorDesc = vendorDesc;
	}

	public String getVendorContactPerson() {
		return vendorContactPerson;
	}

	public void setVendorContactPerson(String vendorContactPerson) {
		this.vendorContactPerson = vendorContactPerson;
	}

	public String getVendorEmail() {
		return vendorEmail;
	}

	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}

	public String getVendorContactNumber() {
		return vendorContactNumber;
	}

	public void setVendorContactNumber(String vendorContactNumber) {
		this.vendorContactNumber = vendorContactNumber;
	}

	public String getVendorType() {
		return vendorType;
	}

	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public List<BookRequisition> getBookRequisitionList() {
		return bookRequisitionList;
	}

	public void setBookRequisitionList(List<BookRequisition> bookRequisitionList) {
		this.bookRequisitionList = bookRequisitionList;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	/*public List<Item> getVendorItems() {
		return vendorItems;
	}

	public void setVendorItems(List<Item> vendorItems) {
		this.vendorItems = vendorItems;
	}*/

	public double getVendorSellingRate() {
		return vendorSellingRate;
	}

	public void setVendorSellingRate(double vendorSellingRate) {
		this.vendorSellingRate = vendorSellingRate;
	}

	public String getVendorContactNo1() {
		return vendorContactNo1;
	}

	public void setVendorContactNo1(String vendorContactNo1) {
		this.vendorContactNo1 = vendorContactNo1;
	}

	public String getVendorContactNo2() {
		return vendorContactNo2;
	}

	public void setVendorContactNo2(String vendorContactNo2) {
		this.vendorContactNo2 = vendorContactNo2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(double quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public List<Item> getVendorItems() {
		return vendorItems;
	}

	public void setVendorItems(List<Item> vendorItems) {
		this.vendorItems = vendorItems;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankLocation() {
		return bankLocation;
	}

	public void setBankLocation(String bankLocation) {
		this.bankLocation = bankLocation;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankIfscCode() {
		return bankIfscCode;
	}

	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}

	public Long getBankBranchCode() {
		return bankBranchCode;
	}

	public void setBankBranchCode(Long bankBranchCode) {
		this.bankBranchCode = bankBranchCode;
	}

	public double getOpeningBal() {
		return openingBal;
	}

	public void setOpeningBal(double openingBal) {
		this.openingBal = openingBal;
	}

	public String getVedorRecId() {
		return vedorRecId;
	}

	public void setVedorRecId(String vedorRecId) {
		this.vedorRecId = vedorRecId;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	
	
	
}
