package com.qts.icam.model.common;

import java.util.List;

public class SMSData {

	private String sender;
	private String route;
	private String country;
	private List<SMS> sms;
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<SMS> getSms() {
		return sms;
	}
	public void setSms(List<SMS> sms) {
		this.sms = sms;
	}
	
}
