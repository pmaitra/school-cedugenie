package com.qts.icam.model.erp;

import java.util.List;

import com.qts.icam.model.common.Address;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.BloodGroup;
import com.qts.icam.model.common.Country;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Marks;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.TeachingLevel;
import com.qts.icam.model.common.WorkShift;

public class Employee {
	private int employeeId;
	private String objectId;
	private String employeeCode;
	private String employeeName;
	private String employeeDesc;
	private String updatedBy;	
	private String status;
	
	private String fatherOccupation;	
	private String motherOccupation;		
	
	private String exServicePersonnel;
	private String service;
	private String serviceNumber;
	private String rank;
	
	private String dateOfJoining;
	private String dateOfRetirement;
	
	private Address address;
	private Resource resource;
	private List<Resource> resourceList;
	private List<ResourceType> resourceTypeList;
	private Qualification qualification;
	private List<Qualification> qualificationList;
	private List<OtherQualification> otherQualificationList;
	private List<Publication> publicationList;
	private List<EmployeeDependent> employeeDependentList;

	private BloodGroup bloodGroup;
	private SocialCategory socialCategory;
	private EmployeeType employeeType;
	private JobType jobType;
	private Designation designation;	
	private Department department;
	private SalaryTemplate salaryTemplate;
	private FixationOfPay fixationOfPay;
	private Attachment attachment;
	
	private int basicPay;
	private String medicalAttention;
	private String maritalStatus;
	private String spouseName;
	
	private String bankName;
	private String branchCode;
	private String branchIFSCCode;
	private String accountType;
	private String accountHolderName;
	private String accountNumber;
	
	private List<BloodGroup> bloodGroupList;
	private List<SocialCategory> socialCategoryList;
	private List<Country> countryList;
	private List<Attachment> attachmentList;
	private List<EmployeeType> employeeTypeList;
	private List<JobType> jobTypeList;
	private List<Designation> designationList;
	private List<Department> departmentList;
	private List<FixationOfPay> fixationOfPayList;
	private List<SalaryTemplate> salaryTemplateList;
	private List<Organization> organizationList;	
	private String qualificationSummary;
	private List<DesignationType> designationTypeList;
	
	private String modeOfRetirement;
	private String reasonOfRetirement;
	private String actualDateOfRetirement;	
	private String confidentialInformation;
	
	private List<NomineeDetails> nomineeDetailsList;	
	private List<AwardsAndRecognization> awardsAndRecognizationList;	
	private List<WorkShopAndTraining> workShopAndTrainingList;
	private List<WorkShift> selectedWorkShiftList;
	private List<SalaryBreakUp> salaryBreakUpList;
	private List<SalaryBreakUp> salaryBreakUpListForShow;
	private List<SalaryBreakUp> extraEarningList;
	private List<Leave> leaveList;
	private String daysInMonths;
	
	
	private String dateOfInterview;
	private String timeOfInterview;
	private String roomNumber;
	private String venue;
	private String experience;
	private String referredBy;
	private List<TeachingLevel> teachingLevelList;
	private TeachingLevel teachingLevel;
	private List<Marks> marksList;
	private String subject;
	private double marks;
	private String comment;
	private String interviewStatus;
	private String paymentMode;
	private String paymentDate;
	private double grossAmount;
	private double netAmount;
	private DesignationLevel designationLevel;
	private SalaryBreakUp individualDeduction;
	private ResourceAttendence resourceAttendance;
	private List<Attachment> previousDocumentsAttachmentList;
	private List<Attachment> publicationDocAttachmentList;
	//added by ranita.sur for employeeLedgerMapping 01082017
	private String empRecId;
	
	//Added By Naimisha 28/03/2018
	private String teachingLevelName;
	
	//Added By Naimisha 16/04/2018
	private String reportingManger;
	
	
	
