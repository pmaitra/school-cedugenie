package com.qts.icam.controller.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.ehcache.store.chm.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.google.gson.Gson;
import com.qts.icam.model.administrator.AccessType;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.administrator.ServerConfiguration;
import com.qts.icam.model.backoffice.CalendarEvent;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.EmailDetails;
import com.qts.icam.model.common.Image;
import com.qts.icam.model.backoffice.MajorEvents;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.Notification;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SchoolDetails;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.login.LoginForm;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.login.LoginService;
import com.qts.icam.service.survey.SurveyService;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.mailservice.EmailReceiver;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

@Controller 
@SessionAttributes("sessionObject")
public class LoginController {

	@Autowired
	LoginService loginService = null;
	
	@Autowired
	CommonService commonService = null;
	
	@Autowired
	SurveyService surveyService;
	
	@Autowired
	EmailReceiver emailReceiver;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	FileUploadDownload fileUploadDownload;
	
	@Value("${max.loginActiveUsers}")
	private String maxLoginActiveUsers;
	
	
	@Value("${emailAttachments.path}")
	private String emailAttachmentsPath;
	
	@Value("${LOGIN_ERROR}")
	private String LOGIN_ERROR;
	
	@Value("${ldap.organization}")
	private String ldapOrganization;
	
	@Value("${ldap.service_username}")
	private String ldapServiceUserName;
	
	@Value("${ldap.service_password}")
	private String ldapServicePassword;

	@Value("${ldap.URI.validateLogin}")
	private String validateLoginURL;
	
	public static Logger logger = Logger.getLogger(LoginController.class);
	
	public static String MODULE_NAME_TO_DISPLAY_ITS_CHARTS = null;
	public static String ROLE_NAME_TO_DISPLAY_ITS_CHARTS = null;
	
	
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String serveLoginPage(HttpServletRequest request,
								 HttpServletResponse response, ModelMap model,
								 LoginForm login,
								 @RequestParam(value = "status", required = false) String status) {
		String strView=null;
		try {
			if(model.containsAttribute("sessionObject")) model.remove("sessionObject");
			HttpSession session = request.getSession(false);
			if(session!=null)session.invalidate();
			SchoolDetails schoolDetails = loginService.getSchoolDetails();
			model.addAttribute("SchoolDetails", schoolDetails);
			List<NoticeBoard> noticelist;
			noticelist = commonService.getNoticeList();
			model.addAttribute("noticelist", noticelist);	
			
			if(null!=status){			
				if(null=="sessionExpired"){
					login.setMessage("Session Expired");
				}
			}
			model.addAttribute("loginForm", login);
		} catch (CustomException e) {
			logger.error("",e);
		} catch (Exception e) {
			logger.error("",e);
		}
		strView="common/loginForm";		
		return strView;
	}
	
	/**
	 * For non authentication of LDAP*/
	
	@RequestMapping(value ="/home")
	public String home(HttpServletRequest request,
			HttpServletResponse response, LoginForm login, ModelMap model) {
			String strView = null;
			List<Notification>notificationList =null;
			List<Notification>taskNotificationList = null;
			List<String> allNoticeList = new ArrayList<String>();
			List<Notification> unreadAlertList = null;
			List<EmailDetails> unreadEmailList = null;
			int emailCount = 0;
		try{
			if(!model.containsAttribute("sessionObject")){
				login.setUserId(login.getUserId().trim().toLowerCase());
				
				//PRAD JUNE 14 2018 OPEN the validateLogin() for LDAP Authentication
				login.setOrganization(ldapOrganization);
				login.setServiceUsername(ldapServiceUserName);
				login.setServicePassword(ldapServicePassword);
				
				boolean authenticateStatus = validateLogin(request, response, login, model);
				logger.info("STATUS : Login Authenticate for "+login.getUserId()+" :::::::::::::::: "+authenticateStatus);
				
				//Added By Naimisha 24042017
				Resource resource = loginService.getAccessTypeAndRoleList(login.getUserId());
				if(resource.getResourceTypeName().equalsIgnoreCase("STUDENT")){
					List<Course>courseList = loginService.getCourseListForAStudent(login.getUserId());
					List<Course>courseListNew = new ArrayList<Course>();
					if(null != courseList && courseList.size()!= 0){
						//for(Course course : courseList){
							//int duration = Integer.parseInt((course.getDesc()));
							//if(duration>=6){
								//courseListNew.add(course);
								if(authenticateStatus){
									Integer maxActiveUsers = checkMaxActiveUsers(login);
									if(maxActiveUsers != null){
										if((maxActiveUsers < loginService.getMaxLoginActiveUsersForLicense() && maxActiveUsers >= 0) || (maxActiveUsers == -1)){
											strView = getResourceInfo(request,response,login,model);
											notificationList = getListForNotification(request,response,login,model);
											//System.out.println("notificationList size===="+notificationList.size());
											//taskNotificationList = getTaskNotificationList(request,response,login,model);  ???//for notification of task
											unreadAlertList = getUnreadAlertList(request,response,login,model);
											//int taskCount = notificationList.size() + taskNotificationList.size();
											int taskCount = notificationList.size();
											int alertCount = unreadAlertList.size();
											unreadEmailList = getUnreadEmailList(request,response,login,model);
											if(null!= unreadEmailList) {
												emailCount = unreadEmailList.size();
											}
											Notification notification = loginService.getNewNotifications(login.getUserId().trim());	
											taskCount = taskCount + notification.getNewNotification();
											if(notificationList != null){
												for(int i=0;i<notificationList.size();i++){
													allNoticeList.add(notificationList.get(i).getNotificationDesc());
												}
											}
											if(taskNotificationList != null){
												for(int i=0;i<taskNotificationList.size();i++){
													allNoticeList.add(taskNotificationList.get(i).getNotificationDesc());
												}
											}
											model.addAttribute("allNoticeList", allNoticeList);
											model.addAttribute("taskCount",taskCount);
											model.addAttribute("alertCount",alertCount);
											model.addAttribute("emailCount",emailCount);
										}else{
											login.setMessage("Login exceeds maximum allowed users");
											strView = serveLoginPage(request,response, model,login,null);
										}
								   }else{
										login.setMessage("Max Limit Reached");
										strView = serveLoginPage(request,response, model,login,null);
									}
							}else{
									login.setMessage("Login authentication failed!!");
									strView = serveLoginPage(request,response, model,login,null);
								}
							
							}else{
								login.setMessage("Student has no program with duration more than 6 months!!");
								strView = serveLoginPage(request,response, model,login,null);	
					//	}
					}
				}else{
					if(authenticateStatus){
						Integer maxActiveUsers = checkMaxActiveUsers(login);
						if(maxActiveUsers != null){
							if((maxActiveUsers < loginService.getMaxLoginActiveUsersForLicense() && maxActiveUsers >= 0) || (maxActiveUsers == -1)){
								strView = getResourceInfo(request,response,login,model);
								notificationList = getListForNotification(request,response,login,model);
								//System.out.println("notificationList size===="+notificationList.size());
								//taskNotificationList = getTaskNotificationList(request,response,login,model);  ???//for notification of task
								unreadAlertList = getUnreadAlertList(request,response,login,model);
								//int taskCount = notificationList.size() + taskNotificationList.size();
								int taskCount = notificationList.size();
								int alertCount = unreadAlertList.size();
								unreadEmailList = getUnreadEmailList(request,response,login,model);
								if(null!= unreadEmailList) {
									emailCount = unreadEmailList.size();
								}
								Notification notification = loginService.getNewNotifications(login.getUserId().trim());	
								taskCount = taskCount + notification.getNewNotification();
								if(notificationList != null){
									for(int i=0;i<notificationList.size();i++){
										allNoticeList.add(notificationList.get(i).getNotificationDesc());
									}
								}
								if(taskNotificationList != null){
									for(int i=0;i<taskNotificationList.size();i++){
										allNoticeList.add(taskNotificationList.get(i).getNotificationDesc());
									}
								}
								model.addAttribute("allNoticeList", allNoticeList);
								model.addAttribute("taskCount",taskCount);
								model.addAttribute("alertCount",alertCount);
								model.addAttribute("emailCount",emailCount);
							}else{
								login.setMessage("Login exceeds maximum allowed users");
								strView = serveLoginPage(request,response, model,login,null);
							}
					   }else{
							login.setMessage("Max Limit Reached");
							strView = serveLoginPage(request,response, model,login,null);
						}
				}else{
						login.setMessage("Login authentication failed!!");
						strView = serveLoginPage(request,response, model,login,null);
					}
				}
				
				
				//ENd By Naimisha 24042017
			
			}else{
				strView = getResourceInfo(request,response,login,model);
			}
		}catch(Exception e){
			logger.error("Login Failed for "+login.getUserId()+"!!",e);
			login.setMessage("Login Failed!!");
			strView = serveLoginPage(request, response, model, login, null);				
		}
		return strView;
	}
	
	
	/**
	 * FOR LDAP authentication*/
	
