package com.qts.icam.dao.impl.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.common.CommonDao;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.administrator.Approver;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.AnnualStock;
import com.qts.icam.model.common.AnnualStockList;
import com.qts.icam.model.common.Asset;
import com.qts.icam.model.common.AssetSubType;
import com.qts.icam.model.common.AssetType;
import com.qts.icam.model.common.BloodGroup;
import com.qts.icam.model.common.Budget;
import com.qts.icam.model.common.Condemnation;
import com.qts.icam.model.common.CondemnationList;
import com.qts.icam.model.common.Country;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.EmailDetails;
import com.qts.icam.model.common.FinancialYear;
import com.qts.icam.model.common.Item;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.LicenseInfo;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.MeritListType;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.Notification;
import com.qts.icam.model.common.NotificationDetails;
import com.qts.icam.model.common.NotificationMedium;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.SchoolDetails;
import com.qts.icam.model.common.SchoolNote;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SmsAudit;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.State;
import com.qts.icam.model.common.StatusOfItem;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.StudentAttendance;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.TeachingLevel;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.common.IncomeAge;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.DesignationLevel;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.erp.ResourceAttendence;
import com.qts.icam.model.erp.SalaryBreakUp;
import com.qts.icam.model.finance.TransactionalWorkingArea;
import com.qts.icam.model.inventory.CommodityPurchaseOrder;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.login.LoginForm;
import com.qts.icam.model.survey.Answer;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.survey.QuestionMaster;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.TaskComment;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;


import com.qts.icam.service.backoffice.BackOfficeService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.ticket.TicketService;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;
import com.qts.icam.utility.mailservice.EmailSender;
import com.qts.icam.model.admission.AdmissionDrive;
import com.qts.icam.model.backoffice.Fees;
import com.qts.icam.model.backoffice.ResourceAttendance;
import com.qts.icam.model.backoffice.Term;
import com.qts.icam.model.backoffice.TimeTableGridData;
import com.qts.icam.model.backoffice.WebIQTransaction;