	public double getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeDesc() {
		return employeeDesc;
	}
	public void setEmployeeDesc(String employeeDesc) {
		this.employeeDesc = employeeDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFatherOccupation() {
		return fatherOccupation;
	}
	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}
	public String getMotherOccupation() {
		return motherOccupation;
	}
	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}
	public String getExServicePersonnel() {
		return exServicePersonnel;
	}
	public void setExServicePersonnel(String exServicePersonnel) {
		this.exServicePersonnel = exServicePersonnel;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	public List<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public SocialCategory getSocialCategory() {
		return socialCategory;
	}
	public void setSocialCategory(SocialCategory socialCategory) {
		this.socialCategory = socialCategory;
	}
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public FixationOfPay getFixationOfPay() {
		return fixationOfPay;
	}
	public void setFixationOfPay(FixationOfPay fixationOfPay) {
		this.fixationOfPay = fixationOfPay;
	}	
	public int getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(int basicPay) {
		this.basicPay = basicPay;
	}	
	public String getMedicalAttention() {
		return medicalAttention;
	}
	public void setMedicalAttention(String medicalAttention) {
		this.medicalAttention = medicalAttention;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public List<BloodGroup> getBloodGroupList() {
		return bloodGroupList;
	}
	public void setBloodGroupList(List<BloodGroup> bloodGroupList) {
		this.bloodGroupList = bloodGroupList;
	}
	public List<SocialCategory> getSocialCategoryList() {
		return socialCategoryList;
	}
	public void setSocialCategoryList(List<SocialCategory> socialCategoryList) {
		this.socialCategoryList = socialCategoryList;
	}
	public List<Country> getCountryList() {
		return countryList;
	}
	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public List<EmployeeType> getEmployeeTypeList() {
		return employeeTypeList;
	}
	public void setEmployeeTypeList(List<EmployeeType> employeeTypeList) {
		this.employeeTypeList = employeeTypeList;
	}
	public List<JobType> getJobTypeList() {
		return jobTypeList;
	}
	public void setJobTypeList(List<JobType> jobTypeList) {
		this.jobTypeList = jobTypeList;
	}
	public List<Department> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}
	public List<FixationOfPay> getFixationOfPayList() {
		return fixationOfPayList;
	}
	public void setFixationOfPayList(List<FixationOfPay> fixationOfPayList) {
		this.fixationOfPayList = fixationOfPayList;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}	
	public List<Qualification> getQualificationList() {
		return qualificationList;
	}
	public void setQualificationList(List<Qualification> qualificationList) {
		this.qualificationList = qualificationList;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}	
	public String getBranchIFSCCode() {
		return branchIFSCCode;
	}
	public void setBranchIFSCCode(String branchIFSCCode) {
		this.branchIFSCCode = branchIFSCCode;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public List<OtherQualification> getOtherQualificationList() {
		return otherQualificationList;
	}
	public void setOtherQualificationList(List<OtherQualification> otherQualificationList) {
		this.otherQualificationList = otherQualificationList;
	}
	public List<Publication> getPublicationList() {
		return publicationList;
	}
	public void setPublicationList(List<Publication> publicationList) {
		this.publicationList = publicationList;
	}
	public List<EmployeeDependent> getEmployeeDependentList() {
		return employeeDependentList;
	}
	public void setEmployeeDependentList(List<EmployeeDependent> employeeDependentList) {
		this.employeeDependentList = employeeDependentList;
	}
	public List<Organization> getOrganizationList() {
		return organizationList;
	}
	public void setOrganizationList(List<Organization> organizationList) {
		this.organizationList = organizationList;
	}
	public String getDateOfRetirement() {
		return dateOfRetirement;
	}
	public void setDateOfRetirement(String dateOfRetirement) {
		this.dateOfRetirement = dateOfRetirement;
	}
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	public List<Designation> getDesignationList() {
		return designationList;
	}
	public void setDesignationList(List<Designation> designationList) {
		this.designationList = designationList;
	}
	public String getQualificationSummary() {
		return qualificationSummary;
	}
	public void setQualificationSummary(String qualificationSummary) {
		this.qualificationSummary = qualificationSummary;
	}
	public SalaryTemplate getSalaryTemplate() {
		return salaryTemplate;
	}
	public void setSalaryTemplate(SalaryTemplate salaryTemplate) {
		this.salaryTemplate = salaryTemplate;
	}
	public List<SalaryTemplate> getSalaryTemplateList() {
		return salaryTemplateList;
	}
	public void setSalaryTemplateList(List<SalaryTemplate> salaryTemplateList) {
		this.salaryTemplateList = salaryTemplateList;
	}
	public String getModeOfRetirement() {
		return modeOfRetirement;
	}
	public void setModeOfRetirement(String modeOfRetirement) {
		this.modeOfRetirement = modeOfRetirement;
	}
	public String getReasonOfRetirement() {
		return reasonOfRetirement;
	}
	public void setReasonOfRetirement(String reasonOfRetirement) {
		this.reasonOfRetirement = reasonOfRetirement;
	}
	public String getActualDateOfRetirement() {
		return actualDateOfRetirement;
	}
	public void setActualDateOfRetirement(String actualDateOfRetirement) {
		this.actualDateOfRetirement = actualDateOfRetirement;
	}
	public String getConfidentialInformation() {
		return confidentialInformation;
	}
	public void setConfidentialInformation(String confidentialInformation) {
		this.confidentialInformation = confidentialInformation;
	}
	public List<NomineeDetails> getNomineeDetailsList() {
		return nomineeDetailsList;
	}
	public void setNomineeDetailsList(List<NomineeDetails> nomineeDetailsList) {
		this.nomineeDetailsList = nomineeDetailsList;
	}
	public List<AwardsAndRecognization> getAwardsAndRecognizationList() {
		return awardsAndRecognizationList;
	}
	public void setAwardsAndRecognizationList(
			List<AwardsAndRecognization> awardsAndRecognizationList) {
		this.awardsAndRecognizationList = awardsAndRecognizationList;
	}
	public List<WorkShopAndTraining> getWorkShopAndTrainingList() {
		return workShopAndTrainingList;
	}
	public void setWorkShopAndTrainingList(
			List<WorkShopAndTraining> workShopAndTrainingList) {
		this.workShopAndTrainingList = workShopAndTrainingList;
	}
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public String getDateOfInterview() {
		return dateOfInterview;
	}
	public void setDateOfInterview(String dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}
	public String getTimeOfInterview() {
		return timeOfInterview;
	}
	public void setTimeOfInterview(String timeOfInterview) {
		this.timeOfInterview = timeOfInterview;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getReferredBy() {
		return referredBy;
	}
	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}
	public List<TeachingLevel> getTeachingLevelList() {
		return teachingLevelList;
	}
	public void setTeachingLevelList(List<TeachingLevel> teachingLevelList) {
		this.teachingLevelList = teachingLevelList;
	}
	public TeachingLevel getTeachingLevel() {
		return teachingLevel;
	}
	public void setTeachingLevel(TeachingLevel teachingLevel) {
		this.teachingLevel = teachingLevel;
	}
	public List<Marks> getMarksList() {
		return marksList;
	}
	public void setMarksList(List<Marks> marksList) {
		this.marksList = marksList;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getInterviewStatus() {
		return interviewStatus;
	}
	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}
	public List<WorkShift> getSelectedWorkShiftList() {
		return selectedWorkShiftList;
	}
	public void setSelectedWorkShiftList(List<WorkShift> selectedWorkShiftList) {
		this.selectedWorkShiftList = selectedWorkShiftList;
	}
	public List<DesignationType> getDesignationTypeList() {
		return designationTypeList;
	}
	public void setDesignationTypeList(List<DesignationType> designationTypeList) {
		this.designationTypeList = designationTypeList;
	}
	public List<SalaryBreakUp> getSalaryBreakUpList() {
		return salaryBreakUpList;
	}
	public void setSalaryBreakUpList(List<SalaryBreakUp> salaryBreakUpList) {
		this.salaryBreakUpList = salaryBreakUpList;
	}
	public List<SalaryBreakUp> getSalaryBreakUpListForShow() {
		return salaryBreakUpListForShow;
	}
	public void setSalaryBreakUpListForShow(
			List<SalaryBreakUp> salaryBreakUpListForShow) {
		this.salaryBreakUpListForShow = salaryBreakUpListForShow;
	}
	public List<ResourceType> getResourceTypeList() {
		return resourceTypeList;
	}
	public void setResourceTypeList(List<ResourceType> resourceTypeList) {
		this.resourceTypeList = resourceTypeList;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public SalaryBreakUp getIndividualDeduction() {
		return individualDeduction;
	}
	public void setIndividualDeduction(SalaryBreakUp individualDeduction) {
		this.individualDeduction = individualDeduction;
	}
	public List<SalaryBreakUp> getExtraEarningList() {
		return extraEarningList;
	}
	public void setExtraEarningList(List<SalaryBreakUp> extraEarningList) {
		this.extraEarningList = extraEarningList;
	}
	public List<Leave> getLeaveList() {
		return leaveList;
	}
	public void setLeaveList(List<Leave> leaveList) {
		this.leaveList = leaveList;
	}
	public String getDaysInMonths() {
		return daysInMonths;
	}
	public void setDaysInMonths(String daysInMonths) {
		this.daysInMonths = daysInMonths;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public ResourceAttendence getResourceAttendance() {
		return resourceAttendance;
	}
	public void setResourceAttendance(ResourceAttendence resourceAttendance) {
		this.resourceAttendance = resourceAttendance;
	}
	public DesignationLevel getDesignationLevel() {
		return designationLevel;
	}
	public void setDesignationLevel(DesignationLevel designationLevel) {
		this.designationLevel = designationLevel;
	}
	public List<Attachment> getPreviousDocumentsAttachmentList() {
		return previousDocumentsAttachmentList;
	}
	public void setPreviousDocumentsAttachmentList(List<Attachment> previousDocumentsAttachmentList) {
		this.previousDocumentsAttachmentList = previousDocumentsAttachmentList;
	}
	public List<Attachment> getPublicationDocAttachmentList() {
		return publicationDocAttachmentList;
	}
	public void setPublicationDocAttachmentList(List<Attachment> publicationDocAttachmentList) {
		this.publicationDocAttachmentList = publicationDocAttachmentList;
	}
	public String getEmpRecId() {
		return empRecId;
	}
	public void setEmpRecId(String empRecId) {
		this.empRecId = empRecId;
	}
	public String getTeachingLevelName() {
		return teachingLevelName;
	}
	public void setTeachingLevelName(String teachingLevelName) {
		this.teachingLevelName = teachingLevelName;
	}
	public String getReportingManger() {
		return reportingManger;
	}
	public void setReportingManger(String reportingManger) {
		this.reportingManger = reportingManger;
	}	
	
	
}
