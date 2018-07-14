package com.qts.icam.model.common;

import java.util.List;

/**
 * 
 * @author parmanand.singh
 * @version 1.0
 *
 */
public class Storage {

	private String storageObject;
	private String storageCode;
	private String storageName;
	private String storageDesc;
	private String storageRootPath;	 
	private String  appConfFile;
	private List<Folder> folders;
	private Folder folder;
	private Attachment attachment;
	
	/**
	 * 
	 * @return String
	 */
	public String getStorageObject() {
		return storageObject;
	}
	
	/**
	 * 
	 * @param String
	 */
	public void setStorageObject(String storageObject) {
		this.storageObject = storageObject;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getStorageCode() {
		return storageCode;
	}
	
	/**
	 * 
	 * @param String
	 */
	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getStorageName() {
		return storageName;
	}
	
	/**
	 * 
	 * @param String
	 */
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getStorageDesc() {
		return storageDesc;
	}
	
	/**
	 * 
	 * @param String
	 */
	public void setStorageDesc(String storageDesc) {
		this.storageDesc = storageDesc;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getStorageRootPath() {
		return storageRootPath;
	}
	
	/**
	 * 
	 * @param String
	 */
	public void setStorageRootPath(String storageRootPath) {
		this.storageRootPath = storageRootPath;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getAppConfFile() {
		return appConfFile;
	}
	
	/**
	 * 
	 * @param String
	 */
	public void setAppConfFile(String appConfFile) {
		this.appConfFile = appConfFile;
	}
	
	/**
	 * 
	 * @return List<Folder>
	 */
	public List<Folder> getFolders() {
		return folders;
	}
	
	/**
	 * 
	 * @param List<Folder>
	 */
	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}
	
	/**
	 * 
	 * @return Folder
	 */
	public Folder getFolder() {
		return folder;
	}

	/**
	 * 
	 * @param Folder
	 */
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	/**
	 * 
	 * @return Attachment
	 */
	public Attachment getAttachment() {
		return attachment;
	}
	
	/**
	 * 
	 * @param Attachment
	 */
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
}
