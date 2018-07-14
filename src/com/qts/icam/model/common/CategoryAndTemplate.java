package com.qts.icam.model.common;

public class CategoryAndTemplate {
	private String categoryAndTemplateObjectId;
	private String updatedBy;
	private String category;
	private String template;
	private String userId;	
	
	//Added By naimisha 04/05/2018
	
	private String status;
	
	
	private String templateType;
	/**
	 * @return the categoryAndTemplateObjectId
	 */
	public String getCategoryAndTemplateObjectId() {
		return categoryAndTemplateObjectId;
	}
	/**
	 * @param categoryAndTemplateObjectId the categoryAndTemplateObjectId to set
	 */
	public void setCategoryAndTemplateObjectId(String categoryAndTemplateObjectId) {
		this.categoryAndTemplateObjectId = categoryAndTemplateObjectId;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
