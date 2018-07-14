package com.qts.icam.dao.backoffice;

import java.util.List;
import java.util.Map;

import com.qts.icam.model.backoffice.Holiday;
import com.qts.icam.model.backoffice.LeavePolicy;
import com.qts.icam.model.common.StudentTc;
import com.qts.icam.model.common.SessionFees;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.ITSectionGroup;
import com.qts.icam.model.common.IncomeAge;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.ITSectionDetails;


import com.qts.icam.model.common.ITSection;
import com.qts.icam.model.common.TeacherForQRCode;
import com.qts.icam.model.common.StudentForQRCode;
import com.qts.icam.model.common.BookForQRCode;
import com.qts.icam.model.common.Class;
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
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.CategoryAndTemplate;
import com.qts.icam.model.common.EventType;
import com.qts.icam.model.common.FeesDuration;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.PreviousYearFinanceData;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.Scholarship;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.library.BookLanguage;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.utility.customexception.CustomException;

public interface BackOfficeDAO {

	String editAcademicYear(AcademicYear academicYear) throws CustomException;

	String editSocialCategory(List<SocialCategory> socialCategoryList) throws CustomException;

	String addFees(Fees fees) throws CustomException;

	String editFees(List<Fees> feesList) throws CustomException;

	String editFeesTemplate(FeesTemplate feesTemplate) throws CustomException;

	List<Candidate> getDocumentVerification() throws CustomException;

	String approveDocument(Candidate candidate) throws CustomException;

	String rejectDocument(Candidate candidate) throws CustomException;

	String checkAvailableRollNumber(String rollNumber) throws CustomException;
	
	String addStudent(Student student) throws CustomException;
	
	List<Exam> getExamList() throws CustomException;

	List<Exam> getTermForExam() throws CustomException;

	String addExamDateSetUp(Exam exam) throws CustomException;
	
	String insertAssignedEvent(CalendarEvent calendarEvent) throws CustomException;

	String getCalendarEventFromDBForAllUser(String eventType) throws CustomException;

	String getCalendarEventFromDBForRoleBased(String rollName, String eventType) throws CustomException;

	String editAssignedEvent(CalendarEvent calendarEvent) throws CustomException;

	String deleteAssignedEvent(CalendarEvent calendarEvent) throws CustomException;

	//List<StudentSubjectMapping> getSubjectsAndGroupForStandard(String standard) throws CustomException;

	String editStudentSubjectMapping(StudentSubjectMapping studentSubjectMapping) throws CustomException;
	
	List<AcademicTimeTable> getTimeTableDetailsFromDB(AcademicTimeTable academicTimeTable) throws CustomException;

	String getSubjectsBasedOnStandardAndSubjectGroup(Subject subject) throws CustomException;

	String addTimeTable(AcademicTimeTable academicTimeTable) throws CustomException;

	String getTimeTableDurationSlotForValidation(AcademicTimeTable academicTimeTable) throws CustomException;

	String addSubjectBreakAndTeacherForTimeTable(AcademicTimeTable academicTimeTable1) throws CustomException;

	String getTeacherNames(String subjectName) throws CustomException;

	String getTeacherConflictionForTimeTable(AcademicTimeTable academicTimeTable) throws CustomException;

	List<AcademicTimeTable> getTimeTableDetails(AcademicTimeTable academicTimeTable) throws CustomException;

	List<AcademicTimeTableDetails> getTimeTableSubjectCount(AcademicTimeTable academicTimeTable) throws CustomException;

	String deleteDraggedElementForAcademicTimeTable(AcademicTimeTable academicTimeTable) throws CustomException;

	String updateAssignedPeriodDuration(AcademicTimeTableDetails academicTimeTable) throws CustomException;

	String updateSubjectTypeTimeTable(AcademicTimeTable academicTimeTable) throws CustomException;
	
	List<FeesTemplate> getSearchBasedFeesTemplateList(Map<String, Object> parameters) throws CustomException;

	List<Student> getSearchBasedStudentList(Map<String, Object> parameters) throws CustomException;

	List<Candidate> getSearchBasedMedFitDocumentsList(Map<String, Object> parameters) throws CustomException;

	String getSubjectsForTeacher(String teacher) throws CustomException;

