package com.qts.icam.model.administrator;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Staff")
public class StaffForXml {
	String userId;
	String resourceType;
	String name;
	String fathersName;
	String mothersName;
	String dateOfBirth;
	String prsentAddress;
	String permanentAddress;
	String nationality;
	String email;
	String contactNumber;
	String gender;
	String religion;
	String bloodGroup;
	String dateOfJoining;
	String dateOfRetirement;
	String jobType;
	boolean retired;
	String modeOfRetirement;
	String reasonOfRetirement;
	String teachingLevel;
	boolean leavingCertificateGranted;
	String department;
	String designation;
	
	String jobTypeCode;
	String designationCode;
	
	String designationLevelName;
	String designationLevelCode;
	
	String templateName;
	String templateCode;
	
	SalaryForXml salary;
	List<SubjectsForXml> subjectList;
	
	public String getUserId() {
		return userId;
	}
	@XmlElement(name="UserID")
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getResourceType() {
		return resourceType;
	}
	@XmlElement(name="ResourceType")
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	public String getName() {
		return name;
	}
	@XmlElement(name="Name")
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFathersName() {
		return fathersName;
	}
	@XmlElement(name="FathersName")
	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}
	
	public String getMothersName() {
		return mothersName;
	}
	@XmlElement(name="MothersName")
	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	@XmlElement(name="DateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getPrsentAddress() {
		return prsentAddress;
	}
	@XmlElement(name="LastAddress")
	public void setPrsentAddress(String prsentAddress) {
		this.prsentAddress = prsentAddress;
	}
	
	public String getPermanentAddress() {
		return permanentAddress;
	}
	@XmlElement(name="PermanentAddress")
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	
	public String getNationality() {
		return nationality;
	}
	@XmlElement(name="Nationality")
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getEmail() {
		return email;
	}
	@XmlElement(name="Email")
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	@XmlElement(name="ContactNumber")
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getGender() {
		return gender;
	}
	@XmlElement(name="Gender")
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getReligion() {
		return religion;
	}
	@XmlElement(name="Religion")
	public void setReligion(String religion) {
		this.religion = religion;
	}
	
	public String getBloodGroup() {
		return bloodGroup;
	}
	@XmlElement(name="BloodGroup")
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	@XmlElement(name="DateOfJoining")
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
	public String getDateOfRetirement() {
		return dateOfRetirement;
	}
	@XmlElement(name="DateOfRetirement")
	public void setDateOfRetirement(String dateOfRetirement) {
		this.dateOfRetirement = dateOfRetirement;
	}
	
	public String getJobType() {
		return jobType;
	}
	@XmlElement(name="JobType")
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	public boolean isRetired() {
		return retired;
	}
	@XmlElement(name="Retired")
	public void setRetired(boolean retired) {
		this.retired = retired;
	}
	
	public String getModeOfRetirement() {
		return modeOfRetirement;
	}
	@XmlElement(name="ModeOfRetirement")
	public void setModeOfRetirement(String modeOfRetirement) {
		this.modeOfRetirement = modeOfRetirement;
	}
	
	public String getReasonOfRetirement() {
		return reasonOfRetirement;
	}
	@XmlElement(name="ReasonOfRetirement")
	public void setReasonOfRetirement(String reasonOfRetirement) {
		this.reasonOfRetirement = reasonOfRetirement;
	}
	
	public String getTeachingLevel() {
		return teachingLevel;
	}
	@XmlElement(name="TeachingLevel")
	public void setTeachingLevel(String teachingLevel) {
		this.teachingLevel = teachingLevel;
	}
	
	public boolean isLeavingCertificateGranted() {
		return leavingCertificateGranted;
	}
	@XmlElement(name="LeavingCertificateGranted")
	public void setLeavingCertificateGranted(boolean leavingCertificateGranted) {
		this.leavingCertificateGranted = leavingCertificateGranted;
	}
	
	public String getDepartment() {
		return department;
	}
	@XmlElement(name="Department")
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getDesignation() {
		return designation;
	}
	@XmlElement(name="Designation")
	public void setDesignation(String designation) {
		this.designation = designation;
	}
		
	public String getJobTypeCode() {
		return jobTypeCode;
	}
	public void setJobTypeCode(String jobTypeCode) {
		this.jobTypeCode = jobTypeCode;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	
	public List<SubjectsForXml> getSubjectList() {
		return subjectList;
	}
	@XmlElement(name="SubjectsTaught")
	public void setSubjectList(List<SubjectsForXml> subjectList) {
		this.subjectList = subjectList;
	}
	
	@XmlElement(name="DesignationLevelName")	
	public void setDesignationLevelName(String designationLevelName) {
		this.designationLevelName = designationLevelName;
	}
	public String getDesignationLevelName() {
		return designationLevelName;
	}
	
	
	@XmlElement(name="DesignationLevelCode")	
	public void setDesignationLevelCode(String designationLevelCode) {
		this.designationLevelCode = designationLevelCode;
	}
	public String getDesignationLevelCode() {
		return designationLevelCode;
	}	
	
	@XmlElement(name="TemplateName")
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateName() {
		return templateName;
	}	
	
	@XmlElement(name="TemplateCode")
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public SalaryForXml getSalary() {
		return salary;
	}
	@XmlElement(name="Salary")
	public void setSalary(SalaryForXml salary) {
		this.salary = salary;
	}
	
}
