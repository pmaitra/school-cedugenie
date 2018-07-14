package com.qts.icam.controller.academics;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dhtmlx.planner.DHXEvent;
import com.google.gson.Gson;
import com.qts.icam.controller.backoffice.BackOfficeController;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.event.EventAchievement;
import com.qts.icam.model.event.SchoolEvent;
import com.qts.icam.model.academics.Algorithm;
import com.qts.icam.model.academics.AssetConsumption;
import com.qts.icam.model.academics.CoScholasticResult;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.academics.CourseSubjectMapping;
import com.qts.icam.model.academics.CourseType;
import com.qts.icam.model.academics.DescriptiveIndicatorSkill;
import com.qts.icam.model.academics.ExamMarks;
import com.qts.icam.model.academics.RoutineTableGridData;
import com.qts.icam.model.academics.StudentCourseSubjectMapping;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.academics.UserDefinedExams;
import com.qts.icam.model.academics.UserExamMarks;
import com.qts.icam.model.administrator.ServerConfiguration;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.backoffice.Term;
import com.qts.icam.model.backoffice.TimeTableGridData;
import com.qts.icam.model.backoffice.WebIQTransaction;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Asset;
import com.qts.icam.model.common.AssetSubType;
import com.qts.icam.model.common.AssetType;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.CategoryAndFees;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.ExamType;
import com.qts.icam.model.common.ProgrammeDetailsForPortal;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.SmsAudit;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.common.TermWiseFees;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.facility.Facility;
import com.qts.icam.model.grade.GradingSystem;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.tender.Tender;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.admission.AdmissionService;
import com.qts.icam.service.backoffice.BackOfficeService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.erp.ERPService;
import com.qts.icam.service.hostel.HostelService;
import com.qts.icam.service.inventory.InventoryService;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;
import com.qts.icam.utility.seat.DiagonalLeftToRightSeating;
import com.qts.icam.utility.seat.LeftToRightSeating;
import com.qts.icam.utility.seat.RightToLeftSeating;
import com.qts.icam.utility.seat.Seating;
import com.qts.icam.utility.seat.SpiralSeating;
import com.qts.icam.utility.seat.ZigzagSeating;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

@Controller
@SessionAttributes("sessionObject")
public class AcademicsController {
	public static Logger logger = Logger.getLogger(AcademicsController.class);
	
	@Autowired
	AcademicsService academicsService;
	
	@Autowired
	BackOfficeService backOfficeService;
	
	@Autowired
	CommonService commonService;

	@Autowired
	HostelService hostelService;
	
	@Autowired
	AdmissionService admissionService ;
	
	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	ERPService erpService;
	
	@Autowired
	private ServletContext servletContext;

	@Autowired
	FileUploadDownload fileUploadDownload;

	@Autowired
	Utility utility;
	
	@Value("${excelDownload.folder}")
	private String excelDownloadFolder;
		
	@Value("${standardSubjectMapping.excel}")
	private String standardSubjectMappingExcel;	

	@Value("${assetDetails.excel}")
	private String assetDetailsExcel;
		
	@Value("${excelUpload.folder}")
	private String excelUploadfolder;
	
	@Value("${root.path}")
	private String rootPath;
	
	@Value("${questionPapers.path}")
	private String uploadQuestionPapersPath;
	
	@Value("${path.marksDownloadFolder}")
	private String marksDownloadFolder;
	
	@Value("${path.marksUploadFolder}")
	private String marksUploadFolder;
	
	@Value("${prospectus.path}")
	private String prospectusPath;
	
	@Value("${assignment.path}")
	private String uploadAssignmenetPath;
	
	@Value("${URI.sendProgrammeDetails}")
	private String URIForSendingProgrammeDetails;
	
	@Value("${URI.eventsEntry}")
	private String URIForEventsEntry;
	
	@Value("${Portal.userName}")
	private String portalUserName;
	
	@Value("${Portal.passWord}")
	private String portalPassWord;
	
	@Autowired
	BackOfficeController backOfficeController;
	
	@Autowired
	AdministratorService loginService;
	
	@Autowired
	InventoryService inventoryService;
	
	int counter = 0;

	@Value("${URI.assignSectionToStudentsDetails}")
	private String URIForAssignSectionToStudentsDetails;

	@Value("${URI.sendExamMarksOfCadet}")
	private String sendExamMarksOfCadet;

	@Value("${webiq.available}")
	private String isWebIQAvailable;
	
	EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
	
	@Value("${studentAchievementImage.path}")
	private String studentAchievementImage;

	@Value("${URI.sendStudentAchievement}")
	private String sendStudentAchievement;
	
	@Value("${sms.enabled}")
	private String isSMSEnabled;

	@Value("${sms-url}")
	private String smsURL;
	
	@Value("${authkey}")
	private String smsAuthkey;

