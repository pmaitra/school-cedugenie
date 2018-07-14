package com.qts.icam.service.impl.common;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.qts.icam.dao.administrator.AdministratorDAO;
import com.qts.icam.dao.backoffice.BackOfficeDAO;
import com.qts.icam.dao.bulkdata.DataDAO;
import com.qts.icam.dao.common.CommonDao;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.AnnualStock;
import com.qts.icam.model.common.AnnualStockList;
import com.qts.icam.model.common.Asset;
import com.qts.icam.model.common.AssetSubType;
import com.qts.icam.model.common.AssetType;
import com.qts.icam.model.common.Attachment;
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
import com.qts.icam.model.common.RepositoryStructure;
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
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.TeachingLevel;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.common.IncomeAge;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.inventory.CommodityPurchaseOrder;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.report.Report;
import com.qts.icam.model.survey.Answer;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.survey.QuestionMaster;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.utility.bulkdata.DataUtility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.ldap.Ldap;
import com.qts.icam.utility.mailservice.EmailSender;
import com.qts.icam.utility.pdfbuilder.PdfBuilder;
import com.qts.icam.utility.report.xmlbuilder.XMLBuilder;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;
import com.qts.icam.model.admission.AdmissionDrive;
import com.qts.icam.model.backoffice.Fees;
import com.qts.icam.model.backoffice.TimeTableGridData;

@Service
public class CommonServiceImpl implements CommonService {
	public static Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	DataUtility dataUtility;
		
	@Autowired
	DataDAO dataDAO;		
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
	CommonDao commonDao;
	
	
	@Autowired
	AdministratorDAO administratorDAO;
	
	
	@Autowired
	BackOfficeDAO backOfficeDAO;
	
	
	@Autowired
	XMLBuilder xMLBuilder;
	
	@Autowired
	PdfBuilder pdfBuilder;
	
	List<Vendor> vendorList = null;

	List<UpdateLog> updateLogList = null;

	@Autowired
	Ldap ldap;
	
	@Autowired
	FileUploadDownload fileUploadDownload; 
	
	List<NoticeBoard> noticeListFromDB = null; 
	int pageSize = 5;	
	// FRESH INTEGRATION BEGINS HERE

	List<Ticket> ticketList = null;
	List<Venue> venueList = null;
	List<EmailDetails> emailDetailsList = null; 
	Notification emailNotification = null;
	
	@Override
	public List<NoticeBoard> getNoticeList() {
		 noticeListFromDB = commonDao.getNoticeList();
		return noticeListFromDB;
	}
	
	@Override
	public AcademicYear getCurrentAcademicYear() {
		return commonDao.getCurrentAcademicYear();
	}

	@Override
	public List<Module> getAllActiveModules() {		
		return  commonDao.getAllActiveModules();
	}

	@Override
	public Notification getEmailDetails(String userId) {		
		return commonDao.getEmailDetails(userId);
	}


	@Override
	public List<Notification> getNotificationDescriptionList(String userId) {		
		return commonDao.getNotificationDescriptionList(userId);
	}

	@Override
	public String getTimeFromDB() {
		return commonDao.getTimeFromDB();

	}
//---------------------------new added by sovan.mukherjee...................................

	@Override
	public String getStateNameList(String country) {
 		String states = "";
 		try{
 			List<State> stateList = commonDao.getStateNameList(country);
 			logger.info("stateList:::::: "+stateList);
 			if(stateList!=null && stateList.size()!=0){
				StringBuilder sb = new StringBuilder();
				for(State state : stateList){
					if (sb.length() != 0) {
						sb.append(";");
				    }
					sb.append(state.getStateCode());
					sb.append("*");
					sb.append(state.getStateName());
				}
				states = (new Gson().toJson(sb.toString()));
			}else{
				logger.info("stream not found by ajax @ getStateNameList()In CommonServiceImpl");
			}
 		}catch(Exception e){
 			logger.error("exception in getStateNameList() in CommonServiceImpl ", e);
 		}
 		logger.info(states);
		return states;
	}

	@Override
	public List<Resource> getActiveLoggedInUser(String userid) {
		return commonDao.getActiveLoggedInUser(userid);
	}
	
	@Override
	public List<Standard> getStandards() {
		List<Standard> classList = commonDao.getStandards();
		
		return classList;
	}
	
	

	@Override
	public String getUserNameForId(String userId) throws CustomException {
			return commonDao.getUserNameForId(userId);
	}
	
	@Override
	public List<Country> getCountryList() {
		return commonDao.getCountryList();
	}

	@Override
	public List<TicketStatus> getAllTicketStatus() throws CustomException {
		return commonDao.getAllTicketStatus();
	}
	
	@Override
	public List<State> getAllStatesInIndia(String indiaCode) throws CustomException {
		return commonDao.getStateNameList(indiaCode);
	}

	@Override
	public List<ResourceType> getAllResourceType() throws CustomException {
		return commonDao.getAllResourceType();
	}
	
	@Override
	public String getSectionAgainstStandard(String standard) throws CustomException {
		String section = "";
 		try{
 			List<Section> sectionList = commonDao.getSectionAgainstStandard(standard);
 			StringBuilder sb = new StringBuilder();
 			if(sectionList!=null && sectionList.size()!=0){				
				for(Section sectionObject : sectionList){
					if (sb.length() != 0) {
						sb.append(";");
				    }
					sb.append(sectionObject.getSectionCode());
					sb.append("*");
					sb.append(sectionObject.getSectionName());
				}
				
			}else{
				logger.info("Section not found by ajax @ getSectionAgainstStandard()In CommonServiceImpl");
			}
 			section = (new Gson().toJson(sb.toString()));
 		}catch(Exception e){
 			logger.error("Exception in getSectionAgainstStandard() in CommonServiceImpl ", e);
 		}
 		
		return section;
	}

