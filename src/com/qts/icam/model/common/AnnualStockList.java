package com.qts.icam.model.common;

import java.util.List;

public class AnnualStockList {
	
	private List<AnnualStock> listAnnualStock;
	private String updatedBy;

	public List<AnnualStock> getListAnnualStock() {
		return listAnnualStock;
	}

	public void setListAnnualStock(List<AnnualStock> listAnnualStock) {
		this.listAnnualStock = listAnnualStock;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	

}
