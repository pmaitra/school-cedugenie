package com.qts.icam.model.common;

public class RepositoryStructure {
	
	private String repositoryObjectId;
	private String updatedBy;
	private String repositoryPathCode;
	private String repositoryPathName;
	private String repositoryPathDesc;
	private String os;
	
	public String getRepositoryObjectId() {
		return repositoryObjectId;
	}
	public void setRepositoryObjectId(String repositoryObjectId) {
		this.repositoryObjectId = repositoryObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getRepositoryPathCode() {
		return repositoryPathCode;
	}
	public void setRepositoryPathCode(String repositoryPathCode) {
		this.repositoryPathCode = repositoryPathCode;
	}
	public String getRepositoryPathName() {
		return repositoryPathName;
	}
	public void setRepositoryPathName(String repositoryPathName) {
		this.repositoryPathName = repositoryPathName;
	}
	public String getRepositoryPathDesc() {
		return repositoryPathDesc;
	}
	public void setRepositoryPathDesc(String repositoryPathDesc) {
		this.repositoryPathDesc = repositoryPathDesc;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
}