	/*@RequestMapping(value ="/home")
	public String home(HttpServletRequest request,
			HttpServletResponse response, LoginForm login, ModelMap model) {
			String strView = null;
			List<Notification>notificationList =null;
			List<Notification>taskNotificationList = null;
			List<String> allNoticeList = new ArrayList<String>();
			List<Notification> unreadAlertList = null;
			List<Notification> unreadEmailList = null;
		try{
			if(!model.containsAttribute("sessionObject")){
				login.setUserId(login.getUserId().trim().toLowerCase());
				System.out.println("userId"+login.getUserId()+"\tpassword:"+login.getPassword());
				boolean authenticateStatus = validateLogin(request, response, login, model);
				logger.info("STATUS : Login Authenticate for "+login.getUserId()+" :::::::::::::::: "+authenticateStatus);
				
				Resource resource = loginService.getAccessTypeAndRoleList(login.getUserId());
				if(resource.getResourceTypeName().equalsIgnoreCase("STUDENT")){
					List<Course>courseList = loginService.getCourseListForAStudent(login.getUserId());
					List<Course>courseListNew = new ArrayList<Course>();
					if(null != courseList && courseList.size()!= 0){
						if(authenticateStatus == true){
							Integer maxActiveUsers = checkMaxActiveUsers(login);
							if(maxActiveUsers != null){
								if((maxActiveUsers < loginService.getMaxLoginActiveUsersForLicense() && maxActiveUsers >= 0) || (maxActiveUsers == -1)){
									strView = getResourceInfo(request,response,login,model);
									notificationList = getListForNotification(request,response,login,model);
									unreadAlertList = getUnreadAlertList(request,response,login,model);
									int taskCount = notificationList.size();
									int alertCount = unreadAlertList.size();
									unreadEmailList = getUnreadEmailList(request,response,login,model);
									int emailCount = unreadEmailList.size();
									Notification notification = loginService.getNewNotifications(login.getUserId().trim());	
									taskCount = taskCount + notification.getNewNotification();
									if(notificationList != null){
										for(int i=0;i<notificationList.size();i++){
											allNoticeList.add(notificationList.get(i).getNotificationDesc());
										}
									}
									model.addAttribute("allNoticeList", allNoticeList);
									model.addAttribute("taskCount",taskCount);
									model.addAttribute("alertCount",alertCount);
									model.addAttribute("emailCount",emailCount);
								}else{
									login.setMessage("Login exceeds maximum allowed users");
									strView = serveLoginPage(request,response, model,login,null);
								}
						   }else{
								login.setMessage("Max Limit Reached");
								strView = serveLoginPage(request,response, model,login,null);
							}
						}else{
								login.setMessage("Login authentication failed!!");
								strView = serveLoginPage(request,response, model,login,null);
							}
					}else{
						login.setMessage("Student has no program with duration more than 6 months!!");
						strView = serveLoginPage(request,response, model,login,null);
					}
				}else{
					if(authenticateStatus == true){
						Integer maxActiveUsers = checkMaxActiveUsers(login);
						if(maxActiveUsers != null){
							if((maxActiveUsers < loginService.getMaxLoginActiveUsersForLicense() && maxActiveUsers >= 0) || (maxActiveUsers == -1)){
								strView = getResourceInfo(request,response,login,model);
								notificationList = getListForNotification(request,response,login,model);
								unreadAlertList = getUnreadAlertList(request,response,login,model);
								int taskCount = notificationList.size();
								int alertCount = unreadAlertList.size();
								unreadEmailList = getUnreadEmailList(request,response,login,model);
								int emailCount = unreadEmailList.size();
								Notification notification = loginService.getNewNotifications(login.getUserId().trim());	
								taskCount = taskCount + notification.getNewNotification();
								if(notificationList != null){
									for(int i=0;i<notificationList.size();i++){
										allNoticeList.add(notificationList.get(i).getNotificationDesc());
									}
								}
								model.addAttribute("allNoticeList", allNoticeList);
								model.addAttribute("taskCount",taskCount);
								model.addAttribute("alertCount",alertCount);
								model.addAttribute("emailCount",emailCount);
							}else{
								login.setMessage("Login exceeds maximum allowed users");
								strView = serveLoginPage(request,response, model,login,null);
							}
					   }else{
							login.setMessage("Max Limit Reached");
							strView = serveLoginPage(request,response, model,login,null);
						}
				}else{
						login.setMessage("Login authentication failed!!");
						strView = serveLoginPage(request,response, model,login,null);
					}
				}	
			}else{
				strView = getResourceInfo(request,response,login,model);
			}
		}catch(Exception e){
			logger.error("Login Failed for "+login.getUserId()+"!!",e);
			login.setMessage("Login Failed!!");
			strView = serveLoginPage(request, response, model, login, null);				
		}
		return strView;
	}*/

	
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
			logger.info("Opening Dashboard Page");
			
			//System.out.println("Current Role Or Access"+sessionObject.getCurrentRoleOrAccess());
			//System.out.println("Current UserId"+sessionObject.getUserId());
			
			/***************************Start Report*******************************/
			
			
			
			/***************************End Report*******************************/
			
			List<MajorEvents> majorEventList = loginService.getMajorEvents();
			model.addAttribute("majorEventList",majorEventList);
			
			Resource resource = loginService.getAccessTypeAndRoleList(sessionObject.getUserId());
			if(null != resource.getImage()){
        		Image i = new Image();
        		i.setImagepath(fileUploadDownload.getBase64Image(resource.getImage().getImagepath()));
        		resource.setImage(i);
	        }
			model.addAttribute("resourceDetails",resource);
			
			/***************************Start Calendar*******************************/
						
			DHXPlanner p = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
			
			Calendar now = Calendar.getInstance();
			 
