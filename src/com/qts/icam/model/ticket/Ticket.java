package com.qts.icam.model.ticket;

import java.util.List;

import com.qts.icam.model.administrator.Approver;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.StudentAttendance;
import com.qts.icam.model.common.UploadFile;



public class Ticket {
	private String ticketRecId;
	private String ticketObjectId;
	private String ticketCode;
	private String ticketDesc;
	private String updatedBy;
	
	private int ticketId;
	private String affectedUser;
	private String reportedBy;
	private String comment;
	private String status;
	private String ticketSummary;
	private String description;
	private List<TicketComment> commentList;
	private ServiceType ticketService;
	private String ticketOpenDate;
	private String ticketCloseDate;
	private List<Attachment> attachmentList;
	private String queryStatus;
	
	private String moduleName;
	private int ticketMaxDays;
	private int ticketMinDays;
	private List<Module> moduleList;
	private UploadFile uploadFile;
	
	
	/***********Added by Naimisha 10.04.2017**********/
	
	private String xmlFilePath;
	private String xmlFileName;
	private String xsltFilePath;
	private String xsltFileName;
	private String pdfFilePath;
	private String pdfFileName;
	private String reportNOCConfigFilePath;
	private AcademicYear currentAcademicYear;
	private String rootPath;
	private String message;
	
	private String reportGatePassConfigFilePath;
	
	/*Added By Naimisha 29082017*/
	
	private List<Approver> approverGroupList;
	
	//Added By Naimisha 18102017
	
	private String taskStatus;
	private String approval;
	
	
	//Added On 25102017
	
	private List<String>taskList;
	private List<String>leveList;
	private List<String>resourceList;
	
	private List<TaskComment> taskCommentList;
	
	
	
	
	private String standard;
	private String section;
	private String rollNumber;
	private String fromDate;
	private String toDate;
	private List<StudentAttendance> studentAttendanceList;
	
	//Added by naimisha 03032018
	
	private String userName;
	private String password;
	private String url;
	
	//Added by Naimisha 05042018
	
	private String department;
	
	
	//Added By Naimisha 12042018
	
	private List<ServiceType>serviceTypeList;
	private String key;
	private String value;
	private boolean isLinked;
	private boolean isFinance;
	private String functionalityName;
	private String  note;
	
	//Added By Naimisha 13042018
	
	private String mode;
	private String paymentMode;
	private String ledger;
	private double amount;
	private String type;
	
	//Added by naimisha 09052018
	
	private String tableName;
	
	//PRAD JUNE 12 2018
	private String webIQAvailable;
	
	public String getWebIQAvailable() {
		return webIQAvailable;
	}
	public void setWebIQAvailable(String webIQAvailable) {
		this.webIQAvailable = webIQAvailable;
	}
	
