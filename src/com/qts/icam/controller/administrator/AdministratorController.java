package com.qts.icam.controller.administrator;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.administrator.AccessType;
import com.qts.icam.model.administrator.Approver;
import com.qts.icam.model.administrator.EmailEventAndTemplate;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.administrator.ProfileMatrix;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.administrator.ServerConfiguration;
import com.qts.icam.model.administrator.StaffForXml;
import com.qts.icam.model.administrator.StudentForXml;
import com.qts.icam.model.administrator.UserGroup;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.NotificationMedium;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.TaskDetails;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.DesignationLevel;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.login.LoginForm;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketComment;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.backoffice.BackOfficeService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.erp.ERPService;
import com.qts.icam.service.ticket.TicketService;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

@Controller
@SessionAttributes("sessionObject")
public class AdministratorController {
	public static Logger logger = Logger.getLogger(AdministratorController.class);
	
	String departmentId="SYSTEM ADMINISTRATION DEPARTMENT";
	
	@Value("${root.path}")
	private String rootPath;
	
	@Value("${excelUpload.folder}")
	private String excelUploadFolder;
	
	@Value("${excelDownload.folder}")
	private String excelDownloadFolder;
	
	@Value("${functionalityRoleMapping.excel}")
	private String functionalityRoleMappingExcel;
	
	@Value("${course.syllabus.path}")
	private String courseSyllabusPath;
	
	/**Added by Saif
	 * Date-24/08/2017*/
	
	@Value("${URI.sendProgrammeDetails}")
	private String URIForSendingProgrammeDetails;
	
	@Value("${URI.sendLocationDetails}")
	private String URIForSendLocationDetails;
	
	@Value("${URI.NewCandidates}")
	private String URIForNewCandidates;
	
	@Value("${URI.ScrutinizedCandidates}")
	private String URIForScrutinizedCandidates;
	
	@Value("${URI.SelectedCandidates}")
	private String URIForSelectedCandidates;
	
	@Value("${URI.AdmittedCandidates}")
	private String URIForAdmittedCandidates;
	
	@Value("${URI.AlumniData}")
	private String URIForSendingAlumniDetails;
	
	@Value("${URI.DisplayNotice}")
	private String URIForPublishNotice;
	
	
	/**Added by Ranita.Sur
	 * Date-25/09/2017*/
	@Value("${message.query}")
	private String messageQueryPath;
	
	@Autowired
	private AdministratorService administratorService = null;
	
		
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private CommonService commonService;

	@Autowired
	private AcademicsService academicsService;
	
	@Autowired
	private ERPService erpService;
	
	@Autowired
	private BackOfficeService backOfficeService;
	
	
	//Added By Naimisha 18102017
	
	@Autowired
	private TicketService ticketService;
	/*
	 * Link To Open Process Flow On Navigation Page
	 * */
	
	@RequestMapping(value = "/administratorProcessFlow", method = RequestMethod.GET)
	public String administratorProcessFlow() {
		return "administrator/administratorProcessFlow";
	}
	
	
	
	/*
	 * 
	 *   Role Starts
	 * 
	 * 
	 * */
	
	
	/*
	 * Link To Open Create New Role
	 * */
	
