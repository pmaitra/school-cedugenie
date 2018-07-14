package com.qts.icam.model.common;

import java.util.List;

public class TermWiseFees {
	
	private String termName;
	private List<CategoryAndFees> categoryAndFeesList;
	
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public List<CategoryAndFees> getCategoryAndFeesList() {
		return categoryAndFeesList;
	}
	public void setCategoryAndFeesList(List<CategoryAndFees> categoryAndFeesList) {
		this.categoryAndFeesList = categoryAndFeesList;
	}
	

}
