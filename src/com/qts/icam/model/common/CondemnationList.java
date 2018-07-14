package com.qts.icam.model.common;

import java.util.List;

public class CondemnationList {
	
	private List<Condemnation> listCondemnationStock;
	private String updatedBy;

	public List<Condemnation> getListCondemnationStock() {
		return listCondemnationStock;
	}

	public void setListCondemnationStock(List<Condemnation> listCondemnationStock) {
		this.listCondemnationStock = listCondemnationStock;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	

}
