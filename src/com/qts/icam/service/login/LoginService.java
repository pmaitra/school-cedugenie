package com.qts.icam.service.login;

import java.util.List;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXStatus;
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
import com.qts.icam.utility.customexception.CustomException;

public interface LoginService {

	Resource getAccessTypeAndRoleList(String userid);

	AccessType getAccessTypeDetails(Resource resource);

	Role getRoleDetails(Role role);

	Notification getNewNotifications(String trim);

	List<Role> getEventUserBased(CalendarEvent calendarEvent);

	List<CalendarEvent> getEventForAllUserFromDb();

	List<CalendarEvent> getEventCountForAllUserFromDb();

	boolean authenticate(LoginForm login);

	List<Functionality> getFunctionalityListForRole(Role role);

	List<Functionality> getFunctionalityListForAccessType(AccessType accessType);

	SchoolDetails getSchoolDetails();

	String updateLoginDetails(LoginForm login);	

	String insertLoginDetails(LoginForm login);

	String getPasswordStatus(LoginForm login);

	Integer getMaxActiveUsers(LoginForm loginForm);
	
	List<CalendarEvent> getEventForSuperadminFromDb();

	List<CalendarEvent> getEventCountForSuperadminFromDb();
	
	List<Exam> getListAssignedExamFromDb() throws CustomException ;

	List<Exam> getListAssignedExamCountFromDb() throws CustomException;

	String updatePassword(LoginForm login);
	
	public int getMaxLoginActiveUsersForLicense();
	
	public List<Notification> getNotificationList(String user_id)throws CustomException;

	int updateTaskNotification(List<Notification> listOfNotification);

	List<MajorEvents> getMajorEvents();

	ServerConfiguration getServerConfigurationDB();

	public List<Notification> getNotificationListForTask(String user_id);

	public int updateTaskDetailsForNotification(List<Notification> listOfNotificationForTask);
	
	public List<Notification> getNotificationListCount(String user_id);

	public  List<Notification> getNotificationListForTaskCount(String user_id);
	
	public List<Notification> getlistOfAlerts(String userId);

	public int updateStatusOfAlert(List<Notification> listOfAlerts);

	public List<Notification> getUnreadAlertList(String user_id);

	public List<Notification> getlistOfMails(String userId);

	public int updateStatusOfMail(List<Notification> listOfMails);

	public List<EmailDetails> getUnreadMailList(String user_id);

	public List<Notification> getAllNotificationListFromAlert(String user_id);

	public int updateAlertForNotification(List<Notification> allnotificationAlertList);

	public List<Notification> getNotificationListFromAlert(String user_id);

	public int updateNotification(List<Notification> listOfNotification);
	
	/********************************/

	public List<Course> getCourseListForAStudent(String userId);
	
	//Added By Naimisha 16042018

	public List<Task> getTaskDetailsListForAUser(String userId);
}
