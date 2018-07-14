package com.qts.icam.dao.impl.admission;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.admission.AdmissionDAO;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.admission.AdmissionDrive;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.admission.AdmissionProcess;
import com.qts.icam.model.admission.CustomizedAdmissionProcess;
import com.qts.icam.model.admission.Form;
import com.qts.icam.model.admission.LocationDetailsForPortal;
import com.qts.icam.model.admission.Marks;
import com.qts.icam.model.backoffice.Fees;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.AdmissionFormForPortalStudents;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Data;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.PaymentDetails;
import com.qts.icam.model.common.ProgrammeDetailsForPortal;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Storage;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.finance.TransactionDetails;
import com.qts.icam.model.finance.TransactionalWorkingArea;
/*import com.qts.icam.model.finance.PreviousYearFinanceData;*/
import com.qts.icam.utility.Utility;

/**
 * AdmissionDriveDAO.java - This DAO is responsible for CRUD operation on
 * admission related data.
 * 
 * @author parmanand.singh
 * @version 1.0
 * 
 */
@Repository
public class AdmissionDAOImpl implements AdmissionDAO {

	public static Logger logger = Logger.getLogger(AdmissionDAOImpl.class);	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	/**
	 * Fetches list of admissions for different years.
	 * 
	 * @param String
	 * @return List<AdmissionForm>
	 * 
	 */
	@Override
	public List<AdmissionForm> getAdmissionDriveList(String strDrive) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionForm> admissionDriveList = null;
		try {
			if (strDrive.equalsIgnoreCase("completed")) {
				admissionDriveList = session.selectList("selectCompletedAdmissionDrive");
			} else {
				admissionDriveList = session.selectList("selectCurrentAdmissionDrive");
			}
		} catch (Exception e) {
			logger.error("Exception IN getAdmissionDriveList(String strDrive) in AdmissionDAOImpl: ",e);
		} finally {
			session.close();
		}
		return admissionDriveList;
	}

	/**
	 * This is a generic method for fetching all the complete path of various
	 * Attachments in a particular folder.
	 * 
	 * @param String
	 * @return List<Storage>
	 * 
	 */
	@Override
	public List<Storage> getAttachmentList(String folderName) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Storage> completePathList = null;
		logger.debug("In getAttachmentList method of AdmissionDriveDAO - folderName parameter is : "+ folderName);
		try {
			completePathList = session.selectList("select_complete_path",folderName);
		} catch (Exception e) {
			logger.error("Exception in getAttachmentList(String folderName) in AdmissionDAOImpl.java:  ",e);
		} finally {
			session.close();
		}
		return completePathList;
	}

	@Override
	public List<AdmissionForm> getAdmissionFormList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionForm> admissionFormList = new ArrayList<AdmissionForm>();
		try {
			logger.info("getAdmissionFormList() method in AdmissionDaoImpl");
			List<AdmissionForm> admissionFormListFromDB = session.selectList("select_admission_form");
			if (admissionFormListFromDB != null && admissionFormListFromDB.size() != 0) {
				for (AdmissionForm af : admissionFormListFromDB) {
					Integer noOfAdmittedForCourse = session.selectOne("selectNoOfAdmittedForCourse", af);
					if (noOfAdmittedForCourse != null) {
						if (noOfAdmittedForCourse < af.getNoOfOpenings()) {
							af.setNoOfOpenings(af.getNoOfOpenings() - noOfAdmittedForCourse);
							admissionFormList.add(af);
						}
					}else {
						admissionFormList.add(af);
					}
				}
			}
//			if (admissionFormList != null && admissionFormList.size() != 0) {
//				for (AdmissionForm af : admissionFormList) {
//					AdmissionForm amount = session.selectOne("selectFromFees",af);
//					if (amount != null) {
//						af.setFormFees(amount.getFormFees());
//					}
//					amount = session.selectOne("selectCourseFees", af);
//					if (amount != null) {
//						af.setCourseFees(amount.getCourseFees());
//					}
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getAdmissionFormList() method in AdmissionDaoImpl: ",e);
		} finally {
			session.close();
		}
		return admissionFormList;
	}
	
	@Override
	public List<AdmissionForm> getAdmissionFormDetailsSearch(Map<String, Object> parameters) {
			SqlSession session =sqlSessionFactory.openSession();
			List<AdmissionForm> admissionFormList = new ArrayList<AdmissionForm>();
		try {
			logger.info("getAdmissionFormList() method in AdmissionDaoImpl");
			List<AdmissionForm> admissionFormListFromDB = session.selectList("select_admission_form",parameters);
			if (admissionFormListFromDB != null && admissionFormListFromDB.size() != 0) {
				for (AdmissionForm af : admissionFormListFromDB) {
					Integer noOfAdmittedForCourse = session.selectOne("selectNoOfAdmittedForCourse", af);
					if (noOfAdmittedForCourse != null) {
						if (noOfAdmittedForCourse < af.getNoOfOpenings()) {
							af.setNoOfOpenings(af.getNoOfOpenings() - noOfAdmittedForCourse);
							admissionFormList.add(af);
						}
					} else {
						admissionFormList.add(af);
					}
				}
			}
			if (admissionFormList != null && admissionFormList.size() != 0) {
				for (AdmissionForm af : admissionFormList) {
					AdmissionForm amount = session.selectOne("selectFromFees",af);
					if (amount != null) {
						af.setFormFees(amount.getFormFees());
					}
					amount = session.selectOne("selectCourseFees", af);
					if (amount != null) {
						af.setCourseFees(amount.getCourseFees());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getAdmissionFormList() method in AdmissionDaoImpl: ",e);
		} finally {
			session.close();
		}
		return admissionFormList;
	}
	

	/**
	 * This method is used to insert data from "Submission Form" form that
	 * accepts Candidate's general information and attachment(s)
	 * 
	 * @param List
	 *            <FormSubmission>
	 * @param FormSubmission
	 * @return void
	 * 
	 */
	@Override
	public List<AdmissionProcess> insertAdmissionForm(
			List<AdmissionProcess> formSubmissionList,
			AdmissionProcess formSubmissionOtherInformation) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> returnsSubmittedList = null;
		try {
			logger.info("insertAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmissionOtherInformation) method in AdmissionDaoImpl");
			Utility util = new Utility();
			logger.info("USER ID   DAO ::" + formSubmissionOtherInformation.getUserId());
			for (AdmissionProcess formSubmission : formSubmissionList) {
				formSubmission.setFolderObjectId(util.getBase64EncodedID("AdmissionDriveDAO"));
				formSubmission.setAttachmentObjectId(util.getBase64EncodedID("AdmissionDriveDAO"));
			}
			//System.out.println("Date of birth===="+formSubmissionOtherInformation.getDateOfBirth());
			int intStatusInfo = session.insert("insert_candidate_info",formSubmissionOtherInformation);
			int intStatusList = session.insert("insert_upload_list",formSubmissionList);
			session.commit();
			returnsSubmittedList = session.selectList("getSubmittedFormList",formSubmissionOtherInformation);
			logger.info("Acknowledgment for General Information by insertAdmissionFormService() of AdmissionDriveDAO :"+ intStatusInfo);
			logger.info("Acknowledgment for List of Attachments by insertAdmissionFormService() of AdmissionDriveDAO :"+ intStatusList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In insertAdmissionFormService() of AdmissionDriveDAO - :Exception",e);
		} finally {
			session.close();
		}
		return returnsSubmittedList;
	}

	/**
	 * This method is used to insert data form "Interview Schedule" form that
	 * accepts interview Details
	 * 
	 * @param List
	 *            <InterviewSchedule>
	 * @return void
	 * 
	 */
	@Override
	public List<AdmissionProcess> insertInterviewSchedule(AdmissionProcess interviewschedule) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> interviewSchedule = null;
		try {
			logger.info("insertInterviewSchedule(AdmissionProcess interviewschedule) method in AdmissionDaoImpl");
			List<Form> formList = interviewschedule.getFormList();
			if (formList != null) {
				for (Form form : formList) {
					AdmissionProcess interviewScheduleToInsert = new AdmissionProcess();
					interviewScheduleToInsert.setFormId(form.getStrFormId());
					interviewScheduleToInsert.setCourseClass(interviewschedule.getCourseClass());
					interviewScheduleToInsert.setInterviewDateAndTime(interviewschedule.getInterviewDateAndTime());
					interviewScheduleToInsert.setExaminerName(interviewschedule.getExaminerName());
					interviewScheduleToInsert.setReviewerName(interviewschedule.getReviewerName());
					interviewScheduleToInsert.setRoomNo(interviewschedule.getRoomNo());
					interviewScheduleToInsert.setVenue(interviewschedule.getVenue());
					interviewScheduleToInsert.setFormName(interviewschedule.getFormName());
					interviewScheduleToInsert.setAdmissionYear(interviewschedule.getAdmissionYear());
					int intInterviewStatus = session.insert("insert_interview_Details",interviewScheduleToInsert);
					session.commit();
					interviewSchedule = session.selectList("getInterviewDetails", interviewschedule);
					logger.info("Acknowledgment for INTERVIEW SCHEDULED insertion by insertInterviewScheduleDao() of AdmissionDriveDAO :"+ intInterviewStatus);
				}
			}
		} catch (Exception e) {
			logger.info("Exception in insertInterviewSchedule(AdmissionProcess interviewschedule) method in AdmissionDaoImpl",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return interviewSchedule;
	}

	/**
	 * This method is used to get Interview ScheduleList
	 * 
	 * @param String
	 * @return List<AdmissionProcess>
	 * 
	 */
	@Override
	public List<AdmissionProcess> getInterviewScheduleList(AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> interviewSchedule = null;
		try {
			logger.info("getInterviewScheduleList(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
			interviewSchedule = session.selectList("getInterviewDetails",admissionProcess);
		} catch (Exception e) {
			logger.error("Exception in getInterviewScheduleList(AdmissionProcess admissionProcess) method in AdmissionDaoImpl",e);
		} finally {
			session.close();
		}
		return interviewSchedule;
	}

	/**
	 * This method is used for fetching all FormID for displaying in the
	 * "InterviewResult Details" form
	 * 
	 * @param void
	 * @return List<FormSubmission>
	 * 
	 */
	@Override
	public List<AdmissionProcess> getGeneratedFormIdList(
			AdmissionProcess formSubmission) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> formIdListForMarks = null;
		try {
			logger.info("getGeneratedFormIdList(AdmissionProcess formSubmission) method in AdmissionDaoImpl");
			formIdListForMarks = session.selectList("getGenerateFormWithClass",formSubmission);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getGeneratedFormIdList(AdmissionProcess formSubmission) method in AdmissionDaoImpl",e);
		} finally {
			session.close();
		}
		return formIdListForMarks;
	}

	/**
	 * 
	 * This method is to get Current Admission OnProcess Status
	 * 
	 * @param AdmissionProcess
	 * @return String
	 */
	@Override
	public String getCurrentAdmissionOnProcessStatus(AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			/**
			 * States of Admission Process
			 * 
			 * FormSubmission = FS
			 * ScheduleInterview = SI
			 * InterviewResult = IR
			 * MeritList = ML
			 * Payment = P
			 */
			
			logger.info("getCurrentAdmissionOnProcessStatus(AdmissionProcess admissionProcess) method in AsmissionDaoImpl");
			CustomizedAdmissionProcess customizedAdmissionProcess = session.selectOne("selectCustomizedAdmissionProcessForAdmissionDrive", admissionProcess);
			if(customizedAdmissionProcess!=null){
				if(customizedAdmissionProcess.isFormSubmission() == true && customizedAdmissionProcess.isScheduleInterview() == true && customizedAdmissionProcess.isInterviewResult() == true && customizedAdmissionProcess.isMeritList() == true && customizedAdmissionProcess.isPayment() == true){
					session.selectOne("selectCurrentAdmissionOnProcessStatusForAllProcess",admissionProcess);
				}
				if(customizedAdmissionProcess.isFormSubmission() == true && customizedAdmissionProcess.isScheduleInterview() == false && customizedAdmissionProcess.isInterviewResult() == false && customizedAdmissionProcess.isMeritList() == true && customizedAdmissionProcess.isPayment() == true){
					session.selectOne("selectCurrentAdmissionOnProcessStatusFor_FS_ML_P",admissionProcess);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getCurrentAdmissionOnProcessStatus(AdmissionProcess admissionProcess) method in AsmissionDaoImpl",e);
		} finally {
			session.close();
		}
		return admissionProcess.getSearchStatus();
	}

	/**
	 * This method is used for inserting interview details and marks obtained in
	 * every subject
	 * 
	 * @param List
	 *            <InterviewResult>
	 * @param MakeInterviewResult
	 * @return List<InterviewResult>
	 * 
	 */
	@Override
	public List<AdmissionProcess> insertResultDetails(
			AdmissionProcess interviewResultToDB) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> interviewResultListFromDB = null;
		try {
			logger.info("insertResultDetails(AdmissionProcess interviewResultToDB) method in AdmissionDaoImpl");
			Utility util = new Utility();
			interviewResultToDB.setInterviewResultObjId(util.getBase64EncodedID("AdmissionDriveDAO"));
			int intDecisionInfo = session.insert("update_admiss_form_rec_with_decision_info",interviewResultToDB);
			session.commit();
			List<Marks> marksListToDB = interviewResultToDB.getMarksList();
			if (marksListToDB != null && marksListToDB.size() != 0) {
				for (Marks marks : marksListToDB) {
					marks.setStrMarksObjId(util.getBase64EncodedID("AdmissionDriveDAO"));
					marks.setAdmissionYear(interviewResultToDB.getAdmissionYear());
					marks.setCourseClass(interviewResultToDB.getCourseClass());
					marks.setFormName(interviewResultToDB.getFormName());
					marks.setUpdatedBy(interviewResultToDB.getUpdatedBy());
					
				}
				int intMarksStatusList = session.insert("insert_marks_list",marksListToDB);
				session.commit();
				logger.info("Acknowledgment for INTERVIEW DETAILS insertion by insertResultDetailsDao() of AdmissionDriveDAO :"+ intMarksStatusList);
				logger.info("Acknowledgment for INTERVIEW DETAILS insertion by insertResultDetailsDao() of AdmissionDriveDAO :"+ intDecisionInfo);
			}
			interviewResultListFromDB = (session.selectList("selectedCandidate", interviewResultToDB));
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.debug("In insertResultDetailsDao() of AdmissionDriveDAO - problem in catch");
		} finally {
			session.close();
		}
		return interviewResultListFromDB;
	}

	/**
	 * This method is used to get Interviewed CandidateList
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 * 
	 */
	@Override
	public List<AdmissionProcess> getInterviewedCandidateList(
			AdmissionProcess interviewResult) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> interviewResultListFromDB = null;
		try {
			logger.info("getInterviewedCandidateList(AdmissionProcess interviewResult) in AdmissionDaoImpl");
			interviewResultListFromDB = session.selectList("selectedCandidate",interviewResult);
		} catch (Exception e) {
			logger.error("Exception  In getInterviewedCandidateList(AdmissionProcess interviewResult) - problem in catch",e);
		} finally {
			session.close();
		}
		return interviewResultListFromDB;
	}

	/**
	 * This method is used for updating interview details and marks obtained in
	 * every subject
	 * 
	 * @param List
	 *            <InterviewResult>
	 * @param MakeInterviewResult
	 * @return List<InterviewResult>
	 * 
	 */
	@Override
	public List<AdmissionProcess> updateResultDetails(AdmissionProcess interviewResultToDB) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> interviewResultListFromDB = null;
		Utility util = new Utility();
		interviewResultToDB.setInterviewResultObjId(util.getBase64EncodedID("AdmissionDriveDAO"));
		try {
			logger.info("updateResultDetails(AdmissionProcess interviewResultToDB) method in AdmissionDaoImpl");
			int intOldMarksStatusList = session.insert("update_marks_list",interviewResultToDB);
			logger.info("Acknowledgment for INTERVIEW MARKS DETAIL updation by updateResultDetails() of AdmissionDriveDAO :"+ intOldMarksStatusList);
			int intDecisionInfo = session.insert("update_admiss_form_rec_with_decision_info",interviewResultToDB);
			List<Marks> marksListToDB = interviewResultToDB.getMarksList();
			if (marksListToDB != null && marksListToDB.size() != 0) {
				for (Marks marks : marksListToDB) {
					marks.setStrMarksObjId(util.getBase64EncodedID("AdmissionDriveDAO"));
					marks.setAdmissionYear(interviewResultToDB.getAdmissionYear());
					marks.setCourseClass(interviewResultToDB.getCourseClass());
					marks.setFormName(interviewResultToDB.getFormName());
				}
				// Edited marks list inserted as new record in DB
				int intNewMarksStatusList = session.insert("insert_marks_list",marksListToDB);
				session.commit();
				logger.info("Acknowledgment for INTERVIEW DETAILS updation by updateResultDetails() of AdmissionDriveDAO :"+ intNewMarksStatusList);
				logger.info("Acknowledgment for INTERVIEW DETAILS updation by updateResultDetails() of AdmissionDriveDAO :"+ intDecisionInfo);
			}
			interviewResultListFromDB = session.selectList("selectedCandidate",interviewResultToDB);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("Exception in updateResultDetails(AdmissionProcess interviewResultToDB) method in AdmissionDaoImpl - problem in catch",e);
		} finally {
			session.close();
		}
		return interviewResultListFromDB;
	}

	/**
	 * This method is used to fetch the last generated Form ID for specified
	 * form name
	 * 
	 * @param AdmissionForm
	 * @return AdmissionForm
	 * 
	 */
	@Override
	public AdmissionForm getLastFormId(AdmissionForm admissionFormName) {
		AdmissionForm admissionFormCode = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("getLastFormID(AdmissionForm admissionForm) method in AdmissionDaoImpl: ");
			admissionFormCode = session.selectOne("lastFormCode",admissionFormName);
		} catch (Exception e) {
			logger.error("Exception in getLastFormID(AdmissionForm admissionForm) method in AdmissionDaoImpl: ",e);
		} finally {
			session.close();
		}
		return admissionFormCode;
	}

	@Override
	public int checkOpeningAdmissionDriveStatus(AdmissionForm admissionForm) {
		int checkOpeningAdmissionDriveStatus = 0;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			logger.info("checkOpeningAdmissionDriveStatus(AdmissionForm admissionForm) method in AdmissionDaoImpl: ");
			String courseCode = session.selectOne("getCourseCodeForDrive",admissionForm);
			admissionForm.setCourseCode(courseCode);
			checkOpeningAdmissionDriveStatus = session.selectOne("selectAdmissionOpeningStatus", admissionForm);
		} catch (Exception e) {
			logger.error("Exception in checkOpeningAdmissionDriveStatus(AdmissionForm admissionForm) method in AdmissionDaoImpl: ",e);
		} finally {
			session.close();
		}
		return checkOpeningAdmissionDriveStatus;
	}

	/**
	 * This method is used to insert a new row of Admission Form
	 * 
	 * @param admissionForm
	 * @return void
	 * 
	 */
	@Override
	public List<AdmissionForm> insertAdmissionDrive(AdmissionForm admissionForm) {
		Utility util = new Utility();
		List<AdmissionForm> admissionDriveList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			//System.out.println("Year :: "+admissionForm.getAdmissionFormYear()+"\nFees :: "+admissionForm.getFormFees());
			logger.info("insertAdmissionDrive(AdmissionForm admissionForm) method in AdmissionDaoImpl:");
			admissionForm.setAdmissionFormObjectID(util.getBase64EncodedID("AdmissionDriveDAO"));
			//System.out.println("UpdatedBy::::::::: "+admissionForm.getUpdatedBy());
			session.insert("insertIntoAdmissionDrive", admissionForm);
			session.commit();
			admissionDriveList = session.selectList("getActiveListFromAdmissionDrive");
		} catch (Exception t) {
			t.printStackTrace();
			logger.error("Exception in insertAdmissionDrive(AdmissionForm admissionForm) method in AdmissionSaoImpl:",t);
		} finally {
			session.close();
		}
		return admissionDriveList;
	}

	@Override
	public List<AdmissionForm> getActiveAdmissionDrives() {
		List<AdmissionForm> admissionDriveList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("getActiveAdmissionDrives() method in AdmissionSaoImpl:");
			admissionDriveList = session.selectList("getActiveListFromAdmissionDrive");
		} catch (Exception t) {
			logger.error("Exception in getActiveAdmissionDrives() method in AdmissionSaoImpl method in AdmissionSaoImpl:",t);
		} finally {
			session.close();
		}
		return admissionDriveList;
	}
	
	@Override
	public List<AdmissionForm> getActiveAdmissionDriveSearch(Map<String, Object> parameters) {
		List<AdmissionForm> admissionDriveList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("getActiveAdmissionDriveSearch() method in AdmissionSaoImpl:");
			admissionDriveList = session.selectList("getActiveListFromAdmissionDrive",parameters);
		} catch (Exception t) {
			logger.error("Exception in getActiveAdmissionDriveSearch() method in AdmissionSaoImpl method in AdmissionSaoImpl:",t);
		} finally {
			session.close();
		}
		return admissionDriveList;
	}

	/**
	 * This method is used to insert FromId.
	 * 
	 * @param admissionFormList
	 * @return void
	 * 
	 */
	@Override
	public String insertAdmissionForm(ArrayList<AdmissionForm> admissionFormList) {
		Utility util = new Utility();
		String lastFormId = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("insertFormId(ArrayList<AdmissionForm> admissionFormList) method in AdmissionDaoImpl");
			for (AdmissionForm admissionForm : admissionFormList) {
				admissionForm.setAdmissionFormObjectID(util.getBase64EncodedID("AdmissionDriveDAO"));
				//System.out.println("updated by = ======     "+ admissionForm.getUpdatedBy());
				//System.out.println("admission drive name =============="+admissionForm.getAdmissionDriveName());
			}
			
			session.insert("insertFormId", admissionFormList);
			if (admissionFormList != null && admissionFormList.size() != 0) {
				AdmissionForm admissionForm = new AdmissionForm();
				admissionForm.setAdmissionFormObjectId(util.getBase64EncodedID("AdmissionDriveDAO"));
				admissionForm.setUpdatedBy(admissionFormList.get(0).getUpdatedBy());
				admissionForm.setAdmissionFormYear(admissionFormList.get(0).getAdmissionFormYear());
				admissionForm.setCourseClass(admissionFormList.get(0).getCourseClass());
				admissionForm.setNoOfOpenings(admissionFormList.size());
				admissionForm.setAdmissionDriveName(admissionFormList.get(0).getAdmissionDriveName());
				AdmissionForm af = session.selectOne("selectFormDetails",admissionForm);
				if (af != null) {
					session.update("updateFormDetails", admissionForm);
				} else {
					session.insert("insertFormDetails", admissionForm);
				}
			}
			session.commit();
			AdmissionForm admissionFormName = new AdmissionForm();
			if (admissionFormList != null && admissionFormList.size() != 0) {
				admissionFormName.setAdmissionDriveName(admissionFormList.get(0).getAdmissionDriveName());
				admissionFormName.setAdmissionFormYear(admissionFormList.get(0).getAdmissionFormYear());
				admissionFormName.setCourseClass(admissionFormList.get(0).getCourseClass());
				admissionFormName = getLastFormId(admissionFormName);
				if (admissionFormName != null) {
					lastFormId = admissionFormName.getAdmissionDriveCode();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("Exception in insertFormId(ArrayList<AdmissionForm> admissionFormList) method in AdmissionDaoImpl",e);
		} finally {
			session.close();
		}
		return lastFormId;
	}

	@Override
	public AdmissionForm getFormDetails(AdmissionForm af) {
		AdmissionForm admissionForm = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("getFormDetails(AdmissionForm af) method in AdmissionDaoImpl");			
			admissionForm = session.selectOne("selectFormDetails", af);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getFormDetails(AdmissionForm af) method in AdmissionDaoImpl",e);
		} finally {
			session.commit();
		}
		return admissionForm;
	}

	/**
	 * This method is used for getting the class
	 * 
	 * @return List<FormSubmission>
	 * 
	 */
	@Override
	public List<AdmissionProcess> getCourseClasses() {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> formSubmissionClassList = null;
		try {
			formSubmissionClassList = session.selectList("selectClassList");
			if (formSubmissionClassList != null) {
				logger.info("IN getCourseClass() of AdmissionDriveDAO -  DISTINCT CLASSES ARE  :"+ formSubmissionClassList.size());
			} else {
				logger.info("IN getCourseClass() of AdmissionDriveDAO - DATA NOT FOUND");
			}
		} catch (Exception e) {
			logger.error("IN getCourseClass() of AdmissionDriveDAO: Exception",e);
		} finally {
			session.close();
		}
		return formSubmissionClassList;
	}

	/**
	 * This method is used to get Form Details
	 * 
	 * @param String
	 * @return AdmissionProcess
	 * 
	 */
	@Override
	public AdmissionProcess getFormDetails(
			AdmissionProcess submitAdmissionFormForEdit) {
		SqlSession session =sqlSessionFactory.openSession();
		AdmissionProcess formForEdit = null;
		try {
			logger.info(" getFormDetails(AdmissionProcess submitAdmissionFormForEdit) method in AdmissionDaoImpl.java:");
			formForEdit = session.selectOne("formInformationForEditing",submitAdmissionFormForEdit);
		} catch (Exception e) {
			logger.error("Exception in getFormDetails(AdmissionProcess submitAdmissionFormForEdit) method in AdmissionDaoImpl",e);
		} finally {
			session.close();
		}
		return formForEdit;
	}

	/**
	 * This method is used to update Admission Form
	 * 
	 * @param List
	 *            <AdmissionProcess>
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 * 
	 */
	@Override
	public List<AdmissionProcess> updateAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmission) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> returnsSubmittedList = null;
		try {
			logger.info("updateAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmission) method in AdmissionDaoImpl");
			Utility util = new Utility();
			for (AdmissionProcess updateFormSubmission : formsubmissionList) {
				updateFormSubmission.setFolderObjectId(util.getBase64EncodedID("AdmissionDriveDAO"));
				updateFormSubmission.setAttachmentObjectId(util.getBase64EncodedID("AdmissionDriveDAO"));
			}
			session.update("update_candidate_info", formSubmission);
			session.insert("insert_upload_list", formsubmissionList);
			session.commit();
			returnsSubmittedList = session.selectList("getSubmittedFormList",formSubmission);
		} catch (Exception e) {
			logger.error("Exception in updateAdmissionForm(List<AdmissionProcess> formsubmissionList,AdmissionProcess formSubmission) :",e);
		e.printStackTrace();
		} finally {
			session.close();
		}
		return returnsSubmittedList;
	}

	/**
	 * This method is used to get Interview Schedule
	 * 
	 * @param String
	 * @return AdmissionProcess
	 * 
	 */
	@Override
	public AdmissionProcess getInterviewSchedule(
			AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		AdmissionProcess interviewSchedule = new AdmissionProcess();
		try {
			logger.info("getInterviewSchedule(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
//			/*session.update("deleteNotSubmittedForms", admissionProcess);*/ No Delete Naimisha
			session.commit();
			List<Form> formIdList = session.selectList("getFormIdList",admissionProcess);
			interviewSchedule.setFormList(formIdList);
			List<Resource> resourceList = session.selectList("getResourceList");
			interviewSchedule.setResourceList(resourceList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getInterviewSchedule(AdmissionProcess admissionProcess) method in AdmissionDaoImpl ",e);
		} finally {
			session.close();
		}
		return interviewSchedule;
	}

	/**
	 * This method is used for editing interview scheduled
	 * 
	 * @param strFormID
	 * @return InterviewSchedule
	 * 
	 */
	@Override
	public AdmissionProcess getInterviewScheduleForEdit(
			AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		AdmissionProcess interviewSchedule = null;
		try {
			logger.info("getInterviewScheduleForEdit(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
			interviewSchedule = session.selectOne("getInterviewScheduleForEdit", admissionProcess);
			List<Resource> resourceList = session.selectList("getResourceList");
			interviewSchedule.setResourceList(resourceList);
		} catch (Exception e) {
			logger.error("Exception In getInterviewScheduleForEdit(AdmissionProcess admissionProcess) method in AdmissionDaoImpl",e);
		} finally {
			session.close();
		}
		return interviewSchedule;
	}

	/**
	 * This method is user for updating interview scheduled
	 * 
	 * @param interviewscheduleToUpadate
	 * @return List<InterviewSchedule>
	 * 
	 */
	@Override
	public List<AdmissionProcess> updateInterviewSchedule(
			AdmissionProcess interviewscheduleToUpadate) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> interviewSchedule = null;
		try {
			logger.info("updateInterviewSchedule(AdmissionProcess interviewscheduleToUpadate) method in AdmissionDaoImpl.");
			List<Form> formList = interviewscheduleToUpadate.getFormList();
			if (formList != null && formList.size() != 0) {
				for (Form form : formList) {
					AdmissionProcess interviewScheduleToInsert = new AdmissionProcess();
					interviewScheduleToInsert.setFormId(form.getStrFormId());
					interviewScheduleToInsert.setCourseClass(interviewscheduleToUpadate.getCourseClass());
					interviewScheduleToInsert.setInterviewDateAndTime(interviewscheduleToUpadate.getInterviewDateAndTime());
					interviewScheduleToInsert.setExaminerName(interviewscheduleToUpadate.getExaminerName());
					interviewScheduleToInsert.setReviewerName(interviewscheduleToUpadate.getReviewerName());
					interviewScheduleToInsert.setRoomNo(interviewscheduleToUpadate.getRoomNo());
					interviewScheduleToInsert.setVenue(interviewscheduleToUpadate.getVenue());
					interviewScheduleToInsert.setFormName(interviewscheduleToUpadate.getFormName());
					interviewScheduleToInsert.setAdmissionYear(interviewscheduleToUpadate.getAdmissionYear());
					int intInterviewStatus = session.insert("insert_interview_Details",interviewScheduleToInsert);
					session.commit();
					interviewSchedule = session.selectList("getInterviewDetails", interviewscheduleToUpadate);
					logger.info("Acknowledgment for INTERVIEW SCHEDULED insertion by insertInterviewScheduleDao() of AdmissionDriveDAO :"+ intInterviewStatus);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in updateInterviewSchedule(AdmissionProcess interviewscheduleToUpadate) method in AdmissionDaoImpl.",e);
		} finally {
			session.close();
		}
		return interviewSchedule;
	}

	/**
	 * This method is used for getting Interview Result For Edit
	 * 
	 * @param strFormID
	 * @return InterviewResult
	 */

	@Override
	public AdmissionProcess getInterviewResultForEdit(
			AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		AdmissionProcess interviewResult = null;
		try {
			logger.info("getInterviewResultForEdit(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
			//System.out.println("Drive Name==========="+admissionProcess.getFormName());
			//System.out.println("Form Id=========="+admissionProcess.getFormId());
			//System.out.println("Academic Year============"+admissionProcess.getAdmissionYear());
			//System.out.println("Class=========="+admissionProcess.getCourseClass());
			List<Marks> marksList = session.selectList("selectMarksListForEdit", admissionProcess);
			//System.out.println("marksList========="+marksList.size());
			interviewResult = session.selectOne("selectDecisionForEdit",admissionProcess);
			interviewResult.setMarksList(marksList);
		} catch (Exception e) {
			logger.error("Exception in getInterviewResultForEdit(AdmissionProcess admissionProcess) method in AdmissionDaoImpl",e);
		} finally {
			session.close();
		}
		return interviewResult;
	}

	/**
	 * This method is used for View MeritList
	 * 
	 * @param strCourseClass
	 * @return List<InterviewResult>
	 */

	@Override
	public List<AdmissionProcess> getInterviewResultForViewMeritList(AdmissionProcess interviewResult) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> interviewResultFromDB = null;
		try {
			logger.info("getInterviewResultForViewMeritList(AdmissionProcess interviewResult) method in AdmissionServiceImpl");
			session.update("ScheduledReviwedNotselected_updateToInactive",interviewResult);
			int statusOfUpdate = session.update("updateStatusToSetFee",interviewResult);
			String admissionDriveName = interviewResult.getFormName();
			//String admissionDriveState = interviewResult.getFormStatus();
			//
			String admissionDriveState = session.selectOne("selectAdmissionDriveCompletionStatus", admissionDriveName);
			//System.out.println("admissionDriveState==="+admissionDriveState);
			if(admissionDriveState != null){
				if(admissionDriveState.equalsIgnoreCase("INTERVIEWSCHEDULED")){
					int statusUpdate = session.update("updateAdmissionDriveCompletionStatusToInterviewResult", admissionDriveName);
				}else if(admissionDriveState.equalsIgnoreCase("NOTSTARTED")){
					int statusUpdate = session.update("updateAdmissionDriveCompletionStatusToInterviewResult", admissionDriveName);
				}
				
			}
			
			session.commit();
			logger.info("ack from statusOfUpdate in DAO(getInterviewResultForViewMeritList)"+statusOfUpdate);
			interviewResultFromDB = session.selectList("getInterviewResultForViewMeritList", interviewResult);
		} catch (Exception e) {
			logger.error("Exception in getInterviewResultForViewMeritList() in AdmissionController");e.printStackTrace();
		} finally {
			session.close();
		}
		return interviewResultFromDB;
	}

	/**
	 * This method is used to get Required MeritList
	 * 
	 * @param String
	 * @return List<AdmissionProcess>
	 * 
	 */

	@Override
	public List<AdmissionProcess> getRequiredMeritList(
			AdmissionProcess admissionProcess) {
		List<AdmissionProcess> interviewResultForMerit = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("getRequiredMeritList(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
			interviewResultForMerit = session.selectList("getRequiredAdmittedCandidates", admissionProcess);
			Boolean dateStatus =  session.selectOne("getEndDateStatusForAdmissionDrive", admissionProcess);
			if(interviewResultForMerit!=null && interviewResultForMerit.size()!=0 && dateStatus!=null){
				interviewResultForMerit.get(0).setDateStatus(dateStatus);
			}
		} catch (Exception e) {
			logger.error("Exception in getRequiredMeritList(AdmissionProcess admissionProcess) method in AdmissionDaoImpl",e);
		} finally {
			session.close();
		}
		return interviewResultForMerit;
	}

	/**
	 * This method is used to insert Selected Candidate
	 * 
	 * @param interviewResultToUpdate
	 * @return List<InterviewResult>
	 */
	@Override
	public List<AdmissionProcess> insertSelectedCandidate(AdmissionProcess interviewResultToUpdate) {
		List<AdmissionProcess> interviewResultForMerit = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("insertSelectedCandidate(AdmissionProcess interviewResultToUpdate) method in AdmissionDaoImpl");
			List<Form> formList = interviewResultToUpdate.getFormList();
			if (formList != null && formList.size() != 0) {
				for (Form form : formList) {
					AdmissionProcess interviewResultToInsert = new AdmissionProcess();
					interviewResultToInsert.setFormId(form.getStrFormId());
					interviewResultToInsert.setLastFeesSubmissionDate(interviewResultToUpdate.getLastFeesSubmissionDate());
					interviewResultToInsert.setAdmissionYear(interviewResultToUpdate.getAdmissionYear());
					interviewResultToInsert.setFormName(interviewResultToUpdate.getFormName());
					interviewResultToInsert.setCourseClass(interviewResultToUpdate.getCourseClass());
					interviewResultToInsert.setUpdatedBy(interviewResultToUpdate.getUpdatedBy());
					session.insert("insert_meritList_selected_list",interviewResultToInsert);
					session.commit();
				}
			}
			interviewResultForMerit = session.selectList("getInterviewResultForViewMeritList",interviewResultToUpdate);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in insertSelectedCandidate(AdmissionProcess interviewResultToUpdate) method in AdmissionDaoImpl: ",e);
		} finally {
			session.close();
		}
		return interviewResultForMerit;
	}

	/**
	 * This method is used to geRequiredCandidates
	 * 
	 * @param String
	 * @return List<AdmissionProcess>
	 * 
	 */
	@Override
	public List<AdmissionProcess> getRequiredCandidates(
			AdmissionProcess admissionProcess) {
		List<AdmissionProcess> interviewResultForMerit = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("getRequiredCandidates(AdmissionProcess admissionProcess) method in AdmissoinDaoImpl");
			interviewResultForMerit = session.selectList("getRequiredAdmittedCandidates", admissionProcess);
			Boolean dateStatus =  session.selectOne("getEndDateStatusForAdmissionDrive", admissionProcess);
			String admissionDriveName = admissionProcess.getFormName();
			String admissionDriveState = session.selectOne("selectAdmissionDriveCompletionStatus", admissionDriveName);
			//System.out.println("admissionDriveState==="+admissionDriveState);
			if(admissionDriveState.equalsIgnoreCase("INTERVIEWRESULT")){
				int statusUpdate = session.update("updateAdmissionDriveCompletionStatusToViewMeritList", admissionDriveName);
			}
			
			if(interviewResultForMerit!=null && interviewResultForMerit.size()!=0 && dateStatus!=null){
			interviewResultForMerit.get(0).setDateStatus(dateStatus);
			}
		} catch (Exception e) {
			logger.error("Exception in getRequiredCandidates(AdmissionProcess admissionProcess) method in AdmissoinDaoImpl",e);
		} finally {
			session.close();
		}
		return interviewResultForMerit;
	}

	/**
	 * This method is used to get Payment List
	 * 
	 * @param InterviewResult
	 * @return InterviewResult
	 */

	@Override
	public AdmissionProcess getPaymentList(AdmissionProcess interviewResultToDAO) {
		AdmissionProcess interviewResultFormDAO = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("getPaymentList(AdmissionProcess interviewResultToDAO) method in AdmissionDaoImpl");
			interviewResultFormDAO = session.selectOne("getPaymentList",interviewResultToDAO);
			//System.out.println("interviewResultFormDAO========="+interviewResultFormDAO.getAdmissionYear());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getPaymentList(AdmissionProcess interviewResultToDAO) method in AdmissionDaoImpl",e);
		} finally {
			session.close();
		}
	return interviewResultFormDAO;
	}

//	/**
//	 * This method is used to update Candidate Payment Status
//	 * 
//	 * @param AdmissionProcess
//	 * @return List<AdmissionProcess>
//	 * 
//	 */
//	@Override
//	public List<AdmissionProcess> updateCandidatePaymentStatus(AdmissionProcess interviewResult) {
//		List<AdmissionProcess> paymentDoneListFromDB = null;
//		SqlSession session =sqlSessionFactory.openSession();
//		try {
//			logger.info("updateCandidatePaymentStatus(AdmissionProcess interviewResult) method in AdmissionDaoImpl");
//			interviewResult.setUpdatedBy();
//			session.insert("updateCandidatePaymentStatus", interviewResult);
//			paymentDoneListFromDB = session.selectList("listOfPaymentDone",interviewResult);
//			session.commit();
//		} catch (Exception e) {
//			logger.error("Exception in updateCandidatePaymentStatus(AdmissionProcess interviewResult) method in AdmissionDaoImpl");
//		} finally {
//			session.close();
//		}
//		return paymentDoneListFromDB;
//	}

	/**
	 * This method is used to get Admissions OnProcess List
	 * 
	 * @return List<AdmissionForm>
	 * 
	 */

	@Override
	public List<AdmissionForm> getAdmissionsOnProcessList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionForm> admissionFormList = null;
		try {
			logger.info("getAdmissionsOnProcessList() method in AdmissionDaoImpl");
			admissionFormList = session.selectList("getAdmissionOnProcessList");
		} catch (Exception e) {
			logger.info("Exception in getAdmissionsOnProcessList() method in AdmissionDaoImpl");
		} finally {
			session.close();
		}
		return admissionFormList;
	}
	
	@Override
	public List<AdmissionForm> getAdmissionsOnProcessSearch(Map<String, Object> parameters) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionForm> admissionFormList = null;
		try {
			logger.info("getAdmissionsOnProcessSearch() method in AdmissionDaoImpl");
			admissionFormList = session.selectList("getAdmissionOnProcessList",parameters);
		} catch (Exception e) {
			logger.info("Exception in getAdmissionsOnProcessSearch() method in AdmissionDaoImpl");
		} finally {
			session.close();
		}
		return admissionFormList;
	}

	/**
	 * This method is used to get Scheduled FormList
	 * 
	 * @param String
	 * @return List<FormSubmission>
	 * 
	 */
	@Override
	public List<AdmissionProcess> getScheduledFormList(
			AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> returnsGeneratedFormList = null;
		try {
			logger.info("getScheduledFormList(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
			/* Not Scheduled Form will be updated to inactive */
			session.update("ScheduledForm_updateToInactive", admissionProcess);
			session.commit();
			returnsGeneratedFormList = session.selectList("getScheduledFormList", admissionProcess);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getScheduledFormList(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
		} finally {
			session.close();
		}
		return returnsGeneratedFormList;
	}

	@Override
	public List<AdmissionProcess> goBackFromReview(AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> returnsGeneratedFormList = null;
		try {
			logger.info("goBackFromReview(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
			returnsGeneratedFormList = session.selectList("goBackFromReview",admissionProcess);
		} catch (Exception e) {
			logger.error("Exception in goBackFromReview(AdmissionProcess admissionProcess) method in AdmissionDaoImpl",e);
		} finally {
			session.close();
		}
		return returnsGeneratedFormList;
	}

	/**
	 * This method is used to get Full Name
	 * 
	 * @param String
	 * @return String
	 * 
	 */
	@Override
	public String getFullName(AdmissionProcess admissionProcess) {
		AdmissionProcess formSubmission = null;
		SqlSession session =sqlSessionFactory.openSession();
		formSubmission = session.selectOne("getFullName", admissionProcess);
		String strFullName = null;
		String strFirstName = formSubmission.getCandidateFirstName();
		String strLastName = formSubmission.getCandidateLastName();
		//System.out.println("below"+strLastName);
		String strMiddleName = " ";
		try {
			if (formSubmission.getCandidateMiddleName() != null) {
				strMiddleName = formSubmission.getCandidateMiddleName();
				strFullName = strFirstName + " " + strMiddleName + " "+ strLastName;
			} else {
				strFullName = strFirstName + strMiddleName + strLastName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return strFullName;
	}

	/**
	 * This method is used to get admit Students
	 * 
	 * @param String
	 */

	@Override
	public void admitStudents(AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info(" admitStudents(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
			session.update("closeAdmission", admissionProcess);
			session.commit();
		} catch (Exception e) {
			logger.error("Exception in  admitStudents(AdmissionProcess admissionProcess) method in AdmissionDaoImpl",e);
		} finally {
			session.close();
		}
	}

	/**
	 * This method is used to get Payment List To Display Back
	 * 
	 * @param strCourseClass
	 * @return
	 * 
	 */
	@Override
	public List<AdmissionProcess> getPaymentListToDisplayBack(AdmissionProcess admissionProcess) {
		List<AdmissionProcess> paymentDoneListFromDB = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("getPaymentListToDisplayBack(AdmissionProcess admissionProcess) method in ADmissionDaoImpl");
			paymentDoneListFromDB = session.selectList("listOfPaymentDone",admissionProcess);
			session.commit();
		} catch (Exception e) {
			logger.error("Exception in getPaymentListToDisplayBack(AdmissionProcess admissionProcess) method in ADmissionDaoImpl",e);
		} finally {
			session.close();
		}
		return paymentDoneListFromDB;
	}

	/**
	 * This method is used to get Admission Form List
	 * 
	 * @param String
	 * @return List<AdmissionProcess>
	 * 
	 */
	@Override
	public List<AdmissionProcess> getAdmissionFormList(
			AdmissionProcess formSubmission) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> returnsSubmittedList = null;
		try {
			logger.info("getAdmissionFormList(AdmissionProcess formSubmission) method in AdmissionController: ");
			returnsSubmittedList = session.selectList("getSubmittedFormList",formSubmission);
		} catch (Exception e) {
			logger.error("ERROR getAdmissionFormList(AdmissionProcess formSubmission) method in AdmissionController :",e);
		} finally {
			session.close();
		}
		return returnsSubmittedList;
	}

	/**
	 * This method is used to get Search List
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 * 
	 */
	@Override
	public List<AdmissionProcess> getSearchList(AdmissionProcess searchingParameter) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> searchList = null;
		try {
			logger.info("getSearchList(AdmissionProcess searchingParameter) method in AdmissionDaoImpl");
			searchList = session.selectList("getSearch", searchingParameter);
		} catch (Exception e) {
			logger.info("Exception in getSearchList(AdmissionProcess searchingParameter) method in AdmissionDaoImpl");
		} finally {
			session.close();
		}
		return searchList;
	}

	@Override
	public List<AdmissionProcess> getHistorySearchList(
			AdmissionProcess searchingParameter) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> searchList = null;
		try {
			logger.info("getHistorySearchList(AdmissionProcess searchingParameter) method in AdmissionDaoImpl");
			searchList = session.selectList("getHistorySearch",searchingParameter);
		} catch (Exception e) {
			logger.error("Exception in getHistorySearchList(AdmissionProcess searchingParameter) method in AdmissionDaoImpl :",e);
		} finally {
			session.close();
		}
		return searchList;
	}

	@Override
	public List<AdmissionForm> admissionDriveSearch(AdmissionForm searchingParameter) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionForm> admissionDriveSearchList = null;
		logger.info(" admissionDriveSearch(AdmissionForm searchingParameter) of AdmissionDAOImpl searchingParameter: "+ searchingParameter.getStatus());
		try {
			admissionDriveSearchList = session.selectList("admissionDriveSearch", searchingParameter);
		} catch (Exception e) {
			logger.error("Exception in admissionDriveSearch(AdmissionForm searchingParameter) method in AdmissionDaoImpl: ",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return admissionDriveSearchList;
	}

	/**
	 * This method is used to get Previous Admission List
	 * 
	 * @param AdmissionProcess
	 * @return List<AdmissionProcess>
	 * 
	 */
	@Override
	public List<AdmissionProcess> getPreviousAdmissionList(AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> historySearchList = null;
		try {
			logger.info("getPreviousAdmissionList(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
			if (admissionProcess.getFormStatus().equals("SUBMITTED")) {
				historySearchList = session.selectList("submittedHistorySearchList", admissionProcess);
			} else if (admissionProcess.getFormStatus().equals("SCHEDULED")) {
				historySearchList = session.selectList("scheduledHistorySearchList", admissionProcess);
			} else if (admissionProcess.getFormStatus().equals("SELECTED")) {
				historySearchList = session.selectList("selectedHistorySearchList", admissionProcess);
			} else if (admissionProcess.getFormStatus().equals("SETFEE")) {
				historySearchList = session.selectList("setfeeHistorySearchList", admissionProcess);
			} else if (admissionProcess.getFormStatus().equals("PAIDWAIVED")) {
				historySearchList = session.selectList("paidAndWaivedHistorySearchList", admissionProcess);
			}
		} catch (Exception e) {
			logger.error("Exception in getPreviousAdmissionList(AdmissionProcess admissionProcess): ",e);
		}
		return historySearchList;
	}

	/**
	 * This method is used to get Course Class List
	 * 
	 * @param String
	 * @return AdmissionForm
	 * 
	 */
	@Override
	public AdmissionForm getAdmissionForm(AdmissionForm admissionFormFromService) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("getAdmissionForm(AdmissionForm admissionFormFromService) method in AdmissionDao: ");
			//System.out.println("Course Code for querey ================"+admissionFormFromService.getCourseCode());
			int admissionOpeningStatus = session.selectOne("selectAdmissionOpeningStatus", admissionFormFromService);
			if (admissionOpeningStatus == 0) {
				Integer noOfOpeningForCourse = session.selectOne("selectNoOfOpeningForCourse", admissionFormFromService);
				Integer noOfAdmittedForCourse = session.selectOne("selectNoOfAdmittedForCourse",admissionFormFromService);
				if (noOfOpeningForCourse != null && noOfAdmittedForCourse != null) {
					if (noOfOpeningForCourse <= noOfAdmittedForCourse) {
						admissionFormFromService.setStatus("FULL");
					}
				}
				if (admissionFormFromService!=null && admissionFormFromService.getStatus() == null) {
					admissionFormFromService = session.selectOne("getAdmissionForm",admissionFormFromService.getCourseCode());
					
					AdmissionForm admissionForm = session.selectOne("selectCourseName", admissionFormFromService);
					if (admissionForm != null) {
						admissionFormFromService.setCourseName(admissionForm.getCourseName());
						admissionFormFromService.setCourseType(admissionForm.getCourseType());
						//admissionFormFromService.setStreamName(admissionForm.getStreamName());
						/*if (admissionFormFromService != null) {
							AdmissionForm amount = session.selectOne("selectFromFees", admissionFormFromService);
							if (amount != null) {
								admissionFormFromService.setFormFees(amount.getFormFees());
							}
							amount = session.selectOne("selectCourseFees",admissionFormFromService);   *******closed by naimisha*******
							if (amount != null) {
								admissionFormFromService.setCourseFees(amount.getCourseFees());
							}
							admissionFormFromService.setAdmissionDriveStartDate(admissionForm.getAdmissionDriveStartDate());
							admissionFormFromService.setAdmissionDriveExpectedEndDate(admissionForm.getAdmissionDriveExpectedEndDate());
						}*/
						if (admissionFormFromService != null) {
							admissionFormFromService.setAdmissionDriveStartDate(admissionForm.getAdmissionDriveStartDate());
							admissionFormFromService.setAdmissionDriveExpectedEndDate(admissionForm.getAdmissionDriveExpectedEndDate());
						}
						
						session.selectOne("generateAdmissionDriveCode",admissionFormFromService);
					
						
						List<CustomizedAdmissionProcess> customizedAdmissionProcessList =  session.selectList("selectCustomizedAdmissionProcess");
						admissionFormFromService.setCustomizedAdmissionProcessList(customizedAdmissionProcessList);
					}
				}
			} else {
				admissionFormFromService.setStatus("ONGOING");
			}
		} catch (Exception e) {
			logger.error("Exception in getAdmissionForm(AdmissionForm admissionFormFromService) method in AdmissionDao",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return admissionFormFromService;
	}

	@Override
	public List<AdmissionForm> getAdmittedDriveList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionForm> admissionFormListFromDb = null;
		try {
			logger.info("getAdmittedDriveList() method in AdmissionDaoImpl");
			admissionFormListFromDb = session.selectList("getAdmittedDriveList");
		} catch (Exception e) {
			logger.error("From getCourseClassList() of AdmissionDriveDAO-FEES LIST :",e);
		} finally {
			session.close();
		}
		return admissionFormListFromDb;

	}

	@Override
	public List<AdmissionProcess> admittedDriveListDetails(
			AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> admissionFormListdetailsFromDb = null;
		try {
			logger.info("admittedDriveListDetails(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
			admissionFormListdetailsFromDb = session.selectList("admittedDriveStudentListDetails", admissionProcess);
		} catch (Exception e) {
			logger.error(" Exception in From admittedDriveListDetails() of AdmissionDriveDAO :",e);
		} finally {
			session.close();
		}
		return admissionFormListdetailsFromDb;
	}

	/**
	 * modified by anup.roy
	 * 02.08.2017
	 * **/
	
	@Override
	public AcademicYear getYearClassList() {
		SqlSession session =sqlSessionFactory.openSession();
		AcademicYear currentAcademicYear = null;
		try {
			currentAcademicYear = session.selectOne("selectCurrentAcademicYear");
			if (currentAcademicYear != null) {
				//query modification for select list for distinct classes 02.08.2017
				List<Standard> classList = session.selectList("selectStandardListForDrive");
				if (classList != null && classList.size() != 0) {
					currentAcademicYear.setClassList(classList);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getYearClassList() method in AdmissionDAOImpl",e);
		} finally {
			session.close();
		}
		return currentAcademicYear;
	}
	
	/**
	 * @author anup.roy
	 * 02.08.2017
	 * */

	public AcademicYear submitSaleFormDetails(AdmissionForm admissionForm) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			admissionForm.setNoOfOpenings(0);
			admissionForm.setTotalPrice(admissionForm.getNoOfFormSold()* admissionForm.getFormFees());
			session.update("updateFormDetails", admissionForm);
			Utility util = new Utility();
			admissionForm.setPaymentMode("CASH");
			admissionForm.setAdmissionFormObjectId(util.getBase64EncodedID("AdmissionDriveDAO"));
			admissionForm.setStrMessage("FORMFEES("+admissionForm.getNoOfFormSold()+")");
			session.insert("insertIntoTransactionalWorkingAreaForAdmissionFormFees", admissionForm);
			
			/* updating FORM FEES ledger */
			TransactionDetails transactionDetails = new TransactionDetails();
			transactionDetails.setLedger("FORM FEES");
			transactionDetails.setAmount(admissionForm.getTotalPrice());
			session.update("updateLedgerBalanceCredit",transactionDetails);
			/* updating CASH ledger */
			TransactionDetails transactionDetails1 = new TransactionDetails();
			transactionDetails.setLedger("CASH");
			transactionDetails.setAmount(admissionForm.getTotalPrice());
			session.update("updateLedgerBalanceCredit",transactionDetails1);
			
			session.commit();
		} catch (Exception e) {
			logger.error("Exception in submitSaleFormDetails(AdmissionForm admissionForm) method in AdmissionDAOImpl: ",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return getYearClassList();
	}
	
	@Override
	public AdmissionForm getStreamClassYearCourseDetails(AdmissionProcess formSubmission) {
		AdmissionForm admissionForm = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			admissionForm = session.selectOne("getStreamClassYearCourseDetails", formSubmission);
		} catch (Exception e) {
			logger.error("Exception in getStreamClassYearCourseDetails(AdmissionProcess formSubmission) method in AdmissionDAOImpl: ",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return admissionForm;
	}
	
	@Override
	public List<AdmissionProcess> admittedDriveListDetailsSearch(AdmissionProcess searchingParameter) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> admissionFormListdetailsFromDb = null;
		try{
			logger.info("admittedDriveListDetailsSearch(AdmissionProcess admissionProcess) method in AdmissionDaoImpl");
			admissionFormListdetailsFromDb = session.selectList("admittedDriveStudentListDetails",searchingParameter);			
		}catch(Exception e){
			logger.error(" Exception in From admittedDriveListDetailsSearch() of AdmissionDriveDAO :",e);
		}finally{
			session.close(); 
		}
		return admissionFormListdetailsFromDb;
	}

	@Override
	public List<LoggingAspect> getAdmissionDriveLogDetails() {
		SqlSession session =sqlSessionFactory.openSession();
		List<LoggingAspect> logDetails = null;
		try{
			logger.info("getAdmissionDriveLogDetails( ) method in AdmissionDaoImpl");
			logDetails = session.selectList("selectAdmissionDriveLogDetails");			
		}catch(Exception e){
			logger.error(" Exception in From admittedDriveListDetailsSearch() of AdmissionDriveDAO :",e);
		}finally{
			session.close(); 
		}
		return logDetails;
	}

	@Override
	public List<LoggingAspect> getSaleFormLogDetails() {
		SqlSession session =sqlSessionFactory.openSession();
		List<LoggingAspect> logDetails = null;
		try{
			logger.info("getSaleFormLogDetails( ) method in AdmissionDaoImpl");
			logDetails = session.selectList("selectSaleFormLogDetails");			
		}catch(Exception e){
			logger.error(" Exception in From getSaleFormLogDetails() of AdmissionDriveDAO :",e);
		}finally{
			session.close(); 
		}
		return logDetails;
	}

	@Override
	public List<LoggingAspect> getAdmissionScheduleInterviewLogDetails(String admissonDriveName) {
		SqlSession session =sqlSessionFactory.openSession();
		List<LoggingAspect> logDetails = null;
		try{
			logger.info("getAdmissionScheduleInterviewLogDetails( ) method in AdmissionDaoImpl");
			logDetails = session.selectList("selectAdmissionScheduleInterviewLogDetails",admissonDriveName);			
		}catch(Exception e){
			logger.error(" Exception in From getAdmissionScheduleInterviewLogDetails() of AdmissionDriveDAO :",e);
		}finally{
			session.close(); 
		}
		return logDetails;
	}

	@Override
	public List<LoggingAspect> getAdmissionInterviewResultLogDetails(String admissonDriveName) {
		SqlSession session =sqlSessionFactory.openSession();
		List<LoggingAspect> logDetails = null;
		try{
			logger.info("getAdmissionInterviewResultLogDetails( ) method in AdmissionDaoImpl");
			logDetails = session.selectList("selectAdmissionInterviewResultLogDetails",admissonDriveName);			
		}catch(Exception e){
			logger.error(" Exception in From getAdmissionInterviewResultLogDetails() of AdmissionDriveDAO :",e);
		}finally{
			session.close(); 
		}
		return logDetails;
	}

	@Override
	public List<LoggingAspect> getAdmissionPaymentDateSetupLogDetails(String admissonDriveName) {
		SqlSession session =sqlSessionFactory.openSession();
		List<LoggingAspect> logDetails = null;
		try{
			logger.info("getAdmissionPaymentDateSetupLogDetails( ) method in AdmissionDaoImpl");
			logDetails = session.selectList("selectAdmissionPaymentDateSetupLogDetails",admissonDriveName);			
		}catch(Exception e){
			logger.error(" Exception in From getAdmissionPaymentDateSetupLogDetails() of AdmissionDriveDAO :",e);
		}finally{
			session.close(); 
		}
		return logDetails;
	}

	@Override
	public List<AdmissionProcess> getSubmittedFormDetails(
			AdmissionProcess admissionProcess) {
		// TODO Auto-generated method stub
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> submittedFormDetailsList = null;
		try{
			 submittedFormDetailsList = session.selectList("getSubmittedFormList",admissionProcess);
			 
			 
		}catch(Exception e){
			e.printStackTrace();
			logger.error(" Exception in From getAdmissionPaymentDateSetupLogDetails() of AdmissionDriveDAO :",e);
		}finally{
			session.close(); 
		}
		return submittedFormDetailsList;
	}

	@Override
	public List<AdmissionProcess> getinterviewScheduledList(
			AdmissionProcess admissionProcess) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> interviewScheduleList = null;
		try {
			logger.info("getinterviewScheduledList(AdmissionProcess interviewschedule) method in AdmissionDaoImpl");
			interviewScheduleList = session.selectList("getInterviewDetails", admissionProcess);
			String admissionDriveName = admissionProcess.getFormName();
			//String admissionDriveState = admissionProcess.getFormStatus();

			String admissionDriveState = session.selectOne("selectAdmissionDriveCompletionStatus", admissionDriveName);
			//System.out.println("admissionDriveState==="+admissionDriveState);
			if(admissionDriveState.equalsIgnoreCase("NOTSTARTED")){
				
				session.update("updateAdmissionDriveCompletionStatusToFormSubmitted", admissionDriveName);
			}
			
				
		
		} catch (Exception e) {
			logger.info("Exception in getinterviewScheduledList(AdmissionProcess interviewschedule) method in AdmissionDaoImpl",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return interviewScheduleList;
	}

	public List<AdmissionProcess> getSelectedReviewdNonselectedStudentList(
			AdmissionProcess makeInterviewResultObject) {
		// TODO Auto-generated method stub
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> selectedReviewNonselectedStudentList = null;
		try {
			logger.info("getinterviewScheduledList(AdmissionProcess interviewschedule) method in AdmissionDaoImpl");
			selectedReviewNonselectedStudentList = session.selectList("selectedCandidate", makeInterviewResultObject);
			//interviewResultListFromDB = (session.selectList("selectedCandidate", interviewResultToDB));
			String admissionDriveName = makeInterviewResultObject.getFormName();
			//String admissionDriveState = makeInterviewResultObject.getFormStatus();

			String admissionDriveState = session.selectOne("selectAdmissionDriveCompletionStatus", admissionDriveName);
			//System.out.println("admissionDriveState==="+admissionDriveState);
			if(admissionDriveState.equalsIgnoreCase("FORMSUBMITTED")){
				session.update("updateAdmissionDriveCompletionStatusToInterviewScheduled", admissionDriveName);	
			}
			
		
		} catch (Exception e) {
			logger.info("Exception in selectedReviewNonselectedStudentList(AdmissionProcess interviewschedule) method in AdmissionDaoImpl",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return selectedReviewNonselectedStudentList;
	}

	@Override
	public List<AdmissionProcess> getResultForViewMeritList(
			AdmissionProcess interviewResultToDB) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> studentMeritList = null;
		try {
			logger.info("getinterviewScheduledList(AdmissionProcess interviewschedule) method in AdmissionDaoImpl");
			studentMeritList = session.selectList("getInterviewResultForViewMeritList", interviewResultToDB);
			//interviewResultListFromDB = (session.selectList("selectedCandidate", interviewResultToDB));
				
		
		} catch (Exception e) {
			logger.info("Exception in selectedReviewNonselectedStudentList(AdmissionProcess interviewschedule) method in AdmissionDaoImpl",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return studentMeritList;
	}

	@Override
	public String getAdmissionDriveState(String strDriveName) {
		String  admissionDriveState = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			admissionDriveState = session.selectOne("selectAdmissionDriveCompletionStatus", strDriveName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return admissionDriveState;
	}

	@Override
	public String getstatusOfAdmissionDrive(AdmissionProcess admissionProcess) {
		// TODO Auto-generated method stub
		String  statusOfAdmission = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			statusOfAdmission = session.selectOne("selectStateOfAdmission", admissionProcess);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return statusOfAdmission;
	}

	@Override
	public List<AdmissionProcess> getAllStudentForMeritList(
			AdmissionProcess interviewResult) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionProcess> allStudentMeritList = null;
		try {
			logger.info("getinterviewScheduledList(AdmissionProcess interviewschedule) method in AdmissionDaoImpl");
			allStudentMeritList = session.selectList("getAllStudentForViewMeritList", interviewResult);
			//interviewResultListFromDB = (session.selectList("selectedCandidate", interviewResultToDB));
				
		
		} catch (Exception e) {
			logger.info("Exception in selectedReviewNonselectedStudentList(AdmissionProcess interviewschedule) method in AdmissionDaoImpl",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return allStudentMeritList;
	}

	@Override
	public String updadateAdmissonDriveStatus(String driveName) {
		String  statusOfAdmission = null;
		SqlSession session =sqlSessionFactory.openSession();
		int updateStatus = 0;
		try {
			//System.out.println("within statusOfAdmission");
			updateStatus = session.update("updateADmissionDriveStatus", driveName);
			if(updateStatus>0){
				statusOfAdmission = "Successfully Updated";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			session.close();
		}
		return statusOfAdmission;
	}

	@Override
	public List<Data> setProgrammeDetailsListForPortal() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Data> programmeDetailsListForPortal = null;
		try{
			programmeDetailsListForPortal = session.selectList("selectProgrammeDetailsListForPortal");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return programmeDetailsListForPortal;
	}
	
	@Override
	public String saveCandidateDetailsFromPortal(AdmissionFormForPortalStudents candidate, String user) {
		SqlSession session = sqlSessionFactory.openSession();
		String insertStatus = "fail";
		int status = 0;
		try{
			Utility util = new Utility();
			List<Data> dataList = candidate.getData();
			List<Data> newDataList = new ArrayList<Data>();
	    	for(Data dataObj : dataList){
	    		Data dataObject = new Data();
	    		dataObject.setUpdatedBy(user);
	    		dataObject.setObjectId(util.getBase64EncodedID("AdmissionDriveDao"));
	    		dataObject.setAdmissionFormId(dataObj.getAdmissionFormId());
	    		dataObject.setDriveName(dataObj.getDriveName());
	    		dataObject.setPaymentDetails(dataObj.getPaymentDetails());
	    		dataObject.setPortalCandidateDetails(dataObj.getPortalCandidateDetails());
	    		dataObject.setProfessionalDetails(dataObj.getProfessionalDetails());
	    		dataObject.setQualificationDetails(dataObj.getQualificationDetails());
	    		newDataList.add(dataObject);
	    	}
	    	candidate.setData(newDataList);
			status = session.insert("insertDetailsOfAllCandidatesFromPortal", candidate);
			if(status !=0){
				insertStatus = "success";
			}
			/* new added by sourav.bhadra
			 * to insert sum of admission form fees
			 * into transactional_working_area table*/
			List<AdmissionForm> incomeFromAdmissionForm = session.selectList("selectTotalFormFeesAgainstToday");
			/*for(AdmissionForm af: incomeFromAdmissionForm){
				af.setPaymentMode("BANK");
				af.setAdmissionFormObjectId(util.getBase64EncodedID("AdmissionDriveDao"));
				af.setUpdatedBy(user);
			}
			session.insert("insertTotalAdmissionFormFeesIntoTransactionalWorkingAreaAgainstCourse", incomeFromAdmissionForm);*/
			Double totalBalance = 0.0;
			for(AdmissionForm af: incomeFromAdmissionForm){
				totalBalance += af.getTotalPrice();
			}
			/* updating FORM FEES ledger */
			TransactionDetails transactionDetails = new TransactionDetails();
			transactionDetails.setLedger("FORM FEES");
			transactionDetails.setAmount(totalBalance);
			session.update("updateLedgerBalanceCredit",transactionDetails);
			/* updating CASH ledger */
			TransactionDetails transactionDetails1 = new TransactionDetails();
			transactionDetails.setLedger("CASH");
			transactionDetails.setAmount(totalBalance);
			session.update("updateLedgerBalanceCredit",transactionDetails1);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return insertStatus;
		
	}

	@Override
	public String saveScrutinizedCandidatesFromPortal(AdmissionFormForPortalStudents candidate , String user) {
		SqlSession session = sqlSessionFactory.openSession();
		String udpateStatus = "fail";
		int status = 0;
		try{
			Utility util = new Utility();
			List<Data> dataList = candidate.getData();
			List<Data> newDataList = new ArrayList<Data>();
	    	for(Data dataObj : dataList ){
	    		Data dataObject = new Data();
	    		dataObject.setUpdatedBy(user);
	    		dataObject.setObjectId(util.getBase64EncodedID("AdmissionDriveDao"));
	    		dataObject.setAdmissionFormId(dataObj.getAdmissionFormId());
	    		dataObject.setDriveName(dataObj.getDriveName());
	    		newDataList.add(dataObject);
	    	}
	    	candidate.setData(newDataList);
			status = session.update("saveScrutinizedCandidatesFromPortal", candidate);
			if(status !=0){
				udpateStatus = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return udpateStatus;
		
	}
	
	@Override
	public String saveSelectedCandidatesFromPortal(AdmissionFormForPortalStudents candidate , String user) {
		SqlSession session = sqlSessionFactory.openSession();
		String udpateStatus = "fail";
		int status = 0;
		try{
			Utility util = new Utility();
			List<Data> dataList = candidate.getData();
			List<Data> newDataList = new ArrayList<Data>();
	    	for(Data dataObj : dataList ){
	    		Data dataObject = new Data();
	    		dataObject.setUpdatedBy(user);
	    		dataObject.setObjectId(util.getBase64EncodedID("AdmissionDriveDao"));
	    		dataObject.setAdmissionFormId(dataObj.getAdmissionFormId());
	    		dataObject.setDriveName(dataObj.getDriveName());
	    		dataObject.setMarks(dataObj.getMarks());
	    		dataObject.setSelectionStatus(dataObj.getSelectionStatus());
	    		newDataList.add(dataObject);
	    	}
	    	candidate.setData(newDataList);
			status = session.update("saveSelectedCandidatesFromPortal", candidate);
			if(status !=0){
				udpateStatus = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return udpateStatus;
		
	}

	@Override
	public java.util.List<LocationDetailsForPortal> setLocationDetailsForPortal() {
		SqlSession session =sqlSessionFactory.openSession();
		List<LocationDetailsForPortal> locationDetailsList = null;
		try {
			
			locationDetailsList = session.selectList("selectLocationDetailsAndInterviewPannel");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception IN getAdmissionDriveList(String strDrive) in AdmissionDAOImpl: ",e);
		} finally {
			session.close();
		}
		return locationDetailsList;
	}
	
	/**
	 * naimisha.ghosh
	 * changes taken on 24062017*/

	@Override
	public String saveAdmittedCandidatesFromPortal(AdmissionFormForPortalStudents candidate, String user) {
		SqlSession session = sqlSessionFactory.openSession();
		String udpateStatus = "fail";
		int status = 0;
		try{
			Utility util = new Utility();
			List<Data> dataList = candidate.getData();
			List<Data> newDataList = new ArrayList<Data>();
	    	for(Data dataObj : dataList ){
	    		Data dataObject = new Data();
	    		dataObject.setUpdatedBy(user);
	    		dataObject.setObjectId(util.getBase64EncodedID("AdmissionDriveDao"));
	    		dataObject.setAdmissionFormId(dataObj.getAdmissionFormId());
	    		dataObject.setDriveName(dataObj.getDriveName());
	    		dataObject.setRegistrationId(dataObj.getRegistrationId());
	    		dataObject.setPaymentForAdmittedCandidates(dataObj.getPaymentForAdmittedCandidates());
	    		newDataList.add(dataObject);
	    	}
	    	candidate.setData(newDataList);
			status = session.update("saveAdmittedCandidatesFromPortal", candidate);
			if(status !=0){
				udpateStatus = "success";
			}
			List<String>registrationIdList = new ArrayList<String>();
			registrationIdList.add("PGPISM_001");//List Of registration Id will come frpm portal who get admitted against a particular problm
			
			if(udpateStatus.equalsIgnoreCase("success")){
				for(String registrationId :registrationIdList ){
					Student student = session.selectOne("fetchStudentNameAgainstRegistrationId",registrationId);
					System.out.println("studentName==="+student.getStudentName());
					
					Student studentObj = new Student();
					studentObj.setCourseName(student.getCourseName());
					studentObj.setCourseId(student.getCourseId());
					studentObj.setTerm(student.getTerm());
					studentObj.setRegistrationId(registrationId);
					
					List<Fees>fessStructureList = session.selectList("fetchStudentFeesDetailsForACourse", studentObj);
					Double totalAmount = 0.0;
					for(Fees fees : fessStructureList){
						totalAmount = totalAmount + Double.parseDouble(fees.getFeesName());
					}
					System.out.println("Total amonut = "+totalAmount);
					
					String ledgerName = registrationId;
					Ledger ledger = new Ledger();
					ledger.setUpdatedBy(user);
					ledger.setObjectId(util.getBase64EncodedID("AdmissionDriveDao"));
					ledger.setLedgerCode(ledgerName);
					ledger.setLedgerName(ledgerName);
					ledger.setParentGroupCode("CURRENT ASSETS");
					ledger.setSubGroupName("STUDENT");
					ledger.setOpeningBal(totalAmount);
					session.insert("createLedger",ledger);
					
					TransactionDetails transaction = new TransactionDetails();
					transaction.setLedger("CASH");
					transaction.setAmount(student.getTotAmmount());
					session.update("updateLedgerBalanceCredit", transaction);
					
					for(Fees feescatagory : fessStructureList){
						TransactionalWorkingArea transactionalWorkingArea = new TransactionalWorkingArea();
						transactionalWorkingArea.setUpdatedBy(user);
						transactionalWorkingArea.setObjectId(util.getBase64EncodedID("AdmissionDriveDao"));
						transactionalWorkingArea.setTransactionalWorkingAreaName(feescatagory.getFeesCode());
						transactionalWorkingArea.setTransactionalWorkingAreaDesc(feescatagory.getFeesCode());
						transactionalWorkingArea.setNetAmount(Double.parseDouble(feescatagory.getFeesName()));
						transactionalWorkingArea.setTransactionMode("BANK");
						transactionalWorkingArea.setDescOfTransaction(feescatagory.getFeesCode());
						transactionalWorkingArea.setPaidAgainst(student.getCourseName());
						transactionalWorkingArea.setDepartment("ADMISSION DEPARTMENT");
						transactionalWorkingArea.setIncomeExpense("INCOME");
						transactionalWorkingArea.setAcademicYear(feescatagory.getFeesCode());
						transactionalWorkingArea.setResource(registrationId);
						session.insert("insertTotalAdmissionFeesIntoTransactionalWorkingArea",transactionalWorkingArea);
						
					}
				}
			}
			/******Changes By /naimisha 20062017*******/
			
	
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally {
			session.close();
		}
		return udpateStatus;
	}

	@Override
	public List<Data> setAlumniDataToPortal() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Data> alumniData = null;
		try{
			alumniData = session.selectList("selectalumniDataListForPortal");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return alumniData;
	}
	
	@Override
	public ProgrammeDetailsForPortal setProgrammeDetailsForPortal(String courseCode) {
		SqlSession session = sqlSessionFactory.openSession();
		ProgrammeDetailsForPortal programmeDetails = null;
		
		try{
			programmeDetails = session.selectOne("selectProgrammeDetails", courseCode);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return programmeDetails;
		
	}

	@Override
	public List<Course> getUnsendProgramListToPortal() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Course> programList = null;
		try{
			programList = session.selectList("selectUnsendProgramsToPortal");
			System.out.println("programList Size:"+programList.size());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return programList;
	}

	@Override
	public String updateProgramStatus(String courseCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String udpateStatus = "fail";
		int status = 0;
		try{
			status = session.update("changeStausForSentProgram", courseCode);
			if(status !=0){
				udpateStatus = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return udpateStatus;
	}

	@Override
	public List<LocationDetailsForPortal> setInterviewLocationDetailsToPortal(String courseCode) {
		SqlSession session =sqlSessionFactory.openSession();
		List<LocationDetailsForPortal> locationDetailsList = null;
		try {
			locationDetailsList = session.selectList("selectInterviewLocationDetailsForPortal", courseCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception IN setInterviewLocationDetailsToPortal() in AdmissionDAOImpl: ",e);
		} finally {
			session.close();
		}
		return locationDetailsList;
	}

	@Override
	public List<NoticeBoard> getNoticeList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<NoticeBoard> noticeList = null;
		try {
			noticeList = session.selectList("selectUnpublishedNotices");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception IN getNoticeList() in AdmissionDAOImpl: ",e);
		} finally {
			session.close();
		}
		return noticeList;
	}

	@Override
	public List<Course> getUnsendProgramsWithLocations() {		
		SqlSession session = sqlSessionFactory.openSession();
		List<Course> programList = null;
		try{
			programList = session.selectList("getUnsendProgramsWithLocations");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return programList;
	}

	@Override
	public List<Course> getDriveListForStudents() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Course> programList = null;
		logger.info("IN getDriveListForStudents() of AdmissionDaoImpl.java");
		try{
			programList = session.selectList("getAllOnlineCourses");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("error in getDriveListForStudents() of AdmissionDaoImpl.java",e);
		}finally {
			session.close();
		}
		return programList;
	}

	@Override
	public String getDrivesAgainstCourse(String course) {
		SqlSession session = sqlSessionFactory.openSession();
		String drive = null;
		logger.info("In getAllDrivesAgainstCourse() of AdmissionDaoImpl.java");
		try{
			drive = session.selectOne("getDriveAgainstCourse",course);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in AdmissionDaoImpl.java",e);
		}finally {
			session.close();
		}
		return drive;
	}

	@Override
	public String getDriveWithUnsentLocationAgainstCourse(String course) {
		SqlSession session = sqlSessionFactory.openSession();
		String drive = null;
		logger.info("In getDriveWithUnsentLocationAgainstCourse(course) of AdmissionDaoImpl.java");
		try{
			drive = session.selectOne("getDriveWithUnsentLocationAgainstCourse", course);
		}catch (Exception e) {
			logger.error("Error in getDriveWithUnsentLocationAgainstCourse(course) of AdmissionDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return drive;
	}

	@Override
	public String updateLocationSendStatusForDrive(String drive) {
		SqlSession session = sqlSessionFactory.openSession();
		int status = 0;
		String updateStatus = "fail";
		logger.info("In updateLocationSendStatusForDrive(String drive) in AdmissionDaoImpl.java");
		try{
			status = session.update("updateLocationSendStatusForDrive", drive);
			if(status != 0){
				updateStatus = "success";
			}
		}catch (Exception e) {
			logger.error("Error in updateLocationSendStatusForDrive(String drive) in AdmissionDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return updateStatus;
	}
	
}
