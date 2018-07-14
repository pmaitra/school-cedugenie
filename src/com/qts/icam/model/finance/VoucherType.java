package com.qts.icam.model.finance;

public class VoucherType {
	
	private int voucherTypeSerialId;
	private String objectId;
	private String updatedBy;
	
	private String voucherTypeCode;
	private String voucherTypeName;
	
	/* added by sourav.bhadra on 20-04-2018 */
	private boolean department;
	private boolean incExp;
	private boolean multipleDebitLedger;
	private boolean ticketNo;
	
	
	public int getVoucherTypeSerialId() {
		return voucherTypeSerialId;
	}
	public void setVoucherTypeSerialId(int voucherTypeSerialId) {
		this.voucherTypeSerialId = voucherTypeSerialId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getVoucherTypeCode() {
		return voucherTypeCode;
	}
	public void setVoucherTypeCode(String voucherTypeCode) {
		this.voucherTypeCode = voucherTypeCode;
	}
	public String getVoucherTypeName() {
		return voucherTypeName;
	}
	public void setVoucherTypeName(String voucherTypeName) {
		this.voucherTypeName = voucherTypeName;
	}
	public boolean isDepartment() {
		return department;
	}
	public void setDepartment(boolean department) {
		this.department = department;
	}
	public boolean isIncExp() {
		return incExp;
	}
	public void setIncExp(boolean incExp) {
		this.incExp = incExp;
	}
	public boolean isMultipleDebitLedger() {
		return multipleDebitLedger;
	}
	public void setMultipleDebitLedger(boolean multipleDebitLedger) {
		this.multipleDebitLedger = multipleDebitLedger;
	}
	public boolean isTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(boolean ticketNo) {
		this.ticketNo = ticketNo;
	}
}
