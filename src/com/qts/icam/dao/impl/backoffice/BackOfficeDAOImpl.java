package com.qts.icam.dao.impl.backoffice;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.model.common.StudentTc;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.SessionFees;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.ITSectionGroup;
import com.qts.icam.model.common.IncomeAge;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.ITSectionDetails;
import com.qts.icam.model.common.ITSection;
import com.qts.icam.model.common.TeacherForQRCode;
import com.qts.icam.model.common.StudentForQRCode;
import com.qts.icam.model.common.BookForQRCode;
import com.qts.icam.model.common.Class;
import com.qts.icam.model.common.ExamType;
import com.qts.icam.model.common.FeesDuration;
import com.qts.icam.model.backoffice.AttendancePolicy;
import com.qts.icam.model.backoffice.VendorRatingPolicy;
import com.qts.icam.model.backoffice.LibraryPolicy;
import com.qts.icam.model.backoffice.MajorEvents;
import com.qts.icam.model.backoffice.Rating;
import com.qts.icam.model.backoffice.ResidentType;
import com.qts.icam.model.backoffice.ResourceAttendance;
import com.qts.icam.model.backoffice.ResourceProfile;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.WorkShift;
import com.qts.icam.model.common.StudentAttendance;
import com.qts.icam.model.common.StudentFeesTemplate;
import com.qts.icam.model.backoffice.Holiday;
import com.qts.icam.model.backoffice.LeavePolicy;
import com.qts.icam.model.backoffice.Term;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.FinancialYear;
import com.qts.icam.dao.backoffice.BackOfficeDAO;
import com.qts.icam.model.academics.CoScholasticResult;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.administrator.AccessType;
import com.qts.icam.model.administrator.EmailEventAndTemplate;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.admission.AdmissionProcess;
import com.qts.icam.model.common.FeesCategory;
import com.qts.icam.model.admission.Form;
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
import com.qts.icam.model.backoffice.WebIQTransaction;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.backoffice.TimeTableConfigModel;
import com.qts.icam.model.backoffice.TimeTableGridData;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Address;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.CategoryAndTemplate;
import com.qts.icam.model.common.EventType;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.PreviousYearFinanceData;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Scholarship;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.finance.TransactionDetails;
import com.qts.icam.model.finance.TransactionalWorkingArea;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.library.BookAllocationDetails;
import com.qts.icam.model.library.BookLanguage;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;
import com.qts.icam.model.common.StudentFeesTemplateDetails;
import com.qts.icam.model.common.BloodGroup;
import com.qts.icam.model.common.Country;
import com.qts.icam.model.common.Organization;
import com.qts.icam.model.common.State;
import com.qts.icam.model.finance.Transaction;

@Repository
public class BackOfficeDAOImpl implements BackOfficeDAO {

	private final static Logger logger = Logger.getLogger(BackOfficeDAOImpl.class);
	
	EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	Utility util = new Utility();

	/**
	 * @author anup.roy
	 * this method submits the new academic year*/

	@Override
	public String editAcademicYear(AcademicYear academicYear) throws CustomException {
		String updateStatus="null";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			Integer count = session.selectOne("numberOfCurrentAcademicYear");
			if(null !=count){
				if(count.intValue()==0){
					System.out.println("in dao:::: start date::"+academicYear.getSessionStartDate()+"--end date::"+academicYear.getSessionEndDate()+"--name::"+academicYear.getAcademicYearName());
					academicYear.setAcademicYearObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
					int status=session.insert("createNewAcademicYear", academicYear);
					if(status==0){
						updateStatus="failed";
					}else{
						updateStatus="created";
					}
				}else{
					updateStatus="exist";
				}
			}else{
				updateStatus="failed";
			}
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("Exception in String editAcademicYear(AcademicYear academicYear) of BackofficeDaoImpl.java:"+e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return updateStatus;
	}

	//**************************************************Academic Year Ends
	
	
	

	

	@Override
	public String editSocialCategory(List<SocialCategory> socialCategoryList) throws CustomException {
		String updateStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("updateSocialCategory", socialCategoryList);
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}		
		return updateStatus;
	}

	//*******************************************************Social Category Ends
	
	

	@Override
	public String addFees(Fees fees) throws CustomException {
		String insertStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			fees.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertFees", fees);
		}catch(Exception e) {
			insertStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String editFees(List<Fees> feesList) throws CustomException {
		String updateStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("updateFees", feesList);
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}		
		return updateStatus;
	}

	//*******************************************************Fees Category Ends
	
	

	

	//*******************************************************Scholarship Ends	
	
	

	

	

