package com.qts.icam.service.impl.backoffice;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidationHelper;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qts.icam.model.backoffice.Holiday;
import com.qts.icam.model.backoffice.LeavePolicy;
import com.qts.icam.model.common.StudentTc;
import com.qts.icam.model.common.SessionFees;
import com.qts.icam.model.common.ITSectionGroup;
import com.qts.icam.model.common.IncomeAge;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.ITSectionDetails;
import com.qts.icam.model.common.ITSection;
import com.qts.icam.utility.qrcode.GenerateQRCodeForHallPass;
import com.qts.icam.model.common.QRCodeForHallPass;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Class;
import com.qts.icam.utility.qrcode.GenerateQRCodeForBook;
import com.qts.icam.utility.qrcode.GenerateQRCodeForStudent;
		

import com.qts.icam.utility.qrcode.GenerateQRCodeForTeacher;
import com.qts.icam.utility.report.xmlbuilder.XMLBuilder;
import com.qts.icam.model.backoffice.AttendancePolicy;
import com.qts.icam.model.backoffice.VendorRatingPolicy;
import com.qts.icam.model.backoffice.Rating;
import com.qts.icam.model.backoffice.ResidentType;
import com.qts.icam.model.backoffice.LibraryPolicy;
import com.qts.icam.model.backoffice.MajorEvents;
import com.qts.icam.model.backoffice.ResourceAttendance;
import com.qts.icam.model.backoffice.ResourceProfile;
import com.qts.icam.model.common.StudentAttendance;
import com.qts.icam.model.common.StudentFeesTemplate;
import com.qts.icam.model.backoffice.Term;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.FinancialYear;
import com.google.gson.Gson;
import com.qts.icam.dao.administrator.AdministratorDAO;
import com.qts.icam.dao.backoffice.BackOfficeDAO;
import com.qts.icam.dao.bulkdata.DataDAO;
import com.qts.icam.dao.common.CommonDao;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.administrator.EmailEventAndTemplate;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.admission.AdmissionProcess;
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
import com.qts.icam.model.backoffice.WebIQTransaction;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.backoffice.TimeTableConfigModel;
import com.qts.icam.model.backoffice.TimeTableGridData;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Address;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.CategoryAndTemplate;
import com.qts.icam.model.common.EventType;
import com.qts.icam.model.common.FeesDuration;
import com.qts.icam.model.common.Image;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.PreviousYearFinanceData;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.Scholarship;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.State;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.library.BookAllocationDetails;
import com.qts.icam.model.library.BookLanguage;
import com.qts.icam.model.report.Report;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.service.backoffice.BackOfficeService;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.bulkdata.DataUtility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.pdfbuilder.PdfBuilder;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

@Service
public class BackOfficeServiceImpl implements BackOfficeService {

	@Autowired
	Utility utility;
	
	@Autowired
	BackOfficeDAO backOfficeDAO;
	
	@Autowired
	DataUtility dataUtility;
		
	@Autowired
	DataDAO dataDAO;
	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	AdministratorDAO administratorDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	FileUploadDownload fileUploadDownload;
	
	@Autowired
	XMLBuilder xMLBuilder;
	
	@Autowired
	PdfBuilder pdfBuilder;
	
	private final static Logger logger = Logger.getLogger("BackOfficeServiceImpl.class");
	
	 List<FeesTemplate> feesTemplateList = null;
	 List<Student> studentList = null;
	 List<Candidate> candidateList = null;
	 List<ExStudents> exStudentList = null;
	 List<AcademicLeave> listLeaveTypes = null;
	 List<Term> listTermHolidays = null;
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String editAcademicYear(AcademicYear academicYear) throws CustomException {
		return backOfficeDAO.editAcademicYear(academicYear);
	}

	

	

	@Override
	public String editSocialCategory(List<SocialCategory> socialCategoryList) throws CustomException {
		return backOfficeDAO.editSocialCategory(socialCategoryList);
	}

	

	@Override
	public String addFees(Fees fees) throws CustomException {
		return backOfficeDAO.addFees(fees);
	}

	@Override
	public String editFees(List<Fees> feesList) throws CustomException {
		return backOfficeDAO.editFees(feesList);
	}

	
	@Override
	public String editFeesTemplate(FeesTemplate feesTemplate) throws CustomException {
		return backOfficeDAO.editFeesTemplate(feesTemplate);
	}

	@Override
	public List<Candidate> getDocumentVerification() throws CustomException {
		candidateList = backOfficeDAO.getDocumentVerification();
		return candidateList;
	}

	@Override
	public String approveDocument(Candidate candidate) throws CustomException {
		return backOfficeDAO.approveDocument(candidate);
	}

	@Override
	public String rejectDocument(Candidate candidate) throws CustomException {
		return backOfficeDAO.rejectDocument(candidate);
	}

	@Override
	public String checkAvailableRollNumber(String rollNumber) throws CustomException {
		return backOfficeDAO.checkAvailableRollNumber(rollNumber);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class) //07052017
	@Override
	public String addStudent(Student student) throws CustomException {
		int insertStatus = 0;
		String inStatus = "";
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Attachment attachment = null;
		try{
			/*
			 * used for set upload file  path
			 */		
			String dynamicSubFolderPath = student.getResourceUserId()+"/";
			/*String filePath = student.getResource().getUploadFile().getAttachment().getStorageRootPath();*/
			String filePath = student.getResource().getUploadFile().getAttachment().getStorageRootPath()+"/";
				   filePath = filePath+student.getResource().getUploadFile().getAttachment().getFolderName();
				   filePath = filePath+dynamicSubFolderPath;
			System.out.println(filePath);
			/*
			 * upload candidate profile photo
			 */
			String userIdOfStudent = student.getResourceUserId();
			Image image = student.getResource().getImage();
			System.out.println(image);
			if(null != image && null != image.getImageData() && image.getImageData().getOriginalFilename() != ""){
				String profilePicturePath =  filePath+"profile_image"+"/";
				image.setImagepath(profilePicturePath);
				attachment = new Attachment();
				attachment.setAttachmentType("profile_image");
				attachment.setStorageRootPath(profilePicturePath);
				logger.info("@@  profilePicturePath  ::  "+profilePicturePath);
				image = fileUploadDownload.uploadImage(image, userIdOfStudent);
				attachment.setAttachmentName(image.getReplacedImagedName());
				attachmentList.add(attachment);
			}
			/*
			 * upload file-document 
			 */
			UploadFile uploadFile = student.getResource().getUploadFile();		
			if(null != uploadFile){
				/*
				 * this is used to upload Previous Educational doc
				 */
				if(null != uploadFile.getFileData() && !uploadFile.getFileData().isEmpty()){
					String previousOrganizationDocPath =  filePath+"previous_organization_doc"+"/";
					for (CommonsMultipartFile file : uploadFile.getFileData()) {
						if(file.getOriginalFilename() != ""){
							attachment = new Attachment();
							int fileSize = fileUploadDownload.fileUpload(previousOrganizationDocPath, file);
							attachment.setStorageRootPath(previousOrganizationDocPath);
							attachment.setAttachmentType("previous_organization_doc");
							attachment.setAttachmentName(file.getOriginalFilename());
							attachment.setAttachmentSize(fileSize);
							attachmentList.add(attachment);
						}
					}
				}
			}
			if(null != student.getResource()){
				student.getResource().setAttachmentList(attachmentList);				
			}
			inStatus = backOfficeDAO.addStudent(student);
			if(inStatus.equals("success")){
				insertStatus = 1;
			}
			if(insertStatus == 0){
				fileUploadDownload.deleteDirectory(filePath);
			}
		}catch(Exception e){
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		}
		return inStatus;
	}

	
	
	@Override
	public List<Exam> getExamList() throws CustomException {
		return backOfficeDAO.getExamList();
	}

