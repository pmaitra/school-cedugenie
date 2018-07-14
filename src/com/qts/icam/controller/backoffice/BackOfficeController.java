package com.qts.icam.controller.backoffice;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.qts.icam.model.common.StudentTc;
import com.qts.icam.model.common.SessionFees;
import com.qts.icam.model.common.ITSectionGroup;
import com.qts.icam.model.common.IncomeAge;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.ITSectionDetails;
import com.qts.icam.model.common.ITSection;
import com.qts.icam.model.common.QRCodeForHallPass;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Class;
import com.qts.icam.model.backoffice.AttendancePolicy;
import com.qts.icam.model.backoffice.VendorRatingPolicy;
import com.qts.icam.model.backoffice.LibraryPolicy;
import com.qts.icam.model.backoffice.MajorEvents;
import com.qts.icam.model.backoffice.Rating;
import com.qts.icam.model.backoffice.ResidentType;
import com.qts.icam.utility.Utility;
import com.qts.icam.model.backoffice.ResourceAttendance;
import com.qts.icam.model.backoffice.ResourceProfile;
import com.qts.icam.model.common.StudentAttendance;
import com.qts.icam.model.common.WorkShift;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.backoffice.Holiday;
import com.qts.icam.model.backoffice.LeavePolicy;
import com.qts.icam.model.backoffice.Term;
import com.qts.icam.model.common.Course;
import com.google.gson.Gson;
import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.administrator.EmailEventAndTemplate;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.admission.AdmissionProcess;
import com.qts.icam.model.admission.LocationDetailsForPortal;
import com.qts.icam.model.common.FeesCategory;
import com.qts.icam.model.backoffice.AcademicLeave;
import com.qts.icam.model.backoffice.AcademicLeaveCategory;
import com.qts.icam.model.backoffice.AcademicTimeTable;
import com.qts.icam.model.backoffice.AcademicTimeTableDetails;
import com.qts.icam.model.backoffice.AttendanceDetails;
import com.qts.icam.model.backoffice.CalendarEvent;
import com.qts.icam.model.backoffice.DisciplinaryAction;
import com.qts.icam.model.backoffice.ExStudents;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.backoffice.Fees;
import com.qts.icam.model.backoffice.FeesTemplate;
import com.qts.icam.model.backoffice.StudentSubjectMapping;
import com.qts.icam.model.backoffice.StudentTC;
import com.qts.icam.model.backoffice.TimeTableConfigModel;
import com.qts.icam.model.backoffice.TimeTableGridData;
//import com.qts.icam.model.backoffice.TimeTableParameter;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.CategoryAndTemplate;
import com.qts.icam.model.common.Country;
import com.qts.icam.model.common.EventType;
import com.qts.icam.model.common.Image;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.PreviousYearFinanceData;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Scholarship;
import com.qts.icam.model.common.SchoolDetails;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.SmsAudit;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.State;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.erp.EmployeeType;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.hostel.House;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.admission.AdmissionService;
import com.qts.icam.service.backoffice.BackOfficeService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.erp.ERPService;
import com.qts.icam.service.finance.FinanceService;
import com.qts.icam.service.hostel.HostelService;
import com.qts.icam.service.venue.VenueService;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;
import com.qts.icam.model.common.StudentFeesTemplate;
import com.qts.icam.model.common.StudentFeesTemplateDetails;
import com.qts.icam.model.common.FeesDuration;
import com.qts.icam.model.common.FinancialYear;
import com.qts.icam.service.inventory.InventoryService;
import com.qts.icam.model.backoffice.WebIQTransaction;

/**
 * BackOfficeController.java - This controller is responsible for backOffice
 * related operations
 * 
 * @version 1.0
 */
@Controller
@SessionAttributes("sessionObject")
public class BackOfficeController {
	public static Logger logger = Logger.getLogger(BackOfficeController.class);

	@Autowired
	BackOfficeService backOfficeService;
	
	@Autowired
	CommonService commonService;

	@Autowired
	HostelService hostelService;
	
	@Autowired
	AcademicsService academicsService;
	
	@Autowired
	ERPService erpService;

	@Autowired
	FinanceService financeService;
	
	@Autowired
	FileUploadDownload fileUploadDownload;

	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	VenueService venueService;
	
	@Autowired
	AdmissionService admissionService;
	
	@Autowired
	InventoryService inventoryService;
	
	@Autowired
	Utility utility;
	
	@Value("${studentsDetails.excel}")
	private String studentExcel;
	
	@Value("${excelDownload.folder}")
	private String excelDownloadFolder;

	@Value("${excelUpload.folder}")
	private String excelUploadfolder;
	
	@Value("${studentDoc.path}")
	private String studentFolderPath;
	
	@Autowired
	private ServletContext servletContext;

	@Value("${root.path}")
	private String rootPath;
	
	@Value("${studentSubjectMapping.excel}")
	private String studentSubjectMappingExcel;
	
	@Value("${resourceAttendance.excel}")
	private String resourceAttendanceExcel;
	
	@Value("${path.teacherQrCode}")
	private String teacherQrCodePath;
	
	@Value("${path.studentQrCode}")
	private String studentQrCodePath;	
	
	@Value("${path.bookQrCode}")
	private String bookQrCodePath;
	
	@Value("${path.hallPassQrCode}")
	private String hallPassQrCodePath;
	
	/***
	 * author kaustav.sen*/
	
	@Value("${excelUploadteacher.folder}")
	private String excelUploadfolderteacher;
	
	@Value("${excelStudentUpload.folder}")
	private String excelStudentUploadfolder;

	@Value("${resourceStudentAttendance.excel}")
	private String resourceStudentAttendanceExcel;
	
	@Value("${resourceAttendance1.excel}")
	private String resourceAttendanceExcel1;
	
	/**Added by @author Saif.Ali
	 * Date-04/02/2018
	 * Used to get the username and password to use in creating json*/
	@Value("${Portal.userName}")
	private String portalUserName;
	
	@Value("${Portal.passWord}")
	private String portalPassWord;
	
	@Value("${URI.sendCadetDetails}")
	private String URIForSendingCadetDetails;
	
	@Value("${URI.updateCadetDetails}")
	private String URIForUpdateCadetDetails;
	
	@Value("${URI.sendFeesPaymentDetails}")
	private String URIForSendingFeesPaymentDetails;
	
	@Value("${URI.sendDisciplinaryActionDetails}")
	private String URIForSendingDisciplinaryActionDetails;
	
	@Value("${URI.sendAttendanceDetailsOfCadet}")
	private String URIForSendingAttendanceDetailsOfCadet;
	
	@Value("${webiq.available}")
	private String isWebIQAvailable;

	@Value("${sms.enabled}")
	private String isSMSEnabled;

	@Value("${sms-url}")
	private String smsURL;
	
	@Value("${authkey}")
	private String smsAuthkey;

	@Value("${ldap.organization}")
	private String ldapOrganization;
	
	@Value("${ldap.service_username}")
	private String ldapServiceUserName;
	
	@Value("${ldap.service_password}")
	private String ldapServicePassword;

	@Value("${ldap.URI.createUser}")
	private String createUserURL;

	/**Modification ends*/

	/**
	 * @author anup.roy
	 * Opens Academic Year page
	 * Returns String 
	 */	
	@RequestMapping(value = "/getAcademicYear", method = RequestMethod.GET)
	public String getAcademicYear(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createAcademicYear";
		try {
			logger.info("Inside Method getAcademicYear-GET of BackOfficeController");
			List<AcademicYear> academicYearList = backOfficeService.getAcademicYearList();
			model.addAttribute("academicYearList", academicYearList);
		} catch (CustomException ce){
			ce.printStackTrace();
		}
		return strView;
	}
	
	/** @author anup.roy
	 * this method is for adding new academic year
	 * returns getAcademicYear page
	 */	
	@RequestMapping(value = "/editAcademicYear", method = RequestMethod.POST)
	public String editAcademicYear(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, AcademicYear academicYear,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String updateStatus = null;
		try {
			logger.info("Inside Method editAcademicYear-POST of BackOfficeController");
			if(null != academicYear){
				academicYear.setUpdatedBy(sessionObject.getUserId());
				updateStatus = backOfficeService.editAcademicYear(academicYear);
			}else{
				updateStatus = "failed";
			}if(null != updateStatus){
				if(updateStatus.equalsIgnoreCase("failed")){
					model.addAttribute("failuremsg", "Failed to create new academic year");
				}
				if(updateStatus.equalsIgnoreCase("exist")){
					model.addAttribute("failuremsg", "Failed to create new academic year, current year already exists");
				}
				if(updateStatus.equalsIgnoreCase("created")){
					model.addAttribute("successmsg", "New academic year created successfully");
				}
			}
		} catch (CustomException ce){
			logger.error("Exception in editAcademicYear method in backofficecontroller");
			ce.printStackTrace();
		}
		return getAcademicYear(request, response, model);
	}
	
	/**
	 * @author anup.roy
	 * this method is for update the existing academic year
	 * only name is updateable*/
	
	@RequestMapping(value="/updateAcademicYear", method = RequestMethod.POST)
	public String updateAcademicYear(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
									AcademicYear academicYear,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		String message=null;
		try{
			if(null !=academicYear){
				academicYear.setUpdatedBy(sessionObject.getUserId());
				message = backOfficeService.updateAcademicYear(academicYear);
			}else{
				message="failed";
			}
			if(null!=message){
				if(message.equalsIgnoreCase("failed")){
					model.addAttribute("failuremsg", "Failed to update academic year");
				}				
				if(message.equalsIgnoreCase("created")){
					model.addAttribute("successmsg", "Academic year updated successfully");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return getAcademicYear(request,response,model);
	}
	
	//**************************************************Academic Year Ends

	/*
	 * @author vinod.singh
	 * Adds New Fees
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/addFees", method = RequestMethod.POST)
	public String addFees(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("feesName") String feesName,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method addFees-POST of BackOfficeController");
			if(null!=feesName && feesName.length()!=0){
				Fees fees=new Fees();
				fees.setFeesName(feesName.trim().toUpperCase());
				fees.setFeesCode(feesName.trim().toUpperCase());
				fees.setDesc(feesName);
				fees.setUpdatedBy(sessionObject.getUserId());
				
				String insertStatus=backOfficeService.addFees(fees);
				model.addAttribute("insertUpdateStatus", insertStatus);
			}else{
				logger.info("Invalid Fees Name.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method addFees-POST of BackOfficeController", ce);
		}
		return getFees(request, response, model);
	}
	
	
	@RequestMapping(value = "/editFeesTemplate", method = RequestMethod.POST)
	public ModelAndView editFeesTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			FeesTemplate feesTemplate,
			@RequestParam(value="socialCategoryNames") String []socialCategoryNames,
			@RequestParam(value="feesNames") String []feesNames,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editFeesTemplate-POST of BackOfficeController");
			if(null!=feesTemplate && null!=feesTemplate.getTemplateName() && feesTemplate.getTemplateName().trim().length()!=0){
				feesTemplate.setDesc(feesTemplate.getTemplateName().trim());
				feesTemplate.setTemplateName(feesTemplate.getDesc().toUpperCase());
				feesTemplate.setTemplateCode(feesTemplate.getDesc().toUpperCase());
				List<Fees> feesList=new ArrayList<Fees>();
				for(int i=0;i<feesNames.length;i++){
					Fees fees=new Fees();
					fees.setFeesCode(feesNames[i]);
					List<SocialCategory> socialCategoryList=new ArrayList<SocialCategory>();
					for(int j=0;j<socialCategoryNames.length;j++){						
						SocialCategory socialCategory=new SocialCategory();
						socialCategory.setSocialCategoryCode(socialCategoryNames[j]);
						socialCategory.setAmount(Double.parseDouble(request.getParameter(feesNames[i]+socialCategoryNames[j])));
						socialCategoryList.add(socialCategory);
					}
					fees.setSocialCategoryList(socialCategoryList);
					feesList.add(fees);
				}
				feesTemplate.setFeesList(feesList);
				feesTemplate.setUpdatedBy(sessionObject.getUserId());
				
				String insertStatus=backOfficeService.editFeesTemplate(feesTemplate);
				model.addAttribute("insertUpdateStatus", insertStatus);
			}else{
				logger.info("Invalid Fees Template Name.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method editFeesTemplate-POST of BackOfficeController", ce);
		}
		return getFeesTemplateList(request, response, model);
	}
	
	//****************************************************************Fees Template Ends
	
	/*
	 * @author vinod.singh
	 * Opens page verify admission documents 
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getDocumentVerification", method = RequestMethod.GET)
	public ModelAndView getDocumentVerification(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("backoffice/listMedFitCandidate");
		try {
			logger.info("Inside Method getDocumentVerification-GET of BackOfficeController");
			
			List<Candidate> candidateList=backOfficeService.getDocumentVerification();
			//model.addAttribute("candidateList", candidateList);
			if (candidateList != null
					&& candidateList.size() != 0) {
				
				PagedListHolder<Candidate> pagedListHolder = backOfficeService
						.getCandidateListPaging(request);
				mav.addObject("first",
						pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last",
						pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
						
		} catch (CustomException ce){
			logger.error("Exception in method getDocumentVerification-GET of BackOfficeController", ce);
		}
		return mav;
	}
	
	/*
	 * @author vinod.singh
	 * Checks for available roll number
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/checkAvailableRollNumber", method = RequestMethod.GET)
	public @ResponseBody
	String getSectionForClassAndStream(@RequestParam("rollNumber") String rollNumber) {
		String checker = "";
		try {
			checker = backOfficeService.checkAvailableRollNumber(rollNumber);
		} catch (CustomException ce){
			logger.error("Exception in method getDocumentVerification-GET of BackOfficeController", ce);
		}
		return (new Gson().toJson(checker));
	}
	
	/*
	 * @author vinod.singh
	 * Approves Candidate Document
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/approveDocument", method = RequestMethod.POST)
	public ModelAndView approveDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			Candidate candidate) {
		try {
			logger.info("Inside Method approveDocument-POST of BackOfficeController");
			if(null!=candidate && null!=candidate.getRollNumber() && null!=candidate.getAdmissionFormId()){
				candidate.setUpdatedBy(sessionObject.getUserId());
				String updateStatus=backOfficeService.approveDocument(candidate);
				model.addAttribute("insertUpdateStatus", updateStatus);
			}else{
				logger.info("Candidate Details Not Found To Update.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method approveDocument-POST of BackOfficeController", ce);
		}
		return getDocumentVerification(request, response, model);
	}
	
	/*
	 * @author vinod.singh
	 * Approves Candidate Document
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/rejectDocument", method = RequestMethod.POST)
	public ModelAndView rejectDocument(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			Candidate candidate) {
		try {
			logger.info("Inside Method rejectDocument-POST of BackOfficeController");
			if(null!=candidate && null!=candidate.getAdmissionFormId()){
				candidate.setUpdatedBy(sessionObject.getUserId());
				String updateStatus=backOfficeService.rejectDocument(candidate);
				model.addAttribute("insertUpdateStatus", updateStatus);
			}else{
				logger.info("Candidate Details Not Found To Update.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method rejectDocument-POST of BackOfficeController", ce);
		}
		return getDocumentVerification(request, response, model);
	}
	
	/*
	 * @author vinod.singh
	 * Opens page to add student
	 * Returns String
	 * 
	 */	
	


	
	/*
	 * @author vinod.singh
	 * Adds New Student
	 * Returns String
	 * 
	 */	
	
	
	
	
	
	/*
	 * @author vinod.singh
	 * Opens page to list students
	 * Returns String
	 * 
	 */	
	
	
	

	/*
	 * @author vinod.singh
	 * Opens page to edit Student
	 * Returns String
	 * 
	 */	

	
	
	/*
	 * @author vinod.singh
	 * Updates Student
	 * Returns String
	 * 
	 */	

@RequestMapping(value = "/editStudent", method = RequestMethod.POST)
	public ModelAndView editStudent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Student student,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editStudent-POST of BackOfficeController");
			if(null != student){
				/***new code added for save this edited file into external repository**/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				File directory = new File(repository);
				AcademicYear academicYear = commonService.getCurrentAcademicYear();
				boolean isExists = directory.exists();
				String insertStatus = null;
				if(isExists == true){
					Attachment attachment = new Attachment();
					attachment.setStorageRootPath(repository);
					attachment.setFolderName(studentFolderPath);				
					if(null != student.getResource() && null != student.getResource().getUploadFile()){
						student.getResource().getUploadFile().setAttachment(attachment);
					}
					student.setUpdatedBy(sessionObject.getUserId());
					student.getAddress().setUpdatedBy(sessionObject.getUserId());				
					
					String mobile = student.getResource().getMobile();
					mobile = mobile.substring(5, 16);
					mobile = mobile.replace("-", "");
					Resource r = student.getResource();
					r.setMobile(mobile);
					student.setResource(r);
					
					insertStatus=backOfficeService.editStudent(student);
					
					model.addAttribute("insertUpdateStatus", insertStatus);	
					if(insertStatus.equals("Update Successful")){
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("STUDENT DETAILS");
						updateLog.setInsertUpdate("UPDATE");
						updateLog.setModule("OFFICE ADMINISTRATION");
						updateLog.setUpdatedFor(student.getResourceUserId()+"");
						updateLog.setDescription("A Student's Record Was Updated With Roll Number "+student.getResourceUserId()+" In Standard "+student.getStandard()+" ("+student.getSection()+").");
						commonService.insertIntoActivityLog(updateLog);
					}
					
					//PRAD June 17 2018
					// If Insert Successful, and WEBIQ is available, then call the API
					if(insertStatus.equalsIgnoreCase("Update Successful") && isWebIQAvailable.equalsIgnoreCase("true")){
						
						Student stuData= backOfficeService.getAdmissionAndDateOfBirthDate(); //added by Saif
						
						//PRAD JUNE 7 2018
						String standardName = backOfficeService.getStandardNameforCourse(student.getCourseId());

						JSONObject jsonObj = new JSONObject();
						jsonObj.put("username",portalUserName);
						jsonObj.put("password",portalPassWord);
						jsonObj.put("standard",standardName);
						jsonObj.put("courseCode",student.getCourseId());
						
						//jsonObj.put("admissionDrive" , "ADIXIXL-D1");
						//jsonObj.put("formId", "NA");
						jsonObj.put("academicsSession", academicYear.getAcademicYearName());
						
						jsonObj.put("rollNumber", student.getResourceUserId());
						jsonObj.put("firstName", student.getResource().getFirstName());
						jsonObj.put("middleName", student.getResource().getMiddleName());
						jsonObj.put("lastName", student.getResource().getLastName());
						jsonObj.put("contactNumber", student.getResource().getMobile());
						jsonObj.put("dateOfBirth",student.getResource().getDateOfBirth());	//stuData.getStudentCode()
						jsonObj.put("admissisonDate",student.getDateOfAdmission());	// stuData.getDateOfAdmission()
						jsonObj.put("gender", student.getResource().getGender());
						jsonObj.put("bloodGroup", student.getResource().getBloodGroup());
						jsonObj.put("category", student.getResource().getCategory());
						jsonObj.put("religion", student.getResource().getReligion());
						jsonObj.put("motherTongue", student.getResource().getMotherTongue());
						jsonObj.put("aadharNumber", student.getResource().getAadharCardNo());
						jsonObj.put("nationality", student.getResource().getNationality());
						jsonObj.put("childId", student.getResource().getChildId());
						
						//PRAD JUNE 7 2018
						//Get the House Name from the House Code
						String houseName = backOfficeService.getHouseName(student.getHouse());
						
						//jsonObj.put("house", student.getHouse());
						jsonObj.put("house", houseName);
						jsonObj.put("stateOfDomicile", student.getStateOfDomicile());
						jsonObj.put("scholarship", student.getScholarship());
						jsonObj.put("bankName", student.getResource().getBankName());
						jsonObj.put("branch", student.getResource().getBankBranch());
						jsonObj.put("accountNumber", student.getResource().getAccountNumber());
						jsonObj.put("medicalStatus", student.getResource().getMedicalStatus());
						jsonObj.put("email", student.getResource().getEmailId());
						
						//PRAD June 18 2017
						//JSON for Profile Image #1
						//Look we dont have yet the bucket for getting the second image
						//So, only one image
						JSONObject profileImage = new JSONObject();
						Image image = student.getResource().getImage();
						//System.out.println(image);
						long fileSize = image.getImageData().getSize();
						String fileName = image.getImageData().getOriginalFilename();
						String contentType = image.getImageData().getContentType();
						String encodedContents = encodeImage(image.getImageData().getBytes());
						
						profileImage.put("fileName",fileName);
						profileImage.put("fileSize",fileSize);
						profileImage.put("imageBytes",encodedContents);
						profileImage.put("fileType",contentType);
						profileImage.put("conderType","BASE64");
						
						jsonObj.put("profileImage1", profileImage);
						//jsonObj.put("cadetParentImage", "");
						
						jsonObj.put("fatherFirstName", student.getResource().getFatherFirstName());
						jsonObj.put("fatherMiddleName", student.getResource().getFatherMiddleName());
						jsonObj.put("fatherLastName", student.getResource().getFatherLastName());
						jsonObj.put("fatherInDefence", student.getResource().getFatherInDefence());
						jsonObj.put("fatherServiceStatus", student.getResource().getFatherServiceStatus());
						jsonObj.put("fatherDefenceCategory", student.getResource().getFatherDefenceCategory());
						jsonObj.put("fatherRank", student.getResource().getFatherRank());
						jsonObj.put("fatherMobile", student.getResource().getFatherMobile());
						jsonObj.put("fatherEmail", student.getResource().getFatherEmail());
					
						jsonObj.put("motherFirstName", student.getResource().getMotherFirstName());
						jsonObj.put("motherMiddleName", student.getResource().getMotherMiddleName());
						jsonObj.put("motherLastName", student.getResource().getMotherLastName());
						jsonObj.put("motherMobile", student.getResource().getMotherMobile());
						jsonObj.put("motherEmail", student.getResource().getMotherEmail());
						
						jsonObj.put("guardianFirstName", student.getGuardianFirstName());
						jsonObj.put("guardianMiddleName", student.getGuardianMiddleName());
						jsonObj.put("guardianLastName", student.getGuardianLastName());
						jsonObj.put("guardianMobile", student.getGuardianMobile());
						jsonObj.put("guardianEmail", student.getGuardianEmail());
						
						jsonObj.put("fatherIncome", student.getFatherIncome());
						jsonObj.put("motherIncome", student.getMotherIncome());
						jsonObj.put("studentIncome", student.getStudentIncome());
						jsonObj.put("familyIncome", student.getFamilyIncome());
						
						//adding the address properties into JSONArray
						JSONArray addressDetails = new JSONArray();
						JSONObject jsonObjAddress = new JSONObject();
						jsonObjAddress.put("presentAddressLine", student.getAddress().getPresentAddressLine()); 
						jsonObjAddress.put("presentAddressLandmark", student.getAddress().getPermanentAddressLandmark()); 
						jsonObjAddress.put("presentAddressCityVillage", student.getAddress().getPresentAddressCityVillage());
						jsonObjAddress.put("presentAddressPinCode", student.getAddress().getPresentAddressPinCode());
						jsonObjAddress.put("presentAddressDistrict", student.getAddress().getPresentAddressDistrict());
						jsonObjAddress.put("presentAddressState", student.getAddress().getPresentAddressState());
						jsonObjAddress.put("presentAddressCountry", student.getAddress().getPermanentAddressCountry());
						jsonObjAddress.put("presentAddressPostOffice", student.getAddress().getPermanentAddressPostOffice());
						jsonObjAddress.put("presentAddressPoliceStation", student.getAddress().getPermanentAddressPoliceStation());
						
						jsonObjAddress.put("permanentAddressLine", student.getAddress().getPermanentAddressLine()); 
						jsonObjAddress.put("permanentAddressLandmark", student.getAddress().getPermanentAddressLandmark()); 
						jsonObjAddress.put("permanentAddressCityVillage", student.getAddress().getPermanentAddressCityVillage());
						jsonObjAddress.put("permanentAddressPinCode", student.getAddress().getPermanentAddressPinCode());
						jsonObjAddress.put("permanentAddressDistrict", student.getAddress().getPermanentAddressDistrict());
						jsonObjAddress.put("permanentAddressState", student.getAddress().getPermanentAddressState());
						jsonObjAddress.put("permanentAddressCountry", student.getAddress().getPermanentAddressCountry());
						jsonObjAddress.put("permanentAddressPostOffice", student.getAddress().getPermanentAddressPostOffice());
						jsonObjAddress.put("permanentAddressPoliceStation", student.getAddress().getPermanentAddressPoliceStation());
						
						jsonObjAddress.put("guardianAddressLine", student.getAddress().getGuardianAddressLine()); 
						jsonObjAddress.put("guardianAddressLandmark", student.getAddress().getGuardianAddressLandmark()); 
						jsonObjAddress.put("guardianAddressCityVillage", student.getAddress().getGuardianAddressCityVillage());
						jsonObjAddress.put("guardianAddressPinCode", student.getAddress().getGuardianAddressPinCode());
						jsonObjAddress.put("guardianAddressDistrict", student.getAddress().getGuardianAddressDistrict());
						jsonObjAddress.put("guardianAddressState", student.getAddress().getGuardianAddressState());
						jsonObjAddress.put("guardianAddressCountry", student.getAddress().getGuardianAddressCountry());
						jsonObjAddress.put("guardianAddressPostOffice", student.getAddress().getGuardianAddressPostOffice());
						jsonObjAddress.put("guardianAddressPoliceStation", student.getAddress().getGuardianAddressPoliceStation());
						
						addressDetails.put(jsonObjAddress);
						jsonObj.put("address", addressDetails);
						//**Address property addition work ends*//
						
						jsonObj.put("foodPreference", student.getResource().getFoodPreference());
						jsonObj.put("firstPickUpPlace", student.getResource().getFirstPickUpPlace());
						jsonObj.put("hobbies", student.getResource().getHobbies());
						jsonObj.put("personalIdentificationMark", student.getResource().getPersonalIdentificationMark());
						
						jsonObj.put("previousSchoolName", student.getPreviousSchoolName());
						jsonObj.put("previousSchoolWebsite", student.getPreviousSchoolWebsite());
						jsonObj.put("previousSchoolAddress", student.getPreviousSchoolAddress());
						jsonObj.put("previousSchoolPhone", student.getPreviousSchoolPhone());
						jsonObj.put("previousSchoolEmail", student.getPreviousSchoolEmail());
						jsonObj.put("previousAchivement", student.getPreviousAchivement());
						
						System.out.println("LN 3730 :: Json Object Contents"+jsonObj.toString());
						final String uri = URIForUpdateCadetDetails;
						System.out.println("URI:::"+uri);
						/* Initialization */
						URL url = new URL(uri);
						HttpURLConnection connection = null;
						OutputStreamWriter out = null;
						String json_response = "";
						InputStreamReader in = null;
						BufferedReader br = null;
						WebIQTransaction webIQTran= null;
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
							webIQTran.setUri(URIForSendingCadetDetails);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(false);
							//Call to the BackOffice Service
							backOfficeService.addWebIQTransaction(webIQTran);
						}
						System.out.println("JSON response:::"+ json_response);

						/**Modification ends*/
						if((!json_response.isEmpty())){
							JSONObject object = new JSONObject(json_response);
							int statusFromJsonResponse = (int)object.get("status");
							if(statusFromJsonResponse==200){
								//If call to the API is successful, then insert into the webiq_transaction_details table 
								webIQTran = new WebIQTransaction();
								webIQTran.setUri(URIForSendingCadetDetails);
								webIQTran.setUpdatedBy(sessionObject.getUserId());
								webIQTran.setRequestJSON(jsonObj.toString());
								webIQTran.setResponseJSON(json_response);
								webIQTran.setStatus(true);
								//PRAD ENDS
							}else{
								//If Failure then also insert into the webiq_transaction_details table
								webIQTran = new WebIQTransaction();
								webIQTran.setUpdatedBy(sessionObject.getUserId());
								webIQTran.setUri(URIForSendingCadetDetails);
								webIQTran.setRequestJSON(jsonObj.toString());
								webIQTran.setResponseJSON(json_response);
								webIQTran.setStatus(false);
							}
							
							//Call to the BackOffice Service
							backOfficeService.addWebIQTransaction(webIQTran);
						}
					}//IF ENDS
					//PRAD ENDS
				else{
					//System.out.println("directory not found");
				}	
			}
			}
		} catch (CustomException ce){
			logger.error("Exception in method editStudent-POST of BackOfficeController", ce);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return getStudentList(request, response, model);
	}

	
	
	
	
	
	
	
	//****************************************************************Student Ends
	
	/*
	 * @author sayani.datta
	 * Opens page to set the exam date
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getExamDateSetUp", method = RequestMethod.GET)
	public String getExamDateSetUp(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createExamDateSetUp";
		try {
			logger.info("Inside Method getExamDateSetUp-GET of BackOfficeController");
			List<Exam> termList = backOfficeService.getTermForExam();
			model.addAttribute("termList", termList);
			
			List<Exam> examList = backOfficeService.getExamList();
			model.addAttribute("examList", examList);
		} catch (CustomException ce){
			logger.error("Exception in method getExamDateSetUp-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	/*
	 * @author sayani.datta
	 * Adds Exam Set Up
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/addExamDateSetUp", method = RequestMethod.POST)
	public String addExamDateSetUp(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("examStartDate") String[] examStartDate,
			@RequestParam("examEndDate") String[] examEndDate,
			@RequestParam("examCode") String[] examCode,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method addSocialCategory-POST of BackOfficeController");
			if(null!=examStartDate && examStartDate.length!=0){
				for(int loopVar = 0; loopVar<examStartDate.length; loopVar++){
					Exam exam=new Exam();
					if(null != examStartDate[loopVar] && examStartDate[loopVar].length() != 0 && null != examEndDate[loopVar] && examEndDate[loopVar].length() != 0){
						exam.setExamCode(examCode[loopVar].trim());
						exam.setExamStartDate(examStartDate[loopVar]);
						exam.setExamEndDate(examEndDate[loopVar]);
						exam.setUpdatedBy(sessionObject.getUserId());

						String insertStatus=backOfficeService.addExamDateSetUp(exam);
						model.addAttribute("insertUpdateStatus", insertStatus);
					}
				}
			}else{
				logger.info("Invalid Social Category Name.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method addSocialCategory-POST of BackOfficeController", ce);
		}
		return getExamDateSetUp(request, response, model);
	}
	

	/*
	 * @author sayani.datta
	 * Opens page to assign calendar event 
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getAssignEvent", method = RequestMethod.GET)
	public String getAssignEvent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createAssignEvent";
		try {
			logger.info("Inside Method getAssignEvent-GET of BackOfficeController");
			AcademicYear currentYear = commonService
					.getCurrentAcademicYear();
			if (currentYear != null) {
				String strYearArr[] = currentYear.getAcademicYearName().split(
						"-");
				List<AcademicYear> ayList = new ArrayList<AcademicYear>();
				for (int i = 0; i < strYearArr.length; i++) {
					AcademicYear ay = new AcademicYear();
					ay.setAcademicYearName(strYearArr[i]);
					ayList.add(ay);
				}
				model.addAttribute("year", currentYear);
			}
			List<Role> roleList = commonService.getAllRoleList();
			model.addAttribute("roleList", roleList);
			List<EventType> eventTypeList =backOfficeService.getEventType();
			if(eventTypeList!=null && eventTypeList.size()!=0){
				model.addAttribute("eventTypeList", eventTypeList);		
			}	
		} catch (CustomException ce){
			logger.error("Exception in method getAssignEvent-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	/*
	 * @author sayani.datta
	 * Add calendar event
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/addCalenderEvent", method = RequestMethod.POST)
	public String addCalenderEvent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("hiddenevent") String strcalendarevent,
			@RequestParam("hiddenevent1") String strcalendarevent1,
			@RequestParam("roleNames") String strRoleNames,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method addCalenderEvent-POST of BackOfficeController");
			String[] dataevent = strcalendarevent.split("/");
			int manipulatednumber = Integer.parseInt(strcalendarevent1);
			int remainder = dataevent.length - manipulatednumber;
			String startDate = null;
			String endDate = null;
			for (int calendarEventlist = remainder; calendarEventlist<dataevent.length; calendarEventlist++) {
				CalendarEvent calendarEvent = new CalendarEvent();
				EventType eventType = new EventType();
				String[] calendarSpecificStaffEventArray = dataevent[calendarEventlist].split("%%");
				for (int j = 0; j < calendarSpecificStaffEventArray.length; j++) {
					int startmonth = Integer.parseInt(calendarSpecificStaffEventArray[3]) + 1;
					int startend = Integer.parseInt(calendarSpecificStaffEventArray[7]) + 1;
					startDate = calendarSpecificStaffEventArray[2] + "-"+ startmonth + "-"+ calendarSpecificStaffEventArray[4];
					endDate = calendarSpecificStaffEventArray[6] + "-"+ startend + "-"+ calendarSpecificStaffEventArray[8];
					calendarEvent.setUpdatedBy(sessionObject.getUserId());
					calendarEvent.setCalendarEventBy(calendarSpecificStaffEventArray[0]);
					calendarEvent.setCalendarEventName(calendarSpecificStaffEventArray[1]);
					calendarEvent.setCalendarEventStartDate(startDate);
					calendarEvent.setCalendarEventEndDate(endDate);
					calendarEvent.setCalendarEventStartTime(calendarSpecificStaffEventArray[5]);
					calendarEvent.setCalendarEventEndTime(calendarSpecificStaffEventArray[9]);
					calendarEvent.setCalendarEventEndColor(calendarSpecificStaffEventArray[10]);
					calendarEvent.setCalendarEventViewer(calendarSpecificStaffEventArray[11]);
					calendarEvent.setRollName(calendarSpecificStaffEventArray[12]);
					eventType.setEventTypeCode(calendarSpecificStaffEventArray[13]);
					calendarEvent.setEventType(eventType);
				}
				
				String insertStatus=backOfficeService.insertAssignedEvent(calendarEvent);
				model.addAttribute("insertUpdateStatus", insertStatus);
			}

		} catch (CustomException ce){
			logger.error("Exception in method addCalenderEvent-POST of BackOfficeController", ce);
		}
		return getAssignEvent(request, response, model);
	}
	
	/*
	 * @author sayani.datta
	 * Opens page to show assigned calendar event 
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getAssignedEvent", method = RequestMethod.GET)
	public String getAssignedEvent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/showAssignedEvent";
		try {
			logger.info("Inside Method getAssignedEvent-GET of BackOfficeController");
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
			List<Role> roleList = commonService.getAllRoleList();
			model.addAttribute("roleList", roleList);
			List<EventType> eventTypeList =backOfficeService.getEventType();
			if(eventTypeList!=null && eventTypeList.size()!=0){
				model.addAttribute("eventTypeList", eventTypeList);		
			}	
		} catch (CustomException ce){
			logger.error("Exception in method getAssignedEvent-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	/*
	 * @author sayani.datta
	 * Ajax Call to fetch the Assigned Events for All Users
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getCalendarEventFromDBForAllUser", method = RequestMethod.GET)
	public @ResponseBody
	String getCalendarEventFromDBForAllUser(@RequestParam("eventType") String eventType) {
		String events = null;
		try {
			logger.info("Inside Method getCalendarEventFromDBForAllUser-GET of BackOfficeController");
			events = backOfficeService.getCalendarEventFromDBForAllUser(eventType);
		} catch (CustomException ce){
			logger.error("Exception in method getCalendarEventFromDBForAllUser-GET of BackOfficeController", ce);
		}
		return (new Gson().toJson(events));
	}
	
	/*
	 * @author sayani.datta
	 * To show Event Assign Page
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/updateCalenderEvent", method = RequestMethod.POST,params = "addEvent")
	public String showCreateAssignEvent(HttpServletRequest request,
		HttpServletResponse response, ModelMap model) {
		logger.info("Inside Method showCreateAssignEvent-POST of BackOfficeController");
		return getAssignEvent(request, response, model);
	}
	
	/*
	 * @author sayani.datta
	 * To edit the assigned event
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/updateCalenderEvent", method = RequestMethod.POST,params = "editButton")
	public String updateAssignEvent(HttpServletRequest request,
		HttpServletResponse response, ModelMap model,CalendarEvent calendarEvent,
		@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method updateAssignEvent-POST of BackOfficeController");
			calendarEvent.setUpdatedBy(sessionObject.getUserId());
			String updateStatus=backOfficeService.editAssignedEvent(calendarEvent);
			model.addAttribute("insertUpdateStatus", updateStatus);
		} catch (CustomException ce){
			logger.error("Exception in method updateAssignEvent-POST of BackOfficeController", ce);
		}
		return getAssignedEvent(request, response, model);
	}
	
	/*
	 * @author sayani.datta
	 * To delete assigned event
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/updateCalenderEvent", method = RequestMethod.POST,params = "deleteButton")
	public String deleteAssignEvent(HttpServletRequest request,HttpServletResponse response,
			ModelMap model,CalendarEvent calendarEvent,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method deleteAssignEvent-POST of BackOfficeController");
			calendarEvent.setUpdatedBy(sessionObject.getUserId());
			String deleteStatus=backOfficeService.deleteAssignedEvent(calendarEvent);
			model.addAttribute("insertUpdateStatus", deleteStatus);
		} catch (CustomException ce){
			logger.error("Exception in method deleteAssignEvent-POST of BackOfficeController", ce);
		}
		return getAssignedEvent(request, response, model);
	}
	
	/*
	 * @author sayani.datta
	 * Ajax Call to fetch the Assigned Events for Roll Based
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getCalendarEventFromDBForRoleBased", method = RequestMethod.GET)
	public @ResponseBody
	String getCalendarEventFromDBForRoleBased(@RequestParam("rollName") String rollName,
			@RequestParam("eventType") String eventType) {
		String events = null;
		try {
			logger.info("Inside Method getCalendarEventFromDBForRoleBased-GET of BackOfficeController");
			events = backOfficeService.getCalendarEventFromDBForRoleBased(rollName,eventType);
		} catch (CustomException ce){
			logger.error("Exception in method getCalendarEventFromDBForRoleBased-GET of BackOfficeController", ce);
		}
		return (new Gson().toJson(events));
	}
	
	
	
	/*
	 * @author vinod.singh
	 * Opens page to add Class Subject Mapping
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getStudentSubjectMappingList", method = RequestMethod.GET)
	public String getStudentSubjectMappingList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/listStudentSubjectMapping";
		try {
			logger.info("Inside Method getStudentSubjectMappingList-GET of BackOfficeController");
			
			List<StudentSubjectMapping> mappingList=backOfficeService.getStudentSubjectMappingList();
			model.addAttribute("mappingList", mappingList);
			
		} catch (CustomException ce){
			logger.error("Exception in method getStudentSubjectMappingList-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	/*
	 * @author vinod.singh
	 * Opens page to update Student Subject Mapping
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/createStudentSubjectMapping", method = RequestMethod.GET)
	public String createStudentSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="standard") String standard,
			@RequestParam(value="section") String section) {
		String strView = "backoffice/createStudentSubjectMapping";
		try {
			logger.info("Inside Method updateStudentSubjectMapping-GET of BackOfficeController");
			
			Student student=new Student();
			student.setStandard(standard);
			student.setSection(section);
			model.addAttribute("student", student);
			
			List<SubjectGroup> subjectGroupList=academicsService.getSubjectGroup();
			model.addAttribute("subjectGroupList", subjectGroupList);
			
			List<Subject> subjectList=commonService.getSubject();
			model.addAttribute("subjectList", subjectList);
			
			List<Student> studentList=backOfficeService.getStudentsNotInStudentSubjectMapping(student);
			model.addAttribute("studentList", studentList);
			
		} catch (CustomException ce){
			logger.error("Exception in method updateStudentSubjectMapping-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	/*
	 * @author vinod.singh
	 * Updates Student Subject Mapping
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/insertStudentSubjectMapping", method = RequestMethod.POST)
	public String insertStudentSubjectMapping(HttpServletRequest request,
												HttpServletResponse response, ModelMap model,
												@RequestParam(value="rollNumber") String[] rollNumber,
												@RequestParam(value="subjects") String []subjects,
												@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method insertStudentSubjectMapping-POST of BackOfficeController");
			List<String> subjectList=new ArrayList<String>();
			List<StudentSubjectMapping> mappingList=new ArrayList<StudentSubjectMapping>();
			
			if(null!=subjects && subjects.length!=0){
				subjectList=Arrays.asList(subjects);
			}
			if(null!=rollNumber && rollNumber.length!=0){
				for(String roll:rollNumber){
					if(null!=subjectList && subjectList.size()!=0){
						for(String subject:subjectList){
							StudentSubjectMapping studentSubjectMapping=new StudentSubjectMapping();
							Student student=new Student();
							studentSubjectMapping.setSubject(subject);
							student.setRollNumber(Integer.parseInt(roll));
							studentSubjectMapping.setStudent(student);
							studentSubjectMapping.setUpdatedBy(sessionObject.getUserId());
							
							mappingList.add(studentSubjectMapping);
						}
					}
					
				}
				if(mappingList.size()!=0){
					String updateStatus=backOfficeService.insertClassSubjectMapping(mappingList);
					model.addAttribute("insertUpdateStatus", updateStatus);
				}else{
					model.addAttribute("insertUpdateStatus", "No Sunjects Found To Update");
				}
			}else{
				model.addAttribute("insertUpdateStatus", "No Students Found To Update");
			}			
		} catch (CustomException ce){
			logger.error("Exception in method insertStudentSubjectMapping-POST of BackOfficeController", ce);
		}
		return getStudentSubjectMappingList(request, response, model);
	}
	
	/*
	 * @author vinod.singh
	 * Opens page to update Student Subject Mapping
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/updateStudentSubjectMapping", method = RequestMethod.GET)
	public String updateStudentSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="standard") String standard,
			@RequestParam(value="section") String section) {
		String strView = "backoffice/editStudentSubjectMapping";
		try {
			logger.info("Inside Method updateStudentSubjectMapping-GET of BackOfficeController");
			
			Student student=new Student();
			student.setStandard(standard);
			student.setSection(section);
			model.addAttribute("student", student);
			
			List<SubjectGroup> subjectGroupList=academicsService.getSubjectGroup();
			model.addAttribute("subjectGroupList", subjectGroupList);
			
			List<Subject> subjectList=commonService.getSubject();
			model.addAttribute("subjectList", subjectList);
			
			List<Student> studentList=backOfficeService.getStudentsInStudentSubjectMapping(student);
			model.addAttribute("studentList", studentList);
			
		} catch (CustomException ce){
			logger.error("Exception in method updateStudentSubjectMapping-GET of BackOfficeController", ce);
		}
		return strView;
	}
	

	
	/*
	 * @author vinod.singh
	 * Updates Student Subject Mapping
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/editStudentSubjectMapping", method = RequestMethod.POST)
	public String editStudentSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="rollNumber") String rollNumber,
			@RequestParam(value="subjects") String []subjects,
			@RequestParam(value="oldSubjects", required = false) String []oldSubjects,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editStudentSubjectMapping-POST of BackOfficeController");
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
			StudentSubjectMapping studentSubjectMapping=new StudentSubjectMapping();
			Student student=new Student();
			student.setRollNumber(Integer.parseInt(rollNumber));
			studentSubjectMapping.setStudent(student);
			if(insertSubjectList.size()!=0)
				studentSubjectMapping.setNewSubjectList(insertSubjectList);
			if(deleteSubjectList.size()!=0)
				studentSubjectMapping.setOldSubjectList(deleteSubjectList);
			studentSubjectMapping.setUpdatedBy(sessionObject.getUserId());
			if(subjectList.size()!=0){
				String updateStatus=backOfficeService.editStudentSubjectMapping(studentSubjectMapping);
				model.addAttribute("insertUpdateStatus", updateStatus);
			}
		} catch (CustomException ce){
			logger.error("Exception in method editStudentSubjectMapping-POST of BackOfficeController", ce);
		}
		return getStudentSubjectMappingList(request, response, model);
	}
	
	
//	/*
//	 * Might Not Be Required
//	 * @author sayani.datta
//	 * Ajax Call to fetch the Subject And Subject Group For Standard
//	 * Returns String
//	 * 
//	 */	
//	@RequestMapping(value = "/getSubjectsAndGroupForStandard", method = RequestMethod.GET)
//	public @ResponseBody
//	String getSubjectsAndGroupForStandard(@RequestParam("standard") String standard) {
//		String subjectAndGroup = null;
//		try {
//			logger.info("Inside Method getSubjectsAndGroupForStandard-GET of BackOfficeController");
//			subjectAndGroup = backOfficeService.getSubjectsAndGroupForStandard(standard);
//		} catch (CustomException ce){
//			System.out.println("exception"+ce.getMessage());
//			logger.error("Exception in method getSubjectsAndGroupForStandard-GET of BackOfficeController", ce);
//		}
//		return (new Gson().toJson(subjectAndGroup));
//	}
	
	
	
	
	
	
	
									/*Time Table*/
	
	/*
	 * @author sayani.datta
	 * Opens page to open the academic time table page
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getTimeTable", method = RequestMethod.GET)
	public String getTimeTable(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createTimeTable";
		try {
			logger.info("Inside Method getTimeTable-GET of BackOfficeController");
			AcademicYear currentYear = commonService
					.getCurrentAcademicYear();
			model.addAttribute("year", currentYear);
			
			List<Standard> listStandard = commonService.getStandards();
			model.addAttribute("listStandard", listStandard);
			
			List<SubjectGroup> subjectGroupList=academicsService.getSubjectGroup();
			model.addAttribute("subjectGroupList", subjectGroupList);
			
			SchoolDetails schoolDetails = commonService.selectSchoolTimeTable();
			model.addAttribute("schoolDetails", schoolDetails);
			
		} catch (CustomException ce){
			logger.error("Exception in method getTimeTable-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	/*
	 * @author sayani.datta
	 * Ajax call to fetch subjects based on standard and subject group
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getSubjectsBasedOnStandardAndSubjectGroup", method = RequestMethod.GET)
	public @ResponseBody
	String getSubjectsBasedOnStandardAndSubjectGroup(@RequestParam("standard") String standard,
			@RequestParam("subjectGroup") String subjectGroup) {
		logger.info("Inside Method getSubjectsBasedOnStandardAndSubjectGroup-GET of BackOfficeController");
		String showDateone = "";
		try {
			Subject subject = new Subject();
			subject.setSubjectGroup(subjectGroup);
			subject.setDesc(standard);
			showDateone = backOfficeService.getSubjectsBasedOnStandardAndSubjectGroup(subject);
		} catch (CustomException ce) {
			logger.error("Exception in method getSubjectsBasedOnStandardAndSubjectGroup-GET of BackOfficeController", ce);
		}		
		return (new Gson().toJson(showDateone));
	}
	
	
	/*
	 * @author sayani.datta
	 * Add period duration for time table
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/addPeriodDurationforTimeTable", method = RequestMethod.POST)
	public ModelAndView addPeriodDurationforTimeTable(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AcademicTimeTableDetails academicTimeTableDetails,
			Section sectionObj, Standard standardObj,AcademicYear academicYearObj,
			AcademicTimeTable academicTimeTable,
			@RequestParam(required = false, value = "hiddenDatetoset") String startDate,
			@RequestParam(required = false, value = "hiddensectiontoset") String sectionCode,
			@RequestParam(required = false, value = "radioClassName") String StandardCode,
			@RequestParam(required = false, value = "hiddenDaytoset") String day,
			@RequestParam(required = false, value = "hiddenSchoolStartTime") String SchoolStartTime,
			@RequestParam(required = false, value = "hiddenperiodtoset") String noOfPeriods,
			@RequestParam(required = false, value = "hiddenStartTimetoset") String individualSlot,
			@RequestParam(required = false, value = "PeriodstartTime") String periodStartTime,
			@RequestParam(required = false, value = "hiddenEndTimetoset") String periodEndTime,
			@RequestParam(required = false, value = "radioYearName") String academicYearStr,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("Inside Method addPeriodDurationforTimeTable-POST of BackOfficeController");
		try {
			sectionObj.setSectionCode(sectionCode);
			standardObj.setStandardCode(StandardCode);
			academicYearObj.setAcademicYearCode(academicYearStr);
			academicTimeTableDetails.setTimeTableDetailsStartDate(startDate);
			academicTimeTableDetails.setTimeTableDetailsEndDate(startDate);
			academicTimeTableDetails.setIndividualSlot(individualSlot);
			academicTimeTableDetails.setTimeTableDetailsStartTime(periodStartTime);
			academicTimeTableDetails.setTimeTableDetailsEndTime(periodEndTime);
			academicTimeTableDetails.setTimeTableDetailsDuration(0);
			academicTimeTableDetails.setSchoolStartTime(SchoolStartTime); // minTime
																				// in
																				// fullCalender

			academicTimeTable.setTimeTableSection(sectionObj);
			academicTimeTable.setTimeTableClass(standardObj);
			academicTimeTable.setAcademicYear(academicYearObj);
			academicTimeTable.setUpdatedBy(sessionObject.getUserId());
			academicTimeTable.setStatus("PERIODDURATION");
			academicTimeTable.setTotalSlot(noOfPeriods);// inserting no of
															// periods in desc
															// maxTime in
															// fullCalender
			
			academicTimeTable.setTimeTableDay(day);

			academicTimeTable
					.setAcademicTimeTableDetails(academicTimeTableDetails);
			if (SchoolStartTime != "" && periodEndTime != "") {
				String insertStatus=backOfficeService.addTimeTable(academicTimeTable);
				model.addAttribute("insertUpdateStatus", insertStatus);
			}
		} catch (Exception ce) {
			logger.error("Exception in method addPeriodDurationforTimeTable-POST of BackOfficeController", ce);
		}	
		return openEditPageForTimeTable(request, response, model, sectionObj, standardObj, academicYearObj, sectionCode, StandardCode, academicYearStr, sessionObject);
	}
	
	/*
	 * @author sayani.datta
	 * Ajax call to fetch duration for validation for time table
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getDurationForValidationForParticularSlot", method = RequestMethod.GET)
	public @ResponseBody
	String getDurationForValidationForParticularSlot(@RequestParam("standard") String standard,
			@RequestParam("section") String section,
			@RequestParam("timeSlot") String timeSlot,
			@RequestParam("year") String year) {
		logger.info("Inside Method getDurationForValidationForParticularSlot-GET of BackOfficeController");
		String showDetails = "";
		try {
			Section sectionObj = new Section();
			Standard standardObj = new Standard();
			AcademicYear academicYearObj = new AcademicYear();
			sectionObj.setSectionCode(section);
			standardObj.setStandardCode(standard);
			academicYearObj.setAcademicYearCode(year);
			AcademicTimeTable academicTimeTable = new AcademicTimeTable();
			AcademicTimeTableDetails academicTimeTableDetails = new AcademicTimeTableDetails();
			
			academicTimeTableDetails.setIndividualSlot(timeSlot);
			academicTimeTable.setAcademicTimeTableDetails(academicTimeTableDetails);
			academicTimeTable.setTimeTableSection(sectionObj);
			academicTimeTable.setTimeTableClass(standardObj);
			academicTimeTable.setAcademicYear(academicYearObj);
			showDetails  = backOfficeService.getTimeTableDurationSlotForValidation(academicTimeTable);
		} catch (CustomException ce) {
			logger.error("Exception in method getDurationForValidationForParticularSlot-GET of BackOfficeController", ce);
		}		
		return (new Gson().toJson(showDetails));
	}
	
	/*
	 * @author sayani.datta
	 * Add teacher and subject for time table
	 * Returns String
	 * 
	 */
	
	
	@RequestMapping(value = "/addSubjectBreakAndTeacherForTimeTable", method = RequestMethod.POST)
	public ModelAndView addSubjectBreakAndTeacherForTimeTable(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			AcademicTimeTableDetails academicTimeTableDetails,
			Section sectionObj,
			Standard standardObj,
			AcademicYear academicYearObj,
			@RequestParam(required = false, value ="hiddensectiontoset") String sectionCode,
			@RequestParam(required = false, value ="radioClassName") String standardCode,
			@RequestParam(required = false, value ="hiddenSchoolStartTime") String SchoolStartTime,
			@RequestParam(required = false, value ="hiddenperiodtoset") String noOfPeriods,
			@RequestParam(required = false, value ="storeDraggedEvent") String storeDraggedEvent,
			@RequestParam(required = false, value ="storeDraggedTeacherEvent") String storeDraggedTeacherEvent,
			@RequestParam(required = false, value ="storeDraggedEventCount") String storeDraggedEventCount,
			@RequestParam(required = false, value ="storeDraggedTeacherEventCount") String storeDraggedTeacherEventCount,
			@RequestParam(required = false, value ="radioYearName") String radioYearNameStr,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("Inside Method addSubjectBreakAndTeacherForTimeTable-GET of BackOfficeController");
		try {
			sectionObj.setSectionCode(sectionCode);
			standardObj.setStandardCode(standardCode);
			academicYearObj.setAcademicYearCode(radioYearNameStr);
			String[] dataevent = storeDraggedEvent.split("/");
			String[] teacherEvent = storeDraggedTeacherEvent.split("/");
			int manipulatednumber = Integer.parseInt(storeDraggedEventCount);
			int manipulatedAnotherNumber = Integer.parseInt(storeDraggedTeacherEventCount);
			int remainder = dataevent.length - manipulatednumber;
			int remainderAnother = teacherEvent.length - manipulatedAnotherNumber;
			String startDate = null;

			for (int calendarEventlist = remainder; calendarEventlist < dataevent.length; calendarEventlist++) {
				AcademicTimeTable academicTimeTable1 = new AcademicTimeTable();
				String[] timeTableEventArray = dataevent[calendarEventlist]
						.split(",");
				for (int j = 0; j < timeTableEventArray.length; j++) {
					int startmonth = Integer.parseInt(timeTableEventArray[3]) + 1;
					startDate = timeTableEventArray[2] + "/" + startmonth + "/"
							+ timeTableEventArray[4];
					if (timeTableEventArray[1].trim().equals("SHORT BREAK")
							|| timeTableEventArray[1].trim().equals(
									"LONG BREAK")) {
						academicTimeTableDetails
								.setTimeTableDetailsStartDate(startDate);
						academicTimeTableDetails
								.setTimeTableDetailsEndDate(startDate);
						academicTimeTableDetails
								.setTimeTableDetailsStartTime(timeTableEventArray[5]);
						academicTimeTableDetails
								.setTimeTableDetailsEndTime("0");
						academicTimeTableDetails.setTimeTableDetailsDuration(0);
						academicTimeTableDetails.setSchoolStartTime(SchoolStartTime); // minTime
																			// in
																			// fullCalender
						academicTimeTableDetails
								.setIndividualSlot(timeTableEventArray[5]);
						academicTimeTableDetails.setTimeTableDetailsSubject(timeTableEventArray[1]);
						academicTimeTable1.setTimeTableSection(sectionObj);
						academicTimeTable1.setTimeTableClass(standardObj);
						academicTimeTable1.setAcademicYear(academicYearObj);
						academicTimeTable1.setUpdatedBy(sessionObject.getUserId());
						academicTimeTable1.setStatus("BREAK");
						academicTimeTable1.setTotalSlot(noOfPeriods);// inserting
																			// no
																			// of
																			// periods
																			// in
																			// desc
																			// maxTime
																			// in
																			// fullCalender
						academicTimeTable1
								.setTimeTableDay(timeTableEventArray[6]);

						academicTimeTable1
								.setAcademicTimeTableDetails(academicTimeTableDetails);

					}
					if (!timeTableEventArray[1].trim().equals("SHORT BREAK")
							|| !timeTableEventArray[1].trim().equals(
									"LONG BREAK")) {
						String[] selectedSubjectGroup = timeTableEventArray[0].trim().split("#");
						academicTimeTableDetails
								.setTimeTableDetailsStartDate(startDate);
						academicTimeTableDetails
								.setTimeTableDetailsEndDate(startDate);
						academicTimeTableDetails
								.setIndividualSlot(timeTableEventArray[5]);
						academicTimeTableDetails.setTimeTableDetailsDuration(0);
						academicTimeTableDetails
								.setSchoolStartTime(SchoolStartTime); // minTime
																			// in
																			// fullCalender
						academicTimeTableDetails
								.setTimeTableDetailsSubject(timeTableEventArray[1]);
						academicTimeTableDetails.setTimeTableDetailsSubjectGroup(selectedSubjectGroup[1]);
						for (int calendarTeacherEventlist = remainderAnother; calendarTeacherEventlist < teacherEvent.length; calendarTeacherEventlist++) {

							String[] timeTableTeacherEventArray = teacherEvent[calendarTeacherEventlist]
									.split(",");
							for (int k = 0; k < timeTableTeacherEventArray.length; k++) {
								if (timeTableTeacherEventArray[1]
										.equals(timeTableEventArray[2])
										&& timeTableTeacherEventArray[2]
												.equals(timeTableEventArray[3])
										&& timeTableTeacherEventArray[3]
												.equals(timeTableEventArray[4])
										&& timeTableTeacherEventArray[4]
												.equals(timeTableEventArray[5])
										&& timeTableTeacherEventArray[5]
												.equals(timeTableEventArray[6])) {
									academicTimeTableDetails
											.setTimeTableDetailsTeacherName(timeTableTeacherEventArray[0]);
									String[] periodTimeSlot = timeTableTeacherEventArray[6].split("-");
									academicTimeTableDetails.setTimeTableDetailsStartTime(periodTimeSlot[0]);
									academicTimeTableDetails.setTimeTableDetailsEndTime(periodTimeSlot[1]);
								}
							}
							//
						}

						academicTimeTable1.setTimeTableSection(sectionObj);
						academicTimeTable1.setTimeTableClass(standardObj);
						academicTimeTable1.setAcademicYear(academicYearObj);
						academicTimeTable1.setUpdatedBy(sessionObject.getUserId());
						academicTimeTable1.setStatus("SUBJECTANDTEACHER");
						academicTimeTable1.setTotalSlot(noOfPeriods);// inserting
																			// no
																			// of
																			// periods
																			// in
																			// desc
																			// maxTime
																			// in
																			// fullCalender
						academicTimeTable1
								.setTimeTableDay(timeTableEventArray[6]);

						academicTimeTable1
								.setAcademicTimeTableDetails(academicTimeTableDetails);

					}
				}
				
				String insertStatus=backOfficeService.addSubjectBreakAndTeacherForTimeTable(academicTimeTable1);
				model.addAttribute("insertUpdateStatus", insertStatus);

			}
		} catch (Exception ce) {
			logger.error("Exception in method addSubjectBreakAndTeacherForTimeTable-POST of BackOfficeController", ce);
		}	
		return openEditPageForTimeTable(request, response, model, sectionObj, standardObj, academicYearObj, sectionCode, standardCode, radioYearNameStr, sessionObject);
	}
	
	/*
	 * @author sayani.datta
	 * Fetch the teacher user id based on subjects
	 * Returns String
	 * 
	 */
	
	@RequestMapping(value = "/getTeacherPerSubject", method = RequestMethod.GET)
	public @ResponseBody
	String getTeacherPerSubject(
			@RequestParam("subjectName") String subjectName) {
		logger.info("Inside Method getTeacherPerSubject-GET of BackOfficeController");
		String teacherDetails = "";
		try{
			
			teacherDetails = backOfficeService
					.getTeacherNames(subjectName);
		}
			catch (CustomException ce) {
				logger.error("Exception in method getTeacherPerSubject-GET of BackOfficeController", ce);
			}		
			return (new Gson().toJson(teacherDetails));
	}
	
	/*
	 * @author sayani.datta
	 * Fetch the teacher user id and time slot for validation
	 * Returns String
	 * 
	 */
	
	@RequestMapping(value = "/getTeacherConflictionForTimeTable", method = RequestMethod.GET)
	public @ResponseBody
	String getTeacherConflictionForTimeTable(
			@RequestParam("standard") String standard,
			@RequestParam("section") String section,
			@RequestParam("daySlot") String daySlot,
			@RequestParam("year") String academicYearCode,
			@RequestParam("userId") String userId,
			@RequestParam("periodStartTime") String periodStartTime,
			@RequestParam("periodEndTime") String periodEndTime) {

		String output = null;
		logger.info("Inside Method getTeacherConflictionForTimeTable-GET of BackOfficeController");
		try {
			Section sectionObj = new Section();
			Standard standardObj = new Standard();
			AcademicYear academicYearObj = new AcademicYear();
			sectionObj.setSectionName(section.trim());
			standardObj.setStandardCode(standard.trim());
			academicYearObj.setAcademicYearCode(academicYearCode.trim());
			
			AcademicTimeTable academicTimeTable = new AcademicTimeTable();
			AcademicTimeTableDetails academicTimeTableDetails = new AcademicTimeTableDetails();
			
			academicTimeTableDetails.setTimeTableDetailsUserId(userId.trim());
			academicTimeTableDetails.setTimeTableDetailsStartTime(periodStartTime.trim());
			academicTimeTableDetails.setTimeTableDetailsEndTime(periodEndTime.trim());
			
			academicTimeTable.setTimeTableDay(daySlot.trim());
			academicTimeTable.setTimeTableSection(sectionObj);
			academicTimeTable.setTimeTableClass(standardObj);
			academicTimeTable.setAcademicYear(academicYearObj);
			academicTimeTable.setAcademicTimeTableDetails(academicTimeTableDetails);
			output = backOfficeService.getTeacherConflictionForTimeTable(academicTimeTable);
			
		}catch (CustomException ce) {
			logger.error("Exception in method getTeacherConflictionForTimeTable-GET of BackOfficeController", ce);
		}	
		return (new Gson().toJson(output));
	}
	
	
	/*
	 * @author sayani.datta
	 * To delete assigned event
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/editPageForTimeTable", method = RequestMethod.POST)
	public ModelAndView openEditPageForTimeTable(HttpServletRequest request,HttpServletResponse response,
			ModelMap model,Section sectionObj,
			Standard standardObj,
			AcademicYear academicYearObj,
			@RequestParam(required = false, value = "hiddensectiontoset") String sectionCode,
			@RequestParam(required = false, value = "radioClassName") String StandardCode,
			@RequestParam(required = false, value = "radioYearName") String academicYearStr,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method openEditPageForTimeTable-POST of BackOfficeController");
			AcademicTimeTable academicTimeTable = new AcademicTimeTable();
			sectionObj.setSectionCode(sectionCode);
			standardObj.setStandardCode(StandardCode);
			academicYearObj.setAcademicYearCode(academicYearStr);

			academicTimeTable.setTimeTableSection(sectionObj);
			academicTimeTable.setTimeTableClass(standardObj);
			academicTimeTable.setAcademicYear(academicYearObj);
			academicTimeTable.setUpdatedBy(sessionObject.getUserId());
			List<AcademicTimeTable> timeTableDetails = backOfficeService
					.getTimeTableDetails(academicTimeTable);
			if (timeTableDetails != null && timeTableDetails.size() != 0) {
				model.addAttribute("timeTableDetails", timeTableDetails);
			}
			List<AcademicTimeTableDetails> timeTableSubjectCount = backOfficeService
					.getTimeTableSubjectCount(academicTimeTable);
			if (timeTableSubjectCount != null
					&& timeTableSubjectCount.size() != 0) {
				model.addAttribute("timeTableSubjectCount",
						timeTableSubjectCount);
			}
			List<SubjectGroup> subjectGroupList=academicsService.getSubjectGroup();
			model.addAttribute("subjectGroupList", subjectGroupList);
			
			SchoolDetails schoolDetails = commonService.selectSchoolTimeTable();
			model.addAttribute("schoolDetails", schoolDetails);
		} catch (CustomException ce){
			logger.error("Exception in method openEditPageForTimeTable-POST of BackOfficeController", ce);
		}
		return new ModelAndView("backoffice/createTimeTable");
	}
	/*
	 * @author sayani.datta
	 * To delete assigned subject and teacher
	 * Returns String
	 * 
	 */	
	
	
	@RequestMapping(value = "/deleteDraggedElementForTimeTable", method = RequestMethod.POST)
	public ModelAndView deleteDraggedElementForTimeTable(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,Section sectionObj,  Standard standardObj,AcademicYear academicYearObj,
			@RequestParam("draggedElementIdForDelete") String strTimeTableId,
			@RequestParam("hiddensectiontoset") String sectionCode,
			@RequestParam("radioClassName") String standardCode,
			@RequestParam("radioYearName") String academicYearStr,
			AcademicTimeTableDetails academicTimeTableDetails,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("Inside Method openEditPageForTimeTable-POST of BackOfficeController");
		try {
			AcademicTimeTable academicTimeTable = new AcademicTimeTable();
			int intTimeTableId = Integer.parseInt(strTimeTableId);
			academicTimeTable.setTimeintTableCode(intTimeTableId);
			academicTimeTable.setUpdatedBy(sessionObject.getUserId());
			
			String updateStatus = backOfficeService.deleteDraggedElementForAcademicTimeTable(academicTimeTable);
			model.addAttribute("insertUpdateStatus", updateStatus);
		} catch (Exception ce) {
			logger.error("Exception in method addSubjectBreakAndTeacherForTimeTable-POST of BackOfficeController", ce);
		}	
		return openEditPageForTimeTable(request, response, model, sectionObj, standardObj, academicYearObj, sectionCode, standardCode, academicYearStr, sessionObject);
	
	}
	
	/*
	 * @author sayani.datta
	 * To edit assigned period duration 
	 * Returns String
	 * 
	 */	
	
	@RequestMapping(value = "/updateAssignedDuration", method = RequestMethod.POST)
	public ModelAndView updateAssignedDuration(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @RequestParam("hiddenUpdateId") String strTimeId,
			@RequestParam("hiddenUpdateTime") String strTime,
			@RequestParam("hiddensectiontoset") String sectionCode,
			@RequestParam("radioClassName") String standardCode,
			@RequestParam("radioYearName") String academicYearCodeStr,
			Section sectionObj, Standard standardObj,AcademicYear academicYearObj,
			AcademicTimeTableDetails academicTimeTable,AcademicTimeTable a,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("Inside Method updateAssignedDuration-POST of BackOfficeController");
		try {
			int intTimeId = Integer.parseInt(strTimeId);
			String[] splitTime = strTime.split("-");
			academicTimeTable.setTimeTableDetailsId(intTimeId);
			academicTimeTable.setTimeTableDetailsStartTime(splitTime[0]);
			academicTimeTable.setTimeTableDetailsEndTime(splitTime[1]);
			academicTimeTable.setUpdatedBy(sessionObject.getUserId());
			String updateStatus = backOfficeService.updateAssignedPeriodDuration(academicTimeTable);
			model.addAttribute("insertUpdateStatus", updateStatus);
		} catch (Exception ce) {
			logger.error("Exception in method updateAssignedDuration-POST of BackOfficeController", ce);
		}	
		return openEditPageForTimeTable(request, response, model, sectionObj, standardObj, academicYearObj, sectionCode, standardCode, academicYearCodeStr, sessionObject);
	}
	
	/*
	 * @author sayani.datta
	 * To edit assigned teacher and subject position
	 * Returns String
	 * 
	 */	
	
	@RequestMapping(value = "/updateAssignedSubjectAndTeacher", method = RequestMethod.POST)
	public ModelAndView updateAssignedSubjectAndTeacher(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@RequestParam("draggedElementIdForUpdate") String strValueToEdit,
			@RequestParam("hiddensectiontoset") String sectionCode,
			@RequestParam("radioClassName") String standardCode,
			@RequestParam("radioYearName") String academicYearStr,
			Section sectionObj, Standard standardObj,AcademicYear academicYearObj, 
			AcademicTimeTable academicTimeTable,
			AcademicTimeTableDetails academicTimeTableDetails,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("Inside Method updateAssignedSubjectAndTeacher-POST of BackOfficeController");
		try {
			String startDate = "";
			String[] splitedValueToEdit = strValueToEdit.split(",");

			for (int dataNo = 0; dataNo < splitedValueToEdit.length; dataNo++) {
				int intTimeId = Integer.parseInt(splitedValueToEdit[0]);

				int startmonth = Integer.parseInt(splitedValueToEdit[2]) + 1;
				startDate = splitedValueToEdit[1] + "/" + startmonth + "/"+ splitedValueToEdit[3];
				academicTimeTable.setUpdatedBy(sessionObject.getUserId());
				academicTimeTable.setTimeintTableCode(intTimeId);
				academicTimeTable.setTimeTableDay(splitedValueToEdit[5]);
				
				academicTimeTableDetails.setTimeTableDetailsStartDate(startDate);
				academicTimeTableDetails.setTimeTableDetailsEndDate(startDate);
				academicTimeTableDetails.setIndividualSlot(splitedValueToEdit[4]);
				
				
				if (splitedValueToEdit[8].trim().equals("SHORT BREAK")
						|| splitedValueToEdit[8].trim().equals(
								"LONG BREAK")){
					academicTimeTableDetails.setTimeTableDetailsStartTime(splitedValueToEdit[4]);
					academicTimeTableDetails.setTimeTableDetailsEndTime("0");
				}
				
				if (!splitedValueToEdit[8].trim().equals("SHORT BREAK")
						&& !splitedValueToEdit[8].trim().equals(
								"LONG BREAK")){
					academicTimeTableDetails.setTimeTableDetailsStartTime(splitedValueToEdit[6]);
					academicTimeTableDetails.setTimeTableDetailsEndTime(splitedValueToEdit[7]);
				}
				
			}
			academicTimeTable.setAcademicTimeTableDetails(academicTimeTableDetails);
			
			String updateStatus = backOfficeService.updateSubjectTypeTimeTable(academicTimeTable);
			model.addAttribute("insertUpdateStatus", updateStatus);
			
		} catch (Exception ce) {
			logger.error("Exception in method updateAssignedSubjectAndTeacher-POST of BackOfficeController", ce);
		}	
		return openEditPageForTimeTable(request, response, model, sectionObj, standardObj, academicYearObj, sectionCode, standardCode, academicYearStr, sessionObject);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * @author sayani.datta
	 * Paginattion for fees template list
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/listFeesTemplatePagination", method = RequestMethod.GET)
	public ModelAndView feesTemplateListPagination(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("Inside Method feesTemplateListPagination-GET of BackOfficeController");
			mav = new ModelAndView("backoffice/listFeesTemplate");
			PagedListHolder<FeesTemplate> pagedListHolder = backOfficeService.getFeesTemplateListPaging(request);
			if (pagedListHolder != null) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		}  catch (CustomException ce){
			logger.error("Exception in method feesTemplateListPagination-GET of BackOfficeController", ce);
		}
		return mav;
	}
	
	/*
	 * @author sayani.datta
	 * Search for fees template list
	 * Returns String
	 * 
	 */	
	
	
	@RequestMapping(value = "/getFeesTemplateDetails", method = RequestMethod.POST, params = "searchSubmit")
	public ModelAndView searchFeesTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(required = false, value = "searchKey") String query,
			@RequestParam(required = false, value = "searchValue") String data) {
		logger.info("Inside Method searchFeesTemplate-POST of BackOfficeController");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(query!=null && data!=null){
			if (query.equalsIgnoreCase("TemplateName")) {
				parameters.put("TemplateName", data.trim());
			}
			if (query.equalsIgnoreCase("Standard")) {
				parameters.put("Standard", data.trim());
			}
		}
		try {
			backOfficeService.getSearchBasedFeesTemplateList(parameters);
			
		} catch (CustomException ce){
			logger.error("Exception in method searchFeesTemplate-POST of BackOfficeController", ce);
		}
		return feesTemplateListPagination(request, response);
	}
	
	
	/*
	 * @author sayani.datta
	 * Pagination for student template list
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/listStudentsPagination", method = RequestMethod.GET)
	public ModelAndView studentsPagination(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("Inside Method studentsPagination-GET of BackOfficeController");
			mav = new ModelAndView("backoffice/listStudents");
			PagedListHolder<Student> pagedListHolder = backOfficeService
					.getStudentListPaging(request);
			if (pagedListHolder != null) {
				mav.addObject("first",
						pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last",
						pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		}  catch (CustomException ce){
			logger.error("Exception in method studentsPagination-GET of BackOfficeController", ce);
		}
		return mav;
	}
	
	/*
	 * @author sayani.datta
	 * Search for student list
	 * Returns String
	 * 
	 */	
	
	
	@RequestMapping(value = "/getStudentDetailsToEdit", method = RequestMethod.POST, params = "searchSubmit")
	public ModelAndView searchStudent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(required = false, value = "searchKey") String query,
			@RequestParam(required = false, value = "searchValue") String data) {
		logger.info("Inside Method searchStudent-POST of BackOfficeController");
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(query!=null && data!=null){
			if (query.equalsIgnoreCase("RollNumber")) {
				parameters.put("RollNumber", data.trim());
			}
			if (query.equalsIgnoreCase("FirstName")) {
				parameters.put("FirstName", data.trim());
			}
			if (query.equalsIgnoreCase("MiddleName")) {
				parameters.put("MiddleName", data.trim());
			}
			if (query.equalsIgnoreCase("LastName")) {
				parameters.put("LastName", data.trim());
			}
			if (query.equalsIgnoreCase("Standard")) {
				parameters.put("Standard", data.trim());
			}
			if (query.equalsIgnoreCase("Section")) {
				parameters.put("Section", data.trim());
			}
			if (query.equalsIgnoreCase("House")) {
				parameters.put("House", data.trim());
			}
			if (query.equalsIgnoreCase("SecondLanguage")) {
				parameters.put("SecondLanguage", data.trim());
			}
			
		}
		try {
			backOfficeService.getSearchBasedStudentList(parameters);
			
		} catch (CustomException ce){
			logger.error("Exception in method searchStudent-POST of BackOfficeController", ce);
		}
		return studentsPagination(request, response);
	}
	
	/*
	 * @author sayani.datta
	 * Pagination for document verification
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/listMedFitPagination", method = RequestMethod.GET)
	public ModelAndView medFitPagination(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("Inside Method medFitPagination-GET of BackOfficeController");
			mav = new ModelAndView("backoffice/listMedFitCandidate");
			PagedListHolder<Candidate> pagedListHolder = backOfficeService
					.getCandidateListPaging(request);
			if (pagedListHolder != null) {
				mav.addObject("first",
						pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last",
						pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		}  catch (CustomException ce){
			logger.error("Exception in method medFitPagination-GET of BackOfficeController", ce);
		}
		return mav;
	}
	
	/*
	 * @author sayani.datta
	 * Search for Medical Fit Documents
	 * Returns String
	 * 
	 */	
	
	
	@RequestMapping(value = "/getMedFitDocumentsSearchDetails", method = RequestMethod.POST)
	public ModelAndView searchMedFitDocumentsSearchDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(required = false, value = "searchKey") String query,
			@RequestParam(required = false, value = "searchValue") String data) {
		logger.info("Inside Method searchMedFitDocumentsSearchDetails-POST of BackOfficeController");
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(query!=null && data!=null){
			if (query.equalsIgnoreCase("FormId")) {
				parameters.put("FormId", data.trim());
			}
			if (query.equalsIgnoreCase("Standard")) {
				parameters.put("Standard", data.trim());
			}
			if (query.equalsIgnoreCase("FirstName")) {
				parameters.put("FirstName", data.trim());
			}
			if (query.equalsIgnoreCase("MiddleName")) {
				parameters.put("MiddleName", data.trim());
			}
			if (query.equalsIgnoreCase("LastName")) {
				parameters.put("LastName", data.trim());
			}
			if (query.equalsIgnoreCase("DOB")) {
				parameters.put("DOB", data.trim());
			}
			
		}
		try {
			backOfficeService.getSearchBasedMedFitDocumentsList(parameters);
			
		} catch (CustomException ce){
			logger.error("Exception in method searchMedFitDocumentsSearchDetails-POST of BackOfficeController", ce);
		}
		return medFitPagination(request, response);
	}
	
	/*
	 * @author vinod.singh
	 * Opens page to add Class Subject Mapping
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getTeacherSubjectMapping", method = RequestMethod.GET)
	public String getTeacherSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createTeacherSubjectMapping";
		try {
			logger.info("Inside Method getTeacherSubjectMapping-GET of AcademicsController");
			List<SubjectGroup> subjectGroupList=academicsService.getSubjectGroup();
			model.addAttribute("subjectGroupList", subjectGroupList);
			
			List<Subject> subjectList=commonService.getSubject();
			model.addAttribute("subjectList", subjectList);
			
			List<Resource> teacherList=commonService.getAllTeacherList();
			model.addAttribute("teacherList", teacherList);
			
		} catch (CustomException ce){
			logger.error("Exception in method getTeacherSubjectMapping-GET of AcademicsController", ce);
		}
		return strView;
	}	
	
	/* 
	 * Gets Subjects For Standard
	 */
	@RequestMapping(value = "/getSubjectsForTeacher", method = RequestMethod.GET)
	public @ResponseBody
	String getSubjectsForTeacher(@RequestParam("teacher") String teacher) {
		logger.info("Inside Method getSubjectsForTeacher-GET of AcademicsController");
		String subjects = "";
		try {
			subjects = backOfficeService.getSubjectsForTeacher(teacher);
		} catch (CustomException ce) {
			logger.error("Exception in method getSubjectsForTeacher-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(subjects));
	}
	
	/*
	 * @author vinod.singh
	 * Updates Class Subject Mapping
	 * Returns String
	 * 
	 */	
	/*@RequestMapping(value = "/editTeacherSubjectMapping", method = RequestMethod.POST)
	public String editTeacherSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="teacher") String teacher,
			@RequestParam(value="subjects") String []subjects,
			@RequestParam(value="oldSubjects", required = false) String []oldSubjects,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editClassSubjectMapping-POST of AcademicsController");
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
			TeacherSubjectMapping teacherSubjectMapping=new TeacherSubjectMapping();
			Resource resource=new Resource();
			resource.setUserId(teacher);
			teacherSubjectMapping.setResource(resource);
			
			if(insertSubjectList.size()!=0)
				teacherSubjectMapping.setNewSubjectList(insertSubjectList);
			if(deleteSubjectList.size()!=0)
				teacherSubjectMapping.setOldSubjectList(deleteSubjectList);
			teacherSubjectMapping.setUpdatedBy(sessionObject.getUserId());
						
			if(subjectList.size()!=0){
				String updateStatus=backOfficeService.editTeacherSubjectMapping(teacherSubjectMapping);
				model.addAttribute("insertUpdateStatus", updateStatus);
			}
		} catch (CustomException ce){
			logger.error("Exception in method editClassSubjectMapping-POST of AcademicsController", ce);
		}
		return getTeacherSubjectMapping(request, response, model);
	}*/
	

	
	
	/**
	 * @author anup.roy
	 * this method is for student promotion view page
	 */
	
	@RequestMapping(value = "/getStudentPromotion", method = RequestMethod.GET)
	public String getStudentPromotion(	HttpServletRequest request,
										HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createStudentPromotion";
		try {
			logger.info("Inside Method getStudentPromotion-GET of BackOfficeController");
			List<Standard> standardList = academicsService.getStandardsForSetExamMarks();
			model.addAttribute("standardList", standardList);
		} catch (Exception ce){
			logger.error("Exception in method getStudentPromotion-GET of BackOfficeController", ce);
			ce.printStackTrace();
		}
		return strView;
	}
	
	
	/**
	 * @author anup.roy
	 * get students for standard and section
	 */
	@RequestMapping(value = "/getStudentsResultForPromotion", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentsResultForPromotion(@RequestParam("standard") String standard,
										@RequestParam("section") String section) {
		logger.info("Inside Method getStudentsResultForPromotion-GET of AcademicsController");
		String result = "";
		try {
			StudentResult studentResult= new StudentResult();
			studentResult.setStandard(standard);
			studentResult.setSection(section);
			result = backOfficeService.getStudentsResultForPromotion(studentResult);
		} catch (CustomException ce) {
			logger.error("Exception in method getStudentsResultForPromotion-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return (new Gson().toJson(result));
	}
	
	
	/**
	 * @author anup.roy
	 * this method is for update student promotion and TC
	 */	
	@RequestMapping(value = "/updateStudentPromotion", method = RequestMethod.POST)
	public String updateStudentPromotion(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="toStandard", required = false) String toStandard,
			@RequestParam(value="toSection", required = false) String toSection,
			@RequestParam(value="rollNumber") String []rollNumber,
			@RequestParam(value="type") String type,
			@RequestParam(value="standard") String standard,
			@RequestParam(value="section") String section,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {		
		try {
			logger.info("Inside Method updateStudentPromotion-POST of AcademicsController");
			List<StudentResult> studentResultList=new ArrayList<StudentResult>();
			if(null!=rollNumber && rollNumber.length!=0){
				if(type.equalsIgnoreCase("PASSOUT")){
					for(int roll=0;roll<rollNumber.length;roll++){
						StudentResult studentResult=new StudentResult();
						studentResult.setUpdatedBy(sessionObject.getUserId());
						studentResult.setStatus(type);
						studentResult.setStandard(standard);
						studentResult.setSection(section);
						studentResult.setRollNumber(rollNumber[roll]);
						studentResultList.add(studentResult);
					}
				}else if(type.equalsIgnoreCase("PROMOTE")){
					for(int roll=0;roll<rollNumber.length;roll++){
						StudentResult studentResult=new StudentResult();
						studentResult.setUpdatedBy(sessionObject.getUserId());
						studentResult.setStatus(type);
						studentResult.setStandard(toStandard);
						studentResult.setSection(toSection);
						studentResult.setRollNumber(rollNumber[roll]);
						studentResultList.add(studentResult);
					}
				}
			}else{
				model.addAttribute("insertUpdateStatus", "Students Not Found To Promote");
			}
			if(studentResultList.size()!=0){
				String updateStatus=backOfficeService.updateStudentPromotion(studentResultList,type,response);
				model.addAttribute("insertUpdateStatus", updateStatus);
			}
		} catch (CustomException ce){
			logger.error("Exception in method updateStudentPromotion-POST of AcademicsController", ce);
			ce.printStackTrace();
		}
		return getStudentPromotion(request, response, model);
	}
	
	/*@RequestMapping(value = "/updateStudentPromotion", method = RequestMethod.POST)
	public String updateStudentPromotion(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="rollNumber") String []rollNumber,
			@RequestParam(value="type") String type,
			@RequestParam(value="standard") String standard,
			@RequestParam(required = false,value="termFrom") String termFrom,
			@RequestParam(required = false,value="termTo") String termTo,
			@RequestParam(value="section") String section,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {		
		try {
			logger.info("Inside Method updateStudentPromotion-POST of AcademicsController");
			List<StudentResult> studentResultList=new ArrayList<StudentResult>();
			if(null!=rollNumber && rollNumber.length!=0){
				if(type.equalsIgnoreCase("PASSOUT")){
					for(int roll=0;roll<rollNumber.length;roll++){
						StudentResult studentResult=new StudentResult();
						studentResult.setUpdatedBy(sessionObject.getUserId());
						studentResult.setStatus(type);
						studentResult.setRollNumber(rollNumber[roll]);
						studentResult.setCourse(standard);
						studentResult.setSection(section);
						studentResult.setDesc(termFrom);
						studentResultList.add(studentResult);
					}
				}else{
					for(int roll=0;roll<rollNumber.length;roll++){
						StudentResult studentResult=new StudentResult();
						studentResult.setUpdatedBy(sessionObject.getUserId());
						studentResult.setStatus(type);
						studentResult.setRollNumber(rollNumber[roll]);
						studentResult.setCourse(standard);
						studentResult.setSection(section);
						studentResult.setTerm(termTo);
						studentResult.setDesc(termFrom);
						studentResultList.add(studentResult);
					}
				}
			}else{
				model.addAttribute("insertUpdateStatus", "Students Not Found To Promote");
			}		
			if(studentResultList.size()!=0){
				String updateStatus=backOfficeService.updateStudentPromotion(studentResultList,response);
				model.addAttribute("insertUpdateStatus", updateStatus);
			}
		} catch (CustomException ce){
			logger.error("Exception in method updateStudentPromotion-POST of AcademicsController", ce);
		}
		return getStudentPromotion(request, response, model);
	}*/
	
	/* 
	 * View Pending Promotion
	 */
	@RequestMapping(value = "/getViewPendingPromotion", method = RequestMethod.GET)
	public String getViewPendingPromotion(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createViewPendingPromotion";
		try {
			logger.info("Inside Method getStudentPromotion-GET of BackOfficeController");
			
			List<StudentResult> pendingList=backOfficeService.getViewPendingPromotion();
			model.addAttribute("pendingList", pendingList);
			
		} catch (CustomException ce){
			logger.error("Exception in method getViewPendingPromotion-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	
	
	/** @author anup.roy
	 * 07092017
	 * Create TC
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/addTC", method = RequestMethod.POST)
	public String addTC(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			StudentTC studentTC,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
				logger.info("Inside Method addSocialCategory-POST of BackOfficeController");
				if(null!=studentTC && null!=studentTC.getRollNumber()){
					studentTC.setUpdatedBy(sessionObject.getUserId());
					String insertStatus=backOfficeService.addTC(studentTC);
					model.addAttribute("insertUpdateStatus", insertStatus);
		
				/**
				 * used to add activity log for student TC granted
				 * */
				if(insertStatus.equals("TC Granted Successfuly")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("STUDENT DETAILS");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("OFFICE ADMINISTRATION");
					updateLog.setUpdatedFor(studentTC.getRollNumber()+"");
					updateLog.setDescription("A New Student got a TC of Roll number "+studentTC.getRollNumber()+" from Program "+studentTC.getStandard()+"of Batch"+studentTC.getSection());
					commonService.insertIntoActivityLog(updateLog);
				}		
			}
			else{
				logger.info("Student Not Found To Grant TC.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method addSocialCategory-POST of BackOfficeController", ce);
			ce.printStackTrace();
		}
		return getTC(request, response, model);
	}
	
	
	
	/*
	 * @author vinod.singh
	 * Opens page to Search Ex Students
	 * Returns ModelAndView
	 * 
	 */	
	@RequestMapping(value = "/getSearchExStudents", method = RequestMethod.GET)
	public ModelAndView getSearchExStudents(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("backoffice/searchExStudents");
		try {
			logger.info("Inside Method getSearchExStudents-GET of BackOfficeController");
		} catch (Exception e){
			logger.error("Exception in method getSearchExStudents-GET of BackOfficeController", e);
		}
		return mav;
	}
	
	
	/**
	 * @author anup.roy
	 * Search Ex Students
	 * Returns ModelAndView
	 * 
	 */	
	@RequestMapping(value = "/searchExStudents", method = RequestMethod.POST)
	public ModelAndView searchExStudents(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			@RequestParam(value="rollNumber") String rollNumber,
			@RequestParam(value="year") String year,
			@RequestParam(required=false, value="firstName") String firstName,
			@RequestParam(required=false, value="middleName") String middleName,
			@RequestParam(required=false, value="lastName") String lastName
			) {
		Map<String, String> parameters = new HashMap<String, String>();
		List<ExStudents> exStudentsList = null;
		try {
			logger.info("Inside Method searchExStudents-POST of BackOfficeController");
			
			
			if(null!=rollNumber && rollNumber.length()!=0){
				parameters.put("rollNumber", rollNumber);				
			}
			if(null!=year && year.length()!=0){
				parameters.put("year", year);				
			}
			if(null!=firstName && firstName.length()!=0){
				parameters.put("firstName", firstName);				
			}
			if(null!=middleName && middleName.length()!=0){
				parameters.put("middleName", middleName);				
			}
			if(null!=lastName && lastName.length()!=0){
				parameters.put("lastName", lastName);				
			}
			
			exStudentsList = backOfficeService.searchExStudents(parameters);
			model.addAttribute("exStudentsList", exStudentsList);
			
		} catch (CustomException ce){
			ce.printStackTrace();
			logger.error("Exception in method searchExStudents-POST of BackOfficeController", ce);
		}
		return new ModelAndView("backoffice/searchExStudents");
	}
	
	
	@RequestMapping(value = "/exStudentsPagination", method = RequestMethod.GET)
	public ModelAndView exStudentsPagination(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> parameters) {
		ModelAndView mav = null;
		try {
			logger.info("Inside Method exStudentsPagination-GET of BackOfficeController");
			mav = new ModelAndView("backoffice/searchExStudents");
			mav.addObject("parameters", parameters);
			PagedListHolder<ExStudents> pagedListHolder = backOfficeService.exStudentsPagination(request);
			if (pagedListHolder != null) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		}  catch (Exception e){
			logger.error("Exception in method exStudentsPagination-GET of BackOfficeController", e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/downloadStudentAttachments", method = RequestMethod.GET)
	public void downloadStudentAttachments(HttpServletRequest request,HttpServletResponse response, ModelMap model,
											@RequestParam("fileLocation") String filePath,
											@RequestParam("fileName") String fileName) {		
		
		try {
			logger.info("Inside downloadStudentAttachments() of BackOfficeController");
			String returnMessage = fileUploadDownload.downloadFile(response, filePath, fileName+".zip");
			if(returnMessage == null){
				model.addAttribute("ResultError", "File not available");
			}
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in downloadStudentAttachments() method in BackOfficeController:  ",ce);
		}
	}
	
	
	@RequestMapping(value = "/deleteStudentAttachmentFromHardDrive", method = RequestMethod.GET)
	public String deleteStudentAttachmentFromHardDrive(HttpServletRequest request,HttpServletResponse response, ModelMap model,
											@RequestParam("fileLocation") String filePath,
											@RequestParam("fileName") String fileName,
											@RequestParam("fileId") String attachId,
											@RequestParam("roll") String roll) {		
		
		try {
			logger.info("Inside deleteStudentAttachmentFromHardDrive() of BackOfficeController");
			String fullPath = filePath+fileName+".zip";
			File myfile = new File(fullPath);
			int fileDeleteStatus = 0;
			if(myfile.isFile()){
				myfile.delete();
				fileDeleteStatus = backOfficeService.deleteSelectedAttachment(Integer.parseInt(attachId));
			}
			if(fileDeleteStatus != 0){
				model.addAttribute("fileDeleteStatus", "Deleted");				
			}else{
				model.addAttribute("fileDeleteStatus", null);
			}
		} catch (Exception ce) {
			logger.error("Exception in deleteStudentAttachmentFromHardDrive() method in BackOfficeController:  ",ce);
		}
		
		return getStudentDetails(request, response, model, roll);
	}
	
	/*
	 * @author sayani.datta
	 * Ajax Call to fetch the Assigned Events for Personal
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getCalendarEventFromDBForPersonal", method = RequestMethod.GET)
	public @ResponseBody
	String getCalendarEventFromDBForPersonal(@RequestParam("eventType") String eventType,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String events = null;
		try {
			logger.info("Inside Method getCalendarEventFromDBForPersonal-GET of BackOfficeController");
			String userId = sessionObject.getUserId();
			events = backOfficeService.getCalendarEventFromDBForPersonal(eventType,userId);
		} catch (Exception ce){
			logger.error("Exception in method getCalendarEventFromDBForPersonal-GET of BackOfficeController", ce);
		}
		return (new Gson().toJson(events));
	}
	
	@RequestMapping(value = "/downloadStudentDetailsExcel", method = RequestMethod.GET)
	public String downloadStudentDetailsExcel(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		try{
			logger.info("In downloadStudentDetailsExcel() method of BackOfficeController");
			String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelDownloadFolder, studentExcel);
			if(returnMessage == null){
				model.addAttribute("ResultError", "File not available");
			}
		}catch(Exception e){
			logger.error("Exception in downloadStudentDetailsExcel() for download Template Excel IN BackOfficeController", e);
		}
		return getStudentDetails(request, response, model);
	}


	@RequestMapping(value = "/downloadStudentSubjectMappExcel", method = RequestMethod.GET)
	public String downloadStudentSubjectMappExcel(HttpServletRequest request,HttpServletResponse response, 
												ModelMap model,
												@RequestParam("standard") String standard,
												@RequestParam("section") String section) {
		Standard standardDB = null;
		try{
			logger.info("In downloadStudentSubjectMappExcel() method of BackOfficeController");
			//System.out.println("...... "+standard+" ....... "+section);
			Student student = new Student();
			student.setStandard(standard);
			student.setSection(section);
			standardDB = backOfficeService.getStudentRollAgainstStandardAndSection(student);
			if(null != standardDB){	
				boolean status = backOfficeService.writeExcelForStudentSubjectMapping(standardDB, rootPath+excelDownloadFolder, studentSubjectMappingExcel);
				if(status == true){					
					String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelDownloadFolder, studentSubjectMappingExcel);
					if(returnMessage == null){
						model.addAttribute("ResultError", "File not available");
					}
				}
			}						
		}catch(Exception e){
			logger.error("Exception in downloadStudentSubjectMappExcel() for download Template Excel IN BackOfficeController", e);
		}
		return getStudentSubjectMappingList(request, response, model);
	}

	
	@RequestMapping(value = "/uploadStudentSubjectMappExcel", method = RequestMethod.POST)
	public String uploadStudentSubjectMappExcel(@ModelAttribute("uploadFile") UploadFile uploadFile,
										HttpServletRequest request,HttpServletResponse response,
										ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("In uploadStudentSubjectMappExcel() of BackOfficeController");
			String returnMessage = fileUploadDownload.uploadExcel(uploadFile,rootPath+excelUploadfolder,studentSubjectMappingExcel);
			if(returnMessage == null){
				model.addAttribute("uploadErrorMessage", studentSubjectMappingExcel+" upload failed.");
			}else{
				int excelDataInsertStatus = backOfficeService.insertStudentSubjectMappExcelBulkData(rootPath+excelUploadfolder+studentSubjectMappingExcel, sessionObject.getUserId());
				model.addAttribute("excelDataInsertStatus", excelDataInsertStatus);
			}
		}catch(Exception e){
			logger.error("Exception in uploadStudentSubjectMappExcel() to upload Excel IN BackOfficeController", e);
		}
		return getStudentSubjectMappingList(request, response, model);
	}
	
	
	//Leave Module
	
		
		/**
		 * this method get strleavetype from leaveCategory.jsp and call
		 * BackOfficeServiceImpl.java to insert LeaveType, and return to
		 * leaveCategory.jsp for create new LeaveType.
		 * 
		 * @param model
		 * @param academicLeaveCategory
		 * @param strleavetype
		 * @return ModelAndView
		 */

		@RequestMapping(value = "/insertLeave", method = RequestMethod.POST)
		public ModelAndView insertleaveCategory(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("leaveType") String[] strleavetype,
				AcademicLeaveCategory academicLeaveCategory,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("In insertleaveCategory() method of BackOfficeController");
			try {
				for (int leavetype = 0; leavetype < strleavetype.length; leavetype++) {
					if ((strleavetype != null && strleavetype[leavetype] != "")) {
						academicLeaveCategory.setLeaveCategoryName(strleavetype[leavetype].toUpperCase());
						academicLeaveCategory.setLeaveCategoryDesc(strleavetype[leavetype].toUpperCase());
						academicLeaveCategory.setUpdatedBy(sessionObject.getUserId());				
						String insertStatus=backOfficeService.insertLeaveType(academicLeaveCategory);
						model.addAttribute("insertUpdateStatus", insertStatus);
					}
				}
			} catch (Exception ce) {
				logger.error("Exception in insertleaveCategory() method in BackOfficeController:  ",ce);
			}
			return showleaveCategory(request, response, model);
		}

		/**
		 * this method get strleavetypecode from leaveCategory.jsp and call
		 * BackOfficeServiceImpl.java to delete selected LeaveType, and return to
		 * leaveCategory.jsp to show result after deletion.
		 * 
		 * @param model
		 * @param academicLeaveCategory
		 * @param strleavetypecode
		 * @return ModelAndView
		 */

		@RequestMapping(value = "/deleteLeave", method = RequestMethod.POST)
		public ModelAndView deletelLeaveCategory(HttpServletRequest request,
				HttpServletResponse response,ModelMap model,
				@RequestParam("leaveCode") String[] strleavetypecode,
				AcademicLeaveCategory academicLeaveCategory,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("In deletelLeaveCategory() method of BackOfficeController");
			try {
				for (int codenum = 0; codenum < strleavetypecode.length; codenum++) {
					if ((strleavetypecode != null && strleavetypecode[codenum] != "")) {
						int intcode = Integer.parseInt(strleavetypecode[codenum]);
						academicLeaveCategory.setLeaveTypeId(intcode);
						academicLeaveCategory.setUpdatedBy(sessionObject.getUserId());
						String insertStatus = backOfficeService.deleteLeaveType(academicLeaveCategory);
						model.addAttribute("insertUpdateStatus", insertStatus);
						
					}
				}
			} catch (Exception ce) {
				logger.error("Exception in deletelLeaveCategory() method in BackOfficeController:  ",ce);
			}
			return showleaveCategory(request, response, model);
		}

		/**
		 * this method get streditedleavetype from leaveCategory.jsp and call
		 * BackOfficeServiceImpl.java to edit selected LeaveType, and return to
		 * leaveCategory.jsp to show result after edition.
		 * 
		 * @param model
		 * @param academicLeaveCategory
		 * @param streditedleavetype
		 * @return ModelAndView
		 */

//		@RequestMapping(value = "/editLeave", method = RequestMethod.POST)
//		public ModelAndView editleaveCategory(HttpServletRequest request,
//				HttpServletResponse response,
//				@RequestParam("hiddenleaveType") String streditedleavetype,
//				@ModelAttribute("sessionObject") SessionObject sessionObject,ModelMap model) {
//			logger.info("In editleaveCategory() method of BackOfficeController");
//			try {
//				logger.debug("editleaveCategory()In BackOfficeController.java: calling deleteLeaveType() in BackOfficeService.java");
//				String[] arr = streditedleavetype.split("/");
//				for (int rownum = 0; rownum < arr.length; rownum++) {
//					String[] arr1 = arr[rownum].split(",");
//					for (int cfees = 0; cfees < arr1.length; cfees++) {
//						if (arr1[cfees] != "" || arr1[cfees] != "") {
//							int intcode = Integer.parseInt(arr1[0]);
//							AcademicLeaveCategory academicLeaveCategory = new AcademicLeaveCategory();
//							academicLeaveCategory.setLeaveTypeId(intcode);
//							academicLeaveCategory.setLeaveCategoryName(arr1[1]
//									.toUpperCase());
//							academicLeaveCategory.setUpdatedBy(sessionObject.getUserId());
//							String insertStatus = backOfficeService.editLeaveType(academicLeaveCategory);
//							model.addAttribute("insertUpdateStatus", insertStatus);
//							
//						}
//					}
//				}
//			} catch (Exception ce) {
//				logger.error("Exception in deletelLeaveCategory() method in BackOfficeController:  ",ce);
//			}
//			return showleaveCategory(request, response, model);
//		}

		/**
		 * this Ajax call return to leaveCategory.jsp
		 * 
		 * @param request
		 * @param response
		 * @return String
		 */

		@RequestMapping(value = "/getPreviousLeaveType", method = RequestMethod.GET)
		public @ResponseBody
		String getPreviousLeaeves() {
			logger.info("In getPreviousLeaeves() method of BackOfficeController");
			String showLeave = null;
			try {
				logger.debug("getPreviousLeaveType()In BackOfficeController.java: calling getPreviousLeaveType() in BackOfficeService.java");
				showLeave = backOfficeService.getPreviousLeaveType();
			} catch (Exception e) {
				logger.error("getPreviousLeaveType() In BackOfficeController.java: Exception"
						+ e);
			}
			return (new Gson().toJson(showLeave));
		}

		/**
		 * this Ajax call return to leaveCategory.jsp
		 * 
		 * @param request
		 * @param response
		 * @return String
		 */
		@RequestMapping(value = "/getLeaveCategoryTypeNameForValidation", method = RequestMethod.GET)
		public @ResponseBody
		String getLeaveCategory(@RequestParam("leaveTypeName") String leaveTypeName) {
			logger.info("In getLeaveCategory() method of BackOfficeController");
			String leaveTypeNameUpper = leaveTypeName.toUpperCase();
			String matchLeave = null;
			String valid = "NO";
			try {
				matchLeave = backOfficeService.getPreviousLeaveType();
				if(matchLeave != null){
					String[] totalLeaveTypeArr = matchLeave.split("/");
					for (int totalleavetype = 0; totalleavetype < totalLeaveTypeArr.length; totalleavetype++) {

						String[] LeaveTypeArr = totalLeaveTypeArr[totalleavetype]
								.split(",");
						for (int leavetype = 0; leavetype < LeaveTypeArr.length; leavetype++) {
							if (LeaveTypeArr[1].equals(leaveTypeNameUpper)) {
								valid = "YES";
							}
						}
					}
				}
				
			} catch (Exception ce) {
				logger.error("Exception in getLeaveCategory() method in BackOfficeController:  ",ce);
			}
			return (new Gson().toJson(valid));
		}
		
		
		
		@RequestMapping(value = "/showListLeaveStructure", method = RequestMethod.GET)
		public ModelAndView showListLeaveStructure(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			logger.info("In showListLeaveStructure() method of BackOfficeController");
			try {
				List<AcademicLeave> leaveTypeList = null;
				logger.debug("showListLeaveStructure()In BackOfficeController.java: calling fetchLeaveTypeList() in BackOfficeService.java");
				leaveTypeList = backOfficeService.getLeaveTypeList();
				if (leaveTypeList != null) {
					logger.info("In BackOfficeController showListLeaveStructure() method: calling showListLeaveStructurePaging(HttpServletRequest request) in BackOfficeService.java");
					PagedListHolder<AcademicLeave> pagedListHolder = backOfficeService.showListLeaveStructurePaging(request);
					model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", pagedListHolder.getNrOfElements());
					model.addAttribute("pagedListHolder", pagedListHolder);
				}
			} catch (NullPointerException e) {
				logger.error("showListLeaveStructure() In BackOfficeController.java: NullPointerException"
						+ e);
			} catch (Exception e) {
				logger.error("showListLeaveStructure() In BackOfficeController.java: Exception"
						+ e);
			}

			return new ModelAndView("backoffice/showListLeaveStructure");
		}


	@RequestMapping(value = "/showListLeaveStructurePagination", method = RequestMethod.GET)
		public ModelAndView showListLeaveStructurePagination(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mav = null;
			try {
				logger.info("In bookListPagination() method of LibraryController");
				mav = new ModelAndView("backoffice/showListLeaveStructure");
				logger.info("In BackOfficeController showListLeaveStructure() method: calling showListLeaveStructurePaging(HttpServletRequest request) in BackOfficeService.java");
				PagedListHolder<AcademicLeave> pagedListHolder = backOfficeService.showListLeaveStructurePaging(request);
				if (pagedListHolder != null) {
					mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
					mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
					mav.addObject("total", pagedListHolder.getNrOfElements());
					mav.addObject("pagedListHolder", pagedListHolder);
				}
			} catch (Exception e) {
				logger.error("Error in BackOfficeController showListLeaveStructurePaging() method for Exception: ", e);
			}
			return mav;
		}

		/**
		 * this method return to leaveStructure.jsp to leaveCategory.jsp to enter
		 * the leave structure.
		 * 
		 * @param model
		 * @param academicLeaveCategory
		 * @return ModelAndView
		 */

	

	

	/**
	 * this method get strcode from listLeaveStructure.jsp and return to
	 * editLeaveStructure.jsp to edit the selected leave structure.
	 * 
	 * @param model
	 * @param academicLeaveCategory
	 * @param strcode
	 * @return ModelAndView
	 */

	@RequestMapping(value = "/editLeaveStructure", method = RequestMethod.GET)
	public ModelAndView showEditLeaveStructure(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("code") String strcode, AcademicLeave academicLeave,BindingResult bindingResult) {
		logger.info("In showEditLeaveStructure() method of BackOfficeController");
		try {
			logger.debug("showeditLeaveStructure() In BackOfficeController.java: calling insertLeaveStructure() in BackOfficeService.java");
			int strintcode = Integer.parseInt(strcode);
			academicLeave.setLeaveDuration(strintcode);
			List<AcademicLeave> listspecleavestructure = null;
			listspecleavestructure = backOfficeService.listLeave(academicLeave);
			if (listspecleavestructure != null) {
				model.addAttribute("listspecleavestructure",
						listspecleavestructure);
			}
		} catch (Exception e) {
			logger.error("Error in BackOfficeController showEditLeaveStructure() method for Exception: ", e);
		}
		return new ModelAndView("backoffice/editLeaveStructure");
	}

	/**
	 * this method get strcode ,strduration ,strencashment ,strlimit ,strvalid
	 * from editLeaveStructure.jsp and return to updateLeaveStructure.jsp to
	 * update the edited leave structure.
	 * 
	 * @param model
	 * @param academicLeaveCategory
	 * @param strcode
	 * @param strduration
	 * @param strencashment
	 * @param strlimit
	 * @param strvalid
	 * @return ModelAndView
	 */



	@RequestMapping(value = "/updateLeaveStructure", method = RequestMethod.GET)
		public ModelAndView updateLeaveApplication(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("leaveCode") String strcode,
				@RequestParam("leaveDuration") String strduration,
				@RequestParam("leaveEncashment") String strencashment,
				@RequestParam("leaveLimit") String strlimit,
				@RequestParam("leaveValid") String strvalid,
				AcademicLeave academicLeave,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("In updateLeaveApplication() method of BackOfficeController");
			try {
				logger.debug("updateLeaveApplication() In BackOfficeController.java: calling updateLeaveStructure() in BackOfficeService.java");
				//List<AcademicLeave> leaveTypeList = null;
				int strcodeint = Integer.parseInt(strcode);
				int strint = Integer.parseInt(strduration);
				int strintlimit = Integer.parseInt(strlimit);
				boolean strbooleanencashment = Boolean.parseBoolean(strencashment);
				academicLeave.setLeaveTypeId(strcodeint);
				academicLeave.setLeaveDuration(strint);
				academicLeave.setLeaveEncashment(strbooleanencashment);
				academicLeave.setLeaveLimit(strintlimit);
				academicLeave.setLeaveValidUpto(strvalid);
				academicLeave.setUpdatedBy(sessionObject.getUserId());
				String updateStatus = backOfficeService.updateLeaveStructure(academicLeave);
				model.addAttribute("insertUpdateStatus", updateStatus);				
			}catch (Exception e) {
				logger.error("Error in BackOfficeController updateLeaveApplication() method for Exception: ", e);
			}
			return showListLeaveStructure(request,response, model);
		}
	
	@RequestMapping(value = "/uploadStudentDetailsExcel", method = RequestMethod.POST)
	public String uploadStudentDetailsExcel(@ModelAttribute("uploadFile") UploadFile uploadFile,
										HttpServletRequest request,HttpServletResponse response,
										ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject){
	try{
		logger.info("In uploadStudentDetailsExcel() of BackOfficeController");
		String returnMessage = fileUploadDownload.uploadExcel(uploadFile,rootPath+excelUploadfolder,studentExcel);
		if(returnMessage == null){
			model.addAttribute("uploadErrorMessage", studentExcel+" upload failed.");
		}else{
			int excelDataInsertStatus = backOfficeService.insertStudentDetailsExcelBulkData(rootPath+excelUploadfolder+studentExcel, sessionObject.getUserId());
			if(excelDataInsertStatus == 0){
				model.addAttribute("uploadErrorMessage", studentExcel+" upload failed.");
			}else			
			model.addAttribute("excelDataInsertStatus", studentExcel+" upload Successful.");
		}
	}catch(Exception e){
		model.addAttribute("uploadErrorMessage", studentExcel+" upload failed.");
		logger.error("Exception in uploadStudentDetailsExcel() to upload Excel IN BackOfficeController", e);
		}
		return getStudentDetails(request, response, model);
	}

	

	@RequestMapping(value = "/noticeBoard", method = RequestMethod.GET)
	public String noticeBoard(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try{
			List<NoticeBoard>  noticeList = commonService.getNoticeList();
			model.addAttribute("noticeList", noticeList);
		}catch(Exception e){
			logger.error("Exception in createNotice() method in BackOfficeController: ", e);
		}
		return "backoffice/createNotice";
	}

	@RequestMapping(value = "/createNotice", method = RequestMethod.POST)
	public String createNotice(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			NoticeBoard noticeBoard,
			@RequestParam(required = false, value = "submit") String submit,
			@RequestParam(required = false, value = "update")   String update,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try{	
			noticeBoard.setUpdatedBy(sessionObject.getUserId());
			if(submit!=null){
				String insertUpdateStatus = backOfficeService.createNotice(noticeBoard);
				model.addAttribute("insertUpdateStatus", insertUpdateStatus);
				//model.remove("noticeBoard");
			}else if(update!=null){
				String insertUpdateStatus = backOfficeService.updateNotice(noticeBoard);
				model.addAttribute("insertUpdateStatus", insertUpdateStatus);
				model.remove("noticeBoard");
			}
		}catch(Exception e){
			logger.error("Exception in createNotice() method in BackOfficeController: ", e);
		}
		return noticeBoard(request, response, model);
	}


	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public String noticeList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try{	
			logger.info("noticeList() method in BackOfficeController");
			List<NoticeBoard>  noticelist = commonService.getNoticeList();
				if (noticelist != null && noticelist.size() != 0) {
					logger.info("In BackOfficeController noticeList() method: calling getNoticeListPaging(HttpServletRequest request) in CommonService.java");
					PagedListHolder<NoticeBoard> pagedListHolder = commonService.getNoticeListPaging(request);
					model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", pagedListHolder.getNrOfElements());
					model.addAttribute("pagedListHolder", pagedListHolder);
					}
		}catch(Exception e){
			logger.error("Exception in noticeList() method in BackOfficeController: ", e);
		}
		return "backoffice/noticeList";
	}

	@RequestMapping(value = "/noticeListPagination", method = RequestMethod.GET)
	public ModelAndView noticeListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In bookListPagination() method of LibraryController");
			mav = new ModelAndView("backoffice/noticeList");
			logger.info("In BackOfficeController noticeListPagination() method: calling getNoticeListPaging(HttpServletRequest request) in CommonService.java");
			PagedListHolder<NoticeBoard> pagedListHolder = commonService.getNoticeListPaging(request);
			if (pagedListHolder != null) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in BackOfficeController getNoticeListPaging() method for Exception: ", e);
		}
		return mav;
	}


	@RequestMapping(value = "/viewAndDeleteNotice", method = RequestMethod.POST)
	public String viewAndDeleteNotice(HttpServletRequest request, HttpServletResponse response, ModelMap model,
								@RequestParam("saveId") String saveId,
								@RequestParam("function") String function,
								@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try{	
			logger.info("viewAndDeleteNotice() method in BackOfficeController");
			String insertUpdateStatus;
			NoticeBoard noticeBoard=new NoticeBoard();
			noticeBoard.setNoticeCode(saveId);
			noticeBoard.setUpdatedBy(sessionObject.getUserId());
			if(function.equalsIgnoreCase("DELETE")){
				insertUpdateStatus = backOfficeService.deleteNotice(noticeBoard);
				model.addAttribute("insertUpdateStatus", insertUpdateStatus);
			}else if(function.equalsIgnoreCase("UPDATE")){
				noticeBoard = backOfficeService.viewNotice(noticeBoard);
				model.addAttribute("noticeBoard", noticeBoard);
			}
		}catch(Exception e){
			logger.error("Exception in viewAndDeleteNotice() method in BackOfficeController: ", e);
		}
		return noticeBoard(request, response, model);
	}

	@RequestMapping(value = "/getEmployeeCompleteLeaveDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getEmployeeCompleteLeaveDetails(@RequestParam("jobTypeName") String jobTypeName,
										   @RequestParam("employeeType") String employeeType,
										   @RequestParam("academicYear") String academicYear) {
		logger.info("Inside Method getStudentsAndMarks-GET of AcademicsController");
		String result = "";
		try {
			//System.out.println(academicYear+":"+employeeType+"|"+jobTypeName);
			AcademicYear ay = new AcademicYear();
			ay.setAcademicYearName(academicYear);
			
			EmployeeType et = new EmployeeType();
			et.setEmployeeTypeCode(employeeType);
			
			JobType jt = new JobType();
			jt.setJobTypeName(jobTypeName);
			
			AcademicLeave academicLeave=new AcademicLeave();
			academicLeave.setAcademicYear(ay);
			academicLeave.setEmployeeType(et);
			academicLeave.setJobType(jt);
				
			List<AcademicLeave> academicLeaveList = backOfficeService.getEmployeeCompleteLeaveDetails(academicLeave);
			for(AcademicLeave al : academicLeaveList){
				result=result+al.getAcademicLeaveType().getLeaveCategoryName()+"*"+			
				al.getLeaveDuration()+"*"+
				al.getLeaveLimit()+"*"+
				al.getLeaveEncashment()+"*"+
				al.isLeaveCarryForward()+"#";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@RequestMapping(value = "/listUpdatedStudentSubjectMapping", method = RequestMethod.GET)
	public String listUpdatedStudentSubjectMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="standard") String standard,
			@RequestParam(value="section") String section) {
		String strView = "backoffice/listUpdatedStudentSubjectMapping";
		try {
			logger.info("Inside Method listUpdatedStudentSubjectMapping-GET of BackOfficeController");
			
			Student student=new Student();
			student.setStandard(standard);
			student.setSection(section);
			model.addAttribute("student", student);
			List<StudentSubjectMapping> studentSubjectMappingList=backOfficeService.listUpdatedStudentSubjectMapping(student);
			model.addAttribute("studentSubjectMappingList", studentSubjectMappingList);
			
		} catch (CustomException ce){
			logger.error("Exception in method listUpdatedStudentSubjectMapping-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//singh.backoffice
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/getSocialCategory", method = RequestMethod.GET)
	public String getSocialCategory(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createSocialCategory";
		try {
			logger.info("Inside Method getSocialCategory-GET of BackOfficeController");
			List<SocialCategory> socialCategoryList = backOfficeService.getSocialCategoryList();
			model.addAttribute("socialCategoryList", socialCategoryList);
		} catch (CustomException ce){
			logger.error("Exception in method getSocialCategory-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	
	@RequestMapping(value = "/addSocialCategory", method = RequestMethod.POST)
	public String addSocialCategory(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("socialCategoryName") String socialCategoryName,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method addSocialCategory-POST of BackOfficeController");
			if(null!=socialCategoryName && socialCategoryName.length()!=0){
				SocialCategory socialCategory=new SocialCategory();
				socialCategory.setSocialCategoryName(socialCategoryName.trim().toUpperCase());
				socialCategory.setSocialCategoryCode(socialCategoryName.trim().toUpperCase());
				socialCategory.setDesc(socialCategoryName);
				socialCategory.setUpdatedBy(sessionObject.getUserId());
				
				String insertStatus=backOfficeService.addSocialCategory(socialCategory);
				model.addAttribute("insertUpdateStatus", insertStatus);
			}else{
				logger.info("Invalid Social Category Name.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method addSocialCategory-POST of BackOfficeController", ce);
		}
		return getSocialCategory(request, response, model);
	}
	

	@RequestMapping(value = "/editSocialCategory", method = RequestMethod.POST)
	public String editSocialCategory(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			
			logger.info("Inside Method editSocialCategory-POST of BackOfficeController");
			
			SocialCategory socialCategory=new SocialCategory();
			String saveId=request.getParameter("saveId");
			/*added by ranita.sur on 14092017*/
			String socialCategoryName=request.getParameter("getCategoryType");
			socialCategory.setDesc(socialCategoryName.trim());
			socialCategory.setSocialCategoryName(socialCategoryName.trim().toUpperCase());
			socialCategory.setSocialCategoryCode(request.getParameter("oldCategoryCode"+saveId));
			socialCategory.setUpdatedBy(sessionObject.getUserId());	
		
			String updateStatus=backOfficeService.editSocialCategory(socialCategory);
			model.addAttribute("insertUpdateStatus", updateStatus);
			/**Added by @author Saif.Ali
			 * Date-13-03-2018*/
			if(updateStatus.equalsIgnoreCase("Update Successful")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT SOCIAL CATEGORY");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor(request.getParameter("oldCategoryCode"+saveId));
				updateLog.setDescription("Social Category :: " + request.getParameter("oldCategoryCode"+saveId) + " updated to Category :: " + socialCategoryName);
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
		} catch (CustomException ce){
			logger.error("Exception in method editSocialCategory-POST of BackOfficeController", ce);
		}
		return getSocialCategory(request, response, model);
	}
	
	//*******************************************************Social Category Ends	
	
	
	
	
	
	
	
	
	
	/**
	 * this method is used to add vendor details   
	 * 
	 * @param model
	 * @param vendor
	 * @param sessionObject
	 * @return String
	 */
	/*@RequestMapping(value = "/submitAddVendor", method = RequestMethod.POST)
	public String submitAddVendor(ModelMap model, Vendor vendor,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In submitVendor method of CommonController.java");
			vendor.setUpdatedBy(sessionObject.getUserId());
			String insertUpdateStatus = backOfficeService.addVendor(vendor);
			model.addAttribute("insertUpdateStatus", insertUpdateStatus);
		} catch (Exception e) {
			logger.error("submitAddVendor() In CommonController.java: Exception", e);
		}
		return addVendor(model);
	}*/
	

	/*@RequestMapping(value = "/updateVendorDetails", method = RequestMethod.POST)
	public String updateVendorDetails(HttpServletRequest request,ModelMap model,
										@ModelAttribute("sessionObject") SessionObject sessionObject,
										@RequestParam(required = false, value = "function") String function) {
		try {
			logger.info("In updateVendorDetails method of CommonController.java");
			
			Vendor vendor = new Vendor();
			String saveId=request.getParameter("saveId");
			vendor.setUpdatedBy(sessionObject.getUserId());
			vendor.setVendorCode(request.getParameter("oldVendorCode"+saveId));
			vendor.setVendorContactNo1((request.getParameter("newVendorContact1"+saveId)).trim());
			vendor.setVendorContactNo2((request.getParameter("newVendorContact2"+saveId)).trim());
			vendor.setVendorName(request.getParameter("newVendorName"+saveId).trim().toUpperCase());
			vendor.setAddress((request.getParameter("newVendorAddress"+saveId)).trim());
			
			
			if(function.equalsIgnoreCase("UPDATE")){
				String insertUpdateStatus = backOfficeService.updateVendorDetails(vendor);
				model.addAttribute("insertUpdateStatus", insertUpdateStatus);
			}else if(function.equalsIgnoreCase("DELETE")){
				
				String insertUpdateStatus = backOfficeService.deleteVendorDetails(vendor);
				model.addAttribute("insertUpdateStatus", insertUpdateStatus);
				
			}
		}catch (Exception e) {
			logger.error("updateVendorDetails() In CommonController.java: Exception",e);
		}
		return addVendor(model);
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//anup.backoffice
	
	
	/**
	 * this method return to leaveCategory.jsp
	 * 
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/leaveCategory", method = RequestMethod.GET)
	public ModelAndView showleaveCategory(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView strView = null;
		try {
			logger.info("Inside Method showLeaveCategory-GET of BackofficeController");
			strView = new ModelAndView("backoffice/leaveCategory");
			List<Leave> leaveList = backOfficeService.getLeave();
			model.addAttribute("leaveList", leaveList);
		} catch (CustomException ce){
			logger.error("Exception in method showLeaveCategory-GET of BackofficeController", ce);
		}
		return strView;
	}

	/**
	 * @author anup.roy
	 * */
	
	@RequestMapping(value = "/editLeave", method = RequestMethod.POST)
	public ModelAndView editLeave(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editLeave-POST of HostelController");
			
			Leave leave = new Leave();
			String saveId=request.getParameter("saveId");
			String leaveName = (request.getParameter("getLeaveType"));
			//System.out.println(saveId);
			 /* added by ranita.sur on 14092017 for showing pop up */
			leave.setLeaveType(leaveName.trim().toUpperCase());
			leave.setLeaveCode(request.getParameter("oldLeaveCode"+saveId));
			leave.setUpdatedBy(sessionObject.getUserId());
			String updateStatus = backOfficeService.editLeave(leave);
			 /* added by ranita.sur on 14092017 for showing pop up */
			model.addAttribute("status", updateStatus);
			/**Added by @author Saif.Ali
			 * Date- 19/03/2018
			 * Used to insert the information into the activity_log table*/
			String oldLeaveType = request.getParameter("leaveName"+saveId);	// old Leave Type
		
			if(updateStatus.equalsIgnoreCase("Update Successful")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT LEAVE CATEGORY");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor(oldLeaveType);
				String description = "";
				if(!(oldLeaveType.equalsIgnoreCase(leaveName)))
				{
					description=description + ("Leave Category :: " + oldLeaveType + " updated to new Leave Category :: " + leaveName + " ; ");
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
		} catch (CustomException ce){
			logger.error("Exception in method editLeave-POST of BackOfficeController", ce);
		}
		return showleaveCategory(request, response, model);
	}

	@RequestMapping(value = "/leaveStructure", method = RequestMethod.GET)
	public ModelAndView showLeaveStructure(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("In showLeaveStructure() method of BackOfficeController");
		try {
			logger.debug("showLeaveStructure()In BackOfficeController.java: calling getAcademicYear() in BackOfficeService.java");
			List<AcademicYear> AcademicYear = backOfficeService.getAcademicYearList();
			List<AcademicLeaveCategory> leavetypes = backOfficeService.getLeavetypes();
			List<ResourceType> resourceTypes= backOfficeService.getResourceTypes();
			List<EmployeeType> employeeTypeList = erpService.getAllEmployeeType();
			List<JobType> jobTypeList = erpService.getAllJobType();
			if (AcademicYear != null) {
				model.addAttribute("AcademicYear", AcademicYear);
			}
			if (leavetypes != null) {
				model.addAttribute("leavetypes", leavetypes);
			}
			if (AcademicYear != null) {
				model.addAttribute("resourceTypes", resourceTypes);
			}
			if (leavetypes != null) {
				model.addAttribute("jobTypeList", jobTypeList);
			}	
			if (AcademicYear != null) {
				model.addAttribute("employeeTypeList", employeeTypeList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("backoffice/leaveStructure");
	}
	
	
	/**
	 * this method get stracademic ,strleavetype ,strduration ,strencashment
	 * ,strlimit ,strvalid and return to listLeaveStructure.jsp to
	 * listLeaveStructure.jsp to show the list of the leave structure.
	 * 
	 * @param model
	 * @param academicLeaveCategory
	 * @param stracademic
	 * @param strleavetype
	 * @param strduration
	 * @param strencashment
	 * @param strlimit
	 * @param strvalid
	 * @return ModelAndView
	 */

	@RequestMapping(value = "/listLeaveStructure", method = RequestMethod.POST)
	public ModelAndView showlistLeaveStructure(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("leaveType") String[] strleavetype,
			@RequestParam("leaveDuration") String[] strDuration,
			@RequestParam("leaveEncashment") String[] strEncashment,
			//@RequestParam("leaveLimit") String[] strLimit,
			@RequestParam("leaveCarryForward") String[] strLeaveCarryForward,
		//	AcademicYear academicYear, 
			AcademicLeave academicLeave,				
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		System.out.println("leavetype::"+strleavetype+"\t duration::"+strDuration+"\tlimit::");
		logger.info("In showlistLeaveStructure() method of BackOfficeController");
		try {				
			for (int i = 0; i < (((strleavetype.length))); i++) {
				AcademicLeaveCategory academicLeaveCategory = new AcademicLeaveCategory();
				int strint = Integer.parseInt(strDuration[i]);
				//int strintlimit = Integer.parseInt(strLimit[i]);					
				boolean strbooleanencashment = Boolean.parseBoolean(strEncashment[i]);
				boolean leaveCarryForward = Boolean.parseBoolean(strLeaveCarryForward[i]);
				academicLeaveCategory.setLeaveCategoryName(strleavetype[i]);
				academicLeave.setAcademicLeaveType(academicLeaveCategory);
				academicLeave.setLeaveDuration(strint);
				academicLeave.setLeaveEncashment(strbooleanencashment);
				//academicLeave.setLeaveLimit(strintlimit);					
				academicLeave.setUpdatedBy(sessionObject.getUserId());
				academicLeave.setLeaveCarryForward(leaveCarryForward);
				String submitResponse = backOfficeService.insertLeaveStructure(academicLeave);
				System.out.println("from ctrl submitResponse"+submitResponse);
				model.addAttribute("submitResponse", submitResponse);
				/**Added by @author Saif.Ali
				 * Date- 20/03/2018
				 * Used to insert the information into the activity_log table*/
				
				if(submitResponse.equalsIgnoreCase("Success")){
					String oldLeaveType = request.getParameter("oldLeaveType");
					int oldLeaveDuration = Integer.parseInt(request.getParameter("oldLeaveDuration"));
					boolean oldLeaveEncashment = Boolean.parseBoolean(request.getParameter("oldLeaveEncashment"));
					boolean oldLeaveCarryForward = Boolean.parseBoolean(request.getParameter("oldLeaveCarryForward"));
					
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("EDIT LEAVE STRUCTURE DETAILS");
					updateLog.setInsertUpdate("UPDATE");
					updateLog.setModule("OFFICE ADMINISTRATION");
					updateLog.setUpdatedFor(oldLeaveType);
					String description = "";
					if(oldLeaveDuration != (strint))
					{
						description=description + ("Duration :: " + oldLeaveDuration + " updated to new Duration :: " + strint + " ; ");
					}
					if(strbooleanencashment!= oldLeaveEncashment)
					{
						if (strbooleanencashment == true)
						{
							description=description +  ("Encashment :: Not Allowed" + " updated to :: Allowed"+ " ; ");
						}
						else
						{
							description=description +  ("Encashment :: Allowed" + " updated to :: Not Allowed"+ " ; ");
						}
						
					}
					if(leaveCarryForward!= oldLeaveCarryForward)
					{
						if (leaveCarryForward == true)
						{
							description=description +  ("Carry Forward :: No" + " updated to :: Yes"+ " ; ");
						}
						else
						{
							description=description +  ("Carry Forward :: Yes" + " updated to :: No"+ " ; ");
						}
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
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController showlistLeaveStructure() method for Exception: ", e);
		}
		return showLeaveStructure(request,response, model);
	}
	
	
	@RequestMapping(value = "/getFees", method = RequestMethod.GET)
	public String getFees(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createFees";
		try {
			logger.info("Inside Method getFees-GET of BackOfficeController");
			List<Fees> feesList=backOfficeService.getFeesList();
			model.addAttribute("feesList", feesList);
		} catch (CustomException ce){
			logger.error("Exception in method getFees-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/editFees", method = RequestMethod.POST)
	public String editFees(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editFees-POST of HostelController");
			
			Fees fees = new Fees();
			String saveId=request.getParameter("saveId");
			//System.out.println(saveId);
			fees.setFeesName((request.getParameter("newFeesName"+saveId)).trim().toUpperCase());
			fees.setFeesCode(request.getParameter("oldFeesNames"+saveId));
			fees.setUpdatedBy(sessionObject.getUserId());
			String updateStatus = backOfficeService.editFees(fees);
			model.addAttribute("insertUpdateStatus", updateStatus);
		} catch (CustomException ce){
			logger.error("Exception in method editFees-POST of BackOfficeController", ce);
		}
		return getFees(request, response, model);
	}
	
	@RequestMapping(value = "/getFeesTemplate", method = RequestMethod.GET)
	public String getFeesTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createFeesTemplate";
		try {
			logger.info("Inside Method getFeesTemplate-GET of BackOfficeController");
			List<SocialCategory> socialCategoryList = backOfficeService.getSocialCategoryList();
			model.addAttribute("socialCategoryList", socialCategoryList);
			
			List<Fees> feesList = backOfficeService.getFeesList();
			model.addAttribute("feesList", feesList);
			
			List<Standard> standardList = commonService.getStandards();
			model.addAttribute("standardList", standardList);
			
			List<FeesTemplate> feesTemplateList = backOfficeService.getFeesTemplateList();
			model.addAttribute("feesTemplateList", feesTemplateList);
			
		} catch (CustomException ce){
			logger.error("Exception in method getFeesTemplate-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/addFeesTemplate", method = RequestMethod.POST)
	public ModelAndView addFeesTemplate(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										FeesTemplate feesTemplate,
										@RequestParam(value="socialCategoryNames") String []socialCategoryNames,
										@RequestParam(value="feesNames") String []feesNames,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method addFeesTemplate-POST of BackOfficeController");
			if(null!=feesTemplate && null!=feesTemplate.getTemplateName() && feesTemplate.getTemplateName().trim().length()!=0){
				feesTemplate.setDesc(feesTemplate.getTemplateName().trim());
				feesTemplate.setTemplateName(feesTemplate.getDesc().toUpperCase());
				feesTemplate.setTemplateCode(feesTemplate.getDesc().toUpperCase());
				List<Fees> feesList = new ArrayList<Fees>();
				for(int i=0;i<feesNames.length;i++){
					Fees fees = new Fees();
					fees.setFeesCode(feesNames[i]);
					List<SocialCategory> socialCategoryList=new ArrayList<SocialCategory>();
					for(int j = 0; j<socialCategoryNames.length; j++){						
						SocialCategory socialCategory = new SocialCategory();
						socialCategory.setSocialCategoryCode(socialCategoryNames[j]);
						socialCategory.setAmount(Double.parseDouble(request.getParameter(feesNames[i]+socialCategoryNames[j])));
						socialCategoryList.add(socialCategory);
					}
					fees.setSocialCategoryList(socialCategoryList);
					feesList.add(fees);
				}
				feesTemplate.setFeesList(feesList);
				feesTemplate.setUpdatedBy(sessionObject.getUserId());
				
				String insertStatus = backOfficeService.addFeesTemplate(feesTemplate);
				model.addAttribute("insertUpdateStatus", insertStatus);
			}else{
				logger.info("Invalid Fees Template Name.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method addFeesTemplate-POST of BackOfficeController", ce);
		}
		return getFeesTemplateList(request, response, model);
	}
	
	
	@RequestMapping(value = "/getFeesTemplateList", method = RequestMethod.GET)
	public ModelAndView getFeesTemplateList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("backoffice/listFeesTemplate");
		try {
			logger.info("Inside Method getFeesTemplateList-GET of BackOfficeController");
			
			List<FeesTemplate> feesTemplateList = backOfficeService.getFeesTemplateList();
			mav.addObject("feesTemplateList", feesTemplateList);
			
		} catch (CustomException ce){
			logger.error("Exception in method getFeesTemplateList-GET of BackOfficeController", ce);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/getFeesTemplateDetails", method = RequestMethod.POST)
	public String getFeesTemplateDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "templateCode") String templateCode) {
		String strView = "backoffice/editFeesTemplate";
		try {
			logger.info("Inside Method getFeesTemplateDetails-POST of BackOfficeController");
			
			List<Standard> standardList = commonService.getStandards();
			model.addAttribute("standardList", standardList);
			
			List<FeesTemplate> feesTemplateList = backOfficeService.getFeesTemplateList();
			model.addAttribute("feesTemplateList", feesTemplateList);
			
			FeesTemplate feesTemplateDetails = backOfficeService.getFeesTemplateDetails(templateCode);
			model.addAttribute("feesTemplateDetails", feesTemplateDetails);
			
		} catch (CustomException ce){
			logger.error("Exception in method getFeesTemplateDetails-POST of BackOfficeController", ce);
		}
		return strView;
	}
	
	/**
	 * @author anup.roy
	 * This method is for create a student
	 * */
	
	@RequestMapping(value = "/getStudentDetails", method = RequestMethod.GET)
	public String getStudentDetails(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createStudent";
		try {
			logger.info("Inside Method getStudentDetails-GET of BackOfficeController");
			
			List<SocialCategory> socialCategoryList = backOfficeService.getSocialCategoryList();
			model.addAttribute("socialCategoryList", socialCategoryList);
			//not necessary for time being
			/*List<Standard> standardList = commonService.getStandards();
			model.addAttribute("standardList", standardList);*/
			
			/*List<Candidate> candidateList = backOfficeService.getFeesPaidCandidate();
			model.addAttribute("candidateList", candidateList);*/
			
			/*List<Hostel> hostelList = hostelService.getHostel();
			model.addAttribute("hostelList", hostelList);*/
			
			List<House> houseList = hostelService.getAllHouseList();
			model.addAttribute("houseList", houseList);
			
			List<ResidentType> residentTypeList = backOfficeService.getAllResidentTypeList();
			model.addAttribute("residentTypeList", residentTypeList);
			
			List<Country> countryList = commonService.getCountryList();
			model.addAttribute("countryList", countryList);
			
			List<State> stateList = commonService.getAllStatesInIndia("IND");
			model.addAttribute("stateList", stateList);
			
			List<Scholarship> scholarshipList = backOfficeService.getScholarshipList();
			model.addAttribute("scholarshipList", scholarshipList);
			
			//List<Course>courseList = backOfficeService.getCourseListForCreateStudent();
			List<Course>courseList = academicsService.getCourseList();
			model.addAttribute("courseList", courseList);
			//for add
			AcademicYear currentAcademicYear = commonService.getCurrentAcademicYear();
			model.addAttribute("currentAcademicYear", currentAcademicYear);
			
			//Added By Naimisha 18072017
			
			RepositoryStructure repositoryStructure =  administratorService.getRespositoryStructure();
			model.addAttribute("repository", repositoryStructure);
			
			//Naimisha Ends 18082017
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/jsp/backoffice/createStudent.jsp");
			File file = new File(realPath);
			if(!file.exists()){
				strView = "common/pageNotFound";
				model.addAttribute("message", "Student Page. Work In Progress.<br/>By the time you grab a cup of coffee, we will be back");
			}
		} catch (CustomException ce){
			logger.error("Exception in method getStudentDetails-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	
	
	
	
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public ModelAndView addStudent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Student student,
			@ModelAttribute("sessionObject") SessionObject sessionObject
			) {
		try {
			logger.info("Inside Method addStudent-POST of BackOfficeController");
			if(null != student){
				/***new code added for save this edited file into external repository**/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				AcademicYear academicYear = commonService.getCurrentAcademicYear();
				
				File directory = new File(repository);
				boolean isExists = directory.exists();
				String insertStatus = null;
				if(isExists == true){
					Attachment attachment = new Attachment();
					attachment.setStorageRootPath(repository);
					attachment.setFolderName(studentFolderPath);
					if(null != student.getResource() && null != student.getResource().getUploadFile()){
						student.getResource().getUploadFile().setAttachment(attachment);
					}
					student.setUpdatedBy(sessionObject.getUserId());
					student.getAddress().setUpdatedBy(sessionObject.getUserId());
					String mobile = student.getResource().getMobile();
					mobile = mobile.substring(5, 16);
					mobile = mobile.replace("-", "");
					Resource r = student.getResource();
					r.setMobile(mobile);
					student.setResource(r);
					//PRAD MAY 30 2018
					insertStatus = backOfficeService.addStudent(student);
					model.addAttribute("insertUpdateStatus", insertStatus);
					if(insertStatus.equals("success")){
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("STUDENT DETAILS");
						updateLog.setInsertUpdate("INSERT");
						updateLog.setModule("OFFICE ADMINISTRATION");
						updateLog.setUpdatedFor(student.getResourceUserId()+"");
						updateLog.setDescription("A New Student Was Inserted With User Id "+student.getResourceUserId()+" In Standard "+student.getCourseId()+" (NA).");
						commonService.insertIntoActivityLog(updateLog);
					}
					
					//PRAD MAY 30 2018
					// If Insert Successful, and WEBIQ is available, then call the API
					if(insertStatus.equals("success") && isWebIQAvailable.equalsIgnoreCase("true")){
						
						Student stuData= backOfficeService.getAdmissionAndDateOfBirthDate(); //added by Saif
						
						//PRAD JUNE 7 2018
						String standardName = backOfficeService.getStandardNameforCourse(student.getCourseId());

						JSONObject jsonObj = new JSONObject();
						jsonObj.put("username",portalUserName);
						jsonObj.put("password",portalPassWord);
						jsonObj.put("standard",standardName);
						jsonObj.put("courseCode",student.getCourseId());
						
						jsonObj.put("admissionDrive" , "ADIXIXL-D1");
						jsonObj.put("formId", "NA");
						jsonObj.put("academicsSession", academicYear.getAcademicYearName());
						
						jsonObj.put("rollNumber", student.getResourceUserId());
						jsonObj.put("firstName", student.getResource().getFirstName());
						jsonObj.put("middleName", student.getResource().getMiddleName());
						jsonObj.put("lastName", student.getResource().getLastName());
						jsonObj.put("contactNumber", student.getResource().getMobile());
						jsonObj.put("dateOfBirth",student.getResource().getDateOfBirth());	//stuData.getStudentCode()
						jsonObj.put("admissisonDate",student.getDateOfAdmission());	// stuData.getDateOfAdmission()
						jsonObj.put("gender", student.getResource().getGender());
						jsonObj.put("bloodGroup", student.getResource().getBloodGroup());
						jsonObj.put("category", student.getResource().getCategory());
						jsonObj.put("religion", student.getResource().getReligion());
						jsonObj.put("motherTongue", student.getResource().getMotherTongue());
						jsonObj.put("aadharNumber", student.getResource().getAadharCardNo());
						jsonObj.put("nationality", student.getResource().getNationality());
						jsonObj.put("childId", student.getResource().getChildId());
						
						//PRAD JUNE 7 2018
						//Get the House Name from the House Code
						String houseName = backOfficeService.getHouseName(student.getHouse());
					
						//jsonObj.put("house", student.getHouse());
						jsonObj.put("house", houseName);
						jsonObj.put("stateOfDomicile", student.getStateOfDomicile());
						jsonObj.put("scholarship", student.getScholarship());
						jsonObj.put("bankName", student.getResource().getBankName());
						jsonObj.put("branch", student.getResource().getBankBranch());
						jsonObj.put("accountNumber", student.getResource().getAccountNumber());
						jsonObj.put("medicalStatus", student.getResource().getMedicalStatus());
						jsonObj.put("email", student.getResource().getEmailId());
						
						jsonObj.put("fatherFirstName", student.getResource().getFatherFirstName());
						jsonObj.put("fatherMiddleName", student.getResource().getFatherMiddleName());
						jsonObj.put("fatherLastName", student.getResource().getFatherLastName());
						jsonObj.put("fatherInDefence", student.getResource().getFatherInDefence());
						jsonObj.put("fatherServiceStatus", student.getResource().getFatherServiceStatus());
						jsonObj.put("fatherDefenceCategory", student.getResource().getFatherDefenceCategory());
						jsonObj.put("fatherRank", student.getResource().getFatherRank());
						jsonObj.put("fatherMobile", student.getResource().getFatherMobile());
						jsonObj.put("fatherEmail", student.getResource().getFatherEmail());
					
						jsonObj.put("motherFirstName", student.getResource().getMotherFirstName());
						jsonObj.put("motherMiddleName", student.getResource().getMotherMiddleName());
						jsonObj.put("motherLastName", student.getResource().getMotherLastName());
						jsonObj.put("motherMobile", student.getResource().getMotherMobile());
						jsonObj.put("motherEmail", student.getResource().getMotherEmail());
						
						jsonObj.put("guardianFirstName", student.getGuardianFirstName());
						jsonObj.put("guardianMiddleName", student.getGuardianMiddleName());
						jsonObj.put("guardianLastName", student.getGuardianLastName());
						jsonObj.put("guardianMobile", student.getGuardianMobile());
						jsonObj.put("guardianEmail", student.getGuardianEmail());
						
						jsonObj.put("fatherIncome", student.getFatherIncome());
						jsonObj.put("motherIncome", student.getMotherIncome());
						jsonObj.put("studentIncome", student.getStudentIncome());
						jsonObj.put("familyIncome", student.getFamilyIncome());
						
						//adding the address properties into JSONArray
						JSONArray addressDetails = new JSONArray();
						JSONObject jsonObjAddress = new JSONObject();
						jsonObjAddress.put("presentAddressLine", student.getAddress().getPresentAddressLine()); 
						jsonObjAddress.put("presentAddressLandmark", student.getAddress().getPermanentAddressLandmark()); 
						jsonObjAddress.put("presentAddressCityVillage", student.getAddress().getPresentAddressCityVillage());
						jsonObjAddress.put("presentAddressPinCode", student.getAddress().getPresentAddressPinCode());
						jsonObjAddress.put("presentAddressDistrict", student.getAddress().getPresentAddressDistrict());
						jsonObjAddress.put("presentAddressState", student.getAddress().getPresentAddressState());
						jsonObjAddress.put("presentAddressCountry", student.getAddress().getPermanentAddressCountry());
						jsonObjAddress.put("presentAddressPostOffice", student.getAddress().getPermanentAddressPostOffice());
						jsonObjAddress.put("presentAddressPoliceStation", student.getAddress().getPermanentAddressPoliceStation());
						
						jsonObjAddress.put("permanentAddressLine", student.getAddress().getPermanentAddressLine()); 
						jsonObjAddress.put("permanentAddressLandmark", student.getAddress().getPermanentAddressLandmark()); 
						jsonObjAddress.put("permanentAddressCityVillage", student.getAddress().getPermanentAddressCityVillage());
						jsonObjAddress.put("permanentAddressPinCode", student.getAddress().getPermanentAddressPinCode());
						jsonObjAddress.put("permanentAddressDistrict", student.getAddress().getPermanentAddressDistrict());
						jsonObjAddress.put("permanentAddressState", student.getAddress().getPermanentAddressState());
						jsonObjAddress.put("permanentAddressCountry", student.getAddress().getPermanentAddressCountry());
						jsonObjAddress.put("permanentAddressPostOffice", student.getAddress().getPermanentAddressPostOffice());
						jsonObjAddress.put("permanentAddressPoliceStation", student.getAddress().getPermanentAddressPoliceStation());
						
						jsonObjAddress.put("guardianAddressLine", student.getAddress().getGuardianAddressLine()); 
						jsonObjAddress.put("guardianAddressLandmark", student.getAddress().getGuardianAddressLandmark()); 
						jsonObjAddress.put("guardianAddressCityVillage", student.getAddress().getGuardianAddressCityVillage());
						jsonObjAddress.put("guardianAddressPinCode", student.getAddress().getGuardianAddressPinCode());
						jsonObjAddress.put("guardianAddressDistrict", student.getAddress().getGuardianAddressDistrict());
						jsonObjAddress.put("guardianAddressState", student.getAddress().getGuardianAddressState());
						jsonObjAddress.put("guardianAddressCountry", student.getAddress().getGuardianAddressCountry());
						jsonObjAddress.put("guardianAddressPostOffice", student.getAddress().getGuardianAddressPostOffice());
						jsonObjAddress.put("guardianAddressPoliceStation", student.getAddress().getGuardianAddressPoliceStation());
						
						addressDetails.put(jsonObjAddress);
						jsonObj.put("address", addressDetails);
						//**Address property addition work ends*//
						
						jsonObj.put("foodPreference", student.getResource().getFoodPreference());
						jsonObj.put("firstPickUpPlace", student.getResource().getFirstPickUpPlace());
						jsonObj.put("hobbies", student.getResource().getHobbies());
						jsonObj.put("personalIdentificationMark", student.getResource().getPersonalIdentificationMark());
						
						jsonObj.put("previousSchoolName", student.getPreviousSchoolName());
						jsonObj.put("previousSchoolWebsite", student.getPreviousSchoolWebsite());
						jsonObj.put("previousSchoolAddress", student.getPreviousSchoolAddress());
						jsonObj.put("previousSchoolPhone", student.getPreviousSchoolPhone());
						jsonObj.put("previousSchoolEmail", student.getPreviousSchoolEmail());
						jsonObj.put("previousAchivement", student.getPreviousAchivement());
						
						System.out.println("LN 3730 :: Json Object Contents"+jsonObj.toString());
						final String uri = URIForSendingCadetDetails;
						System.out.println("URI:::"+uri);
						/* Initialization */
						URL url = new URL(uri);
						HttpURLConnection connection = null;
						OutputStreamWriter out = null;
						String json_response = "";
						InputStreamReader in = null;
						BufferedReader br = null;
						WebIQTransaction webIQTran= null;
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
							webIQTran.setUri(URIForSendingCadetDetails);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(false);
							//Call to the BackOffice Service
							backOfficeService.addWebIQTransaction(webIQTran);
						}
						System.out.println("JSON response:::"+ json_response);

						/**Modification ends*/
						if((!json_response.isEmpty())){
							JSONObject object = new JSONObject(json_response);
							int statusFromJsonResponse = (int)object.get("status");
							if(statusFromJsonResponse==200){
								//If call to the API is successful, then insert into the webiq_transaction_details table 
								webIQTran = new WebIQTransaction();
								webIQTran.setUri(URIForSendingCadetDetails);
								webIQTran.setUpdatedBy(sessionObject.getUserId());
								webIQTran.setRequestJSON(jsonObj.toString());
								webIQTran.setResponseJSON(json_response);
								webIQTran.setStatus(true);
								
								//PRAD JUNE 14 2018
								//Creating User in LDAP
								JSONObject ldapJsonObj = new JSONObject();
								
								ldapJsonObj.put("userName",student.getResourceUserId());
								ldapJsonObj.put("password","welcome");
								ldapJsonObj.put("organization",ldapOrganization);
								ldapJsonObj.put("firstName", student.getResource().getFirstName());
								ldapJsonObj.put("lastName", student.getResource().getLastName());
								ldapJsonObj.put("serviceUserName",ldapServiceUserName);
								ldapJsonObj.put("servicePassword",ldapServicePassword);
								
								final String ldapURI = createUserURL;
								System.out.println("URI:::"+ldapURI);
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
									logger.info("The LDAP User Creation was successful");
								}else{
									logger.info("The LDAP User Creation was a failure");
								}
								//PRAD ENDS
							}else{
								//If Failure then also insert into the webiq_transaction_details table
								webIQTran = new WebIQTransaction();
								webIQTran.setUpdatedBy(sessionObject.getUserId());
								webIQTran.setUri(URIForSendingCadetDetails);
								webIQTran.setRequestJSON(jsonObj.toString());
								webIQTran.setResponseJSON(json_response);
								webIQTran.setStatus(false);
							}
							
							//Call to the BackOffice Service
							backOfficeService.addWebIQTransaction(webIQTran);
						}
					//PRAD ENDS
				}else{
					System.out.println("Error in saving stduent's data or passing to WebIQ");
				}
			}else{
				System.out.println("Repository not found");
				logger.info("Student Details Not Found To Insert.");
			}
		} 
		}catch (CustomException ce){
			ce.printStackTrace();
			logger.error("Exception in method addStudent-POST of BackOfficeController", ce);
		} catch (Exception e){
			e.printStackTrace();
			logger.error("Exception in method addStudent-POST of BackOfficeController", e);
		}
		return getStudentList(request, response, model);
	}
	
	
	
	
	@RequestMapping(value = "/getCandidateDetailsAgainstUserId", method = RequestMethod.GET)
	@ResponseBody public String getCandidateDetailsAgainstFromId(@RequestParam("userId") String userId) {
		String data = "";
		try {
			Student student = backOfficeService.getCandidateDetailsAgainstUserId(userId);
			if(null != student.getSubjectList() && student.getSubjectList().size()>=1){
				for(Subject s:student.getSubjectList()){
					data = data+s.getSubjectCode()+"%";
				}
				data = data.substring(0, data.length()-1);
				
			}
				data = data+"*";
				data = data +student.getRollNumber()+"*"
							+student.getResource().getFirstName()+"*"
							+student.getResource().getMiddleName()+"*"
							+student.getResource().getLastName()+"*"
							+student.getResource().getDateOfBirth()+"*"
							+student.getDateOfAdmission()+"*"
							+student.getResource().getGender()+"*"
							+student.getResource().getBloodGroup()+"*"
							+student.getResource().getCategory()+"*"
							+student.getResource().getReligion()+"*"
							+student.getResource().getNationality()+"*"
							+student.getStandard()+"*"
							+student.getResource().getEmailId()+"*"
							+student.getResource().getFatherFirstName()+"*"
							+student.getResource().getFatherMiddleName()+"*"
							+student.getResource().getFatherLastName()+"*"
							+student.getResource().getMotherFirstName()+"*"
							+student.getResource().getMotherMiddleName()+"*"
							+student.getResource().getMotherLastName()+"*"
							+student.getGuardianFirstName()+"*"
							+student.getGuardianMiddleName()+"*"
							+student.getGuardianLastName()+"*"
							+student.getAddress().getPresentAddressLine()+"*"
							+student.getAddress().getPermanentAddressLandmark()+"*"
							+student.getAddress().getPresentAddressCityVillage()+"*"
							+student.getAddress().getPresentAddressPinCode()+"*"
							+student.getAddress().getPresentAddressDistrict()+"*"
							+student.getAddress().getPresentAddressState()+"*"
							+student.getAddress().getPresentAddressCountry()+"*"
							+student.getAddress().getPresentAddressPostOffice()+"*"
							+student.getAddress().getPresentAddressPoliceStation()+"*"
							+student.getAddress().getPermanentAddressLine()+"*"
							+student.getAddress().getPermanentAddressLandmark()+"*"
							+student.getAddress().getPermanentAddressCityVillage()+"*"
							+student.getAddress().getPermanentAddressPinCode()+"*"
							+student.getAddress().getPermanentAddressDistrict()+"*"
							+student.getAddress().getPermanentAddressState()+"*"
							+student.getAddress().getPermanentAddressCountry()+"*"
							+student.getAddress().getPermanentAddressPostOffice()+"*"
							+student.getAddress().getPermanentAddressPoliceStation()+"*"
							+student.getPreviousSchoolName()+"*"
							+student.getPreviousSchoolWebsite()+"*"
							+student.getPreviousSchoolPhone();
			} catch (Exception ce){
			logger.error("Exception in method getDocumentVerification-GET of BackOfficeController", ce);
		}
		return (new Gson().toJson(data));
	}
	
	/**
	 * @author anup.roy
	 * 07.08.2017
	 * view student list
	 * */
	
	@RequestMapping(value = "/getStudentList", method = RequestMethod.GET)
	public ModelAndView getStudentList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("backoffice/listStudents");
		try {
			logger.info("Inside Method getStudentList-GET of BackOfficeController");
			List<Student> studentList = backOfficeService.getStudentList();
			model.addAttribute("studentList", studentList);
		} catch (CustomException ce){
			logger.error("Exception in method getStudentList-GET of BackOfficeController", ce);
		}
		return mav;
	}
	
	//07/05/2017
	@RequestMapping(value = "/getStudentDetailsToEdit", method = {RequestMethod.POST, RequestMethod.GET})
	public String getStudentDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="rollNumber") String rollNumber) {
		String strView = "backoffice/editStudent";
		try {
			logger.info("Inside Method getStudentDetails-POST of BackOfficeController");
			if(null != rollNumber){
				Student student = backOfficeService.getStudentDetails(rollNumber);
				String mobile = student.getResource().getMobile();
				mobile = "(91) "+mobile;
				mobile = mobile.substring(0, 10)+"-"+mobile.substring(10, mobile.length());
				Resource r = student.getResource();
				r.setMobile(mobile);
				student.setResource(r);
				if(null != student.getResource().getAttachmentList() && student.getResource().getAttachmentList().size() != 0){
					for(Attachment a : student.getResource().getAttachmentList()){
						if(a.getAttachmentType().equalsIgnoreCase("profile_image")){
							String profileImage = a.getStorageRootPath()+a.getAttachmentName();
							fileUploadDownload.getBase64Image(profileImage);
							Image image = new Image();
							image.setImageName(fileUploadDownload.getBase64Image(profileImage));
							student.getResource().setImage(image);
						}
					}
				}else{
					model.addAttribute("message", "Students Details Not Found");
					 getStudentList( request, response,  model);
				}			
				model.addAttribute("student", student);			

				List<Course>courseList = academicsService.getCourseList();
				model.addAttribute("courseList", courseList);
				
				List<SocialCategory> socialCategoryList=backOfficeService.getSocialCategoryList();
				model.addAttribute("socialCategoryList", socialCategoryList);
				
				List<Section> sectionList=backOfficeService.getSectionListForStandard(student.getStandard());
				model.addAttribute("sectionList", sectionList);
				
				List<Standard> standardList=commonService.getStandards();
				model.addAttribute("standardList", standardList);
				
				List<Candidate> candidateList=backOfficeService.getFeesPaidCandidate();
				model.addAttribute("candidateList", candidateList);
				
				/*List<Hostel> hostelList=hostelService.getHostel();
				model.addAttribute("hostelList", hostelList);*/
				List<House> houseList = hostelService.getAllHouseList();
				model.addAttribute("houseList", houseList);
				
				List<ResidentType> residentTypeList = backOfficeService.getAllResidentTypeList();
				model.addAttribute("residentTypeList", residentTypeList);
				
				List<Country> countryList=commonService.getCountryList();
				model.addAttribute("countryList", countryList);
				
				List<State> stateList=commonService.getAllStatesInIndia("IND");
				model.addAttribute("stateList", stateList);
				
				/*List<State> permanentStateList=commonService.getAllStatesInIndia(student.getAddress().getPermanentAddressCountry());
				model.addAttribute("permanentStateList", permanentStateList);
				
				List<State> presentStateList=commonService.getAllStatesInIndia(student.getAddress().getPresentAddressCountry());
				model.addAttribute("presentStateList", presentStateList);
				
				List<State> guardianStateList=commonService.getAllStatesInIndia(student.getAddress().getGuardianAddressCountry());
				model.addAttribute("guardianStateList", guardianStateList);
							
				List<Scholarship> scholarshipList=backOfficeService.getScholarshipList();
				model.addAttribute("scholarshipList", scholarshipList);*/
				model.addAttribute("fileDeleteStatus", null);
			}else{
				model.addAttribute("message", "Students Details Not Found");
				 getStudentList( request, response,  model);
			}
			
			
		} catch (CustomException ce){
			model.addAttribute("message", "Students Details Not Found");
			getStudentList( request, response,  model);
			logger.error("Exception in method getStudentDetails-POST of BackOfficeController", ce);
		}
		return strView;
	}
	
	
	
	@RequestMapping(value = "/viewStudentProfileList", method = RequestMethod.GET)
	public ModelAndView viewStudentProfile(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("backoffice/viewStudentProfileList");
		try {
			logger.info("Inside Method viewStudentProfile-GET of BackOfficeController");
			List<Student> studentList = backOfficeService.getStudentList();
			model.addAttribute("studentList", studentList);
		} catch (CustomException ce){
			logger.error("Exception in method getStudentList-GET of BackOfficeController", ce);
		}
		
		return mav;
	}
	
	
	@RequestMapping(value = "/viewStudentProfileDetails", method = RequestMethod.GET)
	public ModelAndView viewStudentProfileDetails(HttpServletRequest request,
												HttpServletResponse response, 
												ModelMap model,
												@RequestParam("roll") String rollNumber,
												Student student) {
		System.out.println("roll::"+rollNumber);
		logger.info("In viewStudentProfileDetails() method of BackOfficeController: ");
		String strView = null;
		
		try {
			logger.info("In BackOfficeController viewStudentProfileDetails()");
			student.setUserId(rollNumber);                 //07052017
			Student studentDetailsForView = backOfficeService.getStudentListForEdit(student);
			if (studentDetailsForView != null) {
				model.addAttribute("studentDetailsFromDB", studentDetailsForView);
				strView = "backoffice/studentProfilePage";
			} else {
				model.addAttribute("ResultError", "Result not available....");
				strView = "common/errorpage";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(strView);
	}
	
	
	
	/**
	 * anup.backoffice from SMS*/
	
	@RequestMapping(value="/configureFinancialYear", method = RequestMethod.GET)
	public String configureFinancialYear(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try{
			List<FinancialYear> financialYearList = commonService.getFinancialYearList();
			if(null!=financialYearList && financialYearList.size() != 0){
				model.addAttribute("financialYearList", financialYearList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "backoffice/configureFinancialYear";
	}
	
	
	@RequestMapping(value="/createNewFinancialYear", method = RequestMethod.POST)
	public String createNewFinancialYear(HttpServletRequest request, HttpServletResponse response,
										ModelMap model,FinancialYear financialYear,
										@ModelAttribute("sessionObject") SessionObject sessionObject){
		String message=null;
		try{
			if(null != financialYear){
				financialYear.setUpdatedBy(sessionObject.getUserId());
				message = backOfficeService.createNewFinancialYear(financialYear);
			}else{
				message = "failed";
			}
			if(null!=message){
				if(message.equalsIgnoreCase("failed")){
					model.addAttribute("failuremsg", "Failed To Create New Financial Year");
				}
				if(message.equalsIgnoreCase("exist")){
					model.addAttribute("failuremsg", "Failed To Create New Financial Year,Current Year Already Exists");
				}
				if(message.equalsIgnoreCase("created")){
					model.addAttribute("successmsg", "New Financial Year Created Successfully");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return configureFinancialYear(request,response,model);
	}
	
	@RequestMapping(value="/updateFinancialYear", method = RequestMethod.POST)
	public String updateFinancialYear(HttpServletRequest request, HttpServletResponse response,
									ModelMap model, 
									FinancialYear financialYear,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		String message=null;
		try{
			if(null !=financialYear){
				financialYear.setUpdatedBy(sessionObject.getUserId());
				message = backOfficeService.updateFinancialYear(financialYear);
			}else{
				message="failed";
			}
			if(null!=message){
				if(message.equalsIgnoreCase("failed")){
					model.addAttribute("failuremsg", "Failed To Update Financial Year");
				}				
				if(message.equalsIgnoreCase("created")){
					model.addAttribute("successmsg", "Financial Year Updated Successfully");
				}
			}
			/**Added by @author Saif.Ali
			 * Date- 20/03/2018
			 * Used to insert the information into the activity_log table*/
			String oldFinancialYearName = request.getParameter("oldFinancialYearName");	// old Scholarship name value
			String newFinancialYearName = request.getParameter("financialYearName");	// new Scholarship name value
			
			if(message.equalsIgnoreCase("created")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT FINANCIAL YEAR");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor(oldFinancialYearName);
				String description = "";
				if(!(oldFinancialYearName.equalsIgnoreCase(newFinancialYearName)))
				{
					description=description + ("Financial Year Name :: " + oldFinancialYearName + " updated to new Financial Year Name :: " + newFinancialYearName + " ; ");
				}
				updateLog.setDescription(description);
				commonService.insertIntoActivityLog(updateLog);
			}
			/**Addition ends*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return configureFinancialYear(request,response,model);
	}
	
	@RequestMapping(value = "/configureWorkingDays", method = RequestMethod.GET)
	public ModelAndView configureWorkingDays(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = null;
		try {
			logger.info("In BackOfficeController configureWorkingDays() method: calling listTermDetails() in BackOfficeService.java");
			/* modified by sourav.bhadra
			 * on 14-07-2017 to get current academic year */
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
			if (currentYear != null) {
				model.addAttribute("currentYear", currentYear);
				strView = "backoffice/configureWorkingDays";
			}else {
				model.addAttribute("currentYear", currentYear);
				strView = "backoffice/configureWorkingDays";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(strView);
	}
	
	
	@RequestMapping(value = "/listConfigureWorkingDays", method = RequestMethod.POST)
	public ModelAndView listConfigureWorkingDaysShow(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, 
			@RequestParam("academicYear") String academicYear,
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			@RequestParam("mode") String strmode,
			@RequestParam("tworking") String strholdays,
			@RequestParam("inputDateA") String[] strpubholdays,
			@RequestParam("checkHoliday") String strClosedDay,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		Term term = new Term();
		List<Holiday> holidayList = new ArrayList<Holiday>();
		try {
			/*System.out.println("LN3927...BOC...Year :: "+year);
			System.out.println("LN3928...BOC...Month :: "+month);
			System.out.println("LN3929...BOC...Mode :: "+strmode);
			System.out.println("LN3930...BOC...Working Days :: "+strholdays);
			System.out.println("LN3931...BOC...Holiday :: "+strClosedDay);
			for(int i=0; i<strpubholdays.length; i++){
				System.out.println("LN3934...BOC...Dates :: "+strpubholdays[i]);
			}*/
			
			term.setAcademicYear(academicYear);
			term.setTermStartDate(year);
			term.setTermObjectId(month);
			term.setNoOfWorkingDays(Integer.parseInt(strholdays));
			term.setUpdatedBy(sessionObject.getUserId());
			term.setCount(Integer.parseInt(request.getParameter("tholi")));
			if (null != strClosedDay && strClosedDay.length() != 0) {
				String[] splitedClosedDay = strClosedDay.split(";");
				if(splitedClosedDay.length > 1){
					term.setHoliDayOne(splitedClosedDay[0]);
					term.setHoliDayTwo(splitedClosedDay[1]);
				}else{
					term.setHoliDayOne(splitedClosedDay[0]);
				}
			}
			
			logger.info("In BackOfficeController listConfigureWorkingDaysShow() method: calling updateWorkingDays(Term term) in BackOfficeService.java");
			
			if ((strpubholdays != null && strpubholdays.length != 0)) {
				for (int i = 0; i < strpubholdays.length; i++) {
					Holiday holiday = new Holiday();
					holiday.setUpdatedBy(sessionObject.getUserId());
					holiday.setHolidayCode("CODE");
					holiday.setSpecialHoliday(strpubholdays[i]);
					holiday.setMode(strmode);
					holidayList.add(holiday);
				}
				term.setHoliday(holidayList);
			}
			logger.info("In BackOfficeController listConfigureWorkingDaysShow() method: calling insertHolidays(Term term) in BackOfficeService.java");
			backOfficeService.insertHolidays(term);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e){ 
			logger.error("Error in BackOfficeController listConfigureWorkingDaysShow() method for ArrayIndexOutOfBoundsException: ", e); 
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return showListConfigureWorkingDays(request,response,model) ;
	}
	
	
	
	@RequestMapping(value = "/showListConfigureWorkingDays", method = RequestMethod.GET)
	public ModelAndView showListConfigureWorkingDays(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = null;
		try {
			logger.info("In BackOfficeController listConfigureWorkingDaysShow() method: calling listTermHolidays(Term term) in BackOfficeService.java");
			List<Term> listTermHolidays = backOfficeService.listTermHolidaysForShow();
			if (listTermHolidays != null && listTermHolidays.size() != 0) {
				model.addAttribute("listTermHolidays", listTermHolidays);
				strView = "backoffice/showListConfigureWorkingDays";
			} else {
				model.addAttribute("listTermHolidays", listTermHolidays);
				strView = "backoffice/showListConfigureWorkingDays";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController listConfigureWorkingDaysShow() method for NullPointerException: ",e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController listConfigureWorkingDaysShow() method for NumberFormatException: ",e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController listConfigureWorkingDaysShow() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/editConfigureWorkingDays", method = RequestMethod.GET)
	public ModelAndView editConfigureWorkingDaysShow(
			HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(required=false, value="holidayCode") String holidayCode,
			@RequestParam(required=false, value="hiddenAcaYear") String academicYear,
			@RequestParam(required=false, value="hiddenYear") String year,
			@RequestParam(required=false, value="hiddenMonth") String month, 
			@RequestParam(required=false, value="hiddenDays") String workingDays) {
		String strView = "";
		System.out.println("LN4027 in editConfigureWorkingDays");
		try {
			/*System.out.println("LN4031...Holiday Code :: "+holidayCode);
			System.out.println("LN4032...Academic Year :: "+academicYear);
			System.out.println("LN4033...Year :: "+year);
			System.out.println("LN4034...Month :: "+month);
			System.out.println("LN4035...Working Days :: "+workingDays);
			*/
			if (null != holidayCode && null != academicYear && null != year && null != month && null != workingDays) {
				model.addAttribute("holidayCode", holidayCode);
				model.addAttribute("academicYear", academicYear);
				model.addAttribute("year", year);
				model.addAttribute("month", month);
				model.addAttribute("workingDays", workingDays);
				strView = "backoffice/editConfigureWorkingDays";
			} else {
				model.addAttribute("ResultError", "Result not available....");
				strView = "common/errorpage";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/updatePublicHolidays", method = RequestMethod.GET)
	public ModelAndView editHoliDaysShow(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("holidayCode") String holidayCode,
			@RequestParam("academicYear") String academicYear,
			@RequestParam("month") String month,
			@RequestParam("inputDateA") String strdays,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		Holiday holiday = new Holiday();
		try {
			/*System.out.println("LN4069...Holiday Code :: "+holidayCode);
			System.out.println("LN4070...Academic Year :: "+academicYear);
			System.out.println("LN4071...Month :: "+month);
			System.out.println("LN4072...Special Holiday :: "+strdays);
			*/
			if (null != holidayCode && null != academicYear && null != month && null != strdays) {
				holiday.setHolidayCode(holidayCode);
				holiday.setYear(academicYear);
				holiday.setMonth(month);
				holiday.setSpecialHoliday(strdays);
				holiday.setUpdatedBy(sessionObject.getUserId());
				backOfficeService.updatePublicHoliday(holiday);
				System.out.println("**********BOC*******updatePublicHolidays************");
				logger.info("In BackOfficeController editHoliDaysShow() method: calling updatePublicHoliday(Holiday holi) in BackOfficeService.java");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showListConfigureWorkingDays(request,response,model) ;
	}
	
	@RequestMapping(value = "/configureSpecialDays", method = RequestMethod.GET)
	public ModelAndView configureSpecialDaysShow(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In BackOfficeController configureSpecialDaysShow() method: calling listCourse() in BackOfficeService.java");
			List<Course> listCourse = backOfficeService.listCourse();
			if (listCourse != null && listCourse.size() != 0) {
				model.addAttribute("CourseList", listCourse);
			} 
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("backoffice/configureSpecialDays");
	}
	
	@RequestMapping(value = "/getWorkingDays", method = RequestMethod.GET)
	public @ResponseBody
	String getworkingdays(@RequestParam("sterm") String termid) {
		logger.info("In BackOfficeController getworkingdays() method: calling getTermDate(String termid) in BackOfficeService.java");
		String showDateone = backOfficeService.getTermDate(termid);
		return (new Gson().toJson(showDateone));
	}
	
	@RequestMapping(value = "/listConfigureSpecialDays", method = RequestMethod.GET)
	public ModelAndView listConfigureSpecialDaysShow(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @RequestParam("singled") String strradio,
			@RequestParam("inputstream") String stterm,
			@RequestParam("mode") String strmodes,
			@RequestParam("tworking") String strdays,
			@RequestParam("dates") String[] strstartday,
			@RequestParam("inputDateA") String[] strCompensatory,
			@RequestParam("datesone") String strstartdayanother,
			@RequestParam("inputDateAh") String strcompensatoryanother,
			@RequestParam("reason") String strreasonone,
			@RequestParam("reasonone") String strreasontwo, Term term,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			int strintdays = Integer.parseInt(strdays);
			term.setUpdatedBy(sessionObject.getUserId());
			term.setNoOfWorkingDays(strintdays);
			term.setTermCode(stterm);
			logger.info("In BackOfficeController listConfigureSpecialDaysShow() method: calling updateWorkingDays(Term term) in BackOfficeService.java");
			backOfficeService.updateWorkingDays(term);
			List<Holiday> termHolidayList = new ArrayList<Holiday>();
			if (strradio.equalsIgnoreCase("single")) {
				Holiday holidaynew = new Holiday();
				holidaynew.setHolidayObjectId("jk");
				holidaynew.setUpdatedBy(sessionObject.getUserId());
				holidaynew.setHolidayCode("code");
				holidaynew.setHolidayDesc(strreasonone);
				holidaynew.setSpecialHoliday(strstartdayanother);
				holidaynew.setMode(strmodes);
				holidaynew.setCompensatory(strcompensatoryanother);
				holidaynew.setTermCode(stterm);
				termHolidayList.add(holidaynew);
				term.setHoliday(termHolidayList);
			}
			if (strradio.equalsIgnoreCase("multi")) {
				if (strstartday != null && strstartday.length != 0) {
					for (int i = 0; i < (strstartday.length); i++) {
						Holiday holidaynew = new Holiday();
						holidaynew.setHolidayObjectId("jk");
						holidaynew.setUpdatedBy(sessionObject.getUserId());
						holidaynew.setHolidayCode("code");
						holidaynew.setHolidayDesc(strreasontwo);
						holidaynew.setSpecialHoliday(strstartday[i]);
						holidaynew.setMode(strmodes);
						if (strCompensatory != null && strCompensatory.length != 0) {
							if (i < strCompensatory.length && i <= strCompensatory.length) {
								holidaynew.setCompensatory(strCompensatory[i]);
							}
						}
						holidaynew.setTermCode(stterm);
						termHolidayList.add(holidaynew);
						term.setHoliday(termHolidayList);
					}
				}
			}
			logger.info("In BackOfficeController listConfigureSpecialDaysShow() method: calling insertHolidays(Term term) in BackOfficeService.java");
			backOfficeService.insertHolidays(term);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}/*
		 * catch(ArrayIndexOutOfBoundsException e){ logger.error(
		 * "Error in BackOfficeController listConfigureWorkingDaysShow() method for ArrayIndexOutOfBoundsException: "
		 * , e); }
		 */catch (Exception e) {
			e.printStackTrace();
		}
		return  showListSpecialDays(request,response,model,term);
	}
	
	@RequestMapping(value = "/showListSpecialDays", method = RequestMethod.GET)
	public ModelAndView showListSpecialDays(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Term term) {
		String strView = null;
		try {
			logger.info("In BackOfficeController showListSpecialDays() method: calling listTermHolidays(Term term) in BackOfficeService.java");
			term.setTermDesc("Special");
			List<Term> listTermHolidays = backOfficeService.listTermHolidaysForShow();
			if (listTermHolidays != null && listTermHolidays.size() != 0) {
				model.addAttribute("listTermHolidays", listTermHolidays);
				strView = "backoffice/listConfigureSpecialDays";
			} else {
				model.addAttribute("listTermHolidays", listTermHolidays);
				strView = "backoffice/listConfigureSpecialDays";
			}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController listConfigureWorkingDaysShow() method for NullPointerException: ", e);
		} catch (NumberFormatException e) {
			logger.error("Error in BackOfficeController listConfigureWorkingDaysShow() method for NumberFormatException: ", e);
		}catch (Exception e) {
			logger.error("Error in BackOfficeController listConfigureWorkingDaysShow() method for Exception: ", e);
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/editConfigureSpecialDays", method = RequestMethod.GET)
	public ModelAndView editConfigureSpecialDaysShow(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @RequestParam("hiddencode") String strspeccode,
			@RequestParam("hiddenstart") String strstart,
			@RequestParam("hiddenterm") String strspecterm,
			@RequestParam("hiddendays") String strspecdays, Term term,
			Holiday holi) {
		try {
			int strspecdaysint = Integer.parseInt(strspecdays);
			int strspeccodeint = Integer.parseInt(strspeccode);
			term.setTermCode(strspecterm);
			term.setNoOfWorkingDays(strspecdaysint);
			logger.info("In BackOfficeController editConfigureSpecialDaysShow() method: calling specifictermforsingle(Term term) in BackOfficeService.java");
			List<Term> listTermHolidaysspec = backOfficeService.specifictermforsingle(term);
			holi.setHolidayDetailsId(strspeccodeint);
			logger.info("In BackOfficeController editConfigureSpecialDaysShow() method: calling listspeHolidays(Holiday holi) in BackOfficeService.java");
			List<Holiday> listspecialHolidays = backOfficeService.listspeHolidays(holi);
			if (listTermHolidaysspec != null && listTermHolidaysspec.size() != 0) {
				model.addAttribute("listspecialHolidays", listspecialHolidays);
				model.addAttribute("editTerm", listTermHolidaysspec);
				model.addAttribute("strspeccode", strspeccode);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("backoffice/editConfigureSpecialDays");
	}
	
	
	@RequestMapping(value = "/updateSpecialHolidays", method = RequestMethod.POST)
	public ModelAndView updateSpecialHolidays(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("holidaycode") String strspeccode,
			@RequestParam("yesno") String strspectermcode,
			@RequestParam("mode") String strmodes,
			@RequestParam("tworking") String strdays,
			@RequestParam("inputDateA1") String strstartday,
			@RequestParam("inputDateA") String strcompensatory,
			@RequestParam("reasonone") String strreasontwo, Term term,
			Holiday holidaynew,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			int strintdays = Integer.parseInt(strdays);
			int strspeccodeint = Integer.parseInt(strspeccode);
			term.setNoOfWorkingDays(strintdays);
			term.setTermCode(strspectermcode);
			term.setUpdatedBy(sessionObject.getUserId());
			logger.info("In BackOfficeController updateSpecialHolidays() method: calling updateWorkingDays(Term term) in BackOfficeService.java");
			backOfficeService.updateWorkingDays(term);
			List<Holiday> termHolidayList = new ArrayList<Holiday>();
			if ((strcompensatory != null && strcompensatory.trim().length() != 0)) {
				holidaynew.setHolidayObjectId("jk");
				holidaynew.setUpdatedBy(sessionObject.getUserId());
				holidaynew.setHolidayCode("code");
				holidaynew.setHolidayDesc(strreasontwo);
				holidaynew.setSpecialHoliday(strstartday);
				holidaynew.setMode(strmodes);
				holidaynew.setCompensatory(strcompensatory);
				holidaynew.setTermCode(strspectermcode);
				holidaynew.setHolidayDetailsId(strspeccodeint);
				termHolidayList.add(holidaynew);
			}
			// Term termnew = new Term();
			// termnew.setHoliday(termHolidayList);
			logger.info("In BackOfficeController updateSpecialHolidays() method: calling updatePublicHoliday(Holiday holidaynew) in BackOfficeService.java");
			backOfficeService.updatePublicHoliday(holidaynew);
			logger.info("In BackOfficeController updateSpecialHolidays() method: calling listTermHolidaysforSpecial(Term term) in BackOfficeService.java");

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showListSpecialDays(request,response,model,term);
	}
	
	
	/***
	 * FOR ATTENDANCE */
	
	
	@RequestMapping(value = "/resourceAttendance", method = RequestMethod.GET)
	public ModelAndView resourceAttendance(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In resourceAttendance() method ");
		List<Term> listTerm = null;
		List<CalendarEvent> specialHolidays = null;
		try {
			logger.info("showStudentAttendance()In BackOfficeController.java: calling fetchclassDetails() in BackOfficeService.java");
			listTerm = backOfficeService.listTermDetails();
			if (listTerm != null) {
				model.addAttribute("listTerm", listTerm);
			}
			specialHolidays = backOfficeService.listSpecialHolidays();
			if (specialHolidays != null) {
				model.addAttribute("specialHolidays", specialHolidays);
			}
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
			}
			AcademicYear currentYear = commonService.getCurrentAcademicYear();
			if (currentYear != null) {
				//System.out.println(currentYear);
				model.addAttribute("currentYear", currentYear);
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
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("backoffice/attendance");
	}
	
	
	@RequestMapping(value = "/getDaysForAttendance", method = RequestMethod.GET)
	public @ResponseBody
	String getDaysForAttendance(@RequestParam("yearvalue") String selectedyear,
								@RequestParam("monthvalue") String selectedmonth) {
		String showDays = "";
		String specialDays = "";
		try {
			logger.info("In getDaysForAttendance() method ");
			/* added by sourav.bhadra on 20-07-2017 to get holiday details*/
			Term holidayDetails = backOfficeService.getHolidayDetailsOfAMonth(selectedmonth, selectedyear);
			/* modified by sourav.bhadra on 29-08-2017 */
			if(null != holidayDetails){
				System.out.println("LN4348 :: Academic Year :: "+holidayDetails.getAcademicYear()+"\nSpecial Holiday :: "+holidayDetails.getCount()+"\nTotal Workingdays :: "+holidayDetails.getNoOfWorkingDays());
				for(Holiday h : holidayDetails.getHoliday()){
					System.out.println("LN4350 :: Special Holiday :: "+h.getSpecialHoliday());
					String[] date = h.getSpecialHoliday().split("/");
					/* added by sourav.bhadra on 25-07-2017 to remove '0' 
					 * from those dates which are smaller than 10 */
					if(Integer.parseInt(date[0])<10){
						date[0] = date[0].substring(1);
					}
					specialDays += date[0]+";";
				}System.out.println("LN4356 :: "+specialDays);
			}else{
				specialDays += "0;";
			}
			
			/*======*/
			int intselectedyear = Integer.parseInt(selectedyear);
			int intselectedmonth = Integer.parseInt(selectedmonth);
			Calendar cal = new GregorianCalendar();
			cal.clear();
			cal.set(intselectedyear, intselectedmonth - 1, 1);
			int firstWeekdayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
			// obtain the number of days in month.
			int numberOfMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			String header = "";
			// print calendar weekday header
			for (int day3 = 1; day3 <= 4; day3++) {
				// System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");
				header = header + "  " + "Su  Mo  Tu  We  Th  Fr  Sa";
			}
			// leave/skip weekdays before the first day of month
			for (int day = 1; day < firstWeekdayOfMonth; day++) {
				//System.out.print("    ");
			}
			// print the days of month in tabular format.
			for (int day = 1; day <= numberOfMonthDays; day++) {
				// print day
				 //System.out.printf("%1$2d", day);
				cal.set(intselectedyear, intselectedmonth - 1, day);
				//System.out.println("weekday"+cal.get(Calendar.DAY_OF_WEEK));
				// next weekday
				showDays = showDays + "," + day + "/" + cal.get(Calendar.DAY_OF_WEEK);
			}
			showDays = showDays+"@"+specialDays;
			System.out.println("LN4361...BOCon...showdays :: "+showDays);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(showDays));
	}
	
	
	@RequestMapping(value = "/getClassForAttendance", method = RequestMethod.GET)
	public @ResponseBody
	String getClassForAttendance() {
		logger.info("In getClassForAttendance() method ");
		String showClass = backOfficeService.getClassForAttendance();
		return (new Gson().toJson(showClass));
	}
	
	
	
	@RequestMapping(value = "/getCourseForAttendance", method = RequestMethod.GET)
	public @ResponseBody
	String getCourseForAttendance(@RequestParam("class") String className) {
		logger.info("In getStreamForAttendance() method ");
		String showStream = backOfficeService.getCourseForAttendance(className);
		return (new Gson().toJson(showStream));
	}
	
//	@RequestMapping(value = "/getStreamForAttendance", method = RequestMethod.GET)
//	public @ResponseBody
//	String getStreamForAttendance(@RequestParam("course") String course) {
//		logger.info("In getStreamForAttendance() method ");
//		String showStream = backOfficeService.getStreamForAttendance(course);
//		return (new Gson().toJson(showStream));
//	}
	
	
	@RequestMapping(value = "/getSectionForAttendance", method = RequestMethod.GET)
	public @ResponseBody
	String getSectionForAttendance(@RequestParam("class") String selectedclass, Resource resource) {
		String showSections = "";
		try {
			logger.info("In getSectionForAttendance() method ");
			resource.setKlass(selectedclass);
			//resource.setStream(selectedstream);
			showSections = backOfficeService.getSectionForAttendance(resource);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(showSections));
	}
	
	
	@RequestMapping(value = "/getStudentsForAttendance", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentsForAttendance(
									@RequestParam("class") String selectedclass,
									@RequestParam("section") String selectedsection,
									@RequestParam("year") String selectedyear,
									@RequestParam("month") String selectedmonth,
									StudentAttendance studentAttendance) {
		
		String finaldata = "";
		try {
			//System.out.println("4394 in backofcCon::\nclass::"+selectedclass+"\nsection::"+selectedsection+"\nyear::"+selectedyear+"\nmonth::"+selectedmonth);
			logger.info("In getStudentsForAttendance() method ");
			studentAttendance.setYear(selectedyear);
			studentAttendance.setMonth(selectedmonth);
			String showStudentanother = backOfficeService.fetchStudentId(studentAttendance);
			Resource resource = new Resource();
			resource.setKlass(selectedclass);
			//resource.setStream(selectedstream);
			Section section = new Section();
			section.setSectionName(selectedsection);
			resource.setSection(section);
			String showStudent = backOfficeService.getStudentsForAttendance(resource);
			finaldata = showStudentanother + "@" + showStudent;
			System.out.println("4407 in backofcController::"+finaldata);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(finaldata));
	}
	
	
	@RequestMapping(value = "/getDateOfCreationForInsertedStudent", method = RequestMethod.GET)
	public @ResponseBody
	String getDateOfCreationForInsertedStudent(@RequestParam("month") String month,
							@RequestParam("year") String year,
							@RequestParam("studentId") String studentId) {
		String sm = "";
		try {
			sm = backOfficeService.getDateOfCreationForInsertedStudent(month, year, studentId);
		} catch (NullPointerException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return (new Gson().toJson(sm));
	}
	
	
	@RequestMapping(value = "/getDateOfCreationForInsertedResource", method = RequestMethod.GET)
	public @ResponseBody
	String getDateOfCreationForInsertedResource(@RequestParam("month") String month,
							@RequestParam("year") String year,
							@RequestParam("sessionObject") SessionObject sessionObject,
							@RequestParam("shift") String shift,
							@RequestParam("resourceType") String resourceType) {
		String sm = "";
		try {
			sm = backOfficeService.getDateOfCreationForInsertedResource(month, year, sessionObject.getUserId(), shift, resourceType);
		} catch (NullPointerException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return (new Gson().toJson(sm));
	}
	
	 
	@RequestMapping(value = "/insertStudentAttendance", method = RequestMethod.GET)
	public ModelAndView insertStudentAttendance(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("hiddenAbsentDay") String absentdays,
			@RequestParam("hiddenDetails") String studentdetails,
			@RequestParam("hiddenId") String resourceId,
			@RequestParam("hiddenType") String hiddenType,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//System.out.println("line 4489 in controller::absentday:::"+absentdays+"  stduentdetails::"+studentdetails+"  resource_id::"+resourceId+"  hiddenType::"+hiddenType);
		try {
			List<Term> listTerm = null;
			List<CalendarEvent> specialHolidays = null;
			String showStudentanother = null;
			String previousTeacherList = "";
			String[] studentattendancedetails = absentdays.split(",");
			String[] datedetails = studentdetails.split(",");
			StudentAttendance studentAttendanceanother = new StudentAttendance();
			Map<String, Object> insertedStudentMapDB = new HashMap<String, Object>();
			Map<String, Object> insertedTeacherMapDB = new HashMap<String, Object>();
			logger.debug("insertStudentAttendance()In BackOfficeController.java: calling insertStudentAttendance(),updateStudentAttendance(),updateCheckedStudentAttendance() in BackOfficeService.java");
			String[] previousdetails = null;
			String[] previousdetailsForTeacher = null;
			for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
				studentAttendanceanother.setMonth(datedetails[0]);
				studentAttendanceanother.setYear(datedetails[1]);
			}
			ResourceType resourceType = new ResourceType();
			resourceType.setResourceTypeName(hiddenType);
			if (hiddenType.equals("STUDENT")) {
				showStudentanother = backOfficeService.fetchStudentId(studentAttendanceanother);
				//System.out.println("in line 4508> showStudentanother::  " + showStudentanother);
				if (showStudentanother != null) {
					previousdetails = showStudentanother.split("&");
					for (int listnum = 0; listnum < previousdetails.length; listnum++) {
						String[] splitedinfo = previousdetails[listnum].split("~");
						insertedStudentMapDB.put(splitedinfo[0], splitedinfo[1]);
					}
				}
				StudentAttendance studentAttendance = new StudentAttendance();
				if (insertedStudentMapDB.containsKey(resourceId)) {
					//int x = Integer.parseInt(resourceId);
					studentAttendance.setStudentRollNo(resourceId);
					backOfficeService.updateStudentAttendance(studentAttendance);
					for (int listnumber = 0; listnumber < studentattendancedetails.length; listnumber++) {
						//int y = Integer.parseInt(resourceId);
						studentAttendance.setStudentRollNo(resourceId);
						studentAttendance.setResourceType(resourceType);
						if (studentattendancedetails[listnumber] != "") {
							studentAttendance.setAbsentDay(studentattendancedetails[listnumber]);
						}
						for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
							studentAttendance.setMonth(datedetails[0]);
							studentAttendance.setYear(datedetails[1]);
						}
						studentAttendance.setUpdatedBy(sessionObject.getUserId());
						//System.out.println("in controler: line 4532::  studentid::"+studentAttendance.getStudentId()+"   stduentabsent::"+studentAttendance.getAbsentDay());
						backOfficeService.insertStudentAttendance(studentAttendance);
					}
				} else {
					for (int listnumber = 0; listnumber < studentattendancedetails.length; listnumber++) {
						//int z = Integer.parseInt(resourceId);
						studentAttendance.setStudentRollNo(resourceId);
						studentAttendance.setResourceType(resourceType);
						if (studentattendancedetails[listnumber] != "") {
							studentAttendance.setAbsentDay(studentattendancedetails[listnumber]);
						}
						for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
							studentAttendance.setMonth(datedetails[0]);
							studentAttendance.setYear(datedetails[1]);
						}
						studentAttendance.setUpdatedBy(sessionObject.getUserId());
						backOfficeService.insertStudentAttendance(studentAttendance);
					}
				}
			}
			if (!hiddenType.equals("STUDENT")) {
				previousTeacherList = backOfficeService.fetchTeacherId(studentAttendanceanother);
				if (previousTeacherList != null) {
					previousdetailsForTeacher = previousTeacherList.split("&");
					for (int listnum = 0; listnum < previousdetailsForTeacher.length; listnum++) {
						String[] splitedinfo = previousdetailsForTeacher[listnum].split("~");
						insertedTeacherMapDB.put(splitedinfo[0], splitedinfo[1]);
					}
				}
				StudentAttendance studentAttendance = new StudentAttendance();
				WorkShift workShift = new WorkShift();
				
				if (insertedTeacherMapDB.containsKey(resourceId)) {
					studentAttendance.setResourceId(resourceId);
					backOfficeService.updateTeacherAttendance(studentAttendance);
					for (int listnumber = 0; listnumber < studentattendancedetails.length; listnumber++) {
						studentAttendance.setResourceId(resourceId);
						studentAttendance.setResourceType(resourceType);
						if (studentattendancedetails[listnumber] != "") {
							studentAttendance.setAbsentDay(studentattendancedetails[listnumber]);
						}
						for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
							studentAttendance.setMonth(datedetails[0]);
							studentAttendance.setYear(datedetails[1]);
							workShift.setWorkShiftCode(datedetails[2]);
							studentAttendance.setWorkShift(workShift);
						}
						studentAttendance.setUpdatedBy(sessionObject.getUserId());
						backOfficeService.insertTeacherAttendance(studentAttendance);
					}
				} else {
					for (int listnumber = 0; listnumber < studentattendancedetails.length; listnumber++) {
						studentAttendance.setResourceId(resourceId);
						studentAttendance.setResourceType(resourceType);
						if (studentattendancedetails[listnumber] != "") {
							studentAttendance.setAbsentDay(studentattendancedetails[listnumber]);
						}
						for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
							studentAttendance.setMonth(datedetails[0]);
							studentAttendance.setYear(datedetails[1]);
							workShift.setWorkShiftCode(datedetails[2]);
							studentAttendance.setWorkShift(workShift);
						}
						studentAttendance.setUpdatedBy(sessionObject.getUserId());
						backOfficeService.insertTeacherAttendance(studentAttendance);
					}
				}
			}
			// end teacher attendance
			listTerm = backOfficeService.listTermDetails();
			specialHolidays = backOfficeService.listSpecialHolidays();
			AcademicYear currentYear = commonService.getCurrentAcademicYear();
			if (currentYear != null) {
				model.addAttribute("currentYear", currentYear);
			}
			if (specialHolidays != null) {
				model.addAttribute("specialHolidays", specialHolidays);
			}
			if (listTerm != null) {
				model.addAttribute("listTerm", listTerm);
			}
			if (studentdetails != null) {
				model.addAttribute("studentdetails", studentdetails);
			}
			if (hiddenType != null) {
				model.addAttribute("resourceType", hiddenType);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resourceAttendance (request,response,model);
	}
	
	
	@RequestMapping(value = "/getTeacherDetailsForAttendance", method = RequestMethod.GET)
	public @ResponseBody
	String getTeacherDetailsForAttendance(
			@RequestParam("shift") String strshift,
			@RequestParam("year") String selectedyear,
			@RequestParam("month") String selectedmonth,
			@RequestParam("resourceTypeSelected") String resourceTypeStr,
			StudentAttendance studentAttendance,ResourceType resourceType,WorkShift workShift) {
		String finaldata = "";
		try {
			logger.info("In getTeachingLevelForAttendance() method ");
			studentAttendance.setYear(selectedyear);
			studentAttendance.setMonth(selectedmonth);
			resourceType.setResourceTypeName(resourceTypeStr);
			studentAttendance.setResourceType(resourceType);
			workShift.setWorkShiftCode(strshift);
			studentAttendance.setWorkShift(workShift);
			String showInsertedTeacher = backOfficeService.fetchTeacherId(studentAttendance);
			String showTeachingDetails = backOfficeService.getTeacherDetailsForAttendance(studentAttendance);
			finaldata = showInsertedTeacher + "$" + showTeachingDetails;
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController getTeacherDetailsForAttendance() method for NullPointerException: ", e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController getTeacherDetailsForAttendance() method for Exception: ", e);
		}
		return (new Gson().toJson(finaldata));
	}
	
	
	
	/*author Kaustav Sen*/
	
	@RequestMapping(value = "/teacherAttendanceShow", method = RequestMethod.GET)
	public ModelAndView teacherAttendance(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In teacherAttendance() method ");
		List<Term> listTerm = null;
		List<CalendarEvent> specialHolidays = null;
		try {
			logger.debug("showStudentAttendance()In BackOfficeController.java: calling fetchclassDetails() in BackOfficeService.java");
			listTerm = backOfficeService.listTermDetails();
			if (listTerm != null) {
				model.addAttribute("listTerm", listTerm);
			}
			specialHolidays = backOfficeService.listSpecialHolidays();
			if (specialHolidays != null) {
				model.addAttribute("specialHolidays", specialHolidays);
			}
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
			}
			AcademicYear currentYear = commonService.getCurrentAcademicYear();
			if (currentYear != null) {
				model.addAttribute("currentYear", currentYear);
			}
			List<JobType> jobType= erpService.getAllJobType();
			model.addAttribute("jobType",jobType);
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
				List<Course>courseList = commonService.getCourseList();
				model.addAttribute("courseList", courseList);
		} catch (NullPointerException e) {
			logger.error("teacherAttendance() In BackOfficeController.java: NullPointerException"+ e);
		} catch (Exception e) {
			logger.error("teacherAttendance() In BackOfficeController.java: Exception" + e);
		}
		return new ModelAndView("backoffice/teacherAttendance");
	}
	
	
	@RequestMapping(value = "/getWorkShiftForAttendance", method = RequestMethod.GET)
	public @ResponseBody
	String getWorkShiftForAttendance() {
		logger.info("In getTeachingLevelForAttendance() method ");
		String workShift = backOfficeService.getWorkShiftForAttendance();		
		return (new Gson().toJson(workShift));
	}
	
	
	@RequestMapping(value = "/getTeacherListForAttendance", method = RequestMethod.GET)
	public @ResponseBody
	String getTeacherListForAttendance(
			@RequestParam("resourceTypeSelected") String resourceTypeStr,
			StudentAttendance studentAttendance,ResourceType resourceType,WorkShift workShift) {
		String showTeachingDetails = "";		
		try {
			logger.info("In getTeachingLevelForAttendance() method ");
			resourceType.setResourceTypeName(resourceTypeStr);
			studentAttendance.setResourceType(resourceType);
			showTeachingDetails = backOfficeService.getTeacherDetailsForAttendance(studentAttendance);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getTeacherDetailsForAttendance() method for NullPointerException: ", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getTeacherDetailsForAttendance() method for Exception: ", e);
		}
		return (new Gson().toJson(showTeachingDetails));
	}
	
	
	@RequestMapping(value = "/getTeacherAttendance", method = RequestMethod.GET)
	public @ResponseBody
	String getTeacherAttendance(
		//	@RequestParam("shift") String strshift,
			@RequestParam("year") String selectedyear,
			@RequestParam("month") String selectedmonth,
		//	@RequestParam("resourceTypeSelected") String resourceTypeStr,
			@RequestParam("resourceIdSelected") String resourceIdSelectedStr,
			ResourceAttendance resourceAttendance,ResourceType resourceType,WorkShift workShift) {
		String showInsertedTeacher = null;		
		try {
			logger.info("In getTeachingLevelForAttendance() method ");
			resourceAttendance.setYear(selectedyear);
			resourceAttendance.setMonth(selectedmonth);
		//	resourceType.setResourceTypeName(resourceTypeStr);
			resourceAttendance.setResourceType(resourceType);
		//	workShift.setWorkShiftCode(strshift);
			resourceAttendance.setWorkShift(workShift);
			resourceAttendance.setResourceId(resourceIdSelectedStr);
			showInsertedTeacher = backOfficeService.fetchTeacherAttendance(resourceAttendance);
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController fetchTeacherAttendance(studentAttendance) method for NullPointerException: ", e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController fetchTeacherAttendance(studentAttendance) method for Exception: ", e);
		}
		return (new Gson().toJson(showInsertedTeacher));
	}
	
	/**
	 * modified by sourav.bhadra
	 * changes taken on 21042017**/
	
	
	@RequestMapping(value = "/teacherAttendanceUpload", method = RequestMethod.POST)
	public ModelAndView teacherAttendanceUpload(HttpServletRequest request,HttpServletResponse response,
												@ModelAttribute("uploadFile") UploadFile uploadFile,
												ModelMap model,StudentResult studentResult,
												@RequestParam("year") String year,
												@RequestParam("month") String month,
												@RequestParam("jobType") String jobType,
												@ModelAttribute("sessionObject") SessionObject sessionObject){
		
		try{
			/*********code for configure ticket for upload teacher attendance xls file****************/
			ServiceType attendanceService = new ServiceType();
			attendanceService.setTicketServiceCode("JOB-19");
			Ticket ticketForTeacherAttendance = new Ticket();
			ticketForTeacherAttendance.setReportedBy(sessionObject.getUserId());
			ticketForTeacherAttendance.setUpdatedBy(sessionObject.getUserId());
			ticketForTeacherAttendance.setStatus("OPEN");
			ticketForTeacherAttendance.setTicketSummary("Teacher Attendance");
			ticketForTeacherAttendance.setDescription("Teacher Attendance");
			ticketForTeacherAttendance.setTicketService(attendanceService);
			
			/**new code for save attendance in external folder*/
			
			FileUploadDownload util = new FileUploadDownload();
			String path=year+"/"+month+"/"+jobType+"/";
		
			String file= "FILE";
			uploadFile.setDocumentName(uploadFile.getName());
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			File directory = new File(repository);
			boolean isExists = directory.exists();
			String insertStatus = null;
			if(isExists == true){
				String returnMessage = util.uploadExcel1(uploadFile,repository+"/"+excelUploadfolderteacher+path,file);
				String actualFolderPathForUpload = repository+"/"+excelUploadfolderteacher+resourceAttendanceExcel;
				if(returnMessage==null){
					model.addAttribute("returnMessage","error");
					model.addAttribute("uploadErrorMessage", "File"+" upload failed.");
				}else{
					model.addAttribute("returnMessage", "success");
					model.addAttribute("uploadErrorMessage", "File"+" sucessfully uploaded.");
					String updatedBy = sessionObject.getUserId();
					int resourceAttendanceFlag = backOfficeService.uploadResourceAttendance(actualFolderPathForUpload,updatedBy);
					
					/*ticket and notification creation for upload teacher attendance xls file*/
					ticketForTeacherAttendance = commonService.generateTicket(ticketForTeacherAttendance);
				}
			}else{
				System.out.println("directory not found.");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in resourceAttendanceUpload() for upload Template Excel", e);
		}
	return teacherAttendance(request,response,model);
	}
	
	
	
	/**
	 * for library policy*/
	
	@RequestMapping(value = "/libraryPolicy", method = RequestMethod.GET)
	public ModelAndView libraryPolicy(ModelMap model) {
		String strView = null;
		try {
			logger.info("In BackOfficeController libraryPolicy() method: calling saveLibraryPolicy() in BackOfficeService.java");
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
				strView = "backoffice/libraryPolicy";
			}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController libraryPolicy() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController libraryPolicy() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/getlibraryToShow", method = RequestMethod.GET)
	public @ResponseBody
	String showLibraryPolicy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			LibraryPolicy libraryPolicy,
			@RequestParam("resourceTypeSelect") String resourceTypeStr) {
		String finaldata = null;
		String libraryPolicyData = null;
		String lendingRating = null;
		try {
			logger.info("In BackOfficeController saveLibraryPolicy() method: calling saveLibraryPolicy() in BackOfficeService.java");
			ResourceType resourceType = new ResourceType();
			resourceType.setResourceTypeName(resourceTypeStr);
			libraryPolicy.setResourceType(resourceType);
			libraryPolicy = backOfficeService.showLibraryPolicy(libraryPolicy);
			StringBuilder sb = new StringBuilder();
			StringBuilder sb1 = new StringBuilder();
			if(libraryPolicy != null){
				sb.append(libraryPolicy.getMaxNoOfBookReq() + "#");
				sb.append(libraryPolicy.getMaxNoOfBooksPerReq() + "#");
				sb.append(libraryPolicy.getSameBookAcrossReq() + "#");
				sb.append(libraryPolicy.getFinePerDay() + "#");
				sb.append(libraryPolicy.getMaxFine() + "#");
				sb.append(libraryPolicy.getOverDueFine() + "#");
				sb.append(libraryPolicy.getProcessingFee() + "#");
				sb.append(libraryPolicy.getMaxNoOfBookAllocated() + "#");
				libraryPolicyData = sb.toString().substring(0, sb.toString().length() - 1);
				List<Rating> ratingList = libraryPolicy.getRatingList();
				if(ratingList != null){
				for (Rating ratingDetails : ratingList) {
					sb1.append(ratingDetails.getRating() + "%%");
					sb1.append(ratingDetails.getMaxLendingPeriod() + "$");
					lendingRating = sb1.toString().substring(0, sb1.toString().length() - 1);
				}
				}
				finaldata = libraryPolicyData + "^" + lendingRating;
			}
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(finaldata));
	}
	
	
	
	@RequestMapping(value = "/saveLibraryPolicy", method = RequestMethod.POST)
	public ModelAndView saveLibraryPolicy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			LibraryPolicy libraryPolicy,
			@RequestParam("rating") String[] rating,
			@RequestParam("maxLendingPeriod") String[] maxLendingPeriod,
			@RequestParam("resourceTypeSelect") String resourceTypeStr,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		try {
			logger.info("In BackOfficeController saveLibraryPolicy() method: calling saveLibraryPolicy() in BackOfficeService.java");
			List<Rating> ratingList = new ArrayList<Rating>();
			ResourceType resourceType = new ResourceType();
			resourceType.setResourceTypeName(resourceTypeStr);
			for (int i = 0; i < rating.length; i++) {
				Rating r = new Rating();
				r.setResourceType(resourceType);
				r.setRating(Integer.parseInt(rating[i].trim()));
				r.setMaxLendingPeriod(Integer.parseInt(maxLendingPeriod[i].trim()));
				r.setUpdatedBy(sessionObject.getUserId());
				ratingList.add(r);
			}
			libraryPolicy.setResourceType(resourceType);
			libraryPolicy.setRatingList(ratingList);
			libraryPolicy.setUpdatedBy(sessionObject.getUserId());
			libraryPolicy = backOfficeService.saveLibraryPolicy(libraryPolicy);
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
				strView = "backoffice/libraryPolicy";
			}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController saveLibraryPolicy() method for NullPointerException: ",e);
		} catch (NumberFormatException e) {
			logger.error("Error in BackOfficeController saveLibraryPolicy() method for NumberFormatException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController saveLibraryPolicy() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}
	
	
	
	@RequestMapping(value = "/ratingPolicy", method = RequestMethod.GET)
	public ModelAndView ratingPolicy(ModelMap model) {
		String strView = null;
		Rating r = new Rating();
		try {
			logger.info("In BackOfficeController ratingPolicy() method: calling ratingPolicy() in BackOfficeService.java");
			List<AcademicYear> academicYear = backOfficeService.getCurrentAcademicYear();
			logger.info("In BackOfficeController ratingPolicy() method: calling getAcademicYear() in BackOfficeService.java");
			if (academicYear != null && academicYear.size() != 0) {
				model.addAttribute("currentAcademicYear", academicYear);
			}
			for(AcademicYear academicYearDetails : academicYear){
				r.setAcademicYear(academicYearDetails.getAcademicYearCode());
			}
			List<Rating> ratingList = backOfficeService.getRatingPolicy(r);
			if (ratingList != null && ratingList.size() != 0) {
				model.addAttribute("ratingList", ratingList);
				strView = "backoffice/ratingPolicy";
			} else {
				strView = "common/errorpage";
			}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController libraryPolicy() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController libraryPolicy() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}
	
	
	@RequestMapping(value = "/saveRatingPolicy", method = RequestMethod.POST)
	public ModelAndView saveRatingPolicy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("rating") String[] rating,
			@RequestParam("minLendingsTo") String[] minLendingsTo,
			@RequestParam("minLendingsFrom") String[] minLendingsFrom,
			@RequestParam("academicYear") String[] academicYearStr,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//System.out.println("rating in controller::"+rating);
		String strView = null;
		List<Rating> ratingList = new ArrayList<Rating>();
		try {
			logger.info("In BackOfficeController saveRatingPolicy() method: calling saveRatingPolicy() in BackOfficeService.java");
			for (int i = 0; i < rating.length; i++) {
				Rating r = new Rating();
				r.setAcademicYear(academicYearStr[0]);
				r.setRating(Integer.parseInt(rating[i]));
				r.setMinLendingsTo(Integer.parseInt(minLendingsTo[i]));
				r.setMinLendingsFrom(Integer.parseInt(minLendingsFrom[i]));
				r.setUpdatedBy(sessionObject.getUserId());
				ratingList.add(r);
			}
			List<AcademicYear> academicYear = backOfficeService.getCurrentAcademicYear();
			logger.info("In BackOfficeController ratingPolicy() method: calling getAcademicYear() in BackOfficeService.java");
			if (academicYear != null && academicYear.size() != 0) {
				model.addAttribute("currentAcademicYear", academicYear);
			}
			ratingList = backOfficeService.saveRatingPolicy(ratingList);
			if (ratingList != null && ratingList.size() != 0) {
				model.addAttribute("ratingList", ratingList);
				strView = "backoffice/ratingPolicy";
			} else {
				strView = "common/errorpage";
			}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController saveRatingPolicy() method for NullPointerException: ",e);
		} catch (NumberFormatException e) {
			logger.error("Error in BackOfficeController saveRatingPolicy() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController saveRatingPolicy() method for NumberFormatException: ",e);
		}
		return new ModelAndView(strView);
	}
	
	
	@RequestMapping(value = "/vendorRatingPolicy", method = RequestMethod.GET)
	public ModelAndView vendorRatingPolicy(	HttpServletRequest request,
											HttpServletResponse response, ModelMap model) {
		logger.info("In vendorRatingPolicy() method of AdminController");
		ModelAndView mav = new ModelAndView("backoffice/vendorRating");
		try {
			List<VendorType> vendorTypeList = backOfficeService.getVendorTypes();
			if (vendorTypeList != null) {
				mav.addObject("vendorTypeList", vendorTypeList);
			}
		} catch (Exception e) {
			logger.error("vendorRatingPolicy() In AdminController.java: Exception" + e);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/getVendorPloicyToShow", method = RequestMethod.GET)
	public @ResponseBody
	String getVendorPolicyToShow(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			VendorRatingPolicy vendorRatingPolicy,
			@RequestParam("vendorTypeSelect") String vendorTypeStr) {
		String vendorPolicyData = null;
		try {
			logger.info("In BackOfficeController getVendorPolicyToShow() method: calling showVendorPolicy() in BackOfficeService.java");
			VendorType vendorType = new VendorType();
			vendorType.setVendorTypeCode(vendorTypeStr);
			vendorRatingPolicy.setVendorType(vendorType);
			vendorRatingPolicy = backOfficeService.showVendorPolicy(vendorRatingPolicy);
			StringBuilder sb = new StringBuilder();

			if(vendorRatingPolicy != null){
				sb.append(vendorRatingPolicy.getMaxSupplyDay() + "^");
				sb.append(vendorRatingPolicy.getMaxNoDeffects() + "^");
				vendorPolicyData = sb.toString().substring(0, sb.toString().length() - 1);	
			}
			
		} catch (NullPointerException e) {
			logger.error(
					"Error in BackOfficeController getVendorPolicyToShow() method for NullPointerException: ",
					e);
		} catch (NumberFormatException e) {
			logger.error(
					"Error in BackOfficeController getVendorPolicyToShow() method for NumberFormatException: ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Error in BackOfficeController getVendorPolicyToShow() method for Exception: ",
					e);
		}
		return (new Gson().toJson(vendorPolicyData));
	}
	
	
	@RequestMapping(value = "/addVendorRating", method = RequestMethod.POST)
	public ModelAndView saveVendorPolicy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			VendorRatingPolicy vendorRatingPolicy,
			@RequestParam("vendorTypeSelect") String vendorTypeStr,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		try {
			logger.info("In BackOfficeController saveVendorPolicy() method: calling saveVendorPolicy() in BackOfficeService.java");
			VendorType vendorType = new VendorType();
			vendorType.setVendorTypeCode(vendorTypeStr);
			vendorRatingPolicy.setVendorType(vendorType);
			vendorRatingPolicy.setUpdatedBy(sessionObject.getUserId());
			vendorRatingPolicy = backOfficeService.saveVendorPolicy(vendorRatingPolicy);
			List<VendorType> vendorTypeList = backOfficeService.getVendorTypes();
			if (vendorTypeList != null) {
				model.addAttribute("vendorTypeList", vendorTypeList);
			}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController saveVendorPolicy() method for NullPointerException: ",e);
		} catch (NumberFormatException e) {
			logger.error("Error in BackOfficeController saveVendorPolicy() method for NumberFormatException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController saveVendorPolicy() method for Exception: ",e);
		}
		return new ModelAndView("backoffice/vendorRating");
	}
	
	
	@RequestMapping(value = "/attendancePolicy", method = RequestMethod.GET)
	public ModelAndView attendancePolicy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("In paymentPolicy() method of AdminController");
		ModelAndView mav = new ModelAndView("backoffice/attendancePolicy");
		try {
			AttendancePolicy attendancePolicy = backOfficeService.getAttendancePloicy();
			if (attendancePolicy != null) {
				mav.addObject("attendancePolicy", attendancePolicy);
			}
		} catch (Exception e) {
			logger.error("paymentPolicy() In AdminController.java: Exception" + e);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/saveAttendancePolicy", method = RequestMethod.POST)
	public ModelAndView saveAttendancePolicy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AttendancePolicy attendancePolicy,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In BackOfficeController saveExamPolicy() method: calling saveVendorPolicy() in BackOfficeService.java");
			attendancePolicy.setUpdatedBy(sessionObject.getUserId());
			backOfficeService.saveAttendancePolicy(attendancePolicy);
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController saveExamPolicy() method for NullPointerException: ",e);
		} catch (NumberFormatException e) {
			logger.error("Error in BackOfficeController saveExamPolicy() method for NumberFormatException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController saveExamPolicy() method for Exception: ",e);
		}
		return attendancePolicy(request,response,model);
	}
	
	
	@RequestMapping(value = "/examPolicy", method = RequestMethod.GET)
	public ModelAndView examPolicy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("In paymentPolicy() method of AdminController");
		ModelAndView mav = new ModelAndView("backoffice/examPolicy");
		try {
			Exam examPolicy = backOfficeService.getExamPloicy();
			if (examPolicy != null) {
				mav.addObject("examPolicy", examPolicy);
			}
		} catch (Exception e) {
			logger.error("paymentPolicy() In AdminController.java: Exception" + e);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/saveExamPolicy", method = RequestMethod.POST)
	public ModelAndView saveExamPolicy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Exam exam,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//System.out.println("change day::"+exam.getChangeDay());
		try {
			logger.info("In BackOfficeController saveExamPolicy() method: calling saveVendorPolicy() in BackOfficeService.java");
			exam.setUpdatedBy(sessionObject.getUserId());
			backOfficeService.saveExamPolicy(exam);
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController saveExamPolicy() method for NullPointerException: ",e);
		} catch (NumberFormatException e) {
			logger.error("Error in BackOfficeController saveExamPolicy() method for NumberFormatException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController saveExamPolicy() method for Exception: ",e);
		}
		return examPolicy(request,response,model);
	}
	
	
	@RequestMapping(value = "/generateQRCode", method = RequestMethod.GET)
	public String generateQRCode(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {		
		return "backoffice/generateQRCode";
	}
	
	@RequestMapping(value = "/qrcodeForTeacher", method = RequestMethod.GET)
	public String qrcodeForTeacher(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {		
		return "backoffice/qrcodeForTeacher";
	}
	
	@RequestMapping(value = "/submitTeacherAttributeForQRCode", method = RequestMethod.POST)
	public String submitTeacherAttributeForQRCode(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
			String columnForTeacher [] = request.getParameterValues("attributeColumn");
			List<String> attributeColumnForTeacher = Arrays.asList(columnForTeacher);
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			File directory = new File(repository);
			boolean isExists = directory.exists();
			if(isExists == true){
				String path= repository +"\\"+ teacherQrCodePath;
				backOfficeService.generateQrCodeForTeacher(attributeColumnForTeacher, path);
			}
		return "backoffice/qrcodeForTeacher";
	}
	
	
	@RequestMapping(value = "/qrcodeForStudent", method = RequestMethod.GET)
	public String qrcodeForStudent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {		
		return "backoffice/qrcodeForStudent";
	}
	
	@RequestMapping(value = "/submitStudentAttributeForQRCode", method = RequestMethod.POST)
	public String submitStudentAttributeForQRCode(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String columnForBook [] = request.getParameterValues("attributeColumn");
		List<String> attributeColumnForBook = Arrays.asList(columnForBook);
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		File directory = new File(repository);
		boolean isExists = directory.exists();
		if(isExists == true){
			String path= repository +"\\"+ studentQrCodePath;		
			backOfficeService.generateQrCodeForStudent(attributeColumnForBook, path);
		}	
		return "backoffice/qrcodeForStudent";
	}
	
	@RequestMapping(value = "/qrcodeForBook", method = RequestMethod.GET)
	public String qrcodeForBook(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {		
		return "backoffice/qrcodeForBook";
	}
	
	@RequestMapping(value = "/submitBookAttributeForQRCode", method = RequestMethod.POST)
	public String submitBookAttributeForQRCode(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {	
			String columnForBook [] = request.getParameterValues("attributeColumn");			
			List<String> attributeColumnForBook = Arrays.asList(columnForBook);
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			File directory = new File(repository);
			boolean isExists = directory.exists();
			if(isExists == true){
				String path= repository +"\\"+ bookQrCodePath;
				backOfficeService.generateQrCodeForBook(attributeColumnForBook, path);
			}
		return "backoffice/qrcodeForBook";
	}
	
	
	@RequestMapping(value = "/createResult", method = RequestMethod.GET)
	public ModelAndView createResult(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("link") String link) {
		String strView = null;
		try {
			logger.info("In BackOfficeController uploadResult() method: calling getClassList() in BackOfficeService.java");
			AcademicYear academicYear = backOfficeService.getAcademicYearClassAndExamType();
			if (academicYear != null) {
				model.addAttribute("academicYear", academicYear);
				if (link.trim().equalsIgnoreCase("createResult"))
					strView = "backoffice/createResult";
				else if(link.trim().equalsIgnoreCase("listResultNew"))
					strView = "backoffice/listResultNew";
				else
					strView = "backoffice/qrcodeForHallPass";
			} else {
				model.addAttribute("ResultError", "Result not available....");
				strView = "common/errorpage";
			}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController createResult() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController createResult() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}
	
	
	@RequestMapping(value = "/getCourseInClass", method = RequestMethod.GET)
	public @ResponseBody
	String getCourseInClass(@RequestParam("class") String classCode) {
		String sm="";
		try{
			sm = backOfficeService.getCourseInClass(classCode);	
		}catch(Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(sm));
	}
	
	
	@RequestMapping(value = "/getTermForCourseForUnitCriteria", method = RequestMethod.GET)
	public @ResponseBody
	String getTermForCourse(@RequestParam("course") String courseCode) {
		String sm="";
		try{
			sm = backOfficeService.getTermForCourse(courseCode);			
			
		}catch(Exception e){
			e.printStackTrace();	
		}
		return (new Gson().toJson(sm));
	}
	
	@RequestMapping(value = "/getExamsForTermCourseAndExamType", method = RequestMethod.GET)
	public @ResponseBody
	String getExamsForTermCourseAndExamType(
			@RequestParam("courseCode") String courseCode,
			@RequestParam("classCode") String classCode, Exam ex) {
		String str = "";
		try {
			ex.setCourseCode(courseCode);
			ex.setStandardCode(classCode);
			str = backOfficeService.getExamsForTermCourseAndExamType(ex);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getExamsForTermCourseAndExamType() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getExamsForTermCourseAndExamType() method for Exception: ",e);
		}
		return (new Gson().toJson(str));
	}
	
	
	@RequestMapping(value = "/getSectionForClassAndStream", method = RequestMethod.GET)
	public @ResponseBody
	String getSectionForClassAndStream(
			@RequestParam("classCode") String classCode,
			//@RequestParam("streamCode") String streamCode, 
			Class klass) {
		String str = "";
		try {
			klass.setClassCode(classCode);
			//klass.setCriteria(streamCode);
			str = backOfficeService.getSectionForClassAndStream(klass);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getSectionForClassAndStream() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getSectionForClassAndStream() method for Exception: ",e);
		}
		return (new Gson().toJson(str));
	}
	
	
	@RequestMapping(value = "/getStudentForClassStreamSectionAndCourse", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentForClassStreamSectionAndCourse(
			@RequestParam("classCode") String classCode,
			@RequestParam("sectionCode") String sectionCode,
			@RequestParam("courseCode") String courseCode
			) {
		String str = "";
		try {
			Resource resource = new Resource();
			resource.setCode(courseCode);
			resource.setKlass(classCode);
			Section sec = new Section();
			sec.setSectionCode(sectionCode);
			resource.setSection(sec);
			str = backOfficeService.getStudentForClassStreamSectionAndCourse(resource);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStudentForClassStreamSectionAndCourse() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStudentForClassStreamSectionAndCourse() method for Exception: ",e);
		}
		return (new Gson().toJson(str));
	}
	
	
	@RequestMapping(value = "/submitHallPassAttributeForQRCode", method = RequestMethod.POST)
	public ModelAndView submitHallPassAttributeForQRCode(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
			List<QRCodeForHallPass> qRCodeForHallPassList=new ArrayList<QRCodeForHallPass>();
			String examSession=request.getParameter("year");
			String className=request.getParameter("className");
			String courseName=request.getParameter("courseName");
			String term=request.getParameter("term");
			String section=request.getParameter("section");
			String examName=request.getParameter("examName");
			
			String studentList [] = request.getParameterValues("StudentID");
			List<String> attributeColumnForHallPass = Arrays.asList(studentList);
			for(String stud :attributeColumnForHallPass){
				String name = request.getParameter(stud);
				String finalString="Roll Number : "+stud+"; Name : "+name+
						"; Year : "+examSession+"; Class : "+className+
						"; Course : "+courseName+"; Term : "+term+"; Section : "+section+"; Exam : "+examName;
				
				QRCodeForHallPass qRCodeForHallPass=new QRCodeForHallPass();
				qRCodeForHallPass.setRollNumber(stud);
				qRCodeForHallPass.setTotalString(finalString);
				qRCodeForHallPassList.add(qRCodeForHallPass);
			}
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			File directory = new File(repository);
			boolean isExists = directory.exists();
			if(isExists == true){
				String path= repository +"\\"+ hallPassQrCodePath;
				backOfficeService.generateQrCodeForHallPass(qRCodeForHallPassList, path);
			}
			return createResult(request, response, model, "qrcodeForHallPass");
	}
	
	
	@RequestMapping(value = "/readQRCode", method = RequestMethod.GET)
	public String readQRCode(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		File directory = new File(repository);
		boolean isExists = directory.exists();
		if(isExists == true){
			String path= repository +"\\"+bookQrCodePath;
			model.addAttribute("path",path);
		}
		return "backoffice/readQRCode";
	}
	
	@RequestMapping(value = "/getBookIdForQrCode", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getBookIdForQrCode(@RequestParam("term") String strQuery) {
		List<String> bookIdList = null;
		try {
			logger.info("getBookName()In LibraryController.java: calling getBookName( String strQuery) in BackOfficeService.java");
			bookIdList = backOfficeService.getBookIdForQrCode(strQuery);
		} catch (Exception e) {
			logger.error("getBookName() In LibraryController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(bookIdList));
	}
	
	@RequestMapping(value = "/getQRCode", method = RequestMethod.POST)
	public String getQRCode(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		String path=request.getParameter("path");
		System.out.println("path got::>>"+path);
		path=path.replace("\\", "/");
		System.out.println("replaced path::>>"+path);
		model.addAttribute("path",path);
		
//		String fullPath=servletContext.getRealPath("/") +path;
		String fullPath=path;
		String charset = "UTF-8"; // or "ISO-8859-1"
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		Utility u=new Utility();
		String imageData="";
			try {
				//System.out.println("5292 in backofcCotroller::fullPath::>>"+fullPath);
				imageData = u.readQRCode(fullPath, charset, hintMap);
			} catch (NotFoundException | IOException e) {
				e.printStackTrace();
			}
		model.addAttribute("imageData",imageData);
		
		/*String imageFileName = request.getParameter("fileName");
		model.addAttribute("imageFileName", imageFileName);
		String imagePath = null;
		try {
			File image = new File(imagePath, URLDecoder.decode(path, "UTF-8"));
			String contentType = request.getServletContext().getMimeType(image.getName());
			response.setContentType(contentType);
			Files.copy(image.toPath(), response.getOutputStream());
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
		
		return "backoffice/displayQRCode";
	}
	
	
	/**
	 * assign TC*/
	
	@RequestMapping(value = "/assignTC", method = RequestMethod.GET)
	public ModelAndView assignTC(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = null;
		try {
			logger.info("In BackOfficeController assignTC() method: calling assignTC() in BackOfficeService.java");

			AcademicYear aY = backOfficeService.selectCurrentAcademicYear();
			if (aY != null) {
				model.addAttribute("aY", aY);
			}
			List<Class> classList = backOfficeService.getClassNameAndCodeList();
			if (classList != null) {
				model.addAttribute("classList", classList);
				strView = "backoffice/assignTC";
			} else {
				logger.info("In receiveSessionFee method of BackOffice Controller :: Error :: Class List not Found ");
				System.out
						.println("In receiveSessionFee method of BackOffice Controller :: Error :: Class List not Found ");
				strView = "common/errorpage";
			}

		} catch (NullPointerException e) {
			logger.error(
					"Error in BackOfficeController assignTC() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			logger.error(
					"Error in BackOfficeController assignTC() method for Exception: ",
					e);
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/getSectionAgainstClassAndStream", method = RequestMethod.GET)
	public @ResponseBody
	String getSectionAgainstClassAndStream(
			@RequestParam("class") String classCode,
			@RequestParam("stream") String streamCode) {
		String streamList = "";
		try {
			logger.info("In BackOfficeController getStudentsResult() method: calling getStudentsResult() in BackOfficeService.java");
			//System.out.println(""+classCode+"@@@ @@@"+streamCode);
			
			Section section = new Section();
			section.setKlass(classCode);
			//section.setStream(streamCode);
			streamList = backOfficeService.getSectionAgainstClassAndStream(section);

		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStudentsResult() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStudentsResult() method for Exception: ",e);
		}
		return (new Gson().toJson(streamList));
	}
	
	@RequestMapping(value = "/getResourceAgainstSection", method = RequestMethod.GET)
	public @ResponseBody
	String getResourceAgainstSection(
			@RequestParam("sectionCode") Section sectionCode,
			@RequestParam("course") String course) {
		String resourceList = "";
		try {
			logger.info("In BackOfficeController getStudentsResult() method: calling getStudentsResult() in BackOfficeService.java");
			resourceList = backOfficeService.getResourceAgainstSection(sectionCode,course);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error(
					"Error in BackOfficeController getStudentsResult() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Error in BackOfficeController getStudentsResult() method for Exception: ",
					e);
		}
		return (new Gson().toJson(resourceList));
	}
	
	
	@RequestMapping(value = "/checkWheatherFeesPaid", method = RequestMethod.GET)
	public @ResponseBody
	String checkWheatherFeesPaid(@RequestParam("student") String regID,
			@RequestParam("session") String sessionCode) {
		String str = "";
		try {
			SessionFees sf = new SessionFees();
			sf.setRegistrationId(regID);
			sf.setAcademicSsession(sessionCode);
			str = backOfficeService.checkWheatherFeesPaid(sf);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStudentsResult() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStudentsResult() method for Exception: ",e);
		}
		return (new Gson().toJson(str));
	}
	
	@RequestMapping(value = "/grantTC", method = RequestMethod.POST)
	public ModelAndView grantTC(HttpServletRequest request,
								HttpServletResponse response,
							   ModelMap model,
							   StudentTc studentTc,
							   @ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			studentTc.setUpdatedBy(sessionObject.getUserId());
			if(studentTc.getTcCause().equalsIgnoreCase("diciplinary"))
				studentTc.setTcCause("Diciplinary Action");
			else{
				studentTc.setTcCause("Non-Diciplinary Action");
			}
			String status = backOfficeService.grantTC(studentTc);
		}catch(Exception e){
			e.printStackTrace();
		}
	return assignTC(request,response,model);
	}
	
	
	@RequestMapping(value = "/getDate", method = RequestMethod.GET)
	public @ResponseBody
	String getDate(@RequestParam("sterm") String termid) {
		logger.info("In getVendorList() method of AdminController");
		logger.info("In BackOfficeController getDate() method: calling getTermDate(String termid) in BackOfficeService.java");
		String showDate = backOfficeService.getTermDate(termid);
		return (new Gson().toJson(showDate));
	}
	
	
	@RequestMapping(value = "/getTermForCourse", method = RequestMethod.GET)
	public @ResponseBody
	String getTermListForCourse(@RequestParam("course") String courseCode) {
		logger.info("In getVendorList() method of AdminController");
		logger.info("In BackOfficeController getDate() method: calling getTermDate(String termid) in BackOfficeService.java");
		String showTerm = backOfficeService.getTermList(courseCode);
		return (new Gson().toJson(showTerm));
	}
	
	
	@RequestMapping(value = "/getScholarship", method = RequestMethod.GET)
	public String getScholarship(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createScholarship";
		try {
			logger.info("Inside Method getScholarship-GET of BackOfficeController");
			List<Scholarship> scholarshipList=backOfficeService.getScholarshipList();
			model.addAttribute("scholarshipList", scholarshipList);
		} catch (CustomException ce){
			logger.error("Exception in method getScholarship-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/addScholarship", method = RequestMethod.POST)
	public String addScholarship(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Scholarship scholarship,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method addScholarship-POST of BackOfficeController");
			if(null!=scholarship && null!=scholarship.getScholarshipName() && scholarship.getScholarshipName().trim().length()!=0){
				scholarship.setScholarshipName(scholarship.getScholarshipName().trim().toUpperCase());
				scholarship.setScholarshipCode(scholarship.getScholarshipName().trim().toUpperCase());
				scholarship.setDesc(scholarship.getScholarshipName().trim());
				scholarship.setUpdatedBy(sessionObject.getUserId());
				
				String insertStatus=backOfficeService.addScholarship(scholarship);
				model.addAttribute("insertUpdateStatus", insertStatus);
			}else{
				logger.info("Invalid Scholarship Name.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method addScholarship-POST of BackOfficeController", ce);
		}
		return getScholarship(request, response, model);
	}
	
	
	@RequestMapping(value = "/editScholarship", method = RequestMethod.POST)
	public String editScholarship(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editScholarship-POST of BackOfficeController");
					Scholarship scholarship=new Scholarship();
					String saveId = request.getParameter("saveId");
					scholarship.setDesc((request.getParameter("Name"+saveId)).trim().toUpperCase());
					scholarship.setScholarshipName((request.getParameter("Name"+saveId)).trim().toUpperCase());
					scholarship.setScholarshipCode(request.getParameter("oldScholarshipCode"+saveId));
					scholarship.setConcession(Double.parseDouble(request.getParameter("Concession"+saveId)));
					scholarship.setConcessionUnit((request.getParameter("Unit"+saveId)).trim());
					scholarship.setUpdatedBy(sessionObject.getUserId());

					String updateStatus=backOfficeService.editScholarship(scholarship);
					model.addAttribute("insertUpdateStatus", updateStatus);
					/**Added by @author Saif.Ali
					 * Date- 20/03/2018
					 * Used to insert the information into the activity_log table*/
					String oldScholarshipName = request.getParameter("oldScholarshipNames");	// old Scholarship name value
					String newScholarshipName = (request.getParameter("Name"+saveId)).trim();// new Scholarship name value
					double oldConcessionAmount = Double.parseDouble(request.getParameter("oldConcessionAmount"+saveId));// old Concession Amount
					double newConcessionAmount = Double.parseDouble(request.getParameter("Concession"+saveId));// new Concession Amount
					String oldConcessionUnit = request.getParameter("oldConcessionUnit"+saveId);	//Old Concession Unit
					String newConcessionUnit = (request.getParameter("Unit"+saveId)).trim();	//new Concession Unit
					
					if(updateStatus.equalsIgnoreCase("Update Successful")){
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("EDIT SCHOLARSHIP DETAILS");
						updateLog.setInsertUpdate("UPDATE");
						updateLog.setModule("OFFICE ADMINISTRATION");
						updateLog.setUpdatedFor(oldScholarshipName);
						String description = "";
						if(!(oldScholarshipName.equalsIgnoreCase(newScholarshipName)))
						{
							description=description + ("Scholarship Name :: " + oldScholarshipName + " updated to new Scholarship Name :: " + newScholarshipName + " ; ");
						}
						if(oldConcessionAmount != newConcessionAmount)
						{
							description=description +  ("Concession Amount ::" + oldConcessionAmount + " updated to new Concession Amount::" + newConcessionAmount+ " ; ");
						}
						if(!(oldConcessionUnit.equalsIgnoreCase(newConcessionUnit)))
						{
							description=description + ("Concession Unit :: " + oldConcessionUnit + " updated to new Concession Unit :: " + newConcessionUnit + " ; ");
						}
						updateLog.setDescription(description);
						String response_update=commonService.insertIntoActivityLog(updateLog);
					}
					/**Addition ends*/
				
			
		} catch (CustomException ce){
			ce.printStackTrace();
		}
		return getScholarship(request, response, model);
	}
	
	
	@RequestMapping(value = "/createAcademicTerm", method = RequestMethod.GET)
	public ModelAndView createAcademicTerm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = null;
		try {
			logger.info("In BackOfficeController createAcademicYear() method: calling getClassList() in BackOfficeService.java");
			List<AcademicYear> academicYear = backOfficeService.getAcademicYearForTerm();
			if (academicYear != null && academicYear.size() != 0) {
				model.addAttribute("AcademicYear", academicYear);
			}
			List<Term> termName = backOfficeService.getTermNameList();
			if (termName != null && termName.size() != 0) {
				model.addAttribute("TermName", termName);
			}
			List<Standard> classNameList = backOfficeService.getclassListForTermCreation();
			if (classNameList != null && classNameList.size() != 0) {
				model.addAttribute("classNameList", classNameList);
			}
			
			strView = "backoffice/createAcademicTerm";
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController createAcademicYear() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController createAcademicYear() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}
	
	
	@RequestMapping(value = "/insertAcademicTerm", method = RequestMethod.POST)
	public ModelAndView insertAcademicTerm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("academicTermType") String[] strAcademicTerm,
			@RequestParam("startDate") String[] strStartDate,
			@RequestParam("endDate") String[] strEndDate, Term term,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		try {
			logger.info("In BackOfficeController createAcademicYear() method: calling getClassList() in BackOfficeService.java");
			String[] individualClass = term.getCourse().getCourseName().split(",");
			for (int j = 0; j < individualClass.length; j++) {
				for (int termtype = 0; termtype < (strAcademicTerm.length); termtype++) {
					if ((strAcademicTerm != null && strAcademicTerm[termtype] != "")) {
						term.setTermName(strAcademicTerm[termtype].toUpperCase());
						term.setTermDesc(strAcademicTerm[termtype].toUpperCase());
						term.setTermStartDate(strStartDate[termtype]);
						term.setTermEndDate(strEndDate[termtype]);
						term.setUpdatedBy(sessionObject.getUserId());
						Course courseObj = new Course();
						courseObj.setCourseName(individualClass[j]);
						term.setCourse(courseObj);
						backOfficeService.insertAcademicTerm(term);
					}
				}
			}
			logger.info("In BackOfficeController addStudentAttendance() method: calling getTermNameList() in BackOfficeService.java");
			List<Term> termName = backOfficeService.getTermNameList();

			if (termName != null && termName.size() != 0) {
				model.addAttribute("TermName", termName);
			}
			List<AcademicYear> academicYear = backOfficeService.getAcademicYearForTerm();
			logger.info("In BackOfficeController addStudentAttendance() method: calling getAcademicYear() in BackOfficeService.java");
			if (academicYear != null && academicYear.size() != 0) {
				model.addAttribute("AcademicYear", academicYear);
			}
			strView = "backoffice/createAcademicTerm";
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(strView);
	}
	
	
	@RequestMapping(value = "/editAcademicTerm", method = RequestMethod.POST)
	public ModelAndView editAcademicTerm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editAcademicTerm-POST of BackOfficeController");
			Term term = new Term();
			String saveId = request.getParameter("saveId");
			term.setTermDesc((request.getParameter("termType"+saveId)).trim().toUpperCase());
			term.setTermName((request.getParameter("termType"+saveId)).trim().toUpperCase());
			term.setTermDetailsId(Integer.parseInt(request.getParameter("oldTermDetailsCode"+saveId)));
			term.setTermStartDate(request.getParameter("termStartDate"+saveId));
			term.setTermEndDate((request.getParameter("termEndDate"+saveId)));
			term.setUpdatedBy(sessionObject.getUserId());
		
			backOfficeService.editAcademicTermType(term);
				
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return createAcademicTerm(request, response, model);
	}

//	@RequestMapping(value = "/editAcademicTerm", method = RequestMethod.POST)
//	public ModelAndView editAcademicTerm(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model,
//			@RequestParam("hiddenTermType") String streditedleavetermtype,
//			@ModelAttribute("sessionObject") SessionObject sessionObject) {
//		String strView = null;
//		try {
//			String[] arr = streditedleavetermtype.split("/");
//			for (int rownum = 0; rownum < arr.length; rownum++) {
//				String[] arr1 = arr[rownum].split(",");
//				for (int count = 0; count < arr1.length; count++) {
//					if (arr1[count] != "" || arr1[count] != "") {
//						Term term = new Term();
//						if (arr1[0] != "") {
//							int integerId = Integer.parseInt(arr1[0]);
//							term.setTermDetailsId(integerId);
//							term.setTermName(arr1[1].toUpperCase());
//							term.setTermStartDate(arr1[2]);
//							term.setTermEndDate(arr1[3]);
//							term.setAcademicYear(arr1[4]);
//							term.setUpdatedBy(sessionObject.getUserId());
//						}
//						backOfficeService.editAcademicTermType(term);
//					}
//				}
//			}
//			logger.info("In BackOfficeController editAcademicTerm() method: calling getClassList() in BackOfficeService.java");
//			List<AcademicYear> academicYear = backOfficeService.getAcademicYearForTerm();
//			System.out.println("academicYear" + academicYear);
//			logger.info("In BackOfficeController addStudentAttendance() method: calling getTermNameList() in BackOfficeService.java");
//			if (academicYear != null && academicYear.size() != 0) {
//				model.addAttribute("AcademicYear", academicYear);
//			}
//			logger.info("In BackOfficeController addStudentAttendance() method: calling getTermNameList() in BackOfficeService.java");
//			List<Term> termName = backOfficeService.getTermNameList();
//			if (termName != null && termName.size() != 0) {
//				model.addAttribute("TermName", termName);
//			}
//			strView = "backoffice/createAcademicTerm";
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		} catch (ArrayIndexOutOfBoundsException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return new ModelAndView(strView);
//	}
	
	
//	@RequestMapping(value = "/getClassForTermCreation", method = RequestMethod.GET)
//	public @ResponseBody
//	String getClassForTermCreation() {
//		String classes = "";
//		List<Class> classNameList = backOfficeService.getclassListForTermCreation();
//		for (Class className : classNameList) {
//			classes = classes + className.getClassName() + ",";
//		}
//		return (new Gson().toJson(classes));
//	}
	
	
	@RequestMapping(value = "/getCourseForTermCreation", method = RequestMethod.GET)
	public @ResponseBody
	String getCourseForTermCreation(@RequestParam("standard") String strClass) {
		String coursees = "";
		List<Course> CourseList = null;
		CourseList = backOfficeService.getcourseListForTermCreation(strClass);
		for (Course course : CourseList) {
			coursees = coursees + course.getCourseName() + ",";
		}
		return (new Gson().toJson(coursees));
	}
	
	
	///------------------------------------FOR TIME TABLE BY SAIKAT DAS----------------------------------------
	
	@RequestMapping(value = "/configureTimeTable.html", method = RequestMethod.GET)
	public String configureTimeTable(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			
			logger.info("calling configureTimeTable()GET method of BackOfficeController");
			List<TimeTableConfigModel> timeTableList = backOfficeService.getAllTimeTableList();
			if(null!= timeTableList && timeTableList.size()>0){
				model.addAttribute("timeTableList",timeTableList);
			}
			List<Resource> teacherList = backOfficeService.getTeacherList();
			model.addAttribute("teacherList", teacherList);
			List<Standard>standardList = backOfficeService.getStandardList();
			for(Standard std:standardList){
				std.setDesc(std.getStandardName()+std.getSection());
			}
			model.addAttribute("standardList", standardList);
			
			List<Subject>subjectList = backOfficeService.getSubjectList();
			model.addAttribute("subjectList", subjectList);
			
		} catch (Exception e) {
			logger.error("Exception in configureTimeTable() GET method Of BackOfficeController",e);
		}
		return "backoffice/configureTimeTable";
	}
	
	
	@RequestMapping(value = "/saveTimeTableConfiguration", method = RequestMethod.POST)
	public String saveTimeTableConfiguration(@RequestParam("teacherName") String[] strArrTeacherName,
										@RequestParam("classSectionName") String[] strArrClassSectionName,
										@RequestParam("subjectName") String[] strArrSubject,
										@RequestParam("noOfClasses") String[] strArrNoOfClasses,
										HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		
		try {
			logger.info("calling saveTimeTableConfiguration() POST method of BackOfficeController");
			List<TimeTableConfigModel> timeTableConfigModelList = new ArrayList<TimeTableConfigModel>();
			
			if (strArrTeacherName != null && strArrTeacherName.length != 0) {
				for (int i = 0; i < strArrTeacherName.length; i++) {
					TimeTableConfigModel timeTableConfigModel = new TimeTableConfigModel();
					if (strArrTeacherName[i] != null) {
						String tmpArr[] = strArrTeacherName[i].split("-");
						timeTableConfigModel.setTeacherUserId(tmpArr[0]);
						timeTableConfigModel.setTeacherName(tmpArr[1]);
					}
					if (strArrClassSectionName != null) {
						if (strArrClassSectionName[i] != null && strArrClassSectionName[i].trim().length()!=0) {
							timeTableConfigModel.setClassSectionName(strArrClassSectionName[i]);
						}
					}
					if (strArrSubject != null) {
						if (strArrSubject[i] != null) {
							timeTableConfigModel.setSubjectName(strArrSubject[i]);
						}
					}
					if (strArrNoOfClasses != null) {
						if (strArrNoOfClasses[i] != null) {
							timeTableConfigModel.setNoOfClasses(strArrNoOfClasses[i]);
						}
					}
					timeTableConfigModelList.add(timeTableConfigModel);
				}
			}
			
			timeTableConfigModelList = backOfficeService.addTimeTableConfigData(timeTableConfigModelList, sessionObject.getUserId());
//			if (timeTableConfigModelList != null && timeTableConfigModelList.size() != 0) {
//				
//				model.addAttribute("timeTableModelList", timeTableConfigModelList);
//				strView = "backoffice/viewTimeTable";
//			}
//		strView = "backoffice/configureTimeTable";
		} catch (Exception e) {
			logger.error("Exception in saveTimeTableConfiguration() method Of BackOfficeController", e);
			e.printStackTrace();
		}
		return configureTimeTable(request,response,model);
	}
	
	@RequestMapping(value = "/viewTimeTable", method = RequestMethod.GET)
	public ModelAndView viewTimeTable(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("backoffice/viewTimeTable");
		try {
			logger.info("calling viewTimeTable()GET method of BackOfficeController");
			
			List<Resource> teacherList = backOfficeService.getTeacherList();
			model.addAttribute("teacherList", teacherList);
			
		} catch (Exception e) {
			logger.error("Exception in viewTimeTable() GET method Of BackOfficeController",e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/getTeacherListForTimeTable", method = RequestMethod.GET)
	public  @ResponseBody String getTeacherListForTimeTable() {
		
		StringBuffer sb = new StringBuffer();
		try {
			
			List<Resource> teacherList = backOfficeService.getTeacherList();
			
			for(Resource rm : teacherList){
		    	sb.append("<option value =\"" + rm.getUserId() + "-" + rm.getTeacherName() + "\">"+ rm.getTeacherName() + "</option>");
		    }
		    
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method getTeacherListForTimeTable-GET of BackOfficeController", ce);
		}
		return sb.toString();
	}
	
	@RequestMapping(value = "/getClassSectionListForTimeTable", method = RequestMethod.GET)
	public  @ResponseBody String getClassSectionListForTimeTable() {
		
		StringBuffer sb = new StringBuffer();
		try {
			
			List<Standard> classSectionList = backOfficeService.getStandardList();
			
			for(Standard sd : classSectionList){
		    	sb.append("<option value =\"" + sd.getStandardName() + sd.getSection() + "\">"+ sd.getStandardName() + "-" + sd.getSection() + "</option>");
		    }
		    
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method getClassSectionListForTimeTable-GET of BackOfficeController", ce);
		}
		return sb.toString();
	}
	
	@RequestMapping(value = "/getSubjectListForTimeTable", method = RequestMethod.GET)
	public  @ResponseBody String getSubjectListForTimeTable() {
		
		StringBuffer sb = new StringBuffer();
		try {
			
			List<Subject> subjectList = backOfficeService.getSubjectList();
			
			for(Subject sub : subjectList){
		    	sb.append("<option value =\"" + sub.getSubjectName() + "\">"+ sub.getSubjectName() + "</option>");
		    }
		    
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method getSubjectListForTimeTable-GET of BackOfficeController", ce);
		}
		return sb.toString();
	}
	
	@RequestMapping(value = "/getAllTimeTableGridData", method = RequestMethod.GET)
	public  @ResponseBody String getTimeTableGridData() {
		
		StringBuffer sb = new StringBuffer();
		String strGridData = null;
		try {
			
			List<TimeTableGridData> gridDataList = backOfficeService.getAllTimeTableGridData();
			
			for(TimeTableGridData gridData : gridDataList){
		    	sb.append(gridData.getGridId() + "-" + gridData.getGridData() + "|");
		    }
			
			strGridData = sb.toString();
		    
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method getTimeTableGridData-GET of BackOfficeController", ce);
		}
		return strGridData.substring(0, strGridData.length()-1);
	}
	
	@RequestMapping(value = "/insertTimeTableGridData", method = RequestMethod.GET)
	public  @ResponseBody String insertTimeTableGridData(@RequestParam("cellid") String strCellId,
														 @RequestParam("celldata") String strCellData) {

		String strStatus = null;
		try {
			TimeTableGridData timeTableGridData = new TimeTableGridData();
			timeTableGridData.setGridId(strCellId);
			timeTableGridData.setGridData(strCellData);
			if(null != timeTableGridData.getGridId() && null != timeTableGridData.getGridData()){
				TimeTableGridData timeTableGridDataDB = backOfficeService.getTimeTableGridData(timeTableGridData);
				
				if(null != timeTableGridDataDB){
					backOfficeService.updateTimeTableGridData(timeTableGridData);
					strStatus = "Updated Successfully";
				}else{
					backOfficeService.insertTimeTableGridData(timeTableGridData);
					strStatus = "Inserted Successfully";
				}
			}else{
				strStatus = "Blank Value, so not inserted / updated.";
			}
			
			
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method insertTimeTableGridData-POST of BackOfficeController", ce);
		}
		return strStatus;
	}
	
	@RequestMapping(value="/editAndUpdateTimeTable", method = RequestMethod.POST)
	public String editAndUpdateTimeTable(HttpServletRequest request, HttpServletResponse response,
										ModelMap model,
										@ModelAttribute("sessionObject") SessionObject sessionObject){
		String message = null;
		try{
			logger.info("in editAndUpdateTimeTable method in backofficeController");
			TimeTableConfigModel timtab = new TimeTableConfigModel();
			String saveId = request.getParameter("saveId"); 
			timtab.setTeacherName((request.getParameter("teacherName"+saveId)).trim().toUpperCase());
			timtab.setClassSectionName((request.getParameter("classSectionName"+saveId)).trim().toUpperCase());
			timtab.setSubjectName((request.getParameter("subjectName"+saveId)).trim().toUpperCase());
			timtab.setNoOfClasses(request.getParameter("className"+saveId));
			timtab.setTeacherUserId(request.getParameter("OldTeacherName"+saveId));
			timtab.setOldClassSection(request.getParameter("OldClassSectionName"+saveId));
			timtab.setOldsubjectName(request.getParameter("OldSubjectName"+saveId));
			message = backOfficeService.editAndUpdateTimeTable(timtab);
			
			if(message.equalsIgnoreCase("failed")){
			model.addAttribute("failuremsg", "Failed To Update IT Section");
			}
			if(message.equalsIgnoreCase("created")){
				model.addAttribute("successmsg", "IT Section Updated Successfully");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return configureTimeTable(request,response,model);
	}
	
	@RequestMapping(value = "/deleteClassForTeacher", method = RequestMethod.GET)
	public String deleteClassForTeacher(
												HttpServletRequest request, HttpServletResponse response,
												ModelMap model,
												@RequestParam("detailsId") String detailsId,
												@ModelAttribute("sessionObject") SessionObject sessionObject) {		
		try {
			//System.out.println("detailsId:::"+detailsId);
			if (detailsId != null) {
				String insertStatus = backOfficeService.deleteClassForTeacher(detailsId);
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
		return configureTimeTable(request,response,model);
		
	}
	
	///----------------------START LIBRARY FINE PART---------------------------------
	
	/**
	 * @author ranita.sur
	 * this method is for view all requests allowed for fine*
	 * */
	
	@RequestMapping(value = "/getBookDetailsForFine", method = RequestMethod.GET)
	public String getBookDetailsForFine(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/getBookDetailsForFine";
		try {
			logger.info("Inside Method getBook-GET of BackOfficeController");
			List<BookAllocation> bookFinedList = backOfficeService.getBookDetailsForFineList();
			model.addAttribute("bookAllocatedList",bookFinedList);
		} catch (CustomException ce){
			logger.error("Exception in method getSocialCategory-GET of BackOfficeController", ce);
		}
		return strView;
	}
		
	
		/**@author anup.roy
		 * modified on 13 Jan 2017
		 * for adding ledger in fine page
		 * */
	
		@RequestMapping(value = "/libraryFineReceiveDetails", method = RequestMethod.GET)
		public ModelAndView submitUserIdForlibraryFineRecieve(
				HttpServletRequest request, HttpServletResponse response,
				ModelMap model, @RequestParam(required = false, value = "userId") String userId) {
			logger.info("calling submitUserIdForlibraryFineRecieve() method of BackOfficeController");
			try {
				if(userId != null){
					Resource resource = new Resource();
					resource.setUserId(userId);
					List<BookAllocation> bookAllocationList = backOfficeService.getIssuedBookDetails(resource);
					if (bookAllocationList != null && bookAllocationList.size() != 0) {
						model.addAttribute("IssuedBookResultList", bookAllocationList);
					}
					List<Ledger>ledgerList = financeService.getLedgerList();//Added By Anup 13012017
					//System.out.println("ledgerList Size:"+ledgerList.size());
					if (ledgerList != null && ledgerList.size() != 0) {
						model.addAttribute("ledgerList",ledgerList);
					}
					else {
						model.addAttribute("errorMessageDisplay", "Data not available.");
					}
				}
			} catch (NullPointerException e) {
				logger.error("NullPointerException in submitUserIdForlibraryFineRecieve() method Of BackOfficeController", e);
			} catch (Exception e) {
				logger.error("Exception in submitUserIdForlibraryFineRecieve() method Of BackOfficeController", e);
			}
			return new ModelAndView("backoffice/libraryFineReceiveDetails");
		}
		
		@RequestMapping(value = "/submitLibrayFine", method = RequestMethod.POST)
		public ModelAndView submitLibrayFine(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("resource") Resource resource,
				@RequestParam("strBookAllocation") String bookallocationcode,
				@RequestParam("strBookId") String bookid,
				@RequestParam("struId") String uId,
				@RequestParam("strPenalty") String penalty,
				PreviousYearFinanceData previousYearFinanceData,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String strView = "backoffice/libraryFineReceiveDetails";
			logger.info("calling submitLibrayFine() method of BackOfficeController");
			try {
				resource.setCode(bookallocationcode);
				resource.setDesc(bookid);
				previousYearFinanceData.setPreviousDataCode(uId);
				double doublepenalty = Double.parseDouble(penalty);
				previousYearFinanceData.setAmount(doublepenalty);
				previousYearFinanceData.setUpdatedBy(sessionObject.getUserId());
				resource.setUpdatedBy(sessionObject.getUserId());
				//System.out.println(resource.getLedger());
				List<BookAllocation> bookAllocationList = backOfficeService.submitLibrayFine(resource, previousYearFinanceData);
				if (bookAllocationList != null && bookAllocationList.size() != 0) {
					model.addAttribute("IssuedBookResultList", bookAllocationList);
				} else {
					model.addAttribute("errorMessageDisplay", "Data not available.");
				}
			} catch (NullPointerException e) {
				logger.error("NullPointerException in submitLibrayFine() method Of BackOfficeController",e);
			} catch (NumberFormatException e) {
				logger.error("NumberFormatException in submitLibrayFine() method Of BackOfficeController",e);
			} catch (Exception e) {
				logger.error("Exception in submitLibrayFine() method Of BackOfficeController",e);
			}
			return new ModelAndView(strView);
		}
//	-------------------------- END LIBRARY FINE----------------------------
		
// ------------------------ START IT SECTION-----------------------------	
		
	@RequestMapping(value="/addITSections", method = RequestMethod.GET)
	public String addITSections(HttpServletRequest request, HttpServletResponse response,
								ModelMap model){
		//System.out.println("line 5395");
		try{
			List<ITSection> itSectionList = backOfficeService.viewAllITSections();
			if(null !=itSectionList && itSectionList.size()!=0){
				model.addAttribute("itSectionList",itSectionList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "backoffice/addITSections";
	}
	
	@RequestMapping(value="/createITSections", method = RequestMethod.POST)
	public String createITSections(HttpServletRequest request, HttpServletResponse response,
										ModelMap model,ITSection itSection,
										@ModelAttribute("sessionObject") SessionObject sessionObject){
		String message=null;
		try{
			if(null !=itSection){
				itSection.setUpdatedBy(sessionObject.getUserId());
				message=backOfficeService.createITSections(itSection);
			}else{
				message="failed";
			}
			if(null!=message){
				if(message.equalsIgnoreCase("failed")){
					model.addAttribute("failuremsg", "Failed To Add New IT Section");
				}				
				if(message.equalsIgnoreCase("created")){
					model.addAttribute("successmsg", "IT Sections Year Added Successfully");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return addITSections(request,response,model);
	}
	
	@RequestMapping(value="/updateITSection", method = RequestMethod.POST)
	public String updateITSection(HttpServletRequest request, HttpServletResponse response,
									ModelMap model,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		String message=null;
		try{
			logger.info("in updateSection method in backofficeController");
			ITSection itSection = new ITSection();
			String saveId = request.getParameter("saveId"); 
			itSection.setItSectionName((request.getParameter("ItSectionName"+saveId)).trim().toUpperCase());
			itSection.setItSectionDesc((request.getParameter("ItSectionDesc"+saveId)).trim().toUpperCase());
			itSection.setItSectionCode(request.getParameter("oldItSectionCode"+saveId));
			itSection.setUpdatedBy(sessionObject.getUserId());
			message = backOfficeService.updateITSection(itSection);
			
			if(message.equalsIgnoreCase("failed")){
			model.addAttribute("failuremsg", "Failed To Update IT Section");
			}				
			if(message.equalsIgnoreCase("created")){
				model.addAttribute("successmsg", "IT Section Updated Successfully");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return addITSections(request,response,model);
	}
	
	@RequestMapping(value="/viewITSectionRebates", method = RequestMethod.GET)
	public String viewITSectionRebates(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<ITSection> itSectionList = null;
		try{
			itSectionList = backOfficeService.viewAllITSections();
			model.addAttribute("itSectionList", itSectionList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "backoffice/createITSectionDetails";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRebatesForITSection", method = RequestMethod.GET)
	public String getRebatesForITSection(HttpServletRequest request, HttpServletResponse response, 
										@RequestParam("itSection") String itSectionCode, ModelMap model){
		logger.info("In getSubmittedSlabTypeForMiscTax() method of ErpController: ");
		String output = "";
		try {
			List<ITSectionDetails> itSectionDetList = backOfficeService.getRebatesForITSection(itSectionCode);
			if(null != itSectionDetList && itSectionDetList.size() != 0){
				for(ITSectionDetails itSecDets : itSectionDetList){
					output = output + itSecDets.getItSectionDetailsName() + "~" + itSecDets.getItSectionDetailsCode() + "^^";
				}
			}
			//System.out.println("@@@ rowCount :: "+output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(output));
	}	
	
	
	@RequestMapping(value="/createITSectionRebates", method = RequestMethod.POST)
		public String createITSectionRebates(HttpServletRequest request, HttpServletResponse response, ModelMap model,
											@ModelAttribute("ITSection") ITSection itSection,
											@RequestParam("itSectionDetailsName") String[] itSecDetailsNames,
											@ModelAttribute("sessionObject") SessionObject sessionObject){
			//System.out.println("line 5500 in backofficeController:: \t "+itSecDetailsNames);
			List<ITSectionDetails> itSectionDetailsList = null;
			try{
				if(null != itSecDetailsNames && itSecDetailsNames.length != 0){
					//System.out.println("itSecDetailsNames :: "+itSecDetailsNames);
					itSectionDetailsList = new ArrayList<ITSectionDetails>();
					for(int i=0; i<itSecDetailsNames.length; i++){
						ITSectionDetails itSecDet = new ITSectionDetails();
						itSecDet.setItSectionDetailsCode("SEC-REBATE");
						itSecDet.setItSectionDetailsName(itSecDetailsNames[i]);
						itSecDet.setUpdatedBy(sessionObject.getUserId());
						itSectionDetailsList.add(itSecDet);
					}
					itSection.setItSectionDetailsList(itSectionDetailsList);
					String submitResponse = backOfficeService.submitITSectionRebates(itSection);
					//System.out.println("## submitResponse :: "+submitResponse);
					int status = Integer.parseInt(submitResponse);
					if(status != 0){
						model.addAttribute("submitResponse", "New Rebate Successfully Created.");					
					}else{
						model.addAttribute("submitResponse", null);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}		
			return viewITSectionRebates(request, response, model);
		}	
	
	@RequestMapping(value="/editITSectionRebates", method = RequestMethod.POST)
	public String editITSectionRebates(HttpServletRequest request, HttpServletResponse response, ModelMap model,
												@ModelAttribute("ITSection") ITSection itSection,
												@RequestParam("itSectionDetailsName") String[] itSecDetailsNames,
												@RequestParam("itSectionDetailsCode") String[] itSectionDetailsCodes,
												@RequestParam(required = false, value = "itSecDetailsNewName") String[] itSecDetailsNewName,
												@ModelAttribute("sessionObject") SessionObject sessionObject){
		List<ITSectionDetails> itSectionDetailsList = null;
		List<ITSectionDetails> itSecDetailsList = null;
		try{
			if(null != itSecDetailsNames && itSecDetailsNames.length != 0){
				ITSection itSecNew = null;
				itSectionDetailsList = new ArrayList<ITSectionDetails>();
				
				/*System.out.println("ItSectionCode :: "+itSection.getItSectionCode());
				System.out.println("ItSecDetailsNames :: "+itSecDetailsNames.length);
				System.out.println("ItSectionDetailsCodes :: "+itSectionDetailsCodes.length);
				System.out.println("ItSecDetailsNewName :: "+itSecDetailsNewName);*/
				
				for(int i=0; i<itSecDetailsNames.length; i++){
					ITSectionDetails itSecDet = new ITSectionDetails();
					itSecDet.setItSectionDetailsCode(itSectionDetailsCodes[i]);
					itSecDet.setItSectionDetailsName(itSecDetailsNames[i]);
					itSecDet.setUpdatedBy(sessionObject.getUserId());
					itSectionDetailsList.add(itSecDet);
				}
				itSection.setUpdatedBy(sessionObject.getUserId());
				itSection.setItSectionDetailsList(itSectionDetailsList);				
				
				if(null != itSecDetailsNewName && itSecDetailsNewName.length != 0){
					itSecNew = new ITSection();
					itSecNew.setItSectionCode(itSection.getItSectionCode());
					
					itSecDetailsList = new ArrayList<ITSectionDetails>();
					for(int i=0; i<itSecDetailsNewName.length; i++){
						ITSectionDetails itSecDet = new ITSectionDetails();
						itSecDet.setItSectionDetailsCode("SEC-REBATE");
						itSecDet.setItSectionDetailsName(itSecDetailsNewName[i]);
						itSecDet.setUpdatedBy(sessionObject.getUserId());
						itSecDetailsList.add(itSecDet);
					}
					itSecNew.setItSectionDetailsList(itSecDetailsList);					
				}
				String updateResponse = backOfficeService.editITSectionRebates(itSection, itSecNew);
				//System.out.println("updateResponse :: "+updateResponse);
				int status = 0;
				if(null != updateResponse){
					status = Integer.parseInt(updateResponse);
				}
				if(status != 0){
					model.addAttribute("submitResponse", "Rebate Successfully Updated.");
				}else{
					model.addAttribute("submitResponse", null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return viewITSectionRebates(request, response, model);
	}
	
	@RequestMapping(value="/deductionAmountLimit", method = RequestMethod.GET)
	public String deductionAmountLimit(HttpServletRequest request, HttpServletResponse response,
								ModelMap model){
		try{			
			List<FinancialYear> financialYearList = commonService.getFinancialYearList();
			if(null!=financialYearList && financialYearList.size() != 0){
				model.addAttribute("financialYearList", financialYearList);
			}
			List<IncomeAge> incomeAgeList = backOfficeService.getIncomeAgeList();
			if(null!=incomeAgeList && incomeAgeList.size() != 0){
				model.addAttribute("incomeAgeList", incomeAgeList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "backoffice/deductionAmountLimit";
	}
	
	@RequestMapping(value = "/getUnmappedITSections", method = RequestMethod.GET)
	public @ResponseBody
	String getUnmappedITSections(@RequestParam("financialYearCode") String financialYearCode,
								 @RequestParam("incomeAgeCode") String incomeAgeCode) {
		String itSections = "";
		try{
			if(null!=financialYearCode && null!=incomeAgeCode){
				ITSectionGroup itSectionGroup =new ITSectionGroup();
				FinancialYear financialYear=new FinancialYear();
				IncomeAge incomeAge =new IncomeAge();
				
				financialYear.setFinancialYearCode(financialYearCode);
				incomeAge.setIncomeAgeCode(incomeAgeCode);
				itSectionGroup.setFinancialYear(financialYear);
				itSectionGroup.setIncomeAge(incomeAge);
				List<ITSection> itSectionList = backOfficeService.getUnmappedITSections(itSectionGroup);
				if(null!=itSectionList && itSectionList.size()!=0){
					for(ITSection itsection :itSectionList){
						itSections=itSections+itsection.getItSectionCode()+"~"+itsection.getItSectionName()+";";
					}					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return (new Gson().toJson(itSections));
	}
	
	@RequestMapping(value="/insertITSectionDeductionAmount", method = RequestMethod.POST)
	public String insertITSectionDeductionAmount(HttpServletRequest request, HttpServletResponse response,
									ModelMap model,
									ITSectionGroup itSectionGroup,
									@RequestParam("totalGroupAmount") String totalGroupAmount,
									@RequestParam("itSectionCode") String itSectionCode[],
									@RequestParam("amount") String amount[],
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		String message=null;
		List<ITSection> itSectionList=new ArrayList<ITSection>();
		try{
			if(null !=itSectionGroup){
				itSectionGroup.setGroupAmount(Double.parseDouble(totalGroupAmount));
				itSectionGroup.setUpdatedBy(sessionObject.getUserId());
				if(itSectionGroup.isTotalAmountApplicable()){
					if(null!=itSectionCode && itSectionCode.length!=0){
						for(int i=0;i<itSectionCode.length;i++){
							ITSection itSection = new ITSection();
							String codeAndName[]=itSectionCode[i].split("~");
							itSection.setItSectionCode(codeAndName[0]);
							itSection.setItSectionName(codeAndName[1]);
							itSection.setAmount(0);
							itSectionList.add(itSection);							
						}
					}
					if(itSectionList.size()!=0)
					itSectionGroup.setItSectionList(itSectionList);
					message=backOfficeService.insertITSectionDeductionAmount(itSectionGroup);
				}else{
					if(null!=itSectionCode && itSectionCode.length!=0 && null!=amount && amount.length!=0 ){						
						for(int i=0;i<itSectionCode.length;i++){
							ITSection itSection = new ITSection();
							String codeAndName[]=itSectionCode[i].split("~");
							itSection.setItSectionCode(codeAndName[0]);
							itSection.setItSectionName(codeAndName[1]);
							itSection.setAmount(Double.parseDouble(amount[i]));
							itSectionList.add(itSection);
						}
						if(itSectionList.size()!=0)
						itSectionGroup.setItSectionList(itSectionList);
						message=backOfficeService.insertITSectionDeductionAmount(itSectionGroup);
					}
				}				
				
			}else{
				message="failed";
			}
			if(null!=message){
				if(message.equalsIgnoreCase("failed")){
					model.addAttribute("failuremsg", "Failed To Insert I.T Deduction Limit");
				}				
				if(message.equalsIgnoreCase("created")){
					model.addAttribute("successmsg", "I.T Deduction Limit Inserted Successfully");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return deductionAmountLimit(request,response,model);
	}
	
	@RequestMapping(value="/viewRebateAmountLimit", method = RequestMethod.GET)
	public String viewRebateAmountLimit(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<IncomeAge> incomeAgeList = null;
		List<FinancialYear> financialYearList = null;		 
		try{
			financialYearList = commonService.getFinancialYearList();
			if(null != financialYearList && financialYearList.size() != 0){
				model.addAttribute("financialYearList", financialYearList);
			}
			incomeAgeList = backOfficeService.getIncomeAgeList();
			if(null != incomeAgeList && incomeAgeList.size() != 0){
				model.addAttribute("incomeAgeList", incomeAgeList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "backoffice/createRebateAmountLimit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getITSectionGroupsForIncomeAge", method = RequestMethod.GET)
	public String getITSectionGroupsForIncomeAge(HttpServletRequest request, HttpServletResponse response, 
										@ModelAttribute("ITSectionGroup") ITSectionGroup itSectionGrp, ModelMap model){
		logger.info("In getITSectionGroupsForIncomeAge() method of BackOfficeController: ");
		String output = "";
		try {
			List<ITSectionGroup> itSectionGrpList = backOfficeService.getITSectionGroupForAgeYear(itSectionGrp);
			if(null != itSectionGrpList && itSectionGrpList.size() != 0){
				for(ITSectionGroup itSecGroup : itSectionGrpList){
					output = output + itSecGroup.getItSectionGroupCode() + "~" + itSecGroup.getItSectionGroupName() + "^^";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getITSectionGroupsForIncomeAge() method for Exception: ", e);
		}
		return (new Gson().toJson(output));
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkITSecDetailAmount", method = RequestMethod.GET)
	public String checkITSecDetailAmount(HttpServletRequest request, HttpServletResponse response, 
										@ModelAttribute("ITSectionGroup") ITSectionGroup itSectionGrp, ModelMap model){
		logger.info("In checkITSecDetailAmount() method of BackOfficeController: ");
		String count = "";
		try {
			count = backOfficeService.checkITSecDetailAmount(itSectionGrp);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController checkITSecDetailAmount() method for Exception: ", e);
		}
		return (new Gson().toJson(count));
	}
		
	
	@ResponseBody
	@RequestMapping(value = "/getITSectionForITGroups", method = RequestMethod.GET)
	public String getITSectionForITGroups(HttpServletRequest request, HttpServletResponse response, 
										@ModelAttribute("ITSectionGroup") ITSectionGroup itSectionGrp, ModelMap model){
		logger.info("In getITSectionForITGroups() method of BackOfficeController: ");
		String output = "";
		ITSectionGroup itSectionGroup = null;
		List<ITSectionDetails> itSecDetailsList = null;
		try {
			itSectionGroup = backOfficeService.getITSectionForITGroups(itSectionGrp);
			if(null != itSectionGroup){
				output = output + itSectionGroup.getGroupAmount() + "###";				
				for(ITSection itSec : itSectionGroup.getItSectionList()){
					output = output + itSec.getItSectionCode() + "~" + itSec.getItSectionName() + "~" + itSec.getAmount() + "^^";					
					itSecDetailsList = backOfficeService.getITSectionDetailForITSecs(itSec);
					if(null != itSecDetailsList && itSecDetailsList.size() != 0){
						for(ITSectionDetails itSecDet : itSecDetailsList){
							output = output + itSecDet.getItSectionDetailsCode() + ":" + itSecDet.getItSectionDetailsName() + "::"; 
						}
					}
					output = output + "##";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getITSectionForITGroups() method for Exception: ", e);
		}
		return (new Gson().toJson(output));
	}
	
	@RequestMapping(value = "/submitITSectionRebateAmountLimit", method = RequestMethod.POST)
	public String submitITSectionRebateAmountLimit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
												@ModelAttribute("ITSectionGroup") ITSectionGroup itSectionGroup,
												@RequestParam("itSecName") String[] itSecNames,
												@ModelAttribute("sessionObject") SessionObject sessionObject){
		List<ITSection> itSectionList = null;
		List<ITSectionDetails> itSectionDetailsList = null;
		try{
			if(null != itSecNames && itSecNames.length != 0){
				itSectionList = new ArrayList<ITSection>();
				for(int i=0; i<itSecNames.length; i++){
					ITSection itSec = new ITSection();
					itSec.setItSectionCode(itSecNames[i]);
					String[] secDetCodes = request.getParameterValues(itSecNames[i]);
					if(null != itSecNames && itSecNames.length != 0){
						itSectionDetailsList = new ArrayList<ITSectionDetails>();
						for(int j=0; j<secDetCodes.length; j++){
							ITSectionDetails itSecDets = new ITSectionDetails();
							itSecDets.setItSectionDetailsCode(secDetCodes[j]);
							if(request.getParameter(secDetCodes[j]) ==null){
								itSecDets.setSubAmount(Double.parseDouble("0"));
							}else
								itSecDets.setSubAmount(Double.parseDouble(request.getParameter(secDetCodes[j])));
							
							itSectionDetailsList.add(itSecDets);
						}
					}
					itSec.setItSectionDetailsList(itSectionDetailsList);
					itSectionList.add(itSec);
				}
				itSectionGroup.setUpdatedBy(sessionObject.getUserId());
				itSectionGroup.setItSectionList(itSectionList);
				String submitResponse = backOfficeService.submitITSectionRebateAmounts(itSectionGroup);
				int status = Integer.parseInt(submitResponse);
				if(status != 0){
					model.addAttribute("submitResponse", "Rebate Amounts Stored Successfully.");					
				}else{
					model.addAttribute("submitResponse", null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return viewRebateAmountLimit(request, response, model);
	}
	
	@RequestMapping(value="/editRebateAmountLimit", method = RequestMethod.GET)
	public String editRebateAmountLimit(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<IncomeAge> incomeAgeList = null;
		List<FinancialYear> financialYearList = null;		 
		try{
			financialYearList = commonService.getFinancialYearList();
			if(null != financialYearList && financialYearList.size() != 0){
				model.addAttribute("financialYearList", financialYearList);
			}
			incomeAgeList = backOfficeService.getIncomeAgeList();
			if(null != incomeAgeList && incomeAgeList.size() != 0){
				model.addAttribute("incomeAgeList", incomeAgeList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "backoffice/editRebateAmountLimit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRebateAmountDetailForITGroup", method = RequestMethod.GET)
	public String getRebateAmountDetailForITGroup(HttpServletRequest request, HttpServletResponse response, 
										@ModelAttribute("ITSectionGroup") ITSectionGroup itSectionGrp, ModelMap model){
		logger.info("In getRebateAmountDetailForITGroup() method of BackOfficeController: ");
		String output = "";
		ITSectionGroup itSectionGroup = null;
		List<ITSectionDetails> itSecDetailsList = null;
		try {
			itSectionGroup = backOfficeService.getRebateAmountDetailForITGroup(itSectionGrp);
			if(null != itSectionGroup){
				output = output + itSectionGroup.getGroupAmount() + "###";				
				for(ITSection itSec : itSectionGroup.getItSectionList()){
					output = output + itSec.getItSectionCode() + "~" + itSec.getItSectionName() + "~" + itSec.getAmount() + "^^";					
					itSecDetailsList = itSec.getItSectionDetailsList();
					if(null != itSecDetailsList && itSecDetailsList.size() != 0){
						for(ITSectionDetails itSecDet : itSecDetailsList){
							output = output + itSecDet.getItSectionDetailsCode() + ":" + itSecDet.getItSectionDetailsName() + ":" + itSecDet.getSubAmount() +"::"; 
						}
					}
					output = output + "##";
				}
			}
			//System.out.println("*** Output :: "+output);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController getRebateAmountDetailForITGroup() method for Exception: ", e);
		}
		return (new Gson().toJson(output));
	}
	
	@RequestMapping(value = "/updateITSectionRebateAmountLimit", method = RequestMethod.POST)
	public String updateITSectionRebateAmountLimit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
												@ModelAttribute("ITSectionGroup") ITSectionGroup itSectionGroup,
												@RequestParam("itSecName") String[] itSecNames,
												@ModelAttribute("sessionObject") SessionObject sessionObject){
		List<ITSection> itSectionList = null;
		List<ITSectionDetails> itSectionDetailsList = null;
		try{
			if(null != itSecNames && itSecNames.length != 0){
				//System.out.println("itSecNames :: "+itSecNames);
				itSectionList = new ArrayList<ITSection>();
				for(int i=0; i<itSecNames.length; i++){
					ITSection itSec = new ITSection();
					itSec.setItSectionCode(itSecNames[i]);
					String[] secDetCodes = request.getParameterValues(itSecNames[i]);
					if(null != itSecNames && itSecNames.length != 0){
						itSectionDetailsList = new ArrayList<ITSectionDetails>();
						for(int j=0; j<secDetCodes.length; j++){
							ITSectionDetails itSecDets = new ITSectionDetails();
							itSecDets.setItSectionDetailsCode(secDetCodes[j]);
							if(request.getParameter(secDetCodes[j])==null){
								itSecDets.setSubAmount(Double.parseDouble("0"));	
							}else
								itSecDets.setSubAmount(Double.parseDouble(request.getParameter(secDetCodes[j])));
							
							itSectionDetailsList.add(itSecDets);
						}
					}
					itSec.setItSectionDetailsList(itSectionDetailsList);
					itSectionList.add(itSec);
				}
				itSectionGroup.setUpdatedBy(sessionObject.getUserId());
				itSectionGroup.setItSectionList(itSectionList);
				String updateResponse = backOfficeService.updateITSectionRebateAmountLimit(itSectionGroup);
				//System.out.println("## updateResponse :: "+updateResponse);
				int status = Integer.parseInt(updateResponse);
				if(status != 0){
					model.addAttribute("updateResponse", "Rebate Amounts Updated Successfully.");					
				}else{
					model.addAttribute("updateResponse", null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return editRebateAmountLimit(request, response, model);
	}
	
// ------------------------ END IT SECTION-----------------------------	
	
//----------------------------START STUDENT TC PART--------------------------------
	
	@RequestMapping(value = "/getTC", method = RequestMethod.GET)
	public String getTC(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createTC";
		try {
			logger.info("Inside Method getTC-GET of BackOfficeController");
		} catch (Exception ce){
			logger.error("Exception in method getTC-GET of BackOfficeController", ce);
			ce.printStackTrace();
		}
		return strView;
	}
	
	@RequestMapping(value = "/getNameStandardSectionForRollNumber", method = RequestMethod.GET)
	public @ResponseBody
	String getNameStandardSectionForRollNumber(@RequestParam("rollNumber") String rollNumber) {
		logger.info("Inside Method getNameStandardSectionForRollNumber-GET of AcademicsController");
		String result = "";
		try {
			result = backOfficeService.getNameStandardSectionForRollNumber(rollNumber.trim());
		} catch (CustomException ce) {
			logger.error("Exception in method getStudentFeesPaymentStatus-GET of AcademicsController", ce);
			ce.printStackTrace();
		}		
		return (new Gson().toJson(result));
	}
	
	@RequestMapping(value = "/getStudentFeesPaymentStatus", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentFeesPaymentStatus(@RequestParam("rollNumber") String rollNumber) {
		logger.info("Inside Method getStudentFeesPaymentStatus-GET of AcademicsController");
		String result = "";
		try {
			result = backOfficeService.getStudentFeesPaymentStatus(rollNumber);
		} catch (CustomException ce) {
			logger.error("Exception in method getStudentFeesPaymentStatus-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return (new Gson().toJson(result));
	}
	
//-----------------------------END STUDENT TC PART-----------------------------------	
	
	@RequestMapping(value = "/checkSocialCategoryName", method = RequestMethod.GET)
	public @ResponseBody
	String checkSocialCategoryName(@RequestParam("socialCategoryName") String socialCategoryName) {
		String valid = null;
		try {
			valid = backOfficeService.checkSocialCategoryName(socialCategoryName.trim().toUpperCase());
		} catch (Exception e) {
			logger.error("Exception In checkCommodityName() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(valid));
	}
	
/**
 * TO SUBMIT FEES 
 * */	
	
	@RequestMapping(value = "/admissionDriveListForFeesSubmission", method = RequestMethod.GET)
	public ModelAndView admissionDriveListForFeesSubmission(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String strView = null;
		List<AdmissionForm> admissionsOnProcessListFromDb = null;
		try {
			logger.info("admissionDriveListForFeesSubmission() method in BackOfficeController");
			logger.info("call getAdmissionsOnProcessList() in  AdmissionService.java class");
			admissionsOnProcessListFromDb = backOfficeService.admissionDriveListForFeesSubmission();
			/* checkingadmissionCourseList is null */
			if (admissionsOnProcessListFromDb != null && admissionsOnProcessListFromDb.size() != 0) {
				logger.info("In BackOfficeController admissionDriveListForFeesSubmission() method: calling getBookListPaging(HttpServletRequest request) in LibraryService.java");
				model.addAttribute("admissionsOnProcessListFromDb", admissionsOnProcessListFromDb);
				strView = "backoffice/admissionDriveListForFeesSubmission";
			} else {
				/*String error = "data not found.";
				model.addAttribute("ResultError", error);
				strView = "common/errorpage";*/
				model.addAttribute("admissionsOnProcessListFromDb", admissionsOnProcessListFromDb);
				strView = "backoffice/admissionDriveListForFeesSubmission";
			}
		} catch (NullPointerException e) {
			logger.error("NullPointerException in admissionDriveListForFeesSubmission() method in BackOfficeController",e);
		} catch (Exception e) {
			logger.error("Exception in admissionDriveListForFeesSubmission() method in BackOfficeController",e);
		}
		return new ModelAndView(strView);
	}
	
	
	/**
	 * NEW ADDED BY NAIMISHA FOR IT REBATE
	 * */
	
	@ResponseBody
	@RequestMapping(value="/inactiveITRebate", method = RequestMethod.GET)
	public String inactiveITRebate(HttpServletRequest request, HttpServletResponse response, ModelMap model,
										@ModelAttribute("itSection") String itSection){
		//System.out.println("line 5500 in backofficeController:: \t "+itSection);
	//	List<ITSectionDetails> itSectionDetailsList = null;
		String status = null;
		try{
			if(itSection !=null && itSection.length() != 0){
				 status = backOfficeService.inactiveItRebate(itSection);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(status));
	}
	
	/**
	 * Added by parma for receive fees
	 * modified by naimisha 
	 * for ledger list add in page*/
	
	@RequestMapping(value = "/selectedCandidatesForFeesSubmission", method = RequestMethod.GET)
	public ModelAndView selectedCandidatesForFessSubmission(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @RequestParam("courseClass") String courseClass,
			@RequestParam("year") String year,
			@RequestParam("driveName") String driveName,
			@RequestParam("courseName") String courseName,
			AdmissionProcess admissionProcess) {
		String strView = null;
		try {
			logger.info("selectedCandidatesForFessSubmission() method in BackOfficeController");
			admissionProcess.setAdmissionYear(year);
			admissionProcess.setCourseClass(courseClass);
			admissionProcess.setFormName(driveName);
			admissionProcess = backOfficeService.getRegistrationIdFormListClassForFeesSubmission(admissionProcess);
			if (admissionProcess != null) {
				admissionProcess.setCourseName(courseName);
				model.addAttribute("selectedCandidatesForFeesSubmission",admissionProcess);
				strView = "backoffice/selectedCandidatesForFeesSubmission";
			} else {
				String error = "data not found.";
				model.addAttribute("ResultError", error);
				strView = "common/errorpage";
			}
			List<Ledger>ledgerList = financeService.getLedgerList();//Changed By Naimisha 03012017
		
			if (ledgerList != null && ledgerList.size() != 0) {
				model.addAttribute("ledgerList",ledgerList);
			}
		}catch (NullPointerException e) {
			logger.error("NullPointerException in selectedCandidatesForFessSubmission() method in BackOfficeController",e);
		} catch (Exception e) {
			logger.error("Exception in selectedCandidatesForFessSubmission() method in BackOfficeController",e);
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param student
	 * @param selectedformId
	 * @param selectedClass
	 * @param selectedYear
	 * @param selectedDriveName
	 * @param admissionProcess
	 * @return
	 */
	/**modified by sourav.bhadra 19092017
	 * */
	@RequestMapping(value = "/submitFeesForStudent", method = RequestMethod.POST)
	public ModelAndView submitFeesForStudent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("student") Student student,
			@RequestParam("formId") String selectedformId,
			@RequestParam("klass") String selectedClass,
			@RequestParam("year") String selectedYear,
			@RequestParam("driveName") String selectedDriveName,
			AdmissionProcess admissionProcess,
			@ModelAttribute("bankList") String bankName,/* modified by sourav.bhadra on 19-09-2017 */
			@RequestParam("totalSubmittedValue") String totalSubmittedValueStr,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		List<FeesCategory> feesCategoryList = new ArrayList<FeesCategory>();
		try {
			logger.info("submitFeesForStudent() method in BackOfficeController");
			admissionProcess.setCourseClass(selectedClass);
			admissionProcess.setAdmissionYear(selectedYear);
			admissionProcess.setFormName(selectedDriveName);
			student.setStandard(selectedClass);
			student.setAcademicYearForFeesSubmission(selectedYear);
			student.setStudentCode(bankName);/* modified by sourav.bhadra on 19-09-2017 */
			student.setDriveNameForFeesSubmission(selectedDriveName);
			student.setFormIdForFeesSubmission(selectedformId);
			
			String[] SplittedData = totalSubmittedValueStr.split("/");
			for(int index = 0;index<SplittedData.length;index++){
				FeesCategory feesCategory = new FeesCategory();
				String[] individualFees = SplittedData[index].split(";");
				for(int indexAnother = 0;indexAnother<individualFees.length;indexAnother++){
					feesCategory.setFeesCategoryName(individualFees[0]);
					feesCategory.setFees(Double.parseDouble(individualFees[1]));
					feesCategory.setDiscountedFees(Double.parseDouble(individualFees[3]));
					
					/* modified by sourav.bhadra on 18-09-2017 for due */
					double dueAmt = (Double.parseDouble(individualFees[1]) - Double.parseDouble(individualFees[3]));
					feesCategory.setDiscount(dueAmt);
					if(dueAmt > 0){
						feesCategory.setStatus("DUE");
					}else{
						feesCategory.setStatus(individualFees[2]);
					}
				}
				feesCategoryList.add(feesCategory);
			}
			student.setFeesCategoryList(feesCategoryList);
			student.setUpdatedBy(sessionObject.getUserId());
			
			List<Resource> formIdList = backOfficeService.submitFeesForStudent(student);

			if (formIdList != null && formIdList.size() != 0) {
				admissionProcess.setResourceList(formIdList);
			}
			if (admissionProcess != null) {
				model.addAttribute("selectedCandidatesForFeesSubmission",admissionProcess);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in selectedCandidatesForFessSubmission() method in BackOfficeController",e);
		}
		return new ModelAndView("backoffice/selectedCandidatesForFeesSubmission");
	}
	
	/**
	 * 
	 * @param selectedClass
	 * @param selectedFormId
	 * @param selectedYear
	 * @param selectedDriveName
	 * @param resource
	 * @return String
	 modified by sourav bhadra 19092017
	 */
	@RequestMapping(value = "/getFeeStructureForStudent", method = RequestMethod.GET)
	public @ResponseBody
	String getFeeStructureForStudent(
			
			@RequestParam("klass") String selectedClass,
			@RequestParam("formId") String selectedFormId,
			@RequestParam("year") String selectedYear,
			@RequestParam("driveName") String selectedDriveName,
			@RequestParam("courseName") String courseName,
			Resource resource) {
		logger.info("In getFeeStructureForStudent() method ");
		String sm = "";
		try {
			//System.out.println(selectedClass+"-"+selectedFormId+"-"+selectedYear+"-"+selectedDriveName+"-"+courseName);
			resource.setKlass(selectedClass);
			resource.setAdmissionYear(selectedYear);
			resource.setAdmissionDriveNameId(selectedDriveName);
			resource.setAdmissionFromId(selectedFormId);
			resource.setCourseName(courseName);
			Resource candidateDetails = backOfficeService.getCandidateDetails(resource);
			double totValue = 0;
			if (candidateDetails != null) {
				resource.setCategory(candidateDetails.getCategory());
				List<FeesCategory> feesCategoryList = backOfficeService.getFeeStructureForStudent(resource);				
				if (candidateDetails.getFirstName() == null) {
					candidateDetails.setFirstName("");
				}
				if (candidateDetails.getMiddleName() == null ) {
					candidateDetails.setMiddleName("");
				}
				if (candidateDetails.getLastName() == null) {
					candidateDetails.setLastName("");
				}
				String candidateInfo = candidateDetails.getFirstName()+" "+candidateDetails.getMiddleName()+""+candidateDetails.getLastName()+"#@";
				candidateInfo=candidateInfo+candidateDetails.getGender()+"#@";
				candidateInfo=candidateInfo+candidateDetails.getCategory()+ "~";
				sm = sm + candidateInfo;
				if (feesCategoryList != null && feesCategoryList.size() != 0) {
					for (FeesCategory feesCategory : feesCategoryList) {
						sm = sm + feesCategory.getFeesCategoryName() + "*"
								+ feesCategory.getFees() + "*"
								+ feesCategory.getFeesType() + "#";
						totValue = totValue + feesCategory.getFees();
					}
					sm = sm + "~" + totValue;
				}
				
				/* added by sourav.bhadra on 19-09-2017 for bank */
				List<Vendor> bankList = inventoryService.getBankDetailsList();
				String banks = "";
				if(null != bankList || bankList.size() != 0){
					for(Vendor v : bankList){
						banks += v.getBankName() + ";";
					}
				}
				sm = sm + "/bank/" + banks;
			}
		} catch (NullPointerException e) {
			logger.error("NullPointerException in getFeeStructureForStudent() IN BackOfficeController ",e);e.printStackTrace();
		} catch (Exception e) {
			logger.error("Exception in getFeeStructureForStudent() IN BackOfficeController ",e);e.printStackTrace();
		}
		return (new Gson().toJson(sm));
	}

	
	/***
	 * FOR ATTENDANCE CALENDAR FOR STUDENT
	 * */
	
	
	@RequestMapping(value = "/attendanceCalendar", method = RequestMethod.GET)
	public ModelAndView attendanceCalendar(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In attendanceCalendar() method in BackofficeController");
		List<Term> listTerm = null;
		List<CalendarEvent> specialHolidays = null;
		try {
		//	logger.debug("getInfoForCalendar()In BackOfficeController.java: calling fetchclassDetails() in BackOfficeService.java");
			listTerm = backOfficeService.listTermDetails();
			if (listTerm != null) {
				model.addAttribute("listTerm", listTerm);
			}
			specialHolidays = backOfficeService.listSpecialHolidays();
			if (specialHolidays != null) {
				model.addAttribute("specialHolidays", specialHolidays);
			}
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
			}
			AcademicYear currentYear = commonService.getCurrentAcademicYear();
			if (currentYear != null) {
				model.addAttribute("currentYear", currentYear);
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
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("backoffice/infoForAttendanceCalendar");
	}
	
	/***
	 * @author anup.roy
	 * for show list of students for calendar*/
	
	@RequestMapping(value = "/getSelectedStudentsForView", method = RequestMethod.GET)
	public @ResponseBody
	String getSelectedStudentsForView(
									@RequestParam("class") String selectedclass,
									@RequestParam("section") String selectedsection,
									@RequestParam("year") String selectedyear,
									@RequestParam("month") String selectedmonth,
									StudentAttendance studentAttendance) {
		String showStudents = "";
		try {
			logger.info("In getSelectedStudentsForView() method ");
			Resource resource = new Resource();
			resource.setKlass(selectedclass);
			Section section = new Section();
			section.setSectionName(selectedsection);
			resource.setSection(section);
			showStudents = backOfficeService.getStudentsForView(resource);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(showStudents));
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value ="/getStudentAttendanceForCalendar")
	@ResponseBody public String getStudentAttendanceForCalendar(HttpServletRequest request, 
																@RequestParam(required=false, value="year") String year,
																@RequestParam(required=false, value="month") String month,
																@RequestParam(required=false, value="resourceName") String resourceName) {
		System.out.println("line 6504 in backofficeController:: year:::"+year+"\n month::"+month+"\n resource::"+resourceName);
		String output = "";
		AttendanceDetails attendanceDetails = new AttendanceDetails();
		attendanceDetails.setMonth(month);
		attendanceDetails.setYear(year);
		attendanceDetails.setTeacherId(resourceName);
		AttendanceDetails absentDateList = backOfficeService.getResourceAbsentDateRecord(attendanceDetails);
		if(null != absentDateList.getAbsentDateList() && absentDateList.getAbsentDateList().size() !=0){
			for(String str : absentDateList.getAbsentDateList()){
				System.out.println("DATE IS :"+str);			
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date date = sdf.parse(str);
					System.out.println("ADDED:"+date.getDate());
					output = output + date.getDate() + "~";
				} catch (ParseException e) {
					e.printStackTrace();
				}		
			}
		}else{
			output = "null";
		}
		
		System.out.println("output:"+output);
        return (new Gson().toJson(output));
    }
	
	
	/**
	 * for teacher attendance new*/
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getTeacherAttendanceForCalendar", method = RequestMethod.GET)
	public @ResponseBody String getTeacherAttendanceForCalendar(
																@RequestParam("year") String year,
																@RequestParam("month") String month,
																@RequestParam("resourceName") String resourceName,
																ResourceAttendance resourceAttendance) {
		//System.out.println("method called::name::"+resourceName);
		//System.out.println("line 6504 in backofficeController:: year:::"+year+"\n month::"+month+"\n resource::"+resourceName);
		String output = "";
		AttendanceDetails attendanceDetails = new AttendanceDetails();
		attendanceDetails.setMonth(month);
		attendanceDetails.setYear(year);
		attendanceDetails.setTeacherId(resourceName);
		AttendanceDetails presentdate = backOfficeService.getTeacherAttendanceRecord(attendanceDetails);
		for(String str : presentdate.getPresentDateList()){
			//System.out.println("DATE IS :"+str);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date date = sdf.parse(str);
				//System.out.println("ADDED:"+date.getDate());
				output = output + date.getDate() + "~";
			} catch (ParseException e) {
				e.printStackTrace();
			}		
		}
		//System.out.println("output:"+output);
        return (new Gson().toJson(output));
	}
	
	//Modified by naimisha 18082017
	@RequestMapping(value = "/getFormIdAgainstCourseId", method = RequestMethod.GET)
	public @ResponseBody
	String getFormIdAgainstCourseId(@RequestParam("courseId") String courseId,@RequestParam("driveName") String driveName) {
		String formId = null;
		try {
			formId = backOfficeService.getFormIdAgainstCourseId(courseId,driveName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getFormIdAgainstCourseId(courseId) method of InventoryController: Exception",e);
		}
		return (new Gson().toJson(formId));
	}
	
	/**
	 * modified by naimisha 
	 * changes taken on 24062017**/
	
	@RequestMapping(value = "/getCandidateDetailsAgainstFromId", method = RequestMethod.GET)
	@ResponseBody public String getCandidateDetailsAgainstFromId(@RequestParam("formId") String formId,
																@RequestParam("driveName") String driveName,
																@RequestParam("courseId") String courseId) {
		String data = "";
		try {
			Resource resource = new Resource();
			resource.setAdmissionFromId(formId);
			resource.setAdmissionDriveNameId(driveName);
			resource.setCourseName(courseId);
			Student student = backOfficeService.getCandidateDetailsAgainstFromId(resource);
			data = data+"*";
			data = data +student.getResource().getFirstName()+"*"
						+student.getResource().getMiddleName()+"*"
						+student.getResource().getLastName()+"*"
						+student.getResource().getDateOfBirth()+"*"
						+student.getResource().getEmailId()+"*"
						+student.getUserId()+"*"
						+student.getDateOfAdmission()+"*"
						+student.getRegistrationId();
			} catch (CustomException ce){
				ce.printStackTrace();
			logger.error("Exception in method getDocumentVerification-GET of BackOfficeController", ce);
		}
		return (new Gson().toJson(data));
	}
	
	@RequestMapping(value = "/getAdmissionDriveNameAgainstCourseId", method = RequestMethod.GET)
	public @ResponseBody
	String getAdmissionDriveNameAgainstCourseId(@RequestParam("courseId") String courseId) {
		//System.out.println("course::"+courseId);
		String driveId = null;
		try {
			driveId = backOfficeService.getAdmissionDriveNameAgainstCourseId(courseId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getAdmissionDriveNameAgainstCourseId(courseId) method of BackofficeController: Exception",e);
		}
		return (new Gson().toJson(driveId));
	}
	
	
	/**
	 * @author anup.roy
	 * new fees portion starts
	 * this page is for open fees structure page and view the list
	 * backoffice new
	 * */
	
	
	@RequestMapping(value = "/createFeesStructure", method = RequestMethod.GET)
	public String createFees(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createFeesStructure";
		try {
			logger.info("In BackOfficeController createFees() method: calling selectCategory() in BackOfficeService.java");
			List<FeesCategory> listCategory = backOfficeService.selectCategory();
			List<FeesDuration> listFeesDuration = backOfficeService.selectFeesDuration();
			model.addAttribute("listFeesDuration", listFeesDuration);
			if (listCategory != null && listCategory.size() != 0) {
				model.addAttribute("listCategory", listCategory);
			}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController createFeesStructure() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController createFeesStructure() method for Exception: ",e);
		}
		return strView;
	}
	
	/**
	 * @author anup.roy
	 * add new fees structure
	 * backoffice new
	 * */
	
	@RequestMapping(value = "/submitFeesCategory", method = RequestMethod.POST)
	public String submitFeesCategory(HttpServletRequest request,HttpServletResponse response,ModelMap model, 
			@RequestParam("feesCategoryName") String feesCategoryName,
			@RequestParam("feesCategoryDuration") String feesCategoryDuration,
			FeesCategory feescategory, FeesDuration feesduration,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method submitFeesCategory-POST of BackOfficeController");
			feesduration.setFeesDurationCode(feesCategoryDuration);
			feescategory.setFeesCategoryName(feesCategoryName.trim().toUpperCase());
			feescategory.setFeesCategoryDesc(feesCategoryName.trim().toUpperCase());
			feescategory.setFeesCategoryCode(feesCategoryName.trim().toUpperCase());
			feescategory.setFeesDuration(feesduration);
			feescategory.setUpdatedBy(sessionObject.getUserId());
			String insertStatus = backOfficeService.insertCategory(feescategory);
			model.addAttribute("insertUpdateStatus", insertStatus);
		} catch (Exception e){
			logger.error("Exception in method addScholarship-POST of BackOfficeController", e);
			e.printStackTrace();
		}
		return createFees(request, response, model);
	}
	
	/**
	 * @author anup.roy
	 * this method is for edit the fees structure
	 * backoffice new
	 * modified by kaustav.sen
	 * changes taken on 28032017 
	 * **/
	
	@RequestMapping(value = "/editFeesStructure", method = RequestMethod.POST)
	public String editFeesStructure(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editFeesStructure-POST of BackOfficeController");
			FeesCategory feescategory = new FeesCategory();
			FeesDuration feesduration = new FeesDuration();
			//System.out.println("object"+feesduration);
			String saveId = request.getParameter("saveId");
			String duration=request.getParameter("getDuration");
			//System.out.println("LN 7181"+duration);
			String name=request.getParameter("getName");
			//System.out.println(name);
			feesduration.setFeesDurationCode(request.getParameter("oldCategoryCode"+saveId));
			feesduration.setFeesDurationCode(duration);
			feescategory.setFeesDuration(feesduration);
			feescategory.setFeesCategoryName(name.trim().toUpperCase());
			feescategory.setFeesCategoryCode(name.trim().toUpperCase());
			feescategory.setFeesCategoryDesc((request.getParameter("oldCategoryName"+saveId)).trim().toUpperCase());
			//System.out.println("nameCategoryName==="+request.getParameter("nameCategoryName"+saveId));
			//System.out.println("oldCategoryCode"+request.getParameter("oldCategoryCode"+saveId));
			//System.out.println("oldCategoryName===="+(request.getParameter("oldCategoryName"+saveId)).trim().toUpperCase());
			feescategory.setUpdatedBy(sessionObject.getUserId());
			String updateResponse = backOfficeService.editFeesStructure(feescategory);
			model.addAttribute("updateResponse", updateResponse);
			/**Added by @author Saif.Ali
			 * Date- 13/03/2018
			 * Used to insert the information into the activity_log table*/
			if(updateResponse.equalsIgnoreCase("success")){
				String oldCategoryName = (request.getParameter("oldCategoryName"+saveId));
				String oldDuration = (request.getParameter("oldFeesDuration"+saveId));
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT FEES STRUCTURE");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor("Category "+oldCategoryName + " :: " + " Duration "+ oldDuration);
				String description = "";
				if(!(name.equalsIgnoreCase(oldCategoryName)))
				{
					description=description+ ("Category Name :: " + oldCategoryName + " updated to Category Name :: " + name + " ; ");
				}
				if(!(duration.equalsIgnoreCase(oldDuration)))
				{
					description=description+ ("Duration :: " + oldDuration + " updated to Duration :: " + duration + " ; ");
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
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return createFees(request, response, model);
	}
	
	/**
	 * @author anup.roy*
	 * this method is for displaying create fees template page and list page
	 * backoffice new
	 * */
	
	@RequestMapping(value = "/createStudentFeesTemplate", method = RequestMethod.GET)
	public String createStudentFeesTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/createStudentFeesTemplate";
		try {
			logger.info("Inside Method createStudentFeesTemplate-GET of HostelController");
			List<FeesCategory> listCategory = backOfficeService.selectCategory();
			if (listCategory != null && listCategory.size() != 0) {
				model.addAttribute("listCategory", listCategory);				
			}
			
			List<StudentFeesTemplate> studentFeesTemplateList = backOfficeService.studentFeesTemplateList();
			if(studentFeesTemplateList != null && studentFeesTemplateList.size() != 0){
				model.addAttribute("studentFeesTemplateList", studentFeesTemplateList);
			}
		} catch (Exception e){
			e.printStackTrace();
			logger.error("Exception in method createStudentFeesTemplate-GET of HostelController", e);
		}
		return strView;
	}
	
	/**
	 * @author anup.roy*
	 * add new template*
	 * backoffice new
	 * */
	
	@RequestMapping(value = "/submitStudentFeesTemplate", method = RequestMethod.POST)
	public String submitStudentFeesTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="studentFeesTemplateName", required = false) String studentFeesTemplateName,
			@RequestParam(value="newtemps", required = false) String newtemps[],
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method submitStudentFeesTemplate-POST of HostelController");
			StudentFeesTemplate studentFeesTemp = new StudentFeesTemplate();
			
			studentFeesTemp.setStudentFeesTemplateName(studentFeesTemplateName.trim().toUpperCase());
			studentFeesTemp.setStudentFeesTemplateCode(studentFeesTemplateName.trim().toUpperCase());
			studentFeesTemp.setUpdatedBy(sessionObject.getUserId());
			if(null!=newtemps && newtemps.length!=0){
				List <StudentFeesTemplateDetails> tempDetailsList = new ArrayList<StudentFeesTemplateDetails>();
				for(int i=0;i<newtemps.length;i++){
					StudentFeesTemplateDetails tempDetails = new StudentFeesTemplateDetails();
					tempDetails.setStudentFeesBreakUpCode(newtemps[i]);
					tempDetailsList.add(tempDetails);
				}
				if(tempDetailsList.size()!=0){
					studentFeesTemp.setStudentFeesTemplateDetailsList(tempDetailsList);
					String insertStatus = backOfficeService.createNewFeesTemplate(studentFeesTemp);
					model.addAttribute("insertUpdateStatus", insertStatus);
				}else{
					logger.info("No hostels selected.");
				}
			}else{
				logger.info("No hostels selected.");
			}
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method submitStudentFeesTemplate-POST of HostelController", ce);
		}
		return createStudentFeesTemplate(request, response, model);
	}
	
	/**
	 * @author anup.roy*
	 * this method is for edit fees template of a student
	 * backoffice new
	 * Modified by @Kaustav Sen*
	 * */
	
	@RequestMapping(value = "/editStudentFeesTemplate", method = RequestMethod.POST)
	public String editStudentFeesTemplate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editStudentFeesTemplate-POST of HostelController");
			String saveId = request.getParameter("saveId");
			//System.out.println("save id====="+saveId);
			String getName = request.getParameter("getName");
			String getCategories = request.getParameter("getcategory");
			
			//System.out.println("getName==============="+getName);
			//System.out.println("getcategory========"+getCategories);
			
			List<String> feesTemplates = new ArrayList<String>();
			
			String []getCategoryArray = getCategories.split("::");
			//System.out.println("length===="+getCategoryArray.length);
			/*for(String catagory :getCategoryArray ){
				if(null != catagory){
					feesTemplates.add(catagory);
				}
			}*/
			for (int i=1;i<getCategoryArray.length;i++){
				feesTemplates.add(getCategoryArray[i]);
			}
			//System.out.println("feesTemplates size==="+feesTemplates.size());
			//String []selectedCategoryNames = request.getParameterValues("nametemplate"+saveId);
			String []addedCategoryNames = request.getParameterValues("oldcategories"+saveId);
			//System.out.println("addedCategoryNames====="+addedCategoryNames);
			
			StudentFeesTemplate studentFeesTemplate = new StudentFeesTemplate();
				if(null!=feesTemplates && feesTemplates.size()!=0){
					
					studentFeesTemplate.setStudentFeesTemplateCode(request.getParameter("nameOldTemplate"+saveId));
					studentFeesTemplate.setStudentFeesTemplateName(request.getParameter("nameNewTemplate"+saveId).trim().toUpperCase());
					studentFeesTemplate.setUpdatedBy(sessionObject.getUserId());
					
					
					List<String> oldfeesTemplates = new ArrayList<String>();
					
					List<StudentFeesTemplateDetails> listTemplatesToInsert = new ArrayList<StudentFeesTemplateDetails>();
					List<StudentFeesTemplateDetails> listTemplatesToDelete = new ArrayList<StudentFeesTemplateDetails>();
					/*if(null!=selectedCategoryNames && selectedCategoryNames.length!=0){
						feesTemplates = Arrays.asList(selectedCategoryNames);
					}*/
					if(null!=addedCategoryNames && addedCategoryNames.length!=0){
						oldfeesTemplates = Arrays.asList(addedCategoryNames);
					}
					for(String newFeesTemplate:feesTemplates){
						if(! oldfeesTemplates.contains(newFeesTemplate)){
							StudentFeesTemplateDetails stuFeesTemp = new StudentFeesTemplateDetails();
							stuFeesTemp.setStudentFeesBreakUpCode(newFeesTemplate);
							listTemplatesToInsert.add(stuFeesTemp);
						}
					}
					for(String oldFeesTemp:oldfeesTemplates){
						if(! feesTemplates.contains(oldFeesTemp)){
							StudentFeesTemplateDetails stuFeesTemp = new StudentFeesTemplateDetails();
							stuFeesTemp.setStudentFeesBreakUpCode(oldFeesTemp);
							listTemplatesToDelete.add(stuFeesTemp);
						}
					}
					
					if(listTemplatesToInsert.size()!=0)
						studentFeesTemplate.setStudentFeesTemplateDetailsList(listTemplatesToInsert);
					if(listTemplatesToDelete.size()!=0)
						studentFeesTemplate.setStudentFeesTemplateDetailsListOld(listTemplatesToDelete);
					
					String updateStatus = backOfficeService.editStudentFeesTemplates(studentFeesTemplate);
					model.addAttribute("updateResponse", updateStatus);
					/**Added by @author Saif.Ali
					 * Date- 20/03/2018
					 * Used to insert the information into the activity_log table*//*
					if(updateStatus.equalsIgnoreCase("success")){
						String description = "";
						String oldTemplateName= (request.getParameter("nameOldTemplate"+saveId));
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("EDIT FEES TEMPLATE");
						updateLog.setInsertUpdate("UPDATE");
						updateLog.setModule("OFFICE ADMINISTRATION");
						updateLog.setUpdatedFor(oldTemplateName);
						if(!(getName.equalsIgnoreCase(oldTemplateName)))
						{
							description= description + ("Categoty " + oldTemplateName + " updated to new Categoty :: " + getName); 
						}
						if(!(getCategoryArray.equals(addedCategoryNames)))
						{
							
						}
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
					*//**Addition ends*/
				
			}else{
				logger.info("Invalid HostelFacility Name");
			}
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method editStudentFeesTemplate-POST of HostelController", ce);
		}
		return createStudentFeesTemplate(request, response, model);
	}
	
	/**
	 * @author anup.roy
	 * this method is used for map and assigning amount b/w fees template and a standard
	 * 11.09.2017
	 * */
	
	@RequestMapping(value = "/assignAmountToStudentFeesTemplate", method = RequestMethod.GET)
	public ModelAndView assignAmountToStudentFeesTemplate(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In assignAmountToStudentFeesTemplate() method of BackOfficeController");
		try {
			logger.debug("assignAmountToStudentFeesTemplate()In BackOfficeController.java: calling getAcademicYear() in BackOfficeService.java");
			List<StudentFeesTemplate> allFeesTemplates = backOfficeService.getAllFeesTemplates();
			if (null!= allFeesTemplates && allFeesTemplates.size()!=0) {
				model.addAttribute("allFeesTemplates", allFeesTemplates);
			}
			List<Course> allUnmappedCourses = backOfficeService.getAllUnmappedCourses();
			if (null!= allUnmappedCourses && allUnmappedCourses.size()!=0) {
				model.addAttribute("allUnmappedCourses", allUnmappedCourses);
			}
			List<SocialCategory> socialCategoryList = backOfficeService.getAllSocialCategories();
			if(null!=socialCategoryList && socialCategoryList.size()!=0){
				model.addAttribute("socialCategoryList",socialCategoryList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("backoffice/assignAmountToStudentFeesTemplate");
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting all unmapped standards for a template*/
	
	@RequestMapping(value = "/getUnmappedStandardsToFeesTemplate", method = RequestMethod.GET)
	public @ResponseBody
	String getUnmappedStandardsToFeesTemplate(@RequestParam("templateCode") String templateCode) {
		String standard = "";
		try {
			logger.info("In getUnmappedStandardsToFeesTemplate(templateCode) in BackofficeController.java");
			standard = backOfficeService.getUnmappedStandardsToFeesTemplate(templateCode);
		} catch (Exception e) {
			logger.error("Exception In getUnmappedStandardsToFeesTemplate(templateCode) in BackofficeController.java : ",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(standard));
	}
	
	/**
	 * @author anup.roy*
	 * backoffice new*/
	
	@RequestMapping(value = "/getTemplateWiseFeesStructure", method = RequestMethod.GET)
	@ResponseBody
	public String getTemplateWiseFeesStructure(@RequestParam("templateCode") String templateCode) {
		String str = "";
		try {
			List<FeesCategory> feesCategoryList = backOfficeService.getTemplateWiseFeesStructure(templateCode);
			if(feesCategoryList != null){
				for(FeesCategory fc : feesCategoryList){
					//System.out.println(fc.getFeesCategoryCode()+":"+fc.getFeesCategoryName());
					str = str+fc.getFeesCategoryCode()+":"+fc.getFeesCategoryName()+"~";
				}
			}
			//System.out.println("FINAL STRING:"+str);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStudentForClassStreamSectionAndCourse() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStudentForClassStreamSectionAndCourse() method for Exception: ",e);
		}
		return (new Gson().toJson(str));
	}
	
	/**
	 * @author anup.roy
	 * this method is for submit the amount social category wise
	 * without term
	 * 06.08.2017
	 * Rewa Specific
	 * */
	
	@RequestMapping(value="/submitAmountInStudentFeesTemplate", method = RequestMethod.POST)
	public String submitAmountForStudentFeesTemplate(HttpServletRequest request, HttpServletResponse response, 
													ModelMap model,
													@RequestParam("templateName") String templateName,
													@RequestParam("unMappedCourseName") String courseName,
													@ModelAttribute("sessionObject") SessionObject sessionObject){		
		try{
			List<StudentFeesTemplate> studentFeeTempList = null;
			List<SocialCategory> socialCatList = null;
			String feeHeads[] = request.getParameterValues("feesHead");
			StudentFeesTemplate studentFeeTemplate = new StudentFeesTemplate();
			studentFeeTemplate.setStudentFeesTemplateName(templateName);
			Course cr = new Course();
			cr.setCourseName(courseName);
			studentFeeTemplate.setCourse(cr);
			List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();			
			if (socialCategoryList != null) {
				model.addAttribute("socialCategoryList", socialCategoryList);
			}
			if(null != feeHeads && feeHeads.length != 0){
				studentFeeTempList = new ArrayList<StudentFeesTemplate>();
				for(int i = 0; i<feeHeads.length; i++){
					socialCatList = new ArrayList<SocialCategory>();
					StudentFeesTemplate stuFeeTemp = new StudentFeesTemplate();
					stuFeeTemp.setStudentFeesTemplateCode(studentFeeTemplate.getStudentFeesTemplateName());
					stuFeeTemp.setCourse(studentFeeTemplate.getCourse());
					FeesCategory fCat = new FeesCategory();
					fCat.setFeesCategoryCode(feeHeads[i]);
					for(SocialCategory scat : socialCategoryList){
						String feeHeadAndCategory = request.getParameter(feeHeads[i]+"##"+scat.getSocialCategoryName());
						SocialCategory socialCategory = new SocialCategory();
						socialCategory.setSocialCategoryName(scat.getSocialCategoryName());
						socialCategory.setAmount(Double.parseDouble(feeHeadAndCategory));
						socialCatList.add(socialCategory);
					}
					fCat.setSocialCategoryList(socialCatList);
					stuFeeTemp.setFeesCategory(fCat);
					stuFeeTemp.setUpdatedBy(sessionObject.getUserId());
					studentFeeTempList.add(stuFeeTemp);
				}				
			}
			String submitResponse = backOfficeService.submitAmountForStudentFeesTemplate(studentFeeTempList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return getStudentFeesTemplateWithAmountList(request,response,model);
	}
	
	/**
	 * @author anup.roy*
	 * for assigned template list
	 * backoffice new
	 * */
	
	@RequestMapping(value="/getStudentFeesTemplateWithAmountList", method = RequestMethod.GET)
	public String getStudentFeesTemplateWithAmountList(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		try{
			List<StudentFeesTemplate> studentFeesTemplateWithAmountList = backOfficeService.getstudentFeesTemplateWithAmountList();
			if(studentFeesTemplateWithAmountList != null && studentFeesTemplateWithAmountList.size() != 0){
				model.addAttribute("studentFeesTemplateWithAmountList", studentFeesTemplateWithAmountList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "backoffice/studentFeesTemplateWithAmountList";
	}
	
	/**
	 * @author anup.roy
	 * for edit assigned template amount
	 */
	
	@RequestMapping(value="/viewStudentFeesTemplateAmountDetails", method = RequestMethod.GET)
	public String viewStudentFeesTemplateAmountDetails(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
														@RequestParam("courseName") String courseName,
														@RequestParam("templateCode") String templateCode,
														@RequestParam("templateName") String templateName){
		try{
			if(null != courseName && null != templateCode && null != templateName){
				model.addAttribute("templateName", templateName);
				model.addAttribute("templateCode", templateCode);
				model.addAttribute("courseName", courseName);
				
				List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();			
				if (socialCategoryList != null) {
					model.addAttribute("socialCategoryList", socialCategoryList);
				}
				
				List<FeesCategory> studentFeesTemplateAmountDetailsList = backOfficeService.getStudentFeesTemplateAmountDetails(courseName);
				if(null != studentFeesTemplateAmountDetailsList && studentFeesTemplateAmountDetailsList.size() != 0){
					model.addAttribute("studentFeesTemplateAmountDetailsList", studentFeesTemplateAmountDetailsList);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "backoffice/editAssignAmountToStudentFeesTemplate";
	}
	
	
	/**@author anup.roy
	 * backoffice new
	 **/
	
	@RequestMapping(value="/editAmountsInStudentFeesTemplate", method = RequestMethod.POST)
	public String updateAmountsInStudentFeesTemplate(HttpServletRequest request, HttpServletResponse response, 
													ModelMap model, 
													StudentFeesTemplate studentFeeTemplate,
													@ModelAttribute("sessionObject") SessionObject sessionObject){		
		try{
			List<StudentFeesTemplate> studentFeeTempList = null;
			List<SocialCategory> socialCatList = null;
			String feeHeads[] = request.getParameterValues("feesHead");
			
			List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();			
			
			if(null != feeHeads && feeHeads.length != 0){
				studentFeeTempList = new ArrayList<StudentFeesTemplate>();
				for(int i = 0; i<feeHeads.length; i++){
					socialCatList = new ArrayList<SocialCategory>();
					StudentFeesTemplate stuFeeTemp = new StudentFeesTemplate();
					stuFeeTemp.setStudentFeesTemplateCode(studentFeeTemplate.getStudentFeesTemplateCode());
					stuFeeTemp.setCourse(studentFeeTemplate.getCourse());
					FeesCategory fCat = new FeesCategory();
					fCat.setFeesCategoryCode(feeHeads[i]);
					for(SocialCategory scat : socialCategoryList){
						String feeHeadAndCategory = request.getParameter(feeHeads[i]+"##"+scat.getSocialCategoryName());
						//System.out.println(feeHeads[i]+" -- "+scat.getSocialCategoryName()+" :: "+feeHeadAndCategory);
						
						SocialCategory socialCategory = new SocialCategory();
						socialCategory.setSocialCategoryName(scat.getSocialCategoryName());
						socialCategory.setAmount(Double.parseDouble(feeHeadAndCategory));
						socialCatList.add(socialCategory);
					}
					fCat.setSocialCategoryList(socialCatList);
					stuFeeTemp.setFeesCategory(fCat);
					stuFeeTemp.setUpdatedBy(sessionObject.getUserId());
					studentFeeTempList.add(stuFeeTemp);
				}
			}
			String updateStatus = backOfficeService.editAmountDetailsForStudentFeesTemplate(studentFeeTempList);
			//System.out.println("@@@   updateStatus  ::  "+updateStatus);
		}catch(Exception e){
			e.printStackTrace();
		}
		return getStudentFeesTemplateWithAmountList(request, response, model);
	}
	
	/** @author anup.roy
	 * this method is for receiving session fees
	 */
	
	@RequestMapping(value = "/receiveSessionFee", method = RequestMethod.GET)
	public ModelAndView receiveSessionFee(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String strView = null;
		try {
			logger.info("In BackOfficeController receiveSessionFee() method: calling getClassList() in BackOfficeService.java");
			AcademicYear aY = backOfficeService.selectCurrentAcademicYear();
			if (aY != null) {
				model.addAttribute("aY", aY);
			}
			List<Standard> standardList = academicsService.getStandardsForSetExamMarks();
			if (standardList != null) {
				model.addAttribute("standardList", standardList);
				strView = "backoffice/receiveSessionFee";
			} else {
				logger.info("In receiveSessionFee method of BackOffice Controller :: Error :: Class List not Found ");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController uploadResult() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController uploadResult() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}

	/**@author anup.roy
	 * backoffice new
	 * **/
	
	@RequestMapping(value = "/getCourseForClass", method = RequestMethod.GET)
	public @ResponseBody String getCourseForClass(@RequestParam("standard") String standardCode) {
		String courses = "";
		try {
			logger.info("In BackOfficeController getCourseForClass() method: calling getCourseForAttendance() in BackOfficeService.java");
			courses =  backOfficeService.getCourseForClass(standardCode);
			//System.out.println("courses:: "+courses);
		}catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getCourseForClass() method for NullPointerException: ",e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getCourseForClass() method for Exception: ",e);
		}
		return (new Gson().toJson(courses));
	}
	
	/**@author anup.roy
	 * this method is for get section for standard*/
	
	@RequestMapping(value = "/getSectionForStandard", method = RequestMethod.GET)
	@ResponseBody 
	public String getSectionForStandard(@RequestParam("standard") String standardCode){
		String sections = null;
		try{
			logger.info("In getSectionForStandard() of backofficeController.java");
			sections = backOfficeService.getSectionForStandard(standardCode);
		}catch(NullPointerException e){
			e.printStackTrace();
			logger.error(e);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		return (new Gson().toJson(sections));
	}
	
	/**@author anup.roy
	 * this method is for getting student against section and standard
	 * */
	
	@RequestMapping(value = "/getStudentAgainstSection", method = RequestMethod.GET)
	@ResponseBody
	public String getStudentAgainstSection(@RequestParam("section")  String section,
											@RequestParam("standard") String standard){
		String students = null;
		try{
			logger.info("In getStudentAgainstSection() of backofficeController.java");
			students = backOfficeService.getStudentAgainstSection(section, standard);
			//System.out.println("students::"+students);
		}catch(NullPointerException e){
			e.printStackTrace();
			logger.error(e);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		return (new Gson().toJson(students));
	}

	/**@author anup.roy
	 * this method is for getting fees details for student
	 * */
	
	@RequestMapping(value = "/getFeeStructureAgainstStudent", method = RequestMethod.GET)
	public @ResponseBody
	String getFeeStructureAgainstStudent(
			@RequestParam("standard") String standard,
			@RequestParam("academicSession") String academicSession,
			@RequestParam("rollNumber") String rollNumber) {
		String feeStructureList = "";
		try {
			logger.info("In BackOfficeController getStudentsResult() method: calling getStudentsResult() in BackOfficeService.java");
			FeesCategory category = new FeesCategory();
			category.setAcademicYear(academicSession);
			category.setKlass(standard);
			category.setStudent(rollNumber);
			feeStructureList = backOfficeService.getFeeStructureAgainstStudent(category);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStudentsResult() method for NullPointerException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController getStudentsResult() method for Exception: ",e);
		}
		return (new Gson().toJson(feeStructureList));
	}
	
	/**@author anup.roy
	 * delete assigned fees template**/
	
	@RequestMapping(value="/deleteStudentFeesTemplateAmountDetails", method = RequestMethod.GET)
	public String deleteStudentFeesTemplateAmountDetails(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
														@RequestParam("courseName") String courseName,
														@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			Resource r=new Resource();
			r.setUpdatedBy(sessionObject.getUserId());
			r.setCourseName(courseName);
			String status = backOfficeService.deleteStudentFeesTemplateAmountDetails(r);
			model.addAttribute("status",status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return getStudentFeesTemplateWithAmountList(request, response, model);
	}
	
	/**
	 * author kaustav sen*/
	
	@RequestMapping(value = "/studentAttendanceShow", method = RequestMethod.GET)
	public ModelAndView studentAttendanceShow(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In studentAttendanceShow() method ");
		List<Term> listTerm = null;
		List<CalendarEvent> specialHolidays = null;
		try {
			logger.debug("showStudentAttendance()In BackOfficeController.java: calling fetchclassDetails() in BackOfficeService.java");
			listTerm = backOfficeService.listTermDetails();
			if (listTerm != null) {
				model.addAttribute("listTerm", listTerm);
			}
			specialHolidays = backOfficeService.listSpecialHolidays();
			if (specialHolidays != null) {
				model.addAttribute("specialHolidays", specialHolidays);
			}
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
			}
			AcademicYear currentYear = commonService.getCurrentAcademicYear();
			if (currentYear != null) {
				model.addAttribute("currentYear", currentYear);
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
			List<Course>courseList = commonService.getCourseList();
			
			model.addAttribute("courseList", courseList);
		} catch (NullPointerException e) {
			logger.error("teacherAttendance() In BackOfficeController.java: NullPointerException"+ e);
		} catch (Exception e) {
			logger.error("teacherAttendance() In BackOfficeController.java: Exception" + e);
		}
		return new ModelAndView("backoffice/studentAttendance");
	}
	
	@RequestMapping(value = "/resourceAttendanceDownload", method = RequestMethod.GET)
	public String resourceAttendanceDownload(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										@RequestParam("folderParam") String folderParam,
										@RequestParam("fileParam") String fileParam){
		try {
			/*logger.info("Inside resourceAttendanceDownload() of BackOfficeController");
			List<String> academicYearList = null;
			String fullPath = rootPath+excelUploadfolderteacher;
			String fullPath1=rootPath+excelUploadfolderteacher;
			
			if(!fileParam.equals("noFile")){
				fullPath = fullPath  +folderParam;
				System.out.println("@@ fullPath : "+fullPath);
				FileUploadDownload fileUploadDownload = new FileUploadDownload();
				fileUploadDownload.downloadFile(response, fullPath, fileParam);
			}
			else{
				File file = new File(fullPath);
				File[] listOfFiles = file.listFiles();
				System.out.println(listOfFiles.length);
				if(null != listOfFiles && listOfFiles.length != 0){
					academicYearList = new ArrayList<String>();
					for(int i= 0; i<listOfFiles.length; i++){
						if(listOfFiles[i].isDirectory()){
							academicYearList.add(listOfFiles[i].getName());		
							System.out.println("5013"+academicYearList);
						}
					}				
				}
			}
			if(null != academicYearList && academicYearList.size() != 0){
				model.addAttribute("academicYearList", academicYearList);
			}
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
			}
			List<JobType> jobType= erpService.getAllJobType();
			model.addAttribute("jobType",jobType);
			
			List<Course>courseList = academicsService.getCourseList();
			model.addAttribute("courseList",courseList);*/
			/**new code for download from external repository*/
			logger.info("Inside resourceAttendanceDownload() of BackOfficeController");
			
			List<String> academicYearList = null;
			/*String fullPath = rootPath+excelUploadfolderteacher;
			String fullPath1=rootPath+excelUploadfolderteacher;*/
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			String fullPath = repository+"/"+excelUploadfolderteacher;
			String fullPath1= repository+"/"+excelUploadfolderteacher;
			if(!fileParam.equals("noFile")){
				fullPath = fullPath  +folderParam;
				//System.out.println("@@ fullPath : "+fullPath);
				FileUploadDownload fileUploadDownload = new FileUploadDownload();
				fileUploadDownload.downloadFile(response, fullPath, fileParam);
		
			}
			else{
				File file = new File(fullPath);
				File[] listOfFiles = file.listFiles();
				//System.out.println(listOfFiles.length);
				if(null != listOfFiles && listOfFiles.length != 0){
					academicYearList = new ArrayList<String>();
					for(int i= 0; i<listOfFiles.length; i++){
						if(listOfFiles[i].isDirectory()){
							academicYearList.add(listOfFiles[i].getName());		
							//System.out.println("5013"+academicYearList);
						}
					}				
				}
			}
			
			if(null != academicYearList && academicYearList.size() != 0){
				model.addAttribute("academicYearList", academicYearList);
			}
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
			}
			
			
			List<JobType> jobType= erpService.getAllJobType();
			model.addAttribute("jobType",jobType);
			
			List<Course>courseList = academicsService.getCourseList();
			model.addAttribute("courseList",courseList);
		}
		catch (Exception ce){
			logger.error("Exception in viewDownloadAssignment() of CommonController", ce);
			ce.printStackTrace();
		}
		return "common/downloadTeacherExcelSheet";
		
		}
	
	@RequestMapping(value = "/getStudentBatchAgainstCourse", method = RequestMethod.GET)
	public @ResponseBody
	String getBatchAgainstCourse(@RequestParam("course") String course) {
		String StudentBatch = null;
		try {
			//System.out.println("inside cntrlr kaustav "+course);
			logger.info("getBatchAgainstCourse() In HostelController.java");
			StudentBatch = commonService.getBatchAgainstCourse(course);
			//System.out.println(StudentBatch);
		} catch (NullPointerException e) {
			logger.error("getBatchAgainstCourse() In HostelController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getBatchAgainstCourse() In HostelController.java: Exception"
					+ e);
		}
		return StudentBatch;
	}
	
	@RequestMapping(value = "/resourceStudentAttendanceDownload", method = RequestMethod.GET)
	public String resourceStudentAttendanceDownload(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										/*@RequestParam("paperDirName") String paperDirName,*/
										@RequestParam("folderParam") String folderParam,
										@RequestParam("fileParam") String fileParam){
		String ret = "backoffice/downloadStudentAttendance";
		try {
			/*logger.info("Inside resourceStudentAttendanceDownload() of BackOfficeController");
			List<String> academicYearList = null;
			String fullPath = rootPath+excelStudentUploadfolder;
			String fullPath1=rootPath+excelStudentUploadfolder;
			if(!fileParam.equals("noFile")){
				fullPath = fullPath + folderParam + "/";
				//fullPath = fullPath  +fileParam;
				
				FileUploadDownload fileUploadDownload = new FileUploadDownload();
				fileUploadDownload.downloadFile(response, fullPath, fileParam);
			}else{
				File file = new File(fullPath);
				File[] listOfFiles = file.listFiles();
				if(null != listOfFiles && listOfFiles.length != 0){
					academicYearList = new ArrayList<String>();
					for(int i= 0; i<listOfFiles.length; i++){
						if(listOfFiles[i].isDirectory()){
							academicYearList.add(listOfFiles[i].getName());
						
						}
					}				
				}
			}
			if(null != academicYearList && academicYearList.size() != 0){
				
				model.addAttribute("academicYearList", academicYearList);
			}
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
			}
			
			List<Course>courseList = academicsService.getCourseList();
			model.addAttribute("courseList",courseList);*/
			logger.info("Inside resourceStudentAttendanceDownload() of BackOfficeController");
			
			List<String> academicYearList = null;
			/*String fullPath = rootPath+excelStudentUploadfolder;
			String fullPath1=rootPath+excelStudentUploadfolder;*/
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			String fullPath = repository+"/"+excelStudentUploadfolder;
			String fullPath1=repository+"/"+excelStudentUploadfolder;			
			if(!fileParam.equals("noFile")){
				fullPath = fullPath + folderParam + "/";
				//fullPath = fullPath  +fileParam;				
				FileUploadDownload fileUploadDownload = new FileUploadDownload();
				fileUploadDownload.downloadFile(response, fullPath, fileParam);		
			}else{				
				File file = new File(fullPath);
				File[] listOfFiles = file.listFiles();
				
				if(null != listOfFiles && listOfFiles.length != 0){
					academicYearList = new ArrayList<String>();
					for(int i= 0; i<listOfFiles.length; i++){
						if(listOfFiles[i].isDirectory()){
							academicYearList.add(listOfFiles[i].getName());
						
						}
					}				
				}
			}		
			if(null != academicYearList && academicYearList.size() != 0){
				
				model.addAttribute("academicYearList", academicYearList);
			}
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
			}			
			List<Course>courseList = academicsService.getCourseList();
			model.addAttribute("courseList",courseList);
		}
		catch (Exception ce){
			logger.error("Exception in viewDownloadAssignment() of CommonController", ce);
			ce.printStackTrace();
		}
		//System.out.println("before return::::"+paperDirName);
		return ret;
	}
	
	@RequestMapping(value = "/getAssignmentStudentExcelName", method = RequestMethod.GET)
	@ResponseBody
	public String getAssignmentStudentExcelName(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("paperDirName") String paperDirName){
		logger.info("Inside getAssignmentExcelName() of BackOfficeController");
		String allDirs = "";
		
	
		try {
			
			/*String fullPath1 = rootPath+excelStudentUploadfolder;*/
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			String fullPath1 = repository+"/"+excelStudentUploadfolder;
			
			String fullPath = fullPath1+paperDirName+"/";
			//System.out.println("fullpath=="+fullPath);
			File file = new File(fullPath);
			File file1= new File(fullPath1);
			File[] listOfFiles = file.listFiles();
			
			if(null != listOfFiles && listOfFiles.length != 0){
				for(int i= 0; i<listOfFiles.length; i++){
					//System.out.println("bbbbb");
					if(listOfFiles[i].isDirectory() || listOfFiles[i].isFile()){
						
						//if(listOfFiles[i].getName().equals("resourceStudentAttendanceExcel")){
						allDirs = allDirs + listOfFiles[i].getName() + "~";
						//}
						
					}
				}				
			}
			
		} catch (Exception ce) {
			logger.error("Exception in getQuestionFolderNames() of AcademicsController", ce);
			
		}
		
		return (new Gson().toJson(allDirs));
	}
	
	@RequestMapping(value = "/studentAttendanceUpload", method = RequestMethod.POST)
	public ModelAndView studentAttendanceUpload(HttpServletRequest request,HttpServletResponse response,
												@ModelAttribute("uploadFile") UploadFile uploadFile,
												ModelMap model,StudentResult studentResult,
												@RequestParam("year") String year,
												@RequestParam("month") String month,
												@RequestParam("course") String course,
												@RequestParam("batch") String batch,
												@ModelAttribute("sessionObject") SessionObject sessionObject
												){
		
		
		try{
			/*String path=year+"/"+month+"/"+course+"/"+batch+"/";
			
			
			FileUploadDownload util = new FileUploadDownload();
			String finalPath=rootPath+excelStudentUploadfolder+path;
			
			String file= "FILE";
			System.out.println("@@@::"+excelStudentUploadfolder);
			String returnMessage = util.uploadExcel1(uploadFile,finalPath,file);
		
			System.out.println("returnMessage::"+returnMessage);
			//String actualFolderPathForUpload = rootPath+excelStudentUploadfolder+resourceStudentAttendanceExcel;
			//System.out.println("folderUploadpath::"+actualFolderPathForUpload);
			if(returnMessage==null){
				model.addAttribute("uploadStatus", "fail");
				model.addAttribute("uploadErrorMessage", "File"+" upload failed.");
			}else{
				model.addAttribute("uploadStatus", "success");
				model.addAttribute("uploadErrorMessage", "File"+" sucessfully uploaded."); 
				//String updatedBy = sessionObject.getUserId();
				//int resourceAttendanceFlag = backOfficeService.uploadResourceAttendance(actualFolderPathForUpload,updatedBy);
				//System.out.println("resourceAttendanceFlag::"+resourceAttendanceFlag);
			}*/

			
			
			String path=year+"/"+month+"/"+course+"/"+batch+"/";
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			File directory = new File(repository);
			boolean isExists = directory.exists();
			String insertStatus = null;
			if(isExists == true){
				FileUploadDownload util = new FileUploadDownload();
				String finalPath = repository+"/"+excelStudentUploadfolder+path;
				String file= "FILE";
				//System.out.println("@@@::"+excelStudentUploadfolder);
				String returnMessage = util.uploadExcel1(uploadFile,finalPath,file);
				//System.out.println("returnMessage::"+returnMessage);
				if(returnMessage==null){
					model.addAttribute("uploadStatus", "fail");
					model.addAttribute("uploadErrorMessage", "File"+" upload failed.");
				}else{
					model.addAttribute("uploadStatus", "success");
					model.addAttribute("uploadErrorMessage", "File"+" sucessfully uploaded."); 
				}
			}else{
				System.out.println("directory not found.");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in resourceAttendanceUpload() for upload Template Excel", e);
		}
	return studentAttendanceShow(request,response,model);
	}
	
	@RequestMapping(value = "/getAssignmentExcelName", method = RequestMethod.GET)
	@ResponseBody
	public String getAssignmentExcelName(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("paperDirName") String paperDirName){
		logger.info("Inside getAssignmentExcelName() of BackOfficeController");
		String allDirs = "";
		
	
		try {
			/*String fullPath1 = rootPath+excelUploadfolderteacher+paperDirName;*/
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			String fullPath1 = repository+"/"+excelUploadfolderteacher+paperDirName;
			
		
		
			File file1= new File(fullPath1);
			File[] listOfFiles = file1.listFiles();
		
			if(null != listOfFiles && listOfFiles.length != 0){
				for(int i= 0; i<listOfFiles.length; i++){
					//System.out.println("bbbbb");
					if(listOfFiles[i].isDirectory() || listOfFiles[i].isFile()){
						
						allDirs = allDirs + listOfFiles[i].getName() + "~";	}
						
					
				}				
			}
			
		} catch (Exception ce) {
			logger.error("Exception in getQuestionFolderNames() of AcademicsController", ce);
			
		}
		//System.out.println("kaustav"+allDirs);
		return (new Gson().toJson(allDirs));
	}
	
/***added by sourav.bhadra***/
	
	@RequestMapping(value = "/insertAllStudentAttendance", method = RequestMethod.GET)
	public ModelAndView insertAllStudentAttendance(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("hiddenAbsentDay") String absentdays,
			@RequestParam("hiddenDetails") String studentdetails,
			@RequestParam("hiddenId") String resourceId,
			@RequestParam("hiddenType") String hiddenType,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		try {
			List<Term> listTerm = null;
			List<CalendarEvent> specialHolidays = null;
			String[] studentattendancedetails = absentdays.split(" >>>NEXT ATTENDANCE >>>");
			
			String[] studentRollNos = resourceId.split(" >>NEXT ROLL >>");
			
			String resourceTypeName = hiddenType;
			if(studentRollNos.length == studentattendancedetails.length){
				for(int a=0; a<studentRollNos.length; a++){
					String resourceID = studentRollNos[a];
					String[] presentDays = studentattendancedetails[a].split(",");
					
					/**old code of insertStudentAttendance**/
					
					String showStudentanother = null;
					
					String[] datedetails = studentdetails.split(",");
	
					Map<String, Object> insertedStudentMapDB = new HashMap<String, Object>();
					logger.debug("insertStudentAttendance()In BackOfficeController.java: calling insertStudentAttendance(),updateStudentAttendance(),updateCheckedStudentAttendance() in BackOfficeService.java");
					String[] previousdetails = null;
					
					StudentAttendance studentAttendanceanother = new StudentAttendance();
					for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
						studentAttendanceanother.setMonth(datedetails[0]);
						studentAttendanceanother.setYear(datedetails[1]);
					}
					ResourceType resourceType = new ResourceType();
					resourceType.setResourceTypeName(resourceTypeName);
					if (hiddenType.equals("STUDENT")) {
						showStudentanother = backOfficeService.fetchStudentId(studentAttendanceanother);
						if (showStudentanother != null) {
							previousdetails = showStudentanother.split("&");
							for (int listnum = 0; listnum < previousdetails.length; listnum++) {
								String[] splitedinfo = previousdetails[listnum].split("~");
								insertedStudentMapDB.put(splitedinfo[0], splitedinfo[1]);
							}
						}
						StudentAttendance studentAttendance = new StudentAttendance();
						if (insertedStudentMapDB.containsKey(resourceID)) {
							//int x = Integer.parseInt(resourceID);
							studentAttendance.setStudentRollNo(resourceID);
							backOfficeService.updateStudentAttendance(studentAttendance);
							for (int listnumber = 0; listnumber < presentDays.length; listnumber++) {
								//int y = Integer.parseInt(resourceID);
								studentAttendance.setStudentRollNo(resourceID);
								studentAttendance.setResourceType(resourceType);
								if (presentDays[listnumber] != "") {
									studentAttendance.setAbsentDay(presentDays[listnumber]);
								}
								for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
									studentAttendance.setMonth(datedetails[0]);
									studentAttendance.setYear(datedetails[1]);
								}
								studentAttendance.setUpdatedBy(sessionObject.getUserId());
								backOfficeService.insertStudentAttendance(studentAttendance);
							}
						} else {
							for (int listnumber = 0; listnumber < presentDays.length; listnumber++) {
								//int z = Integer.parseInt(resourceID);
								studentAttendance.setStudentRollNo(resourceID);
								studentAttendance.setResourceType(resourceType);
								if (presentDays[listnumber] != "") {
									studentAttendance.setAbsentDay(presentDays[listnumber]);
								}
								for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
									studentAttendance.setMonth(datedetails[0]);
									studentAttendance.setYear(datedetails[1]);
								}
								studentAttendance.setUpdatedBy(sessionObject.getUserId());
								backOfficeService.insertStudentAttendance(studentAttendance);
							}
						}
					}
				
				}
			}
			
			listTerm = backOfficeService.listTermDetails();
			specialHolidays = backOfficeService.listSpecialHolidays();
			AcademicYear currentYear = commonService.getCurrentAcademicYear();
			if (currentYear != null) {
				model.addAttribute("currentYear", currentYear);
			}
			if (specialHolidays != null) {
				model.addAttribute("specialHolidays", specialHolidays);
			}
			if (listTerm != null) {
				model.addAttribute("listTerm", listTerm);
			}
			if (studentdetails != null) {
				model.addAttribute("studentdetails", studentdetails);
			}
			if (hiddenType != null) {
				model.addAttribute("resourceType", hiddenType);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resourceAttendance (request,response,model);
	}
	
	@RequestMapping(value = "/teacherAttendancePost", method = RequestMethod.GET)
	public ModelAndView teacherAttendancePost(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In teacherAttendancePost() method ");
		List<Term> listTerm = null;
		List<CalendarEvent> specialHolidays = null;
		try{
			//System.out.println("teacherAttendancePost() reached");
			logger.info("showStudentAttendance()In BackOfficeController.java: calling fetchclassDetails() in BackOfficeService.java");
			listTerm = backOfficeService.listTermDetails();
			if (listTerm != null) {
				model.addAttribute("listTerm", listTerm);
			}
			specialHolidays = backOfficeService.listSpecialHolidays();
			if (specialHolidays != null) {
				model.addAttribute("specialHolidays", specialHolidays);
			}
			List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
				
			}else{
				//System.out.println(" resourceTypeList null....LN7863....");
			}
			AcademicYear currentYear = commonService.getCurrentAcademicYear();
			if (currentYear != null) {
				//System.out.println(currentYear);
				model.addAttribute("currentYear", currentYear);
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
			
			List<JobType> jobTypeList = erpService.getAllJobType();
			model.addAttribute("jobTypeList", jobTypeList);
			
			//System.out.println("teacherAttendancePost() ends");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("backoffice/teacherAttendancePost");
	}
	
	@RequestMapping(value = "/getTeachersForAttendance", method = RequestMethod.GET)
	public @ResponseBody
	String getTeachersForAttendance(
									@RequestParam("year") String selectedyear,
									@RequestParam("month") String selectedmonth,
									@RequestParam("jobtype") String jobTypeName,
									@RequestParam("resourceTypeName") String resourceTypeName,
									StudentAttendance studentAttendance) {
		
		String finaldata = "";
		//System.out.println("In getTeacherForAttendance()...LN7855");
		try {
			//System.out.println("getTeacherForAttendance() in BackOfficeController reached...");
			//System.out.println("7855 in backofcCon::\nyear::"+selectedyear+"\nmonth::"+selectedmonth);
			logger.info("In getTeacherForAttendance() method ");
			
			ResourceType resourceType = new ResourceType();
			resourceType.setResourceTypeName(resourceTypeName);
			
			
			studentAttendance.setYear(selectedyear);
			studentAttendance.setMonth(selectedmonth);
			studentAttendance.setResourceType(resourceType);
			String showStudentanother = backOfficeService.fetchTeacherId(studentAttendance);
			
			JobType jobtype = new JobType();
			jobtype.setJobTypeName(jobTypeName);
			
			Resource resource = new Resource();
			resource.setResourceTypeName(resourceTypeName);
			resource.setJobType(jobtype);
			
			String showStudent = backOfficeService.getTeachersForAttendance(resource);
			finaldata = showStudentanother + "@" + showStudent;
			System.out.println("7872 in backofcController::"+finaldata);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(finaldata));
	}
	
	/**
	 * modified by sourav.bhadra
	 * changes taken on 21042017**/
	
	@RequestMapping(value = "/insertTeacherAttendance", method = RequestMethod.GET)
	public ModelAndView insertTeacherAttendance(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("hiddenAbsentDay") String presentdays,
			@RequestParam("hiddenDetails") String teacherAttendancedetails,
			@RequestParam("hiddenId") String teacherId,
			@RequestParam("hiddenType") String resourceType,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			List<Term> listTerm = null;
			List<CalendarEvent> specialHolidays = null;
			String showTeacheranother = null;
			String[] teacherattendancedetails = presentdays.split(",");
			String[] datedetails = teacherAttendancedetails.split(",");
			StudentAttendance teacherAttendanceanother = new StudentAttendance();
			Map<String, Object> insertedTeacherMapDB = new HashMap<String, Object>();
			logger.debug("insertTeacherAttendance()In BackOfficeController.java: calling insertTeacherAttendance(),updateTeacherAttendance(),updateCheckedStudentAttendance() in BackOfficeService.java");
			String[] previousdetailsForTeacher = null;
			for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
				teacherAttendanceanother.setMonth(datedetails[0]);
				teacherAttendanceanother.setYear(datedetails[1]);
			}
			ResourceType resourceTypeObj = new ResourceType();
			resourceTypeObj.setResourceTypeName(resourceType);
			
			/*ticket configuration for manual teacher attendance(single insert)*/
			ServiceType attendanceService = new ServiceType();
			attendanceService.setTicketServiceCode("JOB-19");
			Ticket ticketForTeacherAttendance = new Ticket();
			ticketForTeacherAttendance.setReportedBy(sessionObject.getUserId());
			ticketForTeacherAttendance.setUpdatedBy(sessionObject.getUserId());
			ticketForTeacherAttendance.setStatus("OPEN");
			ticketForTeacherAttendance.setTicketSummary("Teacher Attendance");
			ticketForTeacherAttendance.setDescription("Teacher Attendance");
			ticketForTeacherAttendance.setTicketService(attendanceService);
			
			if (resourceType.equals("TEACHING STAFF")) {
				teacherAttendanceanother.setResourceType(resourceTypeObj);
				showTeacheranother = backOfficeService.fetchTeacherId(teacherAttendanceanother);
				if (showTeacheranother != null) {
					previousdetailsForTeacher = showTeacheranother.split("&");
					for (int listnum = 0; listnum < previousdetailsForTeacher.length; listnum++) {
						String[] splitedinfo = previousdetailsForTeacher[listnum].split("~");
						insertedTeacherMapDB.put(splitedinfo[0], splitedinfo[1]);
					}
				}
				StudentAttendance teacherAttendance = new StudentAttendance();
				if (insertedTeacherMapDB.containsKey(teacherId)) {
					teacherAttendance.setResourceId(teacherId);
					backOfficeService.updateTeacherAttendance(teacherAttendance);
					for (int listnumber = 0; listnumber < teacherattendancedetails.length; listnumber++) {
						teacherAttendance.setResourceId(teacherId);
						teacherAttendance.setResourceType(resourceTypeObj);
						if (teacherattendancedetails[listnumber] != "") {
							teacherAttendance.setAbsentDay(teacherattendancedetails[listnumber]);
						}
						for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
							teacherAttendance.setMonth(datedetails[0]);
							teacherAttendance.setYear(datedetails[1]);
						}
						teacherAttendance.setUpdatedBy(sessionObject.getUserId());
						backOfficeService.insertTeacherAttendance(teacherAttendance);
					}
				} else {
					for (int listnumber = 0; listnumber < teacherattendancedetails.length; listnumber++) {
						teacherAttendance.setResourceId(teacherId);
						teacherAttendance.setResourceType(resourceTypeObj);
						if (teacherattendancedetails[listnumber] != "") {
							teacherAttendance.setAbsentDay(teacherattendancedetails[listnumber]);
						}
						for (int secondlistnumber = 0; secondlistnumber < datedetails.length; secondlistnumber++) {
							teacherAttendance.setMonth(datedetails[0]);
							teacherAttendance.setYear(datedetails[1]);
						}
						teacherAttendance.setUpdatedBy(sessionObject.getUserId());
						backOfficeService.insertTeacherAttendance(teacherAttendance);
					}
				}
				/*ticket & notification creation for manual teacher attendance (single insert)*/
				ticketForTeacherAttendance = commonService.generateTicket(ticketForTeacherAttendance);
			}

			listTerm = backOfficeService.listTermDetails();
			specialHolidays = backOfficeService.listSpecialHolidays();
			AcademicYear currentYear = commonService.getCurrentAcademicYear();
			if (currentYear != null) {
				model.addAttribute("currentYear", currentYear);
			}
			if (specialHolidays != null) {
				model.addAttribute("specialHolidays", specialHolidays);
			}
			if (listTerm != null) {
				model.addAttribute("listTerm", listTerm);
			}
			if (teacherAttendancedetails != null) {
				model.addAttribute("studentdetails", teacherAttendancedetails);
			}
			if (resourceType != null) {
				model.addAttribute("resourceType", resourceType);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacherAttendancePost (request,response,model);
	}
	
	/**
	 * modified by sourav.bhadra
	 * changes taken on 21042017**/
	
	@RequestMapping(value = "/insertAllTeacherAttendance", method = RequestMethod.GET)
	public ModelAndView insertAllTeacherAttendance(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("hiddenAbsentDay") String presentdays,
			@RequestParam("hiddenDetails") String teacherAttendancedetails,
			@RequestParam("hiddenId") String resourceId,
			@RequestParam("hiddenType") String resourceType,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			List<Term> listTerm = null;
			List<CalendarEvent> specialHolidays = null;
			String[] teachersattendancedetails = presentdays.split(" >>>NEXT ATTENDANCE >>>");
			String[] teachersID = resourceId.split(" >>NEXT USER >>");
			String resourceTypeName = resourceType;
			
			/*ticket configuration code for teacher attendance (submitAll)*/
			ServiceType attendanceService = new ServiceType();
			attendanceService.setTicketServiceCode("JOB-19");
			Ticket ticketForTeacherAttendance = new Ticket();
			ticketForTeacherAttendance.setReportedBy(sessionObject.getUserId());
			ticketForTeacherAttendance.setUpdatedBy(sessionObject.getUserId());
			ticketForTeacherAttendance.setStatus("OPEN");
			ticketForTeacherAttendance.setTicketSummary("Teacher Attendance");
			ticketForTeacherAttendance.setDescription("Teacher Attendance");
			ticketForTeacherAttendance.setTicketService(attendanceService);

			if(teachersID.length == teachersattendancedetails.length){
				for(int a=0; a<teachersID.length; a++){
					String resourceID = teachersID[a];
					String[] presentDays = teachersattendancedetails[a].split(",");
					
					/**old code of insertTeacherAttendance**/
					String showTeacheranother = null;
					String[] datedetails = teacherAttendancedetails.split(",");
					Map<String, Object> insertedTeacherMapDB = new HashMap<String, Object>();
					logger.debug("insertStudentAttendance()In BackOfficeController.java: calling insertStudentAttendance(),updateStudentAttendance(),updateCheckedStudentAttendance() in BackOfficeService.java");
					String[] previousdetails = null;
					StudentAttendance teacherAttendanceanother = new StudentAttendance();
					teacherAttendanceanother.setMonth(datedetails[0]);
					teacherAttendanceanother.setYear(datedetails[1]);
					ResourceType resourceTypeObj = new ResourceType();
					resourceTypeObj.setResourceTypeName(resourceTypeName);
					if (resourceType.equals("TEACHING STAFF")) {
						teacherAttendanceanother.setResourceType(resourceTypeObj);
						showTeacheranother = backOfficeService.fetchTeacherId(teacherAttendanceanother);
						if (showTeacheranother != null) {
							previousdetails = showTeacheranother.split("&");
							for (int listnum = 0; listnum < previousdetails.length; listnum++) {
								String[] splitedinfo = previousdetails[listnum].split("~");
								insertedTeacherMapDB.put(splitedinfo[0], splitedinfo[1]);
							}
						}
						StudentAttendance teacherAttendance = new StudentAttendance();
						if (insertedTeacherMapDB.containsKey(resourceID)) {
							teacherAttendance.setResourceId(resourceID);
							backOfficeService.updateTeacherAttendance(teacherAttendance);
							for(int b=0; b<presentDays.length; b++){
								if(presentDays[b] != ""){
									teacherAttendance.setAbsentDay(presentDays[b]);
									teacherAttendance.setResourceId(resourceID);
									teacherAttendance.setResourceType(resourceTypeObj);
									teacherAttendance.setUpdatedBy(sessionObject.getUserId());
									teacherAttendance.setMonth(datedetails[0]);
									teacherAttendance.setYear(datedetails[1]);
									backOfficeService.insertTeacherAttendance(teacherAttendance);
								}
								
							}
						} else {
							for(int b=0; b<presentDays.length; b++){
								if(presentDays[b] != ""){
									teacherAttendance.setAbsentDay(presentDays[b]);
									teacherAttendance.setResourceId(resourceID);
									teacherAttendance.setResourceType(resourceTypeObj);
									teacherAttendance.setUpdatedBy(sessionObject.getUserId());
									teacherAttendance.setMonth(datedetails[0]);
									teacherAttendance.setYear(datedetails[1]);
									backOfficeService.insertTeacherAttendance(teacherAttendance);
								}
							}
						}
					}
				
				}
				/*creating ticket and notification for teacher attendance (submitAll)*/
				ticketForTeacherAttendance = commonService.generateTicket(ticketForTeacherAttendance);
			}
			
			listTerm = backOfficeService.listTermDetails();
			specialHolidays = backOfficeService.listSpecialHolidays();
			AcademicYear currentYear = commonService.getCurrentAcademicYear();
			if (currentYear != null) {
				model.addAttribute("currentYear", currentYear);
			}
			if (specialHolidays != null) {
				model.addAttribute("specialHolidays", specialHolidays);
			}
			if (listTerm != null) {
				model.addAttribute("listTerm", listTerm);
			}
			if (teacherAttendancedetails != null) {
				model.addAttribute("studentdetails", teacherAttendancedetails);
			}
			if (resourceType != null) {
				model.addAttribute("resourceType", resourceType);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacherAttendancePost (request,response,model);
	}
	
	@RequestMapping(value = "/getDateOfCreationForInsertedTeacher", method = RequestMethod.GET)
	public @ResponseBody
	String getDateOfCreationForInsertedTeacher(@RequestParam("month") String month,
							@RequestParam("year") String year,
							@RequestParam("teacherId") String teacherId) {
		String sm = "";
		try {
			//System.out.println("in BOC...LN8093..\nMonth::"+month+"\tYear::"+year+"\tTeacherID::"+teacherId);
			sm = backOfficeService.getDateOfCreationForInsertedTeacher(month, year, teacherId);
			//System.out.println("in getDateOfCreationForInsertedTeacher of BOC...LN8094 \nsm::"+sm);
		} catch (NullPointerException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return (new Gson().toJson(sm));
	}
	
/**************Added By Naimisha 13042017****************/
	
	
	@RequestMapping(value = "/getLocationTeacherMapping", method = RequestMethod.GET)
	public String getLocationTeacherMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,SessionObject sessionObject) {
		logger.info("In getLocationTeacherMapping() of Admissioncontroller.java");
		String str = "backoffice/locationTeacherMapping";
		try {
			List<Venue>venueTypeList = venueService.getVenueTypeList();
			model.addAttribute("venueTypeList", venueTypeList);
			Resource resource = administratorService.getResourceAndRolesFromDB();
			List<ResourceType>resourceTypeList = resource.getResourceTypeList();
			if(null!=resource){
				model.addAttribute("resourceTypeList",resourceTypeList);
			}
			//List<Standard> standardList=academicsService.getStandardsForAssignBatch();
			List<Course>courseList = backOfficeService.getProgramsForInterviewPanel();
			model.addAttribute("standardList", courseList);
			List<LocationDetailsForPortal> locationDetailsList = admissionService.setLocationDetailsForPortal();
			//List<LocationDetailsForPortal> locationDetailsList = admissionService.setInterviewLocationDetailsToPortal();
			model.addAttribute("locationDetailsList", locationDetailsList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getLocationTeacherMapping() of Admissioncontroller.java: ",e);
		}
		return str;
	}
	
	@RequestMapping(value = "/insertVenueTeacherMapping", method = RequestMethod.POST)
	public String insertVenueTeacherMapping(HttpServletRequest request,
											HttpServletResponse response, ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject,
											Venue venue
											) {
		String strView = null;
		String insertStatus  = null;
		try {
			logger.info("In insertallocateTask() method of Administrator Controller");
			//strView = "administrator/taskAllocation";
			Resource resource = new Resource();
			List<ResourceType> resourceTypeList = new ArrayList<ResourceType>();
			resource.setUpdatedBy(sessionObject.getUserId());
			resource.setCourseName(venue.getStandard());
			resource.setCode(venue.getVenueCode());
			resource.setDesc(venue.getVenueTypeCode());
			//int taskIndex = Integer.parseInt(request.getParameter("taskIndex"));
			int resourceIndex = Integer.parseInt(request.getParameter("resourceIndex"));
			//System.out.println("taskIndex====="+taskIndex);
			System.out.println("resourceIndex======"+resourceIndex);
			for(int i=0;i<=resourceIndex;i++){
				ResourceType resourceType = new ResourceType();
				String userId = request.getParameter("userName"+i);
				String userName = request.getParameter("name"+i);
				//String desc = request.getParameter("desc"+i);
				resourceType.setResourceTypeCode(userId);
				resourceType.setResourceTypeName(userName);
				System.out.println("userId=="+userId);
				System.out.println("userName=="+userName);
				//resourceType.setResourceTypeDesc(desc);
				resourceTypeList.add(resourceType);
			}
			resource.setResourceTypeList(resourceTypeList);
			//resource.setRegistrationId(ticketCode);
			insertStatus = backOfficeService.insertIntoVenueTeacherMapping(resource);
			System.out.println("insertStatus======"+insertStatus);
			model.addAttribute("insertStatus", insertStatus);
			if (null != insertStatus) {
				if (insertStatus.equalsIgnoreCase("success")) {
					model.addAttribute("successMessage", "Teacher Venue Mapping done SuccessFully");
				} else {
					model.addAttribute("errorMessage", "Teacher Venue Mapping Failed");			
				}
			}	
					
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("insertallocateTask() In AdministratorController.java: Exception", e);
		}
		return getLocationTeacherMapping(request,response,model,sessionObject) ;
	}
	
	/*@RequestMapping(value = "/inactiveVenueTeacherMapping", method = RequestMethod.GET)
	public ModelAndView inactiveVenueTeacherMapping(HttpServletRequest request, HttpServletResponse response
										, ModelMap model,@RequestParam("venueTeacherMappingId")String venueTeacherMappingId,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String mav = null;
		List<Course> courseList=new ArrayList<Course>();
		System.out.println("venueTeacherMappingId=="+venueTeacherMappingId); //Naimisha
		try{
			
				
					Venue venue = new Venue();
					venue.setVenueId(Integer.parseInt(venueTeacherMappingId));
					venue.setUpdatedBy(sessionObject.getUserId());
				//if(courseList.size()!=0){
					String status = backOfficeService.setVenueTeacherMappingInactive(venue);
					if(status.equalsIgnoreCase("false")){
						model.addAttribute("updateFailStatus", "Course Update Failed");
						mav="administrator/inactiveCourse";
					}else{
						model.addAttribute("updateSuccessStatus", "Course Updated Successfully");
						return inactiveCourse( request,  response,  model);
					}
				
			
			
		}catch(Exception e){
			logger.error("Exception in setCourseInactive() method in AdministratorController: ", e);
		}
		return new ModelAndView(mav);
	}
	*/
	
	/**
	 * @author anup.roy
	 * this method is for view all roll nos.
	 * 27.0.2017*/
	
	@RequestMapping(value = "/assignRollNumber", method = RequestMethod.GET)
	public String assignRollNumber(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "backoffice/assignRollNumber";
		try {
			logger.info("Inside Method assignRollNumber-GET of BackOfficeController");
			logger.info("Inside Method getAcademicYear-GET of BackOfficeController");
			List<AcademicYear> academicYearList=backOfficeService.getAcademicYearList();
			model.addAttribute("academicYearList", academicYearList);
			List<AdmissionForm>allCourseList = academicsService.getAllCourseList();
			model.addAttribute("allCourseList", allCourseList);
		} catch (Exception ce){
			logger.error("Exception in method assignRollNumber-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/getStudentsForAssignRollNumber", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentsForAssignRollNumber(@RequestParam("course") String course) {
		String student = "";
		try {
			//System.out.println("in BOC...LN8093..\nMonth::"+course);
			student = backOfficeService.getStudentsForAssignRollNumber(course);
			//System.out.println("in getDateOfCreationForInsertedTeacher of BOC...LN8094 \nsm::"+student);
		} catch (NullPointerException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return (new Gson().toJson(student));
	}
	
	/**
	 * modified by saif.ali
	 * for activity log
	 * 28062017*/
	
	@RequestMapping(value = "/generateRollNumberForStudent", method = RequestMethod.GET)
	public String generateRollNumberForStudent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,@RequestParam("courseCode") String course
			,@RequestParam("academicYear") String academicYear,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//String strView = "backoffice/assignRollNumber";
		try {
			logger.info("Inside Method generateRollNumberForStudent-GET of BackOfficeController");
			logger.info("Inside Method generateRollNumberForStudent-GET of BackOfficeController");
			String updatedBy = null;//sessionObject.getUserId();
			String status = backOfficeService.generateRollNumberForStudent(course,academicYear,updatedBy);
			String msg = null;
			if(status.equalsIgnoreCase("success")){
				msg = "Roll Number Generated SuccessFully";
			}else{
				msg = "Failed To Generate Roll Number";
			}
			model.addAttribute("insertStatus", status);
			model.addAttribute("msg", msg);
			if(status.equals("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("STUDENT DETAILS");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor(course+"");
				updateLog.setDescription("A New Roll number is generated for course "+course+" of academic year "+academicYear);
				commonService.insertIntoActivityLog(updateLog);
			}
		} catch (Exception ce){
			logger.error("Exception in method generateRollNumberForStudent-GET of BackOfficeController", ce);
		}
		return assignRollNumber(request,response,model);
	}
	
	
	/************Changes By Naimisha For Program Policy 21042017************/
	
	/**
	 * for library policy*/
	
	@RequestMapping(value = "/programPolicy", method = RequestMethod.GET)
	public ModelAndView programPolicy(ModelMap model) {
		String strView = null;
		try {
			logger.info("In BackOfficeController programPolicy() method: calling programPolicy() in BackOfficeService.java");
			//List<ResourceType> resourceTypeList = commonService.getAllResourceType();
			String duration =  backOfficeService.getProgramPolicy();
			model.addAttribute("duration", duration);
			//if(resourceTypeList != null){
			//	model.addAttribute("resourceTypeList", resourceTypeList);
				strView = "backoffice/programPolicy";
			//}
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController programPolicy() method for NullPointerException: ",e);
		} catch (Exception e) {
			logger.error("Error in BackOfficeController programPolicy() method for Exception: ",e);
		}
		return new ModelAndView(strView);
	}
	
	
	@RequestMapping(value = "/addProgramPolicy", method = RequestMethod.POST)
	public ModelAndView addProgramPolicy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("duration") String duration,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		try {
			System.out.println("duration======"+duration);
			logger.info("In BackOfficeController addProgramPolicy() method: calling addProgramPolicy() in BackOfficeService.java");
			Course course = new Course();
			course.setUpdatedBy(sessionObject.getUserId());
			course.setDesc(duration);
			String status = backOfficeService.addProgramPolicy(course);
			//model.addAttribute("resourceTypeList", resourceTypeList);
			String msg = null;
			if(status.equalsIgnoreCase("success")){
				msg = "Program policy Created SuccessFully";
			}else{
				msg = "Failed To Create Program Policy" ;
			}
			model.addAttribute("status", status);
			model.addAttribute("msg", msg);
		//	strView = "backoffice/libraryPolicy";
			
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController addProgramPolicy() method for NullPointerException: ",e);
		} catch (NumberFormatException e) {
			logger.error("Error in BackOfficeController addProgramPolicy() method for NumberFormatException: ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in BackOfficeController addProgramPolicy() method for Exception: ",e);
		}
		return  programPolicy(model);
	}
	
	/**
	 * modified by sourav.bhadra
	 * method for creating fees template wrt program and term
	 * changes made on 27042017*/
	
	@RequestMapping(value = "/assignAmountToProgramAndTermWiseFeesTemplate", method = RequestMethod.GET)
	public String assignAmountToProgramAndTermWiseFeesTemplate(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In assignAmountToProgramAndTermWiseFeesTemplate() method of BackOfficeController");
		String strView = "backoffice/assignAmountToProgramAndTermWiseFeesTemplate";
		try {
			logger.debug("assignAmountToProgramAndTermWiseFeesTemplate()In BackOfficeController.java: calling getAcademicYear() in BackOfficeService.java");
			List<StudentFeesTemplate> allFeesTemplates = backOfficeService.getAllFeesTemplates();
			if (null!= allFeesTemplates && allFeesTemplates.size()!=0) {
				model.addAttribute("allFeesTemplates", allFeesTemplates);
			}
			/*List<Course> allUnmappedCourses = backOfficeService.getAllUnmappedCourses();
			if (null!= allUnmappedCourses && allUnmappedCourses.size()!=0) {
				model.addAttribute("allUnmappedCourses", allUnmappedCourses);
			}*/
			List<Course> programList = academicsService.getCourseList();
			model.addAttribute("programList", programList);
			
			List<SocialCategory> socialCategoryList = backOfficeService.getAllSocialCategories();
			if(null!=socialCategoryList && socialCategoryList.size()!=0){
				model.addAttribute("socialCategoryList",socialCategoryList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strView;
	}
	
	@RequestMapping(value = "/getTermNameForFeesTemplate", method = RequestMethod.GET)
	public @ResponseBody
	String getTermNameForCourse(@RequestParam("programName") String program) {
		logger.info("Inside Method getTermNameForFeesTemplate-GET of backofficeController");
		String termName = "";
		try {
			System.out.println("Program Name:"+program);
			termName = backOfficeService.getTermsForfeesTemplate(program);
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in method getTermNameForFeesTemplate-GET of backofficeController", ce);
		}		
		return (new Gson().toJson(termName));
	}
	
	@RequestMapping(value="/submitAmountForProgramAndTermWiseFeesTemplate", method = RequestMethod.POST)
	public String submitAmountForProgramAndTermWiseFeesTemplate(HttpServletRequest request, HttpServletResponse response, 
													ModelMap model,
													@RequestParam("templateName") String templateName,
													@RequestParam("programName") String courseName,
													@RequestParam("term") String termName,
													@ModelAttribute("sessionObject") SessionObject sessionObject){		
		try{
			List<StudentFeesTemplate> studentFeeTempList = null;
			List<SocialCategory> socialCatList = null;
			String feeHeads[] = request.getParameterValues("feesHead");
			StudentFeesTemplate studentFeeTemplate = new StudentFeesTemplate();
			studentFeeTemplate.setStudentFeesTemplateName(templateName);
			Course cr = new Course();
			cr.setCourseName(courseName);
			studentFeeTemplate.setCourse(cr);
			Term term = new Term();
			term.setTermName(termName);
			studentFeeTemplate.setTerm(term);
			List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();			
			if (socialCategoryList != null) {
				model.addAttribute("socialCategoryList", socialCategoryList);
			}
			if(null != feeHeads && feeHeads.length != 0){
				studentFeeTempList = new ArrayList<StudentFeesTemplate>();
				for(int i = 0; i<feeHeads.length; i++){
					socialCatList = new ArrayList<SocialCategory>();
					StudentFeesTemplate stuFeeTemp = new StudentFeesTemplate();
					stuFeeTemp.setStudentFeesTemplateCode(studentFeeTemplate.getStudentFeesTemplateName());
					stuFeeTemp.setCourse(studentFeeTemplate.getCourse());
					stuFeeTemp.setTerm(studentFeeTemplate.getTerm());
					FeesCategory fCat = new FeesCategory();
					fCat.setFeesCategoryCode(feeHeads[i]);
					for(SocialCategory scat : socialCategoryList){
						String feeHeadAndCategory = request.getParameter(feeHeads[i]+"##"+scat.getSocialCategoryName());
						//System.out.println(feeHeads[i]+" -- "+scat.getSocialCategoryName()+" :: "+feeHeadAndCategory);
						
						SocialCategory socialCategory = new SocialCategory();
						socialCategory.setSocialCategoryName(scat.getSocialCategoryName());
						socialCategory.setAmount(Double.parseDouble(feeHeadAndCategory));
						socialCatList.add(socialCategory);
					}
					fCat.setSocialCategoryList(socialCatList);
					stuFeeTemp.setFeesCategory(fCat);
					stuFeeTemp.setUpdatedBy(sessionObject.getUserId());
					studentFeeTempList.add(stuFeeTemp);
				}				
			}
			String submitResponse = backOfficeService.submitAmountForStudentFeesTemplate(studentFeeTempList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return getStudentFeesTemplateWithAmountList(request,response,model);
	}
	
	/**
	 * @author ranita.sur
	 * changes 17062017
	 * */
	
	@RequestMapping(value = "/getBankDetails", method = RequestMethod.GET)
	public String getBankDetails(HttpServletRequest request,
			HttpServletResponse response,ModelMap model) {
		try {
			logger.info("In addVendor method of CommonController.java");
			List<Vendor> bankDetailList = backOfficeService.getBankDetails();
			if (bankDetailList != null) {
				model.addAttribute("bankDetailList", bankDetailList);
				
				logger.info("The vendor list is ready to display.");
				
				
			}
		} catch (Exception e) {
			logger.error("addVendor() In CommonController.java: Exception",e);
		}
		return "backoffice/addBankDetails";
	}
	
	/**
	 * modified by ranita.sur
	 * changes taken on 28062017*/
	
	@RequestMapping(value = "/submitAddBank", method = RequestMethod.POST)
	public String submitAddVendor(	HttpServletRequest request,
									HttpServletResponse response,ModelMap model, Vendor vendor,Ledger ledger,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In submitVendor method of CommonController.java");
			vendor.setUpdatedBy(sessionObject.getUserId());
			ledger.setUpdatedBy(sessionObject.getUserId());
			//System.out.println(vendor.getBankName()+" " +vendor.getBankIfscCode()+" "+vendor.getAccountNo()+""+vendor.getBranchName()+" "+vendor.getBankLocation());
			int insertStatus = backOfficeService.addBank(vendor,ledger);
			if(insertStatus !=0){
				model.addAttribute("successStatus", "Successfully updated");
			}else{
				model.addAttribute("failStatus", "Update failed");
			}
		} catch (Exception e) {	
			e.printStackTrace();
			logger.error("submitAddVendor() In CommonController.java: Exception", e);
		}
		return getBankDetails(request,response,model);
	}
	
	/**
	 * modified by ranita.sur
	 * changes taken on 21062017*/
	
	@RequestMapping(value = "/editBankDetails", method = RequestMethod.POST)
	public String editVendorDetails(HttpServletRequest request,
							  HttpServletResponse response,
							  ModelMap model,
							  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			
			Vendor vendor = new Vendor();
			
			String saveId=request.getParameter("saveId").trim();
			String bankname = request.getParameter("getBankName").trim();
			String branchname=request.getParameter("getBranchName").trim();
			String accno =request.getParameter("getAccountNo").trim();
			String ifsccode= request.getParameter("getBankIfscCode").trim();
			String branchcode= request.getParameter("getBranchCode").trim();
			String location = request.getParameter("getBankLocation").trim();
			
			vendor.setBankCode(request.getParameter("oldBankCode"+saveId));
			vendor.setUpdatedBy(sessionObject.getUserId());
			
			vendor.setBankName(bankname);
			vendor.setBranchName(branchname);
			vendor.setAccountNo(Long.parseLong((accno)));
			vendor.setBankIfscCode(ifsccode);
			vendor.setBankBranchCode(Long.parseLong((branchcode)));
			vendor.setBankLocation(location);
			status=backOfficeService.editBankDetails(vendor);
			model.addAttribute("insertUpdateStatus", status);
			/**Added by @author Saif.Ali
			 * Date-19-03-2018*/
			if(status.equalsIgnoreCase("Bank update successful")){
				String oldBankName = request.getParameter("bankName"+saveId).trim();
				String oldBranchName = request.getParameter("branchName"+saveId).trim();
				String oldAccountNumber = request.getParameter("accountNo"+saveId).trim();
				String oldIFSCCode = request.getParameter("bankIfscCode"+saveId).trim();
				String oldBranchCode = request.getParameter("bankBranchCode"+saveId).trim();
				String oldBankAddress = request.getParameter("bankLocation"+saveId).trim();
				
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT SCHOOL BANK DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("OFFICE ADMINISTRATION");
				updateLog.setUpdatedFor("Bank ::" + request.getParameter("bankName"+saveId) + " account number ::" + request.getParameter("accountNo"+saveId));
				String description= "";
				if(!(oldBankName.equalsIgnoreCase(bankname)))
				{
					description = description + " Bank Name :: " + oldBankName + " updated to new Bank Name :: " + bankname + " ; ";
				}
				if(!(oldBranchName.equalsIgnoreCase(branchname)))
				{
					description = description + " Branch Name :: " + oldBranchName + " updated to new Branch Name ::" + branchname + " ; ";
				}
				if(!(oldAccountNumber.equalsIgnoreCase(accno)))
				{
					description = description + " Account Number :: " + oldAccountNumber + " updated to new Account Number ::" + accno + " ; ";
				}
				if(!(oldIFSCCode.equalsIgnoreCase(ifsccode)))
				{
					description = description +" IFSC Code :: " + oldIFSCCode + " updated to new IFSC Code ::" + ifsccode + " ; ";
				}
				if(!(oldBranchCode.equalsIgnoreCase(branchcode)))
				{
					description = description +" Branch Code :: " + oldBranchCode + " updated to new Branch Code ::" + branchcode + " ; ";
				}
				if(!(oldBankAddress.equalsIgnoreCase(location)))
				{
					description = description +" Branch Address :: " + oldBankAddress + " updated to new Branch Address ::" + location + " ; ";
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
			logger.error("Exception In editBankDetails() method of BackOfficeController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return getBankDetails(request,response,model);
	}
	
	@RequestMapping(value = "/inactiveBankDetails", method = RequestMethod.GET)
	public String inactiveBankDetails(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam("oldBankCode") String bankCode, @ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		try {
			System.out.println("In controller"+bankCode);
			logger.info("Inside Method inactiveBankDetails-GET of BackOfficeController");
			Vendor vendor=new Vendor();
			vendor.setBankCode(bankCode);
			vendor.setUpdatedBy(sessionObject.getUserId());
			
			String status = backOfficeService.inactiveBankDetails(vendor);
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
			logger.error("Exception in method inactiveBank-GET of BackOfficeController", ce);
		}
		return getBankDetails(request,response,model);
	}
	
	/**
	 * @author anup.roy
	 * this method is for view page of profile of specific resource
	 * changes 10072017
	 * */
	
	@RequestMapping(value = "/viewOwnProfile", method = RequestMethod.GET)
	public ModelAndView viewStudentFullProfile(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, 
			@RequestParam("userId") String userId,
			@RequestParam("role")String role) {
		try {
			logger.info("In viewOwnProfile() GET method of BackofficeController::"+role);
			System.out.println("UserId:"+userId+"\tRole:"+role);
			model.addAttribute("currentRole", role);
			model.addAttribute("userId", userId);
		} catch (Exception ce){
			logger.error("Exception in method viewOwnProfile()GET of BackOfficeController", ce);
			ce.printStackTrace();
		}
		return new ModelAndView("backoffice/viewResourceFullProfile");
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting the modules to be displayed for specific role
	 * changes 10072017
	 * **/
	
	@RequestMapping(value = "/getModulesForRole", method = RequestMethod.GET)
	public @ResponseBody
	String getModulesForRole(@RequestParam("currentRole")String role) {
		logger.info("Inside Method getModulesForRole-GET of BackofficeController");
		String module = "";
		try {
			List<Module> moduleListForTab = backOfficeService.getModuleListFortab(role);
			if(null!= moduleListForTab && moduleListForTab.size()!= 0){
				for(Module m : moduleListForTab){
					module = module + m.getModuleName()+"#";
				}
			}
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in getModulesForRole-GET of BackofficeController", ce);
		}
		return (new Gson().toJson(module));
	}
	
	/***
	 * @author anup.roy
	 * this method is for getting the personal details of resource 
	 * changes 10072017
	 * */
	
	@RequestMapping(value = "/getPersonalDetailsForOwnProfile", method = RequestMethod.GET)
	public @ResponseBody
	String getPersonalDetailsForOwnProfile(@RequestParam("currentUserId")String userId) {
		logger.info("Inside Method getPersonalDetailsForOwnProfile-GET of BackofficeController");
		String personalDetails = "";
		try {
			ResourceProfile resourceProfile = backOfficeService.getPersonalDetailsForResourceProfile(userId);
			StringBuilder sb = new StringBuilder();
			if(null!= resourceProfile){
				sb.append(resourceProfile.getResourceName()+"#");
				sb.append(resourceProfile.getResourceFatherName()+"#");
				sb.append(resourceProfile.getResourceMotherName()+"#");
				sb.append(resourceProfile.getDOB()+"#");
				sb.append(resourceProfile.getEmail()+"#");
				sb.append(resourceProfile.getGender()+"#");
				sb.append(resourceProfile.getMobile()+"#");
				sb.append(resourceProfile.getAadhaarNo()+"#");
				sb.append(resourceProfile.getSocialCategory()+"#");
				sb.append(resourceProfile.getHostel()+"#");
				sb.append(resourceProfile.getAddress()+"#");
				sb.append(resourceProfile.getPostOffice()+"#");
				sb.append(resourceProfile.getPostalCode()+"#");
				sb.append(resourceProfile.getPoliceStation()+"#");
				sb.append(resourceProfile.getRailWayStation()+"#");
				sb.append(resourceProfile.getCity()+"#");
				sb.append(resourceProfile.getDistrict()+"#");
				sb.append(resourceProfile.getState()+"#");
				sb.append(resourceProfile.getCountry()+"#");				
				personalDetails = sb.substring(0, sb.toString().length() - 1);
			}
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in getPersonalDetailsForOwnProfile-GET of BackofficeController", ce);
		}
		System.out.println(personalDetails);
		return (new Gson().toJson(personalDetails));
	}
	
	/**
	 * @author saif.ali
	 * 13072017
	 * this method is for view the create event jsp*/
	
	@RequestMapping(value = "/majorEvents", method = RequestMethod.GET)
	public String majorEvents(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "backoffice/createMajorEvents";
	}
	
	/**
	 * @author saif.ali
	 * this method is f+or add new events*/
	
	@RequestMapping(value = "/createMajorEvent", method = RequestMethod.POST)
	public String createMajorEvent(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			MajorEvents majorEvent,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try{
			majorEvent.setUpdatedBy(sessionObject.getUserId());
			String insertStatus = backOfficeService.createMajorEvents(majorEvent);
			model.addAttribute("insertStatus", insertStatus);		
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in createMajorEvent() method in BackOfficeController: ", e);
		}
		return majorEvents(request, response, model);
	}

	/**@author Saif.Ali
	 * Date- 13/07/2017
	 * Used to create the Leave Policy for different staffs*/
	@RequestMapping(value = "/leavePolicy", method = RequestMethod.GET)
	public String leavepolicy(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try{
			List<ResourceType> resourceTypeList = backOfficeService.getResourceType();//old used
			if(resourceTypeList != null){
				model.addAttribute("resourceTypeList", resourceTypeList);
			}
			logger.error("Exception in leavepolicy() method in BackOfficeController: ");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in leavepolicy() method in BackOfficeController: ", e);
		}
		return "backoffice/createLeavePolicy";
	}
	@RequestMapping(value = "/saveLeavePolicy", method = RequestMethod.POST)
	public String submitLeavePolicy(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			LeavePolicy leavePolicy,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try{	
				leavePolicy.setUpdatedBy(sessionObject.getUserId());
				String insertStatus = backOfficeService.submitLeavePolicy(leavePolicy);
				model.addAttribute("insertStatus", insertStatus);
				/**Added by @author Saif.Ali
				 * Date- 20/03/2018
				 * Used to insert the information into the activity_log table*/
				String oldDailyEncashmentAmount = request.getParameter("oldDailyEncashment");	// old daily encashment amount
				String oldNoOfYearsCarryForward = request.getParameter("oldNoOfYearsCarryForward");	// old no Of Years Carry Forward
				String newDailyEncashmentAmount = request.getParameter("dailyEncashment");	// new daily encashment amount
				String newNoOfYearsCarryForward = request.getParameter("noOfYearsCarryForward");	// new no Of Years Carry Forward
				
				if(insertStatus.equalsIgnoreCase("success")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("EDIT LEAVE POLICY");
					updateLog.setInsertUpdate("UPDATE");
					updateLog.setModule("OFFICE ADMINISTRATION");
					updateLog.setUpdatedFor((request.getParameter("resourceTypeSelect")));
					String description = "";
					if(!(oldDailyEncashmentAmount.equalsIgnoreCase(newDailyEncashmentAmount)))
					{
						description=description + ("Daily Encashment :: " + oldDailyEncashmentAmount + " updated to new Daily Encashment :: " + newDailyEncashmentAmount + " ; ");
					}
					if(!(oldNoOfYearsCarryForward.equalsIgnoreCase(newNoOfYearsCarryForward)))
					{
						description=description +  ("Carry Forward Year ::" + oldNoOfYearsCarryForward + " updated to new Carry Forward Year::" + newNoOfYearsCarryForward+ " ; ");
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
		}catch(Exception e){
			logger.error("Exception in submitLeavePolicy() method in BackOfficeController: ", e);
		}
		return leavepolicy(request, response, model);
	}
	
	/**@author Saif.Ali
	 * Date-18/7/2017
	 * Used to show the saved data according to resource type*/
	@RequestMapping(value = "/getLeavePolicyToShow", method = RequestMethod.GET)
	public  @ResponseBody
	String getLeavePolicyToShow(HttpServletRequest request, HttpServletResponse response, ModelMap model,
		@RequestParam("resourceTypeSelect") String resourceTypeSelect,@ModelAttribute("sessionObject") SessionObject sessionObject) 
	{
		LeavePolicy leave = new LeavePolicy();
		String details= "";
		try{
			leave= backOfficeService.getLeavePolicyToShow(resourceTypeSelect);
			if(leave != null){
				details += leave.getDailyEncashment()+"#"+leave.getNoOfYearsCarryForward();
			}
		} 
		catch(Exception e){
			logger.error("Exception in getLeavePolicyToShow() method in BackOfficeController: ", e);
			e.printStackTrace();
		}
	 return (new Gson().toJson(details));
	}
	
	/**
	 * @author anup.roy
	 * this method takes student's roll no as input 
	 * returns the profile in another page
	 * 27.07.2017*/
	
	@RequestMapping(value="/getStudentRollNoForProfile", method = RequestMethod.GET)
	public String getStudentRollNoForProfile (HttpServletRequest request , HttpServletResponse response, ModelMap model){
		logger.info("In getStudentRollNoForProfile() of backofficeController.java");
		return "backoffice/getStudentRollNoForProfile";
	}
	
	/**
	 * @author anup.roy
	 * this method is to view profile against school number
	 * 28.07.2017*/
	@RequestMapping(value = "/getStudentProfileAgainstSchoolNumber", method = RequestMethod.POST)
	public String getStudentProfileAgainstSchoolNumber(HttpServletRequest request, HttpServletResponse response,
														ModelMap model, @RequestParam("rollNumber") String schoolNumber) {
		try {
			logger.info("In getStudentProfileAgainstSchoolNumber() in BackofficeController.java:");
			Student student = backOfficeService.getStudentProfileAgainstSchoolNumber(schoolNumber);
			if(null != student.getResource().getAttachmentList() && student.getResource().getAttachmentList().size() != 0){
				for(Attachment a : student.getResource().getAttachmentList()){
					if(a.getAttachmentType().equalsIgnoreCase("profile_image")){
						String profileImage = a.getStorageRootPath()+a.getAttachmentName();
						fileUploadDownload.getBase64Image(profileImage);
						Image image = new Image();
						image.setImageName(fileUploadDownload.getBase64Image(profileImage));
						student.getResource().setImage(image);
					}
				}
			}
			model.addAttribute("student", student);
		} catch (Exception e) {
			logger.error("In getStudentProfileAgainstSchoolNumber() In BackofficeController.java"+ e);
			e.printStackTrace();
		}
		return "backoffice/studentProfileAgainstSchoolNumber";
	}
	
	/**
	 * @author anup.roy
	 * this method is for submitting the session fees*/
	
	@RequestMapping(value = "/updateStudentFees", method = RequestMethod.POST)
	public ModelAndView updateStudentFees(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("feeName") String[] feeName, SessionFees sessionFees,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			List<SessionFees> sessionFessList = new ArrayList<SessionFees>();
			List<SessionFees> sessionFessListNew = new ArrayList<SessionFees>();	// added by saif
			for (int i = 0; i < feeName.length; i++) {
				SessionFees sessionfee = new SessionFees();
				sessionfee.setAcademicSsession(sessionFees.getAcademicSsession());
				sessionfee.setStandardName(sessionFees.getStandardName());
				sessionfee.setSectionName(sessionFees.getSectionName());
				sessionfee.setRollNumber(sessionFees.getRollNumber());
				sessionfee.setNetTotAmount(sessionFees.getNetTotAmount());

				sessionfee.setFeesName(feeName[i]);
				sessionfee.setPayingAmount(Double.parseDouble(request.getParameter("pay"+ feeName[i])));
				sessionfee.setComment(request.getParameter("comment" + feeName[i]));
				sessionfee.setUpdatedBy(sessionObject.getUserId());
				sessionfee.setSessionFeesCode(request.getParameter("feeCategory" + feeName[i]));
				sessionFessList.add(sessionfee);
				
				//PRAD MAY 31 2018
				
				//added by Saif 22/02/2018
				String durationNew= request.getParameter("duration"+sessionfee.getFeesName());
				String valueToPayNew= request.getParameter("valueToPay"+sessionfee.getFeesName());
				String paidAmountNew= request.getParameter("paidAmount"+sessionfee.getFeesName());
				String payNew= request.getParameter("pay"+sessionfee.getFeesName());
				String commentNew= request.getParameter("comment"+sessionfee.getFeesName());
				
				SessionFees sessionfeeNew = new SessionFees();
				sessionfeeNew.setAcademicSsession(sessionFees.getAcademicSsession());
				sessionfeeNew.setStandardName(sessionFees.getStandardName());
				sessionfeeNew.setSectionName(sessionFees.getSectionName());
				sessionfeeNew.setRollNumber(sessionFees.getRollNumber());

				sessionfeeNew.setFeesName(request.getParameter("feeCategory" + feeName[i])); //changed By Naimisha 08032018
				sessionfeeNew.setSessionFeesDesc(durationNew);
				sessionfeeNew.setNetTotAmount(Double.parseDouble(valueToPayNew));
				sessionfeeNew.setPaymentMode(paidAmountNew);
				sessionfeeNew.setPayingAmount(Double.parseDouble(payNew));
				sessionfeeNew.setComment(commentNew);
				sessionFessListNew.add(sessionfeeNew);
				
				//PRAD ENDS
			}
			
			String flag = backOfficeService.updateStudentFees(sessionFessList);
			
			
			//PRAD MAY 31 2018
			if(flag.equalsIgnoreCase("true"))
			{
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("username",portalUserName);
				jsonObj.put("password",portalPassWord);
				jsonObj.put("academicsSession",sessionFessList.get(0).getAcademicSsession());
				jsonObj.put("standardName",sessionFessList.get(0).getStandardName());
				jsonObj.put("sectionName",sessionFessList.get(0).getSectionName());
				jsonObj.put("rollNumber",sessionFessList.get(0).getRollNumber());
				JSONArray sessionFeeArray = new JSONArray();
				for(SessionFees sessionfees : sessionFessListNew){
					
						JSONObject jsonObjnew = new JSONObject();
						jsonObjnew.put("feesCategoryName",sessionfees.getFeesName());
						jsonObjnew.put("duration", sessionfees.getSessionFeesDesc());
						jsonObjnew.put("totalPayableAmount",sessionfees.getNetTotAmount());
						jsonObjnew.put("paidAmount",(Double.parseDouble(sessionfees.getPaymentMode()))+sessionfees.getPayingAmount());//Added by naimisha 03082018
						jsonObjnew.put("payingAmount",sessionfees.getPayingAmount());
						jsonObjnew.put("comment",sessionfees.getComment());
					
						//Added by naimisha 03082018
						double due = sessionfees.getNetTotAmount() - (Double.parseDouble(sessionfees.getPaymentMode())+sessionfees.getPayingAmount());
						jsonObjnew.put("due",due);
						//Added by naimisha 03082018
						sessionFeeArray.put(jsonObjnew);
				}
				
				jsonObj.put("fees",sessionFeeArray);
				System.out.println("LN 9776 :: Json Object Contents"+jsonObj.toString());
				final String uri = URIForSendingFeesPaymentDetails;
				System.out.println("URI:::"+uri);
				URL url = new URL(uri);
				HttpURLConnection connection = null;
				OutputStreamWriter out = null;
				InputStreamReader in = null;
				BufferedReader br = null;
				String json_response = "";
				WebIQTransaction webIQTran= null;
				
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
				}catch (Exception e){
					e.printStackTrace();
					//Could be Connection Failure then also insert into the webiq_transaction_details table
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(URIForSendingFeesPaymentDetails);
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
						//If call to the API is successful, then insert into the webiq_transaction_details table 
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForSendingFeesPaymentDetails);
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(true);
					}else{
						//If Failure then also insert into the webiq_transaction_details table
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForSendingFeesPaymentDetails);
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(false);
					}
					
					//Call to the BackOffice Service
					backOfficeService.addWebIQTransaction(webIQTran);
				}
			}
			//PRAD ENDS
			
		} catch (NullPointerException e) {
			logger.error("Error in BackOfficeController createAcademicYear() method for NullPointerException: ",e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Error in BackOfficeController createAcademicYear() method for Exception: ",e);
			e.printStackTrace();
		}
		return receiveSessionFee(request, response, model);
	}
	
	//missing link integration 17042018
		@RequestMapping(value = "/mapCategoryWithSLA", method = RequestMethod.GET)
		public String mapCategoryWithSLA(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model,
								  @ModelAttribute("sessionObject") SessionObject sessionObject) {
			
			try {
					List<JobType> categoryList = administratorService.getCategoryList();
					model.addAttribute("categoryList", categoryList);
					
					List<Ticket>allCategorySLAList = backOfficeService.getAllCategorySLAList();
					model.addAttribute("allCategorySLAList", allCategorySLAList);
				
				
			} catch (Exception e) {
				logger.error("Exception In mapCategoryWithSLA() method of BackOfficeController.java: Exception",e);
				e.printStackTrace();
			}
			return "backoffice/mapCategoryWithSLA";
		}
		//missing link integration 17042018
		@RequestMapping(value = "/mapCategoryWithSLA", method = RequestMethod.POST)
		public String submitCategoryWithSLA(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model,
								  @ModelAttribute("sessionObject") SessionObject sessionObject,
								  @RequestParam(value="category") String category,
								  @RequestParam(value="acknowledgementTime") String acknowledgementTime,
								  @RequestParam(value="completionTime") String completionTime) {
			
			try {
					int finalAcknowledgeTimeInMin = 0;
					int finalcompletionTimeInMin = 0;
					
					
					
					if(acknowledgementTime.contains("-")){
						String[] acknowledgementTimeArr = acknowledgementTime.split("-");
						finalAcknowledgeTimeInMin = finalAcknowledgeTimeInMin + Integer.parseInt(acknowledgementTimeArr[0])*60
													+ Integer.parseInt(acknowledgementTimeArr[1]);
						
					}
					
					if(completionTime.contains("-")){
						String[] completionTimeArr = completionTime.split("-");
						finalcompletionTimeInMin = finalcompletionTimeInMin + Integer.parseInt(completionTimeArr[0])*60
													+ Integer.parseInt(completionTimeArr[1]);
						
					}
					
					Ticket ticketObj = new Ticket();
					ticketObj.setUpdatedBy(sessionObject.getUserId());
					ticketObj.setTicketCode(category);
					ticketObj.setTicketMinDays(finalAcknowledgeTimeInMin);
					ticketObj.setTicketMaxDays(finalcompletionTimeInMin);
					
					String insertStatus = backOfficeService.submitCategoryWithSLA(ticketObj);
					model.addAttribute("insertStatus", insertStatus);	
					
					
				
				
			} catch (Exception e) {
				logger.error("Exception In submitCategoryWithSLA() method of BackOfficeController.java: Exception",e);
				e.printStackTrace();
			}
			return mapCategoryWithSLA( request,response,model,sessionObject);
		}
		
		/**
		 * @author anup.roy
		 * this method is for mapping SLA category with Template
		 * */
		//missing link integration 17042018
		@RequestMapping(value = "/mapCategoryTemplateUserSLA", method = RequestMethod.GET)
		public String mapCategoryTemplateUserSLA(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model) {
			try {
				logger.info("In mapCategoryTemplateUserSLA of BackofficeController.java");
				List<JobType> categoryList = administratorService.getCategoryList();
				model.addAttribute("categoryList", categoryList);
				List<EmailEventAndTemplate> templateList= backOfficeService.getListOfTemplateForCategoryTemplateUserSLA();
				model.addAttribute("templateList", templateList);
				List<Resource> resourceList = backOfficeService.getListOfResourceForCategoryTemplateUserSLA();
				model.addAttribute("resourceList", resourceList);
				List<CategoryAndTemplate> mappedCategoryTemplateUserList = backOfficeService.getMappedCategoryTemplateAndUserList();
				model.addAttribute("mappedCategoryTemplateUserList", mappedCategoryTemplateUserList);
				List<TicketStatus> ticketStatusList = commonService.getAllTicketStatus();
				model.addAttribute("ticketStatusList", ticketStatusList);
				
			} catch (Exception e) {
				logger.error("Exception In mapCategoryTemplateUserSLA() method of BackOfficeController.java:",e);
				e.printStackTrace();
			}
			return "backoffice/mapCategoryTemplateUserSLA";
		}
		
		/**
		 * @author anup.roy
		 * this method submits the mapped data of category-template-user*/
		//missing link integration 17042018
		@RequestMapping(value="/submitMapCategoryTemplateUser", method = RequestMethod.POST)
		public String submitMapCategoryTemplateUser(HttpServletRequest request, HttpServletResponse response,
				ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObj, CategoryAndTemplate category){
			try{
				logger.info("In submitMapCategoryTemplateUser() of BackofficeController.java");
				category.setUpdatedBy(sessionObj.getUserId());
				String insertStatus = backOfficeService.submitMapCategoryTemplateUser(category);
				model.addAttribute("insertStatus", insertStatus);
			}catch (Exception e) {
				logger.error("Exception in submitMapCategoryTemplateUser() of BackofficeController.java:",e);
				e.printStackTrace();
			}
			return mapCategoryTemplateUserSLA(request, response, model);
		}
		
		
		/**
		 * @author naimisha.ghosh 30012018
		 * this method is for map Task With EventTemplate
		 * */
		//missing link integration 17042018
		@RequestMapping(value = "/mapTaskWithEventTemplate", method = RequestMethod.GET)
		public String mapTaskWithEventTemplate(HttpServletRequest request,
								  HttpServletResponse response, ModelMap model) {
			try {
				logger.info("In mapTaskWithEventTemplate of BackofficeController.java");
				
				List<JobType>taskList = administratorService.getAllJobDetails();
				model.addAttribute("taskList", taskList);
				
				List<EmailEventAndTemplate> templateList= backOfficeService.getListOfTemplateForCategoryTemplateUserSLA();
				model.addAttribute("templateList", templateList);
			//	List<Resource> resourceList = backOfficeService.getListOfResourceForCategoryTemplateUserSLA();
				//model.addAttribute("resourceList", resourceList);
				List<CategoryAndTemplate> mappedTaskTemplateList = backOfficeService.getMappedTaskTemplateList();
				model.addAttribute("mappedTaskTemplateList", mappedTaskTemplateList);
			} catch (Exception e) {
				logger.error("Exception In mapTaskWithEventTemplate() method of BackOfficeController.java:",e);
				e.printStackTrace();
			}
			return "backoffice/mapTaskWithEventTemplate";
		}
		//missing link integration 17042018
		@RequestMapping(value = "/submitMapTaskTemplate", method = RequestMethod.POST)
		public String submitMapTaskTemplate(HttpServletRequest request,
								  HttpServletResponse response,@ModelAttribute("sessionObject") SessionObject sessionObj,
								  ModelMap model,CategoryAndTemplate category) {
			try {
				logger.info("In submitMapTaskTemplate of BackofficeController.java");
				category.setUpdatedBy(sessionObj.getUserId());
				
				String insertStatus = backOfficeService.submitMapTaskWithTemplate(category);
				model.addAttribute("insertStatus", insertStatus);
				
			} catch (Exception e) {
				logger.error("Exception In submitMapTaskTemplate() method of BackOfficeController.java:",e);
				e.printStackTrace();
			}
			return mapTaskWithEventTemplate( request, response,  model);
		}
		
		@RequestMapping(value = "/getTemplateForATemplateType", method = RequestMethod.GET)
		public @ResponseBody
		String getTemplateForATemplateType(@RequestParam("templateType") String templateType) {
			String templates = "";
			try {
				logger.info("getTemplateForATemplateType() In BackOfficeController.java");
				List<EmailEventAndTemplate> templateList = backOfficeService.getTemplateForATemplateType(templateType);
				for(EmailEventAndTemplate template : templateList){
					templates = templates + template.getTemplateCode() + "*" + template.getEmailSubject()+"#";
				}
				System.out.println("templates===="+templates);
			}  catch (Exception e) {
				e.printStackTrace();
				logger.error("getTemplateForATemplateType() In CommonController.java: Exception"
						+ e);
			}
			return  (new Gson().toJson(templates));
		}
		
		//Added By Naimisha 04052018
		
		@RequestMapping(value = "/getTaskStatusAgainstTaskType", method = RequestMethod.GET)
		public @ResponseBody
		String getTaskStatusAgainstTaskType(@RequestParam("category") String category) {
			String taskStatus = "";
			try {
				logger.info("getTaskStatusAgainstTaskType() In BackOfficeController.java");
				List<TicketStatus> taskStatusList = backOfficeService.getPossibleTaskStatusListForATask(category);
				for(TicketStatus status : taskStatusList){
					taskStatus = taskStatus + status.getTicketStatusCode() + "*" + status.getTicketStatusName()+"#";
				}
				System.out.println("taskStatus===="+taskStatus);
			}  catch (Exception e) {
				e.printStackTrace();
				logger.error("getTaskStatusAgainstTaskType() In CommonController.java: Exception"
						+ e);
			}
			return  (new Gson().toJson(taskStatus));
		}
		
		/**Added by @author Saif.Ali
		 * Date- 28/02/2018
		 * Used to show the screen to add the complaint for student*/
		@RequestMapping(value = "/addDisciplinaryAction", method = RequestMethod.GET)
		public String studentComplaint(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model) {
			try {
				logger.info("In studentComplaint of BackofficeController.java");
				
				List<Standard> standardList=academicsService.getStandardsForAssignBatch();
				model.addAttribute("standardList", standardList);
				
				List<Student> studentDisciplinaryActionList= backOfficeService.getAllListOfDisciplinaryAction();	
				model.addAttribute("studentDisciplinaryActionList",studentDisciplinaryActionList);
				
				List<DisciplinaryAction>disciplinaryActionList =  backOfficeService.getAllDisciplinaryCodeList();
				model.addAttribute("disciplinaryActionList", disciplinaryActionList);
				
			} catch (Exception e) {
				logger.error("Exception In studentComplaint() method of BackOfficeController.java:",e);
				e.printStackTrace();
			}
			return "backoffice/addDisciplinaryAction";
		}
		
		/**Used to get the students roll number to send the disciplinary action*/
		@RequestMapping(value = "/getStudentRollAgainstStandardAndSection", method = RequestMethod.GET)
		public @ResponseBody
		String getStudentRollAgainstStandardAndSection(@RequestParam("standard") String standard,
											@RequestParam("section") String section) {
			String student = null;
			try {
				logger.info("getStudentsToAssignSection() In CommonController.java");
				student = academicsService.getStudentsToAssignSection(standard, section);
			} catch (NullPointerException e) {
				e.printStackTrace();
				logger.error("getStudentRollAgainstStandardAndSection() In CommonController.java: NullPointerException"+ e);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("getStudentRollAgainstStandardAndSection() In CommonController.java: Exception"+ e);
			}
			return student;
		}
		
		/**Used to insert the disciplinary action into database*/
		@RequestMapping(value = "/insertDisciplinaryAction", method = RequestMethod.POST)
		public String insertDisciplinaryAction(HttpServletRequest request,
								  HttpServletResponse response,@ModelAttribute("sessionObject") SessionObject sessionObj,
								  ModelMap model,Student student,@ModelAttribute("complaint") String complaint) {
			try {
				logger.info("In insertDisciplinaryAction of BackofficeController.java");
				AcademicYear academicYear = commonService.getCurrentAcademicYear();
				String standard= request.getParameter("standard");
				String section= request.getParameter("section");
				String rollNumber= request.getParameter("rollNumber");
				//String complaint= request.getParameter("complaint");
				student.setStandard(standard);
				student.setSection(section);
				student.setRoll(rollNumber);
				student.setChequeNo(complaint);
				student.setUpdatedBy(sessionObj.getUserId());
				System.out.println(student.getDisciplinaryCode());
				String status= backOfficeService.insertDisciplinaryAction(student);
				model.addAttribute("status",status);
				
				if(status.equalsIgnoreCase("success") && isWebIQAvailable.equalsIgnoreCase("true"))
				{
					JSONObject jsonObj = new JSONObject();
					Date date = Calendar.getInstance().getTime();
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					String today = formatter.format(date);
					long epochTime = date.getTime() / 1000L;
					
					jsonObj.put("username",portalUserName);
					jsonObj.put("password",portalPassWord);
					jsonObj.put("rollNumber",student.getRoll());
					jsonObj.put("section",student.getSection());
					jsonObj.put("standard",student.getStandard());
					jsonObj.put("comment",student.getChequeNo());
					//jsonObj.put("mobilenumber",student.getMobileNo());
					jsonObj.put("codeOfConduct",student.getDisciplinaryCode());
					jsonObj.put("codeDescription", student.getDescription());
					jsonObj.put("academicsSession",  academicYear.getAcademicYearName());
					jsonObj.put("dateString", today);
					jsonObj.put("logTime", epochTime);
					System.out.println("LN 9776 :: Json Object Contents"+jsonObj.toString());
					final String uri = URIForSendingDisciplinaryActionDetails;
					System.out.println("URI:::"+uri);
					URL url = new URL(uri);
					HttpURLConnection connection = null;
					OutputStreamWriter out = null;
					String json_response = "";
					InputStreamReader in = null;
					BufferedReader br = null;
					WebIQTransaction webIQTran= null;
					
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
					}catch (Exception e){
						e.printStackTrace();
						//Could be Connection Failure then also insert into the webiq_transaction_details table
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObj.getUserId());
						webIQTran.setUri(URIForSendingDisciplinaryActionDetails);
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
							//If call to the API is successful, then insert into the webiq_transaction_details table 
							webIQTran = new WebIQTransaction();
							webIQTran.setUpdatedBy(sessionObj.getUserId());
							webIQTran.setUri(URIForSendingDisciplinaryActionDetails);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(true);
							
						}else{
							//If Failure then also insert into the webiq_transaction_details table
							webIQTran = new WebIQTransaction();
							webIQTran.setUpdatedBy(sessionObj.getUserId());
							webIQTran.setUri(URIForSendingDisciplinaryActionDetails);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(false);
						}
						
						//Call to the BackOffice Service
						backOfficeService.addWebIQTransaction(webIQTran);
					}

				}
				
				/*If SMS is enabled and insert to DB is successful, then send SMS */
				if(status.equalsIgnoreCase("success") && isSMSEnabled.equalsIgnoreCase("true")){
					String mobileNumber = backOfficeService.getMobileNumberAgainstRollNumber(student.getRoll());
					student.setMobileNo(mobileNumber);
					try{
						Map<String, String> dataMap = utility.sendSMSForDisciplinaryAction(student,smsURL,smsAuthkey);
						System.out.println("SMS Status for Disciplinary Action: "+ dataMap.get("status"));
						
						// Added by SAIKAT 16/6/2018
						if(dataMap.get("status").equalsIgnoreCase("true")){
							
							SmsAudit smsAudit = new SmsAudit();
							smsAudit.setMobileNumber(student.getMobileNo());
							smsAudit.setMessage(dataMap.get("message"));
							smsAudit.setActionFor("Disciplinary Action");
							smsAudit.setStatus(new Boolean (dataMap.get("status")));
							smsAudit.setUpdatedBy(sessionObj.getUserId());
							
							String insertStatus = commonService.saveSMSDetailsForAudit(smsAudit);
							
							logger.info("Insert in SmsAuditLog table for disciplinary of student " + student.getRoll() + " : " + insertStatus);
						}
						
					}catch(Exception smsException){
						System.out.println("There was an SMS Exception");
					}
				}
				
				
			} catch (Exception e) {
				logger.error("Exception In insertDisciplinaryAction() method of BackOfficeController.java:",e);
				e.printStackTrace();
			}
			return studentComplaint(request, response, model);
		}
		
		/* added by sourav.bhadra on 26-02-2018 
		 * for daily basis student attendance */
		@RequestMapping(value = "/studentDailyAttendance", method = RequestMethod.GET)
		public ModelAndView studentDailyAttendance(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
			logger.info("In resourceAttendance() method ");
			try {
				logger.info("showStudentAttendance()In BackOfficeController.java: calling fetchclassDetails() in BackOfficeService.java");
				AcademicYear currentYear = commonService.getCurrentAcademicYear();
				if (currentYear != null) {
					model.addAttribute("currentYear", currentYear);
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
				String showClass = backOfficeService.getClassForAttendance();
				String[] classArray = showClass.split(",");
				List<String> classList = new ArrayList<String>(Arrays.asList(classArray));
				model.addAttribute("classList", classList);
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				String todayDate = formatter.format(date);
				model.addAttribute("todayDate", todayDate);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("backoffice/studentDailyAttendance");
		}
		
		/* added by sourav.bhadra on 27-02-2018 */
		@RequestMapping(value = "/getStudentsForDailyAttendance", method = RequestMethod.GET)
		public @ResponseBody
		String getStudentsForDailyAttendance(@RequestParam("class") String selectedclass,
										@RequestParam("section") String selectedsection,
										@RequestParam("currentDate") String todayDate,
										StudentAttendance studentAttendance) {
			
			String showStudent = "";
			try {
				logger.info("In getStudentsForAttendance() method ");
				Resource resource = new Resource();
				resource.setKlass(selectedclass);
				Section section = new Section();
				section.setSectionName(selectedsection);
				resource.setSection(section);
				showStudent = backOfficeService.getStudentsForAttendance(resource);
				
				/*added & modified by sourav.bhadra on 05-02-2018 */
				String studentLeaveDetails = backOfficeService.getStudentsLeaveDetailsForDailyAttendanceSubmit(todayDate);
				showStudent = showStudent +"#"+ studentLeaveDetails;
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (new Gson().toJson(showStudent));
		}

		/* added by sourav.bhadra on 27-02-2018
		 * to submit students daily basis attendance */
		@RequestMapping(value = "/submitStudentsDailyAttendance", method = RequestMethod.POST)
		public ModelAndView submitStudentsDailyAttendance(HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("In submitStudentsDailyAttendance() method ");
			try {
				logger.info("submitStudentsDailyAttendance()In BackOfficeController.java");
				List<StudentAttendance> studentAttendanceList = new ArrayList<StudentAttendance>();
				List<StudentAttendance> absentStudentAttendanceList = new ArrayList<StudentAttendance>();	//added by Saif
				List<StudentAttendance> leaveStudentAttendanceList = new ArrayList<StudentAttendance>();	//added by Saif
				String todayDate = request.getParameter("currentDate");
				//added by Saif
				System.out.println("today Date::"+todayDate);
				String str = todayDate;
				//Saif addition ends
				String[] dateArr = todayDate.split("/");
				String[] studentRollNumbers = request.getParameterValues("studentRoll");
				for(int i=0; i<studentRollNumbers.length; i++){
					String status = request.getParameter("checkedBox_"+studentRollNumbers[i]);
					System.out.println("LN 10197 Roll Numbers"+studentRollNumbers[i]);
					System.out.println("LN 10198 Status::"+status);
					//added by Saif
					if(null== status || status == "")
					{
						StudentAttendance studentAttendance = new StudentAttendance();
						studentAttendance.setStudentRollNo(studentRollNumbers[i]);
						absentStudentAttendanceList.add(studentAttendance);
					}
					else if(status.equalsIgnoreCase("On Leave"))
					{
						StudentAttendance studentAttendance = new StudentAttendance();
						studentAttendance.setStudentRollNo(studentRollNumbers[i]);
						leaveStudentAttendanceList.add(studentAttendance);
					}
					//Saif addition ends
					else
					{
						StudentAttendance studentAttendance = new StudentAttendance();
						studentAttendance.setAbsentDay(todayDate);
						studentAttendance.setMonth(dateArr[1]);
						studentAttendance.setYear(dateArr[2]);
						studentAttendance.setStudentRollNo(studentRollNumbers[i]);
						studentAttendance.setUpdatedBy(sessionObject.getUserId());
						
						studentAttendanceList.add(studentAttendance);
					}
					
				}
				String status = backOfficeService.submitStudentsDailyAttendance(studentAttendanceList);
				if(null != status && status != ""){
					String insertStatus = "Todays Attendance is Submitted Successfully.";
					model.addAttribute("insertStatusSuccess", insertStatus);
				}else{
					String insertStatus = "Todays Attendance Submition Failed.";
					model.addAttribute("insertStatusFail", insertStatus);
				}
				
				/**Added by Saif Date-06032018
				 * Used to send the JSON for the students attendance*/
				String standardName=request.getParameter("class");
				String sectionName= request.getParameter("sectionone");
				
				if(isWebIQAvailable.equalsIgnoreCase("true")){
					AcademicYear academicYear = commonService.getCurrentAcademicYear();
					Date date = Calendar.getInstance().getTime();
					DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					String today = formatter.format(date);
					
					Date dateToday = new SimpleDateFormat("dd/MM/yyyy").parse(todayDate);
					long epochTime = dateToday.getTime() / 1000L;
					System.out.println("EPOCH TIME:"+epochTime);
					
					
					if(null != status && status != "")
					{				
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("username",portalUserName);
						jsonObj.put("password",portalPassWord);
						jsonObj.put("section",sectionName);
						jsonObj.put("standard",standardName);
						jsonObj.put("attendanceDate",epochTime);
						jsonObj.put("dateString", todayDate);
						jsonObj.put("academicsSession",academicYear.getAcademicYearName());
						JSONArray jsonarrayPresent= new JSONArray();
						JSONArray jsonarrayAbsent= new JSONArray();
						JSONArray jsonarrayOnLeave= new JSONArray();
						for(StudentAttendance stuAttend:studentAttendanceList)
						{
							jsonarrayPresent.put(stuAttend.getStudentRollNo());	//present roll numbers
						}
						jsonObj.put("rollNumberPresent",jsonarrayPresent);
						for(StudentAttendance stuAttend:absentStudentAttendanceList)
						{
							jsonarrayAbsent.put(stuAttend.getStudentRollNo());	//absent roll numbers
						}
						jsonObj.put("rollNumberAbsent",jsonarrayAbsent);
						for(StudentAttendance stuAttend:leaveStudentAttendanceList)
						{
							jsonarrayOnLeave.put(stuAttend.getStudentRollNo());	//leave roll numbers
						}
						jsonObj.put("rollNumberOnLeave",jsonarrayOnLeave);
						System.out.println("LN 10263 :: Json Object Contents"+jsonObj.toString());
						final String uri = URIForSendingAttendanceDetailsOfCadet;
						System.out.println("URI:::"+uri);
						URL url = new URL(uri);
						HttpURLConnection connection = null;
						OutputStreamWriter out = null;
						String json_response = "";
						InputStreamReader in = null;
						BufferedReader br = null;
						WebIQTransaction webIQTran= null;
						
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
						}catch (Exception e){
							e.printStackTrace();
							//Could be Connection Failure then also insert into the webiq_transaction_details table
							webIQTran = new WebIQTransaction();
							webIQTran.setUpdatedBy(sessionObject.getUserId());
							webIQTran.setUri(URIForSendingAttendanceDetailsOfCadet);
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
								//If call to the API is successful, then insert into the webiq_transaction_details table 
								webIQTran = new WebIQTransaction();
								webIQTran.setUpdatedBy(sessionObject.getUserId());
								webIQTran.setUri(URIForSendingAttendanceDetailsOfCadet);
								webIQTran.setRequestJSON(jsonObj.toString());
								webIQTran.setResponseJSON(json_response);
								webIQTran.setStatus(true);
							}else{
								//If Failure then also insert into the webiq_transaction_details table
								webIQTran = new WebIQTransaction();
								webIQTran.setUpdatedBy(sessionObject.getUserId());
								webIQTran.setUri(URIForSendingAttendanceDetailsOfCadet);
								webIQTran.setRequestJSON(jsonObj.toString());
								webIQTran.setResponseJSON(json_response);
								webIQTran.setStatus(false);
							}
							
							//Call to the BackOffice Service
							backOfficeService.addWebIQTransaction(webIQTran);
						}

						
					}
					/**Saif addition ends*/
				}
				
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return studentDailyAttendance(request,response,model);
		}

		
		@RequestMapping(value = "/getStudentsRollNumbersForAlreadyAttendedStudents", method = RequestMethod.GET)
		public @ResponseBody
		String getStudentsRollNumbersForAlreadyAttendedStudents(@RequestParam("class") String selectedclass,
										@RequestParam("section") String selectedsection,
										@RequestParam("currentDate") String todayDate) {
			
			String showStudent = "";
			try {
				logger.info("In getStudentsForAttendance() method ");
				Resource resource = new Resource();
				resource.setKlass(selectedclass);
				Section section = new Section();
				section.setSectionName(selectedsection);
				resource.setSection(section);
				resource.setAcademicYear(todayDate);
				showStudent = backOfficeService.getStudentsRollNumbersForAlreadyAttendedStudents(resource);
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (new Gson().toJson(showStudent));
		}

		
		//Added By Naimisha 29052018
		@RequestMapping(value = "/createDisciplinaryCode", method = RequestMethod.GET)
		public String createDisciplinaryCode(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model) {
			try {
				logger.info("In createDisciplinaryCode() of BackofficeController.java");
				List<DisciplinaryAction> disciplinaryCodeList = backOfficeService.getAllDisciplinaryCodeList();
				model.addAttribute("disciplinaryCodeList", disciplinaryCodeList);
				
			} catch (Exception e) {
				logger.error("Exception In createDisciplinaryCode() method of BackOfficeController.java:",e);
				e.printStackTrace();
			}
			return "backoffice/createDisciplinaryCode";
		}
		
		@RequestMapping(value = "/createDisciplinaryCode", method = RequestMethod.POST)
		public String createDisciplinaryCode(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model,DisciplinaryAction disciplinaryAction,
								  @ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In createDisciplinaryCode() of BackofficeController.java");
				disciplinaryAction.setUpdatedBy(sessionObject.getUserId());
				String status = backOfficeService.insertDisciplinaryCode(disciplinaryAction);
				String msg="";
				if(status.equalsIgnoreCase("success")){
					msg = "Disciplinary Code Created Successfully.";
				}else if(status.equalsIgnoreCase("fail")){
					msg = "Failed to Create Disciplinary Action.";
				}
				
				model.addAttribute("status", status);
				model.addAttribute("msg", msg);
				
			} catch (Exception e) {
				logger.error("Exception In createDisciplinaryCode() method of BackOfficeController.java:",e);
				e.printStackTrace();
			}
			return createDisciplinaryCode(request,response,model);
		}
		
		@RequestMapping(value = "/getDescriptionAgainstDisciplinaryCode", method = RequestMethod.GET)
		public @ResponseBody
		String getDescriptionAgainstDisciplinaryCode(@RequestParam("disciplinaryCode") String disciplinaryCode) {
			DisciplinaryAction disciplinaryAction = null;
			String description="";
			try {
				logger.info("getDescriptionAgainstDisciplinaryCode() In CommonController.java");
				disciplinaryAction = backOfficeService.getDescriptionAgainstDisciplinaryCode(disciplinaryCode);
				description = disciplinaryAction.getDescription();
				System.out.println(description);
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("getDescriptionAgainstDisciplinaryCode() In CommonController.java: Exception"+ e);
			}
			return (new Gson().toJson(description));
		}
		
		private static String encodeImage(byte[] imageByteArray) {
	        return Base64.encodeBase64String(imageByteArray);
	    }
		
}
