package com.qts.icam.aspect.logging.erp;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.SalaryTemplate;
import com.qts.icam.service.common.CommonService;

@Aspect
@Component
public class ErpLoggingNotificationAspect {
	
	@Autowired
	CommonService commonService;
	private final static Logger logger = Logger.getLogger(ErpLoggingNotificationAspect.class);
	
	/**
	 * This method is used for log into database, the acknowledgment of inserting department 
	 *  along with schedule date and time.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.addDepartment(..))"+"&& args(department)",returning= "result")
	public void addDepartment(JoinPoint joinPoint,Department department, Object result) {
		try{			
			String functionalityName="Add Department";
			String subjectName="Added New Department";
			String moduleName="ERP";
			String loggingDesc="New department added - "+department.getDepartmentName();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(department.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in addDepartment(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * This method is used for log into database, the acknowledgment of updated department name
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.editDepartment(..))"+"&& args(department)",returning= "result")
	public void editDepartment(JoinPoint joinPoint,Department department, Object result) {
		try{			
			String functionalityName="Add Department";
			String subjectName="Updated Department";
			String moduleName="ERP";
			String loggingDesc="Department Name Updated - "+department.getDepartmentName();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(department.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in editDepartment(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	

	
	/**
	 * This method is used for log into database, the acknowledgment of added Designation name
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.addDesignation(..))"+"&& args(designation)",returning= "result")
	public void addDesignation(JoinPoint joinPoint,Designation designation, Object result) {
		try{			
			String functionalityName="Add Designation";
			String subjectName="Added new designation";
			String moduleName="ERP";
			String loggingDesc="New designation added - "+designation.getDesignationName();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(designation.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in addDesignation(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of updated Designation name
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.editDesignation(..))"+"&& args(designation)",returning= "result")
	public void editDesignation(JoinPoint joinPoint,Designation designation, Object result) {
		try{			
			String functionalityName="Add Designation";
			String subjectName="Updated Designation Name";
			String moduleName="ERP";
			String loggingDesc="Designation name updated - "+designation.getDesignationName();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(designation.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in editDesignation(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * This method is used for log into database, the acknowledgment of added new jobType
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.addJobType(..))"+"&& args(jobType)",returning= "result")
	public void addJobType(JoinPoint joinPoint,JobType jobType, Object result) {
		try{			
			String functionalityName="Add Job Type";
			String subjectName="New Job Type Added";
			String moduleName="ERP";
			String loggingDesc="New Job Type Added - "+jobType.getJobTypeName();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(jobType.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in addJobType(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	

	/**
	 * This method is used for log into database, the acknowledgment of updated jobType
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.editJobType(..))"+"&& args(jobType)",returning= "result")
	public void editJobType(JoinPoint joinPoint,JobType jobType, Object result) {
		try{			
			String functionalityName="Add Job Type";
			String subjectName="Updated Job Type";
			String moduleName="ERP";
			String loggingDesc="Job Type name updated - "+jobType.getJobTypeName();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(jobType.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in editJobType(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * This method is used for log into database, the acknowledgment of added fixation of pay
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.addFixationOfPay(..))"+"&& args(salaryTemplate)",returning= "result")
	public void addFixationOfPay(JoinPoint joinPoint,SalaryTemplate salaryTemplate, Object result) {
		try{			
			String functionalityName="Standard Pay Scales";
			String subjectName="Added Fixation Of Pay";
			String moduleName="ERP";
	//		String loggingDesc="Fixation of pay added - name: "+salaryTemplate.getFixationOfPay().getFixationOfPayName();
	//		loggingDesc=loggingDesc+" Pay Band Start Range-End Range: "+salaryTemplate.getFixationOfPay().getFixationOfPayStartRange()+"-"+salaryTemplate.getFixationOfPay().getFixationOfPayEndRange();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
	//		loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(salaryTemplate.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in addFixationOfPay(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
//	/**
//	 * This method is used for log into database, the acknowledgment of added Standard Pay Scales
//	 *  
//	 */
//	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.addStandardPayScales(..))"+"&& args(salaryTemplate)",returning= "result")
//	public void addStandardPayScales(JoinPoint joinPoint,SalaryTemplate salaryTemplate, Object result) {
//		try{			
//			String functionalityName="Standard Pay Scales";
//			String subjectName="Pay Scales Added";
//			String moduleName="ERP";
//			String loggingDesc="Standard pay scales added for: "+salaryTemplate.getStandardPayScalesList().size() +" pay bands";
//			LoggingAspect loggingAspect = new LoggingAspect();
//			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
//			loggingAspect.setMethodName(joinPoint.getSignature().getName());
//			Module module = new Module();
//			module.setModuleName(moduleName);
//			loggingAspect.setModule(module);
//			Functionality functionality=new Functionality();
//			functionality.setFunctionalityName(functionalityName);
//			loggingAspect.setFunctionality(functionality);
//			loggingAspect.setLoggingDesc(loggingDesc);
//			loggingAspect.setLoggingSubject(subjectName);
//			loggingAspect.setUpdatedBy(salaryTemplate.getUpdatedBy());	
//			if(result!=null && "Success".equals(result.toString())){
//				commonService.createLoggingByAspect(loggingAspect);
//				commonService.createNotificationByAspect(loggingAspect);
//				}
//		}catch(Exception e){
//			logger.error("Exception in addStandardPayScales(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
//		}
//	}
	
