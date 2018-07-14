package com.qts.icam.controller.erp;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.itextpdf.text.log.SysoLogger;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Country;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.IncomeAge;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.Marks;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Staff;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.State;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.common.TeachingLevel;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.erp.AwardsAndRecognization;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.DesignationLevel;
import com.qts.icam.model.erp.DesignationType;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.EmployeeDependent;
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
import com.qts.icam.model.erp.SalaryBreakUp;
import com.qts.icam.model.erp.SalaryTemplate;
import com.qts.icam.model.erp.SalaryTemplateDetails;
import com.qts.icam.model.erp.WorkShopAndTraining;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.backoffice.BackOfficeService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.erp.ERPService;
import com.qts.icam.service.finance.FinanceService;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.mailservice.TelnetClient;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

/**
 * ERPController.java - This controller is responsible for Staff
 * related operations
 * 
 * @version 1.0
 */

@Controller
@SessionAttributes("sessionObject")
public class ERPController {
	public static Logger logger = Logger.getLogger(ERPController.class);
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	BackOfficeService backOfficeService;
	
	@Autowired
	AcademicsService academicsService;
	
	@Autowired
	AdministratorService administratorService;
	
	@Value("${root.path}")
	private String rootPath;
	
	@Value("${employeeDoc.path}")
	private String employeeDocFolderPath;
	
	@Autowired
	FileUploadDownload fileUploadDownload;
	
	@Autowired
	ERPService erpService;	
	
	@Autowired
	FinanceService financeService;

	@Value("${excelDownload.folder}")
	private String excelDownloadFolder;
	
	@Value("${staffsDetails.excel}")
	private String staffExcel;
	
	@Value("${excelUpload.folder}")
	private String excelUploadfolder;
	
	@Value("${ldap.organization}")
	private String ldapOrganization;
	
	@Value("${ldap.service_username}")
	private String ldapServiceUserName;
	
	@Value("${ldap.service_password}")
	private String ldapServicePassword;

