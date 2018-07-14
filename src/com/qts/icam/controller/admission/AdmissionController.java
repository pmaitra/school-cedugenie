package com.qts.icam.controller.admission;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.qts.icam.model.admission.AdmissionDrive;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.admission.AdmissionProcess;
import com.qts.icam.model.admission.Form;
import com.qts.icam.model.admission.LocationDetailsForPortal;
import com.qts.icam.model.admission.Marks;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.AdmissionFormForPortalStudents;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.CategoryAndFees;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Data;
import com.qts.icam.model.common.Folder;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.NameId;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.ProgrammeDetailsForPortal;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Storage;
import com.qts.icam.model.common.TermWiseFees;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.service.admission.AdmissionService;
import com.qts.icam.service.backoffice.BackOfficeService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.date.DateUtility;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qts.icam.model.backoffice.WebIQTransaction;


/**
 * AdmissionController.java - This controller is responsible for admission
 * related operations.
 * 
 * @author sovan.mukherjee
 * @version 1.0
 */

@Controller
@SessionAttributes("sessionObject")
public class AdmissionController {
	public static Logger logger = Logger.getLogger(AdmissionController.class);
	HttpSession session = null;
	
	@Autowired
	AdmissionService admissionService = null;

	@Autowired
	BackOfficeService backOfficeService;

	@Autowired
	CommonService commonService;
	
	@Value("${email.subject}")
	private String emailSubject;
	
	@Value("${email.body}")
	private String emailBody;
	
	@Value("${emilTemplate.basePath}")
	private String emilTemplateBasePath;
	
	/**
	 * get imagePath,pdfFolder,rootPath, from qlssms.properties file
	 */
	@Value("${image.path}")
	private String imagePath;

	@Value("${imageFile1.name}")
	private String image1;

	@Value("${imageFile2.name}")
	private String image2;

	@Value("${pdf.folder}")
	private String pdfFolder;

	@Value("${root.path}")
	private String rootPath;
	
	@Value("${excelDownload.folder}")
	private String excelDownloadFolder;
	
	@Value("${excelUpload.folder}")
	private String excelUploadfolder;
	
	@Value("${folder.path}")
	private String folderName;
	
	@Value("${newAdmissionFormSubmission.excel}")
	private String newAdmissionFormSubmissionExcel;
	
	
	/**
	 * for REST
	 * set URI's*/
	
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
	
	@Value("${Portal.userName}")
	private String PortalUserName;
	
	@Value("${Portal.passWord}")
	private String PortalPassWord;

	@Value("${webiq.available}")
	private String isWebIQAvailable;
	
	/**
	 * This method is used to get current admission process status and return to
	 * that page.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 * 
	 */

