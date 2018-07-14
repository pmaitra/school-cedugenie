package com.qts.icam.service.impl.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;
import java.io.InputStream;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qts.icam.service.login.LoginService;
import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXStatus;
/*import com.itextpdf.text.pdf.codec.Base64.InputStream;*/
import com.qts.icam.dao.common.CommonDao;
import com.qts.icam.dao.login.LoginDao;
import com.qts.icam.model.administrator.AccessType;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.administrator.ServerConfiguration;
import com.qts.icam.model.backoffice.CalendarEvent;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.EmailDetails;
import com.qts.icam.model.backoffice.MajorEvents;
import com.qts.icam.model.common.Notification;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SchoolDetails;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.login.LoginForm;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;
import com.qts.icam.utility.ldap.Ldap;
import com.qts.icam.utility.mailservice.EmailSender;
import com.qts.icam.utility.messageservice.MessageSender;



@Service
public class LoginServiceImpl implements LoginService{

	public static Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	LoginDao loginDao = null;

	@Autowired
	CommonDao commonDao;
	
	@Autowired
	Utility utility;
	
	@Autowired
	Ldap ldap;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
	MessageSender messageSender;
	
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public Resource getAccessTypeAndRoleList(String userid) {
		Resource resource = loginDao.getAccessTypeAndRoleList(userid);
		return resource;
	}

	@Override
	public AccessType getAccessTypeDetails(Resource resource) {
		AccessType accessTypeFromDB = loginDao.getAccessTypeDetails(resource);
		return accessTypeFromDB;
	}

	@Override
	public Role getRoleDetails(Role role) {
		Role roleFromDB= loginDao.getRoleDetails(role);
		return roleFromDB;
	}

	@Override
	public Notification getNewNotifications(String userId) {
		return loginDao.getNewNotifications(userId);
	}
	
	@Override
	public List<Role> getEventUserBased(CalendarEvent calendarEvent) {
		List<Role> event = loginDao.getNewCalendarNotifications(calendarEvent);
		return event;
	}

	@Override
	public List<CalendarEvent> getEventForAllUserFromDb() {
		List<CalendarEvent> event = loginDao.getEventForAllUserFromDb();
		return event;
	}

	@Override
	public List<CalendarEvent> getEventCountForAllUserFromDb() {
		List<CalendarEvent> eventCount = loginDao.getEventCountForAllUserFromDb();
		return eventCount;
	}
		
	@Override
	public List<Functionality> getFunctionalityListForRole(Role role) {
		List<Functionality> functionalityListForRole = loginDao.getFunctionalityListForRole(role);
		return functionalityListForRole;
	}

	@Override
	public List<Functionality> getFunctionalityListForAccessType(
			AccessType accessType) {
		List<Functionality> functionalityListForAccessType = loginDao.getFunctionalityListForAccessType(accessType);
		return functionalityListForAccessType;
	}

	@Override
	public SchoolDetails getSchoolDetails() {
		SchoolDetails schoolDetails = loginDao.getSchoolDetails();
		return schoolDetails;
	}
	
	@Override
	public boolean authenticate(LoginForm login){
		boolean authenticateStatus = ldap.authenticate(login);
		return authenticateStatus;
	}
	
	
	@Override
	public String updateLoginDetails(LoginForm loginForm) {
		return	loginDao.updateLoginDetails(loginForm);
	}

		
	@Override
	public String getPasswordStatus(LoginForm login) {
		return loginDao.getPasswordStatus(login);
	}



@Override
	public String insertLoginDetails(LoginForm login) {
		return loginDao.insertLoginDetails(login);
	}

	@Override
	public Integer getMaxActiveUsers(LoginForm login) {
		return loginDao.getMaxActiveUsers(login);
	}
	
	@Override
	public List<CalendarEvent> getEventForSuperadminFromDb() {
		return loginDao.getEventForSuperadminFromDb();
	}
	
	@Override
	public List<CalendarEvent> getEventCountForSuperadminFromDb() {
		return loginDao.getEventCountForSuperadminFromDb();
	}

	@Override
	public List<Exam> getListAssignedExamFromDb() throws CustomException {
		return loginDao.getListAssignedExamFromDb();
	}

