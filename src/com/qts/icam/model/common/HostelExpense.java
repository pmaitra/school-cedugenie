package com.qts.icam.model.common;

public class HostelExpense {
	
	private int hostelExpenseId;
	private String hostelExpenseObjectId;
	private String updatedBy;
	private String commodityCode;
	private double morning;
	private double noon;
	private double evening;
	private double night;
	private double total;
	private String startDate;
	private String endDate;
	
	public int getHostelExpenseId() {
		return hostelExpenseId;
	}
	public void setHostelExpenseId(int hostelExpenseId) {
		this.hostelExpenseId = hostelExpenseId;
	}
	public String getHostelExpenseObjectId() {
		return hostelExpenseObjectId;
	}
	public void setHostelExpenseObjectId(String hostelExpenseObjectId) {
		this.hostelExpenseObjectId = hostelExpenseObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}
	public double getMorning() {
		return morning;
	}
	public void setMorning(double morning) {
		this.morning = morning;
	}
	public double getNoon() {
		return noon;
	}
	public void setNoon(double noon) {
		this.noon = noon;
	}
	public double getEvening() {
		return evening;
	}
	public void setEvening(double evening) {
		this.evening = evening;
	}
	public double getNight() {
		return night;
	}
	public void setNight(double night) {
		this.night = night;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
