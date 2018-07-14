package com.qts.icam.model.common;

import java.io.Serializable;
/**
 * 
 * @author praveen.kamble
 *
 */
public class LicenseInfo implements Serializable {

	private static final long serialVersionUID = -2212128861127936091L;
	private  byte[] signedInfo;
	private String organizationName;
	private String organizationId;
	private String organizationAliasName;
    private String organizationWebsite;
    private String organizationEmailId;
    private String contactPersonName;
    private String contactNumber;
    private String systemMACAddress;
    private String systemStaticIPAddress;
	private String installationTimeStamp;	
	private String serializedFileLocation;
	private String serializedFileName;
	private String clientKeyStoreFileLocation;
	private String clientKeyStoreFileName;
	private String clientKeyStoreAlias;
	private String clientStorePassword;
	private String purchasedModules;
	private String concurrentLoggedInUser;
	private String registryKey;
	private String registryHive;
	private String applicationTrailPeriod;
    private String strSelectedModule;        
    private String licenseType;
    private String licenseValidity;
    
    
    
    
    
    
    
    
    
    
    
	public byte[] getSignedInfo() {
		return signedInfo;
	}
	public void setSignedInfo(byte[] signedInfo) {
		this.signedInfo = signedInfo;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationAliasName() {
		return organizationAliasName;
	}
	public void setOrganizationAliasName(String organizationAliasName) {
		this.organizationAliasName = organizationAliasName;
	}
	public String getOrganizationWebsite() {
		return organizationWebsite;
	}
	public void setOrganizationWebsite(String organizationWebsite) {
		this.organizationWebsite = organizationWebsite;
	}
	public String getOrganizationEmailId() {
		return organizationEmailId;
	}
	public void setOrganizationEmailId(String organizationEmailId) {
		this.organizationEmailId = organizationEmailId;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getSystemMACAddress() {
		return systemMACAddress;
	}
	public void setSystemMACAddress(String systemMACAddress) {
		this.systemMACAddress = systemMACAddress;
	}
	public String getSystemStaticIPAddress() {
		return systemStaticIPAddress;
	}
	public void setSystemStaticIPAddress(String systemStaticIPAddress) {
		this.systemStaticIPAddress = systemStaticIPAddress;
	}
	public String getInstallationTimeStamp() {
		return installationTimeStamp;
	}
	public void setInstallationTimeStamp(String installationTimeStamp) {
		this.installationTimeStamp = installationTimeStamp;
	}
	public String getSerializedFileLocation() {
		return serializedFileLocation;
	}
	public void setSerializedFileLocation(String serializedFileLocation) {
		this.serializedFileLocation = serializedFileLocation;
	}
	public String getSerializedFileName() {
		return serializedFileName;
	}
	public void setSerializedFileName(String serializedFileName) {
		this.serializedFileName = serializedFileName;
	}
	public String getClientKeyStoreFileLocation() {
		return clientKeyStoreFileLocation;
	}
	public void setClientKeyStoreFileLocation(String clientKeyStoreFileLocation) {
		this.clientKeyStoreFileLocation = clientKeyStoreFileLocation;
	}
	public String getClientKeyStoreFileName() {
		return clientKeyStoreFileName;
	}
	public void setClientKeyStoreFileName(String clientKeyStoreFileName) {
		this.clientKeyStoreFileName = clientKeyStoreFileName;
	}
	public String getClientKeyStoreAlias() {
		return clientKeyStoreAlias;
	}
	public void setClientKeyStoreAlias(String clientKeyStoreAlias) {
		this.clientKeyStoreAlias = clientKeyStoreAlias;
	}
	public String getClientStorePassword() {
		return clientStorePassword;
	}
	public void setClientStorePassword(String clientStorePassword) {
		this.clientStorePassword = clientStorePassword;
	}
	public String getPurchasedModules() {
		return purchasedModules;
	}
	public void setPurchasedModules(String purchasedModules) {
		this.purchasedModules = purchasedModules;
	}
	public String getConcurrentLoggedInUser() {
		return concurrentLoggedInUser;
	}
	public void setConcurrentLoggedInUser(String concurrentLoggedInUser) {
		this.concurrentLoggedInUser = concurrentLoggedInUser;
	}
	public String getRegistryKey() {
		return registryKey;
	}
	public void setRegistryKey(String registryKey) {
		this.registryKey = registryKey;
	}
	public String getRegistryHive() {
		return registryHive;
	}
	public void setRegistryHive(String registryHive) {
		this.registryHive = registryHive;
	}
	public String getApplicationTrailPeriod() {
		return applicationTrailPeriod;
	}
	public void setApplicationTrailPeriod(String applicationTrailPeriod) {
		this.applicationTrailPeriod = applicationTrailPeriod;
	}
	public String getStrSelectedModule() {
		return strSelectedModule;
	}
	public void setStrSelectedModule(String strSelectedModule) {
		this.strSelectedModule = strSelectedModule;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public String getLicenseValidity() {
		return licenseValidity;
	}
	public void setLicenseValidity(String licenseValidity) {
		this.licenseValidity = licenseValidity;
	}
	
	

	
	
	
	

	
	
}