	public int getTicketMinDays() {
		return ticketMinDays;
	}
	public void setTicketMinDays(int ticketMinDays) {
		this.ticketMinDays = ticketMinDays;
	}
	public List<Module> getModuleList() {
		return moduleList;
	}
	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}
	public int getTicketMaxDays() {
		return ticketMaxDays;
	}
	public void setTicketMaxDays(int ticketMaxDays) {
		this.ticketMaxDays = ticketMaxDays;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getTicketRecId() {
		return ticketRecId;
	}
	public void setTicketRecId(String ticketRecId) {
		this.ticketRecId = ticketRecId;
	}
	public String getTicketObjectId() {
		return ticketObjectId;
	}
	public void setTicketObjectId(String ticketObjectId) {
		this.ticketObjectId = ticketObjectId;
	}
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	public String getTicketDesc() {
		return ticketDesc;
	}
	public void setTicketDesc(String ticketDesc) {
		this.ticketDesc = ticketDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getAffectedUser() {
		return affectedUser;
	}
	public void setAffectedUser(String affectedUser) {
		this.affectedUser = affectedUser;
	}
	public String getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public String getTicketSummary() {
		return ticketSummary;
	}
	public void setTicketSummary(String ticketSummary) {
		this.ticketSummary = ticketSummary;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<TicketComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<TicketComment> commentList) {
		this.commentList = commentList;
	}
	public ServiceType getTicketService() {
		return ticketService;
	}
	public void setTicketService(ServiceType ticketService) {
		this.ticketService = ticketService;
	}
	public String getTicketOpenDate() {
		return ticketOpenDate;
	}
	public void setTicketOpenDate(String ticketOpenDate) {
		this.ticketOpenDate = ticketOpenDate;
	}
	public String getTicketCloseDate() {
		return ticketCloseDate;
	}
	public void setTicketCloseDate(String ticketCloseDate) {
		this.ticketCloseDate = ticketCloseDate;
	}

	public String getQueryStatus() {
		return queryStatus;
	}
	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}
	public UploadFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public String getXmlFilePath() {
		return xmlFilePath;
	}
	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	public String getXmlFileName() {
		return xmlFileName;
	}
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}
	public String getXsltFilePath() {
		return xsltFilePath;
	}
	public void setXsltFilePath(String xsltFilePath) {
		this.xsltFilePath = xsltFilePath;
	}
	public String getXsltFileName() {
		return xsltFileName;
	}
	public void setXsltFileName(String xsltFileName) {
		this.xsltFileName = xsltFileName;
	}
	public String getPdfFilePath() {
		return pdfFilePath;
	}
	public void setPdfFilePath(String pdfFilePath) {
		this.pdfFilePath = pdfFilePath;
	}
	public String getPdfFileName() {
		return pdfFileName;
	}
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	public String getReportNOCConfigFilePath() {
		return reportNOCConfigFilePath;
	}
	public void setReportNOCConfigFilePath(String reportNOCConfigFilePath) {
		this.reportNOCConfigFilePath = reportNOCConfigFilePath;
	}
	public AcademicYear getCurrentAcademicYear() {
		return currentAcademicYear;
	}
	public void setCurrentAcademicYear(AcademicYear currentAcademicYear) {
		this.currentAcademicYear = currentAcademicYear;
	}
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReportGatePassConfigFilePath() {
		return reportGatePassConfigFilePath;
	}
	public void setReportGatePassConfigFilePath(String reportGatePassConfigFilePath) {
		this.reportGatePassConfigFilePath = reportGatePassConfigFilePath;
	}
	public List<Approver> getApproverGroupList() {
		return approverGroupList;
	}
	public void setApproverGroupList(List<Approver> approverGroupList) {
		this.approverGroupList = approverGroupList;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public List<String> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<String> taskList) {
		this.taskList = taskList;
	}
	public List<String> getLeveList() {
		return leveList;
	}
	public void setLeveList(List<String> leveList) {
		this.leveList = leveList;
	}
	public List<String> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}
	public List<TaskComment> getTaskCommentList() {
		return taskCommentList;
	}
	public void setTaskCommentList(List<TaskComment> taskCommentList) {
		this.taskCommentList = taskCommentList;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public List<StudentAttendance> getStudentAttendanceList() {
		return studentAttendanceList;
	}
	public void setStudentAttendanceList(List<StudentAttendance> studentAttendanceList) {
		this.studentAttendanceList = studentAttendanceList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public List<ServiceType> getServiceTypeList() {
		return serviceTypeList;
	}
	public void setServiceTypeList(List<ServiceType> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isLinked() {
		return isLinked;
	}
	public void setLinked(boolean isLinked) {
		this.isLinked = isLinked;
	}
	public boolean isFinance() {
		return isFinance;
	}
	public void setFinance(boolean isFinance) {
		this.isFinance = isFinance;
	}
	public String getFunctionalityName() {
		return functionalityName;
	}
	public void setFunctionalityName(String functionalityName) {
		this.functionalityName = functionalityName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getLedger() {
		return ledger;
	}
	public void setLedger(String ledger) {
		this.ledger = ledger;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
