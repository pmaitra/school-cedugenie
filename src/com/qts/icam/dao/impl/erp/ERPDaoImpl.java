package com.qts.icam.dao.impl.erp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.itextpdf.text.log.SysoLogger;
import com.qts.icam.dao.erp.ERPDao;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.common.Address;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.Marks;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.Staff;
import com.qts.icam.model.common.SubjectList;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.common.TeachingLevel;
import com.qts.icam.model.common.TemplateLedgerMapping;
import com.qts.icam.model.common.WorkShift;
import com.qts.icam.model.erp.AwardsAndRecognization;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.DesignationLevel;
import com.qts.icam.model.erp.DesignationType;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.EmployeeDependent;
import com.qts.icam.model.erp.EmployeeType;
import com.qts.icam.model.erp.IncomeTaxParameters;
import com.qts.icam.model.erp.IncomeTaxSlab;
import com.qts.icam.model.erp.IncomeTaxSlabDetails;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.erp.MiscellaneousTax;
import com.qts.icam.model.erp.NomineeDetails;
import com.qts.icam.model.erp.Organization;
import com.qts.icam.model.erp.OtherQualification;
import com.qts.icam.model.erp.Publication;
import com.qts.icam.model.erp.Qualification;
import com.qts.icam.model.erp.ResourceAttendence;
import com.qts.icam.model.erp.SalaryBreakUp;
import com.qts.icam.model.erp.SalaryTemplate;
import com.qts.icam.model.erp.SalaryTemplateDetails;
import com.qts.icam.model.erp.WorkShopAndTraining;
import com.qts.icam.model.finance.Transaction;
import com.qts.icam.model.finance.TransactionDetails;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;


@Repository
public class ERPDaoImpl implements ERPDao {
	
