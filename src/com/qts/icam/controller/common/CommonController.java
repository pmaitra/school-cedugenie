package com.qts.icam.controller.common;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXEvent;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.DHXStatus;
import com.google.gson.Gson;
import com.qts.icam.controller.login.CustomEventsManager;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.administrator.Approver;
import com.qts.icam.model.administrator.ServerConfiguration;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.backoffice.Fees;
import com.qts.icam.model.backoffice.Term;
import com.qts.icam.model.backoffice.TimeTableGridData;
import com.qts.icam.model.backoffice.WebIQTransaction;
//import com.qts.icam.model.backoffice.TimeTableParameter;
import com.qts.icam.model.common.StudentAttendance;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.AnnualStock;
import com.qts.icam.model.common.AnnualStockList;
import com.qts.icam.model.common.AssetSubType;
import com.qts.icam.model.common.AssetType;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Budget;
import com.qts.icam.model.common.Condemnation;
import com.qts.icam.model.common.CondemnationList;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.EmailDetails;
import com.qts.icam.model.common.FeesCategory;
import com.qts.icam.model.common.FinancialYear;
import com.qts.icam.model.common.Item;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.LicenseInfo;
import com.qts.icam.model.common.Notification;
import com.qts.icam.model.common.NotificationDetails;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.SchoolNote;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.SmsAudit;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.StatusOfItem;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.inventory.CommodityPurchaseOrder;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.survey.Answer;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.survey.QuestionMaster;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.TaskComment;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketComment;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.backoffice.BackOfficeService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.erp.ERPService;
import com.qts.icam.service.finance.FinanceService;
import com.qts.icam.service.inventory.InventoryService;
import com.qts.icam.service.login.LoginService;
import com.qts.icam.service.ticket.TicketService;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.mailservice.EmailReceiver;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
//import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;


@Controller
@SessionAttributes("sessionObject")
public class CommonController implements HandlerExceptionResolver {
	
	//@Autowired
	//ServerAuthenticator serverAuthenticator;
	@Autowired
	TicketService ticketService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	AcademicsService academicsService;
	
	@Autowired
	BackOfficeService backOfficeService;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	ERPService erpService;
	
	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	FileUploadDownload fileUploadDownload;
	
	@Autowired
	FinanceService financeService;
	
	@Autowired
	Utility utility;
	
	@Value("${root.path}")
	private String rootPath;
	
	@Value("${employeeDoc.path}")
	private String employeeDocFolderPath;
	
	//@Autowired
	//EmailReceiver emailReceiver = null;
	
	@Value("${email.subject}")
	private String emailSubject;
	
	@Value("${email.body}")
	private String emailBody;
	
	@Value("${emilTemplate.basePath}")
	private String emilTemplateBasePath;

	@Value("${ticketAttachments.path}")
	private String ticketAttachmentsPath;
	
	@Value("${assignment.path}")
	private String uploadAssignmenetPath;
	
	@Value("${reportNOCConfigFile.path}")
	private String reportNOCConfigFilePath;
	
	@Value("${emailAttachments.path}")
	private String emailAttachmentsPath;
	
	@Autowired
	LoginService loginService = null;
	
	@Value("${alert.scheduledTime}")
	private String alertScheduledTime;
	
	@Value("${reportGatePassConfigFile.path}")
	private String reportGatePassConfigFilePath;

	@Value("${reportGatePassXSLTFile.path}")
	private String reportGatePassXSLTFilePath;

	@Value("${reportNOCXSLTFile.path}")
	private String reportNOCXSLTFilePath;
	
	@Value("${Portal.userName}")
	private String portalUserName;
	
	@Value("${Portal.passWord}")
	private String portalPassWord;
	
	@Value("${URI.sendSchoolNote}")
	private String URIForSendSchoolNote;
	
	@Value("${webiq.available}")
	private String isWebIQAvailable;
	
	@Value("${URI.sendLeaveDetailsOfCadet}")
	private String sendLeaveDetailsOfCadet;
	
	@Value("${sms.enabled}")
	private String isSMSEnabled;

	@Value("${sms-url}")
	private String smsURL;
	
	@Value("${authkey}")
	private String smsAuthkey;
	
	public static Logger logger = Logger.getLogger(CommonController.class);
	
	private static HashMap<String, String> chatLog = new HashMap<>();


	/**
	 * USED FOR EXCEPTION HANDLING
	 * 
	 */
	
