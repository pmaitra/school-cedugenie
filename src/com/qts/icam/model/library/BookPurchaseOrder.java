package com.qts.icam.model.library;

import java.util.List;


import com.qts.icam.model.common.Vendor;

public class BookPurchaseOrder {
	
	private String bookRequisition;
	private String purchaseId;
	private String purchaseOrderObjectId;
	private String updatedBy;
	private String purchaseOrderCode;
	private String purchaseOrderDesc;
	private String purchaseOrderOpenDate;
	private String purchaseOrderCloseDate;
	private double totalQtyOrdered;
	private double totalQtyDeficit;;
	private double totalQtyReceived;
	private List<Vendor> vendor;
	private String status;
	private List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList;
	private double totalAmount;
	private double tax;
	private double advanceAmount;
	private double pendingAmount;
	private String amountStatus;
	private String purchaseOrderDamageCode;
	private String vendorCode;	
	private int purchaseSerialId;
	private String modeOfPayment;
	
	private double stdsInPercent;
	private double stdsInAmount;
	private double serviceCharge;
	private double serviceTaxInPercent;
	private double tdsInPercent;
	private double tdsInAmount;
	private double netAmount;
	private String chequeNo;
	private String bankCode;
	private String bankName;
	private String bankLocation;
	private String purchaseOrderName;
	private String ledger;
	
	
	public String getPurchaseOrderName() {
		return purchaseOrderName;
	}
	public void setPurchaseOrderName(String purchaseOrderName) {
		this.purchaseOrderName = purchaseOrderName;
	}
	public String getBookRequisition() {
		return bookRequisition;
	}
	public void setBookRequisition(String bookRequisition) {
		this.bookRequisition = bookRequisition;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}	
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}
	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}
	public String getPurchaseOrderDesc() {
		return purchaseOrderDesc;
	}
	public void setPurchaseOrderDesc(String purchaseOrderDesc) {
		this.purchaseOrderDesc = purchaseOrderDesc;
	}
	public String getPurchaseOrderOpenDate() {
		return purchaseOrderOpenDate;
	}
	public void setPurchaseOrderOpenDate(String purchaseOrderOpenDate) {
		this.purchaseOrderOpenDate = purchaseOrderOpenDate;
	}
	public String getPurchaseOrderCloseDate() {
		return purchaseOrderCloseDate;
	}
	public void setPurchaseOrderCloseDate(String purchaseOrderCloseDate) {
		this.purchaseOrderCloseDate = purchaseOrderCloseDate;
	}
	public double getTotalQtyOrdered() {
		return totalQtyOrdered;
	}
	public void setTotalQtyOrdered(double totalQtyOrdered) {
		this.totalQtyOrdered = totalQtyOrdered;
	}
	public double getTotalQtyDeficit() {
		return totalQtyDeficit;
	}
	public void setTotalQtyDeficit(double totalQtyDeficit) {
		this.totalQtyDeficit = totalQtyDeficit;
	}
	public double getTotalQtyReceived() {
		return totalQtyReceived;
	}
	public void setTotalQtyReceived(double totalQtyReceived) {
		this.totalQtyReceived = totalQtyReceived;
	}
	
	public List<Vendor> getVendor() {
		return vendor;
	}
	public void setVendor(List<Vendor> vendor) {
		this.vendor = vendor;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<BookPurchaseOrderDetails> getBookPurchaseOrderDetailsList() {
		return bookPurchaseOrderDetailsList;
	}
	public void setBookPurchaseOrderDetailsList(
			List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList) {
		this.bookPurchaseOrderDetailsList = bookPurchaseOrderDetailsList;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	public double getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public String getAmountStatus() {
		return amountStatus;
	}
	public void setAmountStatus(String amountStatus) {
		this.amountStatus = amountStatus;
	}
	public String getPurchaseOrderDamageCode() {
		return purchaseOrderDamageCode;
	}
	public void setPurchaseOrderDamageCode(String purchaseOrderDamageCode) {
		this.purchaseOrderDamageCode = purchaseOrderDamageCode;
	}
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPurchaseOrderObjectId() {
		return purchaseOrderObjectId;
	}
	public void setPurchaseOrderObjectId(String purchaseOrderObjectId) {
		this.purchaseOrderObjectId = purchaseOrderObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getPurchaseSerialId() {
		return purchaseSerialId;
	}
	public void setPurchaseSerialId(int purchaseSerialId) {
		this.purchaseSerialId = purchaseSerialId;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public double getStdsInPercent() {
		return stdsInPercent;
	}
	public void setStdsInPercent(double stdsInPercent) {
		this.stdsInPercent = stdsInPercent;
	}
	public double getStdsInAmount() {
		return stdsInAmount;
	}
	public void setStdsInAmount(double stdsInAmount) {
		this.stdsInAmount = stdsInAmount;
	}
	public double getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public double getServiceTaxInPercent() {
		return serviceTaxInPercent;
	}
	public void setServiceTaxInPercent(double serviceTaxInPercent) {
		this.serviceTaxInPercent = serviceTaxInPercent;
	}
	public double getTdsInPercent() {
		return tdsInPercent;
	}
	public void setTdsInPercent(double tdsInPercent) {
		this.tdsInPercent = tdsInPercent;
	}
	public double getTdsInAmount() {
		return tdsInAmount;
	}
	public void setTdsInAmount(double tdsInAmount) {
		this.tdsInAmount = tdsInAmount;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankLocation() {
		return bankLocation;
	}
	public void setBankLocation(String bankLocation) {
		this.bankLocation = bankLocation;
	}
	public String getLedger() {
		return ledger;
	}
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}
	
	
	
	
}