	/**
	 * This method is used for log into database, the acknowledgment of added Salary Template
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.submitSalaryTemplate(..))"+"&& args(salaryTemplate)",returning= "result")
	public void submitSalaryTemplate(JoinPoint joinPoint,SalaryTemplate salaryTemplate, Object result) {
		try{			
			String functionalityName="Salary Template";
			String subjectName="New Salary Template Added";
			String moduleName="ERP";
//			String loggingDesc="Salary template added for "+salaryTemplate.getSalaryTemplateName()+" of "+salaryTemplate.getSalaryTemplateType();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
	//		loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(salaryTemplate.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in submitSalaryTemplate(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of added Salary Template
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.editSalaryTemplate(..))"+"&& args(salaryTemplate)",returning= "result")
	public void editSalaryTemplate(JoinPoint joinPoint,SalaryTemplate salaryTemplate, Object result) {
		try{			
			String functionalityName="Salary Template";
			String subjectName="Salary Template Updated";
			String moduleName="ERP";
	//		String loggingDesc="Salary template Updated for "+salaryTemplate.getSalaryTemplateName()+" of "+salaryTemplate.getSalaryTemplateType();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
	//		loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(salaryTemplate.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in editSalaryTemplate(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of added employee details
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.submitEmployeeDetails(..))"+"&& args(employee)",returning= "result")
	public void submitEmployeeDetails(JoinPoint joinPoint,Employee employee, Object result) {
		try{			
			String functionalityName="Teacher List";
			String subjectName="New Employee Added";
			String moduleName="ERP";
			String loggingDesc="New Employee added, Name- "+employee.getResource().getFirstName()+" "+employee.getResource().getMiddleName()+" "+employee.getResource().getLastName();
			loggingDesc=loggingDesc+" Employee Type- "+employee.getEmployeeType().getEmployeeTypeName();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(employee.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in submitEmployeeDetails(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of updated employee basic details
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.updateEmployeeBasicDetails(..))"+"&& args(employee)",returning= "result")
	public void updateEmployeeBasicDetails(JoinPoint joinPoint,Employee employee, Object result) {
		try{			
			String functionalityName="Teacher List";
			String subjectName="Updated Employee Details ";
			String moduleName="ERP";
			String loggingDesc="Employee basic details updated for user id- "+employee.getResource().getUserId();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(employee.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in updateEmployeeBasicDetails(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of updated employee personal details
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.updateEmployeePersonalDetails(..))"+"&& args(employee)",returning= "result")
	public void updateEmployeePersonalDetails(JoinPoint joinPoint,Employee employee, Object result) {
		try{			
			String functionalityName="Teacher List";
			String subjectName="Updated Employee Details ";
			String moduleName="ERP";
			String loggingDesc="Employee personal details updated for user id- "+employee.getResource().getUserId();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(employee.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in updateEmployeePersonalDetails(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of updated employee qualification details
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.updateEmployeeQualificationDetails(..))"+"&& args(employee)",returning= "result")
	public void updateEmployeeQualificationDetails(JoinPoint joinPoint,Employee employee, Object result) {
		try{			
			String functionalityName="Teacher List";
			String subjectName="Updated Employee Details ";
			String moduleName="ERP";
			String loggingDesc="Employee qualification details updated for user id- "+employee.getResource().getUserId();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(employee.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in updateEmployeeQualificationDetails(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of updated employee bank details
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.updateEmployeeBankDetails(..))"+"&& args(employee)",returning= "result")
	public void updateEmployeeBankDetails(JoinPoint joinPoint,Employee employee, Object result) {
		try{			
			String functionalityName="Teacher List";
			String subjectName="Updated Employee Details ";
			String moduleName="ERP";
			String loggingDesc="Employee bank details updated for user id- "+employee.getResource().getUserId();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(employee.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in updateEmployeeBankDetails(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of updated employee dependents details
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.updateEmployeeDependentsDetails(..))"+"&& args(employee)",returning= "result")
	public void updateEmployeeDependentsDetails(JoinPoint joinPoint,Employee employee, Object result) {
		try{			
			String functionalityName="Teacher List";
			String subjectName="Updated Employee Details ";
			String moduleName="ERP";
			String loggingDesc="Employee dependents details updated for user id- "+employee.getResource().getUserId();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(employee.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in updateEmployeeDependentsDetails(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of updated employee address details
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.updateEmployeeAddressDetails(..))"+"&& args(employee)",returning= "result")
	public void updateEmployeeAddressDetails(JoinPoint joinPoint,Employee employee, Object result) {
		try{			
			String functionalityName="Teacher List";
			String subjectName="Updated Employee Details ";
			String moduleName="ERP";
			String loggingDesc="Employee address details updated for user id- "+employee.getResource().getUserId();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(employee.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in updateEmployeeAddressDetails(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of updated employee publication details
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.updateEmployeePublicationDetails(..))"+"&& args(employee)",returning= "result")
	public void updateEmployeePublicationDetails(JoinPoint joinPoint,Employee employee, Object result) {
		try{			
			String functionalityName="Teacher List";
			String subjectName="Updated Employee Details ";
			String moduleName="ERP";
			String loggingDesc="Employee publication details updated for user id- "+employee.getResource().getUserId();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(employee.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in updateEmployeePublicationDetails(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of updated employee working details
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.updateEmployeeWorkingDetails(..))"+"&& args(employee)",returning= "result")
	public void updateEmployeeWorkingDetails(JoinPoint joinPoint,Employee employee, Object result) {
		try{			
			String functionalityName="Teacher List";
			String subjectName="Updated Employee Details ";
			String moduleName="ERP";
			String loggingDesc="Employee working details updated for user id- "+employee.getResource().getUserId();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(employee.getUpdatedBy());	
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in updateEmployeeWorkingDetails(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * This method is used for log into database, the acknowledgment of updated employee profile image 
	 *  
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.erp.ERPService.updateEmployeeImage(..))"+"&& args(employee)",returning= "result")
	public void updateEmployeeImage(JoinPoint joinPoint,Employee employee, Object result) {
		try{			
			String functionalityName="Teacher List";
			String subjectName="Updated Employee Details ";
			String moduleName="ERP";
			String loggingDesc="Employee profile image updated for user id- "+employee.getResource().getUserId();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(employee.getUpdatedBy());	
			if(result!=null){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
				}
		}catch(Exception e){
			logger.error("Exception in updateEmployeeImage(JoinPoint joinPoint,..................) method in ErpLoggingNotificationAspect.java", e);
		}
	}
}
