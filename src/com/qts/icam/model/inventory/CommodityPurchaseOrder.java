package com.qts.icam.model.inventory;

import java.util.List;

public class CommodityPurchaseOrder {
	
	private String purchaseId;
	private String objId;
	private String updatedBy;
	
	private String purchaseOrderCode;
	private String purchaseOrderDesc;
	
	private String purchaseOrderOpenDate;
	private String purchaseOrderCloseDate;
	
	private double totalQtyOrdered;
	private double totalQtyDeficit;
	private double totalQtyReceived;
	private double totalCommodityAmount;;
	private double totalServiceAmount;
	
	private String vendorCode;
	private String vendorName;
	
	private double netTotal;
	private double advanceAmount;
	private double pendingAmount;
	private double payAmount;
	private double tdsAmount;
	private double stdsAmount;
	
	private String amountStatus;
	private String receiveStatus;
	private String orderStatus;
	
	List<CommodityPurchaseOrderDetails> commodityPurchaseOrderDetailsList;
	List<CommodityPurchaseOrderDetails> serviceDetailsList;
	
	private String transactionMode ;
	private String chequeNo ;
	private String bankName ;
	private String bankCode;
	private String bankLocation ;
	private String departmentCode;
	private String departmentName ;
	private String ledger;
	private String type;
	
	private String bankIfscCode;
	private String bankAccountNo;
	
	private String taxName;
	private double taxPercentage;
	
	//Added By Naimisha 12032018
	private String approvalStatus; 
	
	//Added By Naimisha 29032018
	private String commodityName;
	private double qtyOrdered;
	private double rate;
	private double amount;
	
	
	//Added By Naimisha 30042018
	
	private String ticket;
	private String taskDetailsCode;
	
	
	
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
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
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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
	public double getTotalCommodityAmount() {
		return totalCommodityAmount;
	}
	public void setTotalCommodityAmount(double totalCommodityAmount) {
		this.totalCommodityAmount = totalCommodityAmount;
	}
	public double getTotalServiceAmount() {
		return totalServiceAmount;
	}
	public void setTotalServiceAmount(double totalServiceAmount) {
		this.totalServiceAmount = totalServiceAmount;
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
	public double getNetTotal() {
		return netTotal;
	}
	public void setNetTotal(double netTotal) {
		this.netTotal = netTotal;
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
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public double getTdsAmount() {
		return tdsAmount;
	}
	public void setTdsAmount(double tdsAmount) {
		this.tdsAmount = tdsAmount;
	}
	public double getStdsAmount() {
		return stdsAmount;
	}
	public void setStdsAmount(double stdsAmount) {
		this.stdsAmount = stdsAmount;
	}
	public String getAmountStatus() {
		return amountStatus;
	}
	public void setAmountStatus(String amountStatus) {
		this.amountStatus = amountStatus;
	}
	public String getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public List<CommodityPurchaseOrderDetails> getCommodityPurchaseOrderDetailsList() {
		return commodityPurchaseOrderDetailsList;
	}
	public void setCommodityPurchaseOrderDetailsList(
			List<CommodityPurchaseOrderDetails> commodityPurchaseOrderDetailsList) {
		this.commodityPurchaseOrderDetailsList = commodityPurchaseOrderDetailsList;
	}
	public List<CommodityPurchaseOrderDetails> getServiceDetailsList() {
		return serviceDetailsList;
	}
	public void setServiceDetailsList(
			List<CommodityPurchaseOrderDetails> serviceDetailsList) {
		this.serviceDetailsList = serviceDetailsList;
	}	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getLedger() {
		return ledger;
	}
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBankIfscCode() {
		return bankIfscCode;
	}
	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public double getQtyOrdered() {
		return qtyOrdered;
	}
	public void setQtyOrdered(double qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getTaskDetailsCode() {
		return taskDetailsCode;
	}
	public void setTaskDetailsCode(String taskDetailsCode) {
		this.taskDetailsCode = taskDetailsCode;
	}
}