	@RequestMapping(value = "/getCurrentAdmissionOnProcessStatus", method = RequestMethod.GET)
	public ModelAndView getCurrentAdmissionOnProcessStatus(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, AdmissionProcess admissionProcess,@ModelAttribute("sessionObject") SessionObject sessionObject
) {
		ModelAndView mav = null;
		try {
			logger.info("getCurrentAdmissionOnProcessStatus() method in AdmissionController");
			String strCourseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			admissionProcess.setAdmissionYear(strAdmissionYear);
			admissionProcess.setCourseClass(strCourseClass);
			admissionProcess.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
			model.addAttribute("StreamClassYearCourse",admissionForm);
			logger.info("calling getCurrentAdmissionOnProcessStatus(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
			String strStatus = admissionService.getCurrentAdmissionOnProcessStatus(admissionProcess);
			//System.out.println("@@@@@@@" + strStatus);
			if (strStatus != null) {
				if (strStatus.equalsIgnoreCase("paymentSetup")) {
					return finalSelectedCandidate(request, response, model,admissionProcess);
				} else if (strStatus.equalsIgnoreCase("paymentDone")) {
					List<AdmissionProcess> paymentDoneListFromDB =  admissionService.getPaymentListToDisplayBack(admissionProcess);
					if (paymentDoneListFromDB != null && paymentDoneListFromDB.size() != 0) {
						
						model.addAttribute("Result", paymentDoneListFromDB);
						mav =new ModelAndView("admission/paymentDone");// now unused this
					} else {
						String message = "Data not found...";
						model.addAttribute("ResultError", message);
						mav =new ModelAndView("common/errorpage");
					}
				} else if (strStatus.equalsIgnoreCase("viewMeritList")) {
					return viewMeritList(request, response, model,admissionProcess,sessionObject);
				} else if (strStatus.equalsIgnoreCase("selectedCandidatesList")) {
					List<AdmissionProcess> interviewResultListFromDb = null;
					admissionProcess.setCourseClass(strCourseClass);
					admissionProcess.setFormStatus("SELECTED");
					logger.info("calling getInterviewedCandidateList(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
					interviewResultListFromDb = admissionService.getInterviewedCandidateList(admissionProcess);
					if (interviewResultListFromDb != null && interviewResultListFromDb.size() != 0) {
						if (interviewResultListFromDb.size() != 0 && interviewResultListFromDb.get(0).getFormStatus().equals("SELECTED")) {
							model.addAttribute("Result",interviewResultListFromDb);
							String control="selectedCandidatesList";
							mav = admissionInterviewResultListPaging(request, response, model,control);
						} else {
							String message = "Data not found...";
							model.addAttribute("ResultError", message);
							mav =new ModelAndView("common/errorpage");
						}
					} else {
						admissionProcess.setFormStatus("REVIEW");
						logger.info("calling getInterviewedCandidateList(AdmissionProcess admissionProcess)  method in AdmissionServiceImpl");
						interviewResultListFromDb = admissionService.getInterviewedCandidateList(admissionProcess);
						if (interviewResultListFromDb != null && interviewResultListFromDb.size() != 0) {
							if (interviewResultListFromDb.size() != 0
									&& interviewResultListFromDb.get(0).getFormStatus().equals("REVIEW")) {
								model.addAttribute("Result",interviewResultListFromDb);
								String control="reviewedCandidatesList";
								mav = admissionInterviewResultListPaging(request, response, model,control);
							} else {
								String errorMessage = "Data not found.....";
								model.addAttribute("ResultError", errorMessage);
								mav =new ModelAndView("common/errorpage");
							}
						} else {
							admissionProcess.setFormStatus("NOTSELECTED");
							logger.info("calling getInterviewedCandidateList(AdmissionProcess admissionProcess) in AdmissionServiceImpl");
							interviewResultListFromDb = admissionService.getInterviewedCandidateList(admissionProcess);
							if (interviewResultListFromDb != null && interviewResultListFromDb.size() != 0) {
								if (interviewResultListFromDb.size() != 0 && interviewResultListFromDb.get(0).getFormStatus().equals("NOTSELECTED")) {
									model.addAttribute("Result",interviewResultListFromDb);
									String control="notSelectedCandidatesList";
									mav = admissionInterviewResultListPaging(request, response, model,control);
								} else {
									String errorMessage = "Data not found.....";
									model.addAttribute("ResultError",errorMessage);
									mav =new ModelAndView("common/errorpage");
								}
							} else {
								String errorMessage = "Data not found.....";
								model.addAttribute("ResultError", errorMessage);
								mav =new ModelAndView("common/errorpage");
							}
						}
					}
				} else if (strStatus.equalsIgnoreCase("scheduleNewInterview")) {
					logger.info("calling getInterviewSchedule(AdmissionProcess admissionProcess) in AdmissionServiceImpl");
					admissionProcess = admissionService
							.getInterviewSchedule(admissionProcess);
					List<Form> formIdList = admissionProcess.getFormList();
					if (admissionProcess != null && formIdList.size() != 0) {
						admissionProcess.setCourseClass(request.getParameter("courseClass"));
						admissionProcess.setAdmissionYear(request.getParameter("year"));
						admissionProcess.setFormName(request.getParameter("driveName"));
						model.addAttribute("contactForm", admissionProcess);
						mav =new ModelAndView("admission/scheduleNewInterview");
					} else {
						String message = "Data not found...";
						model.addAttribute("ResultError", message);
						mav =new ModelAndView("common/errorpage");
					}
				} else if (strStatus.equalsIgnoreCase("submitInterviewResult")) {
					List<AdmissionProcess> interviewScheduleList = null;
					interviewScheduleList = new ArrayList<AdmissionProcess>();
					logger.info("calling getScheduledFormList(AdmissionProcess admissionProcess) AdmissionService.java class for retrieve list of form id ");
					interviewScheduleList = admissionService.getScheduledFormList(admissionProcess);
					if (interviewScheduleList != null && interviewScheduleList.size() != 0) {
						model.addAttribute("FORMID", interviewScheduleList);
						mav =new ModelAndView("admission/submitInterviewResult");
					} else {
						String message = "Data not found...";
						model.addAttribute("ResultError", message);
						mav =new ModelAndView("common/errorpage");
					}
				} else if (strStatus.equalsIgnoreCase("submitNewAdmissionForm")) {
					return new ModelAndView(new RedirectView(
							"submitAdmissionForm.html?courseClass="
									+ strCourseClass + "&year="
									+ strAdmissionYear + "&driveName="
									+ strDriveName));
				}
			} else {
				String message = "Data not found...";
				model.addAttribute("ResultError", message);
				mav =new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			logger.error(
					"Exception in getCurrentAdmissionOnProcessStatus() method in AdmissionController",
					e);
		}
		return mav;
	}

	/**
	 * @author anup.roy
	 * 02.08.2017
	 * this method is to go to the pages for finalize admission process step by step
	 */
	@RequestMapping(value = "/admissionOnProcess", method = RequestMethod.GET)
	public ModelAndView admissionOnProcess(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionProcess admissionProcess) {
		try {
			String status = request.getParameter("admissionDriveState");
			//System.out.println("admission status==="+status);
			String strCourseClass = request.getParameter("courseClass");
		//	System.out.println("strCourseClass===="+strCourseClass);
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			admissionProcess.setCourseClass(strCourseClass);
			admissionProcess.setAdmissionYear(strAdmissionYear);
			admissionProcess.setFormName(strDriveName);          //Naimisha
			//admissionProcess.setFormName(strDriveName);
			String admissionDriveStatus = admissionService.getstatusOfAdmissionDrive(admissionProcess);
			//System.out.println("admissionDriveStatus============"+admissionDriveStatus);
			String admissionDriveState = admissionService.getAdmissionDriveState(strDriveName);
			String newStatus = null;
			if(status!=null ){
				if(admissionDriveStatus != null ){
					if(admissionDriveStatus.equalsIgnoreCase("DONE")){
						newStatus = admissionDriveStatus;
					}
				}
				/*if(admissionDriveState.equalsIgnoreCase("FORMSUBMITTED") && status.equalsIgnoreCase("SCHEDULEINTERVIEW")){*/
				if(admissionDriveStatus== null ||!(newStatus.equalsIgnoreCase("DONE"))){
					if( status.equalsIgnoreCase("INTERVIEWSCHEDULED")){
						return new ModelAndView(new RedirectView(
								"scheduleInterview.html?courseClass="
										+ strCourseClass + "&year="
										+ strAdmissionYear + "&driveName="
										+ strDriveName + "&admissionDriveState=" + status));
					}else if(status.equalsIgnoreCase("INTERVIEWRESULT")){
						return new ModelAndView(new RedirectView(
								"addInterviewResult.html?courseClass="
										+ strCourseClass + "&year="
										+ strAdmissionYear + "&driveName="
										+ strDriveName + "&admissionDriveState=" + status));
					} else if(status.equalsIgnoreCase("MERITLIST")){
						return new ModelAndView(new RedirectView(
								"viewMeritList.html?courseClass="
										+ strCourseClass + "&year="
										+ strAdmissionYear + "&driveName="
										+ strDriveName +"&admissionDriveState=" + status));
					}else if( status.equalsIgnoreCase("PAYMENT")){
						return new ModelAndView(new RedirectView(
								"finalSelectedCandidate.html?courseClass="
										+ strCourseClass + "&year="
										+ strAdmissionYear + "&driveName="
										+ strDriveName + "&admissionDriveState=" + status));
					}else{
						List<AdmissionProcess> admissionFormSubmissionList = admissionService.getGeneratedFormIdList(admissionProcess);
						AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
						List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();
						List<AdmissionProcess>submittedAdmissionFormList = admissionService.getSubmittedFormDetails(admissionProcess);
						model.addAttribute("StreamClassYearCourse",admissionForm);
						model.addAttribute("AdmissionOnProcessClass", admissionProcess);
						model.addAttribute("socialCategoryList", socialCategoryList);
						model.addAttribute("FORMLIST", admissionFormSubmissionList);
						model.addAttribute("submittedAdmissionFormList", submittedAdmissionFormList);
						model.addAttribute("admissionDriveState", status);
						model.addAttribute("admissionDriveStateNew", admissionDriveState );
						//return new ModelAndView("admission/formSubmission");
					}
				}else if( newStatus.equalsIgnoreCase("DONE")){
						if( status.equalsIgnoreCase("INTERVIEWSCHEDULED")){
							return new ModelAndView(new RedirectView(
									"scheduleInterview.html?courseClass="
											+ strCourseClass + "&year="
											+ strAdmissionYear + "&driveName="
											+ strDriveName + "&admissionDriveState=" + status));
						}else if(status.equalsIgnoreCase("INTERVIEWRESULT")){
							return new ModelAndView(new RedirectView(
									"addInterviewResult.html?courseClass="
											+ strCourseClass + "&year="
											+ strAdmissionYear + "&driveName="
											+ strDriveName + "&admissionDriveState=" + status));
						} else if(status.equalsIgnoreCase("MERITLIST")){
							return new ModelAndView(new RedirectView(
									"viewMeritList.html?courseClass="
											+ strCourseClass + "&year="
											+ strAdmissionYear + "&driveName="
											+ strDriveName +"&admissionDriveState=" + status));
						}else if( status.equalsIgnoreCase("PAYMENT")){
							return new ModelAndView(new RedirectView(
									"finalSelectedCandidate.html?courseClass="
											+ strCourseClass + "&year="
											+ strAdmissionYear + "&driveName="
											+ strDriveName + "&admissionDriveState=" + status));
						}
					List<AdmissionProcess> admissionFormSubmissionList = admissionService.getGeneratedFormIdList(admissionProcess);
					AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
					List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();
					List<AdmissionProcess>submittedAdmissionFormList = admissionService.getSubmittedFormDetails(admissionProcess);
					model.addAttribute("StreamClassYearCourse",admissionForm);
					model.addAttribute("AdmissionOnProcessClass", admissionProcess);
					model.addAttribute("socialCategoryList", socialCategoryList);
					model.addAttribute("FORMLIST", admissionFormSubmissionList);
					model.addAttribute("submittedAdmissionFormList", submittedAdmissionFormList);
					model.addAttribute("admissionDriveState", newStatus);
					model.addAttribute("admissionDriveStateNew", admissionDriveState );
					//return new ModelAndView("admission/formSubmission");
				}
			}else{
			if(admissionDriveState.equalsIgnoreCase("FORMSUBMITTED")){
				return new ModelAndView(new RedirectView(
						"scheduleInterview.html?courseClass="
								+ strCourseClass + "&year="
								+ strAdmissionYear + "&driveName="
								+ strDriveName));
			}else if(admissionDriveState.equalsIgnoreCase("INTERVIEWSCHEDULED")){
				return new ModelAndView(new RedirectView(
						"addInterviewResult.html?courseClass="
								+ strCourseClass + "&year="
								+ strAdmissionYear + "&driveName="
								+ strDriveName));
			} else if(admissionDriveState.equalsIgnoreCase("INTERVIEWRESULT")){
				return new ModelAndView(new RedirectView(
						"viewMeritList.html?courseClass="
								+ strCourseClass + "&year="
								+ strAdmissionYear + "&driveName="
								+ strDriveName));
			}else if(admissionDriveState.equalsIgnoreCase("MERITLIST")){
				return new ModelAndView(new RedirectView(
						"finalSelectedCandidate.html?courseClass="
								+ strCourseClass + "&year="
								+ strAdmissionYear + "&driveName="
								+ strDriveName ));
			}else{
					List<AdmissionProcess> admissionFormSubmissionList = admissionService.getGeneratedFormIdList(admissionProcess);
					AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
					List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();
					List<AdmissionProcess>submittedAdmissionFormList = admissionService.getSubmittedFormDetails(admissionProcess);
					model.addAttribute("StreamClassYearCourse",admissionForm);
					model.addAttribute("AdmissionOnProcessClass", admissionProcess);
					model.addAttribute("socialCategoryList", socialCategoryList);
					model.addAttribute("FORMLIST", admissionFormSubmissionList);
					model.addAttribute("submittedAdmissionFormList", submittedAdmissionFormList);
					model.addAttribute("admissionDriveState", status);
					model.addAttribute("admissionDriveStateNew", admissionDriveState );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in admissionOnProcess() method in AdmissionController: ",e);
		}
		return new ModelAndView("admission/formSubmission");  /////Naimisha working
	}

	/**
	 * This method get particular year,drive name, class and return to
	 * historyAdmissionOnProcess
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param admissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/historyAdmissionOnProcess", method = RequestMethod.GET)
	public ModelAndView historyListPage(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("admissionProcess") AdmissionProcess admissionProcess) {
		logger.info("In historyAdmissionOnProcess() of Admissioncontroller.java");
		String strView = null;
		try {
			String strCourseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			admissionProcess.setCourseClass(strCourseClass);
			admissionProcess.setAdmissionYear(strAdmissionYear);
			admissionProcess.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
			model.addAttribute("StreamClassYearCourse",admissionForm);
			model.addAttribute("AdmissionOnProcessClass", admissionProcess);
			strView = "admission/historyAdmissionOnProcess";
		} catch (Exception e) {
			logger.error("Exception in historyListPage() of Admissioncontroller.java: ",e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * This method return name of candidate.
	 * 
	 * @param String
	 * @return String
	 * 
	 * 
	 */
	@RequestMapping(value = "/getName", method = RequestMethod.GET)
	public @ResponseBody
	String getNameForFormIdByAjax(@RequestParam("strParam") String strParam,
			AdmissionProcess admissionProcess) {
		
		try {
			
			String[] strArray = strParam.split(":");
			//System.out.println(strArray);
			admissionProcess.setFormId(strArray[0]);
			admissionProcess.setCourseClass(strArray[1]);
			admissionProcess.setFormName(strArray[2]);
			admissionProcess.setAdmissionYear(strArray[3]);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Exception in historyListPage() of Admissioncontroller.java: ",
					e);
		}
		
		String candidateFullName = admissionService.getNameForFormIdByAjax(admissionProcess);
		
		return (new Gson().toJson(candidateFullName));
	}

	/**
	 * This method returns listOfAdmissionForm.jsp populated with year wise
	 * Admission list.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionDrive
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/admissionList", method = RequestMethod.GET)
	public ModelAndView admissionsList(@RequestParam("drive") String strDrive,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		try {
			logger.info("In admissionsList() of Admissioncontroller.java");
			logger.info("calling AdmissionService.java by getAdmissionDriveListService(String strDrive) method: in Admissioncontroller.java");
			List <AdmissionForm> admissionDriveList = admissionService.getAdmissionDriveList(strDrive);
			//String admissionCopletedState = admissionService.getAdmissionDriveState(strDrive);
			//System.out.println("strDrive===="+strDrive);
			//System.out.println("admissionCopletedState==="+admissionCopletedState);
			model.addAttribute("AdmissionDriveList", admissionDriveList);
			model.addAttribute("drivestate", strDrive);
		} catch (Exception e) {
			logger.error("Exception In admissionsList() of Admissioncontroller.java: ",e);
		}
		return new ModelAndView("admission/admissionList");
	}
	
	
	@RequestMapping(value="/admissionListPaging",method=RequestMethod.GET)
	public ModelAndView admissionListPaging(HttpServletRequest request, HttpServletResponse response,ModelMap model){		
		try{
			logger.info("admissionListPaging() method in Admissioncontroller: ");
			PagedListHolder<AdmissionForm> admissionDrivePagedListHolder = admissionService.getAdmissionDriveListPaging(request);	
				if (admissionDrivePagedListHolder != null) {
					model.addAttribute("first", admissionDrivePagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", admissionDrivePagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", admissionDrivePagedListHolder.getNrOfElements());
					model.addAttribute("AdmissionDriveList", admissionDrivePagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in admissionListPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView("admission/admissionList");
	}

	/**
	 * @author 
	 * this method is for view all list of standards with available current openings
	 */
	@RequestMapping(value = "/admissionFormsList", method = RequestMethod.GET)
	public ModelAndView admissionFormsList(HttpServletRequest request,
										HttpServletResponse response, ModelMap model) {
		try {
			logger.info("admissionFormsList() method in AdmissionController: ");
			logger.info("calling getAdmissionFormDetailsService() in AdmissionService.java class and get admissionForm list");
			List<AdmissionForm> admissionFormList = admissionService.getAdmissionFormDetails();
			model.addAttribute("admissionFormList", admissionFormList);
		} catch (Exception e) {
			logger.error("Exception In admissionFormsList() of Admissioncontroller.java: ",e);
		}
		return new ModelAndView("admission/admissionFormsList");
	}
	
	/**
	 * This method returns newAdmissionForm.jsp with populated class and course
	 * name.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @return ModelAndView
	 * naimisha.ghosh
	 * 03072017
	 */
	@RequestMapping(value = "/newAdmissionForm", method = RequestMethod.POST)
	public ModelAndView newAdmissionForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionForm admissionForm,
			@RequestParam(value = "admissionFormSearch", required = false) String searchFlag,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "data", required = false) String data) {
			ModelAndView mav = null;
		try {
				logger.info("In newAdmissionForm() of AdmissionController - checking method: currentOpening::AdmissionControler");
				String courseClass = request.getParameter(admissionForm.getCourseCode().trim()).trim();
				
				admissionForm.setCourseClass(courseClass);
				logger.info("calling getAdmissionForm(AdmissionForm admissionForm) in AdmissionServiceImpl");
			
				AdmissionForm admissionFormDetails = admissionService.getAdmissionForm(admissionForm);
				//System.out.println("drive name ==============="+admissionFormDetails.getAdmissionDriveName());
				AcademicYear currentYear = commonService.getCurrentAcademicYear();
				if (admissionFormDetails != null) {
					String status = admissionFormDetails.getStatus();
					System.out.println("status===="+status);
					if(status != null){
							//if(status.equalsIgnoreCase("ONGOING")){
							List<AdmissionForm> admissionFormList = admissionService.getAdmissionFormDetails(); //NAIMISHA 29062017
							model.addAttribute("admissionFormList", admissionFormList);
							model.addAttribute("msg", "A Drive Is Going ON For this Program");
							mav = new ModelAndView("admission/admissionFormsList");
						//}
					}else{
						model.addAttribute("currentYear", currentYear);
						model.addAttribute("admissionForm", admissionFormDetails);
						mav = new ModelAndView("admission/newAdmissionForm");
					}
					
				} else {
					String error = "No course available for admission.";
					model.addAttribute("ResultError", error);
					mav = new ModelAndView("common/errorpage");
				}
			//}
		} catch (Exception e) {
			logger.error("Exception in newAdmissionForm() in AdmissionController: ",e);
		}
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param admissionform
	 * @return
	 * 
	 *         used
	 */

	@RequestMapping(value = "/admissionForSelectedYear", method = RequestMethod.POST)
	public ModelAndView admissionFormListForCurrentYear(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("admissionform") AdmissionForm admissionform,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = null;
		List<AdmissionForm> admissionFormListFromDb = null;
		try {
			logger.info("admissionFormListForCurrentYear() merhod In AdmissionController");
			Utility util = new Utility();
			/* retrieve date as dd/mm/yyyy and convert into yyyy-mm-dd */
			String lstDateOfSubmission = admissionform.getAdmissionFormSubmissionLastDate();
			admissionform.setAdmissionFormSubmissionLastDate(util.convertDDMMYYtoYYMMDD(lstDateOfSubmission));
			String admissionStartDate = admissionform.getAdmissionDriveStartDate();
			admissionform.setAdmissionDriveStartDate(util.convertDDMMYYtoYYMMDD(admissionStartDate));

			String admissionExpEndDate = admissionform.getAdmissionDriveExpectedEndDate();
			admissionform.setAdmissionDriveExpectedEndDate(util.convertDDMMYYtoYYMMDD(admissionExpEndDate));

			admissionform.setAdmissionDriveCode("AD-"
					+ admissionform.getCourseName() + "-"
					+ admissionform.getAdmissionFormYear() + "-"
					+ admissionform.getCourseName() + "-"
					+ admissionform.getCourseType());
			admissionform.setCourseClass( admissionform.getCourseName());
			admissionform.setAdmissionFormDescription(admissionform.getAdmissionDriveName());
			admissionform.setUpdatedBy(sessionObject.getUserId());
			logger.info("calling insertAdmissionFormService() method in AdmissionService.java class for insert Admission from details");
			admissionFormListFromDb = admissionService.insertAdmissionDrive(admissionform);
			if (admissionFormListFromDb != null && admissionFormListFromDb.size() != 0) {
				model.addAttribute("admissionFormList", admissionFormListFromDb);
				mav = new ModelAndView("admission/currentOpeningList");//activeAdmissionDrivesPaging(request,response,model);
				//return new ModelAndView("admission/currentOpeningList");
			} else {
				String error = "No admission drive published";
				model.addAttribute("ResultError", error);
				mav = new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			logger.error("Exception in admissionFormListForCurrentYear() merhod in AdmissionController",e);
		}
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * 
	 * 
	 */
	@RequestMapping(value = "/activeAdmissionDrives", method = RequestMethod.GET)
	public ModelAndView activeAdmissionDrives(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView mav = null;
		try {
			logger.info("activeAdmissionDrives() merhod In AdmissionController");
			 List<AdmissionForm> activeAdmissionDriveList = admissionService.getActiveAdmissionDrives();
			model.addAttribute("admissionFormList", activeAdmissionDriveList);
		} catch (Exception e) {
			logger.error("Exception in activeAdmissionDrives() merhod in AdmissionController",e);
		}
		return new ModelAndView("admission/currentOpeningList");
	}
	
	@RequestMapping(value = "/activeAdmissionDrivesSearch", method = RequestMethod.POST)
	public ModelAndView activeAdmissionDrivesSearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "data", required = false) String data
			) {
		try {
			logger.info("activeAdmissionDrivesSearch() merhod In AdmissionController");
			if(data!=null){
				data=data.trim();
			}
			Map<String, Object> parameters = new HashMap<String, Object>();
			if (query.equalsIgnoreCase("Year")) {
				parameters.put("Year", data);
			}
			if (query.equalsIgnoreCase("Class")) {
				parameters.put("Class", data);
			}
			if (query.equalsIgnoreCase("Stream")) {
				parameters.put("Stream", data);
			}
			if (query.equalsIgnoreCase("CourseName")) {
				parameters.put("CourseName", data);
			}
			if (query.equalsIgnoreCase("CourseType")) {
				parameters.put("CourseType", data);
			}
			if (query.equalsIgnoreCase("DriveName")) {
				parameters.put("DriveName", data);
			}
			if (query.equalsIgnoreCase("AdmissionStartDate")) {
				parameters.put("AdmissionStartDate", data);
			}
			if (query.equalsIgnoreCase("AdmissionExpectedEndDate")) {
				parameters.put("AdmissionExpectedEndDate", data);
			}
			if (query.equalsIgnoreCase("LastDateOfFormSubmission")) {
				parameters.put("LastDateOfFormSubmission", data);
			}
			if (query.equalsIgnoreCase("Status")) {
				parameters.put("Status", data);
			}
			admissionService.getActiveAdmissionDriveSearch(parameters);
		} catch (Exception e) {
			logger.error("Exception in activeAdmissionDrivesSearch() merhod in AdmissionController",e);
		}
		return activeAdmissionDrivesPaging(request,response,model);
	}
	
	
	@RequestMapping(value="/activeAdmissionDrivesPaging",method=RequestMethod.GET)
	public ModelAndView activeAdmissionDrivesPaging(HttpServletRequest request, HttpServletResponse response,ModelMap model){		
		try{
			logger.info("activeAdmissionDrivesPaging() method in Admissioncontroller: ");
			PagedListHolder<AdmissionForm> activeAdmissionDriveListPagedListHolder = admissionService.activeAdmissionDrivesPaging(request);	
				if (activeAdmissionDriveListPagedListHolder != null) {
					model.addAttribute("first", activeAdmissionDriveListPagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", activeAdmissionDriveListPagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", activeAdmissionDriveListPagedListHolder.getNrOfElements());
					model.addAttribute("admissionFormList", activeAdmissionDriveListPagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in activeAdmissionDrivesPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView("admission/currentOpeningList");
	}

	/**
	 * This method returns printAdmissionForm.jsp with populated class,form fees
	 * ,year.
	 * 
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionForm
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/generateAdmissionForm", method = RequestMethod.GET)
	public ModelAndView generateAdmissionForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionForm admissionform) {
		String strView = null;
		try {
			logger.info("generateAdmissionForm() method in AsmissionController");
			double fees = 0.0;
			ArrayList<AdmissionForm> admissionFormList = new ArrayList<AdmissionForm>();
			fees = Double.valueOf(admissionform.getFormFees()).doubleValue(); 
			logger.info("In printAdmissionForm() of AdmissionController - checking Form Fees: "+ fees);
			logger.info("calling getLastFormIDService() method in AdmissionService.java class for get Last Form ID.");
			String strLastFormID = admissionService.getLastFormID(admissionform);
			admissionform.setAdmissionDriveCode(strLastFormID);
			admissionFormList.add(admissionform);
			if (strLastFormID == null) {
				admissionFormList.get(0).setStatus("Admission Process is going on for this course.");
			}
			model.addAttribute("admissionFormLastFormId", admissionFormList);
			strView = "admission/generateAdmissionForm";
		} catch (NumberFormatException e) {
			logger.error("In generateAdmissionForm() of AdmissionController - NumberFormatException: "+ e);
		} catch (Exception e) {
			logger.error("In generateAdmissionForm() of AdmissionController - Exception: "+ e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * This method generate pdf file and return to generateAdmissionForm.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionForm
	 * @return ModelAndView
	 * 
	 * 
	 */

	@RequestMapping(value = "/print", method = RequestMethod.POST)
	public ModelAndView printForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionForm admissionForm,
			ArrayList<AdmissionForm> admissionFormList,
			@ModelAttribute("sessionObject") SessionObject sessionObject
			) {
		String strView = null;
		double fees = 0.0;
		try {
			session = request.getSession(false);
			int intNumberOfPrint = Integer.parseInt(request.getParameter("numberOfprint"));
			String formCode = admissionForm.getAdmissionDriveCode();
			String[] arrTempFormCode = formCode.split("-");			
			int tempFormCode = Integer.parseInt(arrTempFormCode[1]);
			
			
			 
			for (int i = 1; i <= intNumberOfPrint; i++) {
				AdmissionForm admissionFormToInsert = new AdmissionForm();
				admissionFormToInsert.setAdmissionDriveName(admissionForm.getAdmissionDriveName());
				admissionFormToInsert.setAdmissionDriveCode(arrTempFormCode[0] + "-" + new Integer(tempFormCode + i).toString());
				admissionFormToInsert.setAdmissionFormDescription("FormDescription");
				admissionFormToInsert.setCourseClass(admissionForm.getCourseName());
				admissionFormToInsert.setCourseName(admissionForm.getCourseName());
				admissionFormToInsert.setCourseType(admissionForm.getCourseType());
				admissionFormToInsert.setAdmissionFormSubmissionLastDate(admissionForm.getAdmissionFormSubmissionLastDate());
				admissionFormToInsert.setAdmissionFormYear(admissionForm.getAdmissionFormYear());
				admissionFormToInsert.setRootPath(rootPath);
				admissionFormToInsert.setPdfFolder(pdfFolder);
				admissionFormToInsert.setImagePath(imagePath);
				admissionFormToInsert.setImageFile1name(image1);
				admissionFormToInsert.setImageFile2name(image2);
				admissionFormToInsert.setUpdatedBy(sessionObject.getUserId());
				admissionFormList.add(admissionFormToInsert);
				
			}
			
			Utility util = new Utility();
			logger.info("calling printAdmissionFormPDF(ArrayList<AdmissionForm> admissionFormList) in Utility.java for create admission form");
			//util.printAdmissionFormPDF(admissionFormList);
			logger.info("calling insertFormId(admissionFormList) method in AdmissionServiceImpl: printForm() method in  AdmissionController");
			String strLastFormID = admissionService.insertFormId(admissionFormList);
			fees = Double.valueOf(admissionForm.getFormFees()).doubleValue();
			logger.info("In printAdmissionForm() of AdmissionController - checking Form Fees: "	+ fees);
			logger.info("calling getLastFormID(admissionForm) method in AdmissionServiceImpl: printForm() method in  AdmissionController");
			if (strLastFormID != null) {
				admissionForm.setAdmissionDriveCode(strLastFormID);
				admissionForm.setStrMessage(intNumberOfPrint+ " Forms successfully generated for class "+ admissionForm.getCourseClass() + ".");
				admissionFormList.clear();
				admissionFormList.add(admissionForm);
				model.addAttribute("admissionFormLastFormId", admissionFormList);
				//strView = "admission/generateAdmissionForm";
			} else {
				model.addAttribute("ResultError", "Form generation failed.");
				strView = "common/errorpage";
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error("Exception  In printAdmissionForm() of AdmissionController : ",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In printAdmissionForm() of AdmissionController : ",e);		
		}
		return  viewAndPrintForm( request,response,model);
	}

	/**
	 * @author anup.roy
	 * this method is for view the image of the form
	 * also for download
	 * 07.08.2017**/
	
	@RequestMapping(value = "/printForm", method = RequestMethod.POST)
	public ModelAndView viewAndPrintForm(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		String format = null;
		try {
			format  = "admission/printAdmissionForm";
			logger.info("In printForm() of AdmissionController");
		} catch (Exception e) {
			logger.error("Error in printForm() of AdmissionController"+e);
			e.printStackTrace();
		}
		return new ModelAndView(format);
	}
	
	/**
	 * @author anup.roy
	 * this method is responsible for view and insert form sales 
	 * w.r.t academic year,standard,subject
	 * 02.08.2017  
	 * */
	@RequestMapping(value = "/saleFormDetails", method = RequestMethod.GET)
	public ModelAndView saleFormDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			logger.info("saleFormDetails() method in AdmissionController: ");
			AcademicYear ay = admissionService.getYearClassList();
			if (ay != null) {
				model.addAttribute("academicYearClass", ay);
			} else {
				model.addAttribute("ResultError", "result not available.");
			}
		} catch (Exception e) {
			logger.info("saleFormDetails() method in AdmissionController Exception: ",e);
			e.printStackTrace();
		}
		return new ModelAndView("admission/saleFormDetails");
	}

	/**
	 * this Ajax call get stream list for a class
	 * 
	 * @param klass
	 * @return String
	 * 
	 * 
	 */
	@RequestMapping(value = "/getDriveList", method = RequestMethod.GET)
	public @ResponseBody
	String getStreamList(@RequestParam("klass") String klass,
			@RequestParam("year") String year, Resource r) {
		logger.info("In  getDriveList() method of AdmissionController: ");
		String sm = "";
		// Resource r=new Resource();
		r.setKlass(klass);
		r.setCode(year);
		
		List<AdmissionDrive> driveList = commonService.getDriveList(r);
		
		if (driveList != null && driveList.size() != 0) {
			for (AdmissionDrive admissionDrive : driveList) {
				if (admissionDrive.getAdmissionDriveName().trim().length() != 0) {
					sm = sm + admissionDrive.getAdmissionDriveName() + "*";
				}
			}
		} else {
			logger.info("In BackOfficeController getStreamList() method: No Stream under this Class");
		}
		
		return (new Gson().toJson(sm));
	}

	/**
	 * 
	 * @param klass
	 * @param year
	 * @param driveName
	 * @return
	 * 
	 * 
	 */
	@RequestMapping(value = "/getFormDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getFormDetails(@RequestParam("klass") String klass,
			@RequestParam("year") String year,
			@RequestParam("driveName") String driveName, AdmissionForm af) {
		String sm = "";
		try {
			logger.info("In  getFormDetails() method of AdmissionController: ");
			// AdmissionForm af = new AdmissionForm();
			af.setCourseClass(klass);
			af.setAdmissionFormYear(year);
			af.setAdmissionDriveName(driveName);
			af = admissionService.getFormDetails(af);
			if (af != null) {
				if (af.getNoOfOpenings() != 0) {
					sm = sm + af.getNoOfOpenings() + "*";
				} else {
					sm = sm + "" + "*";
				}
				if (af.getFormFees() != 0) {
					sm = sm + af.getFormFees() + "*";
				} else {
					sm = sm + "" + "*";
				}
				if (af.getNoOfFormSold() != 0) {
					sm = sm + af.getNoOfFormSold() + "*";
				} else {
					sm = sm + "" + "*";
				}
			} else {
				logger.info("In BackOfficeController getFormDetails() method: else{ }");
			}
		} catch (Exception e) {
			logger.error(
					"In BackOfficeController getFormDetails() method: Exception",
					e);
		}
		//System.out.println("sm=========="+sm);
		return (new Gson().toJson(sm));
	}

	/**
	 * @author anup.roy
	 * this method submits the no of form submitted
	 * returns the remaining form details and status
	 * 02.08.2017
	 */
	@RequestMapping(value = "/submitSaleFormDetails", method = RequestMethod.POST)
	public ModelAndView submitSaleFormDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("admissionForm") AdmissionForm admissionForm,
			@ModelAttribute("sessionObject") SessionObject sessionObject
			) {
		try {
			logger.info("submitSaleFormDetails() method in AdmissionController: ");
			admissionForm.setUpdatedBy(sessionObject.getUserId());
			AcademicYear ay = admissionService.submitSaleFormDetails(admissionForm);
			String insertStatus = null;
			String statusMessage = null;
			if (ay != null) {
				model.addAttribute("academicYearClass", ay);
				insertStatus = "success";
				statusMessage = admissionForm.getNoOfFormSold()+"forms have been sold successfully";
			} else {
				insertStatus = "fail";
				statusMessage = "Form selling failed";
			}
			model.addAttribute("insertStatus", insertStatus);
			model.addAttribute("statusMessage", statusMessage);
		} catch (Exception e) {
			logger.info("submitSaleFormDetails() method in AdmissionController Exception: ",e);
			e.printStackTrace();
		}
		return new ModelAndView("admission/saleFormDetails");
	}

	/**
	 * @author anup.roy
	 * 02.08.2017
	 * this method shows the list of all drives which are on process
	 */
	@RequestMapping(value = "/admissionsOnProcessList", method = RequestMethod.GET)
	public ModelAndView admissionsOnProcessList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			logger.info("admissionsOnProcessList() method in AdmissionController");
			logger.info("call getCourseDetails() in  AdmissionService.java class and get admissionCourseList");
			List<AdmissionForm> AdmissionsOnProcessList = admissionService.getAdmissionsOnProcessList();
			model.addAttribute("AdmissionsOnProcessList", AdmissionsOnProcessList);
		} catch (Exception e) {
			logger.error("Exception in admissionsOnProcessList() method in AdmissionController",e);
			e.printStackTrace();
		}
		return new ModelAndView("admission/admissionsOnProcessList");
	}
	
/*	@RequestMapping(value = "/admissionsOnProcessListSearch", method = RequestMethod.POST)
	public ModelAndView admissionsOnProcessListSearch(HttpServletRequest request,
													  HttpServletResponse response, ModelMap model,
													  @RequestParam(value = "query", required = false) String query,
													  @RequestParam(value = "data", required = false) String data
													 ) {
		try {
			logger.info("admissionsOnProcessListSearch() method in AdmissionController");
			if(data!=null){
				data=data.trim();
			}
			Map<String, Object> parameters = new HashMap<String, Object>();
			if (query.equalsIgnoreCase("Year")) {
				parameters.put("Year", data);
			}
			if (query.equalsIgnoreCase("Class")) {
				parameters.put("Class", data);
			}
			if (query.equalsIgnoreCase("Stream")) {
				parameters.put("Stream", data);
			}
			if (query.equalsIgnoreCase("CourseName")) {
				parameters.put("CourseName", data);
			}
			if (query.equalsIgnoreCase("CourseType")) {
				parameters.put("CourseType", data);
			}
			if (query.equalsIgnoreCase("DriveName")) {
				parameters.put("DriveName", data);
			}
			admissionService.getAdmissionsOnProcessSearch(parameters);
		} catch (Exception e) {
			logger.error("Exception in admissionsOnProcessListSearch() method in AdmissionController",e);
		}
		return admissionsOnProcessListPaging(request,response,model);
	}*/

	/*@RequestMapping(value="/admissionsOnProcessListPaging",method=RequestMethod.GET)
	public ModelAndView admissionsOnProcessListPaging(HttpServletRequest request, HttpServletResponse response,ModelMap model){		
		try{
			logger.info("admissionsOnProcessListPaging() method in Admissioncontroller: ");
			PagedListHolder<AdmissionForm> admissionsOnProcessPagedListHolder = admissionService.getadmissionsOnProcessListPaging(request);	
				if (admissionsOnProcessPagedListHolder != null) {
					model.addAttribute("first", admissionsOnProcessPagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", admissionsOnProcessPagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", admissionsOnProcessPagedListHolder.getNrOfElements());
					model.addAttribute("AdmissionsOnProcessList", admissionsOnProcessPagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in admissionsOnProcessListPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView("admission/admissionsOnProcessList");
	}*/
	
	/**
	 * This method get list of submitted form id, teachers and examiner name and
	 * return to scheduleNewInterview.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 *         used
	 */
	@RequestMapping(value = "/scheduleInterview", method = RequestMethod.GET)
	public ModelAndView admissionProcess(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionProcess admissionProcess) {
		ModelAndView mav = null;
		
		try {
			session = request.getSession(false);
			String status = request.getParameter("admissionDriveState");
			String strCourseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			admissionProcess.setAdmissionYear(strAdmissionYear);
			admissionProcess.setCourseClass(strCourseClass);
			admissionProcess.setFormName(strDriveName);
			
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
			logger.info("calling getInterviewSchedule(admissionProcess) method in AdmissionServiceImpl: admissionProcess() method in AdmissionController: ");
			admissionProcess = admissionService.getInterviewSchedule(admissionProcess);
			String admissionDriveStatus = admissionService.getstatusOfAdmissionDrive(admissionProcess);
			List<Form> formIdList = admissionProcess.getFormList();
			if (admissionProcess != null ) {
				admissionProcess.setCourseClass(request.getParameter("courseClass"));
				admissionProcess.setAdmissionYear(request.getParameter("year"));
				admissionProcess.setFormName(request.getParameter("driveName"));
				admissionProcess.setFormStatus(status);
				List<AdmissionProcess>interviewScheduleList = admissionService.getinterviewScheduledList(admissionProcess);
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("contactForm", admissionProcess);
				model.addAttribute("formIdList", formIdList);
				model.addAttribute("submittedAdmisionInterviewScheduleList", interviewScheduleList);
				String newStatus = null ;
				if(admissionDriveStatus !=null){
					if(admissionDriveStatus.equalsIgnoreCase("DONE")){
						newStatus = "DONE";		
						model.addAttribute("admissionDriveState", newStatus);
					}
				}
				else{
					model.addAttribute("admissionDriveState", status);
				}
				String admissionDriveState = admissionService.getAdmissionDriveState(strDriveName);
				model.addAttribute("admissionDriveStateNew", admissionDriveState );
				mav = new ModelAndView("admission/scheduleInterview");
			} /*else {
				mav = new ModelAndView(new RedirectView("submittedInterviewScheduleList.html?courseClass="
								+ strCourseClass + "&year=" + strAdmissionYear
								+ "&driveName=" + strDriveName));
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in admissionProcess() method in AdmissionController",e);
		}
		return mav;
	}



	/**
	 * This method get list of Scheduled candidate and return to
	 * submittedInterviewScheduleList.jsp,if there are no scheduled candidate
	 * then it will return to scheduleNewInterview.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 *         used
	 */
	@RequestMapping(value = "/nextGoToSubmittedInterviewScheduleList", method = RequestMethod.GET)
	public ModelAndView nextGoToSubmittedInterviewScheduleList(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, AdmissionProcess admissionProcess) {
		List<AdmissionProcess> interviewScheduleList = null;
		ModelAndView mav = null;
		try {
			String strCourseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			admissionProcess.setAdmissionYear(strAdmissionYear);
			admissionProcess.setCourseClass(strCourseClass);
			admissionProcess.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
			logger.info("calling getInterviewScheduleList(AdmissionProcess admissionProcess)method in AdmissionServiceImpl");
			interviewScheduleList = admissionService.getInterviewScheduleList(admissionProcess);
			if (interviewScheduleList != null && interviewScheduleList.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				mav = submittedAdmisionInterviewScheduleListPaging(request, response,model);
			} else {
				logger.info("calling getInterviewSchedule(admissionProcess) method in AdmissionController");
				admissionProcess = admissionService.getInterviewSchedule(admissionProcess);
				List<Form> formIdList = admissionProcess.getFormList();
				if (formIdList != null && formIdList.size() != 0) {
					admissionProcess.setCourseClass(strCourseClass);
					admissionProcess.setAdmissionYear(strAdmissionYear);
					admissionProcess.setFormName(strDriveName);
					model.addAttribute("StreamClassYearCourse", admissionForm);
					model.addAttribute("contactForm", admissionProcess);
					mav =new ModelAndView("admission/scheduleNewInterview");
				} else {
					String message = "There are no form id for schedule...";
					model.addAttribute("ResultError", message);
					mav =new ModelAndView("admission/errorpage");
				}
			}
		} catch (Exception e) {
			logger.error("Exception in nextGoToSubmittedInterviewScheduleList() in AdmissionController",e);
		}
		return mav;
	}

	/**
	 * This method get list of Scheduled candidate and return to
	 * submittedInterviewScheduleList.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 */

	@RequestMapping(value = "/submittedInterviewScheduleList", method = RequestMethod.GET)
	public ModelAndView submittedInterviewScheduleList(
												HttpServletRequest request, HttpServletResponse response,
												ModelMap model, AdmissionProcess admissionProcessToDB) {
		try {
			logger.info("submittedInterviewScheduleList() method in AdmissionController: ");
			String courseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			admissionProcessToDB.setAdmissionYear(strAdmissionYear);
			admissionProcessToDB.setCourseClass(courseClass);
			admissionProcessToDB.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcessToDB);
			logger.info("calling getInterviewScheduleList(AdmissionProcess admissionProcessToDB) method in AdmissionController: ");
			List<AdmissionProcess> admissionProcess  = admissionService.getInterviewScheduleList(admissionProcessToDB);
			model.addAttribute("StreamClassYearCourse", admissionForm);
			model.addAttribute("contactForm", admissionProcess);
		} catch (Exception e) {
			logger.error("Exception in submittedInterviewScheduleList() method in AdmissionController: ",e);
		}
		//return submittedAdmisionInterviewScheduleListPaging(request, response,model);
		return new ModelAndView("admission/scheduleInterview");
	}

	/**
	 * This method retrieve Interview schedule details from
	 * interviewSchedule.jsp and passing to AdmissionService.java
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 *         used
	 */

	@RequestMapping(value = "/newScheduleInterview", method = RequestMethod.POST)
	public ModelAndView newScheduleInterview(
											HttpServletRequest request,
											HttpServletResponse response,
											ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject,
											@ModelAttribute("interviewScheduleToInsert") AdmissionProcess interviewScheduleToInsert) {
		ModelAndView mav = null;
		try {
			List<AdmissionProcess> interviewScheduleList = null;
			logger.info("newScheduleInterview() method in AdmissionController");
			
			String strDate = request.getParameter("interviewDate");
			String strTime = request.getParameter("interviewTime");
			List<Form> formIdList = new ArrayList<Form>();
			/* concatenate date and time and set into interviewschedule object */
			interviewScheduleToInsert.setUpdatedBy(sessionObject.getUserId());
			interviewScheduleToInsert.setInterviewDateAndTime((strDate + "::" + strTime));
			String[] formIds = request.getParameterValues("formId");
			if (formIds!=null && formIds.length != 0) {
				/* retrieve each form id and set to InterviewSchedule object */
				for (int i = 0; i < (formIds.length); i++) {
					String strFormid = formIds[i];
					Form form = new Form();
					form.setStrFormId(strFormid);
					formIdList.add(form);
				}
				interviewScheduleToInsert.setFormList(formIdList);				
				AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(interviewScheduleToInsert);
				/*if ("newInterviewSchedule".equals(request.getParameter("decision"))) {
					logger.info("calling AdmissionService.java class by calling insertInterviewSchedule() ");*/
					interviewScheduleList = admissionService.insertInterviewSchedule(interviewScheduleToInsert);
					//System.out.println("interviewScheduleList size = "+interviewScheduleList.size());
					if (interviewScheduleList != null && interviewScheduleList.size() != 0) {
						
						model.addAttribute("StreamClassYearCourse", admissionForm);
					}
				//}
				/*if ("editInterviewSchedule".equals(request.getParameter("decision"))) {
					logger.info("calling AdmissionService.java class by calling insertInterviewSchedule() ");
					interviewScheduleList = admissionService.updateInterviewSchedule(interviewScheduleToInsert);
					if (interviewScheduleList != null && interviewScheduleList.size() != 0) {
						
						model.addAttribute("StreamClassYearCourse",admissionForm);
						
					}
				}*/
				
				AdmissionProcess admissionProcess = admissionService.getInterviewSchedule(interviewScheduleToInsert);
				admissionProcess.setCourseClass(interviewScheduleToInsert.getCourseClass());
				//String admissionDriveState = admissionService.getAdmissionDriveState(strDriveName);
				admissionProcess.setFormName(interviewScheduleToInsert.getFormName());
				admissionProcess.setAdmissionYear(interviewScheduleToInsert.getAdmissionYear());
				model.addAttribute("contactForm", admissionProcess);
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("submittedAdmisionInterviewScheduleList", interviewScheduleList);
				String admissionDriveState = admissionService.getAdmissionDriveState(interviewScheduleToInsert.getFormName());

				model.addAttribute("admissionDriveStateNew", admissionDriveState );
				/*admissionProcess = admissionService.getInterviewSchedule(admissionProcess);
				List<Form> formIdList = admissionProcess.getFormList();
				if (admissionProcess != null && formIdList.size() != 0) {
					admissionProcess.setCourseClass(request.getParameter("courseClass"));
					admissionProcess.setAdmissionYear(request.getParameter("year"));
					admissionProcess.setFormName(request.getParameter("driveName"));
					model.addAttribute("StreamClassYearCourse", admissionForm);
					model.addAttribute("contactForm", admissionProcess);*/
				//mav = submittedAdmisionInterviewScheduleListPaging(request, response,model);
				mav = new ModelAndView("admission/scheduleInterview");
			} else {
				String error = "You should select atleast one candidate.";
				model.addAttribute("ResultError", error);
				mav = new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			logger.error("Exception in newScheduleInterview() in AdmisionController:");
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value="/submittedAdmisionInterviewScheduleListPaging",method=RequestMethod.GET)
	public ModelAndView submittedAdmisionInterviewScheduleListPaging(HttpServletRequest request, HttpServletResponse response,ModelMap model){		
		try{
			logger.info("submittedAdmisionInterviewScheduleListPaging() method in Admissioncontroller: ");
			AdmissionForm admissionForm = admissionService.getStreamClassYearForPaging();
			model.addAttribute("StreamClassYearCourse", admissionForm);
			PagedListHolder<AdmissionProcess> submittedAdmisionInterviewSchedulePagedListHolder = admissionService.getSubmittedAdmisionInterviewScheduleListPaging(request);	
				if (submittedAdmisionInterviewSchedulePagedListHolder != null) {
					model.addAttribute("first", submittedAdmisionInterviewSchedulePagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", submittedAdmisionInterviewSchedulePagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", submittedAdmisionInterviewSchedulePagedListHolder.getNrOfElements());
					model.addAttribute("submittedAdmisionInterviewScheduleList", submittedAdmisionInterviewSchedulePagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in submittedAdmisionInterviewScheduleListPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView("admission/submittedInterviewScheduleList");
	}
	
	/**
	 * This method get details on particular scheduled candidate and return to
	 * editScheduleInterview.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 *         used
	 *//*
	@RequestMapping(value = "/editScheduleInterview", method = RequestMethod.GET)
	public ModelAndView editScheduleInterview(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionProcess interviewSchedule) {
		String strView = null;
		try {

			logger.info("editScheduleInterview() method in AdmissionController: ");
			if (!request.getParameter("query").equalsIgnoreCase("0")) {
				return getSearchList(request, response, model,interviewSchedule);
			}
			AdmissionForm admissionForm = admissionService
					.getStreamClassYearCourseDetails(interviewSchedule);
			logger.info("calling getInterviewScheduleForEdit(AdmissionProcess interviewSchedule) in AdmissionServiceImpl");
			interviewSchedule = admissionService
					.getInterviewScheduleForEdit(interviewSchedule);
			if (interviewSchedule != null) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("Result", interviewSchedule);
				strView = "admission/editScheduleInterview";
			} else {
				model.addAttribute("ResultError", "Result not available");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error(
					"Exception in editScheduleInterview() method in AdmissionController:",
					e);
		}
		return new ModelAndView(strView);
	}
*/
	/**
	 * This method get previous scheduled candidate list and return to
	 * previousDetailsScheduleInterview.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/viewPreviousScheduleInterviewDetails", method = RequestMethod.GET)
	public @ResponseBody
	 String viewPreviousScheduleInterviewDetails(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, AdmissionProcess interviewSchedule) {
		String scheduleInterviewDetails = null;
		try {
			/*if ("historyInterviewScheduleList".equals(request
					.getParameter("pageName"))) {
				if (!request.getParameter("query").equalsIgnoreCase("0")) {
					try {
						return historySearch(request, response, model,
								interviewSchedule);
					} catch (Exception e) {
						logger.error(
								"Exception In historyInterviewScheduleList if block IN viewPreviousScheduleInterviewDetails() method in AdmissionController: ",
								e);
					}
				}
			}*/
			/*if ("previousInterviewScheduleList".equals(request
					.getParameter("pageName"))) {
				if (!request.getParameter("query").equalsIgnoreCase("0")) {
					try {
						return previousSearch(request, response, model,
								interviewSchedule);
					} catch (Exception e) {
						logger.error(
								"Exception In previousInterviewScheduleList if block IN viewPreviousScheduleInterviewDetails() method in AdmissionController: ",
								e);
					}
				}
			}*/
			AdmissionForm admissionForm = admissionService
					.getStreamClassYearCourseDetails(interviewSchedule);
			logger.info("calling getInterviewScheduleForEdit(AdmissionProcess interviewSchedule) in AdmissionServiceimpl.java");
			interviewSchedule = admissionService
					.getInterviewScheduleForEdit(interviewSchedule);
			//model.addAttribute("StreamClassYearCourse", admissionForm);
			//model.addAttribute("Result", interviewSchedule);
			if(interviewSchedule!=null){
				//System.out.println("Academic Year:"+interviewSchedule.getAdmissionYear()+"::"+interviewSchedule.getCourseClass()+"::"+admissionForm.getCourseType()+"::"+interviewSchedule.getFormId());
				scheduleInterviewDetails = scheduleInterviewDetails+";"+interviewSchedule.getAdmissionYear()+";"+admissionForm.getCourseName()+";"+admissionForm.getCourseType()+";"+interviewSchedule.getFormId()+
											";"+interviewSchedule.getInterviewDate()+";"+interviewSchedule.getInterviewTime()+";"+admissionForm.getCourseClass()+";"+interviewSchedule.getExaminerName()+";"+interviewSchedule.getReviewerName()+
											";"+interviewSchedule.getRoomNo()+";"+interviewSchedule.getVenue();
			}
		} catch (Exception e) {
			logger.error(
					"Exception In viewPreviousScheduleInterviewDetails() method in AdmissionController",
					e);
		}
		return (new Gson().toJson(scheduleInterviewDetails));
	}

	/**
	 * This method get list of Form ID from AdmissionService.java class and
	 * return to makeInterviewResult.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */

	@RequestMapping(value = "/addInterviewResult", method = RequestMethod.GET)
	public ModelAndView addInterviewResult(HttpServletRequest request,HttpServletResponse response, ModelMap model,
											AdmissionProcess makeInterviewResultObject) {
		String strView = null;
		List<AdmissionProcess> interviewResultListFromDb = null;
		try {
			String status = request.getParameter("admissionDriveState");
			List<AdmissionProcess> interviewScheduleList = null;
			/* calling AdmissionService.java class for retrieve list of form id */
			String courseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			makeInterviewResultObject.setAdmissionYear(strAdmissionYear);
			makeInterviewResultObject.setCourseClass(courseClass);
			makeInterviewResultObject.setFormName(strDriveName);
			makeInterviewResultObject.setFormStatus(status);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(makeInterviewResultObject);
			logger.info("calling getScheduledFormList(makeInterviewResultObject) method in AdmissionServiceImpl");
			interviewScheduleList = admissionService.getScheduledFormList(makeInterviewResultObject);
			interviewResultListFromDb = admissionService.getSelectedReviewdNonselectedStudentList(makeInterviewResultObject);
			String admissionDriveState = admissionService.getAdmissionDriveState(strDriveName);
			String admissionDriveStatus = admissionService.getstatusOfAdmissionDrive(makeInterviewResultObject);


			//if (interviewResultListFromDb != null && interviewResultListFromDb.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("FORMID", interviewScheduleList);
				model.addAttribute("interviewResultListFromDb", interviewResultListFromDb);
				model.addAttribute("contactForm", makeInterviewResultObject);
				String newStatus = null;
				if(admissionDriveStatus !=null){
					if(admissionDriveStatus.equalsIgnoreCase("DONE")){
						newStatus = "DONE";		
						model.addAttribute("admissionDriveState", newStatus);
					}
				}else{
					model.addAttribute("admissionDriveState", status);
				}
				model.addAttribute("admissionDriveStateNew", admissionDriveState );
				strView = "admission/interviewResult";
				/*	} else {
				System.out.println("within else");
				return selectedCandidatesList(request, response, model,	makeInterviewResultObject);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in addInterviewResult() method in AdmissionController:  ",e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * this method return review candidate list
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param makeInterviewResultObject
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/goBackFromReview", method = RequestMethod.GET)
	public ModelAndView goBackFromReview(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("makeInterviewResultObject") AdmissionProcess makeInterviewResultObject) {
		String strView = null;
		try {
			logger.info("goBackFromReview() method in AdmissionController:");
			List<AdmissionProcess> interviewScheduleList = null;
			String courseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			makeInterviewResultObject.setAdmissionYear(strAdmissionYear);
			makeInterviewResultObject.setCourseClass(courseClass);
			makeInterviewResultObject.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService
					.getStreamClassYearCourseDetails(makeInterviewResultObject);
			logger.info("calling goBackFromReview(AdmissionProcess makeInterviewResultObject) method in AdmissionServiceImpl:");
			interviewScheduleList = admissionService
					.goBackFromReview(makeInterviewResultObject);
			if (interviewScheduleList != null
					&& interviewScheduleList.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("FORMID", interviewScheduleList);
				strView = "admission/submitInterviewResult";
			} else {
				return selectedCandidatesList(request, response, model,
						makeInterviewResultObject);
			}
		} catch (Exception e) {
			logger.error(
					"Exception in goBackFromReview() method in AdmissionController: ",
					e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * this method retrieve all selected candidate and return to
	 * selectedCandidatesList.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/nextGoToSelectedCandidatesList", method = RequestMethod.GET)
	public ModelAndView nextGoToSelectedCandidatesList(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("makeInterviewResultObject") AdmissionProcess makeInterviewResultObject) {
		ModelAndView mav = null;
		try {
			logger.info("nextGoToSelectedCandidatesList() method in AdmissionController");
			List<AdmissionProcess> interviewScheduleList = null;
			interviewScheduleList = new ArrayList<AdmissionProcess>();
			String courseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			makeInterviewResultObject.setAdmissionYear(strAdmissionYear);
			makeInterviewResultObject.setCourseClass(courseClass);
			makeInterviewResultObject.setFormName(strDriveName);
			makeInterviewResultObject.setFormStatus("SELECTED");
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(makeInterviewResultObject);
			logger.info("calling getInterviewedCandidateList(AdmissionProcess makeInterviewResultObject) method in AdmissionServiceImpl:nextGoToSelectedCandidatesList() method in AdmissionController: ");
			interviewScheduleList = admissionService.getInterviewedCandidateList(makeInterviewResultObject);
			if (interviewScheduleList != null && interviewScheduleList.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				String control="selectedCandidatesList";
				mav = admissionInterviewResultListPaging(request, response, model,control);
			} else {
				makeInterviewResultObject.setFormStatus("REVIEW");
				logger.info("calling getInterviewedCandidateList(AdmissionProcess makeInterviewResultObject) method in AdmissionServiceImpl:nextGoToSelectedCandidatesList() method in AdmissionController: ");
				interviewScheduleList = admissionService.getInterviewedCandidateList(makeInterviewResultObject);
				if (interviewScheduleList != null && interviewScheduleList.size() != 0) {
					model.addAttribute("StreamClassYearCourse", admissionForm);
					String control="reviewedCandidatesList";
					mav = admissionInterviewResultListPaging(request, response, model,control);
				} else {
					makeInterviewResultObject.setFormStatus("NOTSELECTED");
					logger.info("calling getInterviewedCandidateList(AdmissionProcess makeInterviewResultObject) method in AdmissionServiceImpl:nextGoToSelectedCandidatesList() method in AdmissionController: ");
					interviewScheduleList = admissionService.getInterviewedCandidateList(makeInterviewResultObject);
					if (interviewScheduleList != null && interviewScheduleList.size() != 0) {
						model.addAttribute("StreamClassYearCourse",admissionForm);
						String control="notSelectedCandidatesList";
						mav = admissionInterviewResultListPaging(request, response, model,control);
					} else {
						logger.info("calling getScheduledFormList(AdmissionProcess makeInterviewResultObject) method in AdmissionServiceImpl:nextGoToSelectedCandidatesList() method in AdmissionController: ");
						interviewScheduleList = admissionService.getScheduledFormList(makeInterviewResultObject);
						if (interviewScheduleList != null && interviewScheduleList.size() != 0) {
							model.addAttribute("StreamClassYearCourse",admissionForm);
							model.addAttribute("FORMID", interviewScheduleList);
							mav = new ModelAndView("admission/submitInterviewResult");
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in nextGoToSelectedCandidatesList() method AdmissionController: ",e);
		}
		return mav;
	}

	/**
	 * this method return to selected candidate list.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/candidateInterviewList", method = RequestMethod.GET)
	public ModelAndView selectedCandidatesList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionProcess interviewResult) {
		ModelAndView mav = null;
		List<AdmissionProcess> interviewResultListFromDb = null;
		try {
			logger.info("selectedCandidatesList() method in AdmissionController.");
			String strCourseClass = request.getParameter("courseClass");
			String strInterviewStatus = request.getParameter("interviewStatus");
			interviewResult.setCourseClass(strCourseClass);
			interviewResult.setFormStatus(strInterviewStatus);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(interviewResult);
			logger.info("calling getInterviewedCandidateList(AdmissionProcess interviewResult) method in AdmissionServiceImpl.");
			interviewResultListFromDb = admissionService.getInterviewedCandidateList(interviewResult);
			if (interviewResultListFromDb != null && interviewResultListFromDb.size() != 0) {
				if (interviewResultListFromDb.get(0).getFormStatus().equals("SELECTED")) {
					model.addAttribute("StreamClassYearCourse", admissionForm);
					model.addAttribute("Result", interviewResultListFromDb);
					String control="selectedCandidatesList";
					mav = admissionInterviewResultListPaging(request, response, model,control);
				} else if (interviewResultListFromDb.get(0).getFormStatus().equals("REVIEW")) {
					interviewResult.setFormStatus("SELECTED");
					logger.info("calling getInterviewedCandidateList(AdmissionProcess interviewResult) method in AdmissionServiceImpl.");
					interviewResultListFromDb = admissionService.getInterviewedCandidateList(interviewResult);
					if (interviewResultListFromDb != null && interviewResultListFromDb.size() != 0) {
						if (interviewResultListFromDb.get(0).getFormStatus().equals("SELECTED")) {
							model.addAttribute("StreamClassYearCourse",admissionForm);
							String control="selectedCandidatesList";
							mav = admissionInterviewResultListPaging(request, response, model,control);
						}
					} else {
						String errorMessage = "There is no selected or no pending candidate for providing marks.";
						model.addAttribute("ResultError", errorMessage);
						mav = new ModelAndView("common/errorpage");
					}
				} else if (interviewResultListFromDb.get(0).getFormStatus().equals("NOTSELECTED")) {
					interviewResult.setFormStatus("SELECTED");
					logger.info("calling getInterviewedCandidateList(AdmissionProcess interviewResult) method in AdmissionServiceImpl.");
					interviewResultListFromDb = admissionService.getInterviewedCandidateList(interviewResult);
					if (interviewResultListFromDb != null && interviewResultListFromDb.size() != 0) {
						if (interviewResultListFromDb.get(0).getFormStatus().equals("SELECTED")) {
							model.addAttribute("StreamClassYearCourse",admissionForm);
							String control="selectedCandidatesList";
							mav = admissionInterviewResultListPaging(request, response, model,control);
						}
					} else {
						String errorMessage = "There is no selected or no pending candidate for providing marks.";
						model.addAttribute("ResultError", errorMessage);
						mav = new ModelAndView("common/errorpage");
					}
				} else {
					String errorMessage = "Data not found.....";
					model.addAttribute("ResultError", errorMessage);
					mav = new ModelAndView("common/errorpage");
				}
			} else {
				String errorMessage = "Data not found.....";
				model.addAttribute("ResultError", errorMessage);
				mav = new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			logger.error("Exception in selectedCandidatesList() method in AdmissionController.");
		}
		return mav;
	}

	/**
	 * this method return review Candidate list
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param interviewResult
	 * @return
	 * 
	 * 
	 */
	@RequestMapping(value = "/goToReviewCandidateList", method = RequestMethod.GET)
	public ModelAndView goToReviewCandidateList(
			@RequestParam("courseClass") String strCourseClass,
			@RequestParam("year") String strAdmissionYear,
			@RequestParam("driveName") String strDriveName,
			@RequestParam("status") String strStatus,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("interviewResult") AdmissionProcess interviewResult) {
		ModelAndView mav = null;
		List<AdmissionProcess> interviewResultListFromDb = null;
		try {
			interviewResult.setCourseClass(strCourseClass);
			interviewResult.setAdmissionYear(strAdmissionYear);
			interviewResult.setFormName(strDriveName);
			interviewResult.setFormStatus("REVIEW");
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(interviewResult);
			logger.info("calling getInterviewedCandidateList(AdmissionProcess interviewResult) method AdmissionServiceImpl");
			interviewResultListFromDb = admissionService.getInterviewedCandidateList(interviewResult);
			if (interviewResultListFromDb != null && interviewResultListFromDb.size() != 0) {
				if (interviewResultListFromDb.get(0).getFormStatus().equals("REVIEW")) {
					model.addAttribute("StreamClassYearCourse", admissionForm);
					model.addAttribute("Result", interviewResultListFromDb);
					String control="reviewedCandidatesList";
					mav = admissionInterviewResultListPaging(request, response, model,control);
				} else {
					String errorMessage = "Data not found.....";
					model.addAttribute("ResultError", errorMessage);
					mav = new ModelAndView("common/errorpage");
				}
			} else {
				interviewResult.setFormStatus(strStatus);
				logger.info("calling getInterviewedCandidateList(AdmissionProcess interviewResult) method AdmissionServiceImpl");
				interviewResultListFromDb = admissionService.getInterviewedCandidateList(interviewResult);
				if (interviewResultListFromDb != null && interviewResultListFromDb.size() != 0) {
					if (interviewResultListFromDb.get(0).getFormStatus().equals("SELECTED")) {
						model.addAttribute("StreamClassYearCourse",admissionForm);
						String control="selectedCandidatesList";
						mav = admissionInterviewResultListPaging(request, response, model,control);
					} else if (interviewResultListFromDb.get(0).getFormStatus().equals("REVIEW")) {
						model.addAttribute("StreamClassYearCourse",admissionForm);
						String control="reviewedCandidatesList";
						mav = admissionInterviewResultListPaging(request, response, model,control);
					} else if (interviewResultListFromDb.get(0).getFormStatus().equals("NOTSELECTED")) {
						model.addAttribute("StreamClassYearCourse",admissionForm);
						String control="notSelectedCandidatesList";
						mav = admissionInterviewResultListPaging(request, response, model,control);
					} else {
						String errorMessage = "Data not found.....";
						model.addAttribute("ResultError", errorMessage);
						mav = new ModelAndView("common/errorpage");
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in goToReviewCandidateList() method in AdmissionController");
		}
		return mav;
	}

	/**
	 * this method return not selected candidate list for a class,drive name,and
	 * year.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param interviewResult
	 * @return
	 * 
	 * 
	 */
	@RequestMapping(value = "/goToNotSelectedCandidateList", method = RequestMethod.GET)
	public ModelAndView goToNotSelectedCandidateList(
			@RequestParam("courseClass") String strCourseClass,
			@RequestParam("year") String strAdmissionYear,
			@RequestParam("driveName") String strDriveName,
			@RequestParam("status") String strStatus,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("interviewResult") AdmissionProcess interviewResult) {
		ModelAndView mav = null;
		try {
			List<AdmissionProcess> interviewResultListFromDb = null;
			interviewResult.setCourseClass(strCourseClass);
			interviewResult.setAdmissionYear(strAdmissionYear);
			interviewResult.setFormName(strDriveName);
			interviewResult.setFormStatus("NOTSELECTED");

			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(interviewResult);
			logger.info("calling getInterviewedCandidateList(AdmissionProcess interviewResult) method in AdmissionServiceImpl.");
			interviewResultListFromDb = admissionService.getInterviewedCandidateList(interviewResult);
			if (interviewResultListFromDb != null && interviewResultListFromDb.size() != 0) {
				if (interviewResultListFromDb.get(0).getFormStatus().equals("NOTSELECTED")) {
					model.addAttribute("StreamClassYearCourse", admissionForm);
					model.addAttribute("Result", interviewResultListFromDb);
					String control="notSelectedCandidatesList";
					mav = admissionInterviewResultListPaging(request, response, model,control);
				} else {
					String errorMessage = "Data not found.....";
					model.addAttribute("ResultError", errorMessage);
					mav = new ModelAndView("common/errorpage");
				}
			} else {
				interviewResult.setFormStatus(strStatus);
				logger.info("calling getInterviewedCandidateList(AdmissionProcess interviewResult) method in AdmissionServiceImpl.");
				interviewResultListFromDb = admissionService.getInterviewedCandidateList(interviewResult);
				if (interviewResultListFromDb != null
						&& interviewResultListFromDb.size() != 0) {
					if (interviewResultListFromDb.get(0).getFormStatus().equals("SELECTED")) {
						model.addAttribute("StreamClassYearCourse",admissionForm);
						model.addAttribute("Result", interviewResultListFromDb);
						String control="selectedCandidatesList";
						mav = admissionInterviewResultListPaging(request, response, model,control);
					} else if (interviewResultListFromDb.get(0).getFormStatus().equals("REVIEW")) {
						model.addAttribute("StreamClassYearCourse",	admissionForm);
						model.addAttribute("Result", interviewResultListFromDb);
						String control="reviewedCandidatesList";
						mav = admissionInterviewResultListPaging(request, response, model,control);
					} else if (interviewResultListFromDb.get(0).getFormStatus().equals("NOTSELECTED")) {
						model.addAttribute("StreamClassYearCourse",admissionForm);
						model.addAttribute("Result", interviewResultListFromDb);
						String control="notSelectedCandidatesList";
						mav = admissionInterviewResultListPaging(request, response, model,control);
					} else {
						String errorMessage = "Data not found.....";
						model.addAttribute("ResultError", errorMessage);
						mav = new ModelAndView("common/errorpage");
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception goToNotSelectedCandidateList() method in AdmissionController.");
		}
		return mav;
	}

	/**
	 * This method take Interview result details from newmakeInterviewResult.jsp
	 * and passing to AdmissionService.java and return list of candidate.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 *         used
	 */

	@RequestMapping(value = "/newAddInterviewResult", method = RequestMethod.POST)
	public ModelAndView newmakeInterviewResult(HttpServletRequest request,
											   HttpServletResponse response, ModelMap model,
											   AdmissionProcess interviewResultToDB,
											   @ModelAttribute("sessionObject") SessionObject sessionObject
											) {
		ModelAndView mav=null;
		List<AdmissionProcess> interviewScheduleList = null;
		try {
			logger.info("newmakeInterviewResult() method in AdmissionController: ");
			List<Marks> marksListToDB = new ArrayList<Marks>();
			List<AdmissionProcess> interviewResultListFromDb = null;
			int intTotalMarks = 0;
			String[] strArrSubject;
			String[] strArrMarks;
			strArrSubject = request.getParameterValues("subject");
			if (strArrSubject!=null && strArrSubject.length != 0) {
				strArrMarks = request.getParameterValues("marks");
				logger.info("In newAddInterviewResult() of AdmissionController - checking No of row: "+ ((strArrSubject.length)));
				/*
				 * retrieve each subject and marks and calculate total marks and
				 * set to MakeInterviewResult object
				 */
				for (int i = 0; i < (strArrSubject.length); i++) {
					String strSubjectName = strArrSubject[i];
					int intSubjectMarks = Integer.parseInt(strArrMarks[i]);
					Marks marks = new Marks();
					marks.setStrSubjectName(strSubjectName);
					marks.setIntSubjectMarks(intSubjectMarks);
					marks.setStrFormId(interviewResultToDB.getFormId());
					intTotalMarks += intSubjectMarks;
					marksListToDB.add(marks);
				}
				interviewResultToDB.setMarksList(marksListToDB);
				interviewResultToDB.setTotalMarks(intTotalMarks);
				interviewResultToDB.setUpdatedBy(sessionObject.getUserId());
			}
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(interviewResultToDB);
			if ("editSubmitInterviewResult".equals(request.getParameter("control"))) {
				logger.info("call updateResultDetails() in AdmissionService.java class for insert interview result details ");
				interviewResultListFromDb = admissionService.updateResultDetails(interviewResultToDB);
			}
			if ("SubmitInterviewResult".equals(request.getParameter("control"))) {
				logger.info("call insertResultDetailsService() in AdmissionService.java class for insert interview result details");
				interviewResultListFromDb = admissionService.insertResultDetails(interviewResultToDB);
			}
			//String strDriveName = request.getParameter("formName");
			
			String admissionDriveState = admissionService.getAdmissionDriveState(interviewResultToDB.getFormName());
			model.addAttribute("admissionDriveStateNew", admissionDriveState );
			AdmissionProcess contactForm = interviewResultListFromDb.get(0);
			if(interviewResultListFromDb != null){
				model.addAttribute("StreamClassYearCourse", admissionForm);
				interviewScheduleList = admissionService.getScheduledFormList(interviewResultToDB);
				model.addAttribute("FORMID", interviewScheduleList);
				model.addAttribute("interviewResultListFromDb", interviewResultListFromDb);
				model.addAttribute("contactForm", contactForm);
				mav = new ModelAndView("admission/interviewResult");
			}else {
				String errorMessage = "Admission Interview Result not available ....";
				model.addAttribute("ResultError", errorMessage);
				mav = new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in newmakeInterviewResult()method  in AdmissionController: ",e);
		}
		return mav;
	}
	
	@RequestMapping(value="/admissionInterviewResultListPaging",method=RequestMethod.GET)
	public ModelAndView admissionInterviewResultListPaging(HttpServletRequest request, HttpServletResponse response,
											  ModelMap model,
											  @RequestParam(value = "control", required = false) String control){		
		String strView="common/errorpage";
		try{
			if(control != null && control.equals("selectedCandidatesList")){
				strView="admission/selectedCandidatesList";
			}
			if(control != null && control.equals("reviewedCandidatesList")){
				strView="admission/reviewedCandidatesList";
			}
			if(control != null && control.equals("notSelectedCandidatesList")){
				strView="admission/notSelectedCandidatesList";
			}
			AdmissionForm admissionForm = admissionService.getStreamClassYearForPaging();
			model.addAttribute("StreamClassYearCourse", admissionForm);
			logger.info("admissionInterviewResultListPaging() method in Admissioncontroller: ");
			PagedListHolder<AdmissionProcess> admissionInterviewResultPagedListHolder = admissionService.getadmissionInterviewResultListPaging(request);	
				if (admissionInterviewResultPagedListHolder != null) {
					model.addAttribute("first", admissionInterviewResultPagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", admissionInterviewResultPagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", admissionInterviewResultPagedListHolder.getNrOfElements());
					model.addAttribute("admissionInterviewResultList", admissionInterviewResultPagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in admissionInterviewResultListPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView(strView);
	}
	
	
	/**
	 * this method get result details on particular candidate and return to
	 * editSubmittedInterviewResult.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/editSubmittedInterviewResult", method = RequestMethod.GET)
	public ModelAndView editSubmittedInterviewResult(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, AdmissionProcess interviewResult) {
		try {
			
			String formId = interviewResult.getFormId();
			
			String driveId = "formName"+formId;
			String formName = request.getParameter("formName"+formId);
			
			String courseClass = request.getParameter("courseClass"+formId);
			String admissionYear = request.getParameter("admissionYear"+formId);
			interviewResult.setCourseClass(courseClass);
			interviewResult.setAdmissionYear(admissionYear);
			interviewResult.setFormName(formName);
			logger.info("editSubmittedInterviewResult() method in AdmissionController: ");
			AdmissionProcess interviewResultFromDB = null;
			AdmissionForm admissionForm = admissionService
					.getStreamClassYearCourseDetails(interviewResult);
			logger.info("calling getInterviewResultForEdit(interviewResult) method in AdmissionController: ");
			interviewResultFromDB = admissionService
					.getInterviewResultForEdit(interviewResult);
			model.addAttribute("StreamClassYearCourse", admissionForm);
			model.addAttribute("InterviewResult", interviewResultFromDB);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Exception in editSubmittedInterviewResult() method in AdmissionController",
					e);
		}
		return new ModelAndView("admission/editSubmittedInterviewResult");
	}

	/**
	 * this method get list of previous selected candidate and return to
	 * previousDetailsSubmittedInterviewResult.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 *         used
	 */
	@RequestMapping(value = "/viewPreviousSubmittedInterviewResult", method = RequestMethod.GET)
	public ModelAndView viewPreviousSubmittedInterviewResult(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, AdmissionProcess interviewResult) {

		try {
			logger.info("viewPreviousSubmittedInterviewResult() method In AdmissionControler.java");
			if ("historySelectedCandidateList".equals(request
					.getParameter("pageName"))) {
				if (!request.getParameter("query").equalsIgnoreCase("0")) {
					try {
						return historySearch(request, response, model,
								interviewResult);
					} catch (Exception e) {
						logger.error(
								"Exception In historySelectedCandidateList if block in viewPreviousSubmittedInterviewResult() method in AdmissionController",
								e);
					}
				}
			}
			if ("previousSelectedCandidateList".equals(request
					.getParameter("pageName"))) {
				if (!request.getParameter("query").equalsIgnoreCase("0")) {
					try {
						return previousSearch(request, response, model,
								interviewResult);
					} catch (Exception e) {
						logger.error(
								"Exception In previousSelectedCandidateList if block in viewPreviousSubmittedInterviewResult() method in AdmissionController",
								e);
					}
				}
			}
			AdmissionForm admissionForm = admissionService
					.getStreamClassYearCourseDetails(interviewResult);
			AdmissionProcess interviewResultFromDB = null;
			logger.info("calling getInterviewResultForEdit(AdmissionProcess interviewResult) method in AdmissionController");
			interviewResultFromDB = admissionService
					.getInterviewResultForEdit(interviewResult);
			model.addAttribute("StreamClassYearCourse", admissionForm);
			model.addAttribute("InterviewResult", interviewResultFromDB);
		} catch (Exception e) {
			logger.error(
					"Exception In viewPreviousSubmittedInterviewResult() method in AdmissionController",
					e);
		}
		return new ModelAndView(
				"admission/previousDetailsSubmittedInterviewResult");
	}

	/**
	 * this method return to viewMeritList.jsp with populated selected
	 * candidate.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/viewMeritList", method = RequestMethod.GET)
	public ModelAndView viewMeritList(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										AdmissionProcess interviewResult,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("viewMeritList() method in AdmissionController");
			//System.out.println("within viewMeritList");
			String status = request.getParameter("admissionDriveState");
			
			interviewResult.setCourseClass(request.getParameter("courseClass"));
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			interviewResult.setAdmissionYear(strAdmissionYear);
			interviewResult.setFormName(strDriveName);
			interviewResult.setFormStatus(status);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(interviewResult);
			model.addAttribute("StreamClassYearCourse", admissionForm);
			interviewResult.setAdmissionForm(admissionForm);
			interviewResult.setUpdatedBy(sessionObject.getUserId());
			
			List<AdmissionProcess> interviewResultFromDB = admissionService.getInterviewResultForViewMeritList(interviewResult);
			List<AdmissionProcess> studentMeritList = admissionService.getAllStudentForMeritList(interviewResult);
			String admissionDriveState = admissionService.getAdmissionDriveState(strDriveName);
			
			String newStatus = null;
			String admissionDriveStatus = admissionService.getstatusOfAdmissionDrive(interviewResult);

			model.addAttribute("viewAdmissionMeritList", interviewResultFromDB);
			model.addAttribute("viewAllStudentMeritList", studentMeritList);
			model.addAttribute("strDriveName", strDriveName);
			model.addAttribute("admissionDriveStateNew", admissionDriveState );
			if(admissionDriveStatus != null){

				if(admissionDriveStatus.equalsIgnoreCase("DONE")){
					newStatus = "DONE";		
					model.addAttribute("admissionDriveState", newStatus);
				}
			}else{
				model.addAttribute("admissionDriveState", status);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in viewMeritList() method in AdmissionController",e);
		}
		return new ModelAndView("admission/viewMeritList");
	}
	
	@RequestMapping(value="/viewMeritListPaging",method=RequestMethod.GET)
	public ModelAndView viewMeritListPaging(HttpServletRequest request, 
											HttpServletResponse response,ModelMap model
										   ){		
		try{
			logger.info("viewMeritListPaging() method in Admissioncontroller: ");
			AdmissionForm admissionForm = admissionService.getStreamClassYearForPaging();
			model.addAttribute("StreamClassYearCourse", admissionForm);
			PagedListHolder<AdmissionProcess> submittedAdmissionFormPagedListHolder = admissionService.getSubmittedFormListPaging(request);	
				if (submittedAdmissionFormPagedListHolder != null) {
					model.addAttribute("first", submittedAdmissionFormPagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", submittedAdmissionFormPagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", submittedAdmissionFormPagedListHolder.getNrOfElements());
					model.addAttribute("viewAdmissionMeritList", submittedAdmissionFormPagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in viewMeritListPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView("admission/viewMeritList");
	}
	
	

	/**
	 * This method retrieve selected candidate with Fees Submission Last Date
	 * and passes to AdmissionService.
	 * 
	 * @param String
	 *            []
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/paymentDateSetUp",method=RequestMethod.POST)
	public ModelAndView paymentDateSetUp(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										AdmissionProcess interviewResultToDB,@ModelAttribute("sessionObject") SessionObject sessionObject
) {
		List<Form> formListToDB = new ArrayList<Form>();
		List<AdmissionProcess> interviewResultListFromDB = null;
		try {
			logger.info("paymentDateSetUp() method in AdmissionController.");
			String[] strArrFormId = request.getParameterValues("formId");
			if (strArrFormId!=null && strArrFormId.length != 0) {
				// strArrFormId = request.getParameterValues("formId");
				logger.info("In finalSelectedCandidate() of AdmissionController - checking No of row: "	+ ((strArrFormId.length)));
				/*
				 * retrieve each subject and marks and calculate total marks and
				 * set to MakeInterviewResult object
				 */
				for (int i = 0; i < (((strArrFormId.length))); i++) {
					String strFormId = strArrFormId[i];
					Form form = new Form();
					form.setStrFormId(strFormId);
					formListToDB.add(form);
				}
				/* retrieve date as dd/mm/yyyy and convert into yyyy-mm-dd */
				String dateReceivedFromUser = interviewResultToDB
						.getLastFeesSubmissionDate();
				/*DateFormat userDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd");
				Date date = userDateFormat.parse(dateReceivedFromUser);
				String convertedDate = dateFormatNeeded.format(date);*/
				interviewResultToDB.setLastFeesSubmissionDate(dateReceivedFromUser);
				interviewResultToDB.setFormList(formListToDB);
				interviewResultToDB.setUpdatedBy(sessionObject.getUserId());
				interviewResultToDB.setCourseClass(request.getParameter("courseClass"+strArrFormId[0]));
				interviewResultToDB.setFormName(request.getParameter("formName"+strArrFormId[0]));
				interviewResultToDB.setAdmissionYear(request.getParameter("admissionYear"+strArrFormId[0]));
				AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(interviewResultToDB);
				logger.info("calling insertSelectedCandidates(AdmissionProcess interviewResultToDB) method  in AdmisionServiceImpl");
				interviewResultListFromDB = admissionService.insertSelectedCandidates(interviewResultToDB);
				String class1 = request.getParameter("courseClass"+strArrFormId[0]);
				if(interviewResultListFromDB != null && interviewResultListFromDB.size() == 0) {
					AdmissionProcess newInterviewResult = new AdmissionProcess();
					newInterviewResult.setCourseClass(interviewResultToDB.getCourseClass());
					newInterviewResult.setFormName(interviewResultToDB.getFormName());
					newInterviewResult.setAdmissionYear(interviewResultToDB.getAdmissionYear());
					interviewResultListFromDB.add(newInterviewResult);
				} 
				List<AdmissionProcess> interviewResultFromDB = admissionService.getResultForViewMeritList(interviewResultToDB);
				List<AdmissionProcess> studentMeritList = admissionService.getAllStudentForMeritList(interviewResultToDB);
				String admissionDriveState = admissionService.getAdmissionDriveState(interviewResultToDB.getFormName());
				String strDriveName = request.getParameter("formName"+strArrFormId[0]);
				model.addAttribute("admissionDriveStateNew", admissionDriveState );
				model.addAttribute("viewAdmissionMeritList", interviewResultFromDB);
				model.addAttribute("viewAllStudentMeritList", studentMeritList);
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("strDriveName", strDriveName);//Naimisha 29062017
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in paymentDateSetUp() method in AdmissionController",e);
		}
		return new ModelAndView("admission/viewMeritList");
	}


	/**
	 * this method get details of
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param interviewResultToDB
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/historyPaymentDateSetUp")
	public ModelAndView historyPaymentDateSetUp(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionProcess interviewResultToDB) {
		try {
			logger.info("historyPaymentDateSetUp() method in AdmissionControler");
			List<Form> formListToDB = new ArrayList<Form>();
			List<AdmissionProcess> interviewResultListFromDB = null;
			if ("historyViewMeritList".equals(request.getParameter("pageName"))) {
				if (!request.getParameter("query").equalsIgnoreCase("0")) {
					try {
						return historySearch(request, response, model,
								interviewResultToDB);
					} catch (Exception e) {
						logger.error(
								"Exception in historyViewMeritList if block in historyPaymentDateSetUp() method in AdmissionController",
								e);
					}
				}
			}
			if ("previousViewMeritList"
					.equals(request.getParameter("pageName"))) {
				if (!request.getParameter("query").equalsIgnoreCase("0")) {
					try {
						return previousSearch(request, response, model,
								interviewResultToDB);
					} catch (Exception e) {
						logger.error(
								"Exception in previousViewMeritList if block in historyPaymentDateSetUp() method in AdmissionController",
								e);
					}
				}
			}
			String[] strArrFormId = request.getParameterValues("formId");
			if (request.getParameter("formId") != null) {
				logger.info("In finalSelectedCandidate() of AdmissionController - checking No of row: "
						+ ((strArrFormId.length)));
				/*
				 * retrieve each subject and marks and calculate total marks and
				 * set to MakeInterviewResult object
				 */
				for (int i = 0; i < (((strArrFormId.length))); i++) {
					String strFormId = strArrFormId[i];
					Form form = new Form();
					form.setStrFormId(strFormId);
					formListToDB.add(form);
				}

				/* retrieve date as dd/mm/yyyy and convert into yyyy-mm-dd */
				String dateReceivedFromUser = interviewResultToDB
						.getLastFeesSubmissionDate();
				DateFormat userDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = userDateFormat.parse(dateReceivedFromUser);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String convertedDate = dateFormatNeeded.format(date);
				interviewResultToDB.setLastFeesSubmissionDate(convertedDate);
				interviewResultToDB.setFormList(formListToDB);
				logger.info("calling insertSelectedCandidates(AdmissionProcess interviewResultToDB) method in AdmissionServiceImpl: historyPaymentDateSetUp() method in AdmissionControler");
				interviewResultListFromDB = admissionService
						.insertSelectedCandidates(interviewResultToDB);
				if (interviewResultListFromDB == null
						|| interviewResultListFromDB.size() != 0) {
					AdmissionProcess newInterviewResult = new AdmissionProcess();
					newInterviewResult.setCourseClass(interviewResultToDB
							.getCourseClass());
					newInterviewResult.setFormName(interviewResultToDB
							.getFormName());
					newInterviewResult.setAdmissionYear(interviewResultToDB
							.getAdmissionYear());
					interviewResultListFromDB.add(newInterviewResult);
				}
				model.addAttribute("Result", interviewResultListFromDB);
			}
		} catch (Exception e) {
			logger.error(
					"Exception in historyPaymentDateSetUp() method in AdmissionControler",
					e);
		}
		return new ModelAndView("admission/viewMeritList");
	}

	/**
	 * This method get all payment required candidate and return to
	 * paymentSetup.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @return ModelAndView
	 * 
	 * used
	 */
	@RequestMapping(value = "/finalSelectedCandidate")
	public ModelAndView finalSelectedCandidate(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("admissionProcess") AdmissionProcess admissionProcess) {
		try {
			logger.info("finalSelectedCandidate() method in AdmissionControler");
			admissionProcess.setCourseClass(request.getParameter("courseClass"));
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			String status = admissionProcess.getStatus();
			//System.out.println("status======="+status);
			admissionProcess.setAdmissionYear(strAdmissionYear);
			admissionProcess.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
			logger.info("calling geRequiredCandidates(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
			List<AdmissionProcess>allSelectedaCandidateList = admissionService.geRequiredCandidates(admissionProcess);
			String statusOfAdmission = admissionService.getstatusOfAdmissionDrive(admissionProcess);
			String admissionDriveState = admissionService.getAdmissionDriveState(strDriveName);
			String admissionDriveStatus = admissionService.getstatusOfAdmissionDrive(admissionProcess);

			//System.out.println(" final selectedCandidate admissionDriveState===="+admissionDriveState);
			model.addAttribute("StreamClassYearCourse", admissionForm);
			model.addAttribute("finalSelectedCandidateList", allSelectedaCandidateList);
			model.addAttribute("statusOfAdmission", statusOfAdmission);
			
			model.addAttribute("admissionDriveStateNew", admissionDriveState );
			String newStatus = null;
			if(admissionDriveStatus != null){
				if(admissionDriveStatus.equalsIgnoreCase("DONE")){
					newStatus = "DONE";		
					model.addAttribute("admissionDriveState", newStatus);
				}
			}else{
				model.addAttribute("admissionDriveState", status);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in finalSelectedCandidate() method in AdmissonController",e);
		}
		return new ModelAndView("admission/payment");
	}
/*
	@RequestMapping(value="/finalSelectedCandidatePaging",method=RequestMethod.GET)
	public ModelAndView finalSelectedCandidatePaging(HttpServletRequest request, HttpServletResponse response,ModelMap model){		
		try{
			logger.info("finalSelectedCandidatePaging() method in Admissioncontroller: ");
			AdmissionForm admissionForm = admissionService.getStreamClassYearForPaging();
			model.addAttribute("StreamClassYearCourse", admissionForm);
			PagedListHolder<AdmissionProcess> finalSelectedCandidatePagedListHolder = admissionService.getFinalSelectedCandidatePaging(request);	
				if (finalSelectedCandidatePagedListHolder != null) {
					model.addAttribute("first", finalSelectedCandidatePagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", finalSelectedCandidatePagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", finalSelectedCandidatePagedListHolder.getNrOfElements());
					model.addAttribute("finalSelectedCandidateList", finalSelectedCandidatePagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in finalSelectedCandidatePaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView("admission/paymentSetup");
	}*/
	
	/**
	 * this method get details of selected candidate for payment and return to
	 * payment.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/payment",method = RequestMethod.GET)
	public @ResponseBody
	String payment(HttpServletRequest request, HttpServletResponse response,
								ModelMap model,
								@ModelAttribute("interviewResultToDAO") AdmissionProcess interviewResultToDAO) {
		
		AdmissionProcess interviewResultFromDB = null;
		String paymentDetails = null;
		String fullName = null;
		try {
			logger.info("payment() method in AdmissionController: ");
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(interviewResultToDAO);
			logger.info("calling getPaymentList(AdmissionProcess interviewResultToDAO) method in AdmissionServiceImpl");
			interviewResultFromDB = admissionService.getPaymentList(interviewResultToDAO);
			
			if(interviewResultFromDB != null){
				String candidateMiddleName = interviewResultFromDB.getCandidateMiddleName();
				if(candidateMiddleName != null){
					 fullName = interviewResultFromDB.getCandidateFirstName()+" "+interviewResultFromDB.getCandidateMiddleName()+" "+interviewResultFromDB.getCandidateLastName();
				}else{
					fullName = interviewResultFromDB.getCandidateFirstName()+"  "+interviewResultFromDB.getCandidateLastName();
				}
				String paymentStatus = interviewResultFromDB.getStatus();
				String dateOfPayment = null;
				/*if(paymentStatus == null){
					paymentStatus = interviewResultFromDB.getCategory();
				}*/
				if(paymentStatus == null){
					paymentStatus = interviewResultFromDB.getFormStatus();
				}
				if(paymentStatus != null && paymentStatus.equalsIgnoreCase("ADMITTED")){
					paymentStatus = interviewResultFromDB.getStatus();
					dateOfPayment = interviewResultFromDB.getPaymentDate();
				}else if(paymentStatus != null && paymentStatus.equalsIgnoreCase("PAID")){
					paymentStatus = interviewResultFromDB.getFormStatus();
					dateOfPayment = interviewResultFromDB.getPaymentDate();
					/*paymentStatus  = "PAYMENT REQUIRED";
					dateOfPayment = interviewResultFromDB.getLastFeesSubmissionDate();*/
				}else{
					paymentStatus  = "PAYMENT REQUIRED";
					dateOfPayment = interviewResultFromDB.getLastFeesSubmissionDate();
				}
				
				
				if (interviewResultFromDB != null) {
					paymentDetails = paymentDetails+";"+admissionForm.getCourseClass()+";"+admissionForm.getAdmissionFormYear()
									+";"+admissionForm.getCourseName()+";"+admissionForm.getCourseType()+";"+interviewResultFromDB.getFormId()
									+";"+interviewResultFromDB.getFormName()+";"+fullName+";"+paymentStatus+";"+dateOfPayment;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in payment() method in AdmissionController", e);
		}
		return (new Gson().toJson(paymentDetails));
	}

	/**
	 * This method get courseClass from paymentDone.jsp and forward to
	 * AdmissionService.java class.
	 * 
	 * @param String
	 * 
	 * 
	 */
	public @ResponseBody
	@RequestMapping(value = "/closeOnProcess", method = RequestMethod.GET)
	String closeOnProcess(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@RequestParam("admissionDrive") String admissionDrive,
			@ModelAttribute("admissionProcess") AdmissionProcess admissionProcess,
			@ModelAttribute("sessionObject") SessionObject sessionObject
			) {
		//System.out.println(" within closeOnProcess");
		try {
			String[] strValues = admissionDrive.split(":");
			admissionProcess.setAdmissionYear(strValues[2]);
			admissionProcess.setFormName(strValues[0]);
			admissionProcess.setCourseClass(strValues[1]);
			admissionProcess.setUpdatedBy(sessionObject.getUserId());
			//System.out.println("strValues[2]========="+strValues[2]);
			//System.out.println("strValues[0]========="+strValues[0]);
			//System.out.println("strValues[1]========="+strValues[1]);
			logger.info("(calling admitStudents(AdmissionProcess admissionProcess) in AdmissionController)Parameter passed = "	+ admissionDrive);
			//admissionService.admitStudents(admissionProcess);
		} catch (Exception e) {
			logger.error(
					"Exception in closeOnProcess at AdmissionController: ", e);
		}
		return new Gson().toJson("done");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/admittedDriveList", method = RequestMethod.GET)
	public ModelAndView admittedDriveList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<AdmissionForm>admittedDriveList = null;
		try {
			logger.info("calling getAdmittedDriveList() method in AdmissionServiceImpl");
			admittedDriveList = admissionService.getAdmittedDriveList();
		}catch (Exception e) {
			logger.error("Exception in admittedDriveList() method in AdmisionControler",e);
		}
		model.addAttribute("admittedDriveList", admittedDriveList);
		return new ModelAndView("admission/admittedDrivelist");
	}
	
	@RequestMapping(value="/admittedDriveListPaging",method=RequestMethod.GET)
	public ModelAndView admittedDriveListPaging(HttpServletRequest request, HttpServletResponse response,ModelMap model){		
		try{
			logger.info("admittedDriveListPaging() method in Admissioncontroller: ");
			PagedListHolder<AdmissionForm> submittedAdmissionFormPagedListHolder = admissionService.getadmittedDriveListPaging(request);	
				if (submittedAdmissionFormPagedListHolder != null) {
					model.addAttribute("first", submittedAdmissionFormPagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", submittedAdmissionFormPagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", submittedAdmissionFormPagedListHolder.getNrOfElements());
					model.addAttribute("admittedDriveList", submittedAdmissionFormPagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in admittedDriveListPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView("admission/admittedDrivelist");
	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param admissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/admittedDriveListDetails", method = RequestMethod.GET)
	public ModelAndView admittedDriveListDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionProcess admissionProcess) {
		List<AdmissionProcess> admissionDriveDetailsList = null;
		try {
			logger.info("admittedDriveListDetails() method in AdmissionController: ");
			admissionProcess.setCourseClass(request.getParameter("courseClass"));
			admissionProcess.setAdmissionYear(request.getParameter("year"));
			admissionProcess.setFormName(request.getParameter("drivename"));
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
			logger.info("calling admittedDriveListDetails(AdmissionProcess admissionProcess) in AdmissionServiceImpl");
			admissionDriveDetailsList = admissionService.admittedDriveListDetails(admissionProcess);
			model.addAttribute("StreamClassYearCourse", admissionForm);
			model.addAttribute("admittedDriveListDetails", admissionDriveDetailsList);
		} catch (Exception e) {
			logger.error("Exception in admittedDriveListDetails() method in AdmissionController: ",e);
		}
		return new ModelAndView("admission/admittedStudentsList");
	}
	
	@RequestMapping(value="/admittedDriveListDetailsPaging",method=RequestMethod.GET)
	public ModelAndView admittedDriveListDetailsPaging(HttpServletRequest request, HttpServletResponse response,ModelMap model){		
		try{
			logger.info("submittedFormListPaging() method in Admissioncontroller: ");
			AdmissionForm admissionForm = admissionService.getStreamClassYearForPaging();
			model.addAttribute("StreamClassYearCourse", admissionForm);
			PagedListHolder<AdmissionProcess> admittedDriveListDetailsPagedListHolder = admissionService.getAdmittedDriveListDetailsPaging(request);	
				if (admittedDriveListDetailsPagedListHolder != null) {
					model.addAttribute("first", admittedDriveListDetailsPagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", admittedDriveListDetailsPagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", admittedDriveListDetailsPagedListHolder.getNrOfElements());
					model.addAttribute("admittedDriveListDetails", admittedDriveListDetailsPagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in admittedDriveListDetailsPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView("admission/admittedStudentsList");
	}

	/**
	 * It will return to paymentSetup.jsp with populated required candidate list
	 * for back button. If there are no required candidate list then it will
	 * return to either paymentDone.jsp or payment.jsp.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 *//*
	@RequestMapping(value = "/requiredCandidateListForBack", method = RequestMethod.GET)
	public ModelAndView requiredCandidateListForBack(
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("interviewResultToDB") AdmissionProcess interviewResultToDB) {
		ModelAndView mav = null;
		try {
			List<AdmissionProcess> interviewResultListFromDB = null;
			String strBack = request.getParameter("back");
			String strCourseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			interviewResultToDB.setCourseClass(strCourseClass);
			interviewResultToDB.setAdmissionYear(strAdmissionYear);
			interviewResultToDB.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(interviewResultToDB);
			logger.info("calling getRequiredMeritList(AdmissionProcess interviewResultToDB) method in AdmissionController");
			interviewResultListFromDB = admissionService.getRequiredMeritList(interviewResultToDB);
			if (interviewResultListFromDB != null && interviewResultListFromDB.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				mav = finalSelectedCandidatePaging(request, response,model);
			} else {
//				if ("paymentDone".equals(strBack)) {
//					List<AdmissionProcess> paymentDoneListFromDB = null;
//					logger.info("calling getPaymentListToDisplayBack(interviewResultToDB) method in AdmissionControler");
//					paymentDoneListFromDB = admissionService.getPaymentListToDisplayBack(interviewResultToDB);
//					if (paymentDoneListFromDB != null && paymentDoneListFromDB.size() != 0) {
//						model.addAttribute("StreamClassYearCourse",admissionForm);
//						model.addAttribute("Result", paymentDoneListFromDB);
//						strView = "admission/paymentDone";
//					} else {
//						String message = "Data not available.....";
//						model.addAttribute("ResultError", message);
//						strView = "common/errorpage";
//					}
//				}
//				if ("payment".equals(strBack)) {
//					AdmissionProcess paymentDoneFromDB = null;
//					String strFormId = request.getParameter("formId");
//					interviewResultToDB.setFormId(strFormId);
//					interviewResultToDB.setCourseClass(strCourseClass);
//					logger.info("calling getPaymentList(AdmissionProcess interviewResultToDB) method in AdmissionServiceImpl");
//					paymentDoneFromDB = admissionService.getPaymentList(interviewResultToDB);
//					if (paymentDoneFromDB != null) {
//						model.addAttribute("StreamClassYearCourse",admissionForm);
//						model.addAttribute("InterviewResult", paymentDoneFromDB);
//						strView = "admission/payment";
//					} else {
//						String message = "Data not available.....";
//						model.addAttribute("ResultError", message);
//						strView = "common/errorpage";
//					}
//				}
				String message = "Required merit list not available for admission";
				model.addAttribute("ResultError", message);
				mav = new ModelAndView("common/errorpage");

			}
		} catch (Exception e) {
			logger.error("Exception in requiredCandidateListForBack() method in AdmissionController",e);
		}
		return mav;
	}*/

	/**
	 * This method is responsible for searching related operations on admission.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @return ModelAndView
	 * @throws ParseException
	 * 
	 *             used
	 *//*
	@RequestMapping(value = "/search")
	public ModelAndView getSearchList(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									AdmissionProcess searchingParameter) throws ParseException {
		ModelAndView mav = null;
		try {
			logger.info("getSearchList() method in AdmissionController: ");
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			String strCourseClass = request.getParameter("courseClass");
			String strStatusToSearch = request.getParameter("statustosearch");
			String strDriveToSearch = request.getParameter("formName");
			searchingParameter.setCourseClass(strCourseClass);
			searchingParameter.setSearchStatus(strStatusToSearch);
			searchingParameter.setFormName(strDriveToSearch);
			if (strKey.equalsIgnoreCase("Year")) {
				searchingParameter.setAdmissionYear(strValue);
			}
			if (strKey.equalsIgnoreCase("Course Name")) {
				searchingParameter.setCourseName(strValue);
			}
			if (strKey.equalsIgnoreCase("Course Type")) {
				searchingParameter.setCourseType(strValue);
			}
			if (strKey.equalsIgnoreCase("Form ID")) {
				searchingParameter.setFormId(strValue);
			}
			if (strKey.equalsIgnoreCase("First Name")) {
				searchingParameter.setCandidateFirstName(strValue);
			}
			if (strKey.equalsIgnoreCase("Middle Name")) {
				searchingParameter.setCandidateMiddleName(strValue);
			}
			if (strKey.equalsIgnoreCase("Last Name")) {
				searchingParameter.setCandidateLastName(strValue);
			}
			if (strKey.equalsIgnoreCase("Contact Number")) {
				searchingParameter.setFormId(strValue);
			}
			if (strKey.equalsIgnoreCase("Email")) {
				searchingParameter.setFormId(strValue);
			}
			if (strKey.equalsIgnoreCase("Status")) {
				searchingParameter.setFormStatus(strValue);
			}
			if (strKey.equalsIgnoreCase("Form Submission Date")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntFormSubmissionDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Interview Date")) {
				searchingParameter.setInterviewDate(strValue);
			}
			if (strKey.equalsIgnoreCase("Payment Date")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntPaymentDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Admitted On")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntPaymentDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Fees Submission Date")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntLastFeesSubmissionDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Total Score")) {
				searchingParameter.setTotalMarks(Integer.parseInt(strValue));
			}
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(searchingParameter);

			logger.info("calling getSearchList(AdmissionProcess searchingParameter) method in AdmissionServiceImpl");
			List<AdmissionProcess> searchList = admissionService.getSearchList(searchingParameter);
			if (searchList != null && searchList.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				String strStatus = searchList.get(0).getFormStatus();
				if (strStatus.equalsIgnoreCase("SUBMITTED")) {
					mav = submittedFormListPaging(request, response,model);
				}
				if (strStatus.equalsIgnoreCase("SCHEDULED")) {
					mav = submittedAdmisionInterviewScheduleListPaging(request, response,model);
				}
				if (strStatus.equalsIgnoreCase("SELECTED")) {
					String control="selectedCandidatesList";
					mav = admissionInterviewResultListPaging(request, response, model,control);
				}
				if (strStatus.equalsIgnoreCase("REVIEW")) {
					String control="reviewedCandidatesList";
					mav = admissionInterviewResultListPaging(request, response, model,control);
				}
				if (strStatus.equalsIgnoreCase("NOTSELECTED")) {
					String control="notSelectedCandidatesList";
					mav = admissionInterviewResultListPaging(request, response, model,control);
				}
				if (strStatus.equalsIgnoreCase("SETFEE")) {
					mav = viewMeritListPaging(request, response,model);
				}
				if (strStatus.equalsIgnoreCase("REQUIRED") || (strStatus.equalsIgnoreCase("ADMITTED"))) {
					mav =finalSelectedCandidatePaging(request, response,model);
				}
			} else {
				model.addAttribute("ResultError", "Data not available.");
				mav = new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			logger.error("Exception in getSearchList() in AdmissionController: ", e);
		}
		return mav;
	}
*/
	/**
	 * this method is used for search in history pages
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param searchingParameter
	 * @return ModelAndView
	 * @throws ParseException
	 * 
	 * 
	 */
	@RequestMapping(value = "/historySearch")
	public ModelAndView historySearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionProcess searchingParameter) throws ParseException {
		logger.info("historySearch()method in AdmissionController:");
		ModelAndView mav=null;
		try {
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			String strCourseClass = request.getParameter("courseClass");
			String strStatusToSearch = request.getParameter("statustosearch");
			searchingParameter.setCourseClass(strCourseClass);
			searchingParameter.setSearchStatus(strStatusToSearch);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(searchingParameter);

			if (strKey.equalsIgnoreCase("Year")) {
				searchingParameter.setAdmissionYear(strValue);
			}
			if (strKey.equalsIgnoreCase("Course Name")) {
				searchingParameter.setCourseName(strValue);
			}
			if (strKey.equalsIgnoreCase("Course Type")) {
				searchingParameter.setCourseType(strValue);
			}
			if (strKey.equalsIgnoreCase("Form ID")) {
				searchingParameter.setFormId(strValue);
			}
			if (strKey.equalsIgnoreCase("First Name")) {
				searchingParameter.setCandidateFirstName(strValue);
			}
			if (strKey.equalsIgnoreCase("Middle Name")) {
				searchingParameter.setCandidateMiddleName(strValue);
			}
			if (strKey.equalsIgnoreCase("Last Name")) {
				searchingParameter.setCandidateLastName(strValue);
			}
			if (strKey.equalsIgnoreCase("Contact Number")) {
				searchingParameter.setFormId(strValue);
			}
			if (strKey.equalsIgnoreCase("Email")) {
				searchingParameter.setFormId(strValue);
			}
			if (strKey.equalsIgnoreCase("Status")) {
				searchingParameter.setFormStatus(strValue);
			}
			if (strKey.equalsIgnoreCase("Form Submission Date")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntFormSubmissionDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Interview Date")) {
				searchingParameter.setInterviewDate(strValue);
			}
			if (strKey.equalsIgnoreCase("Payment Date")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntPaymentDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Admitted On")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntPaymentDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Fees Submission Date")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntLastFeesSubmissionDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Total Score")) {
				searchingParameter.setTotalMarks(Integer.parseInt(strValue));
			}
			logger.info("calling getHistorySearchList(searchingParameter) method in AdmissionServiceImpl");
			List<AdmissionProcess> searchList = admissionService.getHistorySearchList(searchingParameter);
			if (searchList != null && searchList.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("AdmissionHistoryList", searchList);
				String strStatus = searchList.get(0).getFormStatus();
				if (strStatus.equalsIgnoreCase("SUBMITTED")) {
					String control="historySubmittedFormList";
					mav = historyListPagePaging(request, response,model,control);
				}
				if (strStatus.equalsIgnoreCase("SCHEDULED")) {
					String control="historyInterviewScheduleList";
					mav = historyListPagePaging(request, response,model,control);
				}
				if (strStatus.equalsIgnoreCase("SELECTED")) {
					String control="historySelectedCandidatesList";
					mav = historyListPagePaging(request, response,model,control);
				}
				if (strStatus.equalsIgnoreCase("SETFEE")) {
					String control="historyViewMeritList";
					mav = historyListPagePaging(request, response,model,control);
				}
				if ((strStatus.equalsIgnoreCase("PAID")) || (strStatus.equalsIgnoreCase("WAIVED"))) {
					String control="historyPaymentDone";
					mav = historyListPagePaging(request, response,model,control);
				}
			} else {
				String errorMessage = "Admission History not available";
				model.addAttribute("ResultError", errorMessage);
				mav = new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			logger.error("Exception in historySearch() method in AdmissionController: ",e);
		}
		return mav;
	}

	/**
	 * this method used for search parameter of admittedDrivelist.jsp and
	 * admissionList.jsp
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param searchingParameter
	 * @return ModelAndView
	 * @throws ParseException
	 * 
	 * 
	 */
	@RequestMapping(value = "/admissionDriveSearch")
	public ModelAndView admissionDriveSearch(HttpServletRequest request,
									HttpServletResponse response, ModelMap model,
									AdmissionForm searchingParameter) throws ParseException {
		ModelAndView mav = null;
		try {
			logger.info("In admissionDriveSearch() of Admissioncontroller.java");
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			if (strKey.equalsIgnoreCase("Year")) {
				searchingParameter.setAdmissionFormYear(strValue);
			}
			if (strKey.equalsIgnoreCase("Class")) {
				searchingParameter.setCourseClass(strValue);
			}
			if (strKey.equalsIgnoreCase("Stream")) {
				searchingParameter.setStreamName(strValue);
			}
			if (strKey.equalsIgnoreCase("Drive Name")) {
				searchingParameter.setAdmissionDriveName(strValue);
			}
			if (strKey.equalsIgnoreCase("Course Name")) {
				searchingParameter.setCourseName(strValue);
			}
			if (strKey.equalsIgnoreCase("Course Type")) {

				searchingParameter.setCourseType(strValue);
			}
			if (strKey.equalsIgnoreCase("Drive Start Date")) {
				DateUtility util = new DateUtility();
				int driveAStartDate = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntAdmissionDriveStartDate(driveAStartDate);
			}
			if (strKey.equalsIgnoreCase("Form Submission Last Date")) {
				DateUtility util = new DateUtility();
				int formSubmisionDate = (util.humanReadableFormatToEpoch(strValue));
				searchingParameter.setIntAdmissionFormSubmissionLastDate(formSubmisionDate);
			}
			if (strKey.equalsIgnoreCase("Drive Expected End Date")) {
				DateUtility util = new DateUtility();
				int driveExpectedEndDate = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntAdmissionDriveExpectedEndDate(driveExpectedEndDate);
			}
			if (strKey.equalsIgnoreCase("Drive Actual End Date")) {
				DateUtility util = new DateUtility();
				int driveActualEndDate = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntAdmissionDriveActualEndDate(driveActualEndDate);
			}
			if (strKey.equalsIgnoreCase("StatusToSearch")) {
				searchingParameter.setSearchStatus(strValue);
			}
			logger.info("calling admissionDriveSearch(AdmissionForm searchingParameter) In admissionService.java ");
			List<AdmissionForm> admissionDriveSearchList = admissionService.admissionDriveSearch(searchingParameter);
			if (admissionDriveSearchList != null && admissionDriveSearchList.size() != 0) {
				if ("admittedDrivelist".equals(request.getParameter("pageName"))) {
					mav =admittedDriveListPaging(request, response,model);
				}
				if ("admissionList".equals(request.getParameter("pageName"))) {
					mav=admissionListPaging(request, response,model);
				}
			}else {
				model.addAttribute("ResultError", "Data not available.");
				mav = new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			logger.error("Exception In admissionDriveSearch() of Admissioncontroller:",e);
		}
		return mav;
	}

	/**
	 * This method get previous admission list from AdmissionService.java class
	 * and matching with previous admission status it will forward information
	 * to that page.
	 * 
	 * @param String
	 * @param String
	 * @param String
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/previousListPage", method = RequestMethod.GET)
	public ModelAndView previousListPage(
			@RequestParam("courseClass") String strCourseClass,
			@RequestParam("status") String strFormStatus,
			@RequestParam("year") String strAdmissionYear,
			@RequestParam("driveName") String strDriveName,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("admissionProcess") AdmissionProcess admissionProcess) {
		ModelAndView mav = null;
		try {
			List<AdmissionProcess> previousAdmissionListFromDB = null;
			admissionProcess.setCourseClass(strCourseClass);
			admissionProcess.setFormStatus(strFormStatus);
			admissionProcess.setAdmissionYear(strAdmissionYear);
			admissionProcess.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
			logger.info("previousListPage()method in AdmissionControler");
			previousAdmissionListFromDB = admissionService.getPreviousAdmissionList(admissionProcess);
			if (previousAdmissionListFromDB != null && previousAdmissionListFromDB.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				if (previousAdmissionListFromDB.get(0).getFormStatus().equals("SUBMITTED")) {
					String control = "previousSubmittedFormList";
					mav = previousAdmissionListPaging(request, response,model,control);
				} else if (previousAdmissionListFromDB.get(0).getFormStatus().equals("SCHEDULED")) {
					String control = "previousInterviewScheduleList";
					mav = previousAdmissionListPaging(request, response,model,control);
				} else if (previousAdmissionListFromDB.get(0).getFormStatus().equals("SELECTED")) {
					String control = "previousSelectedCandidatesList";
					mav = previousAdmissionListPaging(request, response,model,control);
				} else if (previousAdmissionListFromDB.get(0).getFormStatus().equals("SETFEE")) {
					String control = "previousViewMeritList";
					mav = previousAdmissionListPaging(request, response,model,control);
				} else if (previousAdmissionListFromDB.get(0).getFormStatus().equals("PAID") || previousAdmissionListFromDB.get(0).getFormStatus().equals("WAIVED")) {
					String control = "previousPaymentDone";
					mav = previousAdmissionListPaging(request, response,model,control);
				}
			} else {
				if(strFormStatus.equals("SUBMITTED")){
					String errorMessage = "Submitted form list is not available for Admission";
					model.addAttribute("ResultError", errorMessage);
				}else if(strFormStatus.equals("SCHEDULED")){
					String errorMessage = "Interview schedule is not available for Admission";
					model.addAttribute("ResultError", errorMessage);
				}else if(strFormStatus.equals("SELECTED")){
					String errorMessage = "Selected candidate is not available for Admission";
					model.addAttribute("ResultError", errorMessage);
				}else if(strFormStatus.equals("SETFEE")){
					String errorMessage = "View merit list is not available for Admission";
					model.addAttribute("ResultError", errorMessage);
				}else if(strFormStatus.equals("PAIDWAIVED")){
					String errorMessage = "No admitted candidate available for Admission";
					model.addAttribute("ResultError", errorMessage);
				}
				mav = new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			logger.error("Exception in previousListPage() method in AdmissionController",e);
		}
		return mav;
	}
	
	@RequestMapping(value="/previousAdmissionListPaging",method=RequestMethod.GET)
	public ModelAndView previousAdmissionListPaging(HttpServletRequest request, HttpServletResponse response,
											  ModelMap model,
											  @RequestParam(value = "control", required = false) String control){		
		String strView="common/errorpage";
		try{
			AdmissionForm admissionForm = admissionService.getStreamClassYearForPaging();
			model.addAttribute("StreamClassYearCourse", admissionForm);
			if(control != null && control.equals("previousSubmittedFormList")){
				strView="admission/previousSubmittedFormList";
			}
			if(control != null && control.equals("previousInterviewScheduleList")){
				strView="admission/previousInterviewScheduleList";
			}
			if(control != null && control.equals("previousSelectedCandidatesList")){
				strView="admission/previousSelectedCandidatesList";
			}
			if(control != null && control.equals("previousViewMeritList")){
				strView="admission/previousViewMeritList";
			}
			if(control != null && control.equals("previousPaymentDone")){
				strView="admission/previousPaymentDone";
			}
			logger.info("previousAdmissionListPaging() method in Admissioncontroller: ");
			PagedListHolder<AdmissionProcess> previousAdmissionPagedListHolder = admissionService.getPreviousAdmissionListPaging(request);	
				if (previousAdmissionPagedListHolder != null) {
					model.addAttribute("first", previousAdmissionPagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", previousAdmissionPagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", previousAdmissionPagedListHolder.getNrOfElements());
					model.addAttribute("previousAdmissionList", previousAdmissionPagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in previousAdmissionListPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView(strView);
	}
	

	/**
	 * this method get class,formStatus,drive name, and year from
	 * 
	 * @param strCourseClass
	 * @param strFormStatus
	 * @param strAdmissionYear
	 * @param strDriveName
	 * @param request
	 * @param response
	 * @param model
	 * @param admissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/historyListPage", method = RequestMethod.GET)
	public ModelAndView historyListPage(
								@RequestParam("courseClass") String strCourseClass,
								@RequestParam("status") String strFormStatus,
								@RequestParam("year") String strAdmissionYear,
								@RequestParam("driveName") String strDriveName,
								HttpServletRequest request,HttpServletResponse response,
								ModelMap model,
								@ModelAttribute("admissionProcess") AdmissionProcess admissionProcess) {
		List<AdmissionProcess> previousAdmissionListFromDB = null;
		ModelAndView mav=null;
		try {
			admissionProcess.setCourseClass(strCourseClass);
			admissionProcess.setFormStatus(strFormStatus);
			admissionProcess.setAdmissionYear(strAdmissionYear);
			admissionProcess.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
			logger.info("calling getPreviousAdmissionList(AdmissionProcess admissionProcess) in AdmissionServiceImpl: ");
			previousAdmissionListFromDB = admissionService.getPreviousAdmissionList(admissionProcess);
			if (previousAdmissionListFromDB != null && previousAdmissionListFromDB.size() != 0) {
				model.addAttribute("AdmissionHistoryList", previousAdmissionListFromDB);
				model.addAttribute("StreamClassYearCourse", admissionForm);
				if (previousAdmissionListFromDB.get(0).getFormStatus().equals("SUBMITTED")) {
					String control="historySubmittedFormList";
					mav = historyListPagePaging(request, response,model,control);
				}else if (previousAdmissionListFromDB.get(0).getFormStatus().equals("SCHEDULED")) {
					String control="historyInterviewScheduleList";
					mav = historyListPagePaging(request, response,model,control);
				}else if (previousAdmissionListFromDB.get(0).getFormStatus().equals("SELECTED")) {
					String control="historySelectedCandidatesList";
					mav = historyListPagePaging(request, response,model,control);
				}else if (previousAdmissionListFromDB.get(0).getFormStatus().equals("SETFEE")) {
					String control="historyViewMeritList";
					mav = historyListPagePaging(request, response,model,control);
				}else if (previousAdmissionListFromDB.get(0).getFormStatus().equals("PAID")|| previousAdmissionListFromDB.get(0).getFormStatus().equals("WAIVED")) {
					String control="historyPaymentDone";
					mav = historyListPagePaging(request, response,model,control);
				}else {
					String errorMessage = "Admission History not available";
					model.addAttribute("ResultError", errorMessage);
					mav = new ModelAndView("common/errorpage");
				}
			} else {
				String errorMessage = "Admission History not available";
				model.addAttribute("ResultError", errorMessage);
				mav = new ModelAndView("common/errorpage");
			}
		}catch (Exception e) {
			logger.error("Exception In historyListPage() in AdmissionController", e);
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value="/historyListPagePaging",method=RequestMethod.GET)
	public ModelAndView historyListPagePaging(HttpServletRequest request, HttpServletResponse response,
											  ModelMap model,
											  @RequestParam(value = "control", required = false) String control){		
		String strView="common/errorpage";
		try{
			if(control != null && control.equals("historySubmittedFormList")){
				strView="admission/historySubmittedFormList";
			}
			if(control != null && control.equals("historyInterviewScheduleList")){
				strView="admission/historyInterviewScheduleList";
			}
			if(control != null && control.equals("historySelectedCandidatesList")){
				strView="admission/historySelectedCandidatesList";
			}
			if(control != null && control.equals("historyViewMeritList")){
				strView="admission/historyViewMeritList";
			}
			if(control != null && control.equals("historyPaymentDone")){
				strView="admission/historyPaymentDone";
			}
			AdmissionForm admissionForm = admissionService.getStreamClassYearForPaging();
			model.addAttribute("StreamClassYearCourse", admissionForm);
			logger.info("historyListPagePaging() method in Admissioncontroller: ");
			PagedListHolder<AdmissionProcess> historyAdmissionListFromDB = admissionService.getHistoryListPagePaging(request);	
				if (historyAdmissionListFromDB != null) {
					model.addAttribute("first", historyAdmissionListFromDB.getFirstElementOnPage() + 1);
					model.addAttribute("last", historyAdmissionListFromDB.getLastElementOnPage() + 1);
					model.addAttribute("total", historyAdmissionListFromDB.getNrOfElements());
					model.addAttribute("AdmissionHistoryList", historyAdmissionListFromDB);
				}
		}catch(NullPointerException e){
			logger.error("Exception in historyListPagePaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param searchingParameter
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/previousSearch")
	public ModelAndView previousSearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionProcess searchingParameter) throws ParseException {

		ModelAndView mav = null;
		try {
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			String strCourseClass = request.getParameter("courseClass");
			String strStatusToSearch = request.getParameter("statustosearch");
			searchingParameter.setCourseClass(strCourseClass);
			searchingParameter.setSearchStatus(strStatusToSearch);
			if (strKey.equalsIgnoreCase("Year")) {
				searchingParameter.setAdmissionYear(strValue);
			}
			if (strKey.equalsIgnoreCase("Course Name")) {
				searchingParameter.setCourseName(strValue);
			}
			if (strKey.equalsIgnoreCase("Course Type")) {
				searchingParameter.setCourseType(strValue);
			}
			if (strKey.equalsIgnoreCase("Form ID")) {
				searchingParameter.setFormId(strValue);
			}
			if (strKey.equalsIgnoreCase("First Name")) {
				searchingParameter.setCandidateFirstName(strValue);
			}
			if (strKey.equalsIgnoreCase("Middle Name")) {
				searchingParameter.setCandidateMiddleName(strValue);
			}
			if (strKey.equalsIgnoreCase("Last Name")) {
				searchingParameter.setCandidateLastName(strValue);
			}
			if (strKey.equalsIgnoreCase("Contact Number")) {
				searchingParameter.setFormId(strValue);
			}
			if (strKey.equalsIgnoreCase("Email")) {
				searchingParameter.setFormId(strValue);
			}
			if (strKey.equalsIgnoreCase("Status")) {
				searchingParameter.setFormStatus(strValue);
			}
			if (strKey.equalsIgnoreCase("Form Submission Date")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntFormSubmissionDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Interview Date")) {
				searchingParameter.setInterviewDate(strValue);
			}
			if (strKey.equalsIgnoreCase("Payment Date")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntPaymentDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Admitted On")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntPaymentDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Fees Submission Date")) {
				DateUtility util = new DateUtility();
				int epoch = util.humanReadableFormatToEpoch(strValue);
				searchingParameter.setIntLastFeesSubmissionDate(epoch);
			}
			if (strKey.equalsIgnoreCase("Total Score")) {
				searchingParameter.setTotalMarks(Integer.parseInt(strValue));
			}
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(searchingParameter);
			logger.info("calling getHistorySearchList(AdmissionProcess searchingParameter) method in AdmissionServiceImpl ");
			List<AdmissionProcess> searchList = admissionService.getHistorySearchList(searchingParameter);
			if (searchList != null && searchList.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("Result", searchList);
				String strStatus = searchList.get(0).getFormStatus();
				if (strStatus.equalsIgnoreCase("SUBMITTED")) {
					String control = "previousSubmittedFormList";
					mav = previousAdmissionListPaging(request, response,model,control);
				}
				if (strStatus.equalsIgnoreCase("SCHEDULED")) {
					String control = "previousInterviewScheduleList";
					mav = previousAdmissionListPaging(request, response,model,control);
				}
				if (strStatus.equalsIgnoreCase("SELECTED")) {
					String control = "previousSelectedCandidatesList";
					mav = previousAdmissionListPaging(request, response,model,control);
				}
				if (strStatus.equalsIgnoreCase("SETFEE")) {
					String control = "previousViewMeritList";
					mav = previousAdmissionListPaging(request, response,model,control);
				}
				if ((strStatus.equalsIgnoreCase("PAID"))|| (strStatus.equalsIgnoreCase("WAIVED"))) {
					String control = "previousPaymentDone";
					mav = previousAdmissionListPaging(request, response,model,control);
				}
			} else {
				model.addAttribute("ResultError", "Data not available.");
				mav = new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			logger.error("Exception in previousSearch() method in AdmissionController",e);
		}
		return mav;
	}

	/**
	 * This method return to submitNewAdmissionForm.jsp with generated form id.
	 * 
	 * @param String
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 * 
	 */
	/*Done by Naimisha*/
	@RequestMapping(value = "/submitAdmissionForm", method = RequestMethod.GET)
	public ModelAndView showForm(@RequestParam("year") String strYear,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, AdmissionProcess formSubmission) {
		String strView = null;
		List<AdmissionProcess> admissionFormSubmissionList = null;
		try {
			logger.info("showForm() method in AdmissionController:  ");
			UploadFile form = new UploadFile();
			model.addAttribute("FORM", form);
			String strCourseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			formSubmission.setAdmissionYear(strAdmissionYear);
			formSubmission.setCourseClass(strCourseClass);
			formSubmission.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(formSubmission);
			logger.info("calling getGeneratedFormIdList() method in AdmissionService to  get generated form id ");
			admissionFormSubmissionList = admissionService.getGeneratedFormIdList(formSubmission);
			//System.out.println("admissionFormSubmissionList size====="+admissionFormSubmissionList.size());
			List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();
			/* checking admissionFormSubmissionList is null or size 0 */
			if (admissionFormSubmissionList != null && admissionFormSubmissionList.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("FORMLIST", admissionFormSubmissionList);
				model.addAttribute("socialCategoryList", socialCategoryList);
				strView = "admission/submitNewAdmissionForm";
			} else {
				String message = "Forms  are not available for class "
						+ strCourseClass
						+ "."
						+ "|"
						+ "Please generate forms and then continue admission...";
				model.addAttribute("ResultError", message);
				model.addAttribute("checkMessage", "ceckmessage");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(
					"Exception in showForm() method in AdmissionController:  ",
					e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * This method return to submitNewAdmissionForm.jsp with generated form id
	 * for back button.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/submitAdmissionFormForBack.html", method = RequestMethod.GET)
	public ModelAndView showFormForBack(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionProcess formSubmission) {
		ModelAndView mav = null;
		try {
			logger.info("showFormForBack() method in AdmissionControllerImpl");
			UploadFile form = new UploadFile();
			model.addAttribute("FORM", form);
			String strCourseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			formSubmission.setAdmissionYear(strAdmissionYear);
			formSubmission.setCourseClass(strCourseClass);
			formSubmission.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(formSubmission);
			logger.info("calling getGeneratedFormIdList() method in AdmissionService to  get generated form id ");
			List<AdmissionProcess> admissionFormSubmissionList = admissionService.getGeneratedFormIdList(formSubmission);
			/*
			 * checking admissionFormSubmissionList is null or size 0 and if
			 * condition true it return to submitNewAdmissionForm.jsp otherwise
			 * it return to submittedFormList.jsp
			 */
			List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();
			if (admissionFormSubmissionList != null && admissionFormSubmissionList.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("FORMLIST", admissionFormSubmissionList);
				model.addAttribute("socialCategoryList", socialCategoryList);
				mav = new ModelAndView("admission/submitNewAdmissionForm");
			} else {
				AdmissionForm admissionForm1 = admissionService.getStreamClassYearCourseDetails(formSubmission);
				logger.info("calling to getAdmissionFormList() method in AdmissionService.java class to get submitted form List ");
				admissionFormSubmissionList =  admissionService.getAdmissionFormList(formSubmission);
				model.addAttribute("StreamClassYearCourse", admissionForm1);
				mav = submittedFormListPaging(request, response,model);
			}
		} catch (Exception e) {
			logger.error("Exception  in showFormForBack() method in AdmissionControllerImpl",e);
		}
		return mav;
	}

	/**
	 * This method return to editSubmittedAdmissionForm.jsp for editing on
	 * particular form id.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 *//*
	@RequestMapping(value = "/submitAdmissionFormForEdit", method = RequestMethod.GET)
	public ModelAndView showFormForEdit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("formSubmission") AdmissionProcess formSubmission) {
		String strView = null;
		try {
			logger.info("showFormForEdit() method in AdmissionController:");
			if (!request.getParameter("query").equalsIgnoreCase("0")) {
				try {
					return getSearchList(request, response, model,formSubmission);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(formSubmission);
			logger.info("calling getFormDetails()method in AdmissionService.java class  for get details on particular form id, and it return AdmissionProcess.class type object");
			formSubmission = admissionService.getFormDetails(formSubmission);
			List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();
			
			 * checking formSubmission object is null, if formSubmission object
			 * is not null then it will return to editSubmittedAdmissionForm.jsp
			 * otherwise it will return to common/errorpage.jsp page with a
			 * message.
			 
			if (formSubmission != null) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("contactForm", formSubmission);
				model.addAttribute("socialCategoryList", socialCategoryList);
				strView = "admission/editSubmittedAdmissionForm";
			} else {
				String message = "Data not found.....";
				model.addAttribute("ResultError", message);
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error(
					"Exception in showFormForEdit() method in AdmissionController: ",
					e);
		}
		return new ModelAndView(strView);
	}*/

	/**
	 * This method return to submittedFormList.jsp for next button.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */

	@RequestMapping(value = "/nextGoToSubmittedFormList", method = RequestMethod.GET)
	public ModelAndView showFormForNext(HttpServletRequest request,
										HttpServletResponse response, ModelMap model,
										AdmissionProcess formSubmission) {
		ModelAndView mav = null;
		try {
			logger.info("showFormForNext() method in AdmissionController: ");
			List<AdmissionProcess> admissionFormSubmissionList = null;
			UploadFile form = new UploadFile();
			model.addAttribute("FORM", form);
			String strCourseClass = request.getParameter("courseClass");
			String strAdmissionYear = request.getParameter("year");
			String strDriveName = request.getParameter("driveName");
			formSubmission.setAdmissionYear(strAdmissionYear);
			formSubmission.setCourseClass(strCourseClass);
			formSubmission.setFormName(strDriveName);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(formSubmission);
			logger.info("calling to getAdmissionFormList() method in AdmissionService.java class to get submitted form List");
			admissionFormSubmissionList = admissionService.getAdmissionFormList(formSubmission);
			/*
			 * checking admissionFormSubmissionList is null or , size is zero if
			 * admissionFormSubmissionList is not null or size not zero then it
			 * will return to submittedFormList.jsp otherwise it will return to
			 * submitNewAdmissionForm.jsp.
			 */
			List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();
			if (admissionFormSubmissionList != null && admissionFormSubmissionList.size() != 0) {
				model.addAttribute("StreamClassYearCourse", admissionForm);
				model.addAttribute("Result", admissionFormSubmissionList);
				mav = submittedFormListPaging(request, response,model); 
			} else {
				AdmissionForm admissionForm1 = admissionService.getStreamClassYearCourseDetails(formSubmission);
				model.addAttribute("StreamClassYearCourse", admissionForm1);
				logger.info("calling getGeneratedFormIdList() method in AdmissionService.java class to  get generated form id");
				admissionFormSubmissionList =  admissionService.getGeneratedFormIdList(formSubmission);
				model.addAttribute("FORMLIST", admissionFormSubmissionList);
				model.addAttribute("socialCategoryList", socialCategoryList);
				mav = new ModelAndView("admission/submitNewAdmissionForm");
			}
		} catch (Exception e) {
			logger.error("Exception in showFormForNext() in AdmissionController: ",e);
		}
		return mav;
	}

	/**
	 * This method get the details on particular form id and return to
	 * previousDetailsSubmittedAdmissionForm.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param AdmissionProcess
	 * @return ModelAndView
	 * 
	 * 
	 */

	@RequestMapping(value = "/viewPreviousAdmissionFormDetails", method = RequestMethod.GET)
	public @ResponseBody
	String viewPreviousAdmissionFormDetails(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, AdmissionProcess formSubmission) {
		String strView = null;
		String submittedFormDetails = null;
		try {
			logger.info("viewPreviousAdmissionFormDetails() method in AdmissionController.java:");
			/*if ("historySubmittedFormList".equals(request.getParameter("pageName"))) {
				if (!request.getParameter("query").equalsIgnoreCase("0")) {
					return historySearch(request, response, model,formSubmission);
				}
			}*/
			/*if ("previousSubmittedFormList".equals(request
					.getParameter("pageName"))) {
				if (!request.getParameter("query").equalsIgnoreCase("0")) {
					return previousSearch(request, response, model,formSubmission);
				}
			}*/
			
			AdmissionForm admissionForm = admissionService
					.getStreamClassYearCourseDetails(formSubmission);
			logger.info("calling getFormDetails(AdmissionProcess formSubmission)method in AdmissionService.java class  for get details on particular form id, and it return AdmissionProcess.class type object");
			formSubmission = (AdmissionProcess) admissionService
					.getFormDetails(formSubmission);
			/*
			 * checking formSubmission object is null, if formSubmission object
			 * is not null then it will return to
			 * previousDetailsSubmittedAdmissionForm.jsp otherwise it will
			 * return to common/errorpage.jsp page with a message.
			 */
			if (formSubmission != null) {
				//model.addAttribute("StreamClassYearCourse", admissionForm);
				//model.addAttribute("contactForm", formSubmission);
				submittedFormDetails = submittedFormDetails+";"+ admissionForm.getAdmissionFormYear()+";"+admissionForm.getCourseName()+";"+admissionForm.getCourseType()+";"+
									   formSubmission.getFormId()+";"+formSubmission.getFormSubmissionDate()+";"+formSubmission.getCandidateFirstName()+";"+formSubmission.getCandidateMiddleName()+";"+formSubmission.getCandidateLastName()+
									   ";"+formSubmission.getCourseClass()+";"+formSubmission.getCandidateEmail()+";"+formSubmission.getCandidateContactNo();
				
				
			} 
		} catch (Exception e) {
			logger.error(
					"Exception IN viewPreviousAdmissionFormDetails() method in AdmissionController: ",
					e);
		}
		
		
		return (new Gson().toJson(submittedFormDetails));
	}

	/**
	 * This method is use to set file path,create directory and zip file for new
	 * candidate and return to submittedFormList.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param UploadFile
	 * @param AdmissionProcess
	 * @param BindingResult
	 * @param ModelMap
	 * @param SessionStatus
	 * @return ModelAndView
	 * 
	 * 
	 */
	@RequestMapping(value = "/submitNewAdmissionForm", method = RequestMethod.POST)
	public ModelAndView processNewForm(HttpServletRequest request,
										HttpServletResponse response,
										@ModelAttribute("FORM") UploadFile form,
										AdmissionProcess formSubmission,
										ModelMap model,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		
		String strView = null;
		String attachmentName = null;
		String attachmentFormat = null;
		Utility util = new Utility();
		ModelAndView mav = null;
		List<AdmissionProcess> admissionFormList = null;
		try {
			List<AdmissionProcess> formsubmissionList = new ArrayList<AdmissionProcess>();
			List<AdmissionProcess> admissionFormSubmissionList = null;
				String courseClass = request.getParameter("courseClass"); // Retrieve
																			// courseClass
																			// from
																			// 'FileUploadForm.jsp'
				String formID = request.getParameter("formId");
				/*String BasicRootPath = rootPath + folderName;
				String path = BasicRootPath + "/"
								+ formSubmission.getAdmissionYear() + "/"
								+ formSubmission.getFormName() + "/" + courseClass
								+ "/" + formID + "/";*/
				//System.out.println("formSubmission details =="+formSubmission.getAdmissionYear()+"::"+formSubmission.getFormName()+"::"+courseClass);
				// create directory and sub directory
				//File dir = new File(path);
				/*boolean isDirCreated = dir.mkdirs();
				if (isDirCreated)
					logger.debug("created");
				else
					logger.debug("Failed");*/
		/*		for (CommonsMultipartFile file : form.getFile()) {
					AdmissionProcess formSubmissionObject = new AdmissionProcess();
					if (file.getOriginalFilename() != "") {
						 create a zip file 
						String fileName = file.getOriginalFilename() + "."
								+ "zip";
						 set actual storage path 
						String filePath = path + fileName;
						logger.debug("'''" + filePath);
						 split root path 
						String s[] = rootPath.split("/");
						int dotPos1 = filePath.lastIndexOf("/");
						String setRootPath = s[s.length - s.length] + "/";
						 get sub folder 
						String subFolder = (filePath.substring((s[s.length
								- s.length].length()
								+ 1 + s[s.length - s.length + 1].length() + 1),
								dotPos1));
						 get file name with extension 
						String str = file.getOriginalFilename();
						int dotPos = str.lastIndexOf(".");
						 get file format 
						attachmentFormat = str.substring(dotPos + 1);
						 get file name 
						attachmentName = str.substring(0, dotPos);
						
						 * calling fileUpload() method in AdmissionService.java
						 * and get file size
						 
					//	int fileSize = util.fileUpload(form, filePath, file);
						 set attachment file 
						formSubmissionObject.setStorageRootPath(setRootPath);
						formSubmissionObject.setFolderName(subFolder);
						formSubmissionObject.setAttachmentName(attachmentName);
						formSubmissionObject
								.setAttachmentFormat(attachmentFormat);
						//formSubmissionObject.setAttachmentSize(fileSize); // set
																			// file
																			// size
						formSubmissionObject
								.setFolderObjectId("folderObject002");
						formSubmissionObject
								.setAttachmentObjectId("attachObj004");
						formSubmissionObject
								.setAttachmentCode("attachmentCode004");
						formSubmissionObject.setFolderCode("folderCode002");
						formSubmissionObject
								.setAttachmentDescription("attachmentDesc001");
						formSubmissionObject
								.setFolderDescription("folderdesc001");
						formsubmissionList.add(formSubmissionObject);
					}
				}*/
				formSubmission.setCourseClass(courseClass);
				/*
				 * retrieve date as dd/mm/yyyy and convert into yyyy-mm-dd and
				 * set to FormSubmission object
				 */
				/*String dateReceivedFromUser = formSubmission.getFormSubmissionDate();
				DateFormat userDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd");
				Date date = userDateFormat.parse(dateReceivedFromUser);
				String convertedDate = dateFormatNeeded.format(date);
				formSubmission.setFormSubmissionDate(convertedDate);*/
				/**
				 * calling insertAdmissionForm() method in AdmissionService.java
				 * class for insert form details and this method return
				 * List<AdmissionProcess> type of list and populate it to
				 * submittedFormList.jsp.
				 * 
				 */
				formSubmission.setUserId(sessionObject.getUserId());
				logger.info("calling insertAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmission) method in AdmissionServiceImpl ");
				AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(formSubmission);
				admissionFormSubmissionList = admissionService.insertAdmissionForm(formsubmissionList, formSubmission);
				admissionFormList = admissionService.getGeneratedFormIdList(formSubmission);
				List<SocialCategory> socialCategoryList = commonService.getExistingSocialCategory();
				//System.out.println("admissionFormSubmissionList size======="+admissionFormSubmissionList.size());
				if (admissionFormSubmissionList != null && admissionFormSubmissionList.size() != 0) {
					//System.out.println("B");
					mav = new ModelAndView("admission/formSubmission");
					model.addAttribute("formSubmissionDetails", formSubmission);
					model.addAttribute("submittedAdmissionFormList", admissionFormSubmissionList);
					model.addAttribute("StreamClassYearCourse", admissionForm);
					model.addAttribute("FORMLIST", admissionFormList);
					model.addAttribute("socialCategoryList", socialCategoryList);
					model.addAttribute("AdmissionOnProcessClass", formSubmission);
				}else{
					//System.out.println("C");
					model.addAttribute("ResultError","Record not available.....");
					mav = new ModelAndView("common/errorpage");
				}
				String admissionDriveState = admissionService.getAdmissionDriveState(formSubmission.getFormName());

				model.addAttribute("admissionDriveStateNew", admissionDriveState );
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in processNewForm() method in AdmissionController: ",e);
		}
		return mav;
	}
	
	@RequestMapping(value="/submittedFormListPaging",method=RequestMethod.GET)
	public ModelAndView submittedFormListPaging(HttpServletRequest request, 
												HttpServletResponse response,ModelMap model
												){		
		try{
			logger.info("submittedFormListPaging() method in Admissioncontroller: ");
			 AdmissionForm admissionForm = admissionService.getStreamClassYearForPaging();
			 model.addAttribute("StreamClassYearCourse", admissionForm);
			PagedListHolder<AdmissionProcess> submittedAdmissionFormPagedListHolder = admissionService.getSubmittedFormListPaging(request);	
				if (submittedAdmissionFormPagedListHolder != null) {
					model.addAttribute("first", submittedAdmissionFormPagedListHolder.getFirstElementOnPage() + 1);
					model.addAttribute("last", submittedAdmissionFormPagedListHolder.getLastElementOnPage() + 1);
					model.addAttribute("total", submittedAdmissionFormPagedListHolder.getNrOfElements());
					model.addAttribute("submittedAdmissionFormList", submittedAdmissionFormPagedListHolder);
				}
		}catch(NullPointerException e){
			logger.error("Exception in submittedFormListPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView("admission/submittedFormList");
	}

	/**
	 * This method is use to set file path,create directory and zip file for
	 * editing candidate record and return to submittedFormList.jsp
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param UploadFile
	 * @param AdmissionProcess
	 * @param BindingResult
	 * @param ModelMap
	 * @param SessionStatus
	 * @return ModelAndView
	 * 
	 * 
	 */

	@RequestMapping(value = "/submitEditAdmissionForm", method = RequestMethod.POST)
	public ModelAndView processEditForm(HttpServletRequest request,
										HttpServletResponse response,
										@ModelAttribute("FORM") UploadFile form,
										AdmissionProcess formSubmission, BindingResult result,
										ModelMap model) {
		ModelAndView mav = null;
		String attachmentName = null;
		String attachmentFormat = null;
		try {
			List<AdmissionProcess> formsubmissionList = new ArrayList<AdmissionProcess>();
			FileUploadDownload fileUploadDownload = new FileUploadDownload();
			if (!result.hasErrors()) {
				String courseClass = request.getParameter("courseClass");// Retrieve
																			// courseClass
																			// from
																			// 'FileUploadForm.jsp'
				String formID = request.getParameter("formId");
				String BasicRootPath = rootPath + folderName;// set basic path
				String path = BasicRootPath + "/"
						+ formSubmission.getAdmissionYear() + "/"
						+ formSubmission.getFormName() + "/" + courseClass
						+ "/" + formID + "/";
				// create directory and sub directory
				File dir = new File(path);
				boolean isDirCreated = dir.mkdirs();
				if (isDirCreated)
					logger.info("folder created");
				else
					logger.info("folder exist");
				for (CommonsMultipartFile file : form.getFile()) {
					AdmissionProcess formSubmissionObject = new AdmissionProcess();
					if (file.getOriginalFilename() != "") {
						/* create a zip file */
						String fileName = file.getOriginalFilename() + "."+"zip";
						/* set actual storage path */
						String filePath = path + fileName;
						logger.debug("'''" + filePath);
						/* split root path */
						String s[] = rootPath.split("/");
						int dotPos1 = filePath.lastIndexOf("/");
						String setRootPath = s[s.length - s.length] + "/";
						/* get sub folder */
						String subFolder = (filePath.substring((s[s.length
								- s.length].length()
								+ 1 + s[s.length - s.length + 1].length() + 1),
								dotPos1));
						try {
							/* get file name with extension */
							String str = file.getOriginalFilename();
							int dotPos = str.lastIndexOf(".");
							/* get file format */
							attachmentFormat = str.substring(dotPos + 1);
							/* get file name */
							attachmentName = str.substring(0, dotPos);
						} catch (Exception e) {
							logger.debug("EXception");
						}
						/*
						 * calling fileUpload() method in AdmissionService.java
						 * and get file size
						 */
						//int fileSize = fileUploadDownload.fileUpload(form, filePath, file);
						/* set attachment file */
						formSubmissionObject.setStorageRootPath(setRootPath);
						formSubmissionObject.setFolderName(subFolder);
						formSubmissionObject.setAttachmentName(attachmentName);
						formSubmissionObject.setAttachmentFormat(attachmentFormat);
						//formSubmissionObject.setAttachmentSize(fileSize); // set
																			// file
																			// size
						formSubmissionObject.setFolderObjectId("folderObject002");
						formSubmissionObject.setAttachmentObjectId("attachObj004");
						formSubmissionObject.setAttachmentCode("attachmentCode004");
						formSubmissionObject.setFolderCode("folderCode002");
						formSubmissionObject.setAttachmentDescription("attachmentDesc001");
						formSubmissionObject.setFolderDescription("folderdesc001");
						formsubmissionList.add(formSubmissionObject);
					}
				}
				formSubmission.setCourseClass(courseClass);
				/*
				 * retrieve date as dd/mm/yyyy and convert into yyyy-mm-dd and
				 * set to FormSubmission object
				 */
				String dateReceivedFromUser = formSubmission.getFormSubmissionDate();
				DateFormat userDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd");
				Date date = userDateFormat.parse(dateReceivedFromUser);
				String convertedDate = dateFormatNeeded.format(date);
				formSubmission.setFormSubmissionDate(convertedDate);
				AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(formSubmission);
				logger.info("calling updateAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmission)");
				admissionService.updateAdmissionForm(formsubmissionList, formSubmission);
				model.addAttribute("StreamClassYearCourse", admissionForm);
				mav = submittedFormListPaging(request, response,model);
			} else {
				model.addAttribute("ResultError", "Result not available...");
				mav = new ModelAndView("common/errorpage");
			}
		} catch (ParseException e) {
			logger.error("Exception in processEditForm() method in AdmissionController:ParseException ",e);
		} catch (Exception e) {
			logger.error("Exception in processEditForm() method in AdmissionController",e);
		}
		return mav;
	}

	/**
	 * This method get file path and download the file Admission Drives.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return ModelAndView
	 * 
	 *         used
	 */

	@RequestMapping(value = "/downloadProspectus", method = RequestMethod.GET)
	public ModelAndView downloadProspectus(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		List<Storage> storageList = null;
		/* retrieve control from admissionYearDetails.jsp */
		String control = request.getParameter("control");
		logger.debug(control);
		try {
			/* retrieve courseClass from admissionYearDetails.jsp */
			String courseName = request.getParameter("courseName");
			String courseClass = request.getParameter("courseClass");
			String folderName = control +"/"+courseName+"/" + courseClass;
			if (control.equals("prospectus")) {
				logger.debug("In download() of DownloadController - checking Class: "
						+ request.getParameter("courseClass"));
				storageList = admissionService.attachmentList(folderName);
				logger.debug("In download() of DownloadController - checking path size  :"
						+ storageList.size());
				String attachmentfilepath = null;
				String fileName = null;
				for (Storage s : storageList) {
					logger.debug("In download() of DownloadController - checking rootpath+storageName  :"
							+ s.getStorageRootPath() + s.getStorageName());
					attachmentfilepath = s.getStorageRootPath()
							+ s.getStorageName();
					for (Folder fol : s.getFolders()) {
						logger.debug("In download() of DownloadController - checking folderName  :"
								+ fol.getFolderName());
						attachmentfilepath = attachmentfilepath + "/"
								+ fol.getFolderName();
						for (Attachment ach : fol.getAttachments()) {
							logger.debug("In download() of DownloadController - checking AttachmentName  :"
									+ ach.getAttachmentName());
							logger.debug("In download() of DownloadController - checking attachmentFormat  :"
									+ ach.getAttachmentFormat());
							fileName = ach.getAttachmentName()
									+ ach.getAttachmentFormat();
							attachmentfilepath = attachmentfilepath + "/"
									+ ach.getAttachmentName() + "."
									+ ach.getAttachmentFormat() + ".zip";
						}
					}
				}
				logger.debug("In download() of AdmissionController - checking final path :"
						+ attachmentfilepath);
				String filePath = attachmentfilepath;
				logger.info("file Path @AdmissionController @download() method:  "
						+ filePath);
				if(filePath != null){
				File filedir = new File(filePath);
				if (!filedir.exists()) {
					String error = "file not found...";
					model.addAttribute("ResultError", error);
					return new ModelAndView("common/errorpage");
				}
				String fileName1 = fileName + ".zip";
				/* file download */
				response.setContentType("application/xls");
				// response.setContentType("APPLICATION/OCTET-STREAM");
				// response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				response.setHeader("Content-Disposition","attachment; filename=\"" + fileName1 + "\"");
				java.io.FileInputStream fileInputStream = new java.io.FileInputStream(
						filePath);
				int i;
				while ((i = fileInputStream.read()) != -1) {
					out.write(i);
				}
				fileInputStream.close();
				out.close();				
				return null;
			}
				else {
				model.addAttribute("ResultError", "Result not Available ");
				return new ModelAndView("common/errorpage");
			}
				
			} else {
				model.addAttribute("ResultError", "Result not Available ");
				return new ModelAndView("common/errorpage");
			}
		} catch (Exception e) {
			e.printStackTrace();
			String error = "file  not found";
			logger.error(
					"Exception in download() method in AdmissionController:  ",
					e);
			return new ModelAndView("common/errorpage", "ResultError", error);
		}
	}

	
	@RequestMapping(value = "/admittedDriveListDetailsSearch")
	public ModelAndView admittedDriveListDetailsSearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			AdmissionProcess searchingParameter) throws ParseException {
		try {
			logger.info("admittedDriveListDetailsSearch() method in AdmissionController: ");
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			String strCourseClass = request.getParameter("courseClass");
			String strStatusToSearch = request.getParameter("statustosearch");
			String strDriveToSearch = request.getParameter("formName");
			searchingParameter.setCourseClass(strCourseClass);
			searchingParameter.setSearchStatus(strStatusToSearch);
			searchingParameter.setFormName(strDriveToSearch);
			searchingParameter.setAdmissionYear(request.getParameter("admissionYear"));
			if (strKey.equalsIgnoreCase("CourseName")) {
				searchingParameter.setCourseName(strValue);
			}
			if (strKey.equalsIgnoreCase("CourseType")) {
				searchingParameter.setCourseType(strValue);
			}
			if (strKey.equalsIgnoreCase("FormID")) {
				searchingParameter.setFormId(strValue);
			}
			if (strKey.equalsIgnoreCase("FirstName")) {
				searchingParameter.setCandidateFirstName(strValue);
			}
			if (strKey.equalsIgnoreCase("MiddleName")) {
				searchingParameter.setCandidateMiddleName(strValue);
			}
			if (strKey.equalsIgnoreCase("LastName")) {
				searchingParameter.setCandidateLastName(strValue);
			}
			if (strKey.equalsIgnoreCase("ContactNumber")) {
				searchingParameter.setCandidateContactNo(strValue);
			}
			if (strKey.equalsIgnoreCase("EmailID")) {
				searchingParameter.setCandidateEmail(strValue);
			}
			if (strKey.equalsIgnoreCase("DateOfAdmission")) {
				searchingParameter.setPaymentDate(strValue);
			}
			if (strKey.equalsIgnoreCase("Status")) {
				searchingParameter.setStatus(strValue);
			}
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(searchingParameter);
			logger.info("calling admittedDriveListDetailsSearch(AdmissionProcess searchingParameter) method in AdmissionServiceImpl");
			admissionService.admittedDriveListDetailsSearch(searchingParameter);
			model.addAttribute("StreamClassYearCourse", admissionForm);
		} catch (Exception e) {
			logger.error("Exception in admittedDriveListDetailsSearch() in AdmissionController: ", e);
		}
		return admittedDriveListDetailsPaging(request, response,model);
	}
	
	@RequestMapping(value = "/admissionPocessFlow", method = RequestMethod.GET)
	public String admissionPocessFlow() {
		return "admission/admissionPocessFlow";
	}
	
	
	@RequestMapping(value = "/downloadAdmissionFormSubmissionExcel", method = RequestMethod.GET)
	public String functionalityRoleMappingExcelDownload(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		try{
			FileUploadDownload fileUploadDownload = new FileUploadDownload();
			String returnMessage = fileUploadDownload.downloadFile(response, rootPath+excelDownloadFolder,newAdmissionFormSubmissionExcel);
			if(returnMessage==null){
				model.addAttribute("ResultError", "File not available");
			}
		}catch(Exception e){
			logger.error("Exception in functionalityRoleMappingExcelDownload() for download Template Excel in AdmissionController", e);
		}
	return("common/errorpage");
	}
	
	/*@RequestMapping(value = "/uploadAdmissionFormSubmissionExcel", method = RequestMethod.POST)
	public ModelAndView functionalityRoleMappingExcelUpload(@ModelAttribute("uploadFile") UploadFile uploadFile,
													  HttpServletRequest request,HttpServletResponse response,
													  ModelMap model,@ModelAttribute("sessionObject") SessionObject sessionObject
													){
		try{
			FileUploadDownload fileUploadDownload = new FileUploadDownload();
			String returnMessage = fileUploadDownload.uploadExcel(uploadFile,rootPath+excelUploadfolder,newAdmissionFormSubmissionExcel);
			if(returnMessage==null){
				model.addAttribute("uploadErrorMessage", newAdmissionFormSubmissionExcel+" upload failed.");
			}else{
				int excelDataInsertStatus =  admissionService.insertAdmissionFormSubmissionExcelBulkData(rootPath+excelUploadfolder+newAdmissionFormSubmissionExcel,sessionObject);
				if(excelDataInsertStatus!=0){
					model.addAttribute("excelDataInsertStatus", excelDataInsertStatus);
				}
				model.addAttribute("message", returnMessage);
			}
		}catch(Exception e){
			logger.error("Exception in functionalityRoleMappingExcelUpload() for upload Template Excel in AdmissionController", e);
		}
	return activeAdmissionDrives(request,response,model);
	}*/
	
	
	@RequestMapping(value = "/getAdmissionDriveLogDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getAdmissionDriveLogDetails() {
		String returnData = "";
		try{
			logger.info("In  getAdmissionDriveLogDetails() method of AdmissionController: ");
			List<LoggingAspect> driveLogList = admissionService.getAdmissionDriveLogDetails();
			if (driveLogList != null && driveLogList.size() != 0) {
				for (LoggingAspect loggingAspect : driveLogList) {
					if (loggingAspect != null) {
						returnData=returnData+loggingAspect.getUpdatedBy()+"*~*"+loggingAspect.getLoggingTime()+"*~*"+loggingAspect.getLoggingDesc()+"~*~";
					}
				}
			}else{
				returnData="Details Not Available";
			} 
		}catch(Exception e){
			logger.error("Exception In AdmissionController getAdmissionDriveLogDetails() method:",e);
		}
		return (new Gson().toJson(returnData));
	}
	
	
	@RequestMapping(value = "/getSaleFormLogDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getSaleFormLogDetails() {
		String returnData = "";
		try{
			logger.info("In  getSaleFormLogDetails() method of AdmissionController: ");
			List<LoggingAspect> driveLogList = admissionService.getSaleFormLogDetails();
			if (driveLogList != null && driveLogList.size() != 0) {
				for (LoggingAspect loggingAspect : driveLogList) {
					if (loggingAspect != null) {
						returnData=returnData+loggingAspect.getUpdatedBy()+"*~*"+loggingAspect.getLoggingTime()+"*~*"+loggingAspect.getLoggingDesc()+"~*~";
					}
				}
			}else{
				returnData="Details Not Available";
			}
		}catch(Exception e){
			logger.error("Exception In AdmissionController getSaleFormLogDetails() method:",e);
		}
		return (new Gson().toJson(returnData));
	}
			
	@RequestMapping(value = "/getAdmissionScheduleInterviewLogDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getAdmissionScheduleInterviewLogDetails(@RequestParam("admissonDriveName") String admissonDriveName) {
		String returnData = "";
		try{
			logger.info("In  getAdmissionScheduleInterviewLogDetails() method of AdmissionController: ");
			List<LoggingAspect> LogList = admissionService.getAdmissionScheduleInterviewLogDetails(admissonDriveName);
			if (LogList != null && LogList.size() != 0) {
				for (LoggingAspect loggingAspect : LogList) {
					if (loggingAspect != null) {
						returnData=returnData+loggingAspect.getUpdatedBy()+"*~*"+loggingAspect.getLoggingTime()+"*~*"+loggingAspect.getLoggingDesc()+"~*~";
					}
				}
			}else{
				returnData="Details Not Available";
			}
		}catch(Exception e){
			logger.error("Exception In AdmissionController getAdmissionScheduleInterviewLogDetails() method:",e);
		}
		return (new Gson().toJson(returnData));
	}
	
	
	@RequestMapping(value = "/getAdmissionInterviewResultLogDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getAdmissionInterviewResultLogDetails(@RequestParam("admissonDriveName") String admissonDriveName) {
		String returnData = "";
		try{
			logger.info("In  getAdmissionInterviewResultLogDetails() method of AdmissionController: ");
			List<LoggingAspect> LogList = admissionService.getAdmissionInterviewResultLogDetails(admissonDriveName);
			if (LogList != null && LogList.size() != 0) {
				for (LoggingAspect loggingAspect : LogList) {
					if (loggingAspect != null) {
						returnData=returnData+loggingAspect.getUpdatedBy()+"*~*"+loggingAspect.getLoggingTime()+"*~*"+loggingAspect.getLoggingDesc()+"~*~";
					}
				}
			}else{
				returnData="Details Not Available";
			}
		}catch(Exception e){
			logger.error("Exception In AdmissionController getAdmissionInterviewResultLogDetails() method:",e);
		}
		return (new Gson().toJson(returnData));
	}
	
	
	@RequestMapping(value = "/getAdmissionPaymentDateSetupLogDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getAdmissionPaymentDateSetupLogDetails(@RequestParam("admissonDriveName") String admissonDriveName) {
		String returnData = "";
		try{
			logger.info("In  getAdmissionPaymentDateSetupLogDetails() method of AdmissionController: ");
			List<LoggingAspect> LogList = admissionService.getAdmissionPaymentDateSetupLogDetails(admissonDriveName);
			if (LogList != null && LogList.size() != 0) {
				for (LoggingAspect loggingAspect : LogList) {
					if (loggingAspect != null) {
						returnData=returnData+loggingAspect.getUpdatedBy()+"*~*"+loggingAspect.getLoggingTime()+"*~*"+loggingAspect.getLoggingDesc()+"~*~";
					}
				}
			}else{
				returnData="Details Not Available";
			}
		}catch(Exception e){
			logger.error("Exception In AdmissionController getAdmissionPaymentDateSetupLogDetails() method:",e);
		}
		return (new Gson().toJson(returnData));
	}

	
	@RequestMapping(value="/completeDrive",method=RequestMethod.POST)
	public ModelAndView completeDrive(HttpServletRequest request, HttpServletResponse response,
										ModelMap model,
										@RequestParam("admissionDriveName") String[] admissionDriveName,
										@RequestParam("admissionDriveName") String[] admissionYear){
		try{
			logger.info("submittedFormListPaging() method in Admissioncontroller: ");
			String driveName = admissionDriveName[0];
			String admissionYearName = admissionYear[0];
			String status = admissionService.updadateAdmissonDriveStatus(driveName);
			List<AdmissionForm> AdmissionsOnProcessList = admissionService.getAdmissionsOnProcessList();
			AdmissionProcess admissionProcess = new AdmissionProcess();
			admissionProcess.setFormName(driveName);
			admissionProcess.setAdmissionYear(admissionYearName);
			String statusOfAdmission = admissionService.getstatusOfAdmissionDrive(admissionProcess);
			List<AdmissionProcess>allSelectedaCandidateList = admissionService.geRequiredCandidates(admissionProcess);
			AdmissionForm admissionForm = admissionService.getStreamClassYearCourseDetails(admissionProcess);
			model.addAttribute("AdmissionsOnProcessList", AdmissionsOnProcessList);
			model.addAttribute("successMessage", status);
			model.addAttribute("statusOfAdmission", statusOfAdmission);
			model.addAttribute("StreamClassYearCourse", admissionForm);
			model.addAttribute("finalSelectedCandidateList", allSelectedaCandidateList);
		}catch(NullPointerException e){
			e.printStackTrace();
			logger.error("Exception in admittedDriveListDetailsPaging() in Admissioncontroller: ", e);
		}
		return new ModelAndView("admission/payment");
		//return admissionsOnProcessList(request, response, model);
	}
	
	
	
	/**REST API
	 * @author anup.roy
	 * this method is for sending program details to portal**/
	
	@RequestMapping(value = "/sendProgrammeDetailsToPortal", method = RequestMethod.GET)
	public String getUnsendProgramListToPortal(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "admission/sendProgrammeDetailsToPortal";
		try {
			logger.info("Inside Method getHostel-GET of HostelController");
			List<Course> programList = admissionService.getUnsendProgramListToPortal();
			model.addAttribute("programList", programList);
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method sendProgrammeDetailsToPortal-GET of AdmissionController", ce);
		}
		return strView;
	}
	
	/*@RequestMapping(value = "/sendProgrammeDetailsToPortal", method = RequestMethod.GET)
	public String sendProgrammeDetailsToPortal(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,SessionObject sessionObject) {
		logger.info("In sendProgrammeDetailsToPortal() of Admissioncontroller.java");
		String str = "admission/sendProgrammeDetailsToPortal";
		try {
			List<Data> programmeDetailsListForPortal = admissionService.setProgrammeDetailsListForPortal();
			System.out.println("programmeDetails list size::"+programmeDetailsListForPortal.size());
			//ProgrammeDetailsForPortal programmeDetails = admissionService.setProgrammeDetailsForPortal();
			JSONObject json = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for(Data programmeDetails : programmeDetailsListForPortal){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("username","adminwebservice@qtsin.net");
				jsonObj.put("password","!Q2w3e4r");
				jsonObj.put("programId", programmeDetails.getProgramId());
				jsonObj.put("programName", programmeDetails.getProgramName());
				jsonObj.put("programType", programmeDetails.getProgramType());
				jsonObj.put("totalSeat", programmeDetails.getTotalSeat());
				jsonObj.put("formIssuanceDate", programmeDetails.getFormIssuanceDate());
				jsonObj.put("formSubmissionLastDate", programmeDetails.getFormSubmissionLastDate());
				jsonObj.put("candidateScrutinyDate", programmeDetails.getCandidateScrutinyDate());
				jsonObj.put("interviewDate", programmeDetails.getInterviewDate());
				String interviewTime12hrFormat= programmeDetails.getInterviewTime();
				SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
			    SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
			    Date date = parseFormat.parse(interviewTime12hrFormat);
			    String interviewTime24hrformat = displayFormat.format(date);
			    programmeDetails.setInterviewTime(interviewTime24hrformat);
				jsonObj.put("interviewTime", programmeDetails.getInterviewTime());
				jsonObj.put("marksSubmissionDate", programmeDetails.getMarksSubmissionDate());
				jsonObj.put("feesPaymentStartDate", programmeDetails.getFeesPaymentStartDate());
				jsonObj.put("feesPaymentEndDate", programmeDetails.getFeesPaymentEndDate());
				List<TermWiseFees> termWiseFeesList = programmeDetails.getTermWiseFeesList();
				JSONArray jsonArrayTermFees = new JSONArray();
				for(TermWiseFees termFees : termWiseFeesList){
					JSONObject jsonTermFees = new JSONObject();
					jsonTermFees.put("termName", termFees.getTermName());
					List<SocialCategory> socialCategoryList = termFees.getSocialCategoryList();
					//JSONArray jsonArraySocialCategory = new JSONArray();
					for(SocialCategory category : socialCategoryList){
						//JSONObject jsonObjSocialCategory = new JSONObject();
						jsonTermFees.put(category.getSocialCategoryName(), category.getAmount());
						//jsonArraySocialCategory.put(jsonObjSocialCategory);
					}
					jsonArrayTermFees.put(jsonTermFees);
				}
				jsonObj.put("semesterAndFees", jsonArrayTermFees);
				jsonArray.put(jsonObj);
			}
			json.put("data", jsonArray);
			System.out.println("JSON DATA PREPARED::"+json.toString());
			final String uri = URIForSendingProgrammeDetails;
			System.out.println("URI:::"+uri);
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestMethod("POST");
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(json.toString());
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
				int programId = (int)object.get("data");
				System.out.println("status::"+statusFromJsonResponse+"program:"+programId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in sendProgrammeDetailsToPortal() of Admissioncontroller.java: ",e);
		}
		return str;
	}*/
	
	@RequestMapping(value = "/sendParticularProgrammeDetailsToPortal", method = RequestMethod.POST)
	public String sendProgrammeDetailsToPortal(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,SessionObject sessionObject,
			@RequestParam("courseName") String courseCode) {
		logger.info("In sendProgrammeDetailsToPortal() of Admissioncontroller.java");
		String str = "admission/sendParticularProgrammeDetailsToPortal";
		try {
			ProgrammeDetailsForPortal programmeDetails = admissionService.setProgrammeDetailsForPortal(courseCode);
			model.addAttribute("programmeDetails",programmeDetails);
			JSONObject jsonObj = new JSONObject();
			
			
			if(isWebIQAvailable.equalsIgnoreCase("true")){
				jsonObj.put("username",PortalUserName);
				jsonObj.put("password",PortalPassWord);
				jsonObj.put("programId", programmeDetails.getProgramId());
				jsonObj.put("programName", programmeDetails.getProgramName());
				jsonObj.put("programType", programmeDetails.getProgramType());
				jsonObj.put("totalSeat", programmeDetails.getTotalSeat());
				jsonObj.put("formIssuanceDate", programmeDetails.getFormIssuanceDate());
				jsonObj.put("formSubmissionLastDate", programmeDetails.getFormSubmissionLastDate());
				jsonObj.put("candidateScrutinyDate", programmeDetails.getCandidateScrutinyDate());
				jsonObj.put("interviewDate", programmeDetails.getInterviewDate());
				jsonObj.put("interviewTime", programmeDetails.getInterviewTime());
				jsonObj.put("marksSubmissionDate", programmeDetails.getMarksSubmissionDate());
				jsonObj.put("feesPaymentStartDate", programmeDetails.getFeesPaymentStartDate());
				jsonObj.put("feesPaymentEndDate", programmeDetails.getFeesPaymentEndDate());
				jsonObj.put("formFees", programmeDetails.getCourseFormFees());
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
				
				WebIQTransaction webIQTran = null;
				
				if((!json_response.isEmpty())){
					JSONObject object = new JSONObject(json_response);
					int statusFromJsonResponse = (int)object.get("status");
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
						String updateStatus = admissionService.updateProgramStatus(courseCode);
						
						//PRAD MAY 31 2018
						//If call to the API is successful, then insert into the webiq_transaction_details table 
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in sendProgrammeDetailsToPortal() of Admissioncontroller.java: ",e);
		}
		return str;
	}
	
	/**
	 * @author anup.roy
	 * this method is for get all course names which are published
	 * 29062017
	 * */
	
	@RequestMapping(value = "/getCourseListForAllCandidates", method = RequestMethod.GET)
	public String getDriveListForStudents(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "admission/getCourseForAllCandidates";
		try {
			logger.info("Inside getDriveListForStudents-GET of AdmissionController");
			List<Course> programList = admissionService.getDriveListForStudents();
			model.addAttribute("programList", programList);
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in getDriveListForStudents-GET of AdmissionController", ce);
		}
		return strView;
	}
	
	/**
	 * @author anup.roy
	 * this method returns drive names w.r.t course name*/
	
	@RequestMapping(value = "/getAllDrivesAgainstCourse", method = RequestMethod.GET)
	public @ResponseBody String getAllDrivesAgainstCourse(@RequestParam("course")String course) {
		String allDrives = null;
		try {
			logger.info("In String getAllDrivesAgainstCourse(String course) of AdmissionController.java");
			allDrives = admissionService.getDrivesAgainstCourse(course);
		}catch (Exception e){
			e.printStackTrace();
		}
		return allDrives;
	}
	
	/**
	 * @author anup.roy
	 * this method gets all candidates w.r.t drive*/
	
	
	@RequestMapping(value = "/getCandidatesAgainstDrive", method = RequestMethod.POST)
	public String getCandidatesAgainstDrive(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,SessionObject sessionObject,
			@RequestParam("drive") String drive) {
		logger.info("In getNewCandidatesFromPortal() of Admissioncontroller.java");
		String str = "admission/getNewCandidatesFromPortal";
		try {
			System.out.println("Drive Name:"+drive);
			final String uri = URIForNewCandidates+"?username="+PortalUserName+"&password="+PortalPassWord+"&driveName="+drive;
		    RestTemplate restTemplate = new RestTemplate();
		    
		    if(isWebIQAvailable.equalsIgnoreCase("true")){
			    String data = restTemplate.getForObject(uri, String.class);
			    ObjectMapper mapper = new ObjectMapper();
			    AdmissionFormForPortalStudents candidate = mapper.readValue(data, AdmissionFormForPortalStudents.class);
			    
			    WebIQTransaction webIQTran = null;
			    
			    if(candidate.getStatus()==200){
			    	List<Data> dataList = candidate.getData();
			    	String user = sessionObject.getUserId();
					String insertStatus = admissionService.saveCandidateDetailsFromPortal(candidate,user);
					model.addAttribute("dataList", dataList);
					
					//If call to the API is successful, then insert into the webiq_transaction_details table 
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(uri);
					webIQTran.setRequestJSON(null);
					webIQTran.setResponseJSON(data);
					webIQTran.setStatus(true);
			    }else{
			    	String errormsg = "No candidate found";
			    	model.addAttribute("errormsg", errormsg);
			    	//If Failure then also insert into the webiq_transaction_details table
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(uri);
					webIQTran.setRequestJSON(null);
					webIQTran.setResponseJSON(data);
					webIQTran.setStatus(false);
			    }

			    //Call to the BackOffice Service
			    backOfficeService.addWebIQTransaction(webIQTran);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getNewCandidatesFromPortal() of Admissioncontroller.java: ",e);
		}
		return str;
	}
	
	/**
	 * @author anup.roy
	 * this method gets all program names for srutinized candidates*/
	
	@RequestMapping(value = "/getCourseListForScrutinizedCandidates", method = RequestMethod.GET)
	public String getCourseListForScrutinizedCandidates(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "admission/getCourseForScrutinizedCandidates";
		try {
			logger.info("Inside getCourseListForScrutinizedCandidates-GET of AdmissionController");
			List<Course> programList = admissionService.getDriveListForStudents();
			model.addAttribute("programList", programList);
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in getCourseListForScrutinizedCandidates-GET of AdmissionController", ce);
		}
		return strView;
	}
	
	
	/**
	 * @author anup.roy
	 * this method is for get scrutinized students w.r.t a drive
	 * */
	
	@RequestMapping(value = "/getScrutinizedCandidatesFromPortal", method = RequestMethod.POST)
	public String getScrutinizedCandidatesFromPortal(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, SessionObject sessionObject,
			@RequestParam("drive") String drive) {
		logger.info("In getScrutinizedCandidatesFromPortal() of Admissioncontroller.java");
		String str = "admission/getScrutinizedCandidatesFromPortal";
		try {
			final String uri = URIForScrutinizedCandidates+"?username="+PortalUserName+"&password="+PortalPassWord+"&driveName="+drive;
		    RestTemplate restTemplate = new RestTemplate();
		    
		    if(isWebIQAvailable.equalsIgnoreCase("true")){
			    String data = restTemplate.getForObject(uri, String.class);
			    ObjectMapper mapper = new ObjectMapper();
			    AdmissionFormForPortalStudents candidate = mapper.readValue(data, AdmissionFormForPortalStudents.class);
			    //AdmissionFormForPortalStudents candidate = mapper.readValue(new File("D:\\candidate.json"), AdmissionFormForPortalStudents.class);
			    
			    WebIQTransaction webIQTran = null;
			    
			    if(candidate.getStatus()==200){
			    	List<Data> dataList = candidate.getData();
			    	String user = sessionObject.getUserId();
					String insertStatus = admissionService.saveScrutinizedCandidatesFromPortal(candidate, user);
					model.addAttribute("dataList", dataList);
					
					//If call to the API is successful, then insert into the webiq_transaction_details table 
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(uri);
					webIQTran.setRequestJSON(null);
					webIQTran.setResponseJSON(data);
					webIQTran.setStatus(true);
			    }else{
			    	String errormsg = "No candidate found";
			    	model.addAttribute("errormsg", errormsg);
			    	
			    	//If Failure then also insert into the webiq_transaction_details table
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(uri);
					webIQTran.setRequestJSON(null);
					webIQTran.setResponseJSON(data);
					webIQTran.setStatus(false);
			    }

			    //Call to the BackOffice Service
			    backOfficeService.addWebIQTransaction(webIQTran);

		    }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getNewCandidatesFromPortal() of Admissioncontroller.java: ",e);
		}
		return str;
	}
	
	/**
	 * @author anup.roy
	 * this method gets all program names for selected candidates*/
	
	@RequestMapping(value = "/getCourseListForSelectedCandidates.html", method = RequestMethod.GET)
	public String getCourseListForSelectedCandidates(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "admission/getCourseListForSelectedCandidates";
		try {
			logger.info("Inside getCourseListForSelectedCandidates-GET of AdmissionController");
			List<Course> programList = admissionService.getDriveListForStudents();
			model.addAttribute("programList", programList);
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in getCourseListForSelectedCandidates-GET of AdmissionController", ce);
		}
		return strView;
	}
	
	/**
	 * REST
	 * @author anup.roy
	 * this method is for get selected students w.r.t drive
	 * */
	
	
	@RequestMapping(value = "/getSelectedCandidatesFromPortal", method = RequestMethod.POST)
	public String getSelectedCandidatesFromPortal(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, SessionObject sessionObject,
			@RequestParam ("drive") String drive) {
		logger.info("In getScrutinizedCandidatesFromPortal() of Admissioncontroller.java");
		String str = "admission/getSelectedCandidatesFromPortal";
		try {
			final String uri = URIForSelectedCandidates+"?username="+PortalUserName+"&password="+PortalPassWord+"&driveName="+drive;
		    RestTemplate restTemplate = new RestTemplate();
		    
		    if(isWebIQAvailable.equalsIgnoreCase("true")){
			    String data = restTemplate.getForObject(uri, String.class);
			    ObjectMapper mapper = new ObjectMapper();
			    AdmissionFormForPortalStudents candidate = mapper.readValue(data, AdmissionFormForPortalStudents.class);
			    //AdmissionFormForPortalStudents candidate = mapper.readValue(new File("D:\\candidate.json"), AdmissionFormForPortalStudents.class);
			    
			    WebIQTransaction webIQTran = null;
			    
			    if(candidate.getStatus()==200){
			    	String user = sessionObject.getUserId();
					String insertStatus = admissionService.saveSelectedCandidatesFromPortal(candidate,user);
					List<Data> dataList = candidate.getData();
			    	model.addAttribute("dataList", dataList);
			    	
			    	//If call to the API is successful, then insert into the webiq_transaction_details table 
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(uri);
					webIQTran.setRequestJSON(null);
					webIQTran.setResponseJSON(data);
					webIQTran.setStatus(true);
					
				}else{
			    	String errormsg = "No candidate found";
			    	model.addAttribute("errormsg", errormsg);
			    	
			    	//If Failure then also insert into the webiq_transaction_details table
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(uri);
					webIQTran.setRequestJSON(null);
					webIQTran.setResponseJSON(data);
					webIQTran.setStatus(false);
			    }
			    
			    //Call to the BackOffice Service
			    backOfficeService.addWebIQTransaction(webIQTran);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getNewCandidatesFromPortal() of Admissioncontroller.java: ",e);
		}
		return str;
	}
	
	/**
	 * @author anup.roy
	 * this method is for sending location details **/
	
	@RequestMapping(value = "/sendLocationDetailsToPortal", method = RequestMethod.GET)
	public String getUnsendProgramsWithLocations(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "admission/unsendProgramsWithLocations";
		try {
			List<Course> programList = null;
			logger.info("Inside Method getUnsendProgramsWithLocations-GET of AdmissionController");
			programList = admissionService.getUnsendProgramsWithLocations();
			model.addAttribute("programList", programList);
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method sendProgrammeDetailsToPortal-GET of AdmissionController", ce);
		}
		return strView;
	}
	
	/**
	 * @author anup.roy
	 * this method gets all drives with unsent locations w.r.t course
	 * 03072017
	 * */
	@RequestMapping(value = "/getDriveWithUnsentLocationAgainstCourse", method = RequestMethod.GET)
	public @ResponseBody String getDriveWithUnsentLocationAgainstCourse(@RequestParam("course")String course) {
		String drive = null;
		try {
			System.out.println("course:"+course);
			logger.info("In String getDriveWithUnsentLocationAgainstCourse(String course) of AdmissionController.java");
			drive = admissionService.getDriveWithUnsentLocationAgainstCourse(course);
		}catch (Exception e){
			e.printStackTrace();
		}
		return drive;
	}
	
	/**
	 * @author anup.roy
	 * this method is for locations w.r.t drive*/
	
	@RequestMapping(value = "/sendLocationDetailsForProgram", method = RequestMethod.POST)
	public String sendLocationDetailsToPortal(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,SessionObject sessionObject,
			@RequestParam("drive") String drive,
			@RequestParam("courseName") String course) {
		logger.info("In sendLocationDetailsToPortal() of Admissioncontroller.java");
		/*String str = "admission/sendLocationDetailsToPortal";*/
		String str = "admission/unsendProgramsWithLocations";
		try {
			List<LocationDetailsForPortal> locationDetailsList = admissionService.setInterviewLocationDetailsToPortal(course);
			
			if(isWebIQAvailable.equalsIgnoreCase("true")){
				JSONObject jsonObjMain = new JSONObject();
				jsonObjMain.put("password", PortalPassWord);
				jsonObjMain.put("username", PortalUserName);
				jsonObjMain.put("driveName", drive);
				JSONArray dateAndLocationArray = new JSONArray();
				for(LocationDetailsForPortal locationDetails : locationDetailsList){
					JSONObject dateAndLocationJson = new JSONObject();
					dateAndLocationJson.put("interviewDate", locationDetails.getInterviewDate());
					dateAndLocationJson.put("interviewCity", locationDetails.getCity());
					dateAndLocationJson.put("interviewState", locationDetails.getStateName());
					dateAndLocationJson.put("capacity", 10);
					dateAndLocationJson.put("interviewAddress", locationDetails.getVenueName()+", "+locationDetails.getArea()+", "+locationDetails.getCity()+", "+locationDetails.getStateName()+", "+locationDetails.getPin());
					JSONArray interviewPanelArray = new JSONArray();
					List<NameId> nameIdList = locationDetails.getNameIdList();
					for(NameId nameId : nameIdList){
						JSONObject interviewPanelJson = new JSONObject();
						interviewPanelJson.put("firstName", nameId.getFirstName());
						interviewPanelJson.put("middleName", nameId.getMiddleName());
						interviewPanelJson.put("lastName", nameId.getLastName());
						interviewPanelJson.put("email", nameId.getEmail());
						interviewPanelArray.put(interviewPanelJson);
					}
					dateAndLocationJson.put("interviewPanel", interviewPanelArray);
					dateAndLocationArray.put(dateAndLocationJson);
				}
				jsonObjMain.put("locations", dateAndLocationArray);
				System.out.println("Location String:"+jsonObjMain.toString());
				final String uri = URIForSendLocationDetails;
				System.out.println("URI:::"+uri);
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.setRequestMethod("POST");
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObjMain.toString());
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
					System.out.println("JSON Status:"+statusFromJsonResponse);
					WebIQTransaction webIQTran = null;
					
					if(statusFromJsonResponse == 200){
						String updateDriveStatus = admissionService.updateLocationSendStatusForDrive(drive);
						model.addAttribute("locationDetailsList", locationDetailsList);
						
						//If call to the API is successful, then insert into the webiq_transaction_details table 
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForSendLocationDetails);
						webIQTran.setRequestJSON(jsonObjMain.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(true);
					}else{
						//If Failure then also insert into the webiq_transaction_details table
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForSendLocationDetails);
						webIQTran.setRequestJSON(jsonObjMain.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(false);
					}
					
					//Call to the BackOffice Service
					backOfficeService.addWebIQTransaction(webIQTran);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in sendProgrammeDetailsToPortal() of Admissioncontroller.java: ",e);
		}
		return str;
	}
	
	/**
	 * @author anup.roy
	 * this method gets all program names for admitted candidates*/
	
	@RequestMapping(value = "/getCourseListForAdmittedCandidates.html", method = RequestMethod.GET)
	public String getCourseListForAdmittedCandidates(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "admission/getCourseListForAdmittedCandidates";
		try {
			logger.info("Inside getCourseListForAdmittedCandidates-GET of AdmissionController");
			List<Course> programList = admissionService.getDriveListForStudents();
			model.addAttribute("programList", programList);
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in getCourseListForAdmittedCandidates-GET of AdmissionController", ce);
		}
		return strView;
	}
	
	/**
	 * @author anup.roy
	 * method for receive the admitted candidates w.r.t drive**/
	
	@RequestMapping(value = "/getAdmittedCandidatesFromPortal", method = RequestMethod.POST)
	public String getAdmittedCandidatesFromPortal(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, SessionObject sessionObject,
			@RequestParam("drive") String drive) {
		logger.info("In getAdmittedCandidatesFromPortal() of Admissioncontroller.java");
		String str = "admission/getAdmittedCandidatesFromPortal";
		try {
			final String uri = URIForAdmittedCandidates+"?username="+PortalUserName+"&password="+PortalPassWord+"&driveName="+drive;
		    RestTemplate restTemplate = new RestTemplate();
		    
		    if(isWebIQAvailable.equalsIgnoreCase("true")){
			    String data = restTemplate.getForObject(uri, String.class);
			    ObjectMapper mapper = new ObjectMapper();
			    AdmissionFormForPortalStudents candidate = mapper.readValue(data, AdmissionFormForPortalStudents.class);
			    
			    WebIQTransaction webIQTran= null;
			    
			    if(candidate.getStatus()==200){
			    	String user = sessionObject.getUserId();
			    	String insertStatus = admissionService.saveAdmittedCandidatesFromPortal(candidate, user);
			    	List<Data> dataList = candidate.getData();
			    	model.addAttribute("dataList", dataList);
			    	
			    	//If call to the API is successful, then insert into the webiq_transaction_details table 
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(uri);
					webIQTran.setRequestJSON(null);
					webIQTran.setResponseJSON(data);
					webIQTran.setStatus(true);
					
				}else{
			    	String errormsg = "No candidate found";
			    	model.addAttribute("errormsg", errormsg);
			    	
			    	//If Failure then also insert into the webiq_transaction_details table
					webIQTran = new WebIQTransaction();
					webIQTran.setUpdatedBy(sessionObject.getUserId());
					webIQTran.setUri(uri);
					webIQTran.setRequestJSON(null);
					webIQTran.setResponseJSON(data);
					webIQTran.setStatus(false);
			    }
		    }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getAdmittedCandidatesFromPortal() of Admissioncontroller.java: ",e);
		}
		return str;
	}
	
	/**
	 * @author anup.roy
	 * this method is for sending Alumni data to portal**/
	
	@RequestMapping(value = "/sendAlumniDataToPortal", method = RequestMethod.GET)
	public String sendAlumniDataToPortal(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,SessionObject sessionObject) {
		logger.info("In sendAlumniDataToPortal() of Admissioncontroller.java");
		String str = "admission/sendAlumniDataToPortal";
		try {
			List<Data> alumniDataList = admissionService.setAlumniDataToPortal();
			System.out.println("programmeDetails list size::"+alumniDataList.size());
			model.addAttribute("alumniDataList",alumniDataList);
			JSONObject json = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			
			if(isWebIQAvailable.equalsIgnoreCase("true")){
				for(Data alumniData : alumniDataList){
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("username",PortalUserName);
					jsonObj.put("password",PortalPassWord);
					jsonObj.put("name", alumniData.getName());
					jsonObj.put("email", alumniData.getEmail());
					jsonObj.put("userId", alumniData.getUserId());
					jsonArray.put(jsonObj);
				}
				json.put("data", jsonArray);
				System.out.println("JSON DATA PREPARED::"+json.toString());
				final String uri = URIForSendingAlumniDetails;
				System.out.println("URI:::"+uri);
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.setRequestMethod("POST");
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(json.toString());
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
					int programId = (int)object.get("data");
					
					WebIQTransaction webIQTran= null;
					
					if(statusFromJsonResponse==200){
						//If call to the API is successful, then insert into the webiq_transaction_details table 
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForSendingAlumniDetails);
						webIQTran.setRequestJSON(json.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(true);
					}else{
						//If Failure then also insert into the webiq_transaction_details table
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForSendingAlumniDetails);
						webIQTran.setRequestJSON(json.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(false);
					}
					
					//Call to the BackOffice Service
					backOfficeService.addWebIQTransaction(webIQTran);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in sendAlumniDataToPortal() of Admissioncontroller.java: ",e);
		}
		return str;
	}
	
	/**
	 * @author anup.roy
	 * this method is for publish notice to portal**/
	
	@RequestMapping(value = "/publishNoticeToPortal", method = RequestMethod.GET)
	public String publishNoticeToPortal(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,SessionObject sessionObject) {
		logger.info("In publishNoticeToPortal() of Admissioncontroller.java");
		String str = "admission/publishNoticeToPortal";
		try {
			List<NoticeBoard> noticeList = admissionService.getNoticeList();
			System.out.println("notice list size::"+noticeList.size());
			model.addAttribute("noticeList",noticeList);
			JSONObject json = new JSONObject();
			/*JSONArray jsonArray = new JSONArray();
			for(NoticeBoard notice : noticeList){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("username","adminwebservice@qtsin.net");
				jsonObj.put("password","!Q2w3e4r");
				jsonObj.put("title", notice.getNoticeName());
				jsonObj.put("description", notice.getNoticeDesc());
				jsonObj.put("displayStartDate", notice.getNoticeId());
				jsonObj.put("displayEndDate", notice.getNoticeId());
				jsonArray.put(jsonObj);
			}
			json.put("data", jsonArray);*/
			if(isWebIQAvailable.equalsIgnoreCase("true")){
				for(NoticeBoard notice : noticeList){
					json.put("username",PortalUserName);
					json.put("password",PortalPassWord);
					json.put("title", notice.getNoticeName());
					json.put("description", notice.getNoticeDesc());
					json.put("displayStartDate", notice.getNoticeId());
					json.put("displayEndDate", notice.getNoticeId());
				}
				System.out.println("JSON DATA PREPARED::"+json.toString());
				final String uri = URIForPublishNotice;
				System.out.println("URI:::"+uri);
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.setRequestMethod("POST");
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(json.toString());
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
						webIQTran.setUri(URIForPublishNotice);
						webIQTran.setRequestJSON(json.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(true);
					}else{
						//If Failure then also insert into the webiq_transaction_details table
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForPublishNotice);
						webIQTran.setRequestJSON(json.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(false);
					}
					
					//Call to the BackOffice Service
					backOfficeService.addWebIQTransaction(webIQTran);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in publishNoticeToPortal() of Admissioncontroller.java: ",e);
		}
		return str;
	}
}
