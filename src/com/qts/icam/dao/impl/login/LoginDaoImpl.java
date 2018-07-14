package com.qts.icam.dao.impl.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.qts.icam.dao.login.LoginDao;
import com.qts.icam.model.administrator.AccessType;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.backoffice.CalendarEvent;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.common.EmailDetails;
import com.qts.icam.model.common.Image;
import com.qts.icam.model.backoffice.MajorEvents;
import com.qts.icam.model.common.Notification;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SchoolDetails;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.login.LoginForm;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;


@Repository
public class LoginDaoImpl implements LoginDao{

	private final static Logger logger = Logger.getLogger(LoginDaoImpl.class);	
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	Utility utility;
	
	@Autowired
	EncryptDecrypt encryptDecrypt;
	
	@Override
	public Resource getAccessTypeAndRoleList(String userid) {
		logger.debug("Get getAccessTypeAndRoleList User Id DAOImpl");
		SqlSession session =sqlSessionFactory.openSession();
		Resource resource = null;
		try{
			resource = session.selectOne("getUserDetails",userid);
			
			Image profilePic = session.selectOne("selectEmployeeProfileImage", userid);
			if(null != profilePic && profilePic.getImagepath() != null && profilePic.getImagepath().length() !=0){
				resource.setImage(profilePic);
			}
			
			String address = session.selectOne("getUserAddress",userid);
			if(null!=address){
				resource.setStatus(address);
			}
			String lastVisitedOn =  session.selectOne("lastVisitedOn",userid);
			if(null!=lastVisitedOn){
				resource.setLastVisitedOn(lastVisitedOn);
			}
			if(resource!=null){
				List<AccessType> accessTypeList=session.selectList("getAccessTypeListFromDB",userid);
				if(accessTypeList!=null && accessTypeList.size()!=0){
					resource.setAccessTypeList(accessTypeList);
				}else{
					logger.info("No Access Type Role(s) Found For "+userid+" in Login DAO IMPL");
				}
				List<Role> roleList = session.selectList("getRoleListForUser",userid);
				if(roleList!=null && roleList.size()!=0){
					resource.setRoleList(roleList);
				}else{
				logger.info("No Role(s) Found For "+userid+" in Login DAO IMPL");
				}
			}
		}
		catch(Exception e){
			logger.error(e);
		}finally{
			session.close();
		}		
		return resource;	
	}


	@Override
	public AccessType getAccessTypeDetails(Resource resource) {
		logger.debug("Get getAccessTypeDetails User Id DAOImpl");
		SqlSession session =sqlSessionFactory.openSession();
		AccessType accessType = new  AccessType();
		try{
			List<Role> roleList = session.selectList("getAccessTypeDetails", resource);
			if(roleList!= null && roleList.size()!=0){
				for(Role role : roleList){
					List <Functionality> functionalityList =session.selectList("getFunctionalitiesForRole",role );
					if(functionalityList!= null && functionalityList.size()!=0){
						role.setFunctionalityList(functionalityList);						
					}
				}
				accessType.setRoleList(roleList);
				accessType.setAccessTypeName(roleList.get(0).getAccessTypeName());
			}
		}
		catch(Exception e){
			logger.error(e);
		}finally{
			session.close();
		}		
		return accessType;
	}


	@Override
	public Role getRoleDetails(Role role) {

		logger.debug("Get getRoleDetails User Id DAOImpl");
		SqlSession session =sqlSessionFactory.openSession();
		Role roleFromDB = new Role();
		try{
			List <Functionality> functionalityList =session.selectList("getFunctionalitiesForRole",role );
			if(functionalityList!= null && functionalityList.size()!=0){
				roleFromDB.setRoleName(role.getRoleName());
				roleFromDB.setModuleName(functionalityList.get(0).getModuleName());
				roleFromDB.setFunctionalityList(functionalityList);
			}
		}
		catch(Exception e){
			logger.error(e);
		}finally{
			session.close();
		}		
		return roleFromDB;
	}
	
