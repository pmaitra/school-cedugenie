package com.qts.icam.model.administrator;

public class ServerConfiguration {

	private  String updatedBy;
	private  String directoryServerType;
	private  String directoryServerUrl;
	private  String directoryServerSecurityAuthenticationType;
	private  String directoryServerPort;
	private  String directoryServerUserDN;
	private  String directoryServerBaseDN;
	private  String directoryServerPassword;
	private  String directoryServerFilter;
	
	private  String jdbcURL;
	private  String jdbcPort;
	private  String jdbcUserName;
    private  String jdbcPassword;
    private  String jdbcDatabaseName;
    private  String jdbcMaxStatement;
    private  String jdbcStatementCacheNumDeferredCloseThread;
    private  String jdbcMaxIdleTime;
    private  String jdbcDriverClassName;
    private  String jdbcMaxActive;
    private  String jdbcDialect;
    private  String jdbcInitialSize;
    private  String jdbcAcquireIncrement;
   
    
    private  String mailServerIp;
	private  String mailServerPort;
	private  String mailUserName;
    private  String mailPassword;
    private  String mailTransportProtocol;
    private  String mailSmtpAuth; 
    private  String mailSmtpStarttlsEnable; 
    private  String mailDebug; 
    
    private  String authkey;
	private  String senderId;
	private  String route;
    private  String smsURL;
    private  String proxy;
    private  String proxyUser; 
    private  String proxyPassword; 
    private  String proxySet;   	 
    private  String proxyHost;
    private  String proxyPort;
    
    /**added by saif to configure rest APIs*/
    private String programDetails;
    private String newCandidate;
    private String scrutinizedCandidate;
    private String locationDetails;
    private String selectedCandidates;
    private String admittedCandidates;
    private String alumniData;
    private String displayNotice;
    
    	
    
