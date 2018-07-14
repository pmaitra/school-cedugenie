package com.qts.icam.model.report;

import java.util.HashMap;
import java.util.List;

import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.backoffice.StudentSubjectMapping;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.Condemnation;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SchoolDetails;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.inventory.Commodity;

public class Report {
	private int reportId;
	private String objectId;
	private String reportName;
	private String reportCode;
	private String reporteDesc;
	private String updatedBy;	
	private String status;
	
	private String xmlFilePath;
	private String xmlFileName;
	private String xsltFilePath;
	private String xsltFileName;
	private String pdfFilePath;
	private String pdfFileName;
	
	private SchoolDetails schoolDetails;
	private List<Student> studentList;
	private List<Standard> standardList;
	private List<Employee> employeeList;
	private Student student;
	private List<Candidate> candidateList;
	private Venue venue;
	private AcademicYear academicYear;
	private Condemnation condemnation;
	private List<SubjectGroup> subjectGroupList;
	private String startDate;
	private String endDate;
	private List<Commodity> commodityList;
	private Candidate candidate;
	private List<StudentSubjectMapping> studentSubjectMappingList;
	private List<String> examList;	
	private HashMap<Integer,Integer> reportMap ;
	
	//setter & getter
	
	public String getXmlFileName() {
		return xmlFileName;
	}
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}
	public String getXmlFilePath() {
		return xmlFilePath;
	}
	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}
	public String getXsltFilePath() {
		return xsltFilePath;
	}
	public void setXsltFilePath(String xsltFilePath) {
		this.xsltFilePath = xsltFilePath;
	}
	public String getPdfFileName() {
		return pdfFileName;
	}
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	
	
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getReporteDesc() {
		return reporteDesc;
	}
	public void setReporteDesc(String reporteDesc) {
		this.reporteDesc = reporteDesc;
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
	public SchoolDetails getSchoolDetails() {
		return schoolDetails;
	}
	public void setSchoolDetails(SchoolDetails schoolDetails) {
		this.schoolDetails = schoolDetails;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getPdfFilePath() {
		return pdfFilePath;
	}
	public void setPdfFilePath(String pdfFilePath) {
		this.pdfFilePath = pdfFilePath;
	}
	public String getXsltFileName() {
		return xsltFileName;
	}
	public void setXsltFileName(String xsltFileName) {
		this.xsltFileName = xsltFileName;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public List<Standard> getStandardList() {
		return standardList;
	}
	public void setStandardList(List<Standard> standardList) {
		this.standardList = standardList;
	}
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	public List<Candidate> getCandidateList() {
		return candidateList;
	}
	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public Condemnation getCondemnation() {
		return condemnation;
	}
	public void setCondemnation(Condemnation condemnation) {
		this.condemnation = condemnation;
	}
	public List<SubjectGroup> getSubjectGroupList() {
		return subjectGroupList;
	}
	public void setSubjectGroupList(List<SubjectGroup> subjectGroupList) {
		this.subjectGroupList = subjectGroupList;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<Commodity> getCommodityList() {
		return commodityList;
	}
	public void setCommodityList(List<Commodity> commodityList) {
		this.commodityList = commodityList;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public List<StudentSubjectMapping> getStudentSubjectMappingList() {
		return studentSubjectMappingList;
	}
	public void setStudentSubjectMappingList(List<StudentSubjectMapping> studentSubjectMappingList) {
		this.studentSubjectMappingList = studentSubjectMappingList;
	}
	public List<String> getExamList() {
		return examList;
	}
	public void setExamList(List<String> examList) {
		this.examList = examList;
	}
	public HashMap<Integer, Integer> getReportMap() {
		return reportMap;
	}
	public void setReportMap(HashMap<Integer, Integer> reportMap) {
		this.reportMap = reportMap;
	}
	
	
}
