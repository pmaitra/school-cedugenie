package com.qts.icam.model.common;

import java.util.List;

public class CategoryAndFees {

	private String feesStructureName;
	private List<SocialCategory> socialCategoryList;
	
	public String getFeesStructureName() {
		return feesStructureName;
	}
	public void setFeesStructureName(String feesStructureName) {
		this.feesStructureName = feesStructureName;
	}
	public List<SocialCategory> getSocialCategoryList() {
		return socialCategoryList;
	}
	public void setSocialCategoryList(List<SocialCategory> socialCategoryList) {
		this.socialCategoryList = socialCategoryList;
	}
}
