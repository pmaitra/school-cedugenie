package com.qts.icam.dao.erp;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.Staff;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.DesignationLevel;
import com.qts.icam.model.erp.DesignationType;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.EmployeeType;
import com.qts.icam.model.erp.FixationOfPay;
import com.qts.icam.model.erp.FixationOfPayDetails;
import com.qts.icam.model.erp.IncomeTaxParameters;
import com.qts.icam.model.erp.IncomeTaxSlabDetails;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.erp.MiscellaneousTax;
import com.qts.icam.model.erp.SalaryBreakUp;
import com.qts.icam.model.erp.SalaryTemplate;

public interface ERPDao {

	public List<Department> getAllDepartment();
	
	public String addDepartment(Department department);

	public String editDepartment(Department department);

	public List<Designation> getAllDesignation();

	public String addDesignation(Designation designation);

	public String editDesignation(Designation designation);

	public List<ResourceType> getAllResourceType();

	public String addEmployeeType(EmployeeType employeeType);

	public String editEmployeeType(EmployeeType employeeType);

	public List<JobType> getAllJobType();

	public String addJobType(JobType jobType);

	public String editJobType(JobType jobType);		

	public String submitEmployeeDetails(Employee employee, Ledger ledger);
	
	public List<Employee> getStaffList(Map<String, Object> parameters);	

	public Employee getEmployeeDetails(Employee employee);

	public String updateEmployeeBasicDetails(Employee employee);

	public String updateEmployeePersonalDetails(Employee employee);

	public String updateEmployeeQualificationDetails(Employee employee);

	public String updateEmployeeBankDetails(Employee employee);

	public String updateEmployeeDependentsDetails(Employee employee);

	public String updateEmployeeAddressDetails(Employee employee);

	public String serverSideValidationOfUserId(String strUserId);
	
	public String serverSideValidationOfEmployeeCode(String employeeCode);

	public String updateEmployeePublicationDetails(Employee employee);

	public String updateEmployeeWorkingDetails(Employee employee);
	
	public String updateEmployeeImageToAttachment(Employee employee);
	
	//lEAVE
	
		public List<Leave> getleaveTypeList();

		public List<Leave> getResourceLeaveDetails(String userId);

		public String startLeaveRequest(Task task);

		public Leave getRemainingLeave(String leaveType, String userId);

		public Task getWorkingDaysForLeaveRequest(Task task);

		public Integer getRoleOfUser(Task task);

		public List<Leave> getPendingTaskList();

		public List<Task> searchOnListPendingTask(Map<String, Object> parameters);

		public Leave getPendingLeaveDetails(Leave leave);

		public String insertLeaveResponse(Task task);

		public List<Task> getTaskHistoryList(Task task);

		public List<Task> listPersonalTaskHistory(Task task);

		public Employee getTeacherDetails(String strStaffUserId);

		public String submitStaffRetirement(Employee employee);

		public List<Employee> getRetiredStaffList();		

		public List<Designation> getDesignationForResourceType(String resourceType);
		
		public String updateEmployeeNomineeDetails(Employee employee);

		public String updateEmployeeWorkShopAndTrainingDetails(Employee employee);

		public String updateEmployeeAwardsAndRecognizationDetails(Employee employee);

		public String updateEmployeeConfidentialDetails(Employee employee);

		public String insertManualLeaveResponse(Task task);

		public List<Task> listPersonalLeaveHistory(Task task);

		public List<DesignationType> getAllDesignationType();

		public String addDesignationType(DesignationType designationType);

		public String editDesignationType(DesignationType desginationType);

		public List<DesignationLevel> getAllDesignationLevel();

		public String addDesignationLevel(DesignationLevel designationLevel);

		public String editDesignationLevel(DesignationLevel designationLevel);

		public String addDesignationLevelMapping(List<Designation> designationList);

		public List<Designation> getAllMappedDesignation();

		public String updateDesignationLevelMapping(Designation designation);//Modified by naimisha 16082017

		public List<SalaryBreakUp> getSalaryBreakUp();

		public List<SalaryBreakUp> createSalaryBreakUp(SalaryBreakUp salaryBreakUp);

		public List<SalaryBreakUp> getSalaryBreakUpList();

		public List<SalaryTemplate> salaryTemplateList();

		public List<SalaryBreakUp> getSalaryBreakUpForSlab();

		public List<SalaryBreakUp> getSalaryBreakUpForNonSlab();

		public String getSubmittedSlabTypeForMiscTax(String taxTypeCode);		