	@RequestMapping(value = "/createRoles", method = RequestMethod.GET)
	public ModelAndView createRoles(HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("createRoles() In AdministratorController.java: ");		
		try{
			List<Module> moduleList=administratorService.getmoduleList();
			String updatedBy = sessionObject.getUserId();
			if(null!=moduleList&& moduleList.size()!=0){
				model.addAttribute("moduleList",moduleList);				
			}else{
				logger.info("createRoles() In AdministratorController.java: No moduleList Found in db");
			}
			
			String moduleCode = null;
			model.addAttribute("moduleCode", moduleCode);
			model.addAttribute("updatedBy", updatedBy);
		}catch(Exception e){
			logger.error("Exception in createRoles() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/createRoles");
	}
	
	/**
	 * This Ajax call retrieve Present Roles from DB.	 
	 */
	
	@RequestMapping(value = "/getRolesForModule", method = RequestMethod.GET)
	public @ResponseBody
	String getRolesForModule(@RequestParam("Module") String moduleCode) {
		String listOfRole="";
		try{
			logger.info("getExistingRolesFromDB() In AdministratorController.java");
			List <Role> roleList = administratorService.getRolesForModule(moduleCode);			
			if(null!=roleList && roleList.size()!=0){
				for(Role  role: roleList){						
					listOfRole=listOfRole+role.getRoleName()+"*"+role.getRoleCode()+"*"+role.getRoleDescription()+"*"+role.getRoleTypeName()+"#";
				}
			}else{
				listOfRole = "NULL";
			}
		}catch(Exception e){			
			logger.error("getRolesForModule() In AdministratorController.java: Exception", e);	
		}
		return (new Gson().toJson(listOfRole));
	}
	
	
	/**
	 * Insert Roles
	 * naimisha 25052017
	 */
	@RequestMapping(value = "/addRoles", method = RequestMethod.POST)
	public ModelAndView addRoles(HttpServletRequest request, HttpServletResponse response,
			ModelMap model,Role role,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("insertRoles() In AdministratorController.java: ");
		try{
			role.setRoleName((role.getRoleName().toUpperCase().trim()));
			role.setUpdatedBy(sessionObject.getUserId());
			String insertStatus=administratorService.addRoles(role);
			List<Module> moduleList=administratorService.getmoduleList();
			String updatedBy = sessionObject.getUserId();
			
			if(null!=moduleList&& moduleList.size()!=0){
				model.addAttribute("moduleList",moduleList);				
			}
			String moduleCode = role.getModuleCode();
			
			model.addAttribute("moduleCode", moduleCode);
			model.addAttribute("updatedBy", updatedBy);
			//String msg = null;
			if(insertStatus.equals("SUCCESS")){				
				model.addAttribute("message", "SUCCESS");
				model.addAttribute("msg", "Role Created SuccessFully");
				
			}else{
				model.addAttribute("message", "Failed To Create New Role");
				logger.info("insertRoles() In AdministratorController.java: Error In Creating Roles");
			}
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String moduleName = request.getParameter("moduleCode");
			String roleName = request.getParameter("roleName");
			String roleDescription = request.getParameter("roleDescription");
			
			if(insertStatus.equalsIgnoreCase("SUCCESS")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("CREATE NEW ROLES");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("SYSTEM ADMINISTRATION");
				updateLog.setUpdatedFor("Module Abbreviation :: " + moduleName);
				updateLog.setDescription("Module Abbreviation :: " + moduleName + " with Role Name :: " + roleName + " with role description :: " + roleDescription + " is added.");
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 288 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		}catch(Exception e){
			logger.error("Exception in insertRoles() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/createRoles");
	}
	
	
	/**
	 * Delete Role
	 * naimisha 25052017
	 */
	
	@RequestMapping(value = "/inActiveRole", method = RequestMethod.GET)
	public ModelAndView inActiveRole(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("roleNameChk") String[] roleNameChk,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		try {
			List<Role> roleList = new ArrayList<Role>();
			if (roleNameChk != null && roleNameChk.length != 0) {
				for (int count = 0; count < roleNameChk.length; count++) {
					Role role = new Role();
					role.setRoleCode(roleNameChk[count]);
					role.setUpdatedBy(sessionObject.getUserId());
					roleList.add(role);
				}
			}
			String status= administratorService.inActiveRole(roleList);
			if(status.equals("SUCCESS")){				
				model.addAttribute("message", "SUCCESS");
				model.addAttribute("msg", "Role Deleted SuccessFully");
			}else{
				model.addAttribute("message", "FAILED");
				model.addAttribute("msg", "Failed To Delete Role");
				logger.info("inActiveRole() In AdministratorController.java: Unable to delete");
			}
		} catch (Exception e) {
			logger.error("inActiveRole() In AdministratorController.java: Exception",e);
		}
		return createRoles( request,  response,  model, sessionObject);
		
	}
	
	/**
	 *  Role Contact Mapping
	 */
	
	@RequestMapping(value = "/roleContactMapping", method = RequestMethod.GET)
	public ModelAndView roleContactMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		Resource resource = null;
		try {
			resource = administratorService.getResourceAndRolesFromDB();			
			if (null!=resource) {
				model.addAttribute("resource", resource);
			}
		} catch (Exception e) {
			logger.error("Exception in roleContactMapping() method in AdministratorController: ",e);
		}
		return new ModelAndView("administrator/roleContactMapping");
	}
	
	
	
		
	
	/**
	 * Add Role Contact Mapping
	 */
	@RequestMapping(value = "/addRoleContactMapping", method = RequestMethod.POST)
	public ModelAndView addRoleContactMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("userId") String[] userId,
			@RequestParam("name") String[] name,
			@RequestParam("roleName") String roleCode,
			@RequestParam("resourceTypeName") String resourceTypeCode,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		List<Resource> resourceList = new ArrayList<Resource>();
		ModelAndView view=null;
		try {
			for (int count = 0; count < userId.length;count++) {
				if (userId[count] != null && name[count] != null) {
					Resource resource = new Resource();
					resource.setName(name[count]);
					resource.setUserId(userId[count]);
					resource.setRoleName(roleCode);// Taking Code instead of name
					resource.setResourceTypeName(resourceTypeCode);// Taking Code instead of name
					resource.setUpdatedBy(sessionObject.getUserId());
					resourceList.add(resource);
				}
			}
			String insertStatus= administratorService.addRoleContactMapping(resourceList);
			if (null!=insertStatus) {
				if(insertStatus.equals("SUCCESS")){
					model.addAttribute("successMessage", "Role Assigned To Contact Successfully ");
					//view=listRoleContactMapping( request,response,model);
					view=roleContactMapping( request,response,model,sessionObject);
				}else{
					model.addAttribute("errorMessage", "Failed To Assign The Role To The Contact");
					view=roleContactMapping( request,response,model,sessionObject);					
				}				
			}
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String roleName = "";
			String description = "";
			if(roleCode != "" || roleCode != null)
			{
				if(roleCode.equalsIgnoreCase("ROLECODE-1")){
					roleName = "SYSTEM ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-2")){
					roleName = "ACADEMICS ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-3")){
					roleName = "ADMISSION ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-4")){
					roleName = "ERP ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-5")){
					roleName = "HOSTEL ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-6")){
					roleName = "INVENTORY ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-7")){
					roleName = "LIBRARY ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-8")){
					roleName = "OFFICE ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-9")){
					roleName = "REPORT ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-10")){
					roleName = "TICKET ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-11")){
					roleName = "FINANCE ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-12")){
					roleName = "VENUE ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-13")){
					roleName = "GRADING SYSTEM ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-14")){
					roleName = "FACILITY MANAGEMENT ADMINISTRATOR";
				}
				if(roleCode.equalsIgnoreCase("ROLECODE-15")){
					roleName = "MESS ADMINISTRATOR";
				}
			}
			if(insertStatus.equalsIgnoreCase("SUCCESS")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("ADD ROLE CONTACT MAPPING");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("SYSTEM ADMINISTRATION");
				updateLog.setUpdatedFor("Role Name :: " + roleName + " Resource Type :: " + resourceTypeCode);
				for (int count = 0; count < userId.length;count++) {
					if (userId[count] != null && name[count] != null) {
						description = description + ("Role Name :: " + roleName + " Resource Type :: " + resourceTypeCode + " mapped with user id :: " + userId[count]
								+ " of name :: " + name[count]);
					}
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 468 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception e) {
			logger.error(
					"Exception in insertRoleContactMapping() method in AdministratorController: ",
					e);
		}
		return view;
	}


	/**
	 * List All Role Contact Mapping
	 */
	@RequestMapping(value = "/listRoleContactMapping", method = RequestMethod.GET)
	public ModelAndView listRoleContactMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Role> roleListFromDB = new ArrayList<Role>();
		try {
			roleListFromDB = administratorService.getlistRoleContactMapping();
			if (null!=roleListFromDB && roleListFromDB.size() != 0) {
				/*logger.info("In AdministratorController listRoleContactMapping() method: calling getlistRoleContactMappingPaging(HttpServletRequest request) in AdministratorService.java");
				PagedListHolder<Role> pagedListHolder = administratorService.getlistRoleContactMappingPaging(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());*/
				model.addAttribute("roleListFromDB", roleListFromDB);
			}
		} catch (Exception e) {			
			logger.error("Exception in listRoleContactMapping() method in AdministratorController: ",e);
		}
		return new ModelAndView("administrator/listRoleContactMapping");
	}

	
	@RequestMapping(value = "/listRoleContactMappingPagination", method = RequestMethod.GET)
	public ModelAndView listRoleContactMappingPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In bookListPagination() method of AdministratorController");
			mav = new ModelAndView("administrator/listRoleContactMapping");
			logger.info("In AdministratorController listRoleContactMappingPagination() method: calling getlistRoleContactMappingPaging(HttpServletRequest request) in AdministratorService.java");
			PagedListHolder<Role> pagedListHolder = administratorService.getlistRoleContactMappingPaging(request);
			if (pagedListHolder != null) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in AdministratorController listRoleContactMappingPagination() method for Exception: ", e);
		}
		return mav;
	}
	
	
	
	
	/**
	 * Serach Role Contact mapping 
	 */
	
	@RequestMapping(value = "/searchRoleContactmapping", method = RequestMethod.POST)
	public ModelAndView editRoleContactMappingSearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "data", required = false) String data
			) {
		List<Role>roleListFromDB = new ArrayList<Role>();
		try {
			logger.info("searchRoleContactmapping() merhod ");
			if(null!=data){
				data=data.trim();
			}
			Map<String, Object> parameters = new HashMap<String, Object>();
			if (query.equalsIgnoreCase("contactName")) {
				parameters.put("contactName", data);
			}
			if (query.equalsIgnoreCase("roleName")) {
				parameters.put("roleName", data);
			}
			if (query.equalsIgnoreCase("userId")) {
				parameters.put("userId", data);
			}
			roleListFromDB =  administratorService.searchRoleContactmapping(parameters);
			model.addAttribute("roleListFromDB", roleListFromDB);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in editRoleContactMappingSearch() merhod ",e);
		}
		return new ModelAndView("administrator/listRoleContactMapping");
	}
	
	
	
	
	/**
	 * Edit Role Contact Mapping
	 */
	
	@RequestMapping(value = "/getRoleContactMapping", method = RequestMethod.POST)
	public ModelAndView getRoleContactMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Role role) {
		try {
			//System.out.println("Within");
			List<Resource> resourceList = administratorService.getRoleContactMapping(role);
			if (null!=resourceList && resourceList.size() != 0) {
				model.addAttribute("resourceList", resourceList);
				model.addAttribute("roleName", resourceList.get(0).getRoleName());
				model.addAttribute("code", resourceList.get(0).getCode());
				model.addAttribute("desc", resourceList.get(0).getDesc());
				model.addAttribute("resourceTypeName", resourceList.get(0)
						.getResourceTypeName());
			}
		} catch (Exception e) {
			logger.error(
					"Exception in getRoleContactMapping() method in AdministratorController: ",
					e);
		}
		return new ModelAndView("administrator/editRoleContactMapping");
	}
	
	/*
	 *  Update Role Contact mapping
	 * 
	 * */
	
	
	@RequestMapping(value = "/editRoleContactMapping", method = RequestMethod.POST)
	public ModelAndView editRoleContactMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("userID") String[] userID,
			@RequestParam("name") String[] name,
			@RequestParam("roleName") String roleName,
			@RequestParam("resourceTypeName") String resourceTypeName,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		List<Resource> resourceList = new ArrayList<Resource>();		
		try {
			for (int count = 0; count < userID.length; count++) {
				if (userID[count] != null && name[count] != null) {
					Resource resource = new Resource();
					resource.setName(name[count]);
					resource.setUserId(userID[count]);
					resource.setRoleName(roleName);
					resource.setResourceTypeName(resourceTypeName);
					resource.setUpdatedBy(sessionObject.getUserId());
					resourceList.add(resource);
				}
			}	
			String insertStatus=administratorService.editRoleContactMapping(resourceList);
			if (null != insertStatus) {
				if (insertStatus.equals("SUCCESS")) {
					model.addAttribute("successMessage",
							"Role Contact Mapping Updated Successfully ");
				} else {
					model.addAttribute("errorMessage",
							"Failed To Update Role Contact Mapping");
				
			}}
		}
		catch (Exception e) {
			logger.error("Exception in editRoleContactMapping() method in AdministratorController: ",	e);
		}
		return listRoleContactMapping(request,response,model);
	}
	/*
	 * 
	 *   Role Ends
	 * 
	 * 
	 * */
	
	
	/*
	 * 
	 *   Configuration Part Starts Here
	 * 
	 * 
	 * */
	
	
	/**
	 * Read LDAP Configuration , if exists
	 */
	@RequestMapping(value = "/directoryServerConfiguration", method = RequestMethod.GET)
	public ModelAndView serverConfigurationLDAP(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {		
		try {
			ServerConfiguration serverConfiguration=administratorService.getServerConfigurationLDAP();
			model.addAttribute("serverConfiguration", serverConfiguration);
		} catch (Exception e) {			
			logger.error("serverConfigurationLDAP() In AdministratorController.java: Exception", e);
		}		
		return new ModelAndView("administrator/serverConfigurationLDAP");
	}
	
	/**
	 * Save LDAP Directory Server Configuration 
	 */

	@RequestMapping(value = "/insertServerConfigurationLDAP", method = RequestMethod.POST)
	public ModelAndView insertServerConfigurationLDAP(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			ServerConfiguration serverConfiguration,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {		
		try {
			serverConfiguration.setUpdatedBy(sessionObject.getUserId());
			String status=administratorService.insertServerConfigurationLDAP(serverConfiguration);
			if(status.equalsIgnoreCase("successMessage")){
				model.addAttribute("successMessage","Configuration Saved Successfully ");
			}else{
				model.addAttribute("errorMessage","Failed To Save Configuration");	
			}
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String oldDirectoryServerType = request.getParameter("oldDirectoryServerType");
			String oldDirectoryServerURL = request.getParameter("oldDirectoryServerURL");
			String oldDirectoryServerSecurityAuthenticationType = request.getParameter("oldDirectoryServerSecurityAuthenticationType");
			String oldDirectoryServerPort = request.getParameter("oldDirectoryServerPort");
			String oldDirectoryServerUserDN = request.getParameter("oldDirectoryServerUserDN");
			String oldDirectoryServerBaseDN = request.getParameter("oldDirectoryServerBaseDN");
			String oldDirectoryServerPassword = request.getParameter("oldDirectoryServerPassword");
			String oldDirectoryServerFilter = request.getParameter("oldDirectoryServerFilter");
			
			String directoryServerType = request.getParameter("directoryServerType");
			String directoryServerUrl = request.getParameter("directoryServerUrl");
			String directoryServerSecurityAuthenticationType = request.getParameter("directoryServerSecurityAuthenticationType");
			String directoryServerPort = request.getParameter("directoryServerPort");
			String directoryServerUserDN = request.getParameter("directoryServerUserDN");
			String directoryServerBaseDN = request.getParameter("directoryServerBaseDN");
			String directoryServerPassword = request.getParameter("directoryServerPassword");
			String directoryServerFilter = request.getParameter("directoryServerFilter");
			String description ="" ;
			
			if(status.equalsIgnoreCase("successMessage")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT DIRECTORY SERVER CONFIGURATION");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("SYSTEM ADMINISTRATION");
				updateLog.setUpdatedFor("Directory Server Type" + directoryServerType);
				if(!(oldDirectoryServerType.equalsIgnoreCase(directoryServerType))){
					description = description + ("Directory Server Type :: " + oldDirectoryServerType + " updated to new Directory Server Type :: " + directoryServerType + " ; ");
				}
				if(!(oldDirectoryServerURL.equalsIgnoreCase(directoryServerUrl))){
					description = description + ("Directory Server URL :: " + oldDirectoryServerURL + " updated to new Directory Server URL :: " + directoryServerUrl+ " ; ");
				}
				if(!(oldDirectoryServerSecurityAuthenticationType.equalsIgnoreCase(directoryServerSecurityAuthenticationType))){
					description = description + ("Directory Server Security Authentication Type :: " + oldDirectoryServerSecurityAuthenticationType + " updated to new Directory Server Security Authentication Type :: " + directoryServerSecurityAuthenticationType+ " ; ");
				}
				if(!(oldDirectoryServerPort.equalsIgnoreCase(directoryServerPort))){
					description = description + ("Directory Server Port :: " + oldDirectoryServerPort + " updated to new Directory Server Port :: " + directoryServerPort+ " ; ");
				}
				if(!(oldDirectoryServerUserDN.equalsIgnoreCase(directoryServerUserDN))){
					description = description + ("Directory Server User DN :: " + oldDirectoryServerUserDN + " updated to new Directory Server User DN :: " + directoryServerUserDN+ " ; ");
				}
				if(!(oldDirectoryServerBaseDN.equalsIgnoreCase(directoryServerBaseDN))){
					description = description + ("Directory Server Base DN :: " + oldDirectoryServerBaseDN + " updated to new Directory Server Base DN :: " + directoryServerBaseDN)+ " ; ";
				}
				if(!(oldDirectoryServerPassword.equalsIgnoreCase(directoryServerPassword))){
					description = description + ("Directory Server Password :: " + oldDirectoryServerPassword + " updated to new Directory Server Password :: " + directoryServerPassword+ " ; ");
				}
				if(!(oldDirectoryServerFilter.equalsIgnoreCase(directoryServerType))){
					description = description + ("Directory Server Filter :: " + oldDirectoryServerFilter + " updated to new Directory Server Filter :: " + directoryServerFilter+ " ; ");
				}
				
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 744 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception e) {			
			model.addAttribute("errorMessage","Failed To Save Configuration");	
			logger.error("insertServerConfigurationLDAP() In AdministratorController.java: NullPointerException", e);
		}
		return new ModelAndView("administrator/serverConfigurationLDAP");
	}

	/**
	 * Read DB Configuration File , if exists
	 */
	
	@RequestMapping(value = "/serverConfigurationDB", method = RequestMethod.GET)
	public ModelAndView serverConfigurationDB(HttpServletRequest request,
		HttpServletResponse response, ModelMap model) {		
		try {
			ServerConfiguration serverConfiguration = administratorService.getServerConfigurationDB();
			model.addAttribute("serverConfiguration", serverConfiguration);
		} catch (Exception e) {			
			logger.error("serverConfigurationDB() In AdministratorController.java: Exception",e);
		}
		return new ModelAndView("administrator/serverConfigurationDB");
	}

	/**
	 * Save DB Configuration  
	 */
	
	@RequestMapping(value = "/insertServerConfigurationDB", method = RequestMethod.POST)
	public ModelAndView insertServerConfigurationDB(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			ServerConfiguration serverConfiguration,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			serverConfiguration.setUpdatedBy(sessionObject.getUserId());
			String status= administratorService.insertServerConfigurationDB(serverConfiguration);
			if(status.equalsIgnoreCase("successMessage")){
				model.addAttribute("successMessage","Configuration Saved Successfully ");
			}else{
				model.addAttribute("errorMessage","Failed To Save Configuration");	
			}
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String oldfjdbcURL = request.getParameter("oldfjdbcURL");
			String oldjdbcPort = request.getParameter("oldjdbcPort");
			String oldjdbcUserName = request.getParameter("oldjdbcUserName");
			String oldjdbcPassword = request.getParameter("oldjdbcPassword");
			String oldjdbcDatabaseName = request.getParameter("oldjdbcDatabaseName");
			String oldjdbcMaxStatement = request.getParameter("oldjdbcMaxStatement");
			String oldjdbcStatementCacheNumDeferredCloseThread = request.getParameter("oldjdbcStatementCacheNumDeferredCloseThread");
			String oldjdbcMaxIdleTime = request.getParameter("oldjdbcMaxIdleTime");
			String oldjdbcDriverClassName = request.getParameter("oldjdbcDriverClassName");
			String oldjdbcMaxActive = request.getParameter("oldjdbcMaxActive");
			String oldjdbcDialect = request.getParameter("oldjdbcDialect");
			String oldjdbcInitialSize = request.getParameter("oldjdbcInitialSize");
			String oldjdbcAcquireIncrement = request.getParameter("oldjdbcAcquireIncrement");
			
			String jdbcURL = request.getParameter("jdbcURL");
			String jdbcPort = request.getParameter("jdbcPort");
			String jdbcUserName = request.getParameter("jdbcUserName");
			String jdbcPassword = request.getParameter("jdbcPassword");
			String jdbcDatabaseName = request.getParameter("jdbcDatabaseName");
			String jdbcMaxStatement = request.getParameter("jdbcMaxStatement");
			String jdbcStatementCacheNumDeferredCloseThread = request.getParameter("jdbcStatementCacheNumDeferredCloseThread");
			String jdbcMaxIdleTime = request.getParameter("jdbcMaxIdleTime");
			String jdbcDriverClassName = request.getParameter("jdbcDriverClassName");
			String jdbcMaxActive = request.getParameter("jdbcMaxActive");
			String jdbcDialect = request.getParameter("jdbcDialect");
			String jdbcInitialSize = request.getParameter("jdbcInitialSize");
			String jdbcAcquireIncrement = request.getParameter("jdbcAcquireIncrement");
			String description ="" ;
			
			if(status.equalsIgnoreCase("successMessage")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT DB SERVER CONFIGURATION DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("SYSTEM ADMINISTRATION");
				updateLog.setUpdatedFor(jdbcDatabaseName);
				if(!(oldfjdbcURL.equalsIgnoreCase(jdbcURL))){
					description = description + ("Database Server URL :: " + oldfjdbcURL + " updated to new Database Server URL :: " + jdbcURL + " ; ");
				}
				if(!(oldjdbcPort.equalsIgnoreCase(jdbcPort))){
					description = description + ("Database Server Port :: " + oldjdbcPort + " updated to new Database Server Port :: " + jdbcPort+ " ; ");
				}
				if(!(oldjdbcUserName.equalsIgnoreCase(jdbcUserName))){
					description = description + ("Database User Name :: " + oldjdbcUserName + " updated to new Database User Name :: " + jdbcUserName+ " ; ");
				}
				if(!(oldjdbcPassword.equalsIgnoreCase(jdbcPassword))){
					description = description + ("Database Password :: " + oldjdbcPassword + " updated to new Database Password :: " + jdbcPassword+ " ; ");
				}
				if(!(oldjdbcDatabaseName.equalsIgnoreCase(jdbcDatabaseName))){
					description = description + ("Database Name :: " + oldjdbcDatabaseName + " updated to new Database Name :: " + jdbcDatabaseName+ " ; ");
				}
				if(!(oldjdbcMaxStatement.equalsIgnoreCase(jdbcMaxStatement))){
					description = description + ("Database Maximum Statement :: " + oldjdbcMaxStatement + " updated to new Database Maximum statement :: " + jdbcMaxStatement)+ " ; ";
				}
				if(!(oldjdbcStatementCacheNumDeferredCloseThread.equalsIgnoreCase(jdbcStatementCacheNumDeferredCloseThread))){
					description = description + ("Database Statement Cache Num Deferred Close Thread :: " + oldjdbcStatementCacheNumDeferredCloseThread + " updated to new Database Statement Cache Num Deferred Close Thread :: " + jdbcStatementCacheNumDeferredCloseThread+ " ; ");
				}
				if(!(oldjdbcMaxIdleTime.equalsIgnoreCase(jdbcMaxIdleTime))){
					description = description + ("Database Max Idle Time :: " + oldjdbcMaxIdleTime + " updated to new Database Max Idle Time :: " + jdbcMaxIdleTime+ " ; ");
				}
				if(!(oldjdbcDriverClassName.equalsIgnoreCase(jdbcDriverClassName))){
					description = description + ("Database Driver Class Name :: " + oldjdbcDriverClassName + " updated to new Database Driver Class Name :: " + jdbcDriverClassName+ " ; ");
				}
				if(!(oldjdbcMaxActive.equalsIgnoreCase(jdbcMaxActive))){
					description = description + ("Database Max Active :: " + oldjdbcMaxActive + " updated to new Database Max Active :: " + jdbcMaxActive+ " ; ");
				}
				if(!(oldjdbcDialect.equalsIgnoreCase(jdbcDialect))){
					description = description + ("JDBC Dialect :: " + oldjdbcDialect + " updated to new JDBC Dialect :: " + jdbcDialect+ " ; ");
				}
				if(!(oldjdbcInitialSize.equalsIgnoreCase(jdbcInitialSize))){
					description = description + ("JDBC Initial Size :: " + oldjdbcInitialSize + " updated to new JDBC Initial Size :: " + jdbcInitialSize+ " ; ");
				}
				if(!(oldjdbcAcquireIncrement.equalsIgnoreCase(jdbcAcquireIncrement))){
					description = description + ("JDBC Acquire Increment Size:: " + oldjdbcAcquireIncrement + " updated to new JDBC Acquire Increment Size:: " + jdbcAcquireIncrement+ " ; ");
				}
				
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 874 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception e) {
			logger.error("insertServerConfigurationDB() In AdministratorController.java: NullPointerException", e);
		}		
		return new ModelAndView("administrator/serverConfigurationDB");
	}

	/**
	 * Read EMAIL Configuration File , if exists
	 */
	
	@RequestMapping(value = "/serverConfigurationEMAIL", method = RequestMethod.GET)
	public ModelAndView serverConfigurationEMAIL(HttpServletRequest request,
		HttpServletResponse response, ModelMap model, ServerConfiguration serverConfiguration) {
		try {
			ServerConfiguration ServerConfigurationEmail=administratorService.getServerConfigurationEMAIL(serverConfiguration);
			model.addAttribute("serverConfiguration", ServerConfigurationEmail);
		} catch (Exception e) {
			logger.error("serverConfigurationEMAIL() In AdministratorController.java: Exception", e);
		}
		return new ModelAndView("administrator/serverConfigurationEMAIL");
	}

	/**
	 * Save Email Configuration  
	 */
	
	@RequestMapping(value = "/insertServerConfigurationEMAIL", method = RequestMethod.POST)
	public ModelAndView insertServerConfigurationEMAIL(HttpServletRequest request,
		HttpServletResponse response, ModelMap model,
		ServerConfiguration serverConfiguration,
		@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			serverConfiguration.setUpdatedBy(sessionObject.getUserId());
			String status=administratorService.insertServerConfigurationEMAIL(serverConfiguration);
			if(status.equalsIgnoreCase("successMessage")){
				model.addAttribute("successMessage", "Configuration Updated Successfully");
			}else{
				model.addAttribute("errorMessage","Failed To Save Configuration");	
			}
			/**Added by @author Saif.Ali
			 * Date-29-03-2018*/
			String oldmailServerIp = request.getParameter("oldmailServerIp");
			String oldmailServerPort = request.getParameter("oldmailServerPort");
			String oldmailUserName = request.getParameter("oldmailUserName");
			String oldmailPassword = request.getParameter("oldmailPassword");
			String oldmailTransportProtocol = request.getParameter("oldmailTransportProtocol");
			String oldmailSmtpAuth = request.getParameter("oldmailSmtpAuth");
			String oldmailSmtpStarttlsEnable = request.getParameter("oldmailSmtpStarttlsEnable");
			String oldmailDebug = request.getParameter("oldmailDebug");
			
			String mailServerIp = request.getParameter("mailServerIp");
			String mailServerPort = request.getParameter("mailServerPort");
			String mailUserName = request.getParameter("mailUserName");
			String mailPassword = request.getParameter("mailPassword");
			String mailTransportProtocol = request.getParameter("mailTransportProtocol");
			String mailSmtpAuth = request.getParameter("mailSmtpAuth");
			String mailSmtpStarttlsEnable = request.getParameter("mailSmtpStarttlsEnable");
			String mailDebug = request.getParameter("mailDebug");
			String description ="" ;
			
			if(status.equalsIgnoreCase("successMessage")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT EMAIL SERVER CONFIGURATION DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("SYSTEM ADMINISTRATION");
				updateLog.setUpdatedFor("User Name :: " + mailUserName + " password :: " + mailPassword);
				if(!(oldmailServerIp.equalsIgnoreCase(mailServerIp))){
					description = description + ("EMAIL Server IP :: " + oldmailServerIp + " updated to new EMAIL Server IP :: " + mailServerIp + " ; ");
				}
				if(!(oldmailServerPort.equalsIgnoreCase(mailServerPort))){
					description = description + ("EMAIL Server Port  :: " + oldmailServerPort + " updated to new EMAIL Server Port :: " + mailServerPort+ " ; ");
				}
				if(!(oldmailUserName.equalsIgnoreCase(mailUserName))){
					description = description + ("EMAIL User Name :: " + oldmailUserName + " updated to new EMAIL User Name :: " + mailUserName+ " ; ");
				}
				if(!(oldmailPassword.equalsIgnoreCase(mailPassword))){
					description = description + ("EMAIL Password  :: " + oldmailPassword + " updated to new EMAIL Password  :: " + mailPassword+ " ; ");
				}
				if(!(oldmailTransportProtocol.equalsIgnoreCase(mailTransportProtocol))){
					description = description + ("EMAIL Transport Protocol  :: " + oldmailTransportProtocol + " updated to new EMAIL Transport Protocol  :: " + mailTransportProtocol+ " ; ");
				}
				if(!(oldmailSmtpAuth.equalsIgnoreCase(mailSmtpAuth))){
					description = description + ("EMAIL SMTP Authorization :: " + oldmailSmtpAuth + " updated to new EMAIL SMTP Authorization :: " + mailSmtpAuth)+ " ; ";
				}
				if(!(oldmailSmtpStarttlsEnable.equalsIgnoreCase(mailSmtpStarttlsEnable))){
					description = description + ("EMAIL SMTP Starttls  :: " + oldmailSmtpStarttlsEnable + " updated to new EMAIL SMTP Starttls :: " + mailSmtpStarttlsEnable+ " ; ");
				}
				if(!(oldmailDebug.equalsIgnoreCase(mailDebug))){
					description = description + ("EMAIL Debug :: " + oldmailDebug + " updated to new EMAIL Debug :: " + mailDebug+ " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 977 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception e) {
			logger.error("insertServerConfigurationEMAIL() In AdministratorController.java: NullPointerException", e);
		}
		return new ModelAndView("administrator/serverConfigurationEMAIL");
	}


	/**
	 * Read SMS Configuration , if exists
	 */
	
	@RequestMapping(value = "/serverConfigurationSMS", method = RequestMethod.GET)
	public ModelAndView configureSMSServer(HttpServletRequest request,
		HttpServletResponse response, ModelMap model) {
		try {
			ServerConfiguration serverConfiguration = administratorService.getConfigureSMSServer();			
			model.addAttribute("serverConfiguration", serverConfiguration);
		} catch (Exception e) {
			logger.error("insertServerConfigurationEMAIL() In AdministratorController.java: Exception", e);
		}	
		return new ModelAndView("administrator/serverConfigurationSMS");
	}

	/**
	 * Save SMS Configuration 
	 */

	@RequestMapping(value = "/insertSMSServerConfiguration", method = RequestMethod.POST)
	public ModelAndView insertSMSServerConfiguration(HttpServletRequest request,
		HttpServletResponse response, ModelMap model,
		ServerConfiguration serverConfiguration,
		@ModelAttribute("sessionObject") SessionObject sessionObject) {

		try {
			serverConfiguration.setUpdatedBy(sessionObject.getUserId());
			String status=administratorService.insertSMSServerConfiguration(serverConfiguration);
			if(status.equalsIgnoreCase("successMessage")){
				model.addAttribute("successMessage", "Configuration Updated Successfully");
			}else{
				model.addAttribute("errorMessage","Failed To Save Configuration");	
			}
			/**Added by @author Saif.Ali
			 * Date-29-03-2018*/
			String oldauthkey = request.getParameter("oldauthkey");
			String oldsenderId = request.getParameter("oldsenderId");
			String oldroute = request.getParameter("oldroute");
			String oldsmsURL = request.getParameter("oldsmsURL");
			String oldproxy = request.getParameter("oldproxy");
			String oldproxyUser = request.getParameter("oldproxyUser");
			String oldproxyPassword = request.getParameter("oldproxyPassword");
			String oldproxySet = request.getParameter("oldproxySet");
			String oldproxyHost = request.getParameter("oldproxyHost");
			String oldproxyPort = request.getParameter("oldproxyPort");
						
			String authkey = request.getParameter("authkey");
			String senderId = request.getParameter("senderId");
			String route = request.getParameter("route");
			String smsURL = request.getParameter("smsURL");
			String proxy = request.getParameter("proxy");
			String proxyUser = request.getParameter("proxyUser");
			String proxyPassword = request.getParameter("proxyPassword");
			String proxySet = request.getParameter("proxySet");
			String proxyHost = request.getParameter("proxyHost");
			String proxyPort = request.getParameter("proxyPort");
			String description ="" ;
			
			if(status.equalsIgnoreCase("successMessage")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT SMS SERVER CONFIGURATION DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("SYSTEM ADMINISTRATION");
				updateLog.setUpdatedFor("Authentication Key :: " + authkey);
				if(!(oldauthkey.equalsIgnoreCase(authkey))){
					description = description + ("Authentication Key :: " + oldauthkey + " updated to new Authentication Key :: " + authkey + " ; ");
				}
				if(!(oldsenderId.equalsIgnoreCase(senderId))){
					description = description + ("Sender ID   :: " + oldsenderId + " updated to new Sender ID  :: " + senderId+ " ; ");
				}
				if(!(oldroute.equalsIgnoreCase(route))){
					description = description + ("Route  :: " + oldroute + " updated to new Route  :: " + route+ " ; ");
				}
				if(!(oldsmsURL.equalsIgnoreCase(smsURL))){
					description = description + ("SMS URL  :: " + oldsmsURL + " updated to new SMS URL  :: " + smsURL+ " ; ");
				}
				if(!(oldproxy.equalsIgnoreCase(proxy))){
					description = description + ("Proxy Enabled  :: " + oldproxy + " updated to new Proxy Enabled  :: " + proxy+ " ; ");
				}
				if(!(oldproxyUser.equalsIgnoreCase(proxyUser))){
					description = description + ("Proxy UserName  :: " + oldproxyUser + " updated to new Proxy UserName  :: " + proxyUser)+ " ; ";
				}
				if(!(oldproxyPassword.equalsIgnoreCase(proxyPassword))){
					description = description + ("Proxy Password   :: " + oldproxyPassword + " updated to new Proxy Password  :: " + proxyPassword+ " ; ");
				}
				if(!(oldproxySet.equalsIgnoreCase(proxySet))){
					description = description + ("Proxy Set  :: " + oldproxySet + " updated to new Proxy Set :: " + proxySet+ " ; ");
				}
				if(!(oldproxyHost.equalsIgnoreCase(proxyHost))){
					description = description + ("Proxy Host  :: " + oldproxyHost + " updated to new Proxy Host :: " + proxyHost+ " ; ");
				}
				if(!(oldproxyPort.equalsIgnoreCase(proxyPort))){
					description = description + ("Proxy Port  :: " + oldproxyPort + " updated to new Proxy Port :: " + proxyPort+ " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 1092 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (CustomException e) {
			logger.error("insertSMSServerConfiguration() In AdministratorController.java: NullPointerException", e);
		}
		return new ModelAndView("administrator/serverConfigurationSMS");
	}

	
	/*
	 * 
	 *   Configuration Part Ends Here
	 * 
	 * 
	 * */
	
	
	
	
	
	
	/*
	 * 
	 *   Access Type Work Starts
	 * 
	 * 
	 * */
	
	
	
	/**
	 * Role Access Mapping List
	 */
	
	@RequestMapping(value = "/roleAccessMapping", method = RequestMethod.GET)
	public ModelAndView roleAccessMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Role> roleList = null;
		List<AccessType> accessTypeList = null;
		String strView = null;
		try {
			accessTypeList = administratorService.getAccessTypeList();
			if (null!=accessTypeList && accessTypeList.size() != 0) {
				logger.info("In administratorController roleAccessMapping() method");
				PagedListHolder<AccessType> pagedListHolder = administratorService.getAccessTypeListPaging(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", accessTypeList);
				strView = "administrator/accessTypeRoleMappingList";
			} else {
				Resource resource = administratorService.getResourceAndRolesFromDB();
				if(null!=resource){
					roleList=resource.getRoleList();
					model.addAttribute("roleList", roleList);
				}
				
				strView = "administrator/roleAccessMapping";
			}
		} catch (Exception e) {
			logger.error("Exception in roleAccessMapping() method in AdministratorController: ",
					e);
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * Role Access Mapping List Pagination
	 */
	
		@RequestMapping(value = "/roleAccessMappingPagination", method = RequestMethod.GET)
		public ModelAndView roleAccessMappingPagination(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mav = null;
			try {
				logger.info("In roleAccessMappingPagination() method of administratorController");
				mav = new ModelAndView("administrator/accessTypeRoleMappingList");
				logger.info("In AdministratorController getAccessTypeListPaging()");
				PagedListHolder<AccessType> pagedListHolder = administratorService.getAccessTypeListPaging(request);
				if (pagedListHolder != null) {
					mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
					mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
					mav.addObject("total", pagedListHolder.getNrOfElements());
					mav.addObject("pagedListHolder", pagedListHolder);
				}
			} catch (Exception e) {
				logger.error("Error in administratorController getAccessTypeListPaging() method for Exception: ", e);
			}
			return mav;
		}

/**
 *  create New Access Type.jsp page.
 */

		@RequestMapping(value = "/createNewAccessType", method = RequestMethod.POST)
		public ModelAndView createNewAccessType(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {			
			String strView = null;
			try {
				Resource resource = administratorService.getResourceAndRolesFromDB();
				if(null!=resource){
					model.addAttribute("roleList", resource.getRoleList());
				}
				strView = "administrator/roleAccessMapping";
			} catch (Exception e) {
				logger.error("Exception in createNewAccessType() method in AdministratorController: ",e);
			}
			return new ModelAndView(strView);
		}


	/**
	 * Create New Access Type 
	 */
		@RequestMapping(value = "/addRoleAccessMapping", method = RequestMethod.POST)
		public ModelAndView addRoleAccessMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam(value = "roleName") String[] roleCode,
				AccessType accessType,@ModelAttribute("sessionObject") SessionObject sessionObject) {
			List<Role> roleList = new ArrayList<Role>();		
			try {
				for (int count = 0; count < roleCode.length; count++) {
					if (roleCode[count] != null && roleCode[count] != "") {
						Role role = new Role();
						role.setRoleCode(roleCode[count].trim());
						role.setUpdatedBy(sessionObject.getUserId());
						roleList.add(role);
					}
				}
				accessType.setUpdatedBy(sessionObject.getUserId());
				accessType.setRoleList(roleList);
				String insertStatus = administratorService.addRoleAccessMapping(accessType);
				if (null != insertStatus) {
					if (insertStatus.equals("SUCCESS")) {
						model.addAttribute("successMessage",
								"Access Type Created Successfully ");
					} else {
						model.addAttribute("errorMessage",
								"Failed To Create Access Type");			
					}
				}
				/**Added by @author Saif.Ali
				 * Date-28-03-2018*/
				String accessTypeName = request.getParameter("accessTypeName");
				String accessTypeDesc = request.getParameter("accessTypeDesc");
				String description = "";
				if(insertStatus.equalsIgnoreCase("SUCCESS")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("INSERT ACCESS TYPE ROLE MAPPING");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("SYSTEM ADMINISTRATION");
					description = description + ("Access Type Name :: " + accessTypeName + " Access Type Desc :: " + accessTypeDesc + " ; ");
					for (int count = 0; count < roleCode.length; count++) {
						if (roleCode[count] != null && roleCode[count] != "") {
							updateLog.setUpdatedFor("Role :: " + roleCode[count]);
							description = description + ("Role :: " + roleCode[count]);
						}
					}
					
					updateLog.setDescription(description);
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 969 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
			} catch (Exception e) {
				logger.error("Exception in insertRoleAccessMapping() method in AdministratorController: ",e);
			}
			return roleAccessMapping(request,response,model);
		}


		/**
		 * Edit Access Type Role Mapping Old. 
		 */
		@RequestMapping(value = "/createNewAccessType", method = RequestMethod.POST, params = "edit")
		public ModelAndView editAccessTypeRoleMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("accessTypeRadio") String[] accessTypeRadio) {
			AccessType accessTypeDetails = null;			
			try {
				AccessType accessType = new AccessType();
				if (null !=accessTypeRadio && accessTypeRadio.length != 0) {
						for (int count = 0; count < accessTypeRadio.length; count++) {
							accessType.setAccessTypeCode(accessTypeRadio[count].trim());
						}
						accessTypeDetails = administratorService.editAccessTypeRoleMapping(accessType);
						if(null!=accessTypeDetails){
							model.addAttribute("accessTypeDetails", accessTypeDetails);
						}
						Resource resource = administratorService.getResourceAndRolesFromDB();
						if(null!=resource){
							model.addAttribute("roleList", resource.getRoleList());
						}						
				}
			} catch (Exception e) {
				logger.error(
						"Exception in editAccessTypeRoleMapping() method in AdministratorController: ",
						e);
			}
			return new ModelAndView("administrator/editAccessTypeRoleMapping");
		}
		
		/**
		 * Edit Access Type Role Mapping New. 
		 */
		@RequestMapping(value = "/editAccessType", method = RequestMethod.GET)
		public ModelAndView editAccessType(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("accessTypeRadio") String[] accessTypeRadio) {
			AccessType accessTypeDetails = null;			
			try {
				//System.out.println("within");
				AccessType accessType = new AccessType();
				if (null !=accessTypeRadio && accessTypeRadio.length != 0) {
						for (int count = 0; count < accessTypeRadio.length; count++) {
							accessType.setAccessTypeCode(accessTypeRadio[count].trim());
						}
						accessTypeDetails = administratorService.editAccessTypeRoleMapping(accessType);
						if(null!=accessTypeDetails){
							model.addAttribute("accessTypeDetails", accessTypeDetails);
						}
						Resource resource = administratorService.getResourceAndRolesFromDB();
						if(null!=resource){
							model.addAttribute("roleList", resource.getRoleList());
						}						
				}
			} catch (Exception e) {
				logger.error(
						"Exception in editAccessTypeRoleMapping() method in AdministratorController: ",
						e);
			}
			return new ModelAndView("administrator/editAccessTypeRoleMapping");
		}
		
		
		/**
		 * Update Access Type Role Mapping
		 */
		@RequestMapping(value = "/updateAccessTypeRoleMapping", method = RequestMethod.POST)
		public ModelAndView updateAccessTypeRoleMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject,
				@RequestParam(value = "roleName") String[] roleCode,
				AccessType accessType) {
			List<Role> roleList = new ArrayList<Role>();
			try {
				for (int count = 0; count < roleCode.length; count++) {
					if ( null!=roleCode[count]  && roleCode[count] != "") {
						Role role = new Role();
						role.setUpdatedBy(sessionObject.getUserId());
						role.setRoleCode(roleCode[count].trim());
						roleList.add(role);
					}
				}
				accessType.setUpdatedBy(sessionObject.getUserId());
				accessType.setRoleList(roleList);
				String insertStatus = administratorService.updateAccessTypeRoleMapping(accessType);
				if (null != insertStatus) {
					if (insertStatus.equals("SUCCESS")) {
						model.addAttribute("successMessage",
								"Access Type Updated Successfully ");
					} else {
						model.addAttribute("errorMessage",
								"Failed To Update Access Type");			
					}
				}
			} catch (Exception e) {
				logger.error(
						"Exception in updateAccessTypeRoleMapping() method in AdministratorController: ",
						e);
			}
			return roleAccessMapping(request,response,model);
		}

		
		
	/**
	 * Delete Access Type Role Mapping 
	 */
		@RequestMapping(value = "/createNewAccessType", method = RequestMethod.POST, params = "delete")
		public ModelAndView inactiveAccessType(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject,
				@RequestParam("accessTypeRadio") String[] accessTypeCode) {			
			AccessType accessType = new AccessType();
			try {
				if (null!=accessTypeCode && accessTypeCode.length != 0) {
					for (int count = 0;count < accessTypeCode.length; count++) {
						accessType.setUpdatedBy(sessionObject.getUserId());
						accessType.setAccessTypeCode(accessTypeCode[count].trim());
					}
				}
				String insertStatus = administratorService.inactiveAccessType(accessType);				
				if (null != insertStatus) {
					if (insertStatus.equals("SUCCESS")) {
						model.addAttribute("successMessage",
								"Access Type Removed Successfully ");
					} else {
						model.addAttribute("errorMessage",
								"Failed To Remove Access Type");			
					}
				}		
			} catch (NullPointerException e) {
				logger.error("inactiveAccessType() In AdministratorController.java: NullPointerException", e);
			} catch (Exception e) {
				logger.error("inactiveAccessType() In AdministratorController.java: Exception", e);
			}
			return roleAccessMapping( request, response,  model);
		}

	


	/**
	 * Open Access Type Contact Mapping Page
	 */
	@RequestMapping(value = "/accessTypeContactMapping", method = RequestMethod.GET)
	public ModelAndView accessTypeContactMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Resource resource = new Resource();
		List<Resource> resourceList = null;
		try {
			resource = administratorService.getResourceAndAccessFromDB();
			
			if (resource != null) {
				model.addAttribute("resource", resource);
			}
			resourceList = administratorService.getAccessTypeContactMappingList();
			if (resourceList != null && resourceList.size() != 0) {
				logger.info("In AdministratorController listAccessTypeContactMapping() method");
				PagedListHolder<Resource> pagedListHolder = administratorService.getListAccessTypeContactMappingPaging(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", resourceList);
			}
			
		} catch (Exception e) {
			logger.error("Exception in accessTypeContactMapping() method in AdministratorController: ",	e);
		}
		return new ModelAndView("administrator/accessTypeContactMapping");
	}
	
	/*
	 * Insert Access Type Contact Mapping	 
	 */
	@RequestMapping(value = "/insertAccessTypeContactMapping", method = RequestMethod.POST)
	public ModelAndView insertAccessTypeContactMapping(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, AccessType accessType, ResourceType resourceType,
			Resource resource,@ModelAttribute("sessionObject") SessionObject sessionObject) {		
		try {
			if(null!=accessType && null!=resourceType && null!=resource){
			resource.setAccessType(accessType);
			resource.setResourceType(resourceType);
			resource.setUpdatedBy(sessionObject.getUserId());
			String insertStatus = administratorService.addAccessTypeContactMapping(resource);
			if (null != insertStatus) {
				if (insertStatus.equals("SUCCESS")) {
					model.addAttribute("successMessage",
							"Access Type Assigned Successfully ");
				} else {
					model.addAttribute("errorMessage",
							"Failed To Assigned Access Type");			
					}
				}			
			}
		} catch (Exception e) {
			logger.error("Exception in editAccessTypeRoleMapping() method in AdministratorController: ",e);
		}
		return accessTypeContactMapping(request,response,model);
	}

	/**
	 *Access Type Role Mapping List 	 
	 */
	@RequestMapping(value = "/listAccessTypeContactMapping", method = RequestMethod.GET)
	public ModelAndView getAccessTypeContactMappingList(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		List<Resource> resourceList = null;
		try {
			resourceList = administratorService.getAccessTypeContactMappingList();
			if (resourceList != null && resourceList.size() != 0) {
				logger.info("In AdministratorController listAccessTypeContactMapping() method");
				PagedListHolder<Resource> pagedListHolder = administratorService.getListAccessTypeContactMappingPaging(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Exception in listAccessTypeContactMapping() method in AdministratorController: ",e);
		}
		return new ModelAndView("administrator/listAccessTypeContactMapping");
	}

	/**
	 *	Pagination in Access Type Role Mapping List 	 
	 */
	@RequestMapping(value = "/listAccessTypeContactMappingPagination", method = RequestMethod.GET)
	public ModelAndView listAccessTypeContactMappingPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("administrator/listAccessTypeContactMapping");
		try {
			logger.info("In listAccessTypeContactMappingPagination() method of AdministratorController");			
			PagedListHolder<Resource> pagedListHolder = administratorService.getListAccessTypeContactMappingPaging(request);
			if (pagedListHolder != null) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in AdministratorController getListAccessTypeContactMappingPaging() method for Exception: ", e);
		}
		return mav;
	}

	/**
	 *	Search in Access Type Role Mapping List 	 * 
	 */
	@RequestMapping(value = "/accessTypeContactMappingSearch", method = RequestMethod.POST)
	public ModelAndView accessTypeContactMappingSearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "data", required = false) String data
			) {
		try {
			logger.info("accessTypeContactMappingSearch() merhod ");
			if(data!=null){
				data=data.trim();
			}
			Map<String, Object> parameters = new HashMap<String, Object>();
			if (query.equalsIgnoreCase("contactName")) {
				parameters.put("contactName", data);
			}
			if (query.equalsIgnoreCase("accessType")) {
				parameters.put("accessType", data);
			}
			if (query.equalsIgnoreCase("userId")) {
				parameters.put("userId", data);
			}
			administratorService.accessTypeContactMappingSearch(parameters);
		} catch (Exception e) {
			logger.error("Exception in accessTypeContactMappingSearch() merhod in AdmissionController",e);
		}
		return listAccessTypeContactMappingPagination(request,response);
	}
	
	
	
	/**
	 * Inactive Access Type Role Mapping List 	 * 
	 */
	
	@RequestMapping(value = "/inactiveAccessTypeContactMapping", method = RequestMethod.GET)
	public ModelAndView editAccessTypeContactMapping(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,
			@RequestParam("resourceId") String resourceId,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {		
		try {
			if (resourceId != null) {
				String insertStatus = administratorService.inactiveAccessTypeContactMapping(resourceId,sessionObject.getUserId());
				if (null != insertStatus) {
					if (insertStatus.equals("SUCCESS")) {
						model.addAttribute("successMessage",
								"Access Type - Contact Mapping Deleted Successfully.");
					} else {
						model.addAttribute("errorMessage",
								"Failed To Delete Access Type - Contact Mapping.");			
						}
					}	
			}
		} catch (Exception e) {
			logger.error("Exception in deleteAccessTypeContactMapping() method in AdministratorController: ",e);
		}
		return 
				accessTypeContactMapping(request,response,model);
		
	}
	
	
	
	/*
	 * Link To Open Functionality Role Mapping
	 * */
	
	@RequestMapping(value = "/functionalityRole", method = RequestMethod.GET)
	public String functionalityRole(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("functionalityRole() In AdministratorController.java: ");		
		try{
			List<Module> moduleList=administratorService.getmoduleList();
			if(null!=moduleList&& moduleList.size()!=0){
				model.addAttribute("moduleList",moduleList);				
			}else{
				logger.info("functionalityRole() In AdministratorController.java: No moduleList Found in db");
			}			
		}catch(Exception e){
			logger.error("Exception in functionalityRole() method in AdministratorController: ", e);
		}
		return ("administrator/functionalityRole");
	}
	
	/**
	 *  Functionality Role Mapping
	 */
	@RequestMapping(value = "/functionalityRoleMapping", method = RequestMethod.POST)
	public String functionalityRoleMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("moduleCode") String moduleCode,
			@RequestParam("roleCode") String roleCode) {
		Module module = null;
		String strView=null;
		try {			
			logger.info("functionalityRoleMapping() In AdministratorController.java: ");
			List<Module> moduleList=administratorService.getmoduleList();
			if(null!=moduleList&& moduleList.size()!=0){
				model.addAttribute("moduleList",moduleList);				
			}
			if(null!=moduleCode && null!=roleCode){
				Role role=new Role();
				role.setRoleCode(roleCode);
				role.setModuleCode(moduleCode);
				module = administratorService.getFunctionalitiesForRole(role);
				if(null!=module){
					model.addAttribute("module", module);	
					strView="administrator/functionalityRole";
				}else{
					model.addAttribute("errorMessage","No Functionalities Found For The Module");
					strView=functionalityRole(request,response,model);
				}
			}					
		} catch (Exception e) {
			logger.error("Exception in functionalityRoleMapping() method in AdministratorController: ",e);
			
		}
		return strView;
	}
	
	
	@RequestMapping(value = "/functionalityRoleMappingExcelDownload", method = RequestMethod.GET)
	public String functionalityRoleMappingExcelDownload(HttpServletRequest request,HttpServletResponse response, ModelMap model
														,@RequestParam("moduleCode") String moduleCode,
														@RequestParam("roleCode") String roleCode) {
		String strView=null;
		Module module = null;
		Role role=new Role();
		try{
			FileUploadDownload fileUploadDownload = new FileUploadDownload();
			if(null!=moduleCode && null!=roleCode){
				role.setModuleCode(moduleCode);
				role.setRoleCode(roleCode);
				module = administratorService.getFunctionalitiesForRole(role);					
			}
			if(null==module.getRoleList() || module.getRoleList().size() == 0){
				model.addAttribute("ResultError", "No Role's Found For The Module");
				strView="common/errorpage";
			}
			else{
				module.setModuleName(module.getRoleList().get(0).getFunctionalityList().get(0).getModuleName());
				boolean status = administratorService.writeExcelForFunctionalityRoleMapping(module,rootPath+excelDownloadFolder,functionalityRoleMappingExcel);
				if(status == true){
					String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelDownloadFolder,functionalityRoleMappingExcel);
					if(returnMessage==null){
						model.addAttribute("ResultError", "File not available");
						strView="common/errorpage";
					}
				}
				else{
					model.addAttribute("ResultError", "Failed To Create Excel Sheet");
					strView="common/errorpage";
				}
			}			
		}catch(Exception e){
			logger.error("Exception in functionalityRoleMappingExcelDownload() for download Template Excel", e);
		}
	return strView;
	}
	
	
	@RequestMapping(value = "/functionalityRoleMappingExcelUpload", method = RequestMethod.POST)
	public String functionalityRoleMappingExcelUpload(@ModelAttribute("uploadFile") UploadFile uploadFile,
													  HttpServletRequest request,HttpServletResponse response,
													  ModelMap model,
													  @RequestParam("moduleCode") String moduleCode,
													  @ModelAttribute("sessionObject") SessionObject sessionObject){
		
		try{
			FileUploadDownload fileUploadDownload = new FileUploadDownload();
			String returnMessage = fileUploadDownload.uploadExcel(uploadFile,rootPath+excelUploadFolder,functionalityRoleMappingExcel);
			if(returnMessage==null){
				model.addAttribute("uploadErrorMessage", functionalityRoleMappingExcel+" upload failed.");
			}else{
				returnMessage=administratorService.updateFunctionalityRoleMappingFromExcel(rootPath+excelUploadFolder,functionalityRoleMappingExcel,moduleCode,sessionObject.getUserId());				
				if(returnMessage.equals("SUCCESS")){
					model.addAttribute("successMessage","Data Uploaded Successfully");
				}else{
					model.addAttribute("errorMessage","Failed To Upload.");	
				}			
				
			}
		}catch(Exception e){
			logger.error("Exception in functionalityRoleMappingExcelUpload() for upload Template Excel", e);
		}
	return  functionalityRole(request,response,model);
	}
	
	
	
	/**
	 *
	 */
	@RequestMapping(value = "/insertFunctionalityRoleMapping", method = RequestMethod.POST)
	public String insertFunctionalityRoleMapping(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @RequestParam("moduleCode") String moduleCode,
			@RequestParam("functionality0") String[] functionality,
			@RequestParam("roleCode") String[] roleCode,
			@RequestParam(required = false, value = "view") String[] view,
			@RequestParam(required = false, value = "insert") String[] insert,
			@RequestParam(required = false, value = "update") String[] update,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In insertFunctionalityRoleMapping() method of AdministratorController");
		List<Role> roleList = new ArrayList<Role>();
		try {
			
			if (null!=roleCode && null!= moduleCode) {
				for(int count = 0; count < roleCode.length; count++) {
					Role role = new Role();				
					role.setRoleCode(roleCode[count]);
					role.setModuleCode(moduleCode);
					// System.out.println(roleName[count]);
					if (null != functionality && functionality.length != 0) {
						List<Functionality> functionalityList = new ArrayList<Functionality>();
						for (int countFunctionality = 0; countFunctionality < functionality.length; countFunctionality++) {
							if (null!=functionality[countFunctionality].trim()) {
								Functionality functions = new Functionality();
								functions.setUpdatedBy(sessionObject
										.getUserId());
								functions
										.setFunctionalityName(functionality[countFunctionality]
												.trim());
								if ( null!=view && view.length != 0) {
									for (int countView = 0; countView < view.length; countView++) {
										if (view[countView] != null) {
											String[] str = view[countView].split("#");
											String mName = str[0].trim();
											String splitStr = str[1];
											String[] subStr = splitStr.split("~");
											String function = subStr[0].trim();
											if (mName
													.equalsIgnoreCase(roleCode[count])
													&& function.equalsIgnoreCase(functionality[countFunctionality])) {
												functions.setView(true);
											}
										}
									}
								} else {
									functions.setView(false);
								}
								if (null!=insert && insert.length != 0) {
									for (int countInsert = 0; countInsert < insert.length; countInsert++) {
										if (insert[countInsert] != null) {
											String[] splitString = insert[countInsert]
													.split("#");
											String mName = splitString[0].trim();
											String subStr = splitString[1];
											String[] splitStr = subStr.split("~");
											String function = splitStr[0].trim();
											if (mName
													.equalsIgnoreCase(roleCode[count])
													&& function.equalsIgnoreCase(functionality[countFunctionality])) {
												functions.setInsert(true);
											}
										}
									}
								} else {
									functions.setInsert(false);
								}

								if (null!=update && update.length != 0) {
									for (int countUpdate = 0; countUpdate < update.length; countUpdate++) {
										if (update[countUpdate] != null) {
											String[] str = update[countUpdate]
													.split("#");
											String mName = str[0].trim();
											String subStr = str[1];
											String[] splitStr = subStr.split("~");
											String function = splitStr[0].trim();
											if (mName
													.equalsIgnoreCase(roleCode[count])
													&& function.equalsIgnoreCase(functionality[countFunctionality])) {
												functions.setUpdate(true);
											}
										}
									}
								} else {
									functions.setUpdate(false);
								}

								functionalityList.add(functions);
							}
						}
						role.setFunctionalityList(functionalityList);
					}
					roleList.add(role);
				}				
			}
			
			if(null!=roleList && roleList.size()!=0){
				String insertStatus = administratorService.insertFunctionalityRoleMapping(roleList);				
				if (null != insertStatus) {
					if (insertStatus.equals("SUCCESS")) {
						model.addAttribute("successMessage","Functionality Mapped Successfully.");
					} else {
						model.addAttribute("errorMessage",
								"Failed To Map Functionality");			
						}
					}
			}else{
				model.addAttribute("errorMessage","Failed To Map Functionality");	
			}
			
		} catch (NullPointerException e) {
			logger.error("insertFunctionalityRoleMapping() In AdministratorController.java: NullPointerException", e);
		} catch (Exception e) {
			logger.error("insertFunctionalityRoleMapping() In AdministratorController.java: Exception", e);
		}
		return functionalityRole(request,response,model);
	}

	
	
//	viewAllUserGroups.html
	/*
	 * Link To Open Create New User Group
	 * */
	
	@RequestMapping(value = "/createUserGroup", method = RequestMethod.GET)
	public ModelAndView createUserGroup(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("createUserGroup() In AdministratorController.java: ");		
		try{
			Resource resource = administratorService.getResourceAndRolesFromDB();
			if(null!=resource){
				model.addAttribute("resourceTypeList", resource.getResourceTypeList());
			}
			List<UserGroup> userGroupList = administratorService.getAllUserGroups();
			if(null!=userGroupList && userGroupList.size()!=0){				
				PagedListHolder<UserGroup> pagedListHolder = administratorService.getUserGroupPagination(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", userGroupList);
			}		
			
		}catch(Exception e){
			logger.error("Exception in createUserGroup() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/createUserGroup");
	}
	
	
	
	@RequestMapping(value = "/insertUserGroup", method = RequestMethod.POST)
	public ModelAndView insertUserGroup(HttpServletRequest request,
			HttpServletResponse response,ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam(value = "userName") String[] userId,
			UserGroup userGroup) 
			{		
		try {
			
			if(null!=userGroup && null!=userId && userId.length!=0){
				List<Resource> resourceList=new ArrayList<Resource>();
				for (int count = 0; count < userId.length; count++) {
					if ( null!=userId[count]  && userId[count] != "") {
						Resource resource=new Resource();
						resource.setUserId(userId[count]);
						resource.setUpdatedBy(sessionObject.getUserId());
						resourceList.add(resource);
					}
				}
				if(resourceList.size()!=0){
					userGroup.setResourceList(resourceList);
					userGroup.setUpdatedBy(sessionObject.getUserId());
					String insertStatus = administratorService.addUserGroup(userGroup);
					if (null != insertStatus) {
						if (insertStatus.equals("SUCCESS")) {
							model.addAttribute("successMessage",
									"User Group Created Successfully");
						} else {
							model.addAttribute("errorMessage",
									"Failed To Create User Group");			
						}
					}
					
				}
			}
		} catch (Exception e) {
			logger.error("Exception in updateAccessTypeRoleMapping() method in AdministratorController: ",e);
		}
		return createUserGroup(request,response,model);
	}
	
	


	/*
	 * Link To Open View User Group
	 * */
	
	@RequestMapping(value = "/viewAllUserGroups", method = RequestMethod.GET)
	public ModelAndView viewAllUserGroups(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("viewAllUserGroups() In AdministratorController.java: ");		
		try{
			List<UserGroup> userGroupList = administratorService.getAllUserGroups();
			if(null!=userGroupList && userGroupList.size()!=0){				
				PagedListHolder<UserGroup> pagedListHolder = administratorService.getUserGroupPagination(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", pagedListHolder);
			}			
		}catch(Exception e){
			logger.error("Exception in viewAllUserGroups() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/listUserGroups");
	}
	
	
	/*
	 * Link To Open View User Group
	 * */
	@RequestMapping(value = "/viewAllUserGroupsPagination", method = RequestMethod.GET)
	public ModelAndView viewAllUserGroupsPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("administrator/listUserGroups");
		try {
			logger.info("In viewAllUserGroupsPagination() method of AdministratorController");
			logger.info("In AdministratorController bookListPagination() method: calling viewAllUserGroupsPagination(HttpServletRequest request) in AdministratorController.java");
			PagedListHolder<UserGroup> pagedListHolder = administratorService.getUserGroupPagination(request);
			if (pagedListHolder != null) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			
			logger.error("Error in AdministratorController AdministratorController() method for Exception: ", e);
		}
		return mav;
	}
	/*** Naimisha***/
	
	@RequestMapping(value = "/inactiveUserGroupDetail", method = RequestMethod.GET)
	public ModelAndView inactiveUserGroupDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam("userGroupCode") String userGroupCode) {		
		try {
			
			if(null!=userGroupCode){
				UserGroup userGroup=new UserGroup();
				userGroup.setUserGroupCode(userGroupCode);
				userGroup.setUpdatedBy(sessionObject.getUserId());			
				String status= administratorService.inactiveUserGroupDetails(userGroup);
				if (null!=status) {
					if(status.equals("SUCCESS")){
						model.addAttribute("successMessage", "User Group Deleted Successfully");					
					}else{
						model.addAttribute("errorMessage", "Failed To Delete User Group");										
					}				
				}
			}else{
				model.addAttribute("errorMessage", "Failed To Delete User Group");										
			}
		} catch (NullPointerException e) {
			logger.error("inactiveUserGroupDetails() In AdministratorController.java: NullPointerException",e);
		} catch (Exception e) {
			logger.error("inactiveUserGroupDetails() In AdministratorController.java: Exception",e);
		}
		 return createUserGroup(request,response,model);
	}
	
	
	
	
	@RequestMapping(value = "/getUserGroupDetails", method = RequestMethod.POST, params = "delete")
	public ModelAndView inactiveUserGroupDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam("userGroupCode") String userGroupCode) {		
		try {
			//System.out.println("userGroupCode==="+userGroupCode);
			if(null!=userGroupCode){
				UserGroup userGroup=new UserGroup();
				userGroup.setUserGroupCode(userGroupCode);
				userGroup.setUpdatedBy(sessionObject.getUserId());			
				String status= administratorService.inactiveUserGroupDetails(userGroup);
				if (null!=status) {
					if(status.equals("SUCCESS")){
						model.addAttribute("successMessage", "User Group Deleted Successfully");					
					}else{
						model.addAttribute("errorMessage", "Failed To Delete User Group");										
					}				
				}
			}else{
				model.addAttribute("errorMessage", "Failed To Delete User Group");										
			}
		} catch (NullPointerException e) {
			logger.error("inactiveUserGroupDetails() In AdministratorController.java: NullPointerException",e);
		} catch (Exception e) {
			logger.error("inactiveUserGroupDetails() In AdministratorController.java: Exception",e);
		}
		 return viewAllUserGroups(request,response,model);
	}
	
	
/*	@RequestMapping(value = "/getUserGroupDetails", method = RequestMethod.GET)
	public ModelAndView getUserGroupDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam("userGroupCode") String userGroupCode) 
	{		
		String view="null";
		try {
			
			if(null!=userGroupCode){
				UserGroup userGroup=new UserGroup();
				userGroup.setUserGroupCode(userGroupCode);
				userGroup= administratorService.getUserGroupDetails(userGroup);				
				if (null!=userGroup.getResourceList() && userGroup.getResourceList().size()!=0) {					
					model.addAttribute("userGroup", userGroup);
					view="administrator/createUserGroup";
				}else{
					model.addAttribute("errorMessage", "Failed To Get Group Details");	
					view="administrator/createUserGroup";
				}
			}else{
				model.addAttribute("errorMessage", "Failed To Get Group Details");
				view="administrator/createUserGroup";
			}
		//	view="administrator/createUserGroup";
		} catch (NullPointerException e) {
			logger.error("getUserGroupDetails() In AdministratorController.java: NullPointerException",e);
		} catch (Exception e) {
			logger.error("getUserGroupDetails() In AdministratorController.java: Exception",e);
		}
		System.out.println("view=="+view);
		return new ModelAndView(view);
	}*/
	
	
	@RequestMapping(value = "/getUserGroupDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getUserGroupDetails(@RequestParam("userGroupCode") String userGroupCode) {
		String userDetails = null;
		try {
			
			logger.info("getUserGroupDetails() In CommonController.java");
			//userName = commonService.getUserNameForId(userId);
			UserGroup userGroup=new UserGroup();
			if(null!=userGroupCode){
				
				userGroup.setUserGroupCode(userGroupCode);
				userGroup= administratorService.getUserGroupDetails(userGroup);	
			}
			String usergroupname = userGroup.getUserGroupName();
			//System.out.println("usergroupname =="+usergroupname);
			 userDetails = usergroupname + "#";
			List<Resource> resourceList = userGroup.getResourceList();
			int i = 1;
			for(Resource resource : resourceList){
				
				String name = resource.getName();
				String id = resource.getUserId();
				userDetails = userDetails+name+"-"+id;
				if(i < resourceList.size()){
					userDetails = userDetails + "*";
					i++;
				}
				
				
			}
		} catch (NullPointerException e) {
			logger.error("getUserNameForId() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getUserNameForId() In CommonController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(userDetails));
	}
	@RequestMapping(value = "/getUserGroupDetails", method = RequestMethod.POST, params = "back")
	public ModelAndView backUserGroupDetails(HttpServletRequest request,HttpServletResponse response, ModelMap model) {		
		try {
			
		}  catch (Exception e) {
			logger.error("backUserGroupDetails() In AdministratorController.java: Exception",e);
		}
		return viewAllUserGroups(request,response,model);
	}
	
	
	/**
	 * open notification page
	 * 
	 */
	@RequestMapping(value = "/getLoggingNotificationSetUp", method = RequestMethod.GET)
	public ModelAndView getLoggingNotificationSetUp(HttpServletRequest request, HttpServletResponse response, ModelMap model,@RequestParam("module") String module) {
		try{	
			logger.info("getLoggingNotificationSetUp() method in AdministratorController");
			Module	moduleForNotification = administratorService.getNotificationDetails(module);
			if(moduleForNotification!=null){
				model.addAttribute("moduleForNotification",moduleForNotification);
			}
		}catch(Exception e){
			logger.error("Exception in getLoggingNotificationSetUp() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/loggingNotificationSetUp");
	}
	
	@RequestMapping(value = "/updateLoggingNotificationSetUp", method = RequestMethod.POST)
	public ModelAndView updateLoggingNotificationSetUp(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,@ModelAttribute("module") Module module,
			@ModelAttribute("sessionObject") SessionObject sessionObject)  {
		String updateStatus = "false";
		try{	
			
			logger.info("getNotificationChecks() method in AdministratorController");
			
			updateStatus = administratorService.updateLoggingNotificationSetUp(module,sessionObject.getUserId());
			if(updateStatus!=null && updateStatus.equals("false")){
				model.addAttribute("failStatus", "updation fail.");
			}else{
				model.addAttribute("successStatus", "update successfully...");
			}
		}catch(Exception e){
			logger.error("Exception in updateLoggingNotificationSetUp() method in AdministratorController: ", e);
		}
		return getLoggingNotificationSetUp(request,response,model,module.getModuleName());
	}
	
	
	/**
	 * modified by saurav.bhadra
	 * changes taken on 07042017
	 * **/
	

	@RequestMapping(value="/viewSLAForTicketing", method=RequestMethod.GET)
	public String viewSLAForTicketing(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		List<Ticket> ticketList = null;	
		List<Approver> approverList =null;
		List<TicketStatus> ticketStatusList =null;
		String approverListStr="";
		try{
			ticketList=administratorService.viewSLAForTicketing();
			approverList=administratorService.getAllApproverGroups();
			ticketStatusList=commonService.getAllTicketStatus();
			if(null!=ticketList && ticketList.size()!=0){
				model.addAttribute("ticketList", ticketList);				
			}
			else{				
				model.addAttribute("errorMessage", "No SLA Created For Ticketing");
			}
			if(null!=approverList && approverList.size() != 0){
				for(Approver approver : approverList){
					if(approverListStr == ""){
						approverListStr = approverListStr + approver.getApproverGroupName();						
					}else{
						approverListStr = approverListStr +";"+ approver.getApproverGroupName();	
					}
				}
				model.addAttribute("approverListStr", approverListStr);
			}
			model.addAttribute("approverList", approverList);
			model.addAttribute("ticketStatusList", ticketStatusList);
		}catch(Exception e){
			logger.error("viewSLAForTicketing() in AdministrationController", e);
		}				
		return "administrator/viewSLAForTicketing";
	}
	
	/**
	 * changes taken from saurav.bhadra 07042017
	 * **/
	@RequestMapping(value="/createTicketingSLA",method=RequestMethod.POST)
	public String createTicketingSLA(HttpServletRequest request, HttpServletResponse response,ModelMap model,
									@RequestParam("approverGroupName")String[] moduleName,
									@ModelAttribute("sessionObject") SessionObject sessionObject){		
		try{
			Ticket ticket =new Ticket();							
			List<Module> moduleList=new ArrayList<Module>();
			for(int moduleCount=0;moduleCount<moduleName.length;moduleCount++){
				Module module =new Module();								
				module.setModuleName(moduleName[moduleCount]);
				moduleList.add(module);
			}
			if(moduleList.size()!=0){								
				ticket.setModuleList(moduleList);
				ticket.setUpdatedBy(sessionObject.getUserId());
				ticket.setStatus(request.getParameter("status"));								
				ticket.setTicketMaxDays(Integer.parseInt(request.getParameter("ticketMaxDays")));
				ticket.setTicketMinDays(0);
			}
			String insertStatus="false";
			try {
				insertStatus = administratorService.createTicketingSLA(ticket);
				/**Added by @author Saif.Ali
				 * date- 29-03-2018*/
				String description = "";
				String status = request.getParameter("status");
				int ticketMaxDays = Integer.parseInt(request.getParameter("ticketMaxDays"));
				if(insertStatus.equalsIgnoreCase("true"))
				{
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("SETUP SLA FOR TICKETING");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("SYSTEM ADMINISTRATION");
					if(moduleName!=null && moduleName.length!=0){
						for(int i=0;i<moduleName.length;i++){
							updateLog.setUpdatedFor("Approver Group Code :: " + moduleName[i]);
							description = description + ("Approver Group Code :: " + moduleName[i] + " with status :: " + status + " with maximum days granted :: " + ticketMaxDays + " ; ");
						}
					}
					updateLog.setDescription(description);
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 2486 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
				}	
				/***/
			}catch(CustomException e){
				model.addAttribute("errorMessage", "Insertion Failed");				
			}
			if(insertStatus.equalsIgnoreCase("true")){
				model.addAttribute("updateSuccessStatus", "Inserted Sucessfully");
			}else{
				model.addAttribute("errorMessage", "Insertion Failed");
			}
		}catch(NullPointerException e){	
			model.addAttribute("errorMessage", "Insertion Failed");
			logger.error("createTicketingSLA() in AdministrationControllrt", e);
		}
		return viewSLAForTicketing(request,response,model);
	}
	
	/**
	 * changes taken from saurav.bhadra 07042017
	 * **/
	
	@RequestMapping(value="/updateTicketingSLA",method=RequestMethod.POST)
	public String updateTicketingSLA(HttpServletRequest request, HttpServletResponse response,ModelMap model,
									@RequestParam("serialNoDB")String serialNoDB,
									@ModelAttribute("sessionObject") SessionObject sessionObject){			
		try{
			String rowId = request.getParameter("serialNoDB");
			String newModuleName = request.getParameter("newModuleName");
			String newTicketStatus = request.getParameter("newTicketStatus");
			String newMaxDays = request.getParameter("newMaxDays");
			if(null!=serialNoDB){
				if(null!=newModuleName){
					if(null!=newTicketStatus){
						if(null!=newMaxDays){
							Ticket ticket =new Ticket();
							ticket.setUpdatedBy(sessionObject.getUserId());
							ticket.setModuleName(newModuleName);
							ticket.setStatus(newTicketStatus);
							ticket.setTicketMaxDays(Integer.parseInt(newMaxDays));						
							String insertStatus="false";
							try{
								insertStatus = administratorService.updateTicketingSLA(ticket);
								//System.out.println("insertStatus=="+insertStatus);
							}catch(CustomException e){	
								model.addAttribute("errorMessage", "Updated Failed");
								logger.error("updateTicketingSLA() in AdministrationControllrt", e);
							}						
							if(insertStatus.equalsIgnoreCase("true")){
								model.addAttribute("updateSuccessStatus", "Updated Sucessfully");
							}else{
								model.addAttribute("errorMessage", "Updated Failed");
							}
						}
					}else{
						model.addAttribute("errorMessage", "Insertion Failed");
					}
				}else{
					model.addAttribute("errorMessage", "Insertion Failed");
				}
			}else{
				model.addAttribute("errorMessage", "Insertion Failed");
			}
		}catch(NullPointerException e){	
			model.addAttribute("errorMessage", "Updated Failed");
			logger.error("updateTicketingSLA() in AdministrationControllrt", e);
		}
		return viewSLAForTicketing(request,response,model);
	}
	
	/**new method created by saurav.bhadra
	 * chnages taken on 07042017*/
	
	@RequestMapping(value = "/inactiveSLATicket", method = RequestMethod.GET)
	public String deleteSLATicket(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,
			@RequestParam("slaID") String slaId,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {		
		try {
			if (slaId != null) {
				Ticket ticket = new Ticket();
				ticket.setTicketId(Integer.parseInt(slaId));
				ticket.setUpdatedBy(sessionObject.getUserId());
				String insertStatus = administratorService.deleteSLATicket(ticket);
				if(null != insertStatus){
					if(insertStatus=="SUCCESS") {
						model.addAttribute("updateSuccessStatus","Ticketing-SLA Deleted Successfully.");
					}else{
						model.addAttribute("errorMessage","Failed To Delete Ticketing-SLA.");			
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in deleteAccessTypeContactMapping() method in AdministratorController: ",e);
		}
		return viewSLAForTicketing(request,response,model);
		
	}
	
	@RequestMapping(value = "/archiveRecord", method = RequestMethod.GET)
	public ModelAndView archiveRecord(HttpServletRequest request, HttpServletResponse response, ModelMap model) {		
		return new ModelAndView("administrator/archiveRecord");
	}
	
	@RequestMapping(value = "/createArchiving", method = RequestMethod.POST)
	public ModelAndView archiveRecordPost(HttpServletRequest request, HttpServletResponse response,
											ModelMap model,
											@RequestParam("academicYear")String academicYear,
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("archiveRecordPost() In AdministratorController.java: ");		
		try{			
			if(null!=academicYear){				
				String status=administratorService.createArchiving(academicYear,sessionObject.getUserId());
				model.addAttribute("status", status);
			}			
		}catch(Exception e){
			logger.error("Exception in archiveRecordPost() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/archiveRecord");
	}
	
	
	@RequestMapping(value = "/purgeRecord", method = RequestMethod.GET)
	public ModelAndView purgeRecord(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
			try{
		}catch(Exception e){
			logger.error("Exception in purgeRecord() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/purgeRecord");
	}
	
	@RequestMapping(value = "/purgeRecordPost", method = RequestMethod.GET)
	public ModelAndView purgeRecordPost(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										@RequestParam("academicYear")String academicYear,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {			try{
				String status=administratorService.purgeRecordPost(academicYear, sessionObject.getUserId());
				model.addAttribute("status", status);
		}catch(Exception e){
			logger.error("Exception in createRoles() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/purgeRecord");
	}

	
	
	@RequestMapping(value = "/readArchivedData", method = RequestMethod.GET)
	public ModelAndView readArchivedData(HttpServletRequest request, HttpServletResponse response, ModelMap model){
			return new ModelAndView("administrator/readArchivedData");	
	}
	
	@RequestMapping(value = "/readArchivedDataPost", method = RequestMethod.POST)
	public String readArchivedDataPost(HttpServletRequest request, HttpServletResponse response, ModelMap model,
		@RequestParam("academicYear")String academicYear,
		@RequestParam("resourceType")String resourceType,
		@RequestParam("id")String id) {		
	
		String strview = null;
		String message = null;
	try{
		String path = "C:"+File.separator+"Backup Data"+File.separator+academicYear+File.separator+resourceType;
		String completePath= path+File.separator+id+".xml";
		File file = new File(completePath);			
		if(resourceType.equalsIgnoreCase("Student")){
			StudentForXml student=administratorService.readArchivedDataForStudent(file);
			model.addAttribute("student", student);
		}else{
			StaffForXml staff=administratorService.readArchivedDataForStaff(file);
			model.addAttribute("staff", staff);	
		}
	}catch(Exception e){
		logger.error("Exception in createRoles() method in AdministratorController: ", e);
		message="Data Of The Given Resource Is Not Archieved Yet";
	}finally{
		if(resourceType.equalsIgnoreCase("Student")){
			strview="administrator/studentDetails";  
			model.addAttribute("message", message);				
		  }else{
			  strview="administrator/staffDetails";  
			  model.addAttribute("message", message);				  
		  }
	}
	return strview;
	}
	
	
	
	
	/**
	 * this method get notification medium and notification level for mapping notification medium with notification level
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/setNotificationMedium", method = RequestMethod.GET)
	public String setNotificationMedium(HttpServletRequest request, HttpServletResponse response, ModelMap model)  {
		try{	
			List<LoggingAspect>	notificationMediumList = administratorService.getNotificationMediums();
			if(notificationMediumList!=null){
				model.addAttribute("notificationMediumList",notificationMediumList);
			}
		}catch(Exception e){
			logger.error("Exception in setNotificationMedium() method in AdministratorController: ", e);
		}
		return "administrator/setNotificationMedium";
	}
		
	/**
	 * this method update notification medium and notification level for mapping
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param notificationLevel
	 * @return String
	 */
	@RequestMapping(value = "/updateNotificationMediums", method = RequestMethod.POST)
	public String updateNotificationMediums(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model,
			@RequestParam("notificationLevelName") String []notificationLevel,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {	
		try{
			
			List<LoggingAspect> notificationList=new ArrayList<LoggingAspect>();
			logger.info("In updateNotificationMediums() method of AdministratorController");
			if(notificationLevel!=null && notificationLevel.length!=0){
				for(int i=0;i<notificationLevel.length;i++){
					LoggingAspect la=new LoggingAspect();
					la.setNotificationLevel(notificationLevel[i]);
					String notificationMediumArr[]=request.getParameterValues(notificationLevel[i]);
					List<NotificationMedium> notificationMediumList=new ArrayList<NotificationMedium>();
					if(notificationMediumArr!=null && notificationMediumArr.length!=0){
						for(int j=0;j<notificationMediumArr.length;j++){
							NotificationMedium nm = new NotificationMedium();
							nm.setNotificationMediumName(notificationMediumArr[j]);
							nm.setUpdatedBy(sessionObject.getUserId());
							notificationMediumList.add(nm);
						}
						la.setNotificationMediums(notificationMediumList);
					}
					notificationList.add(la);
				}
				 administratorService.updateNotificationMediums(notificationList);
			}
			/**Added by Saif 29-03-2018
			 * used to insert the details into the activity log table*/
				String description = "";
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT NOTIFICATION MEDIUM SETUP");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("SYSTEM ADMINISTRATION");
				if(notificationLevel!=null && notificationLevel.length!=0){
					for(int i=0;i<notificationLevel.length;i++){
						updateLog.setUpdatedFor("Notification Type :: " + notificationLevel[i]);
						description = description + ("Notification Type :: " + notificationLevel[i]);
						String notificationMediumArr[]=request.getParameterValues(notificationLevel[i]);
						if(notificationMediumArr!=null && notificationMediumArr.length!=0){
							for(int j=0;j<notificationMediumArr.length;j++){
								description = description + (" Notification Medium :: " + notificationMediumArr[j] + " ; ");
							}
						}
					}
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 2486 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			/***/
		}catch(Exception e){
			logger.error("updateNotificationMediums() In AdministratorController.java: Exception", e);	
		}
		return setNotificationMedium(request,response,model);
	}
		
	
	@RequestMapping(value = "/createApprovers", method = RequestMethod.GET)
	public ModelAndView createApprovers(HttpServletRequest request, HttpServletResponse response, ModelMap model)  {
		try{	
			Resource resource = administratorService.getResourceAndRolesFromDB();
			if(null!=resource){
				model.addAttribute("resourceTypeList", resource.getResourceTypeList());
			}
			
			List<Approver> approverGroupList = administratorService.getAllApproverGroups();
			model.addAttribute("pagedListHolder", approverGroupList);
			/*if(null!=approverGroupList && approverGroupList.size()!=0){				
				PagedListHolder<Approver> pagedListHolder = administratorService.getapproverGroupPagination(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", approverGroupList);
			}*/
		}catch(Exception e){
			logger.error("Exception in setNotificationMedium() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/createApprovers") ;
	}
	
	
	//Modifieed By Naimisha 22082017
	@RequestMapping(value = "/insertApprovers", method = RequestMethod.POST)
	public ModelAndView insertApprovers(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam(value = "userName") String[] userId,
		/*	@RequestParam(value = "approvalProcess") String approvalProcess,*/		
			Approver approver) {		
		try {
			if(null!=approver && null!=userId && userId.length!=0){
				List<Resource> resourceList=new ArrayList<Resource>();
				for (int count = 0; count < userId.length; count++) {
					if ( null!=userId[count]  && userId[count] != "") {
						Resource resource=new Resource();
						resource.setUserId(userId[count]);
						resource.setUpdatedBy(sessionObject.getUserId());
						resourceList.add(resource);
					}
				}
				if(resourceList.size()!=0){
					approver.setResourceList(resourceList);
					approver.setUpdatedBy(sessionObject.getUserId());
					/*if(approvalProcess.equalsIgnoreCase("SERIAL")){
						approver.setSerialApproval(true);
					}
					if(approvalProcess.equalsIgnoreCase("PARALLEL")){
						approver.setParallelApproval(true);
					}*/
					approver.setApproverGroupName(approver.getApproverGroupName().toUpperCase());
					String insertStatus = administratorService.insertApprovers(approver);
					if (null != insertStatus) {
						if (insertStatus.equals("SUCCESS")) {
							model.addAttribute("successMessage",
									"User Group Created Successfully");
						} else {
							model.addAttribute("errorMessage",
									"Failed To Create User Group");			
						}
					}
					/**Added by @author Saif.Ali
					 * Date-28-03-2018*/
					String resourceType = request.getParameter("resourceTypeName");
					String approverGroupName = request.getParameter("approverGroupName");
					String approverGroupDesc = request.getParameter("approverGroupDesc");
					String approvalProcess = request.getParameter("approvalProcess");
					String []name = request.getParameterValues("name");
					String description = "";
					
					if (null != insertStatus) {
						if (insertStatus.equalsIgnoreCase("SUCCESS")) {
								UpdateLog updateLog=new UpdateLog();
								updateLog.setUpdatedByUserId(sessionObject.getUserId());
								updateLog.setFunctionality("CREATE APPROVER GROUP");
								updateLog.setInsertUpdate("INSERT");
								updateLog.setModule("SYSTEM ADMINISTRATION");
								updateLog.setUpdatedFor("Resource Type" + resourceType + " Approver Group Name :: " + approverGroupName + " Approver Group Desc :: " + approverGroupDesc + " ; ");
								description = description + ("Resource Type" + resourceType);
								for (int count = 0; count < userId.length; count++) {
									if ( null!=userId[count]  && userId[count] != "") {
										description= description + (" User Id ::" + userId[count] + " Name :: " + name[count]);
									}
								}
								updateLog.setDescription(description);
								String response_update=commonService.insertIntoActivityLog(updateLog);
								
								System.out.println("LN 3178 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
										":: Functonality ::"+ updateLog.getFunctionality() + 
										":: Module ::"+updateLog.getModule() + 
										":: Updated For ::"+ updateLog.getUpdatedFor() +
										":: Description :: "+updateLog.getDescription() +
										":: Response :: " + response_update +
										":: Insert/Update :: " + updateLog.getInsertUpdate());
						}
					}
					/****/
					
				}
			}
		} catch (Exception e) {
			logger.error("Exception in updateAccessTypeRoleMapping() method in AdministratorController: ",e);
		}
		return createApprovers(request,response,model);
	}
	
	/*dfgydfgdfgdfgfgdfgdfgdfg*/
	
	@RequestMapping(value = "/viewApproversList", method = RequestMethod.GET)
	public ModelAndView viewApproversList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("viewApproversList() In AdministratorController.java: ");		
		try{
			List<Approver> approverGroupList = administratorService.getAllApproverGroups();
			if(null!=approverGroupList && approverGroupList.size()!=0){				
				PagedListHolder<Approver> pagedListHolder = administratorService.getapproverGroupPagination(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", pagedListHolder);
			}			
		}catch(Exception e){
			logger.error("Exception in viewAllUserGroups() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/listApproverGroup");
	}
	
	
	@RequestMapping(value = "/viewApproversListPagination", method = RequestMethod.GET)
	public ModelAndView viewApproversListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("administrator/listApproverGroup");
		try {
			logger.info("In viewApproversListPagination() method of AdministratorController");		
			PagedListHolder<Approver> pagedListHolder = administratorService.getapproverGroupPagination(request);
			if (pagedListHolder != null) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			
			logger.error("Error in AdministratorController AdministratorController() method for Exception: ", e);
		}
		return mav;
	}
	
	/*@RequestMapping(value = "/getApproverGroupDetails", method = RequestMethod.POST, params = "delete")
	public ModelAndView inactiveApproverGroupDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam("approverGroupCode") String approverGroupCode) {		
		try {
			if(null!=approverGroupCode){
				Approver approver=new Approver();
				approver.setApproverGroupCode(approverGroupCode);
				approver.setUpdatedBy(sessionObject.getUserId());			
				String status= administratorService.inactiveApproverGroupDetails(approver);
				if (null!=status) {
					if(status.equals("SUCCESS")){
						model.addAttribute("successMessage", "Approver Group Deleted Successfully");					
					}else{
						model.addAttribute("errorMessage", "Failed To Delete Approver Group");										
					}				
				}
			}else{
				model.addAttribute("errorMessage", "Failed To Delete Approver Group");										
			}
		} catch (NullPointerException e) {
			logger.error("inactiveApproverGroupDetails() In AdministratorController.java: NullPointerException",e);
		} catch (Exception e) {
			logger.error("inactiveApproverGroupDetails() In AdministratorController.java: Exception",e);
		}
		return viewApproversList(request,response,model);
	}
	*/
	
	@RequestMapping(value = "/inactiveApproverGroupDetails", method = RequestMethod.GET)
	public ModelAndView inactiveApproverGroupDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam("approverGroupCode") String approverGroupCode) {		
		try {
			if(null!=approverGroupCode){
				Approver approver=new Approver();
				approver.setApproverGroupCode(approverGroupCode);
				approver.setUpdatedBy(sessionObject.getUserId());			
				String status= administratorService.inactiveApproverGroupDetails(approver);
				if (null!=status) {
					if(status.equals("SUCCESS")){
						model.addAttribute("successMessage", "Approver Group Deleted Successfully");					
					}else{
						model.addAttribute("errorMessage", "Failed To Delete Approver Group");										
					}				
				}
			}else{
				model.addAttribute("errorMessage", "Failed To Delete Approver Group");										
			}
		} catch (NullPointerException e) {
			logger.error("inactiveApproverGroupDetails() In AdministratorController.java: NullPointerException",e);
		} catch (Exception e) {
			logger.error("inactiveApproverGroupDetails() In AdministratorController.java: Exception",e);
		}
		return createApprovers(request,response,model);
	}
	
	/*@RequestMapping(value = "/getApproverGroupDetails", method = RequestMethod.POST, params = "details")
	public ModelAndView getApproverGroupDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam("approverGroupCode") String approverGroupCode) {		
		String view="null";
		try {
			if(null!=approverGroupCode){
				Approver approver=new Approver();
				approver.setApproverGroupCode(approverGroupCode);
				approver= administratorService.getApproverGroupDetails(approver);				
				if (null!=approver.getResourceList() && approver.getResourceList().size()!=0) {					
					model.addAttribute("approver", approver);
					view="administrator/approverGroupDetails";
				}else{
					model.addAttribute("errorMessage", "Failed To Get Approver Details");	
					view="administrator/listApproverGroup";
				}
			}else{
				model.addAttribute("errorMessage", "Failed To Get Approver Group Details");
				view="administrator/listApproverGroup";
			}
		} catch (NullPointerException e) {
			logger.error("getApproverGroupDetails() In AdministratorController.java: NullPointerException",e);
		} catch (Exception e) {
			logger.error("getApproverGroupDetails() In AdministratorController.java: Exception",e);
		}
		return new ModelAndView(view);
	}*/
	
	@RequestMapping(value = "/getApproverGroupDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getApproverGroupDetails(@RequestParam("approverGroupCode") String approverGroupCode) {
		String approverGroupDetails = null;
		try {
			
			logger.info("getApproverGroupDetails() In CommonController.java");
			//userName = commonService.getUserNameForId(userId);
			Approver approver=new Approver();
			if(null!=approverGroupCode){
				
				approver.setApproverGroupCode(approverGroupCode);
				approver= administratorService.getApproverGroupDetails(approver);
			}
			String approverGroupName = approver.getApproverGroupName();
			//System.out.println("usergroupname =="+usergroupname);
			approverGroupDetails = approverGroupName + "#";
			List<Resource> resourceList = approver.getResourceList();
			int i = 1;
			for(Resource resource : resourceList){
				
				String name = resource.getName();
				String id = resource.getUserId();
				approverGroupDetails = approverGroupDetails+name+"-"+id;
				if(i < resourceList.size()){
					approverGroupDetails = approverGroupDetails + "*";
					i++;
				}
				
				
			}
		} catch (NullPointerException e) {
			logger.error("getUserNameForId() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getUserNameForId() In CommonController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(approverGroupDetails));
	}
	@RequestMapping(value = "/getApproverGroupDetails", method = RequestMethod.POST, params = "details")
	public ModelAndView getApproverGroupDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam("approverGroupCode") String approverGroupCode) {		
		String view="null";
		try {
			if(null!=approverGroupCode){
				Approver approver=new Approver();
				approver.setApproverGroupCode(approverGroupCode);
				approver= administratorService.getApproverGroupDetails(approver);				
				if (null!=approver.getResourceList() && approver.getResourceList().size()!=0) {					
					model.addAttribute("approver", approver);
					view="administrator/approverGroupDetails";
				}else{
					model.addAttribute("errorMessage", "Failed To Get Approver Details");	
					view="administrator/listApproverGroup";
				}
			}else{
				model.addAttribute("errorMessage", "Failed To Get Approver Group Details");
				view="administrator/listApproverGroup";
			}
		} catch (NullPointerException e) {
			logger.error("getApproverGroupDetails() In AdministratorController.java: NullPointerException",e);
		} catch (Exception e) {
			logger.error("getApproverGroupDetails() In AdministratorController.java: Exception",e);
		}
		return new ModelAndView(view);
	}
	
	@RequestMapping(value = "/getApproverGroupDetails", method = RequestMethod.POST, params = "back")
	public ModelAndView backApproverGroupDetails(HttpServletRequest request,HttpServletResponse response, ModelMap model) {		
		try {
			
		}  catch (Exception e) {
			logger.error("backUserGroupDetails() In AdministratorController.java: Exception",e);
		}
		return viewApproversList(request,response,model);
	}
	
	@RequestMapping(value = "/createAndUpdateUserPassword", method = RequestMethod.GET)
	public String createAndUpdateUserPassword(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		try{
			Resource resource = administratorService.getResourceAndRolesFromDB();
			if(null!=resource){
				model.addAttribute("resourceTypeList", resource.getResourceTypeList());
			}
		}catch(Exception e){
			logger.error("createAndUpdateUserPassword() In AdministratorController.java: Exception",e);e.printStackTrace();
		}
	return "administrator/createAndUpdateUserPassword";
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
	@RequestMapping(value = "/createAndUpdateUserPassword", method = RequestMethod.POST)
	public String updatePasswordByAdmin(HttpServletRequest request,HttpServletResponse response,
									   ModelMap model,LoginForm login,
									   @RequestParam(value = "change", required = false) String change,
									   @RequestParam(value = "submit", required = false) String submit,
									   @RequestParam(value = "delete", required = false) String delete,
									   @ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			String status="fail";
			/**Added by Saif 29-03-2018*/
			String resourceType = request.getParameter("resourceType");
			String userId = request.getParameter("userId");
			String name = request.getParameter("name");
			String newPassword = request.getParameter("newPassword");
			String description = "";
			/***/
			login.setUpdatedBy(sessionObject.getUserId());
			if(null!=change){
				login.setStatus("ByAdmin");
				status = administratorService.updatePassword(login);
				model.addAttribute("message", status);
				/**Added by Saif 29-03-2018*/
				if(status.equalsIgnoreCase("success"))
				{
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("CREATE/MANAGE USER PASSWORD");
						updateLog.setInsertUpdate("UPDATE");
						updateLog.setModule("SYSTEM ADMINISTRATION");
						updateLog.setUpdatedFor("Resource Type :: " + resourceType + " of User Id :: " + userId + " of Name :: " + name);
						description = description + ("A new Password :: "+ newPassword + " for Resource Type :: " + resourceType + " of User Id :: " + userId + " of Name :: " + name + " is generated" + " ; ");
						updateLog.setDescription(description);
						String response_update=commonService.insertIntoActivityLog(updateLog);
						System.out.println("LN 2904 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
								":: Functonality ::"+ updateLog.getFunctionality() + 
								":: Module ::"+updateLog.getModule() + 
								":: Updated For ::"+ updateLog.getUpdatedFor() +
								":: Description :: "+updateLog.getDescription() +
								":: Response :: " + response_update +
								":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
			}
			if(null!=submit){
				status=administratorService.createPassword(login);
				model.addAttribute("message", status);
				/**Added by Saif 29-03-2018*/
				if(status.equalsIgnoreCase("success"))
				{
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("CREATE/MANAGE USER PASSWORD");
						updateLog.setInsertUpdate("UPDATE");
						updateLog.setModule("SYSTEM ADMINISTRATION");
						updateLog.setUpdatedFor("Resource Type :: " + resourceType + " of User Id :: " + userId + " of Name :: " + name);
						description = description + ("A new Password :: "+ newPassword + " for Resource Type :: " + resourceType + " of User Id :: " + userId + " of Name :: " + name + " is generated" + " ; ");
						updateLog.setDescription(description);
						String response_update=commonService.insertIntoActivityLog(updateLog);
						System.out.println("LN 2929 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
								":: Functonality ::"+ updateLog.getFunctionality() + 
								":: Module ::"+updateLog.getModule() + 
								":: Updated For ::"+ updateLog.getUpdatedFor() +
								":: Description :: "+updateLog.getDescription() +
								":: Response :: " + response_update +
								":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
			}
			if(null!=delete){
				status=administratorService.deletePassword(login);
				model.addAttribute("message", status);
				/**Added by Saif 29-03-2018*/
				if(status.equalsIgnoreCase("success"))
				{
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("CREATE/MANAGE USER PASSWORD");
						updateLog.setInsertUpdate("UPDATE");
						updateLog.setModule("SYSTEM ADMINISTRATION");
						updateLog.setUpdatedFor("Resource Type :: " + resourceType + " of User Id :: " + userId + " of Name :: " + name);
						description = description + ("Password is deleted for Resource Type :: " + resourceType + " of User Id :: " + userId + " of Name :: " + name + " is generated" + " ; ");
						updateLog.setDescription(description);
						String response_update=commonService.insertIntoActivityLog(updateLog);
						System.out.println("LN 2954 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
								":: Functonality ::"+ updateLog.getFunctionality() + 
								":: Module ::"+updateLog.getModule() + 
								":: Updated For ::"+ updateLog.getUpdatedFor() +
								":: Description :: "+updateLog.getDescription() +
								":: Response :: " + response_update +
								":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
			}
		}catch(Exception e){
			logger.error("updatePasswordByAdmin() In AdministratorController.java: Exception",e);e.printStackTrace();
		}
	return createAndUpdateUserPassword(request,response,model);
	}
	
	@RequestMapping(value = "/getUserNameAndStatus", method = RequestMethod.GET)
	public @ResponseBody
	String getUserNameAndStatus(@RequestParam("resourceId") String resourceId) {
		String userNameStatus = "";
		try{  			
			String name = commonService.getUserNameForId(resourceId);
			if(name!=null){
				String userStatus = commonService.getUserLdapStatus(resourceId);
				userNameStatus=name+"*"+userStatus;
			}
		}catch(Exception e){
			logger.error("getUserNameANdStatus() in AdministrationControllrt", e);
		}
		return (new Gson().toJson(userNameStatus));
	}
	


	@RequestMapping(value = "/createNewTask", method = RequestMethod.GET)
	public ModelAndView createNewJob(HttpServletRequest request,
											HttpServletResponse response, ModelMap model, JobType jobType,
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		List<JobType> jobDetailsList = new ArrayList<JobType>();
		List<Approver> approversList = new ArrayList<Approver>(); 
		try {
			logger.info("In createNewJob() method of TicketController");
			strView = "administrator/createNewJob";	
			jobDetailsList = administratorService.getAllJobDetails();
			
			List<Department> departmentList = erpService.getAllDepartment();	//Added by naimisha 10042018
			model.addAttribute("departmentList", departmentList);
			
			List<Functionality>functionality = administratorService.getAllFunctionalityList();
			model.addAttribute("functionality", functionality);
			
		}catch (Exception e) {
			logger.error("createNewJob() In TicketController.java: Exception", e);
		}
		model.addAttribute("jobDetailsList", jobDetailsList);
		
		
		return new ModelAndView(strView);
	}
	
	
	
	//Modified By Naimisha 161020172017
	@RequestMapping(value = "/createNewTask", method = RequestMethod.POST)
	public ModelAndView createJob(HttpServletRequest request,
											HttpServletResponse response, ModelMap model, JobType jobType,
											@ModelAttribute("sessionObject") SessionObject sessionObject,
											@RequestParam(required = false, value = "approvalProcess") String approvalProcess,
											@RequestParam(required = false, value = "isFinance") String isFinance,
											@RequestParam(required = false, value = "isLinked") String isLinked,
											@RequestParam(value = "approvalRequired") String approvalRequired,
											@RequestParam(required = false, value = "action") String action) {
		String strView = null;
		try {
			logger.info("In createJob() method of TicketController");
			strView = "administrator/createNewJob";	
			jobType.setUpdatedBy(sessionObject.getUserId());
			jobType.setApprovalRequired(approvalRequired);
			
			if(null != isLinked){
				if(isLinked.equalsIgnoreCase("linked")){
					jobType.setLinked(true);
				}else{
					jobType.setLinked(false);
				}
			}
			if(null != isFinance){
				if(isFinance.equalsIgnoreCase("finance")){
					jobType.setFinance(true);
				}else{
					jobType.setFinance(false);
				}
			}
			/*if(null != approvalProcess){
				if(approvalProcess.equalsIgnoreCase("SERIAL")){
					jobType.setSerialApproval(true);
				}
				if(approvalProcess.equalsIgnoreCase("PARALLEL")){
					jobType.setParallelApproval(true);
				}
			}
			*/
		
				
			
		jobType.setAction(action);
		int status = administratorService.saveJobDetails(jobType);
			
		
		if(status==1){	
			model.addAttribute("insertStatus", "success");
			model.addAttribute("msg", "Successfully Created");
		}else{
			model.addAttribute("insertStatus", "fail");
			model.addAttribute("msg", "Failed To Create");
			logger.info("createNewJob() In AdministratorController.java: Error In Creating Roles");
		}				
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("createNewJob() In TicketController.java: Exception", e);
		}
		return createNewJob(request, response, model,jobType, sessionObject);
	}
	
	
	@RequestMapping(value = "/createApprovalOrder", method = RequestMethod.GET)
	public ModelAndView createApprovalOrder(HttpServletRequest request,
											HttpServletResponse response, ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		List<JobType> jobDetailsList = new ArrayList<JobType>();
		List<Approver> approverGroupList = new ArrayList<Approver>();
		List <Approver> approvalOrderList = new ArrayList<Approver>();
		try {
			logger.info("In createNewJob() method of TicketController");
			strView = "administrator/createApprovalOrder";	
			jobDetailsList = administratorService.getAllJobDetails();
			approverGroupList = administratorService.getAllApproverGroups();
			approvalOrderList = administratorService.getAllApprovalOrderList();	
			
					
		}catch (Exception e) {
			logger.error("createNewJob() In TicketController.java: Exception", e);
		}
		model.addAttribute("jobDetailsList", jobDetailsList);
		model.addAttribute("approverGroupList", approverGroupList);
		model.addAttribute("approvalOrderList", approvalOrderList);
		
		return new ModelAndView(strView);
	}
	

	//Modified BY Naimisha 25082017
	@RequestMapping(value = "/insertApprovalOrder", method = RequestMethod.POST)
	public ModelAndView insertApprovalOrder(HttpServletRequest request,
											HttpServletResponse response, ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject,
											Approver approver) {
		String strView = null;
		//List<JobType> jobDetailsList = new ArrayList<JobType>();
		List<Approver> approverGroupList = new ArrayList<Approver>();
		try {
			logger.info("In createNewJob() method of TicketController");
			strView = "administrator/createApprovalOrder";	
			//jobDetailsList = ticketService.getAllJobDetails();
			//approverGroupList = administratorService.getAllApproverGroups();
			//String levelArray = request.getParameter("level");?
			String[] levelArray = request.getParameterValues("level");
			String[] approverGroup =  request.getParameterValues("approverGroupCode");
			approver.setUpdatedBy(sessionObject.getUserId());
			if(null != levelArray && null != approverGroup){
				for(int i=0; i< levelArray.length; i++){
					Approver approverObj = new Approver();
					approverObj.setApproverGroupCode(approverGroup[i]);
					approverObj.setApproverGroupName(levelArray[i]);
					approverObj.setApproverGroupDesc(approver.getApproverGroupDesc());
					approverObj.setUpdatedBy(sessionObject.getUserId());
					approverGroupList.add(approverObj);
				}
			}
			String insertStatus = administratorService.insertApprovalOrder(approverGroupList);
			model.addAttribute("msg", insertStatus);
			if (null != insertStatus) {
				if (insertStatus.equals("success")) {
					model.addAttribute("successMessage", "Successfully Created");
				} else {
					model.addAttribute("errorMessage", "Failed To Create");			
				}
			}	
					
		}catch (Exception e) {
			logger.error("createNewJob() In TicketController.java: Exception", e);
		}
		//model.addAttribute("jobDetailsList", jobDetailsList);
		//model.addAttribute("approverGroupList", approverGroupList);
		
		return  createApprovalOrder(request,response, model, sessionObject);
	}
	
	
//-------------------FOR TASK DELEGATION AND NOTIFICATION----------------------
	//***************changes on 30032017********************//
	/******changes By Naimisha 04042017********/
	
	@RequestMapping(value = "/allocateTask", method = RequestMethod.GET)
	public ModelAndView allocateTask(HttpServletRequest request, HttpServletResponse response, ModelMap model,@ModelAttribute("sessionObject") SessionObject sessionObject)  {
		try{	
			Resource resource = administratorService.getResourceAndRolesFromDB();
			List<ResourceType>resourceTypeList = resource.getResourceTypeList();
			if(null!=resource){
				model.addAttribute("resourceTypeList",resourceTypeList);
			}
			List<JobType> jobDetailsList = new ArrayList<JobType>();
			jobDetailsList = administratorService.getAllJobDetails();
			String userId = sessionObject.getUserId();
			List<Approver>approverGroupList  = administratorService.getApproversListAgainstUserId(userId);
			//System.out.println("approverGroupList size ======="+approverGroupList.size());
		//	List<String>taskNameList = administratorService.getAllTaskNAmeList();
			String userName = sessionObject.getUserId();
			List<TaskDetails>allTaskDetailsList = administratorService.getAllTaskDetailsList(userName);
			model.addAttribute("taskNameList", jobDetailsList);
			model.addAttribute("allTaskDetailsList", allTaskDetailsList);
			model.addAttribute("approverGroupList", approverGroupList);
		}catch(Exception e){
			logger.error("Exception in setNotificationMedium() method in AdministratorController: ", e);
		}
		return new ModelAndView("administrator/taskAllocation") ;
	}
	
	@RequestMapping(value = "/getResourceForResourceType", method = RequestMethod.GET)
	public @ResponseBody
	String getResourceForResourceType(@RequestParam("resourceType") String resourceTypeName) {
		String listOfResource="";
		try{
			logger.info("getResourceForResourceType() In AdministratorController.java");
			List <Resource> resourceList = administratorService.getResourceDetails(resourceTypeName);
			if(null!=resourceList && resourceList.size()!=0){
				for(Resource  resource: resourceList){
					String resourceName = null;
					if(resource.getMiddleName() == null){
						 resourceName = resource.getFirstName()+" "+resource.getLastName();
					}else{
						resourceName = resource.getFirstName()+" "+resource.getMiddleName()+" "+resource.getLastName();
					}
					
					listOfResource = listOfResource+resource.getUserId()+"*"+resourceName+"#";
				}
			}
		}catch(Exception e){			
			logger.error("getResourceForResourceType() In AdministratorController.java: Exception", e);	
		}
		return (new Gson().toJson(listOfResource));
	}
	/******changes By Naimisha 04042017********/
	
	@RequestMapping(value = "/allocateTask", method = RequestMethod.POST)
	public ModelAndView insertallocateTask(HttpServletRequest request,
											HttpServletResponse response, ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject
											//Resource resource,
											) {
		String strView = null;
		String insertStatus  = null;
		try {
			logger.info("In insertallocateTask() method of Administrator Controller");
			strView = "administrator/taskAllocation";
			Resource resource = new Resource();
			List<ResourceType> resourceTypeList = new ArrayList<ResourceType>();
			resource.setUpdatedBy(sessionObject.getUserId());
		
			int taskIndex = Integer.parseInt(request.getParameter("taskIndex"));
			int resourceIndex = Integer.parseInt(request.getParameter("resourceIndex"));
			//System.out.println("taskIndex====="+taskIndex);
			//System.out.println("resourceIndex======"+resourceIndex);
			String jobCode = request.getParameter("jobCode"+taskIndex);
			String jobName = request.getParameter("jobName"+taskIndex);
			String status = request.getParameter("status"+taskIndex);
			String ticketCode =  request.getParameter("ticketId"+taskIndex);
			JobType jobType = new JobType();
			jobType.setJobTypeCode(jobCode);
			jobType.setJobTypeName(jobName);
			jobType.setStatus(status);
			resource.setJobType(jobType);
			//System.out.println("jobCode====="+jobCode);
			for(int i=0;i<=resourceIndex;i++){
				ResourceType resourceType = new ResourceType();
				String userId = request.getParameter("userName"+i);
				String userName = request.getParameter("name"+i);
				String desc = request.getParameter("desc"+i);
				resourceType.setResourceTypeCode(userId);
				resourceType.setResourceTypeName(userName);
				//System.out.println("userId=="+userId);
				//System.out.println("userName=="+userName);
				resourceType.setResourceTypeDesc(desc);
				resourceTypeList.add(resourceType);
			}
			resource.setResourceTypeList(resourceTypeList);
			resource.setRegistrationId(ticketCode);
			insertStatus = administratorService.insertIntoTaskDetails(resource);
			//System.out.println("insertStatus======"+insertStatus);
			model.addAttribute("insertStatus", insertStatus);
			if (null != insertStatus) {
				if (insertStatus.equalsIgnoreCase("success")) {
					model.addAttribute("successMessage", "Task Allocated  SuccessFully");
				} else {
					model.addAttribute("errorMessage", "Failed To Allocate Task");			
				}
			}	
					
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("insertallocateTask() In AdministratorController.java: Exception", e);
		}
		return allocateTask(request,response,model,sessionObject) ;
	}
	
	
	/**
	 * DONE BY NAIMISHA
	 * CHANGES TAKEN ON 11 JAN 2017
	 * **/
	
/*****************Done By Naimisha 23112016*********************/
	
	@RequestMapping(value="/inactiveSubjectGroup",method=RequestMethod.GET)
	public String inactiveSubjectGroup(HttpServletRequest request, HttpServletResponse response,ModelMap model){		
		String strView="administrator/inactiveSubjectGroup";
		try{			
			List<SubjectGroup> subjectGroupList = academicsService.getSubjectGroup();
			if(subjectGroupList!=null && subjectGroupList.size()!=0){
				model.addAttribute("subjectGroupList",subjectGroupList);
			}
		}catch(NullPointerException | CustomException e){
			e.printStackTrace();
		}
		return strView;
	}
	
	/* Write Inactive Method*/
	@RequestMapping(value="/setInactiveSubjectGroup",method=RequestMethod.GET)
	public String setInactiveSubjectGroup(HttpServletRequest request, HttpServletResponse response,
											ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject){		
		//System.out.println("before jsp");
		String mav = "administrator/inactiveSubject";
		List<SubjectGroup> SubjectGroupList=new ArrayList<SubjectGroup>();
		//System.out.println("within");
		String subjectGroupCode  = request.getParameter("subjectGroupCode");
		//System.out.println("subjectGroupCode=="+subjectGroupCode);
		try{
			
			if(subjectGroupCode!=null ){	
					SubjectGroup subjectGroup=new SubjectGroup();
					subjectGroup.setUpdatedBy(sessionObject.getUserId());
					subjectGroup.setSubjectGroupCode(subjectGroupCode);
					SubjectGroupList.add(subjectGroup);
				}
				if(SubjectGroupList.size()!=0){
					String status=administratorService.setInactiveSubjectGroup(SubjectGroupList);
					if(status.equalsIgnoreCase("false")){
						model.addAttribute("updateFailStatus", "Subject Group Update Failed");
						mav="administrator/inactiveSubjectGroup";
					}else{
						model.addAttribute("updateSuccessStatus", "Subject Group Updated Successfully");
						return inactiveSubjectGroup( request,response,model );
					}
				}
			
		}catch(NullPointerException e){	
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/inactiveSubject", method=RequestMethod.GET)
	public String inactiveSubject(HttpServletRequest request, HttpServletResponse response,ModelMap model){
	List<Subject> subjectList = null;			
		try{
			subjectList= commonService.getSubject();	
			if(subjectList != null){
				model.addAttribute("subjectList", subjectList);
			}	
		}catch(Exception e){
			e.printStackTrace();
		}				
		return "administrator/inactiveSubject";
	}
	
	
	/* Write Inactive Method*/
	@RequestMapping(value="/setInactiveSubject",method=RequestMethod.GET)
	public String setInactiveSubject(HttpServletRequest request, HttpServletResponse response,ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject){		
		String mav = "administrator/inactiveSubject";
		List<Subject> subjectList=new ArrayList<Subject>();
		//System.out.println("within");
		String subjectCode  = request.getParameter("subjectCode ");
		//System.out.println("subjectCode ============"+subjectCode );
		try{			
			if(subjectCode!=null){	
				//for(int i=0;i<serialIndex.length;i++){
					Subject subject=new Subject();
					subject.setSubjectCode(subjectCode);
					subject.setUpdatedBy(sessionObject.getUserId());
					subjectList.add(subject);
				//}
				if(subjectList.size()!=0){
					String status=administratorService.setInactiveSubject(subjectList);
					if(status.equalsIgnoreCase("false")){
						model.addAttribute("updateFailStatus", "Subject Update Failed");
						mav="administrator/inactiveSubject";
					}else{
						/**Added by Saif 29-03-2018*/
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("IN ACTIVE COURSE");
						updateLog.setInsertUpdate("UPDATE");
						updateLog.setModule("SYSTEM ADMINISTRATION");
						updateLog.setUpdatedFor("Subject Code :: " + subjectCode);
						updateLog.setDescription("Subject Code :: " + subjectCode + " is now In Active . ");
						String response_update=commonService.insertIntoActivityLog(updateLog);
						System.out.println("LN 3352 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
								":: Functonality ::"+ updateLog.getFunctionality() + 
								":: Module ::"+updateLog.getModule() + 
								":: Updated For ::"+ updateLog.getUpdatedFor() +
								":: Description :: "+updateLog.getDescription() +
								":: Response :: " + response_update +
								":: Insert/Update :: " + updateLog.getInsertUpdate());
						/***/
						model.addAttribute("updateSuccessStatus", "Subject Updated Successfully");
						return inactiveSubject( request,  response,model );
					}
				}
			}else{
				model.addAttribute("updateFailStatus", "Subject Code Not Found");
				mav="administrator/inactiveSubject";
			}
		}catch(Exception e){	
			e.printStackTrace();
		}
		return mav;
	}	
	
	@RequestMapping(value = "/inactiveStaff", method = RequestMethod.GET)
	public ModelAndView inactiveStaff(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Map<String, Object> parameters) {		
		ModelAndView mav = new ModelAndView("administrator/inactiveStaff");
		try {			
			parameters.put("ResourceType", request.getParameter("resourceType"));
			model.addAttribute("ResourceType", request.getParameter("resourceType"));
			List<Employee> staffDetailsListFromDB = erpService.getStaffList(parameters);
			if (staffDetailsListFromDB != null && staffDetailsListFromDB.size() != 0) {
				model.addAttribute("staffDetailsListFromDB", staffDetailsListFromDB);
			}
		} catch (NullPointerException e) {
			
		} catch (Exception e) {
			
		}
		return mav;
	}
	
	/* Write Inactive Method*/
	@RequestMapping(value="/inactiveStaffDetails",method=RequestMethod.GET)
	public ModelAndView inactiveStaffDetailsInDB(HttpServletRequest request, HttpServletResponse response,
												@ModelAttribute("sessionObject") SessionObject sessionObject,
												Map<String, Object> parameters,ModelMap model){		
		String mav ="administrator/inactiveStaff";
		List<Resource> resourceList=new ArrayList<Resource>();
		try{	
			String userId  = request.getParameter("userId");
			//System.out.println("userId====="+userId);
			if(userId!=null ){	
				//for(int i=0;i<userId.length;i++){
					Resource resource=new Resource();
					resource.setUpdatedBy(sessionObject.getUserId());
					resource.setUserId(userId);
					resourceList.add(resource);
				//}
				if(resourceList.size()!=0){
					String status = administratorService.setInactiveStaff(resourceList);
					if(status.equalsIgnoreCase("false")){
						model.addAttribute("updateFailStatus", "Employee Update Failed");
						mav="administrator/inactiveStaff";
					}else{
						/**Added by Saif 29-03-2018*/
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("IN ACTIVE STAFF");
						updateLog.setInsertUpdate("UPDATE");
						updateLog.setModule("SYSTEM ADMINISTRATION");
						updateLog.setUpdatedFor("User Id :: " + userId);
						updateLog.setDescription("User Id :: " + userId + " is now In Active . ");
						String response_update=commonService.insertIntoActivityLog(updateLog);
						System.out.println("LN 3409 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
								":: Functonality ::"+ updateLog.getFunctionality() + 
								":: Module ::"+updateLog.getModule() + 
								":: Updated For ::"+ updateLog.getUpdatedFor() +
								":: Description :: "+updateLog.getDescription() +
								":: Response :: " + response_update +
								":: Insert/Update :: " + updateLog.getInsertUpdate());
						/***/
						model.addAttribute("updateSuccessStatus", "Employee Updated Successfully");
						return inactiveStaff( request,  response, model,parameters);
					}
				}
			}else{
				model.addAttribute("updateFailStatus", "Staff Code Not Found");
				mav="administrator/inactiveStaff";
			}
		}catch(Exception e){	
			e.printStackTrace();
		}
		return new ModelAndView(mav);
	}
	
	@RequestMapping(value="/inactiveStudent",method=RequestMethod.GET)
	public ModelAndView inactiveStudent(HttpServletRequest request, HttpServletResponse response,ModelMap model){		
		ModelAndView mav = new ModelAndView("administrator/inactiveStudent");
		try{			
			List<Student> studentDetailsListFromDB = backOfficeService.getStudentList();
			if (studentDetailsListFromDB != null
					&& studentDetailsListFromDB.size() != 0) {
				logger.info("In BackOfficeController listStudents() method: calling getStudentListPaging(HttpServletRequest request) in BackOfficeService.java");
				model.addAttribute("studentDetailsListFromDB", studentDetailsListFromDB);
			}
		}catch(NullPointerException e){
			logger.error("Exception in inactiveStudent() in AdministratorController: ", e);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	/* Write Inactive Method*/
	@RequestMapping(value="/inactiveStudentDetails",method=RequestMethod.GET)
	public ModelAndView inactiveStudentDetails(HttpServletRequest request, HttpServletResponse response,
												ModelMap model,	
												@RequestParam("studentName") String studentName,	//Modified by Saif 29-03-2018
												@RequestParam("studentContact") String studentContact,
												@RequestParam("studentEmail") String studentEmail,
												@ModelAttribute("sessionObject") SessionObject sessionObject){	
		String mav ="administrator/inactiveStudent";
		List<Resource> resourceList=new ArrayList<Resource>();
		String rollNumber = request.getParameter("rollNumber");
		try{			
			if(rollNumber!=null ){	
				//for(int i=0;i<registrationId.length;i++){
					Resource resource=new Resource();
					resource.setUpdatedBy(sessionObject.getUserId());
					resource.setRegistrationId(rollNumber);
					resourceList.add(resource);
				//}
				if(resourceList.size()!=0){
					String status = administratorService.setInactiveStudent(resourceList);
					if(status.equalsIgnoreCase("false")){
						model.addAttribute("updateFailStatus", "Student Update Failed");
						mav="administrator/inactiveStudent";
					}else{
						model.addAttribute("updateSuccessStatus", "Student Updated Successfully");
						return inactiveStudent( request,  response, model);
					}
				/**Added by @author Saif.Ali
				 * Date- 29-03-2018*/
					String description = "";
					if(status.equalsIgnoreCase("true"))
					{
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("IN ACTIVE STUDENTS");
						updateLog.setInsertUpdate("UPDATE");
						updateLog.setModule("SYSTEM ADMINISTRATION");
						updateLog.setUpdatedFor("Roll Number :: " + rollNumber + " Name :: " + studentName);
						if(rollNumber != ""){
								description = description + ("Roll Number :: " + rollNumber + " ; ");
						}
						if(studentName != ""){
								description = description + ("Student Name :: " + studentName + " ; ");
						}
						if(studentContact != ""){
								description = description + ("Student Contact :: " + studentContact + " ; ");
						}
						if(studentEmail != ""){
								description = description + ("Student Email :: " + studentEmail + " ; ");
						}
						description = description + " is Deleted ";
						updateLog.setDescription(description);
						String response_update=commonService.insertIntoActivityLog(updateLog);
							
						System.out.println("LN 3489 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
								":: Functonality ::"+ updateLog.getFunctionality() + 
								":: Module ::"+updateLog.getModule() + 
								":: Updated For ::"+ updateLog.getUpdatedFor() +
								":: Description :: "+updateLog.getDescription() +
								":: Response :: " + response_update +
								":: Insert/Update :: " + updateLog.getInsertUpdate());
					}
				/***/
				}
			}else{
				model.addAttribute("updateFailStatus", "Student Code Not Found");
				mav="administrator/inactiveStudent";
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in inactiveStudentDetails() method in AdministratorController: ", e);
		}
		return new ModelAndView(mav);
	}
	
	@RequestMapping(value = "/inactiveCourse", method = RequestMethod.GET)
	public ModelAndView inactiveCourse(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ModelAndView mav = null;
		try{
			mav = new ModelAndView("administrator/inactiveCourse");	
			String syllabusBasicRootPath = rootPath+courseSyllabusPath;
			List<AdmissionForm> courseList= academicsService.getAllCourseList();
			if(courseList != null && courseList.size() != 0){
			model.addAttribute("courseList", courseList);
				
			}
		}catch(Exception e){
			logger.error("Exception in inactiveCourse() method in AdministratorController: ", e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/setCourseInactive", method = RequestMethod.GET)
	public ModelAndView setCourseInactive(HttpServletRequest request, HttpServletResponse response
										, ModelMap model,@RequestParam("courseCode")String courseCode,
										@RequestParam("oldStandard")String oldStandard,
										@RequestParam("oldName")String oldName,
										@RequestParam("oldType")String oldType,
										@RequestParam("oldDuaration")String oldDuaration,
										@RequestParam("oldAdmissionAvailableFromdate")String oldAdmissionAvailableFromdate,
										@RequestParam("oldAdmissionAvailableTodate")String oldAdmissionAvailableTodate,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String mav = null;
		List<Course> courseList=new ArrayList<Course>();
		//System.out.println("courseCode=="+courseCode); //Naimisha
		try{
			if(courseCode!=null){
				//for(int i = 0; i<courseCode.length;i++){
					Course course=new Course();
					course.setUpdatedBy(sessionObject.getUserId());
					course.setCourseCode(courseCode);
					courseList.add(course);
				//}
				if(courseList.size()!=0){
					String status=administratorService.setCourseInactive(courseList);
					if(status.equalsIgnoreCase("false")){
						model.addAttribute("updateFailStatus", "Course Update Failed");
						mav="administrator/inactiveCourse";
					}else{
						model.addAttribute("updateSuccessStatus", "Course Updated Successfully");
						return inactiveCourse( request,  response,  model);
					}
				/**Added by @author Saif.Ali
				 * Date- 29-03-2018*/
				String description = "";
				if(status.equalsIgnoreCase("true"))
				{
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("IN ACTIVE PROGRAMS");
					updateLog.setInsertUpdate("UPDATE");
					updateLog.setModule("SYSTEM ADMINISTRATION");
					updateLog.setUpdatedFor("Course Code :: " + courseCode + " standard :: " + oldStandard);
					if(courseCode != ""){
						description = description + ("Course Code :: " + courseCode + " ; ");
					}
					if(oldStandard != ""){
						description = description + ("Standard :: " + oldStandard + " ; ");
					}
					if(oldName != ""){
						description = description + ("Standard Name :: " + oldName + " ; ");
					}
					if(oldType != ""){
						description = description + ("Type :: " + oldType + " ; ");
					}
					if(oldDuaration != ""){
						description = description + ("Duration :: " + oldDuaration + " ; ");
					}
					if(oldAdmissionAvailableFromdate != ""){
						description = description + ("Admission Available From Date :: " + oldAdmissionAvailableFromdate + " ; ");
					}
					if(oldAdmissionAvailableTodate != ""){
						description = description + ("Admission Available To Date :: " + oldAdmissionAvailableTodate + " ; ");
					}
					description = description + " is Deleted ";
					updateLog.setDescription(description);
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 3550 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
				}
			}
			else{
				model.addAttribute("updateFailStatus", "Course Code Not Found");
				mav="administrator/inactiveCourse";
			}
		}catch(Exception e){
			logger.error("Exception in setCourseInactive() method in AdministratorController: ", e);
		}
		return new ModelAndView(mav);
	}
	
	/**
	 * @author anup.roy*
	 * this method is for set up root folder with respect to system*/
	
	@RequestMapping(value = "/configureRepositoryDirectory", method = RequestMethod.GET)
	public ModelAndView configureRepositoryDirectory(HttpServletRequest request,
										HttpServletResponse response, ModelMap model) {
		try {
			RepositoryStructure repoStructure = administratorService.getRespositoryStructure();
			model.addAttribute("repoStructure", repoStructure);
		} catch (Exception e) {
			logger.error("in configureRepositoryDirectory() In AdministratorController.java: Exception", e);
			e.printStackTrace();
		}
		return new ModelAndView("administrator/configureRepositoryDirectory");
	}
	
	/**
	 * @author anup.roy
	 * this method is for submit root directory to system*/
	
	/*@RequestMapping(value = "/submitRepositoryDirectory", method = RequestMethod.POST)
	public ModelAndView submitRepositoryDirectory(HttpServletRequest request,HttpServletResponse response, ModelMap model,
			RepositoryStructure repository,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			repository.setUpdatedBy(sessionObject.getUserId());
			String status = administratorService.submitRepositoryDirectory(repository);
			if(status.equalsIgnoreCase("success")){
				File file = new File(repository.getRepositoryPathName());
				if(!file.exists()){
					if(file.mkdirs()){
						//System.out.println("directory is created");
						model.addAttribute("successMessage", "Repository created successfully");
					}else{
						//System.out.println("failed to create directory");
					}
				}
			}else{
				model.addAttribute("errorMessage","Failed to create repository");	
			}

		} catch (Exception e) {
			logger.error("In submitRepositoryDirectory() In AdministratorController.java:",e);
			e.printStackTrace();
		}
		return new ModelAndView("administrator/configureRepositoryDirectory");
	}*/
	
	@RequestMapping(value = "/submitRepositoryDirectory", method = RequestMethod.POST)
	public ModelAndView submitRepositoryDirectory(HttpServletRequest request,HttpServletResponse response, ModelMap model,
			RepositoryStructure repository,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String errorMessage = null;
		String successMessage = null;
		try {
			repository.setUpdatedBy(sessionObject.getUserId());
			String repoName = repository.getRepositoryPathName();
			String newRepoName = repoName.replace("/", "\\");
			repository.setRepositoryPathName(newRepoName);
			String status = administratorService.submitRepositoryDirectory(repository);
			if(status.equalsIgnoreCase("success")){
				boolean created = new File(newRepoName).mkdirs();
				if(created){
					successMessage = "Repository created successfully.";
					model.addAttribute("successMessage", successMessage);
					/**Added by Saif 29-03-2018*/
					String oldRepositoryFullPath = request.getParameter("oldRepositoryFullPath");
					String oldOSType = request.getParameter("oldOSType");
					String repositoryPathName = request.getParameter("repositoryPathName");
					String os = request.getParameter("os");
					String description = "";
					
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("EDIT REPOSITORY DETAILS");
					updateLog.setInsertUpdate("UPDATE");
					updateLog.setModule("SYSTEM ADMINISTRATION");
					updateLog.setUpdatedFor("OS :: " + os);
					if(!(oldOSType.equalsIgnoreCase(os))){
						description= description + "OS Type ::" + oldOSType + " updated to new OS Type ::" + os + " ; " ;
					}
					if(!(oldRepositoryFullPath.equalsIgnoreCase(repositoryPathName))){
						description= description + "Repository Path :: " + oldRepositoryFullPath + " updated to new Repository Path ::" + repositoryPathName + " ; " ;
					}
					updateLog.setDescription(description);
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 3733 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
					/**Addition ends*/
					/*if(file.mkdirs()){
						String strQR = reposiName+"\\"+"QRCode";
						File fileQR = new File(strQR);
						fileQR.mkdir();
						if(fileQR.isDirectory()){
							File QrBook = new File(strQR+"\\"+"Book");
							QrBook.mkdir();
							File QrStudent = new File (strQR+"\\"+"Student");
							QrStudent.mkdir();
							File QrTeacher = new File (strQR+"\\"+"Teacher");
							QrTeacher.mkdir();
							File QrHallPass = new File (strQR+"\\"+"HallPass");
							QrHallPass.mkdir();
						}
					}else{
						System.out.println("Failed to create main repository");
					}*/
				}else{
					errorMessage = "Main repository Already exists.";
					model.addAttribute("errorMessage", errorMessage);
				}
			}else{
				errorMessage = "Failed to create repository.";
				model.addAttribute("errorMessage",errorMessage);	
			}
		} catch (Exception e) {
			logger.error("In submitRepositoryDirectory() In AdministratorController.java:",e);
			e.printStackTrace();
		}
		return configureRepositoryDirectory(request,response,model);
	}
	
	//***************changes on 29032017********************//
	
		@RequestMapping(value = "/getTaskDetailsAgainstJobTypeAndApproverGroup", method = RequestMethod.GET)
		public @ResponseBody
		String getTaskDetailsAgainstJobTypeAndApproverGroup(@RequestParam("jobType") String jobType,@RequestParam("approverGroup") String approverGroup) {
			String listOfTask ="";
			try{
				logger.info("getTaskDetailsAgainstJobTypeAndApproverGroup() In AdministratorController.java");
				TaskDetails taskDetails = new TaskDetails();
				taskDetails.setTaskType(jobType);
				taskDetails.setTaskDetailsCode(approverGroup);
				Resource resource = administratorService.getResourceAndRolesFromDB();
				List<ResourceType>resourceTypeList = resource.getResourceTypeList();
				List <TaskDetails> taskDetailsListList = administratorService.getTaskDetailsAgainstJobTypeAndApproverGroup(taskDetails);
				if(null!=taskDetailsListList && taskDetailsListList.size()!=0){
					for(TaskDetails  task: taskDetailsListList){
						listOfTask = listOfTask + task.getTaskDetailsCode()+"*"+task.getTaskDetailsName()+"*"+ task.getStartDate()+"*"+task.getEndDate()+"*"+task.getStatus()+"*"+task.getTaskDetailsDesc()+"*"+task.getTaskDetailsObjectId()+"#";
					}
				}
				listOfTask = listOfTask+"@";
				if(null!=resourceTypeList && resourceTypeList.size()!=0){
					for(ResourceType resourceType : resourceTypeList){
						listOfTask = listOfTask + resourceType.getResourceTypeCode()+"*"+resourceType.getResourceTypeName()+"#";
					}
				}
				//System.out.println("listOfTask======"+listOfTask);
			}catch(Exception e){			
				logger.error("getTaskDetailsAgainstJobTypeAndApproverGroup() In AdministratorController.java: Exception", e);	
			}
			return (new Gson().toJson(listOfTask));
		}
		
		
		@RequestMapping(value = "/getJobForApproverGroup", method = RequestMethod.GET)
		public @ResponseBody
		String getJobForApproverGroup(@RequestParam("approvarGroupCode") String approvarGroupCode) {
			String listOfJobs="";
			try{
				logger.info("getJobForApproverGroup() In AdministratorController.java");
				List<JobType>jobTypeList = administratorService.getJobListAgainstApproverGroup(approvarGroupCode);
				
				/*if(null!=resource){
					model.addAttribute("resourceTypeList",resourceTypeList);
				}*/
				//System.out.println("jobTypeList size===="+jobTypeList.size());
				if(null!=jobTypeList && jobTypeList.size()!=0){
					for(JobType  job: jobTypeList){
						listOfJobs = listOfJobs+job.getJobTypeCode()+"*"+job.getJobTypeName()+"#";
					}
					
				}
				
				//System.out.println("listOfJobs====="+listOfJobs);
			}catch(Exception e){
				e.printStackTrace();
				logger.error("getJobForApproverGroup() In AdministratorController.java: Exception", e);	
			}
			return (new Gson().toJson(listOfJobs));
		}
		
		@RequestMapping(value = "/getDetailsForATicket", method = RequestMethod.GET)
		public @ResponseBody
		String getDetailsForATicket(@RequestParam("ticketId") String ticketId) {
			String ticketDetails = null;
			try {
				logger.info("getDetailsForATicket() In AdministratorController.java");
				Task task = new Task();
				task.setTaskCode(ticketId);;
				List <Task> taskDetailsForATicketList = administratorService.getTaskDetailsForATicket(task);
				for(Task taskDetails:taskDetailsForATicketList){
					ticketDetails = ticketDetails +"#"+ taskDetails.getStartDate() + "*"+ taskDetails.getUpdatedBy()+"*"+taskDetails.getTaskComment();
				}
				//System.out.println("taskCommentDetails======"+ticketDetails);
			} catch (NullPointerException e) {
				logger.error("getDetailsForATicket() In AdministratorController.java: NullPointerException"
						+ e);
			} catch (Exception e) {
				logger.error("getDetailsForATicket() In AdministratorController.java: Exception"
						+ e);
			}
		 return (new Gson().toJson(ticketDetails));
		}
	
//Added By Naimisha 25052017


		/**
		 * Delete Role
		 */
		
		@RequestMapping(value = "/inActiveRoleContactMapping", method = RequestMethod.GET)
		public ModelAndView inActiveRoleContactMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("roleContact") String roleContact,
				@RequestParam("roleName") String roleName,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			
			try {
				Role role = new Role();
						//role.setRoleCode(roleContact);
						role.setUpdatedBy(sessionObject.getUserId());
						role.setRoleCode(roleName);
						role.setRoleName(roleContact);
						
				String status= administratorService.inActiveRoleContactMapping(role);
				
			} catch (Exception e) {
				logger.error("inActiveRoleContactMapping() In AdministratorController.java: Exception",e);
			}
			return listRoleContactMapping( request,  response,  model);
			
		}
		
		/**
		 * @author anup.roy
		 * this method is for setup profile matrix
		 * changes taken on 07062017
		 * */
		
		@RequestMapping(value = "/setProfileViewMatrix", method = RequestMethod.GET)
		public String setProfileViewMatrix(HttpServletRequest request, HttpServletResponse response, ModelMap model)  {
			try{
				List<Module> moduleList = administratorService.getAllModulesForProfilematrix();
				if(null!= moduleList && moduleList.size()!=0){
					model.addAttribute("moduleList",moduleList);
				}
			}catch(Exception e){
				logger.error("Exception in setProfileViewMatrix() method in AdministratorController: ", e);
			}
			return "administrator/setProfileViewMatrix";
		}
		
		/**
		 * @author anup.roy
		 * this method is for get roles and data combination for profile matrix
		 * */

		@RequestMapping(value = "/getRolesForProfileMatrix", method = RequestMethod.GET)
		public @ResponseBody
		String getRolesForProfileMatrix() {
			logger.info("Inside Method getRolesForProfileMatrix-GET of AcademicsController");
			String role = "";
			try {
				List<Role> roleList = administratorService.getAllRolesForProfilematrix();
				if(null!= roleList && roleList.size()!=0){
					for(Role r: roleList){
						List<Module> moduleListForSpecificRole = administratorService.getModuleListForSpecificRole(r.getRoleCode());
						String module = "";
						for(Module m : moduleListForSpecificRole){
							Module tabAndSearchForModuleAndRole = administratorService.getTabAndSearchForModuleAndRole(r.getRoleCode(),m.getModuleCode());
							module = module + m.getModuleCode()+"#"+tabAndSearchForModuleAndRole.isTabCheck()+"#"+tabAndSearchForModuleAndRole.isSearchCheck()+"*";
						}
						role = role + module + r.getRoleCode() + ":" + r.getRoleName() + "%" ;
					}
				}
			} catch (Exception ce) {
				ce.printStackTrace();
				logger.error("Exception in getRolesForProfileMatrix-GET of AcademicsController", ce);
			}
			//System.out.println(role);
			return (new Gson().toJson(role));
		}
		
		/**
		 * @author anup.roy
		 * this method saves the data for profile matrix
		 * */
		
		@RequestMapping(value="/submitProfileMatrix", method = RequestMethod.POST)
		public String submitProfileMatrix(	HttpServletRequest request, HttpServletResponse response, 
											ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject){
			try{
				List<Module>moduleList = administratorService.getAllModulesForProfilematrix();
				List<ProfileMatrix> profileMatrixList = new ArrayList<ProfileMatrix>();
				String[] roleHead = request.getParameterValues("roleHead");
				if(null!= roleHead && roleHead.length != 0){					
					System.out.println("roleHEad length:"+roleHead.length);
					for(int i= 0; i<roleHead.length; i++){
						List<Module> modlist = new ArrayList<Module>();
						for(Module mod : moduleList){
							Module module = new Module();
							module.setModuleCode(mod.getModuleCode());
							String valueOfTabCheck = request.getParameter(roleHead[i]+"##"+mod.getModuleCode());
							String valueOfSearchCheck = request.getParameter(roleHead[i]+"##"+mod.getModuleCode()+"##"+mod.getModuleCode());
							if(null != valueOfTabCheck){
								module.setTabCheck(true);
							}else{
								module.setTabCheck(false);
							}
							if(null != valueOfSearchCheck)
								module.setSearchCheck(true);
							else{
								module.setSearchCheck(false);
							}
							//System.out.println("module:"+module.getModuleCode()+"\nvalueofTabCheck:"+module.isTabCheck()+"\tvalueOfSearchCheck:"+module.isSearchCheck());
							modlist.add(module);
						}
						Role role = new Role();
						role.setRoleCode(roleHead[i]);
						role.setModuleList(modlist);
						ProfileMatrix profile = new ProfileMatrix();
						profile.setUpdatedBy(sessionObject.getUserId());
						profile.setRole(role);
						profileMatrixList.add(profile);
					}
				}
				String status = administratorService.submitProfileMatrix(profileMatrixList);
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception in submitProfileMatrix() POST of AdministratorController.java",e);
			}
			return setProfileViewMatrix(request,response,model);
		}
		
		/**@author Saif.Ali
		 * date-27/07/2017
		 * Used to show the jsp page of the configure Rest APIs*/
		@RequestMapping(value = "/configureRestAPIs", method = RequestMethod.GET)
		public ModelAndView configureRestAPIs(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {		
			try {
				/*ServerConfiguration serverConfiguration = administratorService.getConfigurationRestAPIs();
				model.addAttribute("serverConfiguration", serverConfiguration);*/
				
				model.addAttribute("URIForSendingProgrammeDetails", URIForSendingProgrammeDetails);
				model.addAttribute("URIForSendLocationDetails", URIForSendLocationDetails);
				model.addAttribute("URIForNewCandidates", URIForNewCandidates);
				model.addAttribute("URIForScrutinizedCandidates", URIForScrutinizedCandidates);
				model.addAttribute("URIForSelectedCandidates", URIForSelectedCandidates);
				model.addAttribute("URIForAdmittedCandidates", URIForAdmittedCandidates);
				model.addAttribute("URIForSendingAlumniDetails", URIForSendingAlumniDetails);
				model.addAttribute("URIForPublishNotice", URIForPublishNotice);
			} catch (Exception e) {			
				logger.error("serverConfigurationDB() In AdministratorController.java: Exception",e);
			}
			return new ModelAndView("administrator/configureRestAPIs");
		}
		
		/**@author Saif.Ali
		 * Date- 27/07/2017
		 * Used to insert the configuration Rest APIs*/
		@RequestMapping(value = "/insertConfigureRestAPIs", method = RequestMethod.POST)
		public ModelAndView insertConfigureRestAPIs(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			ServerConfiguration serverConfiguration,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {

			try {
				serverConfiguration.setUpdatedBy(sessionObject.getUserId());
				String status=administratorService.insertConfigureRestAPIs(serverConfiguration);
				if(status.equalsIgnoreCase("successMessage")){
					model.addAttribute("successMessage", "Configuration Updated Successfully");
				}else{
					model.addAttribute("errorMessage","Failed To Save Configuration");	
				}
			} catch (CustomException e) {
				logger.error("insertConfigureRestAPIs() In AdministratorController.java: NullPointerException", e);
			}
			return  new ModelAndView("administrator/configureRestAPIs");
		}
		
		/**@author Saif.Ali
		 * Date-01/08/2017
		 * Used to show the JSP of the events for the templates*/
		@RequestMapping(value = "/createEventForTemplate", method = RequestMethod.GET)
		public String createEventForTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {	
			try {
				List<EmailEventAndTemplate> emailEventTemplateList = administratorService.getEventForTemplateList();
				model.addAttribute("emailEventTemplateList",emailEventTemplateList);
			} catch (Exception e) {			
				logger.error("serverConfigurationDB() In AdministratorController.java: Exception",e);
			}
			return "administrator/createEventForTemplate";
		}
		
		/**@author Saif.Ali
		 * Used to insert the event into the database*/
		@RequestMapping(value = "/insertEventForTemplate", method = RequestMethod.POST)
		public String insertEventForTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, EmailEventAndTemplate emailEvent,@ModelAttribute("sessionObject") SessionObject sessionObject) {	
			try {
				emailEvent.setUpdatedBy(sessionObject.getUserId());
				String status = administratorService.insertEventForTemplate(emailEvent);
				model.addAttribute("status", status);	
			} catch (Exception e) {	
				e.printStackTrace();
				logger.error("serverConfigurationDB() In AdministratorController.java: Exception",e);
			}
			return createEventForTemplate(request,response,model);
		}
		
		/**@author Saif.Ali
		 * Date- 02/08/2017
		 * Used to update the event names*/
		@RequestMapping(value = "/editEmailEventTemplate", method = RequestMethod.POST)
		public String editEmailEventTemplate(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model,EmailEventAndTemplate emailEvent ,
								  @ModelAttribute("sessionObject") SessionObject sessionObject) {
			String status="";
			try {
				String serialId=request.getParameter("serialId");
				String eventName = request.getParameter("getEventName");
				emailEvent.setEventCode(request.getParameter("eventCode"+serialId));
				emailEvent.setUpdatedBy(sessionObject.getUserId());
				emailEvent.setEventName(eventName);
				status=administratorService.editEventForTemplate(emailEvent);
				model.addAttribute("status", status);
			} catch (Exception e) {
				logger.error("Exception In editEmailEventTemplate() method of AdministratorController.java: Exception",e);
				e.printStackTrace();
			}finally{
				logger.info(status);
			}
			return createEventForTemplate(request,response,model);
		}
		
		/**@author Saif.Ali
		 * Date- 03/08/2017
		 * Used to show the jsp page for the event email template*/
		@RequestMapping(value = "/createTemplateForEvent", method = RequestMethod.GET)
		public String createTemplateForEvent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {	
			try {
				/*List<EmailEventAndTemplate> emailEventTemplateList = administratorService.getEventForTemplateList();
				model.addAttribute("emailEventTemplateList",emailEventTemplateList);*/
			} catch (Exception e) {			
				logger.error("serverConfigurationDB() In AdministratorController.java: Exception",e);
			}
			return "administrator/createTemplateForEvent";
		}
		/**@author Saif.Ali
		 * Date- 03/08/2017
		 * Used to insert the email template data into the database*/
		
		
		/*Modified by naimisha 02052018*/
		@RequestMapping(value = "/insertTemplateForEvent", method = RequestMethod.POST)
		public String insertTemplateForEvent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, EmailEventAndTemplate emailEvent,@ModelAttribute("sessionObject") SessionObject sessionObject) {	
			try {
				String emailBody = request.getParameter("emailBody");
				String templateFor= request.getParameter("templateFor"); 	//newly added 17-10-2017
				emailEvent.setEmailBody(emailBody); 
				emailEvent.setTemplateFor(templateFor); 	//newly added 17-10-2017
				emailEvent.setUpdatedBy(sessionObject.getUserId());
				String emailSubject = request.getParameter("emailSubject");
				String emailFooter = request.getParameter("emailFooter");
				//Added by Naimisha 02052018
				String status = "";
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				File directory = new File(repository);
				boolean isExists = directory.exists();
				
				if(isExists == true){
					System.out.println("repository=="+repository);
					String fullPath = repository+"\\velocity";
					File file = new File(fullPath);
				    boolean isCreated = file.mkdirs();
				    
				    System.out.println("isCreated=="+isCreated);
				    System.out.println("emailBody==="+emailBody);
				    
				   
					
				    FileWriter fw = new FileWriter(repository+"\\velocity\\"+emailSubject+".vm");
			
				    BufferedWriter bw = new BufferedWriter(fw);
				    bw.write(emailBody);
				    System.out.println("Done");
						
					bw.close();
					fw.close();
					
					
					emailEvent.setEventCode(repository+"\\velocity\\"+emailSubject+".vm");
					
					List<String>parameterList = new ArrayList<>();
					if(emailBody.contains("$")){
						String[] emailBodyArr = emailBody.split(" ");
						for(int j=0;j<emailBodyArr.length;j++){
							if(emailBodyArr[j].contains("$")){
								parameterList.add(emailBodyArr[j].trim());
							}
						}
						
					}
					
					emailEvent.setActionList(parameterList);
					status = administratorService.insertTemplateForEvent(emailEvent);
					
					model.addAttribute("status", status);
				}
				
				
				
				
					
				/**Added by @author Saif.Ali
				 * Date-29-03-2018
				 * Used to insert the details into the activity log*/
				
				String description = "";
				if(status.equalsIgnoreCase("success"))
				{
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("ENTER TEMPLATE DETAILS");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("SYSTEM ADMINISTRATION");
					updateLog.setUpdatedFor("Template For :: " + templateFor);
					if(templateFor != ""){
						description= description + "Template For ::" + templateFor + " ; " ;
					}
					if(emailSubject != ""){
						description= description + "Email Subject ::" + emailSubject + " ; " ;
					}
					if(emailBody != ""){
						description= description + "Email Body ::" + emailBody + " ; " ;
					}
					if(emailFooter != ""){
						description= description + "Email Footer ::" + emailFooter + " ; " ;
					}
					updateLog.setDescription(description);
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 4150 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
					
					
					
					
					
				}
				
				
			} catch (Exception e) {	
				e.printStackTrace();
				logger.error("insertTemplateForEvent() In AdministratorController.java: Exception",e);
			}
			return createTemplateForEvent(request,response,model);
		}
		
		/**@author Saif.Ali
		 * Date-03/08/2017
		 * Used to get the list of events to show in jsp*/
		@RequestMapping(value = "/emailTemplateList", method = RequestMethod.GET)
		public String getListOfTemplateForEvent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,@ModelAttribute("sessionObject") SessionObject sessionObject) {	
			try {
				List<EmailEventAndTemplate> emailEventTemplateList= administratorService.getListOfTemplateForEvent();
				model.addAttribute("emailEventTemplateList", emailEventTemplateList);	
				} catch (Exception e) {	
				e.printStackTrace();
				logger.error("serverConfigurationDB() In AdministratorController.java: Exception",e);
			}
			return "administrator/emailTemplateForEventList";
		}
		
		/**@author Saif.Ali
		 * Date- 04/08/2017
		 * Used to show the jsp page for the event email and template mapping*/
		@RequestMapping(value = "/mapEventAndTemplate", method = RequestMethod.GET)
		public String mapEventAndTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {	
			try {
				List<EmailEventAndTemplate> emailEventListForMapping = administratorService.getEventListForMapping();
				model.addAttribute("emailEventListForMapping",emailEventListForMapping);
				
				List<EmailEventAndTemplate>emailTemplateListForMapping= administratorService.getTemplateListForMapping();
				model.addAttribute("emailTemplateListForMapping", emailTemplateListForMapping);
				
				List<EmailEventAndTemplate> emailEventTemplateMappingList = administratorService.getEmailEventTemplateMappingList();
				model.addAttribute("emailEventTemplateMappingList",emailEventTemplateMappingList);
			} catch (Exception e) {			
				logger.error("mapEventAndTemplate() In AdministratorController.java: Exception",e);
			}
			return "administrator/createEventAndTemplateMapping";
		}
		
		/**@author Saif.Ali
		 * Date- 04/08/2017
		 * Used to insert the email and template mapping data into the database*/
		@RequestMapping(value = "/insertEventAndTemplateMapping", method = RequestMethod.POST)
		public String insertEventAndTemplateMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, EmailEventAndTemplate emailEvent,@ModelAttribute("sessionObject") SessionObject sessionObject) {	
			try {
				emailEvent.setUpdatedBy(sessionObject.getUserId());
				String status = administratorService.insertEmailEventAndTemplateMapping(emailEvent);
				model.addAttribute("status", status);	
			} catch (Exception e) {	
				e.printStackTrace();
				logger.error("serverConfigurationDB() In AdministratorController.java: Exception",e);
			}
			return mapEventAndTemplate(request,response,model);
		}
		
		/**@author Saif.Ali
		 * Date- 04/08/2017
		 * 
		 * Used to show the information for the email templates*/
		@RequestMapping(value = "/editEmailTemplate", method = RequestMethod.GET)
		public String editEmailTemplate(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model,EmailEventAndTemplate emailEvent ,@RequestParam("templateCode") String templateCode,
								  @ModelAttribute("sessionObject") SessionObject sessionObject) {
			
			try {
				emailEvent=administratorService.getTheTemplateValuesToEdit(templateCode);
				model.addAttribute("emailEvent", emailEvent);
			} catch (Exception e) {
				logger.error("Exception In editEmailEventTemplate() method of AdministratorController.java: Exception",e);
				e.printStackTrace();
			}
			return getListOfTemplateForEvent(request,response,model,sessionObject);
		}
		
		/**@author Saif.Ali
		 * Date- 04/08/2017
		 * Used to update the templates*/
		@RequestMapping(value = "/editTemplateForEvent", method = RequestMethod.POST)
		public String EditTemplateForEvent(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model,EmailEventAndTemplate emailEvent ,@RequestParam("templateCode") String templateCode,
								  @ModelAttribute("sessionObject") SessionObject sessionObject) {
			String status="";
			try {
				/*String temp= request.getParameter(templateCode);*/
				emailEvent.setTemplateCode(templateCode);
				String emailBody = request.getParameter("emailBody");
				emailEvent.setEmailBody(emailBody); 
				String templateFor= request.getParameter("templateFor");		//new added 17-10-2017
				emailEvent.setTemplateFor(templateFor);				//new added 17-10-2017
				System.out.println("LN 3428 :: template for value::"+templateFor); 
				emailEvent.setUpdatedBy(sessionObject.getUserId());
				status=administratorService.editTemplateInformationForemail(emailEvent);
				model.addAttribute("status", status);
				/**Added by @author Saif.Ali
				 * Date- 29-03-2018*/
				String oldTemplateFor = request.getParameter("oldTemplateFor");
				String oldEmailSubject = request.getParameter("oldEmailSubject");
				String oldEmailBody = request.getParameter("oldEmailBody");
				String oldEmailFooter = request.getParameter("oldEmailFooter");
				String description = "";
				
				if(status.equalsIgnoreCase("updatesuccess")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("EDIT EMAIL TEMPLATE DETAILS");
					updateLog.setInsertUpdate("UPDATE");
					updateLog.setModule("SYSTEM ADMINISTRATION");
					updateLog.setUpdatedFor("Template For :: " + oldTemplateFor);
					if(!(oldEmailSubject.equalsIgnoreCase(emailEvent.getEmailSubject()))){
						description = description + ("Email Subject :: " + oldEmailSubject + " is now updated to new email subject :: " + emailEvent.getEmailSubject());
					}
					if(!(oldEmailBody.equalsIgnoreCase(emailBody))){
						description = description + ("Email Body :: " + oldEmailBody + " is now updated to new Email Body :: " + emailBody);
					}
					if(!(oldEmailFooter.equalsIgnoreCase(emailEvent.getEmailFooter()))){
						description = description + ("Email Footer :: " + oldEmailFooter + " is now updated to new Email Footer :: " + emailEvent.getEmailFooter());
					}
					updateLog.setDescription(description);
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 4290 :: AdministratorController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
			} catch (Exception e) {
				logger.error("Exception In EditTemplateForEvent() method of AdministratorController.java: Exception",e);
				e.printStackTrace();
			}finally{
				logger.info(status);
			}
			return editEmailTemplate(request,response,model, emailEvent, status, sessionObject);
		}
		
		//Added By Naimisha 29082017
		
		/*<!-- added By ranita.sur on 271120172017 for mapping with survey -->*/
		//missing link integration 17042018
		@RequestMapping(value = "/mapCategoryWithSurvey", method = RequestMethod.GET)
		public String mapCategoryWithSurvey(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model,
								  @ModelAttribute("sessionObject") SessionObject sessionObject) {
			
			try {
				/*List<JobType> jobDetailsList = administratorService.getAllJobDetails();
				
				model.addAttribute("jobDetailsList", jobDetailsList);*/
				List<JobType> categoryList = administratorService.getCategoryList();
				model.addAttribute("categoryList", categoryList);
				List<Question> surveyList = administratorService.getAllSurveyList();
				model.addAttribute("surveyList", surveyList);
				/*<!-- added By ranita.sur on 29082017 for mapping with survey -->*/
				List<JobType> taskSurveyList = administratorService.getAllTaskSurveyList();
				model.addAttribute("taskSurveyList", taskSurveyList);
				
			} catch (Exception e) {
				logger.error("Exception In mapTaskWithSurvey() method of AdministratorController.java: Exception",e);
				e.printStackTrace();
			}
			return "administrator/mapServeyWithTask";
		}
		/*<!-- added By ranita.sur on 27112017 for mapping with survey -->*/
		//missing link integration 17042018
		@RequestMapping(value = "/submitMapWithServey", method = RequestMethod.POST)
		public String submitMapWithServey(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,@RequestParam("approverGroupDesc") String approverGroupDesc ,
			@RequestParam("surveyName") String surveyName,@ModelAttribute("sessionObject") SessionObject sessionObject) {	
			try {
				JobType jt =new JobType();
				jt.setApproverGroupName(approverGroupDesc);
				jt.setJobTypeName(surveyName);
				jt.setUpdatedBy(sessionObject.getUserId());
				String status = administratorService.submitMapWithServey(jt);
				model.addAttribute("status", status);	
			} catch (Exception e) {	
				e.printStackTrace();
				logger.error("serverConfigurationDB() In AdministratorController.java: Exception",e);
			}
			return mapCategoryWithSurvey(request,response,model,sessionObject);
		}
		
		
		
		/**Added by Ranita.Sur
		 * Date-25/09/2017*/
		
		@RequestMapping(value = "/applicationMessage.html", method = RequestMethod.GET)
		public String applicationMessage(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			List<Department> departmentShowList = null;
			try {
				departmentShowList= commonService.selectAllDepartment();
				model.addAttribute("departmentShowList", departmentShowList);
				logger.info("calling configureTimeTableParameter()GET method of BackOfficeController");
			
				
			} catch (Exception e) {
				logger.error("Exception in configureTimeTableParameter() GET method Of BackOfficeController",e);
			}
			return "administrator/configureApplicationMessage";
		}
		
		
		@RequestMapping(value = "/getConfigureApplicationMessageDetails", method = RequestMethod.GET)
		public @ResponseBody
		String getConfigureApplicationMessageDetails(@RequestParam("departmentCode") String departmentCode,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			//String departmentBudgetDetails="";
	        StringBuffer sb = new StringBuffer();
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			String messagePath = repository+"/"+messageQueryPath;
			System.out.println("LN 9683"+messagePath);
			System.out.println("LN 9684"+departmentCode);
			
					
			
			try {
		         File inputFile = new File(messagePath);
		         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder;

		         dBuilder = dbFactory.newDocumentBuilder();

		         Document doc = dBuilder.parse(inputFile);
		         doc.getDocumentElement().normalize();

		         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		     	 NodeList nodeList = doc.getElementsByTagName("MODULE");

		         for (int i = 0; i < nodeList.getLength(); i++) {
		            Node nNode = nodeList.item(i);
		            
		            System.out.println("\nCurrent Element :" + nNode.getNodeName());
		            
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            	
		            	Element eElement = (Element) nNode;

		    			System.out.println("Module id : " + eElement.getAttribute("ID"));
		    			
		    			if(departmentCode.equalsIgnoreCase(eElement.getAttribute("ID"))){
		    				NodeList childNodes = nNode.getChildNodes();
		    					    				
		    				for (int j = 0; j < childNodes.getLength(); j++) {
		    					Node childNode = childNodes.item(j);
		    					
		    					if (childNode.getNodeType() == Node.ELEMENT_NODE) {
		    						
		    						Element childElement = (Element) childNode;
		    						
		    						sb.append("-ID:" + childElement.getAttribute("ID"));
		    						
		    						if((null != childElement.getElementsByTagName("INSERT"))&&(childElement.getElementsByTagName("INSERT").getLength() != 0)){
		    							sb.append(",INSERTMSG:" + childElement.getElementsByTagName("INSERT").item(0).getTextContent());
		    						}
		    						if((null != childElement.getElementsByTagName("UPDATE"))&&(childElement.getElementsByTagName("UPDATE").getLength() != 0)){
		    							sb.append(",UPDATEMSG:" + childElement.getElementsByTagName("UPDATE").item(0).getTextContent());
		    						}
		    						if((null != childElement.getElementsByTagName("INACTIVE"))&&(childElement.getElementsByTagName("INACTIVE").getLength() != 0)){
		    							sb.append(",INACTIVEMSG:" + childElement.getElementsByTagName("INACTIVE").item(0).getTextContent());
		    						}
		    						if((null != childElement.getElementsByTagName("FAILURE"))&&(childElement.getElementsByTagName("FAILURE").getLength() != 0)){
		    							sb.append(",FAILUREMSG:" + childElement.getElementsByTagName("FAILURE").item(0).getTextContent());
		    						}
		    						
		    						System.out.println("Page id : " + childElement.getAttribute("ID"));
		    						
		    						
		    						
		    					}
		    				}
		    			}
		            }
		            
		            
		            
		         }
		      } catch (ParserConfigurationException e) {
		         e.printStackTrace();
		      } catch (SAXException e) {
		         e.printStackTrace();
		      } catch (FileNotFoundException e){
		          e.printStackTrace();
		      }catch (IOException e){
		    	  e.printStackTrace();
		      }
			System.out.println("LN 9767 :"+sb.toString());
			return sb.toString() ;
		}
		
		
		@RequestMapping(value = "/updateConfigureMessageDetails", method = RequestMethod.POST)
		public String updateConfigureMessageDetails(HttpServletRequest request,
													  HttpServletResponse response,
													  ModelMap model,
													   @RequestParam("departmentId") String departmentShowName,
													 //@RequestParam(required = false,value = "messages") String[] messages,
													  //@RequestParam(required = false,value ="messageName") String[] messageName,
													  @ModelAttribute("sessionObject") SessionObject sessionObject) {
			//String status="";
			System.out.println("LN 9782 :"+departmentShowName);
			
			String pId[] = request.getParameterValues("pageIdies");
			System.out.println("LN 9784 :"+pId);
			
				
			
			try {
				
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				String messagePath = repository+"/"+messageQueryPath;
				
				File xmlFile = new File(messagePath);
		        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder dBuilder;
		        try {
		            dBuilder = dbFactory.newDocumentBuilder();
		            Document doc = dBuilder.parse(xmlFile);
		            doc.getDocumentElement().normalize();
		            
		            //update Element value
		            
		            for(int i=0;i<pId.length-1;i++){
		    			System.out.println("LN 9784 :"+pId[i]);
		    			String insertMessage = request.getParameter("messageINSERTMSG"+pId[i]);
		    			String updateMessage = request.getParameter("messageUPDATEMSG"+pId[i]);
		    			String inactiveMessage = request.getParameter("messageINACTIVEMSG"+pId[i]);
		    			String failureMessage = request.getParameter("messageFAILUREMSG"+pId[i]);
		    			
		    			updateElementValue(doc, pId[i], insertMessage, updateMessage, inactiveMessage, failureMessage);
		    			
		    			System.out.println("LN 9789 :" +insertMessage);
		    			System.out.println("LN 9790:" +updateMessage);
		    			System.out.println("LN 9791 :" +inactiveMessage);
		    			System.out.println("LN 9792 :" +failureMessage);
		    		
		    		}
		            
		            
		            //write the updated document to file or console
		            doc.getDocumentElement().normalize();
		            TransformerFactory transformerFactory = TransformerFactory.newInstance();
		            Transformer transformer = transformerFactory.newTransformer();
		            DOMSource source = new DOMSource(doc);
		            StreamResult result = new StreamResult(new File(messagePath));
		            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		            transformer.transform(source, result);
		            System.out.println("XML file updated successfully");
		            
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
				
			} catch (Exception e) {
				logger.error("Exception In updateIndividualCommodityDetails() method of InventoryController: Exception",e);
				e.printStackTrace();
			}finally{
				//logger.info(status);
			}
			return applicationMessage(request, response, model);
		}	
		
		private static void updateElementValue(Document doc, String pageId, String insertMessage, String updateMessage, String inactiveMessage, String failureMessage) {
	        NodeList pagesList = doc.getElementsByTagName("PAGE");
	        
	        for (int i = 0; i < pagesList.getLength(); i++) {
	        	
	        	Node pageNode = pagesList.item(i);
	            if (pageNode.getNodeType() == Node.ELEMENT_NODE) {
	            	
	            	Element pageElement = (Element) pageNode;
	            	
	            	if(pageId.equalsIgnoreCase(pageElement.getAttribute("ID"))){
	            		
	            		NodeList messageNodes = pageNode.getChildNodes();
	            		
	            		for (int j = 0; j < messageNodes.getLength(); j++) {
	    					Node messageNode = messageNodes.item(j);
	    					
	    					if (messageNode.getNodeType() == Node.ELEMENT_NODE) {
	    						
	    						Element messageElement = (Element) messageNode;
	    						
	    						if(("INSERT".equalsIgnoreCase(messageElement.getNodeName()))&&(null != insertMessage)){
	    							messageElement.setTextContent(insertMessage);
	    						}
	    						
	    						if(("UPDATE".equalsIgnoreCase(messageElement.getNodeName()))&&(null != updateMessage)){
	    							messageElement.setTextContent(updateMessage);
	    						}
	    						
	    						if(("INACTIVE".equalsIgnoreCase(messageElement.getNodeName()))&&(null != inactiveMessage)){
	    							messageElement.setTextContent(inactiveMessage);
	    						}
	    						
	    						if(("FAILURE".equalsIgnoreCase(messageElement.getNodeName()))&&(null != failureMessage)){
	    							messageElement.setTextContent(failureMessage);
	    						}
	    						
	    					}
	    				}
	            	}
	            }
	        }
	        
	    }
		
		
	
		
		//ADDED by naimisha 16102017
		
		@RequestMapping(value = "/createCategory", method = RequestMethod.GET)
		public ModelAndView createCategory(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			try {
				logger.info("In createCategory() method of AdministratorController");
				
				List<JobType>allJobList = administratorService.getAllJobDetails();
				model.addAttribute("allJobList", allJobList);
				
				List<JobType> categoryList = administratorService.getCategoryList();
				model.addAttribute("categoryList", categoryList);
				/*if (serviceTypeList != null && serviceTypeList.size() != 0) {
					model.addAttribute("serviceTypeList", serviceTypeList);
				}*/
			} catch (Exception e) {
				logger.error("createCategory() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/createCategory");
		}

		/**
		 * 
		 * @param request
		 * @param response
		 * @param model
		 * @return ModelAndView
		 */
		@RequestMapping(value = "/insertCategory", method = RequestMethod.POST)
		public ModelAndView insertCategory(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				JobType jobType,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In insertCategory() method of AdministratorController");			
				jobType.setUpdatedBy(sessionObject.getUserId());
				//String status = ticketService.saveTicketService(serviceType);
				String[] tasks = request.getParameterValues("jobTypeCode");
				//System.out.println("tasks==="+tasks);
				//System.out.println("category ===="+jobType.getCategory());
				List<String>taskList = new ArrayList<String>();
				if(null != tasks && tasks.length != 0){
					for(int i = 0 ; i < tasks.length ; i++){
						if(null != tasks[i] &&  tasks[i] != ""){
							taskList.add(tasks[i]);
						}
						
					}
					jobType.setStringList(taskList);
				}
				
			String status = administratorService.insertIntoCategoryAndCategoryTaskMapping(jobType);
			model.addAttribute("status", status);	
			
			if(status.equalsIgnoreCase("success")){	
				model.addAttribute("insertStatus", "success");
				model.addAttribute("msg", "Successfully Created");
			}else{
				model.addAttribute("insertStatus", "fail");
				model.addAttribute("msg", "Failed To Create");
				logger.info("createNewJob() In AdministratorController.java: Error In Creating Roles");
			}	
			} catch (Exception e) {
				logger.error("insertCategory() In AdministratorController.java: Exception" + e);
			}
			return createCategory( request,response,  model);
		}
		
		@RequestMapping(value = "/getTaskListForCategory", method = RequestMethod.GET)
		public ModelAndView getTaskListForCategory(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("categoryCode") String categoryCode) {
			try {
				logger.info("In getTaskListForCategory() method of AdministratorController");
				
				List<JobType>allJobList = administratorService.getAllJobDetails();
				model.addAttribute("allJobList", allJobList);
				
				List<JobType> taskList = administratorService.getTaskistForCategory(categoryCode);
				model.addAttribute("category", taskList.get(0));
				
			} catch (Exception e) {
				logger.error("getTaskListForCategory() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/editCategory");
		}

		@RequestMapping(value = "/editCategory", method = RequestMethod.POST)
		public ModelAndView editCategory(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				JobType jobType,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In editCategory() method of AdministratorController");			
				jobType.setUpdatedBy(sessionObject.getUserId());
				String[] tasks = request.getParameterValues("jobTypeCode");
				List<String>taskList = new ArrayList<String>();
				if(null != tasks){
					for(int i = 0 ; i < tasks.length ; i++){
						taskList.add(tasks[i]);
					}
					jobType.setStringList(taskList);
				}
				
			String status = administratorService.updateIntoCategoryAndCategoryTaskMapping(jobType);
			if(status.equalsIgnoreCase("success")){	
				model.addAttribute("insertStatus", "success");
				model.addAttribute("msg", "Successfully Updated");
			}else{
				model.addAttribute("insertStatus", "fail");
				model.addAttribute("msg", "Failed To Update");
				logger.info("editCategory() In AdministratorController.java: Error In Creating Roles");
			}
			/**Added by @author Saif.Ali
			 * Date-21-03-2018*/
			if(status.equalsIgnoreCase("success")){
				String oldCategoryName= request.getParameter("oldCategoryName");
				String newCategoryName =  request.getParameter("category");
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT CATEGORY TASK MAPPING");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor(oldCategoryName);
				String description = "";
				if(!(oldCategoryName.equalsIgnoreCase(newCategoryName)))
				{
					description= description + "Category Name :: " + oldCategoryName + " updated to Category Name:: " + newCategoryName;
				}
				if(null != tasks)
				{
					for(int i = 0 ; i < tasks.length ; i++)
					{
						if(tasks[i].equalsIgnoreCase("TASK-1"))
						{
							description= description +" Category Name :: " + oldCategoryName+" Mapped with Task Name ::" + "APPROVAL FOR INVENTORY FROM FINANCE";
						}
						if(tasks[i].equalsIgnoreCase("TASK-2"))
						{
							description= description +" Category Name :: " + oldCategoryName+" Mapped with Task Name ::" + "APPROVAL FOR INVENTORY FROM FINANCE REGISTER";
						}
					}
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 3178 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
			}catch(Exception e){
				
			}
			return createCategory( request,response,  model);
		}
	
		
		@RequestMapping(value = "/createRecepientCategoryMapping", method = RequestMethod.GET)
		public ModelAndView createRecepientCategoryMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			try {
				logger.info("In createRecepientCategoryMapping() method of AdministratorController");
				
				List<Approver>recipientList = administratorService.getAllApproverGroups();
				model.addAttribute("recipientList", recipientList);
				
				List<JobType> categoryList = administratorService.getCategoryList();
				model.addAttribute("categoryList", categoryList);
				
				List<JobType>categoryListForRecipientMapping = administratorService.getCategoryListForRecipientMapping();
				model.addAttribute("categoryListForRecipientMapping", categoryListForRecipientMapping);
				/*if (serviceTypeList != null && serviceTypeList.size() != 0) {
					model.addAttribute("serviceTypeList", serviceTypeList);
				}*/
			} catch (Exception e) {
				logger.error("createRecepientCategoryMapping() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/createRecepientCategoryMapping");
		}
		
		
		@RequestMapping(value = "/insertCategoryRecipientMapping", method = RequestMethod.POST)
		public ModelAndView insertCategoryRecipientMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				JobType jobType,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In insertCategoryRecipientMapping() method of AdministratorController");			
				jobType.setUpdatedBy(sessionObject.getUserId());
				//String status = ticketService.saveTicketService(serviceType);
				String[] tasks = request.getParameterValues("jobTypeCode");
				//System.out.println("tasks==="+tasks);
				//System.out.println("category ===="+jobType.getCategory());
				List<String>taskList = new ArrayList<String>();
				if(null != tasks && tasks.length != 0){
					for(int i = 0 ; i < tasks.length ; i++){
						if(null != tasks[i] &&  tasks[i] != ""){
							taskList.add(tasks[i]);
						}
						
					}
					jobType.setStringList(taskList);
				}
				
			String status = administratorService.insertCategoryRecipientMapping(jobType);
			model.addAttribute("status", status);	
			
			if(status.equalsIgnoreCase("success")){	
				model.addAttribute("insertStatus", "success");
				model.addAttribute("msg", "Successfully Created");
			}else{
				model.addAttribute("insertStatus", "fail");
				model.addAttribute("msg", "Failed To Create");
				logger.info("creatinsertCategoryRecipientMappingeNewJob() In AdministratorController.java: Error In Creating CategoryRecipientMapping");
			}	
			} catch (Exception e) {
				logger.error("insertCategoryRecipientMapping() In AdministratorController.java: Exception" + e);
			}
			return createRecepientCategoryMapping( request,response,  model);
		}
		
		@RequestMapping(value = "/getCategoryRecipientMapping", method = RequestMethod.GET)
		public ModelAndView getCategoryRecipientMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("categoryCode") String categoryCode) {
			try {
				logger.info("In getTaskListForCategory() method of AdministratorController");
				
				List<JobType>allJobList = administratorService.getAllJobDetails();
				model.addAttribute("allJobList", allJobList);
				
				List<JobType> categoryWiseRecipientList = administratorService.getCategoryRecipientMapping(categoryCode);
				model.addAttribute("category", categoryWiseRecipientList.get(0));
				
				List<Approver>recipientList = administratorService.getAllApproverGroups();
				model.addAttribute("recipientList", recipientList);
				
			} catch (Exception e) {
				logger.error("getTaskListForCategory() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/editCategoryRecipientMapping");
		}
		
		@RequestMapping(value = "/editCategoryRecipientMapping", method = RequestMethod.POST)
		public ModelAndView editCategoryRecipientMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				JobType jobType,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In editCategoryRecipientMapping() method of AdministratorController");			
				jobType.setUpdatedBy(sessionObject.getUserId());
				//String status = ticketService.saveTicketService(serviceType);
				String[] tasks = request.getParameterValues("jobTypeCode");
				//System.out.println("tasks==="+tasks);
				//System.out.println("category ===="+jobType.getCategory());
				List<String>taskList = new ArrayList<String>();
				if(null != tasks && tasks.length != 0){
					for(int i = 0 ; i < tasks.length ; i++){
						if(null != tasks[i] &&  tasks[i] != ""){
							taskList.add(tasks[i]);
						}
						
					}
					jobType.setStringList(taskList);
				}
				
			String status = administratorService.editCategoryRecipientMapping(jobType);
			model.addAttribute("status", status);	
			
			if(status.equalsIgnoreCase("success")){	
				model.addAttribute("insertStatus", "success");
				model.addAttribute("msg", "Successfully Updated");
			}else{
				model.addAttribute("insertStatus", "fail");
				model.addAttribute("msg", "Failed To Update");
				logger.info("editCategoryRecipientMapping() In AdministratorController.java: Error In Creating CategoryRecipientMapping");
			}
			/**Added by @author Saif.Ali
			 * Date-21-03-2018*/
			if(status.equalsIgnoreCase("success")){
				String description = "";
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT CATEGORY RECEPIENT MAPPING");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor(request.getParameter("category"));
				if(null != tasks && tasks.length != 0){
					for(int i = 0 ; i < tasks.length ; i++){
						if(null != tasks[i] &&  tasks[i] != ""){
							if(tasks[i].equalsIgnoreCase("APPROVERGROUP-1")){
								description= description + "Category Name :: " + request.getParameter("category") + " mapped to Recepient Group :: " + "STUDENT LEAVE APPROVAL GROUP";
							}
							if(tasks[i].equalsIgnoreCase("APPROVERGROUP-2")){
								description= description + "Category Name :: " + request.getParameter("category") + " mapped to Recepient Group :: " + "COMMODITY PO APPROVAL GROUP";
							}
						}
						
					}
					
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 3178 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
			} catch (Exception e) {
				logger.error("editCategoryRecipientMapping() In AdministratorController.java: Exception" + e);
			}
			return createRecepientCategoryMapping( request,response,  model);
		}
		
		
		
		
/*Added by ranita.sur on 17102017 for editing the recipient group*/
		
		
		@RequestMapping(value = "/getRecipientListForEdit", method = RequestMethod.GET)
		public ModelAndView getRecipientListForEdit(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("approverCodes") String approverCodes,
				@RequestParam("approverName") String approverName) {
			//System.out.println("HIII");
			try {
				Resource resource = administratorService.getResourceAndRolesFromDB();
				if(null!=resource){
					model.addAttribute("resourceTypeList", resource.getResourceTypeList());
				}
				Approver approverCode=new Approver();
				if(null!=approverCodes){
					approverCode.setApproverGroupCode(approverCodes);
					//System.out.println("LN 3858  ::"+approverCode.getApproverGroupCode());
					}
				logger.info("In getRecipientListForEdit() method of AdministratorController");
				List<Approver> approverGroupList = administratorService.getAllApproverGroups();
				model.addAttribute("pagedListHolder", approverGroupList);
				model.addAttribute("approverName", approverName);
				model.addAttribute("approverCodes", approverCodes);
				List<Approver> userIdList = administratorService.getUserIdListForApprover(approverCode);
				//System.out.println("LN 3864  ::"+userIdList.size());
				model.addAttribute("userIdList", userIdList);
			} catch (Exception e) {
				logger.error("getRecipientListForEdit() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/editRecipientGroup");
		}

		@RequestMapping(value = "/editRecipient", method = RequestMethod.POST)
		public ModelAndView editRecipient(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				Approver approver,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In insertCategory() method of AdministratorController");			
				approver.setUpdatedBy(sessionObject.getUserId());
				String[] userId = request.getParameterValues("userId");
				//String[] name = request.getParameterValues("userNameNew");
				List<String> approverGroupList = new ArrayList<String>();
				if(null != userId){
					for(int i = 0 ; i < userId.length ; i++){
						approverGroupList.add(userId[i]);
					}
					approver.setStringList(approverGroupList);
				}
				
			String status = administratorService.updateIntoApproverGroupTaskMapping(approver);
			}catch(Exception e){
				
			}
			return createApprovers( request,response,  model);
		}
		
		
		//Added By Naimisha 23102017
		
		
		@RequestMapping(value = "/createTaskStatus", method = RequestMethod.GET)
		public ModelAndView createTaskStatus(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			try {
				logger.info("In createTaskStatus() method of AdministratorController");
				
				List<Ticket>ticketStatusList = administratorService.getAllTicketStatusList();
				model.addAttribute("ticketStatusList", ticketStatusList);
				
				
				List<Ticket>taskStatusList = administratorService.getAllTaskStatusList();
				model.addAttribute("taskStatusList", taskStatusList);
			
			} catch (Exception e) {
				logger.error("createTaskStatus() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/createTaskStatus");
		}
		
		
		@RequestMapping(value = "/createTaskStatus", method = RequestMethod.POST)
		public ModelAndView createTaskStatus(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,Ticket ticket,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In createTaskStatus() POST method of AdministratorController");
				
				ticket.setUpdatedBy(sessionObject.getUserId());
				String status = administratorService.insertTaskStatus(ticket);
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
			return createTaskStatus(request,response,model);
		}
		
		
		/**Edit Task Status Details
		 By Ranita.Sur 24102017**/
		@RequestMapping(value = "/editTaskStatus", method = RequestMethod.POST)
		public ModelAndView editTaskStatus(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,Ticket ticket,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("Inside Method editTaxPercentages-GET of FinanceController");
				ticket = new Ticket();
				String saveId=request.getParameter("saveId");
				String newTaskName = request.getParameter("getTaskStatus").trim().toUpperCase();
				//System.out.println("newTaskName==="+newTaskName);
				String newTaskType = request.getParameter("getTaskType");
				//System.out.println("newTaskType==="+newTaskType);
				String newApprovalReq = request.getParameter("getTicketStatus");
				//System.out.println("newApprovalReq==="+newApprovalReq);
				//jobType.setJobTypeCode(request.getParameter("jobTypeCode"+saveId).trim());
				ticket.setTicketCode(request.getParameter("ticketCode"+saveId).trim());
				ticket.setStatus(newTaskName);
				ticket.setApproval(newTaskType);
				ticket.setTicketDesc(newApprovalReq);
				String updateStatus = administratorService.editTaskStatus(ticket);
				//model.addAttribute("updateStatus", updateStatus);
				if(updateStatus.equalsIgnoreCase("Success")){	
					model.addAttribute("insertStatus", "success");
					model.addAttribute("msg", "Successfully Updated");
				}else{
					model.addAttribute("insertStatus", "Fail");
					model.addAttribute("msg", "Failed To Update");
					logger.info("editNewTask() In AdministratorController.java: Error In Creating Roles");
				}
				/**Added by @author Saif.Ali
				 * Date-21-03-2018*/
				if(updateStatus.equalsIgnoreCase("Success")){
					String oldTaskStatus= request.getParameter("status"+saveId);
					String oldTaskType= request.getParameter("approval"+saveId);
					String oldTicketStatus= request.getParameter("ticketDesc"+saveId);
					String description = "";
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("EDIT TASK STATUS");
					updateLog.setInsertUpdate("UPDATE");
					updateLog.setModule("OFFICE ADMINISTRATION");
					updateLog.setUpdatedFor(request.getParameter("status"+saveId));
					if(!(oldTaskStatus.equalsIgnoreCase(newTaskName)))
					{
						description= description + "Task Status ::" + oldTaskStatus + " updated to new Task Status ::" + newTaskName + " ; " ;
					}
					if(!(oldTaskType.equalsIgnoreCase(newTaskType)))
					{
						description= description + "Task Type ::" + oldTaskType + " updated to new Task Type ::" + newTaskType + " ; " ;
					}
					if(!(oldTicketStatus.equalsIgnoreCase(newApprovalReq)))
					{
						description= description + "Task Status ::" + oldTicketStatus + " updated to new Task Status ::" + newApprovalReq + " ; " ;
					}
					updateLog.setDescription(description);
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 3178 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
			} catch (Exception ce){
				ce.printStackTrace();
				logger.error("Exception in method editNewTask-GET of AdministratorController", ce);
			}
			return createTaskStatus(request,response,model);
		}
		
	//ADDED by naimisha 05042018
		
		@RequestMapping(value = "/mapKeyWithCategory", method = RequestMethod.GET)
		public ModelAndView mapKeyWithCategory(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			try {
				logger.info("In mapKeyWithCategory() method of AdministratorController");
				
				List<Ticket>categoryKeyList = administratorService.getAllCategoryWithKeys();
				model.addAttribute("categoryKeyList", categoryKeyList);
				
				List<JobType> categoryList = administratorService.getCategoryList();
				model.addAttribute("categoryList", categoryList);
				/*if (serviceTypeList != null && serviceTypeList.size() != 0) {
					model.addAttribute("serviceTypeList", serviceTypeList);
				}*/
			} catch (Exception e) {
				logger.error("mapKeyWithCategory() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/mapKeyWithCategory");
		}
		
		@RequestMapping(value = "/insertCategoryKeyMapping", method = RequestMethod.POST)
		public ModelAndView insertCategoryKeyMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				JobType jobType,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In insertCategoryKeyMapping() method of AdministratorController");			
				jobType.setUpdatedBy(sessionObject.getUserId());
				//String status = ticketService.saveTicketService(serviceType);
				String[] tasks = request.getParameterValues("jobTypeCode");
				//System.out.println("tasks==="+tasks);
				//System.out.println("category ===="+jobType.getCategory());
				List<String>taskList = new ArrayList<String>();
				if(null != tasks && tasks.length != 0){
					for(int i = 0 ; i < tasks.length ; i++){
						if(null != tasks[i] &&  tasks[i] != ""){
							taskList.add(tasks[i].trim());
						}
						
					}
					jobType.setStringList(taskList);
				}
				
			String status = administratorService.insertCategoryKeyMapping(jobType);
			model.addAttribute("status", status);	
			
			if(status.equalsIgnoreCase("success")){	
				model.addAttribute("insertStatus", "success");
				model.addAttribute("msg", "Successfully Created");
			}else{
				model.addAttribute("insertStatus", "fail");
				model.addAttribute("msg", "Failed To Create");
				logger.info("insertCategoryKeyMapping() In AdministratorController.java: Error In Creating CategoryRecipientMapping");
			}	
			} catch (Exception e) {
				logger.error("insertCategoryKeyMapping() In AdministratorController.java: Exception" + e);
			}
			return mapKeyWithCategory( request,response,  model);
		}
		
		@RequestMapping(value = "/getCategoryKeyMapping", method = RequestMethod.GET)
		public ModelAndView getCategoryKeyMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("categoryCode") String categoryCode) {
			try {
				logger.info("In getCategoryKeyMapping() method of AdministratorController");
				
			/*	List<JobType>allJobList = administratorService.getAllJobDetails();
				model.addAttribute("allJobList", allJobList);*/
				
				/*List<JobType> categoryWiseRecipientList = administratorService.getCategoryRecipientMapping(categoryCode);
				model.addAttribute("category", categoryWiseRecipientList.get(0));*/
				
				List <Ticket> categoryKeyList = administratorService.getKeyForACategory(categoryCode);
				model.addAttribute("category",categoryKeyList.get(0));
				model.addAttribute("count", (categoryKeyList.get(0).getCommentList().size()));
				
				List<JobType>allKeyList = administratorService.getAllKeysList();
				model.addAttribute("allKeyList",allKeyList);
				/*List<Approver>recipientList = administratorService.getAllApproverGroups();
				model.addAttribute("recipientList", recipientList);*/
				
			} catch (Exception e) {
				logger.error("getCategoryKeyMapping() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/editCategoryKeyMapping");
		}
		
		@RequestMapping(value = "/editCategoryKeyMapping", method = RequestMethod.POST)
		public ModelAndView editCategoryKeyMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				JobType jobType,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
					logger.info("In editCategoryRecipientMapping() method of AdministratorController");			
					jobType.setUpdatedBy(sessionObject.getUserId());
					//String status = ticketService.saveTicketService(serviceType);
					String[] tasks = request.getParameterValues("jobTypeCode");
					//System.out.println("tasks==="+tasks);
					//System.out.println("category ===="+jobType.getCategory());
					List<String>keyList = new ArrayList<String>();
					if(null != tasks && tasks.length != 0){
						for(int i = 0 ; i < tasks.length ; i++){
							if(null != tasks[i] &&  tasks[i] != ""){
								keyList.add(tasks[i].trim());
							}
							
						}
						jobType.setStringList(keyList);
					}
					
				String status = administratorService.editCategoryKeyMapping(jobType);
				model.addAttribute("status", status);	
				
				if(status.equalsIgnoreCase("success")){	
					model.addAttribute("insertStatus", "success");
					model.addAttribute("msg", "Successfully Updated");
				}else{
					model.addAttribute("insertStatus", "fail");
					model.addAttribute("msg", "Failed To Update");
					logger.info("editCategoryRecipientMapping() In AdministratorController.java: Error In Creating CategoryRecipientMapping");
				}
			}catch(Exception e){		
				logger.error("getKeyDetailsForACategory() In AdministratorController.java: Exception", e);	
			}
			return mapKeyWithCategory( request,response,  model);
		}
		
		@RequestMapping(value = "/getKeyDetailsForACategory", method = RequestMethod.GET)
		public @ResponseBody
		String getKeyDetailsForACategory(@RequestParam("category") String category) {
			String keys = "";
			try{
				logger.info("getKeyDetailsForACategory() In AdministratorController.java");
				List <Ticket> categoryKeyList = administratorService.getKeyForACategory(category);
				System.out.println("keyListsize=="+categoryKeyList.size());
				
				if(null != categoryKeyList){
					List<TicketComment>keyList = categoryKeyList.get(0).getCommentList();
					for(TicketComment  key : keyList){
						keys = keys + key.getTicketCommentCode()+"*"+key.getTicketCommentDesc()+"#";
					}
					
				}
				
		
			}catch(Exception e){		
				logger.error("getKeyDetailsForACategory() In AdministratorController.java: Exception", e);	
			}
			return (new Gson().toJson(keys));
		}
		
		//Added By Naimisha 05042018
		
		
		@RequestMapping(value = "/mapDepartmentWithCategory", method = RequestMethod.GET)
		public ModelAndView mapDepartmentWithCategory(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			try {
				logger.info("In mapDepartmentWithCategory() method of AdministratorController");
				
				List<Department> departmentList = erpService.getAllDepartment();	//Added by naimisha 05042018
				model.addAttribute("departmentList", departmentList);
				
				List<JobType> categoryList = administratorService.getCategoryList();
				model.addAttribute("categoryList", categoryList);
				
				List<Department>departmentMappedCategoryList = administratorService.getAllCategoryMappedWithDepartment();
				model.addAttribute("departmentMappedCategoryList", departmentMappedCategoryList);
			
			} catch (Exception e) {
				logger.error("mapDepartmentWithCategory() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/mapDepartmentWithCategory");
		}
	
	//Added By Naimisha 09042018
		
		@RequestMapping(value = "/insertCategoryDepartmentMapping", method = RequestMethod.POST)
		public ModelAndView insertCategoryDepartmentMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				JobType jobType,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In insertCategoryDepartmentMapping() method of AdministratorController");			
				jobType.setUpdatedBy(sessionObject.getUserId());
				//String status = ticketService.saveTicketService(serviceType);
				String[] tasks = request.getParameterValues("categoryName");
				//System.out.println("tasks==="+tasks);
				//System.out.println("category ===="+jobType.getCategory());
				List<String>taskList = new ArrayList<String>();
				if(null != tasks && tasks.length != 0){
					for(int i = 0 ; i < tasks.length ; i++){
						if(null != tasks[i] &&  tasks[i] != ""){
							taskList.add(tasks[i]);
						}
						
					}
					jobType.setStringList(taskList);
				}
				
			String status = administratorService.insertCategoryDepartmentMapping(jobType);
			model.addAttribute("status", status);	
			
			if(status.equalsIgnoreCase("success")){	
				model.addAttribute("insertStatus", "success");
				model.addAttribute("msg", "Successfully Created");
			}else{
				model.addAttribute("insertStatus", "fail");
				model.addAttribute("msg", "Failed To Create");
				logger.info("insertCategoryDepartmentMapping() In AdministratorController.java: Error In Creating CategoryDepartmentMapping");
			}	
			} catch (Exception e) {
				logger.error("insertCategoryDepartmentMapping() In AdministratorController.java: Exception" + e);
			}
			return mapDepartmentWithCategory( request,response,  model);
		}
		
		@RequestMapping(value = "/getCategoryDepartmentMapping", method = RequestMethod.GET)
		public ModelAndView getCategoryDepartmentMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("departmentCode") String departmentCode) {
			//System.out.println("HIII");
			try {
					logger.info("In getCategoryDepartmentMapping() method of AdministratorController");
					
					List<JobType> categoryList = administratorService.getCategoryList();
					model.addAttribute("categoryList", categoryList);
					
					List<JobType> departmentAndCategoryList = administratorService.getCategoryListForADepartment(departmentCode);
					model.addAttribute("departmentAndMappedCategory", departmentAndCategoryList.get(0));
			} catch (Exception e) {
				logger.error("getCategoryDepartmentMapping() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/editDepartmentCategoryMapping");
		}

	//Added by naimisha 10042018
		
		@ResponseBody
		@RequestMapping(value = "/getDesignationListForDepartment", method = RequestMethod.GET)
		public String getDesignationListForDepartment(HttpServletRequest request,
														 HttpServletResponse response, ModelMap model,
														 @RequestParam("department") String department) {
			logger.info("In getDesignationListForDepartment() method of erpController");
			List<Designation> designatioList = null;
			String designationArray = "";	
			try {				
				designatioList = administratorService.getDesignationListForDepartment(department);
				if(designatioList != null){
					for(Designation designationObject : designatioList){
						designationArray = designationArray + designationObject.getDesignationCode() +"~"+ designationObject.getDesignationName() + "@";
					}
				}
				System.out.println("designationArray==="+designationArray);
			} catch (Exception e) {				
				logger.error("Exception occured in getDesignationListForDepartment() from erpController : ",e);
			}
			return (new Gson().toJson(designationArray));			
		}	
		
		@RequestMapping(value = "/taskDetailsAgainstTaskCode", method = RequestMethod.GET)
		public ModelAndView taskDetailsAgainstTaskCode(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("taskCode") String taskCode) {
			//System.out.println("HIII");
			try {
					logger.info("In taskDetailsAgainstTaskCode() method of AdministratorController");
					System.out.println("taskCode=="+taskCode);
					JobType taskDetails = administratorService.taskDetailsAgainstTaskCode(taskCode);
					System.out.println("is finance=="+taskDetails.isFinance());
					System.out.println("is linked =="+ taskDetails.isLinked());
					if(taskDetails.isFinance() == true){
						taskDetails.setJobTypeObjectId("checked");
					}
					
					if(taskDetails.isLinked() == true){
						taskDetails.setCategory("checked");
					}
					model.addAttribute("taskDetails", taskDetails);
			} catch (Exception e) {
				logger.error("taskDetailsAgainstTaskCode() In AdministratorController.java: Exception"+ e);
			}
			return new ModelAndView("administrator/viewTaskDetails");
		}
		
		
		//Added By Naimisha 17052018
		@RequestMapping(value = "/getTaskStatusForDuplicateCheck", method = RequestMethod.GET)
		public @ResponseBody
		String getTaskStatusForDuplicateCheck(
											  @RequestParam("taskType") String taskType,
											  @RequestParam("type") String type,
											  @RequestParam("ticketStatus") String ticketStatus) {
			String taskStatusValue = "";
			try{
				logger.info("getTaskStatusForDuplicateCheck() In AdministratorController.java");
				Ticket ticket = new Ticket();
				//ticket.setTaskStatus(taskStatus.trim().toUpperCase());
				ticket.setType(type.trim());
				ticket.setStatus(ticketStatus.trim().toUpperCase());
				ticket.setTicketDesc(taskType.trim());
				TicketStatus taskStatusObj = administratorService.getTaskStatusForDuplicateCheck(ticket);
				if(null != taskStatusObj){
					taskStatusValue = taskStatusValue + taskStatusObj.getTicketStatusCode() + taskStatusObj.getTicketStatusName();
				}
		
			}catch(Exception e){		
				logger.error("getTaskStatusForDuplicateCheck() In AdministratorController.java: Exception", e);	
			}
			return (new Gson().toJson(taskStatusValue));
		}

}