	@Override
	public Notification getNewNotifications(String userId) {
		SqlSession session =sqlSessionFactory.openSession();
		Notification notification=new Notification();
		try{
			logger.info("Get getNewNotifications(String userId) LoginDAOImpl");
			Integer newNotifications =session.selectOne("selectNoOfNewNotificationsForAlert",userId );
			if(newNotifications!=null){
				notification.setNewNotification(newNotifications);
			}
			Integer countNewMessage  = session.selectOne("selectCountNewMessageForEmail", userId); 
			if(countNewMessage != null){
				notification.setNewEmailNotification(countNewMessage);
			}
			else{
				notification.setNewEmailNotification(0);
			}
			//List<NotificationMedium> notificationMediumList = session.selectList("selectAlerts",userId );
			//notification.setNotificationMediumList(notificationMediumList);
		}
		catch(Exception e){
			logger.error(e);
		}finally{
			session.close();
		}		
		return notification;
	}


	
	@Override
	public List<Role> getNewCalendarNotifications(CalendarEvent calendarEvent) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Role> roleFromDb = new ArrayList<Role>();
		@SuppressWarnings("unused")
		List<CalendarEvent> listEvent = new ArrayList<CalendarEvent>(); 	
		try{						
					List<CalendarEvent> eventFromDb = new ArrayList<CalendarEvent>();
					List<CalendarEvent> eventCountFromDb = new ArrayList<CalendarEvent>();
					
					Role role = new Role();
					role.setRoleName(calendarEvent.getCalendarEventBy());
					eventFromDb = session.selectList("fetchEventBasedOnUserId",role);
					eventCountFromDb = session.selectList("fetchEventCountBasedOnUserId",role);
					role.setCalendarEventList(eventFromDb);
					role.setCalendarEventCountList(eventCountFromDb);
					roleFromDb.add(role);
		
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return roleFromDb;
	}


	@Override
	public List<CalendarEvent> getEventForAllUserFromDb() {
		SqlSession session =sqlSessionFactory.openSession();
		List<CalendarEvent> eventFromDb = null;
		try{
			eventFromDb = session.selectList("fetchEventForAllUser");
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return eventFromDb;
	}


	@Override
	public List<CalendarEvent> getEventCountForAllUserFromDb() {
		SqlSession session =sqlSessionFactory.openSession();
		List<CalendarEvent> eventCountFromDb = null;
		try{
			eventCountFromDb = session.selectList("fetchEventCountForAllUser");
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return eventCountFromDb;
	}
	
	@Override
	public List<Functionality> getFunctionalityListForRole(Role role) {
		List<Functionality> functionalityList =null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			functionalityList = session.selectList("getFunctionalityListForModule",role);
		}catch(Exception e){
			
			logger.error("Exception in getFunctionalityListForRole(LoginForm login) method in LoginDaoImpl EXCEPTION: ",e);			
		}
		return functionalityList;
	}


	@Override
	public List<Functionality> getFunctionalityListForAccessType(
			AccessType accessType) {
		List<Functionality> functionalityList =null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			Role role=session.selectOne("getRoleForAccessType",accessType );
			if(role!=null)				
				functionalityList = session.selectList("getFunctionalityListForModule",role);				
		}catch(Exception e){
			
			logger.error("Exception in getFunctionalityListForAccessType(LoginForm login) method in LoginDaoImpl EXCEPTION: ",e);			
		}
		return functionalityList;
	}
	
	@Override
	public SchoolDetails getSchoolDetails() {
		logger.info("getSchoolDetails() method in LoginDaoImpl");
		SqlSession session =sqlSessionFactory.openSession();
		SchoolDetails schoolDetails=null;
		try{
			schoolDetails = session.selectOne("selectSchoolInformation");			
		}catch(Exception e){
			logger.error("Exception in getSchoolDetails() method in LoginDaoImpl EXCEPTION: ",e);
		}
	return schoolDetails;
	}

	@Override
	public String updateLoginDetails(LoginForm loginForm) {
		SqlSession session =sqlSessionFactory.openSession();
		String loginStatus="fail";
		try{
			logger.info("updateLoginDetails(LoginForm login) method in LoginDAOImpl");
			int status = session.update("updateLoginDetails", loginForm);
			if (status!=0) loginStatus="success";
			session.commit();
		}catch(Exception e){
			logger.error("Exception updateLoginDetails(LoginForm login) method in LoginDAOImpl", e);
			loginStatus="fail";
		}finally{
			session.close();
		}
		return loginStatus;
	}

	
	@Override
	public String insertLoginDetails(LoginForm login) {
		SqlSession session = sqlSessionFactory.openSession();
		String loginStatus = "inactive";
		@SuppressWarnings("unused")
		int status = 0;
		try{
			logger.info("insertLoginDetails(LoginForm login) method in LoginDAOImpl");
			login.setObjId(encryptDecrypt.getBase64EncodedID("LoginDAOImpl"));
			int countActiveStatus = session.selectOne("checkLoginStatus", login);
			if(countActiveStatus==0){
				session.insert("insertIntoLoginDetails", login);
			}else{
				status = session.update("updateLoginDetails", login);
				session.insert("insertIntoLoginDetails", login);
			}
			session.commit();
		}catch(Exception e){
			logger.error("Exception insertLoginDetails(LoginForm login) method in LoginDAOImpl", e);
		}finally{
			session.close();
		}
		return loginStatus;
	}