	String editTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) throws CustomException;

	List<StudentResult> getStudentsResultForPromotion(StudentResult studentResult) throws CustomException;

	String updateStudentPromotion(List<StudentResult> studentResultList) throws CustomException;

	List<StudentResult> getViewPendingPromotion() throws CustomException;

	String addTC(StudentTC studentTC) throws CustomException;

	List<ExStudents> searchExStudents(Map<String, String> parameters) throws CustomException;

	int deleteSelectedAttachment(int attachId) throws CustomException;
	
	List<EventType> getEventType();

	String getCalendarEventFromDBForPersonal(String eventType,String userId);
	
	//Leave
	
	String insertLeaveType(AcademicLeaveCategory academicLeaveCategory);

	String deleteLeaveType(AcademicLeaveCategory academicLeaveCategory);

	String editLeaveType(AcademicLeaveCategory academicLeaveCategory);

	String getPreviousLeaveType();

	List<AcademicLeave> getLeaveTypeList();

	List<AcademicLeaveCategory> getleavetypes();

	String insertLeaveStructure(AcademicLeave academicLeave);

	List<AcademicLeave> listLeave(AcademicLeave academicLeave);

	String updateLeaveStructure(AcademicLeave academicLeave);

	List<StudentSubjectMapping> getStudentSubjectMappingList() throws CustomException;

	List<Student> getStudentsInStudentSubjectMapping(Student student) throws CustomException;

	List<Student> getStudentsNotInStudentSubjectMapping(Student student) throws CustomException;

	String insertClassSubjectMapping(List<StudentSubjectMapping> mappingList) throws CustomException;

	NoticeBoard viewNotice(NoticeBoard noticeBoard);

	public Standard getStudentRollAgainstStandardAndSection(Student student) throws CustomException;

	List<AcademicLeave> getEmployeeCompleteLeaveDetails(AcademicLeave academicLeave);
	
	public List<StudentSubjectMapping> listUpdatedStudentSubjectMapping(Student student) throws CustomException;
	
	
	
//-------------------FOR NOTICE: DONE BY SINGH


	String createNotice(NoticeBoard noticeBoard);

	String updateNotice(NoticeBoard noticeBoard);

	String deleteNotice(NoticeBoard noticeBoard);
	
	
	//singh.backoffice

	String addSocialCategory(SocialCategory socialCategory) throws CustomException;
	
	String editSocialCategory(SocialCategory socialCategory) throws CustomException;
	
	List<VendorType> getVendorTypes();
	
	String addVendor(Vendor vendor);
	
	String updateVendorDetails(Vendor vendor);
	
	
	
	
	//anup.backoffice
	
	List<Leave> getLeave() throws CustomException;

	String editLeave(Leave leave) throws CustomException;

	List<Fees> getFeesList() throws CustomException;
	
	List<AcademicYear> getAcademicYearList() throws CustomException;

	String editFees(Fees fees) throws CustomException;

	List<SocialCategory> getSocialCategoryList() throws CustomException;

	List<FeesTemplate> getFeesTemplateList() throws CustomException;

	String addFeesTemplate(FeesTemplate feesTemplate) throws CustomException;

	FeesTemplate getFeesTemplateDetails(String templateCode) throws CustomException;

	List<Student> getStudentList() throws CustomException;

	public Student getStudentListForEdit(Student student);
	
	List<Candidate> getFeesPaidCandidate() throws CustomException;
	
	
	//from sms


	public String createNewFinancialYear(FinancialYear financialYear);

	public String updateFinancialYear(FinancialYear financialYear);

	public List<Course> listCourse();

	public void updateWorkingDaysForCheckedCloseDay(Term term);

	public void insertHolidays(Term term);

	public List<Term> listTermHolidaysForShow();

	public void updateWorkingDays(Term term);

	public List<Term> listTermDetails();

	public List<CalendarEvent> getlistSpecialHolidays();

	public String getClassForAttendanceFromDB();
	
	public String fetchTeacherId(StudentAttendance studentAttendance);

	public String getTeachingLevgetTeacherDetailsForAttendanceelForAttendance(
			StudentAttendance studentAttendance);

	public String getCourseForAttendance(String className);