            p.setInitialDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE));
            p.setWidth(900);
            p.load("/icam/myevents.html?UsrId="+sessionObject.getUserId(), DHXDataFormat.JSON);
            p.data.dataprocessor.setURL("/icam/myevents.html?UsrId="+sessionObject.getUserId());
            
            ModelAndView mnv = new ModelAndView("common/dashboard");
            
            try {
				mnv.addObject("body", p.render());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            return mnv;
            
            /***************************End Calendar*******************************/
			//strView = "common/dashboard";
		//return new ModelAndView(strView);
	}
	
	@RequestMapping(value ="/myevents")
	@ResponseBody public String events(HttpServletRequest request, @RequestParam("UsrId") String usrId) {
		ServerConfiguration serverConfiguration = loginService.getServerConfigurationDB();
		String urlAsParameter = "jdbc:postgresql://"+serverConfiguration.getJdbcURL()+":"+serverConfiguration.getJdbcPort()+"/"+serverConfiguration.getJdbcDatabaseName();
		
		String userName = serverConfiguration.getJdbcUserName();
		String passwordAsParameter = serverConfiguration.getJdbcPassword();
		String driverClassName = serverConfiguration.getJdbcDriverClassName();
		CustomEventsManager evs = new CustomEventsManager(request, usrId, urlAsParameter, userName, passwordAsParameter, driverClassName);
        return evs.run();
    }
	
	
	
		
	public Integer checkMaxActiveUsers(LoginForm loginForm){
		Integer maxActiveUsers = null;
		try{
			maxActiveUsers = loginService.getMaxActiveUsers(loginForm);
		}catch(Exception e){
			logger.error("",e);
		}
		return maxActiveUsers;
	}
	
	
	public boolean validateLogin(HttpServletRequest request,HttpServletResponse response, LoginForm login,ModelMap model) {
		boolean authenticateStatus = false;
		
		try{		
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("userName",login.getUserId());
			jsonObj.put("password",login.getPassword());
			jsonObj.put("organization",login.getOrganization());
			jsonObj.put("serviceUserName",login.getServiceUsername());
			jsonObj.put("servicePassword",login.getServicePassword());
			
			final String uri = validateLoginURL;
			System.out.println("URI:::"+uri);
			URL url = new URL(uri);
			HttpURLConnection connection = null;
			OutputStreamWriter out = null;
			String json_response = "";
			InputStreamReader in = null;
			BufferedReader br = null;
			
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
			System.out.println("JSON RESPONSE: "+json_response);
			
			JSONObject object = new JSONObject(json_response);
			String message = (String)object.get("message");
			if(message.equalsIgnoreCase("success")){
				authenticateStatus = true;
			}else{
				authenticateStatus = false;
			}
			
			
			/*if(!model.containsAttribute("PasswordChangeFailStatus")){
				 //authenticateStatus=loginService.authenticate(login);
				 
			}else{
				authenticateStatus = true;
			}*/
		}
		catch(Exception e){
			logger.error("",e);
		}
	return  authenticateStatus;
	}
	
	
	/*******Added By Naimisha 22042017*******/
	public String getResourceInfo(HttpServletRequest request,HttpServletResponse response, LoginForm login,ModelMap model) {		
		String strView=null;
		try{	
			SessionObject sessionObject = new SessionObject();
			Resource resource = loginService.getAccessTypeAndRoleList(login.getUserId());
			
			
			
			
			Map<String, String> availableRolesAndAccess = new ConcurrentHashMap<String, String>();		
			Set<String> roles = new HashSet<String>();
			if(null != resource ){	
				
				
		        sessionObject.setUserId(login.getUserId());				        
		        sessionObject.setUserName(resource.getName());
		        sessionObject.setResourceTpye(resource.getResourceTypeName());	
		        HttpSession session = request.getSession();
		        session.setAttribute("resourceType", resource.getResourceTypeName());
		        if(null != resource.getImage()){
	        		Image i = new Image();
	        		i.setImagepath(fileUploadDownload.getBase64Image(resource.getImage().getImagepath()));
	        		resource.setImage(i);
		        }
		        
				model.addAttribute("resource",resource);
				
				if(null!=resource.getLastVisitedOn()){
		        	sessionObject.setLastVisitedOn(resource.getLastVisitedOn());
			    }		        
		       /* if(!model.containsAttribute("sessionObject")){
		        	model.addAttribute("sessionObject", sessionObject);
		        }*/
				if(resource.getImage()!=null){
					sessionObject.setUserImage(resource.getImage().getImageName());								
				}
				
				
				
				if(resource.getResourceTypeName().equalsIgnoreCase("STUDENT")){
					//System.out.println("Student Login Not Allowed Cotrrently");
					List<Course>courseList = loginService.getCourseListForAStudent(login.getUserId());
					System.out.println("courseList size==="+courseList.size());
					model.addAttribute("courseList", courseList);
					sessionObject.setCourseList(courseList);
					sessionObject.setCourseCode(courseList.get(0).getCourseCode());
					session.setAttribute("courseCode", courseList.get(0).getCourseCode());
					/*Question surveyQuestion = surveyService.fetchSurveyForAProgramme(courseList.get(0).getCourseCode(),sessionObject.getUserId());
					//System.out.println("Survey Id===="+surveyQuestion.getSurveyId());
					if(null != surveyQuestion){
						sessionObject.setSurveyId(surveyQuestion.getSurveyId());  11052017
					}*/
					
					strView="common/navigation";
				}
				else{
					if(
						(resource.getRoleList()!=null && resource.getRoleList().size()!=0 )
						|| (resource.getAccessTypeList()!=null && resource.getAccessTypeList().size()!=0 )
					){
						//PRAD JUNE 14 2018
						//String passwordStatus = loginService.getPasswordStatus(login);	
						//passwordStatus="IA"; /* Delete after testing*/
						/*if(passwordStatus!=null && passwordStatus.equals("A")){
							model.addAttribute("NewPassword", "logout");
							strView="common/changePassword";
						}else if(passwordStatus != null && passwordStatus.equals("IA")){
							strView="common/notificationPage";
						}else{
							login.setMessage("Authentication problem.");
							strView = serveLoginPage(request,response, model,login,null);		
						}*/
						
						//PRAD ENDS
						
						/* Extracts Access Type List From  Resource Bean*/
						List<AccessType> accessTypeList = resource.getAccessTypeList();
						/* Extracts Role List From  Resource Bean*/
						List <Role> roleList = resource.getRoleList();		
						
						
						
						if(accessTypeList!=null && accessTypeList.size()!=0){							
							for(AccessType accessType : accessTypeList){							
								availableRolesAndAccess.put(accessType.getAccessTypeName(),"AccessType");
								for(Role role : accessType.getRoleList()){
									availableRolesAndAccess.put(role.getRoleName(),"Role");
									roles.add(role.getRoleName());
								}
							}
						}else{
							logger.info("accessTypeList not found");
						}
						
						if(roleList!=null && roleList.size()!=0){							
							for(Role role : roleList){
								/* Check availableRolesAndAccess doesn't contains duplicate entry */
								if(availableRolesAndAccess!=null && availableRolesAndAccess.size()!=0 ){
									for (Map.Entry<String, String> entry : availableRolesAndAccess.entrySet()) {
										if(!(entry.getKey().equalsIgnoreCase(role.getRoleName()))){
											availableRolesAndAccess.put(role.getRoleName(),"Role");
											roles.add(role.getRoleName());
										}	
									}
								}else{
									availableRolesAndAccess.put(role.getRoleName(),"Role");
									roles.add(role.getRoleName());
								}								
							}
						}else{
							logger.info("roleList not found");
						}
						
						sessionObject.setAvailableRoles(roles);
						if(availableRolesAndAccess!=null && availableRolesAndAccess.keySet()!=null && availableRolesAndAccess.keySet().size()!=0){
							sessionObject.setAvailableRolesAndAccess(availableRolesAndAccess);						
						}
						else{
							logger.info("Roles and accesstype not found");										
						}
						
						
						List<Role> tempRoleList=new ArrayList<Role>();
						if(resource.getAccessTypeList()!=null && resource.getAccessTypeList().size()!=0){
							sessionObject.setCurrentRoleOrAccess(resource.getAccessTypeList().get(0).getAccessTypeName());
							login.setAccessType(resource.getAccessTypeList().get(0).getAccessTypeName());
							model.addAttribute("roleList", resource.getAccessTypeList().get(0).getRoleList());
							tempRoleList=resource.getAccessTypeList().get(0).getRoleList();
						}else{
							login.setRole(resource.getRoleList().get(0).getRoleName());
							sessionObject.setCurrentRoleOrAccess(resource.getRoleList().get(0).getRoleName());	
							Role roleFromDB=resource.getRoleList().get(0);
							List<Role> roleListFromDB=new ArrayList<Role>();
							roleListFromDB.add(roleFromDB);
							model.addAttribute("roleList", roleListFromDB);
							tempRoleList=roleListFromDB;
						}	
						if(!model.containsAttribute("requestFromNotificationPage")){
							String clientIp = getClientIpAddr(request);
							login.setIp(clientIp);
							login.setHttpSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
							loginService.insertLoginDetails(login);
						}						
					
						// Added By Naimisha 16042018
						
						//****Starts ****//
						List<Task> taskList = loginService.getTaskDetailsListForAUser(login.getUserId());
						
						System.out.println("task list size=="+taskList.size());
						if(null != taskList){
							
						}
						//****End****//
					
					
					
					
						List<Functionality> functionalityList= new ArrayList<Functionality>();
						String getCurrentRoleOrAccess = sessionObject.getCurrentRoleOrAccess();
						if(sessionObject!= null && sessionObject.getUserId()!=null){
							if(getCurrentRoleOrAccess!=null && availableRolesAndAccess!=null){
								String value = availableRolesAndAccess.get(getCurrentRoleOrAccess);
								for(Role r:tempRoleList){
									if(value!=null && value.equalsIgnoreCase("AccessType")){
										AccessType accessType =new AccessType();
										accessType.setAccessTypeName(getCurrentRoleOrAccess);
										accessType.setModuleName(r.getModuleName());
										functionalityList=loginService.getFunctionalityListForAccessType(accessType);				
									}else{
										Role role=new Role();
										role.setModuleName(r.getModuleName());
										role.setRoleName(getCurrentRoleOrAccess);
										functionalityList=loginService.getFunctionalityListForRole(role);
									}
									MODULE_NAME_TO_DISPLAY_ITS_CHARTS = r.getModuleName();
									ROLE_NAME_TO_DISPLAY_ITS_CHARTS = r.getRoleName();
									
									if(null != taskList){
										for(Task task : taskList){
											if((task.getRoleName().equalsIgnoreCase(r.getRoleName()))){
												Functionality func = new Functionality();
												func.setFunctionalityName(task.getTaskName());
												func.setView(true);
												func.setInsert(true);
												func.setUpdate(true);
												functionalityList.add(func);
												break;
											}
										}
									}
									
									model.addAttribute(r.getModuleName().replace(" ", "")+"functionalityList",functionalityList);
								}
								
							}
						}
						strView="common/navigation";
					
					
					
					
					
					}else{
						login.setMessage("No Roles Found For Provided User Id");
						strView = serveLoginPage(request,response, model,login,null);			
					}
				}
				if(!model.containsAttribute("sessionObject")){
		        	model.addAttribute("sessionObject", sessionObject);
		        }
				ModelMap modelFromNotification=getEventsAndNotes(request,response,login,model, sessionObject);
		        model=modelFromNotification;
			}else{
				login.setMessage("User Details Not Found");
				strView = serveLoginPage(request,response, model,login,null);	
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		catch(Exception e){
			e.printStackTrace();
			logger.error("",e);
		}	
		return strView;
	}
	
	public ModelMap getEventsAndNotes(HttpServletRequest request,HttpServletResponse response, LoginForm login,ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {		
		List<Role> eventFromDb = null;
		List<CalendarEvent> eventForAllUserFromDb = null;
		List<CalendarEvent> eventCountForAllUserFromDb = null;
		List<CalendarEvent> eventForSuperadminFromDb = null;
		List<CalendarEvent> eventCountForSuperadminFromDb = null;
		List<Exam> listAssignedExam = null;
		List<Exam> listAssignedExamCount = null;
		CalendarEvent calendarEvent = new CalendarEvent();
		try{
			
			//Added By  By Naimisha 20092017
			String path = getEmailRootPath();
			commonService.insertEmailDetails(emailReceiver.readEmail(login.getUserId().trim(), login.getUserId().trim(),servletContext,path),login.getUserId().trim());
			
			
			Notification notification = loginService.getNewNotifications(login.getUserId().trim());			
			if( notification != null){				
				model.addAttribute("notification", notification);				
			}
			List<Notification> notificationList = commonService.getNotificationDescriptionList(sessionObject.getUserId().trim());			
			if(null!=notificationList && notificationList.size()!=0){
				model.addAttribute("notificationList", notificationList);
			}
			Notification emailNotification = commonService.getEmailDetails(login.getUserId().trim());
			if(null!=emailNotification){
				if(null!=emailNotification.getEmailDetailsList() && emailNotification.getEmailDetailsList().size()!=0){
					model.addAttribute("emailDetailsList", emailNotification.getEmailDetailsList());
				}
			}			
			calendarEvent.setCalendarEventBy(sessionObject.getCurrentRoleOrAccess());
			calendarEvent.setUpdatedBy(login.getUserId());
			eventFromDb = loginService.getEventUserBased(calendarEvent);						
			if(eventFromDb != null && eventFromDb.size() !=0){
				model.addAttribute("eventFromDb", eventFromDb);
			}
			eventForAllUserFromDb = loginService.getEventForAllUserFromDb();
			if(eventForAllUserFromDb != null && eventForAllUserFromDb.size() !=0){
				model.addAttribute("eventForAllUserFromDb", eventForAllUserFromDb);
			}
			eventCountForAllUserFromDb = loginService.getEventCountForAllUserFromDb();
			if(eventCountForAllUserFromDb != null && eventCountForAllUserFromDb.size() !=0){
				model.addAttribute("eventCountForAllUserFromDb", eventCountForAllUserFromDb);
			}
			eventForSuperadminFromDb = loginService.getEventForSuperadminFromDb();
			if(eventForSuperadminFromDb != null && eventForSuperadminFromDb.size() !=0){
				model.addAttribute("eventForSuperadminFromDb", eventForSuperadminFromDb);
			}
			eventCountForSuperadminFromDb = loginService.getEventCountForSuperadminFromDb();
			if(eventCountForSuperadminFromDb != null && eventCountForSuperadminFromDb.size() !=0){
				model.addAttribute("eventCountForSuperadminFromDb", eventCountForSuperadminFromDb);
			}
			listAssignedExam = loginService.getListAssignedExamFromDb();			
			if(listAssignedExam != null && listAssignedExam.size() !=0){
				model.addAttribute("listAssignedExam", listAssignedExam);
			}			
			listAssignedExamCount = loginService.getListAssignedExamCountFromDb();			
			if(listAssignedExamCount != null && listAssignedExamCount.size() !=0){
				model.addAttribute("listAssignedExamCount", listAssignedExamCount);
			}

			AcademicYear currentYear = commonService.getCurrentAcademicYear();
			if (currentYear != null) {
					String strYearArr[] = currentYear.getAcademicYearName().split("-");
					List<AcademicYear> ayList = new ArrayList<AcademicYear>();
					for (int i = 0; i < strYearArr.length; i++) {
						AcademicYear ay = new AcademicYear();
						ay.setAcademicYearName(strYearArr[i]);
						ayList.add(ay);
					}
					model.addAttribute("year", currentYear);
				}
		}
		catch(Exception e){
			logger.error(e);
		}
		return model;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request,HttpServletResponse response,
						 ModelMap model,LoginForm loginForm,SessionStatus sessionStatus,
						 @ModelAttribute("sessionObject") SessionObject sessionObject,HttpSession session) {
		if(model.containsAttribute("sessionObject")){			
			//ChatController chatController = new ChatController();
			//chatController.removeUser(sessionObject.getUserName());
			loginForm.setUserId(sessionObject.getUserId());
			loginService.updateLoginDetails(loginForm);
			if(model.containsAttribute("sessionObject")) model.remove("sessionObject");
			sessionStatus.setComplete();
			session.invalidate();
			if(loginForm.getMessage()==null){
				loginForm.setMessage("You have been logged out");
			}
		}
		return serveLoginPage(request,response, model,loginForm,null);
	}
	

/*
 *     Switches roleType on change of Drop Down in links.jsp
 * 
 */

	@RequestMapping(value = "/changeRoleForUser", method = RequestMethod.POST)
	public String changeRoleForUser(HttpServletRequest request, HttpServletResponse response,
												ModelMap model,
												Role role,
												@ModelAttribute("sessionObject") SessionObject sessionObject
											   ) {
		String strView=null;
		List<Role> eventFromDb = null;
		Set<String> roles = new HashSet<String>();
		List<Notification>notificationList = null;
		try{	
			Resource resource = loginService.getAccessTypeAndRoleList(sessionObject.getUserId());
			if(model.containsAttribute("sessionObject")){
				if(resource != null ){
					if(null != resource.getImage()){
		        		Image i = new Image();
		        		i.setImagepath(fileUploadDownload.getBase64Image(resource.getImage().getImagepath()));
		        		resource.setImage(i);
			        }
					model.addAttribute("resource",resource);
				}				
			}			
			if(resource != null ){		
				if((resource.getRoleList() != null && resource.getRoleList().size() != 0 )|| (resource.getAccessTypeList()!=null && resource.getAccessTypeList().size()!=0 )){
					/* Extracts Access Type List From  Resource Bean*/
					List<AccessType> accessTypeList = resource.getAccessTypeList();
					/* Extracts Role List From  Resource Bean*/
					List <Role> roleList = resource.getRoleList();	
					
					Map<String, String> availableRolesAndAccess =new ConcurrentHashMap<>();
					availableRolesAndAccess =sessionObject.getAvailableRolesAndAccess();					
					
					if(null != availableRolesAndAccess){
						List<Role> tempRoleList=new ArrayList<Role>();
						//System.out.println("role.getRoleName()"+role.getRoleName());
						String value = availableRolesAndAccess.get(role.getRoleName());
						//System.out.println("VALUES.................."+value);
							if(value=="AccessType"){
								AccessType accessType = new AccessType();								
								accessType.setAccessTypeName(role.getRoleName());		
								resource.setUserId(sessionObject.getUserId());
								resource.setAccessType(accessType);
								AccessType accesstypeFromDB = new AccessType();
								accesstypeFromDB=loginService.getAccessTypeDetails(resource);
								if(accesstypeFromDB!=null && accesstypeFromDB.getRoleList()!=null && accesstypeFromDB.getRoleList().size()!=0){
									sessionObject.setCurrentRoleOrAccess(accesstypeFromDB.getAccessTypeName());
									model.addAttribute("roleList",accesstypeFromDB.getRoleList());
									tempRoleList=accesstypeFromDB.getRoleList();
								}
							}else if(value=="Role"){								
								Role roleFromDB = loginService.getRoleDetails(role);
								if(roleFromDB!=null && roleFromDB.getFunctionalityList()!=null && roleFromDB.getFunctionalityList().size()!=0){							
									sessionObject.setCurrentRoleOrAccess(roleFromDB.getRoleName());							
									List<Role> roleListForNavigation= new ArrayList<Role>();
									roleListForNavigation.add(roleFromDB);
									model.addAttribute("roleList", roleListForNavigation);
									tempRoleList=roleListForNavigation;
								}
							}
							else{
								LoginForm login = new LoginForm();
								login.setMessage("No Roles Found For User Id "+sessionObject.getUserId());
								strView = serveLoginPage(request,response, model,login,null);
							}
							List<Functionality> functionalityList= new ArrayList<Functionality>();
							String getCurrentRoleOrAccess = sessionObject.getCurrentRoleOrAccess();
							//System.out.println("getCurrentRoleOrAccess:"+getCurrentRoleOrAccess);
							if(sessionObject!= null && sessionObject.getUserId()!=null){
								if(getCurrentRoleOrAccess!=null && availableRolesAndAccess!=null){
									for(Role r:tempRoleList){
										if(value!=null && value.equalsIgnoreCase("AccessType")){
											AccessType accessType =new AccessType();
											accessType.setAccessTypeName(getCurrentRoleOrAccess);
											accessType.setModuleName(r.getModuleName());
											functionalityList=loginService.getFunctionalityListForAccessType(accessType);
										}else{
											Role role1=new Role();
											role1.setModuleName(r.getModuleName());
											//System.out.println("=========================================MODULA NAME:"+r.getModuleName()+"=========");
											
											role1.setRoleName(getCurrentRoleOrAccess);
											functionalityList=loginService.getFunctionalityListForRole(role1);
										}
										//System.out.println("sdsds"+r.getRoleName());
										MODULE_NAME_TO_DISPLAY_ITS_CHARTS = r.getModuleName();
										ROLE_NAME_TO_DISPLAY_ITS_CHARTS = r.getRoleName();
										model.addAttribute(r.getModuleName().replace(" ", "")+"functionalityList",functionalityList);
									}
								}
							}
							LoginForm login = new LoginForm();
							login.setUserId(sessionObject.getUserId());
							notificationList = getListForNotification(request,response,login,model);
							//System.out.println("notificationList size ================"+notificationList.size());
							int taskCount = notificationList.size();
							model.addAttribute("taskCount",taskCount);
							strView="common/navigation";
						
									
					}else{
						LoginForm login = new LoginForm();
						login.setMessage("Session Expired..Please Login Again");
						strView = serveLoginPage(request,response, model,login,null);
						}	
					
					if(accessTypeList!=null && accessTypeList.size()!=0){							
						for(AccessType accessType : accessTypeList){
							roles.add(accessType.getAccessTypeName());
							for(Role r : accessType.getRoleList()){
								//System.out.println(""+r.getRoleName());
								roles.add(r.getRoleName());
							}
						}
					}
					if(roleList!=null && roleList.size()!=0){							
						for(Role r : roleList){
							roles.add(r.getRoleName());
						}
					}
					if(roles.contains(role.getRoleName())){
						 roles.remove(role.getRoleName()); 
					 }
					 sessionObject.setAvailableRoles(roles);
			}
		}else{
			LoginForm login = new LoginForm();
			login.setMessage("Session Expired..Please Login Again");
			strView = serveLoginPage(request,response, model,login,null);
		}
			
	}catch(Exception e){		
		logger.error("Error in changeRoleForUser method of Login Controller", e);
	}		
		return (strView);
	}
	
	
	
	@RequestMapping(value = "/getModuleDetails", method = RequestMethod.GET)
	public ModelAndView getModuleDetails(HttpServletRequest request,
		HttpServletResponse response, ModelMap model,@RequestParam("moduleName") String moduleName,
		@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView=null;
		List<Functionality> functionalityList= new ArrayList<Functionality>();
		if(sessionObject!= null){
		String getCurrentRoleOrAccess = sessionObject.getCurrentRoleOrAccess();
		try{		
		Map<String, String> availableRolesAndAccess =sessionObject.getAvailableRolesAndAccess();
		if(getCurrentRoleOrAccess!=null && moduleName!=null && availableRolesAndAccess!=null){
			String value = availableRolesAndAccess.get(getCurrentRoleOrAccess);		
			if(value!=null && value.equalsIgnoreCase("AccessType")){
				AccessType accessType =new AccessType();
				accessType.setAccessTypeName(getCurrentRoleOrAccess);
				accessType.setModuleName(moduleName);
				functionalityList=loginService.getFunctionalityListForAccessType(accessType);				
			}	
			else{
				Role role=new Role();
				role.setModuleName(moduleName);
				role.setRoleName(getCurrentRoleOrAccess);
				functionalityList=loginService.getFunctionalityListForRole(role);
			}	
		}if(sessionObject.getUserId()!=null){
			Resource resource = loginService.getAccessTypeAndRoleList(sessionObject.getUserId());
			if(resource!=null){
				
				if(null != resource.getImage()){
	        		Image i = new Image();
	        		i.setImagepath(fileUploadDownload.getBase64Image(resource.getImage().getImagepath()));
	        		resource.setImage(i);
		        }			
				
				model.addAttribute("resource",resource);
				
				sessionObject.setAvailableRolesAndAccess(availableRolesAndAccess);
				sessionObject.setCurrentRoleOrAccess(getCurrentRoleOrAccess);
				if(functionalityList!=null && functionalityList.size()!=0){
					Role role = new Role();
					role.setFunctionalityList(functionalityList);
					model.addAttribute("role",role);
					strView="common/navigation";					
				}
				else{
					LoginForm login = new LoginForm();
					login.setMessage("Access Denied To The Module");
					strView = serveLoginPage(request,response, model,login,null);;
			}
				}
			else{
				LoginForm login = new LoginForm();
				login.setMessage("Access Denied To The Module");
				strView = serveLoginPage(request,response, model,login,null);
			}
		}else{
			LoginForm login = new LoginForm();
			login.setMessage("Session Expired...");
			strView = serveLoginPage(request,response, model,login,null);
		}
	}
		catch(Exception e){
			logger.error("",e);
		}
	}
		else{
			LoginForm login = new LoginForm();
			login.setMessage("Session Expired...");
			strView = serveLoginPage(request,response, model,login,null);
		}
		return new ModelAndView(strView);
	}

	
	@RequestMapping(value = "/notificationPage", method = RequestMethod.GET)
	public String notificationPage(HttpServletRequest request,
										 HttpServletResponse response,
										 ModelMap model,
										 SessionStatus sessionStatus,
										 @ModelAttribute("sessionObject") SessionObject sessionObject,
										 HttpSession session) {
		
		if((sessionObject != null)){			
			Role role =new Role();
			role.setRoleName(sessionObject.getCurrentRoleOrAccess());			
			model.addAttribute("requestFromNotificationPage", "requestFromNotificationPage");
			return changeRoleForUser(request, response, model,role,sessionObject) ;
		}		
		else{
			LoginForm login = new LoginForm();
			login.setMessage("Your Session Has Expired. Please Login Again");
			return logOut(request, response, model, login,sessionStatus,sessionObject,session);
		}
		
	}
		
	
	//Naimisha 18092017
	@RequestMapping(value = "/studentNavigationPage", method = RequestMethod.GET)
	public String studentNavigationPage(HttpServletRequest request,
										 HttpServletResponse response,
										 ModelMap model,
										 @ModelAttribute("sessionObject") SessionObject sessionObject) {		
		String strView=null;
		try{		
			if(sessionObject != null){
				Resource resource = loginService.getAccessTypeAndRoleList(sessionObject.getUserId());			
				if(resource!=null){
					//Added By  By Naimisha 20092017
					String path = getEmailRootPath();
					commonService.insertEmailDetails(emailReceiver.readEmail(sessionObject.getUserId(), sessionObject.getUserId(),servletContext,path),sessionObject.getUserId());
					model.addAttribute("resource",resource);
					Notification notification = loginService.getNewNotifications(sessionObject.getUserId());
					if( notification != null){						
						model.addAttribute("notification", notification);
					}
					List<Notification> notificationList = commonService.getNotificationDescriptionList(sessionObject.getUserId().trim());			
					if(null!=notificationList && notificationList.size()!=0){
						model.addAttribute("notificationList", notificationList);
					}
					Notification emailNotification = commonService.getEmailDetails(sessionObject.getUserId());
					if(null!=emailNotification){
						if(null!=emailNotification.getEmailDetailsList() && emailNotification.getEmailDetailsList().size()!=0){
							model.addAttribute("emailDetailsList", emailNotification.getEmailDetailsList());
						}
					}
					strView="studentAccessablePages/studentNavigation";
				}
				else{
					LoginForm login = new LoginForm();
					login.setMessage("User Details Not Found");
					strView = serveLoginPage(request,response, model,login,null);
				}
			}
			else{
				LoginForm login = new LoginForm();
				login.setMessage("Session Expired...");
				strView = serveLoginPage(request,response, model,login,null);
			}
		}
		catch(Exception e){
			logger.error("",e);
		}		
		return strView;
	}
	
	//Modified By Naimisha 18092017
	@RequestMapping(value = "/studentNotificationPage", method = RequestMethod.GET)
	public String studentNotificationPage(HttpServletRequest request,
										 HttpServletResponse response,
										 ModelMap model,
										 @ModelAttribute("sessionObject") SessionObject sessionObject,
										 SessionStatus sessionStatus,HttpSession  session) {		
		LoginForm loginForm=new LoginForm();
		List<Role> eventFromDb = null;
		List<CalendarEvent> eventForAllUserFromDb = null;
		List<CalendarEvent> eventCountForAllUserFromDb = null;
		List<CalendarEvent> eventForSuperadminFromDb = null;
		List<CalendarEvent> eventCountForSuperadminFromDb = null;
		List<Exam> listAssignedExam = null;
		List<Exam> listAssignedExamCount = null;

		CalendarEvent calendarEvent = new CalendarEvent();	
		try {
			if((sessionObject.getUserId()!=null && sessionObject.getUserId().trim().length()!=0)){
				loginForm.setUserId(sessionObject.getUserId());
				//Added By  By Naimisha 20092017
				String path = getEmailRootPath();
				commonService.insertEmailDetails(emailReceiver.readEmail(sessionObject.getUserId(), sessionObject.getUserId(),servletContext,path),sessionObject.getUserId());
				Notification notification = loginService.getNewNotifications(sessionObject.getUserId());
				if( notification != null){				
					model.addAttribute("notification", notification);
				}
				List<Notification> notificationList;
				
					notificationList = commonService.getNotificationDescriptionList(sessionObject.getUserId().trim());
							
				if(null!=notificationList && notificationList.size()!=0){
					model.addAttribute("notificationList", notificationList);
				}
				Notification emailNotification = commonService.getEmailDetails(sessionObject.getUserId());
				if(null!=emailNotification){
					if(null!=emailNotification.getEmailDetailsList() && emailNotification.getEmailDetailsList().size()!=0){
						model.addAttribute("emailDetailsList", emailNotification.getEmailDetailsList());
					}
				}
				Resource resource = loginService.getAccessTypeAndRoleList(sessionObject.getUserId());					
				if(resource!=null ){
			        if(!model.containsAttribute("userId")){
			        	model.addAttribute("userId",sessionObject.getUserId());
			        }					
			        calendarEvent.setUpdatedBy(sessionObject.getUserId());
					calendarEvent.setCalendarEventBy(sessionObject.getCurrentRoleOrAccess());
					eventFromDb = loginService.getEventUserBased(calendarEvent);						
					if(eventFromDb != null && eventFromDb.size() !=0){
						model.addAttribute("eventFromDb", eventFromDb);
					}
					eventForAllUserFromDb = loginService.getEventForAllUserFromDb();
					if(eventForAllUserFromDb != null && eventForAllUserFromDb.size() !=0){
						model.addAttribute("eventForAllUserFromDb", eventForAllUserFromDb);
					}
					eventCountForAllUserFromDb = loginService.getEventCountForAllUserFromDb();
					if(eventCountForAllUserFromDb != null && eventCountForAllUserFromDb.size() !=0){
						model.addAttribute("eventCountForAllUserFromDb", eventCountForAllUserFromDb);
					}
					
					eventForSuperadminFromDb = loginService.getEventForSuperadminFromDb();
					if(eventForSuperadminFromDb != null && eventForSuperadminFromDb.size() !=0){
						model.addAttribute("eventForSuperadminFromDb", eventForSuperadminFromDb);
					}
					eventCountForSuperadminFromDb = loginService.getEventCountForSuperadminFromDb();
					if(eventCountForSuperadminFromDb != null && eventCountForSuperadminFromDb.size() !=0){
						model.addAttribute("eventCountForSuperadminFromDb", eventCountForSuperadminFromDb);
					}
					listAssignedExam = loginService.getListAssignedExamFromDb();			
					if(listAssignedExam != null && listAssignedExam.size() !=0){
						model.addAttribute("listAssignedExam", listAssignedExam);
					}			
					listAssignedExamCount = loginService.getListAssignedExamCountFromDb();			
					if(listAssignedExamCount != null && listAssignedExamCount.size() !=0){
						model.addAttribute("listAssignedExamCount", listAssignedExamCount);
					}
					AcademicYear currentYear = commonService.getCurrentAcademicYear();
					if (currentYear != null) {
							String strYearArr[] = currentYear.getAcademicYearName().split("-");
							List<AcademicYear> ayList = new ArrayList<AcademicYear>();
							for (int i = 0; i < strYearArr.length; i++) {
								AcademicYear ay = new AcademicYear();
								ay.setAcademicYearName(strYearArr[i]);
								ayList.add(ay);
							}
							model.addAttribute("year", currentYear);
						}
					model.addAttribute("resource",resource);
				}
				return "studentAccessablePages/studentNotificationPage";
			}
			else{
					loginForm.setMessage("Your Session Has Expired. Please Login Again");
					return logOut(request, response, model, loginForm,sessionStatus,sessionObject,session);
			}
		} catch (CustomException e) {
			logger.error("",e);
		}
		catch (Exception e) {
			logger.error("",e);
		}return null;	
	}
	
	
	@RequestMapping(value = "/userServices", method = RequestMethod.GET)
	public String userServices(HttpServletRequest request,
										 HttpServletResponse response,
										 ModelMap model,
										 @ModelAttribute("sessionObject") SessionObject sessionObject) {		
		String strView=null;
		try{		
			if(sessionObject!=null){
				Resource resource = loginService.getAccessTypeAndRoleList(sessionObject.getUserId());			
				if(resource!=null){
					
					if(null != resource.getImage()){
		        		Image i = new Image();
		        		i.setImagepath(fileUploadDownload.getBase64Image(resource.getImage().getImagepath()));
		        		resource.setImage(i);
			        }
					
					
					model.addAttribute("resource",resource);
					//Added By  By Naimisha 20092017
					String path = getEmailRootPath();
					commonService.insertEmailDetails(emailReceiver.readEmail(sessionObject.getUserId(), sessionObject.getUserId(),servletContext,path),sessionObject.getUserId());
					Notification notification = loginService.getNewNotifications(sessionObject.getUserId());
					if( notification != null){						
						model.addAttribute("notification", notification);
					}
					List<Notification> notificationList = commonService.getNotificationDescriptionList(sessionObject.getUserId().trim());			
					if(null!=notificationList && notificationList.size()!=0){
						model.addAttribute("notificationList", notificationList);
					}
					Notification emailNotification = commonService.getEmailDetails(sessionObject.getUserId());
					if(null!=emailNotification){
						if(null!=emailNotification.getEmailDetailsList() && emailNotification.getEmailDetailsList().size()!=0){
							model.addAttribute("emailDetailsList", emailNotification.getEmailDetailsList());
						}
					}
					strView="common/myServices";
				}
				else{
					LoginForm login = new LoginForm();
					login.setMessage("User Details Not Found");
					strView = serveLoginPage(request,response, model,login,null);
				}
			}
			else{
				LoginForm login = new LoginForm();
				login.setMessage("Session Expired...");
				strView = serveLoginPage(request,response, model,login,null);
			}
		}
		catch(Exception e){
			logger.error("",e);
		}		
		return strView;
	}
	
	
	
		
	
	public String getClientIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("X-Forwarded-For");  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_FORWARDED");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_FORWARDED");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_VIA");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("REMOTE_ADDR");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}   
	
	
	/**
	 * this method is used to change password 
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param userId
	 * @param login
	 * @return String
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public String updatePasswordByUser(HttpServletRequest request,HttpServletResponse response,
									   ModelMap model,@ModelAttribute("sessionObject") SessionObject sessionObject,LoginForm login,
									   SessionStatus sessionStatus){
		String passwordStatus="fail";
		try{
			if(login.getNewPassword().equals(login.getReTypeNewPassword())){
				if(sessionObject!=null && sessionObject.getUserId() != null){
					login.setUserId(sessionObject.getUserId());
					if(loginService.authenticate(login)){
						login.setStatus("ByUser");
						login.setUpdatedBy(sessionObject.getUserId());
						passwordStatus = loginService.updatePassword(login);
						loginService.updateLoginDetails(login);
					}
				}
			}
		}catch(Exception e){
			logger.error("",e);
		}
		if("success".equals(passwordStatus)){
			login.setMessage("Password changed successfully");
			return serveLoginPage(request,response, model,login,null);
		}else if(login.getStatus().equals("changeUserPassword")){
			login.setUserId(sessionObject.getUserId());
			model.addAttribute("PasswordChangeFailStatus", "updateFail");
			return home(request,response,login,model);
		}else{
			model.addAttribute("message", passwordStatus);
			return "common/changePassword";
		}
	}
	
	
	/**
	 * @author naimisha.ghosh
	 * */
	
	public List<Notification> getListForNotification(HttpServletRequest request,HttpServletResponse response, LoginForm login,ModelMap model) {
		List<Notification> listOfNotification = null;
		try{			
			String user_id = login.getUserId();
			listOfNotification = loginService.getNotificationListCount(user_id); 
		}
		catch(Exception e){
			logger.error("",e);
		}
	return  listOfNotification;
	}
	
	
	

	@RequestMapping(value = "/updateTaskNotification", method = RequestMethod.GET)
	public @ResponseBody
	String updateTaskNotification(ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		List<Notification> listOfNotification = null;
		List<Notification> listOfNotificationForTask = null;
		int statusTaskNotification = 0;
		int statusForTaskDetails = 0;
		int statusForAlertUpdate = 0;
		int taskCount =0;
		String msgForNotification = null;
		StringBuffer json = new StringBuffer();
		try{	
			
			String user_id = sessionObject.getUserId();
			//listOfNotification = loginService.getNotificationList(user_id);
			listOfNotification = loginService.getNotificationListCount(user_id);
			if(listOfNotification != null){
				//for(Notification notification : listOfNotification){
				statusTaskNotification = loginService.updateNotification(listOfNotification);
				for(Notification notofication : listOfNotification){
					msgForNotification = msgForNotification +";"+ notofication.getNotificationDesc();
				}
					
				//}
			}
			//listOfNotificationForTask = loginService.getNotificationListForTask(user_id);
		/*	listOfNotificationForTask = loginService.getNotificationListForTaskCount(user_id);
			if(listOfNotificationForTask != null){
				
				statusForTaskDetails = loginService.updateTaskDetailsForNotification(listOfNotificationForTask);
				for(Notification notofication : listOfNotificationForTask){
					msgForNotification = msgForNotification +";"+ notofication.getNotificationDesc();
				}
			}*/
			
			Notification notification = new Notification();
			notification.setUpdatedBy(user_id);
			List<Notification> allnotificationAlertList = loginService.getAllNotificationListFromAlert(user_id);
			//System.out.println("allnotificationAlertList size===="+allnotificationAlertList.size());
			if(allnotificationAlertList != null){
				statusForAlertUpdate = loginService.updateAlertForNotification(allnotificationAlertList);
				for(Notification notofication : allnotificationAlertList){
					String msg = notofication.getNotificationSender()+" Done The Task Of "+notofication.getNotificationSubject();
					msgForNotification = msgForNotification +";"+ msg;
				}
			}
			if(statusForTaskDetails  == 1 ||statusTaskNotification==1){
				taskCount = 0;
				json.append(taskCount);
			}
			
			//System.out.println("msg==="+msgForNotification);
		}
		catch(Exception e){
			logger.error("within updateTaskNotification()",e);
		}
		//return (new Gson().toJson(json.toString()));
		return (new Gson().toJson(msgForNotification));
	}
	
	
	public List<Notification> getTaskNotificationList(HttpServletRequest request,HttpServletResponse response, LoginForm login,ModelMap model) {
		List<Notification> listOfNotification = null;
		try{			
			String user_id = login.getUserId();
			listOfNotification = loginService.getNotificationListForTaskCount(user_id);
		}
		catch(Exception e){
			logger.error("Within getTaskNotificationList()",e);
		}
	return  listOfNotification;
	}
	
	/**
	 * back to home page*/
	
	@RequestMapping(value = "/backToHome", method = RequestMethod.GET)
	public String backToHome(HttpServletRequest request,
							 HttpServletResponse response,
							 ModelMap model,
							 SessionStatus sessionStatus,
							 @ModelAttribute("sessionObject") SessionObject sessionObject,
							 HttpSession session) {
		//System.out.println("User Id====="+sessionObject.getUserId());
		if((sessionObject != null)){
			Role role =new Role();
			//System.out.println("Access Type======"+sessionObject.getResourceTpye());
			role.setRoleName(sessionObject.getCurrentRoleOrAccess());	
			String roleOrAccess = sessionObject.getResourceTpye();
			
			if(roleOrAccess .equalsIgnoreCase("STUDENT")){
				return "common/navigation";
			}else{
				model.addAttribute("requestFromNotificationPage", "requestFromNotificationPage");
				return changeRoleForUser(request, response, model,role,sessionObject) ;
			}
			
		}
		else{
			LoginForm login = new LoginForm();
			login.setMessage("Your Session Has Expired. Please Login Again");
			return logOut(request, response, model, login,sessionStatus,sessionObject,session);
		}
	}
	
	/**
	 * view all alerts received by user**/
	
	@RequestMapping(value = "/viewAlerts", method = RequestMethod.GET)
	public @ResponseBody
	String viewAlerts(ModelMap model,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		List<Notification> listOfAlerts = null;
		int statusofAlert = 0;
		int alertCount = 0;
		String finalAlertDescription = null;
		StringBuffer json = new StringBuffer();
		try{
			String userId= sessionObject.getUserId();
			listOfAlerts = loginService.getlistOfAlerts(userId);
			if(null!= listOfAlerts && listOfAlerts.size()!=0){
				statusofAlert = loginService.updateStatusOfAlert(listOfAlerts);
				for(Notification alert : listOfAlerts){
					finalAlertDescription = finalAlertDescription+"*"+alert.getNotificationDesc();
				}
			}
			if(statusofAlert ==1){
				alertCount = 0;
				json.append(alertCount);
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(finalAlertDescription));
	}
	
	
	/**
	 * @author anup.roy
	 * get count of unread alerts*/
	
	public List<Notification> getUnreadAlertList(HttpServletRequest request,HttpServletResponse response, LoginForm login,ModelMap model) {
		List<Notification> unreadAlertList = null;
		try{
			String user_id = login.getUserId();
			unreadAlertList = loginService.getUnreadAlertList(user_id);
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("error in getUnreadAlertList method in loginController");
		}
	return  unreadAlertList;
	}
	
	/***
	 * @author anup.roy
	 * view emails received by user
	 * **/
	
	@RequestMapping(value = "/viewEmails", method = RequestMethod.GET)
	public @ResponseBody
	String viewEmails(ModelMap model,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		List<Notification> listOfMails = null;
		int statusofMail = 0;
		int mailCount = 0;
		String finalMailDescription = null;
		StringBuffer json = new StringBuffer();
		try{
			String userId= sessionObject.getUserId();
			listOfMails = loginService.getlistOfMails(userId);
			if(null!= listOfMails && listOfMails.size()!=0){
				statusofMail = loginService.updateStatusOfMail(listOfMails);
				for(Notification alert : listOfMails){
					finalMailDescription = finalMailDescription+"*"+alert.getNotificationDesc();
				}
			}
			if(statusofMail ==1){
				mailCount = 0;
				json.append(mailCount);
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(finalMailDescription));
	}
	
	
	/**
	 * @author anup.roy
	 * 12072017
	 * get count of unread mails*/
	
	public List<EmailDetails> getUnreadEmailList(HttpServletRequest request,HttpServletResponse response, LoginForm login,ModelMap model) {
		List<EmailDetails> unreadEmailList = null;
		try{
			String user_id = login.getUserId();
			unreadEmailList = loginService.getUnreadMailList(user_id);
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error("error in getUnreadAlertList method in loginController");
		}
	return  unreadEmailList;
	}
	
	
	/**
	 * to view all alerts **/
	
	@RequestMapping(value = "/fetchAllAlertForCurrentUser", method = RequestMethod.GET)
	public String fetchAllAlertForCurrentUser(HttpServletRequest request,
												HttpServletResponse response,
												ModelMap model,	
												@ModelAttribute("sessionObject") SessionObject sessionObject
												) {
		List<Notification> listOfAlerts = null;
		try{
			String userId= sessionObject.getUserId();
			listOfAlerts = loginService.getlistOfAlerts(userId);
			model.addAttribute("listOfAlerts", listOfAlerts);
		}catch(Exception e){
			logger.error("Exception in fetchAllAlertForCurrentUser() in CommonController: ", e);
			e.printStackTrace();
		}
		return "common/allAlertList";
	}
	
	
	@RequestMapping(value = "/fetchAllNotificationForCurrentUser", method = RequestMethod.GET)
	public String fetchAllNotificationForCurrentUser(
					HttpServletRequest request,
					HttpServletResponse response,
					ModelMap model,	
					@ModelAttribute("sessionObject") SessionObject sessionObject
					) {
		List<Notification> listOfNotification = null;
		List<Notification> listOfNotificationForTask = null;
		List<Notification> listOfNotificationFOrmAlert = null;
		try{	
			String user_id = sessionObject.getUserId();
			listOfNotification = loginService.getNotificationList(user_id);
			//System.out.println("listOfNotification size===="+listOfNotification.size());
			listOfNotificationForTask = loginService.getNotificationListForTask(user_id);
			//System.out.println("listOfNotificationForTask size==="+listOfNotificationForTask.size());
			listOfNotificationFOrmAlert = loginService.getNotificationListFromAlert(user_id);
			model.addAttribute("listOfNotification", listOfNotification);
			model.addAttribute("listOfNotificationForTask", listOfNotificationForTask);
			model.addAttribute("listOfNotificationFOrmAlert", listOfNotificationFOrmAlert);
		}catch(Exception e){
			logger.error("Exception in fetchAllNotificationForCurrentUser() in CommonController: ", e);
			e.printStackTrace();
		}
		return "common/allNotificationList";
	}

	/*********Added By Naimisha 22042017*********/
	@RequestMapping(value = "/changeCourseCodeInHttpSession", method = RequestMethod.GET)
	public String changeCourseCodeInHttpSession(HttpServletRequest request,
								 HttpServletResponse response, ModelMap model,
								 @RequestParam(value = "courseCode", required = false) String courseCode) {
		String strView=null;
		try {
			System.out.println("courseCode==="+courseCode);
			HttpSession session = request.getSession();
			session.setAttribute("courseCode", courseCode);	

		} catch (Exception e) {
			logger.error("",e);
		} 
		strView="common/loginForm";		
		return strView;
	}
	
	/*********Added By Naimisha 02052017******/
	/********Naimisha Change 05052017**********/

	@RequestMapping(value = "/changeProgramForStudent", method = RequestMethod.POST)
	public String changeProgramForStudent(HttpServletRequest request, HttpServletResponse response,
												ModelMap model,
												@ModelAttribute("programName") String programName,
												@ModelAttribute("sessionObject") SessionObject sessionObject
											   ) {
		String strView=null;
		List<Role> eventFromDb = null;
		Set<String> roles = new HashSet<String>();
		List<Notification>notificationList = null;
		System.out.println("within");
		try{	
			
			List<Course>courseList = loginService.getCourseListForAStudent(sessionObject.getUserId());
			if(courseList.size()!=0){
				
				System.out.println("courseList size==="+courseList.size());
				System.out.println("programName====="+programName);
				model.addAttribute("courseList", courseList);
				//Question surveyQuestion = surveyService.fetchSurveyForAProgramme(programName,sessionObject.getUserId());
				sessionObject.setCourseList(courseList);
				sessionObject.setCourseCode(programName);
				/*if(null != surveyQuestion){
					System.out.println("Survey Id===="+surveyQuestion.getSurveyId());
					sessionObject.setSurveyId(surveyQuestion.getSurveyId());
				}else{
					sessionObject.setSurveyId("NA");
				}*/
				
				strView="common/navigation";
			}else{
				LoginForm login = new LoginForm();
				login.setMessage("Session Expired..Please Login Again");
				strView = serveLoginPage(request,response, model,login,null);
			}	
			
					
					
			
	}catch(Exception e){	
		e.printStackTrace();
		logger.error("Error in changeProgramForStudent method of Login Controller", e);
	}		
		return (strView);
	}
	
	private String getEmailRootPath(){
	
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		File directory = new File(repository);
		boolean isExists = directory.exists();
		String path = null;
		if(isExists == true){
			path = repository+"/"+emailAttachmentsPath;
		}
		System.out.println("path====="+path); 
		return path;
	}
	
}
