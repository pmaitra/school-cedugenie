package com.qts.icam.model.administrator;

import javax.xml.bind.annotation.XmlElement;

public class SalaryDetailsForXml {
	String breakupName;
	String breakupType;
	double breakupAmount;
	
	public String getBreakupName() {
		return breakupName;
	}
	@XmlElement(name="Name")
	public void setBreakupName(String breakupName) {
		this.breakupName = breakupName;
	}
	
	public String getBreakupType() {
		return breakupType;
	}
	@XmlElement(name="Type")
	public void setBreakupType(String breakupType) {
		this.breakupType = breakupType;
	}
	
	public double getBreakupAmount() {
		return breakupAmount;
	}
	@XmlElement(name="Amount")
	public void setBreakupAmount(double breakupAmount) {
		this.breakupAmount = breakupAmount;
	}
}
