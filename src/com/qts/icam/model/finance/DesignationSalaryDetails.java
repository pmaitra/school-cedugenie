package com.qts.icam.model.finance;

public class DesignationSalaryDetails {	
	private int serialId;
	private String objectId;
	private String updatedBy;
	private String status;
	
	private String designation;
	private Double da;
	private Double tptl;	
	private Double smaHma;
	private Double ma;
	private Double sa;
	private Double gpf;
	private Double cpf;
	private Double meterCharge;
	private Double upto100ECRate;
	private Double above100ECRate;
	
	
	public int getSerialId() {
		return serialId;
	}
	public void setSerialId(int serialId) {
		this.serialId = serialId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Double getDa() {
		return da;
	}
	public void setDa(Double da) {
		this.da = da;
	}
	public Double getTptl() {
		return tptl;
	}
	public void setTptl(Double tptl) {
		this.tptl = tptl;
	}
	public Double getSmaHma() {
		return smaHma;
	}
	public void setSmaHma(Double smaHma) {
		this.smaHma = smaHma;
	}
	public Double getMa() {
		return ma;
	}
	public void setMa(Double ma) {
		this.ma = ma;
	}
	public Double getSa() {
		return sa;
	}
	public void setSa(Double sa) {
		this.sa = sa;
	}
	public Double getGpf() {
		return gpf;
	}
	public void setGpf(Double gpf) {
		this.gpf = gpf;
	}
	public Double getCpf() {
		return cpf;
	}
	public void setCpf(Double cpf) {
		this.cpf = cpf;
	}
	public Double getMeterCharge() {
		return meterCharge;
	}
	public void setMeterCharge(Double meterCharge) {
		this.meterCharge = meterCharge;
	}
	public Double getUpto100ECRate() {
		return upto100ECRate;
	}
	public void setUpto100ECRate(Double upto100ecRate) {
		upto100ECRate = upto100ecRate;
	}
	public Double getAbove100ECRate() {
		return above100ECRate;
	}
	public void setAbove100ECRate(Double above100ecRate) {
		above100ECRate = above100ecRate;
	}
}
