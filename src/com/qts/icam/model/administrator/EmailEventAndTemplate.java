package com.qts.icam.model.administrator;

import java.util.List;

public class EmailEventAndTemplate {
	private String serialId;
	private  String updatedBy;
	private String objectId;
	private String eventName;
	private String eventCode;
	
	/**Used for the email template*/
	private String templateCode;
	private String emailSubject;
	private String emailBody;
	private String emailFooter;
	
	private String templateFor;		//Added by Saif 17-10-2017
	private List<String> actionList; //Added by ranita.sur 26-10-2017
	private List<String> templateList; //Added by ranita.sur 26-10-2017
	private List<String> recipientGroupList; //Added by ranita.sur 26-10-2017
	
	public String getSerialId() {
		return serialId;
	}
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public String getEmailFooter() {
		return emailFooter;
	}
	public void setEmailFooter(String emailFooter) {
		this.emailFooter = emailFooter;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateFor() {
		return templateFor;
	}
	public void setTemplateFor(String templateFor) {
		this.templateFor = templateFor;
	}
	public List<String> getActionList() {
		return actionList;
	}
	public void setActionList(List<String> actionList) {
		this.actionList = actionList;
	}
	public List<String> getTemplateList() {
		return templateList;
	}
	public void setTemplateList(List<String> templateList) {
		this.templateList = templateList;
	}
	public List<String> getRecipientGroupList() {
		return recipientGroupList;
	}
	public void setRecipientGroupList(List<String> recipientGroupList) {
		this.recipientGroupList = recipientGroupList;
	}

}