	@Override
	public List<Exam> getListAssignedExamCountFromDb() throws CustomException {
		return loginDao.getListAssignedExamCountFromDb();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String updatePassword(LoginForm login) {
		String status = "fail";
		try{
			int updateStatus = commonDao.updateAndInsertUserPasswordStatus(login);
			if(updateStatus != 0){
				status = utility.updatePassword(login);
				if("success".equals(status)){
					try{
						String emailSubjectTemplate = servletContext.getInitParameter("automailtemplatesubjectpath");
						String emailBodyTemplate = servletContext.getInitParameter("automailtemplatebodypath");
						Resource senderDetails = commonDao.getResourceDetails(login.getUpdatedBy());
						Resource receiverDetails = commonDao.getResourceDetails(login.getUserId());
						if(receiverDetails != null && senderDetails != null){
							EmailDetails emailDetails = new EmailDetails();
							emailDetails.setEmailDetailsSubject("Login Password Changed!!!");
							emailDetails.setEmailDetailsDesc("Hi! "+login.getUserId()+"\nYour password has been change successfully. Now you can login with \nUser Id: "+login.getUserId()+"\nPassword: "+login.getPassword());
							emailDetails.setEmailDetailsReceiver(login.getUserId());
							emailDetails.setStatus("automail");
							emailSender.sendMail(login.getUpdatedBy(), login.getUserId(),emailDetails,emailSubjectTemplate,emailBodyTemplate);										
							//messageSender.sendMessage(receiverDetails.getMobile(), emailDetails.getEmailDetailsDesc());
						}
					}catch(MailSendException mse){
						logger.error("Mail Exception in updatePassword() in BackOfficeServiceImpl", mse);
					}
				}
			}
		}catch(Exception e){
			logger.error("Exception in updatePassword() in LoginServiceImpl", e);e.printStackTrace();
			status = "fail";
		}
		System.out.println("SSSSSSSSSSSS "+status);
		return status;
	}
	
	@Override
	public int getMaxLoginActiveUsersForLicense() {
		return loginDao.getMaxLoginActiveUsersForLicense();
	}
	
	@Override
	public List<Notification> getNotificationList(String user_id)
			throws CustomException {
		return loginDao.getNotificationList(user_id);
	}


	@Override
	public int updateTaskNotification(List<Notification> listOfNotification) {
		// TODO Auto-generated method stub
		return loginDao.updateTaskNotification(listOfNotification);
	}

	@Override
	public List<MajorEvents> getMajorEvents(){
		return loginDao.getMajorEvents();
	}
	
	@Override
	public ServerConfiguration getServerConfigurationDB() {
		Properties prop = new Properties();
		ServerConfiguration serverConfiguration = new ServerConfiguration();
		/*String propertyFile = servletContext.getInitParameter("icamfolderpath")
				+ "conf/configuration.properties";*/
		//String propertyFile = "configuration.properties";
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("configuration.properties");
			//InputStream input = getServletContext().getResourceAsStream("/WEB-INF/configuration.properties");
			//Properties prop = new Properties();
			prop.load(input);
		
			//File propertiesFile = new File(propertyFile);
			if (!prop.isEmpty()) {
				//prop.load(new FileInputStream(propertyFile));
				//logger.info("Loaded DB Configuration in serverConfiguration : "+ propertyFile);
				// Reading Properties file and Populating Ldap model						
				String dbUrl = ((prop.getProperty("jdbc.url") != null) ? prop.getProperty("jdbc.url") : "");				
				String[] parts = dbUrl.split("//");
				String[] urlWithDB = parts[1].split("/");
				String ip = urlWithDB[0];				
				
				serverConfiguration.setJdbcURL(ip);
				serverConfiguration.setJdbcUserName((encryptDecrypt.decrypt(prop.getProperty("jdbc.username")) != null) ? encryptDecrypt.decrypt(prop
						.getProperty("jdbc.username")) : "");				
				serverConfiguration.setJdbcPassword((encryptDecrypt.decrypt(prop.getProperty("jdbc.password")) != null) ? encryptDecrypt.decrypt(prop
						.getProperty("jdbc.password")) : "");
				serverConfiguration.setJdbcAcquireIncrement((prop.getProperty("jdbc.acquireIncrement") != null) ? prop
						.getProperty("jdbc.acquireIncrement") : "");				
				serverConfiguration.setJdbcDialect((prop.getProperty("jdbc.dialect") != null) ? prop
						.getProperty("jdbc.dialect") : "");				
				serverConfiguration.setJdbcDriverClassName((prop.getProperty("jdbc.driverClassName") != null) ? prop
						.getProperty("jdbc.driverClassName") : "");	
				serverConfiguration.setJdbcMaxActive((prop.getProperty("jdbc.maxActive") != null) ? prop
						.getProperty("jdbc.maxActive") : "");				
				serverConfiguration.setJdbcMaxIdleTime((prop.getProperty("jdbc.maxIdleTime") != null) ? prop
						.getProperty("jdbc.maxIdleTime") : "");	
				serverConfiguration.setJdbcMaxStatement((prop.getProperty("jdbc.maxStatements") != null) ? prop
						.getProperty("jdbc.maxStatements") : "");
				serverConfiguration.setJdbcPort((prop.getProperty("jdbc.port") != null) ? prop
						.getProperty("jdbc.port") : "");				
				serverConfiguration.setJdbcStatementCacheNumDeferredCloseThread((prop.getProperty("jdbc.statementCacheNumDeferredCloseThreads") != null) ? prop
						.getProperty("jdbc.statementCacheNumDeferredCloseThreads") : "");				
				serverConfiguration.setJdbcInitialSize((prop.getProperty("jdbc.initialSize") != null) ? prop
						.getProperty("jdbc.initialSize") : "");
				serverConfiguration.setJdbcDatabaseName((prop.getProperty("jdbc.databaseName") != null) ? prop
						.getProperty("jdbc.databaseName") : "");
			} else {
				logger.error("Configuration file doesn't exist ");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("serverConfigurationDB() In AdministratorController.java: Exception",e);
		}
		return serverConfiguration;
	}
	
	@Override
	public List<Notification> getNotificationListForTask(String user_id) {
		// TODO Auto-generated method stub
		return loginDao.getNotificationListForTask(user_id);
	}

	@Override
	public int updateTaskDetailsForNotification(
			List<Notification> listOfNotificationForTask) {
		// TODO Auto-generated method stub
		return loginDao.updateTaskDetailsForNotification(listOfNotificationForTask);
	}
	
	@Override
	public List<Notification> getNotificationListCount(String user_id) {
		return loginDao.getNotificationListCount(user_id);
	}

	@Override
	public List<Notification> getNotificationListForTaskCount(String user_id) {
		return loginDao.getNotificationListForTaskCount(user_id);
	}
	
	@Override
	public List<Notification> getlistOfAlerts(String userId) {
		return loginDao.getListOfAlerts(userId);
	}

	@Override
	public int updateStatusOfAlert(List<Notification> listOfAlerts) {
		return loginDao.updateStatusOfAlert(listOfAlerts);
	}

	@Override
	public List<Notification> getUnreadAlertList(String user_id) {
		return loginDao.getUnreadAlertList(user_id);
	}

	@Override
	public List<Notification> getlistOfMails(String userId) {
		return loginDao.getListOfMails(userId);
	}

	@Override
	public int updateStatusOfMail(List<Notification> listOfMails) {
		return loginDao.updateStatusOfMail(listOfMails);
	}

	@Override
	public List<EmailDetails> getUnreadMailList(String user_id) {
		return loginDao.getUnreadMailList(user_id);
	}
/*Naimisha*/
	@Override
	public List<Notification> getAllNotificationListFromAlert(String user_id) {
		
		return loginDao.getAllNotificationListFromAlert(user_id);
	}

	@Override
	public int updateAlertForNotification(List<Notification> allnotificationAlertList) {
		return loginDao.updateAlertForNotification(allnotificationAlertList);
	}

	@Override
	public List<Notification> getNotificationListFromAlert(String user_id) {
		// TODO Auto-generated method stub
		return loginDao.getNotificationListFromAlert(user_id);
	}

	@Override
	public int updateNotification(List<Notification> listOfNotification) {
		return loginDao.updateNotification(listOfNotification);
	}

	
	/**************Added By Naimisha 22042017************/
	@Override
	public List<Course> getCourseListForAStudent(String userId) {
		return commonDao.getCourseListForAStudent(userId);
	}

	@Override
	public List<Task> getTaskDetailsListForAUser(String userId) {
		return loginDao.getTaskDetailsListForAUser(userId);
	}
	
	
}