	@Override
	public List<Exam> getTermForExam() throws CustomException {
		return backOfficeDAO.getTermForExam();
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String addExamDateSetUp(Exam exam) throws CustomException {
		return backOfficeDAO.addExamDateSetUp(exam);
	}

	


@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String editStudent(Student student) throws CustomException {	
		
		int insertStatus = 0;
		String inStatus = "";
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Attachment attachment = null;
		try{
			/*
			 * used for set upload file  path
			 */		
			String dynamicSubFolderPath = student.getResourceUserId()+"/";
			/*String filePath = student.getResource().getUploadFile().getAttachment().getStorageRootPath();*/
			String filePath = student.getResource().getUploadFile().getAttachment().getStorageRootPath()+"/";
				   filePath = filePath+student.getResource().getUploadFile().getAttachment().getFolderName();
				   filePath = filePath+dynamicSubFolderPath;
			/*
			 * upload candidate profile photo
			 */
			Image image = student.getResource().getImage();
			if(null != image && null != image.getImageData() && image.getImageData().getOriginalFilename() != ""){
				String profilePicturePath =  filePath+"profile_image"+"/";
				image.setImagepath(profilePicturePath);
				logger.info("@@  profilePicturePath  ::  "+profilePicturePath);
				image = fileUploadDownload.uploadImage(image, String.valueOf(student.getUserId()));
			}
			/*
			 * upload file-document 
			 */
			UploadFile uploadFile = student.getResource().getUploadFile();		
			if(null != uploadFile){
				/*
				 * this is used to upload Previous Educational doc
				 */
				if(null != uploadFile.getFileData() && !uploadFile.getFileData().isEmpty()){
					String previousOrganizationDocPath =  filePath+"previous_organization_doc"+"/";
					for (CommonsMultipartFile file : uploadFile.getFileData()) {
						if(file.getOriginalFilename() != ""){
							attachment = new Attachment();
							int fileSize = fileUploadDownload.fileUpload(previousOrganizationDocPath, file);
							attachment.setStorageRootPath(previousOrganizationDocPath);
							attachment.setAttachmentType("previous_organization_doc");
							attachment.setAttachmentName(file.getOriginalFilename());
							attachment.setAttachmentSize(fileSize);
							attachmentList.add(attachment);
						}
					}
				}
			}
			if(null != student.getResource()){
				student.getResource().setAttachmentList(attachmentList);				
			}
			
			inStatus = backOfficeDAO.editStudent(student);
			if(inStatus.equals("Update Successful")){
				insertStatus = 1;
			}
			if(insertStatus == 0){
				fileUploadDownload.deleteDirectory(filePath);
			}
		}catch(Exception e){
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		}		
		return inStatus;
	}


	@Override
	public List<Section> getSectionListForStandard(String standard) throws CustomException {
		return commonDao.getSectionAgainstStandard(standard);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertAssignedEvent(CalendarEvent calendarEvent) throws CustomException {
		return backOfficeDAO.insertAssignedEvent(calendarEvent);

	}

	@Override
	public String getCalendarEventFromDBForAllUser(String eventType) throws CustomException {
		return backOfficeDAO.getCalendarEventFromDBForAllUser(eventType);
	}

	@Override
	public String getCalendarEventFromDBForRoleBased(String rollName,String eventType) throws CustomException {
		return backOfficeDAO.getCalendarEventFromDBForRoleBased(rollName,eventType);
	}

	@Override
	public String editAssignedEvent(CalendarEvent calendarEvent) throws CustomException {
		return backOfficeDAO.editAssignedEvent(calendarEvent);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String deleteAssignedEvent(CalendarEvent calendarEvent) throws CustomException {
		return backOfficeDAO.deleteAssignedEvent(calendarEvent);
	}

//	@Override
//	public String getSubjectsAndGroupForStandard(String standard) throws CustomException {
//		String subjectString = "";
// 		try{
// 			List<StudentSubjectMapping> subjectList = backOfficeDAO.getSubjectsAndGroupForStandard(standard);
// 			if(subjectList!=null && subjectList.size()!=0){
//				for(StudentSubjectMapping subjectObject : subjectList){
//					if(subjectObject.getSubjects()!=null && subjectObject.getSubjects().size()!=0){
//						subjectString=subjectString+subjectObject.getSubjectGroup()+"***";
//						for(Subject subject : subjectObject.getSubjects()){
//							subjectString=subjectString+subject.getSubjectCode()+"*~*"+subject.getSubjectName()+"~#~";
//						}
//						subjectString=subjectString+"~*~";
//					}
//				}
//			}else{
//				logger.info("Section not found by ajax @ getSubjectsAndGroupForStandard()In CommonServiceImpl");
//			}
// 		}catch(Exception e){
// 			e.printStackTrace();
// 			logger.error("Exception in getSubjectsAndGroupForStandard() in CommonServiceImpl ", e);
// 		}
//		return subjectString;
//	}


	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String editStudentSubjectMapping(StudentSubjectMapping studentSubjectMapping) throws CustomException {
		return backOfficeDAO.editStudentSubjectMapping(studentSubjectMapping);
	}
	
	@Override
	@RemoteMethod 
	public List<AcademicTimeTable> getTimeTableTotalDetails(
			String classNameString, String sectionNameString,
			String academicYearString) throws CustomException {
		logger.info("In erpServiceImpl getTimeTableTotalDetails(String classNameString, String sectionNameString, String academicYearString) method: ");
		Section sectionObj = new Section();
		Standard standardObj = new Standard();
		AcademicYear academicYear = new AcademicYear();
		sectionObj.setSectionCode(sectionNameString);
		standardObj.setStandardCode(classNameString);
		academicYear.setAcademicYearCode(academicYearString);
		AcademicTimeTable academicTimeTable = new AcademicTimeTable();
		academicTimeTable.setTimeTableSection(sectionObj);
		academicTimeTable.setTimeTableClass(standardObj);
		academicTimeTable.setAcademicYear(academicYear);
		
		
		List<AcademicTimeTable> detailsList = backOfficeDAO.getTimeTableDetailsFromDB(academicTimeTable);
		return detailsList;
	}

	@Override
	public String getSubjectsBasedOnStandardAndSubjectGroup(Subject subject)
			throws CustomException {
		return backOfficeDAO.getSubjectsBasedOnStandardAndSubjectGroup(subject);
	}

	@Override
	public String addTimeTable(AcademicTimeTable academicTimeTable)
			throws CustomException {
		return backOfficeDAO.addTimeTable(academicTimeTable);
	}

	@Override
	public String getTimeTableDurationSlotForValidation(
			AcademicTimeTable academicTimeTable) throws CustomException {
		return backOfficeDAO.getTimeTableDurationSlotForValidation(academicTimeTable);
	}

	@Override
	public String addSubjectBreakAndTeacherForTimeTable(
			AcademicTimeTable academicTimeTable1) throws CustomException {
		return backOfficeDAO.addSubjectBreakAndTeacherForTimeTable(academicTimeTable1);
	}

	@Override
	public String getTeacherNames(String subjectName) throws CustomException {
		return backOfficeDAO.getTeacherNames(subjectName);
	}

	@Override
	public String getTeacherConflictionForTimeTable(
			AcademicTimeTable academicTimeTable) throws CustomException {
		return backOfficeDAO.getTeacherConflictionForTimeTable(academicTimeTable);
	}

	@Override
	public List<AcademicTimeTable> getTimeTableDetails(
			AcademicTimeTable academicTimeTable) throws CustomException {
		return backOfficeDAO.getTimeTableDetails(academicTimeTable);
	}

	@Override
	public List<AcademicTimeTableDetails> getTimeTableSubjectCount(
			AcademicTimeTable academicTimeTable) throws CustomException {
		return backOfficeDAO.getTimeTableSubjectCount(academicTimeTable);
	}

	
	@Override
	@RemoteMethod 
	public List<AcademicTimeTableDetails> getTimeTableSubjectsCount(
			String classNameString, String sectionNameString,
			String academicYearString) throws CustomException {
		logger.info("In erpServiceImpl getTimeTableSubjectsCount(String classNameString, String sectionNameString, String academicYearString) method: ");
		Section sectionObj = new Section();
		Standard standardObj = new Standard();
		AcademicYear academicYear = new AcademicYear();
		sectionObj.setSectionCode(sectionNameString);
		standardObj.setStandardCode(classNameString);
		academicYear.setAcademicYearCode(academicYearString);
		AcademicTimeTable academicTimeTable = new AcademicTimeTable();
		academicTimeTable.setTimeTableSection(sectionObj);
		academicTimeTable.setTimeTableClass(standardObj);
		academicTimeTable.setAcademicYear(academicYear);
		List<AcademicTimeTableDetails> subjectCountList = backOfficeDAO.getTimeTableSubjectCount(academicTimeTable);
		return subjectCountList;
	}

	@Override
	public String deleteDraggedElementForAcademicTimeTable(
			AcademicTimeTable academicTimeTable) throws CustomException {
		return backOfficeDAO.deleteDraggedElementForAcademicTimeTable(academicTimeTable);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String updateAssignedPeriodDuration(AcademicTimeTableDetails academicTimeTable) throws CustomException {
		return backOfficeDAO.updateAssignedPeriodDuration(academicTimeTable);
	}

	@Override
	public String updateSubjectTypeTimeTable(AcademicTimeTable academicTimeTable)throws CustomException {
		return backOfficeDAO.updateSubjectTypeTimeTable(academicTimeTable);
	}
	
	@Override
	public PagedListHolder<FeesTemplate> getFeesTemplateListPaging(HttpServletRequest request) throws CustomException {
		PagedListHolder<FeesTemplate> pagedListHolder = new PagedListHolder<FeesTemplate>(feesTemplateList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize =10;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	@Override
	public List<FeesTemplate> getSearchBasedFeesTemplateList(Map<String, Object> parameters) throws CustomException {
		feesTemplateList = backOfficeDAO.getSearchBasedFeesTemplateList(parameters);
		return feesTemplateList;

	}

	@Override
	public PagedListHolder<Student> getStudentListPaging(HttpServletRequest request) throws CustomException {
		PagedListHolder<Student> pagedListHolder = new PagedListHolder<Student>(studentList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
		}

	@Override
	public List<Student> getSearchBasedStudentList(Map<String, Object> parameters) throws CustomException {
		studentList = backOfficeDAO.getSearchBasedStudentList(parameters);
		return studentList;
	}

	@Override
	public PagedListHolder<Candidate> getCandidateListPaging(HttpServletRequest request) throws CustomException {
		PagedListHolder<Candidate> pagedListHolder = new PagedListHolder<Candidate>( candidateList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	@Override
	public List<Candidate> getSearchBasedMedFitDocumentsList( Map<String, Object> parameters) throws CustomException {
		candidateList = backOfficeDAO .getSearchBasedMedFitDocumentsList(parameters);
		return candidateList;
	}

	@Override
	public String getSubjectsForTeacher(String teacher) throws CustomException {
		return backOfficeDAO.getSubjectsForTeacher(teacher);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String editTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) throws CustomException {
		return backOfficeDAO.editTeacherSubjectMapping(teacherSubjectMapping);
	}

	@Override
	public String getStudentsResultForPromotion(StudentResult studentResult) throws CustomException {
		String studentResultString = "";
 		try{
 			List<StudentResult> studentResultList = backOfficeDAO.getStudentsResultForPromotion(studentResult);
 			if(null!=studentResultList && studentResultList.size()!=0){
				for(StudentResult result : studentResultList){
					studentResultString=studentResultString+result.getRollNumber()+"***"+result.getName()+"***"+result.getPassFail()+"***"+result.getStatus()+"###";
				}
				System.out.println("studentResultString=="+studentResultString);
			}else{
				logger.info("stream not found by ajax @ getStateNameList()In CommonServiceImpl");
			}
 		}catch(Exception e){
 			logger.error("exception in getStateNameList() in CommonServiceImpl ", e);
 			e.printStackTrace();
 		}
		return studentResultString;
	}

	
	/*@Override
	public String updateStudentPromotion(List<StudentResult> studentResultList) throws CustomException {
		return backOfficeDAO.updateStudentPromotion(studentResultList);
	}*/
	
	/**
	 * @author anup.roy
	 * this method is for update student promotion*/
	
	@Override
	public String updateStudentPromotion(List<StudentResult> studentResultList, 
			String type, HttpServletResponse response) throws CustomException {
		String status = backOfficeDAO.updateStudentPromotion(studentResultList);
		/*if(type.equalsIgnoreCase("PASSOUT")){
			RepositoryStructure repositoryStructure = administratorDAO.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			System.out.println("repository==="+repository);
			File directory = new File(repository);
			boolean isExists = directory.exists();
			String insertStatus = null;
			Report report = new Report();
			if(isExists == true){
				for(StudentResult studentResult : studentResultList){
					String baseDir = repository +"/"+"TC/"+ studentResult.getStandard()+"/"+studentResult.getSection()+"/";
					
					String fileName = studentResult.getRollNumber();
					System.out.println("baseDir==="+baseDir);
					
					String xmlFilePath = baseDir+"xml/";
					String xmlFileName = fileName.replaceAll("/", "-")+".xml";
					System.out.println("xmlFileName==="+xmlFileName);
					report.setXmlFilePath(xmlFilePath);
					report.setXmlFileName(xmlFileName);
					Student student = new Student();
					//String courseName = backOfficeDAO.getCourseNameAgainstCourseCode(studentResult.getStandard());
					student.setStandard(studentResult.getStandard());
					student.setSection(studentResult.getSection());
					
					List<StudentResult> studentResultListNew = backOfficeDAO.getMarksOfAllSubjectForPromotion(student);
					System.out.println("studentResultListNew===="+studentResultListNew.size());
					student.setStudentResultList(studentResultListNew);
					student.setRoll(studentResult.getRollNumber());
					report.setStudent(student);
					xMLBuilder.createXMLFileForStudentPromotion(report);
					
					String xsltFilePath = repository +"/"+"TC/"+ studentResult.getStandard()+"/"+studentResult.getSection()+"/xslt/";
					String xsltFileName = "result.xsl";
					report.setXsltFilePath(xsltFilePath);
					report.setXsltFileName(xsltFileName);
					String pdfFilePath = repository +"/"+"TC/"+ studentResult.getStandard()+"/"+studentResult.getSection()+"/pdf/"+fileName.replaceAll("/", "-")+"/";
					String pdfFileName = fileName.replaceAll("/", "-")+".pdf";
					System.out.println("pdfFilePath==="+pdfFilePath);
					report.setPdfFilePath(pdfFilePath);
					report.setPdfFileName(pdfFileName);
					try{
						pdfBuilder.createPDF(report);
					}catch (IOException e) {
							logger.error(e);
					}	
						
					if ((new File(report.getPdfFilePath()+report.getPdfFileName())).exists()) {
						if (Desktop.isDesktopSupported()) {
							fileUploadDownload.downloadFile(response, report.getPdfFilePath(), report.getPdfFileName());
						}
					}
				}
			}
		}*/
		return status;
	}






	@Override
	public List<StudentResult> getViewPendingPromotion() throws CustomException {
		return backOfficeDAO.getViewPendingPromotion();
	}


	@Override
	public String addTC(StudentTC studentTC) throws CustomException {
		return backOfficeDAO.addTC(studentTC);
	}

	@Override
	public List<ExStudents> searchExStudents(Map<String, String> parameters) throws CustomException {
		exStudentList = backOfficeDAO.searchExStudents(parameters);
		return exStudentList;
	}

	@Override
	public PagedListHolder<ExStudents> exStudentsPagination(
			HttpServletRequest request) {
		PagedListHolder<ExStudents> pagedListHolder = new PagedListHolder<ExStudents>(exStudentList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}
	
	@Override
	public int deleteSelectedAttachment(int attachId) throws CustomException {
		return backOfficeDAO.deleteSelectedAttachment(attachId);
	}
	
	@Override
	public List<EventType> getEventType() {
		return backOfficeDAO.getEventType();
	}

	@Override
	public String getCalendarEventFromDBForPersonal(String eventType,String userId) {
		return backOfficeDAO.getCalendarEventFromDBForPersonal(eventType,userId);
	}
	

	@Override
	public int insertStudentSubjectMappExcelBulkData(String excelFile,	String updatedBy) throws CustomException {
		int insertStatus = 0;
		int studentSubjectMappInsertStatus = 0;
		try{
			logger.info("In insertStudentSubjectMappExcelBulkData(String excelFile, String updatedBy) method of BackOfficeServiceImpl");
			
			List<StudentSubjectMapping> studentSubjectMappList = dataUtility.readExcelFileStudentSubjectMapping(excelFile);					
			if(studentSubjectMappList.size() != 0){
				studentSubjectMappInsertStatus = dataDAO.batchInsertForStudentSubjectMapp(studentSubjectMappList, updatedBy);
				logger.info("@@ studentSubjectMappInsertStatus  ::  "+studentSubjectMappInsertStatus);
			}
			if(studentSubjectMappInsertStatus != 0){
				insertStatus = 1;
			}
		}catch(Exception e){
			logger.error("Exception in insertStudentSubjectMappExcelBulkData() to read excel and insert in BackOfficeServiceImpl", e);
			e.printStackTrace();
		}
		return insertStatus;
	}

//Leave

	@Override
	public String insertLeaveType(AcademicLeaveCategory academicLeaveCategory) {
		return backOfficeDAO.insertLeaveType(academicLeaveCategory);
	}

	@Override
	public String deleteLeaveType(AcademicLeaveCategory academicLeaveCategory) {
		return backOfficeDAO.deleteLeaveType(academicLeaveCategory);
	}

	@Override
	public String editLeaveType(AcademicLeaveCategory academicLeaveCategory) {
		return backOfficeDAO.editLeaveType(academicLeaveCategory);
		
	}

	@Override
	public String getPreviousLeaveType() {
		return backOfficeDAO.getPreviousLeaveType();
	}

	@Override
	public List<AcademicLeave> getLeaveTypeList() {
		listLeaveTypes = backOfficeDAO.getLeaveTypeList();
		return listLeaveTypes;
	}

	@Override
	public PagedListHolder<AcademicLeave> showListLeaveStructurePaging(
			HttpServletRequest request) {
		PagedListHolder<AcademicLeave> pagedListHolder = new PagedListHolder<AcademicLeave>(listLeaveTypes);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	@Override
	public List<AcademicLeaveCategory> getLeavetypes() {
		return backOfficeDAO.getleavetypes();
	}

	@Override
	public String insertLeaveStructure(AcademicLeave academicLeave) {
		return backOfficeDAO.insertLeaveStructure(academicLeave);
	}

	@Override
	public List<AcademicLeave> listLeave(AcademicLeave academicLeave) {
		return backOfficeDAO.listLeave(academicLeave);
	}

	@Override
	public String updateLeaveStructure(AcademicLeave academicLeave) {
		return backOfficeDAO.updateLeaveStructure(academicLeave);
	}
	@Override
	public int insertStudentDetailsExcelBulkData(String excelFile, String updatedBy) throws CustomException {
		int insertStatus = 0;
		int studentDetailsInsertStatus = 0;
		int studentAddressInsertStatus = 0;
		try{
			logger.info("In insertStudentDetailsExcelBulkData(String excelFile, String updatedBy) method of BackOfficeServiceImpl");
			
			List<Student> studentDetailsList = dataUtility.readExcelFileForStudentDetails(excelFile);					
			if(studentDetailsList.size() != 0){
				studentDetailsInsertStatus = dataDAO.batchInsertForStudentDetails(studentDetailsList, updatedBy);
				logger.info("@@ studentDetailsInsertStatus  ::  "+studentDetailsInsertStatus);
			}
			if(studentDetailsInsertStatus != 0){
				int cityStatus = 0;
				int districtStatus = 0;
				
				List<String> cityList = dataUtility.readExcelFileForStudentCityList(excelFile);
				if(null != cityList){
					cityStatus = dataDAO.batchInsertForCity(cityList, updatedBy);
					logger.info("cityStatus :: "+cityStatus);
				}				
				List<String> districtList = dataUtility.readExcelFileForStudentDistrictList(excelFile);
				if(null != districtList){
					districtStatus = dataDAO.batchInsertForDistrict(districtList, updatedBy);
					logger.info("districtStatus :: "+districtStatus);
				}
				List<Address> studentAddressList = dataUtility.readExcelFileForStudentAddressDetails(excelFile);
				if(studentAddressList.size() != 0){
					studentAddressInsertStatus = dataDAO.batchInsertForStudentAddressDetails(studentAddressList, updatedBy);
					logger.info("@@ studentAddressInsertStatus  ::  "+studentAddressInsertStatus);
				}
			}
			if(studentDetailsInsertStatus != 0 && studentAddressInsertStatus != 0){
				insertStatus = 1;
			}
		}catch(Exception e){
			logger.error("Exception in insertStudentDetailsExcelBulkData() to read excel and insert in BackOfficeServiceImpl", e);
			e.printStackTrace();
		}
		return insertStatus;
	}

	@Override
	public List<StudentSubjectMapping> getStudentSubjectMappingList()throws CustomException {
		return backOfficeDAO.getStudentSubjectMappingList();
	}

	@Override
	public List<Student> getStudentsInStudentSubjectMapping(Student student)throws CustomException {
		return backOfficeDAO.getStudentsInStudentSubjectMapping(student);
	}

	@Override
	public List<Student> getStudentsNotInStudentSubjectMapping(Student student)throws CustomException {
		return backOfficeDAO.getStudentsNotInStudentSubjectMapping(student);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String insertClassSubjectMapping(List<StudentSubjectMapping> mappingList) throws CustomException {
		return backOfficeDAO.insertClassSubjectMapping(mappingList);
	}
	
	
	@Override
	public NoticeBoard viewNotice(NoticeBoard noticeBoard) {
		return backOfficeDAO.viewNotice(noticeBoard);
	}
	
	@Override
	public Standard getStudentRollAgainstStandardAndSection(Student student) throws CustomException {
		return backOfficeDAO.getStudentRollAgainstStandardAndSection(student);
	}

	@Override
    public boolean writeExcelForStudentSubjectMapping(Standard standardDB, String excelPath, String studentSubjectMappingExcel) throws CustomException {
        boolean status = false;
        try {
            System.out.println(" In Service =>_ "+standardDB+"  ::  "+excelPath+"  ::  "+studentSubjectMappingExcel);
            if(null != excelPath && null != studentSubjectMappingExcel){
                excelPath = excelPath + studentSubjectMappingExcel;
                if(null != standardDB){
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    DataValidation dataValidation = null;
                    DataValidationConstraint constraint = null;
                    DataValidationHelper validationHelper = null;
                   
                    //Create a blank sheet           
                    System.out.println("Standard.. "+standardDB.getStandardName());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    if(null != standardDB.getSection()){
                        System.out.println("Section..... "+standardDB.getSection());

                        HSSFSheet sheet = workbook.createSheet(standardDB.getStandardName()+"-"+standardDB.getSection());     
                        validationHelper = new HSSFDataValidationHelper(sheet);
                        constraint = validationHelper.createExplicitListConstraint(new String[]{"TRUE","FALSE"});
                       
                        sheet.autoSizeColumn(5);
                        sheet.protectSheet("secretPassword");   
                                                               
                        HSSFFont hSSFFont = workbook.createFont();
                        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
                        hSSFFont.setFontHeightInPoints((short) 12);
                        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                        hSSFFont.setColor(HSSFColor.BLUE.index);
                       
                        /* cell style for locking */
                        CellStyle lockedCellStyle = workbook.createCellStyle();
                        lockedCellStyle.setFont(hSSFFont);
                        lockedCellStyle.setLocked(true);                           
                       
                        Row row = sheet.createRow(0);                               
                        Cell cell = row.createCell(0);                           
                        cell.setCellValue("Roll No.");
                        cell.setCellStyle(lockedCellStyle);
                       
                        int rownum = 1;
                        int cellNum = 1;
//                        for(Student student : standardDB.getStudentList()){
                            for(Subject subject : standardDB.getStudentList().get(0).getSubjectList()){
                                Cell cell2 = row.createCell(cellNum++);
                                cell2.setCellValue(subject.getSubjectName());
                                cell2.setCellStyle(lockedCellStyle);                                                               
                                System.out.println("Subject.. "+subject.getSubjectName()+"  ..  "+cellNum);
                            }
//                        }                                   
                       
                        hSSFFont = workbook.createFont();
                        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
                        hSSFFont.setFontHeightInPoints((short) 10);
                        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                        hSSFFont.setColor(HSSFColor.BLACK.index);
                       
                        lockedCellStyle = workbook.createCellStyle();
                        lockedCellStyle.setFont(hSSFFont);
                        lockedCellStyle.setLocked(true);                           
                       
                        rownum = 1;
                        for(Student student : standardDB.getStudentList()){                           
                            Row row2 = sheet.createRow(rownum++);
                            Cell cell2 = row2.createCell(0);
                            cell2.setCellValue(student.getRollNumber());
                            cell2.setCellStyle(lockedCellStyle);   
                            System.out.println("Roll Num....... "+student.getRollNumber()+"  ..  "+rownum);                                   
                        }
                       
                        hSSFFont = workbook.createFont();
                        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
                        hSSFFont.setFontHeightInPoints((short) 10);
                        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
                        hSSFFont.setColor(HSSFColor.BLACK.index);
                       
                        CellStyle unLockedCellStyle = workbook.createCellStyle();
                        unLockedCellStyle.setFont(hSSFFont);
                        unLockedCellStyle.setLocked(false);
                       
                        for(int start = 1; start<rownum; start++){
                            for(int st = 1; st<cellNum; st++){
                                Row row4 = sheet.getRow(start);
                                Cell cell4 = row4.createCell(st);
                                cell4.setCellType(HSSFCell.CELL_TYPE_BOOLEAN);
                                cell4.setCellValue(true);
                                cell4.setCellStyle(unLockedCellStyle);
                            }
                        }
                       
                        CellRangeAddressList addressList = new  CellRangeAddressList(1,--rownum,1,--cellNum);
                        dataValidation = validationHelper.createValidation(constraint, addressList);
                        dataValidation.setSuppressDropDownArrow(false);     
                        sheet.addValidationData(dataValidation);
                       
                        HSSFRow rows = workbook.getSheetAt(0).getRow(0);
                        for(int colNum = 0; colNum<rows.getLastCellNum(); colNum++){  
                            workbook.getSheetAt(0).autoSizeColumn(colNum);
                        }
                       
                        FileOutputStream out = new FileOutputStream(new File(excelPath));
                        workbook.write(out);
                        out.flush();
                        out.close();
                        status = true;
                        System.out.println("studentSubjectMappingExcel.xlsx written successfully on "+excelPath);
                        logger.info("studentSubjectMappingExcel.xlsx written successfully on "+excelPath);                                           
                    }
                } else{
                    logger.error("Exception in writeExcelForFunctionalityRoleMapping() in administratorService for Excel download");
                }                   
            }
        } catch (Exception e) {
            CustomException.exceptionHandler(e);
            status = false;
        }
        return status;
    }	
	
	@Override
	public List<AcademicLeave> getEmployeeCompleteLeaveDetails(AcademicLeave academicLeave) {
		return backOfficeDAO.getEmployeeCompleteLeaveDetails(academicLeave);
	}
	
	
	@Override
	public List<StudentSubjectMapping> listUpdatedStudentSubjectMapping(Student student) throws CustomException {
		return backOfficeDAO.listUpdatedStudentSubjectMapping(student);
	}
	
	
	
	
	
	
	
	
	
//--------------------FOR NOTICE: DONE BY SINGH-------------------
	@Override
	public String createNotice(NoticeBoard noticeBoard) {
		return backOfficeDAO.createNotice(noticeBoard);
	}
	
	@Override
	public String updateNotice(NoticeBoard noticeBoard) {
		return backOfficeDAO.updateNotice(noticeBoard);
	}
	
	@Override
	public String deleteNotice(NoticeBoard noticeBoard) {
		return backOfficeDAO.deleteNotice(noticeBoard);
	}
	
	
	
	
	
	
	
	
	
	
	//singh.backoffice
	
	
	@Override
	public String addSocialCategory(SocialCategory socialCategory) throws CustomException {
		return backOfficeDAO.addSocialCategory(socialCategory);
	}
	
	@Override
	public String editSocialCategory(SocialCategory socialCategory) throws CustomException {
		return backOfficeDAO.editSocialCategory(socialCategory);
	}
	
	@Override
	public List<VendorType> getVendorTypes() {
		return backOfficeDAO.getVendorTypes();
	}
	
	
	@Override
	public String addVendor(Vendor vendor) {
		return backOfficeDAO.addVendor(vendor);
	}
	
	@Override
	public String updateVendorDetails(Vendor vendor) {
		return backOfficeDAO.updateVendorDetails(vendor);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//anup.backoffice
	
	@Override
	public List<Leave> getLeave() throws CustomException {
		return backOfficeDAO.getLeave();
	}
	
	
	@Override
	public String editLeave(Leave leave) throws CustomException {
		return backOfficeDAO.editLeave(leave);
	}
	
	@Override
	public List<Fees> getFeesList() throws CustomException {
		return backOfficeDAO.getFeesList();
	}
	
	@Override
	public List<AcademicYear> getAcademicYearList() throws CustomException {
		return backOfficeDAO.getAcademicYearList();
	}
	
	@Override
	public String editFees(Fees fees) throws CustomException {
		return backOfficeDAO.editFees(fees);
	}
	
	@Override
	public List<SocialCategory> getSocialCategoryList() throws CustomException {
		return backOfficeDAO.getSocialCategoryList();
	}
	
	@Override
	public List<FeesTemplate> getFeesTemplateList() throws CustomException {
		feesTemplateList = backOfficeDAO.getFeesTemplateList();
		return feesTemplateList;
	}
	
	@Override
	public String addFeesTemplate(FeesTemplate feesTemplate) throws CustomException {
		return backOfficeDAO.addFeesTemplate(feesTemplate);
	}
	
	@Override
	public FeesTemplate getFeesTemplateDetails(String templateCode) throws CustomException {
		return backOfficeDAO.getFeesTemplateDetails(templateCode);
	}
	
	@Override
	public List<Student> getStudentList() throws CustomException {
		studentList = backOfficeDAO.getStudentList();
		return studentList;
	}
	
	@Override
	public Student getStudentListForEdit(Student student) {
		logger.info("In getStudentListForEdit(Resource resource) method of BackOfficeServiceImpl");
		Student studentDetailsForView = backOfficeDAO.getStudentListForEdit(student);
		return studentDetailsForView;
	}
	
	
	@Override
	public List<Candidate> getFeesPaidCandidate() throws CustomException {
		return backOfficeDAO.getFeesPaidCandidate();
	}
	
	
	//form sms
	
	@Override
	public String createNewFinancialYear(FinancialYear financialYear) {
		return backOfficeDAO.createNewFinancialYear(financialYear);
	}
	
	@Override
	public String updateFinancialYear(FinancialYear financialYear) {
		return backOfficeDAO.updateFinancialYear(financialYear);		
	}
	
	@Override
	public List<Course> listCourse() {
		return backOfficeDAO.listCourse();
	}
	
	@Override
	public void updateWorkingDaysForCheckedCloseDay(Term term) {
		backOfficeDAO.updateWorkingDaysForCheckedCloseDay(term);
	}
	
	@Override
	public void insertHolidays(Term term) {
		logger.info("In  insertHolidays(Term term) method of BackOfficeServiceImpl");
		backOfficeDAO.insertHolidays(term);
	}
	
	@Override
	public List<Term> listTermHolidaysForShow() {
		listTermHolidays = backOfficeDAO.listTermHolidaysForShow();
		return listTermHolidays;
	}
	
	@Override
	public PagedListHolder<Term> showListSpecialDaysPaging(HttpServletRequest request) {
		logger.info("In showListSpecialDaysPaging(HttpServletRequest request) method of BackOfficeServiceImpl");
		PagedListHolder<Term> pagedListHolder = new PagedListHolder<Term>(listTermHolidays);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}
	
	@Override
	public void updateWorkingDays(Term term) {
		logger.info("In  updateWorkingDays(Term term) method of BackOfficeServiceImpl");
		backOfficeDAO.updateWorkingDays(term);
	}
	
	@Override
	public List<Term> listTermDetails() {
		logger.info("In  listTermDetails() method of BackOfficeServiceImpl");
		List<Term> listTermFormService = null;
		try {
			listTermFormService = backOfficeDAO.listTermDetails();
		} catch (Exception e) {
			logger.info("Error in BackOfficeServiceImpl listTermDetails()  Exception: "+ e);
		}
		return listTermFormService;
	}
	
	@Override
	public List<CalendarEvent> listSpecialHolidays() {
		List<CalendarEvent> specialHolidays = null;
		try {
			specialHolidays = backOfficeDAO.getlistSpecialHolidays();

			logger.info("listSpecialHolidays() IN SERVICE");
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return specialHolidays;
	}
	
	
	@Override
	public String getClassForAttendance() {
		logger.info("getClassForAttendance() IN SERVICE");
		String showClass = null;
		try {
			showClass = backOfficeDAO.getClassForAttendanceFromDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showClass;
	}
	
	@Override
	public String fetchTeacherId(StudentAttendance studentAttendance) {
		return backOfficeDAO.fetchTeacherId(studentAttendance);
	}
	
	@Override
	public String getTeacherDetailsForAttendance(StudentAttendance studentAttendance) {
		return backOfficeDAO.getTeachingLevgetTeacherDetailsForAttendanceelForAttendance(studentAttendance);
	}
	
	@Override
	public String getCourseForAttendance(String className) {
		return backOfficeDAO.getCourseForAttendance(className);
	}
	
//	@Override
//	public String getStreamForAttendance(String streamlist) {
//		logger.info("getStreamForAttendance() IN SERVICE");
//		String showStream = null;
//		try {
//			showStream = backOfficeDAO.getStreamForAttendanceFromDB(streamlist);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return showStream;
//	}
	
	
	@Override
	public String getSectionForAttendance(Resource resource) {
		logger.info("getSectionForAttendance() IN SERVICE");
		String showSection = null;
		try {
			showSection = backOfficeDAO.getSectionForAttendanceFromDB(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showSection;
	}
	
	
	@Override
	public String fetchStudentId(StudentAttendance studentAttendance) {
		logger.info("fetchStudentId() IN SERVICE");
		String showStudentid = null;
		try {
			showStudentid = backOfficeDAO.fetchStudentIdFromDB(studentAttendance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showStudentid;
	}
	
	
	@Override
	public String getStudentsForAttendance(Resource resource) {
		logger.info("getStudentsForAttendance() IN SERVICE");
		String showStudent = null;
		try {
			showStudent = backOfficeDAO.getStudentsForAttendanceFromDB(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showStudent;
	}
	
	
	@Override
	public String getDateOfCreationForInsertedStudent(String month,
			String year, String studentId) {
		return backOfficeDAO.getDateOfCreationForInsertedStudent(month, year, studentId);
	}
	
	@Override
	public String getDateOfCreationForInsertedResource(String month,
			String year, String userId, String shift,String resourceType) {
		return backOfficeDAO.getDateOfCreationForInsertedResource(month, year, userId, shift, resourceType);
	}
	
	@Override
	public void updateStudentAttendance(StudentAttendance studentAttendance) {
		logger.info("updateStudentAttendance() IN SERVICE");
		backOfficeDAO.updateStudentAttendanceFromDB(studentAttendance);
	}
	
	@Override
	public void insertStudentAttendance(StudentAttendance studentAttendance) {
		logger.info("insertStudentAttendance() IN SERVICE");
		backOfficeDAO.insertStudentAttendanceFromDB(studentAttendance);
	}
	
	@Override
	public void updateTeacherAttendance(
			StudentAttendance studentAttendanceObject) {
		backOfficeDAO.updateTeacherAttendance(studentAttendanceObject);
	}
	
	
	@Override
	public void insertTeacherAttendance(
			StudentAttendance studentAttendanceObject) {
		backOfficeDAO.insertTeacherAttendance(studentAttendanceObject);
	}
	
	@Override
	public String getWorkShiftForAttendance() {
		return backOfficeDAO.getWorkShiftForAttendance();
	}
	
	@Override
	public String fetchTeacherAttendance(ResourceAttendance resourceAttendance) {
		return backOfficeDAO.fetchTeacherAttendance(resourceAttendance);
	}
	
	@Override
	public int uploadResourceAttendance(String actualFolderPathForUpload,String updatedBy) {
		int resourceAttendanceStatus = 0;
		try{
			DataUtility util = new DataUtility();
			List<ResourceAttendance> resourceAttendanceList = util.readExcelForResourceAttendance(actualFolderPathForUpload);
			if(resourceAttendanceList!=null){
				resourceAttendanceStatus = dataDAO.batchInsertResourceAttendance(resourceAttendanceList,updatedBy);
			}
			System.out.println("resource attendance in service :: "+resourceAttendanceStatus);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("In  uploadResourceAttendance( String actualFolderPathForUpload,String updatedBy)batchInsertResourceAttendance() method of BackOfficeServiceImpl",e);
		}
		return resourceAttendanceStatus;
	}
	
	
	@Override
	public LibraryPolicy showLibraryPolicy(LibraryPolicy libraryPolicy) {
		return backOfficeDAO.showLibraryPolicy(libraryPolicy);
	}
	
	@Override
	public LibraryPolicy saveLibraryPolicy(LibraryPolicy libraryPolicy) {
		return backOfficeDAO.saveLibraryPolicy(libraryPolicy);
	}
	
	@Override
	public List<AcademicYear> getCurrentAcademicYear() {
		logger.info("In  getAcademicYear() method of BackOfficeServiceImpl");
		List<AcademicYear> academicYearFormService = null;
		try {
			academicYearFormService = backOfficeDAO.getCurrentAcademicYear();
		} catch (Exception e) {
			logger.info("Error in BackOfficeServiceImpl getAcademicYear()  Exception: "+ e);
		}
		return academicYearFormService;
	}
	
	
	@Override
	public List<Rating> getRatingPolicy(Rating r) {
		return backOfficeDAO.getRatingPolicy(r);
	}
	
	@Override
	public List<Rating> saveRatingPolicy(List<Rating> ratingList) {
		return backOfficeDAO.saveRatingPolicy(ratingList);
	}
	
	@Override
	public VendorRatingPolicy showVendorPolicy(VendorRatingPolicy vendorRatingPolicy) {
		return backOfficeDAO.showVendorPolicy(vendorRatingPolicy);
	}
	
	@Override
	public VendorRatingPolicy saveVendorPolicy(VendorRatingPolicy vendorRatingPolicy) {
		return backOfficeDAO.saveVendorPolicy(vendorRatingPolicy);
	}
	
	@Override
	public AttendancePolicy getAttendancePloicy() {
		return backOfficeDAO.getAttendancePloicy();
	}
	
	@Override
	public void saveAttendancePolicy(AttendancePolicy attendancePolicy) {
		backOfficeDAO.saveAttendancePolicy(attendancePolicy);
	}
	
	@Override
	public Exam getExamPloicy() {
		return backOfficeDAO.getExamPloicy();
	}
	
	@Override
	public void saveExamPolicy(Exam exam) {
		backOfficeDAO.saveExamPolicy(exam);
	}
	
	@Override
	public void generateQrCodeForTeacher(List<String> attributeColumnForTeacher, String path) {
		DateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        String date1 = null;
        Date date2 = null;
        try {
               Date date = new Date();
               date1 =  dateFormatter.format(date);
               System.out.println("date1:"+date1);
               date2 = dateFormatter.parse(date1);
               System.out.println("date2:"+date2);
        } catch (java.text.ParseException e) {
               e.printStackTrace();
        }
      Timer timer = new Timer();
	  timer.schedule(new GenerateQRCodeForTeacher(backOfficeDAO, attributeColumnForTeacher,path), date2);		
	}
	
	
	@Override
	public void generateQrCodeForStudent(List<String> attributeColumnForBook, String path) {
		DateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        String date1 = null;
        Date date2 = null;
        try {
               Date date = new Date();
               date1 =  dateFormatter.format(date);
               System.out.println("date1:"+date1);
               date2 = dateFormatter.parse(date1);
               System.out.println("date2:"+date2);
        } catch (java.text.ParseException e) {
               e.printStackTrace();
        }
      Timer timer = new Timer();
		timer.schedule(new GenerateQRCodeForStudent(backOfficeDAO, attributeColumnForBook,path), date2);	
	}
	
	
	@Override
	public void generateQrCodeForBook(List<String> attributeColumnForBook, String path) {
		DateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        String date1 = null;
        Date date2 = null;
        try {
               Date date = new Date();
               date1 =  dateFormatter.format(date);
               System.out.println("date1:"+date1);
               date2 = dateFormatter.parse(date1);
               System.out.println("date2:"+date2);
        } catch (java.text.ParseException e) {
               e.printStackTrace();
        }
      Timer timer = new Timer();
	    timer.schedule(new GenerateQRCodeForBook(backOfficeDAO, attributeColumnForBook,path), date2);
	}
	
	
	@Override
	public AcademicYear getAcademicYearClassAndExamType() {
		return backOfficeDAO.getAcademicYearClassAndExamType();
	}
	
	
	@Override
	public String getCourseInClass(String classCode) {
		return backOfficeDAO.getCourseInClass(classCode);
	}
	
	@Override
	public String getTermForCourse(String courseCode) {
		return backOfficeDAO.getTermForCourse(courseCode);
	}
	
	@Override
	public String getExamsForTermCourseAndExamType(Exam ex) {
		return backOfficeDAO.getExamsForTermCourseAndExamType(ex);
	}
	
	@Override
	public String getSectionForClassAndStream(Class klass) {
		return backOfficeDAO.getSectionForClassAndStream(klass);
	}
	
	@Override
	public String getStudentForClassStreamSectionAndCourse(Resource resource) {
		return backOfficeDAO.getStudentForClassStreamSectionAndCourse(resource);
	}
	
	@Override
	public void generateQrCodeForHallPass(List<QRCodeForHallPass> dataString, String path) {
		DateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		String date1 = null;
        Date date2 = null;
          try {
                 Date date = new Date();
                 date1 =  dateFormatter.format(date);
                 System.out.println("date1:"+date1);
                 date2 = dateFormatter.parse(date1);
                 System.out.println("date2:"+date2);
          } catch (java.text.ParseException e) {
                 e.printStackTrace();
          }
        Timer timer = new Timer();
	   	timer.schedule(new GenerateQRCodeForHallPass(dataString,path), date2);
	}
	
	@Override
	public AcademicYear selectCurrentAcademicYear() {
		return backOfficeDAO.selectCurrentAcademicYear();
	}
	
	@Override
	public List<Class> getClassNameAndCodeList() {
		return backOfficeDAO.getClassNameAndCodeList();
	}
	
	@Override
	public String getSectionAgainstClassAndStream(Section section) {
		return backOfficeDAO.getSectionAgainstClassAndStream(section);
	}
	
	@Override
	public String getResourceAgainstSection(Section sectionCode,String course) {
		return backOfficeDAO.getResourceAgainstSection(sectionCode,course);
	}
	
	@Override
	public String checkWheatherFeesPaid(SessionFees sf) {
		return backOfficeDAO.checkWheatherFeesPaid(sf);
	}
	
	@Override
	public String grantTC(StudentTc studentTc) {	
		return backOfficeDAO.grantTC(studentTc);
	}
	
	@Override
	public List<String> getBookIdForQrCode(String strQuery) {
		return backOfficeDAO.getBookIdForQrCode(strQuery);
	}
	
	@Override
	public String getTermDate(String termid) {
		logger.info("In getTermDate(String courseid) method of BackOfficeServiceImpl");
		String dateList = null;
		try {
			dateList = backOfficeDAO.getTermDate(termid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateList;
	}
	
	@Override
	public String getTermList(String courseCode) {
		return backOfficeDAO.getTermList(courseCode);
	}
	
	@Override
	public List<Scholarship> getScholarshipList() throws CustomException {
		return backOfficeDAO.getScholarshipList();
	}
	
	@Override
	public String addScholarship(Scholarship scholarship) throws CustomException {
		return backOfficeDAO.addScholarship(scholarship);
	}

	@Override
	public String editScholarship(Scholarship scholarship) throws CustomException {
		return backOfficeDAO.editScholarship(scholarship);
	}
	
	@Override
	@RemoteMethod 
	public Student getCandidateDetailsAgainstFromId(Resource resource) throws CustomException {
		return backOfficeDAO.getCandidateDetailsAgainstFromId(resource);
	}

	@Override
	public Student getStudentDetails(String rollNumber) throws CustomException {
		return backOfficeDAO.getStudentDetails(rollNumber);
	}
	
	@Override
	public List<AcademicYear> getAcademicYearForTerm() {
		logger.info("In  getAcademicYear() method of BackOfficeServiceImpl");
		List<AcademicYear> academicYearFormService = null;
		try {
			academicYearFormService = backOfficeDAO.getAcademicYearForTerm();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return academicYearFormService;
	}
	
	@Override
	public List<Term> getTermNameList() {
		logger.info("In  getTermNameList() method of BackOfficeServiceImpl");
		List<Term> termNameFormService = null;
		try {
			termNameFormService = backOfficeDAO.getTermNameList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return termNameFormService;
	}
	
	@Override
	public void insertAcademicTerm(Term term) {
		logger.info("insertAcademicTerm() IN SERVICE");
		backOfficeDAO.insertAcademicTermFromDB(term);
	}
	
	@Override
	public void editAcademicTermType(Term term) {
		logger.info("editAcademicTermType() IN SERVICE");
		backOfficeDAO.editAcademicTermTypeFromDB(term);

	}
	
	@Override
	public List<Term> specifictermforsingle(Term term) {
		logger.info("In  specifictermforsingle(Term term) method of BackOfficeServiceImpl");
		List<Term> TermsFormService = null;
		try {
			TermsFormService = backOfficeDAO.specifictermforsingle(term);
		} catch (Exception e) {
			logger.info("Error in BackOfficeServiceImpl specifictermforsingle(Term term)  Exception: "
					+ e);
		}
		return TermsFormService;
	}
	
	
	@Override
	public List<Holiday> listspeHolidays(Holiday holi) {
		logger.info("In  listspeHolidays(Holiday holi) method of BackOfficeServiceImpl");
		List<Holiday> holidaysspeFormService = null;
		holidaysspeFormService = backOfficeDAO.listspeHolidays(holi);
		return holidaysspeFormService;
	}
	
	@Override
	public List<Standard> getclassListForTermCreation() {
		return backOfficeDAO.getclassListForTermCreation();
	}
	
	@Override
	public List<Course> getcourseListForTermCreation(String strClass) {
		List<Course> courseList = backOfficeDAO.getcourseListForTermCreationFromDB(strClass);
		return courseList;
	}
	
	@Override
	public void updatePublicHoliday(Holiday holiday) {
		logger.info("In  updatePublicHoliday(Holiday holiday) method of BackOfficeServiceImpl");
		backOfficeDAO.updatePublicHoliday(holiday);
	}
	
	
//-----------------------FOR TIME TABLE BY SAIKAT DAS-----------------
	@Override
	public List<Resource> getTeacherList() {
		return backOfficeDAO.getTeacherList();
	}
	
	@Override
	public List<Standard> getStandardList() {
		return backOfficeDAO.getStandardList();
	}
	
	@Override
	public List<Subject> getSubjectList() {
		return backOfficeDAO.getSubjectList();
	}
	
	@Override
	public List<TimeTableGridData> getAllTimeTableGridData(){
		return backOfficeDAO.getAllTimeTableGridData();
	}
	
	@Override
	public TimeTableGridData getTimeTableGridData(TimeTableGridData timeTableGridData){
		return backOfficeDAO.getTimeTableGridData(timeTableGridData);
	}
	
	@Override
	public void insertTimeTableGridData(TimeTableGridData timeTableGridData){
		backOfficeDAO.insertTimeTableGridData(timeTableGridData);
	}
	
	@Override
	public void updateTimeTableGridData(TimeTableGridData timeTableGridData){
		backOfficeDAO.updateTimeTableGridData(timeTableGridData);
	}
	
	@Override
	public List<TimeTableConfigModel> addTimeTableConfigData(List<TimeTableConfigModel> timeTableConfigModelList, String updatedBy){
		List<TimeTableConfigModel> timeTableConfigModelListtDB = null;
		try {
			timeTableConfigModelListtDB = backOfficeDAO.addTimeTableConfigData(timeTableConfigModelList, updatedBy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeTableConfigModelListtDB;
	}
	
	@Override
	public List<TimeTableConfigModel> getAllTimeTableList(){
		return backOfficeDAO.getAllTimeTableList();
	}
	
	@Override
	public String editAndUpdateTimeTable(TimeTableConfigModel timtab) {
		return backOfficeDAO.editAndUpdateTimeTable(timtab);
	}
	
	@Override
	public String deleteClassForTeacher(String detailsId) throws CustomException {		
		return backOfficeDAO.deleteClassForTeacher(detailsId);
	}
///////--------------------FOR LIBRARY FINE------------------
	@Override
	public List<BookAllocation> getIssuedBookDetails(Resource resource) {
		List<BookAllocation> issuedBookList = null;
		Utility util = new Utility();
		try {
			logger.info("List<BookAllocation> getIssuedBookDetails(Resource resource) method in In BackOfficeServiceImpl");
			issuedBookList = backOfficeDAO.getIssuedBookDetails(resource);
			if (issuedBookList != null) {
				for (BookAllocation bookAllocation : issuedBookList) {
					for (BookAllocationDetails bookAllocationDetails : bookAllocation.getBookAllocationDetails()) {
						if (bookAllocationDetails.getBookIssueDate() != null) {
							bookAllocation.setBookIssueDate(util.epochToHumanReadableFormat(new Integer(bookAllocationDetails.getBookIssueDate()).toString()));
						}
						if (bookAllocationDetails.getBookReturnDate() != null) {
							bookAllocation.setBookReturnDate(util.epochToHumanReadableFormat(new Integer(bookAllocationDetails.getBookReturnDate()).toString()));
						}					
						if (bookAllocationDetails.getActualReturnDate() != null) {
							bookAllocationDetails.setActualReturnDate(util.epochToHumanReadableFormat(new Integer(bookAllocationDetails.getActualReturnDate()).toString()));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return issuedBookList;
	}
	
	
	@Override
	public List<BookAllocation> submitLibrayFine(Resource resource, PreviousYearFinanceData previousYearFinanceData) {
		List<BookAllocation> fineSubmit = null;
		Utility util = new Utility();
		try {
			logger.info("List<BookAllocation> submitLibrayFine(Resource resource) method in In BackOfficeServiceImpl");
			fineSubmit = backOfficeDAO.submitLibrayFine(resource, previousYearFinanceData);
			if (fineSubmit != null) {
				for (BookAllocation bookAllocation : fineSubmit) {
					for (BookAllocationDetails bookAllocationDetails : bookAllocation.getBookAllocationDetails()) {
						if (bookAllocationDetails.getBookIssueDate() != null) {
							bookAllocation.setBookIssueDate(util.epochToHumanReadableFormat(new Integer(bookAllocationDetails.getBookIssueDate()).toString()));
						}
						if (bookAllocationDetails.getBookReturnDate() != null) {
							bookAllocation.setBookReturnDate(util.epochToHumanReadableFormat(new Integer(bookAllocationDetails.getBookReturnDate()).toString()));
						}
						if (bookAllocationDetails.getActualReturnDate() != null) {
							bookAllocationDetails.setActualReturnDate(util
									.epochToHumanReadableFormat(new Integer(bookAllocationDetails.getActualReturnDate()).toString()));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fineSubmit;
	}
	
///-----------------------------------START IT SECTION---------------------------
	
	@Override
	public List<ITSection> viewAllITSections() {
		return backOfficeDAO.viewAllITSections();
	}
	
	@Override
	public String createITSections(ITSection itSection) {
		return backOfficeDAO.createITSections(itSection);
	}

	@Override
	public String updateITSection(ITSection itSection) {
		return backOfficeDAO.updateITSection(itSection);
	}

	@Override
	public List<ITSectionDetails> getRebatesForITSection(String itSectionCode) {
		return backOfficeDAO.getRebatesForITSection(itSectionCode);
	}

	@Override
	public String submitITSectionRebates(ITSection itSection) {
		return backOfficeDAO.submitITSectionRebates(itSection);
	}

	@Override
	public String editITSectionRebates(ITSection itSection, ITSection itSecNew) {
		return backOfficeDAO.editITSectionRebates(itSection, itSecNew);
	}

	@Override
	public List<IncomeAge> getIncomeAgeList() {
		return backOfficeDAO.getIncomeAgeList();
	}

	@Override
	public List<ITSection> getUnmappedITSections(ITSectionGroup itSectionGroup) {
		return backOfficeDAO.getUnmappedITSections(itSectionGroup);
	}

	@Override
	public String insertITSectionDeductionAmount(ITSectionGroup itSectionGroup) {
		return backOfficeDAO.insertITSectionDeductionAmount(itSectionGroup);
	}

	@Override
	public List<ITSectionGroup> getITSectionGroupForAgeYear(ITSectionGroup itSectionGrp) {
		return backOfficeDAO.getITSectionGroupForAgeYear(itSectionGrp);
	}

	@Override
	public String checkITSecDetailAmount(ITSectionGroup itSectionGrp) {
		return backOfficeDAO.checkITSecDetailAmount(itSectionGrp);
	}

	@Override
	public ITSectionGroup getITSectionForITGroups(ITSectionGroup itSectionGrp) {
		return backOfficeDAO.getITSectionForITGroups(itSectionGrp);
	}

	@Override
	public List<ITSectionDetails> getITSectionDetailForITSecs(ITSection itSec) {
		return backOfficeDAO.getITSectionDetailForITSecs(itSec);
	}

	@Override
	public String submitITSectionRebateAmounts(ITSectionGroup itSectionGroup) {
		return backOfficeDAO.submitITSectionRebateAmounts(itSectionGroup);
	}

	@Override
	public ITSectionGroup getRebateAmountDetailForITGroup(ITSectionGroup itSectionGrp) {
		return backOfficeDAO.getRebateAmountDetailForITGroup(itSectionGrp);
	}

	@Override
	public String updateITSectionRebateAmountLimit(ITSectionGroup itSectionGroup) {
		return backOfficeDAO.updateITSectionRebateAmountLimit(itSectionGroup);
	}
	
///-----------------------------------END IT SECTION--------------------
	
///----------------------------START STUDENT TC----------------------
	
	@Override
	public String getNameStandardSectionForRollNumber(String rollNumber)throws CustomException {
		return backOfficeDAO.getNameStandardSectionForRollNumber(rollNumber);
	}

	@Override
	public String getStudentFeesPaymentStatus(String rollNumber) throws CustomException {
		return backOfficeDAO.getStudentFeesPaymentStatus(rollNumber);
	}
	
//----------------------------END STUDENT TC-----------------------	
	
	@Override
	public String checkSocialCategoryName(String socialCategoryName){
		return backOfficeDAO.checkSocialCategoryName(socialCategoryName);
	}
	
	@Override
	public List<AdmissionForm> admissionDriveListForFeesSubmission() {
		List<AdmissionForm>  admissionDriveListForFeesSubmission = null;
		logger.info("admissionDriveListForFeesSubmission()() method in In BackOfficeServiceImpl ");
		admissionDriveListForFeesSubmission = backOfficeDAO.admissionDriveListForFeesSubmission();
		return admissionDriveListForFeesSubmission;
	}
	/**
	 * Added by Naimisha.. for IT rebate*/
	
	@Override
	public String inactiveItRebate(String itSection) {
		 String status = backOfficeDAO.inactiveItRebate(itSection);
		return null;
	}
	
	/***
	 * added by parma for receive fees*/
	
	@Override
	public AdmissionProcess getRegistrationIdFormListClassForFeesSubmission(
			AdmissionProcess admissionProcess) {
		logger.info("getRegistrationIdFormListClassForFeesSubmission(AdmissionProcess admissionProcess) method in In BackOfficeServiceImpl");
		return backOfficeDAO.getRegistrationIdFormListClassForFeesSubmission(admissionProcess);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public List<Resource> submitFeesForStudent(Student student) {
		logger.info(" submitFeesForStudent(Student student) method in In BackOfficeServiceImpl");
		return backOfficeDAO.submitFeesForStudent(student);
	}

	@Override
	public Resource getCandidateDetails(Resource resource) {
		logger.info(" getCandidateDetails(Resource resource) method in In BackOfficeServiceImpl");
		return backOfficeDAO.getCandidateDetails(resource);
	}

	@Override
	public List<FeesCategory> getFeeStructureForStudent(Resource resource) {
		logger.info(" getFeeStructureForStudent(Resource resource) method in In BackOfficeServiceImpl");
		return backOfficeDAO.getFeeStructureForStudent(resource);
	}





	@Override
	public List<ResourceType> getResourceTypes() {
		return backOfficeDAO.getResourceTypes();
	}



	/***
	 * @author anup.roy
	 * for attendance calendar*/
	

	@Override
	public String getStudentsForView(Resource resource) {
		logger.info("getStudentsForAttendance() IN SERVICE");
		String showStudent = null;
		try {
			showStudent = backOfficeDAO.getStudentsForView(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showStudent;
	}
	
	@Override
	public AttendanceDetails getResourceAbsentDateRecord(AttendanceDetails attendanceDetails) {		
		return backOfficeDAO.getResourceAbsentDateRecord(attendanceDetails);
	}





	@Override
	public AttendanceDetails getTeacherAttendanceRecord(
			AttendanceDetails attendanceDetails) {
		return backOfficeDAO.getTeacherAttendanceRecord(attendanceDetails);
	}
	
	@Override
	public List<Course> getCourseListForCreateStudent() {
		return backOfficeDAO.getCourseListForCreateStudent();
	}

	@Override
	public String getFormIdAgainstCourseId(String courseId,String driveName) {
		return backOfficeDAO.getFormIdAgainstCourseId(courseId,driveName); //Modified by naimisha 18082017
	}





	@Override
	public String getAdmissionDriveNameAgainstCourseId(String courseId) {
		return backOfficeDAO.getAdmissionDriveNameAgainstCourseId(courseId);
	}


	/**
	 * @author anup.roy
	 * new fees portion starts**/
	/**new*/
	@Override
	public List<FeesCategory> selectCategory() {
		logger.info("In  selectCategory() method of BackOfficeServiceImpl");
		List<FeesCategory> feesCategoryFromDB = backOfficeDAO.selectCategory();
		return feesCategoryFromDB;
	}
		
	@Override
	public List<FeesDuration> selectFeesDuration() {
		logger.info("In  insertCategory( List<FeesDuration> selectFeesDuration() method of BackOfficeServiceImpl");
		return backOfficeDAO.selectFeesDuration();
	}

	@Override
	public String insertCategory(FeesCategory feescategory) {
		logger.info("In  insertCategory( FeesCategory feescategory() method of BackOfficeServiceImpl");
		return backOfficeDAO.insertCategory(feescategory);
	}

	@Override
	public String editFeesStructure(FeesCategory feescategory) {
		logger.info("In  editFeesStructure( FeesCategory feescategory() method of BackOfficeServiceImpl");
		return backOfficeDAO.editFeesStructure(feescategory);
	}

	@Override
	public List<StudentFeesTemplate> studentFeesTemplateList() {
		return backOfficeDAO.studentFeesTemplateList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String createNewFeesTemplate(StudentFeesTemplate studentFeesTemp) {
		return backOfficeDAO.createNewFeesTemplate(studentFeesTemp);
	}

	@Override
	public String editStudentFeesTemplates(StudentFeesTemplate studentFeesTemplate) {
		return backOfficeDAO.editStudentFeesTemplates(studentFeesTemplate);
	}

	@Override
	public List<StudentFeesTemplate> getAllFeesTemplates() {
		return backOfficeDAO.getAllFeesTemplates();
	}

	@Override
	public List<Course> getAllUnmappedCourses() {
		return backOfficeDAO.getAllUnmappedCourses();
	}

	@Override
	public List<SocialCategory> getAllSocialCategories() {
		return backOfficeDAO.getAllSocialCategories();
	}

	@Override
	public List<FeesCategory> getTemplateWiseFeesStructure(String templateCode) {
		return backOfficeDAO.getTemplateWiseFeesStructure(templateCode);
	}

	@Override
	public String submitAmountForStudentFeesTemplate(List<StudentFeesTemplate> studentFeeTempList) {
		return backOfficeDAO.submitAmountForStudentFeesTemplate(studentFeeTempList);
	}

	@Override
	public List<StudentFeesTemplate> getstudentFeesTemplateWithAmountList() {
		return backOfficeDAO.getstudentFeesTemplateWithAmountList();
	}

	@Override
	public List<FeesCategory> getStudentFeesTemplateAmountDetails(String courseName) {
		return backOfficeDAO.getStudentFeesTemplateAmountDetails(courseName);
	}

	@Override
	public String editAmountDetailsForStudentFeesTemplate(List<StudentFeesTemplate> studentFeeTempList) {
		return backOfficeDAO.editAmountDetailsForStudentFeesTemplate(studentFeeTempList);
	}

	@Override
	public List<Standard> getAllStandardsName() {
		return backOfficeDAO.getAllStandardsName();
	}
	
	@Override
	public String getCourseForClass(String standardCode) {
		return backOfficeDAO.getCourseForClass(standardCode);
	}

	@Override
	public String getSectionForStandard(String standardCode) {
		return backOfficeDAO.getSectionForStandard(standardCode);
	}
	//anup.roy for getting students against standard and section
	@Override
	public String getStudentAgainstSection(String section , String standard) {
		return backOfficeDAO.getStudentAgainstSection(section, standard);
	}

	@Override
	public String getFeeStructureAgainstStudent(FeesCategory category) {
		return backOfficeDAO.getFeeStructureAgainstStudent(category);
	}
	
	@Override
	public String deleteStudentFeesTemplateAmountDetails(Resource r) {
		return backOfficeDAO.deleteStudentFeesTemplateAmountDetails(r);
	}
	
	@Override
	public String getTeachersForAttendance(Resource resource) {
		logger.info("getTeachersForAttendance() IN BACKOFFICESERVICE");
		String showStudent = null;
		try {
			System.out.println("getTeachersForAttendance() in BackOfficeService...LN2139");
			showStudent = backOfficeDAO.getTeachersForAttendanceFromDB(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showStudent;
	}
	
	@Override
	public String getDateOfCreationForInsertedTeacher(String month, String year, String teacherId) {
		return backOfficeDAO.getDateOfCreationForInsertedTeacher(month, year, teacherId);
	}

	@Override
	public String insertIntoVenueTeacherMapping(Resource resource) {
		return backOfficeDAO.insertIntoVenueTeacherMapping(resource);
	}
	
	/***********Added By Naimisha 20042017**********/

	@Override
	public String getStudentsForAssignRollNumber(String course) {
		return backOfficeDAO.getStudentsForAssignRollNumber(course);
	}
	
	@Override
	public String generateRollNumberForStudent(String course, String academicYear,String updatedBy) {
		return backOfficeDAO.generateRollNumberForStudent(course,academicYear,updatedBy);
	}




	/***********Added By Naimisha 21042017**********/
	@Override
	public String addProgramPolicy(Course course) {
		return backOfficeDAO.addProgramPolicy(course);
	}


	@Override
	public String getProgramPolicy() {
		return backOfficeDAO.getProgramPolicy();
	}


	@Override
	public String insertStudentCourseSectionMapping(Student student) {
		return backOfficeDAO.insertStudentCourseSectionMapping(student);
	}
	
	@Override
	public String getTermsForfeesTemplate(String program) {
		List<Term>termList = backOfficeDAO.getTermsForfeesTemplate(program);
		String terms = "";
		if(null!=termList && termList.size()!=0){
			for(Term term:termList){
				terms = terms+term.getTermCode()+"#@#"+term.getTermName()+"*~*";
			}
		}
		return terms;
	}





	@Override
	public List<Course> getProgramsForInterviewPanel() {
		return backOfficeDAO.getProgramsForInterviewPanel();
	}





	@Override
	public Student getCandidateDetailsAgainstUserId(String userId) {
		
		return backOfficeDAO.getCandidateDetailsAgainstUserId(userId);
	}


	@Override
	public List<BookAllocation> getBookDetailsForFineList() throws CustomException {
		return backOfficeDAO.getBookDetailsForFineList();
	}

	@Override
	public List<Vendor> getBankDetails()throws CustomException {
		 return backOfficeDAO.getBankDetails();
	}
	
	@Override
	public int addBank(Vendor vendor) {
		return backOfficeDAO.addBank(vendor);
	}
	
	@Override
	public String editBankDetails(Vendor vendor) {
		return backOfficeDAO.editBankDetails(vendor);
	}
	
	@Override
	public String inactiveBankDetails(Vendor vendor) {
		return backOfficeDAO.inactiveBankDetails(vendor);
	}

	@Override
	public int addBank(Vendor vendor,Ledger ledger) {
		return backOfficeDAO.addBank(vendor,ledger);
	}
	
	@Override
	public List<Module> getModuleListFortab(String role) {
		return backOfficeDAO.getModuleListFortab(role);
	}
	
	@Override
	public ResourceProfile getPersonalDetailsForResourceProfile(String userId) {
		return backOfficeDAO.getPersonalDetailsForResourceProfile(userId);
	}
	
	@Override
	public String createMajorEvents(MajorEvents majorEvent) {
		return backOfficeDAO.createMajorEvents(majorEvent);
	}

	@Override
	public Term getHolidayDetailsOfAMonth(String month, String year) {
		return backOfficeDAO.getHolidayDetailsOfAMonth(month, year);
	}
	
	@Override
	public List<ResourceType> getResourceType(){
		return backOfficeDAO.getResourceType();
	}
	
	@Override
	public String submitLeavePolicy(LeavePolicy leavePolicy){
		return backOfficeDAO.submitLeavePolicy(leavePolicy);
	}
	
	@Override
	public LeavePolicy getLeavePolicyToShow(String resourceTypeSelect){
		return backOfficeDAO.getLeavePolicyToShow(resourceTypeSelect);
	}

	/**
	 * @author anup.roy
	 * method for getting studentProfile details 
	 * 28.07.2017*/
	@Override
	public Student getStudentProfileAgainstSchoolNumber(String schoolNumber) {
		return backOfficeDAO.getStudentProfileAgainstSchoolNumber(schoolNumber);
	}
	
	 //Added By Naimisha 08032018
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String updateStudentFees(List<SessionFees> sessionFessList) {
		return backOfficeDAO.updateStudentFees(sessionFessList);
	}

	/**
	 * @author anup.roy
	 * this method is for getting unmapped standard for template */
	@Override
	public String getUnmappedStandardsToFeesTemplate(String templateCode) {
		String standard = "";
		List<Standard> standardList = backOfficeDAO.getUnmappedStandardsToFeesTemplate(templateCode);
		for(Standard st : standardList){
			standard = standard+ st.getStandardCode()+"*"+ st.getStandardName()+"#";
		}
		return standard;
	}

	/**
	 * @author anup.roy
	 * this method is for updating academic year name*/
	@Override
	public String updateAcademicYear(AcademicYear academicYear) {
		return backOfficeDAO.updateAcademicYear(academicYear);
	}

	/**Added by @author Saif.Ali  Date- 12/03/2018*/
	public String getCurrentModuleName(String userId)
	{
		return backOfficeDAO.getCurrentModuleName(userId);
	}
	
	/**Added by @author Saif.Ali  Date- 12/03/2018*/
	public List<UpdateLog> getAllActivityLogList(String moduleName)
	{
		return backOfficeDAO.getAllActivityLogList(moduleName);
	}
	
	//missing link integration 17042018
	@Override
	public String submitCategoryWithSLA(Ticket ticketObj) {
		return backOfficeDAO.submitCategoryWithSLA(ticketObj);
	}




	//missing link integration 17042018
	@Override
	public List<Ticket> getAllCategorySLAList() {
		return backOfficeDAO.getAllCategorySLAList();
	}

	/**
	 * @author anup.roy*/
	//missing link integration 17042018
	@Override
	public List<EmailEventAndTemplate> getListOfTemplateForCategoryTemplateUserSLA() {
		return backOfficeDAO.getListOfTemplateForCategoryTemplateUserSLA();
	}

	/**
	 * @author anup.roy
	 * */
	//missing link integration 17042018
	@Override
	public List<Resource> getListOfResourceForCategoryTemplateUserSLA() {
		return backOfficeDAO.getListOfResourceForCategoryTemplateUserSLA();
	}
	/**
	 * @author anup.roy*/	
	//missing link integration 17042018
	@Override
	public String submitMapCategoryTemplateUser(CategoryAndTemplate category) {
		return backOfficeDAO.submitMapCategoryTemplateUser(category);
	}
	/**
	 * @author anup.roy*/	
	//missing link integration 17042018
	@Override
	public List<CategoryAndTemplate> getMappedCategoryTemplateAndUserList() {
		return backOfficeDAO.getMappedCategoryTemplateAndUserList();
	}




//Added by naimisha 30012018
	//missing link integration 17042018
	@Override
	public String submitMapTaskWithTemplate(CategoryAndTemplate category) {
		return backOfficeDAO.submitMapTaskWithTemplate(category);
	}




	//missing link integration 17042018
	@Override
	public List<CategoryAndTemplate> getMappedTaskTemplateList() {
		return backOfficeDAO.getMappedTaskTemplateList() ;
	}





	@Override
	public List<EmailEventAndTemplate> getTemplateForATemplateType(String templateType) {
		return backOfficeDAO.getTemplateForATemplateType(templateType);
	}



//Added by Naimisha 04052018

	@Override
	public List<TicketStatus> getPossibleTaskStatusListForATask(String category) {
		return backOfficeDAO.getPossibleTaskStatusListForATask(category);
	}
	
	@Override
	public List<ResidentType> getAllResidentTypeList() {
		return backOfficeDAO.getAllResidentTypeList();
	}
	

	/**Added by saif.Ali Date- 27/02/2018*/
	@Override
	public Student getAdmissionAndDateOfBirthDate() {
		return backOfficeDAO.getAdmissionAndDateOfBirthDate();
	}
	
	/**Added by saif.Ali Date- 28/02/2018*/
	@Override
	public String insertDisciplinaryAction(Student student) {
		return backOfficeDAO.insertDisciplinaryAction(student);
	}

	/**
	 * @author sourav.bhadra on 27-02-2018
	 * this method is for students daily basis attendance submition */
	@Override
	public String submitStudentsDailyAttendance(List<StudentAttendance> studentAttendanceList) {
		return backOfficeDAO.submitStudentsDailyAttendance(studentAttendanceList);
	}

	/**
	 * @author sourav.bhadra on 05-03-2018
	 * this method is for students leave details for daily attendance submit */
	@Override
	public String getStudentsLeaveDetailsForDailyAttendanceSubmit(String currentDate) {
		return backOfficeDAO.getStudentsLeaveDetailsForDailyAttendanceSubmit(currentDate);
	}
	
	/**Added by @author Saif.Ali Date-07/03/2018*/
	@Override
	public List<Student> getAllListOfDisciplinaryAction(){
		return backOfficeDAO.getAllListOfDisciplinaryAction();
	}
	
	/**Added by @author Saif.Ali Date-07/03/2018*/
	@Override
	public String getStudentsRollNumbersForAlreadyAttendedStudents(Resource resource){
		logger.info("getStudentsRollNumbersForAlreadyAttendedStudents() IN SERVICE");
		String showStudent = "";
		List<StudentAttendance> studentAttendanceList = backOfficeDAO.getStudentsRollNumbersForAlreadyAttendedStudents(resource);
		for(StudentAttendance st : studentAttendanceList){
			showStudent = showStudent+st.getStudentId()+"*";
		}
		System.out.println("showStudent=="+showStudent);
		return showStudent;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String insertDisciplinaryCode(DisciplinaryAction disciplinaryAction) {
		return backOfficeDAO.insertDisciplinaryCode(disciplinaryAction);
	}

	@Override
	public List<DisciplinaryAction> getAllDisciplinaryCodeList() {
		return backOfficeDAO.getAllDisciplinaryCodeList();
	}

	@Override
	public DisciplinaryAction getDescriptionAgainstDisciplinaryCode(String disciplinaryCode) {
		return backOfficeDAO.getDescriptionAgainstDisciplinaryCode(disciplinaryCode);
	}
	
	@Override
	public String addWebIQTransaction(WebIQTransaction webIQTran){
		return backOfficeDAO.addWebIQTransaction(webIQTran);
	}

	@Override
	public String getStandardNameforCourse(String courseId){
		return backOfficeDAO.getStandardNameforCourse(courseId);
	}
	
	@Override
	public String getHouseName(String houseCode){
		return backOfficeDAO.getHouseName(houseCode);
	}
	
	@Override
	public String getMobileNumberAgainstRollNumber(String rollNumber){
		return backOfficeDAO.getMobileNumberAgainstRollNumber(rollNumber);
	}
}	