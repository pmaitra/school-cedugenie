package com.qts.icam.model.library;

import com.qts.icam.model.common.Item;

public class BookPurchaseOrderDetails {

	private int purchaseOrderDetailsId; 
	private String purchaseOrderDetailsObjectId;
	private String updatedBy;
	private Item item;
	private String purchaseOrderDetailsCode;
	private String PurchaseOrderDetailsDesc;
	private double qtyOrdered;
	private double qtyDeficit;;
	private double qtyDefect;
	private double qtyReceived;
	private double qtyReceiving;
	private double amount;
	private String status;
	private double rate;
	private double discount;
	private double tax;
	private String itemCode;
	private BookPurchaseOrder bookPurchaseOrder;
	private String bookName;
	private String authorName;	
	private String edition;	
	private String publisherName;	
	private String vendor;
	private String bookPurchaseOrderCode;
	//Added By Naimisha 30062017
	
	private double paidAmount;
	//added by anup.roy 06112017
	private String genre;
	
	public String getBookPurchaseOrderCode() {
		return bookPurchaseOrderCode;
	}
	public void setBookPurchaseOrderCode(String bookPurchaseOrderCode) {
		this.bookPurchaseOrderCode = bookPurchaseOrderCode;
	}
	
	
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getPurchaseOrderDetailsCode() {
		return purchaseOrderDetailsCode;
	}
	public void setPurchaseOrderDetailsCode(String purchaseOrderDetailsCode) {
		this.purchaseOrderDetailsCode = purchaseOrderDetailsCode;
	}
	public String getPurchaseOrderDetailsDesc() {
		return PurchaseOrderDetailsDesc;
	}
	public void setPurchaseOrderDetailsDesc(String purchaseOrderDetailsDesc) {
		PurchaseOrderDetailsDesc = purchaseOrderDetailsDesc;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getPurchaseOrderDetailsId() {
		return purchaseOrderDetailsId;
	}
	public void setPurchaseOrderDetailsId(int purchaseOrderDetailsId) {
		this.purchaseOrderDetailsId = purchaseOrderDetailsId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public double getQtyOrdered() {
		return qtyOrdered;
	}
	public void setQtyOrdered(double qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}
	public double getQtyDeficit() {
		return qtyDeficit;
	}
	public void setQtyDeficit(double qtyDeficit) {
		this.qtyDeficit = qtyDeficit;
	}
	public double getQtyReceived() {
		return qtyReceived;
	}
	public void setQtyReceived(double qtyReceived) {
		this.qtyReceived = qtyReceived;
	}
	public double getQtyReceiving() {
		return qtyReceiving;
	}
	public void setQtyReceiving(double qtyReceiving) {
		this.qtyReceiving = qtyReceiving;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getQtyDefect() {
		return qtyDefect;
	}
	public void setQtyDefect(double qtyDefect) {
		this.qtyDefect = qtyDefect;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getPurchaseOrderDetailsObjectId() {
		return purchaseOrderDetailsObjectId;
	}
	public void setPurchaseOrderDetailsObjectId(
			String purchaseOrderDetailsObjectId) {
		this.purchaseOrderDetailsObjectId = purchaseOrderDetailsObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public BookPurchaseOrder getBookPurchaseOrder() {
		return bookPurchaseOrder;
	}
	public void setBookPurchaseOrder(BookPurchaseOrder bookPurchaseOrder) {
		this.bookPurchaseOrder = bookPurchaseOrder;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
}

