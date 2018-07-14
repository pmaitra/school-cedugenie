package com.qts.icam.model.administrator;

public class ProfileMatrix {
	
	private int profileMatrixId;
	private String profileMatrixObjectId;
	private String updatedBy;
	private String profilematrixCode;
	private String profileMatrixName;
	private Role role;
	private Module module;
	
	public int getProfileMatrixId() {
		return profileMatrixId;
	}
	public void setProfileMatrixId(int profileMatrixId) {
		this.profileMatrixId = profileMatrixId;
	}
	public String getProfileMatrixObjectId() {
		return profileMatrixObjectId;
	}
	public void setProfileMatrixObjectId(String profileMatrixObjectId) {
		this.profileMatrixObjectId = profileMatrixObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getProfilematrixCode() {
		return profilematrixCode;
	}
	public void setProfilematrixCode(String profilematrixCode) {
		this.profilematrixCode = profilematrixCode;
	}
	public String getProfileMatrixName() {
		return profileMatrixName;
	}
	public void setProfileMatrixName(String profileMatrixName) {
		this.profileMatrixName = profileMatrixName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
}
