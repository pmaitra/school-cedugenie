package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.common.MeritListType;

public class Candidate {

	private int candidateId;
	private String objectId;
	private String candidateCode;
	private String candidateDesc;
	private String updatedBy;
	private String candidateName;
	private String status;
	private String admissionFormId;
	
	private String guardianFirstName;
	private String guardianMiddleName;
	private String guardianLastName;
	private String relationshipWithGuardian;

	
	private String fatherOccupation;
	private double fatherAgricultureIncome;
	private double fatherBusinessIncome;
	private double fatherSalaryIncome;
	private double fatherPensionIncome;
	private double fatherOthersIncome;
	private double fatherTotalIncome;
	
	private String motherOccupation;
	private double motherAgricultureIncome;
	private double motherBusinessIncome;
	private double motherSalaryIncome;
	private double motherPensionIncome;
	private double motherOthersIncome;
	private double motherTotalIncome;
	
	private String guardianOccupation;
	private double guardianAgricultureIncome;
	private double guardianBusinessIncome;
	private double guardianSalaryIncome;
	private double guardianPensionIncome;
	private double guardianOthersIncome;
	private double guardianTotalIncome;
	
	private String exServicePersonnel;
	private String service;
	private String serviceNumber;
	private String rank;
	private String dateOfEnrolment;
	private String dateOfDischarge;
	private String recordOfficeName;
	
	private String previousSchoolName;
	private String previousSchoolContact;
	private String previousSchoolWebsite;
	private String previousSchoolStandard;
	
	private String examinationCentre;
	private String examinationMedium;
	private String preferenceSchool1;
	private String preferenceSchool2;
	private String preferenceSchool3;
	
	private String bankDdIpoNo;
	private String bankDate;
	private double bankAmount;
	private String issuingBankBranchName;
	
	private String scheduledCastCommunity;
	private String lastAdmissionFormId;
	
	private String reasonOfRejection;
	private Integer rollNumber;
	
	private Address address;
	private Resource resource;
	private AcademicYear academicYear;
	private Standard standard;
	private SocialCategory socialCategory;
	private MeritListType meritListType;
	
	private List<Standard> standardList;
	private List<SocialCategory> socialCategoryList;
	private List<Country> countryList;
	private List<Attachment> attachmentList;
	private String examCentreAlias;
	
	private String formRollNoId;
	private String toRollNoId;
	private Venue venue;

	
	