	@Override
	public List<String> getUserIdForResourceType(ResourceType resourceType)
			throws CustomException {		
		return commonDao.getUserIdForResourceType(resourceType);
	}
	

@Override
	public List<VendorType> getVendorTypes() {
		return commonDao.getVendorTypes();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int addVendor(Vendor vendor,Ledger ledger) {
		return commonDao.addVendor(vendor,ledger);
	}

	@Override
	public List<Vendor> getVendorList() {
		vendorList = commonDao.getVendorList();
		return vendorList;
	}
	
	@Override
	public PagedListHolder<Vendor> getVendorListPaging(HttpServletRequest request) {
		PagedListHolder<Vendor> vendorPagedListHolder = null;
		try{
			if(vendorList!=null && vendorList.size()!=0){
				vendorPagedListHolder = new PagedListHolder<Vendor>(vendorList);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				vendorPagedListHolder.setPage(page);
				vendorPagedListHolder.setPageSize(pageSize);
			}
		}catch(Exception e){
			logger.error("exception in getVendorListPaging() method CommonServiceImpl ", e);
		}
		return vendorPagedListHolder;
	}

	@Override
	public Vendor getVendorDetails(String vendorCode) {
		return commonDao.getVendorDetails(vendorCode);
	}

	@Override
	public int updateVendorDetails(Vendor vendor) {
		return commonDao.updateVendorDetails(vendor);
	}

	@Override
	public int deleteVendorDetails(Vendor vendor) {
		return commonDao.deleteVendorDetails(vendor);
	}
	
	@Override
	public List<Role> getAllRoleList() throws CustomException {
		return commonDao.getAllRoleList();
	}
	
	@Override
	public AcademicYear getInventorySession() {
		return commonDao.getInventorySession();
	}
	
	@Override
	public List<Resource> getStaffUserIdList() {
		logger.info("getStaffUserIdList() method In CommonServiceImpl.java:");
		return commonDao.getStaffUserIdList();
	}
	
	@Override
	public List<Department> selectAllDepartment() {
		return commonDao.selectAllDepartment();
	}
	
	@Override
	public List<Resource> getDepartmentWiseUserList(String department) {
		return commonDao.getDepartmentWiseUserList(department);
	}

	@Override
	public SchoolDetails selectSchoolTimeTable()throws CustomException {
		return commonDao.selectSchoolTimeTable();
	}
	
	@Override
	public String getStudentsAgainstStandardAndSection(String standard, String section) throws CustomException {
		String studentString = "";
 		try{
 			Student student=new Student();
 			student.setStandard(standard);
 			student.setSection(section);
 			List<Student> studentList = commonDao.getStudentsAgainstStandardAndSection(student);
 			StringBuilder sb = new StringBuilder();
 			if(studentList!=null && studentList.size()!=0){				
				for(Student studentObject : studentList){
					if (sb.length() != 0) {
						sb.append(";");
				    }
					sb.append(studentObject.getRollNumber());
					sb.append("*");
					sb.append(studentObject.getStudentName());
				}				
			}else{
				logger.info("Section not found by ajax @ getStudentsAgainstStandardAndSection()In CommonServiceImpl");
			}
 			studentString = (new Gson().toJson(sb.toString()));
 		}catch(Exception e){
 			
 			logger.error("Exception in getStudentsAgainstStandardAndSection() in CommonServiceImpl ", e);
 		}
		return studentString;
	}


	@Override
	public String getSubjectsStudiedByStudent(String rollNumber) throws CustomException {
		String subjects = "";
 		try{
 			List<Subject> subjectList = commonDao.getSubjectsStudiedByStudent(rollNumber);
 			if(subjectList!=null && subjectList.size()!=0){
				for(Subject subject:subjectList){
					subjects=subjects+subject.getSubjectCode()+"*~*";
				}
			}else{
				logger.info("Section not found by ajax @ getSubjectsStudiedByStudent()In CommonServiceImpl");
			}
 		}catch(Exception e){
 			
 			logger.error("Exception in getSubjectsStudiedByStudent() in CommonServiceImpl ", e);
 		}
		return subjects;
	}
	
	@Override
	public List<Resource> getAllTeacherList() throws CustomException {
		return commonDao.getAllTeacherList();
	}
	
	
	
	@Override
	public void createLoggingByAspect(LoggingAspect loggingAspect) {
		try{
			LoggingAspect loggingActivityRules = commonDao.getLogActivityRules(loggingAspect);
			logger.info("@#"+loggingActivityRules);
			if(loggingActivityRules!=null){
				if(loggingActivityRules.isActivityLog()==true){
					commonDao.insertLoggingDetails(loggingAspect);
				}
			}
		}catch(Exception e){
			logger.error("Exception in createLoggingByAspect() method in CommonServiceImpl.java", e);
		}
	}

	/**
	 * this method is call from different aspect module and used for check Notification activity and create notification depends on notification mediums
	 * 
	 */

@Override
	public void createNotificationByAspect(LoggingAspect loggingAspect) {
		try{
			LoggingAspect notificationActivityRules = commonDao.getNotificationActivityRules(loggingAspect);	
			if(notificationActivityRules!=null){
				logger.info("NOTIFICATION?: "+notificationActivityRules.isNotification());
				if(notificationActivityRules.isNotification()==true){
					String emailSubjectTemplate = servletContext.getInitParameter("automailtemplatesubjectpath");
					String emailBodyTemplate = servletContext.getInitParameter("automailtemplatebodypath");
					Resource senderDetails = commonDao.getResourceDetails(loggingAspect.getUpdatedBy());
					if(senderDetails==null)
						senderDetails = new Resource();
					senderDetails.setEmailId(loggingAspect.getUpdatedBy());
					EmailDetails emailDetails = new EmailDetails();
					if(notificationActivityRules.getNormalNotificationResourceList()!= null && notificationActivityRules.getNormalNotificationResourceList().size()!=0){
						logger.info("NOTIFICATION TYPE:: "+notificationActivityRules.getNormalNotificationResourceList().get(0).getStatus());
						List<NotificationMedium> notificationMediums = commonDao.getNotificationMediums(notificationActivityRules.getNormalNotificationResourceList().get(0).getStatus());
						for(Resource r: notificationActivityRules.getNormalNotificationResourceList()){
							logger.info("NORMAL NOTIFICATION USER ID: "+r.getUserId());
							if(notificationMediums != null && notificationMediums.size() != 0){
								for(NotificationMedium nm: notificationMediums){
									logger.info("NORMAL Notification Medium Name: "+nm.getNotificationMediumName());
									Notification notification = new Notification();
									NotificationMedium notificationMedium = new NotificationMedium();
									notification.setNotificationSubject(loggingAspect.getLoggingSubject());
									notification.setNotificationDesc(loggingAspect.getLoggingDesc());
									notificationMedium.setNotificationMediumName(nm.getNotificationMediumName());
									notification.setNotificationMedium(notificationMedium);
									notification.setNotificationReplyTo(r.getUserId());
									notification.setUpdatedBy(loggingAspect.getUpdatedBy());
									notification.setNotificationSender(loggingAspect.getUpdatedBy());
									//int insertStatus = commonDao.createAlert(notification);
									if(nm.getNotificationMediumName().equals("E-MAIL")){
										
										emailDetails.setEmailDetailsSubject(loggingAspect.getLoggingSubject());
										emailDetails.setEmailDetailsDesc(loggingAspect.getLoggingDesc());
										emailDetails.setEmailDetailsReceiver(r.getUserId());
										emailDetails.setEmailDetailsSender(loggingAspect.getUpdatedBy());
										emailDetails.setStatus("automail");
										logger.info("KK"+emailSender);
										emailSender.sendMail(senderDetails.getEmailId(), r.getUserId(),emailDetails,emailSubjectTemplate,emailBodyTemplate);										
										logger.info("EMAIL");
									}
									if(nm.getNotificationMediumName().equals("MESSAGE")){
										logger.info(":::::"+nm.getNotificationMediumName()+" for "+r.getUserId());
										//messageSender.sendMessage(r.getMobile(), loggingAspect.getLoggingDesc());
									}
									if(nm.getNotificationMediumName().equals("ALERT")){
										logger.info(":::::"+nm.getNotificationMediumName()+" for "+r.getUserId());
										commonDao.createAlert(notification);
									}
								}
							}
						}
					}if(notificationActivityRules.getUrgentNotificationResourceList()!=null && notificationActivityRules.getUrgentNotificationResourceList().size()!=0){
						logger.info("NOTIFICATION TYPE::: "+notificationActivityRules.getUrgentNotificationResourceList().get(0).getStatus());
						List<NotificationMedium> notificationMediums = commonDao.getNotificationMediums(notificationActivityRules.getUrgentNotificationResourceList().get(0).getStatus());
						for(Resource r: notificationActivityRules.getUrgentNotificationResourceList()){
							logger.info("@@: "+notificationMediums);
							if(notificationMediums != null && notificationMediums.size() != 0){
								for(NotificationMedium nm: notificationMediums){
									logger.info("URGENT Notification Medium Name: "+nm.getNotificationMediumName());
									Notification notification = new Notification();
									NotificationMedium notificationMedium = new NotificationMedium();
									notification.setNotificationSubject(loggingAspect.getLoggingSubject());
									notification.setNotificationDesc(loggingAspect.getLoggingDesc());
									notificationMedium.setNotificationMediumName(nm.getNotificationMediumName());
									notification.setNotificationMedium(notificationMedium);
									notification.setNotificationReplyTo(r.getUserId());
									notification.setUpdatedBy(loggingAspect.getUpdatedBy());
									notification.setNotificationSender(loggingAspect.getUpdatedBy());
									//int insertStatus = commonDao.createAlert(notification);
									if(nm.getNotificationMediumName().equals("E-MAIL")){
										emailDetails.setEmailDetailsSubject(loggingAspect.getLoggingSubject());
										emailDetails.setEmailDetailsDesc(loggingAspect.getLoggingDesc());
										emailDetails.setEmailDetailsReceiver(r.getUserId());
										emailDetails.setStatus("automail");
										emailSender.sendMail(senderDetails.getEmailId(), r.getUserId(),emailDetails,emailSubjectTemplate,emailBodyTemplate);										
										logger.info("SENDING EMAIL FROM HERE");
									}
									if(nm.getNotificationMediumName().equals("MESSAGE")){
										logger.info(":::::"+nm.getNotificationMediumName()+" for "+r.getUserId());
										//messageSender.sendMessage(r.getMobile(), loggingAspect.getLoggingDesc());
									}
									if(nm.getNotificationMediumName().equals("ALERT")){
										logger.info(":::::"+nm.getNotificationMediumName()+" for "+r.getUserId());
										commonDao.createAlert(notification);
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error("Exception in createNotificationByAspect() method in CommonServiceImpl.java", e);
		}
	}
	
	@Override
	public List<String> getCity(String strQuery) {
		logger.info("In getCity(String strQuery) method of CommonServiceImpl");
		List<String> cityListFromDB = null;
		cityListFromDB = commonDao.getCity(strQuery);
		return cityListFromDB;
	}

	@Override
	public List<String> getDistrict(String strQuery) {
		logger.info("In getDistrict(String strQuery) method of CommonServiceImpl");
		List<String> districtListFromDB = null;
		districtListFromDB = commonDao.getDistrict(strQuery);
		return districtListFromDB;
	}
	
	@Override
	public String getRecIdForVenue(String venueName) {
		return commonDao.getRecIdForVenue(venueName);
	}
	
	@Override
	public String getUserLdapStatus(String resourceId) {
		return ldap.getUserLdapStatus(resourceId);
	}	
	/*
	 * 
	 * 
	 * 
	 * */
	
	@Override
	public Notification insertEmailDetails(List<EmailDetails> readEmail, String userId) {
		emailNotification = commonDao.insertEmailDetails(readEmail,userId);
		System.out.println("539 in commonserviceimpl::emailNotification::"+emailNotification);
		emailDetailsList = emailNotification.getEmailDetailsList();
		System.out.println("539 in commonserviceimpl::emaildetails::"+emailDetailsList);
		return emailNotification;
	}


	@Override
		public Notification getNotificationDetails(Notification notification) {
			logger.info("getNotificationDetails(Notification notification) method In CommonServiceImpl.java:");
			return commonDao.getNotificationDetails(notification);
		}
	
	@Override
		public void changeMailReadStatus(String emailAlertCode) {
			commonDao.changeMailReadStatus(emailAlertCode);
		}
	
	@Override
		public List<Resource> getGroupList() {
			return commonDao.getGroupList();
		}
	
	
@Override
	public String createNotification(Notification notification) {
	String returnStatus="fail";
	try{
		EmailDetails emailDetails = new EmailDetails();
		logger.info("IN BackOfficeServiceImpl createNotification(Notification notification) method.");
		int insertStatus=0;
		 
		if(notification!=null && notification.getNotificationMedium()!=null){
			List<NotificationMedium> notificationMediums = commonDao.getNotificationMediums(notification.getNotificationMedium().getNotificationMediumName());
			logger.info(notification.getNotificationReplyTo()+"SSSSSSSM:"+notificationMediums);
			if(notificationMediums != null && notificationMediums.size() != 0){
				NotificationMedium notificationMedium = new NotificationMedium();
				if(notification.getNotificationReplyTo()!=null && notification.getNotificationReplyTo().trim().length()!=0){
					for(NotificationMedium nm: notificationMediums){
						logger.info("Notification Medium Name: "+nm.getNotificationMediumName());
						Resource receiverDetails = commonDao.getResourceDetails(notification.getNotificationReplyTo().trim());
						logger.info("K " +receiverDetails);
						if(receiverDetails==null)
							receiverDetails = new Resource();
							receiverDetails.setEmailId(notification.getNotificationReplyTo());
							
						Resource senderDetails = commonDao.getResourceDetails(notification.getUpdatedBy());
						if(senderDetails==null)
							senderDetails = new Resource();
							senderDetails.setEmailId(notification.getUpdatedBy());
						logger.info(senderDetails+" ------------------- "+receiverDetails);
						if(receiverDetails!=null && senderDetails!=null){
							if(nm.getNotificationMediumName()!= null && nm.getNotificationMediumName().equals("E-MAIL")){
								notificationMedium.setNotificationMediumName("E-MAIL");
								notification.setNotificationMedium(notificationMedium);
								//insertStatus = commonDao.createAlert(notification);
								emailDetails.setEmailDetailsSubject(notification.getNotificationSubject());
								emailDetails.setEmailDetailsDesc(notification.getNotificationDesc());
								emailDetails.setEmailDetailsReceiver(receiverDetails.getEmailId());
								emailDetails.setStatus("manualmail");
								emailSender.sendMail(senderDetails.getEmailId(), receiverDetails.getEmailId(),emailDetails,notification.getEmailSubjectTemplate(),notification.getEmailBodyTemplate());
								//---------------------------------------
								//------SENDING MAIL FROM HERE-----------
								//---------------------------------------
							}
							if(nm.getNotificationMediumName()!= null && nm.getNotificationMediumName().equals("MESSAGE")){
								logger.info(":::::"+nm.getNotificationMediumName()+" for "+notification.getNotificationReplyTo());
								//messageSender.sendMessage(receiverDetails.getMobile(), notification.getNotificationDesc());
								
							}
							if(nm.getNotificationMediumName()!= null && nm.getNotificationMediumName().equals("ALERT")){
								logger.info(":::::"+nm.getNotificationMediumName()+" for "+notification.getNotificationReplyTo());
								notificationMedium.setNotificationMediumName("ALERT");
								notification.setNotificationMedium(notificationMedium);
								insertStatus = commonDao.createAlert(notification);
							}
						}
						
					}
				}
				if(notification.getNotificationDetailsList()!= null && notification.getNotificationDetailsList().size()!=0){
					for(NotificationDetails notificationDetails: notification.getNotificationDetailsList()){
						List<Resource> notificationReceiverList = commonDao.getUsersForUserGroup(notificationDetails);
						Resource senderDetails = commonDao.getResourceDetails(notification.getUpdatedBy());
							if(senderDetails==null)
								senderDetails = new Resource();
								senderDetails.setEmailId(notification.getUpdatedBy());
						for(NotificationMedium nm: notificationMediums){
								if(nm.getNotificationMediumName()!= null && nm.getNotificationMediumName().equals("E-MAIL")){
									if(notificationReceiverList != null && notificationReceiverList.size()!=0){
										for(Resource resource : notificationReceiverList) {
											notification.setNotificationReplyTo(resource.getUserId());
											logger.info(":::::"+nm.getNotificationMediumName()+" for "+notification.getNotificationReplyTo());
											notificationMedium.setNotificationMediumName("E-MAIL");
											notification.setNotificationMedium(notificationMedium);
											//insertStatus = commonDao.createAlert(notification);
											//---------------------------------------
											//------SENDING MAIL FROM HERE-----------
											//---------------------------------------
											emailDetails.setEmailDetailsSubject(notification.getNotificationSubject());
											emailDetails.setStatus("manualmail");
											emailDetails.setEmailDetailsDesc(notification.getNotificationDesc());
											emailDetails.setEmailDetailsReceiver(resource.getEmailId());
											logger.info("SEEEEEENDER::::::: "+senderDetails.getEmailId());
											emailSender.sendMail(senderDetails.getEmailId(), resource.getUserId(),emailDetails,notification.getEmailSubjectTemplate(),notification.getEmailBodyTemplate());
										}
									}
								}
								if(nm.getNotificationMediumName()!= null && nm.getNotificationMediumName().equals("MESSAGE")){
									if(notificationReceiverList != null && notificationReceiverList.size()!=0){
										for(Resource resource : notificationReceiverList) {
											//messageSender.sendMessage(resource.getMobile(), notification.getNotificationDesc());
										}
									}
								}
								if(nm.getNotificationMediumName()!= null && nm.getNotificationMediumName().equals("ALERT")){
									if(notificationReceiverList != null && notificationReceiverList.size()!=0){
										for(Resource resource : notificationReceiverList) {
											notification.setNotificationReplyTo(resource.getUserId());
											logger.info(":::::"+nm.getNotificationMediumName()+" for "+notification.getNotificationReplyTo());
											notificationMedium.setNotificationMediumName("ALERT");
											notification.setNotificationMedium(notificationMedium);
											insertStatus = commonDao.createAlert(notification);
										}
									}
								}
							}
					}
				}
			}
		}
		if(insertStatus!=0){
			returnStatus="success";
		}

	}catch(Exception e){
		
		logger.error(e);
	}
	return returnStatus;
	}

@Override
public List<AcademicYear> getPreviousAndCurrentAcademicYear() {
	return commonDao. getPreviousAndCurrentAcademicYear();
}

@Override
public Student getStudentDetails(String rollNumber) {
	return commonDao.getStudentDetails(rollNumber);
}

@Override
public List<AcademicYear> getCurrentAndNextAcademicYear() {
	return commonDao.getCurrentAndNextAcademicYear();
}

public PagedListHolder<NoticeBoard> getNoticeListPaging(HttpServletRequest request) {
	logger.info("In getBookListPaging(HttpServletRequest request) method of commonServiceImpl");
	PagedListHolder<NoticeBoard> pagedListHolder = new PagedListHolder<NoticeBoard>(noticeListFromDB);
	int page = ServletRequestUtils.getIntParameter(request, "p", 0);
	pagedListHolder.setPage(page);
	int pageSize = 5;
	pagedListHolder.setPageSize(pageSize);
	return pagedListHolder;
}

@Override
public List<String> getUserIdToCreateNotification(String strQuery) {
	return commonDao.getUserIdToCreateNotification(strQuery);
}
	
	/**Modified by @author Saif.Ali*/
	@Override
	public List<AnnualStock> selectAssetsForDepartment() throws CustomException {
		return commonDao.selectAssetsForDepartment();
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String submitASTV(AnnualStockList annualStockList) throws CustomException {
		return commonDao.submitASTV(annualStockList);
	}
	
	@Override
	public List<AnnualStock> listASTV(String department) throws CustomException {
		return commonDao.listASTV(department);
	}
	
	
	@Override
	public String updateASTV(AnnualStockList annualStockList) throws CustomException {
		return commonDao.updateASTV(annualStockList);
	}
	
	/**Modified by @author Saif.Ali
	 * Date- 20/07/2017*/
	@Override
	public List<Condemnation> listASTVForCondemnation() throws CustomException {
		return commonDao.listASTVForCondemnation();
	}
	
	@Override
	public List<Condemnation> listCondemnation(String department) throws CustomException {
		return commonDao.listCondemnation(department);
	}
	
	@Override
	public String submitCondemnation(CondemnationList condemnationList) throws CustomException {
		return commonDao.submitCondemnation(condemnationList);
	}
	
	@Override
	public String updateCondemnation(CondemnationList condemnationList) throws CustomException {
		return commonDao.updateCondemnation(condemnationList);
	}
	
	@Override
	public LicenseInfo validateLicenseDetails() {
		return commonDao.validateLicenseDetails();
	}
	
	@Override
	public void getActivityLog(String module, String functionality)throws CustomException {
		updateLogList=commonDao.getActivityLog(module, functionality);
	}

	@Override
	public PagedListHolder<UpdateLog> activityLogPagination(HttpServletRequest request) throws CustomException {
		PagedListHolder<UpdateLog> updateLogPagedListHolder = null;
		try{
			if(updateLogList!=null && updateLogList.size()!=0){
				updateLogPagedListHolder = new PagedListHolder<UpdateLog>(updateLogList);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				updateLogPagedListHolder.setPage(page);
				updateLogPagedListHolder.setPageSize(pageSize);
			}
		}catch(Exception e){
			logger.error("exception in getVendorListPaging() method CommonServiceImpl ", e);
		}
		return updateLogPagedListHolder;
	}

	@Override
	public String insertIntoActivityLog(UpdateLog updateLog)throws CustomException {
		return commonDao.insertIntoActivityLog(updateLog);
	}
	
	@Override
	public int insertAssetDetailsExcelBulkData(String excelFile, String updatedBy) throws CustomException {
		int insertStatus = 0;
		int assetDetailsInsertStatus = 0;
		try{
			logger.info("In insertAssetDetailsExcelBulkData(String excelFile, String updatedBy) method of CommonServiceImpl");
			
			List<Asset> assetDetailsList = dataUtility.readExcelFileForAssetDetails(excelFile);					
			if(null != assetDetailsList && assetDetailsList.size() != 0){
				assetDetailsInsertStatus = dataDAO.batchInsertForAssetDetails(assetDetailsList, updatedBy);
				logger.info("@@ assetDetailsInsertStatus  ::  "+assetDetailsInsertStatus);
			}
			if(assetDetailsInsertStatus != 0){
				insertStatus = 1;
			}
		}catch(Exception e){
			logger.error("Exception in insertAssetDetailsExcelBulkData() to read excel and insert in CommonServiceImpl", e);
			
		}
		return insertStatus;
	}
	
	
	@Override
	public List<AssetType> getAllAssetType() throws CustomException {
		logger.info("In getAllAssetType() method of CommonServiceImpl");
		return commonDao.getAllAssetType();
	}

	@Override
	public String addAssetType(AssetType assetType) throws CustomException {
		logger.info("In addAssetType(AssetType assetType) method of CommonServiceImpl");
		return commonDao.addAssetType(assetType);
	}

	@Override
	public String editAssetType(AssetType assetType) throws CustomException {
		logger.info("In editAssetType(AssetType assetType) method of CommonServiceImpl");
		return commonDao.editAssetType(assetType);
	}

	@Override
	public List<AssetSubType> getAllAssetSubType() throws CustomException {
		logger.info("In List<AssetSubType> getAllAssetSubType() method of CommonServiceImpl");
		return commonDao.getAllAssetSubType();
	}

	@Override
	public String addAssetSubType(AssetSubType assetSubType) throws CustomException {
		logger.info("In addAssetSubType(AssetSubType assetSubType) method of CommonServiceImpl");
		return commonDao.addAssetSubType(assetSubType);
	}

	@Override
	public String editAssetSubType(AssetSubType assetSubType) throws CustomException {
		logger.info("In editAssetSubType(AssetSubType assetSubType) method of CommonServiceImpl");
		return commonDao.editAssetSubType(assetSubType);
	}

	@Override
	public String deleteAssetSubType(AssetSubType assetSubType) throws CustomException {
		logger.info("In deleteAssetSubType(AssetSubType assetSubType) method of CommonServiceImpl");
		return commonDao.deleteAssetSubType(assetSubType);
	}
	
	@Override
	public String getAssetSubTypeForAssetType(String assetType) {
		String assetSubType = "";
		try{
			logger.info("In getAssetSubTypeForAssetType(String assetType) method of CommonServiceImpl");
			List<AssetSubType> assetSubTypeList = commonDao.getAssetSubTypeForAssetType(assetType);
			if(assetSubTypeList!=null && assetSubTypeList.size()!=0){
				StringBuilder strBuilder = new StringBuilder();
				for(AssetSubType csbtype: assetSubTypeList){
					strBuilder.append(csbtype.getAssetSubTypeCode());
					strBuilder.append("#");
					strBuilder.append(csbtype.getAssetSubTypeName());
					strBuilder.append(";");
				}
				assetSubType= strBuilder.toString();
			}
			
		}catch(Exception e){
			logger.error("Exception in getCommoditySubTypeForCommodityType() in CommonServiceImpl ", e);
			e.printStackTrace();
		}
		return assetSubType;
	}
	
	@Override
	public List<AssetSubType> getAllAssetSubTypeForAssetType(String assetCode) throws CustomException {
		logger.info("In getAllAssetSubTypeForAssetType(String assetCode) method of CommonServiceImpl");
		return commonDao.getAssetSubTypeForAssetType(assetCode);
	}
	
	@Override
	public int updateLibraryAssetDetails(Asset asset) {
		return commonDao.updateLibraryAssetDetails(asset);
	}
	
	/**
	 * @author naimisha.ghosh*/
	
	@Override
	public List<Leave> getLeaveHistory(String userId){
		return commonDao.getLeaveHistory(userId);
	}
	
	@Override
	public List<Leave> getLeaveDetails() {
		return commonDao.getLeaveDetails();
	}

	@Override
		public String getApprovarGroupName(String user_id) {
			return commonDao.getApprovarGroupName(user_id);
	}
	
	@Override
	public int insertIntoApprovalAndLeave(Leave leave) throws CustomException {
		return commonDao.insertIntoApprovalAndLeave(leave);
	}
	
	@Override
	public List<Leave> getResourceLeaveDetails(String userId) {
		return commonDao.getResourceLeaveDetails(userId);
	}

	@Override
	public ResourceType getResourceTypeofUser(String userId) {
		return commonDao.getResourceTypeofUser(userId);
	}
	
	@Override
	public List<ServiceType> getServiceTypeList() {
		return commonDao.getServiceTypeList();
	}
	
	
	/*******Changed By Naimisha 30032017******/
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public Ticket generateTicket(Ticket ticket) {
		return commonDao.generateTicket(ticket);
	}
	
	@Override
	public void ticketDocumentUpload(Ticket ticket) {
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Attachment attachment = null;
		String dynamicSubFolderPath = ticket.getTicketService().getTicketServiceCode()+"/";
		System.out.println("dynamicSubFolderPath===="+dynamicSubFolderPath);
		/*String filePath = ticket.getUploadFile().getAttachment().getStorageRootPath();*/
		String filePath = ticket.getUploadFile().getAttachment().getStorageRootPath()+"/";
		filePath = filePath + ticket.getUploadFile().getAttachment().getFolderName();
		System.out.println("filePath======"+filePath);
		//filePath = filePath+dynamicSubFolderPath;
		try{
			UploadFile uploadFile = ticket.getUploadFile();					
			if(uploadFile!=null){
				/*
				 * this is used for upload Ticket Related Documents
				 */
				if(uploadFile.getTicketingRelatedFile()!=null && !uploadFile.getTicketingRelatedFile().isEmpty()){
				//	String ticketDocPath =  filePath+"ticket_doc"+"/"+ticket.getTicketRecId()+"/";
					String ticketDocPath =  filePath+ticket.getTicketCode()+"/";
					logger.info("UPDATE TICKET DOC PATH:"+ticketDocPath);
					for (CommonsMultipartFile file : uploadFile.getTicketingRelatedFile()) {
						if(file.getOriginalFilename()!=""){
							attachment = new Attachment();
							int fileSize = fileUploadDownload.fileUpload(ticketDocPath, file);
							attachment.setStorageRootPath(ticketDocPath);
							attachment.setAttachmentType("ticket_doc");
							attachment.setAttachmentName(file.getOriginalFilename());
							attachment.setAttachmentSize(fileSize);
							attachmentList.add(attachment);
						}
					}
				}
				ticket.setAttachmentList(attachmentList);
				String uploadStatus = commonDao.submitTicketAttachmentDoc(ticket);
				logger.info("ticketDocumentUpload Status:" + uploadStatus);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("ticketDocumentUpload() In CommonServiceImpl.java: Exception" + e);
		}
		
	}
	
	@Override
	public List<Ticket> getTicketList(String updatedBy) {
		ticketList = commonDao.getTicketList(updatedBy);
		return ticketList;
	}
	
	@Override
	public PagedListHolder<Ticket> getTicketListPaging(HttpServletRequest request) {
		logger.info("In getTicketListPaging(HttpServletRequest request) method of TicketServiceImpl");
		PagedListHolder<Ticket> pagedListHolder = new PagedListHolder<Ticket>(ticketList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}
	
	@Override
	public List<Ticket> getClosedTicketList(String updatedBy) {
		ticketList = commonDao.getClosedTicketList(updatedBy);
		return ticketList;
	}
	
	
	@Override
	public PagedListHolder<Ticket> closedTicketListPaging(HttpServletRequest request) {
		logger.info("In closedTicketListPaging(HttpServletRequest request) method of TicketServiceImpl");
		PagedListHolder<Ticket> pagedListHolder = new PagedListHolder<Ticket>(ticketList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}
	


	@Override
	public Ticket getTicketDetails(Ticket ticket) {
		return commonDao.getTicketDetails(ticket);
	}
	
/*/Naimisha/*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public Ticket updateTicket(Ticket ticket) {
		return commonDao.updateTicket(ticket);
	}
	
	@Override
	public List<FinancialYear> getFinancialYearList() {
		return commonDao.getFinancialYearList();
	}

	@Override
	public List<IncomeAge> getIncomeAgeList() {
		return commonDao.getIncomeAgeList();
	}

	@Override
	public List<TeachingLevel> getTeachingLevelList() {
		return commonDao.getTeachingLevelList();
	}
	
//-------------------------FOR ADMISSION------------------------------------
	@Override
	public List<SocialCategory> getExistingSocialCategory() {
		return commonDao.getExistingSocialCategory();
	}
	
	@Override
	public List<AdmissionDrive> getDriveList(Resource r) {
		logger.info("getDriveList(Resource r) method in In CommonServiceImpl");
		return commonDao.getDriveList(r);
	}
	
	@Override
	public List<MeritListType> getMeritListTypes() {
		return commonDao.getMeritListTypes();
	}
	
	@Override
	public List<Venue> getExamVenues(Map<String, Object> parameters) {
		venueList = commonDao.getExamVenues(parameters);
		if(venueList!=null && venueList.size()!=0){
			for(Venue venue : venueList){
					//int totalAllotedSeat = venue.getCapacity()-((int)(venue.getEndSeatRollNo()-venue.getStartSeatRollNo())+1);
					int totalAllotedSeat =commonDao.getTotalAllotedSeat(venue);
					logger.info(totalAllotedSeat+" KKKKKKKKKKKKKkk "+venue.getEndSeatRollNo()+" KKKKKK "+venue.getStartSeatRollNo());
					int availableSeat = venue.getCapacity()-totalAllotedSeat;
					venue.setAvailableSeat(availableSeat);
			}
		}
		return venueList;
	}
	
	@Override
	public Venue getExamVenueDetails(Venue venue) {
		return commonDao.getExamVenueDetails(venue);
	}
	
	
	@Override
	public List<Task> getDelegatedTask(Task task) {
		return commonDao.getDelegatedTask(task);
	}

	@Override
	public List<Task> getOutwardDelegatedTask(Task task) {
		return commonDao.getOutwardDelegatedTask(task);
	}
	
	@Override
	public String editVendorDetails(Vendor vendor) {
		return commonDao.editVendorDetails(vendor);
	}
	
	@Override
	public Notification deleteEmailDetails(Notification notification) {
		emailNotification = commonDao.deleteEmailDetails(notification);
		emailDetailsList = emailNotification.getEmailDetailsList();
		return emailNotification;
	}

	@Override
	public List<EmailDetails> getEmailDetailsPaging(HttpServletRequest request) {
		return null;
	}
	
	@Override
	public FinancialYear getFinancialYear() {
		return commonDao.getFinancialYear();
	}
	
	@Override
	public List<AcademicYear> getAllAcademicYear() {
		return commonDao.getAllAcademicYear();
	}
	
	/**@author anup.roy
	 * modified on 190117*/
	@Override
	public String getCourseForStandard(String standard){
		String stringCourseList="";
		List<Course> courseList = commonDao.getCourseForStandard(standard);
		if(null != courseList && courseList.size()!=0){
			for(Course c:courseList){
				stringCourseList=stringCourseList+c.getCourseCode()+"*"+c.getCourseName()+"~";
			}
		}
		return stringCourseList;
	}
	
	@Override
	public List<Subject> getSubject(){
		return commonDao.getSubject();
	}

	@Override
	public String getStandatdCodeAgainstCourse(String course) {
		return commonDao.getStandatdCodeAgainstCourse(course);
	}
	
	@Override
	public List<Course> getCourseList() {
		return commonDao.getCourseList();
	}

	@Override
	public String getBatchAgainstCourse(String course)  {
		String section = "";
 		try{
 			List<Section> sectionList = commonDao.getBatchAgainstCourse(course);
 			StringBuilder sb = new StringBuilder();
 			if(sectionList!=null && sectionList.size()!=0){				
				for(Section sectionObject : sectionList){
					if (sb.length() != 0) {
						sb.append(";");
				    }
					sb.append(sectionObject.getSectionCode());
					sb.append("*");
					sb.append(sectionObject.getSectionName());
				}
				
			}else{
				logger.info("Batch not found by ajax @ getSectionAgainstStandard()In CommonServiceImpl");
			}
 			section = (new Gson().toJson(sb.toString()));
 		}catch(Exception e){
 			logger.error("Exception in getSectionAgainstStandard() in CommonServiceImpl ", e);
 		}
 		
		return section;
	}

	/*************************Added By Naimisha 04042017*************/
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String updateTicketAndTaskStatus(Ticket ticket) {
		return commonDao.updateTicketAndTaskStatus(ticket);
	}

	@Override
	public List<Task> getAllTaskCommentForATask(Task task) {
		return commonDao.getAllTaskCommentForATask(task);
	}

	
	@Override
	public String createXMLFileForNOC(Ticket ticket, String reportNOCConfigFilePath, String reportNOCXSLTFilePath, String ticketAttachmentsPath) {
		String status = null;
		AcademicYear currentAcademicYear;
		try {
			currentAcademicYear = getCurrentAcademicYear();
			String userName = commonDao.getUserNameForId(ticket.getReportedBy());
			ticket.setUpdatedBy(userName);
			ticket.setPdfFilePath(ticketAttachmentsPath);
			ticket.setReportNOCConfigFilePath(reportNOCConfigFilePath);
			ticket.setXsltFilePath(ticket.getRootPath()+"/"+reportNOCXSLTFilePath);
			ticket.setCurrentAcademicYear(currentAcademicYear);
			createNOCForAStudent(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return status;
	}

	public void createNOCForAStudent(Ticket ticket) {
		try{
			String baseDir = ticket.getRootPath()+"/"+ticket.getReportNOCConfigFilePath()+ticket.getCurrentAcademicYear().getAcademicYearName()+"/"+ticket.getReportedBy()+"/" ;
			String pdfFilePath =ticket.getRootPath()+"/"+ticket.getPdfFilePath()+ ticket.getTicketCode()+"/";
			String fileName="";
			fileName = "NOC_"+ticket.getCurrentAcademicYear().getAcademicYearName()+"_"+ticket.getReportedBy();
			
			Report report = new Report();
			
			report.setUpdatedBy(ticket.getUpdatedBy());
			report.setReportCode(ticket.getReportedBy());
			report.setAcademicYear(ticket.getCurrentAcademicYear());
			String xmlFilePath = baseDir+"xml/";
			String xmlFileName = "NOC"+".xml";
			report.setXmlFilePath(xmlFilePath);
			report.setXmlFileName(xmlFileName);
			xMLBuilder.createXMLFileForNOC(report);
			
			String xsltFileName = "NOC.xsl";
			report.setXsltFilePath(ticket.getXsltFilePath());
			report.setXsltFileName(xsltFileName);
			String pdfFileName = fileName+".pdf";
			report.setPdfFilePath(pdfFilePath);
			report.setPdfFileName(pdfFileName);
			try{
				pdfBuilder.createPDF(report);
			}catch(IOException e){
				logger.error(e);
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public String getSectionsAgainstStandard(String standard) throws CustomException {
		String section = "";
 		try{
 			List<Section> sectionList = commonDao.getSectionsAgainstStandard(standard);
 			StringBuilder sb = new StringBuilder();
 			if(sectionList!=null && sectionList.size()!=0){				
				for(Section sectionObject : sectionList){
					if (sb.length() != 0) {
						sb.append(";");
				    }
					sb.append(sectionObject.getSectionCode());
					sb.append("*");
					sb.append(sectionObject.getSectionName());
				}
				
			}else{
				logger.info("Section not found by ajax @ getSectionAgainstStandard()In CommonServiceImpl");
			}
 			section = (new Gson().toJson(sb.toString()));
 		}catch(Exception e){
 			logger.error("Exception in getSectionAgainstStandard() in CommonServiceImpl ", e);
 		}
 		
		return section;
	}
	
	/**
	 * new 2methods added by sourav.bhadra
	 * changes taken on 20042017**/

	@Override
	public String createXMLFileForGatePass(Ticket ticket, String reportGatePassConfigFilePath, String reportGatePassXSLTFilePath, String ticketAttachmentsPath) {
		String status = null;
		AcademicYear currentAcademicYear;
		try {
			currentAcademicYear = getCurrentAcademicYear();
			String userName = commonDao.getUserNameForId(ticket.getReportedBy());
			ticket.setUpdatedBy(userName);
			ticket.setPdfFilePath(ticketAttachmentsPath);
			ticket.setReportGatePassConfigFilePath(reportGatePassConfigFilePath);
			ticket.setXsltFilePath(ticket.getRootPath()+"/"+reportGatePassXSLTFilePath);
			ticket.setCurrentAcademicYear(currentAcademicYear);
			createGatePassForAStudent(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return status;
	}

	/*******new method added*******/

	public void createGatePassForAStudent(Ticket ticket) {
		try{
			String baseDir = ticket.getRootPath()+"/"+ticket.getReportGatePassConfigFilePath()+ticket.getCurrentAcademicYear().getAcademicYearName()+"/"+ticket.getReportedBy()+"/" ;
			String pdfFilePath =ticket.getRootPath()+"/"+ticket.getPdfFilePath()+"/"+ticket.getTicketCode()+"/";
			String fileName="";
			fileName = "GATEPASS_"+ticket.getCurrentAcademicYear().getAcademicYearName()+"_"+ticket.getReportedBy();
			Report report = new Report();
			
			report.setUpdatedBy(ticket.getUpdatedBy());
			report.setReportCode(ticket.getReportedBy());
			report.setAcademicYear(ticket.getCurrentAcademicYear());
			String xmlFilePath = baseDir+"xml/";
			String xmlFileName = "GATEPASS"+".xml";
			report.setXmlFilePath(xmlFilePath);
			report.setXmlFileName(xmlFileName);
			xMLBuilder.createXMLFileForGatePass(report);
			
			String xsltFileName = "GATEPASS.xsl";
			report.setXsltFilePath(ticket.getXsltFilePath());
			report.setXsltFileName(xsltFileName);
			String pdfFileName = fileName+".pdf";
			report.setPdfFilePath(pdfFilePath);
			report.setPdfFileName(pdfFileName);
			try{
				pdfBuilder.createPDF(report);
			}catch(IOException e){
				logger.error(e);
				e.printStackTrace();
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Course getCourseAgainstCourseCode(String courseCode, String userId) {
		return commonDao.getCourseAgainstCourseCode(courseCode,userId);
	}

	@Override
	public Resource getResourceAgainstUserId(String userId) {
		return commonDao.getResourceAgainstUserId(userId);
	}

	@Override
	public List<Section> getSectionListAgainstStandard(String standard) {
		List<Section> sectionList = null;
 		try{
 			sectionList = commonDao.getSectionAgainstStandard(standard);
 			
 		}catch(Exception e){
 			logger.error("Exception in getSectionAgainstStandard() in CommonServiceImpl ", e);
 		}
 		
		return sectionList;
	}

	@Override
	public String getrollNumberAgainstProgramAndUserId(String userId, String course) {
		return commonDao.getrollNumberAgainstProgramAndUserId(userId,course);
	}
	
	@Override
	public String submitVendorType(VendorType vendorType) {
		return commonDao.submitVendorType(vendorType);
	}

	@Override
	public String editVendorType(VendorType vendorType) {
		return commonDao.editVendorType(vendorType);
	}
	
	@Override
	public String inactiveVendorDetails(Vendor vendor) {
		return commonDao.inactiveVendorDetails(vendor);
	}

	@Override
	public String sendMailFromMyservice(Notification notification) {
		String returnStatus="success";
		try{
			EmailDetails emailDetails = new EmailDetails();
			logger.info("IN BackOfficeServiceImpl createNotification(Notification notification) method.");
			int insertStatus=0;
			List<NotificationDetails> notificationDetailsList = notification.getNotificationDetailsList();
			Resource senderDetails = commonDao.getResourceDetails(notification.getUpdatedBy());
			if(senderDetails==null)
				senderDetails = new Resource();
				senderDetails.setEmailId(notification.getUpdatedBy());
			logger.info(senderDetails+" ------------------- "+senderDetails);
			System.out.println("notificationDetailsList size =="+notificationDetailsList.size());
			for(NotificationDetails notificationDetails :notificationDetailsList){
				Resource receiverDetails = commonDao.getResourceDetails(notificationDetails.getNotificationReceiver().trim());
				logger.info("K " +receiverDetails);
				if(receiverDetails==null)
					receiverDetails = new Resource();
					receiverDetails.setEmailId(notificationDetails.getNotificationReceiver());
					
				
				if(receiverDetails!=null && senderDetails!=null){
					emailDetails.setEmailDetailsSubject(notification.getNotificationSubject());
					emailDetails.setEmailDetailsDesc(notification.getNotificationDesc());
					emailDetails.setStatus("manualmail");
					emailDetails.setEmailDetailsReceiver(receiverDetails.getEmailId());
					emailDetails.setEmailDetailsSender(senderDetails.getEmailId());
					if(null != notification.getUploadFile()){
						emailDetails.setEmailRelatedFile(notification.getUploadFile().getEmailRelatedFile());
					}
					
					if(null != notification.getAttachmentType()){
						if(notification.getAttachmentType().equalsIgnoreCase("Attachment")){
							emailDetails.setFilePath(notification.getEmailFilepath());
							emailDetails.setFileList(notification.getFileList());
						}else if(notification.getAttachmentType().equalsIgnoreCase("ICS")){
							emailDetails.setFilePath(notification.getIcsFilepath());
							List<String> icsFileList =  new ArrayList<>();
							icsFileList.add(notification.getIcsFile());
							emailDetails.setFileList(icsFileList);
						}
					}
					
					
					Notification emailNotification = new Notification();
					emailNotification.setNotificationReplyTo(notificationDetails.getNotificationReceiver());
					emailNotification.setNotificationSubject(notification.getNotificationSubject());
					emailNotification.setNotificationSender(notification.getUpdatedBy());
					emailNotification.setNotificationDesc(notification.getNotificationDesc());
					emailNotification.setUpdatedBy(notification.getUpdatedBy());
					if(null != notification.getEmailFilepath()){
						emailNotification.setEmailFilepath(notification.getEmailFilepath());
					}
					if(null != notification.getIcsFilepath()){
						emailNotification.setEmailFilepath(notification.getIcsFilepath());
					}
					
					emailNotification.setUpdatedBy(notification.getUpdatedBy());
					//emailNotification.setFileList(notification.getFileList());
					String status = emailSender.sendEMail(emailDetails);
					if(status.equalsIgnoreCase("success")){
						String insertStatusStatus = commonDao.insertEmailNotification(emailNotification);
						if(insertStatusStatus.equalsIgnoreCase("success")){
							if(null != notification.getAttachmentType()){
								emailNotification.setAttachmentType(notification.getAttachmentType());
								if(notification.getAttachmentType().equalsIgnoreCase("Attachment")){
									if(null != notification.getFileList() && notification.getFileList().size() !=0){
										for(String fileString : notification.getFileList()){
											emailNotification.setEmailBodyTemplate(fileString);
											String uploadStatus = commonDao.updateEmailAttachmentDoc(emailNotification);
										}
									}
								}
								else if(notification.getAttachmentType().equalsIgnoreCase("ICS")){
									emailNotification.setEmailBodyTemplate(notification.getIcsFile());
									String uploadStatus = commonDao.updateEmailAttachmentDoc(emailNotification);
								}
							}
							
							
							
							
						}
						
					}
					
					//String status = emailSender.sendEMail(senderDetails.getEmailId(), receiverDetails.getEmailId(),emailDetails,notification.getEmailSubjectTemplate(),notification.getEmailBodyTemplate());
				}
			}	
		
		}catch(Exception e){
			returnStatus = "fail";
			e.printStackTrace();
			logger.error(e);
		}
		return returnStatus;
	}

	@Override
	public List<EmailDetails> getsentEmailDetailsList(String userId) {
		return commonDao.getsentEmailDetailsList(userId);
	}

	@Override
	public EmailDetails getEmailDetailsAgainstEmailCode(String emailId) {
		return commonDao.getEmailDetailsAgainstEmailCode(emailId);
	}

	@Override
	public EmailDetails getEmailContentForSentItemsAgainstEmailCode(String emailId) {
		return commonDao.getEmailContentForSentItemsAgainstEmailCode(emailId);
	}

	@Override
	public String inactiveEmailFromSentBox(List<EmailDetails> emailDetailsList) {
		return commonDao.inactiveEmailFromSentBox(emailDetailsList);
	}

	@Override
	public List<EmailDetails> inactiveEmailFromInBox(List<EmailDetails> emailDetailsList) {
		return commonDao.inactiveEmailFromInBox(emailDetailsList);
	}
	
	/**@author Saif.Ali
	 * date- 19/07/2017
	 * Used to get the list of assets from the commodity**/
	/*@Override
	public List<AnnualStock> selectAssetListForASTB(){
		return commonDao.selectAssetListForASTB();
	}*/
	
	/**@author Saif.Ali
	 * DATE- 20/07/2017
	 * Used to get the list of condemned items*//*
	public List<Condemnation> listASTVForCondemnation(){
		return commonDao.listASTVForCondemnation();
	}*/
	
	/*added by ranita.sur on 02082017 For Vendor emailId Validation */
	@Override
	public String serverSideValidationOfVendorEmailId(String emailId) {
		return commonDao.serverSideValidationOfVendorEmailId(emailId);
	}

	//Added By Naimisha Ghosh 02082017
	@Override
	public Employee getStaffSalaryDetailsForSalarySlip(Resource resource) {
		return commonDao.getStaffSalaryDetailsForSalarySlip(resource);
	}

	@Override
	public Ticket getTicketDetailsForMyService(Ticket ticket) {
		return commonDao.getTicketDetailsForMyService(ticket);
	}

	@Override
	public List<Task> getClosedTaskList(Task task) {
		return commonDao.getClosedTaskList(task);
	}

	//Added By Naimisha 29082017
	@Override
	public List<QuestionMaster> fetchQuestionAnswerForSurveyOfATicket(JobType jobType) {
		return commonDao.fetchQuestionAnswerForSurveyOfATicket(jobType);
	}

	@Override
	public String insertTicketSurvey(Question question) {
		return commonDao.insertTicketSurvey(question);
	}

	@Override
	public  List<Answer> getServeyDetailsForATicket(String ticketCode) {
		return commonDao.getServeyDetailsForATicket(ticketCode);
	}

	@Override
	public String getIndividualReportForMyService(StudentResult studentResult,HttpServletResponse response) {
		RepositoryStructure repositoryStructure = administratorDAO.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		System.out.println("repository==="+repository);
		File directory = new File(repository);
		boolean isExists = directory.exists();
		String status = "success";
		Report report = new Report();
		if(isExists == true){
			//for(StudentResult studentResult : studentResultList){
				String baseDir = repository+"/myreport/"+ studentResult.getCourse()+"/"+studentResult.getBatch()+"/"+studentResult.getTerm()+"/"+studentResult.getExam().replaceAll(" ", "-")+"/";
				
				String fileName = studentResult.getRollNumber();
				System.out.println("baseDir==="+baseDir);
				System.out.println("roll number==="+studentResult.getRollNumber());
				String xmlFilePath = baseDir+"xml/";
				String xmlFileName = fileName.replaceAll("/", "-")+".xml";
				System.out.println("xmlFileName==="+xmlFileName);
				report.setXmlFilePath(xmlFilePath);
				report.setXmlFileName(xmlFileName);
				Student student = new Student();
				String courseName = backOfficeDAO.getCourseNameAgainstCourseCode(studentResult.getCourse());
				String termName = backOfficeDAO.getTermNameAgainstTermCode(studentResult.getTerm());
				student.setCourseName(studentResult.getCourse());
				student.setSection(studentResult.getBatch());
				student.setStandard(studentResult.getTerm());
				student.setRoll(studentResult.getRollNumber());
				student.setStudentName(studentResult.getName());
				student.setDateOfAdmission(studentResult.getAcademicYear());
				student.setTerm(studentResult.getExam());
				List<StudentResult> studentResultListNew = backOfficeDAO.getMarksOfAllSubjectForPromotion(student);
				System.out.println("studentResultListNew===="+studentResultListNew.size());
				student.setStudentResultList(studentResultListNew);
				student.setCourseId(courseName);
				student.setStatus(termName);
				student.setRoll(studentResult.getRollNumber());
				report.setStudent(student);
				xMLBuilder.createXMLFileForStudentPromotion(report);
				
				String xsltFilePath = repository +"/myreport/"+ studentResult.getCourse()+"/"+studentResult.getBatch()+"/"+studentResult.getTerm()+"/"+studentResult.getExam().replaceAll(" ", "-")+"/xslt/";
				String xsltFileName = "result.xsl";
				report.setXsltFilePath(xsltFilePath);
				report.setXsltFileName(xsltFileName);
				String pdfFilePath = repository +"/myreport/"+ studentResult.getCourse()+"/"+studentResult.getBatch()+"/"+studentResult.getTerm()+"/"+studentResult.getExam().replaceAll(" ", "-")+"/pdf/"+fileName.replaceAll("/", "-")+"/";
				String pdfFileName = fileName.replaceAll("/", "-")+".pdf";
				System.out.println("pdfFilePath==="+pdfFilePath);
				report.setPdfFilePath(pdfFilePath);
				report.setPdfFileName(pdfFileName);
				try{
					pdfBuilder.createPDF(report);
					}catch (IOException e) {
						status = "fail";
						logger.error(e);
					}	
					
						if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
							if (Desktop.isDesktopSupported()) {
//								Desktop.getDesktop().open(new File(report.getPdfFilePath()+report.getPdfFileName()));
								fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
							}
						}
						
				//report.setStudentList();
				//report.setReportCode(reportCode);
		//	}
			//createReportForStudentPromotion(studentResultList, repository,response);
		}
		return status;
	}
	
	/**Added by @author Saif.Ali
	 * Date-05/09/2017*/
	@Override
	public List<Course> getProgramDetailsList(Course course)
	{
		return commonDao.getProgramDetailsList(course);
		
	}
	
	@Override
	public List<Fees> getAllFeesRelatedInformationForStudent(Course course)
	{
		return commonDao.getAllFeesRelatedInformationForStudent(course);
	}
	
	//Added By Naimisha 08092017
	@Override
	public Student getStudentAgainstProgramAndUserId(String userId, String course) {
		return commonDao.getStudentAgainstProgramAndUserId(userId,course);
	}

	/*added by ranita.sur on 08092017*/
	@Override
	public List<BookAllocation> getBookFineDetails(String userId) {
		return commonDao.getBookFineDetails(userId);
	}

	@Override
	public String insertChatDetailsForIndividualChat(String from, String to, String msg) {
		
		return commonDao.insertChatDetailsForIndividualChat(from,to,msg);
	}

	@Override
	public String updateChatStatusToReadForAUser(String user) {
		return commonDao.updateChatStatusToReadForAUser(user);
	}

	@Override
	public List<Notification> getChatDetailsForIndividualChatForAUser(String to, String from) {
		return commonDao.getChatDetailsForIndividualChatForAUser(to,from);
	}
	
	@Override
	public String emailDocumentUpload(Notification notification) {
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Attachment attachment = null;
		String finalpath = "";
		String fileList = "";
		//String dynamicSubFolderPath = ticket.getTicketService().getTicketServiceCode()+"/";
	//	System.out.println("dynamicSubFolderPath===="+dynamicSubFolderPath);
		/*String filePath = ticket.getUploadFile().getAttachment().getStorageRootPath();*/
		String filePath = notification.getUploadFile().getAttachment().getStorageRootPath()+"/";
		filePath = filePath+notification.getUploadFile().getAttachment().getFolderName();
		System.out.println("filePath======"+filePath);
		//filePath = filePath+dynamicSubFolderPath;
		try{
			UploadFile uploadFile = notification.getUploadFile();					
			if(uploadFile!=null){
				/*
				 * this is used for upload Ticket Related Documents
				 */
				if(uploadFile.getEmailRelatedFile()!=null && !uploadFile.getEmailRelatedFile().isEmpty()){
				//	String ticketDocPath =  filePath+"ticket_doc"+"/"+ticket.getTicketRecId()+"/";
					Date date =new Date();
				    long epoch = date.getTime();
					String emailDocPath =  filePath+"sent/"+notification.getNotificationReplyTo()+"/"+epoch+"/"; 
					logger.info("UPDATE Email DOC PATH:"+emailDocPath);
					for (CommonsMultipartFile file : uploadFile.getEmailRelatedFile()) {
						if(file.getOriginalFilename()!=""){
							attachment = new Attachment();
							int fileSize = fileUploadDownload.fileUploadForEmail(emailDocPath, file);
							attachment.setStorageRootPath(emailDocPath);
							attachment.setAttachmentType("email_doc");
							attachment.setAttachmentName(file.getOriginalFilename());
							attachment.setAttachmentSize(fileSize);
							attachmentList.add(attachment);
							fileList = fileList + file.getOriginalFilename()+"#";
						}
					}
					finalpath = emailDocPath + "*" + fileList;
				}
				notification.setAttachmentList(attachmentList);
				//String uploadStatus = commonDao.updateEmailAttachmentDoc(notification);
				//logger.info("emailDocumentUpload Status:" + uploadStatus);
			}
		}catch(Exception e){
			logger.error("emailDocumentUpload() In CommonServiceImpl.java: Exception" + e);
		}
		return finalpath;
	}

	@Override
	public String updateMyEventsForMeetingInvitation(Notification notification) {
		return commonDao.updateMyEventsForMeetingInvitation(notification);
	}

	
	//Added By Naimisha 24102017
	@Override
	public List<Task> getTaskListForATicket(String ticketCode) {
		return commonDao.getTaskListForATicket(ticketCode);
	}

	@Override
	public List<Task> getTaskListForACategory(String ticketServiceCode) {
		return commonDao.getTaskListForACategory(ticketServiceCode);
	}

	@Override
	public String getResourceUserIdForMinimumNoOfOpenTicket(String groupCode) {
		return commonDao.getResourceUserIdForMinimumNoOfOpenTicket(groupCode);
	}

	@Override
	public Ticket getTaskDetailsOfATask(Task task) {
		return commonDao.getTaskDetailsOfATask(task);
	}

	@Override
	public List<TicketStatus> getAllTaskStatusList(Task task) {
		return commonDao. getAllTaskStatusList( task);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public Ticket updateTaskDetails(Ticket ticket) {
		return commonDao.updateTaskDetails(ticket);
	}

	@Override
	public List<TimeTableGridData> getMyTimeTableGridData(String semester, String userId) {
		return commonDao.getMyTimeTableGridData(semester,userId);
	}

	@Override
	public List<TimeTableGridData> getTimeTableGridDataForStudent(String semester, String program) {
		return commonDao.getTimeTableGridDataForStudent(semester,program);
	}
	
	//Added by Naimisha 06112017
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public Ticket updateMyTicket(Ticket ticket) {
		return commonDao.updateMyTicket(ticket);
	}

	@Override
	public Ticket getStudentLeaveDetailsAgainstTicket(String ticketCodeValue) {
		return commonDao.getStudentLeaveDetailsAgainstTicket(ticketCodeValue);
	}

	//Added By Naimisha 29032018
	@Override
	public List<String> getLevelListForTicket() {
		return commonDao.getLevelListForTicket();
	}

	@Override
	public CommodityPurchaseOrder getCommodityPurchaseOrderDetaialsForATicket(String ticketCodeValue) {
		return commonDao.getCommodityPurchaseOrderDetaialsForATicket(ticketCodeValue);
	}

	@Override
	public String getUserListAssociatedWithATicket(String ticketCode) {
		String users = "";
		try{
			Ticket usersForTicket = commonDao.getUserListAssociatedWithATicket(ticketCode);
			users = users + usersForTicket.getReportedBy()+"*"+usersForTicket.getUserName()+"#"
					+usersForTicket.getAffectedUser()+"*"+usersForTicket.getApproval();
			System.out.println("users=="+users);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return users;
	}
	
	/**
	 * @author anup.roy
	 * for fetching the list of status items*/

	@Override
	public List<StatusOfItem> getAllStatusOfItems() {
		return commonDao.getAllStatusOfItems();
	}
	/**
	 * @author anup.roy
	 * this method is for submitting all status of items*/

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String submitStatusOfItem(StatusOfItem status) {
		return commonDao.submitStatusOfItem(status);
	}

	//Added by naimisha 09042018
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertTicketStatus(Ticket ticket) {
		return commonDao.insertTicketStatus(ticket);
	}

	
	/* added by sourav.bhadra on 10-04-2018 */
	@Override
	public String getBudgetOfSubDeptsForAFinancialYear(String financialYear, String department) {
		return commonDao.getBudgetOfSubDeptsForAFinancialYear(financialYear, department);
	}
	
	/* added by sourav.bhadra on 11-04-2018 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String updateParentDepartmentBudgetDetails(Budget budgetDetails) {
		return commonDao.updateParentDepartmentBudgetDetails(budgetDetails);
	}
	
	/* added by sourav.bhadra on 11-04-2018 */
	@Override
	public Budget getBudgetDetailsForADepartment(String financialYear, String department) {
		return commonDao.getBudgetDetailsForADepartment(financialYear, department);
	}
	
	/* added by sourav.bhadra on 11-04-2018 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String reserveFundEstimation(Budget budget) {
		return commonDao.reserveFundEstimation(budget);
	}

	@Override
	public Department getDepartmentForAUser(String userId) {
		return commonDao.getDepartmentForAUser(userId);
	}

	@Override
	public TicketStatus getTicketStatusForAType(String type) {
		return commonDao.getTicketStatusForAType(type);
	}
	
	/* added by sourav.bhadra on 17-04-2018 */
	@Override
	public List<Department> getAllSubDepartmentsOfADepartment(String parentDepartment) {
		return commonDao.getAllSubDepartmentsOfADepartment(parentDepartment);
	}
	
	/* added by sourav.bhadra on 17-04-2018 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String updateSubDepartmentsBudget(Budget budget) {
		return commonDao.updateSubDepartmentsBudget(budget);
	}

	@Override
	public List<TicketStatus> getTicketStatusListForAType(String type) {
		return commonDao.getTicketStatusListForAType(type);
	}

	//Added By Naimisha 21052018
	@Override
	public List<Task> getTaskNoListForUserAndFunctinalityWise(String userId, String functionality) {
		return commonDao.getTaskNoListForUserAndFunctinalityWise(userId,functionality);
	}

	@Override
	public String insertIntoSchoolNote(SchoolNote schoolNote) {
		return commonDao.insertIntoSchoolNote(schoolNote);
	}

	@Override
	public List<SchoolNote> getSchoolNoteList() {
		return commonDao.getSchoolNoteList();
	}

	@Override
	public List<String> getMobileNumberAgainstRollNumbers(String[] rollNumbers){
		return commonDao.getMobileNumberAgainstRollNumbers(rollNumbers);
	}
	
	// Added by Saikat 16/6/2018
	@Override
	public String saveSMSDetailsForAudit(SmsAudit smsAudit){
		return commonDao.saveSMSDetailsForAudit(smsAudit);
	}
}

