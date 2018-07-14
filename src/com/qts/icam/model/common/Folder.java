package com.qts.icam.model.common;

import java.util.List;

/**
 * 
 * @author parmanand.singh
 * @version 1.0
 *
 */
public class Folder {
	private String folderObjectId;
	private String folderCode;
	private String folderName;
	private String folderDesc;
	private List<Attachment> attachments;
	
	/**
	 * 
	 * @return String
	 */
	public String getFolderObjectId() {
		return folderObjectId;
	}

	public void setFolderObjectId(String folderObjectId) {
		this.folderObjectId = folderObjectId;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getFolderCode() {
		return folderCode;
	}
	
	

	/**
	 * 
	 * @param String
	 */
	public void setFolderCode(String folderCode) {
		this.folderCode = folderCode;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getFolderName() {
		return folderName;
	}
	
	/**
	 * 
	 * @param String
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getFolderDesc() {
		return folderDesc;
	}
	
	/**
	 * 
	 * @param String
	 */
	public void setFolderDesc(String folderDesc) {
		this.folderDesc = folderDesc;
	}
	
	/**
	 * 
	 * @return List<Attachment>
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}
	
	/**
	 * 
	 * @param List<Attachment>
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}


	
	
	

}
