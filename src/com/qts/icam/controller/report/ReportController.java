package com.qts.icam.controller.report;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.google.gson.Gson;
import com.qts.icam.controller.login.LoginController;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.UserDefinedExams;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.backoffice.MajorEvents;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.ChartData;
import com.qts.icam.model.common.ChartValuesModel;
import com.qts.icam.model.common.Condemnation;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Image;
import com.qts.icam.model.common.MeritListType;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.report.Report;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.admission.AdmissionService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.inventory.InventoryService;
import com.qts.icam.service.login.LoginService;
import com.qts.icam.service.report.ReportService;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

@Controller
@SessionAttributes("sessionObject")
public class ReportController {
	
	public static Logger logger = Logger.getLogger(ReportController.class);
	
	
	
	@Autowired
	CommonService commonService;
	@Autowired
	AdministratorService administratorService;
	@Autowired
	ReportService reportService;
	@Autowired
	InventoryService inventoryService;
	@Autowired
	AdmissionService admissionService;
	@Autowired
	AcademicsService academicsService;
	//Added By Naimisha 04102017
	@Autowired
	LoginService loginService = null;
	
	@Value("${report.query}")
	private String reportQueryPath;
	
	//End
	@Value("${reportResultConfigFile.path}")
	private String reportResultConfigFilePath;
	
	@Value("${reportResultPdfFile.path}")
	private String reportResultPdfFilPath;
	
	@Value("${image.path}")
	private String rootImagePath;  
	
	@Value("${reportExamChartImageFile.path}")
	private String reportExamChartImageFilePath;  
	
	@Value("${nominalRoll.excel}")
	private String nominalRollExcel;
	
	
	@Value("${staffExcel.excel}")
	private String staffExcel;
	
	@Value("${studentMarkSheetExcel.excel}")
	private String studentMarkSheetExcel;
	
	@Value("${root.path}")
	private String rootPath;
	
	@Value("${excelReportDownload.folder}")
	private String excelReportDownloadFolder;
	
	String moduleNameForReport = null;
	