	@RequestMapping(value = "/academicsProcessFlow", method = RequestMethod.GET)
	public String academicsProcessFlow() {
		return "academics/academicsProcessFlow";
	}

	
	/**
	 * @author anup.roy
	 * Opens page to add Standard & Section
	 * 02.08.2017
	 */	
	@RequestMapping(value = "/getStandard", method = RequestMethod.GET)
	public ModelAndView getStandard(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "academics/createStandard";
		try {
			logger.info("Inside Method getStandard-GET of AcademicsController");
			List<Standard> standardList=academicsService.getStandardsWithSection();
			model.addAttribute("standardList", standardList);
			
		} catch (CustomException ce){
			logger.error("Exception in method getStandard-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return new ModelAndView(strView);
	}
	
	/*
	 * modified by sourav.bhadra
	 * changes taken on 27042017
	 */	
	@RequestMapping(value = "/addStandard", method = RequestMethod.POST)
	public ModelAndView addStandard(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Standard standard,
			@RequestParam(value="sections") String sections,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String insertStatus = null;
		String msg = null;
		try {
			logger.info("Inside Method addStandard-POST of AcademicsController");
			if(null!=standard && null!=standard.getStandardName() && standard.getStandardName().trim().length()!=0){
				standard.setStandardName(standard.getStandardName().trim().toUpperCase());
				standard.setStandardCode(standard.getStandardName().trim().toUpperCase());
				standard.setDesc(standard.getStandardName().trim());
				standard.setUpdatedBy(sessionObject.getUserId());				
				List<Section> sectionList=new ArrayList<Section>();
				if(null!=sections && sections.length()!=0 && sections.endsWith(","))
					sections=sections.substring(0, sections.length() - 1);
				if(null!=sections && sections.length()!=0 && sections.startsWith(","))
					sections=sections.substring(1);
				if(null!=sections && sections.length()!=0){
					String sectionArray[]=sections.split(",");
					for(int sectionArrayCount=0;sectionArrayCount<sectionArray.length;sectionArrayCount++){
						if(null!=sectionArray[sectionArrayCount].trim().toUpperCase() && sectionArray[sectionArrayCount].trim().toUpperCase()!=""){
							Section section=new Section();
							section.setUpdatedBy(sessionObject.getUserId());
							section.setSectionCode(sectionArray[sectionArrayCount].trim().toUpperCase());
							section.setSectionName(sectionArray[sectionArrayCount].trim().toUpperCase());
							section.setDesc(sectionArray[sectionArrayCount].trim());
							sectionList.add(section);
						}
					}
				}
				Section section=new Section();
				section.setUpdatedBy(sessionObject.getUserId());
				section.setSectionCode("NA");
				section.setSectionName("NA");
				section.setDesc("NA");
				sectionList.add(section);
				standard.setSectionList(sectionList);
				insertStatus=academicsService.addStandard(standard);
				/**Added by @author Saif.Ali
				 * Date-30-03-2018*/
				String description = "";
				if(insertStatus.equalsIgnoreCase("success")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("ENTER STANDARD AND SECTION DETAILS");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor("STANDARD :: "+ standard.getStandardName().trim().toUpperCase());
					description = description + ("STANDARD :: "+ standard.getStandardName().trim().toUpperCase() + " ; ");
					if(null!=sections && sections.length()!=0 && sections.endsWith(","))
						sections=sections.substring(0, sections.length() - 1);
					if(null!=sections && sections.length()!=0 && sections.startsWith(","))
						sections=sections.substring(1);
					if(null!=sections && sections.length()!=0){
						String sectionArray[]=sections.split(",");
						for(int i=0;i<sectionArray.length;i++){
							if(null!=sectionArray[i].trim().toUpperCase() && sectionArray[i].trim().toUpperCase()!=""){
								description = description + ("Section Name :: " + sectionArray[i].trim().toUpperCase() + " ; ");
							}
						}
					}
					updateLog.setDescription(description);
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 285 :: AcademicsController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
			}else{
				logger.info("Invalid Standard Name.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method addStandard-POST of AcademicsController", ce);
			ce.printStackTrace();
		}
		if(insertStatus.equals("success")){
			return createCourse(request, response, model);
		}else{
			model.addAttribute("insertUpdateStatus", insertStatus);
			msg = "Failed To Create Standard";
			model.addAttribute("msg", msg);
			return getStandard(request, response, model);
		}
	}

	
	/*
	 * @author sourav.bhadra
	 * Returns String
	 * changes taken on 27042017
	 */	
	@RequestMapping(value = "/editStandard", method = RequestMethod.POST)
	public ModelAndView editStandard(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editScholarship-POST of AcademicsController");
			Standard standard=new Standard();
			String saveId=request.getParameter("saveId");
			standard.setStandardId(Integer.parseInt(request.getParameter("standardId"+saveId)));
			String programName=request.getParameter("newProgramName");
			String batchName=request.getParameter("newBatchName");
			standard.setStandardName(programName.trim().toUpperCase());					
			standard.setStandardCode(programName.trim().toUpperCase());
			standard.setDesc(programName.trim().toUpperCase());
			standard.setUpdatedBy(sessionObject.getUserId());
			String sections=batchName.trim();
			List<Section> sectionList=new ArrayList<Section>();
			if(null!=sections && sections.length()!=0 && sections.endsWith(","))
				sections=sections.substring(0, sections.length() - 1);
			if(null!=sections && sections.length()!=0 && sections.startsWith(","))
				sections=sections.substring(1);
			if(null!=sections && sections.length()!=0){					
				String sectionArray[]=sections.split(",");
				for(int sectionArrayCount=0;sectionArrayCount<sectionArray.length;sectionArrayCount++){
					if(null!=sectionArray[sectionArrayCount].trim().toUpperCase() && sectionArray[sectionArrayCount].trim().toUpperCase()!=""){
						Section section=new Section();
						section.setUpdatedBy(sessionObject.getUserId());
						section.setSectionCode(sectionArray[sectionArrayCount].trim().toUpperCase());
						section.setSectionName(sectionArray[sectionArrayCount].trim().toUpperCase());
						section.setDesc(sectionArray[sectionArrayCount].trim());
						sectionList.add(section);
					}
				}
			}
			standard.setSectionList(sectionList);
			String updateStatus=academicsService.editStandard(standard);
			model.addAttribute("insertUpdateStatus", updateStatus);
			String msg = null;
			if(updateStatus.equals("success")){
				msg = "Program Updated SuccessFully";
			}else{
				msg = "Failed To Update Program";
			}
			model.addAttribute("msg", msg);
		} catch (CustomException ce){
			ce.printStackTrace();
			logger.error("Exception in method editStandard-POST of AcademicsController", ce);
		}
		return getStandard(request, response, model);
	}
	
	
	//****************************************************************Standard Ends
	
	/**
	 * @author anup.roy
	 * this method is for create page for subject
	 * 02.08.2017
	 */	
	@RequestMapping(value = "/getSubjectGroup", method = RequestMethod.GET)
	public String getSubjectGroup(HttpServletRequest request,HttpServletResponse response, 
			ModelMap model,@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = "academics/createSubjectGroup";
		try {
			logger.info("Inside Method getSubjectGroup-GET of AcademicsController");
			List<SubjectGroup> subjectGroupList=academicsService.getSubjectGroup();
			model.addAttribute("subjectGroupList", subjectGroupList);
			
			/**anup.roy 04.08.2017 for get list of subject types **/
			
			List<SubjectGroup> scholasticTypeList = academicsService.getScholasticTypeList();
			model.addAttribute("scholasticTypeList", scholasticTypeList);
			
			
			/*Added by Naimisha 18052018 for Adding ticket and task no*/
			String userId = sessionObject.getUserId();
			String functionality = "Create Subject";
			List<Task> taskList = commonService.getTaskNoListForUserAndFunctinalityWise(userId,functionality);
			
			List<Task>addTaskList = new ArrayList<>();
			List<Task>editTaskList = new ArrayList<>();
			
			for(Task task :taskList){
				if(null != task.getAction()){
					if(task.getAction().equalsIgnoreCase("ADD")){
						addTaskList.add(task);
					}else if(task.getAction().equalsIgnoreCase("EDIT")){
						editTaskList.add(task);
					}
				}
				
			}
			
			model.addAttribute("addTaskList", addTaskList);
			model.addAttribute("editTaskList", editTaskList);
			System.out.println("add size=="+addTaskList.size());
			System.out.println("edit size=="+editTaskList.size());
			
		} catch (CustomException ce){
			logger.error("Exception in method getSubjectGroup-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return strView;
	}
	
	/**
	 * @author anup.roy
	 * Adds New Subject
	 * 02.08.2017
	 */	
	@RequestMapping(value = "/addSubjectGroup", method = RequestMethod.POST)
	public String addSubjectGroup(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			SubjectGroup subjectGroup,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method addSubjectGroup-POST of AcademicsController");
			if(null!=subjectGroup && null!=subjectGroup.getSubjectGroupName() && subjectGroup.getSubjectGroupName().trim().length()!=0){
				subjectGroup.setSubjectGroupName(subjectGroup.getSubjectGroupName().trim().toUpperCase());
				subjectGroup.setSubjectGroupCode(subjectGroup.getSubjectGroupName().trim().toUpperCase());
				subjectGroup.setDesc(subjectGroup.getSubjectGroupName().trim());
				subjectGroup.setUpdatedBy(sessionObject.getUserId());
				
				String insertStatus = academicsService.addSubjectGroup(subjectGroup);
				String msg = null;
				if(insertStatus.equalsIgnoreCase("success")){
					msg = "Subject Created Successfully";
				}else{
					msg = "Subject Creation Failed";
				}
				model.addAttribute("insertUpdateStatus", insertStatus);
				model.addAttribute("msg",msg);
				/**Added by @author Saif.Ali
				 * Date-30-03-2018*/
				String scholasticTypeName = request.getParameter("scholasticTypeName");
				String subjectGroupName = request.getParameter("subjectGroupName");
				String subjectGroupOrderId = request.getParameter("subjectGroupOrderId");
				String totalHRSForCourse = request.getParameter("totalHRSForCourse");
				String description = "";
				if(scholasticTypeName != ""){
					if(scholasticTypeName.equalsIgnoreCase("scholastic_type-1")){
						description = description + ("Scholastic Type :: " + " Scholastic " + " ; ");
					}
					if(scholasticTypeName.equalsIgnoreCase("scholastic_type-2")){
						description = description + ("Scholastic Type :: " + " Co-Scholastic " + " ; ");
					}
				}
				if(subjectGroupName != ""){
					description = description + ("Subject Name :: " + subjectGroupName + " ; ");
				}
				if(subjectGroupOrderId != ""){
					description = description + ("Subject Credit :: " + subjectGroupOrderId + " ; ");
				}
				if(totalHRSForCourse != ""){
					description = description + ("Subject Duration :: " + totalHRSForCourse + " ; ");
				}
				description = description + " is added ";
				
				if(insertStatus.equalsIgnoreCase("success")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("ENTER SUBJECT DETAILS");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor("Scholastic Code :: " + scholasticTypeName);
					updateLog.setDescription(description);
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 425 :: AcademicsController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
			}else{
				logger.info("Invalid Standard Name.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method addSubjectGroup-POST of AcademicsController", ce);
			ce.printStackTrace();
		}
		return getSubjectGroup(request, response, model, sessionObject);
	}
	
	/**
	 * @author anup.roy
	 * Updates Subject Group
	 * 02.08.2017
	 */	
	@RequestMapping(value = "/editSubjectGroup", method = RequestMethod.POST)
	public String editSubjectGroup(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editSubjectGroup-POST of AcademicsController");
		
			SubjectGroup subjectGroup = new SubjectGroup();
			String saveId=request.getParameter("saveId");
			
			String credit = request.getParameter("getCredit");
			String hours = request.getParameter("getHours");
			
			//Added by naimisha 19052018
			
			String taskNo = request.getParameter("taskNoUpdate");
			String ticketNo = request.getParameter("ticketNoUpdate");
			
			
			subjectGroup.setSubjectGroupId(Integer.parseInt(request.getParameter("subjectGroupId"+saveId)));
			subjectGroup.setSubjectGroupOrderId(Integer.parseInt(credit));
			subjectGroup.setDesc(request.getParameter("subjectGroupName"+saveId).trim());
			subjectGroup.setSubjectGroupCode(request.getParameter("subjectGroupName"+saveId).trim().toUpperCase());
			subjectGroup.setSubjectGroupName(request.getParameter("subjectGroupName"+saveId).trim().toUpperCase());
			subjectGroup.setTotalHRSForCourse(Integer.parseInt(hours));
			
			//Added by naimisha 19052018
			
			subjectGroup.setTaskNo(taskNo);
			subjectGroup.setTicketNo(ticketNo);
			
			
			subjectGroup.setUpdatedBy(sessionObject.getUserId());
			
			String updateStatus =academicsService.editSubjectGroup(subjectGroup);
			model.addAttribute("insertUpdateStatus", updateStatus);
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Subject Updated Successfully";
			}else{
				msg = "Subject Updation Failed";
			}
			model.addAttribute("msg",msg);
			
			/**Added by @author Saif.Ali
			 * Date-30-03-2018*/
			int oldSubjectCredit = Integer.parseInt(request.getParameter("subjectGroupOrderId"+saveId));
			int oldSubjectTotalHours = Integer.parseInt(request.getParameter("totalHRSForCourse"+saveId));
			String subjectGroupName = request.getParameter("subjectGroupName"+saveId);
			
			String description = "";
			description = description + ("Subject Name :: " + subjectGroupName + " ; ");
			if(oldSubjectCredit != (Integer.parseInt(credit))){
				description = description + ("Subject Credit :: " + oldSubjectCredit + " updated to new Subject Credit :: " + (Integer.parseInt(credit)) + " ; ");
			}
			if(oldSubjectTotalHours != (Integer.parseInt(hours))){
				description = description + ("Subject Hour :: " + oldSubjectTotalHours + " updated to new Subject Hour :: " + (Integer.parseInt(hours)) + " ; ");
			}
			
			if(updateStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT SUBJECT DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("ACADEMICS");
				updateLog.setUpdatedFor("Subject Name :: " + subjectGroupName);
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 508 :: AcademicsController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
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
			logger.error("Exception in method editSubjectGroup-POST of AcademicsController", ce);
		}
		return getSubjectGroup(request, response, model, sessionObject);
	}
	
	//****************************************************************Subject Group Ends
	
	/*
	 * @author vinod.singh
	 * Opens page to add Subject
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getSubject", method = RequestMethod.GET)
	public String getSubject(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "academics/createSubject";
		try {
			logger.info("Inside Method getSubject-GET of AcademicsController");
			List<SubjectGroup> subjectGroupList=academicsService.getSubjectGroup();
			model.addAttribute("subjectGroupList", subjectGroupList);
			
			List<Subject> subjectList=commonService.getSubject();
			model.addAttribute("subjectList", subjectList);
			
		} catch (CustomException ce){
			logger.error("Exception in method getSubject-GET of AcademicsController", ce);
		}
		return strView;
	}
	
	/*
	 * @author vinod.singh
	 * Adds New Subject
	 * Returns String
	 * 
	 */	
	
	@RequestMapping(value = "/addSubject", method = RequestMethod.POST)
	public String addSubject(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Subject subject,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method addSubject-POST of AcademicsController");
			if(null!=subject && null!=subject.getSubjectName() && subject.getSubjectName().trim().length()!=0){
				subject.setSubjectName(subject.getSubjectName().trim().toUpperCase());
				subject.setSubjectCode(subject.getSubjectName().trim().toUpperCase());
				subject.setDesc(subject.getSubjectName().trim());
				subject.setUpdatedBy(sessionObject.getUserId());
				
				String insertStatus=academicsService.addSubject(subject);
				model.addAttribute("insertUpdateStatus", insertStatus);
			}else{
				logger.info("Invalid Standard Name.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method addSubject-POST of AcademicsController", ce);
		}
		return getSubject(request, response, model);
	}
	
	/*
	 * @author vinod.singh
	 * Updates Subject
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/editSubject", method = RequestMethod.POST)
	public String editSubject(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editSubjectGroup-POST of AcademicsController");
			
			Subject subject=new Subject();
			String saveId=request.getParameter("saveId");
			//System.out.println(saveId);
			
			subject.setSubjectId(Integer.parseInt(request.getParameter("subjectId"+saveId)));
			subject.setDesc(request.getParameter("subjectName"+saveId).trim());
			subject.setSubjectCode(request.getParameter("subjectName"+saveId).trim().toUpperCase());
			subject.setSubjectName(request.getParameter("subjectName"+saveId).trim().toUpperCase());
			subject.setSubjectGroup(request.getParameter("subjectGroup"+saveId));
			subject.setUpdatedBy(sessionObject.getUserId());
			
			String updateStatus=academicsService.editSubject(subject);
			model.addAttribute("insertUpdateStatus", updateStatus);
			
		} catch (CustomException ce){
			logger.error("Exception in method editSubjectGroup-POST of AcademicsController", ce);
		}
		return getSubject(request, response, model);
	}
	
	//****************************************************************Subject Ends
	
	/*
	 * @author vinod.singh
	 * Opens page to add exam marks
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getExamMarks", method = RequestMethod.GET)
	public String getExamMarks(HttpServletRequest request,
								HttpServletResponse response, ModelMap model) {
		String strView = "academics/createExamMarks";
		try {
			logger.info("Inside Method getExamMarks-GET of AcademicsController");
			
			List<Standard> standardList = commonService.getStandards();
			model.addAttribute("standardList", standardList);
			
		} catch (CustomException ce){
			logger.error("Exception in method getExamMarks-GET of AcademicsController", ce);
		}
		return strView;
	}
	
	

	
	
	
	/*
	 * @author vinod.singh
	 * Updates Exam Marks
	 * Returns String
	 * 
	 */	
	
	
	
	/*
	 * @author vinod.singh
	 * Opens page to Upload Result
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getUploadResult", method = RequestMethod.GET)
	public String getUploadResult(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = "academics/createResult";
		try {
			logger.info("Inside Method getUploadResult-GET of AcademicsController");
			
			List<Standard> standardList=commonService.getStandards();
			model.addAttribute("standardList", standardList);
			
			List<Course> courseList = academicsService.getCourseList();
			model.addAttribute("courseList", courseList);
			
			model.addAttribute("loggedInUser", sessionObject.getUserId());
			
		} catch (CustomException ce){
			logger.error("Exception in method getUploadResult-GET of AcademicsController", ce);
		}
		return strView;
	}
	
	/**
	 * modified by anup.roy
	 * Gets Subjects For Standard
	 */
	@RequestMapping(value = "/getSubjectGroupForStandard", method = RequestMethod.GET)
	public @ResponseBody
	String getSubjectGroupForStandard(@RequestParam("standard") String standard) {
		logger.info("Inside Method getSubjectGroupForStandard-GET of AcademicsController");
		String subjectGroup = "";
		try {
			subjectGroup = academicsService.getSubjectGroupForStandard(standard);
		} catch (CustomException ce) {
			logger.error("Exception in method getSubjectGroupForStandard-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(subjectGroup));
	}
	
	/* 
	 * Gets Students Subjects And Marks
	 */
	/*changes by naimisha 28022017*/
	@RequestMapping(value = "/getStudentsAndMarks", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentsAndMarks(@RequestParam("course") String standard,
										@RequestParam("exam") String exam,
										@RequestParam("subject") String subject,
										@RequestParam("section") String section,
										@RequestParam("term") String term,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("Inside Method getStudentsAndMarks-GET of AcademicsController");
		String subjectAndMarks = "";
		try {
			StudentResult studentResult=new StudentResult();
			studentResult.setStandard(standard);
			studentResult.setExam(exam);
			studentResult.setSubject(subject);
			studentResult.setSection(section);
			studentResult.setObjectId(term);
			String loggedInUser = sessionObject.getUserId();
			subjectAndMarks = academicsService.getStudentsAndMarks(studentResult, loggedInUser);
			System.out.println("subjectAndMarks======"+subjectAndMarks);
		} catch (CustomException ce) {
			logger.error("Exception in method getStudentsAndMarks-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(subjectAndMarks));
	}
	
	
	@RequestMapping(value = "/editStudentResult", method = RequestMethod.POST)
	public String editStudentResult(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="course") String standard,
			@RequestParam(value="section") String section,
			@RequestParam(value="subject") String subject,
			@RequestParam(value="exam") String exam,
			@RequestParam(value="term") String term,
			@RequestParam(value="insertUpdate") String insertUpdate,
			@RequestParam(value="rollNumber") String []rollNumber,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editStudentResult-POST of AcademicsController");
			List<StudentResult> studentResultList=new ArrayList<StudentResult>();
			for(int loopControl=0;loopControl<rollNumber.length;loopControl++){
				StudentResult studentResult=new StudentResult();
				studentResult.setUpdatedBy(sessionObject.getUserId());
				studentResult.setSubject(subject);
				studentResult.setStandard(standard);
				studentResult.setSection(section);
				studentResult.setRollNumber(rollNumber[loopControl]);
				studentResult.setExam(exam);
				studentResult.setDesc(term);
				double weightageObtained=0;
				
				String theoryFull=request.getParameter(rollNumber[loopControl]+"theoryTotal");
				studentResult.setTheory(Integer.parseInt(theoryFull.trim()));
				studentResult.setTheoryPass(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"theoryPass").trim()));
				String theoryObtained=request.getParameter(rollNumber[loopControl]+"theory");
				if(!theoryObtained.equalsIgnoreCase("AB"))
					studentResult.setTheoryObtained(Integer.parseInt(theoryObtained.trim()));
				else
					studentResult.setTheoryObtained(-1);
				
				String practicalFull=request.getParameter(rollNumber[loopControl]+"practicalTotal");
				studentResult.setPractical(Integer.parseInt(practicalFull.trim()));
				studentResult.setPracticalPass(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"practicalPass").trim()));
				String practicalbtained=request.getParameter(rollNumber[loopControl]+"practical");
				if(!practicalbtained.equalsIgnoreCase("AB"))
					studentResult.setPracticalObtained(Integer.parseInt(practicalbtained.trim()));
				else
					studentResult.setPracticalObtained(-1);
				
				String totalFull=request.getParameter(rollNumber[loopControl]+"total");
				studentResult.setTotal(Integer.parseInt(totalFull.trim()));
				String totalbtained=request.getParameter(rollNumber[loopControl]+"obtained");
				if(!totalbtained.equalsIgnoreCase("AB")){
					studentResult.setTotalObtained(Integer.parseInt(totalbtained.trim()));
					weightageObtained=Integer.parseInt(totalbtained.trim());
				}else{
					studentResult.setTotalObtained(-1);
				}
				weightageObtained=weightageObtained/Integer.parseInt(totalFull.trim());
				studentResult.setWeightageObtained(weightageObtained);
				
				String pass=request.getParameter(rollNumber[loopControl]+"totalPass");
				studentResult.setPass(Integer.parseInt(pass.trim()));
				String passFail=request.getParameter(rollNumber[loopControl]+"passfail");
				studentResult.setPassFail(passFail);
				
				studentResultList.add(studentResult);
			}
			String updateStatus = null;
			if(studentResultList.size()!=0){
				 updateStatus=academicsService.editStudentResult(studentResultList, insertUpdate);
				model.addAttribute("insertUpdateStatus", updateStatus);
				if(updateStatus.equalsIgnoreCase("success")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("STUDENT RESULT");
					//updateLog.setInsertUpdate(insertUpdate);
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor(standard+"-"+section+"-"+subject+"-"+exam);
					updateLog.setDescription(subject+" Marks Was Updated For Programme "+standard+"("+section+") For Exam : "+exam);
					commonService.insertIntoActivityLog(updateLog);
				}
			}
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Marks Uploaded SuccessFully";
			}else{
				msg = "Failed To Upload Marks";
			}
			model.addAttribute("msg", msg);
		} catch (CustomException ce){
			ce.printStackTrace();
			logger.error("Exception in method editStudentResult-POST of AcademicsController", ce);
		}
		return getUploadResult(request, response, model, sessionObject);
	}
	
	/**
	 * modified by sourav.bhadra
	 * changes taken on 27042017*/
	
	@RequestMapping(value = "/downloadStandardSubjectMappExcel", method = RequestMethod.GET)
	public ModelAndView downloadStandardSubjectMappExcel(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		try{
			logger.info("In downloadStandardSubjectMappExcel() of AcademicsController");
			String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelDownloadFolder, standardSubjectMappingExcel);
			if(returnMessage == null){
				model.addAttribute("ResultError", "File not available");
			}
		}catch(Exception e){
			logger.error("Exception in downloadStandardSubjectMappExcel() download Template Excel AcademicsController", e);
		}
		return getCourseSubjectMapping(request, response, model);
	}
	
	
//	@RequestMapping(value = "/uploadStandardSubjectMappExcel", method = RequestMethod.POST)
//	public String uploadStandardSubjectMappExcel(@ModelAttribute("uploadFile") UploadFile uploadFile,
//											HttpServletRequest request,HttpServletResponse response,
//											ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject){
//		try{
//			logger.info("In uploadStandardSubjectMappExcel() of AcademicsController");
//			String returnMessage = fileUploadDownload.uploadExcel(uploadFile,rootPath+excelUploadfolder,standardSubjectMappingExcel);
//			if(returnMessage == null){
//				model.addAttribute("uploadErrorMessage", standardSubjectMappingExcel+" upload failed.");
//			}else{
//				int excelDataInsertStatus = academicsService.insertStandardSubjectMappExcelBulkData(rootPath+excelUploadfolder+standardSubjectMappingExcel, sessionObject.getUserId());
//				model.addAttribute("excelDataInsertStatus", excelDataInsertStatus);
//			}
//		}catch(Exception e){
//			logger.error("Exception in uploadStandardSubjectMappExcel() IN AcademicsController", e);
//		}
//		return getCourseSubjectMapping(request, response, model);
//	}
	
	/**
	 * @author anup.roy
	 * Opens page to Assign Section To Student
	 */	
	@RequestMapping(value = "/createAssignSection", method = RequestMethod.GET)
	public String createAssignSection(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "academics/createAssignSection";
		try {
			logger.info("Inside Method createAssignSection-GET of AcademicsController");
			
			List<Standard> standardList=academicsService.getStandardsForAssignBatch();
			model.addAttribute("standardList", standardList);
			//List<course>courseList = academicsService
			
		} catch (CustomException ce){
			logger.error("Exception in method createAssignSection-GET of AcademicsController", ce);
		}
		return strView;
	}
	
	/**
	 * 
	 *Gets students to assign section
	 */
	@RequestMapping(value = "/getStudentsToAssignSection", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentsToAssignSection(@RequestParam("standard") String standard,
										@RequestParam("section") String section) {
		String student = null;
		try {
			logger.info("getStudentsToAssignSection() In CommonController.java");
			//System.out.println("standard==="+standard);
			//System.out.println("Section=="+section);
			student = academicsService.getStudentsToAssignSection(standard, section);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getStudentsToAssignSection() In CommonController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStudentsToAssignSection() In CommonController.java: Exception"
					+ e);
		}
		return student;
	}
	
	/**
	 * @author anup.roy
	 * Adds Students Section
	 * Returns String
	 */	
	@RequestMapping(value = "/insertAssignSection", method = RequestMethod.POST)
	public String insertAssignSection(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									@RequestParam(value="standard") String standard,
									@RequestParam(value="section") String section,
									@RequestParam(value="rollNumber") String []rollNumber,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method insertAssignSection-POST of AcademicsController");
			if(null!=rollNumber && null!=section && rollNumber.length!=0 && section.trim().length()!=0){
				List<Student> studentList=new ArrayList<Student>();
				for(int studentCount=0;studentCount<rollNumber.length;studentCount++){
					Student student=new Student();
					student.setUpdatedBy(sessionObject.getUserId());
					//student.setRollNumber(Integer.parseInt(rollNumber[studentCount]));
					student.setUserId(rollNumber[studentCount]);
					student.setSection(section);
					student.setStandard(standard);
					studentList.add(student);
				}
				String insertStatus=academicsService.insertAssignSection(studentList);
				
				model.addAttribute("insertUpdateStatus", insertStatus);
				String msg = null;
				if(insertStatus.equalsIgnoreCase("success")){
					msg = "Section Assigned Successfully";
				}else{
					msg = "Failed To Assign Section";
				}
				
				model.addAttribute("updateStatus", insertStatus);
				model.addAttribute("msg", msg);
				if(insertStatus.equals("success")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("STUDENT DETAILS");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("ACADEMICS SECTION");
					for(Student student:studentList){
						updateLog.setUpdatedFor(student.getUserId()+"");
						updateLog.setDescription("A New Studentis of User Id "+student.getUserId()+" of program  "+student.getStandard()+" is assigned to Batch "+student.getSection());
					}
					commonService.insertIntoActivityLog(updateLog);
				}
				
				//PRAD MAY 30 2018
				/**Added by @author Saif.Ali
				 * Date- 19/02/2018
				 * Used to add the json which will send the assign section details to the web*/
				// If Insert Successful, and WEBIQ is available, then call the API
				
				String json_response = "";
				JSONObject jsonObj = null;
				
				if(insertStatus.equalsIgnoreCase("success")  && isWebIQAvailable.equalsIgnoreCase("true"))
				{
					jsonObj = new JSONObject();
					jsonObj.put("username",portalUserName);
					jsonObj.put("password",portalPassWord);
					jsonObj.put("standard",studentList.get(0).getStandard());
					jsonObj.put("section",studentList.get(0).getSection());
					JSONArray rollNumberArray = new JSONArray();
					for(Student student: studentList)
					{
						// creating a JSON Array and adding the roll numbers in it 
						rollNumberArray.put(student.getUserId());
					}
					jsonObj.put("rollNumbers", rollNumberArray);
					System.out.println("LN 866 :: Json Object Contents"+jsonObj.toString());
					final String uri = URIForAssignSectionToStudentsDetails;
					System.out.println("URI:::"+uri);
					URL url = new URL(uri);
					HttpURLConnection connection = (HttpURLConnection)url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestProperty("Content-Type", "application/json");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					connection.setRequestMethod("POST");
					OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
					out.write(jsonObj.toString());
					out.close();
					
					
					InputStreamReader in = new InputStreamReader(connection.getInputStream());
					BufferedReader br = new BufferedReader(in);
					String text = "";
					while((text = br.readLine())!= null){
						json_response += text;
					}
					System.out.println("JSON response:::"+ json_response);
				}
				/**Modification ends*/
				
				if((!json_response.isEmpty())){
					JSONObject object = new JSONObject(json_response);
					int statusFromJsonResponse = (int)object.get("status");
					
					WebIQTransaction webIQTran= null;
					
					if(statusFromJsonResponse==200){
						//If call to the API is successful, then update the webiq_transaction_details table with data
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForAssignSectionToStudentsDetails);
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(true);
					}else{
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForAssignSectionToStudentsDetails);
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(false);
					}
					
					//Call to the BackOffice Service
					backOfficeService.addWebIQTransaction(webIQTran);
				}
				
				//PRAD ENDS
				
			}else{
				logger.info("Student Or Section Not Found To Update");
			}
		} catch (CustomException ce){
			logger.error("Exception in method insertAssignSection-POST of AcademicsController", ce);
			ce.printStackTrace();
		} catch (Exception e){
			logger.error("Exception in method insertAssignSection-POST of AcademicsController", e);
			e.printStackTrace();
		}
		return createAssignSection(request, response, model);
	}
	
	
	/*
	 * @author vinod.singh
	 * Opens page to View pending section assignment
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getPendingSectionAssignment", method = RequestMethod.GET)
	public String getPendingSectionAssignment(HttpServletRequest request,
												HttpServletResponse response,
												ModelMap model) {
		String strView = "academics/createViewPendingSectionAssignment";
		try {
			logger.info("Inside Method getPendingSectionAssignment-GET of AcademicsController");
			
			List<Student> pendingList=academicsService.getPendingSectionAssignment();
			model.addAttribute("pendingList", pendingList);
			
		} catch (CustomException ce){
			logger.error("Exception in method getPendingSectionAssignment-GET of AcademicsController", ce);
		}
		return strView;
	}
		
	
	/**
	 * 
	 * Opens page to add co-scholastic result
	 */
	@RequestMapping(value = "/createCoScholasticResult", method = RequestMethod.GET)
	public String createCoScholasticResult(HttpServletRequest request,
						HttpServletResponse response,
						ModelMap model,
						@RequestParam("standard") String standard,
						@RequestParam("section") String section) {
		String strView = "academics/createCoScholasticResult";
		try {
			logger.info("Inside Method createCoScholasticResult-GET of AcademicsController");
			
			CoScholasticResult coScholasticResult = new CoScholasticResult();
			coScholasticResult.setStandard(standard);
			coScholasticResult.setSection(section);
			List<CoScholasticResult> studentList = academicsService.getStudentsForCoScholastic(coScholasticResult);
			if(null != studentList && studentList.size() != 0){
				model.addAttribute("student", studentList.get(0));
			}else{
				return getCoScholasticResultList(request, response, model);
			}
			List<DescriptiveIndicatorSkill> descriptiveIndicatorSkillList = academicsService.getDescriptiveIndicatorHeadList();
			model.addAttribute("descriptiveIndicatorSkillList", descriptiveIndicatorSkillList);
			
			
		} catch (CustomException ce){
			logger.error("Exception in createCoScholasticResult() of AcademicsController", ce);
		}
		return strView;
	}
	
	
	/*
	 * Opens sytatus list of co scholastic result
	 */
	@RequestMapping(value = "/getCoScholasticResultList", method = RequestMethod.GET)
	public String getCoScholasticResultList(HttpServletRequest request,
						HttpServletResponse response,
						ModelMap model) {
		String strView = "academics/listCoScholasticResult";
		try {
			logger.info("Inside Method getCoScholasticResultList-GET of AcademicsController");
			
			List<CoScholasticResult> resultStatusList = academicsService.getCoScholasticResultList();
			model.addAttribute("resultStatusList", resultStatusList);			
		} catch (CustomException ce){
			logger.error("Exception in method getCoScholasticResultList-GET of AcademicsController", ce);
		}
		return strView;
	}
	
	
	/*
	 * @author vinod.singh
	 * Saves Co Scholastic Result
	 * Returns String
	 */	
	@RequestMapping(value = "/saveCoScholasticResult", method = RequestMethod.POST)
	public String saveCoScholasticResult(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									@RequestParam(value="standard") String standard,
									@RequestParam(value="section") String section,
									@RequestParam(value="name") String name,
									@RequestParam(value="rollNumber") String rollNumber,
									@RequestParam(value="skill") String[] skill,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method saveCoScholasticResult-POST of AcademicsController");
			if(null != skill && skill.length != 0){
				List<CoScholasticResult> studentList = new ArrayList<CoScholasticResult>();
				for(int skillCount = 0; skillCount<skill.length; skillCount++){
					String[] head = request.getParameterValues(skill[skillCount]+"head");
					if(null != head && head.length != 0){
						for(int headCount = 0; headCount<head.length; headCount++){
							CoScholasticResult student = new CoScholasticResult();
							student.setUpdatedBy(sessionObject.getUserId());
							student.setRollNumber(rollNumber);
							student.setSection(section);
							student.setStandard(standard);
							student.setName(name);
							student.setSkill(skill[skillCount]);
							student.setHead(head[headCount]);
							if(request.getParameter(head[headCount]+"Indicator").equalsIgnoreCase("OTHERS"))
								student.setIndicator(request.getParameter(head[headCount]+"IndicatorOther"));
							else
								student.setIndicator(request.getParameter(head[headCount]+"Indicator"));
							student.setGrade(request.getParameter(head[headCount]+"Grade"));
							String gradePoint=request.getParameter(head[headCount]+"GradePoint");
							if(null!=gradePoint && gradePoint.trim().length()!=0)
								student.setGradePoint(Double.parseDouble(gradePoint.trim()));
							
							studentList.add(student);
						}
					}
				}
				String insertStatus=academicsService.saveCoScholasticResult(studentList);
				model.addAttribute("insertUpdateStatus", insertStatus);
				if(insertStatus.equalsIgnoreCase("Insert Successful")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("STUDENT RESULT");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor(rollNumber);
					updateLog.setDescription("Co Scholastics Result Was Inserted For "+name+"("+rollNumber+")");
					commonService.insertIntoActivityLog(updateLog);
				}					
			}else{
				logger.info("Student Or Section Not Found To Update");
			}
		} catch (CustomException ce){
			logger.error("Exception in method saveCoScholasticResult-POST of AcademicsController", ce);
		}
		return createCoScholasticResult(request, response, model, standard, section);
	}
	
	
	/**
	 * 
	 * Opens page to update co-scholastic result
	 */
	@RequestMapping(value = "/updateCoScholasticResult", method = RequestMethod.GET)
	public String updateCoScholasticResult(HttpServletRequest request,
						HttpServletResponse response,
						ModelMap model,
						@RequestParam("standard") String standard,
						@RequestParam("section") String section,
						@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = "academics/editCoScholasticResult";
		try {
			logger.info("Inside Method updateCoScholasticResult-GET of AcademicsController");
			
			CoScholasticResult coScholasticResult = new CoScholasticResult();
			coScholasticResult.setStandard(standard);
			coScholasticResult.setSection(section);
			List<CoScholasticResult> studentList = academicsService.getInsertedCoScholasticStudents(coScholasticResult);
			if(null != studentList && studentList.size()!=0){
				model.addAttribute("studentList", studentList);
			}else{
				return getCoScholasticResultList(request, response, model);
			}
			
			model.addAttribute("loggedInUser", sessionObject.getUserId());
			
			List<DescriptiveIndicatorSkill> descriptiveIndicatorSkillList = academicsService.getDescriptiveIndicatorHeadList();
			model.addAttribute("descriptiveIndicatorSkillList", descriptiveIndicatorSkillList);

		} catch (CustomException ce){
			logger.error("Exception in method updateCoScholasticResult-GET of AcademicsController", ce);
		}
		return strView;
	}
	
	/* 
	 * Gets Students Subjects And Marks
	 */
	@RequestMapping(value = "/getStudentsCoScholasticResult", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentsCoScholasticResult(@RequestParam("roll") String roll,
										@RequestParam("loggedInUser") String loggedInUser) {
		logger.info("Inside Method getStudentsCoScholasticResult-GET of AcademicsController");
		String result = "";
		try {
			result = academicsService.getStudentsCoScholasticResult(roll, loggedInUser);			
		} catch (CustomException ce) {
			logger.error("Exception in method getStudentsCoScholasticResult-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(result));
	}
	
	/*
	 * @author vinod.singh
	 * Saves Co Scholastic Result
	 * Returns String
	 */	
	@RequestMapping(value = "/editCoScholasticResult", method = RequestMethod.POST)
	public String editCoScholasticResult(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									@RequestParam(value="rollNumber") String rollNumber,
									@RequestParam(value="skill") String[] skill,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside editCoScholasticResult()-POST of AcademicsController");
			if(null != skill && skill.length != 0){
				List<CoScholasticResult> studentList = new ArrayList<CoScholasticResult>();
				
				for(int skillCount = 0; skillCount<skill.length; skillCount++){
					String[] head = request.getParameterValues(skill[skillCount]+"head");
					if(null != head && head.length != 0){
						for(int headCount = 0; headCount<head.length; headCount++){
							CoScholasticResult student = new CoScholasticResult();
							student.setUpdatedBy(sessionObject.getUserId());
							student.setRollNumber(rollNumber);
							student.setSkill(skill[skillCount]);
							student.setHead(head[headCount]);
							if(request.getParameter(head[headCount]+"Indicator").equalsIgnoreCase("OTHERS"))
								student.setIndicator(request.getParameter(head[headCount]+"IndicatorOther"));
							else
								student.setIndicator(request.getParameter(head[headCount]+"Indicator"));
							student.setGrade(request.getParameter(head[headCount]+"Grade"));
							String gradePoint = request.getParameter(head[headCount]+"GradePoint");
							if(null != gradePoint && gradePoint.trim().length() != 0)
								student.setGradePoint(Double.parseDouble(gradePoint.trim()));
							
							studentList.add(student);
						}
					}
				}
				String insertStatus = academicsService.editCoScholasticResult(studentList);
				model.addAttribute("insertUpdateStatus", insertStatus);
				if(insertStatus.equalsIgnoreCase("Update Successful")){
					UpdateLog updateLog = new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("STUDENT RESULT");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor(rollNumber);
					updateLog.setDescription("Co Scholastics Result Was Inserted For Roll Number "+rollNumber);
					commonService.insertIntoActivityLog(updateLog);
				}
			}else{
				logger.info("Student Or Section Not Found To Update");
			}
		} catch (CustomException ce){
			logger.error("Exception in editCoScholasticResult()-POST of AcademicsController", ce);
		}
		return getCoScholasticResultList(request, response, model);
	}
	
	/*
	 * @author vinod.singh
	 * Opens page to list students
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/viewStudentList", method = RequestMethod.GET)
	public ModelAndView viewStudentList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		
		try {
			logger.info("Inside Method getStudentList-GET of AcademicsController");
			
			List<Student> studentList=academicsService.getAllStudentsList();
			//model.addAttribute("studentList", studentList);
			
			if (studentList != null && studentList.size() != 0) {
				
				model.addAttribute("studentList", studentList);
			}
			
		} catch (CustomException ce){
			logger.error("Exception in method viewStudentList-GET of AcademicsController", ce);
		}
		return new ModelAndView("academics/viewStudentList");
	}
	
	@RequestMapping(value = "/getStudentDetailsToView", method = RequestMethod.POST, params = "searchSubmit")
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
	
	@RequestMapping(value = "/getStudentDetailsToView", method = RequestMethod.GET)
	public ModelAndView viewStudentProfileDetails(HttpServletRequest request,
												HttpServletResponse response, 
												ModelMap model,
												@RequestParam("userId") String userId,
												Student student) {
		logger.info("In viewStudentProfileDetails() method of BackOfficeController: ");
		String strView = null;
		
		try {
			logger.info("In BackOfficeController viewStudentProfileDetails()");
			Resource r = new Resource();
			//System.out.println("resdfd;;"+registrationId);
			//int roll_no =  Integer.parseInt(registrationId);
			//r.setResourceId(roll_no);
			r.setUserId(userId);
			/*r.setRegistrationId(registrationId);*/
			student.setResource(r);
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
	/*@RequestMapping(value = "/getStudentDetailsToView", method = {RequestMethod.POST, RequestMethod.GET})
	public String getStudentDetailsToView(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="rollNumber") String rollNumber) {
		String strView = "backoffice/studentProfilePage";
		try {
			logger.info("Inside Method getStudentDetails-POST of BackOfficeController");
			if(null != rollNumber){
				Student student = backOfficeService.getStudentDetails(rollNumber);
				
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
					 viewStudentList( request, response,  model);
				}			
				model.addAttribute("student", student);			

				List<SocialCategory> socialCategoryList=backOfficeService.getSocialCategoryList();
				model.addAttribute("socialCategoryList", socialCategoryList);
				
				List<Section> sectionList=backOfficeService.getSectionListForStandard(student.getStandard());
				model.addAttribute("sectionList", sectionList);
				
				List<Standard> standardList=commonService.getStandards();
				model.addAttribute("standardList", standardList);
				
				List<Candidate> candidateList=backOfficeService.getFeesPaidCandidate();
				model.addAttribute("candidateList", candidateList);
				
				List<Hostel> hostelList=hostelService.getHostel();
				model.addAttribute("hostelList", hostelList);
				
				List<Country> countryList=commonService.getCountryList();
				model.addAttribute("countryList", countryList);
				
				List<State> stateList=commonService.getAllStatesInIndia("IND");
				model.addAttribute("stateList", stateList);
				
				List<State> permanentStateList=commonService.getAllStatesInIndia(student.getAddress().getPermanentAddressCountry());
				model.addAttribute("permanentStateList", permanentStateList);
				
				List<State> presentStateList=commonService.getAllStatesInIndia(student.getAddress().getPresentAddressCountry());
				model.addAttribute("presentStateList", presentStateList);
				
				List<State> guardianStateList=commonService.getAllStatesInIndia(student.getAddress().getGuardianAddressCountry());
				model.addAttribute("guardianStateList", guardianStateList);
							
				List<Scholarship> scholarshipList=backOfficeService.getScholarshipList();
				model.addAttribute("scholarshipList", scholarshipList);
				model.addAttribute("fileDeleteStatus", null);
				
				
				
				
			}else{
				model.addAttribute("message", "Students Details Not Found");
				 viewStudentList( request, response,  model);
			}
		} catch (CustomException ce){
			model.addAttribute("message", "Students Details Not Found");
			viewStudentList( request, response,  model);
			logger.error("Exception in method getStudentDetails-POST of BackOfficeController", ce);
		}
		return strView;
	}
	*/
	
	/*
	 * @author sayani.datta
	 * Pagination for student template list
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/studentsListPagination", method = RequestMethod.GET)
	public ModelAndView studentsPagination(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("Inside Method studentsPagination-GET of BackOfficeController");
			mav = new ModelAndView("academics/viewStudentList");
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
	 * @author vinod.singh
	 * Opens page to Create User Defined Exams
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/createUserdefinedExams", method = RequestMethod.GET)
	public String createUserdefinedExams(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "academics/createUserdefinedExams";
		try {
			logger.info("Inside Method createUserdefinedExams-GET of AcademicsController");
			
			List<UserDefinedExams> examList=academicsService.getAllUserDefinedExams();
			/*for(UserDefinedExams ude : examList){
				System.out.println("EXAM NAME:"+ude.getExamName()+"--"+ude.getExamCode());
				System.out.println("start date = "+ude.getStartDate());
				for(Course c : ude.getCourseList()){
					System.out.println("COURSE NAME:"+c.getCourseCode()+"--"+c.getCourseName());
				}
			}*/
			model.addAttribute("examList", examList);
			List<Course> courseList=academicsService.getCourseList();
			model.addAttribute("courseList", courseList);
			List<ExamType>examTypeListFromDB = academicsService.getExamType();	
			//System.out.println("examTypeListFromDB ==="+examTypeListFromDB.size());
			if(examTypeListFromDB != null && examTypeListFromDB.size() != 0){
				model.addAttribute("examTypeListFromDB",examTypeListFromDB);
			}
			
		} catch (CustomException ce){
			logger.error("Exception in method createUserdefinedExams-GET of AcademicsController", ce);
		}
		return strView;
	}
	

	
	
	
	
	
	/*
	 * @author vinod.singh
	 * Adds New User Defined Exams
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/addUserDefinedExams", method = RequestMethod.POST)
	public String addUserDefinedExams(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			UserDefinedExams userDefinedExams,
			@RequestParam(value="course", required = false) String []course,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method addUserDefinedExams-POST of BackOfficeController");
			if(null!=userDefinedExams && null!=userDefinedExams.getExamName() && userDefinedExams.getExamName().trim().length()!=0){
				userDefinedExams.setExamName(userDefinedExams.getExamName().trim().toUpperCase());
				userDefinedExams.setExamCode(userDefinedExams.getExamName().trim().toUpperCase());
				userDefinedExams.setDesc(userDefinedExams.getExamName().trim());
				userDefinedExams.setUpdatedBy(sessionObject.getUserId());				
				userDefinedExams.setStartDate(userDefinedExams.getStartDate().trim());
				userDefinedExams.setEndDate(userDefinedExams.getEndDate().trim());
				if(null!=course && course.length!=0){
					List<Course> courseList=new ArrayList<Course>();
					for(int i=0;i<course.length;i++){
						Course c=new Course();
						c.setCourseCode(course[i]);
						courseList.add(c);
					}
					userDefinedExams.setCourseList(courseList);
				}
				
				String insertStatus= academicsService.addUserDefinedExams(userDefinedExams);
				model.addAttribute("insertUpdateStatus", insertStatus);
				String msg = null;
				if(insertStatus.equalsIgnoreCase("success")){
					msg = "Exam Created Successfully";
				}else{
					msg = "Exam Creation Failed";
				}
				model.addAttribute("insertUpdateStatus", insertStatus);
				model.addAttribute("msg",msg);
			}else{
				logger.info("Invalid Hostel Name.");
			}
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method addUserDefinedExams-POST of BackOfficeController", ce);
		}
		return createUserdefinedExams(request, response, model);
	}
	
	/*
	 * @author vinod.singh
	 * Updates User Defined Exams
	 * Returns String
	 * modified by saurav.bhadra
	 * changes taken on 05042017
	 */	
	@RequestMapping(value = "/editUserDefinedExams", method = RequestMethod.POST)
	public String editUserDefinedExams(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			//System.out.println("Inside Method editUserDefinedExams-POST of BackOfficeController");
			List<UserDefinedExams> userDefinedExamsList=new ArrayList<UserDefinedExams>();
			String rowId = request.getParameter("saveId");			
			String course = request.getParameter("course"+rowId);			
			String oldExamName = request.getParameter("examCode"+rowId).trim().toUpperCase();
			UserDefinedExams userDefinedExams=new UserDefinedExams();
			userDefinedExams.setCourse(course);
			userDefinedExams.setExamName((request.getParameter("newCourseName"+rowId).trim()).toUpperCase());
			userDefinedExams.setStartDate((request.getParameter("newExamStartDate").trim()));
			userDefinedExams.setEndDate((request.getParameter("newExamEndDate").trim()));
			userDefinedExams.setExamCode(oldExamName);
			userDefinedExamsList.add(userDefinedExams);
			
			String updateStatus = null;
			
			if(userDefinedExamsList.size()!=0)
			{
				 updateStatus= academicsService.editUserDefinedExams(userDefinedExamsList);
				
			}
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Exam Updated Successfully";
			}else{
				msg = "Exam Updation Failed";
			}
			model.addAttribute("insertUpdateStatus", updateStatus);
			model.addAttribute("msg",msg);
		} catch (Exception ce){	
			ce.printStackTrace();
			logger.error("Exception in method editUserDefinedExams-POST of BackOfficeController", ce);
		}
		return createUserdefinedExams(request, response, model);
	}
	
	/*
	 * @author vinod.singh
	 * Opens page to Set User Defined Exams Marks
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/createUserdefinedExamsMarks", method = RequestMethod.GET)
	public String createUserdefinedExamsMarks(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "academics/createUserExamMarks";
		try {
			logger.info("Inside Method createUserdefinedExams-GET of AcademicsController");
			
			List<Standard> standardList=commonService.getStandards();
			List<Course> courseList = academicsService.getCourseList();
			model.addAttribute("courseList", courseList);
			model.addAttribute("standardList", standardList);
			
		} catch (CustomException ce){
			logger.error("Exception in method createUserdefinedExams-GET of AcademicsController", ce);
		}
		return strView;
	}
	
	
	/* 
	 * Gets User Defined Exams For Standard
	 */
	@RequestMapping(value = "/getUserDefinedExamsForStandard", method = RequestMethod.GET)
	public @ResponseBody
	String getUserDefinedExamsForStandard(@RequestParam("standard") String standard) {
		logger.info("Inside Method getUserDefinedExamsForStandard-GET of AcademicsController");
		String result = "";
		try {
			List<UserDefinedExams> examList= academicsService.getUserDefinedExamsForStandard(standard);
			
			if(null!= examList && examList.size()!=0){
				for(UserDefinedExams ude: examList){
					result=result+ude.getExamCode()+"*";
				}
			}
		} catch (CustomException ce) {
			logger.error("Exception in method getUserDefinedExamsForStandard-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(result));
	}
	
	/* 
	 * Gets Subjects And Marks For User Defined Exams
	 */
	@RequestMapping(value = "/getSubjectAndMarksForUserDefinedExams", method = RequestMethod.GET)
	public @ResponseBody
	String getSubjectAndMarksForUserDefinedExams(@RequestParam("course") String course,
												@RequestParam("exam") String exam) {
		logger.info("Inside Method getSubjectAndMarksForUserDefinedExams-GET of AcademicsController");
		String result = "";
		try {
			UserExamMarks userExamMarks=new UserExamMarks();
			userExamMarks.setCourse(course);
			userExamMarks.setExam(exam);
			List<UserExamMarks> marksList= academicsService.getSubjectAndMarksForUserDefinedExams(userExamMarks);
			
			if(null!= marksList && marksList.size()!=0){
				for(UserExamMarks uem: marksList){
					result=result+
					uem.getSubject()+"*~*"+
					uem.getTheory()+"*~*"+
					uem.getPractical()+"*~*"+
					uem.getTotal()+"*~*"+
					uem.getPass()+"*~*"+
					uem.getTheoryPass()+"*~*"+
					uem.getPracticalPass()+"###";
				}
			}		
			
		} catch (CustomException ce) {
			logger.error("Exception in method getSubjectAndMarksForUserDefinedExams-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(result));
	}
	
	
	/*
	 * @author vinod.singh
	 * Updates Exam Marks
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/editUserExamMarks", method = RequestMethod.POST)
	public String editUserExamMarks(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			UserExamMarks userExamMarks,
			@RequestParam(value="subject") String []allSubjects,
			@RequestParam(value="term") String term,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editUserExamMarks-POST of AcademicsController");
			List<UserExamMarks> examMarksList=null;
			if(null!=allSubjects && allSubjects.length!=0){
				examMarksList=new ArrayList<UserExamMarks>();
				for(int subject=0;subject<allSubjects.length;subject++){
					UserExamMarks examMarks=new UserExamMarks();
					examMarks.setUpdatedBy(sessionObject.getUserId());
					examMarks.setCourse(userExamMarks.getCourse());
					examMarks.setSubject(allSubjects[subject]);
					examMarks.setExam(userExamMarks.getExam());
					examMarks.setDesc(term);
					
					examMarks.setTheory(Integer.parseInt(request.getParameter(allSubjects[subject]+"theory")));
					examMarks.setTheoryPass(Integer.parseInt(request.getParameter(allSubjects[subject]+"theoryPass")));
					examMarks.setPractical(Integer.parseInt(request.getParameter(allSubjects[subject]+"practical")));
					examMarks.setPracticalPass(Integer.parseInt(request.getParameter(allSubjects[subject]+"practicalPass")));
					examMarks.setTotal(Integer.parseInt(request.getParameter(allSubjects[subject]+"total")));
					examMarks.setPass(Integer.parseInt(request.getParameter(allSubjects[subject]+"pass")));
					
					examMarksList.add(examMarks);
				}
			}
			String updateStatus = null;
			if(null!=examMarksList && examMarksList.size()!=0){
				 updateStatus=academicsService.editUserExamMarks(examMarksList);
				//model.addAttribute("insertUpdateStatus", updateStatus);
			}


			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Marks Allotted to Subject Successfully";
			}else{
				msg = "Fail to allot marks ";
			}
			model.addAttribute("insertUpdateStatus", updateStatus);
			model.addAttribute("msg",msg);

		} catch (CustomException ce){
			logger.error("Exception in method editUserExamMarks-POST of AcademicsController", ce);
		}
		return createUserdefinedExamsMarks(request, response, model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * @author vinod.singh
	 * Opens page to Upload Result for User Defined Exam
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getUserExamUploadResult", method = RequestMethod.GET)
	public String getUserExamUploadResult(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = "academics/createUserExamResult";
		try {
			logger.info("Inside Method getUserExamUploadResult-GET of AcademicsController");
			
			List<Standard> standardList=commonService.getStandards();
			model.addAttribute("standardList", standardList);
			List<Course>courseList = academicsService.getCourseList();
			model.addAttribute("courseList", courseList);
			model.addAttribute("loggedInUser", sessionObject.getUserId());
			
		} catch (CustomException ce){
			logger.error("Exception in method getUserExamUploadResult-GET of AcademicsController", ce);
		}
		return strView;
	}
	
	/* 
	 * Gets Students Subjects And Marks
	 */
	
	@RequestMapping(value = "/getStudentsAndMarksForUserDefinedExams", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentsAndMarksForUserDefinedExams(	@RequestParam("exam") String exam,
													@RequestParam("subject") String subject,
													@RequestParam("section") String section,
													@RequestParam("course") String course) {
		logger.info("Inside Method getStudentsAndMarksForUserDefinedExams-GET of AcademicsController");
		String subjectAndMarks = "";
		try {
			StudentResult studentResult=new StudentResult();
			//studentResult.setStandard(standard);
			studentResult.setStandard(course);
			studentResult.setExam(exam);
			studentResult.setSubject(subject);
			studentResult.setSection(section);
			subjectAndMarks = academicsService.getStudentsAndMarksForUserDefinedExams(studentResult);
		} catch (CustomException ce) {
			ce.printStackTrace();
			logger.error("Exception in method getStudentsAndMarksForUserDefinedExams-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(subjectAndMarks));
	}
	
	
	@RequestMapping(value = "/editStudentResultForUserExam", method = RequestMethod.POST)
	public String editStudentResultForUserExam(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="section") String section,
			@RequestParam(value="subject") String subject,
			@RequestParam(value="exam") String exam,
			@RequestParam(value="course") String course,
			@RequestParam(value="rollNumber") String []rollNumber,
			@RequestParam(value="term") String term,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editStudentResultForUserExam-POST of AcademicsController");
			List<StudentResult> studentResultList=new ArrayList<StudentResult>();
			for(int loopControl=0;loopControl<rollNumber.length;loopControl++){
				StudentResult studentResult=new StudentResult();
				studentResult.setUpdatedBy(sessionObject.getUserId());
				studentResult.setSubject(subject);
				//studentResult.setStandard(standard);
				studentResult.setSection(section);
				studentResult.setCourse(course);
				studentResult.setRollNumber(rollNumber[loopControl]);
				studentResult.setExam(exam);
				studentResult.setTerm(term);
				studentResult.setTheory(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"theoryTotal")));
				studentResult.setTheoryPass(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"theoryPass").trim()));
				String theoryObtained=request.getParameter(rollNumber[loopControl]+"theory");
				if(!theoryObtained.equalsIgnoreCase("AB"))
					studentResult.setTheoryObtained(Integer.parseInt(theoryObtained.trim()));
				else
					studentResult.setTheoryObtained(-1);
				
				studentResult.setPractical(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"practicalTotal")));
				studentResult.setPracticalPass(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"practicalPass").trim()));
				String practicalbtained=request.getParameter(rollNumber[loopControl]+"practical");
				if(!practicalbtained.equalsIgnoreCase("AB"))
					studentResult.setPracticalObtained(Integer.parseInt(practicalbtained.trim()));
				else
					studentResult.setPracticalObtained(-1);
				
				studentResult.setTotal(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"total")));
				studentResult.setPass(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"totalPass")));
				String totalbtained=request.getParameter(rollNumber[loopControl]+"obtained");
				if(!totalbtained.equalsIgnoreCase("AB"))
					studentResult.setTotalObtained(Integer.parseInt(totalbtained.trim()));
				else
					studentResult.setTotalObtained(-1);
				
				studentResult.setPassFail(request.getParameter(rollNumber[loopControl]+"passfail"));
				
				studentResultList.add(studentResult);
			}
			if(studentResultList.size()!=0){
				String updateStatus=academicsService.editStudentResultForUserExam(studentResultList);
				model.addAttribute("insertUpdateStatus", updateStatus);
				String msg = null;
				if(updateStatus.equalsIgnoreCase("success")){
					msg = "Student Result Uploaded Successfully";
				}else{
					msg = "Failed to upload result";
				}
				//model.addAttribute("insertUpdateStatus", updateStatus);
				//System.out.println("msg======"+msg);
				model.addAttribute("msg",msg);
				
			}
		} catch (CustomException ce){
			logger.error("Exception in method editStudentResultForUserExam-POST of AcademicsController", ce);
		}
		return getUserExamUploadResult(request, response, model, sessionObject);
	}
	
	
	@RequestMapping(value = "/viewUploadQuestionPapers", method = RequestMethod.GET)
	public String viewUploadQuestionPapers(HttpServletRequest request,
									HttpServletResponse response, ModelMap model) {
		try {
			logger.info("Inside viewUploadQuestionPapers() of AcademicsController");			
			List<AcademicYear> academicYearList = commonService.getPreviousAndCurrentAcademicYear();
			if(null != academicYearList && academicYearList.size() != 0){
				model.addAttribute("academicYearList", academicYearList);
			}
			List<Standard> standardList = commonService.getStandards();
			if(null != standardList && standardList.size() != 0){
				model.addAttribute("standardList", standardList);
			}
			List<Course> courseList = academicsService.getCourseList();
			model.addAttribute("courseList", courseList);
			//List<Course> courseList1 = academicsService.getcourse?
			
		} catch (CustomException ce){
			logger.error("Exception in viewUploadQuestionPapers() of AcademicsController", ce);
		}
		return "academics/uploadQuestionPaper";
	}
	
	@RequestMapping(value = "/getSubjectsForACourseAndTeacher", method = RequestMethod.GET)
	public @ResponseBody
	String getSubjectsForACourseAndTeacher(@RequestParam("course") String course,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("Inside Method getStudentsForprogrammeAndBatch-GET of VenueController");
		String subjects = "";
		try {
			Course courseObj = new Course();
			courseObj.setCourseCode(course);
			courseObj.setUpdatedBy(sessionObject.getUserId());
			List<Subject>subjectList = academicsService.getSubjectsForACourseAndTeacher(courseObj);
			for(Subject subject:subjectList){
				subjects = subjects+"*~*"+subject.getSubjectCode()+"#@#"+subject.getSubjectName();
			}
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in method getFacilityListAgainstVenue-GET of VenueController", ce);
		}	
		
		//System.out.println("subjects====="+subjects);
		return (new Gson().toJson(subjects));
	}
		
	@RequestMapping(value = "/getSubjectsForStandardQP", method = RequestMethod.GET)
	public @ResponseBody
	String getSubjectsForStandardQP(@RequestParam("standard") String standard) {
		logger.info("Inside getSubjectsForStandardQP() of AcademicsController");
		String allSubjects = "";
		try {
			allSubjects = academicsService.getSubjectsForCourse(standard);
		} catch (CustomException ce) {
			logger.error("Exception in getSubjectsForStandardQP() of AcademicsController", ce);
		}		
		return (new Gson().toJson(allSubjects));
	}
	
	
	@RequestMapping(value = "/uploadQuestionPaper", method = RequestMethod.POST)
	public String uploadQuestionPaper(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			StudentResult studentResult,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			/*logger.info("Inside uploadQuestionPaper() of AcademicsController");
			if(null != studentResult){
				Attachment attachment = new Attachment();
				attachment.setStorageRootPath(rootPath);
				attachment.setFolderName(uploadQuestionPapersPath);				
				if(null != studentResult.getUploadFile()){
					studentResult.getUploadFile().setAttachment(attachment);
				}				
				studentResult.setUpdatedBy(sessionObject.getUserId());
				
				String insertStatus = academicsService.uploadQuestionPaper(studentResult);
				System.out.println("insertStatus=="+insertStatus);
				String msg = null;
				if(null != insertStatus){
					model.addAttribute("insertUpdateStatus", "success");		
					model.addAttribute("msg", "Question Papers Successfully Uploaded");					
				}else{
					model.addAttribute("insertUpdateStatus", "fail");		
					model.addAttribute("msg", "Could not Upload Question Papers.");	
				}
			}else{
				logger.info("Proper data not Found.");
			}*/
			/**new code for save in external repository*/
			logger.info("Inside uploadQuestionPaper() of AcademicsController");
			if(null != studentResult){
				/**to get the path of repository and check its existance*/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				File directory = new File(repository);
				boolean isExists = directory.exists();
				String insertStatus = null;
				if(isExists == true){
					Attachment attachment = new Attachment();
					attachment.setStorageRootPath(repository);
					attachment.setFolderName(uploadQuestionPapersPath);				
					if(null != studentResult.getUploadFile()){
						studentResult.getUploadFile().setAttachment(attachment);
					}
					studentResult.setUpdatedBy(sessionObject.getUserId());
					insertStatus = academicsService.uploadQuestionPaper(studentResult);
					//System.out.println("insertStatus=="+insertStatus);
				}else{
					System.out.println("directory not found");
				}				
				if(null != insertStatus){
					model.addAttribute("insertUpdateStatus", "success");		
					model.addAttribute("msg", "Question Papers Successfully Uploaded");					
				}else{
					model.addAttribute("insertUpdateStatus", "fail");		
					model.addAttribute("msg", "Could not Upload Question Papers.");	
				}
			}else{
				logger.info("Proper data not Found.");
			}
		} catch (CustomException ce){
			ce.printStackTrace();
			logger.error("Exception in uploadQuestionPaper() of AcademicsController", ce);
		}
		return viewUploadQuestionPapers(request, response, model);
	}
	
	
	@RequestMapping(value = "/viewDownloadQuestionPapers", method = RequestMethod.GET)
	public String viewDownloadQuestionPapers(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										@RequestParam("folderParam") String folderParam,
										@RequestParam("fileParam") String fileParam) {
		try {
			logger.info("Inside viewDownloadQuestionPapers() of AcademicsController");
			List<String> academicYearList = null;
			/*String fullPath = rootPath+uploadQuestionPapersPath;*/
			/**new code for download from external repository*/
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			String fullPath = repository+"/"+uploadQuestionPapersPath;
			
			//System.out.println("full path for download::"+fullPath);
			//System.out.println("folderParam====="+folderParam);
			//System.out.println("fileParam====="+fileParam);
			if(!folderParam.equals("AcademicYear")){
				fullPath = fullPath + folderParam + "/";
				//System.out.println("@@ fullPath : "+fullPath);
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
				if(null != academicYearList && academicYearList.size() != 0){
					model.addAttribute("academicYearList", academicYearList);
				}
				List<Course>courseList = academicsService.getCourseList();
				model.addAttribute("courseList", courseList);
			}
		} catch (Exception ce){
			logger.error("Exception in viewDownloadQuestionPapers() of AcademicsController", ce);
		}
		return "academics/downloadQuestionPapers";
	}
	
	
	@RequestMapping(value = "/getQuestionFolderNames", method = RequestMethod.GET)
	public @ResponseBody
	String getQuestionFolderNames(@RequestParam("paperDirName") String paperDirName) {
		logger.info("Inside getQuestionFolderNames() of AcademicsController");
		String allDirs = "";
		try {
			/*String fullPath = rootPath+uploadQuestionPapersPath;*/
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			String fullPath = repository+"/"+uploadQuestionPapersPath;
			
			fullPath = fullPath+paperDirName+"/";
			File file = new File(fullPath);
			File[] listOfFiles = file.listFiles();
			if(null != listOfFiles && listOfFiles.length != 0){
				for(int i= 0; i<listOfFiles.length; i++){
					if(listOfFiles[i].isDirectory() || listOfFiles[i].isFile()){
						allDirs = allDirs + listOfFiles[i].getName() + "~";	
					}
				}				
			}
			//System.out.println(allDirs);
		} catch (Exception ce) {
			logger.error("Exception in getQuestionFolderNames() of AcademicsController", ce);
		}		
		return (new Gson().toJson(allDirs));
	}
	
	

	/*
		 * @author vinod.singh
		 * Opens page to Upload Result By Excel
		 * Returns String
		 * 
		 */	
		@RequestMapping(value = "/getDownloadExelForCBSEExams", method = RequestMethod.GET)
		public String getDownloadExelForCBSEExams(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String strView = "academics/downloadExelForCBSEExams";
			try {
				logger.info("Inside Method getDownloadExelForCBSEExams-GET of AcademicsController");
				
				List<Standard> standardList = commonService.getStandards();
				model.addAttribute("standardList", standardList);
				model.addAttribute("loggedInUser", sessionObject.getUserId());
				
			} catch (CustomException ce){
				logger.error("Exception in method getDownloadExelForCBSEExams-GET of AcademicsController", ce);
			}
			return strView;
		}
		
		
		
		@RequestMapping(value = "/downloadExelForCBSEExams", method = RequestMethod.POST)
		public String downloadExelForCBSEExams(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				StudentResult studentResult,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("Inside Method downloadExelForCBSEExams-POST of AcademicsController");			
				String fileName=studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getSubject()+"_"+studentResult.getExam()+".xls";
						
				String status=academicsService.downloadExelForCBSEExams(studentResult, rootPath+marksDownloadFolder, fileName, studentResult.getUpdatedBy());
				if(status!=null && status!=""){
					FileUploadDownload fileUploadDownload = new FileUploadDownload();
					String insertUpdateStatus = fileUploadDownload.downloadFile(response, rootPath+marksDownloadFolder, fileName);
					model.addAttribute("insertUpdateStatus", insertUpdateStatus);
				}else{
					 model.addAttribute("insertUpdateStatus", "Result Not Available");
				}
				
			} catch (Exception ce){
				logger.error("Exception in method downloadExelForCBSEExams-POST of AcademicsController", ce);
			}
			return getDownloadExelForCBSEExams(request, response, model, sessionObject);
		}
		
		
		/*
		 * @author vinod.singh
		 * Opens page to Upload Result By Exel
		 * Returns String
		 * 
		 */	
		@RequestMapping(value = "/getDownloadExelForUserDefinedExams", method = RequestMethod.GET)
		public String getDownloadExelForUserDefinedExams(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String strView = "academics/downloadExelForUserDefinedExam";
			try {
				logger.info("Inside Method getDownloadExelForUserDefinedExams-GET of AcademicsController");
				
				List<Standard> standardList=commonService.getStandards();
				model.addAttribute("standardList", standardList);
				
				model.addAttribute("loggedInUser", sessionObject.getUserId());
				
			} catch (CustomException ce){
				logger.error("Exception in method getDownloadExelForUserDefinedExams-GET of AcademicsController", ce);
			}
			return strView;
		}
		
		@RequestMapping(value = "/downloadExelForUserExam", method = RequestMethod.POST)
		public String downloadExelForUserExam(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				StudentResult studentResult,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("Inside Method downloadExelForUserExam-POST of AcademicsController");			
				String fileName=studentResult.getStandard()+"_"+studentResult.getSection()+"_"+studentResult.getSubject()+"_"+studentResult.getExam()+".xls";
						
				String status=academicsService.downloadExelForUserExam(studentResult, rootPath+marksDownloadFolder, fileName, studentResult.getUpdatedBy());
				if(status!=null && status!=""){
					FileUploadDownload fileUploadDownload = new FileUploadDownload();
					//System.out.println(rootPath+marksDownloadFolder);
					//System.out.println(fileName);
					String returnMessage = fileUploadDownload.downloadFile(response, rootPath+marksDownloadFolder, fileName);
					model.addAttribute("insertUpdateStatus", returnMessage);
				}else{
					 model.addAttribute("insertUpdateStatus", "Result Not Available");					
				}
				
			} catch (Exception ce){
				logger.error("Exception in method downloadExelForUserExam-POST of AcademicsController", ce);
			}
			return getDownloadExelForUserDefinedExams(request, response, model, sessionObject);
		}
		
		
		
		@RequestMapping(value = "/uploadExelForCBSEExam", method = RequestMethod.POST)
		public String uploadExelForCBSEExam(@ModelAttribute("uploadFile") UploadFile uploadFile,
											HttpServletRequest request,HttpServletResponse response,
											ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject){
			try{
				logger.info("In uploadExelForCBSEExam() of BackOfficeController");
				String fileName=String.valueOf(System.currentTimeMillis())+".xls";
				String returnMessage = fileUploadDownload.uploadExcel(uploadFile,rootPath+marksUploadFolder,fileName);
				if(returnMessage == null){
					model.addAttribute("insertUpdateStatus", fileName+" upload failed.");
				}else{
					int excelDataInsertStatus = academicsService.uploadExelForCBSEExamByExel(rootPath+marksUploadFolder+fileName, sessionObject.getUserId());
					model.addAttribute("excelDataInsertStatus", excelDataInsertStatus);
				}
			}catch(Exception e){
				logger.error("Exception in uploadExelForCBSEExam() to upload Excel IN BackOfficeController", e);
			}
			return getDownloadExelForCBSEExams(request, response, model, sessionObject);
		}
		
		
		@RequestMapping(value = "/uploadExelForUserExam", method = RequestMethod.POST)
		public String uploadExelForUserExam(@ModelAttribute("uploadFile") UploadFile uploadFile,
											HttpServletRequest request,HttpServletResponse response,
											ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject){
			try{
				logger.info("In uploadExelForUserExam() of BackOfficeController");
				String fileName=String.valueOf(System.currentTimeMillis())+".xls";
				String returnMessage = fileUploadDownload.uploadExcel(uploadFile,rootPath+marksUploadFolder,fileName);
				if(returnMessage == null){
					model.addAttribute("insertUpdateStatus", fileName+" upload failed.");
				}else{
					int excelDataInsertStatus = academicsService.uploadExelForUserExamByExel(rootPath+marksUploadFolder+fileName, sessionObject.getUserId());
					model.addAttribute("insertUpdateStatus", excelDataInsertStatus);
				}
			}catch(Exception e){
				logger.error("Exception in uploadExelForUserExam() to upload Excel IN BackOfficeController", e);
			}
			return getDownloadExelForCBSEExams(request, response, model, sessionObject);
		}
		
		
		/**
		 * this method is used to open New Asset Details page
		 * 
		 * @param request
		 * @param model
		 * @return String
		 */
		@RequestMapping(value = "/createAcademicsAssetDetails", method = RequestMethod.GET)
		public String newAssetDetails(HttpServletRequest request, ModelMap model) {
			try {
				List<AssetType> assetTypeList = commonService.getAllAssetType();
				if (null != assetTypeList) {
					model.addAttribute("assetTypeList", assetTypeList);
				}
				List<Department> departmentList = academicsService.getAllDepartment();
				if (null != departmentList && departmentList.size() != 0) {			
					model.addAttribute("departmentList", departmentList);
				}
			} catch (Exception e) {
				logger.error("In newAssetDetails() of AcademicsController : " + e);
			}
			return "academics/addAssetDetails";
		}
		
		
		/**
		 * 	This method is used to create New Asset Details
		 * @param request
		 * @param model
		 * @param sessionObject
		 * @param asset
		 */	
		@RequestMapping(value = "/addAcademicsAssetDetails", method = RequestMethod.POST)
		public String submitAssetDetails(HttpServletRequest request, ModelMap model, 
										@ModelAttribute("sessionObject") SessionObject sessionObject,
										Asset asset) {
			try {
				
				asset.setUpdatedBy(sessionObject.getUserId());
				int insertStatus = academicsService.addNewAssetDetails(asset);
				if (insertStatus != 0) {
					model.addAttribute("successStatus", "Successfully updated");
				} else {
					model.addAttribute("failStatus", "Update failed");
				}
			} catch (Exception e) {
				logger.error("Exception In submitAssetDetails() method of AcademicsController : ",e);
			}
			return newAssetDetails(request, model);
		}
		
		
		/**
		 * @param assetName
		 * @return
		 */
		@RequestMapping(value = "/academicsAssetNameValidation", method = RequestMethod.GET)
		public @ResponseBody
		String assetNameValidation(@RequestParam("assetName") String assetName) {
			String asset = "";
			try {
				asset = academicsService.assetNameValidation(assetName);
			} catch (Exception e) {
				logger.error("Exception In assetNameValidation() method of AcademicsController : ",e);
			}
			return (new Gson().toJson(asset));
		}
		
		
		/**
		 * this method is used to View list of all Assets
		 * 
		 * @param request
		 * @param model
		 * @return String
		 */
		@RequestMapping(value = "/viewAcademicsAssetList", method = RequestMethod.GET)
		public String viewAssetList(HttpServletRequest request, ModelMap model) {
			try {
				academicsService.getAssetList();
			} catch (Exception e) {
				logger.error("In viewAssetList() of AcademicsController : " + e);
			}
			return assetListPagination(request, model);
		}
		
		
		/**
		 * this method is used for pagination of Asset list 
		 * 
		 * @param request
		 * @param model
		 */
		@RequestMapping(value = "/academicsAssetListPagination", method = RequestMethod.GET)
		public String assetListPagination(HttpServletRequest request,ModelMap model) {
			try {
				PagedListHolder<Asset> assetPagedListHolder = academicsService.getAssetListPaging(request);	
				if (assetPagedListHolder != null && assetPagedListHolder.getNrOfElements()!=0) {
					model.addAttribute("first", assetPagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", assetPagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", assetPagedListHolder.getNrOfElements());
					model.addAttribute("assetPagedListHolder", assetPagedListHolder);
				}
			}catch(Exception e){
				logger.error("Exception in assetListPagination() in AcademicsController : ", e);
			}
			return "academics/assetList";
		}
		
		
		@RequestMapping(value = "/searchAcademicsAsset", method = RequestMethod.POST)
		public String searchAsset(HttpServletRequest request, ModelMap model) {
			try {
				logger.info("In searchAsset() method of AcademicsController");
				String strKey = request.getParameter("query");
				String strValue = request.getParameter("data");
				Map<String, Object> parameters = new HashMap<String, Object>();			
								
				if (strKey.trim().equalsIgnoreCase("AssetName")) {
					parameters.put("AssetName", strValue.trim());
				}
				if (strKey.trim().equalsIgnoreCase("Department")) {
					parameters.put("Department", strValue.trim());
				}
				if (strKey.trim().equalsIgnoreCase("PurchaseDate")) {
					parameters.put("PurchaseDate", strValue.trim());
				}
				if (strKey.trim().equalsIgnoreCase("AssetType")) {
					parameters.put("AssetType", strValue.trim());
				}
				if (strKey.trim().equalsIgnoreCase("AssetSubType")) {
					parameters.put("AssetSubType", strValue.trim());
				}
				academicsService.getSearchAssetList(parameters);
			} catch (Exception e) {
				logger.error("Exception in searchAsset() method Of AcademicsController ", e);
				
			}
			return assetListPagination(request, model);
		}
		
		
		/**
		 * this method is used to view Asset details in Edit page 
		 * 
		 * @param request
		 * @param model
		 * @param assetId
		 */
		@RequestMapping(value = "/getAcademicsAssetDetails", method = RequestMethod.GET)
		public String getAssetDetails(HttpServletRequest request, ModelMap model, @RequestParam("assetID") String assetId) {
			try {
				Asset asset = academicsService.getAssetDetails(assetId);
				if(null != asset){
					model.addAttribute("asset", asset);
				}
				
				List<Department> departmentList = academicsService.getAllDepartment();
				if (departmentList != null) {			
					model.addAttribute("departmentList", departmentList);
				}
				
				List<AssetType> assetTypeList = commonService.getAllAssetType();
				if (null != assetTypeList) {
					model.addAttribute("assetTypeList", assetTypeList);
				}
				
				List<AssetSubType> assetSubTypeList = commonService.getAllAssetSubTypeForAssetType(asset.getAssetCode());
				if (null != assetSubTypeList) {
					model.addAttribute("assetSubTypeList", assetSubTypeList);
				}
			}catch(Exception e){
				logger.error("Exception in getAssetDetails() in AcademicsController : ", e);
			}
			return "academics/editAssetDetails";
		}
		
		
		/**
		 * 	This method is used to update Asset Details
		 * @param request
		 * @param model
		 * @param sessionObject
		 * @param asset
		 */	
		@RequestMapping(value = "/updateAcademicsAssetDetails", method = RequestMethod.POST)
		public String updateAssetDetails(HttpServletRequest request, ModelMap model, 
										@ModelAttribute("sessionObject") SessionObject sessionObject,
										Asset asset) {
			String assetName = null;
			try {
				if(null != asset){
					assetName = academicsService.assetNameValidation(asset.getAssetName());
					if(null == assetName){
						asset.setUpdatedBy(sessionObject.getUserId());
						int updateStatus = academicsService.updateAssetDetails(asset);
						if (updateStatus != 0) {
							model.addAttribute("successStatus", "Successfully updated");
						} else {
							model.addAttribute("failStatus", "Update failed");
						}
					} else {
						model.addAttribute("sameAssetName", "Exist");
					}
				}
			} catch (Exception e) {
				logger.error("Exception In submitAssetDetails() method of AcademicsController : Exception",e);
			}
			return viewAssetList(request, model);
		}
		
		
		@RequestMapping(value = "/downloadAcademicsAssetDetailsExcel", method = RequestMethod.GET)
		public String downloadAssetDetailsExcel(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
			try{
				logger.info("In downloadAssetDetailsExcel() of AcademicsController");
				String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelDownloadFolder, assetDetailsExcel);
				if(returnMessage == null){
					model.addAttribute("ResultError", "File not available");
				}
			}catch(Exception e){
				logger.error("Exception in downloadAssetDetailsExcel() of AcademicsController", e);
			}
			return newAssetDetails(request, model);
		}
		
		
		@RequestMapping(value = "/uploadAcademicsAssetDetailsExcel", method = RequestMethod.POST)
		public String uploadAssetDetailsExcel(@ModelAttribute("uploadFile") UploadFile uploadFile,
												HttpServletRequest request,HttpServletResponse response,
												ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject){
			try{
				logger.info("In uploadAssetDetailsExcel() of AcademicsController");
				String returnMessage = fileUploadDownload.uploadExcel(uploadFile,rootPath+excelUploadfolder,assetDetailsExcel);
				if(returnMessage == null){
					model.addAttribute("uploadErrorMessage", assetDetailsExcel+" upload failed.");
				}else{
					int excelDataInsertStatus = commonService.insertAssetDetailsExcelBulkData(rootPath+excelUploadfolder+assetDetailsExcel, sessionObject.getUserId());
					if(excelDataInsertStatus != 0){
						model.addAttribute("excelDataInsertStatus", "Asset Excel Uploaded Successfully.");						
					}else{
						model.addAttribute("excelDataInsertStatus", "Problem occurred while uploading "+assetDetailsExcel+" excel.");						
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception in uploadAssetDetailsExcel() IN AcademicsController", e);
			}
			return newAssetDetails(request, model);
		}
		
		
		@RequestMapping(value = "/getResetResult", method = RequestMethod.GET)
		public String getResetResult(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String strView = "academics/resetResult";
			try {
				logger.info("Inside Method getUploadResult-GET of AcademicsController");				
				List<Standard> standardList=commonService.getStandards();
				model.addAttribute("standardList", standardList);	
				List<Course>courseList = academicsService.getCourseList();
				model.addAttribute("courseList", courseList);
				
			} catch (CustomException ce){
				logger.error("Exception in method getUploadResult-GET of AcademicsController", ce);
			}
			return strView;
		}		
		
		
		@RequestMapping(value = "/resetStudentResult", method = RequestMethod.POST)
		public String resetStudentResult(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				StudentResult studentResult,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("Inside Method getUploadResult-GET of AcademicsController");
				String status=academicsService.resetStudentResult(studentResult);
				model.addAttribute("insertUpdateStatus", status);
				String msg = null;
				if( status.equalsIgnoreCase("success")){
					msg = "Reset Result SuccessFully";
				}else{
					msg = "Failed To Reset Result";
				}
				model.addAttribute("msg", msg);
			} catch (CustomException ce){
				logger.error("Exception in method getUploadResult-GET of AcademicsController", ce);
			}
			return getResetResult(request, response, model, sessionObject);
		}
		
		
		@RequestMapping(value = "/getExamForStandard", method = RequestMethod.GET)
		public @ResponseBody
		String getExamForStandard(@RequestParam("standard") String standard) {
			String exam = "";
			try {
				exam = academicsService.getExamForStandard(standard);
			} catch (Exception e) {
				logger.error("Exception In getExamForStandard() method of AcademicsController : ",e);
			}
			return (new Gson().toJson(exam));
		}
				
		
		@RequestMapping(value = "/viewAcademicsAssetConsumption", method = RequestMethod.GET)
		public String viewAcademicsAssetConsumption(HttpServletRequest request, HttpServletResponse response, ModelMap model,
													@RequestParam("assetID") String assetId,
													@RequestParam("assetName") String assetName,
													@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In viewAcademicsAssetConsumption() of AcademicsController");
				if(null != assetId && null != assetName){
					AssetConsumption assetConsumption = academicsService.getAssetCurrentQuantity(Integer.parseInt(assetId));
					if(null != assetConsumption){
						if(!(assetConsumption.getConsumedQuantity() < 0)){
							model.addAttribute("currentQuantity", assetConsumption.getConsumedQuantity());						
						}
						model.addAttribute("assetUnit", assetConsumption.getUnit());						
					}
					model.addAttribute("assetId", assetId);
					model.addAttribute("assetName", assetName);		
				}								
			} catch (Exception ce){
				logger.error("Exception in viewAcademicsAssetConsumption() of AcademicsController : ", ce);
			}
			return "academics/labAssetConsumption";
		}
		
		
		@RequestMapping(value = "/submitAssetConsumption", method = RequestMethod.POST)
		public String submitAssetConsumption(HttpServletRequest request, HttpServletResponse response, ModelMap model,
											AssetConsumption assetConsumption,
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("Inside submitAssetConsumption() of AcademicsController");
				assetConsumption.setUpdatedBy(sessionObject.getUserId());
				String status = academicsService.submitAssetConsumption(assetConsumption);
				if(null != status){
					model.addAttribute("insertStatus", status);
				}
			} catch (CustomException ce){
				logger.error("Exception In submitAssetConsumption() of AcademicsController : ", ce);
			}
			return viewAssetList(request, model);
		}
				
		
		@RequestMapping(value = "/viewAcademicsAssetConsumptionList", method = RequestMethod.GET)
		public String viewAcademicsAssetConsumptionList(HttpServletRequest request, HttpServletResponse response, ModelMap model,
													@RequestParam("assetID") String assetId,
													@RequestParam("assetName") String assetName,
													@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("In viewAcademicsAssetConsumptionList() of AcademicsController");
				if(null != assetId && null != assetName){
					model.addAttribute("assetId", assetId);
					model.addAttribute("assetName", assetName);		
				}								
			} catch (Exception ce){
				logger.error("Exception in viewAcademicsAssetConsumptionList() of AcademicsController : ", ce);
			}
			return "academics/labAssetConsumptionList";
		}
		
		
		@RequestMapping(value = "/getAssetConsumptionList", method = RequestMethod.POST)
		public String getAssetConsumptionList(HttpServletRequest request, ModelMap model, AssetConsumption assetConsumption) {
			try {
				logger.info("In getAssetConsumptionList() of AcademicsController");
				academicsService.getAssetConsumptionList(assetConsumption);
			} catch (Exception e) {
				logger.error("Exception In getAssetConsumptionList() of AcademicsController : " + e);
			}
			return academicsAssetConsumptionListPagination(request, model, assetConsumption.getAsset().getAssetName(), String.valueOf(assetConsumption.getAsset().getAssetId()));
		}
		
		
		/**
		 * this method is used for pagination of Asset Consumption list 
		 * 
		 * @param request
		 * @param model
		 */
		@RequestMapping(value = "/academicsAssetConsumptionListPagination", method = RequestMethod.GET)
		public String academicsAssetConsumptionListPagination(HttpServletRequest request,ModelMap model,
															@RequestParam("assetName") String assetName,
															@RequestParam("assetID") String assetId) {
			try {
				logger.info("In academicsAssetConsumptionListPagination() of AcademicsController");
				PagedListHolder<AssetConsumption> assetConsumptionPagedListHolder = academicsService.getAssetConsumptionListPaging(request);	
				if (assetConsumptionPagedListHolder != null && assetConsumptionPagedListHolder.getNrOfElements()!=0) {
					model.addAttribute("first", assetConsumptionPagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", assetConsumptionPagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", assetConsumptionPagedListHolder.getNrOfElements());
					model.addAttribute("assetId", assetId);
					model.addAttribute("assetName", assetName);			
					model.addAttribute("showConsumptionList", "showConsumptionList");
					model.addAttribute("assetConsumptionPagedListHolder", assetConsumptionPagedListHolder);
				}
			}catch(Exception e){
				logger.error("Exception in academicsAssetConsumptionListPagination() in AcademicsController : ", e);
			}
			return "academics/labAssetConsumptionList";
		}
		
		
		
		
		
		
		
		@RequestMapping(value = "/getCourseType", method = RequestMethod.GET)
		public String getCourseType(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			String strView = "academics/createCourseType";
			
			try {
				logger.info("Inside Method getHostel-GET of HostelController");
				List<CourseType> courseTypeList=academicsService.getCourseTypeList();
				model.addAttribute("courseTypeList", courseTypeList);
			} catch (CustomException ce){
				ce.printStackTrace();
				logger.error("Exception in method getHostel-GET of HostelController", ce);
			}
			return strView;
		}
		
		/**
		 * modified by kaustav.sen
		 * changes taken on 12042017**/
		
		@RequestMapping(value = "/createCourseType", method = RequestMethod.POST)
		public String createCourseType(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				CourseType courseType,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				courseType.setCourseTypeName(courseType.getCourseTypeName().trim().toUpperCase());
				courseType.setCourseTypeCode(courseType.getCourseTypeName());
				courseType.setCourseTypeDesc(courseType.getCourseTypeDesc().trim());
				courseType.setUpdatedBy(sessionObject.getUserId());
				String status = academicsService.createCourseType(courseType);
				String msg = null;
				if(status.equalsIgnoreCase("success")){
					msg = "Programme Type Created SuccessFully";
				}else{
					msg = "Failed To Update Programme Type";
				}
				model.addAttribute("msg", msg);
				model.addAttribute("insertUpdateStatus", status);
				/**Added by @author Saif.Ali
				 * Date-30-03-2018*/
				if(status.equalsIgnoreCase("success")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("ENTER COURSE TYPE DETAILS");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor(courseType.getCourseTypeName().trim().toUpperCase());
					updateLog.setDescription("Course Type :: " + courseType.getCourseTypeName().trim().toUpperCase() + " with Course Type Description :: " + courseType.getCourseTypeDesc() + " is added" +" ; ");
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 2739 :: AcademicsController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
			} catch (CustomException ce){
				ce.printStackTrace();
				logger.error("Exception in method getUploadResult-GET of AcademicsController", ce);
			}
			return getCourseType(request, response, model);
		}
		
		/**
		 * modified by saurav.bhadra 
		 * changes taken on 28032017*/
		
		@RequestMapping(value = "/editCourseType", method = RequestMethod.POST)
		public String editCourseType(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				//System.out.println("editCourseType() reached !!");
				CourseType courseType = new CourseType();
				String saveId=request.getParameter("saveId");
				String courseTypeCode = request.getParameter("oldCourseTypeCode"+saveId);
				String courseTypeName = request.getParameter("newCourseTypeName").trim().toUpperCase();
				String courseTypeDescription = request.getParameter("newCourseTypeDesc").trim();
				//System.out.println("RowID :: "+saveId+"\tUpdated Course Type Name :: "+courseTypeName+"\tUpdated Course Type Description :: "+courseTypeDescription+"\tOld Course Type Code :: "+courseTypeCode);
				
				
				courseType.setCourseTypeName(courseTypeName);
				courseType.setCourseTypeCode(courseTypeCode);
				courseType.setCourseTypeDesc(courseTypeDescription);
				courseType.setUpdatedBy(sessionObject.getUserId());
				
				String updateStatus=academicsService.editCourseType(courseType);
				String msg = null;
				if(updateStatus.equalsIgnoreCase("success")){
					msg = "Programme Type Updated SuccessFully";
				}else{
					msg = "Failed To Update Programme Type";
				}
				model.addAttribute("msg", msg);
				model.addAttribute("insertUpdateStatus", updateStatus);
				
				/**Added by @author Saif.Ali
				 * Date-30-03-2018*/
				String oldCourseType = request.getParameter("oldCourseTypeCode"+saveId);
				String oldDesc = request.getParameter("oldDesc"+saveId);
				String description = "";
				if(!(oldCourseType.equalsIgnoreCase(courseTypeName))){
					description = description + ("Course Type :: " + oldCourseType + " is now updated to new Course Type :: " + courseTypeName + " ; ");
				}
				if(!(oldDesc.equalsIgnoreCase(courseTypeDescription))){
					description = description + ("Course Description :: " + oldDesc + " is now updated to new Course Description :: " + courseTypeDescription+ " ; ");
				}
				
				if(updateStatus.equalsIgnoreCase("success")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("UPDATE COURSE TYPE DETAILS");
					updateLog.setInsertUpdate("UPDATE");
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor("course Type Name :: " + courseTypeName);
					updateLog.setDescription(description);
					String response_update=commonService.insertIntoActivityLog(updateLog);
					
					System.out.println("LN 2810 :: AcademicsController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
				}
				/***/
				
			} catch (CustomException ce){
				ce.printStackTrace();
				logger.error("Exception in method editStandard-POST of HostelController", ce);
			}
			return getCourseType(request, response, model);
		}
		
		@RequestMapping(value="/getCourse",method=RequestMethod.GET)
		public ModelAndView createCourse(HttpServletRequest request, 
										HttpServletResponse response,
										ModelMap model){
			String strView = "academics/createCourse";
			//List<Course> courseTypeList = null;
			try{
				logger.info("createCourse() method in AcademicsController: ");
				//AcademicYear academicYear=commonService.getClassListForCreateNewCourse();
				List<AdmissionForm> courseList=academicsService.getAllCourseList();
				model.addAttribute("courseList", courseList);
				List<Standard> standardList = commonService.getStandards();
				model.addAttribute("standardList", standardList);
				//courseTypeList= academicsService.getCourseTypeList();		
				List<CourseType> courseTypeList=academicsService.getCourseTypeList();
				model.addAttribute("courseTypeList", courseTypeList);
				if(courseTypeList != null){
					model.addAttribute("courseTypeList", courseTypeList);
				}
			}catch(NullPointerException e){
				logger.error("Exception in createCourse() in AcademicsController: ", e);
			}catch(Exception e){
				logger.error("Exception in createCourse() in AcademicsController: ", e);
			}
		return new ModelAndView(strView);
		}
		
		/**
		 * modified by naimisha ghosh
		 * changes taken on 28062017
		 * */
		
		@RequestMapping(value="/createCourse",method=RequestMethod.POST)
		public ModelAndView saveCourse(HttpServletRequest request, 
									   HttpServletResponse response,
									   ModelMap model,
									   AdmissionForm admissionForm,
									   UploadFile uploadFile,
									   @ModelAttribute("sessionObject") SessionObject sessionObject){
			String saveStatusForCourse = "fail";
			String message = null;
			String updatedBy = sessionObject.getUserId();
			List<Section>sectionList = new ArrayList<Section>();
			try{
				String []section = request.getParameterValues("section");
				String []sectionCapacity = request.getParameterValues("sectionCapacity");
				logger.info("saveCourse() method in AcademicsController: ");
				admissionForm.setCourseName(admissionForm.getCourseName().trim().toUpperCase());
				admissionForm.setCourseAcronym(admissionForm.getCourseAcronym().trim().toUpperCase());
				System.out.println("length==="+section.length);
				for(int i=0;i<section.length;i++){
					Section sectionObj = new Section();
					sectionObj.setSectionCode(section[i]);
					sectionObj.setTotalSeat(Integer.parseInt(sectionCapacity[i]));
					sectionList.add(sectionObj);
				}
				admissionForm.setSectionList(sectionList);
				admissionForm.setUpdatedBy(updatedBy);
				saveStatusForCourse = academicsService.saveCourse(admissionForm, updatedBy);
				
				if(saveStatusForCourse.equals("success")){
					
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("ENTER PROGRAM DETAILS");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor(admissionForm.getCourseName()+"");
					updateLog.setDescription("A Program With name  "+admissionForm.getCourseName()+" Created.");
					commonService.insertIntoActivityLog(updateLog);
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception in saveCourse() in AcademicsController: ", e);
			}
			if(saveStatusForCourse.equalsIgnoreCase("success")){
				return getCourseSubjectMapping(request, response, model);
			}else{
				message = "Failed to Enter Details";
				model.addAttribute("insertUpdateStatus", saveStatusForCourse);
				model.addAttribute("msg", message);
				return createCourse(request, response, model);
			}
		}
		
		@RequestMapping(value="/setupAdmissionDrive",method=RequestMethod.GET)
		public ModelAndView setupAdmissionDrive(HttpServletRequest request, 
										HttpServletResponse response,
										ModelMap model){
			String strView = "academics/setupAdmissionDrive";
			//List<Course> courseTypeList = null;
			String driveStatus = null;
			try{
				logger.info("setupAdmissionDrive() method in AcademicsController: ");
				String courseCode = request.getParameter("courseCode");
				String courseName = request.getParameter("courseName");
				String courseType = request.getParameter("courseType");
				String noOfSeats = request.getParameter("noOfSeats");
				Course cousrDetails = academicsService.getProgramDriveDetailsForProgram(courseCode);
				//System.out.println("drive status ==="+cousrDetails.getDesc());
				//System.out.println(courseCode+":::::::::::::"+courseName);
				//List<Course> courseList = academicsService.getCourseList();
				//List<AdmissionForm> courseDetailsListList = academicsService.getAllCourseDetails();
				model.addAttribute("courseCode", courseCode);
				model.addAttribute("courseName", courseName);
				model.addAttribute("courseType", courseType);
				model.addAttribute("noOfSeats", noOfSeats);
				if(null != cousrDetails){
					driveStatus = cousrDetails.getDesc();
				}
				
				
			
			}catch(NullPointerException e){
				logger.error("Exception in setupAdmissionDrive() in AcademicsController: ", e);
			}catch(Exception e){
				logger.error("Exception in setupAdmissionDrive() in AcademicsController: ", e);
			}
			if(null != driveStatus && driveStatus.equalsIgnoreCase("PUBLISHED")){
				model.addAttribute("msg", "A Drive For This Program is Published And Not Completed Yet");
				model.addAttribute("insertUpdateStatus", "exist");
				return listOfProgramsToPublish( request,  response, model);
			}else{
				model.addAttribute("status", "Not Yet Published");
				return new ModelAndView(strView);
			}
		}
		
		/**
		 * modified by anup.roy 30062017*/
		
		@RequestMapping(value="/setupAdmissionDrive",method=RequestMethod.POST)
		public ModelAndView setupAdmissionDrive(HttpServletRequest request, 
									   HttpServletResponse response,
									   ModelMap model,
									   AdmissionForm admissionForm,
									   UploadFile uploadFile,
									   @ModelAttribute("sessionObject") SessionObject sessionObject){
			String saveStatusForCourse = "fail";
			String message = null;
			String updatedBy = sessionObject.getUserId();
			try{
			
				logger.info("setupAdmissionDrive() method in AcademicsController: ");
				Attachment attachment = null;	
				
				String status = admissionForm.getStatus();
				System.out.println("status====="+status);
				saveStatusForCourse = academicsService.saveAdmissionDrive(admissionForm, updatedBy);
				model.addAttribute("insertUpdateStatus", saveStatusForCourse);
				String msg = null;
				if(saveStatusForCourse.equalsIgnoreCase("success")){
					msg = "Drive Created SuccessFully";
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("SET UP ADMISSION DRIVE");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor("Admission Drive For "+admissionForm.getCourseName()+"");
					updateLog.setDescription("Admission Drive For Program "+admissionForm.getCourseName()+" Created.");
					commonService.insertIntoActivityLog(updateLog);
					
				}else{
					msg = "Failed to create Drive";
				}
				model.addAttribute("msg", msg);
				
				//PRAD MAY 30 2018
				// If Insert Successful, and WEBIQ is available, then call the API
				if(saveStatusForCourse.equals("success") && isWebIQAvailable.equalsIgnoreCase("true")){
					String courseCode = admissionForm.getCourseCode();
					System.out.println("course_code=="+courseCode);
					ProgrammeDetailsForPortal programmeDetails = admissionService.setProgrammeDetailsForPortal(courseCode);
					model.addAttribute("programmeDetails",programmeDetails);
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("username",portalUserName);
					jsonObj.put("password",portalPassWord);
					jsonObj.put("driveName", programmeDetails.getAdmissionDrive());
					jsonObj.put("programCode", programmeDetails.getProgramCode());
					jsonObj.put("programName", programmeDetails.getProgramName());
					jsonObj.put("programType", programmeDetails.getProgramType());
					jsonObj.put("totalSeat", programmeDetails.getTotalSeat());
					jsonObj.put("formIssuanceDate", programmeDetails.getFormIssuanceDate());
					jsonObj.put("formSubmissionLastDate", programmeDetails.getFormSubmissionLastDate());
					jsonObj.put("candidateScrutinyDate", programmeDetails.getCandidateScrutinyDate());
					jsonObj.put("interviewDate", programmeDetails.getInterviewDate());
					jsonObj.put("interviewTime", programmeDetails.getInterviewDate());
					jsonObj.put("marksSubmissionDate", programmeDetails.getMarksSubmissionDate());
					jsonObj.put("feesPaymentStartDate", programmeDetails.getFeesPaymentStartDate());
					jsonObj.put("feesPaymentEndDate", programmeDetails.getFeesPaymentEndDate());
					jsonObj.put("formFees", programmeDetails.getCourseFees());
					List<TermWiseFees> termWiseFeesList = programmeDetails.getTermWiseFeesList();
					JSONArray jsonArrayTermFees = new JSONArray();
					int termCounter = 1;
					for(TermWiseFees termFees : termWiseFeesList){
						JSONObject jsonTermFees = new JSONObject();
						jsonTermFees.put("termName", termFees.getTermName());
						jsonTermFees.put("termId", termCounter);
						List<CategoryAndFees> categoryAndFeesLIst = termFees.getCategoryAndFeesList();
						JSONArray categoryAndFeesArray = new JSONArray();
						for(CategoryAndFees categoryAndFees : categoryAndFeesLIst){
							JSONObject categoryAndFeesJson = new JSONObject();
							categoryAndFeesJson.put("feeType", categoryAndFees.getFeesStructureName());
							List<SocialCategory> socialCategoryList = categoryAndFees.getSocialCategoryList();
							for(SocialCategory scategory : socialCategoryList){
								categoryAndFeesJson.put(scategory.getSocialCategoryName(), scategory.getAmount());
							}
							categoryAndFeesArray.put(categoryAndFeesJson);
						}
						jsonTermFees.put("fees", categoryAndFeesArray);
						jsonArrayTermFees.put(jsonTermFees);
						termCounter++;
					}
					jsonObj.put("semesterAndFees", jsonArrayTermFees);
					System.out.println(jsonObj.toString());

					final String uri = URIForSendingProgrammeDetails;
					URL url = new URL(uri);
					HttpURLConnection connection = (HttpURLConnection)url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestProperty("Content-Type", "application/json");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					connection.setRequestMethod("POST");
					OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
					out.write(jsonObj.toString());
					out.close();
					
					String json_response = "";
					InputStreamReader in = new InputStreamReader(connection.getInputStream());
					BufferedReader br = new BufferedReader(in);
					String text = "";
					while((text = br.readLine())!= null){
						json_response += text;
					}
					System.out.println("JSON response:::"+ json_response);
					if((!json_response.isEmpty())){
						JSONObject object = new JSONObject(json_response);
						int statusFromJsonResponse = (int)object.get("status");
						WebIQTransaction webIQTran = null;
						
						if(statusFromJsonResponse==200){
							Utility dateUtil = new Utility();
							String formIssue = dateUtil.convertEpochToDDMMYYYY(programmeDetails.getFormIssuanceDate());
							String formSubmissionLast = dateUtil.convertEpochToDDMMYYYY(programmeDetails.getFormSubmissionLastDate());
							String candidateScrutiny = dateUtil.convertEpochToDDMMYYYY(programmeDetails.getCandidateScrutinyDate());
							String interview = dateUtil.convertEpochToDDMMYYYY(programmeDetails.getInterviewDate());
							String formIssueDate = dateUtil.convertEpochToDDMMYYYY(programmeDetails.getFormIssuanceDate());
							String markSubmissionStart = dateUtil.convertEpochToDDMMYYYY(programmeDetails.getMarksSubmissionDate());
							String feesPaymentStart = dateUtil.convertEpochToDDMMYYYY(programmeDetails.getFeesPaymentStartDate());
							String feesPaymentEnd = dateUtil.convertEpochToDDMMYYYY(programmeDetails.getFeesPaymentEndDate());
							model.addAttribute("programmeDetails",programmeDetails);
							model.addAttribute("formIssue",formIssue);
							model.addAttribute("formSubmissionLast",formSubmissionLast);
							model.addAttribute("candidateScrutiny",candidateScrutiny);
							model.addAttribute("interview",interview);
							model.addAttribute("formIssueDate",formIssueDate);
							model.addAttribute("markSubmissionStart",markSubmissionStart);
							model.addAttribute("feesPaymentStart",feesPaymentStart);
							model.addAttribute("feesPaymentEnd",feesPaymentEnd);
							String courseDetailsCode = programmeDetails.getCourseDetailsCode();
							String updateStatus = admissionService.updateProgramStatus(programmeDetails.getAdmissionDrive());
							
							//If call to the API is successful, then insert into the webiq_transaction_details table 
							webIQTran = new WebIQTransaction();
							webIQTran.setUri(URIForSendingProgrammeDetails);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(true);
						}else{
							//If Failure then also insert into the webiq_transaction_details table
							webIQTran = new WebIQTransaction();
							webIQTran.setUpdatedBy(sessionObject.getUserId());
							webIQTran.setUri(URIForSendingProgrammeDetails);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(false);
						}
						
						//Call to the BackOffice Service
						backOfficeService.addWebIQTransaction(webIQTran);
					}
				}//IF ENDS
				
				//PRAD END
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception in setupAdmissionDrive() in AcademicsController: ", e);
			}
			//if(saveStatusForCourse.equalsIgnoreCase("success")){
				return listOfProgramsToPublish(request, response, model);
			/*}else{
				message = "Failed to Enter Details";
				model.addAttribute("insertUpdateStatus", saveStatusForCourse);
				model.addAttribute("msg", message);
				return createCourse(request, response, model);
			}*/
		}
		
		
		@RequestMapping(value="/editSetUpAdmissionDrive",method=RequestMethod.GET)
		public ModelAndView editSetUpAdmissionDrive(HttpServletRequest request, 
										HttpServletResponse response,
										ModelMap model,
										@ModelAttribute(value="courseDetailsCode") String courseDetailsCode){
			String strView = "academics/editSetupAdmissionDrive";
			//List<Course> courseTypeList = null;
			try{
				logger.info("editSetUpAdmissionDrive() method in AcademicsController: ");
				System.out.println("courseDetailsCode==="+courseDetailsCode);
				AdmissionForm courseDetails= academicsService.getAllCourseDetailsForEdit(courseDetailsCode);
				model.addAttribute("courseDetails", courseDetails);
				
			
			}catch(NullPointerException e){
				logger.error("Exception in editSetUpAdmissionDrive() in AcademicsController: ", e);
			}catch(Exception e){
				logger.error("Exception in editSetUpAdmissionDrive() in AcademicsController: ", e);
			}
		return new ModelAndView(strView);
		}
		
		@RequestMapping(value="/editSetUpAdmissionDrive",method=RequestMethod.POST)
		public ModelAndView editSetUpAdmissionDrive(HttpServletRequest request, 
									   HttpServletResponse response,
									   ModelMap model,
									   AdmissionForm admissionForm,
									   @ModelAttribute("sessionObject") SessionObject sessionObject){
			String editStatusForCourseDetails = "fail";
			String updatedBy = sessionObject.getUserId();
			try{
			
				logger.info("editSetUpAdmissionDrive() method in AcademicsController: ");
			
				
				admissionForm.setUpdatedBy(updatedBy);
				editStatusForCourseDetails = academicsService.updateCourseDetailsForAdmissionDrive(admissionForm);
				model.addAttribute("insertUpdateStatus", editStatusForCourseDetails);
				String msg = null;
				if(editStatusForCourseDetails.equalsIgnoreCase("success")){
					msg = "Drive Updated SuccessFully";
					
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("SET UP ADMISSION DRIVE");
					updateLog.setInsertUpdate("UPDATE");
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor("Admission Drive For"+admissionForm.getCourseName()+"");
					updateLog.setDescription("Admission Drive For Program "+admissionForm.getCourseName()+" Updated.");
					commonService.insertIntoActivityLog(updateLog);
				}else{
					msg = "Failed to Update Drive";
				}
				model.addAttribute("msg", msg);
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception in editSetUpAdmissionDrive() in AcademicsController: ", e);
			}
			//if(saveStatusForCourse.equalsIgnoreCase("success")){
				return setupAdmissionDrive(request, response, model);
			/*}else{
				message = "Failed to Enter Details";
				model.addAttribute("insertUpdateStatus", saveStatusForCourse);
				model.addAttribute("msg", message);
				return createCourse(request, response, model);
			}*/
		}
		
		/**
		 * @author anup.roy
		 * this method is for view all standards which is applicable for online admission*/
		
		@RequestMapping(value="/listOfProgramsToPublish",method=RequestMethod.GET)
		public ModelAndView listOfProgramsToPublish(HttpServletRequest request, 
										HttpServletResponse response,
										ModelMap model){
			String strView = "academics/listOfProgramsToPublish";
			try{
				logger.info("listOfProgramsToPublish() method in AcademicsController: ");
				List<Course> courseList = academicsService.getListOfProgramsToPublish();
				System.out.println("courseList size=="+courseList.size());
				System.out.println("Status ==="+courseList.get(0).getStatus());
				model.addAttribute("courseList", courseList);
			}catch(NullPointerException e){
				logger.error("Exception in listOfProgramsToPublish() in AcademicsController: ", e);
			}catch(Exception e){
				logger.error("Exception in listOfProgramsToPublish() in AcademicsController: ", e);
			}
		return new ModelAndView(strView);
		}
		
		@RequestMapping(value="/listOfPublishedPrograms",method=RequestMethod.GET)
		public ModelAndView listOfPublishedPrograms(HttpServletRequest request, 
										HttpServletResponse response,
										ModelMap model){
			String strView = "academics/listOfPublishedPrograms";
			//List<Course> courseTypeList = null;
			try{
				logger.info("listOfPublishedPrograms() method in AcademicsController: ");
				List<Course> courseList = academicsService.listOfPublishedProgramsList();
				System.out.println("courseList size=="+courseList.size());
				System.out.println("Status ==="+courseList.get(0).getStatus());
				model.addAttribute("courseList", courseList);
				
			
			}catch(NullPointerException e){
				logger.error("Exception in listOfPublishedPrograms() in AcademicsController: ", e);
			}catch(Exception e){
				logger.error("Exception in listOfPublishedPrograms() in AcademicsController: ", e);
			}
		return new ModelAndView(strView);
		}
		//Naimisha end 17062017
		
		@RequestMapping(value="/listCourse",method=RequestMethod.GET)
		public ModelAndView listCourse(HttpServletRequest request, HttpServletResponse response,ModelMap model){		
			ModelAndView mav = null;
			try{
				logger.info("listCourse() method in BackOfficeController: ");
				mav = new ModelAndView("academics/courseList");	
				//String syllabusBasicRootPath = rootPath+courseSyllabusPath;
				List<AdmissionForm> courseList = academicsService.getAllCourseList();
				//System.out.println("List size =============="+ courseList.size());
				model.addAttribute("courseList", courseList);
				
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception in listCourse() in BackOfficeController: ", e);
			}
			return mav;
		}
		
		
		
		
	/**
	 * THIS COMMENTING OF THE FOLLOWING METHOD "editCourse" IS INSTRUCTED BY NAIMISHA
	 * modified by saurav.bhadra
	 * changes taken on 05042017*/
	//Naimisha 19062017	
		@RequestMapping(value = "/editCourse", method = RequestMethod.POST)
		public ModelAndView editCourse(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				//System.out.println("AcademicsController....LN2608...editCourse()");
				Course course = new Course();
				AdmissionForm admissionForm = new AdmissionForm();
				String saveId=request.getParameter("saveId");
				//System.out.println("SaveID-->"+saveId);
				
				String courseCode = request.getParameter("courseCode"+saveId);
				//String admissionEndDate = request.getParameter("newAdmissionEndDate");
				//String courseStartDate = request.getParameter("newCourseStartDate");
				//String openings = request.getParameter("newOpenings");
				String duration = request.getParameter("newDuration");
				
				admissionForm.setCourseCode(courseCode);
			//	admissionForm.setCourseStartDate(courseStartDate);
				//admissionForm.setAdmissionDriveExpectedEndDate(admissionEndDate);
				admissionForm.setCourseDuration(Integer.parseInt(duration));
			//	admissionForm.setNoOfOpenings(Integer.parseInt(openings));
				admissionForm.setUpdatedBy(sessionObject.getUserId());

				String updateStatus = academicsService.editCourse(admissionForm);
				String msg = null;
				if(updateStatus.equalsIgnoreCase("success")){
					msg = "Pragramme updated Successfully";
				}else{
					msg = "Updation Failed";
				}
				model.addAttribute("insertUpdateStatus", updateStatus);
				model.addAttribute("msg",msg);
				
			} catch (CustomException ce){
				ce.printStackTrace();
				logger.error("Exception in method editCourse-POST of AcademicsController", ce);
			}
			 return createCourse(request, response, model);
		}
		
		
		
		
		
		
		/*
		 * modified by sourav.bhadra
		 * Opens page to add Class Subject Mapping
		 * changes taken on 27042017
		 */	
		
		@RequestMapping(value = "/getCourseSubjectMapping", method = RequestMethod.GET)
		public ModelAndView getCourseSubjectMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			String strView = "academics/createCourseSubjectMapping";
			try {
				logger.info("Inside Method getClassSubjectMapping-GET of AcademicsController");
				List<SubjectGroup> subjectGroupList=academicsService.getSubjectGroup();
				model.addAttribute("subjectGroupList", subjectGroupList);
				
				List<Subject> subjectList=commonService.getSubject();
				model.addAttribute("subjectList", subjectList);
				
				List<Standard> standardList=commonService.getStandards();
				model.addAttribute("standardList", standardList);
				
				List<AdmissionForm> courseList=academicsService.getAllCourseList();
				model.addAttribute("courseList", courseList);
			} catch (CustomException ce){
				logger.error("Exception in method getClassSubjectMapping-GET of AcademicsController", ce);
			}
			return new ModelAndView(strView);
		}
		
		/**
		 * @author anup.roy
		 * 08.08.2017
		 * */
		
		@RequestMapping(value = "/getSubjectsForACourse", method = RequestMethod.GET)
		public @ResponseBody
		String getSubjectsForACourse(@RequestParam("course") String course,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
			String subjects = "";
			try {
				subjects = academicsService.getSubjectsForCourse(course);
			} catch (Exception ce) {
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}
			return (new Gson().toJson(subjects));
		}
		
		@RequestMapping(value = "/getSubjectsForCourse", method = RequestMethod.GET)
		public @ResponseBody
		String getSubjectsForCourse(@RequestParam("course") String course,
								@RequestParam("section") String section,
								@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
			String subjects = "";
			try {
				//System.out.println("course==="+course);
				//System.out.println("section===="+section);
			//	subjects = academicsService.getSubjectsForCourse(course);
				TeacherSubjectMapping teacherSubjectMapping = new TeacherSubjectMapping();
				teacherSubjectMapping.setUpdatedBy(sessionObject.getUserId());
				teacherSubjectMapping.setSectionName(section);
				//teacherSubjectMapping.setStandardName(standard);
				teacherSubjectMapping.setTeacherName(course);
				/*For getting subjects for loggedin user*/
				List<Subject> subjectList=academicsService.getSubjectFromStandardTeacherSubjectMapping(teacherSubjectMapping);
				/*subjects = subjects + "#";*/
				for(Subject subject : subjectList){
					subjects = subjects + subject.getSubjectCode()+"*"+subject.getSubjectName()+"#";
				}
				//System.out.println("subjects======"+subjects);
			} catch (Exception ce) {
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}		
			return (new Gson().toJson(subjects));
		}
		
		/*
		 * modified by sourav.bhadra
		 * Updates Class Subject Mapping
		 * changes taken on 27042017
		 * 
		 */	
		@RequestMapping(value = "/editCourseSubjectMapping", method = RequestMethod.POST)
		public ModelAndView editClassSubjectMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam(value="course") String course,
				@RequestParam(required = false,value="subjects") String []subjects,
				@RequestParam(value="oldSubjects", required = false) String []oldSubjects,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String updateStatus = null;
			String msg = null;
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
				if(null != subjectList & subjectList.size()!=0){
					for(String subject:subjectList){
						if(! oldSubjectList.contains(subject))
							insertSubjectList.add(subject);
					}
					for(String subject:oldSubjectList){
						if(! subjectList.contains(subject))
							deleteSubjectList.add(subject);
					}
				}else{
					if(null != oldSubjectList & oldSubjectList.size() !=0){
						for(String subject:oldSubjectList){
							deleteSubjectList.add(subject);
						}
					}
				}
				CourseSubjectMapping standardSubjectMapping=new CourseSubjectMapping();
				standardSubjectMapping.setCourseCode(course);
				if(insertSubjectList.size()!=0)
					standardSubjectMapping.setNewSubjectList(insertSubjectList);
				if(deleteSubjectList.size()!=0)
					standardSubjectMapping.setOldSubjectList(deleteSubjectList);
				standardSubjectMapping.setUpdatedBy(sessionObject.getUserId());
				standardSubjectMapping.setSubjectList(subjectList);
				updateStatus=academicsService.editCourseSubjectMapping(standardSubjectMapping);
			} catch (CustomException ce){
				ce.printStackTrace();
				logger.error("Exception in method editClassSubjectMapping-POST of AcademicsController", ce);
			}
			if(updateStatus.equalsIgnoreCase("success")){
				//anup.roy 06.08.2017 for detouch from term creation and further process
				//return new ModelAndView(getTerm(request, response, model));
				msg = " Standard Subject Mapping Done Successfully";
				model.addAttribute("insertUpdateStatus", updateStatus);
				model.addAttribute("msg",msg);
				return getCourseSubjectMapping(request, response, model);
			}else{
				msg = " Standard Subject Mapping Failed";
				model.addAttribute("insertUpdateStatus", updateStatus);
				model.addAttribute("msg",msg);
				return getCourseSubjectMapping(request, response, model);
			}
		}
		
		@RequestMapping(value = "/studentCourseSubjectMapping", method = RequestMethod.GET)
		public String studentCourseSubjectMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam("link") String link) {
			String strView = "academics/";
			if(link.equalsIgnoreCase("create")){
				strView=strView+"createStudentCourseSubjectMapping";
			}else{
				strView=strView+"editStudentCourseSubjectMapping";
			}
			try {
				logger.info("Inside Method getClassSubjectMapping-GET of AcademicsController");
				
				List<Standard> standardList=commonService.getStandards();
				List<Course>courseList = academicsService.getCourseList();
				model.addAttribute("courseList", courseList);
				model.addAttribute("standardList", standardList);				
				
			} catch (CustomException ce){
				logger.error("Exception in method getClassSubjectMapping-GET of AcademicsController", ce);
			}
			return strView;
		}
		
		
		@RequestMapping(value = "/getStudentsNameAndRollForCourse", method = RequestMethod.GET)
		public @ResponseBody
		String getStudentsNameAndRollForCourse(@RequestParam("course") String course) {
			logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
			String students = "";
			try {
				students = academicsService.getStudentsNameAndRollForCourse(course);
				System.out.println("students======"+students);
			} catch (CustomException ce) {
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}		
			return (new Gson().toJson(students));
		}
		
		/**
		 * modified by saif.ali
		 * changes taken on 28062017
		 * **/
		
		@RequestMapping(value = "/createStudentCourseSubjectMapping", method = RequestMethod.POST)
		public String createStudentCourseSubjectMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam(required = false, value="course") String course,
				@RequestParam(required = false, value="student") String []student,
				@RequestParam(required = false, value="subject") String []subject,
				@RequestParam(required = false, value="type") String type,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				List<StudentCourseSubjectMapping> scsmList=new ArrayList<StudentCourseSubjectMapping>();
				List<String> subjectList = null;
				if(null != subject){
					subjectList=Arrays.asList(subject);
				}
				for(int i=0;i<student.length;i++){
					StudentCourseSubjectMapping scsm=new StudentCourseSubjectMapping();
					scsm.setUpdatedBy(sessionObject.getUserId());
					scsm.setCourseCode(course);
					scsm.setRoll(student[i]);
					scsm.setSubjectList(subjectList);
					scsmList.add(scsm);
				}
				String status=academicsService.createStudentCourseSubjectMapping(scsmList);
				model.addAttribute("insertUpdateStatus", status);
				if(status.equals("success")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("STUDENT DETAILS");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("ACADEMICS SECTION");
					for(StudentCourseSubjectMapping scsm:scsmList){
						updateLog.setUpdatedFor(scsm.getRoll()+"");
						updateLog.setDescription("A New Student with User Id "+scsm.getRoll()+" is assigned in a course" + scsm.getCourseCode()+ "with subjects "+ scsm.getSubjectList());
					}
					commonService.insertIntoActivityLog(updateLog);
				}
				String msg = null;
				if(status.equalsIgnoreCase("success")){
					msg = "Student Pragramme Course Mapping Done Successfully";
				}else{
					msg = "Pragramme Course Mapping Failed";
				}
				model.addAttribute("msg",msg);
			} catch (CustomException ce){
				ce.printStackTrace();
				logger.error("Exception in method getUploadResult-GET of AcademicsController", ce);
			}
			return studentCourseSubjectMapping(request, response, model, type);
		}
		
		@RequestMapping(value = "/getSubjectsMappedStudents", method = RequestMethod.GET)
		public @ResponseBody
		String getSubjectsMappedStudents(@RequestParam("course") String course) {
			logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
			String students = "";
			try {
				students = academicsService.getSubjectsMappedStudents(course);
			} catch (CustomException ce) {
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}		
			return (new Gson().toJson(students));
		}
		
		
		@RequestMapping(value = "/getSubjectsStudiedByStudentInCourse", method = RequestMethod.GET)
		public @ResponseBody
		String getSubjectsStudiedByStudentInCourse(@RequestParam("course") String course,
													@RequestParam("roll") String roll) {
			logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
			String subjects = "";
			try {
				System.out.println("in getSubjectsStudiedByStudentInCourse of AcaCon...LN3002 \n Course :: "+course+"\nRoll :: "+roll);
				StudentCourseSubjectMapping scsm=new StudentCourseSubjectMapping();
				scsm.setCourseCode(course);
				scsm.setRoll(roll);
				subjects = academicsService.getSubjectsStudiedByStudentInCourse(scsm); 
			} catch (CustomException ce) {
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}		
			return (new Gson().toJson(subjects));
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		@RequestMapping(value = "/getExamsForCourse", method = RequestMethod.GET)
		public @ResponseBody
		String getExamsForCourse(@RequestParam("course") String course) {
			String gs = "";
			try {
				gs = academicsService.getExamsForCourse(course);
			} catch (CustomException ce) {
				logger.error("Exception in method getExamsForCourse-GET of AcademicsController", ce);
			}		
			return (new Gson().toJson(gs));
		}
		
	/**done by naimisha
	 * change taken on 11Jan 2017
	 * **/
		
		@RequestMapping(value = "/createExamTypeName", method = RequestMethod.GET)
		public ModelAndView createExamTypeName(HttpServletRequest request,
												HttpServletResponse response, 
												ModelMap model) {
			String strView=null;
			List<ExamType> examTypeListFromDB = null;
			try{					
				examTypeListFromDB = academicsService.getExamType();	
				//System.out.println("examTypeListFromDB ==="+examTypeListFromDB.size());
				if(examTypeListFromDB != null && examTypeListFromDB.size() != 0){
					model.addAttribute("examTypeListFromDB",examTypeListFromDB);
				}
				strView="academics/createExamTypeName";
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}			
			return new ModelAndView(strView);
		}	
		
		@RequestMapping(value = "/submitCreateExamTypeName", method = RequestMethod.POST)
		public ModelAndView submitCreateExamTypeName(HttpServletRequest request,
													HttpServletResponse response, ModelMap model,
													@RequestParam("examTypeName") String []examTypeName,
													@RequestParam("examType") String []examType,
													ArrayList<ExamType> examTypeList,
													@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String strView=null;		
			List<ExamType> examTypeListDB = null;
			try{	
				if(examTypeName != null && examType!= null){
					for(int index=0;index<examTypeName.length;index++){			
						ExamType examTypeObj = new ExamType();
						examTypeObj.setExamTypeName(examTypeName[index].toUpperCase());
						examTypeObj.setExamTypeCode(examType[index].toUpperCase());
						examTypeObj.setUpdatedBy(sessionObject.getUserId());
						examTypeList.add(examTypeObj);
					}
				}
				examTypeListDB = academicsService.saveExamType(examTypeList);	
				if(examTypeListDB.size()!=0){
					String insertUpdateStatus = "success";
					model.addAttribute("insertUpdateStatus", insertUpdateStatus);
					model.addAttribute("msg","Exam Type Created SuccessFully");
				}else{
					String insertUpdateStatus = "fail";
					model.addAttribute("insertUpdateStatus", insertUpdateStatus);
					model.addAttribute("msg","Exam Type Created SuccessFully");
				}
				if(examTypeListDB != null && examTypeListDB.size() != 0){
					model.addAttribute("examTypeListFromDB",examTypeListDB);
				}			
				strView="academics/createExamTypeName";
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
				
			return new ModelAndView(strView);
		}	
		
		/**
		 * @author anup.roy
		 * this method is for getting the view page of creating exam
		 * 08.08.2017
		 * **/
		
		
		@RequestMapping(value = "/createExam", method = RequestMethod.GET)
		public ModelAndView createExam(HttpServletRequest request,
										HttpServletResponse response, 
										ModelMap model) throws CustomException {
			String strView=null;
			List<Standard> classListFromDB = null;
			List<ExamType> examTypeListFromDB = null;
			List<Course> courseListFromDB = null;
			List<Exam> examListDB = null;
			try{
				classListFromDB = academicsService.getStandardsWithSection();			
				examTypeListFromDB = academicsService.getExamType();	
				courseListFromDB = academicsService.getCourseList();
				if(classListFromDB != null  && classListFromDB.size() != 0){
					model.addAttribute("classListFromDB" , classListFromDB);	
				}
				/**
				 * anup.roy for removing exam type from creating exam*/
				/*if(examTypeListFromDB != null && examTypeListFromDB.size() != 0){
					model.addAttribute("examTypeListFromDB",examTypeListFromDB);
				}*/
				examListDB = academicsService.getExamDetails();	
				//System.out.println("examListDB size = "+examListDB.size());
				if(examListDB !=null && examListDB.size()!=0){
					model.addAttribute("examListDB",examListDB);
				}
				model.addAttribute("courseListFromDB" , courseListFromDB);
				//System.out.println("examListDB===="+examListDB.get(0).getStandardCode());
				strView="academics/createExam";
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}
				
			return new ModelAndView(strView);
		}
		
		@RequestMapping(value = "/getCourseForPromotionalExam", method = RequestMethod.GET)
		public @ResponseBody
		String getCourseForPromotionalExam(@RequestParam("strClass") String strClass) {
			String courseNamesDB = "";
			//List<Course> courseList =  null;
			//System.out.println("within");
			try{		
				if(strClass != null && strClass.length() != 0){
					String []classAndStream = strClass.split("-");				
					List<Course> courseList = academicsService.getAllCourseForStandard(classAndStream[0]);
					//System.out.println("courseList size==="+courseList.size());
					if(courseList != null){
						for(Course course : courseList){
							courseNamesDB = courseNamesDB + "," + course.getCourseName() + "##" + course.getCourseCode();
						}	
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//System.out.println("");
			return (new Gson().toJson(courseNamesDB));
		}
		
		/**
		 * @author anup.roy
		 * this method submits the exams 
		 * 09.08.2017
		 * without exam type and term
		 */
		
		@RequestMapping(value = "/submitExam", method = RequestMethod.POST)
		public ModelAndView submitExam(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										@RequestParam("className") String []strclassName,
										@RequestParam("examName") String []examName,
										@RequestParam("courseNames") String []courseNames,
									//	@RequestParam("examType") String []examType,
									//	@RequestParam(required = false, value="term") String []term,
										ArrayList<Exam> examList,
										@ModelAttribute("sessionObject") SessionObject sessionObject) throws CustomException {
			//String strView="academics/listExam";
			
			List<Exam> examListDB = null;
			try{	
				//System.out.println("courseNames======"+courseNames.length);
				if(strclassName != null){
					for(int index=0;index<strclassName.length;index++){
						
						//Class classObj = new Class();
						Standard standardObj  = new Standard();
						standardObj.setStandardName(strclassName[index]);
						//System.out.println("course code====="+standardObj.getStandardName());
						//String examTypeObj = examType[index].toUpperCase();
						
						List<Course> courseList = new ArrayList<Course>();
						if(courseNames != null){
							//String[] courses = courseNames[index].split(";");
							for(int i=0;i<courseNames.length;i++){
								Course courseObj = new Course();
								courseObj.setCourseCode(courseNames[i]);
								courseObj.setCourseName(strclassName[index]);
								//System.out.println("courseNAme==="+courseNames[i]);//Naimisha
								courseList.add(courseObj);
							}
							if(examName != null ){
								Exam examObj = new Exam();
								examObj.setExamName(examName[index].toUpperCase());
								examObj.setExamCode(examName[index].toUpperCase());
								examObj.setDesc(examName[index].toUpperCase());
								//examObj.setStandard(standardObj);
								examObj.setCourseList(courseList);
								//examObj.setExamTypeName(examTypeObj);
								examObj.setUpdatedBy(sessionObject.getUserId());
								/*if(term!=null){
									examObj.setTermCode(term[index]);
								}*/
								examList.add(examObj);
							}
						}
					}
				}
							
				String insertStatus = academicsService.saveExam(examList);	
				if(insertStatus.equalsIgnoreCase("success")){
					model.addAttribute("insertUpdateStatus", insertStatus);	
					model.addAttribute("msg", "Exam Created SuccessFully");	
				}else{
					model.addAttribute("insertUpdateStatus", insertStatus);	
					model.addAttribute("msg", "Failed To Create Exam");
				}
				//if(examListDB != null && examListDB.size() != 0){
					
					/*PagedListHolder<Exam> pagedListHolder = academicsService.listExamPaging(request);
					if (pagedListHolder != null) {
						model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
						model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
						model.addAttribute("total", pagedListHolder.getNrOfElements());
						model.addAttribute("pagedListHolder", pagedListHolder);
					}*/
				//	model.addAttribute("examListDB", examListDB);				
				/*}else{*/
						
			//	}
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}			
			return createExam( request,response, model);
		}
		
		
		@RequestMapping(value = "/setPromotionalExam", method = RequestMethod.GET)
		public ModelAndView setPromotionalExam(HttpServletRequest request,
				HttpServletResponse response, ModelMap model
				) {
			String strView="academics/setPromotionalExam";
			
			try{	
				//List<Class> classListFromDB = commonService.getClassNameWithStream();
				List<Standard> standardList=commonService.getStandards();
				
				if(standardList != null && standardList.size() != 0){	
					model.addAttribute("standardList", standardList);
				}
				
				List<Exam> examListDB = academicsService.getPromotionalExamList();
				model.addAttribute("examListDB", examListDB);
				List<UserDefinedExams> examList=academicsService.getAllUserDefinedExams();
				//System.out.println("examList size===="+examList.size());
				model.addAttribute("examList", examList);	
				List<Course>courseList = academicsService.getCourseList();
				model.addAttribute("courseList", courseList);	
			}
			catch(NullPointerException e){
				logger.error("setPromotionalExam() In AcademicsController.java: NullPointerException"+ e);
			}catch(Exception e){
				logger.error("setPromotionalExam() In AcademicsController.java: Exception"+ e);
			}
				
			return new ModelAndView(strView);
		}
		
/*		@RequestMapping(value = "/getAllExam", method = RequestMethod.GET)
		public @ResponseBody
		String getAllExam(	@RequestParam("strClass") String classNames,
							@RequestParam("strCourse") String courseCode,
							Class classObj,
							Course course
							) {
			String totalStr = "";
			Map<String,String> examMap = null;	
			try{	
				if(classNames != null){
					String []classAndStream = classNames.split("-");
					course.setCourseCode(courseCode);
					classObj.setClassName(classAndStream[0]);				
					classObj.setCourse(course);					
					examMap = academicsService.getAllExamForClassCourse(classObj);
					if(examMap != null){
						for(String exam : examMap.keySet()){				
							totalStr = totalStr + "%" + examMap.get(exam) + "##" + exam;				
						}
					}
				}
			}catch(NullPointerException e){
				logger.error("getAllExam() In AcademicsController.java: NullPointerException"+ e);
			}catch(Exception e){
				logger.error("getAllExam() In AcademicsController.java: Exception"+ e);
			}
			return (new Gson().toJson(totalStr));
		}*/
		
		@RequestMapping(value = "/submitPromotionalExam", method = RequestMethod.POST)
		public ModelAndView submitPromotionalExam(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
			//	@RequestParam("standard") String []strclassName,
				@RequestParam("exam") String examObj,
				@RequestParam("course") String course,
				//ArrayList<Exam> examList,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			//String strView = "academics/listPromotionalExam";
			List<Exam> examListDB = null;		
			try{
				/*if(strclassName != null){
					for(int index=0;index<strclassName.length;index++){
						String []className = strclassName[index].split("-");
						Standard classObj = new Standard();
						classObj.setStandardName(className[0]);	
						Course courseObj = new Course();
						courseObj.setCourseCode(courseCodes[index]);
						Exam ex = new Exam();				
						ex.setExamCode(examCode[index]);
						ex.setCourse(courseObj);
						ex.setStandard(classObj);
						ex.setUpdatedBy(sessionObject.getUserId());
						examList.add(ex);
					}
				}*/
				Exam exam = new Exam();	
				Course courseObj = new Course();
				exam.setCourse(courseObj);
				exam.setUpdatedBy(sessionObject.getUserId());
				exam.setExamCode(examObj);
				examListDB = academicsService.savePromotionalExam(exam);	
				
				if(examListDB != null && examListDB.size() != 0){
					/*PagedListHolder<Course> pagedListHolder = academicsService.listPromotionalExamPaging(request);
					if (pagedListHolder != null) {
						model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
						model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
						model.addAttribute("total", pagedListHolder.getNrOfElements());
						model.addAttribute("pagedListHolder", pagedListHolder);
					}	*/
					model.addAttribute("examListDB", examListDB);
					model.addAttribute("msg","success");
				}else{
					model.addAttribute("msg","fail");
				}
			}catch(NullPointerException e){
				logger.error("submitPromotionalExam() In AcademicsController.java: NullPointerException"+ e);
			}catch(Exception e){
				logger.error("submitPromotionalExam() In AcademicsController.java: Exception"+ e);
			}			
			return setPromotionalExam(request,response,model);
		}
		
		@RequestMapping(value = "/editPromotionalExam", method = RequestMethod.POST)
		public ModelAndView submitEditedPromotionalExam(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject){
			
			Exam ex = new Exam();
			try{
				String saveId = request.getParameter("saveId");
				int serialId = Integer.parseInt(request.getParameter("serialId"+saveId));
				//System.out.println("serialId ======"+serialId);
				String promotionalExam = request.getParameter("examName"+saveId);
				//System.out.println("promotionalExam==="+promotionalExam);
				//String standard = request.getParameter("standardCode"+saveId);
				/*cl.setClassName(className);
				course.setCourseCode(courseCode);
				course.setCourseName(courseName);*/
				ex.setUpdatedBy(sessionObject.getUserId());
				ex.setSerialId(serialId);
				ex.setExamCode(promotionalExam);
				//ex.setClassName(cl);
				//ex.setCourse(course);
				
				String status = academicsService.submitEditedPromotionalExam(ex);		
				model.addAttribute("msg", status);
				List<UserDefinedExams> examList=academicsService.getAllUserDefinedExams();
				model.addAttribute("examList", examList);	
				//model.addAttribute("promotionalExam",promotionalExam);
			}catch(NullPointerException e){
				logger.error("editPromotionalExam() In AcademicsController.java: NullPointerException"+ e);
			}catch(NumberFormatException e){
				logger.error("editPromotionalExam() In AcademicsController.java: NumberFormatException"+ e);
			}catch(Exception e){
				logger.error("editPromotionalExam() In AcademicsController.java: Exception"+ e);
			}
			return setPromotionalExam(request, response, model);
		}
		
		@RequestMapping(value = "/getTermsForACourse", method = RequestMethod.GET)
		public @ResponseBody
		String getTermsForACourse(@RequestParam("course") String course,
								@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
			String subjects = "";
			try {
				subjects = academicsService.getTermsForACourse(course);
				//System.out.println("subjects======"+subjects);
			} catch (Exception ce) {
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}		
			return (new Gson().toJson(subjects));
		}
		
		/**
		 * modified by kaustav.sen
		 * changes taken on 28032017
		 * */
		
		@RequestMapping(value = "/editExam", method = RequestMethod.POST)
		public ModelAndView editExam(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject) throws CustomException  {
			try {
				Exam exam = new Exam();
				String saveId=request.getParameter("saveId");
				exam.setExamCode(request.getParameter("examCode"+saveId).trim());
				exam.setExamName(request.getParameter("getExamName").trim());
				
				exam.setUpdatedBy(sessionObject.getUserId());
				String updateStatus=academicsService.editExam(exam);
				String msg = null;
				if(updateStatus.equalsIgnoreCase("success")){
					msg = "Exam Updated SuccessFully";
				}else{
					msg = "Failed To Update EXam";
				}
				model.addAttribute("msg", msg);
				model.addAttribute("insertUpdateStatus", updateStatus);
				
			} catch (Exception ce){
				ce.printStackTrace();
				logger.error("Exception in method editStandard-POST of HostelController", ce);
			}
			return createExam(request, response, model);
		}	
		
		@RequestMapping(value = "/setExamMarks", method = RequestMethod.GET)
		public String setExamMarks(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
			String strView = "academics/setExamMarks";
			try {
				logger.info("Inside Method createUserdefinedExams-GET of AcademicsController");
				
				List<Standard> standardList=commonService.getStandards();
				List<Course> courseList = academicsService.getCourseList();
				model.addAttribute("courseList", courseList);
				model.addAttribute("standardList", standardList);
				
			} catch (CustomException ce){
				logger.error("Exception in method createUserdefinedExams-GET of AcademicsController", ce);
			}
			return strView;
		}
		
		/**
		 * @author anup.roy
		 * this method is for getting all exams w.r.t a standard
		 * */
		
		@RequestMapping(value = "/getExamsForTermAndCourse", method = RequestMethod.GET)
		public @ResponseBody
		String getExamsForTermAndCourse(@RequestParam("course") String course,
										//@RequestParam("term") String term,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
			String exams = "";
			try {
				Exam exam = new Exam();
				//System.out.println(course +"==="+term);
				exam.setCourseCode(course);
				//exam.setTermCode(term);
				exams = academicsService.getExamsForTermAndCourse(exam);
				//System.out.println("exams======"+exams);
			} catch (Exception ce) {
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}
			return (new Gson().toJson(exams));
		}
		
		/**
		 * @author anup.roy
		 * this method is for submitting the exam marks for a standard and an exam
		 * without term
		 * 10.08.2017
		 * */
		
		@RequestMapping(value = "/editExamMarks", method = RequestMethod.POST)
		public String editExamMarks(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									@RequestParam(value="standard") String standard,
									@RequestParam(value="exam") String exam,
									//@RequestParam(value="term") String term,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("Inside Method editExamMarks-POST of AcademicsController");
				List<ExamMarks> examMarksList = new ArrayList<ExamMarks>();
					String allSubjects[] = request.getParameterValues("subject");
					for(int subject = 0; subject<allSubjects.length; subject++){
						ExamMarks examMarks = new ExamMarks();
						examMarks.setUpdatedBy(sessionObject.getUserId());
						examMarks.setStandard(standard);
						//examMarks.setStatus(term);
						//System.out.println("term ====="+examMarks.getStatus());
						Subject subjectObject = new Subject();
						subjectObject.setSubjectCode(allSubjects[subject]);
						examMarks.setSubject(subjectObject);
						
						Exam examObject = new Exam();
						examObject.setExamCode(exam);
						examMarks.setExam(examObject);
						examMarks.setTheory(Integer.parseInt(request.getParameter(allSubjects[subject]+"theory")));
						examMarks.setTheoryPass(Integer.parseInt(request.getParameter(allSubjects[subject]+"theoryPass")));
						examMarks.setPractical(Integer.parseInt(request.getParameter(allSubjects[subject]+"practical")));
						examMarks.setPracticalPass(Integer.parseInt(request.getParameter(allSubjects[subject]+"practicalPass")));
						examMarks.setTotal(Integer.parseInt(request.getParameter(allSubjects[subject]+"total")));
						examMarks.setPass(Integer.parseInt(request.getParameter(allSubjects[subject]+"pass")));
						
						examMarksList.add(examMarks);
					}
					String updateStatus = null;
				if(examMarksList.size()!=0){
					 updateStatus=academicsService.editExamMarks(examMarksList);
				}
				model.addAttribute("insertUpdateStatus", updateStatus);
				String msg = null;
				if(updateStatus.equalsIgnoreCase("success")){
					msg = "You've successfully set up the marks";
				}else{
					msg = "Marks set up process failed";
				}
				model.addAttribute("msg", msg);
			} catch (CustomException ce){
				logger.error("Exception in method editExamMarks-POST of AcademicsController", ce);
				ce.printStackTrace();
			}
			return setExamMarks(request, response, model);
		}
		
		
	/***********Added By Naimisha 27022017*************/
		@RequestMapping(value = "/getSubjectsForACourseAndTermAndTeacher", method = RequestMethod.GET)
		public @ResponseBody
		String getSubjectsForACourseAndTermAndTeacher(@RequestParam("course") String course,
											@RequestParam("term") String term,
											@RequestParam("section") String section,
								@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
			String subjects = "";
			try {
				Course courseObj  = new Course();
				courseObj.setCourseCode(course);
				courseObj.setCourseName(term);
				courseObj.setUpdatedBy(sessionObject.getUserId());
				courseObj.setCourseDesc(section);
				System.out.println("in controller::course::"+course+"\n section::"+section+"\nterm::"+term);
				subjects = academicsService.getSubjectsForACourseAndTermAndTeacher(courseObj);
				
				//System.out.println("subjects======"+subjects);
			} catch (Exception ce) {
				ce.printStackTrace();
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}		
			return (new Gson().toJson(subjects));
		}
		/* 
		 * Gets Subjects And Marks For Course Term ANd Exam
		 */
		@RequestMapping(value = "/getSubjectsAndMarksForCourseExamTerm", method = RequestMethod.GET)
		public @ResponseBody
		String getSubjectsAndMarksForCourseExamTerm(@RequestParam("course") String standard,
												@RequestParam("exam") String exam,
												@RequestParam("term") String term) {
			//System.out.println("within");
			logger.info("Inside Method getSubjectsAndMarksForCourseExamTerm-GET of AcademicsController");
			String examAndMarks = "";
			try {
				//subjectAndMarks = academicsService.getSubjectsAndMarksForStandard(standard, exam,term);
				examAndMarks = academicsService.getSubjectsAndMarksForCourseExamTerm(standard, exam, term);
				//System.out.println("examAndMarks===="+examAndMarks);
			} catch (Exception ce) {
				logger.error("Exception in method getSubjectsAndMarksForCourseExamTerm-GET of AcademicsController", ce);
			}		
			return (new Gson().toJson(examAndMarks));
		}
		
		/*****************sourav 01032017*********************/


@RequestMapping(value = "/getTerm", method = RequestMethod.GET)
		public String getTerm(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			String strView = "academics/createTerm";
			
			try {
				logger.info("Inside Method getHostel-GET of HostelController");
				List<Course> programList = academicsService.getCourseList();
				model.addAttribute("programList", programList);
				
				List<Term> termList = academicsService.getTermList();
				/*for(Term t : termList){
					System.out.println("Term Code : "+t.getTermCode());
					System.out.println("Term Name : "+t.getTermName());
					System.out.println("Term Desc : "+t.getTermDesc());
					System.out.println("Course Name : "+t.getCourse().getCourseName());
					System.out.println("Session : "+t.getAcademicYear());
				}*/
				model.addAttribute("termList", termList);
				
				AcademicYear academicYear = academicsService.getCurrentAcademicYear();
				//System.out.println(academicYear.getAcademicYearCode());
				model.addAttribute("academicYear", academicYear.getAcademicYearCode());
			} catch (CustomException ce){
				ce.printStackTrace();
				logger.error("Exception in method getTerm-GET of AcademicController", ce);
			}
			return strView;
		}
		
		/**
		 * modified by sourav.bhadra
		 * changes taken on 27042017**/

		@RequestMapping(value = "/createTerm", method = RequestMethod.POST)
		public String createTerm(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				Term term,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String insertStatus = null;
			String msg = null;
			try {
				Course courseObj = new Course();
				courseObj.setCourseCode(request.getParameter("courseName"));
				term.setTermName(term.getTermName().trim().toUpperCase());
				term.setTermDesc(term.getTermName().trim().toUpperCase());
				term.setCourse(courseObj);
				term.setUpdatedBy(sessionObject.getUserId());
				insertStatus=academicsService.createTerm(term);
				if(insertStatus.equalsIgnoreCase("success")){
					
				}else if(insertStatus.equalsIgnoreCase("fail")){
					msg = "Insertion Failed";
				}else{
					msg = "Term Already Exists"; 
				}
			} catch (CustomException ce){
				ce.printStackTrace();
				logger.error("Exception in method getUploadResult-GET of AcademicsController", ce);
			}
			if(insertStatus.equalsIgnoreCase("success")){
				return getTermCourseMapping(request, response, model);
			}else{
				model.addAttribute("insertUpdateStatus", insertStatus);
				model.addAttribute("msg",msg);
				return getTerm(request, response, model);
			}
		}
		
		@RequestMapping(value = "/getTermCourseMapping", method = RequestMethod.GET)
		public String getTermCourseMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			String strView = "academics/createTermCourseMapping";
			try {
				logger.info("Inside Method getClassSubjectMapping-GET of AcademicsController");
				
			//	System.out.println("in getTermCourseMapping method of academicsController...line no 3206");
				
				List<Course> programList = academicsService.getCourseList();
				model.addAttribute("programList", programList);
				
				List<SubjectGroup> subjectGroupList=academicsService.getSubjectGroup();
				model.addAttribute("subjectGroupList", subjectGroupList);
				
				List<Subject> subjectList=commonService.getSubject();
				model.addAttribute("subjectList", subjectList);

			} catch (CustomException ce){
				logger.error("Exception in method getClassSubjectMapping-GET of AcademicsController", ce);
			}
			return strView;
		}
		
		@RequestMapping(value = "/getTermNameForCourse", method = RequestMethod.GET)
		public @ResponseBody
		String getTermNameForCourse(@RequestParam("course") String course) {
			logger.info("Inside Method getTermNameForCourse-GET of AcademicsController");
			String termName = "";
			//System.out.println("in AcademicsController line no 3222");
			//System.out.println(course);
			try {
				termName = academicsService.getTermsForACourse(course);//getTermNameForCourse(course)
				//System.out.println("termName======"+termName);
			} catch (Exception ce) {
				ce.printStackTrace();
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}		
			return (new Gson().toJson(termName));
		}
		
		/**
		 * modified by sourav.bhadra
		 * changes taken 27042017**/
		
		@RequestMapping(value = "/editTermCourseMapping", method = RequestMethod.POST)
		public String editTermCourseMapping(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam(value="course") String course,
				@RequestParam(value="term") String term,
				@RequestParam(required = false, value="subjects") String []subjects,
				@RequestParam(required = false,value="oldSubjects") String []oldSubjects,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String updateStatus = null;
			String msg = null;
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
				if(null != subjectList & subjectList.size() != 0){
					for(String subject:subjectList){
						if(! oldSubjectList.contains(subject))
							insertSubjectList.add(subject);
					}
					for(String subject:oldSubjectList){
						if(! subjectList.contains(subject))
							deleteSubjectList.add(subject);
					}
				}else{
					if(null != oldSubjectList & oldSubjectList.size() != 0){
						for(String subject:oldSubjectList){
							deleteSubjectList.add(subject);
						}
					}
				}
				
				CourseSubjectMapping termSubjectMapping=new CourseSubjectMapping();
				termSubjectMapping.setCourseCode(course);
				termSubjectMapping.setTerm(term);
				
				if(insertSubjectList.size()!=0)
					termSubjectMapping.setNewSubjectList(insertSubjectList);
				if(deleteSubjectList.size()!=0)
					termSubjectMapping.setOldSubjectList(deleteSubjectList);
				termSubjectMapping.setUpdatedBy(sessionObject.getUserId());
				
				updateStatus=academicsService.editTermCourseSubjectMapping(termSubjectMapping);
			
			} catch (CustomException ce){
				logger.error("Exception in method editTermCourseMapping-POST of AcademicsController", ce);
				ce.printStackTrace();
			}
			if(updateStatus.equalsIgnoreCase("success")){
				model.addAttribute("insertUpdateStatus", updateStatus);
				msg = "Map Term And Subject SuccessFully";
				model.addAttribute("msg", msg);
				//return backOfficeController.assignAmountToProgramAndTermWiseFeesTemplate(request, response, model);
			}else{
				model.addAttribute("insertUpdateStatus", updateStatus);
				msg = "Failed To Map Term And Subject";
				model.addAttribute("msg", msg);
				
			}
			return getTermCourseMapping(request, response, model);
		}
		
		/**
		 * @author anup.roy
		 * this method is for getting subjects w.r.t a course */
		
		@RequestMapping(value = "/getSubjectsForTermAndCourse", method = RequestMethod.GET)
		public @ResponseBody
		String getSubjectsForTermAndCourse(@RequestParam("course") String course
										//@RequestParam("term") String termCode
										) {
			logger.info("Inside Method getTermNameForCourse-GET of AcademicsController");
			
			List<Subject> subjectList = null;
			String subjectNames = "";
			try {
				Course crsObj = new Course();
				crsObj.setCourseCode(course);
				
				Term term = new Term();
				//term.setTermCode(termCode);
				term.setCourse(crsObj);
				//term.setTermDesc(exam);
				
				subjectList = academicsService.getSubjectForCourseAndTerm(term);
				
				for(Subject sub:subjectList){
					subjectNames += sub.getSubjectCode()+"#~#"+sub.getSubjectName()+"#*#";
				}
			} catch (Exception ce) {
				ce.printStackTrace();
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}
			return (new Gson().toJson(subjectNames));
		}
		
		
		
		
		
		/************uploadQuestionPaperByKoustav 03032017*************/
		
		@RequestMapping(value = "/uploadAssignment", method = RequestMethod.GET)
		public String uploadAssignment(HttpServletRequest request,
										HttpServletResponse response, ModelMap model) {
			logger.info("Inside uploadQp() of HostelController");
			try {
				List<AcademicYear> academicYearList = commonService.getPreviousAndCurrentAcademicYear();
				if(null != academicYearList && academicYearList.size() != 0){
					model.addAttribute("academicYearList", academicYearList);
				}
				List<Course> courseList = academicsService.getCourseList();
				model.addAttribute("courseList", courseList);
			}catch (Exception e) {
				logger.error("getBatchAgainstCourse() In HostelController.java: Exception"
						+ e);
			}
			return "academics/uploadAssignment";
		}



/*@RequestMapping(value = "/getBatchAgainstCourse", method = RequestMethod.GET)
	public @ResponseBody
	String getBatchAgainstCourse(@RequestParam("course") String course) {
		String gs = null;
		try {
			System.out.println("inside cntrlr");
			logger.info("getBatchAgainstCourse() In HostelController.java");
			gs = commonService.getBatchAgainstCourse(course);
			System.out.println(gs);
		} catch (NullPointerException e) {
			logger.error("getBatchAgainstCourse() In HostelController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getBatchAgainstCourse() In HostelController.java: Exception"
					+ e);
		}
		return gs;
	}*/


/*@RequestMapping(value = "/getSubjectsForACoursesession", method = RequestMethod.GET)
	public @ResponseBody
	String getSubjectsForACourse(@RequestParam("course") String course,StudentResult studentResult, ModelMap model,
							@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
		String subjects = "";
	String it="";
		try {
			studentResult.setUpdatedBy(sessionObject.getUserId());
			System.out.println("kaustav");
			System.out.println(studentResult.getUpdatedBy());
			it=studentResult.getUpdatedBy();
			System.out.println("bhooot=="+it);
			
			subjects = hostelService.getSubjectsForCoursesession(course);
			System.out.println("kkkkkkk"+subjects);
			
		} catch (Exception ce) {
			logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(subjects));
		
	}*/


/*@RequestMapping(value = "/getExamsForCoursesession", method = RequestMethod.GET)
	public @ResponseBody
	String getExamsForCoursesession(@RequestParam("course") String course) {
		String gs = "";
		gs = hostelService.getExamsForCoursesession(course);		
		return (new Gson().toJson(gs));
	}*/
	/*@RequestMapping(value = "/getTeacherid", method = RequestMethod.GET)
	public @ResponseBody
	String getTeacherid(@RequestParam("course") String course) {
		String gs = "";
		gs = hostelService.getTeacherId(course);
		gs=gs+"*~*"+gs;
		System.out.println(gs);
		return (new Gson().toJson(gs));//Required
	}*/

/*@RequestMapping(value = "/getTermsForACourse", method = RequestMethod.GET)
		public @ResponseBody
		String getTermsForACourse(@RequestParam("course") String course,
								@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("Inside Method getSubjectsForStandard-GET of AcademicsController");
			String subjects = "";
			try {
				subjects = academicsService.getTermsForACourse(course);
				System.out.println("subjects======"+subjects);
			} catch (Exception ce) {
				logger.error("Exception in method getSubjectsForStandard-GET of AcademicsController", ce);
			}		
			return (new Gson().toJson(subjects));
		}*/



		@RequestMapping(value = "/uploadAssignment", method = RequestMethod.POST)
		public String uploadAssignment(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				StudentResult studentResult,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			logger.info("Inside uploadQp() of AcademicsController");
			
			if(null != studentResult){
				/***new code added for save this edited file into external repository**/
				RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
				String repository = repositoryStructure.getRepositoryPathName();
				File directory = new File(repository);
				boolean isExists = directory.exists();
				String insertStatus = null;
				if(isExists == true){
					Attachment attachment = new Attachment();
					//System.out.println("innnnnnn");
					attachment.setStorageRootPath(repository);
					attachment.setFolderName(uploadAssignmenetPath);	
					
					if(null != studentResult.getUploadFile()){
						studentResult.getUploadFile().setAttachment(attachment);
					}				
					studentResult.setUpdatedBy(sessionObject.getUserId());
					
					//System.out.println("innnnnnn");
					insertStatus = academicsService.uploadAssignment(studentResult);
					//System.out.println("insertStatus=="+insertStatus);
					String msg = null;
					if(null != insertStatus){
						model.addAttribute("insertUpdateStatus", "success");		
						model.addAttribute("msg", "Assignment Successfully Uploaded");					
					}else{
						model.addAttribute("insertUpdateStatus", "fail");		
						model.addAttribute("msg", "Could not Upload Assignment.");	
					}
				}else{
					//System.out.println("directory not found");
				}
			}else{
				logger.info("Proper data not Found.");
			}
			return uploadAssignment(request, response, model);
		}
		
		
		/************uploadAssignmentBy koustav 03032017*************/
		/*******************************03032017GFor Aminities Usage Of Student****************/
		@RequestMapping(value = "/getAminitiesUsedByStudent", method = RequestMethod.GET)
		public String getAminitiesUsedByStudent(HttpServletRequest request,
				HttpServletResponse response, ModelMap model) {
			String strView = "venue/listAminitiesUsageByStudent";
			try {
				logger.info("Inside Method allocateVenue-GET of VenueController");
				List<Course> courseList = academicsService.getCourseList();
				model.addAttribute("courseList", courseList);
			} catch (Exception ce){
				logger.error("Exception in method allocateVenue-GET of VenueController", ce);
			}
			return strView;
		}
		@RequestMapping(value = "/getStudentsForAllCourse", method = RequestMethod.GET)
		public @ResponseBody
		String getStudentsForAllCourse() {
			logger.info("Inside Method getFacilityListAgainstVenue-GET of VenueController");
			String student = "";
			try {
				 student = academicsService.getStudentsForAllProgrammes();
			} catch (Exception ce) {
				ce.printStackTrace();
				logger.error("Exception in method getFacilityListAgainstVenue-GET of VenueController", ce);
			}	
			
			//System.out.println("student====="+student);
			return (new Gson().toJson(student));
		}
		
		@RequestMapping(value = "/getAminitiesUsageDetaulsByStudent", method = RequestMethod.GET)
		public String getAminitiesUsageDetaulsByStudent(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam(required = false, value="rollNumber") String rollNumber,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			String strView = "venue/aminitiesUsageDetailsByStudent";
			try {
				logger.info("Inside Method getAminitiesUsageDetaulsByStudent-POST of AcademicsController");
				//System.out.println("rollNumber===="+rollNumber);
				Student student = academicsService.getAminitiesUsageByStudent(rollNumber);
				List<Facility>facilityList = student.getFacilityList();
				/*for(Facility f :facilityList){
					System.out.println(f.getFacilityCode()+"*********"+f.getFacilityName());
				}*/
				model.addAttribute("student", student);
			} catch (Exception ce){
				logger.error("Exception in method getAminitiesUsageDetaulsByStudent-POST of AcademicsController", ce);
				ce.printStackTrace();
			}
			return strView;
		}
		
		@RequestMapping(value = "/getStudentsForprogrammeAndBatch", method = RequestMethod.GET)
		public @ResponseBody
		String getStudentsForprogrammeAndBatch(@RequestParam("course") String course,
											@RequestParam("section") String section) {
			logger.info("Inside Method getStudentsForprogrammeAndBatch-GET of VenueController");
			String student = "";
			try {
				Course courseObj = new Course();
				courseObj.setCourseCode(course);
				courseObj.setCourseName(section);
				 student = academicsService.getStudentsForAllProgrammesAndBatches(courseObj);
			} catch (Exception ce) {
				ce.printStackTrace();
				logger.error("Exception in method getFacilityListAgainstVenue-GET of VenueController", ce);
			}	
			
			//System.out.println("student====="+student);
			return (new Gson().toJson(student));
		}
		
  /**
   * modified by sourav.bhadra
   * changes taken on 27042017**/
		
		@RequestMapping(value = "/inactiveStandard", method = RequestMethod.GET)
		public ModelAndView inactiveStandard(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,@RequestParam("serialId") String standardId,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				logger.info("Inside Method inactiveStandard-GET of AcademicsController");
				Standard  standard = new Standard();
				standard.setStandardId(Integer.parseInt(standardId));
				standard.setUpdatedBy(sessionObject.getUserId());
				String status = academicsService.inactiveStandard(standard);
				String msg = null;
				if(status.equalsIgnoreCase("success")){
					msg = "Deleted SuccessFully";
				}else if (status.equalsIgnoreCase("mapped")){
					msg = "Already Mapped";
				}else{
					msg = "Failed To Delete";
				}
				model.addAttribute("msg", msg);
				model.addAttribute("insertUpdateStatus", status);
			} catch (Exception ce){
				logger.error("Exception in method inactiveStandard-GET of AcademicsController", ce);
			}
			return getStandard(request,response,model);
		}
		
		/**
		 * modified by saurav.bhadra
		 * changes taken on 28032017*/
		
		@RequestMapping(value = "/editTerm", method = RequestMethod.POST)
		public String editTerm(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				Term term,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			try {
				String saveId = request.getParameter("saveId");
				String program = request.getParameter("getProgram");
				String termname = request.getParameter("getTerm");
				String termcode = request.getParameter("termCode"+saveId);
				String termid = request.getParameter("termId"+saveId);
				//System.out.println("program :: "+program+"\tterm :: "+termname+"\ttermcode :: "+termcode+"\ttermID :: "+termid);
				//String saveId;
				term.setTermDetailsId(Integer.parseInt(termid));
				Course courseObj = new Course();
				courseObj.setCourseCode(request.getParameter("course.courseCode"+saveId));
				courseObj.setCourseName(program);
				term.setCourse(courseObj);
				term.setTermCode(termcode);
				term.setTermName(termname);
				term.setUpdatedBy(sessionObject.getUserId());
				/*System.out.println("editTerm() in AcademicsController reached !!...LN3984");
				System.out.println("Term Details in controller :");
				System.out.println("Term Name : "+term.getTermName());
				System.out.println("Term Code: "+term.getTermCode());
				System.out.println("Term Desc: "+term.getTermDesc());
				System.out.println("Academic Year: "+term.getAcademicYear());
				System.out.println("Course Code :"+term.getCourse().getCourseCode());
				System.out.println("Course Name :"+term.getCourse().getCourseName());*/
				
				String updateStatus=academicsService.editTerm(term);
				//System.out.println("insertStatus=="+updateStatus);
				String msg = null;
				if(updateStatus.equalsIgnoreCase("success")){
					msg = "Term Updated Successfully";
				}else if(updateStatus.equalsIgnoreCase("fail")){
					msg = "Failed to update";
				}else{
					msg = "Term Already Exists"; // work from home
				}

				model.addAttribute("insertUpdateStatus", updateStatus);
				model.addAttribute("msg",msg);

			} catch (Exception ce){
				ce.printStackTrace();
				logger.error("Exception in method getUploadResult-GET of AcademicsController", ce);
			}
			return getTerm(request, response, model);
		}
	/*******Changes By 20042017 Kaustav************/
		@RequestMapping(value = "/inactiveExam", method = RequestMethod.GET)
		public ModelAndView inactiveExam(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,@RequestParam("examCode") String examCode,
				@ModelAttribute("sessionObject") SessionObject sessionObject) throws CustomException {
			
			try {
				System.out.println("xx");
				System.out.println(examCode);
				logger.info("Inside Method inactiveTerm-GET of AcademicsController");
				Exam exam=new Exam();
				exam.setExamCode(examCode);
				exam.setUpdatedBy(sessionObject.getUserId());
				String status = academicsService.inactiveExam(exam);
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
				logger.error("Exception in method inactiveStandard-GET of AcademicsController", ce);
			}
			return createExam(request,response,model);
		}
		
	@RequestMapping(value = "/inactiveTerm", method = RequestMethod.GET)
		public String inactiveTerm(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,@RequestParam("termCode") String termCode,
				@ModelAttribute("sessionObject") SessionObject sessionObject) {
			
			try {
				logger.info("Inside Method inactiveTerm-GET of AcademicsController");
				Term term=new Term();
				term.setTermCode(termCode);
				term.setUpdatedBy(sessionObject.getUserId());
				
				String status = academicsService.inactiveTerm(term);
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
				logger.error("Exception in method inactiveStandard-GET of AcademicsController", ce);
			}
			return getTerm(request,response,model);
		}
	/******Added By Sourav21032017*******/
	
	@RequestMapping(value = "/inactiveProgramType", method = RequestMethod.GET)
	public String inactiveProgramType(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam("courseTypeCode") String courseTypeCode,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//System.out.println("within");
		try {
			logger.info("Inside Method inactiveProgramType-GET of AcademicsController");
			//System.out.println("in inactiveProgramType of AcaController...LN4021");
			//System.out.println("CourseTypeCode...LN4022::"+courseTypeCode);
			CourseType  courseType = new CourseType();
			courseType.setCourseTypeCode(courseTypeCode);
			courseType.setUpdatedBy(sessionObject.getUserId());
			//System.out.println("CourseTypeCode...LN4026::"+courseType.getCourseTypeCode());
			String status = academicsService.inactiveProgramType(courseType);
			String msg = null;
			if(status.equalsIgnoreCase("success")){
				msg = "Deleted SuccessFully";
			}else{
				msg = "Failed To Delete";
			}

			model.addAttribute("msg", msg);
			model.addAttribute("insertUpdateStatus", status);
			/**Added by @author Saif.Ali
			 * Date-13-03-2018*/
			if(status.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("DELETE COURSE TYPE DETAILS");
				updateLog.setInsertUpdate("DELETE");
				updateLog.setModule("ACADEMICS");
				updateLog.setUpdatedFor("Course Type :: " + courseTypeCode);
				updateLog.setDescription("Course Type :: " + courseTypeCode + " is deleted");
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 4790 :: AcademicsController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
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
			logger.error("Exception in method inactiveStandard-GET of AcademicsController", ce);
		}
		return getCourseType(request,response,model);
	}
	
	/**
	 * modified by kaustav.sen
	 * changes taken on 18042017**/

	@RequestMapping(value = "/inactiveDeleteCourse", method = RequestMethod.GET)
	public String inactiveDeleteCourse(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("groupName") String groupName,
			
			@ModelAttribute("sessionObject") SessionObject sessionObject) throws CustomException {
		
		try {
			String saveId=request.getParameter("saveId");
			System.out.println(saveId);
			logger.info("Inside Method inactiveDeleteCourse-GET of AcademicsController");
			Course course=new Course();
		//	System.out.println(request.getParameter("subjectGroupName"+groupID).trim());
			course.setCourseCode(groupName);
			//course.setSerialId(Integer.parseInt(groupID));
			course.setUpdatedBy(sessionObject.getUserId());
			
			String status = academicsService.inactiveDeleteCourse(course);
		
			String msg = null;
			if(status.equalsIgnoreCase("success")){
				msg = "Deleted SuccessFully";
			}else{
				msg = "Failed To Delete";
			}
			model.addAttribute("msg", msg);
			model.addAttribute("insertUpdateStatus", status);
			/**Added by @author Saif.Ali
			 * Date-13-03-2018*/
			if(status.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("DELETE SUBJECT DETAILS");
				updateLog.setInsertUpdate("DELETE");
				updateLog.setModule("ACADEMICS");
				updateLog.setUpdatedFor("Subject Name :: " + groupName);
				updateLog.setDescription("Subject Name :: " + groupName + " is deleted");
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 4796 :: AcademicsController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
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
			logger.error("Exception in method inactiveStandard-GET of AcademicsController", ce);
		}
		return getSubjectGroup(request,response,model, sessionObject);
	}
	
	/**
	 * @author naimisha.ghosh
	 * 07062017
	 * **/
	
	
	//Modified By Naimisha 20092017
	@RequestMapping(value = "/setExternalExam", method = RequestMethod.GET)
	public ModelAndView setExternalExam(HttpServletRequest request,
			HttpServletResponse response, ModelMap model
			) {
		String strView="academics/createExternalExam";
		
		try{	
			List<Venue>venueList = academicsService.getVenueListForExam();
			model.addAttribute("venueList", venueList);	
			List<Algorithm>algorithmList = academicsService.getAllAlgorithmList();
			model.addAttribute("algorithmList", algorithmList);	
			List<AdmissionForm> courseList = academicsService.getAllCourseList();
			model.addAttribute("courseList", courseList);	
			//int capacity = academicsService.getStrengthOfStudents(standard, section)
		}
		catch(NullPointerException e){
			logger.error("setExternalExam() In AcademicsController.java: NullPointerException"+ e);
		}catch(Exception e){
			logger.error("setExternalExam() In AcademicsController.java: Exception"+ e);
		}
			
		return new ModelAndView(strView);
	}
	
	
	//Added By Naimisha 20092017
	@RequestMapping(value = "/getTotalNoOfStudentForAProgram", method = RequestMethod.GET)
	public @ResponseBody
	String getTotalNoOfStudentForAProgram(@RequestParam("programCode") String programCode) {
		logger.info("Inside Method getVenueDetailsAgainstVenueCode-GET of AcademicsController");
		int totalNoOfStudents = 0;
		try {
			// totalNoOfStudents = academicsService.getTotalNoOfStudentForAProgram(programCode);
			List<Student>studentList  = academicsService.getStudentListForAProgram(programCode);
			totalNoOfStudents = studentList.size();
			 System.out.println("totalNoOfStudents===="+totalNoOfStudents);
			
		} catch (Exception ce) {
			logger.error("Exception in method getVenueDetailsAgainstVenueCode-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(totalNoOfStudents+""));
	}
	
	/**
	 * naimisha changes 09062017
	 * **/
	//Modified By Naimisha 20092017
	
	
	@RequestMapping(value = "/createExternalExam", method = RequestMethod.POST)
	public ModelAndView createExternalExam(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Exam exam,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
				String msg = null;
				exam.setUpdatedBy(sessionObject.getUserId());
				System.out.println("exam.getExamName()====="+exam.getExamName());
				System.out.println("Algorithm======"+exam.getAlgorithm());
				int venueIndex = Integer.parseInt(request.getParameter("venueIndex"));
				int programIndex = Integer.parseInt(request.getParameter("programIndex"));
				System.out.println("venueIndex=="+venueIndex);
				System.out.println("programIndex==="+programIndex);
				List<Course>courseList = new ArrayList<Course>();
				for(int i=0;i<=programIndex;i++){
					String programCode  = request.getParameter("programName"+i);
					Course course = new Course();
					course.setCourseCode(programCode);
					course.setUpdatedBy(sessionObject.getUserId());
					courseList.add(course);
				}
				List<Venue>venueList = new ArrayList<Venue>();
				for(int j=0;j<=venueIndex;j++){
					String venueCode = request.getParameter("venueCode"+j);
					String availableSeat = request.getParameter("availableSeat"+j);
					String rowNumber = request.getParameter("rowNumber"+j);
					String columnNumber = request.getParameter("columnNumber"+j);
					Venue venue = new Venue();
					venue.setVenueCode(venueCode);
					venue.setAvailableSeat(Integer.parseInt(availableSeat));
					venue.setRowNumber(rowNumber);
					venue.setColumnNumber(columnNumber);
					venue.setUpdatedBy(sessionObject.getUserId());
					venueList.add(venue);
				}
				exam.setCourseList(courseList);
				exam.setVenueList(venueList);
				String updateStatus =  academicsService.insertExternalExamination(exam);
				if(updateStatus .equalsIgnoreCase("success")){
					msg = "External Exam Created SuccessFully";
				}else{
					msg = "Failed To Create External Examination";
				}
			model.addAttribute("updateStatus", updateStatus);
			model.addAttribute("msg",msg);

		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method getUploadResult-GET of AcademicsController", ce);
		}
		return setExternalExam(request, response, model);
	}
	
	@RequestMapping(value = "/getVenueDetailsAgainstVenueCode", method = RequestMethod.GET)
	public @ResponseBody
	String getVenueDetailsAgainstVenueCode(@RequestParam("venueCode") String venueCode) {
		logger.info("Inside Method getVenueDetailsAgainstVenueCode-GET of AcademicsController");
		int venueCapacity = 0;
		try {
			Venue venue = academicsService.getVenueDetailsAgainstVenueCode(venueCode);
			venueCapacity = venue.getAvailableSeat();
		} catch (Exception ce) {
			logger.error("Exception in method getVenueDetailsAgainstVenueCode-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(venueCapacity));
	}
	
	@RequestMapping(value = "/displaySittingArrangement", method = RequestMethod.GET)
	public ModelAndView displaySittingArrangement(HttpServletRequest request,
			HttpServletResponse response, ModelMap model
			) {
		String strView="academics/seatingArrangements";
		
		try{	
			List<Exam>examList = academicsService.getExternalExamList();
			model.addAttribute("examList", examList);	
			List<Algorithm>algorithmList = academicsService.getAllAlgorithmList();
			model.addAttribute("algorithmList", algorithmList);	
		}
		catch(NullPointerException e){
			logger.error("displaySittingArrangement.html() In AcademicsController.java: NullPointerException"+ e);
		}catch(Exception e){
			logger.error("displaySittingArrangement.html() In AcademicsController.java: Exception"+ e);
		}
			
		return new ModelAndView(strView);
	}
	

	@RequestMapping(value = "/getVenueDetailsAgainstExam", method = RequestMethod.GET)
	public @ResponseBody
	String getVenueDetailsAgainstExam(@RequestParam("exam") String exam) {
		logger.info("Inside Method getVenueDetailsAgainstExam-GET of AcademicsController");
		String seatingArrangement = "";
		
		Map<Integer, List<Student>> studentListMap = new HashMap<>();
		List<String> finalStudentList = new ArrayList<String>();
		try {
			Exam examObjForVenue = academicsService.getVenueDetailsAgainstExam(exam);  // venueList For A Exam
			Exam examObjectForCourse = academicsService.getPrograamDetailsAgainstExam(exam); // Program List For A Exam
			
			
			List<Venue> venueList = examObjForVenue.getVenueList();  //gettiing venue list from examObjForVenue
			List<Course>courseList = examObjectForCourse.getCourseList(); // getting program List from examObjectForCourse
			
			/*for(Course course : courseList){
				List<Student>studentList = academicsService.getStudentsListForCourse(course.getCourseCode());  //Fetching student List For Course 
			}*/
			int maxListSize = 0;
			for(int i=0 ; i<courseList.size();i++){
				List<Student> studentList = academicsService.getStudentsListForCourse(courseList.get(i).getCourseCode());  //Fetching student List For Course 
				studentListMap.put(i, studentList);
				if(studentList.size()>maxListSize){
					maxListSize = studentList.size();
				}
			}
			
			for(int j = 0; j<maxListSize ;j++){
				for(int k = 0; k<studentListMap.size();k++){
					try{
						finalStudentList.add(studentListMap.get(k).get(j).getRoll());
					}catch(Exception e){
						finalStudentList.add("None");
					}
					
				}
			}
			/*for (Map.Entry<Integer, List<Student>> entry : studentListMap.entrySet())
			{
			    System.out.println(entry.getKey() + "/" + entry.getValue());
			    List<Student> entry.getKey() = entry.getValue();
			}*/
			
			
			//seatingArrangement = examObjForVenue.getAlgorithm()+";";
			int total_no_of_students = finalStudentList.size();
			for(Venue venue : venueList){
				int venueCapacity = venue.getCapacity();
				//for(Course course : courseList){
					
					/*for(Section section : course.getSectionList()){
						total_no_of_students = total_no_of_students + section.getTotalSeat();
					}*/
					if(total_no_of_students<=venueCapacity){
						//counter = 0;
						seatingArrangement = seatingArrangement + venue.getVenueName()+"#*#"+examObjForVenue.getAlgorithm()+"**";
						String rowNumber = venue.getRowNumber();
						String columnNumber  = venue.getColumnNumber();
						int rowValue = Integer.parseInt(rowNumber);
						int columnValue = Integer.parseInt(columnNumber);
						String algorithm = examObjForVenue.getAlgorithm();
						String[][] seatingArrangements = new String[rowValue][columnValue];
						Seating seating =  null;
						if(algorithm.equalsIgnoreCase("LeftToRightSeating")){
							seating = new LeftToRightSeating();
							seatingArrangements = seating.execute(rowValue, columnValue, examObjForVenue.getExamName(), total_no_of_students,finalStudentList,counter);
						}else if (algorithm.equalsIgnoreCase("RightToLeftSeating")){
							seating = new RightToLeftSeating();
							seatingArrangements = seating.execute(rowValue, columnValue,  examObjForVenue.getExamName(), total_no_of_students,finalStudentList,counter);
						}else if (algorithm.equalsIgnoreCase("ZigzagSeating")){
							seating = new ZigzagSeating();
							seatingArrangements = seating.execute(rowValue, columnValue,  examObjForVenue.getExamName(), total_no_of_students,finalStudentList,counter);
						}else if(algorithm.equalsIgnoreCase("DiagonalLeftToRightSeating")){
							seating = new DiagonalLeftToRightSeating();
							seatingArrangements = seating.execute(rowValue, columnValue,  examObjForVenue.getExamName(), total_no_of_students,finalStudentList,counter);
						}else{
							seating = new SpiralSeating();
						//	seatingArrangements = seating.execute(rowValue, columnValue,  examObjForVenue.getExamName(), total_no_of_students,finalStudentList,counter);
						}
						
						String seats = "";
						for(int rowcount = 0; rowcount < rowValue; rowcount++){
							for(int columncount = 0; columncount < columnValue; columncount++){
								System.out.print(seatingArrangements[rowcount][columncount] + "   ");  
								seatingArrangement = seatingArrangement + seatingArrangements[rowcount][columncount]+":"; 
							}
							seatingArrangement = seatingArrangement + "##";
							System.out.println("");
							System.out.println("");
						}
						seatingArrangement = seatingArrangement + ";";
						counter = total_no_of_students;
					}
					if(total_no_of_students>venueCapacity){
						seatingArrangement = seatingArrangement + venue.getVenueName()+"#*#"+examObjForVenue.getAlgorithm()+"**";
						String rowNumber = venue.getRowNumber();
						String columnNumber  = venue.getColumnNumber();
						int rowValue = Integer.parseInt(rowNumber);
						int columnValue = Integer.parseInt(columnNumber);
						String algorithm = examObjForVenue.getAlgorithm();
						String[][] seatingArrangements = new String[rowValue][columnValue];
						Seating seating =  null;
						if(algorithm.equalsIgnoreCase("LeftToRightSeating")){
							seating = new LeftToRightSeating();
							seatingArrangements = seating.execute(rowValue, columnValue, examObjForVenue.getExamName(), total_no_of_students,finalStudentList,counter);
						}else if (algorithm.equalsIgnoreCase("RightToLeftSeating")){
							seating = new RightToLeftSeating();
							seatingArrangements = seating.execute(rowValue, columnValue,  examObjForVenue.getExamName(), total_no_of_students,finalStudentList,counter);
						}else if (algorithm.equalsIgnoreCase("ZigzagSeating")){
							seating = new ZigzagSeating();
							seatingArrangements = seating.execute(rowValue, columnValue,  examObjForVenue.getExamName(), venueCapacity,finalStudentList,counter);
						}else if(algorithm.equalsIgnoreCase("DiagonalLeftToRightSeating")){
							seating = new DiagonalLeftToRightSeating();
							seatingArrangements = seating.execute(rowValue, columnValue,  examObjForVenue.getExamName(), total_no_of_students,finalStudentList,counter);
						}else{
							seating = new SpiralSeating();
							//seatingArrangements = seating.execute(rowValue, columnValue,  examObjForVenue.getExamName(), total_no_of_students,finalStudentList,counter);
						}
						
						String seats = "";
						for(int rowcount = 0; rowcount < rowValue; rowcount++){
							for(int columncount = 0; columncount < columnValue; columncount++){
								System.out.print(seatingArrangements[rowcount][columncount] + "   ");  
								seatingArrangement = seatingArrangement + seatingArrangements[rowcount][columncount]+":"; 
							}
							seatingArrangement = seatingArrangement + "##";
							System.out.println("");
							System.out.println("");
						}
						total_no_of_students = total_no_of_students - venueCapacity;
						counter = venueCapacity;
						seatingArrangement = seatingArrangement + ";";
					}
				//}
				//seatingArrangement = seatingArrangement + venue.getVenueName();
			}
			
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in method getVenueDetailsAgainstExam-GET of AcademicsController", ce);
		}
		System.out.println("seatingArrangement===\n"+seatingArrangement);
		counter = 0;
		return (new Gson().toJson(seatingArrangement));
	}
	
	
	/**New CBSE System Changes start**/
	
	/**
	 * @author anup.roy
	 * this method displays all exam types created in DB 
	 * no create option here
	 * */
	
	@RequestMapping(value = "/examTypeNew", method = RequestMethod.GET)
	public ModelAndView examTypeNew(HttpServletRequest request,	HttpServletResponse response, ModelMap model) {
		String strView=null;
		List<ExamType> examTypeListFromDB = null;
		try{
			logger.info("In examTypeNew(req,res,model) of AcademicsController.java");
			examTypeListFromDB = academicsService.getExamTypeNew();
			
			if(examTypeListFromDB != null && examTypeListFromDB.size() != 0){
				model.addAttribute("examTypeListFromDB",examTypeListFromDB);
			}
			strView="academics/examTypeNew";
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * @author anup.roy
	 * this method is for creating a new exam
	 * functionality is: to map standard and exam type with term
	 * */
	
	@RequestMapping(value = "/createExamNew", method = RequestMethod.GET)
	public ModelAndView createExamNew(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String strView = null;
		List<Course> courseListFromDB = null;
		List<Term> termList = null;
		List<Exam> newExamsList = null;
		try{
			logger.info("In createExamNew method of AcademicsController.java");
			courseListFromDB = academicsService.getAllCourses();
			model.addAttribute("courseListFromDB", courseListFromDB);
			termList = academicsService.getAllTermList();
			model.addAttribute("termList", termList);
			newExamsList = academicsService.getAllNewExams();
			model.addAttribute("newExamsList", newExamsList);
			strView="academics/createExamNew";
		}
		catch(NullPointerException e){
			logger.error("In createExamNew method in AcademicsController.java"+e);
			e.printStackTrace();
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting exam types w.r.t term*/
	
	@RequestMapping(value = "/getExamTypesForATerm", method = RequestMethod.GET)
	public @ResponseBody
	String getExamTypesForATerm(@RequestParam("term") String term,
								@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("Inside Method getExamTypesForATerm-GET of AcademicsController");
		String examTypes = "";
		try {
			examTypes = academicsService.getExamTypesForATerm(term);
		} catch (Exception ce) {
			logger.error("Exception in method getExamTypesForATerm-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		System.out.println("eXamTypes in controller:"+examTypes);
		return (new Gson().toJson(examTypes));
	}
	
	/**
	 * @author anup.roy
	 * this method is for submit the exam for new pattern
	 * */
	
	@RequestMapping(value = "/submitExamNew", method = RequestMethod.POST)
	public ModelAndView submitExamNew(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									@RequestParam("className") String className,
									@RequestParam("termName") String termName,
									@RequestParam("examType") String examType,
									@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("In submitExamNew method of academicsController.java");
			Exam exam = new Exam();
			if(className != null){
				exam.setStandardCode(className);
				exam.setTermCode(termName);
				exam.setExamTypeName(examType);
				exam.setUpdatedBy(sessionObject.getUserId());
			}
			String insertStatus = academicsService.submitExamNew(exam);	
			if(insertStatus.equalsIgnoreCase("success")){
				model.addAttribute("insertUpdateStatus", insertStatus);	
				model.addAttribute("msg", "Exam created successFully");	
			}else{
				model.addAttribute("insertUpdateStatus", insertStatus);	
				model.addAttribute("msg", "Failed to create exam");
			}
		}
		catch(NullPointerException e){
			logger.error("NullPointerException in submitExamNew in AcademicsController.java"+e);
			e.printStackTrace();
		}catch(Exception e){
			logger.error("Exception in submitExamNew in AcademicsController.java"+e);
			e.printStackTrace();
		}
		return createExamNew( request,response, model);
	}
	
	/**
	 * modified by anup.roy
	 * this method is for view the setup exam marks page
	 * */
	
	@RequestMapping(value = "/setExamMarksNew", method = RequestMethod.GET)
	public String setExamMarksNew(HttpServletRequest request,
								HttpServletResponse response, ModelMap model) {
		String strView = "academics/createExamMarksNew";
		List<Standard> standardList = null;
		try {
			logger.info("Inside Method setExamMarks-GET of AcademicsController");
			standardList = academicsService.getStandardsForSetExamMarks();
			model.addAttribute("standardList", standardList);
			/*termList = academicsService.getAllTermList();
			model.addAttribute("termList", termList);*/
		} catch (Exception ce){
			logger.error("Exception in method setExamMarks-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return strView;
	}

	/**
	 * modified by anup.roy
	 * this method is for getting terms w.r.t a standard
	 * */
	
	@RequestMapping(value = "/getTermForStandard", method = RequestMethod.GET)
	public @ResponseBody
	String getTermForStandard(@RequestParam("standard") String standard) {
		String term = "";
		try {
			logger.info("In getTermForStandard method in AcademicsController.java");
			term = academicsService.getTermForStandard(standard);
			System.out.println("term=="+term);
		} catch (Exception e) {
			logger.error("Exception In getTermForStandard() method of AcademicsController : ",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(term));
	}
	
	
	/**
	 * modified by anup.roy
	 * this method is for getting exams w.r.t standard and term*/
	
	@RequestMapping(value = "/getExamForStandardAndTerm", method = RequestMethod.GET)
	public @ResponseBody
	String getExamForStandardAndTerm(@RequestParam("standard") String standard,
									@RequestParam(value="term",required = false) String term) {
		String exam = "";
		try {
			logger.info("In getExamForStandardAndTerm method of AcademicsController.java");
			Exam examObj = new Exam();
			examObj.setTerm(term);
			examObj.setStatus(standard);
			exam = academicsService.getExamForStandardAndTerm(examObj);
		} catch (Exception e) {
			logger.error("Exception In getExamForStandardAndTerm(examObj) method of AcademicsController : ",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(exam));
	}
		
	/**
	 * modified by anup.roy
	 * this method is for submitting the exam marks in new system*/
	
	@RequestMapping(value = "/editIntoExamMarks", method = RequestMethod.POST)
	public String editIntoExamMarks(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="standard") String standard,
			@RequestParam(value="exam") String exam,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editIntoExamMarks-POST of AcademicsController");
			List<ExamMarks> examMarksList = new ArrayList<ExamMarks>();
				String allSubjects[] = request.getParameterValues("subject");
				for(int subject = 0; subject<allSubjects.length; subject++){
					ExamMarks examMarks = new ExamMarks();
					examMarks.setUpdatedBy(sessionObject.getUserId());
					examMarks.setStandard(standard);
					
					Subject subjectObject = new Subject();
					subjectObject.setSubjectCode(allSubjects[subject]);
					examMarks.setSubject(subjectObject);
					
					Exam examObject = new Exam();
					examObject.setExamCode(exam);
					examMarks.setExam(examObject);
					examMarks.setTheory(Integer.parseInt(request.getParameter(allSubjects[subject]+"theory")));
					examMarks.setTheoryPass(Integer.parseInt(request.getParameter(allSubjects[subject]+"theoryPass")));
					examMarks.setPractical(Integer.parseInt(request.getParameter(allSubjects[subject]+"practical")));
					examMarks.setPracticalPass(Integer.parseInt(request.getParameter(allSubjects[subject]+"practicalPass")));
					examMarks.setTotal(Integer.parseInt(request.getParameter(allSubjects[subject]+"total")));
					examMarks.setPass(Integer.parseInt(request.getParameter(allSubjects[subject]+"pass")));
					
					examMarksList.add(examMarks);
				}
			if(examMarksList.size()!=0){
				String updateStatus=academicsService.editIntoExamMarks(examMarksList);
				model.addAttribute("insertUpdateStatus", updateStatus);
				String msg = null;
				if(updateStatus.equalsIgnoreCase("success")){
					msg = "You've successfully set up the marks";
				}else{
					msg = "Marks set up process failed";
				}
				model.addAttribute("msg", msg);
			}
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method editIntoExamMarks-POST of AcademicsController", ce);
		}
		return setExamMarksNew(request, response, model);
	}
	
	/**
	 * modified by anup.roy
	 * this method is for getting subjects w.r.t standard and exam*/
	
	@RequestMapping(value = "/getSubjectsAndMarksForStandardAndExam", method = RequestMethod.GET)
	public @ResponseBody
	String getSubjectsAndMarksForStandardAndExam(@RequestParam("standard") String standard,
											@RequestParam("exam") String exam) {
		logger.info("Inside Method getSubjectsAndMarksForStandardAndExam-GET of AcademicsController");
		String subjectAndMarks = "";
		try {
			subjectAndMarks = academicsService.getSubjectsAndMarksForStandardAndExam(standard, exam);
		} catch (Exception ce) {
			logger.error("Exception in method getSubjectsAndMarksForStandardAndExam-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return (new Gson().toJson(subjectAndMarks));
	}
	
	/**
	 * modified by anup.roy
	 * this method is for getting exams whrere term is not applicable*/
	
	@RequestMapping(value = "/getExamsForStandard", method = RequestMethod.GET)
	public @ResponseBody
	String getExamsForStandard(@RequestParam("standard") String standard) {
		String exam = "";
		try {
			exam = academicsService.getExamsForStandard(standard);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In getExamsForStandard() method of AcademicsController : ",e);
		}
		return (new Gson().toJson(exam));
	}
	
	@RequestMapping(value = "/setUploadResult", method = RequestMethod.GET)
	public String setUploadResult(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = "academics/createResult";
		try {
			logger.info("Inside Method setUploadResult-GET of AcademicsController");
			
			List<Standard> standardList=commonService.getStandards();
			model.addAttribute("standardList", standardList);
			
	//		List<Exam> examList = backOfficeService.getExamList();
	//		model.addAttribute("examList", examList);
			
			model.addAttribute("loggedInUser", sessionObject.getUserId());
			
		} catch (CustomException ce){
			logger.error("Exception in method setUploadResult-GET of AcademicsController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/getMarksForStudents", method = RequestMethod.GET)
	public @ResponseBody
	String getMarksForStudents(@RequestParam("standard") String standard,
										@RequestParam("exam") String exam,
										@RequestParam("subject") String subject,
										@RequestParam("section") String section,
										@RequestParam("loggedInUser") String loggedInUser) {
		logger.info("Inside Method getMarksForStudents-GET of AcademicsController");
		String subjectAndMarks = "";
		try {
			StudentResult studentResult=new StudentResult();
			studentResult.setStandard(standard);
			studentResult.setExam(exam);
			studentResult.setSubject(subject);
			studentResult.setSection(section);
			subjectAndMarks = academicsService.getMarksForStudents(studentResult, loggedInUser);
		} catch (CustomException ce) {
			ce.printStackTrace();
			logger.error("Exception in method getMarksForStudents-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(subjectAndMarks));
	}
	
	@RequestMapping(value = "/editIntoStudentResult", method = RequestMethod.POST)
	public String editIntoStudentResult(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="standard") String standard,
			@RequestParam(value="section") String section,
			@RequestParam(value="subject") String subject,
			@RequestParam(value="exam") String exam,
			@RequestParam(value="insertUpdate") String insertUpdate,
			@RequestParam(value="rollNumber") String []rollNumber,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editIntoStudentResult-POST of AcademicsController");
			List<StudentResult> studentResultList=new ArrayList<StudentResult>();
			for(int loopControl=0;loopControl<rollNumber.length;loopControl++){
				StudentResult studentResult=new StudentResult();
				studentResult.setUpdatedBy(sessionObject.getUserId());
				studentResult.setSubject(subject);
				studentResult.setStandard(standard);
				studentResult.setSection(section);
				studentResult.setRollNumber(rollNumber[loopControl]);
				studentResult.setExam(exam);
				
				double weightageObtained=0;
				
				String theoryFull=request.getParameter(rollNumber[loopControl]+"theoryTotal");
				studentResult.setTheory(Integer.parseInt(theoryFull.trim()));
				studentResult.setTheoryPass(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"theoryPass").trim()));
				String theoryObtained=request.getParameter(rollNumber[loopControl]+"theory");
				studentResult.setTheoryObtainedChar(theoryObtained);
				//if(!theoryObtained.equalsIgnoreCase("AB"))
					
					//studentResult.setTheoryObtainedDoubble(Double.parseDouble(theoryObtained.trim()));
				//else
					//studentResult.setTheoryObtainedDoubble(-1.0);
				
				String practicalFull=request.getParameter(rollNumber[loopControl]+"practicalTotal");
				studentResult.setPractical(Integer.parseInt(practicalFull.trim()));
				studentResult.setPracticalPass(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"practicalPass").trim()));
				String practicalbtained=request.getParameter(rollNumber[loopControl]+"practical");
				studentResult.setPracticalObtainedChar(practicalbtained);
				/*if(!practicalbtained.equalsIgnoreCase("AB"))
					studentResult.setPracticalObtainedDouble(Double.parseDouble(practicalbtained.trim()));
				else
					studentResult.setPracticalObtainedDouble(-1.0);*/
				
				String totalFull=request.getParameter(rollNumber[loopControl]+"total");
				studentResult.setTotal(Integer.parseInt(totalFull.trim()));
				String totalbtained=request.getParameter(rollNumber[loopControl]+"obtained");
				studentResult.setTotalObtainedChar(totalbtained);
				/*if(!totalbtained.equalsIgnoreCase("AB")){
					studentResult.setDoubleTotalObtained(Double.parseDouble(totalbtained.trim()));
					weightageObtained=Double.parseDouble(totalbtained.trim());
				}else{
					studentResult.setDoubleTotalObtained(-1.0);
				}*/
				weightageObtained=weightageObtained/Integer.parseInt(totalFull.trim());
				studentResult.setWeightageObtained(weightageObtained);
				
				String pass=request.getParameter(rollNumber[loopControl]+"totalPass");
				studentResult.setPass(Integer.parseInt(pass.trim()));
				String passFail=request.getParameter(rollNumber[loopControl]+"passfail");
				studentResult.setPassFail(passFail);
				
				studentResultList.add(studentResult);
			}
			if(studentResultList.size()!=0){
				String updateStatus=academicsService.editStudentResult(studentResultList, insertUpdate);
				model.addAttribute("insertUpdateStatus", updateStatus);
				if(updateStatus.equalsIgnoreCase("Update Successful")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("STUDENT RESULT");
					updateLog.setInsertUpdate(insertUpdate);
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor(standard+"-"+section+"-"+subject+"-"+exam);
					updateLog.setDescription(subject+" Marks Was Updated For Standard "+standard+"("+section+") For Exam : "+exam);
					commonService.insertIntoActivityLog(updateLog);
				}
			}
		} catch (CustomException ce){
			logger.error("Exception in method editIntoStudentResult-POST of AcademicsController", ce);
		}
		return setUploadResult(request, response, model, sessionObject);
	}
	
	@RequestMapping(value = "/getAllCoScholasticResultList", method = RequestMethod.GET)
	public String getAllCoScholasticResultList(HttpServletRequest request,
						HttpServletResponse response,
						ModelMap model) {
		String strView = "academics/listCoScholasticResultNew";
		try {
			logger.info("Inside Method getAllCoScholasticResultList-GET of AcademicsController");
			
			List<CoScholasticResult> resultStatusList = academicsService.getCoScholasticResultList();
			model.addAttribute("resultStatusList", resultStatusList);			
		} catch (CustomException ce){
			logger.error("Exception in method getAllCoScholasticResultList-GET of AcademicsController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/createCoScholasticResultNew", method = RequestMethod.GET)
	public String createCoScholasticResultNew(HttpServletRequest request,
						HttpServletResponse response,
						ModelMap model,
						@ModelAttribute("sessionObject") SessionObject sessionObject
						//@RequestParam("standard") String standard,
						//@RequestParam("section") String section
						) {
		String strView = "academics/createCoScholasticResultNew";
		try {
			logger.info("Inside Method createCoScholasticResultNew-GET of AcademicsController");
	
			
			List<Standard> standardList=commonService.getStandards();
			model.addAttribute("standardList", standardList);
			
	//		List<Exam> examList = backOfficeService.getExamList();
	//		model.addAttribute("examList", examList);
			
			model.addAttribute("loggedInUser", sessionObject.getUserId());
			
			List<DescriptiveIndicatorSkill> descriptiveIndicatorSkillList = academicsService.getDescriptiveIndicatorHeadListNew();
			model.addAttribute("descriptiveIndicatorSkillList", descriptiveIndicatorSkillList);
			
		} catch (Exception ce){
			logger.error("Exception in createCoScholasticResultNew() of AcademicsController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/saveCoScholasticResultNew", method = RequestMethod.POST)
	public String saveCoScholasticResultNew(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									@RequestParam(value="standard") String standard,
									@RequestParam(value="section") String section,
									/*@RequestParam(value="name") String name,*/
									@RequestParam(value="studentName") String studentName,
									@RequestParam(value="term") String term,
									@RequestParam(value="skill") String[] skill,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method saveCoScholasticResultNew-POST of AcademicsController");
			String roleAndName[] = studentName.split("-");
			if(null != skill && skill.length != 0){
				List<CoScholasticResult> studentList = new ArrayList<CoScholasticResult>();
				for(int skillCount = 0; skillCount<skill.length; skillCount++){
					//String[] head = request.getParameterValues(skill[skillCount]+"head");
					//if(null != head && head.length != 0){
						//for(int headCount = 0; headCount<head.length; headCount++){
							CoScholasticResult student = new CoScholasticResult();
							student.setUpdatedBy(sessionObject.getUserId());
							student.setRollNumber(roleAndName[0]);
							student.setSection(section);
							student.setStandard(standard);
							student.setName(roleAndName[1]);
							student.setStatus(term);
							student.setSkill(skill[skillCount]);
						
							student.setGrade(request.getParameter(skill[skillCount]+"Grade"));
							
							
							studentList.add(student);
						
				}
				String insertStatus=academicsService.saveCoScholasticResultNew(studentList);
				model.addAttribute("insertUpdateStatus", insertStatus);
				if(insertStatus.equalsIgnoreCase("Insert Successful")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("STUDENT RESULT");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor(roleAndName[0]);
					updateLog.setDescription("Co Scholastics Result Was Inserted For "+roleAndName[1]+"("+roleAndName[0]+")");
					commonService.insertIntoActivityLog(updateLog);
				}			
			}else{
				logger.info("Student Or Section Not Found To Update");
			}
		} catch (Exception ce){
			logger.error("Exception in method saveCoScholasticResultNew-POST of AcademicsController", ce);
		}
		return createCoScholasticResultNew(request, response, model,sessionObject);
	}
	
	/**
	 * @author anup.roy
	 * this method is for view new grading system page**/
	
	@RequestMapping(value = "/getGradingSystemNew", method = RequestMethod.GET)
	public String getGradingSystemNew(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "academics/createGradingSystemNew";
		try {
			logger.info("Inside Method getGradingSystem-GET of AcademicsController");
			
			List<Standard> standardList=academicsService.getStandardsForSetExamMarks();
			model.addAttribute("standardList", standardList);
			
		} catch (Exception ce){
			logger.error("Exception in method getGradingSystem-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return strView;
	}
	
	/*
	 * @author vinod.singh
	 * Updates Grading System
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/editGradingSystemNew", method = RequestMethod.POST)
	public String editGradingSystemNew(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="standard") String standard,
			@RequestParam(value="Srange") String []Srange,
			@RequestParam(value="Crange") String []Crange,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editGradingSystem-POST of AcademicsController");
			List<GradingSystem> gradingSystemList=new ArrayList<GradingSystem>();
			if(null!=Srange && Srange.length!=0){
				for(int range=0;range<Srange.length;range++){
					GradingSystem gradingSystem=new GradingSystem();
					gradingSystem.setStandard(standard);
					gradingSystem.setRange(Srange[range]);
					if(null!=request.getParameter("Sgrade"+Srange[range]) && request.getParameter("Sgrade"+Srange[range]).length()!=0)
						gradingSystem.setGrade(request.getParameter("Sgrade"+Srange[range]));
					
					gradingSystem.setType("SCHOLASTIC");
					gradingSystem.setUpdatedBy(sessionObject.getUserId());
					
					gradingSystemList.add(gradingSystem);
				}
			}
			if(null!=Crange && Crange.length!=0){
				for(int range=0;range<Crange.length;range++){
					GradingSystem gradingSystem=new GradingSystem();
					gradingSystem.setStandard(standard);
					gradingSystem.setRange(Crange[range]);
					gradingSystem.setGrade(request.getParameter("Cgrade"+Crange[range]));
					//gradingSystem.setGradePoint(request.getParameter("Cpoint"+Crange[range]));
					gradingSystem.setType("CO-SCHOLASTIC");
					gradingSystem.setUpdatedBy(sessionObject.getUserId());
					
					gradingSystemList.add(gradingSystem);
				}
			}
			
			if(gradingSystemList.size()!=0){
				String updateStatus=academicsService.editGradingSystemNew(gradingSystemList);
				model.addAttribute("insertUpdateStatus", updateStatus);
			}
		} catch (Exception ce){
			logger.error("Exception in method editGradingSystem-POST of AcademicsController", ce);
		}
		return getGradingSystemNew(request, response, model);
	}
	
	/* 
	 * Gets Subjects For Standard
	 */
	@RequestMapping(value = "/getGradingSystemForStandardNew", method = RequestMethod.GET)
	public @ResponseBody
	String getGradingSystemForStandardNew(@RequestParam("standard") String standard) {
		logger.info("Inside Method getGradingSystemForStandardNew-GET of AcademicsController");
		String gradingSystem = "";
		try {
			gradingSystem = academicsService.getGradingSystemForStandardNew(standard);
		} catch (Exception ce) {
			logger.error("Exception in method getGradingSystemForStandardNew-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(gradingSystem));
	}
	
	@RequestMapping(value = "/getCoScolasticResultAgainsRollNumber", method = RequestMethod.GET)
	public @ResponseBody
	String getCoScolasticResultAgainsRollNumber(@RequestParam("rollName") String rollName,
			@RequestParam("loggedInUser") String loggedInUser,
			@RequestParam("term") String term) {
		logger.info("Inside Method getCoScolasticResultAgainsRollNumber-GET of AcademicsController");
		
		
		String result = "";
		try {
			String roleAndName[] = rollName.split("-");
			result = academicsService.getCoScolasticResultAgainsRollNumberNew(roleAndName[0],loggedInUser,term);		
			System.out.println("result==="+result);
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in method getCoScolasticResultAgainsRollNumber-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(result));
	}
	
	/**
	 * modified by anup.roy
	 * this method is for view upload result page*/
	
	@RequestMapping(value = "/setUploadResultNew", method = RequestMethod.GET)
	public String setUploadResultNew(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = "academics/createResultNew";
		try {
			logger.info("Inside Method setUploadResult-GET of AcademicsController");
			
			List<Standard> standardList = academicsService.getStandardsForSetExamMarks();
			model.addAttribute("standardList", standardList);
			
			model.addAttribute("loggedInUser", sessionObject.getUserId());
		} catch (Exception ce){
			logger.error("Exception in method setUploadResult-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return strView;
	}
	
	/**
	 * modified by anup.roy
	 * this method is for submit the marks of a student's result
	 * */
	
	@RequestMapping(value = "/editIntoStudentResultNew", method = RequestMethod.POST)
	public String editIntoStudentResultNew(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="standard") String standard,
			@RequestParam(value="section") String section,
			@RequestParam(value="subject") String subject,
			@RequestParam(value="exam") String exam,
			@RequestParam(value="insertUpdate") String insertUpdate,
			@RequestParam(value="rollNumber") String []rollNumber,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editIntoStudentResult-POST of AcademicsController");
			List<StudentResult> studentResultList=new ArrayList<StudentResult>();
			for(int loopControl=0;loopControl<rollNumber.length;loopControl++){
				StudentResult studentResult=new StudentResult();
				studentResult.setUpdatedBy(sessionObject.getUserId());
				studentResult.setSubject(subject);
				studentResult.setStandard(standard);
				studentResult.setSection(section);
				studentResult.setRollNumber(rollNumber[loopControl]);
				studentResult.setExam(exam);
				
				double weightageObtained=0;
				
				String theoryFull=request.getParameter(rollNumber[loopControl]+"theoryTotal");
				studentResult.setTheory(Integer.parseInt(theoryFull.trim()));
				studentResult.setTheoryPass(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"theoryPass").trim()));
				String theoryObtained=request.getParameter(rollNumber[loopControl]+"theory");
				studentResult.setTheoryObtainedChar(theoryObtained);
				//if(!theoryObtained.equalsIgnoreCase("AB"))
					
					//studentResult.setTheoryObtainedDoubble(Double.parseDouble(theoryObtained.trim()));
				//else
					//studentResult.setTheoryObtainedDoubble(-1.0);
				
				String practicalFull=request.getParameter(rollNumber[loopControl]+"practicalTotal");
				studentResult.setPractical(Integer.parseInt(practicalFull.trim()));
				studentResult.setPracticalPass(Integer.parseInt(request.getParameter(rollNumber[loopControl]+"practicalPass").trim()));
				String practicalbtained=request.getParameter(rollNumber[loopControl]+"practical");
				studentResult.setPracticalObtainedChar(practicalbtained);
				/*if(!practicalbtained.equalsIgnoreCase("AB"))
					studentResult.setPracticalObtainedDouble(Double.parseDouble(practicalbtained.trim()));
				else
					studentResult.setPracticalObtainedDouble(-1.0);*/
				
				String totalFull=request.getParameter(rollNumber[loopControl]+"total");
				studentResult.setTotal(Integer.parseInt(totalFull.trim()));
				String totalbtained=request.getParameter(rollNumber[loopControl]+"obtained");
				studentResult.setTotalObtainedChar(totalbtained);
				/*if(!totalbtained.equalsIgnoreCase("AB")){
					studentResult.setDoubleTotalObtained(Double.parseDouble(totalbtained.trim()));
					weightageObtained=Double.parseDouble(totalbtained.trim());
				}else{
					studentResult.setDoubleTotalObtained(-1.0);
				}*/
				weightageObtained=weightageObtained/Integer.parseInt(totalFull.trim());
				studentResult.setWeightageObtained(weightageObtained);
				
				String pass=request.getParameter(rollNumber[loopControl]+"totalPass");
				studentResult.setPass(Integer.parseInt(pass.trim()));
				String passFail=request.getParameter(rollNumber[loopControl]+"passfail");
				studentResult.setPassFail(passFail);
				
				studentResultList.add(studentResult);
			}
			if(studentResultList.size()!=0){
				String updateStatus=academicsService.editStudentResultNew(studentResultList, insertUpdate);
				model.addAttribute("insertUpdateStatus", updateStatus);
				if(updateStatus.equalsIgnoreCase("Update Successful")){
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("STUDENT RESULT");
					updateLog.setInsertUpdate(insertUpdate);
					updateLog.setModule("ACADEMICS");
					updateLog.setUpdatedFor(standard+"-"+section+"-"+subject+"-"+exam);
					updateLog.setDescription(subject+" Marks Was Updated For Standard "+standard+"("+section+") For Exam : "+exam);
					commonService.insertIntoActivityLog(updateLog);
				}
				
				//PRAD MAY 31 2018
				//CALL to the WEBIQ and WRITE to the WEBIQ TRANSACTION DB TABLE
				if(updateStatus.equalsIgnoreCase("Update Successful") && isWebIQAvailable.equalsIgnoreCase("true")){
					JSONObject jsonObj = new JSONObject();
					
					jsonObj.put("username",portalUserName);
					jsonObj.put("password",portalPassWord);
					jsonObj.put("standard",studentResultList.get(0).getStandard());
					jsonObj.put("section",studentResultList.get(0).getSection());
					jsonObj.put("exam",studentResultList.get(0).getExam());
					jsonObj.put("subject",studentResultList.get(0).getSubject());
					jsonObj.put("academicYear","2017-2018");
					
					JSONArray studentsArray = new JSONArray();
					for(StudentResult sr :studentResultList){
						JSONObject studentsJson = new JSONObject();
						studentsJson.put("rollNumber", sr.getRollNumber());
						studentsJson.put("name", sr.getName());
						studentsJson.put("theoryTotal", sr.getTheory());
						if(null != sr.getPractical()){
							studentsJson.put("practicalTotal", sr.getPractical());
						}else{
							studentsJson.put("practicalTotal","NA");
						}
						studentsJson.put("theoryObtained", sr.getTheoryObtainedChar());
						if(null != sr.getPracticalObtainedChar()){
							studentsJson.put("practicalObtained", sr.getPracticalObtainedChar());	
						}else{
							studentsJson.put("practicalObtained", "NA");	
						}
						studentsJson.put("status", sr.getPassFail());
						studentsArray.put(studentsJson);
					}
					jsonObj.put("students", studentsArray);
					
					System.out.println(jsonObj.toString());
					final String uri = sendExamMarksOfCadet;
					System.out.println("URI:::"+uri);
					URL url = new URL(uri);
					HttpURLConnection connection = (HttpURLConnection)url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestProperty("Content-Type", "application/json");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					connection.setRequestMethod("POST");
					OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
					out.write(jsonObj.toString());
					out.close();
					
					String json_response = "";
					InputStreamReader in = new InputStreamReader(connection.getInputStream());
					BufferedReader br = new BufferedReader(in);
					String text = "";
					while((text = br.readLine())!= null){
						json_response += text;
					}
					System.out.println("JSON response:::"+ json_response);
					
					if((!json_response.isEmpty())){
						JSONObject object = new JSONObject(json_response);
						int statusFromJsonResponse = (int)object.get("status");
						
						WebIQTransaction webIQTran= null;
						
						if(statusFromJsonResponse==200){
							//If call to the API is successful, then insert into the webiq_transaction_details table 
							webIQTran = new WebIQTransaction();
							webIQTran.setUpdatedBy(sessionObject.getUserId());
							webIQTran.setUri(sendExamMarksOfCadet);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(true);
						}else{
							//If Failure then also insert into the webiq_transaction_details table
							webIQTran = new WebIQTransaction();
							webIQTran.setUpdatedBy(sessionObject.getUserId());
							webIQTran.setUri(sendExamMarksOfCadet);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(false);
						}
					}
	
				}
				//PRAD ENDS
			}
		} catch (CustomException ce){
			logger.error("Exception in method editIntoStudentResult-POST of AcademicsController", ce);
			ce.printStackTrace();
		} catch (Exception e){
			logger.error("Exception in method editIntoStudentResult-POST of AcademicsController", e);
			e.printStackTrace();
		}
		return setUploadResultNew( request, response,  model,sessionObject);
	}
	
	/***
	 * modified by anup.roy
	 * this method is for get students marks */
	
	@RequestMapping(value = "/getMarksForStudentsNew", method = RequestMethod.GET)
	public @ResponseBody
	String getMarksForStudentsNew(@RequestParam("standard") String standard,
										@RequestParam("exam") String exam,
										@RequestParam("subject") String subject,
										@RequestParam("section") String section,
										@RequestParam("loggedInUser") String loggedInUser) {
		logger.info("Inside Method getMarksForStudents-GET of AcademicsController");
		String subjectAndMarks = "";
		try {
			StudentResult studentResult=new StudentResult();
			studentResult.setStandard(standard);
			studentResult.setExam(exam);
			studentResult.setSubject(subject);
			studentResult.setSection(section);
			subjectAndMarks = academicsService.getMarksForStudentsNew(studentResult, loggedInUser);
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in method getMarksForStudents-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(subjectAndMarks));
	}
	
	/**New CBSE System end**/
	

	/*Added By ranita.sur on 03082017 for getting the strength of Student*/
	@RequestMapping(value = "/getStrengthOfStudents", method = RequestMethod.GET)
	public @ResponseBody
	String getStrengthOfStudents(@RequestParam("standard") String standard,@RequestParam("section") String section) {
		String strengthDetails="";
		try {
			strengthDetails = academicsService.getStrengthOfStudents(standard,section); //System.out.println("LN961...Controller :: "+vendorLedger);
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(strengthDetails));
	}
	
	
	//******Added By Naimisha 16042018******//
	@RequestMapping(value = "/createClassTeacher", method = RequestMethod.GET)
	public String createClassTeacher(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = "academics/createClassTeacher";
		try {
			logger.info("Inside Method createClassTeacher-GET of AcademicsController");
			
			List<Standard> standardList = academicsService.getStandardsForSetExamMarks();
			model.addAttribute("standardList", standardList);
			
			List<StudentResult>classTeacherList = academicsService.getAllClassTeacherList();
			model.addAttribute("classTeacherList", classTeacherList);
			
		} catch (Exception ce){
			logger.error("Exception in method createClassTeacher-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return strView;
	}
	
	@RequestMapping(value = "/createClassTeacher", method = RequestMethod.POST)
	public String createClassTeacherMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Standard standard,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = "academics/createClassTeacher";
		try {
			logger.info("Inside Method createClassTeacherMapping-POST of AcademicsController");
			
			standard.setUpdatedBy(sessionObject.getUserId());
			String status = academicsService.createClassTeacher(standard);
			String msg = "";
			model.addAttribute("status", status);
			if(status.equalsIgnoreCase("success")){
				msg = "Successfully Created";
			}else if(status.equalsIgnoreCase("fail")){
				msg = "Failed to Create Successfully"; 
			}
			
			model.addAttribute("msg", msg);
		} catch (Exception ce){
			logger.error("Exception in method createClassTeacherMapping-POST of AcademicsController", ce);
			ce.printStackTrace();
		}
		return createClassTeacher(request,response,model,sessionObject);
	}
	
	/**
	 * @author saikat.das
	 * this method is for integrating with exam routine*/
	
	@RequestMapping(value = "/displayExamRoutine", method = RequestMethod.GET)
	public ModelAndView displayExamRoutine(HttpServletRequest request,
			HttpServletResponse response, ModelMap model
			) {
		String strView="academics/displayExamRoutine";
		try{	
			List<Exam>examList = academicsService.getExternalExamList();
			model.addAttribute("examList", examList);	
			/*List<Algorithm>algorithmList = academicsService.getAllAlgorithmList();
			model.addAttribute("algorithmList", algorithmList);	*/
		}
		catch(NullPointerException e){
			logger.error("displayExamRoutine.html() In AcademicsController.java: NullPointerException"+ e);
			e.printStackTrace();
		}catch(Exception e){
			logger.error("displayExamRoutine.html() In AcademicsController.java: Exception"+ e);
			e.printStackTrace();
		}
		return new ModelAndView(strView);
	}
	
	/**
	 * @author saikat.das
	 * */
	
	@RequestMapping(value = "/getProgramAndExamDetailsAgainstExam", method = RequestMethod.GET)
	public @ResponseBody
	String getProgramAndExamDetailsAgainstExam(@RequestParam("exam") String exam) {
		logger.info("Inside Method getProgramAndExamDetailsAgainstExam-GET of AcademicsController");
		StringBuffer sb = new StringBuffer();
		String strGridData = null;
		List<String> dateStringList = new ArrayList<String>();
		List<Course>courseList =null;
		try {
			String examFromRoutine = academicsService.getExamNameFromExamTableTimeGrid(exam);
			System.out.println("examFromRoutine=="+examFromRoutine);
			if(null == examFromRoutine){
				courseList = academicsService.getProgramListAndSemeterForExamRoutine(exam);
				String startDate = courseList.get(0).getCourseStartTime();
				String endDate = courseList.get(0).getCourseEndTime();
				String startTime = courseList.get(0).getStartTime();
				String endTime = courseList.get(0).getEndTime();
				System.out.println("startDate=="+startDate);
				System.out.println("endDate=="+endDate);
				String workingDate = startDate;
				for(Course course : courseList){
					List<Subject>subjectList = academicsService.getSubjectForCourseAndTermForExamRoutine(course);
					course.setSubjectList(subjectList);
				}
				//List<String>dateStringList = new ArrayList<String>();
				
				do{
					Calendar cal = Calendar.getInstance();
					Date sdate = null;
					try {
						sdate = new SimpleDateFormat("dd/MM/yyyy").parse(workingDate);
					} catch (Exception e) {
						e.printStackTrace();
					}
					cal.setTime(sdate);
					int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
					if(day_of_week != 1){
						dateStringList.add(workingDate);
					}
					workingDate = incrementDate(workingDate);
				}while(getEpochTime(workingDate) <= getEpochTime(endDate));
				for(Course course : courseList){
					//routineArrangement = routineArrangement + course.getCourseName()+"*#*";
					List<Subject>courseWiseSubjectList = course.getSubjectList();
					for(Subject subject : courseWiseSubjectList){
						System.out.println("index====="+courseWiseSubjectList.indexOf(subject));
						subject.setStatus(dateStringList.get(courseWiseSubjectList.indexOf(subject)));
						subject.setDesc(exam);
					}
				}
				String insertStatus = academicsService.insertIntoExamTableTimeGrid(courseList);
				for(Course course : courseList){
					List<Student> studentList = academicsService.getStudentListForAProgram(course.getCourseName());
					List<Subject>courseWiseSubjectList = course.getSubjectList();
					for(Student student : studentList){
						for(Subject subject : courseWiseSubjectList){
							String startDateVal = dateStringList.get(courseWiseSubjectList.indexOf(subject));
							String insertValue = insertIntoMyEventsForExamRoutine(startDateVal,startDateVal,startTime,endTime,student.getUserId(),subject.getSubjectName());
						}
					}
				}
				List<RoutineTableGridData> gridDataList = academicsService.getAllRoutineTableGridDataAgainstExam(exam);
				for(RoutineTableGridData gridData : gridDataList){
			    	sb.append(gridData.getGridId() + "-" + gridData.getGridData() + "|");
			    }
				sb.append("*");
				for(String dateString : dateStringList){
					sb.append(dateString+"#");
				}
				sb.append("*");
				for(Course course : courseList){
					sb.append(course.getCourseName()+"@");
				}
				strGridData = sb.toString();
			}else{
				courseList = academicsService.getProgramListAndSemeterForExamRoutine(exam);
				String startDate = courseList.get(0).getCourseStartTime();
				String endDate = courseList.get(0).getCourseEndTime();
				System.out.println("startDate=="+startDate);
				System.out.println("endDate=="+endDate);
				String workingDate = startDate;
				do{
					Calendar cal = Calendar.getInstance();
					Date sdate = null;
					try {
						sdate = new SimpleDateFormat("dd/MM/yyyy").parse(workingDate);
					} catch (Exception e) {
						e.printStackTrace();
					}
					cal.setTime(sdate);
					int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
					//System.out.println(day_of_week);
					if(day_of_week != 1){
						dateStringList.add(workingDate);
					}
					workingDate = incrementDate(workingDate);
				}while(getEpochTime(workingDate) <= getEpochTime(endDate));
				List<RoutineTableGridData> gridDataList = academicsService.getAllRoutineTableGridDataAgainstExam(exam);
				for(RoutineTableGridData gridData : gridDataList){
			    	sb.append(gridData.getGridId() + "-" + gridData.getGridData() + "|");
			    }
				sb.append("*");
				for(String dateString : dateStringList){
					sb.append(dateString+"#");
				}
				sb.append("*");
				for(Course course : courseList){
					sb.append(course.getCourseName()+"@");
				}
				strGridData = sb.toString();
			}
			System.out.println("strGridData=="+strGridData);
		} catch (Exception ce) {
			logger.error("Exception in method getProgramAndExamDetailsAgainstExam-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return strGridData.substring(0, strGridData.length()-1);
	}
	
	/**
	 * @author saikat.das
	 * */
	
	private String incrementDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 1);  // number of days to add
		return sdf.format(c.getTime()); 
	}
	
	private long getEpochTime(String date){
		Long millis = null;
		try {
		    millis = new SimpleDateFormat("dd/MM/yyyy").parse(date).getTime();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return millis;
	}
	
	String insertIntoMyEventsForExamRoutine(String startDate, String endDate, String startTime,
			 String endTime, String userId,String subject) {
		
		try {
		
		logger.info("insertIntoMyEventsForExamRoutine() In AcademicsController.java");
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
		java.sql.ResultSet result = null;
		
		int startHr = 0;
		int startMin = 0;
		String[] startTimeArr = startTime.split(" ");
		String[] startHrAndMin = startTimeArr[0].split(":");
		if(startTimeArr[1].equalsIgnoreCase("PM")){
			startHr = Integer.parseInt(startHrAndMin[0])+12;
			startMin = Integer.parseInt(startHrAndMin[1]);
		}else{
			startHr = Integer.parseInt(startHrAndMin[0]);
			startMin = Integer.parseInt(startHrAndMin[1]);
		}
		
		int endHr = 0;
		int endMin = 0;
		String[] endTimeArr = endTime.split(" ");
		String[] endHrAndMin = endTimeArr[0].split(":");
		if(endTimeArr[1].equalsIgnoreCase("PM")){
			endHr = Integer.parseInt(endHrAndMin[0])+12;
			endMin = Integer.parseInt(endHrAndMin[1]);
		}else{
			endHr = Integer.parseInt(endHrAndMin[0]);
			endMin = Integer.parseInt(endHrAndMin[1]);
		}
		
		String[] strtDateArr = startDate.split("/");
		String[] endDateArr = endDate.split("/");
		/*String startMonth = convertMonthToIntegerValue(startTimeArr[1]);
		String[] startArr = startTimeArr[3].split(":");
		
		String[] endTimeArr = endTime.split(" ");
		String endMonth = convertMonthToIntegerValue(endTimeArr[1]);
		String[] endArr = endTimeArr[3].split(":");*/
		
		Calendar cal = Calendar.getInstance();
		Date sdate = null;
		try {
			sdate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cal.setTime(sdate);
		
		
		/*java.util.Calendar startDateValue = new GregorianCalendar();
		startDateValue.set(java.util.Calendar.MONTH, java.util.Calendar.MAY);
		startDateValue.set(java.util.Calendar.DAY_OF_MONTH, 16);
		startDateValue.set(java.util.Calendar.YEAR, 2017);
		startDateValue.set(java.util.Calendar.HOUR_OF_DAY, startHr);
		startDateValue.set(java.util.Calendar.MINUTE, startMin);
		//startDateValue.set(java.util.Calendar.SECOND, 0);
*/		
		DHXEvent icsEvent = new DHXEvent();
		
		java.util.Calendar calendar1 = java.util.Calendar.getInstance();
		calendar1.clear();
		calendar1.set(Integer.parseInt(strtDateArr[2]),Integer.parseInt(strtDateArr[1])-1, Integer.parseInt(strtDateArr[0]), startHr, startMin, 00);
		Date start = calendar1.getTime();
						
		java.util.Calendar calendar2 = java.util.Calendar.getInstance();
		calendar2.clear();
		calendar2.set(Integer.parseInt(endDateArr[2]),Integer.parseInt(endDateArr[1])-1, Integer.parseInt(endDateArr[0]), endHr, endMin, 00);
		Date end = calendar2.getTime();
		
		
		icsEvent.setStart_date(start);
		icsEvent.setEnd_date(end);
		//icsEvent.setText(mailBody);
		
		String query = null;
		String start_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
		                            format(icsEvent.getStart_date());
		String end_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
		                          format(icsEvent.getEnd_date());
		
		
		query = "INSERT INTO my_events (my_events_usrid, my_events_start_date, my_events_end_date, my_events_desc)"+ 
					"VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, userId);
			ps.setString(2, start_date);
			ps.setString(3, end_date);
			ps.setString(4, subject);
		
		if (ps!=null) {
			ps.executeUpdate();
			result = ps.getGeneratedKeys();
			if (result.next()) {
				icsEvent.setId(result.getInt(1));
			}
		}
		//EmailDetails emailDetailsObj = new EmailDetails();
		//emailDetailsObj.setEmailDetailsCode(emailCode);
		
		//List<EmailDetails> emailDetailsList =  new ArrayList<EmailDetails>();
		//emailDetailsList.add(emailDetailsObj);
		//List<EmailDetails> emailList = commonService.inactiveEmailFromInBox(emailDetailsList);
		
		} catch (NullPointerException e) {
		e.printStackTrace();
		logger.error("insertIntoMyEventsForExamRoutine() In AcademicsController.java: NullPointerException"
				+ e);
		} catch (Exception e) {
		e.printStackTrace();
		logger.error("insertIntoMyEventsForExamRoutine() In AcademicsController.java: Exception"
				+ e);
		}
		return  null;
		//return "common/sentEmailDetails";
	}
	
	@RequestMapping(value = "/insertRoutineGridData", method = RequestMethod.GET)
	public  @ResponseBody String insertRoutineGridData(@RequestParam("cellid") String strCellId,
														 @RequestParam("celldata") String strCellData) {

		String strStatus = null;
		try {
			TimeTableGridData timeTableGridData = new TimeTableGridData();
			timeTableGridData.setGridId(strCellId);
			timeTableGridData.setGridData(strCellData);
			if(null != timeTableGridData.getGridId() && null != timeTableGridData.getGridData()){
				TimeTableGridData timeTableGridDataDB = academicsService.getRoutineTableGridData(timeTableGridData);
				
				if(null != timeTableGridDataDB){
					academicsService.updateRoutineTableGridData(timeTableGridData);
					strStatus = "Updated Successfully";
				}else{
					academicsService.insertRoutineTableGridData(timeTableGridData);
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
	
//Added by naimisha 05062018
	
	
	
	@RequestMapping(value = "/createEvent", method = RequestMethod.GET)
	public String createEvent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "academics/createEvent";
		
		try {
			logger.info("Inside Method createEvent-GET of AcademicsController");
			List<SchoolEvent> schoolEventList=academicsService.getSchoolEventList();
			model.addAttribute("schoolEventList", schoolEventList);
			
		} catch (Exception e){
			logger.error("Exception in Method createStandard-GET of AcademicsController", e);
			e.printStackTrace();
		}
		return strView;
	}
	
	@RequestMapping(value = "/submitEvent", method = RequestMethod.POST)
	public String submitEvent(HttpServletRequest request,
							  HttpServletResponse response, 
							  ModelMap model,
							  SchoolEvent schoolEvent,
							  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = "academics/createEvent";
		String insertStatus = null;
		try {
			logger.info("In submitEvent() of AcademicsController.java");
			System.out.println("InCharge :"+schoolEvent.getEventIncharge());
			AcademicYear academicYear = commonService.getCurrentAcademicYear();
			schoolEvent.setUpdatedBy(sessionObject.getUserId());
			
			//PRAD JUNE 10 2018
			String employeeName = erpService.getEventEmployeeName(schoolEvent.getEventIncharge());
			System.out.println("Employee Name: "+employeeName);
			
			String status = academicsService.insertSchoolEvent(schoolEvent);
			String msg="";
			if(status.equalsIgnoreCase("success")){
				msg = "School Event Created Successfully.";
			}else if(status.equalsIgnoreCase("fail")){
				msg = "Failed to Create School Event.";
			}
			
			if(status.equals("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("SCHOOL EVENT");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("ACADEMICS");
				updateLog.setUpdatedFor("STUDENTS AND PARENTS");
				updateLog.setDescription("A New School Event Was Inserted With School Event Id "+schoolEvent.getSchoolEventObjectId());
				commonService.insertIntoActivityLog(updateLog);
			}
			
			//PRAD JUNE 10 2018
			if(status.equals("success") && isWebIQAvailable.equalsIgnoreCase("true")){
				//Create the JSON for Event
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("username",portalUserName);
				jsonObj.put("password",portalPassWord);
				jsonObj.put("academicsSession", academicYear.getAcademicYearName());
				
				//Create Array Object for Events
				//Please note for now there would be only one event POST
				JSONArray jsonEvents = new JSONArray();
				
				JSONObject jsonEvent = new JSONObject();
				jsonEvent.put("eventName", schoolEvent.getEventName());
				jsonEvent.put("eventDescription", schoolEvent.getEventDescription());
				
				Date eventStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(schoolEvent.getEventStartDate());
				Date eventEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(schoolEvent.getEventEndDate());
				long epochStartDate = eventStartDate.getTime() / 1000L;
				long epochEndDate = eventEndDate.getTime() / 1000L;
				jsonEvent.put("startDate", epochStartDate);
				jsonEvent.put("endDate", epochEndDate);
				jsonEvent.put("eventIncharge", employeeName);
				
				//Add one event to the events array
				jsonEvents.put(jsonEvent);
				
				//Add the events array into the JSON object
				jsonObj.put("events",jsonEvents);
				
				//Initialize URI
				final String uri = URIForEventsEntry;
				URL url = new URL(uri);
				
				//Print the URI
				System.out.println("URI: "+uri);
				
				//Print the JSON Object
				System.out.println("Request JSON: "+jsonObj.toString());
				
				String json_response = "";
				WebIQTransaction webIQTran = null;
				try{
					HttpURLConnection connection = (HttpURLConnection)url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestProperty("Content-Type", "application/json");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					connection.setRequestMethod("POST");
					OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
					out.write(jsonObj.toString());
					out.close();
					
					
					InputStreamReader in = new InputStreamReader(connection.getInputStream());
					BufferedReader br = new BufferedReader(in);
					String text = "";
					while((text = br.readLine())!= null){
						json_response += text;
					}
					System.out.println("Response JSON :"+ json_response);
					if((!json_response.isEmpty())){
						JSONObject object = new JSONObject(json_response);
						int statusFromJsonResponse = (int)object.get("status");
						
						if(statusFromJsonResponse==200){
							//If call to the API is successful, then insert into the webiq_transaction_details table 
							webIQTran = new WebIQTransaction();
							webIQTran.setUri(URIForEventsEntry);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(true);
						}else{
							//If Failure then also insert into the webiq_transaction_details table
							webIQTran = new WebIQTransaction();
							webIQTran.setUpdatedBy(sessionObject.getUserId());
							webIQTran.setUri(URIForEventsEntry);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(false);
						}
						
						//Call to the BackOffice Service
						backOfficeService.addWebIQTransaction(webIQTran);
					}
					//PRAD ENDS
				}catch(Exception ee){
					ee.printStackTrace();
					//If Failure then also insert into the webiq_transaction_details table
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(URIForEventsEntry);
					webIQTran.setRequestJSON(jsonObj.toString());
					webIQTran.setResponseJSON(json_response);
					webIQTran.setStatus(false);
				}
			}
			
			/*SMS Integration */
			if(status.equalsIgnoreCase("success") && isSMSEnabled.equalsIgnoreCase("true")){
				//Get All Students
				List<Student> studentList = backOfficeService.getStudentList();
				List<String> phoneNumbers = new ArrayList<String>();
				int studentListSize = studentList.size();
				
				for(int i=0; i<studentListSize;i++){
					Student student = studentList.get(i);
					if(student.getMobileNo() != null && !(student.getMobileNo().isEmpty()) && !(student.getMobileNo().equalsIgnoreCase("null"))){
						phoneNumbers.add(student.getMobileNo());
					}
				}
				
				/*Now call the SMS function*/
				// Modified by Saikat 16/6/2018
				if(phoneNumbers.size()>0){
					for(int counter = 0; counter < phoneNumbers.size(); counter++){
						Map<String, String> dataMap = utility.sendSMSForSchoolEvent(phoneNumbers.get(counter), schoolEvent, smsURL, smsAuthkey);
						System.out.println("SMS Status for School Event "+ dataMap.get("status"));
						
						// Added by SAIKAT 16/6/2018
						if(dataMap.get("status").equalsIgnoreCase("true")){
							
							SmsAudit smsAudit = new SmsAudit();
							smsAudit.setMobileNumber(phoneNumbers.get(counter));
							smsAudit.setMessage(dataMap.get("message"));
							smsAudit.setActionFor("School Event");
							smsAudit.setStatus(new Boolean(dataMap.get("status")));
							smsAudit.setUpdatedBy(sessionObject.getUserId());
							
							String saveStatus = commonService.saveSMSDetailsForAudit(smsAudit);
							
							logger.info("Insert in SmsAuditLog table for School Event to recipient number " + phoneNumbers.get(counter) + " : " + saveStatus);
						}
					}
					
				}
				
			}
			
			/*SMS Ends*/
			
			model.addAttribute("status", status);
			model.addAttribute("msg", msg);
			
		} catch (Exception e) {
			logger.error("Exception In submitEvent() method of AcademicsController.java:",e);
			e.printStackTrace();
		}

		return createEvent(request,response,model);
	}
	
	@RequestMapping(value = "/getStudentAchievments", method = RequestMethod.GET)
	public String getStudentAchievments(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "academics/createStudentAchievments";
		try {
			logger.info("Inside Method getStudentAchievments-GET of AcademicsController");
			
			//PRAD JUNE 6 2018
			//Get the List of all the events
			List<SchoolEvent> schoolEventList = academicsService.getSchoolEventList();
			model.addAttribute("schoolEventList", schoolEventList);
			
			List<SchoolEvent> eventWithAchievementList=academicsService.getAllEventAchievements();
			model.addAttribute("eventWithAchievementList", eventWithAchievementList);
			
		} catch (Exception ce){
			logger.error("Exception in method getStudentAchievments-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return strView;
	}
	
	@RequestMapping(value = "/submitStudentAchievement", method = RequestMethod.POST)
	public String submitStudentAchievement(HttpServletRequest request,
										   HttpServletResponse response, 
										   ModelMap model,
										   EventAchievement eventAchievement,
										   @ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		try {
			logger.info("Inside Method submitStudentAchievement-GET of AcademicsController");
			
			/*Get all the Request Param Values*/
			String eventCode = request.getParameter("eventName");
			String eventDescription = request.getParameter("eventDescription");
			
			/*Get All other details from the DB*/
			SchoolEvent schoolEventFromDB = academicsService.selectEventName(eventCode);
			String[] eventPosition = request.getParameterValues("eventPosition");
			String[] student = request.getParameterValues("student");
			//String eventAchievementImageFilePath = null;
			
			/*Create the list to save all the event achievements*/
			List<EventAchievement>eventAchievementList = new ArrayList<>();
			
			JSONObject jsonObj = new JSONObject();
			
			
			if(null != eventPosition){
				
				jsonObj.put("username",portalUserName);
				jsonObj.put("password",portalPassWord);
				/*Get Academic Year*/
				AcademicYear academicYear = commonService.getCurrentAcademicYear();
				jsonObj.put("academicsSession",academicYear.getAcademicYearName());
				 
				jsonObj.put("eventName",schoolEventFromDB.getEventName());
				jsonObj.put("eventDescription",eventDescription);
				/*Get Event Start Date, End Date and Incharge*/
				
				jsonObj.put("eventStartDate",schoolEventFromDB.getEventStartDate());
				jsonObj.put("eventEndDate",schoolEventFromDB.getEventEndDate());
				
				JSONArray achievments = new JSONArray();
				for(int j = 0;j<eventPosition.length;j++){
					
					EventAchievement eventAchievementDao = new EventAchievement();
					eventAchievementDao.setObjectId(encryptDecrypt.getBase64EncodedID("AcademicsDAOImpl"));
					eventAchievementDao.setSchoolEvent(Integer.valueOf(eventCode));
					eventAchievementDao.setEventPosition(eventPosition[j]);
					eventAchievementDao.setRollNumber(student[j]);
					eventAchievementDao.setUpdatedBy(sessionObject.getUserId());
					//ADDED FOR ATTACHMENT
					String studentAchievementImageFilePath = getStudentAchievementImageFilePath(eventAchievement,j,student[j]); 
					eventAchievementDao.setImageFilePath(studentAchievementImageFilePath);
					
					JSONObject jsonObjEventAchievement = new JSONObject();
					jsonObjEventAchievement.put("eventPosition", eventPosition[j]);
					jsonObjEventAchievement.put("rollNumber", student[j]);
					
					JSONObject jsonObjEventAchievementPhoto = new JSONObject();
					String fileName = null;
					long fileSize = 0l;
					String fileType = null;
					CommonsMultipartFile file = null;
					
					//Code to get the file details
					if(eventAchievement.getUploadFile()!=null){
						//RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
						//String repository = repositoryStructure.getRepositoryPathName();
						UploadFile uploadFile = eventAchievement.getUploadFile();
						file = uploadFile.getAchievementRelatedFile().get(j);
						fileName = file.getOriginalFilename();
						fileSize = file.getSize();
						fileType = file.getContentType();
					}
					
					jsonObjEventAchievementPhoto.put("fileName", fileName);
					jsonObjEventAchievementPhoto.put("fileSize", fileSize);
					jsonObjEventAchievementPhoto.put("fileType", fileType);
					jsonObjEventAchievementPhoto.put("encoderType", "BASE64");
					jsonObjEventAchievementPhoto.put("imageBytes", encodeImage(file.getBytes()));
					
					jsonObjEventAchievement.put("eventPhoto", jsonObjEventAchievementPhoto);
					
					achievments.put(jsonObjEventAchievement);
					
					/*Added all the EventAcheivements into the List */
					eventAchievementList.add(eventAchievementDao);
				} //FOR ENDS
				
				jsonObj.put("achievements", achievments);
				
			}//IF ENDS
			
			
			String insertStatus = academicsService.submitEventAchievement(eventAchievementList);
			
			/*Create JSON */
			/*When the API is ready then work on this JSON Structure get data dynamically*/
			if(insertStatus.equals("success") && isWebIQAvailable.equalsIgnoreCase("true")){
				
				//Initialize URI
				final String uri = sendStudentAchievement;
				URL url = new URL(uri);
				
				//Print the URI
				System.out.println("URI: "+uri);
				
				//Print the Request JSON
				System.out.println("Request JSON "+jsonObj);
				
				String json_response = "";
				WebIQTransaction webIQTran = null;
				try{
					HttpURLConnection connection = (HttpURLConnection)url.openConnection();
					connection.setDoOutput(true);
					connection.setRequestProperty("Content-Type", "application/json");
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					connection.setRequestMethod("POST");
					OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
					out.write(jsonObj.toString());
					out.close();
					
					
					InputStreamReader in = new InputStreamReader(connection.getInputStream());
					BufferedReader br = new BufferedReader(in);
					String text = "";
					while((text = br.readLine())!= null){
						json_response += text;
					}
					System.out.println("Response JSON :"+ json_response);
	
					if((!json_response.isEmpty())){
						JSONObject object = new JSONObject(json_response);
						int statusFromJsonResponse = (int)object.get("status");
						
						if(statusFromJsonResponse==200){
							//If call to the API is successful, then insert into the webiq_transaction_details table 
							webIQTran = new WebIQTransaction();
							webIQTran.setUri(sendStudentAchievement);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(true);
						}else{
							//If Failure then also insert into the webiq_transaction_details table
							webIQTran = new WebIQTransaction();
							webIQTran.setUpdatedBy(sessionObject.getUserId());
							webIQTran.setUri(sendStudentAchievement);
							webIQTran.setRequestJSON(jsonObj.toString());
							webIQTran.setResponseJSON(json_response);
							webIQTran.setStatus(false);
						}
						
						//Call to the BackOffice Service
						backOfficeService.addWebIQTransaction(webIQTran);
					}
				}catch(Exception ee){
					ee.printStackTrace();
					//Could be Connection Failure then also insert into the webiq_transaction_details table
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(sendStudentAchievement);
					webIQTran.setRequestJSON(jsonObj.toString());
					webIQTran.setResponseJSON(json_response);
					webIQTran.setStatus(false);
					//Call to the BackOffice Service
					backOfficeService.addWebIQTransaction(webIQTran);
				}
			}
			
			/*SMS Integration */
			// Modified by Saikat 16/6/2018
			if(insertStatus.equalsIgnoreCase("success") && isSMSEnabled.equalsIgnoreCase("true")){
				//Get All Roll Numbers & Postions for this Event Achievement
				// Then send the SMS through the Utility function
				if(student.length >0){
					for(int i=0;i<student.length;i++){
						String phoneNumber = backOfficeService.getMobileNumberAgainstRollNumber(student[i]);
						if(phoneNumber != null && !(phoneNumber.isEmpty()) && !(phoneNumber.equalsIgnoreCase("null"))){
							Map<String, String> dataMap = utility.sendSMSForStudentAchievement(phoneNumber, schoolEventFromDB, eventPosition[i], smsURL, smsAuthkey);
							
							// Added by SAIKAT 16/6/2018
							if(dataMap.get("status").equalsIgnoreCase("true")){
								
								SmsAudit smsAudit = new SmsAudit();
								smsAudit.setMobileNumber(phoneNumber);
								smsAudit.setMessage(dataMap.get("message"));
								smsAudit.setActionFor("School Event");
								smsAudit.setStatus(new Boolean(dataMap.get("status")));
								smsAudit.setUpdatedBy(sessionObject.getUserId());
								
								String saveStatus = commonService.saveSMSDetailsForAudit(smsAudit);
								
								logger.info("Insert in SmsAuditLog table for Academic Achievement to recipient number " + phoneNumber + " : " + saveStatus);
							}
						}
					}
				}
			}
			
			/*SMS Ends*/
			
		} catch (Exception ce){
			logger.error("Exception in method submitStudentAchievement-GET of AcademicsController", ce);
			ce.printStackTrace();
		}
		return getStudentAchievments(request,response,model);
	}
	
	private String getStudentAchievementImageFilePath(EventAchievement eventAchievement,int row, String rollNumber){
		String studentAchievementImageFilePath = null;
		
		/*Ensure the Repository exists*/
		RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
		String repository = repositoryStructure.getRepositoryPathName();
		File directory = new File(repository);
		boolean isExists = directory.exists();
		
		/*Working on the Attachments of Images */
		System.out.println("Value of Row :"+row);
		if(isExists == true){
			
			Attachment attachment = new Attachment();
			attachment.setStorageRootPath(repository);
			attachment.setFolderName(studentAchievementImage);					
			logger.info(attachment.getStorageRootPath()+":"+attachment.getFolderName());					
			if(eventAchievement.getUploadFile()!=null){ 
				eventAchievement.getUploadFile().setAttachment(attachment);
				
				String filePath = eventAchievement.getUploadFile().getAttachment().getStorageRootPath()+"/";
				filePath = filePath + eventAchievement.getUploadFile().getAttachment().getFolderName();
				
					UploadFile uploadFile = eventAchievement.getUploadFile();					
					if(uploadFile!=null){
						/*
						 * this is used for upload Tender Related Documents
						 */
						if(uploadFile.getAchievementRelatedFile()!=null && !uploadFile.getAchievementRelatedFile().isEmpty()){
							String eventAchievementImagePath =  filePath+""+rollNumber+"/";
							logger.info("UPDATE TENDER DOC PATH:"+eventAchievementImagePath);
							//for (CommonsMultipartFile file : uploadFile.getAchievementRelatedFile()) {
							CommonsMultipartFile file = uploadFile.getAchievementRelatedFile().get(row);
							if(file.getOriginalFilename()!=""){
								int fileSize = fileUploadDownload.fileUpload(eventAchievementImagePath, file);
								studentAchievementImageFilePath = eventAchievementImagePath+"/"+file.getOriginalFilename();
								System.out.println("Event Achievement File Path:"+studentAchievementImageFilePath);
							}//if ends
						}//if ends
					}//if ends
			}//if ends
		}///if ends
		
		
		return studentAchievementImageFilePath;
	}
	
	@RequestMapping(value = "/getEventDescription", method = RequestMethod.GET)
	public @ResponseBody
	String getEventDescription(@RequestParam ("eventName") String eventName) {
		String eventDescription = "";
		try {
			logger.info("getEventDescription() In AcademicsController.java.."+eventName);
			int i = Integer.parseInt(eventName);
			eventDescription = academicsService.getEventDescription(eventName);
		} catch (Exception e) {
			logger.error("getEventDescription() In AcademicsController.java: Exception"
					+ e);
		}
		return eventDescription;
	}
	
	private static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64String(imageByteArray);
    }
}