	public int getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	public String getCandidateDesc() {
		return candidateDesc;
	}
	public void setCandidateDesc(String candidateDesc) {
		this.candidateDesc = candidateDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastAdmissionFormId() {
		return lastAdmissionFormId;
	}
	public void setLastAdmissionFormId(String lastAdmissionFormId) {
		this.lastAdmissionFormId = lastAdmissionFormId;
	}
	public List<Standard> getStandardList() {
		return standardList;
	}
	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}
	public String getRelationshipWithGuardian() {
		return relationshipWithGuardian;
	}
	public void setRelationshipWithGuardian(String relationshipWithGuardian) {
		this.relationshipWithGuardian = relationshipWithGuardian;
	}
	public String getGuardianOccupation() {
		return guardianOccupation;
	}
	public void setGuardianOccupation(String guardianOccupation) {
		this.guardianOccupation = guardianOccupation;
	}
	public double getFatherAgricultureIncome() {
		return fatherAgricultureIncome;
	}
	public void setFatherAgricultureIncome(double fatherAgricultureIncome) {
		this.fatherAgricultureIncome = fatherAgricultureIncome;
	}
	public double getFatherBusinessIncome() {
		return fatherBusinessIncome;
	}
	public void setFatherBusinessIncome(double fatherBusinessIncome) {
		this.fatherBusinessIncome = fatherBusinessIncome;
	}
	public double getFatherSalaryIncome() {
		return fatherSalaryIncome;
	}
	public void setFatherSalaryIncome(double fatherSalaryIncome) {
		this.fatherSalaryIncome = fatherSalaryIncome;
	}
	public double getFatherPensionIncome() {
		return fatherPensionIncome;
	}
	public void setFatherPensionIncome(double fatherPensionIncome) {
		this.fatherPensionIncome = fatherPensionIncome;
	}
	public double getFatherOthersIncome() {
		return fatherOthersIncome;
	}
	public void setFatherOthersIncome(double fatherOthersIncome) {
		this.fatherOthersIncome = fatherOthersIncome;
	}
	public double getFatherTotalIncome() {
		return fatherTotalIncome;
	}
	public void setFatherTotalIncome(double fatherTotalIncome) {
		this.fatherTotalIncome = fatherTotalIncome;
	}
	public double getMotherAgricultureIncome() {
		return motherAgricultureIncome;
	}
	public void setMotherAgricultureIncome(double motherAgricultureIncome) {
		this.motherAgricultureIncome = motherAgricultureIncome;
	}
	public double getMotherBusinessIncome() {
		return motherBusinessIncome;
	}
	public void setMotherBusinessIncome(double motherBusinessIncome) {
		this.motherBusinessIncome = motherBusinessIncome;
	}
	public double getMotherSalaryIncome() {
		return motherSalaryIncome;
	}
	public void setMotherSalaryIncome(double motherSalaryIncome) {
		this.motherSalaryIncome = motherSalaryIncome;
	}
	public double getMotherPensionIncome() {
		return motherPensionIncome;
	}
	public void setMotherPensionIncome(double motherPensionIncome) {
		this.motherPensionIncome = motherPensionIncome;
	}
	public double getMotherOthersIncome() {
		return motherOthersIncome;
	}
	public void setMotherOthersIncome(double motherOthersIncome) {
		this.motherOthersIncome = motherOthersIncome;
	}
	public double getMotherTotalIncome() {
		return motherTotalIncome;
	}
	public void setMotherTotalIncome(double motherTotalIncome) {
		this.motherTotalIncome = motherTotalIncome;
	}
	public double getGuardianAgricultureIncome() {
		return guardianAgricultureIncome;
	}
	public void setGuardianAgricultureIncome(double guardianAgricultureIncome) {
		this.guardianAgricultureIncome = guardianAgricultureIncome;
	}
	public double getGuardianBusinessIncome() {
		return guardianBusinessIncome;
	}
	public void setGuardianBusinessIncome(double guardianBusinessIncome) {
		this.guardianBusinessIncome = guardianBusinessIncome;
	}
	public double getGuardianSalaryIncome() {
		return guardianSalaryIncome;
	}
	public void setGuardianSalaryIncome(double guardianSalaryIncome) {
		this.guardianSalaryIncome = guardianSalaryIncome;
	}
	public double getGuardianPensionIncome() {
		return guardianPensionIncome;
	}
	public void setGuardianPensionIncome(double guardianPensionIncome) {
		this.guardianPensionIncome = guardianPensionIncome;
	}
	public double getGuardianOthersIncome() {
		return guardianOthersIncome;
	}
	public void setGuardianOthersIncome(double guardianOthersIncome) {
		this.guardianOthersIncome = guardianOthersIncome;
	}
	public double getGuardianTotalIncome() {
		return guardianTotalIncome;
	}
	public void setGuardianTotalIncome(double guardianTotalIncome) {
		this.guardianTotalIncome = guardianTotalIncome;
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
	public String getDateOfEnrolment() {
		return dateOfEnrolment;
	}
	public void setDateOfEnrolment(String dateOfEnrolment) {
		this.dateOfEnrolment = dateOfEnrolment;
	}
	public String getDateOfDischarge() {
		return dateOfDischarge;
	}
	public void setDateOfDischarge(String dateOfDischarge) {
		this.dateOfDischarge = dateOfDischarge;
	}
	public String getRecordOfficeName() {
		return recordOfficeName;
	}
	public void setRecordOfficeName(String recordOfficeName) {
		this.recordOfficeName = recordOfficeName;
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
	public String getPreviousSchoolName() {
		return previousSchoolName;
	}
	public void setPreviousSchoolName(String previousSchoolName) {
		this.previousSchoolName = previousSchoolName;
	}
	public String getPreviousSchoolContact() {
		return previousSchoolContact;
	}
	public void setPreviousSchoolContact(String previousSchoolContact) {
		this.previousSchoolContact = previousSchoolContact;
	}
	public String getPreviousSchoolWebsite() {
		return previousSchoolWebsite;
	}
	public void setPreviousSchoolWebsite(String previousSchoolWebsite) {
		this.previousSchoolWebsite = previousSchoolWebsite;
	}
	public String getPreviousSchoolStandard() {
		return previousSchoolStandard;
	}
	public void setPreviousSchoolStandard(String previousSchoolStandard) {
		this.previousSchoolStandard = previousSchoolStandard;
	}
	public String getExaminationCentre() {
		return examinationCentre;
	}
	public void setExaminationCentre(String examinationCentre) {
		this.examinationCentre = examinationCentre;
	}
	public String getExaminationMedium() {
		return examinationMedium;
	}
	public void setExaminationMedium(String examinationMedium) {
		this.examinationMedium = examinationMedium;
	}
	public String getPreferenceSchool1() {
		return preferenceSchool1;
	}
	public void setPreferenceSchool1(String preferenceSchool1) {
		this.preferenceSchool1 = preferenceSchool1;
	}
	public String getPreferenceSchool2() {
		return preferenceSchool2;
	}
	public void setPreferenceSchool2(String preferenceSchool2) {
		this.preferenceSchool2 = preferenceSchool2;
	}
	public String getPreferenceSchool3() {
		return preferenceSchool3;
	}
	public void setPreferenceSchool3(String preferenceSchool3) {
		this.preferenceSchool3 = preferenceSchool3;
	}
	public String getBankDdIpoNo() {
		return bankDdIpoNo;
	}
	public void setBankDdIpoNo(String bankDdIpoNo) {
		this.bankDdIpoNo = bankDdIpoNo;
	}
	public String getBankDate() {
		return bankDate;
	}
	public void setBankDate(String bankDate) {
		this.bankDate = bankDate;
	}
	public double getBankAmount() {
		return bankAmount;
	}
	public void setBankAmount(double bankAmount) {
		this.bankAmount = bankAmount;
	}
	public String getIssuingBankBranchName() {
		return issuingBankBranchName;
	}
	public void setIssuingBankBranchName(String issuingBankBranchName) {
		this.issuingBankBranchName = issuingBankBranchName;
	}
	public String getScheduledCastCommunity() {
		return scheduledCastCommunity;
	}
	public void setScheduledCastCommunity(String scheduledCastCommunity) {
		this.scheduledCastCommunity = scheduledCastCommunity;
	}
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
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
	public String getGuardianFirstName() {
		return guardianFirstName;
	}
	public void setGuardianFirstName(String guardianFirstName) {
		this.guardianFirstName = guardianFirstName;
	}
	public String getGuardianMiddleName() {
		return guardianMiddleName;
	}
	public void setGuardianMiddleName(String guardianMiddleName) {
		this.guardianMiddleName = guardianMiddleName;
	}
	public String getGuardianLastName() {
		return guardianLastName;
	}
	public void setGuardianLastName(String guardianLastName) {
		this.guardianLastName = guardianLastName;
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
	public Standard getStandard() {
		return standard;
	}
	public void setStandard(Standard standard) {
		this.standard = standard;
	}
	public SocialCategory getSocialCategory() {
		return socialCategory;
	}
	public void setSocialCategory(SocialCategory socialCategory) {
		this.socialCategory = socialCategory;
	}
	public String getAdmissionFormId() {
		return admissionFormId;
	}
	public void setAdmissionFormId(String admissionFormId) {
		this.admissionFormId = admissionFormId;
	}
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public MeritListType getMeritListType() {
		return meritListType;
	}
	public void setMeritListType(MeritListType meritListType) {
		this.meritListType = meritListType;
	}
	public String getReasonOfRejection() {
		return reasonOfRejection;
	}
	public void setReasonOfRejection(String reasonOfRejection) {
		this.reasonOfRejection = reasonOfRejection;
	}
	public Integer getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(Integer rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getExamCentreAlias() {
		return examCentreAlias;
	}
	public void setExamCentreAlias(String examCentreAlias) {
		this.examCentreAlias = examCentreAlias;
	}
	public String getFormRollNoId() {
		return formRollNoId;
	}
	public void setFormRollNoId(String formRollNoId) {
		this.formRollNoId = formRollNoId;
	}
	public String getToRollNoId() {
		return toRollNoId;
	}
	public void setToRollNoId(String toRollNoId) {
		this.toRollNoId = toRollNoId;
	}
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	
	
	
}