	@Override
	public String editFeesTemplate(FeesTemplate feesTemplate) throws CustomException {
		String insertStatus="Updated Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.insert("updateFeesTemplate", feesTemplate);
		}catch(Exception e) {
			insertStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	//*******************************************************Fees Template Ends	

	@Override
	public List<Candidate> getDocumentVerification() throws CustomException {
		List<Candidate> candidateList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			candidateList=session.selectList("selectMedicallyFitCandidate");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return candidateList;
	}

	@Override
	public String approveDocument(Candidate candidate) throws CustomException {
		String insertStatus="Updated Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.insert("approveDocument", candidate);
		}catch(Exception e) {
			insertStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String rejectDocument(Candidate candidate) throws CustomException {
		String insertStatus="Updated Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.insert("rejectDocument", candidate);
		}catch(Exception e) {
			insertStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String checkAvailableRollNumber(String rollNumber) throws CustomException {
		String available="available";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info(rollNumber);
			Long rollNumberFromDB1=session.selectOne("checkAvailableRollNumber1", Integer.parseInt(rollNumber));
			Long rollNumberFromDB2=session.selectOne("checkAvailableRollNumber2", Integer.parseInt(rollNumber));
			if(null!=rollNumberFromDB1 || null!=rollNumberFromDB2){
				available="not-available";
			}
		}catch(Exception e) {
			available="not-available";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return available;
	}
	
	/**
	 * naimisha.ghosh
	 * 04072017*/
	
	@Override
	public String addStudent(Student student) throws CustomException {		
		String insertStatus = "fail";
		int offlineStatus = 0;
		int onlineStatus = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			student.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			student.getAddress().setObjectId(student.getObjectId());
			//student.getAddress().setRollNumber(student.getRollNumber());
			String userId = student.getResourceUserId();
			Resource resource = session.selectOne("getUserNameForId", userId);	
			String courseCode = student.getCourseId();
			String admissionMode = session.selectOne("getAdmissionModeForACourse",courseCode);
			
			student.setMobileNo(student.getResource().getMobile());
			if(null == resource){
				if(admissionMode.equalsIgnoreCase("ONLINE")){
					onlineStatus = session.insert("insertStudent", student);
				}else{
					offlineStatus = session.insert("insertStudentOffline", student);
				}
				if(onlineStatus != 0 || offlineStatus != 0) {
					session.insert("insertStudentCourseSectionMapping", student);
					student.getAddress().setUserId(student.getResourceUserId());	
					student.getAddress().setAddressType("PRESENT");
					session.insert("insertStudentPresentAddress", student.getAddress());
					
					student.getAddress().setAddressType("PERMANENT");
					session.insert("insertStudentPermanentAddress", student.getAddress());
					
					student.getAddress().setAddressType("LOCAL GUARDIAN");
					session.insert("insertStudentLocalGuardianAddress", student.getAddress());
				}
				
				if(offlineStatus !=0){
					String ledgerName = userId;
					Ledger ledger = new Ledger();
					ledger.setUpdatedBy(student.getUpdatedBy());
					ledger.setObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
					ledger.setLedgerCode(ledgerName);
					ledger.setLedgerName(ledgerName);
					ledger.setParentGroupCode("CURRENT ASSETS");
					ledger.setSubGroupName("TRADE RECEIVABLES");
					ledger.setOpeningBal(0.0);
					System.out.println("for ledger creation for student: ledger code:"+ledger.getLedgerCode()+"parentgroupcode:"+ledger.getParentGroupCode()+":subgrupname:"+ledger.getSubGroupName()+"::opening bal:"+ledger.getOpeningBal());
					int legderinsertStatus = session.insert("createLedger",ledger);
					System.out.println("legderinsertStatus:"+legderinsertStatus);
				}
				insertStatus = "success";
			}
			//PRAD JUNE 2 2018: 
			//WHY SHOULD ONE INSERT DATA INTO MAPPING TABLE IF THE MAIN RESOURCE TABLE HAS REJECTED THE ENTRY DUE TO DUPLICATE
			//SO COMMENTING THE ELSE PART
			/*else{
				session.insert("insertStudentCourseSectionMapping", student);
			}*/
			
			//PRAD JUNE 2 2018
			//AGAIN I AM REALLY NOT SURE ABOUT THIS, SOMEONE NEEDS TO LOOK INTO
			if(admissionMode.equalsIgnoreCase("ONLINE")){
				session.update("updatePrintAdmissionForm",student);
			}else{
				session.update("updatePrintAdmissionFormOffline",student);
			}
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			insertStatus = "fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return insertStatus;
	}

	//*******************************************************Student Ends	
	
	@Override
	public List<Exam> getExamList() throws CustomException {
		List<Exam> examList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			examList=session.selectList("selectExamList");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return examList;
	}

	@Override
	public List<Exam> getTermForExam() throws CustomException {
		List<Exam> termList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			termList=session.selectList("selectTermListForExam");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return termList;
	}

	
	@Override
	public String addExamDateSetUp(Exam exam) throws CustomException {
		
		String insertStatus="Insert Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			exam.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			int countRecord = session.selectOne("countYearWiseExam",exam);
			if(countRecord == 0){
				session.insert("insertExamSetUp", exam);
			}
			if(countRecord != 0){
				session.update("updateExamSetUp", exam);
			}
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			insertStatus="Insert Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public String insertAssignedEvent(CalendarEvent calendarEvent)
			throws CustomException {
		String insertStatus="Insert Successful";
		String viewer = calendarEvent.getCalendarEventViewer();
		
		String userId = calendarEvent.getUpdatedBy();
		SqlSession session =sqlSessionFactory.openSession();
		try{
			String eventViewer = calendarEvent.getCalendarEventViewer();
			if(eventViewer == "Personal"){
				
			}
			calendarEvent.setCalendarEventObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			String totalRoleName = calendarEvent.getRollName();
			String[] splitedRoleName = totalRoleName.split(",");
			session.insert("insertAssignedEvent", calendarEvent);
			session.commit();
			
			for(int i=0;i<splitedRoleName.length;i++){
				calendarEvent.setRollName(splitedRoleName[i]);
				calendarEvent.setCalendarIntEventCode(calendarEvent.getCalendarIntEventCode());
				if(!viewer.trim().equals("Personal")){
					session.insert("insertRollForAssignedEvent", calendarEvent);
					session.commit();
				}
				if(viewer.trim().equals("Personal")){
					calendarEvent.setUpdatedBy(calendarEvent.getUpdatedBy());
					session.insert("insertRollForAssignedEventPersonal", calendarEvent);
					session.commit();
				}
		   }		
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			insertStatus="Insert Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String getCalendarEventFromDBForAllUser(String eventType) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<CalendarEvent> eventFromDb = null;
		String output = null;
		try {
			eventFromDb = session.selectList("fetchCalendarEventListForAllUser",eventType);
			StringBuilder sb = new StringBuilder();
			for (CalendarEvent calendarEvent : eventFromDb) {
				sb.append(calendarEvent.getCalendarEventDesc() + ",");
				sb.append(calendarEvent.getCalendarEventName() + ",");
				sb.append(calendarEvent.getCalendarEventStartDate() + ",");
				sb.append(calendarEvent.getCalendarEventEndDate() + ",");
				sb.append(calendarEvent.getCalendarEventStartTime() + ",");
				sb.append(calendarEvent.getCalendarEventEndTime() + ",");
				sb.append(calendarEvent.getCalendarEventEndColor() + "*");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return output;
	}

	@Override
	public String getCalendarEventFromDBForRoleBased(String rollName,String eventType)
			throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<CalendarEvent> eventFromDb = null;
		String output = null;
		try {
			CalendarEvent CalendarEventObj = new CalendarEvent();
			EventType event = new EventType();
			event.setEventTypeCode(eventType);
			CalendarEventObj.setRollName(rollName);
			CalendarEventObj.setEventType(event);
			eventFromDb = session.selectList("fetchCalendarEventListForRollBased",CalendarEventObj);
			StringBuilder sb = new StringBuilder();
			for (CalendarEvent calendarEvent : eventFromDb) {
				sb.append(calendarEvent.getCalendarEventDesc() + ",");
				sb.append(calendarEvent.getCalendarEventName() + ",");
				sb.append(calendarEvent.getCalendarEventStartDate() + ",");
				sb.append(calendarEvent.getCalendarEventEndDate() + ",");
				sb.append(calendarEvent.getCalendarEventStartTime() + ",");
				sb.append(calendarEvent.getCalendarEventEndTime() + ",");
				sb.append(calendarEvent.getCalendarEventEndColor() + "*");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return output;
	}

	@Override
	public String editAssignedEvent(CalendarEvent calendarEvent)
			throws CustomException {
		String updateStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("calendarEvent name"+calendarEvent.getCalendarEventName());
			session.update("updateAssignedEvent", calendarEvent);
			session.commit();
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return updateStatus;
	}

	@Override
	public String deleteAssignedEvent(CalendarEvent calendarEvent)
			throws CustomException {
		String updateStatus="Delete Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			List<CalendarEvent> listEventRolleMappingId = session.selectList("selectEventRolleMappingIdList", calendarEvent);
			int update = session.update("deleteAssignedEvent", calendarEvent);
			if(update != 0){
				for(CalendarEvent caldarEventObj : listEventRolleMappingId){
					CalendarEvent calEvent = new CalendarEvent();
					calEvent.setUpdatedBy(calendarEvent.getUpdatedBy());
					calEvent.setCalendarIntEventCode(caldarEventObj.getCalendarIntEventCode());
					session.update("deleteAssignedEventRoleMapping", calEvent);
				}
				
			}
			session.commit();
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			updateStatus="Delete Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return updateStatus;
	}
	
//	@Override
//	public List<StudentSubjectMapping> getSubjectsAndGroupForStandard(String standard) throws CustomException {
//		SqlSession session =sqlSessionFactory.openSession();
//		List<StudentSubjectMapping> mappingList = null;
//		try {
//			mappingList = session.selectList("selectSubjectsAndGroupForStandard", standard);			
//		}catch (Exception e) {
//			e.printStackTrace();
//			logger.error("getSubjectsAndGroupForStandard() In CommonDaoImpl.java: Exception" ,e);
//		} finally {
//			session.close();
//		}
//		return mappingList;
//	}

	@Override
	public String editStudentSubjectMapping(StudentSubjectMapping studentSubjectMapping) throws CustomException {
		String status="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			studentSubjectMapping.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			if(null!=studentSubjectMapping.getOldSubjectList() && studentSubjectMapping.getOldSubjectList().size()!=0){
				session.update("inactiveStudentSubjectMapping", studentSubjectMapping);
			}
			if(null!=studentSubjectMapping.getNewSubjectList() && studentSubjectMapping.getNewSubjectList().size()!=0){
				for(String newSubject:studentSubjectMapping.getNewSubjectList()){
					studentSubjectMapping.setSubject(newSubject);
					StudentSubjectMapping studentSubjectMappingCheckParam=new StudentSubjectMapping();
					studentSubjectMappingCheckParam.setSubject(newSubject);
					studentSubjectMappingCheckParam.setStudent(studentSubjectMapping.getStudent());
					StudentSubjectMapping checker=session.selectOne("selectInactiveSubjectForStudent",studentSubjectMappingCheckParam);
					if(null!=checker && null!=checker.getSubject()){						
						session.insert("updateStudentSubjectMapping", studentSubjectMapping);
					}else{
						session.update("insertStudentSubjectMapping", studentSubjectMapping);
					}
				}
			}
			
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return status;
	}
	
	@Override
	public List<AcademicTimeTable> getTimeTableDetailsFromDB(
			AcademicTimeTable academicTimeTable) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<AcademicTimeTable> detailsListFromDb = null;
		try {
			detailsListFromDb = session.selectList("selectAcademicTimeTable",academicTimeTable);
			for (AcademicTimeTable a : detailsListFromDb) {
				List<AcademicTimeTableDetails> academicTimeTableDetailsList = session.selectList("selectAcademicTimeTableDetails", a);
				if (academicTimeTableDetailsList != null && academicTimeTableDetailsList.size() != 0) {
					a.setListAcademicTimeTableDetails(academicTimeTableDetailsList);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return detailsListFromDb;
	}

	@Override
	public String getSubjectsBasedOnStandardAndSubjectGroup(Subject subject)
			throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Subject> subjectList = null;
		String output = null;
		try {
			subjectList = session.selectList("selectSubjectsBasedOnStandardAndSubjectGroup",subject);
			StringBuilder sb = new StringBuilder();
			for (Subject sub : subjectList) {
				sb.append(sub.getSubjectCode() + ",");
				sb.append(sub.getSubjectName() + "*");
				output = sb.toString().substring(0, sb.toString().length());
			}
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return output;
	}

	@Override
	public String addTimeTable(AcademicTimeTable academicTimeTable)
			throws CustomException {
		String insertStatus="Insert Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			academicTimeTable.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertIntoAcademicTimeTable", academicTimeTable);
			session.commit();
		}catch(Exception e) {
			insertStatus="Insert Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String getTimeTableDurationSlotForValidation(
			AcademicTimeTable academicTimeTable) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<AcademicTimeTableDetails> timeTableDetails = null;
		String output = null;
		try {
			timeTableDetails = session.selectList("selectTimeTableDurationSlotForValidation",academicTimeTable);
			StringBuilder sb = new StringBuilder();
			for (AcademicTimeTableDetails details : timeTableDetails) {
				sb.append(details.getTimeTableDetailsStartTime() + ",");
				sb.append(details.getTimeTableDetailsEndTime() + "~");
				output = sb.toString();
			}
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return output;
	}

	@Override
	public String addSubjectBreakAndTeacherForTimeTable(
			AcademicTimeTable academicTimeTable) throws CustomException {
		String insertStatus="Insert Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("NO BREAK"+academicTimeTable.getAcademicTimeTableDetails().getTimeTableDetailsSubject());
			academicTimeTable.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			if (academicTimeTable.getAcademicTimeTableDetails().getTimeTableDetailsSubject().equals("SHORT BREAK") || academicTimeTable.getAcademicTimeTableDetails().getTimeTableDetailsSubject().equals("LONG BREAK")) {
				logger.info("::in break::"+academicTimeTable.getAcademicTimeTableDetails().getTimeTableDetailsSubject());
				session.insert("insertIntoAcademicTimeTableForBreak", academicTimeTable);
				session.commit();
			}
			if (!academicTimeTable.getAcademicTimeTableDetails().getTimeTableDetailsSubject().equals("SHORT BREAK") && !academicTimeTable.getAcademicTimeTableDetails().getTimeTableDetailsSubject().equals("LONG BREAK")) {
				logger.info("::not in break::"+academicTimeTable.getAcademicTimeTableDetails().getTimeTableDetailsSubject());
				session.insert("insertSubjectAndTeacherForTimeTable", academicTimeTable);
				session.commit();
			}
		} catch(Exception e) {
			insertStatus="Insert Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String getTeacherNames(String subjectName) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> teacherNamesPerSubjectFromDB = null;
		String output = null;
		try {
			teacherNamesPerSubjectFromDB = session.selectList("getTeacherNamesBasedOnSubject",subjectName);
			StringBuilder sb = new StringBuilder();
			for (Resource resource : teacherNamesPerSubjectFromDB) {
				sb.append(resource.getUserId() + "*@#");
				sb.append(resource.getName() + "*~*");
				output = sb.toString();
			}
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return output;
	}

	@Override
	public String getTeacherConflictionForTimeTable(AcademicTimeTable academicTimeTable) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<AcademicTimeTableDetails> timeTableDetails = null;
		String output = null;
		try {
			timeTableDetails = session.selectList("getTeacherConflictionForTimeTable",academicTimeTable);
			StringBuilder sb = new StringBuilder();
			if (timeTableDetails != null && timeTableDetails.size() != 0) {
				for (AcademicTimeTableDetails a : timeTableDetails) {
					sb.append(a.getTimeTableDetailsTeacherName() + ",");
					sb.append(a.getTimeTableDetailsSubjectName() + ",");
					output = sb.toString();
					}
				}
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return output;
	}

	@Override
	public List<AcademicTimeTable> getTimeTableDetails(AcademicTimeTable academicTimeTable) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<AcademicTimeTable> detailsListFromDb = null;
		try {
			detailsListFromDb = session.selectList("selectAcademicTimeTable",academicTimeTable);
			for (AcademicTimeTable a : detailsListFromDb) {
				List<AcademicTimeTableDetails> academicTimeTableDetailsList = session.selectList("selectAcademicTimeTableDetails", a);
				if (academicTimeTableDetailsList != null && academicTimeTableDetailsList.size() != 0) {
					a.setListAcademicTimeTableDetails(academicTimeTableDetailsList);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return detailsListFromDb;
	}

	@Override
	public List<AcademicTimeTableDetails> getTimeTableSubjectCount(AcademicTimeTable academicTimeTable) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<AcademicTimeTableDetails> subjectCount = null;
		try {
			subjectCount = session.selectList("selectSubjectAndCount",academicTimeTable);
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return subjectCount;
	}

	@Override
	public String deleteDraggedElementForAcademicTimeTable(AcademicTimeTable academicTimeTable) throws CustomException {
		String insertStatus="Delete Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			academicTimeTable.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.update("deleteDraggedSubjectForAcademicTimeTable",academicTimeTable);
			session.commit();
		}catch(Exception e) {
			insertStatus="Delete Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String updateAssignedPeriodDuration(
			AcademicTimeTableDetails academicTimeTableDetails) throws CustomException {
		String insertStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			int updateStatus = session.update("updateTimeTableForPeriodDuration", academicTimeTableDetails);
			logger.info("first update "+updateStatus);
			if(updateStatus != 0){
				AcademicTimeTable selectedData = session.selectOne("selectedParameterForEditDuration", academicTimeTableDetails);
				if(selectedData != null){
					AcademicTimeTableDetails academicTimeTableDetailsObject = new AcademicTimeTableDetails();
					
					academicTimeTableDetailsObject.setTimeTableDetailsStartTime(academicTimeTableDetails.getTimeTableDetailsStartTime());
					academicTimeTableDetailsObject.setTimeTableDetailsEndTime(academicTimeTableDetails.getTimeTableDetailsEndTime());
					academicTimeTableDetailsObject.setTimeTableDetailsId(academicTimeTableDetails.getTimeTableDetailsId());
					academicTimeTableDetailsObject.setIndividualSlot(selectedData.getAcademicTimeTableDetails().getIndividualSlot());
					
					selectedData.setUpdatedBy(academicTimeTableDetails.getUpdatedBy());
					selectedData.setAcademicTimeTableDetails(academicTimeTableDetailsObject);
					session.update("updateTimeTableDetailsForPeriodDuration", selectedData);
				}
			}
			session.commit();
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			insertStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String updateSubjectTypeTimeTable(AcademicTimeTable academicTimeTable) throws CustomException {
		String insertStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			academicTimeTable.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.update("updateSubjectAndTeacherForTimeTable", academicTimeTable);
			session.commit();
		}catch(Exception e) {
			insertStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public List<FeesTemplate> getSearchBasedFeesTemplateList(Map<String, Object> parameters) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<FeesTemplate> feesTemplateList = null;
		try {
			feesTemplateList = session.selectList("selectFeesTemplateList",parameters);
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return feesTemplateList;
	}

	@Override
	public List<Student> getSearchBasedStudentList(Map<String, Object> parameters) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try {
			studentList = session.selectList("selectAllStudents",parameters);
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return studentList;
	}

	@Override
	public List<Candidate> getSearchBasedMedFitDocumentsList(Map<String, Object> parameters) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Candidate> candidateList = null;
		try {
			candidateList = session.selectList("selectMedicallyFitCandidate",parameters);
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return candidateList;
	}

	@Override
	public String getSubjectsForTeacher(String teacher) throws CustomException {
		String subjects="";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			List<Subject> subjectList=session.selectList("selectSubjectsForTeacher", teacher);
			if(null!=subjectList && subjectList.size()!=0){
				for(Subject subject:subjectList){
					subjects=subjects+subject.getSubjectCode()+"*~*";
				}
			}
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return subjects;
	}

	@Override
	public String editTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) throws CustomException {
		String status="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			teacherSubjectMapping.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			if(null!=teacherSubjectMapping.getOldSubjectList() && teacherSubjectMapping.getOldSubjectList().size()!=0){
				session.update("inactiveTeacherSubjectMapping", teacherSubjectMapping);
			}
			if(null!=teacherSubjectMapping.getNewSubjectList() && teacherSubjectMapping.getNewSubjectList().size()!=0){
				for(String newSubject:teacherSubjectMapping.getNewSubjectList()){
					teacherSubjectMapping.setSubject(newSubject);
					TeacherSubjectMapping teacherSubjectMappingCheckParam=new TeacherSubjectMapping();
					teacherSubjectMappingCheckParam.setSubject(newSubject);
					Resource resource=new Resource();
					resource.setUserId(teacherSubjectMapping.getResource().getUserId());
					teacherSubjectMappingCheckParam.setResource(resource);
					TeacherSubjectMapping checker=session.selectOne("selectInactiveSubjectForTeacher",teacherSubjectMappingCheckParam);
					if(null!=checker && null!=checker.getSubject()){
						session.update("updateTeacherSubjectMapping", teacherSubjectMapping);
					}else{
						session.insert("insertTeacherSubjectMapping", teacherSubjectMapping);
					}
				}
			}
			
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return status;
	}

	@Override
	public List<StudentResult> getStudentsResultForPromotion(StudentResult studentResult) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<StudentResult> finalStudentResultList = new ArrayList<StudentResult>();
		try {
			studentResult.setSerialId(33);
			session.insert("populateResult",studentResult);
			List<StudentResult> studentResultList = session.selectList("selectStudentsResultForPromotion");
			
			if(null!=studentResultList && studentResultList.size()!=0){
				for(StudentResult oneStudent:studentResultList){
					String status=session.selectOne("getStudentFeesPaymentStatus",oneStudent.getRollNumber());
					if(null!=status && status.length()!=0)
						oneStudent.setStatus(status);
					else
						oneStudent.setStatus("REMAINING");
					finalStudentResultList.add(oneStudent);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return finalStudentResultList;
	}

	@Override
	public String updateStudentPromotion(List<StudentResult> studentResultList) throws CustomException {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			Student student = new Student();
			student.setStudentResultList(studentResultList);
			session.insert("updateStudentPromotion",student);
			session.commit();
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<StudentResult> getViewPendingPromotion() throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<StudentResult> studentResultList = null;
		try {
			studentResultList = session.selectList("selectViewPendingPromotion");
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return studentResultList;
	}



	@Override
	public String addTC(StudentTC studentTC) throws CustomException {
		String insertStatus="TC Granted Successfuly";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			Student student=getStudentDetails(studentTC.getRollNumber());
			studentTC.setStudent(student);
			
			
			session.insert("grantStudentTC",studentTC);
			session.commit();
		}catch(Exception e) {
			insertStatus="TC Granting Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<ExStudents> searchExStudents(Map<String, String> parameters) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<ExStudents> exStudentsList = null;
		try {
			exStudentsList = session.selectList("selectSearchExStudents", parameters);
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return exStudentsList;
	}
	

	@Override
		public int deleteSelectedAttachment(int attachId) throws CustomException {
			int updateStatus = 0;
			SqlSession session = sqlSessionFactory.openSession();
			try{
				updateStatus = session.update("deleteSelectedAttachment", attachId);
			} catch (Exception e) {
				logger.error(e);
				CustomException.exceptionHandler(e);
			} finally {
				session.close();
			}
			return updateStatus;
		}
	
	@Override
	public List<EventType> getEventType() {
		SqlSession session =sqlSessionFactory.openSession();
		List<EventType> listEvent = null;
		try {
			listEvent = session.selectList("selectEventType");
		}  catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return listEvent;
	}
	
	@Override
	public String getCalendarEventFromDBForPersonal(String eventType,String userId) {
		SqlSession session =sqlSessionFactory.openSession();
		List<CalendarEvent> eventFromDb = null;
		String output = null;
		try {
			EventType eventTypeObj = new EventType();
			eventTypeObj.setEventTypeCode(eventType);
			eventTypeObj.setUpdatedBy(userId);
			eventFromDb = session.selectList("fetchCalendarEventListForPersonal",eventTypeObj);
			StringBuilder sb = new StringBuilder();
			for (CalendarEvent calendarEvent : eventFromDb) {
				sb.append(calendarEvent.getCalendarEventDesc() + ",");
				sb.append(calendarEvent.getCalendarEventName() + ",");
				sb.append(calendarEvent.getCalendarEventStartDate() + ",");
				sb.append(calendarEvent.getCalendarEventEndDate() + ",");
				sb.append(calendarEvent.getCalendarEventStartTime() + ",");
				sb.append(calendarEvent.getCalendarEventEndTime() + ",");
				sb.append(calendarEvent.getCalendarEventEndColor() + "*");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return output;
	}
	
	
	//LeaVE
	@Override
	public String insertLeaveType(AcademicLeaveCategory academicLeaveCategory) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			academicLeaveCategory.setLeaveCategoryObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertLeaveType",academicLeaveCategory);
			session.commit();
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public String deleteLeaveType(AcademicLeaveCategory academicLeaveCategory) {
		String insertStatus="Leave Deleted Successfuly";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("deleteLeaveType", academicLeaveCategory);
			session.commit();
		}catch(Exception e) {
			insertStatus="Leave Deleted Failed";
			logger.error(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public String editLeaveType(AcademicLeaveCategory academicLeaveCategory) {
		String insertStatus="Leave Edited Successfuly";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("editLeaveType", academicLeaveCategory);
			session.commit();
		}catch(Exception e) {
			insertStatus="Leave Edited Failed";
			logger.error(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public String getPreviousLeaveType() {
		SqlSession session =sqlSessionFactory.openSession();
		List<AcademicLeaveCategory> preLeaveFromDb = null;
		String output = null;
		try {
			preLeaveFromDb = session.selectList("selectLeaveType");
			StringBuilder sb = new StringBuilder();
			for (AcademicLeaveCategory academicLeaveCategoryy : preLeaveFromDb) {
				sb.append(academicLeaveCategoryy.getLeaveTypeId() + ",");
				sb.append(academicLeaveCategoryy.getLeaveCategoryName() + "/");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return output;
	}
	
	@Override
	public List<AcademicLeave> getLeaveTypeList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<AcademicLeave> listLeaveFromDb = null;
		try {
			listLeaveFromDb = session.selectList("selectlistLeaveStructure");
		}  catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return listLeaveFromDb;
	}
	
	@Override
	public List<AcademicLeaveCategory> getleavetypes() {
		SqlSession session =sqlSessionFactory.openSession();
		List<AcademicLeaveCategory> preLeaveFromDb = null;
		try {
			preLeaveFromDb = session.selectList("selectLeaveType");
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method: getleavetypes() ", e);
		} finally {
			session.close();
		}
		return preLeaveFromDb;
	}
	
	/*@Override
	public String insertLeaveStructure(AcademicLeave academicLeave) {
		String submitResponse = "Fail";
		SqlSession session =sqlSessionFactory.openSession();
		try{			
			academicLeave.setLeaveObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));			
			System.out.println("9."+academicLeave.getLeaveObjectId());
			System.out.println("10."+academicLeave.getUpdatedBy());
			System.out.println("1."+academicLeave.getAcademicYear().getAcademicYearName());
			System.out.println("2."+academicLeave.getEmployeeType().getEmployeeTypeName());
			System.out.println("3."+academicLeave.getJobType().getJobTypeName());
			System.out.println("4."+academicLeave.getAcademicLeaveType().getLeaveCategoryName());
			System.out.println("5."+academicLeave.getLeaveDuration());
			System.out.println("6."+academicLeave.getLeaveLimit());
			System.out.println("7."+academicLeave.getLeaveEncashment());
			System.out.println("8."+academicLeave.isLeaveCarryForward());
			
			
			if(academicLeave.getStatus().equalsIgnoreCase("INSERT")){
				//System.out.println("INSERT");
				int insertStatus = session.insert("insertLeaveStructure", academicLeave);
				//System.out.println("insertStatus:"+insertStatus);
				
				if(insertStatus != 0){
					//System.out.println("INSERT");
					submitResponse = "Success";
				}
			}else if(academicLeave.getStatus().equalsIgnoreCase("UPDATE")) {
				//System.out.println("UPDATE");
				int updateStatus = session.insert("updateLeaveStructureDetails", academicLeave);
				//System.out.println("updateStatus:"+updateStatus);
				if(updateStatus == 1){
					//System.out.println("IN SIDE DAO IF updateStatus:"+updateStatus);
					submitResponse = "Success";
				}
			}
			
		}catch(Exception e) {	
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return submitResponse;
	}*/
	
	
	
	@Override
	public String insertLeaveStructure(AcademicLeave academicLeave) {
		String submitResponse = "Fail";
		SqlSession session =sqlSessionFactory.openSession();
		try{			
			academicLeave.setLeaveObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));			
			System.out.println("9."+academicLeave.getLeaveObjectId());
			System.out.println("10."+academicLeave.getUpdatedBy());			
			//System.out.println("1."+academicLeave.getAcademicYear().getAcademicYearName());
			System.out.println("2."+academicLeave.getEmployeeType().getEmployeeTypeName());
			System.out.println("3."+academicLeave.getJobType().getJobTypeName());			
			System.out.println("4."+academicLeave.getAcademicLeaveType().getLeaveCategoryName());
			System.out.println("5."+academicLeave.getLeaveDuration());
			//System.out.println("6."+academicLeave.getLeaveLimit());
			System.out.println("7."+academicLeave.getLeaveEncashment());
			System.out.println("8."+academicLeave.isLeaveCarryForward());
			
			
			if(academicLeave.getStatus().equalsIgnoreCase("INSERT")){
				System.out.println("INSERT");
				int insertStatus = session.insert("insertLeaveStructure", academicLeave);
				System.out.println("insertStatus:"+insertStatus);
				
				if(insertStatus != 0){
					System.out.println("INSERT");
					submitResponse = "Success";
				}
			}else if(academicLeave.getStatus().equalsIgnoreCase("UPDATE")) {
				System.out.println("UPDATE");
				int updateStatus = session.insert("updateLeaveStructureDetails", academicLeave);
				System.out.println("updateStatus:"+updateStatus);
				if(updateStatus == 1){
					System.out.println("IN SIDE DAO IF updateStatus:"+updateStatus);
					submitResponse = "Success";
				}
			}
			
		}catch(Exception e) {	
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return submitResponse;
	}
	
	@Override
	public List<AcademicLeave> listLeave(AcademicLeave academicLeave) {
		SqlSession session =sqlSessionFactory.openSession();
		List<AcademicLeave> leaveDetailListFromDb = null;
		try {
			leaveDetailListFromDb = session.selectList(
					"selectSpecificLeaveStructure", academicLeave);
		}catch(Exception e) {
			logger.error(e);
		}finally{
			session.close();
		}
		return leaveDetailListFromDb;
	}
	
	@Override
	public String updateLeaveStructure(AcademicLeave academicLeave) {
		String updateStatus="Update Successfuly";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("updateLeaveStructure", academicLeave);
			session.commit();
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
		}finally{
			session.close();
		}
		return updateStatus;
	}
	
	@Override
	public List<StudentSubjectMapping> getStudentSubjectMappingList()throws CustomException {
		List<StudentSubjectMapping> mappingStatusList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			mappingStatusList=session.selectList("selectStudentSubjectMappingList");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return mappingStatusList;
	}

	@Override
	public List<Student> getStudentsInStudentSubjectMapping(Student student)throws CustomException {
		List<Student> studentList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			studentList=session.selectList("selectStudentsInStudentSubjectMapping", student);
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return studentList;
	}

	@Override
	public List<Student> getStudentsNotInStudentSubjectMapping(Student student) throws CustomException {
		List<Student> studentList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			studentList=session.selectList("selectStudentsNotInStudentSubjectMapping", student);
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return studentList;
	}

	@Override
	public String insertClassSubjectMapping(List<StudentSubjectMapping> mappingList) throws CustomException {
		String updateStatus="Inserted Successfuly";
		SqlSession session =sqlSessionFactory.openSession();
		Set<String> setOfRollNumber = new HashSet<String>();
		try{
			for(StudentSubjectMapping studentSubjectMapping:mappingList){
				studentSubjectMapping.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
				session.update("insertStudentSubjectMapping", studentSubjectMapping);
			}
			/**
			@author naimisha.ghosh
			07.08.2017
			mid session entry
			*/
			String standard = mappingList.get(0).getStudent().getStandard();
 			String section = mappingList.get(0).getStudent().getSection();
 			String updatedBy = mappingList.get(0).getUpdatedBy();
 			for(StudentSubjectMapping stuSubjectMapping:mappingList){
 				setOfRollNumber.add(stuSubjectMapping.getStudent().getRollNumber().toString());
 			}
			/**
			naimisha 08.08.2017
			*/
			Student studentObj = new Student();
			studentObj.setStandard(standard);
			studentObj.setSection(section);
			int studentCount = session.selectOne("checkIfMarksAlreadyEnteredForAStandard",studentObj);
			if(studentCount != 0){
				for (String roll : setOfRollNumber) {
					int rollNumber = Integer.parseInt(roll);
					Student student = new Student();
					student.setStandard(standard);
					student.setSection(section);
					student.setUpdatedBy(updatedBy);
					student.setRollNumber(rollNumber);
					/*session.insert("insertIntoStudentMarksAfterStudentSubjectMapping", student);*/
					session.insert("insertIntoStudentMarksAfterStudentSubjectMappingForCBSENew", student);
				}
 			}
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return updateStatus;
	}

	


//@Override
//public int createNotice(NoticeBoard noticeBoard) {
//	logger.info("createNotice() method in  BackOfficeDAOImpl");
//	SqlSession session =sqlSessionFactory.openSession();
//	int insertStatus=0;
//	try {
//		noticeBoard.setNoticeObjId((encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl")));
//		insertStatus = session.insert("insertIntoNoticeBoard",noticeBoard);
//		session.commit();
//	} catch (Exception e) {
//		insertStatus=0;
//		logger.error("Exception in createNotice() method in  BackOfficeDAOImpl", e);
//	} finally {
//		session.close();
//	}
//	return insertStatus;
//}
//
//
//
//@Override
//public int deleteNotice(NoticeBoard noticeBoard) {
//	logger.info("deleteNotice() method in  AdministratorDAOImpl");
//	SqlSession session =sqlSessionFactory.openSession();
//	int deleteStatus=0;
//	try {
//		deleteStatus = session.delete("deleteNotice",noticeBoard);
//		session.commit();
//	} catch (Exception e) {
//		logger.error("Exception in deleteNotice() method in  BackOfficeDAOImpl", e);
//	} finally {
//		session.close();
//	}
//	return deleteStatus;
//}
//
//@Override
//public NoticeBoard viewNotice(NoticeBoard noticeBoard) {
//	logger.info("viewNotice() method in  AdministratorDAOImpl");
//	SqlSession session =sqlSessionFactory.openSession();
//	try {
//		noticeBoard = session.selectOne("viewNotice",noticeBoard);
//	} catch (Exception e) {
//		logger.error("Exception in viewNotice() method in  BackOfficeDAOImpl", e);
//	} finally {
//		session.close();
//	}
//	return noticeBoard;
//}
//
//@Override
//public int updateNotice(NoticeBoard noticeBoard) {
//	logger.info("updateNotice() method in  AdministratorDAOImpl");
//	SqlSession session =sqlSessionFactory.openSession();
//	int updateStatus=0;
//	try {
//		updateStatus = session.update("updateNotice",noticeBoard);
//		session.commit();
//	} catch (Exception e) {
//		logger.error("Exception in updateNotice() method in  BackOfficeDAOImpl", e);
//	} finally {
//		session.close();
//	}
//	return updateStatus;
//}


	@Override
	public Standard getStudentRollAgainstStandardAndSection(Student student) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		Standard standardDB = null;
		try {
			//System.out.println("IN DAO :: "+student.getStandard()+" .. "+student.getSection());
			standardDB = session.selectOne("selectStudentRollForStandardAndSection",student);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return standardDB;
	}
	
	@Override
	public List<AcademicLeave> getEmployeeCompleteLeaveDetails(AcademicLeave academicLeave) {
		SqlSession session = sqlSessionFactory.openSession();
		List<AcademicLeave> academicLeaveList = null;
		try {
			//System.out.println(academicLeave.getAcademicYear().getAcademicYearName()+"~"+academicLeave.getJobType().getJobTypeName()+"~"+academicLeave.getEmployeeType().getEmployeeTypeCode());
			academicLeaveList = session.selectList("getEmployeeCompleteLeaveDetails", academicLeave);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return academicLeaveList;
	}
	
	
	@Override
	public List<StudentSubjectMapping> listUpdatedStudentSubjectMapping(Student student) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<StudentSubjectMapping> studentSubjectMappingList = null;
		try {
			studentSubjectMappingList = session.selectList("listUpdatedStudentSubjectMapping", student);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return studentSubjectMappingList;
	}
	
	
	
	
	
	
	
	
	
	
	//------------------FOR NOTICE: DONE BY SINGH--------------------------------
	
	@Override
	public String createNotice(NoticeBoard noticeBoard) {
		logger.info("createNotice() method in  BackOfficeDAOImpl");
		SqlSession session =sqlSessionFactory.openSession();
		String insertStatus="Notice Created Successfully.";
		try {
			noticeBoard.setNoticeObjId((encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl")));
			session.insert("insertIntoNoticeBoard",noticeBoard);
		} catch (Exception e) {
			insertStatus="Notice Creation Failed.";
			logger.error("Exception in createNotice() method in  BackOfficeDAOImpl", e);
		} finally {
			session.close();
		}
		return insertStatus;
	}



	@Override
	public String deleteNotice(NoticeBoard noticeBoard) {
		logger.info("deleteNotice() method in  AdministratorDAOImpl");
		SqlSession session =sqlSessionFactory.openSession();
		String deleteStatus="Notice Deleted Successfully";
		try {
			session.delete("deleteNotice",noticeBoard);
		} catch (Exception e) {
			logger.error("Exception in deleteNotice() method in  BackOfficeDAOImpl", e);
			deleteStatus="Notice Deletion Failed";
		} finally {
			session.close();
		}
		return deleteStatus;
	}

	@Override
	public NoticeBoard viewNotice(NoticeBoard noticeBoard) {
		logger.info("viewNotice() method in  AdministratorDAOImpl");
		SqlSession session =sqlSessionFactory.openSession();
		try {
			noticeBoard = session.selectOne("viewNotice",noticeBoard);
		} catch (Exception e) {
			logger.error("Exception in viewNotice() method in  BackOfficeDAOImpl", e);
		} finally {
			session.close();
		}
		return noticeBoard;
	}

	@Override
	public String updateNotice(NoticeBoard noticeBoard) {
		logger.info("updateNotice() method in  AdministratorDAOImpl");
		SqlSession session =sqlSessionFactory.openSession();
		String updateStatus="Notice Updated Successfully.";
		try {
			session.update("updateNotice",noticeBoard);
		} catch (Exception e) {
			updateStatus="Notice Update Failed.";
			logger.error("Exception in updateNotice() method in  BackOfficeDAOImpl", e);
		} finally {
			session.close();
		}
		return updateStatus;
	}
	
	
	
	
	
	
	
	//singh.backoffice
	
	
	@Override
	public String addSocialCategory(SocialCategory socialCategory) throws CustomException {
		String insertStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			socialCategory.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertSocialCategory", socialCategory);
		}catch(Exception e) {
			insertStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	
	@Override
	public String editSocialCategory(SocialCategory socialCategory) throws CustomException {
		String updateStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("updateSocialCategory", socialCategory);
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}		
		return updateStatus;
	}
	
	
	@Override
	public List<VendorType> getVendorTypes() {
		SqlSession session =sqlSessionFactory.openSession();
		List<VendorType> vendorTypeList = null;
		try {
			vendorTypeList = session.selectList("selectVendorTypes");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vendorTypeList;
	}
	
	
	@Override
	public String addVendor(Vendor vendor) {
		SqlSession session =sqlSessionFactory.openSession();
		String updateStatus="Insert Successful";
		try {
			vendor.setVendorObjectId(encryptDecrypt.getBase64EncodedID("AdminDAOImpl"));
			session.insert("insertVendor", vendor);
		}catch (Exception e) {
			updateStatus="Insert Failed";
			logger.error("addVendor(Vendor vendor) In CommonDaoImpl.java: Exception",e);
		} finally {
			session.close();
		}
		return updateStatus;
	}
	
	@Override
	public String updateVendorDetails(Vendor vendor) {
		SqlSession session =sqlSessionFactory.openSession();
		String updateStatus="Update Successful";
		try {
			session.update("updateVendor", vendor);
		}catch (Exception e) {
			updateStatus="Update Failed";
			logger.error("updateVendorDetails(Vendor vendor) In CommonDaoImpl.java: Exception",e); 
		} finally {
			session.close();
		}
		return updateStatus;
	}
	
	
	
	
	
	
	
	
	
	
	
	//anup.backoffice
	
	
	@Override
	public List<Leave> getLeave() throws CustomException {
		List<Leave> leaveList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			leaveList = session.selectList("selectLeave");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return leaveList;
	}
	
	
	@Override
	public String editLeave(Leave leave) throws CustomException {
		String updateStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("updateLeave", leave);
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}		
		return updateStatus;
	}
	
	
	@Override
	public List<Fees> getFeesList() throws CustomException {
		List<Fees> feesList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			feesList=session.selectList("selectFeesList");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return feesList;
	}
	
	@Override
	public List<AcademicYear> getAcademicYearList() throws CustomException{
		List<AcademicYear> academicYearList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			academicYearList=session.selectList("selectAcademicYearList");
			/*if(null==academicYearList || academicYearList.size()==0){
				throw new Exception();
			}else{
				for(AcademicYear academicYear:academicYearList){
					if(academicYear.getYearStatus().equalsIgnoreCase("CURRENT") && academicYear.getSessionEndDate()!=null){
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String currentDate = sdf.format(new Date());
						if(sdf.parse(currentDate).after(sdf.parse(academicYear.getSessionEndDate()))){
							logger.info("Session Expired");
							logger.info("Current Academic Year Expired, Switching To New Academic Year");
							Integer rowsUpdated=session.update("switchAcademicYear");
							if(null==rowsUpdated || rowsUpdated<=0){
								logger.error("Year Switching Failed");
							}else{
								logger.info("Year Switching Successful");
								academicYearList=session.selectList("selectAcademicYearList");
									if(null==academicYearList || academicYearList.size()==0){
										throw new Exception();
								}
							}
						}else{
							logger.info("Session Not Expired");
						}
					}
				}
			}*/
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return academicYearList;
	}
	
	@Override
	public String editFees(Fees fees) throws CustomException {
		String updateStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("feesUpdate", fees);
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}		
		return updateStatus;
	}
	
	@Override
	public List<SocialCategory> getSocialCategoryList() throws CustomException {
		List<SocialCategory> socialCategoryList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			socialCategoryList=session.selectList("selectSocialCategoryList");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return socialCategoryList;
	}

	@Override
	public List<FeesTemplate> getFeesTemplateList() throws CustomException {
		List<FeesTemplate> feesTemplateList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			feesTemplateList=session.selectList("selectFeesTemplateList");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return feesTemplateList;
	}
	
	
	@Override
	public String addFeesTemplate(FeesTemplate feesTemplate) throws CustomException {
		String insertStatus = "Insert Successful";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			feesTemplate.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			
			session.insert("insertFeesTemplate", feesTemplate);
		}catch(Exception e) {
			insertStatus = "Insert Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	
	@Override
	public FeesTemplate getFeesTemplateDetails(String templateCode) throws CustomException {
		FeesTemplate feesTemplate = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			feesTemplate=session.selectOne("selectFeesTemplateDetails", templateCode);
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return feesTemplate;
	}
	
	@Override
	public List<Student> getStudentList() throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try{
			studentList = session.selectList("selectAllStudents");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return studentList;
	}
	
	@Override
	public Student getStudentListForEdit(Student student) {
		logger.info("In getStudentListForEdit() method of BackOfficeDAOImpl");
		Student studentDetails = null;
		Address presentAddress = null;
		Address permanentAddress = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			studentDetails = session.selectOne("selectStudentDetailsEdit", student);
			if (studentDetails != null) {
//				String hostelAvlStatus = session.selectOne("checkingRoomAvlStatus");
//				if (hostelAvlStatus != null) {
//					studentDetailsFromDB.setStatus(hostelAvlStatus);
//				}
				studentDetails.setFatherIncome(studentDetails.getFatherIncome());
				studentDetails.setMotherIncome(studentDetails.getMotherIncome());
				presentAddress = session.selectOne("selectPresentAddressEdit",student);
				permanentAddress = session.selectOne("selectPermanentAddressEdit", student);
				if (permanentAddress != null) {
					presentAddress.setPermanentAddressLine(permanentAddress.getPermanentAddressLine());
					presentAddress.setPermanentAddressCityVillage(permanentAddress.getPermanentAddressCityVillage());
					presentAddress.setPermanentAddressCountry(permanentAddress.getPermanentAddressCountry());
					presentAddress.setPermanentAddressDistrict(permanentAddress.getPermanentAddressDistrict());
					presentAddress.setPermanentAddressLandmark(permanentAddress.getPermanentAddressLandmark());
					presentAddress.setPermanentAddressPinCode(permanentAddress.getPermanentAddressPinCode());
					presentAddress.setPermanentAddressPoliceStation(permanentAddress.getPermanentAddressPoliceStation());
					presentAddress.setPermanentAddressPostOffice(permanentAddress.getPermanentAddressPostOffice());
					presentAddress.setPermanentAddressState(permanentAddress.getPermanentAddressState());
					List<State> stateList = session.selectList("selectStates",presentAddress.getPermanentAddressCountry());
					if (stateList != null && stateList.size() != 0) {
						presentAddress.setStateList(stateList);
					}
					List<Country> countryList = session.selectList("selectCountry");
					if (countryList != null && countryList.size() != 0) {
						presentAddress.setCountryList(countryList);
					}
					studentDetails.setAddress(presentAddress);
				}
//				List<BloodGroup> bloodGroupList = session.selectList("selectBloodGroup");
//				if (bloodGroupList != null && bloodGroupList.size() != 0) {
//					studentDetails.setBloodGroupList(bloodGroupList);
//				}
				List<SocialCategory> socialCategoryList = session.selectList("selectSocialCategory");
				if (socialCategoryList != null && socialCategoryList.size() != 0) {
					studentDetails.getResource().setSocialCategoryList(socialCategoryList);
				}
//				List<Organization> organizationList = session.selectList("selectPreviousSchoolEdit",student);
//				if (organizationList != null && organizationList.size() != 0) {
//					studentDetails.setOrganizationList(organizationList);
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in BackOfficeDaoImpl : method: getStudentListForEdit(Resource resource)  ", e);
		} finally {
			session.close();
		}
		return studentDetails;
	}
	
	
	@Override
	public List<Candidate> getFeesPaidCandidate() throws CustomException {
		List<Candidate> candidateList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			candidateList=session.selectList("selectFeesPaidCandidate");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return candidateList;
	}
	
	
	//from sms
	
	@Override
	public String createNewFinancialYear(FinancialYear financialYear) {
		String message = null;
		Utility util = new Utility();
		SqlSession session =sqlSessionFactory.openSession();
		try {
			Integer count=session.selectOne("numberOfCurrentFinancialYear");
			if(null !=count){
				if(count.intValue()==0){
					//System.out.println("in dao:::: start date::"+financialYear.getSessionStartDate()+"--end date::"+financialYear.getSessionEndDate()+"--name::"+financialYear.getFinancialYearName());
					financialYear.setFinancialYearObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
					financialYear.setFinancialYearCode(financialYear.getFinancialYearName()+"("+financialYear.getSessionStartDate()+"-"+financialYear.getSessionEndDate()+")");
					financialYear.setFinancialYearDesc("Financial Year "+financialYear.getFinancialYearName()+" Created By "+financialYear.getUpdatedBy());
					int status=session.insert("createNewFinancialYear", financialYear);
					if(status==0){
						message="failed";
					}else{
						message="created";
					}
				}else{
					message="exist";
				}
			}else{
				message="failed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In createNewFinancialYear(FinancialYear financialYear) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return message;
	}
	
	
	@Override
	public String updateFinancialYear(FinancialYear financialYear) {
		String message = null;	
		SqlSession session =sqlSessionFactory.openSession();
		try {
			int status=session.update("updateFinancialYear", financialYear);
			if(status==0){
					message="failed";
				}else{
					message="created";
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In createNewFinancialYear(FinancialYear financialYear) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return message;
	}
	
	
	@Override
	public List<Course> listCourse() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Course> courseListFromDB = null;
		try {
			courseListFromDB = session.selectList("fetchCourseList");
		} catch (Exception e) {
			logger.error(" Exception In  listCourse() of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return courseListFromDB;
	}
	
	
	@Override
	public void updateWorkingDaysForCheckedCloseDay(Term term) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			session.update("updateWorkingDaysForCheckedCloseDay", term);
			session.commit();
		} catch (Exception e) {
			logger.error("In updateWorkingDaysForCheckedCloseDay(Term term) of BackOfficeDAOImpl -",e);
		} finally {
			session.close();
		}
	}
	
	
	@Override
	public void insertHolidays(Term term) {
		Utility util = new Utility();
		List<Holiday> termHoliday = term.getHoliday();
		SqlSession session =sqlSessionFactory.openSession();
		try {
			/*System.out.println("***************BODAOI<insertHolidays()>****************");
			System.out.println("Academic Year :: "+term.getAcademicYear());
			System.out.println("Month :: "+term.getTermObjectId());
			System.out.println("No Of Working Days :: "+term.getNoOfWorkingDays());
			System.out.println("No Of Special Holi Days :: "+term.getNoOfWorkingDays());
			System.out.println("Holiday-1 ::"+term.getHoliDayOne());
			System.out.println("Holiday-2 ::"+term.getHoliDayTwo());
			System.out.println("Special Holidays...");
			for(Holiday hd : term.getHoliday()){
				System.out.println("Special Day :: "+hd.getSpecialHoliday());
				System.out.println("Holiday Mode :: "+hd.getMode());
			}
			System.out.println("*******************************");*/
			Holiday holiday = new Holiday();
			holiday.setUpdatedBy(term.getUpdatedBy());
			holiday.setYear(term.getAcademicYear());
			holiday.setMonth(term.getTermObjectId());
			holiday.setCompensatory(term.getTermStartDate());
			holiday.setSpecialHoliday(Integer.toString(term.getNoOfWorkingDays())); 
			holiday.setMode(Integer.toString(term.getCount()));
			holiday.setHolidayObjectId(util.getBase64EncodedID("MonthWiseHoliday"));
			holiday.setHolidayOne(term.getHoliDayOne());
			holiday.setHolidayTwo(term.getHoliDayTwo());
			int insertStatus = session.insert("insertHolidays", holiday);
			if(insertStatus != 0){
				if(termHoliday != null && termHoliday.size() !=0){
					for(Holiday holidayDetails : termHoliday) {
						holiday.setMode(holidayDetails.getMode());
						holiday.setSpecialHoliday(holidayDetails.getSpecialHoliday());
						session.insert("insertSpecialHolidays", holiday);
					}
				}
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in BackOfficeDaoImpl : method: insertHolidays(Term term)  ", e);
		} finally {
			session.close();
		}
	}
	
	
	@Override
	public List<Term> listTermHolidaysForShow() {
			SqlSession session =sqlSessionFactory.openSession();
			List<Term> holidaysList = null;
			List<Term> allHolidaysList = new ArrayList<Term>();
			try {
				holidaysList = session.selectList("fetchTotalHolidaysList");
				if(null != holidaysList && holidaysList.size() != 0){
					for(Term holidayDB : holidaysList){
						List<Holiday> specialHolidaysListDB = session.selectList("fetchSpecialHolidaysList", holidayDB);
						holidayDB.setHoliday(specialHolidaysListDB);
						allHolidaysList.add(holidayDB);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(" Exception In  listTermHolidaysForShow() of BackOfficeDaoImpl.java",e);
			} finally {
				session.close();
			}
	return allHolidaysList;
	}
	
	@Override
	public void updateWorkingDays(Term term) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			session.update("updateWorkingDays", term);
			session.commit();
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method: updateWorkingDays(Term term)  ", e);
		} finally {
			session.close();
		}
	}
	
	
	@Override
	public List<Term> listTermDetails() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Term> termDetailListFromDb = null;
		try {
			termDetailListFromDb = session.selectList("fetchTermList");
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method: listTermDetails()  ", e);
		} finally {
			session.close();
		}
	return termDetailListFromDb;
	}
	
	@Override
	public List<CalendarEvent> getlistSpecialHolidays() {
		List<CalendarEvent> specialHoliDays = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			specialHoliDays = session.selectList("fetchTermHolidays");
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method: getlistSpecialHolidays() ", e);
		} finally {
			session.close();
		}
		return specialHoliDays;
	}
	
	@Override
	public String getClassForAttendanceFromDB() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Resource> leaveeventFromDb = null;
		String output = null;
		try {
			leaveeventFromDb = session.selectList("fetchclassForAttendance");
			StringBuilder sb = new StringBuilder();
			for (Resource resource : leaveeventFromDb) {
				sb.append(resource.getKlass() + ",");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method: getClassForAttendanceFromDB() ", e);
		} finally {
			session.close();
		}
		return output;
	}
	
	
	@Override
	public String fetchTeacherId(StudentAttendance studentAttendance) {
		SqlSession session =sqlSessionFactory.openSession();
		List<StudentAttendance> teacherUid = null;
		String output = null;
		try {
			teacherUid = session.selectList("fetchTeacherIdForAttendance", studentAttendance);
			StringBuilder sb = new StringBuilder();
			for (StudentAttendance studentAttendance1 : teacherUid) {
				sb.append(studentAttendance1.getResourceId() + "~");
				sb.append(studentAttendance1.getAbsentDay() + "~");
				sb.append(studentAttendance1.getMonth() + "~");
				sb.append(studentAttendance1.getYear() + "~");
				sb.append(studentAttendance1.getAttendanceDetailsId() + "~");
				sb.append(studentAttendance1.getAttendanceDesc() + "&");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error(" Exception In fetchTeacherId(StudentAttendance studentAttendance) of BackOfficeDaoImpl.java", e);
		} finally {
			session.close();
		}
		return output;
	}
	
	@Override
	public String getTeachingLevgetTeacherDetailsForAttendanceelForAttendance(StudentAttendance studentAttendance) {
		List<Resource> teacherDetailsList = new ArrayList<Resource>();
		String Details = "";
		String Name = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			teacherDetailsList = session.selectList("getTeacherDetailsForAttendance", studentAttendance);
			for (Resource resource : teacherDetailsList) {
				if(resource.getMiddleName() != null){
					Name = resource.getFirstName() + " " + resource.getMiddleName() + " " + resource.getLastName();
				}
				else{
					Name = resource.getFirstName() + " " + resource.getLastName();
				}
				Details = resource.getCode() + "/" + Name + "@@@" + Details;
			}
		} catch (Exception e) {
			logger.error(" Exception In getTeachingLevgetTeacherDetailsForAttendanceelForAttendance(StudentAttendance studentAttendance) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return Details;
	}
	
	
	@Override
	public String getCourseForAttendance(String className) {

		SqlSession session =sqlSessionFactory.openSession();
		List<Course> leaveeventFromDb = null;
		String output = null;
		try {
			leaveeventFromDb = session.selectList("fetchCourseForAttendance", className);
			StringBuilder sb = new StringBuilder();
			for (Course course : leaveeventFromDb) {
				sb.append(course.getCourseCode() + ",");
				sb.append(course.getCourseName() + "@");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error(" Exception In getCourseForAttendance(String className) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return output;
	}
	
	
//	@Override
//	public String getStreamForAttendanceFromDB(String streamlist) {
//		SqlSession session =sqlSessionFactory.openSession();
//		List<Stream> streams = null;
//		String output = null;
//		try {
//			streams = session.selectList("fetchStreamForAttendance", streamlist);
//			StringBuilder sb = new StringBuilder();
//			for (Stream stream : streams) {
//				sb.append(stream.getStreamName() + ",");
//				output = sb.toString().substring(0, sb.toString().length() - 1);
//			}
//		} catch (Exception e) {
//			logger.error("Exception in BackOfficeDaoImpl : method: getStreamForAttendanceFromDB(String streamlist) ", e);
//		} finally {
//			session.close();
//		}
//		return output;
//	}
	
	
	@Override
	public String getSectionForAttendanceFromDB(Resource resource) {		
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> section = null;
		String output = null;
		try {
			section = session.selectList("fetchSectionForAttendance", resource);
			StringBuilder sb = new StringBuilder();
			for (Resource resourceone : section) {
				sb.append(resourceone.getSection().getSectionName() + ",");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return output;
	}
	
	@Override
	public String fetchStudentIdFromDB(StudentAttendance studentAttendance) {
		SqlSession session =sqlSessionFactory.openSession();
		List<StudentAttendance> stuid = null;
		String output = null;
		try {
			//System.out.println("2201in backofcDAoimpl::Month:"+studentAttendance.getMonth()+"year:"+studentAttendance.getYear());
			stuid = session.selectList("fetchStudentIdForAttendance",studentAttendance);
			//System.out.println("2202in backofcDaoimpl:size:"+stuid.size());
			StringBuilder sb = new StringBuilder();
			for (StudentAttendance studentAttendance1 : stuid) {
				sb.append(studentAttendance1.getStudentId() + "~");
				sb.append(studentAttendance1.getAbsentDay() + "~");
				sb.append(studentAttendance1.getMonth() + "~");
				sb.append(studentAttendance1.getYear() + "~");
				sb.append(studentAttendance1.getAttendanceDesc() + "#");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		//System.out.println("2216 in backofcDAoimpl:"+output);
		return output;
	}
	
	
	@Override
	public String getStudentsForAttendanceFromDB(Resource resource) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> students = null;
		String output = null;
		try {
			students = session.selectList("fetchStudentsForAttendance",resource);
			StringBuilder sb = new StringBuilder();
			for (Resource resourceone : students) {
				sb.append(resourceone.getFirstName() + ",");
				sb.append(resourceone.getMiddleName() + ",");
				sb.append(resourceone.getLastName() + ",");
				/*sb.append(resourceone.getName()+",");*/
				sb.append(resourceone.getUserId() + "@");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return output;
	}
	
	
	@Override
	public String getDateOfCreationForInsertedStudent(String month, String year, String studentId) {
		//System.out.println("in 2288 daoimpl::"+studentId);
		SqlSession session =sqlSessionFactory.openSession();
		String attendanceDateOfCreationStr =  "";
		StudentAttendance studentAttendance = new StudentAttendance();
		studentAttendance.setMonth(month);
		studentAttendance.setYear(year);
		//System.out.println("line 2293 in daoimpl:: month::"+studentAttendance.getMonth()+"\t year::"+studentAttendance.getYear());
		studentAttendance.setStudentId(studentId);
		//int x = Integer.parseInt(studentId);
		studentAttendance.setStudentRollNo(studentId);
		try {
			//System.out.println("line 2296 in daoimpl:: student roll::"+studentAttendance.getStudentRollNo()+"\t of month::"+studentAttendance.getMonth()+"\t year::"+studentAttendance.getYear());
			StudentAttendance attendanceDateOfCreation =  session.selectOne("fetchStudentsAttendanceDateOfCreation",studentAttendance);
			//System.out.println("line 2297::"+attendanceDateOfCreation);	
			if(attendanceDateOfCreation != null){
				attendanceDateOfCreationStr = attendanceDateOfCreation.getAttendanceDesc();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return attendanceDateOfCreationStr;
	}
	
	
	@Override
	public String getDateOfCreationForInsertedResource(String month,
			String year, String userId, String shift,String resourceType) {
		SqlSession session =sqlSessionFactory.openSession();
		String attendanceDateOfCreationStr =  "";
		StudentAttendance studentAttendance = new StudentAttendance();
		WorkShift workShift = new WorkShift();
		ResourceType resourceTypeObj = new ResourceType();
		resourceTypeObj.setResourceTypeName(resourceType);
		workShift.setWorkShiftName(shift);
		studentAttendance.setMonth(month);
		studentAttendance.setYear(year);
		studentAttendance.setResourceId(userId);
		studentAttendance.setWorkShift(workShift);
		studentAttendance.setResourceType(resourceTypeObj);
		
		try {
			StudentAttendance attendanceDateOfCreation =  session.selectOne("fetchResourceAttendanceDateOfCreation",studentAttendance);
			if(attendanceDateOfCreation != null){
			attendanceDateOfCreationStr = attendanceDateOfCreation.getAttendanceDesc();
			}
		}
		catch (Exception e) {
			logger.error(" Exception In getDateOfCreationForInsertedResource(String month, String year, String userId, String shift,String resourceType) of BackOfficeDaoImpl.java", e);
		} finally {
			session.close();
		}
		return attendanceDateOfCreationStr;
	}
	
	
	@Override
	public void updateStudentAttendanceFromDB(StudentAttendance studentAttendance) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			session.delete("updateStudentAttendance", studentAttendance);
			session.commit();
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method: updateStudentAttendanceFromDB(StudentAttendance studentAttendance) ", e);
		} finally {
			session.close();
		}
	}
	
	
	@Override
	public void insertStudentAttendanceFromDB(StudentAttendance studentAttendance) {
		int insertStatus = 1;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			studentAttendance.setAttendanceCode("ANUP");
			studentAttendance.setAttendanceObjectId(encryptDecrypt.encrypt("BackOfficeDAOImpl"));
			studentAttendance.setStudentRollNo(studentAttendance.getStudentRollNo());
			System.out.println("in daoimpl: line 2446::  studentid::"+studentAttendance.getStudentRollNo());
			session.insert("insertStudentAttendance", studentAttendance);
			session.commit();
		} catch (Exception e) {
			insertStatus = 0;
			e.printStackTrace();
			logger.error("Exception in BackOfficeDaoImpl : method: insertStudentAttendanceFromDB(StudentAttendance studentAttendance) ", e);
		} finally {
			session.close();
		}
	}
	
	
	@Override
	public void updateTeacherAttendance(StudentAttendance studentAttendanceObject) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			session.delete("updateTeacherAttendance",studentAttendanceObject);
			session.commit();
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl: method: updateTeacherAttendance(StudentAttendance studentAttendanceObject)", e);
		} finally {
			session.close();
		}
	}
	
	@Override
	public void insertTeacherAttendance(
			StudentAttendance studentAttendanceObject) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			Utility util = new Utility();
			studentAttendanceObject.setAttendanceCode(util.getBase64EncodedID("BackOfficeDAOImpl"));
			studentAttendanceObject.setAttendanceObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertTeacherAttendance", studentAttendanceObject);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
	@Override
	public String getWorkShiftForAttendance() {
		List<WorkShift> workShiftList = new ArrayList<WorkShift>();
		String WorkShiftDetails = "";		
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			workShiftList = session.selectList("getWorkShiftListForAttendance");
			for (WorkShift workShift : workShiftList) {
				WorkShiftDetails = workShift.getWorkShiftCode() + "#" + workShift.getWorkShiftName() + "@"	+ WorkShiftDetails;
			}
		} catch (Exception e) {
			logger.error(" Exception In getWorkShiftForAttendance() of BackOfficeDaoImpl.java", e);
		} finally {
			session.close();
		}
		return WorkShiftDetails;
	}
	
	
	@Override
	public String fetchTeacherAttendance(ResourceAttendance resourceAttendance) {
		SqlSession session =sqlSessionFactory.openSession();
		List<ResourceAttendance> teacherUid = null;
		String output = null;
		try {
			teacherUid = session.selectList("fetchTeacherAttendance", resourceAttendance);
			StringBuilder sb = new StringBuilder();
			for (ResourceAttendance resourceAttendance1 : teacherUid) {
				sb.append(resourceAttendance1.getResourceId() + "~");
				sb.append(resourceAttendance1.getAttendanceDay() + "~");
				sb.append(resourceAttendance1.getMonth() + "~");
				sb.append(resourceAttendance1.getYear() + "~");
				sb.append(resourceAttendance1.getAttendanceDetailsId() + "~");
				sb.append(resourceAttendance1.getSwipeTimeSlot() + "~");
				sb.append(resourceAttendance1.isAttendanceFlag() + "&");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error("fetchTeacherAttendance(ResourceAttendance resourceAttendance) In BackOfficeDaoImpl.java: Exception",e);
		} finally {
			session.close();
		}
		return output;
	}
	
	
	@Override
	public LibraryPolicy showLibraryPolicy(LibraryPolicy libraryPolicy) {
		LibraryPolicy lpDB = new LibraryPolicy();
		SqlSession session =sqlSessionFactory.openSession();
		try {			
				lpDB = session.selectOne("getLibraryPolicy",libraryPolicy);
				if (lpDB == null) {
					libraryPolicy.setMaxNoOfBookReq(0);
					libraryPolicy.setMaxNoOfBooksPerReq(0);

					List<Rating> ratingList = new ArrayList<Rating>();
					for (int i = 0; i <= 10; i++) {
						Rating r = new Rating();
						r.setRating(i);
						r.setMaxLendingPeriod(0);
						ratingList.add(r);
					}
					libraryPolicy.setRatingList(ratingList);
					libraryPolicy.setSameBookAcrossReq(0);

					libraryPolicy.setFinePerDay(0.00);
					libraryPolicy.setMaxFine(0.00);
					libraryPolicy.setOverDueFine(0.00);
					libraryPolicy.setProcessingFee(0.00);
					lpDB = libraryPolicy;
				} else {
					List<Rating> rl = session.selectList("getLibraryPolicyRating",libraryPolicy);
					lpDB.setRatingList(rl);
				}
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			session.close();
		}
		return lpDB;
	}
	
	
	@Override
	public LibraryPolicy saveLibraryPolicy(LibraryPolicy libraryPolicy) {
		LibraryPolicy lpDB = new LibraryPolicy();
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			lpDB = session.selectOne("getLibraryPolicy",libraryPolicy);
			if (lpDB != null) {
				session.update("updateLibraryPolicy", libraryPolicy);
				for (Rating r : libraryPolicy.getRatingList()) {
					ResourceType rt = new ResourceType();
					rt.setResourceTypeName(libraryPolicy.getResourceType().getResourceTypeName());
					r.setResourceType(rt);
					session.update("updateLibraryPolicyRating", r);
				}
			} else {
				Utility util = new Utility();
				libraryPolicy.setLibraryPolicyObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
				int insertedFlag = session.insert("insertLibraryPolicy", libraryPolicy);
				if(insertedFlag != 0){
					for (Rating r : libraryPolicy.getRatingList()) {
						r.setLibraryPolicyId(libraryPolicy.getLibraryPolicyId());
						r.setRatingObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
						session.update("insertLibraryPolicyRating", r);
					}
				}
			}
			session.commit();
			lpDB = session.selectOne("getLibraryPolicy",libraryPolicy);
			List<Rating> rl = session.selectList("getLibraryPolicyRating");
			lpDB.setRatingList(rl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lpDB;
	}
	
	
	
	@Override
	public List<AcademicYear> getCurrentAcademicYear() {
		List<AcademicYear> academicYearFromDB = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			academicYearFromDB = session.selectList("selectCurrentAcademicYear");
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method: getAcademicYearList()  ", e);
		} finally {
			session.close();
		}
		return academicYearFromDB;
	}
	
	@Override
	public List<Rating> getRatingPolicy(Rating rating) {
		List<Rating> ratingList = new ArrayList<Rating>();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Rating rat = session.selectOne("getRatingPolicyCount",rating);
			if (rat.getRating() == 0) {
				for (int i = 0; i <= 10; i++) {
					Rating r = new Rating();
					r.setRating(i);
					r.setMinLendingsTo(0);
					r.setMinLendingsFrom(0);
					r.setMonths(0);
					ratingList.add(r);
				}
			} else
				ratingList = session.selectList("getRatingPolicy",rating);
		} catch (Exception e) {
			logger.error(" Exception In getRatingPolicy() of BackOfficeDaoImpl.java",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ratingList;
	}
	
	
	@Override
	public List<Rating> saveRatingPolicy(List<Rating> ratingList) {
		List<Rating> rl = new ArrayList<Rating>();
		int updateFlag = 0;
		SqlSession session = sqlSessionFactory.openSession();
		Rating rating = new Rating();
		try {
			Utility util = new Utility();
			for (Rating r : ratingList) {
				rating.setAcademicYear(r.getAcademicYear());
			}
			Rating rat = session.selectOne("getRatingPolicyCount",rating);
			if (rat.getRating() == 0) {
				// insert
				for (Rating r : ratingList) {
					r.setRatingObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
					session.insert("saveRatingPolicy", r);
				}
			} else {
				// update
				for (Rating r : ratingList) {
					r.setRatingObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
					updateFlag = session.update("updateRatingPolicy", r);
				}
				if(updateFlag != 0){
					//System.out.println("update successfully");
					for (Rating r : ratingList) {
						r.setRatingObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
						session.insert("saveRatingPolicy", r);
					}
				}
			}
			rl = session.selectList("getRatingPolicy",rating);
		} catch (Exception e) {
			logger.error(" Exception In saveRatingPolicy() of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return rl;
	}
	
	
	@Override
	public VendorRatingPolicy showVendorPolicy(VendorRatingPolicy vendorRatingPolicy) {
		VendorRatingPolicy vrp = new VendorRatingPolicy();
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			vrp = session.selectOne("getVendorPolicy",vendorRatingPolicy);
				if (vrp == null) {
					vendorRatingPolicy.setMaxSupplyDay(0);
					vendorRatingPolicy.setMaxNoDeffects(0);
					vrp = vendorRatingPolicy;
				}
		} catch (Exception e) {
			logger.error(" Exception In showVendorPolicy(VendorRatingPolicy vendorRatingPolicy) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return vrp;
	}
	
	
	@Override
	public VendorRatingPolicy saveVendorPolicy(VendorRatingPolicy vendorRatingPolicy) {
		VendorRatingPolicy vrp = new VendorRatingPolicy();
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			vrp = session.selectOne("getVendorPolicy",vendorRatingPolicy);
			if (vrp != null) {
				session.update("updateVendorPolicy", vendorRatingPolicy);
			} else {
				Utility util = new Utility();
				vendorRatingPolicy.setVendorRatingPolicyObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
				int insertedFlag = session.insert("insertVendorPolicy", vendorRatingPolicy);
			}
			session.commit();
			vrp = session.selectOne("getVendorPolicy",vendorRatingPolicy);
		} catch (Exception e) {
			logger.error(" Exception In saveVendorPolicy(vendorRatingPolicy) of BackOfficeDaoImpl.java",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vrp;
	}
	
	
	@Override
	public AttendancePolicy getAttendancePloicy() {
		AttendancePolicy attendancePolicy = new AttendancePolicy();
		SqlSession session =sqlSessionFactory.openSession();
		try {
			attendancePolicy = session.selectOne("getAttendancePolicy");
			if (attendancePolicy == null) {
				AttendancePolicy at = new AttendancePolicy();
				at.setLimitationOfDay(0);
				at.setOverTime(null);
				at.setCompensation(null);
				attendancePolicy = at;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return attendancePolicy;
	}
	
	
	@Override
	public void saveAttendancePolicy(AttendancePolicy attendancePolicy) {
		AttendancePolicy attendance = new AttendancePolicy();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			attendance = session.selectOne("getAttendancePolicy");
			Utility util = new Utility();
			attendancePolicy.setAttendancePolicyObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
			if(attendance == null){
			    session.insert("insertAttendancePolicy", attendancePolicy);
			}
			if(attendance != null){
				attendancePolicy.setAttendancePolicyId(attendance.getAttendancePolicyId());
				session.update("updateAttendancePolicy", attendancePolicy);
				session.insert("insertAttendancePolicy", attendancePolicy);
			}
			session.commit();
		} catch (Exception e) {
			logger.error(" Exception In saveAttendancePolicy(AttendancePolicy attendancePolicy) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
	}
	
	
	@Override
	public Exam getExamPloicy() {
		Exam examPolicy = new Exam();
		SqlSession session =sqlSessionFactory.openSession();
		try {
			examPolicy = session.selectOne("getExamPolicy");
			if (examPolicy == null) {
				Exam ex = new Exam();
				ex.setChangeDay(0);
				examPolicy = ex;
				}
		} catch (Exception e) {
			logger.error(" Exception In getExamPloicy() of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return examPolicy;
	}
	
	
	@Override
	public void saveExamPolicy(Exam exam) {
		Exam examPolicy = new Exam();
		SqlSession session =sqlSessionFactory.openSession();
		try {
			examPolicy = session.selectOne("getExamPolicy");
			if(examPolicy == null){
			Utility util = new Utility();
			exam.setObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
			//System.out.println("in dao2789::"+exam.getChangeDay());
			session.insert("insertExamPolicy", exam);
			}
			if(examPolicy != null){
				session.update("updateExamPolicy", exam);
			}
			session.commit();
		} catch (Exception e) {
			logger.error(" Exception In saveExamPolicy(Exam exam) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
	}
	
	
	@Override
	public AcademicYear getAcademicYearClassAndExamType() {
		
		AcademicYear academicYear = new AcademicYear();
		SqlSession session =sqlSessionFactory.openSession();
		try {
			academicYear = session.selectOne("getCurrentAcademicYear");
			List<Standard> classList = session.selectList("getAllClass");
			/*List<ExamType> examTypeList = session.selectList("getAllExamType");
			if (examTypeList != null && examTypeList.size() != 0 && classList != null && classList.size() != 0)
				classList.get(0).setExamTypeList(examTypeList);*/
			if (classList != null && classList.size() != 0)
				academicYear.setClassList(classList);
		} catch (Exception e) {
			logger.error(" Exception In  getAcademicYearClassAndExamType() of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return academicYear;
	}
	
	@Override
	public String getCourseInClass(String classCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String sm = "";
		try {
			List<Course> courseList = session.selectList("getCourseInClass", classCode);
			if (courseList != null && courseList.size() != 0)
				for (Course c : courseList)
					sm = sm + 
					c.getCourseCode() + "*~*" + 
					c.getCourseName() + "~*~";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sm;
	}
	
	
	@Override
	public String getTermForCourse(String courseCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String sm = "";
		try {
			List<Term> termList = session.selectList("getTermForCourse", courseCode);

			if (termList != null && termList.size() != 0)
				for (Term t : termList)
					sm = sm + t.getTermCode() + "*~*" + t.getTermName() + "~*~";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sm;
	}
	
	
	@Override
	public String getExamsForTermCourseAndExamType(Exam ex) {
		String sm = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			List<Exam> examList = session.selectList("getExamsForTermCourseAndExamType", ex);
			if (examList != null && examList.size() != 0)
				for (Exam exam : examList) {
					sm = sm + exam.getExamCode() + "*~*" + exam.getExamName()+ "~*~";
				}
		} catch (Exception e) {
			logger.error(" Exception In  getExamsForTermCourseAndExamType(Exam ex) of BackOfficeDaoImpl.java",e);
		}
		return sm;
	}
	
	
	@Override
	public String getSectionForClassAndStream(Class klass) {
		String sm = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			List<Section> sectionList = session.selectList("getSectionForClassAndStream", klass);
			if (sectionList != null && sectionList.size() != 0)
				for (Section s : sectionList) {
					sm = sm + s.getSectionCode() + "*~*" + s.getSectionName()+ "~*~";
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In  getSectionForClassAndStream(Class klass) of BackOfficeDaoImpl.java",e);
		}
		return sm;
	}
	
	
	@Override
	public String getStudentForClassStreamSectionAndCourse(Resource resource) {
		String sm = "";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			List<Resource> resourceList = session.selectList("getStudentForClassStreamSectionAndCourse", resource);
			if (resourceList != null && resourceList.size() != 0)
				for (Resource r : resourceList) {
					sm = sm + r.getUserId() + "*~*" + r.getResourceName()+ "~*~";
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In  getStudentForClassStreamSectionAndCourse(Resource resource) of BackOfficeDaoImpl.java",e);
		}
		return sm;
	}
	
	
	@Override
	public List<BookForQRCode> readBookDataToGenerateQRCode() {
		List<BookForQRCode> bookForQRCodeListFinal = new ArrayList<BookForQRCode>();
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			List<BookForQRCode> bookForQRCodeList = session.selectList("readBookDataToGenerateQRCode");
			
			//System.out.println(bookForQRCodeList.size()+"****************");
			for(BookForQRCode bookForQRCode:bookForQRCodeList){
				String author="";
				List<String> authorList=session.selectList("readBookAuthorListForQr", bookForQRCode.getBookCommonCode());
				for(String s:authorList){
					author=author+" "+s+",";
				}
				author = author.substring(0, author.length()-1);
				bookForQRCode.setBookAuthor(author);
				bookForQRCodeListFinal.add(bookForQRCode);
			}
		} catch (Exception e) {
			logger.error(" Exception In readBookDataToGenerateQRCode() of BackOfficeDaoImpl.java", e);
		} finally {
			session.close();
		}
		return bookForQRCodeListFinal;
	}
	
	
	@Override
	public void updateBookIdForQRCode(String bookIndividualCode) {
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			session.update("updateBookIdForQRCode",bookIndividualCode);
		} catch (Exception e) {
			logger.error(" Exception In updateBookIdForQRCode(String bookIndividualCode) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
	}
	
	@Override
	public List<StudentForQRCode> readStudentDataToGenerateQRCode() {
		List<StudentForQRCode> studentForQRCode = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			studentForQRCode = session.selectList("readStudentDataToGenerateQRCode");
			//System.out.println("student for qrcode::"+studentForQRCode);
		} catch (Exception e) {
			logger.error(" Exception In readStudentDataToGenerateQRCode() of BackOfficeDaoImpl.java", e);
		} finally {
			session.close();
		}
		return studentForQRCode;
	}
	
	
	@Override
	public List<TeacherForQRCode> readTeacherDataToGenerateQRCode() {
		List<TeacherForQRCode> teacherForQRCodeList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			teacherForQRCodeList = session.selectList("readTeacherDataToGenerateQRCode");
		} catch (Exception e) {
			logger.error(" Exception In readTeacherDataToGenerateQRCode() of BackOfficeDaoImpl.java", e);
		} finally {
			session.close();
		}
		return teacherForQRCodeList;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public AcademicYear selectCurrentAcademicYear() {
		AcademicYear aY = new AcademicYear();
		SqlSession session =sqlSessionFactory.openSession();
		try {
			aY = session.selectOne("selectCurrentAcademicYear");
		} catch (Exception e) {
			logger.error(" Exception In  selectCurrentAcademicYear() of BackOfficeDaoImpl.java",e);
		}
		return aY;
	}
	
	
	@Override
	public List<Class> getClassNameAndCodeList() {
		List<Class> classList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			classList = session.selectList("getClassNameAndCodeList");
		} catch (Exception e) {
			logger.error(" Exception In  getClassNameAndCodeList() of BackOfficeDaoImpl.java",e);
		}
		return classList;
	}
	
	@Override
	public String getSectionAgainstClassAndStream(Section section) {
		String sm = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			List<Section> sectionList = session.selectList("getSectionAgainstClassAndStream", section);

			if (sectionList != null && sectionList.size() != 0)
				for (Section s : sectionList) {
					sm = sm + s.getSectionCode() + "*" + s.getSectionName()+ "~";
				}
		} catch (Exception e) {
			logger.error(" Exception In  getSectionAgainstClassAndStream(Section section) of BackOfficeDaoImpl.java",e);
		}
		return sm;
	}
	
	
	@Override
	public String getResourceAgainstSection(Section sectionCode, String course) {
		String sm = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			//System.out.println(""+course +"******** "+sectionCode);
			Resource resource = new Resource();
			resource.setCourseName(course);
			resource.setSection(sectionCode);
			List<Resource> resourceList = session.selectList("getResourceAgainstSection", resource);

			if (resourceList != null && resourceList.size() != 0)
				for (Resource r : resourceList) {
					sm = sm + r.getRegistrationId() + "*" + r.getName() + "~";
			}
		} catch (Exception e) {
			logger.error(" Exception In  getResourceAgainstSection(String sectionCode) of BackOfficeDaoImpl.java",e);
		}
		return sm;
	}
	
	
	@Override
	public String checkWheatherFeesPaid(SessionFees sf) {
		String sm = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			sf = session.selectOne("checkWheatherFeesPaid", sf);
			if (sf != null) {
				if (sf.getPayingAmount() >= sf.getNetTotAmount()) {
					sm = "Fees Clear";
				} else {
					sm = "Fees Not Clear";
				}
			} else {
				sm = "Fees Not Clear";
			}
		} catch (Exception e) {
			logger.error(" Exception In  checkWheatherFeesPaid(SessionFees sf) of BackOfficeDaoImpl.java",e);
		}
		return sm;
	}
	
	@Override
	public String grantTC(StudentTc studentTc) {
		String status="TC Granted Successfully.";
		try{
			SqlSession session =sqlSessionFactory.openSession();
			Utility utility = new Utility();
			studentTc.setObjectId(utility.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("grantTC", studentTc);
			session.commit();
		}catch(Exception e){
			logger.error("Exception in grantTC(StudentTc studentTc) method in BackOfficeDaoImpl: ",e);
			status="TC Granting Failed.";
		}
		return status;
	}
	
	@Override
	public List<String> getBookIdForQrCode(String strQuery) {
		List<String> bookIdList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			bookIdList = session.selectList("getBookIdForQrCode", strQuery);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getBookIdForQrCode(String strQuery) method in BackOfficeDaoImpl Exception",e);
		}
		return bookIdList;
	}
	
	
	@Override
	public String getTermDate(String termid) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Term> editTermFromDb = null;
		String output = null;
		try {
			editTermFromDb = session.selectList("fetchSelectiveTerm", termid);
			Term countPublicHoliday = session.selectOne("countPublicHoli", termid);
			Term countCompensatoryHoliday = session.selectOne("countCompensatoryHoli", termid);
			int calculation = countPublicHoliday.getTermDetailsId() - countCompensatoryHoliday.getCount();
			StringBuilder sb = new StringBuilder();
			for (Term term : editTermFromDb) {
				sb.append(term.getTermStartDate() + ",");
				sb.append(term.getTermEndDate() + ",");
				sb.append(term.getNoOfWorkingDays() + ",");
				sb.append(term.getHoliDayOne() + ",");
				sb.append(term.getHoliDayTwo() + ",");
				sb.append(calculation + ",");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return output;
	}
	
	
	@Override
	public String getTermList(String courseCode) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Term> termList = null;
		String output = null;
		try {
			termList = session.selectList("fetchTermListForClass", courseCode);
			StringBuilder sb = new StringBuilder();
			for (Term term : termList) {
				sb.append(term.getTermCode() + "^");
				sb.append(term.getTermName() + "#");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error(" Exception In  getTermList(String courseCode) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return output;
	}
	
	@Override
	public List<Scholarship> getScholarshipList() throws CustomException {
		List<Scholarship> scholarshipList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			scholarshipList=session.selectList("selectScholarshipList");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return scholarshipList;
	}

	@Override
	public String addScholarship(Scholarship scholarship) throws CustomException {
		String insertStatus="Insert Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			scholarship.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertScholarship", scholarship);
		}catch(Exception e) {
			insertStatus="Insert Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String editScholarship(Scholarship scholarship) throws CustomException {
		String updateStatus="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("updateScholarship", scholarship);
		}catch(Exception e) {
			updateStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}		
		return updateStatus;
	}
	
	@Override
	public Student getCandidateDetailsAgainstFromId(Resource resource) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		Student student = null;
		try{
			String courseId = resource.getCourseName();
			String admissionMode = session.selectOne("selectAdmissionModeForACourse", courseId);
			if(admissionMode.equalsIgnoreCase("ONLINE")){
				student=session.selectOne("selectCandidateDetailsAgainstFromId",resource);
			}else{
				student=session.selectOne("selectCandidateDetailsAgainstFromIdForOffline",resource);
			}
		
//			if(null != student){
//				if(null!=student.getStandard() && student.getStandard() != ""){
//					subjectList=session.selectList("selectSubjectsForStandard", student.getStandard());
//				}
//				student.setSubjectList(subjectList);
//	       }
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return student;
	}

	

	@Override
	public Student getStudentDetails(String rollNumber) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		Student student = null;
		Address address = null;
		List<Subject> subjectList=null;
		try{
			student = session.selectOne("selectStudentDetails",rollNumber);
			if(null != student){
				address = session.selectOne("selectStudentAddress", rollNumber);
				student.setAddress(address);
				
//				if(null!=student.getStandard() && student.getStandard() != ""){
//					subjectList=session.selectList("selectSubjectsForStandard", student.getStandard());
//				}
//				student.setSubjectList(subjectList);
			}
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return student;
	}



@Override
	public String editStudent(Student student) throws CustomException {
		String updateStatus = "Update Successful";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			student.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			
			session.update("updateStudent", student);
			
			student.getAddress().setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
		//	student.getAddress().setRollNumber(student.getRollNumber());			
			student.getAddress().setUserId(student.getResourceUserId());
			
			student.getAddress().setAddressType("PRESENT");
			session.insert("updateStudentPresentAddress", student.getAddress());
			
			student.getAddress().setAddressType("PERMANENT");
			session.insert("updateStudentPermananentAddress", student.getAddress());
			
//			student.getAddress().setAddressType("LOCAL GUARDIAN");
//			session.insert("updateStudentLocalGuardianAddress", student.getAddress());
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			updateStatus="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
			e.printStackTrace();
		}finally{
			session.close();
		}		
		return updateStatus;
	}

	@Override
	public List<AcademicYear> getAcademicYearForTerm() {
		List<AcademicYear> academicYearFromDB = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			academicYearFromDB = session.selectList("selectCurrentAcademicYear");
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method: getAcademicYearList()  ", e);
		} finally {
			session.close();
		}
		return academicYearFromDB;
	}
	
	@Override
	public List<Term> getTermNameList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Term> termNameListFromDb = null;
		try {
			termNameListFromDb = session.selectList("selectTerm");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return termNameListFromDb;
	}
	
	
	@Override
	public void insertAcademicTermFromDB(Term term) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			Utility util = new Utility();
			logger.info("In BackOfficeDAOImpl insertAcademicTermFromDB(Term term)");

			term.setTermObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertAcademicTerm", term);
			session.commit();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Override
	public void editAcademicTermTypeFromDB(Term term) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("In BackOfficeDAOImpl editAcademicTermTypeFromDB(Term term)");
			session.update("updateAcademicTerm", term);
			session.commit();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Override
	public List<Term> specifictermforsingle(Term term) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Term> editTermFromDb = null;
		try {
			editTermFromDb = session.selectList("fetchSelectiveTermforupdate",term);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return editTermFromDb;
	}
	
	
	@Override
	public List<Holiday> listspeHolidays(Holiday holi) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Holiday> editTermFromDb = null;
		try {
			editTermFromDb = session.selectList("fetchHolidaybyId", holi);
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method: listspeHolidays(Holiday holi) ", e);
		} finally {
			session.close();
		}
		return editTermFromDb;
	}
	
	@Override
	public List<Standard> getclassListForTermCreation() {
		List<Standard> classList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {			
			classList = session.selectList("getListClassForTermCreation");
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return classList;
	}
	
	@Override
	public List<Course> getcourseListForTermCreationFromDB(String strClass) {
		List<Course> courseListFromDB = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			Standard standard = new Standard();					
			standard.setStandardName(strClass);			
			courseListFromDB = session.selectList("getCourseForTermCreation",standard);
		} catch (Exception e) {
			logger.error(" Exception In getcourseListForTermCreationFromDB(String currentYear,String strClass) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return courseListFromDB;
	}

	
	@Override
	public void updatePublicHoliday(Holiday holiday) {
		logger.info("In updatePublicHoliday() DaoImpl");
		SqlSession session =sqlSessionFactory.openSession();
		try {
			session.update("updateHoliDays", holiday);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in BackOfficeDaoImpl : method: updatePublicHoliday(Holiday holiday)  ", e);
		} finally {
			session.close();
		}
	}
	
//----------------FOR TIME TABLE BY SAIKAT DAS-------------------
	
	@Override
	public List<Resource> getTeacherList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Resource> teacherList = null;
		try {
			logger.info("getTeacherList() method in BackOfficeDaoImpl");			
			teacherList = session.selectList("getTeacherList");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTeacherList() method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		return teacherList;
	}
	
	@Override
	public List<Standard> getStandardList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Standard> standardList = null;
		try {
			logger.info("getStandardList() method in BackOfficeDaoImpl");			
			standardList = session.selectList("getStandardList");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStandardList() method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		return standardList;
	}
	
	@Override
	public List<Subject> getSubjectList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Subject> subjectList = null;
		try {
			logger.info("getSubjectList() method in BackOfficeDaoImpl");			
			subjectList = session.selectList("getSubjectList");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getSubjectList() method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		return subjectList;
	}
	
	@Override
	public List<TimeTableGridData> getAllTimeTableGridData(){
		SqlSession session = sqlSessionFactory.openSession();
		List<TimeTableGridData> gridDataList = null;
		
		try {
			logger.info("getAllTimeTableGridData() method in BackOfficeDaoImpl");			
			gridDataList = session.selectList("getAllGridDataList");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllTimeTableGridData() method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		
		return gridDataList;
	}
	
	@Override
	public TimeTableGridData getTimeTableGridData(TimeTableGridData timeTableGridData){
		SqlSession session = sqlSessionFactory.openSession();
		TimeTableGridData timeTableGridDataDB = null;
		
		try {
			logger.info("getTimeTableGridData() method in BackOfficeDaoImpl");		
			timeTableGridDataDB = session.selectOne("getGridData", timeTableGridData);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTimeTableGridData() method in BackOfficeDaoImpl", e);
		}
		
		return timeTableGridDataDB;
	}
	
	@Override
	public void insertTimeTableGridData(TimeTableGridData timeTableGridData){
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			logger.info("insertTimeTableGridData() method in BackOfficeDaoImpl");		
			session.insert("insertGridData" , timeTableGridData);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("insertTimeTableGridData() method in BackOfficeDaoImpl", e);
		}
		
	}
	
	@Override
	public void updateTimeTableGridData(TimeTableGridData timeTableGridData){
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			logger.info("updateTimeTableGridData() method in BackOfficeDaoImpl");		
			session.update("updateGridData" , timeTableGridData);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateTimeTableGridData() method in BackOfficeDaoImpl", e);
		}
		
	}
	
	@Override
	public List<TimeTableConfigModel> addTimeTableConfigData(List<TimeTableConfigModel> timeTableConfigModelList, String updatedBy){
		SqlSession session = sqlSessionFactory.openSession();
		List<TimeTableConfigModel> timeTableModelListDB = null;
		try {
			for (TimeTableConfigModel timeTableConfigModel : timeTableConfigModelList) {
				session.insert("insertTimeTableConfigData", timeTableConfigModel);
				session.commit();
			}
			timeTableModelListDB = session.selectList("getAllTimeTableConfigDataList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeTableModelListDB;
	}
	
	
	@Override
	public List<TimeTableConfigModel> getAllTimeTableList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<TimeTableConfigModel> timeTableList = null;
		try {
			timeTableList = session.selectList("viewAllTimeTable");			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In viweAllITSections method of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return timeTableList;
	}
	
	@Override
	public String editAndUpdateTimeTable(TimeTableConfigModel timtab) {
		String message = null;	
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int status=session.update("editAndUpdateTimeTable", timtab);
			if(status==0){
					message="Class allotment failed.";
				}else{
					message="Class allotment done successfully.";
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In editAndUpdateTimeTable(TimeTableConfigModel timtab) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return message;
	}
	
	
	@Override
	public String deleteClassForTeacher(String detailsId) throws CustomException {
		logger.info("Get inactiveAccessTypeContactMapping  DB");
		SqlSession session =sqlSessionFactory.openSession();		
		TimeTableConfigModel timtabl = new TimeTableConfigModel();
		String statusMessage="FAILED";
		try {
			String[] str = detailsId.split("~");
			String teacherId = str[0];
			String classSectionName = str[1];
			String subject = str[2];
			timtabl.setTeacherUserId(teacherId);
			timtabl.setClassSectionName(classSectionName);// Mapping purpose
			timtabl.setSubjectName(subject);
			//System.out.println("in 3623 in daoimpl::classSection:::::"+timtabl.getClassSectionName()+
					//"\nteacherId:::"+timtabl.getTeacherUserId());
			int status=session.update("deleteClassForTeacher", timtabl);
			session.commit();	
			if(status>0){
				statusMessage="SUCCESS";
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return statusMessage;
	}
	/////////////--------------------FOR LIBRARY FINE--------------------
	@Override
	public List<BookAllocation> getIssuedBookDetails(Resource resource) {
	List<BookAllocation> issuedBookList = null;
	SqlSession session = sqlSessionFactory.openSession();
	try {
		session.update("countPenalty", resource);
		session.commit();
		issuedBookList = session.selectList("getAllocationList", resource);
		if (issuedBookList != null) {
			for (BookAllocation bookAllocation : issuedBookList) {
				List<BookAllocationDetails> issuedBookDetails = session.selectList("getAllocationListForFine",bookAllocation.getBookAllocationCode());
				bookAllocation.setBookAllocationDetails(issuedBookDetails);
				bookAllocation.setBookIssuedTo(resource);
			}
		}
	} catch (Exception e) {
		logger.error("Exception in BackOfficeDaoImpl : method:  getIssuedBookDetails(Resource resource) ", e);
	} finally {
		session.close();
	}
	return issuedBookList;
	}
	
	@Override
	public List<BookAllocation> submitLibrayFine(Resource resource, PreviousYearFinanceData previousYearFinanceData) {
	List<BookAllocation> issuedBookList = null;
	SqlSession session = sqlSessionFactory.openSession();
		try {
			resource.setName("PAID");
			session.update("setPenaltyRecievedD", resource);
			Utility util = new Utility();
			resource.setObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
			resource.setPaymentMode("CASH");
			session.insert("insertIntoTransactionalWorkingAreaForReceiveLibraryFine", resource);
			session.commit();
			issuedBookList = session.selectList("getAllocationList", resource);
			if (issuedBookList != null) {
				for (BookAllocation bookAllocation : issuedBookList) {
					List<BookAllocationDetails> issuedBookDetails = session.selectList("getAllocationListForFine",bookAllocation.getBookAllocationCode());
					bookAllocation.setBookAllocationDetails(issuedBookDetails);
					bookAllocation.setBookIssuedTo(resource);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method:  submitLibrayFine(Resource resource, PreviousYearFinanceData previousYearFinanceData) ", e);
		} finally {
			session.close();
		}
	return issuedBookList;
	}
	
///-----------------------START IT SECTION-------------------------------

	@Override
	public List<ITSection> viewAllITSections() {
		SqlSession session =sqlSessionFactory.openSession();
		List<ITSection> itSectionList=null;
		try {
			itSectionList=session.selectList("viewAllITSections");			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In viweAllITSections method of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return itSectionList;
	}
	
	@Override
	public String createITSections(ITSection itSection) {
		SqlSession session =sqlSessionFactory.openSession();
		Utility util = new Utility();
		String message = null;
		try {
			itSection.setItSectionObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
			int status=session.update("createITSections", itSection);
			if(status==0){
					message="failed";
				}else{
					message="created";
				}
		}  catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In createITSections(FinancialYear financialYear) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return message;
	}
	
	@Override
	public String updateITSection(ITSection itSection) {
		String message = null;	
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int status=session.update("updateITSection", itSection);
			if(status==0){
					message="failed";
				}else{
					message="created";
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In updateITSection(ITSection itSection) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return message;
	}
	
	@Override
	public List<ITSectionDetails> getRebatesForITSection(String itSectionCode) {
		SqlSession session = sqlSessionFactory.openSession();
		List<ITSectionDetails> itSectionDetailList = null;
		try {
			itSectionDetailList = session.selectList("selectRebatesForITSection", itSectionCode);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In getRebatesForITSection(String itSectionCode) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return itSectionDetailList;
	}
	
	@Override
	public String submitITSectionRebates(ITSection itSection) {
		String submitResponse = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int status = 0;
			logger.info("submitITSectionRebates(ITSection itSection) method in BackOfficeDaoImpl");
			if(null != itSection){
				for(ITSectionDetails itSecDet : itSection.getItSectionDetailsList()){
					//System.out.println("##  ItSection :: "+itSection.getItSectionCode()+"  ||  SectionDetailsName :: "+itSecDet.getItSectionDetailsName());
					
					itSecDet.setItSection(itSection.getItSectionCode());
					itSecDet.setItSectionDetailsObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
					status = session.insert("insertIntoITSectionDetail", itSecDet);				
				}
			}
			submitResponse = String.valueOf(status);
		} catch (Exception e) {
			logger.error("submitITSectionRebates(ITSection itSection) method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		return submitResponse;
	}
	
	@Override
	public String editITSectionRebates(ITSection itSection, ITSection itSecNew) {
		String updateResponse = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int updateStatus = 0;
			logger.info("editITSectionRebates(ITSection itSection, ITSection itSecNew) method in BackOfficeDaoImpl");
			if(null != itSection){	
				for(ITSectionDetails itSecDet : itSection.getItSectionDetailsList()){					
					updateStatus = session.update("updateITSecDetails", itSecDet);		
				}
				if(null != itSecNew){
					for(ITSectionDetails itSecDet : itSecNew.getItSectionDetailsList()){
						//System.out.println("##  ItSection :: "+itSecNew.getItSectionCode()+"  ||  SectionDetailsName :: "+itSecDet.getItSectionDetailsName());
						
						itSecDet.setItSection(itSecNew.getItSectionCode());
						itSecDet.setItSectionDetailsObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
						updateStatus = session.insert("insertIntoITSectionDetail", itSecDet);
						
						if(updateStatus != 0){
							itSection.setItSectionObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
							itSection.setAmount(0);
							itSection.setItSectionDesc(itSection.getItSectionCode()+"-");
							updateStatus = session.insert("defaultAmountInsertInITSectionDetailAmnt", itSection);
						}				
					}			
				}
			}
			updateResponse = String.valueOf(updateStatus);
		} catch (Exception e) {
			logger.error("editITSectionRebates(ITSection itSection, ITSection itSecNew) method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		return updateResponse;
	}
	
	@Override
	public List<IncomeAge> getIncomeAgeList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<IncomeAge> incomeAgeList = null;
		try{			
			incomeAgeList=session.selectList("getIncomeAgeList");
		}catch(NullPointerException e){
			logger.error("Error In getIncomeAgeList() method of CommonDAOImpl:NullPointerException:: ",e);
			e.printStackTrace();
		}catch(Exception e){
			logger.error("Error In getIncomeAgeList() method of CommonDAOImpl :Exception:: ",e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return incomeAgeList;
	}
	
	@Override
	public List<ITSection> getUnmappedITSections(ITSectionGroup itSectionGroup) {
		SqlSession session = sqlSessionFactory.openSession();
		List<ITSection> itSectionList = null;
		try {
			itSectionList=session.selectList("getUnmappedITSections",itSectionGroup);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In getUnmappedITSections(ITSectionGroup itSectionGroup) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return itSectionList;
	}
	
	@Override
	public String insertITSectionDeductionAmount(ITSectionGroup itSectionGroup) {
		String message = null;	
		SqlSession session =sqlSessionFactory.openSession();
		int status=0;
		int status1 = 0;
		Utility util = new Utility();
		String itSectionGroupCode="";
		String itSectionGroupName="";
		try {
			itSectionGroup.setItSectionGroupObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
			if(null!=itSectionGroup.getItSectionList() && itSectionGroup.getItSectionList().size()!=0){
				for(ITSection itSection:itSectionGroup.getItSectionList()){
					if(null!=itSection){
					itSectionGroupCode=itSectionGroupCode+itSection.getItSectionCode()+"-";
					itSectionGroupName=itSectionGroupName+itSection.getItSectionName()+"-";
					}
				}
			}	
			if(itSectionGroupName.length()>0 && (itSectionGroupName.charAt(itSectionGroupName.length()-1)=='-')){
				itSectionGroupName=itSectionGroupName.substring(0, itSectionGroupName.length()-1);
			}
			itSectionGroup.setItSectionGroupName(itSectionGroupName.toUpperCase());
			itSectionGroup.setItSectionGroupCode(itSectionGroupCode.toUpperCase());
			 status=session.insert("insertITSectionDeductionAmount", itSectionGroup);			 
			 for(ITSection itSection:itSectionGroup.getItSectionList()){
				 if(null!=itSection){
				 itSection.setUpdatedBy(itSectionGroup.getUpdatedBy());
				 itSection.setItSectionObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));				 
				 itSection.setItSectionDesc(itSectionGroup.getFinancialYear().getFinancialYearCode());//
				 status1=session.insert("insertITSectionDeductionAmountDetails", itSection);
				}
			 }
			if(status==0 && status1==0){
					message="failed";
				}else{
					message="created";
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In createNewFinancialYear(FinancialYear financialYear) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return message;
	}
	
	@Override
	public List<ITSectionGroup> getITSectionGroupForAgeYear(ITSectionGroup itSectionGrp) {
		SqlSession session = sqlSessionFactory.openSession();
		List<ITSectionGroup> itSectionGroupList = null;
		try {
			itSectionGroupList = session.selectList("selectITSectionGroupsForAgeYear", itSectionGrp);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In getITSectionGroupForAgeYear(ITSectionGroup itSectionGrp) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return itSectionGroupList;
	}
	
	@Override
	public String checkITSecDetailAmount(ITSectionGroup itSectionGrp) {
		SqlSession session = sqlSessionFactory.openSession();
		String count = "";
		try {
			count = session.selectOne("checkITSecDetailAmountForITSecGroup", itSectionGrp);		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In checkITSecDetailAmount(ITSectionGroup itSectionGrp) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return count;
	}
	
	@Override
	public ITSectionGroup getITSectionForITGroups(ITSectionGroup itSectionGrp) {
		SqlSession session = sqlSessionFactory.openSession();
		ITSectionGroup itSecGrp = null;
		try {
			itSecGrp = session.selectOne("selectITSectionForSectionGroups", itSectionGrp);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In getITSectionForITGroups(ITSectionGroup itSectionGrp) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return itSecGrp;
	}
	
	@Override
	public List<ITSectionDetails> getITSectionDetailForITSecs(ITSection itSec) {
		SqlSession session = sqlSessionFactory.openSession();
		List<ITSectionDetails> itSecDetailsList = null;
		try {
			itSecDetailsList = session.selectList("selectITSecDetailsForSection", itSec);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In getITSectionDetailForITSecs(ITSection itSec) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return itSecDetailsList;
	}
	
	@Override
	public String submitITSectionRebateAmounts(ITSectionGroup itSectionGroup) {
		String submitResponse = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int status = 0;
			logger.info("submitITSectionRebateAmounts(ITSectionGroup itSectionGroup) method in BackOfficeDaoImpl");
			if(null != itSectionGroup){
				for(ITSection itSec : itSectionGroup.getItSectionList()){
					for(ITSectionDetails itSecDet : itSec.getItSectionDetailsList()){
						ITSection itSection = new ITSection();
						itSection.setItSectionCode(itSec.getItSectionCode());
						itSection.setItSectionDesc(itSecDet.getItSectionDetailsCode());		// Setting ItSectionDetailsCode in ItSectionDesc 
						itSection.setAmount(itSecDet.getSubAmount());
						itSectionGroup.setItSection(itSection);
						itSectionGroup.setItSectionGroupObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
						itSectionGroup.setItSectionGroupDesc(itSectionGroup.getIncomeAge().getIncomeAgeCode()+"-"+itSec.getItSectionCode()+"-"+itSecDet.getItSectionDetailsCode()+"-"+itSecDet.getSubAmount());
						status = session.insert("insertIntoITSectionDetailAmount", itSectionGroup);		
					}							
				}				
			}
			submitResponse = String.valueOf(status);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("submitITSectionRebateAmounts(ITSectionGroup itSectionGroup) method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		return submitResponse;
	}
	
	@Override
	public ITSectionGroup getRebateAmountDetailForITGroup(ITSectionGroup itSectionGrp) {
		SqlSession session = sqlSessionFactory.openSession();
		ITSectionGroup itSecGrp = null;
		try {
			itSecGrp = session.selectOne("selectRebateAmountForITSectionGroup", itSectionGrp);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception In getRebateAmountDetailForITGroup(ITSectionGroup itSectionGrp) of BackOfficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return itSecGrp;
	}
	
	@Override
	public String updateITSectionRebateAmountLimit(ITSectionGroup itSectionGroup) {
		String updateResponse = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			int status = 0;
			int updateStatus = 0;
			logger.info("updateITSectionRebateAmountLimit(ITSectionGroup itSectionGroup) method in BackOfficeDaoImpl");
			if(null != itSectionGroup){
				//System.out.println("#  ItSectionGroupCode :: "+itSectionGroup.getItSectionGroupCode()+"  ||  "+itSectionGroup.getFinancialYear().getFinancialYearCode()+"  ||  IncomeAgeCode :: "+itSectionGroup.getIncomeAge().getIncomeAgeCode());
								
				for(ITSection itSec : itSectionGroup.getItSectionList()){
					//System.out.println("@@  ItSectionCode :: "+itSec.getItSectionCode());
					for(ITSectionDetails itSecDet : itSec.getItSectionDetailsList()){
						//System.out.println("***  ItSectionGroupCode :: "+itSectionGroup.getItSectionGroupCode()+" || ItSectionCode :: "+itSec.getItSectionCode()+" || ItSectionDetailsCode :: "+itSecDet.getItSectionDetailsCode()+"  ||  Amount :: "+itSecDet.getSubAmount());						
						ITSection itSection = new ITSection();
						itSection.setItSectionCode(itSec.getItSectionCode());
						itSection.setItSectionDesc(itSecDet.getItSectionDetailsCode());			// Setting ItSectionDetailsCode in ItSectionDesc 
						itSectionGroup.setItSection(itSection);
						itSectionGroup.setItSectionGroupObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
						itSectionGroup.setItSectionGroupDesc(itSectionGroup.getIncomeAge().getIncomeAgeCode()+"-"+itSec.getItSectionCode()+"-"+itSecDet.getItSectionDetailsCode()+"-"+itSecDet.getSubAmount());
						updateStatus = session.update("updateITSecDetailsAmount", itSectionGroup);
						//System.out.println("in 3280 in daoImpl:: updateStatus:::;"+updateStatus);
					}
				}
				
				if(updateStatus != 0){
					for(ITSection itSec : itSectionGroup.getItSectionList()){
						//System.out.println("@@  ItSectionCode :: "+itSec.getItSectionCode());
						for(ITSectionDetails itSecDet : itSec.getItSectionDetailsList()){
							//System.out.println("***  ItSectionGroupCode :: "+itSectionGroup.getItSectionGroupCode()+" || ItSectionCode :: "+itSec.getItSectionCode()+" || ItSectionDetailsCode :: "+itSecDet.getItSectionDetailsCode()+"  ||  Amount :: "+itSecDet.getSubAmount());
							
							ITSection itSection = new ITSection();
							itSection.setItSectionCode(itSec.getItSectionCode());
							itSection.setItSectionDesc(itSecDet.getItSectionDetailsCode());		// Setting ItSectionDetailsCode in ItSectionDesc 
							itSection.setAmount(itSecDet.getSubAmount());
							itSectionGroup.setItSection(itSection);
							itSectionGroup.setItSectionGroupObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
							itSectionGroup.setItSectionGroupDesc(itSectionGroup.getIncomeAge().getIncomeAgeCode()+"-"+itSec.getItSectionCode()+"-"+itSecDet.getItSectionDetailsCode()+"-"+itSecDet.getSubAmount());
							status = session.insert("insertIntoITSectionDetailAmount", itSectionGroup);		
						}
					}
				}
			}
		updateResponse = String.valueOf(status);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateITSectionRebateAmountLimit(ITSectionGroup itSectionGroup) method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		return updateResponse;
	}
///-----------------------START IT SECTION-------------------------------	
	
///-----------------------START STUDENT TC-------------------------------	
	@Override
	public String getNameStandardSectionForRollNumber(String rollNumber)throws CustomException {
		String result = "";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			Student student=session.selectOne("selectNameStandardSectionForRollNumber", rollNumber);
			if(null!=student)
				result=student.getStudentName()+"*"+student.getStandard()+"*"+student.getSection();
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return result;
	}
	
	/**
	 * @author anup.roy
	 * for checking fees status
	 * 07092017*/
	
	@Override
	public String getStudentFeesPaymentStatus(String rollNumber) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		String status="";
		try {
			status=session.selectOne("getStudentFeesPaymentStatus",rollNumber);
			if(null==status || status.length()==0)
				status="PENDING";
			logger.info(status);
			
			/*String fineStatus=session.selectOne("getStudentLibraryFineStatus",Integer.parseInt(rollNumber));
			if(null==fineStatus || fineStatus.length()==0 || (!fineStatus.equalsIgnoreCase("PEN")))
				status=status+"*CLEARED";
			else
				status=status+"*PENDING";
			logger.info(status);
			
			String bookStatus=session.selectOne("getStudentLibraryBookStatus",Integer.parseInt(rollNumber));
			if(null==bookStatus || bookStatus.length()==0 || (!bookStatus.equalsIgnoreCase("ALLOT")))
				status=status+"*CLEARED";
			else
				status=status+"*ALLOTED";*/
			logger.info(status);
			
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return status;
	}
///-----------------------END STUDENT TC-------------------------------	
	
	@Override
	public String checkSocialCategoryName(String socialCategoryName) {
		SqlSession session = sqlSessionFactory.openSession();
		String valid = "NO";
		try {
			SocialCategory socialCategory = session.selectOne("checkSocialCategoryName", socialCategoryName);
			if (socialCategory != null) {
				valid = "YES";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return valid;
	}
	
	
	@Override
	public List<AdmissionForm> admissionDriveListForFeesSubmission() {
		logger.info("admissionDriveListForFeesSubmission() method in BackOficeDaoImpl");
		SqlSession session =sqlSessionFactory.openSession();
		List<AdmissionForm> admissionFormList = null;
		try {
			logger.info("getAdmissionsOnProcessList() method in AdmissionDaoImpl");
			// #######$$$$$$$$$%%%%%%%%%%%%%%%%%%%&&&&&&&&&&&&((((((((((((NOTE:
			// query should change: add status
			admissionFormList = session.selectList("admissionDriveListForFeesSubmission");
		} catch (Exception e) {
			logger.error("Exception in BackOfficeDaoImpl : method: admissionDriveListForFeesSubmission() ", e);
		} finally {
			session.close();
		}
		return admissionFormList;
	}
	
	/**
	 * Added by naimisha for IT section rebate*/
	
	@Override
	public String inactiveItRebate(String itSection) {
		logger.info("admissionDriveListForFeesSubmission() method in BackOficeDaoImpl");
		SqlSession session =sqlSessionFactory.openSession();
		String message = null;
		int status = 0;
		try {
			logger.info("getAdmissionsOnProcessList() method in AdmissionDaoImpl");
			// #######$$$$$$$$$%%%%%%%%%%%%%%%%%%%&&&&&&&&&&&&((((((((((((NOTE:
			// query should change: add status
			status = session.update("inactiveItRebate",itSection);
			if(status>0){
				message = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in BackOfficeDaoImpl : method: inactiveItRebate() ", e);
		} finally {
			session.close();
		}
		return message;
		
	}
	
	/***
	 * added by parma for receive fees*/
	
	@Override
	public AdmissionProcess getRegistrationIdFormListClassForFeesSubmission(
			AdmissionProcess admissionProcess) {
		logger.info("getRegistrationIdFormListClassForFeesSubmission(AdmissionProcess admissionProcess) method in BackOficeDaoImpl");
		SqlSession session =sqlSessionFactory.openSession();
		try{
			List<Resource> formIdList=session.selectList("getSelectedCandidateList", admissionProcess);
			if(formIdList != null &&formIdList.size()!=0){
				admissionProcess.setResourceList(formIdList);
			}
		}catch(Exception e){
			logger.error("Exception in getRegistrationIdFormListClassForFeesSubmission(AdmissionProcess admissionProcess) method in BackOficeDaoImpl",e);
		}finally{
			session.close();
		}
		return admissionProcess;
	}
	
	/**
	modified by sourav bhadra 19092017*/
	
	@Override
	public List<Resource> submitFeesForStudent(Student student) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> formIdList = null;
		try {
			String year = student.getAcademicYearForFeesSubmission();
			String driveName = student.getDriveNameForFeesSubmission();
			Utility util = new Utility();
			PreviousYearFinanceData previousYearFinanceData = new PreviousYearFinanceData();
			previousYearFinanceData.setPreviousDataObjectId(util.getBase64EncodedID("BackOficeDaoImpl"));
			previousYearFinanceData.setPreviousDataCode(util.getBase64EncodedID("BackOficeDaoImpl"));
			previousYearFinanceData.setUpdatedBy(student.getUpdatedBy());
			String strClass = student.getStandard();
			student.setPaymentStatus("FEESPAID");
			String registrationId = student.getAcademicYearForFeesSubmission()+"/"+student.getDriveNameForFeesSubmission()+"/"+student.getFormIdForFeesSubmission();
			student.setRegistrationId(registrationId);System.out.println("LN4345 :: "+registrationId);
			System.out.println("formId::>>"+student.getFormIdForFeesSubmission()+"\nDriveName::>>"+student.getDriveNameForFeesSubmission()+"\nStandard::>>"+student.getStandard()+"\nYear::>>"+student.getAcademicYearForFeesSubmission());
			int updatedFlag  = session.update("updateStudentStatusForFeesSubmission",student);
			
			String ledgerName = registrationId;
			Ledger ledger = new Ledger();
			ledger.setUpdatedBy(student.getUpdatedBy());
			ledger.setObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
			ledger.setLedgerCode(ledgerName);
			ledger.setLedgerName(ledgerName);
			ledger.setParentGroupCode("CURRENT ASSETS");
			/*modified by ranita.sur on 22082017 for adding subgroup of student*/
			ledger.setSubGroupName("TRADE RECEIVABLES");
			ledger.setOpeningBal(0.0);
			session.insert("createLedger",ledger);
			
			/* modified by sourav.bhadra on 18-09-2017
			 * for a jurnal entry
			 * Student(crerdit) to cash/bank(debit) */
			TransactionDetails trd = new TransactionDetails();
			trd.setUpdatedBy(student.getUpdatedBy());
			trd.setObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
			
			double totalAmt = student.getTotAmmount();
			
			//for student(credit)
			Ledger studentLedgerBalance = session.selectOne("selectBalanceForParentLedger", ledgerName);
			trd.setLedger(ledgerName);
			trd.setGrossAmount(studentLedgerBalance.getOpeningBal());
			
			
			if(studentLedgerBalance.getCurrentBal() < 0 ){
				/* if current balance is -ve then in credit side
				 * credit on credit = add */
				trd.setOnbasic(studentLedgerBalance.getCurrentBal() + totalAmt);
				System.out.println("LN4378 :: Student -ve(credit)  Final Amount :: "+trd.getOnbasic());
				session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
			}else{
				/* if current balance is +ve then in debit side
				 * credit on debit = subtract */ 
				trd.setOnbasic(studentLedgerBalance.getCurrentBal() - totalAmt);
				System.out.println("LN4384 :: Student +ve(debit)  Final Amount :: "+trd.getOnbasic());
				session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
			}
			TransactionDetails tranDetStudent = new TransactionDetails();
			tranDetStudent.setUpdatedBy(student.getUpdatedBy());
			tranDetStudent.setObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
			tranDetStudent.setLedger(ledgerName);
			tranDetStudent.setAmount(totalAmt);
			tranDetStudent.setDebit(false);
			System.out.println("--------------LN4393 Student---------------");
			System.out.println("Total Amount :: "+totalAmt);
			System.out.println("Student Ledger :: "+ledgerName);
			System.out.println("Opening Balance :: "+studentLedgerBalance.getOpeningBal());
			System.out.println("Closing Balance :: "+studentLedgerBalance.getCurrentBal());
			
			//for Cash/Bank (Debit)
			TransactionDetails tranDetCashBank = new TransactionDetails();
			tranDetCashBank.setUpdatedBy(student.getUpdatedBy());
			tranDetCashBank.setObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
			
			if(student.getPaymentMode().equalsIgnoreCase("BANK")){
				/* added by sourav.bhadra on 19-19-2017 */
				String bankName = student.getStudentCode();
				String bankLedger = session.selectOne("selectLedgerOfABank", bankName);;
				Ledger bankLedgerBalance = session.selectOne("selectBalanceForParentLedger", bankLedger);
				trd.setLedger(bankLedger);
				trd.setGrossAmount(bankLedgerBalance.getOpeningBal());
				
				if(bankLedgerBalance.getCurrentBal() < 0 ){
					/*if current balance is -ve then in credit side
					 * debit on credit = subtract */
					trd.setOnbasic(bankLedgerBalance.getCurrentBal() - totalAmt);
					System.out.println("LN4411 :: Bank -ve(credit) Final Amount :: "+trd.getOnbasic());
					session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
				}else{
					/*if current balance is +ve then in debit side
					 * debit on debit = add */
					trd.setOnbasic(bankLedgerBalance.getCurrentBal() + totalAmt);
					System.out.println("LN4417 :: Bank +ve(debit)  Final Amount :: "+trd.getOnbasic());
					session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
				}
				tranDetCashBank.setLedger(bankLedger);
				System.out.println("--------------LN4421 Bank---------------");
				System.out.println("Bank Ledger :: "+bankLedger);
				System.out.println("Opening Balance :: "+bankLedgerBalance.getOpeningBal());
				System.out.println("Closing Balance :: "+bankLedgerBalance.getCurrentBal());
			}else{//for cash
				String cashLedger = "CASH";
				Ledger cashLedgerBalance = session.selectOne("selectBalanceForParentLedger", cashLedger);
				trd.setLedger(cashLedger);
				trd.setGrossAmount(cashLedgerBalance.getOpeningBal());
				
				if(cashLedgerBalance.getCurrentBal() < 0 ){
					/*if current balance is -ve then in credit side
					 * debit on credit = subtract */
					trd.setOnbasic(cashLedgerBalance.getCurrentBal() - totalAmt);
					System.out.println("LN1566 :: CASH -ve(credit) Final Amount :: "+trd.getOnbasic());
					session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
				}else{
					/*if current balance is +ve then in debit side
					 * debit on debit = add */
					trd.setOnbasic(cashLedgerBalance.getCurrentBal() + totalAmt);
					System.out.println("LN1572 :: CASH +ve(debit)  Final Amount :: "+trd.getOnbasic());
					session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
				}
				tranDetCashBank.setLedger(cashLedger);
				System.out.println("--------------LN4445 Cash---------------");
				System.out.println("Cash Ledger :: "+cashLedger);
				System.out.println("Opening Balance :: "+cashLedgerBalance.getOpeningBal());
				System.out.println("Closing Balance :: "+cashLedgerBalance.getCurrentBal());
			}
			tranDetCashBank.setAmount(totalAmt);
			tranDetCashBank.setDebit(true);
			
			List<TransactionDetails> transactionDetailsList = new ArrayList<TransactionDetails>();
			transactionDetailsList.add(tranDetStudent);
			transactionDetailsList.add(tranDetCashBank);
			
			String narration = "Receiving Rs:"+totalAmt+" from student as Admission Fees.";
			Transaction transaction = new Transaction();
			transaction.setObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
			transaction.setUpdatedBy(student.getUpdatedBy());
			transaction.setVoucherTypeCode("JOURNAL");
			transaction.setVoucherTypeName("JOURNAL");
			transaction.setNarration(narration);
			transaction.setTrDetList(transactionDetailsList);
			session.insert("createTransactionForAdmissionFeesReceive", transaction);
			/*------------------*/
			if(updatedFlag != 0){
				Student insertId =session.selectOne("selectSubmitedStudentFees",student);
				List<FeesCategory> feesList = student.getFeesCategoryList();
				for(FeesCategory feesCategory:feesList){
					feesCategory.setFeesCategoryObjectId(util.getBase64EncodedID("BackOficeDaoImpl"));
					feesCategory.setFeesCategoryCode(util.getBase64EncodedID("BackOficeDaoImpl"));
					feesCategory.setCheckValid(insertId.getAdmissionFormId());
					
					session.insert("insertStudentAdmissionFeesDetails",feesCategory);
					
					TransactionalWorkingArea transactionalWorkingArea = new TransactionalWorkingArea();
					transactionalWorkingArea.setUpdatedBy(student.getUpdatedBy());
					transactionalWorkingArea.setObjectId(util.getBase64EncodedID("AdmissionDriveDao"));
					transactionalWorkingArea.setTransactionalWorkingAreaName(feesCategory.getFeesCategoryName());
					transactionalWorkingArea.setTransactionalWorkingAreaDesc(feesCategory.getFeesCategoryName());
					transactionalWorkingArea.setNetAmount(feesCategory.getDiscountedFees());
					transactionalWorkingArea.setTransactionMode(student.getPaymentMode()); //Modified By Naimisha 21082017
					transactionalWorkingArea.setDescOfTransaction("ADMISSION");
					transactionalWorkingArea.setPaidAgainst(student.getStandard());
					transactionalWorkingArea.setDepartment("ADMISSION DEPARTMENT");
					transactionalWorkingArea.setIncomeExpense("INCOME");
					transactionalWorkingArea.setAcademicYear(feesCategory.getFeesCategoryName());
					transactionalWorkingArea.setResource(registrationId);
					session.insert("insertTotalAdmissionFeesIntoTransactionalWorkingArea",transactionalWorkingArea);
					
					
				}
				student.setObjectId(encryptDecrypt.encrypt("BackOficeDaoImpl"));
				student.setAdmissionFormId(insertId.getAdmissionFormId());
				Resource resource = new Resource();
				resource.setAdmissionYear(insertId.getResource().getAdmissionYear());
				resource.setCourseName(insertId.getResource().getCourseName());
				student.setResource(resource);
			}

			session.update("updateAdmissionDriveForNoOfStudentAdmitted",student);
			session.commit();
			AdmissionProcess admissionProcess = new AdmissionProcess();
			admissionProcess.setAdmissionYear(year);
			admissionProcess.setFormName(driveName);
			admissionProcess.setCourseClass(strClass);
			formIdList = session.selectList("getSelectedCandidateList",admissionProcess);
			
			
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("Exception in submitFeesForStudent(Student student) method in BackOficeDaoImpl",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return formIdList;
	}

	@Override
	public Resource getCandidateDetails(Resource resource) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			//System.out.println("1 " + resource.getAdmissionDriveNameId());
			//System.out.println("2 " + resource.getAdmissionFromId());
			//System.out.println("3 " + resource.getAdmissionYear());
			resource = session.selectOne("getRegistrationIdForStudentForAdmissionFeesSubmission",resource);
			//System.out.println(resource.getFirstName()+"$"+resource.getLastName()+"^"+resource.getCategory());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getCandidateDetails(Resource resource) method in BackOficeDaoImpl",e);
		} finally {
			session.close();
		}
		return resource;
	}

	@Override
	public List<FeesCategory> getFeeStructureForStudent(Resource resource) {
		SqlSession session =sqlSessionFactory.openSession();
			List<FeesCategory> totalFeesDetailsDB = null;
			try{
				FeesCategory feesCategory=new FeesCategory();				
				feesCategory.setAcademicYear(resource.getAdmissionYear());
				feesCategory.setKlass(resource.getKlass());
				//String streamName = session.selectOne("getStreamNameForAdmissionDrive", resource.getAdmissionDriveNameId());
				//feesCategory.setStream(stream);
				//Resource category set into feesCategoryDesc
				feesCategory.setFeesCategoryDesc(resource.getCategory());
				feesCategory.setCourse(resource.getCourseName());
				totalFeesDetailsDB = session.selectList("displayFeesForSocialCategoryClassYear",feesCategory);
				//System.out.println("totalFeesDetailsDB--size::>>"+totalFeesDetailsDB.size());
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Exception in getFeeStructureForStudent(Resource resource) method in BackOficeDaoImpl",e);
			}finally{
				session.close();
			}
		return totalFeesDetailsDB;
	}
	
	
	@Override
	public List<ResourceType> getResourceTypes() {
		SqlSession session =sqlSessionFactory.openSession();
		List<ResourceType> resourceTypeList = null;
		try{
			logger.info("Executing getResourceTypes() from BackofficeDaoImpl");
			resourceTypeList = session.selectList("getResourceTypes");
			//System.out.println("from dao:"+resourceTypeList);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured while executing getResourceTypes() from ERPDAOImpl", e);	
		}
		return resourceTypeList;
	}

	/**
	 * @author anup.roy*
	 * for attendance calendar*/
	
	@Override
	public String getStudentsForView(Resource resource) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> students = null;
		String output = null;
		try {
			students = session.selectList("getStudentsForView",resource);
			StringBuilder sb = new StringBuilder();
			for (Resource resourceone : students) {
				sb.append(resourceone.getResourceName()+"*");
				sb.append(resourceone.getUserId() + "@");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return output;
	}
	
	@Override
	public AttendanceDetails getResourceAbsentDateRecord(AttendanceDetails attendanceDetails) {
		SqlSession session =sqlSessionFactory.openSession();
		//System.out.println("At DAOIMPL");
		try{
			List<String> absentDateList = session.selectList("selectAttendanceDetailsOfResource", attendanceDetails);				
			//System.out.println("SIZE:"+absentDateList.size());
			//System.out.println(absentDateList);
			if(absentDateList != null && absentDateList.size() != 0){
				attendanceDetails.setAbsentDateList(absentDateList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return attendanceDetails;
	}

	@Override
	public AttendanceDetails getTeacherAttendanceRecord(AttendanceDetails attendanceDetails) {
		SqlSession session =sqlSessionFactory.openSession();
		//System.out.println("At DAOIMPL");
		try{
			List<String> presentDays = session.selectList("selectAttendanceDetailsOfTeacher", attendanceDetails);				
			//System.out.println("SIZE:"+presentDays.size());
			//System.out.println(presentDays);
			if(presentDays != null && presentDays.size() != 0){
				attendanceDetails.setPresentDateList(presentDays);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return attendanceDetails;
	}
	
	@Override
	public List<Course> getCourseListForCreateStudent() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Course>courseList = null;
		try{
			courseList = session.selectList("getCourseListForCreateStudent");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return courseList;
	}

	//Modified by naimisha 18082017
	@Override
	public String getFormIdAgainstCourseId(String courseId,String driveName) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Form> formid = null;
		Course course = new Course();
		String formId="";
		try{
			course.setCourseName(courseId);
			String admissionMode = session.selectOne("selectAdmissionModeForACourse", courseId);
			System.out.println("admissionMode===="+admissionMode);
			if(admissionMode.equalsIgnoreCase("ONLINE")){
				formid = session.selectList("getFormIdAgainstCourseId", course);
			}else{
				course.setDesc(driveName);
				formid = session.selectList("getFormIdAgainstOfflineCourseId", course);
			}
			
			if(null!= formid && formid.size()>0){
				StringBuilder str = new StringBuilder();
				for(Form form : formid){
					str.append(form.getStrFormId()+"#");
					formId = str.toString().substring(0, str.toString().length() - 1);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		//System.out.println("4214 in backofcDaoimpl::"+formId);
		return formId;
	}

	
	//Modified By Naimisha 18082017
	@Override
	public String getAdmissionDriveNameAgainstCourseId(String courseId) {
		SqlSession session = sqlSessionFactory.openSession();
		Course course = new Course();
		String driveId = "";
		try{
			course.setCourseName(courseId);
			//System.out.println("courseName::"+course.getCourseName());
			List<String>driveIdList = session.selectList("getAdmissionDriveNameAgainstCourseId", course);
			for(String drive : driveIdList){
				driveId = driveId + "*"+ drive;
			}
			System.out.println("drive id===="+driveId);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		//System.out.println("4441 in backofcDaoimpl::"+driveId);
		return driveId;
	}

	/***
	 * @author anup.roy 
	 * new fees portion starts**/
	/**new*/
	@Override
	public List<FeesCategory> selectCategory() {

		SqlSession session =sqlSessionFactory.openSession();
		List<FeesCategory> feesCategoryFromDB = null;
		try {
			feesCategoryFromDB = session.selectList("selectFeesCategory");
		} catch (Exception e) {
			logger.error("Exception in selectCategory() method in  BackOfficeDAOImpl", e);
		} finally {
			session.close();
		}
		return feesCategoryFromDB;
	}

	@Override
	public List<FeesDuration> selectFeesDuration() {

		SqlSession session =sqlSessionFactory.openSession();
		List<FeesDuration> feesDurationFromDB = null;
		try {
			feesDurationFromDB = session.selectList("selectFeesDuration");
		} catch (Exception e) {
			logger.error("Exception in selectFeesDuration() method in  BackOfficeDAOImpl", e);
		} finally {
			session.close();
		}
		return feesDurationFromDB;
	}
	
	/**
	 * ranita.sur 03072017*/
	
	@Override
	public String insertCategory(FeesCategory feescategory){
		String insertStatus=null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			feescategory.setFeesCategoryObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
			int insert = session.insert("insertFeesCategory",feescategory);		
			/* added by sourav.bhadra on 01-09-2017 */
			if(insert != 0){
				Ledger ledger = new Ledger();
				ledger.setUpdatedBy(feescategory.getUpdatedBy());
				ledger.setObjectId(util.getBase64EncodedID("AdmissionDriveDao"));
				ledger.setLedgerCode(feescategory.getFeesCategoryName());
				ledger.setLedgerName(feescategory.getFeesCategoryName());
				ledger.setParentGroupCode("DIRECT INCOME");			
				ledger.setOpeningBal(0);
				session.insert("createLedgerForFeesStructure",ledger);
			}
			
			insertStatus = "success";
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public String editFeesStructure(FeesCategory feescategory){
		String updateStatus=null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In editFeesStructure(FeesCategory feescategory)of backofficeDaoImpl.java");
			int update = session.update("editFeesStructure", feescategory);
			/* added by sourav.bhadra on 01-09-2017 */
			if(update != 0){
				Ledger ledger = new Ledger();
				ledger.setUpdatedBy(feescategory.getUpdatedBy());
				ledger.setLedgerName(feescategory.getFeesCategoryName());
				session.update("updateFeesLedger", ledger);
			}
			updateStatus = "success";
		}catch(Exception e) {
			updateStatus = "fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}		
		return updateStatus;
	}

	@Override
	public List<StudentFeesTemplate> studentFeesTemplateList() {
		logger.info("In studentFeesTemplateList() method of BackofficeDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();	
		List<StudentFeesTemplate> studentFeesTemplateList = new ArrayList<StudentFeesTemplate>();
		try {
			studentFeesTemplateList = session.selectList("selectStudentFeesTemplateList");			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error In studentFeesTemplateList() method of BackofficeDAOImpl:Exception:: "+ e);
		} finally {
			session.close();
		}
		return studentFeesTemplateList;
	}

	
	@Override
	public String createNewFeesTemplate(StudentFeesTemplate studentFeesTemp) {
		String insertStatus=null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In createNewFeesTemplate() method of BackofficeDAOImpl.java ");
			studentFeesTemp.setStudentFeesTemplateObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertStudentFeesTemplate", studentFeesTemp);
			String maxStudentFeesTempCode = session.selectOne("selectMaxStudentFeesTemplateId");			
			if(maxStudentFeesTempCode != null){
				for(StudentFeesTemplateDetails stdObj : studentFeesTemp.getStudentFeesTemplateDetailsList()){		
					stdObj.setStudentFeesTemplate(maxStudentFeesTempCode);
					stdObj.setStudentFeesTemplateDetailsObjectId(util.getBase64EncodedID("ErpDaoImpl"));
					session.insert("insertStudentFeesTemplateDetails", stdObj);
				}
			}
			insertStatus = "success";
		}catch(Exception e) {
			insertStatus = "fail";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String editStudentFeesTemplates(StudentFeesTemplate studentFeesTemplate){
		String status = "success";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			studentFeesTemplate.setStudentFeesTemplateObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			if(null!=studentFeesTemplate.getStudentFeesTemplateDetailsListOld() && studentFeesTemplate.getStudentFeesTemplateDetailsListOld().size()!=0){
				for(StudentFeesTemplateDetails sftd : studentFeesTemplate.getStudentFeesTemplateDetailsListOld()){
					//System.out.println("Inactive For "+studentFeesTemplate.getStudentFeesTemplateCode()+"\t\t"+sftd.getStudentFeesBreakUpCode());
				}
				session.update("inactiveFeesTemplateDetailsMapping", studentFeesTemplate);				
			}
			//System.out.println(studentFeesTemplate.getStudentFeesTemplateDetailsList());
			if(null!= studentFeesTemplate.getStudentFeesTemplateDetailsList() && studentFeesTemplate.getStudentFeesTemplateDetailsList().size()!=0){
				for(StudentFeesTemplateDetails sftd : studentFeesTemplate.getStudentFeesTemplateDetailsList()){
					StudentFeesTemplate studentTemplateNew = new StudentFeesTemplate();
					studentTemplateNew.setStudentFeesTemplateDesc(sftd.getStudentFeesBreakUpCode());
					studentTemplateNew.setStudentFeesTemplateCode(studentFeesTemplate.getStudentFeesTemplateCode());
					studentTemplateNew.setUpdatedBy(studentFeesTemplate.getUpdatedBy());
					StudentFeesTemplate checker = session.selectOne("selectInactiveStudentFeesTemplateMapping", studentTemplateNew);
					System.out.print("Check Availability For "+studentTemplateNew.getStudentFeesTemplateCode()+"\t\t"+studentTemplateNew.getStudentFeesTemplateDesc());
					if(null!=checker && null!=checker.getStudentFeesTemplateDesc()){
						//System.out.println("\t\tAvailable");
						session.insert("updateFeesTemplateMapping", studentTemplateNew);
					}else{
						//System.out.println("\t\tNotAvailable");
						studentTemplateNew.setStudentFeesTemplateObjectId(studentFeesTemplate.getStudentFeesTemplateObjectId());
						session.update("insertNewFeesTemplateMapping", studentTemplateNew);
					}
				}
			}
			session.update("updateStudentFeesTemplate", studentFeesTemplate);
		}catch(Exception e) {
			e.printStackTrace();
			status = "fail";
			logger.error(e);
		}finally{
			session.close();
		}
		return status;
	}

	@Override
	public List<StudentFeesTemplate> getAllFeesTemplates() {
		SqlSession session = sqlSessionFactory.openSession();
		List<StudentFeesTemplate> allFeesTemplates = null;
		try{
			logger.info("In getAllFeesTemplates() of BackofficeDaoImpl.java");
			allFeesTemplates = session.selectList("allFeesTemplates");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		return allFeesTemplates;
	}

	@Override
	public List<Course> getAllUnmappedCourses() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Course> allUnmappedCourses = null;
		try{
			logger.info("In getAllFeesTemplates() of BackofficeDaoImpl.java");
			allUnmappedCourses = session.selectList("allUnmappedCourses");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		return allUnmappedCourses;
	}

	@Override
	public List<SocialCategory> getAllSocialCategories() {
		SqlSession session = sqlSessionFactory.openSession();
		List<SocialCategory> socialCategories = null;
		try{
			logger.info("In getAllSocialCategories() of BackofficeDaoImpl.java");
			socialCategories = session.selectList("getAllSocialCategories");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		return socialCategories;
	}

	@Override
	public List<FeesCategory> getTemplateWiseFeesStructure(String templateCode) {
		logger.info("In getTemplateWiseFeesStructure() method of BackOfficeDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();
		List<FeesCategory> feesCategoryList = null;
		try {
			feesCategoryList = session.selectList("getTemplateWiseFeesStructure", templateCode);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error In getTemplateWiseFeesStructure() method of BackOfficeDAOImpl:Exception:: "+ e);
		} finally {
			session.close();
		}		
		return feesCategoryList;
	}

	
	/**
	 * @author anup.roy
	 * this method is to submit the standard, template, and category wise amount
	 * 06.08.2017
	 * Rewa Specific
	 * */
	
	@Override
	public String submitAmountForStudentFeesTemplate(List<StudentFeesTemplate> studentFeeTempList) {
		logger.info("In submitAmountForStudentFeesTemplate() method of BackOfficeDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();
		String submitResponse = null;
		int insertStatus = 0;
		try {
			if(null != studentFeeTempList){
				for(StudentFeesTemplate sFeeTemp : studentFeeTempList){
					for(SocialCategory scat : sFeeTemp.getFeesCategory().getSocialCategoryList()){
						scat.setSocialCategoryCode(sFeeTemp.getFeesCategory().getFeesCategoryCode());	// Setting FeesCategoryCode
						scat.setObjectId(sFeeTemp.getStudentFeesTemplateCode());				// Setting StudentFeesTemplateCode
						scat.setDesc(sFeeTemp.getCourse().getCourseName());	// Setting Course
						//scat.setStatus(sFeeTemp.getTerm().getTermName()); // removing the term 
						scat.setUpdatedBy(sFeeTemp.getUpdatedBy());
						insertStatus = session.insert("submitAmountInStudentFeesTemplate", scat);
						
					}
				}
				if(insertStatus != 0){
					submitResponse = "success";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error In submitAmountForStudentFeesTemplate() method of BackOfficeDAOImpl:Exception:: "+ e);
		} finally {
			session.close();
		}		
		return submitResponse;		
	}
	
	@Override
	public List<StudentFeesTemplate> getstudentFeesTemplateWithAmountList() {
		logger.info("In getstudentFeesTemplateWithAmountList() method of BackOfficeDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();	
		List<StudentFeesTemplate> studentFeesTemplateList = null;
		try {
			studentFeesTemplateList = session.selectList("getAmountAssignedFeesTemplateList");			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error In getstudentFeesTemplateWithAmountList() method of BackOfficeDAOImpl:Exception:: "+ e);
		} finally {
			session.close();
		}		
		return studentFeesTemplateList;
	}
	
	@Override
	public List<FeesCategory> getStudentFeesTemplateAmountDetails(String courseName) {
		logger.info("In getStudentFeesTemplateAmountDetails() method of BackOfficeDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();	
		List<FeesCategory> studentFeeTemplateAmountDetailsList = null;
		try {				
			studentFeeTemplateAmountDetailsList = session.selectList("getAmountAssignedFeesTemplateDetails", courseName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error In getStudentFeesTemplateAmountDetails() method of BackOfficeDAOImpl:Exception:: "+ e);
		} finally {
			session.close();
		}		
		return studentFeeTemplateAmountDetailsList;
	}
	
	@Override
	public String editAmountDetailsForStudentFeesTemplate(List<StudentFeesTemplate> studentFeeTempList) {
		logger.info("In editAmountDetailsForStudentFeesTemplate() method of BackOfficeDAOImpl: ");
		String submitResponse = null;
		int updateStatus = 0, insertStatus = 0;
		SqlSession session = sqlSessionFactory.openSession();					
		try {
			if(null != studentFeeTempList){
				for(StudentFeesTemplate sFeeTemp : studentFeeTempList){
					for(SocialCategory scat : sFeeTemp.getFeesCategory().getSocialCategoryList()){
						scat.setSocialCategoryCode(sFeeTemp.getFeesCategory().getFeesCategoryCode());	// Setting FeesCategoryCode
						scat.setObjectId(sFeeTemp.getStudentFeesTemplateCode());				// Setting StudentFeesTemplateCode
						scat.setDesc(sFeeTemp.getCourse().getCourseName());								// Setting Course
						scat.setUpdatedBy(sFeeTemp.getUpdatedBy());
						//System.out.println("@@@@@@@@@@@@@@@@@@"+scat.getSocialCategoryCode()+" || "+scat.getDesc()+" || "+scat.getObjectId()+" || "+scat.getUpdatedBy());
						updateStatus = session.update("inactiveAmountDetailsForStudentFeesTemplate", scat);
						//System.out.println("@@@ insertStatus ::"+updateStatus+":");
						if(updateStatus != 0){
							insertStatus = session.insert("submitAmountInStudentFeesTemplate", scat);
						}
					}					
				}
			}
			if(insertStatus != 0){
				submitResponse = Integer.toString(insertStatus);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error In editAmountDetailsForStudentFeesTemplate() method of BackOfficeDAOImpl:Exception:: "+ e);
		} finally {
			session.close();
		}		
		return submitResponse;
	}

	@Override
	public List<Standard> getAllStandardsName() {
		List<Standard> standardList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			standardList = session.selectList("getClassNameAndCodeList");
		} catch (Exception e) {
			logger.error(" Exception In  getClassNameAndCodeList() of BackOfficeDaoImpl.java",e);
		}
		return standardList;
	}
	
	@Override
	public String getCourseForClass(String standardCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String sm = "";
			try {
				List<Course> courseList = session.selectList("getCourseForClass", standardCode);
				if (courseList != null && courseList.size() != 0){
					StringBuilder sb = new StringBuilder();
					for (Course cr : courseList) {
						sb.append(cr.getCourseCode() + "*");
						sb.append(cr.getCourseName() + "#");
						sm = sb.toString().substring(0, sb.toString().length()-1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		return sm;
	}

	@Override
	public String getSectionForStandard(String standardCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String sections = null;
		try{
			List<Section> sectionList = session.selectList("getSectionForStandard",standardCode);
			if(null!= sectionList && sectionList.size()!=0){
				StringBuilder sb = new StringBuilder();
				for(Section sec : sectionList){
					sb.append(sec.getSectionCode()+"*");
					sb.append(sec.getSectionName()+"#");
					sections = sb.substring(0, sb.toString().length()-1);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return sections;
	}
	//anup.roy// for getting students against standard and section
	@Override
	public String getStudentAgainstSection(String section, String standard){
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentList = null;
		String students = null;
		try{
			Section sec = new Section();
			sec.setDesc(standard);
			sec.setSectionCode(section);
			studentList = session.selectList("getStudentAgainstSection",sec);
			if(null!=studentList && studentList.size()!=0){
				StringBuilder sb = new StringBuilder();
				for(Student s : studentList){
					sb.append(s.getRollNumber()+"*");
					sb.append(s.getStudentName()+"#");
					students = sb.substring(0, sb.toString().length()-1);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		};
		return students;
	}

	@Override
	public String getFeeStructureAgainstStudent(FeesCategory category) {
		String sm = "";
		String dateComment = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			List<FeesCategory> fc = session.selectList("getFeeStructureAgainstStudent", category);
			if (fc != null && fc.size() != 0){
				for (FeesCategory f : fc) {
					category.setFeesCategoryObjectId(f.getFeesStructureId()); // FeesCategoryCode
					SessionFees sf = session.selectOne("getIndividualPaidFee",category);
					//modification for viewing previous comments with dates of payment
					List<SessionFees> dateAndCommentList = session.selectList("getPreviousDatesAndCommentOfPayment",category);
					if (null!= sf){
						sm = sm + f.getFeesStructureId() + "*"
								+ f.getFeesCategoryName() + "*" 
								+ f.getFees() + "*" 
								+ f.getUpdatedBy() + "*"
								+ sf.getNetTotAmount() + "~";
						if(null!= dateAndCommentList && dateAndCommentList.size()!= 0){
							for(SessionFees dc : dateAndCommentList){
								dateComment = dateComment + dc.getDateOfPayment() + ":"	+ dc.getComment()+ "#";
							}
						}
						dateComment = dateComment.substring(0, dateComment.length() - 1);
						sm = sm + dateComment + "^^";
					}
					else{
						sm = sm + f.getFeesStructureId() + "*"
								+ f.getFeesCategoryName() + "*" 
								+ f.getFees() + "*" 
								+ f.getUpdatedBy() + "*" 
								+ "0.00" + "~";
						dateComment = dateComment + "No Payment History" + "#";
						sm = sm + dateComment + "^^";
					}
				}
			}
		} catch (Exception e) {
			logger.error(" Exception In  getFeeStructureForClassAndStream(AcademicYear ay) of BackOfficeDaoImpl.java",e);e.printStackTrace();
		}
		return sm;
	}

	@Override
	public String deleteStudentFeesTemplateAmountDetails(Resource r) {
		logger.info("In deleteStudentFeesTemplateAmountDetails() method of BackOfficeDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();	
		String status = null;
		try {
			int i=session.update("deleteStudentFeesTemplateAmountDetails", r);
			if(i!=0)
				status="success";
			else
				status="fail";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error In getStudentFeesTemplateAmountDetails() method of BackOfficeDAOImpl:Exception:: "+ e);
		} finally {
			session.close();
		}
		return status;
	}
	
	@Override
	public String getTeachersForAttendanceFromDB(Resource resource) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> teachers = null;
		String output = null;
		try {
			teachers = session.selectList("fetchTeachersForAttendance",resource);
			StringBuilder sb = new StringBuilder();
			for (Resource resourceone : teachers) {
				sb.append(resourceone.getTeacherName() + ",");
				sb.append(resourceone.getUserId() + "@");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
			//System.out.println("In getTeachersForAttendanceFromDB() of BackOfficeDAOImpl...LN5004 \nOutput : "+output);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return output;
	}

	@Override
	public String getDateOfCreationForInsertedTeacher(String month, String year, String teacherId) {
		//System.out.println("in 5029 daoimpl...teacherID::"+teacherId);
		SqlSession session =sqlSessionFactory.openSession();
		String attendanceDateOfCreationStr =  "";
		StudentAttendance teacherAttendance = new StudentAttendance();
		teacherAttendance.setMonth(month);
		teacherAttendance.setYear(year);
		//System.out.println("line 5035 in daoimpl:: month::"+teacherAttendance.getMonth()+"\t year::"+teacherAttendance.getYear());
		teacherAttendance.setResourceId(teacherId);
		try {
			//System.out.println("line 5038 in daoimpl:: teacher id::"+teacherAttendance.getResourceId()+"\t of month::"+teacherAttendance.getMonth()+"\t year::"+teacherAttendance.getYear());
			StudentAttendance attendanceDateOfCreation =  session.selectOne("fetchTeachersAttendanceDateOfCreation",teacherAttendance);
			//System.out.println("line 5040::"+attendanceDateOfCreation);	
			if(attendanceDateOfCreation != null){
					attendanceDateOfCreationStr = attendanceDateOfCreation.getAttendanceDesc();
				}
			//System.out.println("in getDateOfCreationForInsertedTeacher of BODAOI...LN5044 \nattendanceDateOfCreationStr::"+attendanceDateOfCreationStr);
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return attendanceDateOfCreationStr;
	}
	
	@Override
	public String insertIntoVenueTeacherMapping(Resource resource) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "success";
		int insertStatus = 0;
		try{
			resource.setObjectId(encryptDecrypt.encrypt("BackOfficeDaoimpl"));
			List<ResourceType>resourceTypeList = resource.getResourceTypeList();
			//System.out.println("resource Type list size ===="+resourceTypeList.size());
			for(ResourceType rt : resourceTypeList){
				ResourceType resourceType   = new ResourceType();
				resourceType.setResourceTypeCode(rt.getResourceTypeCode());
				resourceType.setResourceTypeName(rt.getResourceTypeName());
				//resourceType.setResourceTypeDesc(rt.getResourceTypeDesc());
				resource.setResourceType(resourceType);
				System.out.println("resource type in dao:"+resource.getResourceType().getResourceTypeCode()+"\ncourse::"+resource.getCourseName());
				
				insertStatus = session.insert("insertIntoVenueTeacherMapping", resource);
			}
			/*if(insertStatus != 0)
				status = "success";*/
		}catch (Exception e) {
			status = "fail";
			logger.error("In insertIntoVenueTeacherMapping()");
			e.printStackTrace();
		}
		System.out.println("status from daoimpl::"+status);
		return status;
	}
	
	/**************Added By Naimisha 20042017***********/
	@Override
	public String getStudentsForAssignRollNumber(String course) {
		SqlSession session = sqlSessionFactory.openSession();
		String studentString = null;
		try{
			List<Student> studentsList = session.selectList("getStudentListForACourse",course);
			if(null!= studentsList && studentsList.size()!=0){
				for(Student student : studentsList){
					studentString = studentString +"#"+ student.getRoll() + "*" + student.getStudentName() + "*" + student.getStudentCode();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return studentString;
	}

	@Override
	public String generateRollNumberForStudent(String course,String academicYear,String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "success";
		int insertStatus = 0;
		try{
			List<Student> studentsList = session.selectList("getStudentListForAssigningRollNumber",course);
			int rollNumber = 001;
			for(Student student : studentsList){
				Student studentObj = new Student();
				String pattern="000";
			    DecimalFormat myFormatter = new DecimalFormat(pattern);
			    String rollNumberString = myFormatter.format(rollNumber);
				//String rollNumberString = rollNumber + "";
				String roll = student.getStudentCode()+"/"+academicYear+"/"+student.getSection()+"/"+rollNumberString;
				System.out.println("roll====="+roll);
				studentObj.setUpdatedBy(updatedBy);
				studentObj.setRoll(roll);
				studentObj.setRegistrationId(student.getRegistrationId());
				studentObj.setCourseId(course);
				insertStatus = session.insert("insertRollNumberForStudent", studentObj);
				rollNumber = rollNumber + 1;
			}
			
			/*if(insertStatus != 0)
				status = "success";*/
		}catch (Exception e) {
			status = "fail";
			logger.error("In generateRollNumberForStudent()");
			e.printStackTrace();
		}
		//System.out.println("status from daoimpl::"+status);
		return status;
	}

	/**************Added By Naimisha 21042017***********/
	@Override
	public String addProgramPolicy(Course course) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "success";
		int insertStatus = 0;
		try{
			course.setObjectId(encryptDecrypt.encrypt("BackOfficeDaoimpl"));
			String programPolicy =  session.selectOne("getProgramPolicy");
			System.out.println("programPolicy===="+programPolicy);
			if(null == programPolicy){
				insertStatus = session.insert("insertIntoProgramPolicy", course);
			}else{
				int updateStatus = session.delete("deleteProgramPolicy");
				insertStatus = session.insert("insertIntoProgramPolicy", course);
			}
			
			
			/*if(insertStatus != 0)
				status = "success";*/
		}catch (Exception e) {
			status = "fail";
			logger.error("In insertIntoProgramPolicy()");
			e.printStackTrace();
		}
		//System.out.println("status from daoimpl::"+status);
		return status;
	}

	@Override
	public String getProgramPolicy() {
		SqlSession session = sqlSessionFactory.openSession();
		String programPolicy = null;
		try{
			programPolicy =  session.selectOne("getProgramPolicy");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return programPolicy;
	}

	@Override
	public String insertStudentCourseSectionMapping(Student student) {
		String insertStatus = "success";
		SqlSession session = sqlSessionFactory.openSession();
		try{
			student.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			//System.out.println("before inserting student:: drive::>>"+student.getDriveId()+"\nFormId::>>"+student.getStrFormId());
			session.insert("insertStudentCourseSectionMapping", student);
						
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			insertStatus = "Fail";
			logger.error(e);
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public List<Term> getTermsForfeesTemplate(String program) {
		List<Term> termListDB = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			termListDB = session.selectList("listOfTermsForFeesTmplates",program);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return termListDB;
	}

	@Override
	public List<Course> getProgramsForInterviewPanel() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Course>courseList = null;
		try{
			courseList = session.selectList("getProgramsForInterviewPanel");
			System.out.println("courseList size:"+courseList.size());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return courseList;
	}

	@Override
	public Student getCandidateDetailsAgainstUserId(String userId) {
		SqlSession session =sqlSessionFactory.openSession();
		Student student = null;
		try{
			student=session.selectOne("selectCandidateDetailsAgainstUserId",userId);
//			if(null != student){
//				if(null!=student.getStandard() && student.getStandard() != ""){
//					subjectList=session.selectList("selectSubjectsForStandard", student.getStandard());
//				}
//				student.setSubjectList(subjectList);
//	       }
		}catch(Exception e) {
			logger.error(e);
			
		}finally{
			session.close();
		}
		return student;
	}

	@Override
	public List<BookAllocation> getBookDetailsForFineList()throws CustomException {
		List<BookAllocation> bookFinedList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			bookFinedList=session.selectList("selectFinedBookDetails");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return bookFinedList;
	}

	@Override
	public List<StudentResult> getMarksOfAllSubjectForPromotion(Student student) {
		List<StudentResult> studentResultList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			studentResultList=session.selectList("getMarksOfAllSubjectForPromotion",student);
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return studentResultList;
	}

	@Override
	public String getCourseNameAgainstCourseCode(String course) {
		SqlSession session =sqlSessionFactory.openSession();
		String prgramName = null;
		try{
			prgramName = session.selectOne("getCourseNameAgainstCourseCode",course);
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return prgramName;
	}

	@Override
	public String getTermNameAgainstTermCode(String desc) {
		SqlSession session =sqlSessionFactory.openSession();
		String termName = null;
		try{
			termName = session.selectOne("getTermNameAgainstTermCode",desc);
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return termName;
	}
	
	@Override
	public List<Vendor> getBankDetails() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Vendor> bankDetailList = null;
		try {
			bankDetailList = session.selectList("selectBank");
		}catch (Exception e) {
			logger.error("getBankDetails() In BackOfficeDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return bankDetailList;
	}
	
	@Override
	public int addBank(Vendor vendor) {
		SqlSession session =sqlSessionFactory.openSession();
		int insertStatus=0;
		try {
			vendor.setVendorObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			insertStatus = session.insert("insertBank", vendor);
			if(insertStatus != 0)
				session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("addBank(Vendor vendor) In BackOdfficeDaoImpl.java: Exception",e);
		} finally {
			session.close();
		}
		return insertStatus;
	}
	
	
	@Override
	public String editBankDetails(Vendor vendor) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Update Failed.";
		try {
			
			int statusValue = session.update("editBankDetails", vendor);
			if (statusValue != 0) {
				status = "Bank update successful";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}
	
	
	@Override
	public String inactiveBankDetails(Vendor vendor) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			vendor.setVendorObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			System.out.println("IN DAO"+vendor.getBankCode());
			session.update("inactiveBankDetails", vendor);
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			
		}finally{
			session.close();
		}
		return status;
	}
	
	/**
	 * added by ranita.sur
	 * changes taken 28062017*/
	
	@Override
	public int addBank(Vendor vendor,Ledger ledger) {
		SqlSession session =sqlSessionFactory.openSession();
		int insertStatus=0;
		int insertStatus1=0;
		int statusValue=0;
		try {
			vendor.setVendorObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			insertStatus = session.insert("insertBank", vendor);
			ledger.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			ledger.setLedgerName(vendor.getBankName().toUpperCase());
			insertStatus1 = session.insert("insertinLedger", ledger);
			if(insertStatus != 0)
				session.commit();
			if(insertStatus1 != 0)
				statusValue = session.update("editinLedgerBalance", ledger);
				session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("addBank(Vendor vendor) In BackOdfficeDaoImpl.java: Exception",e);
		} finally {
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public List<Module> getModuleListFortab(String role) {
		logger.info("In getModuleListFortab() in backofficeDaoImpl");
		SqlSession session = sqlSessionFactory.openSession();
		List<Module> moduleList = null;
		try{
			moduleList = session.selectList("getModuleListFortab", role);
		}catch (Exception e) {
			logger.error("Error in getModuleListFortab() in backofficeDaoImpl",e);
			e.printStackTrace();
		}
		return moduleList;
	}
	
	@Override
	public ResourceProfile getPersonalDetailsForResourceProfile(String userId) {
		logger.info("In getPersonalDetailsForResourceProfile(userId) of BackofficeDaoImpl.java");
		SqlSession session = sqlSessionFactory.openSession();
		ResourceProfile resourceProfile = null;
		try{
			resourceProfile = session.selectOne("getPersonalDetailsForResourceProfile", userId);
		}catch (Exception e) {
			logger.error("Error in getPersonalDetailsForResourceProfile(userId) of LibraryDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return resourceProfile;
	}
	
	/**
	 * saif.ali 13072017*/
	
	@Override
	public String createMajorEvents(MajorEvents majorEvent) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			majorEvent.setMajorEventObjId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertMajorEvents", majorEvent);
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			
		}finally{
			session.close();
		}
		return status;
	}
	
	@Override
	public Term getHolidayDetailsOfAMonth(String month, String year) {
		SqlSession session =sqlSessionFactory.openSession();
		Term holidayDetials = new Term();
		try{
			month = "0"+month;
			System.out.println("LN5445 :: Month :: "+month+"\nYear :: "+year);
			Map<String, Object> parameters = new HashMap<String, Object>();			
			parameters.put("month", month.trim());
			parameters.put("year", year.trim());
			holidayDetials = session.selectOne("fetchHolidayDetailsOfAMonth", parameters);
			if(null != holidayDetials){
				List<Holiday> specialHoliday = session.selectList("fetchSpecialHolidaysList", holidayDetials);
				holidayDetials.setHoliday(specialHoliday);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return holidayDetials;
	}
	
	/**@author Saif.Ali
	 * date-17/07/2017*/
	@Override
	public List<ResourceType> getResourceType() {
		SqlSession session =sqlSessionFactory.openSession();
		List<ResourceType> resourceList = null;
		try {
			resourceList = session.selectList("selectResourceForLeavePolicy");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllResourceType() In BackOfficeDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return resourceList;
	}
	
	@Override
	public String submitLeavePolicy(LeavePolicy leavePolicy) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		String resourceTypeSelect= leavePolicy.getResourceTypeSelect();
		try{
			leavePolicy.setLeavePolicyObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			LeavePolicy leave= new LeavePolicy();
			leave= session.selectOne("leavePolicyListToShowAccordingToResouceType", resourceTypeSelect);
			if(leave!= null)
			{
				session.update("updateLeavePoilicyForResourceType",leavePolicy);
			}
			if(leave== null)
			{
				session.insert("insertLeavepolicy", leavePolicy);
			}
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			
		}finally{
			session.close();
		}
		return status;
	}
	
	/**@author Saif.Ali
	 * Date-17/07/2017
	 * Used to get the respected details according to resource type**/
	@Override
	public LeavePolicy getLeavePolicyToShow(String resourceTypeSelect) {
		SqlSession session =sqlSessionFactory.openSession();
		LeavePolicy leave= new LeavePolicy();
		try{
			leave.setLeavePolicyObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			leave= session.selectOne("leavePolicyListToShowAccordingToResouceType", resourceTypeSelect);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return leave;
	}

	/**
	 * @author anup.roy
	 * this method is for view all details about profile 
	 * 28.07.2017*/
	@Override
	public Student getStudentProfileAgainstSchoolNumber(String schoolNumber) {
		SqlSession session = sqlSessionFactory.openSession();
		Student student = null;
		Address address = null;
		try{
			logger.info("In getStudentProfileAgainstSchoolNumber() of backofficeDaoImpl.java");
			student = session.selectOne("getStudentPersonalDetails", schoolNumber);
			if(null!= student){
				address = session.selectOne("getStudentAddressForPersonalDetailsTab", schoolNumber);
				student.setAddress(address);
			}
		}catch (Exception e) {
			logger.error("In getStudentProfileAgainstSchoolNumber() of backofficeDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return student;
	}

	/**
	 * @author anup.roy
	 * this method is for submitting the session fees*/
	
	@Override
	public String updateStudentFees(List<SessionFees> sessionFessList) {
		SqlSession session =sqlSessionFactory.openSession();
		try {
			for (SessionFees sf : sessionFessList) {
				Utility util = new Utility();
				sf.setSessionFeesObjectId(util.getBase64EncodedID("BackOfficeDAOImpl"));
				int insert = session.insert("updateStudentFees", sf);
				//for entry in TWA
				
				String ledgerName = sf.getRollNumber();
				TransactionDetails trd = new TransactionDetails();
				trd.setUpdatedBy(sf.getRollNumber());
				trd.setObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
				
				double totalAmt = sf.getPayingAmount();
				
				//for student(credit)
				Ledger studentLedgerBalance = session.selectOne("selectBalanceForParentLedger", ledgerName);
				trd.setLedger(ledgerName);
				trd.setGrossAmount(studentLedgerBalance.getOpeningBal());
				
				
				if(studentLedgerBalance.getCurrentBal() < 0 ){
					/* if current balance is -ve then in credit side
					 * credit on credit = add */
					trd.setOnbasic(studentLedgerBalance.getCurrentBal() + totalAmt);
					System.out.println("LN4378 :: Student -ve(credit)  Final Amount :: "+trd.getOnbasic());
					session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
				}else{
					/* if current balance is +ve then in debit side
					 * credit on debit = subtract */ 
					trd.setOnbasic(studentLedgerBalance.getCurrentBal() - totalAmt);
					System.out.println("LN4384 :: Student +ve(debit)  Final Amount :: "+trd.getOnbasic());
					session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
				}
				TransactionDetails tranDetStudent = new TransactionDetails();
				tranDetStudent.setUpdatedBy(sf.getUpdatedBy());
				tranDetStudent.setObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
				tranDetStudent.setLedger(ledgerName);
				tranDetStudent.setAmount(totalAmt);
				tranDetStudent.setDebit(false);
				System.out.println("Total Amount :: "+totalAmt);
				System.out.println("Student Ledger :: "+ledgerName);
				System.out.println("Opening Balance :: "+studentLedgerBalance.getOpeningBal());
				System.out.println("Closing Balance :: "+studentLedgerBalance.getCurrentBal());
				
				//for Cash/Bank (Debit)
				TransactionDetails tranDetCashBank = new TransactionDetails();
				tranDetCashBank.setUpdatedBy(sf.getUpdatedBy());
				tranDetCashBank.setObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
				
				/*if(student.getPaymentMode().equalsIgnoreCase("BANK")){
					 added by sourav.bhadra on 19-19-2017 
					String bankName = student.getStudentCode();
					String bankLedger = session.selectOne("selectLedgerOfABank", bankName);;
					Ledger bankLedgerBalance = session.selectOne("selectBalanceForParentLedger", bankLedger);
					trd.setLedger(bankLedger);
					trd.setGrossAmount(bankLedgerBalance.getOpeningBal());
					
					if(bankLedgerBalance.getCurrentBal() < 0 ){
						if current balance is -ve then in credit side
						 * debit on credit = subtract 
						trd.setOnbasic(bankLedgerBalance.getCurrentBal() - totalAmt);
						System.out.println("LN4411 :: Bank -ve(credit) Final Amount :: "+trd.getOnbasic());
						session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
					}else{
						if current balance is +ve then in debit side
						 * debit on debit = add 
						trd.setOnbasic(bankLedgerBalance.getCurrentBal() + totalAmt);
						System.out.println("LN4417 :: Bank +ve(debit)  Final Amount :: "+trd.getOnbasic());
						session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
					}
					tranDetCashBank.setLedger(bankLedger);
					System.out.println("--------------LN4421 Bank---------------");
					System.out.println("Bank Ledger :: "+bankLedger);
					System.out.println("Opening Balance :: "+bankLedgerBalance.getOpeningBal());
					System.out.println("Closing Balance :: "+bankLedgerBalance.getCurrentBal());
				}else{*///for cash
					String cashLedger = "CASH";
					Ledger cashLedgerBalance = session.selectOne("selectBalanceForParentLedger", cashLedger);
					trd.setLedger(cashLedger);
					trd.setGrossAmount(cashLedgerBalance.getOpeningBal());
					
					if(cashLedgerBalance.getCurrentBal() < 0 ){
						/*if current balance is -ve then in credit side
						 * debit on credit = subtract */
						trd.setOnbasic(cashLedgerBalance.getCurrentBal() - totalAmt);
						System.out.println("LN1566 :: CASH -ve(credit) Final Amount :: "+trd.getOnbasic());
						session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
					}else{
						/*if current balance is +ve then in debit side
						 * debit on debit = add */
						trd.setOnbasic(cashLedgerBalance.getCurrentBal() + totalAmt);
						System.out.println("LN1572 :: CASH +ve(debit)  Final Amount :: "+trd.getOnbasic());
						session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
					}
					tranDetCashBank.setLedger(cashLedger);
					System.out.println("Cash Ledger :: "+cashLedger);
					System.out.println("Opening Balance :: "+cashLedgerBalance.getOpeningBal());
					System.out.println("Closing Balance :: "+cashLedgerBalance.getCurrentBal());
				//}
				tranDetCashBank.setAmount(totalAmt);
				tranDetCashBank.setDebit(true);
				
				List<TransactionDetails> transactionDetailsList = new ArrayList<TransactionDetails>();
				transactionDetailsList.add(tranDetStudent);
				transactionDetailsList.add(tranDetCashBank);
				
				String narration = "Receiving Rs:"+totalAmt+" from student as Admission Fees.";
				Transaction transaction = new Transaction();
				transaction.setObjectId(util.getBase64EncodedID("BackOfficeDaoImpl"));
				transaction.setUpdatedBy(sf.getUpdatedBy());
				transaction.setVoucherTypeCode("JOURNAL");
				transaction.setVoucherTypeName("JOURNAL");
				transaction.setNarration(narration);
				transaction.setTrDetList(transactionDetailsList);
				session.insert("createTransactionForAdmissionFeesReceive", transaction);
				/*------------------*/
				if(insert != 0){
					//Student insertId =session.selectOne("selectSubmitedStudentFees",student);
					List<FeesCategory> feesList = new ArrayList<FeesCategory>();
					FeesCategory feesCategoryObj = new FeesCategory();
					feesCategoryObj.setFeesCategoryName(sf.getSessionFeesCode());//student.getFeesCategoryList();
					feesCategoryObj.setDiscountedFees(totalAmt);
					feesList.add(feesCategoryObj);
					for(FeesCategory feesCategory:feesList){
						//feesCategory.setFeesCategoryObjectId(util.getBase64EncodedID("BackOficeDaoImpl"));
						//feesCategory.setFeesCategoryCode(util.getBase64EncodedID("BackOficeDaoImpl"));
						//feesCategory.setCheckValid(insertId.getAdmissionFormId());
						
						//session.insert("insertStudentAdmissionFeesDetails",feesCategory);
						
						TransactionalWorkingArea transactionalWorkingArea = new TransactionalWorkingArea();
						transactionalWorkingArea.setUpdatedBy(sf.getUpdatedBy());
						transactionalWorkingArea.setObjectId(util.getBase64EncodedID("AdmissionDriveDao"));
						transactionalWorkingArea.setTransactionalWorkingAreaName(feesCategory.getFeesCategoryName());
						transactionalWorkingArea.setTransactionalWorkingAreaDesc(feesCategory.getFeesCategoryName());
						transactionalWorkingArea.setNetAmount(feesCategory.getDiscountedFees());
						transactionalWorkingArea.setTransactionMode("CASH"); //Modified By Naimisha 21082017
						transactionalWorkingArea.setDescOfTransaction("ADMISSION");
						transactionalWorkingArea.setPaidAgainst(sf.getStandardName());
						transactionalWorkingArea.setDepartment("ADMISSION DEPARTMENT");
						transactionalWorkingArea.setIncomeExpense("INCOME");
						transactionalWorkingArea.setAcademicYear(feesCategory.getFeesCategoryName());
						transactionalWorkingArea.setResource(sf.getRollNumber());
						session.insert("insertTotalAdmissionFeesIntoTransactionalWorkingArea",transactionalWorkingArea);
					}
				}
				// TWA entry code ends
				session.commit();
			}
		} catch (Exception e) {
			logger.error(" Exception In  updateStudentFees(List<SessionFees> sessionFessList) of BackOfficeDaoImpl.java",e);e.printStackTrace();
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

	/**
	 * @author anup.roy
	 * this method is for getting unmapped standard to templates*/
	
	@Override
	public List<Standard> getUnmappedStandardsToFeesTemplate(String templateCode) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Standard> standardList = null;
		try{
			logger.info("In List<Standard> getUnmappedStandardsToFeesTemplate(String templateCode) in BackofficeDaoImpl.java");
			standardList = session.selectList("getUnmappedStandardsToFeesTemplate", templateCode);
		}catch (Exception e) {
			logger.error("Exception in List<Standard> getUnmappedStandardsToFeesTemplate(String templateCode) in BackofficeDaoImpl.java", e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return standardList;
	}

	/**
	 * @author anup.roy
	 * this method is for updating academic year name
	 * */
	@Override
	public String updateAcademicYear(AcademicYear academicYear) {
		String message = null;	
		SqlSession session =sqlSessionFactory.openSession();
		try {
			logger.info("In String updateAcademicYear(AcademicYear academicYear) of backofficeDaoImpl.java");
			int status=session.update("updateAcademicYear", academicYear);
			if(status==0){
					message="failed";
				}else{
					message="created";
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" Exception in String updateAcademicYear(AcademicYear academicYear) of backofficeDaoImpl.java",e);
		} finally {
			session.close();
		}
		return message;
	}
	/**Added by @author Saif.Ali Date-07/03/2018*/
	@Override
	public String getCurrentModuleName(String userId) {
		SqlSession session = sqlSessionFactory.openSession();
		String moduleName= "";
		try{
			moduleName = session.selectOne("selectModuleNameBasedOnUserId",userId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return moduleName;
	}
	
	/**Added by @author Saif.Ali Date-07/03/2018*/
	@Override
	public List<UpdateLog> getAllActivityLogList(String moduleName) {
		SqlSession session = sqlSessionFactory.openSession();
		List<UpdateLog> activiltyLogList = new ArrayList<UpdateLog>();
		try{
			activiltyLogList = session.selectList("selectAllActivityLogListModuleWise",moduleName);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return activiltyLogList;
	}
	
	//Added by naimisha 08012018
	//missing link integration 17042018
	@Override
	public String submitCategoryWithSLA(Ticket ticketObj) {
		SqlSession session =sqlSessionFactory.openSession();
		String insertStatus = "success";
		try {
				ticketObj.setTicketObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
				session.update("insertIntoCategoryWithSLA",ticketObj);
				
				
			
				session.commit();
		}catch (Exception e) {
			insertStatus = "fail";
			e.printStackTrace();
			logger.error("submitCategoryWithSLA(Ticket ticketObj) In BackOfficeDaoImpl.java: Exception",e);
		} finally {
			session.close();
		}
		return insertStatus;
	}
	//missing link integration 17042018
	@Override
	public List<Ticket> getAllCategorySLAList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Ticket> categorySLAList = null;
		try {
			logger.info("getAllCategorySLAList() method in BackOfficeDaoImpl");			
			categorySLAList = session.selectList("getAllCategorySLAList");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllCategorySLAList() method in BackOfficeDaoImpl", e);
		} finally {
			session.close();
		}
		return categorySLAList;
	}

	/**
	 * @author anup.roy
	 * this method is for getting list of all templates for JMS */
	//missing link integration 17042018
	@Override
	public List<EmailEventAndTemplate> getListOfTemplateForCategoryTemplateUserSLA() {
		List<EmailEventAndTemplate> templateList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("In getListOfTemplateForCategoryTemplateUserSLA() of BackofficeDAOImpl.java");
			templateList = session.selectList("getListOfTemplateForCategoryTemplateUserSLA");	//for the time being we are fetching only email purpose templates,later we shall pass parameters
		}catch(Exception e){
			logger.error("Exception in getListOfTemplateForCategoryTemplateUserSLA() of BackofficeDAOImpl.java", e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return templateList;
 	}

	/**
	 * @author anup.roy
	 * this method is for fetching list of resources*/
	//missing link integration 17042018
	@Override
	public List<Resource> getListOfResourceForCategoryTemplateUserSLA() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Resource> resourceList = null;
		try{
			logger.info("In getListOfResourceForCategoryTemplateUserSLA() in BackofficeDAOImpl.java");
			resourceList = session.selectList("getListOfResourceForCategoryTemplateUserSLA"); //for the time being we are fetching only admins, later we shall pass employee types 
		}catch(Exception e){
			logger.error("Exception in getListOfResourceForCategoryTemplateUserSLA() in BackofficeDAOImpl.java", e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return resourceList;
	}

	/**
	 * @author anup.roy
	 * this method is for submitting mapping of category-template-user*/
	//missing link integration 17042018
	@Override
	public String submitMapCategoryTemplateUser(CategoryAndTemplate category) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "fail";
		try{
			logger.info("In String submitMapCategoryTemplateUser(CategoryAndTemplate category) of BackofficeDaoImpl.java");
			category.setCategoryAndTemplateObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			int returnStatus = session.insert("submitMapCategoryTemplateUser", category);
			if(returnStatus!=0) status = "success";
		}catch (Exception e) {
			logger.error("Exception in String submitMapCategoryTemplateUser(CategoryAndTemplate category) of BackofficeDaoImpl.java:",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return status;
	}

	/**
	 * @author anup.roy
	 * this method is for getting list of all mapped category-template-user
	 * */
	//missing link integration 17042018
	@Override
	public List<CategoryAndTemplate> getMappedCategoryTemplateAndUserList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<CategoryAndTemplate> mappedList = null;
		try{
			logger.info("In List<CategoryAndTemplate> getMappedCategoryTemplateAndUserList() in BackofficeDaoImpl.java");
			mappedList = session.selectList("getMappedCategoryTemplateAndUserList");
		}catch (Exception e) {
			logger.error("Exception in List<CategoryAndTemplate> getMappedCategoryTemplateAndUserList() in BackofficeDaoImpl.java:", e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return mappedList;
	}
	//missing link integration 17042018
	@Override
	public String submitMapTaskWithTemplate(CategoryAndTemplate category) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "fail";
		try{
			logger.info("In String submitMapTaskWithTemplate(CategoryAndTemplate category) of BackofficeDaoImpl.java");
			category.setCategoryAndTemplateObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			int returnStatus = session.insert("submitMapTaskWithTemplate", category);
			if(returnStatus!=0) status = "success";
		}catch (Exception e) {
			logger.error("Exception in String submitMapTaskWithTemplate(CategoryAndTemplate category) of BackofficeDaoImpl.java:",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return status;
	}
	//missing link integration 17042018
	@Override
	public List<CategoryAndTemplate> getMappedTaskTemplateList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<CategoryAndTemplate> mappedList = null;
		try{
			logger.info("In List<CategoryAndTemplate> getMappedTaskTemplateList() in BackofficeDaoImpl.java");
			mappedList = session.selectList("getMappedTaskTemplateList");
		}catch (Exception e) {
			logger.error("Exception in List<CategoryAndTemplate> getMappedTaskTemplateList() in BackofficeDaoImpl.java:", e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return mappedList;
	}

	
	//Added By Naimisha 20042018
	@Override
	public List<EmailEventAndTemplate> getTemplateForATemplateType(String templateType) {
		List<EmailEventAndTemplate> templateList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("In getTemplateForATemplateType() of BackofficeDAOImpl.java");
			templateList = session.selectList("getTemplateForATemplateType",templateType);
		}catch(Exception e){
			logger.error("Exception in getTemplateForATemplateType() of BackofficeDAOImpl.java", e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return templateList;
	}

	@Override
	public List<TicketStatus> getPossibleTaskStatusListForATask(String category) {
		List<TicketStatus> taskStatusList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("In getPossibleTaskStatusListForATask() of BackofficeDAOImpl.java");
			String approvalRequired = session.selectOne("getTaskApprovalRequiredOrNot",category);
			
			String taskType = "";
			
			if(approvalRequired.equalsIgnoreCase("YES")){
				taskType = "APPROVAL";
			}else{
				taskType = "NONAPPROVAL";
			}
			
			taskStatusList = session.selectList("getPossibleTaskStatusListForATaskType",taskType);
		}catch(Exception e){
			logger.error("Exception in getPossibleTaskStatusListForATask() of BackofficeDAOImpl.java", e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return taskStatusList;
	}
	
	@Override
	public List<ResidentType> getAllResidentTypeList() {
		List<ResidentType> residentTypeList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In List<ResidentType> getAllResidentTypeList() of BackofficeDaoImpl.java");
			residentTypeList = session.selectList("getAllResidentTypeList");
		} catch (Exception e) {
			logger.error("Exception in List<ResidentType> getAllResidentTypeList() of BackofficeDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return residentTypeList;
	}
	
	/**Added by @author Saif.Ali
	 * 27/02/2018*/
	@Override
	public Student getAdmissionAndDateOfBirthDate() {
		logger.info("In getAdmissionAndDateOfBirthDate() method of BackOfficeDaoImpl");
		Student stuData = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			stuData = session.selectOne("selectDateOfAdmissionAndDateOfBirthOfLastInsertedCadet");
		} catch (Exception e) {
			logger.error("In getClassList() method of CommonDaoImpl" + e);
		} finally {
			session.close();
		}
		return stuData;
	}

	/**Added by @author Saif.Ali
	 * 27/02/2018*/
	@Override
	public String insertDisciplinaryAction(Student student) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "success";
		try{
			logger.info("In String insertDisciplinaryAction(Student student) of BackofficeDaoImpl.java");
			student.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			int returnStatus = session.insert("submitDisciplinaryActionForStudent", student);
			
		}catch (Exception e) {
			logger.error("Exception in String insertDisciplinaryAction( Student student) of BackofficeDaoImpl.java:",e);
			e.printStackTrace();
			status="fail";
		}finally {
			session.close();
		}
		return status;
	}
	

	/**
	 * @author sourav.bhadra on 27-02-2018
	 * this method is for students daily basis attendance submition */
	@Override
	public String submitStudentsDailyAttendance(List<StudentAttendance> studentAttendanceList) {
		SqlSession session = sqlSessionFactory.openSession();
		int status = 0;
		String insertStatus = "";
		for(StudentAttendance stuAttn : studentAttendanceList){
			stuAttn.setAttendanceObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			status = session.insert("insertStudentsDailyAttendance", stuAttn);
		}
		if(status != 0){
			insertStatus += "successful";
		}
		return insertStatus;
	}

	/**
	 * @author sourav.bhadra on 05-03-2018
	 * this method is for students leave details for daily attendance submit */
	@Override
	public String getStudentsLeaveDetailsForDailyAttendanceSubmit(String currentDate) {
		SqlSession session = sqlSessionFactory.openSession();
		String studentLeaveDetails = "";
		try{
			List<StudentAttendance> studentList = session.selectList("selectDetailsOfOnLeaveStudents",currentDate);
			for(StudentAttendance stuAttn : studentList){
				studentLeaveDetails += stuAttn.getStudentId() +","+ stuAttn.getAttendanceDesc() +"/";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return studentLeaveDetails;
	}
	
	/**Added by @author Saif.Ali Date-07/03/2018*/
	@Override
	public List<Student> getAllListOfDisciplinaryAction() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Student> studentListForDisciplinaryAction = new ArrayList<Student>();
		try{
			studentListForDisciplinaryAction = session.selectList("selectListOfDisciplinaryActionForStudents");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return studentListForDisciplinaryAction;
	}
	
	/**Added by @author Saif.Ali Date-07/03/2018*/
	@Override
	public  List<StudentAttendance> getStudentsRollNumbersForAlreadyAttendedStudents(Resource resource) {
		SqlSession session = sqlSessionFactory.openSession();
		List<StudentAttendance> studentList = new ArrayList<StudentAttendance>();
		try{
			studentList = session.selectList("selectDetailsOfStudentsAgainstCurrentDate",resource);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

	@Override
	public String insertDisciplinaryCode(DisciplinaryAction disciplinaryAction) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			disciplinaryAction.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			int insert = session.insert("insertDisciplinaryCode",disciplinaryAction);
			session.commit();
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			insertStatus="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<DisciplinaryAction> getAllDisciplinaryCodeList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<DisciplinaryAction> disciplinaryCodeList = new ArrayList<DisciplinaryAction>();
		try{
			disciplinaryCodeList = session.selectList("selectAllDisciplinaryCodeList");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return disciplinaryCodeList;
	}

	@Override
	public DisciplinaryAction getDescriptionAgainstDisciplinaryCode(String disciplinaryCode) {
		SqlSession session = sqlSessionFactory.openSession();
		DisciplinaryAction disciplinaryAction = null;
		try{
			disciplinaryAction = session.selectOne("selectDescriptionAgainstDisciplinaryCode",disciplinaryCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return disciplinaryAction;
	}
	
	@Override
	public String addWebIQTransaction(WebIQTransaction webIQTran){
		SqlSession session = sqlSessionFactory.openSession();
		String insertStatus = "Success";
		try{
			webIQTran.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			int status = session.insert("addWebIQTransaction",webIQTran);
			if(status == 0){
				insertStatus = "Failure";
			}
		}catch (Exception e) {
			e.printStackTrace();
			insertStatus = "Failure";
		}
		return insertStatus;
		
	}
	
	@Override
	public String getStandardNameforCourse(String courseId){
		SqlSession session = sqlSessionFactory.openSession();
		String standardName = null;
		try{
			standardName = session.selectOne("getStandardNameforCourse",courseId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return standardName;
	}
	
	@Override
	public String getHouseName(String houseCode){
		SqlSession session = sqlSessionFactory.openSession();
		String houseName = null;
		try{
			houseName = session.selectOne("getHouseName",houseCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return houseName;
	}
	
	
	@Override
	public String getMobileNumberAgainstRollNumber(String rollNumber){
		SqlSession session = sqlSessionFactory.openSession();
		String mobileNumber = null;
		try{
			mobileNumber = session.selectOne("getMobileNumberAgainstRollNumber",rollNumber);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mobileNumber;
	}
}