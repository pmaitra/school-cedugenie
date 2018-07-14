package com.qts.icam.service.impl.admission;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import com.qts.icam.dao.admission.AdmissionDAO;
import com.qts.icam.dao.bulkdata.DataDAO;
import com.qts.icam.dao.impl.admission.AdmissionDAOImpl;
import com.qts.icam.model.admission.AdmissionDrive;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.admission.AdmissionProcess;
import com.qts.icam.model.admission.LocationDetailsForPortal;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.AdmissionFormForPortalStudents;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Data;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.ProgrammeDetailsForPortal;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Storage;
import com.qts.icam.service.admission.AdmissionService;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.bulkdata.DataUtility;
import com.qts.icam.utility.mailservice.EmailSender;

/**
 * 
 * @author sovan.mukherjee
 * @version 1.0
 * 
 */
@Service
public class AdmissionServiceImpl implements AdmissionService {
	public static Logger logger = Logger.getLogger(AdmissionDAOImpl.class);
	@Autowired
	AdmissionDAO admissionDriveDAO = null;
	
	@Autowired
	DataUtility dataUtility;
	
	@Autowired
	DataDAO dataDAO;
	
	@Autowired
	EmailSender emailSender;
	
	int pageSize = 5;
	List<AdmissionForm> admissionDriveList = null;
	List<AdmissionProcess>historyAndPreviousAdmissionListFromDB = null;
	List<AdmissionForm> activeAdmissionDrives = null;
	List<AdmissionForm> admissionFormList = null;
	List<AdmissionForm> admissionsOnProcessList = null;
	List<AdmissionProcess> admissionProcess = null;
	List<AdmissionProcess> admittedDriveListDetails = null;
	AdmissionForm streamClassYearForPaging = null;

	@Override
	public AdmissionForm getStreamClassYearForPaging() {
		return streamClassYearForPaging;
	}