@Repository
public class CommonDaoImpl implements CommonDao {
	public static Logger logger = Logger.getLogger(CommonDaoImpl.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	CommonService commonService;
	
	@Autowired
	BackOfficeService backOfficeService;
	
	@Autowired
	Utility utility;
	
	@Autowired
	private EncryptDecrypt encryptDecrypt;
	
	@Autowired
	EmailSender emailSender;

	
	@Override
	public List<NoticeBoard> getNoticeList() {
		logger.info("getNoticeList() method in  AdministratorDAOImpl");
		SqlSession session =sqlSessionFactory.openSession();
		List<NoticeBoard> noticeList = null;
		try {
			noticeList = session.selectList("selectNotice");
		} catch (Exception e) {
			logger.error("Exception in getNoticeList() method in  AdministratorDAOImpl", e);
		} finally {
			session.close();
		}
		return noticeList;
	}

@Override
public AcademicYear getCurrentAcademicYear() {
	logger.info("In getCurrentAcademicYear() method of CommonDaoImpl");
	AcademicYear academicYear = null;
	SqlSession session =sqlSessionFactory.openSession();
	try {
		academicYear = session.selectOne("selectCurrentAcademicYear");
	} catch (Exception e) {
		logger.error("In getClassList() method of CommonDaoImpl" + e);
	} finally {
		session.close();
	}
	return academicYear;
}


	
	public Notification getEmailDetails(String userId) {
		Notification emailNotification=null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			List<EmailDetails> emailDetailsList = session.selectList("selectEmailDetailsFromEmailReceived", userId);
			System.out.println("emailDetailsList size==="+emailDetailsList.size());
			emailNotification = new Notification();
			if(null!=emailDetailsList && emailDetailsList.size()!=0){
				emailNotification.setEmailDetailsList(emailDetailsList);
			}
			Integer countNewMessage  = session.selectOne("selectCountNewMessageForReceivedEmail", userId); 
			System.out.println("count messagae====="+countNewMessage);
			if(countNewMessage != null){
				emailNotification.setNewNotification(countNewMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In getEmailDetails(String userId) of commonDaoImpl - problem in catch",e);
		} finally {
			session.close();
		}
		return emailNotification;
	}
	
	@Override
	public List<Module> getAllActiveModules() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Module> moduleList=null; 
		try {
			logger.info("getAllActiveModules() method in CommonDaoImpl");
			moduleList=session.selectList("getAllActiveModules");		
		} catch (Exception e) {
			logger.error("getAllActiveModules() method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		return moduleList;
	}


	@Override
	public List<Notification> getNotificationDescriptionList(String userId) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Notification> notificationList=null;
		try{			
			notificationList=session.selectList("selectNotificationSenderListForAlert",userId);
		}catch(NullPointerException e){
			logger.error("Error In getNotificationDescriptionList() method of CommonDAOImpl:NullPointerException:: ",e);
		}catch(Exception e){
			logger.error("Error In getNotificationDescriptionList() method of CommonDAOImpl :Exception:: ",e);
		}finally{
			session.close();
		}
		return notificationList;
	}

	@Override
	public String getTimeFromDB() {
		logger.info("getTimeFromDB() method in  CommonDAOImpl");
		SqlSession session =sqlSessionFactory.openSession();
		String  getTimeFromDB= null;
		try {
			getTimeFromDB = session.selectOne("getTimeFromDB");
		} catch (Exception e) {
			logger.error("Exception in getTimeFromDB() method in  CommonDAOImpl", e);
		} finally {
			session.close();
		}
		return getTimeFromDB;
	}
	//-----------------------added for admission by sovan.mukherjee-------------------------------



	@Override
	public List<Country> getCountryList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Country> countryList = null;
		try {
			countryList = session.selectList("selectCountryList");
		} catch (Exception e) {
			logger.error("Exception in getCountryList() in CommonDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return countryList;
	}

	@Override
	public List<SocialCategory> getSocialCategoryList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<SocialCategory> socialCategoryList = null;
		try {
			socialCategoryList = session.selectList("selectSocialCategoryList");
		} catch (Exception e) {
			logger.error("Exception in getSocialCategoryList() in CommonDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return socialCategoryList;
	}

	@Override
	public List<Standard> getStandards() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Standard> standardList = null;
		try {
			standardList = session.selectList("selectStandardListForProgramDetails");
			//System.out.println("standardList::"+standardList);
		} catch (Exception e) {
			logger.error("Exception in getStandards() in CommonDaoImpl(): ", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return standardList;
	}

	@Override
	public List<State> getStateNameList(String countryCode) {
		SqlSession session =sqlSessionFactory.openSession();
		List<State> stateList = null;
		try {
			stateList = session.selectList("selectStateListForCountry",countryCode);
		} catch (Exception e) {
			logger.error("Exception in getStateNameList() in CommonDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return stateList;
	}

	@Override
	public List<AcademicYear> getCurrentAndNextAcademicYear() {
		SqlSession session =sqlSessionFactory.openSession();
		List<AcademicYear> currentAndNextAcademicYear = null;
		try {
			currentAndNextAcademicYear = session.selectList("selectAcademicYearList");
		} catch (Exception e) {
			logger.error("Exception in getCurrentAndNextAcademicYear() in CommonDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return currentAndNextAcademicYear;
	}
	

	@Override
	public List<Resource> getActiveLoggedInUser(String userid) {
		List<Resource> resourceList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			resourceList = session.selectList("getActiveLoggedInUser",userid);		
		} catch (Exception e) {
			
			logger.debug("In getActiveLoggedInUser() of commonDaoImpl - problem in Selecting Active Users");
		} finally {
			session.close();
		}
		return resourceList;
	}

	@Override
	public String getUserNameForId(String userId) {
		logger.info("Get getUserNameForId from DB");
		SqlSession session =sqlSessionFactory.openSession();
		String userName=null;
		try {
			Resource resource = session.selectOne("getUserNameForId", userId);							
			if(null!=resource && null!=resource.getName()){
				userName=resource.getName();
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return userName;
	}
	
	@Override
	public List<TicketStatus> getAllTicketStatus() throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<TicketStatus> ticketStatusList = new ArrayList<TicketStatus>();
		try {
			logger.info("In getAllTicketStatus() method of CommonDaoImpl");			
			ticketStatusList = session.selectList("getAllTicketStatus");
		} catch (NullPointerException e) {
			logger.error("getAllTicketStatus() In CommonDaoImpl.java: NullPointerException" + e);
			CustomException.exceptionHandler(e);
		} catch (Exception e) {
			logger.error("getAllTicketStatus() In AdminDAOImpl.java: Exception" + e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return ticketStatusList;
	}

	@Override
	public List<ResourceType> getAllResourceType() throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<ResourceType> resourceTypeList = new ArrayList<ResourceType>();
		try {
			resourceTypeList = session.selectList("getAllResourceType");
		} catch (Exception e) {
			logger.error("getAllResourceType() In CommonDaoImpl.java: Exception", e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return resourceTypeList;
	}

	@Override
	public List<Role> getAllRoleList() throws CustomException {
		logger.info("Get getAllRoleList from DB");
		SqlSession session =sqlSessionFactory.openSession();
		List<Role> roleList = null ;
		try {			
			roleList = session.selectList("selectAllRoleList");			
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}

		return roleList;
	}
	
	
	@Override
	public List<Section> getSectionAgainstStandard(String standard) throws CustomException {
		List<Section> sectionList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			sectionList = session.selectList("selectSectionAgainstStandard",standard);		
		} catch (Exception e) {
			
			logger.debug("Exception In getSectionAgainstStandard() of commonDaoImpl");
		} finally {
			session.close();
		}
		return sectionList;
	}
	
	@Override
	public List<String> getUserIdForResourceType(ResourceType resourceType)
			throws CustomException {
		logger.info("Get getUserIdForResourceType from DB");
		SqlSession session =sqlSessionFactory.openSession();
		List<String> userIdFromResource = null;
		try {
			userIdFromResource = session.selectList("getUserIdForResourceType", resourceType);
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return userIdFromResource;
	}
	
	@Override
	public List<VendorType> getVendorTypes() {
		SqlSession session =sqlSessionFactory.openSession();
		List<VendorType> vendorTypeList = null;
		try {
			vendorTypeList = session.selectList("selectVendorTypes");
		}catch (Exception e) {
			logger.error("getVendorTypes() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return vendorTypeList;
	}

	/**
	 * modified by ranita.sur
	 * changes taken on 03072017*/
	//Modified by naimisha 03052018
	@Override
	public int addVendor(Vendor vendor,Ledger ledger) {
		SqlSession session =sqlSessionFactory.openSession();
		int insertStatus=0;
		int insertStatus1=0;
		int statusValue=0;
		Vendor vendor1=new Vendor();
		try {
			vendor.setVendorObjectId(encryptDecrypt.getBase64EncodedID("AdminDAOImpl"));
			insertStatus = session.insert("insertVendor", vendor);
			
			/*ledger.setObjectId(encryptDecrypt.getBase64EncodedID("CommonDAOImpl"));
			ledger.setLedgerName(vendor.getVendorName().toUpperCase());
			*//**addedForVendorLedgerMapping 31072017**//*
			vendor1=session.selectOne("selectVendorRecordForLedgerEntry",vendor);
			ledger.setOpeningDrCr(vendor1.getVedorRecId());
			insertStatus1 = session.insert("insertinLedgerForVendor", ledger);
			if(insertStatus != 0)
				session.commit();
			if(insertStatus1 != 0)
				statusValue = session.update("editinLedgerBalanceForVendor", ledger);*/
				session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("addVendor(Vendor vendor) In CommonDaoImpl.java: Exception",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<Vendor> getVendorList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Vendor> vendorList = null;
		try {
			vendorList = session.selectList("selectVendors");
		}catch (Exception e) {
			logger.error("getVendorList() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return vendorList;
	}

	@Override
	public Vendor getVendorDetails(String vendorCode) {
		SqlSession session =sqlSessionFactory.openSession();
		Vendor vendor = new Vendor();
		try {
			vendor.setVendorCode(vendorCode);
			vendor = session.selectOne("selectVendors", vendor);
		}catch (Exception e) {
			logger.error("getVendorDetails(String vendorCode) In CommonDaoImpl.java: Exception",e);
		} finally {
			session.close();
		}
		return vendor;
	}

	@Override
	public int updateVendorDetails(Vendor vendor) {
		SqlSession session =sqlSessionFactory.openSession();
		int updateStatus=0;
		try {
			updateStatus = session.update("updateVendor", vendor);
			if(updateStatus!=0)
			session.commit();
		}catch (Exception e) {
			logger.error("updateVendorDetails(Vendor vendor) In CommonDaoImpl.java: Exception",e); 
		} finally {
			session.close();
		}
		return updateStatus;
	}

	@Override
	public int deleteVendorDetails(Vendor vendor) {
		SqlSession session =sqlSessionFactory.openSession();
		int updateStatus=0;
		try {
			updateStatus = session.delete("deleteVendor", vendor);
			if(updateStatus!=0)
			session.commit();
		}catch (Exception e) {
			logger.error("deleteVendorDetails(Vendor vendor) In CommonDaoImpl.java: Exception",e);
		} finally {
			session.close();
		}
		return updateStatus;
	}

	@Override
	public AcademicYear getInventorySession() {
		logger.info("In getCurrentAcademicYear() method of CommonDaoImpl");
		AcademicYear inventorySession = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			inventorySession = session.selectOne("selectInventorySession");
		} catch (Exception e) {
			logger.error("In getInventorySession() method of CommonDaoImpl" + e);
		} finally {
			session.close();
		}
		return inventorySession;
		}
	

@Override
	public List<Resource> getStaffUserIdList() {
		logger.info("In getStaffUserIdList() method of BackOfficeDAOImpl: ");
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> staffUserIdList = null;
		try {
			staffUserIdList = session.selectList("selectStaffUserIdList");
		} catch (NullPointerException e) {
			logger.error("Error In getStaffUserIdList() method of BackOfficeDAOImpl:NullPointerException:: "
					+ e);
		} catch (Exception e) {
			logger.error("Error In getStaffUserIdList() method of BackOfficeDAOImpl :Exception:: "
					+ e);
		} finally {
			session.close();
		}
		return staffUserIdList;
	} 

	@Override
	public List<Department> selectAllDepartment() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Department> deptList = new ArrayList<Department>();
		try {
			deptList = session.selectList("selectAllDepartment");
		} catch (Exception e) {
			logger.error("selectAllDepartment() In CommonDaoImpl.java: Exception", e);
			logger.error(e);
		} finally {
			session.close();
		}
		return deptList;
	}
	
	@Override
	public List<Resource> getDepartmentWiseUserList(String department) {
		List<Resource> staffList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			staffList = session.selectList("getDepartmentWiseUserList", department);		
		} catch (Exception e) {
			
			logger.debug("In getDepartmentWiseUserList(String department) of commonDaoImpl - problem in catch");
		} finally {
			session.close();
		}
		return staffList;
	}

	@Override
	public SchoolDetails selectSchoolTimeTable()throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		SchoolDetails schoolDetails = null;
		try {
			logger.info("In getSchoolDetails() method of CommonDaoImpl");			
			schoolDetails = session.selectOne("selectSchoolDetails");
		} catch (NullPointerException e) {
			logger.error("getSchoolDetails() In CommonDaoImpl.java: NullPointerException" + e);
			CustomException.exceptionHandler(e);
		} catch (Exception e) {
			logger.error("getSchoolDetails() In AdminDAOImpl.java: Exception" + e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return schoolDetails;
	}
	
	@Override
	public List<Student> getStudentsAgainstStandardAndSection(Student student) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try {
			studentList = session.selectList("selectStudentsAgainstStandardAndSection", student);
		}catch (Exception e) {
			
			logger.error("getStudentsAgainstStandardAndSection() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return studentList;
	}

	@Override
	public List<Subject> getSubjectsStudiedByStudent(String rollNumber) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Subject> subjectList = null;
		try {
			subjectList = session.selectList("selectSubjectsStudiedByStudent", Integer.parseInt(rollNumber));
		}catch (Exception e) {
			
			logger.error("getSubjectsStudiedByStudent() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return subjectList;
	}
	
	
	@Override
	public int updateAndInsertUserPasswordStatus(LoginForm login) {
		SqlSession session =sqlSessionFactory.openSession();
		int status=0;
		try{
		  logger.info("updateAndInsertUserPasswordStatus(LoginForm login) method in BackOfficeDaoImpl");	 
		  status = session.update("updateUserPasswordStatus", login);
			if(login.getStatus().equals("ByAdmin")){			   
			   login.setObjId(encryptDecrypt.getBase64EncodedID("LoginDAOImpl"));
			   status = session.insert("insertUserPasswordStatus", login);
			   session.commit();
			}
		}catch(Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("Exception in createPassword(LoginForm login) method in BackOfficeDaoImpl for LDAP operation EXCEPTION: ",e);
			status=0;
		}
		return status;
	}
	
	@Override
	public Resource getResourceDetails(String userId) {
		Resource resource = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("@@@@@@@ "+userId);
			resource = session.selectOne("selectResourceDetails", userId);
		} catch (Exception e) {
			
			logger.error("In getResourceDetails(String userId) of commonDaoImpl - problem in catch",e);
		} finally {
			session.close();
		}
		return resource;
	}
	
	@Override
	public List<Resource> getAllTeacherList() throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> teacherList = null;
		try {
			teacherList = session.selectList("selectAllTeacherList");
		}catch (Exception e) {
			
			logger.error("getAllTeacherList() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return teacherList;
	}
	
	@Override
	public LoggingAspect getLogActivityRules(LoggingAspect loggingAspect) {
		logger.info(" getLogActivityRules() in CommonDaoImpl(); ");
		SqlSession session =sqlSessionFactory.openSession();
		try{
			loggingAspect=session.selectOne("selectLoggingNotificationActivityRules", loggingAspect);
		}catch(Exception e){
			logger.error("Exception in getLogActivityRules(LoggingAspect loggingAspect) method in CommonDaoImpl:", e);
			
		}finally{
			session.close();
		}
	return loggingAspect;
	}
	
	/**
	 * this method is used to logging into Database 
	 */
	@Override
	public void insertLoggingDetails(LoggingAspect loggingAspect) {
		logger.info(" insertLoggingDetails() in  method in CommonDaoImpl ");
		SqlSession session =sqlSessionFactory.openSession();
		try{
			loggingAspect.setLoggingObjectId(encryptDecrypt.getBase64EncodedID("CommonDaoImpl"));
			session.insert("insertIntoLoggingDetails", loggingAspect);
			session.commit();
		}
		catch(Exception e){
			logger.error("Exception in insertLoggingDetails(LoggingAspect loggingAspect) method:in CommonDaoImpl", e);
			
		}finally{
			session.close();
		}
	}
	
	/**
	 * this method is used to get Notification Activity Rules for create  notification
	 */
	@Override
	public LoggingAspect getNotificationActivityRules(LoggingAspect loggingAspect) {
		logger.info(" getNotificationActivityRules() in CommonDaoImpl ");
		SqlSession session =sqlSessionFactory.openSession();
		LoggingAspect loggingAspectFromDB=null;
		try{
			loggingAspectFromDB=session.selectOne("selectLoggingNotificationActivityRules", loggingAspect);
			if(loggingAspectFromDB != null){
				if(loggingAspectFromDB.isNotification()==true){
					loggingAspect.setNotificationLevel("NORMAL");
					/**
					 * calling into CommonMapper.xml mapper using 'selectAllTeacherAndOfficeStaffListForUrgentOrNormalNotification' id for get Teacher or Staff list for normal notification .
					 */
					List<Resource> normalNotificationUserGroupList = session.selectList("selectAllUserGroupListForUrgentOrNormalNotification", loggingAspect);
					if(normalNotificationUserGroupList != null && normalNotificationUserGroupList.size() != 0){
						List<Resource> normalNotificationResourceList = new ArrayList<Resource>();
						for(Resource r:normalNotificationUserGroupList){
							List<Resource>resourceList = session.selectList("selectResourceListForUserGroup", r);
							if(resourceList!=null && resourceList.size()!=0){
								for(Resource resource: resourceList){
									normalNotificationResourceList.add(resource);
								}
							}
						}
						if(normalNotificationResourceList!=null && normalNotificationResourceList.size()!=0){
							normalNotificationResourceList.get(0).setStatus("NORMAL");
						}
						loggingAspectFromDB.setNormalNotificationResourceList(normalNotificationResourceList);
					}
					/**
					 * calling into CommonMapper.xml mapper using 'selectAllTeacherAndOfficeStaffListForUrgentOrNormalNotification' id for get Teacher or Staff list for urgent notification.
					 */
					logger.info("ModuleName::::::::::::::::::::: "+loggingAspect.getModule().getModuleName());
					logger.info("FunctionalityName::::::::::::::::::::: "+loggingAspect.getFunctionality().getFunctionalityName());
					logger.info("MethodSignatureNameName::::::::::::::::::::: "+loggingAspect.getMethodSignatureName());
					
					loggingAspect.setNotificationLevel("URGENT");
					List<Resource> urgentNotificationRoleList = session.selectList("selectAllUserGroupListForUrgentOrNormalNotification", loggingAspect);
					logger.info("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS"+urgentNotificationRoleList.size());
					if(urgentNotificationRoleList != null && urgentNotificationRoleList.size() != 0){
						List<Resource>urgentNotificationResourceList = new ArrayList<Resource>();
						for(Resource r:urgentNotificationRoleList){
							List<Resource>resourceList = session.selectList("selectResourceListForUserGroup", r);
							if(resourceList!=null && resourceList.size()!=0){
								for(Resource resource: resourceList){
									urgentNotificationResourceList.add(resource);
								}
							}
						}
						if(urgentNotificationResourceList!=null && urgentNotificationResourceList.size()!=0){
							urgentNotificationResourceList.get(0).setStatus("URGENT");
						}
						loggingAspectFromDB.setUrgentNotificationResourceList(urgentNotificationResourceList);
					}
				}
			}
		}catch(Exception e){
			logger.error("Exception in getNotificationActivityRules(LoggingAspect loggingAspect) method:in CommonDaoImpl", e);
			
		}finally{
			session.close();
		}
		return loggingAspectFromDB;
	}

	/**
	 * this method is used for notification mediums for a notification level
	 */
	@Override
	public List<NotificationMedium> getNotificationMediums(String notificatinLevel) {
		SqlSession session =sqlSessionFactory.openSession();
		List<NotificationMedium> notificationMediumList = null;
		try{			
			logger.info("getNotificationMediums(String notificatinLevel) method in CommonDaoImpl");
			notificationMediumList=session.selectList("getActiveNotificationMediumsForNotificationLevel", notificatinLevel);
		}catch(Exception e){
			logger.error("Exception in getNotificationMediums(String notificatinLevel) method:in CommonDaoImpl", e);
			
		}finally{
			session.close();
		}	
		return notificationMediumList;
	}

	@Override
	public int createAlert(Notification notification) {
		SqlSession session =sqlSessionFactory.openSession();
		int updateStatus=0;
		try {
			notification.setNotificationObjectId(encryptDecrypt.getBase64EncodedID("CommonDAOImpl"));			
			updateStatus = session.insert("insertAlert", notification);
		} catch (Exception e) {
			logger.error("createAlert() In CommonDaoImpl.java: Exception", e);
			
		} finally {
			session.close();
		}
		return updateStatus;
	}
	
	
	@Override
	public List<String> getCity(String strQuery) {
		SqlSession session =sqlSessionFactory.openSession();
		List<String> cityList = null;
		try {
			cityList = session.selectList("getCityList", strQuery);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return cityList;
	}	

	@Override
	public List<String> getDistrict(String strQuery) {
		SqlSession session =sqlSessionFactory.openSession();
		List<String> districtList = null;
		try {
			districtList = session.selectList(strQuery);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return districtList;
	}
			
	@Override
	public String getRecIdForVenue(String venueName) {
		SqlSession session =sqlSessionFactory.openSession();
		String recId=null;
		try {
			recId = session.selectOne("selectRecIdForVenue", venueName);
		} catch (Exception e) {
			logger.error("getRecIdForVenue() In CommonDaoImpl.java: Exception", e);
			
		} finally {
			session.close();
		}
		return recId;
	}

	@Override
	public Notification getNotificationDetails(Notification notification) {
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("QQQQ  "+notification.getNotificationId());
			session.update("updateNotificationDetailsForViewNotification",notification);
			session.commit();
			logger.info("In getNotificationDetails(Notification notification) method of CommonDAOImpl: ");
			//notification=session.selectOne("selectNotificationDetails",notification);
			notification=null;
		}catch(NullPointerException e){
			logger.error("Error In getNotificationDetails(Notification notification) method of CommonDAOImpl:NullPointerException:: ",e);
		}catch(Exception e){
			logger.error("Error In getNotificationDetails(Notification notification) method of CommonDAOImpl:: ",e);
		}finally{
			session.close();
		}
		return notification;
	}

	@Override
	public void changeMailReadStatus(String emailAlertCode) {
		SqlSession session =sqlSessionFactory.openSession();		
		try {
			logger.info("changeMailReadStatus() method in CommonDaoImpl");
			session.update("changeMailReadStatus", emailAlertCode);		
		} catch (Exception e) {
			logger.error("changeMailReadStatus() method in CommonDaoImpl", e);
		} finally {
			session.close();
		}		
	}

	@Override
	public List<Resource> getGroupList() {
		SqlSession session =sqlSessionFactory.openSession();		
		List<Resource>  allUserGroupList = null;
		try {
			logger.info("changeMailReadStatus() method in CommonDaoImpl");
			allUserGroupList = session.selectList("selectAllGroupList");	
		} catch (Exception e) {
			logger.error("changeMailReadStatus() method in CommonDaoImpl", e);
		} finally {
			session.close();
		}		
		return allUserGroupList;
	}

	
	@Override
	public List<Resource> getUsersForUserGroup(NotificationDetails notificationDetails) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> resourceList = null;
		try{
			logger.info("In getUsersForUserGroup() method of CommonDAOImpl: ");
			Resource resource = new Resource();
			resource.setCode(notificationDetails.getNotificationReceiver());
			resourceList=session.selectList("selectResourceListForUserGroup", resource);
		}catch(Exception e){
			logger.error("Error In getUsersForUserGroup(NotificationDetails notificationDetails) method of CommonDAOImpl:: ",e);
			
		}finally{
			session.close();
		}
		return resourceList;
	}

	/*@Override
	public void insertEmailDetails(List<EmailDetails> emailDetailsList,String userId) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			  String objId =   encryptDecrypt.getBase64EncodedID("CommonDaoImpl"); 
			  Notification notification = new Notification();
			  NotificationMedium notificationMedium = new NotificationMedium();
			  notificationMedium.setNotificationMediumName("E-MAIL");
			  notification.setNotificationMedium(notificationMedium);
			  if(emailDetailsList != null && emailDetailsList.size() != 0){
				  for(EmailDetails emailDetails : emailDetailsList){
					  notification.setUpdatedBy(userId);
						notification.setNotificationObjectId(objId);
						notification.setNotificationSubject(emailDetails.getEmailDetailsSubject());
						notification.setNotificationDesc(emailDetails.getEmailDetailsDesc());
						logger.info("@@@@########: "+emailDetails.getEmailDetailsSender());
						//String senderUserId = session.selectOne("selectUserIdForEmail", emailDetails.getEmailDetailsSender());
						notification.setNotificationSender(emailDetails.getEmailDetailsSender());
						notification.setNotificationReplyTo(userId);
						notification.setReceiveTime(emailDetails.getTime());
						session.insert("insertAlert", notification);
					}  
				  session.commit();
			  }
		} catch (Exception e) {
			
			logger.error("In insertEmailDetails(List<EmailDetails> emailDetails) of commonDaoImpl - problem in catch",e);
		} finally {
			session.close();
		}
	}	*/
	
	//Modified By Naimisha 18092017
	@Override
	public Notification insertEmailDetails(List<EmailDetails> emailDetailsList,String userId) {
		int  insertStatus=0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			  String objId =   utility.getBase64EncodedID("CommonDaoImpl"); 
			  if(emailDetailsList != null && emailDetailsList.size() != 0){
				  for(EmailDetails emailDetails : emailDetailsList){
						emailDetails.setUpdatedBy(userId);
						emailDetails.setObjId(objId);
						insertStatus = session.insert("insertIntoEmailAlert", emailDetails);
						List<String>fileList = emailDetails.getFileList();
						if (insertStatus !=0){
							if(null != fileList && fileList.size() !=0){
								for(String fileString : fileList){
									Notification notification = new Notification();
									notification.setUpdatedBy(emailDetails.getUpdatedBy());
									notification.setNotificationObjectId(objId);
									notification.setEmailFilepath(emailDetails.getFilePath());
									notification.setEmailBodyTemplate(fileString);
									session.insert("submitEmailReceivedAttachmentDoc", notification);
								}
							}
							
						}
						
						
					}
				  session.commit();
			  }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In insertEmailDetails(List<EmailDetails> emailDetails) of commonDaoImpl - problem in catch",e);
		} finally {
			session.close();
		}
		return getEmailDetails(userId);
	}

	@Override
	public Student getStudentDetails(String rollNumber) {
		SqlSession session =sqlSessionFactory.openSession();
		Student student = null;
		try {
			student = session.selectOne("selectBasicStudentDetailsForReport", rollNumber);
		} catch (Exception e) {
			logger.error("getStudentDetails() method in CommonDaoImpl", e);
		} finally {
			session.close();
		}
		return student;
	}


	@Override
	public List<AcademicYear> getPreviousAndCurrentAcademicYear() {
		SqlSession session =sqlSessionFactory.openSession();
		 List<AcademicYear> academicYearList = null;
		try {
			academicYearList = session.selectList("selectPreviousAndCurrentAcademicYear");
		} catch (Exception e) {
			logger.error("getStudentDetails() method in CommonDaoImpl", e);
		} finally {
			session.close();
		}
		return academicYearList;		
	}

	@Override
	public List<String> getUserIdToCreateNotification(String strQuery) {
		SqlSession session =sqlSessionFactory.openSession();
		List<String> staffUserIds=null;
		try {
			staffUserIds = session.selectList("selectUserIdToCreateNotification", strQuery);
		} catch (Exception e) {
			logger.error("getUserIdToCreateNotification() In CommonDaoImpl.java: Exception", e);
			
		} finally {
			session.close();
		}
		return staffUserIds;
	}	
	
	
	/**Modified by
	 * @author Saif.Ali*/
	@Override
	public List<AnnualStock> selectAssetsForDepartment() throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<AnnualStock> annualStockList = null;
		try {
			logger.info("In selectAssetsForDepartment method of CommonDAOImpl: ");
			List<AnnualStock> annualStocksList = session.selectList("selectAssetsForDepartment");
			if(null != annualStocksList && annualStocksList.size() != 0){
				annualStockList = new ArrayList<AnnualStock>();
				for(AnnualStock anStock : annualStocksList){
					AnnualStock annStock = session.selectOne("selectASTVForAsset", anStock.getCommodity());
					if(null == annStock){
						annStock = new AnnualStock();
						annStock.setCommodity(anStock.getCommodity());
						annStock.setAnnualStockId(0);
						annStock.setGroundBalance(0);
						annStock.setSurplus(0);
						annStock.setDeficient(0);
						annStock.setServiceable(0);
						annStock.setRepairable(0);
						annStock.setCondemnation(false);
						annStock.setRemarks("");
					}
					annualStockList.add(annStock);
				}
			}
		} catch (Exception e) {
			logger.error("In selectAssetsForDepartment of CommonDaoImpl.java: Exception", e);
		} finally {
			session.close();
		}
		return annualStockList;
	}
	
	
	@Override
	public String submitASTV(AnnualStockList annualStockList) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String status="success";
		int insertStatus = 0;	
		
		try {		
			for(AnnualStock annualStock : annualStockList.getListAnnualStock()){
				annualStock.setObjectId(encryptDecrypt.encrypt("InventoryDaoImpl"));
				annualStock.setUpdatedBy(annualStockList.getUpdatedBy());
				if(annualStock.getAnnualStockId() != 0){
					insertStatus = session.update("updateAnnualStock",annualStock);	
				}else{
					insertStatus = session.insert("insertAnnualStock",annualStock);
					
				}	
			}						
			if(insertStatus == 0){
				session.commit();
				status="fail";
			}				
		}catch (Exception e) {	
			logger.error("Exception in submitASTV(AnnualStockList annualStockList) of CommonDAOImpl ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return status;
	}
	

	@Override
	public List<AnnualStock> listASTV(String department) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<AnnualStock> annualStockList = null;
		try {
			logger.info("In listASTV(String department) method of CommonDAOImpl: ");
//			annualStockList = session.selectList("selectAnnualStockListForDepartment", department);
		} catch (Exception e) {
			logger.error("In listASTV(String department) of CommonDaoImpl.java: Exception", e);
		} finally {
			session.close();
		}
		return annualStockList;
	}


	@Override
	public String updateASTV(AnnualStockList annualStockList) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String response = null;
		int updateStatus = 0;		
		try {		
			for(AnnualStock annualStock : annualStockList.getListAnnualStock()){
				annualStock.setObjectId(encryptDecrypt.encrypt("InventoryDaoImpl"));
				annualStock.setUpdatedBy(annualStockList.getUpdatedBy());
//				updateStatus = session.update("updateAnnualStock",annualStock);	
			}						
			if(updateStatus != 0){
				session.commit();
				response = "SUCCESS";
			}				
		}catch (Exception e) {	
			logger.error("Exception in updateASTV(AnnualStockList annualStockList) of CommonDAOImpl ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return response;
	}

	/**Modied by @author Saif.Ali
	 * Date- 20/07/2017
	 * Used to get the List of Condemnation items for the inventory department*/
	@Override
	public List<Condemnation> listASTVForCondemnation() throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<Condemnation> condemnationList = null;
		try {
			logger.info("In listASTVForCondemnation(String department) method of CommonDAOImpl: ");
			List<Condemnation> condemnationsList = session.selectList("selectASTVListForDepartment");
			if(null != condemnationsList && condemnationsList.size() != 0){
				condemnationList = new ArrayList<Condemnation>();
				for(Condemnation condemn : condemnationsList){
					Condemnation condemnation = session.selectOne("selectCondemnationForAstv", condemn.getAnnualStock());
					if(null == condemnation){
						condemnation = new Condemnation();
						condemnation.setAnnualStock(condemn.getAnnualStock());
						condemnation.setCondemnationId(0);
						condemnation.setUnit("");
						condemnation.setQtyProducedForCB(0);
						condemnation.setQtySentencedToUNS(0);
						condemnation.setCostAmount(0);
						condemnation.setRemarks("");
					}
					condemnationList.add(condemnation);
				}
			}
		} catch (Exception e) {
			logger.error("In listASTVForCondemnation(String department) of CommonDaoImpl.java: Exception", e);
		} finally {
			session.close();
		}
		return condemnationList;
	}

	@Override
	public List<Condemnation> listCondemnation(String department) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<Condemnation> condemnationList = null;
		try {
			logger.info("In listCondemnation(String department) method of CommonDAOImpl: ");
//			condemnationList = session.selectList("selectCondemnationStockListForDepartment", department);
		} catch (Exception e) {
			logger.error("In listCondemnation(String department) of CommonDaoImpl.java: Exception", e);
		} finally {
			session.close();
		}
		return condemnationList;
	}

	@Override
	public String submitCondemnation(CondemnationList condemnationList) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String response = null;
		int insertStatus = 0;
		int finalStatus = 0;
		int status = 0;
		String finalstatus="success";
		try {		
			for(Condemnation condemnation : condemnationList.getListCondemnationStock()){
				condemnation.setObjectId(encryptDecrypt.encrypt("InventoryDaoImpl"));
				condemnation.setUpdatedBy(condemnationList.getUpdatedBy());
				if(condemnation.getCondemnationId() != 0){
					insertStatus = session.update("updateCondemnation",condemnation);
					if(insertStatus != 0){
						finalStatus = session.update("updateCondemnedStock",condemnation);
						if(finalStatus != 0){
							status = session.update("updateGrondBalanceStock",condemnation);
						}
					}
				}else{
					insertStatus = session.insert("insertCondemnation",condemnation);	
					if(insertStatus != 0){
						finalStatus = session.update("updateCondemnedStock",condemnation);
						if(finalStatus != 0){
							status = session.update("updateGrondBalanceStock",condemnation);
						}
					}					
				}
			}						
			if(status == 0){
				session.commit();
				finalstatus = "fail";
			}				
		}catch (Exception e) {	
			logger.error("Exception in submitCondemnation(CondemnationList condemnationList) of CommonDAOImpl ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return finalstatus;
	}

	@Override
	public String updateCondemnation(CondemnationList condemnationList) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String response = null;
		int updateStatus = 0;
		int finalStatus = 0;
		try {		
			for(Condemnation condemnation : condemnationList.getListCondemnationStock()){
				condemnation.setObjectId(encryptDecrypt.encrypt("InventoryDaoImpl"));
				condemnation.setUpdatedBy(condemnationList.getUpdatedBy());
				updateStatus = session.update("updateCondemnation",condemnation);
				if(updateStatus != 0){
					finalStatus = session.update("updateCondemnedStock",condemnation);
				}
			}						
			if(finalStatus != 0){
				session.commit();
				response = "SUCCESS";
			}				
		}catch (Exception e) {	
			logger.error("Exception in updateCondemnation(CondemnationList condemnationList) of CommonDAOImpl ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return response;
	}

	
	@Override
	public LicenseInfo validateLicenseDetails() {
		SqlSession session =sqlSessionFactory.openSession();
		LicenseInfo licenseInfo=null;
		try {
			licenseInfo = session.selectOne("selectLicenseDetails");
		} catch (Exception e) {
			logger.error("validateLicenseDetails() In CommonDaoImpl.java: Exception", e);
		} finally {
			session.close();
		}
		return licenseInfo;
	}	
	
	@Override
	public List<UpdateLog> getActivityLog(String module, String functionality)throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<UpdateLog> updateLogList = null;
		UpdateLog updateLog=new UpdateLog();
		updateLog.setModule(module);
		updateLog.setFunctionality(functionality);
		try {
			logger.info("In getActivityLog(String module, String functionality) method of CommonDAOImpl: ");
			updateLogList = session.selectList("getActivityLog", updateLog);
		} catch (Exception e) {
			logger.error("In getActivityLog(String module, String functionality) of CommonDaoImpl.java: Exception", e);
		} finally {
			session.close();
		}
		return updateLogList;
	}

	@Override
	public String insertIntoActivityLog(UpdateLog updateLog)throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String response = null;
		int insertStatus = 0;
		try {
			insertStatus = session.insert("insertIntoActivityLog", updateLog);	
			if(insertStatus != 0){
				response = "SUCCESS";
			}
		}catch (Exception e) {	
			logger.error("Exception in insertIntoActivityLog(UpdateLog updateLog) of CommonDAOImpl ", e);
		} finally {
			session.close();
		}
		return response;
	}
	
	
	@Override
	public List<AssetType> getAllAssetType() throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<AssetType> assetTypeList = null;
		try {
			assetTypeList = session.selectList("selectAllAssetType");
		}catch (Exception e) {
			logger.error("Exception in getAllAssetType() of CommonDAOImpl ", e);
			
		} finally {
			session.close();
		}
		return assetTypeList;
	}

	@Override
	public List<AssetSubType> getAllAssetSubType() throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<AssetSubType> assetSubTypeList = null;
		try {
			assetSubTypeList = session.selectList("selectAllAssetSubType");
		}catch (Exception e) {
			logger.error("Exception in getAllAssetSubType() of CommonDAOImpl ", e);
			
		} finally {
			session.close();
		}
		return assetSubTypeList;
	}
	@Override
	public String addAssetType(AssetType assetType) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String response = "Fail";
		int insertStatus = 0;		
		try {		
			if(null != assetType){
				assetType.setObjectId(encryptDecrypt.encrypt("CommonDAOImpl"));										
				insertStatus = session.insert("insertNewAssetType",assetType);	
			}						
			if(insertStatus != 0){
				session.commit();
				response = "Success";
			}				
		}catch (Exception e) {	
			logger.error("Exception in addAssetType(AssetType assetType) of CommonDAOImpl ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return response;
	}
	

	@Override
	public String editAssetType(AssetType assetType) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String response = "Fail";
		int updateStatus = 0;
		try {
			updateStatus = session.update("updateAssetType", assetType);
			if(updateStatus != 0){
				response = "Success";
			}
		}catch (Exception e) {
			logger.error("Exception In editAssetType(AssetType assetType) CommonDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return response;
	}
	

	@Override
	public String addAssetSubType(AssetSubType assetSubType) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String response = "Fail";
		int insertStatus = 0;		
		try {		
			if(null != assetSubType){
				assetSubType.setObjectId(encryptDecrypt.encrypt("CommonDAOImpl"));										
				insertStatus = session.insert("insertNewAssetSubType",assetSubType);	
			}						
			if(insertStatus != 0){
				session.commit();
				response = "Success";
			}				
		}catch (Exception e) {	
			logger.error("Exception in addAssetSubType(AssetSubType assetSubType) of CommonDAOImpl ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return response;
	}

	@Override
	public String editAssetSubType(AssetSubType assetSubType) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String response = "Fail";
		int updateStatus = 0;
		try {
			updateStatus = session.update("updateAssetSubType", assetSubType);
			if(updateStatus != 0){
				response = "Success";
			}
		}catch (Exception e) {
			logger.error("Exception In editAssetSubType(AssetSubType assetSubType) CommonDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return response;
	}

	@Override
	public String deleteAssetSubType(AssetSubType assetSubType)	throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		String response = "Fail";
		int updateStatus = 0;
		try {
			updateStatus = session.update("deleteAssetSubType", assetSubType);
			if(updateStatus != 0){
				response = "Success";
			}
		}catch (Exception e) {
			logger.error("Exception In deleteAssetSubType(AssetSubType assetSubType) CommonDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return response;
	}
	
	@Override
	public List<AssetSubType> getAssetSubTypeForAssetType(String assetType) {
		SqlSession session = sqlSessionFactory.openSession();
		List<AssetSubType> assetSubTypeList = null;
		try {
			AssetType ct = new AssetType();
			ct.setAssetTypeCode(assetType);
			assetSubTypeList = session.selectList("selectAllAssetSubType",ct);
		}catch (Exception e) {
			logger.error("Exception in getAssetSubTypeForAssetType() of CommonDAOImpl ", e);
			
		} finally {
			session.close();
		}
		return assetSubTypeList;
	}
	
	@Override
	public int updateLibraryAssetDetails(Asset asset) {
		SqlSession session =sqlSessionFactory.openSession();
		int updateStatus=0;
		try {/*
			System.out.println(asset.getUpdatedBy());
			System.out.println(asset.getAssetPrice());
			System.out.println(asset.getLedgerNumber());
			System.out.println(asset.getPageNumber());
			System.out.println(asset.getLedgerBalance());
			System.out.println(asset.getAssetSubType());
			System.out.println(asset.getAssetUnit());
			System.out.println(asset.getAssetId());
			*/
			updateStatus = session.update("updateLibraryAssets", asset);
			if(updateStatus!=0)
			session.commit();
		}catch (Exception e) {
			logger.error("updateLibraryAssetDetails(Asset asset) In CommonDaoImpl.java: Exception",e); 
		} finally {
			session.close();
		}
		return updateStatus;
	}
	
	
	@Override
	public List<Leave> getLeaveHistory(String userId) {
		List<Leave> leaveHistoryList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getLeaveHistory() method of TicketDaoImpl");
			
			leaveHistoryList = session.selectList("getLeaveHistoryListforMyService",userId);
			
		} catch (NullPointerException e) {
			
			logger.error("getLeaveHistory() In TicketDAOImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			
			logger.error("getLeaveHistory() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return leaveHistoryList;
	}

	@Override
	public List<Leave> getLeaveDetails() {
		
		List<Leave> leaveDetailsList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getLeaveDetails() method of TicketDaoImpl");			
			leaveDetailsList = session.selectList("getLeaveDetails");
			
		} catch (NullPointerException e) {
			
			logger.error("getLeaveDetails() In TicketDAOImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			
			logger.error("getLeaveDetails() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return leaveDetailsList;
	}

	@Override
	public String getApprovarGroupName(String user_id) {
		String approverGroupName = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getApprovarGroupName() method of TicketDaoImpl");			
			approverGroupName = session.selectOne("selectApproverGroupName",user_id);
			
		} catch (NullPointerException e) {
			
			logger.error("getApprovarGroupName() In TicketDAOImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			
			logger.error("getApprovarGroupName() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return approverGroupName;
	}


	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public int insertIntoApprovalAndLeave(Leave leave) {
		
		int updateStatus = 0;
		int updateMyEventStatus = 0;
		List<Resource>userIdList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
				leave.setObjId(encryptDecrypt.encrypt("TicketDAOImpl"));
				SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
				Date dNow = new Date( );
				String startDate = (dateFormat.format(dNow)).toString();
				String approverGroupName = leave.getDesc();
				/*System.out.println("updated by=========="+leave.getUpdatedBy());*/
				
				updateStatus = session.insert("insertIntoApproval",leave);
				if(updateStatus!=0){
					session.insert("countNoWorkingDaysBetweenTwoDates",leave);
					//System.out.println("No of days ======"+leave.getCanApplyOnStretch());
					//leave.setCanApplyOnStretch(noOfDays);	
					updateStatus = session.insert("insertIntoLeave",leave);
				}
					
				
				
				if(updateStatus!=0){
					userIdList = session.selectList("getUserIDFromApproverGroup",approverGroupName);
					if(userIdList.size()>0){
						for(Resource resource :userIdList ){
							leave.setDecision(resource.getUserId());
							String userId = leave.getUpdatedBy();
							
							Resource resourceName = session.selectOne("selectUserNameByUserId",userId );
							String msg   = resourceName.getUpdatedBy()+" Applied For Leave";
							leave.setDesc(msg);
							updateStatus = session.insert("insertIntoTaskNotification",leave);
							
							  
						}
					}
					if(updateStatus!=0){
						userIdList = session.selectList("getUserIDFromApproverGroup",approverGroupName);
						if(userIdList.size()>0){
							for(Resource resource :userIdList ){
								leave.setDecision(resource.getUserId());
								leave.setStartDate(startDate);
								leave.setEndDate(startDate);
								//System.out.println("within myevent================="+leave.getStartDate()+"AND"+leave.getEndDate());
								updateMyEventStatus = session.insert("insertIntoMyEventForLeave",leave);
								
								  
							}
						}
					}
					if(updateStatus!=0 && updateMyEventStatus!=0)
						session.commit();
				}
					
				
		} catch (Exception e) {
			logger.error("Exception in insertIntoApprovalAndLeave() in TicketDaoImpl(): ", e);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return updateStatus;
	}
	
	

	
	@Override
	public List<Leave> getResourceLeaveDetails(String userId) {
		List<Leave> leaveTypeList=null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			leaveTypeList = session.selectList("selectResourceLeaveDetails", userId);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured while executing getResourceLeaveDetails() from ERPDAOImpl", e);
			
		}
		return leaveTypeList;
	}
	
	@Override
	public ResourceType getResourceTypeofUser(String userId) {
		ResourceType resourceType = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("In getResourceTypeofUser() method of TicketDaoImpl");
			session = sqlSessionFactory.openSession();
			resourceType = session.selectOne("selectResourceTypeofUser", userId);
		}catch (Exception e) {
			logger.error("In getResourceTypeofUser() In TicketDaoImpl.java: Exception" + e);
		}finally {
			session.close();
		}
		return resourceType;
	}
	
	@Override
	public List<ServiceType> getServiceTypeList() {
		List<ServiceType> serviceTypeList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getServiceTypeList() method of TicketDaoImpl");			
			serviceTypeList = session.selectList("getServiceTypeList");
			if(serviceTypeList != null && serviceTypeList.size() != 0){
				for(ServiceType st : serviceTypeList){
					List<Resource> staffList = session.selectList("getDepartmentWiseUserList", st.getDepartment().getDepartmentName()); 
					st.setResourceList(staffList);
				}
			}
		} catch (NullPointerException e) {
			
			logger.error("getServiceTypeList() In TicketDAOImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			
			logger.error("getServiceTypeList() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return serviceTypeList;
	}
	
	/*******Modified By Naimisha 25082017*********/
/*	@Override
	public Ticket generateTicket(Ticket ticket) {
		SqlSession session = sqlSessionFactory.openSession();	
		int intInsertStatus  = 0;
		try {
			logger.info("In generateTicket() method of TicketDAOImpl");
			ticket.setTicketObjectId(encryptDecrypt.encrypt("TicketDaoImpl"));
			intInsertStatus = session.insert("insertTicket", ticket);
			String jobType = ticket.getTicketService().getTicketServiceCode();
			JobType jobDetails  = session.selectOne("getJobTypeNameAgaianstJobCode",jobType);
			String jobName = jobDetails.getJobTypeName();
			List<Approver> approverGroupList = null;
			List<Resource> resourceList = null;
			if(jobDetails.isParallelApproval() == true){
				approverGroupList = session.selectList("approverGroupAndApprovalLevelListForAJobType", jobType);
				for(Approver approverObj : approverGroupList){
					approverObj.setObjectId(encryptDecrypt.encrypt("TicketDaoImpl"));
					approverObj.setUpdatedBy(ticket.getUpdatedBy());
					approverObj.setStatus("OPEN");
					int insertStatus = session.insert("insertIntoTicketMasterDetails", approverObj);
					String userId = ticket.getUpdatedBy();
					Resource notificationFromResource = session.selectOne("selectUserNameByUserId",userId );
					String comment = notificationFromResource.getUpdatedBy()+" Generated A Ticket For A Task Named "+jobName;
					ticket.setComment(comment);
					if (intInsertStatus != 0 && insertStatus != 0) {
						//for(Approver approver : approverGroupList){
							String approverGroupCode = approverObj.getApproverGroupCode();
							resourceList = session.selectList("selectApproverGroupDetails", approverGroupCode);
							for(Resource resource : resourceList){
								ticket.setModuleName(resource.getUserId());
								ServiceType ticketService = new ServiceType();
								ticketService.setTicketServiceName(jobName);
								ticket.setTicketService(ticketService);
								//System.out.println("ticket Rec_id====="+ticket.getModuleName());
								intInsertStatus = session.insert("insertNotification", ticket);
								String emailSubject = "Ticket For "+jobName;
								EmailDetails emailDetails = new EmailDetails();
								emailSender.sendMail(ticket.getUpdatedBy(), resource.getUserId(), emailDetails, emailSubject, comment);
							}
						//}
						 
					}
				}
				
			}
			
			if(jobDetails.isSerialApproval() == true){
				
				approverGroupList = session.selectList("approverGroupAndApprovalLevelListForAJobType", jobType);
				ArrayList<Integer>levelList = new ArrayList<Integer>();
				
				for(Approver approverObj : approverGroupList){
					String level = approverObj.getApproverGroupDesc();
					levelList.add(Integer.parseInt(level.substring(5)));
					
				}
				Collections.sort(levelList);
				String level = "Level"+levelList.get(0);
				for(Approver approverObj : approverGroupList){
					if(approverObj.getApproverGroupDesc().equalsIgnoreCase(level)){
						approverObj.setObjectId(encryptDecrypt.encrypt("TicketDaoImpl"));
						approverObj.setUpdatedBy(ticket.getUpdatedBy());
						approverObj.setStatus("OPEN");
						int serialInsertnsertStatus = session.insert("insertIntoTicketMasterDetails", approverObj);
						String userId = ticket.getUpdatedBy();
						Resource notificationFromResource = session.selectOne("selectUserNameByUserId",userId );
						String comment = notificationFromResource.getUpdatedBy()+" Generated A Ticket For A Task Named "+jobName;//Modification
						ticket.setComment(comment);
						if (intInsertStatus != 0 && serialInsertnsertStatus != 0) {
							//for(Approver approver : approverGroupList){
								String approverGroupCode = approverObj.getApproverGroupCode();
								resourceList = session.selectList("selectApproverGroupDetails", approverGroupCode);
								for(Resource resource : resourceList){
									ticket.setModuleName(resource.getUserId());
									ServiceType ticketService = new ServiceType();
									ticketService.setTicketServiceName(jobName);
									ticket.setTicketService(ticketService);
									//System.out.println("ticket Rec_id====="+ticket.getModuleName());
									intInsertStatus = session.insert("insertNotification", ticket);
									String emailSubject = "Ticket For "+jobName;
									EmailDetails emailDetails = new EmailDetails();
									emailSender.sendMail(ticket.getUpdatedBy(), resource.getUserId(), emailDetails, emailSubject, comment);
								}
							//}
							 
						}
					}
					
				}
				
				
			}
			if(jobDetails.isSerialApproval() == false && jobDetails.isParallelApproval() == false){
				approverGroupList = session.selectList("approverGroupAndApprovalLevelListForAJobType", jobType);
				for(Approver approverObj : approverGroupList){
					approverObj.setObjectId(encryptDecrypt.encrypt("TicketDaoImpl"));
					approverObj.setUpdatedBy(ticket.getUpdatedBy());
					approverObj.setStatus("OPEN");
					int insertStatus = session.insert("insertIntoTicketMasterDetails", approverObj);
					String userId = ticket.getUpdatedBy();
					Resource notificationFromResource = session.selectOne("selectUserNameByUserId",userId );
					String comment = notificationFromResource.getUpdatedBy()+" Generated A Ticket For A Task Named "+jobName;
					ticket.setComment(comment);
					if (intInsertStatus != 0 && insertStatus != 0) {
						//for(Approver approver : approverGroupList){
							String approverGroupCode = approverObj.getApproverGroupCode();
							resourceList = session.selectList("selectApproverGroupDetails", approverGroupCode);
							for(Resource resource : resourceList){
								ticket.setModuleName(resource.getUserId());
								ServiceType ticketService = new ServiceType();
								ticketService.setTicketServiceName(jobName);
								ticket.setTicketService(ticketService);
								//System.out.println("ticket Rec_id====="+ticket.getModuleName());
								intInsertStatus = session.insert("insertNotification", ticket);
								String emailSubject = "Ticket For "+jobName;
								EmailDetails emailDetails = new EmailDetails();
								emailSender.sendMail(ticket.getUpdatedBy(), resource.getUserId(), emailDetails, emailSubject, comment);
							}
						//}
						 
					}
				}
			}
			//System.out.println("approverGroupList size ==="+approverGroupList.size());
			
			
			session.commit();
			if (intInsertStatus != 0) {				
				ticket.setStatus("success");
			} else {				
				ticket.setStatus("fail");
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			logger.error("generateTicket() In TicketDAOImpl.java: Exception" + e);
			
		} finally {
			session.close();
		}
		return ticket;
	}*/
	
	
	/*******Modified By Naimisha 12042018*********/
	@Override
	public Ticket generateTicket(Ticket ticket) {
		SqlSession session = sqlSessionFactory.openSession();
	
		int intInsertStatus  = 0;
		int updateTaskDetails = 0;
		Map<Integer,String> recepientMap = new HashMap<>();
		Map<Integer,String> taskRecepientMap = new HashMap<>();
		Ticket ticketObj = null;
		Ticket ticketObjectNew1 = new Ticket();
		String status = "success";
		 List<String> allLevelList = new ArrayList<>(); 
		try {
			logger.info("In generateTicket() method of TicketDAOImpl");
			ticket.setTicketObjectId(encryptDecrypt.encrypt("TicketDaoImpl"));
			String categoryCode = ticket.getTicketService().getTicketServiceCode();
			List<JobType>getRecipientGroupList = session.selectList("getCategoryRecipientMapping",categoryCode); //get recipient mapped with category
			for (JobType group : getRecipientGroupList){
				Approver recepientGroup = group.getApproverGroupList().get(0);
				List<Resource> recepientDetailsList = session.selectList("selectRecipientGroupDetails", recepientGroup);//get list of recipients(user) for the recipient group mapped with category
				for(Resource resource : recepientDetailsList){
					String userId = resource.getUserId();
					List<Ticket>openTicketListForARecepient = session.selectList("selectOpenTicketList", userId); //for each recipient(user) fetch List of open ticket
					
						recepientMap.put(openTicketListForARecepient.size(),userId); //put size of open ticket list and user id as key value pair into a map
				
					
				}
				
				
			}
			Map<Integer, String> map = new TreeMap<Integer, String>(recepientMap);  //put the key value pair of map into a tree map to get map into sorted oreder
			/* Set set2 = map.entrySet();
	         Iterator iterator2 = set2.iterator();
	         while(iterator2.hasNext()) {
	              Map.Entry me2 = (Map.Entry)iterator2.next();
	              System.out.print(me2.getKey() + ": ");
	              System.out.println(me2.getValue());
	         }*/
	        // Map<String,String> map=new HashMap<>();
	         Map.Entry<Integer,String> entry = map.entrySet().iterator().next();
	         
	         System.out.println("entry.getValue().toString()="+entry.getValue().toString());
	         ticket.setAffectedUser(entry.getValue().toString());  //the user id  having least no of open ticket  set into ticket object in affectedUser property
			intInsertStatus = session.insert("insertTicket", ticket);//ticket details inserted into ticket table and allocated to the user id (property=affectetdUser)
			if(intInsertStatus != 0){
				TaskComment taskcomment = new TaskComment();
				taskcomment.setTaskCommentObjectId(ticket.getTicketObjectId());
				taskcomment.setUpdatedBy(ticket.getUpdatedBy());
				List<TaskComment>taskCommentList = ticket.getTaskCommentList();
				if(null != taskCommentList && taskCommentList.size() != 0){
					for(TaskComment taskCmnt : taskCommentList){
						taskcomment.setTaskCommentCode(taskCmnt.getTaskCommentCode());       //multiple keys can be mapped with category
						taskcomment.setTaskCommentDesc(taskCmnt.getTaskCommentDesc());
						int ticketKeyMapping = session.insert("insertIntoTicketKeyMapping", taskcomment); // for entering key value mapped with category
					}
				}
				
				
			}
			
			if(intInsertStatus != 0){
				if(null != ticket.getRollNumber()){
					int studentLeaveStatus = session.insert("insertIntoTicketStudentLeave",ticket); //For Student Leave if category is 'StudentLeave'
				}
			}
			if(intInsertStatus !=0){
				
				/******Sending mail and notification to ticket owner******/
				EmailDetails emailDetailsObject = new EmailDetails();
				emailDetailsObject.setEmailDetailsCode(categoryCode);
				emailDetailsObject.setEmailDetailsName(ticket.getStatus());
				
				
				List<EmailDetails> emailDetailsList = session.selectList("selectMailTemplateDetailsForACategory",emailDetailsObject);//get mail notification against category
				
				if(null != emailDetailsList && emailDetailsList.size() != 0){
					for(EmailDetails emailDetailsObj : emailDetailsList){
						String templateType = emailDetailsObj.getStatus();
						
						if(templateType.equalsIgnoreCase("Notification")){
							ticket.setModuleName(ticket.getAffectedUser());
							ServiceType ticketService = new ServiceType();
							ticketService.setTicketServiceName(categoryCode);
							ticket.setTicketService(ticketService);
							//System.out.println("ticket Rec_id====="+ticket.getModuleName());
							String comment1 = emailDetailsObj.getEmailDetailsDesc();
							ticket.setComment(comment1);
							intInsertStatus = session.insert("insertNotification", ticket);
						}else if(templateType.equalsIgnoreCase("Email")){
							
							String emailSubject = emailDetailsObj.getEmailDetailsSubject();
							EmailDetails emailDetails = new EmailDetails();
							String comment = ticket.getReportedBy()+" Generated A Ticket For A Task Named "+categoryCode;
							emailSender.sendMail(ticket.getUpdatedBy(), ticket.getAffectedUser(), emailDetails, emailSubject, emailDetailsObj.getEmailDetailsDesc());
							
						}
					}
				}
				
				
			}
			//Added By Naimisha 11042018
			ticketObjectNew1 = session.selectOne("getLatestInsertedTicket");//get tha last insertted ticket
			String ticketServiceCode = categoryCode;
			int level = 1;
			List<Task>taskList = session.selectList("getTaskListForACategory", ticketServiceCode); //getting taskList For A Category
			 if(null != taskList && taskList.size() != 0){
				 Ticket ticketObject = new Ticket();
				 ticketObject.setUpdatedBy(ticket.getUpdatedBy());
				 ticketObject.setTicketObjectId(ticket.getTicketObjectId());
				 ticketObject.setTicketCode(ticketObjectNew1.getTicketCode());
				 ticketObject.setDescription(ticket.getDescription());
				 ServiceType ticketServiceObj = new ServiceType();
				 ticketServiceObj.setTicketServiceCode(categoryCode);
				 ticketServiceObj.setTicketServiceName(ticket.getTicketService().getTicketServiceName());
				 ticketObject.setTicketService(ticketServiceObj);
				 ticketObject.setTicketSummary(ticket.getTicketSummary());
				 
				 for(int i= 0;i<taskList.size();i++){
					
					 allLevelList.add(level+"") ;
					 ticketObject.setTicketDesc(level+"");
					 
					 String taskCode = taskList.get(i).getTaskCode();
					 ticketObject.setTaskStatus(taskCode);
					 
					 String approvalRequired = session.selectOne("getTaskApprovalRequiredOrNot",taskCode);//get the type of the task
					 if(approvalRequired.equalsIgnoreCase("YES")){
						 ticketObject.setApproval("APPROVAL");
					 }else{
						 ticketObject.setApproval("NONAPPROVAL");
					 }
					 Task taskObject = session.selectOne("getDetailsOfTaskAgainstATaskCode", taskCode);// get details of the task
					 String taskAssignee = taskObject.getTaskType();
					 if(null != taskAssignee){
						 if(taskAssignee.equalsIgnoreCase("designation")){
							 Employee employee = new Employee();
							 Department department = new Department();
							 department.setDepartmentCode(taskObject.getDepartment().getDepartmentCode());
							 employee.setDepartment(department);
							 Designation designation = new Designation();
							 designation.setDesignationCode(taskObject.getDesignation().getDesignationCode());
							 employee.setDesignation(designation);
							 DesignationLevel designationLevel = new DesignationLevel();
							 designationLevel.setDesignationLevelCode(taskObject.getDesignationLevel().getDesignationLevelCode());
							 employee.setDesignationLevel(designationLevel);
							//Fetch list of resource aginst department, designation and designation level 
							 List<Resource> resourceList = session.selectList("selectResourcesForDepartmentANdDesignationANDDesignationLevel", employee);
							 
							 for(Resource resource : resourceList){
								 String userId = resource.getUserId();
								 List<Ticket>openTicketListForARecepient = session.selectList("selectOpenTicketList", userId);//for each user get no of open ticket
								 taskRecepientMap.put(openTicketListForARecepient.size(),userId);//put user id against value of open ticket  in a map
							 }
							 
							 Map<Integer, String> taskMap = new TreeMap<Integer, String>(taskRecepientMap); //short the map
							  Map.Entry<Integer,String> taskEntry = taskMap.entrySet().iterator().next();
						         
						      System.out.println("entry.getValue().toString()="+taskEntry.getValue().toString());
						      ticketObject.setAffectedUser(taskEntry.getValue().toString());  //get the userId having minimum no of open ticket
						      
						     
							
						 }else if(taskAssignee.equalsIgnoreCase("reportingManager")){
							 String userId = ticket.getUpdatedBy();
							 Resource resourceObject = session.selectOne("selectReportingManagerForAUser",userId );//get user id and name of reporting manager
							 ticketObject.setAffectedUser(resourceObject.getUserId());
						 }else if(taskAssignee.equalsIgnoreCase("classTeacher")){
							 //coding need here
							 if(categoryCode.equalsIgnoreCase("StudentLeave")){
								 String standard = ticket.getStandard();
								 String section = ticket.getSection();
								 Standard standardObj = new Standard();
								 standardObj.setStandardCode(standard);
								 standardObj.setSection(section);
								 StudentResult resource = session.selectOne("selectClassTeacherAgainstStandardAndSection", standardObj);//select class teacher against standard  and section
								 String userId = resource.getUpdatedBy();
								 ticketObject.setAffectedUser(userId);
							 }
						 }
					 }
					 
					 
					 ticketObject.setStatus(ticket.getStatus());
					 int taskUpdateStatus = session.insert("insertIntoTaskDetailsForATicket", ticketObject);//insert into task_details along with level (all tasks mapped with a ticket)
					 level++;  //increase level
					 
				 }
						 
				 String ticketCode = ticketObjectNew1.getTicketCode();
				 
					
				 Collections.sort(allLevelList);//sort the levelList
				 
				 Ticket ticketObject1 = new Ticket();
				 ticketObject1.setTicketCode(ticketCode);
				 ticketObject1.setTicketRecId(allLevelList.get(0));
				 
				 updateTaskDetails = session.update("updateTaskDetailsForMinimumLevel",ticketObject1);  //update the task set is_active = true in task_details table having minimum level
				 
				
				 
				 Ticket ticketObjectNew = session.selectOne("selectTaskCodeForATicketOfParticularLevel", ticketObject1); //get the lask updated task
				 
				 session.commit();
				 
				 String taskCode = ticketObjectNew.getTicketCode();  //store the task code
				 
					
				 if(updateTaskDetails != 0){
					 	Task task = new Task();
					 	task.setTaskCode(taskCode);
					 	task.setTaskDesc("OPEN");
					 	List<EmailDetails> templateDetailsList = session.selectList("selectMailTemplateDetailsForATask",task); //get template List against task code
					 	
						 	if(null != templateDetailsList && templateDetailsList.size() !=0){
						 		for(EmailDetails emailDetailsObj : templateDetailsList){
						 			String templateType = emailDetailsObj.getStatus();
						 			if(templateType.equalsIgnoreCase("Notification")){
						 				Ticket ticketObj2 = new Ticket();
										ticketObj2.setTicketObjectId(ticket.getTicketObjectId());
										ticketObj2.setModuleName(ticketObjectNew.getAffectedUser());
										ticketObj2.setUpdatedBy(ticket.getUpdatedBy());
										ServiceType ticketService = new ServiceType();
										ticketService.setTicketServiceName(taskCode);
										ticketObj2.setTicketService(ticketService);
										
										ticketObj2.setComment(emailDetailsObj.getEmailDetailsDesc());
										int intInsertStatus1 = session.insert("insertIntoNotificationForTask", ticketObj2);//send notification
						 			}else if(templateType.equalsIgnoreCase("Email")){
						 				EmailDetails emailDetails = new EmailDetails();
						 				String emailSubject = emailDetailsObj.getEmailDetailsSubject(); //send mail
						 				emailSender.sendMail(ticket.getUpdatedBy(), ticketObjectNew.getAffectedUser(), emailDetails, emailSubject, emailDetailsObj.getEmailDetailsDesc());
										
						 			}
						 		}
						 	}
					 
					}
				 
				 session.commit();
			 }
			 
			
			 
			 
			/* String ticketCode = ticketObjectNew1.getTicketCode();
			 Collections.sort(allLevelList);
			 Ticket ticketObject = new Ticket();
			 ticketObject.setTicketCode(ticketCode);
			 ticketObject.setTicketRecId(allLevelList.get(0));*/
			 
			 
			 
			
			 
			 //For Mail and Notification 12042018
			 /*Start*/
			 

			 
			 
			 
			 
			 /*End*/
			
			
			
			
		} catch (Exception e) {
			status = "fail";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			logger.error("generateTicket() In TicketDAOImpl.java: Exception" + e);
			
		} finally {
			session.close();
			
		}
		ticketObjectNew1.setStatus(status);
		
		return ticketObjectNew1;
		}
	
	
	@Override
	public String submitTicketAttachmentDoc(Ticket ticket) {
		SqlSession session = sqlSessionFactory.openSession();
		String uploadStatus = "Fail";
		try{
			ticket.setTicketObjectId(encryptDecrypt.encrypt("TicketDaoImpl"));
			int intInsertStatus = session.insert("submitTicketAttachmentDoc", ticket);
			if (intInsertStatus != 0) {				
				uploadStatus = "success";
			}
		}catch(Exception e){
			logger.error("In submitTicketAttachmentDoc() In TicketDaoImpl.java: Exception" + e);
		}
		return uploadStatus;
		
	}
	
	@Override
	public List<Ticket> getTicketList(String updatedBy) {
		List<Ticket> ticketList = null;
		List<Ticket> ticketListForServiceOwner = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getTicketList() method of TicketDaoImpl");
			session = sqlSessionFactory.openSession();
			ticketList = session.selectList("getTicketList", updatedBy);
			ticketListForServiceOwner = session.selectList("getTicketListForServiceOwner", updatedBy);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getTicketList() In TicketDaoImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTicketList() In TicketDaoImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		if(ticketList==null ||ticketList.size()== 0){
			return ticketListForServiceOwner;
		}else{
			return ticketList;
		}
	}
	
	@Override
	public List<Ticket> getClosedTicketList(String updatedBy) {
		List<Ticket> ticketList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getClosedTicketList() method of AdminDaoImpl");			
			ticketList = session.selectList("getClosedTicketList", updatedBy);			
			List<Ticket> inwardClosedTicketList = session.selectList("selectInwardClosedTicketList", updatedBy);
			/*if(ticketList!=null && ticketList.size()!=0){
				if(inwardClosedTicketList != null && inwardClosedTicketList.size()!=0){
					ticketList.addAll(inwardClosedTicketList);
				}
			}else{
				ticketList=inwardClosedTicketList;
			}*/
		} catch (NullPointerException e) {
			logger.error("getClosedTicketList() In TicketDaoImpl.java: NullPointerException"	+ e);
		} catch (Exception e) {
			logger.error("getClosedTicketList() In TicketDaoImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return ticketList;
	}
	
	@Override
	public Ticket getTicketDetails(Ticket ticket) {
		SqlSession session = sqlSessionFactory.openSession();
		Ticket ticketObject = null;
		try {
			logger.info("In getTicketDetails() method of TicketDAOImpl");
			System.out.println("ticketCode===="+ticket.getTicketCode());
			System.out.println("ticket updated by====="+ticket.getAffectedUser());
			ticketObject = session.selectOne("getTicketDetailsForEdit", ticket);		
			if(null == ticketObject){
				ticketObject = session.selectOne("getTicketDetailsForEditNew", ticket);
			}
		
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getTicketDetails() In TicketDAOImpl.java: NullPointerException" + e);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTicketDetails() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return ticketObject;
	}
	
	/***
	 * modified by naimisha.ghosh
	 * changes taken on 12042017
	 * **/
	
	@Override
	public Ticket updateTicket(Ticket ticket) {
		SqlSession session = sqlSessionFactory.openSession();
		int ticketUpdateStatus = 0;
		try {
			logger.info("In updateTicket() method of TicketDaoImpl");
			ticket.setTicketObjectId(encryptDecrypt.encrypt("CommonDaoImpl"));
			
			String ticketCodeValue = ticket.getTicketCode(); //store ticket code value
			String ticketStatus = ticket.getStatus(); //store ticket status
			
			String ticketStatusType = session.selectOne("selectTicketStatusTypeAgainstStatus", ticketStatus); //get ticket status type against ticket status
			ticket.setTaskStatus(ticketStatusType);
			
			 ticketUpdateStatus = session.update("updateTicket", ticket); //update status into ticket table
			 if(ticketUpdateStatus != 0){
				 if(ticket != null){
						if (ticket.getComment() != null && ticket.getComment().trim().length() != 0) {
							session.insert("updateTicketComment", ticket); //update into ticket comment
							session.commit();
						}
					}
			 }
			 
			
			 if(ticketUpdateStatus != 0){
				 int attendanceInsertStatus = 0;
				 //for student attendance if ticket status type is CLOSED or COMPLETED then send json to portal with data of leave and insert data into attendance of icam side
				 if((ticket.getTicketService().getTicketServiceCode()).equalsIgnoreCase("StudentLeave")){
					 if((ticketStatusType.equalsIgnoreCase("CLOSED"))||(ticketStatusType.equalsIgnoreCase("COMPLETED"))){
						 List<StudentAttendance>studentAttendanceList = ticket.getStudentAttendanceList();
						 sendAttandaceTOPortalAndSaveInStudentAttendance(studentAttendanceList,ticket);
						}
					 	
						
							
				 }
			 }
			 if(null != ticketStatusType){
				 if(ticketStatusType.equalsIgnoreCase("OPEN")){
					 
					 //if there is any additional task
					 List<String>taskList = ticket.getTaskList();
					 List<String>levelList = ticket.getLeveList();
					 List<String>resourceList = ticket.getResourceList();
					 
					 if(null != taskList && taskList.size() != 0){
						 for(int i= 0;i<taskList.size();i++){
								 ticket.setAffectedUser(resourceList.get(i));
								 ticket.setTaskStatus(taskList.get(i));
								 ticket.setTicketDesc(levelList.get(i));
								
								 Ticket ticketObj = new Ticket();
								 ticketObj.setUpdatedBy(ticket.getUpdatedBy());
								 ticketObj.setTicketObjectId(ticket.getTicketObjectId());
								 ticketObj.setAffectedUser(ticket.getAffectedUser());
								 ticketObj.setTicketCode(ticket.getTicketCode());
								 ticketObj.setTaskStatus(ticket.getTaskStatus());
								 ticketObj.setTicketDesc(ticket.getTicketDesc());
								 ticketObj.setDescription(ticket.getDescription());
								 ServiceType ticketServiceObj = new ServiceType();
								 ticketServiceObj.setTicketServiceCode(ticket.getTicketService().getTicketServiceCode());
								 ticketServiceObj.setTicketServiceName(ticket.getTicketService().getTicketServiceName());
								 ticketObj.setTicketService(ticketServiceObj);
								 
								 String taskCode = ticket.getTaskStatus();
								 String approvalRequired = session.selectOne("getTaskApprovalRequiredOrNot",taskCode);
								 if(approvalRequired.equalsIgnoreCase("YES")){
									 ticketObj.setApproval("APPROVAL");
								 }else{
									 ticketObj.setApproval("NONAPPROVAL");
								 }
								 ticketUpdateStatus = session.insert("insertIntoTaskDetailsForATicket", ticketObj);
								 session.commit();
							 //Added on 06022018
							 
									
								
								
								
								
							
						 	}
						 
						 	
						 
							/* String ticketCode = ticket.getTicketCode();
							 
							 List<String> allLevelList = session.selectList("getAllLevelListOfTasksForATicket",ticketCode); 
							 Collections.sort(allLevelList);
							 
							 Ticket ticketObject = new Ticket();
							 ticketObject.setTicketCode(ticketCode);
							 ticketObject.setTicketRecId(allLevelList.get(0));
							 
							 int updateTaskDetails = session.update("updateTaskDetailsForMinimumLevel",ticketObject);
							 
							 Ticket ticketObjectNew = session.selectOne("selectTaskCodeForATicketOfParticularLevel", ticketObject);
							//Modified By Naimisha 22032018
							 String taskCode = ticketObjectNew.getTicketCode();
							 
							 List<EmailDetails> templateDetailsList = session.selectList("selectMailTemplateDetailsForATicket",ticket);
							 if(updateTaskDetails != 0){
								 
								 if(null != templateDetailsList && templateDetailsList.size() !=0){
								 		for(EmailDetails emailDetailsObj : templateDetailsList){
								 			String templateType = emailDetailsObj.getStatus();
								 			if(templateType.equalsIgnoreCase("Notification")){
												
												Ticket ticketObj2 = new Ticket();
												ticketObj2.setTicketObjectId(ticket.getTicketObjectId());
												
												ticketObj2.setModuleName(ticketObjectNew.getAffectedUser());
												ticketObj2.setUpdatedBy(ticket.getUpdatedBy());
												ServiceType ticketService = new ServiceType();
												ticketService.setTicketServiceName(taskCode);
												ticketObj2.setTicketService(ticketService);
												
												
												ticketObj2.setComment(emailDetailsObj.getEmailDetailsDesc());
												int intInsertStatus1 = session.insert("insertIntoNotificationForTask", ticketObj2);
												
								 			}else if(templateType.equalsIgnoreCase("Email")){
								 				EmailDetails emailDetails = new EmailDetails();
								 				String emailSubject = emailDetailsObj.getEmailDetailsSubject();
								 				emailSender.sendMail(ticket.getUpdatedBy(), ticketObjectNew.getAffectedUser(), emailDetails, emailSubject, emailDetailsObj.getEmailDetailsDesc());
								 			}
								 		}
								 	}
								 
								//mail for  ticket for ticket creator
								 	EmailDetails emailDetails = new EmailDetails();
									String mailSubject = "Information Of Ticket No - "+ticket.getTicketCode();
									String mailDesc = "Some Action is taken against this ticket";
									emailSender.sendMail(ticket.getUpdatedBy(), ticket.getReportedBy(), emailDetails, mailSubject, mailDesc);
									
								}*/
								
					 }
					 
				 }else if(ticketStatusType.equalsIgnoreCase("REJECTED")){
					 if(ticketUpdateStatus !=0){
						 
						 	Ticket ticketObjectNew = new Ticket();
						 	ticketObjectNew.setTicketRecId(ticketCodeValue);
						 	ticketObjectNew.setUpdatedBy(ticket.getUpdatedBy());
						 	
						 	int updateStatus = session.update("updateTablesRelatedToATicketAndtask", ticketObjectNew); //call a procedure to set is_active = false 
						 																								//in all records which are entered into database against ticket no  
						 	
						 	TicketStatus taskStatusObj = session.selectOne("selectTaskStatusAgainstTicketStatus", ticketStatus);  //select task status against ticket status
						 	
						 	String taskStatus = taskStatusObj.getTicketStatusCode();
						 	
						 	ticketObjectNew.setTaskStatus(taskStatus);
						 	int updateTaskStatus = session.update("updateTaskDetailsStatus", ticketObjectNew); // update task_details table with value of task status
						 	
						 	
						 	
						 	List<EmailDetails> templateDetailsList = session.selectList("selectMailTemplateDetailsForATicket",ticket);//select template list agaist ticket code
						 	
						 	
						 	
						 	
						 	if(null != templateDetailsList && templateDetailsList.size() !=0){
						 		for(EmailDetails emailDetailsObj : templateDetailsList){
						 			String templateType = emailDetailsObj.getStatus();
						 			if(templateType.equalsIgnoreCase("Notification")){
										
										Ticket ticketObject = new Ticket();
									 	ticketObject.setTicketObjectId(ticket.getTicketObjectId());
										
									 	ticketObject.setModuleName(ticket.getReportedBy());
									 	ticketObject.setUpdatedBy(ticket.getUpdatedBy());
										ServiceType ticketService = new ServiceType();
										ticketService.setTicketServiceName(ticket.getTicketService().getTicketServiceName());
										ticketObject.setTicketService(ticketService);
										
										
										ticketObject.setComment(emailDetailsObj.getEmailDetailsDesc());
										int intInsertStatus1 = session.insert("insertIntoNotificationForTask", ticketObject);
										
						 			}else if(templateType.equalsIgnoreCase("Email")){
						 				EmailDetails emailDetails = new EmailDetails();
						 				String emailSubject = emailDetailsObj.getEmailDetailsSubject();
						 				emailSender.sendMail(ticket.getUpdatedBy(), ticket.getReportedBy(), emailDetails, emailSubject, emailDetailsObj.getEmailDetailsDesc());
										
						 			}
						 		}
						 	}
						 	
					 }
					
				 }else if((ticketStatusType.equalsIgnoreCase("CLOSED"))||(ticketStatusType.equalsIgnoreCase("COMPLETED"))){
					 if(ticketUpdateStatus !=0){
						 List<EmailDetails> templateDetailsList = session.selectList("selectMailTemplateDetailsForATicket",ticket);  //get template list against ticket code
						 if(null != templateDetailsList && templateDetailsList.size() !=0){
							
						 		for(EmailDetails emailDetailsObj : templateDetailsList){
						 			String templateType = emailDetailsObj.getStatus();
						 			if(templateType.equalsIgnoreCase("Notification")){
										
										Ticket ticketObject = new Ticket();
									 	ticketObject.setTicketObjectId(ticket.getTicketObjectId());
										
									 	ticketObject.setModuleName(ticket.getReportedBy());
									 	ticketObject.setUpdatedBy(ticket.getUpdatedBy());
										ServiceType ticketService = new ServiceType();
										ticketService.setTicketServiceName(ticket.getTicketService().getTicketServiceName());
										ticketObject.setTicketService(ticketService);
										
										
										ticketObject.setComment(emailDetailsObj.getEmailDetailsDesc());
										int intInsertStatus1 = session.insert("insertIntoNotificationForTask", ticketObject);
										
						 			}else if(templateType.equalsIgnoreCase("Email")){
						 				EmailDetails emailDetails = new EmailDetails();
						 				String emailSubject = emailDetailsObj.getEmailDetailsSubject();
						 				emailSender.sendMail(ticket.getUpdatedBy(), ticket.getReportedBy(), emailDetails, emailSubject, emailDetailsObj.getEmailDetailsDesc());
										
						 			}
						 		}
						 	}
						 	
					 }
				 }
			 }
			 session.commit();
				 
			 if(ticket != null && ticketUpdateStatus != 0){
					
					ticket.setMessage("success");
			}else{
				ticket.setStatus("fail");
				ticket.setMessage("fail");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("updateTicket() In TicketDaoImpl.java: Exception", e);
			
		} finally {
			session.close();
		}
		return ticket;
	}

	
	@Override
	public List<FinancialYear> getFinancialYearList() {
		logger.info("In getFinancialYearList() method of CommonDaoImpl");
		List<FinancialYear> financialYearList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			financialYearList = session.selectList("getFinancialYearList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return financialYearList;
	}
	
	@Override
	public List<IncomeAge> getIncomeAgeList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<IncomeAge> incomeAgeList = null;
		try{			
			incomeAgeList=session.selectList("getIncomeAgeList");
			//System.out.println("AAAAA:"+incomeAgeList.size());
		}catch(NullPointerException e){
			logger.error("Error In getIncomeAgeList() method of CommonDAOImpl:NullPointerException:: ",e);
			e.printStackTrace();
		}catch(Exception e){
			logger.error("Error In getIncomeAgeList() method of CommonDAOImpl :Exception:: ",e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return incomeAgeList;
	}
	
	@Override
	public List<TeachingLevel> getTeachingLevelList() {
		List<TeachingLevel> teachingLevelList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("getpositionList() method in BackOfficeDaoImpl");			
			teachingLevelList = session.selectList("selectTeachingLevel");
		} catch (Exception e) {
			logger.error("getpositionList() method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		return teachingLevelList;
	}
	
///-------------------------FOR ADMISSION--------------------------------
	
	@Override
	public List<SocialCategory> getExistingSocialCategory() {
		SqlSession session =sqlSessionFactory.openSession();
		List<SocialCategory> getSocialCategoryList = null;
		try {
			getSocialCategoryList = session.selectList("getExistingSocialCategory");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return getSocialCategoryList;
	}
	
	@Override
	public List<AdmissionDrive> getDriveList(Resource r) {
		logger.info(" getDriveList(Resource r) method in CommonDaoImpl");
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionDrive> getCurrentDriveList = null;
		try {
			getCurrentDriveList = session.selectList("selectCurrenntAdmissionDriveList", r);
		} catch (Exception e) {
			logger.error("Exception in CommonDaoImpl : method: getDriveList(Resource r) ", e);
		} finally {
			session.close();
		}
		return getCurrentDriveList;
	}
	
	
	@Override
	public List<MeritListType> getMeritListTypes() {
		SqlSession session =sqlSessionFactory.openSession();
		List<MeritListType> meritListType = null;
		try {
			meritListType = session.selectList("selectMeritListTypes");
		} catch (Exception e) {
			logger.error("Exception in getMeritListTypes() in AdmissionDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return meritListType;
	}
	
	
	@Override
	public List<Venue> getExamVenues(Map<String, Object> parameters) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Venue> venueList = null;
		try {
			venueList = session.selectList("selectExamVenues",parameters);
		} catch (Exception e) {
			logger.error("Exception in getExamVenues() in AdmissionDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return venueList;
	}
	
	@Override
	public int getTotalAllotedSeat(Venue venue) {
		SqlSession session =sqlSessionFactory.openSession();
		Integer numberOfAllotedSeat = 0;
		try {
			numberOfAllotedSeat = session.selectOne("selectTotalAllotedSeat",venue);
			if(numberOfAllotedSeat==null)
				numberOfAllotedSeat=0;
		} catch (Exception e) {
			logger.error("Exception in getTotalAllotedSeat() in AdmissionDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return numberOfAllotedSeat;
	}
	
	@Override
	public Venue getExamVenueDetails(Venue venue) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			venue = session.selectOne("selectExamVenueDetails",venue);
		} catch (Exception e) {
			logger.error("Exception in getExamVenueDetails() in AdmissionDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return venue;
	}
	

	@Override
	public List<Task> getDelegatedTask(Task task) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Task> taskList = null;
		try {
			taskList = session.selectList("selectInwardTaskList",task);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getDelegatedTask() in AdmissionDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return taskList;
	}

	@Override
	public List<Task> getOutwardDelegatedTask(Task task) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Task> taskList = null;
		try {
			taskList = session.selectList("selectOutwardTaskList",task);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getDelegatedTask() in AdmissionDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return taskList;
	}
	
	@Override
	public String editVendorDetails(Vendor vendor) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Update Failed.";
		try {
			int statusValue = session.update("editVendorDetails", vendor);
			if (statusValue != 0) {
				status = "Vendor update successful";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}
	
	@Override
	public Notification deleteEmailDetails(Notification emailNotification) {
		int  deleteStatus=0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			  if(emailNotification!=null && emailNotification.getEmailDetailsList()!=null && emailNotification.getEmailDetailsList().size()!=0){
				  for(EmailDetails emailDetails : emailNotification.getEmailDetailsList()){
						emailDetails.setUpdatedBy(emailNotification.getUpdatedBy());
						deleteStatus = session.delete("deleteFromEmailAlert", emailDetails);
					}
			  }
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In deleteEmailDetails(Notification emailNotification) of commonDaoImpl - problem in catch",e);
		} finally {
			session.close();
		}
		return getEmailDetails(emailNotification.getUpdatedBy());
	}

	@Override
	public FinancialYear getFinancialYear() {
		SqlSession session =sqlSessionFactory.openSession();
		FinancialYear financialYear = null;
		try{			
			financialYear=session.selectOne("selectCurrentFinancialYear");
		}catch(NullPointerException e){
			logger.error("Error In getFinancialYear() method of CommonDAOImpl:NullPointerException:: ",e);
		}catch(Exception e){
			logger.error("Error In getFinancialYear() method of CommonDAOImpl :Exception:: ",e);
		}finally{
			session.close();
		}
		return financialYear;
	}

	
/**author naimisha for finance*/
	
	@Override
	public List<AcademicYear> getAllAcademicYear() {
		SqlSession session =sqlSessionFactory.openSession();
		List<AcademicYear> AcademicYearList = null;
		try{			
			logger.info("getAllAcademicYear() method in ReportDaoImpl");
			AcademicYearList = session.selectList("getAllAcademicYear");
		}catch(Exception e){
			logger.error("Exception in getAllAcademicYear() method:in ReportDaoImpl", e);
			e.printStackTrace();
		}finally{
			session.close();
		}	
		return AcademicYearList;
	}
	
	/**@author anup.roy
	 * modified on 190117*/
	
	@Override
	public List<Course> getCourseForStandard(String standard){
		SqlSession session = sqlSessionFactory.openSession();
		List<Course> courseList = null;
		try {
			//System.out.println("standard::"+standard);
			courseList = session.selectList("getCourseForStandard", standard);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getCourseTypeList() of AcademicsDAOImpl : " ,e);
		} finally {
			session.close();
		}
		return courseList;
	}

	@Override
	public List<Subject> getSubject(){
		List<Subject> subjectList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			subjectList=session.selectList("selectSubjectList");
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return subjectList;
	}
	
	@Override
	public String getStandatdCodeAgainstCourse(String course) {
		String standard  = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			standard=session.selectOne("selectStandardAgainstCourse",course);
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return standard;
	}
	
	@Override
	public List<Course>getCourseList()  {
		SqlSession session = sqlSessionFactory.openSession();
		List<Course> courseList = null;
		try {			
			courseList = session.selectList("getCourseList1");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getCourseTypeList() of AcademicsDAOImpl : " ,e);
		}
			
		
	finally {
			session.close();
		}
		
		return courseList;
	}


@Override
	public List<Section> getBatchAgainstCourse(String course) {
		List<Section> sectionList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			sectionList = session.selectList("selectBatchAgainstCourse",course);
			//System.out.println("inside dao:");
		} catch (Exception e) {
			
			logger.debug("Exception In getSectionAgainstStandard() of commonDaoImpl");
		} finally {
			session.close();
		}
		return sectionList;
	}


/*********Added By Naimisha 04042017***********/
@Override
public String updateTicketAndTaskStatus(Ticket ticket) {
	SqlSession session =sqlSessionFactory.openSession();
	String updateStatus = "success";
	int insertStatus = 0;
	int updateValue = 0;
	try {
		String userId ;
		ticket.setTicketObjectId(encryptDecrypt.encrypt("CommonDaoImpl"));
		//System.out.println("status = =="+ticket.getStatus());
		//int updateValue = session.update("updateTicketAndTaskStatus", ticket);
		
		//System.out.println("updateValue====="+updateValue);
		if(ticket.getStatus().equalsIgnoreCase("WORK_IN_PROGRESS")){
			updateValue = session.update("updateTicketAndTaskStatusToWorkInProgress", ticket);
		}
		List<Approver> approverList = session.selectList("selectApproverDetailsForATicket",ticket);
		//System.out.println("approverList list size ====="+approverList.size());
		userId = ticket.getUpdatedBy();
		Resource notificationFromResource = session.selectOne("selectUserNameByUserId",userId );
		if(ticket.getStatus().equalsIgnoreCase("CLOSED")){
			 updateValue = session.update("updateTicketAndTaskStatusToClosed", ticket);
			for(Approver approver : approverList){
				ticket.setModuleName(approver.getUpdatedBy());
				ServiceType serviceType = new ServiceType();
				serviceType.setTicketServiceName(ticket.getTicketDesc());
				//userId = approver.getUpdatedBy();
				ticket.setTicketService(serviceType);
				//Resource notificationToResource = session.selectOne("selectUserNameByUserId",userId );
				String msg = notificationFromResource.getUpdatedBy() + " Closed Task Named "+ticket.getTicketDesc();
				ticket.setComment(msg);
				insertStatus = session.insert("insertNotification", ticket);
				//System.out.println("InsertStatus==="+insertStatus);
			}
		}
		if(ticket.getStatus().equalsIgnoreCase("REJECTED")){
			 updateValue = session.update("updateTicketAndTaskStatusToRejected", ticket);
			for(Approver approver : approverList){
				ticket.setModuleName(approver.getUpdatedBy());
				ServiceType serviceType = new ServiceType();
				serviceType.setTicketServiceName(ticket.getTicketDesc());
				//userId = approver.getUpdatedBy();
				//Resource notificationToResource = session.selectOne("selectUserNameByUserId",userId );
				String msg = notificationFromResource.getUpdatedBy() + " Rejected Task Named "+ticket.getTicketDesc();
				ticket.setComment(msg);
				insertStatus = session.insert("insertNotification", ticket);
				//System.out.println("InsertStatus==="+insertStatus);
			}
		}
		insertStatus =  session.insert("insertIntoTaskComment",ticket);
		//if(updateStatus!=0)
		session.commit();
	}catch (Exception e) {
		updateStatus = "fail";
		e.printStackTrace();
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		logger.error("updateTicketAndTaskStatus(Ticket ticket) In CommonDaoImpl.java: Exception",e); 
	} finally {
		session.close();
	}
	return updateStatus;
}

@Override
public List<Task> getAllTaskCommentForATask(Task task) {
	List<Task> taskCommentList = null;
	SqlSession session =sqlSessionFactory.openSession();
	try {			
		taskCommentList = session.selectList("selectAllTaskCommentForATask",task);
		//System.out.println("inside dao:");
	} catch (Exception e) {
		
		logger.debug("Exception In getAllTaskCommentForATask() of commonDaoImpl");
	} finally {
		session.close();
	}
	return taskCommentList;
}

	@Override
	public List<Section> getSectionsAgainstStandard(String standard) throws CustomException {
		List<Section> sectionList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			sectionList = session.selectList("selectSectionsAgainstStandard",standard);		
		} catch (Exception e) {
			
			logger.debug("Exception In getSectionAgainstStandard() of commonDaoImpl");
		} finally {
			session.close();
		}
		return sectionList;
	}

	/********Added By Naimisha 22042017**********/
	@Override
	public List<Course> getCourseListForAStudent(String userId) {
		List<Course> courseList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			courseList = session.selectList("selectCourseListForAStudent",userId);		
		} catch (Exception e) {
			
			logger.debug("Exception In getCourseListForAStudent() of commonDaoImpl");
		} finally {
			session.close();
		}
		return courseList;
	}
	
	@Override
	public Course getCourseAgainstCourseCode(String courseCode ,String userId ) {
		SqlSession session =sqlSessionFactory.openSession();
		Course course = null;
		try {
			Course courseObj = new Course();
			courseObj.setUpdatedBy(userId);
			courseObj.setCourseCode(courseCode);
			 course = session.selectOne("selectCourseAgainstCourseCode",courseObj);		
		} catch (Exception e) {
			
			logger.debug("Exception In getCourseListForAStudent() of commonDaoImpl");
		} finally {
			session.close();
		}
		return course;
	}

	@Override
	public Resource getResourceAgainstUserId(String userId) {
		SqlSession session =sqlSessionFactory.openSession();
		Resource resource = null;
		try {
			resource = session.selectOne("selectResourceAgainstUserId",userId);		
		} catch (Exception e) {
			
			logger.debug("Exception In getResourceAgainstUserId() of commonDaoImpl");
		} finally {
			session.close();
		}
		return resource;
	}

	@Override
	public String getrollNumberAgainstProgramAndUserId(String userId, String course) {
		SqlSession session =sqlSessionFactory.openSession();
		String rollNumber = null;
		try {
			Course courseObj = new Course();
			courseObj.setUpdatedBy(userId);
			courseObj.setCourseCode(course);
			rollNumber = session.selectOne("selectRollNumberAgainstProgramAndUserId",courseObj);		
		} catch (Exception e) {
			
			logger.debug("Exception In getrollNumberAgainstProgramAndUserId() of commonDaoImpl");
		} finally {
			session.close();
		}
		return rollNumber;
	}
	
	@Override
	public String submitVendorType(VendorType vendorType) {
		SqlSession session =sqlSessionFactory.openSession();
		String submitResponse = "";
		try{
			vendorType.setVendorObjectId(encryptDecrypt.getBase64EncodedID("CommonDAOImpl"));
			int insertStatus = session.insert("insertVendorType", vendorType);			 
			if(insertStatus == 1){
				submitResponse = "Success";
			}
			else{
				submitResponse = "Fail";
			}
		}catch(Exception e){
			
			e.printStackTrace();
			logger.debug("Exception In submitVendorType() of commonDaoImpl",e);
		}
		return submitResponse;
	}

	@Override
	public String editVendorType(VendorType vendorType) {
		SqlSession session =sqlSessionFactory.openSession();
		String updateResponse = "";
		try{
			int updateStatus = session.update("updateVendorType", vendorType);
			if(updateStatus == 1){
				updateResponse = "Success";
			}else{
				updateResponse = "Fail";
			}
		}catch(Exception e){
			e.printStackTrace();
			updateResponse = "Fail";
		}
		return updateResponse;
	}
	
	@Override
	public String inactiveVendorDetails(Vendor vendor) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			vendor.setVendorObjectId(encryptDecrypt.getBase64EncodedID("CommonDAOImpl"));
			session.update("inactiveVendorDetails", vendor);	
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}

	@Override
	public String insertEmailNotification(Notification emailNotification) {
		SqlSession session =sqlSessionFactory.openSession();
		String submitResponse = "";
		try{
			emailNotification.setNotificationObjectId(encryptDecrypt.getBase64EncodedID("CommonDAOImpl"));
			int insertStatus = session.insert("insertIntoEmailNotification", emailNotification);			 
			if(insertStatus == 1){
				submitResponse = "success";
			}
		}catch(Exception e){
			submitResponse = "fail";
			e.printStackTrace();
			logger.debug("Exception In submitVendorType() of commonDaoImpl",e);
		}
		return submitResponse;
	}

	@Override
	public List<EmailDetails> getsentEmailDetailsList(String userId) {
		SqlSession session =sqlSessionFactory.openSession();
		List<EmailDetails> sentEmailsDetailsList = null;
		try {
			
			sentEmailsDetailsList = session.selectList("getSentEmailDetailsList",userId);		
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception In getrollNumberAgainstProgramAndUserId() of commonDaoImpl");
		} finally {
			session.close();
		}
		return sentEmailsDetailsList;
	}

	@Override
	public EmailDetails getEmailDetailsAgainstEmailCode(String emailId) {
		SqlSession session =sqlSessionFactory.openSession();
		EmailDetails emailDetails= null;
		try {
			
			emailDetails = session.selectOne("getEmailDetailsAgainstEmailCode",emailId);
			//anup.roy 12072017 for update the read status of mail
			session.update("updateEmailReadStatus", emailId);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception In getrollNumberAgainstProgramAndUserId() of commonDaoImpl");
		} finally {
			session.close();
		}
		return emailDetails;
	}

	@Override
	public EmailDetails getEmailContentForSentItemsAgainstEmailCode(String emailId) {
		SqlSession session =sqlSessionFactory.openSession();
		EmailDetails emailDetails= null;
		try {
			
			emailDetails = session.selectOne("getEmailContentForSentItemsAgainstEmailCode",emailId);		
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception In getrollNumberAgainstProgramAndUserId() of commonDaoImpl");
		} finally {
			session.close();
		}
		return emailDetails;
	}

	@Override
	public String inactiveEmailFromSentBox(List<EmailDetails> emailDetailsList) {
		SqlSession session =sqlSessionFactory.openSession();
		String updateStatus = "success";
		try {
			for(EmailDetails emailDetails:emailDetailsList){
				
				session.update("inactiveEmailFromSentBox",emailDetails);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			updateStatus = "fail";
			logger.debug("Exception In inactiveEmailFromSentBox(emailDetailsList) of commonDaoImpl");
		} finally {
			session.close();
		}
		return updateStatus;
	}

	@Override
	public List<EmailDetails> inactiveEmailFromInBox(List<EmailDetails> emailDetailsList) {
		SqlSession session =sqlSessionFactory.openSession();
		String updateStatus = "success";
		List<EmailDetails>emailList = null;
		try {
			for(EmailDetails emailDetails:emailDetailsList){
				
				session.update("inactiveEmailFromInBox",emailDetails);
			}
			String userId = emailDetailsList.get(0).getUpdatedBy(); 
			emailList = session.selectList("selectEmailDetailsFromEmailReceived", userId);
			
		} catch (Exception e) {
			e.printStackTrace();
			updateStatus = "fail";
			logger.debug("Exception In inactiveEmailFromInBox(emailDetailsList) of commonDaoImpl");
		} finally {
			session.close();
		}
		return emailList;
	}
	
	/**@author Saif.Ali
	 * Date- 19/07/2017
	 * Used to get the asset type commodities from the commodity*/
	/*@Override
	public  List<AnnualStock> selectAssetListForASTB() {
		SqlSession session =sqlSessionFactory.openSession();
		String updateStatus = "success";
		 List<AnnualStock> selectAssetListForASTB = null;
		try {
			selectAssetListForASTB = session.selectList("selectAssetListForASTB");
			
		} catch (Exception e) {
			e.printStackTrace();
			updateStatus = "fail";
			logger.debug("Exception In selectAssetListForASTB() of commonDaoImpl");
		} finally {
			session.close();
		}
		return selectAssetListForASTB;
	}*/
	
	/*added by ranita.sur on 02082017 For Vendor emailId Validation */
	@Override
	public String serverSideValidationOfVendorEmailId(String emailId) {
		SqlSession session = sqlSessionFactory.openSession();
		String emailIdDetails = null;
		try {
			logger.info("Executing serverSideValidationOfVendorEmail() from CommonDaoImpl");
			emailIdDetails = session.selectOne("serverSideValidationOfVendorEmail", emailId);
		} catch (Exception e) {
			logger.error("Exception occured while executing serverSideValidationOfEmployeeCode() from ERPDAOImpl", e);
		} finally {
			session.close();
		}
		return emailIdDetails;
	}
	
	@Override
	public Employee getStaffSalaryDetailsForSalarySlip(Resource resource) {
		logger.info("In getStaffSalaryDetailsForSalarySlip(Resource resource) method of BackOfficeDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();
		Employee staffSalaryDetails = null;
		String actualWorkingDays = null;
		//Set<String> setList = new HashSet<String>();
		List<SalaryBreakUp> taxList = new ArrayList<SalaryBreakUp>();
		int grossAmount = 0;
		int extraIncome = 0;
		int totalDeductedAmount = 0;
		int netAmount = 0;
		try {
			staffSalaryDetails = session.selectOne("getStaffDetails", resource);				
			if (staffSalaryDetails != null) {
				System.out.println("user id======"+resource.getUserId());
				System.out.println("user id======"+resource.getStartDate());
				System.out.println("user id======"+resource.getEndDate());
				
				String existingStatus = session.selectOne("selectExistingStatusForDisburseSalaryDetails", resource);//salary disbursed or not
				
				System.out.println("existingStatus==="+existingStatus);
				staffSalaryDetails.setStatus(existingStatus);
				staffSalaryDetails.getResource().setStartDate(resource.getStartDate());
				staffSalaryDetails.getResource().setEndDate(resource.getEndDate());
				if(null != existingStatus){
					SalaryBreakUp individualDeduction = session.selectOne("selectIndividualDeduction", resource);
					staffSalaryDetails.setIndividualDeduction(individualDeduction);
					List<SalaryBreakUp> salaryBreakUpListForGross  = session.selectList("getSalaryBreakUpListForGrossForSalarySlipView", staffSalaryDetails); //getinng salaryBrekup of type EARNING
					List<SalaryBreakUp> salaryBreakUpListForDeduction  = session.selectList("getSalaryBreakUpListForNetForSalarySlipView", staffSalaryDetails);//getinng salaryBrekup of type DEDUCTION
					
					if(null != salaryBreakUpListForGross & salaryBreakUpListForGross.size() !=0){
						for(SalaryBreakUp salObjForGross : salaryBreakUpListForGross){
							grossAmount = (int) (grossAmount + salObjForGross.getAmount());						
						}
					}
					
					if(null != salaryBreakUpListForDeduction & salaryBreakUpListForDeduction.size()!=0){
						for(SalaryBreakUp salObjForNet : salaryBreakUpListForDeduction){
							totalDeductedAmount = (int) (totalDeductedAmount + salObjForNet.getAmount());
						}
					}
					
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
					
					List<SalaryBreakUp> salaryBreakUpList = session.selectList("getSalaryBreakUPDetailsForSalarySlipDetails", staffSalaryDetails); //getting all salary breakup for view
					if (salaryBreakUpList != null && salaryBreakUpList.size() != 0) {
						salaryBreakUpList.addAll(taxList);
						//System.out.println("ABCasas:"+salaryBreakUpList.size());
						staffSalaryDetails.setSalaryBreakUpList(salaryBreakUpList);
					}
					String userId = resource.getUserId();
					/*List<Leave> staffLeaveDetails = session.selectList("selectResourceLeaveDetails", resource);		*/		
					List<Leave> staffLeaveDetails = session.selectList("selectResourceLeaveDetailsFromStaffLeaveDetails", userId);	
					if(null == staffLeaveDetails || staffLeaveDetails.size() ==0){
						staffLeaveDetails = session.selectList("selectResourceAllLeaveDetails", userId);	
					}
					
					staffSalaryDetails.setLeaveList(staffLeaveDetails);		
					
					int workingDays = 0;
					int absentDay = 0;
					
					 String totalWorkingDays =  session.selectOne("selectTotalWorkingDaysForAMonth", resource);
					 
					 if(null == totalWorkingDays){
						 workingDays = 0;
					 }else{
						 workingDays = Integer.parseInt(totalWorkingDays);
					 }
					 
					 String month = resource.getEndDate();
					 String monthValue = month.substring(1);
					 resource.setEndDate(monthValue);
					 String totalAbsentDays = session.selectOne("selectTotalAbsentDaysForAMonth",resource);
					 if(null == totalAbsentDays){
						 absentDay = 0;
					 }else{
						 absentDay = Integer.parseInt(totalAbsentDays);
					 }
					 ResourceAttendence resourceAttendance = new ResourceAttendence();
					 resourceAttendance.setAttendanceDetailsId(workingDays);
					 resourceAttendance.setExtraWorkingDays(absentDay);
					 staffSalaryDetails.setResourceAttendance(resourceAttendance);
					/*if (resource != null) {	
						actualWorkingDays = resource.getStatus();
					}	*/	
				}
				
						
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error In getStaffSalaryDetailsForSalarySlip(Resource resource) method of BackOfficeDAOImpl:NullPointerException:: "+ e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error In getStaffSalaryDetailsForSalarySlip(Resource resource) method of BackOfficeDAOImpl :Exception:: "+ e);
		} finally {
			session.close();
		}
		return staffSalaryDetails;
	}
	
	
	//Added By Naimisha 29082017
	@Override
	public Ticket getTicketDetailsForMyService(Ticket ticket) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getTicketDetailsForMyService() method of TicketDAOImpl");
			ticket = session.selectOne("getTicketDetailsForMyService", ticket);			//Mopdified By Naimisha 10012018
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getTicketDetailsForMyService() In TicketDAOImpl.java: NullPointerException" + e);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTicketDetailsForMyService() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return ticket;
	}
	
	@Override
	public List<Task> getClosedTaskList(Task task) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Task> taskList = null;
		try {
			taskList = session.selectList("selectClosedTaskList",task);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getDelegatedTask() in AdmissionDaoImpl(): ", e);
		} finally {
			session.close();
		}
		return taskList;
	}
//Added by naimisha 30082017
	@Override
	public List<QuestionMaster> fetchQuestionAnswerForSurveyOfATicket(JobType jobType) {
		List<QuestionMaster> questionList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			questionList = session.selectList("fetchQuestionAnswerForSurveyOfATicket",jobType);
			System.out.println("questionList======="+questionList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return questionList;
	}

	@Override
	public String insertTicketSurvey(Question question) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			question.setObjectId(encryptDecrypt.getBase64EncodedID("TicketDAOImpl"));
			
			session.insert("insertTicketSurvey", question);
				
			
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			e.printStackTrace();
			
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<Answer> getServeyDetailsForATicket(String ticketCode) {
		List<Answer> questionAnswerList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			questionAnswerList = session.selectList("fetchSurveyDetailsOfATicket",ticketCode);
			System.out.println("ticketCode=="+ticketCode);
			System.out.println("questionAnswerList======="+questionAnswerList.size());
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return questionAnswerList;
	}
	
	/**Added by @author Saif.Ali
	 * Date-05/009/2017*/
	@Override
	public List<Course> getProgramDetailsList(Course course) {
		List<Course> courseDetailsList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			courseDetailsList = session.selectList("getProgramDetailsList",course);
			System.out.println("courseDetailsList======="+courseDetailsList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return courseDetailsList;
	}
	
	@Override
	public List<Fees> getAllFeesRelatedInformationForStudent(Course course) {
		List<Fees> feesDetailsList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			feesDetailsList = session.selectList("getFeesRelatedDetailsList",course);
			if(feesDetailsList!= null && feesDetailsList.size()!=0)
			{
				Fees fee = new Fees();
				fee= session.selectOne("getAmountPaymentList",course);
				if(fee!= null)
				{
					for(Fees f:feesDetailsList)
					{
						f.setAmountPaid(fee.getAmountPaid());
						f.setAmountPayable(fee.getAmountPayable()); 
					}
				}
				else
				{
					for(Fees f:feesDetailsList)
					{
						f.setAmountPaid(0);
						f.setAmountPayable(0);
					}
				}
			}
				System.out.println("courseDetailsList======="+feesDetailsList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return feesDetailsList;
	}
	
	@Override
	public Student getStudentAgainstProgramAndUserId(String userId, String course) {
		SqlSession session =sqlSessionFactory.openSession();
		Student student = null;
		try {
			Course courseObj = new Course();
			courseObj.setUpdatedBy(userId);
			courseObj.setCourseCode(course);
			student = session.selectOne("selectStudentAgainstProgramAndUserId",courseObj);		
		} catch (Exception e) {
			
			logger.debug("Exception In getrollNumberAgainstProgramAndUserId() of commonDaoImpl");
		} finally {
			session.close();
		}
		return student;
	}

	/*added by ranita.sur on 08092017*/
	@Override
	public List<BookAllocation> getBookFineDetails(String userId) {
		List<BookAllocation> bookFineListDetails = null;
		SqlSession session =sqlSessionFactory.openSession();
		
		try{
			System.out.println("IN DAO IMPL ::"+userId);
			bookFineListDetails = session.selectList("fetchLibraryFineDetailsOfUser",userId);
			System.out.println("questionList======="+bookFineListDetails.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return bookFineListDetails;
	}

	@Override
	public String insertChatDetailsForIndividualChat(String from, String to, String msg) {
		SqlSession session =sqlSessionFactory.openSession();
		String status = "success";
		try {
			Notification notification = new Notification();
			notification.setNotificationSender(from);
			notification.setNotificationReplyTo(to);
			notification.setNotificationDesc(msg);
			int insertStatus = session.insert("insertChatDetailsForIndividualChat",notification);		
		} catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			logger.debug("Exception In insertChatDetailsForIndividualChat() of commonDaoImpl");
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public String updateChatStatusToReadForAUser(String user) {
		SqlSession session =sqlSessionFactory.openSession();
		String status = "success";
		try {
			int insertStatus = session.update("updateChatStatusToReadForAUser",user);		
		} catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			logger.debug("Exception In insertChatDetailsForIndividualChat() of commonDaoImpl");
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public List<Notification> getChatDetailsForIndividualChatForAUser(String to, String from) {
		
		SqlSession session =sqlSessionFactory.openSession();
		List<Notification>notificationList = null;
		try{
			System.out.println("to ::"+to);
			System.out.println("from ::"+from);
			Notification notification = new Notification();
			notification.setNotificationSender(from);
			notification.setNotificationReplyTo(to);
			notificationList = session.selectList("getChatDetailsForIndividualChatForAUser",notification);
			System.out.println("notificationList==="+notificationList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return notificationList;
	}
	
	
	//Added By Naimisha 15092017
	@Override
	public String updateEmailAttachmentDoc(Notification notification) {
		SqlSession session = sqlSessionFactory.openSession();
		String uploadStatus = "Fail";
		try{
			notification.setNotificationObjectId(encryptDecrypt.encrypt("CommonDaoImpl"));
			int intInsertStatus = session.insert("submitEmailAttachmentDoc", notification);
			if (intInsertStatus != 0) {				
				uploadStatus = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("In updateEmailAttachmentDoc() In CommonDaoImpl.java: Exception" + e);
		}
		return uploadStatus;
		
	}

	@Override
	public String updateMyEventsForMeetingInvitation(Notification notification) {
		SqlSession session = sqlSessionFactory.openSession();
		String uploadStatus = "fail";
		try{
			notification.setNotificationObjectId(encryptDecrypt.encrypt("CommonDaoImpl"));
			int intInsertStatus = session.insert("updateMyEventsForMeetingInvitation", notification);
			if (intInsertStatus != 0) {				
				uploadStatus = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("In updateMyEventsForMeetingInvitation() In CommonDaoImpl.java: Exception" + e);
		}
		return uploadStatus;
	}

	@Override
	public List<Task> getTaskListForATicket(String ticketCode) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Task>taskList = null;
		try{
			
			
			taskList = session.selectList("getListOfTaskForATicket",ticketCode);
			System.out.println("notificationList==="+taskList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return taskList;
	}

	@Override
	public List<Task> getTaskListForACategory(String ticketServiceCode) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Task>taskList = null;
		try{
			taskList = session.selectList("getTaskListForACategory",ticketServiceCode);
			System.out.println("notificationList==="+taskList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return taskList;
	}

	@Override
	public String getResourceUserIdForMinimumNoOfOpenTicket(String groupCode) {
		SqlSession session =sqlSessionFactory.openSession();
		String resourceUserId = "";
		Map<Integer,String> recepientMap = new HashMap<>();
		try{
			Approver recepientGroup = new Approver();
			recepientGroup.setApproverGroupCode(groupCode);
			List<Resource> recepientDetailsList = session.selectList("selectRecipientGroupDetails", recepientGroup);
			for(Resource resource : recepientDetailsList){
				String userId = resource.getUserId();
				List<Ticket>openTicketListForARecepient = session.selectList("selectOpenTicketList", userId);
				
					recepientMap.put(openTicketListForARecepient.size(),userId);
			}
			
			Map<Integer, String> map = new TreeMap<Integer, String>(recepientMap); 
			Map.Entry<Integer,String> entry = map.entrySet().iterator().next();
	         
	         System.out.println("entry.getValue().toString()="+entry.getValue().toString());
	         resourceUserId = entry.getValue().toString();
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return resourceUserId;
	}

	@Override
	public Ticket getTaskDetailsOfATask(Task task) {
		SqlSession session =sqlSessionFactory.openSession();
		Ticket taskDetails = null;
		try{
			taskDetails = session.selectOne("getTaskDetailsOfATask",task);
			//System.out.println("notificationList==="+taskList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return taskDetails;
	}

	@Override
	public List<TicketStatus> getAllTaskStatusList(Task task) {
		SqlSession session = sqlSessionFactory.openSession();
		List<TicketStatus> taskStatusList = new ArrayList<TicketStatus>();
		String taskTypeValue = "";
		try {
			logger.info("In getAllTaskStatusList() method of CommonDaoImpl");
			System.out.println("task code=="+task.getTaskCode());
			String taskType = session.selectOne("selectTaskTypeForATask",task);
			System.out.println("task type===="+taskType);
			if(taskType.equalsIgnoreCase("YES")){
				taskTypeValue = "APPROVAL";
				
			}else{
				taskTypeValue = "NONAPPROVAL";
			}
			System.out.println("taskTypeValue=="+taskTypeValue);
			taskStatusList = session.selectList("getAllTaskStatusListAccordingToTaskType",taskTypeValue);
			
		}  catch (Exception e) {
			logger.error("getAllTaskStatusList() In CommonDAOImpl.java: Exception" + e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return taskStatusList;
	}
	
	@Override
	public Ticket updateTaskDetails(Ticket ticket) {
		SqlSession session = sqlSessionFactory.openSession();
		int taskUpdateStatus = 0;
		Ticket ticketObj2 = new Ticket();
		try {
			logger.info("In updateTaskDetails() method of CommonDaoImpl");
			ticket.setTicketObjectId(encryptDecrypt.encrypt("CommonDaoImpl"));
			
			String taskCode = ticket.getTicketCode();
			String ticketCode = ticket.getTicketRecId();
			System.out.println("taskCode===="+taskCode);
			
			System.out.println("Ticket Code====="+ticket.getTicketRecId());
			String approvalRequired = session.selectOne("selectTaskTypeForATask",taskCode); //select task type of a task
			 if(approvalRequired.equalsIgnoreCase("YES")){
				 ticket.setApproval("APPROVAL");
			 }else{
				 ticket.setApproval("NONAPPROVAL");
			 }
			 String taskStatus = ticket.getStatus();
				
				//Added By naimisha  12042018
					
			String taskStatusType = session.selectOne("getTypeForATaskStatus", taskStatus);  //get task status type against task status 
			
			taskUpdateStatus = session.update("updateTaskDetails", ticket); //update task_details
			//int ticketstatusUpdate = session.update("updateTicketStatusAgainstTaskStatus",ticket);
			 if(taskUpdateStatus != 0){
				 if(ticket != null){
						if (ticket.getComment() != null && ticket.getComment().trim().length() != 0) {
							session.insert("insertIntoTaskComment", ticket); //insert into task comment
							session.commit();
						}
						
						if(null != ticket.getMode()){
							
							//if task is finance type then enter value in twa
							TransactionalWorkingArea transactionalWorkingArea = new TransactionalWorkingArea();
							transactionalWorkingArea.setObjectId(encryptDecrypt.encrypt("CommonDaoImpl"));
							transactionalWorkingArea.setNetAmount(ticket.getAmount());
							transactionalWorkingArea.setTransactionMode(ticket.getPaymentMode());
							transactionalWorkingArea.setDepartment(ticket.getDepartment());
							transactionalWorkingArea.setAcademicYear(ticket.getLedger());
							transactionalWorkingArea.setIncomeExpense(ticket.getMode());
							transactionalWorkingArea.setUpdatedBy(ticket.getUpdatedBy());
							transactionalWorkingArea.setTransactionalWorkingAreaName(ticket.getTicketService().getTicketServiceName());
							transactionalWorkingArea.setTransactionalWorkingAreaDesc(ticket.getTicketService().getTicketServiceName());
							transactionalWorkingArea.setTaskCode(ticket.getTicketCode());
							transactionalWorkingArea.setTicketCode(ticket.getTicketRecId());
							
							//Naimisha you have to change this
							//you have to add ticket code and task code into twa
							
							
							int transactionInsertStatus = session.insert("insertInTransactionWorkingAreaForTicketing", transactionalWorkingArea);
							
							Ticket ticket1 = new Ticket();
							ticket1.setTicketObjectId(transactionalWorkingArea.getObjectId());
							ticket1.setUpdatedBy(ticket.getUpdatedBy());
							ticket1.setTicketCode(transactionalWorkingArea.getTicketCode());
							ticket1.setTicketDesc(transactionalWorkingArea.getTaskCode());
							ticket1.setTableName("transactional_working_area");
							String lastInsertedRecId = session.selectOne("selectLastInsertedRecId",ticket1); //get last inserted rce_id from table
							ticket1.setTicketRecId(lastInsertedRecId);
							int insertStatus = session.insert("insertIntoTicketTaskTablenameMapping",ticket1);//insert into ticket_task_tablename_mapping
						}
						if((ticket.getTicketService().getTicketServiceCode()).equalsIgnoreCase("StudentLeave")){
							 if((taskStatusType.equalsIgnoreCase("CLOSED"))||(taskStatusType.equalsIgnoreCase("COMPLETED"))){
								 List<StudentAttendance>studentAttendanceList = ticket.getStudentAttendanceList();
								 sendAttandaceTOPortalAndSaveInStudentAttendance(studentAttendanceList,ticket);
								}
							 	
								
									
						 }
					}
			 }
	 
			
			
			if(taskStatusType.equalsIgnoreCase("COMPLETED")||taskStatusType.equalsIgnoreCase("CLOSED")){
				
				String presentLevel = session.selectOne("getpresentLeveOfTheTask",taskCode); //get present level of task
				ticket.setModuleName(presentLevel);
				List<Ticket>presentLevelTaskList = session.selectList("getListOfTaskWithPresentLevel", ticket); //check if any tasks with present level exists or not
				System.out.println("size======"+presentLevelTaskList.size());
				if(presentLevelTaskList.size() == 0){ //if exists
					List<String> levelList = session.selectList("getAllLevelListOfTasksForATicket",ticketCode); //get remaining levels list
					if(null != levelList && levelList.size()!= 0){
						Collections.sort(levelList);					//sort level list
						Ticket ticketObj = new Ticket();
						ticketObj.setTicketRecId(levelList.get(0));
						ticketObj.setTicketCode(ticketCode);
						int ticketUpdateStatus = session.update("updateTaskDetailsForMinimumLevel", ticketObj); //update task with minimum level set is_active = true
						//ticket.setStatus("OPEN");
						String type = "OPEN";
						ticket.setType(type);
						TicketStatus taskStatusObj = session.selectOne("selectTaskStatusAgainstTicketCodeAndTaskLevel", ticketObj);//fetching task status object against ticket code and task level
						
						String taskStatusString = taskStatusObj.getTicketStatusCode();
						ticket.setStatus(taskStatusString);
						session.update("updateTicketStatusAgainstTaskStatus",ticket);//update ticket status mapped with task status
								
						Ticket ticketObjectNew = session.selectOne("selectTaskCodeForATicketOfParticularLevel", ticketObj); //get task object against ticket code and level
						//Modified By Naimisha 22032018
						 String taskCodeNew = ticketObjectNew.getTicketCode();
						 if(ticketUpdateStatus != 0){
								
								ticketObj2.setTicketObjectId(ticket.getTicketObjectId());
								ticketObj2.setModuleName(ticketObjectNew.getAffectedUser());
								ticketObj2.setUpdatedBy(ticket.getUpdatedBy());
								ServiceType ticketService = new ServiceType();
								ticketService.setTicketServiceName(taskCode);
								ticketObj2.setTicketService(ticketService);
								//System.out.println("ticket Rec_id====="+ticket.getModuleName());
								
								Task task = new Task();
							 	task.setTaskCode(taskCode);
							 	task.setTaskDesc(type);
							 	
							 	//EmailDetails emailDetailsObj = session.selectOne("selectMailTemplateDetailsForATask",task);
								List<EmailDetails> templateDetailsList = session.selectList("selectMailTemplateDetailsForATask",task);//get template list
								String comment1 = "";
								
								if(null != templateDetailsList && templateDetailsList.size() !=0){
								 		for(EmailDetails emailDetailsObj : templateDetailsList){
								 			String templateType = emailDetailsObj.getStatus();
								 			if(templateType.equalsIgnoreCase("Notification")){
								 				Ticket ticketObj3 = new Ticket();
								 				ticketObj3.setTicketObjectId(ticket.getTicketObjectId());
								 				ticketObj3.setModuleName(ticketObjectNew.getAffectedUser());
								 				ticketObj3.setUpdatedBy(ticket.getUpdatedBy());
												ServiceType ticketService1 = new ServiceType();
												ticketService1.setTicketServiceName(taskCode);
												ticketObj3.setTicketService(ticketService1);
												
												ticketObj3.setComment(emailDetailsObj.getEmailDetailsDesc());
												int intInsertStatus1 = session.insert("insertIntoNotificationForTask", ticketObj3);
								 			}else if(templateType.equalsIgnoreCase("Email")){
								 				EmailDetails emailDetails = new EmailDetails();
								 				String emailSubject = emailDetailsObj.getEmailDetailsSubject();
								 				emailSender.sendMail(ticket.getUpdatedBy(), ticketObjectNew.getAffectedUser(), emailDetails, emailSubject, emailDetailsObj.getEmailDetailsDesc());
												
								 			}
								 		}
								 	}
								
							
						 }
					}else{
						//if no task with present level exists
						ticket.setType(taskStatusType);
						Ticket ticketObj = new Ticket();
						ticketObj.setTicketRecId(presentLevel);
						ticketObj.setTicketCode(ticketCode);
						
						System.out.println("presentLevel="+presentLevel);
						System.out.println("ticketCode=="+ticketCode);
						
						//TicketStatus taskStatusObject = session.selectOne("selectTaskStatusAgainstTicketCodeAndTaskLevel", ticket);
						
						//String taskStatusString = taskStatusObject.getTicketStatusCode();
						ticket.setStatus(taskStatus);
						session.update("updateTicketStatusAgainstTaskStatus",ticket);
					}
				}
				
			}else if(taskStatusType.equalsIgnoreCase("USER_INPUT_REQUIRED")){
				String userId = ticket.getUserName();
				ticketObj2.setModuleName(userId);
				String comment1 = ticket.getUpdatedBy()+" requred more information";
				
				
				ticketObj2.setComment(comment1);
				int intInsertStatus = session.insert("insertIntoNotificationForTask", ticketObj2);
				
			}else if(taskStatusType.equalsIgnoreCase("REJECTED")){
				Ticket ticketObjectNew = new Ticket();
				ticketObjectNew.setType("REJECTED");
				ticketObjectNew.setApproval(ticket.getApproval());
				String taskStatusString = session.selectOne("selectTaskStatusAgainstType", ticketObjectNew);
				
				ticketObjectNew.setTicketCode(taskCode);
				ticketObjectNew.setTicketRecId(ticketCode);
				ticketObjectNew.setStatus(taskStatusString);
				
				session.update("updateTicketStatusAgainstTaskStatus",ticketObjectNew);//reject the ticket
				
				System.out.println("ticket code ="+ticketObjectNew.getTicketRecId());
				System.out.println("task code ="+ticketObjectNew.getTicketCode());
				
				ticketObjectNew.setUpdatedBy(ticket.getUpdatedBy());
				int updateStatus = session.update("updateTablesRelatedToATicketAndtask", ticketObjectNew);
				/*List<String>tableNameList = session.selectList("selectTableNameAgainstTicketNo",ticketObjectNew);
				for(String tableName : tableNameList){
					ticket.setTableName(tableName);
					updateStatus = session.update("updateTablesRelatedToATicketAndtask", ticketObjectNew) ;
				}*/
				
				//***********//

				
				Task task = new Task();
			 	task.setTaskCode(taskCode);
			 	task.setTaskDesc("REJECTED");
			 	
			 	//EmailDetails emailDetailsObj = session.selectOne("selectMailTemplateDetailsForATask",task);
				List<EmailDetails> templateDetailsList = session.selectList("selectMailTemplateDetailsForATask",task); // get template list against task
				String comment1 = "";
				
				if(null != templateDetailsList && templateDetailsList.size() !=0){
				 		for(EmailDetails emailDetailsObj : templateDetailsList){
				 			String templateType = emailDetailsObj.getStatus();
				 			if(templateType.equalsIgnoreCase("Notification")){
				 				Ticket ticketObj3 = new Ticket();
				 				ticketObj3.setTicketObjectId(ticket.getTicketObjectId());
				 				ticketObj3.setModuleName(ticketObjectNew.getAffectedUser());
				 				ticketObj3.setUpdatedBy(ticket.getUpdatedBy());
								ServiceType ticketService1 = new ServiceType();
								ticketService1.setTicketServiceName(taskCode);
								ticketObj3.setTicketService(ticketService1);
								
								ticketObj3.setComment(emailDetailsObj.getEmailDetailsDesc());
								int intInsertStatus1 = session.insert("insertIntoNotificationForTask", ticketObj3);
				 			}else if(templateType.equalsIgnoreCase("Email")){
				 				EmailDetails emailDetails = new EmailDetails();
				 				String emailSubject = emailDetailsObj.getEmailDetailsSubject();
				 				emailSender.sendMail(ticket.getUpdatedBy(), ticketObjectNew.getAffectedUser(), emailDetails, emailSubject, emailDetailsObj.getEmailDetailsDesc());
								
				 			}
				 		}
				 	}
				
			
		 
				
				//******************//
			}
			
			session.commit();
					
			ticket.setMessage("success");
			
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("updateTaskDetails() In CommonDaoImpl.java: Exception", e);
			ticket.setStatus("fail");
			ticket.setMessage("fail");
			
		} finally {
			session.close();
		}
		return ticket;
	}
	
	@Override
	public List<TimeTableGridData> getMyTimeTableGridData(String semester,String userId){
		SqlSession session = sqlSessionFactory.openSession();
		List<TimeTableGridData> gridDataList = null;
		
		try {
			logger.info("getMyTimeTableGridData() method in BackOfficeDaoImpl");
			Term term = new Term();
			term.setTermName(semester);
			term.setUpdatedBy(userId);
			gridDataList = session.selectList("getMyGridDataList",term);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getMyTimeTableGridData() method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		
		return gridDataList;
	}

	@Override
	public List<TimeTableGridData> getTimeTableGridDataForStudent(String semester, String program) {
		SqlSession session = sqlSessionFactory.openSession();
		List<TimeTableGridData> gridDataList = null;
		
		try {
			logger.info("getTimeTableGridDataForStudent() method in BackOfficeDaoImpl");
			Term term = new Term();
			term.setTermName(semester);
			term.setTermDesc(program);
			gridDataList = session.selectList("getTimeTableGridDataForStudent",term);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTimeTableGridDataForStudent() method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		
		return gridDataList;
	}
	
	@Override
	public Ticket updateMyTicket(Ticket ticket) {
		SqlSession session = sqlSessionFactory.openSession();
		int ticketUpdateStatus = 0;
		try {
			logger.info("In updateTicket() method of TicketDaoImpl");
			ticket.setTicketObjectId(encryptDecrypt.encrypt("CommonDaoImpl"));
			
			
			
			// ticketUpdateStatus = session.update("updateTicket", ticket);
			// if(ticketUpdateStatus != 0){
				 if(ticket != null){
						if (ticket.getComment() != null && ticket.getComment().trim().length() != 0) {
							session.insert("updateTicketComment", ticket);
							session.commit();
						}
					}
			// }
			
			
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("updateMyTicket() In TicketDaoImpl.java: Exception", e);
			
		} finally {
			session.close();
		}
		return ticket;
	}

	@Override
	public Ticket getStudentLeaveDetailsAgainstTicket(String ticketCodeValue) {
		SqlSession session =sqlSessionFactory.openSession();
		Ticket studentLeaveDetails= null;
		try{
			studentLeaveDetails = session.selectOne("getStudentLeaveDetailsForATicket",ticketCodeValue);
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return studentLeaveDetails;
	}

	//Added By Naimisha 29032018
	@Override
	public List<String> getLevelListForTicket() {
		SqlSession session =sqlSessionFactory.openSession();
		List<String>levelList = null;
		try{
			levelList = session.selectList("getAllLevelList");
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return levelList;
	}

	@Override
	public CommodityPurchaseOrder getCommodityPurchaseOrderDetaialsForATicket(String ticketCodeValue) {
		SqlSession session =sqlSessionFactory.openSession();
		CommodityPurchaseOrder commodityPurchaseOrder= null;
		try{
				commodityPurchaseOrder = session.selectOne("getCommodityPurchaseOrderDetailsForATicket",ticketCodeValue);
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return commodityPurchaseOrder;
	}

	@Override
	public Ticket getUserListAssociatedWithATicket(String ticketCode) {
		SqlSession session =sqlSessionFactory.openSession();
		Ticket usersForTicket= null;
		try{
			usersForTicket = session.selectOne("getUserDetailsAssociatedWithATicket",ticketCode);
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return usersForTicket;
	}

	/**
	 * @author anup.roy
	 * this method is for getting all status of items*/
	@Override
	public List<StatusOfItem> getAllStatusOfItems() {
		SqlSession session = sqlSessionFactory.openSession();
		List<StatusOfItem> statusList = null;
		try {
			logger.info("In List<StatusOfItem> getAllStatusOfItems() of CommonDaoImpl.java");
			statusList = session.selectList("getAllStatusOfItems");
		}catch (Exception e) {
			logger.error("Exception in List<StatusOfItem> getAllStatusOfItems() of CommonDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return statusList;
	}
	
	/**
	 * @author anup.roy
	 * this method is for submitting all status of items*/
	
	@Override
	public String submitStatusOfItem(StatusOfItem status) {
		SqlSession session = sqlSessionFactory.openSession();
		String insertStatus = "fail";
		try {
			logger.info("In String submitStatusOfItem(StatusOfItem status) of CommonDaoImpl.java");
			status.setObjectId(encryptDecrypt.encrypt("CommonDaoImpl"));
			int insertSt = session.insert("submitStatusOfItem",status);
			if(insertSt != 0) {
				insertStatus = "success";
			}
		}catch (Exception e) {
			logger.error("Exception in String submitStatusOfItem(StatusOfItem status) of CommonDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String insertTicketStatus(Ticket ticket) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In insertTicketStatus() method of AdministratorDAOImpl");		
			ticket.setTicketObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			int insertStatus = session.insert("insertTicketStatus", ticket);
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("insertTicketStatus() In AdministratorDAOImpl.java: Exception" + e);
		}finally{
			session.close();
		}
		return status;
	}

	/* added by sourav.bhadra on 09-04-2018 */
	@Override
	public Department getDepartmentForAUser(String userId) {
		SqlSession session = sqlSessionFactory.openSession();
		Department department = null;
		try {
			logger.info("In getDepartmentForAUser() of CommonDaoImpl.java");
			department = session.selectOne("getDepartmentForAUser",userId);
		}catch (Exception e) {
			logger.error("Exception in getDepartmentForAUser() of CommonDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return department;
	}
	
	/* added by sourav.bhadra on 10-04-2018 */
	@Override
	public String getBudgetOfSubDeptsForAFinancialYear(String financialYear, String department) {
		SqlSession session = sqlSessionFactory.openSession();
		String totalData = "";
		String parentDeptBudget = "";
		String subDeptBudget = "";
		
		try{
			/* fetch parent department's budget */
			Budget parentBudget = new Budget();
			parentBudget.setAcademicYear(financialYear);
			parentBudget.setDepartment(department);
			parentBudget = session.selectOne("selectParentDepartmentBudgetDetails", parentBudget);
			
			if(null != parentBudget.getActualIncome() && null != parentBudget.getReserveFund()){
				parentDeptBudget = String.format("%.2f", parentBudget.getActualIncome()) +"*"+ String.format("%.2f", parentBudget.getExpectedIncome()) +"*"+ String.format("%.2f", parentBudget.getReserveFund());
			}else{
				parentDeptBudget = "null";
			}
			/* fetch sub department's budget */
			String subDeptBudgetStatus="unallocated";
			List<Budget> budgetList = new ArrayList<Budget>();
			List<Department> subDeptList = new ArrayList<>();
			subDeptList = session.selectList("selectSubDeptsForBudgetAllocation", department);
			for(Department d : subDeptList){
				Budget b = new Budget();
				b.setAcademicYear(financialYear);
				b.setDepartment(d.getDepartmentName());
				b = session.selectOne("getBudgetForAcademicYear", b);
				if(null != b){
					subDeptBudgetStatus="allocated";
				}
				if(b == null){
					b = new Budget();
					b.setAcademicYear(financialYear);
					b.setDepartment(d.getDepartmentName());
					b.setTotalExpence(0.0);
					b.setActualIncome(0.0);
					b.setExpectedExpense(0.0);
					b.setExpectedIncome(0.0);
					b.setReserveFund(0.0);
				}
				b.setAcademicYear(financialYear);
				b.setDepartment(d.getDepartmentName());
				budgetList.add(b);
			}
			for(Budget b:budgetList){
				subDeptBudget=subDeptBudget+b.getDepartment()+"*~*"+String.format("%.2f", b.getExpectedIncome())+"*~*"+
						String.format("%.0f", b.getActualIncome())+"*~*"+String.format("%.0f", b.getTotalExpence())+"*~*"+
						String.format("%.0f", b.getExpectedExpense())+"*~*"+String.format("%.0f", b.getReserveFund())+"~*~";
			}
			subDeptBudget=subDeptBudgetStatus+"@@"+subDeptBudget;
		}catch (Exception e) {
			e.printStackTrace();
		}
		totalData = parentDeptBudget + "#" + subDeptBudget;
		return totalData;
	}
	
	/* added by sourav.bhadra on 11-04-2018 */
	@Override
	public String updateParentDepartmentBudgetDetails(Budget budgetDetails) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "";
		try{
			session.update("updateParentDepartmentBudgetDetails", budgetDetails);
			status = "success";
		}catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return status;
	}
	
	/* added by sourav.bhadra on 11-04-2018 */
	@Override
	public Budget getBudgetDetailsForADepartment(String financialYear, String department) {
		SqlSession session = sqlSessionFactory.openSession();
		Budget budgetDetails = new Budget();
		try{
			budgetDetails.setAcademicYear(financialYear);
			budgetDetails.setDepartment(department);
			budgetDetails = session.selectOne("selectParentDepartmentBudgetDetails", budgetDetails);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return budgetDetails;
	}
	
	/* added by sourav.bhadra on 11-04-2018 */
	@Override
	public String reserveFundEstimation(Budget budget) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "";
		try{
			session.update("updateEstimatedReserveFundForADepartment", budget);
			status = "success";
		}catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return status;
	}

	@Override
	public TicketStatus getTicketStatusForAType(String type) {
		SqlSession session = sqlSessionFactory.openSession();
		TicketStatus status = null;
		try {
			logger.info("In getTicketStatusForAType(String type) of CommonDaoImpl.java");
			status = session.selectOne("getTicketStatusForAType",type);
		}catch (Exception e) {
			logger.error("Exception in getTicketStatusForAType(String type) of CommonDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return status;
	}

	/* added by sourav.bhadra on 17-04-2018 */
	@Override
	public List<Department> getAllSubDepartmentsOfADepartment(String parentDepartment) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Department> subDeptList = new ArrayList<>();
		try{
			subDeptList = session.selectList("selectSubDeptsForBudgetAllocation", parentDepartment);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return subDeptList;
	}
	
	/* added by sourav.bhadra on 17-04-2018 */
	@Override
	public String updateSubDepartmentsBudget(Budget budget) {
		System.out.println("LN3143 :: FinDAOImpl\n*******************************************");
		System.out.println("Financial Year :: "+budget.getAcademicYear());
		System.out.println("Department :: "+budget.getBudgetDesc());
		System.out.println("Sub Department :: "+budget.getDepartment());
		System.out.println("Percentage :: "+budget.getExpectedIncome());
		System.out.println("Amount :: "+budget.getActualIncome());
		
		SqlSession session =sqlSessionFactory.openSession();
		String status = "fail";
		try{
			String check = session.selectOne("checkDepartmentBudgetAvailability", budget);
			if(null != check && check != ""){
				budget.setExpectedExpense(budget.getActualIncome());
				budget.setTotalExpence(0.0);
				budget.setReserveFund(0.0);
				session.update("updateBudget", budget);
				status="Update Successful";
			}else{
				budget.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
				budget.setExpectedExpense(budget.getActualIncome());
				budget.setReserveFund(0.0);
				budget.setTotalExpence(0.0);
				session.insert("saveBudget", budget);
				status = "success";
				if(status == "success"){
					String department = budget.getDepartment();
					session.update("updateBudgetStatusInDepartmentTable", department);
				}
			}
			if(status != "fail"){
				session.update("updateParentDeptRemainingBalance", budget);
			}
		}catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return status;
	}

	@Override
	public List<TicketStatus> getTicketStatusListForAType(String type) {
		SqlSession session = sqlSessionFactory.openSession();
		List<TicketStatus> statusList = null;
		try {
			logger.info("In getTicketStatusListForAType(String type) of CommonDaoImpl.java");
			statusList = session.selectList("getTicketStatusListForAType",type);
		}catch (Exception e) {
			logger.error("Exception in getTicketStatusListForAType(String type) of CommonDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return statusList;
	}

	@Override
	public List<Task> getTaskNoListForUserAndFunctinalityWise(String userId, String functionality) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Task> taskList = null;
		try {
			logger.info("In getTaskNoListForUserAndFunctinalityWise(userId,functionality) of CommonDaoImpl.java");
			
			Task task = new Task();
			task.setUserId(userId);
			task.setTaskName(functionality);
			
			taskList = session.selectList("getTaskNoListForUserAndFunctinalityWise",task);
		}catch (Exception e) {
			logger.error("Exception in getTaskNoListForUserAndFunctinalityWise(userId,functionality) of CommonDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return taskList;
	}

	@Override
	public String insertIntoSchoolNote(SchoolNote schoolNote) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "success";
		try{
			logger.info("In insertIntoSchoolNote(schoolNote) of CommonDaoImpl.java");
			schoolNote.setObjectId(encryptDecrypt.encrypt("CommonDaoImpl"));
			int insertStatus = session.insert("insertIntoSchoolNote", schoolNote);
			
		}catch (Exception e) {
			logger.info("Exception In insertIntoSchoolNote(schoolNote) of CommonDaoImpl.java");
			e.printStackTrace();
			status = "fail";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return status;
	}

	@Override
	public List<SchoolNote> getSchoolNoteList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<SchoolNote> schoolNoteList = null;
		try {
			logger.info("In getSchoolNoteList() of CommonDaoImpl.java");
			schoolNoteList = session.selectList("getSchoolNoteList");
		}catch (Exception e) {
			logger.error("Exception in getSchoolNoteList() of CommonDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return schoolNoteList;
	}
	
	public void sendAttandaceTOPortalAndSaveInStudentAttendance( List<StudentAttendance>studentAttendanceList,Ticket ticket){
		int attendanceInsertStatus = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			
			//PRAD JUNE 8 2018
			// Write to DB First and then call the WebIQ API
			for(StudentAttendance studentAttendance :studentAttendanceList){
				 studentAttendance.setAttendanceObjectId(ticket.getTicketObjectId());
				 studentAttendance.setAttendanceDesc(ticket.getDescription());
				 studentAttendance.setUpdatedBy(ticket.getUpdatedBy());
				 
				 attendanceInsertStatus =  session.insert("insertStudentAttendance", studentAttendance);
			}
			//Write to DB Ends here
			
			if(ticket.getWebIQAvailable().equalsIgnoreCase("true")){

				/*Call to DB to get the Approver Name*/
				String approver = session.selectOne("getEventEmployeeName",studentAttendanceList.get(0).getUpdatedBy());
				
				JSONObject jsonObj = new JSONObject();
			 	jsonObj.put("username",ticket.getUserName());
			 	jsonObj.put("password",ticket.getPassword());
				jsonObj.put("rollNumber",studentAttendanceList.get(0).getStudentRollNo());
				jsonObj.put("reason",ticket.getDescription());
				jsonObj.put("approver",approver);
				
				JSONArray studentsArray = new JSONArray();
				//int maxAttendanceId = session.selectOne("selectMaxAttendanceIdFromAttendanceDetails");
				for(StudentAttendance studentAttendance :studentAttendanceList){
					 studentsArray.put(studentAttendance.getAbsentDay());
				}
				jsonObj.put("days", studentsArray);
				
				System.out.println(jsonObj.toString());

				final String uri = ticket.getUrl();
				System.out.println("URI:::"+uri);
				URL url = new URL(uri);
				HttpURLConnection connection = null;
				OutputStreamWriter out = null;
				String json_response = "";
				InputStreamReader in = null;
				BufferedReader br = null;
				WebIQTransaction webIQTran = null;
				
				try{
					connection = (HttpURLConnection)url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestProperty("Content-Type", "application/json");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					connection.setRequestMethod("POST");
					out = new OutputStreamWriter(connection.getOutputStream());
					out.write(jsonObj.toString());
					out.close();
						
					
					in = new InputStreamReader(connection.getInputStream());
					br = new BufferedReader(in);
					String text = "";
					while((text = br.readLine())!= null){
						json_response += text;
					}
				}catch(Exception ee){
					ee.printStackTrace();
					//Could be Connection Failure then also insert into the webiq_transaction_details table
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(ticket.getUpdatedBy());
					webIQTran.setUri(uri);
					webIQTran.setRequestJSON(jsonObj.toString());
					webIQTran.setResponseJSON(json_response);
					webIQTran.setStatus(false);
					//Call to the BackOffice Service
					backOfficeService.addWebIQTransaction(webIQTran);
				}
				
				System.out.println("JSON response:::"+ json_response);
				if((!json_response.isEmpty())){
					JSONObject object = new JSONObject(json_response);
					int statusFromJsonResponse = (int)object.get("status");
					if(statusFromJsonResponse==200){
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(ticket.getUpdatedBy());
						webIQTran.setUri(uri);
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(true);
						
						//Call to the BackOffice Service
						backOfficeService.addWebIQTransaction(webIQTran);
					}else{
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(ticket.getUpdatedBy());
						webIQTran.setUri(uri);
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(false);
						//Call to the BackOffice Service
						backOfficeService.addWebIQTransaction(webIQTran);

					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String> getMobileNumberAgainstRollNumbers(String[] rollNumbers){
		SqlSession session = sqlSessionFactory.openSession();
		List<String> mobileNumbers = new ArrayList<String>();
		try {
			logger.info("In getMobileNumberAgainstRollNumbers() of CommonDaoImpl.java");
			//List <String> rollNumberList = Arrays.asList(rollNumbers);
			for(int i=0;i<rollNumbers.length;i++){
				String mobileNumber = session.selectOne("getMobileNumberAgainstRollNumbers",rollNumbers[i]);
				if(mobileNumber!=null && !(mobileNumber.isEmpty()) && !(mobileNumber.equalsIgnoreCase("null")))
					mobileNumbers.add(mobileNumber);
			}
			System.out.println("List Size :"+mobileNumbers.size());
			
		}catch (Exception e) {
			logger.error("Exception in getMobileNumberAgainstRollNumbers() of CommonDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return mobileNumbers;
	}
	
	// Added by Saikat 16/6/2018
	@Override
	public String saveSMSDetailsForAudit(SmsAudit smsAudit){
		SqlSession session = sqlSessionFactory.openSession();
		String insertStatus = "Success";
		try{
			smsAudit.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			int status = session.insert("saveSMSDetailsForAudit",smsAudit);
			if(status == 0){
				insertStatus = "Failure";
			}
		}catch (Exception e) {
			logger.error("Exception in saveSMSDetailsForAudit() of CommonDaoImpl.java:"+e);
			insertStatus = "Failure";
		}
		return insertStatus;
		
	}
}