	@RequestMapping(value = "/studentMarksheet", method = RequestMethod.GET)
	public ModelAndView studentMarksheet(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
			List<AcademicYear> academicYearList=commonService.getPreviousAndCurrentAcademicYear();
			List<Standard> standardList= commonService.getStandards();			
			if(null!=academicYearList && academicYearList.size()!=0){
				model.addAttribute("academicYearList", academicYearList);		
			}
			if(null!=standardList && standardList.size()!=0){
				model.addAttribute("standardList", standardList);		
			}			
		}catch(Exception e){
			logger.error("In studentMarksheet() In ReportController.java: Exception",e);	
		}		
		return new ModelAndView("report/studentMarksheet");
	}

	@RequestMapping(value = "/getStudentReport", method = RequestMethod.POST)
	public ModelAndView getStudentReport(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									Standard standard,Section section,
									AcademicYear academicYear,Exam exam,
									@RequestParam(value="roll") String []roll) {		
		try{
			if(null!=standard && null!=section && null!=academicYear && null!=exam && null!=roll){
				String status = reportService.getStudentReport(standard,section,academicYear,exam,roll,reportResultConfigFilePath,reportResultPdfFilPath,rootImagePath+reportExamChartImageFilePath,response);
				if(status!=null ){
					 	      
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}
			}
			
		}catch(Exception e){
			logger.error(e);		  	
		}		
		return studentMarksheet(request,response,model);
	}
	
	
	
	@RequestMapping(value = "/classConsolidatedMarksheet", method = RequestMethod.GET)
	public ModelAndView classConsolidatedMarksheet(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
			List<AcademicYear> academicYearList=commonService.getPreviousAndCurrentAcademicYear();
			List<Standard> standardList= commonService.getStandards();			
			if(null!=academicYearList && academicYearList.size()!=0){
				model.addAttribute("academicYearList", academicYearList);		
			}
			if(null!=standardList && standardList.size()!=0){
				model.addAttribute("standardList", standardList);		
			}			
		}catch(Exception e){
			logger.error("In classConsolidatedMarksheet() In ReportController.java: Exception",e);	
		}		
		return new ModelAndView("report/classConsolidatedMarksheet");
	}
	
	
	@RequestMapping(value = "/getConsolidatedResult", method = RequestMethod.POST)
	public ModelAndView getConsolidatedResult(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Standard standard,Section section,
			AcademicYear academicYear,Exam exam,
			@RequestParam(required = false, value = "excel") String excel,
			@RequestParam(required = false, value = "submitGrade") String submitGrade
			) {		
		try{
			String status = null;
			if(null != standard && null != section && null != academicYear && null != exam ){
				if(null != submitGrade){
					standard.setStatus("grade");
				}else{
					standard.setStatus("gradepoint");
				}
				 status = reportService.getConsolidatedResultReport(standard,section,academicYear,exam,reportResultConfigFilePath,reportResultPdfFilPath, excel, studentMarkSheetExcel, response);
				 if(null != status && null != excel){	
					FileUploadDownload fileUploadDownload = new FileUploadDownload();
					String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelReportDownloadFolder,studentMarkSheetExcel);
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}
			}
			
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}		
		return classConsolidatedMarksheet(request,response,model);
	}
	
	
	
	@RequestMapping(value = "/studentNominalRoll", method = RequestMethod.GET)
	public ModelAndView studentNominalRoll(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
			List<Standard> standardList= commonService.getStandards();
			if(null!=standardList && standardList.size()!=0){
				model.addAttribute("standardList", standardList);		
			}
		}catch(Exception e){				
			logger.error("In studentNominalRoll() In ReportController.java: Exception",e);	
		}		
		return new ModelAndView("report/studentNominalRoll");
	}
	
	
	@RequestMapping(value = "/getStudentNominalRoll", method = RequestMethod.POST)
	public ModelAndView getStudentNominalRoll(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,			
			@RequestParam(value="column") String []column,@ModelAttribute("sessionObject") SessionObject sessionObject,
			Standard standard,Section section,
			@RequestParam(required = false, value = "excel") String excel) {		
		try{
			String status = null;
			if(null != column && column.length != 0 && null != standard  && null != section){
				Student student = new Student();
				Resource resource = new Resource();
				if(null != excel){				 
					 for(int count = 0; count<column.length; count++){					
						 if(column[count].equalsIgnoreCase("roll")){
							 student.setRollNumber(1);
						 }
						if(column[count].equalsIgnoreCase("name")){
							student.setStudentName("name");				 
						}
						if(column[count].equalsIgnoreCase("house")){
							student.setHouse("house");
						}
						if(column[count].equalsIgnoreCase("category")){
							resource.setCategory("category");
						}
						if(column[count].equalsIgnoreCase("dob")){
							resource.setDateOfBirth("dob");
						}
						if(column[count].equalsIgnoreCase("stateOfDomicile")){
							student.setStateOfDomicile("stateOfDomicile");
						}
						if(column[count].equalsIgnoreCase("fName")){
							resource.setFatherFirstName("fName");
						}
						if(column[count].equalsIgnoreCase("mName")){
							resource.setMotherFirstName("mName");
						}
						if(column[count].equalsIgnoreCase("secondLanguage")){
							student.setSecondLanguage("secondLanguage");
						}
						if(column[count].equalsIgnoreCase("doa")){
							student.setDateOfAdmission("doa");
						}
						if(column[count].equalsIgnoreCase("contact")){
							resource.setMobile("contact");
						}					
					 }					
				}else{
					student.setRollNumber(1);
					student.setStudentName("name");
					student.setHouse("house");
				}
				resource.setUpdatedBy(sessionObject.getUserId());
				student.setResource(resource);
				student.setStandard(standard.getStandardCode());
				student.setSection(section.getSectionCode());
				
				status = reportService.getStudentNominalRoll(student,excel,reportResultConfigFilePath,reportResultPdfFilPath, nominalRollExcel, response);
				if(status != null && excel != null){	
					FileUploadDownload fileUploadDownload = new FileUploadDownload();					
					String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelReportDownloadFolder,nominalRollExcel);
					//System.out.println("Hello:"+returnMessage);
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}
			}else{
				 model.addAttribute("message", "Please Provide Valid Parameters");					
			}			
		}catch(Exception e){
			logger.error(e);			  	
		}		
		return studentNominalRoll(request,response,model);
	}	
	
	
	@RequestMapping(value = "/staffDetailsList", method = RequestMethod.GET)
	public ModelAndView staffDetailsList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
			Resource resource=administratorService.getResourceAndRolesFromDB();
			if(null!=resource && null!=resource.getResourceTypeList() && resource.getResourceTypeList().size()!=0){
				model.addAttribute("resourceTypeList", resource.getResourceTypeList());		
			}		
		}catch(Exception e){
			logger.error("In staffDetailsList() In ReportController.java: Exception",e);	
		}		
		return new ModelAndView("report/staffDetailsList");
	}
	
	
	@RequestMapping(value = "/getStaffDetailsList", method = RequestMethod.POST)
	public ModelAndView getStaffDetailsList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			Employee employee,
			@RequestParam(required = false, value = "excel") String excel) {		
		try{
			String status =null;
			if( null!=employee ){
				employee.setUpdatedBy(sessionObject.getUserId());
				if(null==employee.getDesignation()){
					Designation designation = new  Designation();
					employee.setDesignation(designation);
				}
				 status = reportService.getStaffDetailsList(employee, excel, reportResultConfigFilePath, reportResultPdfFilPath, staffExcel, response);
				 if(status!=null && excel != null){	
						FileUploadDownload fileUploadDownload = new FileUploadDownload();
						String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelReportDownloadFolder,staffExcel);
					}else{
						 model.addAttribute("message", "Result Not Available");					
					}
			}else{
				 model.addAttribute("message", "Result Not Available");					
			}
			
		}catch(Exception e){
			logger.error(e);			  	
		}		
		return staffDetailsList(request,response,model);
	}

	
	
	
	@RequestMapping(value = "/carTripDetails", method = RequestMethod.GET)
	public ModelAndView carTripDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
					
		}catch(Exception e){
			logger.error("In carTripDetails() In ReportController.java: Exception",e);	
		}		
		return new ModelAndView("report/carTripDetails");
	}
	

	
	
	@RequestMapping(value = "/gatePassReport", method = RequestMethod.GET)
	public ModelAndView gatePassReport(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
					
		}catch(Exception e){
			logger.error("In carTripDetails() In ReportController.java: Exception",e);	
		}		
		return new ModelAndView("report/gatePassReport");
	}
	
	

	

	
	
	
	@RequestMapping(value = "/getStudentAddress", method = RequestMethod.GET)
	public ModelAndView getStudentAddress(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
			List<AcademicYear> academicYearList=commonService.getPreviousAndCurrentAcademicYear();
			List<Standard> standardList= commonService.getStandards();			
			if(null!=academicYearList && academicYearList.size()!=0){
				model.addAttribute("academicYearList", academicYearList);		
			}
			if(null!=standardList && standardList.size()!=0){
				model.addAttribute("standardList", standardList);		
			}			
		}catch(Exception e){
			logger.error("In studentMarksheet() In ReportController.java: Exception",e);	
		}		
		return new ModelAndView("report/studentAddress");
	}
	
	
	@RequestMapping(value = "/getStudentAddressDetails", method = RequestMethod.POST)
	public ModelAndView getStudentAddressDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,	
			@RequestParam(value="roll") String []roll
			) {		
		try{
			String status =null;
			if(roll!=null && roll.length!=0){
				List<Student> studentList=new ArrayList<Student>();
				for(int count=0;count<roll.length;count++){
					Student student = new Student();
					student.setRollNumber(Integer.parseInt(roll[count]));
					studentList.add(student);
				}				
				if(studentList.size()!=0){
					status = reportService.getStudentAddressDetails(studentList,reportResultConfigFilePath,reportResultPdfFilPath, response);
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}				
				if(status!=null ){
					 	      
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}
			}else{
				 model.addAttribute("message", "Result Not Available");					
			}
			
		}catch(Exception e){
			logger.error(e);			  	
		}		
		return getStudentAddress(request,response,model);
	}
	
	
	
	/**
	 * this method is used to generateMeritListForAdmision jsp with loaded basic data
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/meritListForAdmisionReport", method = RequestMethod.GET)
	public String generateMeritListForAdmision(HttpServletRequest request,ModelMap model) {
		try{
			List<MeritListType> meritListTypes = commonService.getMeritListTypes();
			model.addAttribute("meritListTypes", meritListTypes);
			List<Standard> standardList = commonService.getStandards();
			model.addAttribute("standardList", standardList);
		}catch(Exception e){
			logger.error(e);
		}
		return "report/generateMeritListForAdmision";
	}
	
	/**
	 * this method is used to generate merit list report for admission 
	 * 
	 * @param request
	 * @param model
	 * @param sessionObject
	 * @param report
	 * @return
	 */
	@RequestMapping(value = "/generateMeritListForAdmision", method = RequestMethod.POST)
	public String generateMeritListForAdmision(HttpServletRequest request,ModelMap model,
												HttpServletResponse response,
														 @ModelAttribute("sessionObject") SessionObject sessionObject,
														 Report report) {		
		try{
			String status = reportService.generateMeritListForAdmisionReport(report,reportResultConfigFilePath,reportResultPdfFilPath, response);
			if(status==null ){
				 model.addAttribute("message", "Result Not Available");					
			}
		}catch(Exception e){
			logger.error(e);			  	
		}		
		return generateMeritListForAdmision(request,model);
	}
	
	/**
	 * this method is used to open examVenueWiseCandidate jsp with loaded basic data
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/examVenueWiseCandidate", method = RequestMethod.GET)
	public String examVenueWiseCandidate(HttpServletRequest request,ModelMap model) {
		try{
			Map<String,Object> parameters=null;
			List<Venue> examVenueList = commonService.getExamVenues(parameters);
			model.addAttribute("examVenueList", examVenueList);
		}catch(Exception e){
			logger.error(e);
		}
		return "report/examVenueWiseCandidate";
	}
	
	/**
	 * this method is used to generate report for exam venue wise candidate
	 * 
	 * @param request
	 * @param model
	 * @param sessionObject
	 * @param report
	 * @return String
	 */
	@RequestMapping(value = "/examVenueWiseCandidateReport", method = RequestMethod.POST)
	public String examVenueWiseCandidateReport(HttpServletRequest request,ModelMap model, HttpServletResponse response,
														 @ModelAttribute("sessionObject") SessionObject sessionObject,
														 Report report) {		
		try{
				Venue venue = commonService.getExamVenueDetails(report.getVenue());
				report.setVenue(venue);
				String status = reportService.generateExamVenueWiseCandidatesReport(report,reportResultConfigFilePath,reportResultPdfFilPath, response);
				if(status==null ){
					 model.addAttribute("message", "Result Not Available");					
				}
		}catch(Exception e){
			logger.error(e);			  	
		}		
		return examVenueWiseCandidate(request,model);
	}
	
	
	/**
	 * this method is used to open socialCategoryWiseClassStrength jsp with loaded data
	 * 
	 * @param request
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/socialCategoryWiseClassStrength", method = RequestMethod.GET)
	public String socialCategoryWiseClassStrength(HttpServletRequest request,ModelMap model) {
		try{
			List<Standard> standardList = commonService.getStandards();
			model.addAttribute("standardList", standardList);
		}catch(Exception e){
			logger.error(e);
		}
		return "report/socialCategoryWiseClassStrength";
	}
	
	/**
	 * this method is used to generate report for category wise class strength
	 * 
	 * @param request
	 * @param model
	 * @param sessionObject
	 * @param report
	 * @return String
	 */
	@RequestMapping(value = "/generateSocialCategoryWiseClassStrengthReport", method = RequestMethod.POST)
	public String generateSocialCategoryWiseClassStrengthReport(HttpServletRequest request,ModelMap model, HttpServletResponse response,
														 @ModelAttribute("sessionObject") SessionObject sessionObject,
														 @RequestParam(value="standard") String []standardArray,Report report) {		
		try{
			if(null!=standardArray && standardArray.length!=0){
				List<Standard> standardList=new ArrayList<Standard>();
				for(int count=0;count<standardArray.length;count++){
					Standard standard=new Standard();
					standard.setStandardName(standardArray[count]);
					standardList.add(standard);
				}
				if(standardList.size()!=0)
				report.setStandardList(standardList);
				
				String status = reportService.generateSocialCategoryWiseClassStrengthReport(report,reportResultConfigFilePath,reportResultPdfFilPath, response);
				if(status==null ){
					 model.addAttribute("message", "Result Not Available");					
				}
			}else{
				 model.addAttribute("message", "Result Not Available");	
			}
			
		}catch(Exception e){
			logger.error(e);			  	
		}		
		return socialCategoryWiseClassStrength(request,model);
	}
	
	@RequestMapping(value = "/createTCReport", method = RequestMethod.GET)
	public String createTCReport(HttpServletRequest request,ModelMap model) {
		try{			
			
		}catch(Exception e){
			logger.error(e);
		}
		return "report/createTCReport";
	}
	
	
	@RequestMapping(value = "/showTCReport", method = RequestMethod.POST)
	public String showTCReport(HttpServletRequest request,ModelMap model, HttpServletResponse response,
														 @ModelAttribute("sessionObject") SessionObject sessionObject,
														 @RequestParam(value="rollNumber") String rollNumber) {		
		try{
			if(null!=rollNumber){				
				String status = reportService.showTCReport(rollNumber,reportResultConfigFilePath,reportResultPdfFilPath, response);
				if(status==null ){
					 model.addAttribute("message", "Result Not Available");					
				}
			}else{
				 model.addAttribute("message", "Result Not Available");	
			}
			
		}catch(Exception e){
			logger.error(e);			  	
		}		
		return createTCReport(request,model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/getConsolidatedResultForXII_XII", method = RequestMethod.POST)
	public ModelAndView getConsolidatedResultForXII_XII(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Standard standard,Section section,
			AcademicYear academicYear,Exam exam
			) {		
		try{
			String status = null;
			if(null != standard && null != section && null != academicYear && null != exam ){
				status = reportService.getConsolidatedResultForXII_XII(standard,section,academicYear,exam,reportResultConfigFilePath,reportResultPdfFilPath, response);
				if(status != null){
					 
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}
			}
			
		}catch(Exception e){
			logger.error(e);			  	
		}		
		return userExamConsolidatedMarksheet(request, response, model);
	}
	
	@RequestMapping(value = "/getStudentReportFORXI_XII", method = RequestMethod.POST)
	public ModelAndView getStudentReportFORXI_XII(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									Standard standard,Section section,
									AcademicYear academicYear,Exam exam,
									@RequestParam(value="roll") String []roll) {		
		try{
			if(null != standard && null != section && null != academicYear && null != exam && null != roll){
				String status = reportService.getStudentReportFORXI_XII(standard,section,academicYear,exam,roll,reportResultConfigFilePath,reportResultPdfFilPath,rootImagePath+reportExamChartImageFilePath,response);
				if(status != null ){
					 	      
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}
			}
			
		}catch(Exception e){
			logger.error(e);		  	
		}		
		return userExamMarkStatement(request, response, model);
	}
	
	
	@RequestMapping(value = "/userExamMarkStatement", method = RequestMethod.GET)
	public ModelAndView userExamMarkStatement(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try{
			List<AcademicYear> academicYearList = commonService.getPreviousAndCurrentAcademicYear();
			List<Standard> standardList = commonService.getStandards();			
			if(null != academicYearList && academicYearList.size() != 0){
				model.addAttribute("academicYearList", academicYearList);		
			}
			if(null != standardList && standardList.size() != 0){
				model.addAttribute("standardList", standardList);		
			}
		}catch(Exception e){
			logger.error("In userExamMarkStatement() In ReportController.java: Exception",e);	
		}		
		return new ModelAndView("report/userExamMarkStatement");
	}
	
	
	@RequestMapping(value = "/userExamConsolidatedMarksheet", method = RequestMethod.GET)
	public ModelAndView userExamConsolidatedMarksheet(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try{
			List<AcademicYear> academicYearList = commonService.getPreviousAndCurrentAcademicYear();
			List<Standard> standardList = commonService.getStandards();			
			if(null != academicYearList && academicYearList.size() != 0){
				model.addAttribute("academicYearList", academicYearList);		
			}
			if(null != standardList && standardList.size() != 0){
				model.addAttribute("standardList", standardList);		
			}			
		}catch(Exception e){
			logger.error("In userExamConsolidatedMarksheet() In ReportController.java: Exception",e);	
		}		
		return new ModelAndView("report/userExamConsolidatedMarksheet");
	}
	
	
	@RequestMapping(value = "/getUserExamsForStandard", method = RequestMethod.GET)
	public @ResponseBody
	String getUserExamsForStandard(@RequestParam("standard") String standard) {
		logger.info("Inside Method getUserExamsForStandard() of ReportController");
		String result = "";
		try {
			List<UserDefinedExams> examList = reportService.getUserExamsForStandard(standard);
			
			if(null != examList && examList.size() != 0){
				for(UserDefinedExams udExam : examList){
					result = result+udExam.getExamCode()+"*";
				}
			}
		} catch (Exception ce) {
			logger.error("Exception in method getUserExamsForStandard() of ReportController", ce);
		}		
		return (new Gson().toJson(result));
	}
	
	
	@RequestMapping(value = "/studentSubjectMapping", method = RequestMethod.GET)
	public void studentSubjectMapping(HttpServletRequest request, HttpServletResponse response, ModelMap model,
									@RequestParam("standard") String standard,@RequestParam("section") String section) {		
		try{
			if(null != standard && null != section)
			{
				Student studentObject = new Student();
				studentObject.setStandard(standard);
				studentObject.setSection(section);
				String status = reportService.getStudentSubjectMapping(studentObject,reportResultConfigFilePath,reportResultPdfFilPath,response);
			}			
		}catch(Exception e){
			logger.error("In studentSubjectMapping() of ReportController.java: Exception",e);	
		}		
	}
	
	/**
	 * naimisha.. report for NISM Demo**/
	
	
	@RequestMapping(value = "/getCertificate", method = RequestMethod.GET)
	public ModelAndView getCertificate(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try{
			List<AcademicYear> academicYearList = commonService.getPreviousAndCurrentAcademicYear();
			List<Standard> standardList = commonService.getStandards();			
			if(null != academicYearList && academicYearList.size() != 0){
				model.addAttribute("academicYearList", academicYearList);		
			}
			if(null != standardList && standardList.size() != 0){
				model.addAttribute("standardList", standardList);		
			}
		}catch(Exception e){
			logger.error("In getCertificate() In ReportController.java: Exception",e);	
		}		
		return new ModelAndView("report/certificate");
	}
	
	@RequestMapping(value = "/getcertificate", method = RequestMethod.POST)
	public ModelAndView getcertificate(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									Standard standard,Section section,
									AcademicYear academicYear,Exam exam,
									@RequestParam(value="roll") String []roll) {		
		try{
			if(null != standard && null != section && null != academicYear && null != exam && null != roll){
				String status = reportService.getStudentCertificate(standard,section,academicYear,exam,roll,reportResultConfigFilePath,reportResultPdfFilPath,rootImagePath+reportExamChartImageFilePath,response);
				if(status != null ){
					 	      
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);		  	
		}		
		return getCertificate(request, response, model);
	}
	
	/**New CBSE System start**/
	/**
	 * @author anup.roy
	 * this method is for view students
	 * for report*/
	
	@RequestMapping(value = "/newStudentMarksheet", method = RequestMethod.GET)
	public ModelAndView newStudentMarksheet(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
			List<AcademicYear> academicYearList = commonService.getPreviousAndCurrentAcademicYear();
			List<Standard> standardList = academicsService.getStandardsForSetExamMarks();			
			if(null!=academicYearList && academicYearList.size()!=0){
				model.addAttribute("academicYearList", academicYearList);
			}
			if(null!=standardList && standardList.size()!=0){
				model.addAttribute("standardList", standardList);
			}
		}catch(Exception e){
			logger.error("In studentMarksheet() In ReportController.java: Exception",e);
			e.printStackTrace();
		}
		return new ModelAndView("report/studentMarksheetForNewPattern");
	}
	
	@RequestMapping(value = "/getStudentsAgainstStandardAndSectionForNewReport", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentsAgainstStandardAndSectionForNewReport(@RequestParam("standard") String standard,
												@RequestParam("section") String section) {
		String student = null;
		try {
			logger.info("getStudentsAgainstStandardAndSectionForNewReport() In CommonController.java");
			student = reportService.getStudentsAgainstStandardAndSectionForNewReport(standard, section);
		} catch (NullPointerException e) {
			logger.error("getStudentsAgainstStandardAndSectionForNewReport() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getStudentsAgainstStandardAndSectionForNewReport() In CommonController.java: Exception"
					+ e);
		}
		return student;
	}
	
	/**
	 * modified by anup.roy
	 * this method is for get the report*/
	
	@RequestMapping(value = "/getStudentReportnew", method = RequestMethod.POST)
	public ModelAndView getStudentReportnew(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									Standard standard,Section section,
									AcademicYear academicYear,Exam exam,
									@RequestParam(value="roll") String []roll) {		
		try{
			if(null!=standard && null!=section && null!=academicYear && null!=exam && null!=roll){
				RepositoryStructure repository = new RepositoryStructure();
				repository = administratorService.getRespositoryStructure();
				String repositoryName = repository.getRepositoryPathName();
				reportResultConfigFilePath = repositoryName+"/"+reportResultConfigFilePath;
				reportResultPdfFilPath = repositoryName+"/"+reportResultPdfFilPath;
				rootImagePath = repositoryName+"/"+rootImagePath;
				File reportResultConfigFilePathFile = new File(reportResultConfigFilePath);
				reportResultConfigFilePathFile.mkdirs();
				File rootImagePathFile = new File(rootImagePath);
				rootImagePathFile.mkdirs();
				File reportResultPdfFilPathFile = new File(reportResultPdfFilPath);
				reportResultPdfFilPathFile.mkdirs();
				File reportExamChartImageFilePathFile = new File(rootImagePath+reportExamChartImageFilePath);
				reportExamChartImageFilePathFile.mkdirs();
				String status = reportService.getStudentReportNew(standard,section,academicYear,exam,roll,reportResultConfigFilePath,reportResultPdfFilPath,rootImagePath+reportExamChartImageFilePath,response);
				if(status!=null ){
					
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}
			}
			
		}catch(Exception e){
			logger.error(e);		  	
		}		
		return newStudentMarksheet(request,response,model);
	}
	
	@RequestMapping(value = "/newClassConsolidatedMarksheet", method = RequestMethod.GET)
	public ModelAndView newClassConsolidatedMarksheet(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
			List<AcademicYear> academicYearList=commonService.getPreviousAndCurrentAcademicYear();
			List<Standard> standardList= commonService.getStandards();			
			if(null!=academicYearList && academicYearList.size()!=0){
				model.addAttribute("academicYearList", academicYearList);		
			}
			if(null!=standardList && standardList.size()!=0){
				model.addAttribute("standardList", standardList);		
			}			
		}catch(Exception e){
			logger.error("In classConsolidatedMarksheet() In ReportController.java: Exception",e);	
		}
		return new ModelAndView("report/newClassConsolidatedMarksheet");
	}
	
	
	@RequestMapping(value = "/newGetConsolidatedResult", method = RequestMethod.POST)
	public ModelAndView newGetConsolidatedResult(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Standard standard,Section section,
			AcademicYear academicYear,Exam exam,
			@RequestParam(required = false, value = "excel") String excel,
			@RequestParam(required = false, value = "submitGrade") String submitGrade
			) {		
		try{
			String status = null;
			if(null != standard && null != section && null != academicYear && null != exam ){
				if(null != submitGrade){
					standard.setStatus("grade");
				}else{
					standard.setStatus("gradepoint");
				}
				 status = reportService.getConsolidatedResultReportNew(standard,section,academicYear,exam,reportResultConfigFilePath,reportResultPdfFilPath, excel, studentMarkSheetExcel, response);
				 if(null != status && null != excel){
					FileUploadDownload fileUploadDownload = new FileUploadDownload();
					String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelReportDownloadFolder,studentMarkSheetExcel);
				}else{
					 model.addAttribute("message", "Result Not Available");					
				}
			}
			
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
		}		
		return newClassConsolidatedMarksheet(request,response,model);
	}
	
	/**New CBSE System end**/
	
	/*For chart start*/
	
	@RequestMapping(value = "/moduleWiseReport", method = RequestMethod.GET)
	public ModelAndView libraryReport(ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam("moduleName") String module) {
		
			logger.info("Opening Library Report");
			
			moduleNameForReport = module;
            model.addAttribute("module", module);
            ModelAndView mnv = new ModelAndView("report/moduleWiseReport");
           
            
            return mnv;
            
           
	}

	@RequestMapping(value="/loadChart1DataForLibrary", method = RequestMethod.GET)
    protected @ResponseBody String loadChart1DataForLibrary(HttpServletRequest request,ModelMap model,
			HttpServletResponse response,@ModelAttribute("sessionObject") SessionObject sessionObject){
 
		System.out.println("loadChart1DataForLibrary");
		StringBuffer sb = new StringBuffer();
		
		//return "column2d||Revenue for the past year||<chart caption=\"Harry\'s SuperMart\" subcaption=\"Monthly revenue for last year\" xaxisname=\"Month\" yaxisname=\"Amount\" numberprefix=\"$\" palettecolors=\"#008ee4\" bgalpha=\"0\" borderalpha=\"20\" canvasborderalpha=\"0\" useplotgradientcolor=\"0\" plotborderalpha=\"10\" placevaluesinside=\"1\" rotatevalues=\"1\" valuefontcolor=\"#ffffff\" captionpadding=\"20\" showaxislines=\"1\" axislinealpha=\"25\" divlinealpha=\"1\"><set label=\"Jan\" value=\"420000\" /><set label=\"Feb\" value=\"810000\" /><set label=\"Mar\" value=\"720000\" /><set label=\"Apr\" value=\"550000\" /><set label=\"May\" value=\"910000\" /><set label=\"Jun\" value=\"510000\" /><set label=\"Jul\" value=\"680000\" /><set label=\"Aug\" value=\"620000\" /><set label=\"Sep\" value=\"610000\" /><set label=\"Oct\" value=\"490000\" /><set label=\"Nov\" value=\"900000\" /><set label=\"Dec\" value=\"730000\" /></chart>";
		
		//return "pie3d||Visitors by age group||<chart caption=\"Split of Visitors by Age Group\" subcaption=\"Last year\" palettecolors=\"#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000\" bgcolor=\"#ffffff\" showborder=\"0\" use3dlighting=\"0\" showshadow=\"0\" enablesmartlabels=\"0\" startingangle=\"0\" showpercentvalues=\"1\" showpercentintooltip=\"0\" decimals=\"1\" captionfontsize=\"14\" subcaptionfontsize=\"14\" subcaptionfontbold=\"0\" tooltipcolor=\"#ffffff\" tooltipborderthickness=\"0\" tooltipbgcolor=\"#000000\" tooltipbgalpha=\"80\" tooltipborderradius=\"2\" tooltippadding=\"5\" showhovereffect=\"1\" showlegend=\"1\" legendbgcolor=\"#ffffff\" legendborderalpha=\"0\" legendshadow=\"0\" legenditemfontsize=\"10\" legenditemfontcolor=\"#666666\" usedataplotcolorforlabels=\"1\"><set label=\"Teenage\" value=\"1250400\" /><set label=\"Adult\" value=\"1463300\" /><set label=\"Mid-age\" value=\"1050700\" /><set label=\"Senior\" value=\"491000\" /></chart>";
		
		String userId = sessionObject.getUserId();
		Resource resource = loginService.getAccessTypeAndRoleList(userId);
		String roleName = LoginController.ROLE_NAME_TO_DISPLAY_ITS_CHARTS;
		String moduleName = moduleNameForReport ;//"LIBRARY";//LoginController.MODULE_NAME_TO_DISPLAY_ITS_CHARTS;
		//System.out.println("Module====="+module);
		//String moduleName = "LIBRARY";
		
		
		/**
		 * new code added for reading queries.xml file from external repository*/
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		String queryPath = repository+"/"+reportQueryPath;
		/***/
		//ChartData chartData =  reportService.loadChart1Data(roleName, moduleName, reportQueryPath);
		ChartData chartData =  reportService.loadChart1DataForLibrary(roleName, moduleName, queryPath);
		
		sb.append(chartData.getChartType() + "||");
		sb.append(chartData.getChartLabel() + "||");
		sb.append("<chart caption = '" + chartData.getCaption() + "' ");
		sb.append("subcaption = '" + chartData.getSubCaption() + "' ");
		if((chartData.getChartType() == "pie2d") || (chartData.getChartType() == "pie3d")){
			
		}else{
			sb.append("xaxisname = '" + chartData.getXaxisname()  + "' ");
			sb.append("yaxisname = '" + chartData.getYaxisname()  + "' ");
			sb.append("numberprefix = '" + chartData.getNumberprefix()  + "' ");
		}
		sb.append("theme = '" + chartData.getTheme() + "'> ");
		
		List<ChartValuesModel> chartValuesModelList = chartData.getChartValuesModelList();
		for(ChartValuesModel chartValuesModel : chartValuesModelList){
			sb.append("<set label = '" + chartValuesModel.getLabel()  + "' value = '" + chartValuesModel.getValue() + "'/>");
		}
		
		sb.append("</chart>");
		
		System.out.println(sb.toString());
        
		return sb.toString();
    }

	
	@RequestMapping(value="/loadChart2DataForLibrary", method = RequestMethod.GET)
    protected @ResponseBody String loadChart2DataForLibrary(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("sessionObject") SessionObject sessionObject){
 
		System.out.println("loadChart2DataForLibrary");
		System.out.println("Module====="+moduleNameForReport);
		StringBuffer sb = new StringBuffer();
		
		//return "column2d||Revenue for the past year||<chart caption=\"Harry\'s SuperMart\" subcaption=\"Monthly revenue for last year\" xaxisname=\"Month\" yaxisname=\"Amount\" numberprefix=\"$\" palettecolors=\"#008ee4\" bgalpha=\"0\" borderalpha=\"20\" canvasborderalpha=\"0\" useplotgradientcolor=\"0\" plotborderalpha=\"10\" placevaluesinside=\"1\" rotatevalues=\"1\" valuefontcolor=\"#ffffff\" captionpadding=\"20\" showaxislines=\"1\" axislinealpha=\"25\" divlinealpha=\"1\"><set label=\"Jan\" value=\"420000\" /><set label=\"Feb\" value=\"810000\" /><set label=\"Mar\" value=\"720000\" /><set label=\"Apr\" value=\"550000\" /><set label=\"May\" value=\"910000\" /><set label=\"Jun\" value=\"510000\" /><set label=\"Jul\" value=\"680000\" /><set label=\"Aug\" value=\"620000\" /><set label=\"Sep\" value=\"610000\" /><set label=\"Oct\" value=\"490000\" /><set label=\"Nov\" value=\"900000\" /><set label=\"Dec\" value=\"730000\" /></chart>";
		
		//return "pie3d||Visitors by age group||<chart caption=\"Split of Visitors by Age Group\" subcaption=\"Last year\" palettecolors=\"#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000\" bgcolor=\"#ffffff\" showborder=\"0\" use3dlighting=\"0\" showshadow=\"0\" enablesmartlabels=\"0\" startingangle=\"0\" showpercentvalues=\"1\" showpercentintooltip=\"0\" decimals=\"1\" captionfontsize=\"14\" subcaptionfontsize=\"14\" subcaptionfontbold=\"0\" tooltipcolor=\"#ffffff\" tooltipborderthickness=\"0\" tooltipbgcolor=\"#000000\" tooltipbgalpha=\"80\" tooltipborderradius=\"2\" tooltippadding=\"5\" showhovereffect=\"1\" showlegend=\"1\" legendbgcolor=\"#ffffff\" legendborderalpha=\"0\" legendshadow=\"0\" legenditemfontsize=\"10\" legenditemfontcolor=\"#666666\" usedataplotcolorforlabels=\"1\"><set label=\"Teenage\" value=\"1250400\" /><set label=\"Adult\" value=\"1463300\" /><set label=\"Mid-age\" value=\"1050700\" /><set label=\"Senior\" value=\"491000\" /></chart>";
		
		String userId = sessionObject.getUserId();
		Resource resource = loginService.getAccessTypeAndRoleList(userId);
		String roleName = LoginController.ROLE_NAME_TO_DISPLAY_ITS_CHARTS;
		String moduleName = moduleNameForReport;//module;//LoginController.MODULE_NAME_TO_DISPLAY_ITS_CHARTS;
		
		//String moduleName = "LIBRARY";
		
		
		/**
		 * new code added for reading queries.xml file from external repository*/
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		String queryPath = repository+"/"+reportQueryPath;
		/***/
		//ChartData chartData =  reportService.loadChart1Data(roleName, moduleName, reportQueryPath);
		ChartData chartData =  reportService.loadChart2DataForLibrary(roleName, moduleName, queryPath);
		
		sb.append(chartData.getChartType() + "||");
		sb.append(chartData.getChartLabel() + "||");
		sb.append("<chart caption = '" + chartData.getCaption() + "' ");
		sb.append("subcaption = '" + chartData.getSubCaption() + "' ");
		if((chartData.getChartType() == "pie2d") || (chartData.getChartType() == "pie3d")){
			
		}else{
			sb.append("xaxisname = '" + chartData.getXaxisname()  + "' ");
			sb.append("yaxisname = '" + chartData.getYaxisname()  + "' ");
			sb.append("numberprefix = '" + chartData.getNumberprefix()  + "' ");
		}
		sb.append("theme = '" + chartData.getTheme() + "'> ");
		
		List<ChartValuesModel> chartValuesModelList = chartData.getChartValuesModelList();
		for(ChartValuesModel chartValuesModel : chartValuesModelList){
			sb.append("<set label = '" + chartValuesModel.getLabel()  + "' value = '" + chartValuesModel.getValue() + "'/>");
		}
		
		sb.append("</chart>");
		
		System.out.println(sb.toString());
        
		return sb.toString();
    }
	
	
	@RequestMapping(value="/loadChart3DataForLibrary", method = RequestMethod.GET)
    protected @ResponseBody String loadChart3DataForLibrary(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("sessionObject") SessionObject sessionObject){
 
		System.out.println("loadChart3DataForLibrary");
		System.out.println("Module====="+moduleNameForReport);
		StringBuffer sb = new StringBuffer();
		
		//return "column2d||Revenue for the past year||<chart caption=\"Harry\'s SuperMart\" subcaption=\"Monthly revenue for last year\" xaxisname=\"Month\" yaxisname=\"Amount\" numberprefix=\"$\" palettecolors=\"#008ee4\" bgalpha=\"0\" borderalpha=\"20\" canvasborderalpha=\"0\" useplotgradientcolor=\"0\" plotborderalpha=\"10\" placevaluesinside=\"1\" rotatevalues=\"1\" valuefontcolor=\"#ffffff\" captionpadding=\"20\" showaxislines=\"1\" axislinealpha=\"25\" divlinealpha=\"1\"><set label=\"Jan\" value=\"420000\" /><set label=\"Feb\" value=\"810000\" /><set label=\"Mar\" value=\"720000\" /><set label=\"Apr\" value=\"550000\" /><set label=\"May\" value=\"910000\" /><set label=\"Jun\" value=\"510000\" /><set label=\"Jul\" value=\"680000\" /><set label=\"Aug\" value=\"620000\" /><set label=\"Sep\" value=\"610000\" /><set label=\"Oct\" value=\"490000\" /><set label=\"Nov\" value=\"900000\" /><set label=\"Dec\" value=\"730000\" /></chart>";
		
		//return "pie3d||Visitors by age group||<chart caption=\"Split of Visitors by Age Group\" subcaption=\"Last year\" palettecolors=\"#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000\" bgcolor=\"#ffffff\" showborder=\"0\" use3dlighting=\"0\" showshadow=\"0\" enablesmartlabels=\"0\" startingangle=\"0\" showpercentvalues=\"1\" showpercentintooltip=\"0\" decimals=\"1\" captionfontsize=\"14\" subcaptionfontsize=\"14\" subcaptionfontbold=\"0\" tooltipcolor=\"#ffffff\" tooltipborderthickness=\"0\" tooltipbgcolor=\"#000000\" tooltipbgalpha=\"80\" tooltipborderradius=\"2\" tooltippadding=\"5\" showhovereffect=\"1\" showlegend=\"1\" legendbgcolor=\"#ffffff\" legendborderalpha=\"0\" legendshadow=\"0\" legenditemfontsize=\"10\" legenditemfontcolor=\"#666666\" usedataplotcolorforlabels=\"1\"><set label=\"Teenage\" value=\"1250400\" /><set label=\"Adult\" value=\"1463300\" /><set label=\"Mid-age\" value=\"1050700\" /><set label=\"Senior\" value=\"491000\" /></chart>";
		
		String userId = sessionObject.getUserId();
		Resource resource = loginService.getAccessTypeAndRoleList(userId);
		String roleName = LoginController.ROLE_NAME_TO_DISPLAY_ITS_CHARTS;
		String moduleName = moduleNameForReport;//LoginController.MODULE_NAME_TO_DISPLAY_ITS_CHARTS;
		
	//	String moduleName = "LIBRARY";
		
		
		/**
		 * new code added for reading queries.xml file from external repository*/
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		String queryPath = repository+"/"+reportQueryPath;
		/***/
		//ChartData chartData =  reportService.loadChart1Data(roleName, moduleName, reportQueryPath);
		ChartData chartData =  reportService.loadChart3DataForLibrary(roleName, moduleName, queryPath);
		
		sb.append(chartData.getChartType() + "||");
		sb.append(chartData.getChartLabel() + "||");
		sb.append("<chart caption = '" + chartData.getCaption() + "' ");
		sb.append("subcaption = '" + chartData.getSubCaption() + "' ");
		if((chartData.getChartType() == "pie2d") || (chartData.getChartType() == "pie3d")){
			
		}else{
			sb.append("xaxisname = '" + chartData.getXaxisname()  + "' ");
			sb.append("yaxisname = '" + chartData.getYaxisname()  + "' ");
			sb.append("numberprefix = '" + chartData.getNumberprefix()  + "' ");
		}
		sb.append("theme = '" + chartData.getTheme() + "'> ");
		
		List<ChartValuesModel> chartValuesModelList = chartData.getChartValuesModelList();
		for(ChartValuesModel chartValuesModel : chartValuesModelList){
			sb.append("<set label = '" + chartValuesModel.getLabel()  + "' value = '" + chartValuesModel.getValue() + "'/>");
		}
		
		sb.append("</chart>");
		
		System.out.println(sb.toString());
        
		return sb.toString();
    }
	
	@RequestMapping(value="/loadChart4DataForLibrary", method = RequestMethod.GET)
    protected @ResponseBody String loadChart4DataForLibrary(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("sessionObject") SessionObject sessionObject){
 
		System.out.println("loadChart14DataForLibrary");
		System.out.println("Module====="+moduleNameForReport);
		StringBuffer sb = new StringBuffer();
		
		//return "column2d||Revenue for the past year||<chart caption=\"Harry\'s SuperMart\" subcaption=\"Monthly revenue for last year\" xaxisname=\"Month\" yaxisname=\"Amount\" numberprefix=\"$\" palettecolors=\"#008ee4\" bgalpha=\"0\" borderalpha=\"20\" canvasborderalpha=\"0\" useplotgradientcolor=\"0\" plotborderalpha=\"10\" placevaluesinside=\"1\" rotatevalues=\"1\" valuefontcolor=\"#ffffff\" captionpadding=\"20\" showaxislines=\"1\" axislinealpha=\"25\" divlinealpha=\"1\"><set label=\"Jan\" value=\"420000\" /><set label=\"Feb\" value=\"810000\" /><set label=\"Mar\" value=\"720000\" /><set label=\"Apr\" value=\"550000\" /><set label=\"May\" value=\"910000\" /><set label=\"Jun\" value=\"510000\" /><set label=\"Jul\" value=\"680000\" /><set label=\"Aug\" value=\"620000\" /><set label=\"Sep\" value=\"610000\" /><set label=\"Oct\" value=\"490000\" /><set label=\"Nov\" value=\"900000\" /><set label=\"Dec\" value=\"730000\" /></chart>";
		
		//return "pie3d||Visitors by age group||<chart caption=\"Split of Visitors by Age Group\" subcaption=\"Last year\" palettecolors=\"#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000\" bgcolor=\"#ffffff\" showborder=\"0\" use3dlighting=\"0\" showshadow=\"0\" enablesmartlabels=\"0\" startingangle=\"0\" showpercentvalues=\"1\" showpercentintooltip=\"0\" decimals=\"1\" captionfontsize=\"14\" subcaptionfontsize=\"14\" subcaptionfontbold=\"0\" tooltipcolor=\"#ffffff\" tooltipborderthickness=\"0\" tooltipbgcolor=\"#000000\" tooltipbgalpha=\"80\" tooltipborderradius=\"2\" tooltippadding=\"5\" showhovereffect=\"1\" showlegend=\"1\" legendbgcolor=\"#ffffff\" legendborderalpha=\"0\" legendshadow=\"0\" legenditemfontsize=\"10\" legenditemfontcolor=\"#666666\" usedataplotcolorforlabels=\"1\"><set label=\"Teenage\" value=\"1250400\" /><set label=\"Adult\" value=\"1463300\" /><set label=\"Mid-age\" value=\"1050700\" /><set label=\"Senior\" value=\"491000\" /></chart>";
		
		String userId = sessionObject.getUserId();
		Resource resource = loginService.getAccessTypeAndRoleList(userId);
		String roleName = LoginController.ROLE_NAME_TO_DISPLAY_ITS_CHARTS;
		String moduleName = moduleNameForReport;//LoginController.MODULE_NAME_TO_DISPLAY_ITS_CHARTS;
		
		//String moduleName = "LIBRARY";
		
		
		/**
		 * new code added for reading queries.xml file from external repository*/
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		String queryPath = repository+"/"+reportQueryPath;
		/***/
		//ChartData chartData =  reportService.loadChart1Data(roleName, moduleName, reportQueryPath);
		ChartData chartData =  reportService.loadChart4DataForLibrary(roleName, moduleName, queryPath);
		
		sb.append(chartData.getChartType() + "||");
		sb.append(chartData.getChartLabel() + "||");
		sb.append("<chart caption = '" + chartData.getCaption() + "' ");
		sb.append("subcaption = '" + chartData.getSubCaption() + "' ");
		if((chartData.getChartType() == "pie2d") || (chartData.getChartType() == "pie3d")){
			
		}else{
			sb.append("xaxisname = '" + chartData.getXaxisname()  + "' ");
			sb.append("yaxisname = '" + chartData.getYaxisname()  + "' ");
			sb.append("numberprefix = '" + chartData.getNumberprefix()  + "' ");
		}
		sb.append("theme = '" + chartData.getTheme() + "'> ");
		
		List<ChartValuesModel> chartValuesModelList = chartData.getChartValuesModelList();
		for(ChartValuesModel chartValuesModel : chartValuesModelList){
			sb.append("<set label = '" + chartValuesModel.getLabel()  + "' value = '" + chartValuesModel.getValue() + "'/>");
		}
		
		sb.append("</chart>");
		
		System.out.println(sb.toString());
        
		return sb.toString();
    }
	/*For chart end*/
}