//		public String getStreamForAttendanceFromDB(String streamlist);

	public String getSectionForAttendanceFromDB(Resource resource);

	public String fetchStudentIdFromDB(StudentAttendance studentAttendance);

	public String getStudentsForAttendanceFromDB(Resource resource);

	public String getDateOfCreationForInsertedStudent(String month, String year, String studentId);

	public String getDateOfCreationForInsertedResource(String month,
			String year, String userId, String shift, String resourceType);

	public void updateStudentAttendanceFromDB(
			StudentAttendance studentAttendanceAnother);

	public void insertStudentAttendanceFromDB(
			StudentAttendance studentAttendance);

	public void updateTeacherAttendance(
			StudentAttendance studentAttendanceObject);

	public void insertTeacherAttendance(
			StudentAttendance studentAttendanceObject);

	public String getWorkShiftForAttendance();

	public String fetchTeacherAttendance(ResourceAttendance resourceAttendance);
	
	public LibraryPolicy showLibraryPolicy(LibraryPolicy libraryPolicy);

	public LibraryPolicy saveLibraryPolicy(LibraryPolicy libraryPolicy);

	public List<AcademicYear> getCurrentAcademicYear();

	public List<Rating> getRatingPolicy(Rating r);

	public List<Rating> saveRatingPolicy(List<Rating> ratingList);

	public VendorRatingPolicy showVendorPolicy( VendorRatingPolicy vendorRatingPolicy);

	public VendorRatingPolicy saveVendorPolicy(VendorRatingPolicy vendorRatingPolicy);

	public AttendancePolicy getAttendancePloicy();

	public void saveAttendancePolicy(AttendancePolicy attendancePolicy);

	public Exam getExamPloicy();

	public void saveExamPolicy(Exam exam);

	public AcademicYear getAcademicYearClassAndExamType();

	public String getCourseInClass(String classCode);

	public String getTermForCourse(String courseCode);

	public String getExamsForTermCourseAndExamType(Exam ex);

	public String getSectionForClassAndStream(Class klass);

	public String getStudentForClassStreamSectionAndCourse(Resource resource);

	public List<BookForQRCode> readBookDataToGenerateQRCode();

	public void updateBookIdForQRCode(String bookIndividualCode);

	public List<StudentForQRCode> readStudentDataToGenerateQRCode();

	public List<TeacherForQRCode> readTeacherDataToGenerateQRCode();

	public AcademicYear selectCurrentAcademicYear();

	public List<Class> getClassNameAndCodeList();

	public String getSectionAgainstClassAndStream(Section section);

	public String getResourceAgainstSection(Section sectionCode,String course);

	public String checkWheatherFeesPaid(SessionFees sf);

	public String grantTC(StudentTc studentTc);

	public List<String> getBookIdForQrCode(String strQuery);

	public String getTermDate(String termid);

	public String getTermList(String courseCode);

	List<Scholarship> getScholarshipList() throws CustomException;

	String addScholarship(Scholarship scholarship) throws CustomException;

	String editScholarship(Scholarship scholarship) throws CustomException;

	Student getCandidateDetailsAgainstFromId(Resource resource) throws CustomException;

	Student getStudentDetails(String rollNumber) throws CustomException;

	String editStudent(Student student) throws CustomException;

	public List<AcademicYear> getAcademicYearForTerm();

	public List<Term> getTermNameList();

	public void insertAcademicTermFromDB(Term term);

	public void editAcademicTermTypeFromDB(Term term);

	public List<Term> specifictermforsingle(Term term);

	public List<Holiday> listspeHolidays(Holiday holi);

	public List<Standard> getclassListForTermCreation();

	public List<Course> getcourseListForTermCreationFromDB(	String strClass);

	public void updatePublicHoliday(Holiday holiday);
	
///---------------------FOR TIME TABLE BY SAIKAT DAS------------------------


	public List<Resource> getTeacherList();
	
	public List<Standard> getStandardList();
	
	public List<Subject> getSubjectList();
	
	public List<TimeTableGridData> getAllTimeTableGridData();
	
	public TimeTableGridData getTimeTableGridData(TimeTableGridData timeTableGridData);
	
	public void insertTimeTableGridData(TimeTableGridData timeTableGridData);
	
	public void updateTimeTableGridData(TimeTableGridData timeTableGridData);
	
	public List<TimeTableConfigModel> addTimeTableConfigData(List<TimeTableConfigModel> timeTableConfigModelList, String updatedBy);
	
	public List<TimeTableConfigModel> getAllTimeTableList();
	
	public String editAndUpdateTimeTable(TimeTableConfigModel timtab);

	String deleteClassForTeacher(String detailsId)throws CustomException;
//----------------------FOR LIBRARY FINE--------------------

	public List<BookAllocation> getIssuedBookDetails(Resource resource);

	public List<BookAllocation> submitLibrayFine(Resource resource,
			PreviousYearFinanceData previousYearFinanceData);
	