	public static Logger logger = Logger.getLogger(ERPDaoImpl.class);
	@Autowired
	EncryptDecrypt encryptDecrypt; 
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<Department> getAllDepartment() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Department> deptList = null;
		try{
			logger.info("Executing getAllDepartment() from ERPDaoImpl");
			deptList = session.selectList("selectAllDepartment");
		}catch(Exception e){
			logger.error("Exception occured while executing getAllDepartment() from ERPDAOImpl", e);	
		}
		return deptList;
	}
	
	/**
	 * @author ranita.sur
	 * changes taken on 14062017*/		
	
	@Override
	public String addDepartment(Department department) {
		String submitResponse = "Fail";
		int insertStatus=0;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			logger.info("Executing addDepartment() from ERPDaoImpl");
			department.setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));	
			System.out.println("Line 91"+department.getDepartmentName());
			System.out.println("Line 91"+department.getParentDepartment());
			 insertStatus = session.insert("insertIntoDepartment", department);			 
			if(insertStatus !=0){
				submitResponse = "Success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured while executing addDepartment() from ERPDAOImpl", e);			
		} finally {
			session.close();
		}
		return submitResponse;
	}

	@Override
	public String editDepartment(Department department) {
		String updateResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("Executing editDepartment() from ERPDaoImpl");		
			int updateStatus = session.update("updateDepartment", department);			
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		} catch (Exception e) {
			logger.error("Exception occured while executing editDepartment() from ERPDAOImpl", e);
		} finally {
			session.close();
		}
		return updateResponse;
	}


	@Override
	public List<Designation> getAllDesignation() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Designation> designationList = null;
		try {			
			logger.info("Executing getAllDesignation() from ERPDaoImpl");
			designationList = session.selectList("selectAllDesignation");			
		} catch (Exception e) {
			logger.error("Exception occured while executing getAllDesignation() from ERPDAOImpl", e);
		} finally {
			session.close();
		}
		return designationList;
	}

	/**
	 * anup.roy
	 * 11072017*/
	
	@Override
	public String addDesignation(Designation designation) {
		String submitResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("Executing addDesignation() from ERPDaoImpl");
			designation.setDesignationObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
			String existingDesignation = null;
			existingDesignation = session.selectOne("selectOneDesignation", designation);
			if(null == existingDesignation){
				int insertStatus = session.insert("insertIntoDesignation", designation);	
				if(insertStatus == 1){
					submitResponse = "Success";
				}
			}else{
				submitResponse = "duplicate";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured while executing addDesignation() from ERPDAOImpl", e);			
		} finally {
			session.close();
		}
		return submitResponse;
	}

	@Override
	public String editDesignation(Designation designation) {
		String updateResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("Executing editDesignation() from ERPDaoImpl");		
			int updateStatus = session.update("updateDesignation", designation);			
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		} catch (Exception e) {
			logger.error("Exception occured while executing editDesignation() from ERPDAOImpl", e);
		} finally {
			session.close();
		}
		return updateResponse;
	}


	@Override
	public List<ResourceType> getAllResourceType() {
		SqlSession session =sqlSessionFactory.openSession();
		List<ResourceType> resourceTypeList = null;
		try{
			logger.info("Executing getAllEmployeeType() from ERPDaoImpl");
			resourceTypeList = session.selectList("selectAllResourceType");
			//System.out.println("from dao:"+resourceTypeList.size());
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured while executing getAllEmployeeType() from ERPDAOImpl", e);	
		}
		return resourceTypeList;
	}


	@Override
	public String addEmployeeType(EmployeeType employeeType) {
		String submitResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			logger.info("Executing addEmployeeType() from ERPDaoImpl");
			employeeType.setEmployeeTypeObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));			
			int insertStatus = session.insert("insertIntoEmployeeType", employeeType);			 
			if(insertStatus == 1){
				submitResponse = "Success";
			}
		} catch (Exception e) {
			logger.error("Exception occured while executing addEmployeeType() from ERPDAOImpl", e);			
		} finally {
			session.close();
		}
		return submitResponse;
	}


	@Override
	public String editEmployeeType(EmployeeType employeeType) {
		String updateResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("Executing editEmployeeType() from ERPDaoImpl");		
			int updateStatus = session.update("updateEmployeeType", employeeType);			
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		} catch (Exception e) {
			logger.error("Exception occured while executing editEmployeeType() from ERPDAOImpl", e);
		} finally {
			session.close();
		}
		return updateResponse;
	}


	@Override
	public List<JobType> getAllJobType() {
		SqlSession session =sqlSessionFactory.openSession();
		List<JobType> jobTypeList = null;
		try{
			logger.info("Executing getAllJobType() from ERPDaoImpl");
			jobTypeList = session.selectList("selectAllJobType");
		}catch(Exception e){
			logger.error("Exception occured while executing getAllJobType() from ERPDAOImpl", e);	
		}
		return jobTypeList;
	}


	@Override
	public String addJobType(JobType jobType) {
		String submitResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			logger.info("Executing addJobType() from ERPDaoImpl");
			jobType.setJobTypeObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));			
			int insertStatus = session.insert("insertIntoJobType", jobType);			 
			if(insertStatus == 1){
				submitResponse = "Success";
			}
		} catch (Exception e) {
			logger.error("Exception occured while executing addJobType() from ERPDAOImpl", e);			
		} finally {
			session.close();
		}
		return submitResponse;
	}


	@Override
	public String editJobType(JobType jobType) {
		String updateResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("Executing editJobType() from ERPDaoImpl");	
			int updateStatus = session.update("updateJobType", jobType);
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		} catch (Exception e) {
			logger.error("Exception occured while executing editJobType() from ERPDAOImpl", e);
		} finally {
			session.close();
		}
		return updateResponse;
	}
	
	@Override
	public List<DesignationLevel> getAllDesignationLevel() {
		SqlSession session =sqlSessionFactory.openSession();
		List<DesignationLevel> designationLevelList = null;
		try{
			logger.info("Executing getAllDesignationLevel() from ERPDaoImpl");
			designationLevelList = session.selectList("selectAllDesignationLevel");
		}catch(Exception e){
			logger.error("Exception occured while executing getAllDesignationLevel() from ERPDAOImpl", e);	
		}
		return designationLevelList;
	}


	@Override
	public String addDesignationLevel(DesignationLevel designationLevel) {
		String submitResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			logger.info("Executing addDesignationLevel() from ERPDaoImpl");
			designationLevel.setDesignationLevelObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));			
			int insertStatus = session.insert("insertIntoDesignationLevel", designationLevel);			 
			if(insertStatus == 1){
				submitResponse = "Success";
			}
		} catch (Exception e) {
			logger.error("Exception occured while executing addDesignationLevel() from ERPDAOImpl", e);			
		} finally {
			session.close();
		}
		return submitResponse;
	}


	@Override
	public String editDesignationLevel(DesignationLevel designationLevel) {
		String updateResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("Executing editDesignationLevel() from ERPDaoImpl");	
			int updateStatus = session.update("updateDesignationLevel", designationLevel);
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		} catch (Exception e) {
			logger.error("Exception occured while executing editDesignationLevel() from ERPDAOImpl", e);
		} finally {
			session.close();
		}
		return updateResponse;
	}	
	
	/**
	 * ranita.sur 03072017*/

	@Override
	public String submitEmployeeDetails(Employee employee,Ledger ledger) {
		System.out.println();
		String submitResponse = "Fail";
		Integer insertResponse = null;
		int insertStatus1=0;
		int statusValue=0;
		Employee employee1 = new Employee();
		SqlSession session = sqlSessionFactory.openSession();		
		try{
			logger.info("Executing submitEmployeeDetails() from ERPDaoImpl");
			employee.setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
			employee.getResource().setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
			//IMPLICITLY ADDED			
			//employee.getResource().setGender("M");
			insertResponse = session.insert("insertEmployeeBasicDetails", employee);
			/*//System.out.println("insertResponse:"+insertResponse);
			ledger.setObjectId(encryptDecrypt.getBase64EncodedID("ERPDaoImpl"));
			ledger.setLedgerName(employee.getResource().getUserId());
			System.out.println("########################::"+ledger.getLedgerName());
			insertStatus1 = session.insert("insertinLedgerForEmployee", ledger);*/
			
				
			if(insertResponse == 1){
				session.insert("insertEmployeeOfficialDetails", employee);				
				employee.getAddress().setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
				employee.getAddress().setUserId(employee.getResource().getUserId());				
				employee.getAddress().setAddressType("PRESENT");
				session.insert("insertResourcePresentAddressDetails", employee.getAddress());				
				employee.getAddress().setAddressType("PERMANENT");
				session.insert("insertResourcePermananentAddressDetails", employee.getAddress());		
				
				
				if(employee.getQualificationList() != null && employee.getQualificationList().size() != 0){
					for(Qualification qualification : employee.getQualificationList()){						
						qualification.setQualificationObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						qualification.setUserId(employee.getResource().getUserId());
					}
					session.insert("insertResourceQualificationDetails", employee);
				}				
				if(employee.getOtherQualificationList() != null && employee.getOtherQualificationList().size() != 0){
					for(OtherQualification otherQualification : employee.getOtherQualificationList()){
						otherQualification.setOtherQualificationDetailsObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						otherQualification.setUserId(employee.getResource().getUserId());
					}
					session.insert("insertResourceOtherQualificationDetails", employee);
				}				
				if(employee.getEmployeeDependentList() != null && employee.getEmployeeDependentList().size() != 0){
					for(EmployeeDependent eDependent : employee.getEmployeeDependentList()){
						eDependent.setEmployeeDependentObjId(encryptDecrypt.encrypt("ERPDaoImpl"));
						eDependent.setUserId(employee.getResource().getUserId());
					}				
					session.insert("insertResourceDependentsDetails", employee);
				}				
				if(employee.getPublicationList() != null && employee.getPublicationList().size() != 0){
					for(Publication pub : employee.getPublicationList()){
						pub.setPublicationObjId(encryptDecrypt.encrypt("ERPDaoImpl"));
						pub.setUserId(employee.getResource().getUserId());						
					}
					session.insert("insertPublicationDetails", employee);
				}				
				if(employee.getOrganizationList() != null && employee.getOrganizationList().size() != 0){
					for(Organization org : employee.getOrganizationList()){						
						org.setOrganizationObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						org.setUserId(employee.getResource().getUserId());						
					}
					session.insert("insertOrganizationDetails", employee);
				}
				if(employee.getNomineeDetailsList() != null && employee.getNomineeDetailsList().size() != 0){
					for(NomineeDetails nd : employee.getNomineeDetailsList()){						
						nd.setNomineeDetailsObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						nd.setUserId(employee.getResource().getUserId());						
					}
					session.insert("insertNomineeDetails", employee);
				}
				
				if(employee.getWorkShopAndTrainingList() != null && employee.getWorkShopAndTrainingList().size() != 0){
					for(WorkShopAndTraining wat : employee.getWorkShopAndTrainingList()){						
						wat.setWorkShopAndTrainingObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						wat.setUserId(employee.getResource().getUserId());						
					}
					session.insert("insertWorkShopAndTrainingDetails", employee);
				}
				
				if(employee.getAwardsAndRecognizationList() != null && employee.getAwardsAndRecognizationList().size() != 0){
					for(AwardsAndRecognization anr : employee.getAwardsAndRecognizationList()){						
						anr.setAwardsAndRecognizationObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						anr.setUserId(employee.getResource().getUserId());						
					}
					session.insert("insertAwardsAndRecognizationDetails", employee);
				}
				
				session.insert("insertStaffLeaveDetails", employee);
				
				ledger.setObjectId(encryptDecrypt.getBase64EncodedID("ERPDaoImpl"));
				ledger.setLedgerName(employee.getResource().getUserId().toUpperCase());
				/*adder for Employee Ledger Mapping
				 *ranita.sur on 01082017 */
				/*System.out.println(employee.getResource().getEmailId());
				employee1 = session.selectOne("selectEmpRecId",employee);
				System.out.println(employee1.getEmpRecId());
				ledger.setOpeningDrCr(employee1.getEmpRecId());*/
				insertStatus1 = session.insert("insertinLedgerForEmployee", ledger);
				
					
				submitResponse = "Success";
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured while executing submitEmployeeDetails() from ERPDAOImpl", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return submitResponse;
	}

	
	@Override
	public List<Employee> getStaffList(Map<String, Object> parameters) {
		logger.info("Executing getStaffList() from ERPDaoImpl");
		List<Employee> staffListFromDB = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			staffListFromDB = session.selectList("selectStaffList", parameters);
		} catch (Exception e) {
			logger.error("Exception occured while executing getStaffList() from ERPDAOImpl", e);
		} finally {
			session.close();
		}
		return staffListFromDB;
	}
	
//	@Override
//	public List<Employee> getStaffSearchList(Map<String, Object> parameters) {
//		logger.info("Executing getStaffSearchList() from ERPDaoImpl");
//		List<Employee> searchStaffListFromDB = null;
//		SqlSession session = sqlSessionFactory.openSession();
//		try {
//			searchStaffListFromDB = session.selectList("selectStaffList", parameters);
//		} catch (Exception e) {
//			logger.error("Exception occured while executing getStaffSearchList() from ERPDAOImpl", e);
//		} finally {
//			session.close();
//		}
//		return searchStaffListFromDB;
//	}


	@Override
	public Employee getEmployeeDetails(Employee employee) {
		SqlSession session = sqlSessionFactory.openSession();
		Employee emp = new Employee();
		Address address = new Address();
		List<Qualification> quList = new ArrayList<Qualification>();
		List<Publication> pubList = new ArrayList<Publication>();
		List<EmployeeDependent> edbList = new ArrayList<EmployeeDependent>();
		List<Organization> orgList = new ArrayList<Organization>();
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		
		List<NomineeDetails> nomineeList = new ArrayList<NomineeDetails>();
		List<WorkShopAndTraining> wstList = new ArrayList<WorkShopAndTraining>();
		List<AwardsAndRecognization> aarList = new ArrayList<AwardsAndRecognization>();
		try{
			logger.info("Executing getEmployeeDetails() from ERPDaoImpl");
			emp = session.selectOne("selectEmployeeDetails", employee);	
			if(emp != null){
				address.setUserId(employee.getResource().getUserId());			
				attachmentList = session.selectList("selectEmployeeAttachmentsDetails", employee);
				if(attachmentList != null && attachmentList.size() !=0){
					emp.setAttachmentList(attachmentList);				
				}			
				address = session.selectOne("selectEmployeeAddressDetails", employee);
				if(address != null){
					emp.setAddress(address);
				}				
				quList = session.selectList("selectEmployeeQualificationDetails",employee);
				if(quList != null && quList.size() != 0){
					emp.setQualificationList(quList);
				}				
				pubList = session.selectList("selectEmployeePublicationDetails",employee);
				if(pubList != null && pubList.size() != 0){
					emp.setPublicationList(pubList);
				}					
				edbList = session.selectList("selectEmployeeDependentsDetails",employee);			
				if(edbList != null && edbList.size() != 0){
					emp.setEmployeeDependentList(edbList);
				}				
				orgList = session.selectList("selectEmployeeOrganizationDetails",employee);			
				if(orgList != null && orgList.size() != 0){
					emp.setOrganizationList(orgList);
				}
				
				nomineeList = session.selectList("selectEmployeeNomineeDetails",employee);			
				if(nomineeList != null && nomineeList.size() != 0){
					emp.setNomineeDetailsList(nomineeList);
				}
				
				wstList = session.selectList("selectEmployeeWorkShopDetails",employee);			
				if(wstList != null && wstList.size() != 0){
					emp.setWorkShopAndTrainingList(wstList);
				}
				
				aarList = session.selectList("selectEmployeeAwardsDetails",employee);			
				if(aarList != null && aarList.size() != 0){
					emp.setAwardsAndRecognizationList(aarList);
				}
			}else{
				emp = session.selectOne("selectStudentLoginDetails", employee);	
				address = session.selectOne("selectEmployeeAddressDetails", employee);
				if(address != null){
					emp.setAddress(address);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured while executing getEmployeeDetails() from ERPDAOImpl", e);
		}finally{
			session.close();
		}
		return emp;
	}


	@Override
	public String updateEmployeeBasicDetails(Employee employee) {
		String updateResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();		
		try{
			logger.info("DOB:"+employee.getResource().getDateOfBirth());
			logger.info("DOJ:"+employee.getDateOfJoining());
			logger.info("DOR:"+employee.getDateOfRetirement());
			logger.info("R:"+employee.getResource().getGender());
			logger.info("JT:"+employee.getJobType().getJobTypeName());
			logger.info("DEPT:"+employee.getDepartment().getDepartmentName());
			logger.info("D:"+employee.getDesignation().getDesignationName());
			//logger.info("ET:"+employee.getEmployeeType().getEmployeeTypeName());	//Naimisha 26052017		
			logger.info("QS:"+employee.getQualificationSummary());
			
			logger.info("Executing updateEmployeeBasicDetails() from ERPDaoImpl");
			int updateStatus = session.update("editEmployeeBasicDetails", employee);
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured while executing updateEmployeeBasicDetails() from ERPDAOImpl", e);
		}
		return updateResponse;
	}


	@Override
	public String updateEmployeePersonalDetails(Employee employee) {
		String updateResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("Executing updateEmployeePersonalDetails() from ERPDaoImpl");
			int updateStatus = session.update("updateEmployeePersonalDetails", employee);
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		}catch(Exception e){
			logger.error("Exception occured while executing updateEmployeePersonalDetails() from ERPDAOImpl", e);
		}
		return updateResponse;
	}


	@Override
	public String updateEmployeeQualificationDetails(Employee employee) {
		String updateResponse = "Fail";
		int updateStatus = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("Executing updateEmployeeQualificationDetails() from ERPDaoImpl");
			employee.setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
			session.delete("deleteEmployeeQualificationDetails", employee);			
			if(employee.getQualificationList() != null && employee.getQualificationList().size() != 0){
				for(Qualification qualification : employee.getQualificationList()){	
					qualification.setQualificationObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
					qualification.setUserId(employee.getResource().getUserId());
				}
				updateStatus=session.insert("insertResourceQualificationDetails", employee);
			}			
			if(employee.getOtherQualificationList() != null && employee.getOtherQualificationList().size() != 0){
				for(OtherQualification otherQualification : employee.getOtherQualificationList()){
					otherQualification.setOtherQualificationDetailsObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
					otherQualification.setUserId(employee.getResource().getUserId());
				}
				session.insert("insertResourceOtherQualificationDetails", employee);
			}
			if(employee.getAttachmentList() != null && employee.getAttachmentList().size() != 0){				
				session.insert("insertAdditionalAttachmentsForResource",employee);
			}	
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured while executing updateEmployeeQualificationDetails() from ERPDAOImpl", e);
		}
		return updateResponse;
	}


	@Override
	public String updateEmployeeBankDetails(Employee employee) {
		String updateResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("Executing updateEmployeeBankDetails() from ERPDaoImpl");
			int updateStatus = session.update("updateEmployeeBankDetails", employee);
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		}catch(Exception e){
			logger.error("Exception occured while executing updateEmployeeBankDetails() from ERPDAOImpl", e);
		}
		return updateResponse;
	}


	@Override
	public String updateEmployeeDependentsDetails(Employee employee) {
		String updateResponse = "Fail";
		int updateStatus = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("Executing updateEmployeeDependentsDetails() from ERPDaoImpl");
			session.update("deleteEmployeeDependentsDetails", employee);
			if(employee.getEmployeeDependentList() != null && employee.getEmployeeDependentList().size() != 0){
				for(EmployeeDependent eDependent : employee.getEmployeeDependentList()){
					eDependent.setEmployeeDependentObjId(encryptDecrypt.encrypt("ERPDaoImpl"));
					eDependent.setUserId(employee.getResource().getUserId());				
				}				
				updateStatus = session.insert("insertResourceDependentsDetails", employee);
			}	
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured while executing updateEmployeeDependentsDetails() from ERPDAOImpl", e);
		}
		return updateResponse;
	}

	@Override
	public String updateEmployeeAddressDetails(Employee employee) {
		String updateResponse = "Fail";
		SqlSession session = sqlSessionFactory.openSession();		
		try{
			logger.info("Executing updateEmployeeAddressDetails() from ERPDaoImpl");
			employee.getAddress().setUpdatedBy(employee.getUpdatedBy());
			employee.getAddress().setUserId(employee.getResource().getUserId());
			employee.getAddress().setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
			employee.getAddress().setAddressType("PRESENT");
			session.insert("updateResourcePresentAddressDetails", employee.getAddress());			
			employee.getAddress().setAddressType("PERMANENT");
			session.insert("updateResourcePermananentAddressDetails", employee.getAddress());
			updateResponse = "Success";		
		}catch(Exception e){
			logger.error("Exception occured while executing updateEmployeeAddressDetails() from ERPDAOImpl", e);
		}
		return updateResponse;
	}

	@Override
	public String serverSideValidationOfUserId(String strUserId) {
		SqlSession session = sqlSessionFactory.openSession();
		String usrName = null;
		try {
			logger.info("Executing serverSideValidationOfUserId() from ERPDaoImpl");
			usrName = session.selectOne("serverSideValidationOfUserId", strUserId);
		} catch (Exception e) {
			logger.error("Exception occured while executing serverSideValidationOfUserId() from ERPDAOImpl", e);
		} finally {
			session.close();
		}
		return usrName;
	}
	
	@Override
	public String serverSideValidationOfEmployeeCode(String employeeCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String usrName = null;
		try {
			logger.info("Executing serverSideValidationOfEmployeeCode() from ERPDaoImpl");
			usrName = session.selectOne("serverSideValidationOfEmployeeCode", employeeCode);
		} catch (Exception e) {
			logger.error("Exception occured while executing serverSideValidationOfEmployeeCode() from ERPDAOImpl", e);
		} finally {
			session.close();
		}
		return usrName;
	}

	@Override
	public String updateEmployeePublicationDetails(Employee employee) {
		String updateResponse = "Fail";
		int updateStatus = 0;
		SqlSession session = sqlSessionFactory.openSession();		
		try{
			logger.info("Executing updateEmployeePublicationDetails() from ERPDaoImpl");
			employee.setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
			session.update("deleteEmployeePublicationDetails", employee);
			if(employee.getPublicationList() != null && employee.getPublicationList().size() != 0){
				for(Publication pub : employee.getPublicationList()){
					pub.setPublicationObjId(encryptDecrypt.encrypt("ERPDaoImpl"));
					pub.setUserId(employee.getResource().getUserId());						
				}
				updateStatus=session.insert("insertPublicationDetails", employee);				
			}			
			if(employee.getAttachmentList() != null && employee.getAttachmentList().size() != 0){				
				session.insert("insertAdditionalAttachmentsForResource",employee);
			}			
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		}catch(Exception e){
			logger.error("Exception occured while executing updateEmployeePublicationDetails() from ERPDAOImpl", e);
		}
		return updateResponse;
	}

	@Override
	public String updateEmployeeWorkingDetails(Employee employee) {
		String updateResponse = "Fail";
		int updateStatus = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("Executing updateEmployeeWorkingDetails() from ERPDaoImpl");
			employee.setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
			session.update("deleteEmployeeWorkingDetails", employee);			
			if(employee.getOrganizationList() != null && employee.getOrganizationList().size() != 0){
				for(Organization org : employee.getOrganizationList()){						
					org.setOrganizationObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
					org.setUserId(employee.getResource().getUserId());						
				}
				updateStatus=session.insert("insertOrganizationDetails", employee);
			}
			if(employee.getAttachmentList() != null && employee.getAttachmentList().size() != 0){				
				session.insert("insertAdditionalAttachmentsForResource",employee);
			}	
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		}catch(Exception e){
			logger.error("Exception occured while executing updateEmployeeWorkingDetails() from ERPDAOImpl", e);
		}
		return updateResponse;
	}

	@Override
	public String updateEmployeeImageToAttachment(Employee employee) {
		String updateResponse = "Fail";
		int updateStatus = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("Executing updateEmployeeImageToAttachment() from ERPDaoImpl");
			employee.setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));			
			if(employee.getAttachment().getStorageRootPath() != null){
				session.update("deleteEmployeeImageDetails", employee);
				updateStatus=session.insert("insertImagesDetailsForResource",employee);
			}	
			logger.info("from daoimpl:"+updateStatus);
			if(updateStatus == 1){
				updateResponse = "Success";
			}
		}catch(Exception e){
			
			logger.error("Exception occured while executing updateEmployeeWorkingDetails() from ERPDAOImpl", e);
		}
		return updateResponse;
	}
	
	//lEAVE
	
		@Override
		public List<Leave> getleaveTypeList() {
			List<Leave> leaveTypeList=null;
			SqlSession session = sqlSessionFactory.openSession();
			try{			
				leaveTypeList=session.selectList("selectLeaveTypeList");
			}catch(Exception e){
				logger.error("Exception occured while executing getleaveTypeList() from ERPDAOImpl", e);
			}
			return leaveTypeList;
		}

		//Naimisha 21062017

		@Override
		public List<Leave> getResourceLeaveDetails(String userId) {
			List<Leave> leaveTypeList=null;
			SqlSession session = sqlSessionFactory.openSession();
			try{	
				 //userId = "ssp_rt";
				leaveTypeList =  session.selectList("selectResourceLeaveDetailsFromStaffLeaveDetails", userId);	
				if(null == leaveTypeList || leaveTypeList.size() ==0){
					leaveTypeList = session.selectList("selectResourceAllLeaveDetails", userId);	
				}
				
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception occured while executing getResourceLeaveDetails() from ERPDAOImpl", e);
			}
			return leaveTypeList;
		}


		@Override
		public String startLeaveRequest(Task task) {
			String requestId = null;
			SqlSession session = sqlSessionFactory.openSession();
			try{
				logger.info("Executing startLeaveRequest() from ERPDaoImpl");
				task.setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
				session.insert("insertLeaveRequest", task);	
				if(task.getTaskCode() != null){
					requestId = task.getTaskCode();
				}
			}catch(Exception e){			
				logger.error("Exception occured while executing startLeaveRequest() from ERPDAOImpl", e);
			}
			return requestId;
		}


		@Override
		public Leave getRemainingLeave(String leaveType, String userId) {
			Leave leaveTypeNumber = null;
			SqlSession session = sqlSessionFactory.openSession();
			try{			
				logger.info("In getRemainingLeave() method of ERPDAOImpl");
				Leave leave = new Leave();
				leave.setUserId(userId);
				leave.setLeaveType(leaveType);
				leaveTypeNumber = session.selectOne("selectRemainingLeaveType",leave);
				if(leaveTypeNumber == null){
					leaveTypeNumber = session.selectOne("selectRemainingLeaveTypeFromLeaveStructure",leave);
				}			
			}catch(Exception e){
				logger.error(e);
			}finally{
				session.close();
			}
			return leaveTypeNumber;
		}


		@Override
		public Task getWorkingDaysForLeaveRequest(Task task) {
			SqlSession session = sqlSessionFactory.openSession();
			try {
				session.selectOne("countWorkingDaysBetweenTwoDates", task);
			} catch (Exception e) {
				logger.error("In getWorkingDaysForLeaveRequest(Task task) of ERPDaoImpl - problem in catch",e);
			} finally {
				session.close();
			}
			return task;
		}


		@Override
		public Integer getRoleOfUser(Task task) {
			Integer roleId = null;
			SqlSession session = sqlSessionFactory.openSession();
			try{			
				roleId = session.selectOne("selectRoleResourceMapID", task);
				if(roleId==null)
					roleId=0;
			}catch(Exception e){
				logger.error("In getRoleOfUser(Task task) of ERPDaoImpl - problem in catch",e);	
			}finally{
				session.close();
			}
			return roleId;
		}


		@Override
		public List<Leave> getPendingTaskList() {
			List<Leave> pendingTaskList=null;
			SqlSession session = sqlSessionFactory.openSession();
			try{			
				pendingTaskList=session.selectList("selectPendingTaskList");			
			}catch(Exception e){
				logger.error("In getPendingTaskList() of ERPDaoImpl - problem in catch",e);		
				
			}finally{
				session.close();

			}
			return pendingTaskList;
		}


		@Override
		public List<Task> searchOnListPendingTask(Map<String, Object> parameters) {
			List<Task> pendingTaskList=null;
			SqlSession session = sqlSessionFactory.openSession();
			try{			
				pendingTaskList=session.selectList("selectPendingTaskList", parameters);			
			}catch(Exception e){
				logger.error("In getPendingTaskList() of ERPDaoImpl - problem in catch",e);	
				
			}finally{
				session.close();

			}
			return pendingTaskList;
		}


		@Override
		public Leave getPendingLeaveDetails(Leave leave) {
			Leave pendingTaskDetails=null;			
			SqlSession session = sqlSessionFactory.openSession();
			try{			
				pendingTaskDetails = session.selectOne("selectPendingLeaveDetails", leave);
//				Leave leave = new Leave();
//				leave.setUserId(task.getCreatedById());
//				leave.setLeaveType(task.getLeave().getLeaveType());
//				leaveTypeNumber = session.selectOne("selectRemainingLeaveType",leave);			
//				if (leaveTypeNumber == null) {
//					Leave leaveTypeNumberFromDB= session.selectOne("selectRemainingLeaveTypeFromLeaveStructure",leave);
//				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error("In ERPDaoImpl DaoImpl getPendingLeaveDetails from DB Failed"+ e);	
				
			}finally{
				session.close();
			}
			return pendingTaskDetails;
		}


		@Override
		public String insertLeaveResponse(Task task) {
			SqlSession session = sqlSessionFactory.openSession();
			String updateResponse = "Fail";
			try{			
				
				task.setUpdatedBy(task.getLeave().getUpdatedBy());
				task.setUserId(task.getLeave().getUserId());
				task.setTaskDesc(task.getLeave().getRemarks());
				task.setTaskId(task.getLeave().getLeaveId());
				task.setRemainingLeave(task.getLeave().getRemainingLeave());
				task.setNumberofDayRequestedFor(task.getLeave().getTotalRequestedLeave());
				task.setStatus(task.getLeave().getDecision());
				task.setTaskType(task.getLeave().getLeaveType());				
				session.update("insertLeaveResponse",task);			
				session.commit();
			}catch(Exception e){
				logger.error("In ERPDaoImpl insertLeaveResponse into DB Failed"+ e);	
				
			}finally{
				session.close();
			}
			return updateResponse;
		}
		
		@Override
		public List<Task> getTaskHistoryList(Task task) {
			List<Task> taskHistoryList=null;
			SqlSession session = sqlSessionFactory.openSession();
			try{
				taskHistoryList=session.selectList("getTaskHistoryList", task);			
			}catch(Exception e){
				logger.error("In ERPDaoImpl inserting getTaskHistoryList in DB Failed"+ e);	
				
			}finally{
				session.close();
			}
			return taskHistoryList;
		}

		@Override
		public List<Task> listPersonalTaskHistory(Task task) {
			List<Task> taskHistoryList=null;
			SqlSession session = sqlSessionFactory.openSession();
			try{
				taskHistoryList=session.selectList("getPersonalTaskHistoryList", task);			
			}catch(Exception e){
				logger.error("In ERPDaoImpl inserting getTaskHistoryList in DB Failed"+ e);	
				
			}finally{
				session.close();
			}
			return taskHistoryList;
		}

		@Override
		public Employee getTeacherDetails(String strTeacherId) {
			logger.info("In getTeacherDetails(String strTeacherId) method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			Employee teacherDetails = null;
			try {
				teacherDetails = session.selectOne("selectTeacherNameDesignation", strTeacherId);
				List<Qualification> qualificationList = session.selectList("selectTeacherQualification", strTeacherId);
				if (teacherDetails != null) {
					teacherDetails.setQualificationList(qualificationList);
				}
			} catch (NullPointerException e) {
				logger.error("Error In getTeacherDetails(String strTeacherId) method of ERPDaoImpl:NullPointerException:: " + e);
			} catch (Exception e) {
				logger.error("Error In getTeacherDetails(String strTeacherId) method of ERPDaoImpl:Exception:: "+ e);
			} finally {
				session.close();
			}
			return teacherDetails;
		}

		@Override
		public String submitStaffRetirement(Employee employee) {
			logger.info("In submitStaffRetirement(Staff staff) method of ERPDaoImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			String submitResponse = "Fail";
			int checkUpdateStatus = 0;
			try {
				checkUpdateStatus = session.update("activeStaffRetirement", employee);
				if(checkUpdateStatus == 1){
					submitResponse = "Success";
				}
			} catch (NullPointerException e) {
				
				logger.error("Error In submitStaffRetirement(Employee employee) method of ERPDaoImpl:NullPointerException:: "+ e);
			} catch (Exception e) {
				
				logger.error("Error In submitStaffRetirement(Employee employee) method of ERPDaoImpl :Exception:: "+ e);
			} finally {
				session.close();
			}
			return submitResponse;
		}

		@Override
		public List<Employee> getRetiredStaffList() {
			logger.info("Executing getStaffList() from ERPDaoImpl");
			List<Employee> retiredStaffListFromDB = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				retiredStaffListFromDB = session.selectList("selectRetiredStaffList");				
			} catch (Exception e) {
				logger.error("Exception occured while executing getStaffList() from ERPDAOImpl", e);
			} finally {
				session.close();
			}
			return retiredStaffListFromDB;
		}

		@Override
		public List<Designation> getDesignationForResourceType(String resourceType) {
			List<Designation> designationList = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {				
				logger.info("Executing getDesignationForResourceType() from ERPDaoImpl");			
				designationList = session.selectList("getDesignationForResourceType", resourceType);						
			} catch (Exception e) {
				logger.error("Exception occured while executing getDesignationForResourceType() from ERPDAOImpl", e);
			} finally {
				session.close();
			}
			return designationList;
		}
		

		@Override		
		public String updateEmployeeNomineeDetails(Employee employee) {
			String updateResponse = "Fail";
			int updateStatus = 0;
			SqlSession session = sqlSessionFactory.openSession();
			try{
				logger.info("Executing updateEmployeeNomineeDetails() from ERPDaoImpl");
				session.update("deleteEmployeeNomineeDetails", employee);
				if(employee.getNomineeDetailsList() != null && employee.getNomineeDetailsList().size() != 0){
					
					for(NomineeDetails nd : employee.getNomineeDetailsList()){
						nd.setNomineeDetailsObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						nd.setUserId(employee.getResource().getUserId());				
					}				
					updateStatus = session.insert("insertNomineeDetails", employee);
				}	
				if(updateStatus == 1){
					updateResponse = "Success";
				}
			}catch(Exception e){
				logger.error("Exception occured while executing updateEmployeeNomineeDetails() from ERPDAOImpl", e);
			}
			return updateResponse;
		}


		@Override
		public String updateEmployeeWorkShopAndTrainingDetails(Employee employee) {
			String updateResponse = "Fail";
			int updateStatus = 0;
			SqlSession session = sqlSessionFactory.openSession();
			try{
				logger.info("Executing updateEmployeeWorkShopAndTrainingDetails() from ERPDaoImpl");
				session.update("deleteEmployeeWorkShopAndTrainingDetails", employee);
				if(employee.getWorkShopAndTrainingList() != null && employee.getWorkShopAndTrainingList().size() != 0){
					for(WorkShopAndTraining wst : employee.getWorkShopAndTrainingList()){
						wst.setWorkShopAndTrainingObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						wst.setUserId(employee.getResource().getUserId());				
					}				
					updateStatus = session.insert("insertWorkShopAndTrainingDetails", employee);
				}	
				if(updateStatus == 1){
					updateResponse = "Success";
				}
			}catch(Exception e){
				logger.error("Exception occured while executing updateEmployeeWorkShopAndTrainingDetails() from ERPDAOImpl", e);
			}
			return updateResponse;
		}


		@Override
		public String updateEmployeeAwardsAndRecognizationDetails(Employee employee) {
			String updateResponse = "Fail";
			int updateStatus = 0;
			SqlSession session = sqlSessionFactory.openSession();
			try{
				logger.info("Executing updateEmployeeAwardsAndRecognizationDetails() from ERPDaoImpl");
				session.update("deleteEmployeeAwardsAndRecognizationDetails", employee);
				if(employee.getAwardsAndRecognizationList() != null && employee.getAwardsAndRecognizationList().size() != 0){
					
					for(AwardsAndRecognization aar : employee.getAwardsAndRecognizationList()){
						aar.setAwardsAndRecognizationObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						aar.setUserId(employee.getResource().getUserId());				
					}				
					updateStatus = session.insert("insertAwardsAndRecognizationDetails", employee);
				}	
				if(updateStatus == 1){
					updateResponse = "Success";
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception occured while executing updateEmployeeAwardsAndRecognizationDetails() from ERPDAOImpl", e);
			}
			return updateResponse;
		}


		@Override
		public String updateEmployeeConfidentialDetails(Employee employee) {
			String updateResponse = "Fail";
			int updateStatus = 0;
			SqlSession session = sqlSessionFactory.openSession();
			try{
				logger.info("Executing updateEmployeeConfidentialDetails() from ERPDaoImpl");								
				updateStatus = session.update("updateEmployeeConfidentialDetails", employee);
				if(updateStatus == 1){
					updateResponse = "Success";
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception occured while executing updateEmployeeConfidentialDetails() from ERPDAOImpl", e);
			}
			return updateResponse;
		}

//Naimisha 21062017
		@Override
		public String insertManualLeaveResponse(Task task) {
			SqlSession session = sqlSessionFactory.openSession();
			String updateResponse = "Fail";
			int updateStatus = 0;
			try{			
				task.setTaskObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
				task.setUpdatedBy(task.getLeave().getUpdatedBy());
				task.setUserId(task.getLeave().getUserId());
				task.setTaskDesc(task.getLeave().getRemarks());
				task.setTaskId(task.getLeave().getLeaveId());
				task.setRemainingLeave(task.getLeave().getRemainingLeave());
				task.setNumberofDayRequestedFor(task.getLeave().getTotalRequestedLeave());
				task.setStatus(task.getLeave().getDecision());
				task.setTaskType(task.getLeave().getLeaveType());
				String updated_by = task.getLeave().getUpdatedBy();
				/*for(Leave lv : task.getLeaveList())
				{
					System.out.println(lv.getTotalRequestedLeave());
				}*/
				String userId = task.getLeave().getUserId();
				List<Leave> staffLeaveDetailsList = session.selectList("selectResourceLeaveDetailsFromStaffLeaveDetails",userId);
				if(null == staffLeaveDetailsList || staffLeaveDetailsList.size() ==0){
					Employee employee = new Employee();
					employee.setObjectId(task.getTaskObjectId());
					employee.setUpdatedBy(task.getUpdatedBy());
					Resource resource  = new Resource();
					resource.setUserId(task.getUserId());
					employee.setResource(resource);
					session.insert("insertStaffLeaveDetails", employee);
					//session.insert("insertLeaveDetailsForMannualLeave",task);
					updateStatus = session.update("insertManualLeaveResponse",task);
				}else{
					 updateStatus = session.update("insertManualLeaveResponse",task);
				}
				
				int insertStatus = 0;
				if(updateStatus != 0){					
					for(Leave leave : task.getLeaveList()){
						if(leave.getTotalRequestedLeave() != 0){
							leave.setObjId(encryptDecrypt.encrypt("LeaveHistory"));
							leave.setUpdatedBy(updated_by);
							//System.out.println("updatedBY========"+leave.getUpdatedBy());
							insertStatus = session.update("insertStaffLeaveHistory",leave);
						}
					}
					//updateResponse = "Success";
				}
				if (insertStatus != 0){
					updateResponse = "Success";
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error("In ERPDaoImpl insertManualLeaveResponse into DB Failed "+ e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}finally{
				session.close();
			}
			return updateResponse;
		}
		
		
		@Override
		public List<Task> listPersonalLeaveHistory(Task task) {			
			List<Task> leaveHistoryList = null;
			SqlSession session = sqlSessionFactory.openSession();
			try{
				leaveHistoryList = session.selectList("getLeaveHistoryList", task);			
			}catch(Exception e){
				logger.error("In ERPDaoImpl inserting getLeaveHistoryList in DB Failed"+ e);				
			}finally{
				session.close();
			}			
			return leaveHistoryList;	
		}

		@Override
		public List<DesignationType> getAllDesignationType() {
			SqlSession session =sqlSessionFactory.openSession();
			List<DesignationType> designationTypeList = null;
			try{
				logger.info("Executing getAllDesignationType() from ERPDaoImpl");
				designationTypeList = session.selectList("selectAllDesignationType");
			}catch(Exception e){				
				logger.error("Exception occured while executing getAllDesignationType() from ERPDAOImpl", e);	
			}
			return designationTypeList;
		}

		@Override
		public String addDesignationType(DesignationType designationType) {
			String submitResponse = "Fail";
			SqlSession session = sqlSessionFactory.openSession();
			try {			
				logger.info("Executing addDesignationType() from ERPDaoImpl");
				designationType.setDesignationTypeObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
				//System.out.println("DT:"+designationType.getDesignationTypeName());
				int insertStatus = session.insert("insertIntoDesignationType", designationType);			 
				if(insertStatus == 1){
					submitResponse = "Success";
				}
			} catch (Exception e) {
				logger.error("Exception occured while executing addDesignationType() from ERPDAOImpl", e);			
			} finally {
				session.close();
			}
			return submitResponse;
		}

		@Override
		public String editDesignationType(DesignationType desginationType) {
			String updateResponse = "Fail";
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("Executing editDesignationType() from ERPDaoImpl");	
				int updateStatus = session.update("updateDesignationType", desginationType);
				if(updateStatus == 1){
					updateResponse = "Success";
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception occured while executing editDesignationType() from ERPDAOImpl", e);
			} finally {
				session.close();
			}
			return updateResponse;
		}

		/**
		 * sourav.bhadra
		 * 29062017*/

		@Override
		public String addDesignationLevelMapping(List<Designation> designationList) {
			SqlSession session = sqlSessionFactory.openSession();
			String submitResponse = "Fail";
			int insertStatusCount = 0;
			int designationLevelSize = 0;
			try {
				logger.info("addDesignationLevelMapping(JobType jobType) method in ErpDaoImpl");
				for(Designation d : designationList){
					designationLevelSize = d.getDesignationLevelList().size();
				}
				for(Designation designation:designationList){
					for(DesignationLevel level:designation.getDesignationLevelList()){
						System.out.println("designation levelcode===="+level.getDesignationLevelCode());
						level.setDesignationLevelObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						level.setDesignationLevelName(designation.getDesignationCode()+"-"+level.getDesignationLevelCode());
						level.setDesignationLevelDesc(designation.getDesignationCode());   // For Mapping Purpose
						insertStatusCount = session.insert("addDesignationLevelMapping",level);
					}					
				}	
				if(insertStatusCount != 0){
					submitResponse = "Success";
				}
			} catch (Exception e) {
				submitResponse = "Fail";
				e.printStackTrace();
				logger.error("Exception in addDesignationLevelMapping(List<Designation> designationList) method in ErpDaoImpl", e);
			} finally {
				session.close();
			}
			return submitResponse;
		}


		@Override
		public List<Designation> getAllMappedDesignation() {
			List<Designation> designationListFromDB = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("getAllMappedDesignation() method in ErpDaoImpl");			
				designationListFromDB = session.selectList("getAllMappedDesignation");
				//System.out.println("FROM DAO:"+designationListFromDB.size());
				if(designationListFromDB!=null && designationListFromDB.size()!=0){
					for(Designation designation:designationListFromDB){
						List<DesignationLevel> designationLevelList=new ArrayList<DesignationLevel>();
						designationLevelList=session.selectList("getAllMappedLevelForDesignation",designation);
						//System.out.println("from DAO designationLevelList"+designationLevelList.size());
						if(designationLevelList.size()!=0){
							designation.setDesignationLevelList(designationLevelList);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getAllMappedDesignation() method in ErpDaoImpl", e);
			} finally {
				session.close();
			}
			return designationListFromDB;
		}

//Modified by naimisha 16082017
		@Override
		public String updateDesignationLevelMapping(Designation designation) {
			SqlSession session = sqlSessionFactory.openSession();
			String insertUpdateStatus = "success";
			try {
				logger.info("updateDesignationLevelMapping() method in ErpDaoImpl");			
				
				if(null != designation.getNewDesignationLevelList()){
					for(DesignationLevel designationLevel:designation.getNewDesignationLevelList()){						
						designationLevel.setDesignationLevelObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						designationLevel.setUpdatedBy(designation.getUpdatedBy());
						designationLevel.setDesignationLevelName(designation.getDesignationCode()+"-"+designationLevel.getDesignationLevelCode());
						designationLevel.setDesignationLevelDesc(designation.getDesignationCode());   // For Mapping Purpose
						session.insert("addDesignationLevelMapping",designationLevel);
						insertUpdateStatus = "insertSuccess";
					}
				}
				if(null != designation.getOldDesignationLevelList()){
					for(DesignationLevel designationLevel:designation.getOldDesignationLevelList()){
						Designation designationObj = new Designation();
						designationObj.setDesignationCode(designation.getDesignationCode());
						designationObj.setDesignationLevel(designationLevel);
						SalaryTemplate salaryTemplateDetails = session.selectOne("selectSalaryTemplateAgainstDesignationAndLevel", designationObj);
						if(null == salaryTemplateDetails){
							session.update("updateDesignationLevelMapping", designationObj);
							insertUpdateStatus = "updateSuccess";
						}else{
							insertUpdateStatus = "alreadyMapped";
						}
						
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				insertUpdateStatus = "fail";
				logger.error("updateDesignationLevelMapping() method in ErpDaoImpl", e);
			} finally {
				session.close();
			}
			
			return insertUpdateStatus;
		}
		
		@Override
		public List<SalaryBreakUp> getSalaryBreakUp() {
			logger.info("In getSalaryBreakUp() method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			List<SalaryBreakUp> salaryBreakUpList = null;
			try {
				salaryBreakUpList = session.selectList("selectSalaryBreakUpIncludeSlab");
				for (SalaryBreakUp salaryBreakUp : salaryBreakUpList) {
					List<String> salaryBreakUpTypelist = session.selectList("selectSalaryBreakUpType");
					if (salaryBreakUpTypelist != null && salaryBreakUpTypelist.size() != 0) {
						salaryBreakUp.setSalaryBreakUpTypelist(salaryBreakUpTypelist);
					}else{
						//System.out.println("emplty liust");
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				logger.error("Error In getSalaryBreakUp() method of BackOfficeDAOImpl:NullPointerException:: "+ e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error In getSalaryBreakUp() method of BackOfficeDAOImpl:Exception:: " + e);
			} finally {
				session.close();
			}
			return salaryBreakUpList;
		}

		@Override
		public List<SalaryBreakUp> createSalaryBreakUp(SalaryBreakUp salaryBreakUp) {
			logger.info("In createSalaryBreakUp(SalaryBreakUp salaryBreakUp) method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			List<SalaryBreakUp> salaryBreakUpList = null;
			int insertCheck = 0;
			try {
				if (salaryBreakUp.getSalaryBreakUpName().trim().length() == 0 || salaryBreakUp.getSalaryBreakUpType().trim().length() == 0) {
				} else {
					salaryBreakUp.setSalaryBreakUpObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
					salaryBreakUp.setSalaryBreakUpName(salaryBreakUp.getSalaryBreakUpName().trim().toUpperCase());
					salaryBreakUp.setSlab(salaryBreakUp.isSlab());
					insertCheck = session.insert("insertSalaryBreakUp", salaryBreakUp);
					
					session.commit();
				}
				salaryBreakUpList = getSalaryBreakUp();
				if (salaryBreakUpList != null && salaryBreakUpList.size() != 0) {
					if (salaryBreakUp.getStatus().equals("success")) {
						salaryBreakUpList.get(0).setStatus("success");
					} else {
						salaryBreakUpList.get(0).setStatus(salaryBreakUp.getStatus());
					}
					if (insertCheck == 0) {
						salaryBreakUpList.get(0).setStatus("fail");
					}
				}
			} catch (NullPointerException e) {
				logger.error("Error In createSalaryBreakUp(SalaryBreakUp salaryBreakUp) method of BackOfficeDAOImpl:NullPointerException:: "+ e);
			} catch (Exception e) {
				logger.error("Error In createSalaryBreakUp(SalaryBreakUp salaryBreakUp) method of BackOfficeDAOImpl:Exception:: " + e);
			} finally {
				session.close();
			}
			return salaryBreakUpList;
		}


		@Override
		public List<SalaryBreakUp> getSalaryBreakUpList() {
			logger.info("In getSalaryBreakUpList() method of ERPDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();	
			List<SalaryBreakUp> salaryBreakUpList = new ArrayList<SalaryBreakUp>();
			try {
				 salaryBreakUpList = session.selectList("selectSalaryBreakUp");		
			} catch (NullPointerException e) {
				logger.error("Error In getSalaryBreakUpList() method of ERPDAOImpl:NullPointerException:: "+ e);
			} catch (Exception e) {
				logger.error("Error In getSalaryBreakUpList() method of ERPDAOImpl:Exception:: "+ e);
			} finally {
				session.close();
			}
			return salaryBreakUpList;
		}
		
		@Override
		public List<SalaryTemplate> salaryTemplateList() {
			logger.info("In submitSalaryTemplate() method of ERPDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();	
			List<SalaryTemplate> salaryTemplateList = new ArrayList<SalaryTemplate>();
			try {				
				salaryTemplateList = session.selectList("selectSalaryTemplateList");								 
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error In submitSalaryTemplate() method of ERPDAOImpl:Exception:: "+ e);
			} finally {
				session.close();
			}		
			return salaryTemplateList;
		}
		


		@Override
		public List<SalaryBreakUp> getSalaryBreakUpForSlab() {
			SqlSession session = sqlSessionFactory.openSession();
			List<SalaryBreakUp> salBreakUpList = null;
			try{
				logger.info("getSalaryBreakUpForSlab() method in ErpDaoImpl");			
				salBreakUpList = session.selectList("getSalaryBreakUpNameForSlab");			
			}catch(Exception e){
				logger.error(" Exception in getSalaryBreakUpForSlab() of ErpDaoImpl :",e);
				e.printStackTrace();
			}finally{
				session.close(); 
			}
			return salBreakUpList;
		}		



		@Override
		public List<SalaryBreakUp> getSalaryBreakUpForNonSlab() {
			SqlSession session = sqlSessionFactory.openSession();
			List<SalaryBreakUp> nonSlabBreakUpList = null;
			try{
				logger.info("getSalaryBreakUpForNonSlab() method in ErpDaoImpl");			
				nonSlabBreakUpList = session.selectList("getSalaryBreakUpNameForNonSlab");			
			}catch(Exception e){
				logger.error(" Exception in getSalaryBreakUpForNonSlab() of ErpDaoImpl :",e);
				e.printStackTrace();
			}finally{
				session.close(); 
			}
			return nonSlabBreakUpList;
		}
		



		@Override
		public String getSubmittedSlabTypeForMiscTax(String taxTypeCode) {
			String submitResponse = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("getSubmittedSlabTypeForMiscTax() method in ERPDaoImpl");
				int rowCount = 0;
				rowCount = session.selectOne("countSubmittedSlabTypeForMiscTax", taxTypeCode);			
				submitResponse = String.valueOf(rowCount);
			} catch (Exception e) {
				logger.error("getSubmittedSlabTypeForMiscTax() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return submitResponse;
		}

		
		



		@Override
		public List<SalaryBreakUp> getSalaryBreakUpForMiscTaxSlab() {
			SqlSession session = sqlSessionFactory.openSession();
			List<SalaryBreakUp> salBreakUpList = null;
			try{
				logger.info("getSalaryBreakUpForSlab() method in ErpDaoImpl");			
				salBreakUpList = session.selectList("getSalBreakUpForSubmittedMiscTaxSlab");			
			}catch(Exception e){
				logger.error(" Exception in getSalaryBreakUpForSlab() of ErpDaoImpl :",e);
				e.printStackTrace();
			}finally{
				session.close(); 
			}
			return salBreakUpList;
		}
		
		@Override
		public String submitMiscTaxSlab(List<MiscellaneousTax> miscTaxSlabList, MiscellaneousTax miscTax) {
			String submitResponse = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				int status = 0;
				logger.info("submitMiscTaxSlab() method in ERPDaoImpl");
				miscTax.setMiscellaneousTaxSlabObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
				int insertStatus = session.insert("insertIntoSlabCalculation", miscTax);
				if(insertStatus != 0){
					for(MiscellaneousTax miTax : miscTaxSlabList){
						miTax.setMiscellaneousTaxSlabObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						status = session.insert("insertIntoMiscTaxSlab", miTax);				
					}
				}			
				submitResponse = String.valueOf(status);
			} catch (Exception e) {
				logger.error("submitMiscTaxSlab() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return submitResponse;
		}



		@Override
		public List<MiscellaneousTax> getMiscTaxSlabForEdit(String taxTypeCode) {
			SqlSession session = sqlSessionFactory.openSession();
			List<MiscellaneousTax> miscTaxSlabList = null;
			try{
				logger.info("getMiscTaxSlabForEdit(String taxTypeName) method in ErpDaoImpl");			
				miscTaxSlabList = session.selectList("getMiscTaxSlabForUpdate",taxTypeCode);			
			}catch(Exception e){
				logger.error(" Exception in getMiscTaxSlabForEdit(String taxTypeName) of ErpDaoImpl :",e);
				e.printStackTrace();
			}finally{
				session.close(); 
			}
			return miscTaxSlabList;
		}		



		@Override
		public String updateMiscTaxSlab(List<MiscellaneousTax> miscTaxSlabList, MiscellaneousTax miscTax) {
			String submitResponse = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				int status = 0;
				int updateStatus = 0;
				logger.info("updateMiscTaxSlab() method in ERPDaoImpl");
				
				updateStatus = session.update("updateMiscTaxSlab", miscTax);			
				if(updateStatus != 0){
					miscTax.setMiscellaneousTaxSlabObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
					int insertStatus = session.insert("insertIntoSlabCalculation", miscTax);
					if(insertStatus != 0){
						for(MiscellaneousTax miTax : miscTaxSlabList){
							miTax.setMiscellaneousTaxSlabObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));						
							status = session.insert("insertIntoMiscTaxSlab", miTax);				
						}
					}
				}			
				submitResponse = String.valueOf(status);
			} catch (Exception e) {
				logger.error("updateMiscTaxSlab() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return submitResponse;
		}

		


		@Override
		public String getSubmittedEmployerContribution(String taxTypeCode) {
			String submitResponse = "";
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("getSubmittedSlabTypeForMiscTax() method in ERPDaoImpl");
				List<MiscellaneousTax> outputList= session.selectList("submittedEmployerContribution", taxTypeCode);
				for(MiscellaneousTax misc:outputList){
					submitResponse = submitResponse + misc.getStartSlabAmount() + "," +misc.getEndSlabAmount()+ "," +misc.getTaxRate()+ "," +misc.getTaxFigureType()+ "," +misc.getMiscellaneousTaxType()+ "," +misc.getMiscellaneousTaxSlabCode()+ "," +misc.getTaxBasedOn()+ "," +misc.getUpdatedBy()+ "@@";
				}
			} catch (Exception e) {
				logger.error("getSubmittedSlabTypeForMiscTax() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return submitResponse;
		}
		
		@Override
		public String submitEmployerContribution(List<MiscellaneousTax> miscTaxSlabList, MiscellaneousTax miscTax) {
			String submitResponse = "FAIL";
			SqlSession session = sqlSessionFactory.openSession();
			try {				
				int status = 0;
				int updateStatus = 0;
				logger.info("submitMiscTaxSlab() method in ERPDaoImpl");
				int countData = session.selectOne("countEmployerContribution");
				if(countData != 0){
					
				updateStatus = session.update("updateEmployerContribution", miscTax);
				}
				if(updateStatus != 0 || countData == 0){
				miscTax.setMiscellaneousTaxSlabObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
				int insertStatus = session.insert("insertIntoEmployerSlabCalculation", miscTax);
				if(insertStatus != 0){
					for(MiscellaneousTax miTax : miscTaxSlabList){
						miTax.setMiscellaneousTaxSlabObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						status = session.insert("insertIntoEmployerContribution", miTax);	
						//System.out.println("fsdafdsfdsafdsafdsafas"+status);
					}
				}			
			}			
			if(status == 1){
				submitResponse = "SUCCESS";
			}
			
			} catch (Exception e) {
				logger.error("submitMiscTaxSlab() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return submitResponse;
		}

		@Override
		public IncomeTaxSlabDetails viewParameterOfIncomeTaxSalarySlab(IncomeTaxSlabDetails incomeTaxSlabDetailsFromScreen) {
			SqlSession session = sqlSessionFactory.openSession();
			IncomeTaxSlabDetails incomeTaxSlabDetails = null;
			try{			
				logger.info("viewParameterOfIncomeTaxSalarySlab() method in ErpDaoImpl");
			//	System.out.println("!!ftyfghfd!!!!!!:"+incomeTaxSlabDetailsFromScreen.getIncomeAge().getIncomeAgeCode());			
				List<IncomeTaxParameters> incomeTaxParameters = new ArrayList<IncomeTaxParameters>();
				//System.out.println(""+incomeTaxSlabDetailsFromScreen.getIncomeAge().getIncomeAgeCode());
				//System.out.println("XXXX:"+incomeTaxSlabDetailsFromScreen.getStatus());
				String getIncomeTaxBasedOnValue	= session.selectOne("getIncomeTaxBasedOnValue", incomeTaxSlabDetailsFromScreen.getStatus());
				//System.out.println("getIncomeTaxBasedOnValue:"+getIncomeTaxBasedOnValue);
				incomeTaxParameters = session.selectList("selectAllParameterSlabWise", incomeTaxSlabDetailsFromScreen.getIncomeAge().getIncomeAgeCode());			
				incomeTaxSlabDetails = session.selectOne("viewParameterOfIncomeTaxSalarySlab", incomeTaxSlabDetailsFromScreen.getIncomeAge().getIncomeAgeCode());			
				if(incomeTaxSlabDetails != null && incomeTaxParameters != null){
					//System.out.println("not null:"+incomeTaxSlabDetails.getGender()); 
					//System.out.println("number of slab:"+incomeTaxSlabDetails.getIncomeTaxSlabList().size());
					//System.out.println("number of paramter:"+incomeTaxSlabDetails.getIncomeTaxSlabList().get(0).getIncomeTaxParameterList().size());
					incomeTaxSlabDetails.setIncomeTaxParameters(incomeTaxParameters);
					if(getIncomeTaxBasedOnValue != null){
						incomeTaxSlabDetails.setIncomeTaxbasedOn(getIncomeTaxBasedOnValue);
					}
					for(IncomeTaxSlab its : incomeTaxSlabDetails.getIncomeTaxSlabList()){
						for(IncomeTaxParameters itp2 : its.getIncomeTaxParameterList()){
							//System.out.println(itp2.getFigure()+"|"+itp2.getFigureType()+"|"+itp2.getIncomeTaxParamCode());
						}
					}
				}
				else{
					incomeTaxSlabDetails = new IncomeTaxSlabDetails();
					incomeTaxSlabDetails.setStatus("DATA NOT FOUND");
				}
			}catch(Exception e){
				logger.error(" Exception in From getSalaryTemp(Designation designation) of ErpDaoImpl :",e);
				e.printStackTrace();
			}finally{
				session.close(); 
			}
			return incomeTaxSlabDetails;
		}



		@Override
		public String editIncomeTaxSalarySlab(IncomeTaxSlabDetails itIncomeTaxSlabDetails) {
			String strSalaryTemplateName = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				int status = 0;
				int insertIntoCalculationBaseStatus = 0;
				logger.info("editIncomeTaxSalarySlab() method in ERPDaoImpl");
				
				//System.out.println("CCCCCCCCCCCCCCCC:"+itIncomeTaxSlabDetails.getIncomeAge().getIncomeAgeCode()+"|"+itIncomeTaxSlabDetails.getStatus());
				int inActiveStatus = session.update("inActiveITParameterOfSlab", itIncomeTaxSlabDetails);
				//System.out.println("inActiveStatus:"+inActiveStatus);			
				
				int inBasedOnStatus = session.update("inActiveBasedParameterOfSlab", itIncomeTaxSlabDetails);
				//System.out.println("inBasedOnStatus:"+inBasedOnStatus);				
				
				//System.out.println("--name-"+itIncomeTaxSlabDetails.getIncomeTaxSlabDetailsName()+"---");
				//System.out.println("--status-"+itIncomeTaxSlabDetails.getStatus()+"---");
				IncomeTaxSlabDetails countStatus = session.selectOne("countCalculationBase",itIncomeTaxSlabDetails);
				//System.out.println("countStatus-->"+countStatus.getIncomeTaxSerialId());
				if(countStatus.getIncomeTaxSerialId() == 0){
					//System.out.println("calculated for"+itIncomeTaxSlabDetails.getStatus());
					//System.out.println("calculated on"+itIncomeTaxSlabDetails.getIncomeTaxSlabDetailsName());
					itIncomeTaxSlabDetails.setIncomeTaxSlabDetailsId(encryptDecrypt.encrypt("ERPDaoImpl"));
					insertIntoCalculationBaseStatus = session.insert("insertIntoCalculationBase", itIncomeTaxSlabDetails);
				}
				
				List<IncomeTaxSlab> incomeTaxSlabList = itIncomeTaxSlabDetails.getIncomeTaxSlabList();			
//				System.out.println("no of slab:"+incomeTaxSlabList.size());
				for(IncomeTaxSlab itSlab : incomeTaxSlabList){
//					System.out.println("no of parameter per slab:"+itSlab.getIncomeTaxParameterList().size());
					for(IncomeTaxParameters itParam : itSlab.getIncomeTaxParameterList()){
						itParam.setIncomeTaxParameterObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						itParam.setUpdatedBy(itSlab.getUpdatedBy());
						itParam.setIncomeTaxParamDesc(itSlab.getIncomeTaxSlabCode());
//						System.out.println("paramer_name:"+itParam.getIncomeTaxParamCode());
//						System.out.println("Figure type:"+itParam.getFigureType());
//						System.out.println("Figure:"+itParam.getAmount());
//						System.out.println("INCOMe agE:"+itParam.getIncomeAge());
						status = session.insert("insertIntoSalaryTaxSlab", itParam);
						}
				}
				strSalaryTemplateName = String.valueOf(status);
			} catch (Exception e) {
				logger.error("editIncomeTaxSalarySlab() method in ERPDaoImpl", e);
				e.printStackTrace();
			} finally {
				session.close();
			}
			return strSalaryTemplateName;
		}


		@Override
		public String getDesignationBasedOnDesignationType(String designationTypeCode) {
			SqlSession session = sqlSessionFactory.openSession();
			List<Designation> designationFromDb = null;
			String output = null;
			try {
				designationFromDb = session.selectList("selectDesignationList", designationTypeCode);
				StringBuilder sb = new StringBuilder();
				for (Designation designation : designationFromDb) {
					sb.append(designation.getDesignationCode() + ",");
					sb.append(designation.getDesignationName() + "@");
					output = sb.toString().substring(0, sb.toString().length() - 1);
				}
			} catch (Exception e) {
				logger.error("getDesignationBasedOnDesignationType() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return output;
		}


		@Override
		public String getLevelBasedOnDesignation(String designationCode) {
			SqlSession session = sqlSessionFactory.openSession();
			List<DesignationLevel> levelFromDb = null;
			String output = null;
			try {
				levelFromDb = session.selectList("getLevelListForDesignation", designationCode);
				StringBuilder sb = new StringBuilder();
				for (DesignationLevel level : levelFromDb) {
					sb.append(level.getDesignationLevelCode() + ",");
					sb.append(level.getDesignationLevelName() + "@");
					output = sb.toString().substring(0, sb.toString().length() - 1);
				}
			} catch (Exception e) {
				logger.error("getLevelBasedOnDesignation() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return output;
		}


		@Override
		public String checkSalaryTemplateName(String getsalaryTemplateName) {
			String strSalaryTemplateName = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("checkSalaryTemplateName() method in ERPDaoImpl");			
				strSalaryTemplateName = session.selectOne("getsalaryTemplateName", getsalaryTemplateName);
			} catch (Exception e) {
				logger.error("checkSalaryTemplateName() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return strSalaryTemplateName;
		}


		@Override
		public SalaryTemplate getSalaryTemplateDetails(String templateCode) {
			logger.info("In getSalaryTemplateDetails() method of ERPDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();	
			SalaryTemplate salaryTemplate=null;
			try {				
				salaryTemplate = session.selectOne("salaryTemplateDetailsList", templateCode);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error In getSalaryTemplateDetails() method of ERPDAOImpl:Exception:: "+ e);
			} finally {
				session.close();
			}		
			return salaryTemplate;
		}


		@Override
		public String updateSalaryTemplate(SalaryTemplate salaryTemplate) {
			//System.out.println("hello.............");
			String status="Salary template update failed";
			logger.info("In submitSalaryTemplate() method of ERPDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();		
			try {								
				salaryTemplate.setSalaryTemplateObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
				for(SalaryTemplateDetails  salaryTemplateDetails : salaryTemplate.getSalaryTemplateDetailsList()){
					salaryTemplateDetails.setSalaryTemplateDetailsObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
					int returnStatus = session.insert("updateSalaryTemplateDetails", salaryTemplateDetails);
				}					
			status="Salary template update successful";
			} catch (Exception e) {
				status="Salary template update failed";
				e.printStackTrace();
				logger.error("Error In submitSalaryTemplate() method of ERPDAOImpl:Exception:: "+ e);
			} finally {
				session.close();
			}		
			return status;
		}

		@Override
		public String submitIncomeTaxSalarySlab(IncomeTaxSlabDetails itIncomeTaxSlabDetails) {
			String strIncomeTaxSalarySlab = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				int status = 0;
				int statusAnother = 0;
				logger.info("submitIncomeTaxSalarySlab() method in ERPDaoImpl");			
				List<IncomeTaxSlab> incomeTaxSlabList = itIncomeTaxSlabDetails.getIncomeTaxSlabList();
				
				IncomeTaxSlabDetails countStatus = session.selectOne("countCalculationBase",itIncomeTaxSlabDetails);
				//System.out.println("countStatus"+countStatus.getIncomeTaxSerialId());
				if(countStatus.getIncomeTaxSerialId() == 0){
					//System.out.println("calculated for"+itIncomeTaxSlabDetails.getStatus());
					//System.out.println("calculated on"+itIncomeTaxSlabDetails.getIncomeTaxSlabDetailsName());
					itIncomeTaxSlabDetails.setIncomeTaxSlabDetailsId(encryptDecrypt.encrypt("ERPDaoImpl"));
					statusAnother = session.insert("insertIntoCalculationBase", itIncomeTaxSlabDetails);
				}
				
				//System.out.println("no of slab:"+incomeTaxSlabList.size());
					for(IncomeTaxSlab itSlab : incomeTaxSlabList){
						//System.out.println("no of parameter per slab:"+itSlab.getIncomeTaxParameterList().size());
						for(IncomeTaxParameters itParam : itSlab.getIncomeTaxParameterList()){
							itParam.setIncomeTaxParameterObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
							itParam.setUpdatedBy(itSlab.getUpdatedBy());
							itParam.setIncomeTaxParamDesc(itSlab.getIncomeTaxSlabCode());
							//System.out.println("paramer_name:"+itParam.getIncomeTaxParamCode());
							//System.out.println("Figure type:"+itParam.getFigureType());
							//System.out.println("Figure:"+itParam.getAmount());
							//System.out.println("INCOMe agE:"+itParam.getIncomeAge());
							status = session.insert("insertIntoSalaryTaxSlab", itParam);
							}
					}			
				strIncomeTaxSalarySlab = String.valueOf(status);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("submitIncomeTaxSalarySlab() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return strIncomeTaxSalarySlab;
		}


		@Override
		public Employee getStaffDetailsForPromotionAndSalaryMapping(Resource resource) {
			SqlSession session = sqlSessionFactory.openSession();
			Resource staffDetailsFromDB = null;
			Employee employee = null;
			try {
				//System.out.println("userId sdfsdf"+resource.getUserId());
				employee = session.selectOne("selectStaffSpecificDetails", resource);
								
				if (employee != null) {
					List<TeachingLevel> teachingLevelList = session.selectList("getTeachingLevelList");
					if (teachingLevelList != null && teachingLevelList.size() != 0) {
						employee.setTeachingLevelList(teachingLevelList);
					}
				
					staffDetailsFromDB = session.selectOne("selectStaffDetailsForEdit", resource);
					if (staffDetailsFromDB != null) {
						List<DesignationType> designationTypeList = session.selectList("selectDesignationTypeList");
						if(designationTypeList != null && designationTypeList.size() != 0){
							employee.setDesignationTypeList(designationTypeList);
							for(DesignationType designationType : designationTypeList){
								List<Designation> designationList = session.selectList("selectDesignationList",designationType.getDesignationTypeCode());
								designationType.setDesignationList(designationList);
								if(designationList != null && designationList.size() != 0){
									for(Designation designation: designationList){
										List<DesignationLevel> levelList = session.selectList("getLevelListForDesignation",designation.getDesignationCode());
										designation.setLevelList(levelList);
									}
								}
							}
						}						
						//System.out.println(staffDetailsFromDB.getDesignationCode()+"-"+staffDetailsFromDB.getDesignationLevel());
						SalaryTemplate salTemplate = session.selectOne("getSalaryTemplateForMapp", staffDetailsFromDB);
						
						if(salTemplate != null){
							employee.setSalaryTemplate(salTemplate);
						}
						employee.setResource(resource);
						List<SalaryBreakUp> salaryBreakUpList = session.selectList("getDesignationSalaryMappingDetails", employee);
						if(salaryBreakUpList != null && salaryBreakUpList.size() != 0){
							employee.setSalaryBreakUpList(salaryBreakUpList);
						}
						
						List<SalaryBreakUp> salBreakUpListForShow = null;		
						SalaryTemplate salaryTemplateObj = new SalaryTemplate();
						
						salaryTemplateObj.setDesignation(staffDetailsFromDB.getDesignationCode());
						salaryTemplateObj.setDesignationLevel(staffDetailsFromDB.getDesignationLevel());
						salaryTemplateObj.setSalaryTemplateCode(staffDetailsFromDB.getSalaryTemplateCode());
						
						salBreakUpListForShow = session.selectList("getSalaryBreakUpForShow", salaryTemplateObj);	
						
						if(salBreakUpListForShow != null && salBreakUpListForShow.size() != 0){
							//System.out.println("Hello:"+salBreakUpListForShow.size());
							employee.setSalaryBreakUpListForShow(salBreakUpListForShow);
						}
					}
				}
				employee.setResource(staffDetailsFromDB);				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			return employee;
		}
		
		@Override
		public String submitSalaryTemplate(SalaryTemplate salaryTemplate) {
			String status="Salary template saving failed";
			logger.info("In submitSalaryTemplate() method of ERPDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();		
			try {						
				salaryTemplate.setSalaryTemplateObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
				session.insert("salaryTemplate", salaryTemplate);
				String maxTempCode = session.selectOne("selectMaxTemplateId");
				salaryTemplate.setSalaryTemplateCode(maxTempCode);
				session.update("updateDesignationLevelMappingForTemplate",salaryTemplate);
				
				if(maxTempCode != null){
					for(SalaryTemplateDetails stdObj : salaryTemplate.getSalaryTemplateDetailsList()){					
						stdObj.setSalaryTemplate(maxTempCode);
						stdObj.setSalaryTemplateDetailsObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
						session.insert("insertSalaryTemplateDetails", stdObj);						
					}
				}
			status="success";
			} catch (Exception e) {
				status="fail";
				e.printStackTrace();
				logger.error("Error In submitSalaryTemplate() method of ERPDAOImpl:Exception:: "+ e);
			} finally {
				session.close();
			}		
			return status;
		}


		@Override
		public String setTeacherInterviewSchedule(Employee employee) {
			SqlSession session = sqlSessionFactory.openSession();
			String insertStatus = null;
			int insertTeacherInterviewScheduleStatus = 0;
			int mapExaminerForInterviewStatus = 0; 		
			try {
				logger.info("setTeacherInterviewSchedule(Staff staff) method in BackOfficeDaoImpl");			
				if (employee.getResource() != null) {					
					employee.setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
					insertTeacherInterviewScheduleStatus = session.insert("insertTeacherInterviewSchedule", employee);
					if (employee.getResourceList() != null) {
						for (Resource r : employee.getResourceList()) {
							System.out.println("USER ID :"+r.getUserId());
							employee.setEmployeeName(employee.getResource().getUserId());
							System.out.println("x:"+employee.getUpdatedBy()+"-"+employee.getEmployeeCode());
							mapExaminerForInterviewStatus = session.insert("mapExaminerForInterview", employee);
							System.out.println("y:"+mapExaminerForInterviewStatus);
						}
					}
					session.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.error("setTeacherInterviewSchedule(Staff staff) method in BackOfficeDaoImpl", e);
			} finally {
				session.close();
			}
			if (insertTeacherInterviewScheduleStatus != 0 && mapExaminerForInterviewStatus != 0) {//TEMP CODE
				insertStatus ="success";
			} else {
				insertStatus ="fail";
			}
			return insertStatus;
		}


		@Override
		public List<Employee> getTeacherInterviewScheduleList() {
			List<Employee> empList = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("getTeacherInterviewScheduleList() method in ErpDaoImpl");			
				empList = session.selectList("selectTeacherInterviewScheduleList");
			} catch (Exception e) {
				logger.error("getTeacherInterviewScheduleList() method in ErpDaoImpl", e);
			} finally {
				session.close();
			}
			return empList;
		}
		
		@Override
		public Employee getTeacherInterviewScheduleDetails(Employee employee) {
			List<Resource> resourceList = new ArrayList<Resource>();
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("getTeacherInterviewScheduleDetails() method in BackOfficeDaoImpl");			
				List<Resource> selectedResourceList = session.selectList("selectedResourceListForTeacherInterviewSchedule", employee);
				List<Resource> allResourceList = session.selectList("selectStaffListForExaminer");
				Map<String, Object> selectedResourceListMap = new HashMap<String, Object>();
				if (selectedResourceList != null && selectedResourceList.size() != 0) {
					for (Resource resource : selectedResourceList) {
						selectedResourceListMap.put(resource.getUserId(), resource.getUserId());
					}
				}
				if (selectedResourceListMap != null && selectedResourceListMap.size() != 0) {
					if (allResourceList != null && allResourceList.size() != 0) {
						for (Resource resource : allResourceList) {
							Resource r = new Resource();
							if (selectedResourceListMap.containsKey(resource.getUserId())) {
								r.setUserId(resource.getUserId());
								r.setStatus("Checked");
							} else {
								r.setUserId(resource.getUserId());
							}
							resourceList.add(r);
						}
					}
				} else {
					resourceList = allResourceList;
				}
				employee = session.selectOne("getCandidateDetailsForTeacherInterview",employee);
				//System.out.println(employee.getResource().getFirstName());
				//System.out.println(employee.getResource().getMiddleName());
				//System.out.println(employee.getResource().getLastName());
				if (employee != null) {
					employee.setResourceList(resourceList);
				}
			} catch (Exception e) {
				logger.error("getpositionList() method in BackOfficeDaoImpl", e);
				e.printStackTrace();
			} finally {
				session.close();
			}
			return employee;
		}
		
		@Override
		public List<Employee> updateTeacherInterviewSchedule(Employee employee) {
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("updateTeacherInterviewSchedule(Staff staff) method in BackOfficeDaoImpl");			
				if (employee.getResource() != null) {					
					employee.setObjectId((encryptDecrypt.encrypt("ERPDaoImpl")));
					session.update("updateTeacherInterviewSchedule", employee);
					session.update("inactiveTeacherInterviewExaminarMapping", employee);
					if (employee.getResourceList() != null) {
						for (Resource r : employee.getResourceList()) {
							//employee.setStaffName(r.getUserId());
							session.insert("mapExaminerForInterview", employee);
						}
					}
					session.commit();
				}
			} catch (Exception e) {
				logger.error("updateTeacherInterviewSchedule(Staff staff) method in BackOfficeDaoImpl",	e);
				e.printStackTrace();
			} finally {
				session.close();
			}
			return getTeacherInterviewScheduleList();
		}


		@Override
		public List<Employee> getCandidateId() {
			List<Employee> getCandidateId = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("getCandidateId() method in BackOfficeDaoImpl");			
				getCandidateId = session.selectList("getCandidateId");
			} catch (Exception e) {
				logger.error("getCandidateId() method in BackOfficeDaoImpl", e);
			} finally {
				session.close();
			}
			return getCandidateId;
		}
		
		@Override
		public String getCandidateName(Employee employee) {
			String candidateName = "";
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("getCandidateName(Staff staff) method in BackOfficeDaoImpl");			
				Resource resource = session.selectOne("getCandidateName", employee);
				if (resource != null) {
					if (resource.getFirstName() != null && resource.getFirstName().length() != 0) {
						candidateName = resource.getFirstName();
					}
					if (resource.getMiddleName() != null && resource.getMiddleName().length() != 0) {
						candidateName = candidateName + " " + resource.getMiddleName();
					}
					if (resource.getLastName() != null && resource.getLastName().length() != 0) {
						candidateName = candidateName + " " + resource.getLastName();
					}
					if (resource.getCode() != null && resource.getCode().length() != 0) {
						candidateName = candidateName + "*" + resource.getCode();
					} else {
						candidateName = candidateName + "*" + " ";
					}
					if (resource.getDesc() != null && resource.getDesc().length() != 0) {
						candidateName = candidateName + "*" + resource.getDesc();
					} else {
						candidateName = candidateName + "*" + " ";
					}				
					if(resource.getStatus() != null && resource.getStatus().length() != 0){	 
						candidateName = candidateName + "*" + resource.getStatus();
					}else{
						candidateName = candidateName + "*" + " ";
					}
				}			
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getCandidateName(Staff staff) method in BackOfficeDaoImpl", e);
			} finally {
				session.close();
			}
			return candidateName;
		}
		
		@Override
		public String submitTeacherInterviewDetails(Employee employee) {
			String insertStatus = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("submitTeacherInterviewDetails(staff) method in BackOfficeDaoImpl");			
				int status = 0;				
				employee.setObjectId(encryptDecrypt.encrypt("ERPDaoImpl"));
				//System.out.println(employee.getEmployeeCode()+"FRON DAO IMPL"+employee.getStatus());
				status = session.update("updateSubmitTeacherInterviewStatus", employee);
				for (Marks marks : employee.getMarksList()) {
					employee.setSubject(marks.getSubjectName());
					employee.setMarks(marks.getAvgMarks());
					status = session.insert("insertSubmitTeacherInterviewMarks", employee);
				}
				session.commit();
				if (status != 0) {
					insertStatus = "success";
				} else {
					insertStatus = "fail";
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("submitTeacherInterviewDetails(Staff staff) method in BackOfficeDaoImpl",e);
			} finally {
				session.close();
			}
			return insertStatus;
		}

		@Override
		public List<Employee> getTeacherInterviewList() {
			List<Employee> teacherInterviewList = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("getTeacherInterviewList() method in BackOfficeDaoImpl");			
				teacherInterviewList = session.selectList("getTeacherInterviewList");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getTeacherInterviewList() method in BackOfficeDaoImpl", e);
			} finally {
				session.close();
			}
			return teacherInterviewList;
		}


		@Override
		public Resource getCandidateDetails(String strStaffCode) {
			Resource resource = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {				
				resource = session.selectOne("getCandidateDetails", strStaffCode);				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("In getdesignationTypeCountryLanguageJobTypeCandidateName() method of BackOfficeDAOImpl",e);
			} finally {
				session.close();
			}
			return resource;
		}

		@Override
		public Employee getTeacherInterviewDetails(Employee employee) {
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("getTeacherInterviewDetails(Staff staff) method in ErpDaoImpl");	
				//System.out.println("dfasdsadasd:"+employee.getEmployeeCode());
				employee = session.selectOne("getCandidateDetailsForTeachetInterview", employee);
				//System.out.println(employee.getEmployeeCode()+"|"+employee.getResource().getFirstName());
				List<Marks> marksList = session.selectList("selectTeacherInterviewMarks", employee);
				if (employee != null) {
					employee.setMarksList(marksList);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("getTeacherInterviewDetails(Staff staff) method in ErpDaoImpl");
			} finally {
				session.close();
			}
			return employee;
		}
		
		@Override
		public List<String> getStaffUserIdList(String strQuery) {
			logger.info("In getStaffUserIdList(String strQuery) method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			List<String> staffUserIdList = null;
			try {
				staffUserIdList = session.selectList("selectStaffUserIdListByAjax",strQuery);
			} catch (NullPointerException e) {
				logger.error("Error In getStaffUserIdList(String strQuery) method of BackOfficeDAOImpl By Ajax call:NullPointerException:: "+ e);
			} catch (Exception e) {
				logger.error("Error In getStaffUserIdList(String strQuery) method of BackOfficeDAOImpl By Ajax call:Exception:: "+ e);
			} finally {
				session.close();
			}
			return staffUserIdList;
		}


		@Override
		public String getReportingPerson(String designation) {
			SqlSession session = sqlSessionFactory.openSession();
			List<Resource> resourceAsReportingPersonList = null;
			List<DesignationLevel> levelList = null;
			String output = null;
			String output1 = null;
			String outputFinal = null;
			try {
				resourceAsReportingPersonList = session.selectList("getReportingPerson", designation.trim());
				levelList = session.selectList("getLevelListForDesignation", designation.trim());
				if(resourceAsReportingPersonList != null && resourceAsReportingPersonList.size() != 0){	
					StringBuilder sb = new StringBuilder();
					for (Resource objResource : resourceAsReportingPersonList) {					
						sb.append(objResource.getUserId() + ",");
						sb.append(objResource.getDesignation() + "~");
						output = sb.toString().substring(0, sb.toString().length() - 1);
					}
				}else{			
						StringBuilder sb = new StringBuilder();									
						sb.append("SELF" + ",");
						sb.append("REPORTING" + "~");
						output = sb.toString().substring(0, sb.toString().length() - 1);
					
				}
				if(levelList != null && levelList.size() != 0){	
					StringBuilder sb1 = new StringBuilder();
					for (DesignationLevel objLevel : levelList) {					
						sb1.append(objLevel.getDesignationLevelCode() + ",");
						sb1.append(objLevel.getDesignationLevelName() + "@");
						output1 = sb1.toString().substring(0, sb1.toString().length() - 1);
					}
				}
				
				outputFinal = output + "$$" + output1 ;
			}catch (Exception e) {
				logger.error("getReportingPerson() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return outputFinal;
		}
		
		@Override
		public List<SalaryTemplate> getSalaryTemp(Designation designation) {
			SqlSession session = sqlSessionFactory.openSession();
			List<SalaryTemplate> salTempList = null;
			try{
				logger.info("getSalaryTemp() method in ErpDaoImpl");
				Resource resource = new Resource();
				
				resource.setDesignationCode(designation.getDesignationCode());
				resource.setDesignationLevel(designation.getLevel().getDesignationLevelName());
				salTempList = session.selectList("getSalaryTemplateForMapp", resource);			
			}catch(Exception e){
				logger.error(" Exception in From getSalaryTemp(Designation designation) of ErpDaoImpl :",e);
				e.printStackTrace();
			}finally{
				session.close(); 
			}
			return salTempList;
		}

		@Override
		public List<SalaryBreakUp> getSalaryBreakUpForTemplate(SalaryTemplate salaryTemplate) {
			SqlSession session = sqlSessionFactory.openSession();
			List<SalaryBreakUp> salBreakUpList = null;
			try{
				logger.info("getSalaryBreakUpForTemplate() method in ErpDaoImpl");			
				salBreakUpList = session.selectList("getSalaryBreakUpForShow", salaryTemplate);			
			}catch(Exception e){
				logger.error(" Exception in From getSalaryBreakUpForTemplate(SalaryTemplate salaryTemplate) of ErpDaoImpl :",e);
				e.printStackTrace();
			}finally{
				session.close(); 
			}
			return salBreakUpList;
		}

		@Override
		public Employee createErpSalaryMapping(Employee employee) {
			logger.info("In createErpSalaryMapping(Staff staff) method of ErpDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			try {
				String status = null;
				int insertStatus = 0;
				int updateStatusForErp = 0;
				int updateStatusForSalaryAmount = 0;
				int updatesalaryForPromotion = 0;
				// checking if position and job type is null or not.
				if (null != employee.getResource().getDesignationType().trim() && null != employee.getResource().getDesignation().trim() && null != employee.getResource().getDesignationLevel().trim() && null != employee.getResource().getSalaryTemplateCode().trim() && employee.getResource().getDesignationType().length() != 0 && employee.getResource().getDesignation().length() != 0 && employee.getResource().getDesignationLevel().length() != 0 && employee.getResource().getSalaryTemplateCode().length() != 0) {

					if(employee.getStatus().equalsIgnoreCase("Update")){
						int checkingExistingStatus = session.selectOne("checkExistingStatusForErpSalaryMapping",employee);
						// checking map already exist
						if (checkingExistingStatus == 0) {
							List<SalaryBreakUp> salaryBreakUpList = employee.getSalaryBreakUpList();							
							SalaryTemplate salaryTemplate = new SalaryTemplate();
							salaryTemplate.setSalaryTemplateCode(employee.getResource().getSalaryTemplateCode());
							for (SalaryBreakUp salaryBreakUp : salaryBreakUpList) {
								//System.out.println("?????????:"+employee.getResource().getUserId());
								salaryBreakUp.setSalaryBreakUpDesc(employee.getResource().getUserId());
								salaryBreakUp.setSalaryBreakUpObjectId(encryptDecrypt.encrypt("ErpDAOImpl"));
								salaryBreakUp.setSalaryBreakUpCode(employee.getResource().getDesignationType()+ ":"+ employee.getResource().getDesignation()+ ":" + employee.getResource().getDesignationLevel() + ":" + employee.getResource().getSalaryTemplateCode());
								salaryBreakUp.setAmount(salaryBreakUp.getAmount());
								salaryBreakUp.setSalaryBreakUpName(salaryBreakUp.getSalaryBreakUpName());
								salaryBreakUp.setSalaryTemplate(salaryTemplate);
								salaryBreakUp.setUpdatedBy(salaryBreakUp.getUpdatedBy());
								insertStatus = session.insert("insertErpSalaryMapping",salaryBreakUp);
								
							}
							if(insertStatus != 0){
								updateStatusForErp = session.update("updateStaffDetailsForErpSalaryMapping",employee);
							}
						} else {
							List<SalaryBreakUp> salaryBreakUpList = employee.getSalaryBreakUpList();							
							SalaryTemplate salaryTemplate = new SalaryTemplate();
							salaryTemplate.setSalaryTemplateCode(employee.getResource().getSalaryTemplateCode());
							
							
							updateStatusForSalaryAmount = session.update("updateStaffDetailsForErpSalaryAmount",employee);	
							if(updateStatusForSalaryAmount != 0){
								for (SalaryBreakUp salaryBreakUp : salaryBreakUpList) {
									salaryBreakUp.setSalaryBreakUpDesc(employee.getResource().getUserId());
									salaryBreakUp.setSalaryBreakUpObjectId(encryptDecrypt.encrypt("ErpDAOImpl"));
									salaryBreakUp.setSalaryBreakUpCode(employee.getResource().getDesignationType()+ ":"+ employee.getResource().getDesignation()+ ":" + employee.getResource().getDesignationLevel() + ":" + employee.getResource().getSalaryTemplateCode());
									salaryBreakUp.setAmount(salaryBreakUp.getAmount());
									salaryBreakUp.setSalaryBreakUpName(salaryBreakUp.getSalaryBreakUpName());
									salaryBreakUp.setSalaryTemplate(salaryTemplate);
									salaryBreakUp.setUpdatedBy(salaryBreakUp.getUpdatedBy());
									
									insertStatus = session.insert("insertErpSalaryMapping",salaryBreakUp);
								}
								if(insertStatus != 0){
									updateStatusForErp = session.update("updateStaffDetailsForErpSalaryMapping",employee);
								}
								
							}
						}
						
					}//End Update And Insert
					
					if(employee.getStatus().equalsIgnoreCase("Promote")){//Modified by naimisha 08082017
						List<SalaryBreakUp> salaryBreakUpList = employee.getSalaryBreakUpList();						
						SalaryTemplate salaryTemplate = new SalaryTemplate();
						salaryTemplate.setSalaryTemplateCode(employee.getResource().getSalaryTemplateCode());
						
						updatesalaryForPromotion = session.update("updateStaffDetailsForPromotion",employee);
						String ledgerCode = session.selectOne("SelectLedgerCodeAgainstResource", employee);
						System.out.println("ledgerCode==="+ledgerCode);
						employee.getResource().setLedger(ledgerCode);
						int updateSalaryTemplateMapping = session.update("updateSalaryTemplateResourceMappingForPromotion",employee);
						
						TemplateLedgerMapping templateLedgerMapping = new TemplateLedgerMapping();
						templateLedgerMapping.setObjectId(encryptDecrypt.encrypt("ErpDAOImpl"));
						templateLedgerMapping.setUpdatedBy(employee.getUpdatedBy());
						templateLedgerMapping.setSalaryTemplateDetailsCode(employee.getResource().getSalaryTemplateCode());
						templateLedgerMapping.setSalaryTemplateCode(employee.getResource().getUserId());
						templateLedgerMapping.setLedger(ledgerCode);
						int insertSalaryTemplateMapping =  session.insert("mapLedgerTemplateResource", templateLedgerMapping);
						if(updatesalaryForPromotion != 0){
							
							for (SalaryBreakUp salaryBreakUp : salaryBreakUpList) {
								salaryBreakUp.setSalaryBreakUpDesc(employee.getResource().getUserId());
								salaryBreakUp.setSalaryBreakUpObjectId(encryptDecrypt.encrypt("ErpDAOImpl"));
								salaryBreakUp.setSalaryBreakUpCode(employee.getResource().getDesignationType()+ ":"+ employee.getResource().getDesignation()+ ":" + employee.getResource().getDesignationLevel() + ":" + employee.getResource().getSalaryTemplateCode());
								salaryBreakUp.setAmount(salaryBreakUp.getAmount());
								salaryBreakUp.setSalaryBreakUpName(salaryBreakUp.getSalaryBreakUpName());
								salaryBreakUp.setSalaryTemplate(salaryTemplate);
								salaryBreakUp.setUpdatedBy(salaryBreakUp.getUpdatedBy());
								insertStatus = session.insert("insertErpSalaryMapping",salaryBreakUp);
								
							}
						}
						if(insertStatus != 0){
							updateStatusForErp = session.update("updateStaffDetailsForErpSalaryMapping",employee);
						}
					}//end promote
					
				} 
				session.commit();
			} catch (NullPointerException e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.error("Error In createDesignationSalaryMapping(Staff staff) method of BackOfficeDAOImpl:NullPointerException:: ",e);
				e.printStackTrace();
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.error("Error In createDesignationSalaryMapping(Staff staff) method of BackOfficeDAOImpl:Exception:: ",e);
				e.printStackTrace();
			} finally {
				session.close();
			}
			return employee;
		}
		
		@Override
		public List<SalaryBreakUp> getTaxDeductionAmount(String totalGross, String totalNet,Employee employee) {
			logger.info("In getSubmittedSlabTypeForMiscTax(String totalGross, String totalNet) method of ERPDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			List<SalaryBreakUp> taxList = new ArrayList<SalaryBreakUp>();
			Resource resourceObject = employee.getResource();
			
			
			List<SalaryBreakUp> salBreakUpListForSlab = session.selectList("getSalaryBreakUpNameForSlab");	
			for(SalaryBreakUp salObj : salBreakUpListForSlab){
				SalaryBreakUp salaryObject = new SalaryBreakUp();
				SalaryBreakUp salaryObjectForOut = new SalaryBreakUp();
				
				salaryObject.setSalaryBreakUpCode(salObj.getSalaryBreakUpCode());
				SalaryBreakUp calculatedOn = session.selectOne("getCalculatedOnSalaryBreakUp",salObj.getSalaryBreakUpCode());
				if(calculatedOn.getSalaryBreakUpName().equalsIgnoreCase("GROSS AMOUNT")){
					salaryObject.setAmount(Double.parseDouble(totalGross));
				}
				if(calculatedOn.getSalaryBreakUpName().equalsIgnoreCase("NET AMOUNT")){
					salaryObject.setAmount(Double.parseDouble(totalNet));
				}
				resourceObject.setSalaryBreakUp(salaryObject);
				if(!salObj.getSalaryBreakUpName().equalsIgnoreCase("INCOME TAX")){
					session.selectOne("calculatePfOrEsi", resourceObject);
					salaryObjectForOut.setAmount(resourceObject.getSalaryBreakUp().getAmount());
					salaryObjectForOut.setSlab(salObj.isSlab());
					salaryObjectForOut.setSalaryBreakUpCode(salObj.getSalaryBreakUpCode());
					salaryObjectForOut.setSalaryBreakUpType(salObj.getSalaryBreakUpType());
					salaryObjectForOut.setSalaryBreakUpName(salObj.getSalaryBreakUpName());
					
				}
				if(salObj.getSalaryBreakUpName().equalsIgnoreCase("INCOME TAX")){
					session.selectOne("calculateIncomeTax", resourceObject);
					salaryObjectForOut.setSlab(salObj.isSlab());
					salaryObjectForOut.setAmount(resourceObject.getSalaryBreakUp().getAmount());
					salaryObjectForOut.setSalaryBreakUpCode(salObj.getSalaryBreakUpCode());
					salaryObjectForOut.setSalaryBreakUpName(salObj.getSalaryBreakUpName());
					
				}
				taxList.add(salaryObjectForOut);
			}
			return taxList;
		}
		
		@Override
		public List<IncomeTaxParameters> getIncomeTaxParameter() {
			SqlSession session = sqlSessionFactory.openSession();
			List<IncomeTaxParameters> incomeTaxParameters = new ArrayList<>();
			try{
				incomeTaxParameters = session.selectList("getIncomeTaxParameter");
			}catch(Exception e){
				e.printStackTrace();
			}
			return incomeTaxParameters;
		}
		
		@Override
		public IncomeTaxSlabDetails getSlabCalculationParameter(String hidVal) {		
			IncomeTaxSlabDetails incomeTaxSlabDetails = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {
				logger.info("getSlabCalculationParameter() method in ERPDaoImpl");
				//System.out.println("HIDVALUE:"+hidVal);
				incomeTaxSlabDetails = session.selectOne("getSlabCalculationParameter", hidVal);
				if(incomeTaxSlabDetails != null){
					//System.out.println("dgdfgdf"+incomeTaxSlabDetails.getIncomeTaxbasedOn()+"|"+incomeTaxSlabDetails.getFinancialYear()+"|"+incomeTaxSlabDetails.getIncomeTaxSlabDetailsName());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getSubmittedSlabTypeForMiscTax() method in ERPDaoImpl", e);
			} finally {
				session.close();
			}
			return incomeTaxSlabDetails;
		}
		
		/**
		 * modified by ranita.sur
		 * changes taken on 10062017**/
		
		@Override
		public Employee getStaffSalaryDetails(Resource resource) {
			logger.info("In getStaffSalaryDetails(Resource resource) method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			Employee staffSalaryDetails = null;
			String actualWorkingDays = null;
			Set<String> setList = new HashSet<String>();
			List<SalaryBreakUp> taxList = new ArrayList<SalaryBreakUp>();
			int grossAmount = 0;
			int extraIncome = 0;
			int totalDeductedAmount = 0;
			int netAmount = 0;
			try {
				staffSalaryDetails = session.selectOne("getStaffDetails", resource);				
				if (staffSalaryDetails != null) {
					String existingStatus = session.selectOne("selectExistingStatusForDisburseSalaryDetails", resource);
					
					staffSalaryDetails.setStatus(existingStatus);
					SalaryBreakUp individualDeduction = session.selectOne("selectIndividualDeduction", resource);
					staffSalaryDetails.setIndividualDeduction(individualDeduction);
					List<SalaryBreakUp> salaryBreakUpListForGross  = session.selectList("getSalaryBreakUpListForGross", staffSalaryDetails);
					List<SalaryBreakUp> salaryBreakUpListForDeduction  = session.selectList("getSalaryBreakUpListForNet", staffSalaryDetails);
					
					for(SalaryBreakUp salObjForGross : salaryBreakUpListForGross){
						grossAmount = (int) (grossAmount + salObjForGross.getAmount());						
					}
					for(SalaryBreakUp salObjForNet : salaryBreakUpListForDeduction){
						totalDeductedAmount = (int) (totalDeductedAmount + salObjForNet.getAmount());
					}
					List<SalaryBreakUp> salBreakUpListForSlab = session.selectList("getSalaryBreakUpNameForSlab");	
					for(SalaryBreakUp salObj : salBreakUpListForSlab){
						SalaryBreakUp salaryObject = new SalaryBreakUp();
						SalaryBreakUp salaryObjectForOut = new SalaryBreakUp();
						
						salaryObject.setSalaryBreakUpCode(salObj.getSalaryBreakUpCode());
						System.out.println("salObj.getSalaryBreakUpCode()"+salObj.getSalaryBreakUpCode());
						SalaryBreakUp calculatedOn = session.selectOne("getCalculatedOnSalaryBreakUp",salObj.getSalaryBreakUpCode());
						if(null != calculatedOn){
							if(calculatedOn.getSalaryBreakUpName().equalsIgnoreCase("GROSS AMOUNT")){
								salaryObject.setAmount(grossAmount);
							}
							if(calculatedOn.getSalaryBreakUpName().equalsIgnoreCase("NET AMOUNT")){
								netAmount = grossAmount - totalDeductedAmount;
								salaryObject.setAmount(netAmount);
							}
						}
						
						resource.setSalaryBreakUp(salaryObject);
						if(!salObj.getSalaryBreakUpName().equalsIgnoreCase("INCOME TAX")){
							System.out.println(resource.getUserId());
							System.out.println(resource.getStartDate());
							System.out.println(resource.getEndDate());
							System.out.println(resource.getSalaryBreakUp().getSalaryBreakUpCode());
							System.out.println(resource.getSalaryBreakUp().getAmount());
							session.selectOne("calculatePfOrEsi", resource);
							salaryObjectForOut.setAmount(resource.getSalaryBreakUp().getAmount());
							salaryObjectForOut.setSlab(salObj.isSlab());
							salaryObjectForOut.setSalaryBreakUpCode(salObj.getSalaryBreakUpCode());
							salaryObjectForOut.setSalaryBreakUpType(salObj.getSalaryBreakUpType());
							salaryObjectForOut.setSalaryBreakUpName(salObj.getSalaryBreakUpName());
							
						}
						if(salObj.getSalaryBreakUpName().equalsIgnoreCase("INCOME TAX")){
							session.selectOne("calculateIncomeTax", resource);
							salaryObjectForOut.setSlab(salObj.isSlab());
							salaryObjectForOut.setAmount(resource.getSalaryBreakUp().getAmount());
							salaryObjectForOut.setSalaryBreakUpCode(salObj.getSalaryBreakUpCode());
							salaryObjectForOut.setSalaryBreakUpName(salObj.getSalaryBreakUpName());
							
						}
						taxList.add(salaryObjectForOut);
					}
					List<SalaryBreakUp> salaryBreakUpList = session.selectList("getDesignationSalaryMappingDetails", staffSalaryDetails);
					if (salaryBreakUpList != null && salaryBreakUpList.size() != 0) {
						salaryBreakUpList.addAll(taxList);
						//System.out.println("ABCasas:"+salaryBreakUpList.size());
						staffSalaryDetails.setSalaryBreakUpList(salaryBreakUpList);
					}
					List<Leave> staffLeaveDetails = session.selectList("selectResourceLeaveDetails", resource);					
					staffSalaryDetails.setLeaveList(staffLeaveDetails);			
					if (resource != null) {	
						actualWorkingDays = resource.getStatus();
					}				
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				logger.error("Error In getStaffSalaryDetails(Resource resource) method of BackOfficeDAOImpl:NullPointerException:: "+ e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error In getStaffSalaryDetails(Resource resource) method of BackOfficeDAOImpl :Exception:: "+ e);
			} finally {
				session.close();
			}
			return staffSalaryDetails;
		}
		
		/**ranita.sur 04072017*/
		
		@Override
		public String saveStaffSalaryDetails(Employee staff) {
			SqlSession session = sqlSessionFactory.openSession();
			String status="fail";
			int statusValue=0;
			try{
				logger.info("saveStaffSalaryDetails(Staff staff) method in ErpDaoImpl");	
				staff.setObjectId(encryptDecrypt.encrypt("ErpDaoImpl"));
				System.out.println("////LEdger In Dao::"+staff.getComment());
				int insertStatus = session.insert("insertStaffSalaryDetails", staff);
				int insertStatus1= session.update("updateLedgerBalanceForStaff", staff);
			   if(insertStatus!=0){
					staff.setPaymentMode("CASH");
					 
					session.insert("insertIntoTransactionalWorkingAreaForStaffSalary", staff);
					List<SalaryBreakUp> salaryBreakUpList = session.selectList("getDesignationSalaryMappingDetails", staff);
					List<SalaryBreakUp> extraIncome = staff.getExtraEarningList();
					for(SalaryBreakUp sal : extraIncome){
						SalaryBreakUp SalaryBreakUpObj = new SalaryBreakUp();
						SalaryBreakUpObj.setSalaryBreakUpCode(sal.getSalaryBreakUpCode());
						SalaryBreakUpObj.setSalaryBreakUpName(sal.getSalaryBreakUpName());
						SalaryBreakUpObj.setAmount(sal.getAmount());
						SalaryBreakUpObj.setStatus(sal.getStatus());
						salaryBreakUpList.add(SalaryBreakUpObj);
					}
					staff.setSalaryBreakUpList(salaryBreakUpList);
					
					session.insert("insertIntoTransactionalWorkingAreaDetailsForStaffSalary", staff);
					 
					/*if(insertStatus1!=0)
					{
						statusValue=session.update("editinLedgerBalanceForEmployeeDetails",staff);
					}*/
					session.insert("insertIntoStaffSalaryDetails", staff);
				}
				session.commit();
				if(insertStatus != 0){
					status = "success";
		        }
			}catch(Exception e){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.error(" Exception in From getSalaryBreakUpForTemplate(SalaryTemplate salaryTemplate) of ErpDaoImpl :",e);
				e.printStackTrace();
			}finally{
				session.close(); 
			}
			return status;
		}


		@Override
		public List<DesignationLevel> getDesignationLevelListForDesignation(String designationName) {			
			List<DesignationLevel> designationLevelList = null;
			SqlSession session = sqlSessionFactory.openSession();
			try {				
				logger.info("Executing getDesignationLevelListForDesignation() from ERPDaoImpl");			
				designationLevelList = session.selectList("getDesignationLevelListForDesignation", designationName);						
			} catch (Exception e) {
				logger.error("Exception occured while executing getDesignationForResourceType() from ERPDAOImpl", e);
			} finally {
				session.close();
			}
			return designationLevelList;
		}
		

	/**
	 * done by naimisha
	 * changes taken on 11 jan 2017
	 * **/	
	
		/*Done by naimisha for erp*/
		@Override
		public Staff getTeacherSubjectForMapping() {
			logger.info("In getTeacherSubjectForMapping() method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			Staff teacherIdSubjects = new Staff();
			try {
				List<Resource> teacherIdList = session.selectList("selectTeacherId");
				if (teacherIdList != null && teacherIdList.size() != 0) {
					teacherIdSubjects.setResourceList(teacherIdList);
				}
				List<SubjectGroup> subjectGroup = session.selectList("selectSubjectGroupList");
				/*if (subjectGroup != null && subjectGroup.size() != 0) {
					for (SubjectGroup sg : subjectGroup) {
						List<Subject> subjectListForSubjectGroup = session.selectList("selectSubjectAndCategory", sg);
						sg.setSubjectList(subjectListForSubjectGroup);
					}
				}*/
				SubjectList subjectListObj = new SubjectList();
				subjectListObj.setListSubjectGroup(subjectGroup);
				teacherIdSubjects.setSubjectListObj(subjectListObj);
			} catch (NullPointerException e) {
				logger.error("Error In getTeacherSubjectForMapping() method of ErpDAOImpl:NullPointerException:: ", e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("Error In getTeacherSubjectForMapping() method of ErpDAOImpl:Exception:: ",e);
				e.printStackTrace();
			} finally {
				session.close();
			}
			return teacherIdSubjects;
		}


		@Override
		public Staff saveTeacherSubjectMapping(Staff staff) {
			logger.info("In saveTeacherSubjectMapping(Staff staff) method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			try {
				Utility util = new Utility();
				if (staff != null
						&& staff.getSubjectListObj() != null
						&& staff.getSubjectListObj().getListSubjectGroup() != null
						&& staff.getSubjectListObj().getListSubjectGroup().size() != 0) {
					for (SubjectGroup sg : staff.getSubjectListObj().getListSubjectGroup()) {
						if (sg.getSubjectList() != null && sg.getSubjectList().size() != 0) {
							for (Subject s : sg.getSubjectList()) {
								if (s != null && s.getSubjectName() != null && s.getSubjectName().trim().length() != 0) {
									s.setSubjectCode(sg.getSubjectGroupName() + ":"+ s.getSubjectName() + ":"+ staff.getResource().getUserId());
									s.setSubjectDesc(sg.getSubjectGroupName());
									s.setStatus(staff.getTeachingLevel().getTeachingLevelName());
									s.setSubjectObjectId(util.getBase64EncodedID("ErpDAOImpl"));
									s.setResource(staff.getResource());
									session.insert("insertTeacherSubjectMapping", s);
									session.commit();
								}
							}
						}
					}
				}
			} catch (NullPointerException e) {
				logger.error("Error In saveTeacherSubjectMapping(Staff staff) method of ErpDAOImpl:NullPointerException:: ",e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("Error In saveTeacherSubjectMapping(Staff staff) method of ErpDAOImpl:Exception:: ",e);
				e.printStackTrace();
			} finally {
				session.close();
			}
			return getTeacherSubjectForMapping();
		}


		@Override
		public String getTeacherSubjectMappping(String strStaffUserId) {
			String subjects="";
			SqlSession session =sqlSessionFactory.openSession();
			try{
				List<Subject> subjectList=session.selectList("selectSubjectsForATeacher", strStaffUserId);
				if(null!=subjectList && subjectList.size()!=0){
					for(Subject subject:subjectList){
						subjects=subjects+subject.getSubjectGroup()+"#@#"+subject.getSubjectCode()+"*~*";
					}
				}
			}catch(Exception e) {
				logger.error(e);
				//CustomException.exceptionHandler(e);
			}finally{
				session.close();
			}
			//System.out.println("subjects===0"+subjects);
			return subjects;
		}


		@Override
		public String submitTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) {
			String status="success";
			SqlSession session =sqlSessionFactory.openSession();
			try{
			//	String teacherId = teacherSubjectMapping.getObjectId();
				teacherSubjectMapping.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
				if(null!=teacherSubjectMapping.getOldSubjectList() && teacherSubjectMapping.getOldSubjectList().size()!=0){
					session.update("inactiveTeacherSubjectMapping", teacherSubjectMapping);
				}
				if(null!=teacherSubjectMapping.getNewSubjectList() && teacherSubjectMapping.getNewSubjectList().size()!=0){
					for(String newSubject:teacherSubjectMapping.getNewSubjectList()){
						teacherSubjectMapping.setSubject(newSubject);
						TeacherSubjectMapping teacherSubjectMappingCheckParam = new TeacherSubjectMapping();
						teacherSubjectMappingCheckParam.setSubject(newSubject);
						//teacherSubjectMappingCheckParam.setCourseCode(teacherSubjectMappingCheckParam.getCourseCode());
						teacherSubjectMappingCheckParam.setStatus(teacherSubjectMapping.getStatus());
						TeacherSubjectMapping checker=session.selectOne("selectInactiveSubjectForTeacher",teacherSubjectMappingCheckParam);
						if(null!=checker && null!=checker.getSubject()){
							session.update("updateTeacherSubjectMapping", teacherSubjectMapping);
						}else{
							int insertStatus = session.insert("insertIntoTeacherSubjectMapping", teacherSubjectMapping);
							//System.out.println("insertStatus=="+insertStatus);
						}
					}
				}
				
			}catch(Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				status="fail";
				logger.error(e);
				e.printStackTrace();
				//CustomException.exceptionHandler(e);
			}finally{
				session.close();
			}
			return status;
		}


		@Override
		public List<Resource> getTeachersFromTeacherSubjectMappingList() {
			logger.info("In getTeacherSubjectMappingList() method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			List<Resource> teacherIdName = null;
			try {
				teacherIdName = session.selectList("selectTeacherIdName");
			} catch (NullPointerException e) {
				logger.error("Error In getTeacherSubjectMappingList() method of BackOfficeDAOImpl:NullPointerException:: "+ e);
			} catch (Exception e) {
				logger.error("Error In getTeacherSubjectMappingList() method of BackOfficeDAOImpl:Exception:: "+ e);
			} finally {
				session.close();
			}
			return teacherIdName;
		}


		@Override
		public Staff getTeacherSubjectMappingForEdit(Staff staff) {
			logger.info("In getTeacherSubjectMappingForEdit() method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			String teacherId = staff.getResource().getUserId();
			try {
				Employee emp = session
						.selectOne("selectTeacherNameDesignation", teacherId);
				// selectTeacherQualification id used for get Qualifications for a
				// teacher
				Resource resource = new Resource();
				resource.setFirstName(emp.getResource().getFirstName());
				resource.setMiddleName(emp.getResource().getMiddleName());
				resource.setLastName(emp.getResource().getLastName());
				staff.setResource(resource);
				staff.setJobType(emp.getJobType().getJobTypeName());
				staff.setDesignation(emp.getDesignation().getDesignationName());
				List<Qualification> qualificationList = session.selectList(
						"selectTeacherQualification", teacherId);
				if (qualificationList != null) {
					staff.setQualificationList(qualificationList);
				}
				List<SubjectGroup> subjectGroup = session
						.selectList("selectAllSubjectGroup");

				if (subjectGroup != null && subjectGroup.size() != 0) {
					logger.info("IN ErpDAOImpl getTeacherSubjectMappingForEdit() method: SIZE of subjectTypeList: "
							+ subjectGroup.size());
					for (SubjectGroup sg : subjectGroup) {
						logger.info("IN ErpDAOImpl getTeacherSubjectMappingForEdit() method: getSubjectGroupName:"
								+ sg.getSubjectGroupName());
						List<Subject> subjectListByDefault = session.selectList(
								"selectSubjectList");
						staff.setStaffObjectId(sg.getSubjectGroupName().trim());// set
																				// SubjectGroupName
																				// into
																				// staffObjectId
																				// for
																				// a
																				// teacher
																				// id.
						staff.setStaffCode(teacherId.trim());// set teacher userId
																// into staffCode .
						String strStaffUserId = teacherId;
						List<Subject> subjectListByTeacher = session.selectList(
								"selectSubjectsForATeacher", strStaffUserId);
						Map<String, Object> subjectListByTeacherMap = new HashMap<String, Object>();
						for (Subject subject : subjectListByTeacher) {
							subjectListByTeacherMap.put(subject.getSubjectName(),
									subject.getSubjectName());
						}
						if (subjectListByDefault != null
								&& subjectListByDefault.size() != 0) {
							for (Subject subject : subjectListByDefault) {
								if (subjectListByTeacherMap != null
										&& subjectListByTeacherMap.size() != 0) {
									if (subjectListByTeacherMap.containsKey(subject
											.getSubjectName())) {
										subject.setStatus("Checked");
									}
								}
							}
						}
						sg.setSubjectList(subjectListByDefault);
					}
				}
				SubjectList subjectListObj = new SubjectList();
				subjectListObj.setListSubjectGroup(subjectGroup);
				staff.setSubjectListObj(subjectListObj);
				staff.getResource().setUserId(teacherId);
				
			} catch (NullPointerException e) {
				logger.error(
						"Error In getTeacherSubjectMappingList() method of ErpDAOImpl:NullPointerException:: ",
						e);
				e.printStackTrace();
			} catch (Exception e) {
				logger.error(
						"Error In getTeacherSubjectMappingList() method of ErpDAOImpl:Exception:: ",
						e);
				e.printStackTrace();
			} finally {
				session.close();
			}
			return staff;//Naimisha naimisha
		}


		@Override
		public String submitStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) {
			String status="success";
			SqlSession session =sqlSessionFactory.openSession();
			try{
					teacherSubjectMapping.setObjectId(encryptDecrypt.getBase64EncodedID("ERPDAOImpl"));
					int insertStatus = session.insert("insertIntoStandardTeacherSubjectMapping", teacherSubjectMapping);
					//System.out.println("insertStatus=="+insertStatus);
				
			}catch(Exception e) {
				//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				status="fail";
				logger.error(e);
				e.printStackTrace();
				//CustomException.exceptionHandler(e);
			}finally{
				session.close();
			}
			return status;
		}


		@Override
		public List<TeacherSubjectMapping> getTeachersFromStandardTeacherSubjectMappingList() {
			logger.info("In getTeachersFromStandardTeacherSubjectMappingList() method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			List<TeacherSubjectMapping> standardTeacherSubjectMappingList = null;
			try {
				standardTeacherSubjectMappingList = session.selectList("selectTeachertsFromStandardTeacherSubjectMapping");
			} catch (NullPointerException e) {
				logger.error("Error In getTeachersFromStandardTeacherSubjectMappingList() method of ERPDAOImpl:NullPointerException:: "+ e);
			} catch (Exception e) {
				logger.error("Error In getTeachersFromStandardTeacherSubjectMappingList() method of ERPDAOImpl:Exception:: "+ e);
			} finally {
				session.close();
			}
			return standardTeacherSubjectMappingList;
		}


		@Override
		public String editStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) {
			String status="success";
			SqlSession session =sqlSessionFactory.openSession();
			try{
			//	String teacherId = teacherSubjectMapping.getObjectId();
				teacherSubjectMapping.setObjectId(encryptDecrypt.getBase64EncodedID("ERPDAOImpl"));
				session.update("updateStandardTeacherSubjectMapping", teacherSubjectMapping);
					
			}catch(Exception e) {
				///TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				status="fail";
				logger.error(e);
				e.printStackTrace();
				//CustomException.exceptionHandler(e);
			}finally{
				session.close();
			}
			return status;
		}


		@Override
		public String deleteStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) {
			String status="success";
			SqlSession session =sqlSessionFactory.openSession();
			try{
			//	String teacherId = teacherSubjectMapping.getObjectId();
				teacherSubjectMapping.setObjectId(encryptDecrypt.getBase64EncodedID("ERPDAOImpl"));
				session.update("deleteStandardTeacherSubjectMapping", teacherSubjectMapping);
					
			}catch(Exception e) {
				///TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				status="fail";
				logger.error(e);
				e.printStackTrace();
				//CustomException.exceptionHandler(e);
			}finally{
				session.close();
			}
			return status;
		}

		@Override
		public List<EmployeeType> getAllEmployeeType() {
			SqlSession session =sqlSessionFactory.openSession();
			List<EmployeeType> empTypeList = null;
			try{
				logger.info("Executing getAllEmployeeType() from ERPDaoImpl");
				empTypeList = session.selectList("selectAllEmployeeType");
			}catch(Exception e){
				logger.error("Exception occured while executing getAllEmployeeType() from ERPDAOImpl", e);	
			}
			return empTypeList;
		}
		
		
		@Override
		public String updateHodForDepartment(Department department) {
			SqlSession session = sqlSessionFactory.openSession();
			String status = "";
			int statusValue=0;
			try {
				
				 statusValue = session.update("editHodDetailsForDepartment", department);
				
				if (statusValue != 0) {
					status = "Success";
				}
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				status="Fail";
			} catch (Exception e) {
				e.printStackTrace();
				status="Fail";
			} finally {
				session.close();
			}
			return status;
		}
		
		/**
		 * @author naimisha.ghosh
		 * 06072017
		 * */		
		
		@Override
		public String getAllUserIdList(String parent) {
			logger.info("In getAllUserIdList() method of BackOfficeDAOImpl: ");
			SqlSession session = sqlSessionFactory.openSession();
			List<Resource> userIdList = null;
			String resourceType = parent;
			String userIdDetails = "";
			try {
				userIdList = session.selectList("selectAllUserIdAgainstResourceType",resourceType);
				for(Resource r:userIdList)
				{
					userIdDetails +=r.getUserId()+"*"+r.getName()+"~";
				}
			} catch (NullPointerException e) {
				logger.error("Error In getTeachersFromStandardTeacherSubjectMappingList() method of ERPDAOImpl:NullPointerException:: "+ e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error In getTeachersFromStandardTeacherSubjectMappingList() method of ERPDAOImpl:Exception:: "+ e);
			} finally {
				session.close();
			}
			return userIdDetails;
		}
		
		@Override
		public List<Department> getMapDepartmentWithResourceType() {
			SqlSession session =sqlSessionFactory.openSession();
			List<Department> deptMapDetailsList = null;
			try{
				logger.info("Executing getAllDepartment() from ERPDaoImpl");
				
				deptMapDetailsList = session.selectList("selectAllMappedDepartmentHod");
				System.out.println("3121:"+deptMapDetailsList);
				System.out.println("3120::"+deptMapDetailsList.size());
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception occured while executing getAllDepartment() from ERPDAOImpl", e);	
			}
			return deptMapDetailsList;
		}
		
		@Override
		public String saveUpdateDetails(Department department) {
			SqlSession session = sqlSessionFactory.openSession();
			String status = "";
			int statusValue=0;
			try {
				System.out.println("3052"+department.getDepartmentCode()+" " +department.getDepartmentHead());
				/*for(Department d:department){*/
				 statusValue = session.update("saveHodDetailsForDepartment", department);
			/*	}*/
				if (statusValue != 0) {
					status = "Success";
				}
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				status = "Fail";
			} catch (Exception e) {
				e.printStackTrace();
				status = "Fail";
			} finally {
				session.close();
			}
			return status;
		}

		
		//Added By Naimisha 31072017
		@Override
		public List<Resource> getResourceDetailsForSalary(Resource resource) {
			SqlSession session =sqlSessionFactory.openSession();
			List<Resource> resourceDetailsForSalaryList = null;
			try{
				logger.info("Executing getResourceDetailsForSalary() from ERPDaoImpl");
				
				resourceDetailsForSalaryList = session.selectList("selectResourceDetailsForSalaryDisburse",resource);
				System.out.println("3121:"+resourceDetailsForSalaryList.toString());
				System.out.println("3120::"+resourceDetailsForSalaryList.size());
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception occured while executing getResourceDetailsForSalary() from ERPDAOImpl", e);	
			}
			return resourceDetailsForSalaryList;
		}

		@Override
		public String saveStaffSalaryDetailsNew(List<Resource> resourceList) {

			SqlSession session = sqlSessionFactory.openSession();
			String status="success";
			
			Employee staffSalaryDetails = new Employee();
			double grossAmount = 0.0;
			
			double totalDeductedAmount = 0.0;
			
			List<SalaryBreakUp> allSalaryBreakUpList = new ArrayList<SalaryBreakUp>();
			try{
				logger.info("saveStaffSalaryDetailsNew(List<Resource> resourceList) method in ErpDaoImpl");	
				for(Resource resource : resourceList){
					
					staffSalaryDetails.setResource(resource);
					String existingStatus = session.selectOne("selectExistingStatusForDisburseSalaryDetails", resource);
					
					staffSalaryDetails.setStatus(existingStatus);
					if(null == existingStatus){
						
						SalaryBreakUp individualDeduction = session.selectOne("selectIndividualDeduction", resource);
						staffSalaryDetails.setIndividualDeduction(individualDeduction);
						
						List<SalaryBreakUp> salaryBreakUpListForGross  = session.selectList("getSalaryBreakUpListForGross", staffSalaryDetails);
						List<SalaryBreakUp> salaryBreakUpListForDeduction  = session.selectList("getSalaryBreakUpListForNet", staffSalaryDetails);
						List<SalaryBreakUp> mannualSalaryBreakUpList = resource.getSalaryBreakUpList();
						
						
						for(SalaryBreakUp salObjForGross : salaryBreakUpListForGross){
							
							SalaryBreakUp salaryBreakUp = new SalaryBreakUp();
							salaryBreakUp.setAmount(salObjForGross.getAmount());
							salaryBreakUp.setSalaryBreakUpCode(salObjForGross.getSalaryBreakUpCode());
							salaryBreakUp.setSalaryBreakUpName(salObjForGross.getSalaryBreakUpName());
							salaryBreakUp.setSalaryBreakUpType(salObjForGross.getSalaryBreakUpType());
							allSalaryBreakUpList.add(salaryBreakUp);
							grossAmount = (grossAmount + salObjForGross.getAmount());						
						}
						for(SalaryBreakUp salObjForNet : salaryBreakUpListForDeduction){
							SalaryBreakUp salaryBreakUp = new SalaryBreakUp();
							salaryBreakUp.setAmount(salObjForNet.getAmount());
							salaryBreakUp.setSalaryBreakUpCode(salObjForNet.getSalaryBreakUpCode());
							salaryBreakUp.setSalaryBreakUpName(salObjForNet.getSalaryBreakUpName());
							salaryBreakUp.setSalaryBreakUpType(salObjForNet.getSalaryBreakUpType());
							allSalaryBreakUpList.add(salaryBreakUp);
							totalDeductedAmount = (totalDeductedAmount + salObjForNet.getAmount());
						}
						
						if(null != mannualSalaryBreakUpList){
							for(SalaryBreakUp salObjForMannual : mannualSalaryBreakUpList){
								SalaryBreakUp salaryBreakUp = new SalaryBreakUp();
								salaryBreakUp.setAmount(salObjForMannual.getAmount());
								salaryBreakUp.setSalaryBreakUpCode(salObjForMannual.getSalaryBreakUpCode());
								salaryBreakUp.setSalaryBreakUpName(salObjForMannual.getSalaryBreakUpName());
								salaryBreakUp.setSalaryBreakUpType(salObjForMannual.getSalaryBreakUpName());
								allSalaryBreakUpList.add(salaryBreakUp);
								totalDeductedAmount = totalDeductedAmount + salObjForMannual.getAmount();
							}
						}
						staffSalaryDetails.setGrossAmount(grossAmount);
						staffSalaryDetails.setNetAmount(grossAmount - totalDeductedAmount);
						staffSalaryDetails.setObjectId(encryptDecrypt.encrypt("ErpDaoImpl"));
						
						
						/*For Tax calculation 14082017*/
						
						List<SalaryBreakUp> salBreakUpListForSlab = session.selectList("getSalaryBreakUpNameForSlab");	//GETTING salaryBREAKUP with slab
						for(SalaryBreakUp salObj : salBreakUpListForSlab){
							SalaryBreakUp salaryObject = new SalaryBreakUp();
							SalaryBreakUp salaryObjectForOut = new SalaryBreakUp();
							
							salaryObject.setSalaryBreakUpCode(salObj.getSalaryBreakUpCode());
							System.out.println("salObj.getSalaryBreakUpCode()"+salObj.getSalaryBreakUpCode());
							SalaryBreakUp calculatedOn = session.selectOne("getCalculatedOnSalaryBreakUp",salObj.getSalaryBreakUpCode());
							if(null != calculatedOn){
								if(calculatedOn.getSalaryBreakUpName().equalsIgnoreCase("GROSS AMOUNT")){
									salaryObject.setAmount(grossAmount);
								}
								if(calculatedOn.getSalaryBreakUpName().equalsIgnoreCase("NET AMOUNT")){
									
									salaryObject.setAmount(grossAmount - totalDeductedAmount);
								}
							}
							
							resource.setSalaryBreakUp(salaryObject);
							if(!salObj.getSalaryBreakUpName().equalsIgnoreCase("INCOME TAX")){
								System.out.println(resource.getUserId());
								System.out.println(resource.getStartDate());
								System.out.println(resource.getEndDate());
								System.out.println(salObj.getSalaryBreakUpCode());
								System.out.println(salaryObject.getAmount());
								session.selectOne("calculatePfOrEsi", resource);
								salaryObjectForOut.setAmount(resource.getSalaryBreakUp().getAmount());
								salaryObjectForOut.setSlab(salObj.isSlab());
								salaryObjectForOut.setSalaryBreakUpCode(salObj.getSalaryBreakUpCode());
								salaryObjectForOut.setSalaryBreakUpType(salObj.getSalaryBreakUpType());
								salaryObjectForOut.setSalaryBreakUpName(salObj.getSalaryBreakUpName());
								staffSalaryDetails.setNetAmount(staffSalaryDetails.getNetAmount()- resource.getSalaryBreakUp().getAmount());
								
							}
							if(salObj.getSalaryBreakUpName().equalsIgnoreCase("INCOME TAX")){
								session.selectOne("calculateIncomeTax", resource);
								salaryObjectForOut.setSlab(salObj.isSlab());
								salaryObjectForOut.setAmount(resource.getSalaryBreakUp().getAmount());
								salaryObjectForOut.setSalaryBreakUpCode(salObj.getSalaryBreakUpCode());
								salaryObjectForOut.setSalaryBreakUpName(salObj.getSalaryBreakUpName());
								staffSalaryDetails.setNetAmount(staffSalaryDetails.getNetAmount()- resource.getSalaryBreakUp().getAmount());
							}
							allSalaryBreakUpList.add(salaryObjectForOut);
						}
						
						//Naimisha tax ends 14082017
						
						staffSalaryDetails.setSalaryBreakUpList(allSalaryBreakUpList);
						
						int workingDays = 0;
						int absentDay = 0;
						 String  totalWorkingDays =  session.selectOne("selectTotalWorkingDaysForAMonth", resource);
						 
						 if(null != totalWorkingDays){
							 workingDays = Integer.parseInt(totalWorkingDays);
						 }
						 
						 String month = resource.getEndDate();
						 String monthValue = month.substring(1);
						 resource.setEndDate(monthValue);
						 String totalAbsentDays = session.selectOne("selectTotalAbsentDaysForAMonth",resource);
						 if(null != totalAbsentDays){
							 absentDay = Integer.parseInt(totalAbsentDays);
						 }
						 ResourceAttendence resourceAttendance = new ResourceAttendence();
						 resourceAttendance.setTotalDays(workingDays);
						 resourceAttendance.setPresentDays(workingDays - absentDay);
						 staffSalaryDetails.setResourceAttendance(resourceAttendance);
						 
						 resource.setEndDate(month);
						int insertStatus = session.insert("insertStaffSalaryDetails", staffSalaryDetails);
						
						int insertstatus = session.insert("insertStaffSalaryBreakupDetails", staffSalaryDetails);
						
					//	int insertStatus1= session.update("updateLedgerBalanceForStaff", staffSalaryDetails);
						
						/********* Add Naimisha 17/04/2018 *********/
						TransactionDetails trd = new TransactionDetails();
						trd.setUpdatedBy(resource.getUpdatedBy());
						trd.setObjectId(encryptDecrypt.encrypt("ErpDaoImpl"));
						
						
						String employeeLedger = resource.getUserId();
						Ledger employeeLedgerBalance = session.selectOne("selectBalanceForParentLedger", employeeLedger);
						trd.setLedger(employeeLedger);
						trd.setGrossAmount(employeeLedgerBalance.getOpeningBal());
						
						if(employeeLedgerBalance.getCurrentBal() < 0 ){
							/*if current balance is -ve then in debit side
							 * credit on debit = subtract */
							trd.setOnbasic(employeeLedgerBalance.getCurrentBal() - staffSalaryDetails.getNetAmount());
							System.out.println("LN537 :: Employee -ve(debit)  Final Amount :: "+trd.getOnbasic());
							session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
						}else{
							/*if current balance is -ve then in credit side
							 * credit on credit = add */
							trd.setOnbasic(employeeLedgerBalance.getCurrentBal() + staffSalaryDetails.getNetAmount());
							System.out.println("LN543 :: Employee +ve(credit)  Final Amount :: "+trd.getOnbasic());
							session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
						}
						//need discussion
						Transaction transaction = new Transaction();
						transaction.setUpdatedBy(resource.getUpdatedBy());
						transaction.setVoucherTypeCode("JOURNAL");
						transaction.setObjectId(encryptDecrypt.encrypt("ErpDaoImpl"));
						
						
						
						String pattern = "dd-MM-yyyy";
						String dateInString = new SimpleDateFormat(pattern).format(new Date());
						transaction.setNarration("Salary Disburse");
						transaction.setTransactionDate(dateInString);
						
						
						List<TransactionDetails>trDetList = new ArrayList<>();
						TransactionDetails transactionDetailsObj = new TransactionDetails();
						transactionDetailsObj.setLedger(employeeLedger);
						transactionDetailsObj.setAmount(grossAmount);
						transactionDetailsObj.setDebit(false);
						
						trDetList.add(transactionDetailsObj);
						
						transaction.setTrDetList(trDetList);
						int transactionStatus = session.insert("createTransaction",transaction);
						
						List<TransactionDetails>transactionDetailsList = new ArrayList<>();
						
						
						//*************For Deduction type salary breakup****************//
						for(SalaryBreakUp salObjForNet : salaryBreakUpListForDeduction){
							
							Transaction transaction2 = new Transaction();
							transaction2.setUpdatedBy(resource.getUpdatedBy());
							transaction2.setVoucherTypeCode("JOURNAL");
							transaction2.setObjectId(encryptDecrypt.encrypt("ErpDaoImpl"));
							
							
							
							//String pattern = "dd-MM-yyyy";
							String dateString = new SimpleDateFormat(pattern).format(new Date());
							transaction2.setNarration("Salary Disburse");
							transaction2.setTransactionDate(dateString);
							
							
							TransactionDetails transactionDetails = new TransactionDetails();
							transactionDetails.setUpdatedBy(resource.getUpdatedBy());
							transactionDetails.setObjectId(encryptDecrypt.encrypt("ErpDaoImpl"));
							String salaryBreakUpCode = salObjForNet.getSalaryBreakUpCode();
							Ledger ledger = session.selectOne("selectLedgerForASalaryBreakUp", salaryBreakUpCode);
							if(null != ledger){
								String deductionLedger = ledger.getLedgerCode();
								Ledger deductionLedgerBalance = session.selectOne("selectBalanceForParentLedger", deductionLedger);
								trd.setLedger(deductionLedger);
								trd.setGrossAmount(deductionLedgerBalance.getOpeningBal());
								
								
								trd.setOnbasic(deductionLedgerBalance.getCurrentBal() + salObjForNet.getAmount());
								System.out.println("LN543 :: Employee +ve(credit)  Final Amount :: "+trd.getOnbasic());
								session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
								
							/*	/For debit in salary breakup ledger/*/
								TransactionDetails transactionDetailsObj2 = new TransactionDetails();
								transactionDetailsObj2.setLedger(deductionLedger);
								transactionDetailsObj2.setAmount(salObjForNet.getAmount());
								transactionDetailsObj2.setDebit(false);
								
								/*	/For credit in employee ledger/*/
								TransactionDetails transactionDetailsObj3 = new TransactionDetails();
								transactionDetailsObj3.setLedger(employeeLedger);
								transactionDetailsObj3.setAmount(salObjForNet.getAmount());
								transactionDetailsObj3.setDebit(true);
								
								
								transactionDetailsList.add(transactionDetailsObj2);
								transactionDetailsList.add(transactionDetailsObj3);
							}
							transaction2.setTrDetList(transactionDetailsList);
							int transactionStatus1 = session.insert("createTransaction",transaction2);
						}
						
						 //You need to modify here
						//*************For Mannual type salary breakup****************//
						
						List<TransactionDetails>transactionDetailsList2 = new ArrayList<>();
						for(SalaryBreakUp salObjForMannual : mannualSalaryBreakUpList){
							
							Transaction transaction2 = new Transaction();
							transaction2.setUpdatedBy(resource.getUpdatedBy());
							transaction2.setVoucherTypeCode("JOURNAL");
							transaction2.setObjectId(encryptDecrypt.encrypt("ErpDaoImpl"));
							
							
							
							//String pattern = "dd-MM-yyyy";
							String dateString = new SimpleDateFormat(pattern).format(new Date());
							transaction2.setNarration("Salary Disburse");
							transaction2.setTransactionDate(dateString);
							
							
							TransactionDetails transactionDetails = new TransactionDetails();
							transactionDetails.setUpdatedBy(resource.getUpdatedBy());
							transactionDetails.setObjectId(encryptDecrypt.encrypt("ErpDaoImpl"));
							String salaryBreakUpCode = salObjForMannual.getSalaryBreakUpCode();
							Ledger ledger = session.selectOne("selectLedgerForASalaryBreakUp", salaryBreakUpCode);
							if(null != ledger){
								String deductionLedger = ledger.getLedgerCode();
								Ledger deductionLedgerBalance = session.selectOne("selectBalanceForParentLedger", deductionLedger);
								trd.setLedger(deductionLedger);
								trd.setGrossAmount(deductionLedgerBalance.getOpeningBal());
								
								
								trd.setOnbasic(deductionLedgerBalance.getCurrentBal() + salObjForMannual.getAmount());
								System.out.println("LN543 :: Employee +ve(credit)  Final Amount :: "+trd.getOnbasic());
								session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
								
							/*	/For debit in salary breakup ledger/*/
								TransactionDetails transactionDetailsObj2 = new TransactionDetails();
								transactionDetailsObj2.setLedger(deductionLedger);
								transactionDetailsObj2.setAmount(salObjForMannual.getAmount());
								transactionDetailsObj2.setDebit(false);
								
								/*	/For credit in employee ledger/*/
								TransactionDetails transactionDetailsObj3 = new TransactionDetails();
								transactionDetailsObj3.setLedger(employeeLedger);
								transactionDetailsObj3.setAmount(salObjForMannual.getAmount());
								transactionDetailsObj3.setDebit(true);
								
								
								transactionDetailsList2.add(transactionDetailsObj2);
								transactionDetailsList2.add(transactionDetailsObj3);
							}
							transaction2.setTrDetList(transactionDetailsList2);
							int transactionStatus1 = session.insert("createTransaction",transaction2);
						}
						
						
					
						/************End Naimisha 17/04/2018*************/
						
						if(insertStatus!=0){
							//staff.setPaymentMode("CASH");
							List<SalaryBreakUp> salaryBreakUpList = session.selectList("getDesignationSalaryMappingDetails", staffSalaryDetails);
							staffSalaryDetails.setSalaryBreakUpList(salaryBreakUpList);
							session.insert("insertIntoTransactionalWorkingAreaForStaffSalary", staffSalaryDetails);
						/*	List<SalaryBreakUp> salaryBreakUpList = session.selectList("getDesignationSalaryMappingDetails", staffSalaryDetails);
							List<SalaryBreakUp> extraIncome = staff.getExtraEarningList();
							for(SalaryBreakUp sal : extraIncome){
								SalaryBreakUp SalaryBreakUpObj = new SalaryBreakUp();
								SalaryBreakUpObj.setSalaryBreakUpCode(sal.getSalaryBreakUpCode());
								SalaryBreakUpObj.setSalaryBreakUpName(sal.getSalaryBreakUpName());
								SalaryBreakUpObj.setAmount(sal.getAmount());
								SalaryBreakUpObj.setStatus(sal.getStatus());
								salaryBreakUpList.add(SalaryBreakUpObj);
							}
							staff.setSalaryBreakUpList(salaryBreakUpList);
							*/
							session.insert("insertIntoTransactionalWorkingAreaDetailsForStaffSalary", staffSalaryDetails); //Modified By Naimisha 04092017
						}
						
						
					}else{
						status = "disbursed";
					}
					
					
				}
				
			}catch(Exception e){
				status = "fail";
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				logger.error(" Exception in From getSalaryBreakUpForTemplate(SalaryTemplate salaryTemplate) of ErpDaoImpl :",e);
				e.printStackTrace();
			}finally{
				session.close(); 
			}
			return status;
		
		}

		@Override
		public List<Resource> getPaymentStatusForEmployeeForAYearAndMonth(Resource resource) {
			SqlSession session =sqlSessionFactory.openSession();
			List<Resource> resourceDetailsForSalaryList = null;
			try{
				logger.info("Executing getPaymentStatusForEmployeeForAYearAndMonth() from ERPDaoImpl");
				
				resourceDetailsForSalaryList = session.selectList("selectPaymentStatusForEmployeeForAYearAndMonth",resource);
				System.out.println("3121:"+resourceDetailsForSalaryList);
				System.out.println("3120::"+resourceDetailsForSalaryList.size());
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception occured while executing getPaymentStatusForEmployeeForAYearAndMonth() from ERPDAOImpl", e);	
			}
			return resourceDetailsForSalaryList;
		}

		
		//Added By Naimisha 11082017
		@Override
		public SalaryTemplate getTemplateForDesignationTypeAndDesignationAndLevel(Resource resource) {
			SqlSession session =sqlSessionFactory.openSession();
			SalaryTemplate salaryTemplate = null;
			try{
				logger.info("Executing getTemplateForDesignationTypeAndDesignationAndLevel() from ERPDaoImpl");
				
				salaryTemplate = session.selectOne("selectTemplateForDesignationTypeAndDesignationAndLevel",resource);
				
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception occured while executing getTemplateForDesignationTypeAndDesignationAndLevel() from ERPDAOImpl", e);	
			}
			return salaryTemplate;
		}
		
		/*modified by ranita.sur on 20092017 for getting unmapped designation*/
		@Override
		public List<Designation> getAllUnMappedDesignation() {
			SqlSession session =sqlSessionFactory.openSession();
			List<Designation> designationLevelList = null;
			try{
				logger.info("Executing getAllDesignationLevel() from ERPDaoImpl");
				designationLevelList = session.selectList("selectAllUnMappedDesignation");
			}catch(Exception e){
				logger.error("Exception occured while executing getAllDesignationLevel() from ERPDAOImpl", e);	
			}
			return designationLevelList;
		}
		
		@Override
		public String getEventEmployeeName(String eventIncharge){
			SqlSession session =sqlSessionFactory.openSession();
			String employeeName = null;
			try{
				System.out.println("In DAO getEventEmployeeName : "+eventIncharge);
				logger.info("Executing getEmployeeName() from ERPDaoImpl");
				employeeName = session.selectOne("getEventEmployeeName",eventIncharge);
				System.out.println("EMPLOYEE NAME in DAO.."+employeeName);
			}catch(Exception e){
				logger.error("Exception occured while executing getAllDesignationLevel() from ERPDAOImpl", e);	
			}
			return employeeName;
		}

}