	/**
	 * This getAdmissionDriveList() is calling getAdmisionDriveList() of
	 * AdmisionDriveDAO for displaying Admission Drive List
	 * 
	 * @param String
	 * @return List<AdmissionDrive>
	 * 
	 */
	@Override
	public List<AdmissionForm> getAdmissionDriveList(String strDrive) {
		logger.info("getAdmissionDriveList(String strDrive) method In AdmissionServiceImpl: ");
		try {
			Utility util = new Utility();
			logger.info("calling getAdmissionDriveList(String strDrive) method In AdmissionDAOImpl: ");
			admissionDriveList = admissionDriveDAO.getAdmissionDriveList(strDrive);
			if (admissionDriveList != null && admissionDriveList.size() != 0) {
				for (AdmissionForm admissionDrive : admissionDriveList) {
					if (null != admissionDrive.getAdmissionDriveStartDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionDriveStartDate());
						admissionDrive.setAdmissionDriveStartDate(readableDate);
					}
					if (null != admissionDrive.getAdmissionDriveActualEndDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionDriveActualEndDate());
						admissionDrive.setAdmissionDriveActualEndDate(readableDate);
					} else {
						admissionDrive.setAdmissionDriveActualEndDate("N/A ");
					}
					if (null != admissionDrive.getAdmissionDriveExpectedEndDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionDriveExpectedEndDate());
						admissionDrive.setAdmissionDriveExpectedEndDate(readableDate);
					}
					if (null != admissionDrive.getAdmissionFormSubmissionLastDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionFormSubmissionLastDate());
						admissionDrive.setAdmissionFormSubmissionLastDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			logger.error("EXception IN getAdmissionDriveList(String strDrive) method In AdmissionServiceImpl: ",e);
		}
		return admissionDriveList;
	}
	
	@Override
	public PagedListHolder<AdmissionForm> getAdmissionDriveListPaging(HttpServletRequest request) {
		PagedListHolder<AdmissionForm> pagedListHolder = null;
		if(admissionDriveList!=null && admissionDriveList.size()!=0){
			pagedListHolder = new PagedListHolder<AdmissionForm>(admissionDriveList);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setPageSize(pageSize);
		}
		return pagedListHolder;
	}
	

	/**
	 * This attachmentList() is calling getAttachmentList() of AdmissionDriveDAO
	 * for display all the attachment in the specified folderName
	 * 
	 * @param String
	 * @return List<Storage>
	 * 
	 */
	@Override
	public List<Storage> attachmentList(String folderName) {
		List<Storage> storageList = null;
		try {
			storageList = admissionDriveDAO.getAttachmentList(folderName);
		} catch (Exception e) {
			logger.error("attachmentList()AdmissionServiceImpl: Exception :",e);
		}
		return storageList;
	}
	
	@Override
	public List<AdmissionForm> getAdmissionFormDetails() {
		logger.info("In getAdmissionFormDetails() method in AdmissionServiceImpl.java");
		admissionFormList = admissionDriveDAO.getAdmissionFormList();
		return admissionFormList;
	}
	
	@Override
	public List<AdmissionForm> getAdmissionFormDetailsSearch(Map<String, Object> parameters) {
		logger.info("getAdmissionFormDetailsSearch() method in AdmissionServiceImpl.");
		admissionFormList = admissionDriveDAO.getAdmissionFormDetailsSearch(parameters);
		return admissionFormList;
	}
	

	/**
	 * This insertAdmissionForm() is calling insertAdmissionForm() of
	 * AdmissionDriveDAO for inserting data from "Submission Form" form that
	 * accepts Candidate's general information and attachment(s)
	 * 
	 * @param List
	 *            <FormSubmission>
	 * @param FormSubmission
	 * @return List<AdmissionProcess>
	 * 
	 */
	@Override
	public List<AdmissionProcess> insertAdmissionForm(List<AdmissionProcess> formsubmissionList,
														AdmissionProcess formSubmissionOtherInformation) {
		Utility util = new Utility();
		try {
			logger.info("insertAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmissionOtherInformation) method in AdmissionServiceImpl");
			admissionProcess = admissionDriveDAO.insertAdmissionForm(formsubmissionList, formSubmissionOtherInformation);
			if(admissionProcess != null && admissionProcess.size()!=0){
				for (AdmissionProcess submittedForm : admissionProcess) {
					if (submittedForm.getIntFormSubmissionDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(submittedForm.getIntFormSubmissionDate()).toString());
						submittedForm.setFormSubmissionDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in insertAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmissionOtherInformation) ",e);
		}
		return admissionProcess;
	}
	
	@Override
	public PagedListHolder<AdmissionProcess> getSubmittedFormListPaging(HttpServletRequest request) {
		PagedListHolder<AdmissionProcess> pagedListHolder = new PagedListHolder<AdmissionProcess>(admissionProcess);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	/**
	 * This insertInterviewSchedule is calling insertInterviewSchedule() of
	 * AdmissionDriveDAO for inserting data form "Interview Schedule" form that
	 * accepts interview Details and return InterviewSchedule List.
	 * 
	 * @param interviewscheduleList
	 * @return List<AdmissionProcess>
	 * 
	 */
	@Override
	public List<AdmissionProcess> insertInterviewSchedule(AdmissionProcess interviewschedule) {
		logger.info("insertInterviewSchedule(AdmissionProcess interviewschedule) method in AdmissionServiceImpl");
		admissionProcess = admissionDriveDAO.insertInterviewSchedule(interviewschedule);
		return admissionProcess;
	}
	
	@Override
	public PagedListHolder<AdmissionProcess> getSubmittedAdmisionInterviewScheduleListPaging(HttpServletRequest request) {
		PagedListHolder<AdmissionProcess> pagedListHolder = new PagedListHolder<AdmissionProcess>(admissionProcess);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	/**
	 * this method return Interview Schedule List.
	 * 
	 * @param courseClass
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> getInterviewScheduleList(AdmissionProcess ap) {
		logger.info("getInterviewScheduleList(AdmissionProcess admissionProcess) method in AdmissionServiceImpl: ");
		admissionProcess = 	admissionDriveDAO.getInterviewScheduleList(ap);
		return admissionProcess;
	}

	/**
	 * This getFormIdList is calling getFormIdList() of AdmissionDriveDAO for
	 * fetching all FormID, for displaying in the "InterviewResult Details" form
	 * 
	 * @param String
	 * @return List<FormSubmission>
	 * 
	 */
	@Override
	public List<AdmissionProcess> getGeneratedFormIdList(AdmissionProcess formSubmission) {
		logger.info("getGeneratedFormIdList(AdmissionProcess formSubmission) method in AdmissionServiceImpl");
		return admissionDriveDAO.getGeneratedFormIdList(formSubmission);
	}

	/**
	 * this method return Interviewed Candidate List
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> getInterviewedCandidateList(AdmissionProcess interviewResult) {
		logger.info("getInterviewedCandidateList(AdmissionProcess interviewResult) in AdmissionServiceImpl");
		admissionProcess = admissionDriveDAO.getInterviewedCandidateList(interviewResult);
		return admissionProcess;
	}

	/**
	 * This insertResultDetailsService() is calling insertResultDetailsDAO() of
	 * AdmissionDriveDAO for inserting interview details and marks obtained in
	 * every subject
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 * 
	 */

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public List<AdmissionProcess> insertResultDetails(AdmissionProcess interviewResultToDB) {
		logger.info("insertResultDetails(AdmissionProcess interviewResultToDB) method in AdmissionServiceImpl");
		admissionProcess = admissionDriveDAO.insertResultDetails(interviewResultToDB);
		return admissionProcess;
	}


	@Override
	public PagedListHolder<AdmissionProcess> getadmissionInterviewResultListPaging(HttpServletRequest request) {
		PagedListHolder<AdmissionProcess> pagedListHolder = new PagedListHolder<AdmissionProcess>(admissionProcess);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	/**
	 * This getLastFormIDService() is calling getLastFormIdDAO() of
	 * AdmissionDriveDAO to get last formId for specified form name.
	 * 
	 * @param AdmissionForm
	 * @return String
	 * 
	 */
	@Override
	public String getLastFormID(AdmissionForm admissionForm) {
		String strLastFormId = null;
		try {
			logger.info("getLastFormID(AdmissionForm admissionForm) method in AdmissionServiceImpl: ");
			int checkOpeningAdmissionDriveStatus = admissionDriveDAO.checkOpeningAdmissionDriveStatus(admissionForm);
			if (checkOpeningAdmissionDriveStatus == 0) {
				AdmissionForm admissionFormObj = admissionDriveDAO.getLastFormId(admissionForm);
				if (admissionFormObj == null) {
					strLastFormId = admissionForm.getCourseClass() + "-" + "000";
				} else {
					strLastFormId = admissionFormObj.getAdmissionDriveCode();
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getLastFormID(AdmissionForm admissionForm) method in AdmissionServiceImpl: ",e);
		}
		return strLastFormId;
	}

	/**
	 * This insertAdmissionDrive() is calling insertAdmissionFormDAO() of
	 * AdmissionDriveDAO to insert new Admission Form.
	 * 
	 * @param AdmissionForm
	 * @return void
	 * 
	 */
	@Override
	public List<AdmissionForm> insertAdmissionDrive(AdmissionForm admissionForm) {
		try {
			logger.info("insertAdmissionDrive(AdmissionForm admissionForm) method in AdmissionServiceImpl:");
			Utility util = new Utility();
			activeAdmissionDrives = admissionDriveDAO.insertAdmissionDrive(admissionForm);
			if (activeAdmissionDrives != null && activeAdmissionDrives.size() != 0) {
				for (AdmissionForm admissionDrive : activeAdmissionDrives) {
					if (null != admissionDrive.getAdmissionDriveStartDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionDriveStartDate());
						admissionDrive.setAdmissionDriveStartDate(readableDate);
					}
					if (null != admissionDrive.getAdmissionDriveExpectedEndDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionDriveExpectedEndDate());
						admissionDrive.setAdmissionDriveExpectedEndDate(readableDate);
					}
					if (null != admissionDrive.getAdmissionFormSubmissionLastDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionFormSubmissionLastDate());
						admissionDrive.setAdmissionFormSubmissionLastDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in insertAdmissionDrive(AdmissionForm admissionForm) method in AdmissionServiceImpl",e);
		}
		return activeAdmissionDrives;
	}

	/**
	 * This insertFormIdService() is calling insertAdmissionFormDAO() of
	 * AdmissionDriveDAO to insert Form Id.
	 * 
	 * @param ArrayList
	 *            <AdmissionForm>
	 * @return void
	 * 
	 */
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String insertFormId(ArrayList<AdmissionForm> admissionFormList) {
		logger.info("insertFormId(ArrayList<AdmissionForm> admissionFormList) method in AdmissionServiceImpl");
		return admissionDriveDAO.insertAdmissionForm(admissionFormList);
	}

	/**
	 * this method return list of classes.
	 * 
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> getCourseClasses() {
		logger.info("List<AdmissionProcess> getCourseClasses() method in AdmissionServiceImpl");
		return admissionDriveDAO.getCourseClasses();
	}

	/**
	 * this method return admission form details for edit
	 * 
	 * @param String
	 * @return AdmissionProcess
	 */
	@Override
	public AdmissionProcess getFormDetails(AdmissionProcess submitAdmissionFormForEdit) {
		AdmissionProcess formSubmission = null;
		try {
			logger.info(" getFormDetails(AdmissionProcess submitAdmissionFormForEdit) method in AdmissionServiceImpl.java:");
			formSubmission = admissionDriveDAO.getFormDetails(submitAdmissionFormForEdit);
			if (formSubmission != null) {
				String readableDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date(Integer.parseInt(formSubmission.getFormSubmissionDate()) * 1000L));
				formSubmission.setFormSubmissionDate(readableDate);
			}
		} catch (Exception e) {
			logger.info("viewPreviousAdmissionFormDetails() method in AdmissionController.java:");
		}
		return formSubmission;
	}

	/**
	 * this method update Admission Form details on particular candidate
	 * 
	 * @param List
	 *            <AdmissionProcess>
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> updateAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmission) {
		try {
			logger.info("updateAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmission) method in AdmissionServiceImpl");
			admissionProcess = admissionDriveDAO.updateAdmissionForm(formsubmissionList,formSubmission);
			Utility util = new Utility();
			if (admissionProcess != null && admissionProcess.size() != 0) {
				for (AdmissionProcess submittedForm : admissionProcess) {
					if (submittedForm.getIntFormSubmissionDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(submittedForm.getIntFormSubmissionDate()).toString());
						submittedForm.setFormSubmissionDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			logger.error("exception in updateAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmission) method in AdmissionServiceImpl",e);
		}
		return admissionProcess;
	}

	/**
	 * this method return Interview Schedule
	 * 
	 * @param String
	 * @return AdmissionProcess
	 */
	@Override
	public AdmissionProcess getInterviewSchedule(AdmissionProcess admissionProcess) {
		logger.info("getInterviewSchedule(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
		return admissionDriveDAO.getInterviewSchedule(admissionProcess);
	}

	/**
	 * this method return Interview Schedule For Edit on particular candidate
	 * 
	 * @param String
	 * @return AdmissionProcess
	 */
	@Override
	public AdmissionProcess getInterviewScheduleForEdit(AdmissionProcess admissionProcess) {
		logger.info("getInterviewScheduleForEdit(AdmissionProcess admissionProcess) method In AdmissionService ");
		return admissionDriveDAO.getInterviewScheduleForEdit(admissionProcess);
	}

	/**
	 * this method update Interview Schedule on particular candidate
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> updateInterviewSchedule(AdmissionProcess interviewscheduleToUpadate) {
		logger.info("updateInterviewSchedule(AdmissionProcess interviewscheduleToUpadate) method in AdmissionServiceImpl");
		admissionProcess = admissionDriveDAO.updateInterviewSchedule(interviewscheduleToUpadate);
		return admissionProcess;
	}

	/**
	 * this method return interview result details for particular candidate for
	 * edit.
	 * 
	 * @param String
	 * @return AdmissionProcess
	 */
	@Override
	public AdmissionProcess getInterviewResultForEdit(AdmissionProcess admissionProcess) {
		logger.info("getInterviewResultForEdit(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
		return admissionDriveDAO.getInterviewResultForEdit(admissionProcess);

	}

	/**
	 * this method update result details.
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public List<AdmissionProcess> updateResultDetails(AdmissionProcess interviewResultToDB) {
		logger.info("updateResultDetails(AdmissionProcess interviewResultToDB) method in AdmissionServiceImpl");
		admissionProcess = admissionDriveDAO.updateResultDetails(interviewResultToDB);
		return admissionProcess;
	}

	
	/**
	 * this method return interview result for view merit list.
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> getInterviewResultForViewMeritList(AdmissionProcess interviewResult) {
		logger.info("getInterviewResultForViewMeritList(AdmissionProcess interviewResult) method in AdmissionServiceImpl");
		admissionProcess = admissionDriveDAO.getInterviewResultForViewMeritList(interviewResult);
		return admissionProcess;
	}

	@Override
	public List<AdmissionProcess> getRequiredMeritList(AdmissionProcess ap) {
		try {
			logger.info("getRequiredMeritList(admissionProcess) method in AdmissionServiceImpl");
			admissionProcess = admissionDriveDAO.getRequiredMeritList(ap);
			if (admissionProcess != null && admissionProcess.size() != 0) {
				for (AdmissionProcess admittedStudent : admissionProcess) {
					String readableDate = new SimpleDateFormat("dd/MM/yyyy")
																.format(new Date(Integer.parseInt(admittedStudent
																.getLastFeesSubmissionDate()) * 1000L));
					admittedStudent.setLastFeesSubmissionDate(readableDate);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getRequiredMeritList(admissionProcess) method in AdmissionServiceImpl",e);
		}
		return admissionProcess;
	}

	/**
	 * this method send AdmissionProcess object for update selected candidate
	 * status and return a list of selected candidate list.
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> insertSelectedCandidates(AdmissionProcess interviewResultToDB) {
		
		logger.info("insertSelectedCandidates(AdmissionProcess interviewResultToDB) method in AdmissionServiceImpl");
		try{
			Utility util = new Utility();
			String readableDate = null;
			admissionProcess = admissionDriveDAO.insertSelectedCandidate(interviewResultToDB);
			if (admissionProcess != null && admissionProcess.size() != 0) {
				for (AdmissionProcess interviewResult : admissionProcess) {
					if (interviewResult.getLastFeesSubmissionDate() != null) {
						readableDate = util.epochToHumanReadableFormat(interviewResult.getLastFeesSubmissionDate());
					}
					interviewResult.setLastFeesSubmissionDate((readableDate));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in insertSelectedCandidates(AdmissionProcess interviewResultToDB) method in AdmissionServiceImpl: ",e);
		}
		return admissionProcess;
	}

	/**
	 * this method return list of requered candidates.
	 * 
	 * @param String
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> geRequiredCandidates(AdmissionProcess ap) {
		try{
			logger.info("geRequiredCandidates(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
			Utility util = new Utility();
			String readableDate = null;
			admissionProcess = admissionDriveDAO.getRequiredCandidates(ap);
			if(admissionProcess!=null && admissionProcess.size()!=0){
				for (AdmissionProcess interviewResult : admissionProcess) {
					if (interviewResult.getLastFeesSubmissionDate() != null) {
						readableDate = util.epochToHumanReadableFormat(interviewResult.getLastFeesSubmissionDate());
					}
					interviewResult.setLastFeesSubmissionDate((readableDate));
				}
			}
		} catch (Exception e) {
			logger.error("Exception in geRequiredCandidates(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
		}
		return admissionProcess;
	}
	
	@Override
	public PagedListHolder<AdmissionProcess> getFinalSelectedCandidatePaging(HttpServletRequest request) {
		PagedListHolder<AdmissionProcess> pagedListHolder = new PagedListHolder<AdmissionProcess>(admissionProcess);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}


	/**
	 * This method return payment done candidates
	 * 
	 * @param AdmissionProcess
	 * @return AdmissionProcess
	 */
	@Override
	public AdmissionProcess getPaymentList(AdmissionProcess interviewResultToDAO) {
		AdmissionProcess selectedCandidateForPayment = null;
		try{
			logger.info("getPaymentList(AdmissionProcess interviewResultToDAO) method in AdmmsionServiceImpl:");
			selectedCandidateForPayment = admissionDriveDAO.getPaymentList(interviewResultToDAO);
			String readableDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date(Integer.parseInt(selectedCandidateForPayment.getLastFeesSubmissionDate()) * 1000L));
			selectedCandidateForPayment.setLastFeesSubmissionDate(readableDate);
		} catch (Exception e) {
			logger.error("Exception in getPaymentList(AdmissionProcess interviewResultToDAO) method in AdmissionServiceImpl",e);
		}
	return selectedCandidateForPayment;
	}

//	/**
//	 * this method update candidate payment status and return successful payment
//	 * candidate.
//	 * 
//	 * @param AdmissionProcess
//	 * @return List<AdmissionProcess>
//	 */
//	@Override
//	public List<AdmissionProcess> updateCandidatePaymentStatus(AdmissionProcess interviewResult) {
//		List<AdmissionProcess> paymentDoneListFromDB = null;
//		try {
//			logger.info("updateCandidatePaymentStatus(AdmissionProcess interviewResult) method in AdmissionServiceImpl");
//			paymentDoneListFromDB = admissionDriveDAO.updateCandidatePaymentStatus(interviewResult);
//			if (paymentDoneListFromDB != null && paymentDoneListFromDB.size() != 0){
//				for (AdmissionProcess admittedStudent : paymentDoneListFromDB) {
//					String readableDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date(Integer.parseInt(admittedStudent.getPaymentDate()) * 1000L));
//					admittedStudent.setPaymentDate(readableDate);
//				}
//			}
//		}catch (Exception e) {
//			logger.error("Exception in updateCandidatePaymentStatus(AdmissionProcess interviewResult) method in AdmissionServiceImpl",e);
//		}
//		return paymentDoneListFromDB;
//	}

	/**
	 * This method return admission process list
	 * 
	 * @return List<Course>
	 */
	@Override
	public List<AdmissionForm> getAdmissionsOnProcessList() {
		logger.info("getAdmissionsOnProcessList() method in AdmissionServiceImpl");
		admissionsOnProcessList = admissionDriveDAO.getAdmissionsOnProcessList();
		return admissionsOnProcessList;
	}
	
	@Override
	public void getAdmissionsOnProcessSearch(Map<String, Object> parameters) {
		logger.info("getAdmissionsOnProcessSearch() method in AdmissionServiceImpl");
		admissionsOnProcessList = admissionDriveDAO.getAdmissionsOnProcessSearch(parameters);
	}
	
	@Override
	public PagedListHolder<AdmissionForm> getadmissionsOnProcessListPaging(HttpServletRequest request) {
		PagedListHolder<AdmissionForm> pagedListHolder = null;
		if(admissionsOnProcessList!=null && admissionsOnProcessList.size()!=0){
			pagedListHolder = new PagedListHolder<AdmissionForm>(admissionsOnProcessList);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setPageSize(pageSize);
		}
		return pagedListHolder;
	}

	/**
	 * This method return scheduled candidate list.
	 * 
	 * @param String
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> getScheduledFormList(AdmissionProcess admissionProcess) {
		logger.info("getScheduledFormList(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
		return admissionDriveDAO.getScheduledFormList(admissionProcess);
	}

	/**
	 * 
	 * @param admissionProcess
	 * @return
	 */
	@Override
	public List<AdmissionProcess> goBackFromReview(AdmissionProcess admissionProcess) {
		logger.info("goBackFromReview(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
		return admissionDriveDAO.goBackFromReview(admissionProcess);
	}

	/**
	 * this method return full name on particular form id.
	 * 
	 * @param String
	 * @return String
	 */
	@Override
	public String getNameForFormIdByAjax(AdmissionProcess admissionProcess) {
		logger.info("getNameForFormIdByAjax(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
		return admissionDriveDAO.getFullName(admissionProcess);
	}

	/**
	 * this method passes class to DAO
	 * 
	 * @param strCourseClass
	 */
	@Override
	public void admitStudents(AdmissionProcess admissionProcess) {
		logger.info("admitStudents(AdmissionProcess admissionProcess) method in AdmissinServiceImpl");
		admissionDriveDAO.admitStudents(admissionProcess);
	}

	/**
	 * this method return payment List for back button.
	 * 
	 * @param String
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> getPaymentListToDisplayBack(AdmissionProcess admissionProcess) {
		List<AdmissionProcess> paymentDoneListFromDB = null;
		try {
			logger.info("getPaymentListToDisplayBack(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
			paymentDoneListFromDB = admissionDriveDAO.getPaymentListToDisplayBack(admissionProcess);
			if (paymentDoneListFromDB != null && paymentDoneListFromDB.size() != 0) {
				for (AdmissionProcess admittedStudent : paymentDoneListFromDB) {
					String readableDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date(Integer.parseInt(admittedStudent
																			.getPaymentDate()) * 1000L));
					admittedStudent.setPaymentDate(readableDate);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getPaymentListToDisplayBack(AdmissionProcess admissionProcess) method in AdmissionServiceImpl",e);
		}
		return paymentDoneListFromDB;
	}

	/**
	 * This method return submitted admission form list.
	 * 
	 * @param String
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> getAdmissionFormList(AdmissionProcess formSubmission) {
		try {
			logger.info("getAdmissionFormList(AdmissionProcess formSubmission) method in AdmissionServiceImpl: ");
			Utility util = new Utility();
			admissionProcess = admissionDriveDAO.getAdmissionFormList(formSubmission);
			if (admissionProcess != null && admissionProcess.size() != 0) {
				for (AdmissionProcess submittedForm : admissionProcess) {
					if (submittedForm.getIntFormSubmissionDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(submittedForm.getIntFormSubmissionDate()).toString());
						submittedForm.setFormSubmissionDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getAdmissionFormList(AdmissionProcess formSubmission) method in AdmissionController: ",e);
		}
		return admissionProcess;
	}

	/**
	 * This method returns list of searches data.
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> getSearchList(AdmissionProcess searchingParameter) {
		try {
			logger.info("getSearchList(AdmissionProcess searchingParameter) method in AdmissionServiceImpl");
			admissionProcess = admissionDriveDAO.getSearchList(searchingParameter);
			Utility util = new Utility();
			if (admissionProcess != null && admissionProcess.size() != 0) {
				for (AdmissionProcess admittedStudent : admissionProcess) {
					if (admittedStudent.getIntPaymentDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admittedStudent.getIntPaymentDate()).toString());
						admittedStudent.setPaymentDate(readableDate);
					}
					if (admittedStudent.getIntLastFeesSubmissionDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admittedStudent.getIntLastFeesSubmissionDate()).toString());
						admittedStudent.setLastFeesSubmissionDate(readableDate);
					}
					if (admittedStudent.getIntFormSubmissionDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admittedStudent.getIntFormSubmissionDate()).toString());
						admittedStudent.setFormSubmissionDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getSearchList(AdmissionProcess searchingParameter) method in AdmissionServiceImpl",e);
		}
		return admissionProcess;
	}

	/**
	 * 
	 * @param searchingParameter
	 * @return
	 */
	@Override
	public List<AdmissionProcess> getHistorySearchList(AdmissionProcess searchingParameter) {
		logger.info("getHistorySearchList(AdmissionProcess searchingParameter) method in AdmissionServiceImpl ");
		try {
			historyAndPreviousAdmissionListFromDB = admissionDriveDAO.getHistorySearchList(searchingParameter);
			Utility util = new Utility();
			if (historyAndPreviousAdmissionListFromDB != null && historyAndPreviousAdmissionListFromDB.size() != 0) {
				for (AdmissionProcess admittedStudent : historyAndPreviousAdmissionListFromDB) {
					if (admittedStudent.getIntPaymentDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admittedStudent.getIntPaymentDate()).toString());
						admittedStudent.setPaymentDate(readableDate);
					}
					if (admittedStudent.getIntLastFeesSubmissionDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admittedStudent.getIntLastFeesSubmissionDate()).toString());
						admittedStudent.setLastFeesSubmissionDate(readableDate);
					}
					if (admittedStudent.getIntFormSubmissionDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admittedStudent.getIntFormSubmissionDate()).toString());
						admittedStudent.setFormSubmissionDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getHistorySearchList(AdmissionProcess searchingParameter) method in AdmissionServiceImpl",e);
		}
		return historyAndPreviousAdmissionListFromDB;
	}

	/**
	 * 
	 * @param searchingParameter
	 * @return
	 */
	@Override
	public List<AdmissionForm> admissionDriveSearch(
			AdmissionForm searchingParameter) {
		logger.info("admissionDriveSearch(AdmissionForm searchingParameter) method In AdmissionServiceImpl: ");
		try {
			logger.info("calling admissionDriveSearch(AdmissionForm searchingParameter) method In AdmissionDaoImpl: ");
			admissionDriveList = admissionDriveDAO.admissionDriveSearch(searchingParameter);
			Utility util = new Utility();
			if (admissionDriveList != null && admissionDriveList.size() != 0) {
				for (AdmissionForm admissionDrive : admissionDriveList) {
					if (null != admissionDrive.getAdmissionDriveStartDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionDriveStartDate());
						admissionDrive.setAdmissionDriveStartDate(readableDate);
					}
					if (null != admissionDrive.getAdmissionDriveActualEndDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionDriveActualEndDate());
						admissionDrive.setAdmissionDriveActualEndDate(readableDate);
					} else {
						admissionDrive.setAdmissionDriveActualEndDate("N/A ");
					}
					if (null != admissionDrive.getAdmissionDriveExpectedEndDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionDriveExpectedEndDate());
						admissionDrive.setAdmissionDriveExpectedEndDate(readableDate);
					}
					if (null != admissionDrive.getAdmissionFormSubmissionLastDate()) {
						String readableDate = util.epochToHumanReadableFormat(admissionDrive.getAdmissionFormSubmissionLastDate());
						admissionDrive.setAdmissionFormSubmissionLastDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception In admissionDriveSearch(AdmissionForm searchingParameter) in AdmissionServiceImpl",e);
		}
		return admissionDriveList;
	}

	/**
	 * This method return current admission status.
	 * 
	 * @param AdmissionProcess
	 * @return String
	 */
	@Override
	public String getCurrentAdmissionOnProcessStatus(AdmissionProcess admissionProcess) {
		logger.info("getCurrentAdmissionOnProcessStatus(AdmissionProcess admissionProcess) method in AsmissionServiceImpl");
		return admissionDriveDAO.getCurrentAdmissionOnProcessStatus(admissionProcess);
	}

	/**
	 * this method return previous admission list
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 */
	@Override
	public List<AdmissionProcess> getPreviousAdmissionList(AdmissionProcess admissionProcess) {
		try {
			Utility util = new Utility();
			logger.info("calling getPreviousAdmissionList( AdmissionProcess admissionProcess)in  admissionDAOImpl");
			historyAndPreviousAdmissionListFromDB = admissionDriveDAO.getPreviousAdmissionList(admissionProcess);
			if (historyAndPreviousAdmissionListFromDB != null && historyAndPreviousAdmissionListFromDB.size() != 0) {
				for (AdmissionProcess admittedStudent : historyAndPreviousAdmissionListFromDB) {
					if (admittedStudent.getIntPaymentDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admittedStudent.getIntPaymentDate()).toString());
						admittedStudent.setPaymentDate(readableDate);
					}
					if (admittedStudent.getIntLastFeesSubmissionDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admittedStudent.getIntLastFeesSubmissionDate()).toString());
						admittedStudent.setLastFeesSubmissionDate(readableDate);
					}
					if (admittedStudent.getIntFormSubmissionDate() != 0) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admittedStudent.getIntFormSubmissionDate()).toString());
						admittedStudent.setFormSubmissionDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			logger.info("Exception in getPreviousAdmissionList(AdmissionProcess admissionProcess) In admissionServiceImpl:",e);
		}
		return historyAndPreviousAdmissionListFromDB;
	}
	
	
	@Override
	public PagedListHolder<AdmissionProcess> getPreviousAdmissionListPaging(HttpServletRequest request) {
		PagedListHolder<AdmissionProcess> pagedListHolder = new PagedListHolder<AdmissionProcess>(historyAndPreviousAdmissionListFromDB);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	
	@Override
	public PagedListHolder<AdmissionProcess> getHistoryListPagePaging(HttpServletRequest request) {
		PagedListHolder<AdmissionProcess> pagedListHolder = new PagedListHolder<AdmissionProcess>(historyAndPreviousAdmissionListFromDB);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	/**
	 * This method return list of course class wise.
	 * 
	 * @param String
	 * @return AdmissionForm
	 */
	@Override
	public AdmissionForm getAdmissionForm(AdmissionForm admissionForm) {
		logger.info("getAdmissionForm(AdmissionForm admissionForm) method in AdmissionServiceImpl:");
		return admissionDriveDAO.getAdmissionForm(admissionForm);
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public List<AdmissionForm> getAdmittedDriveList() {
		try {
			logger.info("getAdmittedDriveList() method in AdmissionServiceImpl");
			Utility util = new Utility();
			admissionDriveList = admissionDriveDAO.getAdmittedDriveList();
			if (admissionDriveList != null && admissionDriveList.size() != 0) {
				for (AdmissionForm admissionForm : admissionDriveList) {
					if (admissionForm.getAdmissionDriveStartDate() != null) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admissionForm.getAdmissionDriveStartDate()).toString());
						admissionForm.setAdmissionDriveStartDate(readableDate);
					}
					if (admissionForm.getAdmissionFormSubmissionLastDate() != null) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admissionForm.getAdmissionFormSubmissionLastDate()).toString());
						admissionForm.setAdmissionFormSubmissionLastDate(readableDate);
					}
					if (admissionForm.getAdmissionDriveExpectedEndDate() != null) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admissionForm.getAdmissionDriveExpectedEndDate()).toString());
						admissionForm.setAdmissionDriveExpectedEndDate(readableDate);
					}
					if (admissionForm.getAdmissionDriveActualEndDate() != null) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admissionForm.getAdmissionDriveActualEndDate()).toString());
						admissionForm.setAdmissionDriveActualEndDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getAdmittedDriveList()method  in AdmissionServiceImpl",e);
		}
		return admissionDriveList;
	}
	
	@Override
	public PagedListHolder<AdmissionForm> getadmittedDriveListPaging(HttpServletRequest request) {
		PagedListHolder<AdmissionForm> pagedListHolder = null;
		if(admissionDriveList!=null && admissionDriveList.size()!=0){
			pagedListHolder = new PagedListHolder<AdmissionForm>(admissionDriveList);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setPageSize(pageSize);
		}
		return pagedListHolder;
	}

	/**
	 * 
	 * @param admissionProcess
	 * @return
	 */
	@Override
	public List<AdmissionProcess> admittedDriveListDetails(
			AdmissionProcess admissionProcess) {
		
		try {
			logger.info("admittedDriveListDetails(AdmissionProcess admissionProcess) method in AdmissionServiceImpl");
			Utility util = new Utility();
			admittedDriveListDetails = admissionDriveDAO.admittedDriveListDetails(admissionProcess);
			if(admittedDriveListDetails!=null && admittedDriveListDetails.size()!=0){
				for (AdmissionProcess admittedDriveList : admittedDriveListDetails) {
					if (admittedDriveList.getPaymentDate() != null) {
						String readableDate = util.epochToHumanReadableFormat(new Integer(admittedDriveList.getPaymentDate()).toString());
						admittedDriveList.setPaymentDate(readableDate);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in admittedDriveListDetails(AdmissionProcess admissionProcess) method in AdmissionServiceImpl",e);
		}
		return admittedDriveListDetails;
	}
	
	
	@Override
	public PagedListHolder<AdmissionProcess> getAdmittedDriveListDetailsPaging(HttpServletRequest request) {
		PagedListHolder<AdmissionProcess> pagedListHolder = new PagedListHolder<AdmissionProcess>(admittedDriveListDetails);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	@Override
	public AcademicYear getYearClassList() {
		logger.info(" getYearClassList() method in AdmissionServiceImpl: ");
		return admissionDriveDAO.getYearClassList();
	}

	@Override
	public AdmissionForm getFormDetails(AdmissionForm af) {
		logger.info(" getFormDetails() method in AdmissionServiceImpl: ");
		return admissionDriveDAO.getFormDetails(af);
	}
	
	/**
	 * @author anup.roy
	 * 02.08.2017
	 * */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public AcademicYear submitSaleFormDetails(AdmissionForm admissionForm) {
		logger.info(" submitSaleFormDetails(AdmissionForm admissionForm) method in AdmissionServiceImpl: ");
		return admissionDriveDAO.submitSaleFormDetails(admissionForm);
	}

	@Override
	public AdmissionForm getStreamClassYearCourseDetails(AdmissionProcess formSubmission) {
		logger.info(" getStreamClassYearCourseDetails(AdmissionProcess formSubmission) method in AdmissionServiceImpl: ");
		streamClassYearForPaging = admissionDriveDAO.getStreamClassYearCourseDetails(formSubmission);;
		return streamClassYearForPaging;
	}

	@Override
	public List<AdmissionForm> getActiveAdmissionDrives() {
		logger.info(" getActiveAdmissionDrives( ) method in AdmissionServiceImpl: ");
		activeAdmissionDrives = admissionDriveDAO.getActiveAdmissionDrives();
	return activeAdmissionDrives;
	}
	
	@Override
	public void getActiveAdmissionDriveSearch(Map<String, Object> parameters) {
		logger.info(" getActiveAdmissionDriveSearch( ) method in AdmissionServiceImpl: ");
		activeAdmissionDrives = admissionDriveDAO.getActiveAdmissionDriveSearch(parameters);
	}
	
	@Override
	public PagedListHolder<AdmissionForm> activeAdmissionDrivesPaging(HttpServletRequest request) {
		PagedListHolder<AdmissionForm> pagedListHolder = null;
		if(activeAdmissionDrives!=null && activeAdmissionDrives.size()!=0){
			pagedListHolder = new PagedListHolder<AdmissionForm>(activeAdmissionDrives);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			pagedListHolder.setPageSize(pageSize);
		}
		return pagedListHolder;
	}

	@Override
	public List<AdmissionProcess> admittedDriveListDetailsSearch(AdmissionProcess searchingParameter) {
		logger.info(" admittedDriveListDetailsSearch(AdmissionProcess searchingParameter) method in AdmissionServiceImpl: ");
		admittedDriveListDetails = admissionDriveDAO.admittedDriveListDetailsSearch(searchingParameter);
		return admittedDriveListDetails;
	}

	/*@Override
	public int insertAdmissionFormSubmissionExcelBulkData(String excelFilePath,SessionObject sessionObject) {
		int insertStatus = 0;
		try{
			List<AdmissionProcess> candidateList = dataUtility.readAdmissionFormSubmissionExcelBulkData(excelFilePath);
			if(candidateList!=null && candidateList.size()!=0){
				candidateList.get(0).setUpdatedBy(sessionObject.getUserId());
				insertStatus = dataDAO.batchInsertForAdmissionFormSubmissionExcelBulkData(candidateList);
			}
		}catch(Exception e){
			logger.error("Exception in insertAdmissionFormSubmissionExcelBulkData() method for read excel and insert", e);
			e.printStackTrace();
		}
		return insertStatus;
	}*/

	
	@Override
	public List<LoggingAspect> getAdmissionDriveLogDetails() {
		return admissionDriveDAO.getAdmissionDriveLogDetails();
	}

	@Override
	public List<LoggingAspect> getSaleFormLogDetails() {
		return admissionDriveDAO.getSaleFormLogDetails();
	}

	@Override
	public List<LoggingAspect> getAdmissionScheduleInterviewLogDetails(String admissonDriveName) {
		return admissionDriveDAO.getAdmissionScheduleInterviewLogDetails(admissonDriveName);
	}

	@Override
	public List<LoggingAspect> getAdmissionInterviewResultLogDetails(String admissonDriveName) {
		return admissionDriveDAO.getAdmissionInterviewResultLogDetails(admissonDriveName);
	}

	@Override
	public List<LoggingAspect> getAdmissionPaymentDateSetupLogDetails(String admissonDriveName) {
		return admissionDriveDAO.getAdmissionPaymentDateSetupLogDetails(admissonDriveName);

	}

	@Override
	public List<AdmissionProcess> getSubmittedFormDetails(
			AdmissionProcess admissionProcess) {
		return admissionDriveDAO.getSubmittedFormDetails(admissionProcess);
	}

	@Override
	public List<AdmissionProcess> getinterviewScheduledList(AdmissionProcess admissionProcess) {
		return admissionDriveDAO.getinterviewScheduledList(admissionProcess);
	}
	@Override
	public List<AdmissionProcess> getSelectedReviewdNonselectedStudentList(AdmissionProcess makeInterviewResultObject) {
		return admissionDriveDAO.getSelectedReviewdNonselectedStudentList(makeInterviewResultObject);
	}
	@Override
	public List<AdmissionProcess> getResultForViewMeritList(AdmissionProcess interviewResultToDB) {
		return admissionDriveDAO.getResultForViewMeritList(interviewResultToDB);
	}
	@Override
	public String getAdmissionDriveState(String strDriveName) {
		return admissionDriveDAO.getAdmissionDriveState(strDriveName);
	}
	@Override
	public String getstatusOfAdmissionDrive(AdmissionProcess admissionProcess) {
		return admissionDriveDAO.getstatusOfAdmissionDrive(admissionProcess);
	}
	@Override
	public List<AdmissionProcess> getAllStudentForMeritList(AdmissionProcess interviewResult) {
		return admissionDriveDAO.getAllStudentForMeritList (interviewResult);
	}
	@Override
	public String updadateAdmissonDriveStatus(String driveName) {
		return admissionDriveDAO.updadateAdmissonDriveStatus (driveName);
	}
	@Override
	public String saveCandidateDetailsFromPortal(AdmissionFormForPortalStudents candidate, String userId) {
		return admissionDriveDAO.saveCandidateDetailsFromPortal(candidate, userId);
	}
	@Override
	public String saveScrutinizedCandidatesFromPortal(AdmissionFormForPortalStudents candidate, String userId) {
		return admissionDriveDAO.saveScrutinizedCandidatesFromPortal(candidate, userId);
	}
	@Override
	public String saveSelectedCandidatesFromPortal(AdmissionFormForPortalStudents candidate, String userId) {
		return admissionDriveDAO.saveSelectedCandidatesFromPortal(candidate, userId);
	}
	@Override
	public List<Data> setProgrammeDetailsListForPortal() {
		return admissionDriveDAO.setProgrammeDetailsListForPortal();
	}
	@Override
	public List<LocationDetailsForPortal> setLocationDetailsForPortal() {
		return admissionDriveDAO.setLocationDetailsForPortal();
	}
	@Override
	public String saveAdmittedCandidatesFromPortal(AdmissionFormForPortalStudents candidate, String userId) {
		return admissionDriveDAO.saveAdmittedCandidatesFromPortal(candidate , userId);
	}
	@Override
	public List<Data> setAlumniDataToPortal() {
		return admissionDriveDAO.setAlumniDataToPortal();
	}
	@Override
	public ProgrammeDetailsForPortal setProgrammeDetailsForPortal(String courseCode) {
		return admissionDriveDAO.setProgrammeDetailsForPortal(courseCode);
	}
	@Override
	public List<Course> getUnsendProgramListToPortal() {
		return admissionDriveDAO.getUnsendProgramListToPortal();
	}
	@Override
	public String updateProgramStatus(String courseCode) {
		return admissionDriveDAO.updateProgramStatus(courseCode);
	}
	@Override
	public List<LocationDetailsForPortal> setInterviewLocationDetailsToPortal(String courseCode) {
		return admissionDriveDAO.setInterviewLocationDetailsToPortal(courseCode);
	}
	@Override
	public List<NoticeBoard> getNoticeList() {
		return admissionDriveDAO.getNoticeList();
	}
	@Override
	public List<Course> getUnsendProgramsWithLocations() {
		return admissionDriveDAO.getUnsendProgramsWithLocations();
	}
	@Override
	public List<Course> getDriveListForStudents() {
		return admissionDriveDAO.getDriveListForStudents();
	}
	@Override
	public String getDrivesAgainstCourse(String course) {
		return admissionDriveDAO.getDrivesAgainstCourse(course);
	}

	@Override
	public String getDriveWithUnsentLocationAgainstCourse(String course) {
		return admissionDriveDAO.getDriveWithUnsentLocationAgainstCourse(course);
	}

	@Override
	public String updateLocationSendStatusForDrive(String drive) {
		return admissionDriveDAO.updateLocationSendStatusForDrive(drive);
	}
}
