package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.DesignationType;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.Leave;
//import com.qts.icam.model.erp.Position;
import com.qts.icam.model.erp.Publication;
import com.qts.icam.model.erp.Qualification;
//import com.qts.icam.model.erp.Retirement;
import com.qts.icam.model.erp.SalaryBreakUp;
import com.qts.icam.model.erp.SalaryTemplate;



public class Staff {
	private int staffId;
	private String staffObjectId;
	private String staffCode;
	private String staffDesc;
	private String updatedBy;
	private String staffName;
	private Resource resource;
	private List<Resource> resourceList;
	//private List<UserResourceType> userResourceTypeList;
	private String dateOfJoin;
	private List<JobType> jobTypeList;
	private List<WorkShift> workShiftList;
	private List<Department> departmentList;
	private String jobRole;
	private int totWorkExpYear;
	private int totWorkExpMonth;
	private String costToCompany;
	//private List<Position> positionList;
	private List<TeachingLevel> teachingLevelList;
	private TeachingLevel teachingLevel;
	private boolean bedQualification;
	private boolean dedQualification;
	private boolean ttcQualification;
	private List<Subject> subjectList;
	private List<SalaryBreakUp>salaryBreakUpList;
	private List<Qualification> qualificationList;
	private Qualification qualification;
	//private Retirement retirement;
	private String status;
	private String interviewStatus;
	private List<Marks> marksList;
	//used for mapping
	private String position;
	private String jobType;
	private String subject;
	private double marks;
	private String comment;
	private String dateOfInterview;
	private String timeOfInterview;
	private String roomNumber;
	private String venue;
	private List<Publication> publicationList;
	private String experience;
	private List<Class> classList;
	private String designation;
	private List<SubjectType> subjectTypeList;
	//---setter&getter
	private List<Designation> designationList;
	private List<DesignationType> designationTypeList;	
	private List<WorkShift> selectedWorkShiftList;		
	private List<Task> taskList;
	private List<Leave> leaveList;
	private String referredBy;
	
	
	private String absentDays;
	private String daysInMonths;
	private StudentAttendance studentAttendance;
	//----------------new added for subject staff mapping---------------------------------------------------------
	private SubjectList subjectListObj;
	private String level;
	
	private double grossAmount;
	private double netAmount;
	private String paymentDate;	
	
	private List<SalaryBreakUp> salaryBreakUpListForShow;
	private Leave leave;	
	private String paymentMode;
		