	@Value("${ldap.URI.createUser}")
	private String createUserURL;
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/erpProcessFlow", method = RequestMethod.GET)
	public ModelAndView showerpProcessFlow(ModelMap model) {
		String strView = null;
		try {
			logger.info("Excecuting showerpProcessFlow() from erpController, calling showerpProcessFlow() in erpService.java");
				strView = "erp/erpProcessFlow";	
		} catch (NullPointerException e) {
			logger.error("NullPointerException in showerpProcessFlow() from erpController : ",e);
		} catch (Exception e) {
			logger.error("Exception in showerpProcessFlow() from erpController : ",e);
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author ranita.sur
	 * changes taken on 14062017
	 */
	@RequestMapping(value="/viewDepartment", method = RequestMethod.GET)
	public String viewDepartment(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<Department> departmentList = null;
		List<Department> departmentShowList=null;
		try{
			logger.info("Excecuting viewDepartment() from erpController, calling getAllDepartment() in erpService.java");
			departmentList = erpService.getAllDepartment();					
			model.addAttribute("departmentList", departmentList);
			departmentShowList= commonService.selectAllDepartment();
			model.addAttribute("departmentShowList", departmentShowList);
		}catch(Exception e){
			logger.error("Exception in viewDepartment() from erpController : ",e);
		}
		return "erp/createAndEditDepartment";
	}
		
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param department
	 * @param sessionObject
	 * @return
	 * @author ranita.sur
	 * changes taken 14062017
	 */
	@RequestMapping(value="/submitDepartment", method = RequestMethod.POST)
	public String addDepartment(HttpServletRequest request, HttpServletResponse response, ModelMap model,
								@RequestParam("departmentName") String departmentName,
								@RequestParam("departmentShowName") String departmentShowName,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			
			Department department=new Department();
			logger.info("Excecuting addDepartment() from erpController, calling addDepartment() in erpService.java");
			department.setUpdatedBy(sessionObject.getUserId());	
			department.setDepartmentName(departmentName);
			department.setParentDepartment(departmentShowName);
			String submitResponse = erpService.addDepartment(department);
			model.addAttribute("submitResponse", submitResponse);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in addDepartment() from erpController : ",e);
		}		
		return viewDepartment(request, response, model);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param department
	 * @param sessionObject
	 * @return
	 */
	@RequestMapping(value="/editDepartment", method = RequestMethod.POST)
	public String editDepartment(HttpServletRequest request, HttpServletResponse response, ModelMap model,								
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{	
			logger.info("Excecuting editDepartment() from erpController, calling editDepartment() in erpService.java");
			String saveId=request.getParameter("saveId");
			/* modified by ranita.sur on 14092017 for showing department in pop up */
			String departmentName = request.getParameter("getDepartmentType");
			Department department = new Department();
			department.setDepartmentName(departmentName.trim().toUpperCase());
			department.setDepartmentDescription(departmentName.trim().toUpperCase());
			department.setDepartmentCode((request.getParameter("oldDepartmentNames"+saveId)).trim().toUpperCase());
			department.setUpdatedBy(sessionObject.getUserId());
			String updateResponse = erpService.editDepartment(department);
			model.addAttribute("updateResponse", updateResponse);
			/**Added by @author Saif.Ali
			 * Date- 12/03/2018
			 * Used to insert the information into the activity_log table*/
			if(updateResponse.equalsIgnoreCase("Success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT DEPARTMENT");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor((request.getParameter("oldDepartmentNames"+saveId)));
				updateLog.setDescription("Department ::  " + (request.getParameter("oldDepartmentNames"+saveId)) + " updated to Department Name :: " + departmentName);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 246 :: ERPController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/**Addition ends*/
		}catch(Exception e){
			logger.error("Exception occured in editDepartment() from erpController : ",e);
		}		
		return viewDepartment(request, response, model);
	}
	

/**
	 * This method is used to Open designation page with Designation List
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewDesignation", method = RequestMethod.GET)
	public String viewDesignation(HttpServletRequest request, HttpServletResponse response, ModelMap model) {			
		List<Designation> designationList=null;
		List<DesignationType> designationTypeList=null;		
		try {
			logger.info("Excecuting viewDesignation() from erpController, calling getAllDesignation() in erpService.java");
			designationTypeList= erpService.getAllDesignationType();
			model.addAttribute("designationTypeList", designationTypeList);
			designationList = erpService.getAllDesignation();							
			model.addAttribute("designationList", designationList);			
		} catch (NullPointerException e) {
			logger.error("Exception occured in viewDesignation() from erpController : ",e);
		} catch (Exception e) {
			logger.error("Exception occured in viewDesignation() from erpController : ",e);
		}
		return "erp/designation";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param designation
	 * @param sessionObject
	 * @return
	 */
	@RequestMapping(value="/submitDesignation", method = RequestMethod.POST)
	public String addDesignation(HttpServletRequest request, HttpServletResponse response, ModelMap model,
									@ModelAttribute("Designation") Designation designation,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("Excecuting addDesignation() from erpController, calling addDesignation() in erpService.java");			
			designation.setUpdatedBy(sessionObject.getUserId());			
			String submitResponse = erpService.addDesignation(designation);			
			model.addAttribute("submitResponse", submitResponse);
		}catch(Exception e){
			logger.error("Exception occured in addDesignation() from erpController : ",e);
		}		
		return viewDesignation(request, response, model);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param designation
	 * @param sessionObject
	 * @return
	 */
	@RequestMapping(value="/editDesignation", method = RequestMethod.POST)
	public String editDesignation(HttpServletRequest request, HttpServletResponse response, ModelMap model,									
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("Excecuting editDesignation() from erpController, calling editDesignation() in erpService.java");
			String saveId=request.getParameter("saveId");	
			//modified by ranita.sur on 11092017
			String designationName = request.getParameter("getNewDesignation").trim().toUpperCase();
			//System.out.println("LN 304 ::" +designationName);
			String designationTypeName =  request.getParameter("getNewDesignationType").trim().toUpperCase();
			//System.out.println("LN 306 ::" +designationTypeName);
			Designation designation = new Designation();
			DesignationType designationType = new DesignationType();
			designationType.setDesignationTypeName(designationTypeName);			
			designation.setDesignationName(designationName);
			designation.setDesignationCode((request.getParameter("oldDesignationNames"+saveId)).trim().toUpperCase());
			designation.setUpdatedBy(sessionObject.getUserId());
			designation.setDesignationType(designationType);	
			String updateResponse = erpService.editDesignation(designation);
			model.addAttribute("updateResponse", updateResponse);
			/**Added by @author Saif.Ali
			 * Date-13-03-2018*/
			String oldDesignation = request.getParameter("oldDesignationNames"+saveId);
			String oldDesignationType = request.getParameter("designationTypeDesignationTypeName"+saveId);
			String newDesignation = request.getParameter("getNewDesignation");
			String newDesignationType = request.getParameter("getNewDesignationType");
			String description = "" ;
			if(updateResponse.equalsIgnoreCase("Success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT DESIGNATION DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("ERP");
				updateLog.setUpdatedFor("Designation :: " + oldDesignation + " and Designation Type :: " + oldDesignationType);
				if(!(oldDesignation.equalsIgnoreCase(newDesignation)))
				{
					description = description + ("Designation :: " + oldDesignation + " is now updated to new Designation :: " + newDesignation + " ; ");
				}
				if(!(oldDesignationType.equalsIgnoreCase(newDesignationType)))
				{
					description = description + ("Designation Type :: " + oldDesignationType + " is now updated to new Designation Type :: " + newDesignationType + " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 366 :: ERPController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		}catch(Exception e){
			logger.error("Exception occured in editDesignation() from erpController : ",e);
		}		
		return viewDesignation(request, response, model);
	}		
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/viewDesignationType", method = RequestMethod.GET)
	public String viewDesignationType(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<DesignationType> designationTypeList = null;
		try{
			logger.info("Excecuting viewDesignationType() from erpController, calling getAllDesignationType() in erpService.java");
			designationTypeList = erpService.getAllDesignationType();
			model.addAttribute("designationTypeList", designationTypeList);
		}catch(Exception e){
			logger.error("Exception in viewJobType() from erpController : ",e);
		}		
		return "erp/designationType";
	}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param jobType
	 * @param sessionObject
	 * @return
	 */
	@RequestMapping(value="/submitDesignationType", method = RequestMethod.POST)
	public String addDesignationType(HttpServletRequest request, HttpServletResponse response, ModelMap model,
									@ModelAttribute("DesignationType") DesignationType designationType,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("Excecuting addJobType() from erpController, calling submitDesignationType() in erpService.java");
			designationType.setUpdatedBy(sessionObject.getUserId());			
			String submitResponse = erpService.addDesignationType(designationType);
			model.addAttribute("submitResponse", submitResponse);
		}catch(Exception e){
			logger.error("Exception in submitDesignationType() from erpController : ",e);
		}		
		return viewDesignationType(request, response, model);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param jobType
	 * @param sessionObject
	 * @return
	 */
	@RequestMapping(value="/editDesignationType", method = RequestMethod.POST)
	public String editDesignationType(HttpServletRequest request, HttpServletResponse response, ModelMap model,									
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("Excecuting editDesignationType() from erpController, calling editDesignationType() in erpService.java");
			String saveId=request.getParameter("saveId");
			//modified by ranita.sur on 11092017
			String designationType = request.getParameter("getDesignationType");
			DesignationType desginationType = new DesignationType();
			desginationType.setDesignationTypeName(designationType.trim().toUpperCase());
			desginationType.setDesignationTypeDesc(designationType.trim().toUpperCase());
			desginationType.setDesignationTypeCode((request.getParameter("oldDesignationTypeNames"+saveId)).trim().toUpperCase());
			desginationType.setUpdatedBy(sessionObject.getUserId());			
			String updateResponse = erpService.editDesignationType(desginationType);
			model.addAttribute("updateResponse", updateResponse);
			/**Added by @author Saif.Ali
			 * 26-03-2018*/
			/**Added by @author Saif.Ali
			 * Date-13-03-2018*/
			if(updateResponse.equalsIgnoreCase("Success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT DESIGNATION TYPE DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("ERP");
				updateLog.setUpdatedFor(request.getParameter("oldDesignationTypeNames"+saveId));
				updateLog.setDescription("Designation Type :: " + request.getParameter("oldDesignationTypeNames"+saveId) + " is updated to new Designation Type :: " + designationType);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 429 :: ERPController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
			/***/
		}catch(Exception e){
			logger.error("Exception occured in editDesignationType() from erpController : ",e);
		}		
		return viewDesignationType(request, response, model);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/viewJobType", method = RequestMethod.GET)
	public String viewJobType(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<JobType> jobTypeList = null;
		try{
			logger.info("Excecuting viewJobType() from erpController, calling getAllJobType() in erpService.java");
			jobTypeList = erpService.getAllJobType();					
			model.addAttribute("jobTypeList", jobTypeList);
		}catch(Exception e){
			logger.error("Exception in viewJobType() from erpController : ",e);
		}		
		return "erp/jobType";
	}
		
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param jobType
	 * @param sessionObject
	 * @return
	 */
	@RequestMapping(value="/submitJobType", method = RequestMethod.POST)
	public String addJobType(HttpServletRequest request, HttpServletResponse response, ModelMap model,
									@ModelAttribute("JobType") JobType jobType,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("Excecuting addJobType() from erpController, calling addJobType() in erpService.java");
			jobType.setUpdatedBy(sessionObject.getUserId());			
			String submitResponse = erpService.addJobType(jobType);
			model.addAttribute("submitResponse", submitResponse);
		}catch(Exception e){
			logger.error("Exception in addJobType() from erpController : ",e);
		}		
		return viewJobType(request, response, model);
	}		
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param jobType
	 * @param sessionObject
	 * @return
	 */
	@RequestMapping(value="/editJobType", method = RequestMethod.POST)
	public String editJobType(HttpServletRequest request, HttpServletResponse response, ModelMap model,									
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("Excecuting editJobType() from erpController, calling editJobType() in erpService.java");
			String saveId=request.getParameter("saveId");
			//modified by ranita.sur on 11092017
			String newJob =request.getParameter("getJobType");
			JobType jobType = new JobType();
			jobType.setJobTypeName(newJob.trim().toUpperCase());
			jobType.setJobTypeDesc(newJob.trim().toUpperCase());
			jobType.setJobTypeCode((request.getParameter("oldJobTypeNames"+saveId)).trim().toUpperCase());
			jobType.setUpdatedBy(sessionObject.getUserId());			
			String updateResponse = erpService.editJobType(jobType);
			model.addAttribute("updateResponse", updateResponse);
			/**Added by @author Saif.Ali
			 * Date-27-03-2018*/
			String oldjobType= (request.getParameter("oldJobTypeNames"+saveId)).trim().toUpperCase();
			String description = "";
			if(updateResponse.equalsIgnoreCase("Success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT JOB TYPE DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("ERP");
				updateLog.setUpdatedFor("Job Type :: " + newJob);
				if(!(oldjobType.equalsIgnoreCase(newJob)))
				{
					description =description + ("Job Type :: " + oldjobType + " is updated to new Job type :: " + newJob);
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 565 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		}catch(Exception e){
			logger.error("Exception occured in editJobType() from erpController : ",e);
		}		
		return viewJobType(request, response, model);
	}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/viewDesignationLevel", method = RequestMethod.GET)
	public String viewDesignationLevel(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<DesignationLevel> designationLevelList = null;
		try{
			logger.info("Excecuting viewDesignationLevel() from erpController, calling viewDesignationLevel() in erpService.java");
			designationLevelList = erpService.getAllDesignationLevel();					
			model.addAttribute("designationLevelList", designationLevelList);
		}catch(Exception e){
			logger.error("Exception in viewDesignationLevel() from erpController : ",e);
		}		
		return "erp/designationLevel";
	}
		
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param jobType
	 * @param sessionObject
	 * @return
	 */
	@RequestMapping(value="/submitDesignationLevel", method = RequestMethod.POST)
	public String addDesignationLevel(HttpServletRequest request, HttpServletResponse response, ModelMap model,
									@ModelAttribute("DesignationLevel") DesignationLevel designationLevel,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("Excecuting addDesignationLevel() from erpController, calling addDesignationLevel() in erpService.java");
			designationLevel.setUpdatedBy(sessionObject.getUserId());			
			String submitResponse = erpService.addDesignationLevel(designationLevel);
			model.addAttribute("submitResponse", submitResponse);
		}catch(Exception e){
			logger.error("Exception in addDesignationLevel() from erpController : ",e);
		}		
		return viewDesignationLevel(request, response, model);
	}		
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param jobType
	 * @param sessionObject
	 * @return
	 */
	@RequestMapping(value="/editDesignationLevel", method = RequestMethod.POST)
	public String editDesignationLevel(HttpServletRequest request, HttpServletResponse response, ModelMap model,									
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("Excecuting editDesignationLevel() from erpController, calling editDesignationLevel() in erpService.java");
			String saveId=request.getParameter("saveId");
			//modified by ranita.sur on 11092017
			String newDesignationLevel = request.getParameter("getNewDesignationLevel");
			DesignationLevel designationLevel = new DesignationLevel();
			designationLevel.setDesignationLevelName(newDesignationLevel.trim().toUpperCase());
			designationLevel.setDesignationLevelDesc(newDesignationLevel.trim().toUpperCase());
			designationLevel.setDesignationLevelCode((request.getParameter("oldDesignationLevelNames"+saveId)).trim().toUpperCase());
			designationLevel.setUpdatedBy(sessionObject.getUserId());			
			String updateResponse = erpService.editDesignationLevel(designationLevel);
			model.addAttribute("updateResponse", updateResponse);
			/**Added by @author Saif.Ali
			 * Date-27-03-2018*/
			String oldDesignationLevel = request.getParameter("oldDesignationLevelNames"+saveId);
			String description = "" ;
			if(updateResponse.equalsIgnoreCase("Success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT DESIGNATION LEVEL DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("ERP");
				updateLog.setUpdatedFor("Designation Level :: " + newDesignationLevel);
				if(!(oldDesignationLevel.equalsIgnoreCase(newDesignationLevel)))
				{
					description= description + ("Designation Level :: " + oldDesignationLevel + " updated to new Designation Level :: " + newDesignationLevel + " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 639 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		}catch(Exception e){
			logger.error("Exception occured in editDesignationLevel() from erpController : ",e);
		}		
		return viewDesignationLevel(request, response, model);
	}	
	
	/**
	 * 
	 * @param model
	 * @param employee
	 * @return
	 */
	@RequestMapping(value = "/viewEmployeeDetails", method = RequestMethod.GET)
	public String viewEmployeeDetails(ModelMap model, Employee employee) {				
		try {
			logger.info("Excecuting viewEmployeeDetails() from erpController");					
			employee = erpService.getEmployeeFormBasicData();
			List<Country> countryList=commonService.getCountryList();
			model.addAttribute("countryList", countryList);
			
			List<State> stateList=commonService.getAllStatesInIndia("IND");
			model.addAttribute("stateList", stateList);
			model.addAttribute("employee", employee);
		}catch (Exception e) {
			logger.error("Exception occured in viewEmployeeDetails() from erpController : ",e);
		}
		return "erp/addEmployeeDetails";
	}	
	
	/**
	 * 
	 * @param strUserId
	 * @return
	 */
	@RequestMapping(value = "/serverSideValidationOfUserId", method = RequestMethod.GET)
	public @ResponseBody
	String serverSideValidationOfUserId(@RequestParam("userId") String strUserId) {
		String userId = "";
		userId = erpService.serverSideValidationOfUserId(strUserId);
		return (new Gson().toJson(userId));
	}
	
	/**
	 * 
	 * @param strUserId
	 * @return
	 */
	@RequestMapping(value = "/serverSideValidationOfEmployeeCode", method = RequestMethod.GET)
	public @ResponseBody
	String serverSideValidationOfEmployeeCode(@RequestParam("employeeCode") String employeeCode) {
		String existEmployeeCode = "";		
		existEmployeeCode = erpService.serverSideValidationOfEmployeeCode(employeeCode);
		return (new Gson().toJson(existEmployeeCode));
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employee
	 * @param othersExamName
	 * @param othersSpecialization
	 * @param othersSchoolCollege
	 * @param othersBoardUniversity
	 * @param othersMarks
	 * @param othersPassingYear
	 * @param publicationName
	 * @param dateOfPublication
	 * @param coPublisher
	 * @param publicationDesc
	 * @param childName
	 * @param childGender
	 * @param childDateOfBirth
	 * @param organizationName
	 * @param fromDate
	 * @param toDate
	 * @param organizationContactNo
	 * @param organizationWebSite
	 * @param sessionObject
	 * @return
	 * 
	 * modified by ranita
	 * 03072017
	 */
	
	@RequestMapping(value="/submitEmployeeDetails", method = RequestMethod.POST)
	public String submitEmployeeDetails(HttpServletRequest request, HttpServletResponse response, 
									ModelMap model,															
									Employee employee,
									Ledger ledger,
									@RequestParam(required = false, value = "othersExamName") String[] othersExamName,
									@RequestParam(required = false, value = "othersSpecialization") String[] othersSpecialization,
									@RequestParam(required = false, value = "othersSchoolCollege") String[] othersSchoolCollege,
									@RequestParam(required = false, value = "othersBoardUniversity") String[] othersBoardUniversity,
									@RequestParam(required = false, value = "othersMarks") String[] othersMarks,
									@RequestParam(required = false, value = "othersPassingYear") String[] othersPassingYear,
									
									@RequestParam(required = false, value = "publicationName") String[] publicationName,
									@RequestParam(required = false, value = "dateOfPublication") String[] dateOfPublication,
									@RequestParam(required = false, value = "coPublisher") String[] coPublisher,
									@RequestParam(required = false, value = "publicationDesc") String[] publicationDesc,
									
									@RequestParam(required = false, value = "childName") String[] childName,
									@RequestParam(required = false, value = "childGender") String[] childGender,
									@RequestParam(required = false, value = "childDateOfBirth") String[] childDateOfBirth,									
									
									@RequestParam(required = false, value = "organizationName") String[] organizationName,
									@RequestParam(required = false, value = "fromDate") String[] fromDate,
									@RequestParam(required = false, value = "toDate") String[] toDate,
									@RequestParam(required = false, value = "organizationContactNo") String[] organizationContactNo,
									@RequestParam(required = false, value = "organizationWebSite") String[] organizationWebSite,
									
									@RequestParam(required = false, value = "nomineeName") String[] nomineeName,
									@RequestParam(required = false, value = "relationship") String[] relationship,
									@RequestParam(required = false, value = "nomineePercent") String[] nomineePercent,									
									
									@RequestParam(required = false, value = "subject") String[] subject,
									@RequestParam(required = false, value = "venue") String[] venue,
									@RequestParam(required = false, value = "organizedBy") String[] organizedBy,
									@RequestParam(required = false, value = "trainingFromDate") String[] trainingFromDate,
									@RequestParam(required = false, value = "trainingToDate") String[] trainingToDate,
									@RequestParam(required = false, value = "duration") String[] duration,
									
									@RequestParam(required = false, value = "awardName") String[] awardName,
									@RequestParam(required = false, value = "presentedBy") String[] presentedBy,
									@RequestParam(required = false, value = "presentedOn") String[] presentedOn,
									
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			System.out.println("Hiiii");
			logger.info("Excecuting submitEmployeeDetails() from erpController, calling submitEmployeeDetails() in erpService.java");

			employee.setUpdatedBy(sessionObject.getUserId());
			employee.getAddress().setUpdatedBy(sessionObject.getUserId());
			ledger.setUpdatedBy(sessionObject.getUserId());
			List<OtherQualification> otherQualificationDetailsList = new ArrayList<OtherQualification>();
			
			if(employee.getQualificationList() != null && employee.getQualificationList().size() != 0){
				for(Qualification qualification : employee.getQualificationList()){					
					qualification.setUpdatedBy(sessionObject.getUserId());
				}
			}
			
			if (othersExamName != null && othersExamName.length != 0) {
				for (int i = 0; i < othersExamName.length; i++) {
					OtherQualification oqd = new OtherQualification();
					oqd.setOthersExamName(othersExamName[i]);
					oqd.setUpdatedBy(sessionObject.getUserId());
					if (othersSchoolCollege != null && othersSchoolCollege.length != 0) {
						oqd.setOthersSchoolCollege(othersSchoolCollege[i]);
					}
					if (othersBoardUniversity != null && othersBoardUniversity.length != 0) {
						oqd.setOthersBoardUniversity(othersBoardUniversity[i]);
					}
					if (othersMarks != null && othersMarks.length != 0) {
						oqd.setOthersMarks(othersMarks[i]);
					}
					if (othersSpecialization != null && othersSpecialization.length != 0) {
						oqd.setOthersSpecialization(othersSpecialization[i]);
					}
					if (othersPassingYear != null && othersPassingYear.length != 0) {
						oqd.setOthersPassingYear(othersPassingYear[i]);
					}
					otherQualificationDetailsList.add(oqd);
				}
			}
			
			List<Publication> publicationList = new ArrayList<Publication>();
			if (publicationName != null && publicationName.length != 0) {				
				for (int i = 0; i < publicationName.length; i++) {
					Publication publication = new Publication();
					publication.setUpdatedBy(sessionObject.getUserId());
					if (dateOfPublication != null && dateOfPublication.length != 0) {
						if (dateOfPublication[i] != null && dateOfPublication[i].length() != 0) {
							publication.setDateOfPublication(dateOfPublication[i]);
						}
					}
					if (coPublisher != null && coPublisher.length != 0) {
						if (coPublisher[i] != null && coPublisher[i].length() != 0) {
							publication.setCoPublisher(coPublisher[i]);
						}
					}
					if (publicationDesc != null && publicationDesc.length != 0) {
						if (publicationDesc[i] != null && publicationDesc[i].length() != 0) {
							publication.setPublicationDesc(publicationDesc[i]);
						}
					}
					publication.setPublicationName(publicationName[i]);
					publicationList.add(publication);
				}					
			}
			
			List<EmployeeDependent> employeeDependentList = new ArrayList<EmployeeDependent>();
			if(childName != null && childName.length != 0){
				for(int i = 0; i < childName.length; i++){
					EmployeeDependent eDependent = new EmployeeDependent();
					eDependent.setUpdatedBy(sessionObject.getUserId());
					if(childName != null && childName.length != 0){
						if(childName[i] != null && childName[i].length()!=0){
							eDependent.setChildName(childName[i]);
						}
					}
					if(childGender != null && childGender.length != 0){
						if(childGender[i] != null && childGender[i].length()!=0){
							eDependent.setChildGender(childGender[i]);
						}
					}
					if(childDateOfBirth != null && childDateOfBirth.length != 0){
						if(childDateOfBirth[i] != null && childDateOfBirth[i].length()!=0){
							eDependent.setChildDOB(childDateOfBirth[i]);
						}
					}
					employeeDependentList.add(eDependent);
				}
			}
			
			List<Organization> organizationList = new ArrayList<Organization>();
			if(organizationName != null && organizationName.length != 0){
				for(int i = 0; i < organizationName.length; i++){
					Organization organization = new Organization();
					organization.setUpdatedBy(sessionObject.getUserId());
					if(organizationName != null && organizationName.length != 0){
						if(organizationName[i] != null && organizationName[i].length()!=0){
							organization.setOrganizationName(organizationName[i]);
						}
					}
					if(fromDate != null && fromDate.length != 0){
						if(fromDate[i] != null && fromDate[i].length()!=0){
							organization.setFromDate(fromDate[i]);
						}
					}
					if(toDate != null && toDate.length != 0){
						if(toDate[i] != null && toDate[i].length()!=0){
							organization.setToDate(toDate[i]);
						}
					}
					
					if(organizationContactNo != null && organizationContactNo.length != 0){
						if(organizationContactNo[i] != null && organizationContactNo[i].length()!=0){
							organization.setOrganizationContactNo(organizationContactNo[i]);
						}
					}
					if(organizationWebSite != null && organizationWebSite.length != 0){
						if(organizationWebSite[i] != null && organizationWebSite[i].length()!=0){
							organization.setOrganizationWebSite(organizationWebSite[i]);
						}
					}
					organizationList.add(organization);
				}
			}			
			
			List<NomineeDetails> nomineeDetailsList = new ArrayList<NomineeDetails>();
			if (nomineeName != null && nomineeName.length != 0 && relationship != null && relationship.length != 0 && nomineePercent !=null && nomineePercent.length!= 0) {				
				for (int i = 0; i < nomineeName.length; i++) {
					NomineeDetails nomineeDetails = new NomineeDetails();
					nomineeDetails.setUpdatedBy(sessionObject.getUserId());
					if (nomineeName != null && nomineeName.length != 0) {
						if (nomineeName[i] != null && nomineeName[i].length() != 0) {
							nomineeDetails.setNomineeName(nomineeName[i]);
						}
					}
					if (relationship != null && relationship.length != 0) {
						if (relationship[i] != null && relationship[i].length() != 0) {
							 nomineeDetails.setRelationship(relationship[i]);
						}
					}
					if (nomineePercent != null && nomineePercent.length != 0) {
						if (nomineePercent[i] != null && nomineePercent[i].length() != 0) {
							nomineeDetails.setPercentage(Double.parseDouble(nomineePercent[i]));
						}
					}					
					nomineeDetailsList.add(nomineeDetails);					
				}					
			}
			
			List<WorkShopAndTraining> workShopAndTrainingList = new ArrayList<WorkShopAndTraining>();
			if (subject != null && subject.length != 0 && venue != null && venue.length != 0 && organizedBy != null && organizedBy.length != 0 && duration != null && duration.length != 0) {				
				for (int i = 0; i < subject.length; i++) {
					WorkShopAndTraining workShopAndTraining = new WorkShopAndTraining();
					workShopAndTraining.setUpdatedBy(sessionObject.getUserId());
					if (subject != null && subject.length != 0) {
						if (subject[i] != null && subject[i].length() != 0) {
							workShopAndTraining.setSubject(subject[i]);
						}
					}
					if (venue != null && venue.length != 0) {
						if (venue[i] != null && venue[i].length() != 0) {
							workShopAndTraining.setVenue(venue[i]);
						}
					}
					if (organizedBy != null && organizedBy.length != 0) {
						if (organizedBy[i] != null && organizedBy[i].length() != 0) {
							workShopAndTraining.setOrganizedBy(organizedBy[i]);
						}
					}
					if (duration != null && duration.length != 0) {
						if (duration[i] != null && duration[i].length() != 0) {
							workShopAndTraining.setDuration(duration[i]);
						}
					}
					if (trainingFromDate != null && trainingFromDate.length != 0) {
						if (trainingFromDate[i] != null && trainingFromDate[i].length() != 0) {
							workShopAndTraining.setTrainingFromDate(trainingFromDate[i]);
						}
					}
					if (trainingToDate != null && trainingToDate.length != 0) {
						if (trainingToDate[i] != null && trainingToDate[i].length() != 0) {
							workShopAndTraining.setTrainingToDate(trainingToDate[i]);
						}
					}
					workShopAndTrainingList.add(workShopAndTraining);					
				}					
			}

			List<AwardsAndRecognization> awardsAndRecognizationList = new ArrayList<AwardsAndRecognization>();
			if (awardName != null && awardName.length != 0 && presentedBy != null && presentedBy.length != 0 && presentedOn != null && presentedOn.length != 0) {				
				for (int i = 0; i < awardName.length; i++) {
					AwardsAndRecognization awardsAndRecognization = new AwardsAndRecognization();
					awardsAndRecognization.setUpdatedBy(sessionObject.getUserId());
					if (awardName != null && awardName.length != 0) {
						if (awardName[i] != null && awardName[i].length() != 0) {
							awardsAndRecognization.setAwardName(awardName[i]);
						}
					}
					if (presentedBy != null && presentedBy.length != 0) {
						if (presentedBy[i] != null && presentedBy[i].length() != 0) {
							awardsAndRecognization.setPresentedBy(presentedBy[i]);
						}
					}
					if (presentedOn != null && presentedOn.length != 0) {
						if (presentedOn[i] != null && presentedOn[i].length() != 0) {
							awardsAndRecognization.setPresentedOn(presentedOn[i]);
						}
					}					
					awardsAndRecognizationList.add(awardsAndRecognization);					
				}					
			}
			
			employee.setNomineeDetailsList(nomineeDetailsList);
			employee.setWorkShopAndTrainingList(workShopAndTrainingList);
			employee.setAwardsAndRecognizationList(awardsAndRecognizationList);
			employee.setPublicationList(publicationList);
			employee.setEmployeeDependentList(employeeDependentList);
			employee.setOtherQualificationList(otherQualificationDetailsList);
			employee.setOrganizationList(organizationList);
			
			
			/**new code addded for save picture in external repository*/
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			File directory = new File(repository);
			boolean isExists = directory.exists();
			String insertStatus = null;
			if(isExists == true){
				Attachment attachment = new Attachment();
				attachment.setStorageRootPath(repository);
				attachment.setFolderName(employeeDocFolderPath);			
				if(employee.getResource()!=null && employee.getResource().getUploadFile()!=null){
					employee.getResource().getUploadFile().setAttachment(attachment);
				}
				/**new code ended*/
				
				String submitResponse = erpService.submitEmployeeDetails(employee,ledger);
				model.addAttribute("submitResponse", submitResponse);
				if(submitResponse.equals("Success")){
					String description = "New Staff Was Inserted With User ID:"+employee.getResource().getUserId()+" Of Employee Type "+employee.getEmployeeType().getEmployeeTypeName()+" WIth Designation "+employee.getDesignation().getDesignationName();			
					callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "INSERT", description);
					
					// register the user id in JAMES
					
					/*TelnetClient telnetClient = new TelnetClient("localhost");
					String userIdRegisterStatus = telnetClient.addUser(employee.getResource().getUserId(), employee.getResource().getUserId());
					model.addAttribute("userIdRegisterStatus", userIdRegisterStatus);*/
					
					// register user id in LDAP
					
					JSONObject ldapJsonObj = new JSONObject();
					
					ldapJsonObj.put("userName",employee.getResource().getUserId());
					ldapJsonObj.put("password","welcome");
					ldapJsonObj.put("organization",ldapOrganization);
					ldapJsonObj.put("firstName", employee.getResource().getFirstName());
					ldapJsonObj.put("lastName", employee.getResource().getLastName());
					ldapJsonObj.put("serviceUserName",ldapServiceUserName);
					ldapJsonObj.put("servicePassword",ldapServicePassword);
					
					final String ldapURI = createUserURL;
					System.out.println("URI:::"+ldapURI);
					System.out.println("JSON obj:"+ldapJsonObj.toString());
					URL ldapURL = new URL(ldapURI);
					HttpURLConnection ldapConnection = null;
					OutputStreamWriter ldapOut = null;
					String ldap_json_response = "";
					InputStreamReader ldapIn = null;
					BufferedReader ldapBr = null;
					
					ldapConnection = (HttpURLConnection)ldapURL.openConnection();
					ldapConnection.setDoOutput(true);
					ldapConnection.setRequestProperty("Content-Type", "application/json");
					ldapConnection.setConnectTimeout(5000);
					ldapConnection.setReadTimeout(5000);
					ldapConnection.setRequestMethod("POST");
					ldapOut = new OutputStreamWriter(ldapConnection.getOutputStream());
					ldapOut.write(ldapJsonObj.toString());
					ldapOut.close();
								
					ldapIn = new InputStreamReader(ldapConnection.getInputStream());
					ldapBr = new BufferedReader(ldapIn);
					String text = "";
					while((text = ldapBr.readLine())!= null){
							ldap_json_response += text;
					}
					System.out.println("JSON RESPONSE: "+ldap_json_response);
					
					JSONObject ldapResponseObject = new JSONObject(ldap_json_response);
					String message = (String)ldapResponseObject.get("message");
					if(message.equalsIgnoreCase("success")){
						System.out.println("The LDAP User Creation was successful");
						logger.info("The LDAP User Creation was successful");
					}else{
						System.out.println("The LDAP User Creation failed");
						logger.info("The LDAP User Creation was a failure");
					}
					//LDAP ENDS
				}
			}else{
				System.out.println("Repository not found");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured in submitEmployeeDetails() from ERPController : ",e);
		}
		/* modified by sourav.bhadra on 23-03-2018 */
		Employee emp = null;
		return viewEmployeeDetails(model,emp);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param parameters
	 * @return
	 */
	@RequestMapping(value = "/staffList", method = RequestMethod.GET)
	public String staffList(HttpServletRequest request, HttpServletResponse response, ModelMap model,Map<String, Object> parameters) {
		String strResourceType = request.getParameter("resourceType");
		try {			
			logger.info("Executing ERPController staffList() method: calling staffList in erpService.java");
			parameters.put("ResourceType", strResourceType);
			List<Employee> staffList = new ArrayList<Employee>();
			staffList = erpService.getStaffList(parameters);
			model.addAttribute("staffList", staffList);
		} catch (NullPointerException e) {
			logger.error("Error in ERPController listStaff() method for NullPointerException: ", e);
		} catch (Exception e) {
			logger.error("Error in ERPController listStaff() method for Exception: ", e);
		}
		return "erp/staffList";
	}	
		
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param resource
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewEditStaffDetails", method = RequestMethod.GET)
	public ModelAndView viewEditStaffDetails(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
			Employee employee,
			@RequestParam(required = false, value = "userId") String userId) {
		logger.info("In viewEditStaffDetails() method of ERPController: ");
		String strView = null;
		try {
			Resource resource = new Resource();
			resource.setUserId(userId);
			employee.setResource(resource);
			Employee employeeDetails = erpService.getEmployeeDetails(employee);
			//System.out.println(employeeDetails.getEmployeeType().getEmployeeTypeCode()+"***"+employeeDetails.getEmployeeType().getEmployeeTypeName());
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			List<Attachment> previousYearDocAttachmentList = new ArrayList<Attachment>();
			List<Attachment> publicationDocAttachmentList = new ArrayList<Attachment>();
			
			if(employeeDetails != null ){
				/*String path = rootPath + employeeDocFolderPath +employeeDetails.getResource().getUserId()+"/qualification_doc/";*/
				/**this code is for fetch qualification related files from external repository*/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				String path = repository+"/"+ employeeDocFolderPath +employeeDetails.getResource().getUserId()+"/qualification_doc/";
				/**new code ended*/
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
				/*String path = rootPath + employeeDocFolderPath +employeeDetails.getResource().getUserId()+"/previous_organization_doc/";*/
				
				/**this code is for fetch previous organization doc files from external repository*/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				String path = repository+"/"+ employeeDocFolderPath +employeeDetails.getResource().getUserId()+"/previous_organization_doc/";
				/**new code ended*/
				
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
							attch.setStorageRootPath(path);
							attch.setUpdatedBy(employeeDetails.getResource().getUserId());
							
							previousYearDocAttachmentList.add(attch);					 				 
						} 
					}				
				}
			}
			
			if(employeeDetails != null ){
				/*String path = rootPath + employeeDocFolderPath +employeeDetails.getResource().getUserId()+"/publication_doc/";*/
				
				/**this code is for fetch publication doc files from external repository*/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				String path = repository+"/"+ employeeDocFolderPath +employeeDetails.getResource().getUserId()+"/publication_doc/";
				/**new code ended*/
				
				
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
							attch.setUpdatedBy(employeeDetails.getResource().getUserId());
							publicationDocAttachmentList.add(attch);					 				 
						} 
					}				
				}
			}
			if(null != previousYearDocAttachmentList && previousYearDocAttachmentList.size()!= 0){
				employeeDetails.setPreviousDocumentsAttachmentList(previousYearDocAttachmentList);	
				
			}
			if(null != publicationDocAttachmentList && publicationDocAttachmentList.size()!= 0){
				employeeDetails.setPublicationDocAttachmentList(publicationDocAttachmentList);	
				
			}
			employee = erpService.getEmployeeFormBasicData();			
			List<Country> countryList=commonService.getCountryList();
			model.addAttribute("countryList", countryList);
			
			List<State> stateList=commonService.getAllStatesInIndia("IND");
			model.addAttribute("stateList", stateList);
			model.addAttribute("employee", employee);			
			
			model.addAttribute("employeeDetails", employeeDetails);
			strView = "erp/editEmployeeDetails";			
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in ERPController viewEditStaffDetails() method for NullPointerException: ", e);
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in ERPController viewEditStaffDetails() method for Exception: ", e);
			
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employee
	 * @param sessionObject
	 * @return
	 */
	@RequestMapping(value = "/updateEmployeeBasicDetails", method = RequestMethod.POST)
	public ModelAndView updateEmployeeBasicDetails(
							HttpServletRequest request, HttpServletResponse response, ModelMap model,
							Employee employee,
							@ModelAttribute("sessionObject") SessionObject sessionObject){
		String updateMessage = null;
		try{
			logger.info("Executing updateEmployeeBasicDetails() method of ERPController: ");
			employee.setUpdatedBy(sessionObject.getUserId());
			String updateResponse = erpService.updateEmployeeBasicDetails(employee);
			if(updateResponse.equals("Success")){
				String description = "Employee Basic Details Was Updated for UserID: "+employee.getResource().getUserId();				
				callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
			}			
			if(updateResponse.equalsIgnoreCase("Success")){
				updateMessage = "Employee Basic Details Successfully Updated";
			}else{
				updateMessage = "Employee Basic Details Updation Failed";
			}
			model.addAttribute("updateMessage", updateMessage);
			model.addAttribute("updateResponse", updateResponse);
		}catch(Exception e){
			logger.error("Exception in updateEmployeeBasicDetails()", e);
		}
		return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employee
	 * @param sessionObject
	 * @return
	 */
//Naimisha 26052017
@RequestMapping(value = "/updateEmployeePersonalDetails", method = RequestMethod.POST)
	public ModelAndView updateEmployeePersonalDetails(
							HttpServletRequest request, HttpServletResponse response, ModelMap model,
							Employee employee,
							@ModelAttribute("sessionObject") SessionObject sessionObject){
		String updateMessage = null;
		try{
			logger.info("Executing updateEmployeePersonalDetails() method of ERPController: ");
			employee.setUpdatedBy(sessionObject.getUserId());
			//System.out.println("GEBNDERRR:"+employee.getResource().getGender());
			String updateResponse = erpService.updateEmployeePersonalDetails(employee);
			if(updateResponse.equals("Success")){				
				String description = "Employee Personal Details Was Updated for UserID: "+employee.getResource().getUserId();			
				callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
			}
			if(updateResponse.equalsIgnoreCase("Success")){
				updateMessage = "Employee Personal Details Successfully Updated";
			}else{
				updateMessage = "Employee Personal Details Updation Failed";
			}
			model.addAttribute("updateMessage", updateMessage);
			model.addAttribute("updateResponse", updateResponse);					
		}catch(Exception e){
			logger.error("Exception in updateStaffPersonalDetails()", e);
		}
		return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employee
	 * @param othersExamName
	 * @param othersSpecialization
	 * @param othersSchoolCollege
	 * @param othersBoardUniversity
	 * @param othersMarks
	 * @param othersPassingYear
	 * @param sessionObject
	 * @return
	 */
@RequestMapping(value = "/updateStaffQualificationDetails", method = RequestMethod.POST)
public ModelAndView updateStaffQualificationDetails(
						HttpServletRequest request, HttpServletResponse response, ModelMap model,
						Employee employee,
						@RequestParam(required = false, value = "othersExamName") String[] othersExamName,
						@RequestParam(required = false, value = "othersSpecialization") String[] othersSpecialization,
						@RequestParam(required = false, value = "othersSchoolCollege") String[] othersSchoolCollege,
						@RequestParam(required = false, value = "othersBoardUniversity") String[] othersBoardUniversity,
						@RequestParam(required = false, value = "othersMarks") String[] othersMarks,
						@RequestParam(required = false, value = "othersPassingYear") String[] othersPassingYear,
						@RequestParam(required = false, value = "othersQualificationType") String[] othersQualificationType,
						@ModelAttribute("sessionObject") SessionObject sessionObject){
	String updateMessage = null;
	try{
		logger.info("Executing updateStaffQualificationDetails() method of ERPController: ");
		employee.setUpdatedBy(sessionObject.getUserId());
		List<OtherQualification> otherQualificationDetailsList = new ArrayList<OtherQualification>();			
		if(employee.getQualificationList() != null && employee.getQualificationList().size() != 0){
			for(Qualification qualification : employee.getQualificationList()){
				qualification.setUpdatedBy(sessionObject.getUserId());
			}
		}			
		if (othersExamName != null && othersExamName.length != 0) {
			for (int i = 0; i < othersExamName.length; i++) {
				OtherQualification oqd = new OtherQualification();
				oqd.setOthersExamName(othersExamName[i]);
				oqd.setUpdatedBy(sessionObject.getUserId());
				if (othersSchoolCollege != null && othersSchoolCollege.length != 0) {
					oqd.setOthersSchoolCollege(othersSchoolCollege[i]);
				}
				if (othersBoardUniversity != null && othersBoardUniversity.length != 0) {
					oqd.setOthersBoardUniversity(othersBoardUniversity[i]);
				}
				if (othersMarks != null && othersMarks.length != 0) {
					oqd.setOthersMarks(othersMarks[i]);
				}
				if (othersSpecialization != null && othersSpecialization.length != 0) {
					oqd.setOthersSpecialization(othersSpecialization[i]);
				}
				if (othersPassingYear != null && othersPassingYear.length != 0) {
					oqd.setOthersPassingYear(othersPassingYear[i]);
				}
				oqd.setOtherQualificationDetailsType(othersQualificationType[0]);//Naimisha 26052017
				otherQualificationDetailsList.add(oqd);
			}
		}
		
		employee.setOtherQualificationList(otherQualificationDetailsList);
		Attachment attachment = new Attachment();
		attachment.setStorageRootPath(rootPath);
		attachment.setFolderName(employeeDocFolderPath);			
		if(employee.getResource()!=null && employee.getResource().getUploadFile()!=null){
			employee.getResource().getUploadFile().setAttachment(attachment);
		}
		String updateResponse = erpService.updateEmployeeQualificationDetails(employee);
		if(updateResponse.equals("Success")){				
			String description = "Employee Qualification Details Was Updated for UserID: "+employee.getResource().getUserId();			
			callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
		}
		if(updateResponse.equalsIgnoreCase("Success")){
			updateMessage = "Employee Qualification Details Successfully Updated";
		}else{
			updateMessage = "Employee Qualification Details Updation Failed";
		}
		model.addAttribute("updateMessage", updateMessage);
		model.addAttribute("updateResponse", updateResponse);							
	}catch(Exception e){
		logger.error("Exception in updateStaffQualificationDetails()", e);
	}
	return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employee
	 * @param sessionObject
	 * @return
	 */
@RequestMapping(value = "/updateEmployeeAddressDetails", method = RequestMethod.POST)
public ModelAndView updateEmployeeAddressDetails(
						HttpServletRequest request, HttpServletResponse response, ModelMap model,
						Employee employee,
						@ModelAttribute("sessionObject") SessionObject sessionObject){
	String updateMessage = null;
	try{
		logger.info("Executing updateEmployeeAddressDetails() method of ERPController: ");
		employee.setUpdatedBy(sessionObject.getUserId());
		String updateResponse = erpService.updateEmployeeAddressDetails(employee);
		if(updateResponse.equals("Success")){				
			String description = "Employee Address Details Was Updated for UserID: "+employee.getResource().getUserId();			
			callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
		}
		if(updateResponse.equalsIgnoreCase("Success")){
			updateMessage = "Employee Address Details Successfully Updated";
		}else{
			updateMessage = "Employee Address Details Updation Failed";
		}
		model.addAttribute("updateMessage", updateMessage);
		model.addAttribute("updateResponse", updateResponse);							
	}catch(Exception e){
		logger.error("Exception in updateEmployeeAddressDetails()", e);
	}
	return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employee
	 * @param childName
	 * @param childGender
	 * @param childDateOfBirth
	 * @param childAge
	 * @param sessionObject
	 * @return
	 */

@RequestMapping(value = "/updateEmployeeDependentsDetails", method = RequestMethod.POST)
	public ModelAndView updateEmployeeDependentsDetails(
							HttpServletRequest request, HttpServletResponse response, ModelMap model,
							Employee employee,
							@RequestParam(required = false, value = "childName") String[] childName,
							@RequestParam(required = false, value = "childGender") String[] childGender,
							@RequestParam(required = false, value = "childDateOfBirth") String[] childDateOfBirth,
							@RequestParam(required = false, value = "childAge") String[] childAge,
							@ModelAttribute("sessionObject") SessionObject sessionObject){
		String updateMessage = null;
		try{
			logger.info("Executing updateEmployeeDependentsDetails() method of ERPController: ");
			List<EmployeeDependent> employeeDependentList = new ArrayList<EmployeeDependent>();
			if(childName != null && childName.length != 0){
				for(int i = 0; i < childName.length; i++){
					EmployeeDependent eDependent = new EmployeeDependent();
					eDependent.setUpdatedBy(sessionObject.getUserId());
					if(childName != null && childName.length != 0){
						if(childName[i] != null && childName[i].length()!=0){
							eDependent.setChildName(childName[i]);
						}
					}
					if(childGender != null && childGender.length != 0){
						if(childGender[i] != null && childGender[i].length()!=0){
							eDependent.setChildGender(childGender[i]);
						}
					}
					if(childDateOfBirth != null && childDateOfBirth.length != 0){
						if(childDateOfBirth[i] != null && childDateOfBirth[i].length()!=0){
							eDependent.setChildDOB(childDateOfBirth[i]);
						}
					}
					employeeDependentList.add(eDependent);
				}
			}			
			
			if(employee.getEmployeeDependentList() != null && employee.getEmployeeDependentList().size() != 0){
				for(EmployeeDependent eDependent : employee.getEmployeeDependentList()){					
					eDependent.setUserId(employee.getResource().getUserId());					
				}				
			}			
			employee.setUpdatedBy(sessionObject.getUserId());
			employee.setEmployeeDependentList(employeeDependentList);			
			String updateResponse = erpService.updateEmployeeDependentsDetails(employee);
			if(updateResponse.equals("Success")){				
				String description = "Employee Dependents Details Was Updated for UserID: "+employee.getResource().getUserId();			
				callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
			}
			if(updateResponse.equalsIgnoreCase("Success")){
				updateMessage = "Employee Dependents Details Successfully Updated";
			}else{
				updateMessage = "Employee Dependents Details Updation Failed";
			}
			model.addAttribute("updateMessage", updateMessage);
			model.addAttribute("updateResponse", updateResponse);							
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in updateEmployeeDependentsDetails()", e);
		}
		return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
	}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employee
	 * @param sessionObject
	 * @return
	 */
@RequestMapping(value = "/updateEmployeeBankDetails", method = RequestMethod.POST)
public ModelAndView updateEmployeeBankDetails(
						HttpServletRequest request, HttpServletResponse response, ModelMap model,
						Employee employee,
						@ModelAttribute("sessionObject") SessionObject sessionObject){
	String updateMessage = null;
	try{
		logger.info("Executing updateEmployeeBankDetails() method of ERPController: ");
		employee.setUpdatedBy(sessionObject.getUserId());
		String updateResponse = erpService.updateEmployeeBankDetails(employee);
		if(updateResponse.equals("Success")){			
			String description = "Employee Bank Details Was Updated for UserID: "+employee.getResource().getUserId();			
			callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
		}
		if(updateResponse.equalsIgnoreCase("Success")){
			updateMessage = "Employee Bank Details Successfully Updated";
		}else{
			updateMessage = "Employee Bank Details Updation Failed";
		}
		model.addAttribute("updateMessage", updateMessage);
		model.addAttribute("updateResponse", updateResponse);							
	}catch(Exception e){
		logger.error("Exception in updateEmployeeBankDetails()", e);
	}
	return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
}


	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employee
	 * @param organizationName
	 * @param fromDate
	 * @param toDate
	 * @param organizationContactNo
	 * @param organizationWebSite
	 * @param sessionObject
	 * @return
	 */
@RequestMapping(value = "/updateEmployeeWorkingDetails", method = RequestMethod.POST)
public ModelAndView updateEmployeeWorkingDetails(
						HttpServletRequest request, HttpServletResponse response, ModelMap model,
						Employee employee,
						@RequestParam(required = false, value = "organizationName") String[] organizationName,
						@RequestParam(required = false, value = "fromDate") String[] fromDate,
						@RequestParam(required = false, value = "toDate") String[] toDate,
						@RequestParam(required = false, value = "organizationContactNo") String[] organizationContactNo,
						@RequestParam(required = false, value = "organizationWebSite") String[] organizationWebSite,						
						@ModelAttribute("sessionObject") SessionObject sessionObject){
	String updateMessage = null;
	try{
		logger.info("Executing updateEmployeeWorkingDetails() method of ERPController: ");
		List<Organization> organizationList = new ArrayList<Organization>();
		if(organizationName != null && organizationName.length != 0){
			for(int i = 0; i < organizationName.length; i++){
				Organization organization = new Organization();
				organization.setUpdatedBy(sessionObject.getUserId());
				if(organizationName != null && organizationName.length != 0){
					if(organizationName[i] != null && organizationName[i].length()!=0){
						organization.setOrganizationName(organizationName[i]);
					}
				}
				if(fromDate != null && fromDate.length != 0){
					if(fromDate[i] != null && fromDate[i].length()!=0){
						organization.setFromDate(fromDate[i]);
					}
				}
				if(toDate != null && toDate.length != 0){
					if(toDate[i] != null && toDate[i].length()!=0){
						organization.setToDate(toDate[i]);
					}
				}
				
				if(organizationContactNo != null && organizationContactNo.length != 0){
					if(organizationContactNo[i] != null && organizationContactNo[i].length()!=0){
						organization.setOrganizationContactNo(organizationContactNo[i]);
					}
				}
				if(organizationWebSite != null && organizationWebSite.length != 0){
					if(organizationWebSite[i] != null && organizationWebSite[i].length()!=0){
						organization.setOrganizationWebSite(organizationWebSite[i]);
					}
				}
				organizationList.add(organization);
			}
		}				
		employee.setOrganizationList(organizationList);
		employee.setUpdatedBy(sessionObject.getUserId());
		
		/***new code added for save this edited file into external repository**/
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		File directory = new File(repository);
		boolean isExists = directory.exists();
		String insertStatus = null;
		if(isExists == true){
			Attachment attachment = new Attachment();
			attachment.setStorageRootPath(repository);
			attachment.setFolderName(employeeDocFolderPath);			
			if(employee.getResource()!=null && employee.getResource().getUploadFile()!=null){
				employee.getResource().getUploadFile().setAttachment(attachment);
			}
			String updateResponse = erpService.updateEmployeeWorkingDetails(employee);
			if(updateResponse.equals("Success")){
				String description = "Employee Work Experience Details Was Updated for UserID: "+employee.getResource().getUserId();			
				callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
			}
			if(updateResponse.equalsIgnoreCase("Success")){
				updateMessage = "Employee Work Experience Details Successfully Updated";
			}else{
				updateMessage = "Employee Work Experience Details Updation Failed";
			}
			model.addAttribute("updateMessage", updateMessage);
			model.addAttribute("updateResponse", updateResponse);
		}else{
			//System.out.println("no directory found.");
		}
		/**new code ended*/
								
	}catch(Exception e){
		logger.error("Exception in updateEmployeeWorkingDetails()", e);
	}
	return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employee
	 * @param publicationName
	 * @param dateOfPublication
	 * @param coPublisher
	 * @param publicationDesc
	 * @param sessionObject
	 * @return
	 */
@RequestMapping(value = "/updateEmployeePublicationDetails", method = RequestMethod.POST)
public ModelAndView updateEmployeePublicationDetails(
						HttpServletRequest request, HttpServletResponse response, ModelMap model,
						Employee employee,
						@RequestParam(required = false, value = "publicationName") String[] publicationName,
						@RequestParam(required = false, value = "dateOfPublication") String[] dateOfPublication,
						@RequestParam(required = false, value = "coPublisher") String[] coPublisher,
						@RequestParam(required = false, value = "publicationDesc") String[] publicationDesc,						
						@ModelAttribute("sessionObject") SessionObject sessionObject){
	String updateMessage = null;
	try{
		logger.info("Executing updateEmployeePublicationDetails() method of ERPController: ");
		List<Publication> publicationList = new ArrayList<Publication>();
		if (publicationName != null && publicationName.length != 0) {				
			for (int i = 0; i < publicationName.length; i++) {
				Publication publication = new Publication();
				publication.setUpdatedBy(sessionObject.getUserId());
				if (dateOfPublication != null && dateOfPublication.length != 0) {
					if (dateOfPublication[i] != null && dateOfPublication[i].length() != 0) {
						publication.setDateOfPublication(dateOfPublication[i]);
					}
				}
				if (coPublisher != null && coPublisher.length != 0) {
					if (coPublisher[i] != null && coPublisher[i].length() != 0) {
						publication.setCoPublisher(coPublisher[i]);
					}
				}
				if (publicationDesc != null && publicationDesc.length != 0) {
					if (publicationDesc[i] != null && publicationDesc[i].length() != 0) {
						publication.setPublicationDesc(publicationDesc[i]);
					}
				}
				publication.setPublicationName(publicationName[i]);
				publicationList.add(publication);
			}					
		}			
		employee.setPublicationList(publicationList);
		employee.setUpdatedBy(sessionObject.getUserId());	
		
		/***new code added for save this edited file into external repository**/
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		File directory = new File(repository);
		boolean isExists = directory.exists();
		String insertStatus = null;
		if(isExists == true){
			Attachment attachment = new Attachment();
			attachment.setStorageRootPath(repository);
			attachment.setFolderName(employeeDocFolderPath);			
			if(employee.getResource()!=null && employee.getResource().getUploadFile()!=null){
				employee.getResource().getUploadFile().setAttachment(attachment);
			}
			String updateResponse = erpService.updateEmployeePublicationDetails(employee);
			if(updateResponse.equals("Success")){
				String description = "Employee Publication Details Was Updated for UserID: "+employee.getResource().getUserId();			
				callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
			}
			if(updateResponse.equalsIgnoreCase("Success")){
				updateMessage = "Employee Publication Details Successfully Updated";
			}else{
				updateMessage = "Employee Publication Details Updation Failed";
			}
			model.addAttribute("updateMessage", updateMessage);
			model.addAttribute("updateResponse", updateResponse);
		}else{
			//System.out.println("no directory found.");
		}
		/**new code ended*/
		
							
	}catch(Exception e){
		logger.error("Exception in updateEmployeePublicationDetails()", e);
	}
	return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
}

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */

//Naimish 27052017
	@RequestMapping(value = "/downloadEmployeeAttachments", method = RequestMethod.GET)
	public ModelAndView downloadEmployeeAttachments(HttpServletRequest request,HttpServletResponse response, ModelMap model) {		
		try {
			logger.info("Executing downloadEmployeeAttachments() method of ERPController: ");
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			
			String attachmentType = request.getParameter("attachmentType");
			String attachmentName = request.getParameter("attachmentName");
			String userId = request.getParameter("userId");		
			//String folderName = rootPath + employeeDocFolderPath +userId+"/"+attachmentType+"/";
			//repository+"/"+ employeeDocFolderPath +employeeDetails.getResource().getUserId()+"/previous_organization_doc/";
			String folderName = repository+"/"+ employeeDocFolderPath +userId+"/"+attachmentType+"/";
			String returnMessage = fileUploadDownload.downloadFile(response, folderName, attachmentName);
			if(returnMessage==null){
				model.addAttribute("ResultError", "File not available");
			}
		} catch (Exception e) {
			String error = "file  not found";
			logger.error("Exception in downloadEmployeeAttachments() method in ERPController:  ",	e);
			return new ModelAndView("common/errorpage", "ResultError", error);
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/updateEmployeeImage", method = RequestMethod.POST)
	public ModelAndView updateEmployeeImage(HttpServletRequest request, HttpServletResponse response, 
									ModelMap model,									
									Employee employee,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{	
			logger.info("Executing updateEmployeeImage() method of ERPController: ");
			employee.setUpdatedBy(sessionObject.getUserId());
			
			/***new code added for save this edited file into external repository**/
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			File directory = new File(repository);
			boolean isExists = directory.exists();
			String insertStatus = null;
			if(isExists == true){
				Attachment attachment = new Attachment();
				attachment.setStorageRootPath(repository);
				attachment.setFolderName(employeeDocFolderPath);			
				if(employee.getResource()!=null && employee.getResource().getImage().getImageData()!=null){
					employee.setAttachment(attachment);
				}
				String updateResponse = erpService.updateEmployeeImage(employee);
				if(updateResponse.equals("Success")){				
					String description = "Employee Image was Updated for UserID: "+employee.getResource().getUserId();			
					callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
				}
				if(updateResponse.equalsIgnoreCase("Success")){
					model.addAttribute("updateResponse", updateResponse);
					model.addAttribute("updateMessage", "Employee Image Successfully Updated");
				}else{
					model.addAttribute("updateResponse", updateResponse);
					model.addAttribute("updateMessage", "Employee Image Updation Failed");
				}
			}else{
				//System.out.println("no directory found.");
			}
			/**new code ended*/
					
		}catch(Exception e){
			logger.error("Exception occured in updateEmployeeImage() from ERPController : ",e);
		}		
		return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
	}
		
	@RequestMapping(value = "/downloadStaffDetailsExcel", method = RequestMethod.GET)
	public String downloadStaffDetailsExcel(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		try{
			logger.info("In downloadStaffDetailsExcel() method of ERPController");
			String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelDownloadFolder, staffExcel);
			if(returnMessage == null){
				model.addAttribute("ResultError", "File not available");
			}
		}catch(Exception e){
			logger.error("Exception in downloadStaffDetailsExcel() for download Template Excel IN ERPController", e);
		}
		return viewEmployeeDetails(model, new Employee());
	}
	
	
	@RequestMapping(value = "/uploadStaffDetailsExcel", method = RequestMethod.POST)
	public String uploadStaffDetailsExcel(@ModelAttribute("uploadFile") UploadFile uploadFile,
											HttpServletRequest request,HttpServletResponse response,
											ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("In uploadStaffDetailsExcel() of ERPController");
			String returnMessage = fileUploadDownload.uploadExcel(uploadFile,rootPath+excelUploadfolder,staffExcel);
			if(returnMessage == null){
				model.addAttribute("uploadErrorMessage", staffExcel+" upload failed.");
			}else{
				int excelDataInsertStatus = erpService.insertStaffDetailsExcelBulkData(rootPath+excelUploadfolder+staffExcel, sessionObject.getUserId());
				model.addAttribute("excelDataInsertStatus", excelDataInsertStatus);
			}
		}catch(Exception e){
			logger.error("Exception in uploadStaffDetailsExcel() to upload Template Excel IN ERPController", e);
		}
		return viewEmployeeDetails(model, new Employee());
	}
	
	//lEAVE
	
//		@RequestMapping(value = "/leaveRequest", method = RequestMethod.GET)
//		public String leaveRequest(HttpServletRequest request, 
//										 HttpServletResponse response, 
//										 ModelMap model,
//										 @ModelAttribute("sessionObject") SessionObject sessionObject){
//			try {
//				logger.info("In leaveRequest() of ERPController");			
//				List<Leave> staffLeaveDetails = erpService.getResourceLeaveDetails(sessionObject.getUserId());
//				if(staffLeaveDetails != null && staffLeaveDetails.size() != 0){		
//					model.addAttribute("staffLeaveDetails", staffLeaveDetails);	
//				}
//			} catch (Exception e) {
//				logger.error("Exception In leaveRequest() of ERPController", e);
//			}
//			return "erp/leaveRequest";
//		}

		/* Leave Application Page in Post Method */
//		@RequestMapping(value = "/leaveRequest", method = RequestMethod.POST)
//		public String requestForLeave(HttpServletRequest request,	
//											HttpServletResponse response, 
//											ModelMap model,
//											@ModelAttribute("FORM") UploadFile uploadFile,
//											@ModelAttribute("Leave") Leave leave,
//											BindingResult result,
//											@ModelAttribute("sessionObject") SessionObject sessionObject) {
//			logger.info("In requestForLeave() of ERPController");
//			try {			
//				Task task = new Task();
//				task.setLeave(leave);
//				task.setCreatedById(sessionObject.getUserId());
//				task.setSkipable(false);			
//				task.setTaskName(leave.getTitle());
//				task.setStartDate(leave.getStartDate());
//				task.setEndDate(leave.getEndDate());
//				task.setProcessStatus(leave.getDesc());
//				task.setActivationTime(leave.getLeaveType());
//				
//				Attachment attachment = new Attachment();
//				attachment.setStorageRootPath(rootPath);
//				attachment.setFolderName(employeeDocFolderPath);	
//				if(uploadFile.getFileData()!=null){
//					uploadFile.setAttachment(attachment);
//					task.setUploadFile(uploadFile);
//				}			
//				String status=erpService.startLeaveRequest(task);		
//				model.addAttribute("message", status);
//			} catch (Exception e) {
//				logger.error("Exception In requestForLeave() of ERPController", e);
//			}		
//			return leaveRequest(request, response, model, sessionObject);
//		}

		@RequestMapping(value = "/listPendingTask", method = RequestMethod.GET)
		public ModelAndView listPendingTask(HttpServletRequest request,	HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject){
			ModelAndView mav = new ModelAndView("erp/listPendingTask");
			try {
				logger.info("listPendingTask() In ERPController.java");
				List<Leave> pendingLeaveList = erpService.getPendingTaskList();
				model.addAttribute("pendingLeaveList", pendingLeaveList);
			} catch (Exception e) {
				logger.error("listPendingTask() In ERPController.java: Exception", e);
			}
			return mav;
		}		
		
		@RequestMapping(value = "/pendingLeaveDetails", method = RequestMethod.GET)
		public ModelAndView pendingLeaveDetails(HttpServletRequest request, HttpServletResponse response, ModelMap model,
											@RequestParam("leaveCode") String leaveCode,
											@ModelAttribute("sessionObject") SessionObject sessionObject){
			String strView = null;
			try {					
				Leave leave = new Leave();
				leave.setLeaveCode(leaveCode);				
				Leave leaveDetails = erpService.getPendingLeaveDetails(leave);	
				//System.out.println(" LD USERS:"+leaveDetails.getUserId());
				List<Leave> staffLeaveDetails = erpService.getResourceLeaveDetails(leaveDetails.getUserId());
				if(staffLeaveDetails != null && staffLeaveDetails.size() != 0){				
					model.addAttribute("staffLeaveDetails", staffLeaveDetails);	
				}
				model.addAttribute("leaveDetails", leaveDetails);			 
				strView = "erp/pendingLeaveDetails";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception In leaveResponse() GET of ERPController", e);
			}
			return new ModelAndView(strView);
		}
		
		@RequestMapping(value = "/leaveResponse", method = RequestMethod.POST)
		public ModelAndView leaveResponse(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("Leave") Leave leave, 
				Task task
				) {
			try {
				SessionObject sessionObject=(SessionObject) request.getSession().getAttribute("sessionObject");
				logger.info("In leaveResponse() POST of ERPController");
				leave.setUpdatedBy(sessionObject.getUserId());
				task.setTaskApprover(sessionObject.getUserId());
				task.setTaskId(leave.getLeaveId());
				task.setLeave(leave);			
				String status = erpService.insertLeaveResponse(task);
				//List<Leave> taskList = erpService.getPendingTaskList();	
				//System.out.println("TASk LISt SIZE:"+taskList.size());
				model.addAttribute("updateStatus", status);
			} catch (Exception e) {		
				e.printStackTrace();
				logger.error("Exception In leaveResponse() POST of ERPController",	e);
				
			}
			return new ModelAndView("erp/listPendingTask");
		}
		
		@RequestMapping(value = "/listTaskHistory", method = RequestMethod.GET)
		public ModelAndView listTaskHistory(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
				Task task, @ModelAttribute("sessionObject") SessionObject sessionObject) {
			ModelAndView mav = new ModelAndView("erp/listTaskHistory");
			try {
				logger.info("In listTaskHistory() of WorkFlowController");
				task.setCreatedById(sessionObject.getUserId());
				List<Task> taskList = erpService.getTaskHistoryList(task);
			} catch (Exception e) {
				logger.error("Exception In listTaskHistory() of WorkFlowController", e);
			}
			return listTaskHistoryPagination(request, response);
		}	
		
		@RequestMapping(value = "/listTaskHistoryPagination", method = RequestMethod.GET)
		public ModelAndView listTaskHistoryPagination(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mav = null;
			try {
				logger.info("In listTaskHistoryPagination() method of workFlowController");
				mav = new ModelAndView("erp/listTaskHistory");
				logger.info("In ERPController listTaskHistoryPagination() method: calling listTaskHistoryPagination(HttpServletRequest request) in ERPService.java");
				PagedListHolder<Task> pagedListHolder = erpService.getTaskHistoryListPaging(request);
				if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
					mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
					mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
					mav.addObject("total", pagedListHolder.getNrOfElements());
					mav.addObject("pagedListHolder", pagedListHolder);
				}
			} catch (Exception e) {
				logger.error("Error in ERPController listTaskHistoryPagination() method for Exception: ", e);
			}
			return mav;
		}
		
		@RequestMapping(value = "/searchOnListTaskHistory", method = RequestMethod.POST, params = "searchSubmit")
		public ModelAndView searchOnListTaskHistory(
				HttpServletRequest request, HttpServletResponse response,
				ModelMap model,
				@RequestParam(required = false, value = "query") String strKey,
				@RequestParam(required = false, value = "data") String strValue,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {		
			try {
				Task task = new Task();	
				task.setCreatedById(sessionObject.getUserId());
				logger.info("In searchOnListTaskHistory() method of WorkFlowController: ");			
				if (strKey.equalsIgnoreCase("creationDate")) {
					task.setActivationTime(strValue.trim());				
				}
				if (strKey.equalsIgnoreCase("fromDate")) {
					task.setStartDate(strValue.trim());
				}
				if (strKey.equalsIgnoreCase("toDate")) {
					task.setEndDate(strValue.trim());
				}	
				if (strKey.equalsIgnoreCase("taskStatus")) {
					task.setStatus(strValue.trim());
				}	
				erpService.getTaskHistoryList(task);					
			} catch (Exception e) {
				
				logger.error("Exception In searchOnListTaskHistory() method of ERPController: Exception",e);
			}
			return listTaskHistoryPagination(request, response);
	}
		
		@RequestMapping(value = "/listPersonalTaskHistory", method = RequestMethod.GET)
		public ModelAndView listPersonalTaskHistory(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
				Task task, @ModelAttribute("sessionObject") SessionObject sessionObject) {
			ModelAndView mav = new ModelAndView("erp/listTaskHistory");
			try {
				logger.info("In listTaskHistory() of WorkFlowController");
				task.setCreatedById(sessionObject.getUserId());
				List<Task> taskList = erpService.listPersonalTaskHistory(task);
			} catch (Exception e) {
				logger.error("Exception In listTaskHistory() of WorkFlowController", e);
			}
			return listTaskHistoryPagination(request, response);
		}
		
		/**
		 * this method get staff user id from erpServiceImpl.java and return to
		 * createRetirement.jsp
		 * 
		 * @param model
		 * @return ModelAndView
		 */
		@RequestMapping(value = "/createRetirement", method = RequestMethod.GET)
		public ModelAndView createRetirement(ModelMap model) {
			logger.info("In createRetirement() method of ERPController: ");
			try {
				logger.info("In ERPController createRetirement() method: calling getStaffIdList() method in erpServiceImpl.java");
				List<Resource> staffList = commonService.getStaffUserIdList();
				if (staffList != null && staffList.size() != 0) {
					model.addAttribute("resourceStaffUserIdList", staffList);
				}
			} catch (NullPointerException e) {
				
				logger.error("Error in ERPController createRetirement() method for NullPointerException: ", e);
			} catch (Exception e) {
				
				logger.error("Error in ERPController createRetirement() method for Exception: ",e);
			}
			return new ModelAndView("erp/createRetirement");
		}
		
		/**
		 * this Ajax call get staff username,designation,date of join from
		 * BackOfficeService.java and return to createRetirement.jsp
		 * 
		 * @param strStaffUserId
		 * @param request
		 * @param response
		 * @return String
		 */
		@RequestMapping(value = "/getStaffDetails", method = RequestMethod.GET)
		public @ResponseBody
		String getStaffDetails(@RequestParam("staffUserId") String strStaffUserId,
				HttpServletRequest request, HttpServletResponse response) {
			logger.info("In getTeacherDetails() Ajax method of BackOfficeController");
			String sm = "";
			try {
				logger.info("In ERPController getTeacherDetails() method: calling getTeacherDetails(String strTeacherId) in BackOfficeService.java");
				Employee employee = erpService.getTeacherDetails(strStaffUserId);
				if (employee != null) {
					if (employee.getEmployeeCode() == null) {
						employee.setEmployeeCode(" ");
					}
					if (employee.getResource().getFirstName() == null) {
						employee.getResource().setFirstName(" ");
					}
					if (employee.getResource().getMiddleName() == null) {
						employee.getResource().setMiddleName(" ");
					}
					if (employee.getResource().getLastName() == null) {
						employee.getResource().setLastName(" ");
					}
					if (employee.getEmployeeType().getEmployeeTypeName() == null) {
						employee.getEmployeeType().setEmployeeTypeName(" ");
					}
					if (employee.getDesignation() == null) {
						employee.getDesignation().setDesignationName(" ");
					}
					if (employee.getJobType() == null) {
						employee.getJobType().setJobTypeName(" ");
					}
					if (employee.getDateOfJoining() == null) {
						employee.setDateOfJoining(" ");
					}
					if (employee.getDateOfRetirement() == null) {
						employee.setDateOfRetirement(" ");
					}
					sm = employee.getEmployeeCode()+ "*" 
							+employee.getResource().getFirstName() + " "
							+ employee.getResource().getMiddleName() + " "
							+ employee.getResource().getLastName() + "*"
							+ employee.getEmployeeType().getEmployeeTypeName() + "*"
							+ employee.getDesignation().getDesignationName() + "*" 
							+ employee.getJobType().getJobTypeName() + "*"
							+ employee.getDateOfJoining() + "*"
							+ employee.getDateOfRetirement();
				}
			} catch (NullPointerException e) {
				logger.error(
						"Error in ERPController getStaffDetails() method for NullPointerException: ",
						e);
			} catch (Exception e) {
				logger.error(
						"Error in ERPController getStaffDetails() method for Exception: ",
						e);
			}
			return sm;
		}
		
		/**
		 * this method get details of staff information from createRetirement.jsp
		 * and call to BackOfficeServiceImpl.java for create retirement and return
		 * to createRetirement.jsp
		 * 
		 * @param model
		 * @param staff
		 * @param uploadFile
		 * @return ModelAndView
		 */

	@RequestMapping(value = "/submitStaffRetirement", method = RequestMethod.POST)
		public ModelAndView submitStaffRetirement(ModelMap model,
				@ModelAttribute("employee") Employee employee,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("In submitStaffRetirement() method of ERPController: ");
			try {
				logger.info("In ERPController submitStaffRetirement() method: calling submitStaffRetirement() method in BackOfficeServiceImpl.java");
				employee.setUpdatedBy(sessionObject.getUserId());
				String updateResponse = erpService.submitStaffRetirement(employee);
				model.addAttribute("updateResponse", updateResponse);
			} catch (NullPointerException e) {
				
				logger.error("Error in ERPController submitStaffRetirement() method for NullPointerException: ",e);
			} catch (Exception e) {
				
				logger.error("Error in ERPController submitStaffRetirement() method for Exception: ",e);
			}
			return createRetirement(model);
		}
	
	/**
	 * this method get list of retired staff from erpServiceImpl.java and return
	 * to listRetirement.jsp
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/retirementList", method = RequestMethod.GET)
	public String retirementList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {		
		logger.info("In retirementList() method of ERPController: ");
		try {
			logger.info("In ERPController retirementList() method: calling getRetiredStaffList() method in erpServiceImpl.java");
			//System.out.println("parmaaaa");
			List<Employee> retiredStaffList = erpService.getRetiredStaffList();
			//System.out.println("retiredStaffList:"+retiredStaffList.size());
			model.addAttribute("retiredStaffList", retiredStaffList);
		} catch (NullPointerException e) {
			logger.error("Error in ERPController retirementList() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in ERPController retirementList() method for Exception: ",e);
		}
		return "erp/listRetirement";
	}	

	@ResponseBody
	@RequestMapping(value = "/getDesignationForResourceType", method = RequestMethod.GET)
	public String getDesignationForResourceType(HttpServletRequest request,
													 HttpServletResponse response, ModelMap model,
													 @RequestParam("resourceType") String resourceType) {
		logger.info("In getDesignationForResourceType() method of erpController");
		List<Designation> designationList = null;
		String designationArray = "";	
		try {				
			designationList = erpService.getDesignationForResourceType(resourceType);
			if(designationList != null){
				for(Designation designationObject : designationList){
					designationArray = designationArray+designationObject.getDesignationCode()+"~"+designationObject.getDesignationName() + "@";
				}
			}
		} catch (Exception e) {				
			logger.error("Exception occured in getDesignationForResourceType() from erpController : ",e);
		}
		return (new Gson().toJson(designationArray));			
	}	


@RequestMapping(value = "/updateEmployeeNomineeDetails", method = RequestMethod.POST)
public ModelAndView updateEmployeeNomineeDetails(
						HttpServletRequest request, HttpServletResponse response, ModelMap model,
						Employee employee,
						@RequestParam(required = false, value = "nomineeName") String[] nomineeName,
						@RequestParam(required = false, value = "relationship") String[] relationship,
						@RequestParam(required = false, value = "nomineePercent") String[] nomineePercent,	
						@ModelAttribute("sessionObject") SessionObject sessionObject){
	String updateMessage = null;
	try{
		logger.info("Executing updateEmployeeNomineeDetails() method of ERPController: ");
		List<NomineeDetails> nomineeDetailsList = new ArrayList<NomineeDetails>();
		if (nomineeName != null && nomineeName.length != 0 && relationship != null && relationship.length != 0 && nomineePercent !=null && nomineePercent.length!= 0) {				
			for (int i = 0; i < nomineeName.length; i++) {
				NomineeDetails nomineeDetails = new NomineeDetails();
				nomineeDetails.setUpdatedBy(sessionObject.getUserId());
				if (nomineeName != null && nomineeName.length != 0) {
					if (nomineeName[i] != null && nomineeName[i].length() != 0) {
						nomineeDetails.setNomineeName(nomineeName[i]);
					}
				}
				if (relationship != null && relationship.length != 0) {
					if (relationship[i] != null && relationship[i].length() != 0) {
						 nomineeDetails.setRelationship(relationship[i]);
					}
				}
				if (nomineePercent != null && nomineePercent.length != 0) {
					if (nomineePercent[i] != null && nomineePercent[i].length() != 0) {
						nomineeDetails.setPercentage(Double.parseDouble(nomineePercent[i]));
					}
				}
				
				nomineeDetailsList.add(nomineeDetails);					
			}					
		}
		
		employee.setNomineeDetailsList(nomineeDetailsList);	
		employee.setUpdatedBy(sessionObject.getUserId());
		if(employee.getNomineeDetailsList() != null && employee.getNomineeDetailsList().size() != 0){
			for(NomineeDetails nd : employee.getNomineeDetailsList()){					
				nd.setUserId(employee.getResource().getUserId());					
			}				
		}								
		String updateResponse = erpService.updateEmployeeNomineeDetails(employee);
		if(updateResponse.equals("Success")){			
			String description = "Employee Nominee Details Was Updated for UserID: "+employee.getResource().getUserId();			
			callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
		}
		if(updateResponse.equalsIgnoreCase("Success")){
			updateMessage = "Employee Nominee Details Successfully Updated";
		}else{
			updateMessage = "Employee Nominee Details Updation Failed";
		}
		model.addAttribute("updateMessage", updateMessage);
		model.addAttribute("updateResponse", updateResponse);							
	}catch(Exception e){
		logger.error("Exception in updateEmployeeNomineeDetails()", e);
	}
	return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
}



@RequestMapping(value = "/updateEmployeeWorkShopAndTrainingDetails", method = RequestMethod.POST)
public ModelAndView updateEmployeeWorkShopAndTrainingDetails(
						HttpServletRequest request, HttpServletResponse response, ModelMap model,
						Employee employee,
						@RequestParam(required = false, value = "subject") String[] subject,
						@RequestParam(required = false, value = "venue") String[] venue,
						@RequestParam(required = false, value = "organizedBy") String[] organizedBy,
						@RequestParam(required = false, value = "duration") String[] duration,	
						@RequestParam(required = false, value = "trainingFromDate") String[] trainingFromDate,
						@RequestParam(required = false, value = "trainingToDate") String[] trainingToDate,
						@ModelAttribute("sessionObject") SessionObject sessionObject){
	String updateMessage = null;
	try{
		logger.info("Executing updateEmployeeWorkShopAndTrainingDetails() method of ERPController: ");
		List<WorkShopAndTraining> workShopAndTrainingList = new ArrayList<WorkShopAndTraining>();
		if (subject != null && subject.length != 0 && venue != null && venue.length != 0 && organizedBy != null && organizedBy.length != 0 && duration != null && duration.length != 0&& trainingFromDate != null && trainingFromDate.length != 0&& trainingToDate != null && trainingToDate.length != 0) {				
			for (int i = 0; i < subject.length; i++) {
				WorkShopAndTraining workShopAndTraining = new WorkShopAndTraining();
				workShopAndTraining.setUpdatedBy(sessionObject.getUserId());
				if (subject != null && subject.length != 0) {
					if (subject[i] != null && subject[i].length() != 0) {
						workShopAndTraining.setSubject(subject[i]);
					}
				}
				if (venue != null && venue.length != 0) {
					if (venue[i] != null && venue[i].length() != 0) {
						workShopAndTraining.setVenue(venue[i]);
					}
				}
				if (organizedBy != null && organizedBy.length != 0) {
					if (organizedBy[i] != null && organizedBy[i].length() != 0) {
						workShopAndTraining.setOrganizedBy(organizedBy[i]);
					}
				}
				if (duration != null && duration.length != 0) {
					if (duration[i] != null && duration[i].length() != 0) {
						workShopAndTraining.setDuration(duration[i]);
					}
				}
				if (trainingFromDate != null && trainingFromDate.length != 0) {
					if (trainingFromDate[i] != null && trainingFromDate[i].length() != 0) {
						workShopAndTraining.setTrainingFromDate(trainingFromDate[i]);
					}
				}
				if (trainingToDate != null && trainingToDate.length != 0) {
					if (trainingToDate[i] != null && trainingToDate[i].length() != 0) {
						workShopAndTraining.setTrainingToDate(trainingToDate[i]);
					}
				}
				
				workShopAndTrainingList.add(workShopAndTraining);					
			}					
		}
		employee.setWorkShopAndTrainingList(workShopAndTrainingList);
		employee.setUpdatedBy(sessionObject.getUserId());
		
		String updateResponse = erpService.updateEmployeeWorkShopAndTrainingDetails(employee);
		if(updateResponse.equals("Success")){			
			String description = "Employee WorkShop And Training Details Was Updated for UserID: "+employee.getResource().getUserId();			
			callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
		}
		if(updateResponse.equalsIgnoreCase("Success")){
			updateMessage = "Employee WorkShop And Training Details Successfully Updated";
		}else{
			updateMessage = "Employee WorkShop And Training Details Updation Failed";
		}
		model.addAttribute("updateMessage", updateMessage);
		model.addAttribute("updateResponse", updateResponse);						
	}catch(Exception e){
		logger.error("Exception in updateEmployeeWorkShopAndTrainingDetails()", e);
	}
	return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
}




@RequestMapping(value = "/updateEmployeeAwardsAndRecognizationDetails", method = RequestMethod.POST)
public ModelAndView updateEmployeeAwardsAndRecognizationDetails(
						HttpServletRequest request, HttpServletResponse response, ModelMap model,
						Employee employee,
						@RequestParam(required = false, value = "awardName") String[] awardName,
						@RequestParam(required = false, value = "presentedBy") String[] presentedBy,
						@RequestParam(required = false, value = "presentedOn") String[] presentedOn,			
						@ModelAttribute("sessionObject") SessionObject sessionObject){
	String updateMessage = null;
	try{
		logger.info("Executing updateEmployeeAwardsAndRecognizationDetails() method of ERPController: ");
		List<AwardsAndRecognization> awardsAndRecognizationList = new ArrayList<AwardsAndRecognization>();
		if (awardName != null && awardName.length != 0 && presentedBy != null && presentedBy.length != 0 && presentedOn != null && presentedOn.length != 0) {				
			for (int i = 0; i < awardName.length; i++) {
				AwardsAndRecognization awardsAndRecognization = new AwardsAndRecognization();
				awardsAndRecognization.setUpdatedBy(sessionObject.getUserId());
				if (awardName != null && awardName.length != 0) {
					if (awardName[i] != null && awardName[i].length() != 0) {
						awardsAndRecognization.setAwardName(awardName[i]);
					}
				}
				if (presentedBy != null && presentedBy.length != 0) {
					if (presentedBy[i] != null && presentedBy[i].length() != 0) {
						awardsAndRecognization.setPresentedBy(presentedBy[i]);
					}
				}
				if (presentedOn != null && presentedOn.length != 0) {
					if (presentedOn[i] != null && presentedOn[i].length() != 0) {
						awardsAndRecognization.setPresentedOn(presentedOn[i]);
					}
				}
				
				awardsAndRecognizationList.add(awardsAndRecognization);					
			}					
		}
		employee.setAwardsAndRecognizationList(awardsAndRecognizationList);
		employee.setUpdatedBy(sessionObject.getUserId());
		
		String updateResponse = erpService.updateEmployeeAwardsAndRecognizationDetails(employee);
		if(updateResponse.equals("Success")){			
			String description = "Employee Awards And Recognization Details Was Updated for UserID: "+employee.getResource().getUserId();			
			callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
		}
		if(updateResponse.equalsIgnoreCase("Success")){
			updateMessage = "Employee Awards And Recognization Details Successfully Updated";
		}else{
			updateMessage = "Employee Awards And Recognization Details Updation Failed";
		}
		model.addAttribute("updateMessage", updateMessage);
		model.addAttribute("updateResponse", updateResponse);						
	}catch(Exception e){
		logger.error("Exception in updateEmployeeAwardsAndRecognizationDetails()", e);
	}
	return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
}



@RequestMapping(value = "/updateEmployeeConfidentialDetails", method = RequestMethod.POST)
public ModelAndView updateEmployeeConfidentialDetails(
						HttpServletRequest request, HttpServletResponse response, ModelMap model,
						Employee employee,
												
						@ModelAttribute("sessionObject") SessionObject sessionObject){
	String updateMessage = null;
	try{
		logger.info("Executing updateEmployeeConfidentialDetails() method of ERPController: ");					
		employee.setUpdatedBy(sessionObject.getUserId());	
		String updateResponse = erpService.updateEmployeeConfidentialDetails(employee);
		if(updateResponse.equals("Success")){			
			String description = "Employee Confidential Details Was Updated for UserID: "+employee.getResource().getUserId();			
			callActivityLog(sessionObject.getUserId(), employee.getResource().getUserId(), "UPDATE", description);
		}
		if(updateResponse.equalsIgnoreCase("Success")){
			updateMessage = "Employee Confidential Details Successfully Updated";
		}else{
			updateMessage = "Employee Confidential Details Updation Failed";
		}
		model.addAttribute("updateMessage", updateMessage);
		model.addAttribute("updateResponse", updateResponse);					
	}catch(Exception e){
		e.printStackTrace();
		logger.error("Exception in updateEmployeeConfidentialDetails()", e);
	}
	return viewEditStaffDetails(request, response, model, employee, employee.getResource().getUserId());
	}


	@RequestMapping(value = "/manualLeaveUpdate", method = RequestMethod.GET)
	public ModelAndView manualLeaveUpdate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("In showLeaveStructure() method of BackOfficeController");
		try {
			logger.debug("showLeaveStructure()In BackOfficeController.java: calling getAcademicYear() in BackOfficeService.java");							
			List<Resource> staffList = commonService.getStaffUserIdList();
			if (staffList != null && staffList.size() != 0) {
				model.addAttribute("resourceStaffUserIdList", staffList);
			}				
		} catch (Exception e) {
			logger.error("Exception in manualLeaveUpdate()", e);
		}
	
		return new ModelAndView("erp/manualLeaveUpdate");
	}
	
	
	@RequestMapping(value = "/getSpecificEmployeeCompleteLeaveDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getSpecificEmployeeCompleteLeaveDetails(@RequestParam("staffUserId") String staffUserId) {
		logger.info("Inside Method getSpecificEmployeeCompleteLeaveDetails-GET of AcademicsController");
		String result = "";
		try {
			List<Leave> staffLeaveDetails = erpService.getResourceLeaveDetails(staffUserId);
			if(staffLeaveDetails != null && staffLeaveDetails.size() != 0){				
				for(Leave lv : staffLeaveDetails){
					result=result+lv.getLeaveType()+"*"+			
					lv.isEncashable()+"*"+
					lv.getTotalAvailLeave()+"*"+
					lv.getCanApplyOnStretch()+"*"+
					lv.getRemainingLeave()+"#";
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return (new Gson().toJson(result));	
	}
	
	
	@RequestMapping(value = "/updateManualLeaveResponse", method = RequestMethod.POST)
	public ModelAndView updateManualLeaveResponse(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("Leave") Leave leave, 
			Task task
			) {
		try {
			SessionObject sessionObject=(SessionObject) request.getSession().getAttribute("sessionObject");
			logger.info("In leaveResponse() POST of ERPController");
			leave.setUpdatedBy(sessionObject.getUserId());
			task.setTaskApprover(sessionObject.getUserId());
			task.setTaskId(leave.getLeaveId());
			task.setLeave(leave);
			
			//Task task = new Task();
			task.setLeave(leave);
			task.setCreatedById(sessionObject.getUserId());
			task.setSkipable(false);			
			task.setTaskName(leave.getTitle());
			task.setStartDate(leave.getStartDate());
			task.setEndDate(leave.getEndDate());
			task.setProcessStatus(leave.getDesc());			
			task.setActivationTime(leave.getLeaveType());
			task.setNumberofDayRequestedFor(leave.getTotalRequestedLeave());				
			String status = erpService.insertManualLeaveResponse(task);			
			model.addAttribute("updateStatus", status);
		} catch (Exception e) {	
			e.printStackTrace();
			logger.error("Exception In leaveResponse() POST of ERPController",	e);
			
		}
		return manualLeaveUpdate(request, response, model);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param Task
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getWorkingDaysForLeaveRequest", method = RequestMethod.GET)
	public String getWorkingDaysForLeaveRequest(HttpServletRequest request,
													 HttpServletResponse response, ModelMap model,
													 @RequestParam("startDate") String startDate,
													 @RequestParam("endDate") String endDate) {
		logger.info("In getWorkingDaysForLeaveRequest() method of erpController:, calling getGradesForSalaryTemplate() in erpService.java");
		Task task=new Task();
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		String count = "";
		try {	
			task = erpService.getWorkingDaysForLeaveRequest(task);
			count=count+task.getTaskId();
		} catch (Exception e) {				
			logger.error("Exception occured in getWorkingDaysForLeaveRequest() from erpController : ",e);
		}
		return (new Gson().toJson(count));			
	}		
	
	public String callActivityLog(String updatedBy, String upadatedFor, String action, String desc) throws CustomException{
		String logStatus = "";
		try{			
			UpdateLog updateLog=new UpdateLog();
			updateLog.setUpdatedByUserId(updatedBy);
			updateLog.setFunctionality("STAFF DETAILS");
			updateLog.setInsertUpdate(action);
			updateLog.setModule("ERP");
			updateLog.setUpdatedFor(upadatedFor);
			updateLog.setDescription(desc);
			logStatus = commonService.insertIntoActivityLog(updateLog);
			logStatus = "success";
		}catch(Exception e){
			logger.error("Exception In callActivityLog() method of ErpController: Exception",e);		}				
		return logStatus;
	}			
	
	@RequestMapping(value = "/designationLevelMapping", method = RequestMethod.GET)
	public String designationLevelMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			logger.info("designationLevelMapping()In ErpController.java: calling taskDelegation() in ErpController.java");
			//List<Designation> designationcompleteList=erpService.getAllDesignation();
			List<DesignationLevel> designationLevelList=erpService.getAllDesignationLevel();
			/*modified by ranita.sur on 20092017 for getting unmapped designation*/
			List<Designation> designationcompleteList = erpService.getAllUnMappedDesignation();
			if(null!=designationcompleteList && designationcompleteList.size()!=0){
				model.addAttribute("designationListForUnmapped", designationcompleteList);//added by ranita.sur on20092017
			}
			if(null!=designationLevelList && designationLevelList.size()!=0){
				model.addAttribute("designationLevelList", designationLevelList);
			}
			//logger.info("getAllMappedDesignation()In ErpController.java: calling taskDelegation() in ErpController.java");
			List<Designation> designationList=erpService.getAllMappedDesignation();
			List<DesignationLevel> levelList=erpService.getAllDesignationLevel();
			if(designationList!=null && levelList!=null){				
					for(Designation designation:designationList){
						HashMap<String,String> alllevelMap = new HashMap<String, String>();
						for(DesignationLevel levelInDB:designation.getDesignationLevelList()){
							alllevelMap.put(levelInDB.getDesignationLevelCode(), levelInDB.getDesignationLevelCode());
						}
						designation.setDesignationLevelList(null);
						List<DesignationLevel> levelListTemp = new ArrayList<DesignationLevel>();
							for(DesignationLevel level:levelList){
								DesignationLevel lvl = new DesignationLevel();
								if( alllevelMap.containsKey(level.getDesignationLevelCode())){									
									lvl.setStatus("checked");
								}
								lvl.setDesignationLevelCode(level.getDesignationLevelCode());
								lvl.setDesignationLevelName(level.getDesignationLevelName());
								levelListTemp.add(lvl);
							}
						designation.setDesignationLevelList(levelListTemp);
					}
			}
			model.addAttribute("designationList", designationList);	
		} catch (Exception e) {
			logger.error("Exception In designationLevelMapping() method of ErpController: Exception",e);
		}
		return "erp/designationLevelMapping";
	}
	
	@RequestMapping(value = "/submitDesignationLevelMapping", method = RequestMethod.POST)
	public String submitDesignationLevelMapping(HttpServletRequest request,	HttpServletResponse response, ModelMap model,
												@ModelAttribute("sessionObject") SessionObject sessionObject,												
												@RequestParam(value="designationLevel", required = false) String []levelArray,
												@RequestParam("designationCode") String designationCode) {
		try {
			logger.info("submitDesignationLevelMapping()In ErpController.java: calling submitDesignationLevelMapping() in ErpController.java");
			List<DesignationLevel> designationLevelList=new ArrayList<DesignationLevel>();
			List<Designation> designationList=new ArrayList<Designation>();
			Designation designation=new Designation();
			if(levelArray!=null && levelArray.length!=0){
				for(int i=0;i<levelArray.length;i++){
					DesignationLevel level = new DesignationLevel();
					level.setDesignationLevelCode(levelArray[i]);
					level.setUpdatedBy(sessionObject.getUserId());
					designationLevelList.add(level);
				}
				if(designationCode!=null){
					designation.setDesignationLevelList(designationLevelList);				
					designation.setDesignationCode(designationCode);
					designation.setUpdatedBy(sessionObject.getUserId());
				}
				designationList.add(designation);				
			String submitResponse = erpService.addDesignationLevelMapping(designationList);	
			model.addAttribute("submitResponse", submitResponse);
			}
		} catch (Exception e) {
			logger.error("Exception In submitDesignationLevelMapping() method of ErpController: Exception",e);
		}
		return designationLevelMapping(request,response,model);
	}
	
	@RequestMapping(value = "/editDesignationLevelMapping", method = RequestMethod.GET)
	public String editDesignationLevelMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			logger.info("getAllMappedDesignation()In ErpController.java: calling taskDelegation() in ErpController.java");
			List<Designation> designationList=erpService.getAllMappedDesignation();
			List<DesignationLevel> levelList=erpService.getAllDesignationLevel();
			if(designationList!=null && levelList!=null){				
					for(Designation designation:designationList){
						HashMap<String,String> alllevelMap = new HashMap<String, String>();
						for(DesignationLevel levelInDB:designation.getDesignationLevelList()){
							alllevelMap.put(levelInDB.getDesignationLevelCode(), levelInDB.getDesignationLevelCode());
						}
						designation.setDesignationLevelList(null);
						List<DesignationLevel> levelListTemp = new ArrayList<DesignationLevel>();
							for(DesignationLevel level:levelList){
								DesignationLevel lvl = new DesignationLevel();
								if( alllevelMap.containsKey(level.getDesignationLevelCode())){									
									lvl.setStatus("checked");
								}
								lvl.setDesignationLevelCode(level.getDesignationLevelCode());
								lvl.setDesignationLevelName(level.getDesignationLevelName());
								levelListTemp.add(lvl);
							}
						designation.setDesignationLevelList(levelListTemp);
					}
			}
			model.addAttribute("designationList", designationList);			
		} catch (Exception e) {
			logger.error("Exception In getAllMappedDesignation() method of ErpController: Exception",e);
		}
		return "erp/editDesignationLevelMapping";
	}
	
	
	
	@RequestMapping(value = "/updateDesignationLevelMapping", method = RequestMethod.POST)
	public String updateDesignationLevelMapping(HttpServletRequest request,
						HttpServletResponse response, ModelMap model,
						@ModelAttribute("sessionObject") SessionObject sessionObject,
						@RequestParam("designationCodeList") String designationCodeList) {
		try {
			designationCodeList=request.getParameter("saveId");
			Designation designation = new Designation();
			int flag = 0;
			if(designationCodeList!=null){				
					if(request.getParameter("designation"+designationCodeList)!=null){
						designation.setDesignationCode(request.getParameter("designation"+designationCodeList));
						designation.setUpdatedBy(sessionObject.getUserId());
						if(request.getParameterValues("level"+designationCodeList)!=null){
							String []newLevelArray = request.getParameterValues("level"+designationCodeList);
							String []oldLevelArray = request.getParameterValues("oldDesignationLevel"+designationCodeList);
							List<String>newLevelArrayList = Arrays.asList(newLevelArray);
							List<String>oldLevelArrayList = Arrays.asList(oldLevelArray);
							List<DesignationLevel> levelList=new ArrayList<DesignationLevel>();
							List<DesignationLevel> designationLevelList=new ArrayList<DesignationLevel>();
							//Modified by naimisha 16082017
							if(newLevelArray.length > oldLevelArray.length){
								for(String newLevel : newLevelArrayList){
									//for(int j = 0;j<oldLevelArray.length;j++){
									if(!(oldLevelArrayList.contains(newLevel))){
											DesignationLevel designationLevel = new DesignationLevel();
											designationLevel.setDesignationLevelCode(newLevel);
											levelList.add(designationLevel);
										}
									//}
								}
								designation.setNewDesignationLevelList(levelList);
							}else if (newLevelArray.length < oldLevelArray.length){
								
								for(String oldLevel : oldLevelArrayList){
									if(!(newLevelArrayList.contains(oldLevel))){
										DesignationLevel designationLevel = new DesignationLevel();
										designationLevel.setDesignationLevelCode(oldLevel);
										designationLevelList.add(designationLevel);
									}
								}
								designation.setOldDesignationLevelList(designationLevelList);
							}
							
							String updateStatus = erpService.updateDesignationLevelMapping(designation);	
							if(updateStatus.equalsIgnoreCase("insertSuccess")||updateStatus.equalsIgnoreCase("updateSuccess")){
								model.addAttribute("status", "success");
								model.addAttribute("message", "Designation-Level Mapping Updated Successfully ");
							}else if (updateStatus.equalsIgnoreCase("alreadyMapped")){
								model.addAttribute("status", "mapped");
								model.addAttribute("message", "Designation-Level Already mapped with a Template.Can not delete ");
							}else{
								model.addAttribute("status", "fail");
								model.addAttribute("message", "Designation-Level Mapping Update Failed ");
							}
							/**Added by @author Saif.Ali
							 * Date - 27-03-2018*/
							String saveId = request.getParameter("saveId");
							String description = "" ;
							if(updateStatus.equalsIgnoreCase("insertSuccess")||updateStatus.equalsIgnoreCase("updateSuccess")){
								UpdateLog updateLog=new UpdateLog();
								updateLog.setUpdatedByUserId(sessionObject.getUserId());
								updateLog.setFunctionality("EDIT DESIGNATION LEVEL MAPPING DETAILS");
								updateLog.setInsertUpdate("UPDATE");
								updateLog.setModule("ERP");
								updateLog.setUpdatedFor("Designation :: " + request.getParameter("designation"+saveId));
								description= description + ("Designation :: "+ request.getParameter("designation"+saveId));
								if(newLevelArray.length > oldLevelArray.length)
								{
									for(int i=0; i<newLevelArray.length ; i++)
									{
										if(!(oldLevelArrayList.contains(newLevelArray[i])))
										{
											description= description + (" with Designation Level :: " + oldLevelArrayList + " updated to new Designation Level :: " + oldLevelArrayList + newLevelArray[i] + " ; ");
										}
									}
								}
								else if (newLevelArray.length < oldLevelArray.length)
								{
									for(int i=0; i<oldLevelArray.length; i++)
									{
										if(!(newLevelArrayList.contains(oldLevelArray[i])))
										{
											description= description + (" with Designation Level :: " + oldLevelArray[i] + " updated to new Designation Level :: " + newLevelArrayList + " ; ");
										}
									}
								}	
								updateLog.setDescription(description);
								String response_update=commonService.insertIntoActivityLog(updateLog);
								System.out.println("LN 2731 :: ERPController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
										":: Functonality ::"+ updateLog.getFunctionality() + 
										":: Module ::"+updateLog.getModule() + 
										":: Updated For ::"+ updateLog.getUpdatedFor() +
										":: Description :: "+updateLog.getDescription() +
										":: Response :: " + response_update +
										":: Insert/Update :: " + updateLog.getInsertUpdate());
							}
							/***/
							}/*else{
								model.addAttribute("message", "Designation-Level Mapping Update Failed ");
							}*/
					}/*else{
						model.addAttribute("message", "Designation-Level Mapping Update Failed ");
					}*/
			}
			/*else{
				model.addAttribute("message", "Designation-Level Mapping Update Failed ");
			}*/
			logger.info("getAllMappedDesignation()In ErpController.java: calling taskDelegation() in ErpController.java");
				
		} catch (Exception e) {
			logger.error("Exception In getAllMappedDesignation() method of ErpController: Exception",e);
		}
		/*modified by ranita.sur on 20092017 for returning in the designationLevelMapping Page*/
		return designationLevelMapping(request,response,model);
	}
	//Staff Salary Code Integration 
	//----------------------------------------------------------------------------
	
	/**
	 * this method get existing salary break up for edit and return to
	 * salaryBreakUp.jsp for update existing salary break up and create new
	 * salary break up.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewSalaryBreakUp", method = RequestMethod.GET)
	public ModelAndView viewSalaryBreakUp(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("In salaryBreakUp() method of BackOfficeController: ");
		try {
			logger.info("In BackOfficeController viewSalaryBreakUp() method: calling getSalaryBreakUp() method in erpServiceImpl.java");
			List<SalaryBreakUp> salaryBreakUplist = erpService.getSalaryBreakUp();
			if (salaryBreakUplist != null && salaryBreakUplist.size() != 0) {
				model.addAttribute("salaryBreakUpListForCreateUpdate", salaryBreakUplist);
			}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController viewSalaryBreakUp() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController viewSalaryBreakUp() method for Exception: ", e);
		}
		return new ModelAndView("erp/salaryBreakUp");
	}

	/**
	 * this method get salary breakUp name and type from salaryBreakUp.jsp and
	 * call erpServiceImpl.java for create salary breakup and get list of salary
	 * break up and return to salaryBreakUp.jsp
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param salaryBreakUp
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/createSalaryBreakUp", method = RequestMethod.POST)
	public ModelAndView createSalaryBreakUp(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("salaryBreakUp") SalaryBreakUp salaryBreakUp,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In createSalaryBreakUp() method of BackOfficeController: ");
		try {
			logger.info("In BackOfficeController createSalaryBreakUp() method: calling createSalaryBreakUp(SalaryBreakUp salaryBreakUp) method in erpServiceImpl.java");
			salaryBreakUp.setUpdatedBy(sessionObject.getUserId());
			List<SalaryBreakUp> salaryBreakUplist = erpService.createSalaryBreakUp(salaryBreakUp);
			if (salaryBreakUplist != null && salaryBreakUplist.size() != 0) {
				model.addAttribute("salaryBreakUpListForCreateUpdate", salaryBreakUplist);
			}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController createSalaryBreakUp() method for NullPointerException: ", e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController createSalaryBreakUp() method for Exception: ", e);
		}
		return new ModelAndView("erp/salaryBreakUp");
	}
	
	@RequestMapping(value = "/salaryTemplate", method = RequestMethod.GET)
	public ModelAndView salaryTemplate(HttpServletRequest request,HttpServletResponse response, ModelMap model,
							@ModelAttribute("sessionObject") SessionObject sessionObject){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("erp/createSalaryTemplate");
		try{
			List<SalaryBreakUp> salaryBreakUpList = erpService.getSalaryBreakUpList();
			if(salaryBreakUpList != null && salaryBreakUpList.size() != 0){
				model.addAttribute("salaryBreakUpList", salaryBreakUpList);
			}else{
				model.addAttribute("status", "Salary Break Up Not Found");
			}
			
			List<DesignationType> designationTypeList = erpService.getAllDesignationType();
			if(designationTypeList != null && designationTypeList.size() != 0){
				model.addAttribute("designationTypeList", designationTypeList);
			}else{
				model.addAttribute("status", "Salary Break Up Not Found");
			}
			
		}catch(Exception e){
			logger.error("Exception in salaryTemplate() from Controller", e);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/salaryTemplateList", method = RequestMethod.GET)
	public ModelAndView salaryTemplateList(HttpServletRequest request,HttpServletResponse response, ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("erp/salaryTemplateList");
		try{
			List<SalaryTemplate> salaryTemplateList = erpService.salaryTemplateList();
			if(salaryTemplateList != null && salaryTemplateList.size() != 0){
				model.addAttribute("salaryTemplateList", salaryTemplateList);
			}
		}catch(Exception e){
			logger.error("Exception in salaryTemplate() from Controller", e);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/submitSalaryTemplate", method = RequestMethod.POST)
	public ModelAndView submitSalaryTemplate(HttpServletRequest request,HttpServletResponse response, ModelMap model,
							@RequestParam(value = "salaryBreakUpCode", required = false) String[] strSalaryBreakUpList,
							@RequestParam(value = "salaryTemplateName", required = false) String salaryTemplateName,
							@RequestParam(value = "designation", required = false) String designation,
							@RequestParam(value = "designationLevel", required = false) String designationLevel,
							@ModelAttribute("sessionObject") SessionObject sessionObject){
		List<SalaryTemplateDetails> salaryTemplateDetailsList = new ArrayList<SalaryTemplateDetails>();
		try{
			for(int i= 0; i < strSalaryBreakUpList.length ; i++){				
				SalaryTemplateDetails salaryTemplateDetails = new SalaryTemplateDetails();
				salaryTemplateDetails.setUpdatedBy(sessionObject.getUserId());
				salaryTemplateDetails.setSalaryBreakUpCode(strSalaryBreakUpList[i]);
				salaryTemplateDetailsList.add(salaryTemplateDetails);
			}
			
			SalaryTemplate salaryTemplate = new SalaryTemplate();
			salaryTemplate.setUpdatedBy(sessionObject.getUserId());
			salaryTemplate.setSalaryTemplateName(salaryTemplateName.trim().toUpperCase());
			salaryTemplate.setSalaryTemplateDesc(salaryTemplateName.trim().toUpperCase());
			salaryTemplate.setDesignation(designation);
			salaryTemplate.setDesignationLevel(designationLevel);
			salaryTemplate.setSalaryTemplateDetailsList(salaryTemplateDetailsList);
			
			String status = erpService.submitSalaryTemplate(salaryTemplate);
			model.addAttribute("status", status);
		}catch(Exception e){
			logger.error("Exception in salaryTemplate() from Controller", e);
		}
		return salaryTemplateList(request, response, model);
	}
	
	@RequestMapping(value="/professionalTaxSlab", method = RequestMethod.GET)
	public String professionalTaxSlab(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<SalaryBreakUp> salBreakUpList = null;
		List<SalaryBreakUp> nonSlabBreakUpList = null;
		try{			
			salBreakUpList = erpService.getSalaryBreakUpForSlab();
			nonSlabBreakUpList = erpService.getSalaryBreakUpForNonSlab();
			model.addAttribute("salBreakUpList", salBreakUpList);
			model.addAttribute("nonSlabBreakUpList", nonSlabBreakUpList);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return "erp/miscellaneousTaxSlab";
	}
			

	@ResponseBody
	@RequestMapping(value = "/getSubmittedSlabTypeForMiscTax", method = RequestMethod.GET)
	public String getSubmittedSlabTypeForMiscTax(HttpServletRequest request, HttpServletResponse response, 
												@RequestParam("miscTaxTypeName") String taxTypeCode, ModelMap model){
		logger.info("In getSubmittedSlabTypeForMiscTax() method of ErpController: ");
		String output = null;
		try {
			output = erpService.getSubmittedSlabTypeForMiscTax(taxTypeCode);
		} catch (Exception e) {
			logger.error("Error in ErpController getSubmittedSlabTypeForMiscTax() method for Exception: ", e);
		}
		return (new Gson().toJson(output));
	}
	
	
	@RequestMapping(value="/submitMiscTaxSlab", method = RequestMethod.POST)
	public String submitMiscTaxSlab(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
											@ModelAttribute("sessionObject") SessionObject sessionObject,
											@ModelAttribute("MiscellaneousTax") MiscellaneousTax miscTax){		
		try{
			List<MiscellaneousTax> miscTaxSlabList = null;
			String[] start = request.getParameterValues("startSlabIndex");
			String[] end = request.getParameterValues("endSlabIndex");
			String[] amount = request.getParameterValues("taxAmount");
			
			if(null != start && null != end && null != amount){
				miscTaxSlabList = new ArrayList<MiscellaneousTax>();
				for(int j = 0 ; j < start.length; j++){
					MiscellaneousTax miscSlab = new MiscellaneousTax();
					miscSlab.setMiscellaneousTaxType(miscTax.getMiscellaneousTaxType());
					miscSlab.setTaxFigureType(miscTax.getTaxFigureType());
					miscSlab.setMiscellaneousTaxSlabCode(miscTax.getMiscellaneousTaxType()+"-SLAB-"+(j+1));
					miscSlab.setUpdatedBy(sessionObject.getUserId());
					miscSlab.setStartSlabAmount(Double.parseDouble(start[j]));
					miscSlab.setEndSlabAmount(Double.parseDouble(end[j]));
					miscSlab.setTaxRate(Double.parseDouble(amount[j]));	
					miscTaxSlabList.add(miscSlab);
				}
				miscTax.setUpdatedBy(sessionObject.getUserId());
				String submitResponse = erpService.submitMiscTaxSlab(miscTaxSlabList, miscTax);				
			}	
		}catch(Exception e){
		}
		return viewMiscTaxSlab(request, response, model);
	}
	
		
	@RequestMapping(value="/viewMiscTaxSlab", method = RequestMethod.GET)
	public String viewMiscTaxSlab(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<SalaryBreakUp> salBreakUpList = null;
		try{			
			salBreakUpList = erpService.getSalaryBreakUpForMiscTaxSlab();
			model.addAttribute("salBreakUpList", salBreakUpList);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return "erp/viewMiscellaneousTaxSlab";
	}
	
	
	
	@RequestMapping(value="/getMiscellaneousSlabForEdit", method = RequestMethod.GET)
	public ModelAndView getMiscellaneousSlabForEdit(@RequestParam("miscTaxTypeCode") String taxTypeCode, 
													@RequestParam("miscTaxTypeName") String taxTypeName,
													HttpServletRequest request, HttpServletResponse response, Model model){
		ModelAndView modelAndView = new ModelAndView();
		List<MiscellaneousTax> miscTaxList = null;
		List<SalaryBreakUp> nonSlabBreakUpList = null;
		try{
			miscTaxList = erpService.getMiscTaxSlabForEdit(taxTypeCode);
			//System.out.println("misc tax list size"+miscTaxList.size());
			nonSlabBreakUpList = erpService.getSalaryBreakUpForNonSlab();			
			
			model.addAttribute("figureType", miscTaxList.get(0).getTaxFigureType());
			model.addAttribute("taxBasedOnName", miscTaxList.get(0).getTaxBasedOn());
			model.addAttribute("taxBasedOnCode", miscTaxList.get(0).getMiscellaneousTaxSlabDesc());
			model.addAttribute("taxTypeCode", taxTypeCode);
			model.addAttribute("taxTypeName", taxTypeName);
			model.addAttribute("miscTaxList", miscTaxList);
			model.addAttribute("nonSlabBreakUpList", nonSlabBreakUpList);
		}catch(Exception e){
			e.printStackTrace();
		}
		modelAndView.setViewName("erp/editMiscellaneousTaxSlab");				
		return modelAndView;
	}	
	
	
//	@RequestMapping(value="/editMiscTaxSlab", method = RequestMethod.POST)
//	public String updateMiscTaxSlab(@ModelAttribute("MiscellaneousTax") MiscellaneousTax miscTax,
//									HttpServletRequest request, HttpServletResponse response, ModelMap model, 
//									@ModelAttribute("sessionObject") SessionObject sessionObject){		
//		try{
//			List<MiscellaneousTax> miscTaxSlabList = null;
//			String[] start = request.getParameterValues("startSlabIndex");
//			String[] end = request.getParameterValues("endSlabIndex");
//			String[] amount = request.getParameterValues("taxAmount");
//			
//			if(null != start && null != end && null != amount){
//				miscTaxSlabList = new ArrayList<MiscellaneousTax>();
//				for(int j = 0 ; j < start.length; j++){
//					MiscellaneousTax miscSlab = new MiscellaneousTax();
//					miscSlab.setMiscellaneousTaxType(miscTax.getMiscellaneousTaxType());
//					miscSlab.setMiscellaneousTaxSlabCode(miscTax.getMiscellaneousTaxType()+"-SLAB-"+(j+1));
//					miscSlab.setTaxFigureType(miscTax.getTaxFigureType());
//					miscSlab.setUpdatedBy(sessionObject.getUserId());
//					miscSlab.setStartSlabAmount(Double.parseDouble(start[j]));
//					miscSlab.setEndSlabAmount(Double.parseDouble(end[j]));
//					miscSlab.setTaxRate(Double.parseDouble(amount[j]));	
//					System.out.println("--------END OF "+(j+1)+" ROW----------");
//					miscTaxSlabList.add(miscSlab);
//				}
//				miscTax.setUpdatedBy(sessionObject.getUserId());
//				String updateResponse = erpService.updateMiscTaxSlab(miscTaxSlabList, miscTax);
//				System.out.println("/////   updateResponse  :  "+updateResponse);
//			}	
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return viewMiscTaxSlab(request, response, model);
//	}

	
	@RequestMapping(value = "/ajaxCallForCalculateSlabTax", method = RequestMethod.GET)
	public @ResponseBody
	String ajaxCallForCalculateSlabTax(@RequestParam("totalGross") String totalGross, 
						@RequestParam("totalNet") String totalNet,
						@RequestParam("userId") String userId,
						@RequestParam("month") String month,
						@RequestParam("year") String year) {
		String strTaxes = "";
		if(totalGross != null && totalNet != null){
			Employee employee = new Employee();
			Resource resource = new Resource();
			resource.setUserId(userId);
			resource.setStartDate(year);
			resource.setEndDate(month);
			employee.setResource(resource);
			List<SalaryBreakUp> taxList = erpService.getTaxDeductionAmount(totalGross,totalNet,employee);
			if (taxList != null && taxList.size() != 0) {
				for (SalaryBreakUp taxes : taxList) {
					//System.out.println("result in ajax"+taxes.getAmount()+";"+taxes.getSalaryBreakUpCode()+";"+taxes.getSalaryBreakUpName());
					strTaxes = strTaxes + taxes.getSalaryBreakUpCode() + "~" + taxes.getSalaryBreakUpName() + "~" +taxes.isSlab()+ "~" +taxes.getAmount() + "@@";
				}
			}
		}		
		return (new Gson().toJson(strTaxes));
	}
	
	
	@RequestMapping(value="/employerContribution", method = RequestMethod.GET)
	public String employerContribution(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		logger.info("In employerContribution() method of erpController");
		try{			
			List<SalaryBreakUp> salaryBreakUplist = erpService
					.getSalaryBreakUp();
			if (salaryBreakUplist != null && salaryBreakUplist.size() != 0) {
				model.addAttribute("salBreakUpList",
						salaryBreakUplist);
			}
			List<SalaryBreakUp> nonSlabBreakUpList = erpService.getSalaryBreakUpForNonSlab();
			if (nonSlabBreakUpList != null && nonSlabBreakUpList.size() != 0) {
				model.addAttribute("nonSlabBreakUpList", nonSlabBreakUpList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return "erp/employerContribution";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubmittedEmployerContribution", method = RequestMethod.GET)
	public String getSubmittedEmployerContribution(HttpServletRequest request, HttpServletResponse response, 
												@RequestParam("miscTaxTypeName") String taxTypeCode, ModelMap model){
		logger.info("In getSubmittedEmployerContribution() method of ErpController: ");
		String output = null;
		try {
			output = erpService.getSubmittedEmployerContribution(taxTypeCode);
		} catch (Exception e) {
			logger.error("Error in ErpController getSubmittedSlabTypeForMiscTax() method for Exception: ", e);
		}
		return (new Gson().toJson(output));
	}

	@RequestMapping(value="/submitEmployerContribution", method = RequestMethod.POST)
	public String submitEmployerContribution(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
											@ModelAttribute("sessionObject") SessionObject sessionObject,
											@ModelAttribute("MiscellaneousTax") MiscellaneousTax miscTax){		
		try{
			List<MiscellaneousTax> miscTaxSlabList = null;
			String[] start = request.getParameterValues("startSlabIndex");
			String[] end = request.getParameterValues("endSlabIndex");
			String[] amount = request.getParameterValues("taxAmount");
			String submitResponse = null;
			
			if(null != start && null != end && null != amount){
				miscTaxSlabList = new ArrayList<MiscellaneousTax>();
				for(int j = 0 ; j < start.length; j++){
					MiscellaneousTax miscSlab = new MiscellaneousTax();
					miscSlab.setMiscellaneousTaxType(miscTax.getMiscellaneousTaxType());
					miscSlab.setTaxFigureType(miscTax.getTaxFigureType());
					miscSlab.setMiscellaneousTaxSlabCode(miscTax.getMiscellaneousTaxType()+"-SLAB-"+(j+1));
					miscSlab.setUpdatedBy(sessionObject.getUserId());
					miscSlab.setStartSlabAmount(Double.parseDouble(start[j]));
					miscSlab.setEndSlabAmount(Double.parseDouble(end[j]));
					miscSlab.setTaxRate(Double.parseDouble(amount[j]));	
					miscTaxSlabList.add(miscSlab);
				}
				miscTax.setUpdatedBy(sessionObject.getUserId());
				submitResponse = erpService.submitEmployerContribution(miscTaxSlabList, miscTax);				
			}	
			List<SalaryBreakUp> salaryBreakUplist = erpService.getSalaryBreakUp();
			if (salaryBreakUplist != null && salaryBreakUplist.size() != 0) {
				model.addAttribute("salBreakUpList", salaryBreakUplist);
			}
			List<SalaryBreakUp> nonSlabBreakUpList = erpService.getSalaryBreakUpForNonSlab();
			if (nonSlabBreakUpList != null && nonSlabBreakUpList.size() != 0) {
				model.addAttribute("nonSlabBreakUpList", nonSlabBreakUpList);
			}
			model.addAttribute("submitResponse", submitResponse);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "erp/employerContribution";
	}

	@RequestMapping(value="/viewParameterOfIncomeTaxSalarySlab.html", method = RequestMethod.POST)
	public String viewParameterOfIncomeTaxSalarySlab(HttpServletRequest request, 
											HttpServletResponse response,
											ModelMap model,
											@RequestParam("hidVal") String hidVal){	
		try{	
			//System.out.println("HIDVAL:"+hidVal);
			String strIncomeAge = request.getParameter("incomeAge");	
			IncomeTaxSlabDetails incomeTaxSlabDetails = new IncomeTaxSlabDetails();
			IncomeAge incomeAge = new IncomeAge();
			incomeAge.setIncomeAgeCode(request.getParameter("incomeAge"));
			
			incomeTaxSlabDetails.setStatus(hidVal);
			incomeTaxSlabDetails.setIncomeAge(incomeAge);
			
			IncomeTaxSlabDetails itIncomeTaxSlabsDetails = erpService.viewParameterOfIncomeTaxSalarySlab(incomeTaxSlabDetails);
			if(itIncomeTaxSlabsDetails != null){
				model.addAttribute("itIncomeTaxSlabsDetails", itIncomeTaxSlabsDetails);
			}
			List<SalaryBreakUp> salaryBreakUplist = erpService.getSalaryBreakUp();
			if (salaryBreakUplist != null && salaryBreakUplist.size() != 0) {
				model.addAttribute("salaryBreakUpListForCreateUpdate",salaryBreakUplist);
			}
			model.addAttribute("strIncomeAge", strIncomeAge);
			model.addAttribute("calCulationFor", hidVal);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return "erp/editIncomeTaxSlab";
	}		
	
//	@RequestMapping(value="/editIncomeTaxSalarySlabDetails", method = RequestMethod.POST)
//	public String editIncomeTaxSalarySlabDetails(HttpServletRequest request, 
//												 HttpServletResponse response, 
//												 ModelMap model, 
//												 @ModelAttribute("sessionObject") SessionObject sessionObject,
//												 @RequestParam("hidVal") String hidVal
//												 ){		
//		try{
//			IncomeAge incomeAge = new IncomeAge();
//			incomeAge.setIncomeAgeCode(request.getParameter("incomeAge"));
//			
//			List<IncomeTaxSlab> incomeTaxSlabList = null;
//			String[] params = request.getParameterValues("itParameter");	
//			
//			IncomeTaxSlabDetails itIncomeTaxSlabDetails = new IncomeTaxSlabDetails();
//			itIncomeTaxSlabDetails.setUpdatedBy(sessionObject.getUserId());
//			itIncomeTaxSlabDetails.setStatus(hidVal.trim());
//			itIncomeTaxSlabDetails.setIncomeTaxSlabDetailsName(request.getParameter("salaryBreakUp"));			
//			itIncomeTaxSlabDetails.setIncomeAge(incomeAge);
//			
//			if(null != params){
//				incomeTaxSlabList = new ArrayList<IncomeTaxSlab>();
//				for(int j = 1 ; j < request.getParameterValues(params[0]).length; j++){
//					IncomeTaxSlab itSlab = new IncomeTaxSlab();
//					List<IncomeTaxParameters> itParameterList = new ArrayList<IncomeTaxParameters>();
//					itSlab.setIncomeTaxSlabCode("ITSLB-"+(j+1));
//					itSlab.setUpdatedBy(sessionObject.getUserId());
//					for(int i = 0; i < params.length; i++){	
//					//	System.out.println("Paramter:"+params[i]);
//					//	System.out.println("figure type:"+request.getParameterValues(params[i])[0]);
//					//	System.out.println("figure:"+request.getParameterValues(params[i])[j]);						
//						IncomeTaxParameters itparameters = new IncomeTaxParameters();
//						itparameters.setIncomeAge(request.getParameter("incomeAge"));
//						itparameters.setIncomeTaxParamCode(params[i]);
//						itparameters.setFigureType(request.getParameterValues(params[i])[0]);					
//						itparameters.setAmount(Double.parseDouble(request.getParameterValues(params[i])[j]));
//						itParameterList.add(itparameters);
//					}
//				//	System.out.println("--------END OF "+(j+1)+" ROW----------");						
//					itSlab.setIncomeTaxParameterList(itParameterList);
//					incomeTaxSlabList.add(itSlab);
//				}
//				itIncomeTaxSlabDetails.setIncomeTaxSlabList(incomeTaxSlabList);
//				String updateResponse = erpService.editIncomeTaxSalarySlab(itIncomeTaxSlabDetails);
//				System.out.println("/////   submitResponse  :  "+updateResponse);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return viewParameterOfIncomeTaxSalarySlab(request, response, model,hidVal);
//	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getCalculatedValueOfGivenFormula", method = RequestMethod.GET)
	public String getCalculatedValueOfGivenFormula(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("finalFormula") String finalFormulaStr) {
		//System.out.println("ABC:"+finalFormulaStr);
		logger.info("In getCalculatedValueOfGivenFormula() method of ERPController: ");
		String output = null;
		try {
			Utility util = new Utility();		
			double value = util.evaluate(finalFormulaStr);
			output = Double.toString(value);
			//System.out.println("OUTPUT:"+output);
		} catch (Exception e) {
			logger.error("Error in ERPController getCalculatedValueOfGivenFormula() method for Exception: ", e);
		}
		return (new Gson().toJson(output));
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCalculatedExactValueOfGivenFormula", method = RequestMethod.GET)
	public String getCalculatedExactValueOfGivenFormula(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("finalFormula") String finalFormulaStr) {
		logger.info("In getCalculatedExactValueOfGivenFormula() method of ERPController: ");
		String output = null;
		try {
			String[] SplitedValue = finalFormulaStr.split("_");
			//System.out.println("SplitedValue[0]"+SplitedValue[0]+SplitedValue[1]);
			Utility util = new Utility();		
			String value = util.evaluateAnother(SplitedValue[0],SplitedValue[1]);
			//System.out.println("value"+value);
			output = value;
	
		} catch (Exception e) {
			logger.error("Error in ERPController getCalculatedExactValueOfGivenFormula() method for Exception: ", e);
		}
		return (new Gson().toJson(output));
	}	
	
	@ResponseBody
	@RequestMapping(value = "/getDesignationBasedOnDesignationType", method = RequestMethod.GET)
	public String getDesignationBasedOnDesignationType(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("designationTypeCode") String designationTypeCode) {
		logger.info("In getDesignationBasedOnDesignationType() method of erpController: ");
		String output = null;
		try {
			output = erpService.getDesignationBasedOnDesignationType(designationTypeCode);

		} catch (Exception e) {
			logger.error("Error in erpController checkDesignationType() method for Exception: ", e);
		}
		return (new Gson().toJson(output));
	}
	
	@ResponseBody
	@RequestMapping(value = "/getLevelBasedOnDesignation", method = RequestMethod.GET)
	public String getLevelBasedOnDesignation(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("designationCode") String designationCode) {
		logger.info("In getLevelBasedOnDesignation() method of erpController: ");
		String output = null;
		try {
			output = erpService.getLevelBasedOnDesignation(designationCode);

		} catch (Exception e) {
			logger.error("Error in erpController checkDesignationType() method for Exception: ", e);
		}
		return (new Gson().toJson(output));
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSalaryTemplateName", method = RequestMethod.GET)
	public String getSalaryTemplateName(HttpServletRequest request,
										HttpServletResponse response, 
										ModelMap model,
			@RequestParam("salaryTemplateName") String salaryTemplateName) {
		logger.info("In getsalaryTemplateName() method of ERPController: ");
		String output = null;
		try {
			output = erpService.checkSalaryTemplateName(salaryTemplateName);
	
		} catch (Exception e) {
			logger.error("Error in ERPController getsalaryTemplateName() method for Exception: ",e);
		}
		return (new Gson().toJson(output));
	}
	
	@RequestMapping(value = "/viewEditTemplate", method = RequestMethod.GET)
	public ModelAndView viewEditTemplate(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										@ModelAttribute("templateCode") String templateCode){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("erp/salaryTemplateDetails");
		try{
			SalaryTemplate salaryTemplate = erpService.getSalaryTemplateDetails(templateCode);
			if(salaryTemplate != null){
				model.addAttribute("salaryTemplate", salaryTemplate);
			}else{
				model.addAttribute("status", "Template Details Not Find");
			}
			
			List<SalaryBreakUp> salaryBreakUpList = erpService.getSalaryBreakUpList();
			if(salaryBreakUpList != null && salaryBreakUpList.size() != 0){
				model.addAttribute("salaryBreakUpList", salaryBreakUpList);
			}else{
				model.addAttribute("status", "Salary Break Up Not Found");
			}
		}catch(Exception e){
			logger.error("Exception in viewEditTemplate() from Controller", e);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/updateSalaryTemplate", method = RequestMethod.POST)
	public ModelAndView updateSalaryTemplate(HttpServletRequest request,
							HttpServletResponse response, 
							ModelMap model,						
							@RequestParam(value = "salaryTemplateName", required = false) String salaryTemplateName,
							@RequestParam(value = "salaryTemplateCode", required = false) String salaryTemplateCode,
							@RequestParam(value = "salaryBreakUpName", required = false) String[] salaryBreakUpName,
							@ModelAttribute("sessionObject") SessionObject sessionObject){	
		try{			
			List<SalaryTemplateDetails> salaryTemplateDetailsList = new  ArrayList<SalaryTemplateDetails>();
			
			for(int i= 0; i< salaryBreakUpName.length; i++){				
				if(salaryBreakUpName[i] != null && salaryBreakUpName[i].length() != 0){
					if(request.getParameter(salaryBreakUpName[i])!= null && request.getParameter(salaryBreakUpName[i]).length() != 0){
						SalaryTemplateDetails salaryTemplateDetails = new SalaryTemplateDetails();
						salaryTemplateDetails.setUpdatedBy(sessionObject.getUserId());
						salaryTemplateDetails.setSalaryTemplate(salaryTemplateCode);
						salaryTemplateDetails.setSalaryBreakUpName(salaryBreakUpName[i]);
						salaryTemplateDetails.setSalaryHeadFormula(request.getParameter(salaryBreakUpName[i]));						
						salaryTemplateDetailsList.add(salaryTemplateDetails);
					}					
				}
			}			
			
			SalaryTemplate salaryTemplate = new SalaryTemplate();
			salaryTemplate.setUpdatedBy(sessionObject.getUserId());
			salaryTemplate.setSalaryTemplateCode(salaryTemplateCode);
			salaryTemplate.setSalaryTemplateName(salaryTemplateName.trim().toUpperCase());
			salaryTemplate.setSalaryTemplateDesc(salaryTemplateName.trim().toUpperCase());
			salaryTemplate.setSalaryTemplateDetailsList(salaryTemplateDetailsList);
			String status = erpService.updateSalaryTemplate(salaryTemplate);
			//System.out.println("Update Status :: "+status);
			model.addAttribute("status", status);
		}catch(Exception e){
			logger.error("Exception in salaryTemplate() from Controller", e);
			e.printStackTrace();
		}
		return salaryTemplateList(request, response, model);
	}
	
	@RequestMapping(value="/incomeTaxSalarySlab.html", method = RequestMethod.GET)
	public ModelAndView incomeTaxSalarySlab(HttpServletRequest request, 
											HttpServletResponse response,
											Model model,
											@RequestParam("calculationFor") String hidVal,											
											@RequestParam("incomeAge") String strIncomeAge
											){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("erp/incomeTaxSalarySlab");
		try{
			com.qts.icam.model.common.FinancialYear financialYear = commonService.getFinancialYear();
			if(financialYear != null){
				model.addAttribute("financialYear", financialYear);
			}			
			List<IncomeTaxParameters> itParameterList = erpService.getIncomeTaxParameter();
			if(itParameterList != null && itParameterList.size() != 0){
				model.addAttribute("itParameterList", itParameterList);
			}
			IncomeTaxSlabDetails slabCalculationParameter = erpService.getSlabCalculationParameter(hidVal);
			if(slabCalculationParameter != null){
				model.addAttribute("slabCalculationParameter", slabCalculationParameter);
			}
			List<SalaryBreakUp> salaryBreakUplist = erpService.getSalaryBreakUp();
			if (salaryBreakUplist != null && salaryBreakUplist.size() != 0) {
				model.addAttribute("salaryBreakUpListForCreateUpdate",salaryBreakUplist);
			}
			model.addAttribute("calCulationFor", hidVal);
			model.addAttribute("strIncomeAge", strIncomeAge);
		}catch(Exception e){
			e.printStackTrace();
		}		
		return modelAndView;
	}

	
	@RequestMapping(value="/submitIncomeTaxSalarySlab", method = RequestMethod.POST)
	public String submitIncomeTaxSalarySlab(HttpServletRequest request, 
											HttpServletResponse response, 
											ModelMap model, 
											@ModelAttribute("sessionObject") SessionObject sessionObject,
											@RequestParam("hidVal") String hidVal){		
		try{
			List<IncomeTaxSlab> incomeTaxSlabList = null;
			String[] params = request.getParameterValues("itParameter");	
			//System.out.println("..................."+request.getParameter("incomeAge"));
			IncomeAge incomeAge = new IncomeAge();
			incomeAge.setIncomeAgeCode(request.getParameter("incomeAge"));
			
			IncomeTaxSlabDetails itIncomeTaxSlabDetails = new IncomeTaxSlabDetails();
			itIncomeTaxSlabDetails.setUpdatedBy(sessionObject.getUserId());
			itIncomeTaxSlabDetails.setStatus(hidVal.trim());
			itIncomeTaxSlabDetails.setIncomeTaxSlabDetailsName(request.getParameter("salaryBreakUp"));
			itIncomeTaxSlabDetails.setIncomeAge(incomeAge);
			
			if(null != params){
				incomeTaxSlabList = new ArrayList<IncomeTaxSlab>();
				for(int j = 1 ; j < request.getParameterValues(params[0]).length; j++){
					IncomeTaxSlab itSlab = new IncomeTaxSlab();
					List<IncomeTaxParameters> itParameterList = new ArrayList<IncomeTaxParameters>();
					itSlab.setIncomeTaxSlabCode("ITSLB-"+(j+1));
					itSlab.setUpdatedBy(sessionObject.getUserId());
					for(int i = 0; i < params.length; i++){	
						//System.out.println("Paramter:"+params[i]);
						//System.out.println("figure type:"+request.getParameterValues(params[i])[0]);
						//System.out.println("figure:"+request.getParameterValues(params[i])[j]);						
						IncomeTaxParameters itparameters = new IncomeTaxParameters();
						itparameters.setIncomeAge(request.getParameter("incomeAge"));
						itparameters.setIncomeTaxParamCode(params[i]);
						itparameters.setFigureType(request.getParameterValues(params[i])[0]);					
						itparameters.setAmount(Double.parseDouble(request.getParameterValues(params[i])[j]));
						itParameterList.add(itparameters);
					}
					//System.out.println("--------END OF "+(j+1)+" ROW----------");						
					itSlab.setIncomeTaxParameterList(itParameterList);
					incomeTaxSlabList.add(itSlab);
				}
				itIncomeTaxSlabDetails.setIncomeTaxSlabList(incomeTaxSlabList);
				String submitResponse = erpService.submitIncomeTaxSalarySlab(itIncomeTaxSlabDetails);
				//System.out.println("/////   submitResponse  :  "+submitResponse);
			}					
		}catch(Exception e){
			e.printStackTrace();
		}
		return viewParameterOfIncomeTaxSalarySlab(request, response, model,hidVal);
	}	
	
	
	@RequestMapping(value="/editIncomeTaxSalarySlab.html", method = RequestMethod.GET)
	public ModelAndView editIncomeTaxSalarySlab(HttpServletRequest request, 
											HttpServletResponse response,
											Model model,
											@RequestParam("calculationFor") String hidVal
											){
		ModelAndView modelAndView = new ModelAndView();
		List<IncomeAge> incomeAgeList = commonService.getIncomeAgeList();
		if(incomeAgeList != null && incomeAgeList.size() != 0){
			//System.out.println("SIZE:"+incomeAgeList.size());
			model.addAttribute("incomeAgeList", incomeAgeList);
		}
		model.addAttribute("calCulationFor", hidVal);		
		modelAndView.setViewName("erp/viewIncomeTaxSlab");			
		return modelAndView;
	}
	
	@RequestMapping(value = "/staffSalaryMappingAndPromotion", method = RequestMethod.GET)
	public String staffSalaryMappingAndPromotion(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			List<SalaryBreakUp> salaryBreakUpList = erpService.getSalaryBreakUpList();
			if(salaryBreakUpList != null && salaryBreakUpList.size() != 0){
				model.addAttribute("salaryBreakUpList", salaryBreakUpList);
			}else{
				model.addAttribute("status", "Salary Break Up Not Found");
			}

		}catch(Exception e){
			logger.error("Exception in staffSalaryMappingAndPromotion() from ErpController", e);
			e.printStackTrace();
		}
		return "erp/StaffSalaryMappingAndPromotion";
	}
	
	
	@RequestMapping(value = "/submitStaffSalaryMappingAndPromotion", method = RequestMethod.POST)
	public String submitStaffSalaryMappingAndPromotion(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("submitBttn") String link, Resource resource) {
		try {
			model.addAttribute("link", link);
			//System.out.println("ABBBC");
			Employee staffDetailsFromDB = erpService.getStaffDetailsForPromotionAndSalaryMapping(resource);
			
			//OUTPUT FOR UPDATE
			if(staffDetailsFromDB != null){
				if(staffDetailsFromDB.getSalaryTemplate() != null){
					if(staffDetailsFromDB.getSalaryTemplate().getSalaryTemplateCode() != null){
						//System.out.println("SALRY TEMPLATE CODE:"+staffDetailsFromDB.getSalaryTemplate().getSalaryTemplateCode());
					}
				}
			}			
			
			if (staffDetailsFromDB != null) {
				model.addAttribute("staffDetailsFromDB", staffDetailsFromDB);
			} else {
				//System.out.println("staffDetailsFromDB is NULL");
				model.addAttribute("ResultError", "Result not available....");
			}
			model.addAttribute("resource", resource);
		}catch(Exception e){
			logger.error("Exception in staffSalaryMappingAndPromotion() from ErpController", e);
			e.printStackTrace();
		}
		return "erp/StaffSalaryMappingAndPromotion";
	}
	
	@RequestMapping(value = "/updateAndInsertSalryAmount", method = RequestMethod.POST)
	public ModelAndView updateAndInsertSalryAmount(ModelMap model,
			@ModelAttribute("employee") Employee employee,
			@ModelAttribute("designationTypeHid") String designationTypeHid,
			@ModelAttribute("designationHid") String designationHid,
			@ModelAttribute("levelHid") String levelHid,
			@ModelAttribute("templateHid") String templateHid,
			@RequestParam("salBreakUpHead") String[] strSalaryBreakUpName,
			@RequestParam("amount") String[] strAmount,
			@RequestParam("submitBttnForUpdateAndPromote") String buttonStatus,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In updateAndInsertSalryAmount() method of ErpController: ");
		//System.out.println("inside update and delete salary amount");
		try {
			List<SalaryBreakUp> salaryBreakUpList = new ArrayList<SalaryBreakUp>();
			if (strSalaryBreakUpName != null && strSalaryBreakUpName.length != 0) {
				for (int i = 0; i < strSalaryBreakUpName.length; i++) {
					SalaryBreakUp salaryBreakUp = new SalaryBreakUp();
					salaryBreakUp.setUpdatedBy(sessionObject.getUserId());
					if (strAmount != null && strAmount.length != 0) {
						salaryBreakUp.setSalaryBreakUpName(strSalaryBreakUpName[i]);
						if (strAmount[i] != null && strAmount[i].trim().length() != 0) {
							salaryBreakUp.setAmount(Double.parseDouble(strAmount[i]));
						} else {
							salaryBreakUp.setAmount(0);
						}
					}
					salaryBreakUpList.add(salaryBreakUp);
				}
				employee.setSalaryBreakUpList(salaryBreakUpList);
			}
			Resource resource = new Resource();
			resource.setDesignationType(designationTypeHid);
			resource.setDesignation(designationHid);
			resource.setDesignationLevel(levelHid);
			resource.setSalaryTemplateCode(templateHid);
			resource.setUserId(employee.getResource().getUserId());
			employee.setStatus(buttonStatus);
			employee.setResource(resource);
			employee.setUpdatedBy(sessionObject.getUserId());//Modified by naimisha 08082017
			logger.info("In BackOfficeController updateAndInsertSalryAmount() method: calling createErpSalaryMapping(Staff staff) method in erpServiceImpl.java");
			employee = erpService.createErpSalaryMapping(employee);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in ErpController updateAndInsertSalryAmount() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in ErpController updateAndInsertSalryAmount() method for Exception: ",e);
		}
		return new ModelAndView("erp/StaffSalaryMappingAndPromotion");
	}
	
	/**
	 * this method return to viewStaffSalarySlip.jsp for view staff salary
	 * sleep.
	 * 
	 * @return String
	 */
	
	//Modified By Naimisha 31072017
	@RequestMapping(value = "/viewStaffSalarySlip", method = RequestMethod.GET)
	public String viewStaffSalarySlip(Model model) {
		logger.info("In viewStaffSalarySlip() method of BackOfficeController: ");
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
		
		return "erp/viewStaffSalarySlipNew";
	}
	
	//================================================TEACHER INTERVIEW PROCESS==============================================================
	
	/**
	 * This method return list of teacher and Teaching Level to
	 * teacherInterviewSchedule.jsp for set interview schedule
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/teacherInterviewSchedule", method = RequestMethod.GET)
	public ModelAndView teacherInterviewSchedule(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Map<String, Object> parameters) {
		String strView = "erp/teacherInterviewSchedule";
		try {
			logger.info("In teacherInterview() method of BackOfficeController:");
			parameters.put("ResourceType", "TEACHER");
			List<Employee> staffList = erpService.getStaffList(parameters);
			List<TeachingLevel> teachingLevelList = commonService.getTeachingLevelList();
			if (staffList != null && staffList.size() != 0 && teachingLevelList != null && teachingLevelList.size() != 0) {
				model.addAttribute("resourceInterviewSchedule", staffList);
				model.addAttribute("teachingLevelList", teachingLevelList);				
			}
			List<Venue>venueList = academicsService.getVenueListForExam();
			model.addAttribute("venueList", venueList);	
			
		} catch (Exception e) {
			logger.error("Exception In teacherInterviewSchedule() method of BackOfficeController: Exception", e);
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * this method set teacher interview schedule for a particular candidate
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param staff
	 * @param strexaminerName
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/setTeacherInterviewSchedule", method = RequestMethod.POST)
	public ModelAndView setTeacherInterviewSchedule(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("FORM") UploadFile uploadResume,			
			@ModelAttribute("employee") Employee employee,
			@RequestParam(value = "examinerName", required = false) String strexaminerName,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			Map<String, Object> parameters
			) {	
			//System.out.println(request.getParameter("examinerName"));
			//System.out.println("hello....."+strexaminerName.length);
		try {		
			parameters.put("ResourceType", "TEACHER");
			if (uploadResume.getFileData() != null) {
				logger.info("In setTeacherInterviewSchedule() method of ERPController to Upload Teacher Resume: ");
				String strRootPath = rootPath;
				//String path = rootPath + teacherCandidateResumePath + staff.getResource().getFirstName()+"_"+employee.getResource().getLastName() + "/";
				String resourceId = employee.getResource().getFirstName()+"_"+employee.getResource().getLastName();
				Utility util = new Utility();					
//				List<Attachment> attachmentList = util.uploadDoc(path, uploadResume, resourceId, strRootPath);					
//				if(attachmentList != null && attachmentList.size() != 0){						
//					model.addAttribute("uploadStatus", "Resume Successfully Uploaded");
//				}else{
//					model.addAttribute("uploadStatus", "Resume Upload Fails");
//				}
			}
			logger.info("In setTeacherInterviewSchedule() method of BackOfficeController: ");
			/*if (strexaminerName != null) {
				
					for (int i = 0; i < strexaminerName.length; i++) {
						Resource resource = new Resource();
						resource.setUserId(strexaminerName[i]);
						resourceList.add(resource);//Modified By Naimisha 21062017
					}
					employee.setResourceList(resourceList);
			}*/
			List<Resource> resourceList = new ArrayList<Resource>();
			Resource resource = new Resource();
			resource.setUserId(strexaminerName);
			resourceList.add(resource);
			employee.setResourceList(resourceList);
			employee.setUpdatedBy(sessionObject.getUserId());
			String insertStatus = erpService.setTeacherInterviewSchedule(employee);	
			List<Employee> staffList = erpService.getStaffList(parameters);
			List<TeachingLevel> teachingLevelList = commonService.getTeachingLevelList();
			model.addAttribute("resourceInterviewSchedule", staffList);
			model.addAttribute("teachingLevelList", teachingLevelList);
			model.addAttribute("insertStatus", insertStatus);
			/**Added by @author Saif.Ali
			 * Date-13-03-2018*/
			String teacherFirstName = request.getParameter("resource.firstName");
			String teacherMiddleName = request.getParameter("resource.middleName");
			String teacherLastName = request.getParameter("resource.lastName");
			String teacherGender = request.getParameter("resource.gender");
			String teacherDateOfBirth = request.getParameter("resource.dateOfBirth");
			String teacherHighestQualification = request.getParameter("qualification.qualificationName");
			String teacherSpecialization = request.getParameter("qualification.specialization");
			String teacherExperience = request.getParameter("experience");
			String teacherDateOfInterview = request.getParameter("dateOfInterview");
			String teacherTimeOfInterview = request.getParameter("timeOfInterview");
			String teacherTeachingLevel = request.getParameter("teachingLevel.teachingLevelName");
			String teacherExaminerName = request.getParameter("examinerName");
			String teacherVenue = request.getParameter("roomNumber");
			String teacherReferral = request.getParameter("referredBy");
			String description = "" ;
			
			if(insertStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("INSERT EMPLOYEE INTERVIEW DETAILS");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor("Teacher Name :: " + teacherFirstName + teacherMiddleName + teacherLastName);
				if(teacherFirstName != "")
				{
					description = description + ("Teacher First Name :: " + teacherFirstName + " ; ");
				}
				if(teacherMiddleName != "")
				{
					description = description + ("Teacher Middle Name :: " + teacherMiddleName + " ; ");
				}
				if(teacherLastName != "")
				{
					description = description + ("Teacher Last Name :: " + teacherLastName + " ; ");
				}
				if(teacherGender != "")
				{
					description = description + ("Teacher Gender :: " + teacherGender + " ; ");
				}
				if(teacherDateOfBirth != "")
				{
					description = description + ("Teacher Date Of Birth :: " + teacherDateOfBirth + " ; ");
				}
				if(teacherHighestQualification != "")
				{
					description = description + ("Teacher Highest Qualification :: " + teacherHighestQualification + " ; ");
				}
				if(teacherSpecialization != "")
				{
					description = description + ("Teacher Specialization :: " + teacherSpecialization + " ; ");
				}
				if(teacherExperience != "")
				{
					description = description + ("Teacher Experience :: " + teacherExperience + " ; ");
				}
				if(teacherDateOfInterview != "")
				{
					description = description + ("Teacher Date of Interview :: " + teacherDateOfInterview + " ; ");
				}
				if(teacherTimeOfInterview != "")
				{
					description = description + ("Teacher Time Of Interview :: " + teacherTimeOfInterview + " ; ");
				}
				if(teacherTeachingLevel != "")
				{
					description = description + ("Teacher Teaching Level :: " + teacherTeachingLevel + " ; ");
				}
				if(teacherExaminerName != "")
				{
					description = description + ("Teacher Examiner Name :: " + teacherExaminerName + " ; ");
				}
				if(teacherVenue != "")
				{
					description = description + ("Teacher venue :: " + teacherVenue + " ; ");
				}
				if(teacherReferral != "")
				{
					description = description + ("Teacher Referral :: " + teacherReferral + " ; ");
				}
				
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 3836 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception e) {
			logger.error("Exception In setTeacherInterviewSchedule() method of BackOfficeController: Exception", e);
		} 
		return teacherInterviewScheduleList(request,response,model); //Naimisha 20062017 
	}
	
	/**
	 * this method get teacher Interview Schedule List
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/teacherInterviewScheduleList", method = RequestMethod.GET)
	public ModelAndView teacherInterviewScheduleList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {		
		ModelAndView mav = new ModelAndView("erp/teacherInterviewScheduleList");
		try {
			logger.info("In teacherInterviewScheduleList() method of BackOfficeController:");
			List<Employee> empList = erpService.getTeacherInterviewScheduleList();
			if (empList != null && empList.size() != 0) {
				model.addAttribute("empList", empList);					
			}
		} catch (Exception e) {
			logger.error("Exception In teacherInterviewScheduleList() method of BackOfficeController: Exception", e);
		}
		return mav;
	}
	
	/**
	 * This method get teacher interview schedule details for a particular
	 * candidate for edit
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param staff
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/editTeacherInterviewSchedule", method = RequestMethod.GET)
	public ModelAndView editTeacherInterviewSchedule(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @ModelAttribute("employee") Employee employee) {
		String strView = "";
		try {
			logger.info("In editTeacherInterviewSchedule() method of ErpController: ");
			employee = erpService.getTeacherInterviewScheduleDetails(employee);			
			List<TeachingLevel> teachingLevelList = commonService.getTeachingLevelList();
			if (employee != null && teachingLevelList != null && teachingLevelList.size() != 0) {
				model.addAttribute("resourceInterviewSchedule", employee);
				model.addAttribute("teachingLevelList", teachingLevelList);
				strView = "erp/editTeacherInterviewSchedule";
			} else {
				model.addAttribute("ResultError", "Result not available....");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error("Exception In teacherInterviewSchedule() method of ErpController: Exception", e);
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * this method update Teacher Interview Schedule details for a particular
	 * candidate.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param staff
	 * @param strexaminerName
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/updateTeacherInterviewSchedule", method = RequestMethod.POST)
	public ModelAndView updateTeacherInterviewSchedule(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("staff") Employee employee,
			@RequestParam(value = "examinerName", required = false) String[] strexaminerName,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = new ModelAndView("erp/teacherInterviewScheduleList");		
		try {
			logger.info("In setTeacherInterviewSchedule() method of BackOfficeController: ");
			if (strexaminerName != null) {
				List<Resource> resourceList = new ArrayList<Resource>();
				for (int i = 0; i < strexaminerName.length; i++) {
					Resource resource = new Resource();
					resource.setUserId(strexaminerName[i]);
					resourceList.add(resource);
				}
				employee.setResourceList(resourceList);
			}
			employee.setUpdatedBy(sessionObject.getUserId());
			List<Employee> empList = erpService.updateTeacherInterviewSchedule(employee);
			if (empList != null && empList.size() != 0) {
				model.addAttribute("empList", empList);	
				model.addAttribute("insertStatus", "success");
			}else{
				model.addAttribute("insertStatus", "fail");
			}
			/**Added by @author Saif.Ali
			 * Date-27-03-2018*/
			String candidateCode = request.getParameter("employeeCode");
			String oldTeacherFirstName = request.getParameter("oldTeacherFirstName");
			String oldTeacherMiddleName  = request.getParameter("oldTeacherMiddleName");
			String oldTeacherLastName = request.getParameter("oldTeacherLastName");
			String oldTeacherHighestQualification = request.getParameter("oldTeacherHighestQualification");
			String oldTeacherSpecialization = request.getParameter("oldTeacherSpecialization");
			String oldTeacherExperience = request.getParameter("oldTeacherExperience");
			String oldTeacherDateOfInterview = request.getParameter("oldTeacherDateOfInterview");
			String oldTeacherInterviewTime = request.getParameter("oldTeacherInterviewTime");
			String oldTeacherRoomNumber = request.getParameter("oldTeacherRoomNumber");
			String oldTeacherReferredBy = request.getParameter("oldTeacherReferredBy");	
			
			String newTeacherFirstName = request.getParameter("resource.firstName");
			String newTeacherMiddleName  = request.getParameter("resource.middleName");
			String newTeacherLastName = request.getParameter("resource.lastName");
			String newTeacherGender = request.getParameter("resource.gender");
			String newTeacherDateOfBirth = request.getParameter("resource.dateOfBirth");
			String newTeacherQualification = request.getParameter("qualification.qualificationName");
			String newTeacherSpecialization = request.getParameter("qualification.specialization");
			String newTeacherExperience = request.getParameter("experience");
			String newTeacherDateOfInterview = request.getParameter("dateOfInterview");
			String newTeacherTimeOfInterview = request.getParameter("timeOfInterview");	
			String newTeacherTeachingLevel = request.getParameter("teachingLevel.teachingLevelName");	
			String newTeacherexaminerName = request.getParameter("examinerName");	
			String newTeacherRoomNumber = request.getParameter("roomNumber");	
			String newTeacherReferredBy = request.getParameter("referredBy");
			String description = "";
			
			if (empList != null && empList.size() != 0) {
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT SOCIAL CATEGORY");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor(candidateCode);
				if(!(oldTeacherFirstName.equalsIgnoreCase(newTeacherFirstName)))
				{
					description = description + (" Teacher First Name :: " + oldTeacherFirstName + " updated to new first name as :: " + newTeacherFirstName + " ; ");
				}
				if(!(oldTeacherMiddleName.equalsIgnoreCase(newTeacherMiddleName)))
				{
					description = description + (" Teacher Middle Name :: " + oldTeacherMiddleName + " updated to new middle name as :: " + newTeacherMiddleName+ " ; ");
				}
				if(!(oldTeacherLastName.equalsIgnoreCase(newTeacherLastName)))
				{
					description = description + (" Teacher Last Name :: " + oldTeacherLastName + " updated to new last name as :: " + newTeacherLastName+ " ; ");
				}
				if(newTeacherGender != "")
				{
					description = description + (" Teacher Gender :: " + newTeacherGender+ " ; ");
				}
				if(newTeacherDateOfBirth != "")
				{
					description = description + (" Teacher Date Of Birth :: " + newTeacherDateOfBirth+ " ; ");
				}
				if(!(oldTeacherHighestQualification.equalsIgnoreCase(newTeacherQualification)))
				{
					description = description + (" Teacher Highest Qualification :: " + oldTeacherHighestQualification + " updated to new Teacher Qualification as :: " + newTeacherQualification);
				}
				if(!(oldTeacherSpecialization.equalsIgnoreCase(newTeacherSpecialization)))
				{
					description = description + (" Teacher Specialization :: " + oldTeacherSpecialization + " updated to new specialization as :: " + newTeacherSpecialization);
				}
				if(!(oldTeacherExperience.equalsIgnoreCase(newTeacherExperience)))
				{
					description = description + (" Teacher Experience :: " + oldTeacherExperience + " updated to new teacher experience as :: " + newTeacherExperience);
				}
				if(!(oldTeacherDateOfInterview.equalsIgnoreCase(newTeacherDateOfInterview)))
				{
					description = description + (" Teacher Date Of Interview :: " + oldTeacherDateOfInterview + " updated to new Date of Interview as :: " + newTeacherDateOfInterview);
				}
				if(!(oldTeacherInterviewTime.equalsIgnoreCase(newTeacherTimeOfInterview)))
				{
					description = description + (" Teacher Interview Time :: " + oldTeacherInterviewTime + " updated to new Time of Interview as :: " + newTeacherTimeOfInterview);
				}
				if(newTeacherTeachingLevel != "")
				{
					description = description + (" Teacher Teaching Level :: " + newTeacherTeachingLevel + " ; ");
				}
				if(newTeacherexaminerName != "")
				{
					description = description + (" Teacher Examiner name :: " + newTeacherexaminerName + " ; ");
				}
				if(!(oldTeacherRoomNumber.equalsIgnoreCase(newTeacherRoomNumber)))
				{
					description = description + (" Teacher Room Number :: " + oldTeacherRoomNumber + " updated to new Room Number as :: " + newTeacherRoomNumber + " ; ");
				}
				if(!(oldTeacherReferredBy.equalsIgnoreCase(newTeacherReferredBy)))
				{
					description = description + (" Teacher Referred :: " + oldTeacherReferredBy + " updated to new Teacher Referred as :: " + newTeacherReferredBy + " ; ");
				}
				
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 4043 :: ERPController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception e) {
			logger.error("Exception In teacherInterviewScheduleList() method of BackOfficeController: Exception",e);
		}
		return mav;
	}
	
	/**
	 * this method return to teacherInterview.jsp for submit teacher interview
	 * Details.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/teacherInterview", method = RequestMethod.GET)
	public ModelAndView teacherInterview(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In teacherInterview() method of BackOfficeController: ");
			List<Employee> empList = erpService.getCandidateId();
			//System.out.println("CANDIDIATE LIST:"+empList.size());
			if (empList != null && empList.size() != 0) {
				model.addAttribute("staffCandidateId", empList);
			}
		} catch (Exception e) {
			logger.error("In teacherInterview() method of BackOfficeController:Exception", e);
		}
		return new ModelAndView("erp/teacherInterview");
	}
	
	/**
	 * this method get candidate name for a particular candidate id
	 * 
	 * @param strParam
	 * @param staff
	 * @return String
	 */
	@RequestMapping(value = "/getCandidateName", method = RequestMethod.GET)
	public @ResponseBody
	String getNameForFormIdByAjax(@RequestParam("strParam") String strParam, Employee employee) {
		logger.info("In getCandidateName() method of BackOfficeController: ");
		employee.setEmployeeCode(strParam);
		return (new Gson().toJson(erpService.getCandidateName(employee)));
	}
	
	/**
	 * this method submit interview marks details and selection status for a
	 * particular candidate
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param staff
	 * @param strSubject
	 * @param strMarks
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/submitTeacherInterviewDetails", method = RequestMethod.POST)
	public String submitTeacherInterviewDetails(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("employee") Employee employee,
			@RequestParam(value = "subject", required = false) String[] strSubject,
			@RequestParam(value = "subMarks", required = false) String[] strMarks,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String updateStatus = "fail";
		//System.out.println(employee.getEmployeeCode()+"EMPLOYEE STATUS:"+employee.getStatus());
		try {			
			logger.info("In submitTeacherInterviewDetails() method of BackOfficeController: ");
			ArrayList<Marks> marksList = new ArrayList<Marks>();
			if (strSubject != null && strMarks != null) {
				for (int i = 0; i < strSubject.length; i++) {
					Marks marks = new Marks();
					if (strSubject[i] != null && strMarks[i] != null) {						
						marks.setSubjectName(strSubject[i]);
						marks.setAvgMarks(Double.parseDouble(strMarks[i]));
					}
					marksList.add(marks);
				}
				employee.setMarksList(marksList);
				employee.setUpdatedBy(sessionObject.getUserId());
				updateStatus = erpService.submitTeacherInterviewDetails(employee);
			}
			/**Added by @author Saif.Ali
			 * Date-27-03-2018*/
			String employeeCode = request.getParameter("employeeCode");
			String employeeName = request.getParameter("name");
			String employeeQualification = request.getParameter("qualification.qualificationName");
			String employeeSpecialization = request.getParameter("qualification.subjectSpecilization");
			String employeeReferredBy = request.getParameter("referredBy");
			String employeeComment = request.getParameter("comment");
			String employeeStatus = request.getParameter("status");
			String description = "" ;
			
			if(updateStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("ENTER TEACHER INTERVIEW");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("ERP");
				updateLog.setUpdatedFor("Employee Code ::" + employeeCode + " employee Name :: " + employeeName);
				description = description + ("Employee Code ::" + employeeCode + " employee Name :: " + employeeName);
				if (strSubject != null && strMarks != null) 
				{
					for (int i = 0; i < strSubject.length; i++) 
					{
						if (strSubject[i] != null && strMarks[i] != null) 
						{
							description = description + (" with subject :: " + strSubject[i] + " with marks :: " + strMarks[i] + " ; ");
						}
					}
				}
				if(employeeComment != "")
				{
					description = description + (" with comment :: " + employeeComment + " ; ");
				}
				if(employeeStatus != "")
				{
					description = description + (" with status :: " + employeeStatus + " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 4177 :: ERPController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (NullPointerException e) {
			logger.error(
					"Exception In submitTeacherInterviewDetails() method of BackOfficeController: NullPointerException",
					e);
		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error(
					"Exception In submitTeacherInterviewDetails() method of BackOfficeController: ArrayIndexOutOfBoundsException",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Exception In submitTeacherInterviewDetails() method of BackOfficeController: Exception",
					e);
		}
		return teacherInterviewList(request, response, model); //NAimisha 20062017
	}
	
	/**
	 * this method get teacher interview List for update selection status or
	 * submit staff form
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/teacherInterviewList", method = RequestMethod.GET)
	public String teacherInterviewList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {		
		try {
			logger.info("In teacherInterviewList() method of BackOfficeController: ");
			List<Employee> teacherInterviewList = erpService.getTeacherInterviewList();			
				model.addAttribute("teacherInterviewList",teacherInterviewList);			
		} catch (Exception e) {
			logger.error("Exception In teacherInterviewList() method of BackOfficeController: Exception", e);
		}		
		return "erp/teacherInterviewList";
	}
	
	/**
	 * this method get staff details form information for a particular candidate
	 * @param request
	 * @param response
	 * @param model
	 * @param strStaffCode
	 * @return ModelAndView
	 * modified by sourav.bhadra
	 * changes taken on 29062017
	 */
	@RequestMapping(value = "/getFormDetailsForStaffDetailsInfo", method = RequestMethod.GET)
	public ModelAndView getFormDetailsForStaffDetailsInfo(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @RequestParam("strStaffCode") String strStaffCode,
			@RequestParam("dateOfInterview")String dateOfInterview) {
		logger.info("In getFormDetailsForStaffDetailsInfo() method of BackOfficeController: ");
		String strView = "";
		Employee employee = null;
		try {
			employee = erpService.getEmployeeFormBasicData();
			List<Country> countryList=commonService.getCountryList();
			List<State> stateList=commonService.getAllStatesInIndia("IND");
			Resource resource = erpService.getCandidateDetails(strStaffCode);
			Resource resourceObj = new Resource();
			if (resource != null) {
				if (employee != null) {
					resourceObj.setFirstName(resource.getFirstName());
					resourceObj.setMiddleName(resource.getMiddleName());
					resourceObj.setLastName(resource.getLastName());
					resourceObj.setDateOfBirth(resource.getDateOfBirth());
					resourceObj.setGender(resource.getGender());
					resourceObj.setStatus(resource.getStatus());
					resourceObj.setCode(resource.getCode());
					resourceObj.setObjectId(resource.getObjectId());
					resourceObj.setDateOfInterview(dateOfInterview);
					employee.setResource(resourceObj);
					TeachingLevel tl = new TeachingLevel();
					tl.setTeachingLevelName(resource.getObjectId());
					employee.setTeachingLevel(tl);					
					employee.setEmployeeCode(strStaffCode);
				}
			}				
			if (employee != null) {
				model.addAttribute("countryList", countryList);
				model.addAttribute("stateList", stateList);
				model.addAttribute("employee", employee);
				strView = "erp/addEmployeeDetails";
			} else {
				model.addAttribute("ResultError", "Result not available....");
				strView = "common/errorpage";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getFormDetailsForStaffDetailsInfo() method for NullPointerException: ", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getFormDetailsForStaffDetailsInfo() method for Exception: ", e);
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * this method get teacher marks details for particular candidate id for
	 * edit and return to editTeacherInterview.jsp
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param staff
	 * @return
	 */
	@RequestMapping(value = "/editTeacherInterview", method = RequestMethod.GET)
	public ModelAndView editTeacherInterview(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("employee") Employee employee) {
		logger.info("In editTeacherInterview() method of BackOfficeController: ");
		String strView = "";		
		try {
			Employee editTeacherInterviewDetails = erpService.getTeacherInterviewDetails(employee);
			if (editTeacherInterviewDetails != null) {
				model.addAttribute("staffTeacherInterview",editTeacherInterviewDetails);
				strView = "erp/editTeacherInterview";
			} else {
				model.addAttribute("ResultError", "Result not available....");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In editTeacherInterview() method of BackOfficeController: Exception", e);
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * this Ajax call get staff UserId and return to viewStaffSalarySlip.
	 * 
	 * @return Gson
	 */
	@RequestMapping(value = "/getStaffUserIdList", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getStaffUserIdList(@RequestParam("term") String strQuery) {
		logger.info("In getStaffUserIdList() Ajax method of BackOfficeController");

		List<String> staffUserIdList = erpService.getStaffUserIdList(strQuery);
		return (new Gson().toJson(staffUserIdList));
	}
	
	@ResponseBody
	@RequestMapping(value = "/getReportingPerson", method = RequestMethod.GET)
	public String getReportingPerson(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam("designation") String designation) {
		logger.info("In getReportingPerson() method of erpController: ");
		String output = null;		
		try {
			output = erpService.getReportingPerson(designation);			
		} catch (Exception e) {
			logger.error("Error in erpController getReportingPerson() method for Exception: ",e);
		}
		return (new Gson().toJson(output));
	}
	
	@RequestMapping(value = "/getSalTemplate", method = RequestMethod.GET)
	public @ResponseBody
	String getSalTemplate(@RequestParam("strlevel") String strlevel, 
						@RequestParam("strDesignationCode") String strDesignationCode) {
		String strTemplates = "";
		Designation designation = null;
		if(strlevel != null && strDesignationCode != null){
			DesignationLevel level = new DesignationLevel();
			designation = new Designation();
			designation.setDesignationCode(strDesignationCode);
			level.setDesignationLevelName(strlevel);
			designation.setLevel(level);
			List<SalaryTemplate> salaryTemplateList = erpService.getSalaryTemp(designation);
			if (salaryTemplateList != null && salaryTemplateList.size() != 0) {
				for (SalaryTemplate salTemp : salaryTemplateList) {
					strTemplates = strTemplates + salTemp.getSalaryTemplateName() + "~" + salTemp.getSalaryTemplateCode() + "@@";
				}
			}
		}		
		return (new Gson().toJson(strTemplates));
	}
	
	@RequestMapping(value = "/getSalBreakUp", method = RequestMethod.GET)
	public @ResponseBody
	String getSalBreakUp(@RequestParam("strLevel") String strlevel, 
						@RequestParam("strDesignationCode") String strDesignationCode,
						@RequestParam("strSalaryTemp") String strSalaryTemp) {
		String strBreakUp = "";
		SalaryTemplate salaryTemplate = null;
		try {
			if(strlevel != null && strDesignationCode != null && strSalaryTemp != null){
				salaryTemplate = new SalaryTemplate();
				salaryTemplate.setSalaryTemplateCode(strSalaryTemp);
				salaryTemplate.setDesignationLevel(strlevel);
				salaryTemplate.setDesignation(strDesignationCode);
				List<SalaryBreakUp> salaryBreakUpList = erpService.getSalaryBreakUpForTemplate(salaryTemplate);
				if (salaryBreakUpList != null && salaryBreakUpList.size() != 0) {
					for (SalaryBreakUp salBreak : salaryBreakUpList) {
						strBreakUp = strBreakUp + salBreak.getSalaryBreakUpType() + "~" + salBreak.getSalaryBreakUpName() +  "~" + salBreak.getSalaryBreakUpDesc() + "~" + salBreak.isSlab() + "@@";
					}
				}
				//System.out.println("@@@@   BreakUp  :: "+strBreakUp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return (new Gson().toJson(strBreakUp));
	}
	
	/**
	 * this method get salary details for a teacher from
	 * BackOfficeServiceImpl.java and return to viewStaffSalarySlip.jsp
	 * 
	 * @param model
	 * @param resource
	 * @return ModelAndView
	 * changes by ranita.sur 08062017
	 */
	
	@RequestMapping(value = "/getStaffSalaryDetails", method = RequestMethod.POST)
	public ModelAndView getStaffSalaryDetails(ModelMap model,
			@ModelAttribute("resource") Resource resource,
			@RequestParam(required = false, value = "year") String year,
			@RequestParam(required = false, value = "month") String month,
			HttpSession session
			){
		logger.info("In getStaffSalaryDetails() method of BackOfficeController: ");
		try {
			SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
			logger.info("In BackOfficeController editDesignationSalaryMapping() method: calling getDesignationSalaryMappingDetailsForEdit(Staff staff) method in BackOfficeServiceImpl.java");
			resource.setStartDate(year);//Carrying Academic Year
			resource.setEndDate(month);//Carrying Academic Month			
			Calendar calendar = Calendar.getInstance();
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
			resource.setStatus(Integer.toString(actualDayOfMonth));
			Employee staff = erpService.getStaffSalaryDetails(resource);
			if (staff != null) {
				intselectedyear = Integer.parseInt(year);
				intselectedmonth = Integer.parseInt(month);
				Calendar cal = new GregorianCalendar();
				cal.clear();
				cal.set(intselectedyear, intselectedmonth - 1, 1);
				// obtain the number of days of the month.
				int numberOfDaysInSpecificMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				staff.setDaysInMonths(Integer.toString(numberOfDaysInSpecificMonth));	
				
				model.addAttribute("staffForViewStaffSalaryDetails", staff);
				model.addAttribute("salaryMonth",resource.getEndDate());
				model.addAttribute("salaryYear",resource.getStartDate());
				model.addAttribute("daysInMonth",daysInMonth);
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
			List<Ledger> ledgerList=financeService.getLedgerList();
			if(ledgerList != null && ledgerList.size()!=0)
			{
				model.addAttribute("ledgerList", ledgerList);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStaffSalaryDetails() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStaffSalaryDetails() method for Exception: ",e);
		}
		return new ModelAndView("erp/viewStaffSalarySlip");
	}
	
	/**
	 * changes by ranita.sur 04072017
	 * */

	@RequestMapping(value = "/saveStaffSalaryDetails", method = RequestMethod.POST)
	public String saveStaffSalaryDetails(HttpServletRequest request,
			HttpServletResponse response, Model model,
			Employee staff,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam(value="slabTax", required = false) String[] strSlabTax,
			@RequestParam(value="ledgerCode", required = false) String ledger){
		try {
			logger.info("saveStaffSalaryDetails()In ErpController.java: calling saveStaffSalaryDetails() in ErpService.java");
			staff.setUpdatedBy(sessionObject.getUserId());
			staff.setComment(ledger);
			List<SalaryBreakUp> salaryBreakUpList = new ArrayList<SalaryBreakUp>();
			List<SalaryBreakUp> taxList = new ArrayList<SalaryBreakUp>();
			if (strSlabTax != null && strSlabTax.length != 0) {
				for (int j = 0; j < strSlabTax.length; j++) {
					SalaryBreakUp salaryBreakUp = new SalaryBreakUp();
					String[] splitedIndividual = strSlabTax[j].split("-");
					salaryBreakUp.setSalaryBreakUpCode(splitedIndividual[0]);
					salaryBreakUp.setSalaryBreakUpName(splitedIndividual[1]);
					if (splitedIndividual[2] != null && splitedIndividual[2].trim().length() != 0) {
						salaryBreakUp.setAmount(Double.parseDouble(splitedIndividual[2]));
					}else {
						salaryBreakUp.setAmount(0);
					}
					taxList.add(salaryBreakUp);
				}
			}
			salaryBreakUpList.addAll(taxList);
			staff.setExtraEarningList(salaryBreakUpList);
			String  saveStatus = erpService.saveStaffSalaryDetails(staff);
		} catch (Exception e) {
			logger.error("Exception In saveStaffSalaryDetails() method of ErpController: Exception",e);
			e.printStackTrace();
		}
		return viewStaffSalarySlip(model);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getDesignationLevelListForDesignation", method = RequestMethod.GET)
	public String getDesignationLevelListForDesignation(HttpServletRequest request,
													 HttpServletResponse response, ModelMap model,
													 @RequestParam("designation") String designationName) {
		logger.info("In getDesignationForResourceType() method of erpController");
		List<DesignationLevel> designationLevelList = null;
		String designationLevelArray = "";	
		try {				
			designationLevelList = erpService.getDesignationLevelListForDesignation(designationName);
			if(designationLevelList != null){
				for(DesignationLevel designationLevelObject : designationLevelList){
					designationLevelArray = designationLevelArray+designationLevelObject.getDesignationLevelCode()+"~"+designationLevelObject.getDesignationLevelName() + "@";
				}
			}
		} catch (Exception e) {				
			logger.error("Exception occured in getDesignationForResourceType() from erpController : ",e);
		}
		return (new Gson().toJson(designationLevelArray));			
	}	

	/**
	 * done by naimisha
	 * changes taken on 12Jan2017
	 * **/
	
	@RequestMapping(value = "/viewTeacherSubjectMapping", method = RequestMethod.GET)
	public ModelAndView viewTeacherSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("In viewTeacherSubjectMapping() method of ErpController: ");
		String strView = null;
		try {
			logger.info("In ErpController viewTeacherSubjectMapping() method: calling getTeacherSubjectForMapping() method in erpService.java");
			Staff staff = erpService.getTeacherSubjectForMapping();
			if (staff != null) {
				model.addAttribute("staffTeacherIdSubjects", staff);
				strView = "erp/teacherSubjectMapping";
			} else {
				model.addAttribute("ResultError", "Result not available....");
				strView = "common/errorpage";
			}
			List<SubjectGroup> subjectGroupList = staff.getSubjectListObj().getListSubjectGroup();
			model.addAttribute("subjectGroupList", subjectGroupList);
			List<Subject> subjectList=commonService.getSubject();
			model.addAttribute("subjectList", subjectList);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController viewTeacherSubjectMapping() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController viewTeacherSubjectMapping() method for Exception: ",
					e);
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/submitTeacherSubjectMapping", method = RequestMethod.POST)
	public ModelAndView submitTeacherSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="subjects") String []subjects,
			@RequestParam(value="oldSubjects", required = false) String []oldSubjects,
			@RequestParam(value="teacherId") String teacherId,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In submitTeacherSubjectMapping() method of BackOfficeController: ");
		String strView = null;
		try {
			logger.info("In BackOfficeController submitTeacherSubjectMapping() method: calling saveTeacherSubjectMapping() method in BackOfficeServiceImpl.java.");
			/*for (SubjectGroup sg : staff.getSubjectListObj().getListSubjectGroup()) {
				if (sg.getSubjectList() != null && sg.getSubjectList().size() != 0) {
					for (Subject s : sg.getSubjectList()) {
						s.setUpdatedBy(sessionObject.getUserId());
					}
				}
			}*/
			logger.info("Inside Method submitTeacherSubjectMapping-POST of AcademicsController");
			List<String> subjectList=new ArrayList<String>();
			List<String> oldSubjectList=new ArrayList<String>();
			
			List<String> insertSubjectList=new ArrayList<String>();
			List<String> deleteSubjectList=new ArrayList<String>();
			if(null!=subjects && subjects.length!=0){
				subjectList=Arrays.asList(subjects);
			}
			if(null!=oldSubjects && oldSubjects.length!=0){
				oldSubjectList=Arrays.asList(oldSubjects);
			}
			
			for(String subject:subjectList){
				if(! oldSubjectList.contains(subject))
					insertSubjectList.add(subject);
			}
			for(String subject:oldSubjectList){
				if(! subjectList.contains(subject))
					deleteSubjectList.add(subject);
			}
			TeacherSubjectMapping teacherSubjectMapping = new TeacherSubjectMapping();
			teacherSubjectMapping.setUpdatedBy(sessionObject.getUserId());
			teacherSubjectMapping.setStatus(teacherId);
			if(insertSubjectList.size()!=0)
			teacherSubjectMapping.setNewSubjectList(insertSubjectList);
			if(deleteSubjectList.size()!=0)
			teacherSubjectMapping.setOldSubjectList(deleteSubjectList);
			String updateStatus = erpService.submitTeacherSubjectMapping(teacherSubjectMapping);
			//staff = erpService.saveTeacherSubjectMapping(staff);
		/*if (staff != null) {
			model.addAttribute("staffTeacherIdSubjects", staff);
			strView = "erp/teacherSubjectMapping";
		} else {
			model.addAttribute("ResultError", "Result not available....");
			strView = "common/errorpage";
		}*/
			model.addAttribute("updateStatus", updateStatus);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController submitTeacherSubjectMapping() method for NullPointerException: ", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController submitTeacherSubjectMapping() method for Exception: ", e);
		}
		return teacherSubjectMappingList(request,response,model);
	}
	
	@RequestMapping(value = "/getStaffDetailsForTeacherSubjectMapping", method = RequestMethod.GET)
	public @ResponseBody
	String getStaffDetailsForTeacherSubjectMapping(@RequestParam("staffUserId") String strStaffUserId,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("In getTeacherDetails() Ajax method of BackOfficeController");
		String sm = "";
		try {
			logger.info("In ERPController getTeacherDetails() method: calling getTeacherDetails(String strTeacherId) in BackOfficeService.java");
			Employee employee = erpService.getTeacherDetails(strStaffUserId);
			if (employee != null) {
				if (employee.getEmployeeCode() == null) {
					employee.setEmployeeCode(" ");
				}
				if (employee.getResource().getFirstName() == null) {
					employee.getResource().setFirstName(" ");
				}
				if (employee.getResource().getMiddleName() == null) {
					employee.getResource().setMiddleName(" ");
				}
				if (employee.getResource().getLastName() == null) {
					employee.getResource().setLastName(" ");
				}
				if (employee.getEmployeeType().getEmployeeTypeName() == null) {
					employee.getEmployeeType().setEmployeeTypeName(" ");
				}
				if (employee.getDesignation() == null) {
					employee.getDesignation().setDesignationName(" ");
				}
				if (employee.getJobType() == null) {
					employee.getJobType().setJobTypeName(" ");
				}
				if (employee.getDateOfJoining() == null) {
					employee.setDateOfJoining(" ");
				}
				if (employee.getDateOfRetirement() == null) {
					employee.setDateOfRetirement(" ");
				}
				sm = employee.getEmployeeCode()+ "*" 
						+employee.getResource().getFirstName() + " "
						+ employee.getResource().getMiddleName() + " "
						+ employee.getResource().getLastName() + "*"
						+ employee.getEmployeeType().getEmployeeTypeName() + "*"
						+ employee.getDesignation().getDesignationName() + "*" 
						+ employee.getJobType().getJobTypeName() + "*"
						+ employee.getDateOfJoining() + "*"
						+ employee.getDateOfRetirement();
			}
			//System.out.println("value======"+sm);
			List<Subject> subjectList=commonService.getSubject();
			String sm1 = erpService.getTeacherSubjectMappping(strStaffUserId);
			sm = sm + "**"+sm1;
			
			//System.out.println("sm ** ====="+sm);
			
			//System.out.println("sm1============"+sm1);
			sm = sm + "#*#";
		//	System.out.println("sm #*# ========"+sm);
			for(Subject subject : subjectList){
				sm = sm + "###"+ subject.getSubjectCode();
			}
		} catch (NullPointerException e) {
			logger.error(
					"Error in ERPController getStaffDetails() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			logger.error(
					"Error in ERPController getStaffDetails() method for Exception: ",
					e);
		}
		//System.out.println("value ======"+sm);
		 return (new Gson().toJson(sm));	
	}
	
	/**
	 * this method get list of teacher-subject mapping from erpServiceImpl.java
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/teacherSubjectMappingList", method = RequestMethod.GET)
	public ModelAndView teacherSubjectMappingList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In teacherSubjectMappingList() method of BackOfficeController: ");
		ModelAndView mav = new ModelAndView("erp/listTeacherSubjectMapping");
		try {
			logger.info("In BackOfficeController teacherSubjectMappingList() method: calling getTeacherSubjectMappingList() method in erpServiceImpl.java");
			List<Resource> teacherList = erpService.getTeachersFromTeacherSubjectMappingList();
			//System.out.println("teacherList size==="+teacherList.size());
			model.addAttribute("teacherList", teacherList);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController teacherSubjectMappingList()() method for NullPointerException: ", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController teacherSubjectMappingList()() method for Exception: ", e);
		}
		return new ModelAndView("erp/listTeacherSubjectMapping");
	}
	
	/**
	 * this method get teacher id from listTeacherSubjectMapping.jsp and call
	 * BackOfficeServiceImpl.java for get list of mapped subjects with a teacher
	 * for edit teacher-subject mapping and return to
	 * editTeacherSubjectMapping.jsp
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param staff
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/getTeacherSubjectMappingForList", method = RequestMethod.GET)
	public ModelAndView editTeacherSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("staff") Staff staff, 
			@RequestParam(required = false, value = "userId") String userId
			) {
		logger.info("In editTeacherSubjectMapping() method of BackOfficeController: ");
		if(userId != null){
			Resource resource = new Resource();
			resource.setUserId(userId);
			staff.setResource(resource);
		}
		
		try {
			logger.info("In BackOfficeController editTeacherSubjectMapping() method: calling getTeacherSubjectMappingList() method in BackOfficeServiceImpl.java");
			staff = erpService.getTeacherSubjectMappingForEdit(staff);
			if (staff != null) {
				model.addAttribute("staffTeacherIdSubjectsForEdit", staff);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController editTeacherSubjectMapping() method for NullPointerException: ",	e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController editTeacherSubjectMapping() method for Exception: ", e);
		}
		return new ModelAndView("erp/editTeacherSubjectMapping");
	}
	
	@RequestMapping(value = "/editTeacherSubjectMapping", method = RequestMethod.POST)
	public ModelAndView editTeacherSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="subjects") String []subjects,
			@RequestParam(value="oldSubjects", required = false) String []oldSubjects,
			@RequestParam(value="teacherId") String teacherId,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In submitTeacherSubjectMapping() method of BackOfficeController: ");
		String strView = null;
		try {
			logger.info("In BackOfficeController submitTeacherSubjectMapping() method: calling saveTeacherSubjectMapping() method in BackOfficeServiceImpl.java.");
			/*for (SubjectGroup sg : staff.getSubjectListObj().getListSubjectGroup()) {
				if (sg.getSubjectList() != null && sg.getSubjectList().size() != 0) {
					for (Subject s : sg.getSubjectList()) {
						s.setUpdatedBy(sessionObject.getUserId());
					}
				}
			}*/
			logger.info("Inside Method submitTeacherSubjectMapping-POST of AcademicsController");
			List<String> subjectList=new ArrayList<String>();
			List<String> oldSubjectList=new ArrayList<String>();
			
			List<String> insertSubjectList=new ArrayList<String>();
			List<String> deleteSubjectList=new ArrayList<String>();
			if(null!=subjects && subjects.length!=0){
				subjectList=Arrays.asList(subjects);
			}
			if(null!=oldSubjects && oldSubjects.length!=0){
				oldSubjectList=Arrays.asList(oldSubjects);
			}
			
			for(String subject:subjectList){
				if(! oldSubjectList.contains(subject))
					insertSubjectList.add(subject);
			}
			for(String subject:oldSubjectList){
				if(! subjectList.contains(subject))
					deleteSubjectList.add(subject);
			}
			TeacherSubjectMapping teacherSubjectMapping = new TeacherSubjectMapping();
			teacherSubjectMapping.setUpdatedBy(sessionObject.getUserId());
			teacherSubjectMapping.setStatus(teacherId);
			if(insertSubjectList.size()!=0)
			teacherSubjectMapping.setNewSubjectList(insertSubjectList);
			if(deleteSubjectList.size()!=0)
			teacherSubjectMapping.setOldSubjectList(deleteSubjectList);
			String updateStatus = erpService.submitTeacherSubjectMapping(teacherSubjectMapping);
			//staff = erpService.saveTeacherSubjectMapping(staff);
		/*if (staff != null) {
			model.addAttribute("staffTeacherIdSubjects", staff);
			strView = "erp/teacherSubjectMapping";
		} else {
			model.addAttribute("ResultError", "Result not available....");
			strView = "common/errorpage";
		}*/
			model.addAttribute("updateStatus", updateStatus);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController submitTeacherSubjectMapping() method for NullPointerException: ", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController submitTeacherSubjectMapping() method for Exception: ", e);
		}
		return teacherSubjectMappingList(request,response,model);
	}
	
	/**
	 * this method get teacher and subjects list for teacher-subject mapping and
	 * return to teacherSubjectMapping.jsp
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/teacherClassMapping", method = RequestMethod.GET)
	public ModelAndView viewTeacherClassMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("In viewTeacherClassMapping() method of ErpController: ");
		String strView = null;
		try {
			logger.info("In ErpController viewTeacherClassMapping() method: ");
			//Staff staff = erpService.getTeacherSubjectForMapping();
			List<Resource> teacherList = erpService.getTeachersFromTeacherSubjectMappingList();
			List<Standard>standardList = academicsService.getStandardsWithSection();
			List<TeacherSubjectMapping> teacherSubjectMappingList = erpService.getTeachersFromStandardTeacherSubjectMappingList();
			List<Course>courseList = academicsService.getCourseList();
			//if (staff != null) {
				model.addAttribute("teacherList", teacherList);
				model.addAttribute("standardList", standardList);
				model.addAttribute("courseList", courseList);
				model.addAttribute("teacherSubjectMappingList", teacherSubjectMappingList);
				strView = "erp/teacherClassMapping";
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController viewTeacherClassMapping() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController viewTeacherClassMapping() method for Exception: ",
					e);
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/getSubjectAgainstTeacher", method = RequestMethod.GET)
	public @ResponseBody
	String getSubjectAgainstTeacher(@RequestParam("teacher") String strStaffUserId,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("In getTeacherDetails() Ajax method of BackOfficeController");
		String subject = "";
		try {
			logger.info("In ERPController getSubjectAgainstTeacher() method: calling getSubjectAgainstTeacher(String strTeacherId) in BackOfficeService.java");
			 subject = erpService.getTeacherSubjectMappping( strStaffUserId);
			
			
		} catch (NullPointerException e) {
			logger.error(
					"Error in ERPController getStaffDetails() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			logger.error(
					"Error in ERPController getStaffDetails() method for Exception: ",
					e);
		}
		 return (new Gson().toJson(subject));	
	}
	
	@RequestMapping(value = "/teacherClassSubjectMapping", method = RequestMethod.POST)
		public ModelAndView teacherClassSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="subject") String subject,
			@RequestParam(value="course") String standard,
			@RequestParam(value="teacherId") String teacherId,
			@RequestParam(value="section") String section,
		//	@RequestParam(value="noOfClass") int noOfClass,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In teacherClassSubjectMapping() method of ErpController: ");
		
		try {
			TeacherSubjectMapping teacherSubjectMapping = new TeacherSubjectMapping();
			teacherSubjectMapping.setUpdatedBy(sessionObject.getUserId());
			//teacherSubjectMapping.setNoOfClass(noOfClass);
			teacherSubjectMapping.setSectionName(section);
			teacherSubjectMapping.setStandardName(standard);
			teacherSubjectMapping.setSubject(subject);
			teacherSubjectMapping.setTeacherName(teacherId);
			String updateStatus = erpService.submitStandardTeacherSubjectMapping(teacherSubjectMapping);
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Class Alocated to Teacher  Successfully";
			}else{
				msg = "Failed to Allocate Class";
			}
			model.addAttribute("insertUpdateStatus", updateStatus);
			model.addAttribute("msg",msg);
			//model.addAttribute("updateStatus",updateStatus );
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController teacherClassSubjectMapping() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController teacherClassSubjectMapping() method for Exception: ",
					e);
		}
		return viewTeacherClassMapping( request, response,  model);
	}
	
	@RequestMapping(value = "/editTeacherClassMapping", method = RequestMethod.POST)
	public ModelAndView editTeacherClassMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In editTeacherClassMapping() method of ErpController: ");
		
		try {
			TeacherSubjectMapping teacherSubjectMapping = new TeacherSubjectMapping();
			teacherSubjectMapping.setUpdatedBy(sessionObject.getUserId());

			String saveId = request.getParameter("saveId");
			String statusValue = request.getParameter("statusValue");
			if(statusValue.equalsIgnoreCase("edit")){
				teacherSubjectMapping.setNoOfClass(Integer.parseInt(request.getParameter("noOfClass"+saveId).trim()));
				String no = request.getParameter("noOfClass"+saveId).trim();
				teacherSubjectMapping.setSectionName(request.getParameter("section"+saveId));
				teacherSubjectMapping.setStandardName(request.getParameter("standard"+saveId));
				teacherSubjectMapping.setSubject(request.getParameter("subject"+saveId));
				teacherSubjectMapping.setTeacherName(request.getParameter("teacherId"+saveId));
				String updateStatus = erpService.editStandardTeacherSubjectMapping(teacherSubjectMapping);
				model.addAttribute("updateStatus",updateStatus );
			}
			if(statusValue.equalsIgnoreCase("delete")){
				teacherSubjectMapping.setNoOfClass(Integer.parseInt(request.getParameter("noOfClass"+saveId).trim()));
				String no = request.getParameter("noOfClass"+saveId).trim();
				teacherSubjectMapping.setSectionName(request.getParameter("section"+saveId));
				teacherSubjectMapping.setStandardName(request.getParameter("standard"+saveId));
				teacherSubjectMapping.setSubject(request.getParameter("subject"+saveId));
				teacherSubjectMapping.setTeacherName(request.getParameter("teacherId"+saveId));
				String updateStatus = erpService.deleteStandardTeacherSubjectMapping(teacherSubjectMapping);
				model.addAttribute("updateStatus",updateStatus );
			}
			
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController editTeacherClassMapping() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController editTeacherClassMapping() method for Exception: ",
					e);
		}
		return viewTeacherClassMapping( request, response,  model);
	}
	
	@RequestMapping(value = "/deleteTeacherClassSubjectMapping", method = RequestMethod.GET)
	public ModelAndView deleteTeacherClassSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="rowId") String saveId,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In deleteTeacherClassSubjectMapping() method of ErpController: ");
		
		try {
			TeacherSubjectMapping teacherSubjectMapping = new TeacherSubjectMapping();
			teacherSubjectMapping.setUpdatedBy(sessionObject.getUserId());

			//String saveId = request.getParameter("saveId");
			//System.out.println(saveId);
			teacherSubjectMapping.setNoOfClass(Integer.parseInt(request.getParameter("noOfClass"+saveId).trim()));
			String no = request.getParameter("noOfClass"+saveId).trim();
			teacherSubjectMapping.setSectionName(request.getParameter("section"+saveId));
			teacherSubjectMapping.setStandardName(request.getParameter("standard"+saveId));
			teacherSubjectMapping.setSubject(request.getParameter("subject"+saveId));
			teacherSubjectMapping.setTeacherName(request.getParameter("teacherId"+saveId));
			String updateStatus = erpService.deleteStandardTeacherSubjectMapping(teacherSubjectMapping);
			model.addAttribute("updateStatus",updateStatus );
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController deleteTeacherClassSubjectMapping() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController deleteTeacherClassSubjectMapping() method for Exception: ",
					e);
		}
		return viewTeacherClassMapping( request, response,  model);
	}
	
	
	/**
	 * Modified by anup.roy
	 * 29112017**/
	
	@RequestMapping(value = "/viewStaffProfile", method = RequestMethod.GET)
	public ModelAndView viewStaffProfile(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Map<String, Object> parameters) {
		logger.info("In viewStaffProfile() method of BackOfficeController: ");
		try {
			String strResourceType = request.getParameter("resourceType");
			logger.info("In BackOfficeController viewStaffProfile() method: calling viewStaffProfile in erpService.java");
			parameters.put("ResourceType", strResourceType);
			List<Employee> employeeList = erpService.getStaffList(parameters);	
			model.addAttribute("ResourceType", strResourceType);
			model.addAttribute("employeeList", employeeList);
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController listStaff() method for NullPointerException: ", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Error in BackOfficeController listStaff() method for Exception: ", e);
			e.printStackTrace();
		}
		return new ModelAndView("erp/viewStaffProfileList");
	}
	
	@RequestMapping(value = "/inactiveTeacherClassSubjectMapping", method = RequestMethod.GET)
	public ModelAndView inactiveTeacherClassSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="serialId") String serialId,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In deleteTeacherClassSubjectMapping() method of ErpController: ");
		
		try {
			TeacherSubjectMapping teacherSubjectMapping = new TeacherSubjectMapping();
			teacherSubjectMapping.setUpdatedBy(sessionObject.getUserId());

			//String saveId = request.getParameter("saveId");
			/*System.out.println(saveId);
			teacherSubjectMapping.setNoOfClass(Integer.parseInt(request.getParameter("noOfClass"+saveId).trim()));
			String no = request.getParameter("noOfClass"+saveId).trim();
			teacherSubjectMapping.setSectionName(request.getParameter("section"+saveId));
			teacherSubjectMapping.setStandardName(request.getParameter("standard"+saveId));
			teacherSubjectMapping.setSubject(request.getParameter("subject"+saveId));
			teacherSubjectMapping.setTeacherName(request.getParameter("teacherId"+saveId));*/
			teacherSubjectMapping.setSerialId(Integer.parseInt(serialId));
			String updateStatus = erpService.deleteStandardTeacherSubjectMapping(teacherSubjectMapping);
			model.addAttribute("insertUpdateStatus",updateStatus );
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Deleted SuccessFully";
			}else{
				msg = "Failed To Delete";
			}
			model.addAttribute("msg", msg);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController deleteTeacherClassSubjectMapping() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Error in ErpController deleteTeacherClassSubjectMapping() method for Exception: ",
					e);
		}
		return viewTeacherClassMapping( request, response,  model);
	}
	
	/**
	 * @author ranita.sur
	 * changes taken on 21062017*/
	
	@RequestMapping(value="/mapDepartmentToHead", method = RequestMethod.GET)
	public String mapDepartmentToHead(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<Department> departmentList = null;
		List<Department> departmentHodList=null;
		try{
			logger.info("Excecuting mapDepartmentToHead from erpController, calling getAllDepartment() in erpService.java");
			departmentList = erpService.getAllDepartment();	
			model.addAttribute("departmentList", departmentList);
			Resource resource = new Resource();
			resource = administratorService.getResourceAndAccessFromDB();
			List<ResourceType>	resourceTypeList=resource.getResourceTypeList();
			List<Department> deapartmentDetailsList=erpService.getMapDepartmentWithResourceType();
			
			model.addAttribute("deapartmentDetailsList", deapartmentDetailsList);
			if (resourceTypeList != null) {
				model.addAttribute("resourceTypeList", resourceTypeList);
			}
			}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in viewDepartment() from erpController : ",e);
		}
		return "erp/mapDepartmentToHead";
	}
	
	@RequestMapping(value = "/submitDepartmentHeadDetails", method = RequestMethod.POST)
	public String submitDepartmentHeadDetails(HttpServletRequest request,
			HttpServletResponse response,ModelMap model,
			@RequestParam("resourceUserId") String departmentHead,
			@RequestParam("departmentCode") String departmentCode,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		try {
				String updatestatus="";
				logger.info("In submitVendor method of CommonController.java");
			
			
				Department department = new Department();
				department.setUpdatedBy(sessionObject.getUserId());
				department.setDepartmentCode(departmentCode);
				department.setDepartmentHead(departmentHead);
				updatestatus=erpService.updateHodForDepartment(department);
			
				model.addAttribute("updatestatus", updatestatus);
			
		} catch (Exception e) {	
			e.printStackTrace();
			logger.error("submitAddVendor() In CommonController.java: Exception", e);
		}
		return mapDepartmentToHead(request,response,model);
	}
	
	
	@RequestMapping(value = "/getResourceUserId", method = RequestMethod.GET)
	public @ResponseBody
	String getResourceUserId(@RequestParam("parent") String parent) {
		String subGroup="";
		
		try {
			subGroup = erpService.getAllUserIdList(parent);			
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(subGroup));
	}
	
	@RequestMapping(value = "/saveUpdateDetails", method = RequestMethod.GET)
	public String saveUpdateDetails(HttpServletRequest request,
			HttpServletResponse response,ModelMap model, @RequestParam("resourceId") String resourceId,
													@RequestParam("departmentCode") String departmentCode,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		try {
			String status="";
			
			logger.info("In saveUpdateDetails method of ERPController.java");
			Department department = new Department();
			department.setUpdatedBy(sessionObject.getUserId());
			department.setDepartmentCode(departmentCode);
			department.setDepartmentHead(resourceId);
			 status=erpService.saveUpdateDetails(department);
			model.addAttribute("status", status);
			/**Added by @author Saif.Ali
			 * Date- 13/03/2018
			 * Used to insert the information into the activity_log table*/
			if(status.equalsIgnoreCase("Success")){
				String oldDepartmentHOD= request.getParameter("departmentResource");
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT MAP DEPARTMENT TO HEAD");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor(departmentCode);
				updateLog.setDescription("HOD ::  " + oldDepartmentHOD + " of department :: " + departmentCode + " is now updated with new HOD :: " + resourceId);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 4812 :: ERPController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/**Addition ends*/
			
		} catch (Exception e) {	
			e.printStackTrace();
			logger.error("saveUpdateDetails() In ERPController.java: Exception", e);
		}
		return mapDepartmentToHead(request,response,model);
	}
	
	//Added  By Naimisha  31/07/2017
	@RequestMapping(value = "/getResourceDetailsForSalary", method = RequestMethod.GET)
	public @ResponseBody
	String getResourceDetailsForSalary(@RequestParam("resourceTypeName") String resourceTypeName
			/*@RequestParam("year") String year,
			@RequestParam("month") String month*/) {
		String resourceDetailsString="";
		
		try {
			Resource resource = new Resource();
			resource.setResourceTypeName(resourceTypeName);
			//resource.setStartDate(year);
			//resource.setEndDate(month);
			int flag = 1;
			List<Resource>resourceDetailsList = erpService.getResourceDetailsForSalary(resource);	
			for(Resource resourceObj : resourceDetailsList){
				resourceDetailsString = resourceDetailsString + resourceObj.getUserId() + "*" +
										resourceObj.getName() + "*" + resourceObj.getSalaryTemplateCode();
				List<SalaryTemplate> salaryBreakUpList = resourceObj.getSalaryTemplateList();
				
				resourceDetailsString = resourceDetailsString +"#";
						
				for(SalaryTemplate st : salaryBreakUpList){
					if(st.getSalaryTemplateDesc().equalsIgnoreCase("MANUAL")){
						resourceDetailsString = resourceDetailsString + st.getSalaryTemplateCode()+"*"+st.getSalaryTemplateName()+"@";
						//flag = 0;
					}
				}
				//if(flag == 1){
					resourceDetailsString = resourceDetailsString+";";
				//}
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(resourceDetailsString));
	}
	
	
	
	@RequestMapping(value = "/saveStaffSalaryDetailsNew", method = RequestMethod.POST)
	public String saveStaffSalaryDetailsNew(HttpServletRequest request,
			HttpServletResponse response, Model model,
			Employee staff,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam(value="userId", required = false) String[] userId,
			@RequestParam(value="templateCode", required = false) String[] templateCode,
			@RequestParam(required = false, value = "year") String year,
			@RequestParam(required = false, value = "month") String month){
		try {
			logger.info("saveStaffSalaryDetailsNew()In ErpController.java: calling saveStaffSalaryDetailsNew() in ErpService.java");
		
			List<Resource>resourceList = new ArrayList<Resource>();
			for(int i=0;i<userId.length;i++){
				Resource resource = new Resource();
				resource.setUpdatedBy(sessionObject.getUserId());
				resource.setUserId(userId[i]);
				System.out.println("template=="+request.getParameter("templateCode_"+userId[i]));
				resource.setSalaryTemplateCode(request.getParameter("templateCode_"+userId[i]));
				resource.setStartDate(year);
				resource.setEndDate(month);
				
				System.out.println(request.getParameter("breakUpCode_"+userId[i]));
				List<SalaryBreakUp>mannualBreakList = new ArrayList<>();
				
				String[] mannualBreakups = request.getParameterValues("breakUpCode_"+userId[i]);
				
				if(null != mannualBreakups){
					for(int j = 0;j<mannualBreakups.length;j++){
						SalaryBreakUp salaryBreakup = new SalaryBreakUp();
						salaryBreakup.setSalaryBreakUpCode(mannualBreakups[i]);
						salaryBreakup.setSalaryBreakUpName(request.getParameter("breakUpName_"+mannualBreakups[i]));
						String amount = request.getParameter("value_"+request.getParameter("breakUpCode_"+userId[i]));
						salaryBreakup.setAmount(Double.parseDouble(amount));
						salaryBreakup.setSalaryBreakUpType("MANUAL");;
						mannualBreakList.add(salaryBreakup);
					}
				}
				
				resource.setSalaryBreakUpList(mannualBreakList);
				resourceList.add(resource);
			}
			
			/*staff.setComment(ledger);
			List<SalaryBreakUp> salaryBreakUpList = new ArrayList<SalaryBreakUp>();
			List<SalaryBreakUp> taxList = new ArrayList<SalaryBreakUp>();
			if (strSlabTax != null && strSlabTax.length != 0) {
				for (int j = 0; j < strSlabTax.length; j++) {
					SalaryBreakUp salaryBreakUp = new SalaryBreakUp();
					String[] splitedIndividual = strSlabTax[j].split("-");
					salaryBreakUp.setSalaryBreakUpCode(splitedIndividual[0]);
					salaryBreakUp.setSalaryBreakUpName(splitedIndividual[1]);
					if (splitedIndividual[2] != null && splitedIndividual[2].trim().length() != 0) {
						salaryBreakUp.setAmount(Double.parseDouble(splitedIndividual[2]));
					}else {
						salaryBreakUp.setAmount(0);
					}
					taxList.add(salaryBreakUp);
				}
			}
			salaryBreakUpList.addAll(taxList);
			staff.setExtraEarningList(salaryBreakUpList);*/
			String  saveStatus = erpService.saveStaffSalaryDetailsNew(resourceList);
			model.addAttribute("status", saveStatus);
		} catch (Exception e) {
			logger.error("Exception In saveStaffSalaryDetails() method of ErpController: Exception",e);
			e.printStackTrace();
		}
		return viewStaffSalarySlip(model);
	}
	
	
	
	//Added By Naimisha New 09082017
	@RequestMapping(value = "/getPaymentStatusForEmployeeForAYearAndMonth", method = RequestMethod.GET)
	public @ResponseBody
	String getPaymentStatusForEmployeeForAYearAndMonth(@RequestParam("resourceTypeName") String resourceTypeName,
			@RequestParam("year") String year,
			@RequestParam("month") String month) {
		String resourceDetailsString="";
		
		try {
			Resource resource = new Resource();
			resource.setResourceTypeName(resourceTypeName);
			resource.setStartDate(year);
			resource.setEndDate(month);
			List<Resource>resourceDetailsList = erpService.getPaymentStatusForEmployeeForAYearAndMonth(resource);	
			for(Resource resourceObj : resourceDetailsList){
				resourceDetailsString = resourceDetailsString + resourceObj.getUserId() +  "*";
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(resourceDetailsString));
	}
	//Added By Naimisha 11082017
	@ResponseBody
	@RequestMapping(value = "/getTemplateForDesignationTypeAndDesignationAndLevel", method = RequestMethod.GET)
	public String getTemplateForDesignationTypeAndDesignationAndLevel(HttpServletRequest request,
										HttpServletResponse response, 
										ModelMap model,
			@RequestParam("designationType") String designationType,
			@RequestParam("designation") String designation,
			@RequestParam("level") String level) {
		logger.info("In getTemplateForDesignationTypeAndDesignationAndLevel() method of ERPController: ");
		String output = "";
		try {
			Resource resource = new Resource();
			resource.setDesignationType(designationType);
			resource.setDesignation(designation);
			resource.setDesignationLevel(level);
			SalaryTemplate salaryTemplate = erpService.getTemplateForDesignationTypeAndDesignationAndLevel(resource);
			output = output + salaryTemplate.getSalaryTemplateCode();


	
		} catch (Exception e) {
			logger.error("Error in ERPController getTemplateForDesignationTypeAndDesignationAndLevel() method for Exception: ",e);
		}
		return (new Gson().toJson(output));
	}
}