	@Autowired
	InventoryService inventoryService;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception exception) {
		//Map<Object, Object> model = new HashMap<Object, Object>();
		String viewName="common/errorpage";
		ModelAndView mav=new ModelAndView(viewName);
			//  check max file size
			if (exception instanceof MaxUploadSizeExceededException){		
				logger.error("########MAX UPLOAD FILE SIZE EXCEED EXCEPTION##########In CommonController resolveException() method:###########MAX UPLOAD FILE SIZE EXCEED EXCEPTION############",exception);
				mav.addObject("ResultError", "File size should be less then "+" 1MB");
				viewName="common/errorpage";
			}else if (exception instanceof PersistenceException){	
				logger.error("########IBATIS PROBLEM##########In CommonController resolveException() method:###########IBATIS PROBLEM############IBATIS PROBLEM###########",exception);
				mav.addObject("ResultError", "IBATIS ERROR ");
				viewName="common/errorpage";
			}else if(exception instanceof MessagingException){
				
				logger.error("########MEssing anf mail exceptionmethod:###########IBATIS PROBLEM############IBATIS PROBLEM###########",exception);
				mav.addObject("ResultError", "MAIL ERROR ");
				viewName="common/errorpage";
			}else if(exception instanceof HttpSessionRequiredException){
			
				logger.error("########SESSION OVER##########In CommonController resolveException() method:###########SESSION OVER############SESSION OVER#################",exception);
				
				viewName="forward:/login.html?status=sessionExpired";
				mav=new ModelAndView(viewName);
			}else{
				logger.info("In CommonController resolveException() method:",exception);
				exception.printStackTrace();
				mav.addObject("ResultError","Error......");
			 }
		
			return mav;
	}
	
	@RequestMapping(value = "/getTimeFromDB", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getTimeFromDB() {
		logger.info("In getTimeFromDB() Ajax method of CommonController");
		logger.info("In CommonController getTimeFromDB() method: calling getTimeFromDB() in CommonController.java");
		String dateAndTime = null;
		try {
			dateAndTime = commonService.getTimeFromDB();
			/*
			if(null!=dateAndTime){
				String[] parts = dateAndTime.split(":");
				int hour=Integer.parseInt(parts[0]);
				hour=hour/1;
				int min=Integer.parseInt(parts[1]);
				min=min/1;
				int sec=Integer.parseInt(parts[2]);
				sec=sec/1;
				dateAndTime=hour+":"+min+":"+sec+":"+parts[3];
			}
			*/
			
		} catch (CustomException e) {			
			logger.error(e);
		}
		return (new Gson().toJson(dateAndTime));
	}
			
		//----------------------new added by sovan.mukherjee--------------------------
			
	/**
	 * this Ajax call get states for a country
	 * 
	 * @param stateCountry
	 * @return String
	 */
	@RequestMapping(value = "/getStateList", method = RequestMethod.GET)
	public @ResponseBody
	String getStateList(@RequestParam("country") String country) {
		String states="";
		try{
			states = commonService.getStateNameList(country);
		}catch(Exception e){
			logger.error("exception in getStateList() in CommonController: ", e);
		}
		return states;
	}
		
	/*
	 * Check Whether any chat Message is coming or not
	 * 
	 * */
	@RequestMapping(value = "/getChatCall", method = RequestMethod.GET)
	public @ResponseBody
	String getChatCall(@RequestParam("userName") String userName) {				
		String message="";
		userName=userName.toUpperCase();
		//System.out.println("chatLog==="+chatLog);
		try {
			if(!chatLog.isEmpty()){						
				if(null != userName){
					if(chatLog.containsValue(userName)){
					//if(chatLog.containsKey(userName)){
						String messageFrom="";
						
						for (Map.Entry<String, String> entry : chatLog.entrySet()) {
							if (entry.getValue().equals(userName)) {
								messageFrom = messageFrom+entry.getKey()+", ";
							}
						}
						messageFrom = messageFrom.substring(0, messageFrom.length() - 2);
						if(null != messageFrom){
							message = userName+":"+messageFrom;							
						}else{
							logger.info("Chat Details Not Found");
						}								
					}else{
						logger.info("No Chat Window Opened ");
					}
				}else{
					logger.error("Individual Chat Log Failed to read In In Common Controller");
				}					
			}
		} catch (Exception e) {			
			logger.error("Individual Chat Log Failed to read In Common Controller", e);
		}
		return message;
	}	
	
	
	/*
	 * Individual Chat Conversation
	 * 
	 * */
	@RequestMapping(value = "/notifyChat", method = RequestMethod.GET)
	public @ResponseBody
	String notifyChat(@RequestParam("From") String from,@RequestParam("To") String to) {
	String status="false";
	String room="";
	try{
		if(null !=to && null != from){
//			System.out.println(from+"\t\t"+to);
			from=from.toUpperCase();
			to=to.toUpperCase();
//			System.out.println("BEFORE");
//			for (Map.Entry<String, String> entry : chatLog.entrySet()) {
//				System.out.print(entry.getKey()+"+"+entry.getValue());
//			}System.out.println("\n\n\n");
			
			if(chatLog.size()>0){
				if(chatLog.containsKey(from) && chatLog.get(from).equalsIgnoreCase(to))
				{
					chatLog.remove(from);
					status="true";
					room = to+"~"+from;
//					System.out.println("room1=="+room);
				}
				else if(chatLog.containsKey(to) && chatLog.get(to).equalsIgnoreCase(from))
				{
//					System.out.println("within second");
					chatLog.remove(to);
					status="true";
					room = to+"~"+from;
//					System.out.println("room2=="+room);
				}
				else{
					chatLog.put(from.toUpperCase(),to.toUpperCase());
					room = from+"~"+to;
//					System.out.println("room3=="+room);
				}
			}else{
				chatLog.put(from.toUpperCase(),to.toUpperCase());
				room = from+"~"+to;
//				System.out.println("room4=="+room);
			}
			
//			System.out.println("AFTER");
//			for (Map.Entry<String, String> entry : chatLog.entrySet()) {
//				System.out.print(entry.getKey()+"+"+entry.getValue());
//			}System.out.println("\n\n\n");			
		}		
		else{
			logger.error("Individual Chat Log Failed to write In In Common Controller");
		}
		InetAddress IP = InetAddress.getLocalHost();
		room = room+";"+IP.getHostAddress();
		}catch(Exception e){
			logger.error("Individual Chat Log Failed to write In Common Controller", e);
		}
		return room;
	}
	
	
	/*
	 * 
	 * Check Users available For Chat
	 * */
	
	@RequestMapping(value = "/individualChat", method = RequestMethod.GET)
	public @ResponseBody
	String individualChat(@ModelAttribute("sessionObject") SessionObject sessionObject) {	
		String resourceName="";			
		try{
			List<Resource> resourceList = commonService.getActiveLoggedInUser(sessionObject.getUserId());
			if(resourceList!=null && resourceList.size()!=0){
				for(Resource resource : resourceList){
					if(resource.getName()!=null){
						resourceName=resourceName+resource.getName()+";";
					}						
				}					
			}else{
				logger.info("No Resource Present For Individual Chat");
			}
		}catch(Exception e){
			logger.error("Exception in ", e);
			
		}
		return resourceName;
	}
	
	
	/**
	 * Get User Name For Id
	 */
	@RequestMapping(value = "/getUserNameForId", method = RequestMethod.GET)
	public @ResponseBody
	String getUserNameForId(@RequestParam("userId") String userId) {
		String userName = null;
		try {
			logger.info("getUserNameForId() In CommonController.java");
			userName = commonService.getUserNameForId(userId);
		} catch (NullPointerException e) {
			logger.error("getUserNameForId() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getUserNameForId() In CommonController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(userName));
	}
	
    /**
	 * Get Section Against Standard
	 */
	@RequestMapping(value = "/getSectionAgainstStandard", method = RequestMethod.GET)
	public @ResponseBody
	String getSectionAgainstStandard(@RequestParam("standard") String standard) {
		String section = null;
		try {
			logger.info("getSectionAgainstStandard() In CommonController.java");
			section = commonService.getSectionAgainstStandard(standard);
		} catch (NullPointerException e) {
			logger.error("getSectionAgainstStandard() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getSectionAgainstStandard() In CommonController.java: Exception"
					+ e);
		}
		return section;
	}
	
	
	/**
	 * Get User Based On Resource Type
	 */

	@RequestMapping(value = "/getUserIdForResourceType", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getUserIdFromResourceForAccessType(
			@RequestParam("term") String strQuery,
			@RequestParam("resourceType") String resourceTypeCode) {
		List<String> userIdFromResource = null;
		ResourceType resourceType = new ResourceType();
		try {
			logger.info("getUserIdForResourceType()In AdministratorController.java");
			if (resourceTypeCode != null && strQuery != null) {
				resourceType.setResourceTypeCode(resourceTypeCode);
				resourceType.setUpdatedBy(strQuery);
				userIdFromResource = commonService.getUserIdForResourceType(resourceType);
			}
		} catch (NullPointerException e) {
			logger.error("getUserIdForResourceType() In AdministratorController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getUserIdForResourceType() In AdministratorController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(userIdFromResource));
	}
	
	
	/**
	 * this method is used to open addVendor.jsp with vendor types 
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/addVendor", method = RequestMethod.GET)
	public String addVendor(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In addVendor method of CommonController.java");
			List<VendorType> vendorTypeList = commonService.getVendorTypes();
			if (vendorTypeList != null) {
				model.addAttribute("vendorTypeList", vendorTypeList);
				List vendorList = commonService.getVendorList();
				logger.info("The vendor list is ready to display.");
				model.addAttribute("vendorDetailsList", vendorList);
				FinancialYear financialYear = commonService.getFinancialYear();
				model.addAttribute("financialYear", financialYear);
			}
		} catch (Exception e) {
			logger.error("addVendor() In CommonController.java: Exception",e);
		}
		
		return "common/addVendor";
	}
	
	/**
	 * this method is used to add vendor details   
	 * modified by ranita.sur 28062017
	 * @param model
	 * @param vendor
	 * @param sessionObject
	 * @return String
	 */
	@RequestMapping(value = "/submitAddVendor", method = RequestMethod.POST)
	public String submitAddVendor(ModelMap model, Vendor vendor, HttpServletRequest request, HttpServletResponse response,Ledger ledger,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In submitVendor method of CommonController.java");
			vendor.setUpdatedBy(sessionObject.getUserId());
			ledger.setUpdatedBy(sessionObject.getUserId());
			int insertStatus = commonService.addVendor(vendor,ledger);
			if(insertStatus !=0){
				model.addAttribute("successStatus", "Successfully updated");
			}else{
				model.addAttribute("failStatus", "Update failed");
			}
		} catch (Exception e) {
			logger.error("submitAddVendor() In CommonController.java: Exception", e);
		}
		return addVendor(request,response,model);
	}
	
	/**
	 * this method is used to get vendor list
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/vendorList", method = RequestMethod.GET)
	public String listVendor(HttpServletRequest request,ModelMap model) {
		try {
			logger.info("In vendorList method of CommonController.java");
			List<Vendor> vendorPagedListHolder= commonService.getVendorList();
			model.addAttribute("vendorPagedListHolder", vendorPagedListHolder);
		} catch (Exception e) {
			logger.error("listVendor() In CommonController.java: Exception" + e);
		}
		return "common/vendorList";
	}
	
	/**
	 * this method is used to pagination for vendor list 
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/vendorListPagination", method = RequestMethod.GET)
	public String vendorListPagination(HttpServletRequest request,ModelMap model) {
		try {
			logger.info("In vendorListPagination method of CommonController.java");
			PagedListHolder<Vendor> vendorPagedListHolder = commonService.getVendorListPaging(request);	
			if (vendorPagedListHolder != null && vendorPagedListHolder.getNrOfElements()!=0) {
				model.addAttribute("first", vendorPagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", vendorPagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", vendorPagedListHolder.getNrOfElements());
				model.addAttribute("vendorPagedListHolder", vendorPagedListHolder);
			}
		}catch(Exception e){
			logger.error("Exception in vendorPagedListHolder() in CommonController: ", e);
		}
		return "common/vendorList";
	}
	
	/**
	 * this method get vendor details for vendor code
	 * 
	 * @param model
	 * @param vendorCode
	 * @return String
	 */
	@RequestMapping(value = "/vendorDetails", method = RequestMethod.POST)
	public String vendorDetails(ModelMap model,
									@RequestParam("radio") String vendorCode) {
		try {
			logger.info("In vendorDetails method of CommonController.java");
			Vendor vendor = commonService.getVendorDetails(vendorCode);
				model.addAttribute("vendor", vendor);
		}catch (Exception e){
			logger.error("vendorDetails() In CommonController.java: Exception", e);
		}
		return "common/vendorDetails";
	}
	
	/**
	 * this method is used to update or delete vendors
	 * 
	 * @param request
	 * @param model
	 * @param vendor
	 * @param sessionObject
	 * @param update
	 * @param delete
	 * @return String
	 */
	@RequestMapping(value = "/updateVendorDetails", method = RequestMethod.POST)
	public String updateVendorDetails(HttpServletRequest request,ModelMap model, Vendor vendor,
										@ModelAttribute("sessionObject") SessionObject sessionObject,
										@RequestParam(required = false, value = "update") String update,
										@RequestParam(required = false, value = "delete") String delete) {
		try {
			logger.info("In updateVendorDetails method of CommonController.java");
			vendor.setUpdatedBy(sessionObject.getUserId());
			int updateStatus = 0;
			if(update!=null){
				updateStatus = commonService.updateVendorDetails(vendor);
			}
			if(delete!=null){
				updateStatus = commonService.deleteVendorDetails(vendor);
			}
			if(updateStatus !=0){
				model.addAttribute("successStatus", "Successfully updated");
			}else{
				model.addAttribute("failStatus", "Update failed");
			}
		}catch (Exception e) {
			logger.error("updateVendorDetails() In CommonController.java: Exception",e);
		}
		return listVendor(request,model);
	}
	
	/**
	 * Get Section Against Standard
	 */
	@RequestMapping(value = "/getStudentsAgainstStandardAndSection", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentsAgainstStandardAndSection(@RequestParam("standard") String standard,
												@RequestParam("section") String section) {
		String student = null;
		try {
			logger.info("getStudentsAgainstStandardAndSection() In CommonController.java");
			student = commonService.getStudentsAgainstStandardAndSection(standard, section);
		} catch (NullPointerException e) {
			logger.error("getStudentsAgainstStandardAndSection() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getStudentsAgainstStandardAndSection() In CommonController.java: Exception"
					+ e);
		}
		return student;
	}
	
	
	/**
	 * Get Section Against Standard
	 */
	@RequestMapping(value = "/getSubjectsStudiedByStudent", method = RequestMethod.GET)
	public @ResponseBody
	String getSubjectsStudiedByStudent(@RequestParam("rollNumber") String rollNumber) {
		String subjects = null;
		try {
			logger.info("getSubjectsStudiedByStudent() In CommonController.java");
			subjects = commonService.getSubjectsStudiedByStudent(rollNumber);
		} catch (NullPointerException e) {
			logger.error("getSubjectsStudiedByStudent() In CommonController.java: NullPointerException" + e);
		} catch (Exception e) {
			logger.error("getSubjectsStudiedByStudent() In CommonController.java: Exception" + e);
		}
		return (new Gson().toJson(subjects));
	}
	
	/**
	 * This Ajax call get city list for a country
	 * 
	 */
	@RequestMapping(value = "/getCityList", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getCity(@RequestParam("term") String strQuery) {
		logger.info("In getCity() Ajax method of BackOfficeController");
		logger.info("In BackOfficeController getCity() method: calling getCity(String strQuery) in BackOfficeService.java");
		List<String> cityList = commonService.getCity(strQuery);
		return (new Gson().toJson(cityList));
	}

	/**
	 * This Ajax call get city list for a District
	 * 
	 */
	@RequestMapping(value = "/getDistrictList", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getDistrict(@RequestParam("term") String strQuery) {
		logger.info("In getDistrict() Ajax method of BackOfficeController");
		logger.info("In BackOfficeController getDistrict() method: calling getDistrict(String strQuery) in BackOfficeService.java");
		List<String> districtList = commonService.getDistrict(strQuery);
		return (new Gson().toJson(districtList));
	}
	
	
	@RequestMapping(value = "/getNotificationDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getNotificationDetails(HttpServletRequest request,
								  HttpServletResponse response,
								  @ModelAttribute("sessionObject") SessionObject sessionObject,
								  @RequestParam("notificationId") String notificationId,
								  Notification notification
								  ) {			
		String returnVal = "";
		try{
			if(notificationId != null && notificationId.trim().length() !=0){
			notification.setNotificationId(Integer.parseInt(notificationId));
			}
			notification.setUpdatedBy(sessionObject.getUserId());
			notification = commonService.getNotificationDetails(notification);
			if(notification!=null){
				returnVal = returnVal+"#"+notification.getNotificationSender()+"#"+notification.getNotificationDesc();				
			}
		}catch(NullPointerException e){
			logger.error("getNotificationDetails() In CommonController.java: NullPointerException"+ e);	
		}catch(NumberFormatException e){
			logger.error("getNotificationDetails() In CommonController.java: NumberFormatException"+ e);	
		}	
		catch(Exception e){
			logger.error("getNotificationDetails() In CommonController.java: Exception"+ e);	
		}	
		return (new Gson().toJson(returnVal));			
	}	

	@RequestMapping(value = "/changeMailReadStatus", method = RequestMethod.GET)
	public @ResponseBody
	void changeMailReadStatus(@RequestParam("emailAlertCode") String emailAlertCode) {
		try {
			logger.info("changeMailReadStatus() In CommonController.java");
			commonService.changeMailReadStatus(emailAlertCode);
		}catch (Exception e) {
			logger.error("changeMailReadStatus() In CommonController.java: Exception",e);
		}				
	}	
	
	@RequestMapping(value = "/createNotification", method = RequestMethod.GET)
	public String createNotification(
									HttpServletRequest request,
									HttpServletResponse response,
									ModelMap model,
									@ModelAttribute("sessionObject") SessionObject sessionObject,
									Notification notification,
									@RequestParam(value = "replyTo", required=false) String replyTo
									
									) {	
		logger.info("In  createNotificatin() method of CommonController");
		try{
			
			if(sessionObject!=null && sessionObject.getUserId()!=null && sessionObject.getUserId().trim().length()!=0){
				//List<Resource> resourceList = commonService.getStaffUserIdList();
				// Role name set into userId in Resource
				List<Resource> resourceList = commonService.getGroupList();
				if(resourceList !=null && resourceList.size()!=0){
					List<NotificationDetails> notificationDetailsList = new ArrayList<NotificationDetails>();
					for(Resource resource: resourceList){
						if(resource !=null){
							if(!resource.getUserId().equalsIgnoreCase(sessionObject.getUserId())){
								NotificationDetails nd = new NotificationDetails();
								nd.setNotificationDetailsCode(resource.getCode());
								nd.setNotificationReceiver(resource.getUserId());
								if(replyTo!=null && replyTo.trim().length()!=0){										
									if(resource.getUserId().equalsIgnoreCase(replyTo.trim())){
										nd.setStatus("selected");
									}
								}
								notificationDetailsList.add(nd);
							}
						}
					}
					notification.setNotificationDetailsList(notificationDetailsList);
					if(replyTo!=null && replyTo.trim().length()!=0){	
						model.addAttribute("notificationReplyTo", replyTo);
					}
				}
				notification.setNotificationSender(sessionObject.getUserId());
			}
			model.addAttribute("notification", notification);
		}catch(NullPointerException e){
			logger.error("createNotificatin() In CommonController.java: NullPointerException", e);	
		}catch(Exception e){
			logger.error("createNotificatin() In CommonController.java: Exception", e);	
		}
		return "common/createNotification";
	}
	
	
	
	//Naimisha 22062017
	//Modiofied By Naimisha 13092017
	@RequestMapping(value = "/createNotification", method = RequestMethod.POST)
	public String createNotificatinPOST(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										@ModelAttribute("notification") Notification notification,
										@RequestParam(value = "emailBody", required=false) String emailBody,
										/*@RequestParam(value = "myFileInput", required=false) String attachment,*/
									/*	@RequestParam(value = "notificationTo", required=false) String notificationReciver,*/
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//System.out.println("method called");
		logger.info("In  createNotificatin() method of BackOfficeController");
		String filepathAndFile = "";
		try{
		
			
			System.out.println("meetingDate==="+notification.getMeetingDate());
			System.out.println("meeting location===="+notification.getMeetingLocation());
			System.out.println("startTime==="+notification.getMeetingStartTime());
			System.out.println("meetingDate==="+notification.getMeetingEndTime());
			String mailbody = request.getParameter("emailBody");
			if(null != notification.getStatus()){
				if(notification.getStatus().equalsIgnoreCase("ics")){
					if((null != notification.getMeetingDate()||!("".equalsIgnoreCase(notification.getMeetingDate())))&&(null != notification.getMeetingLocation()||!("".equalsIgnoreCase(notification.getMeetingLocation())))&& (null != notification.getMeetingStartTime()||!("".equalsIgnoreCase(notification.getMeetingStartTime())))&&( null != notification.getMeetingEndTime()||!("".equalsIgnoreCase(notification.getMeetingEndTime())))){
						String icsFileAndPath = generateICSFile(notification.getMeetingDate(),notification.getMeetingLocation(),notification.getMeetingStartTime(),notification.getMeetingEndTime(),notification.getNotificationSubject(),mailbody,notification.getNotificationReplyTo(),sessionObject.getUserId());
						System.out.println("icsFileAndPath====="+icsFileAndPath);
						if(null != icsFileAndPath){
							String[] icsFileArr = icsFileAndPath.split("\\*");
							notification.setIcsFilepath(icsFileArr[0]);
							notification.setIcsFile(icsFileArr[1]);
							notification.setAttachmentType("ICS");
						}
					}
				}
			}
			
				
		
		
			
			String insertstatus = "fail";
			String notificationReciver = notification.getNotificationReplyTo();
			if(notificationReciver!=null && notificationReciver.trim().length()!=0){
				String []reciverArray = notificationReciver.split(",");
				if(reciverArray !=null && reciverArray.length!=0){
					List<NotificationDetails> notificationDetailsList = new ArrayList<NotificationDetails>();
					for(String reciver: reciverArray){
						if(reciver!=null && reciver.length()!=0){	
							NotificationDetails nd =new NotificationDetails();
							nd.setNotificationReceiver(reciver);
							notificationDetailsList.add(nd);
						}
					}
					notification.setNotificationDesc(request.getParameter("emailBody"));
					notification.setNotificationDetailsList(notificationDetailsList);
				}
			}
			//notification.setEmailBodyTemplate(emilTemplateBasePath+emailBody);
			//notification.setEmailSubjectTemplate(emilTemplateBasePath+emailSubject);
			notification.setNotificationSubject(notification.getNotificationSubject());
			notification.setNotificationDesc(notification.getNotificationDesc());
			notification.setUpdatedBy(sessionObject.getUserId());
			notification.setNotificationSender(sessionObject.getUserId());
			
			
			//Added By Naimisha 15092017
			
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			File directory = new File(repository);
			boolean isExists = directory.exists();
			String insertStatus = null;
			if(isExists == true){
				//if(ticket.getTicketRecId() != null){
					Attachment attachment = new Attachment();
					//attachment.setStorageRootPath(rootPath);
					attachment.setStorageRootPath(repository);
					attachment.setFolderName(emailAttachmentsPath);					
					logger.info(attachment.getStorageRootPath()+":"+attachment.getFolderName());	
					if(null != notification.getUploadFile()){
						if(null != notification.getUploadFile().getEmailRelatedFile().get(0).getFileItem().getName()){ 
							notification.getUploadFile().setAttachment(attachment);
							 filepathAndFile = commonService.emailDocumentUpload(notification);
						}
					}
					
				//}
				//model.addAttribute("ticket", ticket);
			}
			
			System.out.println("filepathAndFile===="+filepathAndFile);
			if(null != filepathAndFile){
				String[] fileInform = filepathAndFile.split("\\*");
				System.out.println("fileInform=="+fileInform.length);
				notification.setEmailFilepath(fileInform[0]);
				List<String>fileList = new ArrayList<String>();
				if(fileInform.length>1){
					if(null != fileInform[1]){
						String[] fileArray = fileInform[1].split("\\#");
					
						for(int i=0;i<fileArray.length;i++){
							fileList.add(fileArray[i]);
						}
					}
					
					notification.setFileList(fileList);
					notification.setAttachmentType("Attachment");
				}
				
			}
			
			//End By Naimisha 15092017
			insertstatus = commonService.sendMailFromMyservice(notification);
			System.out.println("insertstatus===="+insertstatus);
			//insertstatus = "success";
		//	if(insertstatus .equalsIgnoreCase("success")){
				//***new code added for save this edited file into external repository**//
				
			//}
			//String status = null;
			if(insertstatus.equalsIgnoreCase("success")){
				model.addAttribute("status", insertstatus);
				model.addAttribute("updateStatus", "Mail Sent Successfully.");
			}else{
				model.addAttribute("status", insertstatus);
				model.addAttribute("updateStatus", "Mail Sending Failed!!");
			}
			
			System.out.println("emailBody===="+request.getParameter("emailBody"));
		}catch(NullPointerException e){
			e.printStackTrace();
			logger.error("createNotificatin() In BackOfficeController.java: NullPointerException", e);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("createNotificatin() In BackOfficeController.java: Exception", e);	
		}
		return createNotification(request,response,model,sessionObject,notification,"");
	}
	
	
	private String generateICSFile(String meetingDate, String meetingLocation, String meetingStartTime,
			String meetingEndTime, String mailSubjet, String mailBody, String mailTo, String mailSentBy) {
		
		String []reciverArray = null;
		System.out.println("meetingDate==="+meetingDate);
		System.out.println("meeting location===="+meetingLocation);
		System.out.println("startTime==="+meetingStartTime);
		System.out.println("meetingEndTime==="+meetingEndTime);
		System.out.println("mailSubjet==="+mailSubjet);
		System.out.println("mailBody==="+mailBody);
		System.out.println("mailTo==="+mailTo);
	
		if(mailTo!=null && mailTo.trim().length()!=0){
			reciverArray = mailTo.split(",");
			
		}
		
		
		String[] dateArr = meetingDate.split("/");
		//String monthValue = getMonthForInt(Integer.parseInt(dateArr[1]));
		
		//System.out.println("monthValue=="+monthValue);
		//String temp = "java.util.Calendar."+monthValue.toUpperCase();
		
		String startTime = get24HrFormatFrom12HrFormat(meetingStartTime);
		String[] startTimeArr = startTime.split(":");
		int startMonthValue = Integer.parseInt(dateArr[1]) -1;;
		java.util.Calendar startDate =  java.util.Calendar.getInstance();
		//startDate.set(java.util.Calendar.MONTH, java.util.Calendar.MAY); 
		startDate.set(java.util.Calendar.MONTH,startMonthValue);
		startDate.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(dateArr[0]));
		startDate.set(java.util.Calendar.YEAR, Integer.parseInt(dateArr[2]));
		startDate.set(java.util.Calendar.HOUR_OF_DAY, Integer.parseInt(startTimeArr[0]));
		startDate.set(java.util.Calendar.MINUTE, Integer.parseInt(startTimeArr[1]));
		startDate.set(java.util.Calendar.SECOND, 0);
		
		
		
		// End Date is on: MAY 16, 2017, 13:00
		
		String endTime = get24HrFormatFrom12HrFormat(meetingEndTime);
		String[] endTimeArr = endTime.split(":");
		int endMonthValue = Integer.parseInt(dateArr[1]) -1;
		java.util.Calendar endDate = java.util.Calendar.getInstance();
		endDate.set(java.util.Calendar.MONTH, endMonthValue);
		endDate.set(java.util.Calendar.DAY_OF_MONTH, Integer.parseInt(dateArr[0]));
		endDate.set(java.util.Calendar.YEAR, Integer.parseInt(dateArr[2]));
		endDate.set(java.util.Calendar.HOUR_OF_DAY,Integer.parseInt(endTimeArr[0]));
		endDate.set(java.util.Calendar.MINUTE, Integer.parseInt(endTimeArr[1]));	
		endDate.set(java.util.Calendar.SECOND, 0);
		
		// Create the event
		String eventName = mailSubjet;
		DateTime start = new DateTime(startDate.getTime());
		DateTime end = new DateTime(endDate.getTime());
		String agenda = mailBody;
		
		VEvent meeting = new VEvent(start, end, eventName);
		
		// generate unique identifier..
		UidGenerator ug = null;
		try {
			ug = new UidGenerator("uidGen");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Uid uid = ug.generateUid();
		meeting.getProperties().add(uid);
		

		// add description
		Description description = new Description(agenda);
		meeting.getProperties().add(description);
		
		// add organiser
		Organizer organizer = new Organizer(URI.create(mailSentBy));
		meeting.getProperties().add(organizer);
		
		Location location = new Location(meetingLocation);
		meeting.getProperties().add(location);
		
		for(int i=0;i<reciverArray.length;i++){
			Attendee dev1 = new Attendee(URI.create(reciverArray[i]));
			dev1.getParameters().add(Role.REQ_PARTICIPANT);
			dev1.getParameters().add(new Cn(reciverArray[i]));
			meeting.getProperties().add(dev1);
		}
		
		// Create a calendar
		net.fortuna.ical4j.model.Calendar icsCalendar = new net.fortuna.ical4j.model.Calendar();
		icsCalendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 1.0//EN"));
		icsCalendar.getProperties().add(Version.VERSION_2_0);
		icsCalendar.getProperties().add(Method.REQUEST);
		icsCalendar.getProperties().add(CalScale.GREGORIAN);


		// Add the event and print
		icsCalendar.getComponents().add(meeting);
		System.out.println(icsCalendar);
		
		FileOutputStream fout = null;
		String fullPath = "";
	//	String filePathAndFile = "";
		try {
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				File directory = new File(repository);
				boolean isExists = directory.exists();
				String insertStatus = null;
				if(isExists == true){
					//emailAttachmentsPath
					
					Date date =new Date();
				    long epoch = date.getTime();
				     fullPath = repository+"/"+emailAttachmentsPath+"sent/ics/"+mailSentBy+"/"+epoch+"/";
				  
				    File f = new File(fullPath);
				    boolean isCreated = f.mkdirs();
				    fout = new FileOutputStream(fullPath+mailSentBy+".ics");
				}
				// filePathAndFile = fullPath+"*"+mailSentBy+".ics";
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CalendarOutputter outputter = new CalendarOutputter();
		try {
			outputter.output(icsCalendar, fout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fullPath+"*"+mailSentBy+".ics";
	}

	
	private String getMonthForInt(int m) {
	    String month = "invalid";
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (m >= 0 && m <= 11 ) {
	        month = months[m];
	    }
	    return month;
	}
	
	private String get24HrFormatFrom12HrFormat(String time){
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
	    SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
	    Date date = null;
		try {
			date = parseFormat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       //System.out.println(parseFormat.format(date) + " = " + displayFormat.format(date));
		return displayFormat.format(date);
	  }
	
	@RequestMapping(value = "/getUserIdToCreateNotification", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getUserIdToCreateNotification(@RequestParam("term") String strQuery) {
		logger.info("In getUserIdToCreateNotification() Ajax method of BackOfficeController");
		logger.info("In BackOfficeController getCity() method: calling getCity(String strQuery) in BackOfficeService.java");
		List<String> userIdList = commonService.getUserIdToCreateNotification(strQuery);
		return (new Gson().toJson(userIdList));
	}
	
	
	/**@author Saif.Ali
	 * Modified on 19/07/2017
	 * Used to create the ASTB for the Inventory Section*/
	@RequestMapping(value = "/createASTB", method = RequestMethod.GET)
	public ModelAndView createASTB(HttpServletRequest request, HttpServletResponse response,
										ModelMap model) {
		logger.info("calling createASTB() method of CommonController");
		ModelAndView mav = new ModelAndView("common/createASTB");
		List<AnnualStock> annualStockList = null;
		try {
			FinancialYear financialYear = commonService.getFinancialYear();
			annualStockList = commonService.selectAssetsForDepartment();
			if(null != annualStockList && annualStockList.size() != 0){
				model.addAttribute("annualStockList",annualStockList);
				model.addAttribute("submitType","INSERTSTOCK");
				model.addAttribute("financialYear",financialYear);
			}
			logger.info("In CommonController createASTB() method");					
		}catch (Exception e) {
			logger.error("Error in CommonController createASTV() method for Exception: ",e);
		}
		return mav;				
	}

	
	/**@author Saif.Ali
	 * Date- 19/07/2017
	 * used to submit the created ASTB for the Inventory section*/
	@RequestMapping(value = "/submitASTB", method = RequestMethod.POST)
	public ModelAndView submitASTV(HttpServletRequest request, HttpServletResponse response,
											 ModelMap model, AnnualStockList annualStockList,
											 @ModelAttribute("sessionObject") SessionObject sessionObject,
											 @RequestParam("submitType") String submitType) {
		logger.info("calling submitASTB() method of CommonController");
		try {
			if(null != annualStockList.getListAnnualStock() && annualStockList.getListAnnualStock().size() != 0){
				logger.info("List Size :: "+annualStockList.getListAnnualStock().size());
				annualStockList.setUpdatedBy(sessionObject.getUserId());
				if(null != submitType){
					String insertStatus = commonService.submitASTV(annualStockList);
					if (null != insertStatus) {
							model.addAttribute("insertStatus",insertStatus);
						
					}
				}				
			}
			logger.info("In CommonController submitASTV() method");					
		}catch (Exception e) {
			logger.error("Error in CommonController submitASTV() method for Exception: ",e);
		}
		return createASTB(request, response, model);				
	}

	
	/**@author Saif.Ali
	 * date- 20/07/2017
	 * Used to create or update the Condemnation*/
	@RequestMapping(value = "/createUpdateCondemnation", method = RequestMethod.GET)
	public ModelAndView createUpdateCondemnation(HttpServletRequest request, ModelMap model) {
		logger.info("calling createUpdateCondemnation() method of CommonController");
		ModelAndView mav = new ModelAndView("common/createUpdateCondemnation");
		List<Condemnation> condemnationList = null;
		try {
							com.qts.icam.model.common.FinancialYear financialYear = commonService.getFinancialYear();
							condemnationList = commonService.listASTVForCondemnation();
							if(null != condemnationList && condemnationList.size() != 0){
								//model.addAttribute("department",department);
								model.addAttribute("condemnationList",condemnationList);
								model.addAttribute("submitType","INSERTSTOCK");
								model.addAttribute("financialYear",financialYear);
							}	
					
			logger.info("In CommonController createUpdateCondemnation() method");					
		}catch (Exception e) {
			logger.error("Error in CommonController createUpdateCondemnation() method for Exception: ",e);
		}
		return mav;				
	}
		
	
	/**@author Saif.Ali
	 * date-20/07/2017
	 * Used to insert the condemnation*/
	@RequestMapping(value = "/submitCondemnation", method = RequestMethod.POST)
	public ModelAndView submitCondemnation(HttpServletRequest request, HttpServletResponse response,
											 ModelMap model, CondemnationList condemnationList,
											 @ModelAttribute("sessionObject") SessionObject sessionObject,
											 @RequestParam("submitType") String submitType) {
		logger.info("calling submitCondemnation() method of CommonController");
		try {
			if(null != condemnationList.getListCondemnationStock() && condemnationList.getListCondemnationStock().size() != 0){
				logger.info("List Size :: "+condemnationList.getListCondemnationStock().size());
				condemnationList.setUpdatedBy(sessionObject.getUserId());
				if(null != submitType){
					String insertStatus = commonService.submitCondemnation(condemnationList);
					if (null != insertStatus) {
						model.addAttribute("insertStatus",insertStatus); 
					}
				}				
			}
			logger.info("In CommonController submitCondemnation() method");					
		}catch (Exception e) {
			logger.error("Error in CommonController submitCondemnation() method for Exception: ",e);
		}
		return createUpdateCondemnation(request, model);				
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param resource
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewStaffProfileDetails", method = RequestMethod.GET)
	public ModelAndView viewStaffProfileDetails(HttpServletRequest request, HttpServletResponse response, Employee employee, ModelMap model, 
			@RequestParam(required = false, value = "userId") String userId) {
		logger.info("In viewStaffProfileDetails() method of CommonController: ");
		String strView = null;
		try {
			Resource resource = new Resource();
			resource.setUserId(userId);
			employee.setResource(resource);
			Employee employeeDetails = erpService.getEmployeeDetails(employee);	
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			if(employeeDetails != null ){
				String path = rootPath + employeeDocFolderPath +employeeDetails.getResource().getUserId()+"/qualification_doc/";
				File filedir = new File(path);
				if (!filedir.exists()) {					
				}
				else{
					File folder = new File(path);
					File[] listOfFiles = folder.listFiles();
	
					for (int i = 0; i < listOfFiles.length; i++) {
						File file = listOfFiles[i];
						if (file.isFile()) {
							Attachment attch = new Attachment();
							attch.setAttachmentType("qualification_doc");
							attch.setAttachmentName(file.getName());
							attachmentList.add(attch);					 				 
						} 
					}				
				}
			}
			
			if(employeeDetails != null ){
				String path = rootPath + employeeDocFolderPath +employeeDetails.getResource().getUserId()+"/previous_organization_doc/";
				File filedir = new File(path);
				if (!filedir.exists()) {					
				}
				else{
					File folder = new File(path);
					File[] listOfFiles = folder.listFiles();
	
					for (int i = 0; i < listOfFiles.length; i++) {
						File file = listOfFiles[i];
						if (file.isFile()) {
							Attachment attch = new Attachment();
							attch.setAttachmentType("previous_organization_doc");
							attch.setAttachmentName(file.getName());
							attachmentList.add(attch);					 				 
						} 
					}				
				}
			}
			
			if(employeeDetails != null ){
				String path = rootPath + employeeDocFolderPath +employeeDetails.getResource().getUserId()+"/publication_doc/";
				File filedir = new File(path);
				if (!filedir.exists()) {					
				}
				else{
					File folder = new File(path);
					File[] listOfFiles = folder.listFiles();
	
					for (int i = 0; i < listOfFiles.length; i++) {
						File file = listOfFiles[i];
						if (file.isFile()) {
							Attachment attch = new Attachment();
							attch.setAttachmentType("publication_doc");
							attch.setAttachmentName(file.getName());
							attachmentList.add(attch);					 				 
						} 
					}				
				}
			}
			if(null != attachmentList && attachmentList.size()!= 0){
				employeeDetails.setAttachmentList(attachmentList);	
			}			
			model.addAttribute("employeeDetails", employeeDetails);
						
		} catch (NullPointerException e) {			
			logger.error("Error in CommonController viewStaffProfileDetails() method for NullPointerException: ", e);
			
		} catch (Exception e) {
			logger.error("Error in CommonController viewStaffProfileDetails() method for Exception: ", e);
			
		}finally{
		strView = "common/viewStaffProfileDetails";
		}
		return new ModelAndView(strView);
	}
	
	
	
	/**
	 * access this method at the time of server startup after bean initialization
	 */
	@PostConstruct
	public void init() {
		if(!validateLicenseAgreement())
			System.exit(1);	
	}
	
	
	/**
	 * this method get license details from DB and call authenticateServer() for client server
	 * authentication
	 * 
	 * @return boolean
	 */
	public boolean validateLicenseAgreement(){
		boolean licenseAgreementStatus = false;
		try{
			/*
			 * db call to fetch license data 
			 */
			LicenseInfo licenseInfo = commonService.validateLicenseDetails();
			logger.info("@-@: "+licenseInfo);
			/*System.out.println("licenseInfo===="+licenseInfo.getInstallationTimeStamp());
			//Date date = new Date();
			 int dateInteger = (int) (new Date().getTime()/1000);
			 int dateAfterTwoMonth = 1497897000;
			 System.out.println("dateInteger======"+dateInteger);
			 if(dateInteger<=dateAfterTwoMonth){
				 licenseAgreementStatus = true;
			 }*/
			if(licenseInfo!=null)
				licenseAgreementStatus=true;
		}catch(Exception e){
			logger.error("Exception in validateLicenseAgreement() method at CommonController", e);
			
		}
		return licenseAgreementStatus;
	}
	
	@RequestMapping(value = "/getActivityLog", method = RequestMethod.GET)
	public String getActivityLog(HttpServletRequest request, ModelMap model,
										@RequestParam("module") String module,
										@RequestParam("functionality") String functionality) {
		logger.info("calling getActivityLog() method of CommonController");		
		try {
			logger.info(module);
			logger.info(functionality);
			commonService.getActivityLog(module, functionality);
			logger.info("In CommonController getActivityLog() method");					
		}catch (Exception e) {
			logger.error("Error in CommonController getActivityLog() method for Exception: ",e);
		}
		return activityLogPagination(request, model);				
	}
	
	@RequestMapping(value = "/activityLogPagination", method = RequestMethod.GET)
	public String activityLogPagination(HttpServletRequest request,ModelMap model) {
		try {
			PagedListHolder<UpdateLog> updateLogListHolder = commonService.activityLogPagination(request);	
			if (updateLogListHolder != null && updateLogListHolder.getNrOfElements()!=0) {
				model.addAttribute("first", updateLogListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", updateLogListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", updateLogListHolder.getNrOfElements());
				model.addAttribute("updateLogListHolder", updateLogListHolder);
			}
		}catch(Exception e){
			logger.error("Exception in activityLogPagination() in CommonController: ", e);
		}
		return "common/updateLog";
	}
	
	
	@RequestMapping(value="/viewAssetType", method = RequestMethod.GET)
	public String viewAssetType(HttpServletRequest request, ModelMap model){
		List<AssetType> assetTypeList = null;
		try{
			logger.info("In viewAssetType() of InventoryController.");
			assetTypeList = commonService.getAllAssetType();					
			model.addAttribute("assetTypeList", assetTypeList);
		}catch(Exception e){
			logger.error("Exception in viewAssetType() of CommonController : ",e);
		}		
		return "common/addAssetType";
	}
	
	@RequestMapping(value="/submitAssetType", method = RequestMethod.POST)
	public String addAssetType(HttpServletRequest request, HttpServletResponse response, ModelMap model,
									@ModelAttribute("AssetType") AssetType assetType,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("In addAssetType() of CommonController");
			if(null != assetType){
				assetType.setUpdatedBy(sessionObject.getUserId());			
				String submitResponse = commonService.addAssetType(assetType);
				model.addAttribute("submitResponse", submitResponse);
			}
		}catch(Exception e){
			logger.error("Exception in addAssetType() of CommonController : ",e);
		}		
		return viewAssetType(request, model);
	}
	
	@RequestMapping(value="/editAssetType", method = RequestMethod.POST)
	public String editAssetType(HttpServletRequest request, HttpServletResponse response, ModelMap model,
									@ModelAttribute("AssetType") AssetType assetType,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("In editAssetType() of CommonController");
			if(null != assetType){
				assetType.setUpdatedBy(sessionObject.getUserId());			
				String updateResponse = commonService.editAssetType(assetType);
				model.addAttribute("updateResponse", updateResponse);
			}
		}catch(Exception e){
			logger.error("Exception occured in editAssetType() of CommonController : ",e);
		}		
		return viewAssetType(request, model);
	}
	
	@RequestMapping(value="/viewAssetSubType", method = RequestMethod.GET)
	public String viewAssetSubType(HttpServletRequest request, ModelMap model){
		List<AssetType> assetTypeList = null;
		List<AssetSubType> assetSubTypeList = null;
		try{
			logger.info("In viewAssetSubType() of CommonController.");
			assetTypeList = commonService.getAllAssetType();
			if(null != assetTypeList && assetTypeList.size() != 0){
				model.addAttribute("assetTypeList", assetTypeList);				
			}
			assetSubTypeList = commonService.getAllAssetSubType();					
			model.addAttribute("assetSubTypeList", assetSubTypeList);
		}catch(Exception e){
			logger.error("Exception in viewAssetSubType() of CommonController : ",e);
		}		
		return "common/addAssetSubType";
	}
	
	@RequestMapping(value="/submitAssetSubType", method = RequestMethod.POST)
	public String addAssetSubType(HttpServletRequest request, HttpServletResponse response, ModelMap model,
									@ModelAttribute("AssetSubType") AssetSubType assetSubType,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("In addAssetSubType() of CommonController");
			if(null != assetSubType){
				assetSubType.setUpdatedBy(sessionObject.getUserId());			
				String submitResponse = commonService.addAssetSubType(assetSubType);
				model.addAttribute("submitResponse", submitResponse);
			}
		}catch(Exception e){
			logger.error("Exception in addAssetSubType() of CommonController : ",e);
		}		
		return viewAssetSubType(request, model);
	}
	
	@RequestMapping(value="/editAssetSubType", method = RequestMethod.POST)
	public String editAssetSubType(HttpServletRequest request, HttpServletResponse response, ModelMap model,
									@ModelAttribute("AssetSubType") AssetSubType assetSubType,
									@ModelAttribute("sessionObject") SessionObject sessionObject,
									@RequestParam(required = false, value = "editAssetSubType") String updateFlag,
									@RequestParam(required = false, value = "deleteAssetSubType") String deleteFlag){
		try{
			logger.info("In editAssetSubType() of CommonController");
			if(null != updateFlag){
				if(null != assetSubType){
					assetSubType.setUpdatedBy(sessionObject.getUserId());			
					String updateResponse = commonService.editAssetSubType(assetSubType);
					model.addAttribute("updateResponse", updateResponse);
				}
			}if(null != deleteFlag){
				if(null != assetSubType){
					assetSubType.setUpdatedBy(sessionObject.getUserId());			
					String updateResponse = commonService.deleteAssetSubType(assetSubType);
					model.addAttribute("updateResponse", updateResponse);
				}
			}
		}catch(Exception e){
			logger.error("Exception occured in editAssetSubType() of CommonController : ",e);
		}		
		return viewAssetSubType(request, model);
	}	
	
	
	@RequestMapping(value = "/getAssetSubTypeForAssetType", method = RequestMethod.GET)
	public @ResponseBody
	String getAssetSubTypeForAssetType(@RequestParam("assetType") String assetType) {
		String assetSubType = null;
		try {
			assetSubType = commonService.getAssetSubTypeForAssetType(assetType);
		} catch (Exception e) {
			logger.error("Exception In checkCommodityName() method of CommonController: Exception",e);
			
		}
		return (new Gson().toJson(assetSubType));
	}
	
	/**
	 * @author naimisha.ghosh
	 * */
	
	@RequestMapping(value = "/viewAttendance", method = RequestMethod.GET)
	public String viewAttendance(
					HttpServletRequest request,
					HttpServletResponse response,
					ModelMap model,
					@ModelAttribute("sessionObject") SessionObject sessionObject
					) {				
		return "common/viewAttendance";
	}
	
	
	@RequestMapping(value = "/viewAttendanceDetails", method = RequestMethod.POST)
	public String viewAttendanceDetails(
					HttpServletRequest request,
					HttpServletResponse response,
					ModelMap model,						
					StudentAttendance attendance,
					@ModelAttribute("sessionObject") SessionObject sessionObject
					) {	
		try{
			ResourceType resourceType =new ResourceType();
			resourceType.setResourceTypeName(sessionObject.getResourceTpye());				
			attendance.setResourceId(sessionObject.getUserId());				
			attendance.setResourceType(resourceType);
		//	attendance=commonService.viewAttendanceDetails(attendance);
			model.addAttribute("attendance", attendance);
		}catch(Exception e){
			logger.error("Exception in getEmailContent() in CommonController: ", e);
			e.printStackTrace();
		}
		return "common/viewAttendance";
	}
	
	
	/**
	 * copied from ticketcontroller
	 * */
	
	@RequestMapping(value = "/listPersonalLeaveHistoryForMyService", method = RequestMethod.GET)
	public ModelAndView listPersonalLeaveHistory(HttpServletRequest request, 
												HttpServletResponse response, ModelMap model, 
												@ModelAttribute("sessionObject") SessionObject sessionObject){

		String strView = null;
		List<Leave> leaveHistoryList = new ArrayList<Leave>();
		try {
			strView = "common/leaveHistory";
			leaveHistoryList = commonService.getLeaveHistory(sessionObject.getUserId());
		} catch (Exception e) {
			logger.error("Exception In listPersonalLeaveHistory() of ERPController", e);
		}
		model.addAttribute("leaveHistoryList", leaveHistoryList);
		return new ModelAndView(strView);
	}
	
	
	
	@RequestMapping(value = "/applyForLeave", method = RequestMethod.GET)
	public ModelAndView applyForLeave(HttpServletRequest request,
											HttpServletResponse response, ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		List<Leave> leaveDetailsList = new ArrayList<Leave>();
		//List<Approver> approverGroupList = new ArrayList<Approver>();
		String approverGroupName = null;
		try {
			logger.info("In applyForLeave() method of TicketController");
			strView = "common/applyForLeave";	
			leaveDetailsList = commonService.getLeaveDetails();
			
			String user_id = sessionObject.getUserId();
			
			 approverGroupName = commonService.getApprovarGroupName(user_id);
			
					
		}catch (Exception e) {
			logger.error("applyForLeave() In TicketController.java: Exception", e);
		}
		
		model.addAttribute("leaveDetailsList", leaveDetailsList);
		model.addAttribute("approverGroupName", approverGroupName);
		
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/applyForLeave", method = RequestMethod.POST)
	public ModelAndView insertAppliedLeave(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam(value = "title") String jobName,		
			Leave leave) {	
		int insertStatus = 0;
		try {
			
			if(jobName.equalsIgnoreCase("Leave")){
				leave.setUpdatedBy(sessionObject.getUserId());
				leave.setStatus("PENDING");
				leave.setTitle(jobName);
				 insertStatus = commonService.insertIntoApprovalAndLeave(leave);
				if (0 != insertStatus) {
					model.addAttribute("successMessage", "Leave Created Successfully");
					} else {
						model.addAttribute("errorMessage", "Failed To Create Leave");			
					}
				}	
			
			
		} catch (Exception e) {
			logger.error("Exception in insertAppliedLeave() method in AdministratorController: ",e);
		}
		return applyForLeave(request,response,model,sessionObject);
	}
	
	@RequestMapping(value = "/leaveRequest", method = RequestMethod.GET)
	public String leaveRequest(HttpServletRequest request, 
									 HttpServletResponse response, 
									 ModelMap model,
									 @ModelAttribute("sessionObject") SessionObject sessionObject){
		try {
			logger.info("In leaveRequest() of ERPController");			
			List<Leave> staffLeaveDetails = commonService.getResourceLeaveDetails(sessionObject.getUserId());
			if(staffLeaveDetails != null && staffLeaveDetails.size() != 0){		
				model.addAttribute("staffLeaveDetails", staffLeaveDetails);	
			}
		} catch (Exception e) {
			logger.error("Exception In leaveRequest() of ERPController", e);
		}
		return "common/leaveRequest";
	}
	
	/*******************Modified By Naimisha 11042018************************/
	
	@RequestMapping(value = "/generateTicket", method = RequestMethod.GET)
	public ModelAndView generateTicketGET(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In generateTicketGET() method of TicketController");
			
			Department departmentObj = commonService.getDepartmentForAUser(sessionObject.getUserId()); //Added by naimisha 11042018
			model.addAttribute("departmentObj", departmentObj);
			
			List<JobType> jobDetailsList = new ArrayList<JobType>();
			ResourceType rType = commonService.getResourceTypeofUser(sessionObject.getUserId());
			if (rType != null){
				model.addAttribute("resourceType", rType.getResourceTypeName());				
			}
			List<Resource> staffList = commonService.getStaffUserIdList();
			if (staffList != null && staffList.size() != 0) {
				model.addAttribute("userIdList", staffList);
				model.addAttribute("reportedBy", sessionObject.getUserId());
			}
			List<ServiceType> serviceTypeList = commonService.getServiceTypeList();
			if (serviceTypeList != null && serviceTypeList.size() != 0) {
				model.addAttribute("serviceTypeList", serviceTypeList);				
			}
			/*jobDetailsList = administratorService.getAllJobDetails();
			model.addAttribute("jobDetailsList", jobDetailsList);*/	
			List<Standard> standardList = academicsService.getStandardsForSetExamMarks();  //Added by naimisha 20022018
			model.addAttribute("standardList", standardList);
	
			
			List<JobType> categoryList = administratorService.getCategoryListForADepartment(departmentObj.getDepartmentCode());
			if(null != categoryList && categoryList.size() !=0){
				model.addAttribute("categoryList", categoryList.get(0).getTaskList());
			}
			
			
			List<Department> departmentList = erpService.getAllDepartment();	//Added by naimisha 05042018
			model.addAttribute("departmentList", departmentList);
			
			String type = "OPEN";
			List<TicketStatus> ticketStatusObjList = commonService.getTicketStatusListForAType(type);
			model.addAttribute("ticketStatusObjList",ticketStatusObjList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("generateTicketGET() In TicketController.java: Exception"+ e);
		}
		return new ModelAndView("common/generateTicket");
	}
	
	@RequestMapping(value = "/generateTicket", method = RequestMethod.POST)
	public ModelAndView generateTicketPOST(HttpServletRequest request,
										   HttpServletResponse response, 
										   ModelMap model, 										   
										   Ticket ticket,
										   @ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In generateTicketPOST() method of TicketController");	
			ticket.setUpdatedBy(sessionObject.getUserId());
			String surveyId = request.getParameter("survey");
			String[] questionId = request.getParameterValues("question");
		//	String[] answer = request.getParameterValues("answer");
			System.out.println("surveyId===="+surveyId);
			System.out.println("serviceType ===="+ticket.getTicketService().getTicketServiceCode());
			
			//Added By Naimisha 05042018
			
			String[] keys = request.getParameterValues("key");
			
			List<TaskComment>taskCommentList = new ArrayList<>();
			if(null != keys){
				//if(ticket.getStatus().equalsIgnoreCase("success")){
					for(int j = 0;j<keys.length;j++){
						String value = request.getParameter(keys[j]+"key");
						TaskComment taskComment = new TaskComment();
						taskComment.setTaskCommentCode(keys[j]);
						taskComment.setTaskCommentDesc(value);
						taskCommentList.add(taskComment);
					}
					//String keyInsertStatus = commonService.insertIntoKeyTicketMapping(ticketobj);
				//}
					ticket.setTaskCommentList(taskCommentList);
			}
			
			
			Ticket ticketobj = commonService.generateTicket(ticket);
			
			ticket.setTicketCode(ticketobj.getTicketCode());
			ticket.setStatus(ticketobj.getStatus());
			List<Answer> answerList = new ArrayList<Answer>();
			if(null != questionId){
				if(ticket.getStatus().equalsIgnoreCase("success")){
					Question question = new Question();
					question.setUpdatedBy(sessionObject.getUserId());
					question.setSurveyId(surveyId);
					for(int i=0;i< questionId.length; i++){
						Answer answerObj = new Answer();
						answerObj.setQuestionId(questionId[i]);
						String answer = request.getParameter("answer"+i);
						//answerObj.setAnswerId(answer[i]);
						answerObj.setAnswerId(answer);
						answerList.add(answerObj);
					}
					question.setAnswerList(answerList);
					
					String insertStatus = commonService.insertTicketSurvey(question);
				}
			}
			
			
			
			if(ticket != null){
				/***new code added for save this edited file into external repository**/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				File directory = new File(repository);
				boolean isExists = directory.exists();
				String insertStatus = null;
				if(isExists == true){
					//if(ticket.getTicketCode() != null){
						Attachment attachment = new Attachment();
						//attachment.setStorageRootPath(rootPath);
						attachment.setStorageRootPath(repository);
						attachment.setFolderName(ticketAttachmentsPath);					
						logger.info(attachment.getStorageRootPath()+":"+attachment.getFolderName());					
						if(ticket.getUploadFile()!=null){ 
							ticket.getUploadFile().setAttachment(attachment);
							commonService.ticketDocumentUpload(ticket);
						}
					//}
					model.addAttribute("ticket", ticket);
				}else{
					//System.out.println("directory not found");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("generateTicketPOST() In TicketController.java: Exception"+ e);
		}
		return generateTicketGET(request, response, model, sessionObject);
	}
	
//changes 020616	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/listTicket", method = RequestMethod.GET)
	public ModelAndView listTicketGET(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = new ModelAndView("common/listTicket");
		try {
			logger.info("In listTicketGET() method of TicketController");
			List<Ticket> ticketList = commonService.getTicketList(sessionObject.getUserId());
			model.addAttribute("ticketList", ticketList);
		} catch (Exception e) {
			logger.error("listTicketGET() In TicketController.java: Exception"+ e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/closedTicketList", method = RequestMethod.GET)
	public ModelAndView closedTicketList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = new ModelAndView("common/closedTicketList");
		try {
			logger.info("In closedTicketList() method of TicketController");
			List<Ticket> ticketList = commonService.getClosedTicketList(sessionObject.getUserId());
			model.addAttribute("ticketList", ticketList);
			/*if (ticketList != null && ticketList.size() != 0) {
				logger.info("In TicketController closedTicketList() method: calling closedTicketList(HttpServletRequest request) in TicketService.java");
				PagedListHolder<Ticket> pagedListHolder = commonService.closedTicketListPaging(request);
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", ticketList);
				}*/
		} catch (Exception e) {
			logger.error("closedTicketList() In TicketController.java: Exception"+ e);
		}
		return mav;
	}
	
	
	//Modified By Naimisha 24102017
	@RequestMapping(value = "/editTicket", method = RequestMethod.GET)
	public String editTicket(HttpServletRequest request,
								   HttpServletResponse response, ModelMap model, Ticket ticket,
								   @ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = new ModelAndView();	
		logger.info("TKT DETAILS:"+ticket.getTicketCode());
		List<Task> taskList = new ArrayList<>();
		try {
			logger.info("In editTicket() method of TicketController");
			String ticketCodeValue = request.getParameter("ticketCode");
			String ticketServiceCodeValue = request.getParameter("ticketServiceCode");
			String statusType = request.getParameter("status");
			System.out.println("ticketCode=="+ticketCodeValue);
			System.out.println("ticketServiceCodeValue=="+ticketServiceCodeValue);
			System.out.println("statusType==="+statusType);
			
			if(ticketServiceCodeValue.equalsIgnoreCase("StudentLeave")){
				Ticket stuLeaveDetailsObj = commonService.getStudentLeaveDetailsAgainstTicket(ticketCodeValue);
				if(null != stuLeaveDetailsObj){
					System.out.println("standard==="+stuLeaveDetailsObj.getStandard());
					//PRAD JUNE 12 2018
					stuLeaveDetailsObj.setUrl(sendLeaveDetailsOfCadet);	
					stuLeaveDetailsObj.setUserName(portalUserName);
					stuLeaveDetailsObj.setPassword(portalPassWord);
					stuLeaveDetailsObj.setWebIQAvailable(isWebIQAvailable);
					List<StudentAttendance> studentAttendanceList = getStudentAttandanceList(stuLeaveDetailsObj);//for student leave
					stuLeaveDetailsObj.setStudentAttendanceList(studentAttendanceList);
					
					//PRAD ENDS
					model.addAttribute("stuLeaveDetailsObj", stuLeaveDetailsObj);
				}
			}else if(ticketServiceCodeValue.equalsIgnoreCase("InventoryPO")){  //Added By Naimisha 29032018
				
				CommodityPurchaseOrder commodityPurchaseOrder = commonService.getCommodityPurchaseOrderDetaialsForATicket(ticketCodeValue);
				if(null != commodityPurchaseOrder){
					model.addAttribute("commodityPurchaseOrder", commodityPurchaseOrder);
				}
			}
			
			ticket.setTicketCode(ticketCodeValue);
			ticket.setUpdatedBy(sessionObject.getUserId());
			ticket = commonService.getTicketDetails(ticket);
			List<Task> taskListForTicket = commonService.getTaskListForATicket(ticketCodeValue); // Added by naimisha 24102017
			if(null == taskListForTicket || taskListForTicket.size()==0){
				taskList = commonService.getTaskListForACategory(ticketServiceCodeValue);
			}
		//	if(null != taskList && taskList.size() !=0){
				model.addAttribute("taskList", taskList);
			//}
			
			//if(null != taskListForTicket && taskListForTicket.size() !=0){
				model.addAttribute("taskListForTicket", taskListForTicket);
		//	}
			
			List<JobType> allTaskList = administratorService.getAllJobDetails();
			model.addAttribute("allTaskList", allTaskList);
			
			
			
			List<Approver>recipientGroupList = administratorService.getAllApproverGroups();
			model.addAttribute("recipientGroupList",recipientGroupList);
			
					
			Resource resource = administratorService.getResourceAndRolesFromDB();
			List<ResourceType>resourceTypeList = resource.getResourceTypeList();
			if(null!=resource){
				model.addAttribute("resourceTypeList",resourceTypeList);
			}
			
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			if(ticket != null ){
				/***new code added for save this edited file into external repository**/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				File directory = new File(repository);
				boolean isExists = directory.exists();
				String insertStatus = null;
				if(isExists == true){
					//String path = rootPath + ticketAttachmentsPath +ticket.getTicketService().getTicketServiceCode()+"/ticket_doc/"+ ticket.getTicketCode()+"/";
					String path = repository + "/"+ ticketAttachmentsPath + ticket.getTicketCode()+"/";
					//String path = repository+"/"+ticketAttachmentsPath + ticket.getTicketRecId()+"/";
					//System.out.println("path======="+path);
					File filedir = new File(path);
					if (!filedir.exists()) {					
					}
					else{
						File folder = new File(path);
						File[] listOfFiles = folder.listFiles();
		
						for (int i = 0; i < listOfFiles.length; i++) {
							File file = listOfFiles[i];
							if (file.isFile()) {
								Attachment attch = new Attachment();
								attch.setAttachmentName(file.getName());
								attachmentList.add(attch);					 				 
							} 
						}				
					}
					ticket.setAttachmentList(attachmentList);
				}
					/*List<Resource> staffList = commonService.getStaffUserIdList();
					if (staffList != null && staffList.size() != 0) {
						model.addAttribute("userIdList", staffList);
					}*/
					/*List<ServiceType> serviceTypeList = commonService.getServiceTypeList();
					if (serviceTypeList != null && serviceTypeList.size() != 0) {
						model.addAttribute("serviceTypeList", serviceTypeList);
					}*/
					List<TicketStatus> ticketStatusList = commonService.getAllTicketStatus();
					if(ticketStatusList != null && ticketStatusList.size() != 0){
						for(TicketStatus ts : ticketStatusList){
							logger.info(ts.getTicketStatusCode());
							logger.info(ts.getTicketStatusName());
						}
						model.addAttribute("ticketStatusList",ticketStatusList);
					}
					ResourceType rType = commonService.getResourceTypeofUser(sessionObject.getUserId());
					if (rType != null){
						model.addAttribute("resourceType", rType.getResourceTypeName());				
					}
					
					String ticketCode = ticket.getTicketRecId();
					 List<Answer> questionAnswerList = commonService.getServeyDetailsForATicket(ticketCode);
					 model.addAttribute("questionAnswerList",questionAnswerList);
						
					//Added By Naimisha 29032018
					 
					 List<String>levelList = commonService.getLevelListForTicket();
					 model.addAttribute("levelList", levelList);
					 
					 
					model.addAttribute("ticket", ticket);
					
					model.addAttribute("statusType", statusType);
				//}else{
					//System.out.println("directory not found");
				//}				
			}			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("editTicket() In TicketController.java: Exception", e);
			
		}
		return "common/editTicket";
	}
	
	/**
	 * modified by sourav.bhadra
	 * chnages taken on 20042017
	 * **/
	
	@RequestMapping(value = "/editTask", method = RequestMethod.POST)
	public String editTask(HttpServletRequest request,
									 HttpServletResponse response, 
									 ModelMap model, 									 
									 Ticket ticket,
									 @ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = new ModelAndView("common/listTicket");
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		try {
			logger.info("In editTask() method of CommonController");	
		//	String serviceTypeCode = ticket.getTicketService().getTicketServiceCode();
			ticket.setUpdatedBy(sessionObject.getUserId());
			System.out.println("Standard==="+ticket.getStandard());
			if((ticket.getTicketService().getTicketServiceCode()).equalsIgnoreCase("StudentLeave")){
				ticket.setUrl(sendLeaveDetailsOfCadet);	
				ticket.setUserName(portalUserName);
				ticket.setPassword(portalPassWord);
				ticket.setWebIQAvailable(isWebIQAvailable);
				List<StudentAttendance> studentAttendanceList = getStudentAttandanceList(ticket);//for student leave
				ticket.setStudentAttendanceList(studentAttendanceList);
			}
			Ticket ticketObj = commonService.updateTaskDetails(ticket); 
			/***new code added for save this edited file into external repository**/
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			
				if(ticket != null){
					if(ticket.getTicketRecId() != null){
						//ticket.setTicketRecId(ticket.getTicketRecId());
						Attachment attachment = new Attachment();
						attachment.setStorageRootPath(repository);
						attachment.setFolderName(ticketAttachmentsPath);
						ticket.setTicketCode(ticket.getTicketRecId());
						if(ticket.getUploadFile()!=null){ 
							ticket.getUploadFile().setAttachment(attachment);
							commonService.ticketDocumentUpload(ticket);
						}		
					}	
				}
		//	}
				
			
			model.addAttribute("ticket", ticket);

			String updateStatus = ticketObj.getQueryStatus();
			model.addAttribute("updateStatus", updateStatus);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("editTask() InCommonController.java: Exception" + e);
		}
		return  inwardDelegatedTask( request,response, model, sessionObject);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param className
	 * @param teachingLevel
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/closedTicketDetails", method = RequestMethod.GET)
	public ModelAndView closedTicketDetails(HttpServletRequest request,
											HttpServletResponse response, ModelMap model, Ticket ticket,
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = new ModelAndView();
		try {
			logger.info("In closedTicketDetails() method of TicketController");
			ticket.setUpdatedBy(sessionObject.getUserId());
			ticket = commonService.getTicketDetailsForMyService(ticket);			
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			if(ticket != null ){	
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				//String path = rootPath + ticketAttachmentsPath + ticket.getTicketCode();
				//String path = rootPath + employeeDocFolderPath +ticket.getTicketService().getTicketServiceCode()+"/ticket_doc/"+ ticket.getTicketCode()+"/";
				String path = repository + "/" + ticketAttachmentsPath +ticket.getTicketCode()+"/";
				System.out.println("path======"+path);
				File filedir = new File(path);
				if (!filedir.exists()) {					
				}else{
				File folder = new File(path);
				File[] listOfFiles = folder.listFiles();

				for (int i = 0; i < listOfFiles.length; i++) {
				  File file = listOfFiles[i];
				  if (file.isFile()) {
					  Attachment attch = new Attachment();
					  attch.setAttachmentName(file.getName());
					  attachmentList.add(attch);					 				 
				  } 
				}				
			}
			ticket.setAttachmentList(attachmentList);			

			ResourceType rType = commonService.getResourceTypeofUser(sessionObject.getUserId());
			if (rType != null){
				model.addAttribute("resourceType", rType.getResourceTypeName());				
			}
			List<Resource> staffList = commonService.getStaffUserIdList();
			if (staffList != null && staffList.size() != 0) {
				model.addAttribute("userIdList", staffList);
			}
			List<ServiceType> serviceTypeList = commonService.getServiceTypeList();
			if (serviceTypeList != null && serviceTypeList.size() != 0) {
				model.addAttribute("serviceTypeList", serviceTypeList);
			}
			List<TicketStatus> ticketStatusList = commonService.getAllTicketStatus();
			if(ticketStatusList != null && ticketStatusList.size() != 0){
				model.addAttribute("ticketStatusList",ticketStatusList);
			}
			
			String ticketCode = ticket.getTicketCode();
			 List<Answer> questionAnswerList = commonService.getServeyDetailsForATicket(ticketCode); //Added by naimisha 30082017
			 model.addAttribute("questionAnswerList",questionAnswerList);
			 
			mav.setViewName("common/viewClosedTicket");			
			model.addAttribute("ticket", ticket);
			}			
		}catch (Exception e) {
			logger.error("closedTicketDetails() In TicketController.java: Exception", e);
		}
		return mav;
	}

	@RequestMapping(value = "/inwardDelegatedTask", method = RequestMethod.GET)
	public String inwardDelegatedTask(
					HttpServletRequest request,
					HttpServletResponse response,
					ModelMap model,	
					@ModelAttribute("sessionObject") SessionObject sessionObject
					) {	
		try{	
			Task task = new Task();
			task.setUserId(sessionObject.getUserId());
			List<Task> delegatedTaskList = commonService.getDelegatedTask(task);
			model.addAttribute("delegatedTaskList", delegatedTaskList);
		}catch(Exception e){
			logger.error("Exception in getDelegatedTask() in CommonController: ", e);
			e.printStackTrace();
		}
		return "common/delegatedTask";
	}
	
	@RequestMapping(value = "/outwardDelegatedTask", method = RequestMethod.GET)
	public String outwardDelegatedTask(
					HttpServletRequest request,
					HttpServletResponse response,
					ModelMap model,	
					@ModelAttribute("sessionObject") SessionObject sessionObject
					) {
		try{	
			Task task = new Task();
			task.setUserId(sessionObject.getUserId());
			List<Task> delegatedTaskList = commonService.getOutwardDelegatedTask(task);
			model.addAttribute("delegatedTaskList", delegatedTaskList);
		}catch(Exception e){
			logger.error("Exception in getDelegatedTask() in CommonController: ", e);
			e.printStackTrace();
		}
		return "common/outwardDelegatedTask";
	}
	
	/**
	 * ranita.sur
	 * changes taken on 17062017*/
	
	@RequestMapping(value = "/editVendorDetails", method = RequestMethod.POST)
	public String editVendorDetails(HttpServletRequest request,
							  HttpServletResponse response,
							  ModelMap model,
							  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			
			Vendor vendor = new Vendor();
			
			String saveId=request.getParameter("saveId");
			String vendorname = request.getParameter("getVendorName");
			String contactno = request.getParameter("getContactNo1");
			String contctno1 = request.getParameter("getContactNo2");
			String email = request.getParameter("getEmailId");
			String add = request.getParameter("getAddress");
			String bankname = request.getParameter("getBankName");
			String accno =request.getParameter("getAccountNo");
			String code= request.getParameter("getBankCode");
			String location = request.getParameter("getBankLocation");
			String branchcod =request.getParameter("getBranchCode");
			vendor.setVendorCode(request.getParameter("oldVendorCode"+saveId));
			
			vendor.setUpdatedBy(sessionObject.getUserId());
			vendor.setVendorName(vendorname);
			vendor.setVendorDesc(vendorname);
			vendor.setVendorContactNo1(contactno);
			vendor.setVendorContactNo2(contctno1);
			
			vendor.setEmailId(email);
			vendor.setBranchCode(branchcod);
			System.out.println(vendor.getEmailId());
			vendor.setVendorAddress(add);
			vendor.setBankName(bankname);
			vendor.setAccountNo(Long.parseLong((accno)));
			vendor.setBankCode(code);
			vendor.setBankLocation(location);
			status=commonService.editVendorDetails(vendor);
			model.addAttribute("insertUpdateStatus", status);
			/**Added by @author Saif.Ali
			 * Date- 19/03/2018
			 * Used to insert the information into the activity_log table*/
			String oldVendorName = request.getParameter("newVendorName"+saveId);	// old vendor name
			//String newVendorName = request.getParameter("vendorName");	// new vendor name
			System.out.println("LN 2179 :: New vendor name::"+ vendorname);
			String oldContactNumber1= request.getParameter("vendorContact1"+saveId);	// old contact number 1
		//	String newContactNumber1= request.getParameter("vendorContactNo1");	// new contact number 1
			System.out.println("LN 2182 :: New contact number 1 name::"+ contactno);
			String oldContactNumber2= request.getParameter("vendorContact2"+saveId);	// old contact number 2
		//	String newContactNumber2= request.getParameter("vendorContactNo2");	// new contact number 2
			System.out.println("LN 2185 :: New contact number 2 name::"+ contctno1);
			
			if(status.equalsIgnoreCase("Vendor update successful")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT VENDOR DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor((request.getParameter("newVendorName"+saveId)));
				String description = "";
				if(!(oldVendorName.equalsIgnoreCase(vendorname)))
				{
					description=description + ("Vendor Name :: " + oldVendorName + " updated to new Vendor Name :: " + vendorname + " ; ");
				}
				if(!(oldContactNumber1.equalsIgnoreCase(contactno)))
				{
					description=description +  ("contact number 1 ::" + oldContactNumber1 + " updated to ::" + contactno+ " ; ");
				}
				if(!(oldContactNumber2.equalsIgnoreCase(contctno1)))
				{
					description=description +  ("contact number 2 ::" + oldContactNumber2 + " updated to ::" + contctno1+ " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 2822 :: CommonController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/**Addition ends*/
			
			
		} catch (Exception e) {
			logger.error("Exception In editCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return addVendor(request,response,model);
	}
	
	/**
	 * @author anup.roy*
	 * get email details; inbox; sentbox*/
	
	@RequestMapping(value = "/getEmailDetails", method = {RequestMethod.GET, RequestMethod.POST })
	public String getEmailDetails(
									HttpServletRequest request,
									HttpServletResponse response,
									ModelMap model,
									@ModelAttribute("sessionObject") SessionObject sessionObject,
									@RequestParam(value = "delete", required = false) String delete,
									@RequestParam(value = "emailDetailsCode", required = false) String []emailDetailsCodeArr
									) {	
		logger.info("In  getEmailDetails() method of CommonController");
		Notification emailNotification = null;
		try{
			if(delete != null){
				if(emailDetailsCodeArr!=null && emailDetailsCodeArr.length!=0){
					List<EmailDetails> emailDetailsList = new ArrayList<>();
					for(int i=0;i < emailDetailsCodeArr.length;i++){
						EmailDetails emailDetails = new EmailDetails();
						emailDetails.setEmailDetailsCode(emailDetailsCodeArr[i]);
						emailDetailsList.add(emailDetails);
					}
					emailNotification = new Notification();
					emailNotification.setUpdatedBy(sessionObject.getUserId());
					emailNotification.setEmailDetailsList(emailDetailsList);
					emailNotification = commonService.deleteEmailDetails(emailNotification);
				}
			}else{
				//have to retrive name and password for user					
				EmailReceiver emailReceiver = new EmailReceiver();
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				File directory = new File(repository);
				boolean isExists = directory.exists();
				String path = null;
				if(isExists == true){
					path = repository+"/"+emailAttachmentsPath;
				}
				System.out.println("path====="+path); 
				//emailNotification = commonService.insertEmailDetails(emailReceiver.readEmail(sessionObject.getUserId(), sessionObject.getUserId(),servletContext),sessionObject.getUserId());
				emailNotification = commonService.insertEmailDetails(emailReceiver.readEmail(sessionObject.getUserId(), sessionObject.getUserId(),servletContext,path),sessionObject.getUserId());
			}
		}catch(NullPointerException e){
			logger.error("getEmailDetails() In CommonController.java: NullPointerException", e);	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getEmailDetails() In CommonController.java: Exception", e);	
		}
		return getEmailDetailsPaging(request,response,model,emailNotification,sessionObject);
	}
	
	@RequestMapping(value="/getEmailDetailsPaging",method=RequestMethod.GET)
	public String getEmailDetailsPaging(HttpServletRequest request, 
										HttpServletResponse response,
										ModelMap model,
										Notification emailNotification,
										@ModelAttribute("sessionObject") SessionObject sessionObject
										){		
		try{
			logger.info("getEmailDetailsPaging() method in CommonController: ");
			model.addAttribute("emailNotification", emailNotification);
			model.addAttribute("emailItem", "Inbox");
			/*List<EmailDetails> emailDetailsList = commonService.getEmailDetailsPaging(request);	
			model.addAttribute("emailDetailsList", emailDetailsList);*/
			List<EmailDetails> emailDetailsList = emailNotification.getEmailDetailsList();
			model.addAttribute("emailDetailsList", emailDetailsList);
		}catch(NullPointerException e){
			e.printStackTrace();
			logger.error("Exception in getEmailDetailsPaging() in CommonController: ", e);
		}
		return "common/emailDetails";
	}
	
	/*************Naimisha23022017******************/
	@RequestMapping(value = "/getSectionAgainstCourse", method = RequestMethod.GET)
	public @ResponseBody
	String getSectionAgainstCourse(@RequestParam("course") String course) {
		String section = null;
		try {
			logger.info("getSectionAgainstStandard() In CommonController.java");
			String standard = commonService.getStandatdCodeAgainstCourse(course);
			section = commonService.getSectionAgainstStandard(standard);
			//System.out.println("section======="+section);
		} catch (NullPointerException e) {
			logger.error("getSectionAgainstStandard() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getSectionAgainstStandard() In CommonController.java: Exception"
					+ e);
		}
		return section;
	}

	/***************Added By Koustav03032017*******************************/
	@RequestMapping(value = "/viewDownloadAssignment", method = RequestMethod.GET)
	public String viewDownloadAssignment(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										@RequestParam("folderParam") String folderParam,
										@RequestParam("fileParam") String fileParam,
										@RequestParam( value = "courseCode" , required = false) String courseCode,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			//System.out.println("inside common:");
			//System.out.println("folder param="+folderParam);
			logger.info("Inside viewDownloadAssignment() of CommonController");
			//System.out.println(folderParam);
			List<String> academicYearList = null;
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			String fullPath = repository+"/"+uploadAssignmenetPath;
			if(!folderParam.equals("AcademicYear")){
				fullPath = fullPath + folderParam + "/";
				//System.out.println("@@ fullPath : "+fullPath);
				FileUploadDownload fileUploadDownload = new FileUploadDownload();
				fileUploadDownload.downloadFile(response, fullPath, fileParam);
			}else{
				File file = new File(fullPath);
				File[] listOfFiles = file.listFiles();
				//System.out.println(listOfFiles.length);
				if(null != listOfFiles && listOfFiles.length != 0){
					academicYearList = new ArrayList<String>();
					for(int i= 0; i<listOfFiles.length; i++){
						if(listOfFiles[i].isDirectory()){
							academicYearList.add(listOfFiles[i].getName());						
						}
					}				
				}
			}
			//System.out.println(academicYearList);
				if(null != academicYearList && academicYearList.size() != 0){
					model.addAttribute("academicYearList", academicYearList);
				}
				/* /Added By Naimisha 24042017/*/
				String userId = sessionObject.getUserId();
				Course course = commonService.getCourseAgainstCourseCode(courseCode,userId);
				
				
			//	List<Course> courseList = academicsService.getCourseList();
			
				model.addAttribute("course", course);
			
		} 
		catch (Exception ce){
			logger.error("Exception in viewDownloadAssignment() of CommonController", ce);
		}
		return "common/downloadAssignment";
}

/*
	@RequestMapping(value = "/getTeacherAgainstCode", method = RequestMethod.GET)
	public @ResponseBody
	String getTeacherid(@RequestParam("course") String course) {
		String gs = "";
		gs = commonService.getTeacherId(course);
		gs=gs+"*~*"+gs;
		System.out.println(gs);
		return (new Gson().toJson(gs));
	}*/
	@RequestMapping(value = "/getTeacherAgainstCourse", method = RequestMethod.GET)
	public @ResponseBody
	String getTeacherAgainstCourse(@RequestParam("course") String course,
			@RequestParam("term") String term,@RequestParam("subject") String subject,
							@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
		String teacherDetails = "";
		try {
			Course courseObj = new Course();
			courseObj.setCourseCode(course);
			courseObj.setCourseName(term);
			courseObj.setCourseDesc(subject);
			teacherDetails = academicsService.getTeacherAgainstCourse(courseObj);
			//System.out.println("teacherDetails======"+teacherDetails);
		} catch (Exception ce) {
			logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(teacherDetails));
	}

	@RequestMapping(value = "/getAssignmentFolderNames", method = RequestMethod.GET)
	public @ResponseBody
	String getQuestionFolderNames(@RequestParam("paperDirName") String paperDirName) {
		logger.info("Inside getQuestionFolderNames() of AcademicsController");
		String allDirs = "";
		//System.out.println("calllll====");
		//System.out.println(paperDirName);
		try {
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			String fullPath = repository+"/"+uploadAssignmenetPath;
			fullPath = fullPath+paperDirName+"/";
			//System.out.println("fullpath=="+fullPath);
			File file = new File(fullPath);
			File[] listOfFiles = file.listFiles();
			//System.out.println("====" +listOfFiles);
			if(null != listOfFiles && listOfFiles.length != 0){
				for(int i= 0; i<listOfFiles.length; i++){
					//System.out.println("bbbbb");
					if(listOfFiles[i].isDirectory() || listOfFiles[i].isFile()){
						//System.out.println("@@@@@@@@@@@@@@@@@@");
						//System.out.println(listOfFiles[i].getName());
						allDirs = allDirs + listOfFiles[i].getName() + "~";	
					}
				}				
			}
			//System.out.println("kaustav"+allDirs);
		} catch (Exception ce) {
			logger.error("Exception in getQuestionFolderNames() of AcademicsController", ce);
		}		
		return (new Gson().toJson(allDirs));
	}
	
	/**************New Added By Naimisha 040422017************/
	@RequestMapping(value = "/approveTask", method = RequestMethod.GET)
	String approveTask(HttpServletRequest request,HttpServletResponse response, ModelMap model,
						@RequestParam("saveId") String saveId,
						@RequestParam("ticketCode") String ticketCode,
						@RequestParam("taskId") String taskId,
						@RequestParam("taskName") String taskName,
						@RequestParam("taskComment") String taskComment,
						@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String section = null;
		try {
			//System.out.println("saveId======"+saveId);
			//System.out.println("taskId========"+taskId);
			//System.out.println("ticketCode==="+ticketCode);
			//System.out.println("taskComment==="+taskComment);
			Ticket ticket = new Ticket();
			ticket.setTicketCode(ticketCode);
			ticket.setTicketId(Integer.parseInt(taskId));
			ticket.setStatus("WORK_IN_PROGRESS");
			ticket.setComment(taskComment);
			ticket.setTicketDesc(taskName);
			ticket.setUpdatedBy(sessionObject.getUserId());
			String updateStatus = commonService.updateTicketAndTaskStatus(ticket);
			String msg = null;
			//System.out.println("insertStatus======"+updateStatus);
			//model.addAttribute("insertStatus", updateStatus);
			/*if (null != updateStatus) {
				if (updateStatus.equalsIgnoreCase("success")) {
					model.addAttribute("successMessage", "Task Allocated  SuccessFully");
				} else {
					model.addAttribute("errorMessage", "Failed To Allocate Task");			
				}
			}	*/
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("approveTask() In CommonController.java: Exception"
					+ e);
		}
		return inwardDelegatedTask( request, response,model,sessionObject);
	}
	
	@RequestMapping(value = "/closedTask", method = RequestMethod.GET)
	String closedTask(HttpServletRequest request,HttpServletResponse response, ModelMap model,
						@RequestParam("saveId") String saveId,
						@RequestParam("ticketCode") String ticketCode,
						@RequestParam("taskId") String taskId,
						@RequestParam("taskName") String taskName,
						@RequestParam("taskComment") String taskComment,
						@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//System.out.println("helooooooooooooo");
	
		try {
			/*System.out.println("saveId======"+saveId);
			System.out.println("taskId========"+taskId);
			System.out.println("ticketCode==="+ticketCode);
			System.out.println("taskName==="+taskName);
			System.out.println("taskComment==="+taskComment);*/
			Ticket ticket = new Ticket();
			ticket.setTicketCode(ticketCode);
			ticket.setTicketId(Integer.parseInt(taskId));
			ticket.setStatus("CLOSED");
			ticket.setTicketDesc(taskName);
			ticket.setComment(taskComment);
			ticket.setUpdatedBy(sessionObject.getUserId());
			String updateStatus = commonService.updateTicketAndTaskStatus(ticket);
			String msg = null;
			/*if(updateStatus.equalsIgnoreCase("success")){
				msg =""
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("closedTask() In CommonController.java: Exception"
					+ e);
		}
		return inwardDelegatedTask( request, response,model,sessionObject);
	}
	
	@RequestMapping(value = "/rejectTask", method = RequestMethod.GET)
	String rejectTask(HttpServletRequest request,HttpServletResponse response, ModelMap model,
						@RequestParam("saveId") String saveId,
						@RequestParam("ticketCode") String ticketCode,
						@RequestParam("taskId") String taskId,
						@RequestParam("taskName") String taskName,
						@RequestParam("taskComment") String taskComment,
						@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String section = null;
		try {
			/*System.out.println("saveId======"+saveId);
			System.out.println("taskId========"+taskId);
			System.out.println("ticketCode==="+ticketCode);
			System.out.println("taskComment==="+taskComment);*/
			Ticket ticket = new Ticket();
			ticket.setTicketCode(ticketCode);
			ticket.setTicketId(Integer.parseInt(taskId));
			ticket.setStatus("REJECTED");
			ticket.setComment(taskComment);
			ticket.setTicketDesc(taskName);
			ticket.setUpdatedBy(sessionObject.getUserId());
			String updateStatus = commonService.updateTicketAndTaskStatus(ticket);
			String msg = null;
			//System.out.println("insertStatus======"+updateStatus);
			//model.addAttribute("insertStatus", updateStatus);
			/*if (null != updateStatus) {
				if (updateStatus.equalsIgnoreCase("success")) {
					model.addAttribute("successMessage", "Task Allocated  SuccessFully");
				} else {
					model.addAttribute("errorMessage", "Failed To Allocate Task");			
				}
			}	*/
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("rejectTask() In CommonController.java: Exception"
					+ e);
		}
		return inwardDelegatedTask( request, response,model,sessionObject);
	}
	
	@RequestMapping(value = "/getPreviousCommentForTask", method = RequestMethod.GET)
	public @ResponseBody
	String getPreviousCommentForTask(@RequestParam("taskId") String taskId) {
		String taskCommentDetails = null;
		try {
			logger.info("getPreviousCommentForTask() In CommonController.java");
			Task task = new Task();
			task.setTaskId(Integer.parseInt(taskId));;
			List <Task> taskCommentList = commonService.getAllTaskCommentForATask(task);
			for(Task taskComment:taskCommentList){
				taskCommentDetails = taskCommentDetails +"#"+ taskComment.getStartDate() + "*"+ taskComment.getTaskComment();
			}
			//System.out.println("taskCommentDetails======"+taskCommentDetails);
		} catch (NullPointerException e) {
			logger.error("getPreviousCommentForTask() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getPreviousCommentForTask() In CommonController.java: Exception"
					+ e);
		}
	 return (new Gson().toJson(taskCommentDetails));
	}
	
	/**
	 * New method by kaustav.sen
	 * chnages taken on 13042017
	 * **/
	
	@RequestMapping(value = "/getSectionsAgainstCourse", method = RequestMethod.GET)
	public @ResponseBody
	String getSectionsAgainstCourse(@RequestParam("standard") String standard) {
		String section = null;
		//System.out.println("standard====="+standard);
		//System.out.println("LN1902");
		try {
			
			logger.info("getSectionAgainstStandard() In CommonController.java");
			
			section = commonService.getSectionsAgainstStandard(standard);
			//System.out.println("1909section======="+section);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getSectionsAgainstStandard() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getSectionsAgainstStandard() In CommonController.java: Exception"
					+ e);
		}
		return section;
	}
	/***************Added By Naimisha 24042017**************/
	@RequestMapping(value = "/viewFeesDetails", method = RequestMethod.GET)
	String viewFeesDetails(HttpServletRequest request,HttpServletResponse response, ModelMap model,
						@RequestParam("userId") String userId,
						@RequestParam("courseCode") String courseCode,
						@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String section = null;
		try {
			Course course = commonService.getCourseAgainstCourseCode(courseCode, userId);
			String courseName = courseCode;
			List<FeesCategory> studentFeesTemplateAmountDetailsList = backOfficeService.getStudentFeesTemplateAmountDetails(courseName);
			Resource resource = commonService.getResourceAgainstUserId(userId);
			model.addAttribute("resource", resource);
			model.addAttribute("course", course);
			
			List<FeesCategory>feesCategoryList = new ArrayList<FeesCategory>();
			for(FeesCategory fees : studentFeesTemplateAmountDetailsList ){
				List<SocialCategory>socialCategoryList = fees.getSocialCategoryList();
				FeesCategory feesCategory  = new FeesCategory();
				for(SocialCategory socialCategory : socialCategoryList){
					if(resource.getCategory().equalsIgnoreCase(socialCategory.getSocialCategoryCode())){
						
						feesCategory.setFees(socialCategory.getAmount());
						feesCategory.setFeesCategoryCode(fees.getFeesCategoryName());
					}
				}
				feesCategoryList.add(feesCategory);
			}
			resource.setFeesCategoryList(feesCategoryList);
				
			model.addAttribute("feesCategoryList", feesCategoryList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("rejectTask() In CommonController.java: Exception"
					+ e);
		}
		return "common/viewFeesDetails";
	}
	
	//Modified By Naimisha 06092017
	@RequestMapping(value = "/downloadMarkSheet", method = RequestMethod.GET)
	public String downloadMarkSheet(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										@RequestParam("courseCode") String courseCode,
										@RequestParam("userId") String userId,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
		
			logger.info("Inside viewDownloadAssignment() of CommonController");
			
			
			
			//RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			//String repository = repositoryStructure.getRepositoryPathName();
			//String fullPath = repository+"/"+uploadAssignmenetPath;
			
			
				Course course = commonService.getCourseAgainstCourseCode(courseCode,userId);
				
				List<Term> termList = academicsService.getTermListForACourse(courseCode);
				
				
			
				model.addAttribute("userId", userId);
				model.addAttribute("course", course);
				model.addAttribute("termList", termList);
			
		} 
		catch (Exception ce){
			logger.error("Exception in viewDownloadAssignment() of CommonController", ce);
		}
		return "common/downloadMarkSheet";
	}
	
	@RequestMapping(value = "/downloadResultForMyService", method = RequestMethod.POST)
	public String getStudentReportFORXI_XII(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									StudentResult studentResult,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {	
		String userId=null;
		String courseCode = null;
		try{
			//if(null != standard && null != section && null != academicYear && null != exam && null != roll){
			AcademicYear academicYear=commonService.getCurrentAcademicYear();
			 userId = studentResult.getStudentUserId();
			 courseCode = studentResult.getCourse();
			Student  student = commonService.getStudentAgainstProgramAndUserId(userId, courseCode);
			//String rollNumber = student.g
			//System.out.println("rollNumber=="+rollNumber);
			studentResult.setRollNumber(student.getRoll());
			studentResult.setName(student.getStudentName());
			studentResult.setAcademicYear(academicYear.getAcademicYearCode());
			String status = commonService.getIndividualReportForMyService(studentResult,response);
				/*if(status != null ){
					 	      
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}*/
			//}
			
		}catch(Exception e){
			logger.error(e);		  	
		}		
		return downloadMarkSheet(request, response, model,courseCode,userId,sessionObject);
	}
	
	@RequestMapping(value = "/getMarksheet", method = RequestMethod.GET)
	public @ResponseBody
	String getMarksheet(@RequestParam("course") String course,
			@RequestParam("term") String term,
			@RequestParam("batch") String batch,
			@RequestParam("userId") String userId) {
		String allDirs = "";
		//System.out.println("standard====="+standard);
		//System.out.println("LN1902");
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		try {
			
			logger.info("getMarksheet() In CommonController.java");
			String rolNumber = commonService.getrollNumberAgainstProgramAndUserId(userId,course);
			String roll = rolNumber.replace("/", "-"); 
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			
			File directory = new File(repository);
			boolean isExists = directory.exists();
			String insertStatus = null;
			if(isExists == true){
				String path = repository+"/"+course + "/"+batch+"/"+term+"/"+"pdf/"+roll+"/";
				System.out.println("path=="+path);
				File filedir = new File(path);
				if (!filedir.exists()) {					
				}
				else{
					File folder = new File(path);
					File[] listOfFiles = folder.listFiles();
	
					/*for (int i = 0; i < listOfFiles.length; i++) {
						File file = listOfFiles[i];
						if (file.isFile()) {
							Attachment attch = new Attachment();
							attch.setAttachmentName(file.getName());
							attachmentList.add(attch);					 				 
						} 
					}		*/	
					if(null != listOfFiles && listOfFiles.length != 0){
						for(int i= 0; i<listOfFiles.length; i++){
							if(listOfFiles[i].isDirectory() || listOfFiles[i].isFile()){
								allDirs = allDirs + listOfFiles[i].getName() + "~";	
							}
						}				
					}
					allDirs = allDirs +";"+path;
				}
			}
			//System.out.println("attachmentList==="+attachmentList.size());
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getMarksheet() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getMarksheet() In CommonController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(allDirs));
	}
	
	@RequestMapping(value = "/viewDownloadMarkSheet", method = RequestMethod.GET)
	public String viewDownloadMarkSheet(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										@RequestParam("folderParam") String folderParam,
										@RequestParam("fileParam") String fileParam) {
		try {
			logger.info("Inside viewDownloadMarkSheet() of AcademicsController");
			List<String> academicYearList = null;
			/*String fullPath = rootPath+uploadQuestionPapersPath;*/
			/**new code for download from external repository*/
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			String fullPath = folderParam;
			fileUploadDownload.downloadFile(response, fullPath, fileParam);

		} catch (Exception ce){
			logger.error("Exception in viewDownloadMarkSheet() of AcademicsController", ce);
		}
		return null;
	}
	
	/**
	 * @author sourav.bhadra
	 * changes taken on 17062017
	 * */
	
	@RequestMapping(value = "/addVendorType", method = RequestMethod.GET)
	public String addVendorType(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In addVendorType method of CommonController.java");
			List<VendorType> vendorTypeList = commonService.getVendorTypes();
			if (vendorTypeList != null) {
				model.addAttribute("vendorTypeList", vendorTypeList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addVendorType() In CommonController.java: Exception",e);
		}
		return "common/addVendorType";
	}
		

	@RequestMapping(value="/submitVendorType", method = RequestMethod.POST)
	public String addVendorType(HttpServletRequest request, HttpServletResponse response, ModelMap model,
									@ModelAttribute("VendorType") VendorType vendorType,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("Excecuting addVendorType() from CommonController, calling addVendorType() in commonService.java");
			vendorType.setUpdatedBy(sessionObject.getUserId());	
			String submitResponse = commonService.submitVendorType(vendorType);
			model.addAttribute("submitResponse", submitResponse);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in addVendorType() from commonController : ",e);
		}		
		return addVendorType(request, response, model);
	}

	@RequestMapping(value="/editVendorType", method = RequestMethod.POST)
	public String editVendorType(HttpServletRequest request, HttpServletResponse response, ModelMap model,									
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("Excecuting editDesignationType() from erpController, calling editDesignationType() in erpService.java");
			String saveId=request.getParameter("saveId");
			  /* added by ranita.sur on 14092017 for showing pop up */ 
			String vendorTypes = (request.getParameter("getVendorType"));
			VendorType vendorType = new VendorType();
			vendorType.setVendorTypeName(vendorTypes.trim());
			vendorType.setVendorTypeCode((request.getParameter("vendorTypeCode"+saveId)));
			vendorType.setUpdatedBy(sessionObject.getUserId());			
			String updateResponse = commonService.editVendorType(vendorType);
			model.addAttribute("updateResponse", updateResponse);
			/**Added by @author Saif.Ali
			 * Date- 13/03/2018
			 * Used to insert the information into the activity_log table*/
			if(updateResponse.equalsIgnoreCase("Success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT Vendor Type");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor((request.getParameter("oldVendorTypeNames"+saveId)));
				updateLog.setDescription("Vendor Type :: " + (request.getParameter("oldVendorTypeNames"+saveId)) + " updated to Vendor Type :: " + vendorTypes);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 2822 :: CommonController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/**Addition ends*/
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured in editDesignationType() from erpController : ",e);
		}		
		return addVendorType(request, response, model);
	}
	
	/**
	 *@author ranita.sur
	 *changes 19062017*/
	
	@RequestMapping(value = "/inactiveVendorDetails", method = RequestMethod.GET)
	public String inactiveProgramType(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam("vendorCode") String vendorCode,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {

		try {
			logger.info("Inside Method inactiveVendorDetails-GET of CommonController");
			
			Vendor  vendor = new Vendor();
			vendor.setVendorCode(vendorCode);
			vendor.setUpdatedBy(sessionObject.getUserId());
			
			String status = commonService.inactiveVendorDetails(vendor);
			String msg = null;
			if(status.equalsIgnoreCase("success")){
				msg = "Deleted SuccessFully";
			}else{
				msg = "Failed To Delete";
			}
			model.addAttribute("msg", msg);
			model.addAttribute("insertUpdateStatus", status);
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method inactiveVendorDetails-GET of CommonController", ce);
		}
		return addVendor(request,response,model);
	}
	
	//Added By Naimisha 22062017
	
	@RequestMapping(value = "/getSentEmailDetails", method = RequestMethod.GET)
	public String getSentEmailDetails(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In getSentEmailDetails method of CommonController.java");
			
			List<EmailDetails>sentEmailDetailsList = commonService.getsentEmailDetailsList(sessionObject.getUserId());
			System.out.println("sentEmailDetailsList size==="+sentEmailDetailsList.size());
			System.out.println("status = "+sentEmailDetailsList.get(0).getStatus());
			model.addAttribute("sentEmailDetailsList", sentEmailDetailsList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getSentEmailDetails() In CommonController.java: Exception",e);
		}
		return "common/sentMailBox";
	}
	
	///Modified By naimisha 13092017
	@RequestMapping(value = "/emailContent", method = RequestMethod.GET)
	//public @ResponseBody
	String emailContent(HttpServletRequest request, HttpServletResponse response, ModelMap model,@RequestParam("emailId") String emailId,
			@RequestParam("senderUserId") String senderUserId,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
	
		try {
			
			logger.info("emailContent() In CommonController.java");
			System.out.println("within");
			List<String> allFiles = new ArrayList<>();
			//userName = commonService.getUserNameForId(userId);
			EmailDetails emailDetailsObj = commonService.getEmailDetailsAgainstEmailCode(emailId);
			if(null != emailDetailsObj.getFilePath()){
				String fullPath  = emailDetailsObj.getFilePath();
				File file = new File(fullPath);
				File[] listOfFiles = file.listFiles();
				if(null != listOfFiles && listOfFiles.length != 0){
					for(int i= 0; i<listOfFiles.length; i++){
						if(listOfFiles[i].isDirectory() || listOfFiles[i].isFile()){
							allFiles.add(listOfFiles[i].getName()); //= allDirs + listOfFiles[i].getName() + "~";	
						}
					}				
				}
			}
			
			
			//System.out.println("allDirs==="+allDirs);
			//emailDetails = emailDetails + emailDetailsObj.getEmailDetailsSender()+"*"+emailDetailsObj.getTime()+"*"+emailDetailsObj.getEmailDetailsSubject()+"*"+emailDetailsObj.getEmailDetailsDesc();
			//emailDetails = emailDetailsObj.getEmailDetailsDesc();
			//System.out.println(emailDetails);
			
			if(null != allFiles && allFiles.size() !=0){
				if(allFiles.size() == 1){
					//String[] filePart = allFiles.get(0).contains("ics");
					//System.out.println("filePart[1]==========="+filePart[1]);
					if(allFiles.get(0).contains(".ics")){
						DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
						
						FileInputStream fin = null;
						try {
							fin = new FileInputStream( emailDetailsObj.getFilePath()+"/"+allFiles.get(0));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						CalendarBuilder builder = new CalendarBuilder();
						
						net.fortuna.ical4j.model.Calendar icsCalendar = null;
						try {
							icsCalendar = builder.build(fin);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}/* catch (ParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						String startdate = null, enddate = null, event = null, location = null, description = null, organizer = null, attendee = null;	
						 Date startDateFromString = null;
						 Date endDateFromString = null;
						for (Iterator<?> i = icsCalendar.getComponents().iterator(); i.hasNext();) {
							
							Component component = (Component) i.next();
							
							for (Iterator<?> j = component.getProperties().iterator(); j.hasNext();) {
								
								try{
									
								     Property property = (Property) j.next();
								     if ("DTSTART".equals(property.getName())) {
								    	 startdate = property.getValue();
								    	 startDateFromString = dateFormat.parse(startdate);
								    	 System.out.println("Start Time : " + startdate);
								    	 System.out.println("Start Time : " + startDateFromString);
								     }
								     if ("DTEND".equals(property.getName())) {
								         enddate = property.getValue();
								         endDateFromString = dateFormat.parse(enddate);
								         System.out.println("enddate==="+enddate);
								    	 System.out.println("End Time : " + endDateFromString);
								     }
								     if ("SUMMARY".equals(property.getName())) {
								         event = property.getValue();
								         System.out.println("Event Name : " + event);
								     }
								     if ("DESCRIPTION".equals(property.getName())) {
								    	 description = property.getValue();
								         System.out.println("Agenda : " + description);
								     }
								     if ("LOCATION".equals(property.getName())) {
								    	 location = property.getValue();
								         System.out.println("Location : " + location);
								     }
								     if ("ORGANIZER".equals(property.getName())) {
								    	 organizer = property.getValue();
								         System.out.println("Organizer : " + organizer);
								     }
								     if ("ATTENDEE".equals(property.getName())) {
								    	 attendee = property.getValue();
								         System.out.println("Attendee : " + attendee);
								     }
								}catch(Exception ex){
									ex.printStackTrace();
								}
								
							}
							   
						}
						model.addAttribute("type","ics");
						model.addAttribute("location",location);
						model.addAttribute("eventName",event);
						model.addAttribute("startTime",startDateFromString);
						model.addAttribute("endTime",endDateFromString);
					}
				}
				model.addAttribute("allFiles",allFiles);
				model.addAttribute("filePath",emailDetailsObj.getFilePath());
			}
			
			model.addAttribute("emailDetailsObj", emailDetailsObj);
			model.addAttribute("userId", sessionObject.getUserId());
			model.addAttribute("emailCode", emailId);
			model.addAttribute("senderUserId", senderUserId);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("emailContent() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("emailContent() In CommonController.java: Exception"
					+ e);
		}
		return "common/inboxEmailDetails";
	}
	
	
	///Modified By naimisha 13092017
	@RequestMapping(value = "/emailContentForSentItems", method = RequestMethod.GET)
	
	String emailContentForSentItems(HttpServletRequest request, HttpServletResponse response, ModelMap model,@RequestParam("emailId") String emailId,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String emailDetails = "";
		try {
			
			logger.info("emailContent() In CommonController.java");
			List<String> allFiles = new ArrayList<>();
			EmailDetails emailDetailsObj = commonService.getEmailContentForSentItemsAgainstEmailCode(emailId);
			
			if(null != emailDetailsObj.getFilePath()){
				String fullPath  = emailDetailsObj.getFilePath();
				File file = new File(fullPath);
				File[] listOfFiles = file.listFiles();
				if(null != listOfFiles && listOfFiles.length != 0){
					for(int i= 0; i<listOfFiles.length; i++){
						if(listOfFiles[i].isDirectory() || listOfFiles[i].isFile()){
							allFiles.add(listOfFiles[i].getName()); //= allDirs + listOfFiles[i].getName() + "~";	
						}
					}				
				}
			}
			
			
			//System.out.println("allDirs==="+allDirs);
			//emailDetails = emailDetails + emailDetailsObj.getEmailDetailsSender()+"*"+emailDetailsObj.getTime()+"*"+emailDetailsObj.getEmailDetailsSubject()+"*"+emailDetailsObj.getEmailDetailsDesc();
			//emailDetails = emailDetailsObj.getEmailDetailsDesc();
			//System.out.println(emailDetails);
			
			if(null != allFiles && allFiles.size() !=0){
				if(allFiles.size() == 1){
					//String[] filePart = allFiles.get(0).contains("ics");
					//System.out.println("filePart[1]==========="+filePart[1]);
					if(allFiles.get(0).contains(".ics")){
						DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
						
						FileInputStream fin = null;
						try {
							fin = new FileInputStream( emailDetailsObj.getFilePath()+"/"+allFiles.get(0));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						CalendarBuilder builder = new CalendarBuilder();
						
						net.fortuna.ical4j.model.Calendar icsCalendar = null;
						try {
							icsCalendar = builder.build(fin);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}/* catch (ParserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						String startdate = null, enddate = null, event = null, location = null, description = null, organizer = null, attendee = null;	
						 Date startDateFromString = null;
						 Date endDateFromString = null;
						for (Iterator<?> i = icsCalendar.getComponents().iterator(); i.hasNext();) {
							
							Component component = (Component) i.next();
							
							for (Iterator<?> j = component.getProperties().iterator(); j.hasNext();) {
								
								try{
									
								     Property property = (Property) j.next();
								     if ("DTSTART".equals(property.getName())) {
								    	 startdate = property.getValue();
								    	 startDateFromString = dateFormat.parse(startdate);
								    	 System.out.println("Start Time : " + startdate);
								    	 System.out.println("Start Time : " + startDateFromString);
								     }
								     if ("DTEND".equals(property.getName())) {
								         enddate = property.getValue();
								         endDateFromString = dateFormat.parse(enddate);
								         System.out.println("enddate==="+enddate);
								    	 System.out.println("End Time : " + endDateFromString);
								     }
								     if ("SUMMARY".equals(property.getName())) {
								         event = property.getValue();
								         System.out.println("Event Name : " + event);
								     }
								     if ("DESCRIPTION".equals(property.getName())) {
								    	 description = property.getValue();
								         System.out.println("Agenda : " + description);
								     }
								     if ("LOCATION".equals(property.getName())) {
								    	 location = property.getValue();
								         System.out.println("Location : " + location);
								     }
								     if ("ORGANIZER".equals(property.getName())) {
								    	 organizer = property.getValue();
								         System.out.println("Organizer : " + organizer);
								     }
								     if ("ATTENDEE".equals(property.getName())) {
								    	 attendee = property.getValue();
								         System.out.println("Attendee : " + attendee);
								     }
								}catch(Exception ex){
									ex.printStackTrace();
								}
								
							}
							   
						}
						model.addAttribute("type","ics");
						model.addAttribute("location",location);
						model.addAttribute("eventName",event);
						model.addAttribute("startTime",startDateFromString);
						model.addAttribute("endTime",endDateFromString);
					}
				}
				model.addAttribute("allFiles",allFiles);
				model.addAttribute("filePath",emailDetailsObj.getFilePath());
			}
			
			model.addAttribute("emailDetailsObj", emailDetailsObj);
			model.addAttribute("userId", sessionObject.getUserId());
			
			model.addAttribute("emailDetailsObj", emailDetailsObj);
		} catch (NullPointerException e) {
			logger.error("emailContent() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("emailContent() In CommonController.java: Exception"
					+ e);
		}
		return "common/sentEmailDetails";
	}
	
	@RequestMapping(value="/inactiveForMailSentBox", method = RequestMethod.POST)
	public String inactiveForMailSentBox(HttpServletRequest request, HttpServletResponse response, ModelMap model,									
									@ModelAttribute("sessionObject") SessionObject sessionObject,
									@RequestParam(required = false, value = "emailDetailsCode") String emailDetailsCode[]){
		try{
			logger.info("Excecuting inactiveForMailSentBox() from CommonController");
			List<EmailDetails>emailDetailsList = new ArrayList<EmailDetails>();
			for(int i=0;i<emailDetailsCode.length;i++){
				EmailDetails emailDetails = new EmailDetails();
				emailDetails.setUpdatedBy(sessionObject.getUserId());	
				emailDetails.setEmailDetailsCode(emailDetailsCode[i]);
				emailDetailsList.add(emailDetails);
			}
			
			String updateResponse = commonService.inactiveEmailFromSentBox(emailDetailsList);
			model.addAttribute("updateResponse", updateResponse);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured in inactiveForMailSentBox() from erpController : ",e);
		}		
		return getSentEmailDetails( request,  response,  model, sessionObject);
	}
	
	
	@RequestMapping(value="/inactiveForMailInBox", method = RequestMethod.POST)
	public String inactiveForMailInBox(HttpServletRequest request, HttpServletResponse response, ModelMap model,									
									@ModelAttribute("sessionObject") SessionObject sessionObject,
									@RequestParam(required = false, value = "emailDetailsCode") String emailDetailsCode[]){
		try{
			logger.info("Excecuting inactiveForMailSentBox() from CommonController");
			List<EmailDetails>emailDetailsList = new ArrayList<EmailDetails>();
			for(int i=0;i<emailDetailsCode.length;i++){
				EmailDetails emailDetails = new EmailDetails();
				emailDetails.setUpdatedBy(sessionObject.getUserId());	
				emailDetails.setEmailDetailsCode(emailDetailsCode[i]);
				emailDetailsList.add(emailDetails);
			}
			
			List<EmailDetails> emailList = commonService.inactiveEmailFromInBox(emailDetailsList);
			System.out.println("size==="+emailList.size());
			Notification emailNotification = new Notification();
			emailNotification.setNewNotification(emailList.size());
			model.addAttribute("emailDetailsList", emailList);
			model.addAttribute("emailNotification", emailNotification);
			model.addAttribute("emailItem", "Inbox");
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured in inactiveForMailSentBox() from erpController : ",e);
		}		
		return  "common/emailDetails";
	}
	/*added by ranita.sur on 02082017 For Vendor emailId Validation */
	@RequestMapping(value = "/serverSideValidationOfVendorEmailId", method = RequestMethod.GET)
	public @ResponseBody
	String serverSideValidationOfVendorEmailId(@RequestParam("emailId") String emailId) {
		String vendorEmailId = "";		
		vendorEmailId = commonService.serverSideValidationOfVendorEmailId(emailId);
		return (new Gson().toJson(vendorEmailId));
	}
	
	
	//Added By Naimisha 01082017
	
		@RequestMapping(value = "/getMySalarySlip", method = RequestMethod.GET)
		public String getMySalarySlip(HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("In getMySalarySlip() method of BackOfficeController: ");
			AcademicYear currentYear=null;
			try {
				currentYear = commonService.getCurrentAcademicYear();
			} catch (CustomException e) {
				e.printStackTrace();
			}
			if (currentYear != null) {			
				String strYearArr[] = currentYear.getAcademicYearName().split("-");
				List<AcademicYear> ayList = new ArrayList<AcademicYear>();
				for (int i = 0; i < strYearArr.length; i++) {
					AcademicYear ay = new AcademicYear();
					ay.setAcademicYearName(strYearArr[i]);
					ayList.add(ay);
				}
				model.addAttribute("year", ayList);
			}
			
			List<ResourceType> resourceTypes= backOfficeService.getResourceTypes();
			model.addAttribute("resourceTypes", resourceTypes);
			
			return "common/viewMySalarySlip";
		}
		
		@RequestMapping(value = "/getMySalaryDetails", method = RequestMethod.POST)
		public String getStaffSalaryDetails(ModelMap model,HttpServletRequest request, HttpServletResponse response,
				@ModelAttribute("resource") Resource resource,
				@RequestParam(required = false, value = "year") String year,
				@RequestParam(required = false, value = "month") String month,
				@ModelAttribute("sessionObject") SessionObject sessionObject
				){
			logger.info("In getStaffSalaryDetails() method of BackOfficeController: ");
			Employee staff = null;
			try {
				
				logger.info("In BackOfficeController editDesignationSalaryMapping() method: calling getDesignationSalaryMappingDetailsForEdit(Staff staff) method in BackOfficeServiceImpl.java");
				resource.setStartDate(year);//Carrying Academic Year
				resource.setEndDate(month);//Carrying Academic Month
				resource.setUserId(sessionObject.getUserId());
			/*	Calendar calendar = Calendar.getInstance();
				int intselectedyear = Integer.parseInt(year);
				int intselectedmonth = Integer.parseInt(month);
				String monthYear = month+"-"+year;
				resource.setDesc(monthYear);
				 calendar.set(intselectedyear, intselectedmonth - 1, 1);
				    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

				    int count = 0;
				    for (int day = 1; day <= daysInMonth; day++) {
				        calendar.set(intselectedyear, intselectedmonth - 1, day);
				        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
				        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
				            count++;
				            // Or do whatever you need to with the result.
				        }
				    }
				   
				//System.out.println(daysInMonth+"@@@@@@   count@@@@@@@@"+count);    
				int actualDayOfMonth = daysInMonth - count;
				resource.setUpdatedBy(sessionObject.getUserId());
				resource.setStatus(Integer.toString(actualDayOfMonth));*/
				staff = commonService.getStaffSalaryDetailsForSalarySlip(resource);
				if (staff != null) {
					/*intselectedyear = Integer.parseInt(year);
					intselectedmonth = Integer.parseInt(month);
					Calendar cal = new GregorianCalendar();
					cal.clear();
					cal.set(intselectedyear, intselectedmonth - 1, 1);
					// obtain the number of days of the month.
					int numberOfDaysInSpecificMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					staff.setDaysInMonths(Integer.toString(numberOfDaysInSpecificMonth));	*/
					
					
					model.addAttribute("staffForViewStaffSalaryDetails", staff);
					model.addAttribute("salaryMonth",resource.getEndDate());
					model.addAttribute("salaryYear",resource.getStartDate());
					//model.addAttribute("daysInMonth",daysInMonth);
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
					model.addAttribute("year", ayList);
				}
				if(null != staff){
					if(null == staff.getStatus()){
						model.addAttribute("status","NA");
						model.addAttribute("msg","Salary is Not Disbursed Yet!!");
						
					}
				}
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				logger.error("Error in BackOfficeController getStaffSalaryDetails() method for NullPointerException: ",e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error in BackOfficeController getStaffSalaryDetails() method for Exception: ",e);
			}
			if(null == staff.getStatus()){
				return getMySalarySlip( request,  response,  model,sessionObject);
			}else{
				return "common/mySalarySlip";
			}
			
			
		}
		
	//Added by naimisha 29082017	
		@RequestMapping(value = "/editTicketForMyService", method = RequestMethod.GET)
		public ModelAndView editTicketForMyService(HttpServletRequest request,
									   HttpServletResponse response, ModelMap model, Ticket ticket,
									   @ModelAttribute("sessionObject") SessionObject sessionObject) {
			ModelAndView mav = new ModelAndView();	
			logger.info("TKT DETAILS:"+ticket.getTicketCode());
			try {
				logger.info("In editTicketForMyService() method of TicketController");
				ticket.setUpdatedBy(sessionObject.getUserId());
				ticket = commonService.getTicketDetailsForMyService(ticket);	
				List<Attachment> attachmentList = new ArrayList<Attachment>();
				List<TicketStatus> ticketStatusList = commonService.getAllTicketStatus();
				if(ticket != null ){
					/***new code added for save this edited file into external repository**/
					RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
					String repository = repositoryStructure.getRepositoryPathName();
					File directory = new File(repository);
					boolean isExists = directory.exists();
					String insertStatus = null;
					if(isExists == true){
						//String path = rootPath + ticketAttachmentsPath +ticket.getTicketService().getTicketServiceCode()+"/ticket_doc/"+ ticket.getTicketCode()+"/";
						//String path = rootPath + ticketAttachmentsPath + ticket.getTicketCode()+"/";
						String path = repository+"/"+ticketAttachmentsPath + ticket.getTicketCode()+"/";
						//System.out.println("path======="+path);
						File filedir = new File(path);
						if (!filedir.exists()) {					
						}
						else{
							File folder = new File(path);
							File[] listOfFiles = folder.listFiles();
			
							for (int i = 0; i < listOfFiles.length; i++) {
								File file = listOfFiles[i];
								if (file.isFile()) {
									Attachment attch = new Attachment();
									attch.setAttachmentName(file.getName());
									attachmentList.add(attch);					 				 
								} 
							}				
						}
						ticket.setAttachmentList(attachmentList);
						List<Resource> staffList = commonService.getStaffUserIdList();
						if (staffList != null && staffList.size() != 0) {
							model.addAttribute("userIdList", staffList);
						}
						List<ServiceType> serviceTypeList = commonService.getServiceTypeList();
						if (serviceTypeList != null && serviceTypeList.size() != 0) {
							model.addAttribute("serviceTypeList", serviceTypeList);
						}
						
						if(ticketStatusList != null && ticketStatusList.size() != 0){
							for(TicketStatus ts : ticketStatusList){
								logger.info(ts.getTicketStatusCode());
								logger.info(ts.getTicketStatusName());
							}
							model.addAttribute("ticketStatusList",ticketStatusList);
						}
						ResourceType rType = commonService.getResourceTypeofUser(sessionObject.getUserId());
						if (rType != null){
							model.addAttribute("resourceType", rType.getResourceTypeName());				
						}
						String ticketCode = ticket.getTicketCode();
						List<Answer> questionAnswerList = commonService.getServeyDetailsForATicket(ticketCode); //Added by naimisha 30082017
						model.addAttribute("questionAnswerList",questionAnswerList);
						mav.setViewName("common/editTicketForMyService");
						model.addAttribute("ticket", ticket);
					}else{
						//System.out.println("directory not found");
					}
				}
				model.addAttribute("ticketStatusList",ticketStatusList);
				model.addAttribute("ticket", ticket);
				mav.setViewName("common/editTicketForMyService");
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("editTicket() In TicketController.java: Exception", e);
				
			}
			return mav;
		}
		
		@RequestMapping(value = "/closedTaskList", method = RequestMethod.GET)
		public String closedTaskList(
						HttpServletRequest request,
						HttpServletResponse response,
						ModelMap model,	
						@ModelAttribute("sessionObject") SessionObject sessionObject
						) {	
			try{	
				Task task = new Task();
				task.setUserId(sessionObject.getUserId());
				List<Task> closedTaskList = commonService.getClosedTaskList(task);
				model.addAttribute("closedTaskList", closedTaskList);
			}catch(Exception e){
				logger.error("Exception in closedTaskList() in CommonController: ", e);
				e.printStackTrace();
			}
			return "common/closedTaskList";
		}
		
		
	//Added By Naimisha 29082017
		
		@RequestMapping(value = "/getSurveyDetailsForAJobType", method = RequestMethod.GET)
		public @ResponseBody
		String getSurveyDetailsForAJobType(@RequestParam("ticketServiceName") String ticketServiceName) {
			String questionAnswerDetails = "";
			try {
				logger.info("getSurveyDetailsForAJobType() In CommonController.java");
				JobType jobType = new JobType();
				jobType.setJobTypeCode(ticketServiceName);
				List<QuestionMaster>questionAnswerList = commonService.fetchQuestionAnswerForSurveyOfATicket(jobType);
				/*for(Task taskComment:taskCommentList){
					taskCommentDetails = taskCommentDetails +"#"+ taskComment.getStartDate() + "*"+ taskComment.getTaskComment();
				}*/
				//System.out.println("taskCommentDetails======"+taskCommentDetails);
				for(QuestionMaster questionAnswer : questionAnswerList){
					questionAnswerDetails = questionAnswerDetails + questionAnswer.getSurveyName()+"*"+questionAnswer.getSurveyId()+"##";
					for(Question question : questionAnswer.getQuestionList()){
						questionAnswerDetails = questionAnswerDetails + question.getQuestion()+"*"+question.getObjectId()+"*"+question.getQuestionId()+"*";
						for(Answer answer : question.getAnswerList()){
							questionAnswerDetails = questionAnswerDetails + answer.getInitialValue()+"@";
						}
						questionAnswerDetails = questionAnswerDetails +";";
					}
				}
				System.out.println("questionAnswerDetails===="+questionAnswerDetails);
			} catch (NullPointerException e) {
				logger.error("getSurveyDetailsForAJobType() In CommonController.java: NullPointerException"
						+ e);
			} catch (Exception e) {
				logger.error("getSurveyDetailsForAJobType() In CommonController.java: Exception"
						+ e);
			}
		 return (new Gson().toJson(questionAnswerDetails));
		}
		
		/**Added by @author Saif.Ali
		 * Date-05/09/2017
		 * Used to view the program details of the respective students*/
		@RequestMapping(value = "/showProgramDetails", method = RequestMethod.GET)
		public String showProgramDetails(
						HttpServletRequest request,
						HttpServletResponse response,
						ModelMap model,	
						@ModelAttribute("sessionObject") SessionObject sessionObject
						) {	
			try{	
				Course course= new Course();
				course.setUpdatedBy(sessionObject.getUserId()); 
				course.setCourseCode(sessionObject.getCourseCode());
				
				List<Course> courseDetailsList= commonService.getProgramDetailsList(course);
				model.addAttribute("courseDetailsList", courseDetailsList.get(0));
				
				List<Fees> feesInformationList= commonService.getAllFeesRelatedInformationForStudent(course);
				model.addAttribute("feesInformationList", feesInformationList);
				
			}catch(Exception e){
				logger.error("Exception in closedTaskList() in CommonController: ", e);
				e.printStackTrace();
			}
			return "common/getAllProgramDetails";
		}

		/*added by ranita.sur on 08092017*/
		@RequestMapping(value = "/viewFineDetails", method = RequestMethod.GET)
		String viewFineDetails(HttpServletRequest request,HttpServletResponse response, ModelMap model,
							@RequestParam("userId") String userId,
							@RequestParam("courseCode") String courseCode,
							@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String section = null;
			System.out.println("LN 2877 ::"+userId);
			try {
				Course course = commonService.getCourseAgainstCourseCode(courseCode, userId);
				//System.out.println("LN 2880::"+course.getCourseName());
				String courseName = courseCode;
				List<FeesCategory> studentFeesTemplateAmountDetailsList = backOfficeService.getStudentFeesTemplateAmountDetails(courseName);
				Resource resource = commonService.getResourceAgainstUserId(userId);
				model.addAttribute("resource", resource);
				model.addAttribute("course", course);
				List<BookAllocation> bookFineDetailsList = commonService.getBookFineDetails(userId.trim());
				
				model.addAttribute("bookFineDetailsList", bookFineDetailsList);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("rejectTask() In CommonController.java: Exception"
						+ e);
			}
			return "common/viewFineDetails";
		}
		
		@RequestMapping(value = "/storeChatDetails", method = RequestMethod.GET)
		public @ResponseBody
		String storeChatDetails(@RequestParam("From") String from,@RequestParam("To") String to,@RequestParam("msg") String msg) {
		String status="false";
		
		try{
			System.out.println("From :====="+from);
			System.out.println("To======="+to);
			System.out.println("msg======"+msg);
			status = commonService.insertChatDetailsForIndividualChat(from,to,msg);
			System.out.println("********************");
			System.out.println("status=="+status);
			}catch(Exception e){
				logger.error("Individual Chat Log Failed to write In Common Controller", e);
			}
			return null;
		}
		
		@RequestMapping(value = "/markChatRead", method = RequestMethod.GET)
		public @ResponseBody
		String markChatRead(@RequestParam("user") String user) {
		String status="false";
		
		try{
			System.out.println("user :====="+user);
			status = commonService.updateChatStatusToReadForAUser(user);
			System.out.println("********************");
			System.out.println("status=="+status);
			}catch(Exception e){
				logger.error("Individual Chat Log Failed to write In Common Controller", e);
			}
			return null;
		}
		
		
		@RequestMapping(value = "/getChatDetailsForIndividualChatForAUser", method = RequestMethod.GET)
		public @ResponseBody
		String getChatDetailsForIndividualChatForAUser(@RequestParam("to") String from,@RequestParam("from") String to) {
		
		String chatDetails = "";
		try{
			System.out.println("from :====="+from);
			System.out.println("to :====="+to);
			List<Notification> chatNotificationList = commonService.getChatDetailsForIndividualChatForAUser(to,from);
			System.out.println("********************");
			for(Notification notification : chatNotificationList){
				chatDetails = chatDetails + notification.getNotificationName()+"*"+notification.getNotificationDesc()+";";
			}
			System.out.println("chatDetails====="+chatDetails);
			}catch(Exception e){
				logger.error("Individual Chat Log Failed to write In Common Controller", e);
			}
		return (new Gson().toJson(chatDetails));
		}
		
		@RequestMapping(value = "/viewDownloadEmailAttachment", method = RequestMethod.GET)
		public String viewDownloadEmailAttachment(HttpServletRequest request,
											HttpServletResponse response, ModelMap model,
											@RequestParam("folderParam") String folderParam,
											@RequestParam("fileParam") String fileParam) {
			try {
				logger.info("Inside viewDownloadEmailAttachment() of AcademicsController");
				fileUploadDownload.downloadFile(response, folderParam, fileParam);
				
			} catch (Exception ce){
				logger.error("Exception in viewDownloadEmailAttachment() of AcademicsController", ce);
			}
			return null;
		}
		
		@RequestMapping(value = "/updateIntoMyEvents", method = RequestMethod.GET)
		
		String updateIntoMyEvents(HttpServletRequest request, HttpServletResponse response, 
								ModelMap model,@RequestParam("startTime") String startTime,
								@RequestParam("endTime") String endTime,@RequestParam("subject") String subject,
								@RequestParam("userId") String userId,@RequestParam("emailBody") String mailBody,
								@RequestParam("emailCode") String emailCode,
								@RequestParam("senderUserId") String senderUserId,
								@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String emailDetails = "";
			
			
			
			try {
				
				logger.info("updateIntoMyEvents() In CommonController.java");
				ServerConfiguration serverConfiguration = loginService.getServerConfigurationDB();
				String urlAsParameter = "jdbc:postgresql://"+serverConfiguration.getJdbcURL()+":"+serverConfiguration.getJdbcPort()+"/"+serverConfiguration.getJdbcDatabaseName();
				
				String userName = serverConfiguration.getJdbcUserName();
				String passwordAsParameter = serverConfiguration.getJdbcPassword();
				String driverClassName = serverConfiguration.getJdbcDriverClassName();
				
				Class.forName(driverClassName);
	            String url = urlAsParameter;
	            String user = userName;
	            String password = passwordAsParameter;
	            java.sql.Connection conn = DriverManager.getConnection(url, user, password);
		        java.sql.PreparedStatement ps = null;
		        java.sql.PreparedStatement ps1 = null;
		        java.sql.ResultSet result = null;
		        
				String[] startTimeArr = startTime.split(" ");
				
			
				String startMonth = convertMonthToIntegerValue(startTimeArr[1]);
				String[] startArr = startTimeArr[3].split(":");
				
				String[] endTimeArr = endTime.split(" ");
				String endMonth = convertMonthToIntegerValue(endTimeArr[1]);
				String[] endArr = endTimeArr[3].split(":");
				
				DHXEvent icsEvent = new DHXEvent();
				
				java.util.Calendar calendar1 = java.util.Calendar.getInstance();
				calendar1.clear();
				calendar1.set(Integer.parseInt(startTimeArr[5]),Integer.parseInt(startMonth)-1, Integer.parseInt(startTimeArr[2]), Integer.parseInt(startArr[0]), Integer.parseInt(startArr[1]), Integer.parseInt(startArr[2]));
				Date start = calendar1.getTime();
								
				java.util.Calendar calendar2 = java.util.Calendar.getInstance();
				calendar2.clear();
				calendar2.set(Integer.parseInt(endTimeArr[5]),Integer.parseInt(endMonth)-1, Integer.parseInt(endTimeArr[2]), Integer.parseInt(endArr[0]), Integer.parseInt(endArr[1]), Integer.parseInt(endArr[2]));
				Date end = calendar2.getTime();
				
				
				icsEvent.setStart_date(start);
				icsEvent.setEnd_date(end);
				icsEvent.setText(mailBody);
			    
				String query = null;
	    		String start_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
	                                        format(icsEvent.getStart_date());
	    		String end_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
	                                      format(icsEvent.getEnd_date());
	    		
	    		
	    		query = "INSERT INTO my_events (my_events_usrid, my_events_start_date, my_events_end_date, my_events_desc)"+ 
	    					"VALUES (?, ?, ?, ?)";
					ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, sessionObject.getUserId());
					ps.setString(2, start_date);
					ps.setString(3, end_date);
					ps.setString(4, icsEvent.getText());
				
					ps1 = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ps1.setString(1, senderUserId);
					ps1.setString(2, start_date);
					ps1.setString(3, end_date);
					ps1.setString(4, icsEvent.getText());
					
	    		if (ps!=null) {
	    			ps.executeUpdate();
	    			result = ps.getGeneratedKeys();
	    			if (result.next()) {
	    				icsEvent.setId(result.getInt(1));
	    			}
	    		}
	    		
	    		if (ps1!=null) {
	    			ps1.executeUpdate();
	    			result = ps1.getGeneratedKeys();
	    			if (result.next()) {
	    				icsEvent.setId(result.getInt(1));
	    			}
	    		}
	    		
	    		
	    		EmailDetails emailDetailsObj = new EmailDetails();
	    		emailDetailsObj.setEmailDetailsCode(emailCode);
	    		
	    		List<EmailDetails> emailDetailsList =  new ArrayList<EmailDetails>();
	    		emailDetailsList.add(emailDetailsObj);
	    		List<EmailDetails> emailList = commonService.inactiveEmailFromInBox(emailDetailsList);
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				logger.error("updateIntoMyEvents() In CommonController.java: NullPointerException"
						+ e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("updateIntoMyEvents() In CommonController.java: Exception"
						+ e);
			}
			return getEmailDetails( request, response, model,sessionObject, null, null) ;
			//return "common/sentEmailDetails";
		}
		
		private String convertMonthToIntegerValue(String month){
			DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM")
                    .withLocale(Locale.ENGLISH);
			TemporalAccessor accessor = parser.parse(month);
			System.out.println(accessor.get(ChronoField.MONTH_OF_YEAR));
			int monthValue = accessor.get(ChronoField.MONTH_OF_YEAR);
			String monthString = "";
			if(monthValue<10){
				monthString = "0"+monthValue;
			}else{
				monthString = monthValue+"";
			}
			return monthString;
		}
		
		
		//Modified By Naimisha 01112017
		@RequestMapping(value = "/editTask", method = RequestMethod.GET)
		public ModelAndView editTask(HttpServletRequest request,
									   HttpServletResponse response, ModelMap model, Task task,
									   @ModelAttribute("sessionObject") SessionObject sessionObject) {
			
			logger.info("Task DETAILS:"+task.getTaskCode());
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			try {
				logger.info("In editTask() method of TicketController");
				task.setUpdatedBy(sessionObject.getUserId());
				Ticket taskDetails = commonService.getTaskDetailsOfATask(task);
				
				String department = taskDetails.getDepartment();
				
				
				if(null != department){
					String budget = inventoryService.getDepartmentBudgetDetails(department);
					
					System.out.println("budget=="+budget);
					String[] budgetDetails = budget.split("~");
					model.addAttribute("availableBudget", budgetDetails[2]);
				}
				
				
				
				boolean isLinked = taskDetails.isLinked();
				boolean isFinance = taskDetails.isFinance();
				
				model.addAttribute("isLinked", isLinked);
				model.addAttribute("isFinance", isFinance);
				
				
				System.out.println("isLinked=="+isLinked);
				System.out.println("isFinance=="+isFinance);
				/** Added by Naimisha 13042014**/
				
				//Added By Naimisha 29032018
				String ticketCode = taskDetails.getMessage();
				System.out.println("ticketCode===="+ticketCode);
				String ticketServiceCodeValue = taskDetails.getComment();
				System.out.println("ticketServiceCodeValue===="+ticketServiceCodeValue);
				if(ticketServiceCodeValue.equalsIgnoreCase("InventoryPO")){  
					
					CommodityPurchaseOrder commodityPurchaseOrder = commonService.getCommodityPurchaseOrderDetaialsForATicket(ticketCode);
					if(null != commodityPurchaseOrder){
						model.addAttribute("commodityPurchaseOrder", commodityPurchaseOrder);
					}
				}
				
				if(ticketServiceCodeValue.equalsIgnoreCase("StudentLeave")){
					Ticket stuLeaveDetailsObj = commonService.getStudentLeaveDetailsAgainstTicket(ticketCode);
					if(null != stuLeaveDetailsObj){
						System.out.println("standard==="+stuLeaveDetailsObj.getStandard());
						model.addAttribute("stuLeaveDetailsObj", stuLeaveDetailsObj);
					}
				}
				List<TicketStatus> taskStatusList = commonService.getAllTaskStatusList(task);
				model.addAttribute("taskStatusList", taskStatusList);
				
				System.out.println("taskStatusList size()=="+taskStatusList.size());
					
				List<Ledger>ledgerList = financeService.getLedgerList();
				model.addAttribute("ledgerList", ledgerList);
				
				List<Department> departmentList = erpService.getAllDepartment(); // Added By Naimisha 20042018
				model.addAttribute("departmentList", departmentList);
				
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				
				if(task != null ){
					logger.info(task.getTaskObjectId());
					String path = repository+"/"+ticketAttachmentsPath +task.getTaskObjectId()+"/";
					File filedir = new File(path);
					if (!filedir.exists()) {					
					}
					else{
						File folder = new File(path);
						File[] listOfFiles = folder.listFiles();	
						for (int i = 0; i < listOfFiles.length; i++) {
							File file = listOfFiles[i];
							if (file.isFile()) {
								Attachment attch = new Attachment();
								attch.setAttachmentName(file.getName());
								attachmentList.add(attch);					 				 
							} 
						}				
					}
				}
				taskDetails.setAttachmentList(attachmentList);
				taskDetails.setTicketRecId(task.getTaskObjectId());;
				model.addAttribute("taskDetails", taskDetails);
				
				System.out.println("taskType=="+task.getTaskType());
				model.addAttribute("taskType",task.getTaskType());
				
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("editTask() In TicketController.java: Exception", e);
				
			}
			return new ModelAndView("ticketing/editTask");
		}
		
		
		@RequestMapping(value = "/updateTicket", method = RequestMethod.POST)
		public ModelAndView updateTicket(HttpServletRequest request,
										 HttpServletResponse response, 
										 ModelMap model, 									 
										 Ticket ticket,
										 @RequestParam(required = false, value ="additinalTask") String[] additionalTaskArr,
										 @RequestParam(required = false, value = "additinoalApproval") String[] additionalApprovedBy,
										 @RequestParam(required = false, value ="additionalLevel") String[] additionalLevelArr,
										 @ModelAttribute("sessionObject") SessionObject sessionObject) {
			ModelAndView mav = new ModelAndView("common/listTicket");
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			try {
				logger.info("In updateTicket() method of TicketController");	
				String serviceTypeCode = ticket.getTicketService().getTicketServiceCode();
				System.out.println("PRAD in CONTROLLER:"+ticket.getTicketService().getTicketServiceCode());
				ticket.setUpdatedBy(sessionObject.getUserId());
				String[] approvedBy = request.getParameterValues("approval");
				String[] taskArr = request.getParameterValues("task");
				String[] levelArr = request.getParameterValues("level");
				
				//String[] additionalApprovedBy = request.getParameterValues("additinoalApproval");
				////String[] additionalTaskArr = request.getParameterValues("additinalTask");
				//String[] additionalLevelArr = request.getParameterValues("additionalLevel");
				
			/*	System.out.println("additionalApprovedBy length==="+additionalApprovedBy.length);
				System.out.println("additionalTaskArr length===="+additionalTaskArr.length);
				System.out.println("additionalLevelArr====="+additionalLevelArr.length);*/
				
				List<String>taskList = new ArrayList<>();
				List<String>leveList = new ArrayList<>();
				List<String>resourceList = new ArrayList<>();
				
				
				
				
				if(null != taskArr && taskArr.length !=0){
					for(int j = 0;j<taskArr.length ;j++ ){
						System.out.println("taskArr value ===="+taskArr[j]);
						taskList.add(taskArr[j]);
					}
				}
				
				
				if(null != additionalTaskArr && additionalTaskArr.length !=0){
					for(int j = 0;j<additionalTaskArr.length ;j++ ){
						System.out.println("taskArr value ===="+additionalTaskArr[j]);
						taskList.add(additionalTaskArr[j]);
					}
				}
				
				
				
				if(null != levelArr && levelArr.length !=0){
					for(int i = 0;i<levelArr.length ;i++ ){
						System.out.println("levelArr value ===="+levelArr[i]);
						leveList.add(levelArr[i]);
					}
				}
				
				if(null != additionalLevelArr && additionalLevelArr.length !=0){
					for(int i = 0;i<additionalLevelArr.length ;i++ ){
						System.out.println("levelArr value ===="+additionalLevelArr[i]);
						leveList.add(additionalLevelArr[i]);
					}
				}
				if(null != approvedBy && approvedBy.length !=0){
					for(int i = 1;i<=approvedBy.length ;i++ ){
						String resouceUserId = "";
						if(approvedBy[i-1].equalsIgnoreCase("individual")){
							 resouceUserId = request.getParameter("resource"+i);
							
						}else if(approvedBy[i-1].equalsIgnoreCase("group")){
							String groupCode = request.getParameter("group"+i);
							resouceUserId = commonService.getResourceUserIdForMinimumNoOfOpenTicket(groupCode);
						}
						resourceList.add(resouceUserId);
					}
				}
				
				
				if(null != additionalApprovedBy && additionalApprovedBy.length !=0){
					for(int i = 1;i<=additionalApprovedBy.length ;i++ ){
						String resouceUserId = "";
						if(approvedBy[i-1].equalsIgnoreCase("individual")){
							 resouceUserId = request.getParameter("additionalResource"+i);
							
						}else if(approvedBy[i-1].equalsIgnoreCase("group")){
							String groupCode = request.getParameter("group"+i);
							resouceUserId = commonService.getResourceUserIdForMinimumNoOfOpenTicket(groupCode);
						}
						resourceList.add(resouceUserId);
					}
				}
				ticket.setTaskList(taskList);
				ticket.setLeveList(leveList);
				ticket.setResourceList(resourceList);
				
				System.out.println("PRAD Standard==="+ticket.getStandard());
				if((serviceTypeCode.equalsIgnoreCase("StudentLeave"))){
					List<StudentAttendance> studentAttendanceList = getStudentAttandanceList(ticket);
					
						ticket.setStudentAttendanceList(studentAttendanceList);
				}
					
				//}
				
				ticket.setUrl(sendLeaveDetailsOfCadet);	
				ticket.setUserName(portalUserName);
				ticket.setPassword(portalPassWord);
				ticket.setWebIQAvailable(isWebIQAvailable);
				System.out.println("PRAD HERE in CONTROLLER");
				ticket = commonService.updateTicket(ticket); 
				/***new code added for save this edited file into external repository**/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				if(serviceTypeCode.equalsIgnoreCase("NOC")){
					String ticketStatus = ticket.getStatus();
					if(ticketStatus.equalsIgnoreCase("APPROVED")){
						ticket.setRootPath(repository);
						String status = commonService.createXMLFileForNOC(ticket,reportNOCConfigFilePath, reportNOCXSLTFilePath, ticketAttachmentsPath);
						String path = repository+"/"+ticketAttachmentsPath +ticket.getTicketCode()+"/";
					}
				}else if(serviceTypeCode.equalsIgnoreCase("GATEPASS")){
					String ticketStatus = ticket.getStatus();
					if(ticketStatus.equalsIgnoreCase("APPROVED")){
						System.out.println("LN1577...in gatepass...closed");
						ticket.setRootPath(repository);
						String status = commonService.createXMLFileForGatePass(ticket,reportGatePassConfigFilePath,reportGatePassXSLTFilePath,ticketAttachmentsPath);
						String path = repository+"/"+ticketAttachmentsPath +ticket.getTicketCode()+"/";
					}
				}else{
					if(ticket != null){
						if(ticket.getTicketCode() != null){
							ticket.setTicketRecId(ticket.getTicketCode());
							Attachment attachment = new Attachment();
							attachment.setStorageRootPath(repository);
							attachment.setFolderName(ticketAttachmentsPath);					
							if(ticket.getUploadFile()!=null){ 
								ticket.getUploadFile().setAttachment(attachment);
								commonService.ticketDocumentUpload(ticket);
							}		
						}	
					}
				}
					
				
				if(ticket != null ){
					logger.info(ticket.getTicketCode());
					String path = repository+"/"+ticketAttachmentsPath +ticket.getTicketCode()+"/";
					File filedir = new File(path);
					if (!filedir.exists()) {					
					}
					else{
						File folder = new File(path);
						File[] listOfFiles = folder.listFiles();	
						for (int i = 0; i < listOfFiles.length; i++) {
							File file = listOfFiles[i];
							if (file.isFile()) {
								Attachment attch = new Attachment();
								attch.setAttachmentName(file.getName());
								attachmentList.add(attch);					 				 
							} 
						}				
					}
				}
				ResourceType rType = commonService.getResourceTypeofUser(sessionObject.getUserId());
				if (rType != null){
					model.addAttribute("resourceType", rType.getResourceTypeName());				
				}
				ticket.setAttachmentList(attachmentList);
				model.addAttribute("ticket", ticket);

				String updateStatus = ticket.getQueryStatus();
				model.addAttribute("updateStatus", updateStatus);
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("updateTicket() In TicketController.java: Exception" + e);
			}
			return  inwardListTicket( request,  response,  model,sessionObject);
		}
		
		
		
//Added By NAimisha 01112017
		
		/*@RequestMapping(value = "/viewMyTimeTable", method = RequestMethod.GET)
		public ModelAndView viewMyTimeTable(HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			ModelAndView mav = new ModelAndView("common/viewMyTimeTable");
			try {
				logger.info("calling viewMyTimeTable()GET method of CommonController");
				List<Term> termList= backOfficeService.getAllTermListToShowInJSP();
				model.addAttribute("termList", termList);
				String userId = sessionObject.getUserId();
				model.addAttribute("userId", userId);
				Resource resourceNew= backOfficeService.getTeacherNameAgainstTeacherUserId(userId);
				String teacherName = resourceNew.getTeacherName();
				model.addAttribute("teacherName", teacherName);
				
			} catch (Exception e) {
				logger.error("Exception in viewMyTimeTable() GET method Of CommonController",e);
			}
			return mav;
		}
		
		@RequestMapping(value = "/getMyTimeTableGridData", method = RequestMethod.GET)
		public  @ResponseBody String getTimeTableGridData(@ModelAttribute("semester") String semester,
				@ModelAttribute("userId") String userId) {
			
			StringBuffer sb = new StringBuffer();
			String strGridData = null;
			try {
				System.out.println("semester====="+semester);
				List<TimeTableGridData> gridDataList = commonService.getMyTimeTableGridData(semester,userId);
			//	List<Resource> teacherList = backOfficeService.getTeacherList();
				
				for(TimeTableGridData gridData : gridDataList){
			    	sb.append(gridData.getGridId() + "-" + gridData.getGridData() + "|");
			    }
				sb.append("*#*");
				
				for(Resource resource :teacherList){
					sb.append(resource.getTeacherName());
					sb.append("*");
				}
				
				sb.append("*#*");
				
				TimeTableParameter timeTableParameter = backOfficeService.getConfigurationTimeTableParameters();
				//int totalNoOfColumn = Integer.parseInt(timeTableParameter.getNoOfPeriods())* Integer.parseInt(timeTableParameter.getNoOfWorkingDays());
				
				//String totalNoOfColumnValue = totalNoOfColumn + "";
				
				sb.append(timeTableParameter.getNoOfWorkingDays());
				sb.append("*#*");
				sb.append(timeTableParameter.getNoOfPeriods());
				
				strGridData = sb.toString();
				System.out.println("strGridData===="+strGridData);
			    
			} catch (Exception ce){
				ce.printStackTrace();
				logger.error("Exception in method getTimeTableGridData-GET of BackOfficeController", ce);
			}
			return strGridData.substring(0, strGridData.length());
		}
		

		@RequestMapping(value = "/viewTimeTableForStubent", method = RequestMethod.GET)
		public ModelAndView viewTimeTableForStubent(HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			ModelAndView mav = new ModelAndView("common/viewTimeTableForStubent");
			try {
				logger.info("calling viewTimeTableForStubent()GET method of CommonController");
				List<Term> termList= backOfficeService.getAllTermListToShowInJSP();
				model.addAttribute("termList", termList);
				String userId = sessionObject.getUserId();
				model.addAttribute("userId", userId);
				List<Course>courseList = loginService.getCourseListForAStudent(userId);
				model.addAttribute("courseList", courseList);
				Resource resourceNew = backOfficeService.getTeacherNameAgainstTeacherUserId(userId);
				String teacherName = resourceNew.getTeacherName();
				model.addAttribute("teacherName", teacherName);
				
			} catch (Exception e) {
				logger.error("Exception in viewTimeTableForStubent() GET method Of CommonController",e);
			}
			return mav;
		}*/
		
		/*@RequestMapping(value = "/getTimeTableGridDataForStudent", method = RequestMethod.GET)
		public  @ResponseBody String getTimeTableGridDataForStudent(@ModelAttribute("semester") String semester,
				@ModelAttribute("program") String program) {
			
			StringBuffer sb = new StringBuffer();
			String strGridData = null;
			try {
				System.out.println("semester====="+semester);
				List<TimeTableGridData> gridDataList = commonService.getTimeTableGridDataForStudent(semester,program);
			//	List<Resource> teacherList = backOfficeService.getTeacherList();
				
				for(TimeTableGridData gridData : gridDataList){
			    	sb.append(gridData.getGridId() + "-" + gridData.getGridData() + "|");
			    }
				sb.append("*#*");
				
				for(Resource resource :teacherList){
					sb.append(resource.getTeacherName());
					sb.append("*");
				}
				
				sb.append("*#*");
				
				TimeTableParameter timeTableParameter = backOfficeService.getConfigurationTimeTableParameters();
				//int totalNoOfColumn = Integer.parseInt(timeTableParameter.getNoOfPeriods())* Integer.parseInt(timeTableParameter.getNoOfWorkingDays());
				
				//String totalNoOfColumnValue = totalNoOfColumn + "";
				
				sb.append(timeTableParameter.getNoOfWorkingDays());
				sb.append("*#*");
				sb.append(timeTableParameter.getNoOfPeriods());
				
				strGridData = sb.toString();
				System.out.println("strGridData===="+strGridData);
			    
			} catch (Exception ce){
				ce.printStackTrace();
				logger.error("Exception in method getTimeTableGridData-GET of BackOfficeController", ce);
			}
			return strGridData.substring(0, strGridData.length());
		}*/
		
		//Added by naimisha 06112017
		@RequestMapping(value = "/updateMyTicket", method = RequestMethod.POST)
		public ModelAndView updateMyTicket(HttpServletRequest request,
										 HttpServletResponse response, 
										 ModelMap model, 									 
										 Ticket ticket,
										 @ModelAttribute("sessionObject") SessionObject sessionObject) {
			ModelAndView mav = new ModelAndView("common/listTicket");
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			try {
				logger.info("In updateMyTicket() method of TicketController");	
				String serviceTypeCode = ticket.getTicketService().getTicketServiceCode();
				ticket.setUpdatedBy(sessionObject.getUserId());
				
				ticket = commonService.updateMyTicket(ticket); 
				/***new code added for save this edited file into external repository**/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				if(serviceTypeCode.equalsIgnoreCase("NOC")){
					String ticketStatus = ticket.getStatus();
					if(ticketStatus.equalsIgnoreCase("APPROVED")){
						ticket.setRootPath(repository);
						String status = commonService.createXMLFileForNOC(ticket,reportNOCConfigFilePath, reportNOCXSLTFilePath, ticketAttachmentsPath);
						String path = repository+"/"+ticketAttachmentsPath +ticket.getTicketCode()+"/";
					}
				}else if(serviceTypeCode.equalsIgnoreCase("GATEPASS")){
					String ticketStatus = ticket.getStatus();
					if(ticketStatus.equalsIgnoreCase("APPROVED")){
						System.out.println("LN1577...in gatepass...closed");
						ticket.setRootPath(repository);
						String status = commonService.createXMLFileForGatePass(ticket,reportGatePassConfigFilePath,reportGatePassXSLTFilePath,ticketAttachmentsPath);
						String path = repository+"/"+ticketAttachmentsPath +ticket.getTicketCode()+"/";
					}
				}else{
					if(ticket != null){
						if(ticket.getTicketCode() != null){
							ticket.setTicketRecId(ticket.getTicketCode());
							Attachment attachment = new Attachment();
							attachment.setStorageRootPath(repository);
							attachment.setFolderName(ticketAttachmentsPath);					
							if(ticket.getUploadFile()!=null){ 
								ticket.getUploadFile().setAttachment(attachment);
								commonService.ticketDocumentUpload(ticket);
							}		
						}	
					}
				}
					
				
				if(ticket != null ){
					logger.info(ticket.getTicketCode());
					String path = repository+"/"+ticketAttachmentsPath +ticket.getTicketCode()+"/";
					File filedir = new File(path);
					if (!filedir.exists()) {					
					}
					else{
						File folder = new File(path);
						File[] listOfFiles = folder.listFiles();	
						for (int i = 0; i < listOfFiles.length; i++) {
							File file = listOfFiles[i];
							if (file.isFile()) {
								Attachment attch = new Attachment();
								attch.setAttachmentName(file.getName());
								attachmentList.add(attch);					 				 
							} 
						}				
					}
				}
				ResourceType rType = commonService.getResourceTypeofUser(sessionObject.getUserId());
				if (rType != null){
					model.addAttribute("resourceType", rType.getResourceTypeName());				
				}
				ticket.setAttachmentList(attachmentList);
				model.addAttribute("ticket", ticket);

				String updateStatus = ticket.getQueryStatus();
				model.addAttribute("updateStatus", updateStatus);
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("updateTicket() In TicketController.java: Exception" + e);
			}
			return  listTicketGET( request,  response,  model,sessionObject);
		}
		
		@RequestMapping(value = "/inwardListTicket", method = RequestMethod.GET)
		public ModelAndView inwardListTicket(HttpServletRequest request, HttpServletResponse response, 
				ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject) {
			ModelAndView mav = new ModelAndView("ticketing/listTicket");
			try {
				logger.info("In inwardListTicket() method of TicketController");
				List<Ticket> ticketList = ticketService.getInwardTicketList(sessionObject.getUserId());
				if (ticketList != null && ticketList.size() != 0) {
					model.addAttribute("ticketList", ticketList);
					
				}
			} catch (Exception e) {
				logger.error("inwardListTicket() In TicketController.java: Exception"+ e);
			}
			return mav;
		}
		
		//Added By NAimisha 29032018
		@RequestMapping(value = "/getUserListAssociatedWithATicket", method = RequestMethod.GET)
		public @ResponseBody
		String getUserListAssociatedWithATicket(@RequestParam("ticketCode") String ticketCode) {
			String users="";
			try{
				users = commonService.getUserListAssociatedWithATicket(ticketCode);
			}catch(Exception e){
				logger.error("exception in getUserListAssociatedWithATicket(ticketCode) in CommonController: ", e);
			}
			return (new Gson().toJson(users));
		}
		
		/**
		 * @author anup.roy
		 * this method is for view and create page status for any item */
		
		@RequestMapping(value = "/createStatusOfItem", method = RequestMethod.GET)
		public String createStatusOfItem(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			String strView = "common/createStatusOfItem";
			List<StatusOfItem> statusList = null;
			try {
				logger.info("Inside Method createStatusOfItem-GET of CommonController");
				statusList = commonService.getAllStatusOfItems();
				model.addAttribute("statusList", statusList);
			} catch (Exception e){
				logger.error("Exception in method createStatusOfItem-GET of CommonController", e);
				e.printStackTrace();
			}
			return strView;
		}
		
		/**
		 * @author anup.roy
		 * this method is for submitting a status of item*/
		
		@RequestMapping(value = "/submitStatusOfItem", method = RequestMethod.POST)
		public String submitStatusOfItem(HttpServletRequest req, HttpServletResponse res, ModelMap model, 
				StatusOfItem status, @ModelAttribute("sessionObject") SessionObject sessionObj) {
			try {
				logger.info("In String submitStatusOfItem(HttpServletRequest req, HttpServletResponse res, ModelMap model, StatusOfItem status) of CommonController.java");
				status.setUpdatedBy(sessionObj.getUserId());
				String insertStatus = commonService.submitStatusOfItem(status);
				model.addAttribute("insertStatus", insertStatus);
			}catch (Exception e) {
				logger.error("Exception in String submitStatusOfItem(HttpServletRequest req, HttpServletResponse res, ModelMap model, StatusOfItem status) of CommonController.java:"+e);
				e.printStackTrace();
			}
			return createStatusOfItem(req, res, model);
		}

		//Added By Naimisha 09042018
		
		@RequestMapping(value = "/createTicketStatus", method = RequestMethod.GET)
		public ModelAndView createTicketStatus(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			try {
				logger.info("In createTicketStatus() method of AdministratorController");
				
				List<Ticket>ticketStatusList = administratorService.getAllTicketStatusList();
				model.addAttribute("ticketStatusList", ticketStatusList);
				
				
				/*List<Ticket>taskStatusList = administratorService.getAllTaskStatusList();
				model.addAttribute("taskStatusList", taskStatusList);*/
			
			} catch (Exception e) {
				logger.error("createTicketStatus() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("common/createTicketStatus");
		}
		
		
		
		@RequestMapping(value = "/createTicketStatus", method = RequestMethod.POST)
		public ModelAndView createTaskStatus(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,Ticket ticket,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In createTaskStatus() POST method of AdministratorController");
				
				ticket.setUpdatedBy(sessionObject.getUserId());
				String status = commonService.insertTicketStatus(ticket);
				model.addAttribute("status", status);	
				
				if(status.equalsIgnoreCase("success")){	
					model.addAttribute("insertStatus", "success");
					model.addAttribute("msg", "Successfully Created");
				}else{
					model.addAttribute("insertStatus", "fail");
					model.addAttribute("msg", "Failed To Create");
					
				}	
				
			
			} catch (Exception e) {
				logger.error("createTaskStatus() POST In AdministratorController.java: Exception"+ e);
			}
			return createTicketStatus(request,response,model);
		}

		/**
		 * @author sourav.bhadra 09-04-2018
		 * this method is for setting budget for sub departments */
		
		@RequestMapping(value = "/subDepartmentsBudgetAllocation", method = RequestMethod.GET)
		public String subDepartmentsBudgetAllocation(HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObj) {
			String strView = "common/subDepartmentsBudgetAllocation";
			try {
				logger.info("Inside Method subDepartmentsBudgetAllocation-GET of CommonController");
				List<FinancialYear> financialYearList = commonService.getFinancialYearList();
				if(null!=financialYearList && financialYearList.size() != 0){
					model.addAttribute("financialYearList", financialYearList);
				}
				Department dept = commonService.getDepartmentForAUser(sessionObj.getUserId());
				model.addAttribute("department", dept.getDepartmentCode());
				/*System.out.println("LN4344 :: CommonController\n*********************************");
				System.out.println("Current Role :: "+sessionObj.getCurrentRoleOrAccess());
				System.out.println("Resource Type :: "+sessionObj.getResourceTpye());
				System.out.println("User Id :: "+sessionObj.getUserId());
				System.out.println("User Department :: "+dept);*/
			} catch (Exception e){
				logger.error("Exception in method createStatusOfItem-GET of CommonController", e);
				e.printStackTrace();
			}
			return strView;
		}
		
		/**
		 * @author sourav.bhadra 10-04-2018
		 * this method is for getting sub departments for budget */
		@RequestMapping(value = "/getBudgetOfSubDeptsForAFinancialYear", method = RequestMethod.GET)
		public @ResponseBody
		String getBudgetOfSubDeptsForAFinancialYear(@RequestParam("financialYear") String financialYear, 
				@RequestParam("department") String department) {
			String budget="";
			try {
				/*System.out.println("LN4365 :: CommonController\n*********************************");
				System.out.println("Financial Year :: "+financialYear);
				System.out.println("Department :: "+department);*/
				budget = commonService.getBudgetOfSubDeptsForAFinancialYear(financialYear, department);
				System.out.println("LN3911 :: "+budget);
			}catch (Exception e){
				e.printStackTrace();
			}
			return (new Gson().toJson(budget));
		}
		
		/**
		 * @author sourav.bhadra 11-04-2018
		 * this method is for submitting sub departments for budget
		 */
		@RequestMapping(value = "/saveSubDeptsBudget", method = RequestMethod.POST)
		public String saveSubDeptsBudget(HttpServletRequest request,
										HttpServletResponse response,												
										ModelMap model,
										@RequestParam("financialYear") String financialYear,
										@RequestParam("department") String department,
										@RequestParam("availableFund") String availableFund,
										@RequestParam("subDepartment") String[] subDepartment,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String status="";
			try{
				List<Budget> budgetList=new ArrayList<Budget>();
				for(int i=0;i<subDepartment.length;i++){
					Budget b=new Budget();
					String percent = request.getParameter(subDepartment[i]+"_percentage");
					String actualAmount = request.getParameter(subDepartment[i]+"ActualAmount");
					String totalExpences = request.getParameter(subDepartment[i]+"_totalExpences");
					String balance = request.getParameter(subDepartment[i]+"balance");
					String reserve = request.getParameter(subDepartment[i]+"reserveBalance");
					if((null !=actualAmount && actualAmount != "") && (null !=balance && balance != "") &&  (null !=totalExpences && totalExpences != "")){
						b.setDepartment(subDepartment[i]);				
						b.setExpectedIncome(Double.parseDouble(percent));
						b.setActualIncome(Double.parseDouble(actualAmount));
						b.setTotalExpence(Double.parseDouble(totalExpences));
						b.setExpectedExpense(Double.parseDouble(balance));
						b.setReserveFund(Double.parseDouble(reserve));
						b.setAcademicYear(financialYear);
						b.setUpdatedBy(sessionObject.getUserId());
						budgetList.add(b);
					}
					
				}
				status = financeService.saveBudget(budgetList);
				
				/* update parent department's available balance */
				if((null !=availableFund && availableFund != "")){
					Budget budgetDetails=new Budget();
					budgetDetails.setUpdatedBy(sessionObject.getUserId());
					budgetDetails.setAcademicYear(financialYear);
					budgetDetails.setDepartment(department);
					budgetDetails.setExpectedIncome(Double.parseDouble(availableFund));
					
					commonService.updateParentDepartmentBudgetDetails(budgetDetails);
				}
				model.addAttribute("status",status);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				System.out.println(status);
			}
			return subDepartmentsBudgetAllocation(request, response, model, sessionObject);
		}
		
		/**
		 * @author sourav.bhadra 09-04-2018
		 * this method is for reserve fund estimation of a department */
		
		@RequestMapping(value = "/reserveFundEstimationForADepartment", method = RequestMethod.GET)
		public String reserveFundEstimationForADepartment(HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObj) {
			String strView = "common/reserveFundEstimation";
			try {
				logger.info("Inside Method subDepartmentsBudgetAllocation-GET of CommonController");
				FinancialYear currentFinancialYear = commonService.getFinancialYear();
				model.addAttribute("currentFinancialYear", currentFinancialYear.getFinancialYearName());
				
				Department dept = commonService.getDepartmentForAUser(sessionObj.getUserId());
				model.addAttribute("department", dept.getDepartmentCode());
				
				Budget budgetDetails = commonService.getBudgetDetailsForADepartment(currentFinancialYear.getFinancialYearName(), dept.getDepartmentCode());
				model.addAttribute("budgetDetails", budgetDetails);
				
			} catch (Exception e){
				logger.error("Exception in method createStatusOfItem-GET of CommonController", e);
				e.printStackTrace();
			}
			return strView;
		}
		
		/**
		 * @author sourav.bhadra 11-04-2018
		 * this method is for submitting estimated reserve fund
		 */
		@RequestMapping(value = "/saveEstimatedReserveFundForADepartment", method = RequestMethod.POST)
		public String saveEstimatedReserveFundForADepartment(HttpServletRequest request,
										HttpServletResponse response,												
										ModelMap model,
										@RequestParam("financialYear") String financialYear,
										@RequestParam("department") String department,
										@RequestParam("availableFund") String availableFund,
										@RequestParam("reserveFund") String reserveFund,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String status="";
			try{
				Budget reserveFundDetails = new Budget();
				reserveFundDetails.setUpdatedBy(sessionObject.getUserId());
				reserveFundDetails.setAcademicYear(financialYear);
				reserveFundDetails.setDepartment(department);
				reserveFundDetails.setActualIncome(Double.parseDouble(availableFund));
				reserveFundDetails.setReserveFund(Double.parseDouble(reserveFund));
				status = commonService.reserveFundEstimation(reserveFundDetails);
				model.addAttribute("status",status);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				System.out.println(status);
			}
			return reserveFundEstimationForADepartment(request, response, model, sessionObject);
		}
		
		/**
		 * @author sourav.bhadra 13-04-2018
		 * this method is for reserve to unreserve fund conversion of a department */
		
		@RequestMapping(value = "/reserveToUnreserveFundConversion", method = RequestMethod.GET)
		public String reserveToUnreserveConversion(HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObj) {
			String strView = "common/reserveToUnreserveFundConversion";
			try {
				logger.info("Inside Method subDepartmentsBudgetAllocation-GET of CommonController");
				FinancialYear currentFinancialYear = commonService.getFinancialYear();
				model.addAttribute("currentFinancialYear", currentFinancialYear.getFinancialYearName());
				
				Department dept = commonService.getDepartmentForAUser(sessionObj.getUserId());
				model.addAttribute("department", dept.getDepartmentCode());
				
				Budget budgetDetails = commonService.getBudgetDetailsForADepartment(currentFinancialYear.getFinancialYearName(), dept.getDepartmentCode());
				model.addAttribute("budgetDetails", budgetDetails);
				
			} catch (Exception e){
				logger.error("Exception in method createStatusOfItem-GET of CommonController", e);
				e.printStackTrace();
			}
			return strView;
		}
		
		/**
		 * @author sourav.bhadra 13-04-2018
		 * this method is for converting reserve fund to unreserve
		 */
		@RequestMapping(value = "/submitReserveToUnreserveFund", method = RequestMethod.POST)
		public String submitReserveToUnreserveFund(HttpServletRequest request,
										HttpServletResponse response,												
										ModelMap model,
										@RequestParam("financialYear") String financialYear,
										@RequestParam("department") String department,
										@RequestParam("availableFund") String availableFund,
										@RequestParam("reserveFund") String reserveFund,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String status="";
			try{
				Budget reserveFundDetails = new Budget();
				reserveFundDetails.setUpdatedBy(sessionObject.getUserId());
				reserveFundDetails.setAcademicYear(financialYear);
				reserveFundDetails.setDepartment(department);
				reserveFundDetails.setActualIncome(Double.parseDouble(availableFund));
				reserveFundDetails.setReserveFund(Double.parseDouble(reserveFund));
				status = commonService.reserveFundEstimation(reserveFundDetails);
				model.addAttribute("status",status);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				System.out.println(status);
			}
			return reserveToUnreserveConversion(request, response, model, sessionObject);
		}
		
		/**
		 * @author sourav.bhadra 17-04-2018
		 * this method is for reserve fund allocation for sub departments */
		
		@RequestMapping(value = "/reserveFundAllocationForSubDepartments", method = RequestMethod.GET)
		public String reserveFundAllocationForSubDepartments(HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObj) {
			String strView = "common/reserveFundAllocationForSubDepartments";
			try {
				logger.info("Inside Method subDepartmentsBudgetAllocation-GET of CommonController");
				FinancialYear currentFinancialYear = commonService.getFinancialYear();
				model.addAttribute("currentFinancialYear", currentFinancialYear.getFinancialYearName());
				
				Department dept = commonService.getDepartmentForAUser(sessionObj.getUserId());
				model.addAttribute("department", dept.getDepartmentCode());
				
				Budget budgetDetails = commonService.getBudgetDetailsForADepartment(currentFinancialYear.getFinancialYearName(), dept.getDepartmentCode());
				model.addAttribute("budgetDetails", budgetDetails);
				
				List<Department> subDeptList = commonService.getAllSubDepartmentsOfADepartment(dept.getDepartmentCode());
				model.addAttribute("subDeptList", subDeptList);
				
			} catch (Exception e){
				logger.error("Exception in method createStatusOfItem-GET of CommonController", e);
				e.printStackTrace();
			}
			return strView;
		}
		
		/* added by sourav.bhadra on 17-04-2018
		 * to set a child department's budget
		 * from parent department's budget */
		@RequestMapping(value = "/updateBudgetForSubDepts", method = RequestMethod.POST)
		public String updateBudgetForSubDepts(HttpServletRequest request,
										HttpServletResponse response,												
										ModelMap model,
										@RequestParam("financialYear") String financialYear,
										@RequestParam("department") String department,
										@RequestParam("subDepartment") String subDepartment,
										@RequestParam("subDeptPercentage") double percentage,
										@RequestParam("subDeptAmount") double amount,
										//@RequestParam("reserveFund") double reserveFund,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String status1="";
			String status2="";
			try{
				Budget b=new Budget();
				b.setUpdatedBy(sessionObject.getUserId());
				b.setAcademicYear(financialYear);
				b.setBudgetDesc(department);
				b.setDepartment(subDepartment);
				b.setActualIncome(amount);
				b.setExpectedIncome(percentage);
				
				status1 = commonService.updateSubDepartmentsBudget(b);
				
				model.addAttribute("submitResponse1", status1);
				model.addAttribute("submitResponse2", status2);
			}catch(Exception e){
				e.printStackTrace();
			}
			return reserveFundAllocationForSubDepartments(request, response, model, sessionObject);
		}
		
		@RequestMapping(value = "/getCategoryListForADepartment", method = RequestMethod.GET)
		public @ResponseBody
		String getCategoryListForADepartment(@RequestParam("department") String department) {
			String category ="";
			try{
				List<JobType> departmentAndCategoryList = administratorService.getCategoryListForADepartment(department);
				List<Task>categoryList = departmentAndCategoryList.get(0).getTaskList();
				for(Task categoryObj : categoryList){
					category = category + categoryObj.getTaskCode()+"*"+categoryObj.getTaskName()+"#";
				}
				
				//System.out.println("category=="+category);
			}catch(Exception e){
				logger.error("exception in getStateList() in CommonController: ", e);
			}
			return (new Gson().toJson(category));
		}
		
		@RequestMapping(value = "/getMySchoolNote", method = RequestMethod.GET)
		public String getMySchoolNote(HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObj) {
			String strView = "common/mySchoolNote";
			try {
				logger.info("Inside Method getMySchoolNote-GET of CommonController");
				//
				List<SchoolNote> schoolNoteList = commonService.getSchoolNoteList();
				List<SchoolNote> schoolNoteListNew  = new ArrayList<>();
				
				for(SchoolNote schoolNote : schoolNoteList){
					
					SchoolNote schoolNoteObj = new SchoolNote();
					List<Standard>standardList = new ArrayList<>();
					
					JSONObject json = new JSONObject(schoolNote.getJson());
					schoolNoteObj.setNote(schoolNote.getNote());
					schoolNoteObj.setDescription(schoolNote.getDescription());
					schoolNoteObj.setSender(json.getString("sender"));
					schoolNoteObj.setAcademicSession(json.getString("academicsSession"));
					schoolNoteObj.setDateString(json.getString("dateString"));
					JSONArray standardArray = null;
					JSONArray rollArray = null;
					String general = "";
					if (json.has("standardwise")) { 
						 standardArray = (JSONArray) json.get("standardwise");
					}
					if(json.has("rollwise")){
						 rollArray = (JSONArray) json.get("rollwise");
					}
					if(json.has("general")){
						 general = json.getString("general");
					}
					
					
					if(general != ""){
						schoolNoteObj.setGeneral(general);
						schoolNoteListNew.add(schoolNoteObj);
					}
					
					if(null != standardArray && standardArray.length()!=0){
						for(int i=0;i<standardArray.length();i++){
							JSONObject jsonObject = standardArray.getJSONObject(i);
							Standard standard = new Standard();
							standard.setStandardName(jsonObject.getString("standard"));
							List<Section>sectionList = new ArrayList<>();
							JSONArray sectionArray = (JSONArray) jsonObject.get("section");
							for(int j=0;j<sectionArray.length();j++){
								Section section = new Section();
								section.setSectionName(sectionArray.getString(j));
								sectionList.add(section);
							}
							standard.setSectionList(sectionList);
							standardList.add(standard);
						}
						schoolNoteObj.setStandardList(standardList);
						schoolNoteListNew.add(schoolNoteObj);
					}
					
					if(null != rollArray && rollArray.length() !=0){
						List<String>rollList = new ArrayList<>();
						for(int k=0;k<rollArray.length();k++){
							rollList.add(rollArray.getString(k));
						}
						schoolNoteObj.setRollList(rollList);
						schoolNoteListNew.add(schoolNoteObj);
					}
					
					
					
					
					
				}
				
				model.addAttribute("schoolNoteList",schoolNoteListNew);
				System.out.println("size=="+schoolNoteListNew.size());
			} catch (Exception e){
				logger.error("Exception in method getMySchoolNote-GET of CommonController", e);
				e.printStackTrace();
			}
			return strView;
		}
		
		@RequestMapping(value = "/getSectionAndStandardList", method = RequestMethod.GET)
		public @ResponseBody
		String getSectionAndStandardList() {
			String standardSection ="";
			try{
				List<Standard> standardList = academicsService.getStandardsWithSection();
				for(Standard standard : standardList){
					standardSection = standardSection + standard.getStandardCode()+"*"+standard.getStandardName()+"##";
					List<Section>sectionList = standard.getSectionList();
					for(Section section : sectionList){
						if(!(section.getSectionCode().equalsIgnoreCase("NA"))){
							standardSection = standardSection + section.getSectionCode()+"@"+section.getSectionName()+"*#*";
						}
						
					}
					standardSection = standardSection + "@#@";
				}
				
				//System.out.println("standardSection=="+standardSection);
			}catch(Exception e){
				logger.error("exception in getSectionAndStandardList() in CommonController: ", e);
			}
			return (new Gson().toJson(standardSection));
		}
		
		@RequestMapping(value = "/mySchoolNote", method = RequestMethod.POST)
		public String mySchoolNote(HttpServletRequest request,
										HttpServletResponse response,												
										ModelMap model,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
			
			try{
				AcademicYear academicYear = commonService.getCurrentAcademicYear();
				String note = request.getParameter("note");
				String description = request.getParameter("description");
				String recipients = request.getParameter("recipients");
				String userId = sessionObject.getUserId();
				SchoolNote schoolNote = new SchoolNote();
				schoolNote.setUpdatedBy(sessionObject.getUserId());
				schoolNote.setDescription(description);
				schoolNote.setNote(note);
				
				String[] rollNumbers = null;
				List<String> phoneNumbersForAll = null;
				Resource resource = new Resource();
				resource.setUserId(userId);
				Employee employee = erpService.getStaffSalaryDetails(resource);
				
				 Date date = Calendar.getInstance().getTime();

				    // Display a date in day, month, year format
				 DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				 String today = formatter.format(date);
				 long epochTime = date.getTime() / 1000L;
				    
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("username",portalUserName);
				jsonObj.put("password",portalPassWord);
				jsonObj.put("academicsSession", academicYear.getAcademicYearName());
				jsonObj.put("note", note);
				jsonObj.put("descr", description);
				String designation = employee.getDesignation()==null?"":employee.getDesignation().getDesignationName();
				jsonObj.put("sender",employee.getResource().getName()+"("+designation+")");
				jsonObj.put("dateString", today);
				jsonObj.put("logTime", epochTime);
				
				JSONArray standardDetails = new JSONArray();
				if(recipients.equalsIgnoreCase("standard")){
					String[] standards = request.getParameterValues("standard");
					if(null != standards){
						for(int i=0;i<standards.length;i++){
							System.out.println(standards[i]);
							JSONObject jsonObject= new JSONObject();
							jsonObject.put("standard", standards[i]);
							JSONArray sectionDetails = new JSONArray();
							String[]sections = request.getParameterValues("section"+standards[i]);
							for(int j=0; j<sections.length;j++){
								sectionDetails.put(sections[j]);
							}
							jsonObject.put("section", sectionDetails);
							
							standardDetails.put(jsonObject);
						}
						jsonObj.put("standardwise", standardDetails);
						schoolNote.setJson(jsonObj.toString());
					}
					
				}else if(recipients.equalsIgnoreCase("roll")){
					String rollNumber = request.getParameter("rollNumber");
					rollNumbers = rollNumber.split(",");
					JSONArray rollArray = new JSONArray();
					if(null != rollNumber){
						for(int k=0;k<rollNumbers.length;k++){
							rollArray.put(rollNumbers[k]);
						}
						jsonObj.put("rollwise", rollArray);
						schoolNote.setJson(jsonObj.toString());
						
					}
				}else if(recipients.equalsIgnoreCase("all")){
					jsonObj.put("general", "allstudents");
					schoolNote.setJson(jsonObj.toString());
					
					/*Get All Phone Numbers for all the students for SMS Integration*/
					//Get All Students
					List<Student> studentList = backOfficeService.getStudentList();
					phoneNumbersForAll = new ArrayList<String>();
					int studentListSize = studentList.size();
					
					for(int i=0; i<studentListSize;i++){
						Student student = studentList.get(i);
						if(student.getMobileNo() != null && !(student.getMobileNo().isEmpty()) && !(student.getMobileNo().equalsIgnoreCase("null"))){
							phoneNumbersForAll.add(student.getMobileNo());
						}
					}
					System.out.println("Student with Phone Numbers: "+phoneNumbersForAll.size());
				}
				System.out.println(jsonObj.toString());
				
				//Before the call to API, the Data must be stored locally
				String status = commonService.insertIntoSchoolNote(schoolNote);
				
				final String uri = URIForSendSchoolNote;
				System.out.println("URI:::"+uri);
				HttpURLConnection connection = null;
				OutputStreamWriter out = null;
				InputStreamReader in = null;
				BufferedReader br = null;
				String json_response = "";
				WebIQTransaction webIQTran = null;
				
				if(status.equalsIgnoreCase("success") && isWebIQAvailable.equalsIgnoreCase("true"))
				{
					URL url = new URL(uri);
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
					}catch(Exception e){
						/** Could be connection Issue **/
						e.printStackTrace();

						//Could be Connection Failure then also insert into the webiq_transaction_details table
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForSendSchoolNote);
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(false);
						//Call to the BackOffice Service
						backOfficeService.addWebIQTransaction(webIQTran);
					}
				}
				System.out.println("JSON response:::"+ json_response);

				if((!json_response.isEmpty())){
					JSONObject object = new JSONObject(json_response);
					int statusFromJsonResponse = (int)object.get("status");
					if(statusFromJsonResponse==200){
						//If call to the API is successful, then insert into the webiq_transaction_details table 
						webIQTran = new WebIQTransaction();
						webIQTran.setUri(URIForSendSchoolNote);
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(true);
						
					}else{
						//If Failure then also insert into the webiq_transaction_details table
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForSendSchoolNote);
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(false);
					}
					
					//Call to the BackOffice Service
					backOfficeService.addWebIQTransaction(webIQTran);
				}
				
				/*Call to the SMS Server*/
				if(status.equalsIgnoreCase("success") && isSMSEnabled.equalsIgnoreCase("true")){
					//Get all the phone numbers based on the roll numbers
					if(rollNumbers != null && rollNumbers.length>0){
						List<String> phoneNumbersAgainstRoll = commonService.getMobileNumberAgainstRollNumbers(rollNumbers);
						// Modified by Saikat 16/6/2018
						if(phoneNumbersAgainstRoll !=null && phoneNumbersAgainstRoll.size()>0){
							for(int counter = 0; counter < phoneNumbersAgainstRoll.size(); counter++){
								Map<String, String> dataMap = utility.sendSMSForSchoolNote(phoneNumbersAgainstRoll.get(counter), schoolNote, smsURL, smsAuthkey);
								System.out.println("SMS Status for School Note "+ dataMap.get("status"));
								
								// Added by SAIKAT 16/6/2018
								if(dataMap.get("status").equalsIgnoreCase("true")){
									
									SmsAudit smsAudit = new SmsAudit();
									smsAudit.setMobileNumber(phoneNumbersAgainstRoll.get(counter));
									smsAudit.setMessage(dataMap.get("message"));
									smsAudit.setActionFor("School Note");
									smsAudit.setStatus(new Boolean(dataMap.get("status")));
									smsAudit.setUpdatedBy(sessionObject.getUserId());
									
									String insertStatus = commonService.saveSMSDetailsForAudit(smsAudit);
									
									logger.info("Insert in SmsAuditLog table for School Note to recipient number " + phoneNumbersAgainstRoll.get(counter) + " : " + insertStatus);
								}
							}
							
						}
					}
					if(phoneNumbersForAll != null && phoneNumbersForAll.size() >0){
						//boolean smsStatus = utility.sendSMSForSchoolNote(phoneNumbersForAll, schoolNote, smsURL, smsAuthkey);
						//System.out.println("SMS Status for School Note "+smsStatus);
					}
				}
				
				model.addAttribute("status", status);
				String msg="";
				if(status.equalsIgnoreCase("success")){
					msg = "School note Created Succesfully";
				}else{
					msg = "Failed to Create School note";
				}
				
				model.addAttribute("msg", msg);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return getMySchoolNote(request, response, model, sessionObject);
		}
		
	public List<StudentAttendance> getStudentAttandanceList(Ticket ticket){
			
			List<StudentAttendance> studentAttendanceList = new ArrayList<StudentAttendance>();
			try{
				
				if((null != ticket.getFromDate()) || (ticket.getFromDate() != "")){
					
					String fromDate = ticket.getFromDate();
					String toDate = ticket.getToDate();
					String[] fromDateArr = fromDate.split("/");
					String[] toDateArr = toDate.split("/");
					List<String> datesInRange = new ArrayList<>();
					Calendar startDate = new GregorianCalendar(Integer.parseInt(fromDateArr[2]), Integer.parseInt(fromDateArr[1])-1, Integer.parseInt(fromDateArr[0]));
					Calendar endDate = new GregorianCalendar(Integer.parseInt(toDateArr[2]),Integer.parseInt(toDateArr[1])-1, Integer.parseInt(toDateArr[0])+1);
					while (startDate.before(endDate)) {
						StudentAttendance studentLeaveObj = new StudentAttendance();
						studentLeaveObj.setUpdatedBy(ticket.getUpdatedBy());
						studentLeaveObj.setStudentRollNo(ticket.getRollNumber());
						studentLeaveObj.setStudentId(ticket.getStandard());
						int dayOfWeek = startDate.get(Calendar.DAY_OF_WEEK);
						System.out.println(dayOfWeek);
						if(dayOfWeek != Calendar.SUNDAY){
							Date result = startDate.getTime();
							 SimpleDateFormat dmyFormat = new SimpleDateFormat("dd/MM/yyyy");
							 String leaveDate = dmyFormat.format(result);
							 String[] leaveDateArr = leaveDate.split("/");
							 System.out.println(leaveDate);
							 studentLeaveObj.setAbsentDay(leaveDate);
							 studentLeaveObj.setMonth(leaveDateArr[1]);
							 studentLeaveObj.setYear(leaveDateArr[2]);
							datesInRange.add(leaveDate);
							studentAttendanceList.add(studentLeaveObj);
							
						}
						startDate.add(startDate.DATE, 1);
				       
				    }
				}
				
			}catch(Exception e){
				
			}
			return studentAttendanceList;
		}
}