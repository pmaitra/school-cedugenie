package com.qts.icam.model.finance;

import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.SalaryTemplate;

public class StaffSalaryDetails {	
	private String objectId;
	private String updatedBy;
	
	private Employee employee;	
	private SalaryTemplate salaryTemplate;	
	private double gpf;
	private double basic;	
	private double ed;
	private double wc;
	private double freeElectricCharge;
	private double ip;
	private double gip;
	private double pt;
	private double nps;
	private double npsBoth;
	
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getGpf() {
		return gpf;
	}
	public void setGpf(double gpf) {
		this.gpf = gpf;
	}
	public SalaryTemplate getSalaryTemplate() {
		return salaryTemplate;
	}
	public void setSalaryTemplate(SalaryTemplate salaryTemplate) {
		this.salaryTemplate = salaryTemplate;
	}
	public double getBasic() {
		return basic;
	}
	public void setBasic(double basic) {
		this.basic = basic;
	}
	public double getEd() {
		return ed;
	}
	public void setEd(double ed) {
		this.ed = ed;
	}
	public double getWc() {
		return wc;
	}
	public void setWc(double wc) {
		this.wc = wc;
	}
	public double getFreeElectricCharge() {
		return freeElectricCharge;
	}
	public void setFreeElectricCharge(double freeElectricCharge) {
		this.freeElectricCharge = freeElectricCharge;
	}
	public double getIp() {
		return ip;
	}
	public void setIp(double ip) {
		this.ip = ip;
	}
	public double getGip() {
		return gip;
	}
	public void setGip(double gip) {
		this.gip = gip;
	}
	public double getPt() {
		return pt;
	}
	public void setPt(double pt) {
		this.pt = pt;
	}
	public double getNps() {
		return nps;
	}
	public void setNps(double nps) {
		this.nps = nps;
	}
	public double getNpsBoth() {
		return npsBoth;
	}
	public void setNpsBoth(double npsBoth) {
		this.npsBoth = npsBoth;
	}
	
	
}