//-------------------------------START IT SECTION-----------------------------
	
	public List<ITSection> viewAllITSections();
	
	public String createITSections(ITSection itSection);
	
	public String updateITSection(ITSection itSection);
	
	public List<ITSectionDetails> getRebatesForITSection(String itSectionCode);
	
	public String submitITSectionRebates(ITSection itSection);
	
	public String editITSectionRebates(ITSection itSection, ITSection itSecNew);
	
	public List<IncomeAge> getIncomeAgeList();
	
	public List<ITSection> getUnmappedITSections(ITSectionGroup itSectionGroup);
	
	public String insertITSectionDeductionAmount(ITSectionGroup itSectionGroup);
	
	public List<ITSectionGroup> getITSectionGroupForAgeYear(ITSectionGroup itSectionGrp);
	
	public String checkITSecDetailAmount(ITSectionGroup itSectionGrp);
	
	public ITSectionGroup getITSectionForITGroups(ITSectionGroup itSectionGrp);
	
	public List<ITSectionDetails> getITSectionDetailForITSecs(ITSection itSec);
	
	public String submitITSectionRebateAmounts(ITSectionGroup itSectionGroup);
	
	public ITSectionGroup getRebateAmountDetailForITGroup(ITSectionGroup itSectionGrp);
	
	public String updateITSectionRebateAmountLimit(ITSectionGroup itSectionGroup);
	
//-------------------------------END IT SECTION------------------------------	
///-------------------------------START STUDENT TC---------------------
	
	String getNameStandardSectionForRollNumber(String rollNumber)throws CustomException;
	
	String getStudentFeesPaymentStatus(String rollNumber) throws CustomException;
	
//------------------------------------END STUDENT TC--------------------		
	public String checkSocialCategoryName(String socialCategoryName);
	
	public List<AdmissionForm> admissionDriveListForFeesSubmission();

	public String inactiveItRebate(String itSection);
	
	public AdmissionProcess getRegistrationIdFormListClassForFeesSubmission(AdmissionProcess admissionProcess);

	List<Resource> submitFeesForStudent(Student student);

	Resource getCandidateDetails(Resource resource);

	List<FeesCategory> getFeeStructureForStudent(Resource resource);

	public List<ResourceType> getResourceTypes();
