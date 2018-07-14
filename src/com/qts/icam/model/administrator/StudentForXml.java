package com.qts.icam.model.administrator;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Student")
public class StudentForXml {
	String registrationId;
	String name;
	String fathersName;
	String mothersName;
	String admissionDate;
	String admissionCourse;
	String admissionClass;
	String dateOfBirth;
	String prsentAddress;
	String permanentAddress;
	String nationality;
	String email;
	String contactNumber;
	String gender;
	String religion;
	String bloodGroup;
	String leavingCourse;
	String leavingClass;
	String klass;
	String section;
	String stream;
	
	String yearClass;
	String yearCourse;
	String yearSection;
	String yearStream;
	
	String year;
	
	List<ExamForXml> examlist;
	
	
	public String getRegistrationId() {
		return registrationId;
	}
	@XmlElement(name="RegistrationID")
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
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
	
	public String getAdmissionDate() {
		return admissionDate;
	}
	@XmlElement(name="AdmissionDate")
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	
	public String getAdmissionCourse() {
		return admissionCourse;
	}
	@XmlElement(name="AdmissionCourse")
	public void setAdmissionCourse(String admissionCourse) {
		this.admissionCourse = admissionCourse;
	}
	
	public String getAdmissionClass() {
		return admissionClass;
	}
	@XmlElement(name="AdmissionClass")
	public void setAdmissionClass(String admissionClass) {
		this.admissionClass = admissionClass;
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
	@XmlElement(name="PresentAddress")
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
	
	public String getLeavingCourse() {
		return leavingCourse;
	}
	@XmlElement(name="LeavingCourse")
	public void setLeavingCourse(String leavingCourse) {
		this.leavingCourse = leavingCourse;
	}
	
	public String getLeavingClass() {
		return leavingClass;
	}
	@XmlElement(name="LeavingClass")
	public void setLeavingClass(String leavingClass) {
		this.leavingClass = leavingClass;
	}
	
	public String getKlass() {
		return klass;
	}
	@XmlElement(name="Class")
	public void setKlass(String klass) {
		this.klass = klass;
	}
	
	public String getSection() {
		return section;
	}
	@XmlElement(name="Section")
	public void setSection(String section) {
		this.section = section;
	}
	
	public String getStream() {
		return stream;
	}
	@XmlElement(name="Stream")
	public void setStream(String stream) {
		this.stream = stream;
	}
	
	public String getYearClass() {
		return yearClass;
	}
	@XmlElement(name="YearClass")
	public void setYearClass(String yearClass) {
		this.yearClass = yearClass;
	}
	
	public String getYearCourse() {
		return yearCourse;
	}
	@XmlElement(name="YearCourse")
	public void setYearCourse(String yearCourse) {
		this.yearCourse = yearCourse;
	}
	
	public String getYearSection() {
		return yearSection;
	}
	@XmlElement(name="YearSection")
	public void setYearSection(String yearSection) {
		this.yearSection = yearSection;
	}
	
	public String getYearStream() {
		return yearStream;
	}
	@XmlElement(name="YearStream")
	public void setYearStream(String yearStream) {
		this.yearStream = yearStream;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<ExamForXml> getExamlist() {
		return examlist;
	}
	@XmlElement(name="Result")
	public void setExamlist(List<ExamForXml> examlist) {
		this.examlist = examlist;
	}
}