	public String getAuthkey() {
		return authkey;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getSmsURL() {
		return smsURL;
	}
	public void setSmsURL(String smsURL) {
		this.smsURL = smsURL;
	}
	
	public String getProxy() {
		return proxy;
	}
	public void setProxy(String proxy) {
		this.proxy = proxy;
	}
	public String getProxyUser() {
		return proxyUser;
	}
	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
	}
	public String getProxyPassword() {
		return proxyPassword;
	}
	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}
	public String getProxySet() {
		return proxySet;
	}
	public void setProxySet(String proxySet) {
		this.proxySet = proxySet;
	}
	public String getProxyHost() {
		return proxyHost;
	}
	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}
	public String getProxyPort() {
		return proxyPort;
	}
	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}
	public String getDirectoryServerType() {
		return directoryServerType;
	}
	public void setDirectoryServerType(String directoryServerType) {
		this.directoryServerType = directoryServerType;
	}
	public String getDirectoryServerUrl() {
		return directoryServerUrl;
	}
	public void setDirectoryServerUrl(String directoryServerUrl) {
		this.directoryServerUrl = directoryServerUrl;
	}
	public String getDirectoryServerSecurityAuthenticationType() {
		return directoryServerSecurityAuthenticationType;
	}
	public void setDirectoryServerSecurityAuthenticationType(
			String directoryServerSecurityAuthenticationType) {
		this.directoryServerSecurityAuthenticationType = directoryServerSecurityAuthenticationType;
	}
	public String getDirectoryServerPort() {
		return directoryServerPort;
	}
	public void setDirectoryServerPort(String directoryServerPort) {
		this.directoryServerPort = directoryServerPort;
	}
	public String getDirectoryServerUserDN() {
		return directoryServerUserDN;
	}
	public void setDirectoryServerUserDN(String directoryServerUserDN) {
		this.directoryServerUserDN = directoryServerUserDN;
	}
	public String getDirectoryServerBaseDN() {
		return directoryServerBaseDN;
	}
	public void setDirectoryServerBaseDN(String directoryServerBaseDN) {
		this.directoryServerBaseDN = directoryServerBaseDN;
	}
	public String getDirectoryServerPassword() {
		return directoryServerPassword;
	}
	public void setDirectoryServerPassword(String directoryServerPassword) {
		this.directoryServerPassword = directoryServerPassword;
	}
	public String getDirectoryServerFilter() {
		return directoryServerFilter;
	}
	public void setDirectoryServerFilter(String directoryServerFilter) {
		this.directoryServerFilter = directoryServerFilter;
	}
	
	
	
	
	
	//newly added.......................
	
	public String getJdbcURL() {
		return jdbcURL;
	}
	public void setJdbcURL(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}
	public String getJdbcPort() {
		return jdbcPort;
	}
	public void setJdbcPort(String jdbcPort) {
		this.jdbcPort = jdbcPort;
	}
	public String getJdbcUserName() {
		return jdbcUserName;
	}
	public void setJdbcUserName(String jdbcUserName) {
		this.jdbcUserName = jdbcUserName;
	}
	public String getJdbcPassword() {
		return jdbcPassword;
	}
	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}
	public String getJdbcDatabaseName() {
		return jdbcDatabaseName;
	}
	public void setJdbcDatabaseName(String jdbcDatabaseName) {
		this.jdbcDatabaseName = jdbcDatabaseName;
	}
	public String getJdbcMaxStatement() {
		return jdbcMaxStatement;
	}
	public void setJdbcMaxStatement(String jdbcMaxStatement) {
		this.jdbcMaxStatement = jdbcMaxStatement;
	}
	public String getJdbcStatementCacheNumDeferredCloseThread() {
		return jdbcStatementCacheNumDeferredCloseThread;
	}
	public void setJdbcStatementCacheNumDeferredCloseThread(
			String jdbcStatementCacheNumDeferredCloseThread) {
		this.jdbcStatementCacheNumDeferredCloseThread = jdbcStatementCacheNumDeferredCloseThread;
	}
	public String getJdbcMaxIdleTime() {
		return jdbcMaxIdleTime;
	}
	public void setJdbcMaxIdleTime(String jdbcMaxIdleTime) {
		this.jdbcMaxIdleTime = jdbcMaxIdleTime;
	}
	public String getJdbcDriverClassName() {
		return jdbcDriverClassName;
	}
	public void setJdbcDriverClassName(String jdbcDriverClassName) {
		this.jdbcDriverClassName = jdbcDriverClassName;
	}
	public String getJdbcMaxActive() {
		return jdbcMaxActive;
	}
	public void setJdbcMaxActive(String jdbcMaxActive) {
		this.jdbcMaxActive = jdbcMaxActive;
	}
	public String getJdbcDialect() {
		return jdbcDialect;
	}
	public void setJdbcDialect(String jdbcDialect) {
		this.jdbcDialect = jdbcDialect;
	}
	public String getJdbcAcquireIncrement() {
		return jdbcAcquireIncrement;
	}
	public void setJdbcAcquireIncrement(String jdbcAcquireIncrement) {
		this.jdbcAcquireIncrement = jdbcAcquireIncrement;
	}
	
	
	
	
	
	public String getJdbcInitialSize() {
		return jdbcInitialSize;
	}
	public void setJdbcInitialSize(String jdbcInitialSize) {
		this.jdbcInitialSize = jdbcInitialSize;
	}
	public String getMailServerIp() {
		return mailServerIp;
	}
	public void setMailServerIp(String mailServerIp) {
		this.mailServerIp = mailServerIp;
	}
	public String getMailServerPort() {
		return mailServerPort;
	}
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}
	public String getMailUserName() {
		return mailUserName;
	}
	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}
	public String getMailTransportProtocol() {
		return mailTransportProtocol;
	}
	public void setMailTransportProtocol(String mailTransportProtocol) {
		this.mailTransportProtocol = mailTransportProtocol;
	}
	public String getMailSmtpAuth() {
		return mailSmtpAuth;
	}
	public void setMailSmtpAuth(String mailSmtpAuth) {
		this.mailSmtpAuth = mailSmtpAuth;
	}
	public String getMailSmtpStarttlsEnable() {
		return mailSmtpStarttlsEnable;
	}
	public void setMailSmtpStarttlsEnable(String mailSmtpStarttlsEnable) {
		this.mailSmtpStarttlsEnable = mailSmtpStarttlsEnable;
	}
	public String getMailDebug() {
		return mailDebug;
	}
	public void setMailDebug(String mailDebug) {
		this.mailDebug = mailDebug;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	//Newly Added by Saif 27/07/2017.....................
	public String getProgramDetails() {
		return programDetails;
	}
	public void setProgramDetails(String programDetails) {
		this.programDetails = programDetails;
	}
	public String getNewCandidate() {
		return newCandidate;
	}
	public void setNewCandidate(String newCandidate) {
		this.newCandidate = newCandidate;
	}
	public String getScrutinizedCandidate() {
		return scrutinizedCandidate;
	}
	public void setScrutinizedCandidate(String scrutinizedCandidate) {
		this.scrutinizedCandidate = scrutinizedCandidate;
	}
	public String getLocationDetails() {
		return locationDetails;
	}
	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}
	public String getSelectedCandidates() {
		return selectedCandidates;
	}
	public void setSelectedCandidates(String selectedCandidates) {
		this.selectedCandidates = selectedCandidates;
	}
	public String getAdmittedCandidates() {
		return admittedCandidates;
	}
	public void setAdmittedCandidates(String admittedCandidates) {
		this.admittedCandidates = admittedCandidates;
	}
	public String getAlumniData() {
		return alumniData;
	}
	public void setAlumniData(String alumniData) {
		this.alumniData = alumniData;
	}
	public String getDisplayNotice() {
		return displayNotice;
	}
	public void setDisplayNotice(String displayNotice) {
		this.displayNotice = displayNotice;
	}
    
    
}