/**
 * for attendance calendar*/
	
	public String getStudentsForView(Resource resource);

	public AttendanceDetails getResourceAbsentDateRecord(AttendanceDetails attendanceDetails);

	public AttendanceDetails getTeacherAttendanceRecord(AttendanceDetails attendanceDetails);
	
	public List<Course> getCourseListForCreateStudent();

	public String getFormIdAgainstCourseId(String courseId, String driveName);//Modified by naimisha 18082017

	public String getAdmissionDriveNameAgainstCourseId(String courseId);
	
	/**
	 * @author anup.roy
	 * new fees portion starts**/
	/**new*/
	public List<FeesCategory> selectCategory();

	public List<FeesDuration> selectFeesDuration();

	public String insertCategory(FeesCategory feescategory);

	public String editFeesStructure(FeesCategory feescategory);

	public List<StudentFeesTemplate> studentFeesTemplateList();

	public String createNewFeesTemplate(StudentFeesTemplate studentFeesTemp);

	public String editStudentFeesTemplates(StudentFeesTemplate studentFeesTemplate);

	public List<StudentFeesTemplate> getAllFeesTemplates();

	public List<Course> getAllUnmappedCourses();

	public List<SocialCategory> getAllSocialCategories();

	public List<FeesCategory> getTemplateWiseFeesStructure(String templateCode);

	public String submitAmountForStudentFeesTemplate(List<StudentFeesTemplate> studentFeeTempList);

	public List<StudentFeesTemplate> getstudentFeesTemplateWithAmountList();

	public List<FeesCategory> getStudentFeesTemplateAmountDetails(String courseName);

	public String editAmountDetailsForStudentFeesTemplate(List<StudentFeesTemplate> studentFeeTempList);

	public List<Standard> getAllStandardsName();

	public String getCourseForClass(String standardCode);

	public String getSectionForStandard(String standardCode);
	//anup.roy for getting students against standard and section
	public String getStudentAgainstSection(String section, String standard);

	public String getFeeStructureAgainstStudent(FeesCategory category);

	public String deleteStudentFeesTemplateAmountDetails(Resource r);
	
	public String getTeachersForAttendanceFromDB(Resource resource);
	
	public String getDateOfCreationForInsertedTeacher(String month, String year, String teacherId);

	public String insertIntoVenueTeacherMapping(Resource resource);
	
	public String getStudentsForAssignRollNumber(String course);

	public String generateRollNumberForStudent(String course, String academicYear, String updatedBy);

	public String addProgramPolicy(Course course);

	public String getProgramPolicy();

	public String insertStudentCourseSectionMapping(Student student);

	public List<Term> getTermsForfeesTemplate(String program);

	public List<Course> getProgramsForInterviewPanel();

	public Student getCandidateDetailsAgainstUserId(String userId);

	public List<BookAllocation> getBookDetailsForFineList() throws CustomException;

	public List<StudentResult> getMarksOfAllSubjectForPromotion(Student student);

	public String getCourseNameAgainstCourseCode(String course);

	public String getTermNameAgainstTermCode(String desc);

	public List<Vendor> getBankDetails()throws CustomException;
	
	public int addBank(Vendor vendor);
	
	public String editBankDetails(Vendor vendor);
	
	public String inactiveBankDetails(Vendor vendor);

	public int addBank(Vendor vendor, Ledger ledger);

	public List<Module> getModuleListFortab(String role);

	public ResourceProfile getPersonalDetailsForResourceProfile(String userId);
	
	public String createMajorEvents(MajorEvents majorEvent);

	/* added by sourav.bhadra on 20/07/2017 to get holiday details of a month */
	public Term getHolidayDetailsOfAMonth(String month, String year);

	/**@author Saif.Ali*/
	public List<ResourceType> getResourceType();

	public String submitLeavePolicy(LeavePolicy leavePolicy);

	public LeavePolicy getLeavePolicyToShow(String resourceTypeSelect);

	/**@author anup.roy 28.07.2017 for getting student profile data*/
	public Student getStudentProfileAgainstSchoolNumber(String schoolNumber);
	/**@author anup.roy 30.08.2017 for submitting session fees*/
	public String updateStudentFees(List<SessionFees> sessionFessList);
	/**@author anup.roy 05.09.2017 for getting unmapped standard for template*/
	List<Standard> getUnmappedStandardsToFeesTemplate(String templateCode);

	/**
	 * @author anup.roy
	 * this method is for updating the academic year name */
	public String updateAcademicYear(AcademicYear academicYear);
	/**Added by @author Saif.Ali  Date- 21/03/2018*/
	public String getCurrentModuleName(String userId);
	
	/**Added by @author Saif.Ali  Date- 12/03/2018*/
	public List<UpdateLog> getAllActivityLogList(String moduleName);
	
	//missing link integration 17042018
	public String submitCategoryWithSLA(Ticket ticketObj);
	//missing link integration 17042018
	public List<Ticket> getAllCategorySLAList();

	/**@author anup.roy*/
	//missing link integration 17042018
	public List<EmailEventAndTemplate> getListOfTemplateForCategoryTemplateUserSLA();
	/**@author anup.roy*/
	//missing link integration 17042018
	public List<Resource> getListOfResourceForCategoryTemplateUserSLA();
	/**@author anup.roy*/
	//missing link integration 17042018
	public String submitMapCategoryTemplateUser(CategoryAndTemplate category);
	/**@author anup.roy*/
	//missing link integration 17042018
	public List<CategoryAndTemplate> getMappedCategoryTemplateAndUserList();

	
	//Added by naimisha 30012018
	//missing link integration 17042018
	public String submitMapTaskWithTemplate(CategoryAndTemplate category);
	//missing link integration 17042018
	public List<CategoryAndTemplate> getMappedTaskTemplateList();
	
	//Added By Naimisha 20042018

	public List<EmailEventAndTemplate> getTemplateForATemplateType(String templateType);

	List<TicketStatus> getPossibleTaskStatusListForATask(String category);

	public List<ResidentType> getAllResidentTypeList();
	
	//PRAD ADDED FROM THE SSP CODEBASE MAY 28 2018
	public Student getAdmissionAndDateOfBirthDate();
	
	public String insertDisciplinaryAction(Student student);
	
	public String submitStudentsDailyAttendance(List<StudentAttendance> studentAttendanceList);
	
	public String getStudentsLeaveDetailsForDailyAttendanceSubmit(String currentDate);
	
	public List<Student> getAllListOfDisciplinaryAction();
	
	public List<StudentAttendance> getStudentsRollNumbersForAlreadyAttendedStudents(Resource resource);

	public String insertDisciplinaryCode(DisciplinaryAction disciplinaryAction);

	public List<DisciplinaryAction> getAllDisciplinaryCodeList();

	public DisciplinaryAction getDescriptionAgainstDisciplinaryCode(String disciplinaryCode);
	
	public String addWebIQTransaction(WebIQTransaction webIQTran);

	public String getStandardNameforCourse(String courseId);
	
	public String getHouseName(String houseCode);
	
	public String getMobileNumberAgainstRollNumber(String rollNumber);
}