		public List<SalaryBreakUp> getSalaryBreakUpForMiscTaxSlab();

		public List<MiscellaneousTax> getMiscTaxSlabForEdit(String taxTypeCode);

		public String updateMiscTaxSlab(List<MiscellaneousTax> miscTaxSlabList,	MiscellaneousTax miscTax);

		public String getSubmittedEmployerContribution(String taxTypeCode);

		public String submitEmployerContribution(List<MiscellaneousTax> miscTaxSlabList, MiscellaneousTax miscTax);

		public IncomeTaxSlabDetails viewParameterOfIncomeTaxSalarySlab(IncomeTaxSlabDetails incomeTaxSlabDetails);

		public String editIncomeTaxSalarySlab(IncomeTaxSlabDetails itIncomeTaxSlabDetails);

		public String getDesignationBasedOnDesignationType(String designationTypeCode);

		public String getLevelBasedOnDesignation(String designationCode);

		public String checkSalaryTemplateName(String getsalaryTemplateName);

		public SalaryTemplate getSalaryTemplateDetails(String templateCode);

		public String updateSalaryTemplate(SalaryTemplate salaryTemplate);

		public String submitIncomeTaxSalarySlab(IncomeTaxSlabDetails itIncomeTaxSlabDetails);

		public Employee getStaffDetailsForPromotionAndSalaryMapping(Resource resource);

		public String submitSalaryTemplate(SalaryTemplate salaryTemplate);

		public String submitMiscTaxSlab(List<MiscellaneousTax> miscTaxSlabList,	MiscellaneousTax miscTax);

		public String setTeacherInterviewSchedule(Employee employee);

		public List<Employee> getTeacherInterviewScheduleList();

		public Employee getTeacherInterviewScheduleDetails(Employee employee);

		public List<Employee> updateTeacherInterviewSchedule(Employee employee);

		public List<Employee> getCandidateId();

		public String getCandidateName(Employee employee);

		public String submitTeacherInterviewDetails(Employee employee);

		public List<Employee> getTeacherInterviewList();

		public Resource getCandidateDetails(String strStaffCode);

		public Employee getTeacherInterviewDetails(Employee employee);

		public List<String> getStaffUserIdList(String strQuery);

		public String getReportingPerson(String designation);

		public List<SalaryTemplate> getSalaryTemp(Designation designation);

		public List<SalaryBreakUp> getSalaryBreakUpForTemplate(SalaryTemplate salaryTemplate);

		public Employee createErpSalaryMapping(Employee employee);

		public List<SalaryBreakUp> getTaxDeductionAmount(String totalGross, String totalNet, Employee employee);

		public List<IncomeTaxParameters> getIncomeTaxParameter();

		public IncomeTaxSlabDetails getSlabCalculationParameter(String hidVal);

		public Employee getStaffSalaryDetails(Resource resource);

		public String saveStaffSalaryDetails(Employee staff);

		public List<DesignationLevel> getDesignationLevelListForDesignation(String designationName);

/*Done by naimisha for erp*/
		
		public Staff getTeacherSubjectForMapping();

		public Staff saveTeacherSubjectMapping(Staff staff);

		public String getTeacherSubjectMappping(String strStaffUserId);

		public String submitTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping);

		public List<Resource> getTeachersFromTeacherSubjectMappingList();

		public Staff getTeacherSubjectMappingForEdit(Staff staff);

		public String submitStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping);

		public List<TeacherSubjectMapping> getTeachersFromStandardTeacherSubjectMappingList();

		public String editStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping);

		public String deleteStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping);

		public List<EmployeeType> getAllEmployeeType();
		
		public String updateHodForDepartment(Department department);
		
		public String getAllUserIdList(String parent);

		public List<Department> getMapDepartmentWithResourceType();
				
		public String saveUpdateDetails(Department department);
		
		//Added By Naimisha 31/07/2017

		public List<Resource> getResourceDetailsForSalary(Resource resource);

		public String saveStaffSalaryDetailsNew(List<Resource> resourceList);
		
		//Added By Naimisha 09082017

		public List<Resource> getPaymentStatusForEmployeeForAYearAndMonth(Resource resource);

		
		//Added By Naimisha 11082017

		public SalaryTemplate getTemplateForDesignationTypeAndDesignationAndLevel(Resource resource);
		/*modified by ranita.sur on 20092017 for getting unmapped designation*/
		public List<Designation> getAllUnMappedDesignation();
		
		public String getEventEmployeeName(String eventIncharge);
}
