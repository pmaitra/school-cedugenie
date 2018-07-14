package com.qts.icam.model.ticket;

import java.util.List;

import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Resource;



public class ServiceType {
	private String ticketServiceObjectId;
	private String ticketServiceCode;
	private String ticketServiceDesc;
	private String updatedBy;
	private int ticketServiceId;
	private String ticketServiceName;
	private Resource ticketServiceOner;
	private Department department;
	private List<Resource> resourceList;
	
	
	
	
	
	public List<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	public String getTicketServiceObjectId() {
		return ticketServiceObjectId;
	}
	public void setTicketServiceObjectId(String ticketServiceObjectId) {
		this.ticketServiceObjectId = ticketServiceObjectId;
	}
	public String getTicketServiceCode() {
		return ticketServiceCode;
	}
	public void setTicketServiceCode(String ticketServiceCode) {
		this.ticketServiceCode = ticketServiceCode;
	}
	public String getTicketServiceDesc() {
		return ticketServiceDesc;
	}
	public void setTicketServiceDesc(String ticketServiceDesc) {
		this.ticketServiceDesc = ticketServiceDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getTicketServiceId() {
		return ticketServiceId;
	}
	public void setTicketServiceId(int ticketServiceId) {
		this.ticketServiceId = ticketServiceId;
	}
	public String getTicketServiceName() {
		return ticketServiceName;
	}
	public void setTicketServiceName(String ticketServiceName) {
		this.ticketServiceName = ticketServiceName;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Resource getTicketServiceOner() {
		return ticketServiceOner;
	}
	public void setTicketServiceOner(Resource ticketServiceOner) {
		this.ticketServiceOner = ticketServiceOner;
	}
	
}