	@Override
	public String getPasswordStatus(LoginForm login) {
		String status="";
		SqlSession session =sqlSessionFactory.openSession();
		logger.info("getPasswordStatus(LoginForm login) method in LoginDaoImpl");
		try{
			status = session.selectOne("selectPasswordStatus", login);
		}catch(Exception e){
			logger.error("Exception in getPasswordStatus(LoginForm login) method in LoginDaoImpl for LDAP operation EXCEPTION: ",e);
		
		}
		return status;
	}

	

	@Override
	public Integer getMaxActiveUsers(LoginForm login) {
		Integer  maxActiveUsers=null;
		SqlSession session =sqlSessionFactory.openSession();
		logger.info("getMaxActiveUsers() method in LoginDaoImpl");
		try{
			login.setUserId(login.getUserId().trim().toLowerCase());
			//System.out.println("QWQQQ "+login.getUserId());
			String resourceTypeName = session.selectOne("selectResourceType", login);
			//System.out.println("QQQ "+resourceTypeName);
			if(resourceTypeName != null && resourceTypeName.length() != 0){				
				if(!resourceTypeName.equals("TEACHING STAFF")){
					session.update("updateLoginDetails", login);
					maxActiveUsers = session.selectOne("selectmaxActiveUsers");
					if(maxActiveUsers==null)
						maxActiveUsers=0;
				}else{
					maxActiveUsers = -1;
				}
			}
		}catch(Exception e){e.printStackTrace();
			logger.error("Exception in getMaxActiveUsers() method in LoginDaoImpl for LDAP operation EXCEPTION: ",e);
		}
		return maxActiveUsers;
	}
	
	@Override
	public int getMaxLoginActiveUsersForLicense() {
		SqlSession session =sqlSessionFactory.openSession();
		int maxLoginActiveUsers = 0;
		try{		
			Integer maxUsers = session.selectOne("selectMaxLoginActiveUsersForLicense");
			if(maxUsers==null){
				maxLoginActiveUsers=0;
			}else{
				maxLoginActiveUsers=maxUsers;
			}
				
		}catch (Exception e) {
			logger.error(e);e.printStackTrace();
		} finally {
			session.close();
		}
		return maxLoginActiveUsers;
	}
	