	private SalaryTemplate salaryTemplate;
	private List<SalaryBreakUp> extraEarningList;
	private SalaryBreakUp individualDeduction;
		
	
	
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
	public SalaryTemplate getSalaryTemplate() {
		return salaryTemplate;
	}
	public void setSalaryTemplate(SalaryTemplate salaryTemplate) {
		this.salaryTemplate = salaryTemplate;
	}
	public Leave getLeave() {
		return leave;
	}
	public void setLeave(Leave leave) {
		this.leave = leave;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public List<SalaryBreakUp> getSalaryBreakUpListForShow() {
		return salaryBreakUpListForShow;
	}
	public void setSalaryBreakUpListForShow(
			List<SalaryBreakUp> salaryBreakUpListForShow) {
		this.salaryBreakUpListForShow = salaryBreakUpListForShow;
	}
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
	public SubjectList getSubjectListObj() {
		return subjectListObj;
	}
	public void setSubjectListObj(SubjectList subjectListObj) {
		this.subjectListObj = subjectListObj;
	}
	public List<Designation> getDesignationList() {
		return designationList;
	}
	public void setDesignationList(List<Designation> designationList) {
		this.designationList = designationList;
	}
	public List<DesignationType> getDesignationTypeList() {
		return designationTypeList;
	}
	public void setDesignationTypeList(List<DesignationType> designationTypeList) {
		this.designationTypeList = designationTypeList;
	}
	public List<WorkShift> getSelectedWorkShiftList() {
		return selectedWorkShiftList;
	}
	public void setSelectedWorkShiftList(List<WorkShift> selectedWorkShiftList) {
		this.selectedWorkShiftList = selectedWorkShiftList;
	}
	public String getStaffObjectId() {
		return staffObjectId;
	}
	public TeachingLevel getTeachingLevel() {
		return teachingLevel;
	}
	public void setTeachingLevel(TeachingLevel teachingLevel) {
		this.teachingLevel = teachingLevel;
	}
	public String getInterviewStatus() {
		return interviewStatus;
	}
	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}
	public void setStaffObjectId(String staffObjectId) {
		this.staffObjectId = staffObjectId;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getStaffDesc() {
		return staffDesc;
	}
	public void setStaffDesc(String staffDesc) {
		this.staffDesc = staffDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public String getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(String dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	public List<Department> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public int getTotWorkExpYear() {
		return totWorkExpYear;
	}
	public void setTotWorkExpYear(int totWorkExpYear) {
		this.totWorkExpYear = totWorkExpYear;
	}
	public int getTotWorkExpMonth() {
		return totWorkExpMonth;
	}
	public void setTotWorkExpMonth(int totWorkExpMonth) {
		this.totWorkExpMonth = totWorkExpMonth;
	}
	public String getCostToCompany() {
		return costToCompany;
	}
	public void setCostToCompany(String costToCompany) {
		this.costToCompany = costToCompany;
	}
	/*public List<Position> getPositionList() {
		return positionList;
	}
	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}*/
	public boolean isBedQualification() {
		return bedQualification;
	}
	public void setBedQualification(boolean bedQualification) {
		this.bedQualification = bedQualification;
	}
	public boolean isDedQualification() {
		return dedQualification;
	}
	public void setDedQualification(boolean dedQualification) {
		this.dedQualification = dedQualification;
	}
	public boolean isTtcQualification() {
		return ttcQualification;
	}
	public void setTtcQualification(boolean ttcQualification) {
		this.ttcQualification = ttcQualification;
	}
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	public List<SalaryBreakUp> getSalaryBreakUpList() {
		return salaryBreakUpList;
	}
	public void setSalaryBreakUpList(List<SalaryBreakUp> salaryBreakUpList) {
		this.salaryBreakUpList = salaryBreakUpList;
	}
	public List<Qualification> getQualificationList() {
		return qualificationList;
	}
	public void setQualificationList(List<Qualification> qualificationList) {
		this.qualificationList = qualificationList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public List<JobType> getJobTypeList() {
		return jobTypeList;
	}
	public void setJobTypeList(List<JobType> jobTypeList) {
		this.jobTypeList = jobTypeList;
	}
	public List<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public List<Publication> getPublicationList() {
		return publicationList;
	}
	public void setPublicationList(List<Publication> publicationList) {
		this.publicationList = publicationList;
	}
	/*public Retirement getRetirement() {
		return retirement;
	}
	public void setRetirement(Retirement retirement) {
		this.retirement = retirement;
	}*/
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<TeachingLevel> getTeachingLevelList() {
		return teachingLevelList;
	}
	public void setTeachingLevelList(List<TeachingLevel> teachingLevelList) {
		this.teachingLevelList = teachingLevelList;
	}
	public List<Class> getClassList() {
		return classList;
	}
	public void setClassList(List<Class> classList) {
		this.classList = classList;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public List<WorkShift> getWorkShiftList() {
		return workShiftList;
	}
	public void setWorkShiftList(List<WorkShift> workShiftList) {
		this.workShiftList = workShiftList;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public List<SubjectType> getSubjectTypeList() {
		return subjectTypeList;
	}
	public void setSubjectTypeList(List<SubjectType> subjectTypeList) {
		this.subjectTypeList = subjectTypeList;
	}
	public List<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	public List<Leave> getLeaveList() {
		return leaveList;
	}
	public void setLeaveList(List<Leave> leaveList) {
		this.leaveList = leaveList;
	}
	public String getAbsentDays() {
		return absentDays;
	}
	public void setAbsentDays(String absentDays) {
		this.absentDays = absentDays;
	}
	public String getDaysInMonths() {
		return daysInMonths;
	}
	public void setDaysInMonths(String daysInMonths) {
		this.daysInMonths = daysInMonths;
	}
	public StudentAttendance getStudentAttendance() {
		return studentAttendance;
	}
	public void setStudentAttendance(StudentAttendance studentAttendance) {
		this.studentAttendance = studentAttendance;
	}
	public String getReferredBy() {
		return referredBy;
	}
	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	/*public List<UserResourceType> getUserResourceTypeList() {
		return userResourceTypeList;
	}
	public void setUserResourceTypeList(List<UserResourceType> userResourceTypeList) {
		this.userResourceTypeList = userResourceTypeList;
	}
	*/
	
	
	
}
