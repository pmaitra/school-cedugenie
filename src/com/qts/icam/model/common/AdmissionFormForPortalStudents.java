package com.qts.icam.model.common;

import java.util.List;

public class AdmissionFormForPortalStudents {

	private int status;
	private String message;
	private List<Data>data;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
	
	
	
	
}