	@Override
	public List<CalendarEvent> getEventForSuperadminFromDb() {
		SqlSession session =sqlSessionFactory.openSession();
		List<CalendarEvent> roleFromDb = new ArrayList<CalendarEvent>();
		try{		
			roleFromDb = session.selectList("fetchAllEventsForSuperAdmin");

		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return roleFromDb;
	}


	@Override
	public List<CalendarEvent> getEventCountForSuperadminFromDb() {
		SqlSession session =sqlSessionFactory.openSession();
		List<CalendarEvent> countFromDb = new ArrayList<CalendarEvent>();
		try{		
			countFromDb = session.selectList("fetchAllEventsCountForSuperAdmin");

		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return countFromDb;
	}

	@Override
	public List<Exam> getListAssignedExamFromDb() throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Exam> examsFromDb = new ArrayList<Exam>();
		try{		
			examsFromDb = session.selectList("selectExamStartAndEndDateToView");

		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return examsFromDb;
	}


	@Override
	public List<Exam> getListAssignedExamCountFromDb() throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Exam> countFromDb = new ArrayList<Exam>();
		try{		
			countFromDb = session.selectList("selectListAssignedExamCountFromDb");

		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return countFromDb;
	}
	
	@Override
	public List<Notification> getNotificationList(String user_id) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Notification> notificationList = new ArrayList<Notification>();
		try{		
			notificationList = session.selectList("selectAllListFormNotification",user_id);

		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return notificationList;
	}


	@Override
	public int updateTaskNotification(List<Notification> listOfNotification) {
		SqlSession session =sqlSessionFactory.openSession();
		int status = 0;
		try{		
			for(Notification notification : listOfNotification){
				String notificationId = notification.getNotificationObjectId();
				//System.out.println("notificationId============="+notificationId);
				  status = session.update("updateTaskNotification", notificationId);
			 }
	
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		 
		return status;
	}

	@Override
	public List<MajorEvents> getMajorEvents(){
		SqlSession session =sqlSessionFactory.openSession();
		List<MajorEvents> majorEventsList = new ArrayList<MajorEvents>();
		try{		
			majorEventsList = session.selectList("selectMajorEvents");

		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return majorEventsList;
	}

	@Override
	public List<Notification> getNotificationListForTask(String user_id) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Notification> notificationList = new ArrayList<Notification>();
		try{		
			notificationList = session.selectList("selectNotificationListFormTaskDetails",user_id);

		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return notificationList;
	}


	@Override
	public int updateTaskDetailsForNotification(List<Notification> listOfNotificationForTask) {
		SqlSession session =sqlSessionFactory.openSession();
		int status = 0;
		try{		
			for(Notification notification : listOfNotificationForTask){
				String notificationId = notification.getNotificationObjectId();
				//System.out.println("notificationId=================="+notificationId);
				  status = session.update("updateTaskDetailsForNotification", notificationId);
			 }
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		 
		return status;
	}	
	
	@Override
	public List<Notification> getNotificationListCount(String user_id) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Notification> notificationList = new ArrayList<Notification>();
		
		try{		
			notificationList = session.selectList("selectNotificationListFromNotification",user_id);

		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return notificationList;
	}


	@Override
	public List<Notification> getNotificationListForTaskCount(String user_id) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Notification> notificationList = new ArrayList<Notification>();
		try{		
			notificationList = session.selectList("selectNotificationListFormTaskDetailsForCount",user_id);

		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return notificationList;
	}	
	
	
	@Override
	public List<Notification> getListOfAlerts(String userId) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Notification> listAlerts = new ArrayList<Notification>();
		try{
			listAlerts = session.selectList("listAlerts", userId);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("error in method getListOfAlerts in logindaoimpl");
		}
		finally{
			session.close();
		}
		return listAlerts;
	}


	@Override
	public int updateStatusOfAlert(List<Notification> listOfAlerts) {
		SqlSession session = sqlSessionFactory.openSession();
		int updateStatus = 0;
		try{
			for(Notification alerts : listOfAlerts){
				String alertCode = alerts.getNotificationCode();
				updateStatus = session.update("updateStatusOfAlert", alertCode);
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			logger.error("error in upateStatusOfAlert method of loginDaoImpl");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return updateStatus;
	}


	@Override
	public List<Notification> getUnreadAlertList(String user_id) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Notification> unreadAlertList = new ArrayList<Notification>();
		
		try{
			unreadAlertList = session.selectList("getUnreadAlertList",user_id);

		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return unreadAlertList;
	}


	@Override
	public List<Notification> getListOfMails(String userId) {
		SqlSession session= sqlSessionFactory.openSession();
		List<Notification> listOfMails = new ArrayList<Notification>();
		try{
			listOfMails = session.selectList("getListOfMails",userId);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("error in getListOfMails method of loginDaoImpl");
		}finally{
			session.close();
		}
		return listOfMails;
	}


	@Override
	public int updateStatusOfMail(List<Notification> listOfMails) {
		SqlSession session = sqlSessionFactory.openSession();
		int updateStatus = 0;
		try{
			for(Notification alerts : listOfMails){
				String alertCode = alerts.getNotificationCode();
				updateStatus = session.update("updateStatusOfMail", alertCode);
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			logger.error("error in updateStatusOfMail method of loginDaoImpl");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return updateStatus;
	}

	//anup.roy 12072017 for get unreadEmailList in Dashboard page
	@Override
	public List<EmailDetails> getUnreadMailList(String user_id) {
		SqlSession session =sqlSessionFactory.openSession();
		List<EmailDetails> unreadMailList = null;		
		try{
			unreadMailList = session.selectList("getUnreadMails", user_id);
		}catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return unreadMailList;
	}


	@Override
	public List<Notification> getAllNotificationListFromAlert(String user_id) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Notification> listAlerts = new ArrayList<Notification>();
		try{
			listAlerts = session.selectList("listAllUnreadNotificationAlerts", user_id);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("error in method getListOfAlerts in logindaoimpl");
		}
		finally{
			session.close();
		}
		return listAlerts;
	}


	@Override
	public int updateAlertForNotification(List<Notification> allnotificationAlertList) {
		SqlSession session =sqlSessionFactory.openSession();
		int status = 0;
		try{		
			for(Notification notification : allnotificationAlertList){
				String notificationId = notification.getNotificationCode();
				//System.out.println("notificationId=================="+notificationId);
				  status = session.update("updateAlertForNotification", notificationId);
			 }
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		 
		return status;
	}


	@Override
	public List<Notification> getNotificationListFromAlert(String user_id) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Notification> listAlerts = new ArrayList<Notification>();
		try{
			listAlerts = session.selectList("listAllNotificationAlerts", user_id);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("error in method getListOfAlerts in logindaoimpl");
		}
		finally{
			session.close();
		}
		return listAlerts;
	}


	@Override
	public int updateNotification(List<Notification> listOfNotification) {
			SqlSession session =sqlSessionFactory.openSession();
			int status = 0;
			try{		
				for(Notification notification : listOfNotification){
					int notificationId = notification.getNotificationId();
					//System.out.println("notificationId============="+notificationId);
					  status = session.update("updateForNotification", notificationId);
				 }
		
			}catch (Exception e) {
				logger.error(e);
			} finally {
				session.close();
			}
			 
			return status;
		
	}


	@Override
	public List<Task> getTaskDetailsListForAUser(String userId) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Task> taskList = null;
		try{
			taskList = session.selectList("getTaskDetailsListForAUser",userId);
			//System.out.println("notificationList==="+taskList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return taskList;
	}
	
}
